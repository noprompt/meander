(ns ^:no-doc meander.syntax.epsilon
  #?(:clj
     (:require [clojure.core.specs.alpha :as core.specs]
               [clojure.set :as set]
               [clojure.spec.alpha :as s]
               [clojure.string :as string]
               [meander.syntax.specs.epsilon :as m.syntax.specs]
               [meander.util.epsilon :as r.util])
     :cljs
     (:require [cljs.core.specs.alpha :as core.specs]
               [cljs.spec.alpha :as s :include-macros true]
               [clojure.set :as set]
               [clojure.string :as string]
               [meander.syntax.specs.epsilon :as m.syntax.specs]
               [meander.util.epsilon :as r.util]
               [goog.object]))
  #?(:cljs
     (:require-macros [meander.syntax.epsilon])))

#?(:clj (set! *warn-on-reflection* true))

(defn node?
  "true if x is an AST node."
  [x]
  (s/valid? :meander.syntax.epsilon/node x))

(defn tag
  "Return the tag of node."
  [node]
  (s/assert :meander.syntax.epsilon/node node)
  (:tag node))

(defn any-node?
  [x]
  (s/valid? :meander.syntax.epsilon.node/any x))

(defn lvr-node?
  [x]
  (s/valid? :meander.syntax.epsilon.node/lvr x))

(defn mvr-node?
  [x]
  (s/valid? :meander.syntax.epsilon.node/mvr x))

(defn variable-node?
  [x]
  (or (mvr-node? x) (lvr-node? x)))

(defn ref-node?
  "true if x is a :ref node, false otherwise."
  [x]
  (s/valid? :meander.syntax.epsilon.node/ref x))

(defn with-node? [x]
  (s/valid? :meander.syntax.epsilon.node/with x))

(defn partition-node? [x]
  (s/valid? :meander.syntax.epsilon.node/partition x))

(defn cat-node? [x]
  (and (map? x) (= (get x :tag) :cat)))

(defn empty-cat-node? [x]
  (and (= :cat (:tag x))
       (not (seq (:elements x)))))

(defn tail-node? [x]
  (and (= :tail (:tag x))
       (some? (:pattern x))))

(defn map-node? [x]
  (and (map? x) (= (get x :tag) :map)))

(defn mut-node? [x]
   (and (map? x) (= (get x :tag) :mut)))

(defn set-node? [x]
  (and (map? x) (= (get x :tag) :set)))

(defn vec-node? [x]
  (and (map? x) (= (get x :tag) :vec)))

(defn prt-node? [x]
  (and (map? x) (= (get x :tag) :prt)))

(defn seq-node? [x]
  (and (map? x) (= (get x :tag) :seq)))

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

(defmulti min-length
  "The minimum possible length the pattern described by `node` can be.
  Note, this multimethod will throw an error whenever `node` does not
  have a method to handle it. This behavior is intentional as the
  implementations should only exist for things which have can have
  length. The `min-length?` predicate can be used to detect if `node`
  implements `min-length`."
  {:arglists '([node])}
  #'tag)

(defn min-length?
  "true if `x` implements `min-length`, false otherwise."
  [x]
  (and (node? x)
       (contains? (methods min-length) (tag x))))

(defmulti max-length
  "The maximum possible length the pattern described by `node` can
  be. Note, this multimethod will throw an error whenever `node` does
  not have a method to handle it. This behavior is intentional as the
  implementations should only exist for things which have can have
  length. The `max-length?` predicate can be used to detect if `node`
  implements `max-length`."
  {:arglists '([node])}
  #'tag)

(defn max-length?
  "true if `x` implements `max-length`, false otherwise."
  [x]
  (and (node? x)
       (contains? (methods max-length) (tag x))))

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

(defmulti search?
  "true if node represents a search, false otherwise."
  {:arglists '([node])}
  #'tag)

(defn unparse-dispatch
  {:private true}
  [node]
  (if (contains? node ::original-form)
    ::original-form
    (tag node)))

(defmulti unparse
  "In pre-order fashion rewrite a node into a Clojure form."
  {:arglists '([node])}
  #'unparse-dispatch)

(defmethod unparse ::original-form
  [node]
  (::original-form node))

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
    (set/union (get vars :lvr) (get vars :mvr))))

(defn memory-variables
  "Return all :mvr nodes in node."
  [node]
  (s/assert :meander.syntax.epsilon/node node)
  (get (variables* node) :mvr))

(defn logic-variables
  "Return all :lvr nodes in node."
  [node]
  (s/assert :meander.syntax.epsilon/node node)
  (get (variables* node) :lvr))

(defn references
  "Return all :ref nodes in node."
  [node]
  (get (variables* node) :ref))

(defn mutable-variables
  "Return all :mut nodes in node."
  [node]
  (s/assert :meander.syntax.epsilon/node node)
  (get (variables* node) :mut))

(defn top-level
  [node]
  (case (tag node)
    (:cnj :dsj :wth)
    (mapcat top-level (children node))
    ;; else
    [node]))

;; ---------------------------------------------------------------------
;; Parse

(declare parse)

(def default-env
  {::expander-registry {}
   ::parser-registry {}})

(defonce global-expander-registry
  (atom {}))

(defn register-expander
  [symbol f]
  {:pre [(symbol? symbol)
         (or (fn? f)
             (and (var? f) (fn? (deref f))))]}
  (swap! global-expander-registry assoc symbol f)
  nil)

(defonce global-parser-registry
  (atom {}))

(defn register-parser
  [symbol f]
  {:pre [(symbol? symbol)
         (or (fn? f)
             (and (var? f) (fn? (deref f))))]}
  (swap! global-parser-registry assoc symbol f)
  nil)

;;; Syntax expansion

(defn expander-registry
  "Return the `::expander-registry` of the environment `env` or `nil`
  if it cannot be found."
  [env]
  (let [x (get env ::expander-registry)]
    (if (map? x)
      x)))

(defn resolve-expander
  "Return the `::expander` associated with `sym` with respect to the
  environment `env`."
  [sym env]
  (let [x (get (expander-registry env) (r.util/expand-symbol sym env))]
    (if (fn? x)
      x)))

(defn expand-form
  "Expand `form` with respect to `env` if possible. Returns the result
  of expanding `form` or `form` unchanged."
  [form env]
  (if (seq? form)
    (let [head (first form)]
      (if (symbol? head)
        (let [expander (resolve-expander head env)]
          (if (fn? expander)
            (expander form env)
            form))
        form))
    form))

;;; Syntax parsing

(defn parser-registry
  "Return the `::parser-registry` of the environment `env` or `nil` if
  it cannot be found."
  [env]
  (let [x (get env ::parser-registry)]
    (if (map? x)
      x)))

(defn resolve-parser
  "Return the `::parser` associated with `sym` with respect to the
  environment `env`."
  [sym env]
  (let [x (get (parser-registry env) (r.util/expand-symbol sym env))
        x (if (var? x)
            (deref x)
            x)]
    (if (fn? x)
      x)))

(defn parse-all
  "Apply `parse` to all forms in the sequence `forms`."
  [forms env]
  (map (fn [form] (parse form env)) forms))

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

(defn prt-append
  {:private true}
  [prt node]
  (cond
    (partition-node? prt)
    (if (partition-node? (:right prt))
      (update prt :right prt-append node)
      (if (empty-cat-node? (:right prt))
        (assoc prt :right node)
        (assoc prt :right
               {:tag :prt
                :left (:right prt)
                :right node})))

    (empty-cat-node? prt)
    {:tag :prt
     :left node
     :right {:tag :cat
             :elements []}}))

(defmulti expand-seq
  {:arglists '([seq env])}
  (fn [xs env]
    (if (seq? xs)
      (let [x (first xs)]
        (if (symbol? x)
          (r.util/expand-symbol x env)
          ::default))
      ::default)))

(defmethod expand-seq :default
  [xs env]
  xs)

(defn parse-contain
  {:private true}
  [xs env]
  (if (and (seq? xs)
           (= (first xs)
              'meander.syntax.epsilon/$))
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

(swap! global-parser-registry assoc `$ parse-contain)

(defn parse-with
  {:private true}
  [xs env]
  {:pre [(seq? xs)]}
  (let [bindings (nth xs 1)]
    (if (and (vector? bindings)
             (even? (count bindings))
             (every? m.syntax.specs/reference-symbol? (take-nth 2 bindings)))
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
                 :meta (meta xs)})))))

(swap! global-parser-registry assoc `with parse-with)

(defn parse-as
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
          (let [;; Check for illegal :as pattern.
                as-result (parse-as xs* env)]
            (case (nth as-result 0)
              (:failure :success)
              [:failure ":as pattern may only occur once"]
              ;; else
              [:success xs* as-node]))
          ;; else
          [:failure ":as pattern must be a logic variable or memory variable"]))
      [:nothing xs nil])))

(defn parse-&
  {:private true}
  [xs env]
  (let [c (count xs)
        &-index (- c 2)]
    (if (and (<= 2 c)
             (= (nth xs &-index) '&))
      (let [xs* (take &-index xs)
            &-pattern (last xs)
            &-node (parse &-pattern env)]
        (let [;; Check for illegal :as pattern.
              as-result (parse-as xs* env)]
          (case (nth as-result 0)
            (:failure :success)
            [:failure "& pattern must appear be before :as pattern"]

            ;; else
            (let [;; Check for illegal & pattern.
                  &-result (parse-& xs* env)]
              (case (nth &-result 0)
                (:failure :sucess)
                [:failure "& pattern may only occur once"]

                ;; else
                [:success xs* &-node])))))
      [:success xs nil])))

(defn parse-sequential
  "Used by `parse-seq-no-head` and `parse-vector` to parse their
  `:prt` and `:as` nodes."
  {:private true}
  [xs env]
  ;; Check for :as ?x or :as !xs
  (let [as-result (parse-as xs env)]
    (case (nth as-result 0)
      :failure
      (throw (ex-info (nth as-result 1)
                      {:form xs
                       :meta (meta xs)}))

      (:success :nothing)
      (let [[_ xs* as-node] as-result
            ;; Check for & ?x or & !xs
            &-result (parse-& xs* env)]
        (case (nth &-result 0)
          :failure
          &-result

          (:success :nothing)
          (let [[_ xs** rest-node] &-result
                prt (expand-prt (parse-all xs** env))
                prt (if rest-node
                      (prt-append prt {:tag :tail
                                       :pattern rest-node})
                      prt)]
            [:success prt as-node]))))))

(defn parse-seq-no-head
  {:private true}
  [xs env]
  (let [result (parse-sequential xs env)]
    (case (nth result 0)
      :failure
      (let [[_ error-message] result]
        (throw (ex-info error-message
                        {:form xs
                         :meta (meta xs)})))

      :success
      (let [[_ prt as-node] result]
        {:tag :seq
         :prt prt
         :as as-node}))))

(defn parse-seq
  "Parses a seq? into a :meander.syntax.epsilon/node.

  seqs? of the following form are handled specially, all other seqs
  are parsed as :seq nodes.

    (quote <form>)
    (clojure.core/unquote <form>)
    (clojure.core/unquote-splicig <form>)
    (<symbol*> <form_0> ... <form_n>)

  where symbol* is a fully qualified symbol with respect to the
  current namespace."
  [xs env]
  (let [x (first xs)]
    (if (symbol? x)
      (case x
        quote
        {:tag :quo
         :form (second xs)}

        clojure.core/unquote
        {:tag :unq
         :expr (second xs)}

        clojure.core/unquote-splicing
        {:tag :uns
         :expr (second xs)}

        ;; else
        (let [xs* (expand-form xs env)]
          (if (= xs* xs)
            ;; Syntax expansion failed, try to parse special form.
            (let [head (first xs)
                  parser (if (symbol? head)
                           (resolve-parser head env))]
              (if (fn? parser)
                (let [node (parser xs env)]
                  (if (node? node)
                    ;; Special form, return the node.
                    (assoc node ::original-form xs)
                    (throw (ex-info ":meander.syntax.epsilon/parse-syntax function must return a :meander.syntax.epsilon/node"
                                    {:form xs
                                     :env env}))))
                ;; Not a special form, parse as ordinary seq pattern.
                (parse-seq-no-head xs env)))
            ;; Syntax expansion successful, recursively parse the
            ;; result.
            (assoc (parse xs* env) ::original-form xs))))
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
  {:arglists '([val-of-js-value env])
   :private true}
  [x env]
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
                        [(parse k* env) (parse v env)])))
                   x)}))

(defn parse-vector
  {:private true}
  [v env]
  (let [result (parse-sequential v env)]
    (case (nth result 0)
      :failure
      (let [[_ error-message] result]
        (throw (ex-info error-message
                        {:form v
                         :meta (meta v)})))

      :success
      (let [[_ prt as-node] result]
        {:tag :vec
         :prt prt
         :as as-node}))))

(defn parse-map
  {:private true}
  [m env]
  (if (and (map? m)
           (not (record? m)))
    (let [as (if-some [[_ y] (find m :as)]
               (if (or (m.syntax.specs/logic-variable-symbol? y)
                       (m.syntax.specs/memory-variable-symbol? y))
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
       :elements (parse-all (seq s) env)})
    (parse s env)))

(defn parse
  "Parse `form` into an abstract syntax tree (AST) optionally with
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
  ([form]
   (parse form {::expander-registry @global-expander-registry
                ::parser-registry @global-parser-registry}))
  ([form env]
   (let [node (cond
                (seq? form)
                (parse-seq form env)

                (vector? form)
                (parse-vector form env)

                (and (map? form)
                     (not (record? form)))
                (parse-map form env)

                (set? form)
                (parse-set form env)

                (symbol? form)
                (parse-symbol form)

                #?@(:clj [(r.util/js-value? form)
                          (parse-js-value (r.util/val-of-js-value form) env)])

                :else
                {:tag :lit
                 :value form})]
     (if-some [meta (meta form)]
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

;; :cat

(defmethod ground? :cat [node]
  (every? ground? (:elements node)))

(defmethod children :cat [node]
  (:elements node))

(defmethod max-length :cat [node]
  (count (:elements node)))

(defmethod min-length :cat [node]
  (count (:elements node)))

(defmethod unparse :cat [node]
  (apply list (map unparse (:elements node))))

(defmethod search? :cat [node]
  (boolean (some search? (:elements node))))

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
  (let [p (unparse (:prt node))]
    #?(:clj
       (r.util/make-js-value (vec p))
       :cljs
       (into-array p))))

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
     (let [m (reduce-kv
                (fn [m k v]
                  (assoc m (unparse k) (unparse v)))
                {}
                (:object node))]
       (r.util/make-js-value m))
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
            [rest-map])
          (if-some [as (:as node)]
            [as])))

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
        (if (tail-node? (:right node))
          right
          `(~'. ~@right)))))

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

;; :set

(defmethod children :set [node]
  (concat (:elements node)
          (if-some [rest-set (:rest node)]
            [rest-set])
          (if-some [as (:as node)]
            [as])))

(defmethod ground? :set [node]
  (every? ground? (:elements node)))

(defmethod unparse :set [node]
  (cond-> (set (map unparse (:elements node)))
    (some? (:as node))
    (conj (vary-meta (unparse (:as node)) assoc :as true))

    (some? (:rest node))
    (conj (vary-meta (unparse (:rest node)) assoc :tag '&))))

(defmethod search? :set [node]
  (not (ground? node)))

;; seq

(defmethod children :seq [node]
  (let [prt (:prt node)]
    (if-some [as (:as node)]
      [prt as]
      [prt])))

(defmethod ground? :seq [node]
  (and (ground? (:prt node))
       (nil? (:as node))))

(defmethod unparse :seq [node]
  (let [prt-forms (unparse (:prt node))]
    (if-some [as (:as node)]
      (concat prt-forms (list (unparse as)))
      prt-forms)))

(defmethod search? :seq [node]
  (search? (:prt node)))

(defmethod min-length :seq [node]
  (min-length (:prt node)))

(defmethod max-length :seq [node]
  (max-length (:prt node)))

;; :tail

(defmethod children :tail [node]
  [(:pattern node)])

(defmethod ground? :tail [node]
  (ground? (:pattern node)))

(defmethod unparse :tail [node]
  (list '& (unparse (:pattern node))))

(defmethod search? :tail [node]
  (search? (:pattern node)))

;; To compute the `min-length` and `max-length` for `:tail` depends on
;; the pattern it originated from. When the pattern looks similar to
;;
;;     (_ ... & (1 2 3))
;;     ---------^^^^^^
;;
;; where the underlined pattern is the `:pattern` of the `:tail`, then
;; `min-length` and `max-length` can be derived from this pattern. When
;; the pattern looks something like
;;
;;     (_ ... & ?x)
;;     ---------^^
;;
;; where the underlined pattern is the `:pattern` of the `:tail, then
;; we fail back using `0` and `##Inf` for `min-length` and
;; `max-length` respectively.

(defmethod min-length :tail [node]
  (let [pattern (:pattern node)]
    (if (min-length? pattern)
      (min-length pattern)
      0)))

(defmethod max-length :tail [node]
  (let [pattern (:pattern node)]
    (if (max-length? pattern)
      (max-length pattern)
      ##Inf)))

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
  (let [prt (:prt node)]
    (if-some [as (:as node)]
      [prt as]
      [prt])))

(defmethod ground? :vec [node]
  (and (ground? (:prt node))
       (nil? (:as node))))

(defmethod min-length :vec [node]
  (min-length (:prt node)))

(defmethod max-length :vec [node]
  (max-length (:prt node)))

(defmethod unparse :vec [node]
  (let [vec-form (vec (unparse (:prt node)))]
    (if-some [as (:as node)]
      (conj vec-form :as (unparse (:as node)))
      vec-form)))

(defmethod search? :vec [node]
  (search? (:prt node)))

;; wth

(defmethod children :wth [node]
  [(:body node)])

(defmethod ground? :wth [node]
  (ground? (:body node)))

(defmethod unparse :wth [node]
  `(with [~@(mapcat (juxt
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
  (walk (fn [node]
          (postwalk f node))
        (fn [node]
          (let [node* (f node)]
            (if (reduced? node*)
              (unreduced node*)
              node*)))
        node))

(defn prewalk
  [f node]
  (let [x (f node)]
    (if (reduced? x)
      (unreduced x)
      (walk (fn [node] (prewalk f node)) identity x))))

(defn prewalk-replace
  "Same as clojure.walk/prewalk-replace but for AST nodes."
  [smap node]
  (prewalk (fn [x] (if (contains? smap x) (smap x) x)) node))

(defn postwalk-replace
  "Same as clojure.walk/postwal-replace but for AST nodes."
  [smap node] (postwalk (fn [x] (if (contains? smap x) (smap x) x)) node))

(defmethod walk :cat [inner outer node]
  (outer (assoc node :elements (mapv inner (:elements node)))))

(defmethod walk :ctn [inner outer node]
  (outer (assoc node :pattern (inner (:pattern node)))))

(defmethod walk :jsa [inner outer node]
  (outer (assoc node :prt (inner (:prt node)))))

(defmethod walk :jso [inner outer node]
  (outer (assoc node :object (reduce
                              (fn [m [k-node v-node]]
                                (assoc m (inner k-node) (inner v-node)))
                              {}
                              (:object node)))))

(defmethod walk :map [inner outer node]
  (outer (assoc node
                :rest-map (if-some [rest-map (:rest-map node)]
                            (inner rest-map))
                :map (reduce
                      (fn [m [k-node v-node]]
                        (assoc m (inner k-node) (inner v-node)))
                      {}
                      (:map node)))))

(defmethod walk :prt [inner outer node]
  (outer (assoc node
                :left (inner (:left node))
                :right (inner (:right node)))))

(defmethod walk :rp* [inner outer node]
  (outer (assoc node :cat (inner (:cat node)))))

(defmethod walk :rp+ [inner outer node]
  (outer (assoc node :cat (inner (:cat node)))))

(defmethod walk :rpl [inner outer node]
  (outer (assoc node :cat (inner (:cat node)))))

(defmethod walk :rpm [inner outer node]
  (outer (assoc node :cat (inner (:cat node)))))

(defmethod walk :set [inner outer node]
  (outer (assoc node
                :rest (if-some [rest-set (:rest node)]
                        (inner rest-set))
                :elements (mapv inner (:elements node)))))

(defmethod walk :seq [inner outer node]
  (outer (assoc node :prt (inner (:prt node)))))

(defmethod walk :tail [inner outer node]
  (outer (assoc node :pattern (inner (:pattern node)))))

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
;; Other useful utilities

(defn make-variable-rename-map
  [node]
  (reduce
   (fn [index [i node]]
     (if (variable-node? node)
       (if (contains? index node)
         index
         (assoc index node (assoc node :symbol (symbol (str "?v__" i)))))
       index))
   {}
   (map vector (range) (subnodes node))))

(defn genref
  []
  {:tag :ref
   :symbol (gensym "%r__")})

(defn abstract
  {:style/indent 1}
  ([node]
   (let [ref (genref)]
     {:tag :wth
      :bindings [{:ref ref
                  :pattern node}]
      :body ref}))
  ([node f]
   (let [ref (genref)]
     {:tag :wth
      :bindings [{:ref ref
                  :pattern node}]
      :body (f ref)})))

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
                  (let [bindings* (run!
                                   (fn [binding]
                                     (let [pattern* (consolidate-with (get binding :pattern))]
                                       (if (with-node? pattern*)
                                         (do (vswap! state into (get pattern* :bindings))
                                             (vswap! state conj (assoc binding :pattern (get pattern* :body))))
                                         (vswap! state conj binding))))
                                   (get node :bindings))]
                    (do (vswap! state into bindings*)
                        (:body node)))
                  node))
              node)]
    {:tag :wth
     :bindings (deref state)
     :body node}))

(defn make-ref-map
  "If node is a node repesenting a with pattern, return a map from
  reference to pattern node derived from it's bindings, otherwise
  return an empty map."
  [node]
  (if (with-node? node)
    (into {} (map (juxt :ref :pattern)) (:bindings node))
    {}))

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
             (assoc node :body (substitute-refs body ref-map)))
           node))

        :else
        node))
    node)))

(defn refs-in-use*
  "Returns the set of `:ref` nodes in use with respect to
  `ref-map`."
  [node ref-map]
  (cond
    (empty? ref-map)
    #{}

    (and (ref-node? node)
         (contains? ref-map node))
    (set/union #{node}
               (refs-in-use* (get ref-map node)
                             (dissoc ref-map node)))

    (with-node? node)
    (let [ref-map* (reduce
                    (fn [ref-map* [other-ref _]]
                      (dissoc ref-map* other-ref))
                    ref-map
                    (make-ref-map node))]
      (refs-in-use* (get node :body) ref-map*))

    :else
    (reduce
     (fn [in-use child-node]
       (set/union in-use (refs-in-use* child-node ref-map)))
     #{}
     (children node))))

(defn refs-in-use
  "Returns the set of `:ref` nodes bound by `with-node` that are in
  use in its body."
  [with-node]
  {:pre [(with-node? with-node)]}
  (let [ref-map (make-ref-map with-node)
        used-in-body (refs-in-use* (get with-node :body) ref-map)]
    (reduce
     (fn [used ref-node]
       (set/union used (refs-in-use* (get ref-map ref-node) ref-map)))
     used-in-body
     used-in-body)))

(defn ref-deps
  ([ref-map]
   (ref-deps ref-map (keys ref-map)))
  ([ref-map refs]
   (into {} (map
             (fn [ref]
               [ref (refs-in-use* (get ref-map ref) ref-map)]))
         refs)))

(defn substitute-acyclic-refs-in-ref-map
  "Construct a new ref-map `ref-map*` for which each acyclic ref,
  `ref`, in use in `ref-map`, and for each acyclic ref occuring in
  `ref`s `pattern`, `other-ref`, substitute `other-ref` with its
  pattern."
  ([ref-map]
   (substitute-acyclic-refs-in-ref-map ref-map (ref-deps ref-map)))
  ([ref-map ref-deps]
   (reduce
    (fn [ref-map* [ref deps]]
      (if (or (contains? deps ref)
              (empty? deps)
              (not (contains? ref-deps ref)))
        ;; If the ref is cyclic, has no dependencies, or is not a
        ;; member of `ref-deps` e.g. not significant to this process,
        ;; continue with the ref-map as is.
        ref-map*
        (reduce
         (fn [ref-map* [other-ref node]]
           (if (= other-ref ref)
             ref-map*
             (assoc ref-map* other-ref (substitute-refs node ref-map*))))
         ref-map*
         ref-map*)))
    ref-map
    ref-deps)))

(defn bindings-from-ref-map
  [ref-map]
  (map
   (fn [[ref pattern]]
     {:ref ref
      :pattern pattern})
   ref-map))

(defn substitute-acyclic-refs
  [with-node]
  (let [ref-map (make-ref-map with-node)
        in-use (refs-in-use with-node)
        ref-deps (ref-deps ref-map in-use)
        ref-map* (substitute-acyclic-refs-in-ref-map ref-map ref-deps)
        acyclic-refs (keep
                      (fn [[ref deps]]
                        (when (not (contains? deps ref))
                          ref))
                      ref-deps)
        body* (substitute-refs (get with-node :body)
                               (select-keys ref-map* acyclic-refs))
        bindings* (bindings-from-ref-map ref-map*)]
    (merge with-node
           {:body body*
            :bindings bindings*})))

(defn literal?
  "true if node is ground and does not contain :map or :set subnodes,
  false otherwise.

  The constraint that node may not contain :map or :set subnodes is
  due to the semantics of map and set patterns: they express submap
  and subsets respectively. Compiling these patterns to literals as
  part of an equality check would result in false negative matches."
  [node]
  (and (ground? node)
       (not-any? (comp #{:map :unq :set} tag)
                 (subnodes node))))

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
  (s/assert :meander.syntax.epsilon.node/partition node)
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


(defn lit-form
  [node]
  (case (tag node)
    :cat
    (map lit-form (:elements node))

    :jsa
    (let [form (lit-form (:prt node))]
      #?(:clj
         (r.util/make-js-value (vec form))
         :cljs
         (into-array form)))

    :lit
    (:value node)

    :map
    (into {}
          (map (fn [[k v]]
                 [(lit-form k) (lit-form v)]))
          (:map node))

    :prt
    (concat (lit-form (:left node))
            (lit-form (:right node)))

    :quo
    (:form node)

    :vec
    (into [] (lit-form (:prt node)))

    :seq
    (if-some [l (seq (lit-form (:prt node)))]
      l
      ())

    :set
    (into #{} (map lit-form (:elements node)))))

(defn literal-keys [map-node]
  {:pre [(map-node? map-node)]}
  (filter literal? (keys (get map-node :map))))

(defn non-literal-keys [map-node]
  {:pre [(map-node? map-node)]}
  (remove literal? (keys (get map-node :map))))

(defn literal-elements [node]
  {:pre [(or (set-node? node)
             (cat-node? node))]}
  (filter literal? (get node :elements)))

(defn non-literal-elements [node]
  {:pre [(or (set-node? node)
             (cat-node? node))]}
  (remove literal? (get node :elements)))

;; ---------------------------------------------------------------------
;; defsyntax

(def ^{:dynamic true}
  *form* nil)

(def ^{:dynamic true}
  *env* {})

(defmacro defsyntax
  {:arglists '([name doc-string? attr-map? [params*] prepost-map? body]
               [name doc-string? attr-map? ([params*] prepost-map? body) + attr-map?])
   :style/indent :defn}
  [& defn-args]
  (let [conformed-defn-args (s/conform ::m.syntax.specs/defsyntax-args defn-args)
        defn-args (next defn-args)
        docstring (:docstring conformed-defn-args)
        defn-args (if docstring
                    (next defn-args)
                    defn-args)
        meta (:meta conformed-defn-args)
        defn-args (if meta
                    (next defn-args)
                    defn-args)
        meta (if docstring
               (merge {:doc docstring} meta)
               meta)
        variadic? (= (first (:fn-tail conformed-defn-args))
                     :arity-n)
        arglists (if variadic?
                   (map first defn-args)
                   (list (first defn-args)))
        meta (assoc meta :arglists (list 'quote arglists))
        fn-name (:fn-name conformed-defn-args)
        meta (merge meta (clojure.core/meta fn-name))
        fn-name (with-meta fn-name meta)
        body (if variadic?
               defn-args
               (list defn-args))
        body (map
              (fn [fn-spec]
                `(~(first fn-spec)
                  (let [~'&form *form*
                        ~'&env *env*]
                    ~@(rest fn-spec))))
              body)
        qfn-name (symbol (name (ns-name *ns*))
                         (name fn-name))
        expander-definition-body-form
        `(do (def ~fn-name (fn ~@body))
             (let [expander# (fn expander# [form# env#]
                               (binding [*form* form#
                                         *env* env#]
                                 (apply ~fn-name (rest form#))))]
               (swap! global-expander-registry assoc '~qfn-name expander#)))]
    ;; When defining new syntax in ClojureScript it is also necessary
    ;; to define the methods which parse and expand the syntax in
    ;; Clojure. This is because the match, search, and find macros (in
    ;; meander.match.epsilon) are expanded in Clojure which, in turn,
    ;; rely on these methods.
    #?(:clj
       (when-some [cljs-ns (:ns &env)]
         ;; Visit the namespace.
         (in-ns (:name cljs-ns))
         ;; Try to require the namespace or everything in
         ;; :requires. Both operations can fail.
         (try
           (require (:name cljs-ns))
           (catch Exception _
             (try
               (doseq [[alias ns-name] (:requires cljs-ns)]
                 (if (= alias ns-name)
                   (require ns-name)
                   (require [ns-name :as alias])))
               (catch Exception _))))))
    `(do ~expander-definition-body-form
         (var ~fn-name))))

#?(:clj
   (s/fdef defsyntax
     :args ::m.syntax.specs/defsyntax-args))
