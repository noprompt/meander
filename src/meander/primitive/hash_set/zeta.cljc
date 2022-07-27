(ns meander.primitive.hash-set.zeta
  (:require [clojure.core :as clj]
            [clojure.set :as set]
            [meander.algorithms.zeta :as m.algorithms]
            [meander.logic.zeta :as m.logic]
            [meander.protocols.zeta :as m.protocols]
            [meander.state.zeta :as m.state])
  (:refer-clojure :exclude [cast
                            conj
                            empty]))

(defn empty-set? [x]
  (= x #{}))

(defrecord HashSetAny [])

(defrecord HashSetMember [a]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.protocols/-query a (m.logic/check-object ilogic set?)))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/check-object (m.protocols/-yield a ilogic) set?)))


(defrecord HashSetCast [a]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.protocols/-query a (m.logic/check-object ilogic set?)))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/update-object (m.logic/check-object (m.protocols/-yield a ilogic) coll?) set)))


(defrecord HashSetEmpty []
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/check-object ilogic empty-set?))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/set-object ilogic #{})))


(defrecord HashSetConj [s e]
  m.protocols/IQuery
  (m.protocols/-query [this ilogic]
    (m.logic/each ilogic
      (fn [istate0]
        (let [x (m.state/get-object istate0)]
          (if (zero? (count x))
            (m.logic/fail ilogic istate0)
            (m.logic/scan x
              (fn [y]
                (let [rest-x (disj x y)]
                  (m.protocols/-query s (m.logic/set-object (m.protocols/-query e (m.logic/pass ilogic (m.state/set-object istate0 y))) rest-x))))))))))

  m.protocols/IYield
  (m.protocols/-yield [this ilogic]
    (m.logic/each ilogic
      (fn [istate0]
        (let [x (m.state/get-object istate0)]
          (m.logic/update-object (m.protocols/-yield e (m.logic/pass ilogic istate0))
            (fn [y]
              (clj/conj x y))))))))


(defrecord HashSetUnion [s1 s2]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/each ilogic
      (fn [istate0]
        (let [x (m.state/get-object istate0)]
          (m.logic/scan (m.algorithms/set-partitions 2 x)
            (fn [[a b]]
              (m.logic/each (m.protocols/-query s1 (m.logic/pass ilogic (m.state/set-object istate0 a)))
                (fn [istate1]
                  (m.protocols/-query s2 (m.logic/pass ilogic (m.state/set-object istate1 b)))))))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/each (m.protocols/-yield s1 ilogic)
      (fn [istate1]
        (let [x (m.state/get-object istate1)]
          (m.logic/update-object (m.protocols/-yield s2 (m.logic/pass ilogic istate1))
            (fn [y]
              (set/union x y))))))))


(comment
  ;; Set intersection query can potentially return infinitely many solutions.
  (each (hash-set/member ?x)
        (pick (project ?x <s1> ?x)
              (project (hash-set/union ?x (hash-set/any))
                       (each <s1> ?y1) ?y1))
        (pick (project ?x <s2> ?x)
              (project (hash-set/union ?x (hash-set/any))
                       (each <s2> ?y2) ?y2))))

(defrecord HashSetIntersection [s1 s2]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/each ilogic
      (fn [istate0]
        (m.logic/fail ilogic istate0))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/each (m.protocols/-yield s1 ilogic)
      (fn [istate1]
        (let [x (m.state/get-object istate1)]
          (m.logic/update-object (m.protocols/-yield s2 (m.logic/pass ilogic istate1))
            (fn [y]
              (set/intersection x y))))))))


(def ^{:arglists '([])}
  any #'->HashSetAny)

(def ^{:arglists '([])}
  empty #'->HashSetEmpty)

(def ^{:arglists '([a])}
  cast #'->HashSetCast)

(def ^{:arglists '([a])}
  member #'->HashSetMember)

(defn conj
  [s e]
  (->HashSetConj (member s) e))

(defn union [s1 s2]
  (member (->HashSetUnion (member s1) (member s2))))

(defn intersection [s1 s2]
  (member (->HashSetIntersection (member s1) (member s2))))
