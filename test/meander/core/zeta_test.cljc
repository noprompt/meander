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
  (t/testing "seq pattern matches seq?"
    (let [object '(1 2 3)
          ?it (m/logic-variable)]
      (t/is (= [object]
               (yield-one (m/project object (m/seq ?it) (m/rx-cat ?it)))))))

  (t/testing "seq pattern does not recognize a vector"
    (let [object []
          ?it (m/logic-variable)]
      (t/is (= nil
               (yield-one (m/project object (m/seq ?it) (m/rx-cat ?it)))))))

  (t/testing "seq pattern yields nil for an empty collection"
    (t/is (= '(nil nil nil nil nil)
             (yield-all (m/seq (m/some nil () [] {} #{}))))))

  (t/testing "seq pattern yields a seq? for a non-empty collection type"
    (t/is (= '((1) ([:a :b]) (2))
             (yield-all (m/seq (m/some [1] {:a :b} #{2}))))))

  (t/testing "seq pattern yields nothing for a non-collection type value."
    (t/is (= '()
             (yield-all (m/seq (m/some 1 :a :b 2)))))))


(t/deftest vec-test
  (t/testing "vec pattern [] for an empty collection"
    (t/is (= '([] [] [] [] [] [])
             (yield-all (m/vec (m/some nil [] () [] {} #{}))))))

  (t/testing "vec pattern yields a vector? for a non-empty collection type"
    (t/is (= '([1] [[:a :b]] [2])
             (yield-all (m/vec (m/some [1] {:a :b} #{2}))))))

  (t/testing "vec pattern yields nothing for a non-collection type value."
    (t/is (= '()
             (yield-all (m/vec (m/some 1 :a :b 2)))))))
