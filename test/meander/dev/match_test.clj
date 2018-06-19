(ns meander.dev.match-test
  (:require [clojure.test :as t]
            [clojure.test.check :as tc]
            [clojure.test.check.clojure-test :as tc.t]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as s.gen]
            [meander.dev.syntax :as r.syntax]
            [meander.dev.match :as r.match]))

(defn gen-parse-tree [form-gen]
  (s.gen/fmap r.syntax/parse form-gen))


(defn gen-row [form-gen]
  (s.gen/fmap
   (fn [[col rhs]]
     {:cols [col]
      :rhs rhs})
   (s.gen/tuple
    (gen-parse-tree form-gen)
    (s.gen/keyword))))


(defn gen-rows [form-gen]
  (s.gen/such-that not-empty (s.gen/vector (gen-row form-gen))))


(t/deftest match-test
  (let [is (shuffle (range 5))
        js (shuffle (range 5))
        ms (map (fn [i j] {:i i, :j j}) is js)]
    (t/is
     (r.match/match ms
       ({:i !is, :j !js} ...)
       (and (= !is is)
            (= !js js))

       _
       false)))
  
  (t/is
   (r.match/match (range -5 6)
     (!xs ... . 3 4 5)
     (= !xs (range -5 3))

     _
     false))

  (t/is
   (r.match/match '(1 2 1 2 4)
     (?x ?y ... . 4)
     true
     _
     false))

  (t/is
   (not
    (r.match/match '(1 2 1 3 4)
      (?x ?y ... . 4)
      true

      _
      false)))

  (t/is
   (r.match/match [1 2 1 2 4]
     [?x ?y ... . 4]
     true
     _
     false))
  
  (t/is
   (not
    (r.match/match [1 2 1 3 4]
      [?x ?y ... . 4]
      true

      _
      false)))

  (t/testing "and patterns"
    (t/is
     (r.match/match [1 2 3]
       (and ?x [_ ...])
       (= ?x [1 2 3])))

    (let [xs [[1 2 3] [1 2 3] [1 2 3]]]
      (t/is
       (r.match/match xs
         [?x ?y ?x]
         (= ?x ?y [1 2 3])

         _
         false))

      (t/is
       (r.match/match xs
         [?x (and ?x ?y) ?y]
         (= ?x ?y [1 2 3])

         _
         false))

      (t/is
       (r.match/match xs
         [?x ?y (and ?x ?y)]
         (= ?x ?y [1 2 3])

         _
         false))))

  (t/testing "init patterns"
    (t/is
     (r.match/match '(1 2 3 4 5 6)
       (_ ... . 4 5 6)
       true

       _
       false))

    (t/is
     (not
      (r.match/match '(1 2 3 4 5 6)
        (_ ... . 4 5)
        true

        _
        false)))

    (t/is
     (r.match/match [1 2 3 4 5 6]
       [_ ... . 4 5 6]
       true

       _
       false))

    (t/is
     (not
      (r.match/match [1 2 3 4 5 6]
        [_ ... . 4 5]
        true

        _
        false))))

  (t/testing "predicate patterns"
    (t/is
     (r.match/match [1 2 3]
       (? vector?)
       true

       _
       false))

    (t/is
     (r.match/match "foo"
       (? (fn [x] (re-matches #"[a-z]+" x)))
       true

       _
       false)))

  (t/testing "map patterns"
    (let [node {:tag :h1
                :attrs {:style "font-weight:normal"}
                :children []}]
      (t/is
       (r.match/match node
         {:tag ?tag, :attrs ?attrs, :children ?children}
         (and (= (get node :tag)
                 ?tag)
              (= (get node :attrs)
                 ?attrs)
              (= (get node :children)
                 ?children))

         _
         false))

      ;; TODO: The most specific match should be selected. 
      (t/is
       (not
        (r.match/match node
          {:tag ?tag, :attrs ?attrs, :children ?children}
          (and (= (get node :tag)
                  ?tag)
               (= (get node :attrs)
                  ?attrs)
               (= (get node :children)
                  ?children))

          {:tag ?tag, :children ?children}
          false

          {:tag ?tag, :attrs ?attrs}
          false

          {:attrs ?attrs, :children ?children}
          false

          {:attrs ?attrs}
          false

          {:children ?children}
          false)))))

  (t/testing "drop patterns"
    (t/is
     (r.match/match '(1 2 3 4 5 6)
       (1 2 3 . _ ...)
       true

       _
       false))

    (t/is
     (r.match/match [1 2 3 4 5 6]
       [1 2 3 . _ ...]
       true

       _
       false))

    (t/is
     (not
      (r.match/match '(1 2 3 4 5 6)
        (1 2 3 4 5 6 7 . _ ...)
        true

        _
        false)))

    (t/is
     (not
      (r.match/match [1 2 3 4 5 6]
        [1 2 3 4 5 6 7 . _ ...]
        true

        _
        false))))


  (let [form '(let (a 1 b 2)
                (+ a b)
                (+ b a))]
    (t/is
     (r.match/match form
       (let . !forms ...)
       (= !forms '[(a 1 b 2) (+ a b) (+ b a)])

       _
       false))

    (t/is
     (r.match/match form
       (let ?bindings . !forms ...)
       (and (= ?bindings '(a 1 b 2))
            (= !forms '[(+ a b) (+ b a)]))

       _
       false))

    (t/is
     (r.match/match form
       (let (!bindings ...) . !forms ...)
       (and (= !bindings '(a 1 b 2))
            (= !forms '[(+ a b) (+ b a)]))

       _
       false))

    (t/is
     (r.match/match form
       (let (!syms !vals ...) . !forms ...)
       (and (= !syms '(a b))
            (= !vals '(1 2))
            (= !forms '[(+ a b) (+ b a)]))

       _
       false))

    (t/is
     (r.match/match form
       (let ((!syms !vals :as !pairs) ...) . !forms ...)
       (and (= !syms '(a b))
            (= !vals '(1 2))
            (= !forms '[(+ a b) (+ b a)]))

       _
       false)))

  (t/is 
   (r.match/match '(let [x 1, y 1]
                     (+ x y))
     (let [!bindings ...] . !body ...)
     (and (= !bindings '[x 1, y 1])
          (= !body '[(+ x y)]))

     _
     false))

  (t/testing "cap patterns"
    (t/is
     (let [n (rand)]
       (r.match/match [n n]
         [(_ :as ?x) (_ :as ?x)]
         (= n ?x))))

    (t/is
     (let [n (rand)]
       (r.match/match [n n]
         [(_ :as !x) (_ :as !x)]
         (= [n n] !x))))

    (t/is
     (let [n 1]
       (r.match/match [n n]
         [(_ _ :as !x)]
         (= [[n n]] !x))))

    (t/is
     (r.match/match '[x 0 y 1 1 2 3]
       [(!bindings !values :as !binding-pairs) ...
        . (1 2 3 :as ?tail)]
       (and (= !bindings
               '[x y])
            (= !values
               [0 1])
            (= '[[x 0] [y 1]]
               !binding-pairs)
            (= [1 2 3]
               ?tail))
       _
       false))

    (t/is
     (r.match/match '(let [a 1 b 2]
                       (+ a b)
                       (+ b a))
       (let [(!bindings !values :as !binding-pairs) ...] . !body ...)
       (and (= '[a b]
               !bindings)
            (= '[1 2]
               !values)
            (= '[(a 1) (b 2)]
               !binding-pairs)
            (= '[(+ a b) (+ b a)]
               !body))))

    
    (t/is
     (not
      (r.match/match (list 1 2 1 2 1 3 1 4)
        ;; This is a "search" because it has variable length on both
        ;; sides. It should fail to match.
        (?x ?y ... . !zs ...)
        (and (= ?x 1)
             (= ?y 2)
             (= !zs [1 3 1 4]))

        _
        false)))

    (t/is
     (r.match/match '(def foo "bar" :baz)
       (def ?sym ?init)
       (and (= ?sym 'foo)
            (= ?init :baz))

       (def ?sym ?doc ?init)
       (and (= ?sym 'foo)
            (= ?doc "bar")
            (= ?init :baz))


       _
       false))))


(t/deftest app-patterns-test
  (t/testing "app patterns"
    (let [n (rand-int 100)]
      (t/is
       (r.match/match n
         (>> (partial repeat 3)
             (?a ?b ?c :as ?ns))
         (and (= ?a ?b ?c n)
              (= ?ns [n n n]))

         _
         false)))

    ;; You can program with app patterns! XD
    (t/is
     (r.match/match :okay
       (>> (constantly 1) ?x
           (>> (constantly 2)) ?y
           (>> (constantly (+ ?x ?y))) ?z)
       (= 6 (+ ?x ?y ?z))

       _
       false))))



(t/deftest not-patterns-test
  (t/testing "not patterns"
    (t/is
     (r.match/match 3
       (not 2)
       true

       _
       false))

    (t/is
     (r.match/match 3
       (not 2 (? even?))
       true

       _
       false))

    (t/is
     (r.match/match (filter odd? (range 10))
       ((not (? even?)) ...)
       true

       _
       false))))

(t/deftest part-test
  (t/is
   (r.match/match '(do :foo :bar :baz)
     (do . !statements ... . ?ret)
     (and (= !statements [:foo :bar])
          (= ?ret :baz))

     _
     false))

  (t/is
   (r.match/match '(cond :foo :bar :baz :quux)
     (cond . ((!xs !ys :as !pairs) ... :as !all-pairs))
     (and (= !xs [:foo :baz])
          (= !ys [:bar :quux])
          (= !pairs [[:foo :bar], [:baz :quux]])
          ;; BUG: This should be [:foo :bar :baz :quux].
          (= [[]] !all-pairs)) 
     _
     false))

  (t/is
   (r.match/match [:A :B [1 2 3] :C :D]
     [:A :B . !xs ... . ?x ?y]
     (and (= [[1 2 3]]
             !xs)
          (= :C ?x)
          (= :D ?y))

     _
     false))

  (t/is
   (r.match/match (list :A :B [1 2 3] :C :D)
     (:A :B . !xs ... . ?x ?y)
     (and (= [[1 2 3]]
             !xs)
          (= :C ?x)
          (= :D ?y))

     _
     false)))


(t/deftest map-pattern-test
  (t/is
   (r.match/match {:c 'c :e 'e :k [:halt]}
     {:k [:halt]}
     true

     _
     false))

  (t/is
   (false?
    (r.match/match {:c 'c :e 'e :k [:halt]}
      {:k [:okay]}
      true

      _
      false))))


(tc.t/defspec empty-or-pattern-always-fails
  (prop/for-all [x gen/any]
    (not
     (r.match/match x
       (or)
       true

       _
       false))))


(tc.t/defspec or-pattern-containing-any-always-succeeds
  (prop/for-all [x gen/any]
    (r.match/match x
      (or (not ~x) _ (not _))
      true)))


(t/deftest or-pattern-test
  (t/is
   (r.match/match 2
     (and (? number?)
          (or (? odd?) (? even?)))
     true

     _
     false)))
