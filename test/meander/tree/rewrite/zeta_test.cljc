(ns meander.tree.rewrite.zeta-test
  ""
  (:require [clojure.test :as t]
            [meander.tree.zeta :as m.tree]
            [meander.tree.rewrite.zeta :as m.tree.rewrite]))

(def %true
  (m.tree/data true))

(def %false
  (m.tree/data false))

(def %empty-bindings
  (m.tree/bindings))

(defn data-state [x]
  (m.tree/state (m.tree/data x) (m.tree/bindings)))

(def %true-state
  (m.tree/state %true %empty-bindings))

(def %false-state
  (m.tree/state %true %empty-bindings))

;; Bind tests
;; ---------------------------------------------------------------------

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
  (t/testing "Rule

        (bind x (pass e1) e2)
        ---------------------
            (let x e1 e2)"
    (let [%i__1 (m.tree/identifier)]
      (= (m.tree/let %i__1 %true-state
           (m.tree/pass %true-state))
         (m.tree.rewrite/pass-bind 
          (m.tree/bind %i__1 (m.tree/pass %true-state)
            (m.tree/pass %true-state)))))))

(t/deftest rule-bind-pick-test
  (t/testing "Rule

        (bind i (pick e1 e2) e3)
    ------------------------------------ BindPick
    (pick (bind i e1 e3) (bind i e2 e3))"
    (let [i (m.tree/identifier)
          e1 (m.tree/pass %true-state)
          e2 (m.tree/pass %false-state)
          e3 (m.tree/pass %true-state)]
      (t/is (= (m.tree/pick (m.tree/bind i e1 e3) (m.tree/bind i e2 e3))
               (m.tree.rewrite/rule-bind-pick
                (m.tree/bind i (m.tree/pick e1 e2) e3)))))))

;; Let tests
;; ---------------------------------------------------------------------

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

;; Join tests
;; ---------------------------------------------------------------------

(t/deftest rule-join-test
  (t/is (= nil
           (m.tree.rewrite/rule-join
            (m.tree/join (m.tree/pass %true-state)
                         (m.tree/pass %false-state)))))

  (t/testing "Rule

    (join (fail e1) e2)
    ------------------- JoinFail
           e2"
    (t/is (= (m.tree/pass %false-state)
             (m.tree.rewrite/rule-join
              (m.tree/join (m.tree/fail %true-state)
                           (m.tree/pass %false-state)))))))

;; Pick tests
;; ---------------------------------------------------------------------

(t/deftest rule-pick-pass-test
  (t/testing "Rule

    (pick (pass e1) e2)
    ------------------- 
         (pass e1)"
    (t/is (= (m.tree/pass %true-state)
             (m.tree.rewrite/rule-pick
              (m.tree/pick (m.tree/pass %true-state)
                           (m.tree/pass %false-state)))))))

(t/deftest rule-pick-fail-test
  (t/testing "Rule

    (pick (fail e1) e2)
    ------------------- PickFail
           e2"
    (t/is (= (m.tree/pass %false-state)
             (m.tree.rewrite/rule-pick
              (m.tree/pick (m.tree/fail %true-state)
                           (m.tree/pass %false-state)))))))

(t/deftest rule-pick-let-test
  (t/testing "Rule

    (pick (let i e1 e2) e3)
    -----------------------
    (let i e1 (pick e2 e3))"
    (let [i (m.tree/identifier)
          e1 %true-state
          e2 (m.tree/pass %true-state)
          e3 (m.tree/pass %false-state)]
     (t/is (= (m.tree/let i e1 (m.tree/pick e2 e3))
               (m.tree.rewrite/rule-pick-let
                (m.tree/pick (m.tree/let i e1 e2) e3)))))))

(t/deftest rule-pick-test-test
  (t/testing "Rule

         (pick (test e1 e2 e3) e4)
    -----------------------------------
    (test e1 (pick e2 e4) (pick e3 e4))"
    (let [e1 (m.tree/data 1)
          e2 (m.tree/pass (m.tree/seed 1))
          e3 (m.tree/pass (m.tree/seed 2))
          e4 (m.tree/pass (m.tree/seed 3))]
      (t/is (= (m.tree/test e1 (m.tree/pick e2 e4) (m.tree/pick e3 e4))
               (m.tree.rewrite/rule-pick-test
                (m.tree/pick (m.tree/test e1 e2 e3) e4)))))))

;; Test tests
;; ---------------------------------------------------------------------

(t/deftest rule-test-true-test
  (t/testing "Rule

    (test (data true) e1 e2)
    ------------------------
              e1"
    (let [e1 (m.tree/data 1)
          e2 (m.tree/data 2)]
      (t/is (= e1
               (m.tree.rewrite/rule-test-true 
                (m.tree/test m.tree/$true e1 e2)))))))

(t/deftest rule-test-false-test
  (t/testing "Rule

    (test (data false) e1 e2)
    ------------------------
              e2"
    (let [e1 (m.tree/data 1)
          e2 (m.tree/data 2)]
      (t/is (= e2
               (m.tree.rewrite/rule-test-false 
                (m.tree/test m.tree/$false e1 e2)))))))

(t/deftest rule-prune-test-redundant
  (let [i1 (m.tree/identifier)
        e1 (m.tree/data 1)]
    (t/is (= e1
             (m.tree.rewrite/rule-prune-test-redundant
              (m.tree/test i1 e1 e1))))))

(t/deftest pass-prune-test-test
  (let [i1 (m.tree/identifier)
        e1 (m.tree/data 1)
        e2 (m.tree/data 2)
        e3 (m.tree/data 3)
        e4 (m.tree/data 4)]
    (t/is (= (m.tree/test i1 e1 e4)
             (m.tree.rewrite/rule-prune-nested-test
              (m.tree/test i1
                (m.tree/test i1 e1 e2)
                (m.tree/test i1 e3 e4)))))))

;; Interpretation
;; ---------------------------------------------------------------------

;; SetObject tests
;; ---------------

(t/deftest set-object-test
  (t/is (= (m.tree/state (m.tree/data 10) (m.tree/bindings))
           (m.tree.rewrite/set-object (m.tree/state (m.tree/data 20) (m.tree/bindings)) (m.tree/data 10)))))
