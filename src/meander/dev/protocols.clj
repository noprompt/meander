(ns meander.dev.protocols)


(defprotocol IAll
  (-all [term strategy]
    "Apply strategy to all subterms in term."))


(defprotocol IOne
  (-one [term strategy]
    "Apply strategy to exactly one subterm in term."))


(defprotocol IMany
  (-many [term strategy]
    "Apply strategy to at least one subterm in term."))

