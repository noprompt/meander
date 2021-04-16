(ns meander.zeta-test
  (:require [clojure.test :as t]
            [meander.zeta :as m]))

(t/deftest query-one-test
  (t/is (= {}
           ((m/query-one 1) 1)))

  (t/is (= nil
           ((m/query-one 1) 2)))

  (t/is (= {'?a 1}
           ((m/query-one ?a) 1)))

  (t/is (= {'?a 1}
           ((m/query-one [?a]) [1])))

  (t/is (= nil
           ((m/query-one [?a]) [])))

  (t/is (= {'?a 1}
           ((m/query-one [?a ?a]) [1 1])))

  (t/is (= nil
           ((m/query-one [?a ?a]) [1 2])))

  (t/is (= {'?a 1 '?b 2}
           ((m/query-one [?a ?b]) [1 2]))))
