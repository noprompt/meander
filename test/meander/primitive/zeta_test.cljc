(ns meander.primitive.zeta-test
  (:require
   [clojure.test :as t]
   [meander.logic.zeta :as m.logic]
   [meander.primitive.zeta :as m.primitive]
   [meander.primitive.hash-set.zeta :as m.primitive.hash-set]
   [meander.protocols.zeta :as m.protocols]
   [meander.state.zeta :as m.state])
  #?(:clj
     (:import clojure.lang.ExceptionInfo
              meander.logic.dff.zeta.DFFLogic
              meander.logic.bfs.zeta.BFSLogic)))

(def ^{:arglists '([iquery ilogic])}
  query-unwrap
  (comp m.protocols/-unwrap m.protocols/-query))

(def ^{:arglists '([iyield ilogic])}
  yield-unwrap
  (comp m.protocols/-unwrap m.protocols/-yield))

(def ^{:arglists '([iyield ilogic])}
  redex-unwrap
  (comp m.protocols/-unwrap m.protocols/-redex))

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
             (m.protocols/-get-variable istate v unbound))
           ilogic))))

(defn get-object
  ([ilogic]
   (get-object ilogic nil))
  ([ilogic zero]
   (if (m.logic/zero? ilogic)
     zero
     (deref (fmap
             (fn [istate]
               (m.protocols/-get-object istate))
             ilogic)))))

;; Tests

(t/deftest anything-protocol-satisfaction-test
  (t/testing "-query"
    (let [ilogic (m.logic/make-dff (m.state/make {}))]
      (t/is (query-unwrap (m.primitive/anything) ilogic))))

  (t/testing "-yield"
    (let [ilogic (m.logic/make-dff (m.state/make {}))]
      (t/is (yield-unwrap (m.primitive/anything) ilogic)))))

(t/deftest nothing-protocol-satisfaction-test
  (t/testing "-query"
    (let [ilogic (m.logic/make-dff (m.state/make {}))]
      (t/is (not (query-unwrap (m.primitive/nothing) ilogic)))))

  (t/testing "-yield"
    (let [ilogic (m.logic/make-dff (m.state/make {}))]
      (t/is (not (yield-unwrap (m.primitive/nothing) ilogic))))))

(t/deftest is-protocol-satisfaction-test
  (t/testing "-query"
    (let [object (rand)
          istate (m.state/make {:object object})
          ilogic (m.logic/make-dff istate)]
      (t/is (= istate
               (query-unwrap (m.primitive/is object) ilogic)))))

  (t/testing "-yield"
    (let [object (rand)
          istate (m.state/make {:object (inc object)})
          ilogic (m.logic/make-dff istate)]
      (t/is (= (m.protocols/-set-object istate object)
               (yield-unwrap (m.primitive/is object) ilogic))))))

(t/deftest not-protocol-satisfaction-test
  (t/testing "-query"
    (let [ilogic (m.logic/make-dff (m.state/make {}))]
      (t/is (not (query-unwrap (m.primitive/not (m.primitive/anything)) ilogic))))

    (let [object (rand)
          ilogic (m.logic/make-dff (m.state/make {:object object}))]
      (t/is (not (query-unwrap (m.primitive/not (m.primitive/is object)) ilogic))))

    (let [object (rand)
          ilogic (m.logic/make-dff (m.state/make {:object object}))]
      (t/is (query-unwrap (m.primitive/not (m.primitive/is (inc object))) ilogic))))

  (t/testing "-yield"
    (let [ilogic (m.logic/make-dff (m.state/make {}))]
      (t/is (thrown-with-msg? ExceptionInfo #"Not implemented" (m.protocols/-yield (m.primitive/not (m.primitive/anything)) ilogic))))))

(t/deftest some-protocol-satisfaction-test
  (t/testing "-query (dff)"
    (let [object1 (rand)
          object2 (inc object1)
          object3 (inc object2)
          ilogic1 (m.logic/make-dff (m.state/make {:object object1}))
          ilogic2 (m.logic/make-dff (m.state/make {:object object2}))
          ilogic3 (m.logic/make-dff (m.state/make {:object object3}))
          pattern (m.primitive/some (m.primitive/is object1)
                                    (m.primitive/is object2))]
      (t/is (query-unwrap pattern ilogic1))
      (t/is (query-unwrap pattern ilogic2))
      (t/is (not (query-unwrap pattern ilogic3)))))

  (t/testing "-query (bfs)"
    (let [object1 (rand)
          object2 (inc object1)
          object3 (inc object2)
          ilogic1 (m.logic/make-bfs (m.state/make {:object object1}))
          ilogic2 (m.logic/make-bfs (m.state/make {:object object2}))
          ilogic3 (m.logic/make-bfs (m.state/make {:object object3}))
          pattern (m.primitive/some (m.primitive/is object1)
                                    (m.primitive/is object2))]
      (t/is (query-unwrap pattern ilogic1))
      (t/is (query-unwrap pattern ilogic2))
      (t/is (not (seq (query-unwrap pattern ilogic3))))))

  (t/testing "-yield (dff)"
    (let [object1 (rand)
          object2 (inc object1)
          ilogic (m.logic/make-dff (m.state/make {}))
          pattern (m.primitive/some (m.primitive/is object1)
                                    (m.primitive/is object2))]
      (t/is (= object1
               (:object (yield-unwrap pattern ilogic))))))

  (t/testing "-yield (bfs)"
    (let [object1 (rand)
          object2 (inc object1)
          ilogic (m.logic/make-bfs (m.state/make {}))
          pattern (m.primitive/some (m.primitive/is object1)
                                    (m.primitive/is object2))]
      (t/is (= [object1 object2]
               (map :object (yield-unwrap pattern ilogic)))))))

(t/deftest pick-protocol-satisfaction-test
  (t/testing "-query (dff)"
    (let [object1 (rand)
          object2 (inc object1)
          object3 (inc object2)
          ilogic1 (m.logic/make-dff (m.state/make {:object object1}))
          ilogic2 (m.logic/make-dff (m.state/make {:object object2}))
          ilogic3 (m.logic/make-dff (m.state/make {:object object3}))
          pattern (m.primitive/pick (m.primitive/is object1)
                                    (m.primitive/is object2))]
      (t/is (query-unwrap pattern ilogic1))
      (t/is (query-unwrap pattern ilogic2))
      (t/is (not (query-unwrap pattern ilogic3)))))

  (t/testing "-query (bfs)"
    (let [object1 (rand)
          object2 (inc object1)
          object3 (inc object2)
          ilogic1 (m.logic/make-bfs (m.state/make {:object object1}))
          ilogic2 (m.logic/make-bfs (m.state/make {:object object2}))
          ilogic3 (m.logic/make-bfs (m.state/make {:object object3}))
          pattern (m.primitive/pick (m.primitive/is object1)
                                    (m.primitive/is object2))]
      (t/is (query-unwrap pattern ilogic1))
      (t/is (query-unwrap pattern ilogic2))
      (t/is (not (seq (query-unwrap pattern ilogic3))))))

  (t/testing "-yield (dff)"
    (let [object1 (rand)
          object2 (inc object1)
          ilogic (m.logic/make-dff (m.state/make {}))
          pattern (m.primitive/pick (m.primitive/is object1)
                                    (m.primitive/is object2))]
      (t/is (= object1
               (:object (yield-unwrap pattern ilogic))))))

  (t/testing "-yield (bfs)"
    (let [object1 (rand)
          object2 (inc object1)
          ilogic (m.logic/make-bfs (m.state/make {}))
          pattern (m.primitive/pick (m.primitive/is object1)
                                    (m.primitive/is object2))]
      (t/is (= [object1]
               (map :object (yield-unwrap pattern ilogic)))))))

(t/deftest each-protocol-satisfaction-test
  (t/testing "-query (dff)"
    (let [ilogic (m.logic/make-dff (m.state/make {}))]
      (t/is (query-unwrap (m.primitive/each) ilogic))
      (t/is (query-unwrap (m.primitive/each (m.primitive/anything)) ilogic))
      (t/is (query-unwrap (m.primitive/each (m.primitive/anything) (m.primitive/anything)) ilogic))
      (t/is (not (query-unwrap (m.primitive/each (m.primitive/nothing) (m.primitive/anything)) ilogic)))
      (t/is (not (query-unwrap (m.primitive/each (m.primitive/anything) (m.primitive/nothing)) ilogic)))))

  (t/testing "-query (bfs)"
    (let [istate (m.state/make {})
          ilogic (m.logic/make-bfs istate)]
      (t/is (= [istate]
               (query-unwrap (m.primitive/each) ilogic)))
      (t/is (= [istate]
               (query-unwrap (m.primitive/each (m.primitive/anything)) ilogic)))
      (t/is (= [istate]
               (query-unwrap (m.primitive/each (m.primitive/anything) (m.primitive/anything)) ilogic)))
      (t/is (= ()
               (query-unwrap (m.primitive/each (m.primitive/nothing) (m.primitive/anything)) ilogic)))
      (t/is (= ()
               (query-unwrap (m.primitive/each (m.primitive/anything) (m.primitive/nothing)) ilogic)))))

  (t/testing "-yield (dff)"
    (let [ilogic (m.logic/make-dff (m.state/make {}))]
      (t/is (yield-unwrap (m.primitive/each) ilogic))
      (t/is (yield-unwrap (m.primitive/each (m.primitive/anything)) ilogic))
      (t/is (yield-unwrap (m.primitive/each (m.primitive/anything) (m.primitive/anything)) ilogic))
      (t/is (not (yield-unwrap (m.primitive/each (m.primitive/nothing) (m.primitive/anything)) ilogic)))
      (t/is (not (yield-unwrap (m.primitive/each (m.primitive/anything) (m.primitive/nothing)) ilogic)))))

  (t/testing "-yield (bfs)"
    (let [ilogic (m.logic/make-bfs (m.state/make {}))]
      (t/is (seq (yield-unwrap (m.primitive/each) ilogic)))
      (t/is (seq (yield-unwrap (m.primitive/each (m.primitive/anything)) ilogic)))
      (t/is (seq (yield-unwrap (m.primitive/each (m.primitive/anything) (m.primitive/anything)) ilogic)))
      (t/is (not (seq (yield-unwrap (m.primitive/each (m.primitive/nothing) (m.primitive/anything)) ilogic))))
      (t/is (not (seq (yield-unwrap (m.primitive/each (m.primitive/anything) (m.primitive/nothing)) ilogic)))))))

(t/deftest logic-variable-protocol-satisfaction-test
  (t/testing "-query (dff)"
    (m.primitive/fresh [?1]
      (let [object1 (rand)
            object2 (inc object1)
            istate1 (m.state/make {:object object1})
            istate2 (m.protocols/-set-variable istate1 ?1 object2)
            ilogic1 (m.logic/make-dff istate1)
            ilogic2 (m.logic/make-dff istate2)]
        (t/is (= object1
                 (m.protocols/-get-variable (query-unwrap ?1 ilogic1) ?1 ::unbound)))

        (t/is (m.logic/zero?
               (m.protocols/-query ?1 ilogic2))))))

  (t/testing "-query (bfs)"
    (m.primitive/fresh [?1]
      (let [object1 (rand)
            object2 (inc object1)
            istate1 (m.state/make {:object object1})
            istate2 (m.protocols/-set-variable istate1 ?1 object2)
            ilogic1 (m.logic/make-bfs istate1)
            ilogic2 (m.logic/make-bfs istate2)]
        (t/is (= [object1]
                 (map #(m.protocols/-get-variable % ?1 ::unbound)
                      (query-unwrap ?1 ilogic1))))

        (t/is (m.logic/zero?
               (m.protocols/-query ?1 ilogic2))))))

  (t/testing "-yield (dff)"
    (m.primitive/fresh [?1]
      (let [object1 (rand)
            object2 (inc object1)
            istate1 (m.state/make {:object object1})
            istate2 (m.protocols/-set-variable istate1 ?1 object2)
            ilogic1 (m.logic/make-dff istate1)
            ilogic2 (m.logic/make-dff istate2)]
        (t/is (m.logic/zero?
               (m.protocols/-yield ?1 ilogic1)))

        (t/is (= object2
                 (m.protocols/-get-object (yield-unwrap ?1 ilogic2)))))))

  (t/testing "-yield (bfs)"
    (m.primitive/fresh [?1]
      (let [object1 (rand)
            object2 (inc object1)
            istate1 (m.state/make {:object object1})
            istate2 (m.protocols/-set-variable istate1 ?1 object2)
            ilogic1 (m.logic/make-bfs istate1)
            ilogic2 (m.logic/make-bfs istate2)]
        (t/is (m.logic/zero?
               (m.protocols/-yield ?1 ilogic1)))

        (t/is (= [object2]
                 (map m.protocols/-get-object (yield-unwrap ?1 ilogic2))))))))

(t/deftest rule-protocol-satisfaction-test
  (t/testing "-query (dff)"
    (let [object1 (rand)
          object2 (inc object1)
          ilogic1 (m.logic/make-dff (m.state/make {:object object1}))
          ilogic2 (m.logic/make-dff (m.state/make {:object object2}))
          pattern (m.primitive/rule (m.primitive/is object1) (m.primitive/is object2))]
      (t/is (not (m.logic/zero? (m.protocols/-query pattern ilogic1))))
      (t/is (m.logic/zero? (m.protocols/-query pattern ilogic2)))))

  (t/testing "-query (bfs)"
    (let [object1 (rand)
          object2 (inc object1)
          ilogic1 (m.logic/make-bfs (m.state/make {:object object1}))
          ilogic2 (m.logic/make-bfs (m.state/make {:object object2}))
          pattern (m.primitive/rule (m.primitive/is object1) (m.primitive/is object2))]
      (t/is (not (m.logic/zero? (m.protocols/-query pattern ilogic1))))
      (t/is (m.logic/zero? (m.protocols/-query pattern ilogic2)))))

  (t/testing "-yield (dff)"
    (let [object1 (rand)
          object2 (inc object1)
          ilogic1 (m.logic/make-dff (m.state/make {:object object1}))
          pattern (m.primitive/rule (m.primitive/is object1) (m.primitive/is object2))]
      (t/is (= object2
               (m.protocols/-get-object (yield-unwrap pattern ilogic1))))))

  (t/testing "-yield (bfs)"
    (let [object1 (rand)
          object2 (inc object1)
          ilogic1 (m.logic/make-bfs (m.state/make {:object object1}))
          pattern (m.primitive/rule (m.primitive/is object1) (m.primitive/is object2))]
      (t/is (= [object2]
               (map m.protocols/-get-object (yield-unwrap pattern ilogic1))))))

  (t/testing "-redex (dff)"
    (let [object1 (rand)
          object2 (inc object1)
          ilogic1 (m.logic/make-dff (m.state/make {:object object1}))
          pattern (m.primitive/rule (m.primitive/is object1) (m.primitive/is object2))]
      (t/is (= object2
               (m.protocols/-get-object (redex-unwrap pattern ilogic1))))))

  (t/testing "-redex (bfs)"
    (let [object1 (rand)
          object2 (inc object1)
          ilogic1 (m.logic/make-bfs (m.state/make {:object object1}))
          pattern (m.primitive/rule (m.primitive/is object1) (m.primitive/is object2))]
      (t/is (= [object2]
               (map m.protocols/-get-object (redex-unwrap pattern ilogic1)))))))

(t/deftest str-protocol-satisfaction-test
  (t/testing "-query (dff)"
    (m.primitive/fresh [?1 ?2]
      (let [object1 (str)
            object2 (str (rand))
            istate1 (m.state/make {:object object1})
            istate2 (m.state/make {:object object2})
            ilogic1 (m.logic/make-dff istate1)
            ilogic2 (m.logic/make-dff istate2)]
        (t/is (not (m.logic/zero?
                    (m.protocols/-query (m.primitive/str (m.primitive/is object1))
                                        ilogic1))))
        (t/is (not (m.logic/zero?
                    (m.protocols/-query (m.primitive/str (m.primitive/is object2))
                                        ilogic2))))

        (let [result1 (query-unwrap (m.primitive/str ?1 ?2) ilogic1)]
          (t/is (= "" (m.protocols/-get-variable result1 ?1 ::unbound)))
          (t/is (= object1 (m.protocols/-get-variable result1 ?2 ::unbound))))

        (let [result2 (query-unwrap (m.primitive/str ?1 ?2) ilogic2)]
          (t/is (= "" (m.protocols/-get-variable result2 ?1 ::unbound)))
          (t/is (= object2 (m.protocols/-get-variable result2 ?2 ::unbound)))))))

  (t/testing "-yield (dff)"
    (let [object1 "foo"
          object2 "bar"
          istate1 (m.state/make {})
          ilogic1 (m.logic/make-dff istate1)]
      (t/is (= "foobar"
               (m.protocols/-get-object
                (yield-unwrap (m.primitive/str (m.primitive/is object1) (m.primitive/is object2))
                              ilogic1))))))

  (t/testing "-query (bfs)"
    (m.primitive/fresh [?1 ?2]
      (let [object1 "ab"
            istate1 (m.state/make {:object object1})
            ilogic1 (m.logic/make-bfs istate1)
            istates (query-unwrap (m.primitive/str ?1 ?2) ilogic1)
            ?1-vals (map (fn [istate] (m.protocols/-get-variable istate ?1 ::unbound)) istates)
            ?2-vals (map (fn [istate] (m.protocols/-get-variable istate ?2 ::unbound)) istates)]
        (t/is (= ["" "ab" "a"] ?1-vals))
        (t/is (= ["ab" "" "b"] ?2-vals))))))

(t/deftest symbol-protocol-satisfaction-test
  (t/testing "-query (dff)"
    (let [object1 'foo
          object2 'foo/foo
          object3 'foo/bar
          istate1 (m.state/make {:object object1})
          istate2 (m.state/make {:object object2})
          istate3 (m.state/make {:object object3})
          ilogic1 (m.logic/make-dff istate1)
          ilogic2 (m.logic/make-dff istate2)
          ilogic3 (m.logic/make-dff istate3)
          symbol1 (m.primitive/symbol (m.primitive/is "foo"))
          symbol2 (m.primitive/symbol (m.primitive/is "foo") (m.primitive/is "foo"))]
      (t/is (not (m.logic/zero? (m.protocols/-query symbol1 ilogic1))))
      (t/is (not (m.logic/zero? (m.protocols/-query symbol1 ilogic2))))
      (t/is (not (m.logic/zero? (m.protocols/-query symbol2 ilogic2))))
      (t/is (m.logic/zero? (m.protocols/-query symbol2 ilogic3)))
      (t/is (m.logic/zero? (m.protocols/-query symbol2 ilogic3)))))

  (t/testing "-yield (dff)"
    (let [object1 'foo
          object2 'foo/foo
          istate1 (m.state/make {})
          ilogic1 (m.logic/make-dff istate1)
          symbol1 (m.primitive/symbol (m.primitive/is "foo"))
          symbol2 (m.primitive/symbol (m.primitive/is "foo") (m.primitive/is "foo"))]
      (t/is (= object1
               (m.protocols/-get-object (yield-unwrap symbol1 ilogic1))))

      (t/is (= object2
               (m.protocols/-get-object (yield-unwrap symbol2 ilogic1)))))))

(t/deftest keyword-protocol-satisfaction-test
  (t/testing "-query (dff)"
    (let [object1 :foo
          object2 :foo/foo
          object3 :foo/bar
          istate1 (m.state/make {:object object1})
          istate2 (m.state/make {:object object2})
          istate3 (m.state/make {:object object3})
          ilogic1 (m.logic/make-dff istate1)
          ilogic2 (m.logic/make-dff istate2)
          ilogic3 (m.logic/make-dff istate3)
          keyword1 (m.primitive/keyword (m.primitive/is "foo"))
          keyword2 (m.primitive/keyword (m.primitive/is "foo") (m.primitive/is "foo"))]
      (t/is (not (m.logic/zero? (m.protocols/-query keyword1 ilogic1))))
      (t/is (not (m.logic/zero? (m.protocols/-query keyword1 ilogic2))))
      (t/is (not (m.logic/zero? (m.protocols/-query keyword2 ilogic2))))
      (t/is (m.logic/zero? (m.protocols/-query keyword2 ilogic3)))
      (t/is (m.logic/zero? (m.protocols/-query keyword2 ilogic3)))))

  (t/testing "-yield (dff)"
    (let [object1 :foo
          object2 :foo/foo
          istate1 (m.state/make {})
          ilogic1 (m.logic/make-dff istate1)
          keyword1 (m.primitive/keyword (m.primitive/is "foo"))
          keyword2 (m.primitive/keyword (m.primitive/is "foo") (m.primitive/is "foo"))]
      (t/is (= object1
               (m.protocols/-get-object (yield-unwrap keyword1 ilogic1))))

      (t/is (= object2
               (m.protocols/-get-object (yield-unwrap keyword2 ilogic1)))))))

(t/deftest cons-protocol-satisfaction-test
  (t/testing "-query (dff)"
    (let [object1 (list (rand))
          object2 (list (int (first object1)))
          object3 (vec object1)
          istate1 (m.state/make {:object object1})
          ilogic1 (m.logic/make-dff istate1)
          istate2 (m.state/make {:object object2})
          ilogic2 (m.logic/make-dff istate2)
          istate3 (m.state/make {:object object3})
          ilogic3 (m.logic/make-dff istate3)
          cons1 (m.primitive/cons (m.primitive/is (first object1)) (m.primitive/is ()))]
      (t/is (not (m.logic/zero? (m.protocols/-query cons1 ilogic1))))
      (t/is (m.logic/zero? (m.protocols/-query cons1 ilogic2)))
      (t/is (not (m.logic/zero? (m.protocols/-query cons1 ilogic3))))))

  (t/testing "-yield (dff)"
    (let [object1 (rand)
          istate1 (m.state/make {})
          ilogic1 (m.logic/make-dff istate1)
          cons1 (m.primitive/cons (m.primitive/is object1) (m.primitive/is ()))]
      (t/is (= (list object1)
               (m.protocols/-get-object (yield-unwrap cons1 ilogic1)))))))

(t/deftest concat-protocol-satisfaction-test
  (t/testing "-query (bfs)"
    (m.primitive/fresh [?1 ?2]
      (let [object1 (range 3)
            istate1 (m.state/make {:object object1})
            ilogic1 (m.logic/make-bfs istate1)
            pattern (m.primitive/concat ?1 ?2)]
        (t/is (= '(() (0 1 2) (0 1) (0))
                 (map #(m.protocols/-get-variable % ?1 ::unbound)
                      (query-unwrap pattern ilogic1))))

        (t/is (= '((0 1 2) () (2) (1 2))
                 (map #(m.protocols/-get-variable % ?2 ::unbound)
                      (query-unwrap pattern ilogic1)))))))

  (t/testing "-yield (dff)"
    (m.primitive/fresh [?1 ?2]
      (let [object1 (range 3)
            istate1 (m.state/make {:object object1})
            istate1 (m.protocols/-set-variable istate1 ?1 [1 2])
            istate1 (m.protocols/-set-variable istate1 ?2 [3 4])
            ilogic1 (m.logic/make-dff istate1)
            pattern (m.primitive/concat ?1 ?2)]
        (t/is (= '(1 2 3 4)
                 (m.protocols/-get-object (yield-unwrap pattern ilogic1)))))

      (let [object1 (range 3)
            istate1 (m.state/make {:object object1})
            istate1 (m.protocols/-set-variable istate1 ?1 :a)
            istate1 (m.protocols/-set-variable istate1 ?2 [3 4])
            ilogic1 (m.logic/make-dff istate1)
            pattern (m.primitive/concat ?1 ?2)]
        (t/is (m.logic/zero? (m.protocols/-yield pattern ilogic1)))))))

(t/deftest greedy-star-protocol-satisfaction-test
  (t/testing "-query (dff)"
    (m.primitive/fresh [?a ?b]
      (let [object1 [1 1 1 1 2 3 4]
            istate1 (m.state/make {:object object1})
            ilogic1 (m.logic/make-dff istate1)
            pattern (m.primitive/greedy-star ?a ?b)]
        (t/is (not (m.logic/zero? (m.protocols/-query pattern ilogic1))))

        (t/is (= 1
                 (m.protocols/-get-variable (query-unwrap pattern ilogic1) ?a ::unbound)))

        (t/is (= [2 3 4]
                 (m.protocols/-get-variable (query-unwrap pattern ilogic1) ?b ::unbound))))))

  (t/testing "-query (bfs)"
    (m.primitive/fresh [?a ?b]
      (let [object1 [1 1 1 1 2 3 4]
            istate1 (m.state/make {:object object1})
            ilogic1 (m.logic/make-bfs istate1)
            pattern (m.primitive/greedy-star ?a ?b)]
        (t/is (not (m.logic/zero? (m.protocols/-query pattern ilogic1))))

        (t/is (= [1]
                 (map #(m.protocols/-get-variable % ?a ::unbound) (query-unwrap pattern ilogic1))))

        (t/is (= [[2 3 4]]
                 (map #(m.protocols/-get-variable % ?b ::unbound) (query-unwrap pattern ilogic1))))))))

(t/deftest frugal-star-protocol-satisfaction-test
  (t/testing "-query (dff)"
    (m.primitive/fresh [?a ?b]
      (let [object1 [1 1 1 2 3 4]
            istate1 (m.state/make {:object object1})
            ilogic1 (m.logic/make-dff istate1)
            pattern (m.primitive/frugal-star ?a ?b)]
        (t/is (not (m.logic/zero? (m.protocols/-query pattern ilogic1))))

        (t/is (= ::unbound
                 (m.protocols/-get-variable (query-unwrap pattern ilogic1) ?a ::unbound)))

        (t/is (= [1 1 1 2 3 4]
                 (m.protocols/-get-variable (query-unwrap pattern ilogic1) ?b ::unbound))))))

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
        unbound (m.protocols/-unbound ilogic_)
        istate0 (m.protocols/-set-object istate_ unbound)
        ilogic0 (m.logic/make-dff istate0)
        pattern (m.primitive/unbound)
        result0 (m.protocols/-query pattern ilogic0)]
    (t/is (identical? unbound (get-object result0)))))

(t/deftest variable-protocol-satisfaction-test
  (t/testing "dff"
    (t/testing "query"
      (m.primitive/fresh [?x]
        (let [qsystem (m.primitive/forget
                       (m.primitive/rule
                        (m.primitive/vector (m.primitive/anything) ?x)
                        ?x))
              ysystem (m.primitive/forget
                       (m.primitive/rule
                        ?x
                        (m.primitive/vector ?x ?x)))
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
