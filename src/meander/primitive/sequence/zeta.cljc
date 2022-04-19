(ns meander.primitive.sequence.zeta
  (:refer-clojure :exclude [concat
                            cons]))

(defrecord SequenceCons [head tail])

(def cons
  #'->SequenceCons)

(defrecord SequenceConcat [a b])

(def concat
  #'->SequenceConcat)

