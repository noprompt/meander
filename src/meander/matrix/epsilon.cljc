(ns ^:no-doc meander.matrix.epsilon
  "Operators for pattern matrices."
  (:refer-clojure :exclude [empty?])
  (:require [clojure.set :as set]
            [meander.syntax.epsilon :as r.syntax]))


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

(defn action [row]
  (:rhs row))

(defn row?
  "true if x is a matrix row."
  [x]
  (and (map? x)
       (let [cols (get x :cols)]
         (and (seq? x)
              (every? r.syntax/node? cols)))
       (let [env (get x :env)]
         (or (nil? env)
             (set? env)))
       (let [refs (get x :refs)]
         (or (nil? refs)
             (map? refs)))
       (let [ref-specs (get x :ref-specs)]
         (or (nil? ref-specs)
             (and (sequential? ref-specs) (every? map? ref-specs))))
       (let [path (get x :path)]
         (or (nil? path)
             (and (sequential? path) (every? map? path))))))

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


(defn swap-column
  "Swaps column i with column j in the matrix."
  [matrix i j]
  (into [] (map
            (fn [row]
              (update row :cols swap i j)))
        matrix))


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


(defn drop-column
  "Drop the first column in row."
  [matrix]
  (into [] (map
            (fn [row]
              (if (= (:cols row) [])
                row
                (update row :cols subvec 1))))
        matrix))


(defn prepend-cells
  "Prepends `cells` to `row`."
  {:style/indent 1}
  [row cells]
  (assoc row :cols (into (vec cells) (:cols row))))


(defn prepend-column
  "Prepends column to matrix."
  [matrix column]
  (into [] (map
             (fn [row cell]
               (assoc row :cols (into [cell] (:cols row))))
             matrix
             column)))


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

(defn get-env
  [row]
  (or (:env row) #{}))


(defn add-var
  "Add var to the environment in row."
  [row var]
  (update row :env (fnil conj #{}) var))

(defn add-vars
  "Add vars to the environment in row."
  [row vars]
  (update row :env (fnil into #{}) vars))

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

(defn any-column?
  "`true` if every cell in the nth-column `index` of `matrix` is an
  `any-node?`, `false` otherwise."
  [matrix index]
  (every? r.syntax/any-node? (nth-column matrix index)))
