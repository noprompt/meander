(ns meander.matrix.alpha
  "Operators for pattern matrices."
  (:refer-clojure :exclude [empty?])
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as s.gen]
            [meander.syntax.alpha :as r.syntax]))


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
;; Matrix

(defn action [row]
  (:rhs row))


(defn row?
  "true if x is a matrix row."
  [x]
  (s/valid? :meander.matrix.alpha/row x))


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
  :args (s/cat :matrix :meander.matrix.alpha/matrix
               :i nat-int?
               :j nat-int?)
  :ret :meander.matrix.alpha/matrix)


(defn swap-column
  "Swaps column i with column j in the matrix."
  [matrix i j]
  (into [] (map
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
  :args (s/cat :matrix :meander.matrix.alpha/matrix)
  :ret :meander.matrix.alpha/matrix)


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
  :args (s/cat :matrix :meander.matrix.alpha/matrix
               :column (s/coll-of :meander.syntax.alpha/node
                                  :kind sequential?
                                  :into []))
  :ret :meander.matrix.alpha/matrix)


(defn prepend-column
  "Prepends column to matrix."
  [matrix column]
  (into [] (map
             (fn [row col]
               (assoc row :cols (cons col (:cols row))))
             matrix
             column)))


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



;; ---------------------------------------------------------------------
;; Usefulness


(s/fdef useful?
  :args (s/cat :matrix :meander.matrix.alpha/matrix
               :clause (s/coll-of :meander.syntax.alpha/node))
  :ret boolean?)

;; Maranget, L. (2007). Warnings for pattern matching.
;; http://moscova.inria.fr/~maranget/papers/warn/warn.pdf
(defn useful?
  "true if clause is useful with respect to matrix."
  [matrix clause]
  (cond
    ;; Base case 1
    (and (empty? matrix)
         (not (seq clause)))
    false

    ;; Base case 2
    (not (seq clause))
    true

    ;; Induction
    :else
    (let [[node & clause*] clause]
      (if-some [node-children (seq (r.syntax/children node))]
        (let [matrices (specialize-by r.syntax/tag matrix)]
          (if-some [s-matrix (get matrices (r.syntax/tag node))]
            (let [total-node-children (count node-children)
                  d-matrix (sequence
                            (keep
                             (fn [row]
                               (let [[col & cols*] (:cols row)
                                     col-children (r.syntax/children col)]
                                 (when (= (count col-children)
                                          total-node-children)
                                   (assoc row :cols (concat col-children cols*))))))
                            s-matrix)]
              (useful? d-matrix (concat node-children clause*)))
            ;; No tags match.
            (not-any?
             (fn [m-node]
               (case (r.syntax/tag m-node)
                 ;; No variables.
                 (:any :lvr :mvr)
                 true))
             (nth-column matrix 0))))
        ;; Node has no children.
        (or (not-any?
             (fn [m-node]
               (case (r.syntax/tag m-node)
                 ;; No variables.
                 (:any :lvr :mvr)
                 true

                 ;; No equivalent values.
                 (= m-node node)))
             (nth-column matrix 0))
            (useful? (drop-column matrix) clause*))))))
