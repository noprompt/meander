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


(t/deftest unify-test
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

  (t/is (= {"z" 3, "xs" [1], "x" 1, "zs" [3], "ys" [3], "y" 2}
           (r/unify (r/pattern [~x [~y [~z ~@zs] ~@ys] ~@xs])
                    [1 [2 [3 3] 3] 1])))

  (t/is (= {"city" "CITY"
            "last" "LAST"
            "state" "STATE"
            "first" "FIRST"}
           (r/unify
            (r/pattern
              {:user {:first ~first
                      :last ~last
                      :address {:city ~city
                                :state ~state}}})
            {:user {:first "FIRST"
                    :last "LAST"
                    :address {:city "CITY"
                              :state "STATE"}}})))


  (t/is (= #{{"z" 6, "y" 5}
             {"x" 4, "y" 5}
             {"z" 6, "x" 4}}
           (set (r/unify* (r/choice (r/pattern [4 ~y ~z])
                                    (r/pattern [~x 5 ~z])
                                    (r/pattern [~x ~y 6]))
                          [4 5 6]
                          {}))))

  (t/is (= #{{"rest" [2 3], "xs-2" [[1 5 6]], "x" 1, "xs-1" [[2 3 4]]}
             {"rest" [5 6], "xs-2" [], "x" 1, "xs-1" [[2 3 4] [1 2 3]]}}
           (set (r/unify*
                 (r/pattern {:x [~x]
                             :y [~@xs-1
                                 [~x ~@rest]
                                 ~@xs-2]})
                 {:x [1]
                  :y [[2 3 4]
                      [1 2 3]
                      [1 5 6]]})))))

;; ---------------------------------------------------------------------
;; Combinators

(def inc-number
  (r/pipe (r/pred number?) inc))


(def upper-case-string
  (r/pipe (r/pred string?) (memfn ^String toUpperCase)))


(t/deftest choice-test
  (t/is (r/fail? ((r/choice r/*fail*)
                  :foo)))

  (t/is (r/fail? ((r/choice inc-number upper-case-string)
                  :foo)))

  (t/is (r/fail? ((r/choice upper-case-string)
                  :foo)))

  (t/is (r/fail? ((r/choice r/*fail*)
                  :foo)))

  (t/is (r/fail? ((r/choice)
                  :foo)))

  (t/is (r/fail? ((r/choice r/*fail* r/*fail*)
                  :foo)))

  (t/is (= 42
           ((r/choice inc-number upper-case-string)
            41)))

  (t/is (= 42
           ((r/choice inc-number)
            41)))

  (t/is (= 42
           ((r/choice r/*fail* inc-number)
            41)))

  (t/is (= 42
           ((r/choice inc-number r/*fail*)
            41))))


(t/deftest all-implementation-test
  (t/testing "clojure.lang.IPersistentSeq"
    (t/is (= ()
             ((r/all (r/pipe (r/pred number?)
                             inc))
              ())))

    (t/is (r/fail? ((r/all (r/pipe (r/pred number?)
                                   inc))
                    (list "A" "B" "C" "D"))))

    (t/is (= (list 1 2 3 4)
             ((r/all (r/pipe (r/pred number?)
                             inc))
              (list 0 1 2 3)))))


  (t/testing "clojure.lang.IPersistentVector"
    (t/is (= []
             ((r/all (r/pipe (r/pred number?)
                             inc))
              [])))

    (t/is (r/fail? ((r/all (r/pipe (r/pred number?)
                                   inc))
                    ["A" "B" "C" "D"])))

    (t/is (r/fail? ((r/all (r/pipe (r/pred number?)
                                   inc))
                    ["A" 1 "C" 3]))))


  (t/testing "clojure.lang.IPersistentMap"
    (t/is (= {}
             ((r/all (r/pipe (r/pred number?)
                             inc))
              {})))

    (t/is (r/fail? ((r/all (r/pipe (r/pred number?)
                                   inc))
                    {"A" "B" "C" "D"})))


    (t/is (= {1 2 3 4}
             ((r/all (r/pipe (r/pred number?)
                             inc))
              {0 1 2 3}))))

  (t/testing "clojure.lang.IPersistentSet"
    (t/is (= #{}
             ((r/all (r/pipe (r/pred number?)
                             inc))
              #{})))

    (t/is (r/fail? ((r/all (r/pipe (r/pred number?)
                                   inc))
                    #{"A" "B" "C" "D"})))

    (t/is (= #{1 2 3 4}
             ((r/all (r/pipe (r/pred number?)
                             inc))
              #{0 1 2 3})))))

(t/deftest one-implementation-test
  (t/testing "clojure.lang.IPersistentSeq"
    (t/is (r/fail? ((r/one (r/pipe (r/pred number?)
                                   inc))
                    ())))

    (t/is (r/fail? ((r/one (r/pipe (r/pred number?)
                                   inc))
                    (list "A" "B" "C" "D"))))

    (t/is (= (list "A" 2 "C" 3)
             ((r/one (r/pipe (r/pred number?)
                             inc))
              (list "A" 1 "C" 3)))))


  (t/testing "clojure.lang.IPersistentVector"
    (t/is (r/fail? ((r/one (r/pipe (r/pred number?)
                                   inc))
                    [])))

    (t/is (r/fail? ((r/one (r/pipe (r/pred number?)
                                   inc))
                    ["A" "B" "C" "D"])))

    (t/is (= ["A" 2 "C" 3]
             ((r/one (r/pipe (r/pred number?)
                             inc))
              ["A" 1 "C" 3]))))


  (t/testing "clojure.lang.IPersistentMap"
    (t/is (r/fail? ((r/one (r/pipe (r/pred number?)
                                   inc))
                    {})))

    (t/is (r/fail? ((r/one (r/pipe (r/pred number?)
                                   inc))
                    {"A" "B" "C" "D"})))


    (t/is (= {"A" 2 "C" 3}
             ((r/one (r/pipe (r/pred number?)
                             inc))
              (assoc (sorted-map)
                     "A" 1
                     "C" 3)))))

  (t/testing "clojure.lang.IPersistentSet"
    (t/is (r/fail? ((r/one (r/pipe (r/pred number?)
                                   inc))
                    #{})))

    (t/is (r/fail? ((r/one (r/pipe (r/pred number?)
                                   inc))
                    #{"A" "B" "C" "D"})))

    (t/is (= #{2 3}
             ((r/one (r/pipe (r/pred number?)
                             inc))
              (into (sorted-set) #{1 3}))))))


(t/deftest many-implementation-test
  (t/testing "clojure.lang.IPersistentSeq"
    (t/is (r/fail? ((r/many (r/pipe (r/pred number?)
                                    inc))
                    ())))

    (t/is (r/fail? ((r/many (r/pipe (r/pred number?)
                                    inc))
                    (list "A" "B" "C" "D"))))

    (t/is (= (list "A" 2 "C" 4)
             ((r/many (r/pipe (r/pred number?)
                              inc))
              (list "A" 1 "C" 3)))))


  (t/testing "clojure.lang.IPersistentVector"
    (t/is (r/fail? ((r/many (r/pipe (r/pred number?)
                                    inc))
                    [])))

    (t/is (r/fail? ((r/many (r/pipe (r/pred number?)
                                    inc))
                    ["A" "B" "C" "D"])))

    (t/is (= ["A" 2 "C" 4]
             ((r/many (r/pipe (r/pred number?)
                              inc))
              ["A" 1 "C" 3]))))


  (t/testing "clojure.lang.IPersistentMap"
    (t/is (r/fail? ((r/many (r/pipe (r/pred number?)
                                    inc))
                    {})))

    (t/is (r/fail? ((r/many (r/pipe (r/pred number?)
                                    inc))
                    {"A" "B" "C" "D"})))


    (t/is (= {"A" 2 "C" 4}
             ((r/many (r/pipe (r/pred number?)
                              inc))
              {"A" 1 "C" 3}))))

  (t/testing "clojure.lang.IPersistentSet"
    (t/is (r/fail? ((r/many (r/pipe (r/pred number?)
                                    inc))
                    #{})))

    (t/is (r/fail? ((r/many (r/pipe (r/pred number?)
                                    inc))
                    #{"A" "B" "C" "D"})))

    (t/is (= #{"A" 2 "C" 4}
             ((r/many (r/pipe (r/pred number?)
                              inc))
              #{"A" 1 "C" 3})))))


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

    (t/is (nil? (r/unify* dot-form-t1 '(. bar (foo baz)) {}))))

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




(t/deftest mutliple-unifiers?-test
  (t/testing "inductive patterns"
    (t/testing "vectors"
      (t/is (not (r/multiple-unifiers? (r/parse-form '[~@ys ~x]))))
      (t/is (not (r/multiple-unifiers? (r/parse-form '[~x ~@ys]))))
      (t/is (r/multiple-unifiers? (r/parse-form '[~@ys ~x ~@zs])))
      (t/is (r/multiple-unifiers? (r/parse-form '[~x ~@ys ~@zs])))
      (t/is (r/multiple-unifiers? (r/parse-form '[~@ys ~x ~@zs]))))

    (t/testing "seqs"
      (t/is (not (r/multiple-unifiers? (r/parse-form '(~@ys ~x)))))
      (t/is (not (r/multiple-unifiers? (r/parse-form '(~x ~@ys)))))
      (t/is (r/multiple-unifiers? (r/parse-form '(~@ys ~x ~@zs))))
      (t/is (r/multiple-unifiers? (r/parse-form '(~x ~@ys ~@zs))))
      (t/is (r/multiple-unifiers? (r/parse-form '(~@ys ~x ~@zs))))))

  (t/testing "non-inductive patterns"
    (t/testing "maps"
      (t/is (not (r/multiple-unifiers? (r/parse-form '{x foo}))))
      (t/is (not (r/multiple-unifiers? (r/parse-form '{}))))
      (t/is (r/multiple-unifiers? (r/parse-form '{~x foo}))))

    (t/testing "sets"
      (t/is (not (r/multiple-unifiers? (r/parse-form '#{~x}))))
      (t/is (not (r/multiple-unifiers? (r/parse-form '#{x}))))
      (t/is (not (r/multiple-unifiers? (r/parse-form '#{x}))))

      (t/is (r/multiple-unifiers? (r/parse-form '#{~x ~y}))))))
