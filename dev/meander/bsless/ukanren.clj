(ns meander.bsless.ukanren
  (:require
   [meander.bsless.dev :refer [-return -mzero -interleave >>-]]))

(defrecord LVar [x])
(defn lvar? [x] (instance? LVar x))

(defn walk [u s]
  (if-let [pr (and (lvar? u) (s u))]
    (recur pr s)
    u))

(defn ext-s [x v s] (assoc s x v))

(defn unify [u v s]
  (cond
    (and (lvar? u) (lvar? v) (= u v)) s
    (lvar? u) (ext-s u v s)
    (lvar? v) (ext-s v u s)
    (and (seqable? u) (seqable? v))
    (let [s (unify (first u) (first v) s)]
      (and s (unify (rest u) (rest v) s)))
    :else (and (= u v) s)))

(defn === [u v]
  (fn [s]
    (if-let [s (unify u v s)]
      (-return () s) (-mzero ()))))

(defn -disj [g1 g2] (fn disjunction [s] (-interleave (g1 s) (g2 s))))
(defn -conj [g1 g2] (fn conjunction [s] (>>- (g1 s) g2)))

(defn call-fresh
  [f]
  (fn fresh [s]
    ((f (->LVar (gensym))) s)))


(comment
  ((call-fresh (fn [q] (=== q 5))) {})

  (def a-and-b
    (-conj
     (call-fresh (fn [a] (=== a 7)))
     (call-fresh (fn [b] (-disj (=== b 5) (=== b 6))))))

  (a-and-b {})

  (defn sixes [x]
    (-disj (=== x 6) (fn [s] (fn [] ((sixes x) s)))))

  (defn sixes [x]
    (-disj (=== x 6) (fn [s] ((sixes x) s))))

  (def x (take 2 ((call-fresh sixes) {})))

  (defn sixes [x]
    (-disj (=== x 6) (fn sc [s] (fn $ [] ((sixes x) s)))))
  (take 33 ((call-fresh sixes) {}))
  )
