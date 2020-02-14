(ns meander.dev.zeta
  (:require [clojure.walk :as walk]
            [meander.epsilon :as me]
            [meander.zeta :as mz]
            [meander.runtime.zeta :as m.runtime]
            [meander.dev.parse.zeta :as dev.parse]
            [meander.dev.match.zeta :as dev.match]
            [meander.dev.subst.zeta :as dev.subst]))

(defn ns-symbolic-alias-map [ns]
  (into {} (map (fn [[alias ns]]
                  [alias (ns-name ns)]))
        (ns-aliases ns)))

(defn make-env []
  {:aliases (ns-symbolic-alias-map *ns*)
   :cata-symbol (gensym "C__")
   :state-symbol (gensym "S__")})

(defn parse-pattern
  ([pattern]
   (parse-pattern pattern (make-env)))
  ([pattern env]
   (let [ast (dev.parse/parse [pattern env])
         ast {:tag :root :next ast}]
     ast)))

(defn variables [ast]
  (me/search ast
    (me/$ {:tag (me/or :logic-variable :memory-variable :mutable-variable)
           :as ?ast})
    ?ast))

(defmacro solve [expression pattern]
  (let [;; Evolve this structure.
        env (make-env)
        ast (parse-pattern pattern env)
        target (gensym "target__")
        match-form (dev.match/match-compile [(list [ast target]) env])]
    `(let [~target ~expression]
       ~match-form)))

(defmacro search
  {:style/indent 1}
  [expression & clauses]
  (let [env (make-env)
        target (gensym "target___")]
    `(let [~target ~expression]
       ~@(map
          (fn [[left right]]
            (let [left-ast (parse-pattern left env)
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
  (let [env (make-env)
        match-ast (parse-pattern match-pattern env)
        cata-symbol (get env :cata-symbol)
        target (gensym "target__")
        match-form (dev.match/match-compile [(list [match-ast target]) env])
        subst-ast (parse-pattern subst-pattern env)
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
  (let [env (make-env)
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
               [(`let [~generator-symbol (me/app dev.subst/generate-compile [(me/app parse-pattern !right ~env) ~env])]
                 (`mapcat
                  (`fn [~env-symbol]
                   (`m.runtime/run-gen ~generator-symbol ~env-symbol))
                  (me/app dev.match/match-compile [([(me/app parse-pattern !left ~env) ~target-symbol]) ~env])))
                ...]))]
       (~cata-symbol ~target-symbol)))))

(defmacro rewrites
  {:style/indent 1}
  [expression & clauses]
  (rewrites-code expression clauses))

(defmacro rewrite
  {:style/indent 1}
  [expression & clauses]
  `(nth ~(rewrites-code expression clauses) 0))

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
  (let [env (make-env)
        ast (parse-pattern pattern env)
        gen (dev.subst/generate-compile [ast env])]
    `(let [gen# ~gen]
       (fn
         ([env#]
          (m.runtime/run-gen gen# env#))
         ([env# n#]
          (m.runtime/run-gen gen# env# n#))))))
