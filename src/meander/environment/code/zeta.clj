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
