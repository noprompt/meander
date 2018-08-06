(ns meander.matrix.alpha
  "Operators for pattern matrices."
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as s.gen]
            [meander.syntax.alpha]))


(defprotocol IPatternMatrix
  (-nth-column
    [obj index]
    [obj index not-found]))


(s/def :meander.matrix.alpha/matrix
  (s/coll-of :meander.matrix.alpha/row
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
                      (s/gen :meander.matrix.alpha/row))))))


(s/def :meander.matrix.alpha/row
  (s/keys :req-un [:meander.matrix.alpha.row/cols
                   :meander.matrix.alpha.row/rhs]
          :opt-un [:meander.matrix.alpha.row/env]))


(s/def :meander.matrix.alpha.row/cols
  (s/coll-of :meander.syntax.alpha/node
             :kind sequential?
             :into []))


(s/def :meander.matrix.alpha.row/rhs
  any?)


(s/def :meander.matrix.alpha.row/env
  (s/coll-of (s/or :lvr :meander.syntax.alpha/logic-variable
                   :mvr :meander.syntax.alpha/memory-variable)
             :kind set?
             :into #{}))


(s/def :meander.matrix.alpha/object
  (s/or :matrix :meander.matrix.alpha/matrix
        :row :meander.matrix.alpha/row
        :unknown any?))


;; ---------------------------------------------------------------------
;; Row/Column


(defn swap
  "Swap elements at positions i and j in the vector v."
  {:private true}
  [v i j]
  (let [v (vec v)]
    (assoc v i (nth v j) j (nth v i))))


(s/fdef swap-column
  :args (s/cat :matrix :meander.matrix.alpha/matrix
               :i nat-int?
               :j nat-int?)
  :ret :meander.matrix.alpha/matrix)


(defn swap-column
  "Swaps column i with column j in the matrix."
  [matrix i j]
  (sequence
   (map
    (fn [row]
      (update row :cols swap i j)))
   matrix))


(s/fdef subcols
  :args (s/or :a2 (s/cat :matrix :meander.matrix.alpha/matrix
                         :i nat-int?)
              :a3 (s/cat :matrix :meander.matrix.alpha/matrix
                         :i nat-int?
                         :j nat-int?))
  :ret :meander.matrix.alpha/matrix)


(defn subcols
  "Return matrix with only the columns after i or i through j."
  ([matrix i]
   (sequence
    (map
     (fn [row]
       (update row :cols subvec i)))
    matrix))
  ([matrix i j]
   (sequence
    (map
     (fn [row]
       (update row :cols subvec i j)))
    matrix)))


(s/fdef width
  :args (s/cat :matrix :meander.matrix.alpha/matrix)
  :ret nat-int?)


(defn width
  [matrix]
  (count (:cols (first matrix))))


(s/fdef nth-column
  :args (s/cat :matrix :meander.matrix.alpha/matrix)
  :ret (s/coll-of :meander.syntax.alpha/node))


(defn nth-column
  ([matrix index]
   (sequence
    (comp (map :cols)
          (map
           (fn [col]
             (nth col index))))
    matrix))
  ([matrix index not-found]
   (sequence
    (comp (map :cols)
          (map
           (fn [col]
             (nth col index not-found))))
    matrix)))


(defn first-column
  "Return the first column in row."
  [row]
  (nth-column row 0 nil))


(s/fdef drop-column
  :args (s/cat :matrix :meander.matrix.alpha/matrix)
  :ret :meander.matrix.alpha/matrix)


(defn drop-column
  "Drop the first column in row."
  [matrix]
  (sequence
   (map
    (fn [row]
      (update row :cols rest)))
   matrix))


(s/fdef specialize-by
  :args (s/cat :f (s/fspec
                   :args (s/cat :node :meander.syntax.alpha/node)
                   :ret any?)
               :matrix :meander.matrix.alpha/matrix)
  :ret (s/map-of :meander.syntax.alpha.node/tag
                 :meander.matrix.alpha/matrix))


(defn specialize-by
  "Split matrix into submatrices by the return result of applying f to
  the first column of each row in matrix."
  [f matrix]
  (group-by (comp f first :cols) matrix))


;; ---------------------------------------------------------------------
;; Environment

(s/fdef get-env
  :args (s/cat :row :meander.matrix.alpha/row)
  :ret :meander.matrix.alpha.row/env)


(defn get-env
  [row]
  (or (:env row) #{}))


(s/fdef add-var
  :args (s/cat :row :meander.matrix.alpha/row
               :var (s/or :meander.syntax.alpha.node/lvr
                          :meander.syntax.alpha.node/mvr))
  :ret :meander.matrix.alpha/row)

(defn add-var
  "Add var to the environment in row."
  [row var]
  (update row :env (fnil conj #{}) var))


(s/fdef get-var
  :args (s/cat :row :meander.matrix.alpha/row
               :var (s/or :meander.syntax.alpha.node/lvr
                          :meander.syntax.alpha.node/mvr))
  :ret (s/nilable
        (s/or :meander.syntax.alpha.node/lvr
              :meander.syntax.alpha.node/mvr)))


(defn get-var
  "Get var from the environment in row."
  [row var]
  (get (:env row) var))
