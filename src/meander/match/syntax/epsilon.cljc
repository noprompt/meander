(ns meander.match.syntax.epsilon
  (:refer-clojure :exclude [macroexpand-1])
  #?(:clj
     (:require [clojure.walk :as walk]
               [clojure.spec.alpha :as s]
               [clojure.core.specs.alpha :as core.specs]
               [meander.syntax.epsilon :as r.syntax])
     :cljs
     (:require [clojure.walk :as walk]
               [cljs.spec.alpha :as s :include-macros true]
               [cljs.core.specs.alpha :as core.specs]
               [meander.syntax.epsilon :as r.syntax]))
  #?(:cljs
     (:require-macros [meander.syntax.epsilon])))


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
     :cljs (if (qualified-symbol? sym)
             (let [ns-sym (symbol (namespace sym))]
               (if-some [ns (get (:requires cljs-ns) ns-sym)]
                 (symbol (name ns) (name sym))
                 sym))
             (if (contains? (:defs cljs-ns) sym)
               (symbol (name (:name cljs-ns)) (name sym))
               sym))))

(defn get-macro
  {:private true}
  [sym env]
  (get (deref macro-registry) (expand-symbol sym env)))

(defn macroexpand-1
  {:private true}
  ([form]
   (macroexpand-1 form {}))
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

(defn parse
  ([form]
   (r.syntax/parse form {::r.syntax/macroexpand-1 macroexpand-1}))
  ([form env]
   (r.syntax/parse form (assoc env ::r.syntax/macroexpand-1 macroexpand-1))))

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
