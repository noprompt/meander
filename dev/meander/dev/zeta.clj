(ns meander.dev.zeta
  (:require [clojure.walk :as walk]
            [meander.epsilon :as me]
            [meander.zeta :as mz]
            [meander.runtime.zeta :as m.runtime]
            [meander.dev.parse.zeta :as dev.parse]
            [meander.dev.match.zeta :as dev.match]))

(defn ns-symbolic-alias-map [ns]
  (into {} (map (fn [[alias ns]]
                  [alias (ns-name ns)]))
        (ns-aliases ns)))

(defn parse-pattern [pattern env]
  (let [ast (dev.parse/parse [pattern env])
        ast {:tag :root :next ast}]
    ast))

(defn variables [ast]
  (me/search ast
    (me/$ {:tag (me/or :logic-variable :memory-variable :mutable-variable)
           :as ?ast})
    ?ast))

(defn make-env []
  {:aliases (ns-symbolic-alias-map *ns*)
   :state-symbol (gensym "S__")})

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

;; (report (into [] (repeat 10 1)) [!xs ...] {'!xs !xs})
;; (report (into [] (repeat 10 1)) [?x . ?x ...] {'?x ?x})
;; (let [root {:tag :root :next (dev.parse/parse '(1 2 3))}]
;;   (dev.match/match-compile [(list [root 'target]) {}]))
;; (solve '(1 2 3) (?x ?y ?z))
