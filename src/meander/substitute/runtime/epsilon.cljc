(ns ^:no-doc meander.substitute.runtime.epsilon
  (:refer-clojure :exclude [iterator-seq]))

(def FAIL
  (ex-info "" {}))

(defmacro fail? [x]
  `(identical? ~x FAIL))

(defn iterator [coll]
  #?(:clj (clojure.lang.RT/iter coll)
     :cljs (iter coll)))

(def
  ^{:arglists '([iter])}
  iterator-seq
  #?(:clj clojure.core/iterator-seq
     :cljs (fn f [i]
             (if (.hasNext i)
               (lazy-seq (cons (.next i) (f i)))))))

