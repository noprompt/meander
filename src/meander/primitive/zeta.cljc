(ns meander.primitive.zeta
  (:require [meander.primitive.string.zeta :as m.primitive.string])
  (:refer-clojure :exclude [not
                            str]))

(defrecord Anything [])
(defrecord Is [x])
(defrecord Each [a b])
(defrecord Not [a])
(defrecord Pick [a b])
(defrecord Some [a b])
(defrecord Predicate [p])

(def anything #'->Anything)

(def is #'->Is)

(def not #'->Not)

(def nothing (comp not anything))

(def predicate #'->Predicate)

(defn some
  ([] nothing)
  ([a] a)
  ([a b] (->Some a b))
  ([a b & more]
   (apply some (->Some a b) more)))

(defn pick
  ([] nothing)
  ([a] a)
  ([a b] (->Pick a b))
  ([a b & more]
   (apply pick (->Pick a b) more)))

(defn each
  ([] anything)
  ([a] a)
  ([a b] (->Each a b))
  ([a b & more]
   (apply each (->Each a b) more)))

(defn str
  ([] (is ""))
  ([a] (m.primitive.string/concat (is "") a))
  ([a b] (m.primitive.string/concat a b))
  ([a b & more] (apply str (str a b) more)))
