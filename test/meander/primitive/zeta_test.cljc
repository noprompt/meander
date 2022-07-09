(ns meander.primitive.zeta-test
  (:require
   [clojure.test :as t]
   [meander.logic.zeta :as m.logic]
   [meander.primitive.zeta :as m.primitive]
   [meander.protocols.zeta :as m.protocols]
   [meander.state.zeta :as m.state])
  #?(:clj
     (:import clojure.lang.ExceptionInfo)))

(def ^{:arglists '([iquery ilogic])}
  query-unwrap
  (comp m.protocols/-unwrap m.protocols/-query))

(def ^{:arglists '([iyield ilogic])}
  yield-unwrap
  (comp m.protocols/-unwrap m.protocols/-yield))

(def ^{:arglists '([iyield ilogic])}
  redex-unwrap
  (comp m.protocols/-unwrap m.protocols/-redex))

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
    (let [ilogic (m.logic/make-bfs (m.state/make {}))]
      (t/is (= ilogic
               (query-unwrap (m.primitive/each) ilogic)))
      (t/is (= ilogic
               (query-unwrap (m.primitive/each (m.primitive/anything)) ilogic)))
      (t/is (= ilogic
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
    (let [?1 (m.primitive/? 1)
          object1 (rand)
          object2 (inc object1)
          istate1 (m.state/make {:object object1})
          istate2 (m.protocols/-set-variable istate1 ?1 object2)
          ilogic1 (m.logic/make-dff istate1)
          ilogic2 (m.logic/make-dff istate2)]
      (t/is (= object1
               (m.protocols/-get-variable (query-unwrap ?1 ilogic1) ?1 ::unbound)))

      (t/is (m.logic/zero?
             (m.protocols/-query ?1 ilogic2)))))

  (t/testing "-query (bfs)"
    (let [?1 (m.primitive/? 1)
          object1 (rand)
          object2 (inc object1)
          istate1 (m.state/make {:object object1})
          istate2 (m.protocols/-set-variable istate1 ?1 object2)
          ilogic1 (m.logic/make-bfs istate1)
          ilogic2 (m.logic/make-bfs istate2)]
      (t/is (= [object1]
               (map #(m.protocols/-get-variable % ?1 ::unbound)
                    (query-unwrap ?1 ilogic1))))

      (t/is (m.logic/zero?
             (m.protocols/-query ?1 ilogic2)))))
  (t/testing "-yield (dff)"
    (let [?1 (m.primitive/? 1)
          object1 (rand)
          object2 (inc object1)
          istate1 (m.state/make {:object object1})
          istate2 (m.protocols/-set-variable istate1 ?1 object2)
          ilogic1 (m.logic/make-dff istate1)
          ilogic2 (m.logic/make-dff istate2)]
      (t/is (m.logic/zero?
             (m.protocols/-yield ?1 ilogic1)))

      (t/is (= object2
               (m.protocols/-get-object (yield-unwrap ?1 ilogic2))))))

  (t/testing "-yield (bfs)"
    (let [?1 (m.primitive/? 1)
          object1 (rand)
          object2 (inc object1)
          istate1 (m.state/make {:object object1})
          istate2 (m.protocols/-set-variable istate1 ?1 object2)
          ilogic1 (m.logic/make-bfs istate1)
          ilogic2 (m.logic/make-bfs istate2)]
      (t/is (m.logic/zero?
             (m.protocols/-yield ?1 ilogic1)))

      (t/is (= [object2]
               (map m.protocols/-get-object (yield-unwrap ?1 ilogic2)))))))

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
