(ns meander.primitive.string.zeta
  (:refer-clojure :exclude [concat]))

(defrecord AnyString [])

(defrecord Concat [a b])

(def any #'->AnyString)

(def ^{:arglists '([a b])
       :doc "Constructor for the pattern which represents an element
  of the set of all strings which are the concatentation of the string
  described by a and b."}
  concat #'->Concat)

