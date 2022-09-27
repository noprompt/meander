(ns meander.test-helpers
  (:require
   [clojure.test :as t]
   [meander.integer.zeta :as m.integer]
   [meander.logic.bfs.zeta :as m.logic.bfs]
   [meander.logic.dff.zeta :as m.logic.dff]
   [meander.logic.zeta :as m.logic]
   [meander.primitive.zeta :as m.primitive]
   [meander.protocols.zeta :as m.protocols]
   [meander.state.zeta :as m.state])
  #?(:clj
     (:import clojure.lang.ExceptionInfo
              meander.logic.dff.zeta.DFFLogic
              meander.logic.bfs.zeta.BFSLogic)))

(def ^{:arglists '([iquery ilogic])}
  query-unwrap
  (comp deref m.protocols/-query))

(def ^{:arglists '([iyield ilogic])}
  yield-unwrap
  (comp deref m.protocols/-yield))

(def ^{:arglists '([iyield ilogic])}
  redex-unwrap
  (comp deref m.protocols/-redex))

(defn var-factory [qrule yrule]
  (fn [id]
    (m.primitive/variable id qrule yrule)))

;; NOTE: It may be worthwhile to promote this eventually.
(defprotocol IFMap
  (-fmap [this f]))

(defn fmap [f x]
  (-fmap x f))

(extend-protocol IFMap
  DFFLogic
  (-fmap [this f]
    (if (nil? (.-istate this))
      this
      (DFFLogic. (f (.-istate this)))))

  BFSLogic
  (-fmap [this f]
    (BFSLogic. (map f (.-istates this)))))

(defn get-variable
  ([ilogic v]
   (get-variable ilogic v nil))
  ([ilogic v unbound]
   (deref (fmap
           (fn [istate]
             (m.state/get-variable istate v unbound))
           ilogic))))

(defn get-object
  ([ilogic]
   (get-object ilogic nil))
  ([ilogic zero]
   (if (m.logic/zero? ilogic)
     zero
     (deref (fmap
             (fn [istate]
               (m.state/get-object istate))
             ilogic)))))

(defn setup-state [{:keys [object bindings]}]
  (reduce-kv m.state/set-variable (m.state/make {:object object}) bindings))


(defn test-method [f pattern options]
  (let [istate (setup-state options)
        dff-result (f pattern (m.logic/make-dff istate))
        bfs-result (f pattern (m.logic/make-bfs istate))]
    {:dff-result dff-result
     :dff-zero? (m.logic/zero? dff-result)
     :bfs-result bfs-result
     :bfs-zero? (m.logic/zero? bfs-result)}))

(defmacro test-query
  {:arglists '([pattern {:keys [object bindings]} results-binding])
   :style/indent 3}
  [pattern options results & body]
  `(t/testing "-query"
     (let [~results (test-method m.protocols/-query ~pattern ~options)]
      ~@body)))

(defmacro test-yield
  {:arglists '([pattern {:keys [object bindings]} results-binding])
   :style/indent 3}
  [pattern options results & body]
  `(t/testing "-yield"
     (let [~results (test-method m.protocols/-yield ~pattern ~options)]
       ~@body)))

(defmacro test-redex
  {:arglists '([pattern {:keys [object bindings]} results-binding])
   :style/indent 3}
  [pattern options results & body]
  `(t/testing "-redex"
     (let [~results (test-method m.protocols/-redex ~pattern ~options)]
       ~@body)))
