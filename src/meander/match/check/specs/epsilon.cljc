(ns ^:no-doc meander.match.check.epsilon
  (:require [clojure.spec.alpha :as s]
            [meander.syntax.specs.epsilon :as r.syntax]))


(s/def :meander.match.epsilon.check-env/lvrs
  (s/coll-of :meander.syntax.epsilon.node/lvr :kind set?))

(s/def :meander.match.epsilon.check-env/mvrs
  (s/coll-of :meander.syntax.epsilon.node/mvr :kind set?))

(s/def :meander.match.epsilon.check-env/ref-map
  (s/keys :req-un [:meander.syntax.epsilon/node
                   :meander.syntax.epsilon.node/with]))

(s/def :meander.match.epsilon.check-env/refs
  (s/map-of :meander.syntax.epsilon.node/ref
            :meander.match.epsilon.check-env/ref-map))

(s/def :meander.match.epsilon/check-env
  (s/keys :req-un [:meander.match.epsilon.check-env/lvrs
                   :meander.match.epsilon.check-env/mvrs
                   :meander.match.epsilon.check-env/refs]))
