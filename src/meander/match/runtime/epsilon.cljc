(ns meander.match.runtime.epsilon
  "Functions used by the pattern matcher at runtime."
  (:require [meander.util.epsilon :as r.util]
            [clojure.walk :as walk]
            [clojure.zip :as zip]))

(def permutations r.util/permutations)
(def k-combinations r.util/k-combinations)
(def partitions r.util/partitions)
(def coll-zip r.util/coll-zip)
(def zip-next-seq r.util/zip-next-seq)

(def FAIL
  "Special value signaling a match failure. Generated code will often
  utilize this value for control flow purposes."
  (reify))

(defn fail?
  "true if the `x` is the special runtime value `FAIL`, false
  otherwise."
  [x]
  (identical? x FAIL))

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
           (run-star-vec-search (subvec coll (min n (count coll))) rets n body-f then-f)))
       (body-f rets xs))
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
           (run-star-js-array-search (.slice coll (min n (count coll))) rets n body-f then-f)))
       (body-f rets xs))
      (if (seq coll)
        nil
        (then-f rets)))))
