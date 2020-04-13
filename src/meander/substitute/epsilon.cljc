(ns ^:no-doc meander.substitute.epsilon
  (:refer-clojure :exclude [compile])
  #?(:cljs (:require-macros [meander.substitute.epsilon]))
  (:require [clojure.set :as set]
            [clojure.spec.alpha :as s]
            [clojure.walk :as walk]
            [meander.match.epsilon :as r.match]
            [meander.match.runtime.epsilon :as r.match.runtime]
            [meander.match.syntax.epsilon :as r.match.syntax]
            [meander.syntax.epsilon :as r.syntax]
            [meander.substitute.runtime.epsilon :as r.subst.runtime]
            [meander.substitute.syntax.epsilon :as r.subst.syntax :include-macros true]
            [meander.util.epsilon :as r.util]))

;; ---------------------------------------------------------------------
;; Environment utilities

(defn stateful-memory-variables
  {:private true}
  [node]
  (distinct
   (r.match/search node
     (meander.syntax.epsilon/$
      {:tag ::r.subst.syntax/cata
       :argument (meander.syntax.epsilon/$ {:tag :mvr :as ?mvr-node})})
     ?mvr-node

     (meander.syntax.epsilon/$
      {:tag (r.match.syntax/or :rp* :rp+ :rpl :rpm)
       :cat (meander.syntax.epsilon/$ {:tag :mvr :as ?mvr-node})})
     ?mvr-node

     (meander.syntax.epsilon/$
      {:tag :rpm
       :mvr {:tag :mvr :as ?mvr-node}})
     ?mvr-node

     ;; TODO: This definition is weak.
     (meander.syntax.epsilon/$
      {:tag :wth
       :bindings [_ ...
                  {:ref {:symbol ?symbol}
                   :pattern (meander.syntax.epsilon/$ {:tag :mvr :as ?mvr-node})} .
                  _ ...]})
     ?mvr-node)))

(defn memory-variable-data
  {:private true}
  [node]
  (r.match/search (stateful-memory-variables node)
    (_ ... {:symbol ?symbol} . _ ...)
    {:memory-variable/symbol ?symbol
     :memory-variable/state :iterating
     :iterator/symbol (with-meta (symbol (str (name ?symbol) "__counter"))
                        {:tag 'java.util.Iterator})}))

(defn make-env
  [node]
  {:wth-refs {}
   :data (into #{} (memory-variable-data node))})

(defn get-wth-refs
  {:private true}
  [env]
  (get env :wth-refs))

(defn get-wth-ref-pattern
  {:private true}
  [env ref-node]
  (get-in env [:wth-refs ref-node]))

(defn add-wth-refs
  {:private true}
  [env ref-map]
  (update env :wth-refs merge ref-map))

;; ---------------------------------------------------------------------
;; Compilation

(defn compile-ground
  "This function is used to compile the `:value` of `:lit` nodes."
  {:private true}
  [x]
  (cond
    (symbol? x)
    `(quote ~x)

    (seq? x)
    (if (= (first x) 'quote)
      x
      (if (= (first x) `list)
        (cons (first x) (map compile-ground (rest x)))
        (if (seq x)
          (cons `list (map compile-ground x))
          ())))

    (map? x)
    (into {}
          (map
           (fn [[k v]]
             [(compile-ground k) (compile-ground v)]))
          x)

    (coll? x)
    (into (empty x) (map compile-ground) x)

    :else
    x))

(defn iterator-has-next-form
  {:private true}
  [iterator-form]
  `(.hasNext ~iterator-form))

(defn iterator-next-form
  {:private true}
  [iterator-form]
  `(if (.hasNext ~iterator-form)
     (.next ~iterator-form)))

(defn iterator-rest-form
  {:private true}
  [iterator-form]
  `(vec (r.subst.runtime/iterator-seq ~iterator-form)))

(defmulti compile*
  ""
  {:arglists '([node env])}
  (fn [node _] (:tag node)))

(defn compile-all*
  [nodes env]
  (reduce
   (fn [[forms env] node]
     (let [[form env*] (compile* node env)]
       [(conj forms form) env*]))
   [[] env]
   nodes))

(defmethod compile* ::r.subst.syntax/apply
  [node env]
  (r.match/match node
    {:function ?function
     :argument ?argument}
    (let [[form env] (compile* ?argument env)]
      [`(~?function ~form) env])))

(defmethod compile* ::r.subst.syntax/cata
  [node env]
  (r.match/match node
    {:argument ?argument :as ?node}
    (if-some [cata-symbol (get env :cata-symbol)]
      (let [[argument env] (compile* ?argument env)
            form `(let [CATA_RESULT# (~cata-symbol ~argument)]
                    (if (r.match.runtime/fail? CATA_RESULT#)
                      (throw r.subst.runtime/FAIL)
                      (nth CATA_RESULT# 0)))]
        [form env])
      (let [env (update env :data conj {:error :cata-not-bound})]
        [`(throw (ex-info "cata not bound" {})) env]))))

(defmethod compile* :ctn
  [node env]
  (let [pattern (:pattern node)]
    (if-some [context (:context node)]
      (let [[pattern-form env] (compile* pattern env)
            [context-form env] (compile* context env)]
        [`(~context-form ~pattern-form) env])
      (compile* (:pattern node) env))))

(defmethod compile* :cat
  [node env]
  (r.match/match node
    ;; Base case 1.
    {:elements []}
    [() env]

    ;; Base case 2.
    {:elements ()}
    [() env]

    ;; Normalize elements to vector and recur.
    {:elements (& _ :as ?elements)}
    (compile* {:tag :cat :elements (vec ?elements)} env)

    ;; Process each element in the sequence from left to right.

    ;; Handle unquote splicing.
    {:elements [{:tag :uns, :expr ?expr} & ?tail]}
    (r.match/match (compile* ?expr env)
      [?expr-form ?expr-env]
      (r.match/match (compile* {:tag :cat
                                :elements ?tail}
                               ?expr-env)
        [?tail-form ?tail-env]
        [`(concat ~?expr-form ~?tail-form) ?tail-env]))

    ;; Handle anything else.
    {:elements [?head & ?tail]}
    (r.match/match (compile* ?head env)
      [?head-form ?head-env]
      (r.match/match (compile* {:tag :cat
                                :elements ?tail}
                               ?head-env)
        [?tail-form ?tail-env]
        [`(cons ~?head-form ~?tail-form) ?tail-env]))))

(defmethod compile* :drp
  [_ env]
  [() env])

(defmethod compile* :lit
  [node env]
  [(compile-ground (:value node)) env])

(defmethod compile* :lvr
  [node env]
  [(:symbol node) env])

(defmethod compile* :map
  [node env]
  (let [[form env] (if-some [as-node (:as node)]
                     (let [[form env] (compile* as-node env)]
                       [`(into {} ~form) env])
                     [{} env])
        [forms env] (compile-all* (into [] cat (:map node)) env)
        form `(merge ~form ~(into {} (map vec (partition 2 forms))))
        [form env] (if-some [rest-node (:rest-map node)]
                     (let [[rest-form env] (compile* rest-node env)]
                       [`(let [form# ~form]
                           (merge (into {} ~rest-form) form#)) env])
                     [form env])
        ;; Search for keys containing memory variables that have
        ;; associated iterator symbols in the environment.
        iterator-symbols (r.match/search [node env]
                           [{:map {(r.match.syntax/apply r.syntax/variables #{{:tag :mvr :symbol ?symbol}}) _}}
                            {:data #{{:memory-variable/symbol ?symbol
                                      :iterator/symbol (r.match.syntax/pred symbol? ?iterator-symbol)}}}]
                           ?iterator-symbol)
        ;; If there are any iterator symbols we need to check to see
        ;; if all of them have a value available at runtime.
        form (if (seq iterator-symbols)
               `(if (and ~@(map iterator-has-next-form iterator-symbols))
                  ~form
                  {})
               form)]
    [form env]))

(defmethod compile* :mvr
  [node env]
  (r.match/find [node env]
    [{:symbol ?symbol}
     {:data #{{:memory-variable/symbol ?symbol
               :memory-variable/state :finished}}}]
    [nil env]

    ;; Check for an associated iterator.
    [{:symbol ?symbol}
     {:data #{{:memory-variable/symbol ?symbol
               :iterator/symbol (r.match.syntax/pred symbol? ?iterator-symbol)}}}]
    [(iterator-next-form ?iterator-symbol) env]

    ;; Check for an associated counter.
    [{:symbol ?symbol}
     {:data #{{:memory-variable/symbol ?symbol
               :counter/memory-variable-symbol ?symbol
               :counter/value ?value
               :as ?element}
              ^& ?rest-data}}]
    (let [element* (update ?element :counter/value inc)
          data* (conj ?rest-data element*)
          env* (assoc env :data data*)]
      [`(nth ~?symbol ~?value nil) env*])

    ;; Associate a counter.
    [{:symbol ?symbol}
     {:data #{{:memory-variable/symbol ?symbol
               :as ?value}
              ^:as ?data}}]
    (let [value* (merge ?value {:counter/memory-variable-symbol ?symbol
                                :counter/value 1})
          data* (conj ?data value*)
          env* (assoc env :data data*)]
      [`(nth ~?symbol 0 nil) env*])

    [{:symbol ?symbol}
     {:data #{^:as ?data}}]
    (let [memory-variable {:memory-variable/symbol ?symbol
                           :counter/memory-variable-symbol ?symbol
                           :counter/value 1}
          data* (conj ?data memory-variable)
          env* (assoc env :data data*)]
      [`(nth ~?symbol 0 nil) env*])))

(defmethod compile* :prt
  [node env]
  (r.match/match node
    {:left ?left
     :right ?right}
    (r.match/match (compile* ?left env)
      [?left-form ?left-env]
      (r.match/match (compile* ?right ?left-env)
        [?right-form ?right-env]
        [`(concat ~?left-form ~?right-form) ?right-env]))))

(defmethod compile* :quo
  [node env]
  [`(quote ~(:form node)) env])

(defmethod compile* :ref
  [node env]
  [`(~(:symbol node)) env])

(defmethod compile* :rp*
  [node env]
  (let [mvrs (r.syntax/memory-variables
              (r.syntax/substitute-refs node (get-wth-refs env)))]
    ;; If there are memory variables, compile a while loop that runs
    ;; until one of them has exauhsted its values.
    (if (seq mvrs)
      (let [;; Compile a conjunction of checks which will be performed
            ;; at the top of each loop. Each check verifies a memory
            ;; variable still has values to retrieve.
            checks (r.match/search [mvrs env]
                     [#{{:symbol ?symbol}}
                      {:data #{{:memory-variable/symbol ?symbol
                                :iterator/symbol ?iterator-symbol}}}]
                     (iterator-has-next-form ?iterator-symbol))
            ;; Compile each element of the corresponding `:cat` node
            ;; one at a time.
            [element-forms elements-env] (compile-all* (:elements (:cat node)) env)
            return-symbol (gensym "return__")]
        [`(loop [~return-symbol (transient [])]
                (if (and ~@checks)
                  (recur
                    ~(reduce (fn [ret form] `(conj! ~ret ~form))
                             return-symbol element-forms))
                  (persistent! ~return-symbol)))
         elements-env])
      ;; This should happen in a separate check phase.
      (throw (ex-info "No memory variables found for operator (...)"
                      {:node (r.syntax/unparse node)
                       :env env})))))

(defmethod compile* :rp+
  [node env]
  (r.match/match node
    {:cat {:elements ?elements}
     :n ?n}
    (let [[forms env] (compile-all* ?elements env)
          n-symbol (gensym "n__")
          return-symbol (gensym "return__")
          form `(loop [~return-symbol (transient [])
                       ~n-symbol ~?n]
                  ;; Yield n substitutions.
                  (if (zero? ~n-symbol)
                    (persistent! ~return-symbol)
                    (recur ~(reduce (fn [ret form] `(conj! ~ret ~form))
                                    return-symbol forms)
                           (unchecked-dec ~n-symbol))))]
      [form env])))

(defmethod compile* :rpl [node env]
  (r.match/match node
    {:cat {:elements ?elements}
     :lvr ?lvr}
    (let [[forms env] (compile-all* ?elements env)
          [n-form env] (compile* ?lvr env)
          n-symbol (gensym "n__")
          return-symbol (gensym "return__")
          form `(loop [~return-symbol (transient [])
                       ~n-symbol ~n-form]
                  ;; Yield ?n substitutions.
                  (if (zero? ~n-symbol)
                    (persistent! ~return-symbol)
                    (recur ~(reduce (fn [ret form] `(conj! ~ret ~form))
                                    return-symbol forms)
                           (unchecked-dec ~n-symbol))))]
      [form env])))

(defmethod compile* :rpm [node env]
  (r.match/match node
    {:cat {:elements ?elements}
     :mvr ?mvr}
    (let [[forms env] (compile-all* ?elements env)
          [n-form env] (compile* ?mvr env)
          n-symbol (gensym "n__")
          return-symbol (gensym "return__")
          ;; Yield !n substitutions. Note that unlike `:rpl`
          ;; and `:rp+` we need to guard against the
          ;; possibility of a `nil` value in the case the
          ;; memory variable has been exauhsted.
          form `(loop [~return-symbol (transient [])
                       ~n-symbol (or ~n-form 0)]
                  (if (zero? ~n-symbol)
                    (persistent! ~return-symbol)
                    (recur ~(reduce (fn [ret form] `(conj! ~ret ~form))
                                    return-symbol forms)
                           (unchecked-dec ~n-symbol))))]
      [form env])))

(defmethod compile* :rst
  [node env]
  (r.match/find [node env]
    ;; Check for associated memory variable in a `:finished` state.
    [{:mvr {:symbol ?symbol}}
     {:data #{{:memory-variable/symbol ?symbol
               :memory-variable/state :finished}}}]
    [nil env]

    ;; Check for associated iterator that is not in a `:finished`
    ;; state.
    [{:mvr {:symbol ?symbol}}
     {:data #{{:memory-variable/symbol ?symbol
               :iterator/symbol (r.match.syntax/pred symbol? ?iterator-symbol)
               :as ?memory-variable}
              ^& ?rest-data}}]
    (let [memory-variable* (assoc ?memory-variable :memory-variable/state :finished)
          data* (conj ?rest-data memory-variable*)
          env* (assoc env :data data*)]
      [(iterator-rest-form ?iterator-symbol) env*])

    ;; Check for associated counter.
    [{:mvr {:symbol ?symbol}}
     {:data #{{:memory-variable/symbol ?symbol
               :counter/value ?value
               :as ?memory-variable}
              ^& ?rest-data}}]
    (let [memory-variable* (assoc ?memory-variable :memory-variable/state :finished)
          data* (conj ?rest-data memory-variable*)
          env* (assoc env :data data*)]
      [`(subvec ~?symbol (min ~?value (count ~?symbol)))
       env*])

    ;; Update existing memory variable state.
    [{:mvr {:symbol ?symbol}}
     {:data #{{:memory-variable/symbol ?symbol
               :as ?memory-variable}
              ^& ?rest-data}}]
    (let [memory-variable* (assoc ?memory-variable :memory-variable/state :finished)
          data* (conj ?rest-data memory-variable*)
          env* (assoc env :data data*)]
      [?symbol env*])

    ;; Insert memory variable in a finished state.
    [{:mvr {:symbol ?symbol}}
     {:data #{^:as ?data}}]
    (let [memory-variable {:memory-variable/symbol ?symbol
                           :memory-variable/state :finished}
          data* (conj ?data memory-variable)
          env* (assoc env :data data*)]
      [?symbol env*])))

(defmethod compile* :set
  [node env]
  (let [[forms env] (compile-all* (:elements node) env)
        form (into #{} forms)
        [form env] (if-some [as-node (:as node)]
                     (let [[as-form as-env] (compile* as-node env)]
                       [`(into ~form ~as-form) as-env])
                     [form env])
        [form env] (if-some [rest-node (:rest node)]
                     (let [[rest-form rest-env] (compile* rest-node env)]
                       [`(into ~form ~rest-form) rest-env])
                     [form env])]
    [form env]))

(defmethod compile* :seq
  [node env]
  (r.match/match node
    {:prt ?prt
     :as nil}
    (r.match/match (compile* ?prt env)
      [?form ?env]
      [`(list* ~?form) ?env])))

(defmethod compile* :tail
  [node env]
  (compile* (:pattern node) env))

(defmethod compile* :unq
  [node env]
  [(:expr node) env])

(defmethod compile* :uns
  [node env]
  [(:expr node) env])

(defmethod compile* :vec
  [node env]
  (r.match/match node
    {:prt ?prt
     :as nil}
    (r.match/match (compile* ?prt env)
      [?form ?env]
      [`(into [] ~?form) ?env])))

(defmethod compile* :wth
  [node env]
  (let [;; Get all of the references used in the body and in the
        ;; bindings.
        ref-set (into (r.syntax/references node)
                      (comp (map :pattern)
                            (mapcat r.syntax/references))
                      (:bindings node))
        ;; Update the compilation environment for subnodes.
        env* (add-wth-refs env (r.syntax/make-ref-map node))
        [body-form env**] (compile* (:body node) env*)
        ;; Restore the original refs.
        env** (assoc env** :wth-refs (:wth-refs env))]
    ;; Compile functions only for the references used.
    [`(letfn [~@(r.match/search [node ref-set]
                  (r.syntax/with [%ref {:ref {:symbol ?symbol :as ?ref}
                                        :pattern ?pattern}
                                  %bindings (r.match.syntax/or [_ ... %ref . _ ...] (_ ... %ref . _ ...))]
                                 [{:bindings %bindings} #{?ref}])
                  (let [[form _] (compile* ?pattern env*)]
                    `(~?symbol [] ~form)))]
        ~body-form)
     env**]))

(defn rewrite-clojure*
  {:private true}
  [form]
  (clojure.walk/prewalk
   (fn f [form]
     (r.match/match form
       ;; concat rules
       ;; ------------

       (clojure.core/concat ?x)
       ?x

       (clojure.core/concat ?x ())
       ?x

       (clojure.core/concat () ?x)
       ?x

       (clojure.core/concat (clojure.core/list & ?args1) (clojure.core/list & ?args2) & ?rest-args)
       `(clojure.core/concat (clojure.core/list ~@?args1 ~@?args2) ~@ ?rest-args)

       (clojure.core/concat (clojure.core/list & _ :as ?list))
       ?list

       ;; cons rules
       ;; ----------

       (clojure.core/cons ?x ())
       `(list ~?x)

       (clojure.core/cons ?x (clojure.core/list . !xs ...))
       `(list ~?x ~@!xs)

       ;; conj rules
       ;; ----------

       (clojure.core/conj [!ys ...] . !xs ...)
       `[~@!ys ~@!xs]

       ;; lefn rules
       ;; -----------
       (clojure.core/letfn [] ?body)
       ?body

       (clojure.core/letfn [] & ?body)
       `(do ~?body)

       ;; list* rules
       ;; -----------

       (clojure.core/list* (clojure.core/list & _ :as ?list-form))
       ?list-form

       (clojure.core/list* . !list*-args ... (clojure.core/list* & ?last-list*-args))
       `(clojure.core/list* ~@!list*-args ~@?last-list*-args)

       ;; merge rules
       ;; -----------

       (clojure.core/merge {:as ?m1} {:as ?m2})
       (merge ?m1 ?m2)

       ;; or rules
       ;; --------
       (clojure.core/or (if ?test ?then) ?else)
       `(if ~?test ~?then ~?else)

       (clojure.core/or (if ?test ?then nil) ?else)
       `(if ~?test ~?then ~?else)

       ;; into rules
       ;; ----------

       (clojure.core/into [!xs ...] (clojure.core/cons ?x ?y))
       `(clojure.core/into (conj ~!xs ~?x) ~?y)

       (clojure.core/into [!xs ...] (clojure.core/list . !ys ...))
       `[~@!xs ~@!ys]

       (clojure.core/into [& _ :as ?vector] nil)
       ?vector

       (clojure.core/into [& _ :as ?vector] ())
       ?vector

       (clojure.core/into [] (clojure.core/subvec & _ :as ?subvec-form))
       ?subvec-form

       (clojure.core/into [] (clojure.core/loop [?ret (clojure.core/transient [])]
                               . _ ... .
                               (clojure.core/persistent! ?ret)
                               :as ?loop-form))
       ?loop-form

       (clojure.core/into {} (clojure.core/loop [?ret (clojure.core/transient [])]
                               . !forms ... .
                               (clojure.core/persistent! ?ret)))
       `(clojure.core/loop [~?ret (clojure.core/transient {})]
          ~@!forms
          (clojure.core/persistent! ~?ret))

       (clojure.core/into {:as ?m1} {:as ?m2})
       (merge ?m1 ?m2) 

       (clojure.core/into {:as ?m} [[_ _] ... :as ?v])
       (into ?m ?v)

       (clojure.core/into #{^:as ?s1} #{^:as ?s2})
       (into ?s1 ?s2) 

       (clojure.core/into (clojure.core/into #{^:as ?s1} ?x) #{^:as ?s2})
       `(clojure.core/into ~(into ?s1 ?s2) ~?x) 

       ;; else
       ;; ----

       ?x
       ?x))
   form))

(defn rewrite-clojure
  {:private true}
  [form]
  (let [form* (rewrite-clojure* form)]
    (if (= form form*)
      form
      (recur form*))))

(defn iter-bindings
  {:private true}
  [env]
  (into [] cat
        (r.match/search env
          {:data #{{:memory-variable/symbol ?memory-variable-symbol
                    :iterator/symbol (r.match.syntax/pred some? ?iterator-symbol)}}}
          [?iterator-symbol `(r.subst.runtime/iterator ~?memory-variable-symbol)])))

(defn compile [node env]
  (let [node (r.subst.syntax/expand-ast node)
        env (merge env (make-env node))
        [form env] (compile* node env)]
    (r.match/find env
      {:data #{{:error :cata-not-bound}}}
      ::CATA_NOT_BOUND

      _
      (let [form* (rewrite-clojure form)
            iter-bindings (iter-bindings env)
            form* (if (seq iter-bindings)
                    `(let ~iter-bindings ~form*)
                    form*)
            form* (if (and (not (get env :match-cata?))
                           (get env :subst-cata?))
                    (if (r.subst.syntax/contains-cata-node? node)
                      `(try
                         [~form*]
                         (catch Exception e#
                           (if (r.subst.runtime/fail? e#)
                             r.match.runtime/FAIL
                             (throw e#))))
                      [form*])
                    form*)]
        form*))))

(defmacro substitute
  [pattern]
  (let [node (r.subst.syntax/parse pattern &env)
        x (compile node &env)]
    (if (= ::CATA_NOT_BOUND x)
      (throw (ex-info "cata not allowed here" {:pattern pattern}))
      x)))

(s/fdef substitute
  :args (s/cat :pattern any?)
  :ret any?)
