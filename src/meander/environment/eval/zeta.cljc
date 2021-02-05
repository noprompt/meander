(ns meander.environment.eval.zeta
  (:require
   [clojure.set :as set]
   [meander.algorithms.zeta :as m.algorithms]))

(def default-eval
  #?(:clj eval
     :cljs (fn no-eval [_]
             (throw (ex-info "eval not defined" {})))))

(defn star-eval [f & args]
  (apply (fn loop [& args] (apply f loop args)) args))

(defn star-code [f & args]
  (let [loop__ (gensym "F__")
        args__ (repeatedly (count args) #(gensym "X__"))]
    `((fn ~loop__ [~@args__] ~(apply f loop__ args__)) ~@args)))

;; Depth First One
;; ---------------------------------------------------------------------

(def depth-first-one
  (let [none ::none]
    (letfn [(bind [f x]
              (if x (f x)))

            (call [f & args]
              (apply f args))

            (eval [x]
              (default-eval x))

            (fail [state]
              nil)

            (find [state id]
              (let [f (get (get state :references) id)]
                (if (fn? f)
                  f
                  (throw (ex-info "Unbound reference" {:id id, :state state})))))

            (give [state object]
              (assoc state :object object))

            (join [a b]
              (or a b))

            (load [state id unfold pass fail]
              (let [unfold-pass (fn [x new]
                                  (pass (give (assoc state id new) x)))
                    unfold-fail (fn [x]
                                  (fail state))
                    old (if-some [entry (clojure.core/find state id)]
                          (val entry)
                          none)]
                (unfold old unfold-pass unfold-fail)))

            (make [state]
              (reify))

            (mint [state]
              {:object (get state :object)
               :references (get state :references)})

            (pass [state]
              state)

            (pick [a b]
              (or a b))

            (save [state id fold new pass fail]
              (let [fold-pass (fn [new]
                                (pass (assoc state id new)))
                    fold-fail (fn [x]
                                (fail state))
                    old (if-some [entry (clojure.core/find state id)]
                          (val entry)
                          none)]
                (fold old new fold-pass fold-fail)))

            (scan [f xs]
              (reduce
               (fn [_ x]
                 (let [state (f x)]
                   (if state
                     (reduced state))))
               nil
               xs))

            (seed [x]
              {:object x})

            (take [state]
              (get state :object))

            (test [test then else]
              (if test (then) (else)))

            (with [state mapping f]
              (let [old-references (get state :references)
                    new-references (merge old-references mapping)]
                (bind (fn [state]
                        (assoc state :references old-references))
                      (f (assoc state :references new-references)))))]
      {:bind bind
       :call call
       :code identity
       :eval eval
       :find find
       :fail fail
       :give give
       :join join
       :load load
       :make make
       :mint mint
       :none none
       :pass pass
       :pick pick
       :save save
       :scan scan
       :seed seed
       :star star-eval
       :take take
       :test test
       :with with})))
