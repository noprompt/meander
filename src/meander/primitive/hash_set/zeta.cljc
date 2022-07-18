(ns meander.primitive.hash-set.zeta
  (:require [clojure.core :as clj]
            [clojure.set :as set]
            [meander.algorithms.zeta :as m.algorithms]
            [meander.protocols.zeta :as m.protocols])
  (:refer-clojure :exclude [conj
                            empty]))

(defrecord HashSetAny [])

(defrecord HashSetCast [a]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.protocols/-each ilogic
      (fn [istate0]
        (let [x (m.protocols/-get-object istate0)]
          (if (set? x)
            (m.protocols/-query a (m.protocols/-pass ilogic istate0))
            (m.protocols/-fail ilogic istate0))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.protocols/-each (m.protocols/-yield ilogic a)
      (fn [istate0]
        (let [x (m.protocols/-get-object istate0)]
          (if (set? x)
            (m.protocols/-query a (m.protocols/-pass ilogic istate0))
            (m.protocols/-fail ilogic istate0)))))))

(defrecord HashSetEmpty []
  m.protocols/IQuery
  (-query [this m]
    (m.protocols/-each m
      (fn [s]
        (let [x (m.protocols/-get-object s)]
          (if (= x #{})
            (m.protocols/-pass m s)
            (m.protocols/-fail m s))))))

  m.protocols/IYield
  (-yield [this m]
    (m.protocols/-each m
      (fn [s]
        (m.protocols/-pass m (m.protocols/-set-object s #{}))))))

(defrecord HashSetConj [s e]
  m.protocols/IQuery
  (m.protocols/-query [this ilogic]
    (m.protocols/-each ilogic
      (fn [istate0]
        (let [x (m.protocols/-get-object istate0)]
          (if (set? x)
            (if (zero? (count x))
              (m.protocols/-fail ilogic istate0)
              (reduce m.protocols/-some
                      (map
                       (fn [y]
                         (let [rest-x (disj x y)]
                           (m.protocols/-each (m.protocols/-query e (m.protocols/-pass ilogic (m.protocols/-set-object istate0 y)))
                             (fn [istate1]
                               (m.protocols/-query s (m.protocols/-pass ilogic (m.protocols/-set-object istate1 rest-x)))))))
                       x)))
            (m.protocols/-fail ilogic istate0))))))

  m.protocols/IYield
  (m.protocols/-yield [this m]
    (m.protocols/-each (m.protocols/-yield (.-s this) m)
      (fn [s]
        (let [x (m.protocols/-get-object s)]
          (if (set? x)
            (m.protocols/-each (m.protocols/-yield (.-e this) (m.protocols/-pass m s))
              (fn [s]
                (let [y (m.protocols/-get-object s)]
                  (m.protocols/-pass m (m.protocols/-set-object s (clj/conj x y))))))
            (m.protocols/-fail m s)))))))

(defrecord HashSetUnion [s1 s2]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.protocols/-each ilogic
      (fn [istate0]
        (let [x (m.protocols/-get-object istate0)]
          (if (set? x)
            (reduce m.protocols/-some
                    (map (fn [[a b]]
                           (m.protocols/-each (m.protocols/-query s1 (m.protocols/-pass ilogic (m.protocols/-set-object istate0 a)))
                             (fn [istate1]
                               (m.protocols/-query s2 (m.protocols/-pass ilogic (m.protocols/-set-object istate1 b))))))
                         (m.algorithms/set-partitions 2 x)))
            (m.protocols/-fail ilogic istate0))))))

  m.protocols/IYield
  (-yield [this m]
    (m.protocols/-each (m.protocols/-yield (.-s1 this) m)
      (fn [s]
        (let [x (m.protocols/-get-object s)]
          (if (set? x)
            (m.protocols/-each (m.protocols/-yield (.-s2 this) (m.protocols/-pass m s))
              (fn [s]
                (let [y (m.protocols/-get-object s)]
                  (if (set? y)
                    (m.protocols/-pass m (m.protocols/-set-object s (set/union x y)))
                    (m.protocols/-fail m s)))))
            (m.protocols/-fail m s)))))))

(defrecord HashSetIntersection [s1 s2]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.protocols/-each ilogic
      (fn [istate0]
        (let [w (m.protocols/-get-object istate0)]
          (if (set? w)
            (m.protocols/-each (m.protocols/-yield s1 (m.protocols/-pass ilogic istate0))
              (fn [istate1]
                (let [x (m.protocols/-get-object istate1)]
                  (if (set? x)
                    (m.protocols/-each (m.protocols/-yield s2 (m.protocols/-pass ilogic istate0))
                      (fn [istate2]
                        (let [y (m.protocols/-get-object istate2)]
                          (if (set? x)
                            (if (every? (fn [e] (or (contains? x e) (contains? y e))) w)
                              (m.protocols/-pass ilogic istate0)
                              (m.protocols/-fail ilogic istate2))
                            (m.protocols/-fail ilogic istate2)))))
                    (m.protocols/-fail ilogic istate1)))))
            (m.protocols/-fail ilogic istate0))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.protocols/-each (m.protocols/-yield s1 ilogic)
      (fn [istate1]
        (let [x (m.protocols/-get-object istate1)]
          (if (set? x)
            (m.protocols/-each (m.protocols/-yield s2 (m.protocols/-pass ilogic istate1))
              (fn [istate2]
                (let [x (m.protocols/-get-object istate2)]
                  (if (set? x)
                    (m.protocols/-pass ilogic istate2)
                    (m.protocols/-fail ilogic istate1)))))
            (m.protocols/-fail ilogic istate1)))))))

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
