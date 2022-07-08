(ns meander.logic.zeta
  (:require
   [meander.logic.bfs.zeta :as m.logic.bfs]
   [meander.logic.dff.zeta :as m.logic.dff]))

(defn make-dff [istate]
  (m.logic.dff/->DFFLogic istate))

(defn make-bfs [istate]
  (list istate))
