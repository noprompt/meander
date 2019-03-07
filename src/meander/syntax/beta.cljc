(ns meander.syntax.beta
  #?(:clj
     (:require [clojure.spec.alpha :as s]
               [clojure.spec.gen.alpha :as s.gen]
               [clojure.string :as string]
               [cljs.tagged-literals]
               [meander.util.beta :as util])
     :cljs
     (:require [cljs.spec.alpha :as s :include-macros true]
               [cljs.spec.gen.alpha :as s.gen :include-macros true]
               [clojure.string :as string]
               [meander.util.beta :as util]))
  #?(:cljs
     (:require-macros [meander.syntax.beta]))
  #?(:clj
     (:import (cljs.tagged_literals JSValue))))

#?(:clj (set! *warn-on-reflection* true))

(s/def :meander.syntax.beta/node
  (s/tuple keyword? any?))

(defn children-dispatch
  {:private true}
  [node]
  (s/assert :meander.syntax.beta/node node)
  (nth node 0))

(s/fdef children
  :args (s/cat :node :meander.syntax.beta/node)
  :ret (s/coll-of :meander.syntax.beta/node
                  :kind sequential?))

(defmulti children
  "Return a sequential? of all children of node."
  {:arglists '([node])}
  #'children-dispatch)

(defmethod children :default
  [node] [])

(defn subnodes
  "Return a sequence of all subnodes of node."
  [node]
  (cons node (mapcat subnodes (children node))))

(defn proper-subnodes
  "Return the all subnodes in node excluding node."
  [node]
  (rest (subnodes node)))

(s/fdef max-length
  :args (s/cat :node :meander.syntax.beta/node)
  :ret (s/or :nat nat-int?
             :inf #{##Inf}))

(defn max-length-dispatch
  {:private true}
  [node]
  (s/assert :meander.syntax.beta/node node)
  (nth node 0))

(defmulti max-length
  "The maximum possible length the pattern described by node can be."
  {:arglists '([node])}
  #'max-length-dispatch)

(defn min-length-dispatch
  {:private true}
  [node]
  (s/assert :meander.syntax.beta/node node)
  (nth node 0))

(defmulti min-length
  "The maximum possible length the pattern described by node can be."
  {:arglists '([node])}
  #'min-length-dispatch)

(s/fdef variable-length?
  :args (s/cat :node :meander.syntax.beta/node)
  :ret boolean?)

(defn variable-length?
  "true if node may have a variable length."
  [node]
  (not (= (min-length node) (max-length node))))

(defn ground?-dispatch
  {:private true}
  [node]
  (s/assert :meander.syntax.beta/node node)
  (nth node 0))

(defmulti ground?
  "true if node is ground i.e. it contains no variables or is not a
  match operator."
  {:arglists '([node])}
  #'ground?-dispatch)

(s/fdef search?
  :args (s/cat :node :meander.syntax.beta/node)
  :ret boolean?)

(defn search?-dispatch
  {:private true}
  [node]
  (s/assert :meander.syntax.beta/node node)
  (nth node 0))

(defmulti search?
  "true if node represents a search, false otherwise."
  {:arglists '([node])}
  #'search?-dispatch)

(defn unparse-dispatch
  {:private true}
  [node]
  (s/assert :meander.syntax.beta/node node)
  (nth node 0))

(defmulti unparse
  "In pre-order fashion rewrite a node into a Clojure form."
  {:arglists '([node])}
  #'unparse-dispatch)

(defn tag [node]
  (s/assert :meander.syntax.beta/node node)
  (first node))

(defn variables
  "Return all :lvr and :mvr nodes in node."
  [node]
  (s/assert :meander.syntax.beta/node node)
  (into #{}
        (filter (comp #{:lvr :mvr} tag))
        (subnodes node)))

(defn memory-variables
  "Return all :mvr nodes in node."
  [node]
  (s/assert :meander.syntax.beta/node node)
  (into #{}
        (filter (comp #{:mvr} tag))
        (subnodes node)))

(defn logic-variables
  "Return all :lvr nodes in node."
  [node]
  (s/assert :meander.syntax.beta/node node)
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

(s/def :meander.syntax.beta/any
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

(s/def :meander.syntax.beta.node/any
  (s/tuple #{:any} :meander.syntax.beta/any))

(defn any-node?
  [x]
  (s/valid? :meander.syntax.beta.node/any x))

(s/fdef logic-variable-form?
  :args (s/cat :x any?)
  :ret boolean?)

(defn logic-variable-form?
  "true if x is in the form of a logic variable i.e. a simple symbol
  with a name beginning with \\?."
  [x]
  (and (simple-symbol? x)
       (re-matches? #"\?.+" (name x))))

(s/def :meander.syntax.beta/logic-variable
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

(s/def :meander.syntax.beta.node/lvr
  (s/tuple #{:lvr} :meander.syntax.beta/logic-variable))

(defn lvr-node?
  [x]
  (s/valid? :meander.syntax.beta.node/lvr x))

(defn memory-variable-form?
  "true if x is in the form of a memory variable i.e. a simple symbol
  with a name beginning with \\!."
  [x]
  (and (simple-symbol? x)
       (re-matches? #"!.+" (name x))))

(s/def :meander.syntax.beta/memory-variable
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

(s/def :meander.syntax.beta.node/mvr
  (s/tuple #{:mvr} :meander.syntax.beta/memory-variable))

(defn mvr-node?
  [x]
  (s/valid? :meander.syntax.beta.node/mvr x))

(defn variable-node?
  [x]
  (or (mvr-node? x)
      (lvr-node? x)))

;; ---------------------------------------------------------------------
;; Parse implementation

(declare parse)

(defn parse-all
  {:private true}
  [xs env]
  (map (fn [x]
         (parse x env))
       xs))

(defn expand-prt
  {:private true}
  [xs]
  (let [[l r] (split-with
               (fn [[t]]
                 (case t
                   (:dot :dt+ :dt*)
                   false
                   ;; else
                   true))
               xs)]
    (if (seq l)
      (let [[t x] (first r)]
        [:prt {:left (case t
                       :dt*
                       (if (and (= (bounded-count 2 l) 1)
                                (= :any (ffirst l)))
                         [:drp (first l)]
                         [:rp* {:terms l}])

                       :dt+
                       [:rp+ {:terms l :n x}]

                       (nil :dot)
                       [:cat l]) 
               :right (expand-prt (next r))}])
      (if (seq r)
        (let [[t x] (first r)]
          [:prt {:left (case t
                         :dt*
                         [:rp* {:terms l}]

                         :dt+
                         [:rp+ {:terms l :n x}]

                         (nil :dot)
                         [:cat l]) 
                 :right (expand-prt (next r))}])
        [:cat []]))))

(s/fdef parse
  :args (s/alt :a1 (s/cat :x any?)
               :a2 (s/cat :x any? :env map?))
  :ret :meander.syntax.beta/node)

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

(defn parse-seq
  "Parses a seq? into a :meander.syntax.beta/node.

  seqs? of the following form are handled specially, all other seqs
  are parsed as :seq nodes.

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
        and
        [:cnj {:terms (parse-all (rest xs) env)}]

        app
        [:app {:form (second xs)
               :terms (parse-all (nnext xs) env)}]

        guard
        [:grd {:form (second xs)}]

        let
        (let [xs* (rest xs)]
          (if (odd? (count xs*))
            (throw (ex-info "let pattern requires an even number of arguments"
                            {:pattern xs
                             :meta (meta xs)}))
            [:cnj {:terms (map
                           (fn [[pattern expr]]
                             [:let {:binding (parse pattern env)
                                    :expr expr}])
                           (partition-all 2 xs*))}]))

        not
        (if (= 1 (bounded-count 2 (drop 1 xs)))
          [:not {:term (parse (second xs) env)}]
          (throw (ex-info "not pattern requires at one argument"
                          {:pattern xs
                           :meta (meta xs)})))

        or
        [:dsj {:terms (parse-all (rest xs) env)}]

        pred
        [:prd {:form (second xs)
               :terms (parse-all (nnext xs) env)}]

        quote 
        [:quo {:form (second xs)}]

        re
        (let [nothing (gensym)
              regex (nth xs 1 nothing)]
          (if (identical? regex nothing)
            (throw (ex-info "re pattern expects at least one argument"
                            {:pattern xs
                             :meta (meta xs)}))
            (let [capture (nth xs 2 nothing)]
              (if (identical? capture nothing)
                [:rxt {:regex regex}]
                [:rxc {:regex regex
                       :capture (parse capture env)}]))))

        scan
        (parse-scan xs env) 

        vscan
        (parse-vscan xs env)

        clojure.core/unquote
        [:unq {:form (second xs)}]

        clojure.core/unquote-splicing
        [:uns {:form (second xs)}]

        ;; else
        (let [xs* (expand-seq xs env)]
          (if (= xs* xs)
            [:seq (expand-prt (parse-all xs env))]
            (parse xs* env))))
      [:seq (expand-prt (parse-all xs env))])))

(defn parse-symbol
  {:private true}
  [sym]
  (if (namespace sym)
    [:lit sym]
    (let [s (name sym)
          [$0 $N] (re-matches #"\.(?:\.(?:\.|(\d+))?)?" s)]
      (case $N
        nil
        (case $0
          "."
          [:dot]

          ".."
          [:dt+ $N]

          "..."
          [:dt*]

          nil
          (cond
            (re-find #"\A_" s)
            [:any sym]

            (re-find #"\A\?." s)
            [:lvr sym]

            (re-find #"\A!." s)
            [:mvr sym]

            :else
            [:lit sym]))

        "0"
        [:dt*]

        ;; else
        [:dt+ (util/parse-int $N)]))))

(defn parse-js-value [^JSValue js-value env]
  (let [x (.val js-value)]
    (cond
      (vector? x)
      [:jsa
       (expand-prt (parse-all x env))]

      (map? x)
      [:jso
       (into {}
             (map
              (fn [[k v]]
                (let [k* (if (keyword? k)
                           (subs (str k) 1)
                           k)]
                  [(parse k*) (parse v)])))
             x)])))

(defn parse
  ([x]
   (parse x {}))
  ([x env]
   (cond
     (seq? x)
     (parse-seq x env)
     
     (vector? x)
     [:vec (expand-prt (parse-all x env))]

     (and (map? x)
          (not (record? x)))
     [:map
      (into {}
            (map
             (fn [[k v]]
               [(parse k) (parse v)]))
            x)]

     (set? x)
     [:set (parse-all x env)]

     (symbol? x)
     (parse-symbol x)

     #?@(:clj [(instance? JSValue x)
               (parse-js-value x env)])

     :else
     [:lit x])))

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

(defmethod children :app [[_ {terms :terms}]]
  terms)

(defmethod ground? :app [_]
  false)

(defmethod unparse :app [[_ {form :form terms :terms}]]
  `(~'app ~form ~@(map unparse terms)))

(defmethod search? :app
  [_] false)

;; :cat

(defmethod ground? :cat [[_ nodes]]
  (every? ground? nodes))

(defmethod children :cat [[_ terms]]
  terms)

(defmethod min-length :cat [[_ terms]]
  (count terms))

(defmethod max-length :cat [[_ terms]]
  (count terms))

(defmethod unparse :cat [[_ terms]]
  (apply list (map unparse terms)))

(defmethod search? :cat [[_ terms]]
  (boolean (some search? terms)))

;; :cnj

(defmethod children :cnj [[_ {terms :terms}]]
  terms)

(defmethod ground? :cnj [_]
  false)

(defmethod unparse :cnj [[_ {terms :terms}]]
  `(~'and ~@(sequence (map unparse) terms)))

(defmethod search? :cnj
  [[_ {terms :terms}]]
  (boolean (some search? terms)))

;; :drp

(defmethod ground? :drp [_]
  false)

(defmethod min-length :drp [_]
  0)

(defmethod max-length :drp [_]
  ##Inf)

(defmethod unparse :drp [[_ [_ any-sym]]]
  (list any-sym '...))

(defmethod search? :drp [_]
  false)

;; :dsj

(defmethod children :dsj [[_ {terms :terms}]]
  terms)

(defmethod ground? :dsj [_]
  false)

(defmethod unparse :dsj [[_ {nodes :terms}]]
  `(~'or ~@(sequence (map unparse) nodes)))

(defmethod search? :dsj [[_ {nodes :terms}]]
  (boolean (some search? nodes)))

;; :grd

(defmethod ground? :grd [_]
  false)

(defmethod unparse :grd [[_ {form :form}]]
  `(~'guard ~form))

(defmethod search? :grd [_]
  false)

;; :jsa

(defmethod children :jsa [[_ prt]]
  [prt])

(defmethod ground? :jsa [[_ prt]]
  (ground? prt))

(defmethod min-length :jsa [[_ prt]]
  (min-length prt))

(defmethod max-length :jsa [[_ prt]]
  (max-length prt))

(defmethod unparse :jsa [[_ prt]]
  #?(:clj
     (JSValue. (vec (unparse prt)))
     :cljs
     (into-array (unparse prt))))

(defmethod search? :jsa [[_ prt]]
  (search? prt))


;; :let

(defmethod children :let [[_ {binding :binding, expr :expr}]]
  [binding])

(defmethod ground? :let [_]
  false)

(defmethod unparse :let [[_ {binding :binding, expr :expr}]]
  `(~'let ~(unparse binding) ~expr))

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

(defmethod unparse :lit [[_ lit]]
  (unparse-lit lit))

(defmethod search? :lit [_] false)

;; :lvr

(defmethod ground? :lvr [_]
  false)

(defmethod unparse :lvr [[_ sym]]
  sym)

(defmethod search? :lvr [_]
  false)

;; :map

(defmethod children :map [[_ map-data]]
  (mapcat identity map-data))

(defmethod ground? :map [[_ map-data]]
  (every?
   (fn [[k v]]
     (and (ground? k)
          (ground? v)))
   map-data))

(defmethod unparse :map [[_ map-data]]
  (reduce-kv
   (fn [m k v]
     (assoc m (unparse k) (unparse v)))
   {}
   map-data))

(defmethod search? :map [[_ map-data]]
  (boolean
   (some
    (fn [[k v]]
      (or (not (ground? k))
          (search? k)
          (search? v)))
    map-data)))

;; :mvr

(defmethod ground? :mvr [_]
  false)

(defmethod unparse :mvr [[_ sym]]
  sym)

(defmethod search? :mvr [_]
  false)

;; :not

(defmethod children :not [[_ {term :term}]]
  [term])

(defmethod ground? :not [_]
  false)

(defmethod unparse :not [[_ {term :term}]]
  `(~'not ~(unparse term)))

(defmethod search? :not [[_ {term :term}]]
  (search? term))

;; :prd

(defmethod children :prd [[_ {terms :terms}]]
  terms)

(defmethod ground? :prd [_]
  false)

(defmethod unparse :prd [[_ {form :form, terms :terms}]]
  `(~'pred ~form ~@(map unparse terms)))

(defmethod search? :prd [[_ {terms :terms}]]
  (boolean (some search? terms)))

;; :prt

(defmethod children :prt [[_ {left :left, right :right}]]
  [left right])


(defmethod ground? :prt
  [[_ {left :left, right :right}]]
  (and (ground? left)
       (if (some? right)
         (ground? right)
         true)))

(defmethod min-length :prt
  [[_ {left :left, right :right}]]
  (+ (min-length left)
     (if (some? right)
       (min-length right)
       0)))

(defmethod max-length :prt
  [[_ {left :left, right :right}]]
  (+ (max-length left)
     (if (some? right)
       (max-length right)
       0)))

(defmethod unparse :prt
  [[_ {left :left, right :right}]]
  `(~@(unparse left)
    ~@(when-some [right (seq (unparse right))]
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
  [[_ {left :left, right :right}]]
  (if (some? right)
    (or (and (variable-length? left)
             (variable-length? right))
        (search? left)
        (search? right))
    (search? left)))

;; :quo

(defmethod ground? :quo [_]
  true)

(defmethod unparse :quo [[_ {form :form}]]
  `(quote ~form))


(defmethod search? :quo [_]
  false)

;; :rp*

(defmethod children :rp* [[_ {terms :terms}]]
  terms)

(defmethod ground? :rp* [_]
  false)

(defmethod min-length :rp* [_]
  0)

(defmethod max-length :rp* [_]
  ##Inf)

(defmethod unparse :rp*
  [[_ {terms :terms}]]
  `(~@(sequence (map unparse) terms) ~'...))

(defmethod search? :rp*
  [_] false)

;; :rp+

(defmethod children :rp+ [[_ {terms :terms}]]
  terms)

(defmethod ground? :rp+ [_]
  false)

(defmethod min-length :rp+ [[_ {terms :terms, n :n}]]
  (if (integer? n)
    (* n (count terms))
    0))

(defmethod max-length :rp+ [_]
  ##Inf)

(defmethod unparse :rp+
  [[_ {terms :terms n :n}]]
  (let [dots (if (some? n)
               (symbol (str ".." n))
               '..)]
    `(~@(sequence (map unparse) terms) ~dots)))

(defmethod search? :rp+
  [_] false)

;; :set

(defmethod children :set [[_ elements]]
  elements)

(defmethod ground? :set [[_ the-set]]
  (every? ground? the-set))

(defmethod unparse :set [[_ the-set]]
  (set (map unparse the-set)))

(defmethod search? :set [node]
  (not (ground? node)))

;; seq

(defmethod children :seq [[_ prt]]
  [prt])

(defmethod ground? :seq [[_ prt]]
  (ground? prt))

(defmethod unparse :seq [[_ prt]]
  (seq (unparse prt)))

(defmethod search? :seq [[_ prt]]
  (search? prt))

(defmethod min-length :seq
  [[_ prt]]
  (min-length prt))


(defmethod max-length :seq
  [[_ prt]]
  (max-length prt))

;; :unq

(defmethod ground? :unq [_]
  true)

(defmethod unparse :unq [[_ {expr :expr}]]
  (list 'clojure.core/unquote expr))

(defmethod search? :unq [_]
  false)

;; :uns

(defmethod ground? :uns [_]
  false)

(defmethod unparse :uns [[_ {expr :expr}]]
  (list 'clojure.core/unquote-splicing expr))

(defmethod search? :uns
  [_] false)

;; :vec

(defmethod children :vec [[_ prt]]
  [prt])

(defmethod ground? :vec [[_ prt]]
  (ground? prt))

(defmethod min-length :vec [[_ prt]]
  (min-length prt))

(defmethod max-length :vec [[_ prt]]
  (max-length prt))

(defmethod unparse :vec [[_ prt]]
  (vec (unparse prt)))

(defmethod search? :vec [[_ prt]]
  (search? prt))

;; ---------------------------------------------------------------------
;; defsyntax


(s/def :meander.syntax.beta/defsyntax-args
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

  (require '[meander.match.beta :as r.match])

  (r.match/match \"elf\"
    (re #\"[a-z]+\")
    :okay!)
  ;; => :okay
  "
  {:arglists '([name docstring? meta? arglist & body])}
  [& args]
  (let [data (s/conform :meander.syntax.beta/defsyntax-args args)]
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
        ;; find macros (in meander.match.beta) are expanded in
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

                 (defmethod meander.syntax.beta/expand-seq '~q-sym [[_# ~@arglist] _#]
                   (~sym ~@arglist))))
             (in-ns 'meander.syntax.beta)))
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
