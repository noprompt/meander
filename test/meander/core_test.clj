(ns meander.core-test
  (:require [clojure.test :as t]
            [clojure.test.check :as tc]
            [clojure.test.check.clojure-test :as tc.t]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            ;; r is short for "rewrite"
            [meander.core :as r]))


;; ---------------------------------------------------------------------
;; Generators


(def gen-var-name
  (gen/fmap name gen/symbol))


(def gen-smap
  (gen/map gen-var-name gen/any))


(def gen-var
  (gen/fmap r/make-variable gen-var-name))


(def gen-splicing-var
  (gen/fmap r/make-splicing-variable gen-var-name))


(def gen-object
  (gen/one-of [gen/any
               gen-var
               gen-splicing-var]))


;; ---------------------------------------------------------------------
;; unify


(tc.t/defspec unifying-identities-always-succeeds
  (prop/for-all [u gen-object
                 smap gen-smap]
    (t/is (= smap
             (r/unify u u smap))))

  (prop/for-all [x gen/any 
                 u gen-var
                 smap gen-smap]
    (let [smap* (r/extend smap u x)]
      (t/is (= (r/unify u x smap)
               (r/unify u x smap*))))))


(tc.t/defspec unifying-inequalities-always-fails
  (prop/for-all [[u v] (gen/such-that
                        (fn [[u v]]
                          (not= u v))
                        (gen/tuple gen/any gen/any))
                 smap gen-smap]
    (t/is (nil? (r/unify u v smap))))

  (prop/for-all [[x1 x2] (gen/such-that
                          (fn [[u v]]
                            (not= u v))
                          (gen/tuple gen/any gen/any))
                 u gen-var
                 smap gen-smap]
    (let [smap* (r/extend smap u x1)]
      (t/is (nil? (r/unify u x2 smap*))))))


;; ---------------------------------------------------------------------
;; t

(def dot-form-t1
  ;; (.foo bar baz)
  (r/t (~method ~target ~@args)
    :when (symbol? method)
    :let [method-name (name method)]
    :when (re-find #"\A\.." method-name)
    :let [method* (symbol (subs method-name 1))]
    (. ~target ~method* ~@args)))


(def dot-form-t2
  ;; (. bar (foo baz))
  (r/t (. ~target (~method ~@args))
    :when (symbol? method)
    (. ~target ~method ~@args)))

(t/deftest t-test
  (t/testing "transforms implement IFn"
    (t/is (= '(. bar foo baz)
             (dot-form-t1 '(.foo bar baz)))))

  (t/testing "transforms are unifiable"
    (t/is (= '({"args" (baz)
                "method" .foo
                "target" bar})
             (r/unify* dot-form-t1 '(.foo bar baz) {})))

    (t/is (= '()
             (r/unify* dot-form-t1 '(. bar (foo baz)) {})))))







