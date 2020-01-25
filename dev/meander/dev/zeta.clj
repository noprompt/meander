(ns meander.dev.zeta
  (:require [clojure.walk :as walk]
            [clojure.java.io :as io]
            [meander.epsilon :as me]
            [meander.runtime.zeta :as m.runtime]))

(defn epsilon->zeta [form]
  (walk/prewalk
   (fn [x]
     (me/rewrite x
       meander.substitute.runtime.epsilon/fail?
       meander.runtime.zeta/fail?

       meander.match.runtime.epsilon/fail?
       meander.runtime.zeta/fail?

       meander.match.runtime.epsilon/FAIL
       (meander.runtime.zeta/fail)

       meander.substitute.runtime.epsilon/FAIL
       (meander.runtime.zeta/fail)

       meander.substitute.runtime.epsilon/iterator
       meander.runtime.zeta/iterator

       ?x ?x))
   form))

(defn replace-$-variables [form]
  (let [replace (memoize (fn [s] (gensym (str s "__"))))]
    (walk/postwalk
     (fn [x]
       (me/match x
         (me/symbol nil (me/re #"\$.+" ?name))
         (replace ?name)

         ?x ?x))
     form)))

(defmacro defmodule [module-name & rules]
  (let [input-symbol (gensym "input__")
        rewrite-form (macroexpand `(me/rewrite ~input-symbol ~@rules))
        rewrite-form (epsilon->zeta rewrite-form)
        rewrite-form (replace-$-variables rewrite-form)
        defn-form `(defn ~module-name [~input-symbol] ~rewrite-form)
        ns (symbol (str "meander.compiled." module-name ".zeta"))
        ns-form (list 'ns ns '(:require [meander.runtime.zeta]))
        file (io/file "src/meander/compiled" (munge (name module-name))  "zeta.clj")
        parent (io/file (.getParent file))]
    (.mkdirs parent)
    (.createNewFile file)
    (with-open [file (io/writer file)]
      (binding [*out* file]
        (prn ns-form)
        (prn defn-form)))
    (require ns :reload)
    `(def ~(with-meta module-name '{:arglists '([input])})
       ~(symbol (str ns) (str module-name)))))

(defmodule parse
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

(me/defsyntax $inc [x]
  `(me/app inc ~x))

(me/defsyntax $dec [x]
  `(me/app dec ~x))

(defmodule match-compile
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
     (me/cata [`nths [!asts ...] ?take-symbol ($dec ?n) [([?next ?drop-symbol] & ?rest) ?env]])))

  [`nths _ _ -1 ?state]
  (me/cata ?state)

  [`nths [!rest-asts ... ?ast] ?target ?n [?queue ?env]]
  (me/cata [`nths [!rest-asts ...] ?target ($dec ?n) [([{:tag :nth, :index ?n, :pattern ?ast} ?target] & ?queue) ?env]])

  (me/and [([{:tag :nth, :index ?index, :pattern ?pattern} ?target] . (me/or [{:tag :nth} ?target :as !nths] !not-nths) ...) ?env]
          (me/let [?nth-symbol (gensym)]))
  (let* [?nth-symbol (clojure.core/nth ?target ?index)]
    (me/cata [(!nths ... [?pattern ?nth-symbol] . !not-nths ...) ?env]))


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

  (me/and [([{:tag :entry, :key-pattern ?key, :val-pattern ?val, :next ?next} ?target] & ?rest) ?env]
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
        (fn [$state]
          (me/cata [([?val ?val-target] [?next ?next-target] & ?rest) ?env]))
        (me/cata [([?key ?key-target]) ?env])))))
   ?target)

  ;; :into
  ;; -----

  [([{:tag :into
      :memory-variable {:symbol ?symbol}} ?target] & ?rest) ?env]
  (`m.runtime/into-memory-variable [$state ('quote ?symbol) ?target]
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

  [([{:tag :logic-variable :symbol ?symbol} ?target] & ?rest) {?key ?symbol :as ?env}]
  (let* [$value (clojure.core/get $state ?key)]
    (if (= $value ?target)
      (me/cata [?rest ?env])
      (`m.runtime/fail)))

  [([{:tag :logic-variable :symbol ?symbol} ?target] & ?rest) ?env]
  (`m.runtime/bind-logic-variable [$state ('quote ?symbol) ?target]
   (me/cata [?rest {('quote ?symbol) ?symbol & ?env}]))

  ;; :map
  ;; ----

  [([{:tag :map, :next ?next} ?target] & ?rest) ?env]
  (if (clojure.core/map? ?target)
    (me/cata [([?next ?target] & ?rest) ?env])
    (`m.runtime/fail))

  ;; :memory-variable
  ;; ----------------

  [([{:tag :memory-variable :symbol ?symbol} ?target] & ?rest) {?key ?symbol :as ?env}]
  (let* [$value (clojure.core/get $state ?key)
         $value (clojure.core/conj $value ?target)
         $state (clojure.core/assoc $state ?key $value)]
    (me/cata [?rest ?env]))

  [([{:tag :memory-variable :symbol ?symbol} ?target] & ?rest) ?env]
  (let* [$state (`m.runtime/bind-memory-variable $state ('quote ?symbol) ?target)]
    (me/cata [?rest {('quote ?symbol) ?symbol & ?env}]))

  ;; :reference
  ;; ----------

  [([{:tag :reference, :symbol ?symbol} ?target] & ?rest) ?env]
  (clojure.core/mapcat
   (fn [$state] (me/cata [?rest ?env]))
   (?symbol ?target $state))

  ;; :root
  ;; -----

  [([{:tag :root, :next ?next} ?target] & ?rest) ?env]
  (let* [$state {}]
    (me/cata [([?next ?target] & ?rest) ?env]))

  ;; :seq
  ;; ----

  [([{:tag :seq :next ?next} (& _ :as ?target) & ?rest]) ?env]
  (me/cata [([?next ?target] & ?rest) ?env])

  [([{:tag :seq, :next ?next} ?target] & ?rest) ?env]
  (if (clojure.core/seq? ?target)
    (me/cata [([?next ?target] & ?rest) ?env]))

  ;; :some-map
  ;; ---------

  [([{:tag :some-map} _] & ?rest) ?env]
  (me/cata [?rest ?env])

  ;; :star
  ;; -----

  (me/and [([{:tag :star
              :pattern {:tag :cat, :sequence [!xs ..?n]
                        :next {:tag :empty}}
              :next {:tag :empty}} ?target] & ?rest) ?env]
          (me/let [(!nth-symbol ... :as ?nth-symbols) (repeatedly ?n gensym)
                   (!nth-symbol ...) ?nth-symbols
                   (!index ...) (range ?n)
                   ?take-symbol (gensym)
                   ?drop-symbol (gensym)
                   ?goal-symbol (gensym)]))
  (let* [?goal-symbol
         (fn* ?goal-symbol
              ([$input $state]
               (if (clojure.core/seq $input)
                 (`m.runtime/bind [?take-symbol (`m.runtime/-take $input ?n)]
                  (let* [!nth-symbol (clojure.core/nth ?take-symbol !index) ..?n]
                    (`m.runtime/bind [?drop-symbol (`m.runtime/-drop $input ?n)]
                     (clojure.core/mapcat
                      (fn [$state]
                        (?goal-symbol ?drop-symbol $state))
                      (me/cata [([!xs !nth-symbol] ..?n) ?env])))))
                 (`m.runtime/succeed $state))))]
    (clojure.core/mapcat
     (fn [$state]
       (me/cata [?rest ?env]))
     (?goal-symbol ?target $state)))


  (me/and [([{:tag :star :pattern ?pattern :next ?next} ?target] & ?rest) ?env]
          (me/let [?partitions-symbol (gensym "partitions__")
                   ?partition-symbol (gensym "partition__")
                   ?left-symbol (gensym "left__")
                   ?right-symbol (gensym "right__")
                   ?goal-symbol (gensym "goal__")]))
  (let* [?goal-symbol
         (fn* ?goal-symbol
              ([$input $state]
               (let* [?partitions-symbol (`m.runtime/partitions $input)]
                 (clojure.core/mapcat
                  (fn*
                   ([?partition-symbol]
                    (let* [?left-symbol (clojure.core/nth ?partition-symbol 0)
                           ?right-symbol (clojure.core/nth ?partition-symbol 1)]
                      (clojure.core/mapcat
                       (fn*
                        ([$state]
                         (`m.runtime/knit
                          [(?goal-symbol ?right-symbol $state)
                           (me/cata [([?next ?right-symbol] & ?rest) ?env])])))
                       (me/cata [([?pattern ?left-symbol]) ?env])))))
                  ?partitions-symbol))))]
    (?goal-symbol ?target $state))

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
   ?env]
  (letfn [(!symbol [$input $state]
            (me/cata [([!pattern $input]) ?env]))
          ...]
    (me/cata [?rest ?env]))

  ;; Success!

  [() ?env]
  (`m.runtime/succeed $state)

  ;; Probably not.

  ?x ?x)

(defmacro solve [expression pattern]
  (let [ast (parse pattern)
        ast {:tag :root :next ast}
        target (gensym "target__")
        match-form (match-compile [(list [ast target]) {}])]
    `(let [~target ~expression]
       ~match-form)))

(defmacro report [expr pattern action]
  (let [x (gensym)
        solve-form `(solve ~x ~pattern)
        search-form `(me/search ~expr ~pattern ~action)]
    `(let [~x ~expr]
       {:pattern '~pattern
        :solve (with-out-str (time (dotimes [_# 1000] (doall ~solve-form))))
        :solve-expand (with-out-str (time (dotimes [_# 100] (macroexpand '~solve-form))))
        :search (with-out-str (time (dotimes [_# 1000] (doall ~search-form))))
        :search-expand (with-out-str (time (dotimes [_# 100] (macroexpand '~search-form))))
        :solve-answer ~solve-form
        :search-answer ~search-form})))

;; (report (into [] (repeat 10 1)) [!xs ...] {'!xs !xs})
;; (report (into [] (repeat 10 1)) [?x . ?x ...] {'?x ?x})
;; (let [root {:tag :root :next (parse '(1 2 3))}]
;;   (match-compile [(list [root 'target]) {}]))
;; (solve '(1 2 3) (?x ?y ?z))
