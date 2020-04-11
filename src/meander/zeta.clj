(ns meander.zeta
  (:refer-clojure :exclude [or and let])
  (:require [meander.syntax.zeta :as m.syntax]))

(def
  ^{:arglists '([] [pattern] [pattern-a pattern-b & more-patterns])}
  and)

(def
  ^{:arglists '([*mutable-variable initial-value-expr reduce-fn-expr])
    :style/indent 1}
  fold)

(def
  ^{:arglists '([bindings] [bindings body-pattern])
    :style/indent 1}
  let)

(def
  ^{:arglists '([pred-expr & more-patterns])}
  pred)

(def
  ^{:arglists '([bindings body-pattern])
    :style/indent 1}
  with)

(def
  ^{:arglists '([] [pattern] [pattern-a pattern-b & more-patterns])}
  or)

(def
  ^{:arglists '([pattern])}
  recur)

(def
  ^{:arglists '([& patterns])}
  string)
