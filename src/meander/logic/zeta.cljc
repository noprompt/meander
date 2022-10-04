(ns meander.logic.zeta
  (:require
   [meander.logic.bfs.zeta :as m.logic.bfs]
   [meander.logic.dff.zeta :as m.logic.dff]
   [meander.logic.explain.zeta :as m.logic.explain]
   [meander.protocols.zeta :as m.protocols]
   [meander.state.zeta :as m.state]
   [meander.logic.zeta :as m.logic])
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

(defmacro foreach
  {:style/indent 1}
  [bindings body]
  (if (seq bindings)
    (let [[istate ilogic & rest-bindings] bindings]
      (if (= istate :let)
        `(let ~ilogic (foreach ~(vec rest-bindings) ~body))
        `(m.protocols/-each ~ilogic
           (fn [~istate]
             (foreach ~(vec rest-bindings) ~body)))))
    body))

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

(defn scan
  {:style/indent 1}
  [xs f]
  (reduce some (map f xs)))

(defmacro zero? [ilogic]
  `(not (seq ~ilogic)))

(defn forget
  {:style/indent 1}
  [ilogic f]
  (each ilogic
    (fn [istate0]
      (each (f istate0)
        (fn [istate1]
          (let [x (m.state/get-object istate1)]
            (pass ilogic (m.state/set-object istate0 x))))))))

(defn check-object
  {:style/indent 1}
  [ilogic pred]
  (each ilogic
    (fn [istate]
      (let [x (m.state/get-object istate)]
        (if (pred x)
          (pass ilogic istate)
          (fail ilogic istate))))))

(defn set-object
  {:style/indent 1}
  [ilogic x]
  (each ilogic
    (fn [istate]
      (pass ilogic (m.state/set-object istate x)))))

(defn update-object
  {:style/indent 1}
  [ilogic f]
  (each ilogic
    (fn [istate0]
      (pass ilogic (m.state/set-object istate0 (f (m.state/get-object istate0)))))))

(def ^{:arglists '([ilogic context])}
  explain #'m.protocols/-explain)

(defmacro query [ilogic a]
  `(m.protocols/-query ~a ~ilogic))

(defmacro yield [ilogic a]
  `(m.protocols/-yield ~a ~ilogic))

(defmacro redex [ilogic a]
  `(m.protocols/-redex ~a ~ilogic))

(defmacro fmap [ilogic f]
  {:style/indent 1}
  `(m.protocols/-fmap ~ilogic ~f))

(defn ground-values [ilogic a]
  (if (satisfies? m.protocols/IGroundValues a)
    (m.protocols/-ground-values a ilogic)
    (fail ilogic)))

;; Constructors


(defn make-dff [istate]
  (m.logic.dff/->DFFLogic istate))

(defn make-bfs [istate]
  (m.logic.bfs/->BFSLogic (list istate)))

(defn make-dff-explain [istate]
  (m.logic.explain/->ExplainLogic (make-dff istate) nil))

(defn make-bfs-explain [istate]
  (m.logic.explain/->ExplainLogic (make-bfs istate) nil))
