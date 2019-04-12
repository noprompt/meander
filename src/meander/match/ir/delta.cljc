(ns meander.match.ir.delta
  "Functions for working with the Meander's match compiler
  intermediate representation (IR)."
  (:refer-clojure :exclude [compile])
  #?(:cljs (:require-macros [meander.match.ir.delta :refer [defop]]))
  (:require
   [clojure.zip :as zip]
   [meander.util.delta :as r.util]
   [meander.syntax.delta :as r.syntax]))

;; TODO: (TR) Inline bindings used once
;; TODO: (TR) Remove bindings never used
;; TODO: (TC) create :resolve node for symbols
;; TODO: (TC) replace :eval with :resolve where possible

(defn child-keys [dt]
  (keep
   (fn [[k v]]
     (when (some? (:op v))
       k))
   dt))

(defn children [dt]
  (if (map? dt)
    (case (:op dt)
      :branch
      (:arms dt)

      ;; else
      (map dt (child-keys dt)))))

(defn branch? [dt]
  (some? (seq (children dt))))

(defn make-node [dt new-children]
  (case (:op dt)
    :branch
    (assoc dt :arms new-children)

    ;; else
    (into dt (map vector (child-keys dt) new-children))))

(defn dt-zip [dt]
  (zip/zipper branch? children make-node dt))


(defn height
  "Return the height of dt."
  [dt]
  (if-some [dts (children dt)]
    (transduce (comp (map height)
                     (map inc))
               max
               1
               dts)
    1))

(defn nodes
  "Return all nodes in dt."
  [dt]
  (tree-seq branch? children dt))

;; ---------------------------------------------------------------------
;; Tree nodes

(defmacro defop [symbol op params]
  (let [meta {:arglists `'(~params)
              :style/indent :defn} ]
    `(defn ~(vary-meta symbol merge meta) ~params
       (hash-map ~@(mapcat
                    (fn [param]
                      [(keyword (name param)) param])
                    params)
                 :op ~op))))

(defop op-bind :bind [symbol value then])

(defop op-branch :branch [arms])

(defop op-drop :drop [target n kind])

(defop op-nth :nth [target index])

(defop op-pass :pass [then])

(defop op-eval :eval [form])

(defop op-check :check [test target then])

(defop op-check-array :check-array [target then])

(defop op-check-array-equals :check-array-equals [target-1 target-2 then])

(defop op-check-boolean :check-boolean [test then])

(defop op-check-bounds :check-bounds [target length kind then])

(defop op-check-empty :check-empty [target then])

(defop op-check-equal :check-equal [target-1 target-2 then])

(defop op-check-lit :check-lit [target value then])

(defop op-check-map :check-map [target then])

(defop op-check-seq :check-seq [target then])

(defop op-check-set :check-set [target then])

(defop op-check-vector :check-vector [target then])

;; TODO: No need for :symbol.
(defop op-find :find [symbol value body])

(defop op-load :load [id])

(defop op-lvr-check :lvr-check [symbol target then])

(defop op-lvr-bind :lvr-bind [symbol target then])

(defop op-mvr-append :mvr-append [symbol target then])

(defop op-mvr-bind :mvr-bind [symbol target then])

(defop op-mvr-init :mvr-init [symbol then])

(defop op-save :save [id body-1 body-2])

;; TODO: No need for :symbol.
(defop op-search :search [symbol value body])

(defn op-star
  {:style/indent :defn}
  [input n kind return-symbols body-fn then]
  (let [input-symbol (gensym "input__")]
    {:op :star
     :input-symbol input-symbol
     :input input
     :body (body-fn input-symbol (op-eval (vec return-symbols)))
     :then then
     :kind kind
     :n n
     :return-symbols (vec return-symbols)}))

(defn op-plus
  {:style/indent :defn}
  [input n m kind return-symbols body-fn then]
  (let [input-symbol (gensym "input__")]
    {:op :plus
     :input-symbol input-symbol
     :input input
     :body (body-fn input-symbol (op-eval (vec return-symbols)))
     :then then
     :kind kind
     :n n
     :m m
     :return-symbols (vec return-symbols)}))

(defop op-take :take [target n kind])

(defop op-fail :fail [])

;; ---------------------------------------------------------------------
;; Code generation
;;
;; TODO: Move all vars intended to be used by compiled code to a
;; meander.runtime.<greek-letter> namespace.

(def FAIL
  "Special value signaling a match failure. Generated code will often
  utilize this value as for control flow purposes."
  (reify))

(defn seq-bites
  "Internal function used by compiled :star nodes to successively
  split coll into a sequence of

    ([(take n coll) (drop n coll)]
     [(take n (drop n coll)) (drop (* 2 n) coll)]
     [(take n (drop (* 2 n) coll)) (drop (* 3 n) coll)]
     ,,,
     [(take n (drop (* (- m 1) n) coll)) (drop (* m n) coll)])

  Example:

    (seq-bites 3 '(a b c d e f g))
    ;; =>
    ([(a b c) (d e f g)]
     [(d e f) (g)]
     [(g) ()])"
  [n coll]
  (if (seq coll)
    (lazy-seq
     (cons [(take n coll) (drop n coll)]
           (if (seq (drop n coll))
             (seq-bites n (drop n coll)))))
    (list [() ()])))

(defn seq-bites-indexed
  "Internal function used by compiled :star nodes. Like seq-bites but
  includes the index of each sequence member as a third element."
  ([n coll]
   (seq-bites-indexed n coll 0))
  ([n coll i]
   (if (seq coll)
     (lazy-seq
      (cons [(take n coll) (drop n coll) i]
            (seq-bites-indexed n (drop n coll) (inc i))))
     (list [() () 0]))))

(defn vec-bites
  "Internal function used by compiled :star nodes. Like seq-bites but
  specifically designed for vectors.

  Example:

  (vec-bites 3 '[a b c d e f g])
  ;; =>
  ([[a b c] [d e f g]]
   [[d e f] [g]]
   [[g] []])"
  [n coll]
  (if (seq coll)
    (map
     (fn [[a b]]
       (if b
         [(subvec coll a b) (subvec coll b)]
         [(subvec coll a) []]))
     (partition-all 2 1 (range 0 (count coll) n)))
    (list [[] []])))

(defn vec-bites-indexed
  "Internal function used by compiled :star nodes. Like
  vec-bites-indexed but includes the index of each sequence member as
  a third element."
  [n coll]
  (if (seq coll)
    (map-indexed
     (fn [i [a b]]
       (if b
         [(subvec coll a b) (subvec coll b) i]
         [(subvec coll a) [] i]))
     (partition-all 2 1 (range 0 (count coll) n)))
    (list [[] [] 0])))

(defn js-array-bites
 "Internal function used by compiled :star nodes. Like seq-bites but
  specifically designed for JavaScript Array."
  [n coll]
  #?(:cljs
     (if (seq coll)
       (map
        (fn [[a b]]
          (if b
            [(.slice coll a b) (.slice coll b)]
            [(.slice coll a) #js []]))
        (partition-all 2 1 (range 0 (.-length coll) n)))
       (list [#js [] #js []]))))

(defn js-array-bites-indexed
 "Internal function used by compiled :star nodes. Like seq-bites but
  specifically designed for JavaScript Array."
  [n coll]
  #?(:cljs
     (if (seq coll)
       (map-indexed
        (fn [i [a b]]
          (if b
            [(.slice coll a b) (.slice coll b) i]
            [(.slice coll a) #js [] i]))
        (partition-all 2 1 (range 0 (.-length coll) n)))
       (list [#js [] #js []]))))

(defn js-array-equals-form
  "Form used to test if two arrays a and b are equal in
  ClojureScript."
  [a b]
  `(goog.array/equals ~a ~b
                      (fn f# [a# b#]
                        (if (cljs.core/array? a#)
                          (goog.array/equals a# b# f#)
                          (= a# b#)))))

(defn take-form [n target-form kind]
  (case kind
    :js-array
    `(.slice ~target-form 0 (min (.-length ~target-form) ~n))

    :vector
    `(subvec ~target-form 0 (min (count ~target-form) ~n))

    ;; else
    `(take ~n ~target-form)))

(defn drop-form [n target-form kind]
  (case kind
    :js-array
    `(.slice ~target-form ~n)

    :vector
    `(subvec ~target-form ~n)

    :seq
    `(drop ~n ~target-form)))

(defn dt-fail?
  {:private true}
  [dt]
  (= (:op dt) :fail))

(defn dt-check?
  [dt]
  (case (:op dt)
    (:check
     :check-array
     :check-array-equals
     :check-boolean
     :check-bounds
     :check-empty
     :check-equal
     :check-map
     :check-seq
     :check-set
     :check-vector
     :lvr-check)
    true

    ;; else
    false))

(defmulti compile*
  (fn [dt fail kind]
    (:op dt)))

(defmethod compile* :bind
  [dt fail kind]
  (loop [bindings []
         dt dt]
    (if (= (:op dt) :bind)
      (recur (conj bindings (:symbol dt) (compile* (:value dt) fail kind))
             (:then dt))
      `(let ~bindings
         ~(compile* dt fail kind)))))

(defmethod compile* :branch
  [dt fail kind]
  (case kind
    :search
    (let [arms (remove dt-fail? (:arms dt))]
      (case (count arms)
        0
        fail

        1
        (compile* (first arms) fail kind)

        ;; else
        `(concat
          ~@(map
             (fn [dt]
               (compile* dt fail kind))
             arms))))

    (:find :match)
    (let [arms (:arms dt)
          arms (if (= kind :find)
                 (remove dt-fail? arms)
                 arms)]
      (case (count arms)
        0
        fail

        1
        (compile* (first arms) fail kind)

        2
        (compile* (first arms)
               (compile* (second arms) fail kind)
               kind)

        ;; else
        (reduce
         (fn [fail arm]
           (compile* arm fail kind))
         fail
         (reverse arms))))))

(defmethod compile* :call
  [dt fail kind]
  `(let [x# (~(:symbol dt) ~(compile* (:target dt) fail kind) ~@(:req-syms dt))]
     (if (identical? x# FAIL)
       ~fail
       (let [[~@(:ret-syms dt)] x#]
         ~(compile* (:then dt) fail kind)))))

(defmethod compile* :check-array
  [dt fail kind]
  `(if (cljs.core/array? ~(compile* (:target dt) fail kind))
     ~(compile* (:then dt) fail kind)
     ~fail))

(defmethod compile* :check-array-equals
  [dt fail kind]
  `(if ~(js-array-equals-form
         (compile* (:target-1 dt) fail kind)
         (compile* (:target-2 dt) fail kind))
     ~(compile* (:then dt) fail kind)
     ~fail))

(defmethod compile* :check-boolean
  [dt fail kind]
  `(if ~(compile* (:test dt) fail kind)
     ~(compile* (:then dt) fail kind)
     ~fail))

(defmethod compile* :check-bounds
  [dt fail kind]
  (let [length (:length dt)
        target (compile* (:target dt) fail kind)
        test (case (:kind dt)
               :js-array
               `(= (.-length ~target) ~length)

               (:map :set)
               `(<= ~length (count ~target))

               :seq
               `(= (bounded-count (inc ~length) ~target)
                   ~length)

               :vector
               `(= (count ~target) ~length))]
    `(if ~test
       ~(compile* (:then dt) fail kind)
       ~fail)))

(defmethod compile* :check-equal
  [dt fail kind]
  `(if (= ~(compile* (:target-1 dt) fail kind)
          ~(compile* (:target-2 dt) fail kind))
     ~(compile* (:then dt) fail kind)
     ~fail))

(defmethod compile* :check-empty
  [dt fail kind]
  `(if (not (seq ~(compile* (:target dt) fail kind)))
     ~(compile* (:then dt) fail kind)
     ~fail))

(defmethod compile* :check-lit
  [dt fail kind]
  `(if (= ~(compile* (:target dt) fail kind)
          ~(compile* (:value dt) fail kind))
     ~(compile* (:then dt) fail kind)
     ~fail))

(defmethod compile* :check-map
  [dt fail kind]
  `(if (map? ~(compile* (:target dt) fail kind))
     ~(compile* (:then dt) fail kind)
     ~fail))

(defmethod compile* :check-seq
  [dt fail kind]
  `(if (seq? ~(compile* (:target dt) fail kind))
     ~(compile* (:then dt) fail kind)
     ~fail))

(defmethod compile* :check-set
  [dt fail kind]
  `(if (set? ~(compile* (:target dt) fail kind))
     ~(compile* (:then dt) fail kind)
     ~fail))

(defmethod compile* :check-vector
  [dt fail kind]
  `(if (vector? ~(compile* (:target dt) fail kind))
     ~(compile* (:then dt) fail kind)
     ~fail))

(defmethod compile* :drop
  [dt fail kind]
  (case (:kind dt)
    :js-array
    `(.slice ~(compile* (:target dt) fail kind)
             ~(:n dt))

    :vector
    `(subvec ~(compile* (:target dt) fail kind)
             ~(:n dt))

    :seq
    `(drop ~(:n dt)
           ~(compile* (:target dt) fail kind))))

(defmethod compile* :def
  [dt fail kind]
  (loop [bindings []
         dt dt]
    (if (= (:op dt) :def)
      (recur (conj bindings
                   `(~(:symbol dt) [~(:target-arg dt) ~@(:req-syms dt)]
                     ~(compile* (:body dt) `FAIL kind)))
             (:then dt))
      `(letfn ~bindings
         ~(compile* dt fail kind)))))

(defmethod compile* :eval
  [dt fail kind]
  (:form dt))

(defmethod compile* :fail
  [dt fail kind]
  fail)

(defmethod compile* :find
  [dt fail kind]
  (let [result-sym (gensym "result__")
        fail-sym (gensym "fail__")]
    `(let [~result-sym (reduce
                        (fn [~fail-sym ~(:symbol dt)]
                          (let [~result-sym ~(compile* (:body dt) `FAIL kind)]
                            (if (identical? ~result-sym ~fail-sym)
                              ~fail-sym
                              (reduced ~result-sym))))
                        FAIL
                        ~(compile* (:value dt) fail kind))]
       (if (identical? ~result-sym FAIL)
         ~fail
         ~result-sym))))

(defmethod compile* :load
  [dt fail kind]
  `(~(:id dt)))

(defmethod compile* :lvr-bind
  [dt fail kind]
  `(let [~(:symbol dt) ~(compile* (:target dt) fail kind)]
     ~(compile* (:then dt) fail kind)))

(defmethod compile* :lvr-check
  [dt fail kind]
  `(if (= ~(:symbol dt) ~(compile* (:target dt) fail kind))
     ~(compile* (:then dt) fail kind)
     ~fail))

(defmethod compile* :nth
  [dt fail kind]
  `(nth ~(compile* (:target dt) fail kind) ~(:index dt)))

(defmethod compile* :mvr-append
  [dt fail kind]
  (let [!symbol (:symbol dt)]
    `(let [~!symbol (conj ~!symbol ~(compile* (:target dt) fail kind))]
       ~(compile* (:then dt) fail kind))))

(defmethod compile* :mvr-init
  [dt fail kind]
  `(let [~(:symbol dt) []]
     ~(compile* (:then dt) fail kind)))

(defmethod compile* :pass
  [dt fail kind]
  (compile* (:then dt) fail kind))

(defmethod compile* :plus
  [dt fail kind]
  (let [input-sym (:input-symbol dt)
        return-syms (:return-symbols dt)
        minimum (:m dt)
        then-sym (gensym "then__")
        then-form (compile* (:then dt) fail kind)]
    `(let [~input-sym ~(compile* (:input dt) fail kind)
           ~then-sym (fn [~return-syms] ~then-form)]
       (reduce
        (fn [~return-syms [~input-sym tail# i#]]
          (if (= (count ~input-sym) ~(:n dt))
            (let [result# ~(compile* (:body dt) `FAIL (case kind
                                                     :search :find
                                                     kind))]
              (if (identical? result# FAIL)
                (reduced ~fail)
                (if (seq tail#)
                  result#
                  ;; Because we've successfully consumed and i will
                  ;; increment at the top of the next iteration, we
                  ;; need to use inc to check if we met the minimum.
                  (if (<= ~minimum (inc i#))
                    (reduced (~then-sym result#))
                    ~fail))))
            ;; Failed to consume
            (reduced
             (if (or (seq ~input-sym)
                     (seq tail#)
                     (< i# ~minimum))
               ~fail
               (~then-sym ~return-syms)))))
        ~(:return-symbols dt)
        ~(case (:kind dt)
           :js-array
           `(js-array-bites-indexed ~(:n dt) ~input-sym)

           :seq
           `(seq-bites-indexed ~(:n dt) ~input-sym)

           :vector
           `(vec-bites-indexed ~(:n dt) ~input-sym))))))

(defmethod compile* :save
  [dt fail kind]
  (let [id (:id dt)
        body-1 (:body-1 dt)
        body-2 (:body-2 dt)]
    (if (and (= (:op body-2) :load)
             (= (:id body-2 id)))
      `(letfn [(~id [] ~fail)]
         ~(compile* body-1 `(~id) kind))
      (let [f-sym (gensym "f__")]
        `(letfn [(~id [] ~fail)
                 (~f-sym [] ~(compile* body-2 fail kind))]
           ~(compile* body-1 `(~f-sym) kind))))))

(defmethod compile* :search
  [dt fail kind]
  (case kind
    (:find :match)
    (compile* (assoc dt :op :find) fail kind)

    :search
    `(mapcat
      (fn [~(:symbol dt)]
        ~(compile* (:body dt) fail kind))
      ~(compile* (:value dt) fail kind))))

(defmethod compile* :star
  [dt fail kind]
  (let [coll-sym (gensym "coll__")
        input-sym (:input-symbol dt)
        return-syms (:return-symbols dt)
        n (:n dt)
        then-sym (gensym "then__")
        body-form (compile* (:body dt) `FAIL (case kind :search :find kind))
        then-form (compile* (:then dt) fail kind)]
    `(loop [~coll-sym ~(compile* (:input dt) fail kind)
            ~return-syms ~(:return-symbols dt)]
      (let [~input-sym ~(take-form n coll-sym (:kind dt))]
        (if (= (count ~input-sym) ~n)
          (let [result# ~body-form]
            (if (identical? result# FAIL)
              ~fail
              (recur ~(drop-form n coll-sym (:kind dt)) result#)))
          ;; Failed to consume
          (if (seq ~coll-sym)
            ~fail
            ~then-form))))))

(defmethod compile* :take
  [dt fail kind]
  (let [target-code (compile* (:target dt) fail kind)
        n (:n dt)]
    (case (:kind dt)
      :vector
      `(subvec ~target-code 0 (min (count ~target-code) ~n))

      :js-array
      `(.slice ~target-code 0 (min (.-length ~target-code) ~n))

      ;; else
      `(take ~n ~target-code))))


(defmethod compile* :default
  [dt fail kind]
  dt)

;; ---------------------------------------------------------------------
;; Tree rewriting

;; :def rewriting

(defn def-remove-unused [dt]
  (let [call-symbols (into #{}
                           (comp (filter (comp #{:call} :op))
                                 (map :symbol))
                           (nodes dt))]
    (loop [loc (dt-zip dt)]
      (if (zip/end? loc)
        (zip/root loc)
        (let [node (zip/node loc)]
          (recur
           (case (:op node)
             :def
             (if (contains? call-symbols (:symbol node))
               (zip/next loc)
               (zip/replace loc (:then node)))

             ;; else
             (zip/next loc))))))))

(defn rewrite-def [dt]
  (-> dt
      def-remove-unused))

;; :mvr rewriting

(defn rewrite-move-mvr-init-to-top-level [dt]
  (reduce
   (fn [_ loc]
     (let [node (zip/node loc)]
       (case (:op node)
         :mvr-init
         (let [dt* (zip/root (zip/edit loc :then))]
           (reduced (assoc node :then (rewrite-move-mvr-init-to-top-level dt*))))
         ;; else
         dt)))
   dt
   (r.util/zip-next-seq (dt-zip dt))))

;; :branch rewriting

(defn branch-flatten
  "Equivalent to the rewrite rule

    (rewrite
     (with [%arms [(or {:op :branch
                        :arms %arms}
                       {:op :fail}
                       !arms)
                   ...]]
       {:op :branch
        :arms %arms})
     ;; =>
     {:op :branch
      :arms [!arms ... {:op :fail}]})"
  {:private true}
  [dt]
  (case (:op dt)
    :branch
    (let [arms* (into [] (mapcat
                          (fn f [node]
                            (if (= (:op node) :branch)
                              (mapcat f (:arms node))
                              (list node))))
                      (:arms dt))
          arms* (if (some dt-fail? arms*)
                  (conj (into [] (remove dt-fail?) arms*)
                        (op-fail))
                  arms*)]
      (assoc dt :arms arms*))
    dt))

(defmulti branch-merge-checks*
  (fn [[a b]]
    (let [op-a (:op a)
          op-b (:op b)]
      (if (= op-a op-b)
        op-a
        ::default)))
  :default ::default)

(defmethod branch-merge-checks* ::default
  [[a b]]
  [a b])

(defmethod branch-merge-checks* :bind
  [[a b]]
  (if (= (:target a)
         (:target b))
    [(assoc a
            :then (op-bind (:symbol b) (op-eval (:symbol a))
                    (op-branch [(:then a)
                                (:then b)])))]
    [a b]))

(defmethod branch-merge-checks* :check-bounds
  [[a b]]
  (if (and (= (:target a)
              (:target b))
           (= (:length a)
              (:length b))
           (= (:kind a)
              (:kind b)))
    [(assoc a
            :then (op-branch [(:then a)
                              (:then b)]))]
    [a b]))

(defmethod branch-merge-checks* :check-array
  [[a b]]
  (if (= (:target a)
         (:target b))
    [(assoc a
            :then (op-branch [(:then a)
                              (:then b)]))]
    [a b]))

(defmethod branch-merge-checks* :check-map
  [[a b]]
  (if (= (:target a)
         (:target b))
    [(assoc a
            :then (op-branch [(:then a)
                              (:then b)]))]
    [a b]))

(defmethod branch-merge-checks* :check-lit
  [[a b]]
  (if (= (:target a)
         (:target b))
    [(assoc a
            :then (op-branch [(:then a)
                              (:then b)]))]
    [a b]))

(defmethod branch-merge-checks* :check-seq
  [[a b]]
  (if (= (:target a)
         (:target b))
    [(assoc a
            :then (op-branch [(:then a)
                              (:then b)]))]
    [a b]))

(defmethod branch-merge-checks* :check-vector
  [[a b]]
  (if (= (:target a)
         (:target b))
    [(assoc a
            :then (op-branch [(:then a)
                              (:then b)]))]
    [a b]))

(defn branch-merge-checks
  [dt]
  (case (:op dt)
    :branch
    (loop [arms (:arms dt)
           arms* []]
      (case (count arms)
        (0 1)
        (assoc dt :arms (into arms* arms))
        ;; else
        (let [[a b] (take 2 arms)
              [a* b*] (branch-merge-checks* [a b])]
          (if (some? b*)
            ;; Couldn't merge, consume a.
            (recur (drop 1 arms) (conj arms* a))
            ;; Could merge, drop b, push a*.
            (recur (cons a* (drop 2 arms)) arms*)))))
    ;; else
    dt))


(defn branch-one-arm
  [dt]
  (if (= (:op dt) :branch)
    (if (= (count (:arms dt)) 1)
      (recur (first (:arms dt)))
      dt)
    dt))

(defn rewrite-branch* [dt]
  (-> dt
      branch-flatten
      branch-merge-checks
      branch-one-arm))

(defn rewrite-branch [dt]
  (let [dt* (loop [loc (dt-zip dt)]
              (if (zip/end? loc)
                (zip/root loc)
                (let [node (zip/node loc)]
                  (case (:op node)
                    :branch
                    (let [arms (:arms node)]
                      (case (count arms)
                        0
                        (recur (zip/next loc))

                        1
                        (recur (zip/replace loc (first arms)))

                        ;; else
                        (recur (zip/next (zip/edit loc rewrite-branch*)))))

                    ;; else
                    (recur (zip/next loc))))))]
    (if (= dt dt*)
      dt
      (recur dt*))))

(defn rewrite-save
  "Remove useless save nodes from dt."
  [dt]
  (let [dt* (loop [loc (dt-zip dt)]
              (if (zip/end? loc)
                (zip/root loc)
                (let [node (zip/node loc)]
                  (case (:op node)
                    :save
                    (let [body-1 (:body-1 node)]
                      (if (some dt-check? (nodes body-1))
                        (recur (zip/next loc))
                        (recur (zip/replace loc body-1))))

                    ;; else
                    (recur (zip/next loc))))))]
    (if (= dt dt*)
      dt
      (recur dt*))))

(defn rewrite [dt]
  (-> dt
      rewrite-def
      rewrite-branch
      rewrite-save))

(defn compile [dt fail kind]
  (compile* (rewrite dt) fail kind))
