(ns meander.zeta-test
  (:require
   [clojure.test :as t]
   [meander.primitive.zeta :as m.primitive]
   [meander.zeta :as m]
   [meander.set.zeta :as m.set])
  (:import meander.primitive.zeta.Variable))

;; Primitive notation tests
;; ---------------------------------------------------------------------

(t/deftest anything-symbol-test
  (t/is (= `(m/anything)
           (m/anything-symbol (gensym "_")))))

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

;; Explain tests
;; ---------------------------------------------------------------------

(t/deftest explain-test
  (t/testing "operator"
    (t/is (= `(m/explain* a)
             (m/explain `a)))

    (t/is (= `(m/explain* (m/each (m/explain a) (m/explain b)))
             (m/explain `(m/each a b))))

    (t/is (= `(m/explain* (m/some (m/explain a) (m/explain b)))
             (m/explain `(m/some a b))))

    (t/is (= `(m/explain* (m/pick (m/explain a) (m/explain b)))
             (m/explain `(m/pick a b))))

    (t/is (= `(m/explain* (m/cons (m/explain a) (m/explain b)))
             (m/explain `(m/cons a b))))

    (t/is (= `(m/explain* (m/rule (m/explain a) (m/explain b)))
             (m/explain `(m/rule a b))))

    (t/is (= `(m/explain* (m/system
                           (m/explain (m/rule a b))
                           (m/explain (m/rule c d))))
             (m/explain `(m/system
                          (m/rule a b)
                          (m/rule c d)))))))

;; Vector notation tests
;; ---------------------------------------------------------------------

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

;; Hash map tests
;; ---------------------------------------------------------------------

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

;; Hash set tests
;; ---------------------------------------------------------------------

(t/deftest union-operator-test
  (t/is (= `(m.set/member ?x)
           (m.set/union `?x)))

  (t/is (= `(m.set/union* ?x (m.set/union ?y))
           (m.set/union `?x `?y)))

  (t/is (= `(m.set/union* ?x (m.set/union ?y ?z))
           (m.set/union `?x `?y `?z))))

(t/deftest intersection-operator-test
  (t/is (= `(m.set/member ?x)
           (m.set/intersection `?x)))

  (t/is (= `(m.set/intersection* ?x (m.set/intersection ?y))
           (m.set/intersection `?x `?y)))

  (t/is (= `(m.set/intersection* ?x (m.set/intersection ?y ?z))
           (m.set/intersection `?x `?y `?z))))

(t/deftest hash-set-as-notation-test
  (t/testing "Does nothing if there is no element with meta containing the submap {::m/as true}"
    (t/is (= #{1 2}
             (m/hash-set-as #{1 2}))))

  (t/testing "Regonizes element with meta containing the submap {::m/as true}"
    (t/is (= `(m/each ?x #{1 2})
             (m/hash-set-as #{1 2 (with-meta `?x {::m/as true})}))))

  (t/testing "Removes ::m/as meta"
    (t/is (= {:foo "bar"}
             (meta (second (m/hash-set-as #{1 2 (with-meta `?x {:foo "bar", ::m/as true})})))))))

(t/deftest hash-set-rest-notation-test
  (t/testing "Does nothing if there is no element with meta containing the submap {& true}"
    (t/is (= #{1 2}
             (m/hash-set-rest #{1 2}))))

  (t/testing "Regonizes element with meta containing the submap {& pattern}"
    (t/is (= `(m.set/union ?x #{1 2})
             (m/hash-set-rest #{1 2 (with-meta `?x `{m/& true})}))))

  (t/testing "Removes & meta"
    (t/is (= {:foo "bar"}
             (meta (second (m/hash-set-rest #{1 2 (with-meta `?x `{m/& true, :foo "bar"})})))))))

;; FIFO (>>) tests
;; ---------------------------------------------------------------------

(t/deftest >>-test
  (t/testing "notation"
    (t/is (= `(m/>> >>1) (m/>>-symbol `>>1))))
  (t/testing "operator"
    (t/is (instance? Variable (m/>> `>>1)))))
