(ns meander.substitute.syntax.epsilon
  #?(:clj
     (:require [clojure.walk :as walk]
               [clojure.spec.alpha :as s]
               [clojure.core.specs.alpha :as core.specs]
               [meander.match.epsilon :as r.match]
               [meander.syntax.epsilon :as r.syntax]
               [meander.util.epsilon :as r.util])
     :cljs
     (:require [clojure.walk :as walk]
               [cljs.spec.alpha :as s :include-macros true]
               [cljs.core.specs.alpha :as core.specs]
               [meander.match.epsilon :as r.match]
               [meander.syntax.epsilon :as r.syntax]
               [meander.util.epsilon :as r.util]))
  #?(:cljs
     (:require-macros [meander.substitute.syntax.epsilon])))

;; ---------------------------------------------------------------------
;; Rewriting

(defn rewrite-partition
  [node]
  (r.match/find node
    (with [%right (not (or () []))]
      {:tag :prt
       :left {:tag :cat
              :elements ?ls}
       :right {:tag :cat
               :elements (and %right ?rs)}
       :as ?prt})
    {:tag :prt
     :left {:tag :cat
            :elements (vec (concat ?ls ?rs))}
     :right {:tag :cat
             :elements []}}

    {:tag :prt
     :left {:tag :cat
            :elements ?elements1}
     :right {:tag :prt
             :left {:tag :cat
                    :elements ?elements2}
             :right ?right}}
    (rewrite-partition
     {:tag :prt
      :left {:tag :cat
             :elements (vec (concat ?elements1 ?elements2))}
      :right ?right})

    {:tag :prt
     :left {:tag :prt,
            :left ?left,
            :right {:tag :cat
                    :elements ?elements-1}},
     :right {:tag :cat
             :elements ?elements-2}}
    (rewrite-partition
     {:tag :prt
      :left ?left
      :right {:tag :cat
              :elements (vec (concat ?elements-1 ?elements-2))}})

    {:tag :prt
     :left ?left
     :right {:tag :prt
             :left {:tag :cat
                    :elements ?elements}
             :right {:tag :cat
                     :elements (or [] ())}}}
    {:tag :prt
     :left ?left
     :right {:tag :cat
             :elements ?elements}}

    _
    node))

(defn rewrite-partitions
  [node]
  (r.syntax/prewalk rewrite-partition node))


(defn rewrite-coerce-literals-to-lit
  [node]
  (r.syntax/prewalk
   (fn [node]
     (if (and (r.syntax/literal? node)
              (not= (r.syntax/tag node) :cat)
              (not= (r.syntax/tag node) :prt))
       {:tag :lit
        :value (r.syntax/unparse node)}
       node))
   node))


(defn expand-ast
  [node]
  (-> node
      r.syntax/rename-refs
      rewrite-partitions
      rewrite-coerce-literals-to-lit))

;; ---------------------------------------------------------------------
;; Syntax extension


(def ^{:dynamic true}
  *form* nil)

(def ^{:dynamic true}
  *env* {})

(defonce macro-registry
  (atom {}))

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

(defmulti parse-special
  (fn [form env]
    (if (and (seq? form) (symbol? (first form)))
      (first form)
      ::not-special))
  :default ::not-special)

(s/fdef parse-special
  :args (s/cat :form (s/cat :head symbol? :tail (s/* any?))
               :parse-env ::r.syntax/pase-env)
  :ret ::r.syntax/node)

(defn special-form?
  "`true` if `x` is a special form and `false` otherwise."
  [x env]
  (and (seq? x)
       (symbol? (first x))
       (contains? (methods parse-special) (expand-symbol (first x) env))))

(def parse-env
  {::r.syntax/parse-special #'parse-special
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
