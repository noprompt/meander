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

(defn run-plus [state n input f g]
  (let [partitions (partitions input)]
    (concat
     (mapcat
      (fn [partition]
        (let [left (nth partition 0)
              right (nth partition 1)
              n* (dec n)]
          (mapcat
           (fn [state]
             (run-plus state n* right f g))
           (f state left))))
      partitions)
     (if (<= n 0)
       (g state input)
       (fail)))))

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
    (let [result (-generate this {})]
      (nth result 0)))

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
    (let [result (-generate this {})]
      (nth result 0)))

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

(deftype CycleGenerator [generators ^:unsynchronized-mutable current-index]
  java.util.Iterator
  (hasNext [this]
    true)

  (next [this]
    (if (< current-index (count generators))
      (let [current (nth generators current-index)]
        (set! current-index (inc current-index))
        (.next current))
      (let [current (nth generators 0)]
        (set! current-index 1)
        (.next current))))

  IGenerate
  (-generate [this env]
    (if (< current-index (count generators))
      (let [current (nth generators current-index)]
        (set! current-index (inc current-index))
        (-generate current env))
      (let [current (nth generators 0)]
        (set! current-index 1)
        (-generate current env)))))

(deftype MapGenerator [f generator]
  java.util.Iterator
  (hasNext [this]
    (.hasNext generator))

  (next [this]
    (f (.next generator)))

  IGenerate
  (-generate [this env]
    (let [result (-generate generator env)
          x (nth result 0)]
      (if (fail? x)
        result
        (let [env* (nth result 1)]
          [(f x) env*])))))

(defn cycle-generator [generators]
  (if (seq generators)
    (CycleGenerator. (vec generators) 0)
    (reify
      IGenerate
      (-generate [this env]
        [(fail) env]))))

;; (let [step-size 2
;;       step (double (/ 1 step-size))
;;       g (cycle-generator (map constant-generator (range 0 2 step)))
;;       f (MapGenerator. (fn [n]
;;                          (Math/sin (* n Math/PI)))
;;                        g)]
;;   [(.next f)
;;    (.next g)
;;    (.next f)]
;;   #_
;;   [(.next g) (.next f) (.next g) ])

;; (let [?x (logic-variable-generator '?x)
;;       ?y (logic-variable-generator '?y)]
;;   (-generate
;;    (vector-generator [(<-generator ?x ?y) ?x ?y])
;;    {}))

;; [[false 577500676 262064960] {?x 577500676, ?y 262064960}]

;; [(< !xs 1) !xs]

;; (defn <-generator [a b]
;;   (reify
;;     IGenerate
;;     (-generate [this env]
;;       (let [a-result (-generate a env)
;;             a (nth a-result 0)]
;;         (if (fail? a)
;;           a-result
;;           (let [a-env (nth a-result 1)
;;                 b-result (-generate b a-env)
;;                 b (nth b-result 0)]
;;             (if (fail? b-result)
;;               b-result
;;               (let [b-env (nth b-result 1)]
;;                 (cond
;;                   (and (number? a) (number? b))
;;                   [(< a b) b-env]

;;                   (and (instance? LogicVariableGenerator a)
;;                        (number? b))
;;                   (let [n (rand-int Integer/MAX_VALUE)]
;;                     [(< n b) (assoc b-env (.-symbol a) n)])

;;                   (and (instance? MemoryVariableGenerator a)
;;                        (number? b))
;;                   (let [n (rand-int Integer/MAX_VALUE)]
;;                     [(< n b) (assoc b-env (.-symbol a) [n])])

;;                   (and (instance? LogicVariableGenerator a)
;;                        (instance? MemoryVariableGenerator b))
;;                   (let [m (rand-int Integer/MAX_VALUE)
;;                         n (rand-int Integer/MAX_VALUE)]
;;                     [(< n b) (merge b-env {(.-symbol a) m, (.-symbol b) [n]})])

;;                   (and (number? a)
;;                        (instance? LogicVariableGenerator b))
;;                   (let [n (rand-int Integer/MAX_VALUE)]
;;                     [(< a n) (assoc b-env (.-symbol b) n)])

;;                   (and (instance? LogicVariableGenerator a)
;;                        (instance? LogicVariableGenerator b))
;;                   (let [m (rand-int Integer/MAX_VALUE)
;;                         n (rand-int Integer/MAX_VALUE)]
;;                     [(< m n) (merge b-env {(.-symbol a) m, (.-symbol b) n})])

;;                   :else
;;                   [(fail) a-env])))))))))
