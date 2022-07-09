(ns meander.protocols.zeta)

(defprotocol IQuery
  :extend-via-metadata true
  (-query [iquery ilogic]))

(defprotocol IYield
  :extend-via-metadata true
  (-yield [iyield ilogic]))

(defprotocol IRedex
  :extend-via-metadata true
  (-redex [iredex ilogic]))

(defprotocol IState
  :extend-via-metadata true
  (-get-object [istate])
  (-set-object [istate new-object])
  (-get-variable [istate variable unbound])
  (-set-variable [istate variable value])
  (-get-reference [istate reference not-found])
  (-set-reference [istate reference new-definition])
  (-set-random [istate]))

(defn istate? [x]
  (satisfies? IState x))

(defprotocol ILogic
  :extend-via-metadata true
  ;; True/In
  (-pass [ilogic state])
  ;; False/Not In
  (-fail [ilogic state])
  ;; And/All/Intersection
  (^{:style/indent 1}
   -each [ilogic f])
  ;; Or/Some/Union
  (-some [ilogic that])
  ;; Pick/XOR
  (-pick [ilogic that])
  ;; Not/Complement
  (^{:style/indent 1}
   -comp [ilogic f])
  ;; Sentinel value
  (-unbound [ilogic]))

(defprotocol IUnwrap
  :extend-via-metadata true
  (-unwrap [iunwrap]))