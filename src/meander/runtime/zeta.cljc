(ns meander.runtime.zeta)

(defprotocol ISearch
  (-search [this target env]))

(defprotocol IGenerate
  (-generate [this env]))

(defprotocol IStable
  (-stable [this]))

(defmacro stable? [x]
  `(-stable ~x))

(defprotocol IHead
  (-head [this]))

(defprotocol ITail
  (-tail [this]))

(defprotocol IPartitions
  (-partitions [this]))

(defprotocol ITake
  (-take [this n]))

(defprotocol IDrop
  (-drop [this n]))

(defprotocol ISplitAt
  (-split-at [this n]))

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

       ISeqable
       (-seq [_] nil))))

(extend-type (class FAIL)
  IGenerate
  (-generate [this env]
    [this env this])

  IStable
  (-stable [this]
    true))

(defmethod print-method (class FAIL) [g writer]
  (.write writer "#meander.runtime.zeta/fail[]"))

(defmacro head [x]
  `(-head ~x))

(defmacro tail [x]
  `(-tail ~x))

(defmacro partitions [x]
  `(-partitions ~x))

(defmacro fail? [x]
  `(identical? ~x FAIL))

(defmacro fail []
 `FAIL)

(defmacro succeed [x]
  `(list ~x))

(defn iterator [coll]
  #?(:clj (clojure.lang.RT/iter coll)
     :cljs (iter coll)))

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
                       new-val# (conj old-val# ~v)]
                   (assoc ~smap ~v new-val#))
                 (assoc ~smap ~v [~x]))]
     ~body-expression))

(defmacro into-memory-variable
  {:style/indent 1}
  [[smap v x] body-expression]
  `(let* [entry# (find ~smap ~v)]
     (if entry#
       (let* [value# (val entry#)
              value# (into value# ~x)
              ~smap (assoc ~smap ~v value#)]
         ~smap)
       (let* [value# (vec ~x)
              ~smap (assoc ~smap ~v value#)]
         ~body-expression))))

(defn knit
  [colls]
  (case (bounded-count 2 colls)
    0 ()

    1 (if (seq (first colls))
        (first colls)
        ())

    ;; else
    (concat
     (sequence (comp (keep seq)
                     (map first))
               colls)
     (lazy-seq (knit (keep next colls))))))

(defmacro bind
  {:style/indent 1}
  [[symbol expression] body-expression]
  `(let* [~symbol ~expression]
     (if (fail? ~symbol)
       (fail)
       ~body-expression)))

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

(extend-type clojure.lang.IPersistentVector
  ITake
  (-take [this n]
    (let [c (count this)]
      (if (<= n c)
        (subvec this 0 n)
        FAIL)))

  IDrop
  (-drop [this n]
    (let [c (count this)]
      (if (<= n c)
        (subvec this n)
        FAIL)))

  IHead
  (-head [this]
    (if (identical? this clojure.lang.PersistentVector/EMPTY)
      FAIL
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
        FAIL)))

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
      FAIL))

  IDrop
  (-drop [this n]
    (let [m (bounded-count (inc n) this)]
      (if (<= n m)
        (clojure.core/drop n this)
        FAIL)))

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
        FAIL)))

  ISplitAt
  (-split-at [this n]
    (bind [left (-take this n)]
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
    FAIL)

  IPartitions
  (-partitions [this]
    ())

  ISplitAt
  (-split-at [this n]
    FAIL)

  ITail
  (-tail [this]
    nil))

;; ---------------------------------------------------------------------
;; Generators

(defmacro get-val [state]
  `(nth ~state 0))

(defmacro put-val [state val]
  `(assoc ~state 0 ~val))

(defmacro get-env [state]
  `(nth ~state 1))

(defmacro put-env [state env]
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
         (let [env# (get-env ~binding)]
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

(defn const [x]
  (reify
    ISearch
    (-search [this target env]
      (if (= target x)
        (succeed env)))

    IGenerate
    (-generate [this env]
      [x env (fail)])

    IStable
    (-stable [this]
      true)))

(defn fmap
  [f obj]
  (let [stable (stable? obj)]
    (reify
      IGenerate
      (-generate [this env]
        (bind-gen env [state obj]
          (let [val (get-val state)
                val* (f val)
                env* (get-env state)
                obj* (get-gen state)]
            [val* env* (fmap f obj*)])))

      ISearch
      (-search [this target env]
        (-search obj (f target) env))

      IStable
      (-stable [this]
        stable))))

(defn choice
  ([obj-a obj-b]
   (if (= obj-a obj-b)
     obj-a
     (let [stable (and (stable? obj-a) (stable? obj-b))]
       (reify
         IGenerate
         (-generate [this env]
           (if-gen [state obj-a env]
             (let [obj-a* (get-gen state)
                   state* (put-gen state (choice obj-b obj-a*))]
               state*)
             (if-gen [state obj-b env]
               (let [obj-b* (get-gen state)
                     state* (put-gen state obj-b*)]
                 state*))))

         ISearch
         (-search [this target env]
           (concat
            (-search obj-a target env)
            (-search obj-b target env)))

         IStable
         (-stable [this]
           stable)))))
  ([obj-a obj-b & more]
   (choice obj-a (apply choice obj-b more))))

(defn pair
  [obj-1 obj-2]
  (let [obj-1-stable (stable? obj-1)
        obj-2-stable (stable? obj-2)]
    (reify
      IGenerate
      (-generate [this env]
        (bind-gen env [state-1 obj-1
                       state-2 obj-2]
          (let [val-1 (get-val state-1)
                val-2 (get-val state-2)
                obj-1* (get-gen state-1)
                obj-2* (get-gen state-2)
                env* (get-env state-2)
                val [val-1 val-2]
                seq1 (pair (if obj-1-stable
                             (const val-1)
                             obj-1)
                           obj-2*)
                seq2 (pair obj-1*
                           (if obj-2-stable
                             (const val-2)
                             obj-2))
                seq3 (pair obj-1* obj-2*)
                gen* (choice seq1 seq2 seq3)]
            [val env* gen*])))

      ISearch
      (-search [this target env]
        (if (and (sequential? target)
                 (= 2 (bounded-count 3 target)))
          (let [x-1 (nth target 0)
                x-2 (nth target 1)]
            (mapcat
             (fn [env]
               (-search obj-2 x-2 env))
             (-search obj-1 x-1 env)))
          (fail)))

      IStable
      (-stable [this]
        (and obj-1-stable obj-2-stable)))))

(defmacro cons-gen [gen-car gen-cdr]
  `(fmap (fn [xs#]
           (cons (nth xs# 0) (nth xs# 1)))
     (pair ~gen-car ~gen-cdr)))

(defn plus [obj]
  (reify
    IGenerate
    (-generate [this env]
      (if-gen [state obj env]
        (let [vals (get-val state)
              env* (get-env state)
              obj* (get-gen state)
              obj* (choice (fmap (fn [xs]
                                   (concat (nth xs 0) (nth xs 1)))
                             (pair obj this))
                           (plus obj*))]
          [vals env* obj*])))

    IStable
    (-stable [this]
      true)))

(defn star
  ([obj]
   (choice (const ()) (plus obj))))

(defn memory-variable [symbol]
  (reify
    IGenerate
    (-generate [this env]
      (let [xs (get env symbol)]
        (if (seq xs)
          (let [x (nth xs 0)
                xs* (next xs)
                env* (assoc env symbol xs*)]
            [x env* (fail)])
          [(fail) env (fail)])))

    ISearch
    (-search [this target env]
      (bind-memory-variable [env symbol target]
        (succeed env)))

    IStable
    (-stable [this]
      false)))

(defn logic-variable [symbol]
  (reify
    IGenerate
    (-generate [this env]
      (if-some [entry (find env symbol)]
        [(val entry) env (fail)]
        [(fail) (assoc env symbol this) this]))

    ISearch
    (-search [this target env]
      (bind-logic-variable [env symbol target]
        (succeed env)))

    IStable
    (-stable [this]
      true)))

(defn source [xs]
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

(defn cata
  {:style/indent 1}
  [f obj]
  (let [stable (stable? obj)]
    (reify
      IGenerate
      (-generate [this env]
        (bind-gen env [state obj]
          (let [val (f (get-val state))]
            (if (seq val)
              (let [val* (nth val 0)
                    env* (get-env state)
                    obj* (get-gen state)
                    gen* (choice (source (next val))
                                 (cata f obj*))]
                [val* env* gen*])
              [(fail) env (fail)]))))

      ISearch
      (-search [this target env]
        (mapcat
         (fn [x]
           (-search obj x env))
         (f target)))

      IStable
      (-stable [this] stable))))

(defn join [obj-1 obj-2]
  (fmap (fn [xs]
          (concat (nth xs 0) (nth xs 1)))
        (pair obj-1 obj-2)))

(defn drain [symbol]
  (reify
    IGenerate
    (-generate [this env]
      (let [val (get env symbol)
            env* (assoc env symbol ())
            gen* (fail)]
        [val env* gen*]))

    ISearch
    (-search [this target env]
      (if (sequential? target)
        (into-memory-variable [env symbol target]
          (succeed env))
        (fail)))

    IStable
    (-stable [this]
      false)))
