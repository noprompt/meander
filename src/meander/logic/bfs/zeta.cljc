(ns meander.logic.bfs.zeta
  (:require
   [meander.algorithms.zeta :as m.algorithms]
   [meander.protocols.zeta :as m.protocols]))

(def unbound
  (reify))

(deftype BFSLogic [istates]
  m.protocols/ILogic
  (-pass [this istate]
    (BFSLogic. (list istate)))

  (-fail [this state]
    (BFSLogic. ()))

  (-each [this f]
    (BFSLogic. (lazy-seq (m.algorithms/mix* (map (comp deref f) istates)))))

  (-some [this that]
    (if (seq istates)
      (BFSLogic. (m.algorithms/mix istates (.-istates that)))
      that))

  (-pick [this that]
    (if (seq istates) this that))

  (-scan [this f xs]
    (BFSLogic.
     (m.algorithms/mix*
      (map (fn [x]
             (m.algorithms/mix*
              (map (fn [istate]
                     (deref (f istate x)))
                   istates)))
           xs))))

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
    istates)

  #?@(:clj [clojure.lang.Seqable
            (seq [this]
              (seq istates))

            java.lang.Object
            (equals [this that]
              (and (instance? BFSLogic that)
                   (= istates (.-istates ^BFSLogic that))))])

  #?@(:cljs [ISeqable
             (-seq [this]
                   (seq istates))

             IEquiv
             (equals [this that]
               (and (instance? BFSLogic that)
                    (= istates (.-istates ^BFSLogic that))))]))
