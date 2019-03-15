(ns meander.matrix.gamma
  "Operators for pattern matrices."
  (:refer-clojure :exclude [empty?])
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as s.gen]
            [meander.syntax.gamma :as r.syntax]))


(s/def :meander.matrix.gamma/matrix
  (s/coll-of :meander.matrix.gamma/row
             :kind sequential?
             :into []
             :gen (fn []
                    (s.gen/fmap
                     (fn [rows]
                       ;; All rows of a matrix must have equal width.
                       (let [i (reduce min (map (comp count :cols) rows))]
                         (into []
                               (map
                                (fn [row]
                                  (update row :cols subvec 0 i)))
                               rows)))
                     (s.gen/vector
                      (s/gen :meander.matrix.gamma/row))))))


(s/def :meander.matrix.gamma/row
  (s/keys :req-un [:meander.matrix.gamma.row/cols
                   :meander.matrix.gamma.row/rhs]
          :opt-un [:meander.matrix.gamma.row/env]))


(s/def :meander.matrix.gamma.row/cols
  (s/coll-of :meander.syntax.gamma/node
             :kind sequential?
             :into []))


(s/def :meander.matrix.gamma.row/rhs
  any?)


(s/def :meander.matrix.gamma.row/env
  (s/coll-of (s/or :lvr :meander.syntax.gamma/logic-variable
                   :mvr :meander.syntax.gamma/memory-variable)
             :kind set?
             :into #{}))


(s/def :meander.matrix.gamma/object
  (s/or :matrix :meander.matrix.gamma/matrix
        :row :meander.matrix.gamma/row
        :unknown any?))


;; ---------------------------------------------------------------------
;; Matrix

(defn action [row]
  (:rhs row))


(defn row?
  "true if x is a matrix row."
  [x]
  (s/valid? :meander.matrix.gamma/row x))


(defn empty?
  "true if matrix has no columns."
  [matrix]
  (every?
   (fn [row]
     (not (seq (:cols row))))
   matrix))


(defn element
  ([matrix i j]
   (nth (:cols (nth matrix i)) j))
  ([matrix i j not-found]
   (let [x (nth matrix i not-found)]
     (if (identical? x not-found)
       not-found
       (nth (:cols x) j not-found)))))


(defn swap
  "Swap elements at positions i and j in the vector v."
  [v i j]
  (let [v (vec v)]
    (assoc v i (nth v j) j (nth v i))))


(s/fdef swap-column
  :args (s/cat :matrix :meander.matrix.gamma/matrix
               :i nat-int?
               :j nat-int?)
  :ret :meander.matrix.gamma/matrix)


(defn swap-column
  "Swaps column i with column j in the matrix."
  [matrix i j]
  (into [] (map
            (fn [row]
              (update row :cols swap i j)))
        matrix))


(s/fdef subcols
  :args (s/or :a2 (s/cat :matrix :meander.matrix.gamma/matrix
                         :i nat-int?)
              :a3 (s/cat :matrix :meander.matrix.gamma/matrix
                         :i nat-int?
                         :j nat-int?))
  :ret :meander.matrix.gamma/matrix)


(defn subcols
  "Return matrix with only the columns after i or i through j."
  ([matrix i]
   (into [] (map
             (fn [row]
               (update row :cols subvec i)))
         matrix))
  ([matrix i j]
   (into [] (map
             (fn [row]
               (update row :cols subvec i j)))
         matrix)))


(s/fdef width
  :args (s/cat :matrix :meander.matrix.gamma/matrix)
  :ret nat-int?)


(defn width
  [matrix]
  (count (:cols (first matrix))))


(s/fdef nth-column
  :args (s/cat :matrix :meander.matrix.gamma/matrix)
  :ret (s/coll-of :meander.syntax.gamma/node))


(defn nth-column
  ([matrix index]
   (into [] (comp (map :cols)
                  (map
                   (fn [col]
                     (nth col index))))
         matrix))
  ([matrix index not-found]
   (into [] (comp (map :cols)
                  (map
                   (fn [col]
                     (nth col index not-found))))
         matrix)))


(defn first-column
  "Return the first column in matrix."
  [matrix]
  (nth-column matrix 0 nil))


(defn columns
  [matrix]
  (sequence
   (map nth-column)
   (repeat matrix)
   (range (width matrix))))


(s/fdef drop-column
  :args (s/cat :matrix :meander.matrix.gamma/matrix)
  :ret :meander.matrix.gamma/matrix)


(defn drop-column
  "Drop the first column in row."
  [matrix]
  (into [] (map
            (fn [row]
              (if (= (:cols row) [])
                row
                (update row :cols subvec 1))))
        matrix))

(s/fdef prepend-column
  :args (s/cat :matrix :meander.matrix.gamma/matrix
               :column (s/coll-of :meander.syntax.gamma/node
                                  :kind sequential?
                                  :into []))
  :ret :meander.matrix.gamma/matrix)


(defn prepend-column
  "Prepends column to matrix."
  [matrix column]
  (into [] (map
             (fn [row cell]
               (assoc row :cols (into [cell] (:cols row))))
             matrix
             column)))


(s/fdef specialize-by
  :args (s/cat :f (s/fspec
                   :args (s/cat :node :meander.syntax.gamma/node)
                   :ret any?)
               :matrix :meander.matrix.gamma/matrix)
  :ret (s/map-of :meander.syntax.gamma.node/tag
                 :meander.matrix.gamma/matrix))


(defn specialize-by
  "Split matrix into submatrices by the return result of applying f to
  the first column of each row in matrix."
  [f matrix]
  (if (empty? matrix)
    {}
    (let [matrix (vec matrix)
          grouped (group-by (comp f first :cols) matrix)]
      (into
       (sorted-map-by
        (fn [k1 k2]
          (compare
           (apply min ##Inf
                  (map (fn [v]
                         (.indexOf matrix v))
                       (get grouped k1)))
           (apply min  ##Inf
                  (map (fn [v]
                         (.indexOf matrix v))
                       (get grouped k2))))))
       grouped))))


;; ---------------------------------------------------------------------
;; Environment

(s/fdef get-env
  :args (s/cat :row :meander.matrix.gamma/row)
  :ret :meander.matrix.gamma.row/env)


(defn get-env
  [row]
  (or (:env row) #{}))


(s/fdef add-var
  :args (s/cat :row :meander.matrix.gamma/row
               :var (s/or :meander.syntax.gamma.node/lvr
                          :meander.syntax.gamma.node/mvr))
  :ret :meander.matrix.gamma/row)

(defn add-var
  "Add var to the environment in row."
  [row var]
  (update row :env (fnil conj #{}) var))


(s/fdef get-var
  :args (s/cat :row :meander.matrix.gamma/row
               :var (s/or :meander.syntax.gamma.node/lvr
                          :meander.syntax.gamma.node/mvr))
  :ret (s/nilable
        (s/or :meander.syntax.gamma.node/lvr
              :meander.syntax.gamma.node/mvr)))


(defn get-var
  "Get var from the environment in row."
  [row var]
  (get (:env row) var))
