(ns meander.zeta
  (:require [meander.core.zeta :as m.core]
            [meander.pattern.zeta :as m.pattern]
            [meander.parse.zeta :as m.parse]
            [meander.tree.zeta :as m.tree]
            [meander.tree.rewrite.zeta :as m.tree.rewrite]
            [meander.kernel.tree.zeta :as m.kernel.tree]
            [meander.kernel.tree.one.zeta :as m.kernel.tree.one]
            [meander.kernel.tree.all.zeta :as m.kernel.tree.all]
            [meander.util.zeta :as m.util]))

(def optimize
  (m.util/fix
   (comp m.tree.rewrite/pass-prune-test
         m.tree.rewrite/pass-prune-let
         m.tree.rewrite/pass-interpret
         m.tree.rewrite/pass-test
         m.tree.rewrite/pass-pick
         m.tree.rewrite/pass-bind
         m.tree.rewrite/pass-let)))

(defmacro query [pattern]
  (let [kernel (m.kernel.tree/df-one {})
        bind (get kernel :bind)
        host (get kernel :eval)
        get-bindings (get kernel :list)
        object (gensym "x__")
        tree (m.pattern/run-query (m.parse/parse pattern) (host object) kernel)
        tree (bind get-bindings tree)
        tree (optimize tree)
        body (m.kernel.tree.one/clojure tree)]
    `(fn [~object] ~body)))

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
;;       (merge (m.util/cljs-ns-from-clj-ns *ns*)
;;              {:variable-id variable-id
;;               :variable-cache cache}))))

;; (defn make-parser
;;   {:private true}
;;   []
;;   (m.parse/parser (make-parse-environment)))

;; (def optimize
;;   (m.util/fix
;;    (comp m.tree.rewrite/pass-prune-test
;;          m.tree.rewrite/pass-prune-let
;;          m.tree.rewrite/pass-interpret
;;          m.tree.rewrite/pass-test
;;          m.tree.rewrite/pass-pick
;;          m.tree.rewrite/pass-bind
;;          m.tree.rewrite/pass-let)))

;; (defn extra-rules [environment]
;;   (m.core/one-system
;;    (let [<args (m.core/fifo-variable)
;;          *args (m.core/* [<args])]
;;      [(m.core/rule
;;        (m.core/seq (m.core/cons (m.parse/special-symbol `all environment) *args))
;;        (m.core/apply (m.core/data m.core/all) (m.core/* [(m.core/again <args)])))
;;       (m.core/rule
;;        (m.core/seq (m.core/cons (m.parse/special-symbol `one environment) *args))
;;        (m.core/apply (m.core/data m.core/one) (m.core/* [(m.core/again <args)])))])))

;; (defn parse-query [pattern]
;;   (let [environment (m.util/cljs-ns-from-clj-ns *ns*)
;;         environment (assoc environment :extra-rules (extra-rules environment))
;;         parse (m.parse/parser environment)]
;;     (parse pattern)))

;; (defn query-tree [input-symbol form options]
;;   (let [kernel (m.kernel.tree/df-one options)
;;         bind (:bind kernel)
;;         code (:eval kernel)
;;         list (:list kernel)
;;         pass (:pass kernel)
;;         pattern (parse-query form)
;;         tree (bind (fn [state] (pass (list state)))
;;                    (m.core/run-query pattern kernel (code input-symbol)))
;;         f (if (false? (::optimize-post-construct? options))
;;             identity
;;             optimize)
;;         tree (f tree)]
;;     tree))

;; (defmacro query-one [pattern]
;;   (let [options (meta &form)
;;         input-symbol (gensym "t__")
;;         tree (query-tree input-symbol pattern options)
;;         clojure (m.kernel.tree.one/clojure tree)]
;;     `(fn [~input-symbol] ~clojure)))

;; (defmacro query-all [pattern]
;;   (let [options (meta &form)
;;         input-symbol (gensym "t__")
;;         tree (query-tree input-symbol pattern options)
;;         clojure (m.kernel.tree.all/clojure tree)]
;;     `(fn [~input-symbol] ~clojure)))
