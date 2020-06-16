(ns ^:no-doc meander.match.syntax.epsilon
  "This namespace defines the match syntax special forms, and AST
  transformations and queries."
  #?(:clj
     (:require [clojure.walk :as walk]
               [meander.environment.epsilon :as r.environment]
               [meander.syntax.epsilon :as r.syntax]
               [meander.util.epsilon :as r.util])
     :cljs
     (:require [clojure.walk :as walk]
               [meander.environment.epsilon :as r.environment]
               [meander.syntax.epsilon :as r.syntax]
               [meander.util.epsilon :as r.util])))

;; ---------------------------------------------------------------------
;; AST rewriting

(defn expand-as
  {:private true}
  [node]
  (if-some [as (:as node)]
    {:tag ::and
     :arguments [(assoc node :as nil) as]}
    node))

(defn flatten-and
  [and-node]
  (let [arguments (:arguments and-node)
        arguments* (mapcat
                    (fn f [node]
                      (if (= (r.syntax/tag node) ::and)
                        (mapcat f (:arguments node))
                        (list node)))
                    arguments)]
    {:tag ::and
     :arguments arguments*}))

(defn flatten-or
  [or-node]
  (let [arguments (:arguments or-node)
        arguments* (mapcat
                    (fn f [node]
                      (if (= (r.syntax/tag node) ::or)
                        (mapcat f (:arguments node))
                        (list node)))
                    arguments)]
    {:tag ::or
     :arguments arguments*}))

(defn infer-case
  {:private true}
  [node]
  (case (get node :tag)
    ::or
    (let [arguments (:arguments node)
          [a b] (split-with r.syntax/literal? arguments)]
      (case (count a)
        0
        node

        1
        {:tag ::or
         :arguments [(first a)
                     {:tag ::or
                      :arguments b}]}

        ;; else
        (let [case-tests (sequence
                          (comp (map r.syntax/lit-form)
                                (distinct)
                                (map r.util/case-test-form))
                          a)
              pred-form (vary-meta `(fn [x#]
                                      (case x#
                                        (~@case-tests)
                                        true
                                        false))
                                   assoc
                                   :meander.epsilon/beta-reduce true)
              pred-node {:tag ::pred
                         :form pred-form
                         :arguments []}]

          (if (seq b)
            {:tag ::or
             :arguments (vec (cons pred-node b))}
            pred-node))))

    ;; else
    node))

(defn expand-or
  [node]
  (let [node (flatten-or node)
        arguments (:arguments node)]
    (case (count arguments)
      1
      (first arguments)

      ;; else
      (infer-case node))))

(defn expand-map-rest
  [node]
  (if-some [rest-map (:rest-map node)]
    (let [key-map (into {} (keep (fn [k-node]
                                   (if (or (r.syntax/ground? k-node)
                                           (r.syntax/lvr-node? k-node))
                                     [k-node k-node]
                                     [k-node {:tag :mut
                                              :symbol (gensym "*m__")}])))
                        (keys (:map node)))
          map* (into {} (map (fn [[k-node v-node]]
                               (let [node (get key-map k-node)]
                                 (if (= node k-node)
                                   [k-node v-node]
                                   [{:tag ::and
                                     :arguments [k-node node]}
                                    v-node]))))
                     (:map node))
          node* (assoc node :rest-map nil)
          node* (assoc node* :map map*)
          disj-args (map
                     (fn [elem-node]
                       (case (get elem-node :tag)
                         ;; NOTE: `:expr` could be impure; we need to
                         ;; do better than this.
                         :unq
                         (get elem-node :expr)
                         ;; else
                         (r.syntax/unparse elem-node)))
                     (vals key-map))]
      {:tag ::and
       :arguments [node*
                   {:tag ::apply
                    :function (vary-meta
                               `(fn [m#]
                                  (dissoc m# ~@disj-args))
                               assoc
                               :meander.epsilon/beta-reduce true)
                    :argument rest-map}]})
    node))

(defn prioritize-map-entries
  {:private true}
  [node]
  (let [literal-keys (r.syntax/literal-keys node)
        non-literal-keys (r.syntax/non-literal-keys node)
        the-map (get node :map)]
    (cond
      (seq literal-keys)
      (let [rest-the-map (reduce dissoc the-map literal-keys)]
        (merge node
               {:map (select-keys the-map literal-keys)
                :rest-map (if (seq rest-the-map)
                            {:tag :map
                             :as nil
                             :map rest-the-map
                             :rest-map (get node :rest-map)}
                            (get node :rest-map))}))


      (< 1 (count non-literal-keys))
      (merge node
             {:map {(first non-literal-keys) (get the-map (first non-literal-keys))}
              :rest-map {:tag :map
                         :as nil
                         :map (select-keys the-map (next non-literal-keys))
                         :rest-map (get node :rest-map)}})

      :else node)))

(defn expand-map-keys
  [node]
  (prioritize-map-entries node))

(defn expand-map
  [node]
  (let [node* (prioritize-map-entries node)]
    (if (= node node*)
      (let [node* (expand-as node)]
        (if (= node node*)
          (expand-map-rest node)
          node*))
      (expand-map-rest node*))))

(defn eliminate-double-negation
  {:private true}
  [node]
  (let [argument (:argument node)]
    (if (= (r.syntax/tag argument) ::not)
      (:argument argument)
      node)))

(defn expand-not [node]
  (eliminate-double-negation node))

(defn expand-prt [node]
  (let [left (get node :left)
        right (get node :right)]
    (if (= (r.syntax/tag right) :prt)
      (let [right-left (get right :left)
            right-right (get right :right)]
        (if (and (= (r.syntax/tag left) :cat)
                 (= (r.syntax/tag right-left) :cat))
          (merge node {:left {:tag :cat
                              :elements (concat (get left :elements)
                                                (get right-left :elements))}
                       :right right-right})
          node))
      node)))

(defn infer-subset
  {:private true}
  [node])

(defn rewrite-set-rest-to-disj
  {:private true}
  [node]
  (if-some [rest-set (:rest node)]
    (let [elements (:elements node)
          elem-map (into {}
                         (map
                          (fn [node]
                            (if (or (r.syntax/ground? node)
                                    (r.syntax/lvr-node? node))
                              [node node]
                              [node {:tag :mut
                                     :symbol (gensym "*m__")}])))
                         elements)
          elements* (map
                     (fn [node]
                       (let [[n1 n2] (find elem-map node)]
                         (if (= n1 n2)
                           n1
                           {:tag ::and
                            :arguments [n1 n2]})))
                     elements)
          node* (assoc node :elements elements*)
          node* (dissoc node* :rest)
          disj-args (map
                     (fn [elem-node]
                       (case (get elem-node :tag)
                         ;; NOTE: `:expr` could be impure; we need to
                         ;; do better than this.
                         :unq
                         (get elem-node :expr)
                         ;; else
                         (r.syntax/unparse elem-node)))
                     (vals elem-map))]
      {:tag ::and
       :arguments [node* {:tag ::apply
                          :function (vary-meta
                                     `(fn [s#]
                                        (disj s# ~@disj-args))
                                     assoc
                                     :meander.epsilon/beta-reduce true)
                          :argument rest-set}]})
    node))

(defn expand-set-rest
  {:deprecated true}
  [node]
  (rewrite-set-rest-to-disj node))

(defn prioritize-literal-set-elements
  {:private true}
  [node]
  (let [literal-elements (r.syntax/literal-elements node)
        non-literal-elements (r.syntax/non-literal-elements node)]
    (cond
      (seq literal-elements)
      (merge node
             {:elements literal-elements
              :rest (if (seq non-literal-elements)
                      {:tag :set
                       :as nil
                       :elements non-literal-elements}
                      (get node :rest))})

      (< 1 (count non-literal-elements))
      (merge node
             {:elements [(first non-literal-elements)]
              :rest {:tag :set
                     :as nil
                     :elements (next non-literal-elements)
                     :rest (get node :rest)}})

      :else node)))

(defn expand-set-elements
  [node]
  (prioritize-literal-set-elements node))

(defn expand-set
  [node]
  (let [node* (expand-set-elements node)]
    (if (= node node*)
      (let [node* (expand-as node)]
        (if (= node* node)
          (rewrite-set-rest-to-disj node)
          node*))
      (rewrite-set-rest-to-disj node*))))

(defn expand-seq [node]
  (expand-as node))

(defn expand-vec [node]
  (expand-as node))

(defn expand-ast-top-down
  {:private true}
  ([node]
   (expand-ast-top-down node r.environment/default))
  ([node env]
   (let [eliminate-double-negation? (get env :meander.epsilon/eliminate-double-negation)
         flatten-and? (get env :meander.epsilon/flatten-and)
         flatten-or? (get env :meander.epsilon/flatten-or)
         infer-case? (get env :meander.epsilon/infer-case)
         infer-literal-seq? (get env :meander.epsilon/infer-literal-seq)
         infer-literal-vector? (get env :meander.epsilon/infer-literal-vector)
         prioritize-map-entries? (get env :meander.epsilon/prioritize-map-entries)
         prioritize-literal-set-elements? (get env :meander.epsilon/prioritize-literal-set-elements)
         rewrite-map-as-to-and? (get env :meander.epsilon/rewrite-map-as-to-and)
         rewrite-map-rest-to-dissoc? (get env :meander.epsilon/rewrite-map-rest-to-dissoc)
         rewrite-seq-as-to-and? (get env :meander.epsilon/rewrite-seq-as-to-and)
         rewrite-set-as-to-and? (get env :meander.epsilon/rewrite-set-as-to-and)
         rewrite-set-rest-to-disj? (get env :meander.epsilon/rewrite-set-rest-to-disj)
         rewrite-vector-as-to-and? (get env :meander.epsilon/rewrite-vector-as-to-and)
         substitute-acyclic-references? (get env :meander.epsilon/substitute-acyclic-references)]
     (r.syntax/prewalk
      (fn f [node]
        (case (r.syntax/tag node)
          ::and
          (if flatten-and?
            (flatten-and node)
            node)

          :map
          (let [node* (if prioritize-map-entries?
                        (prioritize-map-entries node)
                        node)
                node* (if (= node node*)
                        (if rewrite-map-as-to-and?
                          (expand-as node)
                          node*)
                        node*)]
            (if rewrite-map-rest-to-dissoc?
              (expand-map-rest node*)
              node*))

          ::not
          (if eliminate-double-negation?
            (eliminate-double-negation node)
            node)

          ::or
          (let [node (if flatten-or?
                       (flatten-or node)
                       node)
                node (if infer-case?
                       (infer-case node)
                       node)]
            node)

          :prt
          (expand-prt node)

          :set
          (let [node* (if prioritize-literal-set-elements?
                        (prioritize-literal-set-elements node)
                        node)
                node* (if (= node* node)
                        (if rewrite-set-as-to-and?
                          (expand-as node)
                          node)
                        node*)]
            (if rewrite-set-rest-to-disj?
              (rewrite-set-rest-to-disj node*)
              node*))

          :seq
          (let [node (if rewrite-seq-as-to-and?
                       (expand-as node)
                       node)
                node (if (and infer-literal-seq? (r.syntax/literal? node))
                       {:tag :lit, :value (r.syntax/lit-form node)}
                       node)]
            node)

          :vec
          (let [node (if rewrite-vector-as-to-and?
                       (expand-as node)
                       node)
                node (if (and infer-literal-vector? (r.syntax/literal? node))
                       {:tag :lit, :value (r.syntax/lit-form node)}
                       node)]
            node)

          :wth
          (if substitute-acyclic-references?
            (r.syntax/substitute-acyclic-refs node)
            node)

          ;; else
          node))
      node))))


;; Produces shorter but potentially slower code.
(defn abstract-plus
  {:private true}
  [node]
  (let [cat-node (get node :cat)
        cat-elements (get cat-node :elements)
        n (get node :n)
        node-map (into {} (map (fn [node]
                                 [node (r.syntax/genref)]))
                       cat-elements)]
    {:tag :wth
     :bindings (mapv
                (fn [[node ref]]
                  {:ref ref, :pattern node})
                node-map)
     :body
     {:tag :prt
      :left {:tag :cat
             :elements (into [] cat (repeat n (map node-map cat-elements)))}
      :right {:tag :rp*
              :cat {:tag :cat
                    :elements (mapv node-map cat-elements)}}}}))

(defn expand-ast-bottom-up
  {:private true}
  ([node]
   (expand-ast-bottom-up r.environment/default))
  ([node env]
   (let [abstract-disjunction? (get env :meander.epsilon/abstract-disjunction)
         abstract-plus? (get env :meander.epsilon/abstract-plus)]
     (r.syntax/postwalk
      (fn [node]
        (case (r.syntax/tag node)
          ::or
          (if abstract-disjunction?
            (r.syntax/abstract node)
            node)

          :rp+
          (let [n (get node :n)]
            (if (zero? n)
              (assoc (dissoc node :n) :tag :rp*)
              (if abstract-plus?
                (abstract-plus node)
                node)))

          ;; else
          node))
      node))))


(defn expand-ast
  "Takes an AST node as returned by `meander.syntax.epsilon/parse` and
  expands it in such a way that it can either reduce compiled code
  size, improve compiled code efficiency, or both."
  ([node]
   (expand-ast node r.environment/default))
  ([node env]
   (let [node* (-> (expand-ast-top-down node env)
                   (expand-ast-bottom-up env)
                   (r.syntax/rename-refs)
                   (r.syntax/consolidate-with))
         node* (if (seq (get node* :bindings))
                 node*
                 (get node* :body))]
     node*)))

;; ---------------------------------------------------------------------
;; Syntax analysis

(defn not-not?
  "true if `node` represents the syntax `(not (not <pattern>))`."
  {:private true}
  [node]
  (= (:tag node)
     (:tag (:argument node))
     ::not))

(defn not-tag
  "Returns `:not-not` if `node` represents the syntax
  `(not (not <pattern>))`; `:not` if `node` represents the syntax
  `(not <pattern>)`; `nil` otherwise."
  {:private true}
  [node]
  (case (:tag node)
    ::not
    (case (:tag (:argument node))
      ::not
      :not-not
      ;; else
      :not)
    ;; else
    nil))

(defn analyze*
  {:private true}
  [node]
  (r.syntax/fold
   (fn [state node]
     (let [negated-counter (:negated-counter state)]
       {:negated-counter
        (if (zero? negated-counter)
          (case (not-tag node)
            :not
            (+ negated-counter (dec (count (r.syntax/subnodes node))))

            :not-not
            (+ negated-counter 1)

            ;; else
            negated-counter)
          (dec negated-counter))

        :occurrences
        (if (r.syntax/variable-node? node)
          (update (:occurrences state) node (fnil inc 0))
          (:occurrences state))

        :occurrences-in-not
        (if (and (not (zero? negated-counter))
                 (r.syntax/variable-node? node))
          (update (:occurrences-in-not state) node (fnil inc 0))
          (:occurrences-in-not state))}))
   {;; The `nat-int?` number of nodes currently under a negation.
    :negated-counter 0
    ;; A map from `variable-node?` to `nat-int?`. Keeps track of how
    ;; many times a `variable-node?` appears.
    :occurrences {}
    ;; A map from `variable-node?` to `nat-int?`. Keeps track of how
    ;; many times a `variable-node?` appears inside a `not` pattern.
    :occurrences-in-not {}}
   node))

(defn analyze
  [node]
  (dissoc (analyze* node) :negated-counter))

;; ---------------------------------------------------------------------
;; Special forms

;;; and

(def and-symbol
  'meander.match.syntax.epsilon/and)

(defn parse-and
  [[_ & args] env]
  {:tag ::and
   :arguments (r.syntax/parse-all args env)})

(defmethod r.syntax/children ::and
  [node] (:arguments node))

(defmethod r.syntax/ground? ::and
  [node] false)

(defmethod r.syntax/unparse ::and
  [node] `(~and-symbol ~@(sequence (map r.syntax/unparse) (:arguments node))))

(defmethod r.syntax/walk ::and [inner outer node]
  (outer (assoc node :arguments (mapv inner (:arguments node)))))

(defmethod r.syntax/search? ::and
  [node] (boolean (some r.syntax/search? (:arguments node))))

;;; apply

(def apply-symbol
  'meander.match.syntax.epsilon/apply)

(defn parse-apply [form env]
  (let [args (rest form)]
    (if (= 2 (bounded-count 3 args))
      {:tag ::apply
       :function (first args)
       :argument (r.syntax/parse (second args) env)}
      (throw (ex-info "meander.match.syntax.epsilon/apply requires two arguments" {})))))

(defmethod r.syntax/children ::apply [node]
  [(:argument node)])

(defmethod r.syntax/ground? ::apply [_]
  false)

(defmethod r.syntax/unparse ::apply [node]
  `(~apply-symbol
    ~(:function node)
    ~(r.syntax/unparse (:argument node))))

(defmethod r.syntax/search? ::apply
  [_] false)

(defmethod r.syntax/walk ::apply [inner outer node]
  (outer (assoc node :argument (inner (:argument node)))))

;;; cata

(def cata-symbol
  'meander.match.syntax.epsilon/cata)

(defn parse-cata [form env]
  (let [args (rest form)]
    (if (= 1 (count args))
      {:tag ::cata
       :argument (r.syntax/parse (first args) env)}
      (throw (ex-info "meander.match.syntax.epsilon/cata requires one argument" {})))))

(defmethod r.syntax/children ::cata [node]
  [(:argument node)])

(defmethod r.syntax/ground? ::cata [_]
  false)

(defmethod r.syntax/unparse ::cata [node]
  `(~cata-symbol
    ~(r.syntax/unparse (:argument node))))

(defmethod r.syntax/search? ::cata
  [_] false)

(defmethod r.syntax/walk ::cata [inner outer node]
  (outer (assoc node :argument (inner (:argument node)))))

(defn cata-node?
  "true if `x` is a `:meander.match.syntax.epsilon/cata` node."
  [x]
  (and (map? x) (= (get x :tag) ::cata)))

(defn contains-cata-node?
  [root-node]
  (r.syntax/fold
   (fn [_ node]
     (cond
       (cata-node? node)
       (reduced true)

       (r.syntax/with-node? node)
       (let [ref-map (r.syntax/make-ref-map node)
             in-use (r.syntax/refs-in-use node)]
         (if (some contains-cata-node? (map ref-map in-use))
           (reduced true)
           false))))
   false
   root-node))

;;; guard

(def guard-symbol
  'meander.match.syntax.epsilon/guard)

(defn parse-guard [[_ expr] env]
  {:tag ::guard
   :expr expr})


(defmethod r.syntax/ground? ::guard
  [node] false)

(defmethod r.syntax/unparse ::guard
  [node] `(~guard-symbol ~(:expr node)))

(defmethod r.syntax/search? ::guard
  [node] false)

;;; let

(def let-symbol
  'meander.match.syntax.epsilon/let)

(defn parse-let [[_ & args :as form] env]
  (case (bounded-count 4 args)
    2
    (let [[pattern expression] args]
      {:tag ::let
       :pattern (r.syntax/parse pattern env)
       :expression expression})

    3
    (let [[pattern expression then] args]
      {:tag ::and
       :arguments [{:tag ::let
                    :pattern (r.syntax/parse pattern env)
                    :expression expression}
                   (r.syntax/parse then env)]})

    ;; else
    (throw (ex-info "meander.match.syntax.epsilon/let expects two or three arguments"
                    {:pattern form
                     :meta (meta form)}))))


(defmethod r.syntax/children ::let
  [node] [(:pattern node)])

(defmethod r.syntax/ground? ::let
  [node] false)

(defmethod r.syntax/unparse ::let
  [node]
  `(~let-symbol ~(r.syntax/unparse (:pattern node)) ~(:expression node)))

(defmethod r.syntax/search? ::let
  [node] false)

(defmethod r.syntax/walk ::let
  [inner outer node]
  (outer (update node :pattern inner)))

;;; not

(def not-symbol
  'meander.match.syntax.epsilon/not)

(defn parse-not [[_ & args :as form] env]
  (if (= 1 (bounded-count 2 args))
    {:tag ::not
     :argument (r.syntax/parse (first args) env)}
    (throw (ex-info "meander.match.syntax.epsilon/not pattern requires at one argument"
                    {:pattern form
                     :meta (meta form)}))))


(defmethod r.syntax/children ::not
  [node] [(:argument node)])

(defmethod r.syntax/ground? ::not
  [node] false)

(defmethod r.syntax/walk ::not
  [inner outer node]
  (outer (assoc node :argument (inner (:argument node)))))

(defmethod r.syntax/unparse ::not
  [node] `(~not-symbol ~(r.syntax/unparse (:argument node))))

(defmethod r.syntax/search? ::not
  [node] (r.syntax/search? (:argument node)))

;;; or

(def or-symbol
  'meander.match.syntax.epsilon/or)

(defn parse-or [[_ & args :as form] env]
  {:tag ::or
   :arguments (r.syntax/parse-all args env)})

(defmethod r.syntax/children ::or
  [node] (:arguments node))

(defmethod r.syntax/ground? ::or
  [node] false)

(defmethod r.syntax/walk ::or
  [inner outer node]
  (outer (assoc node :arguments (mapv inner (:arguments node)))))

(defmethod r.syntax/unparse ::or
  [node]
  `(~or-symbol ~@(sequence (map r.syntax/unparse) (:arguments node))))

(defmethod r.syntax/search? ::or
  [node]
  (boolean (some r.syntax/search? (:arguments node))))


;;; pred

(def pred-symbol
  'meander.match.syntax.epsilon/pred)

(defn parse-pred [[_ expr & args :as form] env]
  {:tag ::pred
   :form expr
   :arguments (r.syntax/parse-all args env)})


(defmethod r.syntax/children ::pred
  [node] (:arguments node))

(defmethod r.syntax/ground? ::pred [_]
  false)

(defmethod r.syntax/walk ::pred
  [inner outer node]
  (outer (assoc node :arguments (mapv inner (:arguments node)))))

(defmethod r.syntax/unparse ::pred
  [node] `(~pred-symbol ~(:form node) ~@(map r.syntax/unparse (:arguments node))))

(defmethod r.syntax/search? ::pred [node]
  (boolean (some r.syntax/search? (:arguments node))))

;;; re

(def re-symbol
  'meander.match.syntax.epsilon/re)

(defn parse-re [[_ & args :as form] env]
  (case (bounded-count 3 args)
    1 {:tag ::rxt
       :regex (first args)}
    2 {:tag ::rxc
       :regex (first args)
       :capture (r.syntax/parse (second args) env)}
    (throw (ex-info "meander.match.syntax.epsilon/re expects one or two arguments"
                    {:pattern form
                     :meta (meta form)}))))


(defmethod r.syntax/children ::rxc
  [node] [(:capture node)])

(defmethod r.syntax/ground? ::rxc
  [node] (r.syntax/ground? (:capture node)))

(defmethod r.syntax/walk ::rxc
  [inner outer node]
  (outer (assoc node :capture (inner (:capture node)))))

(defmethod r.syntax/search? ::rxc
  [node] (r.syntax/search? (:capture node)))

(defmethod r.syntax/unparse ::rxc
  [node]
  `(~re-symbol (r.syntax/unparse (:regex node)) (r.syntax/unparse (:capture node))))

(defmethod r.syntax/search? ::rxt
  [node] false)

(defmethod r.syntax/ground? ::rxt
  [node] false)

(defmethod r.syntax/unparse ::rxt
  [node] `(~re-symbol ~(r.syntax/unparse (:regex node))))


(def default-parsers
  {and-symbol #'parse-and
   apply-symbol #'parse-apply
   cata-symbol #'parse-cata
   guard-symbol #'parse-guard
   let-symbol #'parse-let
   not-symbol #'parse-not
   or-symbol #'parse-or
   pred-symbol #'parse-pred
   re-symbol #'parse-re})

(defn parse
  ([form]
   (parse form {}))
  ([form env]
   (let [parser-registry (merge (deref r.syntax/global-parser-registry)
                                default-parsers)
         expander-registry (deref r.syntax/global-expander-registry)
         env (merge env {::r.syntax/expander-registry expander-registry
                         ::r.syntax/phase :meander/match
                         ::r.syntax/parser-registry parser-registry})]
     (r.syntax/parse form env))))
