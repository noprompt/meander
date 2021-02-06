(ns meander.environment.code.zeta-test
  (:require [clojure.test :as t]
            [meander.util.zeta :as m.util]
            [meander.environment.code.zeta :as m.environment.code]))

(defmacro => [a b]
  `(t/is (= '~b (m.environment.code/partial-evaluate '~a))))

(t/deftest partial-evaluate-test
  (t/testing "clojure.core/assoc"
    (=> (clojure.core/assoc (clojure.core/assoc A :key 1) :key 2)
        (clojure.core/assoc A :key 2)))

  (t/testing "clojure.core/get"
    (=> (clojure.core/get (clojure.core/assoc A :object (clojure.core/get B :object)) :object)
        (clojure.core/get B :object)))

  (t/testing "clojure.core/="
    (=> (clojure.core/= 1 1) true)
    (=> (clojure.core/= 1 2) false))

  (t/testing "clojure.core/identity"
    (=> (clojure.core/identity x) x)
    (=> (clojure.core/identity (clojure.core/identity x)) x))

  (t/testing "if"
    (=> (if true 1 2) 1)
    (=> (if false 1 2) 2)
    (=> (if (clojure.core/= 1 1) 1 2) 1)
    (=> (if (clojure.core/= 1 2) 1 2) 2))

  (t/testing "let*"
    (=> (let* [a b] c)
        (let* [a b] c))
    (=> (let* [a (clojure.core/= 1 2)] b)
        (let* [a false] b))))

