(ns meander.bsless.zeta-kanren
  (:require
   [clojure.test :as t]
   [meander.primitive.zeta :as z]
   [meander.bsless.ukanren :refer [-disj -conj === ->LVar]])
  (:import
   (meander.primitive.zeta Each Some Is Anything)))

(defprotocol IntoGoal
  (-into-goal [this ctx]))

(extend-protocol IntoGoal
  Each
  (-into-goal [this ctx]
    (-conj (-into-goal (.-a this) ctx) (-into-goal (.-b this) ctx)))
  Some
  (-into-goal [this ctx]
    (-disj (-into-goal (.-a this) ctx) (-into-goal (.-b this) ctx)))
  Is
  (-into-goal [this ctx] (=== (:lvar ctx) (.-x this)))
  Anything
  (-into-goal [this ctx] (=== true true)))

(t/deftest is
  (t/testing "Should always succeed"
    (let [x (->LVar 'x)]
      (t/is (= [{x 1}] ((-into-goal (z/is 1) {:lvar x}) {}))))))

(t/deftest disjunction
  (t/testing "Should return one or the other"
    (let [x (->LVar 'x)]
      (t/is (= [{x 1} {x 2}]
               ((-into-goal (z/some (z/is 1) (z/is 2)) {:lvar x}) {}))))))

(t/deftest conjunction
  (t/testing "Conjunction branch eliminates other option"
    (let [x (->LVar 'x)
          expr (z/each (z/some (z/is 1) (z/is 2)) (z/is 2))
          goal (-into-goal expr {:lvar x})]
      (t/is (= [{x 2}] (goal {}))))))

(comment
  (defn ==k->v
    [m k v]
    (fn [s] (=== (get (val (meander.bsless.ukanren/-lookup s (->LVar m))) k)
                (->LVar v))))


  ((-conj (-into-goal (z/is {:x 1}) {:lvar '?root}) (==k->v '?root :x '?x)) {})

  ((-into-goal (z/is {:x 1}) {:lvar '?root}) {}))
