(ns meander.interpreter.epsilon
  (:require
   [meander.match.syntax.epsilon :as m.match.syntax]
   [meander.pattern-factory.epsilon :as m.pf]
   [meander.syntax.epsilon :as m.syntax]))

(def expander-registry
  {`meander.interpreter.epsilon/and
   (fn [[_ & args] env]
     `(m.match.syntax/and ~@args))

   `meander.interpreter.epsilon/app
   (fn [[_ & args] env]
     (case (count args)
       (0 1)
       (throw (ex-info "app expects at least two arguments" {}))
       ;; else
       `(m.match.syntax/app ~(first args) (m.match.syntax/and ~@(rest args)))))

   `meander.interpreter.epsilon/cata
   (fn [[_ & args] env]
     `(m.match.syntax/cata ~@args))

   `meander.interpreter.epsilon/fresh
   (fn [[_ & args] env]
     `(m.syntax/fresh ~@args))

   `meander.interpreter.epsilon/guard
   (fn [[_ & args] env]
     `(m.match.syntax/guard ~@args))

   `meander.interpreter.epsilon/let
   (fn [[_ & args] env]
     (let [bindings (nth args 0 nil)]
       (if (and (vector? bindings)
                (even? (count bindings)))
         (reduce
          (fn [inner [pattern expression]]
            `(r.match.syntax/let ~pattern ~expression ~inner))
          (reverse (partition 2 bindings)))
         (throw (ex-info "The second argument to let must be a vector with an even number of elements" {}))))
     (cons `m.match.syntax/let args))

   `meander.interpreter.epsilon/not
   (fn [[_ & args] env]
     `(m.match.syntax/not ~@args))

   `meander.interpreter.epsilon/or
   (fn [[_ & args] env]
     `(m.match.syntax/or ~@args))

   `meander.interpreter.epsilon/pred
   (fn [[_ & args] env]
     (if (seq args)
       `(m.match.syntax/pred ~(first args) (m.match.syntax/and ~@(rest args)))
       `(m.match.syntax/pred)))

   `meander.interpreter.epsilon/project
   (fn [[_ & args] env]
     `(m.syntax/project ~@args))

   `meander.interpreter.epsilon/re
   (fn [[_ & args] env]
     `(m.match.syntax/re ~@args))})

(def default-parse-env
  {::m.syntax/expander-registry expander-registry})

#_
(doseq [[symbol f] expander-registry]
  (m.syntax/register-expander symbol f))

(defn -pattern-dispatch
  {:private true}
  [ast]
  (get ast :tag))

(defmulti -pattern
  {:arglists '([ast])
   :private true}
  #'-pattern-dispatch)

(defn pattern
  {:private true}
  [form]
  (let [parse-env (merge default-parse-env (meta form))]
    (-pattern (m.match.syntax/parse form parse-env))))

(defmethod -pattern :any [_]
  m.pf/anything)

(defmethod -pattern :cat
  [ast]
  (m.pf/cat-from (map -pattern (get ast :elements))))

(defmethod -pattern :ctn
  [ast]
  (let [pattern_pf (-pattern (get ast :pattern))]
    (if-some [context (get ast :context)]
      ;; {:tag :ctn, :context {:as ?context} :pattern ?pattern}
      (m.pf/contain (-pattern context) pattern_pf)
      ;; {:tag :ctn, :context nil :pattern ?pattern}
      (m.pf/contain pattern_pf))))

(defmethod -pattern :drp [_]
  m.pf/anything)

(defmethod -pattern :jsa [ast])

(defmethod -pattern :jso [ast])

(defmethod -pattern :lvr
  [ast]
  (m.pf/logic-variable (get ast :symbol)))

(defmethod -pattern :lit
  [ast]
  (m.pf/is (get ast :value)))

(defmethod -pattern :map [ast]
  (if-some [as (get ast :as)]
    (m.pf/all (-pattern as)
              (-pattern (assoc ast :as nil)))
    (let [entries-pf (map (fn [[k v]]
                            (m.pf/entry (-pattern k) (-pattern v)))
                          (get ast :map))]
      (if-some [rest-map (get ast :rest-map)]
        (m.pf/hash-map-from entries-pf (-pattern rest-map))
        (m.pf/hash-map-from entries-pf)))))

(defmethod -pattern :mut
  [ast]
  (m.pf/mutable-variable (get ast :symbol)))

(defmethod -pattern :mvr [ast]
  (m.pf/memory-variable (get ast :symbol)))

(defmethod -pattern :quo [ast]
  (m.pf/is (get ast :form)))

(defmethod -pattern :prt
  [ast]
  (m.pf/concat (-pattern (get ast :left))
               (-pattern (get ast :right))))

(defmethod -pattern :seq
  [ast]
  (m.pf/seq (-pattern (get ast :prt))))

(defmethod -pattern :ref
  [ast]
  (m.pf/reference (get ast :symbol)))

(defmethod -pattern :rp* [ast]
  (let [cat-pf (-pattern (get ast :cat))
        frugal-star-pf (m.pf/frugal-star cat-pf)
        greedy-star-pf (m.pf/greedy-star cat-pf)]
    (m.pf/factory
      (fn make-query [runtime]
        (m.pf/make-query frugal-star-pf runtime))
      (fn make-yield [runtime]
        (m.pf/make-query greedy-star-pf runtime)))))

(defmethod -pattern :rp+
  [ast]
  (let [n (get ast :n)
        cat-pf (-pattern (get ast :cat))
        frugal-plus-pf (m.pf/frugal-plus cat-pf n)
        greedy-plus-pf (m.pf/greedy-plus cat-pf n)]
    (m.pf/factory
      (fn make-query [runtime]
        (m.pf/make-query frugal-plus-pf runtime))
      (fn make-yield [runtime]
        (m.pf/make-query greedy-plus-pf runtime)))))

(defmethod -pattern :rpl [ast]
  (m.pf/all (-pattern {:tag :rp*, :cat (get ast :cat)})
            (m.pf/call count (-pattern (get ast :lvr)))))

(defmethod -pattern :rpm
  [ast]
  (m.pf/all (-pattern {:tag :rp*, :cat (get ast :cat)})
            (m.pf/call count (-pattern (get ast :mvr)))))

(defmethod -pattern :rst [ast]
  (let [id (get (get ast :mvr) :symbol)]
    (m.pf/factory
      (fn make-query [runtime]
        (let [pass (get runtime :pass)]
          (fn rest-query [target bindings]
            (pass (update bindings id (fnil into []) target)))))
      (fn make-yield [runtime]
        (let [pass (get runtime :pass)]
          (fn rest-yield [bindings]
            (pass (merge bindings {id [] :object (get bindings id)}))))))))

(defmethod -pattern :tail [ast]
  (-pattern (get ast :pattern)))

(defmethod -pattern :set
  [ast]
  (if-some [as (get ast :as)]
    (m.pf/all (-pattern as)
              (-pattern (assoc ast :as nil)))
    (let [element-pfs (map -pattern (get ast :elements))]
      (if-some [rest (get ast :rest)]
        (m.pf/into-set-of element-pfs (-pattern rest))
        (m.pf/set-from element-pfs)))))

(defmethod -pattern :unq
  [ast]
  (let [expr (get ast :expr)]
    (m.pf/factory
      (fn make-query [runtime]
        (if-some [eval (get runtime :eval)]
          (let [pass (get runtime :pass)
                fail (get runtime :fail)]
            (fn unquote-query [target bindings]
              (if (= target (eval expr))
                (pass bindings)
                fail)))
          (throw (ex-info "eval not provided" {:runtime runtime}))))
      (fn make-yield [runtime]
        (if-some [eval (get runtime :eval)]
          (let [pass (get runtime :pass)
                fail (get runtime :fail)]
            (fn unquote-yield [target bindings]
              (pass (assoc bindings :object (eval expr)))))
          (throw (ex-info "eval not provided" {:runtime runtime})))))))

(defmethod -pattern :uns
  [ast]
  (let [expr (get ast :expr)]
    (m.pf/factory
      (fn make-query [runtime]
        (if-some [eval (get runtime :eval)]
          (let [pass (get runtime :pass)
                fail (get runtime :fail)]
            (fn unquote-query [target bindings]
              (let [x (eval expr)]
                (if (and (coll? x) (= target x))
                  (pass bindings)
                  fail))))
          (throw (ex-info "eval not provided" {:runtime runtime}))))
      (fn make-yield [runtime]
        (if-some [eval (get runtime :eval)]
          (let [pass (get runtime :pass)
                fail (get runtime :fail)]
            (fn unquote-yield [target bindings]
              (let [x (eval expr)]
                (if (coll? x)
                  (pass (assoc bindings :object x))
                  fail))))
          (throw (ex-info "eval not provided" {:runtime runtime})))))))

(defmethod -pattern :vec
  [ast]
  (m.pf/vec (-pattern (get ast :prt))))

(defmethod -pattern :wth
  [ast]
  (let [bindings (get ast :bindings)
        body (get ast :body)]
    (if (some? body)
      (m.pf/with (sequence (comp (mapcat (juxt :ref :pattern))
                                 (map -pattern))
                           bindings)
        (-pattern body))
      m.pf/anything)))

;; (defmethod -pattern :meander.syntax.epsilon/fresh
;;   [ast]
;;   (reduce
;;    (fn [pattern_pf var-pf]
;;      (m.pf/make-fresh var-pf pattern_pf))
;;    (-pattern (get ast :pattern))
;;    (map -pattern (get ast :vars))))

;; (defmethod -pattern :meander.syntax.epsilon/project
;;   [ast]
;;   (m.pf/project (-pattern (get ast :yield-pattern))
;;                 (-pattern (get ast :query-pattern))
;;                 (-pattern (get ast :value-pattern))))

(defmethod -pattern :meander.match.syntax.epsilon/and
  [ast]
  (reduce m.pf/all (map -pattern (get ast :arguments))))

(defmethod -pattern :meander.match.syntax.epsilon/cata
  [ast]
  (let [argument-pf (-pattern (get ast :argument))]
    (m.pf/factory
      (fn make-query [runtime]
        (let [fmap (get runtime :fmap)
              scan (get runtime :scan)
              argument-pq (m.pf/make-query argument-pf runtime)]
          (fn cata-query [target bindings]
            (if-some [cata (get bindings ::cata)]
              (fmap (fn [x]
                      (argument-pq x bindings))
                    (cata target))
              (throw (ex-info "cata not provided" {}))))))
      (fn make-yield [runtime]
        (let [fmap (get runtime :fmap)
              scan (get runtime :scan)
              argument-py (m.pf/make-yield argument-pf runtime)]
          (fn cata-query [target bindings]
            (if-some [cata (get bindings ::cata)]
              (fmap (fn [x]
                      (argument-py x bindings))
                    (cata target))
              (throw (ex-info "cata not provided" {})))))))))

(defmethod -pattern :meander.match.syntax.epsilon/let
  [ast]
  (let [pattern_pf (-pattern (get ast :pattern))
        expression (get ast :expression)]
    (m.pf/factory
      (fn let-make-query [runtime]
        (if-some [eval (get runtime :eval)]
          (let [pattern-pq (m.pf/make-query pattern_pf runtime)]
            (fn let-query [target bindings]
              (let [x (eval expression)]
                (pattern-pq x bindings))))
          (throw (ex-info "eval not provided" {:runtime runtime}))))
      (fn let-make-yield [runtime]
        (if-some [eval (get runtime :eval)]
          (let [pattern-pq (m.pf/make-query pattern_pf runtime)]
            (fn let-yield [bindings]
              (let [x (eval expression)]
                (pattern-pq x bindings))))
          (throw (ex-info "eval not provided" {:runtime runtime})))))))

(defmethod -pattern :meander.match.syntax.epsilon/or
  [ast]
  (reduce m.pf/some (map -pattern (get ast :arguments))))

(defmethod -pattern :meander.match.syntax.epsilon/apply
  [ast]
  (let [function (get ast :function)
        argument-pf (-pattern (get ast :argument))]
    (m.pf/factory
      (fn apply-make-query [runtime]
        (if-some [eval (get runtime :eval)]
          (let [argument-pq (m.pf/make-query argument-pf runtime)]
            (fn pattern-apply-query [target bindings]
              (let [f (eval function)]
                (argument-pq (f target) bindings))))
          (throw (ex-info "eval not provided" {:runtime runtime}))))
      (fn apply-make-yield [runtime]
        (if-some [eval (get runtime :eval)]
          (let [argument-py (m.pf/make-yield argument-pf runtime)
                fmap (get runtime :fmap)]
            (fn pattern-apply-yield [bindings]
              (let [f (eval function)]
                (fmap (fn [bindings]
                        (update bindings :object f))
                      (argument-py bindings)))))
          (throw (ex-info "eval not provided" {:runtime runtime})))))))

(defmethod -pattern :meander.match.syntax.epsilon/guard
  [ast]
  (let [expr (get ast :expr)]
    (m.pf/factory
      (fn guard-make-query [runtime]
        (if-some [eval (get runtime :eval)]
          (let [fail (get runtime :fail)
                pass (get runtime :pass)]
            (fn pattern-guard-query [target bindings]
              (if (eval expr)
                (pass bindings)
                fail)))
          (throw (ex-info "eval not provided" {:runtime runtime}))))
      (fn guard-make-yield [runtime]
        (if-some [eval (get runtime :eval)]
          (let [fail (get runtime :fail)
                pass (get runtime :pass)]
            (fn pattern-guard-yield [bindings]
              (if (eval expr)
                (pass bindings)
                fail)))
          (throw (ex-info "eval not provided" {:runtime runtime})))))))

(defmethod -pattern :meander.match.syntax.epsilon/not
  [ast]
  (m.pf/not (-pattern (get ast :argument))))

(defmethod -pattern :meander.match.syntax.epsilon/pred
  [ast]
  (let [form (get ast :form)
        arguments (get ast :arguments)
        and-pf (-pattern {:tag :meander.match.syntax.epsilon/and
                          :arguments arguments})]
    (m.pf/factory
      (fn pred-make-query [runtime]
        (if-some [eval (get runtime :eval)]
          (let [and-pq (m.pf/make-query and-pf runtime)
                fail (get runtime :fail)
                pass (get runtime :pass)]
            (fn pred-query [target bindings]
              (let [predicate (eval form)]
                (if (predicate target)
                  (pass bindings)
                  fail))))
          (throw (ex-info "eval not provided" {:runtime runtime}))))
      (fn pred-make-yield [runtime]
        (if-some [eval (get runtime :eval)]
          (let [and-py (m.pf/make-yield and-pf runtime)
                fail (get runtime :fail)
                fmap (get runtime :fmap)
                pass (get runtime :pass)]
            (fn pred-yield [bindings]
              (let [predicate (eval form)]
                (fmap (fn [bindings]
                        (let [object (get bindings :object)]
                          (if (predicate object)
                            (pass bindings)
                            fail)))
                      (and-py bindings)))))
          (throw (ex-info "eval not provided" {:runtime runtime})))))))

(defmethod -pattern :meander.match.syntax.epsilon/rxc
  [ast]
  (let [regex (get ast :regex)
        capture-pf (-pattern (get ast :capture))]
    (m.pf/factory
      (fn make-query [runtime]
        (let [capture-pq (m.pf/make-query capture-pf runtime)
              fail (get runtime :fail)
              pass (get runtime :pass)]
          (fn re-a2-query [target bindings]
            (if (string? target)
              (if-some [matches (re-matches regex target)]
                (capture-pq matches bindings)
                fail)
              fail))))
      (fn make-yield [runtime]
        (throw (ex-info "re pattern does not support yield" {}))))))

(defmethod -pattern :meander.match.syntax.epsilon/rxt
  [ast]
  (let [regex (get ast :regex)]
    (m.pf/factory
      (fn make-query [runtime]
        (let [fail (get runtime :fail)
              pass (get runtime :pass)]
          (fn re-a1-query [target bindings]
            (if (string? target)
              (pass bindings)
              fail))))
      (fn make-yield [runtime]
        (throw (ex-info "re pattern does not support yield" {}))))))

(defmethod -pattern :meander.match.syntax.epsilon/subsequence
  [ast]
  (let [cat (get ast :cat)
        n (count cat)
        cat-pf (-pattern cat)]
    (m.pf/factory
      (fn make-query [runtime]
        (let [cat-pq (m.pf/make-query cat-pf runtime)
              scan (get runtime :scan)]
          (fn [target bindings]
            (scan (fn [partition]
                    (cat-pf partition bindings))
                  (partition n 1 target)))))
      (fn make-yield [runtime]
        (m.pf/make-yield cat-pf runtime)))))

(defn match-clause-factory
  {:private true}
  [query-pf f]
  (fn [runtime]
    (let [query-pq (m.pf/make-query query-pf runtime)
          fmap (get runtime :fmap)
          pass (get runtime :pass)]
      (fn [x bindings]
        (fmap (fn [bindings]
                (pass (f bindings)))
              (query-pq x bindings))))))

(defn match-system-factory
  {:private true}
  [clauses]
  (let [cfs (map (fn [[query yield]]
                   (match-clause-factory
                    (if (satisfies? m.pf/IMakeQuery query)
                      query
                      (pattern query))
                    yield))
                 (partition 2 clauses))]
    (fn [runtime]
      (let [scan (get runtime :scan)
            rules (map (fn [cf] (cf runtime)) cfs)]
        (fn f [x]
          (scan (fn [rule] (rule x {::cata f})) rules))))))

(defn finder-from
  "Takes a sequence of [query f] pairs and returns a function which
  behaves like `meander.epsilon/find`. query is a quoted pattern
  and f is a unary function which takes a map of bindings and returns
  any value."
  {:arglists '([[query f] & more-clauses])}
  [clauses]
  ((match-system-factory clauses) m.pf/find-runtime))

(defn finder
  "Takes an even number of arguments
    query_1 f_1
    ...
    query_n f_n

  and returns a function which behaves like
  `meander.epsilon/find`. query is a quoted pattern and f is a unary
  function which takes a map of bindings and returns any value."
  ([pattern f]
   (finder-from [pattern f]))
  ([pattern f & more-clauses]
   (assert (even? (count more-clauses)) "finder expects an even number of arguments")
   (finder-from (cons [pattern f] (partition 2 more-clauses)))))

(defn searcher-from
  "Takes a sequence of [query f] pairs and returns a function which
  behaves like `meander.epsilon/search`. query is a quoted pattern
  and f is a unary function which takes a map of bindings and returns
  any value."
  {:arglists '([[pattern f] & more-clauses])}
  [clauses]
  ((match-system-factory clauses) m.pf/depth-first-search-runtime))

(defn searcher
  "Takes an even number of arguments
    query_1 f_1
    ...
    query_n f_n

  and returns a function which behaves like
  `meander.epsilon/search`. query is a quoted pattern and f is a unary
  function which takes a map of bindings and returns any value."
  ([pattern f]
   (searcher-from [pattern f]))
  ([pattern f & more-clauses]
   (assert (even? (count more-clauses)) "searcher expects an even number of arguments")
   (searcher-from (cons [pattern f] (partition 2 more-clauses)))))

(defn rewrite-rule-factory
  {:private true}
  [query-pf yield-pf]
  (fn [runtime]
    (let [query-pq (m.pf/make-query query-pf runtime)
          yield-py (m.pf/make-yield yield-pf runtime)
          fmap (get runtime :fmap)
          pass (get runtime :pass)]
      (fn [x bindings]
        (fmap (fn [bindings]
                (fmap (fn [bindings]
                        (pass (get bindings :object)))
                      (yield-py bindings)))
              (query-pq x bindings))))))

(defn rewrite-system-factory
  {:arglists '([[x_lhs x_rhs & more-clauses]])
   :private true}
  [clauses]
  (let [rfs (map (fn [[query yield]]
                   (rewrite-rule-factory
                    (if (satisfies? m.pf/IMakeQuery query)
                      query
                      (pattern query))
                    (if (satisfies? m.pf/IMakeYield yield)
                      yield
                      (pattern yield))))
                 (partition 2 clauses))]
    (fn [runtime]
      (let [scan (get runtime :scan)
            rules (map (fn [rf] (rf runtime)) rfs)]
        (fn f [x]
          (scan (fn [rule] (rule x {::cata f})) rules))))))

(defn rewriter
  {:arglists '([x_lhs x_rhs & more-clauses])}
  [& clauses]
  ((rewrite-system-factory clauses) m.pf/find-runtime))

(defn rewriter*
  {:arglists '([x_lhs x_rhs & more-clauses])}
  [& clauses]
  ((rewrite-system-factory clauses) m.pf/depth-first-search-runtime))
