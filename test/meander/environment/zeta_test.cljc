(ns meander.environment.zeta-test
  (:require
   [meander.environment.zeta :as m.environment :refer [empty-ns-info] :include-macros true]
   [clojure.test :as t]))

(t/deftest ns-info-test
  (t/testing "ns-info has the correct shape."
    (let [ns-info (m.environment/ns-info)]
      (t/is (= 'meander.environment.zeta-test
               (::m.environment/namespace ns-info)))

      (t/is (= #?(:clj 'clojure.test, :cljs 'cljs.test)
               (get (::m.environment/requires ns-info) 't)))

      (t/is (= 'meander.environment.zeta
               (get (::m.environment/requires ns-info) 'm.environment))))))

(t/deftest create-test
  (t/testing "environment has the correct shape."
    (let [environment (m.environment/create)]
      (t/is (= 'meander.environment.zeta-test
               (::m.environment/namespace environment)))

      (t/is (= #?(:clj 'clojure.test, :cljs 'cljs.test)
               (get (::m.environment/requires environment) 't)))

      (t/is (= 'meander.environment.zeta
               (get (::m.environment/requires environment) 'm.environment)))

      (t/is (map? (::m.environment/operators environment))))))

(t/deftest qualify-symbol-test-1
  (let [environment (m.environment/create)]
    (t/is (= `m.environment/qualify-symbol
             (m.environment/qualify-symbol environment 'm.environment/qualify-symbol)))))

(t/deftest operator-registry-api-test
  (let [symbol `test-operator
        object (reify)
        system (constantly object)
        _ (m.environment/operator-add! symbol system)
        environment-a (m.environment/create)
        _ (m.environment/operator-remove! symbol)
        environment-b (m.environment/create)]
    (t/testing "After operator is added."
      (t/testing "The operator is found."
        (t/is (= [symbol system]
                 (m.environment/operator-find environment-a symbol))))

      (t/testing "The operator is expanded."
        (t/is (= object
                 (m.environment/operator-expand environment-a (list symbol))))))

    (t/testing "After operator is removed."
      (t/testing "The operator is not found."
        (t/is (= nil
                 (m.environment/operator-find environment-b symbol))))

      (t/testing "The operator does not expand."
        (t/is (= (list symbol)
                 (m.environment/operator-expand environment-b (list symbol))))))))
