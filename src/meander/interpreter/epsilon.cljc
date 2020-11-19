(ns meander.interpreter.epsilon
  (:require [clojure.zip :as zip]
            [meander.environment.epsilon :as m.environment]
            [meander.util.epsilon :as m.util]
            [meander.match.syntax.epsilon :as m.match.syntax]
            [meander.syntax.epsilon :as m.syntax]))


;; Private API
;; -----------

(declare search-fn)

;; Tree patterns

(defn search-fn-and
  {:private true}
  ([search-fn-a]
   search-fn-a)
  ([search-fn-a search-fn-b]
   (fn [x bindings]
     (mapcat
      (fn [bindings]
        (search-fn-b x bindings))
      (search-fn-a x bindings)))))

(defn search-fn-or
  {:private true}
  ([]
   (fn [x bindings] ()))
  ([search-fn-a]
   search-fn-a)
  ([search-fn-a search-fn-b]
   (fn [x bindings]
     (m.util/mix (search-fn-a x bindings)
                 (search-fn-b x bindings)))))

;; Leaf patterns

(defn search-fn-literal
  {:private true}
  [x return reject]
  (fn [target bindings]
    (if (= target x)
      (return bindings)
      (reject bindings))))

(defn search-fn-logic-variable
  {:private true}
  [v return reject]
  (fn [x bindings]
    (if-some [[_ y] (find bindings v)]
      (if (= y x)
        (return bindings)
        (reject bindings))
      (return (assoc bindings v x)))))

(defn search-fn-memory-variable
  {:private true}
  [v return]
  (fn [x bindings]
    (let [xs (if-some [e (find bindings v)]
               (val e)
               [])
          xs (conj xs x)]
      (return (assoc bindings v xs)))))

(defn search-fn-mutable-variable
  {:private true}
  [v return]
  (fn [x bindings]
    (return (assoc bindings v x))))

(defn search-fn-pred
  {:style/indent 1
   :private true}
  [p search-fn reject]
  (fn [x bindings]
    (if (p x)
      (search-fn x bindings)
      (reject bindings))))

(defn search-fn-partition
  {:private true}
  [search-fn-left search-fn-right]
  (fn [target bindings]
    (mapcat
     (fn [partition]
       (let [a (nth partition 0)
             b (nth partition 1)]
         (mapcat
          (fn [bindings]
            (search-fn-right b bindings))
          (search-fn-left a bindings))))
     (m.util/partitions 2 target))))

(defn run-cat
  {:arglists '([search-fns xs bindings return reject])
   :private true}
  [fs xs bindings return reject]
  (if-some [[f & rest-fs] (seq fs)]
    (let [[x & rest-xs] xs]
      (mapcat
       (fn [bindings]
         (run-cat rest-fs rest-xs bindings return reject))
       (f x bindings)))
    (if (seq xs)
      (reject bindings)
      (return bindings))))

(defn search-fn-cat
  {:private true}
  [length search-fns return reject]
  (let [length+1 (inc length)]
    (fn [target bindings]
      (if (= (bounded-count length+1 target) length)
        (run-cat search-fns target bindings return reject)
        (reject bindings)))))

(defn entry-search-fns
  {:private true}
  [entry-asts env return reject]
  (map (fn [e]
         (let [k-ast (key e)
               v-ast (val e)
               k-search-fn (search-fn k-ast env return reject)
               v-search-fn (search-fn v-ast env return reject)]
           (fn [target bindings]
             (let [k (key target)
                   v (val target)]
               (mapcat (fn [bindings]
                         (v-search-fn v bindings))
                       (k-search-fn k bindings))))))
       entry-asts))

(defn search-fn
  {:private true}
  [ast env return reject]
  (case (get ast :tag)
    ;; {:tag :any}
    :any
    (fn [target bindings]
      (return bindings))

    ;; {:tag :cat, :elements ?elements}
    :cat
    (let [?elements (get ast :elements)]
      (search-fn-cat (count ?elements)
                     (map (fn [element]
                            (search-fn element env return reject))
                          ?elements)
                     return
                     reject))

    :ctn
    (let [?context (get ast :context)
          ?pattern (get ast :pattern)
          pattern-search-fn (search-fn ?pattern env reject)]
      (if (some? ?context)
        ;; {:tag :ctn, :context {:as ?contenxt} :pattern ?pattern}
        (let [context-search-fn (search-fn ?context env reject)]
          (fn [target bindings]
            (mapcat
             (fn [loc]
               (let [node (zip/node loc)
                     edit (fn [x]
                            (zip/root (zip/replace loc x)))]
                 (mapcat
                  (fn [bindings]
                    (context-search-fn edit bindings))
                  (pattern-search-fn node bindings))))
             (m.util/zip-next-seq (m.util/coll-zip target)))))
        ;; {:tag :ctn, :context nil :pattern ?pattern}
        (let [pattern-search-fn (search-fn ?pattern env reject)]
          (fn [target bindings]
            (mapcat
             (fn [x]
               (pattern-search-fn x bindings))
             (m.util/coll-seq target))))))

    ;; {:tag :drp}
    :drp
    (fn [target bindings]
      (return bindings))

    ;; {:tag :lit, :value ?value}
    :lit
    (let [?value (get ast :?value)]
      (search-fn-literal ?value return reject))

    ;; {:tag :lvr, :symbol ?symbol}
    :lvr
    (let [?symbol (get ast :symbol)]
      (search-fn-logic-variable ?symbol return reject))

    :map
    (let [?as (get ast :as)
          ?rest-map (get ast :rest-map)
          ?map (get ast :map)]
      (case [(nil? ?as) (nil? ?rest-map)]
        ;; {:tag :map ':as nil :rest-map nil :map {:as ?map}}
        [true true]
        (let [k (count ?map)
              entry-search-fns (entry-search-fns ?map env reject)]
          (search-fn-pred map?
                          (fn [target bindings]
                            (if (<= k (count target))
                              (mapcat
                               (fn [selection]
                                 (run-cat entry-search-fns selection bindings reject))
                               (m.util/k-permutations target k))
                              (reject bindings)))
                          reject))

        ;; {:tag :map ':as nil :rest-map {:as ?rest-map} :map {:as ?map}}
        [true false]
        (let [k (count ?map)
              entry-search-fns (entry-search-fns ?map env reject)
              rest-search-fn (search-fn ?rest-map env reject)]
          (search-fn-pred map?
                          (fn [target bindings]
                            (if (<= k (count target))
                              (mapcat
                               (fn [pair]
                                 (let [selection (nth pair 0)
                                       rest (nth pair 1)]
                                   (mapcat
                                    (fn [bindings]
                                      (rest-search-fn rest bindings))
                                    (run-cat entry-search-fns selection bindings))))
                               (m.util/map-k-permutations-with-unselected target k))
                              (reject bindings)))
                          reject))

        ;; {:tag :map ':as ?as :rest-map ?rest-map}
        ;; else
        (search-fn-and (search-fn ?as env return reject)
                       (search-fn (assoc ast :as nil) env return reject))))

    ;; {:tag :mut :symbol ?symbol}
    :mut
    (let [?symbol (get ast :symbol)]
      (search-fn-mutable-variable ?symbol))

    ;; {:tag :mvr :symbol ?symbol}
    :mvr
    (let [?symbol (get ast :symbol)]
      (search-fn-memory-variable ?symbol return))

    ;; {:tag :prt, :left ?left, :right ?right}
    :prt
    (let [?left (get ast :left)
          ?right (get ast :right)]
      (search-fn-partition (search-fn ?left env return reject)
                           (search-fn ?right env return reject)))

    ;; {:tag :ref, :symbol ?symbol}
    :ref
    (let [?symbol (get ast :symbol)]
      (if-some [e (find env ?symbol)]
        (val e)
        (throw (ex-info (str "Unbound reference: " ?symbol) {:meta (meta ?symbol)}))))

    :seq
    (let [?as (get ast :as)
          ?prt (get ast :prt)]
      ;; {:tag :seq, :prt ?prt, ':as ?as}
      (if (some? ?as)
        (search-fn-and (search-fn ?as env return reject)
                       (search-fn-pred seq? (search-fn ?prt env return reject) reject))
        ;; {:tag :seq, :prt ?prt, ':as nil}
        (search-fn-pred seq? (search-fn ?prt env return reject) reject)))

    ;; {:tag :rp* :cat {:elements ?elements :as ?cat}}
    :rp*
    (let [?cat (get ast :cat)
          ?elements (get ast :elements)
          k (count ?elements)
          search-fn-cat (search-fn ?cat env return reject)
          ]
      (fn search-fn-star [target bindings]
        (if (seq target)
          (let [front (take k target)]
            (if (= k (count front))
              (let [back (drop k target)]
                (mapcat
                 (fn [bindings]
                   (search-fn-star back bindings))
                 (search-fn-cat front bindings)))
              (reject bindings)))
          (return bindings))))

    ;; {:tag :rp+, :n ?n, :cat {:elements ?elements :as ?cat}}
    :rp+
    (let [?n (get ast :n)
          ?cat (get ast :cat)
          ?elements (get ast :elements)]
      (search-fn {:tag :prt
                  :left {:tag :cat
                         :elements (reduce concat () (repeat ?n ?elements))}
                  :right {:tag :rp*, :cat ?cat}}))

    ;; {:tag :rpl, :lvr {:symbol ?symbol :as ?lvr}, :cat {:elements ?elements :as ?cat}}
    :rpl
    (let [?lvr (get ast :lvr)
          ?symbol (get ?lvr :symbol)
          ?cat (get ast :cat)
          ?elements (get ast :elements)
          search-fn-star (search-fn {:tag :rp* :cat ?cat} env return reject)]
      (fn [target bindings]
        (let [m (count target)]
          (if-some [e (find bindings ?symbol)]
            (let [n (val e)]
              (if (= n m)
                (search-fn-star target bindings)
                (reject bindings)))
            (search-fn-star target (assoc bindings ?symbol m))))))

    ;; {:tag :rpm, :mvr {:symbol ?symbol :as ?mvr}, :cat {:elements ?elements :as ?cat}}
    :rpm
    (let [?mvr (get ast :mvr)
          ?symbol (get ?mvr :symbol)
          ?cat (get ast :cat)
          ?elements (get ?cat :elements)
          search-fn-star (search-fn {:tag :rp* :cat ?cat} env return reject)]
      (fn [target bindings]
        (let [m (count target)
              xs (if-some [e (find bindings ?symbol)]
                   (val e)
                   [])
              xs (conj xs m)]
          (search-fn-star target (assoc bindings ?symbol xs)))))

    ;; {:tag :rst, :mvr {:symbol ?symbol}}
    :rst
    (let [?mvr (get ast :mvr)
          ?symbol (get ?mvr :symbol)]
      (fn [target bindings]
        (let [xs (if-some [e (find bindings ?symbol)]
                   (val e)
                   [])
              xs (into xs target)]
          (return (assoc bindings ?symbol xs)))))
    :set
    (let [?as (get ast :ast)
          ?rest (get ast :rest)
          ?elements (get ast :elements)
          ?set ast]
      (case [(nil? ?as) (nil? ?rest)]
        ;; {:tag :set ':as nil :rest nil :elements ?elements :as ?set}
        [true true]
        (let [k (count ?elements)
              element-search-fns (map (fn [element]
                                        (search-fn element env return reject))
                                      ?elements)
              search-fn-set (fn [target bindings]
                              (if (<= k (count target))
                                (mapcat
                                 (fn [selection]
                                   (run-cat element-search-fns selection bindings return reject))
                                 (m.util/k-permutations target k))
                                (reject bindings)))]
          (if (= set? (get env ?set))
            search-fn-set
            (search-fn-pred set? search-fn-set reject)))

        ;; {:tag :set ':as nil :rest ?rest :elements ?elements :as ?set}
        [true false]
        (let [?set ast
              k (count ?elements)
              element-search-fns (map (fn [element]
                                        (search-fn element env return reject))
                                      ?elements)
              rest-search-fn (search-fn ?rest (assoc env ?rest set?) return reject)
              search-fn-set (fn [target bindings]
                              (if (<= k (count target))
                                (mapcat
                                 (fn [pair]
                                   (let [selection (nth pair 0)
                                         rest (nth pair 1)]
                                     (mapcat
                                      (fn [bindings]
                                        (rest-search-fn rest bindings))
                                      (run-cat element-search-fns selection bindings return reject))))
                                 (m.util/set-k-permutations-with-unselected target k))
                                (reject bindings)))]
          (if (= set? (get env ?set))
            search-fn-set
            (search-fn-pred set? search-fn-set reject)))
        ;; {:tag :set ':as ?as :rest ?rest}
        ;; else
        (search-fn-and (search-fn ?as env return reject)
                       (search-fn (assoc ast :as nil) env return reject))))

    ;; {:tag :tail, :pattern ?pattern}
    :tail
    (let [?pattern (get ast :pattern)]
      (search-fn ?pattern env return reject))

    ;; {:tag :unq, :form ?form}
    :unq
    (let [?form (get ast :form)]
      (if-some [e (find env ?form)]
        (let [value (val e)]
          (fn [target bindings]
            (if (= target value)
              (return bindings)
              (reject bindings))))
        (let [ast-meta (meta ast)]
          (fn [target bindings]
            (if-some [e (find bindings ::eval)]
              (let [eval (val e)]
                (fn [target bindings]
                  (if (= target (eval ?form))
                    (return bindings)
                    (reject bindings))))
              (throw (ex-info "Unable to resolve eval function for unquote pattern" {:pattern-meta ast-meta})))))))

    :vec
    (let [?as (get ast :as)
          ?prt (get ast :prt)]
      (if (some? ?as)
        ;; {:tag :vec, :prt ?prt, ':as ?as}
        (search-fn-and (search-fn ?as env return reject)
                       (search-fn-pred vector? (search-fn ?prt env return reject) reject))

        ;; {:tag :vec, :prt ?prt, ':as nil}
        (search-fn-pred vector? (search-fn ?prt env return reject) reject)))

    :wth
    (let [?bindings (get ast :bindigns)
          ?body (get ast :body)]
      ;; {:tag :wth, :bindings ?bindings, :body {:as ?body}}
      (if (some? ?body)
        (let [ref-bindings (reduce
                            (fn [ref-bindings binding]
                              (let [?ref (get binding :ref)
                                    ?symbol (get ?ref :symbol)
                                    ?pattern (get binding :pattern)]
                                (assoc ref-bindings ?symbol (search-fn ?pattern env return reject))))
                            {}
                            ?bindings)
              search-fn-body (search-fn ?body (merge env ref-bindings) return reject)]
          (fn [target bindings]
            (search-fn-body target bindings)))
        ;; {:tag :wth, :bindings _, :body _}
        (fn [target bindings]
          (return bindings))))

    ;; {:tag ::m.match.syntax/and, :arguments ?arguments}
    ::m.match.syntax/and
    (let [?arguments (get ast :arguments)]
      (if (seq ?arguments)
        (reduce search-fn-and
                (map (fn [argument]
                       (search-fn argument env return reject))
                     ?arguments))
        (fn [target bindings]
          (return bindings))))

    ;; {:tag ::m.match.syntax/apply, :function ?function, :argument ?argument}
    ::m.match.syntax/apply
    (let [?function (get ast :function)
          ?argument (get ast :argument)]
      (let [eval (get env ::eval)
            search-fn-argument (search-fn ?argument env return reject)]
        (fn [target bindings]
          (let [f (eval ?function)]
            (search-fn-argument (f target) bindings)))))

    ;; {:tag ::m.match.syntax/cata, :argument ?pattern}
    ::m.match.syntax/cata
    (let [?pattern (get ast :argument)
          search-fn-pattern (search-fn ?pattern env return reject)]
      (if-some [e (find env ::cata)]
        (let [search-fn-cata (val e)]
          (fn [target bindings]
            (let [search-fn-cata (val e)]
              (mapcat
               (fn [target]
                 (search-fn-pattern target bindings))
               (search-fn-cata target)))))
        (let [ast-meta (meta ast)]
          (fn [target bindings]
            (if-some [e (find bindings ::cata)]
              (let [search-fn-cata (val e)]
                (mapcat
                 (fn [target]
                   (search-fn-pattern target bindings))
                 (search-fn-cata target)))
              (throw (ex-info "Unable to resolve cata function for cata pattern" {:pattern-meta ast-meta})))))))

    ;; {:tag ::m.match.syntax/guard, :expr ?expr}
    ::m.match.syntax/guard
    (let [?expr (get ast :expr)]
      (if-some [e (find env ?expr)]
        (let [p (val e)]
          (fn [target bindings]
            (if (p target)
              (return bindings)
              (reject bindings))))
        (let [ast-meta (meta ast)]
          (fn [target bindings]
            (if-some [e (find bindings ::eval)]
              (let [eval (val e)]
                (if (eval target)
                  (return bindings)
                  (reject bindings)))
              (throw (ex-info "Unable to resolve eval function for guard pattern" {:meta ast-meta})))))))

    ;; {:tag ::m.match.syntax/let, :pattern ?pattern, :expression ?expression}
    ::m.match.syntax/let
    (let [?pattern (get ast :pattern)
          ?expression (get ast :expression)
          eval (get env ::eval)
          search-fn-pattern (search-fn ?pattern env reject)]
      (fn [target bindings]
        (search-fn-pattern (eval ?expression) bindings)))

    ;; {:tag :meander.match.syntax.epsilon/not :argument ?argument}
    :meander.match.syntax.epsilon/not
    (let [?argument (get ast :argument)
          search-fn-argument (search-fn ?argument env return reject)]
      (fn [target bindings]
        (if (seq (search-fn-argument target bindings))
          (reject bindings)
          (return bindings))))

    ;; {:tag :meander.match.syntax.epsilon/or :arguments ?arguments}
    :meander.match.syntax.epsilon/or
    (let [?arguments (get ast :arguments)]
      (if (seq ?arguments)
        (reduce search-fn-or
                (map (fn [argument]
                       (search-fn argument env return reject))
                     ?arguments))
        reject))

    ;; {:tag :meander.match.syntax.epsilon/pred, :form ?form, :arguments ?arguments}
    :meander.match.syntax.epsilon/pred
    (let [?form (get ast :form)
          ?arguments (get ast :arguments)
          synthetic-and {:tag :meander.match.syntax.epsilon/and, :arguments ?arguments}
          search-fn-arguments (search-fn synthetic-and env return reject)]
      (fn pred-search-fn [target bindings]
        (let [eval (get bindings ::eval)
              predicate (eval ?form)]
          (if (predicate target)
            (search-fn-arguments target bindings)
            (reject bindings)))))

    ;; {:tag :meander.match.syntax.epsilon/rxc, :regex ?regex, :capture ?capture}
    :meander.match.syntax.epsilon/rxc
    (let [?regex (get ast :regex)
          ?capture (get ast :capture)
          search-fn-capture (search-fn ?capture env return reject)]
      (search-fn-pred string?
                      (fn [target bindings]
                        (if-some [matches (re-matches ?regex target)]
                          (let [matches (if (coll? matches)
                                          (vec matches)
                                          [matches])]
                            (search-fn-capture matches bindings))
                          (reject bindings)))
                      reject))

    ;; {:tag :meander.match.syntax.epsilon/rxt, :regex ?regex}
    :meander.match.syntax.epsilon/rxt
    (let [?regex (get ast :regex)]
      (search-fn-pred string?
                      (fn [target bindings]
                        (if (re-matches ?regex target)
                          (return bindings)
                          (reject bindings)))
                      reject))

    ;; else
    (throw (ex-info "" {:ast ast}))))

;; Public API
;; ----------

(def default-bindings
  {::eval #?(:clj eval
             :cljs (fn no-eval [_]
                     (throw (ex-info "eval not defined" {}))))
   ::cata (fn no-cata [bindings]
            (throw (ex-info "Unable to resolve cata" {})))})

(def
  ^{:arglists '([bindings])
    :private true}
  default-return list)

(defn default-reject
  {:private true}
  [bindings] ())

(def default-parse-env
  {::m.syntax/expander-registry
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

    `meander.interpreter.epsilon/re
    (fn [[_ & args] env]
      `(m.match.syntax/re ~@args))}})

(defn searcher
  ([pattern-form callback]
   (let [ast (m.match.syntax/parse pattern-form default-parse-env)
         env (merge {} (meta pattern-form))
         pattern-fn (search-fn ast env default-return default-reject)]
     (fn f
       ([target]
        (f target default-bindings))
       ([target bindings]
        (map callback (pattern-fn target (assoc bindings ::cata pattern-fn)))))))
  ([pattern-form callback & more-clauses]
   (let [search-fn
         (reduce search-fn-or
                 (map (fn [[pattern-form callback]]
                        (let [ast (m.match.syntax/parse pattern-form default-parse-env)
                              env (merge {} (meta pattern-form))
                              pattern-fn (search-fn ast env default-return default-reject)]
                          (fn [x bindings]
                            (map callback (pattern-fn x bindings)))))
                      (cons [pattern-form callback] (partition 2 more-clauses))))]
     (fn f
       ([target]
        (search-fn target default-bindings))
       ([target bindings]
        (search-fn target (assoc bindings ::cata search-fn)))))))
