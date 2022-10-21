(ns meander.integer.zeta-test
  (:require
   [clojure.test :as t]
   [meander.environment.zeta :as m.env]
   [meander.parse.zeta :as m.parse]
   [meander.integer.zeta :as m.int]
   [meander.primitive.zeta :as m.primitive]
   [meander.test-helpers :refer [test-query test-yield]]))

(t/deftest min-rewrite-test
  (t/is (= 1 (m.int/min 1 2)))

  (t/is (= (list `m.int/min* 1 `(m.int/cast ?a))
           (m.int/min 1 `?a)))

  (t/is (= (list `m.int/min* `(m.int/cast ?a) 1)
           (m.int/min `?a 1))))


(t/deftest max-rewrite-test
  (t/is (= 2 (m.int/max 1 2)))

  (t/is (= (list `m.int/max* 1 `(m.int/cast ?a))
           (m.int/max 1 `?a))))


(t/deftest +-rewrite-test
  (t/is (= 3 (m.int/+ 1 2)))

  (t/is (= (list `m.int/+* 1 `(m.int/cast ?a))
           (m.int/+ 1 `?a)))

  (t/is (= 6
           (m.int/+ 1 2 3)))

  (t/is (= `(m.int/+ 6 (m.int/+ ?a (m.int/+ ?b)))
           (m.int/+ 1 `?a 2 `?b 3)))

  (t/is (= `(m.int/+ ?a (m.int/+ ?b ?c))
           (m.int/+ `?a `?b `?c))))
