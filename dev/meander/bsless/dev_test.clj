(ns meander.bsless.dev-test
  (:require
   [meander.bsless.dev :as sut]
   [clojure.test :as t]))

(t/deftest monad-laws
  (t/testing "left identity"
    (t/testing "ISeq"
      (let [f #(range %)
            a 4]
        (t/is (= (sut/-bind (sut/-return () a) f) (f a)))))
    #_
    (t/testing "Fn"
      (let [f #(range %)
            ctx (fn [])
            a '(4)]
        ((-bind (-return ctx a) f))
        #_(t/is (= (-bind (-return ctx a) f) (f a))))))
  (t/testing "Right identity"
    (t/testing "ISeq"
      (let [a '(1)]
        (t/is (= a (sut/-bind a (partial sut/-return ()))))))))

(t/deftest sequence-monad
  (t/is (= '(0 0 1 0 1 2)
           (sut/-bind '(1 2 3) (fn [x] (range x))))))

(t/deftest monad-plus
  (t/is (= () (sut/-mzero '(1 2 3))))
  (t/is (= '(1 2 3 4 5 6) (sut/-mplus '(1 2 3) '(4 5 6)))))

(t/deftest msplit
  (t/is (= '([1 (2 3 4)]) (sut/-msplit '(1 2 3 4))))
  (t/is (= '(nil) (sut/-msplit '())))
  (t/is (= '([1 ()]) (sut/-msplit '(1)))))

(t/deftest -interleave
  (t/is (= '(1 4 2 5 3 6) (sut/-interleave '(1 2 3) '(4 5 6))))
  (t/is (= [1 0 2 1 3 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
            21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38]
           (take 42 (sut/-interleave '(1 2 3) (range))))))

(t/deftest interweave
  (t/is
   (= '{a 50 b 25 c 24} (frequencies (take 99 (sut/>>- '(a b c) (fn [x] (repeat x)))))))
  (t/is
   (= '{a 100 b 50 c 25 d 25}
      (frequencies (take 200 (sut/>>- '(a b c d) (fn [x] (repeat x)))))))
  (t/is
   (= '{a 25 b 25 c 25 d 25}
      (frequencies
       (take
        100
        (sut/-interleave
         (sut/>>- '(a b) (fn [x] (repeat x)))
         (sut/>>- '(c d) (fn [x] (repeat x))))))))
  (t/is
   (t/is
    (=
     '(a0 b0 a1 b1 a2 b2 a3 b3 a4 b4 a5 b5 a6 b6 a7 b7 a8 b8 a9 b9)
     (sut/>>- '(a b) (fn [a] (map #(symbol (str a %)) (range 10))))))))
