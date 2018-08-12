(ns meander.match.alpha-test
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as s.gen]
            [clojure.test :as t]
            [clojure.test.check :as tc]
            [clojure.test.check.clojure-test :as tc.t]
            [clojure.test.check.generators :as tc.gen]
            [clojure.test.check.properties :as tc.prop]
            [meander.match.alpha :as r.match]
            [meander.syntax.alpha :as r.syntax]))


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
      (= [x] !xs)

      _
      false)))


(tc.t/defspec any-x-cap-lvr
  (tc.prop/for-all [x tc.gen/any]
    (r.match/match x
      (~x :as ?x)
      (= x ?x)

      _
      false)))


(tc.t/defspec any-x-cap-mvr
  (tc.prop/for-all [x tc.gen/any]
    (r.match/match x
      (~x :as !xs)
      (= [x] !xs)

      _
      false)))


(tc.t/defspec lvr-cap-lvr
  (tc.prop/for-all [x tc.gen/any]
    (r.match/match x
      (?x :as ?y)
      (= x ?x ?y)

      _
      false)))


(tc.t/defspec lvr-cap-mvr
  (tc.prop/for-all [x tc.gen/any]
    (r.match/match x
      (?x :as !xs)
      (and (= x ?x)
           (= [x] !xs))

      _
      false)))


(tc.t/defspec mvr-cap-lvr
  (tc.prop/for-all [x tc.gen/any]
    (r.match/match x
      (!xs :as ?x)
      (and (= x ?x)
           (= [x] !xs))

      _
      false)))


(tc.t/defspec circular-cap-fails
  (tc.prop/for-all [x tc.gen/any]
    (r.match/match x
      ((([?x] :as ?z) :as ?y) :as ?x)
      false

      _
      true)))


(tc.t/defspec ?x-cap-?x-succeeds
  (tc.prop/for-all [x tc.gen/any]
    (r.match/match x
      (?x :as ?x)
      (= x ?x)

      _
      false)))


(tc.t/defspec !xs-cap-!xs
  (tc.prop/for-all [x tc.gen/any]
    (r.match/match x
      (!xs :as !xs)
      (= [x x])

      _
      false)))


(tc.t/defspec pred-succeeds
  (tc.prop/for-all [x tc.gen/nat]
    (r.match/match x
      (pred nat-int?)
      true)))


(tc.t/defspec pred-fails
  (tc.prop/for-all [x tc.gen/nat]
    (r.match/match x
      (pred string?)
      false

      _
      true)))


(tc.t/defspec guard-succeeds
  (tc.prop/for-all [x tc.gen/nat]
    (r.match/match x
      (guard (= 1 1))
      true)))


(tc.t/defspec guard-fails
  (tc.prop/for-all [x tc.gen/nat]
    (r.match/match x
      (guard (= 1 2))
      false

      _
      true)))


(tc.t/defspec and-succeeds
  (tc.prop/for-all [x tc.gen/nat]
    (r.match/match x
      (and ~x ?x)
      (= x ?x))))


(tc.t/defspec and-fails
  (tc.prop/for-all [x tc.gen/any]
    (r.match/match x
      (and ~x ?x (guard false))
      false

      _
      true)))


(tc.t/defspec or-succeeds
  (tc.prop/for-all [x tc.gen/any
                    y tc.gen/any
                    z tc.gen/any]
    (r.match/match x
      (or ~z ~y ~x)
      true

      _
      false)))


(tc.t/defspec or-fails
  (tc.prop/for-all [x tc.gen/any
                    y tc.gen/any
                    z tc.gen/any]
    (r.match/match [x y z]
      (or ~z ~y ~x)
      false

      _
      true)))


(tc.t/defspec or-compilation-fails
  (tc.prop/for-all [[?x ?y ?z] (tc.gen/fmap
                                (fn [?x]
                                  [(symbol (str ?x 1))
                                   (symbol (str ?x 2))
                                   (symbol (str ?x 3))])
                                (s/gen :meander.syntax.alpha/logic-variable))]
    (let [or-pat `(~'or ~?x ~?y ~?z)]
      (t/is (= {:pat or-pat
                :env #{}
                :problems
                [{:pat ?x, :absent #{?y ?z}}
                 {:pat ?y, :absent #{?x ?z}}
                 {:pat ?z, :absent #{?x ?y}}]}
               (try
                 (macroexpand
                  `(r.match/match 1
                     ~or-pat
                     false))
                 (catch clojure.lang.ExceptionInfo ex
                   (ex-data ex))))))))


(tc.t/defspec let-succeeds
  (tc.prop/for-all [x tc.gen/any
                    y tc.gen/any]
    (r.match/match y
      (and ?y (let ?x x))
      (and (= y ?y)
           (= x ?x)))))


(tc.t/defspec let-fails
  (tc.prop/for-all [x tc.gen/any]
    (r.match/match x
      (and ?x (let ?x [x]))
      false

      _
      true)))

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
      true

      _
      false)))


(tc.t/defspec seq-zero-or-more-any-in-tail
  (tc.prop/for-all [n tc.gen/nat
                    x1 tc.gen/any
                    x2 tc.gen/any
                    y1 tc.gen/any
                    y2 tc.gen/any]
    (r.match/match `(~x1 ~x2 ~@(mapcat identity (repeat n [y1 y2])))
      (~x1 ~x2 . ~y1 ~y2 ...)
      true

      _
      false)))


(tc.t/defspec seq-zero-or-more-mvr-in-head
  (tc.prop/for-all [n tc.gen/nat
                    x1 tc.gen/any
                    x2 tc.gen/any
                    y1 tc.gen/any
                    y2 tc.gen/any]
    (r.match/match `(~@(mapcat identity (repeat n [x1 x2])) ~y1 ~y2)
      (!xs1 !xs2 ... ~y1 ~y2)
      (and (= (repeat n x1) !xs1)
           (= (repeat n x2) !xs2))

      _
      false)))


(tc.t/defspec seq-zero-or-more-mvr-in-tail
  (tc.prop/for-all [n tc.gen/nat
                    x1 tc.gen/any
                    x2 tc.gen/any
                    y1 tc.gen/any
                    y2 tc.gen/any]
    (r.match/match `(~x1 ~x2 ~@(mapcat identity (repeat n [y1 y2])))
      (~x1 ~x2 . !ys1 !ys2 ...)
      (and (= (repeat n y1) !ys1)
           (= (repeat n y2) !ys2))

      _
      false)))


(tc.t/defspec seq-zero-or-more-lvr-in-head
  (tc.prop/for-all [n (tc.gen/such-that (complement zero?) tc.gen/nat)
                    x1 tc.gen/any
                    x2 tc.gen/any
                    y1 tc.gen/any
                    y2 tc.gen/any]
    (r.match/match `(~@(mapcat identity (repeat n [x1 x2])) ~y1 ~y2)
      (?x1 ?x2 ... ~y1 ~y2)
      (and (= x1 ?x1)
           (= x2 ?x2))

      _
      false)))


(tc.t/defspec seq-zero-or-more-nested-mvr
  (tc.prop/for-all [n (tc.gen/such-that (complement zero?) tc.gen/nat)
                    b (s/gen simple-symbol?)
                    v tc.gen/any]
    (r.match/match `(let (~@(mapcat identity (repeat n [b v])))
                      ~@(repeat n b))
      (`let (!bs !vs ...) . !body ...)
      (and (= !bs (repeat n b))
           (= !vs (repeat n v))
           (= !body (repeat n b)))

      _
      false)))


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
      true

      _
      false)))


(tc.t/defspec seq-any-x-cap-lvr
  (tc.prop/for-all [x tc.gen/any]
    (r.match/match (list x)
      ((~x :as ?x))
      (= x ?x)

      _
      false)))


(tc.t/defspec seq-any-x-cap-mvr
  (tc.prop/for-all [x tc.gen/any]
    (r.match/match (list x)
      ((~x :as !xs))
      (= [x] !xs)

      _
      false)))


(tc.t/defspec seq-lvr-cap-lvr-okay
  (tc.prop/for-all [x tc.gen/any]
    (r.match/match (list x x x)
      (?y (?x :as ?y) ?x)
      (= x ?x ?y)

      _
      false)))


(tc.t/defspec seq-lvr-cap-lvr-fail
  (tc.prop/for-all [x tc.gen/any]
    (r.match/match (list [x] x x)
      (?y (?x :as ?y) ?x)
      false

      _
      true)))


(tc.t/defspec seq-lvr-cap-mvr
  (tc.prop/for-all [x tc.gen/any]
    (r.match/match (list x x x)
      (?x (?x :as !xs) ?x)
      (and (= x ?x) (= [x] !xs))

      _
      false)))


(tc.t/defspec seq-mvr-cap-lvr
  (tc.prop/for-all [x tc.gen/any]
    (r.match/match (list x x x)
      (?x (!xs :as ?x) ?x)
      (and (= x ?x) (= [x] !xs))

      _
      false)))


(tc.t/defspec seq-?x-cap-?x-with-unbound-?y-binds-?y
  (tc.prop/for-all [x tc.gen/any]
    (r.match/match (list [x] [x])
      (?x ([?y] :as ?x))
      (= [x] ?x [?y])

      _
      false)))


(tc.t/defspec seq-?x-guard-true-?x-succeeds
  (tc.prop/for-all [x tc.gen/any
                    y tc.gen/any]
    (r.match/match (list x y x)
      (?x (guard (= ?x ?x)) ?x)
      true

      _
      false)))

;; Vectors

(tc.t/defspec vec-unquote-patterns-match
  (tc.prop/for-all [x tc.gen/any
                    y tc.gen/any]
    (r.match/match [x [y x] y]
      [~x [~y ~x] ~y]
      true

      _
      false)))


(tc.t/defspec vec-lvrs-bind-the-values-they-match
  (tc.prop/for-all [x tc.gen/any
                    y tc.gen/any]
    (r.match/match [x [y x] y]
      [?x [?y ?x] ?y]
      (and (= ?x x)
           (= ?y y))

      _
      false)))


(tc.t/defspec vec-mvrs-collect-the-values-they-match
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
      true

      _
      false)))


(tc.t/defspec vec-zero-or-more-any-in-tail
  (tc.prop/for-all [n tc.gen/nat
                    x1 tc.gen/any
                    x2 tc.gen/any
                    y1 tc.gen/any
                    y2 tc.gen/any]
    (r.match/match `[~x1 ~x2 ~@(mapcat identity (repeat n [y1 y2]))]
      [~x1 ~x2 . ~y1 ~y2 ...]
      true

      _
      false)))


(tc.t/defspec vec-zero-or-more-mvr-in-head
  (tc.prop/for-all [n tc.gen/nat
                    x1 tc.gen/any
                    x2 tc.gen/any
                    y1 tc.gen/any
                    y2 tc.gen/any]
    (r.match/match `[~@(mapcat identity (repeat n [x1 x2])) ~y1 ~y2]
      [!xs1 !xs2 ... ~y1 ~y2]
      (and (= (repeat n x1) !xs1)
           (= (repeat n x2) !xs2))

      _
      false)))


(tc.t/defspec vec-zero-or-more-mvr-in-tail
  (tc.prop/for-all [n tc.gen/nat
                    x1 tc.gen/any
                    x2 tc.gen/any
                    y1 tc.gen/any
                    y2 tc.gen/any]
    (r.match/match `[~x1 ~x2 ~@(mapcat identity (repeat n [y1 y2]))]
      [~x1 ~x2 . !ys1 !ys2 ...]
      (and (= (repeat n y1) !ys1)
           (= (repeat n y2) !ys2))

      _
      false)))


(tc.t/defspec vec-zero-or-more-lvr-in-head
  (tc.prop/for-all [n (tc.gen/such-that (complement zero?) tc.gen/nat)
                    x1 tc.gen/any
                    x2 tc.gen/any
                    y1 tc.gen/any
                    y2 tc.gen/any]
    (r.match/match `[~@(mapcat identity (repeat n [x1 x2])) ~y1 ~y2]
      [?x1 ?x2 ... ~y1 ~y2]
      (and (= x1 ?x1)
           (= x2 ?x2))

      _
      false)))


(tc.t/defspec vec-zero-or-more-nested-mvr
  (tc.prop/for-all [n (tc.gen/such-that (complement zero?) tc.gen/nat)
                    b (s/gen simple-symbol?)
                    v tc.gen/any]
    (r.match/match `(let [~@(mapcat identity (repeat n [b v]))]
                      ~@(repeat n b))
      (`let [!bs !vs ...] . !body ...)
      (and (= !bs (repeat n b))
           (= !vs (repeat n v))
           (= !body (repeat n b)))

      _
      false)))


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


(tc.t/defspec vec-?x-cap-?x-with-unbound-?y-binds-?y
  (tc.prop/for-all [x tc.gen/any]
    (r.match/match [[x] [x]]
      [?x ([?y] :as ?x)]
      (= [x] ?x [?y]))))


(tc.t/defspec vec-?x-guard-true-?x-succeeds
  (tc.prop/for-all [x tc.gen/any
                    y tc.gen/any]
    (r.match/match [x y x]
      [?x (guard (= ?x ?x)) ?x]
      true

      _
      false)))


;; ---------------------------------------------------------------------
;; Priority tests


(t/deftest priority-test
  (t/is (r.match/match [0 0]
          [?x ?x]
          true

          [?x _]
          false

          [_ ?x]
          false)))
