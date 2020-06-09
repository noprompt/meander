(ns ^:no-doc meander.util.epsilon
  (:require [clojure.walk :as walk]
            [clojure.zip :as zip]))


(defn cljs-env?
  "true if compiling ClojureScript or in a ClojureScript setting,
  false otherwise."
  [env]
  #?(:clj (some? (:ns env))
     :cljs true))


(defn parse-int
  "Parse the string s as an integer."
  [s]
  #?(:clj (Integer/parseInt s)
     :cljs (js/parseInt s)))


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

(defn k-combination-indices
  {:private true}
  [n k]
  (case k
    0 ()

    1 (map vector (range 0 n))

    2 (mapcat
       (fn [i]
         (let [j (inc i)]
           (map (fn [k] [i k])
                (range j n))))
       (range 0 n))

    ;; else
    (mapcat
     (fn [is]
       (let [i (peek is)
             j (inc i)]
         (map (fn [i] (conj is i))
              (range j n))))
     (k-combination-indices n (dec k)))))

(defn set-k-combinations-with-unselected
  [s k]
  (case k
    0 (list [[] s])

    1 (map (fn [x]
             [[x] (disj s x)])
           s)

    2 (mapcat
       (fn [x]
         (let [s-x (disj s x)]
           (map (fn [y]
                  [[x y] (disj s-x y)])
                s-x)))
       s)

    ;; else
    (mapcat
     (fn [pair]
       (let [xs (nth pair 0)
             s-xs (nth pair 1)]
         (map (fn [x]
                [(conj xs x) (disj s-xs x)])
              s-xs)))
     (set-k-combinations-with-unselected s (dec k)))))

(defn map-k-combinations-with-unselected
  [m k]
  (case k
    0 (list [[] m])

    1 (map (fn [e]
             [[e] (dissoc m (key e))])
           m)

    2 (mapcat
       (fn [e1]
         (map (fn [e2]
                [[e1 e2] (dissoc m (key e2))])
              (dissoc m (key e1))))
       m)

    ;; else
    (mapcat
     (fn [pair]
       (let [es (nth pair 0)
             m-es (nth pair 1)]
         (map (fn [e]
                [(conj es e) (dissoc m-es (key e))])
              m-es)))
     (map-k-combinations-with-unselected m (dec k)))))


(defn k-combinations-with-unselected
  [coll k]
  (let [coll (vec coll)
        coll* (repeat coll)
        n (count coll)]
    (mapcat (fn [is]
              (let [i-head (nth is 0)
                    i-last (peek is)
                    unselected (subvec coll 0 i-head)
                    unselected (reduce
                                (fn [v [a b]]
                                  (into v (subvec coll (inc a) b)))
                                unselected
                                (partition 2 1 is))
                    unselected (into unselected (subvec coll (inc i-last)))]
                (map vector
                     (permutations (map nth coll* is))
                     (repeat unselected))))
            (k-combination-indices n k))))


(defn k-combinations
  "All the ways to choose k items from coll."
  [coll k]
  (let [coll (vec coll)
        n (count coll)]
    (sequence
     (comp
      (map (fn [indices]
             (mapv nth (repeat coll) indices)))
      (mapcat permutations))
     (k-combination-indices n k))))


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
  ([n v]
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
  ([n m v]
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
  ([n coll]
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
  ([n m coll]
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
  [n str]
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
     (.matches (re-matcher re s))
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

(defn knit
  [colls]
  (case (bounded-count 2 colls)
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
              (symbol (name (:name cljs-ns)) (name sym)))
            ;; Clojure
            (if (qualified-symbol? sym)
              (let [ns-sym (symbol (namespace sym))]
                (if-some [ns (get (ns-aliases *ns*) ns-sym)]
                  (symbol (name (ns-name ns)) (name sym))
                  sym))
              (symbol (name (ns-name *ns*)) (name sym))))
     :cljs (if-some [cljs-ns (:ns env)]
             (if (qualified-symbol? sym)
               (let [ns-sym (symbol (namespace sym))]
                 (if-some [ns (get (:requires cljs-ns) ns-sym)]
                   (symbol (name ns) (name sym))
                   sym))
               (symbol (name (:name cljs-ns)) (name sym)))
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

(defn val-of-js-value [x]
  (if (js-value? x) (.val x) x))
