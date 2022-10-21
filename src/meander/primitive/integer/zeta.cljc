(ns meander.primitive.integer.zeta
  (:refer-clojure :exclude [cast - + min max])
  (:require [clojure.core :as clj]
            [meander.algorithms.zeta :as m.algorithms]
            [meander.logic.zeta :as m.logic]
            [meander.primitive.zeta :as m*]
            [meander.protocols.zeta :as m.protocols]
            [meander.state.zeta :as m.state]))


(defmacro check-integer
  {:private true}
  [ilogic]
  `(m.logic/check-object ~ilogic integer?))

;; TODO: Move to logic namespace and reverse parameters
(defmacro yield-integer
  {:private true}
  [p ilogic]
  `(check-integer (m.protocols/-yield ~p ~ilogic)))


(defrecord AnyInteger []
  m.protocols/IQuery
  (-query [this ilogic]
    (check-integer ilogic)))


(defrecord IntegerCast [a]
  m.protocols/IQuery
  (-query [this ilogic]
    (check-integer ilogic))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/foreach [istate0 (m.logic/yield ilogic a)
                      :let [x (m.state/get-object istate0)]]
      (try
        (m.logic/pass ilogic (m.state/set-object istate0 (long x)))
        (catch Exception _
          (m.logic/fail ilogic istate0))))))

(defrecord IntegerMin [a b]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/foreach [istate0 (check-integer ilogic)
                      :let [x (m.state/get-object istate0)]
                      istate1 (yield-integer a (m.logic/pass ilogic istate0))
                      :let [a-val (m.state/get-object istate1)]
                      istate2 (yield-integer b (m.logic/pass ilogic istate0))
                      :let [b-val (m.state/get-object istate2)]]
      (if (= x (clj/min a-val b-val))
        (m.logic/pass ilogic istate0)
        (m.logic/fail ilogic istate0))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/foreach [istate0 (yield-integer a ilogic)
                      :let [a (m.state/get-object istate0)]
                      istate1 (yield-integer b (m.logic/pass ilogic istate0))
                      :let [b (m.state/get-object istate1)]]
      (m.logic/pass ilogic (m.state/set-object istate1 (clj/min a b))))))


(defrecord IntegerMax [a b]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/foreach [istate0 (check-integer ilogic)
                      :let [x (m.state/get-object istate0)]
                      istate1 (yield-integer a (m.logic/pass ilogic istate0))
                      :let [a-val (m.state/get-object istate1)]
                      istate2 (yield-integer b (m.logic/pass ilogic istate0))
                      :let [b-val (m.state/get-object istate2)]]
      (if (= x (clj/max a-val b-val))
        (m.logic/pass ilogic istate0)
        (m.logic/fail ilogic istate0))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/foreach [istate0 (yield-integer a ilogic)
                      :let [a (m.state/get-object istate0)]
                      istate1 (yield-integer b (m.logic/pass ilogic istate0))
                      :let [b (m.state/get-object istate1)]]
      (m.logic/pass ilogic (m.state/set-object istate1 (clj/max a b))))))


(defrecord IntegerInRange [a b]
  m.protocols/IQuery
  (-query [this ilogic]
    ;; NOTE: The implementation given here is incomplete but probably good
    ;; enough for most practical use cases. Rather than yielding a and b, we
    ;; should actually look at x, the object, and see if some integer below x
    ;; successfully queries against a and, likewise, do the same for b but with
    ;; every integer above x.
    (m.logic/foreach [istate0 (m.logic/check-object ilogic integer?)
                      :let [x (m.state/get-object istate0)]
                      istate1 (yield-integer a (m.logic/pass ilogic istate0))
                      :let [a-val (m.state/get-object istate1)]
                      istate2 (if (<= a-val x)
                                (yield-integer b (m.logic/pass ilogic istate1))
                                (m.logic/fail ilogic istate1))
                      :let [b-val (m.state/get-object istate2)]]
      (if (<= x b-val)
        (m.logic/pass ilogic istate0)
        (m.logic/fail ilogic istate0))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/foreach [istate0 (yield-integer a ilogic)
                      :let [a-val (m.state/get-object istate0)]
                      istate1 (yield-integer b (m.logic/pass ilogic istate0))
                      :let [b-val (m.state/get-object istate1)]]
      (if  (<= a-val b-val)
        (reduce
         (fn [ilogic n]
           (m.logic/some ilogic (m.logic/pass ilogic (m.state/set-object istate1 n))))
         (m.logic/pass ilogic (m.state/set-object istate1 a-val))
         (range (inc a-val) (inc b-val)))
        (m.logic/fail ilogic istate1)))))


(defrecord IntegerSum [a b]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/foreach [istate0 (check-integer ilogic)
                      :let [x (m.state/get-object istate0)]]
      (m.logic/scan (m.algorithms/permuted-integer-partitions 2 x)
        (fn [[i j]]
          (m.logic/foreach [istate1 (m.protocols/-query a (m.logic/pass ilogic (m.state/set-object istate0 i)))]
            (m.protocols/-query b (m.logic/pass ilogic (m.state/set-object istate1 j))))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/foreach [istate0 (yield-integer a ilogic)
                      :let [a-val (m.state/get-object istate0)]
                      istate1 (yield-integer b (m.logic/pass ilogic istate0))
                      :let [b-val (m.state/get-object istate1)]]
      (m.logic/pass ilogic (m.state/set-object istate1 (clj/+ a-val b-val))))))

;; x = a - b
;; a = x + b
;; b = a - x
(defrecord IntegerDifference [a b]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/foreach [istate1 (check-integer ilogic)
                      :let [x (m.state/get-object istate1)
                            ilogic1 (m.logic/pass ilogic istate1)]]
      (m.logic/pick
       (m.logic/foreach [istate2 (check-integer (m.logic/ground-values ilogic1 a))
                         :let [a-val (m.state/get-object istate2)
                               b-val (clj/- a-val x)]]
         (m.logic/query (m.logic/pass ilogic (m.state/set-object istate2 b-val)) b))
       (m.logic/pick
        (m.logic/foreach [istate2 (check-integer (m.logic/ground-values ilogic1 b))
                          :let [b-val (m.state/get-object istate2)
                                a-val (clj/+ x b-val)]]
          (m.logic/query (m.logic/pass ilogic (m.state/set-object istate2 a-val)) a))
        (m.protocols/-scan ilogic1
         (fn [istate0 i]
           (let [x (m.state/get-object istate0)]
             (m.logic/foreach [istate1 (-> (m.logic/pass ilogic (m.state/set-object istate0 i))
                                           (m.logic/query a))
                               :let [a-val (m.state/get-object istate1)
                                     b-val (clj/- a-val x)
                                     istate2 (m.state/set-object istate1 b-val)]]
               (m.logic/query (m.logic/pass ilogic istate2) b))))
         (range))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/foreach [istate0 (yield-integer a ilogic)
                      :let [a-val (m.state/get-object istate0)]
                      istate1 (yield-integer b (m.logic/pass ilogic istate0))
                      :let [b-val (m.state/get-object istate1)]]
      (m.logic/pass ilogic (m.state/set-object istate1 (clj/- a-val b-val))))))


(def ^{:arglists '([])}
  any #'->AnyInteger)


(def ^{:arglists '([a b])}
  min #'->IntegerMin)


(def ^{:arglists '([a b])}
  max #'->IntegerMax)


(def ^{:arglists '([min max])}
  in-range #'->IntegerInRange)


(def
  ^{:arglists '([a b])}
  + #'->IntegerSum)


(def
  ^{:arglists '([a b])}
  - #'->IntegerDifference)


(def
  ^{:arglists '([a])}
  cast #'->IntegerCast)
