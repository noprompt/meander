(ns meander.syntax.gamma
  #?(:clj
     (:require [clojure.spec.alpha :as s]
               [clojure.spec.gen.alpha :as s.gen]
               [clojure.string :as string]
               [cljs.tagged-literals]
               [meander.util.gamma :as util])
     :cljs
     (:require [cljs.spec.alpha :as s :include-macros true]
               [cljs.spec.gen.alpha :as s.gen :include-macros true]
               [clojure.string :as string]
               [meander.util.gamma :as util]))
  #?(:cljs
     (:require-macros [meander.syntax.gamma]))
  #?(:clj
     (:import (cljs.tagged_literals JSValue))))

#?(:clj (set! *warn-on-reflection* true))

(s/def :meander.syntax.gamma/tag
  keyword?)

(s/def :meander.syntax.gamma/node
  (s/keys :req-un [:meander.syntax.gamma/tag]))

;; ---------------------------------------------------------------------
;; AST API

(defn node?
  "true if x is an AST node."
  [x]
  (s/valid? :meander.syntax.gamma/node x))

(defn children-dispatch
  {:private true}
  [node]
  (s/assert :meander.syntax.gamma/node node)
  (:tag node))

(s/fdef children
  :args (s/cat :node :meander.syntax.gamma/node)
  :ret (s/coll-of :meander.syntax.gamma/node
                  :kind sequential?))

(defmulti children
  "Return a sequential? of all children of node."
  {:arglists '([node])}
  #'children-dispatch)

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
  :args (s/cat :node :meander.syntax.gamma/node)
  :ret (s/or :nat nat-int?
             :inf #{##Inf}))

(defn max-length-dispatch
  {:private true}
  [node]
  (s/assert :meander.syntax.gamma/node node)
  (:tag node))

(defmulti max-length
  "The maximum possible length the pattern described by node can be."
  {:arglists '([node])}
  #'max-length-dispatch)

(defn min-length-dispatch
  {:private true}
  [node]
  (s/assert :meander.syntax.gamma/node node)
  (:tag node))

(defmulti min-length
  "The maximum possible length the pattern described by node can be."
  {:arglists '([node])}
  #'min-length-dispatch)

(s/fdef variable-length?
  :args (s/cat :node :meander.syntax.gamma/node)
  :ret boolean?)

(defn variable-length?
  "true if node may have a variable length."
  [node]
  (not (= (min-length node) (max-length node))))

(defn ground?-dispatch
  {:private true}
  [node]
  (s/assert :meander.syntax.gamma/node node)
  (:tag node))

(defmulti ground?
  "true if node is ground i.e. it contains no variables or is not a
  match operator."
  {:arglists '([node])}
  #'ground?-dispatch)

(s/fdef search?
  :args (s/cat :node :meander.syntax.gamma/node)
  :ret boolean?)

(defn search?-dispatch
  {:private true}
  [node]
  (s/assert :meander.syntax.gamma/node node)
  (:tag node))

(defmulti search?
  "true if node represents a search, false otherwise."
  {:arglists '([node])}
  #'search?-dispatch)

(defn unparse-dispatch
  {:private true}
  [node]
  (s/assert :meander.syntax.gamma/node node)
  (:tag node))

(defmulti unparse
  "In pre-order fashion rewrite a node into a Clojure form."
  {:arglists '([node])}
  #'unparse-dispatch)

(defn tag
  "Return the tag of node."
  [node]
  (s/assert :meander.syntax.gamma/node node)
  (:tag node))

(defn variables
  "Return all :lvr and :mvr nodes in node."
  [node]
  (s/assert :meander.syntax.gamma/node node)
  (into #{}
    (filter (comp #{:lvr :mvr} tag))
    (subnodes node)))

(defn memory-variables
  "Return all :mvr nodes in node."
  [node]
  (s/assert :meander.syntax.gamma/node node)
  (into #{}
        (filter (comp #{:mvr} tag))
        (subnodes node)))

(defn logic-variables
  "Return all :lvr nodes in node."
  [node]
  (s/assert :meander.syntax.gamma/node node)
  (into #{}
        (filter (comp #{:lvr} tag))
        (subnodes node)))

#?(:clj
   (defn re-matches? [ re s]
     (.matches (re-matcher re s)))
   :cljs
   (defn re-matches? [re s]
     (.test re s)))

(defn any-form?
  "true if x is a symbol beginning with _."
  [x]
  (and (simple-symbol? x)
       (re-matches? #"_.*" (name x))))

(s/def :meander.syntax.gamma/any
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

(s/def :meander.syntax.gamma.node.any/tag
  #{:any})

(s/def :meander.syntax.gamma.node.any/symbol
  :meander.syntax.gamma/any)

(s/def :meander.syntax.gamma.node/any
  (s/keys :req-un [:meander.syntax.gamma.node.any/tag
                   :meander.syntax.gamma.node.any/symbol]))

(defn any-node?
  [x]
  (s/valid? :meander.syntax.gamma.node/any x))


(s/fdef logic-variable-form?
  :args (s/cat :x any?)
  :ret boolean?)

(defn logic-variable-form?
  "true if x is in the form of a logic variable i.e. a simple symbol
  with a name beginning with \\?."
  [x]
  (and (simple-symbol? x)
       (re-matches? #"\?.+" (name x))))

(s/def :meander.syntax.gamma/logic-variable
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

(s/def :meander.syntax.gamma.node.lvr/tag
  #{:lvr})

(s/def :meander.syntax.gamma.node.lvr/symbol
  :meander.syntax.gamma/logic-variable)

(s/def :meander.syntax.gamma.node/lvr
  (s/keys :req-un [:meander.syntax.gamma.node.lvr/tag
                   :meander.syntax.gamma.node.lvr/symbol]))

(defn lvr-node?
  [x]
  (s/valid? :meander.syntax.gamma.node/lvr x))

(defn memory-variable-form?
  "true if x is in the form of a memory variable i.e. a simple symbol
  with a name beginning with \\!."
  [x]
  (and (simple-symbol? x)
       (re-matches? #"!.+" (name x))))

(s/def :meander.syntax.gamma/memory-variable
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

(s/def :meander.syntax.gamma.node.mvr/tag
  #{:mvr})

(s/def :meander.syntax.gamma.node.mvr/symbol
  :meander.syntax.gamma/memory-variable)

(s/def :meander.syntax.gamma.node/mvr
  (s/keys :req-un [:meander.syntax.gamma.node.mvr/tag
                   :meander.syntax.gamma.node.mvr/symbol]))

(defn mvr-node?
  [x]
  (s/valid? :meander.syntax.gamma.node/mvr x))

(defn variable-node?
  [x]
  (or (mvr-node? x)
      (lvr-node? x)))

(defn ref-sym?
  [x]
  (and (simple-symbol? x)
       (boolean (re-matches #"%.+" (name x)))))

(s/def :meander.syntax.gamma/reference
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

(s/def :meander.syntax.gamma.node/ref
  (s/tuple #{:ref} :meander.syntax.gamma/reference))

(defn ref-node?
  [x]
  (s/valid? :meander.syntax.gamma.node/reference x))

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
                   (:dot :dt+ :dt*)
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
                     (and (= c 1)
                          (= :any (:tag (first l))))
                     {:tag :drp
                      :symbol (:symbol (first l))}

                     (and (= c 1)
                          (= :mvr (:tag (first l))))
                     {:tag :rst
                      :mvr (first l)}

                     :else
                     {:tag :rp*
                      :elements l}))

                 :dt+
                 {:tag :rp+
                  :elements l
                  :n (:n node)}

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
                    :elements l}

                   :dt+
                   {:tag :rp+
                    :elements l
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
    (let [nothing (gensym)
          pattern (nth xs 1 nothing)]
      (if (identical? pattern nothing)
        (throw (ex-info "scan expects at least on argument"
                        {:pattern xs
                         :meta (meta xs)}))
        (parse
         `(~'pred coll?
           ;; Will cause compiler to emit a useless seq? check.
           (~'app seq (~@'(_ ...) ~pattern ~@'(. _ ...))))
         env)))
    (parse xs env)))

(defn parse-vscan
  {:private true}
  [xs env]
  (if (and (seq? xs)
           (= (first xs) 'vscan))
    (let [nothing (gensym)
          pattern (nth xs 1 nothing)]
      (if (identical? pattern nothing)
        (throw (ex-info "vscan expects at least on argument"
                        {:pattern xs
                         :meta (meta xs)}))
        (parse
         `(~'pred coll?
           ;; Will cause compiler to emit a useless vector?
           ;; check.
           (~'app vec [~@'(_ ...) ~pattern ~@'(. _ ...)]))
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
  "Parses a seq? into a :meander.syntax.gamma/node.

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
          [$0 $N] (re-matches #"\.(?:\.(?:\.|(\d+))?)?" s)]
      (case $N
        nil
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
            (re-find #"\A_" s)
            {:tag :any
             :symbol sym}

            (re-find #"\A\?." s)
            {:tag :lvr
             :symbol sym}

            (re-find #"\A!." s)
            {:tag :mvr
             :symbol sym}

            #_(re-find #"\A%." s)
            #_{:tag :ref
               :symbol sym}

            :else
            {:tag :lit
             :value sym}))

        ;; `..0` is the same as `...`.
        "0"
        {:tag :dt*}

        ;; else
        ;; Inteneral tag for postfix n or more operator.
        {:tag :dt+
         :n (util/parse-int $N)}))))

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

(s/fdef parse
  :args (s/alt :a1 (s/cat :x any?)
               :a2 (s/cat :x any? :env map?))
  :ret :meander.syntax.gamma/node)

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
   (cond
     (seq? x)
     (parse-seq x env)
     
     (vector? x)
     (parse-vector x env) 

     (and (map? x)
          (not (record? x)))
     (parse-map x env)
     

     (set? x)
     {:tag :set
      :elements (parse-all x env)}

     (symbol? x)
     (parse-symbol x)

     #?@(:clj [(instance? JSValue x)
               (parse-js-value x env)])

     :else
     {:tag :lit
      :value x})))


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
  `(~'let ~@(mapcat (juxt :binding :expr) (:bindings node))))

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
          (children (:rest-map node))))

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

;; :rp*

(defmethod children :rp* [node]
  (:elements node))
(defmethod ground? :rp* [_]
  false)

(defmethod min-length :rp* [_]
  0)

(defmethod max-length :rp* [_]
  ##Inf)

(defmethod unparse :rp* [node]
  `(~@(sequence (map unparse) (:elements node)) ~'...))

(defmethod search? :rp* [_]
  false)

;; :rp+

(defmethod children :rp+ [node]
  (:elements node))

(defmethod ground? :rp+ [_]
  false)

(defmethod min-length :rp+ [node]
  (let [n (:n node)]
    (if (integer? n)
      (* n (count (:elements node)))
      0)))

(defmethod max-length :rp+ [_]
  ##Inf)

(defmethod unparse :rp+ [node]
  (let [dots (if-some [n (:n node)]
               (symbol (str ".." n))
               '..)]
    `(~@(sequence (map unparse) (:elements node)) ~dots)))

(defmethod search? :rp+ [_]
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
  (:elements node))

(defmethod ground? :set [node]
  (every? ground? (:elements node)))

(defmethod unparse :set [node]
  (set (map unparse (:elements node))))

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
  "Same as clojure.walk/postwalk but for AST nodes."
  [f node]
  (walk (partial postwalk f) f node))

(defn prewalk
  "Same as clojure.walk/prewalk but for AST nodes."
  [f node]
  (walk (partial prewalk f) identity (f node)))

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

(defmethod walk :prd [inner outer node]
  (outer (assoc node :arguments (mapv inner (:arguments node)))))

(defmethod walk :prt [inner outer node]
  (outer (assoc node
                :left (inner (:left node))
                :right (inner (:right node)))))

(defmethod walk :rp* [inner outer node]
  (outer (assoc node :elements (map inner (:elements node)))))

(defmethod walk :rp+ [inner outer node]
  (outer (assoc node :elements (map inner (:elements node)))))

(defmethod walk :rxc [inner outer node]
  (outer (assoc node :capture (inner (:capture node)))))

(defmethod walk :set [inner outer node]
  (outer (assoc node :elements (map inner (:elements node)))))

(defmethod walk :seq [inner outer node]
  (outer (assoc node :prt (inner (:prt node)))))

(defmethod walk :vec [inner outer node]
  (outer (assoc node :prt (inner (:prt node)))))


;; ---------------------------------------------------------------------
;; fold

(defmulti fold
  {:arglists '([f value node])}
  (fn [_ _ node] (tag node))
  :default ::default)

(defmethod fold ::default
  [f result node]
  (f result node))

(defmethod fold :app [f result node]
  (reduce
   (fn [result node]
     (fold f result node))
   (f result node)
   (:arguments node)))

(defmethod fold :cat
  [f result node]
  (reduce
   (fn [result element]
     (fold f result element))
   (f result node)
   (:elements node)))

(defmethod fold :cnj [f result node]
  (reduce
   (fn [result node]
     (fold f result node))
   (f result node)
   (:arguments node)))

(defmethod fold :ctn [f result node]
  (let [result (f result node)
        result (if-some [context (:context node)]
                 (fold f result context)
                 result)]
    (fold f result (:pattern node))))

(defmethod fold :dsj [f result node]
  (reduce
   (fn [result node]
     (fold f result node))
   (f result node)
   (:arguments node)))

(defmethod fold :jsa [f result node]
  (fold f (f result node) (:prt node)))

(defmethod fold :jso [f result node]
  (reduce
   (fn [result [k-node v-node]]
     (fold f (fold f result k-node) v-node))
   (f result node)
   (:object node)))

(defmethod fold :let [f result node]
  (reduce
   (fn [result binding]
     (fold f result (:binding binding)))
   (f result node)
   (:bindings node)))

(defmethod fold :map
  [f result node]
  (let [result (if-some [rest-map (:rest-map node)]
                 (fold f result rest-map)
                 result)]
    (reduce
     (fn [result [k-node v-node]]
       (fold f (fold f result k-node) v-node))
     (f result node)
     (:map node))))

(defmethod fold :not [f result node]
  (fold f (f result node) (:argument node)))

(defmethod fold :prd [f result node]
  (reduce
   (fn [result node]
     (fold f result node))
   (f result node)
   (:arguments node)))

(defmethod fold :prt
  [f result node]
  (fold f (fold f (f result node) (:left node)) (:right node)))

(defmethod fold :rp*
  [f result node]
  (reduce
   (fn [result node]
     (fold f result node))
   (f result node)
   (:elements node)))

(defmethod fold :rp+
  [f result node]
  (reduce
   (fn [result node]
     (fold f result node))
   (f result node)
   (:elements node)))

(defmethod fold :rxc [f result node]
  (fold f (f result node) (:capture node)))

(defmethod fold :set
  [f result node]
  (reduce
   (fn [result node]
     (fold f result node))
   (f result node)
   (:elements node)))

(defmethod fold :seq
  [f result node]
  (fold f (f result node) (:prt node)))

(defmethod fold :vec
  [f result node]
  (fold f (f result node) (:prt node)))


;; ---------------------------------------------------------------------
;; defsyntax


(s/def :meander.syntax.gamma/defsyntax-args
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

  (require '[meander.match.gamma :as r.match])

  (r.match/match \"elf\"
    (re #\"[a-z]+\")
    :okay!)
  ;; => :okay
  "
  {:arglists '([name docstring? meta? arglist & body])}
  [& args]
  (let [data (s/conform :meander.syntax.gamma/defsyntax-args args)]
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
        ;; find macros (in meander.match.gamma) are expanded in
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

                 (defmethod meander.syntax.gamma/expand-seq '~q-sym [[_# ~@arglist] _#]
                   (~sym ~@arglist))))
             (in-ns 'meander.syntax.gamma)))
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
