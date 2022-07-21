(ns meander.zeta
  (:require
   [clojure.core :as clj]
   [meander.environment.zeta :as m.env]
   [meander.logic.zeta :as m.logic]
   [meander.parse.zeta :as m.parse]
   [meander.primitive.hash-set.zeta :as m.primitive.hash-set]
   [meander.primitive.zeta :as m.primitive]
   [meander.protocols.zeta :as m.protocols]
   [meander.state.zeta :as m.state])
  (:refer-clojure :exclude [apply
                            assoc
                            concat
                            cons
                            hash-map
                            hash-set
                            keyword
                            let
                            list
                            merge
                            not
                            seq
                            set
                            some
                            str
                            symbol
                            vec
                            with-meta]))

;; Primitive operator definitions
;; ---------------------------------------------------------------------

(defn derive-operator-from-function
  {:private true}
  [f]
  (fn [env [_ & args]] (clj/apply f (m.parse/parse-all env args))))

(defmacro def-fn-operator
  {:private true}
  [sym f]
  (clj/let [ns-info (m.env/derive-ns-info &env)
            fq-sym (clj/symbol (name (::m.env/namespace ns-info)) (name sym))]
    `(clj/let [f# (derive-operator-from-function ~f)
               v# (defn ~sym [& args#] (f# nil (clj/cons '~fq-sym args#)))]
       (m.env/operator-add! '~fq-sym (comp reduced f#))
       v#)))

(def ^{:arglists '([id])}
  ? m.primitive/logic-variable)
(m.env/operator-add! `? (fn [env [_ & args]] (reduced (clj/apply ? args))))

(def-fn-operator anything m.primitive/anything)
(def-fn-operator apply m.primitive/apply)
(def-fn-operator assoc m.primitive/assoc)
(def-fn-operator concat m.primitive/concat)
(def-fn-operator cons m.primitive/cons)
(def-fn-operator each m.primitive/each)
(def-fn-operator hash-map m.primitive/hash-map)
(def-fn-operator hash-set m.primitive/hash-set)
(def-fn-operator hash-set-conj* m.primitive.hash-set/conj)
(def-fn-operator intersection* m.primitive.hash-set/intersection)
(def-fn-operator keyword m.primitive/keyword)
(def-fn-operator list m.primitive/list)
(def-fn-operator merge m.primitive/merge)
(def-fn-operator not m.primitive/not)
(def-fn-operator pick m.primitive/pick)
(def-fn-operator project m.primitive/project)
(def-fn-operator rule m.primitive/rule)
(def-fn-operator seq m.primitive/seq)
(def-fn-operator set m.primitive/set)
(def-fn-operator some m.primitive/some)
(def-fn-operator str m.primitive/str)
(def-fn-operator symbol m.primitive/symbol)
(def-fn-operator system (comp m.primitive/system vector))
(def-fn-operator union* m.primitive.hash-set/union)
(def-fn-operator vec m.primitive/vec)
(def-fn-operator ^{:style/indent 1} with m.primitive/with)
(def-fn-operator with-meta m.primitive/with-meta)
(def-fn-operator unbound m.primitive/unbound)

;; Notation/Operator macros
;; ---------------------------------------------------------------------

(defn make-notation
  [env system-form on-zero {:keys [terminal?]}]
  (clj/let [system-term (m.parse/parse env system-form)]
    (if (satisfies? m.protocols/IRedex system-term)
      (fn [form]
        (clj/let [istate (m.state/make {:object form})
                  ilogic (m.logic/make-dff istate)
                  result (m.protocols/-redex system-term ilogic)]
          (if (m.logic/zero? result)
            (on-zero form)
            (clj/let [object (m.protocols/-get-object (m.protocols/-unwrap result))]
              (if terminal?
                (reduced object)
                object)))))
      (throw (ex-info "system-form must parse to an object which satisfies meander.protocols.zeta/IRedex"
                      {:system-form system-form
                       :system-term system-term})))))

(defmacro notation
  ([system-form]
   `(notation ~system-form {}))
  ([system-form {:keys [eval notations terminal?]}]
   `(make-notation (m.env/create {::m.env/eval ~eval
                                 ::m.env/extensions ~(clj/vec notations)})
                  '~system-form
                  identity
                  {:terminal? ~(clj/boolean terminal?)})))

(defmacro defnotation
  ([symbol system-form]
   `(defnotation ~symbol ~system-form {:notations [], :terminal? false}))
  ([symbol system-form {:keys [eval notations terminal?]}]
   `(def ~(clj/with-meta symbol (clj/merge {:arglists ''([form])} (meta symbol)))
      (make-notation (m.env/create {::m.env/eval ~eval
                                    ::m.env/extensions ~(clj/vec notations)})
                     '~system-form
                     identity
                     {:terminal? ~(clj/boolean terminal?)}))))

(defmacro defoperator
  ([symbol system-form]
   `(defoperator ~symbol ~system-form {:notations [], :terminal? false}))
  ([symbol system-form {:keys [eval notations terminal?]}]
   (clj/let [env (m.env/derive-ns-info &env)
             fq-symbol (m.env/qualify-symbol env symbol)]
     `(clj/let [f# (make-notation
                    (m.env/create {::m.env/eval ~eval
                                   ::m.env/extensions ~(clj/vec notations)})
                    '~system-form
                    (fn [form#]
                      (throw (ex-info "Match error" {:form form#, :symbol '~symbol})))
                    {:terminal? ~(clj/boolean terminal?)})
                g# (fn [env# form#] (f# (vary-meta form# clj/merge env#)))]
        (m.env/operator-add! '~fq-symbol g#)
        (defn ~symbol [& ~'forms]
          (f# (clj/cons '~fq-symbol ~'forms)))))))

(defn variable-factory
  [env q-system y-system]
  (clj/let [q-system-term (m.parse/parse env q-system)
            y-system-term (m.parse/parse env y-system)]
    (fn [id]
      (m.primitive/variable id q-system-term y-system-term))))

(defmacro defvariable
  [symbol q-system-form y-system-form {:keys [eval notations]}]
  (clj/let [env (m.env/derive-ns-info &env)
            fq-symbol (m.env/qualify-symbol env symbol)]
    `(clj/let [v# (def ~(clj/with-meta symbol {:arglists ''([id])})
                    (variable-factory (m.env/create {::m.env/eval ~eval
                                                     ::m.env/extensions ~notations})
                                      '~q-system-form
                                      '~y-system-form))]
       (m.env/operator-add! '~fq-symbol (fn [env# [_# & args#]] (reduced (clj/apply ~symbol args#)))))))

;; Symbol notation
;; ---------------

(defnotation
  ^{:doc "Convert symbols that start with \"_\"? into the form (anything)."}
  anything-symbol
  (rule
   (symbol (anything) (str "_" (anything)))
   (apply ~$1 [] (anything)))
  {:eval (fn [x] (case x $1 m.primitive/anything))
   :terminal? true})

(defnotation
  ^{:doc "Convert symbols with names that start with \"?\" into logic
  variables."}
  logic-variable-symbol
  (rule
   (each (? 1) (symbol _ (str "?" _)))
   (apply ~$1 [(? 1)] _))
  {:eval (fn [x] (case x $1 m.primitive/logic-variable))
   :notations [anything-symbol]
   :terminal? true})

(defnotation
  ^{:doc "Convert symbols with names that start with \"%\" into references."}
  reference-symbol
  (rule
   (each ?symbol (symbol _ (str "%" _)))
   (apply ~$1 [?symbol] _))
  {:eval (fn [x] (case x $1 m.primitive/reference))
   :notations [anything-symbol
               logic-variable-symbol]
   :terminal? true})

;; Vector operators and notations
;; ------------------------------

(defnotation vector-as
  (rule
   (vec (concat ?left (cons ::as (some (cons ?x (some () ?right)) ()))))
   (some (`each ?x (vec (some (concat ?left ?right)
                              ?left)))
         ?left))
  {:notations [anything-symbol
               logic-variable-symbol]})

(defnotation vector-rest
  (rule
   (vec (concat ?left
                (cons (symbol (str "&" _))
                      (some (cons ?rest (some () ?right))
                            ()))))
   (some (`vec (`concat ?left ?rest (vec ?right)))
         (`vec (`concat ?left ?rest))
         (vec ?left)))
  {:notations [anything-symbol
               logic-variable-symbol]})

;; Map operators and notation
;; --------------------------

(defnotation hash-map-as
  (rule
   (assoc ?m ::as ?v)
   (`each ?v ?m))
  {:notations [logic-variable-symbol]})

(defnotation hash-map-rest
  (rule
   (assoc ?m (symbol (str "&" _)) ?v)
   (`merge ?m ?v))
  {:notations [anything-symbol
               logic-variable-symbol]})

;; Set operators and notation
;; --------------------------

(defoperator union
  (system
   (rule
    (_) #{})
   (rule
    (_ ?x)
    (`union* #{} ?x))
   (rule
    (_ ?x ?y)
    (`union* ?x ?y))
   (rule
    (cons _ (cons ?x (each ?y (cons _ _))))
    (`union* ?x (cons `union ?y))))
  {:notations [anything-symbol
               logic-variable-symbol]})

(defoperator intersection
  (system
   (rule
    (_) #{})
   (rule
    (_ ?x)
    (`intersection* #{} ?x))
   (rule
    (_ ?x ?y)
    (`intersection* ?x ?y))
   (rule
    (cons _ (cons ?x (each ?y (cons _ _))))
    (`intersection* ?x (cons `intersection ?y))))
  {:notations [anything-symbol
               logic-variable-symbol]})

(defnotation hash-set-as
  (rule
   (union #{(with-meta ?x {::as true & ?rest-meta})} ?s)
   (`each (with-meta ?x ?rest-meta) ?s))
  {:notations [logic-variable-symbol
               hash-map-rest]})

(defnotation hash-set-rest
  (system
   (rule
    #{(with-meta ?x {(symbol _ (str "&" _)) true & ?rest-meta})}
    (`each (with-meta ?x ?rest-meta)))
   (rule
    (union #{(with-meta ?x {(symbol _ (str "&" _)) true & ?rest-meta})} ?s)
    (`union (with-meta ?x ?rest-meta) ?s)))
  {:notations [logic-variable-symbol
               hash-map-rest]})

(defoperator let
  (system
   (rule
    (_ [] ?1)
    ?1)

   (rule
    (_ [?1 ?2 & ?rest] ?3)
    (`project ?2 ?1 (`let ?rest ?3))))
  {:notations [#'anything-symbol
               #'logic-variable-symbol
               #'vector-rest]})

;; Variable definitions and notation
;; ---------------------------------------------------------------------

(defvariable <<
  (system
   (rule [(unbound) ?x]
         [?x])
   (rule [[& ?rest] ?x]
         [& ?rest ?x]))
  (system
   (rule [?x]
         [(unbound) ?x])
   (rule [& ?rest ?x]
         [[& ?rest] ?x]))
  {:notations [logic-variable-symbol
               vector-rest]})

(defnotation <<-symbol
  (rule
   (each ?1 (symbol _ (str "<<" _)))
   (`<< ?1))
  {:notations [anything-symbol
               logic-variable-symbol]})

(defvariable >>
  (system
   (rule [(unbound) ?x]
         (?x))
   (rule [(each ?xs (cons _ _)) ?x]
         (cons ?x ?xs)))
  (system
   (rule (?x)
         [(unbound) ?x])
   (rule (cons ?x ?xs)
         [?xs ?x]))
  {:notations [anything-symbol
               logic-variable-symbol]})

(defnotation >>-symbol
  (rule
   (each ?1 (symbol _ (str ">>" _)))
   (`>> ?1))
  {:notations [anything-symbol
               logic-variable-symbol]})

(defvariable ++
  (system
   (rule [(unbound) _]
         1)
   (rule [?n _]
         (apply ~inc [?n] _)))
  (system
   (rule ?x [?x ?x]))
  {:eval {'inc inc}
   :notations [anything-symbol
               logic-variable-symbol]})

(defnotation ++-symbol
  (rule
   (each ?1 (symbol _ (str "++" _)))
   (`++ ?1))
  {:notations [anything-symbol
               logic-variable-symbol]})

;; Callable Systems
;; ---------------------------------------------------------------------

(defn make-logic
  {:private true}
  [env system-form make-logic]
  (clj/let [system-term (m.parse/parse env system-form)]
    (if (satisfies? m.protocols/IRedex system-term)
      (fn [form]
        (clj/let [istate (m.state/make {:object form})
                  ilogic (make-logic istate)
                  result (m.protocols/-redex system-term ilogic)]
          (m.protocols/-unwrap result)))
      (throw (ex-info "system-form must parse to an object which satisfies meander.protocols.zeta/IRedex"
                      {:system-form system-form
                       :system-term system-term})))))

(def default-notations
  [#'anything-symbol
   #'logic-variable-symbol
   #'hash-map-as
   #'hash-map-rest
   #'vector-as
   #'vector-rest])

(defmacro dff
  ([system]
   `(dff ~system {:notations ~default-notations}))
  ([system {:keys [notations]}]
   `(make-logic (m.env/create {::m.env/extensions ~notations}) '~system m.logic/make-dff)))

(defmacro bfs
  ([system]
   `(bfs ~system {:notations ~default-notations}))
  ([system {:keys [notations]}]
   (make-logic (m.env/create {::m.env/extensions ~notations}) '~system m.logic/make-bfs)))
