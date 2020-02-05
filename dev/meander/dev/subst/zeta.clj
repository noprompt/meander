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

  ;; :empty
  ;; ------

  {:tag :empty}
  []

  ;; :literal
  ;; --------

  {:tag :literal, :form ?form}
  (`m.runtime/constant-generator ('quote ?form))

  ;; :logic-variable
  ;; ---------------

  {:tag :logic-variable :symbol ?symbol}
  (`m.runtime/logic-variable-generator ('quote ?symbol))

  ;; :memory-variable
  ;; ----------------

  {:tag :memory-variable :symbol ?symbol}
  (`m.runtime/memory-variable-generator ('quote ?symbol))

  ;; :root
  ;; -----

  {:tag :root
   :next ?next}
  (me/cata ?next)

  ;; :seq
  ;; ----

  {:tag :seq
   :next ?next}
  (`m.runtime/seq-generator [& (me/cata ?next)])

  ;; :vector
  ;; -------

  {:tag :vector
   :next ?next}
  (`m.runtime/vector-generator (me/cata ?next))

  ;; Not implemented
  ;; ---------------

  ?x ?x)
