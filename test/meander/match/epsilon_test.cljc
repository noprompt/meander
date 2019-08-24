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

