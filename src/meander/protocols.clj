(ns meander.protocols)


(defprotocol IForm
  (-form [x]))


(defprotocol ITermSize
  (-term-size [t]))


(defprotocol ITermPositions
  (-term-positions [t level]))


(defprotocol ITermVariables
  (-term-variables [t]))


(defprotocol IRuleExecute
  (-rule-execute [r t]))


(defprotocol IRuleLeftHandSide
  (-rule-left-hand-side [r]))


(defprotocol IRuleRightHandSide
  (-rule-right-hand-side [r]))


(defprotocol IIsVariable
  (-variable? [t]))


(defprotocol IUnify
  (-unify [this that substitution-map bottom]))


(defprotocol IUnify*
  (-unify* [this that substition-map bottom]))


(defprotocol ISubstitute
  (-substitute [this substitution-map]))


(defprotocol IFmap
  (-fmap [this f]))


(defprotocol IWalk
  (-walk [this inner-f outer-f]))


(defprotocol IStream
  (-stream-head [this no-head])
  (-stream-tail [this]))
