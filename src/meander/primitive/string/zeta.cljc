(ns meander.primitive.string.zeta
  (:refer-clojure :exclude [concat]))

(defrecord Member [])

(defrecord Concat [a b])

(def ^{:arglists '([])
       :doc "Constructor for the pattern which represents an element
  of the set of all strings."}
  member #'->Member)

(def ^{:arglists '([a b])
       :doc "Constructor for the pattern which represents an element
  of the set of all strings which are the concatentation of the string
  described by a and b."}
  concat #'->Concat)

