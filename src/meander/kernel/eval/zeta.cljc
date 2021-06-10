(ns meander.kernel.eval.zeta
  (:refer-clojure :exclude [test])
  (:require [meander.kernel.eval.breadth-first.zeta :as m.kernel.eval.breadth-first]
            [meander.kernel.eval.depth-first.zeta :as m.kernel.eval.depth-first]))

(defn df-all []
  (m.kernel.eval.depth-first/all))

(defn df-one []
  (m.kernel.eval.depth-first/one))

(defn bf-all []
  (m.kernel.eval.breadth-first/all))

(defn bf-one [])
