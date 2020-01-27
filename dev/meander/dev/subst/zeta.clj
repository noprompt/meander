(ns meander.dev.subst.zeta
  (:require [meander.dev.kernel.zeta :as dev.kernel]
            [meander.dev.parse.zeta :as dev.parse]
            [meander.epsilon :as me]
            [meander.runtime.zeta :as m.runtime]))

(dev.kernel/defmodule generate-compile
  ;; :cat
  ;; ----

  {:tag :cat
   :sequence [!xs ...]
   :next ?next}
  [(me/cata !xs) ... & (me/cata ?next)]

  {:tag :empty}
  []

  {:tag :literal, :form ?form}
  (`m.runtime/constant-generator ('quote ?form))

  {:tag :logic-variable :symbol ?symbol}
  (`m.runtime/logic-variable-generator ('quote ?symbol))

  {:tag :memory-variable :symbol ?symbol}
  (`m.runtime/memory-variable-generator ('quote ?symbol))

  {:tag :seq
   :next ?next}
  (`m.runtime/seq-generator [& (me/cata ?next)])

  {:tag :vector
   :next ?next}
  (`m.runtime/vector-generator [& (me/cata ?next)])

  ?x ?x)

;; (< ?x 1)
;; (<
(generate-compile (dev.parse/parse [2 '(3 4 5) 6]))
