(ns meander.zeta
  (:require
   [clojure.core :as clj]
   [clojure.string :as string]
   [meander.environment.zeta :as m.env]
   [meander.logic.zeta :as m.logic]
   [meander.parse.zeta :as m.parse]
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
  (clj/let [ns-info (m.env/derive-ns-info &env)]
    `(clj/let [v# (def ~sym ~f)]
       (m.env/operator-add! '~(clj/symbol (name (::m.env/namespace ns-info)) (name sym))
                            (comp reduced (derive-operator-from-function ~f)))
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
(def-fn-operator keyword m.primitive/keyword)
(def-fn-operator list m.primitive/list)
(def-fn-operator merge m.primitive/merge)
(def-fn-operator project m.primitive/project)
(def-fn-operator pick m.primitive/pick)
(def-fn-operator not m.primitive/not)
(def-fn-operator rule m.primitive/rule)
(def-fn-operator seq m.primitive/seq)
(def-fn-operator some m.primitive/some)
(def-fn-operator str m.primitive/str)
(def-fn-operator symbol m.primitive/symbol)
(def-fn-operator system (comp m.primitive/system vector))
(def-fn-operator with m.primitive/with)
(def-fn-operator vec m.primitive/vec)
(def-fn-operator with-meta m.primitive/with-meta)


;; Notation/Operator macros
;; ---------------------------------------------------------------------

(defn notation*
  {:private true}
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

(defmacro defnotation
  ([symbol system-form]
   `(defnotation ~symbol ~system-form {:notations [], :terminal? false}))
  ([symbol system-form {:keys [eval notations terminal?]}]
   `(def ~(clj/with-meta symbol (clj/merge {:arglists ''([form])} (meta symbol)))
      (#'notation* (m.env/create {::m.env/eval ~eval
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
     `(clj/let [f# (#'notation* (m.env/create {::m.env/eval ~eval
                                               ::m.env/extensions ~(clj/vec notations)})
                                '~system-form
                                (fn [form#]
                                  (throw (ex-info "Match error" {:form form#, :symbol '~symbol})))
                                {:terminal? ~(clj/boolean terminal?)})
                g# (fn [env# form#] (f# (vary-meta form# merge env#)))]
        (m.env/operator-add! '~fq-symbol g#)
        (defn ~symbol [& ~'forms]
          (f# (clj/cons '~fq-symbol ~'forms)))))))

;; Notation
;; ---------------------------------------------------------------------

(defnotation
  ^{:doc "Convert symbols that start with \"_\"? into the form (anything)."}
  anything-symbol
  (rule
   (symbol (anything) (str "_" (anything)))
   (list `anything)))

(defnotation
  ^{:doc "Convert symbols with names that start with \"?\" into logic
  variable forms."}
  logic-variable-symbol
  (rule
   (each (? 1) (symbol _ (str "?" _)))
   (list `? (? 1)))
  {:notations [#'anything-symbol]})

(defnotation vector-as
  (rule
   (vec (concat ?left (cons ::as (some (cons ?x (some () ?right)) ()))))
   (some (list `each ?x (vec (some (concat ?left ?right)
                                   ?left)))
         (vec ?left)))
  {:notations [#'anything-symbol
               #'logic-variable-symbol]})

(defnotation vector-rest
  (rule
   (vec (concat ?left
                (cons (symbol (str "&" _))
                      (some (cons ?rest (some () ?right))
                            ()))))
   (some (list `vec (list `concat ?left ?rest (vec ?right)))
         (list `vec (list `concat ?left ?rest))
         (list `vec ?left)))
  {:notations [#'anything-symbol
               #'logic-variable-symbol]})

(defnotation hash-map-as
  (rule
   (assoc ?m ::as ?v)
   (list `each ?v ?m))
  {:notations [#'logic-variable-symbol]})

(defnotation hash-map-rest
  (rule
   (assoc ?m (symbol (str "&" _)) ?v)
   (list `merge ?m ?v))
  {:notations [#'anything-symbol
               #'logic-variable-symbol]})

(defoperator let
  (system
   (rule
    (_ [] ?1)
    ?1)

   (rule
    (_ [?1 ?2 & ?rest] ?3)
    (list `project ?2 ?1 (list `let ?rest ?3))))
  {:notations [#'anything-symbol
               #'logic-variable-symbol
               #'vector-rest]})

(defoperator bool
  (rule
   (_)
   (list `some true false))
  {:notations [#'anything-symbol
               #'logic-variable-symbol
               #'vector-rest]})

(comment
  [(anything-symbol '_)
   (logic-variable-symbol '?x)
   (vector-as '[?x 1 2 ::as 3])
   (hash-map-as '{:foo 1 ::as ?x})
   (hash-map-rest '{:foo "bar" &1 ?x})
   (vector-rest '[?x 1 2 3 & ?rest 1 2])
   (bool)
   (let '[] 1)
   (let '[?x 1] 1)])

(comment
  (m.parse/parse (m.env/create {::m.env/extensions [#'anything-symbol
                                                    #'logic-variable-symbol
                                                    #'hash-map-as
                                                    #'hash-map-rest
                                                    #'vector-as
                                                    #'vector-rest]})
                 '(let [?x 1 ?y 2]
                    {:x ?x, :y ?y})))
