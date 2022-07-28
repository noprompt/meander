(ns meander.primitive.zeta-test
  (:require
   [clojure.test :as t]
   [meander.logic.zeta :as m.logic]
   [meander.primitive.zeta :as m.primitive]
   [meander.primitive.hash-set.zeta :as m.primitive.hash-set]
   [meander.protocols.zeta :as m.protocols]
   [meander.state.zeta :as m.state]
   [meander.logic.dff.zeta :as m.logic.dff])
  #?(:clj
     (:import clojure.lang.ExceptionInfo
              meander.logic.dff.zeta.DFFLogic
              meander.logic.bfs.zeta.BFSLogic)))

(def ^{:arglists '([iquery ilogic])}
  query-unwrap
  (comp deref m.protocols/-query))

(def ^{:arglists '([iyield ilogic])}
  yield-unwrap
  (comp deref m.protocols/-yield))

(def ^{:arglists '([iyield ilogic])}
  redex-unwrap
  (comp deref m.protocols/-redex))

(defn var-factory [qrule yrule]
  (fn [id]
    (m.primitive/variable id qrule yrule)))

;; NOTE: It may be worthwhile to promote this eventually.
(defprotocol IFMap
  (-fmap [this f]))

(defn fmap [f x]
  (-fmap x f))

(extend-protocol IFMap
  DFFLogic
  (-fmap [this f]
    (if (nil? (.-istate this))
      this
      (DFFLogic. (f (.-istate this)))))

  BFSLogic
  (-fmap [this f]
    (BFSLogic. (map f (.-istates this)))))

(defn get-variable
  ([ilogic v]
   (get-variable ilogic v nil))
  ([ilogic v unbound]
   (deref (fmap
           (fn [istate]
             (m.state/get-variable istate v unbound))
           ilogic))))

(defn get-object
  ([ilogic]
   (get-object ilogic nil))
  ([ilogic zero]
   (if (m.logic/zero? ilogic)
     zero
     (deref (fmap
             (fn [istate]
               (m.state/get-object istate))
             ilogic)))))

(defn setup-state [{:keys [object bindings]}]
  (reduce-kv m.state/set-variable (m.state/make {:object object}) bindings))


(defn test-method [f pattern options]
  (let [istate (setup-state options)
        dff-result (f pattern (m.logic/make-dff istate))
        bfs-result (f pattern (m.logic/make-bfs istate))]
    {:dff-result dff-result
     :dff-zero? (m.logic/zero? dff-result)
     :bfs-result bfs-result
     :bfs-zero? (m.logic/zero? bfs-result)}))

(defmacro test-query
  {:arglists '([pattern {:keys [object bindings]} results-binding])
   :style/indent 3}
  [pattern options results & body]
  `(t/testing "-query"
     (let [~results (test-method m.protocols/-query ~pattern ~options)]
      ~@body)))

(defmacro test-yield
  {:arglists '([pattern {:keys [object bindings]} results-binding])
   :style/indent 3}
  [pattern options results & body]
  `(t/testing "-yield"
     (let [~results (test-method m.protocols/-yield ~pattern ~options)]
       ~@body)))

(defmacro test-redex
  {:arglists '([pattern {:keys [object bindings]} results-binding])
   :style/indent 3}
  [pattern options results & body]
  `(t/testing "-redex"
     (let [~results (test-method m.protocols/-redex ~pattern ~options)]
       ~@body)))

;; Tests

(t/deftest anything-protocol-satisfaction-test
  (let [pattern (m.primitive/anything)]
    (test-query pattern {} {:keys [dff-result bfs-result]}
      (t/is (not (m.logic/zero? dff-result)))
      (t/is (not (m.logic/zero? bfs-result)))))

  (test-yield (m.primitive/anything) {} {:keys [dff-result bfs-result]}
    (t/is (not (m.logic/zero? dff-result)))
    (t/is (not (m.logic/zero? bfs-result)))))

(t/deftest nothing-protocol-satisfaction-test
  (test-query (m.primitive/nothing) {} {:keys [dff-result bfs-result]}
    (t/is (m.logic/zero? dff-result))
    (t/is (m.logic/zero? bfs-result)))

  (test-yield (m.primitive/nothing) {} {:keys [dff-result bfs-result]}
    (t/is (m.logic/zero? dff-result))
    (t/is (m.logic/zero? bfs-result))))

(t/deftest is-protocol-satisfaction-test
  (let [object (reify)
        pattern (m.primitive/is object)]
    (test-query pattern {:object object} {:keys [dff-result bfs-result]}
      (t/is (not (m.logic/zero? dff-result)))
      (t/is (not (m.logic/zero? bfs-result))))

    (test-yield pattern {} {:keys [dff-result bfs-result]}
      (t/is (= object
               (get-object dff-result)))

      (t/is (= [object]
               (get-object bfs-result))))))

(t/deftest not-protocol-satisfaction-test
  (let [pattern (m.primitive/not (m.primitive/is 1))]
    (test-query pattern {:object 1} {:keys [dff-result bfs-result]}
      (t/is (m.logic/zero? dff-result))
      (t/is (m.logic/zero? bfs-result)))

    (test-query pattern {:object 2} {:keys [dff-result bfs-result]}
      (t/is (not (m.logic/zero? dff-result)))
      (t/is (not (m.logic/zero? bfs-result))))

    (t/testing "-yield"
      (let [ilogic (m.logic/make-dff (m.state/make {}))]
        (t/is (thrown-with-msg? ExceptionInfo #"Not implemented" (m.protocols/-yield pattern ilogic))))

      (let [ilogic (m.logic/make-bfs (m.state/make {}))]
        (t/is (thrown-with-msg? ExceptionInfo #"Not implemented" (m.protocols/-yield pattern ilogic)))))))

(t/deftest some-protocol-satisfaction-test
  (let [object1 (rand)
        object2 (inc object1)
        object3 (inc object2)
        pattern (m.primitive/some (m.primitive/is object1) (m.primitive/is object2))]
    (test-query pattern {:object object1} {:keys [dff-result bfs-result]}
      (t/is (not (m.logic/zero? dff-result)))
      (t/is (not (m.logic/zero? bfs-result))))

    (test-query pattern {:object object2} {:keys [dff-result bfs-result]}
      (t/is (not (m.logic/zero? dff-result)))
      (t/is (not (m.logic/zero? bfs-result))))

    (test-query pattern {:object object3} {:keys [dff-result bfs-result]}
      (t/is (m.logic/zero? dff-result))
      (t/is (m.logic/zero? bfs-result)))

    (test-yield pattern {} {:keys [dff-result bfs-result]}
      (t/is (= object1 (get-object dff-result)))
      (t/is (= [object1 object2]
               (get-object bfs-result))))))

(t/deftest pick-protocol-satisfaction-test
  (let [object1 (rand)
        object2 (inc object1)
        object3 (inc object2)
        pattern (m.primitive/pick (m.primitive/is object1) (m.primitive/is object2))]
    (test-query pattern {:object object1} {:keys [dff-result bfs-result]}
      (t/is (not (m.logic/zero? dff-result)))
      (t/is (not (m.logic/zero? bfs-result))))

    (test-query pattern {:object object2} {:keys [dff-result bfs-result]}
      (t/is (not (m.logic/zero? dff-result)))
      (t/is (not (m.logic/zero? bfs-result))))

    (test-query pattern {:object object3} {:keys [dff-result bfs-result]}
      (t/is (m.logic/zero? dff-result))
      (t/is (m.logic/zero? bfs-result)))

    (test-yield pattern {} {:keys [dff-result bfs-result]}
      (t/is (= object1 (get-object dff-result)))
      (t/is (= [object1]
               (get-object bfs-result))))))

(t/deftest each-protocol-satisfaction-test
  (let [pattern (m.primitive/each)]
    (test-query pattern {} {:keys [dff-result bfs-result]}
      (t/is (not (m.logic/zero? dff-result)))
      (t/is (not (m.logic/zero? bfs-result))))

    (test-yield pattern {} {:keys [dff-result bfs-result]}
      (t/is (not (m.logic/zero? dff-result)))
      (t/is (not (m.logic/zero? bfs-result)))))

  (let [pattern (m.primitive/each (m.primitive/anything))]
    (test-query pattern {} {:keys [dff-result bfs-result]}
      (t/is (not (m.logic/zero? dff-result)))
      (t/is (not (m.logic/zero? bfs-result))))

    (test-yield pattern {} {:keys [dff-result bfs-result]}
      (t/is (not (m.logic/zero? dff-result)))
      (t/is (not (m.logic/zero? bfs-result)))))

  (let [pattern (m.primitive/each (m.primitive/anything) (m.primitive/anything))]
    (test-query pattern {} {:keys [dff-result bfs-result]}
      (t/is (not (m.logic/zero? dff-result)))
      (t/is (not (m.logic/zero? bfs-result))))

    (test-yield pattern {} {:keys [dff-result bfs-result]}
      (t/is (not (m.logic/zero? dff-result)))
      (t/is (not (m.logic/zero? bfs-result)))))

  (let [pattern (m.primitive/each (m.primitive/is 1) (m.primitive/anything))]
    (test-query pattern {:object 1} {:keys [dff-result bfs-result]}
      (t/is (not (m.logic/zero? dff-result)))
      (t/is (not (m.logic/zero? bfs-result))))

    (test-yield pattern {} {:keys [dff-result bfs-result]}
      (t/is (not (m.logic/zero? dff-result)))
      (t/is (= 1 (get-object dff-result)))

      (t/is (not (m.logic/zero? bfs-result)))
      (t/is (= [1] (get-object bfs-result)))))

  (let [pattern (m.primitive/each (m.primitive/nothing) (m.primitive/anything))]
    (test-query pattern {:object 1} {:keys [dff-result bfs-result]}
      (t/is (m.logic/zero? dff-result))
      (t/is (m.logic/zero? bfs-result)))

    (test-yield pattern {} {:keys [dff-result bfs-result]}
      (t/is (m.logic/zero? dff-result))
      (t/is (m.logic/zero? bfs-result))))

  (let [pattern (m.primitive/each (m.primitive/nothing) (m.primitive/anything))]
    (test-query pattern {:object 1} {:keys [dff-result bfs-result]}
      (t/is (m.logic/zero? dff-result))
      (t/is (m.logic/zero? bfs-result)))

    (test-yield pattern {} {:keys [dff-result bfs-result]}
      (t/is (m.logic/zero? dff-result))
      (t/is (m.logic/zero? bfs-result))))

  (let [pattern (m.primitive/each (m.primitive/nothing) (m.primitive/nothing))]
    (test-query pattern {:object 1} {:keys [dff-result bfs-result]}
      (t/is (m.logic/zero? dff-result))
      (t/is (m.logic/zero? bfs-result)))

    (test-yield pattern {} {:keys [dff-result bfs-result]}
      (t/is (m.logic/zero? dff-result))
      (t/is (m.logic/zero? bfs-result)))))


(t/deftest logic-variable-protocol-satisfaction-test
  (m.primitive/fresh [?1]
    (t/testing "When variable is unbound"
      (test-query ?1 {:object 1} {:keys [dff-result bfs-result]}
        (t/is (= 1 (get-variable dff-result ?1)))
        (t/is (= [1] (get-variable bfs-result ?1))))

      (test-yield ?1 {:object 1} {:keys [dff-result bfs-result]}
        (t/is (m.logic/zero? dff-result))
        (t/is (m.logic/zero? bfs-result))))

    (t/testing "When variable is bound to a value not equal to object"
      (test-query ?1 {:object 1 :bindings {?1 2}} {:keys [dff-result bfs-result]}
        (t/is (m.logic/zero? dff-result))
        (t/is (m.logic/zero? bfs-result)))

      (test-yield ?1 {:object 1 :bindings {?1 2}} {:keys [dff-result bfs-result]}
        (t/is (= 2 (get-variable dff-result ?1)))
        (t/is (= [2] (get-variable bfs-result ?1)))))))

(t/deftest rule-protocol-satisfaction-test
  (let [pattern (m.primitive/rule (m.primitive/anything) (m.primitive/anything))]
    (t/testing "When query and yield succeed so does redex"
      (test-query pattern {} {:keys [dff-result bfs-result]}
        (t/is (not (m.logic/zero? dff-result)))
        (t/is (not (m.logic/zero? bfs-result))))

      (test-yield pattern {} {:keys [dff-result bfs-result]}
        (t/is (not (m.logic/zero? dff-result)))
        (t/is (not (m.logic/zero? bfs-result))))

      (test-redex pattern {} {:keys [dff-result bfs-result]}
        (t/is (not (m.logic/zero? dff-result)))
        (t/is (not (m.logic/zero? bfs-result))))))

  (let [pattern (m.primitive/rule (m.primitive/nothing) (m.primitive/anything))]
    (t/testing "When query fails so does redex"
      (test-query pattern {} {:keys [dff-result bfs-result]}
        (t/is (m.logic/zero? dff-result))
        (t/is (m.logic/zero? bfs-result)))

      (test-yield pattern {} {:keys [dff-result bfs-result]}
        (t/is (not (m.logic/zero? dff-result)))
        (t/is (not (m.logic/zero? bfs-result))))

      (test-redex pattern {} {:keys [dff-result bfs-result]}
        (t/is (m.logic/zero? dff-result))
        (t/is (m.logic/zero? bfs-result)))))

  (let [pattern (m.primitive/rule (m.primitive/anything) (m.primitive/nothing))]
    (t/testing "When yield fails so does redex"
      (test-query pattern {} {:keys [dff-result bfs-result]}
        (t/is (not (m.logic/zero? dff-result)))
        (t/is (not (m.logic/zero? bfs-result))))

      (test-yield pattern {} {:keys [dff-result bfs-result]}
        (t/is (m.logic/zero? dff-result))
        (t/is (m.logic/zero? bfs-result)))

      (test-redex pattern {} {:keys [dff-result bfs-result]}
        (t/is (m.logic/zero? dff-result))
        (t/is (m.logic/zero? bfs-result))))))

(t/deftest str-protocol-satisfaction-test
  (t/testing "With zero arguments"
    (let [pattern (m.primitive/str)]
      (t/testing "when the object is the empty string"
        (test-query pattern {:object ""} {:keys [dff-result bfs-result]}
          (t/is (not (m.logic/zero? dff-result)))
          (t/is (not (m.logic/zero? bfs-result))))

        (test-yield pattern {} {:keys [dff-result bfs-result]}
          (t/is (= "" (get-object dff-result)))
          (t/is (= [""] (get-object bfs-result)))))))

  (t/testing "With one argument"
    (m.primitive/fresh [?a]
      (let [pattern (m.primitive/str ?a)]
        (t/testing "when the object is the empty string"
          (test-query pattern {:object ""} {:keys [dff-result bfs-result]}
            (t/is (not (m.logic/zero? dff-result)))
            (t/is (not (m.logic/zero? bfs-result)))

            (t/is (= "" (get-variable dff-result ?a)))
            (t/is (= [""] (get-variable bfs-result ?a))))

          (test-yield pattern {:object "" :bindings {?a ""}} {:keys [dff-result bfs-result]}
            (t/is (= "" (get-variable dff-result ?a)))
            (t/is (= [""] (get-variable bfs-result ?a))))))))

  (t/testing "With two arguments"
    (m.primitive/fresh [?a ?b]
      (let [pattern (m.primitive/str ?a ?b)]
        (t/testing "when the object is the empty string"
          (test-query pattern {:object ""} {:keys [dff-result bfs-result]}
            (t/is (not (m.logic/zero? dff-result)))
            (t/is (= "" (get-variable dff-result ?a)))
            (t/is (= "" (get-variable dff-result ?b)))

            (t/is (not (m.logic/zero? bfs-result)))
            (t/is (= [""] (get-variable bfs-result ?a)))
            (t/is (= [""] (get-variable bfs-result ?b))))

          (test-yield pattern {:object "" :bindings {?a "" ?b ""}} {:keys [dff-result bfs-result]}
            (t/is (= "" (get-object dff-result)))
            (t/is (= [""] (get-object bfs-result)))))

        (t/testing "when the object is a non-empty string"
          (test-query pattern {:object "foo"} {:keys [dff-result bfs-result]}
            (t/is (not (m.logic/zero? dff-result)))
            (t/is (= "" (get-variable dff-result ?a)))
            (t/is (= "foo" (get-variable dff-result ?b)))

            (t/is (not (m.logic/zero? bfs-result)))
            (t/is (= ["" "foo" "fo" "f"] (get-variable bfs-result ?a)))
            (t/is (= ["foo" "" "o" "oo"] (get-variable bfs-result ?b))))

          (test-yield pattern {:object "foo" :bindings {?a "b" ?b "ar"}} {:keys [dff-result bfs-result]}
            (t/is (= "bar" (get-object dff-result)))
            (t/is (= ["bar"] (get-object bfs-result)))))))))

(t/deftest symbol-protocol-satisfaction-test
  (t/testing "With one argument"
    (let [pattern (m.primitive/symbol (m.primitive/is "foo"))]
      (t/testing "when object is an unqualified symbol"
        (test-query pattern {:object 'foo} {:keys [dff-result bfs-result]}
          (t/is (not (m.logic/zero? dff-result)))
          (t/is (not (m.logic/zero? bfs-result)))))

      (t/testing "when object is a qualified symbol"
        (test-query pattern {:object 'foo/foo} {:keys [dff-result bfs-result]}
          (t/is (not (m.logic/zero? dff-result)))
          (t/is (not (m.logic/zero? bfs-result))))

        (test-query pattern {:object 'foo/bar} {:keys [dff-result bfs-result]}
          (t/is (m.logic/zero? dff-result))
          (t/is (m.logic/zero? bfs-result))))

      (test-yield pattern {} {:keys [dff-result bfs-result]}
        (t/is (= 'foo (get-object dff-result)))
        (t/is (= ['foo] (get-object bfs-result))))))

  (t/testing "With two arguments"
    (let [pattern (m.primitive/symbol (m.primitive/is "foo") (m.primitive/is "foo"))]
      (t/testing "when object is an unqualified symbol"
        (test-query pattern {:object 'foo} {:keys [dff-result bfs-result]}
          (t/is (m.logic/zero? dff-result))
          (t/is (m.logic/zero? bfs-result))))

      (t/testing "when object is a qualified symbol"
        (test-query pattern {:object 'foo/foo} {:keys [dff-result bfs-result]}
          (t/is (not (m.logic/zero? dff-result)))
          (t/is (not (m.logic/zero? bfs-result))))

        (test-query pattern {:object 'foo/bar} {:keys [dff-result bfs-result]}
          (t/is (m.logic/zero? dff-result))
          (t/is (m.logic/zero? bfs-result))))

      (test-yield pattern {} {:keys [dff-result bfs-result]}
        (t/is (= 'foo/foo (get-object dff-result)))
        (t/is (= ['foo/foo] (get-object bfs-result)))))))

(t/deftest keyword-protocol-satisfaction-test
  (t/testing "With one argument"
    (let [pattern (m.primitive/keyword (m.primitive/is "foo"))]
      (t/testing "when object is an unqualified keyword"
        (test-query pattern {:object :foo} {:keys [dff-result bfs-result]}
          (t/is (not (m.logic/zero? dff-result)))
          (t/is (not (m.logic/zero? bfs-result)))))

      (t/testing "when object is a qualified keyword"
        (test-query pattern {:object :foo/foo} {:keys [dff-result bfs-result]}
          (t/is (not (m.logic/zero? dff-result)))
          (t/is (not (m.logic/zero? bfs-result))))

        (test-query pattern {:object :foo/bar} {:keys [dff-result bfs-result]}
          (t/is (m.logic/zero? dff-result))
          (t/is (m.logic/zero? bfs-result))))

      (test-yield pattern {} {:keys [dff-result bfs-result]}
        (t/is (= :foo (get-object dff-result)))
        (t/is (= [:foo] (get-object bfs-result))))))

  (t/testing "With two arguments"
    (let [pattern (m.primitive/keyword (m.primitive/is "foo") (m.primitive/is "foo"))]
      (t/testing "when object is an unqualified keyword"
        (test-query pattern {:object :foo} {:keys [dff-result bfs-result]}
          (t/is (m.logic/zero? dff-result))
          (t/is (m.logic/zero? bfs-result))))

      (t/testing "when object is a qualified keyword"
        (test-query pattern {:object :foo/foo} {:keys [dff-result bfs-result]}
          (t/is (not (m.logic/zero? dff-result)))
          (t/is (not (m.logic/zero? bfs-result))))

        (test-query pattern {:object :foo/bar} {:keys [dff-result bfs-result]}
          (t/is (m.logic/zero? dff-result))
          (t/is (m.logic/zero? bfs-result))))

      (test-yield pattern {} {:keys [dff-result bfs-result]}
        (t/is (= :foo/foo (get-object dff-result)))
        (t/is (= [:foo/foo] (get-object bfs-result)))))))

(t/deftest cons-protocol-satisfaction-test
  (m.primitive/fresh [?x ?y]
    (let [pattern (m.primitive/cons ?x ?y)]
      (test-query pattern {:object [1]} {:keys [dff-result bfs-result]}
        (t/is (not (m.logic/zero? dff-result)))
        (t/is (= 1 (get-variable dff-result ?x)))
        (t/is (= [] (get-variable dff-result ?y)))

        (t/is (not (m.logic/zero? bfs-result)))
        (t/is (= [1] (get-variable bfs-result ?x)))
        (t/is (= [[]] (get-variable bfs-result ?y))))

      (test-yield pattern {:bindings {?x 1 ?y [2 3]}} {:keys [dff-result bfs-result]}
        (t/is (not (m.logic/zero? dff-result)))
        (t/is (= [1 2 3] (get-object dff-result ?x)))

        (t/is (not (m.logic/zero? bfs-result)))
        (t/is (= [[1 2 3]] (get-object bfs-result ?y)))))))

(t/deftest concat-protocol-satisfaction-test
  (m.primitive/fresh [?x ?y]
    (let [pattern (m.primitive/concat ?x ?y)]
      (t/testing "When object is an empty vector"
        (test-query pattern {:object []} {:keys [dff-result bfs-result]}
          (t/is (not (m.logic/zero? dff-result)))
          (t/is (= [] (get-variable dff-result ?x)))
          (t/is (= [] (get-variable dff-result ?y)))

          (t/is (not (m.logic/zero? bfs-result)))
          (t/is (= [[]] (get-variable bfs-result ?x)))
          (t/is (= [[]] (get-variable bfs-result ?y)))))

      (t/testing "When object is an empty seq"
        (test-query pattern {:object ()} {:keys [dff-result bfs-result]}
          (t/is (not (m.logic/zero? dff-result)))
          (t/is (= () (get-variable dff-result ?x)))
          (t/is (= () (get-variable dff-result ?y)))

          (t/is (not (m.logic/zero? bfs-result)))
          (t/is (= [()] (get-variable bfs-result ?x)))
          (t/is (= [()] (get-variable bfs-result ?y)))))

      (t/testing "When object is a singleton vector"
        (test-query pattern {:object [1]} {:keys [dff-result bfs-result]}
          (t/is (not (m.logic/zero? dff-result)))
          (t/is (= [] (get-variable dff-result ?x)))
          (t/is (= [1] (get-variable dff-result ?y)))

          (t/is (not (m.logic/zero? bfs-result)))
          (t/is (= [[] [1]] (get-variable bfs-result ?x)))
          (t/is (= [[1] []] (get-variable bfs-result ?y)))))

      (test-query pattern {:object [1 2 3]} {:keys [dff-result bfs-result]}
        (t/is (not (m.logic/zero? dff-result)))
        (t/is (= [] (get-variable dff-result ?x)))
        (t/is (= [1 2 3] (get-variable dff-result ?y)))

        (t/is (not (m.logic/zero? bfs-result)))
        (t/is (= [[] [1 2 3] [1 2] [1]] (get-variable bfs-result ?x)))
        (t/is (= [[1 2 3] [] [3] [2 3]] (get-variable bfs-result ?y))))

      (test-yield pattern {:bindings {?x [1] ?y [2 3]}} {:keys [dff-result bfs-result]}
        (t/is (not (m.logic/zero? dff-result)))
        (t/is (= [1 2 3] (get-object dff-result ?x)))

        (t/is (not (m.logic/zero? bfs-result)))
        (t/is (= [[1 2 3]] (get-object bfs-result ?y)))))))

(t/deftest greedy-star-protocol-satisfaction-test
  (t/testing "-query (dff)"
    (m.primitive/fresh [?a ?b]
      (let [object1 [1 1 1 1 2 3 4]
            istate1 (m.state/make {:object object1})
            ilogic1 (m.logic/make-dff istate1)
            pattern (m.primitive/greedy-star ?a ?b)]
        (t/is (not (m.logic/zero? (m.protocols/-query pattern ilogic1))))

        (t/is (= 1
                 (m.state/get-variable (query-unwrap pattern ilogic1) ?a ::unbound)))

        (t/is (= [2 3 4]
                 (m.state/get-variable (query-unwrap pattern ilogic1) ?b ::unbound))))))

  (t/testing "-query (bfs)"
    (m.primitive/fresh [?a ?b]
      (let [object1 [1 1 1 1 2 3 4]
            istate1 (m.state/make {:object object1})
            ilogic1 (m.logic/make-bfs istate1)
            pattern (m.primitive/greedy-star ?a ?b)]
        (t/is (not (m.logic/zero? (m.protocols/-query pattern ilogic1))))

        (t/is (= [1]
                 (map #(m.state/get-variable % ?a ::unbound) (query-unwrap pattern ilogic1))))

        (t/is (= [[2 3 4]]
                 (map #(m.state/get-variable % ?b ::unbound) (query-unwrap pattern ilogic1))))))))

(t/deftest frugal-star-protocol-satisfaction-test
  (t/testing "-query (dff)"
    (m.primitive/fresh [?a ?b]
      (let [object1 [1 1 1 2 3 4]
            istate1 (m.state/make {:object object1})
            ilogic1 (m.logic/make-dff istate1)
            pattern (m.primitive/frugal-star ?a ?b)]
        (t/is (not (m.logic/zero? (m.protocols/-query pattern ilogic1))))

        (t/is (= ::unbound
                 (m.state/get-variable (query-unwrap pattern ilogic1) ?a ::unbound)))

        (t/is (= [1 1 1 2 3 4]
                 (m.state/get-variable (query-unwrap pattern ilogic1) ?b ::unbound))))))

  (t/testing "-query (bfs)"
    (m.primitive/fresh [?a ?b]
      (let [object1 [1 1 1 2 3 4]
            istate1 (m.state/make {:object object1})
            ilogic1 (m.logic/make-bfs istate1)
            pattern (m.primitive/frugal-star ?a ?b)
            result1 (m.protocols/-query pattern ilogic1)]
        (t/is (not (m.logic/zero? (m.protocols/-query pattern ilogic1))))

        (t/is (= [::unbound 1 1 1]
                 (get-variable result1 ?a ::unbound)))

        (t/is (= [[1 1 1 2 3 4] [1 1 2 3 4] [1 2 3 4] [2 3 4]]
                 (get-variable result1 ?b)))))))

(t/deftest hash-set-union-protocol-satisfaction-test
  (m.primitive/fresh [?a ?b]
    (t/testing "Target is empty set"
      (t/testing "dff"
        (let [object0 #{}
              istate0 (m.state/make {:object object0})
              ilogic0 (m.logic/make-dff istate0)
              pattern (m.primitive.hash-set/union ?a ?b)
              result0 (m.protocols/-query pattern ilogic0)]
          (t/is (not (m.logic/zero? result0)))
          (t/is (= #{} (get-variable result0 ?a)))
          (t/is (= #{} (get-variable result0 ?b)))))

      (t/testing "bfs"
        (let [object0 #{}
              istate0 (m.state/make {:object object0})
              ilogic0 (m.logic/make-bfs istate0)
              pattern (m.primitive.hash-set/union ?a ?b)
              result0 (m.protocols/-query pattern ilogic0)]
          (t/is (not (m.logic/zero? result0)))
          (t/is (= [#{}]
                   (get-variable result0 ?a)))
          (t/is (= [#{}]
                   (get-variable result0 ?b))))))

    (t/testing "Target is singleton set"
      (t/testing "dff"
        (let [object0 #{1}
              istate0 (m.state/make {:object object0})
              ilogic0 (m.logic/make-dff istate0)
              pattern (m.primitive.hash-set/union ?a ?b)
              result0 (m.protocols/-query pattern ilogic0)]
          (t/is (not (m.logic/zero? result0)))
          (t/is (= #{1}
                   (get-variable result0 ?a)))
          (t/is (= #{}
                   (get-variable result0 ?b)))))

      (t/testing "bfs"
        (let [object0 #{1}
              istate0 (m.state/make {:object object0})
              ilogic0 (m.logic/make-bfs istate0)
              pattern (m.primitive.hash-set/union ?a ?b)
              result0 (m.protocols/-query pattern ilogic0)]
          (t/is (not (m.logic/zero? result0)))
          (t/is (= [#{1} #{}]
                   (get-variable result0 ?a)))
          (t/is (= [#{} #{1}]
                   (get-variable result0 ?b))))))

    (t/testing "Target is set with two elements"
      (t/testing "dff"
        (let [object0 #{1 2}
              istate0 (m.state/make {:object object0})
              ilogic0 (m.logic/make-dff istate0)
              pattern (m.primitive.hash-set/union ?a ?b)
              result0 (m.protocols/-query pattern ilogic0)]
          (t/is (not (m.logic/zero? result0)))
          (t/is (= #{1 2}
                   (get-variable result0 ?a)))
          (t/is (= #{}
                   (get-variable result0 ?b)))))

      (t/testing "bfs"
        (let [object0 #{1 2}
              istate0 (m.state/make {:object object0})
              ilogic0 (m.logic/make-bfs istate0)
              pattern (m.primitive.hash-set/union ?a ?b)
              result0 (m.protocols/-query pattern ilogic0)]
          (t/is (not (m.logic/zero? result0)))
          (t/is (= [#{1 2} #{} #{1} #{2}]
                   (get-variable result0 ?a)))
          (t/is (= [#{} #{1 2} #{2} #{1}]
                   (get-variable result0 ?b))))))))

(t/deftest with-meta-protocol-satisfaction-test
  (t/testing "dff"
    (t/testing "query"
      (m.primitive/fresh [?a ?b]
        (let [object0 (with-meta {} {:foo "bar"})
              istate0 (m.state/make {:object object0})
              ilogic0 (m.logic/make-dff istate0)
              pattern (m.primitive/with-meta ?a ?b)
              result0 (m.protocols/-query pattern ilogic0)]
          (t/is (= {}
                   (get-variable result0 ?a)))
          (t/is (= {:foo "bar"}
                   (get-variable result0 ?b))))))

    (t/testing "yield"
      (m.primitive/fresh [?a ?b]
        (let [object0 nil
              istate0 (m.state/make {:object object0})
              ilogic0 (m.logic/make-dff istate0)
              pattern (m.primitive/let [?a (m.primitive/is {})
                                        ?b (m.primitive/is {:foo "bar"})]
                        (m.primitive/with-meta ?a ?b))
              result0 (m.protocols/-yield pattern ilogic0)]
          (t/is (= {}
                   (get-variable result0 ?a)))
          (t/is (= {:foo "bar"}
                   (get-variable result0 ?b)))
          (t/is (= {:foo "bar"}
                   (meta (get-object result0)))))))))

;; NOTE: This is still in development.
(t/deftest unbound-protocol-satisfaction-test
  (let [istate_ (m.state/make {})
        ilogic_ (m.logic/make-dff istate_)
        unbound (m.logic/unbound ilogic_)
        istate0 (m.state/set-object istate_ unbound)
        ilogic0 (m.logic/make-dff istate0)
        pattern (m.primitive/unbound)
        result0 (m.protocols/-query pattern ilogic0)]
    (t/is (identical? unbound (get-object result0)))))

(t/deftest variable-protocol-satisfaction-test
  (t/testing "dff"
    (t/testing "query"
      (m.primitive/fresh [?x]
        (let [qsystem (m.primitive/rule
                       (m.primitive/vector (m.primitive/anything) ?x)
                       ?x)
              ysystem (m.primitive/rule
                       ?x
                       (m.primitive/vector ?x ?x))
              ! (var-factory qsystem ysystem)
              !1 (! 1)

              object0 [1 2]
              istate0 (m.state/make {:object object0})
              ilogic0 (m.logic/make-dff istate0)
              pattern (m.primitive/vector !1 !1)
              result0 (m.protocols/-query pattern ilogic0)
              result1 (m.protocols/-yield pattern result0)]

          (t/is (= 2 (get-variable result0 !1)))

          (t/is (= 2 (get-variable result1 !1)))

          (t/is (= [2 2] (get-object result1 !1))))))))

#_
(t/deftest sequence-member-protocol-satisfaction-test
  (t/testing "dff"
    (t/testing "query"
      (m.primitive/fresh [?x]
        (let [object0 []
              istate0 (m.state/make {:object object0})
              ilogic0 (m.logic/make-dff istate0)
              pattern (m.primitive/sequence-member ?x)
              result0 (m.protocols/-query pattern ilogic0)]
          (t/is (not (m.logic/zero? result0))))

        (let [object0 ()
              istate0 (m.state/make {:object object0})
              ilogic0 (m.logic/make-dff istate0)
              pattern (m.primitive/sequence-member ?x)
              result0 (m.protocols/-query pattern ilogic0)]
          (t/is (not (m.logic/zero? result0))))

        (let [object0 1
              istate0 (m.state/make {:object object0})
              ilogic0 (m.logic/make-dff istate0)
              pattern (m.primitive/sequence-member ?x)
              result0 (m.protocols/-query pattern ilogic0)]
          (t/is (m.logic/zero? result0)))))))

(t/deftest vector-protocol-satisfaction-test
  (t/testing "dff"
    (t/testing "query"
      (let [object0 []
            istate0 (m.state/make {:object object0})
            ilogic0 (m.logic/make-dff istate0)
            pattern (m.primitive/vector)
            result0 (m.protocols/-query pattern ilogic0)]
        (t/is (not (m.logic/zero? result0))))

      (m.primitive/fresh [?x]
        (let [object0 [1]
              istate0 (m.state/make {:object object0})
              ilogic0 (m.logic/make-dff istate0)
              pattern (m.primitive/vector ?x)
              result0 (m.protocols/-query pattern ilogic0)]
          (t/is (not (m.logic/zero? result0)))
          (t/is (= 1 (get-variable result0 ?x))))))))
