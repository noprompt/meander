(ns meander.core-test
  (:require [clojure.test :as t]
            [meander.core :as r]))

;; ---------------------------------------------------------------------
;; t-test

(def dot-form-t1
  ;; (.foo bar baz)
  (r/t (~method ~target ~@args)
    :when (symbol? method)
    :let [method-name (name method)]
    :when (re-find #"\A\.." method-name)
    :let [method* (symbol (subs method-name 1))]
    (. ~target ~method* ~@args)))


(def dot-form-t2
  ;; (. bar (foo baz))
  (r/t (. ~target (~method ~@args))
    :when (symbol? method)
    (. ~target ~method ~@args)))

(t/deftest t-test
  (t/testing "transforms implement IFn"
    (t/is (= '(. bar foo baz)
             (dot-form-t1 '(.foo bar baz)))))

  (t/testing "transforms are unifiable"
    (t/is (= '({"args" (baz)
                "method" .foo
                "target" bar})
             (r/unify* dot-form-t1 '(.foo bar baz) {})))

    (t/is (= '()
             (r/unify* dot-form-t1 '(. bar (foo baz)) {})))))







