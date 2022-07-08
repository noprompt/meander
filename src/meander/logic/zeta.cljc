(ns meander.logic.zeta
  (:require [meander.logic.dff.zeta :as m.logic.dff]))

(defn make-dff [istate]
  (m.logic.dff/->DFFLogic istate))
