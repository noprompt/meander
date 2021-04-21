(ns meander.zeta-test
  (:require [clojure.test :as t]
            [meander.zeta :as m]))

(t/deftest query-test
  (t/is (= {}
           ((m/query-one 1) 1)))

  (t/is (= [{}]
           ((m/query-all 1) 1)))

  (t/is (= nil
           ((m/query-one 1) 2)))

  (t/is (= ()
           ((m/query-all 1) 2)))

  (t/is (= {'?a 1}
           ((m/query-one ?a) 1)))

  (t/is (= [{'?a 1}]
           ((m/query-all ?a) 1)))

  (t/is (= {'?a 1}
           ((m/query-one [?a]) [1])))

  (t/is (= nil
           ((m/query-one [?a]) [])))

  (t/is (= {'?a 1}
           ((m/query-one [?a ?a]) [1 1])))

  (t/is (= [{'?a 1}]
           ((m/query-all [?a ?a]) [1 1])))

  (t/is (= {'?a 1}
           (^{::m/optimize? false}
            (m/query-one [?a ?a]) [1 1])))

  (t/is (= nil
           ((m/query-one [?a ?a]) [1 2])))

  (t/is (= ()
           ((m/query-all [?a ?a]) [1 2])))

  (t/is (= {'?a 1 '?b 2}
           ((m/query-one [?a ?b ?a]) [1 2 1])))

  (t/is (= [{'?a 1 '?b 2}]
           ((m/query-all [?a ?b ?a ?b]) [1 2 1 2]))))

