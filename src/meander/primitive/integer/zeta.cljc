(ns meander.primitive.integer.zeta)

(defrecord AnyInteger [])

(defrecord IntegerInRange [min max])

(defrecord IntegerSum [a b])

(def any #'->AnyInteger)

(def ^{:arglists '([min max])}
  in-range #'->IntegerInRange)

#_
(defn +
  ([a b]
   (#'->IntegerSum a b))
  ([a b c]
   (#'->IntegerSum (+ a b) c)))
