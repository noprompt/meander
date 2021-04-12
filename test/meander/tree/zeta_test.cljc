(ns meander.tree.zeta-test
  (:require [clojure.test :as t]
            [meander.tree.zeta :as m.tree]))

(t/deftest subnodes-test
  (let [bindings (m.tree/bindings {})
        data (m.tree/data 1)
        state (m.tree/state data bindings)
        pass (m.tree/pass state)]
    (t/is (= [pass state data bindings]
             (m.tree/subnodes pass)))))
