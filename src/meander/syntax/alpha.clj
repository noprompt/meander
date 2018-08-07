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
  (s/or :mvr :meander.syntax.alpha/memory-variable
        :lvr :meander.syntax.alpha/logic-variable))


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


(s/def :meander.syntax.alpha/term
  (s/or :quo :meander.syntax.alpha/quote
        :unq :meander.syntax.alpha/unquote
        :cap :meander.syntax.alpha/capture
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
  (s/tuple #{:lvr} :meander.syntax.alpha/memory-variable))


(s/def :meander.syntax.alpha.node/mvr
  (s/tuple #{:mvr} :meander.syntax.alpha/memory-variable))


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


(s/fdef data
  :args (s/cat :node :meander.syntax.alpha/node)
  :ret any?)


(defn data
  [node]
  (s/assert :meander.syntax.alpha/node node)
  (second node))



(s/fdef variables
  :args (s/or :a1 (s/cat :node :meander.syntax.alpha/node)
              :a2 (s/cat :node :meander.syntax.alpha/node
                         :filters (s/coll-of #{:lvr :mvr} :kind set? :into #{})))
  :ret (s/coll-of
        (s/or :meander.syntax.alpha.node/lvr
              :meander.syntax.alpha.node/mvr)
        :kind set?
        :into #{}))

(defn variables
  "Return all :lvr and :mvr nodes."
  ([node]
   (s/assert :meander.syntax.alpha/node node)
   (into #{}
         (comp
          (filter node?)
          (filter (comp #{:lvr :mvr} tag)))
         (tree-seq coll? seq node)))
  ([node filters]
   (s/assert :meander.syntax.alpha/node node)
   (into #{}
         (comp
          (filter node?)
          (filter (comp (set/intersection #{:lvr :mvr} filters) tag)))
         (tree-seq coll? seq node))))


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


(defmethod rank :default [_]
  0)


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


;; :cat

(defmethod ground? :cat
  [[_ nodes]] (every? ground? nodes))


(defmethod min-length :cat
  [[_ nodes]]
  (count nodes))


(defmethod max-length :cat
  [[_ nodes]]
  (count nodes))


;; :drp


(defmethod ground? :drp
  [_] false)


(defmethod min-length :drp
  [_] 0)


(defmethod max-length :drp
  [_] ##Inf)


;; :lit

(defmethod ground? :lit
  [_] true)


(defmethod rank :lit
  [_] 1)


;; :lvr

(defmethod ground? :lvr
  [_] false)


;; :mvr

(defmethod ground? :mvr
  [_] false)


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


;; :quo


(defmethod ground? :quo
  [_] true)


(defmethod rank :quo
  [_] 1)


;; :rp*

(defmethod ground? :rp*
  [_] false)


(defmethod min-length :rp*
  [_] 0)


(defmethod max-length :rp*
  [_] ##Inf)


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


;; :seq

(defmethod ground? :seq
  [[_ prt]] (ground? prt))


;; :uns

(defmethod ground? :uns
  [_] false)


;; :unq

(defmethod ground? :unq
  [_] false)


;; :vec

(defmethod ground? :vec
  [[_ prt]] (ground? prt))


(defmethod min-length :vec
  [[_ prt]] (min-length prt))


(defmethod max-length :vec
  [[_ prt]] (max-length prt))
