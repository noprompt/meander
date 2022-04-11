(ns meander.bsless.zeta-kanren
  (:require
   [meander.primitive.zeta :as z]
   [meander.bsless.ukanren :refer [-disj -conj === call-fresh ->LVar]])
  (:import
   (meander.primitive.zeta Each Some Is)))

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
  (-into-goal [this ctx] (=== (:lvar ctx) (.-x this))))

((-into-goal (z/each (z/some (z/is 1) (z/is 2))
                     (z/is 2))
             {:lvar (->LVar 'x)}) {})


#_#_
(defprotocol IntoGoal
  (-into-goal [this]))

(extend-protocol IntoGoal
  Each
  (-into-goal [this]
    (-conj (-into-goal (.-a this)) (-into-goal (.-b this))))
  Some
  (-into-goal [this]
    (-disj (-into-goal (.-a this)) (-into-goal (.-b this))))
  Is
  (-into-goal [this] (call-fresh #(=== % (.-x this)))))

#_
((-into-goal (z/each (z/some (z/is 1) (z/is 2))
                     (z/is 2))
             ) {})
