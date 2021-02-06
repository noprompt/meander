(ns meander.environment.code.zeta
  (:require [clojure.walk :as walk]
            [meander.core.zeta :as m]
            [meander.environment.eval.zeta :as m.environment.eval]
            [meander.parse.zeta :as m.parse]
            [meander.util.zeta :as m.util]))

;; Utilities
;; ---------------------------------------------------------------------

(defn parse-environment
  {:private true}
  []
  (letfn [(variable-id [sigil name]
            (gensym (str sigil "__")))]
    (assoc (m.util/canonical-ns)
           :variable-id (memoize variable-id))))

(defn parser
  {:private true}
  []
  (m.parse/parser (parse-environment)))

(defn invert-quote
  {:private true}
  [form]
  (m.util/prewalk
   (fn f [x]
     (if (seq? x)
       (cond
         (= 'clojure.core/unquote (first x))
         (reduced (second x))

         (= 'quote (first x))
         (reduced x)

         :else
         (reduced (cons `list (map (partial m.util/prewalk f) x))))
       (if (symbol? x)
         (reduced `(quote ~x))
         x)))
   form))

(defmacro rule
  {:private true}
  [query yield]
  `(let [parse# (m.parse/parser (parse-environment))]
     (m/rule (parse# ~(invert-quote query))
             (parse# ~(invert-quote yield)))))


;; Partial Evaluation
;; ---------------------------------------------------------------------

(defn constant?
  {:private true}
  [x]
  (or (boolean? x)
      (nil? x)
      (number? x)
      (string? x)
      (keyword? x)
      (and (or (vector? x)
               (map? x)
               (set? x))
           (every? constant? x))
      (= x ())))

(defn truthy-constant?
  {:private true}
  [x]
  (or (true? x)
      (number? x)
      (string? x)
      (keyword? x)
      (vector? x)
      (map? x)
      (set? x)))

(defn falsy-constant?
  {:private true}
  [x]
  (or (false? x)
      (nil? x)))

(def ^{:private true}
  partial-evaluation-system
  (m/one-system
   [;; clojure.core/assoc
    (rule
     (clojure.core/assoc (m/again ?map-form) (m/again ?key-form) (m/again ?val-form))
     (m/one
      (m/project (?map-form ?key-form ?val-form)
                 ((clojure.core/assoc ?map ?key _) ?key ?val)
                 (clojure.core/assoc ?map ?key ?val))
      (clojure.core/assoc ?map-form ?key-form ?val-form)))

    ;; clojure.core/get
    (rule
     (clojure.core/get (clojure.core/assoc _ ?key (clojure.core/get ?map ?key)) ?key)
     (clojure.core/get ?map ?key))

    ;; clojure.core/identity
    (rule
     (clojure.core/identity (m/again ?x))
     ?x)

    ;; clojure.core/=
    (rule
     (clojure.core/= (m/again ?x) (m/again ?y))
     (m/one
      ;; ?x and ?y are constants and equal
      (m/project ?x (m/predicate ~constant? ?y) true)
      ;; ?x and ?y are constants and not equal
      (m/project ?x (m/predicate ~constant?) (m/project ?y (m/predicate ~constant?) false))
      ;; One or neither of ?x and ?y are constants
      (clojure.core/= ?x ?y)))

    ;; if
    (rule
     (if (m/again ?test) (m/again ?then) (m/again ?else))
     (m/one (m/project ?test true ?then)
            (m/project ?test false ?else)
            (if ?test ?then ?else))) 

    ;; let*
    (rule
     (let* [<bindings (m/again <value) *] & ((m/again <body) *))
     (let* [<bindings <value] & (m/one (<body *) ())))
    
    ;; Default 
    (rule ?x ?x)]))

(defn partial-evaluate [x]
  (m/run-system partial-evaluation-system m.environment.eval/depth-first-one x))

;; Depth First One
;; ---------------------------------------------------------------------

(def ^{:private true}
  none ::none)

(defn star-code [f & args]
  (let [loop__ (gensym "F__")
        args__ (repeatedly (count args) #(gensym "X__"))]
    `((fn ~loop__ [~@args__] ~(apply f loop__ args__)) ~@args)))

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

(def depth-first-one
  (letfn [(bind [f x]
            (let [state (gensym "A__")]
              (nice-let state x (f state))))

          (call [f & args]
            `(~f ~@args))

          (dual [a b]
            `(if ~a
               (if ~b
                 nil
                 ~a)
               nil))

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
     :dual dual
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
     :with with}))

;; Depth First All
;; ---------------------------------------------------------------------

(def depth-first-all
  (letfn [(bind [f x]
            (let [a__ (gensym "A__")]
              `(mapcat (fn [~a__] ~(f a__)) ~x)))

          (call [f & args]
            `(~f ~@args))

          (dual [a b]
            `(if (seq ~a)
               (if (seq ~b)
                 ()
                 ~a)
               ()))

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
     :dual dual
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
     :with with}))

