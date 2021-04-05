(ns meander.runtime.eval.common.zeta
  {:no-doc true}
  (:refer-clojure :exclude [test])
  (:require [meander.algorithms.zeta :as m.algorithms]))

(def none
  :meander.runtime.eval.zeta/none)

(defn call
  ([f then]
   (then (f)))
  ([f a then]
   (then (f a)))
  ([f a b then]
   (then (f a b)))
  ([f a b c then]
   (then (f a b c)))
  ([f a b c d & more]
   (let [then (last more)]
     (then (apply f a b c d (butlast more))))))

(defn get-object
  {:style/indent 1}
  [state then]
  (then (get state :object none)))

(defn set-object
  {:style/indent 2}
  [state object then]
  (then (assoc state :object object)))

(defn list-bindings
  [state]
  (get state :bindings))

(defn dispense
  {:style/indent 2}
  [state id unfold pass fail]
  (let [unfold-pass (fn [x new]
                      (set-object (assoc-in state [:bindings id] new) x pass))
        unfold-fail (fn [x]
                      (fail state))
        entry (find (get state :bindings) id)
        old (if entry (val entry) none)]
    (unfold old unfold-pass unfold-fail)))

(defn fabricate
  [state]
  (reify))

(defn mint
  [state]
  {:object (get state :object)
   :bindings {}
   :references {}})

(defn receive
  {:style/indent 2}
  [state id fold pass fail]
  (get-object state
    (fn [new]
      (let [fold-pass (fn [new]
                        (pass (assoc-in state [:bindings id] new)))
            fold-fail (fn [x]
                        (fail state))
            old (if-some [entry (find (get state :bindings) id)]
                  (val entry)
                  none)]
        (fold old new fold-pass fold-fail)))))

(defn seed
  [object]
  {:object object
   :bindings {}
   :references {}})

(defn star
  [state f]
  ((fn g [state] (f g state)) state))

(defn test
  [test then else]
  (if test (then) (else)))

(defn resolve-reference
  [state id]
  (let [f (get (get state :references) id)]
    (if (fn? f)
      f
      (throw (ex-info "Unbound reference" {:id id, :state state})))))

(defn with
  {:style/indent 2}
  [state mapping then]
  (then (reduce-kv (fn [m k v] (assoc-in m [:references k] v)) state mapping)))

#?(:cljs
   (def cljs-symbol-table
     {'cljs.core/- cljs.core/-
      'cljs.core/= cljs.core/=
      'cljs.core/apply cljs.core/apply
      'cljs.core/assoc cljs.core/assoc
      'cljs.core/bounded-count cljs.core/bounded-count
      'cljs.core/coll? cljs.core/coll?
      'cljs.core/concat cljs.core/concat
      'cljs.core/conj cljs.core/conj
      'cljs.core/cons cljs.core/cons
      'cljs.core/count cljs.core/count
      'cljs.core/dissoc cljs.core/dissoc
      'cljs.core/fn? cljs.core/fn?
      'cljs.core/key cljs.core/key
      'cljs.core/keyword cljs.core/keyword
      'cljs.core/keyword? cljs.core/keyword?
      'cljs.core/list cljs.core/list
      'cljs.core/map? cljs.core/map?
      'cljs.core/merge cljs.core/merge
      'cljs.core/name cljs.core/name
      'cljs.core/namespace cljs.core/namespace
      'cljs.core/nil? cljs.core/nil?
      'cljs.core/nth cljs.core/nth
      'cljs.core/rest cljs.core/rest
      'cljs.core/seq cljs.core/seq
      'cljs.core/seq? cljs.core/seq?
      'cljs.core/sequential? cljs.core/sequential?
      'cljs.core/str cljs.core/str
      'cljs.core/string? cljs.core/string?
      'cljs.core/subs cljs.core/subs
      'cljs.core/subvec cljs.core/subvec
      'cljs.core/symbol cljs.core/symbol
      'cljs.core/symbol? cljs.core/symbol?
      'cljs.core/val cljs.core/val
      'cljs.core/vec cljs.core/vec
      'cljs.core/vector? cljs.core/vector?
      'meander.algorithms.zeta/map-partitions meander.algorithms.zeta/map-partitions
      'meander.algorithms.zeta/partitions meander.algorithms.zeta/partitions
      'meander.algorithms.zeta/string-partitions meander.algorithms.zeta/string-partitions
      'meander.algorithms.zeta/tail meander.algorithms.zeta/tail}))

#?(:clj
   (def ^{:arglists '([form])}
     platform-eval eval)

   :cljs
   (defn platform-eval [form]
     (cond
       (or (keyword? form) (number? form) (string? form))
       form

       (symbol? form)
       (if-some [x (get cljs-symbol-table form)]
         x
         (throw (ex-info "Unable to evaluate symbol" {:symbol form})))

       :else
       (throw (ex-info "Unable to evaluate form" {:form form})))))
