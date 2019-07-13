(ns meander.epsilon
  #?(:clj
     (:require [clojure.spec.alpha :as s]
               [meander.match.epsilon :as r.match]
               [meander.strategy.epsilon :as r]
               [meander.substitute.epsilon :as r.substitute])
     :cljs
     (:require [cljs.spec.alpha :as s :include-macros true]
               [meander.match.epsilon :as r.match :include-macros true]
               [meander.strategy.epsilon :as r :include-macros true]
               [meander.substitute.epsilon :as r.substitute :include-macros true]))
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
