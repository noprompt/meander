(ns meander.dev.syntax
  (:require [clojure.spec.alpha :as s]))


(defn quote-form?
  [x]
  (and (seq? x)
       (= (first x) 'quote)))


(defn partition-symbol?
  [x]
  (= x '.))


(defn zero-or-more-symbol?
  [x]
  (= x '...))


(defn k-or-more-symbol?
  [x]
  (and (simple-symbol? x)
       (some? (re-matches #"..\d+" (name x)))))


(defn wildcard-symbol?
  [x]
  (= x '_))


(defn var-name?
  [s]
  (some? (re-matches #"\?\S+" s)))


(defn var-symbol?
  [x]
  (and (simple-symbol? x)
       (var-name? (name x))))


(defn mem-name?
  [s]
  (some? (re-matches #"!\S+" s)))


(defn mem-symbol?
  [x]
  (and (simple-symbol? x)
       (mem-name? (name x))))


(defn ref-name?
  [s]
  (some? (re-matches #"%\S+" s)))


(defn ref-symbol?
  [x]
  (and (simple-symbol? x)
       (ref-name? (name x))))


(s/def :meander.syntax/partition-symbol
  (s/conformer
   (fn [x]
     (if (partition-symbol? x)
       x
       ::s/invalid))
   identity))


(s/def :meander.syntax/zero-or-more-symbol
  (s/conformer
   (fn [x]
     (if (zero-or-more-symbol? x)
       x
       ::s/invalid))
   identity))


(s/def :meander.syntax/k-or-more-symbol
  (s/conformer
   (fn [x]
     (if (k-or-more-symbol? x)
       x
       ::s/invalid))
   identity))


(s/def :meander.syntax/lit
  (s/conformer
   (fn [x]
     (if (or (not (partition-symbol? x))
             (quote-form? x))
       x
       ::s/invalid))
   identity))


(s/def :meander.syntax/any
  (s/conformer
   (fn [x]
     (if (wildcard? x)
       x
       ::s/invalid))
   identity))


(s/def :meander.syntax/var
  (s/conformer
   (fn [x]
     (if (var-symbol? x)
       x
       ::s/invalid))
   identity))


(s/def :meander.syntax/mem
  (s/conformer
   (fn [x]
     (if (mem-symbol? x)
       x
       ::s/invalid))
   identity))


(s/def :meander.syntax/ref
  (s/conformer
   (fn [x]
     (if (ref-symbol? x) 
       x
       ::s/invalid))
   identity))


(s/def :meander.syntax/quote
  quote-form?)


(s/def :meander.syntax/term
  (s/or :var :meander.syntax/var
        :mem :meander.syntax/mem
        :ref :meander.syntax/ref
        :any :meander.syntax/any
        :vec :meander.syntax/vec
        :def :meander.syntax/def
        :quo :meander.syntax/quote
        :seq :meander.syntax/seq
        :lit :meander.syntax/lit))


(s/def :meander.syntax/def
  (s/cat
   :def :meander.syntax/elem
   :as #{:as}
   :ref :meander.syntax/ref))


(s/def :meander.syntax/cat
  (s/+ :meander.syntax/term))


(s/def :meander.syntax/zero-or-more
  (s/cat
   :init :meander.syntax/cat
   :sym :meander.syntax/zero-or-more-symbol))


(s/def :meander.syntax/k-or-more
  (s/cat
   :init :meander.syntax/cat
   :sym :meander.syntax/k-or-more-symbol))


(s/def :meander.syntax/partition
  (s/cat
   :left (s/alt
          :rep :meander.syntax/repitition
          :cat :meander.syntax/cat)
   :dot '#{.}
   :right (s/? :meander.syntax/elem)))


(s/def :meander.syntax/elem
  (s/alt
   :part :meander.syntax/partition
   :rep :meander.syntax/repitition
   :repk :meander.syntax/k-or-more
   :cat :meander.syntax/cat))


(s/def :meander.syntax/vec
  (s/and vector? :meander.syntax/elem))


(s/def :meander.syntax/seq
  (s/and seq? :meander.syntax/elem))



(defn parse-term* [term]
  (s/conform :meander.syntax/term term))


(defn has-tag? [x tag]
  (and (vector? x) (= (first x) tag)))


(defn parse-term [term]
  (clojure.walk/prewalk
   (fn f [x]
     (or (if (has-tag? x :part)
           (let [{:keys [left right]} (second x)]
             (if (has-tag? left :cat)
               (cond
                 (nil? right)
                 left

                 (has-tag? right :part)
                 (let [{right-left :left, right-right :right} (second right)]
                   (if (has-tag? right-left :cat)
                     (f [:part (assoc (second x)
                                      :left [:cat (into (second left)
                                                        (second right-left))]
                                      :right right-right)])))
                 (has-tag? right :cat)
                 [:cat (into (second left) (second right))]))))
         (if (has-tag? x :repk)
           (let [{sym :sym :as data} (second x)
                 k (Long/parseUnsignedLong (subs (name sym) 2))]
             [:repk (assoc data :k k)]))
         x))
   (parse-term* term)))
