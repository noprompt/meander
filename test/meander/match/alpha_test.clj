(ns meander.match.alpha-test
  (:require [clojure.test :as t]
            [clojure.test.check :as tc]
            [clojure.test.check.clojure-test :as tc.t]
            [clojure.test.check.generators :as tc.gen]
            [clojure.test.check.properties :as tc.prop]
            [meander.match.alpha :as r.match]))


;; Top level

(t/deftest quote-pattern-test
  (t/is (r.match/match '?x
          '?x true))

  (t/is (r.match/match '!x
          '!x true))

  (t/is (r.match/match '(a . b . c .. d ..2)
          '(a . b . c .. d ..2)
          true))

  (t/is (r.match/match '[a . b . c .. d ..2]
          '[a . b . c .. d ..2]
          true)))


(tc.t/defspec unquote-any-x-matches-any-x
  (tc.prop/for-all [x tc.gen/any]
    (r.match/match x
      ~x
      true

      _
      false)))


(tc.t/defspec _-matches-any-x
  (tc.prop/for-all [x tc.gen/any]
    (r.match/match x
      _
      true)))


(tc.t/defspec ?x-matches-any-x
  (tc.prop/for-all [x tc.gen/any]
    (r.match/match x
      ?x
      true)))


(tc.t/defspec !xs-collects-any-x
  (tc.prop/for-all [x tc.gen/any]
    (r.match/match x
      !xs
      (= [x] !xs))))


;; Seqs

(tc.t/defspec seq-unquote-patterns-match
  (tc.prop/for-all [x tc.gen/any
                    y tc.gen/any]
    (r.match/match (list x (list y x) y)
      (~x (~y ~x) ~y)
      true

      _
      false)))


(tc.t/defspec seq-lvrs-bind-the-values-they-match
  (tc.prop/for-all [x tc.gen/any
                    y tc.gen/any]
    (r.match/match (list x (list y x) y)
      (?x (?x ?x) ?x)
      (= ?x y x)

      (?x (?y ?x) ?y)
      (and (= ?x x)
           (= ?y y))

      _
      false)))


(tc.t/defspec seq-mvrs-collect-the-values-they-match
  (tc.prop/for-all [x tc.gen/any
                    y tc.gen/any]
    (r.match/match (list x (list y x) y)
      (!xs (!ys !xs) !ys)
      (and (= [x x] !xs)
           (= [y y] !ys))

      _
      false)))


(tc.t/defspec seq-zero-or-more-any-in-head
  (tc.prop/for-all [n tc.gen/nat
                    x1 tc.gen/any
                    x2 tc.gen/any
                    y1 tc.gen/any
                    y2 tc.gen/any]
    (r.match/match `(~@(mapcat identity (repeat n [x1 x2])) ~y1 ~y2)
      (~x1 ~x2 ... ~y1 ~y2)
      true)))


(tc.t/defspec seq-zero-or-more-any-in-tail
  (tc.prop/for-all [n tc.gen/nat
                    x1 tc.gen/any
                    x2 tc.gen/any
                    y1 tc.gen/any
                    y2 tc.gen/any]
    (r.match/match `(~x1 ~x2 ~@(mapcat identity (repeat n [y1 y2])))
      (~x1 ~x2 . ~y1 ~y2 ...)
      true)))


(tc.t/defspec seq-zero-or-more-mvr-in-head
  (tc.prop/for-all [n tc.gen/nat
                    x1 tc.gen/any
                    x2 tc.gen/any
                    y1 tc.gen/any
                    y2 tc.gen/any]
    (r.match/match `(~@(mapcat identity (repeat n [x1 x2])) ~y1 ~y2)
      (!xs1 !xs2 ... ~y1 ~y2)
      (and (= (repeat n x1) !xs1)
           (= (repeat n x2) !xs2)))))


(tc.t/defspec seq-zero-or-more-mvr-in-tail
  (tc.prop/for-all [n tc.gen/nat
                    x1 tc.gen/any
                    x2 tc.gen/any
                    y1 tc.gen/any
                    y2 tc.gen/any]
    (r.match/match `(~x1 ~x2 ~@(mapcat identity (repeat n [y1 y2])))
      (~x1 ~x2 . !ys1 !ys2 ...)
      (and (= (repeat n y1) !ys1)
           (= (repeat n y2) !ys2)))))


(tc.t/defspec seq-zero-or-more-lvr-in-head
  (tc.prop/for-all [n (tc.gen/such-that (complement zero?) tc.gen/nat)
                    x1 tc.gen/any
                    x2 tc.gen/any
                    y1 tc.gen/any
                    y2 tc.gen/any]
    (r.match/match `(~@(mapcat identity (repeat n [x1 x2])) ~y1 ~y2)
      (?x1 ?x2 ... ~y1 ~y2)
      (and (= x1 ?x1)
           (= x2 ?x2)))))


(tc.t/defspec seq-drop-in-head
  (tc.prop/for-all [n tc.gen/nat
                    x tc.gen/any]
    (r.match/match `(~@(map identity (repeat n x)) ~x ~x)
      (_ ... ~x ~x)
      true)))


(tc.t/defspec seq-drop-in-tail
  (tc.prop/for-all [n tc.gen/nat
                    x tc.gen/any]
    (r.match/match `(~@(map identity (repeat n x)) ~x ~x)
      (~x ~x . _ ...)
      true)))


;; Vectors

(tc.t/defspec vec-unquote-patterns-match
  (tc.prop/for-all [x tc.gen/any
                    y tc.gen/any]
    (r.match/match [x [y x] y]
      [~x [~y ~x] ~y]
      true

      _
      false)))


(tc.t/defspec vec-mvrs-bind-the-values-they-match
  (tc.prop/for-all [x tc.gen/any
                    y tc.gen/any]
    (r.match/match [x [y x] y]
      [?x [?x ?x] ?x]
      (= ?x y x)

      [?x [?y ?x] ?y]
      (and (= ?x x)
           (= ?y y))

      _
      false)))


(tc.t/defspec vec-lvrs-collect-the-values-they-match
  (tc.prop/for-all [x tc.gen/any
                    y tc.gen/any]
    (r.match/match [x [y x] y]
      [!xs [!ys !xs] !ys]
      (and (= [x x] !xs)
           (= [y y] !ys))

      _
      false)))


(tc.t/defspec vec-zero-or-more-any-in-head
  (tc.prop/for-all [n tc.gen/nat
                    x1 tc.gen/any
                    x2 tc.gen/any
                    y1 tc.gen/any
                    y2 tc.gen/any]
    (r.match/match `[~@(mapcat identity (repeat n [x1 x2])) ~y1 ~y2]
      [~x1 ~x2 ... ~y1 ~y2]
      true)))


(tc.t/defspec vec-zero-or-more-any-in-tail
  (tc.prop/for-all [n tc.gen/nat
                    x1 tc.gen/any
                    x2 tc.gen/any
                    y1 tc.gen/any
                    y2 tc.gen/any]
    (r.match/match `[~x1 ~x2 ~@(mapcat identity (repeat n [y1 y2]))]
      [~x1 ~x2 . ~y1 ~y2 ...]
      true)))


(tc.t/defspec vec-zero-or-more-mvr-in-head
  (tc.prop/for-all [n tc.gen/nat
                    x1 tc.gen/any
                    x2 tc.gen/any
                    y1 tc.gen/any
                    y2 tc.gen/any]
    (r.match/match `[~@(mapcat identity (repeat n [x1 x2])) ~y1 ~y2]
      [!xs1 !xs2 ... ~y1 ~y2]
      (and (= (repeat n x1) !xs1)
           (= (repeat n x2) !xs2)))))


(tc.t/defspec vec-zero-or-more-mvr-in-tail
  (tc.prop/for-all [n tc.gen/nat
                    x1 tc.gen/any
                    x2 tc.gen/any
                    y1 tc.gen/any
                    y2 tc.gen/any]
    (r.match/match `[~x1 ~x2 ~@(mapcat identity (repeat n [y1 y2]))]
      [~x1 ~x2 . !ys1 !ys2 ...]
      (and (= (repeat n y1) !ys1)
           (= (repeat n y2) !ys2)))))


(tc.t/defspec vec-zero-or-more-lvr-in-head
  (tc.prop/for-all [n (tc.gen/such-that (complement zero?) tc.gen/nat)
                    x1 tc.gen/any
                    x2 tc.gen/any
                    y1 tc.gen/any
                    y2 tc.gen/any]
    (r.match/match `[~@(mapcat identity (repeat n [x1 x2])) ~y1 ~y2]
      [?x1 ?x2 ... ~y1 ~y2]
      (and (= x1 ?x1)
           (= x2 ?x2)))))


(tc.t/defspec vec-drop-in-head
  (tc.prop/for-all [n tc.gen/nat
                    x tc.gen/any]
    (r.match/match `[~@(map identity (repeat n x)) ~x ~x]
      [_ ... ~x ~x]
      true)))


(tc.t/defspec vec-drop-in-tail
  (tc.prop/for-all [n tc.gen/nat
                    x tc.gen/any]
    (r.match/match `[~@(map identity (repeat n x)) ~x ~x]
      [~x ~x . _ ...]
      true)))
