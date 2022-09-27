(ns meander.zeta
  (:require
   [clojure.core :as clj]
   [meander.environment.zeta :as m.env]
   [meander.logic.zeta :as m.logic]
   [meander.parse.zeta :as m.parse]
   [meander.primitive.hash-set.zeta :as m.primitive.hash-set]
   [meander.primitive.zeta :as m.primitive]
   [meander.private.zeta :as m.private]
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

(m.private/def-fn-operator anything m.primitive/anything)
(m.private/def-fn-operator apply m.primitive/apply)
(m.private/def-fn-operator assoc m.primitive/assoc)
(m.private/def-fn-operator concat m.primitive/concat)
(m.private/def-fn-operator cons m.primitive/cons)
(m.private/def-fn-operator each m.primitive/each)
(m.private/def-fn-operator explain* m.primitive/explain)
(m.private/def-fn-operator hash-map m.primitive/hash-map)
(m.private/def-fn-operator hash-set* m.primitive/hash-set)
(m.private/def-fn-operator set m.primitive.hash-set/cast)
(m.private/def-fn-operator keyword m.primitive/keyword)
(m.private/def-fn-operator list m.primitive/list)
(m.private/def-fn-operator merge m.primitive/merge)
(m.private/def-fn-operator not m.primitive/not)
(m.private/def-fn-operator pick m.primitive/pick)
(m.private/def-fn-operator project m.primitive/project)
(m.private/def-fn-operator rule m.primitive/rule)
(m.private/def-fn-operator seq m.primitive/seq)
(m.private/def-fn-operator set m.primitive/set)
(m.private/def-fn-operator some m.primitive/some)
(m.private/def-fn-operator str m.primitive/str)
(m.private/def-fn-operator symbol m.primitive/symbol)
(m.private/def-fn-operator system (comp m.primitive/system vector))
(m.private/def-fn-operator unbound m.primitive/unbound)
(m.private/def-fn-operator vec m.primitive/vec)
(m.private/def-fn-operator with-meta m.primitive/with-meta)

(def ^{:arglists '([id])}
  ? m.primitive/logic-variable)
(m.env/operator-add! `? (fn [env [_ & args]] (reduced (clj/apply ? args))))

(def ^{:arglists '([id])}
  % m.primitive/reference)
(m.env/operator-add! `% (fn [env [_ & args]] (reduced (clj/apply % args))))

(m.env/operator-add! `with*
  (fn [env [_ index body]]
    (reduced
     (m.primitive/with
      (reduce-kv (fn [m k v]
                   (clj/assoc m (m.parse/parse env k) (m.parse/parse env v)))
                 {}
                 index)
      (m.parse/parse env body)))))

;; Notation/Operator macros
;; ---------------------------------------------------------------------

(defmacro notation
  {:arglists '([system-form]
               [system-form {:keys [eval notations terminal?]}])}
  ([system-form]
   `(notation ~system-form {}))
  ([system-form options]
   `(m.private/make-notation (m.env/create {::m.env/eval (:eval ~options)
                                            ::m.env/extensions (:notations ~options)})
                             ~system-form
                             identity
                             ~options)))

(defmacro defnotation
  {:arglists '([symbol system-form]
               [symbol system-form {:keys [eval notations terminal?]}])}
  ([symbol system-form]
   `(defnotation ~symbol ~system-form {:notations [], :terminal? false}))
  ([symbol system-form options]
   `(def ~(clj/with-meta symbol (clj/merge {:arglists ''([form])} (meta symbol)))
      (notation ~system-form ~options))))

(defmacro defoperator
  ([symbol system-form]
   `(defoperator ~symbol ~system-form {:notations [], :terminal? false}))
  ([symbol system-form {:keys [eval notations terminal?]}]
   (clj/let [env (m.env/derive-ns-info &env)
             fq-symbol (m.env/qualify-symbol env symbol)]
     `(do
        (m.env/operator-remove! '~fq-symbol)
        (clj/let [f# (m.private/make-notation
                      (m.env/create {::m.env/eval ~eval
                                     ::m.env/extensions ~(clj/vec notations)})
                      ~system-form
                      (fn [form#]
                        (throw (ex-info "Match error" {:form form#, :symbol '~symbol})))
                      {:terminal? ~(clj/boolean terminal?)})
                  g# (fn [env# form#] (f# (vary-meta form# clj/merge env#)))]
          (m.env/operator-add! '~fq-symbol g#)
          (defn ~symbol [& ~'forms]
            (f# (clj/cons '~fq-symbol ~'forms))))))))

(defmacro defvariable
  [symbol q-system-form y-system-form {:keys [eval notations]}]
  (clj/let [env (m.env/derive-ns-info &env)
            fq-symbol (m.env/qualify-symbol env symbol)]
    `(clj/let [v# (def ~(clj/with-meta symbol {:arglists ''([id])})
                    (m.private/variable-factory (m.env/create {::m.env/eval ~eval
                                                               ::m.env/extensions ~notations})
                                                '~q-system-form
                                                '~y-system-form))]
       (m.env/operator-add! '~fq-symbol (fn [env# [_# & args#]] (reduced (clj/apply ~symbol args#)))))))

;; Base operators
;; --------------

(defoperator ^{:style/indent 1} with
  (rule
   ((anything) (? 1) (? 2))
   (`with* (? 1) (? 2))))

;; Symbol notation
;; ---------------

(defnotation
  ^{:doc "Convert symbols that start with \"_\"? into the form (anything)."}
  anything-symbol
  (rule
   (symbol (anything) (str "_" (anything)))
   (`anything)))

(defnotation
  ^{:doc "Convert symbols with names that start with \"?\" into logic
  variables."}
  logic-variable-symbol
  (rule
   (each (? 1) (symbol _ (str "?" _)))
   (apply ~logic-variable [(? 1)] _))
  {:eval (fn [x] (case x logic-variable m.primitive/logic-variable))
   :notations [anything-symbol]
   :terminal? true})

(defnotation
  ^{:doc "Convert symbols with names that start with \"%\" into references."}
  reference-symbol
  (rule
   (each ?symbol (symbol _ (str "%" _)))
   (apply ~reference [?symbol] _))
  {:eval (fn [x] (case x reference m.primitive/reference))
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

;; FIFO
;; TODO: Back with a queue.
(defvariable <<
  (system
   (rule [(unbound) ?x#]
         [?x#])
   (rule [[& ?rest#] ?x#]
         [& ?rest# ?x#]))
  (system
   (rule [?x#]
         [(unbound) ?x#])
   (rule [?x# & ?rest#]
         [[& ?rest#] ?x#]))
  {:notations [logic-variable-symbol
               vector-rest]})

(defnotation <<-symbol
  (rule
   (each ?1 (symbol _ (str "<<" _)))
   (`<< ?1))
  {:notations [anything-symbol
               logic-variable-symbol]})

;; LIFO
(defvariable >>
  (system
   (rule [(unbound) ?x#]
         (?x#))
   (rule [(each ?xs# (cons _ _)) ?x#]
         (cons ?x# ?xs#)))
  (system
   (rule (?x)
         [(unbound) ?x#])
   (rule (cons ?x# ?xs#)
         [?xs# ?x#]))
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
         (apply ~inc [?n#] _)))
  (system
   (rule ?n#
         [?n# ?n#]))
  {:eval {'inc inc}
   :notations [anything-symbol
               logic-variable-symbol]})

(defnotation ++-symbol
  (rule
   (each ?1 (symbol _ (str "++" _)))
   (`++ ?1))
  {:notations [anything-symbol
               logic-variable-symbol]})

(defoperator explain
  (system
   (rule
    (_ (`explain ?a))
    (`explain* ?a))

   (with {%op (each ?op (some `apply
                              `cons
                              `each
                              `hash-map
                              `hash-set
                              `pick
                              `rule
                              `some
                              `system))
          %arg <<x
          %args-in (cons %arg (pick %args-in ()))
          %args-out (cons (`explain %arg) (pick %args-out ()))}
     (rule
      (_ (cons %op %args-in))
      (`explain* (cons %op %args-out))))

   (rule
    (_ ?a)
    (`explain* ?a)))
  {:notations [<<-symbol
               anything-symbol
               reference-symbol
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
   (assoc ?m (symbol _ (str "&" _)) ?v)
   (`merge ?m ?v))
  {:notations [anything-symbol
               logic-variable-symbol]})


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


;; Callable Systems
;; ---------------------------------------------------------------------

(def default-notations
  [#'anything-symbol
   #'logic-variable-symbol
   #'reference-symbol
   #'>>-symbol
   #'<<-symbol
   #'++-symbol
   #'hash-map-as
   #'hash-map-rest
   ;; #'m.set/hash-set-as
   ;; #'m.set/hash-set-rest
   #'vector-as
   #'vector-rest])

(defmacro pattern
  {:arglists '([form]
               [form {:keys [eval explain? notations]}])}
  ([x]
   `(pattern ~x {}))
  ([x options]
   `(clj/let [env# (m.env/create {::m.env/eval (:eval ~options)
                                  ::m.env/extensions (:notations ~options)})]
      (m.parse/parse env# (m.private/preprocess-form env# '~x)))))

(defmacro dff
  {:arglists '([system-form]
               [system-form {:keys [eval explain? notations]}])}
  ([system]
   `(dff ~system {:notations ~default-notations}))
  ([system options]
   `(clj/let [make-logic# (if (:explain? ~options)
                            m.logic/make-dff-explain
                            m.logic/make-dff)]
     (m.private/create-system-fn ~system ~options make-logic#))))

(defmacro bfs
  {:arglists '([system-form]
               [system-form {:keys [eval explain? notations]}])}
  ([system]
   `(bfs ~system {:notations ~default-notations}))
  ([system options]
   `(clj/let [make-logic# (if (:explain? ~options)
                            m.logic/make-bfs-explain
                            m.logic/make-bfs)]
      (m.private/create-system-fn ~system ~options make-logic#))))

(def query m.protocols/-query)
(def yield m.protocols/-yield)
(def redex m.protocols/-redex)
