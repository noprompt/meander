(ns meander.zeta-test
  (:require
   [clojure.test :as t]
   [meander.primitive.zeta :as m.primitive]
   [meander.zeta :as m]))

(t/deftest anything-symbol-test
  (let [result (m/anything-symbol (gensym "_"))]
    (t/is (reduced? result))

    (when (reduced? result)
      (t/is (= (m/anything)
               (deref result))))))

(t/deftest logic-variable-notation-test
  (let [result (m/logic-variable-symbol '?1)]
    (t/is (reduced? result))

    (when (reduced? result)
      (t/is (= (m/? '?1)
               (deref result))))))

(t/deftest reference-notation-test
  (let [result (m/reference-symbol '%1)]
    (t/is (reduced? result))

    (when (reduced? result)
      (t/is (= (m.primitive/reference '%1)
               (deref result))))))

(t/deftest vector-as-notation-test
  (t/testing "Rule does nothing when ::m/as is not present"
    (let [result (m/vector-as '[1 2 3])]
      (t/is (= result [1 2 3]))))

  (t/testing "Trailing ::m/as is dropped"
    (let [result (m/vector-as '[1 2 3 ::m/as])]
      (t/is (= result [1 2 3]))))

  (t/testing "Typical use case"
    (let [result (m/vector-as '[1 2 3 ::m/as ?x])]
      (t/is (= result
               '(meander.zeta/each ?x [1 2 3])))))

  (t/testing "::m/as can appear anywhere"
    (let [result (m/vector-as '[1 2 3 ::m/as ?x 4 5 6])]
      (t/is (= result
               '(meander.zeta/each ?x [1 2 3 4 5 6]))))))

(t/deftest vector-rest-notation-test
  (t/testing "Rule does nothing when & is not present"
    (let [result (m/vector-rest '[1 2 3])]
      (t/is (= result
               [1 2 3]))))

  (t/testing "Trailing & is dropped"
    (let [result (m/vector-rest '[1 2 3 &])]
      (t/is (= result
               [1 2 3]))))

  (t/testing "& can be qualified"
    (let [result (m/vector-rest `[1 2 3 &])]
      (t/is (= result
               [1 2 3]))))

  (t/testing "Typical use case"
    )

  (t/testing "& can appear anywhere"
    (let [result (m/vector-rest `[1 2 3 & ?x 4 5 6])]
      (t/is (= result
               `(m/vec (m/concat [1 2 3] ?x [4 5 6]))))))


  (t/testing "Recognizes any symbol whos name starts with \"&\""
    (let [result (m/vector-rest '[1 2 3 & ?x])]
      (t/is (= result
               `(m/vec (m/concat [1 2 3] ~'?x)))))

    (let [result (m/vector-rest '[1 2 3 &1 ?x])]
      (t/is (= result
               `(m/vec (m/concat [1 2 3] ~'?x)))))

    (let [result (m/vector-rest `[1 2 3 & ?x])]
      (t/is (= result
               `(m/vec (m/concat [1 2 3] ?x)))))

    (let [result (m/vector-rest `[1 2 3 &foo ?x 4 5 6])]
      (t/is (= result
               `(m/vec (m/concat [1 2 3] ?x [4 5 6])))))))


(t/deftest hash-map-as-notation-test
  (t/testing "Rule does nothing when ::m/as is not present"
    (let [result (m/hash-map-as {:foo "bar"})]
      (t/is (= result
               {:foo "bar"}))))

  (t/testing "Typical use case"
    (let [result (m/hash-map-as `{:foo "bar" ::m/as ?x})]
      (t/is (= result
               `(m/each ?x {:foo "bar"}))))))

(t/deftest hash-map-rest-notation-test
  (t/testing "Rule does nothing when ::m/as is not present"
    (let [result (m/hash-map-rest {:foo "bar"})]
      (t/is (= result {:foo "bar"}))))

  (t/testing "Recognizes any symbol whos name starts with \"&\""
    (let [result (m/hash-map-rest '{:foo "bar" & ?x})]
      (t/is (= result
               `(m/merge {:foo "bar"} ~'?x))))

    (let [result (m/hash-map-rest '{:foo "bar" &1 ?x})]
      (t/is (= result
               `(m/merge {:foo "bar"} ~'?x))))

    (let [result (m/hash-map-rest `{:foo "bar" & ?x})]
      (t/is (= result
               `(m/merge {:foo "bar"} ?x))))

    (let [result (m/hash-map-rest `{:foo "bar" &1 ?x})]
      (t/is (= result
               `(m/merge {:foo "bar"} ?x))))))

(t/deftest union-operator-test
  (t/is (= `(m/union* #{} ?x) (m/union `?x)))
  (t/is (= `(m/union* ?x ?y) (m/union `?x `?y)))
  (t/is (= `(m/union* ?x (m/union ?y ?z)) (m/union `?x `?y `?z))))
