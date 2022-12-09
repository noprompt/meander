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

(defprotocol IGroundValues
  :extend-via-metadata true
  (-ground-values [iground-values ilogic]))

(defprotocol IState
  :extend-via-metadata true
  (-get-object [istate])
  (-set-object [istate new-object])
  (-get-variable [istate variable unbound])
  (-set-variable [istate variable value])
  (-get-reference [istate reference not-found])
  (-get-references [istate])
  (-push-references [istate references])
  (-pop-references [istate])
  (-set-random [istate])
  (-clear-variables [istate])
  (-unset-variable [istate variable]))

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
  ;; Exists
  (-scan [ilogic f xs])
  ;; Sentinel value
  (-unbound [ilogic])
  ;; NOTE: Experimental
  (-explain [ilogic context]))

(defprotocol IFMap
  :extend-via-metadata true
  (^{:style/indent 1}
   -fmap [this f]))

(defprotocol IPure
  :extend-via-metadata true
  (-pure [this x]))
