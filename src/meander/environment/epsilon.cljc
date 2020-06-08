(ns meander.environment.epsilon)

(def default
  {:meander.epsilon/eliminate-double-negation true
   :meander.epsilon/flatten-and true
   :meander.epsilon/flatten-or true
   :meander.epsilon/infer-case true
   :meander.epsilon/infer-literal-seq true
   :meander.epsilon/infer-literal-vector true
   :meander.epsilon/prioritize-map-entries true
   :meander.epsilon/prioritize-literal-set-elements true
   :meander.epsilon/rewrite-seq-as-to-and true
   :meander.epsilon/rewrite-set-as-to-and true
   :meander.epsilon/rewrite-vector-as-to-and true
   :meander.epsilon/substitute-acyclic-references true
   :meander.syntax.epsilon/expander-registry {}
   :meander.syntax.epsilon/phase nil
   :meander.syntax.epsilon/parser-registry {}})
