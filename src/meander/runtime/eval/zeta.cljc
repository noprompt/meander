(ns meander.runtime.eval.zeta
  (:refer-clojure :exclude [test])
  (:require [meander.runtime.eval.breadth-first.zeta :as m.runtime.eval.breadth-first]
            [meander.runtime.eval.depth-first.zeta :as m.runtime.eval.depth-first]))

(defn df-all []
  (m.runtime.eval.depth-first/all))

(defn df-one []
  (m.runtime.eval.depth-first/one))

(defn bf-all []
  (m.runtime.eval.breadth-first/all))

(defn bf-one [])
