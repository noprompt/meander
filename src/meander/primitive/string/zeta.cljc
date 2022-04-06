(ns meander.primitive.string.zeta
  (:refer-clojure :exclude [concat]))

(defrecord Member [])

(defrecord Concat [a b])

(def member #'->Member)

(def concat #'->Concat)

