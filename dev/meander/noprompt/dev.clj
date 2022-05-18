(ns meander.noprompt.dev
  (:require [clojure.test :as t]
            [clojure.set :as set]
            [meander.noprompt.util :as m.util]
            [meander.algorithms.zeta :as m.algorithms]
            [meander.primitive.zeta :as m]
            [meander.primitive.character.zeta :as m.char]
            [meander.primitive.hash-map.zeta :as m.hash-map]
            [meander.primitive.hash-set.zeta :as m.hash-set]
            [meander.primitive.integer.zeta :as m.int]
            [meander.primitive.string.zeta :as m.str]
            [meander.random.zeta :as m.random])
  (:import meander.primitive.zeta.Anything
           meander.primitive.zeta.Each
           meander.primitive.zeta.Is
           meander.primitive.zeta.LogicVariable
           meander.primitive.zeta.Not
           meander.primitive.zeta.Pick
           meander.primitive.zeta.Predicate
           meander.primitive.zeta.Project
           meander.primitive.zeta.Reference
           meander.primitive.zeta.Rule
           meander.primitive.zeta.Some
           meander.primitive.zeta.With
           meander.primitive.hash_map.zeta.HashMapEmpty
           meander.primitive.hash_map.zeta.HashMapEntry
           meander.primitive.hash_map.zeta.HashMapAssoc
           meander.primitive.hash_map.zeta.HashMapMerge
           meander.primitive.hash_set.zeta.HashSetAny
           meander.primitive.hash_set.zeta.HashSetEmpty
           meander.primitive.hash_set.zeta.HashSetConj
           meander.primitive.hash_set.zeta.HashSetUnion
           meander.primitive.hash_set.zeta.HashSetIntersection
           meander.primitive.character.zeta.AnyCharacter
           meander.primitive.character.zeta.CharacterInRange
           meander.primitive.integer.zeta.AnyInteger
           meander.primitive.integer.zeta.IntegerInRange
           #_meander.primitive.real.zeta.AnyReal
           #_meander.primitive.real.zeta.RealInRange
           meander.primitive.sequence.zeta.SequenceCons
           meander.primitive.sequence.zeta.SequenceConcat
           meander.primitive.sequence.zeta.SequenceEmpty
           meander.primitive.sequence.zeta.SequenceSeqCast
           meander.primitive.sequence.zeta.SequenceVectorCast
           meander.primitive.string.zeta.AnyString
           meander.primitive.string.zeta.StringConcat))

(defprotocol IQuery
  :extend-via-metadata true
  (-query [pattern logicm]))

(defprotocol IYield
  :extend-via-metadata true
  (-yield [pattern logicm]))

(defprotocol IRedex
  :extend-via-metadata true
  (-redex [pattern logicm]))

(defprotocol IState
  :extend-via-metadata true
  (-get-object [this])
  (-set-object [this new-object])
  (-get-variable [this variable unbound])
  (-set-variable [this variable value])
  (-get-reference [this reference not-found])
  (-set-reference [this reference new-definition])
  (-set-random [this]))

(defprotocol ILogic
  :extend-via-metadata true
  ;; True/In
  (-pass [this state])
  ;; False/Not In
  (-fail [this state])
  ;; And/All/Intersection
  (^{:style/indent 1}
   -each [this f])
  ;; Or/Some/Union
  (-some [this that])
  ;; Pick/XOR
  (-pick [this that])
  ;; Not/Complement
  (^{:style/indent 1}
   -comp [this f])
  ;; Sentinel value
  (-unbound [this]))

;; Implementation
;; ---------------------------------------------------------------------

;; Patterns
;; --------

(extend-type Anything
  IQuery
  (-query [this m]
    m)

  ;; FIXME: For each state in m produce an infinite sequence. 
  IYield
  (-yield [this m]
    (-each m
      (fn [s]
        (-pass m (-set-random s))))))

(extend-type Is
  IQuery
  (-query [this m]
    (-each m
      (fn [s]
        (let [x (.-x this)
              y (-get-object s)]
          (if (= x y)
            (-pass m s)
            (-fail m s))))))

  IYield
  (-yield [this m]
    (-each m
      (fn [s]
        (-pass m (-set-object s (.-x this)))))))

(extend-type Some
  IQuery
  (-query [this m]
    (-some (-query (.-a this) m)
           (-query (.-b this) m)))

  IYield
  (-yield [this m]
    (-some (-yield (.-a this) m)
           (-yield (.-b this) m))))

(extend-type Each
  IQuery
  (-query [this m]
    (-each (-query (.-a this) m)
      (fn [s]
        (-query (.-b this) (-pass m s)))))

  IYield
  (-yield [this m]
    (-some (-each (-yield (.-a this) m)
             (fn [s]
               (let [ms (-pass m s)]
                 (-each (-query (.-b this) (-pass m s))
                   (fn [_] ms)))))
           (-each (-yield (.-b this) m)
             (fn [s]
               (let [ms (-pass m s)]
                 (-each (-query (.-a this) (-pass m s))
                   (fn [_] ms))))))))

(extend-type Not
  IQuery
  (-query [this m]
    (-comp m
      (fn [s]
        (-query (.-a this) (-pass m s)))))

  IYield
  (-yield [this m]
    (-comp m
      (fn [s]
        (-yield (.-a this) (-pass m s))))))

(extend-type LogicVariable
  IQuery
  (-query [this m]
    (let [unbound (-unbound m)]
      (-each m
        (fn [s]
          (let [x (-get-object s)
                y (-get-variable s this unbound)]
            (if (identical? y unbound)
              (-pass m (-set-variable s this x))
              (if (= x y)
                (-pass m s)
                (-fail m s))))))))

  IYield
  (-yield [this m]
    (let [unbound (-unbound m)]
      (-each m
        (fn [s]
          (let [x (-get-variable s this unbound)]
            (if (identical? x unbound)
              (-fail m s)
              (-pass m (-set-object s x)))))))))

(extend-type Reference
  IQuery
  (-query [this m]
    (-each m
      (fn [s]
        (if-some [p (-get-reference s this nil)]
          (-query p (-pass m s))
          (-fail m s)))))

  IYield
  (-yield [this m]
    (-each m
      (fn [s]
        (if-some [p (-get-reference s this nil)]
          (-yield p (-pass m s))
          (-fail m s))))))


(extend-type With
  IQuery
  (-query [this m]
    (-each m
      (fn [s1]
        (let [s2 (reduce (fn [s2 [k v]]
                           (-set-reference s2 k v))
                         s1
                         (.-index this))]
          (-each (-query (.-a this) (-pass m s2))
            (fn [s3]
              (-pass m (reduce
                        (fn [s4 k]
                          (-set-reference s4 k (-get-reference s1 k nil)))
                        s3
                        (keys (.-index this))))))))))

  IYield
  (-yield [this m]
    (-each m
      (fn [s1]
        (let [s2 (reduce (fn [s2 [k v]]
                           (-set-reference s2 k v))
                         s1
                         (.-index this))]
          (-each (-yield (.-a this) (-pass m s2))
            (fn [s3]
              (-pass m (reduce
                        (fn [s4 k]
                          (-set-reference s4 k (-get-reference s1 k nil)))
                        s3
                        (keys (.-index this)))))))))))

(extend-type Project
  IQuery
  (-query [this m]
    (-each m
      (fn [s]
        (-each (-yield (.-y this) (-pass m s))
          (fn [sy]
            (let [x (-get-object sy)]
              (-query (.-a this)
                      (-query (.-q this) (-pass m (-set-object s x))))))))))

  IYield
  (-yield [this m]
    (-each m
      (fn [s]
        (-each (-yield (.-y this) (-pass m s))
          (fn [sy]
            (let [x (-get-object sy)]
              (-yield (.-a this)
                      (-query (.-q this) (-pass m (-set-object s x)))))))))))

(extend-type Rule
  IQuery
  (-query [this m]
    (-query (.-q this) m))

  IYield
  (-yield [this m]
    (-yield (.-y this) m))

  IRedex
  (-redex [this m]
    (-yield this (-query this m))))

;; Integer
;; ---------------------------------------------------------------------

(extend-type AnyInteger
  IQuery
  (-query [this m]
    (-each m
      (fn [s]
        (let [x (-get-object s)]
          (if (integer? x)
            (-pass m s)
            (-fail m s))))))

  IYield
  (-yield [this m]
    (-yield (m.int/in-range (m/is Long/MIN_VALUE) (m/is Long/MAX_VALUE)) m)))

(extend-type IntegerInRange
  IQuery
  (-query [this m]
    (-each m
      (fn [s]
        (let [x (-get-object s)]
          (if (integer? x)
            ;; Yield min.
            (-each (-yield (.-min this) (-pass m s))
              (fn [s-min]
                (let [min (-get-object s-min)]
                  (if (integer? min)
                    ;; Yield max (with s not s-min).
                    (-each (-yield (.-max this) (-pass m s))
                      (fn [s-max]
                        (let [max (-get-object s-max)]
                          ;; Max is exclusive.
                          (if (and (integer? max) (< min max))
                            (-pass m s)
                            ;; Max was invalid.
                            (-fail m s)))))
                    ;; Min was invalid
                    (-fail m s)))))
            ;; Object was not a character.
            (-fail m s))))))

  IYield
  (-yield [this m]
    (-each (-yield (.-min this) m)
      (fn [s-min]
        (let [x (-get-object s-min)]
          (if (integer? x)
            (-each (-yield (.-max this) (-pass m s-min))
              (fn [s-max]
                (let [y (-get-object s-max)]
                  (if (integer? y)
                    (if (< x y)
                      (let [s-rnd (-set-random s-max)
                            f (-get-object s-rnd)
                            i (long (m.util/fit01 f x y))]
                        (-some (-pass m (-set-object s-rnd i))
                               (if (== i x)
                                 (-yield (m.int/in-range (m/is (inc x)) (m/is y)) (-pass m s-rnd))
                                 (-some (-yield (m.int/in-range (.-min this) (m/is i)) (-pass m s-rnd))
                                        (-yield (m.int/in-range (m/is (inc i)) (.-max this)) (-pass m s-rnd))))))
                      ;; Invalid range
                      (-fail m s-max))
                    ;; Invalid max
                    (-fail m s-max)))))
            ;; Invalid min
            (-fail m s-min)))))))

;; Character
;; ---------------------------------------------------------------------

(def CHARACTER_MAX_INT_VALUE
  (int Character/MAX_VALUE))

(extend-type AnyCharacter
  IQuery
  (-query [this m]
    (-each m
      (fn [s]
        (let [x (-get-object s)]
          (if (char? x)
            (-pass m s)
            (-fail m s))))))

  IYield
  (-yield [this m]
    (-yield (m.char/in-range (m/is 0) (m/is CHARACTER_MAX_INT_VALUE)) m)))

(extend-type CharacterInRange
  IQuery
  (-query [this m]
    (-each m
      (fn [s]
        (let [x (-get-object s)]
          (if (char? x)
            (let [i (int x)]
              ;; Yield min.
              (-each (-yield (.-min this) (-pass m s))
                (fn [s-min]
                  (let [min (-get-object s-min)]
                    (if (and (nat-int? min) (<= min i))
                      ;; Yield max (with s not s-min).
                      (-each (-yield (.-max this) (-pass m s))
                        (fn [s-max]
                          (let [max (-get-object s-max)]
                            ;; Max is exclusive.
                            (if (and (nat-int? max) (< i max))
                              (-pass m s)
                              ;; Max was invalid.
                              (-fail m s)))))
                      ;; Min was invalid
                      (-fail m s))))))
            ;; Object was not a character.
            (-fail m s))))))

  IYield
  (-yield [this m]
    (-each m
      (fn [s]
        ;; Yield min.
        (-each (-yield (.-min this) (-pass m s))
          (fn [s-min]
            (let [min (-get-object s-min)]
              (if (nat-int? min)
                ;; Yield max.
                (-each (-yield (.-max this) (-pass m s-min))
                  (fn [s-max]
                    (let [max (-get-object s-max)]
                      (if (and (nat-int? max) (<= max CHARACTER_MAX_INT_VALUE))
                        (if (< min max)
                          ;; Range with max - min elements.
                          (let [s-rnd (-set-random s-max)
                                f (-get-object s-rnd)
                                i (int (m.util/fit01 f min max))]
                            (-some (-pass m (-set-object s-rnd (char i)))
                                   (if (== i min)
                                     (-yield (m.char/in-range (m/is (inc min)) (m/is max)) (-pass m s-rnd))
                                     (-some (-yield (m.char/in-range (.-min this) (m/is i)) (-pass m s-rnd))
                                            (-yield (m.char/in-range (m/is (inc i)) (.-max this)) (-pass m s-rnd))))))
                          ;; Invalid Range
                          (-fail m s))
                        ;; Max was invalid.
                        (-fail m s)))))
                ;; Min was invalid
                (-fail m s)))))))))

;; String
;; ---------------------------------------------------------------------

(extend-type AnyString
  IQuery
  (-query [this m]
    (-each m
      (fn [s]
        (let [x (-get-object s)]
          (if (string? x)
            (-pass m s)
            (-fail m s))))))

  IYield
  (-yield [this m]
    (-yield (let [%s (m/% (gensym))]
              (m/with {%s (m/some (m/str (m.char/any)) (m/str %s (m.char/any)))}
                (m/some (m/str) %s)))
            m)))

(extend-type StringConcat
  IQuery
  (-query [this m]
    (-each m
      (fn [s]
        (let [x (-get-object s)]
          (if (string? x)
            (reduce -some (-fail m s)
                    (map (fn [[a b]]
                           (-each (-query (.-a this) (-pass m (-set-object s a)))
                             (fn [s]
                               (-query (.-b this) (-pass m (-set-object s b))))))
                         (m.algorithms/string-partitions x 2)))
            (-fail m s))))))

  IYield
  (-yield [this m]
    (-each (-yield (.-a this) m)
      (fn [s]
        (let [a (-get-object s)]
          (-each (-yield (.-b this) (-pass m s))
            (fn [s]
              (let [b (-get-object s)]
                (-pass m (-set-object s (str a b)))))))))))

;; Sequence
;; ---------------------------------------------------------------------

(extend-type SequenceEmpty
  IQuery
  (-query [this m]
    (-each m
      (fn [s]
        (let [x (-get-object s)]
          (if (sequential? x)
            (-pass m s)
            (-fail m s))))))

  IYield
  (-yield [this m]
    (-each m
      (fn [s]
        (-some (-pass m (-set-object s ()))
               (-pass m (-set-object s [])))))))

(extend-type SequenceCons
  IQuery
  (-query [this m]
    (-each m
      (fn [s]
        (let [x (-get-object s)]
          (if (sequential? x)
            (if (seq x)
              (let [head (first x)
                    tail (rest x)]
                (-each (-query (.-head this) (-pass m (-set-object s head)))
                  (fn [s]
                    (-query (.-tail this) (-pass m (-set-object s tail))))))
              (-fail m s))
            (-fail m s))))))

  IYield
  (-yield [this m]
    (-each (-yield (.-head this) m)
      (fn [s]
        (let [x (-get-object s)]
          (-each (-yield (.-tail this) (-pass m s))
            (fn [s]
              (let [y (-get-object s)]
                (if (sequential? y)
                  (-pass m (-set-object s (cons x y)))
                  (-fail m s))))))))))

(extend-type SequenceConcat
  IQuery
  (-query [this m]
    (-each m
      (fn [s]
        (let [x (-get-object s)]
          (if (sequential? x)
            (reduce
             (fn [m [a b]]
               (-some m
                      (-each (-query (.-a this) (-pass m (-set-object s a)))
                        (fn [s]
                          (-query (.-b this) (-pass m (-set-object s b)))))))
             (-fail m s)
             (m.algorithms/partitions 2 x)))))))

  IYield
  (-yield [this m]
    (-each (-yield (.-a this) m)
      (fn [s]
        (let [x (-get-object s)]
          (if (sequential? x)
            (-each (-yield (.-b this) (-pass m s))
              (fn [s]
                (let [y (-get-object s)]
                  (if (sequential? y)
                    (-pass m (-set-object s (concat x y)))
                    (-fail m s)))))
            (-fail m s)))))))

(extend-type SequenceSeqCast
  IQuery
  (-query [this m]
    (-each m
      (fn [s]
        (let [x (-get-object s)]
          (if (or (seq? x) (nil? x))
            (-query (.-a this) (-pass m s))
            (-fail m s))))))

  IYield
  (-yield [this m]
    (-each (-yield (.-a this) m)
      (fn [s]
        (let [x (-get-object s)]
          (if (seqable? x)
            (-pass m (-set-object s (seq x)))
            (-fail m s)))))))

(extend-type SequenceVectorCast
  IQuery
  (-query [this m]
    (-each m
      (fn [s]
        (let [x (-get-object s)]
          (if (vector? x)
            (-query (.-a this) (-pass m s))
            (-fail m s))))))

  IYield
  (-yield [this m]
    (-each (-yield (.-a this) m)
      (fn [s]
        (let [x (-get-object s)]
          (if (seqable? x)
            (-pass m (-set-object s (vec x)))
            (-fail m s)))))))


;; HashMap
;; ---------------------------------------------------------------------

(extend-type HashMapEmpty
  IQuery
  (-query [this m]
    (-each m
      (fn [s]
        (let [x (-get-object s)]
          (if (= x {})
            (-pass m s)
            (-fail m s))))))

  IYield
  (-yield [this m]
    (-each m
      (fn [s]
        (-pass m (-set-object s {}))))))

(extend-type HashMapAssoc
  IQuery
  (-query [this m]
    (let [entry (m.hash-map/entry (.-k this) (.-v this))]
      (-each m
        (fn [s]
          (let [x (-get-object s)]
            (if (map? x)
              (case (count x)
                0 (-fail m s)

                1 (let [e (first x)
                        x-e (dissoc x (key e))]
                    (-each (-query entry (-pass m (-set-object s e)))
                      (fn [s]
                        (-query (.-m this) (-pass m (-set-object s x-e))))))

                ;; else
                (reduce -some
                        (map
                         (fn [e]
                           (let [x-e (dissoc x (key e))]
                             (-each (-query entry (-pass m (-set-object s e)))
                               (fn [s]
                                 (-query (.-m this) (-pass m (-set-object s x-e)))))))
                         x)))
              (-fail m s)))))))

  IYield
  (-yield [this m]
    (-each (-yield (m.hash-map/entry (.-k this) (.-v this)) m)
      (fn [s]
        (let [e (-get-object s)]
          (-each (-yield (.-m this) (-pass m s))
            (fn [s]
              (let [x (-get-object s)]
                (if (map? x)
                  (-pass m (-set-object s (conj x e)))
                  (-fail m s))))))))))

(extend-type HashMapEntry
  IQuery
  (-query [this m]
    (-each m
      (fn [s]
        (let [x (-get-object s)]
          (if (map-entry? x)
            (-each (-query (.-k this) (-pass m (-set-object s (key x))))
              (fn [s]
                (-query (.-v this) (-pass m (-set-object s (val x))))))
            (-fail m s))))))

  IYield
  (-yield [this m]
    (-each (-yield (.-k this) m)
      (fn [s]
        (let [k (-get-object s)]
          (-each (-yield (.-v this) (-pass m s))
            (fn [s]
              (let [v (-get-object s)]
                (-pass m (-set-object s (clojure.lang.MapEntry. k v)))))))))))

(extend-type HashMapMerge
  IQuery
  (-query [this m]
    (-each m
      (fn [s]
        (let [x (-get-object s)]
          (if (map? x)
            (if (zero? (count x))
              (-each (-query (.-m1 this) (-pass m (-set-object s {})))
                (fn [s]
                  (-query (.-m2 this) (-pass m (-set-object s {})))))
              ;; Not supplying val here is safe because
              ;; (map-partitions m 2) will give us a sequence of at
              ;; least 2 when the key count of m is greater than 0.
              (reduce -some
                      (map (fn [[a b]]
                             (-each (-query (.-m1 this) (-pass m (-set-object s a)))
                               (fn [s]
                                 (-query (.-m2 this) (-pass m (-set-object s b))))))
                           (m.algorithms/map-partitions x 2))))
            (-fail m s))))))

  IYield
  (-yield [this m]
    (-each (-yield (.-m1 this) m)
      (fn [s]
        (let [x (-get-object s)]
          (if (map? x)
            (-each (-yield (.-m2 this) (-pass m s))
              (fn [s]
                (let [y (-get-object s)]
                  (if (map? y)
                    (-pass m (-set-object s (merge x y)))
                    (-fail m s)))))
            (-fail m s)))))))

;; HashSet
;; ---------------------------------------------------------------------

(extend-type HashSetAny)

(extend-type HashSetEmpty
  IQuery
  (-query [this m]
    (-each m
      (fn [s]
        (let [x (-get-object s)]
          (if (= x #{})
            (-pass m s)
            (-fail m s))))))

  IYield
  (-yield [this m]
    (-each m
      (fn [s]
        (-pass m (-set-object s #{}))))))

(extend-type HashSetConj
  IQuery
  (-query [this m]
    (-each m
      (fn [s]
        (let [x (-get-object s)]
          (if (set? x)
            (if (< 0 (count x))
              (map (fn [e]
                     (let [y (disj x e)]
                       (-query (.-e this) (-pass m (-set-object s e)))))
                   x))
            (-fail m s))))))

  IYield
  (-yield [this m]
    (-each (-yield (.-s this) m)
      (fn [s]
        (let [x (-get-object s)]
          (if (set? x)
            (-each (-yield (.-e this) (-pass m s))
              (fn [s]
                (let [y (-get-object s)]
                  (-pass m (-set-object s (conj x y))))))
            (-fail m s)))))))

(extend-type HashSetUnion
  IQuery
  (-query [this m]
    (-each m
      (fn [s]
        (let [x (-get-object s)]
          (if (set? x)
            (if (zero? (count x))
              (-each (-query (.-s1 this) (-pass m (-set-object s #{})))
                (fn [s]
                  (-query (.-s2 this) (-pass m (-set-object s #{})))))
              (reduce -some
                      (map (fn [[a b]]
                             (-each (-query (.-s1 this) (-pass m (-set-object s a)))
                               (fn [s]
                                 (-query (.-s2 this) (-pass m (-set-object s b))))))
                           (m.algorithms/set-partitions 2 x))))
            (-fail m s))))))

  IYield
  (-yield [this m]
    (-each (-yield (.-s1 this) m)
      (fn [s]
        (let [x (-get-object s)]
          (if (set? x)
            (-each (-yield (.-s2 this) (-pass m s))
              (fn [s]
                (let [y (-get-object s)]
                  (if (set? y)
                    (-pass m (-set-object s (set/union x y)))
                    (-fail m s)))))
            (-fail m s)))))))

(extend-type HashSetIntersection)

;; Logic/State implementation
;; ---------------------------------------------------------------------

;; Extended to Clojure values directly.

(extend-type clojure.lang.IPersistentMap
  IState
  (-get-object [this]
    (get this :object))

  (-set-object [this new-object]
    (assoc this :object new-object))

  (-get-variable [this variable not-found]
    (get-in this [:variables variable] not-found))

  (-set-variable [this variable value]
    (assoc-in this [:variables variable] value))

  (-get-reference [this reference not-found]
    (get-in this [:references reference] not-found))

  (-set-reference [this reference new-definition]
    (assoc-in this [:references reference] new-definition))

  (-set-random [this]
    (let [r1 (get this :random)
          r2 (nth (m.random/split-n r1 1) 0)
          x (m.random/rand-double r1)]
      (assoc this :object x :random r2))))

(defn make-state [{:keys [object seed]}]
  (let [seed (or seed (long (rand Long/MAX_VALUE)))
        ;; random (java.util.Random. seed)
        random (m.random/make-random seed)]
    {:object object
     :random random
     :seed seed}))

(def unbound
  (reify))

(extend-type clojure.lang.ISeq
  ILogic
  (-pass [this state]
    (list state))

  (-fail [this state]
    ())

  (-each [this f]
    (lazy-seq (m.algorithms/mix* (map f this))))

  (-some [this that]
    (m.algorithms/mix this that))

  (-pick [this that]
    (or (seq this) that))

  (-comp [this f]
    (keep (fn [state]
            (if (seq (f state))
              nil
              state))
          this))

  (-unbound [this]
    unbound))

;; Scratch
;; ---------------------------------------------------------------------

;; (-yield (m/list (m/? 1) (m/? 1))
;;         (-query (m/cons (m/? 1) (m/? 1)) (list (make-state {:object [[1 2 3] 1 2 3]}))))

;; (project 3 *i
;;          (m/with {%base (project *i 0 _)
;;                   %step (project *i (+ *i 1) (project *i >i %main))
;;                   %main (some %base %step)}
;;            %main))

;; (keep (fn [seed] 
;;         (let [mn 0
;;               mx 127
;;               result1 (map (fn [i] (bit-and (* i seed) mx)) (range mn mx))
;;               result2 (into #{} result1)]
;;           (when (= mx (count result2))
;;             (take 10 result1))))
;;       (range 127))

;; (let [?x (m/? '?x)
;;       ?xs (m/? '?xs)
;;       p1 (m/cons ?x ?xs)
;;       p2 (m/cons ?xs (m/concat ?xs ?xs))
;;       s (make-state {:object '(1 4 3)})
;;       m (list s)]
;;   (-yield p2 (-query p1 m)))

;; (-yield (m/assoc (m/is {})
;;                  (m/is :a) (m.char/in-range (m/is 65) (m/is 92))
;;                  (m/is :b) (m/is 2))
;;         (list (make-state {})))
;; (-query (m.hash-map/assoc (m.hash-map/empty) (m/? 1) (m/? 2))
;;         (list {:object {:a 1}}))

;; (let [?x (m/? 'x)
;;       ?y (m/? 'y)]
;;   (-yield (m.hash-map/merge ?x ?y)
;;           (-query (m.hash-map/merge ?x ?y)
;;                   (list (make-state {:object {:foo 1 :bar 2}})))))

;;  (with {%equal-pair (omit [?_ ?_])}
;;         %equal-pair-list (cons %equal-pair (some (empty) %equal-pair-list))}
;;    %equal-pair-list)

;; (-query (m/let [(m/? 'x) (m/vec (m/list (m/is 20) (m/is 10)))]
;;           (m/anything))
;;         (list (make-state {})))

;; (let [?x (m/? 'x)
;;       ?y (m/? 'y)]
;;   (-yield (m.hash-set/union ?x ?y)
;;           (-query (m.hash-set/union ?x ?y)
;;                   (list (make-state {:object #{:foo 1 :bar 2}})))))

;; (let [?x (m/? 'x)
;;       ?y (m/? 'y)]
;;   (-redex 
;;    (m/rule
;;     (m/vec (m/cons ?x ?y))
;;     (m/cons ?x (m/seq ?y)))
;;    (list (make-state {:object [1 2 3 4 5 6]}))))
