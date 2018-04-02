(ns meander.dev.syntax
  (:require [clojure.spec.alpha :as s]))


(defn quote-form?
  [x]
  (and (seq? x)
       (= (first x) 'quote)))


(s/def :meander.syntax/lit
  (s/conformer
   (fn [x]
     (if (or (not (or (= x '.)
                      (= x '...)))
             (quote-form? x))
       x
       ::s/invalid))
   identity))


(defn var-name?
  [s]
  (some? (re-matches #"\?\S+" s)))


(defn var-symbol?
  [x]
  (and (simple-symbol? x)
       (var-name? (name x))))


(s/def :meander.syntax/var
  (s/conformer
   (fn [x]
     (if (var-symbol? x)
       x
       ::s/invalid))
   identity))


(defn mut-name?
  [s]
  (some? (re-matches #"!\S+" s)))


(defn mut-symbol?
  [x]
  (and (simple-symbol? x)
       (mut-name? (name x))))


(s/def :meander.syntax/mut
  (s/conformer
   (fn [x]
     (if (mut-symbol? x)
       x
       ::s/invalid))
   identity))


(defn ref-name?
  [s]
  (some? (re-matches #"%\S+" s)))


(defn ref-symbol?
  [x]
  (and (simple-symbol? x)
       (ref-name? (name x))))


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
        :mut :meander.syntax/mut
        :ref :meander.syntax/ref
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


(s/def :meander.syntax/repitition
  (s/cat
   :init :meander.syntax/cat
   :ellipsis '#{...}))


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
         x))
   (parse-term* term)))
