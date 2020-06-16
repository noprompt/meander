(ns ^:no-doc meander.match.runtime.epsilon
  "Functions used by the pattern matcher at runtime."
  (:require [meander.util.epsilon :as r.util]
            [clojure.walk :as walk]
            [clojure.set :as set]
            [clojure.zip :as zip]))

(def permutations r.util/permutations)
(def k-combinations r.util/k-combinations)
(def ^{:arglists '([s k])} set-k-permutations-with-unselected
  r.util/set-k-permutations-with-unselected)
(def ^{:arglists '([m k])} map-k-permutations-with-unselected
  r.util/map-k-permutations-with-unselected)
(def partitions r.util/partitions)
(def coll-zip r.util/coll-zip)
(def coll-seq r.util/coll-seq)
(def zip-next-seq r.util/zip-next-seq)
(def knit r.util/knit)

(def FAIL
  "Special value signaling a match failure. Generated code will often
  utilize this value for control flow purposes."
  #?(:clj
     (reify
       clojure.lang.Seqable
       (seq [_] nil))

     :cljs
     (reify
       ISeqable
       (-seq [_] nil))))

(defn fail?
  "true if the `x` is the special runtime value `FAIL`, false
  otherwise."
  [x]
  (identical? x FAIL))

(defn run-star-1
  {:style/indent :defn}
  [coll rets body-f then-f]
  (let [result
        (reduce (fn [acc xs]
                  (let [acc (body-f acc [xs])]
                    (if (fail? acc)
                      (reduced FAIL)
                      acc)))
                rets
                coll)]
    (if (fail? result)
      FAIL
      (then-f result))))


(defn run-star-seq
  {:style/indent :defn}
  [coll rets n body-f then-f]
  (loop [coll coll
         rets rets]
    (let [xs (take n coll)]
      (if (= (count xs) n)
        (let [rets (body-f rets xs)]
          (if (fail? rets)
            FAIL
            (recur (drop n coll) rets)))
        (if (seq coll)
          FAIL
          (then-f rets))))))

(defn run-star-seq-search
  {:style/indent :defn}
  [coll rets n body-f then-f]
  (let [xs (take n coll)]
    (if (= (count xs) n)
      (mapcat
       (fn [rets]
         (if (fail? rets)
           nil
           (run-star-seq-search (drop n coll) rets n body-f then-f)))
       (body-f rets xs))
      (if (seq coll)
        nil
        (then-f rets)))))

(defn run-plus-seq-search
  {:style/indent :defn
   :arglists '([coll rets cat-length min-reps body-f then-f])}
  [coll rets n m body-f then-f]
  (let [m*n (* m n)
        xs (take m*n coll)]
    (if (= (count xs) m*n)
      (let [ys (drop m*n coll)]
        (mapcat
         (fn [rets]
           (if (fail? rets)
             nil
             (run-star-seq-search ys rets n body-f then-f)))
         (sequence
          (apply comp
                 (map (fn [chunk]
                        (mapcat (fn [rets]
                                  (if (fail? rets)
                                    nil
                                    (body-f rets chunk)))))
                      (partition n xs)))
          [rets])))
      (if (seq coll)
        nil
        (then-f rets)))))

(defn run-star-vec
  {:style/indent :defn}
  [coll rets n body-f then-f]
  (loop [coll coll
         rets rets]
    (let [xs (subvec coll 0 (min n (count coll)))]
      (if (= (count xs) n)
        (let [rets (body-f rets xs)]
          (if (fail? rets)
            FAIL
            (recur (subvec coll (min n (count coll))) rets)))
        (if (seq coll)
          FAIL
          (then-f rets))))))

(defn run-star-vec-search
  {:style/indent :defn}
  [coll rets n body-f then-f]
  (let [xs (subvec coll 0 (min n (count coll)))]
    (if (= (count xs) n)
      (mapcat
       (fn [rets]
         (if (fail? rets)
           nil
           (run-star-vec-search (subvec coll n) rets n body-f then-f)))
       (body-f rets xs))
      (if (seq coll)
        nil
        (then-f rets)))))

(defn run-plus-vec-search
  {:style/indent :defn
   :arglists '([coll rets cat-length min-reps body-f then-f])}
  [coll rets n m body-f then-f]
  (let [m*n (* m n)
        xs (subvec coll 0 (min m*n (count coll)))]
    (if (= (count xs) m*n)
      (let [ys (subvec coll m*n)]
        (mapcat
         (fn [rets]
           (if (fail? rets)
             nil
             (run-star-vec-search ys rets n body-f then-f)))
         (sequence
          (apply comp
                 (map (fn [chunk]
                        (mapcat (fn [rets]
                                  (if (fail? rets)
                                    nil
                                    (body-f rets chunk)))))
                      (partition n xs)))
          [rets])))
      (if (seq coll)
        nil
        (then-f rets)))))

(defn run-star-js-array
  {:style/indent :defn}
  [coll rets n body-f then-f]
  (loop [coll coll
         rets rets]
    (let [xs (.slice coll 0 (min (.-length coll) n))]
      (if (= (count xs) n)
        (let [rets (body-f rets xs)]
          (if (fail? rets)
            FAIL
            (recur (.slice coll n) rets)))
        (if (seq coll)
          FAIL
          (then-f rets))))))

(defn run-star-js-array-search
  {:style/indent :defn}
  [coll rets n body-f then-f]
  (let [xs (.slice coll 0 (min n (count coll)))]
    (if (= (count xs) n)
      (mapcat
       (fn [rets]
         (if (fail? rets)
           nil
           (run-star-js-array-search (.slice coll n) rets n body-f then-f)))
       (body-f rets xs))
      (if (seq coll)
        nil
        (then-f rets)))))

(defn run-plus-js-array-search
  {:style/indent :defn}
  [coll rets n m body-f then-f]
  (let [m*n (* m n)
        xs (.slice coll 0 (min m*n (count coll)))]
    (if (= (count xs) m*n)
      (let [ys (.slice coll m*n)]
        (mapcat
         (fn [rets]
           (if (fail? rets)
             nil
             (run-star-js-array-search ys rets n body-f then-f)))
         (sequence
          (apply comp
                 (map (fn [chunk]
                        (mapcat (fn [rets]
                                  (if (fail? rets)
                                    nil
                                    (body-f rets chunk)))))
                      (partition n xs)))
          [rets])))
      (if (seq coll)
        nil
        (then-f rets)))))
