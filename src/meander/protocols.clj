(ns meander.protocols)


(defprotocol IForm
  (-form [x]))


(defprotocol ITermSize
  (-term-size [t]))


(defprotocol ITermPositions
  (-term-positions [t level]))


(defprotocol ITermVariables
  (-term-variables [t]))


(defprotocol IRule)


(defprotocol IRuleLeftSide
  (-rule-left-side [r]))


(defprotocol IRuleRightSide
  (-rule-right-side [r]))


(defprotocol IVariable)


(defprotocol IUnify
  (-unify [this that substitution-map]))


(defprotocol IUnify*
  (-unify* [this that substition-map]))


(defprotocol ISubstitute
  (-substitute [this substitution-map]))


(defprotocol IFmap
  (-fmap [this f]))


(defprotocol IWalk
  "clojure.core.walk as a protocol."
  (-walk [this inner-f outer-f]))


(defprotocol IAll
  (-all [term strategy]
    "Apply strategy to all subterms in term."))


(defprotocol IOne
  (-one [term strategy]
    "Apply strategy to exactly one subterm in term."))


(defprotocol IMany
  (-many [term strategy]
    "Apply strategy to at least one subterm in term."))
