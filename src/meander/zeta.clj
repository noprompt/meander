(ns meander.zeta
  (:require [meander.core.zeta :as m.core]
            [meander.environment.data.zeta :as m.data]
            [meander.parse.zeta :as m.parse]
            [meander.util.zeta :as m.util]))

;; (defn make-parse-environment
;;   {:private true}
;;   []
;;   (let [cache (java.util.HashMap.)]
;;     (letfn [(variable-id [sigil name]
;;               (let [key (symbol (str sigil name))]
;;                 (if-some [id (.get cache key)]
;;                   id
;;                   (let [id (gensym (str sigil "__"))]
;;                     (.put cache key id)
;;                     id))))]
;;       (assoc (m.util/cljs-ns-from-clj-ns *ns*)
;;              :variable-id variable-id
;;              :variable-cache cache))))

;; (defprotocol IQuery
;;   (query [this object]))

;; (defprotocol IYield
;;   (yield [this bindings]))

;; (defn one-yield-tree
;;   {:private true}
;;   [yield-pattern bindings-symbol variable-cache]
;;   (let [variable-alias (reduce
;;                         (fn [m [k variable-id]]
;;                           (assoc m variable-id (gensym "x__")))
;;                         {}
;;                         variable-cache)
;;         yield-bindings (reduce
;;                         (fn [m [variable-id variable-alias]]
;;                           (let [k* (m.core/logic-variable variable-id)
;;                                 v* (m.data/make-eval variable-alias)]
;;                             (assoc m k* v*)))
;;                         {}
;;                         variable-alias)]
;;     (m.core/run-yield yield-pattern m.data/environment yield-bindings)))

;; (defn one-yield-code
;;   {:private true}
;;   [yield-pattern bindings-symbol variable-cache]
;;   (let [variable-alias (reduce
;;                         (fn [m [k variable-id]]
;;                           (assoc m variable-id (gensym "x__")))
;;                         {}
;;                         variable-cache)
;;         yield-bindings (reduce
;;                         (fn [m [variable-id variable-alias]]
;;                           (let [k* (m.core/logic-variable variable-id)
;;                                 v* (m.data/make-eval variable-alias)]
;;                             (assoc m k* v*)))
;;                         {}
;;                         variable-alias)]
;;     `(let* [~@(mapcat (fn [[k v]]
;;                         [v `(get ~bindings-symbol '~k ::m.data/none)])
;;                       variable-alias)]
;;        ~(m.data/one-code (m.core/run-yield yield-pattern m.data/environment yield-bindings)))))

;; (defn one-query-code
;;   {:private true}
;;   [query-pattern object-symbol]
;;   (m.data/one-code (m.core/run-query query-pattern m.data/environment object-symbol)))

;; (defn one-ifn-code
;;   {:private true}
;;   [query-pattern yield-pattern object-symbol]
;;   (-> (m.core/rule query-pattern yield-pattern)
;;       (m.core/run-rule m.data/environment object-symbol)
;;       (m.data/one-code)))

;; (defmacro one-rule
;;   {:private true}
;;   [query yield]
;;   (let [parse-environment (make-parse-environment)
;;         parse (m.parse/parser parse-environment)
;;         query-pattern (parse query)
;;         yield-pattern (parse yield)
;;         variable-cache (:variable-cache parse-environment)
;;         this (gensym "x__")
;;         input (gensym "x__")]
;;     `(reify
;;        clojure.lang.IFn
;;        (invoke [~this ~input]
;;          ~(one-ifn-code query-pattern yield-pattern input))

;;        IQuery
;;        (query [~this ~input]
;;          ~(one-query-code query-pattern input))

;;        IYield
;;        (yield [~this ~input]
;;          ~(one-yield-code yield-pattern input variable-cache)))))

;; (defn one-query-tree
;;   {:private true}
;;   [yield-pattern bindings-symbol variable-cache]
;;   (let [variable-alias (reduce
;;                         (fn [m [k variable-id]]
;;                           (assoc m variable-id (gensym "x__")))
;;                         {}
;;                         variable-cache)
;;         yield-bindings (reduce
;;                         (fn [m [variable-id variable-alias]]
;;                           (let [k* (m.core/logic-variable variable-id)
;;                                 v* (m.data/make-eval variable-alias)]
;;                             (assoc m k* v*)))
;;                         {}
;;                         variable-alias)]
;;     (m.core/run-yield yield-pattern m.data/environment yield-bindings)))


;; (one-rule
;;  {"type" ?type, "value" ?value}
;;  [?type ?value])

;; #_
;; comment
;; (def example-rule
;;   (one-rule
;;    [?type ?value]
;;    [?type ?value])

;;   )

;; (yield example-rule (query example-rule [2 1]))

;; (let [query '[?type ?value]
;;       parse-environment (make-parse-environment)
;;       parse (m.parse/parser parse-environment)
;;       pattern (parse query)
;;       variable-cache (:variable-cache parse-environment)]
;;   (m.data/one-code (m.core/run-query pattern m.data/environment 'INPUT)))

;; (let [query '(m.core/one [?x ?y] [?y ?x])
;;       parse-environment (make-parse-environment)
;;       parse (m.parse/parser parse-environment)
;;       pattern (parse query)
;;       variable-cache (:variable-cache parse-environment)
;;       variable-alias (reduce
;;                       (fn [m [k variable-id]]
;;                         (assoc m variable-id (gensym "x__")))
;;                       {}
;;                       variable-cache)
;;       yield-bindings (reduce
;;                       (fn [m [variable-id variable-alias]]
;;                         (let [k* (m.core/logic-variable variable-id)
;;                               v* (m.data/make-eval variable-alias)]
;;                           (assoc m k* v*)))
;;                       {}
;;                       variable-alias)
;;       ]
;;   (m.core/run-yield pattern m.data/environment yield-bindings)
;;   #_
;;   (m.core/run-query query-pattern m.data/environment 'INPUT))


;; (= (example-rule [2 1])
;;    [2 1 2])

;; (= (yield example-rule (query example-rule [2 1]))
;;    [2 1 2])

