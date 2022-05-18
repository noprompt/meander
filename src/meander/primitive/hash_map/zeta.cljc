(ns meander.primitive.hash-map.zeta
  (:refer-clojure :exclude [assoc
                            empty
                            merge]))

(defrecord HashMapEmpty [])

(defrecord HashMapAny [])

(defrecord HashMapAssoc [m k v])

(defrecord HashMapMerge [m1 m2])

(defrecord HashMapEntry [k v])

(def
  ^{:arglists '([])}
  empty #'->HashMapEmpty)

(def
  ^{:arglists '([])}
  any #'->HashMapAny)

(def
  ^{:arglists '([m k v])}
  assoc #'->HashMapAssoc)

(def
  ^{:arglists '([m1 m2])}
  merge #'->HashMapMerge)

(def
  ^{:arglists '([k v])}
  entry #'->HashMapEntry)
