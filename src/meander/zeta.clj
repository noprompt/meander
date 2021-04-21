(ns meander.zeta
  (:require [meander.core.zeta :as m.core]
            [meander.parse.zeta :as m.parse]
            [meander.tree.zeta :as m.tree]
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
  (m.util/fix (comp m.rt.tree.one/pass-prune
                    m.rt.tree.one/pass-commute
                    m.rt.tree.one/pass-interpret)))

(defmacro query-one [pattern]
  (let [options (meta &form)
        parse (m.parse/parser (m.util/cljs-ns-from-clj-ns *ns*))
        rt (m.rt.tree/df-one options)
        bind (:bind rt)
        code (:eval rt)
        list (:list rt)
        pass (:pass rt)
        input (gensym "X__")
        tree (bind (fn [state] (pass (list state)))
                       (m.core/run-query (parse pattern) rt (code input)))
        f (if (false? (::optimize? options)) identity optimize)
        ;; tree (f tree)
        clojure (m.rt.tree.one/clojure tree)
        form `(fn [~input] ~clojure)]
    form
    #_
    (with-meta form {::tree (m.tree/form tree)})))

(defmacro query-all [pattern]
  (let [options (meta &form)
        parse (m.parse/parser (m.util/cljs-ns-from-clj-ns *ns*))
        rt (m.rt.tree/df-one options)
        bind (:bind rt)
        code (:eval rt)
        list (:list rt)
        pass (:pass rt)
        input (gensym "X__")
        tree (bind (fn [state] (pass (list state)))
                   (m.core/run-query (parse pattern) rt (code input)))
        f (if (false? (::optimize? options)) identity optimize)
        ;; tree (f tree)
        clojure (m.rt.tree.all/clojure tree)]
    `(fn [~input] ~clojure)))
