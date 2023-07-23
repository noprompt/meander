(ns ^:no-doc meander.util.epsilon
  (:require [clojure.walk :as walk]
            [clojure.zip :as zip])
  #?(:cljs
     (:require-macros [meander.util.epsilon
                       :refer [__disj
                               __dissoc
                               __nth]])))

#?(:clj (set! *warn-on-reflection* true))

(defn cljs-env?
  "true if compiling ClojureScript or in a ClojureScript setting,
  false otherwise."
  [env]
  #?(:clj (some? (:ns env))
     :cljs true))

#?(:clj
   (defn cljs-ns-from-clj-ns
     "Given a `clojure.lang.Namespace` clj-ns, return a map which
  partially emulates the :ns key of a ClojureScript &env
  environment. The map has the structure

    {:name symbol?
     :requires {symbol? qualified-symbol?}
     :uses {symbol? qualified-symbol?}}

  This is used to provide a canonical format for symbol
  resolution and reasoning in cases where using `*ns*` is
  inappropriate or does not work."
     [clj-ns]
     (let [requires (into {}
                          (map (fn [[k v]] [k (ns-name v)]))
                          (ns-aliases *ns*))
           required-ns-names (set (vals requires))
           uses (into {}
                      (keep (fn [[sym x]]
                              (if (var? x)
                                (let [var-ns-name (ns-name (:ns (meta x)))]
                                  (if (contains? required-ns-names var-ns-name)
                                    [sym var-ns-name])))))
                      (ns-refers *ns*))]
       ;; Incomplete.
       {:name (ns-name *ns*)
        :requires requires
        :uses uses})))

#?(:clj
   (defmacro canonical-ns
     "Return the current compile time canonical namespace data."
     []
     (if (cljs-env? &env)
       `(quote ~{:ns (select-keys (get &env :ns) [:name :requires :uses])})
       `(quote ~(cljs-ns-from-clj-ns *ns*)))))

(defn parse-int
  "Parse the string s as an integer."
  [s]
  #?(:clj (Integer/parseInt s)
     :cljs (js/parseInt s)))

(defmacro __disj
  {:private true}
  [s x]
  (if (cljs-env? &env)
    `(disj ~s ~x)
    `(.disjoin ~(vary-meta s assoc :tag 'clojure.lang.IPersistentSet) ~x)))

(defmacro __dissoc
  {:private true}
  ([m x]
   (if (cljs-env? &env)
     `(dissoc ~m ~x)
     `(.without  ~(vary-meta m assoc :tag 'clojure.lang.IPersistentMap) ~x)))
  ([m x & xs]
   `(__dissoc (__dissoc ~m ~x) ~@xs)))

(defmacro __nth
  {:private true}
  [v i]
  (if (cljs-env? &env)
    `(nth ~v ~i)
    `(.nth ~(vary-meta v assoc :tag 'clojure.lang.IPersistentVector) ~i)))

(defn swap
  "Swap the elements at positions `i` and `j` in `v`."
  {:private true}
  [v i j]
  (-> v
      (assoc i (nth v j))
      (assoc j (nth v i))))

;; SEE: https://en.wikipedia.org/wiki/Heap%27s_algorithm
(defn permutations
  "Return a sequence of all the ways to arrange coll."
  [coll]
  (let [v (vec coll)
        n (count v)]
    (loop [n n
           a [v]]
      (if (zero? n)
        a
        (let [n* (dec n)
              a* (mapcat
                  (fn step [v]
                    (map
                     (fn [i]
                       (swap v i n*))
                     (range n)))
                  a)]
          (recur n* a*))))))

(defn set-k-permutations-with-unselected
  "Set specific algorithm for returning a lazy sequence of pairs

     [[,,,] #{,,,}]

  where the first element in the pair is a permuted selection of k
  items from the set s, and the second element is s with those
  elements removed."
  [s ^long k]
  (case k
    0 (list [[] s])

    1 (map (fn [x]
             [[x] (__disj s x)])
           s)

    2 (mapcat
       (fn [x]
         (let [s-x (__disj s x)]
           (map (fn [y]
                  [[x y] (__disj s-x y)])
                s-x)))
       s)

    ;; else
    (mapcat
     (fn [pair]
       (let [xs (__nth pair 0)
             s-xs (__nth pair 1)]
         (map (fn [x]
                [(conj xs x) (__disj s-xs x)])
              s-xs)))
     (set-k-permutations-with-unselected s (dec k)))))

(defn map-k-permutations-with-unselected
  "Map specific algorithm for returning a lazy sequence of pairs

     [[,,,] {,,,}]

  where the first element in the pair is a permuted selection of k
  entries from the map m, and the second element is m with those
  entries removed."
  [m ^long k]
  (case k
    0 (list [[] m])

    1 (map (fn [e]
             [[e] (__dissoc m (key e))])
           m)

    2 (mapcat
       (fn [e1]
         (let [m-e1 (__dissoc m (key e1))]
           (map (fn [e2]
                  [[e1 e2] (__dissoc m-e1 (key e2))])
                m-e1)))
       m)

    ;; else
    (mapcat
     (fn [pair]
       (let [es (__nth pair 0)
             m-es (__nth pair 1)]
         (map (fn [e]
                [(conj es e) (__dissoc m-es (key e))])
              m-es)))
     (map-k-permutations-with-unselected m (dec k)))))

(defn vector-k-permutations-with-unselected
  "Vector specific algorithm for returning a lazy sequence of pairs

     [[,,,] [,,,]]

  where the first element in the pair is a permuted selection of k
  items from the vector v, and the second element is v with the items
  at their respective indicies removed."
  [v ^long k]
  (case k
    0 (list [[] v])

    1 (map-indexed
       (fn [i x]
         (let [j (inc i)]
           [[x] (into (subvec v 0 i) (subvec v j))]))
       v)

    ;; else
    (mapcat
     (fn [pair]
       (let [xs (__nth pair 0)
             v-xs (__nth pair 1)]
         (map-indexed
          (fn [i x]
            (let [j (inc i)]
              [(conj xs x) (into (subvec v-xs 0 i) (subvec v-xs j))]))
          v-xs)))
     (vector-k-permutations-with-unselected v (dec k)))))

(defn seq-k-permutations-with-unselected
  "Seq specific algorithm for returning a lazy sequence of pairs

     [[,,,] (,,,)]

  where the first element in the pair is a permuted selection of k
  items from the seq s, and the second element is s with the items
  at their respective indicies removed."
  [s ^long k]
  (case k
    0 (list [[] s])

    1 (map-indexed
       (fn [i x]
         (let [j (inc i)]
           [[x] (concat (take i s) (drop j s))]))
       s)

    ;; else
    (mapcat
     (fn [pair]
       (let [xs (__nth pair 0)
             s-xs (__nth pair 1)]
         (map-indexed
          (fn [i x]
            (let [j (inc i)]
              [(conj xs x) (concat (take i s-xs) (drop j s-xs))]))
          s-xs)))
     (seq-k-permutations-with-unselected s (dec k)))))

(defn k-permutations
  "All the ways to choose k permuted items from coll."
  [coll k]
  (map (fn [pair]
         (__nth pair 0))
       (cond
         (map? coll)
         (map-k-permutations-with-unselected coll k)

         (set? coll)
         (set-k-permutations-with-unselected coll k)

         (vector? coll)
         (vector-k-permutations-with-unselected coll k)

         :else
         (seq-k-permutations-with-unselected coll k))))

(defn k-combinations
  "All the ways to choose k items from coll."
  [coll k]
  (sequence
   (comp (map set)
         (distinct))
   (k-permutations coll k)))

(defn vsplit-at
  "Like `clojure.core/split-at` but for vectors."
  ([n v]
   {:pre [(vector? v)]}
   (let [i (min n (count v))]
     [(subvec v 0 i) (subvec v i)])))


(defn vec-partitions
  "
  (let [coll [:a :b]
        n 3]
  (vec-partitions n coll))
  ;; => ([[] [] [:a :b]]
  ;;     [[] [:a] [:b]]
  ;;     [[:a] [] [:b]]
  ;;     [[] [:a :b] []]
  ;;     [[:a] [:b] []]
  ;;     [[:a :b] [] []])
  "
  {:private true}
  ([^long n v]
   {:pre [(nat-int? n)]}
   (case n
     0 (list [])
     1 (list [v])
     2 (sequence
        (map
         (fn [i]
           [(subvec v 0 i) (subvec v i)]))
        (range (inc (count v))))
     ;; else
     (sequence
      (comp (map-indexed
             (fn [i _]
               [(subvec v 0 i) (subvec v i)]))
            (mapcat
             (fn [[a b]]
               (sequence
                (map conj)
                (vec-partitions (dec n) a)
                (repeat b)))))
      (range (inc (count v))))))
  ([^long n m v]
   {:pre [(nat-int? n) (nat-int? m)]}
   (if (<= m (count v))
     (case n
       0 (list [])
       1 (list [v])
       2 (sequence
          (comp (take-while
                 (let [j (count v)]
                   (fn [i]
                     (<= (+ i m) j))))
                (map
                 (fn [i]
                   [(subvec v 0 (+ i m)) (subvec v (+ i m))])))
          (range (inc (count v))))
       ;; else
       (sequence
        (comp (take-while
               (let [j (count v)]
                 (fn [i]
                   (<= (+ i m) j))))
              (map
               (fn [i]
                 [(subvec v 0 (+ i m)) (subvec v (+ i m))]))
              (mapcat
               (fn [[a b]]
                 (sequence
                  (map conj)
                  (vec-partitions (dec n) m a)
                  (repeat b)))))
        (range (inc (count v)))))
     (list []))))

(defn coll-partitions
  {:private true}
  ([^long n coll]
   {:pre [(nat-int? n)]}
   (case n
     0 (list [])
     1 (list [coll])
     2 (sequence
        (map-indexed
         (fn [i _]
           (split-at i coll)))
        (cons 1 coll))
     ;; else
     (sequence
      (comp
       (map-indexed
        (fn [i _]
          (split-at i coll)))
       (mapcat
        (fn [[a b]]
          (sequence
           (map conj)
           (coll-partitions (dec n) a)
           (repeat b)))))
      ;; Adding one more element to the coll ensures we split at 0
      ;; *and* at (count coll) without counting the collection.
      (cons (first coll) coll))))
  ([^long n m coll]
   {:pre [(nat-int? n) (nat-int? m)]}
   (if (<= m (bounded-count m coll))
     (case n
       0 (list [])
       1 (list [coll])
       2 (sequence
          (comp (map-indexed
                 (fn [i _]
                   (split-at (+ i m) coll)))
                (distinct))
          (cons 1 coll))
       ;; else
       (sequence
        (comp (map-indexed
               (fn [i _]
                 (split-at (+ i m) coll)))
              (distinct)
              (mapcat
               (fn [[a b]]
                 (sequence
                  (map conj)
                  (coll-partitions (dec n) m a)
                  (repeat b)))))
        ;; Adding one more element to the coll ensures we split at 0
        ;; *and* at (count coll) without counting the collection.
        (cons (first coll) coll)))
     (list []))))


(defn str-partitions
  "
  Examples:

  (let [str \"ab\"
      n 0]
  (str-partitions n str))
  ;; => ([])

  (let [str \"ab\"
      n 1]
  (partitions n coll))
  ;; => ([\"ab\"])

  (let [str \"ab\"
      n 2]
  (partitions n coll))
  ;; => ([[] [\"ab\"]
  ;;     [[\"a\"] [\"b\"]]
  ;;     [[\"ab\"] []])

  (let [str \"ab\"
      n 3]
  (partitions n coll))
  ;; => ([[] [] [\"ab\"]]
  ;;     [[] [\"a\"] [\"b\"]]
  ;;     [[\"a\"] [] [\"b\"]]
  ;;     [[] [\"ab\"] []]
  ;;     [[\"a\"] [\"b\"] []]
  ;;     [[\"ab\"] [] []])
  "
  [^long n #?(:clj ^String str
              :cljs str)]
  {:pre [(nat-int? n)]}
  (case n
    0 (list [])
    1 (list [str])
    2 (sequence
       (map
        (fn [i]
          [(subs str 0 i) (subs str i)]))
       (range (inc (.length str))))
    ;; else
    (sequence
     (comp
      (map
       (fn [i]
         [(subs str 0 i) (subs str i)]))
      (mapcat
       (fn [[a b]]
         (sequence
          (map conj)
          (str-partitions (dec n) a)
          (repeat b)))))
     (range (inc (.length str))))))

(defn increment-code
  {:private true}
  [base code]
  (reduce
   (fn [v i]
     (let [b (nth v i)]
       (if (zero? b)
         (reduced (assoc v i 1))
         (let [b (+ b 1)]
           (if (zero? (mod b base))
             (assoc v i 0)
             (assoc v i b))))))
   code
   (range (count code))))

(defn code-seq
  {:arglists '([number-of-positions number-of-partitions])
   :private true}
  [n m]
  (let [number-of-answers (Math/pow m n)]
    (if (< number-of-answers ##Inf)
      (take number-of-answers
            (iterate (partial increment-code m) (vec (repeat n 0))))
      (let [last-answer (vec (repeat n (dec m)))]
        (take-while (fn [answer]
                      (not (= answer last-answer)))
                    (iterate (partial increment-code m) (vec (repeat n 0))))))))


(defn map-partitions
  {:arglists '([map number-of-partitions])}
  [m n]
  (let [elements (vec m)
        number-of-elements (count m)
        empty-partitions (vec (repeat n {}))]
    (map (fn [code]
           (reduce (fn [partitions i]
                     (update partitions (nth code i) conj (nth elements i)))
                   empty-partitions
                   (range (count code))))
         (code-seq number-of-elements n))))

(doall (map-partitions {:a 1 :b 2 :c 3} 3))


(defn partitions "
  Examples:

  (def coll [:a :b])

  (partitions 0 coll))
  ;; => ([])

  (partitions 1 coll)
  ;; => ([[:a :b]])

  (partitions 2 coll)
  ;; => '([[] [:a :b]]
  ;;      [[:a] [:b]]
  ;;      [[:a :b] []])

  (partitions 3 coll)
  ;; => '([[] [] [:a :b]]
  ;;      [[] [:a] [:b]]
  ;;      [[:a] [] [:b]]
  ;;      [[] [:a :b] []]
  ;;      [[:a] [:b] []]
  ;;      [[:a :b] [] []])
  "
  ([n coll]
   (cond
     (vector? coll)
     (vec-partitions n coll)

     (coll? coll)
     (coll-partitions n coll)

     (string? coll)
     (str-partitions n coll)

     (nil? coll)
     ()

     :else
     (throw (ex-info "coll must be a string? or coll?" {:type (type coll)}))))
  ([n m coll]
   (cond
     (vector? coll)
     (vec-partitions n m coll)

     (coll? coll)
     (coll-partitions n m coll)

     (string? coll)
     (str-partitions n coll)

     (nil? coll)
     ()

     :else
     (throw (ex-info "coll must be a string? or coll?" {:type (type coll)})))))

(defn coll-zip
  [root]
  (zip/zipper coll?
              seq
              (fn [coll coll-new]
                (cond
                  (seq? coll)
                  coll-new

                  (record? coll)
                  (into coll coll-new)

                  (map? coll)
                  (into {} coll-new)

                  (map-entry? coll)
                  (vec coll-new)

                  :else
                  (into (empty coll) coll-new)))
              root))

(defn coll-seq
  "Return a lazy sequence of all the nodes in the tree `root`
  excluding `map-entry?`s."
  [root]
  (remove map-entry? (tree-seq coll? seq root)))

(defn zip-next-seq
  "Given a clojure.zip zipper location loc return a lazy sequence of
  all clojure.zip/next locations from loc. Note that `map-entry?`s
  are skipped."
  [loc]
  (cond
    (zip/end? loc)
    ()

    (map-entry? (zip/node loc))
    (recur (zip/next loc))

    :else
    (lazy-seq (cons loc (zip-next-seq (zip/next loc))))))

(defn rank
  "Returns a sorted sequence of values in xs by frequency of
  occurence."
  [xs]
  (map first (sort-by (comp - val) (frequencies xs))))

(defn re-matches?
  "Returns true if s matches the regex pattern re, false otherwise."
  [re s]
  #?(:clj
     (boolean (re-matches re s))
     :cljs
     (.test re s)))

(defn case-test-form
  "Converts all `form` into a form suitable for use as a `case`
  test. Assumes `form` is already a literal value."
  [form]
  (walk/prewalk
   (fn [y]
     (if (seq? y)
       (vec y)
       y))
   form))


(defn quoted?
  "`true` if `x` is a quoted form, e.g. `(quote _)`, and `false`
  otherwise."
  [x]
  (and (seq? x) (= (first x) 'quote)))


(defn mix*
  "Helper function for mix."
  {:private true}
  [colls]
  (if (seq colls)
    (if (seq (first colls))
      (lazy-seq (cons (ffirst colls)
                      (mix* (conj (subvec colls 1)
                                  (rest (first colls))))))
      (recur (subvec colls 1)))
    ()))

(defn mix
  "Like clojure.core/interleave but exhausts each supplied
  collection.
    (interleave [1 2 3] [\"a\" \"b\"] [:w :x :y :z])
    ;; =>
    (1 \"a\" :w 2 \"b\" :x)
    (mix [1 2 3] [\"a\" \"b\"] [:w :x :y :z])
    ;; =>
    (1 \"a\" :w 2 \"b\" :x 3 :y :z)"
  {:arglists '([coll1 coll2 & more-colls])}
  ([coll1 coll2]
   (if (seq coll1)
     (lazy-seq (cons (first coll1) (mix coll2 (rest coll1))))
     coll2))
  ([coll1 coll2 coll3]
   (if (seq coll1)
     (lazy-seq (cons (first coll1) (mix coll2 coll3 (rest coll1))))
     (mix coll2 coll3)))
  ([coll1 coll2 coll3 coll4]
   (if (seq coll1)
     (lazy-seq (cons (first coll1) (mix coll2 coll3 coll4 (rest coll1))))
     (mix coll2 coll3 coll4)))
  ([coll1 coll2 coll3 coll4 & more-colls]
   (mix* (into [coll1 coll2 coll3 coll4] more-colls))))

(defn knit
  [colls]
  (case (long (bounded-count 2 colls))
    0 ()

    1 (if (seq (first colls))
        (first colls)
        ())

    ;; else
    (concat
     (sequence (comp (keep seq)
                     (map first))
               colls)
     (lazy-seq (knit (keep next colls))))))

(defn expand-symbol
  [sym env]
  #?(:clj (if-some [cljs-ns (:ns env)]
            ;; ClojureScript compile-time
            (if (qualified-symbol? sym)
              (let [ns-sym (symbol (namespace sym))]
                (if-some [ns (get (:requires cljs-ns) ns-sym)]
                  (symbol (name ns) (name sym))
                  sym))
              (let [ns-uses (get cljs-ns :uses)]
                (if-some [ns-symbol (get ns-uses sym)]
                  (symbol (name ns-symbol) (name sym))
                  (symbol (name (:name cljs-ns)) (name sym)))))
            ;; Clojure
            (if (qualified-symbol? sym)
              (let [ns-sym (symbol (namespace sym))]
                (if-some [ns (get (ns-aliases *ns*) ns-sym)]
                  (symbol (name (ns-name ns)) (name sym))
                  sym))
              (if-some [var (ns-resolve *ns* sym)]
                (symbol (name (ns-name (get (meta var) :ns))) (name sym))
                (symbol (name (ns-name *ns*)) (name sym)))))
     :cljs (if-some [cljs-ns (:ns env)]
             (if (qualified-symbol? sym)
               (let [ns-sym (symbol (namespace sym))]
                 (if-some [ns (get (:requires cljs-ns) ns-sym)]
                   (symbol (name ns) (name sym))
                   sym))
               (let [ns-uses (get cljs-ns :uses)]
                 (if-some [ns-symbol (get ns-uses sym)]
                   (symbol (name ns-symbol) (name sym))
                   (symbol (name (:name cljs-ns)) (name sym)))))
             sym)))

#?(:clj
   (try
     (let [c (Class/forName "cljs.tagged_literals.JSValue")]
       (defn js-value? [x]
         (instance? c x)))
     (catch ClassNotFoundException _
       (defn js-value? [x]
         false)))
   :cljs
   (defn js-value? [x]
     false))

#?(:clj
   (try
     (let [c (Class/forName "cljs.tagged_literals.JSValue")
           s 'cljs.tagged_literals.JSValue]
       (defn make-js-value [x]
         (eval `(new ~s ~x))))
     (catch ClassNotFoundException _
       (defn make-js-value [x]
         x)))
   :cljs
   (defn make-js-value [x] x))

#?(:clj
   (defmacro val-op
     {:private true}
     [x]
     (try
       (Class/forName "cljs.tagged_literals.JSValue")
       `(.val ^"cljs.tagged_literals.JSValue" ~x)
       (catch ClassNotFoundException _
         nil))))

#?(:clj
   (defn val-of-js-value [x]
     (if (js-value? x) (val-op x) x)))
