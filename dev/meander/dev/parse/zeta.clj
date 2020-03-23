(ns meander.dev.parse.zeta
  (:require [meander.dev.kernel.zeta :as dev.kernel]
            [meander.epsilon :as me]))

(me/defsyntax &digit [match size]
  (me/re #"&(\d+)" [match size]))

(me/defsyntax dot-symbol []
  (me/symbol nil (me/re #"\.\.(?:\.|\d+)")))

(me/defsyntax not-dot-symbol [pattern]
  (me/and (me/not (dot-symbol)) pattern))

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

(dev.kernel/defconstructor parse-sequential [forms env])
(dev.kernel/defconstructor parse-entries [map-form env])
(dev.kernel/defconstructor parse-with-bindings [bindings env])
(dev.kernel/defconstructor make-join [left right env])
(dev.kernel/defconstructor make-cat [sequence next env])
(dev.kernel/defconstructor make-star [pattern next env])
(dev.kernel/defconstructor make-fold [var-ast init-ast fold-form form])

(dev.kernel/defmodule parse
  ;; Meta Rules
  ;; ----------

  (parse-sequential [] ?env)
  {:tag :empty}

  ;; [,,, :meander.zeta/as ?pattern]
  ;; -------------------------------

  (parse-sequential [!xs ... :meander.zeta/as ?pattern] ?env)
  {:tag :as
   :pattern (me/cata [?pattern ?env])
   :next (parse-sequential [!xs ...] ?env)}

  ;; [,,, meander.zeta/&N ?pattern]
  ;; -----------------------------

  (parse-sequential [!init ... (me/symbol ?ns (me/re #"&(\d+)" [_ ?n])) ?pattern & ?rest]
                    (me/or (me/let [?ns "meander.zeta"] ?env)
                           {:aliases {(me/symbol ?ns) (me/symbol "meander.zeta")} :as ?env}))
  (make-join (parse-sequential [!init ...] ?env)
             (make-join {:tag :slice
                         :size ~(Integer. ?n)
                         :pattern (me/cata [?pattern ?env])}
                        (parse-sequential [& ?rest] ?env)
                        ?env)
             ?env)

  ;; [,,, meander.zeta/& ?pattern]
  ;; -----------------------------

  (parse-sequential [!init ... (me/symbol ?ns (me/re #"&.*")) ?pattern & ?rest]
                    (me/or (me/let [?ns "meander.zeta"] ?env)
                           {:aliases {(me/symbol ?ns) (me/symbol "meander.zeta")} :as ?env}))
  (make-join (parse-sequential [!init ...] ?env)
             (make-join (me/cata [?pattern ?env])
                        (parse-sequential [& ?rest] ?env)
                        ?env)
             ?env)

  (parse-sequential [!xs ... (me/symbol ?ns (me/re #"&.*" ?name)) ?pattern]
                    {:aliases {(me/symbol ?ns) (me/symbol "meander.zeta")} :as ?env})
  (parse-sequential [!xs ... (me/symbol "meander.zeta" ?name) ?pattern] ?env)

  ;; [,,, . ?pattern]
  ;; ----------------

  (parse-sequential [!xs ... '. & ?rest] ?env)
  (make-join (parse-sequential [!xs ...] ?env)
             (parse-sequential ?rest ?env)
             ?env)

  ;; [,,, ... ?pattern]
  ;; ----------------

  (parse-sequential ['... & ?rest] ?env)
  (parse-sequential ?rest ?env)

  (parse-sequential [(not-dot-symbol !xs) ... '... & ?rest] ?env)
  (make-star (parse-sequential [!xs ...] ?env)
             (parse-sequential ?rest ?env)
             ?env)

  (make-star {:tag :cat
              :sequence [{:tag :memory-variable :as ?memory-variable}]
              :next {:tag :empty}}
             ?next
             ?env)
  (make-join {:tag :into :memory-variable ?memory-variable}
             ?next
             ?env)

  (make-star ?pattern ?next {:context :string})
  {:tag :string-star
   :greedy? false
   :pattern ?pattern
   :next ?next}

  (make-star ?pattern ?next _)
  {:tag :star
   :greedy? false
   :pattern ?pattern
   :next ?next}

  ;; [,,, ..N ?pattern]
  ;; ------------------

  (parse-sequential [(me/symbol nil (me/re #"\.\.(\d+)" [_ ?n]) :as ?operator) & ?rest]
                    ?env)
  {:tag :syntax-error
   :message "The n or more operator ..N must be preceeded by at least one pattern"}

  (parse-sequential [(not-dot-symbol !xs) ... (me/symbol nil (me/re #"\.\.(\d+)" [_ ?n])) & ?rest]
                    (me/or (me/let [?tag :string-plus] {:context :string :as ?env})
                           (me/let [?tag :plus] ?env)))
  {:tag ?tag
   :n ~(Integer. ?n)
   :greedy? false
   :pattern (parse-sequential [!xs ...] ?env)
   :next (parse-sequential ?rest ?env)}

  ;; [,,, ..?n ?pattern]
  ;; -----------------

  (parse-sequential [(me/symbol nil (me/re #"\.\.(\?.+)" [_ ?n]) :as ?operator) & ?rest]
                    ?env)
  {:tag :syntax-error
   :message "The ?n or more operator ..?n must be preceeded by at least one pattern"}

  (parse-sequential [(not-dot-symbol !xs) ... (me/symbol nil (me/re #"\.\.(\?.+)" [_ ?n])) & ?rest]
                    (me/or (me/let [?tag :string-logical-plus] {:context :string :as ?env})
                           (me/let [?tag :logical-plus] ?env)))
  {:tag ?tag
   :n {:tag :logic-variable
       :name ?n
       :symbol (me/symbol ?n)}
   :greedy? false
   :pattern (parse-sequential [!xs ...] ?env)
   :next (parse-sequential ?rest ?env)}

  ;; [,,, ..?n ?pattern]
  ;; -----------------

  (parse-sequential [(me/symbol nil (me/re #"\.\.(!.+)" [_ ?n]) :as ?operator) & ?rest]
                    ?env)
  {:tag :syntax-error
   :message "The operator ..!n must be preceeded by at least one pattern"}

  (parse-sequential [(not-dot-symbol !xs) ... (me/symbol nil (me/re #"\.\.(\!.+)" [_ ?n])) & ?rest]
                    (me/or (me/let [?tag :string-memory-plus] {:context :string :as ?env})
                           (me/let [?tag :memory-plus] ?env)))
  {:tag ?tag
   :n {:tag :memory-variable
       :name ?n
       :symbol (me/symbol ?n)}
   :greedy? false
   :pattern (parse-sequential [!xs ...] ?env)
   :next (parse-sequential ?rest ?env)}

  ;; [,,,]
  ;; -----

  (parse-sequential [!xs ...] ?env)
  (make-cat [(me/cata [!xs ?env]) ...] {:tag :empty} ?env)

  (make-cat [] ?next ?env)
  ?next

  (make-cat [(me/and (me/not {:tag :group}) !xs) ... {:tag :group :as ?group} & ?rest] ?next ?env)
  (make-join (make-cat [!xs ...] {:tag :empty} ?env)
             (make-join ?group
                        (make-cat ?rest ?next ?env)
                        ?env)
             ?env)

  (make-cat [(me/and (me/not {:tag :star}) !xs) ... {:tag :star :as ?group} & ?rest] ?next ?env)
  (make-join (make-cat [!xs ...] {:tag :empty} ?env)
             (make-join ?group
                        (make-cat ?rest ?next ?env)
                        ?env)
             ?env)

  (make-cat [{:tag :literal, :type (me/or :string :char) :form !forms} ..1] {:tag :empty} _)
  {:tag :literal
   :type :string
   :form (me/app clojure.string/join [!forms ...])}

  (make-cat [{:tag :literal, :type :string, :as ?ast} & ?rest] ?next ?env)
  (make-join ?ast
             (make-cat ?rest ?next ?env)
             ?env)

  (make-cat [{:tag :literal, :form !forms} ..1] {:tag :empty} {:context ?context :as ?env})
  {:tag :literal
   :type ?context
   :form [!forms ...]}

  (make-cat ?sequence ?next {:context :string})
  {:tag :string-cat
   :sequence ?sequence
   :next ?next}

  (make-cat ?sequence ?next _)
  {:tag :cat
   :sequence ?sequence
   :next ?next}

  (make-join {:tag :cat, :sequence ?sequence, :next {:tag :empty}} ?right ?env)
  (make-cat ?sequence ?right ?env)

  (make-join {:tag :literal, :type :string, :form ?form-1}
             {:tag :string-join
              :left {:tag :literal :type :string :form ?form-2}
              :right ?right}
             {:context :string :as ?env})
  (make-join {:tag :literal, :type :string, :form (me/app str ?form-1 ?form-2)}
             ?right
             ?env)

  (make-join {:tag :cat :as ?ast}
             {:tag :cat :sequence ?sequence :next ?next}
             _)
  {:tag :cat
   :sequence [?ast & ?sequence]
   :next ?next}

  (make-join {:tag :string-cat
              :as ?ast}
             {:tag :string-cat :sequence ?sequence :next ?next}
             _)
  {:tag :string-cat
   :sequence [?ast & ?sequence]
   :next ?next}

  (make-join {:tag :string-cat :sequence ?sequence :next {:tag :empty}}
             ?right
             ?env)
  (make-join ?sequence ?right ?env)

  (make-join {:tag :string-star, :pattern ?pattern, :next {:tag :empty}}
             ?right
             {:context :string})
  {:tag :string-star, :pattern ?pattern, :next ?right}

  (make-join {:tag :string-join, :left ?left, :right ?right-1}
             ?right-2
             {:context :string, :as ?env})
  {:tag :string-join, :left ?left, :right (make-join ?right-1 ?right-2 ?env)}

  (make-join ?left {:tag :empty} ?env)
  ?left

  (make-join {:tag :empty} ?right ?env)
  ?right

  (make-join {:tag :star, :next {:tag :empty} :as ?left}
             ?right
             ?env)
  {:tag :star :next ?right & ?left}

  (make-join ?left ?right {:context :string})
  {:tag :string-join, :left ?left, :right ?right}


  (make-join ?left ?right ?env)
  {:tag :join, :left ?left, :right ?right}


  ;; {:meander.zeta/as ?pattern ,,,}
  ;; -------------------------------

  (parse-entries (me/and {:meander.zeta/as ?pattern & ?rest}
                         (me/not ?rest))
                 ?env)
  {:tag :as
   :pattern (me/cata [?pattern ?env])
   :next (me/cata [?rest ?env])}

  ;; {meander.zeta/&name ?pattern ,,,}
  ;; ---------------------------------

  (parse-entries {(me/symbol ?ns (me/re #"&.*")) ?pattern & ?rest}
                 (me/or (me/let [?ns "meander.zeta"] ?env)
                        {:aliases {(me/symbol ?ns) (me/symbol "meander.zeta")} :as ?env}))
  {:tag :rest-map
   :pattern (me/cata [?pattern ?env])
   :next (parse-entries ?rest ?env)}

  ;; {?pattern1 ?pattern2 ,,,}
  ;; -------------------------

  (parse-entries {?key-pattern ?val-pattern & ?rest} ?env)
  {:tag :entry
   :key-pattern (me/cata [?key-pattern ?env])
   :val-pattern (me/cata [?val-pattern ?env])
   :next (parse-entries ?rest ?env)}

  ;; {}
  ;; --

  (parse-entries {} ?env)
  {:tag :some-map}

  (parse-with-bindings [] ?env)
  []

  (parse-with-bindings [_] ?env)
  [{:tag :error
    :message "meander.zeta/with expects an even number of bindings"}]

  (parse-with-bindings [(me/symbol _ (me/re #"%.+") :as ?symbol) ?pattern & ?rest]
                       ?env)
  [{:tag :with-binding
    :reference {:tag :reference
                :symbol ?symbol
                :form ?symbol}
    :pattern (me/cata [?pattern ?env])}
   & (parse-with-bindings ?rest ?env)]

  (parse-with-bindings [?x ?pattern & ?rest] ?env)
  [{:tag :error
    :message "meander.zeta/with bindings must be an repeating sequence of %name pattern"}]

  ;; Primary Rules
  ;; =============

  ;; Vector pattern
  ;; --------------

  [[& ?sequence :as ?form] ?env]
  {:tag :vector
   :next (parse-sequential ?sequence {:context :vector & ?env})
   :form ?form}

  ;; (meander.zeta/* pattern ,,,)

  (special "*" (_ & ?patterns :as ?form) ?env)
  {:tag :star
   :greedy? true
   :pattern (parse-sequential [& ?patterns] ?env)
   :next {:tag :empty}}

  ;; (meander.zeta/<> pattern ,,,)

  (special "<>" (_ & ?patterns :as ?form) ?env)
  {:tag :group
   :pattern (parse-sequential [& ?patterns] ?env)}
  
  ;; (meander.zeta/with [,,,])
  ;; (meander.zeta/with [,,,] pattern)

  (special "with" (_  ?bindings ?body :as ?form) ?env)
  {:tag :with
   :bindings {:tag :with-bindings
              :bindings (parse-with-bindings ?bindings ?env)}
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
  (make-fold (me/cata [?mutable-variable ?env]) (me/cata [?initial-value ?env]) ?fold-function ?form)

  (make-fold {:tag :mutable-variable :as ?variable-ast} ?initial-value-ast ?fold-function ?form)
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


  ;; (meander.zeta/re _)
  ;; ----------------------

  (special "re" (_ ?regex :as ?form) ?env)
  {:tag :regex
   :regex ?regex
   :form ?form}

  ;; (meander.zeta/re _ _)
  ;; ----------------------

  (special "re" (_ ?regex ?capture :as ?form) ?env)
  {:tag :regex
   :regex ?regex
   :capture (me/cata [?capture ?env])
   :form ?form}

  ;; (meander.zeta/string _)
  ;; ---------------------

  (special "string" (_ & ?sequence :as ?form) ?env)
  {:tag :string
   :next (parse-sequential [& ?sequence] {:context :string & ?env})
   :form ?form}

  ;; (meander.zeta/symbol _)
  ;; ---------------------

  (special "symbol" (_ ?name :as ?form) ?env)
  {:tag :symbol
   :name (me/cata [?name ?env])
   :form ?form}

  ;; (meander.zeta/symbol _ _)
  ;; ---------------------

  (special "symbol" (_ ?namespace ?name :as ?form) ?env)
  {:tag :symbol
   :name (me/cata [?name ?env])
   :namespace (me/cata [?namespace ?env])
   :form ?form}

  ;; (meander.zeta/symbol _ _ :meander.zeta/as _)
  ;; ---------------------

  (special "symbol" (_ ?namespace ?name :meander.zeta/as ?pattern :as ?form) ?env)
  {:tag :symbol
   :name (me/cata [?name ?env])
   :namespace (me/cata [?namespace ?env])
   :as-pattern (me/cata [?pattern ?env])
   :form ?form}

  ;; Seq pattern
  ;; -----------

  [(& ?sequence) ?env]
  {:tag :seq
   :next (parse-sequential [& ?sequence] {:context :seq & ?env})
   :form ?sequence}

  ;; Map pattern
  ;; -----------

  [{:as ?map} ?env]
  {:tag :map
   :next (parse-entries ?map ?env)
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

  ?x
  {:tag :mistake
   :x ?x})
