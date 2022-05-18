(ns meander.primitive.hash-set.zeta
  (:refer-clojure :exclude [conj
                            empty]))

(defrecord HashSetAny [])

(defrecord HashSetEmpty [])

(defrecord HashSetConj [s e])

(defrecord HashSetUnion [s1 s2])

(defrecord HashSetIntersection [s1 s2])

(def ^{:arglists '([])}
  any #'->HashSetAny)

(def ^{:arglists '([])}
  empty #'->HashSetEmpty)

(def ^{:arglists '([s x])}
  conj #'->HashSetConj)

(def ^{:arglists '([s1 s2])}
  union #'->HashSetUnion)

(def ^{:arglists '([s1 s2])}
  intersection #'->HashSetIntersection)

