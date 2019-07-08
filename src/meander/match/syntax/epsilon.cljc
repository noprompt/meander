(ns meander.match.syntax.epsilon
  "This namespace defines the match syntax special forms, and AST
  transformations and queries."
  #?(:clj
     (:require [clojure.walk :as walk]
               [clojure.spec.alpha :as s]
               [clojure.core.specs.alpha :as core.specs]
               [meander.syntax.epsilon :as r.syntax]
               [meander.util.epsilon :as r.util])
     :cljs
     (:require [clojure.walk :as walk]
               [cljs.spec.alpha :as s :include-macros true]
               [cljs.core.specs.alpha :as core.specs]
               [meander.syntax.epsilon :as r.syntax]
               [meander.util.epsilon :as r.util]))
  #?(:cljs
     (:require-macros [meander.syntax.epsilon])))

;; ---------------------------------------------------------------------
;; Rewriting

(defn expand-as
  {:private true}
  [node]
  (if-some [as (:as node)]
    {:tag :cnj
     :arguments [(assoc node :as nil) as]}
    node))

(defn flatten-cnj
  [cnj-node]
  (let [arguments (:arguments cnj-node)
        arguments* (mapcat
                    (fn f [node]
                      (if (= (r.syntax/tag node) :cnj)
                        (mapcat f (:arguments node))
                        (list node)))
                    arguments)]
    {:tag :cnj
     :arguments arguments*}))

(defn flatten-dsj
  [dsj-node]
  (let [arguments (:arguments dsj-node)
        arguments* (mapcat
                    (fn f [node]
                      (if (= (r.syntax/tag node) :dsj)
                        (mapcat f (:arguments node))
                        (list node)))
                    arguments)]
    {:tag :dsj
     :arguments arguments*}))

(defn expand-dsj
  [node]
  (let [arguments (:arguments (flatten-dsj node))]
    (case (count arguments)
      1
      (first arguments)

      ;; else
      (let [[a b] (split-with r.syntax/literal? arguments)]
        (case (count a)
          0
          node

          1
          {:tag :dsj
           :arguments [(first a)
                       {:tag :dsj
                        :arguments b}]}

          ;; else
          (let [case-tests (sequence
                            (comp (map r.syntax/lit-form)
                                  (distinct)
                                  (map r.util/case-test-form))
                            a)
                pred-form (vary-meta `(fn [x#]
                                        (case x#
                                          (~@case-tests)
                                          true
                                          false))
                                     assoc
                                     :meander.epsilon/beta-reduce true)]
            {:tag :prd
             :form pred-form
             :arguments (if (seq b)
                          [{:tag :dsj
                            :arguments b}]
                          [])}))))))

(defn expand-map-rest
  [node]
  (if-some [rest-map (:rest-map node)]
    (let [key-map (into {} (keep (fn [k-node]
                                   (if (or (r.syntax/ground? k-node)
                                           (r.syntax/lvr-node? k-node))
                                     [k-node k-node]
                                     [k-node {:tag :mut
                                              :symbol (gensym "*m__")}])))
                        (keys (:map node)))
          map* (into {} (map (fn [[k-node v-node]]
                               (let [node (get key-map k-node)]
                                 (if (= node k-node)
                                   [k-node v-node]
                                   [{:tag :cnj
                                     :arguments [k-node node]}
                                    v-node]))))
                     (:map node))
          node* (assoc node :rest-map nil)
          node* (assoc node* :map map*)]
      {:tag :cnj
       :arguments [node*
                   {:tag :app
                    :fn-expr `(fn [m#]
                                (dissoc m# ~@(map r.syntax/unparse (vals key-map))))
                    :arguments [rest-map]}]})
    node))

(defn expand-map
  [node]
  (let [node* (expand-as node)]
    (if (= node* node)
      (expand-map-rest node)
      node*)))

(defn expand-not [node]
  (let [argument (:argument node)]
    (if (= (r.syntax/tag argument) :not)
      (:argument argument)
      node)))

(defn expand-set-rest [node]
  (if-some [rest-set (:rest node)]
    (let [elements (:elements node)
          elem-map (into {}
                         (map
                          (fn [node]
                            (if (or (r.syntax/ground? node)
                                    (r.syntax/lvr-node? node))
                              [node node]
                              [node {:tag :mut
                                     :symbol (gensym "*m__")}])))
                         elements)
          elements* (map
                     (fn [node]
                       (let [[n1 n2] (find elem-map node)]
                         (if (= n1 n2)
                           n1
                           {:tag :cnj
                            :arguments [n1 n2]})))
                     elements)
          node* (assoc node :elements elements*)
          node* (dissoc node* :rest)]
      {:tag :cnj
       :arguments [node* {:tag :app
                          :fn-expr `(fn [s#]
                                      (disj s# ~@(map r.syntax/unparse (vals elem-map))))
                          :arguments [rest-set]}]})
    node))

(defn expand-set
  [node]
  (let [node* (expand-as node)]
    (if (= node* node)
      (expand-set-rest node)
      node*)))

(defn expand-seq [node]
  (expand-as node))

(defn expand-vec [node]
  (expand-as node))

(defn expand-ast
  "Takes an AST node as returned by `meander.syntax.epsilon/parse` and
  expands it in such a way that it can either reduce compiled code
  size, improve compiled code efficiency, or both."
  [node]
  (r.syntax/prewalk
   (fn f [node]
     (case (r.syntax/tag node)
       :cnj
       (flatten-cnj node)

       :dsj
       (expand-dsj node)

       :map
       (expand-map node)

       :not
       (expand-not node)

       :set
       (expand-set node)

       :seq
       (expand-seq node)

       :vec
       (expand-vec node)

       ;; else
       node))
   (r.syntax/rename-refs node)))

;; ---------------------------------------------------------------------
;; Syntax extension


(def ^{:dynamic true}
  *form* nil)

(def ^{:dynamic true}
  *env* {})

(defonce macro-registry
  (atom {}))

(defonce special-registry
  (atom {}))

(defn register-special
  ([symbol var]
   {:pre [(symbol? symbol) (var? var)]}
   (swap! special-registry assoc symbol var))
  ([special-registry symbol var]
   {:pre [(symbol? symbol) (var? var)]}
   (swap! special-registry assoc symbol var)))

(s/fdef register-special
  :args (s/cat :symbol symbol?
               :var-form (s/cat :var-symbol #{'var}
                                :symbol symbol?)))

(defn expand-symbol
  {:private true}
  [sym env]
  #?(:clj (if-some [cljs-ns (:ns env)]
            ;; ClojureScript compile-time
            (if (qualified-symbol? sym)
              (let [ns-sym (symbol (namespace sym))]
                (if-some [ns (get (:requires cljs-ns) ns-sym)]
                  (symbol (name ns) (name sym))
                  sym))
              (if (contains? (:defs cljs-ns) sym)
                (symbol (name (:name cljs-ns)) (name sym))
                sym))
            ;; Clojure
            (if (qualified-symbol? sym)
              (let [ns-sym (symbol (namespace sym))]
                (if-some [ns (get (ns-aliases *ns*) ns-sym)]
                  (symbol (name (ns-name ns)) (name sym))
                  sym))
              (symbol (name (ns-name *ns*)) (name sym))))
     :cljs (if-some [cljs-ns (:ns env)]
             (if (qualified-symbol? sym)
               (let [ns-sym (symbol (namespace sym))]
                 (if-some [ns (get (:requires cljs-ns) ns-sym)]
                   (symbol (name ns) (name sym))
                   sym))
               (if (contains? (:defs cljs-ns) sym)
                 (symbol (name (:name cljs-ns)) (name sym))
                 sym))
             sym)))

(defn get-macro
  {:private true}
  [sym env]
  (get (deref macro-registry) (expand-symbol sym env)))

(defn syntax-expand
  {:private true}
  ([form]
   (syntax-expand form {}))
  ([form env]
   (let [macro (if (seq? form)
                 (if (symbol? (first form))
                   (get-macro (first form) env)))]
     (if (fn? macro)
       (binding [*form* form
                 *env* env]
         (walk/prewalk
          (fn [x]
            (if (seq? x)
              (doall x)
              x))
          (apply macro (rest form))))
       form))))

(defn resolve-special-fn
  {:private true
   :style/indent :defn}
  [sym env]
  (let [;; This should be
        ;;
        ;;     (get (::r.syntax/special-registry env) (expand-symbol form env))
        ;;
        ;; instead.
        x (get (deref special-registry) (expand-symbol sym env))]
    (if (var? x)
      (let [y (deref x)]
        (if (fn? y)
          y
          nil))
      nil)))

(defn parse-special
  "Parses a special form e.g. a `seq?` with a special `symbol?` in its
  first position into a `:meander.syntax.epsilon/node`."
  [form env]
  ;; The choice to return keywords in each of the three failure modes
  ;; is probably not a good long term choice though they will cause
  ;; parse failures to occur properly. There is enough information in
  ;; each of the three cases to inform a programmer that either
  ;;
  ;; 1. the form type was passed,
  ;; 2. the value registered under the form symbol was no a var, or
  ;; 3. the value registered under the form symbol was a var but its
  ;;    dereferenced value wasa not a function.
  ;;
  ;; The keywords should be fine for now, however.
  (if (and (seq? form) (symbol? (first form)))
    (let [x (resolve-special-fn (first form) env)]
      (if (fn? x)
        (x form env)
        :meander.match.syntax.error/invalid-register))
    :meander.match.syntax.error/invalid-special-form))

(s/fdef parse-special
  :args (s/cat :form (s/cat :head symbol? :tail (s/* any?))
               :parse-env ::r.syntax/pase-env)
  :ret (s/or :node ::r.syntax/node
             :other #{:meander.match.syntax.error/invalid-register
                      :meander.match.syntax.error/invalid-special-form}))


(defn special-form?
  "`true` if `x` is of the form

      (and <pattern_0> ... <pattern_n>)
      (app <expr> <pattern> ...)
      (guard <expr>)
      (let <pattern_0> <expr_0> ... <pattern_n> <expr_n>)
      (not <pattern>)
      (or <pattern_0> ... <pattern_n>)
      (pred <expr> <pattern_0> ... <pattern_n>)
      (re <regex-expr>)
      (re <regex-expr> <pattern>)

  and `false` otherwise."
  [x env]
  (and (seq? x)
       (symbol? (first x))
       (fn? (resolve-special-fn (first x) env))))

(def parse-env
  {::r.syntax/parse-special #'parse-special
   ;; Consider replacing this with `::r.syntax/special-registry`. By
   ;; doing that we can eliminate this predicate and simply answer the
   ;; question by checking the registry.
   ::r.syntax/special-form? #'special-form?
   ::r.syntax/syntax-expand #'syntax-expand})

(defn parse
  ([form]
   (r.syntax/parse form parse-env))
  ([form env]
   (r.syntax/parse form (merge env parse-env))))

(defmacro defsyntax [& defn-args]
  (let [conformed-defn-args (s/conform ::core.specs/defn-args defn-args)
        defn-args (next defn-args)
        docstring (:docstring conformed-defn-args)
        defn-args (if docstring
                    (next defn-args)
                    defn-args)
        meta (:meta conformed-defn-args)
        defn-args (if meta
                    (next defn-args)
                    defn-args)
        meta (if docstring
               (merge {:doc docstring} meta)
               meta)
        variadic? (= (first (:fn-tail conformed-defn-args))
                     :arity-n)
        arglists (if variadic?
                   (map first defn-args)
                   (list (first defn-args)))
        meta (assoc meta :arglists (list 'quote arglists))
        fn-name (:fn-name conformed-defn-args)
        meta (merge meta (clojure.core/meta fn-name))
        fn-name (with-meta fn-name meta)
        body (if variadic?
               defn-args
               (list defn-args))
        body (map
              (fn [fn-spec]
                `(~(first fn-spec)
                  (let [~'&form *form*
                        ~'&env *env*]
                    ~@(rest fn-spec))))
              body)
        qfn-name (symbol (name (ns-name *ns*))
                         (name fn-name))]
    ;; When defining new syntax in ClojureScript it is also necessary
    ;; to define the methods which parse and expand the syntax in
    ;; Clojure. This is because the match, search, and find macros (in
    ;; meander.match.epsilon) are expanded in Clojure which, in turn,
    ;; rely on these methods.
    #?(:clj
       (when-some [cljs-ns (:ns &env)]
         ;; Visit the namespace.
         (in-ns (:name cljs-ns))
         ;; Try to require the namespace or everything in
         ;; :requires. Both operations can fail.
         (try
           (require (:name cljs-ns))
           (catch Exception _
             (doseq [[alias ns-name] (:requires cljs-ns)]
               (if (= alias ns-name)
                 (require ns-name)
                 (require [ns-name :as alias])))))
         (eval
          `(do (def ~fn-name (fn ~@body))
               (swap! macro-registry assoc '~qfn-name ~fn-name)))))
    `(do (def ~fn-name (fn ~@body))
         (swap! macro-registry assoc '~qfn-name ~fn-name)
         (var ~fn-name))))

;; ClojureScript seems to have this weird quirk where we need to ask
;; for the spec twice. The first time blows up with an error saying it
;; can't find it, the second time works like a charm. Needless to say,
;; we can't have this namespace cause breakage by virtue of requiring
;; it and getting this error. Needs investigation.
#?(:clj
   (s/fdef defsyntax
     :args ::core.specs/defn-args))

;; ---------------------------------------------------------------------
;; Syntax analysis

(defn not-not?
  "true if `node` represents the syntax `(not (not <pattern>))`."
  {:private true}
  [node]
  (= (:tag node)
     (:tag (:argument node))
     :not))

(defn not-tag
  "Returns `:not-not` if `node` represents the syntax
  `(not (not <pattern>))`; `:not` if `node` represents the syntax
  `(not <pattern>)`; `nil` otherwise."
  {:private true}
  [node]
  (case (:tag node)
    :not
    (case (:tag (:argument node))
      :not
      :not-not
      ;; else
      :not)
    ;; else
    nil))

(defn analyze*
  {:private true}
  [node]
  (r.syntax/fold
   (fn [state node]
     (let [negated-counter (:negated-counter state)]
       {:negated-counter
        (if (zero? negated-counter)
          (case (not-tag node)
            :not
            (+ negated-counter (dec (count (r.syntax/subnodes node))))

            :not-not
            (+ negated-counter 1)

            ;; else
            negated-counter)
          (dec negated-counter))

        :occurrences
        (if (r.syntax/variable-node? node)
          (update (:occurrences state) node (fnil inc 0))
          (:occurrences state))

        :occurrences-in-not
        (if (and (not (zero? negated-counter))
                 (r.syntax/variable-node? node))
          (update (:occurrences-in-not state) node (fnil inc 0))
          (:occurrences-in-not state))}))
   {;; The `nat-int?` number of nodes currently under a negation.
    :negated-counter 0
    ;; A map from `variable-node?` to `nat-int?`. Keeps track of how
    ;; many times a `variable-node?` appears.
    :occurrences {}
    ;; A map from `variable-node?` to `nat-int?`. Keeps track of how
    ;; many times a `variable-node?` appears inside a `not` pattern.
    :occurrences-in-not {}}
   node))

(defn analyze
  [node]
  (dissoc (analyze* node) :negated-counter))

;; ---------------------------------------------------------------------
;; Special forms

;; and
;; ---

(defn parse-and
  {:private true}
  [[_ & args] env]
  {:tag :cnj
   :arguments (r.syntax/parse-all args env)})

(register-special `meander.epsilon/and #'parse-and)
(register-special `meander.match.epsilon/and #'parse-and)

;; apply
;; -----

(defn parse-apply [form env]
  (let [args (rest form)]
    (if (= 2 (bounded-count 3 args))
      {:tag ::apply
       :function (first args)
       :argument (r.syntax/parse (second args) env)}
      (throw (ex-info "meander.epsilon/apply requires two arguments" {})))))

(register-special `meander.epsilon/apply #'parse-apply)

(defmethod r.syntax/children ::apply [node]
  [(:argument node)])

(defmethod r.syntax/ground? ::apply [_]
  false)

(defmethod r.syntax/unparse ::apply [node]
  `(meander.epsilon/apply
    ~(:function node)
    ~(r.syntax/unparse (:argument node))))

(defmethod r.syntax/search? ::apply
  [_] false)

(defmethod r.syntax/walk ::apply [inner outer node]
  (outer (assoc node :argument (inner (:argument node)))))

;; guard
;; -----

(defn parse-guard [[_ expr] env]
  {:tag :grd
   :expr expr})

(register-special `meander.epsilon/guard #'parse-guard)
(register-special `meander.match.epsilon/guard #'parse-guard)

;; let
;; ---

(defn parse-let [[_ & args :as form] env]
  (if (odd? (count args))
    (throw (ex-info "let pattern requires an even number of arguments"
                    {:pattern form
                     :meta (meta form)}))
    {:tag :let
     :bindings (map (fn [[pattern expr]]
                      {:binding (r.syntax/parse pattern env)
                       :expr expr})
                    (partition-all 2 args))}))

(register-special `meander.epsilon/let #'parse-let)
(register-special `meander.match.epsilon/let #'parse-let)

;; not
;; ---

(defn parse-not [[_ & args :as form] env]
  (if (= 1 (bounded-count 2 args))
    {:tag :not
     :argument (r.syntax/parse (first args) env)}
    (throw (ex-info "not pattern requires at one argument"
                    {:pattern form
                     :meta (meta form)}))))

(register-special `meander.epsilon/not #'parse-not)
(register-special `meander.match.epsilon/not #'parse-not)

;; or
;; --

(defn parse-or [[_ & args :as form] env]
  {:tag :dsj
   :arguments (r.syntax/parse-all args env)})

(register-special `meander.epsilon/or #'parse-or)
(register-special `meander.match.epsilon/or #'parse-or)


;; pred
;; ----

(defn parse-pred [[_ expr & args :as form] env]
  {:tag :prd
   :form expr
   :arguments (r.syntax/parse-all args env)})

(register-special `meander.epsilon/pred #'parse-pred)
(register-special `meander.match.epsilon/pred #'parse-pred)

;; re
;; --

(defn parse-re [[_ & args :as form] env]
  (case (bounded-count 3 args)
    1 {:tag :rxt
       :regex (first args)}
    2 {:tag :rxc
       :regex (first args)
       :capture (r.syntax/parse (second args) env)}
    (throw (ex-info "re pattern expects at one or two arguments"
                    {:pattern form
                     :meta (meta form)}))))

(register-special `meander.epsilon/re #'parse-re)
(register-special `meander.match.epsilon/re #'parse-re)
