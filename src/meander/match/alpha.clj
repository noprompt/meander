(ns meander.match.alpha
  (:refer-clojure :exclude [compile])
  (:require [clojure.set :as set]
            [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as s.gen]
            [meander.syntax.alpha :as r.syntax]
            [meander.matrix.alpha :as r.matrix])
  (:import [java.util.concurrent.atomic AtomicInteger]))


;; ---------------------------------------------------------------------
;; Utilities


(defmacro undefined
  {:private true}
  []
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
;;    a tag). Here specialization has a slighly different meaning than
;;    what is commonly described in the literature where it means to
;;    extract a new matrix with respect to a type constructor.
;;
;;
;; 3. Compile each submatrix.
;;
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


(s/fdef compile-specialized-matrix
  :args (s/cat :tag :meander.syntax.alpha.node/tag
               :targets (s/coll-of :meander.match.alpha/target
                                   :kind sequential?
                                   :into [])
               :s-matrix :meander.matrix.alpha/matrix
               :default any?)
  :ret (s/coll-of (s/tuple any? any?)
                  :kind sequential?
                  :into []))


(defmulti compile-specialized-matrix
  {:arglists '([tag targets s-matrix default])}
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
                         (fn [i]
                           (gensym* (str "nth_" i "__")))
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

;; :cap


(defmethod compile-specialized-matrix :cap
  [_ [target & targets*] s-matrix default]
  (sequence
   (map
    (fn [[_ {term :term, binding :binding} :as cap-node] row]
      (cond
        ;; This can never match.
        (r.syntax/circular-cap? cap-node)
        [false default]

        ;; If the logic variable binding is already bound and term is
        ;; free of memory variables and unbound logic variables, push
        ;; only the binding on to the stack.
        (and (r.syntax/lvr-node? binding)
             (r.matrix/get-var row binding)
             (every?
              (fn [var-node]
                (and (not (r.syntax/mvr-node? var-node))
                     (r.matrix/get-var row var-node)))
              (r.syntax/variables term)))
        [true
         (compile
          (cons target targets*)
          [(assoc row :cols (cons binding (:cols row)))]
          default)]

        ;; (?x :as ?x) is ?x
        (and (r.syntax/lvr-node? binding)
             (= term binding))
        [true
         (compile
          (list* target targets*)
          [(assoc row :cols (cons term (:cols row)))]
          default)]

        :else
        [true
         (compile
          (list* target target targets*)
          [(assoc row :cols (cons binding (cons term (:cols row))))]
          default)])))
   (r.matrix/nth-column s-matrix 0)
   (r.matrix/drop-column s-matrix)))


;; :cnj

(defmethod compile-specialized-matrix :cnj
  [_ [target & targets*] s-matrix default]
  (let [max-pats (transduce (comp (map r.syntax/data)
                                  (map :terms)
                                  (map count))
                            max
                            0
                            (r.matrix/nth-column s-matrix 0))]
    [[true
      (compile
       (concat (repeat max-pats target) targets*)
       (sequence
        (map
         (fn [row]
           (let [[[_ {terms :terms}] & rest-cols] (:cols row)]
             (assoc row :cols (concat terms
                                      (repeat (- max-pats (count terms)) [:any '_])
                                      rest-cols)))))
        s-matrix)
       default)]]))


;; :drp

(defmethod compile-specialized-matrix :drp
  [_ [_ & targets*] s-matrix default]
  [[true
    (compile targets* (r.matrix/drop-column s-matrix) default)]])


;; :dsj


(s/fdef analyze-dsj
  :args (s/cat :env :meander.matrix.alpah.row/env
               :dsj-node :meander.syntax.alpha.node/dsj)
  :ret (s/coll-of (s/tuple #{:fail}
                           :meander.syntax.alpha/node
                           (s/coll-of
                            (s/or :lvr :meander.syntax.alpha/logic-variable
                                  :mvr :meander.syntax.alpha/memory-variable)
                            :kind set?
                            :into #{}))))


(defn analyze-dsj
  "Analyze :dsj or and a sequence of [:fail term absent-vars] tuples."
  {:arglists '([env or-term])
   :private true}
  [env [_ {terms :terms}]]
  (let [term-vars (sequence
                   (map
                    (fn [term]
                      ;; We don't need to account for bound variables.
                      (set/difference (r.syntax/variables term) env)))
                   terms)
        all-vars (reduce set/union #{} term-vars)]
    (sequence
     (comp (map vector)
           (keep
            (fn [[term term-vars]]
              (let [absent-vars (set/difference all-vars term-vars)]
                (when (seq absent-vars)
                  [:fail term (into #{} (map r.syntax/unparse) absent-vars)])))))
     terms
     term-vars)))


(s/fdef check-dsj
  :args (s/cat :env :meander.matrix.alpah.row/env
               :dsj-node :meander.syntax.alpha.node/dsj)
  :ret (s/coll-of (s/tuple #{:fail}
                           :meander.syntax.alpha/node
                           (s/coll-of
                            (s/or :lvr :meander.syntax.alpha/logic-variable
                                  :mvr :meander.syntax.alpha/memory-variable)
                            :kind set?
                            :into #{}))))


(defn check-dsj
  "Checks if every variable in a dsj-node occurs in every pattern of
  dsj-node. If not returns an instance of ex-info describing the
  problems and returns nil otherwise. The returned ex-info contains
  the complete problematic or pattern, it's environment, and the
  sequence of offending dsj-nodes."
  {:private true}
  [env dsj-node]
  (when-some [fails (seq (analyze-dsj env dsj-node))]
    (ex-info
     "Every pattern of an or pattern must have references to the same unbound variables."
     {:pat (r.syntax/unparse dsj-node)
      :env (into #{} (map r.syntax/unparse) env)
      :problems (mapv
                 (fn [[_ pat absent-vars]]
                   {:pat (r.syntax/unparse pat)
                    :absent absent-vars})
                 fails)})))


(defmethod compile-specialized-matrix :dsj
  [_ targets s-matrix default]
  (let [matrices (r.matrix/specialize-by
                  (comp count :terms r.syntax/data)
                  s-matrix)]
    (clojure.pprint/pprint matrices)
    (mapcat
     (fn [[n s-matrix]]
       (map
        (fn [dsj-node env row]
          (when-some [ex (check-dsj env dsj-node)]
            (throw ex))
          (let [[_ {terms :terms}] dsj-node]
            (case n
              ;; Just as (clojure.core/or) is falsey so is the (or)
              ;; pattern.
              0
              [false default]

              ;; Since (or pat) ≈ pat compile as if pat were given.
              1
              [true (compile targets
                             [(assoc row :cols (cons (first terms) (:cols row)))]
                             default)]

              ;; Otherwise
              ;; To reduce the amount of code generated a function
              ;; containing the right hand side is compiled. The
              ;; function accepts as arguments any variables that
              ;; occur in the pattern (which are bound upon a
              ;; successful pattern match). The original right hand
              ;; side is then replaced with an invocation of this
              ;; function with the required variables if any.
              (let [unbound-vars (set/difference
                                  (transduce
                                   (map r.syntax/variables)
                                   set/union
                                   (cons dsj-node (:cols row)))
                                  env)
                    unbound-syms (sequence (map r.syntax/unparse) unbound-vars)
                    f-sym (gensym* "f__")
                    rhs* `(~f-sym ~@unbound-syms)
                    matrix (sequence
                            (map
                             (fn [terms]
                               (assoc row
                                      :cols (cons terms (:cols row))
                                      :rhs rhs*)))
                            terms)
                    inner-form (compile targets matrix default)]
                [true
                 `(let [~f-sym (fn ~f-sym [~@unbound-syms]
                                 ~(:rhs row))
                        ~@(sequence
                           (comp (filter r.syntax/mvr-node?)
                                 (mapcat (juxt second (constantly []))))
                           unbound-vars)]
                    ~inner-form)]))))
        (r.matrix/nth-column s-matrix 0)
        (sequence (map :env) s-matrix)
        (r.matrix/drop-column s-matrix)))
     matrices)))


;; :grd

(defmethod compile-specialized-matrix :grd
  [_ [_ & targets*] s-matrix default]
  (sequence
   (map
    (fn [[[_ {expr :form}] s-matrix*]]
      [expr
       (compile targets* (r.matrix/drop-column s-matrix*) default)]))
   (r.matrix/specialize-by identity s-matrix)))


;; :let

(defmethod compile-specialized-matrix :let
  [_ [_ & targets*] s-matrix default]
  (sequence
   (map
    (fn [[_ {binding :binding, expr :expr} :as let-node] row]
      (let [expr-target (gensym* "expr__")]
        [true
         `(let [~expr-target ~expr]
            ~(compile (cons expr-target targets*)
                      [(assoc row :cols (cons binding (:cols row)))]
                      default))])))
   (r.matrix/nth-column s-matrix 0)
   (r.matrix/drop-column s-matrix)))


;; :lit

(defmethod compile-specialized-matrix :lit
  [_ [target & targets*] s-matrix default]
  (sequence
   (map
    (fn [[[_ lit-val] matrix]]
      [`(= ~target '~lit-val)
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


;; :map

;; :map nodes are compiled indirectly via the :mnc (map no check)
;; node. Compilation of a :map matrix consists of compiling a map?
;; check against the primary target and compiling the rewrite of each
;; :map node in the first column to an :mnc node against the targets.

;; Assumes keys are ground.
(defmethod compile-specialized-matrix :map
  [_ [target :as targets] s-matrix default]
  ;; Test if target is a map *once*.
  [[`(map? ~target)
    (compile targets
             (map
              (fn [row]
                (let [[[_ map-data] & rest-cols] (:cols row)]
                  ;; Rewrite :map nodes in the first column as :mnc
                  ;; nodes.
                  (assoc row :cols (cons [:mnc map-data] rest-cols))))
              s-matrix)
             default)]])


(defn compile-mnc-matrix
  {:private true}
  [[map-target & targets*] s-matrix default]
  (let [s-matrix (vec s-matrix)
        column (r.matrix/nth-column s-matrix 0)
        map-data (map r.syntax/data column)
        ;; Index rows by their first column.
        row-by-map (into {} (map vector map-data s-matrix))
        ;; Index maps by common keys.
        maps-by-key (into {}
                          (map
                           (fn [k]
                             [k (into #{}
                                      (filter
                                       (fn [m]
                                         (contains? m k)))
                                      map-data)]))
                          (into #{} (mapcat keys) map-data))
        ;; Rank the keys by the total number of maps they belong to.
        ranked-keys (keys (sort-by (comp - count val) maps-by-key))]
    ;; Compile each key, one at a time.
    (map
     (fn [[k ms]]
       [true
        (let [entry-target (gensym* "entry__")
              val-target (gensym* "val__")]
          ;; find and destructing are useful together here because find
          ;; returns either nil or an entry which destructuring can
          ;; safely pull apart. If the entry exists then entry-target
          ;; will be non-nil making pattern matching on val-target
          ;; possible.
          `(let [~entry-target (find ~map-target ~(r.syntax/unparse k))]
             ~(compile
               [map-target map-target]
               (mapv
                (fn [m]
                  (let [row (get row-by-map m)
                        env (r.matrix/get-env row)]
                    {;; Check if entry was found, then continue
                     ;; checking keys.
                     :cols [[:grd {:form `(some? ~entry-target)}]
                            [:mnc (dissoc m k)]]
                     :env env
                     ;; Bind val from entry-target as late as
                     ;; possible. 
                     :rhs `(let [~val-target (val ~entry-target)]
                             ~(compile
                               (cons val-target targets*)
                               [(assoc row
                                       :cols (cons (get m k) (rest (:cols row)))
                                       :env (into env
                                                  (mapcat r.syntax/variables)
                                                  (vals (dissoc m k))))]
                               default))}))
                ms)
               default)))])
     (sort-by 
      (fn [[k ms]]
        (apply min
          (map
           (fn [m]
             (.indexOf s-matrix (row-by-map m)))
           ms)))
      (sequence
       (keep
        (fn [[k ms]]
          (when (seq ms)
            [k (sort-by
                (fn [m]
                  (.indexOf s-matrix (row-by-map m)))
                ms)])))
       (reduce
        (fn [acc k]
          (let [vs1 (get acc k)]
            (into acc
                  (map
                   (fn [[k vs2]]
                     [k (set/difference vs2 vs1)]))
                  (dissoc acc k))))
        maps-by-key
        ranked-keys))))))

;; :mnc

(defmethod r.syntax/ground? :mnc
  [[_ map-data]]
  (every?
   (fn [[k v]]
     (and (r.syntax/ground? k)
          (r.syntax/ground? v)))
   map-data))


(defmethod compile-specialized-matrix :mnc
  [_ targets s-matrix default]
  (let [{empty true, not-empty false}
        (r.matrix/specialize-by
         (comp empty? r.syntax/data)
         s-matrix)]
    (concat
     (map
      (fn [row]
        [true
         (compile (rest targets) [row] default)])
      (r.matrix/drop-column empty))
     (compile-mnc-matrix targets s-matrix default))))


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
    (fn [[_ {pred :form}] row]
      [`(~pred ~target)
       (compile targets* [row] default)]))
   (r.matrix/nth-column s-matrix 0)
   (r.matrix/drop-column s-matrix)))


;; :prt

(defmethod compile-specialized-matrix :prt
  [_ [target & targets*] s-matrix default]
  (let [s-matrices (r.matrix/specialize-by
                    (comp r.syntax/variable-length? :left r.syntax/data)
                    s-matrix)]
    (concat
     ;; Left tree has a fixed length.
     (when-some [[_ fl-matrix] (find s-matrices false)]
       (let [l-target (gensym* "left__")
             r-target (gensym* "right__")]
         (sequence
          (map
           (fn [[min-length ml-matrix]]
             (if (= min-length 0)
               [true
                (compile (list* target targets*)
                         (sequence
                          (map
                           (fn [row]
                             (assoc row :cols (cons [:grd {:form `(not (seq ~target))}]
                                                    (:cols row)))))
                          (r.matrix/drop-column ml-matrix))
                         default)]
               [true
                `(let [~l-target (take ~min-length ~target)
                       ~r-target (drop ~min-length ~target)]
                   ;; l-target is pushed on to the target stack twice:
                   ;; once for it's length be checked (by a :grd node
                   ;; below), and once for pattern matching.
                   ~(compile (list* l-target l-target r-target targets*)
                             (sequence
                              (map
                               (fn [row]
                                 (let [[[_ {:keys [left right]}] & rest-cols] (:cols row)]
                                   (assoc row :cols (list* [:grd {:form `(= (count ~l-target) ~min-length)}]
                                                           left
                                                           (or right
                                                               [:grd {:form `(not (seq ~r-target))}])
                                                           rest-cols)))))
                              ml-matrix)
                             default))])))
          (r.matrix/specialize-by
           (comp r.syntax/min-length :left r.syntax/data)
           fl-matrix))))
     ;; Left tree has a variable length.
     (when-some [[_ vl-matrix] (find s-matrices true)]
       (let [l-target (gensym* "left__")
             r-target (gensym* "right__")]
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
                                   (assoc row :cols (list* [:grd {:form `(= ~min-length (count ~r-target))}]
                                                           (or right [:any '_])
                                                           left
                                                           rest-cols)))))
                              mr-matrix)
                             default))])))
          (r.matrix/specialize-by
           (comp
            (fn [r]
              (if r
                (r.syntax/min-length r)
                0))
            :right
            r.syntax/data)
           vl-matrix)))))))


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
                              [row]
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
                                   [(assoc row :env loop-env)]
                                   default)
                         ~default)
            ;; This symbol points to the current first n elements of
            ;; target.
            slice (gensym* "slice__")
            ;; Checked at the top of each loop. 
            grd [:grd {:form `(= ~n (count ~slice))}]]
        [true
         `(let [~@let-bindings
                ~slice (take ~n ~target)]
            ~(compile [slice slice]
                      [{:cols [grd cat]
                        :env let-env
                        :rhs
                        `((fn ~loop-name ~loop-syms
                            (let [~slice (take ~n ~target)]
                              ~(compile [slice slice]
                                        [{:cols [grd cat]
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
  (assert (= (count targets)
             (count (:cols (first matrix))))
          "Number of targets does not match the number of columns")
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


(s/def :meander.match.alpha.match/clauses
  (s/* (s/cat :pat ::r.syntax/term
              :expr any?)))


(s/fdef match
  :args (s/cat :target any?
               :clauses :meander.match.alpha.match/clauses)
  :ret any?)


(defmacro match
  {:arglists '([x & clauses])}
  [& match-args]
  (let [data (parse-match-args match-args)
        target (gensym "target__")]
    `(try
       (let [~target ~(:target data)]
         ~(compile [target] (:matrix data) `(throw backtrack)))
       (catch Exception e#
         (if (identical? e# backtrack)
           (throw (Exception. "non exhaustive pattern match"))
           (throw e#))))))
