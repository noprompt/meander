(ns meander.kernel.eval.zeta-test
  #?(:clj
     (:require
      [clojure.test :as t]
      [clojure.template :as template]
      [meander.kernel.eval.zeta :as m.rt.eval])
     :cljs
     (:require
      [clojure.test :as t]
      [meander.kernel.eval.zeta :as m.rt.eval]))
  #?(:cljs
     (:require-macros [meander.kernel.eval.zeta-test :refer [using-kernel
                                                             kernel-tests]])))

(defmacro using-kernel
  {:style/indent 1}
  [kernel & body]
  `(let [{:keys [~'bind
                 ~'call
                 ~'data
                 ~'dual
                 ~'eval
                 ~'fail
                 ~'find
                 ~'give
                 ~'join
                 ~'list
                 ~'load
                 ~'make
                 ~'mint
                 ~'none
                 ~'pass
                 ~'pick
                 ~'save
                 ~'scan
                 ~'seed
                 ~'star
                 ~'take
                 ~'test
                 ~'with]} ~kernel]
     ~@body))

;; The following kernel methods are untested.
;;
;;   :eval
;;   :make
;;   :none
#?(:clj
   (def kernel-test-template
     '(using-kernel $kernel
                    (t/testing "data"
                      (t/is (= 1
                               (data 1))))

                    (t/testing "seed"
                      (t/is (= {:object 1, :bindings {}, :references {}}
                               (seed 1)))

                      (t/is (= (pass {:object 1, :bindings {}, :references {}})
                               (pass (seed 1)))))

                    (t/testing "give"
                      (t/is (= (pass {:object 2, :bindings {}, :references {}})
                               (give (seed 1) (data 2) pass))))

                    (t/testing "take"
                      (t/is (= (pass 1)
                               (take (seed 1) pass)))

                      (t/is (= (fail 1)
                               (take (seed 1) fail))))

                    (t/testing "call"
                      (t/is (= (pass (seed 1))
                               (call (data (constantly 1)) (fn [x] (pass (seed x))))))

                      (t/is (= (pass (seed 2))
                               (call (data inc) 1 (fn [x] (pass (seed x))))))

                      (t/is (= (pass (seed 2))
                               (call (data +) 1 1 (fn [x] (pass (seed x)))))))

                    (t/testing "dual"
                      (t/is (= (pass (seed 1))
                               (dual (pass (seed 1)) (fail (seed 2)))))

                      (t/is (= (fail (seed 2))
                               (dual (fail (seed 2)) (pass (seed 1)))))

                      (t/is (= (fail (seed 1))
                               (dual (pass (seed 1)) (pass (seed 2))))))

                    (t/testing "save"
                      (let [identifier (gensym)]
                        (t/is (= (pass {:object 1, :bindings {identifier 1}, :references {}})
                                 (save (seed 1)
                                       identifier
                                       (fn [old new fold-pass fold-fail]
                                         (fold-pass new))
                                       pass
                                       fail)))))

                    (t/testing "load"
                      (let [identifier (gensym)]
                        (t/is (= (pass {:object 1, :bindings {identifier 1}, :references {}})
                                 (load {:object 0, :bindings {identifier 1}, :references {}}
                                       identifier
                                       (fn [old unfold-pass unfold-fail]
                                         (unfold-pass old old))
                                       pass
                                       fail))))

                      (let [identifier (gensym)]
                        (t/is (= (fail {:object 0, :bindings {identifier 1}, :references {}})
                                 (load {:object 0, :bindings {identifier 1}, :references {}}
                                       identifier
                                       (fn [old unfold-pass unfold-fail]
                                         (unfold-fail old))
                                       pass
                                       fail)))))

                    (t/testing "list"
                      (let [identifier (gensym)]
                        (t/is (= (pass {identifier 1})
                                 (save (seed 1)
                                       identifier
                                       (fn [old new fold-pass fold-fail]
                                         (fold-pass new))
                                       (fn [state]
                                         (pass (list state)))
                                       fail)))))

                    (t/testing "bind"
                      (t/is (= (pass {:object 1, :bindings {}, :references {}})
                               (bind pass (pass (seed 1)))))

                      (t/is (= (pass (seed 1))
                               (bind pass (pass (seed 1)))))

                      (t/is (= (fail (seed 1))
                               (bind fail (pass (seed 1))))))

                    (t/testing "pick"
                      (t/is (= (pass (seed 1))
                               (pick (fn [] (pass (seed 1)))
                                     (fn [] (fail (seed 2))))))

                      (t/is (= (pass (seed 2))
                               (pick (fn [] (fail (seed 1)))
                                     (fn [] (pass (seed 2))))))

                      (t/is (= (fail (seed 2))
                               (pick (fn [] (fail (seed 1)))
                                     (fn [] (fail (seed 2)))))))

                    (t/testing "join"
                      (t/is (= (pass (seed 1))
                               (join (fn [] (pass (seed 1)))
                                     (fn [] (fail (seed 2))))))

                      (t/is (= (pass (seed 2))
                               (join (fn [] (fail (seed 1)))
                                     (fn [] (pass (seed 2))))))

                      (t/is (= (fail (seed 2))
                               (join (fn [] (fail (seed 1)))
                                     (fn [] (fail (seed 2)))))))

                    (t/testing "scan"
                      (t/is (= (join (fn [] (pass (seed 1)))
                                     (fn [] (join (fn [] (pass (seed 2)))
                                                  (fn [] (pass (seed 3))))))
                               (scan (fn [x] (pass (seed x))) [1 2 3]))))

                    (t/testing "test"
                      (t/is (= (pass (seed 1))
                               (test true
                                     (fn [] (pass (seed 1)))
                                     (fn [] (pass (seed 2))))))

                      (t/is (= (pass (seed 2))
                               (test false
                                     (fn [] (pass (seed 1)))
                                     (fn [] (pass (seed 2)))))))

                    (t/testing "with"
                      (let [identifier (gensym)
                            function (fn [state] (give state 2 pass))]
                        (t/is (= (pass {:object 1, :bindings {}, :references {identifier function}})
                                 (with (seed 1) {identifier function}
                                   pass)))))

                    (t/testing "find"
                      (let [identifier (gensym)
                            function (fn [state] (give state 2 pass))]
                        (t/is (= (pass {:object 2, :bindings {}, :references {identifier function}})
                                 (with (seed 1) {identifier function}
                                   (fn [state]
                                     ((find state identifier) state)))))))

                    (t/testing "mint clears references"
                      (let [identifier (gensym)
                            function (fn [state] (give state 2 pass))]
                        (t/is (= (pass {:object 1, :bindings {}, :references {}})
                                 (save (seed 1)
                                       identifier
                                       (fn [old new fold-pass fold-fail]
                                         (fold-pass new))
                                       (fn [state]
                                         (pass (mint state)))
                                       fail)))))

                    (t/testing "mint clears binding"
                      (let [identifier (gensym)
                            function (fn [state] (give state 2 pass))]
                        (t/is (= (pass {:object 1, :bindings {}, :references {}})
                                 (with (seed 1) {identifier function}
                                   (fn [state]
                                     (pass (mint state))))))))

                    (t/testing "star"
                      (t/is (= (pass (seed 3))
                               (star (seed 1)
                                     (fn [again state]
                                       (take state
                                             (fn [x]
                                               (call (data =) x (data 3)
                                                     (fn [true?]
                                                       (test true?
                                                             (fn [] (pass state))
                                                             (fn [] (call (data inc) x
                                                                          (fn [y]
                                                                            (give state y
                                                                                  (fn [state]
                                                                                    (call again state identity)))))))))))))))))))

#?(:clj
   (defmacro kernel-tests [kernel]
     (template/apply-template '[$kernel] kernel-test-template [kernel])))

(t/deftest df-one-kernel-test
  (kernel-tests (m.rt.eval/df-one)))

(t/deftest df-all-kernel-test
  (kernel-tests (m.rt.eval/df-all)))
