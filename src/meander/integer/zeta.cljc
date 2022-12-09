(ns meander.integer.zeta
  (:refer-clojure :exclude [cast min max + -])
  (:require [clojure.core :as clj]
            [meander.logic.zeta :as m.logic]
            [meander.private.zeta :as m.private]
            [meander.primitive.zeta :as m*]
            [meander.primitive.integer.zeta :as m.integer*]
            [meander.protocols.zeta :as m.protocols]
            [meander.state.zeta :as m.state]
            [meander.environment.zeta :as m.env]
            [meander.zeta :as m]))



;; Any
;; ---------------------------------------------------------------------

(m.private/def-fn-operator any m.integer*/any)


;; Aggregations
;; ---------------------------------------------------------------------

(m/defvariable sum
  (m/system
   (m/rule
    [(m/unbound) (m/each (any) ?a)]
    ?a)

   (m/rule
    [?a (m/each (any) ?b)]
    (m/apply ~op [?a ?b] _)))

  (m/system
   (m/rule ?a [?a ?a]))
  {:eval {'op clj/+}
   :notations m/default-notations})


;; Cast
;; ---------------------------------------------------------------------

(m.private/def-fn-operator cast* m.integer*/cast)

(m/defoperator cast
  (m/system
   ;; Identity
   (m/rule
    (_ (m/each (m/cons `cast* _) ?form))
    ?form)

   ;; Replace this cast with its arg if it represents an integer.
   (m/rule
    (_ (m/each (any) ?arg))
    ?arg)

   ;; Replace this cast with its arg if it represents an integer.
   (m/rule
    (m/with {%op (m/pick `- `-*
                         `+ `+*
                         `any `cast
                         `cast* `max
                         `max* `min
                         `min* `in-range)}
      (_ (m/each (m/cons %op ?x))))
    ?x))
  {:notations m/default-notations})


;; Min
;; ---------------------------------------------------------------------

(m.private/def-fn-operator min* m.integer*/min)

(m/defoperator min
  (m/system
   (m/rule
    (_ (m/each ?a (any)) (m/each ?b (any)))
    (min* ?a ?b))

   (m/rule
    (_ (m/each ?a (any)) ?b)
    (`min* ?a (`cast ?b)))

   (m/rule
    (_ ?a (m/each ?b (any)))
    (`min* (`cast ?a) ?b))

   (m/rule
    (_ ?a ?b)
    (`min* (`cast ?a) (`cast ?b)))

   (m/rule
    (m/concat (_ ?a ?b) (m/cons ?c ?rest))
    (`min (`min ?a ?b) (m/cons `min (m/cons ?c ?rest)))))
  {:notations m/default-notations})

;; Max
;; ---------------------------------------------------------------------

(m.private/def-fn-operator max* m.integer*/max)

(m/defoperator max
  (m/system
   (m/rule
    (_ (m/each ?a (any)) (m/each ?b (any)))
    (max* ?a ?b))

   (m/rule
    (_ (m/each ?a (any)) ?b)
    (`max* ?a (`cast ?b)))

   (m/rule
    (_ ?a (m/each ?b (any)))
    (`max* (`cast ?a) ?b))

   (m/rule
    (_ ?a ?b)
    (`max* (`cast ?a) (`cast ?b))))
  {:notations [m/anything-symbol
               m/logic-variable-symbol]})


;; Add
;; ---------------------------------------------------------------------

(m.private/def-fn-operator +* m.integer*/+)

(m/defoperator
  ^{:arglists '([] [a & more])}
  +
  (m/system
   (m/rule
    (_)
    0)

   (m/rule
    (_ (m/each (any) ?x))
    ?x)

   (m/rule
    (_ (m/each ?a (any)) (m/each ?b (any)))
    (+* ?a ?b))

   (m/rule
    (_ (m/each ?a (any)) ?b)
    (`+* ?a (`cast ?b)))

   (m/rule
    (_ ?a ?b)
    (`+* (`cast ?a) (`cast ?b)))

   (m/rule
    (m/cons _ (m/* (m/pick (sum 1) <<x) ()))
    (m/let [?a (m/pick (sum 1) 0)
            ?not-ints (m/* <<x ())
            ?b (m/pick (m/project ?not-ints () 0)
                       (m/let [(m/cons ?head ?tail) ?not-ints]
                         (`+ ?head (m/cons `+ ?tail))))]
      (m/pick (m/project ?a 0 ?b)
              (m/project ?b 0 ?a)
              (`+ ?a ?b)))))
  {:notations m/default-notations})


;; Subtract
;; ---------------------------------------------------------------------

(m.private/def-fn-operator -* m.integer*/-)

(m/defoperator -
  (m/system
   (m/rule
    (_ (m/each ?a (any)))
    (m/apply ~neg [?a] _))

   (m/rule
    (_ (m/each ?a (any)) (m/each ?b (any)))
    (-* ?a ?b))

   (m/rule
    (_ ?a ?b)
    (`-* (`cast ?a) (`cast ?b)))

   (m/rule
    (m/concat (_ ?a ?b) (m/cons ?c ?rest))
    (`- (`- ?a ?b) (m/cons `- (m/cons ?c rest)))))
  {:eval {'neg clj/-}
   :notations m/default-notations})

;; In Range
;; ---------------------------------------------------------------------

(m.private/def-fn-operator in-range m.integer*/in-range)
