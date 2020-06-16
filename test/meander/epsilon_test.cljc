(ns meander.epsilon-test
  (:require [clojure.spec.alpha :as s :include-macros true]
            [clojure.spec.test.alpha :as st :include-macros true]
            [clojure.test :as t]
            [clojure.test.check.clojure-test :as tc.t :include-macros true]
            [clojure.test.check.generators :as tc.gen :include-macros true]
            [clojure.test.check.properties :as tc.prop :include-macros true]
            [meander.epsilon :as r :include-macros true]
            [meander.syntax.specs.epsilon :as m.syntax.specs]))

#?(:clj (set! *warn-on-reflection* true))

#?(:clj (st/instrument))

#?(:clj (s/check-asserts true))

;; ---------------------------------------------------------------------
;; Helpers

(def gen-scalar
  (tc.gen/one-of [tc.gen/int
                  tc.gen/string
                  tc.gen/keyword
                  tc.gen/symbol]))

;; ---------------------------------------------------------------------
;; match

;; Primitive patterns

(t/deftest quote-pattern-test
  (t/is (r/match '?x
          '?x true))

  (t/is (r/match '!x
          '!x true))

  (t/is (r/match '(a . b . c .. d ..2)
          '(a . b . c .. d ..2)
          true))

  (t/is (r/match '[a . b . c .. d ..2]
          '[a . b . c .. d ..2]
          true)))


(tc.t/defspec unquote-any-x-matches-any-x
  (tc.prop/for-all [x gen-scalar]
    (r/match x
      ~x
      true

      _
      false)))


(tc.t/defspec _-matches-any-x
  (tc.prop/for-all [x gen-scalar]
    (r/match x
      _
      true)))


(tc.t/defspec ?x-matches-any-x
  (tc.prop/for-all [x gen-scalar]
    (r/match x
      ?x
      true)))


(tc.t/defspec !xs-collects-any-x
  (tc.prop/for-all [x gen-scalar]
    (r/match x
      !xs
      (= [x] !xs)

      _
      false)))


;;; Seqs


(tc.t/defspec seq-unquote-patterns-match
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar]
    (r/match (list x (list y x) y)
      (~x (~y ~x) ~y)
      true

      _
      false)))


(tc.t/defspec seq-lvrs-bind-the-values-they-match
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar]
    (r/match (list x (list y x) y)
      (?x (?y ?x) ?y)
      (and (= ?x x)
           (= ?y y))

      _
      false)))


(tc.t/defspec seq-mvrs-collect-the-values-they-match
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar]
    (r/match (list x (list y x) y)
      (!xs (!ys !xs) !ys)
      (and (= [x x] !xs)
           (= [y y] !ys))

      _
      false)))


(tc.t/defspec seq-zero-or-more-any-in-head
  (tc.prop/for-all [n tc.gen/nat
                    x1 gen-scalar
                    x2 gen-scalar
                    y1 gen-scalar
                    y2 gen-scalar]
    (r/match `(~@(mapcat identity (repeat n [x1 x2])) ~y1 ~y2)
      (~x1 ~x2 ... ~y1 ~y2)
      true

      _
      false)))


(tc.t/defspec seq-zero-or-more-any-in-tail
  (tc.prop/for-all [n tc.gen/nat
                    x1 gen-scalar
                    x2 gen-scalar
                    y1 gen-scalar
                    y2 gen-scalar]
    (r/match `(~x1 ~x2 ~@(mapcat identity (repeat n [y1 y2])))
      (~x1 ~x2 . ~y1 ~y2 ...)
      true

      _
      false)))


(tc.t/defspec seq-zero-or-more-mvr-in-head
  (tc.prop/for-all [n tc.gen/nat
                    x1 gen-scalar
                    x2 gen-scalar
                    y1 gen-scalar
                    y2 gen-scalar]
    (r/match `(~@(mapcat identity (repeat n [x1 x2])) ~y1 ~y2)
      (!xs1 !xs2 ... ~y1 ~y2)
      (and (= (repeat n x1) !xs1)
           (= (repeat n x2) !xs2))

      _
      false)))


(tc.t/defspec seq-zero-or-more-mvr-in-tail
  (tc.prop/for-all [n tc.gen/nat
                    x1 gen-scalar
                    x2 gen-scalar
                    y1 gen-scalar
                    y2 gen-scalar]
    (r/match `(~x1 ~x2 ~@(mapcat identity (repeat n [y1 y2])))
      (~x1 ~x2 . !ys1 !ys2 ...)
      (and (= (repeat n y1) !ys1)
           (= (repeat n y2) !ys2))

      _
      false)))


(tc.t/defspec seq-zero-or-more-lvr-previously-bound
  (tc.prop/for-all [n (tc.gen/such-that (complement zero?) tc.gen/nat)
                    x1 gen-scalar
                    x2 gen-scalar
                    y1 gen-scalar
                    y2 gen-scalar]
    (r/match `(~@(mapcat identity (repeat n [x1 x2])) ~y1 ~y2)
      (?x1 ?x2 . ?x1 ?x2 ... ~y1 ~y2)
      (and (= x1 ?x1)
           (= x2 ?x2))

      _
      false)))


(tc.t/defspec seq-zero-or-more-nested-mvr
  (tc.prop/for-all [n (tc.gen/such-that (complement zero?) tc.gen/nat)
                    b (s/gen simple-symbol?)
                    v gen-scalar]
    (r/match `(let (~@(mapcat identity (repeat n [b v])))
                      ~@(repeat n b))
      (`let (!bs !vs ...) . !body ...)
      (and (= !bs (repeat n b))
           (= !vs (repeat n v))
           (= !body (repeat n b)))

      _
      false)))


(tc.t/defspec seq-drop-in-head
  (tc.prop/for-all [n tc.gen/nat
                    x gen-scalar]
    (r/match `(~@(map identity (repeat n x)) ~x ~x)
      (_ ... ~x ~x)
      true)))


(tc.t/defspec seq-drop-in-tail
  (tc.prop/for-all [n tc.gen/nat
                    x gen-scalar]
    (r/match `(~@(map identity (repeat n x)) ~x ~x)
      (~x ~x . _ ...)
      true

      _
      false)))


(tc.t/defspec seq-?x-guard-true-?x-succeeds
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar]
    (r/match (list x y x)
      (?x (r/guard (= ?x ?x)) ?x)
      true

      _
      false)))


(t/deftest seq-as-pattern
  (let [xs '(1 2 3)]
    (t/is (r/match xs
            (?x ?y ?z :as ?xs)
            (= (list ?x ?y ?z) ?xs xs)))))


(t/deftest seq-pattern-matching-with-infinite-sequences
  (t/is (r/match (iterate inc 1)
          (_1)
          false

          _n
          true))

  (t/is (r/match (iterate inc 1)
          (_1 _2)
          false

          _n
          true)))


(t/deftest seq-any-test
  (t/is (r/match '(1 2 3) (_ 2 3) true))
  (t/is (r/match '(1 2 3) (1 _ 3) true))
  (t/is (r/match '(1 2 3) (1 2 _) true)))


(t/deftest seq-rp+
  (t/is (= '[a b c d]
           (r/match '((a (b (c d)))
                      (a (b (c d)))
                      (a (b (c d))))
             ((?1 (?2 (?3 ?4))) ..1)
             [?1 ?2 ?3 ?4])))

  (t/is (= '(1)
           (r/search '(1 1 1 1 1) (1 ..4 ?x) ?x)))

  (t/is (= '1
           (r/find '(1 1 1 1 1) (1 ..4 ?x) ?x))))


(t/deftest seq-prt
  (let [x (rand)
        y (rand)
        l (list x y x y x)]
    (t/is (= [x y]
             (r/match l
               (_ _ . ?x ?y ?x)
               [?x ?y])))

    (t/is (= false
             (r/match l
               (_ _ . ?x ?y)
               [?x ?y]
               _ false)))))

;;; Vectors


(tc.t/defspec vec-unquote-patterns-match
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar]
    (r/match [x [y x] y]
      [~x [~y ~x] ~y]
      true

      _
      false)))


(tc.t/defspec vec-lvrs-bind-the-values-they-match
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar]
    (r/match [x [y x] y]
      [?x [?y ?x] ?y]
      (and (= ?x x)
           (= ?y y))

      _
      false)))


(tc.t/defspec vec-mvrs-collect-the-values-they-match
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar]
    (r/match [x [y x] y]
      [!xs [!ys !xs] !ys]
      (and (= [x x] !xs)
           (= [y y] !ys))

      _
      false)))


(tc.t/defspec vec-zero-or-more-any-in-head
  (tc.prop/for-all [n tc.gen/nat
                    x1 gen-scalar
                    x2 gen-scalar
                    y1 gen-scalar
                    y2 gen-scalar]
    (r/match `[~@(mapcat identity (repeat n [x1 x2])) ~y1 ~y2]
      [~x1 ~x2 ... ~y1 ~y2]
      true

      _
      false)))


(tc.t/defspec vec-zero-or-more-any-in-tail
  (tc.prop/for-all [n tc.gen/nat
                    x1 gen-scalar
                    x2 gen-scalar
                    y1 gen-scalar
                    y2 gen-scalar]
    (r/match `[~x1 ~x2 ~@(mapcat identity (repeat n [y1 y2]))]
      [~x1 ~x2 . ~y1 ~y2 ...]
      true

      _
      false)))


(tc.t/defspec vec-zero-or-more-mvr-in-head
  (tc.prop/for-all [n tc.gen/nat
                    x1 gen-scalar
                    x2 gen-scalar
                    y1 gen-scalar
                    y2 gen-scalar]
    (r/match `[~@(mapcat identity (repeat n [x1 x2])) ~y1 ~y2]
      [!xs1 !xs2 ... ~y1 ~y2]
      (and (= (repeat n x1) !xs1)
           (= (repeat n x2) !xs2))

      _
      false)))


(tc.t/defspec vec-zero-or-more-mvr-in-tail
  (tc.prop/for-all [n tc.gen/nat
                    x1 gen-scalar
                    x2 gen-scalar
                    y1 gen-scalar
                    y2 gen-scalar]
    (r/match `[~x1 ~x2 ~@(mapcat identity (repeat n [y1 y2]))]
      [~x1 ~x2 . !ys1 !ys2 ...]
      (and (= (repeat n y1) !ys1)
           (= (repeat n y2) !ys2))

      _
      false)))


(tc.t/defspec vec-zero-or-more-lvr-previously-bound
  (tc.prop/for-all [n (tc.gen/such-that (complement zero?) tc.gen/nat)
                    x1 gen-scalar
                    x2 gen-scalar
                    y1 gen-scalar
                    y2 gen-scalar]
    (r/match `[~@(mapcat identity (repeat n [x1 x2])) ~y1 ~y2]
      [?x1 ?x2 . ?x1 ?x2 ... ~y1 ~y2]
      (and (= x1 ?x1)
           (= x2 ?x2))

      _
      false)))


(tc.t/defspec vec-zero-or-more-nested-mvr
  (tc.prop/for-all [n (tc.gen/such-that (complement zero?) tc.gen/nat)
                    b (s/gen simple-symbol?)
                    v gen-scalar]
    (r/match `(let [~@(mapcat identity (repeat n [b v]))]
                      ~@(repeat n b))
      (`let [!bs !vs ...] . !body ...)
      (and (= !bs (repeat n b))
           (= !vs (repeat n v))
           (= !body (repeat n b)))

      _
      false)))


(tc.t/defspec vec-drop-in-head
  (tc.prop/for-all [n tc.gen/nat
                    x gen-scalar]
    (r/match `[~@(map identity (repeat n x)) ~x ~x]
      [_ ... ~x ~x]
      true)))


(tc.t/defspec vec-drop-in-tail
  (tc.prop/for-all [n tc.gen/nat
                    x gen-scalar]
    (r/match `[~@(map identity (repeat n x)) ~x ~x]
      [~x ~x . _ ...]
      true)))


(tc.t/defspec vec-?x-guard-true-?x-succeeds
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar]
    (r/match [x y x]
      [?x (r/guard (= ?x ?x)) ?x]
      true

      _
      false)))


(t/deftest vec-compile-inside-seq
  (t/is (= 1 (r/match '(let [] 1)
               ('let [] ?x)
               ?x))))


(t/deftest vec-as-pattern
  (let [xs [1 2 3]]
    (t/is (r/match xs
            [?x ?y ?z :as ?xs]
            (= [?x ?y ?z] ?xs xs)))))


(t/deftest vec-any-test
  (t/is (r/match [1 2 3] [_ 2 3] true))
  (t/is (r/match [1 2 3] [1 _ 3] true))
  (t/is (r/match [1 2 3] [1 2 _] true)))


(t/deftest vec-rp+
  (t/is (= '[a b c d]
           (r/match '[(a (b (c d)))
                      (a (b (c d)))
                      (a (b (c d)))]
             [(?1 (?2 (?3 ?4))) ..1]
             [?1 ?2 ?3 ?4])))

  (t/is (= 1
           (r/find '[1 1 1 1 1] [1 ..4 ?x] ?x)))

  (t/is (= '(1)
           (r/search '[1 1 1 1 1] [1 ..4 ?x] ?x))))

;; Maps


(tc.t/defspec map-unq-succeeds
  ;; such-that is to ensure we don't get a duplicate key
  (tc.prop/for-all [x (tc.gen/such-that (complement #{:x}) gen-scalar)]
    (r/match {:x x, x :x}
      {:x ~x, ~x :x}
      true

      _
      false)))


(tc.t/defspec map-lvr-succeeds
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar]
    (r/match {:x x, :y y}
      {:x ?x, :y ?y}
      (= [x y] [?x ?y])

      _
      false)))


(tc.t/defspec map-vec-lvr
  (tc.prop/for-all [x gen-scalar]
    (let [y [x]
          z [[x]]]
      (r/match {:x [x y], :y [y x], :z [z]}
        {:x [?x ?y] :y [?y ?x] :z [?z]}
        (= [x y z] [?x ?y ?z])

        _
        false))))


(tc.t/defspec map-seq-lvr
  (tc.prop/for-all [x gen-scalar]
    (let [y [x]
          z [[x]]]
      (r/match {:x (list x y), :y (list y x), :z (list z)}
        {:x (?x ?y) :y (?y ?x) :z (?z)}
        (= (list x y z) (list ?x ?y ?z))

        _
        false))))


(tc.t/defspec map-mvr
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar]
    (r/match {:x x, :y y}
      {:x !xs, :y !xs}
      (or (= [x y] !xs)
          (= [y x] !xs))

      _
      false)))


(tc.t/defspec map-map
  (tc.prop/for-all [x gen-scalar]
    (r/match {:x {:y x}, :z x}
      {:x {:y ~x}, :z ~x}
      true

      _
      false)))


(t/deftest map-rest-map
  (t/is (= [2 {:_1 1, :_3 3}]
           (r/match {:_1 1, :_2 2, :_3 3}
             {:_2 ?2 & ?rest-map}
             [?2 ?rest-map])))

  (t/is (= [2 3 {:_1 1}]
           (r/match {:_1 1, :_2 2, :_3 3}
             {:_2 ?2 & {:_3 ?3 & ?rest-map}}
             [?2 ?3 ?rest-map])))

  (t/is (= '([2 :_1 1] [2 :_3 3])
           (r/search {:_1 1, :_2 2, :_3 3}
             {:_2 ?2 & (r/scan [?k ?v])}
             [?2 ?k ?v]))))


(t/deftest ground-map-pattern-expresses-submap-match
  (t/is (r/match {:foo 1, :bar 2, :baz 3}
          {:foo 1}
          true))

  (t/is (r/match {:foo 1, :bar 2, :baz 3}
          {:bar 2}
          true))

  (t/is (r/match {:foo 1, :bar 2, :baz 3}
          {:baz 3}
          true))

  (t/is (r/match {:foo 1, :bar 2, :baz 3}
          {:foo 1, :bar 2}
          true))

  (t/is (r/match {:foo 1, :bar 2, :baz 3}
          {:bar 2, :baz 3}
          true))

  (t/is (r/match {:foo 1, :bar 2, :baz 3}
          {:foo 1, :baz 3}
          true))

  (t/is (r/match '({:foo 1, :bar 2, :baz 3}
                   {:foo 1, :bar 2, :baz 3}
                   {:foo 1, :bar 2, :baz 3})
          ({:foo 1, :bar 2}
           {:bar 2, :baz 3}
           {:foo 1, :baz 3})
          true))

  (t/is (r/match [{:foo 1, :bar 2, :baz 3}
                  {:foo 1, :bar 2, :baz 3}
                  {:foo 1, :bar 2, :baz 3}]
          [{:foo 1, :bar 2}
           {:bar 2, :baz 3}
           {:foo 1, :baz 3}]
          true)))


;;; Sets


(t/deftest set-unq
  (let [k1 :k1
        k2 :k2]
    (r/match #{k1 k2}
      #{~(keyword (name k1)) ~k2}
      true)))


(t/deftest set-&-as
  (t/is (= #{[#{1 3 2} #{3 2} #{2}]
             [#{1 3 2} #{3 2} #{3}]
             [#{1 3 2} #{1 2} #{2}]
             [#{1 3 2} #{1 2} #{1}]
             [#{1 3 2} #{1 3} #{3}]
             [#{1 3 2} #{1 3} #{1}]}
           (set
            ;; Find subsets S1, S2, and S3 of S such that
            ;;
            ;;   S1 = S
            ;;   S2 = S1 - #{Ei}
            ;;   S3 = S2 - #{Ej}
            (r/search #{1 2 3}
              #{_ ^& #{_ ^& ?s3 ^:as ?s2} ^:as ?s1}
              [?s1 ?s2 ?s3])))))

(t/deftest ground-set-pattern-expresses-subset-match
  (t/is (r/match #{1 2 3}
          #{1}
          true))

  (t/is (r/match #{1 2 3}
          #{2}
          true))

  (t/is (r/match #{1 2 3}
          #{3}
          true))

  (t/is (r/match #{1 2 3}
          #{1 2}
          true))

  (t/is (r/match #{1 2 3}
          #{2 3}
          true))

  (t/is (r/match #{1 2 3}
          #{1 3}
          true))

  (t/is (r/match '(#{1 2 3}
                   #{1 2 3}
                   #{1 2 3})
          (#{1 2}
           #{2 3}
           #{1 3})
          true))

  (t/is (r/match [#{1 2 3}
                  #{1 2 3}
                  #{1 2 3}]
          [#{1 2}
           #{2 3}
           #{1 3}]
          true))

  (t/is (r/match #{[{:foo "bar", :baz "foo"}]
                   [{:foo "baz", :baz "quux"}]}
          #{[{:foo "bar"}]
            [{:baz "quux"}]}
          true))

  (t/is (r/match #{[{:foo "bar", :baz "foo"}]
                   [{:foo "baz", :baz "quux"}]}
          #{[{:foo "baz"}]
            [{:baz "foo"}]}
          true)))

;; ---------------------------------------------------------------------
;; Match operators

;;; and

(tc.t/defspec and-succeeds
  (tc.prop/for-all [x tc.gen/nat]
    (r/match x
      (r/and ~x ?x)
      (= x ?x))))


(tc.t/defspec and-fails
  (tc.prop/for-all [x gen-scalar]
    (r/match x
      (r/and ~x ?x (r/guard false))
      false

      _
      true)))

;;; app

(t/deftest app
  (t/is (r/match {:foo 1}
          (r/app (fn [m] (assoc m :baz 2))
                 ?x
                 (r/app (fn [m] (assoc m :quux 3))
                        ?y)
                 (r/let [?z [?x ?y]]))
          (= ?z
             [?x ?y]
             [{:foo 1, :baz 2}
              {:foo 1, :baz 2, :quux 3}]))))


;;; cata


(t/deftest cata-test-1
  (t/is (= [:one :two :three]
           (r/match [1 2 3]
             [(r/cata ?x) (r/cata ?y) (r/cata ?z)]
             [?x ?y ?z]

             3
             :three

             2
             :two

             1
             :one))))

(t/deftest cata-test-2
   (t/is (= [[:one :two] :two [:three :two]]
            (r/match [[2 1] 2 [3 2]]
              [(r/cata ?x) (r/cata ?y) (r/cata ?z)]
              [?x ?y ?z]

              [3 (r/cata ?y)]
              [:three ?y]

              2
              :two

              [(r/cata ?x) 1]
              [:one ?x]))))

(t/deftest cata-test-3
   (t/is (= #{[:other :other :other]
              [:other :other :three]
              [:other :two :other]
              [:other :two :three]
              [:one :other :other]
              [:one :other :three]
              [:one :two :other]
              [:one :two :three]
              :other}
            (set
             (r/search [1 2 3]
               [(r/cata ?x) (r/cata ?y) (r/cata ?z)]
               [?x ?y ?z]

               ?other
               :other

               3
               :three

               2
               :two

               1
               :one)))))

(t/deftest cata-test-4
   (let [rep {:head :+
              :arg1 {:head :number
                     :value 1}
              :arg2 {:head :number
                     :value 2}}]
     (t/is (= [1 2]
              (r/match rep
                {:head :+
                 :arg1 (r/cata [!xs1 ...])
                 :arg2 (r/cata [!xs2 ...])}
                (r/subst [!xs1 ... !xs2 ...])

                {:head :number
                 :value ?value}
                [?value]

                ?x
                [?x])))))

(t/deftest cata-test-5
  (let [x {:source {:type "ip"
                    :value "192.1.2.3"}
           :related {:type "ip"
                     :value "192.1.2.3"}}
        expected {:source {:type "ip", :value "192.1.2.3", :internal true},
                  :related {:type "ip", :value "192.1.2.3", :internal true}}
        actual (r/match x
                 {:source (r/and {} (r/cata ?source))
                  :related (r/and {} (r/cata ?related))}
                 (assoc x
                        :source ?source
                        :related ?related)

                 {:type "ip"
                  :value (r/re #"192\..*")
                  :as ?x}
                 (assoc ?x :internal true)

                 ?x
                 ?x)]
    (t/is (= expected actual))))

(t/deftest cata-test-6
   (t/is (= [{:name "a", :arg-list [[1 2 3] [5 6 7]]}
             {:name "b", :arg-list [[1]]}]
            (r/rewrite (group-by :name [{:name "a" :args [1 2 3]}
                                        {:name "a" :args [5 6 7]}
                                        {:name "b" :args [1]}])
              {?name [{:args !args} ...]
               & (r/cata ?rest)}
              [{:name ?name :arg-list [!args ...]}
               & ?rest]

              ?x
              ?x))))

(t/deftest cata-test-7
   (let [tree '(branch (leaf (s 0))
                       (branch (leaf (s (s 0)))
                               (leaf (s 0))))
         expected '(branch (branch (leaf 0)
                                   (leaf 0))
                           (branch (branch (leaf 0)
                                           (branch (leaf 0)
                                                   (leaf 0)))
                                   (branch (leaf 0)
                                           (leaf 0))))]
     (t/is (= expected
              (r/rewrite tree
                (leaf 0)
                (leaf 0)

                (leaf (s ?n))
                (branch (leaf 0)
                        (r/cata (leaf ?n)))

                (branch ?a ?b)
                (branch (r/cata ?a)
                        (r/cata ?b)))))

     (t/is (= expected
              (r/rewrite tree
                (leaf 0)
                (leaf 0)

                (leaf (s ?n))
                (branch (leaf 0)
                        (r/cata (leaf ?n)))

                (branch (r/cata ?a)
                        (r/cata ?b))
                (branch ?a ?b))))))


;;; gather


(t/deftest gather-succeeds-test
  (t/is (= [2 4 6]
           (r/match [1 2 3 4 5 6]
             (r/gather (r/pred even? !xs))
             !xs)))

  (t/is (= [2 4 6]
           (r/match [1 2 3 4 5 6]
             (r/gather (r/pred even? !xs) 3)
             !xs)))

  (t/is (= [[2 4 6] 3]
           (r/match [[1 2 3 4 5 6] 3]
             [(r/gather (r/pred even? !xs) ?n) ?n]
             [!xs ?n])))

  (t/is (= [[2 4 6] [3]]
           (r/match [1 2 3 4 5 6]
             (r/gather (r/pred even? !xs) !n)
             [!xs !n])))

  (t/is (= [2 4 6]
           (r/match [1 2 3 4 5 6]
             (r/gather (r/pred even? !xs) _)
             !xs)))

  (t/is (= [2 4 6]
           (r/match [1 2 3 4 5 6]
             (r/gather (r/pred even? !xs) ...)
             !xs)))

  (t/is (= [2 4 6]
           (r/match [1 2 3 4 5 6]
             (r/gather (r/pred even? !xs) ..3)
             !xs)))

  (t/is (= [[2 4 6] 3]
           (r/match [1 2 3 4 5 6]
             (r/gather (r/pred even? !xs) ..?n)
             [!xs ?n])))

  (t/is (= [[2 4 6] [3]]
           (r/match [1 2 3 4 5 6]
             (r/gather (r/pred even? !xs) ..!n)
             [!xs !n]))))

(t/deftest gather-fails-test
  (t/is (= :fail
           (r/match [1 2 3]
             (r/gather !xs 4)
             :okay

             _
             :fail)))

  (t/is (= :fail
           (r/match [1 2 3]
             (let [?n 4]
               (r/gather !xs ?n))
             :okay

             _
             :fail)))

  (t/is (= :fail
           (r/match [1 2 3]
             (let [?n 4]
               (r/gather !xs ?n))
             :okay

             _
             :fail))))

;;; guard


(tc.t/defspec guard-succeeds
  (tc.prop/for-all [x tc.gen/nat]
    (r/match x
      (r/guard (= 1 1))
      true)))


(tc.t/defspec guard-fails
  (tc.prop/for-all [x tc.gen/nat]
    (r/match x
      (r/guard (= 1 2))
      false

      _
      true)))

;;; keyword

(t/deftest match-keyword-succeeds
  (t/is (= "bar"
           (r/match :foo/bar
             (r/keyword ?name)
             ?name)))

  (t/is (= ["foo" "bar"]
           (r/match :foo/bar
             (r/keyword ?namespace ?name)
             [?namespace ?name]))))


(t/deftest match-keyword-fails
  (t/is (false? (r/match 'foo
                  (r/keyword ?name)
                  true

                  _
                  false)))

  (t/is (false? (r/match 'foo/bar
                  (r/keyword ?namespace ?name)
                  true

                  _
                  false))))


;;; let


(tc.t/defspec let-succeeds
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar]
    (r/match y
      (r/and ?y (r/let [?x x]))
      (and (= y ?y)
           (= x ?x)))))


(tc.t/defspec let-fails
  (tc.prop/for-all [x gen-scalar]
    (r/match x
      (r/and ?x (r/let [?x [x]]))
      false

      _
      true)))


(t/deftest let-test
  (t/is (= [1 2]
           (r/match 42
             (r/or [?x ?y] (r/let [[?x ?y] [1 2]]))
             [?x ?y]))))

;;; or

(tc.t/defspec or-succeeds
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar
                    z gen-scalar]
    (r/match x
      (r/or ~z ~y ~x)
      true

      _
      false)))


(tc.t/defspec or-fails
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar
                    z gen-scalar]
    (r/match [x y z]
      (r/or ~z ~y ~x)
      false

      _
      true)))


(tc.t/defspec or-captures
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar
                    z gen-scalar]
        (= [[x y z]]
           (r/match [x y z]
             (r/or ~z ~y ~x !xs)
             !xs))))


#?(:clj
   ;; If this let appears inside the `deftest` form the test fails for
   ;; unclear reasons.
   (let [x (try
             (macroexpand '(r/match 1
                             (r/or ?x ?y ?z)
                             false))
             false
             (catch Exception _
               true))]
     (t/deftest or-compilation-fails
       (t/is x))))


(t/deftest or-literal-test
  (t/is (r/match false
          (r/or true false)
          true))
  (t/is (r/match nil
          (r/or true nil false)
          true)))


(t/deftest unbound-mvrs-in-or
  (t/is (= [[0 2 4 6 8] [1 3 5 7 9]]
           (r/match (range 10)
             ((r/or (r/pred even? !evens)
                    (r/pred odd? !odds)) ...)
             [!evens !odds])))

  (t/is (= [[2 1 3] []]
           (r/match [2 1 3]
             [(r/or (r/pred even? !xs)
                    (r/pred odd? !ys))
              !xs
              !xs]
             [!xs !ys])))

  (t/is (= [[2 4 6 8] [1 3 5 7 9]]
           (r/match [1 2 3 4 5 6 7 8 9]
             [(r/or (r/pred even? !xs) (r/pred odd? !ys))
              ...]
             [!xs !ys]))))


(t/deftest or-test
  (t/is (= :okay (r/match 1 (r/or {} _) :okay)))
  (t/is (= :okay (r/match 1 (r/or _ {}) :okay))))


;;; not


(t/deftest not-not-test
  (let [x 1]
    (t/is (= x (r/find 1
                 (r/not (r/not ?x))
                 ?x)))))


(t/deftest set-negation
  (t/is (= '(1 3)
           (r/search [#{1 2 3} #{[2 2]}]
             [#{?x} #{(r/not [?x ?x])}]
             ?x)))

  (t/is (= '(1 3)
           (r/search [#{1 2 3} #{[2 2]}]
             [#{?x} (r/not #{[?x ?x]})]
             ?x)))

  (let [x (r/find [#{1 2 3} #{[2 2]}]
            [#{?x}  #{(r/not [?x ?x])}]
            ?x)]
    (t/is (or (= x 1)
              (= x 3)))))

;;; pred


(tc.t/defspec pred-succeeds
  (tc.prop/for-all [x tc.gen/nat]
    (r/match x
      (r/pred nat-int?)
      true)))


(tc.t/defspec pred-fails
  (tc.prop/for-all [x tc.gen/nat]
    (r/match x
      (r/pred string?)
      false

      _
      true)))


(t/deftest pred-arity-n
  (t/is (= [1 2]
           (r/match 1
             (r/pred odd? ?x (r/app inc ?y))
             [?x ?y]))))


;;; re form


(t/deftest re-test
  (t/is (r/match "foo foo foo"
          (r/re #"(?:foo ?)+")
          true
          _
          false))

  (t/is (r/match "Harry Hacker"
          (r/re #"([^ ]+) *([^ ]+)" [_ ?Harry ?Hacker])
          (and (= ?Harry "Harry")
               (= ?Hacker "Hacker"))
          _
          false)))


;;; seqable


(t/deftest basic-seqables
  (t/is (r/match [1 2 3]
          (r/seqable 1 2 3)
          true
          _
          false))

  (t/is (r/match (list 1 2 3)
          (r/seqable 1 2 3)
          true
          _
          false))

  (t/is (r/match (range 1 4)
          (r/seqable 1 2 3)
          true
          _
          false))

  (t/is (r/match [1 2 3]
          (r/seqable ?x ?y ?z)
          true
          _
          false))

  (t/is (r/match (list 1 2 3)
          (r/seqable ?x ?y ?z)
          true
          _
          false))

  (t/is (r/match #{1 2 3}
          (r/seqable ?x ?y ?z)
          true
          _
          false))

  (t/is (r/match #{1 2}
          (r/seqable ?x ?y ?z)
          false
          _
          true))

  (t/is (r/match {(list :left) [:right]}
          (r/seqable (r/seqable (r/seqable ?x) (r/seqable ?y)))
          true
          _
          false))

  (t/is (r/match [(list :left) [:right]]
          [(r/seqable ?x) (r/seqable ?y)]
          true
          _
          false)))


(t/deftest search-seqables
  (t/is (= 4 (count
              (r/search [[1 2] (list 1 2) #{1 2} {1 2}]
                (r/scan (r/seqable ?x ?y))
                [?x ?y]

                (r/scan (r/seqable (r/seqable ?x ?y)))
                [?x ?y]))))

  (t/is (= 4 (count
              (r/search {:a [1 2 3]
                               :b (range 10)
                               :c #{1 2 3}
                               :d {:a 1 :b 2}}
                {?key (r/seqable !xs ...)}
                !xs)))))


(defn make-array-list [& args]
  #?(:clj
     (java.util.ArrayList. ^java.util.Collection args)

     :cljs
     (into-array args)))


(def ordered-seqable-gen
  (tc.gen/elements [list vector make-array-list]))


(tc.t/defspec seqable-unquote-patterns-match
  (tc.prop/for-all [coll ordered-seqable-gen
                    x gen-scalar
                    y gen-scalar]
    (r/match (coll x (coll y x) y)
      (r/seqable ~x (r/seqable ~y ~x) ~y)
      true

      _
      false)))


(tc.t/defspec seqable-drop-in-head
  (tc.prop/for-all [n tc.gen/nat
                    x gen-scalar]
    (r/match `[~@(map identity (repeat n x)) ~x ~x]
      (r/seqable _ ... ~x ~x)
      true)))


(tc.t/defspec seqable-drop-in-tail
  (tc.prop/for-all [n tc.gen/nat
                    x gen-scalar]
    (r/match `[~@(map identity (repeat n x)) ~x ~x]
      (r/seqable~x ~x . _ ...)
      true)))


(tc.t/defspec seqable-mvrs-collect-the-values-they-match
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar]
    (r/match [x [y x] y]
      (r/seqable !xs (r/seqable !ys !xs) !ys)
      (and (= [x x] !xs)
           (= [y y] !ys))

      _
      false)))


;;; symbol


(t/deftest match-symbol-succeeds
  (t/is (= "bar"
           (r/match 'foo/bar
             (r/symbol ?name)
             ?name)))

  (t/is (= ["foo" "bar"]
           (r/match 'foo/bar
             (r/symbol ?namespace ?name)
             [?namespace ?name]))))


(t/deftest match-symbol-fails
  (t/is (false? (r/match :foo
                  (r/symbol ?name)
                  true

                  _
                  false)))

  (t/is (false? (r/match :foo/bar
                  (r/symbol ?namespace ?name)
                  true

                  _
                  false))))


;;; with


(t/deftest with-form-test
  (let [hiccup [:div
                [:p {"foo" "bar"}
                 [:strong "Foo"]
                 [:em {"baz" "quux"} "Bar"
                  [:u "Baz"]]]
                [:ul
                 [:li "Beef"]
                 [:li "Lamb"]
                 [:li "Pork"]
                 [:li "Chicken"]]]
        expected [[:div :p :strong :em :u :ul :li :li :li :li]
                  [{"foo" "bar"} {"baz" "quux"}]
                  ["Foo" "Bar" "Baz" "Beef" "Lamb" "Pork" "Chicken"]]
        actual (r/find hiccup
                 (r/with [%h1 [!tags {:as !attrs} . %hiccup ...]
                          %h2 [!tags . %hiccup ...]
                          %h3 !xs
                          %hiccup (r/or %h1 %h2 %h3)]
                   %hiccup)
                 [!tags !attrs !xs])]
    (t/is (= actual expected)))

  (t/is (= (set
            (r/search '[#{{:tag :lvr, :symbol ?y}
                          {:tag :lvr, :symbol ?x}
                          {:tag :lvr, :symbol ?z}}
                        #{{:tag :lvr, :symbol ?x}}]
              (r/with [%lvr {:tag :lvr, :symbol ?symbol}]
                [#{%lvr} #{(r/not %lvr)}])
              ?symbol))
           '#{?y ?z}))


  (t/testing "recursive key/val collection"
    (t/is (= [#{:foo :baz} #{"bar" "quux"}]
             (r/find {:foo "bar", :baz "quux"}
               (r/with [%kvs {!k !v & (r/or %kvs {})}]
                 %kvs)
               [(set !k) (set !v)]))))

  (t/testing "recursive element collection"
    (t/is (= #{"bar" :baz :foo "quux"}
             (r/find #{:foo "bar", :baz "quux"}
               (r/with [%elems #{!x ^& (r/or %elems #{})}]
                 %elems)
               (set !x)))))

  (t/testing "recursive grammar"
    (t/is (= #{[1 1 1 1] [1 1 1] [1 1] [1] []}
             (set (r/search {:a 1
                             :b {:a 1
                                 :b 2}
                             :c '(1 2 1 2)}
                    (r/with [%1_ (r/or %1 _)
                             %1 (r/or (r/and 1 !1s)
                                      {_ %1_ & %1_}
                                      (%1_ ...)
                                      [%1_ ...]
                                      #{%1_})]
                      %1)
                    !1s))))))



;;; JS Array

#?(:cljs
   (tc.t/defspec jsa-unquote-patterns-match
     (tc.prop/for-all [x gen-scalar
                       y gen-scalar]
       (r/match #js [x #js [y x] y]
         #js [~x #js [~y ~x] ~y]
         true

         _
         false))))


#?(:cljs
   (tc.t/defspec jsa-lvrs-bind-the-values-they-match
     (tc.prop/for-all [x gen-scalar
                       y gen-scalar]
       (r/match #js [x #js [y x] y]
         #js [?x #js [?y ?x] ?y]
         (and (= ?x x)
              (= ?y y))

         _
         false))))


#?(:cljs
   (tc.t/defspec jsa-mvrs-collect-the-values-they-match
     (tc.prop/for-all [x gen-scalar
                       y gen-scalar]
       (r/match #js [x #js [y x] y]
         #js [!xs #js [!ys !xs] !ys]
         (and (= [x x] !xs)
              (= [y y] !ys))

         _
         false))))


#?(:cljs
   (tc.t/defspec jsa-zero-or-more-any-in-head
     (tc.prop/for-all [n tc.gen/int
                       x1 gen-scalar
                       x2 gen-scalar
                       y1 gen-scalar
                       y2 gen-scalar]
       (r/match (into-array (conj (into [] (mapcat identity) (repeat n [x1 x2])) y1 y2))
         #js [~x1 ~x2 ... ~y1 ~y2]
         true

         _
         false))))


#?(:cljs
   (tc.t/defspec jsa-zero-or-more-any-in-tail
     (tc.prop/for-all [n tc.gen/nat
                       x1 gen-scalar
                       x2 gen-scalar
                       y1 gen-scalar
                       y2 gen-scalar]
       (r/match (into-array (into [x1 x2] (mapcat identity) (repeat n [y1 y2])))
         #js [~x1 ~x2 . ~y1 ~y2 ...]
         true

         _
         false))))


#?(:cljs
   (tc.t/defspec jsa-zero-or-more-mvr-in-head
     (tc.prop/for-all [n tc.gen/nat
                       x1 gen-scalar
                       x2 gen-scalar
                       y1 gen-scalar
                       y2 gen-scalar]
       (r/match (into-array (conj (into [] (mapcat identity) (repeat n [x1 x2])) y1 y2))
         #js [!xs1 !xs2 ... ~y1 ~y2]
         (and (= (repeat n x1) !xs1)
              (= (repeat n x2) !xs2))

         _
         false))))


#?(:cljs
   (tc.t/defspec jsa-zero-or-more-mvr-in-tail
     (tc.prop/for-all [n tc.gen/nat
                       x1 gen-scalar
                       x2 gen-scalar
                       y1 gen-scalar
                       y2 gen-scalar]
       (r/match (into-array (into [x1 x2] (mapcat identity) (repeat n [y1 y2])))
         #js [~x1 ~x2 . !ys1 !ys2 ...]
         (and (= (repeat n y1) !ys1)
              (= (repeat n y2) !ys2))

         _
         false))))


#?(:cljs
   (tc.t/defspec jsa-zero-or-more-lvr-previously-bound
     (tc.prop/for-all [n (tc.gen/such-that (complement zero?) tc.gen/nat)
                       x1 gen-scalar
                       x2 gen-scalar
                       y1 gen-scalar
                       y2 gen-scalar]
       (r/match (into-array (conj (into [] (mapcat identity) (repeat n [x1 x2])) y1 y2))
         #js [?x1 ?x2 . ?x1 ?x2 ... ~y1 ~y2]
         (and (= x1 ?x1)
              (= x2 ?x2))

         _
         false))))


#?(:cljs
   (tc.t/defspec jsa-zero-or-more-nested-mvr
     (tc.prop/for-all [n (tc.gen/such-that (complement zero?) tc.gen/nat)
                       b (s/gen simple-symbol?)
                       v gen-scalar]
       (r/match (list* `let (into-array (into [] (mapcat identity) (repeat n [b v])))
                       (repeat n b))
         (`let #js [!bs !vs ...] . !body ...)
         (and (= !bs (repeat n b))
              (= !vs (repeat n v))
              (= !body (repeat n b)))

         _
         false))))


#?(:cljs
   (tc.t/defspec jsa-drop-in-head
     (tc.prop/for-all [n tc.gen/nat
                       x gen-scalar]
       (r/match (into-array (conj (into [] (map identity) (repeat n x)) x x))
         #js [_ ... ~x ~x]
         true))))


#?(:cljs
   (tc.t/defspec jsa-drop-in-tail
     (tc.prop/for-all [n tc.gen/nat
                       x gen-scalar]
       (r/match (into-array (conj (into [] (map identity) (repeat n x)) x x))
         #js [~x ~x . _ ...]
         true))))


#?(:cljs
   (tc.t/defspec jsa-?x-guard-true-?x-succeeds
     (tc.prop/for-all [x gen-scalar
                       y gen-scalar]
       (r/match #js [x y x]
         #js [?x (r/guard (= ?x ?x)) ?x]
         true

         _
         false))))


#?(:cljs
   (t/deftest jsa-compile-inside-seq
     (t/is (= 1 (r/match '(let #js [] 1)
                  ('let #js [] ?x)
                  ?x)))))

;; JS Object

#?(:cljs
   (tc.t/defspec jso-unq-succeeds
     (tc.prop/for-all [x gen-scalar]
       (r/match #js {:x x}
         #js {:x ~x}
         true

         _
         false))))

#?(:cljs
   (tc.t/defspec jso-lvr-succeeds
     (tc.prop/for-all [x gen-scalar
                       y gen-scalar]
       (r/match #js {:x x, :y y}
         #js {:x ?x, :y ?y}
         (= [x y] [?x ?y])

         _
         false))))


#?(:cljs
   (tc.t/defspec jso-vec-lvr
     (tc.prop/for-all [x gen-scalar]
       (let [y [x]
             z [[x]]]
         (r/match #js {:x [x y], :y [y x], :z [z]}
           #js {:x [?x ?y] :y [?y ?x] :z [?z]}
           (= [x y z] [?x ?y ?z])

           _
           false)))))

#?(:cljs
   (tc.t/defspec jso-seq-lvr
     (tc.prop/for-all [x gen-scalar]
       (let [y [x]
             z [[x]]]
         (r/match #js {:x (list x y), :y (list y x), :z (list z)}
           #js {:x (?x ?y) :y (?y ?x) :z (?z)}
           (= (list x y z) (list ?x ?y ?z))

           _
           false)))))

#?(:cljs
   (tc.t/defspec jso-mvr
     (tc.prop/for-all [x gen-scalar
                       y gen-scalar]
       (r/match #js {:x x, :y y}
         #js {:x !xs, :y !xs}
         (or (= [x y] !xs)
             (= [y x] !xs))

         _
         false))))


#?(:cljs
   (tc.t/defspec jso-jso
     (tc.prop/for-all [x gen-scalar]
       (r/match #js {:x #js {:y x}, :z x}
         #js {:x #js {:y ~x}, :z ~x}
         true

         _
         false))))


;;; Misc


(t/deftest memory-variables-in-nested-zero-or-more
  (t/is (= [[:aa :bb :cc] [1 2 3]]
           (r/match '[([:aa 1] [:bb 2]) ([:cc 3])]
             [([!ks !vs] ...) ...]
             [!ks !vs]

             _
             nil))))


(t/deftest gh-33
  (t/is (= 1 (r/find [1 2]
               [?a ?b]
               ?a

               (?a ?b)
               ?a)))

  (t/is (= 1 (r/find '(1 2)
               [?a ?b]
               ?a

               (?a ?b)
               ?a))))


(t/deftest gh-41
  (let [fail (gensym)]
    (t/is (identical? fail
                      (r/match [1 2 3 4 5 6 7]
                        {:as !as, :bs !bs}
                        [!as !bs]
                        _ fail)))))


(t/deftest gh-44
  (let [f (fn [z]
            (r/match z
              [?x ?y ~(+ ?x ?y)]
              :yes

              _
              :no))]
    (t/is (= [:yes :no :yes]
             [(f [1 2 3])
              (f [2 1 4])
              (f [1 3 4])]))))


(t/deftest logical-length-subsequence-test
  (t/is (= [[1 2 3] [4 5 6]]
           (r/find '[1 2 3 4 5 6]
             [!xs ..?n !ys ..?n]
             [!xs !ys])
           (r/find '(1 2 3 4 5 6)
             (!xs ..?n !ys ..?n)
             [!xs !ys])))

  (t/is (= 1
           (r/match [1 2 3 4]
             [_ _ _ _ ..?n]
             ?n)

           (r/match '(1 2 3 4)
             (_ _ _ _ ..?n)
             ?n)))

  (t/is (= 2
           (r/match [1 2 3 4]
             [_ _ ..?n]
             ?n)
           (r/match '(1 2 3 4)
             (_ _ ..?n)
             ?n)))

  (t/is (= :fail
           (r/match [1 2 3 4]
             [_ _ _ ..?n]
             ?n

             _
             :fail)

           (r/match '(1 2 3 4)
             (_ _ _ ..?n)
             ?n

             _
             :fail)))

  (t/is (= 4
           (r/match [1 2 3 4]
             [_ ..?n]
             ?n)

           (r/match '(1 2 3 4)
             (_ ..?n)
             ?n))))


(t/deftest memory-length-subsequence-test
  (t/is (= [[1 2 3 4 5 6] [4 2]]
           (r/find '[[1 2 3 4] [5 6]]
             [[!xs ..!ns] [!xs ..!ns]]
             [!xs !ns])
           (r/find '((1 2 3 4) (5 6))
             ((!xs ..!ns) (!xs ..!ns))
             [!xs !ns])))

  (t/is (= [[1 3] [2 4] [2]]
           (r/match [1 2 3 4]
             [!as !bs ..!ns]
             [!as !bs !ns])
           (r/match [[1 2] [3 4]]
             [[!as !bs] ..!ns]
             [!as !bs !ns]))))


(t/deftest &-patterns-test
  (t/is (= '#{(2 3) (3) ()}
           (set (r/search '((a 2 3) (b 2 3))
                  ((_ ... & ?rest) (_ ... & ?rest))
                  ?rest))
           (set (r/search '([a 2 3] [b 2 3])
                  ([_ ... & ?rest] [_ ... & ?rest])
                  ?rest))))

  (t/is (= '#{[2 (3)] [3 ()]}
           (set (r/search '((a 2 3) (b 2 3))
                  ((_ ... & (?x & ?rest)) (_ ... & (?x & ?rest)))
                  [?x ?rest]))
           (set (r/search '([a 2 3] [b 2 3])
                  ([_ ... & [?x & ?rest]] [_ ... & [?x & ?rest]])
                  [?x ?rest])))))

;; ---------------------------------------------------------------------
;; search

(t/deftest breadth-first-search-test
  (t/is (= '([:a :b] [1 2] [:a :c] [1 3])
           (r/breadth-first-search [1 2]
             (r/let [?s :a]
               (r/or (r/let [?t :b])
                     (r/let [?t :c])))
             [?s ?t]

             (r/let [?s 1]
               (r/or (r/let [?t 2])
                     (r/let [?t 3])))
             [?s ?t]))))

(t/deftest search-map-1-test
  (t/is (= #{["v1" "v2"]}
           (set (r/search {:k1 "v1", :k2 {"v1" "v2"}}
                  {:k1 ?x, :k2 {?x ?y}}
                  [?x ?y])))))

(t/deftest search-map-2-test
  (let [expected #{[:k1 :k2 "v1" "v2"]
                   [:k1 :k2 :k3 "v1" "v2"]}]
    (t/is (= expected
             (set (r/search {:k1 "v1"
                             :k2 {"v1" "v2"}
                             :k3 {"v2" "v1"}}

                    {?k1 ?x
                     ?k2 {?x ?y}}
                    [?k1 ?k2 ?x ?y]

                    {?k1 ?x
                     ?k2 {?x ?y}
                     ?k3 {?y ?x}}
                    [?k1 ?k2 ?k3 ?x ?y]))))))


(t/deftest search-map-3-unq-test
  (t/is (= #{["v1" "v2"]}
           (let [k1 :k1]
             (set (r/search {:k1 :k2, :k2 {"v1" "v2"}}
                            {~k1 ?k2, ?k2 {?x ?y}}
                            [?x ?y]))))))


(t/deftest search-or-1-test
  (t/is (= #{[1 2] [2 1]}
           (set (r/search [1 2]
                  (r/or [?x ?y] [?y ?x])
                  [?x ?y])))))


(t/deftest search-subsequence-1-test
  (t/is (= (set (r/search [1 2 1 2 1 2 3 5 6 7 8 9 1 2]
                  [?x ?y . ?x ?y ... !zs ... ?x ?y]
                  {:?x ?x, :?y ?y, :!zs !zs}))
           #{{:?x 1, :?y 2, :!zs [1 2 1 2 3 5 6 7 8 9]}
             {:?x 1, :?y 2, :!zs [1 2 3 5 6 7 8 9]}
             {:?x 1, :?y 2, :!zs [3 5 6 7 8 9]}})))

(t/deftest search-rp-test*
  (t/is (= #{[[] [1 2 3]]
             [[1] [2 3]]
             [[1 2] [3]]
             [[1 2 3] []]
             [[] [] [1 2 3]]
             [[] [1] [2 3]]
             [[] [1 2] [3]]
             [[] [1 2 3] []]
             [[1] [] [2 3]]
             [[1] [2] [3]]
             [[1] [2 3] []]
             [[1 2] [] [3]]
             [[1 2] [3] []]
             [[1 2 3] [] []]}
           (set
            (r/search [1 2 3]
                      [!xs ... !ys ...]
                      [!xs !ys]

                      [!as ... !bs ... !cs ...]
                      [!as !bs !cs])))))


;;; scan


(t/deftest scan-test
  (t/testing "Given a match anything pattern"
    (t/is (= '(1 2 3)
             (r/search [1 2 3]
                       (r/scan ?x)
                       ?x))
          "every element should be found by the scan"))

  (t/testing "Given an :as clause in a match anything scan"
    (t/is (= '(1 2 3)
             (r/search [1 2 3]
                       (r/scan ?x :as ?v)
                       ?x))
          "every element should be found by the scan"))

  (t/is (= '([:_1 "_1"] [:_2 "_2"] [:_3 "_3"] [:_4 "_4"])
           (r/search [{:_1 "_1"} {:_2 "_2", :_3 "_3"} {:_4 "_4"}]
             (r/scan {?k ?v})
             [?k ?v])
           (r/search '({:_1 "_1"} {:_2 "_2", :_3 "_3"} {:_4 "_4"})
             (r/scan {?k ?v})
             [?k ?v])))

  (t/is (= '([1 2 3] [4 5 6])
           (r/search [[1 2] 3 [4 5] 6]
             (r/scan [?x ?y] ?z)
             [?x ?y ?z])
           (r/search '([1 2] 3 [4 5] 6)
             (r/scan [?x ?y] ?z)
             [?x ?y ?z])))

  (t/is (= ()
           (r/search []
             (r/scan ?x)
             ?x))))

;;; separated


(t/deftest separated-test
  (t/is (= '([1 2 3 4] [1 2 4 5] [2 3 4 5])
           (r/search (list 1 2 3 4 5)
             (_ ... ?a ?b . _ ... ?c ?d . _ ...)
             [?a ?b ?c ?d])
           (r/search [1 2 3 4 5]
             [_ ... ?a ?b . _ ... ?c ?d . _ ...]
             [?a ?b ?c ?d]))))

;; ---------------------------------------------------------------------
;; find


(tc.t/defspec find-results-are-elements-of-search-results
  (tc.prop/for-all [v (tc.gen/vector tc.gen/nat 3 5)]
    (contains? (set (r/search v
                      [!xs ..1 !ys ...]
                      {'!xs !xs, '!ys !ys}))
               (r/find v
                 [!xs ..1 !ys ...]
                 {'!xs !xs, '!ys !ys}))))


(t/deftest find-mvrs-are-collected-properly
  (let [data [{:name "George"
               :owners ["Frege" "Peirce"]}
              {:name "Francis"
               :owners ["De Morgan"]}
              {:name "Bob"
               :owners ["Peirce"]}]
        expected-results #{{:owner "De Morgan", :names ["Francis"]}
                           {:owner "Frege", :names ["George"]}
                           {:owner "Peirce", :names ["Bob"]}
                           {:owner "Peirce", :names ["George"]}
                           {:owner "Peirce", :names ["George" "Bob"]}}]
    (t/is (= expected-results
             (set (r/search data
                    [_ ...
                     {:name !names
                      :owners [_ ... ?owner . _ ...]}
                     .
                     (r/or {:name !names
                            :owners [_ ... ?owner . _ ...]}
                           _)
                     ...]
                    {:owner ?owner
                     :names !names}))))
    (t/is (contains? expected-results
                     (r/find data
                       [_ ...
                        {:name !names
                         :owners [_ ... ?owner . _ ...]}
                        .
                        (r/or {:name !names
                               :owners [_ ... ?owner . _ ...]}
                              _)
                        ...]
                       {:owner ?owner
                        :names !names})))))


(t/deftest find-separated-items
  (t/is (= [:a :v '[[:any _] [:lvr ?a] [:lit "Bill"]]]
           (r/find '([:a [[:any _] [:lvr ?a] [:lit "Bill"]]]
                     [:e [[:lvr ?e] [:any _] [:lit "Alice"]]]
                     [:v [[:any _] [:lvr ?a] [:lit "Bill"]]])
             (_ ... [?i1 ?tuple] . _ ... [?i2 ?tuple] . _ ...)
             [?i1 ?i2 ?tuple]))))

;; ---------------------------------------------------------------------
;; subst

(t/deftest subst-lvr-test
  (let [?1 1
        ?2 2
        ?3 3]
    (t/is (= {1 [2 {3 []}]}
             (r/subst {?1 [?2 {?3 []}]})))
    (t/is (= [?2 ?3 ?1]
             (r/subst [?2 ?3 ?1])))))


(t/deftest subst-mvr-test
  (let [!1s [1 2 3]]
    (t/is (= {1 [2 {3 []}]}
             (r/subst {!1s [!1s {!1s []}]})))))


(t/deftest subst-mvr-rp*-test
  (let [!1s [1 2 3]
        !2s [:a :b]]
    (t/is (= '(1 2 3)
             (r/subst (!1s ...))))
    (t/is (= '(1 2 3)
             (r/subst [!1s ...])))
    (t/is (= [[1] [2] [3]]
             (r/subst [[!1s] ...])))
    (t/is (= [[1 :a] [2 :b]]
             (r/subst [[!1s !2s] ...])))
    (t/is (= [[:a 1] [:b 2]]
             (r/subst [[!2s !1s] ...])))))


(t/deftest subst-mvr-rp+-test
  (let [!1s [1 2 3]
        !2s [:a :b]]
    (t/is (= [[1] [2] [3] [nil]]
             (r/subst [[!1s] ..4])))
    (t/is (= [[1 :a] [2 :b] [3 nil] [nil nil]]
             (r/subst [[!1s !2s] ..4])))))

(t/deftest subst-rp+-test
  (t/is (= [:A :A :A :A]
           (r/subst [:A ..4])))

  (let [!1s [:X :O :X :O]
        !2s [1 2 3 4]]
    (t/is (= [:X 1 :O 2 :X :O 3 4]
             (r/subst [!1s !2s ..2 !1s ... !2s ...])))))


(t/deftest subst-unq-test
  (let [x 1]
    (t/is (= [x] (r/subst [~x])))))


(t/deftest subst-prt-test
  (t/is (= [1 2]
           (r/subst [1 . 2])))

  (t/is (= [1 2]
           (r/subst [. 1 2])))

  (t/is (= [1 2]
           (r/subst [1 2 .])))

  (let [!1s [:X :O :X :O]]
    (t/is (= [1 2 :X :O :X :O]
             (r/subst [1 2 . !1s ...])))
    (t/is (= [:X :O :X :O 1 2]
             (r/subst [!1s ... 1 2])))))

(t/deftest subst-map-test
  (let [?rest-map {:foo "bar"}]
    (t/is (= (r/subst {:bar "baz" & ?rest-map})
             {:foo "bar", :bar "baz"}))

    (t/is (= (r/subst {:bar "baz" & ?rest-map :as "quux"})
             {:foo "bar", :bar "baz", :as "quux"}))

    (t/is (= (r/subst {& ?rest-map})
             {:foo "bar"}))

    (t/is (= (r/subst {:foo "bar" & [[:bar "foo"] [:quux "frob"]]})
             {:foo "bar" :bar "foo" :quux "frob"}))

    (t/is (= (r/subst {:foo "bar" & [[:bar "foo"] [:quux "frob"]]})
             {:foo "bar" :bar "foo" :quux "frob"}))

    (let [!ks [:foo :bar :baz]
          !vs [1111 2222 3333]]
      (t/is (= (r/subst {& [[!ks !vs] ...]})
               {:foo 1111, :bar 2222, :baz 3333})))

    (let [!ms [{:foo 1}
               {:foo 2}
               {:foo 3}]
          !ns [4 5 6]]
      (t/is (= (r/subst [{:bar !ns & !ms} ...])
               [{:foo 1, :bar 4}
                {:foo 2, :bar 5}
                {:foo 3, :bar 6}])))

    (let [!k [1 2 3]
          !v [4 5 6]]
      (t/is (= {1 4, 2 5, 3 6}
               (r/subst (r/with [%m {!k !v & %m}]
                          %m)))))

    (let [!k [1 2]
          !v [4 5 6]]
      (t/is (= {1 4, 2 5}
               (r/subst (r/with [%m {!k !v & %m}]
                          %m)))))

    (let [!k [1 2 3]
          !v [4]]
      (t/is (= {1 4, 2 nil, 3 nil}
               (r/subst (r/with [%m {!k !v & %m}]
                          %m)))))

    (let [?rest {:foo "bar" :baz "quux" :quux "ducks"}
          ?as {:foo "goo" :frob "knob"}]
      (t/is (= {:foo "quux" :baz "bar" :quux "ducks" :frob "knob"}
               (r/subst {:foo "quux" :baz "bar" & ?rest :as ?as}))))))


(t/deftest subst-$-test
  (let [?context (fn [hole]
                   [1 hole 2])
        ?value 3
        !values [:A :B :C]]
    (t/is (= (r/subst (r/$ ?context ?value))
             [1 3 2]))
    (t/is (= (r/subst (r/$ ?value))
             3))
    (t/is (r/subst [(r/$ ?context !values) ...])
          [[1 :A 2]
           [1 :B 2]
           [1 :C 2]])))


(t/deftest subst-set-test
  (let [?rest-set #{2 3}]
    (t/is (= #{1 2 3}
             (r/subst #{1 ^& ?rest-set}))))
  (let [?a-set #{1 4}
        ?b-set #{2 3}]
    (t/is (= #{1 2 3 4}
             (r/subst #{^:as ?a-set ^& ?b-set})))))


(t/deftest subst-with-test
  (let [!tags [1 2 3]]
    (t/is (= [1 [2 [3]]]
             (r/subst
               (r/with [%h1 [!tags . %h1 ...]]
                 %h1)))))
  (let [!xs [11 12 14]
        ?y 12
        ?z 13]
    (t/is (= [[11 13] [12 13] [14 13]]
             (r/subst
               (r/with [%foo [%bar ..3]
                        %bar [!xs ?z]]
                 %foo))))))


(tc.t/defspec subst-rst-behaves-properly-1
  (tc.prop/for-all [!xs (tc.gen/vector tc.gen/int)]
    (= !xs (r/subst [!xs ...]))))


(tc.t/defspec subst-rst-behaves-properly-2
  (tc.prop/for-all [!ys (tc.gen/vector tc.gen/int)
                    !xs (tc.gen/vector tc.gen/int)]
    (= (into !ys !xs)
       (r/subst [!ys ... !xs ...]))))


(t/deftest subst-logical-length-subsequence-test
  (let [!xs [1 2 3]
        ?n 3]
    (t/is (= '[1 2 3]
             (r/subst [!xs ..?n])))))


(t/deftest subst-memory-length-subsequence-test
  (let [!xs [1 2 3 4 5]
        !ns [2 1 2]]
    (t/is (= '[[1 2] [3] [4 5]]
             (r/subst [[!xs ..!ns] [!xs ..!ns] [!xs ..!ns]])))))


(t/deftest subst-&-test
  (let [?rest '(2 3 4)]
    (t/is (= '(1 2 3 4)
             (r/subst (1 & ?rest)))))

  (let [?rest '[2 3 4]]
    (t/is (= '[1 2 3 4]
             (r/subst [1 & ?rest])))))


(t/deftest subst-app-test
  (let [?f inc
        ?x 10]
    (t/is (= 11
             (r/subst (r/app ?f ?x))))))


(t/deftest subst-keyword
  (t/is (= :foo
           (r/subst (r/keyword "foo"))))

  (t/is (= :foo/bar
           (r/subst (r/keyword "foo" "bar")))))


(t/deftest subst-symbol
  (t/is (= 'foo
           (r/subst (r/symbol "foo"))))

  (t/is (= 'foo/bar
           (r/subst (r/symbol "foo" "bar")))))

;; ---------------------------------------------------------------------
;; rewrite

(defn fib-identity [t]
  (r/rewrite t
    (+ 0 ?x)
    ?x

    (+ ?x 0)
    ?x))

(defn fib-add [t]
  (r/rewrite t
    (+ (s ?x) ?y)
    (s (+ ?x ?y))))

(defn fib-0 [t]
  (r/rewrite t
    (fib 0)
    0))

(defn fib-1 [t]
  (r/rewrite t
    (fib (s 0))
    (s 0)))

(defn fib-n [t]
  (r/rewrite t
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

(t/deftest gh-72-test
  (let [names ["JOHN" "PAUL" "DOE"]]
    (t/is (= [["JOHN" "PAUL"] "DOE"]
             (r/find names
               [!firsts ..1 ?last]
               [!firsts ?last]))))
  (let [names ["MADONNA"]]
    (t/is (= nil
             (r/find names
               [!firsts ..1 ?last]
               [!firsts ?last]))))

  (let [names ["JOHN" "DOE"]]
    (t/is (= [["JOHN"]"DOE"]
             (r/find names
               [!firsts ..1 ?last]
               [!firsts ?last])))))

(t/deftest match-$-test
  (t/is (= ["bar"]
           (r/match {:foo ["bar"]}
             (r/$ [& _ :as ?x])
             ?x)))

  (t/is (= {:foo [["bar"]]}
           (r/match {:foo ["bar"]}
             (r/$ ?foo [& _ :as ?x])
             (?foo [?x])))))

(t/deftest memory-variable-key-test
  (t/is (= [1 2]
           (r/find [1 {2 3}]
             [!xs {!xs _}]
             !xs))))

(t/deftest some-test-1
  (t/is (= '(:ok)
           (r/search nil
             (r/not (r/some _))
             :ok))))

(t/deftest keyword-test
  (t/is (r/match :foo
          (r/keyword "foo")
          true
          _
          false))

  (t/is (r/match :foo/bar
          (r/keyword "foo" _)
          true
          _
          false))

  (t/is (r/match :foo/bar
          (r/keyword _ "bar")
          true
          _
          false))

  (t/is (r/match :foo/bar
          (r/keyword "foo" "bar")
          true
          _
          false))

  (t/is (= :foo/bar
           (r/match :foo/bar
             (r/keyword _ _ :as ?keyword)
             ?keyword

             _
             false)))

  (t/is (= :foo (r/subst (r/keyword "foo"))))
  (t/is (= :foo (r/subst (r/keyword nil "foo"))))
  (t/is (= :foo/bar (r/subst (r/keyword "foo" "bar"))))
  (t/is (= :foo/bar (r/subst (r/keyword "foo" "bar" :as :ignored)))))

(t/deftest symbol-test
  (t/is (r/match 'foo
          (r/symbol "foo")
          true

          _
          false))

  (t/is (r/match 'foo/bar
          (r/symbol "foo" _)
          true
          _
          false))

  (t/is (r/match 'foo/bar
          (r/symbol _ "bar")
          true
          _
          false))

  (t/is (r/match 'foo/bar
          (r/symbol "foo" "bar")
          true
          _
          false))

  (t/is (= 'foo/bar
           (r/match 'foo/bar
             (r/symbol _ _ :as ?symbol)
             ?symbol

             _
             false)))

  (t/is (= 'foo (r/subst (r/symbol "foo"))))
  (t/is (= 'foo (r/subst (r/symbol nil "foo"))))
  (t/is (= 'foo/bar (r/subst (r/symbol "foo" "bar"))))
  (t/is (= 'foo/bar (r/subst (r/symbol "foo" "bar" :as 'ignored)))))


(t/deftest keyword-seq-correct
  (t/is
   (let [x {:foo {:test 2}}]
     (r/match (:foo x)
       {:as _}
       true

       _
       false))))

;; No "real" tests here. This "test" verifies that patterns which
;; previously produced Clojure code which triggered
;; `clojure.lang.Compiler$CompilerException`s due to symbols being
;; unresolvable, no longer due. The patterns used in the test below
;; are real examples (though not necessarily minimal) which triggered
;; the exception.
(t/deftest ir-def-test
  (fn [x]
    (r/match x
      (r/or {:type "save-snapshot"
             :arg {:description (r/or (r/pred string?) nil)}}
            {:type "suggest-snapshot"})
      true

      _
      false)

    (r/match x
      (r/with [%e-entidade (r/or (= e (r/pred symbol? ?e))
                                 (= (r/pred symbol? ?e) e))
               %a-atributo (r/or (= a (r/pred keyword? ?a))
                                 (= (r/pred keyword? ?a) a))
               %v-valor (r/or (= v (r/pred symbol? ?v))
                              (= (r/pred symbol? ?v) v))
               %v-args (r/or v (r/pred symbol? ?v)) 
               %v-teste-unario ((r/pred fn? !funcao-unaria) !param-function-unaria)
               %operador-binario (r/pred #{'< '> '<= '>= 'not=} !funcao-binaria)
               %v-teste-binario (%operador-binario %v-args !param-function-binaria)
               %v-teste-funcao (r/or %v-teste-unario %v-teste-binario)]
        [%e-entidade %a-atributo %v-valor . %v-teste-funcao ..1])
      true

      _
      false)))

(t/deftest rp-subst-test
  (t/is (= (r/rewrite (range 20)
             (!a !b ...)
             {& [[!a !b] ...]})
           {0 1, 4 5, 6 7, 12 13, 2 3, 14 15, 16 17, 10 11, 18 19, 8 9}))

  (t/is (= (r/rewrite (range 20)
             (!a !b ...)
             {& [[!a !b] ..20]})
           {nil nil, 0 1, 4 5, 6 7, 12 13, 2 3, 14 15, 16 17, 10 11, 18 19, 8 9}))

  (t/is (= (r/rewrite [4 (range 20)]
             [?n (!a !b ...)]
             {& [[!a !b] ..?n]})
           {0 1, 2 3, 4 5, 6 7}))

  (t/is (= (r/rewrite [4 (range 20)]
             [!n (!a !b ...)]
             {& [[!a !b] ..!n]})
           {0 1, 2 3, 4 5, 6 7})))


(t/deftest map-of-test
  (t/is (= #{#{"baz" "foo"} #{"quux" "bar"}}
          (r/find {"foo" "bar", "baz" "quux"}
             (r/map-of (r/pred string? !k) !v)
             #{(set !k) (set !v)})))

  (t/is (= nil
          (r/find {:foo "bar", :baz "quux"}
             (r/map-of (r/pred string? !k) !v)
             #{!k !v})))

  (t/is (= nil
          (r/find {"foo" "bar", :baz "quux"}
             (r/map-of (r/pred string? !k) !v)
             #{!k !v})))

  (t/is (= {}
          (let [!k []
                !v []]
            (r/subst (r/map-of !k !v)))))

  (t/is (= {"foo" 1, "bar" 2}
          (let [!k ["foo" "bar"]
                !v [1 2]]
             (r/subst (r/map-of !k !v))))))

(t/deftest submap-of-test
  (t/is (= [[] []]
          (r/find {}
             (r/submap-of (r/pred string? !k) !v)
             [!k !v])))

  (t/is (= #{#{"baz" "foo"} #{"quux" "bar"}}
          (set (r/find {"foo" "bar", "baz" "quux"}
                  (r/submap-of (r/pred string? !k) !v)
                  #{(set !k) (set !v)}))))

  (t/is (= [[] []]
          (r/find {:foo "bar", :baz "quux"}
             (r/submap-of (r/pred string? !k) !v)
             [!k !v])))

  (t/is (= #{["foo"] ["bar"]}
          (set (r/find {"foo" "bar", :baz "quux"}
                  (r/submap-of (r/pred string? !k) !v)
                  [!k !v]))))
  (t/is (= {}
          (let [!k []
                !v []]
            (r/subst (r/submap-of !k !v)))))

  (t/is (= {"foo" 1, "bar" 2}
          (let [!k ["foo" "bar"]
                !v [1 2]]
            (r/subst (r/submap-of !k !v))))))
