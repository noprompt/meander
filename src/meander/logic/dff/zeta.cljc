(ns meander.logic.dff.zeta
  (:require [meander.protocols.zeta :as m.protocols]))

(def unbound
  (reify))

(defrecord DFFLogic [istate]
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
    (if (get (f istate) :istate)
      (DFFLogic. nil)
      this))

  (-unbound [this]
    unbound)

  m.protocols/IUnwrap
  (-unwrap [this]
    istate)

  #?(:clj clojure.lang.IDeref, :cljs IDeref)
  (deref [this]
    istate))
