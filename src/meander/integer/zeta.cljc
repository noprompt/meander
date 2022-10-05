(ns meander.integer.zeta
  (:refer-clojure :exclude [min max +])
  (:require [meander.logic.zeta :as m.logic]
            [meander.private.zeta :as m.private]
            [meander.primitive.zeta :as m*]
            [meander.primitive.integer.zeta :as m.integer*]
            [meander.protocols.zeta :as m.protocols]
            [meander.state.zeta :as m.state]
            [meander.environment.zeta :as m.env]
            [meander.zeta :as m]))

(m.private/def-fn-operator any m.integer*/any)

;; Min
;; ---------------------------------------------------------------------

(m.private/def-fn-operator min* m.integer*/min)

(m/defoperator min
  (m/system
   (m/rule
    (_ (m/each ?a (any)) (m/each ?b (any)))
    (min* ?a ?b))

   (m/rule
    (_ ?a ?b)
    (`min* ?a ?b)))
  {:notations [m/anything-symbol
               m/logic-variable-symbol]})

;; Max
;; ---------------------------------------------------------------------

(m.private/def-fn-operator max* m.integer*/max)

(m/defoperator max
  (m/system
   (m/rule
    (_ (m/each ?a (any)) (m/each ?b (any)))
    (max* ?a ?b))

   (m/rule
    (_ ?a ?b)
    (`max* ?a ?b)))
  {:notations [m/anything-symbol
               m/logic-variable-symbol]})


;; Add
;; ---------------------------------------------------------------------

(m.private/def-fn-operator +* m.integer*/+)

(m/defoperator +
  (m/system
   (m/rule
    (_ (m/each ?a (any)) (m/each ?b (any)))
    (+* ?a ?b))

   (m/rule
    (_ ?a ?b)
    (`+* ?a ?b)))
  {:notations [m/anything-symbol
               m/logic-variable-symbol]})

;; Subtract
;; ---------------------------------------------------------------------

(m.private/def-fn-operator -* m.integer*/-)

(m/defoperator -
  (m/system
   (m/rule
    (_ (m/each ?a (any)) (m/each ?b (any)))
    (-* ?a ?b))

   (m/rule
    (_ ?a ?b)
    (`-* ?a ?b)))
  {:notations [m/anything-symbol
               m/logic-variable-symbol]})

;; In Range
;; ---------------------------------------------------------------------
