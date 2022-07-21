(ns meander.logic.zeta
  (:require
   [meander.logic.bfs.zeta :as m.logic.bfs]
   [meander.logic.dff.zeta :as m.logic.dff]
   [meander.protocols.zeta :as m.protocols]
   [meander.state.zeta :as m.state])
  (:refer-clojure :exclude [comp
                            some
                            zero?]))

(def
  ^{:arglists '([ilogic istate])}
  pass #'m.protocols/-pass)

(def
  ^{:arglists '([ilogic x])}
  fail #'m.protocols/-fail)

(def
  ^{:arglists '([ilogic])}
  unbound #'m.protocols/-unbound)

(def
  ^{:arglists '([ilogic f])
    :style/indent 1}
  each #'m.protocols/-each)

(def
  ^{:arglists '([ilogic f])
    :style/indent 1}
  comp #'m.protocols/-comp)

(def
  ^{:arglists '([ilogic1 ilogic2])}
  pick #'m.protocols/-pick)

(def
  ^{:arglists '([ilogic1 ilogic2])}
  some #'m.protocols/-some)

(defn zero? [ilogic]
  (= ilogic
     (m.protocols/-each ilogic
       (fn [istate]
         (m.protocols/-fail ilogic istate)))))

(defn forget
  {:style/indent 1}
  [ilogic f]
  (each ilogic
    (fn [istate0]
      (each (f istate0)
        (fn [istate1]
          (let [x (m.state/get-object istate1)]
            (pass ilogic (m.state/set-object istate0 x))))))))

(defn make-dff [istate]
  (m.logic.dff/->DFFLogic istate))

(defn make-bfs [istate]
  (m.logic.bfs/->BFSLogic (list istate)))
