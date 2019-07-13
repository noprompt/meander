(ns meander.epsilon
  #?(:clj
     (:require [clojure.spec.alpha :as s]
               [meander.match.epsilon :as r.match]
               [meander.match.syntax.epsilon :as r.match.syntax]
               [meander.strategy.epsilon :as r]
               [meander.substitute.epsilon :as r.subst]
               [meander.substitute.syntax.epsilon :as r.subst.syntax])
     :cljs
     (:require [cljs.spec.alpha :as s :include-macros true]
               [meander.match.epsilon :as r.match :include-macros true]
               [meander.match.syntax.epsilon :as r.match.syntax :include-macros true]
               [meander.strategy.epsilon :as r :include-macros true]
               [meander.substitute.epsilon :as r.subst :include-macros true]
               [meander.substitute.syntax.epsilon :as r.subst.syntax :include-macros true]))
  #?(:cljs (:require-macros [meander.epsilon])))

(def ^{:arglists '([x])
       :private true}
  compile-rewrite
  (r/rewrite
   [?x (!match !substitution ...)]
   (`r.match/find ?x . !match (`r.substitute/substitute !substitution) ...)

   _
   [:error "rewrite expects and odd number of arguments"]))

(defmacro rewrite
  {:style/indent :defn}
  [x & clauses]
  (r.match/match (compile-rewrite [x clauses])
    [:error ?error-message]
    (throw (ex-info ?error-message {}))

    ?form
    ?form))

(s/fdef rewrite
  :args (s/cat :x any?
               :clauses (s/* (s/cat :match any?
                                    :substitution any?)))
  :ret any?)

;; ---------------------------------------------------------------------
;; Match

(defmacro match
  {:arglists '([x & clauses])
   :style/indent :defn}
  [& args] `(r.match/match ~@args))

(defn register-match-expander
  [sym fn-2args]
  (r.match.syntax/register-expander sym fn-2args))

;; ---------------------------------------------------------------------
;; Substitute

(defmacro subst
  {:arglists '([x & clauses])
   :style/indent :defn}
  [& args] `(r.subst/substitute ~@args))

(defn register-subst-expander
  [sym fn-2args]
  (r.subst.syntax/register-expander sym fn-2args))
