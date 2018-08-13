(ns meander.syntax.alpha
  (:require [clojure.set :as set]
            [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as s.gen]
            [clojure.walk :as walk]))


(set! *warn-on-reflection* true)


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


(defn any-symbol?
  "true if x is a symbol beginning with _."
  [x]
  (and (simple-symbol? x)
       (.matches (re-matcher #"_.*" (name x)))))


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
       (.matches (re-matcher #"\?.+" (name x)))))


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
       (.matches (re-matcher #"!.+" (name x)))))


(s/def :meander.syntax.alpha/memory-variable
  (s/with-gen
    (conformer* memory-variable-form? identity)
    (fn []
      (s.gen/fmap
       (fn [x]
         (symbol (str \! (name x))))
       (s/gen simple-symbol?)))))


(s/def :meander.syntax.alpha.capture/binding
  (s/or :lvr :meander.syntax.alpha/logic-variable
        :mvr :meander.syntax.alpha/memory-variable))


(s/def :meander.syntax.alpha/capture
  (s/cat :term :meander.syntax.alpha/term
         :as #{:as}
         :binding :meander.syntax.alpha.capture/binding))


(defn partition-symbol?
  [x]
  (= x '.))


(defn zero-or-more-symbol?
  [x]
  (and (simple-symbol? x)
       (re-matches #"\.\.+" (name x))))


(defn n-or-more-symbol?
  [x]
  (and (simple-symbol? x)
       (re-matches #"\.+\d+" (name x))))


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
        :let :meander.syntax.alpha/let
        :prd :meander.syntax.alpha/pred
        :grd :meander.syntax.alpha/guard
        :cap :meander.syntax.alpha/capture
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
    (s/cat :items (s/+ :meander.syntax.alpha.sequential/subterm)
           :dots zero-or-more-symbol?)
    (fn []
      (s.gen/fmap
       (fn [items]
         (~@items ~'...))
       (s.gen/not-empty (s.gen/list (s/gen :meander.syntax.alpha.sequential/subterm)))))))


(s/def :meander.syntax.alpha/n-or-more
  (s/with-gen
    (s/cat :items (s/+ :meander.syntax.alpha.sequential/subterm)
           :dots n-or-more-symbol?)
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


(s/def :meander.syntax.alpha.sequential/item
  (s/alt :prt (s/cat :left (s/alt :drp :meander.syntax.alpha/drop
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
                   :let :meander.syntax.alpha/let
                   :prd :meander.syntax.alpha/pred
                   :grd :meander.syntax.alpha/guard
                   :cap :meander.syntax.alpha/capture
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
  (s/map-of :meander.syntax.alpha/term
            :meander.syntax.alpha/term))


(s/def :meander.syntax.alpha/pred
  (s/with-gen
    (s/and seq?
           (s/cat :pred #{'pred}
                  :form any?))
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


(s/def :meander.syntax.alpha/let
  (s/with-gen
    (s/and seq?
           (s/cat :let #{'let}
                  :binding (s/or :lvr :meander.syntax.alpha/logic-variable)
                  :expr any?))
    (fn []
      (s.gen/fmap
       (fn [[lvr x]]
         (list 'let lvr x))
       (s.gen/tuple
        (s/gen :meander.syntax.alpha/logic-variable)
        (s.gen/any))))))


(s/def :meander.syntax.alpha/term
  (s/or :quo :meander.syntax.alpha/quote
        :unq :meander.syntax.alpha/unquote
        :cap :meander.syntax.alpha/capture
        :cnj :meander.syntax.alpha/and
        :dsj :meander.syntax.alpha/or
        :let :meander.syntax.alpha/let
        :prd :meander.syntax.alpha/pred
        :grd :meander.syntax.alpha/guard
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


(s/def :meander.syntax.alpha.node/lvr
  (s/tuple #{:lvr} :meander.syntax.alpha/logic-variable))


(s/def :meander.syntax.alpha.node/mvr
  (s/tuple #{:mvr} :meander.syntax.alpha/memory-variable))


(s/def :meander.syntax.alpha.node.dsj/terms
  (s/* :meander.syntax.alpha/node))


(s/def :meander.syntax.alpha.node/dsj
  (s/tuple #{:dsj} (s/keys :req-un [:meander.syntax.alpha.node.dsj/terms])))


(s/fdef parse
  :args (s/cat :x any?)
  :ret :meander.syntax.alpha/node)


(defn parse
  [x]
  (s/conform :meander.syntax.alpha/term x))


(defn node?
  [x]
  (s/valid? :meander.syntax.alpha/node x))


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


(s/fdef subnodes
  :args (s/cat :node :meander.syntax.alpha/node)
  :ret (s/coll-of :meander.syntax.alpha/node
                  :kind sequential?
                  :into []))


(defn subnodes-dispatch
  [node]
  (s/assert :meander.syntax.alpha/node node)
  (tag node))


(defmulti subnodes
  "Return a sequence of all subnodes of node."
  {:arglists '([node])}
  #'subnodes-dispatch)


(defmethod subnodes :default
  [node] [node])


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
  (sequence
   (remove
    (fn [x]
      (identical? x node)))
   (subnodes node)))


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
  "true if node is ground i.e. it contains no variables or is not a
  match operator."
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
   :lit])


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
  {:arglists '([node])
   :tag 'java.lang.Number}
  #'max-length-dispatch)


(defn min-length-dispatch
  {:private true}
  [node]
  (s/assert :meander.syntax.alpha/node node)
  (tag node))


(defmulti min-length
  ""
  {:arglists '([node])
   :tag 'java.lang.Number}
  #'min-length-dispatch)


(s/fdef variable-length?
  :args (s/cat :node :meander.syntax.alpha/node)
  :ret boolean?)


(defn variable-length?
  "true if node may have a variable length."
  [node]
  (not (= (min-length node) (max-length node))))


;; ---------------------------------------------------------------------
;; AST Method implementations


;; :any

(defmethod ground? :any
  [_] false)


(defmethod unparse :any
  [_] '_)

;; :cap


(defmethod subnodes :cap
  [[_ {:keys [term binding]} :as node]]
  (list* node binding (subnodes term)))


(defmethod unparse :cap
  [[_ {:keys [term binding]}]]
  `(~(unparse term) :as ~(unparse binding)))


(defn circular-cap?
  "true if cap-node binding is a logic variable and term contains a
  circular reference to it's binding."
  {:arglists '([cap-node])}
  [[_ {:keys [term binding]}]]
  (if (lvr-node? binding)
    (transduce
     (comp (remove (comp #{:cap} tag))
           (map proper-subnodes))
     (fn
       ([init] init)
       ([_ nodes]
        (if (some #{binding} nodes)
          (reduced true)
          false)))
     false
     (subnodes term))
    ;; :cap nodes which bind memory variables include themselves as
    ;; subterms.
    false))

;; :cat

(defmethod ground? :cat
  [[_ nodes]] (every? ground? nodes))


(defmethod min-length :cat
  [[_ nodes]]
  (count nodes))


(defmethod max-length :cat
  [[_ nodes]]
  (count nodes))


(defmethod subnodes :cat
  [[_ nodes :as node]]
  (cons node (sequence (mapcat subnodes) nodes)))


(defmethod unparse :cat
  [[_ nodes]]
  (sequence (map unparse) nodes))

;; :cnj

(defmethod ground? :cnj
  [_] false)


(defmethod subnodes :cnj
  [[_ {nodes :terms} :as node]]
  (cons node (sequence (map subnodes) nodes)))


(defmethod unparse :cnj
  [[_ {nodes :terms}]]
  `(~'and ~@(sequence (map unparse) nodes)))


;; :drp


(defmethod ground? :drp
  [_] false)


(defmethod min-length :drp
  [_] 0)


(defmethod max-length :drp
  [_] ##Inf)


(defmethod unparse :drp
  [_] '(_ ...))


;; :dsj

(defmethod ground? :dsj
  [_] false)


(defmethod subnodes :dsj
  [[_ {nodes :terms} :as node]]
  (cons node (sequence (map subnodes) nodes)))


(defmethod unparse :dsj
  [[_ {nodes :terms}]]
  `(~'or ~@(sequence (map unparse) nodes)))


;; :grd

(defmethod ground? :grd
  [_] false)


(defmethod unparse :grd
  [[_ {form :form}]]
  `(~'guard ~form))

;; :let

(defmethod ground? :let
  [_] false)


(defmethod subnodes :let
  [[_ {binding :binding, expr :expr} :as node]]
  [node binding])


(defmethod unparse :let
  [[_ {binding :binding, expr :expr}]]
  `(~'let ~binding ~expr))


;; :lit

(defmethod ground? :lit
  [_] true)


(defmethod unparse :lit
  [[_ lit]] lit)

;; :lvr

(defmethod ground? :lvr
  [_] false)


(defmethod unparse :lvr
  [[_ sym]] sym)


;; :mvr

(defmethod ground? :mvr
  [_] false)


(defmethod unparse :mvr
  [[_ sym]] sym)


;; :prd

(defmethod ground? :prd
  [_] false)


(defmethod unparse :prd
  [[_ {form :form}]]
  `(~'pred ~form))


;; :prt

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


(defmethod subnodes :prt
  [[_ {left :left, right :right} :as node]]
  (cons node
        (concat (subnodes left)
                (when (some? right)
                  (subnodes right))))) 

(defmethod unparse :prt
  [[_ {dot :dot, left :left, right :right}]]
  (concat (unparse left)
          (when (some? dot)
            (list dot))
          (when (some? right)
            (unparse right))))

;; :quo

(defmethod ground? :quo
  [_] true)


(defmethod unparse :quo
  [[_ {form :form}]]
  `(quote ~form))


;; :rp*

(defmethod ground? :rp*
  [_] false)


(defmethod min-length :rp*
  [_] 0)


(defmethod max-length :rp*
  [_] ##Inf)


(defmethod subnodes :rp*
  [[_ {items :items} :as node]]
  (cons node (sequence (map subnodes) items)))


(defmethod unparse :rp*
  [[_ {items :items dots :dots}]]
  `(~@(sequence (map unparse) items) ~dots))


;; :rp+

(defmethod ground? :rp+
  [_] false)


(defmethod min-length :rp+
  [[_ {dots :dots}]]
  (Integer/parseInt
   (aget
    (.split (name dots) "\\.+" 2)
    1)))


(defmethod max-length :rp+
  [_] ##Inf)


(defmethod subnodes :rp+
  [[_ {items :items} :as node]]
  (cons node (sequence (map subnodes)) items))


(defmethod unparse :rp+
  [[_ {items :items dots :dots}]]
  `(~@(sequence (map unparse) items) ~dots))


;; :seq

(defmethod ground? :seq
  [[_ prt]] (ground? prt))


(defmethod subnodes :seq
  [[_ prt :as node]]
  (cons node (subnodes prt)))


(defmethod unparse :seq
  [[_ prt]]
  (seq (unparse prt)))


;; :uns

(defmethod ground? :uns
  [_] false)


(defmethod unparse :uns
  [[_ {expr :expr}]]
  (list 'clojure.core/unquote-splicing expr))

;; :unq

(defmethod ground? :unq
  [_] false)


(defmethod unparse :unq
  [[_ {expr :expr}]]
  (list 'clojure.core/unquote expr))


;; :vec

(defmethod ground? :vec
  [[_ prt]] (ground? prt))


(defmethod min-length :vec
  [[_ prt]] (min-length prt))


(defmethod max-length :vec
  [[_ prt]] (max-length prt))


(defmethod subnodes :vec
  [[_ prt :as node]]
  (cons node (subnodes prt)))


(defmethod unparse :vec
  [[_ prt]]
  (vec (unparse prt)))
