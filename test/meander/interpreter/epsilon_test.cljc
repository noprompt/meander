(ns meander.interpreter.epsilon-test
  (:require [clojure.test :as t]
            [meander.interpreter.epsilon :as mi]
            [meander.util.epsilon :as m.util])
  #?(:cljs
     (:require-macros [meander.interpreter.epsilon-test :refer [with-the-right-namespace]])))

#?(:clj
   ;; A breadth and depth of sadness captured in macro form.
   (defmacro with-the-right-namespace [& body]
     (if (m.util/cljs-env? &env) 
       `(comment ~@body)
       `(binding [*ns* (the-ns 'meander.interpreter.epsilon-test)]
          ~@body))))

(defn project [vars]
  (fn [bindings]
    (mapv bindings vars)))

(t/deftest quote-pattern-test
  (t/is ((mi/finder ''?x (constantly true)) '?x))
  (t/is ((mi/finder ''!x  (constantly true)) '!x))
  (t/is ((mi/finder ''(a . b . c .. d ..2) (constantly true)) '(a . b . c .. d ..2)))
  (t/is ((mi/finder ''[a . b . c .. d ..2] (constantly true)) '[a . b . c .. d ..2])))

#?(:clj
   (t/deftest unquote-pattern-test
     (t/is ((mi/finder '~(+ 5 5) (constantly true)) 10))))

(t/deftest anything-pattern-test
  (t/is ((mi/finder '_ (constantly true)) 10)))

(t/deftest logic-variable-test
  (t/is (= ((mi/finder '?x (project ['?x])) 10)
           [10])))

(t/deftest memory-variable-test
  (= ((mi/finder '!x (project ['!x])) 10)
     [[10]]))


(t/deftest and-test
  (with-the-right-namespace
    (let [sf (mi/searcher '(mi/and ?x 1) (project '[?x]))]
      (t/is (= (sf 1) [[1]])))

    (let [ff (mi/finder '(mi/and 1 1) (constantly true))]
      (t/is (ff 1)))

    (let [ff (mi/finder '(mi/and 1 2) (constantly false))]
      (t/is (= nil (ff 2))))

    (let [ff (mi/finder '(mi/and ?x 1) (project ['?x]))]
      (t/is (= (ff 1) [1])))))


(t/deftest or-test
  (with-the-right-namespace
    (let [sf (mi/searcher '(mi/or ?x 1) (project ['?x]))]
      (t/is (= (sf 1) [[1] [nil]])))

    (let [ff (mi/finder '(mi/or 1 2) (constantly true))]
      (t/is (ff 2)))

    (let [ff (mi/finder '(mi/or 2 1) (constantly true))]
      (t/is (ff 1)))))

(t/deftest vec-test
  (let [ff (mi/finder '[?x] (project '[?x]))]
    (t/is (= (ff [1]) [1])))

  (let [ff (mi/finder '[?x] (project '[?x]))]
    (t/is (= (ff [1]) [1])))

  (let [ff (mi/finder '[?x ?y] (project '[?y ?x]))]
    (t/is (= (ff [1 2]) [2 1])))

  (let [ff (mi/finder '[?x & ?y] (project '[?y ?x]))]
    (t/is (= (ff [1 2]) [[2] 1]))))

(t/deftest seq-test
  (let [ff (mi/finder '(?x) (project '[?x]))]
    (t/is (= (ff '(1)) [1])))

  (let [ff (mi/finder '(?x) (project '[?x]))]
    (t/is (= (ff '(1)) [1])))

  (let [ff (mi/finder '(?x ?y) (project '[?y ?x]))]
    (t/is (= (ff '(1 2)) [2 1])))

  (let [ff (mi/finder '(?x & ?y) (project '[?y ?x]))]
    (t/is (= (ff '(1 2)) [[2] 1]))))

(t/deftest map-test
  (t/is (= ((mi/finder '{?k ?v, ?v ?k} (project '[?k ?v]))
            {:a 1 :b 2 1 :a})
           [:a 1]))

  (t/is (= ((mi/searcher '{?k ?v, ?v ?k} (project '[?k ?v]))
            {:a 1 :b 2 1 :a})
           [[:a 1] [1 :a]])))

(t/deftest set-test
  (let [ff (mi/finder '#{?x ?y ?z} (project '[?x ?y ?z]))]
    (t/is (= (ff #{1 2 3})
             [1 3 2])))

  (let [sf (mi/searcher '#{?x ?y ?z} (project '[?x ?y ?z]))]
    (t/is (= (sf #{1 2 3})
             [[1 3 2] [1 2 3] [3 1 2] [3 2 1] [2 1 3] [2 3 1]]))))
