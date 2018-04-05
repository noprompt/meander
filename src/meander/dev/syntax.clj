(ns meander.dev.syntax
  (:require [clojure.spec.alpha :as s]
            [clojure.walk :as walk]))


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
     (if (wildcard-symbol? x)
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
        :cap :meander.syntax/cap
        :quo :meander.syntax/quote
        :seq :meander.syntax/seq
        :lit :meander.syntax/lit))


(s/def :meander.syntax/cap
  (s/cat
   :pat :meander.syntax/elem
   :as #{:as}
   :var (s/or :mem :meander.syntax/mem
              :var :meander.syntax/var)))


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
          :rep :meander.syntax/zero-or-more
          :repk :meander.syntax/k-or-more
          :cat :meander.syntax/cat)
   :dot '#{.}
   :right (s/? :meander.syntax/elem)))


(s/def :meander.syntax/elem
  (s/alt
   :part :meander.syntax/partition
   :rep :meander.syntax/zero-or-more
   :repk :meander.syntax/k-or-more
   :cat :meander.syntax/cat))


(s/def :meander.syntax/vec
  (s/and vector? :meander.syntax/elem))


(s/def :meander.syntax/seq
  (s/and seq? :meander.syntax/elem))


(defn parse-term* [term]
  (s/conform :meander.syntax/term term))


(defn tagged? [x]
  (and (vector? x)
       (keyword? (first x))))


(defn has-tag? [x tag]
  (and (vector? x)
       (= (first x) tag)))


(defn cat-cats [[_ xs] [_ ys]]
  [:cat (into (vec xs) ys)])


#_
(ns-unmap *ns* 'expand-pat)

(defmulti expand-pat
  (fn [x]
    (if-some [tag (when (vector? x)
                    (first x))]
      tag
      ::no-tag))
  :default ::no-tag)


(defmethod expand-pat ::no-tag [x]
  x)


(defmethod expand-pat :cat [[_ pats]]
  #_
  (if (= (count pats) 1)
    (first pats))
  (let [[xs ys] (split-with
                 (fn [[tag data]]
                   (if (= tag :cap)
                     (let [{[cap-pat-tag cap-pat-data] :pat} data]
                       (and (not= cap-pat-tag :rep)
                            (not= cap-pat-tag :repk)
                            (and (= cap-pat-tag :cat)
                                 (<= (count cap-pat-data) 1))))
                     true))
                 pats)
        xs (sequence
            (map
             (fn [[tag data :as node]]
               (if (= tag :cap)
                 (let [{[cap-pat-tag cap-pat-data] :pat} data]
                   (if (and (= cap-pat-tag :cat)
                            (= (count cap-pat-data)
                               1))
                     [:cap (assoc data :pat (first cap-pat-data))]
                     node))
                 node)))
            xs)]
    (if (seq xs)
      (if (seq ys)
        [:part
         {:left [:cat xs]
          :right [:cat ys]}]
        [:cat xs])
      (if (seq ys)
        (if (seq (next ys))
          [:part
           {:left (first ys)
            :right [:cat (next ys)]}]
          (first ys))
        [:cat pats]))))


(defmethod expand-pat :cap [[_ cap-data :as cap]]
  (if (has-tag? (:pat cap-data) :any)
    (:var cap-data)
    cap))


(defmethod expand-pat :part [[_ part-data :as part]]
  (let [{:keys [left right]} part-data]
    (if (has-tag? left :cat)
      (cond
        (nil? right)
        left

        (has-tag? right :part)
        (let [{right-left :left, right-right :right} (second right)]
          (if (has-tag? right-left :cat)
            (recur [:part (assoc part-data
                                 :left (cat-cats left right-left)
                                 :right right-right)])))
        (has-tag? right :cat)
        (cat-cats left right)

        :else
        part)
      part)))


(defn expand-rep-init [rep-init]
  (walk/prewalk
   expand-pat
   (cond (has-tag? rep-init :cat)
         rep-init

         (tagged? rep-init)
         [:cat [rep-init]]

         :else
         [:cat rep-init])))


(defmethod expand-pat :rep [[_ rep-data]]
  [:rep (update rep-data :init expand-rep-init)])


(defmethod expand-pat :repk [[_ repk-data]]
  (let [{init :init sym :sym} repk-data]
    [:repk
     (assoc repk-data
            :init (expand-rep-init init)
            :k (Long/parseUnsignedLong (subs (name sym) 2)))]))


(defmethod expand-pat :seq [[_ node :as seq]]
  (if (has-tag? node :part)
    (let [[_ part-data] node
          {left :left, right :right} part-data]
      (if (has-tag? right :seq-end)
        seq
        [:seq
         [:part
          {:left node
           :right [:seq-end]}]]))
    [:seq
     [:part
      {:left node
       :right [:seq-end]}]]))


(defmethod expand-pat :vec [[_ node :as vec]]
  (if (has-tag? node :part)
    (let [[_ part-data] node
          {left :left, right :right} part-data]
      (if (has-tag? right :seq-end)
        vec
        [:vec
         [:part
          {:left node
           :right [:seq-end]}]]))
    [:vec
     [:part
      {:left node
       :right [:seq-end]}]]))


(defn parse-term [term]
  (clojure.walk/prewalk expand-pat (parse-term* term)))
