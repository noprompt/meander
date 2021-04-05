(ns meander.protocols.zeta)

(defprotocol IChildren
  (children [this]))

(defprotocol IMakeNode
  (make-node [this new-children]))

(defprotocol IWalk
  (walk [this inner outer]))
