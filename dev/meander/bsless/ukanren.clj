(ns meander.bsless.ukanren
  (:require
   [meander.bsless.dev :refer [-return -mzero -interleave >>-]]))

(defprotocol IUnify
  (-unify [this that s]))

(defprotocol ISubstitution
  (-lookup [this v])
  (-extend [this lvar value] "No check")
  (-walk [this u]))

(defrecord LVar [x])
(defn lvar? [x] (instance? LVar x))
(defn lvar
  ([] (lvar (gensym)))
  ([x] (->LVar x)))

(extend-protocol ISubstitution
  clojure.lang.IPersistentMap
  (-lookup [this v] (find this v))
  (-extend [this lvar value] (assoc this lvar value))
  (-walk [s u]
    (if-let [found (and (lvar? u) (-lookup s u))]
      (recur s (val found))
      u)))

(defprotocol IOccursCheck
  (-occurs? [this x s]))

(extend-protocol IOccursCheck
  LVar
  (-occurs? [v x s] (= v (-walk s x)))
  Object (-occurs? [_ _ _] nil)
  nil (-occurs? [_ _ _] nil)
  )

(defn occurs-check
  [u v s]
  (-occurs? u v s)
  #_(when-let [v (and (lvar? u) (-walk s v))]
    (-occurs? u v s)))

(defn ext-s [u v s]
  (if (occurs-check u v s)
    nil
    (-extend s u v)))

(defn unify [u v s]
  (if (identical? u v)
    s
    (let [u (-walk s u) v (-walk s v)]
      (cond
        (and (lvar? u) (lvar? v) (= u v)) s
        (lvar? u) (ext-s u v s)
        (lvar? v) (ext-s v u s)
        (and (sequential? u) (sequential? v))
        (let [s (unify (first u) (first v) s)]
          (and s (unify (rest u) (rest v) s)))
        :else (when (= u v) s)))))

(defn === [u v]
  (fn [s]
    (if-let [s (unify u v s)]
      (-return () s)
      (-mzero ()))))

(defn -disj [g1 g2] (fn disjunction [s] (-interleave (g1 s) (g2 s))))
(defn -conj [g1 g2] (fn conjunction [s] (>>- (g1 s) g2)))

(defn call-fresh
  [f]
  (fn fresh [s]
    ((f (lvar)) s)))
