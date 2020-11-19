(ns ^:no-doc meander.match.specs.epsilon
  #?(:clj
     (:require [clojure.spec.alpha :as s])
     :cljs
     (:require [cljs.spec.alpha :as s])))

(s/def :meander.match.epsilon/expr
  any?)

(s/def :meander.match.epsilon/pattern
  any?)

(s/def :meander.match.epsilon/clause
  (s/cat :pat :meander.match.epsilon/pattern
         :rhs :meander.match.epsilon/expr))

(s/def :meander.match.epsilon.match/clauses
  (s/* (s/cat :pat :meander.match.epsilon/pattern
              :rhs :meander.match.epsilon/expr)))

(s/def :meander.match.epsilon.match/args
  (s/cat :expr :meander.match.epsilon/expr
         :clauses (s/* :meander.match.epsilon/clause)))

(s/def :meander.match.epsilon.match/data
  (s/keys :req-un [:meander.match.epsilon/expr
                   :meander.matrix.epsilon/matrix
                   :meander.matrix.epsilon.data/final-clause]))

(s/def :meander.match.epsilon.match.data/final-clause
  (s/nilable :meander.matrix.alpha/row))

(s/fdef meander.match.epsilon/tag-rank
  :args (s/cat :tag keyword?)
  :ret nat-int?)

(s/fdef meander.match.epsilon/score-column
  :args (s/cat :column (s/coll-of :meander.syntax.epsilon/node :kind sequential?))
  :ret nat-int?)

(s/fdef meander.match.epsilon/compile-specialized-matrix
  :args (s/cat :tag keyword?
               :targets (s/coll-of simple-symbol? :kind vector? :into [])
               :matrix :meander.matrix.epsilon/matrix))

(s/def :meander.match.epsilon/pattern
  any?)

(s/def :meander.match.epsilon/clause
  (s/cat :pat :meander.match.epsilon/pattern
         :rhs :meander.match.epsilon/expr))

(s/def :meander.match.epsilon.match/clauses
  (s/* (s/cat :pat :meander.match.epsilon/pattern
              :rhs :meander.match.epsilon/expr)))

(s/def :meander.match.epsilon.match/args
  (s/cat :expr :meander.match.epsilon/expr
         :clauses (s/* :meander.match.epsilon/clause)))

(s/def :meander.match.epsilon.match/data
  (s/keys :req-un [:meander.match.epsilon/expr
                   :meander.matrix.epsilon/matrix
                   :meander.matrix.epsilon.data/final-clause]))

(s/def :meander.match.epsilon.match.data/final-clause
  (s/nilable :meander.matrix.alpha/row))

(s/fdef meander.match.epsilon/analyze-match-args
  :args (s/alt :a1 (s/cat :match-args :meander.match.epsilon.match/args)
               :a2 (s/cat :match-args :meander.match.epsilon.match/args
                          :env any?))
  :ret :meander.match.epsilon.match/data)

(s/fdef meander.match.epsilon/match
  :args (s/cat :expr any?
               :clauses :meander.match.epsilon.match/clauses)
  :ret any?)

(s/fdef meander.match.epsilon/search
  :args (s/cat :expr any?
               :clauses :meander.match.epsilon.match/clauses)
  :ret (s/coll-of any? :kind sequential?))

(s/fdef meander.match.epsilon/find
  :args (s/cat :expr any?
               :clauses :meander.match.epsilon.match/clauses)
  :ret any?)
