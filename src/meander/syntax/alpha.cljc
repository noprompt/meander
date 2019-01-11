(ns meander.syntax.alpha
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as s.gen]
            [clojure.string :as string]
            [clojure.walk :as walk]
            [meander.util.alpha :as util]))


#?(:clj (set! *warn-on-reflection* true))


;; ---------------------------------------------------------------------
;; Grammar


(defn conformer*
  "Like clojure.spec.alpha/conformer but returns a conformer which
  applies f to some value x and returns x if the result is truthy
  and :clojure.spec.alpha/invalid if falsey."
  {:private true}
  ([f]
   (s/conformer
    (fn [x]
      (if (f x)
        x
        ::s/invalid))))
  ([f unf]
   (s/conformer
    (fn [x]
      (if (f x)
        x
        ::s/invalid))
    unf))) 


(s/def :meander.syntax.alpha/literal
  any?)

#?(:clj
   (defn re-matches? [ re s]
     (.matches (re-matcher re s)))
   :cljs
   (defn re-matches? [re s]
     (.test re s)))

(defn any-symbol?
  "true if x is a symbol beginning with _."
  [x]
  (and (simple-symbol? x)
       (re-matches? #"_.*" (name x))))


(s/def :meander.syntax.alpha/any
  (s/with-gen
    (conformer* any-symbol? identity)
    (fn []
      (s.gen/fmap
       (fn [sym]
         (symbol (str "_" (name sym))))
       (s.gen/symbol)))))


(defn quote-form?
  "true if x is in the form (quote x)."
  [x]
  (and (seq? x)
       (= (first x) 'quote)
       (= (count x) 2)))


(s/def :meander.syntax.alpha/quote
  (s/cat :quote #{'quote}
         :form any?))


(defn unquote-form?
  "true if x is in the form (clojure.core/unquote x)."
  [x]
  (and (seq? x)
       (= (first x) 'clojure.core/unquote)
       (= (count x) 2)))


(s/def :meander.syntax.alpha/unquote
  (s/cat :unquote #{'clojure.core/unquote}
         :expr any?))


(defn unquote-splicing-form?
  "true if x is in the form (clojure.core/unquote-splicing x)."
  [x]
  (and (seq? x)
       (= (first x) 'clojure.core/unquote-splicing)
       (= (count x) 2)))


(s/def :meander.syntax.alpha/unquote-splicing
  (s/cat :unquote #{'clojure.core/unquote-splicing}
         :form any?))


(s/fdef logic-variable-form?
  :args (s/cat :x any?)
  :ret boolean?)


(defn logic-variable-form?
  "true if x is in the form of a logic variable i.e. a simple symbol
  with a name beginning with \\?."
  [x]
  (and (simple-symbol? x)
       (re-matches? #"\?.+" (name x))))


(s/def :meander.syntax.alpha/logic-variable
  (s/with-gen
    (conformer* logic-variable-form? identity)
    (fn []
      (s.gen/fmap
       (fn [x]
         (symbol (str \? (name x))))
       (s/gen simple-symbol?)))))


(defn memory-variable-form?
  "true if x is in the form of a memory variable i.e. a simple symbol
  with a name beginning with \\!."
  [x]
  (and (simple-symbol? x)
       (re-matches? #"!.+" (name x))))


(s/def :meander.syntax.alpha/memory-variable
  (s/with-gen
    (conformer* memory-variable-form? identity)
    (fn []
      (s.gen/fmap
       (fn [x]
         (symbol (str \! (name x))))
       (s/gen simple-symbol?)))))


(defn partition-symbol?
  [x]
  (= x '.))


(defn zero-or-more-symbol?
  [x]
  (and (simple-symbol? x)
       (re-matches? #"\.\.\.+" (name x))))


(defn n-or-more-symbol?
  [x]
  (and (simple-symbol? x)
       (re-matches? #"\.\.(\d+)?" (name x))))


(defn pattern-op-dispatch
  "Dispatch function for pattern-op."
  [x]
  (if (seq? x)
    (let [y (first x)]
      (if (symbol? y)
        y))))


(defmulti pattern-op
  {:arglists '([seq])}
  #'pattern-op-dispatch)


(defmethod pattern-op :default
  [_]
  (s/conformer
   (fn [_]
     ;; This is wrapped in a do because spec considers the form
     ;; without it invalid.
     (do ::s/invalid))
   identity))


(s/def :meander.syntax.alpha.sequential/literal
  (s/and (s/with-gen
           (complement
            (some-fn partition-symbol?
                     zero-or-more-symbol?
                     n-or-more-symbol?))
           (fn []
             (s.gen/such-that
              (complement
               (some-fn partition-symbol?
                        zero-or-more-symbol?
                        n-or-more-symbol?))
              (s.gen/any))))
         :meander.syntax.alpha/literal))


(s/def :meander.syntax.alpha.sequential/subterm
  (s/or :quo :meander.syntax.alpha/quote
        :unq :meander.syntax.alpha/unquote
        :uns :meander.syntax.alpha/unquote-splicing
        :cnj :meander.syntax.alpha/and
        :dsj :meander.syntax.alpha/or
        :not :meander.syntax.alpha/not
        :let :meander.syntax.alpha/let
        :prd :meander.syntax.alpha/pred
        :grd :meander.syntax.alpha/guard
        :app :meander.syntax.alpha/app
        :usr (s/multi-spec pattern-op :usr)
        :seq :meander.syntax.alpha/seq
        :vec :meander.syntax.alpha/vector
        :set :meander.syntax.alpha/set
        :map :meander.syntax.alpha/map
        :any :meander.syntax.alpha/any
        :lvr :meander.syntax.alpha/logic-variable
        :mvr :meander.syntax.alpha/memory-variable
        :lit :meander.syntax.alpha.sequential/literal))


(s/def :meander.syntax.alpha/zero-or-more
  (s/with-gen
    (s/cat :items (s/* :meander.syntax.alpha.sequential/subterm)
           :dots zero-or-more-symbol?)
    (fn []
      (s.gen/fmap
       (fn [items]
         `(~@items ~'...))
       (s.gen/not-empty (s.gen/list (s/gen :meander.syntax.alpha.sequential/subterm)))))))

(s/def :meander.syntax.alpha/n-or-more
  (s/with-gen
    (s/cat :items (s/* :meander.syntax.alpha.sequential/subterm)
           :n (s/conformer
               (fn conform-n-or-more-n [x]
                 (if (n-or-more-symbol? x)
                   (if (= x '..)
                     nil
                     (util/parse-int
                      (nth (string/split (name "..2") #"\.+" 2) 1)))
                   ::s/invalid))))
    (fn []
      (s.gen/fmap
       (fn [[items ^long n]]
         `(~@items ~(symbol (str ".." (Math/abs n)))))
       (s.gen/tuple
        (s.gen/not-empty
         (s.gen/list (s/gen :meander.syntax.alpha.sequential/subterm)))
        (s.gen/int))))))


(s/def :meander.syntax.alpha/drop
  (s/with-gen
    (s/cat :any :meander.syntax.alpha/any
           :dots zero-or-more-symbol?)
    (fn []
      (s.gen/fmap
       (fn [sym]
         `(~sym ~'...))
       (s/gen :meander.syntax.alpha/any)))))


(s/def :meander.syntax.alpha/rest
  (s/with-gen
    (s/cat :mvr (s/or :mvr :meander.syntax.alpha/memory-variable)
           :dots zero-or-more-symbol?)
    (fn []
      (s.gen/fmap
       (fn [sym]
         `(~sym ~'...))
       (s/gen :meander.syntax.alpha/memory-variable)))))


(s/def :meander.syntax.alpha.sequential/item
  (s/alt :prt (s/cat :left (s/alt :drp :meander.syntax.alpha/drop
                                  :rst :meander.syntax.alpha/rest
                                  :rp* :meander.syntax.alpha/zero-or-more
                                  :rp+ :meander.syntax.alpha/n-or-more)
                     :right (s/? :meander.syntax.alpha.sequential/item))
         :prt (s/cat :left (s/alt :cat (s/* :meander.syntax.alpha.sequential/subterm))
                     :dot '#{.}
                     :right (s/? :meander.syntax.alpha.sequential/item))
         :prt (s/cat :left (s/alt :cat (s/* :meander.syntax.alpha.sequential/subterm))))) 


(s/def :meander.syntax.alpha/seq
  (s/and seq? :meander.syntax.alpha.sequential/item))


(s/def :meander.syntax.alpha/vector
  (s/and vector? :meander.syntax.alpha.sequential/item))


(s/def :meander.syntax.alpha/set
  (s/coll-of (s/or :quo :meander.syntax.alpha/quote
                   :unq :meander.syntax.alpha/unquote
                   :uns :meander.syntax.alpha/unquote-splicing
                   :cnj :meander.syntax.alpha/and
                   :dsj :meander.syntax.alpha/or
                   :not :meander.syntax.alpha/not
                   :let :meander.syntax.alpha/let
                   :prd :meander.syntax.alpha/pred
                   :grd :meander.syntax.alpha/guard
                   :app :meander.syntax.alpha/app
                   :usr (s/multi-spec pattern-op :usr)
                   :seq :meander.syntax.alpha/seq
                   :vec :meander.syntax.alpha/vector
                   :set :meander.syntax.alpha/set
                   :map :meander.syntax.alpha/map
                   :any :meander.syntax.alpha/any
                   :lvr :meander.syntax.alpha/logic-variable
                   :mvr :meander.syntax.alpha/memory-variable
                   :lit :meander.syntax.alpha.sequential/literal)
             :kind set?
             :into #{}))


(s/def :meander.syntax.alpha/map
  (s/with-gen
    (s/conformer
     (fn [x]
       (if (and (map? x)
                (not (record? x)))
         (transduce
          (map
           (fn [[k v]]
             [(s/conform :meander.syntax.alpha/term k)
              (s/conform :meander.syntax.alpha/term v)]))
          (fn
            ([] {})
            ([m] m)
            ([m [ck cv]]
             (if (or (= ck ::s/invalid)
                     (= cv ::s/invalid))
               (reduced ::s/invalid)
               (assoc m ck cv))))
          x)
         ::s/invalid))
     (fn [m]
       (transduce
        (map
         (fn [[ck cv]]
           [(s/unform :meander.syntax.alpha/term ck)
            (s/unform :meander.syntax.alpha/term cv)]))
        conj
        {}
        m)))
    (fn []
      (s.gen/map (s/gen :meander.syntax.alpha/term)
                 (s/gen :meander.syntax.alpha/term)))))


(s/def :meander.syntax.alpha/pred
  (s/with-gen
    (s/and seq?
           (s/cat :pred #{'pred}
                  :form any?
                  :terms (s/* :meander.syntax.alpha/term)))
    (fn []
      (s.gen/fmap
       (fn [x]
         (list 'pred x))
       (s.gen/any)))))


(s/def :meander.syntax.alpha/guard
  (s/with-gen
    (s/and seq?
           (s/cat :guard #{'guard}
                  :form any?))
    (fn []
      (s.gen/fmap
       (fn [x]
         (list 'guard x))
       (s.gen/any)))))


(s/def :meander.syntax.alpha/app
  (s/with-gen
    (s/and seq?
           (s/cat :app #{'app}
                  :form any?
                  :terms (s/* :meander.syntax.alpha/term)))
    (fn []
      (s.gen/fmap
       (fn [[x t]]
         (list 'app x))
       (s.gen/tuple
        (s.gen/any)
        (s/gen :meander.syntax.alpha/term))))))


(s/def :meander.syntax.alpha/and
  (s/with-gen
    (s/and seq?
           (s/cat :and #{'and}
                  :terms (s/* :meander.syntax.alpha/term)))
    (fn []
      (s.gen/fmap
       (fn [x]
         (list 'and x))
       (s.gen/any)))))


(s/def :meander.syntax.alpha/or
  (s/with-gen
    (s/and seq?
           (s/cat :or #{'or}
                  :terms (s/* :meander.syntax.alpha/term)))
    (fn []
      (s.gen/fmap
       (fn [x]
         (list 'or x))
       (s.gen/any)))))


(s/def :meander.syntax.alpha/not
  (s/with-gen
    (s/and seq?
           (s/cat :not #{'not}
                  :term :meander.syntax.alpha/term))
    (fn []
      (s.gen/fmap
       (fn [x]
         (list 'not x))
       (s.gen/any)))))

;; Borrowed from Emacs' pcase
;; https://www.gnu.org/software/emacs/manual/html_node/elisp/Pattern-matching-case-statement.html
(s/def :meander.syntax.alpha/let
  (s/with-gen
    (s/and seq?
           (s/cat :let #{'let*}
                  :binding :meander.syntax.alpha/term
                  :expr any?))
    (fn []
      (s.gen/fmap
       (fn [[lvr x]]
         (list 'let* lvr x))
       (s.gen/tuple
        (s/gen :meander.syntax.alpha/logic-variable)
        (s.gen/any))))))


(s/def :meander.syntax.alpha/term
  (s/or :quo :meander.syntax.alpha/quote
        :unq :meander.syntax.alpha/unquote
        :cnj :meander.syntax.alpha/and
        :dsj :meander.syntax.alpha/or
        :not :meander.syntax.alpha/not
        :let :meander.syntax.alpha/let
        :prd :meander.syntax.alpha/pred
        :grd :meander.syntax.alpha/guard
        :app :meander.syntax.alpha/app
        :usr (s/multi-spec pattern-op :usr)
        :seq :meander.syntax.alpha/seq
        :vec :meander.syntax.alpha/vector
        :set :meander.syntax.alpha/set
        :map :meander.syntax.alpha/map
        :any :meander.syntax.alpha/any
        :lvr :meander.syntax.alpha/logic-variable
        :mvr :meander.syntax.alpha/memory-variable
        :lit :meander.syntax.alpha/literal))


;; ---------------------------------------------------------------------
;; AST API


(s/def :meander.syntax.alpha/node
  (s/tuple :meander.syntax.alpha.node/tag
           any?))


(s/def :meander.syntax.alpha.node/tag
  keyword?)


(s/def :meander.syntax.alpha.node/any
  (s/tuple #{:any} any-symbol?))


(s/def :meander.syntax.alpha.node/lvr
  (s/tuple #{:lvr} :meander.syntax.alpha/logic-variable))


(s/def :meander.syntax.alpha.node/mvr
  (s/tuple #{:mvr} :meander.syntax.alpha/memory-variable))

(defn variable-node?
  [x]
  (s/valid? (s/or :lvr :meander.syntax.alpha.node/lvr
                  :mvr :meander.syntax.alpha.node/mvr)
            x))


(s/def :meander.syntax.alpha.node.dsj/terms
  (s/* :meander.syntax.alpha/node))


(s/def :meander.syntax.alpha.node/dsj
  (s/tuple #{:dsj} (s/keys :req-un [:meander.syntax.alpha.node.dsj/terms])))


(defn expand-usr-op-dispatch
  [[_ {op :op}]]
  op)

(defmulti expand-usr-op
  {:arglists '([usr-node])}
  #'expand-usr-op-dispatch)

(defmethod expand-usr-op :default
  [node] node)


(s/fdef parse
  :args (s/cat :x any?)
  :ret :meander.syntax.alpha/node)

(defn expand-usr-ops
  [node]
  (walk/prewalk
   (fn [x]
     (if (and (vector? x)
              (= (first x) :usr))
       (expand-usr-op x)
       x))
   node))

(defn parse
  [x]
  (let [data (s/conform :meander.syntax.alpha/term x)]
    (if (= data ::s/invalid)
      data
      (expand-usr-ops data))))

(defn node?
  [x]
  (s/valid? :meander.syntax.alpha/node x))


(defn any-node?
  [x]
  (s/valid? :meander.syntax.alpha.node/any x))


(defn dsj-node?
  [x]
  (s/valid? :meander.syntax.alpha.node/dsj x))


(defn lvr-node?
  [x]
  (s/valid? :meander.syntax.alpha.node/lvr x))


(defn mvr-node?
  [x]
  (s/valid? :meander.syntax.alpha.node/mvr x))


(s/fdef tag
  :args (s/cat :node :meander.syntax.alpha/node)
  :ret :meander.syntax.alpha.node/tag)


(defn tag
  [node]
  (s/assert :meander.syntax.alpha/node node)
  (first node))


(s/fdef unparse
  :args (s/cat :node :meander.syntax.alpha/node)
  :ret any?
  :fn (fn [{:keys [args ret]}]
        (= (parse ret) (:node args))))


(defn unparse-dispatch
  {:private true}
  [node]
  (s/assert :meander.syntax.alpha/node node)
  (tag node))


(defmulti unparse
  "In pre-order fashion rewrite a node into a Clojure form."
  {:arglists '([node])}
  #'unparse-dispatch)


(s/fdef data
  :args (s/cat :node :meander.syntax.alpha/node)
  :ret any?)


(defn data
  [node]
  (s/assert :meander.syntax.alpha/node node)
  (second node))

(s/fdef children
  :args (s/cat :node :meander.syntax.alpha/node)
  :ret (s/coll-of :meander.syntax.alpha/node
                  :kind sequential?
                  :into []))

(defn children-dispatch
  [node]
  (s/assert :meander.syntax.alpha/node node)
  (tag node))


(defmulti children
  "Return a sequence of all subnodes of node."
  {:arglists '([node])}
  #'children-dispatch)


(defmethod children :default
  [node] [])


(defn subnodes
  "Return a sequence of all subnodes of node."
  [node]
  (lazy-seq
   (cons node
         ((fn rec [node]
            (sequence
             (mapcat
              (fn [node]
                (lazy-seq (cons node (rec node)))))
             (children node)))
          node))))


(s/fdef proper-subnodes
  :args (s/cat :node :meander.syntax.alpha/node)
  :ret (s/coll-of :meander.syntax.alpha/node
                  :kind sequential?
                  :into #{})
  :fn (fn [{:keys [args ret]}]
        (not (contains? (:node args) ret))))


(defn proper-subnodes
  "Return the all subnodes in node excluding node."
  [node]
  (rest (subnodes node)))


(s/fdef variables
  :args (s/cat :node :meander.syntax.alpha/node)
  :ret (s/coll-of
        (s/or :lvr :meander.syntax.alpha.node/lvr
              :mvr :meander.syntax.alpha.node/mvr)
        :kind set?
        :into #{}))


(defn variables
  "Return all :lvr and :mvr nodes in node."
  [node]
  (s/assert :meander.syntax.alpha/node node)
  (into #{}
        (filter (comp #{:lvr :mvr} tag))
        (subnodes node)))


(defn memory-variables
  "Return all :mvr nodes in node."
  [node]
  (s/assert :meander.syntax.alpha/node node)
  (into #{}
        (filter (comp #{:mvr} tag))
        (subnodes node)))


(s/fdef ground?
  :args (s/cat :node :meander.syntax.alpha/node)
  :ret boolean?)


(defn ground?-dispatch
  {:private true}
  [node]
  (s/assert :meander.syntax.alpha/node node)
  (tag node))


(defmulti ground?
  "true if node is ground i.e. it contains no variables or is not a
  match operator."
  {:arglists '([node])}
  #'ground?-dispatch)


(s/fdef rank
  :args (s/cat :node :meander.syntax.alpha/node)
  :ret nat-int?)


(defn rank-dispatch
  {:private true}
  [node]
  (s/assert :meander.syntax.alpha/node node)
  (tag node))


(defmulti rank 
  ""
  {:arglists '([node])}
  #'rank-dispatch)


(def
  ^{:private true
    :tag clojure.lang.PersistentVector}
  TAG_RANK
  [:grd
   :mvr
   :any
   :lvr
   :quo
   :lit
   :let])


(defmethod rank :default [[tag _]]
  (.indexOf TAG_RANK tag))


(s/fdef max-length
  :args (s/cat :node :meander.syntax.alpha/node)
  :ret (s/or :nat nat-int?
             :inf #{##Inf}))


(defn max-length-dispatch
  {:private true}
  [node]
  (s/assert :meander.syntax.alpha/node node)
  (tag node))


(defmulti max-length
  ""
  {:arglists '([node])}
  #'max-length-dispatch)


(defn min-length-dispatch
  {:private true}
  [node]
  (s/assert :meander.syntax.alpha/node node)
  (tag node))


(defmulti min-length
  ""
  {:arglists '([node])}
  #'min-length-dispatch)


(s/fdef variable-length?
  :args (s/cat :node :meander.syntax.alpha/node)
  :ret boolean?)


(defn variable-length?
  "true if node may have a variable length."
  [node]
  (not (= (min-length node) (max-length node))))


(s/fdef search?
  :args (s/cat :node :meander.syntax.alpha/node)
  :ret boolean?)


(defn search?-dispatch
  {:private true}
  [node]
  (s/assert :meander.syntax.alpha/node node)
  (tag node))


(defmulti search?
  ""
  {:arglists '([node])}
  #'search?-dispatch)


;; ---------------------------------------------------------------------
;; AST Method implementations


;; :any

(defmethod ground? :any
  [_] false)


(defmethod unparse :any
  [_] '_)


(defmethod search? :any
  [_] false)


;; :app

(defmethod children :app
  [[_ {terms :terms}]]
  (vec terms))


(defmethod ground? :app
  [_] false)


(defmethod unparse :app
  [[_ {form :form terms :terms}]]
  `(~'app ~form ~@(map unparse terms)))


(defmethod search? :app
  [_] false)

;; :cat

(defmethod children :cat
  [[_ nodes]] nodes)


(defmethod ground? :cat
  [[_ nodes]] (every? ground? nodes))


(defmethod min-length :cat
  [[_ nodes]]
  (count nodes))


(defmethod max-length :cat
  [[_ nodes]]
  (count nodes))


(defmethod unparse :cat
  [[_ nodes]]
  (apply list (map unparse nodes)))


(defmethod search? :cat
  [[_ nodes]]
  (boolean (some search? nodes)))


;; :cnj

(defmethod children :cnj
  [[_ {nodes :terms}]] nodes)


(defmethod ground? :cnj
  [_] false)


(defmethod unparse :cnj
  [[_ {nodes :terms}]]
  `(~'and ~@(sequence (map unparse) nodes)))


(defmethod search? :cnj
  [[_ {nodes :terms}]]
  (boolean (some search? nodes)))


;; :drp

(defmethod ground? :drp
  [_] false)


(defmethod min-length :drp
  [_] 0)


(defmethod max-length :drp
  [_] ##Inf)


(defmethod unparse :drp
  [_] '(_ ...))


(defmethod search? :drp
  [_] false)

;; :dsj

(defmethod children :dsj
  [[_ {nodes :terms}]] nodes)


(defmethod ground? :dsj
  [_] false)


(defmethod unparse :dsj
  [[_ {nodes :terms}]]
  `(~'or ~@(sequence (map unparse) nodes)))


(defmethod search? :dsj
  [[_ {nodes :terms}]]
  (boolean (some search? nodes)))


;; :grd

(defmethod ground? :grd
  [_] false)


(defmethod unparse :grd
  [[_ {form :form}]]
  `(~'guard ~form))


(defmethod search? :grd
  [_] false)

;; :let

(defmethod children :let
  [[_ {binding :binding}]]
  [binding])


(defmethod ground? :let
  [_] false)


(defmethod unparse :let
  [[_ {binding :binding, expr :expr}]]
  `(~'let* ~(unparse binding) ~expr))


(defmethod search? :let
  [_] false)


;; :lit

(defmethod ground? :lit
  [_] true)


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


(defmethod unparse :lit
  [[_ lit]]
  (unparse-lit lit))


(defmethod search? :lit
  [_] false)

;; :lvr

(defmethod ground? :lvr
  [_] false)


(defmethod unparse :lvr
  [[_ sym]] sym)


(defmethod search? :lvr
  [_] false)

;; :map

(defmethod children :map
  [[_ map-data]]
  (mapcat identity map-data))


(defmethod ground? :map
  [[_ map-data]]
  (every?
   (fn [[k v]]
     (and (ground? k)
          (ground? v)))
   map-data))


(defmethod unparse :map
  [[_ map-data]]
  (reduce-kv
   (fn [m k v]
     (assoc m (unparse k) (unparse v)))
   {}
   map-data))


(defmethod search? :map
  [[_ map-data]]
  (boolean
   (some
    (fn [[k v]]
      (or (not (ground? k))
          (search? k)
          (search? v)))
    map-data)))

;; :mvr

(defmethod ground? :mvr
  [_] false)


(defmethod unparse :mvr
  [[_ sym]] sym)


(defmethod search? :mvr
  [_] false)


;; :not

(defmethod children :not
  [[_ {term :term}]]
  [term])


(defmethod ground? :not
  [_] false)


(defmethod unparse :not
  [[_ {term :term}]]
  `(~'not ~(unparse term)))


(defmethod search? :not
  [[_ {term :term}]]
  (search? term))


;; :prd

(defmethod children :prd
  [[_ {terms :terms}]]
  (vec terms))


(defmethod ground? :prd
  [_] false)


(defmethod unparse :prd
  [[_ {form :form, terms :terms}]]
  `(~'pred ~form ~@(map unparse terms)))


(defmethod search? :prd
  [[_ {terms :terms}]]
  (boolean (some search? terms)))

;; :prt

(defmethod children :prt
  [[_ {left :left, right :right}]]
  (if (some? right)
    [left right]
    [left]))


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
  [[_ {dot :dot, left :left, right :right}]]
  (apply list
    (concat (unparse left)
            (when (some? dot)
              (list dot))
            (when (some? right)
              (unparse right)))))


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

(defmethod ground? :quo
  [_] true)


(defmethod unparse :quo
  [[_ {form :form}]]
  `(quote ~form))


(defmethod search? :quo
  [_] false)


;; :rp*

(defmethod children :rp*
  [[_ {items :items}]]
  items)


(defmethod ground? :rp*
  [_] false)


(defmethod min-length :rp*
  [_] 0)


(defmethod max-length :rp*
  [_] ##Inf)


(defmethod unparse :rp*
  [[_ {items :items dots :dots}]]
  `(~@(sequence (map unparse) items) ~dots))


(defmethod search? :rp*
  [_] false)

;; :rp+


(defmethod children :rp+
  [[_ {items :items}]]
  items)


(defmethod ground? :rp+
  [_] false)


(defmethod min-length :rp+
  [[_ {items :items, n :n}]]
  (if (integer? n)
    (* n (count items))
    0))


(defmethod max-length :rp+
  [_] ##Inf)


(defmethod unparse :rp+
  [[_ {items :items n :n}]]
  (let [dots (if (some? n)
               (symbol (str ".." n))
               '..)]
    `(~@(sequence (map unparse) items) ~dots)))


(defmethod search? :rp+
  [_] false)


;; :rst

(defmethod children :rst
  [[_ {mvr :mvr}]]
  [mvr])


(defmethod ground? :rst
  [_] false)


(defmethod min-length :rst
  [_] 0)


(defmethod max-length :rst
  [_] ##Inf)


(defmethod unparse :rst
  [[_ {:keys [dots mvr]}]]
  (list (unparse mvr) dots))


(defmethod search? :rst
  [_] false)


;; :seq

(defmethod children :seq
  [[_ prt]] [prt])


(defmethod ground? :seq
  [[_ prt]] (ground? prt))


(defmethod unparse :seq
  [[_ prt]]
  (seq (unparse prt)))


(defmethod search? :seq
  [[_ prt]]
  (search? prt))


;; :set

(defmethod children :set
  [[_ the-set]] (vec the-set))


(defmethod ground? :set
  [[_ the-set]]
  (every? ground? the-set))


(defmethod unparse :set
  [[_ the-set]]
  (set (map unparse the-set)))


(defmethod search? :set
  [node]
  (not (ground? node)))


;; :uns

(defmethod ground? :uns
  [_] false)


(defmethod unparse :uns
  [[_ {expr :expr}]]
  (list 'clojure.core/unquote-splicing expr))


(defmethod search? :uns
  [_] false)

;; :unq

(defmethod ground? :unq
  [_] true)


(defmethod unparse :unq
  [[_ {expr :expr}]]
  (list 'clojure.core/unquote expr))


(defmethod search? :unq
  [_] false)


;; :vec

(defmethod children :vec
  [[_ prt]] [prt])


(defmethod ground? :vec
  [[_ prt]] (ground? prt))


(defmethod min-length :vec
  [[_ prt]] (min-length prt))


(defmethod max-length :vec
  [[_ prt]] (max-length prt))


(defmethod unparse :vec
  [[_ prt]]
  (vec (unparse prt)))


(defmethod search? :vec
  [[_ prt]]
  (search? prt))


;; ---------------------------------------------------------------------
;; Additional operators

(defmethod pattern-op 'scan
  [_]
  (s/cat :op '#{scan}
         :pats (s/+ :meander.syntax.alpha/term)))

(defmethod expand-usr-op 'scan
  [[_ {pats :pats}]]
  [:prd {:form `sequential?
         :terms [[:app {:form `seq
                        :terms [[:seq
                                 [:prt {:left '[:drp {:any _, :dots ...}],
                                        :right
                                        [:prt {:left [:cat pats],
                                               :dot '.,
                                               :right '[:drp {:any _, :dots ...}]}]}]]]}]]}])


(defmethod pattern-op 'vscan
  [_]
  (s/cat :op '#{vscan}
         :pats (s/+ :meander.syntax.alpha/term)))

(defmethod expand-usr-op 'vscan
  [[_ {pats :pats}]]
  [:vec
   [:prt
    {:left '[:drp {:any _, :dots ...}]
     :right
     [:prt
      {:left [:cat pats],
       :dot '.
       :right '[:drp {:any _, :dots ...}]}]}]])

(defmethod pattern-op 'let
  [_]
  (s/cat :op '#{let}
         :bindings (s/* (s/cat :binding :meander.syntax.alpha/term
                               :expr any?))))

(defmethod expand-usr-op 'let
  [[_ {bindings :bindings}]]
  [:cnj {:terms (mapv
                 (juxt (constantly :let)
                       identity)
                 bindings)}])
