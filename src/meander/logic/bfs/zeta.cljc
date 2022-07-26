(ns meander.logic.bfs.zeta
  (:require
   [meander.algorithms.zeta :as m.algorithms]
   [meander.protocols.zeta :as m.protocols]))

(def unbound
  (reify))

(defrecord BFSLogic [istates]
  m.protocols/ILogic
  (-pass [this istate]
    (BFSLogic. (list istate)))

  (-fail [this state]
    (BFSLogic. ()))

  (-each [this f]
    (BFSLogic. (lazy-seq (m.algorithms/mix* (map (comp deref f) istates)))))

  (-some [this that]
    (if (seq istates)
      (if (seq (.-istates that))
        (BFSLogic. (m.algorithms/mix istates (.-istates that)))
        this)
      that))

  (-pick [this that]
    (if (seq istates) this that))

  (-comp [this f]
    (BFSLogic. (keep (fn [state]
                       (if (seq (deref (f state)))
                         nil
                         state))
                     istates)))

  (-unbound [this]
    unbound)

  m.protocols/IFMap
  (-fmap [this f]
    (if (seq istates)
      (BFSLogic. (map f istates))
      this))

  m.protocols/IPure
  (-pure [this x]
    (BFSLogic. (list x)))

  #?(:clj clojure.lang.IDeref, :cljs IDeref)
  (deref [this]
    istates))
