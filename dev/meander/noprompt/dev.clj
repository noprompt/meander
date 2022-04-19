(ns meander.noprompt.dev
  (:require [clojure.test :as t]
            [meander.algorithms.zeta :as m.algorithms]
            [meander.primitive.zeta :as m]
            [meander.primitive.string.zeta :as m.str]
            [meander.primitive.character.zeta :as m.char]
            [meander.random.zeta :as m.random])
  (:import meander.primitive.zeta.Anything
           meander.primitive.zeta.Each
           meander.primitive.zeta.Is
           meander.primitive.zeta.Not
           meander.primitive.zeta.Pick
           meander.primitive.zeta.Predicate
           meander.primitive.zeta.Some
           meander.primitive.character.zeta.AnyCharacter
           meander.primitive.character.zeta.CharacterInRange
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
   -comp [this f]))

;; Implementation
;; ---------------------------------------------------------------------

;; Patterns
;; --------

(extend-type meander.primitive.zeta.Anything
  IQuery
  (-query [this m]
    m)

  IYield
  (-yield [this m]
    (-each m
      (fn [s]
        (-pass m (-set-random s))))))

(extend-type meander.primitive.zeta.Is
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

(extend-type meander.primitive.zeta.Some
  IQuery
  (-query [this m]
    (-some (-query (.-a this) m)
           (-query (.-b this) m)))

  IYield
  (-yield [this m]
    (-some (-yield (.-a this) m)
           (-yield (.-b this) m))))

(extend-type meander.primitive.zeta.Each
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

(extend-type meander.primitive.zeta.Not
  IQuery
  (-query [this m]
    (-comp m
      (fn [s]
        (-query (.-a this) (-pass m s))))))

;; Character
;; ---------------------------------------------------------------------

(defn clamp [value value-min value-max]
  (max value-min (min value value-max)))

;; SEE: https://www.sidefx.com/docs/houdini/expressions/fit01.html
(defn fit01
  "Return the number between new-min and new-max relative to f in
  between 0 and 1. If the f is outside the 0 to 1 range it will be
  clamped."
  [f new-min new-max]
  (+ new-min (* (clamp f 0 1) (- new-max new-min))))

(def CHARACTER_MAX_INT_VALUE
  (int Character/MAX_VALUE))

(extend-type meander.primitive.character.zeta.AnyCharacter
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

(extend-type meander.primitive.character.zeta.CharacterInRange
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
                                i (int (fit01 f min max))]
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

;; ---------------------------------------------------------------------

(extend-type meander.primitive.string.zeta.StringConcat
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



;; Implementation
;; --------------

;; Extended to Clojure values directly.

(extend-type clojure.lang.IPersistentMap
  IState
  (-get-object [this]
    (get this :object))

  (-set-object [this new-object]
    (assoc this :object new-object))

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
          this)))


;; (comment
;;   (defrecord State [object]
;;     IState
;;     (-get-object [this]
;;       object)

;;     (-set-object [this new-object]
;;       (assoc this :object object)))

;;   (defrecord DFSLogic [states]
;;     ILogic
;;     (-pass [this state]
;;       (assoc this :states (list state)))

;;     (-fail [this state]
;;       (assoc this :states ()))

;;     (-each [this f]
;;       (assoc this :states (mapcat (comp :states f) states)))

;;     (-some [this that]
;;       (assoc this :states (concat states (:states that))))

;;     (-pick [this that]
;;       (if (seq states) this that))

;;     (-comp [this f]
;;       (assoc this :states
;;              (keep (fn [state]
;;                      (if (seq (:states (f state)))
;;                        nil
;;                        state))
;;                    states))))

;;   (-query (m/not (m/is 2))
;;           (-pass (->DFSLogic (list))
;;                  (->State 1))))

#_
(comment
  (let [uppercase (m.char/in-range (m/is 65) (m/is (+ 65 26)))
        vowel (m/some (m/is \a) (m/is \e) (m/is \i) (m/is \o) (m/is \u))
        expected [\A \B \C \D \E \F]
        n (count expected)]
    (loop [i 4588001]
      (cond
        (= expected (take n (map :object (-yield uppercase (list (make-state {:seed i}))))))
        i

        (= expected (take n (map :object (-yield uppercase (list (make-state {:seed (- i)}))))))
        (- i)

        :else
        (recur (inc i)))))

  ;;       10 A
  ;;      -50 A B
  ;;    15858 A B C
  ;;   942972 A B C D
  ;; -4588001 A B C D E
)
