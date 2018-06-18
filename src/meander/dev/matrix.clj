(ns meander.dev.matrix
  "Operators for pattern matrices."
  (:require [clojure.spec.alpha :as s]
            [meander.dev.syntax :as r.syntax]))


(s/def :meander.dev/matrix
  (s/coll-of :meander.dev.matrix/row
             :kind sequential?
             :into []))

(s/def :meander.dev.matrix/row
  (s/keys :req-un [:meander.dev.matrix/cols
                   :meander.dev.matrix/rhs]
          :opt-un [:meander.dev.matrix/env]))


(s/def :meander.dev.matrix/cols
  (s/coll-of r.syntax/node?
             :kind sequential?
             :into []))


(s/def :meander.dev.matrix/rhs
  any?)


(s/def :meander.dev.matrix/env
  (s/coll-of (s/or :var :meander.syntax/var
                   :mem :meander.syntax/mem)
             :kind set?
             :into #{}))


;; ---------------------------------------------------------------------
;; Row/Column


(defn swap
  "Swap elements at positions i and j in the vector v."
  {:private true}
  [v i j]
  (let [v (vec v)]
    (assoc v i (nth v j) j (nth v i))))


(defn swap-column
  "Swaps column i with column j in the matrix."
  [matrix i j]
  (sequence
   (map
    (fn [row]
      (update row :cols swap i j)))
   matrix))


(defn nth-column
  "Get the nth column in row."
  ([row index]
   (nth (:cols row) index))
  ([row index not-found]
   (nth (:cols row) index not-found)))


(defn first-column
  "Return the first column in row."
  [row]
  (nth-column row 0 nil))


(defn rest-columns
  "Return the rest of the columns in row."
  [row]
  (rest (:cols row)))


(defn drop-column
  "Drop the first column in row."
  [row]
  (update row :cols rest))


(defn specialize-by
  "Split matrix into submatrices by the return result of applying f to
  the first column of each row in matrix."
  [f matrix]
  (group-by (comp f first-column) matrix))


;; ---------------------------------------------------------------------
;; Environment


(defn add-sym
  "Add the symbol sym to the environment in row."
  [row sym]
  (update row :env (fnil conj #{}) sym))


(defn get-sym
  "Get the symbol sym from the environment in row."
  [row sym]
  (get (:env row) sym))

