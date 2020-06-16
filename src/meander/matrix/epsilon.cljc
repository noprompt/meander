(ns ^:no-doc meander.matrix.epsilon
  "Operators for pattern matrices."
  (:refer-clojure :exclude [empty?])
  (:require [clojure.spec.alpha :as s]
            [clojure.set :as set]
            [clojure.spec.gen.alpha :as s.gen]
            [meander.syntax.epsilon :as r.syntax]))


(s/def :meander.matrix.epsilon/matrix
  (s/coll-of :meander.matrix.epsilon/row
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
                      (s/gen :meander.matrix.epsilon/row))))))

(s/def :meander.matrix.epsilon.row/path
  (s/coll-of :meander.syntax.epsilon/node
             :kind sequential?
             :into []))

(s/def :meander.matrix.epsilon.row/refs
  :meander.syntax.epsilon/ref-map)

(s/def :meander.matrix.epsilon.row/ref-specs
  (s/map-of :meander.syntax.epsilon.node/ref
            (s/coll-of map?
                       :kind sequential?
                       :into [])))

(s/def :meander.matrix.epsilon/ref-map
  map?)

(s/def :meander.matrix.epsilon/row
  (s/keys :req-un [:meander.matrix.epsilon.row/cols
                   :meander.matrix.epsilon.row/rhs]
          :opt-un [:meander.matrix.epsilon.row/env
                   :meander.matrix.epsilon.row/refs
                   :meander.matrix.epsilon.row/ref-specs
                   :meander.matrix.epsilon.row/path]))


(s/def :meander.matrix.epsilon.row/cols
  (s/coll-of :meander.syntax.epsilon/node
             :kind sequential?
             :into []))


(s/def :meander.matrix.epsilon.row/rhs
  any?)


(s/def :meander.matrix.epsilon.row/env
  (s/coll-of (s/or :lvr :meander.syntax.epsilon.node/lvr
                   :mut :meander.syntax.epsilon.node/mut
                   :mvr :meander.syntax.epsilon.node/mvr)
             :kind set?
             :into #{}))


(s/def :meander.matrix.epsilon/object
  (s/or :matrix :meander.matrix.epsilon/matrix
        :row :meander.matrix.epsilon/row
        :unknown any?))

(s/def :meander.matrix.epsilon/columns
  (s/coll-of ::r.syntax/node :kind sequential? :into []))


;; ---------------------------------------------------------------------
;; Matrix

(def empty-row
  {:cols []
   :rhs nil
   :env #{}
   :refs {}
   :ref-specs {}})

(defn make-row
  "Given a sequence of `nodes` and some form `action-form`, return a
  matrix row."
  [nodes action-form]
  (assoc empty-row :cols nodes :rhs action-form))

(s/fdef make-row
  :args (s/cat :columns :meander.matrix.epsilon/columns
               :action any?)
  :ret :meander.matrix.epsilon/row)

(defn action [row]
  (:rhs row))

(defn row?
  "true if x is a matrix row."
  [x]
  (s/valid? :meander.matrix.epsilon/row x))


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
  :args (s/cat :matrix :meander.matrix.epsilon/matrix
               :i nat-int?
               :j nat-int?)
  :ret :meander.matrix.epsilon/matrix)


(defn swap-column
  "Swaps column i with column j in the matrix."
  [matrix i j]
  (into [] (map
            (fn [row]
              (update row :cols swap i j)))
        matrix))


(s/fdef subcols
  :args (s/or :a2 (s/cat :matrix :meander.matrix.epsilon/matrix
                         :i nat-int?)
              :a3 (s/cat :matrix :meander.matrix.epsilon/matrix
                         :i nat-int?
                         :j nat-int?))
  :ret :meander.matrix.epsilon/matrix)


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
  :args (s/cat :matrix :meander.matrix.epsilon/matrix)
  :ret nat-int?)


(defn width
  [matrix]
  (count (:cols (first matrix))))


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


(s/fdef nth-column
  :args (s/alt :a2 (s/cat :matrix :meander.matrix.epsilon/matrix
                          :index nat-int?)
               :a3 (s/cat :matrix :meander.matrix.epsilon/matrix
                          :index nat-int?
                          :not-found any?))
  :ret (s/coll-of :meander.syntax.epsilon/node))


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
  :args (s/cat :matrix :meander.matrix.epsilon/matrix)
  :ret :meander.matrix.epsilon/matrix)


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
  :args (s/cat :row :meander.matrix.epsilon/row
               :cells (s/coll-of :meander.syntax.epsilon/node
                                 :kind sequential?
                                 :into []))
  :ret :meander.matrix.epsilon/row)


(defn prepend-cells
  "Prepends `cells` to `row`."
  {:style/indent 1}
  [row cells]
  (assoc row :cols (into (vec cells) (:cols row))))


(s/fdef prepend-column
  :args (s/cat :matrix :meander.matrix.epsilon/matrix
               :column (s/coll-of :meander.syntax.epsilon/node
                                  :kind sequential?
                                  :into []))
  :ret :meander.matrix.epsilon/matrix)


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
                   :args (s/cat :node :meander.syntax.epsilon/node)
                   :ret any?)
               :matrix :meander.matrix.epsilon/matrix)
  :ret (s/map-of :meander.syntax.epsilon.node/tag
                 :meander.matrix.epsilon/matrix))


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
  :args (s/cat :row :meander.matrix.epsilon/row)
  :ret :meander.matrix.epsilon.row/env)


(defn get-env
  [row]
  (or (:env row) #{}))


(defn add-var
  "Add var to the environment in row."
  [row var]
  (update row :env (fnil conj #{}) var))

(s/fdef add-var
  :args (s/cat :row :meander.matrix.epsilon/row
               :var (s/or :lvr :meander.syntax.epsilon.node/lvr
                          :mvr :meander.syntax.epsilon.node/mvr
                          :mut :meander.syntax.epsilon.node/mut))
  :ret :meander.matrix.epsilon/row)

(defn add-vars
  "Add vars to the environment in row."
  [row vars]
  (update row :env (fnil into #{}) vars))


(s/fdef add-vars
  :args (s/cat :row :meander.matrix.epsilon/row
               :vars (s/or
                      :set
                      (s/coll-of (s/or :lvr :meander.syntax.epsilon.node/lvr
                                       :mut :meander.syntax.epsilon.node/mut
                                       :mvr :meander.syntax.epsilon.node/mvr)
                                 :kind set?
                                 :into #{})

                      :sequential
                      (s/coll-of (s/or :lvr :meander.syntax.epsilon.node/lvr
                                       :mut :meander.syntax.epsilon.node/mut
                                       :mvr :meander.syntax.epsilon.node/mvr)
                                :kind sequential?
                                :into #{})))
  :ret :meander.matrix.epsilon/row)

(defn get-var
  "Get var from the environment in row."
  [row var]
  (get (:env row) var))


(s/fdef get-var
  :args (s/cat :row :meander.matrix.epsilon/row
               :var (s/or :lvr :meander.syntax.epsilon.node/lvr
                          :mut :meander.syntax.epsilon.node/mut
                          :mvr :meander.syntax.epsilon.node/mvr))
  :ret (s/nilable
        (s/or :lvr :meander.syntax.epsilon.node/lvr
              :mut :meander.syntax.epsilon.node/mut
              :mvr :meander.syntax.epsilon.node/mvr)))

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

(defn unbound-lvrs
  "Return the set of unbound logic variables in node with respect to
  row."
  [row node]
  (set/difference
   (r.syntax/logic-variables (r.syntax/substitute-refs node (:refs row)))
   (bound-lvrs row)))

(defn unbound-vars
  [row node]
  (set/difference
   (r.syntax/variables (r.syntax/substitute-refs node (get row :refs)))
   (get row :env)))

(defn any-row?
  "`true` if every column in `row` is an `any-node?`, `false`
  otherwise."
  [row]
  (every? r.syntax/any-node? (:cols row)))

(s/fdef any-row?
  :args (s/cat :row :meander.matrix.epsilon/row)
  :ret boolean?)

(defn any-column?
  "`true` if every cell in the nth-column `index` of `matrix` is an
  `any-node?`, `false` otherwise."
  [matrix index]
  (every? r.syntax/any-node? (nth-column matrix index)))

(s/fdef any-column?
  :args (s/cat :matrix :meander.matrix.epsilon/matrix
               :index nat-int?)
  :ret boolean?)
