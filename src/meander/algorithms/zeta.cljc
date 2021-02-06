(ns meander.algorithms.zeta
  #?(:clj
     (:import (clojure.lang PersistentList
                            PersistentVector
                            PersistentHashSet))))


(defn cljs-env?
  "true if compiling ClojureScript or in a ClojureScript setting,
  false otherwise."
  [env]
  #?(:clj (some? (:ns env))
     :cljs true))

(defmacro __disj
  {:private true}
  [s x]
  (if (cljs-env? &env)
    `(disj ~s ~x)
    `(.disjoin ~(vary-meta s assoc :tag 'clojure.lang.IPersistentSet) ~x)))

(defmacro __dissoc
  {:private true}
  ([m x]
   (if (cljs-env? &env)
     `(dissoc ~m ~x)
     `(.without  ~(vary-meta m assoc :tag 'clojure.lang.IPersistentMap) ~x)))
  ([m x & xs]
   `(__dissoc (__dissoc ~m ~x) ~@xs)))

(defmacro __nth
  {:private true}
  [v i]
  (if (cljs-env? &env)
    `(nth ~v ~i)
    `(.nth ~(vary-meta v assoc :tag 'clojure.lang.IPersistentVector) ~i)))

(defn tail
  [x]
  {:pre [(sequential? x)]}
  (if (vector? x)
    (if (zero? (count x))
      []
      (subvec x 1))
    (rest x)))

;; Permutations
;; ---------------------------------------------------------------------

(defn swap
  "Swap the elements at positions `i` and `j` in `v`."
  {:private true}
  [v i j]
  (-> v
      (assoc i (nth v j))
      (assoc j (nth v i))))

;; SEE: https://en.wikipedia.org/wiki/Heap%27s_algorithm
(defn permutations
  "Return a sequence of all the ways to arrange coll."
  [coll]
  (let [v (vec coll)
        n (count v)]
    (loop [n n
           a [v]]
      (if (zero? n)
        a
        (let [n* (dec n)
              a* (mapcat
                  (fn step [v]
                    (map
                     (fn [i]
                       (swap v i n*))
                     (range n)))
                  a)]
          (recur n* a*))))))

(defn set-k-permutations-with-unselected
  "Set specific algorithm for returning a lazy sequence of pairs

     [[,,,] #{,,,}]

  where the first element in the pair is a permuted selection of k
  items from the set s, and the second element is s with those
  elements removed."
  [s k]
  (case k
    0 (list [[] s])

    1 (map (fn [x]
             [[x] (__disj s x)])
           s)

    2 (mapcat
       (fn [x]
         (let [s-x (__disj s x)]
           (map (fn [y]
                  [[x y] (__disj s-x y)])
                s-x)))
       s)

    ;; else
    (mapcat
     (fn [pair]
       (let [xs (__nth pair 0)
             s-xs (__nth pair 1)]
         (map (fn [x]
                [(conj xs x) (__disj s-xs x)])
              s-xs)))
     (set-k-permutations-with-unselected s (dec k)))))

(defn map-k-permutations-with-unselected
  "Map specific algorithm for returning a lazy sequence of pairs

     [[,,,] {,,,}]

  where the first element in the pair is a permuted selection of k
  entries from the map m, and the second element is m with those
  entries removed."
  [m k]
  (case k
    0 (list [[] m])

    1 (map (fn [e]
             [[e] (__dissoc m (key e))])
           m)

    2 (mapcat
       (fn [e1]
         (let [m-e1 (__dissoc m (key e1))]
           (map (fn [e2]
                  [[e1 e2] (__dissoc m-e1 (key e2))])
                m-e1)))
       m)

    ;; else
    (mapcat
     (fn [pair]
       (let [es (__nth pair 0)
             m-es (__nth pair 1)]
         (map (fn [e]
                [(conj es e) (__dissoc m-es (key e))])
              m-es)))
     (map-k-permutations-with-unselected m (dec k)))))

(defn vector-k-permutations-with-unselected
  "Vector specific algorithm for returning a lazy sequence of pairs

     [[,,,] [,,,]]

  where the first element in the pair is a permuted selection of k
  items from the vector v, and the second element is v with the items
  at their respective indicies removed."
  [v k]
  (case k
    0 (list [[] v])

    1 (map-indexed
       (fn [i x]
         (let [j (inc i)]
           [[x] (into (subvec v 0 i) (subvec v j))]))
       v)

    ;; else
    (mapcat
     (fn [pair]
       (let [xs (__nth pair 0)
             v-xs (__nth pair 1)]
         (map-indexed
          (fn [i x]
            (let [j (inc i)]
              [(conj xs x) (into (subvec v-xs 0 i) (subvec v-xs j))]))
          v-xs)))
     (vector-k-permutations-with-unselected v (dec k)))))

(defn seq-k-permutations-with-unselected
  "Seq specific algorithm for returning a lazy sequence of pairs

     [[,,,] (,,,)]

  where the first element in the pair is a permuted selection of k
  items from the seq s, and the second element is s with the items
  at their respective indicies removed."
  [s k]
  (case k
    0 (list [[] s])

    1 (map-indexed
       (fn [i x]
         (let [j (inc i)]
           [[x] (concat (take i s) (drop j s))]))
       s)

    ;; else
    (mapcat
     (fn [pair]
       (let [xs (__nth pair 0)
             s-xs (__nth pair 1)]
         (map-indexed
          (fn [i x]
            (let [j (inc i)]
              [(conj xs x) (concat (take i s-xs) (drop j s-xs))]))
          s-xs)))
     (seq-k-permutations-with-unselected s (dec k)))))

(defn k-permutations
  "All the ways to choose k permuted items from coll."
  [coll k]
  (map (fn [pair]
         (__nth pair 0))
       (cond
         (map? coll)
         (map-k-permutations-with-unselected coll k)

         (set? coll)
         (set-k-permutations-with-unselected coll k)

         (vector? coll)
         (vector-k-permutations-with-unselected coll k)

         :else
         (seq-k-permutations-with-unselected coll k))))
;; Partitions
;; ---------------------------------------------------------------------


(defprotocol IPartitions
  (-partitions [coll n] "Return a lazy sequence of all of the ways to partition coll into n pieces."))

;; Big Endian
(defn code-from-number
  {:private true}
  [base number]
  (loop [o (transient [])
         n number]
    (if (zero? n)
      (persistent! o)
      (if (zero? n)
        (persistent! o)
        (recur (conj! o (rem n base))
               (quot n base))))))

;; Big Endian
(defn increment-code
  {:private true}
  [base code]
  (reduce
   (fn [v i]
     (let [b (nth v i)]
       (if (zero? b)
         (reduced (assoc v i 1))
         (let [b (+ b 1)]
           (if (zero? (mod b base))
             (assoc v i 0)
             (assoc v i b))))))
   code
   (range (count code))))

(defn code-seq
  {:arglists '([number-of-positions number-of-partitions])
   :private true}
  [n m]
  (let [number-of-answers (Math/pow m n)]
    (if (< number-of-answers ##Inf)
      (take number-of-answers
            (iterate (partial increment-code m) (vec (repeat n 0))))
      (let [last-answer (vec (repeat n (dec m)))]
        (take-while (fn [answer]
                      (not (= answer last-answer)))
                    (iterate (partial increment-code m) (vec (repeat n 0))))))))

(defn set-partitions
  {:arglists '([set number-of-partitions])}
  [n s]
  (let [elements (vec s)
        number-of-elements (count s)
        empty-partitions (vec (repeat n #{}))]
    (map (fn [code]
           (reduce (fn [partitions i]
                     (update partitions (nth code i) conj (nth elements i)))
                   empty-partitions
                   (range (count code))))
         (code-seq number-of-elements n))))

(defn map-partitions
  {:arglists '([map number-of-partitions])}
  [m n]
  (let [elements (vec m)
        number-of-elements (count m)
        empty-partitions (vec (repeat n {}))]
    (map (fn [code]
           (reduce (fn [partitions i]
                     (update partitions (nth code i) conj (nth elements i)))
                   empty-partitions
                   (range (count code))))
         (code-seq number-of-elements n))))

(defn vector-partitions
  {:arglists '([number-of-partitions vector])}
  [n v]
  (case n
    0 (list [])
    1 (list [v])
    2 (sequence (map (fn [i]
                       (let [a (subvec v 0 i)
                             b (subvec v i)]
                         [a b])))
                (range (inc (count v))))
    ;; else
    (sequence
     (comp (map-indexed (fn [i _]
                          [(subvec v 0 i) (subvec v i)]))
           (mapcat (fn [[a b]]
                     (sequence (map conj)
                               (vector-partitions (dec n) a)
                               (repeat b)))))
     (range (inc (count v))))))

(defn string-partitions
  {:arglists '([number-of-partitions string])}
  [^String s n]
  (case n
    0 (list [])
    1 (list [s])
    2 (sequence (map (fn [i]
                       (let [a (.substring s 0 i)
                             b (.substring s i)]
                         [a b])))
                (range (inc (.length s))))
    ;; else
    (sequence
     (comp (map (fn [i]
                  (let [a (.substring s 0 i)
                        b (.substring s i)]
                    [a b])))
           (mapcat (fn [[a b]]
                     (sequence (map conj)
                               (string-partitions (dec n) a)
                               (repeat b)))))
     (range (inc (.length s))))))

(defn seq-partitions
  {:arglists '([number-of-partitions seq])}
  [n xs]
  (case n
    0 (list [])
    1 (list [xs])
    2 (sequence (map-indexed (fn [i _]
                               (split-at i xs)))
                (cons 1 xs))
    ;; else
    (sequence
     (comp
      (map-indexed
       (fn [i _]
         (split-at i xs)))
      (mapcat
       (fn [[a b]]
         (sequence (map conj)
                   (seq-partitions (dec n) a)
                   (repeat b)))))
     ;; Adding one more element to the xs ensures we split at 0 *and*
     ;; at (count xs) without counting the collection.
     (cons (first xs) xs))))

(defn partitions [n coll]
  (cond
    (seq? coll)
    (seq-partitions n coll)

    (vector? coll)
    (vector-partitions n coll)

    (set? coll)
    (set-partitions n coll)

    (map? coll)
    (map-partitions coll n)

    :else
    (seq-partitions n coll)))
