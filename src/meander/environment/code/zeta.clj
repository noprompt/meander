(ns meander.environment.code.zeta
  (:require
   [clojure.walk :as walk]
   [meander.core.zeta :as m]
   [meander.environment.eval.zeta :as m.environment.eval]
   [meander.parse.zeta :as m.parse]
   [meander.util.zeta :as m.util]))

(defn constant? [x]
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

(defn truthy-constant? [x]
  (or (true? x)
      (number? x)
      (string? x)
      (keyword? x)
      (vector? x)
      (map? x)
      (set? x)))

(defn falsy-constant? [x]
  (or (false? x)
      (nil? x)))

(defn parse-environment []
  (letfn [(variable-id [sigil name]
            (gensym (str sigil "__")))]
    (assoc (m.util/canonical-ns)
           :variable-id (memoize variable-id))))

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

(defmacro rule [query yield]
  `(let [parse-environment# (parse-environment)]
     (m/rule (m.parse/parse parse-environment# ~(invert-quote query))
             (m.parse/parse parse-environment# ~(invert-quote yield)))))

(def partial-evaluation-system
  (m/one-system
   [ ;; clojure.core/assoc
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

(comment
  (partial-evaluate
   '(let* [X__345992 (clojure.core/= (clojure.core/get (clojure.core/assoc A__345504 :object (clojure.core/get A__345988 :object)) :object) X__345990)]
      (if true
        (clojure.core/assoc (clojure.core/assoc A__345504 :object (clojure.core/get A__345988 :object)) '?__344558 X__345990)
        nil)))

  (partial-evaluate
   '(let* [X__345992 (clojure.core/= (clojure.core/get (clojure.core/assoc A__345504 :object (clojure.core/get A__345988 :object)) :object) X__345990)]
      (if false
        (clojure.core/assoc (clojure.core/assoc A__345504 :object (clojure.core/get A__345988 :object)) '?__344558 X__345990)
        nil))))

