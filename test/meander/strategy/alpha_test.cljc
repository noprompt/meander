(ns meander.strategy.alpha-test
  (:require [clojure.test :as t]
            [meander.util.alpha :as r.util :include-macros true]
            [meander.strategy.alpha :as r :include-macros true]))

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

