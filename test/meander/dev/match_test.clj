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
          (= !body '[(+ x y)])))))
