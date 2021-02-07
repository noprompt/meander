(ns meander.core.zeta-test
  (:require [clojure.test :as t]
            [meander.core.zeta :as m]
            [meander.environment.zeta :as m.environment]))

;; Helpers
;; ---------------------------------------------------------------------

(defn yield-one [pattern]
  (m/run-yield pattern m.environment/depth-first-one-eval))

(defn yield-all [pattern]
  (m/run-yield pattern m.environment/depth-first-all-eval))

(defn query-one [pattern object]
  (m/run-query pattern m.environment/depth-first-one-eval object))

(defn query-all [pattern object]
  (m/run-query pattern m.environment/depth-first-all-eval object))


;; Atomic pattern tests
;; ---------------------------------------------------------------------

(t/deftest constant-test
  (t/is (some? (query-one even? even?)))
  (t/is (some? (query-one `even? `even?))))

(t/deftest some-test
  (t/is (= '(nil [])
           (yield-all (m/some nil [])))))

(t/deftest symbol-test
  (t/is (= [nil "symbol"]
           (let [?namespace (m/logic-variable)
                 ?name (m/logic-variable)]
             (yield-one (m/project 'symbol
                                   (m/symbol ?namespace ?name)
                                   (m/rx-cat ?namespace ?name))))))

  (t/is (= ["meander.zeta" "symbol"]
           (let [?namespace (m/logic-variable)
                 ?name (m/logic-variable)]
             (yield-one (m/project 'meander.zeta/symbol
                                   (m/symbol ?namespace ?name)
                                   (m/rx-cat ?namespace ?name))))))

  (t/is (= 'symbol
           (yield-one (m/symbol nil "symbol"))))

  (t/is (= 'meander.zeta/symbol
           (yield-one (m/symbol "meander.zeta" "symbol")))))


(t/deftest keyword-test
  (t/is (= [nil "keyword"]
           (let [?namespace (m/logic-variable)
                 ?name (m/logic-variable)]
             (yield-one (m/project :keyword
                                   (m/keyword ?namespace ?name)
                                   (m/rx-cat ?namespace ?name))))))

  (t/is (= ["meander.zeta" "keyword"]
           (let [?namespace (m/logic-variable)
                 ?name (m/logic-variable)]
             (yield-one (m/project :meander.zeta/keyword
                                   (m/keyword ?namespace ?name)
                                   (m/rx-cat ?namespace ?name))))))

  (t/is (= :keyword
           (yield-one (m/keyword nil "keyword"))))

  (t/is (= :meander.zeta/keyword
           (yield-one (m/keyword "meander.zeta" "keyword")))))

;; Collection pattern tests
;; ---------------------------------------------------------------------

(t/deftest rx-cons-test
  (t/is (query-one (m/rx-cons 1 '(2 3)) '[1 2 3]))

  (t/is (query-one (m/rx-cons 1 '[2 3]) '[1 2 3]))

  (t/is (query-one (m/rx-cons 1 '(2 3)) '(1 2 3)))

  (t/is (query-one (m/rx-cons 1 '[2 3]) '(1 2 3)))

  (t/is (= '(1 2 3) (yield-one (m/rx-cons 1 '(2 3)))))

  (t/is (= '(1 2 3) (yield-one (m/rx-cons 1 '[2 3])))))

(t/deftest seq-test
  (t/testing "seq query"
    (t/is (not (query-one (m/seq m/_) 1)))

    (t/is (not (query-one (m/seq [1 2 3]) [1])))

    (t/is (not (query-one (m/seq [1 2 3]) [1 2])))

    (t/is (query-one (m/seq '(1 2 3)) '(1 2 3)))

    (t/is (not (query-one (m/seq [1 2 3]) [1 2 3 4])))

    (t/is (query-one (m/seq m/_) ())))

  (t/testing "seq yield"
    (t/is (= [] (yield-one (m/seq nil))))

    (t/is (= [] (yield-one (m/seq []))))

    (t/is (= [] (yield-one (m/seq ()))))

    (t/is (= [] (yield-one (m/seq {}))))

    (t/is (= [] (yield-one (m/seq #{}))))

    (t/testing "seq pattern [] for an empty collection"
      (t/is (= '([] [] [] [] [])
               (yield-all (m/seq (m/some nil [] () {} #{}))))))

    (t/testing "seq pattern yields a seqtor? for a non-empty collection type"
      (t/is (= '([1] [[:a :b]] [2])
               (yield-all (m/seq (m/some [1] {:a :b} #{2}))))))

    (t/testing "seq pattern yields nothing for a non-collection type value."
      (t/is (= '()
               (yield-all (m/seq (m/some 1 :a :b 2)))))))


  (t/testing "seq yield satisfies query"
    (t/is (query-one (m/seq [1 2 3]) (yield-one (m/seq [1 2 3]))))))


(t/deftest vec-test
  (t/testing "vec query"
    (t/is (not (query-one (m/vec m/_) 1)))

    (t/is (not (query-one (m/vec [1 2 3]) [1])))

    (t/is (not (query-one (m/vec [1 2 3]) [1 2])))

    (t/is (query-one (m/vec [1 2 3]) [1 2 3]))

    (t/is (not (query-one (m/vec [1 2 3]) [1 2 3 4])))

    (t/is (not (query-one (m/vec m/_) ()))))

  (t/testing "vec yield"
    (t/is (= [] (yield-one (m/vec nil))))

    (t/is (= [] (yield-one (m/vec []))))

    (t/is (= [] (yield-one (m/vec ()))))

    (t/is (= [] (yield-one (m/vec {}))))

    (t/is (= [] (yield-one (m/vec #{}))))

    (t/testing "vec pattern [] for an empty collection"
      (t/is (= '([] [] [] [] [])
               (yield-all (m/vec (m/some nil [] () {} #{}))))))

    (t/testing "vec pattern yields a vector? for a non-empty collection type"
      (t/is (= '([1] [[:a :b]] [2])
               (yield-all (m/vec (m/some [1] {:a :b} #{2}))))))

    (t/testing "vec pattern yields nothing for a non-collection type value."
      (t/is (= '()
               (yield-all (m/vec (m/some 1 :a :b 2)))))))


  (t/testing "vec yield satisfies query"
    (t/is (query-one (m/vec [1 2 3]) (yield-one (m/vec [1 2 3]))))))
