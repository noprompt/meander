(ns meander.kernel.eval.breadth-first.zeta
  {:no-doc true}
  (:refer-clojure :exclude [test])
  (:require [meander.kernel.eval.common.zeta :as m.kernel.eval.common]
            [meander.algorithms.zeta :as m.algorithms]))

(defn bind
  {:private true}
  [f m]
  (m.algorithms/mix* (map f m)))

(defn dual
  {:private true}
  [a b]
  (if (seq a)
    (if (seq b)
      ()
      a)))

(defn fail
  {:private true}
  [state]
  ())

(defn pick
  {:private true}
  [a b]
  (if (seq a) a b))

(defn join
  {:private true}
  [thunk-a thunk-b]
  (m.algorithms/mix (thunk-a) (thunk-b)))

(defn scan
  {:private true}
  [f xs]
  (m.algorithms/mix* (map f xs)))

(defn with
  {:style/indent 2}
  [state mapping then]
  (let [old-references (get state :references)
        new-references (assoc state :references (merge mapping old-references))]
    (map (fn [state] (assoc state :references old-references))
         (then (assoc state :references (merge old-references mapping))))))

(defn all [& args]
  {:bind bind
   :call m.kernel.eval.common/call
   :data identity
   :dual dual
   :eval m.kernel.eval.common/platform-eval
   :fail fail
   :find m.kernel.eval.common/resolve-reference
   :give m.kernel.eval.common/set-object
   :list m.kernel.eval.common/list-bindings
   :load m.kernel.eval.common/dispense
   :make m.kernel.eval.common/fabricate
   :mint m.kernel.eval.common/mint
   :none m.kernel.eval.common/none
   :pass list
   :pick pick
   :join join
   :save m.kernel.eval.common/receive
   :scan scan
   :seed m.kernel.eval.common/seed
   :star m.kernel.eval.common/star
   :take m.kernel.eval.common/get-object
   :test m.kernel.eval.common/test
   :with with})
