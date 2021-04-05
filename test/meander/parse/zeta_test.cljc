(ns meander.parse.zeta-test
  (:require [clojure.test :as t]
            [meander.core.zeta :as m]
            [meander.util.zeta :as m.util :include-macros true]
            [meander.parse.zeta :as m.parse]))

(defn with-parse* [f]
  (f (m.parse/parser (m.util/canonical-ns))))

(defmacro with-parse [symbol & body]
  `(with-parse* (fn [~symbol] ~@body)))

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

(t/deftest parse-sequential-test
  (with-parse parse
    (t/testing "parse seq"
      (t/is (= (m/seq (m/data ()))
               (parse ())))

      (t/is (= (m/seq (m/rx-cat [1]))
               (parse '(1)))))

    #_
    (t/testing "parse vector"
      (t/is (= (m/vec (m/data []))
               (parse [])))

      (t/is (= (m/vec (m/rx-cat [1]))
               (parse [1]))))))

(t/deftest parse-map-test
  (t/is (= (m/merge)
           (parse {})))

  (t/is (= (m/assoc (m/merge) :key "val")
           (parse {:key "val"})))

  (t/is (= (m/merge (m/logic-variable '?rest))
           (parse '{& ?rest})))

  (t/is (= (m/assoc (m/merge (m/logic-variable '?rest)) :key "val")
           (parse '{:key "val" & ?rest}))))

