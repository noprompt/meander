(ns ^:no-doc meander.match.specs.epsilon
  #?(:clj (:require [clojure.spec.alpha :as s])
     :cljs (:require [cljs.spec.alpha :as s])))

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

