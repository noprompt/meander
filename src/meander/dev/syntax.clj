(ns meander.dev.syntax
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as s.gen]
            [clojure.walk :as walk]))


(defn quote-form?
  [x]
  (and (seq? x)
       (= (first x) 'quote)))


(defn unquote-form?
  [x]
  (and (seq? x)
       (= (first x) 'clojure.core/unquote)))


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
  (s/with-gen
    (s/conformer
     (fn [x]
       (if (or (not (partition-symbol? x))
               (quote-form? x))
         x
         ::s/invalid))
     identity)
    (s.gen/fmap
     (fn [x]
       `(quote ~x))
     (s.gen/any))))


(s/def :meander.syntax/any
  (s/with-gen
    (s/conformer
     (fn [x]
       (if (wildcard-symbol? x)
         x
         ::s/invalid))
     identity)
    (constantly '_)))


(s/def :meander.syntax/var
  (s/with-gen 
    (s/conformer
     (fn [x]
       (if (var-symbol? x)
         x
         ::s/invalid))
     identity)
    (fn []
      (s.gen/fmap
       (fn [sym]
         (symbol (str \? sym)))
       (s.gen/symbol)))))


(s/def :meander.syntax/mem
  (s/with-gen
    (s/conformer
     (fn [x]
       (if (mem-symbol? x)
         x
         ::s/invalid))
     identity)
    (fn []
      (s.gen/fmap
       (fn [sym]
         (symbol (str \! sym)))
       (s.gen/symbol)))))


(s/def :meander.syntax/ref
  (s/conformer
   (fn [x]
     (if (ref-symbol? x) 
       x
       ::s/invalid))
   identity))


(s/def :meander.syntax/quote
  quote-form?)


(s/def :meander.syntax/unquote
  unquote-form?)


(s/def :meander.syntax/term
  (s/or :var :meander.syntax/var
        :mem :meander.syntax/mem
        :ref :meander.syntax/ref
        :any :meander.syntax/any
        :vec :meander.syntax/vec
        :map :meander.syntax/map
        :unq :meander.syntax/unquote
        :quo :meander.syntax/quote
        :cap :meander.syntax/cap
        :and :meander.syntax/and
        :seq :meander.syntax/seq
        :lit :meander.syntax/lit))


(s/def :meander.syntax/top-level
  (s/or :var :meander.syntax/var
        :mem :meander.syntax/mem
        :ref :meander.syntax/ref
        :any :meander.syntax/any
        :vec :meander.syntax/vec
        :map :meander.syntax/map
        :unq :meander.syntax/unquote
        :quo :meander.syntax/quote
        :and :meander.syntax/and
        ;; Should this be top-cap?
        :cap (s/cat
              :pat :meander.syntax/top-level
              :as #{:as}
              :var (s/or :mem :meander.syntax/mem
                         :var :meander.syntax/var))
        :err/bad-top-level-cap
        (s/cat
         :pat :meander.syntax/elem
         :as #{:as}
         :var (s/or :mem :meander.syntax/mem
                    :var :meander.syntax/var))
        :seq :meander.syntax/seq
        :lit :meander.syntax/lit))


(s/def :meander.syntax/and
  (s/and seq?
         (s/cat
          :and '#{and}
          :pats (s/* :meander.syntax/term))))

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


(s/def :meander.syntax/map
  (s/conformer
   (fn [x]
     (if (map? x)
       (reduce-kv
        (fn [v xk xv]
          (let [kc (s/conform :meander.syntax/top-level xk)
                vc (s/conform :meander.syntax/top-level xv)]
            (if (or (identical? ::s/invalid kc)
                    (identical? ::s/invalid vc))
              ::s/invalid
              (assoc v kc vc))))
        {}
        x)
       ::s/invalid))))


(s/def :meander.syntax/seq
  (s/and seq? :meander.syntax/elem))


;; ---------------------------------------------------------------------
;; meander.syntax.parse


(defn parse* [term]
  (s/conform :meander.syntax/top-level term))


(defn node? [x]
  (and (vector? x)
       (keyword? (first x))))

(defn has-tag?
  ([node tag]
   (and (node? node)
        (= (first node) tag))))

(defn tag
  [node]
  {:pre [(node? node)]}
  (first node))

(defn data
  [node]
  {:pre [(node? node)]}
  (second node))

(defn nth-node
  [index node]
  {:pre [(node? node)]}
  (if (has-tag? node :nth)
    [:nth (assoc (data node) :index index)]
    [:nth {:index index, :pat node}]))

(defn cat-cats [[_ xs] [_ ys]]
  [:cat (into (vec xs) ys)])

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
  (let [[xs ys] (split-with
                 (fn [[tag data]]
                   (if (= tag :cap)
                     (let [{[cap-pat-tag cap-pat-data] :pat} data]
                       (and (not= cap-pat-tag :rep)
                            (not= cap-pat-tag :repk)
                            (and (= cap-pat-tag :cat)
                                 (<= (count cap-pat-data) 1))))
                     true))
                 pats
                 #_
                 (map-indexed
                  (fn [index pat]
                    [:nth {:index index, :pat pat}])
                  pats))
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
  (let [pat (:pat cap-data)
        var (:var cap-data)]
    (if (or (has-tag? pat :any)
            (and (has-tag? pat :var)
                 (= var pat)))
      var
      cap)))


(defmethod expand-pat :part [[_ part-data :as part]]
  (let [{:keys [left right]} part-data
        [left-tag left-data] left
        [right-tag right-data] right]
    (case [left-tag right-tag]
      [:cat :cat]
      [:part
       {:left (cat-cats left right)
        :right [:seq-end]}]

      [:cat :part]
      (let [{right-left :left, right-right :right} (second right)]
        (if (has-tag? right-left :cat)
          (recur [:part (assoc part-data
                               :left (cat-cats left right-left)
                               :right right-right)])))

      ;; else
      part)))


(defn expand-rep-init [rep-init]
  (walk/prewalk
   expand-pat
   (cond (has-tag? rep-init :cat)
         rep-init

         (node? rep-init)
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


(defmulti collapse-pat
  (fn [x]
    (if-some [tag (when (vector? x)
                    (first x))]
      tag
      ::no-tag))
  :default ::no-tag)


(defmethod collapse-pat ::no-tag [x]
  x)


(defmethod collapse-pat :part [[_ {:keys [left right]} :as part]]
  (let [[left-tag left-data] left
        [right-tag right-data] right]
    (case [left-tag right-tag]
      [:part :seq-end]
      (collapse-pat left)

      [:cat :rep]
      (let [{init :init} right-data
            [init-tag init-data] init]
        (if (= init-tag :cat)
          (let [[[tag data] & rest] init-data]
            (cond
              (and (not (seq rest))
                   (= tag :any))
              [:part
               {:left left
                :right [:drop]}]

              (and (not (seq rest))
                   (= tag :mem))
              [:part
               {:left left
                :right [:rest {:var [tag data]}]}]

              :else
              part))
          part))

      ([:rep :cat]
       [:rep :seq-end])
      (let [{init :init} left-data
            [init-tag init-data] init]
        (if (= init-tag :cat)
          (let [[[tag data] & rest] init-data]
            (cond
              (and (not (seq rest))
                   (= tag :any)
                   (= right-tag :seq-end))
              [:part
               ;; I don't really like the name drop.
               {:left [:drop]
                :right right}]

              (and (not (seq rest))
                   (= tag :mem))
              [:part
               {:left (if (= right-tag :seq-end)
                        [:rest {:var [tag data]}]
                        [:init {:var [tag data]}])
                :right right}]

              :else
              part))
          part))

      #_[:cat :seq-end]
      #_left

      ;; else
      part)))

(defn seq->cons [node]
  (or (if (has-tag? node :seq)
        (let [child (data node)]
          (if (has-tag? child :part)
            (let [{:keys [left right]} (data child)]
              (if (has-tag? left :cat)
                (let [elems (data left)]
                  (if (seq (rest elems))
                    (reduce
                     (fn [right* elem]
                       [:cons
                        {:car elem
                         :cdr right*}])
                     [:cons
                      {:car (first (reverse elems))
                       :cdr right}]
                     (rest (reverse elems))))))))))
      node)) 

(defn cat->nths [node]
  (if (has-tag? node :cat) 
    [:cat (map-indexed nth-node (data node))]
    node))


(defn cap-cat? [node]
  (and (has-tag? node :cap)
       (has-tag? (:pat (data node))
                 :cat)))

(defn rewrite-cap-cat*
  [data]
  (if (seq data)
    (let [[init tail] (split-with (complement cap-cat?) data)]
      (if (seq init)
        (if (seq tail)
          [:part
           {:left [:cat init]
            :right (rewrite-cap-cat* tail)}])
        (if (seq tail)
          [:part
           {:left (first tail)
            :right (rewrite-cap-cat* (next tail))}])))
    [:seq-end]))

;; Part of the :cat node rewrite process involves rewriting nodes
;; which look like [:cat [?node]] to ?node. This is not okay for :rep
;; nodes because the :pat attribute is expected to be a :cat. This
;; function resolves this problem.
(defn rewrite-cap-cat [node]
  (if (has-tag? node :rep)
    (let [init (:init (data node))]
      (cond
        (and (has-tag? init :cat)
             (some cap-cat? (data init)))
        [:rep
         (assoc (data node) :init (rewrite-cap-cat* (data init)))]

        (has-tag? init :cap)
        (let [cap-data (data init)
              pat (:pat cap-data)]
          (if (has-tag? pat :cat)
            (if (= 1 (count (data pat)))
              [:rep
               {:init [:cat
                       [[:cap
                         (assoc cap-data :pat (first (data pat)))]]]}]
              node)
            [:rep
             (assoc (data node)
                    :init [:cap (assoc cap-data
                                       :pat [:cat [pat]])])]))

        :else
        node))
    node))


(defn parse
  [form]
  (->> (parse* form)
       (walk/prewalk expand-pat)
       (walk/postwalk collapse-pat)
       (walk/postwalk rewrite-cap-cat)))
