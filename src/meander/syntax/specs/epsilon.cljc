(ns ^:no-doc meander.syntax.specs.epsilon
  #?(:clj
     (:require [clojure.spec.alpha :as s]
               [clojure.spec.gen.alpha :as s.gen]
               [meander.util.epsilon :as m.util])
     :cljs
     (:require [cljs.spec.alpha :as s]
               [cljs.spec.gen.alpha :as s.gen]
               [meander.util.epsilon :as m.util])))

;; Every AST node has, at least, the key `:tag`, the value of which is
;; a `keyword?`.

(s/def :meander.syntax.epsilon.node/tag
  keyword?)

;; Some nodes may have an `::original-form` key, the value of which is
;; `any?`. This key is populated by forms which have been expanded
;; during `parse` and then subsequently parsed into an AST node.

(s/def :meander.syntax.epsilon.node/original-form
  any?)

(s/def :meander.syntax.epsilon/node
  (s/keys :req-un [:meander.syntax.epsilon.node/tag]
          :opt [:meander.syntax.epsilon.node/original-form]))

;; ---------------------------------------------------------------------
;; Data specs

;; Any
;; ---

(defn any-symbol?
  "`true` if `x` is a `simple-symbol?` the name of which begins with
  \"_\"."
  [x]
  (and (simple-symbol? x) (m.util/re-matches? #"_.*" (name x))))

(s/def :meander.syntax.epsilon/any-symbol
  (s/with-gen
    (s/conformer
     (fn [x]
       (if (any-symbol? x)
         x
         :clojure.spec.alpha/invalid))
     identity)
    (fn []
      (s.gen/fmap
       (fn [sym]
         (symbol (str "_" (name sym))))
       (s.gen/symbol)))))

(s/def :meander.syntax.epsilon.node.any/tag
  #{:any})

(s/def :meander.syntax.epsilon.node.any/symbol
  :meander.syntax.epsilon/any-symbol)

(s/def :meander.syntax.epsilon.node/any
  (s/keys :req-un [:meander.syntax.epsilon.node.any/tag
                   :meander.syntax.epsilon.node.any/symbol]))

;; Logic variable
;; --------------

(defn logic-variable-symbol?
  "true if `x` is in the form of a logic variable i.e. a simple symbol
  with a name beginning with \\?."
  [x]
  (and (simple-symbol? x) (m.util/re-matches? #"\?.+" (name x))))

(s/fdef logic-variable-symbol?
  :args (s/cat :x any?)
  :ret boolean?)

(s/def :meander.syntax.epsilon/logic-variable-symbol
  (s/with-gen
    (s/conformer
     (fn [x]
       (if (logic-variable-symbol? x)
         x
         :clojure.spec.alpha/invalid))
     identity)
    (fn []
      (s.gen/fmap
       (fn [x]
         (symbol (str \? (name x))))
       (s/gen simple-symbol?)))))

(s/def :meander.syntax.epsilon.node.lvr/tag
  #{:lvr})

(s/def :meander.syntax.epsilon.node.lvr/symbol
  :meander.syntax.epsilon/logic-variable-symbol)

(s/def :meander.syntax.epsilon.node/lvr
  (s/keys :req-un [:meander.syntax.epsilon.node.lvr/tag
                   :meander.syntax.epsilon.node.lvr/symbol]))

;; Memory variable
;; ---------------

(defn memory-variable-symbol?
  "true if x is in the form of a memory variable i.e. a simple symbol
  with a name beginning with \\!."
  [x]
  (and (simple-symbol? x) (m.util/re-matches? #"!.+" (name x))))

(s/def :meander.syntax.epsilon/memory-variable
  (s/with-gen
    (s/conformer
     (fn [x]
       (if (memory-variable-symbol? x)
         x
         :clojure.spec.alpha/invalid))
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

;; Mutable variable
;; ---------------

(defn mutable-variable-symbol?
  "true if x is in the form of a memory variable i.e. a simple symbol
  with a name beginning with \\!."
  [x]
  (and (simple-symbol? x) (m.util/re-matches? #"\*.+" (name x))))

(s/def :meander.syntax.epsilon/mutable-variable
  (s/with-gen
    (s/conformer
     (fn [x]
       (if (mutable-variable-symbol? x)
         x
         :clojure.spec.alpha/invalid))
     identity)
    (fn []
      (s.gen/fmap
       (fn [x]
         (symbol (str \* (name x))))
       (s/gen simple-symbol?)))))

(s/def :meander.syntax.epsilon.node.mut/tag
  #{:mut})

(s/def :meander.syntax.epsilon.node.mut/symbol
  :meander.syntax.epsilon/mutable-variable)

(s/def :meander.syntax.epsilon.node/mut
  (s/keys :req-un [:meander.syntax.epsilon.node.mut/tag
                   :meander.syntax.epsilon.node.mut/symbol]))

;; Reference
;; ---------

(defn reference-symbol?
  [x]
  (and (simple-symbol? x) (m.util/re-matches? #"%.+" (name x))))

(s/def :meander.syntax.epsilon/reference-symbol
  (s/with-gen
    (s/conformer
     (fn [x]
       (if (reference-symbol? x)
         x
         :clojure.spec.alpha/invalid))
     identity)
    (fn []
      (s.gen/fmap
       (fn [x]
         (symbol (str \% (name x))))
       (s/gen simple-symbol?)))))

(s/def :meander.syntax.epsilon.node.ref/tag
  #{:ref})

(s/def :meander.syntax.epsilon.node.ref/symbol
  :meander.syntax.epsilon/reference-symbol)

(s/def :meander.syntax.epsilon.node/ref
  (s/keys :req-un [:meander.syntax.epsilon.node.ref/symbol
                   :meander.syntax.epsilon.node.ref/tag]))

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

(s/def :meander.syntax.epsilon.node.partition/left
  :meander.syntax.epsilon/node)

(s/def :meander.syntax.epsilon.node.partition/right
  :meander.syntax.epsilon/node)

(s/def :meander.syntax.epsilon.node.partition/right
  :meander.syntax.epsilon/node)

(s/def :meander.syntax.epsilon.node/partition
  (s/keys :req-un [:meander.syntax.epsilon.node.partition/left
                   :meander.syntax.epsilon.node.partition/right]
          :opt-un [:meander.syntax.epsilon.node.partition/as]))


(s/def :meander.syntax.epsilon/expander-registry
  (s/map-of symbol? (s/or :fn fn? :var var?)))

(s/def :meander.syntax.epsilon/parser-registry
  (s/map-of symbol? (s/or :fn fn? :var var?)))

(s/def :meander.syntax.epsilon/env
  (s/keys :req [:meander.syntax.epsilon/expander-registry
                :meander.syntax.epsilon/parser-registry]
          :opt [:meander.syntax.epsilon/phase]))

(s/def :meander.syntax.epsilon/ref-map
  (s/map-of :meander.syntax.epsilon.node/ref
            :meander.syntax.epsilon/node))

;; ---------------------------------------------------------------------
;; Fn specs

#_
(s/fdef meander.syntax.epsilon/children
  :args (s/cat :node :meander.syntax.epsilon/node)
  :ret (s/coll-of :meander.syntax.epsilon/node
                  :kind sequential?))

#_
(s/fdef meander.syntax.epsilon/min-length
  :args (s/cat :node :meander.syntax.epsilon/node)
  :ret (s/or :nat nat-int?
             :inf #{##Inf}))

(s/fdef meander.syntax.epsilon/min-length?
  :args (s/cat :x any?)
  :ret boolean?)

#_
(s/fdef meander.syntax.epsilon/max-length
  :args (s/cat :node :meander.syntax.epsilon/node)
  :ret (s/or :nat nat-int?
             :inf #{##Inf}))

(s/fdef meander.syntax.epsilon/max-length?
  :args (s/cat :x any?)
  :ret boolean?)

(s/fdef meander.syntax.epsilon/variable-length?
  :args (s/cat :node :meander.syntax.epsilon/node)
  :ret boolean?)

(s/fdef meander.syntax.epsilon/ground?
  :args (s/cat :node :meander.syntax.epsilon/node)
  :ret boolean?)

(s/fdef meander.syntax.epsilon/search?
  :args (s/cat :node :meander.syntax.epsilon/node)
  :ret boolean?)

(s/fdef meander.syntax.epsilon/references
  :args (s/cat :node :meander.syntax.epsilon/node)
  :ret (s/coll-of :meander.syntax.epsilon.node/ref
                  :kind set?
                  :into #{}))

(s/fdef meander.syntax.epsilon/resolve-expander
  :args (s/cat :sym symbol?
               :env :meander.syntax.epsilon/env)
  :ret (s/or :fn fn?
             :nil nil?))

(s/fdef meander.syntax.epsilon/expand-form
  :args (s/cat :form any?
               :env :meander.syntax.epsilon/env)
  :ret any?)

(s/fdef meander.syntax.epsilon/resolver-parser
  :args (s/cat :sym symbol?
               :env :meander.syntax.epsilon/env)
  :ret (s/alt :fn fn?
              :nil nil?))

(s/fdef meander.syntax.epsilon/parse-all
  :args (s/cat :forms (s/nilable (s/coll-of any? :kind sequential?))
               :env :meander.syntax.epsilon/env)
  :ret (s/coll-of :meander.syntax.epsilon/node
                  :kind sequential?))

(s/fdef meander.syntax.epsilon/parse
  :args (s/alt :a1 (s/cat :form any?)
               :a2 (s/cat :form any?
                          :env :meander.syntax.epsilon/env))
  :ret :meander.syntax.epsilon/node)

(s/fdef meander.syntax.epsilon/make-ref-map
  :args (s/cat :node :meander.syntax.epsilon/node)
  :ret :meander.syntax.epsilon/ref-map)

(s/fdef meander.syntax.epsilon/substitute-refs
  :args (s/alt :a1 (s/cat :node :meander.syntax.epsilon/node)
               :a2 (s/cat :node :meander.syntax.epsilon/node
                          :ref-map :meander.syntax.epsilon/ref-map))
  :ret :meander.syntax.epsilon/node)

(s/fdef meander.syntax.epsilon/partition-nodes
  :args (s/cat :node :meander.syntax.epsilon.node/partition))

(s/fdef meander.syntax.epsilon/tag
  :args (s/cat :node :meander.syntax.epsilon/node))

(s/fdef meander.syntax.epsilon/variables
  :args (s/cat :node :meander.syntax.epsilon/node))

(s/fdef meander.syntax.epsilon/memory-variables
  :args (s/cat :node :meander.syntax.epsilon/node))

(s/fdef meander.syntax.epsilon/logic-variables
  :args (s/cat :node :meander.syntax.epsilon/node))

(s/fdef meander.syntax.epsilon/mutable-variables
  :args (s/cat :node :meander.syntax.epsilon/node))

(s/def ::local-name
  (s/and simple-symbol? (fn [x] (not (= x '&)))))

(s/def ::binding-form
  (s/or :local-symbol ::local-name
        :seq-destructure ::seq-binding-form
        :map-destructure ::map-binding-form))

;; sequential destructuring

(s/def ::seq-binding-form
  (s/and vector?
         (s/cat :forms (s/* ::binding-form)
                :rest-forms (s/? (s/cat :ampersand #{'&} :form ::binding-form))
                :as-form (s/? (s/cat :as #{:as} :as-sym ::local-name)))))

;; map destructuring

(s/def ::keys (s/coll-of ident? :kind vector?))
(s/def ::syms (s/coll-of symbol? :kind vector?))
(s/def ::strs (s/coll-of simple-symbol? :kind vector?))
(s/def ::or (s/map-of simple-symbol? any?))
(s/def ::as ::local-name)

(s/def ::map-special-binding
  (s/keys :opt-un [::as ::or ::keys ::syms ::strs]))

(s/def ::map-binding (s/tuple ::binding-form any?))

(s/def ::ns-keys
  (s/tuple (s/and qualified-keyword?
                  (fn [k] (contains? #{"keys" "syms"} (name k))))
           (s/coll-of simple-symbol? :kind vector?)))

(s/def ::map-bindings
  (s/every (s/or :map-binding ::map-binding
                 :qualified-keys-or-syms ::ns-keys
                 :special-binding (s/tuple #{:as :or :keys :syms :strs} any?))
           :kind map?))

(s/def ::map-binding-form
  (s/merge ::map-bindings ::map-special-binding))


(s/def ::param-list
  (s/and vector?
         (s/cat :params (s/* ::binding-form)
                :var-params (s/? (s/cat :ampersand #{'&} :var-form ::binding-form)))))

(s/def ::params+body
  (s/cat :params ::param-list
         :body (s/alt :prepost+body (s/cat :prepost map?
                                           :body (s/+ any?))
                      :body (s/* any?))))

(s/def ::defsyntax-args
  (s/cat :fn-name simple-symbol?
         :docstring (s/? string?)
         :meta (s/? map?)
         :fn-tail (s/alt :arity-1 ::params+body
                         :arity-n (s/cat :bodies (s/+ (s/spec ::params+body))
                                         :attr-map (s/? map?)))))

(s/fdef meander.syntax.epsilon/defsyntax
  :args ::defsyntax-args)
