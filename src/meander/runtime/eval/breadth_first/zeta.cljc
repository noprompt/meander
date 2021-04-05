(ns meander.runtime.eval.breadth-first.zeta
  {:no-doc true}
  (:refer-clojure :exclude [test])
  (:require [meander.runtime.eval.common.zeta :as m.runtime.eval.common]
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

(defn all [& args]
  {:bind bind
   :call m.runtime.eval.common/call
   :data identity
   :dual dual
   :eval m.runtime.eval.common/platform-eval
   :fail fail
   :find m.runtime.eval.common/resolve-reference
   :give m.runtime.eval.common/set-object
   :list m.runtime.eval.common/list-bindings
   :load m.runtime.eval.common/dispense
   :make m.runtime.eval.common/fabricate
   :mint m.runtime.eval.common/mint
   :none m.runtime.eval.common/none
   :pass list
   :pick pick
   :join join
   :save m.runtime.eval.common/receive
   :scan scan
   :seed m.runtime.eval.common/seed
   :star m.runtime.eval.common/star
   :take m.runtime.eval.common/get-object
   :test m.runtime.eval.common/test
   :with m.runtime.eval.common/with})
