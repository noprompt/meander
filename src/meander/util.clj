(ns meander.util)


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
