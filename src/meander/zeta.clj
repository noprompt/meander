(ns meander.zeta
  (:require [meander.core.zeta :as m.core]
            [meander.parse.zeta :as m.parse]
            [meander.tree.zeta :as m.tree]
            [meander.tree.rewrite.zeta :as m.tree.rewrite]
            [meander.runtime.tree.zeta :as m.rt.tree]
            [meander.runtime.tree.one.zeta :as m.rt.tree.one]
            [meander.runtime.tree.all.zeta :as m.rt.tree.all]
            [meander.util.zeta :as m.util]))

(defn make-parse-environment
  {:private true}
  []
  (let [cache (java.util.HashMap.)]
    (letfn [(variable-id [sigil name]
              (let [key (symbol (str sigil name))]
                (if-some [id (.get cache key)]
                  id
                  (let [id (gensym (str sigil "__"))]
                    (.put cache key id)
                    id))))]
      (merge (m.util/cljs-ns-from-clj-ns *ns*)
             {:variable-id variable-id
              :variable-cache cache}))))

(defn make-parser
  {:private true}
  []
  (m.parse/parser (make-parse-environment)))

(def optimize
  (m.util/fix
   (comp m.tree.rewrite/pass-prune-test
         m.tree.rewrite/pass-prune-let
         m.tree.rewrite/pass-interpret
         m.tree.rewrite/pass-test
         m.tree.rewrite/pass-pick
         m.tree.rewrite/pass-bind
         m.tree.rewrite/pass-let)))

(defn extra-rules [environment]
  (m.core/one-system
   (let [<args (m.core/fifo-variable)
         *args (m.core/* [<args])]
     [(m.core/rule
       (m.core/seq (m.core/cons (m.parse/special-symbol `all environment) *args))
       (m.core/apply (m.core/data m.core/all) (m.core/* [(m.core/again <args)])))
      (m.core/rule
       (m.core/seq (m.core/cons (m.parse/special-symbol `one environment) *args))
       (m.core/apply (m.core/data m.core/one) (m.core/* [(m.core/again <args)])))])))

(defn parse-query [pattern]
  (let [environment (m.util/cljs-ns-from-clj-ns *ns*)
        environment (assoc environment :extra-rules (extra-rules environment))
        parse (m.parse/parser environment)]
    (parse pattern)))

(defn query-tree [input-symbol form options]
  (let [rt (m.rt.tree/df-one options)
        bind (:bind rt)
        code (:eval rt)
        list (:list rt)
        pass (:pass rt)
        pattern (parse-query form)
        tree (bind (fn [state] (pass (list state)))
                   (m.core/run-query pattern rt (code input-symbol)))
        f (if (false? (::optimize-post-construct? options))
            identity
            optimize)
        tree (f tree)]
    tree))

(defmacro query-one [pattern]
  (let [options (meta &form)
        input-symbol (gensym "t__")
        tree (query-tree input-symbol pattern options)
        clojure (m.rt.tree.one/clojure tree)]
    `(fn [~input-symbol] ~clojure)))

(defmacro query-all [pattern]
  (let [options (meta &form)
        input-symbol (gensym "t__")
        tree (query-tree input-symbol pattern options)
        clojure (m.rt.tree.all/clojure tree)]
    `(fn [~input-symbol] ~clojure)))