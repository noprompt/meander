(ns meander.dev.match.zeta
  (:require [meander.dev.kernel.zeta :as dev.kernel]
            [meander.dev.parse.zeta :as dev.parse]
            [meander.epsilon :as me]
            [meander.runtime.zeta :as m.runtime]))

(me/defsyntax $inc [x]
  `(me/app inc ~x))

(me/defsyntax $dec [x]
  `(me/app dec ~x))

(dev.kernel/defmodule match-compile
  ;; :and
  ;; ----

  [([{:tag :and :left ?left :right ?right :form ?form} ?target] & ?rest) ?env]
  (me/cata [([?left ?target] [?right ?target] & ?rest) ?env])

  ;; :as
  ;; ---

  [([{:tag :as, :pattern ?pattern, :next ?next} ?target] & ?rest) ?env]
  (me/cata [([?pattern ?target] [?next ?target] & ?rest) ?env])

  ;; :cat
  ;; ----

  (me/and [([{:tag :cat, :sequence [!asts ..?n], :next ?next} ?target] & ?rest)
           ?env]
          (me/let [?split-symbol (gensym)
                   ?take-symbol (gensym)
                   ?drop-symbol (gensym)]))
  (`m.runtime/bind [?split-symbol (`m.runtime/-split-at ?target ?n)]
   (let* [?take-symbol (clojure.core/nth ?split-symbol 0)
          ?drop-symbol (clojure.core/nth ?split-symbol 1)]
     ;; Use `nths form (see below).
     (me/cata [`nths [!asts ...] ?take-symbol 0 ?n [([?next ?drop-symbol] & ?rest) ?env]])))

  [`nths _ _ ?n ?n ?state]
  (me/cata ?state)

  (me/and [`nths [?ast . !rest-asts ...] ?target ?m ?n [(!queue ...) ?env]]
          (me/let [?nth-symbol (gensym)]))
  (let* [?nth-symbol (clojure.core/nth ?target ?m)]
    (me/cata [`nths [!rest-asts ...] ?target ($inc ?m) ?n [(!queue ... [?ast ?nth-symbol]) ?env]]))

  ;; :cons
  ;; -----

  [([{:tag :cons, :head ?head, :tail ?tail} [?head-target & ?tail-target]] & ?rest) ?env]
  (me/cata [([?head ?head-target] [?tail ?tail-target] & ?rest) ?env])

  (me/and [([{:tag :cons, :head ?head, :tail ?tail} ?target] & ?rest) ?env]
          (me/let [?head-symbol (gensym)
                   ?tail-symbol (gensym)]))
  (let* [?head-symbol (`m.runtime/head ?target)]
    (if (`m.runtime/fail? ?head-symbol)
      (`m.runtime/fail)
      (let* [?tail-symbol (`m.runtime/tail ?target)]
        (me/cata [([?head ?head-symbol] [?tail ?tail-symbol] & ?rest) ?env]))))

  ;; :empty
  ;; ------

  [([{:tag :empty} []] & ?rest) ?env]
  (me/cata [?rest ?env])

  [([{:tag :empty} ?target] & ?rest) ?env]
  (if (clojure.core/seq ?target)
    (`m.runtime/fail)
    (me/cata [?rest ?env]))

  ;; :entry
  ;; ------

  (me/and [([{:tag :entry,
              :key-pattern {:tag :literal
                            :form ?form}
              :val-pattern ?val,
              :next ?next} ?target]
            & ?rest)
           ?env]
          (me/let [?val-target (gensym)
                   ?next-target (gensym)]))
  (let* [?val-target (clojure.core/get ?target ('quote ?form))
         ?next-target (clojure.core/dissoc ?target ('quote ?form))]
    (me/cata [([?val ?val-target] [?next ?next-target] & ?rest) ?env]))

  (me/and [([{:tag :entry, :key-pattern ?key, :val-pattern ?val, :next ?next} ?target] & ?rest)
           {:state-symbol ?state :as ?env}]
          (me/let [?key-target (gensym)
                   ?val-target (gensym)
                   ?next-target (gensym)]))
  (clojure.core/mapcat
   (fn*
    ([$entry]
     (let [?key-target (clojure.core/key $entry)
           ?val-target (clojure.core/val $entry)
           ?next-target (clojure.core/dissoc ?target ?key-target)]
       (clojure.core/mapcat
        (fn [?state]
          (me/cata [([?val ?val-target] [?next ?next-target] & ?rest) ?env]))
        (me/cata [([?key ?key-target]) ?env])))))
   ?target)

  ;; :into
  ;; -----

  [([{:tag :into
      :memory-variable {:symbol ?symbol}} ?target] & ?rest)
   {:state-symbol ?state
    :as ?env}]
  (`m.runtime/into-memory-variable [?state ('quote ?symbol) ?target]
    (me/cata [?rest ?env]))

  (me/and [([{:tag :join, :left ?left, :right ?right} ?target] & ?rest) ?env]
          (me/let [?partitions-symbol (gensym "partitions__")
                   ?partition-symbol (gensym "partition__")
                   ?left-symbol (gensym "left__")
                   ?right-symbol (gensym "right__")]))
  (let* [?partitions-symbol (`m.runtime/partitions ?target)]
    (clojure.core/mapcat
     (fn*
      ([?partition-symbol]
       (let* [?left-symbol (clojure.core/nth ?partition-symbol 0)
              ?right-symbol (clojure.core/nth ?partition-symbol 1)]
         (me/cata [([?left ?left-symbol] [?right ?right-symbol] & ?rest) ?env]))))
     ?partitions-symbol))

  ;; :literal
  ;; --------

  [([{:tag :literal :form ?form} ?target] & ?rest) ?env]
  (if (= ('quote ?form) ?target)
    (me/cata [?rest ?env])
    (`m.runtime/fail))

  ;; :logic-variable
  ;; ---------------

  [([{:tag :logic-variable :symbol ?symbol} ?target] & ?rest)
   {?key ?symbol
    :state-symbol ?state
    :as ?env}]
  (let* [$value (clojure.core/get ?state ?key)]
    (if (= $value ?target)
      (me/cata [?rest ?env])
      (`m.runtime/fail)))

  [([{:tag :logic-variable :symbol ?symbol} ?target] & ?rest)
   {:state-symbol ?state
    :as ?env}]
  (`m.runtime/bind-logic-variable [?state ('quote ?symbol) ?target]
   (me/cata [?rest {('quote ?symbol) ?symbol & ?env}]))

  ;; :map
  ;; ----

  [([{:tag :map, :next ?next} ?target] & ?rest) ?env]
  (if (clojure.core/map? ?target)
    (me/cata [([?next ?target] & ?rest) ?env])
    (`m.runtime/fail))

  ;; :memory-variable
  ;; ----------------

  [([{:tag :memory-variable :symbol ?symbol} ?target] & ?rest)
   {?key ?symbol
    :state-symbol ?state
    :as ?env}]
  (let* [$value (clojure.core/get ?state ?key)
         $value (clojure.core/conj $value ?target)
         ?state (clojure.core/assoc ?state ?key $value)]
    (me/cata [?rest ?env]))

  [([{:tag :memory-variable :symbol ?symbol} ?target] & ?rest)
   {:state-symbol ?state
    :as ?env}]
  (let* [?state (`m.runtime/bind-memory-variable ?state ('quote ?symbol) ?target)]
    (me/cata [?rest {('quote ?symbol) ?symbol & ?env}]))

  ;; :or
  ;; ----

  [([{:tag :or :left ?left :right ?right :form ?form} ?target] & ?rest) ?env]
  (`clojure.core/concat
   (me/cata [([?right ?target] & ?rest) ?env])
   (me/cata [([?left ?target] & ?rest) ?env]))

  ;; :plus
  ;; -----

  (me/and [([{:tag :plus, :n ?n, :pattern ?pattern, :next ?next} ?target]
            & ?rest)
           {:state-symbol ?state :as ?env}]
          (me/let [?goal-symbol (gensym)
                   ?partitions-symbol (gensym)
                   ?partition-symbol (gensym)
                   ?left-symbol (gensym)
                   ?right-symbol (gensym)
                   ?n-symbol (gensym)]))
  (let* [?goal-symbol
         (fn* ?goal-symbol
              ([$input ?n-symbol ?state]
               (let* [?partitions-symbol (`m.runtime/partitions $input)]
                 (clojure.core/concat
                  (clojure.core/mapcat
                   (fn*
                    ([?partition-symbol]
                     (let* [?left-symbol (clojure.core/nth ?partition-symbol 0)
                            ?right-symbol (clojure.core/nth ?partition-symbol 1)]
                       (clojure.core/mapcat
                        (fn*
                         ([?state]
                          (?goal-symbol ?right-symbol (clojure.core/dec ?n-symbol) ?state)))
                        (me/cata [([?pattern ?left-symbol]) ?env])))))
                   ?partitions-symbol)
                  (if (<= ?n-symbol 0)
                    (me/cata [([?next $input] & ?rest) ?env])
                    (`m.runtime/fail))))))]
    (?goal-symbol ?target ?n ?state))


  ;; :reference
  ;; ----------

  [([{:tag :reference, :symbol ?symbol} ?target] & ?rest)
   {:state-symbol ?state
    :as ?env}]
  (clojure.core/mapcat
   (fn* ([?state] (me/cata [?rest ?env])))
   (?symbol ?target ?state))

  ;; :root
  ;; -----

  [([{:tag :root, :next ?next} ?target] & ?rest)
   {:state-symbol ?state
    :as ?env}]
  (let* [?state {}]
    (me/cata [([?next ?target] & ?rest) ?env]))

  ;; :seq
  ;; ----

  [([{:tag :seq :next ?next} (& _ :as ?target) & ?rest]) ?env]
  (me/cata [([?next ?target] & ?rest) ?env])

  [([{:tag :seq, :next ?next} ?target] & ?rest) ?env]
  (if (clojure.core/seq? ?target)
    (me/cata [([?next ?target] & ?rest) ?env]))

  ;; :slice
  ;; ------

  (me/and [([{:tag :slice, :size ?size, :pattern ?pattern} ?target] & ?rest) ?env]
          (me/let [?take-symbol (gensym)]))
  (`m.runtime/bind [?take-symbol (`m.runtime/-take ?target ?size)]
   (me/cata [([?pattern ?take-symbol] & ?rest) ?env]))

  ;; :some-map
  ;; ---------

  [([{:tag :some-map} _] & ?rest) ?env]
  (me/cata [?rest ?env])

  ;; :star
  ;; -----

  (me/and [([{:tag :star
              :pattern {:tag :cat, :sequence [!xs ..?n]
                        :next {:tag :empty}}
              :next {:tag :empty}} ?target] & ?rest)
           {:state-symbol ?state :as ?env}]
          (me/let [(!nth-symbol ... :as ?nth-symbols) (repeatedly ?n gensym)
                   (!nth-symbol ...) ?nth-symbols
                   (!index ...) (range ?n)
                   ?take-symbol (gensym)
                   ?drop-symbol (gensym)
                   ?goal-symbol (gensym)]))
  (let* [?goal-symbol
         (fn* ?goal-symbol
              ([$input ?state]
               (if (clojure.core/seq $input)
                 (`m.runtime/bind [?take-symbol (`m.runtime/-take $input ?n)]
                  (let* [!nth-symbol (clojure.core/nth ?take-symbol !index) ..?n]
                    (`m.runtime/bind [?drop-symbol (`m.runtime/-drop $input ?n)]
                     (clojure.core/mapcat
                      (fn [?state]
                        (?goal-symbol ?drop-symbol ?state))
                      (me/cata [([!xs !nth-symbol] ..?n) ?env])))))
                 (`m.runtime/succeed ?state))))]
    (clojure.core/mapcat
     (fn [?state]
       (me/cata [?rest ?env]))
     (?goal-symbol ?target ?state)))


  (me/and [([{:tag :star :pattern ?pattern :next ?next} ?target] & ?rest)
           {:state-symbol ?state :as ?env}]
          (me/let [?partitions-symbol (gensym "partitions__")
                   ?partition-symbol (gensym "partition__")
                   ?left-symbol (gensym "left__")
                   ?right-symbol (gensym "right__")
                   ?goal-symbol (gensym "goal__")]))
  (let* [?goal-symbol
         (fn* ?goal-symbol
              ([$input ?state]
               (let* [?partitions-symbol (`m.runtime/partitions $input)]
                 (clojure.core/mapcat
                  (fn*
                   ([?partition-symbol]
                    (let* [?left-symbol (clojure.core/nth ?partition-symbol 0)
                           ?right-symbol (clojure.core/nth ?partition-symbol 1)]
                      (clojure.core/mapcat
                       (fn*
                        ([?state]
                         (`m.runtime/knit
                          [(?goal-symbol ?right-symbol ?state)
                           (me/cata [([?next ?right-symbol] & ?rest) ?env])])))
                       (me/cata [([?pattern ?left-symbol]) ?env])))))
                  ?partitions-symbol))))]
    (?goal-symbol ?target ?state))

  ;; :vector
  ;; -------

  [([{:tag :vector, :next ?next} [& _ :as ?target]] & ?rest) ?env]
  (me/cata [([?next ?target] & ?rest) ?env])

  [([{:tag :vector, :next ?next} ?target] & ?rest) ?env]
  (if (clojure.core/vector? ?target)
    (me/cata [([?next ?target] & ?rest) ?env]))

  ;; :wildcard
  ;; ---------

  [([{:tag :wildcard} ?target] & ?rest) ?env]
  (me/cata [?rest ?env])

  ;; :with
  ;; -----

  [([{:tag :with, :bindings ?bindings :body ?body} ?target] & ?rest) ?env]
  (me/cata [([?bindings ?target] [?body ?target] & ?rest) ?env])

  ;; :with-bindings
  ;; --------------

  [([{:tag :with-bindings,
      :bindings [{:reference {:symbol !symbol} :pattern !pattern}]} ?target] & ?rest)
   {:state-symbol ?state
    :as ?env}]
  (letfn [(!symbol [$input ?state]
            (me/cata [([!pattern $input]) ?env]))
          ...]
    (me/cata [?rest ?env]))

  ;; Success!

  [() {:state-symbol ?state :as ?env}]
  (`m.runtime/succeed ?state)

  ;; Probably not.

  ?x ?x)
