(ns meander.syntax.zeta
  (:require [meander.util.zeta :as m.util]
            [meander.db.zeta :as db])
  #?(:cljs (:require-macros [meander.syntax.zeta])))

#?(:clj
   (defn dispatch-fn [^clojure.lang.MultiFn multi-fn]
     (.dispatchFn multi-fn)))

(defmacro satisfies-multi-fn?
  {:private true}
  [multi-fn x]
  `(contains? (methods ~multi-fn) ((dispatch-fn ~multi-fn) ~x)))

(defn ast? [x]
  (and (map? x) (contains? x :tag)))

(defn tag [ast]
  {;;:pre [(ast? ast)]
   :post (keyword? %)}
  (get ast :tag))

;; AST interfaces
;; ---------------------------------------------------------------------

(defmulti branch?
  "true if `ast` is a branch node e.g. it has children."
  {:arglists '([ast])}
  #'tag
  :default ::default)

(defmethod branch? ::default
  [ast] false)

(defmulti children
  "Return a sequence of AST nodes that are the children of this `ast`."
  {:arglists '([ast])}
  #'tag
  :default ::default)

(defmethod children ::default
  [ast] [])

(defmulti make-node
  "Given an AST node `ast` and a sequence of AST nodes `children`
  construct a new `ast` node."
  {:arglists '([ast new-children])}
  (fn [ast new-children]
    (tag ast))
  :default ::default)

(defmethod make-node ::default
  [ast new-children]
  ast)

(defmulti min-length
  {:arglists '([ast])}
  #'tag)

(defn min-length?
  "true if `x` has a `min-length` method, false otherwise."
  [x]
  (satisfies-multi-fn? min-length x))

(defmulti max-length
  {:arglists '([ast])}
  #'tag)

(defn max-length?
  "true if `x` has a `max-length` method, false otherwise."
  [x]
  (satisfies-multi-fn? max-length x))

(defn variable-length?
  "true if `ast` has a variable length."
  [ast]
  {:pre [(min-length? ast)
         (max-length? ast)]}
  (not (= (min-length ast) (max-length ast))))

;; Traversal
;; ---------

(defn fold
  [f init ast]
  (reduce f init (tree-seq branch? children ast)))

(defn fmap
  [f ast]
  (make-node ast (map f (children ast))))

(defn prewalk [f ast]
  (let [ast* (f ast)]
    (if (reduced? ast*)
      (unreduced ast*)
      (fmap (fn [ast] (prewalk f ast)) ast*))))

(defn postwalk [f ast]
  (f (fmap (fn [ast] (postwalk f ast)) ast)))


;; AST
;; ---------------------------------------------------------------------

;; :as
;; ---

(defn make-as [pattern-ast next-ast]
  {:tag :as
   :pattern pattern-ast
   :next next-ast})

(defmethod min-length :as
  [ast]
  (min-length (get ast :next)))

(defmethod max-length :as
  [ast]
  (max-length (get ast :next)))

;; :cat
;; ----

(defmethod min-length :cat
  [ast]
  (inc (min-length (get ast :next))))

(defmethod max-length :cat
  [ast]
  (inc (max-length (get ast :next))))

(defmethod branch? :cat
  [ast] true)

(defmethod children :cat
  [ast]
  [(get ast :pattern)
   (get ast :next)])


;; :empty
;; ------

(defn empty-ast?
  [x]
  (and (map? x)
       (= (get x :tag) :empty)))

(defmethod min-length :empty
  [ast] 0)

(defmethod max-length :empty
  [ast] 0)

;; :literal
;; --------

(defn make-literal
  [form]
  {:tag :literal
   :form form})

;; :logic-variable
;; ----------------

(defn logic-variable-name? [x]
  (and (string? x)
       (m.util/re-matches? #"^\?.+" x)))

(defn logic-variable-symbol?
  "true if `x` is a logic variable symbol, false otherwise."
  [x]
  (and (simple-symbol? x)
       (logic-variable-name? (name x))))

(defn make-logic-variable
  "Construct a logic variable from with `symbol`."
  ([]
   (make-logic-variable (gensym "?X__")))
  ([symbol]
   {:pre [(logic-variable-symbol? symbol)]}
   {:tag :logic-variable
    :symbol symbol}))

;; :memory-variable
;; ----------------

(defn memory-variable-name? [x]
  (and (string? x)
       (m.util/re-matches? #"^!.+" x)))

(defn memory-variable-symbol?
  "true if `x` is a memory variable symbol, false otherwise."
  [x]
  (and (simple-symbol? x)
       (memory-variable-name? (name x))))

(defn make-memory-variable
  "Construct a memory variable from with `symbol`."
  ([] (make-memory-variable (gensym "!X__")))
  ([symbol]
   {:pre [(memory-variable-symbol? symbol)]}
   {:tag :memory-variable
    :symbol symbol}))

;; :mutable-variable
;; -----------------

(defn mutable-variable-name?
  [x]
  (and (string? x)
       (m.util/re-matches? #"^\*.+" x)))

(defn mutable-variable-symbol?
  [x]
  (and (simple-symbol? x)
       (mutable-variable-name? (name x))))

(defn make-mutable-variable
  [symbol]
  {:pre [(mutable-variable-symbol? symbol)]}
  {:tag :mutable-variable
   :symbol symbol})

;; :partition
;; ----------

(defmethod branch? :partition [ast]
  true)

(defmethod children :partition [ast]
  [(get ast :left)
   (get ast :right)])


;; :reference
;; ----------

(defn reference-name?
  [x]
  (and (string? x)
       (m.util/re-matches? #"^%.+" x)))

(defn reference-symbol?
  [x]
  (and (simple-symbol? x)
       (reference-name? (name x))))

(defn make-reference
  [symbol]
  {:pre [(reference-symbol? symbol)]}
  {:tag :reference
   :symbol symbol})

;; :seq
;; ----

(defmethod min-length :seq
  [ast]
  (min-length (get ast :pattern)))

(defmethod max-length :seq
  [ast]
  (max-length (get ast :pattern)))

(defmethod branch? :seq
  [ast] true)

(defmethod children :seq
  [ast]
  [(get ast :pattern)])

;; :star
;; -----

(defmethod min-length :star
  [ast]
  (min-length (get ast :next)))

(defmethod max-length :star
  [ast] ##Inf)

(defmethod branch? :star
  [ast] true)

(defmethod children :star [ast]
  [(get ast :pattern)])

;; :vector
;; -------

(defmethod min-length :vector
  [ast]
  (min-length (get ast :pattern)))

(defmethod max-length :vector
  [ast]
  (max-length (get ast :pattern)))

(defmethod branch? :vector
  [ast] true)

(defmethod children :vector
  [ast]
  [(get ast :pattern)])

;; :wildcard
;; ---------

(defn wildcard-name?
  [x]
  (and (string? x)
       (m.util/re-matches? #"^_.*" x)))

(defn wildcard-symbol?
  [x]
  (and (simple-symbol? x)
       (wildcard-name? (name x))))

(defn make-wildcard
  [symbol]
  {:pre [(wildcard-symbol? symbol)]}
  {:tag :wildcard
   :symbol symbol})

;; Misc
;; ----

(defmacro with-fresh-variables [binding-symbols & body]
  `(let [~@(mapcat
            (fn [binding-symbol]
              (cond
                (logic-variable-symbol? binding-symbol)
                `[~binding-symbol (make-logic-variable)]

                (memory-variable-symbol? binding-symbol)
                `[~binding-symbol (make-memory-variable)]))
            binding-symbols)]
     ~@body))

;; Parser
;; ---------------------------------------------------------------------

(declare parse*)

(defn parse-all*
  {:private true}
  [xs env]
  (mapv (fn [x] (parse* x env)) xs))

(defn as-ast?
  [x]
  (= x {:tag :literal
        :type :keyword
        :form :meander.zeta/as}))

(defn &-ast?
  [x]
  (= x {:tag :literal
        :type :symbol
        :form '&}))

(defn logic-variable?
  [x]
  (and (map? x) (= (get x :tag) :logic-variable)))

(defn memory-variable?
  [x]
  (and (map? x) (= (get x :tag) :memory-variable)))

(defn mutable-variable?
  [x]
  (and (map? x) (= (get x :tag) :mutable-variable)))

(defn variable?
  [x]
  (or (logic-variable? x) (memory-variable? x)))

(defn ampersand-symbol?
  [x] (= x '&))

;; Symbol parsing
;; --------------

(defn parse-symbol
  {:private true}
  [sym]
  (if (namespace sym)
    {:tag :literal
     :type :symbol
     :form sym}
    (let [s (name sym)
          [$0 $N $L $M] (re-matches #"\.(?:\.(?:\.|(\d+)|(\?.+)|(!.+))?)?" s)]
      (cond
        ;; `..<nat-int>`
        (some? $N)
        (if (= $N "0")
          ;; `..0` is the same as `...`.
          {:tag :...
           :form sym}
          {:tag :..n
           :n (m.util/parse-int $N)
           :form sym})

        ;; `..?<name>`
        (some? $L)
        {:tag :..?n
         :logic-variable (make-logic-variable (symbol $L))
         :form sym}

        ;; `..!<name>`
        (some? $M)
        {:tag :..!n
         :memory-variable (make-memory-variable (symbol $M))
         :form sym}

        :else
        (case $0
          ;; Internal tag for postfix partition.
          "."
          {:tag :.
           :form sym}

          ;; Internal tag for postfix n or more operator.
          ".."
          {:tag :..n
           :n $N
           :form sym}

          ;; Internal tag for postfix 0 or more operator.
          "..."
          {:tag :...
           :form sym}

          nil
          (cond
            (wildcard-symbol? sym)
            {:tag :wildcard
             :symbol sym
             :form sym}

            (logic-variable-symbol? sym)
            {:tag :logic-variable
             :symbol sym
             :form sym}

            (memory-variable-symbol? sym)
            {:tag :memory-variable
             :symbol sym
             :form sym}

            (reference-symbol? sym)
            {:tag :reference
             :symbol sym
             :form sym}

            (mutable-variable-symbol? sym)
            {:tag :mutable-variable
             :symbol sym
             :form sym}

            (ampersand-symbol? sym)
            {:tag :ampersand
             :symbol sym
             :form sym}

            :else
            {:tag :literal
             :type :symbol
             :form sym}))))))

;; Seq parsing
;; -----------

(defn subsequence-splitter?
  {:private true}
  [ast]
  (case (get ast :tag)
    (:.
     :..!n
     :...
     :..?n
     :..n
     :ampersand)
    false

    ;; else
    (not (as-ast? ast))))

(defn subsequence-operator-type
  {:private true}
  [ast]
  (let [tag (get ast :tag)]
    (case tag
      (:.
       :..!n
       :...
       :..?n
       :..n
       :ampersand)
      tag

      :literal
      (if (as-ast? ast)
        :as
        nil)

      ;; else
      nil)))


(defn expand-subsequence
  {:private true}
  [asts]
  (letfn [(expand-cats [outer-asts inner-ast]
            (reduce
              (fn [next-ast pattern-ast]
                {:tag :cat
                 :pattern pattern-ast
                 :next next-ast})
              inner-ast
              (reverse outer-asts)))

          (expand-ampersand [operator-ast prev-asts next-asts]
            (if-some [pattern-ast (first next-asts)]
              (let [next-ast (expand-subsequence (next next-asts))]
                (case (get next-ast :tag)
                  :as
                  {:tag :as
                   :pattern (get next-ast :pattern)
                   :next (expand-cats prev-asts {:tag :tail
                                                 :pattern pattern-ast
                                                 :next (get next-ast :next)})}

                  ;; else
                  (expand-cats prev-asts {:tag :tail
                                          :pattern pattern-ast
                                          :next next-ast})))
              {:tag :error
               :message "The & operator must be followed by another pattern"
               :data (meta (get operator-ast :form))}))

          (expand-as [operator-ast prev-asts next-asts]
            (let [pattern-ast (first next-asts)]
              (if (variable? pattern-ast)
                (let [next-asts (next next-asts)]
                  (if (seq next-asts)
                    {:tag :error
                     :message "The :meander.zeta/as operator may only be followed by a variable pattern"
                     :data nil}
                    {:tag :as
                     :pattern pattern-ast
                     :next (expand-cats prev-asts {:tag :empty})}))
                {:tag :error
                 :message "The :meander.zeta/as operator must be followed by a variable pattern"
                 :data nil})))

          (expand-dot [operator-ast prev-asts next-asts]
            (let [next-ast (expand-subsequence next-asts)]
              (case (get next-ast :tag)
                :as
                {:tag :as
                 :pattern (get next-ast :pattern)
                 :next (expand-cats prev-asts (get next-ast :next))}

                ;; else
                (expand-cats prev-asts next-ast))))

          (expand-dot-dot-!n [operator-ast prev-asts next-asts]
            (let [next-ast (expand-subsequence next-asts)]
              (case (get next-ast :tag)
                :as
                {:tag :as
                 :pattern (get next-ast :pattern)
                 :next {:tag :cat
                        :pattern {:tag :memory-plus
                                  :operator operator-ast
                                  :pattern (expand-cats prev-asts {:tag :empty})}
                        :next (get next-ast :next)}}

                ;; else
                {:tag :cat
                 :pattern {:tag :memory-plus
                           :operator operator-ast
                           :pattern (expand-cats prev-asts {:tag :empty})}
                 :next next-ast})))

          (expand-dot-dot-dot [operator-ast prev-asts next-asts]
            (let [next-ast (expand-subsequence next-asts)]
              (case (get next-ast :tag)
                :as
                {:tag :as
                 :pattern (get next-ast :pattern)
                 :next {:tag :star
                        :operator operator-ast
                        :pattern (expand-cats prev-asts {:tag :empty})
                        :next (get next-ast :next)}}

                ;; else
                {:tag :star
                 :operator operator-ast
                 :pattern (expand-cats prev-asts {:tag :empty})
                 :next next-ast})))

          (expand-dot-dot-n [operator-ast prev-asts next-asts]
            (let [next-ast (expand-subsequence next-asts)]
              (case (get next-ast :tag)
                :as
                {:tag :as
                 :pattern (get next-ast :pattern)
                 :next {:tag :cat
                        :pattern {:tag :plus
                                  :operator operator-ast
                                  :pattern (expand-cats prev-asts {:tag :empty})}
                        :next (get next-ast :next)}}

                ;; else
                {:tag :cat
                 :pattern {:tag :plus
                           :operator operator-ast
                           :pattern (expand-cats prev-asts {:tag :empty})}
                 :next next-ast})))

          (expand-dot-dot-?n [operator-ast prev-asts next-asts]
            (let [next-ast (expand-subsequence next-asts)
                  pattern-ast (expand-cats prev-asts {:tag :empty})]
              (case (get next-ast :tag)
                :as
                {:tag :as
                 :pattern (get next-ast :pattern)
                 :next {:tag :cat
                        :pattern {:tag :logical-plus
                                  :operator operator-ast
                                  :pattern pattern-ast}
                        :next (get next-ast :next)}}

                ;; else
                {:tag :cat
                 :pattern {:tag :logical-plus
                           :operator operator-ast
                           :pattern pattern-ast}
                 :next next-ast})))]
    (let [[prev-asts [operator-ast & next-asts]] (split-with subsequence-splitter? asts)]
      (case (subsequence-operator-type operator-ast)
        :.
        (expand-dot operator-ast prev-asts next-asts)

        :..!n
        (expand-dot-dot-!n operator-ast prev-asts next-asts)

        :...
        (expand-dot-dot-dot operator-ast prev-asts next-asts)

        :..?n
        (expand-dot-dot-?n operator-ast prev-asts next-asts)

        :..n
        (expand-dot-dot-n operator-ast prev-asts next-asts)

        :ampersand
        (expand-ampersand operator-ast prev-asts next-asts)

        :as
        (expand-as operator-ast prev-asts next-asts)

        ;; else
        (expand-cats prev-asts {:tag :empty})))))

(defn parse-seq-not-special
  {:private true}
  [xs env]
  (let [asts (parse-all* xs env)
        ast (expand-subsequence asts)
        ast (case (tag ast)
              :as
              {:tag :as
               :pattern (get ast :pattern)
               :next {:tag :seq
                      :pattern (get ast :next)
                      :form xs}}
              ;; else
              {:tag :seq
               :pattern ast
               :form xs})]
    ast))

(defn parse-set
  "Parses a `set?` into a `:meander.syntax.zeta/ast`. "
  {:private true}
  [xs env]
  (let [&-form (some
                (fn [x]
                  (if (= (get (meta x) :tag) '&)
                    x))
                xs)
        xs (if &-form
             (disj xs &-form)
             xs)
        as-form (some
                (fn [x]
                  (if (true? (get (meta x) :meander.zeta/as))
                    x))
                xs)
        xs (if as-form
             (disj xs as-form)
             xs)
        &-node (parse* &-form env)
        as-node (parse* as-form env)
        x-nodes (parse-all* xs env)
        set-node {:tag :set
                  :form xs
                  :elements x-nodes
                  :rest &-node}]
    (if as-node
      {:tag :as
       :pattern as-node
       :next set-node}
      set-node)))

(defn parse-seq
  "Parses a `seq?` into a `:meander.syntax.zeta/ast`.

  `seq?`s of the following form are handled specially, all other seqs
  are parsed as `:seq` ASTs.

      (quote <form>)
      (clojure.core/unquote <form>)
      (clojure.core/unquote-splicig <form>)
      (<symbol*> <form_0> ... <form_n>)

  where `symbol*` is a fully qualified symbol with respect to the
  current namespace."
  {:private true}
  [xs env]
  (let [x (first xs)]
    (if (symbol? x)
      (case x
        quote
        {:tag :quote
         :form xs}

        clojure.core/unquote
        {:tag :unquote
         :expr (second xs)
         :form xs}

        clojure.core/unquote-splicing
        {:tag :unquote-splicing
         :expr (second xs)
         :form xs}

        ;; else
        (parse-seq-not-special xs env))
      (parse-seq-not-special xs env))))

;; Vector parsing
;; --------------

(defn parse-vector
  {:private true}
  [xs env]
  (let [asts (parse-all* xs env)
        ast (expand-subsequence asts)
        ast (case (tag ast)
              :as
              {:tag :as
               :pattern (get ast :pattern)
               :next {:tag :vector
                      :pattern (get ast :next)
                      :form xs}}
              ;; else
              {:tag :vector
               :pattern ast
               :form xs})]
    ast))

(defn parse-map
  [form env]
  (if-some [[as-key as-val] (find form :meander.zeta/as)]
    (let [as-ast (parse* as-val env)]
      (if (variable? as-ast)
        (let [form* (dissoc form :meander.zeta/as)
              next-ast (parse* form* env)]
          {:tag :as
           :pattern as-ast
           :next next-ast})
        {:tag :error
         :message "The :meander.zeta/as operator must be followed by a variable pattern"
         :data nil}))
    (if-some [[_ rest-val] (find form '&)]
      (let [form* (dissoc form '&)
            rest-ast (parse* rest-val env)
            entry-asts (mapv
                        (fn [[k v]]
                          {:tag :entry
                           :key (parse* k env)
                           :val (parse* v env)})
                        form*)]
        {:tag :map
         :entries entry-asts
         :rest rest-ast})
      (let [entry-asts (mapv
                        (fn [[k v]]
                          {:tag :entry
                           :key (parse* k env)
                           :val (parse* v env)})
                        form)]
        {:tag :map
         :entries entry-asts
         :rest nil}))))

(defn parse*
  [form env]
  (cond
    (char? form)
    {:tag :literal
     :type :char
     :form form}

    (keyword? form)
    {:tag :literal
     :type :keyword
     :form form}

    (map? form)
    (parse-map form env)

    (number? form)
    {:tag :literal
     :type :number
     :form form}

    (set? form)
    (parse-set form env)

    (seq? form)
    (parse-seq form env)

    (string? form)
    {:tag :literal
     :type :string
     :form form}

    (symbol? form)
    (parse-symbol form)

    (vector? form)
    (parse-vector form env)

    :else
    {:tag :literal
     :type :unknown
     :form form}))

(defn make-parse-env []
  {:ns nil
   :expander-registry {}
   :parser-registry {}})

(defn parse
  ([form]
   (parse* form (make-parse-env)))
  ([form env]
   (parse* form env)))
