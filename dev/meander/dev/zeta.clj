(ns meander.dev.zeta
  (:require [clojure.walk :as walk]
            [meander.epsilon :as me]
            [meander.zeta :as mz]
            [meander.runtime.zeta :as m.runtime]
            [meander.syntax.zeta :as m.syntax]
            [meander.dev.parse.zeta :as dev.parse]
            [meander.dev.match.zeta :as dev.match]
            [meander.dev.subst.zeta :as dev.subst]))

(defn variables [ast]
  (me/search ast
    (me/$ {:tag (me/or :logic-variable :memory-variable :mutable-variable)
           :as ?ast})
    ?ast))

(defmacro solve [expression pattern]
  (let [env (m.syntax/make-parse-env *ns*)
        ast (m.syntax/parse pattern env)
        target (gensym "target__")
        match-form (dev.match/match-compile [(list [ast target]) (merge env (meta &form))])]
    `(let [~target ~expression]
       ~match-form)))

(defmacro search
  {:style/indent 1}
  [expression & clauses]
  (let [env (m.syntax/make-parse-env *ns*)
        target (gensym "target___")]
    `(let [~target ~expression]
       ~@(map
          (fn [[left right]]
            (let [left-ast (m.syntax/parse left env)
                  match-form (dev.match/match-compile [(list [left-ast target]) env])
                  syms (into [] (comp (map :symbol) (distinct)) (variables left-ast))
                  state (gensym "S__")]
              `(map
                (fn [~state]
                  ;; Clojure {:syms [,,,]} destructuring adds extra
                  ;; useless overhead so we do the map look up
                  ;; ourselves.
                  (let [~@(mapcat
                           (fn [sym]
                             [sym `(get ~state '~sym)])
                           syms)]
                      ~right))
                ~match-form)))
          (me/rewrite clauses (!left !right ...) ([!left !right] ...))))))

(defmacro rewrite-1
  {:style/indent 1}
  [expression match-pattern subst-pattern]
  (let [env (m.syntax/make-parse-env)
        match-ast (m.syntax/parse match-pattern env)
        cata-symbol (get env :cata-symbol)
        target (gensym "target__")
        match-form (dev.match/match-compile [(list [match-ast target]) env])
        subst-ast (m.syntax/parse subst-pattern env)
        generator (gensym "generator__")
        subst-form (dev.subst/generate-compile subst-ast)]
    `(let [~target ~expression
           ~cata-symbol (fn ~cata-symbol [~target]
                          (let [~generator ~subst-form]
                            (map
                             (fn [env#]
                               (nth (m.runtime/-generate ~generator env#) 0))
                             ~match-form)))]
       (~cata-symbol ~target))))

(defn rewrites-code
  [expression clauses]
  (let [env (m.syntax/make-parse-env *ns*)
        cata-symbol (get env :cata-symbol)
        target-symbol (gensym "T__")
        generator-symbol (gensym)
        env-symbol (gensym)]
    (me/rewrite clauses
      (!left !right ...)
      (`let [~target-symbol ~expression
             ~cata-symbol
             (`fn ~cata-symbol [~target-symbol]
              (`m.runtime/knit
               [(`let [~generator-symbol (me/app dev.subst/generate-compile [(me/app m.syntax/parse !right ~env) ~env])]
                 (`mapcat
                  (`fn [~env-symbol]
                   (`m.runtime/run-gen ~generator-symbol ~env-symbol))
                  (me/app dev.match/match-compile [([(me/app m.syntax/parse !left ~env) ~target-symbol]) ~env])))
                ...]))]
       (~cata-symbol ~target-symbol)))))

(defmacro rewrites
  {:style/indent 1}
  [expression & clauses]
  (rewrites-code expression clauses))

(defmacro rewrite
  {:style/indent 1}
  [expression & clauses]
  `(nth ~(rewrites-code expression clauses) 0 nil))

(defn rewrite-operators [expr]
  (walk/prewalk
   (fn [x]
     (me/rewrite x
       (meander.zeta/and ?x ?y)
       (meander.epsilon/and ?x ?y)
       ?x ?x))
   expr))

(defmacro report [expr pattern action]
  (let [x (gensym)
        solve-form `(solve ~x ~pattern)
        search-form `(me/search ~(rewrite-operators expr) ~pattern ~action)]
    `(let [~x ~expr]
       {:pattern '~pattern
        :solve (with-out-str (time (dotimes [_# 1000] (doall ~solve-form))))
        :solve-expand (with-out-str (time (dotimes [_# 100] (macroexpand '~solve-form))))
        :search (with-out-str (time (dotimes [_# 1000] (doall ~search-form))))
        :search-expand (with-out-str (time (dotimes [_# 100] (macroexpand '~search-form))))
        :solve-answer ~solve-form
        :search-answer ~search-form})))

(defmacro gen [pattern]
  (let [env (m.syntax/make-parse-env *ns*)
        ast (m.syntax/parse pattern env)
        gen (dev.subst/generate-compile [ast env])]
    `(let [gen# ~gen]
       (fn
         ([env#]
          (m.runtime/run-gen gen# env#))
         ([env# n#]
          (m.runtime/run-gen gen# env# n#))))))
