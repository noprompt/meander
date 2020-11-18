(ns ^:no-doc meander.matrix.specs.epsilon
  "Operators for pattern matrices."
  (:require [clojure.spec.alpha :as s]
            [clojure.set :as set]
            [clojure.spec.gen.alpha :as s.gen]
            [meander.syntax.specs.epsilon]))

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
  (s/coll-of :meander.syntax.epsilon/node :kind sequential? :into []))

(s/fdef meander.matrix.epsilon/make-row
  :args (s/cat :columns :meander.matrix.epsilon/columns
               :action any?)
  :ret :meander.matrix.epsilon/row)

(s/fdef meander.matrix.epsilon/swap-column
  :args (s/cat :matrix :meander.matrix.epsilon/matrix
               :i nat-int?
               :j nat-int?)
  :ret :meander.matrix.epsilon/matrix)

(s/fdef meander.matrix.epsilon/subcols
  :args (s/or :a2 (s/cat :matrix :meander.matrix.epsilon/matrix
                         :i nat-int?)
              :a3 (s/cat :matrix :meander.matrix.epsilon/matrix
                         :i nat-int?
                         :j nat-int?))
  :ret :meander.matrix.epsilon/matrix)

(s/fdef meander.matrix.epsilon/width
  :args (s/cat :matrix :meander.matrix.epsilon/matrix)
  :ret nat-int?)

(s/fdef meander.matrix.epsilon/nth-column
  :args (s/alt :a2 (s/cat :matrix :meander.matrix.epsilon/matrix
                          :index nat-int?)
               :a3 (s/cat :matrix :meander.matrix.epsilon/matrix
                          :index nat-int?
                          :not-found any?))
  :ret (s/coll-of :meander.syntax.epsilon/node))

(s/fdef meander.matrix.epsilon/drop-column
  :args (s/cat :matrix :meander.matrix.epsilon/matrix)
  :ret :meander.matrix.epsilon/matrix)

(s/fdef meander.matrix.epsilon/prepend-cells
  :args (s/cat :row :meander.matrix.epsilon/row
               :cells (s/coll-of :meander.syntax.epsilon/node
                                 :kind sequential?
                                 :into []))
  :ret :meander.matrix.epsilon/row)

(s/fdef meander.matrix.epsilon/prepend-column
  :args (s/cat :matrix :meander.matrix.epsilon/matrix
               :column (s/coll-of :meander.syntax.epsilon/node
                                  :kind sequential?
                                  :into []))
  :ret :meander.matrix.epsilon/matrix)

(s/fdef meander.matrix.epsilon/specialize-by
  :args (s/cat :f (s/fspec
                   :args (s/cat :node :meander.syntax.epsilon/node)
                   :ret any?)
               :matrix :meander.matrix.epsilon/matrix)
  :ret (s/map-of :meander.syntax.epsilon.node/tag
                 :meander.matrix.epsilon/matrix))

(s/fdef meander.matrix.epsilon/get-env
  :args (s/cat :row :meander.matrix.epsilon/row)
  :ret :meander.matrix.epsilon.row/env)

(s/fdef meander.matrix.epsilon/add-var
  :args (s/cat :row :meander.matrix.epsilon/row
               :var (s/or :lvr :meander.syntax.epsilon.node/lvr
                          :mvr :meander.syntax.epsilon.node/mvr
                          :mut :meander.syntax.epsilon.node/mut))
  :ret :meander.matrix.epsilon/row)

(s/fdef meander.matrix.epsilon/add-vars
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

(s/fdef meander.matrix.epsilon/get-var
  :args (s/cat :row :meander.matrix.epsilon/row
               :var (s/or :lvr :meander.syntax.epsilon.node/lvr
                          :mut :meander.syntax.epsilon.node/mut
                          :mvr :meander.syntax.epsilon.node/mvr))
  :ret (s/nilable
        (s/or :lvr :meander.syntax.epsilon.node/lvr
              :mut :meander.syntax.epsilon.node/mut
              :mvr :meander.syntax.epsilon.node/mvr)))

(s/fdef meander.matrix.epsilon/any-row?
  :args (s/cat :row :meander.matrix.epsilon/row)
  :ret boolean?)

(s/fdef meander.matrix.epsilon/any-column?
  :args (s/cat :matrix :meander.matrix.epsilon/matrix
               :index nat-int?)
  :ret boolean?)
