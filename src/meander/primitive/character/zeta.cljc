(ns meander.primitive.character.zeta)

(defrecord AnyCharacter [])

(defrecord CharacterInRange [min max])

(def any #'->AnyCharacter)

(def ^{:arglists '([min max])}
  in-range #'->CharacterInRange)
