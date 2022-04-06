(ns meander.noprompt.dev
  (:require [meander.algorithms.zeta :as m.algorithms]
            [meander.primitive.zeta :as m]
            [meander.primitive.string.zeta :as m.str])
  (:import meander.primitive.zeta.Anything
           meander.primitive.zeta.Each
           meander.primitive.zeta.Is
           meander.primitive.zeta.Not
           meander.primitive.zeta.Pick
           meander.primitive.zeta.Predicate
           meander.primitive.zeta.Some
           meander.primitive.string.zeta.Member
           meander.primitive.string.zeta.Concat))


(defprotocol IQuery
  :extend-via-metadata true
  (query [pattern logic]))

(defprotocol IYield
  :extend-via-metadata true
  (yield [pattern logic]))

(defprotocol IRedex
  :extend-via-metadata true
  (redex [pattren logic]))

;; FAILURE  = empty [A] : M[A]
;; DISJ = plus [A](x: M[A], y: M[A]) : M[A]
;; SUCCESS = pure [A](a: A) : M[A]
;; CONJ = flatMap [A, B](ma: M[A])(f: A => M[B])
(defprotocol IMonadPlus
  :extend-via-metadata true
  ;; Conjunction
  (^{:style/indent 1} bind [this f]))

(defprotocol ILogic
  :extend-via-metadata true
  (pass [logic state])
  (fail [logic state])
  ;; Disjunction
  (some [logic-a logic-b]))

(defprotocol IState
  :extend-via-metadata true
  (geto [state])
  (seto [state x]))

(extend-type meander.primitive.zeta.Anything
  IQuery
  (query [this logic]
    logic)

  IYield
  (yield [this logic]
    logic))

;; (is 1)
(extend-type meander.primitive.zeta.Is
  IQuery
  (query [this logic]
    (bind logic
      (fn [state]
        (let [x (.-x this)
              y (geto state)]
          (if (= x y)
            (pass logic state)
            (fail logic state))))))
                                   
  IYield
  (yield [this logic]
    (let [x (.-x this)]
      (bind logic
        (fn [state]
          (pass logic (seto state x)))))))

(let [object 1

      state
      (with-meta {:object object}
        {`geto :object
         `seto (fn [this x] (assoc this :object x))})

      logic
      (with-meta (list state)
        {`pass (fn [_ state] (list state))
         `fail (constantly ())
         `some concat
         `flat-map (fn [this f]
                     (mapcat f this))})
      p (m/is 10)]
  (yield p logic))
