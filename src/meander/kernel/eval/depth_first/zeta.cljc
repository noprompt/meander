(ns meander.kernel.eval.depth-first.zeta
  {:no-doc true}
  (:refer-clojure :exclude [test])
  (:require [meander.kernel.eval.common.zeta :as m.kernel.eval.common]
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

(defn with-one
  {:style/indent 2}
  [state mapping then]
  (let [old-references (get state :references)
        new-references (merge old-references mapping)]
    (bind-one (fn [state] (assoc state :references old-references))
              (then (assoc state :references new-references)))))

(def ^{:private true}
  pass-all list)

(def ^{:private true}
  pass-one identity)

(defn pick-all
  {:private true}
  [thunk-a thunk-b]
  (let [a (thunk-a)]
    (if (seq a) a (thunk-b))))

(defn pick-one
  {:private true}
  [thunk-a thunk-b]
  (if-some [a (thunk-a)]
    a
    (thunk-b)))

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

(defn with-all
  {:style/indent 2}
  [state mapping then]
  (let [old-references (get state :references)
        new-references (merge old-references mapping)]
    (map (fn [state] (assoc state :references old-references))
         (then (assoc state :references new-references)))))

(defn one [& args]
  {:bind bind-one
   :call m.kernel.eval.common/call
   :data identity
   :dual dual-one
   :eval m.kernel.eval.common/platform-eval
   :fail fail-one
   :find m.kernel.eval.common/resolve-reference
   :give m.kernel.eval.common/set-object
   :list m.kernel.eval.common/list-bindings
   :load m.kernel.eval.common/dispense
   :make m.kernel.eval.common/fabricate
   :mint m.kernel.eval.common/mint
   :none m.kernel.eval.common/none
   :pass pass-one
   :pick pick-one
   :join join-one
   :save m.kernel.eval.common/receive
   :scan some
   :seed m.kernel.eval.common/seed
   :star m.kernel.eval.common/star
   :take m.kernel.eval.common/get-object
   :test m.kernel.eval.common/test
   :with with-one})

(defn all [& args]
  {:bind bind-all
   :call m.kernel.eval.common/call
   :data identity
   :dual dual-all
   :eval m.kernel.eval.common/platform-eval
   :fail fail-all
   :find m.kernel.eval.common/resolve-reference
   :give m.kernel.eval.common/set-object
   :list m.kernel.eval.common/list-bindings
   :load m.kernel.eval.common/dispense
   :make m.kernel.eval.common/fabricate
   :mint m.kernel.eval.common/mint
   :none m.kernel.eval.common/none
   :pass pass-all
   :pick pick-all
   :join join-all
   :save m.kernel.eval.common/receive
   :scan bind-all
   :seed m.kernel.eval.common/seed
   :star m.kernel.eval.common/star
   :take m.kernel.eval.common/get-object
   :test m.kernel.eval.common/test
   :with with-all})
