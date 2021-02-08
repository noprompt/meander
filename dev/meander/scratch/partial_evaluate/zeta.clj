(ns meander.scratch.partial-evaluate.zeta
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
     (clojure.core/assoc {?k _ & ?rest} ?k ?y)
     {?k ?y & ?rest})

    ;; clojure.core/bounded-count
    (rule
     (clojure.core/bounded-count ?bound [& (m/apply ~count [] ?count)])
     (m/again (clojure.core/min ?bound ?count)))

      ;; clojure.core/get
    (rule
     (clojure.core/get (clojure.core/assoc _ ?key ?value) ?key)
     ?value)

    (rule
     (clojure.core/get {?k ?v} ?k)
     ?v)

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

    ;; clojure.core/mapcat
    (rule
     (clojure.core/mapcat (m/again ?f) ())
     ())

    ;; clojure.core/min
    (rule
     (clojure.core/min (m/predicate ~number? ?bound) (m/predicate ~number? ?count))
     (m/again (if (m/apply ~< [?count ?bound]) ?count ?bound)))

    (rule
     (clojure.core/mapcat ?f (m/again (clojure.core/list ?x)))
     (?f ?x))

    ;; clojure.core/list
    (rule
     (clojure.core/list & ((m/again <x) *))
     (clojure.core/list & (<x *)))

    ;; clojure.core/sequential?
    (rule
     (clojure.core/sequential? {})
     false)

    (rule
     (clojure.core/sequential? [& _])
     true)

    ;; if
    (rule
     (if (m/again ?test) (m/again ?then) (m/again ?else))
     (m/one (m/project ?test true ?then)
            (m/project ?test false ?else)
            (if ?test ?then ?else))) 

    ;; fn*
    (rule
     (fn* & (m/one (?name) ()) & ([<p *] (m/again ?body)))
     (fn* & (m/one (?name) ()) & [<p *] ?body))

    ;; let*
    (rule
     (let* [<bindings (m/again <value) *] & ((m/again <body) *))
     (let* [<bindings <value] & (m/one (<body *) ())))

    (rule
     ((m/dual ?x (m/symbol _ _)) ?arg)
     ((m/again ?x) (m/again ?arg)))
    
    ;; Default 
    (rule ?x ?x)]))

(defn partial-evaluate [x]
  (m/run-system partial-evaluation-system m.environment.eval/depth-first-one x))
