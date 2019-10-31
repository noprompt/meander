(ns ^:no-doc meander.substitute.syntax.epsilon
  "This namespace defines the substition syntax special forms, and AST
  transformations and queries."
  #?(:clj
     (:require [clojure.walk :as walk]
               [clojure.spec.alpha :as s]
               [clojure.core.specs.alpha :as core.specs]
               [meander.match.epsilon :as r.match]
               [meander.match.syntax.epsilon :as r.match.syntax]
               [meander.syntax.epsilon :as r.syntax]
               [meander.util.epsilon :as r.util])
     :cljs
     (:require [clojure.walk :as walk]
               [cljs.spec.alpha :as s :include-macros true]
               [cljs.core.specs.alpha :as core.specs]
               [meander.match.epsilon :as r.match]
               [meander.match.syntax.epsilon :as r.match.syntax]
               [meander.syntax.epsilon :as r.syntax]
               [meander.util.epsilon :as r.util])))

;; ---------------------------------------------------------------------
;; Rewriting

(defn rewrite-partition
  [node]
  (r.match/find node
    (r.syntax/with [%right (r.match.syntax/not (or () []))]
      {:tag :prt
       :left {:tag :cat
              :elements ?ls}
       :right {:tag :cat
               :elements (r.match.syntax/and %right ?rs)}
       :as ?prt})
    {:tag :prt
     :left {:tag :cat
            :elements (vec (concat ?ls ?rs))}
     :right {:tag :cat
             :elements []}}

    {:tag :prt
     :left {:tag :cat
            :elements ?elements1}
     :right {:tag :prt
             :left {:tag :cat
                    :elements ?elements2}
             :right ?right}}
    (rewrite-partition
     {:tag :prt
      :left {:tag :cat
             :elements (vec (concat ?elements1 ?elements2))}
      :right ?right})

    {:tag :prt
     :left {:tag :prt,
            :left ?left,
            :right {:tag :cat
                    :elements ?elements-1}},
     :right {:tag :cat
             :elements ?elements-2}}
    (rewrite-partition
     {:tag :prt
      :left ?left
      :right {:tag :cat
              :elements (vec (concat ?elements-1 ?elements-2))}})

    {:tag :prt
     :left ?left
     :right {:tag :prt
             :left {:tag :cat
                    :elements ?elements}
             :right {:tag :cat
                     :elements (r.match.syntax/or [] ())}}}
    {:tag :prt
     :left ?left
     :right {:tag :cat
             :elements ?elements}}

    _
    node))

(defn rewrite-partitions
  [node]
  (r.syntax/prewalk rewrite-partition node))

(defn rewrite-coerce-literals-to-lit
  [node]
  (r.syntax/prewalk
   (fn [node]
     (if (and (r.syntax/literal? node)
              (not= (r.syntax/tag node) :cat)
              (not= (r.syntax/tag node) :prt))
       {:tag :lit
        :value (r.syntax/lit-form node)}
       node))
   node))

(defn expand-ast-top-down
  {:private true}
  [node]
  (r.syntax/prewalk
   (fn f [node]
     (case (r.syntax/tag node)
       :prt
       (rewrite-partition node)

       :wth
       (r.syntax/substitute-acyclic-refs node)

       ;; else
       node))
   node))

(defn expand-ast
  [node]
  (expand-ast-top-down
   (r.syntax/rename-refs
    (rewrite-coerce-literals-to-lit
     node))))

(defn cata-index [node]
  (r.match/search (r.syntax/subnodes node)
    (_ ... {:tag ::cata :as ?node} . _ ...)
    [?node (gensym "X__")]))

(defn cata-nodes [node]
  (r.match/search (r.syntax/subnodes node)
    (_ ... {:tag ::cata :as ?node} . _ ...)
    ?node))

;; ---------------------------------------------------------------------
;; Special forms

;;; apply

(def apply-symbol
  'meander.substitute.syntax.epsilon/apply)

(defn parse-apply
  [form env]
  (let [args (rest form)]
    (if (= 2 (bounded-count 3 args))
      {:tag ::apply
       :function (first args)
       :argument (r.syntax/parse (second args) env)}
      (throw (ex-info "meander.substitute.syntax.epsilon/apply requires two arguments" {})))))

(defmethod r.syntax/children ::apply
  [node]
  [(:argument node)])

(defmethod r.syntax/ground? ::apply
  [node]
  false)

(defmethod r.syntax/unparse ::apply
  [node]
  `(~apply-symbol ~(:function node) ~(r.syntax/unparse (:argument node))))

(defmethod r.syntax/search? ::apply
  [node]
  false)

(defmethod r.syntax/walk ::apply
  [inner outer node]
  (outer (assoc node :argument (inner (:argument node)))))

;;; cata

(def cata-symbol
  'meander.substitute.syntax.epsilon/cata)

(defn parse-cata [form env]
  (let [args (rest form)]
    (if (= 1 (count args))
      {:tag ::cata
       :argument (r.syntax/parse (first args) env)}
      (throw (ex-info "meander.substitute.syntax.epsilon/cata requires one argument" {})))))

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
  "true if `x` is a `:meander.substitute.syntax.epsilon/cata` node."
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

(def default-parsers
  {apply-symbol #'parse-apply
   cata-symbol #'parse-cata})

(defn parse
  ([form]
   (parse form {}))
  ([form env]
   (let [parser-registry (merge (deref r.syntax/global-parser-registry)
                                default-parsers)
         env (merge env {::r.syntax/expander-registry (deref r.syntax/global-expander-registry)
                         ::r.syntax/phase :meander/substitute
                         ::r.syntax/parser-registry parser-registry})]
     (r.syntax/parse form env))))
