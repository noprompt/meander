(ns meander.parse.zeta-test
  (:require [clojure.test :as t]
            [meander.parse.zeta :as m.parse]
            [meander.pattern.zeta :as m.pattern]
            [meander.util.zeta :as m.util :include-macros true]))



(t/deftest parse-test
  (let [x (reify)]
    (t/testing "data parse"
      (t/is (= (m.pattern/data x)
               (m.parse/parse x))))))

#_
(defn with-parse* [f]
  (f (m.parse/parser (m.util/canonical-ns))))

#_
(defmacro with-parse [symbol & body]
  `(with-parse* (fn [~symbol] ~@body)))

#_
(t/deftest parse-variable-test
  (with-parse parse
    (t/testing "parse logic variable"
      (t/is (= (m/logic-variable '?x)
               (parse '?x))))

    (t/testing "parse fifo variable"
      (t/is (= (m/fifo-variable '<x)
               (parse '<x))))

    (t/testing "parse filo variable"
      (t/is (= (m/filo-variable '>x)
               (parse '>x))))

    (t/testing "parse mutable variable"
      (t/is (= (m/mutable-variable '!x)
               (parse '!x))))))

#_
(t/deftest parse-sequential-test
  (with-parse parse
    (t/testing "parse seq"
      (t/is (= (m/seq (m/rx-empty))
               (parse ())))

      (t/is (= (m/seq (m/rx-cat [(m/data 1)]))
               (parse '(1)))))

    (t/testing "parse vector"
      (t/is (= (m/vec (m/rx-empty))
               (parse [])))

      (t/is (= (m/vec (m/rx-cat [(m/data 1)]))
               (parse [1]))))))

#_
(t/deftest parse-special-form-test
  (with-parse parse
    (t/testing "all"
      (t/is (= (m/all (m/data 1) (m/data 2))
               (parse '(m/all 1 2)))))

    (t/testing "greedy repeated subsequences"
      (t/testing "*"
        (t/is (= (m/* (m/rx-empty) (m/rx-empty))
                 (parse '(m/*))))

        (t/is (= (m/* [(m/data 1)])
                 (parse '(m/* 1)))))

      (t/testing "+"
        (t/is (= (m/+ (m/rx-empty) (m/rx-empty))
                 (parse '(m/+))))

        (t/is (= (m/+ [(m/data 1)])
                 (parse '(m/+ 1))))))

    (t/testing "frugal repeated subsequences"
      (t/testing "*?"
        (t/is (= (m/+ (m/rx-empty) (m/rx-empty))
                 (parse '(m/+))))

        (t/is (= (m/+ [(m/data 1)])
                 (parse '(m/+ 1)))))

      (t/testing "+?"
        (t/is (= (m/+ (m/rx-empty) (m/rx-empty))
                 (parse '(m/+))))

        (t/is (= (m/+ [(m/data 1)])
                 (parse '(m/+ 1))))))))

#_
(t/deftest parse-map-test
  (t/is (= (m/merge)
           (parse {})))

  (t/is (= (m/assoc (m/merge) :key "val")
           (parse {:key "val"})))

  (t/is (= (m/merge (m/logic-variable '?rest))
           (parse '{& ?rest})))

  (t/is (= (m/assoc (m/merge (m/logic-variable '?rest)) :key "val")
           (parse '{:key "val" & ?rest}))))

