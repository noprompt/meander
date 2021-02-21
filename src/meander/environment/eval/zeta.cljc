(ns meander.environment.eval.zeta
  (:refer-clojure :exclude [test])
  (:require
   [clojure.set :as set]
   [meander.util.zeta :as m.util]
   [meander.algorithms.zeta :as m.algorithms]))

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

#?(:cljs
   (defn cljs-eval [form]
     (cond
       (or (keyword? form) (number? form) (string? form))
       form

       (symbol? form)
       (if-some [x (get cljs-symbol-table form)]
         x
         (throw (ex-info "Unable to evaluate symbol" {:symbol form})))

       :else
       (throw (ex-info "Unable to evaluate form" {:form form})))))

(def ^{:private true}
  default-eval
  #?(:clj eval
     :cljs cljs-eval))

(def ^{:private true}
  none ::none)

(defn call
  {:private true}
  ([f x] (f x))
  ([f x y] (f x y))
  ([f x y z] (f x y z))
  ([f x y z & more]
   (apply f x y z more)))

(defn star
  {:private true}
  [f & args]
  (apply (fn loop [& args] (apply f loop args)) args))

(defn find-reference
  {:private true}
  [state id]
  (let [f (get (get state :references) id)]
    (if (fn? f)
      f
      (throw (ex-info "Unbound reference" {:id id, :state state})))))

(defn give-object
  {:private true}
  [state object]
  (assoc state :object object))

(defn load-object
  {:private true}
  [state id unfold pass fail]
  (let [unfold-pass (fn [x new]
                      (pass (give-object (assoc state id new) x)))
        unfold-fail (fn [x]
                      (fail state))
        entry (find state id)
        old (if entry (val entry) none)]
    (unfold old unfold-pass unfold-fail)))

(defn make
  {:private true}
  [state]
  (reify))

(defn mint
  {:private true}
  [state]
  {:object (get state :object)
   :references (get state :references)})

(defn seed
  {:private true}
  [x]
  {:object x})

(defn take-object
  {:private true}
  [state]
  (get state :object))

(defn save
  {:private true}
  [state id fold pass fail]
  (let [new (take-object state)
        fold-pass (fn [new]
                    (pass (assoc state id new)))
        fold-fail (fn [x]
                    (fail state))
        old (if-some [entry (find state id)]
              (val entry)
              none)]
    (fold old new fold-pass fold-fail)))

(defn test
  {:private true}
  [test then else]
  (if test (then) (else)))

(defn list-bindings
  {:private true}
  [state]
  (dissoc state :object :references))

;; Depth First One
;; ---------------------------------------------------------------------

(def depth-first-one
  (letfn [(bind [f x]
            (if x (f x)))

          (data [x]
            x)

          (dual [a b]
            (if (and a b)
              nil
              a))

          (eval [x]
            (default-eval x))

          (fail [state]
            nil)

          (join [a b]
            (or a b))

          (pass [state]
            state)

          (pick [a b]
            (or a b))

          (scan [f xs]
            (reduce
             (fn [_ x]
               (let [state (f x)]
                 (if state
                   (reduced state))))
             nil
             xs))

          (with [state mapping f]
            (let [old-references (get state :references)
                  new-references (merge old-references mapping)]
              (bind (fn [state]
                      (assoc state :references old-references))
                    (f (assoc state :references new-references)))))]
    {:bind bind
     :call call
     :data data
     :dual dual
     :eval eval
     :find find-reference
     :fail fail
     :give give-object
     :join join
     :load load-object
     :list list-bindings
     :make make
     :mint mint
     :none none
     :pass pass
     :pick pick
     :save save
     :scan scan
     :seed seed
     :star star
     :take take-object
     :test test
     :with with}))

;; Depth First All
;; ---------------------------------------------------------------------

(def depth-first-all
  (letfn [(dual [a b]
            (if (and (seq a) (seq b))
              ()
              a))

          (fail [state]
            ())

          (pick [a b]
            (if (seq a) a b))

          (take [state]
            (get state :object))

          (with [state mapping f]
            (f (reduce-kv (fn [m k v] (assoc m k v)) state mapping)))]
    {:bind mapcat
     :call call
     :data identity
     :dual dual
     :eval eval
     :fail fail
     :find find-reference
     :give give-object
     :list list-bindings
     :load load-object
     :make make
     :none none
     :pass list
     :pick pick
     :join concat
     :save save
     :scan mapcat
     :seed seed
     :star star
     :take take-object
     :test test
     :with with}))
