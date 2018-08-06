(ns meander.match.alpha
  (:refer-clojure :exclude [compile])
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as s.gen]
            [meander.syntax.alpha :as r.syntax]
            [meander.matrix.alpha :as r.matrix])
  (:import [java.util.concurrent.atomic AtomicInteger]))


;; ---------------------------------------------------------------------
;; Utilities


(defmacro undefined []
  `(throw (ex-info "undefined" '~(meta &form))))


(defonce
  ^{:tag AtomicInteger
    :private true}
  gensym-id
  (AtomicInteger.))


(defn next-gensym-id
  {:private true}
  []
  (.incrementAndGet gensym-id))


(defmacro gensym*
  "Custom version of gensym which prefixes the symbol with the line
  number. This is useful for debugging macro expansions."
  {:private true}
  ([]
   ;; "M" means "match".
   `(symbol (format "M%d__G%d"
                    ~(:line (meta &form))
                    (next-gensym-id))))
  ([prefix]
   `(symbol (format "M%d__%s%d"
                    ~(:line (meta &form))
                    ~prefix
                    (next-gensym-id)))))


;; ---------------------------------------------------------------------
;; Matrix compilation
;;
;; 1. Prioritize matrix columns by score. For example, the column
;;    containing the most literals should be prioritized over all
;;    other columns.
;;
;;    ;; Targets
;;    [target_0, target_1]
;;    ;; Matrix
;;    [[:lvr ?x] [:lit 1]]
;;    [[:mvr !x] [:lit :A]]
;;
;;    ;; =>
;;
;;    ;; New targets
;;    [target_1, target_0]
;;    ;; New matrix
;;    [[:lit 1] [:lvr ?x]]
;;    [[:lit :A] [:mvr !x]]
;;
;;    
;; 2. Split matrix into specialized matrices, matrices such that the
;;    first column of each matrix shares some common property (usually
;;    a tag).
;;
;; 3. Compile each submatrix.
;;
;; 4. When there are no more columns emit the code for the right hand
;;    side (also known as the action).

(declare compile)


(s/def :meander.match.alpha/target
  simple-symbol?)


(s/def :meander.match.alpha/state
  (s/tuple (s/coll-of :meander.match.alpha/target
                      :kind sequential?
                      :into [])
           :meander.matrix.alpha/matrix))


(defn compile-specialized-matrix-dispatch
  {:private true}
  [tag targets s-matrix default]
  tag)


(defmulti compile-specialized-matrix
  #'compile-specialized-matrix-dispatch)

;; :any


(defmethod compile-specialized-matrix :any
  [_ [_ & targets*] s-matrix default]
  [[true
    (compile targets* (r.matrix/drop-column s-matrix) default)]])


;; :cat

(defmethod compile-specialized-matrix :cat
  [_ [target & targets*] s-matrix default]
  (sequence
   (map
    (fn [[length matrix]]
      (let [nth-targets (mapv
                         (fn [_]
                           (gensym* "nth__"))
                         (range length))]
        [true
         `(let [~@(sequence
                   (mapcat
                    (fn [nth-target i]
                      [nth-target `(nth ~target ~i)]))
                   nth-targets
                   (range length))]
            ~(compile (concat nth-targets targets*)
                      (sequence
                       (map
                        (fn [row]
                          (let [[[_ nodes] & rest-cols] (:cols row)]
                            (assoc row :cols (concat nodes rest-cols)))))
                       matrix)
                      default))])))
   (r.matrix/specialize-by
    (comp count r.syntax/data)
    s-matrix)))


;; :lit

(defmethod compile-specialized-matrix :lit
  [_ [target & targets*] s-matrix default]
  (sequence
   (map
    (fn [[[_ lit-val] matrix]]
      [`(= ~target ~lit-val)
       (compile targets* (r.matrix/drop-column matrix) default)]))
   (r.matrix/specialize-by identity s-matrix)))


;; :lvr

(defmethod compile-specialized-matrix :lvr
  [_ [target & targets*] s-matrix default]
  (sequence
   (map
    (fn [[_ lvr-sym :as lvr] row]
      (if (r.matrix/get-var row lvr)
        [`(= ~lvr-sym ~target)
         (compile targets* [row] default)]
        [true
         `(let [~lvr-sym ~target]
            ~(compile targets* [(r.matrix/add-var row lvr)] default))])))
   (r.matrix/nth-column s-matrix 0)
   (r.matrix/drop-column s-matrix)))


;; :mvr

(defmethod compile-specialized-matrix :mvr
  [_ [target & targets*] s-matrix default]
  (sequence
   (map
    (fn [[_ mvr-sym :as mvr] row]
      [true
       `(let [~mvr-sym ~(if (r.matrix/get-var row mvr)
                          `(conj ~mvr-sym ~target)
                          `[~target])]
          ~(compile targets* [(r.matrix/add-var row mvr)] default))]))
   (r.matrix/nth-column s-matrix 0)
   (r.matrix/drop-column s-matrix)))


;; :prd

(defmethod compile-specialized-matrix :prd
  [_ [target & targets*] s-matrix default]
  (sequence
   (map
    (fn [[[_ pred] matrix]]
      [`(~pred ~target)
       (compile targets* (r.matrix/drop-column matrix) default)]))
   (r.matrix/specialize-by identity s-matrix)))


;; :prt

(defmethod compile-specialized-matrix :prt
  [_ [target & targets*] s-matrix default]
  (let [s-matrices (r.matrix/specialize-by
                    (comp r.syntax/variable-length? :left r.syntax/data)
                    s-matrix)
        l-target (gensym* "left__")
        r-target (gensym* "right__")]
    (concat
     ;; Left tree has a fixed length.
     (when-some [[_ fl-matrix] (find s-matrices false)]
       (sequence
        (map
         (fn [[min-length ml-matrix]]
           [true
            `(let [~l-target (take ~min-length ~target)
                   ~r-target (drop ~min-length ~target)]
               ;; l-target is pushed on to the target stack twice:
               ;; once for it's length be checked (by a :prd node
               ;; below), and once for pattern matching.
               ~(compile (list* l-target l-target r-target targets*)
                         (sequence
                          (map
                           (fn [row]
                             (let [[[_ {:keys [left right]}] & rest-cols] (:cols row)]
                               (assoc row :cols (list* [:prd `(comp #(= ~min-length %) count)]
                                                       left
                                                       (or right [:prd `(complement seq)])
                                                       rest-cols)))))
                          ml-matrix)
                         default))]))
        (r.matrix/specialize-by
         (comp r.syntax/min-length :left r.syntax/data)
         fl-matrix)))
     ;; Left tree has a variable length.
     (when-some [[_ vl-matrix] (find s-matrices true)]
       (sequence
        (map
         (fn [[min-length mr-matrix]]
           (let [n (gensym* "target_length__")
                 m (gensym* "target_mark__")]
             [true
              `(let [~n (count ~target)
                     ~m (max 0 (- ~n ~min-length))
                     ~l-target (take ~m ~target)
                     ~r-target (drop ~m ~target)]
                 ;; r-target is matched before l-target because it has a
                 ;; fixed length. It is pushed on to the stack twice:
                 ;; once for it's length to be checked, and once for
                 ;; pattering matching.
                 ~(compile (list* r-target r-target l-target targets*)
                           (sequence
                            (map
                             (fn [row]
                               (let [[[_ {:keys [left right]}] & rest-cols] (:cols row)]
                                 (assoc row :cols (list* [:prd `(fn ~(gensym* "check_rlength__")
                                                                  [~r-target]
                                                                  (= ~min-length (count ~r-target)))]
                                                         (or right [:any '_])
                                                         left
                                                         rest-cols)))))
                            mr-matrix)
                           default))])))
        (r.matrix/specialize-by
         (comp (fnil r.syntax/min-length 0) :right r.syntax/data)
         vl-matrix))))))


;; :quo

(defmethod compile-specialized-matrix :quo
  [_ [target & targets*] s-matrix default]
  (sequence
   (map
    (fn [[[_ {form :form}] matrix]]
      [`(= ~target (quote ~form))
       (compile targets* (r.matrix/drop-column matrix) default)]))
   (r.matrix/specialize-by identity s-matrix)))


;; :rp*

(defmethod compile-specialized-matrix :rp*
  [_ [target & targets*] s-matrix default]
  (sequence
   (map
    (fn [[_ {items :items}] row]
      (let [n (count items)
            cat [:cat items]
            vars (r.syntax/variables cat)
            ;; Initial bindings for unbound memory and logic
            ;; variables. Unbound memory variables are initialized as
            ;; usual and logic variables are initialized to ::unbound.
            let-bindings (sequence
                          (mapcat
                           (fn [[tag sym :as var]]
                             (if (r.matrix/get-var row var)
                               nil
                               [sym (case tag
                                      :mvr []
                                      :lvr ::unbound)])))
                          vars)
            env (r.matrix/get-env row)
            let-env (into env
                          (filter r.syntax/mvr-node?)
                          vars)
            let-else (compile targets*
                              (r.matrix/drop-column [row])
                              default)
            let-syms (take-nth 2 let-bindings)
            loop-name (gensym* "loop__")
            ;; The environment the loop will execute in. Includes all
            ;; memory and logic variables.
            loop-env (into env vars)
            ;; Memory variables are "stateful" and need to be tracked
            ;; at each iteration of the loop.
            loop-syms (into [target]
                            (comp (filter r.syntax/mvr-node?)
                                  (map r.syntax/data))
                            loop-env)
            ;; When failing to match the first n elements check if
            ;; target is empty. If it is continue matching, default
            ;; condition otherwise.
            loop-else `(if (not (seq ~target))
                         ~(compile targets*
                                   (r.matrix/drop-column [(assoc row :env loop-env)])
                                   default)
                         ~default)
            ;; This symbol points to the current first n elements of
            ;; target.
            slice (gensym* "slice__")
            ;; Checked at the top of each loop. 
            prd [:prd `(fn [~slice] (= ~n (count ~slice)))]]
        [true
         `(let [~@let-bindings
                ~slice (take ~n ~target)]
            ~(compile [slice slice]
                      [{:cols [prd cat]
                        :env let-env
                        :rhs
                        `((fn ~loop-name ~loop-syms
                            (let [~slice (take ~n ~target)]
                              ~(compile [slice slice]
                                        [{:cols [prd cat]
                                          :env loop-env
                                          :rhs
                                          `(let [~target (drop ~n ~target)]
                                             (~loop-name ~@loop-syms))}]
                                        loop-else)))
                          (drop ~n ~target)
                          ~@(rest loop-syms))}]
                      let-else))])))
   (r.matrix/nth-column s-matrix 0)
   (r.matrix/drop-column s-matrix)))


;; :seq


(defmethod compile-specialized-matrix :seq
  [_ [target :as targets] s-matrix default]
  [[`(seq? ~target)
    (compile targets
             (sequence
              (map
               (fn [row]
                 (let [[[_ prt] & rest-cols] (:cols row)]
                   (assoc row :cols (cons prt rest-cols)))))
              s-matrix)
             default)]])


;; :unq


(defmethod compile-specialized-matrix :unq
  [_ [target & targets*] s-matrix default]
  (sequence
   (map
    (fn [[[_ {expr :expr}] matrix]]
      [`(= ~expr ~target)
       (compile targets* (r.matrix/drop-column matrix) default)]))
   (r.matrix/specialize-by identity s-matrix)))


;; :vec

(defmethod compile-specialized-matrix :vec
  [_ [target :as targets] s-matrix default]
  [[`(vector? ~target)
    (compile targets
             (sequence
              (map
               (fn [row]
                 (let [[[_ prt] & rest-cols] (:cols row)]
                   (assoc row :cols (cons prt rest-cols)))))
              s-matrix)
             default)]])


(defn prioritize-matrix
  {:arglists '([[targets matrix :as state]])}
  [[targets matrix]]
  (let [;; Compute new column arragement
        new-indices (sequence
                     (map
                      (fn [[index _]]
                        index))
                     (sort-by
                      (fn [[_ rank]]
                        (- rank))
                      (sequence
                       (map-indexed
                        (fn [index i]
                          [index (transduce
                                  (map r.syntax/rank)
                                  +
                                  (r.matrix/nth-column matrix i))]))
                       (range (r.matrix/width matrix)))))
        ;; Reorder targets
        targets* (map nth (repeat targets) new-indices)
        ;; Reorder columns
        matrix* (map
                 (fn [row]
                   (assoc row :cols (mapv nth (repeat (:cols row)) new-indices)))
                 matrix)
        ;; Reorder rows
        matrix* (sort-by
                 (fn [row]
                   (- (r.syntax/rank (first (:cols row)))))
                 matrix*)]
    [targets* matrix*]))


(s/fdef prioritize-matrix
  :args (s/cat :state :meander.match.alpha/state)
  :ret :meander.match.alpha/state)


(def backtrack
  (Exception. "non exhaustive pattern match"))


(defn try-form [expr catch]
  `(try
     ~expr
     (catch ~'Exception exception#
       (if (identical? exception# backtrack)
         ~catch
         (throw exception#)))))


(defn compile [targets matrix default]
  (if (seq targets)
    (let [[targets* matrix*] (prioritize-matrix [targets matrix])
          {no-preds true, preds false} (group-by (comp true? first)
                                                 (sequence
                                                  (mapcat
                                                   (fn [[tag s-matrix]]
                                                     (compile-specialized-matrix tag targets* s-matrix default)))
                                                  (r.matrix/specialize-by r.syntax/tag matrix*)))
          no-pred-body (reduce
                        (fn [next-choice [_ body-form]]
                          (if (= next-choice default)
                            body-form
                            (try-form body-form next-choice)))
                        default
                        no-preds)
          pred-body (reduce
                     (fn [else [test then]]
                       `(if ~test
                          ~then
                          ~else))
                     no-pred-body
                     (reverse preds))]
      (if (seq preds)
        (if (seq no-preds)
          (try-form pred-body no-pred-body)
          pred-body)
        no-pred-body))
    (:rhs (first matrix))))


;; ---------------------------------------------------------------------
;; Match macro


(s/def :meander.match.alpha/clause
  (s/cat
   :pat ::r.syntax/term
   :rhs any?))


(s/def :meander.match.alpha.match/args
  (s/cat :target any?
         :clauses (s/* :meander.match.alpha/clause)))


(s/fdef parse-match-args
  :args (s/cat :match-args :meander.match.alpha.match/args)
  :ret any?)


(defn parse-match-args
  [match-args]
  (let [data (s/conform :meander.match.alpha.match/args match-args)]
    (if (identical? data ::s/invalid)
      (undefined)
      (let [matrix (mapv
                    (fn [{:keys [pat rhs]}]
                      {:cols [pat]
                       :env #{}
                       :rhs rhs})
                    (:clauses data))]
        (assoc data :matrix matrix)))))


(s/fdef match
  :args (s/cat :target any?
               :clauses (s/* (s/cat :pat ::r.syntax/term
                                    :expr any?)))
  :ret any?)


(defmacro match
  {:arglists '([x & clauses])}
  [& match-args]
  (let [data (parse-match-args match-args)
        target (gensym "target__")]
    `(let [~target ~(:target data)]
       ~(compile [target] (:matrix data) `(throw backtrack)))))


