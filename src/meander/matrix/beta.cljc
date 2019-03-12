(ns meander.matrix.beta
  "Operators for pattern matrices."
  (:refer-clojure :exclude [empty?])
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as s.gen]
            [meander.syntax.beta :as r.syntax]))


(s/def :meander.matrix.beta/matrix
  (s/coll-of :meander.matrix.beta/row
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
                      (s/gen :meander.matrix.beta/row))))))


(s/def :meander.matrix.beta/row
  (s/keys :req-un [:meander.matrix.beta.row/cols
                   :meander.matrix.beta.row/rhs]
          :opt-un [:meander.matrix.beta.row/env]))


(s/def :meander.matrix.beta.row/cols
  (s/coll-of :meander.syntax.beta/node
             :kind sequential?
             :into []))


(s/def :meander.matrix.beta.row/rhs
  any?)


(s/def :meander.matrix.beta.row/env
  (s/coll-of (s/or :lvr :meander.syntax.beta/logic-variable
                   :mvr :meander.syntax.beta/memory-variable)
             :kind set?
             :into #{}))


(s/def :meander.matrix.beta/object
  (s/or :matrix :meander.matrix.beta/matrix
        :row :meander.matrix.beta/row
        :unknown any?))


;; ---------------------------------------------------------------------
;; Matrix

(defn action [row]
  (:rhs row))


(defn row?
  "true if x is a matrix row."
  [x]
  (s/valid? :meander.matrix.beta/row x))


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
  :args (s/cat :matrix :meander.matrix.beta/matrix
               :i nat-int?
               :j nat-int?)
  :ret :meander.matrix.beta/matrix)


(defn swap-column
  "Swaps column i with column j in the matrix."
  [matrix i j]
  (into [] (map
            (fn [row]
              (update row :cols swap i j)))
        matrix))


(s/fdef subcols
  :args (s/or :a2 (s/cat :matrix :meander.matrix.beta/matrix
                         :i nat-int?)
              :a3 (s/cat :matrix :meander.matrix.beta/matrix
                         :i nat-int?
                         :j nat-int?))
  :ret :meander.matrix.beta/matrix)


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
  :args (s/cat :matrix :meander.matrix.beta/matrix)
  :ret nat-int?)


(defn width
  [matrix]
  (count (:cols (first matrix))))


(s/fdef nth-column
  :args (s/cat :matrix :meander.matrix.beta/matrix)
  :ret (s/coll-of :meander.syntax.beta/node))


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
  :args (s/cat :matrix :meander.matrix.beta/matrix)
  :ret :meander.matrix.beta/matrix)


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
  :args (s/cat :matrix :meander.matrix.beta/matrix
               :column (s/coll-of :meander.syntax.beta/node
                                  :kind sequential?
                                  :into []))
  :ret :meander.matrix.beta/matrix)


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
                   :args (s/cat :node :meander.syntax.beta/node)
                   :ret any?)
               :matrix :meander.matrix.beta/matrix)
  :ret (s/map-of :meander.syntax.beta.node/tag
                 :meander.matrix.beta/matrix))


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
  :args (s/cat :row :meander.matrix.beta/row)
  :ret :meander.matrix.beta.row/env)


(defn get-env
  [row]
  (or (:env row) #{}))


(s/fdef add-var
  :args (s/cat :row :meander.matrix.beta/row
               :var (s/or :meander.syntax.beta.node/lvr
                          :meander.syntax.beta.node/mvr))
  :ret :meander.matrix.beta/row)

(defn add-var
  "Add var to the environment in row."
  [row var]
  (update row :env (fnil conj #{}) var))


(s/fdef get-var
  :args (s/cat :row :meander.matrix.beta/row
               :var (s/or :meander.syntax.beta.node/lvr
                          :meander.syntax.beta.node/mvr))
  :ret (s/nilable
        (s/or :meander.syntax.beta.node/lvr
              :meander.syntax.beta.node/mvr)))


(defn get-var
  "Get var from the environment in row."
  [row var]
  (get (:env row) var))
