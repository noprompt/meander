(ns meander.state.zeta-test
  (:require
   [clojure.test :as t]
   [meander.protocols.zeta :as m.protocols]
   [meander.state.zeta :as m.state]))

(t/deftest protocol-satisfaction-test
  (t/testing "-get-object"
    (let [object (rand)
          istate (m.state/make {:object object})]
      (t/is (= object (m.protocols/-get-object istate)))))

  (t/testing "-set-object"
    (let [object (rand)
          istate (m.state/make {:object (inc object)})]
      (t/is (= object
               (m.protocols/-get-object 
                (m.protocols/-set-object istate object))))))

  (t/testing "-get-variable"
    (let [istate (m.state/make {})]
      (t/is (= ::unbound
               (m.protocols/-get-variable istate 0 ::unbound)))))

  (t/testing "-set-variable"
    (let [istate (m.state/make {})]
      (t/is (= ::bound
               (m.protocols/-get-variable (m.protocols/-set-variable istate 0 ::bound) 0 ::unbound)))))

  (t/testing "-get-reference"
    (let [istate (m.state/make {})]
      (t/is (= ::unbound
               (m.protocols/-get-reference istate 0 ::unbound)))))

  (t/testing "-set-reference"
    (let [istate (m.state/make {})]
      (t/is (= ::bound
               (m.protocols/-get-reference (m.protocols/-set-reference istate 0 ::bound) 0 ::unbound)))))

  (t/testing "-set-random"
    (let [istate (m.state/make {})
          istate (m.protocols/-set-random istate)
          random (m.protocols/-get-object istate)]
      (t/is (float? random)))))
