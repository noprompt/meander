(ns meander.runtime.zeta
  (:refer-clojure :exclude [drop])
  (:require [clojure.core :as clj]))

;; Protocols
;; ---------------------------------------------------------------------

(defprotocol IBindVariable
  (-bind-variable [variable value bindings]))

(extend-protocol IBindVariable
  Object
  (-bind-variable [object value bindings]
    (assoc bindings object value)))

(defprotocol IDrop
  (-drop [this n]))

(defprotocol IGenerate
  (-generate [generator bindings]))

(defprotocol IHead
  (-head [coll]))

(defmacro head [coll]
  `(-head ~coll))

(defprotocol IHeight
  (-height [tree]))

(extend-protocol IHeight
  Object
  (-bind-variable [tree]
    0))

(defprotocol IPartitions
  (-partitions [this]))

(defmacro partitions [coll]
  `(-partitions ~coll))

(defprotocol ISearch
  (-search [this target env]))

(defprotocol IStable
  (-stable [generator]))

(extend-protocol IStable
  Object
  (-stable [generator]
    false))

(defmacro stable? [x]
  `(-stable ~x))

(defprotocol IStrategyInvokeIn
  (-strategy-invoke-in [this arg env]))

(defmacro strategy-invoke-in [f arg env]
  `(-strategy-invoke-in ~f ~arg ~env))

(defprotocol ITail
  (-tail [coll]))

(defmacro tail [coll]
  `(-tail ~coll))

(defprotocol ITake
  (-take [coll n]))

(defprotocol ISplitAt
  (-split-at [coll n]))

;; Implementation
;; ---------------------------------------------------------------------

(defmacro succeed [x]
  `(list ~x))

(def FAIL
  #?(:clj
     (proxy [java.lang.Throwable
             clojure.lang.Seqable] []
       (seq [] nil))

     :cljs
     (reify
       IGenerate
       (-generate [this env]
         [this env this])

       IHeight
       (-height [this]
         0)

       ISearch
       (-search [this target env]
         this)

       ISeqable
       (-seq [_] nil)


       IStrategyInvokeIn
       (-strategy-invoke-in [this arg env]
         this))))

#?(:clj
   (extend-type (class FAIL)
     IGenerate
     (-generate [this env]
       [this env this])

     IHeight
     (-height [this]
       0)

     ISearch
     (-search [this target env]
       this)

     IStable
     (-stable [this]
       true)

     IStrategyInvokeIn
     (-strategy-invoke-in [this arg env]
       this)))

(defmethod print-method (class FAIL) [g writer]
  (.write writer "#meander.runtime.zeta/fail[]"))

(defmacro fail []
  `FAIL)

(defmacro fail? [x]
  `(identical? ~x FAIL))

(defmacro if-result
  "Like if-some but tests the result of `expr` with `fail?`."
  {:style/indent 1}
  ([[binding expr] then]
   `(let [result# ~expr]
      (if (fail? result#)
        result#
        (let [~binding result#]
          ~then))))
  ([[binding expr] then else]
   `(let [result# ~expr]
      (if (fail? result#)
        ~else
        (let [~binding result#]
          ~then)))))

(defn bind-variable [bindings variable value]
  {:post [(or (map? %) (fail? %))]}
  (-bind-variable variable value bindings))

(defn merge-bindings [left-bindings right-bindings]
  (reduce
   (fn [merged-bindings pair]
     (let [variable (key pair)
           value (val pair)
           merged-bindings* (-bind-variable variable value merged-bindings)]
       (if (fail? merged-bindings*)
         (reduced merged-bindings*)
         merged-bindings*)))
   left-bindings
   right-bindings))

(defn combine-binding-streams
  [left-binding-stream right-binding-stream merge-bindings]
  (mapcat
   (fn [left-bindings]
     (keep
      (fn [right-bindings]
        (if-result [result (merge-bindings left-bindings right-bindings)]
          result
          nil))
      right-binding-stream))
   left-binding-stream))

;; Generator helpers
;; -----------------

(defmacro get-val [state]
  `(nth ~state 0))

(defmacro put-val [state val]
  `(assoc ~state 0 ~val))

(defmacro get-bindings [state]
  `(nth ~state 1))

(defmacro put-bindings [state env]
  `(assoc ~state 1 ~env))

(defmacro get-gen [state]
  `(nth ~state 2))

(defmacro put-gen [state gen]
  `(assoc ~state 2 ~gen))

(defmacro if-gen
  ([[binding gen env] then]
   `(~@&form [(fail) ~env (fail)]))
  ([[binding gen env] then else]
   `(let [~binding (-generate ~gen ~env)
          val# (get-val ~binding)]
      (if (fail? val#)
        ~else
        ~then))))

(defmacro bind-gen
  {:arglists '([env [binding gen & rest] return])
   :style/indent 2}
  [env bindings return]
  (if (seq bindings)
    (let [[binding gen & rest] bindings]
      `(if-gen [~binding ~gen ~env]
         (let [env# (get-bindings ~binding)]
           (bind-gen env# [~@rest]
             ~return))))
    return))

(defn run-gen
  ([gen env]
   (lazy-seq
    (let [state (-generate gen env)
          val (get-val state)]
      (if (fail? val)
        ()
        (let [gen* (get-gen state)]
          (cons val (run-gen gen* env)))))))
  ([gen env n]
   (take n (run-gen gen env))))

(defn source
  "Convert the sequence `xs` into a generator."
  [xs]
  (if (seq xs)
    (reify
      IGenerate
      (-generate [this env]
        (if (seq xs)
          (let [x (nth xs 0)
                xs* (rest xs)]
            [x env (source xs*)])
          [(fail) env (fail)]))

      ISearch
      (-search [this target env]
        (if (= target xs)
          (succeed env)
          (fail)))
      
      IStable
      (-stable [this] true))
    (fail)))

;; Const
;; -----

(deftype Const [value]
  clojure.lang.IFn
  (invoke [this _]
    value)

  IGenerate
  (-generate [this bindings]
    [value bindings (fail)])

  IHeight
  (-height [this]
    0)

  IStrategyInvokeIn
  (-strategy-invoke-in [this _ bindings]
    (succeed [value bindings]))

  ISearch
  (-search [this target bindings]
    (if (= target value)
      (succeed bindings)))

  IStable
  (-stable [this]
    true))

(defn const [x]
  (Const. x))

;; Wildcard
;; --------

(def wildcard
  (reify
    clojure.lang.IFn
    (invoke [this x]
      x)

    IGenerate
    (-generate [this bindings]
      [(Object.) bindings (fail)])

    IHeight
    (-height [this] 0)

    ISearch
    (-search [_ _ bindings]
      (succeed bindings))

    IStable
    (-stable [_]
      false)

    IStrategyInvokeIn
    (-strategy-invoke-in [this x bindings]
      (succeed [x bindings]))))


(deftype LogicVariable [symbol]
  Object
  (hashCode [this]
    (hash symbol))

  (equals [this that]
    (and (instance? LogicVariable that)
         (= symbol (.-symbol ^LogicVariable that))))

  clojure.lang.IHashEq
  (hasheq [this]
    (hash symbol))

  clojure.lang.IFn
  (invoke [this x]
    ;; This fails because there is no environment to consult.
    (fail))

  IBindVariable
  (-bind-variable [this value bindings]
    (if-some [entry (find bindings this)]
      (if (= (val entry) value)
        bindings
        (fail))
      (assoc bindings this value)))

  IStrategyInvokeIn
  (-strategy-invoke-in [this target bindings]
    (if-result [new-bindings (-bind-variable this target bindings)]
      (succeed [target bindings])))

  IGenerate
  (-generate [this bindings]
    (if-some [entry (find bindings this)]
      [(val entry) bindings (fail)]
      [(fail) bindings (fail)]))

  IHeight
  (-height [this]
    0)

  ISearch
  (-search [this target bindings]
    (if-result [new-bindings (-bind-variable this target bindings)]
      (succeed new-bindings)))

  IStable
  (-stable [this]
    true))

(defn logic-variable [symbol]
  (LogicVariable. symbol))

(defmethod print-method LogicVariable [^LogicVariable v writer]
  (.write writer "#meander.runtime.zeta/logic-variable ")
  (.write writer (str (.-symbol v))))

;; Memory Variable
;; ---------------

(deftype MemoryVariable [symbol]
  Object
  (hashCode [this]
    (hash symbol))

  (equals [this that]
    (and (instance? MemoryVariable that)
         (= symbol (.-symbol ^MemoryVariable that))))

  clojure.lang.IHashEq
  (hasheq [this]
    (hash symbol))

  clojure.lang.IFn
  (invoke [this x]
    ;; This fails because there is no environment to consult.
    (fail))

  IBindVariable
  (-bind-variable [this value bindings]
    ;; `value` is assumed to be a `vector?` or at least `sequential?`.
    (if-some [entry (find bindings this)]
      (assoc bindings this (into (val entry) value))
      (assoc bindings this value)))

  IGenerate
  (-generate [this bindings]
    (let [xs (get bindings this)]
      (if (seq xs)
        (let [x (nth xs 0)
              xs* (next xs)
              bindings* (assoc bindings this xs*)]
          [x bindings* (fail)])
        [(fail) bindings (fail)])))

  IHeight
  (-height [this]
    0)

  ISearch
  (-search [this target bindings]
    (succeed (-bind-variable this [target] bindings)))

  IStable
  (-stable [this]
    false))

(defn memory-variable [symbol]
  (MemoryVariable. symbol))

;; Fold Variable
;; -------------

(deftype FoldVariable [symbol initial-value fold-fn unfold-fn]
  Object
  (hashCode [this]
    (hash symbol))

  (equals [this that]
    (and (instance? FoldVariable that)
         (= symbol (.-symbol ^FoldVariable that))))

  clojure.lang.IHashEq
  (hasheq [this]
    (hash symbol))

  clojure.lang.IFn
  (invoke [this x]
    ;; This fails because there is no environment to consult.
    (fail))

  IBindVariable
  (-bind-variable [this value bindings]
    (if-some [entry (find bindings this)]
      (assoc bindings this (fold-fn (val entry) value))
      (assoc bindings this (fold-fn initial-value value))))


  IGenerate
  (-generate [this bindings]
    (let [result (if-some [entry (find bindings this)]
                   (unfold-fn (val entry))
                   (unfold-fn initial-value))]
      (if (fail? result)
        [(fail) bindings (fail)]
        (let [val* (nth result 0)
              next (nth result 1)
              bindings* (-bind-variable this next bindings)]
          [val* bindings* (fail)]))))

  IHeight
  (-height [this]
    0)

  ISearch
  (-search [this target bindings]
    (succeed (-bind-variable this target bindings)))

  IStable
  (-stable [this]
    false))

(defmulti unfold-for
  (fn [x]
    (if (fn? x)
      x
      ::not-fn)))

(defn fold-variable [symbol initial-value fold-fn]
  (let [unfold-fn (unfold-for fold-fn)]
    (FoldVariable. symbol initial-value fold-fn unfold-fn)))

(defmethod unfold-for clj/max [_]
  (fn [current]
    (let [return (inc current)]
      [return return])))

(defmethod unfold-for clj/min [_]
  (fn [current]
    (let [return (dec current)]
      [return return])))

(defmethod unfold-for clj/conj [_]
  (fn [current]
    (if (seq current)
      (let [return (peek current)
            next (pop current)]
        [return next])
      (fail))))

(defmethod unfold-for clj/cons [_]
  (fn [current]
    (if (seq current)
      (let [return (first current)
            next (rest current)]
        [return next])
      (fail))))

;; Call
;; ----

(deftype Call [f obj height stable]
  clojure.lang.IFn
  (invoke [this x]
    (f x))

  IGenerate
  (-generate [this bindings]
    (bind-gen bindings [state obj]
      (let [val (get-val state)
            val* (f val)
            bindings* (get-bindings state)]
        (if-result [obj* (get-gen state)]
          [val* bindings* (Call. f obj* height (stable? obj*))]
          [val* bindings* (fail)]))))

  IHeight
  (-height [this]
    height)

  IStrategyInvokeIn
  (-strategy-invoke-in [this x bindings]
    (let [x* (f x)]
      (if (fail? x*)
        x*
        (strategy-invoke-in obj x* bindings))))

  ISearch
  (-search [this target bindings]
    (-search obj (f target) bindings))

  IStable
  (-stable [this]
    stable))

(defn call
  ([f]
   (call f wildcard))
  ([f obj]
   (if (fail? obj)
     obj
     (Call. f obj (+ 1 (-height obj)) (stable? obj)))))

;; Choice
;; ------

(declare choice)

(deftype Choice [obj-a obj-b stable height]
  IGenerate
  (-generate [this bindings]
    (if-gen [state obj-a bindings]
      (let [obj-a* (get-gen state)
            state* (put-gen state (choice obj-b obj-a*))]
        state*)
      (if-gen [state obj-b bindings]
        (let [obj-b* (get-gen state)
              state* (put-gen state obj-b*)]
          state*))))

  IHeight
  (-height [this]
    height)

  ISearch
  (-search [this target bindings]
    (concat (-search obj-a target bindings)
            (-search obj-b target bindings)))

  IStable
  (-stable [this]
    stable)

  IStrategyInvokeIn
  (-strategy-invoke-in [this x bindings]
    (or (seq (concat (-strategy-invoke-in obj-a x bindings)
                     (-strategy-invoke-in obj-b x bindings)))
        (fail))))

(defn choice
  ([obj-a]
   obj-a)
  ([obj-a obj-b]
   (cond
     (fail? obj-a)
     obj-b

     (fail? obj-b)
     obj-a
     
     (= obj-a obj-b)
     obj-a

     :else
     (let [stable (and (stable? obj-a) (stable? obj-b))
           height (+ 1 (max (-height obj-a) (-height obj-b)))]
       (Choice. obj-a obj-b stable height))))
  ([obj-a obj-b & more]
   (choice obj-a (apply choice obj-b more))))

;; Pair
;; ----

(declare pair)

(deftype PairFirstThenSecond [obj-1 obj-2 stable height]
  IGenerate
  (-generate [this bindings]
    (bind-gen bindings [state-1 obj-1
                        state-2 obj-2]
      (let [val-1 (get-val state-1)
            val-2 (get-val state-2)
            obj-1* (get-gen state-1)
            obj-2* (get-gen state-2)
            bindings* (get-bindings state-2)
            val [val-1 val-2]
            seq1 (pair (if (stable? obj-1)
                         (const val-1)
                         obj-1)
                       obj-2*)
            seq2 (pair obj-1*
                       (if (stable? obj-2)
                         (const val-2)
                         obj-2))
            seq3 (pair obj-1* obj-2*)
            gen* (choice seq1 seq2 seq3)]
        [val bindings* gen*])))

  IHeight
  (-height [this]
    height)

  ISearch
  (-search [this target bindings]
    (if (and (sequential? target)
             (= 2 (bounded-count 3 target)))
      (let [x-1 (nth target 0)
            x-2 (nth target 1)]
        (combine-binding-streams
         (-search obj-1 x-1 bindings)
         (-search obj-2 x-2 bindings)
         merge-bindings))
      (fail)))

  IStable
  (-stable [this]
    stable)

  IStrategyInvokeIn
  (-strategy-invoke-in [this x bindings]
    (mapcat
     (fn [result-1]
       (let [x-1 (nth result-1 0)
             bindings-1 (nth result-1 1)]
         (mapcat
          (fn [result-2]
            (let [x-2 (nth result-2 0)
                  bindings-2 (nth result-2 1)]
              [[x-1 x-2] bindings-2]))
          (-strategy-invoke-in obj-2 x bindings))))
     (-strategy-invoke-in obj-1 x bindings))))

(deftype PairSecondThenFirst [obj-1 obj-2 stable height]
  IGenerate
  (-generate [this bindings]
    (bind-gen bindings [state-1 obj-1
                   state-2 obj-2]
      (let [val-1 (get-val state-1)
            val-2 (get-val state-2)
            obj-1* (get-gen state-1)
            obj-2* (get-gen state-2)
            bindings* (get-bindings state-2)
            val [val-1 val-2]
            seq1 (pair (if (stable? obj-1)
                         (const val-1)
                         obj-1)
                       obj-2*)
            seq2 (pair obj-1*
                       (if (stable? obj-2)
                         (const val-2)
                         obj-2))
            seq3 (pair obj-1* obj-2*)
            gen* (choice seq1 seq2 seq3)]
        [val bindings* gen*])))

  IHeight
  (-height [this]
    height)

  ISearch
  (-search [this target bindings]
    (if (and (sequential? target)
             (= 2 (bounded-count 3 target)))
      (let [x-1 (nth target 0)
            x-2 (nth target 1)]
        (combine-binding-streams
         (-search obj-2 x-2 bindings)
         (-search obj-1 x-1 bindings)
         (fn [right-bindings left-bindings]
           (merge-bindings left-bindings right-bindings))))
      (fail)))

  IStable
  (-stable [this]
    stable)

  IStrategyInvokeIn
  (-strategy-invoke-in [this x bindings]
    (mapcat
     (fn [result-1]
       (let [x-1 (nth result-1 0)
             bindings-1 (nth result-1 1)]
         (mapcat
          (fn [result-2]
            (let [x-2 (nth result-2 0)
                  bindings-2 (nth result-2 1)]
              [[x-1 x-2] bindings-2]))
          (-strategy-invoke-in obj-2 x bindings))))
     (-strategy-invoke-in obj-1 x bindings))))

(defn pair
  [obj-1 obj-2]
  (if (or (fail? obj-1) (fail? obj-2))
    (fail)
    (let [stable (and (stable? obj-1) (stable? obj-2))
          height (+ 1 (max (-height obj-1) (-height obj-2)))]
      (if (<= (-height obj-1) (-height obj-2))
        (PairFirstThenSecond. obj-1 obj-2 stable height)
        (PairSecondThenFirst. obj-1 obj-2 stable height)))))

(defn cons-gen [gen-car gen-cdr]
  (call (fn [xs]
          (cons (nth xs 0) (nth xs 1)))
        (pair gen-car gen-cdr)))

(defn join [obj-1 obj-2]
  (call (fn [xs]
          (concat (nth xs 0) (nth xs 1)))
        (pair obj-1 obj-2)))

;; Cata
;; ----

(deftype Cata  [f obj stable height]
  IGenerate
  (-generate [this env]
    (bind-gen env [state obj]
      (let [val (f (get-val state))]
        (if (seq val)
          (let [val* (nth val 0)
                env* (get-bindings state)
                obj* (get-gen state)
                gen* (choice (source (next val))
                             (Cata. f obj* (stable? obj*) height))]
            [val* env* gen*])
          [(fail) env (fail)]))))

  IHeight
  (-height [this]
    height)

  ISearch
  (-search [this target env]
    (mapcat
     (fn [x]
       (-search obj x env))
     (f target)))

  IStable
  (-stable [this] stable))

;; TODO: Compute the `height` of the cata if possible.
(defn cata
  {:style/indent 1}
  ([f obj]
   ;; The default `height` is infinite because the `height` of the
   ;; cata function `f` unknown.
   (cata f obj Float/POSITIVE_INFINITY))
  ([f obj height]
   (if (fail? obj)
     (fail)
     (let [stable (stable? obj)]
       (Cata. f obj stable height)))))

(defn random-symbol [symbol]
  (reify
    IGenerate
    (-generate [this env]
      (if-some [entry (find env symbol)]
        [(val entry) env (fail)]
        (let [val (gensym)
              env* (assoc env symbol val)]
          [val env* (fail)])))

    ISearch
    (-search [this target env]
      (if-some [entry (find env symbol)]
        (let [value (val entry)]
          (if (= target value)
            (succeed env)
            (fail)))
        (let [value (gensym)]
          (if (= target value)
            (let [env* (assoc env symbol value)]
              (succeed env*))
            (fail)))))

    IStable
    (-stable [this]
      false)))

;; Star
;; ----

(defn star* [obj]
  (if (fail? obj)
    (fail)
    (let [stable (stable? obj)
          height (-height obj)]
      (reify
        IGenerate
        (-generate [this env]
          (if-gen [state obj env]
            (let [vals (get-val state)
                  env* (get-bindings state)
                  obj* (get-gen state)
                  obj* (choice
                        (call (fn [xs]
                                (concat (nth xs 0) (nth xs 1)))
                              (pair obj this))
                        (star* obj*))]
              [vals env* obj*])))

        IHeight
        (-height [this]
          height)

        IStable
        (-stable [this]
          stable)))))

(defn star
  ([obj]
   (choice (const ()) (star* obj))))

(defn plus
  ([obj n]
   (if (<= n 0)
     (star obj)
     (call (fn [[a b]]
             (concat a b))
           (pair obj (plus obj (dec n)))))))

(defn string [obj]
  (reify
    ISearch
    (-search [this target bindings]
      (if (string? target)
        (-search obj target bindings)
        (fail)))))

;; (defn drain [symbol]
;;   (reify
;;     IGenerate
;;     (-generate [this env]
;;       (let [val (get env symbol)
;;             env* (assoc env symbol ())
;;             gen* (fail)]
;;         [val env* gen*]))

;;     IHeight
;;     (-height [this]
;;       0)

;;     ISearch
;;     (-search [this target env]
;;       (if (sequential? target)
;;         (into-memory-variable [env symbol target]
;;           (succeed env))
;;         (fail)))

;;     IStable
;;     (-stable [this]
;;       false)))


;; Helpers
;; ---------------------------------------------------------------------

(defn iterator [coll]
  #?(:clj (clojure.lang.RT/iter coll)
     :cljs (iter coll)))

(defn knit
  [colls]
  (case (bounded-count 2 colls)
    0 ()

    1 (if (seq (first colls))
        (first colls)
        ())

    ;; else
    (concat (sequence (comp (keep seq)
                            (map first))
                      colls)
            (lazy-seq (knit (keep next colls))))))

;; Temporary. Remove this when zeta can bootstrap without epsilon.
(defn epsilon-run-star-1
  {:style/indent :defn}
  [coll rets body-f then-f]
  (let [rets* (reduce
               (fn [acc xs]
                 (let [acc (body-f acc [xs])]
                   (if (fail? acc)
                     (reduced FAIL)
                     acc)))
               rets
               coll)]
    (if (fail? rets*)
      FAIL
      (then-f rets*))))

(defn run-star [state input f g]
  (let [partitions (partitions input)]
    (concat
     (mapcat
      (fn [partition]
        (let [left (nth partition 0)
              right (nth partition 1)]
          (mapcat
           (fn [state]
             (run-star state right f g))
           (f state left))))
      partitions)
     (g state input))))

(defn run-plus [state n input f g]
  (if (<= n 0)
    (run-star state input f g)
    (let [partitions (partitions input)
          n* (dec n)]
      (mapcat
       (fn [partition]
         (let [left (nth partition 0)
               right (nth partition 1)]
           (mapcat
            (fn [state]
              (run-plus state n* right f g))
            (f state left))))
       partitions))))

(defn second-argument [_ x] x)

(defmethod unfold-for second-argument [_]
  (fn [current]
    [current current]))

;; Code generation helpers
;; -----------------------

(defmacro bind-logic-variable
  {:style/indent 1}
  [[smap v x] body-expression]
  `(let* [entry# (find ~smap ~v)]
     (if entry#
       (let* [y# (val entry#)]
         (if (= ~x y#)
           ~body-expression
           (fail)))   
       (let* [~smap (assoc ~smap ~v ~x)]
         ~body-expression))))

(defmacro bind-memory-variable
  {:style/indent 1}
  [[smap v x] body-expression]
  `(let [~smap (if-some [entry# (find ~smap ~v)]
                 (let [old-val# (val entry#)
                       new-val# (conj old-val# ~x)]
                   (assoc ~smap ~v new-val#))
                 (assoc ~smap ~v [~x]))]
     ~body-expression))

(defmacro into-memory-variable
  {:style/indent 1}
  [[smap v x] body-expression]
  `(let* [entry# (find ~smap ~v)
          ~smap (if entry#
                  (let* [value# (val entry#)
                         value# (into value# ~x)]
                    (assoc ~smap ~v value#))
                  (let* [value# (vec ~x)]
                    (assoc ~smap ~v value#)))]
     ~body-expression))

(defmacro bind-mutable-variable
  {:style/indent 1}
  [[smap symbol target] body-expression]
  `(let [~smap (assoc ~smap ~symbol ~target)]
     ~body-expression))

;; Extensions
;; ---------------------------------------------------------------------

(extend-type clojure.lang.IFn
  IStrategyInvokeIn
  (-strategy-invoke-in [this arg env]
    (if-result [result (this arg)]
      (succeed [(this arg) env]))))

(extend-type String
  ITake
  (-take [this n]
    (let [c (count this)]
      (if (<= n c)
        (subs this 0 n)
        (fail))))

  IDrop
  (-drop [this n]
    (let [c (count this)]
      (if (<= n c)
        (subs this n)
        (fail))))

  IHead
  (-head [this]
    (if (= this "")
      (fail)
      (nth this 0)))

  IPartitions
  (-partitions [this]
    (sequence
     (map
      (fn [i]
        [(subs this 0 i) (subs this i)]))
     (range (inc (count this)))))

  
  ISplitAt
  (-split-at [this n]
    (let [c (count this)]
      (if (<= n c)
        [(subs this 0 n)
         (subs this n)]
        (fail))))

  ITail
  (-tail [this]
    (if (= this "")
      this
      (subs this 1))))


(extend-type clojure.lang.IPersistentVector
  ITake
  (-take [this n]
    (let [c (count this)]
      (if (<= n c)
        (subvec this 0 n)
        (fail))))

  IDrop
  (-drop [this n]
    (let [c (count this)]
      (if (<= n c)
        (subvec this n)
        (fail))))

  IHead
  (-head [this]
    (if (identical? this clojure.lang.PersistentVector/EMPTY)
      (fail)
      (nth this 0)))

  IPartitions
  (-partitions [this]
    (sequence
     (map
      (fn [i]
        [(subvec this 0 i) (subvec this i)]))
     (range (inc (count this)))))

  
  ISplitAt
  (-split-at [this n]
    (let [c (count this)]
      (if (<= n c)
        [(subvec this 0 n)
         (subvec this n)]
        (fail))))

  ITail
  (-tail [this]
    (if (identical? this clojure.lang.PersistentVector/EMPTY)
      this
      (subvec this 1))))

(extend-type clojure.lang.ISeq
  IHead
  (-head [this]
    (if (seq this)
      (nth this 0)
      (fail)))

  IDrop
  (-drop [this n]
    (let [m (bounded-count (inc n) this)]
      (if (<= n m)
        (clojure.core/drop n this)
        (fail))))

  IPartitions
  (-partitions [this]
    (sequence
     (map-indexed
      (fn [i _]
        (split-at i this)))
     (cons 1 this)))

  ITake
  (-take [this n]
    (let [m (bounded-count (inc n) this)]
      (if (<= n m)
        (clojure.core/take n this)
        (fail))))

  ISplitAt
  (-split-at [this n]
    (if-result [left (-take this n)]
      (let [right (clojure.core/drop n this)]
        [left right])))
  
  ITail
  (-tail [this]
    (if (seq this)
      (rest this)
      this)))

(extend-type nil
  IHead
  (-head [this]
    (fail))

  IPartitions
  (-partitions [this]
    ())

  ISplitAt
  (-split-at [this n]
    (fail))

  ITail
  (-tail [this]
    nil))

;; (defn mutable-variable [symbol]
;;   (reify
;;     IGenerate
;;     (-generate [this env]
;;       (if-some [entry (find env symbol)]
;;         [(val entry) env (fail)]
;;         [(fail) env (fail)]))

;;     ISearch
;;     (-search [this target env]
;;       (bind-mutable-variable [env symbol target]
;;         (succeed env)))

;;     IStable
;;     (-stable [this]
;;       false)))

;; (defn letp
;;   {:style/indent 2}
;;   [binding-obj x body-obj]
;;   (reify
;;     ISearch
;;     (-search [this target env]
;;       (concat (mapcat
;;                (fn [env]
;;                  (-search body-obj target env))
;;                (-search binding-obj x env))
;;               (fail)))

;;     IGenerate
;;     (-generate [this env]
;;       (let [stream (source
;;                     (mapcat (fn [env] (run-gen body-obj env))
;;                             (-search binding-obj x env)))]
;;         (-generate stream env)))

;;     IStable
;;     (-stable [this]
;;       false)))

;; (defn state-stream [states]
;;   (reify
;;     IGenerate
;;     (-generate [this env]
;;       (if (seq states)
;;         (let [state (nth states 0)]
;;           [(get-val state)
;;            (get-bindings state)
;;            (choice (get-gen state)
;;                    (state-stream  (next states)))])
;;         [(fail) env (fail)]))

;;     IStable
;;     (-stable [this]
;;       true)))

;; (defn pipe
;;   ([obj1 obj2]
;;    (reify
;;      ;; Strategy
;;      clojure.lang.IFn
;;      (invoke [this x]
;;        (let [x* (obj1 x)]
;;          (if (fail? x*)
;;            x*
;;            (obj2 x*))))

;;      IGenerate
;;      (-generate [this env]
;;        (if-gen [state obj1 env]
;;          (let [x (get-val state)
;;                env* (get-bindings state)
;;                obj1* (get-gen state)
;;                this* (pipe obj1* obj2)]
;;            (-generate (state-stream
;;                        (map
;;                         (fn [result]
;;                           (let [x* (nth result 0)
;;                                 env* (nth result 1)]
;;                             [x* env* this*]))
;;                         (strategy-invoke-in obj2 x env)))
;;                       env))))

;;      ISearch
;;      (-search [this target env]
;;        (or (seq (map
;;                  (fn [result]
;;                    (let [env* (nth result 1)]
;;                      env*))
;;                  (strategy-invoke-in this target env)))
;;            (fail)))

;;      IStable
;;      (-stable [this]
;;        (and (stable? obj1) (stable? obj2)))

;;      IStrategyInvokeIn
;;      (-strategy-invoke-in [this x env]
;;        (mapcat
;;         (fn [result]
;;           (let [x* (nth result 0)
;;                 env* (nth result 1)]
;;             (strategy-invoke-in obj2 x* env*)))
;;         (strategy-invoke-in obj1 x env)))))
;;   ([obj1 obj2 & more]
;;    (pipe obj1 (clj/apply pipe obj2 more))))
