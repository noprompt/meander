(ns meander.substitute.delta-test
  (:require [clojure.test :as t]
            [meander.substitute.delta :as r.substitute :include-macros true]))

(t/deftest lvr-test
  (let [?1 1
        ?2 2
        ?3 3]
    (t/is (= {1 [2 {3 []}]}
             (r.substitute/substitute {?1 [?2 {?3 []}]})))
    (t/is (= [?2 ?3 ?1]
             (r.substitute/substitute [?2 ?3 ?1])))))


(t/deftest mvr-test
  (let [!1s [1 2 3]]
    (t/is (= {1 [2 {3 []}]}
             (r.substitute/substitute {!1s [!1s {!1s []}]})))))


(t/deftest mvr-rp*-test
  (let [!1s [1 2 3]
        !2s [:a :b]]
    (t/is (= '(1 2 3)
             (r.substitute/substitute (!1s ...))))
    (t/is (= '(1 2 3)
             (r.substitute/substitute [!1s ...])))
    (t/is (= [[1] [2] [3]]
             (r.substitute/substitute [[!1s] ...])))
    (t/is (= [[1 :a] [2 :b]]
             (r.substitute/substitute [[!1s !2s] ...])))
    (t/is (= [[:a 1] [:b 2]]
             (r.substitute/substitute [[!2s !1s] ...])))))


(t/deftest mvr-rp+-test
  (let [!1s [1 2 3]
        !2s [:a :b]]
    (t/is (= [[1] [2] [3] [nil]]
             (r.substitute/substitute [[!1s] ..4])))
    (t/is (= [[1 :a] [2 :b] [3 nil] [nil nil]]
             (r.substitute/substitute [[!1s !2s] ..4])))))

(t/deftest rp+-test
  (t/is (= [:A :A :A :A]
           (r.substitute/substitute [:A ..4])))

  (let [!1s [:X :O :X :O]
        !2s [1 2 3 4]]
    (t/is (= [:X 1 :O 2 :X :O 3 4])
          (r.substitute/substitute [!1s !2s ..2 !1s ... !2s ...]))))


(t/deftest unq-test
  (let [x 1]
    (t/is (= [x] (r.substitute/substitute [~x])))))


(t/deftest prt-test
  (t/is (= [1 2]
           (r.substitute/substitute [1 . 2])))

  (t/is (= [1 2]
           (r.substitute/substitute [. 1 2])))

  (t/is (= [1 2]
           (r.substitute/substitute [1 2 .])))

  (let [!1s [:X :O :X :O]]
    (t/is (= [1 2 :X :O :X :O]
             (r.substitute/substitute [1 2 . !1s ...])))
    (t/is (= [:X :O :X :O 1 2]
             (r.substitute/substitute [!1s ... 1 2])))))


(t/deftest map-test
  (let [?rest-map {:foo "bar"}]
    (t/is (= (r.substitute/substitute {:bar "baz" & ?rest-map})
             {:foo "bar", :bar "baz"}))
    (t/is (= (r.substitute/substitute {:bar "baz" & ?rest-map :as "quux"})
             {:foo "bar", :bar "baz", :as "quux"}))
    (t/is (= (r.substitute/substitute {& ?rest-map})
             {:foo "bar"}))
    (t/is (= (r.substitute/substitute {:foo "bar" & [[:bar "foo"] [:quux "frob"]]})
             {:foo "bar" :bar "foo" :quux "frob"}))
    (t/is (= (r.substitute/substitute {:foo "bar" & [[:bar "foo"] [:quux "frob"]]})
             {:foo "bar" :bar "foo" :quux "frob"}))
    (let [!ks [:foo :bar :baz]
          !vs [1111 2222 3333]]
      (t/is (= (r.substitute/substitute {& [[!ks !vs] ...]})
               {:foo 1111, :bar 2222, :baz 3333})))))


(t/deftest ctn-test
  (let [?context (fn [hole]
                   [1 hole 2])
        ?value 3
        !values [:A :B :C]]
    (t/is (= (r.substitute/substitute ($ ?context ?value))
             [1 3 2]))
    (t/is (= (r.substitute/substitute ($ ?value))
             3))
    (t/is (r.substitute/substitute [($ ?context !values) ...])
          [[1 :A 2]
           [1 :B 2]
           [1 :C 2]])))

(t/deftest set-test
  (let [?rest-set #{2 3}]
    (t/is (= #{1 2 3}
             (r.substitute/substitute
              #{1 ^& ?rest-set}))))
  (let [?a-set #{1 4} 
        ?b-set #{2 3}]
    (t/is (= #{1 2 3 4}
             (r.substitute/substitute
              #{^:as ?a-set ^& ?b-set})))))
