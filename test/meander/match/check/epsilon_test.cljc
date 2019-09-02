(ns meander.match.check.epsilon-test
  (:require [clojure.spec.alpha :as s :include-macros true]
            [clojure.test :as t]
            [clojure.test.check.clojure-test :as tc.t :include-macros true]
            [clojure.test.check.generators :as tc.gen :include-macros true]
            [clojure.test.check.properties :as tc.prop :include-macros true]
            [meander.epsilon :as m :include-macros true]
            [meander.match.check.epsilon :as r.match.check :include-macros true]
            [meander.match.syntax.epsilon :as r.match.syntax :include-macros true]
            [meander.syntax.epsilon :as r.syntax :include-macros true]))

(t/deftest no-value-before-zero-or-more
  (t/testing "match"
    (let [error (r.match.check/check (r.match.syntax/parse '[... ?x] {}) false)]
      (t/is (= "Zero or more (...) is a postfix operator. It must have some value in front of it. (i.e. [1 ... ?x])"
               #?(:clj (.getMessage error)
                  :cljs (.-message error))))))

  (t/testing "search"
    (let [error (r.match.check/check (r.match.syntax/parse '[... ?x] {}) true)]
      (t/is (= "Zero or more (...) is a postfix operator. It must have some value in front of it. (i.e. [1 ... ?x])"
               #?(:clj (.getMessage error)
                  :cljs (.-message error)))))))


(t/deftest no-value-before-one-or-more
  (t/testing "match"
    (let [error (r.match.check/check (r.match.syntax/parse '[..2 ?x] {}) false)]
      (t/is (= "N or more (..N) is a postfix operator. It must have some value in front of it. (i.e. [1 ..2 ?x])"
               #?(:clj (.getMessage error)
                  :cljs (.-message error))))))

  (t/testing "search"
    (let [error (r.match.check/check (r.match.syntax/parse '[..2 ?x] {}) true)]
      (t/is (= "N or more (..N) is a postfix operator. It must have some value in front of it. (i.e. [1 ..2 ?x])"
               #?(:clj (.getMessage error)
                  :cljs (.-message error)))))))


(t/deftest no-value-after-one-or-more
  (t/testing "match"
    (let [error (r.match.check/check (r.match.syntax/parse '[1 .. ?x] {}) false)]
      (t/is (= "Ambiguous ellipsis. Perhaps you meant the n or more operator (..N) or the zero or more operator (...)?"
                #?(:clj (.getMessage error)
                   :cljs (.-message error))))))

  (t/testing "search"
    (let [error (r.match.check/check (r.match.syntax/parse '[1 .. ?x] {}) true)]
      (t/is (= "Ambiguous ellipsis. Perhaps you meant the n or more operator (..N) or the zero or more operator (...)?"
               #?(:clj (.getMessage error)
                  :cljs (.-message error)))))))

(t/deftest with-has-duplicate-references
  #?(:clj
     ;; *ns* is user when tests run.
     (binding [*ns* (the-ns 'meander.match.check.epsilon-test)]
       (t/testing "match"
         (t/testing "no body"
           (let [error (r.match.check/check (r.match.syntax/parse '(r.syntax/with [%1 1 %1 1]) {}) false)]
             (t/is (nil? error))))
         (t/testing "body"
           (let [error (r.match.check/check (r.match.syntax/parse '(r.syntax/with [%1 1 %1 1] %1) {}) false)]
             (t/is (= "with patterns must have distinct references"
                      #?(:clj (.getMessage error)
                         :cljs (.-message error)))))))

       (t/testing "search"
         (t/testing "no body"
           (let [error (r.match.check/check (r.match.syntax/parse '(r.syntax/with [%1 1 %1 1]) {}) true)]
             (t/is (nil? error))))
         (t/testing "body"
           (let [error (r.match.check/check (r.match.syntax/parse '(r.syntax/with [%1 1 %1 1] %1) {}) true)]
             (t/is (= "with patterns must have distinct references"
                      #?(:clj (.getMessage error)
                         :cljs (.-message error))))))))
     :cljs
     (do
       #_
       (t/testing "match"
         (t/testing "no body"
           (let [error (r.match.check/check (r.match.syntax/parse '(r.syntax/with [%1 1 %1 1]) {}) false)]
             (t/is (nil? error))))
         (t/testing "body"
           (let [error (r.match.check/check (r.match.syntax/parse '(r.syntax/with [%1 1 %1 1] %1) {}) false)]
             (t/is (= "with patterns must have distinct references"
                      #?(:clj (.getMessage error)
                         :cljs (.-message error)))))))

       #_
       (t/testing "search"
         (t/testing "no body"
           (let [error (r.match.check/check (r.match.syntax/parse '(r.syntax/with [%1 1 %1 1]) {}) true)]
             (t/is (nil? error))))
         (t/testing "body"
           (let [error (r.match.check/check (r.match.syntax/parse '(r.syntax/with [%1 1 %1 1] %1) {}) true)]
             (t/is (= "with patterns must have distinct references"
                      #?(:clj (.getMessage error)
                         :cljs (.-message error))))))))))


(t/deftest match-doesnt-allow-maps-where-we-would-need-to-search
  (t/are [x] (not (nil? x))
    (r.match.check/check (r.match.syntax/parse '{?x ?y} {}) false)
    (r.match.check/check (r.match.syntax/parse '{(meander.epsilon/pred number?) ?y} {}) false)
    (r.match.check/check (r.match.syntax/parse '{(meander.epsilon/scan 0) ?y} {}) false)
    (r.match.check/check (r.match.syntax/parse '{!xs ?y} {}) false)
    (r.match.check/check (r.match.syntax/parse '{_ ?y} {}) false)
    (r.match.check/check (r.match.syntax/parse '{[_] ?y} {}) false)
    (r.match.check/check (r.match.syntax/parse '{{:a ?x} ?y} {}) false)
    (r.match.check/check (r.match.syntax/parse '(meander.epsilon/with [%x (m/pred number?)] {%x ?y}) {}) false))

  (t/are [x] (nil? x)
    (r.match.check/check (r.match.syntax/parse '{:a ?y} {}) false)
    (r.match.check/check (r.match.syntax/parse '[?x {?x ?y}] {}) false)
    (r.match.check/check (r.match.syntax/parse '{:x (meander.epsilon/pred number?)} {}) false)
    (r.match.check/check (r.match.syntax/parse '{:x [_ ... 0]} {}) false)
    (r.match.check/check (r.match.syntax/parse '{:x !xs} {}) false)
    (r.match.check/check (r.match.syntax/parse '{:name ?name
                                                 :thing {?name ?y}} {}) false)
    (r.match.check/check (r.match.syntax/parse '{:thing {?name ?y}
                                                 :name ?name} {}) false)
    (r.match.check/check (r.match.syntax/parse '{{:a :b} ?y} {}) false)))
