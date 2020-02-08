(ns meander.dev.subst.zeta
  (:require [clojure.core :as clj]
            [meander.dev.kernel.zeta :as dev.kernel]
            [meander.dev.parse.zeta :as dev.parse]
            [meander.epsilon :as me]
            [meander.runtime.zeta :as m.runtime]))

(dev.kernel/defmodule generate-compile
  ;; :cat
  ;; ----

  [{:tag :cat :sequence [] :next ?next} ?env]
  (me/cata [?next ?env])

  [{:tag :cat :sequence [?x] :next ?next} ?env]
  (`m.runtime/fmap
   (`clj/fn [xs]
     (`clj/cons (`clj/nth xs 0) (`clj/nth xs 1)))
   (`m.runtime/pair
    (me/cata [?x ?env])
    (me/cata [?next ?env])))

  [{:tag :cat :sequence [?x & ?sequence] :next ?next} ?env]
  (`m.runtime/fmap
   (`clj/fn [xs]
     (`clj/cons (`clj/nth xs 0) (`clj/nth xs 1)))
   (`m.runtime/pair
    (me/cata [?x ?env])
    (me/cata [{:tag :cat :sequence ?sequence :next ?next} ?env])))

  ;; :cata
  ;; -----

  [{:tag :cata :pattern ?pattern} {:cata-symbol ?cata-symbol, :as ?env}]
  (`m.runtime/cata ?cata-symbol (me/cata [?pattern ?env]))

  ;; :empty
  ;; ------

  [{:tag :empty} _]
  (`m.runtime/const [])

  ;; :into
  ;; -----

  [{:tag :into, :memory-variable {:symbol ?symbol}} ?env]
  (`m.runtime/star (`m.runtime/fmap `clj/list (`m.runtime/memory-variable ('quote ?symbol))))
  ;; (`m.runtime/drain ('quote ?symbol))

  ;; :join
  ;; -----

  [{:tag :join :left ?left :right ?right} ?env]
  (`m.runtime/join (me/cata [?left ?env]) (me/cata [?right ?env]))

  ;; :literal
  ;; --------

  [{:tag :literal, :form ?form} _]
  (`m.runtime/const ('quote ?form))

  ;; :logic-variable
  ;; ---------------

  [{:tag :logic-variable :symbol ?symbol} _ ]
  (`m.runtime/logic-variable ('quote ?symbol))

  ;; :memory-variable
  ;; ----------------

  [{:tag :memory-variable :symbol ?symbol} _]
  (`m.runtime/memory-variable ('quote ?symbol))

  ;; :or
  ;; ---

  [{:tag :or, :left ?left, :right ?right} ?env]
  (`m.runtime/choice (me/cata [?left ?env]) (me/cata [?right ?env]))

  ;; :root
  ;; -----

  [{:tag :root :next ?next} ?env]
  (me/cata [?next ?env])

  ;; :seq
  ;; ----

  [{:tag :seq :next ?next} ?env]
  (`m.runtime/fmap `clojure.core/seq (me/cata [?next ?env]))

  ;; :star
  ;; -----

  [{:tag :star :pattern ?pattern :next ?next} ?env]
  (`m.runtime/join
   (`m.runtime/star (me/cata [?pattern ?env]))
   (me/cata [?next ?env]))

  ;; :vector
  ;; -------

  [{:tag :vector :next ?next} ?env]
  (`m.runtime/fmap `clojure.core/vec (me/cata [?next ?env]))

  ;; Not implemented
  ;; ---------------

  ?x ?x)
