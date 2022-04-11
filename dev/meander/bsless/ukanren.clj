(ns meander.bsless.ukanren
  (:require
   [clojure.test :as t]
   [meander.bsless.dev :refer [-return -mzero -interleave >>-]]))

(defrecord LVar [x])
(defn lvar? [x] (instance? LVar x))
(defn lvar
  ([] (lvar (gensym)))
  ([x] (->LVar x)))

(defprotocol ISubstitution
  (-lookup [this v])
  (-extend [this lvar value]))

(extend-protocol ISubstitution
  clojure.lang.IPersistentMap
  (-lookup [this v] (find this v))
  (-extend [this lvar value] (assoc this lvar value)))

(defn walk [u s]
  (if-let [[_ found] (and (lvar? u) (-lookup s u))]
    (recur found s)
    u))

(t/deftest walk-test
  (let [v1 (->LVar 1)
        v2 (->LVar 2)
        v3 (->LVar 3)
        s (-> {} (-extend v1 1) (-extend v2 v1))]
    (t/testing "LVar mapped to simple value"
      (t/is (= 1 (walk v1 s))))
    (t/testing "Transitive mapping: v2 -> v1 -> value"
      (t/is (= 1 (walk v2 s))))
    (t/testing "LVar not mapped to anything is returned"
      (t/is (= v3 (walk v3 s))))
    (t/testing "Value not mapped to anything is returned"
      (t/is (= 4 (walk 4 s)))))
  (t/is (= (let [x  (lvar 'x)
                 y  (lvar 'y)
                 s (reduce (fn [m [x y]] (-extend m x y)) {} [[x 5] [y x]])]
             (walk y s))
           5))
  (t/is (= (let [[x y z c b a] (map lvar '[x y z c b a])
                 s (reduce (fn [m [x y]] (-extend m x y)) {} [[x 5] [y x] [z y] [c z] [b c] [a b]])]
             (walk a s))
           5)))

(defprotocol IOccursCheck
  (-occurs? [this x s]))

(extend-protocol IOccursCheck
  LVar
  (-occurs? [v x s] (= v (walk x s))))

(defn occurs-check
  [u v s]
  (when-let [v (and (lvar? u) (walk v s))]
    (-occurs? u v s)))

(t/deftest occurance
  (let [v1 (->LVar 1)
        v2 (->LVar 2)
        v3 (->LVar 3)
        v4 (->LVar 4)
        s (-> {} (-extend v1 1) (-extend v2 v1) (-extend v3 v4))]
    (t/is (false? (occurs-check v1 v2 s)))
    (t/is (false? (occurs-check v1 v1 s)))
    (t/is (false? (occurs-check v2 v1 s)))
    (t/is (false? (occurs-check v2 1 s)))
    (t/is (nil? (occurs-check 1 v2 s)))
    (t/is (false? (occurs-check v3 3 s)))
    (t/is (nil? (occurs-check 3 v3 s)))
    (t/is (false? (occurs-check v2 v3 s)))
    (t/is (true? (occurs-check v4 v3 s)))))

(defn ext-s [u v s]
  (if (occurs-check u v s)
    nil
    (-extend s u v)))

(t/deftest extension
  (t/testing "Can't extend ground variable to another value"
    (let [v1 (lvar 1)
          s (-extend {} v1 1)]
      (ext-s v1 2 s))))

(defn unify [u v s]
  (if (identical? u v)
    s
    (let [u (walk u s) v (walk v s)]
      (cond
        (and (lvar? u) (lvar? v) (= u v)) s
        (lvar? u) (ext-s u v s)
        (lvar? v) (ext-s v u s)
        (and (sequential? u) (sequential? v))
        (let [s (unify (first u) (first v) s)]
          (and s (unify (rest u) (rest v) s)))
        :else (when (= u v) s)))))

(t/deftest unification
  (t/testing "nil"
    (t/testing "Can't unify nil with object"
      (t/is (nil? (unify nil 1 {}))))
    (t/testing "Can unify lvar and nil"
      (let [x (lvar 'x) s (-extend {} x nil)]
        (t/is (= s (unify nil x {})))
        (t/is (= s (unify x nil {}))))))
  (t/testing "Objects"
    (t/testing "Failing unification"
      (t/is (nil? (unify 1 nil {})))
      (t/is (nil? (unify 1 2 {})))
      (t/is (nil? (unify 2 1 {})))
      (t/is (nil? (unify "b" "a" {}))))
    (t/testing "Unmodified unification"
      (t/is (= {} (unify 1 1 {})))
      (t/is (= {} (unify 'a 'a {})))
      (t/is (= {} (unify :a :a {})))
      (t/is (= {} (unify "a" "a" {}))))))

(defn === [u v]
  (fn [s]
    (if-let [s (unify u v s)]
      (-return () s) (-mzero ()))))

(defn -disj [g1 g2] (fn disjunction [s] (-interleave (g1 s) (g2 s))))
(defn -conj [g1 g2] (fn conjunction [s] (>>- (g1 s) g2)))

(defn call-fresh
  [f]
  (fn fresh [s]
    ((f (lvar)) s)))

(t/deftest basic-unification
  (let [ret ((call-fresh (fn [q] (=== q 5))) {})]
    (t/is (= 1 (count ret)))
    (t/is (= 1 (count (first ret))))
    (t/is (= 5 (val (ffirst ret))))))

(t/deftest basic-and-or
  (let [a-and-b
        (-conj
         (call-fresh (fn [a] (=== a 7)))
         (call-fresh (fn [b] (-disj (=== b 5) (=== b 6)))))]
    (t/is (= '((7 5) (7 6)) (map vals (a-and-b {}))))))

(t/deftest contradiction
  (t/testing "Should be no answers for which this holds"
    (t/is
     (nil? (seq ((call-fresh
                  (fn [q]
                    (-conj
                     (=== q 1)
                     (=== q 2))))
                 {}))))))

(t/deftest one-or-branch
  (t/is (= '((7))
           (map vals ((call-fresh
                       (fn [q]
                         (-conj
                          (=== q 7)
                          (-disj (=== q 5) (=== q 7))))) {})))))

(comment
  (defn sixes [x]
    (-disj (=== x 6) (fn [s] (fn [] ((sixes x) s)))))

  (defn sixes [x]
    (-disj (=== x 6) (fn [s] ((sixes x) s))))

  (def x (take 2 ((call-fresh sixes) {})))
  (type (second x))

  (defn sixes [x]
    (-disj (=== x 6) (fn sc [s] (fn $ [] ((sixes x) s)))))

  (take 33 ((call-fresh sixes) {}))


  ((call-fresh
    (fn [q]
      (-conj
       (=== q 7)
       (-disj (=== q 5) (=== q 7))))) {})

  ((fn [s]
     (let [q (->LVar 'q)]
       (>>-
        (>>- s (=== q 1))
        (fn [a12821]
          (-interleave
           (>>- s (=== q 1))
           (>>- a12821 (=== q 2)))))))
   {})

  )
