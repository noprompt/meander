(ns meander.specs.epsilon
  (:require [meander.match.syntax.epsilon :as r.match.syntax]
            [meander.syntax.specs.epsilon :as r.syntax.specs]
            [clojure.spec.alpha :as s]))


(s/fdef meander.epsilon/defsyntax
  :args ::r.syntax.specs/defsyntax-args)

(s/fdef meander.epsilon/rewrite
  :args (s/cat :x any?
               :clauses (s/* (s/cat :match any?
                                    :substitution any?)))
  :ret any?)


(s/fdef meander.epsilon/rewrites
  :args (s/cat :x any?
               :clauses (s/* (s/cat :match any?
                                    :substitution any?)))
  :ret any?)

(s/fdef meander.epsilon/let
  :args (s/cat :binding-patterns (s/and vector? (s/cat :pattern any? :expression any?))
               :target-pattern (s/? any?))
  :ret seq?)
