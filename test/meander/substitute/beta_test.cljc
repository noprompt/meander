(ns meander.substitute.beta-test
  (:require [clojure.test :as t]
            [meander.substitute.beta :as r.substitute :include-macros true]))

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
           (r.substitute/substitute [. 1 2])))

  (t/is (= [1 2]
           (r.substitute/substitute [1 . 2])))

  (t/is (= [1 2]
           (r.substitute/substitute [1 2 .])))

  (let [!1s [:X :O :X :O]]
    (t/is (= [1 2 :X :O :X :O]
             (r.substitute/substitute [1 2 . !1s ...])))
    (t/is (= [:X :O :X :O 1 2]
             (r.substitute/substitute [!1s ... 1 2])))))

