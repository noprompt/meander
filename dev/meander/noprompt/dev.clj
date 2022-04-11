(ns meander.noprompt.dev
  (:require [clojure.test :as t]
            [meander.algorithms.zeta :as m.algorithms]
            [meander.primitive.zeta :as m]
            [meander.primitive.string.zeta :as m.str]
            [meander.random.zeta :as m.random])
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
  (-query [pattern logicm]))

(defprotocol IYield
  :extend-via-metadata true
  (-yield [pattern logicm]))

(defprotocol IRedex
  :extend-via-metadata true
  (-redex [pattern logicm]))

(defprotocol IState
  :extend-via-metadata true
  (-get-object [this])
  (-set-object [this new-object])
  (-set-random [this]))

(defprotocol ILogic
  :extend-via-metadata true
  ;; True/In
  (-pass [this state])
  ;; False/Not In
  (-fail [this state])
  ;; And/All/Intersection
  (^{:style/indent 1}
   -each [this f])
  ;; Or/Some/Union
  (-some [this that])
  ;; Pick/XOR
  (-pick [this that])
  ;; Not/Complement
  (^{:style/indent 1}
   -comp [this f]))

;; Implementation
;; ---------------------------------------------------------------------

;; Patterns
;; --------

(extend-type meander.primitive.zeta.Anything
  IQuery
  (-query [this m]
    m)

  IYield
  (-yield [this m]
    (-each m
      (fn [s]
        (-pass m (-set-random s))))))

(extend-type meander.primitive.zeta.Is
  IQuery
  (-query [this m]
    (-each m
      (fn [s]
        (let [x (.-x this)
              y (-get-object s)]
          (if (= x y)
            (-pass m s)
            (-fail m s))))))

  IYield
  (-yield [this m]
    (-each m
      (fn [s]
        (-pass m (-set-object s (.-x this)))))))

(extend-type meander.primitive.zeta.Some
  IQuery
  (-query [this m]
    (-some (-query (.-a this) m)
           (-query (.-b this) m)))

  IYield
  (-yield [this m]
    (-some (-yield (.-a this) m)
           (-yield (.-b this) m))))

(extend-type meander.primitive.zeta.Each
  IQuery
  (-query [this m]
    (-each (-query (.-a this) m)
      (fn [s]
        (-query (.-b this) (-pass m s)))))


  IYield
  (-yield [this m]
    (-some (-each (-yield (.-a this) m)
             (fn [s]
               (let [ms (-pass m s)]
                 (-each (-query (.-b this) (-pass m s))
                   (fn [_] ms)))))
           (-each (-yield (.-b this) m)
             (fn [s]
               (let [ms (-pass m s)]
                 (-each (-query (.-a this) (-pass m s))
                   (fn [_] ms))))))))

(extend-type meander.primitive.zeta.Not
  IQuery
  (-query [this m]
    (-comp m
      (fn [s]
        (-query (.-a this) (-pass m s))))))

;; Interpreter
;; -----------

;; Extended to Clojure values directly.

(extend-type clojure.lang.IPersistentMap
  IState
  (-get-object [this]
    (get this :object))

  (-set-object [this new-object]
    (assoc this :object new-object))

  (-set-random [this]
    (let [r1 (get this :random)
          [r2] (m.random/split-n r1 1)
          x (m.random/rand-long r1)]
      (assoc this :object x :random r2))))

(defn make-state [{:keys [object seed]}]
  (let [seed (or seed (long (rand Long/MAX_VALUE)))
        random (m.random/make-random seed)]
    {:object object
     :random random
     :seed seed}))

(extend-type clojure.lang.ISeq
  ILogic
  (-pass [this state]
    (list state))

  (-fail [this state]
    ())

  (-each [this f]
    (mapcat f this))

  (-some [this that]
    (concat this that))

  (-pick [this that]
    (or (seq this) that))

  (-comp [this f]
    (keep (fn [state]
            (if (seq (f state))
              nil
              state))
          this)))

;; Tests
;; -----
(t/deftest primitive-query-test
  (let [seed (long (rand Long/MAX_VALUE))
        s {:seed seed
           :object 1
           :random (m.random/make-random seed)}
        m (-pass (list) s)]
    (t/is (= (-query (m/anything) m)
             m))

    (t/is (= (-query (m/is 1) m)
             (list s)))

    (t/is (= (-query (m/is 2) m)
             ()))

    (t/is (= (-query (m/some (m/is 1) (m/is 1)) m)
             (list s s)))

    (t/is (= (-query (m/some (m/is 0) (m/is 1)) m)
             (list s)))

    (t/is (= (-query (m/some (m/is 0) (m/is 2)) m)
             ()))

    (t/is (= (-query (m/each (m/is 1) (m/anything)) m)
             (list s)))

    (t/is (= (-query (m/each (m/is 1) (m/is 0)) m)
             ()))

    (t/is (= (-query (m/not (m/is 2)) m)
             (list s)))))

(comment
  (let [s (make-state {:seed 10})
        m (-pass (list) s)]
    (-yield (m/each (m/anything) (m/anything)) m)))

;; Same as above but extended to types we "own".
(comment
  (defrecord State [object]
    IState
    (-get-object [this]
      object)

    (-set-object [this new-object]
      (assoc this :object object)))

  (defrecord DFSLogic [states]
    ILogic
    (-pass [this state]
      (assoc this :states (list state)))

    (-fail [this state]
      (assoc this :states ()))

    (-each [this f]
      (assoc this :states (mapcat (comp :states f) states)))

    (-some [this that]
      (assoc this :states (concat states (:states that))))

    (-pick [this that]
      (if (seq states) this that))

    (-comp [this f]
      (assoc this :states
             (keep (fn [state]
                     (if (seq (:states (f state)))
                       nil
                       state))
                   states))))

  (-query (m/not (m/is 2))
          (-pass (->DFSLogic (list))
                 (->State 1))))
