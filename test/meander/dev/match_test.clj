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
  (t/is 
   (r.match/match '(let [x 1, y 1]
                     (+ x y))
     (let [!bindings ...] . !body ...)
     (and (= !bindings '[x 1, y 1])
          (= !body '[(+ x y)]))))

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
             ?tail))))

  (let [is (shuffle (range 5))
        js (shuffle (range 5))
        ms (map (fn [i j] {:i i, :j j}) is js)]
    (t/is
     (r.match/match ms
       ({:i !is, :j !js} ...)
       (and (= !is is)
            (= !js js))))))

