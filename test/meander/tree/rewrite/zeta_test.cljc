(ns meander.tree.rewrite.zeta-test
  (:require [clojure.test :as t]
            [meander.tree.zeta :as m.tree]
            [meander.tree.rewrite.zeta :as m.tree.rewrite]))

(def %true
  (m.tree/data true))

(def %false
  (m.tree/data false))

(def %empty-bindings
  (m.tree/bindings))

(def %true-state
  (m.tree/state %true %empty-bindings))

(def %false-state
  (m.tree/state %true %empty-bindings))

(t/deftest rule-bind-pass-test
  (t/testing "Rule

    (bind i1 (pass e1) e2)
    ---------------------- BindPass
        (let i1 e1 e2)"
    (let [%i__1 (m.tree/identifier)
          %i__2 (m.tree/identifier)]
      (t/is (= (m.tree/let %i__1 %true-state
                 (m.tree/pass %true-state))
               (m.tree.rewrite/rule-bind-pass
                (m.tree/bind %i__1 (m.tree/pass %false-state)
                  (m.tree/pass %true-state))))))))

(t/deftest rule-bind-fail-test
  (t/testing "Rule

    (bind i1 (fail e1) e2)
    ---------------------- BindFail
          (fail e1)"
    (let [%i__1 (m.tree/identifier)
          %i__2 (m.tree/identifier)]
      (t/is (= (m.tree/fail %false-state)
               (m.tree.rewrite/rule-bind-fail
                (m.tree/bind %i__1 (m.tree/fail %false-state)
                  (m.tree/pass %true-state))))))))

(t/deftest rule-bind-let-test
  (t/testing "Rule

    (bind i1 (let i2 e1 e2) e3)
    --------------------------- BindLet
    (let i2 e1 (bind i1 e2 e3))"
    (let [%i__1 (m.tree/identifier)
          %i__2 (m.tree/identifier)]
      (t/is (= (m.tree/let %i__2 %false
                 (m.tree/bind %i__1 (m.tree/pass %false-state)
                   (m.tree/pass %true)))
               (m.tree.rewrite/rule-bind-let
                (m.tree/bind %i__1 (m.tree/let %i__2 %false
                                     (m.tree/pass %false-state))
                  (m.tree/pass %true))))))))

(t/deftest rule-bind-test-test
  (t/testing "Rule

        (bind x (test e1 e2 e3) e4)
    --------------------------------------- BindTest
    (test e1 (bind x e2 e4) (bind x e3 e4))"
    (let [%i__1 (m.tree/identifier)
          %i__2 (m.tree/identifier)]
      (t/is (= (m.tree/test %i__2
                 (m.tree/bind %i__1 (m.tree/pass %true-state) (m.tree/pass %true-state))
                 (m.tree/bind %i__1 (m.tree/fail %true-state) (m.tree/pass %true-state)))
               (m.tree.rewrite/rule-bind-test
                (m.tree/bind %i__1 (m.tree/test %i__2 (m.tree/pass %true-state) (m.tree/fail %true-state))
                  (m.tree/pass %true-state))))))))

(t/deftest bind-pass-test
  (let [%i__1 (m.tree/identifier)
        %i__2 (m.tree/identifier)]
    (= (m.tree/test %i__2
         (m.tree/let %i__1 %true-state (m.tree/pass %true-state))
         (m.tree/fail %true-state))
       (m.tree.rewrite/bind-pass 
        (m.tree/bind %i__1 (m.tree/test %i__2 (m.tree/pass %true-state) (m.tree/fail %true-state))
          (m.tree/pass %true-state))))))

(t/deftest rule-let-let-test
  (t/testing "Rule

    (let i1 (let i2 e1 e2) e3)
    -------------------------- LetLet
    (let i2 e1 (let i1 e2 e3))"
    (let [%i__1 (m.tree/identifier)
          %i__2 (m.tree/identifier)]
      (t/is (= (m.tree/let %i__2 %false 
                 (m.tree/let %i__1 (m.tree/pass %false-state)
                   (m.tree/pass %true)))
               (m.tree.rewrite/rule-let-let
                (m.tree/let %i__1 (m.tree/let %i__2 %false (m.tree/pass %false-state))
                  (m.tree/pass %true))))))))

(t/deftest set-object-test
  (t/is (= (m.tree/state (m.tree/data 10) (m.tree/bindings))
           (m.tree.rewrite/set-object (m.tree/state (m.tree/data 20) (m.tree/bindings)) (m.tree/data 10)))))
