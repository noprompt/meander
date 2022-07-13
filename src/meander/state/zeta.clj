(ns meander.state.zeta
  (:require
   [meander.protocols.zeta :as m.protocols]
   [meander.random.zeta :as m.random]))

(defrecord State [object variables references seed random]
  m.protocols/IState
  (-get-object [this]
    (get this :object))

  (-set-object [this new-object]
    (assoc this :object new-object))

  (-get-variable [this variable not-found]
    (get-in this [:variables variable] not-found))

  (-set-variable [this variable value]
    (assoc-in this [:variables variable] value))

  (-get-reference [this reference not-found]
    (get-in this [:references reference] not-found))

  (-set-reference [this reference new-definition]
    (assoc-in this [:references reference] new-definition))

  (-set-random [this]
    (let [r1 (get this :random)
          r2 (nth (m.random/split-n r1 1) 0)
          x (m.random/rand-double r1)]
      (assoc this :object x :random r2))))

(defn make [{:keys [object seed]}]
  (let [seed (or seed (long (rand Long/MAX_VALUE)))
        ;; random (java.util.Random. seed)
        random (m.random/make-random seed)]
    (map->State {:object object
                 :random random
                 :references {}
                 :seed seed
                 :variables {}})))
