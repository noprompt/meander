(ns meander.runtime.zeta)

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
  (reify
    clojure.lang.Seqable
    (seq [this] nil)))

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
           (succeed ~smap)
           (fail)))   
       (let* [~smap (assoc ~smap ~v ~x)]
         ~body-expression))))

(defn bind-memory-variable [smap key val]
  (if-some [entry (find smap key)]
    (let [old-val (clojure.core/val entry)
          new-val (conj old-val val)]
      (assoc smap key new-val))
    (assoc smap key [val])))

(defmacro into-memory-variable [[smap v x] body-expression]
  `(let* [entry# (find ~smap ~v)]
     (if entry#
       (let* [value# (val entry#)
              value# (into value# ~x)
              ~smap (assoc ~smap ~v value#)])  
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
;; Generate

(defprotocol IGenerate
  (-generate [this env]))

(deftype ConstantGenerator [value]
  java.util.Iterator
  (hasNext [this]
    true)

  (next [this]
    value)

  IGenerate
  (-generate [this env]
    [value env]))

(deftype LogicVariableGenerator [symbol]
  java.util.Iterator
  (hasNext [this]
    true)

  (next [this]
    this)

  IGenerate
  (-generate [this env]
    (if-some [entry (find env symbol)]
      [(val entry) env]
      [this (assoc env symbol this)])))

(defmethod print-method LogicVariableGenerator [g writer]
  (print-method (.-symbol g) writer))

(deftype MemoryVariableGenerator [symbol]
  java.util.Iterator
  (hasNext [this]
    true)

  (next [this]
    this)

  IGenerate
  (-generate [this env]
    (if-some [xs-entry (find env symbol)]
      (let [xs (val xs-entry)
            index (if-some [index-entry (find env [symbol :index])]
                    (val index-entry)
                    0)
            x (nth xs index nil)
            index* (inc index)
            env* (assoc env [symbol :index] index*)]
        [x env*])
      [this (assoc env symbol this)])))

(defmethod print-method MemoryVariableGenerator [g writer]
  (print-method (.-symbol g) writer))

(deftype VectorGenerator [generators]
  java.util.Iterator
  (hasNext [this]
    true)

  (next [this]
    (reduce
     (fn [xs generator]
       ;; TODO Handle failure
       (conj xs (.next generator)))
     []
     generators))

  IGenerate
  (-generate [this env]
    (reduce
     (fn [[xs env] generator]
       ;; TODO Handle failure
       (let [[x env*] (-generate generator env)]
         [(conj xs x) env*]))
     [[] env]
     generators)))

(deftype SeqGenerator [generators]
  java.util.Iterator
  (hasNext [this]
    true)

  (next [this]
    (map
     (fn [generator]
       ;; TODO Handle failure
       (.next generator))
     generators))

  IGenerate
  (-generate [this env]
    ;; TODO Handle failure
    ((fn f [gens env]
       (if (seq gens)
         (lazy-seq
          (let [gen (nth gens 0)
                [x env*] (-generate gen env)
                [xs env**] (f (next gens) env*)]
            [(cons x xs) env**]))
         [() env]))
     generators env)))

(defn logic-variable-generator [symbol]
  (LogicVariableGenerator. symbol))

(defn memory-variable-generator [symbol]
  (MemoryVariableGenerator. symbol))

(defn constant-generator [value]
  (ConstantGenerator. value))

(defn seq-generator [generators]
  (SeqGenerator. generators))

(defn vector-generator [generators]
  (VectorGenerator. generators))
