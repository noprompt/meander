(ns meander.syntax.zeta
  (:require [meander.util.zeta :as m.util]
            [meander.compiled.parse.zeta :as m.parse])
  #?(:cljs (:require-macros [meander.syntax.zeta])))

;; The map returned by this function is used to qualify symbols during
;; parse.
(defn ns-aliases*
  "Like `ns-aliases` but returns a map from symbol to symbol."
  [ns]
  #?(:clj (into {} (map (fn [[alias ns]]
                          [alias (ns-name ns)]))
                (ns-aliases ns))
     :cljs {}))

(defn make-parse-env [ns]
  {:aliases (ns-aliases* ns)
   :cata-symbol (gensym "C__")
   :state-symbol (gensym "S__")
   :expander-registry {}
   :parser-registry {}})

(defn parse
  ([form]
   (parse form (make-parse-env *ns*)))
  ([form env]
   (let [ast (m.parse/parse [form env])]
     {:tag :root, :next ast})))
