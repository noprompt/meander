(ns meander.epsilon
  (:refer-clojure :exclude [and find keyword let not or some symbol])
  (:require [clojure.core :as clj]
            [meander.match.epsilon :as r.match]
            [meander.match.syntax.epsilon :as r.match.syntax]
            [meander.rewrite.epsilon :as r.rewrite]
            [meander.strategy.epsilon :as r]
            [meander.syntax.epsilon :as r.syntax]
            [meander.substitute.epsilon :as r.subst]
            [meander.substitute.syntax.epsilon :as r.subst.syntax]
            [meander.util.epsilon :as r.util]))

;; ---------------------------------------------------------------------
;; Match, Find, Search

(defmacro match
  "Basic pattern matching macro.

  Syntax:

      (match x
        pattern_1 expr_1
        ,,,
        pattern_n expr_n)

  Attempts to pattern match `x` against one of patterns `pattern_1`
  through `pattern_n`. If some pattern `pattern_i` matches
  successfully, `expr_i` will be executed. If none of the patterns
  match successfully an error will be thrown indicating the pattern
  match failed.

  This operator restricts ambiguous patterns i.e. patterns which have
  more than one possible match. For example, the pattern

      #{?x ?y}

  matches any set with at least two elements. However, with
  consideration to the property that Clojure sets are unordered, there
  are many possible ways we could bind values for `?x` and
  `?y`. Because there is no obvious way to know which solution to
  pick, patterns which have this property are illegal in the context
  of this operator.

  For operators which relax this restriction, see `find` and `search`."
  {:style/indent :defn}
  ([x pattern expr]
   (with-meta `(meander.match.epsilon/match ~x ~pattern ~expr)
     (meta &form)))
  ([x pattern expr & more-clauses]
   (assert (even? (count more-clauses)) "match requires an even number of clauses")
   (with-meta `(meander.match.epsilon/match ~x ~pattern ~expr ~@more-clauses)
     (meta &form))))


(defmacro find
  "Like `search` but returns only the first successful match.

  Syntax:

      (find x
        pattern_1 expr_1
        ,,,
        pattern_n expr_n)
  "
  {:style/indent :defn}
  [x & clauses]
  (assert (even? (count clauses)) "find requires an even number of clauses")
  (with-meta `(meander.match.epsilon/find ~x ~@clauses)
    (meta &form)))

(defmacro search
  "Like `match` but allows for ambiguous patterns and returns a lazy
  sequence of all expression values in depth-first order.

  Syntax:

      (search x
        pattern_1 expr_1
        ,,,
        pattern_n expr_n)

  Example:

      (search [1 2 3]
        [!xs ... !ys ...]
        {'!xs !xs, '!ys !ys})
      ;; =>
      ({!xs [], !ys [1 2 3]}
       {!xs [1], !ys [2 3]}
       {!xs [1 2], !ys [3]}
       {!xs [1 2 3], !ys []})

  Example:
  
      (search {:foo 1, :bar 2, :baz 1, :quux 2}
        {?k 1}
        [?k :one]
      
        {?k 2}
        [?k :two])
      ;; =>
      ([:foo :one]
       [:baz :one]
       [:quux :two]
       [:bar :two])

  Note, if only the first value is needed, use `find` instead. The
  expression

      (first (search x ,,,))

  can be significantly slower than

      (find x ,,,)"
  {:style/indent :defn}
  [x & clauses]
  (assert (even? (count clauses)) "search requires an even number of clauses")
  `(meander.match.epsilon/search ~x ~@clauses))

(defmacro breadth-first-search
  "Like `search` but traverses the search space in breadth first
  order."
  {:style/indent :defn}
  [x & clauses]
  (assert (even? (count clauses)) "breadth-first-search requires an even number of clauses")
  (with-meta `(meander.match.epsilon/search ~x ~@clauses)
    (assoc (meta &form) :search-order :breadth-first)))

;; ---------------------------------------------------------------------
;; Substitute

(defmacro subst
  ;; TODO: This is not enough documentation.
  "Substitution operator, the inverse of pattern matching. Evaluates
  `pattern` in the Clojure environment."
  {:style/indent :defn}
  [pattern]
  (clj/let [node (r.subst.syntax/parse pattern &env)
            x (r.subst/compile node &env)]
    (if (= ::CATA_NOT_BOUND x)
      (throw (ex-info "cata is not allowed in subst; use `rewrite`"
                      {:pattern pattern}))
      x)))

;; ---------------------------------------------------------------------
;; Rewrite

(defmacro rewrite
  "Given some data `x`, match a `match-pattern` and substitute with
  the `subst-pattern`.

  Syntactic sugar for

       (find x
         p_1 (subst p_2)
         ,,,
         p_n-1 (subst p_n))"
  {:arglists '([x match-pattern subst-pattern & more-pattern-pairs])
   :style/indent :defn}
  [x & clauses]
  (assert (even? (count clauses)) "rewrite requires an even number of clauses")
  (clj/let [y (r.rewrite/compile-rewrite-args (list* x clauses) &env)]
    (if (instance? Exception y)
      (throw y)
      y)))

(defmacro rewrites
  "Like `rewrite` but is sugar for

       (search x
         p_1 (subst p_2)
         ,,,
         p_n-1 (subst p_n))"

  {:style/indent :defn}
  [x & clauses]
  (assert (even? (count clauses)) "rewrites requires an even number of clauses")
  (clj/let [y (r.rewrite/compile-rewrites-args (list* x clauses) &env)]
    (cond
      (instance? Exception y)
      (throw y)

      (= ::r.rewrite/CATA_NOT_IMPLEMENTED y)
      (throw (ex-info "cata is not implemented for rewrites" {}))

      :else
      y)))

;; ---------------------------------------------------------------------
;; Syntax extensions

(defn match-syntax?
  [env]
  (match env
    {::r.syntax/phase :meander/match}
    true

    _
    false))

(defn subst-syntax?
  [env]
  (match env
    {::r.syntax/phase :meander/substitute}
    true

    _
    false))

(defmacro defsyntax
  {:arglists '([name doc-string? attr-map? [params*] prepost-map? body]
               [name doc-string? attr-map? ([params*] prepost-map? body) + attr-map?])
   :style/indent :defn}
  [& args]
  `(r.syntax/defsyntax ~@args))



(defsyntax and
  "Pattern matching operator which matches when `pattern` and,
  optionally, all of `patterns` match."
  [pattern & patterns]
  (case (::r.syntax/phase &env)
    :meander/match
    `(r.match.syntax/and ~pattern ~@patterns)
    ;; else
    &form))

(defsyntax or
  "Pattern matching operator which matches when either `pattern` or,
  opitionally, one of `patterns` match."
  [pattern & patterns]
  (case (::r.syntax/phase &env)
    :meander/match
    `(r.match.syntax/or ~pattern ~@patterns)
    ;; else
    &form))

(defsyntax not
  "Pattern matching operator which matches when `pattern` does not
  match."
  [pattern]
  (case (::r.syntax/phase &env)
    :meander/match
    `(r.match.syntax/not ~pattern)
    ;; else
    &form))

(defsyntax let
  {:style/indent 1}
  ([binding-patterns]
   (case (::r.syntax/phase &env)
     :meander/match
     (meander.match.epsilon/match binding-patterns
       [_ _ ...]
       (reduce
        (fn [?inner [?pattern ?expression]]
          (r.subst/substitute (`r.match.syntax/let ?pattern ?expression ?inner)))
        '_
        (reverse (partition 2 binding-patterns)))

       _
       (throw (ex-info "The second argument to let must be a vector with an even number of elements"
                       {:form &form
                        :meta (meta &form)})))
     ;; else
     &form))
  ([binding-patterns target-pattern]
   (case (::r.syntax/phase &env)
     :meander/match
     (meander.match.epsilon/match binding-patterns
       [_ _ ...]
       (reduce
        (fn [?inner [?pattern ?expression]]
          (r.subst/substitute (`r.match.syntax/let ?pattern ?expression ?inner)))
        target-pattern
        (reverse (partition 2 binding-patterns)))

       _
       (throw (ex-info "The second argument to let must be a vector with an even number of elements"
                       {:form &form
                        :meta (meta &form)})))

     ;; else
     &form)))

(defsyntax pred
  "Pattern matching operator which successfully matches whenever the
  target of pattern matching applied to `expr` returns a truthy
  value.

      (match 1
        (pred odd?)
        :okay)
      ;; => :okay

  Optionally, additional patterns `patterns` may be passed in which
  case they will be treated as an `and` pattern against the target of
  pattern matching.

      (match 1
        (pred odd? ?x)
        :okay)

  is the same as

      (match 1
        (and (pred odd?) ?x)
        :okay)"
  {:arglists '([expr] [expr & patterns])}
  ([p]
   (case (::r.syntax/phase &env)
     :meander/match
     `(r.match.syntax/pred ~p)

     ;; else
     &form))
  ([p pattern]
   (case (::r.syntax/phase &env)
     :meander/match
     `(r.match.syntax/pred ~p ~pattern)

     ;; else
     &form))
  ([p pattern & patterns]
   (case (::r.syntax/phase &env)
     :meander/match
     `(r.match.syntax/pred ~p (r.match.syntax/and ~pattern ~@patterns))

     ;; else
     &form)))

(defsyntax seqable
  "Pattern matching operator which matches the `seq` of anything that
  is `seqable?` against

      (p1 ,,, pn)

  where the sequence `p1` through `pn` is equal to `patterns`."
  [& patterns]
  (case (::r.syntax/phase &env)
    :meander/match
    `(r.match.syntax/pred seqable? (r.match.syntax/apply seq ~patterns))

    ;; else
    &form))

(defsyntax scan
  "Pattern matching operator which matches the `seq` of `seqable?`
  forms of the shape

      (_ ... p1 ,,, pn . _ ...)

  or `vectors?` of the form

      [_ ... p1 ,,, pn . _ ...]

  where the sequence `p1` through `pn` is equal to `patterns`."
  [& patterns]
  (case (::r.syntax/phase &env)
    :meander/match
    (clj/let [[as-pattern as & not-as] (reverse patterns)
              inner (if (= :as as)
                      `(~@'(_ ...) ~@(reverse not-as) ~@'(. _ ...) :as ~as-pattern)
                      `(~@'(_ ...) ~@patterns ~@'(. _ ...)))]
      `(seqable ~@inner)
      ;; Using `or` like this can cause code explosions. Come back to this
      ;; when we support `<>` fragments.
      #_
      `(r.match.syntax/or
        [~@inner]
        ;; Prevent producing the same search results twice when
        ;; the target is a vector.
        (r.match.syntax/and (r.match.syntax/not (r.match.syntax/pred vector?))
                            (seqable ~@inner))))

    ;; else
    &form))

(defsyntax separated
  "Pattern matching operator which matches the `seq` of `seqable?`
  forms of the shape

      (_ ... p1 ,,, . _ ... pn . _ ...)

  or `vectors?` of the form

      [_ ... p1 ,,, . _ ... pn . _ ...]

  where the sequence `p1` through `pn` is equal to `patterns`."
  [& patterns]
  (case (::r.syntax/phase &env)
    :meander/match
    (clj/let [inner `(~@'(_ ...) ~@(mapcat cons patterns (repeat '(. _ ...))))]
      `(r.match.syntax/or
        [~@inner]
        ;; Prevent producing the same search results twice when
        ;; the target is a vector.
        (r.match.syntax/and (r.match.syntax/not (r.match.syntax/pred vector?))
                            (seqable ~@inner))))

    ;; else
    &form))

(defsyntax app
  "Pattern matching operator which applies pattern matching the result
  applying `f` to the current value being matched."
  ([f pattern]
   (case (::r.syntax/phase &env)
     :meander/match
     `(r.match.syntax/apply ~f ~pattern)

     :meander/substitute
     `(r.subst.syntax/apply ~f ~pattern)

     ;;
     &form))
  ([f pattern & patterns]
   (case (::r.syntax/phase &env)
     :meander/match
     `(r.match.syntax/apply ~f (r.match.syntax/and ~pattern ~@patterns))

     :meander/substitute
     `(r.subst.syntax/apply (partial apply ~f) [~pattern ~@patterns])

     ;; else
     &form)))

(defsyntax guard
  "Pattern matching operator which succeeds whenever `pred-expr`
  returns a truthy result. `pred-expr` is evaluated by Clojure."
  ([pred-expr]
   (case (::r.syntax/phase &env)
     :meander/match
     `(r.match.syntax/guard ~pred-expr)

     ;; else
     &form)))

(defsyntax re
  "Pattern matching operator which matches strings which match the
   regular expression `regex-pattern` with `re-matches`. Optionally, a
   second argument `capture-pattern` can be passed which will be
   matched against the result of the underlying `re-matches` call.

       (match \"foo\"
         (re #\"...\")
         true)
       ;; =>
       true

       (match \"foo\"
         (re #\"(.)(.)(.)\" [?0 ?1 ?2 ?3])
         [?0 ?1 ?2 ?3])
       ;; =>
       [\"foo\" \"f\" \"o\" \"o\"]"
  ([regex-pattern]
   (case (::r.syntax/phase &env)
     :meander/match
     `(r.match.syntax/re ~regex-pattern)
     ;; else
     &form))
  ([regex-pattern capture-pattern]
   (case (::r.syntax/phase &env)
     :meander/match
     `(r.match.syntax/re ~regex-pattern ~capture-pattern)
     ;; else
     &form)))

(defsyntax $
  "Pattern matching and substitution operator.

  When used as a pattern matching operator will attempt match
  `pattern` against some value that is a subtree of the target of
  pattern matching. Optionally, `context-pattern` (should be a
  variable pattern) can be passed and will be applied against a
  function. This function accepts one argument and when invoked with
  some value `x` will return the original, root target updated with
  the `x` installed at the place where `pattern` matched successfully.


      (match [:A 2]
        ($ (pred number? ?x))
        ?x)
      ;; => 2

      (match [:A 2]
        ($ ?context (pred number? ?x))
        (?context 3))
      ;; => [:A 3]"
  ([pattern]
   (case (::r.syntax/phase &env)
     (:meander/match :meander/substitute)
     `(r.syntax/$ ~pattern)

     ;; else
     &form))
  ([context-pattern pattern]
   (case (::r.syntax/phase &env)
     (:meander/match :meander/substitute)
     `(r.syntax/$ ~context-pattern ~pattern)

     ;; else
     &form)))

(defsyntax $*
  ([context pattern]
   (case (::r.syntax/phase &env)
     :meander/match
     `(and (~'$ ?context# (and ~pattern ?pattern#))
           (let [~context (fn
                            ([]
                             (?context# ?pattern#))
                            ([f#]
                             (?context# (f# ?pattern#)))
                            ([f# & args#]
                             (?context# (apply f# ?pattern# args#))))]))
     :meander/substitute
     `(app ~context ~pattern)

     ;; else
     &form))
  ([context pattern & patterns]
   (case (::r.syntax/phase &env)
     :meander/match
     `(and (~'$ ?context# (and ~pattern ~@patterns ?pattern#))
           (let [~context (fn
                            ([]
                             (?context# ?pattern#))
                            ([f#]
                             (?context# (f# ?pattern#)))
                            ([f# & args#]
                             (?context# (apply f# ?pattern# args#))))]))
     :meander/substitute
     `(app ~context ~pattern ~@patterns)

     ;; else
     &form)))

(defsyntax symbol
  "Pattern matching and substitution operator.

   When used as a pattern matching operator it will match a symbol
   with `name` and, optionally, `namespace`.

       (match 'foo/bar
         (symbol ?name)
         ?name)
       ;; => \"bar\"

       (match :foo/bar
         (symbol ?namespace ?name)
         [?namespace ?name])
       ;; => [\"foo\" \"bar\"]


   Additionally, the symbol itself can be pattern matched against
   the `:as` keyword argument.

       (match 'foo/bar
         (symbol _ _ :as ?symbol)
         ?symbol)
       ;; => 'foo/bar

   When used as a substutition operator it will create a symbol with
   `name` and, optionally, `namespace` e.g. it behaves the same as
   `clojure.core/symbol` in the context of normal substitution
   behavior.

       (subst (symbol \"foo\" \"bar\"))
       ;; => 'foo/bar

       ;; clojure.core/let
       (let [!namespaces [\"foo\" \"foo\"]
             !names [\"bar\" \"baz\"]]
         (subst [(symbol !namespaces !names) ...]))
       ;; => ['foo/bar 'foo/baz]

   For substitution the `:as` keyword argument is ignored."
  {:arglists '([name]
               [namespaces name]
               [namespace name & {:keys [as]}])}
  ([name]
   (case (::r.syntax/phase &env)
     :meander/match
     `(and (pred symbol?) (app name ~name))

     :meander/substitute
     `(app clj/symbol ~name)

     ;; else
     &form))
  ([namespace name]
   (case (::r.syntax/phase &env)
     :meander/match
     `(and (pred symbol?)
           (app namespace ~namespace)
           (app name ~name))

     :meander/substitute
     `(app clj/symbol ~namespace ~name)

     ;; else
     &form))
  ([namespace name & rest]
   (case (::r.syntax/phase &env)
     :meander/match
     (clj/let [keyword-args (into {} (map vec) (partition-all 2 rest))
               as (get keyword-args :as)]
       `(and (meander.epsilon/symbol ~namespace ~name) ~as))

     :meander/substitute
     (symbol namespace name)

     ;; else
     &form)))

(defsyntax keyword
  "Pattern matching and substitution operator.

   When used as a pattern matching operator it will match a keyword
   with `name` and, optionally, `namespace`.

       (match :foo/bar
         (keyword ?name)
         ?name)
       ;; => \"bar\"

       (match :foo/bar
         (keyword ?namespace ?name)
         [?namespace ?name])
       ;; => [\"foo\" \"bar\"]

   Additionally, the keyword itself can be pattern matched against
   the `:as` keyword argument.

       (match :foo/bar
         (keyword _ _ :as ?keyword)
         ?keyword)
       ;; => :foo/bar

   When used as a substutition operator it will create a keyword with
   `name` and, optionally, `namespace` e.g. it behaves the same as
   `clojure.core/keyword` in the context of normal substitution
   behavior.

       (subst (keyword \"foo\" \"bar\"))
       ;; => :foo/bar

       ;; clojure.core/let
       (let [!namespaces [\"foo\" \"foo\"]
             !names [\"bar\" \"baz\"]]
         (subst [(keyword !namespaces !names) ...]))
       ;; => [:foo/bar :foo/baz]

   For substitution the `:as` keyword argument is ignored."
  {:arglists '([name]
               [namespaces name]
               [namespace name & {:keys [as]}])}
  ([name]
   (case (::r.syntax/phase &env)
     :meander/match
     `(and (pred keyword?) (app name ~name))

     :meander/substitute
     `(app clj/keyword ~name)

     ;; else
     &form))
  ([namespace name]
   (case (::r.syntax/phase &env)
     :meander/match
     `(and (pred keyword?)
           (app namespace ~namespace)
           (app name ~name))

     :meander/substitute
     `(app clj/keyword ~namespace ~name)

     ;; else
     &form))
  ([namespace name & rest]
   (case (::r.syntax/phase &env)
     :meander/match
     (clj/let [keyword-args (into {} (map vec) (partition-all 2 rest))
               as (get keyword-args :as)]
       `(and (keyword ~namespace ~name) ~as))

     :meander/substitute
     (keyword namespace name)

     ;; else
     &form)))

(defsyntax with
  "Pattern matching and substitution operator.

   Syntax

       (with [%pattern-name pattern ...]
         target-pattern)

   Allows for patterns to be referenced by %pattern-name in
   target-pattern.

   Example

       (match [[1 2] [1 2]]
         (with [%x-y [?x ?y]]
           [%x-y %x-y])
         {:x ?x, :y ?y})
       ;; =>
       {:x 1, :y 2}"
  {:style/indent 1}
  ([pattern-bindings body]
   (case (::r.syntax/phase &env)
     (:meander/match :meander/substitute)
     (meander.epsilon/find pattern-bindings
       ;; Even number of syntactically valid bindings.
       [(meander.epsilon/symbol nil (meander.epsilon/re #"%.+")) _ ...]
       `(r.syntax/with ~pattern-bindings ~body)

       ;; Even number of bindings; one is invalid.
       (r.syntax/with [%invalid-name (not (symbol nil (re #"%.+")))
                       %invalid-namespace (symbol (not nil) _)
                       %invalid-binding (or %invalid-name %invalid-namespace)]
         [_ _ ... (and %invalid-binding ?x) _ . _ _ ...])
       (throw (ex-info "with binding form must be a simple symbol the name of which begins with \"%\""
                       {:invalid-binding ?x
                        :form &form}))

       ;; Invalid binding form.
       (not [_ _ ...])
       (throw (ex-info "first argument to with must be a vector of the form [%<name> <pattern> ...]"
                       {:invalid-bindings pattern-bindings
                        :form &form})))

     ;; else
     &form)))

(defsyntax gather
  "Pattern matching operator that works in a similar manner to
   clojure.core/filter.

  `gather` allows you to match a pattern over a seqable, ignoring any
  values that don't match.


      (match [1 2 3 4]
        (gather (m/pred even? !xs))
        !xs)
        ;; =>
        [2 4]

  `gather` also offers a two arity version that can be supplied a
  logic variable, memory variable, _ pattern, number, or an ellipsis
  that allows control over the repeat behavior of gather.

      (match [:a :b :a :a :c]
        (gather :a ?count)
        ?count)
        ;; =>
        3

      (match [:a :b :a :a :c]
        (gather :a ..4)
         true

         _
         false)
         ;; =>
         false"
  ([pattern]
   (case (::r.syntax/phase &env)
     :meander/match
     `(seqable (or ~pattern _#) ...)
     ;; else
     &form))
  ([pattern count-pattern]
   (case (::r.syntax/phase &env)
     :meander/match
     (cond
       ;; 3
       (nat-int? count-pattern)
       (clj/let [?n count-pattern
                 ellipsis (clj/symbol (str ".." ?n))]
         `(and (seqable (or (and ~pattern !gather#) _gather#) ~ellipsis)
               (guard (<= ~?n (count !gather#)))))

       (symbol? count-pattern)
       (clj/let [symbol-name (name count-pattern)]
         (if-some [[_ n] (re-matches #"..(\d+)" symbol-name)]
           ;; ..n
           (clj/let [?n (r.util/parse-int n)
                     ellipsis (clj/symbol (str ".." ?n))]
             `(and (seqable (or (and ~pattern !gather#) _gather#) ~ellipsis)
                   (guard (<= ~?n (count !gather#)))))
           (if-some [[_ var-name] (re-matches #"(?:\.\.)?((?:\?|!).+)" symbol-name)]
             ;; ..?x and ..!x
             (clj/let [?var (clj/symbol var-name)]
               `(and (seqable (or (and ~pattern !gather#) _gather#) ...)
                     (let [~?var (count !gather#)])))
             (if (re-matches #"_.*|\.\.\." symbol-name)
               ;; _ and ...
               `(seqable (or ~pattern _#) ...)
               (throw (ex-info "second argument to gather must be logic variable, memory variable, _ pattern, number, or an ellipsis"
                               {:form &form}))))))

       :else
       (throw (ex-info "second argument to gather must be logic variable, memory variable, _ pattern, number, or an ellipsis"
                       {:form &form})))

     ;; else
     &form)))

(defsyntax number
  "Pattern matching operator which matches a `number?`. Optionally
  `pattern` may be passed to further pattern match on the value."
  ([] `(number _#))
  ([pattern]
   (if (match-syntax? &env)
     `(pred number? ~pattern)
     &form)))

(defsyntax some
  "Pattern matching operator which matches a non `nil`
  value. Optionally, `pattern` may be passed to further match on the
  value."
  ([]
   (if (match-syntax? &env)
     `(not nil)
     &form))
  ([pattern]
   (if (match-syntax? &env)
     `(and (not nil) ~pattern)
     &form)))

(defsyntax cata
  "Pattern matching operator which causes pattern matching to recurse
  on the target of pattern matching. The result is then pattern
  matched with `pattern`.

      (match [1 2 3]
        [(cata ?x) (cata ?y) (cata ?z)]
        [?x ?y ?z]

        3
        :three

        2
        :two

        1
        :one)
      ;; =>
      [:one :two :three]"
  ([pattern]
   (cond
     (match-syntax? &env)
     `(r.match.syntax/cata ~pattern)

     (subst-syntax? &env)
     `(r.subst.syntax/cata ~pattern)

     :else
     &form)))

(defsyntax map-of
  "Pattern matching and substitution operator.

  When used as a pattern matching operator matches a map of which all
  then entries have keys which match `k-pattern` and all the values
  match `v-pattern`.

  When used as a pattern substitution operator constructs a map of
  by which all entries are constructed with keys with `k-pattern` and
  values with `v-pattern`."
  [k-pattern v-pattern]
  (cond
    (match-syntax? &env)
    `(with [%map# (or {~k-pattern ~v-pattern & %map#}
                     '{})]
       %map#)

    (subst-syntax? &env)
    `{& [[~k-pattern ~v-pattern] ...]}

    :else
    &form))

(defsyntax submap-of
  "Pattern matching and substitution operator.

  When used as a pattern matching operator matches a map of which some
  or none of the entries have keys that match `k-pattern` and values
  which match `v-pattern`.

  When used as a pattern substitution operator constructs a map of
  by which all entries are constructed with keys with `k-pattern` and
  values with `v-pattern`."
  ([k-pattern v-pattern]
   (cond
     (match-syntax? &env)
     `(with [%map# (or {~k-pattern ~v-pattern & %map#}
                       {_# _# & %map#}
                       '{})]
        %map#)

     (subst-syntax? &env)
     `{& [[~k-pattern ~v-pattern] ...]}

     :else
     &form)))

