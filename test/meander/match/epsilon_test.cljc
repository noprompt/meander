(ns meander.match.epsilon-test
  (:require [clojure.spec.alpha :as s :include-macros true]
            [clojure.test :as t]
            [clojure.test.check.clojure-test :as tc.t :include-macros true]
            [clojure.test.check.generators :as tc.gen :include-macros true]
            [clojure.test.check.properties :as tc.prop :include-macros true]
            [meander.match.epsilon :as r.match :include-macros true]
            [meander.syntax.epsilon :as r.syntax :include-macros true]))

(def gen-scalar
  (tc.gen/one-of [tc.gen/int
                  tc.gen/string
                  tc.gen/keyword
                  tc.gen/symbol]))

;; ---------------------------------------------------------------------
;; match macro tests

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
  (tc.prop/for-all [x gen-scalar]
    (r.match/match x
      ~x
      true

      _
      false)))


(tc.t/defspec _-matches-any-x
  (tc.prop/for-all [x gen-scalar]
    (r.match/match x
      _
      true)))


(tc.t/defspec ?x-matches-any-x
  (tc.prop/for-all [x gen-scalar]
    (r.match/match x
      ?x
      true)))


(tc.t/defspec !xs-collects-any-x
  (tc.prop/for-all [x gen-scalar]
    (r.match/match x
      !xs
      (= [x] !xs)

      _
      false)))


(tc.t/defspec pred-succeeds
  (tc.prop/for-all [x tc.gen/nat]
    (r.match/match x
      (r.match/pred nat-int?)
      true)))


(tc.t/defspec pred-fails
  (tc.prop/for-all [x tc.gen/nat]
    (r.match/match x
      (r.match/pred string?)
      false

      _
      true)))


(tc.t/defspec guard-succeeds
  (tc.prop/for-all [x tc.gen/nat]
    (r.match/match x
      (r.match/guard (= 1 1))
      true)))


(tc.t/defspec guard-fails
  (tc.prop/for-all [x tc.gen/nat]
    (r.match/match x
      (r.match/guard (= 1 2))
      false

      _
      true)))


(tc.t/defspec and-succeeds
  (tc.prop/for-all [x tc.gen/nat]
    (r.match/match x
      (r.match/and ~x ?x)
      (= x ?x))))


(tc.t/defspec and-fails
  (tc.prop/for-all [x gen-scalar]
    (r.match/match x
      (r.match/and ~x ?x (r.match/guard false))
      false

      _
      true)))


(tc.t/defspec or-succeeds
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar
                    z gen-scalar]
    (r.match/match x
      (r.match/or ~z ~y ~x)
      true

      _
      false)))


(tc.t/defspec or-fails
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar
                    z gen-scalar]
    (r.match/match [x y z]
      (r.match/or ~z ~y ~x)
      false

      _
      true)))

#?(:clj
   ;; If this let appears inside the `deftest` form the test fails for
   ;; unclear reasons.
   (let [x (try
             (macroexpand '(meander.match.epsilon/match 1
                             (r.match/or ?x ?y ?z)
                             false))
             false
             (catch Exception _
               true))]
     (t/deftest or-compilation-fails
       (t/is x))))

(tc.t/defspec let-succeeds
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar]
    (r.match/match y
      (r.match/and ?y (r.match/let ?x x))
      (and (= y ?y)
           (= x ?x)))))


(tc.t/defspec let-fails
  (tc.prop/for-all [x gen-scalar]
    (r.match/match x
      (r.match/and ?x (r.match/let ?x [x]))
      false

      _
      true)))


(t/deftest let-test
  (t/is (= [1 2]
           (r.match/match 42
             (r.match/or [?x ?y] (r.match/let [?x ?y] [1 2]))
             [?x ?y]))))

;; Seqs

(tc.t/defspec seq-unquote-patterns-match
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar]
    (r.match/match (list x (list y x) y)
      (~x (~y ~x) ~y)
      true

      _
      false)))


(tc.t/defspec seq-lvrs-bind-the-values-they-match
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar]
    (r.match/match (list x (list y x) y)
      (?x (?y ?x) ?y)
      (and (= ?x x)
           (= ?y y))

      _
      false)))


(tc.t/defspec seq-mvrs-collect-the-values-they-match
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar]
    (r.match/match (list x (list y x) y)
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
    (r.match/match `(~@(mapcat identity (repeat n [x1 x2])) ~y1 ~y2)
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
    (r.match/match `(~x1 ~x2 ~@(mapcat identity (repeat n [y1 y2])))
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
    (r.match/match `(~@(mapcat identity (repeat n [x1 x2])) ~y1 ~y2)
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
    (r.match/match `(~x1 ~x2 ~@(mapcat identity (repeat n [y1 y2])))
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
    (r.match/match `(~@(mapcat identity (repeat n [x1 x2])) ~y1 ~y2)
      (?x1 ?x2 . ?x1 ?x2 ... ~y1 ~y2)
      (and (= x1 ?x1)
           (= x2 ?x2))

      _
      false)))


(tc.t/defspec seq-zero-or-more-nested-mvr
  (tc.prop/for-all [n (tc.gen/such-that (complement zero?) tc.gen/nat)
                    b (s/gen simple-symbol?)
                    v gen-scalar]
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
                    x gen-scalar]
    (r.match/match `(~@(map identity (repeat n x)) ~x ~x)
      (_ ... ~x ~x)
      true)))


(tc.t/defspec seq-drop-in-tail
  (tc.prop/for-all [n tc.gen/nat
                    x gen-scalar]
    (r.match/match `(~@(map identity (repeat n x)) ~x ~x)
      (~x ~x . _ ...)
      true

      _
      false)))


(tc.t/defspec seq-?x-guard-true-?x-succeeds
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar]
    (r.match/match (list x y x)
      (?x (r.match/guard (= ?x ?x)) ?x)
      true

      _
      false)))

(t/deftest seq-as-pattern
  (let [xs '(1 2 3)]
    (t/is (r.match/match xs
            (?x ?y ?z :as ?xs)
            (= (list ?x ?y ?z) ?xs xs)))))

;; ---------------------------------------------------------------------
;; Vectors

(tc.t/defspec vec-unquote-patterns-match
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar]
    (r.match/match [x [y x] y]
      [~x [~y ~x] ~y]
      true

      _
      false)))


(tc.t/defspec vec-lvrs-bind-the-values-they-match
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar]
    (r.match/match [x [y x] y]
      [?x [?y ?x] ?y]
      (and (= ?x x)
           (= ?y y))

      _
      false)))


(tc.t/defspec vec-mvrs-collect-the-values-they-match
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar]
    (r.match/match [x [y x] y]
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
    (r.match/match `[~@(mapcat identity (repeat n [x1 x2])) ~y1 ~y2]
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
    (r.match/match `[~x1 ~x2 ~@(mapcat identity (repeat n [y1 y2]))]
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
    (r.match/match `[~@(mapcat identity (repeat n [x1 x2])) ~y1 ~y2]
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
    (r.match/match `[~x1 ~x2 ~@(mapcat identity (repeat n [y1 y2]))]
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
    (r.match/match `[~@(mapcat identity (repeat n [x1 x2])) ~y1 ~y2]
      [?x1 ?x2 . ?x1 ?x2 ... ~y1 ~y2]
      (and (= x1 ?x1)
           (= x2 ?x2))

      _
      false)))


(tc.t/defspec vec-zero-or-more-nested-mvr
  (tc.prop/for-all [n (tc.gen/such-that (complement zero?) tc.gen/nat)
                    b (s/gen simple-symbol?)
                    v gen-scalar]
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
                    x gen-scalar]
    (r.match/match `[~@(map identity (repeat n x)) ~x ~x]
      [_ ... ~x ~x]
      true)))


(tc.t/defspec vec-drop-in-tail
  (tc.prop/for-all [n tc.gen/nat
                    x gen-scalar]
    (r.match/match `[~@(map identity (repeat n x)) ~x ~x]
      [~x ~x . _ ...]
      true)))


(tc.t/defspec vec-?x-guard-true-?x-succeeds
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar]
    (r.match/match [x y x]
      [?x (r.match/guard (= ?x ?x)) ?x]
      true

      _
      false)))


(t/deftest vec-compile-inside-seq
  (t/is (= 1 (r.match/match '(let [] 1)
               ('let [] ?x)
               ?x))))

(t/deftest vec-as-pattern
  (let [xs [1 2 3]]
    (t/is (r.match/match xs
            [?x ?y ?z :as ?xs]
            (= [?x ?y ?z] ?xs xs)))))

;; ---------------------------------------------------------------------
;; Maps


(tc.t/defspec map-unq-succeeds
  (tc.prop/for-all [x gen-scalar]
    (r.match/match {:x x, x :x}
      {:x ~x, ~x :x}
      true

      _
      false)))


(tc.t/defspec map-lvr-succeeds
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar]
    (r.match/match {:x x, :y y}
      {:x ?x, :y ?y}
      (= [x y] [?x ?y])

      _
      false)))


(tc.t/defspec map-vec-lvr
  (tc.prop/for-all [x gen-scalar]
    (let [y [x]
          z [[x]]]
      (r.match/match {:x [x y], :y [y x], :z [z]}
        {:x [?x ?y] :y [?y ?x] :z [?z]}
        (= [x y z] [?x ?y ?z])

        _
        false))))


(tc.t/defspec map-seq-lvr
  (tc.prop/for-all [x gen-scalar]
    (let [y [x]
          z [[x]]]
      (r.match/match {:x (list x y), :y (list y x), :z (list z)}
        {:x (?x ?y) :y (?y ?x) :z (?z)}
        (= (list x y z) (list ?x ?y ?z))

        _
        false))))


(tc.t/defspec map-mvr
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar]
    (r.match/match {:x x, :y y}
      {:x !xs, :y !xs}
      (or (= [x y] !xs)
          (= [y x] !xs))

      _
      false)))


(tc.t/defspec map-map
  (tc.prop/for-all [x gen-scalar]
    (r.match/match {:x {:y x}, :z x}
      {:x {:y ~x}, :z ~x}
      true

      _
      false)))


(t/deftest map-rest-map
  (t/is (= [2 {:_1 1, :_3 3}]
           (r.match/match {:_1 1, :_2 2, :_3 3}
             {:_2 ?2 & ?rest-map}
             [?2 ?rest-map])))

  (t/is (= [2 3 {:_1 1}]
           (r.match/match {:_1 1, :_2 2, :_3 3}
             {:_2 ?2 & {:_3 ?3 & ?rest-map}}
             [?2 ?3 ?rest-map])))

  (t/is (= '([2 :_1 1] [2 :_3 3])
           (r.match/search {:_1 1, :_2 2, :_3 3}
             {:_2 ?2 & (r.match/scan [?k ?v])}
             [?2 ?k ?v]))))


;; ---------------------------------------------------------------------
;; Sets

(t/deftest set-unq
  (let [k1 :k1
        k2 :k2]
    (r.match/match #{k1 k2}
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
            (r.match/search #{1 2 3}
              #{_ ^& #{_ ^& ?s3 ^:as ?s2} ^:as ?s1}
              [?s1 ?s2 ?s3])))))


#_
;; TODO: This should pass because ?x in the nested map can be bound
;; previously.
(t/deftest map-can-have-variable-bound-lvr-keys
  (r.match/match {:k1 "v1"
                  :k2 {"v1" "v2"}}
    {:k1 ?x
     :k2 {?x ?y}}
    (t/is (and (= ?x "v1")
               (= ?y "v2")))

    _
    false))

#_
;; TODO: This should pass because ?x and ?y can be matched before
;; attempting to match the subsequence.
(t/deftest zero-or-more-may-have-lvrs-if-lvrs-can-be-bound
  (t/is  (= [1 2]
            (r.match/match [[1 2 1 2] 1 2]
              [[?x ?y ...] ?x ?y]
              [?x ?y]))))

#_
;; TODO: This should pass because there is only one possible solution.
(t/deftest sequences-of-zero-or-more
  (t/is (r.match/match [1 2 3 4 3 4]
          [1 2 ... 3 4 ...]
          true

          _
          false)))



;; ---------------------------------------------------------------------
;; seqables


(t/deftest basic-seqables
  (t/is (r.match/match [1 2 3]
          (r.match/seqable 1 2 3)
          true
          _
          false))

  (t/is (r.match/match (list 1 2 3)
          (r.match/seqable 1 2 3)
          true
          _
          false))

  (t/is (r.match/match (range 1 4)
          (r.match/seqable 1 2 3)
          true
          _
          false))


  (t/is (r.match/match [1 2 3]
          (r.match/seqable ?x ?y ?z)
          true
          _
          false))

  (t/is (r.match/match (list 1 2 3)
          (r.match/seqable ?x ?y ?z)
          true
          _
          false))

  (t/is (r.match/match #{1 2 3}
          (r.match/seqable ?x ?y ?z)
          true
          _
          false))

  (t/is (r.match/match #{1 2}
          (r.match/seqable ?x ?y ?z)
          false
          _
          true))

  (t/is (r.match/match {(list :left) [:right]}
          (r.match/seqable (r.match/seqable (r.match/seqable ?x) (r.match/seqable ?y)))
          true
          _
          false))

  (t/is (r.match/match [(list :left) [:right]]
          [(r.match/seqable ?x) (r.match/seqable ?y)]
          true
          _
          false)))

(t/deftest search-seqables
  (t/is (= 4 (count
              (r.match/search [[1 2] (list 1 2) #{1 2} {1 2}]
                (r.match/scan (r.match/seqable ?x ?y))
                [?x ?y]

                (r.match/scan (r.match/seqable (r.match/seqable ?x ?y)))
                [?x ?y]))))

  (t/is (= 4 (count
              (r.match/search {:a [1 2 3]
                               :b (range 10)
                               :c #{1 2 3}
                               :d {:a 1 :b 2}}
                {?key (r.match/seqable !xs ...)}
                !xs)))))


(defn make-array-list [& args]
  #?(:clj
     (java.util.ArrayList. args)

     :cljs
     (into-array args)))

(def ordered-seqable-gen
  (tc.gen/elements [list vector make-array-list]))

(tc.t/defspec seqable-unquote-patterns-match
  (tc.prop/for-all [coll ordered-seqable-gen
                    x gen-scalar
                    y gen-scalar]
    (r.match/match (coll x (coll y x) y)
      (r.match/seqable ~x (r.match/seqable ~y ~x) ~y)
      true

      _
      false)))


(tc.t/defspec seqable-drop-in-head
  (tc.prop/for-all [n tc.gen/nat
                    x gen-scalar]
    (r.match/match `[~@(map identity (repeat n x)) ~x ~x]
      (r.match/seqable _ ... ~x ~x)
      true)))


(tc.t/defspec seqable-drop-in-tail
  (tc.prop/for-all [n tc.gen/nat
                    x gen-scalar]
    (r.match/match `[~@(map identity (repeat n x)) ~x ~x]
      (r.match/seqable~x ~x . _ ...)
      true)))


(tc.t/defspec seqable-mvrs-collect-the-values-they-match
  (tc.prop/for-all [x gen-scalar
                    y gen-scalar]
    (r.match/match [x [y x] y]
      (r.match/seqable !xs (r.match/seqable !ys !xs) !ys)
      (and (= [x x] !xs)
           (= [y y] !ys))

      _
      false)))


;; ---------------------------------------------------------------------
;; re form

(t/deftest re-test
  (t/is (r.match/match "foo foo foo"
          (r.match/re #"(?:foo ?)+")
          true
          _
          false))

  (t/is (r.match/match "Harry Hacker"
          (r.match/re #"([^ ]+) *([^ ]+)" [_ ?Harry ?Hacker])
          (and (= ?Harry "Harry")
               (= ?Hacker "Hacker"))
          _
          false)))

;; ---------------------------------------------------------------------
;; search macro tests


(t/deftest search-map-1-test
  (t/is (= #{["v1" "v2"]}
           (set (r.match/search {:k1 "v1", :k2 {"v1" "v2"}}
                  {:k1 ?x, :k2 {?x ?y}}
                  [?x ?y])))))

(t/deftest search-map-2-test
  (t/is (= #{[:k1 :k2 "v1" "v2"]
             [:k1 :k2 :k3 "v1" "v2"]}
           (set (r.match/search {:k1 "v1"
                                 :k2 {"v1" "v2"}
                                 :k3 {"v2" "v1"}}

                  {?k1 ?x
                   ?k2 {?x ?y}}
                  [?k1 ?k2 ?x ?y]

                  {?k1 ?x
                   ?k2 {?x ?y}
                   ?k3 {?y ?x}}
                  [?k1 ?k2 ?k3 ?x ?y])))))


(t/deftest search-or-1-test
  (t/is (= #{[1 2] [2 1]}
           (set (r.match/search [1 2]
                  (r.match/or [?x ?y] [?y ?x])
                  [?x ?y])))))


(t/deftest search-subsequence-1-test
  (t/is (= (set (r.match/search [1 2 1 2 1 2 3 5 6 7 8 9 1 2]
                  [?x ?y . ?x ?y ... !zs ... ?x ?y]
                  {:?x ?x, :?y ?y, :!zs !zs}))
           #{{:?x 1, :?y 2, :!zs [1 2 1 2 3 5 6 7 8 9]}
             {:?x 1, :?y 2, :!zs [1 2 3 5 6 7 8 9]}
             {:?x 1, :?y 2, :!zs [3 5 6 7 8 9]}})))

(t/deftest scan-test
  (t/is (= '([:_1 "_1"] [:_2 "_2"] [:_3 "_3"] [:_4 "_4"])
           (r.match/search [{:_1 "_1"} {:_2 "_2", :_3 "_3"} {:_4 "_4"}]
             (r.match/scan {?k ?v})
             [?k ?v])
           (r.match/search '({:_1 "_1"} {:_2 "_2", :_3 "_3"} {:_4 "_4"})
             (r.match/scan {?k ?v})
             [?k ?v])))

  (t/is (= '([1 2 3] [4 5 6])
           (r.match/search [[1 2] 3 [4 5] 6]
             (r.match/scan [?x ?y] ?z)
             [?x ?y ?z])
           (r.match/search '([1 2] 3 [4 5] 6)
             (r.match/scan [?x ?y] ?z)
             [?x ?y ?z]))))

(t/deftest separated-test
  ;; Note [1 2 4 5] appears twice in the result set, this is not a
  ;; bug but a product of how the search space is computed and
  ;; searched.
  ;;
  ;;    [[ 1  2] [ 3  4  5]]  ;; 1
  ;;      ?a ?b   ?c ?d
  ;;                 ?c ?d    ;; 2
  ;;    [[ 1  2  3] [ 4  5]]
  ;;      ?a ?b      ?c ?d    ;; 3
  ;;         ?a ?b   ?c ?d    ;; 4
  ;;    [[1 2 3 4] [5]]       ;; Nothing for ?c ?d
  ;;    [[ 1 2 3 4 5] []]     ;; Nothing for ?c ?d
  (t/is (= '([1 2 3 4] [1 2 4 5] [1 2 4 5] [2 3 4 5])
           (r.match/search (list 1 2 3 4 5)
             (_ ... ?a ?b . _ ... ?c ?d . _ ...)
             [?a ?b ?c ?d])
           (r.match/search [1 2 3 4 5]
             [_ ... ?a ?b . _ ... ?c ?d . _ ...]
             [?a ?b ?c ?d]))))

;; ---------------------------------------------------------------------
;; Misc

(t/deftest memory-variables-in-nested-zero-or-more
  (t/is (= [[:aa :bb :cc] [1 2 3]]
           (r.match/match '[([:aa 1] [:bb 2]) ([:cc 3])]
             [([!ks !vs] ...) ...]
             [!ks !vs]

             _
             nil))))


(t/deftest pred
  (t/is (= [1 2]
           (r.match/match 1
             (r.match/pred odd? ?x (r.match/app inc ?y))
             [?x ?y]))))


(t/deftest app
  (t/is (r.match/match {:foo 1}
          (r.match/app (fn [m] (assoc m :baz 2))
                       ?x
                       (r.match/app (fn [m] (assoc m :quux 3))
                                    ?y)
                       (r.match/let ?z [?x ?y]))
          (= ?z
             [?x ?y]
             [{:foo 1, :baz 2}
              {:foo 1, :baz 2, :quux 3}]))))


(t/deftest ground-map-pattern-expresses-submap-match
  (t/is (r.match/match {:foo 1, :bar 2, :baz 3}
          {:foo 1}
          true))

  (t/is (r.match/match {:foo 1, :bar 2, :baz 3}
          {:bar 2}
          true))

  (t/is (r.match/match {:foo 1, :bar 2, :baz 3}
          {:baz 3}
          true))

  (t/is (r.match/match {:foo 1, :bar 2, :baz 3}
          {:foo 1, :bar 2}
          true))

  (t/is (r.match/match {:foo 1, :bar 2, :baz 3}
          {:bar 2, :baz 3}
          true))

  (t/is (r.match/match {:foo 1, :bar 2, :baz 3}
          {:foo 1, :baz 3}
          true))

  (t/is (r.match/match '({:foo 1, :bar 2, :baz 3}
                         {:foo 1, :bar 2, :baz 3}
                         {:foo 1, :bar 2, :baz 3})
          ({:foo 1, :bar 2}
           {:bar 2, :baz 3}
           {:foo 1, :baz 3})
          true))

  (t/is (r.match/match [{:foo 1, :bar 2, :baz 3}
                        {:foo 1, :bar 2, :baz 3}
                        {:foo 1, :bar 2, :baz 3}]
          [{:foo 1, :bar 2}
           {:bar 2, :baz 3}
           {:foo 1, :baz 3}]
          true)))

(t/deftest ground-set-pattern-expresses-subset-match
  (t/is (r.match/match #{1 2 3}
          #{1}
          true))

  (t/is (r.match/match #{1 2 3}
          #{2}
          true))

  (t/is (r.match/match #{1 2 3}
          #{3}
          true))

  (t/is (r.match/match #{1 2 3}
          #{1 2}
          true))

  (t/is (r.match/match #{1 2 3}
          #{2 3}
          true))

  (t/is (r.match/match #{1 2 3}
          #{1 3}
          true))

  (t/is (r.match/match '(#{1 2 3}
                         #{1 2 3}
                         #{1 2 3})
          (#{1 2}
           #{2 3}
           #{1 3})
          true))

  (t/is (r.match/match [#{1 2 3}
                        #{1 2 3}
                        #{1 2 3}]
          [#{1 2}
           #{2 3}
           #{1 3}]
          true))

  (t/is (r.match/match #{[{:foo "bar", :baz "foo"}]
                         [{:foo "baz", :baz "quux"}]}
          #{[{:foo "bar"}]
            [{:baz "quux"}]}
          true))

  (t/is (r.match/match #{[{:foo "bar", :baz "foo"}]
                         [{:foo "baz", :baz "quux"}]}
          #{[{:foo "baz"}]
            [{:baz "foo"}]}
          true)))

(tc.t/defspec find-results-are-elements-of-search-results
  (tc.prop/for-all [v (tc.gen/vector tc.gen/nat 3 5)]
    (contains? (set (r.match/search v
                      [!xs ..1 !ys ...]
                      {'!xs !xs, '!ys !ys}))
               (r.match/find v
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
             (set (r.match/search data
                    [_ ...
                     {:name !names
                      :owners [_ ... ?owner . _ ...]}
                     .
                     (r.match/or {:name !names
                                  :owners [_ ... ?owner . _ ...]}
                                 _)
                     ...]
                    {:owner ?owner
                     :names !names}))))
    (t/is (contains? expected-results
                     (r.match/find data
                       [_ ...
                        {:name !names
                         :owners [_ ... ?owner . _ ...]}
                        .
                        (r.match/or {:name !names
                                     :owners [_ ... ?owner . _ ...]}
                                    _)
                        ...]
                       {:owner ?owner
                        :names !names})))))

(t/deftest find-separated-items
  (t/is (= [:a :v '[[:any _] [:lvr ?a] [:lit "Bill"]]]
           (r.match/find '([:a [[:any _] [:lvr ?a] [:lit "Bill"]]]
                           [:e [[:lvr ?e] [:any _] [:lit "Alice"]]]
                           [:v [[:any _] [:lvr ?a] [:lit "Bill"]]])
             (_ ... [?i1 ?tuple] . _ ... [?i2 ?tuple] . _ ...)
             [?i1 ?i2 ?tuple]))))

(t/deftest set-negation
  (t/is (= '(1 3)
           (r.match/search [#{1 2 3} #{[2 2]}]
             [#{?x} (r.match/not #{[?x ?x]})]
             ?x)))

  (let [x (r.match/find [#{1 2 3} #{[2 2]}]
            [#{?x} (r.match/not #{[?x ?x]})]
            ?x)]
    (t/is (or (= x 1)
              (= x 3)))))

(t/deftest unbound-mvrs-in-dsj
  (t/is (= [[0 2 4 6 8] [1 3 5 7 9]]
           (r.match/match (range 10)
             ((r.match/or (r.match/pred even? !evens)
                          (r.match/pred odd? !odds)) ...)
             [!evens !odds])))

  (t/is (= [[2 1 3] []]
           (r.match/match [2 1 3]
             [(r.match/or (r.match/pred even? !xs)
                          (r.match/pred odd? !ys))
              !xs
              !xs]
             [!xs !ys])))

  (t/is (= [[2 4 6 8] [1 3 5 7 9]]
           (r.match/match [1 2 3 4 5 6 7 8 9]
             [(r.match/or (r.match/pred even? !xs) (r.match/pred odd? !ys))
              ...]
             [!xs !ys]))))


(t/deftest dsj-literal-test
  (t/is (r.match/match false
          (r.match/or true false)
          true))
  (t/is (r.match/match nil
          (r.match/or true nil false)
          true)))

(t/deftest seq-pattern-matching-with-infinite-sequences
  (t/is (r.match/match (iterate inc 1)
          (_1)
          false

          _n
          true))

  (t/is (r.match/match (iterate inc 1)
          (_1 _2)
          false

          _n
          true)))

(t/deftest not-not-test
  (let [x 1]
    (t/is (= x (r.match/find 1
                 (r.match/not (r.match/not ?x))
                 ?x)))))

;; ---------------------------------------------------------------------
;; JS Array match tests

#?(:cljs
   (tc.t/defspec jsa-unquote-patterns-match
     (tc.prop/for-all [x gen-scalar
                       y gen-scalar]
       (r.match/match #js [x #js [y x] y]
         #js [~x #js [~y ~x] ~y]
         true

         _
         false))))


#?(:cljs
   (tc.t/defspec jsa-lvrs-bind-the-values-they-match
     (tc.prop/for-all [x gen-scalar
                       y gen-scalar]
       (r.match/match #js [x #js [y x] y]
         #js [?x #js [?y ?x] ?y]
         (and (= ?x x)
              (= ?y y))

         _
         false))))


#?(:cljs
   (tc.t/defspec jsa-mvrs-collect-the-values-they-match
     (tc.prop/for-all [x gen-scalar
                       y gen-scalar]
       (r.match/match #js [x #js [y x] y]
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
       (r.match/match (into-array (conj (into [] (mapcat identity) (repeat n [x1 x2])) y1 y2))
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
       (r.match/match (into-array (into [x1 x2] (mapcat identity) (repeat n [y1 y2])))
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
       (r.match/match (into-array (conj (into [] (mapcat identity) (repeat n [x1 x2])) y1 y2))
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
       (r.match/match (into-array (into [x1 x2] (mapcat identity) (repeat n [y1 y2])))
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
       (r.match/match (into-array (conj (into [] (mapcat identity) (repeat n [x1 x2])) y1 y2))
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
       (r.match/match (list* `let (into-array (into [] (mapcat identity) (repeat n [b v])))
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
       (r.match/match (into-array (conj (into [] (map identity) (repeat n x)) x x))
         #js [_ ... ~x ~x]
         true))))


#?(:cljs
   (tc.t/defspec jsa-drop-in-tail
     (tc.prop/for-all [n tc.gen/nat
                       x gen-scalar]
       (r.match/match (into-array (conj (into [] (map identity) (repeat n x)) x x))
         #js [~x ~x . _ ...]
         true))))


#?(:cljs
   (tc.t/defspec jsa-?x-guard-true-?x-succeeds
     (tc.prop/for-all [x gen-scalar
                       y gen-scalar]
       (r.match/match #js [x y x]
         #js [?x (r.match/guard (= ?x ?x)) ?x]
         true

         _
         false))))


#?(:cljs
   (t/deftest jsa-compile-inside-seq
     (t/is (= 1 (r.match/match '(let #js [] 1)
                  ('let #js [] ?x)
                  ?x)))))

;; ---------------------------------------------------------------------
;; JS Object match tests

#?(:cljs
   (tc.t/defspec jso-unq-succeeds
     (tc.prop/for-all [x gen-scalar]
       (r.match/match #js {:x x}
         #js {:x ~x}
         true

         _
         false))))

#?(:cljs
   (tc.t/defspec jso-lvr-succeeds
     (tc.prop/for-all [x gen-scalar
                       y gen-scalar]
       (r.match/match #js {:x x, :y y}
         #js {:x ?x, :y ?y}
         (= [x y] [?x ?y])

         _
         false))))


#?(:cljs
   (tc.t/defspec jso-vec-lvr
     (tc.prop/for-all [x gen-scalar]
       (let [y [x]
             z [[x]]]
         (r.match/match #js {:x [x y], :y [y x], :z [z]}
           #js {:x [?x ?y] :y [?y ?x] :z [?z]}
           (= [x y z] [?x ?y ?z])

           _
           false)))))

#?(:cljs
   (tc.t/defspec jso-seq-lvr
     (tc.prop/for-all [x gen-scalar]
       (let [y [x]
             z [[x]]]
         (r.match/match #js {:x (list x y), :y (list y x), :z (list z)}
           #js {:x (?x ?y) :y (?y ?x) :z (?z)}
           (= (list x y z) (list ?x ?y ?z))

           _
           false)))))

#?(:cljs
   (tc.t/defspec jso-mvr
     (tc.prop/for-all [x gen-scalar
                       y gen-scalar]
       (r.match/match #js {:x x, :y y}
         #js {:x !xs, :y !xs}
         (or (= [x y] !xs)
             (= [y x] !xs))

         _
         false))))


#?(:cljs
   (tc.t/defspec jso-jso
     (tc.prop/for-all [x gen-scalar]
       (r.match/match #js {:x #js {:y x}, :z x}
         #js {:x #js {:y ~x}, :z ~x}
         true

         _
         false))))

;; ---------------------------------------------------------------------
;; with tests

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
                 [:li "Chicken"]]]]
    (t/is (= (r.match/find hiccup
               (with [%h1 [!tags {:as !attrs} . %hiccup ...]
                      %h2 [!tags . %hiccup ...]
                      %h3 !xs
                      %hiccup (r.match/or %h1 %h2 %h3)]
                 %hiccup)
               [!tags !attrs !xs])
             [[:div :p :strong :em :u :ul :li :li :li :li]
              [{"foo" "bar"} {"baz" "quux"}]
              ["Foo" "Bar" "Baz" "Beef" "Lamb" "Pork" "Chicken"]])))

  (t/is (= (set
            (r.match/search '[#{{:tag :lvr, :symbol ?y}
                                {:tag :lvr, :symbol ?x}
                                {:tag :lvr, :symbol ?z}}
                              #{{:tag :lvr, :symbol ?x}}]
              (with [%lvr {:tag :lvr, :symbol ?symbol}]
                [#{%lvr} #{(r.match/not %lvr)}])
              ?symbol))
           '#{?y ?z}))


  (t/testing "recursive key/val collection"
    (t/is (= [#{:foo :baz} #{"bar" "quux"}]
             (r.match/find {:foo "bar", :baz "quux"}
               (with [%kvs {!k !v & (r.match/or %kvs {})}]
                 %kvs)
               [(set !k) (set !v)]))))

  (t/testing "recursive element collection"
    (t/is (= #{"bar" :baz :foo "quux"}
             (r.match/find #{:foo "bar", :baz "quux"}
               (with [%elems #{!x ^& (r.match/or %elems #{})}]
                 %elems)
               (set !x)))))

  (t/testing "recursive grammar"
    (t/is (= #{[1 1 1 1] [1 1 1] [1 1] [1] []}
             (set (r.match/search {:a 1
                                   :b {:a 1
                                       :b 2}
                                   :c '(1 2 1 2)}
                    (with [%1_ (r.match/or %1 _)
                           %1 (r.match/or (r.match/and 1 !1s)
                                          {_ %1_ & %1_}
                                          (%1_ ...)
                                          [%1_ ...]
                                          #{%1_})]
                      %1)
                    !1s))))))


(t/deftest gh-33
  (t/is (= 1 (r.match/find [1 2]
               [?a ?b]
               ?a

               (?a ?b)
               ?a)))

  (t/is (= 1 (r.match/find '(1 2)
               [?a ?b]
               ?a

               (?a ?b)
               ?a))))

(t/deftest gh-41
  (let [fail (gensym)]
    (t/is (identical? fail
                      (r.match/match [1 2 3 4 5 6 7]
                        [!as !bs ...]
                        {:as !as, :bs !bs}
                        _ fail)))))

(t/deftest gh-44
  (let [f (fn [z]
            (r.match/match z
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
           (r.match/find '[1 2 3 4 5 6]
             [!xs ..?n !ys ..?n]
             [!xs !ys])
           (r.match/find '(1 2 3 4 5 6)
             (!xs ..?n !ys ..?n)
             [!xs !ys])))

  (t/is (= 1
           (r.match/match [1 2 3 4]
             [_ _ _ _ ..?n]
             ?n)

           (r.match/match '(1 2 3 4)
             (_ _ _ _ ..?n)
             ?n)))

  (t/is (= 2
           (r.match/match [1 2 3 4]
             [_ _ ..?n]
             ?n)
           (r.match/match '(1 2 3 4)
             (_ _ ..?n)
             ?n)))

  (t/is (= :fail
           (r.match/match [1 2 3 4]
             [_ _ _ ..?n]
             ?n

             _
             :fail)

           (r.match/match '(1 2 3 4)
             (_ _ _ ..?n)
             ?n

             _
             :fail)))

  (t/is (= 4
           (r.match/match [1 2 3 4]
             [_ ..?n]
             ?n)

           (r.match/match '(1 2 3 4)
             (_ ..?n)
             ?n))))

(t/deftest memory-length-subsequence-test
  (t/is (= [[1 2 3 4 5 6] [4 2]]
           (r.match/find '[[1 2 3 4] [5 6]]
             [[!xs ..!ns] [!xs ..!ns]]
             [!xs !ns])
           (r.match/find '((1 2 3 4) (5 6))
             ((!xs ..!ns) (!xs ..!ns))
             [!xs !ns])))

  (t/is (= [[1 3] [2 4] [2]]
           (r.match/match [1 2 3 4]
             [!as !bs ..!ns]
             [!as !bs !ns])
           (r.match/match [[1 2] [3 4]]
             [[!as !bs] ..!ns]
             [!as !bs !ns]))))


(t/deftest &-patterns-test
  (t/is (= '#{(2 3) (3) ()}
           (set (r.match/search '((a 2 3) (b 2 3))
                  ((_ ... & ?rest) (_ ... & ?rest))
                  ?rest))
           (set (r.match/search '([a 2 3] [b 2 3])
                  ([_ ... & ?rest] [_ ... & ?rest])
                  ?rest))))

  (t/is (= '#{[2 (3)] [3 ()]}
           (set (r.match/search '((a 2 3) (b 2 3))
                  ((_ ... & (?x & ?rest)) (_ ... & (?x & ?rest)))
                  [?x ?rest]))
           (set (r.match/search '([a 2 3] [b 2 3])
                  ([_ ... & [?x & ?rest]] [_ ... & [?x & ?rest]])
                  [?x ?rest])))))
