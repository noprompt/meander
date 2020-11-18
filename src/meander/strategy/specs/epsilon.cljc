(ns ^:no-doc meander.strategy.specs.epsilon
  (:require
   [meander.match.specs.epsilon]
   [clojure.spec.alpha :as s]))

(s/fdef meander.strategy.epsilon/match
  :args :meander.match.epsilon.match/clauses
  :ret any?)

(s/fdef meander.strategy.epsilon/search
  :args :meander.match.epsilon.match/clauses
  :ret any?)

(s/fdef meander.strategy.epsilon/find
  :args :meander.match.epsilon.match/clauses
  :ret any?)

(s/fdef meander.strategy.epsilon/rewrite
  :args :meander.match.epsilon.match/clauses
  :ret any?)

(s/fdef meander.strategy.epsilon/rewrites
  :args :meander.match.epsilon.match/clauses
  :ret any?)
