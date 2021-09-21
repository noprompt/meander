(ns meander.environment.epsilon)

(def default
  {:meander.epsilon/abstract-disjunction true
   ;; This reduces code size at the expensive of performance.
   :meander.epsilon/abstract-plus false
   :meander.epsilon/eliminate-double-negation true
   :meander.epsilon/flatten-and true
   :meander.epsilon/flatten-or true
   :meander.epsilon/infer-case true
   :meander.epsilon/infer-literal-seq true
   :meander.epsilon/infer-literal-vector true
   ;; When set to true disables type checking.
   :meander.epsilon/no-type-check false
   ;; When set to true disables bounds checking.
   :meander.epsilon/no-bounds-check false
   :meander.epsilon/prioritize-map-entries true
   :meander.epsilon/prioritize-literal-set-elements false
   ;; This is false because enabling it offers no significant
   ;; improvement to generated code. In fact, it has no affect on the
   ;; generated code whatsoever. This flag only exists for historical
   ;; and testing purposes.
   :meander.epsilon/rewrite-map-as-to-and false
   ;; This is false because enabling it produces slower, larger
   ;; code. This flag only exists for historical and testing purposes.
   :meander.epsilon/rewrite-map-rest-to-dissoc false
   :meander.epsilon/rewrite-seq-as-to-and true
   ;; This is false because enabling it offers no significant
   ;; improvement to generated code. This flag only exists for
   ;; historical and testing purposes.
   :meander.epsilon/rewrite-set-as-to-and false
   ;; This is false because enabling it produces slower, larger
   ;; code. This flag only exists for historical and testing purposes.
   :meander.epsilon/rewrite-set-rest-to-disj false
   :meander.epsilon/rewrite-vector-as-to-and true
   :meander.epsilon/substitute-acyclic-references true
   ;; When set to true disables type checks and bounds checks.
   :meander.epsilon/use-native-methods true
   :meander.syntax.epsilon/expander-registry {}
   :meander.syntax.epsilon/phase nil
   :meander.syntax.epsilon/parser-registry {}})

(defn desugar-unsafe [env]
  (if (:meander.epsilon/unsafe env)
    (assoc (dissoc env :meander.epsilon/unsafe)
           :meander.epsilon/no-type-check true)
    env))

(defn desugar-dangerous [env]
  (if (:meander.epsilon/dangerous env)
    (assoc (dissoc env :meander.epsilon/dangerous)
           :meander.epsilon/no-type-check true
           :meander.epsilon/use-native-methods true)
    env))

(defn desugar [env]
  (-> env
      desugar-unsafe
      desugar-dangerous))
