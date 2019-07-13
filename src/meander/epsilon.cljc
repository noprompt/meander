(ns meander.epsilon
  (:refer-clojure :exclude [find])
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

;; ---------------------------------------------------------------------
;; Match, Find, Search

(defmacro match
  {:arglists '([x & clauses])
   :style/indent :defn}
  [& args] `(r.match/match ~@args))

(defn register-match-expander
  {:style/indent :defn}
  [sym fn-2args]
  (r.match.syntax/register-expander sym fn-2args))

(defmacro find
  {:arglists '([x & clauses])
   :style/indent :defn}
  [& args] `(r.match/find ~@args))

(defmacro search
  {:arglists '([x & clauses])
   :style/indent :defn}
  [& args] `(r.match/search ~@args))

;; ---------------------------------------------------------------------
;; Substitute

(defmacro subst
  {:arglists '([x & clauses])
   :style/indent :defn}
  [& args] `(r.subst/substitute ~@args))

(defn register-subst-expander
  {:style/indent :defn}
  [sym fn-2args]
  (r.subst.syntax/register-expander sym fn-2args))

;; ---------------------------------------------------------------------
;; Rewrite

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

(def ^{:arglists '([x])
       :private true}
  compile-rewrites
  (r/rewrite
   [?x (!match !substitution ...)]
   (`r.match/search ?x . !match (`r.substitute/substitute !substitution) ...)

   _
   [:error "rewrite expects and odd number of arguments"]))

(defmacro rewrites
  {:style/indent :defn}
  [x & clauses]
  (r.match/match (compile-rewrites [x clauses])
    [:error ?error-message]
    (throw (ex-info ?error-message {}))

    ?form
    ?form))

(s/fdef rewrites
  :args (s/cat :x any?
               :clauses (s/* (s/cat :match any?
                                    :substitution any?)))
  :ret any?)
