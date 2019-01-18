(ns meander.protocols.alpha)


(defprotocol IAll
  (-all [term strategy]
    "Apply strategy to all subterms in term."))


(defprotocol IOne
  (-one [term strategy]
    "Apply strategy to exactly one subterm in term."))


(defprotocol ISome
  (-some [term strategy]
    "Apply strategy to at least one subterm in term."))


(defprotocol IProject
  (-project [term strategy]
    "Return term such that only subterms for which strategy succeeds
    are retained."))
