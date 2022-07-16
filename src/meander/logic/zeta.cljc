(ns meander.logic.zeta
  (:require
   [meander.logic.bfs.zeta :as m.logic.bfs]
   [meander.logic.dff.zeta :as m.logic.dff]
   [meander.protocols.zeta :as m.protocols])
  (:refer-clojure :exclude [zero?]))

(defn zero? [ilogic]
  (= ilogic
     (m.protocols/-each ilogic
       (fn [istate]
         (m.protocols/-fail ilogic istate)))))

(defn make-dff [istate]
  (m.logic.dff/->DFFLogic istate))

(defn make-bfs [istate]
  (m.logic.bfs/->BFSLogic (list istate)))
