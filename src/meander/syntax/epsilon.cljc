(ns meander.syntax.epsilon
  #?(:clj
     (:require [clojure.set :as set]
               [clojure.spec.alpha :as s]
               [clojure.spec.gen.alpha :as s.gen]
               [clojure.string :as string]
               [cljs.tagged-literals]
               [meander.util.epsilon :as r.util])
     :cljs
     (:require [clojure.set :as set]
               [cljs.spec.alpha :as s :include-macros true]
               [cljs.spec.gen.alpha :as s.gen :include-macros true]
               [clojure.string :as string]
               [meander.util.epsilon :as r.util]))
  #?(:cljs
     (:require-macros [meander.syntax.epsilon]))
  #?(:clj
     (:import (cljs.tagged_literals JSValue))))

#?(:clj (set! *warn-on-reflection* true))

;; ---------------------------------------------------------------------
;; AST specs and predicates

(s/def :meander.syntax.epsilon/tag
  keyword?)

(s/def :meander.syntax.epsilon/node
  (s/keys :req-un [:meander.syntax.epsilon/tag]))

(defn any-form?
  "true if x is a symbol beginning with _."
  [x]
  (and (simple-symbol? x)
       (r.util/re-matches? #"_.*" (name x))))

(s/def :meander.syntax.epsilon/any
  (s/with-gen
    (s/conformer
     (fn [x]
       (if (any-form? x)
         x
         ::s/invalid))
     identity)
    (fn []
      (s.gen/fmap
       (fn [sym]
         (symbol (str "_" (name sym))))
       (s.gen/symbol)))))

(s/def :meander.syntax.epsilon.node.any/tag
  #{:any})

(s/def :meander.syntax.epsilon.node.any/symbol
  :meander.syntax.epsilon/any)

(s/def :meander.syntax.epsilon.node/any
  (s/keys :req-un [:meander.syntax.epsilon.node.any/tag
                   :meander.syntax.epsilon.node.any/symbol]))

(defn any-node?
  "true if x is an :any node, false otherwise."
  [x]
  (s/valid? :meander.syntax.epsilon.node/any x))

(s/fdef logic-variable-form?
  :args (s/cat :x any?)
  :ret boolean?)

(defn logic-variable-form?
  "true if x is in the form of a logic variable i.e. a simple symbol
  with a name beginning with \\?."
  [x]
  (and (simple-symbol? x)
       (r.util/re-matches? #"\?.+" (name x))))

(s/def :meander.syntax.epsilon/logic-variable
  (s/with-gen
    (s/conformer
     (fn [x]
       (if (logic-variable-form? x)
         x
         ::s/invalid))
     identity)
    (fn []
      (s.gen/fmap
       (fn [x]
         (symbol (str \? (name x))))
       (s/gen simple-symbol?)))))

(s/def :meander.syntax.epsilon.node.lvr/tag
  #{:lvr})

(s/def :meander.syntax.epsilon.node.lvr/symbol
  :meander.syntax.epsilon/logic-variable)

(s/def :meander.syntax.epsilon.node/lvr
  (s/keys :req-un [:meander.syntax.epsilon.node.lvr/tag
                   :meander.syntax.epsilon.node.lvr/symbol]))

(defn lvr-node?
  [x]
  (s/valid? :meander.syntax.epsilon.node/lvr x))

(defn memory-variable-form?
  "true if x is in the form of a memory variable i.e. a simple symbol
  with a name beginning with \\!."
  [x]
  (and (simple-symbol? x)
       (r.util/re-matches? #"!.+" (name x))))

(s/def :meander.syntax.epsilon/memory-variable
  (s/with-gen
    (s/conformer
     (fn [x]
       (if (memory-variable-form? x)
         x
         ::s/invalid))
     identity)
    (fn []
      (s.gen/fmap
       (fn [x]
         (symbol (str \! (name x))))
       (s/gen simple-symbol?)))))

(s/def :meander.syntax.epsilon.node.mvr/tag
  #{:mvr})

(s/def :meander.syntax.epsilon.node.mvr/symbol
  :meander.syntax.epsilon/memory-variable)

(s/def :meander.syntax.epsilon.node/mvr
  (s/keys :req-un [:meander.syntax.epsilon.node.mvr/tag
                   :meander.syntax.epsilon.node.mvr/symbol]))

(defn mvr-node?
  [x]
  (s/valid? :meander.syntax.epsilon.node/mvr x))

(defn variable-node?
  [x]
  (or (mvr-node? x)
      (lvr-node? x)))

(defn ref-sym?
  [x]
  (and (simple-symbol? x)
       (boolean (re-matches #"%.+" (name x)))))

(s/def :meander.syntax.epsilon/reference
  (s/with-gen
    (s/conformer
     (fn [x]
       (if (ref-sym? x)
         x
         ::s/invalid))
     identity)
    (fn []
      (s.gen/fmap
       (fn [x]
         (symbol (str \% (name x))))
       (s/gen simple-symbol?)))))

(s/def :meander.syntax.epsilon.node.ref/tag
  #{:ref})

(s/def :meander.syntax.epsilon.node.ref/symbol
  :meander.syntax.epsilon/reference)

(s/def :meander.syntax.epsilon.node/ref
  (s/keys :req-un [:meander.syntax.epsilon.node.ref/symbol
                   :meander.syntax.epsilon.node.ref/tag]))

(defn ref-node?
  "true if x is a :ref node, false otherwise."
  [x]
  (s/valid? :meander.syntax.epsilon.node/ref x))

(s/def :meander.syntax.epsilon.node.with/tag
  #{:wth})

(s/def :meander.syntax.epsilon.node.with.binding/ref
  :meander.syntax.epsilon.node/ref)

(s/def :meander.syntax.epsilon.node.with.binding/pattern
  :meander.syntax.epsilon/node)

(s/def :meander.syntax.epsilon.node.with/binding
  (s/keys :req-un [:meander.syntax.epsilon.node.with.binding/pattern
                   :meander.syntax.epsilon.node.with.binding/ref]))

(s/def :meander.syntax.epsilon.node.with/bindings
  (s/coll-of :meander.syntax.epsilon.node.with/binding
             :kind sequential?))

(s/def :meander.syntax.epsilon.node.with/body
  (s/nilable :meander.syntax.epsilon/node))

(s/def :meander.syntax.epsilon.node/with
  (s/keys :req-un [:meander.syntax.epsilon.node.with/tag
                   :meander.syntax.epsilon.node.with/bindings]
          :opt-un [:meander.syntax.epsilon.node.with/body]))

(defn with-node? [x]
  (s/valid? :meander.syntax.epsilon.node/with x))


(s/def :meander.syntax.epsilon.node.partition/left
  :meander.syntax.epsilon/node)

(s/def :meander.syntax.epsilon.node.partition/right
  :meander.syntax.epsilon/node)

(s/def :meander.syntax.epsilon.node/partition
  (s/keys :req-un [:meander.syntax.epsilon.node.partition/left
                   :meander.syntax.epsilon.node.partition/right]))

(defn partition-node? [x]
  (s/valid? :meander.syntax.epsilon.node/partition x))

(defn node?
  "true if x is an AST node."
  [x]
  (s/valid? :meander.syntax.epsilon/node x))

;; ---------------------------------------------------------------------
;; AST API

(defn tag
  "Return the tag of node."
  [node]
  (s/assert :meander.syntax.epsilon/node node)
  (:tag node))

;; children
;; --------

(s/fdef children
  :args (s/cat :node :meander.syntax.epsilon/node)
  :ret (s/coll-of :meander.syntax.epsilon/node
                  :kind sequential?))

(defmulti children
  "Return a sequential? of all children of node."
  {:arglists '([node])}
  #'tag)

(defmethod children :default
  [node]
  [])

(defn subnodes
  "Return a sequence of all subnodes of node."
  [node]
  (cons node (mapcat subnodes (children node))))

(defn proper-subnodes
  "Return the all subnodes in node excluding node."
  [node]
  (rest (subnodes node)))

(s/fdef max-length
  :args (s/cat :node :meander.syntax.epsilon/node)
  :ret (s/or :nat nat-int?
             :inf #{##Inf}))

(defmulti max-length
  "The maximum possible length the pattern described by node can
  be. Note, this mutlimethod will throw an error wheneven node does
  not have a method to handle it. This behavior is intentional as the
  implementations should only exist for things which have can have
  length."
  {:arglists '([node])}
  #'tag)

(defmulti min-length
  "The maximum possible length the pattern described by node can be.
  Note, this mutlimethod will throw an error wheneven node does not
  have a method to handle it. This behavior is intentional as the
  implementations should only exist for things which have can have
  length."
  {:arglists '([node])}
  #'tag)

(s/fdef variable-length?
  :args (s/cat :node :meander.syntax.epsilon/node)
  :ret boolean?)

(defn variable-length?
  "true if node may have a variable length, false otherwise. Note this
  function will throw an error if node does not implement methods for
  both min-length and max-length."
  [node]
  (not (= (min-length node) (max-length node))))

(defmulti ground?
  "true if node is ground i.e. it contains no variables or is not a
  match operator."
  {:arglists '([node])}
  #'tag)

(s/fdef search?
  :args (s/cat :node :meander.syntax.epsilon/node)
  :ret boolean?)

(defmulti search?
  "true if node represents a search, false otherwise."
  {:arglists '([node])}
  #'tag)

(defmulti unparse
  "In pre-order fashion rewrite a node into a Clojure form."
  {:arglists '([node])}
  #'tag)

(defn fold
  "Same as clojure.core/reduce but specifically for ASTs. f must be a
  binary (arity 2) function and will receive as arguments the current
  accumulated value and an AST node. fold is eager and will visit each
  subnode in node."
  [f value node]
  (reduce f value (subnodes node)))

(defn variables*
  {:private true}
  [node]
  (fold
   (fn [vars node]
     (let [tag (tag node)]
       (case tag
         (:lvr :mut :mvr :ref)
         (update vars tag conj node)

         :wth
         (transduce (comp (map :pattern)
                          (map variables*))
                    (fn
                      ([a] a)
                      ([a b]
                       (merge-with set/union a b)))
                    vars
                    (:bindings node))
         
         ;; else
         vars)))
   {:lvr #{}
    :mut #{}
    :mvr #{}
    :ref #{}}
   node))

(defn variables
  "Return all :lvr and :mvr nodes in node."
  [node]
  (s/assert :meander.syntax.epsilon/node node)
  (let [vars (variables* node)]
    (set/union (:lvr vars) (:mvr vars))))

(defn memory-variables
  "Return all :mvr nodes in node."
  [node]
  (s/assert :meander.syntax.epsilon/node node)
  (:mvr (variables* node)))

(defn logic-variables
  "Return all :lvr nodes in node."
  [node]
  (s/assert :meander.syntax.epsilon/node node)
  (:lvr (variables* node)))


(s/fdef references
  :args (s/cat :node :meander.syntax.epsilon/node)
  :ret (s/coll-of :meander.syntax.epsilon.node/ref
                  :kind set?
                  :into #{}))

(defn references
  "Return all :ref nodes in node."
  [node]
  (:ref (variables* node)))

(defn top-level
  [node]
  (case (tag node)
    (:cnj :dsj :wth)
    (mapcat top-level (children node))
    ;; else
    [node]))

;; ---------------------------------------------------------------------
;; Parse implementation

(declare parse)

(defn parse-all
  {:private true}
  [xs env]
  (map (fn [x] (parse x env)) xs))

(defn expand-prt
  {:private true}
  [xs]
  (let [[l r] (split-with
               (fn [{tag :tag}]
                 (case tag
                   (:dot :dt+ :dt* :dtl :dtm)
                   false
                   ;; else
                   true))
               xs)]
    (if (seq l)
      (let [node (first r)]
        {:tag :prt
         :left (case (:tag node)
                 :dt*
                 (let [c (bounded-count 2 l)]
                   (cond
                     ;; _ ...
                     (and (= c 1)
                          (= :any (:tag (first l))))
                     {:tag :drp
                      :symbol (:symbol (first l))}

                     ;; !xs ...
                     (and (= c 1)
                          (= :mvr (:tag (first l))))
                     {:tag :rst
                      :mvr (first l)}

                     ;; a b ...
                     :else
                     {:tag :rp*
                      :cat {:tag :cat
                            :elements l}}))

                 ;; a b ..<nat-int>
                 :dt+
                 {:tag :rp+
                  :cat {:tag :cat
                        :elements l}
                  :n (:n node)}

                 ;; ab ..?<name>
                 :dtl
                 {:tag :rpl
                  :cat {:tag :cat
                        :elements l}
                  :lvr (:lvr node)}

                 ;; a b ..!<name>
                 :dtm
                 {:tag :rpm
                  :cat {:tag :cat
                        :elements l}
                  :mvr (:mvr node)}

                 (nil :dot)
                 {:tag :cat
                  :elements l}) 
         :right (expand-prt (next r))})
      (if (seq r)
        (let [node (first r)]
          {:tag :prt
           :left (case (:tag node)
                   :dt*
                   {:tag :rp*
                    :cat {:tag :cat
                          :elements l}}

                   :dt+
                   {:tag :rp+
                    :cat {:tag :cat
                          :elements l}
                    :n (:n node)}

                   (nil :dot)
                   {:tag :cat
                    :elements l}) 
           :right (expand-prt (next r))})
        {:tag :cat
         :elements []}))))

(defn expand-symbol
  {:private true}
  [sym env]
  #?(:clj (if-some [cljs-ns (:ns env)]
            ;; ClojureScript
            (if (qualified-symbol? sym)
              (let [ns-sym (symbol (namespace sym))]
                (if-some [ns (get (:requires cljs-ns) ns-sym)]
                  (symbol (name ns) (name sym))
                  sym))
              (if (contains? (:defs cljs-ns) sym)
                (symbol (name (:name cljs-ns)) (name sym))
                sym))
            ;; Clojure
            (if (qualified-symbol? sym)
              (let [ns-sym (symbol (namespace sym))]
                (if-some [ns (get (ns-aliases *ns*) ns-sym)]
                  (symbol (name (ns-name ns)) (name sym))
                  sym))
              (symbol (name (ns-name *ns*)) (name sym))))
     :cljs sym))

(defmulti expand-seq
  {:arglists '([seq env])}
  (fn [xs env]
    (if (seq? xs)
      (let [x (first xs)]
        (if (symbol? x)
          (expand-symbol x env)
          ::default))
      ::default)))

(defmethod expand-seq :default
  [xs env]
  xs)

(defn parse-scan
  {:private true}
  [xs env]
  (if (and (seq? xs)
           (= (first xs) 'scan))
    (let [nothing (gensym)]
      (if (identical? (nth xs 1 nothing) nothing)
        (throw (ex-info "scan expects at least one argument"
                        {:pattern xs
                         :meta (meta xs)}))
        (parse
         `(~'pred coll?
           ;; Will cause compiler to emit a useless seq? check.
           (~'app seq (~@'(_ ...) ~@(rest xs) ~@'(. _ ...))))
         env)))
    (parse xs env)))

(defn parse-vscan
  {:private true}
  [xs env]
  (if (and (seq? xs)
           (= (first xs) 'vscan))
    (let [nothing (gensym)]
      (if (identical? (nth xs 1 nothing) nothing)
        (throw (ex-info "vscan expects at least one argument"
                        {:pattern xs
                         :meta (meta xs)}))
        (parse
         `(~'pred coll?
           ;; Will cause compiler to emit a useless vector?
           ;; check.
           (~'app vec [~@'(_ ...) ~@(rest xs) ~@'(. _ ...)]))
         env)))
    (parse xs env)))

(defn parse-contain
  {:private true}
  [xs env]
  (if (and (seq? xs) (= (first xs) '$))
    (case (long (bounded-count 2 (rest xs)))
      1
      {:tag :ctn
       :pattern (parse (nth xs 1) env)}

      2
      {:tag :ctn
       ;; Should be an :lvr or :mvr.
       :context (parse (nth xs 1) env)
       :pattern (parse (nth xs 2) env)}
      ;; else
      (throw (ex-info "$ expects one or two arguments"
                      {:form xs
                       :meta (meta xs)})))
    (parse xs env)))

(defn parse-with
  {:private true}
  [xs env]
  (if (and (seq? xs) (= 'with (first xs)))
    (let [bindings (nth xs 1)]
      (if (and (vector? bindings)
               (even? (count bindings))
               (every? ref-sym? (take-nth 2 bindings)))
        {:tag :wth
         :bindings (map
                    (fn [[ref-sym x]]
                      {:ref {:tag :ref
                             :symbol ref-sym}
                       :pattern (parse x env)})
                    (partition 2 bindings))
         :body (let [nothing (gensym)
                     x (nth xs 2 nothing)]
                 (if (identical? x nothing)
                   nil
                   (parse x env)))}
        (throw
         (ex-info "second argument to with must be vector of the form [%ref-name pattern ...]"
                  {:form xs
                   :meta (meta xs)}))))
    (parse xs env)))

(defn parse-seq-no-head
  {:private true}
  [xs env]
  (let [c (count xs)
        as-index (- c 2)]
    (if (and (<= 2 c)
             (= (nth xs as-index) :as))
      (let [xs* (take as-index xs)
            as-pattern (last xs)
            as-node (parse as-pattern env)]
        (case (:tag as-node)
          (:lvr :mvr)
          {:tag :seq
           :as as-node
           :prt (expand-prt (parse-all xs* env))}
          ;; else
          (throw (ex-info "Seq :as pattern must be a logic variable or memory variable"
                          {:form xs
                           :meta (meta xs)}))))
      {:tag :seq
       :prt (expand-prt (parse-all xs env))})))

(defn parse-seq
  "Parses a seq? into a :meander.syntax.epsilon/node.

  seqs? of the following form are handled specially, all other seqs
  are parsed as :seq nodes.

    ($ <pattern>)
    ($ ?<context-name> <pattern>)
    (and <pattern_0> ... <pattern_n>)
    (app <expr> <pattern> ...)
    (guard <expr>)
    (let <pattern_0> <expr_0> ... <pattern_n> <expr_n>)
    (not <pattern>)
    (or <pattern_0> ... <pattern_n>)
    (pred <expr> <pattern_0> ... <pattern_n>)
    (quote <form>)
    (re <regex-expr>)
    (re <regex-expr> <pattern>)
    (with [%<simple-symbol> <pattern> ...] <pattern>)
    (clojure.core/unquote <form>) 
    (clojure.core/unquote-splicig <form>)
    (<symbol*> <form_0> ... <form_n>)

  where symbol* is a fully qualified symbol with respect to the
  current namespace."
  {:private true}
  [xs env]
  (let [x (first xs)]
    (if (symbol? x)
      (case x
        $
        (parse-contain xs env)
        
        and
        {:tag :cnj
         :arguments (parse-all (rest xs) env)}

        app
        {:tag :app
         :fn-expr (second xs)
         :arguments (parse-all (nnext xs) env)}

        guard
        {:tag :grd
         :expr (second xs)}

        let
        (let [xs* (rest xs)]
          (if (odd? (count xs*))
            (throw (ex-info "let pattern requires an even number of arguments"
                            {:pattern xs
                             :meta (meta xs)}))
            {:tag :let
             :bindings (map
                        (fn [[pattern expr]]
                          {:binding (parse pattern env)
                           :expr expr})
                        (partition-all 2 xs*))}))

        not
        (if (= 1 (bounded-count 2 (drop 1 xs)))
          {:tag :not
           :argument (parse (second xs) env)}
          (throw (ex-info "not pattern requires at one argument"
                          {:pattern xs
                           :meta (meta xs)})))

        or
        {:tag :dsj
         :arguments (parse-all (rest xs) env)}

        pred
        {:tag :prd
         :form (second xs)
         :arguments (parse-all (nnext xs) env)}

        quote 
        {:tag :quo
         :form (second xs)}

        re
        (let [nothing (gensym)
              regex (nth xs 1 nothing)]
          (if (identical? regex nothing)
            (throw (ex-info "re pattern expects at least one argument"
                            {:pattern xs
                             :meta (meta xs)}))
            (let [capture (nth xs 2 nothing)]
              (if (identical? capture nothing)
                {:tag :rxt
                 :regex regex}
                {:tag :rxc
                 :regex regex
                 :capture (parse capture env)}))))

        scan
        (parse-scan xs env) 

        vscan
        (parse-vscan xs env)

        with
        (parse-with xs env)

        clojure.core/unquote
        {:tag :unq
         :expr (second xs)}

        clojure.core/unquote-splicing
        {:tag :uns
         :expr (second xs)}

        ;; else
        (let [xs* (expand-seq xs env)]
          (if (= xs* xs)
            (parse-seq-no-head xs env)
            (parse xs* env))))
      (parse-seq-no-head xs env))))

(defn parse-symbol
  {:private true}
  [sym]
  (if (namespace sym)
    {:tag :lit
     :value sym}
    (let [s (name sym)
          [$0 $N $L $M] (re-matches #"\.(?:\.(?:\.|(\d+)|(\?.+)|(!.+))?)?" s)]
      (cond
        ;; `..<nat-int>`
        (some? $N)
        (if (= $N "0")
          ;; `..0` is the same as `...`.
          {:tag :dt*}
          ;; Inteneral tag for postfix n or more operator.
          {:tag :dt+
           :n (r.util/parse-int $N)})

        ;; `..?<name>`
        (some? $L)
        ;; Internal tag for postfix ?n or more operator.
        {:tag :dtl
         :lvr {:tag :lvr
               :symbol (symbol $L)}}

        (some? $M)
        ;; Internal tag for postfix !n or more operator.
        {:tag :dtm
         :mvr {:tag :mvr
               :symbol (symbol $M)}}

        :else
        (case $0
          ;; Internal tag for postfix partition.
          "."
          {:tag :dot}

          ;; Internal tag for postfix n or more operator.
          ".."
          {:tag :dt+
           :n $N}

          ;; Internal tag for postfix 0 or more operator.
          "..."
          {:tag :dt*}

          nil
          (cond
            (r.util/re-matches? #"^_.*" s)
            {:tag :any
             :symbol sym}

            (r.util/re-matches? #"^\?.+" s)
            {:tag :lvr
             :symbol sym}

            (r.util/re-matches? #"^!.+" s)
            {:tag :mvr
             :symbol sym}

            (r.util/re-matches? #"^%.+" s)
            {:tag :ref
             :symbol sym}

            (r.util/re-matches? #"^\*.+" s)
            {:tag :mut
             :symbol sym}

            :else
            {:tag :lit
             :value sym}))))))

(defn parse-js-value
  {:private true}
  [^JSValue js-value env]
  (let [x (.val js-value)]
    (cond
      (vector? x)
      {:tag :jsa
       :prt (expand-prt (parse-all x env))}

      (map? x)
      {:tag :jso
       :object (into {}
                     (map
                      (fn [[k v]]
                        (let [k* (if (keyword? k)
                                   (subs (str k) 1)
                                   k)]
                          [(parse k*) (parse v)])))
                     x)})))

(defn parse-vector
  {:private true}
  [v env]
  (if (vector? v)
    (let [c (count v)
          as-index (- c 2)]
      (if (and (<= 2 c)
               (= (nth v as-index) :as))
        (let [v* (subvec v 0 as-index)
              [_ as-pattern] (subvec v as-index)
              as-node (parse as-pattern env)]
          (case (:tag as-node)
            (:lvr :mvr)
            {:tag :vec
             :as as-node
             :prt (expand-prt (parse-all v* env))}
            ;; else
            (throw (ex-info "Vector :as pattern must be a logic variable or memory variable"
                            {:form v
                             :meta (meta v)}))))
        {:tag :vec
         :prt (expand-prt (parse-all v env))}))
    (parse v env)))

(defn parse-map
  {:private true}
  [m env]
  (if (and (map? m)
           (not (record? m)))
    (let [as (if-some [[_ y] (find m :as)]
               (if (or (logic-variable-form? y)
                       (memory-variable-form? y))
                 (parse y env)))
          m (if (some? as)
              (dissoc m :as)
              m)
          rest-map (if-some [[_ y] (find m '&)]
                     (parse y env))
          m (if (some? rest-map)
              (dissoc m '&)
              m)]
      {:tag :map
       :as as
       :rest-map rest-map
       :map (into {}
                  (map
                   (fn [[k v]]
                     [(parse k env) (parse v env)]))
                  m)})
    (parse m env)))

(defn parse-set [s env]
  (if (set? s)
    (let [as-form (some
                   (fn [x]
                     (when (= (:as (meta x)) true)
                       x))
                   s)
          s (if (some? as-form)
              (disj s as-form)
              s)
          rest-form (some
                     (fn [x]
                       (when (= (:tag (meta x)) '&)
                         x))
                     s)
          s (if (some? rest-form)
              (disj s rest-form)
              s)]
      {:tag :set
       :as (if (some? as-form)
             (parse as-form env))
       :rest (if (some? rest-form)
               (parse rest-form env))
       :elements (parse-all s env)})
    (parse s env)))


(s/fdef parse
  :args (s/alt :a1 (s/cat :x any?)
               :a2 (s/cat :x any? :env map?))
  :ret :meander.syntax.epsilon/node)

(defn parse
  "Parse `x` into an abstract syntax tree (AST) optionally with
  respect to the environment `env`.

  (parse '(?x1 ?x2 :as ?xs))
  ;; =>
  {:tag :seq
   :as {:tag :lvr
        :symbol ?xs}
   :prt {:tag :prt
         :left {:tag :cat
                :elements ({:tag :lvr :symbol ?x1}
                           {:tag :lvr :symbol ?x2})}
         :right {:tag :cat
                 :elements []}}}"
  ([x]
   (parse x {}))
  ([x env]
   (let [node (cond
                (seq? x)
                (parse-seq x env)
                
                (vector? x)
                (parse-vector x env) 

                (and (map? x)
                     (not (record? x)))
                (parse-map x env)
                
                (set? x)
                (parse-set x env) 

                (symbol? x)
                (parse-symbol x)

                #?@(:clj [(instance? JSValue x)
                          (parse-js-value x env)])

                :else
                {:tag :lit
                 :value x})]
     (if-some [meta (meta x)]
       (with-meta node meta)
       node))))


;; ---------------------------------------------------------------------
;; AST method implementations

;; :any

(defmethod ground? :any [_]
  false)

(defmethod unparse :any [_]
  '_)

(defmethod search? :any [_]
  false)

;; :app

(defmethod children :app [node]
  (:arguments node))

(defmethod ground? :app [_]
  false)

(defmethod unparse :app [node]
  `(~'app ~(:fn-exp node) ~@(map unparse (:arguments node))))

(defmethod search? :app
  [_] false)

;; :cat

(defmethod ground? :cat [node]
  (every? ground? (:elements node)))

(defmethod children :cat [node]
  (:elements node))

(defmethod min-length :cat [node]
  (count (:elements node)))

(defmethod max-length :cat [node]
  (count (:elements node)))

(defmethod unparse :cat [node]
  (apply list (map unparse (:elements node))))

(defmethod search? :cat [node]
  (boolean (some search? (:elements node))))

;; :cnj

(defmethod children :cnj [node]
  (:arguments node))

(defmethod ground? :cnj [_]
  false)

(defmethod unparse :cnj [node]
  `(~'and ~@(sequence (map unparse) (:arguments node))))

(defmethod search? :cnj
  [node]
  (boolean (some search? (:arguments node))))

;; :ctn

(defmethod children :ctn [node]
  (if-some [[_ pattern] (find node :pattern)]
    [pattern]
    []))

(defmethod ground? :ctn [_]
  false)

(defmethod unparse :ctn [node]
  `(~'$
    ~@(if-some [[_ context] (find node :context)]
        [(unparse context)])
    ~@(if-some [[_ pattern] (find node :pattern)]
        [(unparse pattern)])))

(defmethod search? :ctn [_]
  true)

;; :drp

(defmethod ground? :drp [_]
  false)

(defmethod min-length :drp [_]
  0)

(defmethod max-length :drp [_]
  ##Inf)

(defmethod unparse :drp [node]
  (list (:symbol node) '...))

(defmethod search? :drp [_]
  false)

;; :dsj

(defmethod children :dsj [node]
  (:arguments node))

(defmethod ground? :dsj [_]
  false)

(defmethod unparse :dsj [node]
  `(~'or ~@(sequence (map unparse) (:arguments node))))

(defmethod search? :dsj [node]
  (boolean (some search? (:arguments node))))

;; :grd

(defmethod ground? :grd [_]
  false)

(defmethod unparse :grd [node]
  `(~'guard ~(:expr node)))

(defmethod search? :grd [_]
  false)

;; :jsa

(defmethod children :jsa [node]
  [(:prt node)])

(defmethod ground? :jsa [node]
  (ground? (:prt node)))

(defmethod min-length :jsa [node]
  (min-length (:prt node)))

(defmethod max-length :jsa [node]
  (max-length (:prt node)))

(defmethod unparse :jsa [node]
  #?(:clj
     (JSValue. (vec (unparse (:prt node))))
     :cljs
     (into-array (unparse (:prt node)))))

(defmethod search? :jsa [node]
  (search? (:prt node)))

;; :jso

(defmethod children :jso [node]
  (mapcat identity (:object node)))

(defmethod ground? :jso [node]
  (every?
   (fn [[k v]]
     (and (ground? k)
          (ground? v)))
   (:object node)))

(defmethod unparse :jso [node]
  #?(:clj
     (JSValue. (reduce-kv
                (fn [m k v]
                  (assoc m (unparse k) (unparse v)))
                {}
                (:object node)))
     :cljs
     (reduce-kv
      (fn [obj [k v]]
        (doto obj
          (goog.object/set (unparse k) (unparse v))))
      #js {}
      (:object node))))

(defmethod search? :jso [node]
  (boolean
   (some
    (fn [[k v]]
      (or (not (ground? k))
          (search? k)
          (search? v)))
    (:object node))))


;; :let

(defmethod children :let [node]
  (map :binding (:bindings node)))

(defmethod ground? :let [_]
  false)

(defmethod unparse :let [node]
  `(~'let ~@(sequence (mapcat (juxt (comp unparse :binding) :expr)) (:bindings node))))

(defmethod search? :let [_]
  false)

;; :lit

(defmethod ground? :lit [_]
  true)

(defn unparse-lit
  {:private true}
  [x]
  (cond
    (symbol? x)
    `(quote ~x)

    (seq? x)
    (if (= (first x) 'quote)
      x
      (if (= (first x) `list)
        (cons (first x) (map unparse-lit (rest x)))
        (if (seq x) 
          (cons `list (map unparse-lit x))
          ())))

    (map? x)
    (into {}
          (map
           (fn [[k v]]
             [(unparse-lit k) (unparse-lit v)]))
          x)

    (coll? x)
    (into (empty x) (map unparse-lit) x)

    :else
    x))

(defmethod unparse :lit [node]
  (unparse-lit (:value node)))

(defmethod search? :lit [_]
  false)

;; :lvr

(defmethod ground? :lvr [_]
  false)

(defmethod unparse :lvr [node]
  (:symbol node))

(defmethod search? :lvr [_]
  false)

;; :map

(defmethod children :map [node]
  (concat (mapcat identity (:map node))
          (if-some [rest-map (:rest-map node)]
            (let [xs (children rest-map)]
              (if (seq xs)
                xs
                [rest-map])))
          (if-some [as (:as node)]
            (let [xs (children as)]
              (if (seq xs)
                xs
                [as])))))


(defmethod ground? :map [node]
  (every?
   (fn [[k v]]
     (and (ground? k)
          (ground? v)))
   (:map node)))

(defmethod unparse :map [node]
  (cond-> (reduce-kv
           (fn [m k v]
             (assoc m (unparse k) (unparse v)))
           {}
           (:map node))
    (some? (:as node))
    (assoc :as (:as node))

    (some? (get node '&))
    (assoc '& (unparse (get node '&)))))

(defmethod search? :map [node]
  (boolean
   (some
    (fn [[k v]]
      (or (not (ground? k))
          (search? k)
          (search? v)))
    (:map node))))

;; :mut

(defmethod ground? :mut [_]
  false)

(defmethod unparse :mut [node]
  (:symbol node))

(defmethod search? :mut [_]
  false)

;; :mvr

(defmethod ground? :mvr [_]
  false)

(defmethod unparse :mvr [node]
  (:symbol node))

(defmethod search? :mvr [_]
  false)

;; :not

(defmethod children :not [node]
  [(:argument node)])

(defmethod ground? :not [_]
  false)

(defmethod unparse :not [node]
  `(~'not ~(unparse (:argument node))))

(defmethod search? :not [node]
  (search? (:argument node)))

;; :prd

(defmethod children :prd [node]
  (:arguments node))

(defmethod ground? :prd [_]
  false)

(defmethod unparse :prd [node]
  `(~'pred ~(:form node) ~@(map unparse (:arguments node))))

(defmethod search? :prd [node]
  (boolean (some search? (:arguments node))))

;; :prt

(defmethod children :prt [node]
  [(:left node) (:right node)])

(defmethod ground? :prt [node]
  (and (ground? (:left node))
       (ground? (:right node))))

(defmethod min-length :prt [node]
  (+ (min-length (:left node))
     (min-length (:right node))))

(defmethod max-length :prt [node]
  (+ (max-length (:left node))
     (max-length (:right node))))

(defmethod unparse :prt [node]
  `(~@(unparse (:left node))
    ~@(when-some [right (seq (unparse (:right node)))]
        `(~'. ~@right))))

;; This is not really a good definition. While it is true that finding
;; solutions for a series variable length subsequence patterns
;; would require searching, it does not imply there is more than one
;; solution. For example, the pattern
;;
;;   [1 2 ... 3 4 ...]
;;
;; can only have one solution. Therefore, for patterns such as these
;; this method should return false.
(defmethod search? :prt
  [{left :left, right :right}]
  (or (and (variable-length? left)
           (variable-length? right))
      (search? left)
      (search? right)))

;; :quo

(defmethod ground? :quo [_]
  true)

(defmethod unparse :quo [node]
  `(quote ~(:form node)))


(defmethod search? :quo [_]
  false)

;; :ref

(defmethod children :ref [node]
  [])

(defmethod ground? :ref [_]
  false)

(defmethod unparse :ref [node]
  (:symbol node))

(defmethod search? :ref [_]
  false)

;; :rp*

(defmethod children :rp* [node]
  [(:cat node)])

(defmethod ground? :rp* [_]
  false)

(defmethod min-length :rp* [_]
  0)

(defmethod max-length :rp* [_]
  ##Inf)

(defmethod unparse :rp* [node]
  `(~@(unparse (:cat node)) ~'...))

(defmethod search? :rp* [_]
  false)

;; :rp+

(defmethod children :rp+ [node]
  [(:cat node)])

(defmethod ground? :rp+ [_]
  false)

(defmethod min-length :rp+ [node]
  (let [n (:n node)]
    (if (integer? n)
      (* n (min-length (:cat node)))
      0)))

(defmethod max-length :rp+ [_]
  ##Inf)

(defmethod unparse :rp+ [node]
  (let [dots (if-some [n (:n node)]
               (symbol (str ".." n))
               '..)]
    `(~@(unparse (:cat node)) ~dots)))

(defmethod search? :rp+ [_]
  false)

;; :rpl

(defmethod children :rpl [node]
  [(:cat node) (:lvr node)])

(defmethod ground? :rpl [_]
  false)

(defmethod min-length :rpl [node]
  0)

(defmethod max-length :rpl [_]
  ##Inf)

(defmethod unparse :rpl [node]
  (let [dots (symbol (str ".." (unparse (:lvr node))))]
    `(~@(unparse (:cat node)) ~dots)))

(defmethod search? :rpl [_]
  false)

;; :rpm

(defmethod children :rpm [node]
  [(:cat node) (:mvr node)])

(defmethod ground? :rpm [_]
  false)

(defmethod min-length :rpm [node]
  0)

(defmethod max-length :rpm [_]
  ##Inf)

(defmethod unparse :rpm [node]
  (let [dots (symbol (str ".." (unparse (:mvr node))))]
    `(~@(unparse (:cat node)) ~dots)))

(defmethod search? :rpm [_]
  false)

;; :rst

(defmethod children :rst [node]
  [(:mvr node)])

(defmethod ground? :rst [_]
  false)

(defmethod min-length :rst [_]
  0)

(defmethod max-length :rst [_]
  ##Inf)

(defmethod unparse :rst [node]
  (list (unparse (:mvr node)) '...))

(defmethod search? :rst [_]
  false)

;; :rxc

(defmethod children :rxc [node]
  [(:capture node)])

(defmethod ground? :rxc [node]
  (ground? (:capture node)))

(defmethod search? :rxc [node]
  (search? (:capture node)))

(defmethod unparse :rxc [node]
  (list 're (unparse (:regex node)) (unparse (:capture node))))

;; :rxt

(defmethod ground? :rxt [node]
  true)

(defmethod unparse :rxt [node]
  (list 're (unparse (:regex node))))

;; :set

(defmethod children :set [node]
  (if-some [rest-node (:rest node)]
    (concat (:elements node)
            (if-some [rest-set (:rest node)]
              (let [xs (children rest-set)]
                (if (seq xs)
                  xs
                  [rest-set])))
            (if-some [as (:as node)]
              (let [xs (children as)]
                (if (seq xs)
                  xs
                  [as]))))
    (:elements node)))

(defmethod ground? :set [node]
  (every? ground? (:elements node)))

(defmethod unparse :set [node]
  (cond-> (set (map unparse (:elements node)))
    (some? (:as node))
    (conj (vary-meta (unparse (:as node)) assoc :as true))

    (some? (:rest node))
    (conj (vary-meta (unparse (:as node)) assoc :tag '&))))

(defmethod search? :set [node]
  (not (ground? node)))

;; seq

(defmethod children :seq [node]
  [(:prt node)])

(defmethod ground? :seq [node]
  (ground? (:prt node)))

(defmethod unparse :seq [node]
  (seq (unparse (:prt node))))

(defmethod search? :seq [node]
  (search? (:prt node)))

(defmethod min-length :seq
  [node]
  (min-length (:prt node)))

(defmethod max-length :seq [node]
  (max-length (:prt node)))

;; :unq

(defmethod ground? :unq [_]
  true)

(defmethod unparse :unq [node]
  (list 'clojure.core/unquote (:expr node)))

(defmethod search? :unq [_]
  false)

;; :uns

(defmethod ground? :uns [_]
  false)

(defmethod unparse :uns [node]
  (list 'clojure.core/unquote-splicing (:expr node)))

(defmethod search? :uns [_]
  false)

;; :vec

(defmethod children :vec [node]
  [(:prt node)])

(defmethod ground? :vec [node]
  (ground? (:prt node)))

(defmethod min-length :vec [node]
  (min-length (:prt node)))

(defmethod max-length :vec [node]
  (max-length (:prt node)))

(defmethod unparse :vec [node]
  (cond-> (vec (unparse (:prt node)))
    (some? (:as node))
    (conj :as (unparse (:as node)))))

(defmethod search? :vec [node]
  (search? (:prt node)))

;; wth

(defmethod children :wth [node]
  [(:body node)])

(defmethod ground? :wth [node]
  (ground? (:body node)))

(defmethod unparse :wth [node]
  `(~'with [~@(mapcat
               (juxt
                (comp unparse :ref)
                (comp unparse :pattern))
               (:bindings node))]
    ~@(when-some [body (:body node)]
        [(unparse body)])))

(defmethod search? :wth [node]
  ;; Come back to to this.
  true)

;; ---------------------------------------------------------------------
;; walk

(defmulti walk
  "Same as clojure.walk/walk but for AST nodes."
  {:arglists '([inner outer node])}
  (fn [_ _ node]
    (tag node))
  :default ::default)

(defmethod walk ::default
  [inner outer x]
  (if (node? x)
    (outer x)
    x))

(defn postwalk
  [f node]
  (walk (fn [x]
          (let [y (f x)]
            (if (reduced? y)
              (deref y)
              (postwalk f y))))
        f
        node))

(defn prewalk
  [f node]
  (let [x (f node)]
    (if (reduced? x)
      (deref x)
      (walk (partial prewalk f) identity x))))

(defn prewalk-replace
  "Same as clojure.walk/prewalk-replace but for AST nodes."
  [smap form]
  (prewalk (fn [x] (if (contains? smap x) (smap x) x)) form))

(defn postwalk-replace
  "Same as clojure.walk/postwal-replace but for AST nodes."
  [smap form] (postwalk (fn [x] (if (contains? smap x) (smap x) x)) form))

(defmethod walk :app [inner outer node]
  (outer (assoc node :arguments (mapv inner (:arguments node)))))

(defmethod walk :cat [inner outer node]
  (outer (assoc node :elements (mapv inner (:elements node)))))

(defmethod walk :cnj [inner outer node]
  (outer (assoc node :arguments (mapv inner (:arguments node)))))

(defmethod walk :ctn [inner outer node]
  (outer (assoc node :pattern (inner (:pattern node)))))

(defmethod walk :dsj [inner outer node]
  (outer (assoc node :arguments (mapv inner (:arguments node)))))

(defmethod walk :jsa [inner outer node]
  (outer (assoc node :prt (inner (:prt node)))))

(defmethod walk :jso [inner outer node]
  (outer (assoc node :object (reduce
                              (fn [m [k-node v-node]]
                                (assoc m (inner k-node) (inner v-node)))
                              {}
                              (:object node)))))

(defmethod walk :let [inner outer node]
  (outer (assoc node :bindings (mapv
                                (fn [binding]
                                  (assoc binding :binding (inner (:binding binding))))
                                (:bindings node)))))

(defmethod walk :map [inner outer node]
  (outer (assoc node
                :rest-map (if-some [rest-map (:rest-map node)]
                            (inner rest-map))
                :map (reduce
                      (fn [m [k-node v-node]]
                        (assoc m (inner k-node) (inner v-node)))
                      {}
                      (:map node)))))

(defmethod walk :not [inner outer node]
  (outer (assoc node :argument (inner (:argument node)))))

(defmethod walk :prd [inner outer node]
  (outer (assoc node :arguments (mapv inner (:arguments node)))))

(defmethod walk :prt [inner outer node]
  (outer (assoc node
                :left (inner (:left node))
                :right (inner (:right node)))))

(defmethod walk :rp* [inner outer node]
  (outer (assoc node :cat (inner (:cat node)))))

(defmethod walk :rp+ [inner outer node]
  (outer (assoc node :cat (inner (:cat node)))))

(defmethod walk :rxc [inner outer node]
  (outer (assoc node :capture (inner (:capture node)))))

(defmethod walk :set [inner outer node]
  (outer (assoc node
                :rest (if-some [rest-set (:rest node)]
                        (inner rest-set))
                :elements (mapv inner (:elements node)))))

(defmethod walk :seq [inner outer node]
  (outer (assoc node :prt (inner (:prt node)))))

(defmethod walk :vec [inner outer node]
  (outer (assoc node :prt (inner (:prt node)))))

(defmethod walk :wth [inner outer node]
  (outer (assoc node
                :bindings (mapv
                           (fn [binding]
                             (assoc binding :pattern (inner (:pattern binding))))
                           (:bindings node))
                :body (inner (:body node)))))

;; ---------------------------------------------------------------------
;; defsyntax


(s/def :meander.syntax.epsilon/defsyntax-args
  (s/cat :name simple-symbol?
         :docstring (s/? string?)
         :meta (s/? map?)
         :arglist (s/coll-of simple-symbol? :kind vector?)
         :body (s/* any?)))

(defmacro defsyntax
  "EXPERIMENTAL Like defn but for defining new pattern syntax by
  extending the parser. When parsing, if a seq is encountered starting
  with a symbol which can be resolved to the created var then the tail
  of the seq is applied to the var and the result is parsed.

  Example

  (defsyntax re [regex]
    `(~'pred
      (fn [s#]
        (and (string? s#)
             (re-matches ~regex s#)))))

  (require '[meander.match.epsilon :as r.match])

  (r.match/match \"elf\"
    (re #\"[a-z]+\")
    :okay!)
  ;; => :okay
  "
  {:arglists '([name docstring? meta? arglist & body])}
  [& args]
  (let [data (s/conform :meander.syntax.epsilon/defsyntax-args args)]
    (if (identical? data ::s/invalid)
      nil
      (let [sym (get data :name)
            q-sym (symbol (name (ns-name *ns*))
                          (name sym))
            arglist (get data :arglist)
            body (get data :body)
            name-key (keyword (name (gensym "name__")))]
        ;; When defining new syntax in ClojureScript it is also
        ;; necessary to define the methods which parse and expand the
        ;; syntax in Clojure. This is because the match, search, and
        ;; find macros (in meander.match.epsilon) are expanded in
        ;; Clojure which, in turn, rely on these methods.
        #?(:clj
           (when-some [cljs-ns (:ns &env)]
             ;; Visit the namespace.
             (in-ns (:name cljs-ns))
             ;; Try to require the namespace or everything in
             ;; :requires. Both operations can fail.
             (try
               (require (:name cljs-ns))
               (catch Exception _
                 (doseq [[alias ns-name] (:requires cljs-ns)]
                   (if (= alias ns-name)
                     (require ns-name)
                     (require [ns-name :as alias])))))
             (eval
              `(do
                 (defn ~sym
                   ~@(when-some [docstring (get data :docstring)]
                       [docstring])
                   ~@(when-some [meta (get data :meta)]
                       [meta])
                   ~arglist
                   ~@body)

                 (defmethod meander.syntax.epsilon/expand-seq '~q-sym [[_# ~@arglist] _#]
                   (~sym ~@arglist))))
             (in-ns 'meander.syntax.epsilon)))
        `(do
           (defn ~sym
             ~@(when-some [docstring (get data :docstring)]
                 [docstring])
             ~@(when-some [meta (get data :meta)]
                 [meta])
             ~arglist
             ~@body)

           (defmethod expand-seq '~q-sym [[_# ~@arglist] _#]
             (~sym ~@arglist))

           (var ~q-sym))))))

;; ---------------------------------------------------------------------
;; Other useful utilities

(defn genref
  {:private true}
  []
  {:tag :ref
   :symbol (gensym "%r__")})

(defn ref-smap
  {:private true}
  [with-node]
  (into {} (map
            (fn [binding]
              [(:ref binding) (genref)]))
        (:bindings with-node)))

(defn rename-refs
  "Give all distinct :ref nodes a unique :symbol."
  [node]
  (postwalk
   (fn [node]
     (if (with-node? node)
       (let [ref-smap (ref-smap node)]
         (assoc node
                :bindings (map
                           (fn [binding]
                             {:ref (get ref-smap (:ref binding))
                              :pattern (postwalk-replace ref-smap (:pattern binding))})
                           (:bindings node))
                :body (postwalk-replace ref-smap (:body node))))
       node))
   node))

(defn consolidate-with
  "Collapse all :wth nodes into a single :wth node."
  [node]
  (let [state (volatile! [])
        node (prewalk
              (fn f [node]
                (if (with-node? node)
                  (do (vswap! state into (:bindings node))
                      (:body node))
                  node))
              node)]
    {:tag :wth
     :bindings (deref state)
     :body node}))


(s/def :meander.syntax.epsilon/ref-map
  (s/map-of :meander.syntax.epsilon.node/ref
            :meander.syntax.epsilon/node))

(s/fdef make-ref-map
  :args (s/cat :node :meander.syntax.epsilon/node)
  :ret :meander.syntax.epsilon/ref-map)

(defn make-ref-map
  "If node is a node repesenting a with pattern, return a map from
  reference to pattern node derived from it's bindings, otherwise
  return an empty map."
  [node]
  (if (with-node? node)
    (into {} (map (juxt :ref :pattern)) (:bindings node))
    {}))

(s/fdef substitute-refs
  :args (s/alt :a1 (s/cat :node :meander.syntax.epsilon/node)
               :a2 (s/cat :node :meander.syntax.epsilon/node
                          :ref-map :meander.syntax.epsilon/ref-map))
  :ret :meander.syntax.epsilon/node)

(defn substitute-refs
  "Given node and an optional ref-map "
  ([node]
   (substitute-refs node {}))
  ([node ref-map]
   (prewalk
    (fn f [node]
      (cond
        (ref-node? node)
        (reduced
         (if-some [other-node (get ref-map node)]
           (substitute-refs other-node (dissoc ref-map node))
           node))

        (with-node? node)
        (reduced
         (if-some [body (:body node)]
           (let [ref-map (reduce
                          (fn [ref-map [k v]]
                            (if (contains? ref-map k)
                              ref-map
                              (assoc ref-map k v)))
                          (make-ref-map node)
                          ref-map)]
             (substitute-refs body ref-map))
           node))

        :else
        node))
    node)))

(defn literal?
  "true if node is ground and does not contain :map or :set subnodes,
  false otherwise.

  The constraint that node may not contain :map or :set subnodes is
  due to the semantics of map and set patterns: they express submap
  and subsets respectively. Compiling these patterns to literals as
  part of an equality check would result in false negative matches.

  See also: compile-ground"
  [node]
  (and (ground? node)
       (not-any? (comp #{:map :unq :set} tag)
                 (subnodes node))))

(defn not-not?
  [node]
  (= (:tag node) :not (:tag (:argument node))))

(defn not-tag
  {:private true}
  [node]
  (case (:tag node)
    :not
    (case (:tag (:argument node))
      :not
      :not-not
      ;; else
      :not)
    ;; else
    nil))

(defn match-bindings
  "Returns a set of variables which would be bound by a successful
  pattern match.

  (match-bindings (parse '(not [?x])))
  ;; =>
  #{}

  (match-bindings (parse '(not (not [?x]))))
  ;; =>
  #{{:tag :lvr, :symbol ?x}}
  "
  [node]
  (variables
   (prewalk
    (fn [node]
      (case (not-tag node)
        ;; Replace (not (not ?x)) with ?x.
        :not-not
        (:argument (:argument node))

        ;; Replace (not ?x) with _.
        :not
        {:tag :any, :symbol '_}

        ;; else
        node))
    node)))

(defn analyze*
  {:private true}
  [node]
  (fold
   (fn [state node]
     (let [negated-counter (:negated-counter state)]
       {:negated-counter
        (if (zero? negated-counter)
          (case (not-tag node)
            :not
            (+ negated-counter (dec (count (subnodes node))))

            :not-not
            (+ negated-counter 1)

            ;; else
            negated-counter)
          (dec negated-counter))
        
        :occurrences
        (if (variable-node? node)
          (update (:occurrences state) node (fnil inc 0))
          (:occurrences state))
        
        :occurrences-in-not
        (if (and (not (zero? negated-counter))
                 (variable-node? node))
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

(defn scan-cat
  "If `node` is a `:prt` node resembling the pattern

    _ ... <p_1 ,,, p_n> . _ ...

  then `scan-cat` will return the `:cat` node representing

    <p_1 ,,, p_n>

  Example:

    (scan-cat
     '{:tag :prt,
       :left {:tag :drp, :symbol _},
       :right
       {:tag :prt,
        :left {:tag :cat,
               :elements [{:tag :lvr, :symbol ?x}
                          {:tag :lvr, :symbol ?y}]},
        :right
        {:tag :prt,
         :left {:tag :drp, :symbol _},
         :right {:tag :cat, :elements []}}}})
    ;; =>
    {:tag :cat,
     :elements [{:tag :lvr, :symbol ?x}
                {:tag :lvr, :symbol ?y}]}"
  [node]
  (if (and (= (:tag node) :prt)
           (= (:tag (:left node)) :drp)
           (= (:tag (:right node)) :prt)
           (= (:tag (:left (:right node))) :cat)
           (= (:tag (:right (:right node))) :prt)
           (= (:tag (:left (:right (:right node)))) :drp)
           (= (:tag (:right (:right (:right node)))) :cat)
           (empty? (:elements (:right (:right (:right node))))))
    (:left (:right node))))

(s/fdef scan-cat
  :args (s/cat :node ::node)
  :ret (s/nilable ::node))

(defn partition-nodes
  "Given a `partition-node?` returns a vector of its `:left` and
  `:right` nodes recursively and in order i.e. `:left` or `:right` is
  a `partition-node?` then the returned vector will include the result
  of applying `partition-nodes` to them.

  Example:

      (let [vec-node (parse '[_ ..2 1 . 1 2 3 ... !xs])
            prt-node (:prt vec-node)]
        (map unparse (partition-nodes prt-node)))
      ;; =>
      ((_ ..2) (1) (1 2 3 ...) (!xs) ())"
  {:arglists '([partition-node])
   :private true}
  [node]
  (s/assert :meander.syntax.node.epsilon/partition node)
  (let [left (:left node)
        right (:right node)]
    (concat (if (partition-node? left)
              (partition-nodes left)
              (list left))
            (if (partition-node? right)
              (partition-nodes right)
              (list right)))))

(defn partition-from-nodes
  {:private true}
  [nodes]
  (reduce
   (fn [prt-node node]
     {:tag :prt
      :left node
      :right prt-node})
   (last nodes)
   (reverse (butlast nodes))))

(defn window [node]
  (s/assert :meander.syntax.node.epsilon/partition node)
  (let [p-nodes (partition-nodes node)]
    (if (<= 3 (count p-nodes))
      (let [[a b c] p-nodes]
        (if (and (= (:tag a) :drp)
                 (= (:tag b) :cat)
                 (= (:tag c) :drp))
          (let [rest-p-nodes (drop 2 p-nodes)]
            (if (and (= (count rest-p-nodes) 2)
                     (= (:tag (nth rest-p-nodes 0)) :drp)
                     (and (= (:tag (nth rest-p-nodes 1)) :cat)
                          (= (max-length (nth rest-p-nodes 1)) 0)))
              [b nil]
              [b (partition-from-nodes rest-p-nodes)])))))))

(comment
  (analyze (parse '[(not [?x (not ?x)]) !xs (not [?x ?x])]))
  ;; =>
  {:occurrences
   {{:tag :lvr, :symbol ?x} 4
    {:tag :mvr, :symbol !xs} 1}
   :occurrences-in-not
   {{:tag :lvr, :symbol ?x} 4}})
