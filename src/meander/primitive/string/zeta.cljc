(ns meander.primitive.string.zeta
  (:refer-clojure :exclude [concat]))

(defrecord AnyString [])

(defrecord Concat [a b])

(def any #'->AnyString)

(def concat #'->Concat)

