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
    (= smap
       (r/unify u u smap)))

  (prop/for-all [x gen/int 
                 u gen-var]
    (= (r/unify u x)
       (r/unify u x (r/unify u x)))))


(tc.t/defspec unifying-inequalities-always-fails
  (prop/for-all [[u v] (gen/such-that
                        (fn [[u v]]
                          (not= u v))
                        (gen/tuple gen/any gen/any))
                 smap gen-smap]
    (nil? (r/unify u v smap)))

  (prop/for-all [[x1 x2] (gen/such-that
                          (fn [[u v]]
                            (not= u v))
                          (gen/tuple gen/any gen/any))
                 u gen-var
                 smap gen-smap]
    (let [smap* (r/extend smap u x1)]
      (nil? (r/unify u x2 smap*)))))


(tc.t/defspec splicing-var-only-unifies-only-with-sequential
  (prop/for-all [u gen-splicing-var
                 x gen/any]
    (if (coll? x)
      (let [smap (r/extend-no-check {} u x)]
        (= smap
           (r/unify u x smap)))
      (not (r/unify u x {})))))


(tc.t/defspec sequential-unification
  (prop/for-all [v gen-var
                 x gen/any
                 y gen/any
                 i gen/nat]
    (let [u (interleave (repeat i v)
                        (repeat y))
          v (interleave (repeat i x)
                        (repeat y))
          smap-a (r/unify u v)
          smap-b (r/unify (vec u) (vec v))]
      (and (some? smap-a)
           (some? smap-b)
           (= smap-a
              smap-b)))))


(tc.t/defspec set-unification-producuces-n!-distinct-solutions-on-success
  ;; :max-elements is capped at 4 to keep the test sane.
  (prop/for-all [u (gen/set gen-var {:min-elements 1
                                     :max-elements 4})
                 v (gen/set gen/any {:min-elements 1
                                     :max-elements 4})]
    (if (= (count u)
           (count v))
      (= (count (distinct (r/unify* u v)))
         (reduce * (map inc (range (count u)))))
      (not (r/unify* u v)))))


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
             (r/unify* dot-form-t1 '(. bar (foo baz)) {}))))

  (t/testing "example transforms"
    (t/is (= '(g 1 2)
             ((r/t (~f (~f ~x ~y))
                :when (= f 'g)
                (~f ~x ~y))
              '(g (g 1 2)))))

    (t/is (= '(g 1 2)
             ((r/t (g (g ~x ~y))
                (g ~x ~y))
              '(g (g 1 2)))))

    (let [thread-1a
          (r/t (-> ~x ~f)
            (~f ~x))

          thread-1b
          (r/t (-> ~x ~f ~@args)
            (-> (~f ~x) ~@args))

          thread-1c
          (r/t (-> ~x)
            ~x)

          thread-1
          (r/repeat
           (r/choice thread-1a
                     thread-1b
                     thread-1c))]

      (t/is (= '(f x)
               (thread-1a '(-> x f))))

      (t/is (= '(-> (f x))
               (thread-1b '(-> x f))))

      (t/is (= '(f x)
               (thread-1c '(-> (f x)))))

      (t/is (= '(f (g (h x)))
               (thread-1 '(-> x h g f)))))))

(let [p (r/parse-form '(1 2 ~@xs))
      m (r/pattern (1 2 ~@xs))
      x (list 1 2 3 4 5 6 7 8 9)]
  (t/is (= (r/unify* p x)
           (r/unify* m x))))

(let [p (r/parse-form '[1 2 ~@xs])
      m (r/pattern [1 2 ~@xs])
      x [1 2 3 4 5 6 7 8 9]]
  (t/is (= (r/unify* p x)
           (r/unify* m x))))

(let [p (r/parse-form '(2 ~@xs 3))
      m (r/pattern (2 ~@xs 3) )]
  (t/is (= '({"xs" (4)})
           (r/unify* p (list 2 4 3))
           (r/unify* m (list 2 4 3)))))
