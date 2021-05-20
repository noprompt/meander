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

(def %empty
  (m.pattern/regex-empty))

;; Tests
;; ---------------------------------------------------------------------


(t/deftest anything-test
  (let [x (reify)]
    (t/testing "anything query"
      (t/is (= {:object x, :bindings {}, :references {}}
               (host-match %anything x)))

      (t/is (= [{:object x, :bindings {}, :references {}}]
               (host-search %anything x))))

    (t/testing "anything yield"
      (let [state (host-build %anything)]
        (t/is (map? state))

        (t/is (contains? state :object))

        (t/is (= {}
                 (get state :bindings)))

        (t/is (= {}
                 (get state :references))))

      (let [[state & rest-states] (host-stream %anything)]
        (t/is (not (seq rest-states)))

        (t/is (map? state))

        (t/is (contains? state :object))

        (t/is (= {}
                 (get state :bindings)))

        (t/is (= {}
                 (get state :references)))))))

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

;; NOTE: See `project-test` for additional examples of behavior.
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

;; NOTE: See `project-test` for additional examples of behavior.
(t/deftest mutable-variable-test
  (t/testing "mutable variable query"
    (let [x (reify)
          *x (m.pattern/mutable-variable '*x)]
      (t/testing "mutable variable match"
        (t/is (= {:object x, :bindings {'*x x}, :references {}}
                 (host-match *x x))))

      (t/testing "mutable variable search"
        (t/is (= [{:object x, :bindings {'*x x}, :references {}}]
                 (host-search *x x))))))

  (t/testing "mutable variable yield"
    (let [*x (m.pattern/mutable-variable '*x)]
      (t/testing "mutable variable build"
        (t/is (= nil
                 (host-build *x))))

      (t/testing "mutable variable search"
        (t/is (= []
                 (host-stream *x)))))))

;; NOTE: See `project-test` for additional examples of behavior.
(t/deftest fifo-variable-test
  (t/testing "fifo variable query"
    (let [x (reify)
          <x (m.pattern/fifo-variable '<x)]
      (t/testing "fifo variable match"
        (t/is (= {:object x, :bindings {'<x [x]}, :references {}}
                 (host-match <x x))))

      (t/testing "fifo variable search"
        (t/is (= [{:object x, :bindings {'<x [x]}, :references {}}]
                 (host-search <x x))))))

  (t/testing "fifo variable yield"
    (let [<x (m.pattern/fifo-variable '<x)]
      (t/testing "fifo variable build"
        (t/is (= nil
                 (host-build <x))))

      (t/testing "fifo variable search"
        (t/is (= []
                 (host-stream <x)))))))

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
        ?x (m.pattern/logic-variable '?x)
        *x (m.pattern/mutable-variable '*x)
        <x (m.pattern/fifo-variable '<x)
        %x (m.pattern/data x)
        %y (m.pattern/data y)]
    (t/testing "project query"
      (t/testing "project match"
        (t/is (= {:object y, :bindings {}, :references {}}
                 (host-match (m.pattern/project %x %x %y) y)))

        (t/is (= nil
                 (host-match (m.pattern/project %y %y %x) y)))

        (t/is (= nil
                 (host-match (m.pattern/project %y %x %y) y)))

        (t/testing "project match with logic variable"
          (t/is (= {:object x, :bindings {'?x x}, :references {}}
                   (host-match (m.pattern/project %x ?x %x) x)))

          (t/is (= {:object y, :bindings {'?x x}, :references {}}
                   (host-match (m.pattern/project %x ?x %y) y)))

          (t/is (= nil
                   (host-match (m.pattern/project %x ?x ?x) y))))

        (t/testing "project match with mutable variable"
          (t/is (= {:object x, :bindings {'*x x}, :references {}}
                   (host-match (m.pattern/project %x *x %x) x)))

          (t/is (= {:object y, :bindings {'*x y}, :references {}}
                   (host-match (m.pattern/project %x *x *x) y)))

          (t/is (= {:object y, :bindings {'*x x}, :references {}}
                   (host-match (m.pattern/project %x *x %y) y))))

        (t/testing "project match with fifo variable"
          (t/is (= {:object x, :bindings {'<x [x]}, :references {}}
                   (host-match (m.pattern/project %x <x %x) x)))

          (t/is (= {:object y, :bindings {'<x [x y]}, :references {}}
                   (host-match (m.pattern/project %x <x <x) y)))

          (t/is (= {:object y, :bindings {'<x [x]}, :references {}}
                   (host-match (m.pattern/project %x <x %y) y)))))

      (t/testing "project search"
        (t/is (= [{:object y, :bindings {}, :references {}}]
                 (host-search (m.pattern/project %x %x %y) y)))

        (t/is (= []
                 (host-search (m.pattern/project %y %y %x) y)))

        (t/is (= []
                 (host-search (m.pattern/project %y %x %y) y)))

        (t/testing "project search with logic variable"
          (t/is (= [{:object x, :bindings {'?x x}, :references {}}]
                   (host-search (m.pattern/project %x ?x %x) x)))

          (t/is (= [{:object y, :bindings {'?x x}, :references {}}]
                   (host-search (m.pattern/project %x ?x %y) y)))

          (t/is (= []
                   (host-search (m.pattern/project %x ?x ?x) y))))

        (t/testing "project search with mutable variable"
          (t/is (= [{:object x, :bindings {'*x x}, :references {}}]
                   (host-search (m.pattern/project %x *x %x) x)))

          (t/is (= [{:object y, :bindings {'*x y}, :references {}}]
                   (host-search (m.pattern/project %x *x *x) y)))

          (t/is (= [{:object y, :bindings {'*x x}, :references {}}]
                   (host-search (m.pattern/project %x *x %y) y))))

        (t/testing "project search with fifo variable"
          (t/is (= [{:object x, :bindings {'<x [x]}, :references {}}]
                   (host-search (m.pattern/project %x <x %x) x)))

          (t/is (= [{:object y, :bindings {'<x [x y]}, :references {}}]
                   (host-search (m.pattern/project %x <x <x) y)))

          (t/is (= [{:object y, :bindings {'<x [x]}, :references {}}]
                   (host-search (m.pattern/project %x <x %y) y))))))

    (t/testing "project yield"
      (t/testing "project build"
        (t/is (= {:object y, :bindings {}, :references {}}
                 (host-build (m.pattern/project %x %x %y))))

        (t/is (= {:object x, :bindings {}, :references {}}
                 (host-build (m.pattern/project %y %y %x))))

        (t/is (= nil
                 (host-build (m.pattern/project %y %x %y))))

        (t/testing "project build with logic variable"
          (t/is (= {:object x, :bindings {'?x x}, :references {}}
                   (host-build (m.pattern/project %x ?x %x))))

          (t/is (= {:object y, :bindings {'?x x}, :references {}}
                   (host-build (m.pattern/project %x ?x %y))))

          (t/is (= {:object x, :bindings {'?x x}, :references {}}
                   (host-build (m.pattern/project %x ?x ?x))))

          (t/is (= nil
                   (host-build (m.pattern/project %x ?x %nothing)))))

        (t/testing "project build with mutable variable"
          (t/is (= {:object x, :bindings {'*x x}, :references {}}
                   (host-build (m.pattern/project %x *x %x))))

          (t/is (= {:object y, :bindings {'*x x}, :references {}}
                   (host-build (m.pattern/project %x *x %y))))

          (t/is (= {:object x, :bindings {'*x x}, :references {}}
                   (host-build (m.pattern/project %x *x *x))))

          (t/is (= nil
                   (host-build (m.pattern/project %x *x %nothing)))))

        (t/testing "project build with fifo variable"
          (t/is (= {:object x, :bindings {'<x [x]}, :references {}}
                   (host-build (m.pattern/project %x <x %x))))

          (t/is (= {:object y, :bindings {'<x [x]}, :references {}}
                   (host-build (m.pattern/project %x <x %y))))

          (t/is (= {:object x, :bindings {'<x []}, :references {}}
                   (host-build (m.pattern/project %x <x <x))))

          (t/is (= nil
                   (host-build (m.pattern/project %x <x %nothing))))))

      (t/testing "project stream"
        (t/is (= [{:object y, :bindings {}, :references {}}]
                 (host-stream (m.pattern/project %x %x %y))))

        (t/is (= [{:object x, :bindings {}, :references {}}]
                 (host-stream (m.pattern/project %y %y %x))))

        (t/is (= []
                 (host-stream (m.pattern/project %y %x %y))))

        (t/testing "project stream with logic variable"
          (t/is (= [{:object x, :bindings {'?x x}, :references {}}]
                   (host-stream (m.pattern/project %x ?x %x))))

          (t/is (= [{:object y, :bindings {'?x x}, :references {}}]
                   (host-stream (m.pattern/project %x ?x %y))))

          (t/is (= [{:object x, :bindings {'?x x}, :references {}}]
                   (host-stream (m.pattern/project %x ?x ?x))))

          (t/is (= []
                   (host-stream (m.pattern/project %x ?x %nothing)))))

        (t/testing "project stream with mutable variable"
          (t/is (= [{:object x, :bindings {'*x x}, :references {}}]
                   (host-stream (m.pattern/project %x *x %x))))

          (t/is (= [{:object y, :bindings {'*x x}, :references {}}]
                   (host-stream (m.pattern/project %x *x %y))))

          (t/is (= [{:object x, :bindings {'*x x}, :references {}}]
                   (host-stream (m.pattern/project %x *x *x))))

          (t/is (= []
                   (host-stream (m.pattern/project %x *x %nothing))))))

      (t/testing "project stream with fifo variable"
        (t/is (= [{:object x, :bindings {'<x [x]}, :references {}}]
                 (host-stream (m.pattern/project %x <x %x))))

        (t/is (= [{:object y, :bindings {'<x [x]}, :references {}}]
                 (host-stream (m.pattern/project %x <x %y))))

        (t/is (= [{:object x, :bindings {'<x []}, :references {}}]
                 (host-stream (m.pattern/project %x <x <x))))

        (t/is (= []
                 (host-stream (m.pattern/project %x <x %nothing))))))))

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

(t/deftest regex-empty-test
  (t/testing "regex-empty query"
    (t/testing "regex-empty match"
      (t/is (= {:object [], :bindings {}, :references {}}
               (host-match (m.pattern/regex-empty) [])))

      (t/is (= {:object [], :bindings {}, :references {}}
               (host-match (m.pattern/regex-empty) ())))

      (t/is (= nil
               (host-match (m.pattern/regex-empty) nil)))

      (t/is (= nil
               (host-match (m.pattern/regex-empty) [1]))))

    (t/testing "regex-empty search"
      (t/is (= [{:object [], :bindings {}, :references {}}]
               (host-search (m.pattern/regex-empty) [])))

      (t/is (= [{:object [], :bindings {}, :references {}}]
               (host-search (m.pattern/regex-empty) ())))

      (t/is (= []
               (host-search (m.pattern/regex-empty) nil)))

      (t/is (= []
               (host-search (m.pattern/regex-empty) [1])))))

  (t/testing "regex-empty yield"
    (t/testing "regex-empty build"
      (t/is (= {:object [], :bindings {}, :references {}}
               (host-build (m.pattern/regex-empty))))

      (t/is (= {:object [], :bindings {}, :references {}}
               (host-build (m.pattern/regex-empty)))))

    (t/testing "regex-empty stream"
      (t/is (= [{:object [], :bindings {}, :references {}}]
               (host-stream (m.pattern/regex-empty))))

      (t/is (= [{:object [], :bindings {}, :references {}}]
               (host-stream (m.pattern/regex-empty)))))))


(t/deftest regex-cons-test
  (let [x (reify)]
    (t/testing "regex-cons query"
      (t/testing "regex-cons match"
        (t/is (= {:object [], :bindings {}, :references {}}
                 (host-match (m.pattern/regex-cons %anything %empty) [x])))

        (t/is (= {:object [], :bindings {}, :references {}}
                 (host-match (m.pattern/regex-cons %anything %empty) (list x))))

        (t/is (= nil
                 (host-match (m.pattern/regex-cons %anything %empty) [])))

        (t/is (= nil
                 (host-match (m.pattern/regex-cons %anything %empty) ())))))

    (t/testing "regex-cons yield"
      (t/testing "regex-cons build"
        (t/is (= {:object [x], :bindings {}, :references {}}
                 (host-build (m.pattern/regex-cons (m.pattern/data x) %empty))))

        (t/is (= nil
                 (host-build (m.pattern/regex-cons %nothing %empty))))

        (t/is (= nil
                 (host-build (m.pattern/regex-cons %anything %nothing))))))))

(t/deftest regex-concatenation-test
  (let [x (reify)
        y (reify)]
    (t/testing "regex-concatenation query"
      (t/testing "regex-concatenation match"
        (t/is (= {:object [], :bindings {}, :references {}}
                 (host-match (m.pattern/regex-concatenation [%anything] %empty) [x])))

        (t/is (= {:object [], :bindings {}, :references {}}
                 (host-match (m.pattern/regex-concatenation [%anything %anything] %empty) [x y])))

        (t/is (= {:object [], :bindings {}, :references {}}
                 (host-match (m.pattern/regex-concatenation [%anything] %empty) (list x))))

        (t/is (= {:object [], :bindings {}, :references {}}
                 (host-match (m.pattern/regex-concatenation [%anything %anything] %empty) (list x y))))

        (t/is (= {:object [], :bindings {}, :references {}}
                 (host-match (m.pattern/regex-concatenation [%anything] (m.pattern/regex-concatenation [%anything] %empty))
                             (list x y))))

        (t/is (= nil
                 (host-match (m.pattern/regex-concatenation [%anything] %empty) [])))

        (t/is (= nil
                 (host-match (m.pattern/regex-concatenation [%anything] %empty) ()))))

      (t/testing "regex-concatenation search"
        (t/is (= [{:object [], :bindings {}, :references {}}]
                 (host-search (m.pattern/regex-concatenation [%anything] %empty) [x])))

        (t/is (= [{:object [], :bindings {}, :references {}}]
                 (host-search (m.pattern/regex-concatenation [%anything %anything] %empty) [x y])))

        (t/is (= [{:object [], :bindings {}, :references {}}]
                 (host-search (m.pattern/regex-concatenation [%anything] %empty) (list x))))

        (t/is (= [{:object [], :bindings {}, :references {}}]
                 (host-search (m.pattern/regex-concatenation [%anything %anything] %empty) (list x y))))

        (t/is (= []
                 (host-search (m.pattern/regex-concatenation [%anything] %empty) [])))

        (t/is (= []
                 (host-search (m.pattern/regex-concatenation [%anything] %empty) ())))))

    (t/testing "regex-concatenation yield"
      (let [<x (m.pattern/fifo-variable '<x)
            %x (m.pattern/data x)
            %y (m.pattern/data y)
            %x|Ε (m.pattern/regex-concatenation [%x] %empty)
            %x·y|Ε₁ (m.pattern/regex-concatenation [%x %y] %empty)
            %x·y|Ε₂ (m.pattern/regex-concatenation [%x] (m.pattern/regex-concatenation [%y] %empty))
            %¡|E (m.pattern/regex-concatenation [%nothing] %empty)
            %_|¡ (m.pattern/regex-concatenation [%anything] %nothing)
            %<x·<x|E (m.pattern/regex-concatenation [<x <x] %empty)]
        (t/testing "regex-concatenation build"
          (t/is (= {:object [x], :bindings {}, :references {}}
                   (host-build %x|Ε)))

          (t/is (= {:object [x y], :bindings {}, :references {}}
                   (host-build %x·y|Ε₁)))

          (t/is (= {:object [x y], :bindings {}, :references {}}
                   (host-build %x·y|Ε₂)))

          (t/is (= {:object [x y], :bindings {'<x []}, :references {}}
                   (host-build (m.pattern/project %x <x (m.pattern/project %y <x %<x·<x|E)))))

          (t/is (= nil
                   (host-build %<x·<x|E)))

          (t/is (= nil
                   (host-build %¡|E)))

          (t/is (= nil
                   (host-build %_|¡))))

        (t/testing "regex-concatenation build"
          (t/is (= [{:object [x], :bindings {}, :references {}}]
                   (host-stream %x|Ε)))

          (t/is (= [{:object [x y], :bindings {}, :references {}}]
                   (host-stream %x·y|Ε₁)))

          (t/is (= [{:object [x y], :bindings {}, :references {}}]
                   (host-stream %x·y|Ε₂)))

          (t/is (= [{:object [x y], :bindings {'<x []}, :references {}}]
                   (host-stream (m.pattern/project %x <x (m.pattern/project %y <x %<x·<x|E)))))

          (t/is (= []
                   (host-stream %¡|E)))

          (t/is (= []
                   (host-stream %_|¡))))))))

(t/deftest regex-join-test
  (let [x (reify)
        y (reify)
        %x (m.pattern/data x)
        %invalid (m.pattern/regex-join %x %anything)]
    (t/testing "regex-join query"
      (let [%_·_|E|E (m.pattern/regex-join (m.pattern/regex-concatenation [%anything %anything] %empty) %empty)
            %E|_·_|E (m.pattern/regex-join %empty (m.pattern/regex-concatenation [%anything %anything] %empty))
            %_|E|_|E (m.pattern/regex-join (m.pattern/regex-concatenation [%anything] %empty)
                                           (m.pattern/regex-concatenation [%anything] %empty))]
        (t/testing "regex-join match"
          (t/is (= {:object [], :bindings {}, :references {}}
                   (host-match %_·_|E|E [x y])))

          (t/is (= {:object [], :bindings {}, :references {}}
                   (host-match %E|_·_|E [x y])))

          (t/is (= {:object [], :bindings {}, :references {}}
                   (host-match %_|E|_|E [x y])))

          (t/is (= nil
                   (host-match %_·_|E|E x)))

          (t/is (= nil
                   (host-match %_·_|E|E [x])))

          (t/is (= nil
                   (host-match %E|_·_|E [x])))

          (t/is (= nil
                   (host-match %_|E|_|E [x])))

          (t/is (= nil
                   (host-match %invalid [10]))))

        (t/testing "regex-join search"
          (t/is (= [{:object [], :bindings {}, :references {}}]
                   (host-search %_·_|E|E [x y])))

          (t/is (= [{:object [], :bindings {}, :references {}}]
                   (host-search %E|_·_|E [x y])))

          (t/is (= [{:object [], :bindings {}, :references {}}]
                   (host-search %_|E|_|E [x y])))

          (t/is (= []
                   (host-search %_·_|E|E [x])))

          (t/is (= []
                   (host-search %E|_·_|E [x])))

          (t/is (= []
                   (host-search %_|E|_|E [x])))

          (t/is (= []
                   (host-search %invalid [10]))))))

    (t/testing "regex-join yield"
      (let [%x (m.pattern/data x)
            %y (m.pattern/data y)
            %x·y|E|E (m.pattern/regex-join (m.pattern/regex-concatenation [%x %y] %empty) %empty)
            %E|x·y|E (m.pattern/regex-join %empty (m.pattern/regex-concatenation [%x %y] %empty))
            %x|E|y|E (m.pattern/regex-join (m.pattern/regex-concatenation [%x] %empty)
                                           (m.pattern/regex-concatenation [%y] %empty))
            %¡|E (m.pattern/regex-join %nothing %empty)
            %E|¡ (m.pattern/regex-join %empty %nothing)]
        (t/testing "regex-join build"
          (t/is (= {:object [x y], :bindings {}, :references {}}
                   (host-build %x·y|E|E)))

          (t/is (= {:object [x y], :bindings {}, :references {}}
                   (host-build %E|x·y|E)))

          (t/is (= {:object [x y], :bindings {}, :references {}}
                   (host-build %x|E|y|E)))

          (t/is (= nil
                   (host-build %E|¡)))

          (t/is (= nil
                   (host-build %invalid))))

        (t/testing "regex-join stream"
          (t/is (= [{:object [x y], :bindings {}, :references {}}]
                   (host-stream %x·y|E|E)))

          (t/is (= [{:object [x y], :bindings {}, :references {}}]
                   (host-stream %E|x·y|E)))

          (t/is (= [{:object [x y], :bindings {}, :references {}}]
                   (host-stream %x|E|y|E)))

          (t/is (= []
                   (host-stream %E|¡)))

          (t/is (= []
                   (host-stream %invalid))))))))


(t/deftest greedy-star-test
  (let [x (reify)
        y (reify)
        z (reify)
        %x (m.pattern/data x)
        %y (m.pattern/data y)
        %z (m.pattern/data z)]
    (t/testing "greedy-star query"
      (let [%x·y*|E (m.pattern/greedy-star [%x %y] %empty)
            %x·y*·z|E (m.pattern/greedy-star [%x %y] (m.pattern/regex-concatenation [%z] %empty))
             %invalid (m.pattern/greedy-star [%x %y] %x)]
        (t/testing "greedy-star match"
          (t/is (= {:object [], :bindings {}, :references {}}
                   (host-match %x·y*|E [])))

          (t/is (= {:object [], :bindings {}, :references {}}
                   (host-match %x·y*|E [x y])))

          (t/is (= {:object [], :bindings {}, :references {}}
                   (host-match %x·y*|E (take 100 (cycle [x y])))))

          (t/is (= {:object [], :bindings {}, :references {}}
                   (host-match %x·y*·z|E (concat (take 100 (cycle [x y])) [z]))))

          (t/is (= nil
                   (host-match %x·y*|E [x])))

          (t/is (= nil
                   (host-match %x·y*|E (take 99 (cycle [x y])))))

          (t/is (= nil
                   (host-match %invalid [x y]))))

        (t/testing "greedy-star search"
          (t/is (= [{:object [], :bindings {}, :references {}}]
                   (host-search %x·y*|E [])))

          (t/is (= [{:object [], :bindings {}, :references {}}]
                   (host-search %x·y*|E [x y])))

          (t/is (= [{:object [], :bindings {}, :references {}}]
                   (host-search %x·y*|E (take 100 (cycle [x y])))))

          (t/is (= []
                   (host-search %x·y*|E [x])))

          (t/is (= []
                   (host-search %x·y*|E (take 99 (cycle [x y])))))

          (t/is (= []
                   (host-search %invalid [x y]))))))

    (t/testing "greedy-star yield"
      (let [%x (m.pattern/data x)
            <x (m.pattern/fifo-variable '<x)
            %<x*|E (m.pattern/greedy-star [<x] (m.pattern/regex-empty))
            %invalid (m.pattern/greedy-star [<x] %x)]
        (t/testing "greedy-star build"
          (t/is (= {:object [x], :bindings {'<x []}, :references {}}
                   (host-build (m.pattern/project %x <x %<x*|E))))

          (t/is (= nil
                   (host-build (m.pattern/project %x <x %invalid)))))

        (t/testing "greedy-star stream"
          (t/is (= [{:object [x x], :bindings {'<x []}, :references {}}]
                   (host-stream (m.pattern/project %x (m.pattern/each <x <x) %<x*|E))))

          (t/is (= []
                   (host-stream (m.pattern/project %x <x %invalid)))))))))

(t/deftest frugal-star-test
  (t/testing "frugal-star query"
    (let [x (reify)
          y (reify)
          z (reify)
          %x (m.pattern/data x)
          %y (m.pattern/data y)
          %z (m.pattern/data z)
          %x·y*?|E (m.pattern/frugal-star [%x %y] %empty)
          %x·y*?|_ (m.pattern/frugal-star [%x %y] %anything)
          %x·y*?·z|E (m.pattern/frugal-star [%x %y] (m.pattern/regex-concatenation [%z] %empty))]
      (t/testing "frugal-star match"
        (t/is (= {:object [], :bindings {}, :references {}}
                 (host-match %x·y*?|E [])))

        (t/is (= {:object [], :bindings {}, :references {}}
                 (host-match %x·y*?|E [x y])))

        (t/is (= {:object [], :bindings {}, :references {}}
                 (host-match %x·y*?|E (take 100 (cycle [x y])))))

        (t/is (= {:object [], :bindings {}, :references {}}
                 (host-match %x·y*?·z|E [x y x y z])))

        (t/is (= {:object [x y x y], :bindings {}, :references {}}
                 (host-match %x·y*?|_ [x y x y])))

        (t/is (= nil
                 (host-match %x·y*?|E [x])))

        (t/is (= nil
                 (host-match %x·y*?|E [x y x]))))

      (t/testing "frugal-star search"
        (t/is (= [{:object [], :bindings {}, :references {}}]
                 (host-search %x·y*?|E [])))

        (t/is (= [{:object [], :bindings {}, :references {}}]
                 (host-search %x·y*?|E [x y])))

        (t/is (= [{:object [], :bindings {}, :references {}}]
                 (host-search %x·y*?|E [x y x y])))

        (t/is (= [{:object [x y x y], :bindings {}, :references {}}
                  {:object [x y], :bindings {}, :references {}}
                  {:object [], :bindings {}, :references {}}]
                 (host-search %x·y*?|_ [x y x y])))

        (t/is (= []
                 (host-search %x·y*?|E [x])))

        (t/is (= []
                 (host-search %x·y*?|E [x y x]))))))

  (t/testing "frugal-star yield"
    (let [x (reify)
          %x (m.pattern/data x)
          <x (m.pattern/fifo-variable '<x)
          %<x&<x (m.pattern/each <x <x)
          %<x*?|E (m.pattern/frugal-star [<x] (m.pattern/regex-empty))]
      (t/testing "frugal-star build"
        (t/is (= {:object [], :bindings {'<x [x x]}, :references {}}
                 (host-build (m.pattern/project %x %<x&<x %<x*?|E)))))

      (t/testing "frugal-star stream"
        (t/is (= [{:object [], :bindings {'<x [x x]}, :references {}}
                  {:object [x], :bindings {'<x [x]}, :references {}}
                  {:object [x x], :bindings {'<x []}, :references {}}]
                 (host-stream (m.pattern/project %x %<x&<x %<x*?|E))))))))
