(ns meander.util)


(defmacro undefined
  ([] `(throw (ex-info "undefined" ~(meta &form)))))


(defn rotations
  ([coll]
   (lazy-seq
    (cons coll
          (rotations
           (concat (rest coll)
                   (list (first coll))))))))


(defn cartesian-product
  "All the ways to take one item from each sequence."
  ([seqs]
   (let [v-original-seqs (vec seqs)
         step
         (fn step [v-seqs]
           (let [increment
                 (fn [v-seqs]
                   (loop [i (dec (count v-seqs))
                          v-seqs v-seqs]
                     (if (= i -1) nil
                         (if-let [rst (next (v-seqs i))]
                           (assoc v-seqs i rst)
                           (recur (dec i)
                                  (assoc v-seqs i (v-original-seqs i)))))))]
             (when v-seqs
               (cons (map first v-seqs)
                     (lazy-seq (step (increment v-seqs)))))))]
     (when (every? seq seqs)
       (lazy-seq (step v-original-seqs))))))


(defn swap
  "Swap the elements at positions `i` and `j` in `v`."
  {:private true}
  [v i j]
  (-> v
      (assoc i (get v j))
      (assoc j (get v i))))


;; SEE: https://en.wikipedia.org/wiki/Heap%27s_algorithm
(defn permutations [coll]
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


(defn subseqs [coll]
  (cond
    (string? coll)
    (let [l (inc (.length coll))]
      (distinct
       (for [i (range l)
             j (range i)]
         (subs coll j i))))

    (vector? coll)
    (distinct
     (let [l (inc (.length coll))]
       (for [i (range l)
             j (range i)]
         (subvec coll j i))))

    (seq? coll)
    (distinct
     (for [i (map-indexed (fn [i _] (inc i)) coll)
           j (range i)]
       (take i (drop j coll))))))


(defn vsplit-at
  "Like `clojure.core/split-at` but for vectors."
  ([n v]
   {:pre [(vector? v)]}
   (let [i (min n (count v))]
     [(subvec v 0 i) (subvec v i)])))


(defn vec-partitions [n coll]
  {:private true}
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
  {:pre [(nat-int? n)]}
  (cond
    (= n 0)
    (list [])

    (= n 1)
    (list [coll])

    :else
    (mapcat
     (fn [[a b]]
       (lazy-seq
        (map conj (vec-partitions (dec n) a) (repeat b))))
     (map-indexed
      (fn [i _]
        (vsplit-at i coll))
      (range (inc (count coll)))))))


(defn coll-partitions
  {:private true}
  ([n coll]
   {:pre [(nat-int? n)]}
   (cond
     (= n 0)
     (list [])

     (= n 1)
     (list [coll])

     :else
     (mapcat
      (fn [[a b]]
        (lazy-seq
         (map conj (coll-partitions (dec n) a) (repeat b))))
      (map-indexed
       (fn [i _]
         (split-at i coll))
       ;; Adding one more element to the coll ensures we split at 0
       ;; *and* at `(count coll)` without count the collection.
       (cons (first coll) coll))))))


(defn str-partitions [n str]
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
  {:pre [(nat-int? n)]}
  (cond
    (= n 0)
    (list [])

    (= n 1)
    (list [str])

    :else
    (mapcat
     (fn [[a b]]
       (lazy-seq
        (map conj (str-partitions (dec n) a) (repeat b))))
     (map
      (fn [i]
        [(subs str 0 i) (subs str i)])
      (range (inc (.length str)))))))


(defn partitions [n coll]
  "
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
  (cond
    (vector? coll)
    (vec-partitions n coll)

    (coll? coll)
    (coll-partitions n coll)

    (string? coll)
    (str-partitions n coll)

    :else
    (undefined)))
