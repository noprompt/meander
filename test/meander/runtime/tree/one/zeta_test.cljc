(ns meander.runtime.tree.one.zeta-test
  (:require [clojure.test :as t]
            [meander.tree.zeta :as m.tree]
            [meander.runtime.tree.one.zeta :as m.rt.one]))


(t/deftest set-object-test
  (t/is (= (m.tree/state (m.tree/data 10) (m.tree/bindings))
           (m.rt.one/set-object (m.tree/state (m.tree/data 20) (m.tree/bindings)) (m.tree/data 10)))))
