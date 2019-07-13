(ns meander.syntax.epsilon-test
  (:require [clojure.test :as t]
            [clojure.test.check.clojure-test :as tc.t :include-macros true]
            [clojure.test.check.generators :as tc.gen :include-macros true]
            [clojure.test.check.properties :as tc.prop :include-macros true]
            [meander.match.epsilon :as r.match :include-macros true]
            [meander.epsilon :as m :include-macros true]))

;; rewrite

(defn fib-identity [t]
  (m/rewrite t
    (+ 0 ?x)
    ?x

    (+ ?x 0)
    ?x))

(defn fib-add [t]
  (m/rewrite t
    (+ (s ?x) ?y)
    (s (+ ?x ?y))))

(defn fib-0 [t]
  (m/rewrite t
    (fib 0)
    0))

(defn fib-1 [t]
  (m/rewrite t
    (fib (s 0))
    (s 0)))

(defn fib-n [t]
  (m/rewrite t
    (fib (s (s ?x)))
    (+ (fib (s ?x))
       (fib ?x))))

(t/deftest fib-rewrite-test
  (t/testing "fib 0"
    (t/is (= 0
             (fib-0 '(fib 0)))))

  (t/testing "fib 1"
    (t/is (= '(s 0)
             (fib-1 '(fib (s 0))))))

  (t/testing "fib n"
    (t/is (= '(+ (fib (s 0))
                 (fib 0))
             (fib-n '(fib (s (s 0)))))))

  (t/testing "fib-identity"
    (t/is (= '(s 0)
             (fib-identity '(+ (s 0) 0))))

    (t/is (= '(s 0)
             (fib-identity '(+ 0 (s 0))))))

  (t/testing "fib-add"
    (t/is (=  '(s (+ 0 0))
              (fib-add '(+ (s 0) 0))))))
