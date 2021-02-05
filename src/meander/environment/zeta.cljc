(ns meander.environment.zeta
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

(def depth-first-one-eval
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

(def simple-optimize? true)

(defn assoc* [m k v]
  (if (and simple-optimize? (map? m))
    (assoc m k v)
    `(assoc ~m ~k ~v)))

(defn let*-form? [x]
  (and (seq? x)
       (= (first x) 'let*)))

(defn nice-let
  {:style/indent 2}
  [a b body]
  (if simple-optimize?
    (loop [bindings [a b]
           body body]
      (if (and (seq? body)
               (= 'let* (first body)))
        (recur (into bindings (second body))
               (nth body 2))
        `(let* ~bindings ~body)))
    `(let* [~a ~b]
       ~body)))

(def depth-first-one-code
  (let [none ::none]
    (letfn [(bind [f x]
              (let [state (gensym "A__")]
                (nice-let state x (f state))))

            (call [f & args]
              `(~f ~@args))

            (code [x]
              `(quote ~x))

            (eval [x]
              x)

            (fail [state]
              nil)

            (find [state id]
              id)

            (give [state object]
              (assoc* state :object object))

            (join [a b]
              (let [x (gensym "X__")]
                (nice-let x a `(if ~x ~x ~b))))

            (load [state id unfold pass fail]
              (let [pass* (fn [x new]
                            (pass (give (assoc* state `(quote ~id) new) x)))
                    fail* (fn [x]
                            (fail state))]
                (if (and simple-optimize? (map? state))
                  (let [entry (clojure.core/find state id)
                        old (if entry (val entry) none)]
                    (unfold old pass* fail*))
                  (let [entry (gensym "E__")
                        old (gensym "X__")]
                    (nice-let entry `(clojure.core/find ~state '~id)
                      (nice-let old `(if ~entry (val ~entry) ~none)
                        (unfold old pass* fail*)))))))

            (make [state]
              `(reify))

            (mint [state]
              {:object `(get state :object)})

            (pass [state]
              state)

            (pick [a b]
              (let [x (gensym "X__")]
                (nice-let x a `(if ~x ~x ~b))))

            (save [state id fold new pass fail]
              (let [pass* (fn [new]
                            (pass (assoc* state `(quote ~id) new)))
                    fail* (fn [x]
                            (fail state))]
                (if (and simple-optimize? (map? state))
                  (let [entry (clojure.core/find state id)
                        old (if entry (val entry) none)]
                    (fold old new pass* fail*))
                  (let [entry (gensym "E__")
                        old (gensym "X__")]
                    (nice-let entry `(clojure.core/find ~state '~id)
                      (nice-let old `(if ~entry (val ~entry) ~none)
                        (fold old new pass* fail*)))))))

            (scan [f xs]
              (let [state (gensym "S__")
                    _ (gensym "X__")
                    y (gensym "X__")]
                `(reduce (fn* [~_ ~y]
                              (let* [~state ~(f y)]
                                (if ~state
                                  (reduced ~state)
                                  nil)))
                         nil
                         ~xs)))

            (seed [x]
              {:object x})

            (take [state]
              (if (and simple-optimize? (map? state))
                (get state :object)
                `(get ~state :object)))

            (test [test then else]
              (let [x (gensym "X__")]
                (nice-let x test `(if ~x ~(then) ~(else)))))

            (with [state mapping f]
              `(letfn [~@(map (fn [[id g]]
                                (let [state (gensym "X__")]
                                  (list id [state] (g state))))
                           mapping)]
                 ~(f state)))]
      {:bind bind
       :call call
       :code code
       :eval eval
       :fail fail
       :find find
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
       :star star-code
       :take take
       :test test
       :with with})))

;; Depth First Search
;; ---------------------------------------------------------------------

(def depth-first-all-eval
  (let [none ::none]
    (letfn [(call
              ([f x] (f x))
              ([f x y] (f x y))
              ([f x y z] (f x y z))
              ([f x y z & more]
               (apply f x y z more)))

            (fail [state]
              ())

            (give [state object]
              (assoc state :object object))

            (load [state id unfold pass fail]
              (let [pass* (fn [x new]
                            (pass (give (assoc state id new) x)))
                    fail* (fn [x]
                            (fail state))
                    entry (find state id)
                    old (if entry (val entry) none)]
                (unfold old pass* fail*)))

            (make [state]
              (reify))

            (pick [a b]
              (if (seq a) a b))

            (save [state id fold new pass fail]
              (let [pass* (fn [new]
                            (pass (assoc state id new)))
                    fail* (fn [x]
                            (fail state))
                    old (if-some [entry (find state id)]
                          (val entry)
                          none)]
                (fold old new pass* fail*)))

            (seed [x]
              {:object x})
           
            (take [state]
              (get state :object))

            (test [x pass fail]
              (if x (pass) (fail)))

            (with [state mapping f]
              (f (reduce-kv (fn [m k v] (assoc m k v)) state mapping)))]
      {:bind mapcat
       :call call
       :code identity
       :eval eval
       :fail fail
       :give give
       :load load
       :make make
       :none none
       :pass list
       :pick pick
       :join concat
       :save save
       :scan mapcat
       :seed seed
       :star star-eval
       :take take
       :test test
       :with with})))

(def depth-first-all-code
  (let [none ::none]
    (letfn [(bind [f x]
              (let [a__ (gensym "A__")]
                `(mapcat (fn [~a__] ~(f a__)) ~x)))

            (call [f & args]
              `(~f ~@args))

            (code [x]
              `(quote ~x))

            (eval [x]
              x)

            (fail [state]
              ())

            (give [state object]
              `(assoc ~state :object ~object))

            (join [a b]
              `(concat ~a ~b))

            (load [state id unfold pass fail]
              (let [entry__ (gensym "X__")
                    old__ (gensym "X__")]
                (let [pass* (fn [x new]
                              (pass (give `(assoc ~state '~id ~new) x)))
                      fail* (fn [x]
                              (fail state))]
                  `(let [~entry__ (find ~state '~id)
                         ~old__ (if ~entry__ (val ~entry__) ~none)]
                     ~(unfold old__ pass* fail*)))))

            (make [state]
              `(reify))

            (pass [state]
              `(list ~state))

            (pick [a b]
              (let [m-a (gensym "M__")]
                `(let [~m-a ~a]
                   (if (seq ~m-a)
                     ~m-a
                     ~b))))

            (save [state id fold new pass fail]
              (let [entry__ (gensym "E__")
                    old__ (gensym "X__")
                    pass* (fn [new]
                            (pass `(assoc ~state '~id ~new)))
                    fail* (fn [x]
                            (fail state))]
                `(let [~entry__ (find ~state '~id)
                       ~old__ (if ~entry__ (val ~entry__) ~none)]
                   ~(fold old__ new pass* fail*))))

            (scan [f x]
              (let [y (gensym "X__")]
                `(mapcat (fn [~y] ~(f y)) ~x)))

            (seed [x]
              {:object x})

            (take [state]
              `(get ~state :object))

            (test [test then else]
              `(if ~test ~(then) ~(else)))

            (with [state mapping f]
              `(letfn [~@(map
                           (fn [[id g]]
                             (g (gensym "S__")))
                           mapping)]
                 ~(f state)))]
      {:bind bind
       :call call
       :code code
       :eval eval
       :fail fail
       :give give
       :join join
       :load load
       :make make
       :none none
       :pass pass
       :pick pick
       :save save
       :scan scan
       :seed seed
       :star star-code
       :take take
       :test test
       :with with})))
