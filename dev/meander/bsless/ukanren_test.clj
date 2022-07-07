(ns meander.bsless.ukanren-test
  (:require
   [meander.bsless.ukanren
    :as sut
    :refer [->LVar -extend -walk lvar occurs-check ext-s unify
            === call-fresh -conj -disj]]
   [clojure.test :as t]))

(t/deftest walk-test
  (let [v1 (->LVar 1)
        v2 (->LVar 2)
        v3 (->LVar 3)
        s (-> {} (-extend v1 1) (-extend v2 v1))]
    (t/testing "LVar mapped to simple value"
      (t/is (= 1 (-walk s v1))))
    (t/testing "Transitive mapping: v2 -> v1 -> value"
      (t/is (= 1 (-walk s v2))))
    (t/testing "LVar not mapped to anything is returned"
      (t/is (= v3 (-walk s v3))))
    (t/testing "Value not mapped to anything is returned"
      (t/is (= 4 (-walk s 4)))))
  (t/is (= (let [x  (lvar 'x)
                 y  (lvar 'y)
                 s (reduce (fn [m [x y]] (-extend m x y)) {} [[x 5] [y x]])]
             (-walk s y))
           5))
  (t/is (= (let [[x y z c b a] (map lvar '[x y z c b a])
                 s (reduce (fn [m [x y]] (-extend m x y)) {} [[x 5] [y x] [z y] [c z] [b c] [a b]])]
             (-walk s a))
           5)))

(t/deftest occurance
  (let [v1 (->LVar 1)
        v2 (->LVar 2)
        v3 (->LVar 3)
        v4 (->LVar 4)
        s (-> {} (-extend v1 1) (-extend v2 v1) (-extend v3 v4))]
    (t/is (false? (occurs-check v1 v2 s)))
    (t/is (false? (occurs-check v1 v1 s)))
    (t/is (false? (occurs-check v2 v1 s)))
    (t/is (false? (occurs-check v2 1 s)))
    (t/is (nil? (occurs-check 1 v2 s)))
    (t/is (false? (occurs-check v3 3 s)))
    (t/is (nil? (occurs-check 3 v3 s)))
    (t/is (false? (occurs-check v2 v3 s)))
    (t/is (true? (occurs-check v4 v3 s)))))

(t/deftest extension
  (t/testing "Can't extend ground variable to another value"
    (let [v1 (lvar 1)
          s (-extend {} v1 1)]
      (ext-s v1 2 s))))

(t/deftest unification
  (t/testing "nil"
    (t/testing "Can't unify nil with object"
      (t/is (nil? (unify nil 1 {}))))
    (t/testing "Can unify lvar and nil"
      (let [x (lvar 'x) s (-extend {} x nil)]
        (t/is (= s (unify nil x {})))
        (t/is (= s (unify x nil {}))))))
  (t/testing "Objects"
    (t/testing "Failing unification"
      (t/is (nil? (unify 1 nil {})))
      (t/is (nil? (unify 1 2 {})))
      (t/is (nil? (unify 2 1 {})))
      (t/is (nil? (unify "b" "a" {}))))
    (t/testing "Unmodified unification"
      (t/is (= {} (unify 1 1 {})))
      (t/is (= {} (unify 'a 'a {})))
      (t/is (= {} (unify :a :a {})))
      (t/is (= {} (unify "a" "a" {}))))))

(t/deftest basic-unification
  (let [ret ((call-fresh (fn [q] (=== q 5))) {})]
    (t/is (= 1 (count ret)))
    (t/is (= 1 (count (first ret))))
    (t/is (= 5 (val (ffirst ret))))))

(t/deftest basic-and-or
  (let [a-and-b
        (-conj
         (call-fresh (fn [a] (=== a 7)))
         (call-fresh (fn [b] (-disj (=== b 5) (=== b 6)))))]
    (t/is (= '((7 5) (7 6)) (map vals (a-and-b {}))))))

(t/deftest contradiction
  (t/testing "Should be no answers for which this holds"
    (t/is
     (nil? (seq ((call-fresh
                  (fn [q]
                    (-conj
                     (=== q 1)
                     (=== q 2))))
                 {}))))))

(t/deftest one-or-branch
  (t/is (= '((7))
           (map
            vals
            ((call-fresh
              (fn [q]
                (-conj
                 (=== q 7)
                 (-disj (=== q 5) (=== q 7))))) {})))))

(t/deftest infinite-stream-test
  (t/is
   (= (repeat 33 6)
      (->> {}
           ((call-fresh
             (fn sixes [x]
               (-disj (=== x 6) (fn [s] (lazy-seq ((sixes x) s)))))))
           (take 33)
           (mapcat vals)))))