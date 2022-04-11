(ns meander.primitive.zeta
  (:require [meander.primitive.string.zeta :as m.primitive.string])
  (:refer-clojure :exclude [not
                            some
                            str]))

(defrecord Anything [])
(defrecord Is [x])
(defrecord Each [a b])
(defrecord Not [a])
(defrecord Pick [a b])
(defrecord Some [a b])
(defrecord Predicate [p])

(def ^{:arglists '([])
       :doc "Constructor for the pattern which represents an element
  of set of all objects."}
  anything #'->Anything)

(def ^{:arglists '([x])
       :doc "Constructor for the pattern which represents an element
  of the set containing only x."}
  is #'->Is)

(def ^{:arglists '([p])
       :doc "Constructor for the pattern which represents an element
  of the complement of the set described by the pattern p."}
  not #'->Not)

(def ^{:arglists '([])
       :doc "Constructor for the pattern which represents an element
  of the empty set e.g. nothing."}
  nothing (comp not anything))

;; Note: Not ready for documentation.
(def predicate
  #'->Predicate)

(defn some
  "Constructor for the pattern which represents an element of the
  union of the sets described by patterns provided."
  ([] (nothing))
  ([a] a)
  ([a b] (->Some a b))
  ([a b & more]
   (apply some (->Some a b) more)))

(defn pick
  "Constructor for the pattern which represents an element of the
  of one of the sets described by patterns provided."
  ([] (nothing))
  ([a] a)
  ([a b] (->Pick a b))
  ([a b & more]
   (apply pick (->Pick a b) more)))

(defn each
  "Constructor for the pattern which represents an element of the
  of the intersection of sets described by patterns provided."
  ([] (anything))
  ([a] a)
  ([a b] (->Each a b))
  ([a b & more]
   (apply each (->Each a b) more)))

(defn str
  "Constructor for the pattern which represents an element of the
  of set of strings described by patterns provided."
  ([] (is ""))
  ([a] (m.primitive.string/concat (is "") a))
  ([a b] (m.primitive.string/concat a b))
  ([a b & more] (apply str (str a b) more)))