(ns meander.runtime.eval.depth-first.zeta
  {:no-doc true}
  (:refer-clojure :exclude [test])
  (:require [meander.runtime.eval.common.zeta :as m.runtime.eval.common]
            [meander.algorithms.zeta :as m.algorithms]))

(def ^{:private true}
  bind-all mapcat)

(defn bind-one
  {:private true}
  [f state]
  (if state (f state)))

(defn dual-all
  {:private true}
  [a b]
  (if (seq a)
    (if (seq b)
      ()
      a)
    ()))

(defn dual-one
  {:private true}
  [a b]
  (if (some? a)
    (if (nil? b)
      a
      nil)
    nil))

(defn fail-all
  {:private true}
  [state]
  ())

(defn fail-one
  {:private true}
  [state]
  nil)

(def ^{:private true}
  pass-all list)

(def ^{:private true}
  pass-one identity)

(defn pick-all
  {:private true}
  [a b]
  (if (seq a) a b))

(defn pick-one
  {:private true}
  [a b]
  (if (some? a) a b))

(defn join-all
  {:private true}
  [thunk-a thunk-b]
  (lazy-cat (thunk-a) (thunk-b)))

(defn join-one
  {:private true}
  [thunk-a thunk-b]
  (if-some [a (thunk-a)]
    a
    (thunk-b)))

(defn scan-one
  {:private true}
  [f xs]
  (reduce
   (fn [fail x]
     (if-some [y (f x)]
       (reduced y)
       fail))
   nil
   xs))


(defn one [& args]
  {:bind bind-one
   :call m.runtime.eval.common/call
   :data identity
   :dual dual-one
   :eval m.runtime.eval.common/platform-eval
   :fail fail-one
   :find m.runtime.eval.common/resolve-reference
   :give m.runtime.eval.common/set-object
   :list m.runtime.eval.common/list-bindings
   :load m.runtime.eval.common/dispense
   :make m.runtime.eval.common/fabricate
   :mint m.runtime.eval.common/mint
   :none m.runtime.eval.common/none
   :pass pass-one
   :pick pick-one
   :join join-one
   :save m.runtime.eval.common/receive
   :scan scan-one
   :seed m.runtime.eval.common/seed
   :star m.runtime.eval.common/star
   :take m.runtime.eval.common/get-object
   :test m.runtime.eval.common/test
   :with m.runtime.eval.common/with})

(defn all [& args]
  {:bind bind-all
   :call m.runtime.eval.common/call
   :data identity
   :dual dual-all
   :eval m.runtime.eval.common/platform-eval
   :fail fail-all
   :find m.runtime.eval.common/resolve-reference
   :give m.runtime.eval.common/set-object
   :list m.runtime.eval.common/list-bindings
   :load m.runtime.eval.common/dispense
   :make m.runtime.eval.common/fabricate
   :mint m.runtime.eval.common/mint
   :none m.runtime.eval.common/none
   :pass pass-all
   :pick pick-all
   :join join-all
   :save m.runtime.eval.common/receive
   :scan bind-all
   :seed m.runtime.eval.common/seed
   :star m.runtime.eval.common/star
   :take m.runtime.eval.common/get-object
   :test m.runtime.eval.common/test
   :with m.runtime.eval.common/with})
