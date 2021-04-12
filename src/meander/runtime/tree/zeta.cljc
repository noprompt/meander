(ns meander.runtime.tree.zeta
  (:refer-clojure :exclude [test])
  (:require [meander.tree.zeta :as m.tree]))

(defn none []
  (m.tree/data :meander.zeta/none))

(defn bind [f m-state]
  (let [identifier (m.tree/identifier)]
    (m.tree/bind identifier m-state (f identifier))))

(defn call
  ([f then]
   (then (m.tree/call f [])))
  ([f a & rest]
   (let [then (last rest)
         args (into [a] (butlast rest))]
     (then (m.tree/call f args)))))

(defn get-object [state then]
  (let [identifier (m.tree/identifier)]
    (m.tree/let identifier (m.tree/get-object state) (then identifier))))

(defn set-object
  {:style/indent 2}
  [state object then]
  (let [identifier (m.tree/identifier)]
    (m.tree/let identifier object 
      (then (m.tree/set-object state identifier)))))

(defn dispense
  {:style/indent 2}
  [state id unfold pass fail]
  (let [unfold-pass (fn [x new]
                      (set-object (assoc state id new) x pass))
        unfold-fail (fn [x]
                      (fail state))
        entry (find state id)
        old (if entry (val entry) (none))]
    (unfold old unfold-pass unfold-fail)))

(defn join
  [a thunk-b]
  (m.tree/pick (a) (thunk-b)))

(defn pick
  [a thunk-b]
  (m.tree/pick (a) (thunk-b)))

(defn receive
  {:style/indent 2}
  [state id fold pass fail]
  (get-object state
    (fn [new]
      (let [fold-pass (fn [new]
                        (pass (m.tree/set-binding state (m.tree/identifier id) new)))
            fold-fail (fn [x]
                        (fail state))
            old (m.tree/get-binding state (m.tree/identifier id) (none))]
        (fold old new fold-pass fold-fail)))))

(defn scan
  [f xs]
  (let [identifier (m.tree/identifier)]
    (m.tree/scan identifier xs (f identifier))))

(defn star [f state]
  (let [identifier (m.tree/identifier)]
    (m.tree/star state identifier (fn g [state] (f g identifier)))))

(defn test [test then else]
  (let [identifier (m.tree/identifier)]
    (m.tree/let identifier test
      (m.tree/test identifier (then) (else)))))

(defn with [state mapping then]
  (let [identifier (m.tree/identifier)]
    (m.tree/with state mapping identifier
      (then identifier))))

(defn df-one []
  {:bind bind
   :call call
   :data m.tree/data
   :dual m.tree/dual
   :eval m.tree/code
   :fail m.tree/fail
   ;; :find m.runtime.eval.common/resolve-reference
   :give set-object
   :list m.tree/get-bindings
   :load dispense
   :make m.tree/fabricate
   :mint m.tree/mint
   :none (none)
   :pass m.tree/pass
   :pick pick
   :join join
   :save receive
   :scan scan
   :seed m.tree/seed
   :star star
   :take get-object
   :test test
   :with with})
