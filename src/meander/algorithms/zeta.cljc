(ns meander.algorithms.zeta)

;; Partitions
;; ---------------------------------------------------------------------

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
  {:arglists '([number-of-partitions set])}
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
  {:arglists '([string number-of-partitions])}
  [^String s n]
  (case n
    0 (list [])
    1 (list [s])
    2 (sequence (map (fn [i]
                       (let [a (.substring s 0 i)
                             b (.substring s i)]
                         [a b])))
                (range (inc #?(:clj (.length s), :cljs (.-length s)))))
    ;; else
    (sequence
     (comp (map (fn [i]
                  (let [a (.substring s 0 i)
                        b (.substring s i)]
                    [a b])))
           (mapcat (fn [[a b]]
                     (sequence (map conj)
                               (string-partitions a (dec n))
                               (repeat b)))))
     (range (inc #?(:clj (.length s), :cljs (.-length s)))))))

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

;; `integer-partitions*` is based on a modified version of the `ruleAsc`
;; algorithm.
;;
;; def ruleAscLen(n, l):
;;     a = [0 for i in range(n + 1)]
;;     k = 1
;;     a[0] = 0
;;     a[1] = n
;;     while k != 0:
;;         x = a[k - 1] + 1
;;         y = a[k] - 1
;;         k -= 1
;;         while x <= y and k < l - 1:
;;             a[k] = x
;;             y -= x
;;             k += 1
;;         a[k] = x + y
;;         yield a[:k + 1]
;;
;; SEE: https://math.stackexchange.com/a/28371
;; SEE: http://jeromekelleher.net/category/combinatorics.html
(defn integer-partitions*
  {:arglists '([number-of-partitions integer])}
  [l n]
  (loop [k 1
         v (assoc (vec (repeat n 0)) 1 n)
         vs-out []]
    (if (zero? k)
      vs-out
      (let [[k v] (loop [v v
                         x (inc (nth v (dec k)))
                         y (dec (nth v k))
                         k (dec k)]
                    (if (and (<= x y) (< k (dec l)))
                      (recur (assoc v k x)
                             x
                             (- y x)
                             (inc k))
                      [k (assoc v k (+ x y))]))]
        (recur k v (conj vs-out (subvec v 0 (inc k))))))))

(defn integer-partitions [number-of-partitions integer]
  (cond
    (zero? number-of-partitions)
    []

    (zero? integer)
    [(vec (repeat number-of-partitions 0))]

    :else
    (let [partitions (integer-partitions* number-of-partitions integer)]
      (map (fn [vs]
             (into vs (repeat (- number-of-partitions (count vs)) 0)))
           partitions))))

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

;; Permutations
;; ---------------------------------------------------------------------

(defn set-k-permutations-with-unselected
  "Set specific algorithm for returning a lazy sequence of pairs

     [[,,,] #{,,,}]

  where the first element in the pair is a permuted selection of k
  items from the set s, and the second element is s with those
  elements removed."
  [s ^long k]
  (case k
    0 (list [[] s])

    1 (map (fn [x]
             [[x] (disj s x)])
           s)

    2 (mapcat
       (fn [x]
         (let [s-x (disj s x)]
           (map (fn [y]
                  [[x y] (disj s-x y)])
                s-x)))
       s)

    ;; else
    (mapcat
     (fn [pair]
       (let [xs (nth pair 0)
             s-xs (nth pair 1)]
         (map (fn [x]
                [(conj xs x) (disj s-xs x)])
              s-xs)))
     (set-k-permutations-with-unselected s (dec k)))))

(defn map-k-permutations-with-unselected
  "Map specific algorithm for returning a lazy sequence of pairs

     [[,,,] {,,,}]

  where the first element in the pair is a permuted selection of k
  entries from the map m, and the second element is m with those
  entries removed."
  [m ^long k]
  (case k
    0 (list [[] m])

    1 (map (fn [e]
             [[e] (dissoc m (key e))])
           m)

    2 (mapcat
       (fn [e1]
         (let [m-e1 (dissoc m (key e1))]
           (map (fn [e2]
                  [[e1 e2] (dissoc m-e1 (key e2))])
                m-e1)))
       m)

    ;; else
    (mapcat
     (fn [pair]
       (let [es (nth pair 0)
             m-es (nth pair 1)]
         (map (fn [e]
                [(conj es e) (dissoc m-es (key e))])
              m-es)))
     (map-k-permutations-with-unselected m (dec k)))))

(defn vector-k-permutations-with-unselected
  "Vector specific algorithm for returning a lazy sequence of pairs

     [[,,,] [,,,]]

  where the first element in the pair is a permuted selection of k
  items from the vector v, and the second element is v with the items
  at their respective indicies removed."
  [v ^long k]
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
       (let [xs (nth pair 0)
             v-xs (nth pair 1)]
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
  [s ^long k]
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
       (let [xs (nth pair 0)
             s-xs (nth pair 1)]
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
         (nth pair 0))
       (cond
         (map? coll)
         (map-k-permutations-with-unselected coll k)

         (set? coll)
         (set-k-permutations-with-unselected coll k)

         (vector? coll)
         (vector-k-permutations-with-unselected coll k)

         :else
         (seq-k-permutations-with-unselected coll k))))

(defn k-combinations
  "All the ways to choose k items from coll."
  [coll k]
  (sequence
   (comp (map set)
         (distinct))
   (k-permutations coll k)))

;; Other
;; ---------------------------------------------------------------------

(defn permuted-integer-partitions [number-of-partitions integer]
  (mapcat (fn [v]
            (map #(nth % 0) (vector-k-permutations-with-unselected v number-of-partitions)))
          (integer-partitions number-of-partitions integer)))

(defn mix*
  "Same as `mix` but accepts a sequence of colls."
  [colls]
  (lazy-seq
   (if (seq colls)
     (if (seq (first colls))
       (cons (ffirst colls)
             (mix* (lazy-cat (next colls) (list (next (first colls))))))
       (mix* (next colls)))
     ())))

(defn mix
  "Like clojure.core/interleave but exhausts each supplied
  collection.

    (interleave [1 2 3] [\"a\" \"b\"] [:w :x :y :z])
    ;; =>
    (1 \"a\" :w 2 \"b\" :x)

    (mix [1 2 3] [\"a\" \"b\"] [:w :x :y :z])
    ;; =>
    (1 \"a\" :w 2 \"b\" :x 3 :y :z)"
  {:arglists '([coll1 coll2 & more-colls])}
  ([coll1 coll2]
   (lazy-seq
    (if (seq coll1)
      (cons (first coll1) (mix coll2 (rest coll1)))
      coll2)))
  ([coll1 coll2 coll3]
   (lazy-seq
    (if (seq coll1)
      (cons (first coll1) (mix coll2 coll3 (rest coll1)))
      (mix coll2 coll3))))
  ([coll1 coll2 coll3 coll4]
   (lazy-seq
    (if (seq coll1)
      (cons (first coll1) (mix coll2 coll3 coll4 (rest coll1)))
      (mix coll2 coll3 coll4))))
  ([coll1 coll2 coll3 coll4 & more-colls]
   (mix* (concat (list coll1 coll2 coll3 coll4) more-colls))))

