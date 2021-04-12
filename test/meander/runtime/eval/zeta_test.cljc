(ns meander.runtime.eval.zeta-test
  (:require
   [clojure.test :as t]
   [clojure.template :as template]
   [meander.core.zeta :as m.core]
   [meander.runtime.eval.zeta :as m.rt.eval]
   [meander.runtime.tree.zeta :as m.rt.tree]))

(defmacro using-runtime
  {:style/indent 1}
  [runtime & body]
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
                 ~'with]} ~runtime]
     ~@body))

;; The following runtime methods are untested.
;;
;;   :eval
;;   :make
;;   :none
(def runtime-test-template
  '(using-runtime $runtime
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
                (pick (pass (seed 1))
                      (fail (seed 2)))))

       (t/is (= (pass (seed 2))
                (pick (fail (seed 1))
                      (pass (seed 2)))))

       (t/is (= (fail (seed 2))
                (pick (fail (seed 1))
                      (fail (seed 2))))))

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
                                             (call again state identity))))))))))))))))))

(defmacro runtime-tests [runtime]
  (template/apply-template '[$runtime] runtime-test-template [runtime]))

(t/deftest df-one-runtime-test
  (runtime-tests (m.rt.eval/df-one)))

(t/deftest df-all-runtime-test
  (runtime-tests (m.rt.eval/df-all)))

(t/deftest df-one-integration-test
  (let [rt (m.rt.eval/df-all)
        query (fn [p x] (m.core/run-query p rt x))
        yield (fn [p] (m.core/run-yield p rt))
        rewrite (fn [p x] (m.core/run-rule p rt x))]
    (using-runtime rt 
      (t/testing "anything"
        (let [x (rand)]
          (t/is (= (pass (seed x))
                   (query (m.core/anything) x)))))

      (t/testing "dual"
        (let [x 1
              y 2]
          (t/is (= (pass (seed x))
                   (query (m.core/dual (m.core/data x) (m.core/data y)) x)))

          (t/is (= (fail (seed x))
                   (query (m.core/dual (m.core/data x) (m.core/data x)) x)))

          (t/is (= (pass (seed x))
                   (yield (m.core/dual (m.core/data x) (m.core/data y)))))))

      (t/testing "one"
        (let [x 1
              y 2]
          (t/is (= (pass (seed x))
                   (query (m.core/one (m.core/data x) (m.core/data y)) x)))

          (t/is (= (pass (seed y))
                   (query (m.core/one (m.core/data x) (m.core/data y)) y)))

          (t/is (= (pick (pass (seed x))
                         (pass (seed x)))
                   (query (m.core/one (m.core/data x) (m.core/data x)) x)))

          (t/is (= (pass (seed x))
                   (yield (m.core/one (m.core/data x) (m.core/data x)))))))

      (t/testing "some"
        (let [x 1
              y 2]
          (t/is (= (pass (seed x))
                   (query (m.core/some (m.core/data x) (m.core/data y)) x)))

          (t/is (= (pass (seed y))
                   (query (m.core/some (m.core/data x) (m.core/data y)) y)))

          (t/is (= (join (fn [] (pass (seed x)))
                         (fn [] (pass (seed x))))
                   (query (m.core/some (m.core/data x) (m.core/data x)) x)))

          (t/is (= (join (fn [] (pass (seed x)))
                         (fn [] (pass (seed x))))
                   (yield (m.core/some (m.core/data x) (m.core/data x)))))))

      (t/testing "all"
        (let [x 1
              y 2]
          (t/is (= (pass (seed x))
                   (query (m.core/all (m.core/data x) (m.core/data x)) x)))

          (t/is (= (fail (seed y))
                   (query (m.core/all (m.core/data x) (m.core/data y)) x)))

          (t/is (= (fail (seed x))
                   (query (m.core/all (m.core/data y) (m.core/data x)) x)))

          (t/is (= (join (fn [] (pass (seed x)))
                         (fn [] (pass (seed x))))
                   (yield (m.core/all (m.core/data x) (m.core/data x)))))

          (t/is (= (fail (seed y))
                   (yield (m.core/all (m.core/data x) (m.core/data y)))))))

      (t/testing "apply"
        (t/is (= (pass (seed 2))
                 (query (m.core/apply (m.core/data inc) [] (m.core/data 2)) 1)))

        (t/is (= (fail (seed 2))
                 (query (m.core/apply (m.core/data inc) [] (m.core/data 1)) 1)))

        (t/is (= (pass (seed 2))
                 (query (m.core/apply (m.core/data +) [(m.core/data 1)] (m.core/data 2)) 1)))

        (t/is (= (fail (seed 2))
                 (query (m.core/apply (m.core/data +) [(m.core/data 1)] (m.core/data 1)) 1))))

      (t/testing "predicate"
        (t/is (= (pass (seed 1))
                 (query (m.core/predicate (m.core/data odd?) (m.core/anything)) 1)))

        (t/is (= (fail (seed 1))
                 (query (m.core/predicate (m.core/data odd?) (m.core/data 3)) 1)))

        (t/is (= (pass (seed 1))
                 (yield (m.core/predicate (m.core/data odd?) (m.core/data 1)))))

        (t/is (= (fail (seed 2))
                 (yield (m.core/predicate (m.core/data odd?) (m.core/data 2))))))

      (t/testing "project"
        (t/is (= (pass (seed 1))
                 (query (m.core/project (m.core/data 2) (m.core/data 2) (m.core/data 1)) 1)))

        (t/is (= (fail (seed 1))
                 (query (m.core/project (m.core/data 2) (m.core/data 1) (m.core/data 1)) 1)))
        
        (t/is (= (fail (seed 1))
                 (query (m.core/project (m.core/data 2) (m.core/data 2) (m.core/data 2)) 1))))

      (t/testing "rx-empty"
        (t/is (= (pass (seed ()))
                 (query (m.core/rx-empty) ())))

        (t/is (= (pass (seed []))
                 (query (m.core/rx-empty) [])))

        (t/is (= (pass (seed []))
                 (yield (m.core/rx-empty)))))

      (t/testing "rx-cons"
        (t/is (= (pass (seed '(1)))
                 (yield (m.core/rx-cons (m.core/data 1) (m.core/data ()))))))

      (t/testing "rx-cat"
        (t/is (= (pass (seed []))
                 (query (m.core/rx-cat [(m.core/anything)]) [1])
                 (query (m.core/rx-cat [(m.core/anything)] (m.core/rx-empty)) [1])))

        (t/is (= (pass (seed []))
                 (query (m.core/rx-cat [(m.core/anything)]) [1])
                 (query (m.core/rx-cat [(m.core/anything)] (m.core/rx-empty)) [1]))))

      (t/testing "logic-variable"
        (let [id (gensym)
              ?x (m.core/logic-variable id)]
          (t/is (= (pass {:object 1, :bindings {id 2}, :references {}})
                   (query (m.core/project (m.core/data 2) ?x
                            (m.core/anything)) 1)))

          (t/is (= (pass {:object 2, :bindings {id 2}, :references {}})
                   (query (m.core/project (m.core/data 2) ?x ?x) 2)))

          (t/is (= (fail (seed 1))
                   (query (m.core/project (m.core/data 2) ?x ?x) 1)))

          (t/is (= (pass {:object 2, :bindings {id 2}, :references {}})
                   (yield (m.core/project (m.core/data 2) ?x ?x))))

          (t/is (= (fail {:object 1, :bindings {id 2}, :references {}})
                   (yield (m.core/project (m.core/data 2) ?x
                            (m.core/project (m.core/data 1) ?x 
                              (m.core/anything))))))))

      (t/testing "symbol"
        (t/is (= (pass (seed "name"))
                 (query (m.core/symbol (m.core/data "name")) 'name)))

        (t/is (= (pass (seed "name"))
                 (query (m.core/symbol (m.core/data "namespace") (m.core/data "name")) 'namespace/name)))

        (t/is (= (fail (seed "namespace"))
                 (query (m.core/symbol (m.core/data "namespace") (m.core/data "name")) 'name)))

        (t/is (= (fail (seed "namespace"))
                 (query (m.core/symbol (m.core/data "namespace") (m.core/data "name")) 'name/namespace))))

      (t/testing "keyword"
        (t/is (= (pass (seed "name"))
                 (query (m.core/keyword (m.core/data "name")) :name)))

        (t/is (= (pass (seed "name"))
                 (query (m.core/keyword (m.core/data "namespace") (m.core/data "name")) :namespace/name)))

        (t/is (= (fail (seed "namespace"))
                 (query (m.core/keyword (m.core/data "namespace") (m.core/data "name")) :name)))

        (t/is (= (fail (seed "namespace"))
                 (query (m.core/keyword (m.core/data "namespace") (m.core/data "name")) :name/namespace))))

      (t/testing "rule"
        (let [OneTwo (m.core/rule (m.core/data 1) (m.core/data 2))]
          (t/is (= (pass (seed 1))
                   (query OneTwo 1)))

          (t/is (= (pass (seed 2))
                   (yield OneTwo)))

          (t/is (= (pass (seed 2))
                   (rewrite OneTwo 1)))

          (t/is (= (fail (seed 2))
                   (rewrite OneTwo 2))))

        (let [?x (m.core/logic-variable '?x)
              LogicVariableID (m.core/rule ?x ?x)]
          (t/is (= (pass {:object 1 :bindings {'?x 1} :references {}})
                   (rewrite LogicVariableID 1))))

        (let [?x (m.core/logic-variable '?x)
              ?y (m.core/logic-variable '?y)
              PairReverse (m.core/rule (m.core/rx-cat [?x ?y]) (m.core/rx-cat [?y ?x]))]
          (t/is (= (pass {:object [2 1] :bindings {'?x 1, '?y 2} :references {}})
                   (rewrite PairReverse [1 2]))))

        (let [<x (m.core/fifo-variable '<x)
              FifoVariableID (m.core/rule <x <x)]
          (t/is (= (pass {:object 1 :bindings {'<x []} :references {}})
                   (rewrite FifoVariableID 1))))

        (let [>x (m.core/filo-variable '>x)
              FiloVariableID (m.core/rule >x >x)]
          (t/is (= (pass {:object 1 :bindings {'>x ()} :references {}})
                   (rewrite FiloVariableID 1))))

        (let [>x (m.core/filo-variable '>x)
              Reverse (m.core/rule (m.core/rx-cat [>x >x]) (m.core/rx-cat [>x >x]))]
          (t/is (= (pass {:object [2 1] :bindings {'>x ()} :references {}})
                   (rewrite Reverse [1 2]))))

        ))))
