(ns meander.dev.parse.zeta
  (:require [meander.dev.kernel.zeta :as dev.kernel]
            [meander.epsilon :as me]))

(dev.kernel/defmodule parse
  ;; Meta Rules
  ;; ----------

  [`parse-seq []]
  {:tag :empty}

  ;; [,,, :meander.zeta/as ?pattern]
  ;; -------------------------------

  [`parse-seq [!xs ... :meander.zeta/as ?pattern]]
  {:tag :as
   :pattern (me/cata ?pattern)
   :next (me/cata [`parse-seq [!xs ...]])}

  ;; [,,, meander.zeta/& ?pattern]
  ;; -----------------------------

  [`parse-seq [(me/symbol "meander.zeta" (me/re #"&(\d+)" [_ ?n])) ?pattern]]
  {:tag :slice
   :size ~(Integer. ?n)
   :pattern (me/cata ?pattern)}

  [`parse-seq [!xs ... (me/symbol "meander.zeta" (me/re #"&(\d+)" [_ ?n])) ?pattern]]
  {:tag :join
   :left (me/cata [`parse-seq [!xs ...]])
   :right {:tag :slice
           :size ~(Integer. ?n)
           :pattern (me/cata ?pattern)}}

  ;; [,,, meander.zeta/& ?pattern]
  ;; -----------------------------

  [`parse-seq [(me/symbol "meander.zeta" (me/re #"&.*")) ?pattern]]
  (me/cata ?pattern)

  [`parse-seq [!xs ... (me/symbol "meander.zeta" (me/re #"&.*")) ?pattern]]
  {:tag :join
   :left (me/cata [`parse-seq [!xs ...]])
   :right (me/cata ?pattern)}

  ;; [,,, . ?pattern]
  ;; ----------------

  [`parse-seq ['. & ?rest]]
  (me/cata [`parse-seq ?rest])

  [`parse-seq [!xs ... '. & ?rest]]
  (me/cata [`join-args {:tag :join
                        :left (me/cata [`parse-seq [!xs ...]])
                        :right (me/cata [`parse-seq ?rest])}])

  ;; [,,, ... ?pattern]
  ;; ----------------

  [`parse-seq ['... & ?rest]]
  (me/cata [`parse-seq ?rest])

  [`parse-seq [!xs ... '... & ?rest]]
  (me/cata [`star-args
            (me/cata [`parse-seq [!xs ...]])
            (me/cata [`parse-seq ?rest])])

  [`star-args
   {:tag :cat
    :sequence [{:tag :memory-variable :as ?memory-variable}]
    :next {:tag :empty}}
   ?next]
  (me/cata [`join-args {:tag :join
                        :left {:tag :into
                               :memory-variable ?memory-variable}
                        :right ?next}])


  [`star-args ?pattern ?next]
  {:tag :star
   :pattern ?pattern
   :next ?next}

  ;; [,,,]
  ;; -----

  [`parse-seq [!xs ...]]
  {:tag :cat
   :sequence [(me/cata !xs) ...]
   :next {:tag :empty}}

  [`join-args {:tag :join
               :left {:tag :cat
                      :sequence ?sequence
                      :next {:tag :empty}}
               :right ?right
               :form ?form}]
  {:tag :cat
   :sequence ?sequence
   :next ?right}

  [`join-args {:tag :join
               :left ?left
               :right {:tag :empty}
               :form ?form}]
  ?left

  [`join-args ?ast]
  ?ast

  ;; {meander.zeta/:as ?pattern ,,,}
  ;; -------------------------------

  [`parse-entries (me/and {:meander.zeta/as ?pattern & ?rest}
                          (me/not ?rest))]
  {:tag :as
   :pattern ?pattern
   :next (me/cata ?rest)}

  ;; {meander.zeta/&name ?pattern ,,,}
  ;; ---------------------------------

  [`parse-entries {(me/symbol "meander.zeta" (me/re #"&.*")) ?pattern & ?rest}]
  {:tag :rest-map
   :pattern ?pattern
   :next (me/cata [`parse-entries ?rest])}

  ;; {?pattern1 ?pattern2 ,,,}
  ;; -------------------------

  [`parse-entries {?key-pattern ?val-pattern & ?rest}]
  {:tag :entry
   :key-pattern (me/cata ?key-pattern)
   :val-pattern (me/cata ?val-pattern)
   :next (me/cata [`parse-entries ?rest])}

  ;; {}
  ;; --

  [`parse-entries {}]
  {:tag :some-map}

  [`parse-with-bindings []]
  []

  [`parse-with-bindings [_]]
  [{:tag :error
    :message "meander.zeta/with expects an even number of bindings"}]

  [`parse-with-bindings [(me/symbol _ (me/re #"%.+") :as ?symbol) ?pattern & ?rest]]
  [{:tag :with-binding
    :reference {:tag :reference
                :symbol ?symbol
                :form ?symbol}
    :pattern (me/cata ?pattern)}
   & (me/cata [`parse-with-bindings ?rest])]

  [`parse-with-bindings [?x ?pattern & ?rest]]
  [{:tag :error
    :message "meander.zeta/with bindings must be an repeating sequence of %name pattern"}]

  ;; Primary Rules
  ;; =============

  ;; Vector pattern
  ;; --------------

  [& ?sequence]
  {:tag :vector
   :next (me/cata [`parse-seq ?sequence])
   :form ?sequence}

  ;; (meander.zeta/with [,,,])
  ;; (meander.zeta/with [,,,] pattern)

  (meander.zeta/with ?bindings ?body :as ?form)
  {:tag :with
   :bindings {:tag :with-bindings
              :bindings (me/cata [`parse-with-bindings ?bindings])}
   :body (me/cata ?body)
   :form ?form}

  ;; (meander.zeta/and _ _)
  ;; ----------------------

  (meander.zeta/and ?left ?right :as ?form)
  {:tag :and
   :left (me/cata ?left)
   :right (me/cata ?right)
   :form ?form}

  ;; (meander.zeta/or _ _)
  ;; ----------------------

  (meander.zeta/or ?left ?right :as ?form)
  {:tag :or
   :left (me/cata ?left)
   :right (me/cata ?right)
   :form ?form}


  ;; Seq pattern
  ;; -----------

  (& ?sequence)
  {:tag :seq
   :next (me/cata [`parse-seq [& ?sequence]])
   :form ?sequence}

  ;; Map pattern
  ;; -----------

  {:as ?map}
  {:tag :map
   :next (me/cata [`parse-entries ?map])
   :form ?map}

  ;; _example
  (me/symbol _ (me/re #"_.*" ?name) :as ?symbol)
  {:tag :wildcard
   :name ?name
   :form ?symbol
   :symbol ?symbol}

  ;; example#
  (me/symbol _ (me/re #".+#" ?name) :as ?symbol)
  {:tag :random-symbol
   :name ?name
   :form ?symbol
   :symbol ?symbol}

  ;; %example
  (me/symbol _ (me/re #"%.+" ?name) :as ?symbol)
  {:tag :reference
   :name ?name
   :symbol ?symbol}

  ;; *example
  (me/symbol _ (me/re #"\*.+" ?name) :as ?symbol)
  {:tag :mutable-variable
   :name ?name
   :symbol ?symbol}

  ;; !example
  (me/symbol _ (me/re #"\!.+" ?name) :as ?symbol)
  {:tag :memory-variable
   :name ?name
   :symbol ?symbol}

  ;; ?example
  (me/symbol _ (me/re #"\?.+" ?name) :as ?symbol)
  {:tag :logic-variable
   :name ?name
   :symbol ?symbol}

  ?x
  {:tag :literal
   :form ?x})
