(ns meander.bsless.zeta-kanren
  (:require
   [meander.primitive.zeta :as z]
   [meander.bsless.ukanren :refer [-disj -conj === call-fresh ->LVar]])
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

((-into-goal (z/each (z/some (z/is 1) (z/is 2))
                     (z/is 2))
             {:lvar (->LVar 'x)}) {})
