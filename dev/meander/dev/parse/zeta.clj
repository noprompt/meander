(ns meander.dev.parse.zeta
  (:require [meander.dev.kernel.zeta :as dev.kernel]
            [meander.epsilon :as me]))

(me/defsyntax &digit [match size]
  (me/re #"&(\d+)" [match size]))

(me/defsyntax dot-symbol []
  (me/symbol nil (me/re #"\.\.(?:\.|\d+)")))

(me/defsyntax not-dot-symbol [pattern]
  (me/and (me/not (dot-symbol)) pattern))

(dev.kernel/defmodule parse
  ;; Meta Rules
  ;; ----------

  [`parse-seq [] ?env]
  {:tag :empty}

  ;; [,,, :meander.zeta/as ?pattern]
  ;; -------------------------------

  [`parse-seq [!xs ... :meander.zeta/as ?pattern] ?env]
  {:tag :as
   :pattern (me/cata ?pattern)
   :next (me/cata [`parse-seq [!xs ...] ?env])}

  ;; [,,, meander.zeta/&N ?pattern]
  ;; -----------------------------

  [`parse-seq [!init ... (me/symbol ?ns (me/re #"&(\d+)" [_ ?n])) ?pattern & ?rest]
   (me/or (me/let [?ns "meander.zeta"] ?env)
          {:aliases {(me/symbol ?ns) (me/symbol "meander.zeta")} :as ?env})]
  (me/cata
   [`join-args
    (me/cata [`parse-seq [!init ...] ?env])
    (me/cata [`join-args
              {:tag :slice
               :size ~(Integer. ?n)
               :pattern (me/cata [?pattern ?env])}
              (me/cata [`parse-seq [& ?rest] ?env])])])

  ;; [,,, meander.zeta/& ?pattern]
  ;; -----------------------------

  [`parse-seq [!init ... (me/symbol ?ns (me/re #"&.*")) ?pattern & ?rest]
   (me/or (me/let [?ns "meander.zeta"] ?env)
          {:aliases {(me/symbol ?ns) (me/symbol "meander.zeta")} :as ?env})]
  (me/cata [`join-args
            (me/cata [`parse-seq [!init ...] ?env])
            (me/cata [`join-args
                      (me/cata [?pattern ?env])
                      (me/cata [`parse-seq [& ?rest] ?env])])])

  [`parse-seq [!xs ... (me/symbol ?ns (me/re #"&.*" ?name)) ?pattern]
   {:aliases {(me/symbol ?ns) (me/symbol "meander.zeta")} :as ?env}]
  (me/cata [`parse-seq [!xs ... (me/symbol "meander.zeta" ?name) ?pattern] ?env])

  ;; [,,, . ?pattern]
  ;; ----------------

  [`parse-seq [!xs ... '. & ?rest] ?env]
  (me/cata [`join-args
            (me/cata [`parse-seq [!xs ...] ?env])
            (me/cata [`parse-seq ?rest ?env])])

  ;; [,,, ... ?pattern]
  ;; ----------------

  [`parse-seq ['... & ?rest] ?env]
  (me/cata [`parse-seq ?rest ?env])

  [`parse-seq [(not-dot-symbol !xs) ... '... & ?rest] ?env]
  (me/cata [`star-args
            (me/cata [`parse-seq [!xs ...] ?env])
            (me/cata [`parse-seq ?rest ?env])])

  [`star-args
   {:tag :cat
    :sequence [{:tag :memory-variable :as ?memory-variable}]
    :next {:tag :empty}}
   ?next]
  (me/cata [`join-args
            {:tag :into
             :memory-variable ?memory-variable}
            ?next])

  [`star-args ?pattern ?next]
  {:tag :star
   :pattern ?pattern
   :next ?next}

  ;; [,,, ..N ?pattern]
  ;; -----------------

  [`parse-seq
   [(me/symbol nil (me/re #"\.\.(\d+)" [_ ?n]) :as ?operator) & ?rest]
   ?env]
  {:tag :syntax-error
   :message "The n or more operator ..N must be preceeded by at least one pattern"}

  [`parse-seq
   [(not-dot-symbol !xs) ... (me/symbol nil (me/re #"\.\.(\d+)" [_ ?n])) & ?rest]
   ?env]
  {:tag :plus
   :n ~(Integer. ?n)
   :pattern (me/cata [`parse-seq [!xs ...] ?env])
   :next (me/cata [`parse-seq ?rest ?env])}

  ;; [,,,]
  ;; -----

  [`parse-seq [!xs ...] ?env]
  (me/cata [`cat-args [(me/cata [!xs ?env]) ...] {:tag :empty}])

  [`cat-args [{:tag :literal, :form !forms} ...] {:tag :empty}]
  {:tag :literal
   :form [!forms ...]}

  [`cat-args ?sequence ?next]
  {:tag :cat
   :sequence ?sequence
   :next ?next}

  [`join-args
   {:tag :cat
    :sequence ?sequence
    :next {:tag :empty}}
   ?right]
  (me/cata [`cat-args ?sequence ?right])

  [`join-args ?left {:tag :empty}]
  ?left

  [`join-args {:tag :empty} ?right]
  ?right

  [`join-args ?left ?right]
  {:tag :join
   :left ?left
   :right ?right}

  ;; {meander.zeta/:as ?pattern ,,,}
  ;; -------------------------------

  [`parse-entries (me/and {:meander.zeta/as ?pattern & ?rest}
                          (me/not ?rest))
   ?env]
  {:tag :as
   :pattern ?pattern
   :next (me/cata [?rest ?env])}

  ;; {meander.zeta/&name ?pattern ,,,}
  ;; ---------------------------------

  [`parse-entries {(me/symbol ?ns (me/re #"&.*")) ?pattern & ?rest}
   (me/or (me/let [?ns "meander.zeta"] ?env)
          {:aliases {(me/symbol ?ns) (me/symbol "meander.zeta")} :as ?env})]
  {:tag :rest-map
   :pattern (me/cata [?pattern ?env])
   :next (me/cata [`parse-entries ?rest ?env])}

  ;; {?pattern1 ?pattern2 ,,,}
  ;; -------------------------

  [`parse-entries {?key-pattern ?val-pattern & ?rest} ?env]
  {:tag :entry
   :key-pattern (me/cata [?key-pattern ?env])
   :val-pattern (me/cata [?val-pattern ?env])
   :next (me/cata [`parse-entries ?rest ?env])}

  ;; {}
  ;; --

  [`parse-entries {} ?env]
  {:tag :some-map}

  [`parse-with-bindings [] ?env]
  []

  [`parse-with-bindings [_] ?env]
  [{:tag :error
    :message "meander.zeta/with expects an even number of bindings"}]

  [`parse-with-bindings [(me/symbol _ (me/re #"%.+") :as ?symbol) ?pattern & ?rest] ?env]
  [{:tag :with-binding
    :reference {:tag :reference
                :symbol ?symbol
                :form ?symbol}
    :pattern (me/cata [?pattern ?env])}
   & (me/cata [`parse-with-bindings ?rest ?env])]

  [`parse-with-bindings [?x ?pattern & ?rest] ?env]
  [{:tag :error
    :message "meander.zeta/with bindings must be an repeating sequence of %name pattern"}]

  ;; Primary Rules
  ;; =============

  ;; Vector pattern
  ;; --------------

  [[& ?sequence] ?env]
  {:tag :vector
   :next (me/cata [`parse-seq ?sequence ?env])
   :form ?sequence}

  ;; (meander.zeta/with [,,,])
  ;; (meander.zeta/with [,,,] pattern)

  [(meander.zeta/with ?bindings ?body :as ?form) ?env]
  {:tag :with
   :bindings {:tag :with-bindings
              :bindings (me/cata [`parse-with-bindings ?bindings ?env])}
   :body (me/cata [?body ?env])
   :form ?form}

  ;; (meander.zeta/and _ _)
  ;; ----------------------

  [((me/symbol ?ns "and") ?left ?right :as ?form)
   (me/or (me/let [?ns "meander.zeta"] ?env)
          {:aliases {(me/symbol ?ns) (me/symbol "meander.zeta")} :as ?env})]
  {:tag :and
   :left (me/cata [?left ?env])
   :right (me/cata [?right ?env])
   :form ?form}

  ;; (meander.zeta/not _)
  ;; ----------------------

  [((me/symbol ?ns "not") ?pattern :as ?form)
   (me/or (me/let [?ns "meander.zeta"] ?env)
          {:aliases {(me/symbol ?ns) (me/symbol "meander.zeta")} :as ?env})]
  {:tag :not
   :pattern (me/cata [?pattern ?env])
   :form ?form}


  ;; (meander.zeta/or _ _)
  ;; ----------------------

  [((me/symbol ?ns "or") ?left ?right :as ?form)
   (me/or (me/let [?ns "meander.zeta"] ?env)
          {:aliases {(me/symbol ?ns) (me/symbol "meander.zeta")} :as ?env})]
  {:tag :or
   :left (me/cata [?left ?env])
   :right (me/cata [?right ?env])
   :form ?form}


  ;; Seq pattern
  ;; -----------

  [(& ?sequence) ?env]
  {:tag :seq
   :next (me/cata [`parse-seq [& ?sequence] ?env])
   :form ?sequence}

  ;; Map pattern
  ;; -----------

  [{:as ?map} ?env]
  {:tag :map
   :next (me/cata [`parse-entries ?map ?env])
   :form ?map}

  ;; _example
  [(me/symbol _ (me/re #"_.*" ?name) :as ?symbol) _]
  {:tag :wildcard
   :name ?name
   :form ?symbol
   :symbol ?symbol}

  ;; example#
  [(me/symbol _ (me/re #".+#" ?name) :as ?symbol) _]
  {:tag :random-symbol
   :name ?name
   :form ?symbol
   :symbol ?symbol}

  ;; %example
  [(me/symbol _ (me/re #"%.+" ?name) :as ?symbol) _]
  {:tag :reference
   :name ?name
   :symbol ?symbol}

  ;; *example
  [(me/symbol _ (me/re #"\*.+" ?name) :as ?symbol) _]
  {:tag :mutable-variable
   :name ?name
   :symbol ?symbol}

  ;; !example
  [(me/symbol _ (me/re #"\!.+" ?name) :as ?symbol) _]
  {:tag :memory-variable
   :name ?name
   :symbol ?symbol}

  ;; ?example
  [(me/symbol _ (me/re #"\?.+" ?name) :as ?symbol) _]
  {:tag :logic-variable
   :name ?name
   :symbol ?symbol}

  [?x _]
  {:tag :literal
   :form ?x}

  ;; Probably not

  ?x ?x)
