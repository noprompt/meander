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

  (-explain [this context]
    this)

  (-unbound [this]
    unbound)

  m.protocols/IFMap
  (-fmap [this f]
    (if istate (DFFLogic. (f istate)) this))

  #?(:clj clojure.lang.IDeref, :cljs IDeref)
  (deref [this]
    istate))

#_
(prefer-method print-method DFFLogic [clojure.lang.IPersistentMap clojure.lang.IDeref])
#_
(prefer-method print-method DFFLogic [java.util.Map clojure.lang.IDeref])
