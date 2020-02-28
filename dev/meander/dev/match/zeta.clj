(ns meander.dev.match.zeta
  (:require [clojure.core :as clj]
            [meander.dev.kernel.zeta :as dev.kernel]
            [meander.dev.parse.zeta :as dev.parse]
            [meander.epsilon :as me]
            [meander.runtime.zeta :as m.runtime]))

(me/defsyntax $inc [x]
  `(me/app inc ~x))

(me/defsyntax $dec [x]
  `(me/app dec ~x))

(dev.kernel/defmodule match-compile
  [`mapcat-args (_ [?state] ?body) (`m.runtime/succeed ?state)]
  ?body

  [`mapcat-args ?fn ?coll]
  (clojure.core/or (clojure.core/seq (clojure.core/mapcat ?fn ?coll))
                   (m.runtime/fail))

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
  (`m.runtime/if-result [?split-symbol (`m.runtime/-split-at ?target ?n)]
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

  ;; :cata
  ;; -----

  (me/and [([{:tag :cata, :pattern ?pattern} ?target]  & ?rest)
           {:cata-symbol ?cata-symbol :as ?env}]
          (me/let [?stream (gensym "S__")
                   ?target* (gensym "T__")]))
  (let* [?stream (?cata-symbol ?target)]
    (me/cata
     [`mapcat-args
      (fn [?target*]
        (me/cata [([?pattern ?target*] & ?rest) ?env]))
      ?stream]))


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
          (me/let [?entry (gensym)
                   ?key-target (gensym)
                   ?val-target (gensym)
                   ?next-target (gensym)]))
  (me/cata [`mapcat-args
            (fn [?entry]
              (let [?key-target (clojure.core/key ?entry)
                    ?val-target (clojure.core/val ?entry)
                    ?next-target (clojure.core/dissoc ?target ?key-target)]
                (me/cata
                 [`mapcat-args
                  (fn [?state]
                    (me/cata [([?val ?val-target] [?next ?next-target] & ?rest) ?env]))
                  (me/cata [([?key ?key-target]) ?env])])))
            ?target])

  ;; :fold
  ;; -----

  [([{:tag :fold,
      :variable {:tag :mutable-variable, :symbol ?symbol},
      :initial-value {:form ?initial-value}
      :fold-function {:tag :host-expression, :form ?form}}
     ?target] & ?rest)
   {:state-symbol ?state :as ?env}]
  (me/cata
   [`mapcat-args
    (fn [?state]
      (me/cata [?rest ?env]))
    (`m.runtime/-search (`m.runtime/fold-variable ('quote ?symbol) ?initial-value ?form) ?target ?state)])


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
    (me/cata
     [`mapcat-args
      (fn*
       ([?partition-symbol]
        (let* [?left-symbol (clojure.core/nth ?partition-symbol 0)
               ?right-symbol (clojure.core/nth ?partition-symbol 1)]
          (me/cata [([?left ?left-symbol] [?right ?right-symbol] & ?rest) ?env]))))
      ?partitions-symbol]))

  ;; :let
  ;; ----

  (me/and [([{:tag :let, :pattern ?pattern, :expression {:form ?expression}, :next ?next} ?target] & ?rest) ?env]
          (me/let [?new-target (gensym)]))
  (`clj/let [?new-target ?expression]
   (me/cata [([?pattern ?new-target] [?next ?target] & ?rest) ?env]))

  ;; :literal
  ;; --------

  [([{:tag :literal :form ?form} ?target] & ?rest) ?env]
  (if (= ('quote ?form) ?target)
    (me/cata [?rest ?env])
    (`m.runtime/fail))

  ;; :logical-plus
  ;; -------------

  (me/and [([{:tag :logical-plus, :n {:symbol ?symbol}, :pattern ?pattern, :next ?next} ?target]
            & ?rest)
           {:state-symbol ?state :as ?env}]
          (me/let [?input (gensym)
                   ?state-1 (gensym)
                   ?state-2 (gensym)
                   ?* (gensym "*__")
                   ?? (gensym "?__")
                   ?x (gensym)]))
  (`clj/let [;; Use a fold variable to keep track of number of match times.
             ?* (`m.runtime/fold-variable (gensym) 0 `clj/+)
             ?? (`m.runtime/logic-variable ('quote ?symbol))]
   (`m.runtime/run-star ?state ?target
    (fn [?state-1 ?input]
      (`clj/mapcat
       (fn [?state-1]
         ;; Increment fold variable by 1.
         (`m.runtime/succeed (`m.runtime/bind-variable ?state-1 ?* 1)))
       (me/cata [([?pattern ?input]) {:state-symbol ?state-1 & ?env}])))
    (fn [?state-2 ?input]
      (`clj/let [?x (`clj/get ?state-2 ?*)]
       ;; Unify the value of the fold variable with the logic variable.
       (`m.runtime/if-result [?state-2 (`m.runtime/bind-variable ?state-2 ?? ?x)]
        (me/cata [([?next ?input] & ?rest) {:state-symbol ?state-2 & ?env}]))))))

  ;; :logic-variable
  ;; ---------------

  (me/and [([{:tag :logic-variable :symbol ?symbol} ?target] & ?rest)
           {?key ?symbol
            :state-symbol ?state
            :as ?env}]
          (me/let [?value (gensym)]))
  (let* [?value (clojure.core/get ?state ?key)]
    (if (= ?value ?target)
      (me/cata [?rest ?env])
      (`m.runtime/fail)))

  [([{:tag :logic-variable :symbol ?symbol} ?target] & ?rest)
   {:state-symbol ?state
    :as ?env}]
  (`m.runtime/if-result [?state (`m.runtime/bind-variable ?state (`m.runtime/logic-variable ('quote ?symbol)) ?target)]
   (me/cata [?rest ?env]))

  ;; :map
  ;; ----

  [([{:tag :map, :next ?next} ?target] & ?rest) ?env]
  (if (clojure.core/map? ?target)
    (me/cata [([?next ?target] & ?rest) ?env])
    (`m.runtime/fail))

  ;; :memory-variable
  ;; ----------------

  ;; TODO: Come back to this once memory variable binding is sorted out.
  ;; (me/and [([{:tag :memory-variable :symbol ?symbol} ?target] & ?rest)
  ;;          {?key ?symbol
  ;;           :state-symbol ?state
  ;;           :as ?env}]
  ;;         (me/let [?value (gensym)]))
  ;; (let* [?value (clojure.core/get ?state (`m.runtime/memory-variable ?key))
  ;;        ?value (clojure.core/conj ?value ?target)
  ;;        ?state (clojure.core/assoc ?state (`m.runtime/memory-variable ?key) ?value)]
  ;;   (me/cata [?rest ?env]))

  [([{:tag :memory-variable :symbol ?symbol} ?target] & ?rest)
   {:state-symbol ?state
    :as ?env}]
  (let [?state (`m.runtime/bind-variable ?state (`m.runtime/memory-variable ('quote ?symbol)) [?target])]
   (me/cata [?rest {('quote ?symbol) ?symbol & ?env}]))

  ;; :mutable-variable
  ;; -----------------

  [([{:tag :mutable-variable :symbol ?symbol} ?target] & ?rest)
   {:state-symbol ?state :as ?env}]
  (`clj/let [?state (`m.runtime/bind-variable ?state (`m.runtime/fold-variable ('quote ?symbol) nil `m.runtime/second-argument) ?target)]
   (me/cata [?rest ?env]))

  ;; :not
  ;; ----

  [([{:tag :not :pattern {:tag :not :pattern ?pattern}} ?target] & ?rest) ?env]
  (me/cata [([?pattern ?target] & ?rest) ?env])

  (me/and [([{:tag :not :pattern ?pattern} ?target] & ?rest)
           ?env]
          (me/let [?x (gensym)]))
  (let* [?x (me/cata [([?pattern ?target]) ?env])]
    (if (`m.runtime/fail? ?x)
      (me/cata [?rest ?env])
      (`m.runtime/fail)))

  ;; :or
  ;; ---

  [([{:tag :or :left ?left :right ?right :form ?form} ?target] & ?rest) ?env]
  (`clojure.core/concat
   (me/cata [([?right ?target] & ?rest) ?env])
   (me/cata [([?left ?target] & ?rest) ?env]))

  ;; :plus
  ;; -----

  (me/and [([{:tag :plus, :n ?n, :pattern ?pattern, :next ?next} ?target]
            & ?rest)
           {:state-symbol ?state :as ?env}]
          (me/let [?input (gensym)]))
  (`m.runtime/run-plus ?state ?n ?target
   (fn [?state ?input]
     (me/cata [([?pattern ?input]) ?env]))
   (fn [?state ?input]
     (me/cata [([?next ?input] & ?rest) ?env])))

  ;; :reference
  ;; ----------

  [([{:tag :reference, :symbol ?symbol} ?target] & ?rest)
   {:state-symbol ?state
    :as ?env}]
  (me/cata [`mapcat-args
            (fn [?state] (me/cata [?rest ?env]))
            (?symbol ?target ?state)])

  ;; :rest-map
  ;; ---------

  [([{:tag :rest-map :pattern ?pattern, :next {:tag :some-map}} ?target] & ?rest)
   ?env]
  (me/cata [([?pattern ?target] & ?rest) ?env])

  ;; Prioritize subsequent entries over the rest of the map.
  [([{:tag :rest-map
      :pattern ?pattern
      :next {:tag :entry
             :next ?next
             :as ?entry}}
     ?target] & ?rest)
   ?env]
  (me/cata [([{:next {:tag :rest-map
                      :pattern ?pattern
                      :next ?next}
               :as ?entry} ?target] & ?rest)
            ?env])

  ;; TODO: Not implemented yet. We need to match all possible submaps
  ;; of `?target` against `?pattern` with the rest of `?target` being
  ;; matched against `?next`.
  [([{:tag :rest-map :pattern ?pattern, :next ?next} ?target] & ?rest)
   ?env]
  (`m.runtime/fail)

  ;; :random-symbol
  ;; --------------

  (me/and [([{:tag :random-symbol, :symbol ?symbol} ?target] & ?rest)
           {:state-symbol ?state
            :as ?env}]
          (me/let [?entry (gensym)
                   ?runtime-value (gensym)]))
  (`clj/let [?entry (`clj/find ?state ('quote ?symbol))
             ?runtime-value (if ?entry
                              (`clj/val ?entry)
                              (`clj/gensym))]
   (if (`clj/= ?target ?runtime-value)
     (`clj/let [?state (if ?entry
                         ?state
                         (`clj/assoc ?state ('quote ?symbol) ?runtime-value))]
      (me/cata [?rest ?env]))
     (`m.runtime/fail)))

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
    (me/cata [([?next ?target] & ?rest) ?env])
    (`m.runtime/fail))

  ;; :slice
  ;; ------

  (me/and [([{:tag :slice, :size ?size, :pattern ?pattern} ?target] & ?rest)
           ?env]
          (me/let [?split-symbol (gensym)
                   ?take-symbol (gensym)
                   ?drop-symbol (gensym)]))
  (`m.runtime/if-result [?split-symbol (`m.runtime/-split-at ?target ?size)]
   (let* [?take-symbol (clojure.core/nth ?split-symbol 0)
          ?drop-symbol (clojure.core/nth ?split-symbol 1)]
     (if (seq ?drop-symbol)
       (`m.runtime/fail)
       (me/cata [([?pattern ?take-symbol] & ?rest) ?env]))))

  ;; :some-map
  ;; ---------

  [([{:tag :some-map} _] & ?rest) ?env]
  (me/cata [?rest ?env])

  ;; :star
  ;; -----

  (me/and [([{:tag :star, :pattern ?pattern, :next ?next} ?target]
            & ?rest)
           {:state-symbol ?state :as ?env}]
          (me/let [?input (gensym)]))
  (`m.runtime/run-star ?state ?target
   (fn [?state ?input]
     (me/cata [([?pattern ?input]) ?env]))
   (fn [?state ?input]
     (me/cata [([?next ?input] & ?rest) ?env])))

  ;; :string
  ;; -------

  [([{:tag :string, :next ?next} ?target] & ?rest) ?env]
  (if (clojure.core/string? ?target)
    (me/cata [([?next ?target] & ?rest) ?env])
    (`m.runtime/fail))

  ;; :vector
  ;; -------

  [([{:tag :vector, :next ?next} [& _ :as ?target]] & ?rest) ?env]
  (me/cata [([?next ?target] & ?rest) ?env])

  [([{:tag :vector, :next ?next} ?target] & ?rest) ?env]
  (if (clojure.core/vector? ?target)
    (me/cata [([?next ?target] & ?rest) ?env])
    (`m.runtime/fail))

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
      :bindings [{:reference {:symbol !symbol} :pattern !pattern} ...]} ?target] & ?rest)
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
