(ns meander.strategy.delta-test
  (:require
   [clojure.test :as t]
   [clojure.test.check.clojure-test :as tc.t :include-macros true]
   [clojure.test.check.generators :as tc.gen :include-macros true]
   [clojure.test.check.properties :as tc.prop :include-macros true]
   [meander.match.delta :as r.match :include-macros true]
   [meander.util.delta :as r.util :include-macros true]
   [meander.strategy.delta :as r :include-macros true]))

(t/deftest pipe-fail-test
  (t/testing "If any strategy to pipe fails then so does pipe."
    (run!
     (fn [f]
       (t/is (r/fail? (f 1))))
     (sequence
      (comp (map
             (fn [n]
               (cons inc (repeat (inc n) r/fail))))
            (mapcat r.util/permutations)
            (map
             (fn [fs]
               (apply r/pipe fs))))
      (range 4)))))

(t/deftest choice-fail-test
  (t/testing "If every strategy to choice fails then so does choice."
    (run!
     (fn [f]
       (t/is (r/fail? (f 1))))
     (sequence
      (map
       (fn [n]
         (apply r/choice (repeat n r/fail))))
      (range 6))))

  (t/testing "If at least one strategy to choice succeeds then so does choice."
    (run!
     (fn [f]
       (t/is (= 2 (f 1))))
     (sequence
      (comp (map
             (fn [n]
               (cons inc (repeat n r/fail))))
            (mapcat r.util/permutations)
            (map
             (fn [fs]
               (apply r/choice fs))))
      (range 5)))))

(defn inc-number
  "Strategy which increments numbers."
  [x]
  (if (number? x)
    (inc x)
    r/*fail*))

;; ---------------------------------------------------------------------
;; one

(def inc-number-one
  "inc-number strategy utilizing the one combinator."
  (r/one inc-number))

(t/deftest one-seq-test
  (t/testing "one with seq? like objects"
    (t/is (r/fail? (inc-number-one '())))  
    (t/is (r/fail? (inc-number-one '(a))))
    (t/is (= '(2)
             (inc-number-one '(1))))
    (t/is (= '(2 a)
             (inc-number-one '(1 a))))
    (t/is (= '(a 2)
             (inc-number-one '(a 1))))
    (t/is (= '(a 2 b)
             (inc-number-one '(a 1 b))))
    (t/is (= '(a b 2)
             (inc-number-one '(a b 1))))))

(t/deftest one-vector-test
  (t/testing "one with vector? like objects"
    (t/is (r/fail? (inc-number-one [])))  
    (t/is (r/fail? (inc-number-one '[a])))
    (t/is (= '[2]
             (inc-number-one '[1])))
    (t/is (= '[2 a]
             (inc-number-one '[1 a])))
    (t/is (= '[a 2]
             (inc-number-one '[a 1])))
    (t/is (= '[a 2 b]
             (inc-number-one '[a 1 b])))
    (t/is (= '[a b 2]
             (inc-number-one '[a b 1])))))

(t/deftest one-set-test
  (t/testing "one with set? like objects"
    (t/is (r/fail? (inc-number-one #{})))  
    (t/is (r/fail? (inc-number-one '#{a})))
    (t/is (= '#{2}
             (inc-number-one '#{1})))
    (t/is (= '#{2 a}
             (inc-number-one '#{1 a})))
    (t/is (= '#{a 2}
             (inc-number-one '#{a 1})))
    (t/is (= '#{a 2 b}
             (inc-number-one '#{a 1 b})))
    (t/is (= '#{a b 2}
             (inc-number-one '#{a b 1})))))

(t/deftest one-map-test
  (t/testing "one with map? like objects"
    (t/is (r/fail? (inc-number-one {})))
    (t/is (r/fail? (inc-number-one {:a :a})))
    (t/is (= {:a 2}
             (inc-number-one {:a 1})))))

;; ---------------------------------------------------------------------
;; some

(def inc-number-some
  "inc-number strategy utilizing the some combinator."
  (r/some inc-number))

(t/deftest some-seq-test
  (t/testing "some with seq? like objects"
    (t/is (r/fail? (inc-number-some '())))  
    (t/is (r/fail? (inc-number-some '(a))))
    (t/is (= '(2)
             (inc-number-some '(1))))
    (t/is (= '(2 a)
             (inc-number-some '(1 a))))
    (t/is (= '(a 2)
             (inc-number-some '(a 1))))
    (t/is (= '(a 2 b)
             (inc-number-some '(a 1 b))))
    (t/is (= '(a b 2)
             (inc-number-some '(a b 1))))
    (t/is (= '(a 1 b 2)
             (inc-number-some '(a 0 b 1))))))

(t/deftest some-vector-test
  (t/testing "some with vector? like objects"
    (t/is (r/fail? (inc-number-some [])))  
    (t/is (r/fail? (inc-number-some '[a])))
    (t/is (= '[2]
             (inc-number-some '[1])))
    (t/is (= '[2 a]
             (inc-number-some '[1 a])))
    (t/is (= '[a 2]
             (inc-number-some '[a 1])))
    (t/is (= '[a 2 b]
             (inc-number-some '[a 1 b])))
    (t/is (= '[a b 2]
             (inc-number-some '[a b 1])))
    (t/is (= '[a 1 b 2]
             (inc-number-some '[a 0 b 1])))))

(t/deftest some-set-test
  (t/testing "some with set? like objects"
    (t/is (r/fail? (inc-number-some #{})))  
    (t/is (r/fail? (inc-number-some '#{a})))
    (t/is (= '#{2}
             (inc-number-some '#{1})))
    (t/is (= '#{2 a}
             (inc-number-some '#{1 a})))
    (t/is (= '#{a 2}
             (inc-number-some '#{a 1})))
    (t/is (= '#{a 2 b}
             (inc-number-some '#{a 1 b})))
    (t/is (= '#{a b 2}
             (inc-number-some '#{a b 1})))
    (t/is (= '#{a 1 b 2}
             (inc-number-some '#{a 0 b 1})))))

(t/deftest some-map-test
  (t/testing "some with map? like objects"
    (t/is (r/fail? (inc-number-some {})))
    (t/is (r/fail? (inc-number-some {:a :a})))
    (t/is (= {:a 2 :b 3}
             (inc-number-some {:a 1 :b 2})))))

;; ---------------------------------------------------------------------
;; all

(def inc-number-all
  "inc-number strategy utilizing the all combinator."
  (r/all inc-number))

(t/deftest all-seq-test
  (t/testing "all with seq? like objects"
    (t/is (= '()
             (inc-number-all '())))  
    (t/is (r/fail? (inc-number-all '(a))))
    (t/is (r/fail? (inc-number-all '(1 a))))
    (t/is (r/fail? (inc-number-all '(a b 1))))
    (t/is (= '(2)
             (inc-number-all '(1))))
    (t/is (= '(2 3 4)
             (inc-number-all '(1 2 3))))))

(t/deftest all-vector-test
  (t/testing "all with vector? like objects"
    (t/is (= []
             (inc-number-all [])))  
    (t/is (r/fail? (inc-number-all '[a])))
    (t/is (r/fail? (inc-number-all '[1 a])))
    (t/is (r/fail? (inc-number-all '[a b 1])))
    (t/is (= [2]
             (inc-number-all [1])))
    (t/is (= [2 3 4]
             (inc-number-all [1 2 3])))))

(t/deftest all-set-test
  (t/testing "all with set? like objects"
    (t/is (= #{}
             (inc-number-all #{})))  
    (t/is (r/fail? (inc-number-all '#{a})))
    (t/is (r/fail? (inc-number-all '#{1 a})))
    (t/is (r/fail? (inc-number-all '#{a b 1})))
    (t/is (= #{2}
             (inc-number-all #{1})))
    (t/is (= #{2 3 4}
             (inc-number-all #{1 2 3})))))

(t/deftest all-map-test
  (t/testing "all with map? like objects"
    (t/is (= {}
             (inc-number-all {})))
    (t/is (r/fail? (inc-number-all {:a :a})))
    (t/is (r/fail? (inc-number-all {:a 1 :b 2})))
    (t/is (= {1 2 11 12}
             (inc-number-all {0 1 10 11})))))


(t/deftest retain-test
  (t/is (= '(2 3 4)
           (r/retain (r/pipe (r/pred number?) inc)
                      '(1 :a 2 :b 3 :c))))

  (t/is (= '(2 3 4)
           ((r/retain (r/pipe (r/pred number?) inc))
            '(1 :a 2 :b 3 :c))))

  (t/is (= [2 3 4]
           (r/retain (r/pipe (r/pred number?) inc)
                      [1 :a 2 :b 3 :c])))

  
  (t/is (= [2 3 4]
           ((r/retain (r/pipe (r/pred number?) inc))
            [1 :a 2 :b 3 :c])))

  (t/is (= #{2 3 4}
           (r/retain (r/pipe (r/pred number?) inc)
                      #{1 :a 2 :b 3 :c})))

  
  (t/is (= #{2 3 4}
           ((r/retain (r/pipe (r/pred number?) inc))
            #{1 :a 2 :b 3 :c})))

  (t/is (= {:a 1, :c 2}
           (r/retain (r/pipe
                       (r/match
                         [?k (pred number? ?v)]
                         [?k (inc ?v)]))
                      {:a 0, :b "B", :c 1, :d "D"})))

  (t/is (= {:a 1, :c 2}
           ((r/retain (r/pipe
                        (r/match
                          [?k (pred number? ?v)]
                          [?k (inc ?v)])))
            {:a 0, :b "B", :c 1, :d "D"}))))


(t/deftest tuple-test
  (doseq [fs (reductions conj [inc] (map (constantly inc) (range 0 4)))]
    (let [s (apply r/tuple fs)
          v (s 1)]
      (t/is (= (count v) (count fs)))
      (t/is (every? #{2} v)))))


(t/deftest rewrite-test
  (let [fib-rules (r/rewrite
                   (+ 0 ?y)
                   ?y

                   (+ ?y 0)
                   ?y

                   (+ (s ?x) ?y)
                   (s (+ ?x ?y))

                   (fib 0)
                   0

                   (fib (s 0))
                   (s 0)

                   (fib (s (s ?x)))
                   (+ (fib (s ?x))
                      (fib ?x))

                   ?else
                   ?else)
        fib (r/until = (r/bottom-up fib-rules))]
    ;; F1 = 1
    (t/is (= '(s 0) (fib '(fib (s 0)))))
    ;; F2 = 1
    (t/is (= '(s 0) (fib '(fib (s (s 0))))))
    ;; F3 = 2
    (t/is (= '(s (s 0)) (fib '(fib (s (s (s 0)))))))
    ;; F4 = 3
    (t/is (= '(s (s (s 0))) (fib '(fib (s (s (s (s 0))))))))
    ;; F5 = 5
    (t/is (= '(s (s (s (s (s 0))))) (fib '(fib (s (s (s (s (s 0)))))))))
    ;; F6 = 8
    (t/is (= '(s (s (s (s (s (s (s (s 0)))))))) (fib '(fib (s (s (s (s (s (s 0))))))))))))
