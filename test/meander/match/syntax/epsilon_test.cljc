(ns meander.match.syntax.epsilon-test
  (:require [clojure.spec.alpha :as s :include-macros true]
            [clojure.test :as t]
            [clojure.test.check.clojure-test :as tc.t :include-macros true]
            [clojure.test.check.generators :as tc.gen :include-macros true]
            [clojure.test.check.properties :as tc.prop :include-macros true]
            [meander.match.epsilon :as r.match :include-macros true]
            [meander.match.syntax.epsilon :as r.match.syntax :include-macros true]
            [meander.syntax.epsilon :as r.syntax :include-macros true]))

;; ---------------------------------------------------------------------
;; Generators

(defn gen-parse
  [gen]
  (tc.gen/fmap (fn [x]
                 (r.match.syntax/parse x {})) gen))

(defn gen-or-form
  [gen]
  (tc.gen/fmap
   (fn [l] (cons 'r/or l))
   (tc.gen/list gen)))

(defn gen-or-tree
  [gen]
  (gen-or-form (tc.gen/recursive-gen gen-or-form gen)))

(defn gen-and-form
  [gen]
  (tc.gen/fmap
   (fn [l] (cons 'r/and l))
   (tc.gen/list gen)))

(defn gen-and-tree
  [gen]
  (gen-and-form (tc.gen/recursive-gen gen-and-form gen)))

;; ---------------------------------------------------------------------
;; AST rewriting tests

#_ ;; TODO: Come back to these.
#?(:clj
   ;; If this let appears inside the `deftest` form the test fails for
   ;; unclear reasons.
   (let [a (r.match.syntax/expand-ast (r.match.syntax/parse '{:foo :bar :as ?baz} {}))
         b (dissoc (r.match.syntax/parse '(r/and {:foo :bar} ?baz) {})
                   ::r.syntax/original-form)]
     (t/deftest map-expand-as
       (t/is (= a b))))

   :cljs
   (t/deftest map-expand-as
     (t/is (= (r.match.syntax/expand-ast (r.match.syntax/parse '{:foo :bar :as ?baz} {}))
              (dissoc (r.match.syntax/parse '(r/and {:foo :bar} ?baz))
                      ::r.syntax/original-form)))))
