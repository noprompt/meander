(ns meander.zeta-test
  (:require [clojure.test :as t]
            [meander.zeta :as m]))


(t/is (m/search 1 _ true))
