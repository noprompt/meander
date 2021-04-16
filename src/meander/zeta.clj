(ns meander.zeta
  (:require [meander.core.zeta :as m.core]
            [meander.parse.zeta :as m.parse]
            [meander.runtime.tree.zeta :as m.rt.tree]
            [meander.runtime.tree.one.zeta :as m.rt.tree.one]
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

(defmacro query-one [pattern]
  (let [parse (m.parse/parser (m.util/cljs-ns-from-clj-ns *ns*))
        rt (m.rt.tree/df-one)
        bind (:bind rt)
        code (:eval rt)
        list (:list rt)
        pass (:pass rt)
        input (gensym "X__")]
    `(fn [~input]
       ~(m.rt.tree.one/clojure
         ((m.util/fix
           (comp m.rt.tree.one/pass-interpret
                 m.rt.tree.one/pass-commute))
          (bind (fn [state]
                  (pass (list state)))
                (m.core/run-query (parse pattern) rt (code input))))))))
