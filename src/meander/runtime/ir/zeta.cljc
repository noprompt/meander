(ns meander.runtime.ir.zeta
  (:refer-clojure :exclude [test])
  (:require [meander.ir.zeta :as m.ir]))

(defn none []
  (m.ir/data :meander.zeta/none))

(defn bind [f m-state]
  (let [identifier (m.ir/identifier)]
    (m.ir/bind identifier m-state (f identifier))))

(defn call
  ([f then]
   (then (m.ir/call f [])))
  ([f a & rest]
   (let [then (last rest)
         args (into [a] (butlast rest))]
     (then (m.ir/call f args)))))

(defn get-object [state then]
  (let [identifier (m.ir/identifier)]
    (m.ir/let identifier (m.ir/get-object state) (then identifier))))

(defn set-object
  {:style/indent 2}
  [state object then]
  (let [identifier (m.ir/identifier)]
    (m.ir/let identifier object 
      (then (m.ir/set-object state identifier)))))

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
  (m.ir/pick (a) (thunk-b)))

(defn pick
  [a thunk-b]
  (m.ir/pick (a) (thunk-b)))

(defn receive
  {:style/indent 2}
  [state id fold pass fail]
  (get-object state
    (fn [new]
      (let [fold-pass (fn [new]
                        (pass (m.ir/set-binding state (m.ir/identifier id) new)))
            fold-fail (fn [x]
                        (fail state))
            old (m.ir/get-binding state (m.ir/identifier id) (none))]
        (fold old new fold-pass fold-fail)))))

(defn scan
  [f xs]
  (let [identifier (m.ir/identifier)]
    (m.ir/scan identifier xs (f identifier))))

(defn star [f state]
  (let [identifier (m.ir/identifier)]
    (m.ir/star state identifier (fn g [state] (f g identifier)))))

(defn test [test then else]
  (let [identifier (m.ir/identifier)]
    (m.ir/let identifier test
      (m.ir/test identifier (then) (else)))))

(defn with [state mapping then]
  (let [identifier (m.ir/identifier)]
    (m.ir/with state mapping identifier
      (then identifier))))

(defn df-one []
  {:bind bind
   :call call
   :data m.ir/data
   :dual m.ir/dual
   :eval m.ir/code
   :fail m.ir/fail
   ;; :find m.runtime.eval.common/resolve-reference
   :give set-object
   :list m.ir/get-bindings
   :load dispense
   :make m.ir/fabricate
   :mint m.ir/mint
   :none (none)
   :pass m.ir/pass
   :pick pick
   :join join
   :save receive
   :scan scan
   :seed m.ir/seed
   :star star
   :take get-object
   :test test
   :with with})
