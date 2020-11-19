(ns ^:no-doc meander.substitute.specs.epsilon
  (:require [clojure.spec.alpha :as s]))

(s/fdef meander.substitute.epsilon/substitute
  :args (s/cat :pattern any?)
  :ret any?)
