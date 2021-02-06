(ns meander.environment.eval.zeta
  (:refer-clojure :exclude [test])
  (:require
   [clojure.set :as set]
   [meander.algorithms.zeta :as m.algorithms]))

(def ^{:private true}
  default-eval
  #?(:clj eval
     :cljs (fn no-eval [_]
             (throw (ex-info "eval not defined" {})))))

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

(defn save
  {:private true}
  [state id fold new pass fail]
  (let [fold-pass (fn [new]
                    (pass (assoc state id new)))
        fold-fail (fn [x]
                    (fail state))
        old (if-some [entry (find state id)]
              (val entry)
              none)]
    (fold old new fold-pass fold-fail)))

(defn seed
  {:private true}
  [x]
  {:object x})

(defn take-object
  {:private true}
  [state]
  (get state :object))

(defn test
  {:private true}
  [test then else]
  (if test (then) (else)))

;; Depth First One
;; ---------------------------------------------------------------------

(def depth-first-one
  (letfn [(bind [f x]
            (if x (f x)))

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
     :dual dual
     :eval eval
     :find find-reference
     :fail fail
     :give give-object
     :join join
     :load load-object
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
     :dual dual
     :eval eval
     :fail fail
     :find find-reference
     :give give-object
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
