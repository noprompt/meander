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


(defn app-symbol?
  [x]
  (= x '>>))


(defn partition-symbol?
  [x]
  (= x '.))


(defn zero-or-more-symbol?
  [x]
  (= x '...))


(defn k-or-more-symbol?
  [x]
  (and (simple-symbol? x)
       (some? (re-matches #"\.\.\d+" (name x)))))


(defn wildcard-symbol?
  [x]
  (= x '_))


(defn pred-symbol?
  [x]
  (= x '?))


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
        :prd :meander.syntax/pred
        :cap :meander.syntax/cap
        :app :meander.syntax/app
        :and :meander.syntax/and
        :not :meander.syntax/not
        :or :meander.syntax/or
        :seq :meander.syntax/seq
        :lit :meander.syntax/lit))

(s/def :meander.syntax/top-level
  (s/or
   :var :meander.syntax/var
   :mem :meander.syntax/mem
   :ref :meander.syntax/ref
   :any :meander.syntax/any
   :vec :meander.syntax/vec
   :map :meander.syntax/map
   :unq :meander.syntax/unquote
   :quo :meander.syntax/quote
   :prd :meander.syntax/pred
   :app :meander.syntax/app
   :not :meander.syntax/not
   :and :meander.syntax/and
   :or :meander.syntax/or
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

(s/def :meander.syntax/not
  (s/and seq?
         (s/cat
          :not '#{not}
          :pats (s/+ :meander.syntax/term))))


(s/def :meander.syntax/and
  (s/and seq?
         (s/cat
          :and '#{and}
          :pats (s/* :meander.syntax/term))))


(s/def :meander.syntax/or
  (s/and seq?
         (s/cat
          :and '#{or}
          :pats (s/* :meander.syntax/term))))


(s/def :meander.syntax/app
  (s/and seq?
         (s/cat
          :app-sym '#{>>}
          :expr any?
          :pats (s/* :meander.syntax/term))))


(s/def :meander.syntax/pred
  (s/and seq?
         (s/cat
          :qmark '#{?}
          :pred any?
          :pats (s/* :meander.syntax/term))))


(s/def :meander.syntax/cap
  (s/cat
   :pat :meander.syntax/elem
   :as #{:as}
   :var (s/or :mem :meander.syntax/mem
              :var :meander.syntax/var)))


(s/def :meander.syntax/cat
  (s/* :meander.syntax/term))


(s/def :meander.syntax/rest
  (s/cat
   :mem :meander.syntax/mem
   :sym :meander.syntax/zero-or-more-symbol))


(s/def :meander.syntax/drop
  (s/cat
   :init :meander.syntax/any
   :sym :meander.syntax/zero-or-more-symbol))


(s/def :meander.syntax/zero-or-more
  (s/cat
   :init (s/+ :meander.syntax/term)
   :sym :meander.syntax/zero-or-more-symbol))


(s/def :meander.syntax/k-or-more
  (s/cat
   :init (s/+ :meander.syntax/term)
   :sym :meander.syntax/k-or-more-symbol))


(s/def :meander.syntax/partition
  (s/cat
   :left (s/alt
          :init :meander.syntax/rest
          :drop :meander.syntax/drop
          :rep :meander.syntax/zero-or-more
          :repk :meander.syntax/k-or-more
          :cat :meander.syntax/cat)
   :dot '#{.}
   :right (s/? :meander.syntax/elem)))

(s/def :meander.syntax/elem
  (s/alt
   :rest :meander.syntax/rest
   :drop :meander.syntax/drop
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


(defn parse*
  [term]
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


(s/def :meander.syntax.ast.part/left
  node?)


(s/def :meander.syntax.ast.part/right
  node?)


(s/def :meander.syntax.ast/part
  (s/tuple #{:part} (s/keys :req-un [:meander.syntax.ast.part/left
                                     :meander.syntax.ast.part/right])))

(defn part-node? [x]
  (s/valid? :meander.syntax.ast/part x))

(defn data
  [node]
  {:pre [(node? node)]}
  (second node))


(defn update-data
  [node f & args]
  {:pre [node? node]}
  (apply update node 1 f args))


(defn left-node
  [part-node]
  {:pre [(part-node? part-node)]}
  (:left (data part-node)))


(defn left-tag
  [part-node]
  {:pre [(part-node? part-node)]}
  (tag (left-node part-node)))


(defn right-node
  [part-node]
  {:pre [(part-node? part-node)]}
  (:right (data part-node)))


(defn right-tag
  [part-node]
  {:pre [(part-node? part-node)]}
  (tag (right-node part-node)))


(defmulti min-length
  #'tag)


(defn has-min-length?
  [node]
  (some? (get-method min-length (tag node))))


(defmethod min-length :cap [node]
  (min-length (:pat (data node))))


(defn cat-length [cat-node]
  {:pre [(has-tag? cat-node :cat)]}
  (count (data cat-node)))


(defmethod min-length :cat [node]
  (cat-length node))


(defmethod min-length :drop [node]
  0)


(defmethod min-length :init [node]
  0)


(defmethod min-length :map [node]
  1)


(defmethod min-length :part [node]
  (let [{:keys [left right]} (data node)]
    (+ (min-length left)
       (min-length right))))


(defmethod min-length :rep [node]
  0)


(defmethod min-length :rest [node]
  0)


(defmethod min-length :seq [node]
  (min-length (data node)))


(defmethod min-length :seq-end [node]
  0)


(defmethod min-length :vcat [node]
  (count (data node)))


(defmethod min-length :vec [node]
  (min-length (data node)))



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
                               :right right-right)])
          part))

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

      ;; else
      part)))


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


(defn part-to-vpart
  [node] 
  (or (when (has-tag? node :vec)
        (let [x (data node)]
          (when (has-tag? x :part)
            (let [part-data (data x)
                  left (:left part-data)]
              [:vec
               (if (has-tag? left :cat)
                 [:vpart (assoc part-data :left [:vcat (data left)])]
                 [:vpart (data x)])])))) 
      node))

(defn part-ensure-seq-end
  [node]
  (or 
   (when (has-tag? node :part)
     (let [part-data (data node)
           right (:right part-data)]
       (when (not (has-tag? right :seq-end))
         [:part
          (assoc part-data
                 :right [:part {:left right 
                                :right [:seq-end]}])])))
   node))

(defn part-expand-left-part [node]
  (if (part-node? node)
    (let [{:keys [left right]} (data node)]
      (if (part-node? left)
        [:part
         {:left (left-node left)
          :right (if (= (right-tag left) :seq-end)
                   right
                   [:part {:left (right-node left)
                           :right right}])}]
        node))
    node))


(defn parse
  [form]
  (->> (parse* form)
       (walk/prewalk expand-pat)
       (walk/postwalk collapse-pat)
       (walk/postwalk rewrite-cap-cat)
       (walk/postwalk part-to-vpart)
       (walk/postwalk part-ensure-seq-end)
       (walk/prewalk part-expand-left-part)))

(defmethod min-length :vpart
  [node]
  (let [part-data (data node)]
    (+ (min-length (:left part-data))
       (min-length (:right part-data)))))

(defmethod min-length :vcat
  [node]
  (count (data node)))


(defmethod min-length :map [_]
  1)

(defmulti variable-length?
  #'tag)

(defmethod variable-length? :cat [_]
  false)

(defmethod variable-length? :vcat [_]
  false)

(defmethod variable-length? :part [node]
  (boolean (some variable-length? ((juxt :left :right) (data node)))))

(defmethod variable-length? :seq-end [_]
  false)

(defmethod variable-length? :drop [_]
  true)

(defmethod variable-length? :rest [_]
  true)

(defmethod variable-length? :init [_]
  true)

(defmethod variable-length? :rep [_]
  true)

(defmethod variable-length? :repk [_]
  true)

(defmethod variable-length? :seq [node]
  (variable-length? (data node)))

(defmethod variable-length? :vec [node]
  (variable-length? (data node)))


;; ---------------------------------------------------------------------
;; Unparse


(defmulti unparse
  {:arglists (:arglists (meta #'tag))}
  #'tag)


(defmethod unparse :vec [[_ elem]]
  (into [] (unparse elem)))


(defmethod unparse :part [[_ {:keys [left right]}]]
  (concat (unparse left)
          (when-some [xs (seq (unparse right))]
            (cons '. xs))))


(defmethod unparse :cat [[_ pats]]
  (map unparse pats))


(defmethod unparse :lit [[_ lit]]
  lit)


(defmethod unparse :seq-end [_]
  ())

(defmethod unparse :var [[_ sym]]
  sym)


(defmethod unparse :mem [[_ sym]]
  sym)


(defmethod unparse :seq [[_ elem]]
  (unparse elem))


(defmethod unparse :rep [[_ {:keys [init]}]]
  (concat (unparse init) (list '...)))


(defmethod unparse :quo [[_ x]]
  x)


(defmethod unparse :prd [[_ {:keys [pred pats]}]]
  (list* '? pred (sequence (map unparse) pats)))


(defmethod unparse :and [[_ {:keys [pats]}]]
  (cons 'and (sequence (map unparse) pats)))


(defmethod unparse :app [[_ {:keys [expr pats]}]]
  (cons '>> (cons expr (sequence (map unparse) pats))))


(defmethod unparse :drop [_]
  '(_ ...))


(defmethod unparse :rest [[_ {:keys [sym]}]]
  (list sym '...))


(defmethod unparse :vpart [[_ {:keys [left right]}]]
  (concat (unparse left)
          (when-some [xs (seq (unparse right))]
            (cons '. xs))))


(defmethod unparse :vcat [[_ pats]]
  (mapv unparse pats))


(defmethod unparse :map [[_ entries]]
  (reduce
   (fn [m [k v]]
     (assoc m (unparse k) (unparse v)))
   {}
   entries))


(defmethod unparse :not [[_ {:keys [pats]}]]
  (cons 'not (sequence (map unparse) pats)))


(defn variables
  "Return all variable nodes in x."
  [x]
  (into #{}
        (keep
         (fn [x]
           (cond
             (map-entry? x)
             nil
             
             (and (or (has-tag? x :var)
                      (has-tag? x :mem))
                  (simple-symbol? (data x)))
             x

             (or (has-tag? x :rest)
                 (has-tag? x :init))
             (find (data x) :mem))))
        (tree-seq seqable? seq x)))
