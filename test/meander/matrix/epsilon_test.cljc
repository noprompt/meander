(ns meander.matrix.epsilon-test
  (:require [clojure.spec.alpha :as s :include-macros true]
            [clojure.test :as t]
            [clojure.test.check.clojure-test :as tc.t :include-macros true]
            [clojure.test.check.generators :as tc.gen :include-macros true]
            [clojure.test.check.properties :as tc.prop :include-macros true]
            [meander.syntax.epsilon :as r.syntax]
            [meander.matrix.epsilon :as r.matrix]))


(t/deftest any-row?-test
  (t/testing "should not be an any-row?"
    #_
    (let [pattern-forms '()
          nodes (map r.syntax/parse pattern-forms)
          action-form :a1
          row (r.matrix/make-row nodes action-form)]
      (t/is (= false (r.matrix/any-row? row))))

    (let [pattern-forms '(_ 1)
          nodes (map r.syntax/parse pattern-forms)
          action-form :a1
          row (r.matrix/make-row nodes action-form)]
      (t/is (= false (r.matrix/any-row? row)))))

  (t/testing "should be an any-row?"
    (let [pattern-forms '(_)
          nodes (map r.syntax/parse pattern-forms)
          action-form :a1
          row (r.matrix/make-row nodes action-form)]
      (t/is (= true (r.matrix/any-row? row))))

    (let [pattern-forms '(_ _)
          nodes (map r.syntax/parse pattern-forms)
          action-form :a1
          row (r.matrix/make-row nodes action-form)]
      (t/is (= true (r.matrix/any-row? row))))))
