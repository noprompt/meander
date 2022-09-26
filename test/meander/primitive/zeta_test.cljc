(ns meander.primitive.zeta-test
  (:require
   [clojure.test :as t]
   [meander.logic.zeta :as m.logic]
   [meander.primitive.zeta :as m.primitive]
   [meander.primitive.hash-set.zeta :as m.primitive.hash-set]
   [meander.primitive.integer.zeta :as m.primitive.integer]
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
  ;; TODO: Test yield.
  (m.primitive/fresh [?a ?b]
    (let [pattern (m.primitive/greedy-star ?a ?b)]
      (test-query pattern {:object [1 1 1 2 3 4]} {:keys [dff-result bfs-result]}
        (t/is (not (m.logic/zero? dff-result)))
        (t/is (= 1 (get-variable dff-result ?a)))
        (t/is (= [2 3 4] (get-variable dff-result ?b)))

        (t/is (not (m.logic/zero? bfs-result)))
        (t/is (= [1] (get-variable bfs-result ?a)))
        (t/is (= [[2 3 4]] (get-variable bfs-result ?b)))))))

(t/deftest frugal-star-protocol-satisfaction-test
  (m.primitive/fresh [?a ?b]
    (let [pattern (m.primitive/frugal-star ?a ?b)]
      (test-query pattern {:object [1 1 1 2 3 4]} {:keys [dff-result bfs-result]}
        (t/is (not (m.logic/zero? dff-result)))
        (t/is (= nil
                 (get-variable dff-result ?a)))
        (t/is (= [1 1 1 2 3 4]
                 (get-variable dff-result ?b)))

        (t/is (not (m.logic/zero? bfs-result)))
        (t/is (= [nil 1 1 1]
                 (get-variable bfs-result ?a)))
        (t/is (= [[1 1 1 2 3 4] [1 1 2 3 4] [1 2 3 4] [2 3 4]]
                 (get-variable bfs-result ?b)))))))

(t/deftest hash-set-union-protocol-satisfaction-test
  (m.primitive/fresh [?a ?b]
    (let [pattern (m.primitive.hash-set/union ?a ?b)]
      (t/testing "Target is empty set"
        (test-query pattern {:object #{}} {:keys [dff-result bfs-result]}
          (t/is (not (m.logic/zero? dff-result)))
          (t/is (= #{} (get-variable dff-result ?a)))
          (t/is (= #{} (get-variable dff-result ?b)))

          (t/is (not (m.logic/zero? bfs-result)))
          (t/is (= [#{}] (get-variable bfs-result ?a)))
          (t/is (= [#{}] (get-variable bfs-result ?b)))))

      (t/testing "Target is singleton set"
        (test-query pattern {:object #{1}} {:keys [dff-result bfs-result]}
          (t/is (not (m.logic/zero? dff-result)))
          (t/is (= #{1} (get-variable dff-result ?a)))
          (t/is (= #{} (get-variable dff-result ?b)))

          (t/is (not (m.logic/zero? bfs-result)))
          (t/is (= [#{1} #{}] (get-variable bfs-result ?a)))
          (t/is (= [#{} #{1}] (get-variable bfs-result ?b)))))

      (t/testing "Target is set with two elements"
        (test-query pattern {:object #{1 2}} {:keys [dff-result bfs-result]}
          (t/is (not (m.logic/zero? dff-result)))
          (t/is (= #{1 2} (get-variable dff-result ?a)))
          (t/is (= #{} (get-variable dff-result ?b)))

          (t/is (not (m.logic/zero? bfs-result)))
          (t/is (= [#{1 2} #{} #{1} #{2}] (get-variable bfs-result ?a)))
          (t/is (= [#{} #{1 2} #{2} #{1}] (get-variable bfs-result ?b))))))))

(t/deftest with-meta-protocol-satisfaction-test
  (m.primitive/fresh [?a ?b]
    (let [pattern (m.primitive/with-meta ?a ?b)]
      (test-query pattern {:object (with-meta {} {:foo "bar"})} {:keys [dff-result bfs-result]}
        (t/is (= {}
                 (get-variable dff-result ?a)))

        (t/is (= {:foo "bar"}
                 (get-variable dff-result ?b)))

        (t/is (= [{}]
                 (get-variable bfs-result ?a)))

        (t/is (= [{:foo "bar"}]
                 (get-variable bfs-result ?b))))

      (test-yield pattern {:bindings {?a {}, ?b {:foo "bar"}}} {:keys [dff-result]}
        (t/is (= {}
                 (get-object dff-result)))

        (t/is (= {:foo "bar"}
                 (meta (get-object dff-result))))))))

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

  (m.primitive/fresh [?x]
    (let [qsystem (m.primitive/rule
                   (m.primitive/vector (m.primitive/anything) ?x)
                   ?x)
          ysystem (m.primitive/rule
                   ?x
                   (m.primitive/vector ?x ?x))
          ;; "Mutable variable"
          * (var-factory qsystem ysystem)
          *1 (* 1)
          pattern (m.primitive/vector *1 *1)]
      (test-query pattern {:object [1 2]} {:keys [dff-result bfs-result]}
        (t/is (= 2 (get-variable dff-result *1)))
        (t/is (= 2 (get-variable dff-result *1)))
        (t/is (= [2] (get-variable bfs-result *1))))

      (test-yield pattern {:bindings {*1 2}} {:keys [dff-result bfs-result]}
        (t/is (= [2 2] (get-object dff-result)))
        (t/is (= [2 2] (get-object dff-result)))))))

(t/deftest vector-protocol-satisfaction-test
  (test-query (m.primitive/vector) {:object []} {:keys [dff-result bfs-result]}
    (t/is (not (m.logic/zero? dff-result)))
    (t/is (not (m.logic/zero? bfs-result))))

  (m.primitive/fresh [?a ?b]
    (test-query (m.primitive/vector ?a ?b) {:object [1 2]} {:keys [dff-result bfs-result]}
      (t/is (= 1 (get-variable dff-result ?a)))
      (t/is (= 2 (get-variable dff-result ?b)))

      (t/is (= [1] (get-variable bfs-result ?a)))
      (t/is (= [2] (get-variable bfs-result ?b))))))

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


;; Integer tests
;; ---------------------------------------------------------------------

(t/deftest integer-min-test
  (let [pattern (m.primitive.integer/min (m.primitive/is 1) (m.primitive/is 3))]
    (test-query pattern {:object 1} {:keys [dff-result bfs-result]}
      (t/is (not (m.logic/zero? dff-result)))
      (t/is (not (m.logic/zero? bfs-result))))

    (test-query pattern {:object 3} {:keys [dff-result bfs-result]}
      (t/is (m.logic/zero? dff-result))
      (t/is (m.logic/zero? bfs-result))))

  ;; ?a is less than or equal to 3
  (m.primitive/fresh [?a]
    (let [pattern (m.primitive/each ?a (m.primitive.integer/min ?a (m.primitive/is 3)))]
      (test-query pattern {:object 1} {:keys [dff-result bfs-result]}
        (t/is (not (m.logic/zero? dff-result)))
        (t/is (not (m.logic/zero? bfs-result))))

      (test-query pattern {:object 3} {:keys [dff-result bfs-result]}
        (t/is (not (m.logic/zero? dff-result)))
        (t/is (not (m.logic/zero? bfs-result))))

      (test-query pattern {:object 4} {:keys [dff-result bfs-result]}
        (t/is (m.logic/zero? dff-result))
        (t/is (m.logic/zero? bfs-result))))))


(t/deftest integer-max-test
  (let [pattern (m.primitive.integer/max (m.primitive/is 1) (m.primitive/is 3))]
    (test-query pattern {:object 3} {:keys [dff-result bfs-result]}
      (t/is (not (m.logic/zero? dff-result)))
      (t/is (not (m.logic/zero? bfs-result))))

    (test-query pattern {:object 1} {:keys [dff-result bfs-result]}
      (t/is (m.logic/zero? dff-result))
      (t/is (m.logic/zero? bfs-result))))

  ;; ?a is greater than or equal to 3
  (m.primitive/fresh [?a]
    (let [pattern (m.primitive/each ?a (m.primitive.integer/max ?a (m.primitive/is 3)))]
      (test-query pattern {:object 4} {:keys [dff-result bfs-result]}
        (t/is (not (m.logic/zero? dff-result)))
        (t/is (not (m.logic/zero? bfs-result))))

      (test-query pattern {:object 3} {:keys [dff-result bfs-result]}
        (t/is (not (m.logic/zero? dff-result)))
        (t/is (not (m.logic/zero? bfs-result))))

      (test-query pattern {:object 1} {:keys [dff-result bfs-result]}
        (t/is (m.logic/zero? dff-result))
        (t/is (m.logic/zero? bfs-result))))))
