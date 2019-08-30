(ns meander.match.epsilon-test
  (:require [clojure.spec.alpha :as s :include-macros true]
            [clojure.test :as t]
            [clojure.test.check.clojure-test :as tc.t :include-macros true]
            [clojure.test.check.generators :as tc.gen :include-macros true]
            [clojure.test.check.properties :as tc.prop :include-macros true]
            [meander.epsilon :as r :include-macros true]
            [meander.match.epsilon :as r.match :include-macros true]
            [meander.syntax.epsilon :as r.syntax :include-macros true]))

;; ---------------------------------------------------------------------
;; match macro tests


(t/deftest map-can-have-variable-bound-lvr-keys
  (r.match/match {:k1 "v1"
                  :k2 {"v1" "v2"}}
    {:k1 ?x
     :k2 {?x ?y}}
    (t/is (and (= ?x "v1")
               (= ?y "v2")))

    _
    false))


(t/deftest matching-and-searching-maps
  (t/are [x] (if (coll? x)
               (every? true? x)
               (true? x))
    (r/match {:k1 :k2
              :ks {:k2 {:valid true}}}
      {:k1 ?k
       :ks {?k {:valid ?valid}}}
      ?valid)

    (r/search {:k1 :k2
              :ks {:k2 {:valid true}}}
      {:k1 ?k
       :ks {?k {:valid ?valid}}}
      ?valid)

    (r/search {1 2
               3 4
               :ks {1 {:parent nil}
                    2 {:parent 1}
                    3 {:parent nil}
                    4 {:parent 3}}}
      {?k1 ?k2
       :ks {?k2 {:parent ?k1}}}
      (= 1 (- ?k2 ?k1)))

    (r/match [:a {:a :b}]
      [?a {?a ?b}]
      (= ?b :b))

    (r/match {:a :b :b 4 :c 5}
      {:a ?b ?b (r/pred number?)}
      (= ?b :b))

    (r/search {2 :a 3 :b}
      (r/with [%x (r/pred even? ?k)]
        {%x ?v})
      (= [2 :a] [?k ?v]))

    (r/search {:a :b :b 4 :c 5}
      {:a ?b ?b (r/pred number?)}
      (= ?b :b))

    (r/search {:a :b :b 4 :c 5}
      {(r/pred keyword?) ?b ?b (r/pred number?)}
      (= ?b :b))

    (r/search {:a :b :b 4 :c :d :d 6 :e 5}
      {(r/pred keyword?) ?b ?b (r/pred even? ?num)}
      (boolean (and (#{:b :d} ?b)
                    (#{4 6} ?num))))

    (r/search {1 {2 [1 2 3 0]}
               3 {[1 2 3 0] 4}
               5 {[1 2 3 0] 6}}
      {?k1 {?k2 (r/scan 0)}
       ?k3 {(r/scan 0) ?k4}}
      (= -2 (- ?k4 ?k3 ?k2 ?k1)))))



#_
;; TODO: This should pass because ?x and ?y can be matched before
;; attempting to match the subsequence.
(t/deftest zero-or-more-may-have-lvrs-if-lvrs-can-be-bound
  (t/is  (= [1 2]
            (r.match/match [[1 2 1 2] 1 2]
              [[?x ?y ...] ?x ?y]
              [?x ?y]))))

#_
;; TODO: This should pass because there is only one possible solution.
(t/deftest sequences-of-zero-or-more
  (t/is (r.match/match [1 2 3 4 3 4]
          [1 2 ... 3 4 ...]
          true

          _
          false)))
