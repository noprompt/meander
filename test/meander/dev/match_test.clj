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


(t/deftest cap-test
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
     [(!bindings !values :as !binding-pairs) ... . (1 2 3 :as ?tail)]
     (and (= !bindings
             '[x y])
          (= !values
             [0 1])
          (= '[[x 0] [y 1]]
             !binding-pairs)
          (= [1 2 3]
             ?tail)))))


(t/is
 (r.match/match '(let [a 1 b 2] (+ a b) (+ b a))
   (let [(!bindings !values :as !binding-pairs) ...] . !body ...)
   (and (= '[a b]
           !bindings)
        (= '[1 2]
           !values)
        (= '[(a 1) (b 2)]
           !binding-pairs)
        (= '[(+ a b) (+ b a)]
           !body))))


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
 (r.match/match '(let [x 1, y 1]
                   (+ x y))
   (let [!bindings ...] . !body ...)
   (and (= !bindings '[x 1, y 1])
        (= !body '[(+ x y)]))))

(t/deftest and-test
  (t/is
   (r.match/match [1 2 3]
     (and ?x [_ ...])
     (= ?x [1 2 3])))

  (let [xs [[1 2 3] [1 2 3] [1 2 3]]]
    (t/is
     (r.match/match xs
       [(and ?x ?y) ?x ?y]
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


(t/deftest init-pattern
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
        false)))))

(t/deftest drop-pattern-test
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
        false)))))

(t/deftest predicate-patterns
  (t/is
   (r.match/match [1 2 3]
     (? vector?)
     true

     _
     false))

  (t/is
   (r.match/match "foo"
     (? (fn [x]
          (re-matches #"[a-z]+" x)))
     true

     _
     false)))


(t/deftest map-patterns
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

    ;; The most specific match should be selected. 
    (t/is
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
       false))))
