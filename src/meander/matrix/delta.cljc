(ns meander.matrix.delta
  "Operators for pattern matrices."
  (:refer-clojure :exclude [empty?])
  (:require [clojure.spec.alpha :as s]
            [clojure.set :as set]
            [clojure.spec.gen.alpha :as s.gen]
            [meander.syntax.delta :as r.syntax]))


(s/def :meander.matrix.delta/matrix
  (s/coll-of :meander.matrix.delta/row
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
                      (s/gen :meander.matrix.delta/row))))))

(s/def :meander.matrix.delta.row/path
  (s/coll-of :meander.syntax.delta/node
             :kind sequential?
             :into []))

(s/def :meander.matrix.delta.row/refs
  (s/map-of :meander.syntax.delta.node/ref
            :meander.matrix.delta/ref-map))


(s/def :meander.matrix.delta/row
  (s/keys :req-un [:meander.matrix.delta.row/cols
                   :meander.matrix.delta.row/rhs]
          :opt-un [:meander.matrix.delta.row/env
                   :meander.matrix.delta.row/refs
                   :meander.matrix.delta.row/path]))


(s/def :meander.matrix.delta.row/cols
  (s/coll-of :meander.syntax.delta/node
             :kind sequential?
             :into []))


(s/def :meander.matrix.delta.row/rhs
  any?)


(s/def :meander.matrix.delta.row/env
  (s/coll-of (s/or :lvr :meander.syntax.delta/logic-variable
                   :mvr :meander.syntax.delta/memory-variable)
             :kind set?
             :into #{}))


(s/def :meander.matrix.delta/object
  (s/or :matrix :meander.matrix.delta/matrix
        :row :meander.matrix.delta/row
        :unknown any?))


;; ---------------------------------------------------------------------
;; Matrix

(def empty-row
  {:cols []
   :rhs nil
   :env #{}
   :refs {}
   :ref-specs {}})

(defn make-row
  [cols rhs]
  (assoc empty-row :cols cols :rhs rhs))

(defn action [row]
  (:rhs row))

(defn row?
  "true if x is a matrix row."
  [x]
  (s/valid? :meander.matrix.delta/row x))


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
  :args (s/cat :matrix :meander.matrix.delta/matrix
               :i nat-int?
               :j nat-int?)
  :ret :meander.matrix.delta/matrix)


(defn swap-column
  "Swaps column i with column j in the matrix."
  [matrix i j]
  (into [] (map
            (fn [row]
              (update row :cols swap i j)))
        matrix))


(s/fdef subcols
  :args (s/or :a2 (s/cat :matrix :meander.matrix.delta/matrix
                         :i nat-int?)
              :a3 (s/cat :matrix :meander.matrix.delta/matrix
                         :i nat-int?
                         :j nat-int?))
  :ret :meander.matrix.delta/matrix)


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
  :args (s/cat :matrix :meander.matrix.delta/matrix)
  :ret nat-int?)


(defn width
  [matrix]
  (count (:cols (first matrix))))


(s/fdef nth-column
  :args (s/cat :matrix :meander.matrix.delta/matrix)
  :ret (s/coll-of :meander.syntax.delta/node))


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
  :args (s/cat :matrix :meander.matrix.delta/matrix)
  :ret :meander.matrix.delta/matrix)


(defn drop-column
  "Drop the first column in row."
  [matrix]
  (into [] (map
            (fn [row]
              (if (= (:cols row) [])
                row
                (update row :cols subvec 1))))
        matrix))


(s/fdef prepend-cells
  :args (s/cat :row :meander.matrix.delta/row
               :cells (s/coll-of :meander.syntax.delta/node
                                 :kind sequential?
                                 :into []))
  :ret :meander.matrix.delta/row)


(defn prepend-cells
  "Prepends `cells` to `row`."
  [row cells]
  (assoc row :cols (into (vec cells) (:cols row))))


(s/fdef prepend-column
  :args (s/cat :matrix :meander.matrix.delta/matrix
               :column (s/coll-of :meander.syntax.delta/node
                                  :kind sequential?
                                  :into []))
  :ret :meander.matrix.delta/matrix)


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
                   :args (s/cat :node :meander.syntax.delta/node)
                   :ret any?)
               :matrix :meander.matrix.delta/matrix)
  :ret (s/map-of :meander.syntax.delta.node/tag
                 :meander.matrix.delta/matrix))


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
  :args (s/cat :row :meander.matrix.delta/row)
  :ret :meander.matrix.delta.row/env)


(defn get-env
  [row]
  (or (:env row) #{}))


(s/fdef add-var
  :args (s/cat :row :meander.matrix.delta/row
               :var (s/or :meander.syntax.delta.node/lvr
                          :meander.syntax.delta.node/mvr))
  :ret :meander.matrix.delta/row)

(defn add-var
  "Add var to the environment in row."
  [row var]
  (update row :env (fnil conj #{}) var))

(s/fdef add-vars
  :args (s/cat :row :meander.matrix.delta/row
               :vars (s/coll-of (s/or :meander.syntax.delta.node/lvr
                                      :meander.syntax.delta.node/mvr)
                                :kind sequential?
                                :into #{}))
  :ret :meander.matrix.delta/row)


(defn add-vars
  "Add vars to the environment in row."
  [row vars]
  (update row :env (fnil into #{}) vars))


(s/fdef get-var
  :args (s/cat :row :meander.matrix.delta/row
               :var (s/or :meander.syntax.delta.node/lvr
                          :meander.syntax.delta.node/mvr))
  :ret (s/nilable
        (s/or :meander.syntax.delta.node/lvr
              :meander.syntax.delta.node/mvr)))


(defn get-var
  "Get var from the environment in row."
  [row var]
  (get (:env row) var))

;; TODO: Make mvrs it's own part of the map.
(defn bound-mvrs
  "Return the set of currently bound memory variables in row."
  [row]
  (into #{} (filter r.syntax/mvr-node?) (:env row)))

;; TODO: Make lvrs it's own part of the map.
(defn bound-lvrs
  "Return the set of currently bound logic variables in row."
  [row]
  (into #{} (filter r.syntax/lvr-node?) (:env row)))

(defn unbound-mvrs
  "Return the set of unbound memory variables in node with respect to
  row."
  [row node]
  (set/difference
   (r.syntax/memory-variables (r.syntax/substitute-refs node (:refs row)))
   (bound-mvrs row)))
