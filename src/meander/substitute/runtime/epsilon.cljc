(ns meander.substitute.runtime.epsilon)

(defn iterator [coll]
  #?(:clj (clojure.lang.RT/iter coll)
     :cljs (iter coll)))
