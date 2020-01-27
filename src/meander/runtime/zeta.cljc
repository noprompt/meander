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
