(ns meander.syntax.zeta
  (:require [meander.util.zeta :as m.util]
            [meander.compiled.parse.zeta :as m.parse])
  #?(:cljs (:require-macros [meander.syntax.zeta])))

(defn ns-symbolic-alias-map [ns]
  #?(:clj (into {}
                (map (fn [[alias ns]]
                       [alias (ns-name ns)]))
                (ns-aliases ns))
     :cljs {}))

(defn make-parse-env [ns]
  {:aliases (ns-symbolic-alias-map ns)
   :cata-symbol (gensym "C__")
   :state-symbol (gensym "S__")
   :expander-registry {}
   :parser-registry {}})

(defn parse
  ([form]
   (m.parse/parse [form (make-parse-env *ns*)]))
  ([form env]
   (m.parse/parse [form env])))
