(ns meander.logic.dff.zeta
  (:require [meander.protocols.zeta :as m.protocols]))

(def unbound
  (reify))

(deftype DFFLogic [istate]
  m.protocols/ILogic
  (-pass [this istate]
    (DFFLogic. istate))

  (-fail [this istate]
    (DFFLogic. nil))

  (-zero [this]
    (nil? istate))

  (-each [this f]
    (if istate (f istate) this))

  (-some [this that]
    (if istate this that))

  (-pick [this that]
    (if istate this that))
  
  (-comp [this f]
    (if (and istate (.-istate ^DFFLogic (f istate)))
      (DFFLogic. nil)
      this))

  (-scan [this f xs]
    (if istate
      (reduce
       (fn [fail-logic x]
         (let [ilogic (f istate x)]
           (if (.-istate ^DFFLogic ilogic)
             (reduced ilogic)
             fail-logic)))
       (DFFLogic. nil)
       xs)
      this))

  (-explain [this context]
    this)

  (-unbound [this]
    unbound)

  m.protocols/IFMap
  (-fmap [this f]
    (if istate (DFFLogic. (f istate)) this))

  m.protocols/IPure
  (-pure [this x]
    (DFFLogic. x))

  #?(:clj clojure.lang.IDeref, :cljs IDeref)
  (deref [this]
    istate))
