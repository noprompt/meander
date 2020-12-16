(ns meander.interpreter.epsilon-test
  (:require [clojure.test :as t]
            [meander.interpreter.epsilon :as mi]
            [meander.util.epsilon :as m.util])
  #?(:cljs
     (:require-macros [meander.interpreter.epsilon-test :refer [pattern]])))

;; Helpers/Hacks
;; ---------------------------------------------------------------------

;; HACK: `*ns*` is user when running tests from the command
;; line. This is a problem for tests which need to resolve symbols
;; e.g. any test which uses an operator. The two macros below "fix"
;; this problem by ensuring that we are in *this* namespace for
;; Clojure, and by ensuring that a pattern form is parsed with respect
;; to *this* namespace in ClojureScript. The latter solution does this
;; by attaching meta to the form which, internally, is merged into the
;; parse environment.

#?(:clj
   (defmacro in-this-ns [& body]
     (if (m.util/cljs-env? &env)
       `(do ~@body)
       (binding [*ns* (the-ns (symbol (namespace ::_)))]
         ~@body))))

#?(:clj
   (defmacro pattern [form]
     (if (m.util/cljs-env? &env) 
       `(quote ~(with-meta form &env))
       `(quote ~form))))

(defn project
  "Helper which extracts the values of vars (keys) out a map in the
  order they are given.

  Example:

    (let [f (mi/finder '[?x ?y] (project '[?y ?x ?y]))]
      (f [1 2]))
    ;; => [2 1 2]"
  [vars]
  (fn [bindings] (mapv bindings vars)))

;; Tests
;; ---------------------------------------------------------------------


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
  (in-this-ns
    (let [sf (mi/searcher
              (pattern (mi/and ?x 1))
              (project '[?x]))]
      (t/is (= (sf 1) [[1]])))

    (let [ff (mi/finder
              (pattern (mi/and 1 1))
              (constantly true))]
      (t/is (ff 1)))

    (let [ff (mi/finder
              (pattern (mi/and 1 2))
              (constantly false))]
      (t/is (= nil (ff 2))))

    (let [ff (mi/finder
              (pattern (mi/and ?x 1))
              (project ['?x]))]
      (t/is (= (ff 1) [1])))))


(t/deftest or-test
  (in-this-ns
    (let [sf (mi/searcher
              (pattern (mi/or ?x 1))
              (project ['?x]))]
      (t/is (= (sf 1) [[1] [nil]])))

    (let [ff (mi/finder
              (pattern (mi/or 1 2))
              (constantly true))]
      (t/is (ff 2)))

    (let [ff (mi/finder
              (pattern (mi/or 2 1))
              (constantly true))]
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
