(ns meander.core.zeta-test
  (:require [clojure.test :as t :include-macros true]
            [clojure.test.check.clojure-test :as tc.t :include-macros true]
            [clojure.test.check.generators :as tc.gen :include-macros true]
            [clojure.test.check.properties :as tc.prop :include-macros true]
            [meander.core.zeta :as m]
            [meander.environment.eval.zeta :as m.environment.eval]))

;; Helpers
;; ---------------------------------------------------------------------

(defn yield-one [pattern]
  (m/run-yield pattern m.environment.eval/depth-first-one))

(defn yield-all [pattern]
  (m/run-yield pattern m.environment.eval/depth-first-all))

(defn query-one [pattern object]
  (m/run-query pattern m.environment.eval/depth-first-one object))

(defn query-all [pattern object]
  (m/run-query pattern m.environment.eval/depth-first-all object))

(def non-empty-list-of-anything
  (tc.gen/such-that not-empty (tc.gen/list tc.gen/any)))

;; Pattern tests
;; ---------------------------------------------------------------------

(tc.t/defspec anything-query-test
  (tc.prop/for-all [x tc.gen/any]
    (t/is (query-one m/_ x))))

(tc.t/defspec constant-query-test
  (tc.prop/for-all [x tc.gen/any]
    (t/is (query-one x x))))

(tc.t/defspec constant-yield-test
  (tc.prop/for-all [x tc.gen/any]
    (t/is (= x (yield-one x)))))

(t/deftest predicate-test
  (t/testing "predicate query"
    (t/is (query-one (m/predicate number?) 2))

    (t/is (query-one (m/predicate number? 2) 2)))

  (t/testing "predicate yield"
    (t/is (yield-one (m/predicate number? 2)))

    (t/is (not (yield-one (m/predicate number? "2"))))

    (t/is (= '(1 3)
             (yield-all (m/predicate number? (m/some 1 "2" 3 "4"))))))

  (t/testing "predicate children"
    (t/is (= [number? 2]
             (m/children (m/predicate number? 2))))))


(tc.t/defspec dual-query-one-test
  (tc.prop/for-all [[x y] (tc.gen/such-that
                           (fn [[x y]]
                             (not= x y))
                           (tc.gen/tuple tc.gen/any tc.gen/any))]
    (query-one (m/dual x y) x)))

(tc.t/defspec dual-yield-one-test
  (tc.prop/for-all [[x y] (tc.gen/such-that
                           (fn [[x y]]
                             (not= x y))
                           (tc.gen/tuple tc.gen/any tc.gen/any))]
    (= x (yield-one (m/dual x y)))))

(tc.t/defspec one-query-one-test
  (tc.prop/for-all [xs non-empty-list-of-anything]
    (let [p (apply m/one xs)]
      (every? (fn [x] (query-one p x)) xs))))

(tc.t/defspec one-query-all-test
  (tc.prop/for-all [xs non-empty-list-of-anything]
    (let [p (apply m/one xs)]
      (every? (fn [x]
                (= [{:object x}] (query-all p x)))
              xs))))

(tc.t/defspec some-query-one-test
  (tc.prop/for-all [xs non-empty-list-of-anything]
    (let [p (apply m/some xs)]
      (every? (fn [x] (query-one p x)) xs))))

(tc.t/defspec some-yield-all-test
  (tc.prop/for-all [xs non-empty-list-of-anything]
    (= xs (yield-all (apply m/some xs)))))

(t/deftest some-children-test
  (t/is (= []
           (m/children (m/some true))))

  (t/is (= [true false]
           (m/children (m/some true false))))

  (t/is (= [true (m/some false nil)]
           (m/children (m/some true false nil)))))

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
