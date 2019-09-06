(ns meander.match.syntax.epsilon
  "This namespace defines the match syntax special forms, and AST
  transformations and queries."
  #?(:clj
     (:require [clojure.walk :as walk]
               [clojure.spec.alpha :as s]
               [clojure.core.specs.alpha :as core.specs]
               [meander.syntax.epsilon :as r.syntax]
               [meander.util.epsilon :as r.util])
     :cljs
     (:require [clojure.walk :as walk]
               [cljs.spec.alpha :as s :include-macros true]
               [cljs.core.specs.alpha :as core.specs]
               [meander.syntax.epsilon :as r.syntax]
               [meander.util.epsilon :as r.util]))
  #?(:cljs
     (:require-macros [meander.syntax.epsilon])))

(defn parse
  [form env]
  (r.syntax/parse form (merge env {::r.syntax/expander-registry (deref r.syntax/global-expander-registry)
                                   ::r.syntax/phase :meander/match
                                   ::r.syntax/parser-registry (deref r.syntax/global-parser-registry)})))

;; ---------------------------------------------------------------------
;; AST rewriting

(defn expand-as
  {:private true}
  [node]
  (if-some [as (:as node)]
    {:tag ::and
     :arguments [as (assoc node :as nil)]}
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

(defn expand-or
  [node]
  (let [arguments (:arguments (flatten-or node))]
    (case (count arguments)
      1
      (first arguments)

      ;; else
      (let [[a b] (split-with r.syntax/literal? arguments)]
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


                ;; If we have other arguments, then we should try the
                ;; rest of our disjuncts if the case-tests fail. If
                ;; there are no arguments, we should continue matching
                ;; if we pass the case-tests. That is why we have true
                ;; and false switched based on b below.
                pred-form (vary-meta (if (seq b)
                                       `(fn [x#]
                                          (case x#
                                            (~@case-tests)
                                            false
                                            true))
                                       `(fn [x#]
                                          (case x#
                                            (~@case-tests)
                                            true
                                            false)))
                                     assoc
                                     :meander.match.syntax.epsilon/beta-reduce true)]

          {:tag ::pred
           :form pred-form
           :arguments (if (seq b)
                        [{:tag ::or
                          :arguments b}]
                        [])}))))))

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
          node* (assoc node* :map map*)]
      {:tag ::and
       :arguments [node*
                   {:tag ::apply
                    :function `(fn [m#]
                                 (dissoc m# ~@(map r.syntax/unparse (vals key-map))))
                    :argument rest-map}]})
    node))

(defn expand-map
  [node]
  (let [node* (expand-as node)]
    (if (= node* node)
      (expand-map-rest node)
      node*)))

(defn expand-not [node]
  (let [argument (:argument node)]
    (if (= (r.syntax/tag argument) ::not)
      (:argument argument)
      node)))

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

(defn expand-set-rest [node]
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
          node* (dissoc node* :rest)]
      {:tag ::and
       :arguments [node* {:tag ::apply
                          :function `(fn [s#]
                                       (disj s# ~@(map r.syntax/unparse (vals elem-map))))
                          :argument rest-set}]})
    node))

(defn expand-set
  [node]
  (let [node* (expand-as node)]
    (if (= node* node)
      (expand-set-rest node)
      node*)))

(defn expand-seq [node]
  (expand-as node))

(defn expand-vec [node]
  (expand-as node))

(defn expand-ast-top-down
  {:private true}
  [node]
  (r.syntax/prewalk
   (fn f [node]
     (case (r.syntax/tag node)
       ::and
       (flatten-and node)

       ::or
       (expand-or node)

       :map
       (expand-map node)

       ::not
       (expand-not node)

       :prt
       (expand-prt node)

       :set
       (expand-set node)

       :seq
       (expand-seq node)

       :vec
       (expand-vec node)

       ;; else
       node))
   node))

(defn expand-ast-bottom-up
  {:private true}
  [node]
  (r.syntax/postwalk
   (fn [node]
     (case (r.syntax/tag node)
       :rp+
       (let [n (:n node)]
         (if (= 0 n)
           {:tag :prt
            :left (:cat node)
            :right {:tag :rp*
                    :cat (:cat node)}}
           {:tag :prt
            :left (:cat node)
            :right {:tag :rp+
                    :cat (:cat node)
                    :n (dec n)}}))

       ;; else
       node))
   node))

(defn expand-ast
  "Takes an AST node as returned by `meander.syntax.epsilon/parse` and
  expands it in such a way that it can either reduce compiled code
  size, improve compiled code efficiency, or both."
  [node]
  (expand-ast-bottom-up
   (expand-ast-top-down
    (r.syntax/rename-refs node))))

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

(r.syntax/register-parser and-symbol #'parse-and)

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

(r.syntax/register-parser apply-symbol #'parse-apply)

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

;;; guard

(def guard-symbol
  'meander.match.syntax.epsilon/guard)

(defn parse-guard [[_ expr] env]
  {:tag ::guard
   :expr expr})

(r.syntax/register-parser guard-symbol #'parse-guard)

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

(r.syntax/register-parser let-symbol #'parse-let)

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

(r.syntax/register-parser not-symbol #'parse-not)

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

(r.syntax/register-parser or-symbol #'parse-or)

;;; pred

(def pred-symbol
  'meander.match.syntax.epsilon/pred)

(defn parse-pred [[_ expr & args :as form] env]
  {:tag ::pred
   :form expr
   :arguments (r.syntax/parse-all args env)})

(r.syntax/register-parser pred-symbol #'parse-pred)

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

(r.syntax/register-parser re-symbol #'parse-re)

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
