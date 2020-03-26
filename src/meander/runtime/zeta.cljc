(ns meander.runtime.zeta
  (:refer-clojure :exclude [cat drop])
  (:require [clojure.core :as clj]))

;; TODO: Allow a maximum depth to be configured for search, find, and
;;       generate.
;; TODO: Allow a maximum width to be configured for search, find, and
;;       generate.
;; TODO: IHeight with respect to env. This is needed for `Reference`.
;; TODO: Replace the word `state` to refer to the triple that is
;;       returned by `-generate` with more descriptive one.

;; Protocols
;; ---------------------------------------------------------------------

(defprotocol IAmbiguousPattern
  "Protocol indicating the object is an ambiguous pattern."
  (-ambiguous-pattern? [this]))

(defprotocol IConstantValue
  (-constant-value [pattern bindings]))

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

(defprotocol IVariable
  "Marker protocol indicating")

;; Implementation
;; ---------------------------------------------------------------------

(defmacro succeed
  "Returns code used to indicate pattern search/genearte success: a
  list of bindings returned by `bindings-expr` at rutime."
  [bindings-expr]
  `(list ~bindings-expr))

(def FAIL
  "Singleton used to indicate a pattern search/generate failure."
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

(defmacro fail
  "Code for the singleton value `FAIL`."
  [] `FAIL)

(defmacro fail?
  "Code to ask if runtime value of `expr` is `FAIL`."
  [expr]
  `(identical? ~expr FAIL))

(defmacro result-stream [seq-returning-expr]
  `(or (seq ~seq-returning-expr) (fail)))

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

(defn variable? [x]
  (satisfies? IVariable x))

(defn bind-variable [bindings variable value]
  {:pre [(variable? variable)]
   :post [(or (map? %) (fail? %))]}
  (-bind-variable variable value bindings))

(defn bound-variable? [bindings x]
  (and (variable? x) (contains? bindings x)))

(defn resolve-variable [bindings variable]
  {:pre [(variable? variable)]}
  (if-some [e (find bindings variable)]
    (val e)
    (fail)))

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

(defn ambiguous-pattern? [x]
  (and (satisfies? IAmbiguousPattern x)
       (-ambiguous-pattern? x)))

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

(defn gen-stream
  ([gen env]
   (lazy-seq
    (let [state (-generate gen env)
          val (get-val state)]
      (if (fail? val)
        ()
        (let [gen* (get-gen state)]
          (cons state (gen-stream gen* env)))))))
  ([gen env n]
   (take n (gen-stream gen env))))

(defn run-gen
  ([gen env]
   (lazy-seq
    (let [state (-generate gen env)
          val (get-val state)
          gen* (get-gen state)]
      (if (fail? val)
        (if (fail? gen*)
          ()
          (run-gen gen* env))
        (cons val (run-gen gen* env))))))
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

      IHeight
      (-height [this]
        1)

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

  IConstantValue
  (-constant-value [this bindings]
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

  IConstantValue
  (-constant-value [this bindings]
    (if-some [entry (find bindings this)]
      (val entry)
      (fail)))

  IGenerate
  (-generate [this bindings]
    (if-some [entry (find bindings this)]
      [(val entry) bindings (fail)]
      [(fail) bindings (fail)]))

  IHeight
  (-height [this]
    0)

  IVariable

  ISearch
  (-search [this target bindings]
    (if-result [new-bindings (-bind-variable this target bindings)]
      (succeed new-bindings)))

  IStable
  (-stable [this]
    true)

  IStrategyInvokeIn
  (-strategy-invoke-in [this target bindings]
    (if-result [new-bindings (-bind-variable this target bindings)]
      (succeed [target bindings]))))

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

  IVariable

  ISearch
  (-search [this target bindings]
    (succeed (-bind-variable this [target] bindings)))

  IStable
  (-stable [this]
    false))

(defmethod print-method MemoryVariable [^MemoryVariable v writer]
  (.write writer "#meander.runtime.zeta/memory-variable ")
  (.write writer (str (.-symbol v))))

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

  IVariable

  ISearch
  (-search [this target bindings]
    (succeed (-bind-variable this target bindings)))

  IStable
  (-stable [this]
    false))

(defmethod print-method FoldVariable [^FoldVariable v writer]
  (.write writer "#meander.runtime.zeta/fold-variable [")
  (.write writer (str (.-symbol v)))
  (.write writer " ")
  (.write writer (str (.-initial-value v)))
  (.write writer " ")
  (.write writer (str (.-fold-fn v)))
  (.write writer " ")
  (.write writer (str (.-unfold-fn v)))
  (.write writer "]"))

(defmulti unfold-for
  (fn [x]
    (if (fn? x)
      x
      ::not-fn)))

(defn fold-variable [symbol initial-value fold-fn]
  (let [unfold-fn (unfold-for fold-fn)]
    (FoldVariable. symbol initial-value fold-fn unfold-fn)))

(defmethod unfold-for clj/+ [_]
  (fn [current]
    (let [return (- current)]
      [return return])))

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

(deftype Call [f obj height stable ambiguous]
  clojure.lang.IFn
  (invoke [this x]
    (f x))

  IAmbiguousPattern
  (-ambiguous-pattern? [this]
    ambiguous)

  IGenerate
  (-generate [this bindings]
    (bind-gen bindings [state obj]
      (let [val (get-val state)
            val* (f val)
            bindings* (get-bindings state)]
        (if-result [obj* (get-gen state)]
          [val* bindings* (Call. f obj* height (stable? obj*) (ambiguous-pattern? obj*))]
          [val* bindings* (fail)]))))

  IHeight
  (-height [this]
    height)

  ISearch
  (-search [this target bindings]
    (-search obj (f target) bindings))

  IStable
  (-stable [this]
    stable)

  IStrategyInvokeIn
  (-strategy-invoke-in [this x bindings]
    (let [x* (f x)]
      (if (fail? x*)
        x*
        (strategy-invoke-in obj x* bindings)))))

(defn call
  ([f]
   (call f wildcard))
  ([f obj]
   (if (fail? obj)
     obj
     (Call. f obj (+ 1 (-height obj)) (stable? obj) (ambiguous-pattern? obj)))))

;; Choice
;; ------

(declare choice)

(deftype Choice [obj-a obj-b stable height]
  IAmbiguousPattern
  (-ambiguous-pattern? [this]
    true)

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

(defn andp [leftp rightp]
  (let [height (max (-height leftp) (-height rightp))]
    (reify
      IHeight
      (-height [this]
        height)

      ISearch
      (-search [this target bindings]
        (combine-binding-streams
         (-search leftp target bindings)
         (-search rightp target bindings)
         merge-bindings)))))

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

(defn drain [obj]
  (reify
    IGenerate
    (-generate [this env]
      ;; The goal here is to yield the "deepest" generation first,
      ;; then the previous one, etc. The idea is that we can use this
      ;; for zero or more patterns containing memory variables to
      ;; generate the deepest dispersal first for rewrite.
      ;;
      ;; 1000 is the maximum generator depth and is temporarily
      ;; hardcode for now.
      (-generate (source (reverse (run-gen obj env 1000))) env))

    IHeight
    (-height [this]
      0)

    ISearch
    (-search [this target env]
      (if (sequential? target)
        (succeed env)
        (fail)))

    IStable
    (-stable [this]
      true)))

(defn pred
  ([p]
   (reify
     ISearch
     (-search [this x env]
       (if (p x)
         (succeed env)
         (fail)))))
  ([p obj]
   (reify
     ISearch
     (-search [this x env]
       (if (p x)
         (-search obj x env)
         (fail))))))

(defn vector-p [obj]
  (let [height (+ 1 (-height obj))]
    (reify
      ISearch
      (-search [this x bindings]
        (if (vector? x)
          (-search obj x bindings)
          (fail)))

      IGenerate
      (-generate [this bindings]
        (if-result [gen-state (-generate obj bindings)]
          (put-val gen-state (into [] (get-val gen-state)))
          (fail)))

      IHeight
      (-height [this]
        height))))

(defn cat
  ([objs]
   {:pre [(sequential? objs)]}
   (cat objs wildcard))
  ([objs next-obj]
   {:pre [(sequential? objs)]}
   (let [n (count objs)
         g (case n
             0 next-obj
             1 (call (fn [[xs ys]]
                       (cons xs ys))
                     (pair (nth objs 0) next-obj))
             ;; else
             (reduce (fn [obj1 obj2]
                       (call (fn [[xs ys]]
                               (concat xs ys))
                             (pair obj1 obj2)))
                     (pair (nth objs 0) (nth objs 1))
                     (concat (clj/drop 2 objs)
                             (list next-obj))))
         height (reduce max 0 (map -height objs))]
     (reify
       IGenerate
       (-generate [this bindings]
         (-generate g bindings))

       IHeight
       (-height [this]
         height)

       ISearch
       (-search [this x bindings]
         (if-result [[init tail] (-split-at x n)]
           (sequence
            (comp (reduce comp
                          (map (fn [x obj]
                                 (mapcat (fn [bindings] (-search obj x bindings))))
                               init
                               objs))
                  (mapcat (fn [bindings] (-search next-obj tail bindings))))
            (list bindings))
           (fail)))))))

(deftype With [with-bindings obj]
  IGenerate
  (-generate [this bindings]
    (-generate obj (merge bindings with-bindings)))

  IHeight
  (-height [this]
    ;; TODO
    (-height obj))

  ISearch
  (-search [this target bindings]
    (let [old-references (get bindings ::references)
          new-references (merge old-references with-bindings)
          f (if (some? old-references)
              (fn [bindings*]
                (assoc bindings* ::references old-references))
              (fn [bindings*]
                (dissoc bindings* ::references)))]
      (map f (-search obj target (assoc bindings ::references new-references))))))

(defn with [with-bindings obj]
  (With. with-bindings obj))


(deftype Reference [symbol]
  Object
  (hashCode [this]
    (hash symbol))

  (equals [this that]
    (and (instance? Reference that)
         (= symbol (.-symbol ^Reference that))))

  clojure.lang.IHashEq
  (hasheq [this]
    (hash symbol))

  clojure.lang.IFn
  (invoke [this x]
    ;; This fails because there is no environment to consult.
    (fail))

  IGenerate
  (-generate [this bindings]
    (let [result (if-some [x (get-in bindings [::references this])]
                   x
                   (fail))]
      (if (fail? result)
        [(fail) bindings (fail)]
        (-generate result bindings))))

  IHeight
  (-height [this]
    0)

  ISearch
  (-search [this target bindings]
    (let [result (if-some [x (get-in bindings [::references this])]
                   x
                   (fail))]
      (if (fail? result)
        [(fail) bindings (fail)]
        (-search result target bindings))))

  IStable
  (-stable [this]
    false))

(defn reference [symbol]
  (Reference. symbol))

(defn greedy-star [obj next-obj]
  (reify
    IHeight
    (-height [this]
      (max (-height obj) (-height next-obj)))

    ISearch
    (-search [this target bindings]
      (reduce
       (fn [default partition]
         (let [left (nth partition 0)
               right (nth partition 1)]
           (if-some [stream (and (seq left)
                                 (seq (-search this bindings left)))]
             (mapcat
              (fn [bindings]
                (or (seq (-search this right bindings))
                    (seq (-search next-obj right bindings))))
              stream)
             default)))
       (fail)
       (partitions target)))))

(defn addp [np mp]
  (if (or (fail? np) (fail? mp))
    (fail)
    (let [height (max (-height np) (-height mp))]
      (reify
        IConstantValue
        (-constant-value [this bindings]
          (let [n (-constant-value np bindings)
                m (-constant-value mp bindings)]
            (if (and (number? n) (number? m))
              (+ n m)
              (fail))))

        IHeight
        (-height [this]
          height)

        ISearch
        (-search [this target bindings]
          (if (number? target)
            (let [n (-constant-value np bindings)
                  m (-constant-value mp bindings)]
              (case [(fail? n) (fail? m)]
                [false false]
                (if (and (number? n)
                         (number? m)
                         (= target (+ n m)))
                  (succeed bindings)
                  (fail))

                [false true]
                (if (number? n)
                  (-search mp (- target n) bindings)
                  (fail))

                [true false]
                (if (number? m)
                  (-search np (- target m) bindings)
                  (fail))

                ;; Search pairs of numbers which sum to the target.
                [true true]
                (mapcat
                 (fn [n m]
                   (combine-binding-streams
                    (-search np n bindings)
                    (-search mp m bindings)
                    merge-bindings))
                 ;; These need to be controlled by a max-length
                 (iterate inc 0)
                 (iterate dec target))))
            (fail)))

        IGenerate
        (-generate [this bindings]
          (if-gen [n-result np bindings]
            (let [n (get-val n-result)]
              (if (number? n)
                (if-gen [m-result mp (get-bindings n-result)]
                  (let [m (get-val m-result)
                        n-gen* (get-gen n-result)
                        m-gen* (get-gen m-result)
                        gen* (choice (addp n-gen* m-gen*)
                                     (addp np m-gen*)
                                     (addp n-gen* mp))]
                    (if (number? m)
                      [(+ n m) (get-bindings m-result) gen*]
                      [(fail) bindings gen*])))
                [(fail) bindings (addp (get-gen n-result) mp)]))))))))


(let [?x (logic-variable '?x)]
  (run-gen
   (addp (choice (const 4) (const 5))
         (choice (const 4) (const 5)))
   {?x "foo"}
   15))

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

(defn run-star [bindings input f g]
  (let [partitions (partitions input)]
    (concat
     (mapcat
      (fn [partition]
        (let [left (nth partition 0)
              right (nth partition 1)]
          (mapcat
           (fn [bindings]
             (run-star bindings right f g))
           (f bindings left))))
      partitions)
     (g bindings input))))

(defn run-greedy-star
  [bindings input f g]
  (reduce
   (fn [default partition]
     (let [left (nth partition 0)
           right (nth partition 1)]
       ;; If we can consume the left
       (if (seq left)
         (if-some [bindings (seq (mapcat
                                  (fn [bindings]
                                    (run-greedy-star bindings right f g))
                                  (f bindings left)))]
           (reduced bindings)
           default)
         default)))
   (g bindings input)
   (partitions input)))

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

(defn re-match-index-seq [re s]
  (let [m (re-matcher re s)]
    ((fn f []
       (lazy-seq
        (if (.find m)
          (cons (.start m) (f))))))))

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

(extend-protocol IConstantValue
  Object
  (-constant-value [this bindings]
    (fail)))

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

;; Epsilon runtime
;; ---------------

(defn vec-partitions
  "
  (let [coll [:a :b]
        n 3]
  (vec-partitions n coll))
  ;; => ([[] [] [:a :b]]
  ;;     [[] [:a] [:b]]
  ;;     [[:a] [] [:b]]
  ;;     [[] [:a :b] []]
  ;;     [[:a] [:b] []]
  ;;     [[:a :b] [] []])
  "
  {:private true}
  ([n v]
   {:pre [(nat-int? n)]}
   (case n
     0 (list [])
     1 (list [v])
     2 (sequence
        (map
         (fn [i]
           [(subvec v 0 i) (subvec v i)]))
        (range (inc (count v))))
     ;; else
     (sequence
      (comp (map-indexed
             (fn [i _]
               [(subvec v 0 i) (subvec v i)]))
            (mapcat
             (fn [[a b]]
               (sequence
                (map conj)
                (vec-partitions (dec n) a)
                (repeat b)))))
      (range (inc (count v))))))
  ([n m v]
   {:pre [(nat-int? n) (nat-int? m)]}
   (if (<= m (count v))
     (case n
       0 (list [])
       1 (list [v])
       2 (sequence
          (comp (take-while
                 (let [j (count v)]
                   (fn [i]
                     (<= (+ i m) j))))
                (map
                 (fn [i]
                   [(subvec v 0 (+ i m)) (subvec v (+ i m))])))
          (range (inc (count v))))
       ;; else
       (sequence
        (comp (take-while
               (let [j (count v)]
                 (fn [i]
                   (<= (+ i m) j))))
              (map
               (fn [i]
                 [(subvec v 0 (+ i m)) (subvec v (+ i m))]))
              (mapcat
               (fn [[a b]]
                 (sequence
                  (map conj)
                  (vec-partitions (dec n) m a)
                  (repeat b)))))
        (range (inc (count v)))))
     (list []))))


(defn coll-partitions
  {:private true}
  ([n coll]
   {:pre [(nat-int? n)]}
   (case n
     0 (list [])
     1 (list [coll])
     2 (sequence
        (map-indexed
         (fn [i _]
           (split-at i coll)))
        (cons 1 coll))
     ;; else
     (sequence
      (comp
       (map-indexed
        (fn [i _]
          (split-at i coll)))
       (mapcat
        (fn [[a b]]
          (sequence
           (map conj)
           (coll-partitions (dec n) a)
           (repeat b)))))
      ;; Adding one more element to the coll ensures we split at 0
      ;; *and* at (count coll) without counting the collection.
      (cons (first coll) coll))))
  ([n m coll]
   {:pre [(nat-int? n) (nat-int? m)]}
   (if (<= m (bounded-count m coll))
     (case n
       0 (list [])
       1 (list [coll])
       2 (sequence
          (comp (map-indexed
                 (fn [i _]
                   (split-at (+ i m) coll)))
                (distinct))
          (cons 1 coll))
       ;; else
       (sequence
        (comp (map-indexed
               (fn [i _]
                 (split-at (+ i m) coll)))
              (distinct)
              (mapcat
               (fn [[a b]]
                 (sequence
                  (map conj)
                  (coll-partitions (dec n) m a)
                  (repeat b)))))
        ;; Adding one more element to the coll ensures we split at 0
        ;; *and* at (count coll) without counting the collection.
        (cons (first coll) coll)))
     (list []))))


(defn str-partitions
  "
  Examples:

  (let [str \"ab\"
      n 0]
  (str-partitions n str))
  ;; => ([])

  (let [str \"ab\"
      n 1]
  (partitions n coll))
  ;; => ([\"ab\"])

  (let [str \"ab\"
      n 2]
  (partitions n coll))
  ;; => ([[] [\"ab\"]
  ;;     [[\"a\"] [\"b\"]]
  ;;     [[\"ab\"] []])

  (let [str \"ab\"
      n 3]
  (partitions n coll))
  ;; => ([[] [] [\"ab\"]]
  ;;     [[] [\"a\"] [\"b\"]]
  ;;     [[\"a\"] [] [\"b\"]]
  ;;     [[] [\"ab\"] []]
  ;;     [[\"a\"] [\"b\"] []]
  ;;     [[\"ab\"] [] []])
  "
  [n str]
  {:pre [(nat-int? n)]}
  (case n
    0 (list [])
    1 (list [str])
    2 (sequence
       (map
        (fn [i]
          [(subs str 0 i) (subs str i)]))
       (range (inc (.length str))))
    ;; else
    (sequence
     (comp
      (map
       (fn [i]
         [(subs str 0 i) (subs str i)]))
      (mapcat
       (fn [[a b]]
         (sequence
          (map conj)
          (str-partitions (dec n) a)
          (repeat b)))))
     (range (inc (.length str))))))


(defn epsilon-partitions "
  Examples:

  (def coll [:a :b])

  (partitions 0 coll))
  ;; => ([])

  (partitions 1 coll)
  ;; => ([[:a :b]])

  (partitions 2 coll)
  ;; => '([[] [:a :b]]
  ;;      [[:a] [:b]]
  ;;      [[:a :b] []])

  (partitions 3 coll)
  ;; => '([[] [] [:a :b]]
  ;;      [[] [:a] [:b]]
  ;;      [[:a] [] [:b]]
  ;;      [[] [:a :b] []]
  ;;      [[:a] [:b] []]
  ;;      [[:a :b] [] []])
  "
  ([n coll]
   (cond
     (vector? coll)
     (vec-partitions n coll)

     (coll? coll)
     (coll-partitions n coll)

     (string? coll)
     (str-partitions n coll)

     (nil? coll)
     ()

     :else
     (throw (ex-info "coll must be a string? or coll?" {:type (type coll)}))))
  ([n m coll]
   (cond
     (vector? coll)
     (vec-partitions n m coll)

     (coll? coll)
     (coll-partitions n m coll)

     (string? coll)
     (str-partitions n coll)

     (nil? coll)
     ()

     :else
     (throw (ex-info "coll must be a string? or coll?" {:type (type coll)})))))

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
