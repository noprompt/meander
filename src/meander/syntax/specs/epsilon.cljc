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

(s/fdef meander.syntax.epsilon/children
  :args (s/cat :node :meander.syntax.epsilon/node)
  :ret (s/coll-of :meander.syntax.epsilon/node
                  :kind sequential?))

(s/fdef meander.syntax.epsilon/min-length
  :args (s/cat :node :meander.syntax.epsilon/node)
  :ret (s/or :nat nat-int?
             :inf #{##Inf}))

(s/fdef meander.syntax.epsilon/min-length?
  :args (s/cat :x any?)
  :ret boolean?)

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
  :ret (s/alt :fn fn?
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
