(ns meander.integer.zeta-test
  (:require
   [clojure.test :as t]
   [meander.integer.zeta :as m.integer]
   [meander.primitive.zeta :as m.primitive]
   [meander.test-helpers :refer [test-query test-yield]]))


(t/deftest min-rewrite-test
  (t/is (= 1 (m.integer/min 1 2)))
  (t/is (= (list `m.integer/min* 1 `?a) (m.integer/min 1 `?a))))


(t/deftest max-rewrite-test
  (t/is (= 2 (m.integer/max 1 2)))
  (t/is (= (list `m.integer/max* 1 `?a) (m.integer/max 1 `?a))))


(t/deftest +-rewrite-test
  (t/is (= 3 (m.integer/+ 1 2)))
  (t/is (= (list `m.integer/+* 1 `?a)
           (m.integer/+ 1 `?a))))
