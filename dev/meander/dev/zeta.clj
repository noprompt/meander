(ns meander.dev.zeta
  (:require [clojure.walk :as walk]
            [meander.epsilon :as me]
            [meander.zeta :as mz]
            [meander.runtime.zeta :as m.runtime]
            [meander.syntax.zeta :as m.syntax]
            [meander.dev.parse.zeta :as dev.parse]
            [meander.dev.match.zeta :as dev.match]
            [meander.dev.subst.zeta :as dev.subst]))

;; Helpers
;; -------

(defn variable-db-from-variables
  [variables]
  (into #{} (comp (map (juxt :tag :symbol))
                  (distinct)
                  (map
                   (fn [[tag symbol]]
                     (let [[id object]
                           (case tag
                             :logic-variable
                             (let [id (gensym "?__")]
                               [id `(m.runtime/logic-variable '~id)])

                             :mutable-variable
                             (let [id (gensym "*__")]
                               [id `(m.runtime/mutable-variable '~id)])

                             :memory-variable
                             (let [id (gensym "!__")]
                               [id `(m.runtime/memory-variable '~id)])

                             :reference
                             (let [id (gensym "%__")]
                               [id `(m.runtime/reference '~id)]))]
                       {:id id
                        :tag tag
                        :object object
                        :symbol symbol}))))
        variables))

(defn make-variable-db [ast]
  (variable-db-from-variables (m.syntax/variables ast)))

;; Solve
;; -----

(defmacro solve
  {:style/indent 1}
  [expression pattern]
  (let [env (m.syntax/make-parse-env *ns*)
        ast (m.syntax/parse pattern env)
        env (merge env (meta &form))
        target (gensym "target__")
        match-form (dev.match/match-compile [(list [ast target]) env])]
    `(let [~target ~expression]
       ~match-form)))

;; Search
;; ------

(defn analyze-search-clause [[left-form right-form] env]
  (let [left-ast (m.syntax/parse left-form env)
        variable-db (make-variable-db left-ast)]
    {:id (gensym "I__")
     :env env
     :left-ast left-ast
     :right-form right-form
     :variable-db variable-db}))

(defn analyze-search-clauses-args
  ([search-clauses-args]
   (analyze-search-clauses-args search-clauses-args (m.syntax/make-parse-env *ns*)))
  ([search-clauses-args env]
   (map (fn [clause] (analyze-search-clause clause env))
        (partition-all 2 search-clauses-args))))

(defn compile-search-args
  [expression clauses]
  (let [analyses (analyze-search-clauses-args clauses)
        target (gensym "T__")
        bindings (gensym "B__")]
    `(let [~target ~expression
           ;; Bind all 
           ~@(sequence
              (comp (mapcat :variable-db)
                    (mapcat (juxt :id :object)))
              analyses)]
       (letfn [~@(map
                   (fn [analysis]
                     (let [variable-db (get analysis :variable-db)
                           env (get analysis :env)
                           right-form (get analysis :right-form)
                           fn-name (get analysis :id)
                           fn-body `(let [~@(mapcat (fn [{:keys [tag symbol id]}]
                                                      (case tag
                                                        :memory-variable
                                                        [symbol `(get ~bindings ~id [])]
                                                        ;; else
                                                        [symbol `(get ~bindings ~id)]))
                                                    variable-db)]
                                      ~right-form)]
                       `(~fn-name [~bindings]
                         (m.runtime/succeed ~fn-body))))
                   analyses)]
         (concat
          ~@(map
             (fn [analysis]
               (let [variable-db (get analysis :variable-db)
                     env (get analysis :env)
                     env (into env (map (juxt :id :symbol)) variable-db)
                     env (assoc env target expression)
                     env (assoc env :succeed-symbol (get analysis :id))
                     left-ast (get analysis :left-ast)
                     match-form (dev.match/match-compile [(list [left-ast target]) env])]
                 match-form))
             analyses))))))

(defmacro search
  {:style/indent 1}
  [expression & clauses]
  (compile-search-args expression clauses))

;; Rewrites
;; --------

(defn analyze-rewrite-clause [[left-form right-form] env]
  (let [left-ast (m.syntax/parse left-form env)
        left-vars (m.syntax/variables left-ast)
        right-ast (m.syntax/parse right-form env)
        right-vars (m.syntax/variables right-ast)
        variable-db (variable-db-from-variables (into left-vars right-vars))]
    {:env env
     :left-ast left-ast
     :right-ast right-ast
     :variable-db variable-db}))

(defn rewrites-clause-code
  {:arglists '([target rewrites-analysis])}
  [target {:keys [env left-ast right-ast variable-db]}]
  (let [env (into env (map (juxt :id :symbol)) variable-db)
        match-form (dev.match/match-compile [(list [left-ast target]) env])
        subst-form (dev.subst/generate-compile [right-ast env])
        bindings (gensym "B__")
        generator (gensym "G__")]
    `(let [~generator ~subst-form]
       (map
        (fn [~bindings]
          ;; Uncomment this if we want to bind the variable symbols in
          ;; Clojure.
          ;;
          ;; (let [~@(sequence
          ;;          (comp (map (juxt :symbol :id))
          ;;                (mapcat (fn [[symbol id]]
          ;;                          [symbol `(get ~bindings ~id)])))
          ;;          variable-db)])
          (m.runtime/run-gen ~generator ~bindings))
        ~match-form))))

(defn rewrites-code
  [expression clauses]
  (let [env (m.syntax/make-parse-env *ns*)
        cata (get env :cata-symbol)
        target (gensym "T__")
        analyses (map
                  (fn [clause]
                    (analyze-rewrite-clause clause env))
                  (partition-all 2 clauses))]
    `(let [~@(sequence (comp (mapcat :variable-db)
                             (mapcat (juxt :id :object)))
                       analyses)]
       ((fn ~cata [~target]
           (m.runtime/knit
            ~(mapv
              (fn [analysis]
                (rewrites-clause-code target analysis))
              analyses)))
         ~expression))))

(defmacro rewrites
  {:style/indent 1}
  [expression & clauses]
  (rewrites-code expression clauses))

(defmacro rewrite
  {:style/indent 1}
  [expression & clauses]
  `(nth ~(rewrites-code expression clauses) 0 nil))

(defmacro execution-time [form]
  `(let [start# (. java.lang.System (clojure.core/nanoTime))]
     ~form
     (/ (double (- (. java.lang.System (clojure.core/nanoTime)) start#)) 1000000.0)))

(defmacro report
  ([expr pattern action]
   `(report ~expr ~pattern ~pattern ~action))
  ([expr zeta-pattern epsilon-pattern action]
   (let [x (gensym)
         zeta-form `(search ~x ~zeta-pattern ~action)
         epsilon-form `(me/search ~x ~epsilon-pattern ~action)]
     `(let [~x ~expr]
        [[:epsilon/pattern '~epsilon-pattern]
         [:zeta/pattern '~zeta-pattern]
         [:epsilon/execution-time (execution-time (dotimes [_# 1000] (doall ~epsilon-form)))]
         [:zeta/execution-time (execution-time (dotimes [_# 1000] (doall ~zeta-form)))]
         [:epsilon/answer ~epsilon-form]
         [:zeta/answer ~zeta-form]
         [:epsilon/expand-time (execution-time (dotimes [_# 100] (macroexpand '~epsilon-form)))]
         [:zeta/expand-time (execution-time (dotimes [_# 100] (macroexpand '~zeta-form)))]
         [:epsilon/expand (macroexpand '~epsilon-form)]
         [:zeta/expand (macroexpand '~zeta-form)]]))))

(defmacro gen [pattern]
  (let [env (m.syntax/make-parse-env *ns*)
        ast (m.syntax/parse pattern env)
        gen (dev.subst/generate-compile [ast env])]
    `(let [gen# ~gen]
       (fn
         ([env#]
          (m.runtime/run-gen gen# env#))
         ([env# n#] (m.runtime/run-gen gen# env# n#))))))
