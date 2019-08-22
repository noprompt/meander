(ns meander.substitute.epsilon-test
  (:require [clojure.test :as t]
            [clojure.test.check.clojure-test :as tc.t :include-macros true]
            [clojure.test.check.generators :as tc.gen :include-macros true]
            [clojure.test.check.properties :as tc.prop :include-macros true]
            [meander.substitute.epsilon :as r.subst :include-macros true]
            [meander.substitute.syntax.epsilon :as r.subst.syntax :include-macros true]))

(t/deftest substitute-map
  (t/is (= {:a 1 :b 2}
           (let [?a 1 ?b 2]
             (r.subst/substitute {:a ?a :b ?b}))))
  (t/is (= {:a 1 :b 2}
           (let [?a 1 !xs [2]]
             (r.subst/substitute {:a ?a :b !xs})))))

(t/deftest rest-map-properly-substitutes
  (t/is (= {:a 1 :b 2} (r.subst/substitute {& [[:a 1] [:b 2]]})))
  (t/is (= {:a 1 :b 2 :c 3} (r.subst/substitute {:c 3 & [[:a 1] [:b 2]]}))))
