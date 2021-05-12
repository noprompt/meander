(ns meander.pattern.zeta-test
  (:require [clojure.test :as t]
            [meander.pattern.zeta :as m.pattern]
            [meander.runtime.eval.zeta :as m.kernel.eval]))

;; Helpers
;; ---------------------------------------------------------------------

(defn host-match [pattern object]
  (m.pattern/run-query pattern (m.kernel.eval/df-one) object))

(defn host-search [pattern object]
  (m.pattern/run-query pattern (m.kernel.eval/df-all) object))

(defn host-build [pattern]
  (m.pattern/run-yield pattern (m.kernel.eval/df-one)))

(defn host-stream [pattern]
  (m.pattern/run-yield pattern (m.kernel.eval/df-all)))

(def %anything
  (m.pattern/anything))

(def %nothing
  (m.pattern/nothing))

;; Tests
;; ---------------------------------------------------------------------


(t/deftest anything-test
  (let [x (reify)]
    (t/is (= {:object x, :bindings {}, :references {}}
             (host-match (m.pattern/anything) x)))

    (t/is (= [{:object x, :bindings {}, :references {}}]
             (host-search (m.pattern/anything) x)))))

(t/deftest nothing-test
  (let [x (reify)]
    (t/is (= nil
             (host-match (m.pattern/nothing) x)))

    (t/is (= ()
             (host-search (m.pattern/nothing) x)))))

(t/deftest data-test
  (t/testing "data query"
    (t/is (= {:object 1, :bindings {}, :references {}}
             (host-match (m.pattern/data 1) 1)))

    (t/is (= [{:object 1, :bindings {}, :references {}}]
             (host-search (m.pattern/data 1) 1)))

    (t/is (= nil
             (host-match (m.pattern/data 1) 2)))

    (t/is (= ()
             (host-search (m.pattern/data 1) 2))))

  (t/testing "data yield"
    (t/is (= {:object 1, :bindings {}, :references {}}
             (host-build (m.pattern/data 1))))

    (t/is (= [{:object 1, :bindings {}, :references {}}]
             (host-stream (m.pattern/data 1))))))

(t/deftest logic-variable-test
  (t/testing "logic variable query"
    (let [x (reify)
          ?x (m.pattern/logic-variable '?x)]
      (t/testing "logic variable match"
        (t/is (= {:object x, :bindings {'?x x}, :references {}}
                 (host-match ?x x))))

      (t/testing "logic variable search"
        (t/is (= [{:object x, :bindings {'?x x}, :references {}}]
                 (host-search ?x x))))))

  (t/testing "logic variable yield"
    (let [?x (m.pattern/logic-variable '?x)]
      (t/testing "logic variable build"
        (t/is (= nil
                 (host-build ?x))))

      (t/testing "logic variable search"
        (t/is (= []
                 (host-stream ?x)))))))
#?(:clj
   (t/deftest host-test
     (t/testing "host query"
       (t/is (= {:object inc, :bindings {}, :references {}}
                (host-match (m.pattern/host `inc) inc)))

       (t/is (= [{:object inc, :bindings {}, :references {}}]
                (host-search (m.pattern/host `inc) inc)))

       (t/is (= nil
                (host-match (m.pattern/host `inc) dec)))

       (t/is (= ()
                (host-search (m.pattern/host `inc) dec))))))

(t/deftest pick-test
  (t/testing "pick query"
    (let [x (reify)]
      (t/testing "pick match"
        (t/is (= {:object x, :bindings {}, :references {}}
                 (host-match (m.pattern/pick %anything %anything) x)))

        (t/is (= {:object x, :bindings {}, :references {}}
                 (host-match (m.pattern/pick %anything %nothing) x)))

        (t/is (= {:object x, :bindings {}, :references {}}
                 (host-match (m.pattern/pick %nothing %anything) x)))

        (t/is (= nil
                 (host-match (m.pattern/pick %nothing %nothing) x))))

      (t/testing "pick search"
        (t/is (= [{:object x, :bindings {}, :references {}}]
                 (host-search (m.pattern/pick %anything %anything) x)))

        (t/is (= [{:object x, :bindings {}, :references {}}]
                 (host-search (m.pattern/pick %anything %nothing) x)))

        (t/is (= [{:object x, :bindings {}, :references {}}]
                 (host-search (m.pattern/pick %nothing %anything) x)))

        (t/is (= []
                 (host-search (m.pattern/pick %nothing %nothing) x))))))

  (t/testing "pick yield"
    (let [x (reify)
          y (reify)
          %x (m.pattern/data x)
          %y (m.pattern/data y)]
      (t/testing "pick build"
        (t/is (= {:object x, :bindings {}, :references {}}
                 (host-build (m.pattern/pick %x %y))))

        (t/is (= {:object x, :bindings {}, :references {}}
                 (host-build (m.pattern/pick %x %nothing))))

        (t/is (= {:object x, :bindings {}, :references {}}
                 (host-build (m.pattern/pick %nothing %x))))

        (t/is (= nil
                 (host-build (m.pattern/pick %nothing %nothing)))))

      (t/testing "pick stream"
        (t/is (= [{:object x, :bindings {}, :references {}}]
                 (host-stream (m.pattern/pick %x %y))))

        (t/is (= [{:object x, :bindings {}, :references {}}]
                 (host-stream (m.pattern/pick %x %nothing))))

        (t/is (= [{:object x, :bindings {}, :references {}}]
                 (host-stream (m.pattern/pick %nothing %x))))

        (t/is (= []
                 (host-stream (m.pattern/pick %nothing %nothing))))))))

(t/deftest some-test
  (let [x (reify)]
    (t/testing "some query"
      (t/testing "some match"
        (t/is (= {:object x, :bindings {}, :references {}}
                 (host-match (m.pattern/some %anything %anything) x)))

        (t/is (= {:object x, :bindings {}, :references {}}
                 (host-match (m.pattern/some %anything %nothing) x)))

        (t/is (= {:object x, :bindings {}, :references {}}
                 (host-match (m.pattern/some %nothing %anything) x)))

        (t/is (= nil
                 (host-match (m.pattern/some %nothing %nothing) x))))

      (t/testing "some search"
        (t/is (= [{:object x, :bindings {}, :references {}}
                  {:object x, :bindings {}, :references {}}]
                 (host-search (m.pattern/some %anything %anything) x)))

        (t/is (= [{:object x, :bindings {}, :references {}}]
                 (host-search (m.pattern/some %anything %nothing) x)))

        (t/is (= [{:object x, :bindings {}, :references {}}]
                 (host-search (m.pattern/some %nothing %anything) x)))

        (t/is (= []
                 (host-search (m.pattern/some %nothing %nothing) x)))))

    (t/testing "some yield"
      (let [x (reify)
            y (reify)
            %x (m.pattern/data x)
            %y (m.pattern/data y)]
        (t/testing "some build"
          (t/is (= {:object x, :bindings {}, :references {}}
                   (host-build (m.pattern/some %x %y))))

          (t/is (= {:object x, :bindings {}, :references {}}
                   (host-build (m.pattern/some %x %nothing))))

          (t/is (= {:object x, :bindings {}, :references {}}
                   (host-build (m.pattern/some %nothing %x))))

          (t/is (= nil
                   (host-build (m.pattern/some %nothing %nothing)))))

        (t/testing "some stream"
          (t/is (= [{:object x, :bindings {}, :references {}}
                    {:object y, :bindings {}, :references {}}]
                   (host-stream (m.pattern/some %x %y))))

          (t/is (= [{:object x, :bindings {}, :references {}}]
                   (host-stream (m.pattern/some %x %nothing))))

          (t/is (= [{:object x, :bindings {}, :references {}}]
                   (host-stream (m.pattern/some %nothing %x))))

          (t/is (= []
                   (host-stream (m.pattern/some %nothing %nothing)))))))))

(t/deftest each-test
  (t/testing "each query"
    (let [x (reify)]
      (t/testing "each match"
        (t/is (= {:object x, :bindings {}, :references {}}
                 (host-match (m.pattern/each %anything %anything) x)))

        (t/is (= nil
                 (host-match (m.pattern/each %anything %nothing) x)))

        (t/is (= nil
                 (host-match (m.pattern/each %nothing %anything) x))))

      (t/testing "each search"
        (t/is (= [{:object x, :bindings {}, :references {}}]
                 (host-search (m.pattern/each %anything %anything) x)))

        (t/is (= []
                 (host-search (m.pattern/each %anything %nothing) x)))

        (t/is (= []
                 (host-search (m.pattern/each %nothing %anything) x))))))

  (t/testing "each yield"
    (let [x (reify)
          y (reify)
          %x (m.pattern/data x)
          %y (m.pattern/data y)]
      (t/testing "each build"
        (t/is (= {:object x, :bindings {}, :references {}}
                 (host-build (m.pattern/each %x %x))))

        (t/is (= nil
                 (host-build (m.pattern/each %x %y))))

        (t/is (= nil
                 (host-build (m.pattern/each %y %x)))))

      (t/testing "each search"
        (t/is (= [{:object x, :bindings {}, :references {}}
                  {:object x, :bindings {}, :references {}}]
                 (host-stream (m.pattern/each %x %x))))

        (t/is (= []
                 (host-stream (m.pattern/each %x %y))))

        (t/is (= []
                 (host-stream (m.pattern/each %y %x))))))))

(t/deftest project-test
  (let [x (reify)
        y (reify)
        %x (m.pattern/data x)
        %y (m.pattern/data y)]
    (t/testing "project query"
      (t/testing "project match"
        (t/is (= {:object y, :bindings {}, :references {}}
                 (host-match (m.pattern/project %x %x %y) y)))

        (t/is (= nil
                 (host-match (m.pattern/project %y %y %x) y)))

        (t/is (= nil
                 (host-match (m.pattern/project %y %x %y) y))))

      (t/testing "project search"
        (t/is (= [{:object y, :bindings {}, :references {}}]
                 (host-search (m.pattern/project %x %x %y) y)))

        (t/is (= []
                 (host-search (m.pattern/project %y %y %x) y)))

        (t/is (= []
                 (host-search (m.pattern/project %y %x %y) y)))))

    (t/testing "project yield"
      (t/testing "project build"
        (t/is (= {:object y, :bindings {}, :references {}}
                 (host-build (m.pattern/project %x %x %y))))

        (t/is (= {:object x, :bindings {}, :references {}}
                 (host-build (m.pattern/project %y %y %x))))

        (t/is (= nil
                 (host-build (m.pattern/project %y %x %y)))))

      (t/testing "project stream"
        (t/is (= [{:object y, :bindings {}, :references {}}]
                 (host-stream (m.pattern/project %x %x %y))))

        (t/is (= [{:object x, :bindings {}, :references {}}]
                 (host-stream (m.pattern/project %y %y %x))))

        (t/is (= []
                 (host-stream (m.pattern/project %y %x %y))))))))

(t/deftest apply-test
  (let [?x (m.pattern/logic-variable '?x)
        %inc (m.pattern/data inc)]
    (t/testing "apply query"
      (t/testing "apply match"
        (t/is (= {:object 2, :bindings {'?x 2}, :references {}}
                 (host-match (m.pattern/apply %inc (m.pattern/data []) ?x) 1)))

        (t/is (= nil
                 (host-match (m.pattern/apply %inc (m.pattern/data []) %nothing) 1))))

      (t/testing "apply search"
        (t/is (= [{:object 2, :bindings {'?x 2}, :references {}}]
                 (host-search (m.pattern/apply %inc (m.pattern/data []) ?x) 1)))

        (t/is (= []
                 (host-search (m.pattern/apply %inc (m.pattern/data []) %nothing) 1)))))

    (t/testing "apply yield"
      (t/testing "apply build"
        (t/is (= {:object 2, :bindings {'?x 2}, :references {}}
                 (host-build (m.pattern/apply %inc (m.pattern/data [1]) ?x))))
        (t/is (= nil
                 (host-build (m.pattern/apply %inc (m.pattern/data [1]) %nothing)))))

      (t/testing "apply stream"
        (t/is (= [{:object 2, :bindings {'?x 2}, :references {}}]
                 (host-stream (m.pattern/apply %inc (m.pattern/data [1]) ?x))))

        (t/is (= []
                 (host-stream (m.pattern/apply %inc (m.pattern/data [1]) %nothing))))))))

(t/deftest predicate-test
  (let [?x (m.pattern/logic-variable '?x)
        %even? (m.pattern/data even?)]
    (t/testing "predicate query"
      (t/testing "predicate match"
        (t/is (= {:object 2, :bindings {'?x 2}, :references {}}
                 (host-match (m.pattern/predicate %even? ?x) 2)))

        (t/is (= nil
                 (host-match (m.pattern/predicate %even? ?x) 1)))

        (t/is (= nil
                 (host-match (m.pattern/predicate %even? %nothing) 2))))

      (t/testing "predicate search"
        (t/is (= [{:object 2, :bindings {'?x 2}, :references {}}]
                 (host-search (m.pattern/predicate %even? ?x) 2)))

        (t/is (= []
                 (host-search (m.pattern/predicate %even? ?x) 1)))

        (t/is (= []
                 (host-search (m.pattern/predicate %even? %nothing) 2)))))

    (t/testing "predicate yield"
      (t/testing "predicate build"
        (t/is (= {:object 2, :bindings {}, :references {}}
                 (host-build (m.pattern/predicate %even? (m.pattern/data 2)))))

        (t/is (= nil
                 (host-build (m.pattern/predicate %even? (m.pattern/data 1))))))

      (t/testing "predicate stream"
        (t/is (= [{:object 2, :bindings {}, :references {}}]
                 (host-stream (m.pattern/predicate %even? (m.pattern/data 2)))))

        (t/is (= []
                 (host-stream (m.pattern/predicate %even? (m.pattern/data 1)))))))))
