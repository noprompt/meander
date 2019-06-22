(ns meander.match.syntax.epsilon-test
  (:require [clojure.spec.alpha :as s :include-macros true]
            [clojure.test :as t]
            [clojure.test.check.clojure-test :as tc.t :include-macros true]
            [clojure.test.check.generators :as tc.gen :include-macros true]
            [clojure.test.check.properties :as tc.prop :include-macros true]
            [meander.match.epsilon :as r.match :include-macros true]
            [meander.match.syntax.epsilon :as r.match.syntax :include-macros true]
            [meander.syntax.epsilon :as r.syntax :include-macros true]))

;; ---------------------------------------------------------------------
;; Generators

(defn gen-parse
  [gen]
  (tc.gen/fmap r.match.syntax/parse gen))

(defn gen-or-form
  [gen]
  (tc.gen/fmap
   (fn [l] (cons 'or l))
   (tc.gen/list gen)))

(defn gen-or-tree
  [gen]
  (gen-or-form (tc.gen/recursive-gen gen-or-form gen)))

(defn gen-and-form
  [gen]
  (tc.gen/fmap
   (fn [l] (cons 'and l))
   (tc.gen/list gen)))

(defn gen-and-tree
  [gen]
  (gen-and-form (tc.gen/recursive-gen gen-and-form gen)))

;; ---------------------------------------------------------------------
;; AST rewriting tests

(t/deftest map-expand-as
  (= (r.match.syntax/expand-ast (r.match.syntax/parse '{:foo :bar :as ?baz}))
     (r.match.syntax/parse '(and {:foo :bar} ?baz))))
