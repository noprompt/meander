(ns meander.core.zeta-test
  (:require [clojure.test :as t :include-macros true]
            [clojure.test.check.clojure-test :as tc.t :include-macros true]
            [clojure.test.check.generators :as tc.gen :include-macros true]
            [clojure.test.check.properties :as tc.prop :include-macros true]
            [meander.core.zeta :as m]
            [meander.environment.eval.zeta :as m.environment.eval]))

;; Helpers
;; ---------------------------------------------------------------------

(defn yield-one
  ([pattern]
   (m/run-yield pattern m.environment.eval/depth-first-one))
  ([pattern bindings]
   (m/run-yield pattern m.environment.eval/depth-first-one bindings)))

(defn yield-all
  ([pattern]
   (m/run-yield pattern m.environment.eval/depth-first-all {}))
  ([pattern bindings]
   (m/run-yield pattern m.environment.eval/depth-first-all bindings)))

(defn query-one
  ([pattern object]
   (m/run-query pattern m.environment.eval/depth-first-one object))
  ([pattern object bindings]
   (m/run-query pattern m.environment.eval/depth-first-one object bindings)))

(defn query-all
  ([pattern object]
   (m/run-query pattern m.environment.eval/depth-first-all object))
  ([pattern object bindings]
   (m/run-query pattern m.environment.eval/depth-first-all object bindings)))

(def non-empty-list-of-anything
  (tc.gen/such-that not-empty (tc.gen/list tc.gen/any-equatable)))

;; Pattern tests
;; ---------------------------------------------------------------------

;; Logic Variable
;; --------------

(tc.t/defspec logic-variable-query-one-test-1
  (tc.prop/for-all [id tc.gen/symbol
                    x tc.gen/any-equatable]
    (let [?x (m/logic-variable id)]
      (= {id x}
         (query-one ?x x)))))

(tc.t/defspec logic-variable-query-one-test-2
  (tc.prop/for-all [id tc.gen/symbol
                    x tc.gen/any-equatable]
    (let [?x (m/logic-variable id)]
      (= {id x}
         (query-one ?x x {?x x})))))

(tc.t/defspec logic-variable-query-one-test-3
  (tc.prop/for-all [id tc.gen/symbol
                    x tc.gen/any-equatable]
    (let [?x (m/logic-variable id)]
      (not (query-one ?x x {?x [x]})))))

(tc.t/defspec logic-variable-yield-one-test-1
  (tc.prop/for-all [id tc.gen/symbol]
    (let [?x (m/logic-variable id)]
      (not (yield-one ?x)))))

(tc.t/defspec logic-variable-yield-one-test-2
  (tc.prop/for-all [id tc.gen/symbol
                    x tc.gen/any-equatable]
    (let [?x (m/logic-variable id)]
      (= x
         (yield-one ?x {?x x})))))

;; Mutable Variable
;; ----------------

(tc.t/defspec mutable-variable-query-one-test-1
  (tc.prop/for-all [id tc.gen/symbol
                    x tc.gen/any-equatable]
    (let [*x (m/mutable-variable id)]
      (= {id x}
         (query-one *x x)))))

(tc.t/defspec mutable-variable-query-one-test-2
  (tc.prop/for-all [id tc.gen/symbol
                    x tc.gen/any-equatable]
    (let [y [x]
          *x (m/mutable-variable id)]
      (= {id y}
         (query-one *x y {*x x})))))

(tc.t/defspec mutable-variable-yield-one-test-1
  (tc.prop/for-all [id tc.gen/symbol]
    (let [*x (m/mutable-variable id)]
      (not (yield-one *x)))))

(tc.t/defspec mutable-variable-yield-one-test-2
  (tc.prop/for-all [id tc.gen/symbol
                    x tc.gen/any-equatable]
    (let [*x (m/mutable-variable id)]
      (= x (yield-one *x {*x x})))))

;; Anything
;; --------

(tc.t/defspec anything-query-test
  (tc.prop/for-all [x tc.gen/any-equatable]
    (t/is (query-one m/_ x))))

;; Constant
;; --------

(tc.t/defspec constant-query-test
  (tc.prop/for-all [x tc.gen/any-equatable]
    (t/is (query-one x x))))

(tc.t/defspec constant-yield-test
  (tc.prop/for-all [x tc.gen/any-equatable]
    (t/is (= x (yield-one x)))))

;; Predicate
;; ---------

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

;; Apply
;; -----

(t/deftest apply-query-one-test
  (t/is (query-one (m/apply inc [] 2) 1))

  (t/is (not (query-one (m/apply inc [] 3) 1))))

(t/deftest apply-query-all-test
  (t/is (= [{}]
           (query-all (m/apply (m/some inc dec) [] 2) 1)))

  (t/is (= ()
           (query-all (m/apply (m/some inc dec) [] 3) 1))))

(t/deftest apply-yield-one-test
  (t/is (= 2
           (yield-one (m/apply inc [1]))))

  (t/is (yield-one (m/apply inc [1] 2)))

  (t/is (not (yield-one (m/apply inc [1] 3)))))

(t/deftest apply-yield-all-test
  (t/is (= [2 0]
           (yield-all (m/apply (m/some inc dec) [1]))))

  (t/is (= [2]
           (yield-all (m/apply (m/some inc dec) [1] 2))))

  (t/is (= ()
           (yield-all (m/apply (m/some inc dec) [1] 3)))))

;; Project
;; -------

(tc.t/defspec project-query-one-test-1
  (tc.prop/for-all [x tc.gen/any-equatable]
    (query-one (m/project [x] [x] x) x)))

(tc.t/defspec project-query-one-test-2
  (tc.prop/for-all [x tc.gen/any-equatable]
    (not (query-one (m/project x [x] x) x))))

(tc.t/defspec project-query-one-test-3
  (tc.prop/for-all [x tc.gen/any-equatable]
    (not (query-one (m/project x x [x]) x))))

(tc.t/defspec project-yield-one-test-1
  (tc.prop/for-all [x tc.gen/any-equatable]
    (= x (yield-one (m/project [x] [x] x)))))

(tc.t/defspec project-yield-one-test-2
  (tc.prop/for-all [x tc.gen/any-equatable]
    (not (yield-one (m/project x [x] x)))))

;; One
;; ---

(tc.t/defspec dual-query-one-test
  (tc.prop/for-all [[x y] (tc.gen/such-that
                           (fn [[x y]]
                             (not= x y))
                           (tc.gen/tuple tc.gen/any-equatable tc.gen/any-equatable))]
    (query-one (m/dual x y) x)))

(tc.t/defspec dual-yield-one-test
  (tc.prop/for-all [[x y] (tc.gen/such-that
                           (fn [[x y]]
                             (not= x y))
                           (tc.gen/tuple tc.gen/any-equatable tc.gen/any-equatable))]
    (= x (yield-one (m/dual x y)))))

(tc.t/defspec one-query-one-test
  (tc.prop/for-all [xs non-empty-list-of-anything]
    (let [p (apply m/one xs)]
      (every? (fn [x] (query-one p x)) xs))))

(tc.t/defspec one-query-all-test
  (tc.prop/for-all [xs non-empty-list-of-anything]
    (let [p (apply m/one xs)]
      (every? (fn [x]
                (= [{}] (query-all p x)))
              xs))))

;; Some
;; ----

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

;; All
;; ---

(t/deftest all-query-one-test
  (t/is (query-one (m/all 1) 1))

  (t/is (query-one (m/all 1 1) 1))

  (t/is (query-one (m/all 1 1 1) 1))

  (t/is (not (query-one (m/all 1 2) 1))))

(t/deftest all-query-all-test
  (t/is (query-all (m/all 1) 1))

  (t/is (query-all (m/all 1 1) 1))

  (t/is (query-all (m/all 1 1 1) 1))

  (t/is (= []
           (query-all (m/all 1 2) 1))))

(t/deftest all-yield-one-test
  (t/is (yield-one (m/all 1)))

  (t/is (yield-one (m/all 1 1)))

  (t/is (yield-one (m/all 1 1 1)))

  (t/is (not (yield-one (m/all 1 2)))))

(t/deftest all-yield-all-test
  (t/is (= [1]
           (yield-all (m/all 1))))

  (t/is (= [1 1]
           (yield-all (m/all 1 1))))

  (t/is (= [1 1 1]
           (yield-all (m/all 1 1 1))))

  (t/is (= []
           (yield-all (m/all 1 2)))))

;; Symbol
;; ------

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

;; Keyword
;; -------

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

;; RegexCons
;; ---------

(t/deftest rx-cons-test
  (t/is (query-one (m/rx-cons 1 '(2 3)) '[1 2 3]))

  (t/is (query-one (m/rx-cons 1 '[2 3]) '[1 2 3]))

  (t/is (query-one (m/rx-cons 1 '(2 3)) '(1 2 3)))

  (t/is (query-one (m/rx-cons 1 '[2 3]) '(1 2 3)))

  (t/is (= '(1 2 3) (yield-one (m/rx-cons 1 '(2 3)))))

  (t/is (= '(1 2 3) (yield-one (m/rx-cons 1 '[2 3])))))

;; Seq
;; ---

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

;; Vec
;; ---

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
