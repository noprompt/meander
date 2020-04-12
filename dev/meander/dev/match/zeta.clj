(ns meander.dev.match.zeta
  (:require [clojure.core :as clj]
            [meander.dev.kernel.zeta :as dev.kernel]
            [meander.dev.parse.zeta :as dev.parse]
            [meander.epsilon :as me] [meander.runtime.zeta :as m.runtime]))

(me/defsyntax $inc [x]
  `(me/app inc ~x))

(me/defsyntax $dec [x]
  `(me/app dec ~x))

(dev.kernel/defmetafn query [bindings body search-space environment])
(dev.kernel/defmetafn succeed [bindings environment])
(dev.kernel/defmetafn search [object target bindings environment])
(dev.kernel/defmetafn make-object [ast environment])

(dev.kernel/defmodule match-compile
  ;; Support rules
  ;; -------------

  (query _ ?body (`m.runtime/succeed ?state) _)
  ?body

  (query ?state-symbol ?body ?space {:return :one})
  (`m.runtime/if-result [?state-symbol ?space]
   ?body)

  (query ?state-symbol ?body ?space _)
  (clojure.core/or
   (clojure.core/seq (clojure.core/mapcat (fn [?state-symbol] ?body) ?space))
   (m.runtime/fail))

  (search ?obj ?target ?state {:return :one})
  ;; TODO: Create a runtime protocol for `find`.
  (nth (`m.runtime/-search ?obj ?target ?state) 0 (`m.runtime/fail))

  (search ?obj ?target ?state _)
  (`m.runtime/-search ?obj ?target ?state)

  (succeed ?bindings {:id ?id :return :one})
  ?bindings

  (succeed ?bindings {:succeed-symbol ?succeed-symbol :id ?id :as ?env})
  (?succeed-symbol ?bindings)

  ;; make-object
  ;; -----------

  (make-object {:tag :and, :left ?left, :right ?right} ?bindings)
  (`m.runtime/andp (make-object ?left ?bindings) (make-object ?right ?bindings))

  (make-object {:tag :literal :form ?form} ?bindings)
  (`m.runtime/const ('quote ?form))

  (me/and (make-object {:tag :logic-variable :symbol ?symbol}
                       {?lv-sym ?symbol
                        :state-symbol ?bindings-symbol})
          (me/let [?result-sym (gensym)]))
  (`m.runtime/if-result [?result-sym (`m.runtime/-constant-value ?lv-sym ?bindings-symbol)]
   (`m.runtime/const ?result-sym)
   ?lv-sym)

  (me/and (make-object {:tag :logic-variable :symbol ?symbol}
                       {:state-symbol ?bindings-symbol})
          (me/let [?lv-sym (gensym)
                   ?result-sym (gensym)]))
  (let [?lv-sym (`m.runtime/logic-variable ('quote ?symbol))]
    (`m.runtime/if-result [?result-sym (`m.runtime/-constant-value ?lv-sym ?bindings-symbol)]
     (`m.runtime/const ?result-sym)
     ?lv-sym))

  (me/and (make-object {:tag :memory-variable :symbol ?symbol}
                       {?mv-sym ?symbol
                        :state-symbol ?bindings-symbol}))
  ?mv-sym

  (make-object {:tag :memory-variable :symbol ?symbol}
               {:state-symbol ?bindings-symbol})
  (`m.runtime/memory-variable ('quote ?symbol))

  (me/and (make-object {:tag :mutable-variable :symbol ?symbol}
                       {?mv-sym ?symbol
                        :state-symbol ?bindings-symbol}))
  ?mv-sym

  (make-object {:tag :mutable-variable :symbol ?symbol}
               {:state-symbol ?bindings-symbol})
  (`m.runtime/mutable-variable ('quote ?symbol))

  (make-object {:tag :or, :left ?left, :right ?right} ?env)
  (`m.runtime/choice (make-object ?left ?env) (make-object ?right ?env))

  (make-object {:tag :meander.math.zeta/+, :left ?left, :right ?right} ?env)
  (`m.runtime/addp (make-object ?left ?env) (make-object ?right ?env))

  (make-object {:tag :meander.math.zeta/-, :left ?left, :right ?right} ?env)
  (`m.runtime/subp (make-object ?left ?env) (make-object ?right ?env))

  (make-object ?x _)
  (throw (ex-info "Missing definition for make-object" ('quote ?x)))

  ;; Primary rules
  ;; -------------

  ;; :and
  ;; ----

  [([{:tag :and :left ?left :right ?right :form ?form} ?target] & ?rest) ?env]
  (me/cata [([?left ?target] [?right ?target] & ?rest) ?env])

  ;; :apply
  ;; ------

  (me/and [([{:tag :apply :fn ?fn :pattern ?pattern} ?target] & ?rest) ?env]
          (me/let [?new-target (gensym)]))
  (`clj/let [?new-target (?fn ?target)]
   (me/cata [([?pattern ?new-target] & ?rest) ?env]))

  ;; :as
  ;; ---

  [([{:tag :as, :pattern ?pattern, :next ?next} ?target] & ?rest) ?env]
  (me/cata [([?pattern ?target] [?next ?target] & ?rest) ?env])

  ;; :cat
  ;; ----

  (me/and [([{:tag (me/or :cat :string-cat), :sequence [!asts ..?n], :next ?next} ?target] & ?rest)
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
    (query ?target* (me/cata [([?pattern ?target*] & ?rest) ?env]) ?stream ?env))

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
  (query ?entry
         (let [?key-target (clojure.core/key ?entry)
               ?val-target (clojure.core/val ?entry)
               ?next-target (clojure.core/dissoc ?target ?key-target)]
           (query ?state
                  (me/cata [([?val ?val-target] [?next ?next-target] & ?rest) ?env])
                  (me/cata [([?key ?key-target]) ?env])
                  ?env))
         ?target
         ?env)

  ;; :fold
  ;; -----

  (me/and [([{:tag :fold,
              :variable {:tag :mutable-variable, :symbol ?symbol :as ?variable-ast},
              :initial-value {:form ?initial-value}
              :fold-function {:tag :host-expression, :form ?form}}
             ?target] & ?rest)
           {:state-symbol ?state :as ?env}]
          (me/let [?variable (gensym)]))
  (let [?variable (make-object ?variable-ast ?env)]
    (query ?state
           (me/cata [?rest ?env])
           (search (`m.runtime/fold ?variable ?initial-value ?form) ?target ?state ?env)
           ?env))

  ;; :group
  ;; ------

  [([{:tag :group, :pattern ?pattern} ?target] & ?rest) ?env]
  (me/cata [([?pattern ?target]  & ?rest) ?env])

  ;; :host-expression
  ;; ----------------

  (me/and [([{:tag :host-expression, :expression ?expression} ?target] & ?rest) ?env]
          (me/let [?x (gensym)]))
  (`clj/let [?x ?expression]
   (if (= ?target ?expression)
     (me/cata [?rest ?env])))

  ;; :into
  ;; -----

  [([{:tag :into
      :memory-variable {:symbol ?symbol}} ?target] & ?rest)
   {?id ?symbol
    :state-symbol ?state
    :as ?env}]
  (`m.runtime/into-memory-variable [?state ?id ?target]
    (me/cata [?rest ?env]))

  (me/and [([{:tag :into
              :memory-variable {:symbol ?symbol}} ?target] & ?rest)
           {:state-symbol ?state
            :as ?env}]
          (me/let [?id (gensym)]))
  (`clj/let [?id (`m.runtime/memory-variable ('quote ?symbol))]
   (`m.runtime/into-memory-variable [?state ?id ?target]
    (me/cata [?rest {?id ?symbol & ?env}])))


  ;; :join
  ;; -----

  (me/and [([{:tag :join, :left ?left, :right ?right} ?target] & ?rest) ?env]
          (me/let [?partitions-symbol (gensym "partitions__")
                   ?partition-symbol (gensym "partition__")
                   ?left-symbol (gensym "left__")
                   ?right-symbol (gensym "right__")]))
  (let* [?partitions-symbol (`m.runtime/partitions ?target)]
    (query ?partition-symbol
           (let* [?left-symbol (clojure.core/nth ?partition-symbol 0)
                  ?right-symbol (clojure.core/nth ?partition-symbol 1)]
             (me/cata [([?left ?left-symbol] [?right ?right-symbol] & ?rest) ?env]))
           ?partitions-symbol
           ?env))

  ;; :keyword
  ;; --------

  (me/and [([{:tag :keyword :name (me/some ?name)} ?target] & ?rest) ?env]
          (me/let [?name-target (gensym)]))
  (if (keyword? ?target)
    (let [?name-target (name ?target)]
      (me/cata [([?name ?name-target] & ?rest) ?env]))
    (`m.runtime/fail))

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

  (me/and [([{:tag :logical-plus, :n ?variable, :pattern ?pattern, :next ?next} ?target]
            & ?rest)
           {:state-symbol ?state :as ?env}]
          (me/let [?input (gensym)
                   ?state-1 (gensym)
                   ?state-2 (gensym)
                   ?fold (gensym)
                   ?* (gensym "*__")
                   ?? (gensym "?__")
                   ?x (gensym)]))
  (`clj/let [;; Use a fold variable to keep track of number of match times.
             ?* (`m.runtime/mutable-variable ('quote ?*))
             ?fold (`m.runtime/fold ?* 0 `clj/+)
             ?? (make-object ?variable ?env)]
   (`m.runtime/run-star ?state ?target
    (fn [?state-1 ?input]
      (`clj/mapcat
       (fn [?state-1]
         ;; Increment fold variable by 1.
         (succeed (`m.runtime/bind-variable ?state-1 ?fold 1) ?env))
       (me/cata [([?pattern ?input]) {:state-symbol ?state-1 & ?env}])))
    (fn [?state-2 ?input]
      (`clj/let [?x (`clj/get ?state-2 ?*)]
       ;; Unify the value of the fold variable with the logic variable.
       (`m.runtime/if-result [?state-2 (`m.runtime/bind-variable ?state-2 ?? ?x)]
        (me/cata [([?next ?input] & ?rest) {:state-symbol ?state-2 & ?env}]))))))

  ;; :logic-variable
  ;; ---------------

  ;; The variable has been previously bound in code (see below).
  (me/and [([{:tag :logic-variable :symbol ?symbol} ?target] & ?rest)
           {?lv-sym ?symbol
            :state-symbol ?bindings
            :as ?env}]
          (me/let [?value (gensym)]))
  (`m.runtime/if-result [?bindings (`m.runtime/bind-variable ?bindings ?lv-sym ?target)]
   (me/cata [?rest ?env]))

  ;; The variable has not been bound.
  (me/and [([{:tag :logic-variable :symbol ?symbol} ?target] & ?rest)
           {:state-symbol ?state
            :as ?env}]
          (me/let [;; Symbol of the variable object.
                   ?lv-sym (gensym "L__")]))
  (let [?lv-sym (`m.runtime/logic-variable ('quote ?symbol))]
    (`m.runtime/if-result [?state (`m.runtime/bind-variable ?state ?lv-sym ?target)]
     (me/cata [?rest {;; Update the environment with the symbol of the
                      ;; variable object as the key and the actual
                      ;; variable symbol as the val.
                      ?lv-sym ?symbol
                      :as ?env}])))

  ;; :map
  ;; ----

  [([{:tag :map, :next ?next} ?target] & ?rest) ?env]
  (if (clojure.core/map? ?target)
    (me/cata [([?next ?target] & ?rest) ?env])
    (`m.runtime/fail))

  ;; :memory-plus
  ;; ------------

  (me/and [([{:tag :memory-plus, :n ?variable, :pattern ?pattern, :next ?next} ?target]
            & ?rest)
           {:state-symbol ?bindings :as ?env}]
          (me/let [?input (gensym)
                   ?bindings-1 (gensym "B__")
                   ?bindings-2 (gensym "B__")
                   ?fold (gensym "F__")
                   ?* (gensym "*__")
                   ?! (gensym "!__")
                   ?x (gensym)]))
  (`clj/let [;; Use a fold variable to keep track of number of match times.
             ?* (`m.runtime/mutable-variable ('quote ?*))
             ?fold (`m.runtime/fold-variable ?* 0 `clj/+)
             ?! (make-object ?variable ?env)]
   (`m.runtime/run-star ?bindings ?target
    (fn [?bindings-1 ?input]
      (`clj/mapcat
       (fn [?bindings-1]
         ;; Increment fold variable by 1.
         (succeed (`m.runtime/bind-variable ?bindings-1 ?fold 1) ?env))
       (me/cata [([?pattern ?input]) {:state-symbol ?bindings-1 & ?env}])))
    (fn [?bindings-2 ?input]
      (`clj/let [?x (`clj/get ?bindings-2 ?*)
                 ?bindings-2 (`m.runtime/bind-variable ?bindings-2 ?! [?x])]
       (me/cata [([?next ?input] & ?rest) {:state-symbol ?bindings-2 & ?env}])))))

  ;; :memory-variable
  ;; ----------------

  (me/and [([{:tag :memory-variable :symbol ?symbol} ?target] & ?rest)
           {?id ?symbol
            :state-symbol ?bindings
            :as ?env}]
          (me/let [?new-bindings (gensym "B__")]))
  (let [?new-bindings (`m.runtime/bind-variable ?bindings ?id [?target])]
   (me/cata [?rest {:state-symbol ?new-bindings & ?env}]))

  (me/and [([{:tag :memory-variable :symbol ?symbol} ?target] & ?rest)
           {:state-symbol ?bindings
            :as ?env}]
          (me/let [?id (gensym "M__")
                   ?new-bindings (gensym "B__")]))
  (let [?id (`m.runtime/memory-variable ('quote ?symbol))
        ?new-bindings (`m.runtime/bind-variable ?bindings ?id [?target])]
   (me/cata [?rest {?id ?symbol :state-symbol ?new-bindings & ?env}]))

  ;; :mutable-variable
  ;; -----------------

  [([{:tag :mutable-variable :symbol ?symbol} ?target] & ?rest)
   {?mv-sym ?symbol
    :state-symbol ?state
    :as ?env}]
  (`clj/let [?state (`m.runtime/bind-variable ?state ?mv-sym ?target)]
   (me/cata [?rest ?env]))
  
  (me/and [([{:tag :mutable-variable :symbol ?symbol} ?target] & ?rest)
           {:state-symbol ?state
            :as ?env}]
          (me/let [?mv-sym (gensym "M__")]))
  (let [?mv-sym (`m.runtime/mutable-variable ('quote ?symbol))]
    (`clj/let [?state (`m.runtime/bind-variable ?state ?mv-sym ?target)]
     (me/cata [?rest {?mv-sym ?symbol
                      & ?env}])))

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

  ;; :pred
  ;; -----

  (me/and [([{:tag :pred
              :pattern ?pattern
              :expression {:tag :host-expression :form ?form}} ?target] & ?rest) ?env]
          (me/let [?pred (gensym)]))
  (let [?pred ?form]
    (if (?pred ?target)
      (me/cata [([?pattern ?target] & ?rest) ?env])
      (`m.runtime/fail)))

  ;; :reference
  ;; ----------

  [([{:tag :reference, :symbol ?symbol} ?target] & ?rest)
   {:state-symbol ?state
    :as ?env}]
  (query ?state
         (me/cata [?rest ?env])
         (?symbol ?target ?state)
         ?env)

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


  ;; :regex
  ;; -------

  (me/and [([{:tag :regex, :regex ?regex :capture (me/some ?capture)} ?target] & ?rest) ?env]
          (me/let [?matches (gensym)]))
  (if (`clj/string? ?target)
    (let [?matches (re-matches ?regex ?target)]
      (if ?matches
        (me/cata [([?capture ?matches] & ?rest) ?env])
        `(m.runtime/fail)))
    (`m.runtime/fail))

  [([{:tag :regex, :regex ?regex} ?target] & ?rest) ?env]
  (if (`clj/string? ?target)
    (if (re-matches ?regex ?target)
      (me/cata [?rest ?env])
      `(m.runtime/fail))
    (`m.runtime/fail))

  ;; :root
  ;; -----

  [([{:tag :root, :next ?next} ?target] & ?rest)
   {:state-symbol ?bindings
    :max-length (me/or (me/and nil (me/let [?max-length 32]))
                       ?max-length)
    :as ?env}]
  (let* [?bindings {:max-length ?max-length}]
    (me/cata [([?next ?target] & ?rest) ?env]))

  ;; :seq
  ;; ----
  [([{:tag :seq, :next ?next} ?target] & ?rest) {?target ('quote & _) :as  ?env}]
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

  (me/and [([{:tag :star, :greedy? true, :pattern ?pattern, :next ?next} ?target]
            & ?rest)
           {:state-symbol ?state :as ?env}]
          (me/let [?input (gensym)]))
  (`m.runtime/run-greedy-star ?state ?target
   (fn [?state ?input]
     (me/cata [([?pattern ?input]) ?env]))
   (fn [?state ?input]
     (me/cata [([?next ?input] & ?rest) ?env])))

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

  ;; :string-join
  ;; ------------

  (me/and [([{:tag :string-join, :left {:tag :literal :type :string :form ?string},:right ?right} ?target] & ?rest)
           ?env]
          (me/let [?target-0 (gensym)
                   ?target-1 (gensym)
                   ?target-2 (gensym)]))
  (`m.runtime/if-result [?target-0 (`m.runtime/-split-at ?target ~(count ?string))]
   (let [?target-1 (`clj/nth ?target-0 0)]
     (if (= ?target-1 ?string)
       (let [?target-2 (`clj/nth ?target-0 1)]
         (me/cata [([?right ?target-2] & ?rest) ?env]))
       (`m.runtime/fail))))

  [([{:tag :string-join, :left ?left, :right {:tag :empty}} ?target] & ?rest) ?env]
  (me/cata [([?left ?target] & ?rest) ?env])

  (me/and [([{:tag :string-join, :left ?left, :right ?right} ?target] & ?rest) ?env]
          (me/let [?partitions-symbol (gensym "partitions__")
                   ?partition-symbol (gensym "partition__")
                   ?left-symbol (gensym "left__")
                   ?right-symbol (gensym "right__")]))
  (let* [?partitions-symbol (`m.runtime/partitions ?target)]
    (query ?partition-symbol
           (let* [?left-symbol (clojure.core/nth ?partition-symbol 0)
                  ?right-symbol (clojure.core/nth ?partition-symbol 1)]
             (me/cata [([?left ?left-symbol] [?right ?right-symbol] & ?rest) ?env]))
           ?partitions-symbol
           ?env))

  ;; :string-star
  ;; ------------

  (me/and [([{:tag :string-star, :pattern {:tag :literal, :type :string, :form ?form}, :next {:tag :empty}} ?target] & ?rest) ?env]
          (me/let [?re (gensym)]))
  (`clj/let [;; TODO: Regex escape ?form
             ?re ~(clojure.core/re-pattern (clojure.core/str "\\A(?:" ?form ")*\\z"))]
   (if (`clj/re-matches ?re ?target)
    (me/cata [?rest ?env])))

  (me/and [([{:tag :string-star, :pattern {:tag :literal, :type :string, :form ?form}, :next ?next} ?target] & ?rest) ?env]
          (me/let [?re (gensym)
                   ?indexes (gensym)
                   ?index (gensym)
                   ?f (gensym)
                   ?next-target (gensym)]))
  (`clj/let [;; TODO: Regex escape ?form
             ?re ~(clojure.core/re-pattern ?form)
             ?indexes (`m.runtime/re-match-index-seq ?re ?target)
             ?f (`clj/fn [?next-target]
                 (me/cata [([?next ?next-target] & ?rest) ?env]))]
   (`clj/concat
    (?f ?target)
    (query ?index
           (`clj/let [?next-target (`clj/subs ?target (`clj/+ ?index ~(count ?form)))]
            (?f ?next-target))
           ?indexes
           ?env)))

  ;; :symbol
  ;; -------

  (me/and [([{:tag :symbol :name (me/some ?name) :namespace (me/some ?namespace) :as-pattern (me/some ?as)}
             ?target] & ?rest) ?env]
   (me/let [?name-target (gensym)
            ?namespace-target (gensym)]))
  (if (symbol? ?target)
    (let [?name-target (name ?target)
          ?namespace-target (namespace ?target)]
      (me/cata [([?as ?target] [?name ?name-target] [?namespace ?namespace-target] & ?rest) ?env]))
    (`m.runtime/fail))

  (me/and [([{:tag :symbol :name (me/some ?name) :namespace (me/some ?namespace)} ?target] & ?rest) ?env]
         (me/let [?name-target (gensym)
                  ?namespace-target (gensym)]))
  (if (symbol? ?target)
    (let [?name-target (name ?target)
          ?namespace-target (namespace ?target)]
      (me/cata [([?name ?name-target] [?namespace ?namespace-target] & ?rest) ?env]))
    (`m.runtime/fail))

  (me/and [([{:tag :symbol :name (me/some ?name)} ?target] & ?rest) ?env]
         (me/let [?name-target (gensym)]))
  (if (symbol? ?target)
    (let [?name-target (name ?target)]
      (me/cata [([?name ?name-target] & ?rest) ?env]))
    (`m.runtime/fail))


  ;; :vector
  ;; -------

  [([{:tag :vector, :next ?next} ?target] & ?rest) {?target [& _] :as ?env}]
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

  (me/and [([{:tag :with-bindings,
              :bindings [{:reference {:symbol !symbol} :pattern !pattern} ...]} ?target] & ?rest)
           {:state-symbol ?state
            :as ?env}]
          (me/let [?input (gensym "X__")]))
  (letfn [(!symbol [?input ?state]
            (me/cata [([!pattern ?input]) ?env]))
          ...]
    (me/cata [?rest ?env]))

  ;; :meander.math.zeta/+
  ;; --------------------

  (me/and [([{:tag :meander.math.zeta/+, :left ?left, :right ?right} ?target] & ?rest)
           {:state-symbol ?bindings
            :as ?env}]
          (me/let [?np-symbol (gensym)
                   ?mp-symbol (gensym)
                   ?bindings-symbol (gensym)
                   ?target-symbol (gensym)]))
  (`clj/let [?np-symbol (make-object ?left ?env)
             ?mp-symbol (make-object ?right ?env)]
   (query ?bindings
          (me/cata [?rest ?env])
          (search (`m.runtime/addp ?np-symbol ?mp-symbol)
                  ?target
                  ?bindings
                  ?env)
          ?env))

  (me/and [([{:tag :meander.math.zeta/-, :left ?left, :right ?right} ?target] & ?rest)
           {:state-symbol ?bindings
            :as ?env}]
          (me/let [?np-symbol (gensym)
                   ?mp-symbol (gensym)
                   ?bindings-symbol (gensym)
                   ?target-symbol (gensym)]))
  (`clj/let [?np-symbol (make-object ?left ?env)
             ?mp-symbol (make-object ?right ?env)]
   (query ?bindings
          (me/cata [?rest ?env])
          (search (`m.runtime/subp ?np-symbol ?mp-symbol)
                  ?target
                  ?bindings
                  ?env)
          ?env))


  ;; Success!

  [() {:state-symbol ?state :as ?env}]
  (succeed ?state ?env)

  ;; Probably not.

  ?x
  (throw (ex-info "No equation for" {:term ('quote ?x)})))

;; Search compilation
;; ------------------

(me/defsyntax indexed [& patterns]
  `(me/and (me/pred seqable?)
           (me/app meander.util.zeta/indexed (~@patterns))))

(dev.kernel/defmetafn ^{:arglists '([[p ...]])}
  flat-concat [xs])

(dev.kernel/defmetafn
  bind-variable [?bindings ?symbol ?variable-db ?target])

(dev.kernel/defmetafn
  ^{:style/indent 1}
  flat-let [bindings body])

(dev.kernel/defmetafn
  expand-n [row n])

(dev.kernel/defmetafn
  expand-nths [row n])

(dev.kernel/defmetafn
  expand-or [row])

(dev.kernel/defmetafn
  with-fact [row fact])

(dev.kernel/defmetafn
  make-case [args])

(dev.kernel/defmetafn
  check [form facts])

(dev.kernel/defmetafn
  add-fact [form facts])

(dev.kernel/defmodule
  ^{:arglists '([[matrix targets bindings]])}
  search-compile
  ;; Support rules
  ;; =============

  ;;

  (bind-variable ?bindings ?symbol #{{:id ?id :symbol ?symbol}} ?target)
  (`m.runtime/bind-variable ?bindings ?id ?target)

  ;;

  (flat-concat [!xs ... (me/or (`concat . !ys ...) (`list) () nil) . !zs ...])
  (flat-concat [!xs ... !ys ... !zs ...])

  (flat-concat []) ()

  (flat-concat [?x]) ?x

  (flat-concat [?x & ?rest])
  (`concat ?x & ?rest)

  ;; let* compilation

  (flat-let [!bindings ...] (let* [!bindings ...] ?body))
  (flat-let [!bindings ...] ?body)

  (flat-let ?bindings ?body)
  (let* ?bindings ?body)

  ;; Fact management

  (add-fact (`= ?target ?x) #{(`= ?target (me/not ?x)) ^:as ?facts})
  ?facts

  (add-fact (`= ?target ?x) #{^& ?facts})
  #{(`= ?target ?x) ^:as ?facts}

  (check (`= ?target ?x) #{(`= ?target (me/not ?x))})
  false

  (check ?form #{?form})
  true

  (check ?form _)
  ?form

  ;; Primary rules
  ;; =============

  ;; The matrix and targets are empty.

  {:matrix [{:cells [] :succeed-symbol !succeed} ...]
   :targets []
   :bindings ?bindings}
  (flat-concat [(!succeed ?bindings) ...])

  {:matrix []}
  ()

  ;; :wildcard
  ;; ---------

  (expand-n {:cells [{:tag :wildcard :as ?ast} & ?rest-cells] :as ?row} ?n)
  {:cells [?ast ..?n & ?rest-cells] :as ?row}

  (me/with [%row {:cells [{:tag :wildcard} & !rest-cells] :as !row}]
    {:matrix [%row . (me/or %row !not-row)]
     :targets [?target & ?rest-targets]
     :bindings ?bindings
     :as ?state})
  (flat-concat [(me/cata {:matrix [{:cells !rest-cells :as !row} ...]
                          :targets ?rest-targets
                          :bindings ?bindings
                          :as ?state})
                (me/cata {:matrix [!not-row ...]
                          :as ?state})])

  ;; :literal
  ;; --------

  (me/with [%row {:cells [{:tag :literal, :form ?form} & !rest-cells]
                  :as !row}]
    {:matrix [%row . (me/or %row !not-row) ...]
     :targets [?target & ?rest-targets]
     :facts #{^& ?facts}
     :as ?state})
  (flat-concat [(if (check (`= ?target ('quote ?form)) ?facts)
                  (me/cata {:matrix [{:cells [& !rest-cells] :as !row} ...]
                            :targets ?rest-targets
                            :facts (add-fact (`= ?target ('quote ?form)) ?facts)
                            :as ?state}))
                (me/cata {:matrix [!not-row  ...]
                          :as ?state})])

  ;; :keyword
  ;; --------

  (expand-n {:cells [{:tag :keyword, :namespace ?namespace, :name ?name} & ?rest-cells]
             :as ?row}
            2)
  {:cells [?namespace ?name & ?rest-cells] :as ?row}

  (me/with [%keyword-row {:cells [{:tag :keyword} & ?rest-cells] :as !row}
            %wildcard-row {:cells [{:tag :wildcard} & ?rest-cells] :as !row}]
    (me/and {:matrix [%keyword-row . (me/or %keyword-row %wildcard-row !not-row) ...]
             :targets [?target & ?rest-targets]
             :facts #{^& ?facts}
             :as ?state}
            (me/let [?name (gensym "name__")
                     ?namespace (gensym "namespace__")])))
  (flat-concat
   [(if (check (`clj/keyword? ?target) ?facts)
      (flat-let [?namespace (`clj/namespace ?target)
                 ?name (`clj/name ?target)]
        (me/cata {:matrix [(expand-n !row 2) ...]
                  :targets [?namespace ?name & ?rest-targets]
                  :facts #{(`clj/keyword? ?target)
                           (`clj/string? ?name)
                           ^& ?facts}
                  :as ?state}))
      (`m.runtime/fail))
    (me/cata {:matrix [!not-row ...]
              :as ?state})])

  ;; :symbol
  ;; --------

  (expand-n {:cells [{:tag :symbol, :namespace ?namespace, :name ?name} & ?rest-cells]
             :as ?row}
            2)
  {:cells [?namespace ?name & ?rest-cells] :as ?row}

  (me/with [%symbol-row {:cells [{:tag :symbol} & ?rest-cells] :as !row}
            %wildcard-row {:cells [{:tag :wildcard} & ?rest-cells] :as !row}]
    (me/and {:matrix [%symbol-row . (me/or %symbol-row %wildcard-row !not-row) ...]
             :targets [?target & ?rest-targets]
             :facts #{^& ?facts}
             :as ?state}
            (me/let [?name (gensym "name__")
                     ?namespace (gensym "namespace__")])))
  (flat-concat
   [(if (check (`clj/symbol? ?target) ?facts)
      (flat-let [?namespace (`clj/namespace ?target)
                 ?name (`clj/name ?target)]
        (me/cata {:matrix [(expand-n !row 2) ...]
                  :targets [?namespace ?name & ?rest-targets]
                  :facts #{(`clj/symbol? ?target)
                           (`clj/string? ?name)
                           ^& ?facts}
                  :as ?state}))
      (`m.runtime/fail))
    (me/cata {:matrix [!not-row ...]
              :as ?state})])

  ;; :as
  ;; ---

  (expand-n {:cells [{:tag :as, :pattern ?pattern, :next ?next} & ?rest-cells] :as ?row} 2)
  {:cells [?pattern ?next & ?rest-cells] :as ?row}

  (me/with [%as {:tag :as}
            %wildcard {:tag :wildcard}
            %head-row {:cells [%as & !rest-cells] :as !row}
            %tail-row {:cells [(me/or %as %wildcard) & !rest-cells]
                       :as !row}]
    {:matrix [%head-row . (me/or %tail-row !not-row) ...]
     :target [?target & ?rest-targets :as ?targets]
     :as ?state})
  (flat-concat
   [(me/cata {:matrix [(expand-n !row 2) ...]
              :targets [?target ?target & !rest-cells]
              :as ?state})
    (me/cata {:matrix [!not-row ...]
              :as ?state})])

  ;; :and
  ;; ----

  (expand-n {:cells [{:tag :and, :left ?left, :right ?right} & ?rest-cells] :as ?row} 2)
  {:cells [?left ?right & ?rest-cells] :as ?row}

  (me/with [%and {:tag :and}
            %wildcard {:tag :wildcard}
            %head-row {:cells [%and & _] :as !row}
            %tail-row {:cells [(me/or %and %wildcard) & _]
                       :as !row}]
    {:matrix [%head-row . (me/or %tail-row !not-row) ...]
     :targets [?target & ?rest-targets]
     :as ?state})
  (flat-concat
   [(me/cata {:matrix [(expand-n !row 2) ...]
              :targets [?target ?target & ?rest-targets]
              :as ?state})
    (me/cata {:matrix [!not-row ...]
              :as ?state})])

  ;; :or
  ;; ----

  (me/with [%or-row (me/and {:cells [{:tag :or, :left !ast, :right !ast} & (me/and !rest-cells !rest-cells)]}
                            !row
                            !row)
            %wildcard-row (me/and {:cells [(me/and {:tag :wild-card} !ast !ast)
                                           & (me/and !rest-cells !rest-cells)]}
                                  !row
                                  !row)]
   {:matrix [%or-row . (me/or %or-row %wildcard-row !not-row) ...]
    :state ?state})
  (flat-concat
   [(me/cata {:matrix [{:cells [!ast & !rest-cells] :as !row} ...]
              :as ?state})
    (me/cata {:matrix [!not-row ...]
              :as ?state})])

  ;; :empty
  ;; ------

  (me/with [%row {:cells [{:tag :empty} & !rest-cells] :as !row}]
    {:matrix [%row . (me/or %row !not-row) ...]
     :targets [?target & ?rest-targets]
     :as ?state})
  (flat-concat
   [(if (`seq ?target)
      ()
      (me/cata {:matrix [{:cells !rest-cells :as !row} ...]
                :targets ?rest-targets
                :as ?state}))
    (me/cata {:matrix [!not-row ...]
              :as ?state})])

  ;; :memory-variable
  ;; ---------------

  (me/with [%row {:cells [{:tag :memory-variable, :symbol ?symbol} & ?rest-cells]
                  :variable-db ?variable-db
                  :as ?row}]
    (me/and {:matrix [%row & ?rest-rows]
             :targets [?target & ?rest-targets :as ?targets]
             :bindings ?bindings
             :as ?state}
            ;; Symbol for the updated bindings.
            (me/let [?new-bindings (gensym)])))
  (flat-concat
   [(flat-let [?new-bindings (bind-variable ?bindings ?symbol ?variable-db ?target)]
      (me/cata {:matrix [{:cells ?rest-cells :as ?row}]
                :targets ?rest-targets
                :bindings ?new-bindings
                :as ?state}))
    (me/cata {:matrix ?rest-rows
              :as ?state})])

  ;; :mutable-variable
  ;; ------------------

  (me/with [%row {:cells [{:tag :mutable-variable, :symbol ?symbol} & ?rest-cells]
                  :variable-db ?variable-db
                  :as ?row}]
    (me/and {:matrix [%row & ?rest-rows]
             :targets [?target & ?rest-targets]
             :bindings ?bindings
             :as ?state}
            ;; Symbol for the updated bindings.
            (me/let [?new-bindings (gensym)])))
  (flat-concat
   [(flat-let [?new-bindings (bind-variable ?bindings ?symbol ?variable-db ?target)]
      (me/cata {:matrix [{:cells ?rest-cells :as ?row}]
                :targets ?rest-targets
                :bindings ?new-bindings
                :as ?state}))
    (me/cata {:matrix ?rest-rows
              :as ?state})])

  ;; :logic-variable
  ;; ---------------

  (me/with [%row {:cells [{:tag :logic-variable, :symbol ?symbol} & ?rest-cells]
                  :variable-db ?variable-db
                  :as ?row}]
    (me/and {:matrix [%row & ?rest-rows]
             :targets [?target & ?rest-targets]
             :bindings ?bindings
             :as ?state}
            ;; Symbol for the updated bindings.
            (me/let [?new-bindings (gensym)])))
  (flat-concat
   [(`m.runtime/if-result [?new-bindings (bind-variable ?bindings ?symbol ?variable-db ?target)]
     (me/cata {:matrix [{:cells ?rest-cells :as ?row}]
               :targets ?rest-targets
               :bindings ?new-bindings
               :as ?state}))
    (me/cata {:matrix ?rest-rows
              :as ?state})])

  ;; :seq
  ;; ----

  (expand-n {:cells [{:tag :seq :next ?next} & ?rest-cells] :as ?row} _)
  {:cells [?next & ?rest-cells] :as ?row}

  (me/with [%row {:cells [{:tag :seq} & _] :as !row}]
    {:matrix [%row . (me/or %row !not-row) ...]
     :targets [?target & _ :as ?targets]
     :facts #{^& ?facts}
     :as ?state})
  (flat-concat [(if (check (`seq? ?target) ?facts)
                  (me/cata {:matrix [(expand-n !row 1) ...]
                            :targets ?targets
                            :facts #{(`seq? target) ^& ?facts}
                            :as ?state}))
                (me/cata {:matrix [!not-row ...]
                          :targets ?targets
                          :as ?state})])

  ;; :vector
  ;; -------

  (expand-n {:cells [{:tag :vector :next ?next} & ?rest-cells] :as ?row} _)
  {:cells [?next & ?rest-cells] :as ?row}

  (me/with [%row {:cells [{:tag :vector} & _] :as !row}]
    {:matrix [%row . (me/or %row !not-row) ...]
     :targets [?target & _ :as ?targets]
     :facts #{^& ?facts}
     :as ?state})
  (flat-concat [(if (check (`vector? ?target) ?facts)
                  (me/cata {:matrix [(expand-n !row 1) ...]
                            :facts #{(`vector? ?target) ^& ?facts}
                            :as ?state}))
                (me/cata {:matrix [!not-row ...]
                          :as ?state})])

  ;; :cat
  ;; ----

  (expand-nths {:cells [{:tag :cat :sequence (indexed [!index !ast] ..?n) :next ?next} & ?rest-cells] :as ?row} ?n)
  {:cells [{:tag :nth :index !index :pattern !ast} ..?n ?next & ?rest-cells] :as ?row}

  (me/and (expand-nths {:cells [{:tag :wildcard} & ?rest-cells] :as ?row} ?n)
          (me/let [(!index ..?n & _) (range)]))
  {:cells [{:tag :nth :index !index :pattern {:tag :wildcard}} ..?n {:tag :wildcard} & ?rest-cells] :as ?row}

  (me/with [%cat-row {:cells [{:tag :cat :sequence [_ ..?n]} & _] :as !row}
            %wildcard-row {:cells [{:tag :wildcard} & _] :as !row}]
    (me/and {:matrix [%cat-row . (me/or %cat-row wildcard-row !not-row) ...]
             :targets [?target & ?rest-targets :as ?targets]
             :as ?state}
            (me/let [?result (gensym "R__")
                     ?left (gensym "L__")
                     ?right (gensym "R__")])))
  (flat-concat
   [(`m.runtime/if-result [?result (`m.runtime/-split-at ?target ?n)]
     (flat-let [?left (nth ?result 0)
                ?right (nth ?result 1)]
       (me/cata {:matrix [(expand-nths !row ?n) ...]
                 :targets [?left ..?n ?right & ?rest-targets]
                 :as ?state})))
    (me/cata {:matrix [!not-row ...]
              :as ?state})])

  ;; :nth
  ;; ----

  (me/with [%row {:cells [{:tag :nth :index ?i, :pattern !pattern} & !rest-cells]
                  :as !row}
            %wildcard-row {:cells [{:tag :wildcard :as !pattern} & !rest-cells]
                           :as !row}]
    (me/and {:matrix [%row . (me/or %row %wildcard-row !not-row) ...]
             :targets [?target . !rest-targets ... :as ?targets]
             :as ?state}
            (me/let [?x (gensym "X__")])))
  (flat-concat
   [(flat-let [?x (`nth ?target ?i)]
              (me/cata {:matrix [{:cells (me/app conj !rest-cells !pattern) :as !row} ...]
                        :targets [!rest-targets ... ?x]
                        :as ?state}))
    (me/cata {:matrix [!not-row ...]
              :as ?state})])

  ;; :join
  ;; -----

  (expand-n {:cells [{:tag :join :left ?left :right ?right} & ?rest-cells] :as ?row} 2)
  {:cells [?left ?right & ?rest-cells] :as ?row}

  (me/with [%join-row {:cells [{:tag :join} & _] :as !row}
            %wildcard-row {:cells [{:tag :wildcard} & _] :as !row}]
    (me/and {:matrix [%join-row . (me/or %join-row !not-row) ...]
             :targets [?target & ?rest-targets :as ?targets]
             :as ?state}
            (me/let [?partitions (gensym "partitions__")
                     ?partition (gensym "partition__")
                     ?left (gensym "left__")
                     ?right (gensym "right__")])))
  (flat-concat
   [(let* [?partitions (`m.runtime/partitions ?target)]
      (`mapcat
       (fn [?partition]
         (flat-let [?left (`clj/nth ?partition 0)
                    ?right (`clj/nth ?partition 1)]
           (me/cata {:matrix [(expand-n !row 2) ...]
                     :targets [?left ?right & ?rest-targets]
                     :as ?state})))
       ?partitions))
    (me/cata {:matrix [!not-row ...]
              :as ?state})])

  ;; :apply
  ;; ------

  (expand-n {:cells [{:tag :apply :pattern ?pattern} & ?rest-row] :as ?row} 1)
  {:cells [?pattern & ?rest-row] :as ?row}

  (me/with [%apply-row {:cells [{:tag :apply :fn ?fn} & _]
                        :as !row}
            %wildcard-row {:cells [{:tag :wildcard} & _]
                           :as !row}]
    (me/and {:matrix [%apply-row . (me/or %apply-row %wildcard-row !not-row) ...]
             :targets [?target & ?rest-targets :as ?targets]
             :as ?state}
            (me/let [?x (gensym "X__")])))
  (flat-concat [(let* [?x (?fn ?target)]
                  (me/cata {:matrix [(expand-n !row 1) ...]
                            :targets [?x & ?rest-targets]
                            :as ?state}))
                (me/cata {:matrix [!not-row ...]
                          :as ?state})])

  ;; :pred
  ;; -----

  (me/with [%row {:cells [{:tag :pred
                           :expression {:tag :host-expression :form ?form}
                           :pattern !pattern}
                          & !rest-cells]
                  :as !row}]
    (me/and {:matrix [%row . (me/or %row !not-row) ...]
             :targets [?target & _ :as ?targets]
             :facts #{^& ?facts}
             :as ?state}
            (me/let [?pred (gensym "pred__")])))
  (flat-concat
   [(let* [?pred ?form]
      (if (check (?pred ?target) ?facts)
        (me/cata {:matrix [{:cells [!pattern & !rest-cells]
                            :facts #{(?pred ?target) ^& ?facts}
                            :as !row}]})))
    (me/cata {:matrix [!not-row ...]
              :as ?state})])

  ;; :cata
  ;; -----

  (me/and {:matrix [{:cells [{:tag :cata, :pattern ?pattern} & ?rest-cells]
                     :cata-symbol ?cata-symbol
                     :as ?row}
                    & ?rest-rows]
           :targets [?target & ?rest-targets]
           :bindings ?bindings
           :as ?state}
          (me/let [?new-bindings (gensym "B__")]))
  (flat-concat [(`mapcat
                 (fn [?bindings]
                   (me/cata {:matrix [{:cells ?rest-cells :as ?row}]
                             :targets ?rest-targets
                             :as ?state}))
                 (?cata-symbol ?target))
                (me/cata {:matrix ?rest-rows
                          :as ?state})])

  ;; :with
  ;; -----

  (me/and {:matrix [{:cells [{:tag :with
                              :bindings {:tag :with-bindings
                                         :bindings [{:reference {:symbol !symbol}
                                                     :pattern !pattern} ...]}
                              :body ?body} & ?rest-cells]
                     :as ?row}
                    & ?rest-rows]
           :targets [?target & _]
           :as ?state}
          (me/let [?input (gensym "X__")
                   ?new-bindings (gensym "B__")]))
  (flat-concat
   [(`letfn [(!symbol [?input ?new-bindings]
                      (me/cata {:matrix [{:cells [!pattern]
                                          :succeed-symbol `m.runtime/succeed
                                          :as ?row}]
                                :targets [?input]
                                :bindings ?new-bindings
                                :as ?state}))
             ...]
     (me/cata {:matrix [{:cells [?body & ?rest-cells]
                         :as ?row}]
               :as ?state}))
    (me/cata {:matrix ?rest-rows
              :as ?state})])

  ;; :reference
  ;; ----------

  (me/and {:matrix [{:cells [{:tag :reference, :symbol ?symbol} & ?rest-cells]
                     :as ?row}
                    & ?rest-rows]
           :targets [?target & ?rest-targets]
           :bindings ?bindings
           :as ?state}
          (me/let [?new-bindings (gensym "B__")]))
  (flat-concat
   [(`mapcat
     (fn [?new-bindings]
       (me/cata {:matrix [{:cells ?rest-cells :as ?row}]
                 :targets ?rest-targets
                 :bindings ?new-bindings
                 :as ?state}))
     (?symbol ?target ?bindings))
    (me/cata {:matrix ?rest-rows
              :as ?state})])
  ?x
  (throw (ex-info "No equation for" {:term ('quote ?x)})))
