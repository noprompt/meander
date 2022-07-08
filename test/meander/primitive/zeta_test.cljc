(ns meander.primitive.zeta-test
  (:require
   [clojure.test :as t]
   [meander.logic.zeta :as m.logic]
   [meander.primitive.zeta :as m.primitive]
   [meander.protocols.zeta :as m.protocols]
   [meander.state.zeta :as m.state]))


(def ^{:arglists '([iquery istate])}
  query-unwrap
  (comp m.protocols/-unwrap m.protocols/-query))

(def ^{:arglists '([iyield istate])}
  yield-unwrap
  (comp m.protocols/-unwrap m.protocols/-yield))

(t/deftest anything-protocol-satisfaction-test
  (t/testing "-query"
    (let [ilogic (m.logic/make-dff (m.state/make {}))]
      (t/is (query-unwrap (m.primitive/anything) ilogic))))

  (t/testing "-yield"
    (let [ilogic (m.logic/make-dff (m.state/make {}))]
      (t/is (yield-unwrap (m.primitive/anything) ilogic)))))

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
