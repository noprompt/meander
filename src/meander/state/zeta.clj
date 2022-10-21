(ns meander.state.zeta
  (:require
   [meander.protocols.zeta :as m.protocols]
   [meander.random.zeta :as m.random]))


(def ^{:arglists '([istate])}
 get-object m.protocols/-get-object)

(def ^{:arglists '([istate new-object])}
 set-object m.protocols/-set-object)

(def ^{:arglists '([istate variable unbound])}
 get-variable m.protocols/-get-variable)

(def ^{:arglists '([istate variable value])}
 set-variable m.protocols/-set-variable)

(def ^{:arglists '([istate variable])}
 unset-variable m.protocols/-unset-variable)

(def ^{:arglists '([istate])}
 clear-variables m.protocols/-clear-variables)

(def ^{:arglists '([istate reference not-found])}
 get-reference m.protocols/-get-reference)

(def ^{:arglists '([istate reference new-definition])}
 set-reference m.protocols/-set-reference)

(def ^{:arglists '([istate])}
 set-random m.protocols/-set-random)

(defrecord State [object variables references seed random]
  m.protocols/IState
  (-get-object [this]
    object)

  (-set-object [this new-object]
    (State. new-object variables references seed random))

  (-get-variable [this variable not-found]
    (get variables variable not-found))

  (-set-variable [this variable value]
    (State. object (assoc variables variable value) references seed random))

  (-unset-variable [this variable]
    (State. object (dissoc variables variable) references seed random))

  (-clear-variables [this]
    (State. object {} references seed random))

  (-get-reference [this reference not-found]
    (get references reference not-found))

  (-set-reference [this reference new-definition]
    (State. object variables (assoc references reference new-definition) seed random))

  (-set-random [this]
    (let [r1 (get this :random)
          r2 (nth (m.random/split-n r1 1) 0)
          x (m.random/rand-double r1)]
      (State. x variables references seed r2))))

(defn make [{:keys [object seed]}]
  (let [seed (or seed (long (rand Long/MAX_VALUE)))
        ;; random (java.util.Random. seed)
        random (m.random/make-random seed)]
    (map->State {:object object
                 :random random
                 :references {}
                 :seed seed
                 :variables {}})))
