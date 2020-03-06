(ns meander.dev.parse.zeta
  (:require [meander.dev.kernel.zeta :as dev.kernel]
            [meander.epsilon :as me]))

(me/defsyntax &digit [match size]
  (me/re #"&(\d+)" [match size]))

(me/defsyntax dot-symbol []
  (me/symbol nil (me/re #"\.\.(?:\.|\d+)")))

(me/defsyntax not-dot-symbol [pattern]
  (me/and (me/not (dot-symbol)) pattern))

(me/defsyntax parse-seq-or-string [pattern]
  (if (me/match-syntax? &env)
    (me/and (me/or `parse-seq `parse-string) pattern)
    &form))

(me/defsyntax special
  "Equivalent to form

  [((symbol ?_ns ?name) & _ :as ?form)
   (or (let [?_ns \"meander.zeta\"] ?env)
       {:aliases {(symbol ?_ns) (symbol \"meander.zeta\")
        :as ?env}

  where ?_ns is internal to the pattern."
  [?name ?form ?env]
  {:pre [(string? name)]}
  (if (me/match-syntax? &env)
    (let [?ns (gensym "?__")]
      (me/subst
        (`me/and [((`me/symbol ?ns ?name) '& '_)
                  (`me/or (`me/let [?ns "meander.zeta"])
                   {:aliases {(`me/symbol ?ns) (`me/symbol "meander.zeta")}})]
         [?form ?env])))
    &form))

(dev.kernel/defmodule parse
  ;; Meta Rules
  ;; ----------

  [(parse-seq-or-string _) [] ?env]
  {:tag :empty}

  ;; [,,, :meander.zeta/as ?pattern]
  ;; -------------------------------

  [(parse-seq-or-string ?rule-name) [!xs ... :meander.zeta/as ?pattern] ?env]
  {:tag :as
   :pattern (me/cata [?pattern ?env])
   :next (me/cata [?rule-name [!xs ...] ?env])}


  ;; [,,, meander.zeta/&N ?pattern]
  ;; -----------------------------

  [(parse-seq-or-string ?rule-name) [!init ... (me/symbol ?ns (me/re #"&(\d+)" [_ ?n])) ?pattern & ?rest]
   (me/or (me/let [?ns "meander.zeta"] ?env)
          {:aliases {(me/symbol ?ns) (me/symbol "meander.zeta")} :as ?env})]
  (me/cata
   [`join-args
    (me/cata [?rule-name [!init ...] ?env])
    (me/cata [`join-args
              {:tag :slice
               :size ~(Integer. ?n)
               :pattern (me/cata [?pattern ?env])}
              (me/cata [?rule-name [& ?rest] ?env])])])

  ;; [,,, meander.zeta/& ?pattern]
  ;; -----------------------------

  [`parse-seq [!init ... (me/symbol ?ns (me/re #"&.*")) ?pattern & ?rest]
   (me/or (me/let [?ns "meander.zeta"] ?env)
          {:aliases {(me/symbol ?ns) (me/symbol "meander.zeta")} :as ?env})]
  (me/cata [`join-args
            (me/cata [`parse-seq [!init ...] ?env])
            (me/cata [`join-args
                      (me/cata [?pattern ?env])
                      (me/cata [`parse-string [& ?rest] ?env])])])

  [`parse-string [!init ... (me/symbol ?ns (me/re #"&.*")) ?pattern & ?rest]
   (me/or (me/let [?ns "meander.zeta"] ?env)
          {:aliases {(me/symbol ?ns) (me/symbol "meander.zeta")} :as ?env})]
  (me/cata [`string-join-args
            (me/cata [`parse-string [!init ...] ?env])
            (me/cata [`string-join-args
                      (me/cata [?pattern ?env])
                      (me/cata [`parse-string [& ?rest] ?env])])])

  [`parse-seq [!xs ... (me/symbol ?ns (me/re #"&.*" ?name)) ?pattern]
   {:aliases {(me/symbol ?ns) (me/symbol "meander.zeta")} :as ?env}]
  (me/cata [`parse-seq [!xs ... (me/symbol "meander.zeta" ?name) ?pattern] ?env])

  [`parse-string [!xs ... (me/symbol ?ns (me/re #"&.*" ?name)) ?pattern]
   {:aliases {(me/symbol ?ns) (me/symbol "meander.zeta")} :as ?env}]
  (me/cata [`parse-string [!xs ... (me/symbol "meander.zeta" ?name) ?pattern] ?env])

  ;; [,,, . ?pattern]
  ;; ----------------

  [`parse-seq [!xs ... '. & ?rest] ?env]
  (me/cata [`join-args
            (me/cata [`parse-seq [!xs ...] ?env])
            (me/cata [`parse-seq ?rest ?env])])


  [`parse-string [!xs ... '. & ?rest] ?env]
  (me/cata [`string-join-args
            (me/cata [`parse-string [!xs ...] ?env])
            (me/cata [`parse-string ?rest ?env])])

  ;; [,,, ... ?pattern]
  ;; ----------------

  [(parse-seq-or-string ?rule-name) ['... & ?rest] ?env]
  (me/cata [?rule-name ?rest ?env])

  (me/with [%1 (me/and `parse-string ?rule-name (me/let [?star-name `string-star-args]))
            %2 (me/and `parse-seq ?rule-name (me/let [?star-name `star-args]))
            %3 (me/or %1 %2)]
    [%3 [(not-dot-symbol !xs) ... '... & ?rest] ?env])
  (me/cata [?star-name
            (me/cata [?rule-name [!xs ...] ?env])
            (me/cata [?rule-name ?rest ?env])])

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

  [`string-star-args
   {:tag :string-cat
    :sequence [{:tag :memory-variable :as ?memory-variable}]
    :next {:tag :empty}}
   ?next]
  (me/cata [`string-join-args
            {:tag :into
             :memory-variable ?memory-variable}
            ?next])

  [`string-star-args ?pattern ?next]
  {:tag :string-star
   :pattern ?pattern
   :next ?next}

  ;; [,,, ..N ?pattern]
  ;; -----------------

  [(parse-seq-or-string _)
   [(me/symbol nil (me/re #"\.\.(\d+)" [_ ?n]) :as ?operator) & ?rest]
   ?env]
  {:tag :syntax-error
   :message "The n or more operator ..N must be preceeded by at least one pattern"}

  [(parse-seq-or-string ?rule-name)
   [(not-dot-symbol !xs) ... (me/symbol nil (me/re #"\.\.(\d+)" [_ ?n])) & ?rest]
   ?env]
  {:tag :plus
   :n ~(Integer. ?n)
   :pattern (me/cata [?rule-name [!xs ...] ?env])
   :next (me/cata [?rule-name ?rest ?env])}

  ;; [,,, ..?n ?pattern]
  ;; -----------------

  [(parse-seq-or-string _)
   [(me/symbol nil (me/re #"\.\.(\?.+)" [_ ?n]) :as ?operator) & ?rest]
   ?env]
  {:tag :syntax-error
   :message "The ?n or more operator ..?n must be preceeded by at least one pattern"}

  [(parse-seq-or-string ?rule-name)
   [(not-dot-symbol !xs) ... (me/symbol nil (me/re #"\.\.(\?.+)" [_ ?n])) & ?rest]
   ?env]
  {:tag :logical-plus
   :n {:tag :logic-variable
       :name ?n
       :symbol (me/symbol ?n)}
   :pattern (me/cata [?rule-name [!xs ...] ?env])
   :next (me/cata [?rule-name ?rest ?env])}

  ;; [,,, ..?n ?pattern]
  ;; -----------------

  [(parse-seq-or-string _)
   [(me/symbol nil (me/re #"\.\.(!.+)" [_ ?n]) :as ?operator) & ?rest]
   ?env]
  {:tag :syntax-error
   :message "The operator ..!n must be preceeded by at least one pattern"}

  [(parse-seq-or-string ?rule-name)
   [(not-dot-symbol !xs) ... (me/symbol nil (me/re #"\.\.(\!.+)" [_ ?n])) & ?rest]
   ?env]
  {:tag :memory-plus
   :n {:tag :memory-variable
       :name ?n
       :symbol (me/symbol ?n)}
   :pattern (me/cata [?rule-name [!xs ...] ?env])
   :next (me/cata [?rule-name ?rest ?env])}

  ;; [,,,]
  ;; -----

  [`parse-seq [!xs ...] ?env]
  (me/cata [`cat-args [(me/cata [!xs ?env]) ...] {:tag :empty}])

  [`parse-string [!xs ...] ?env]
  (me/cata [`string-cat-args [(me/cata [!xs ?env]) ...] {:tag :empty}])

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

  [`string-cat-args [{:tag :literal, :type (me/or :string :char) :form !forms} ...] {:tag :empty}]
  {:tag :literal
   :type :string
   :form (me/app clojure.string/join [!forms ...])}

  [`string-cat-args [{:tag :literal, :type :string, :as ?ast} & ?rest] ?next]
  (me/cata [`string-join-args ?ast (me/cata [`string-cat-args ?rest ?next])])

  [`string-cat-args ?sequence ?next]
  {:tag :string-cat
   :sequence ?sequence
   :next ?next}

  [`string-join-args
   {:tag :literal, :type :string, :form ?form-1}
   {:tag :string-join
    :left {:tag :literal :type :string :form ?form-2}
    :right ?right}]
  (me/cata [`string-join-args
            {:tag :literal, :type :string, :form (me/app str ?form-1 ?form-2)}
            ?right])

  [`string-join-args
   {:tag :literal :type :string :as ?ast}
   {:tag :string-cat :sequence ?sequence :next ?next}]
  {:tag :string-cat
   :sequence [?ast & ?sequence]
   :next ?next}

  [`string-join-args
   {:tag :string-cat :sequence ?sequence :next {:tag :empty}}
   ?right]
  (me/cata [`string-cat-args ?sequence ?right])

  [`string-join-args {:tag :string-star, :pattern ?pattern, :next {:tag :empty}} ?right]
  {:tag :string-star, :pattern ?pattern, :next ?right}

  [`string-join-args {:tag :string-join, :left ?left, :right ?right-1} ?right-2]
  {:tag :string-join, :left ?left, :right (me/cata [`string-join-args ?right-1 ?right-2])}

  [`string-join-args ?left {:tag :empty}]
  ?left

  [`string-join-args {:tag :empty} ?right]
  ?right

  [`string-join-args ?left ?right]
  {:tag :string-join, :left ?left, :right ?right}

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
  (me/cata [`vector-args (me/cata [`parse-seq ?sequence ?env]) ?sequence])

  [`vector-args {:tag :literal :as ?literal} _]
  ?literal

  [`vector-args ?next ?sequence]
  {:tag :vector
   :next ?next
   :form ?sequence}

  ;; (meander.zeta/with [,,,])
  ;; (meander.zeta/with [,,,] pattern)

  (special "with" (_  ?bindings ?body :as ?form) ?env)
  {:tag :with
   :bindings {:tag :with-bindings
              :bindings (me/cata [`parse-with-bindings ?bindings ?env])}
   :body (me/cata [?body ?env])
   :form ?form}

  ;; (meander.zeta/apply f pattern)
  ;; ------------------------------

  (special "apply" (_ ?fn ?pattern :as ?form) ?env)
  {:tag :apply
   :fn ?fn
   :pattern (me/cata [?pattern ?env])
   :form ?form}

  ;; (meander.zeta/and _ _)
  ;; ----------------------

  (special "and" (_ ?left ?right :as ?form) ?env)
  {:tag :and
   :left (me/cata [?left ?env])
   :right (me/cata [?right ?env])
   :form ?form}

  ;; (meander.zeta/cata _)
  ;; ---------------------

  (special "cata" (_ ?pattern :as ?form) ?env)
  {:tag :cata
   :pattern (me/cata [?pattern ?env])
   :form ?form}

  ;; (meander.zeta/fold *mutable-variable initial-value fold-function)
  ;; -----------------------------------------------------------------

  (special "fold" (_  ?mutable-variable ?initial-value ?fold-function :as ?form) ?env)
  (me/cata [`fold-args (me/cata [?mutable-variable ?env]) (me/cata [?initial-value ?env]) ?fold-function ?form])

  [`fold-args {:tag :mutable-variable :as ?variable-ast} ?initial-value-ast ?fold-function ?form]
  {:tag :fold
   :variable ?variable-ast
   :initial-value ?initial-value-ast
   :fold-function {:tag :host-expression
                   :form ?fold-function}
   :form ?form}

  ;; (meander.zeta/let _ _ _)
  ;; ------------------------

  (special "let" (?pattern ?expression ?next :as ?form) ?env)
  {:tag :let
   :pattern (me/cata [?pattern ?env])
   :expression {:tag :host-expression
                :form ?expression}
   :next (me/cata [?next ?env])}

  ;; (meander.zeta/not _)
  ;; ----------------------

  (special "not" (_ ?pattern :as ?form) ?env)
  {:tag :not
   :pattern (me/cata [?pattern ?env])
   :form ?form}


  ;; (meander.zeta/or _ _)
  ;; ----------------------

  (special "or" (_ ?left ?right :as ?form) ?env)
  {:tag :or
   :left (me/cata [?left ?env])
   :right (me/cata [?right ?env])
   :form ?form}

  ;; (meander.zeta/string _)
  ;; ---------------------

  (special "string" (_ & ?sequence :as ?form) ?env)
  {:tag :string
   :next (me/cata [`parse-string [& ?sequence] ?env])
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

  [(me/pred string? ?x) _]
  {:tag :literal
   :type :string
   :form ?x}

  [(me/pred char? ?x) _]
  {:tag :literal
   :type :char
   :form ?x}

  [?x _]
  {:tag :literal
   :form ?x}

  ;; Probably not

  ?x ?x)
