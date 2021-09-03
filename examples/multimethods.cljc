(ns multimethods
  (:refer-clojure :exclude [defmethod defmulti])
  (:require
   #?(:clj [clojure.core :as clj] :cljs [cljs.core :as cljs])
   [meander.epsilon :as m]))

(def cache_ (atom {}))

(defprotocol IMeanderMethods
  (-set-fn         [this f]))

(deftype MultiMeanderFn [^:unsynchronized-mutable target-fn]
  IMeanderMethods
  (-set-fn [_ f]
    (set! target-fn f))

  clojure.lang.IFn
  (invoke [___ a]
    (target-fn a))
  (invoke [___ a b]
    (target-fn a b))
  (invoke [___ a b c]
    (target-fn a b c))
  (invoke [___ a b c d]
    (target-fn a b c d))
  (invoke [___ a b c d e]
    (target-fn a b c d e))
  ;; ... apply rest etc
  )

(defmacro defmulti [name]
  (swap! cache_ dissoc name)
  `(def ~name (MultiMeanderFn. nil)))

(defmacro defmethod
  [mf [& lhr] & body]
  (swap! cache_ assoc-in [mf lhr] body)
  (let [ptrns (get @cache_ mf)]
    `(-set-fn ~(with-meta mf {:tag `MultiMeanderFn})
              (fn [& ~'argsv]
                (m/match ~'argsv
                  ~@(loop [[[l r] & more] ptrns xs []]
                      (if l
                        (recur more (conj xs l (cons `do r)))
                        xs)))))))

(defmulti test-fn)

(defmethod test-fn
  [:add ?x ?y]
  (println ?x ?y)
  (+ ?x ?y))

(test-fn :add 1 2)
;; => 3

(defmethod test-fn
  [:sub ?x ?y]
  (- ?x ?y))

(test-fn :sub 2 1)
;; => 1

(defmethod test-fn
  [:sum . [!xs ...]]
  (reduce + !xs))

(test-fn :sum [1 2 3 4 5 6 7 8 9 10])
;; => 55
