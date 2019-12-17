(ns meander.match.ir.epsilon-test
  (:require [clojure.spec.alpha :as s :include-macros true]
            [clojure.spec.gen.alpha :as s.gen :include-macros true]
            [clojure.test :as t]
            [clojure.test.check.clojure-test :as tc.t :include-macros true]
            [clojure.test.check.generators :as tc.gen :include-macros true]
            [clojure.test.check.properties :as tc.prop :include-macros true]
            [meander.match.ir.epsilon :as r.ir]))



(t/deftest op-bind-merge-test
  (let [a (r.ir/op-bind 'a 1 (r.ir/op-return 1))
        b (r.ir/op-bind 'b 1 (r.ir/op-return 1))]
    (t/is (= a
             (r.ir/merge a b)))

    (let [a (r.ir/op-bind 'a 1 (r.ir/op-return 1))
          b (r.ir/op-bind 'b 2 (r.ir/op-return 2))]
      (t/is
       (= (r.ir/op-branch [a b])
          (r.ir/merge a b))))))

(t/deftest op-lvr-bind-merge-test
  (let [a (r.ir/op-lvr-bind '?x (r.ir/op-eval 1)
            (r.ir/op-return 1))
        b (r.ir/op-lvr-bind '?x (r.ir/op-eval 2)
            (r.ir/op-return 1))]
    (t/is (= (r.ir/op-branch [a b])
             (r.ir/merge a b)))
    (t/is (= [a b]
             (r.ir/merge-all [a b]))))

  (let [a (r.ir/op-lvr-bind '?x (r.ir/op-eval 1)
            (r.ir/op-return 1))]
    (t/is (= a
             (r.ir/merge a a)))
    (t/is (= [a]
             (r.ir/merge-all [a a])))))

(t/deftest op-branch-merge-test
  (let [a (r.ir/op-lvr-bind '?a (r.ir/op-eval 1) (r.ir/op-return 1))
        b (r.ir/op-lvr-bind '?b (r.ir/op-eval 1) (r.ir/op-return 1))
        c (r.ir/op-lvr-bind '?c (r.ir/op-eval 1) (r.ir/op-return 1))
        d (r.ir/op-lvr-bind '?d (r.ir/op-eval 1) (r.ir/op-return 1))]
    (t/is (= (r.ir/op-branch [a b c d])
             (r.ir/merge (r.ir/op-branch [a]) (r.ir/op-branch [b c d]))
             (r.ir/merge (r.ir/op-branch [a b]) (r.ir/op-branch [c d]))
             (r.ir/merge (r.ir/op-branch [a b c]) (r.ir/op-branch [d]))))))

(t/deftest op-lit-check-merge
  (let [target (r.ir/op-eval :target)
        case-a (r.ir/op-eval 1)
        then-a (r.ir/op-return 1)
        case-b (r.ir/op-eval 2)
        then-b (r.ir/op-return 2)
        case-c (r.ir/op-eval 3)
        then-c (r.ir/op-return 3)]
    (t/is (= (r.ir/op-case target
               [[case-a then-a]
                [case-b then-b]]
               (r.ir/op-fail))
             (r.ir/merge (r.ir/op-check-lit target case-a then-a)
                         (r.ir/op-check-lit target case-b then-b))))
    (t/is (= (r.ir/op-case target
               [[case-a then-a]
                [case-b then-b]
                [case-c then-c]]
               (r.ir/op-fail))
             (r.ir/merge
              (r.ir/merge (r.ir/op-check-lit target case-a then-a)
                          (r.ir/op-check-lit target case-b then-b))
              (r.ir/op-check-lit target case-c then-c))))
    (t/is (= (r.ir/op-case target
               [[case-c then-c]
                [case-a then-a]
                [case-b then-b]]
               (r.ir/op-fail))
             (r.ir/merge
              (r.ir/op-check-lit target case-c then-c)
              (r.ir/merge (r.ir/op-check-lit target case-a then-a)
                          (r.ir/op-check-lit target case-b then-b)))))))
