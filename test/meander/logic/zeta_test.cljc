(ns meander.logic.zeta-test
  (:require
   [clojure.test :as t]
   [meander.logic.zeta :as m.logic]
   [meander.protocols.zeta :as m.protocols]
   [meander.state.zeta :as m.state]))

(t/deftest dff-protocol-satisfaction-test
  (t/testing "-pass"
    (let [ilogic (m.logic/make-dff (m.state/make {}))
          istate (m.state/make {:object (rand)})]
      (t/is (= istate
               (m.protocols/-unwrap (m.protocols/-pass ilogic istate))))))

  (t/testing "-fail"
    (let [ilogic (m.logic/make-dff (m.state/make {}))
          istate (m.state/make {:object (rand)})]
      (t/is (m.logic/zero?
             (m.protocols/-fail ilogic istate)))
      (t/is (= nil
               (m.protocols/-unwrap (m.protocols/-fail ilogic istate))))))

  (t/testing "-each"
    (let [ilogic (m.logic/make-dff (m.state/make {}))
          istate (m.state/make {:object (rand)})]
      (t/is (= istate
               (m.protocols/-unwrap
                (m.protocols/-each ilogic
                  (fn [_]
                    (m.protocols/-pass ilogic istate))))))

      (t/is (m.logic/zero?
             (m.protocols/-each ilogic
               (fn [istate]
                 (m.protocols/-fail ilogic istate)))))

      (t/is (m.logic/zero?
             (m.protocols/-each (m.protocols/-fail ilogic istate)
               (fn [istate]
                 (m.protocols/-pass ilogic istate)))))

      (t/is (m.logic/zero?
             (m.protocols/-each ilogic
               (fn [istate]
                 (m.protocols/-fail ilogic istate)))))))

  (t/testing "-some"
    (let [istate (m.state/make {})
          ilogic1 (m.logic/make-dff istate)
          ilogic2 (m.protocols/-fail ilogic1 istate)]
      (t/is (= ilogic1
               (m.protocols/-some ilogic1 ilogic1)))

      (t/is (= ilogic1
               (m.protocols/-some ilogic1 ilogic2)))

      (t/is (= ilogic1
               (m.protocols/-some ilogic2 ilogic1)))

      (t/is (= ilogic2
               (m.protocols/-some ilogic2 ilogic2)))))

  (t/testing "-pick"
    (let [istate (m.state/make {})
          ilogic1 (m.logic/make-dff istate)
          ilogic2 (m.protocols/-fail ilogic1 istate)]
      (t/is (= ilogic1
               (m.protocols/-pick ilogic1 ilogic1)))

      (t/is (= ilogic1
               (m.protocols/-pick ilogic1 ilogic2)))

      (t/is (= ilogic1
               (m.protocols/-pick ilogic2 ilogic1)))

      (t/is (= ilogic2
               (m.protocols/-pick ilogic2 ilogic2)))))

  (t/testing "-comp"
    (let [ilogic (m.logic/make-dff (m.state/make {}))]
      (t/is (m.logic/zero?
             (m.protocols/-comp ilogic
               (fn [istate]
                 (m.protocols/-pass ilogic istate)))))

      (t/is (= ilogic
               (m.protocols/-comp ilogic
                 (fn [istate]
                   (m.protocols/-fail ilogic istate))))))))

(t/deftest bfs-protocol-satisfaction-test
  (t/testing "-pass"
    (let [ilogic (m.logic/make-bfs (m.state/make {}))
          istate (m.state/make {:object (rand)})]
      (t/is (= [istate]
               (m.protocols/-unwrap (m.protocols/-pass ilogic istate))))))

  (t/testing "-fail"
    (let [ilogic (m.logic/make-bfs (m.state/make {}))
          istate (m.state/make {:object (rand)})]
      (t/is (m.logic/zero?
             (m.protocols/-fail ilogic istate)))
      (t/is (= []
               (m.protocols/-unwrap (m.protocols/-fail ilogic istate))))))

  (t/testing "-each"
    (let [ilogic (m.logic/make-bfs (m.state/make {}))
          istate (m.state/make {:object (rand)})]
      (t/is (= [istate]
               (m.protocols/-unwrap
                (m.protocols/-each ilogic
                  (fn [_]
                    (m.protocols/-pass ilogic istate))))))

      (t/is (m.logic/zero?
             (m.protocols/-each ilogic
               (fn [istate]
                 (m.protocols/-fail ilogic istate)))))

      (t/is (m.logic/zero?
             (m.protocols/-each (m.protocols/-fail ilogic istate)
               (fn [istate]
                 (m.protocols/-pass ilogic istate)))))

      (t/is (m.logic/zero?
             (m.protocols/-each ilogic
               (fn [istate]
                 (m.protocols/-fail ilogic istate)))))))

  (t/testing "-some"
    (let [istate (m.state/make {})
          ilogic1 (m.logic/make-bfs istate)
          ilogic2 (m.protocols/-fail ilogic1 istate)]
      (t/is (not (m.logic/zero?
                  (m.protocols/-some ilogic1 ilogic1))))

      (t/is (= ilogic1
               (m.protocols/-some ilogic2 ilogic1)))

      (t/is (= ilogic1
               (m.protocols/-some ilogic1 ilogic2)))

      (t/is (= ilogic2
               (m.protocols/-some ilogic2 ilogic2)))))

  (t/testing "-pick"
    (let [istate (m.state/make {})
          ilogic1 (m.logic/make-bfs istate)
          ilogic2 (m.protocols/-fail ilogic1 istate)]
      (t/is (= ilogic1
               (m.protocols/-pick ilogic1 ilogic1)))

      (t/is (= ilogic1
               (m.protocols/-pick ilogic1 ilogic2)))

      (t/is (= ilogic1
               (m.protocols/-pick ilogic2 ilogic1)))

      (t/is (= ilogic2
               (m.protocols/-pick ilogic2 ilogic2)))))

  (t/testing "-comp"
    (let [ilogic (m.logic/make-bfs (m.state/make {}))]
      (t/is (m.logic/zero?
             (m.protocols/-comp ilogic
               (fn [istate]
                 (m.protocols/-pass ilogic istate)))))

      (t/is (not (m.logic/zero?
                  (m.protocols/-comp ilogic
                    (fn [istate]
                      (m.protocols/-fail ilogic istate)))))))))
