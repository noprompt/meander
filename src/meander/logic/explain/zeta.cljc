(ns meander.logic.explain.zeta
  (:require [meander.protocols.zeta :as m.protocols]))

(deftype ExplainLogic [ilogic explanation]
  m.protocols/ILogic
  (-pass [this istate]
    (ExplainLogic. (m.protocols/-pass ilogic istate) nil))

  (-fail [this reason]
    (ExplainLogic. (m.protocols/-fail ilogic reason)
                   {:tag :leaf
                    :reason reason}))

  (-each [this f]
    (if explanation
      this
      (ExplainLogic.
       (m.protocols/-each ilogic
         (fn [istate]
           (.-ilogic ^ExplainLogic (f istate))))
       (deref (m.protocols/-fmap ilogic
                (fn [istate]
                  (.-explanation ^ExplainLogic (f istate))))))))

  (-some [this that]
    (let [that-explanation (.-explanation ^ExplainLogic that)]
      (ExplainLogic.
       (m.protocols/-some ilogic (.-ilogic ^ExplainLogic that))
       (if (and explanation that-explanation)
         {:tag :branch
          :left explanation
          :right that-explanation}
         explanation))))

  (-pick [this that]
    (ExplainLogic.
     (m.protocols/-pick ilogic (.-ilogic ^ExplainLogic that))
     (or explanation (.-explanation ^ExplainLogic that))))

  (-comp [this f]
    (ExplainLogic.
     (m.protocols/-comp ilogic
       (fn [istate]
         (.-ilogic ^ExplainLogic (f istate))))
     (deref (m.protocols/-fmap ilogic
              (fn [istate]
                (if (.-explanation (f istate))
                  {:tag :leaf
                   :reason "Negation failed"}
                  {:tag :null}))))))

  (-explain [this context]
    (ExplainLogic. ilogic {:tag :context
                           :context context
                           :explanation explanation}))

  (-unbound [this]
    (m.protocols/-unbound ilogic))

  #?(:clj clojure.lang.IDeref, :cljs IDeref)
  (deref [this]
    (if (some (comp #{:leaf} :tag) (tree-seq coll? seq explanation))
      explanation)))
