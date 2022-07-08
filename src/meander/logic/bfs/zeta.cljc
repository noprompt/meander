(ns meander.logic.bfs.zeta
  (:require
   [meander.algorithms.zeta :as m.algorithms]
   [meander.protocols.zeta :as m.protocols]))

(def unbound
  (reify))

(extend-type clojure.lang.ISeq
  m.protocols/ILogic
  (-pass [this state]
    (list state))

  (-fail [this state]
    ())

  (-each [this f]
    (lazy-seq (m.algorithms/mix* (map f this))))

  (-some [this that]
    (m.algorithms/mix this that))

  (-pick [this that]
    (or (seq this) that))

  (-comp [this f]
    (keep (fn [state]
            (if (seq (f state))
              nil
              state))
          this))

  (-unbound [this]
    unbound)

  m.protocols/IUnwrap
  (-unwrap [this]
    this))
