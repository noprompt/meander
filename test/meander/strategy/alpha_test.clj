(ns meander.strategy.alpha-test
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as s.gen]
            [clojure.test :as t]
            [clojure.test.check :as tc]
            [clojure.test.check.clojure-test :as tc.t]
            [clojure.test.check.generators :as tc.gen]
            [clojure.test.check.properties :as tc.prop]
            [meander.match.alpha :as r.match]
            [meander.util.alpha :as r.util]
            [meander.strategy.alpha :as r]))

(t/deftest pipe-fail-test
  (t/testing "If any strategy to pipe fails then so does pipe."
    (run!
     (fn [f]
       (t/is (r/fail? (f 1))))
     (sequence
      (comp (map
             (fn [n]
               (cons inc (repeat (inc n) r/fail))))
            (mapcat r.util/permutations)
            (map
             (fn [fs]
               (apply r/pipe fs))))
      (range 4)))))

(t/deftest choice-fail-test
  (t/testing "If every strategy to choice fails then so does choice."
    (run!
     (fn [f]
       (t/is (r/fail? (f 1))))
     (sequence
      (map
       (fn [n]
         (apply r/choice (repeat n r/fail))))
      (range 6))))

  (t/testing "If at least one strategy to choice succeeds then so does choice."
    (run!
     (fn [f]
       (t/is (= 2 (f 1))))
     (sequence
      (comp (map
             (fn [n]
               (cons inc (repeat n r/fail))))
            (mapcat r.util/permutations)
            (map
             (fn [fs]
               (apply r/choice fs))))
      (range 5)))))

