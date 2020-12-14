(ns ^:no-doc meander.pattern-factory.epsilon
  (:refer-clojure :exclude [*
                            +
                            apply
                            cat
                            conj
                            cons
                            concat
                            empty
                            list
                            not
                            hash-map
                            hash-set
                            set
                            seq
                            some
                            symbol
                            vec
                            vector])
  (:require [clojure.core :as clojure]
            [clojure.zip :as zip]
            [meander.syntax.epsilon :as m.syntax]
            [meander.util.epsilon :as m.util]))

;; Runtime
;; -------

;; A runtime is a map of functions and values used to construct
;; matching functions.
;;
;; Matching functions take an object called "target" and a map called
;; "bindings" and return either a passing result with the runtime
;; function "pass", or a failing result with the runtime value "fail".

;; Results are process with the fuctions "fmap" which maps a function
;; f over a result, and "join" which combines two results.

;; Finally, there is the "scan" function which takes a sequence of
;; objects and a function which takes any object and returns a result.


(def default-eval
  #?(:clj eval
     :cljs (fn no-eval [_]
             (throw (ex-info "eval not defined" {})))))

(def find-runtime
  (let [fail nil]
    {:eval default-eval
     :pass identity
     :fail fail
     :fmap (fn [f x]
             (if (identical? x fail)
               x
               (f x)))
     :join (fn
             ([a] a)
             ([a b] a))
     :scan (fn [p xs]
             (reduce
              (fn [_ x]
                (let [y (p x)]
                  (if (identical? y fail)
                    y
                    (reduced y))))
              fail
              xs))}))

(def depth-first-search-runtime
  {:eval default-eval
   :pass clojure/list
   :fail ()
   :fmap mapcat
   :join clojure/concat
   :scan mapcat})

(def breadth-first-search-runtime
  (let [fmap (fn g [f x]
               (if (clojure/seq x)
                 (m.util/mix (f (first x))
                             (lazy-seq (g f (rest x))))
                 ()))]
    {:eval default-eval
     :pass clojure/list
     :fail ()
     :fmap fmap
     :join (fn
             ([a] a)
             ([a b] (m.util/mix a b)))
     :scan fmap}))

;; Pattern factory protocols
;; -------------------------

(defprotocol IMakeQuery
  (make-query [this runtime]))

(defprotocol IMakeYield
  (make-yield [this runtime]))

(defprotocol IVariableKey
  (variable-key [this]))

(defn query-factory
  {:private true}
  [runtime]
  (fn query
    ([i-make-query]
     (let [q (make-query i-make-query runtime)]
       (fn [x] (q x {}))))
    ([i-make-query f]
     (let [fmap (get runtime :fmap)
           pass (get runtime :pass)
           q (make-query i-make-query runtime)
           g (comp pass f)]
       (fn [x]
         (fmap g (q x {})))))))

(defn yield-factory
  {:private true}
  [runtime]
  (fn yield
    ([i-make-yield]
     (let [y (make-yield i-make-yield runtime)]
       (fn [b] (y b))))
    ([i-make-yield f]
     (let [fmap (get runtime :fmap)
           pass (get runtime :pass)
           y (make-yield i-make-yield runtime)
           g (comp pass f)]
       (fn [b]
         (fmap g (y b)))))))

(def ^{:arglists '([i-make-query])}
  find-query
  (query-factory find-runtime))

(defn rewrite-rule
  ([i-make-query i-make-yield]
   (let [q (find-query i-make-query)
         y ((yield-factory find-runtime) i-make-yield)
         fmap (get find-runtime :fmap)
         pass (get find-runtime :pass)]
     (fn [x]
       (fmap (comp pass :object y) (q x)))))
  ([i-make-query i-make-yield f]
   (let [rule (rewrite-rule i-make-query i-make-yield)
         fmap (get find-runtime :fmap)
         pass (get find-runtime :pass)]
     (fn [x]
       (fmap (comp pass f) (rule x))))))

(def ^{:arglists '([i-make-query])}
  dfs-query
  (query-factory depth-first-search-runtime))

(def ^{:arglists '([i-make-query])}
  bfs-query
  (query-factory breadth-first-search-runtime))


;; Pattern factory constructors
;; ----------------------------

(defrecord Factory [make-query make-yield]
  IMakeQuery
  (make-query [this runtime]
    (make-query runtime))

  IMakeYield
  (make-yield [this runtime]
    (make-yield runtime)))

(defn factory [make-query make-yield]
  (Factory. make-query make-yield))

(def anything
  (factory
    (fn anything-make-query [runtime]
      (let [pass (get runtime :pass)]
        (fn anything-query [target bindings]
          (pass bindings))))
    (fn anything-make-yield [runtime]
      (let [pass (get runtime :pass)]
        (fn anything-yield [bindings]
          (pass (assoc bindings :object (reify))))))))

(defn pred [f pf]
  (factory
   (fn pred-make-query [runtime]
     (let [pass (get runtime :pass)
           fail (get runtime :fail)
           pq (make-query pf runtime)]
       (fn pred-query [target bindings]
         (if (f target)
           (pq target bindings)
           fail))))
   (fn pred-make-yield [runtime]
     (let [fail (get runtime :fail)
           fmap (get runtime :fmap)
           pass (get runtime :pass)
           scan (get runtime :scan)
           py (make-yield pf runtime)]
       (fn pred-yield [bindings]
         (fmap (fn [bindings]
                 (let [object (get bindings :object)]
                   (if (f object)
                     (pass bindings)
                     fail)))
               (py bindings)))))))

(defn call [f pf]
  (factory
   (fn call-make-query [runtime]
     (let [pq (make-query pf runtime)]
       (fn call-query [target bindings]
         (pq (f target) bindings))))
   (fn call-make-yield [runtime]
     (let [py (make-yield pf runtime)
           fmap (get runtime :fmap)]
       (fn call-yield [bindings]
         (fmap (fn [bindings]
                 (update bindings :object f))
               (py bindings)))))))

(defn one [pf-1 pf-2]
  (factory
   (fn one-make-query [runtime]
     (let [fail (get runtime :fail)
           p_1 (make-query pf-1 runtime)
           p_2 (make-query pf-2 runtime)]
       (fn one-query [target bindings]
         (let [x (p_1 target bindings)]
           (if (= x fail)
             (p_2 target bindings)
             x)))))
   (fn one-make-yield [runtime]
     (let [fail (get runtime :fail)
           py-1 (make-yield pf-1 runtime)
           py-2 (make-yield pf-2 runtime)]
       (fn one-yield [bindings]
         (let [x (py-1 bindings)]
           (if (= x fail)
             (py-2 bindings)
             x)))))))

(defn some [pf-1 pf-2]
  (factory
   (fn some-make-query [runtime]
     (let [fail (get runtime :fail)
           join (get runtime :join)
           pq-1 (make-query pf-1 runtime)
           pq-2 (make-query pf-2 runtime)]
       (fn some-query [target bindings]
         (let [x (pq-1 target bindings)
               y (pq-2 target bindings)]
           (case [(= x fail) (= y fail)]
             [false false]
             (join x y)

             [false true]
             x

             [true false]
             y

             ;; else
             fail)))))
   (fn some-make-yield [runtime]
     (let [fail (get runtime :fail)
           join (get runtime :join)
           py-1 (make-yield pf-1 runtime)
           py-2 (make-yield pf-2 runtime)]
       (fn some-yield [bindings]
         (let [x (py-1 bindings)
               y (py-2 bindings)]
           (case [(= x fail) (= y fail)]
             [false false]
             (join x y)

             [false true]
             x

             [true false]
             y

             ;; else
             fail)))))))

(defn not [pf]
  (factory
   (fn not-make-query [runtime]
     (let [pass (get runtime :pass)
           fail (get runtime :fail)
           pq (make-query pf runtime)]
       (fn not-query [target bindings]
         (let [x (pq target bindings)]
           (if (= x fail)
             (pass bindings)
             fail)))))
   (fn not-make-yield [runtime]
     (let [pass (get runtime :pass)
           fail (get runtime :fail)
           py (make-yield pf runtime)]
       (fn not-yield [bindings]
         (let [x (py bindings)]
           (if (= x fail)
             (pass bindings)
             fail)))))))

(defn all [pf-1 pf-2]
  (factory
   (fn all-make-query [runtime]
     (let [fmap (get runtime :fmap)
           pq-1 (make-query pf-1 runtime)
           pq-2 (make-query pf-2 runtime)]
       (fn all-query [target bindings]
         (fmap (fn [bindings]
                 (pq-2 target bindings))
               (pq-1 target bindings)))))
   (fn all-make-yield [runtime]
     (let [fmap (get runtime :fmap)
           pq-1 (make-query pf-1 runtime)
           pq-2 (make-query pf-2 runtime)]
       (fn all-query [target bindings]
         (fmap (fn [bindings]
                 (pq-2 target bindings))
          (pq-1 target bindings)))))))

(defrecord Is [value]
  IMakeQuery
  (make-query [this runtime]
    (let [fail (get runtime :fail)
          pass (get runtime :pass)]
      (fn [target bindings]
        (if (= target value)
          (pass bindings)
          fail))))

  IMakeYield
  (make-yield [this runtime]
    (let [pass (get runtime :pass)]
      (fn [bindings]
        (pass (assoc bindings :object value))))))

(defn is [x]
  (Is. x))

(defrecord Project [pf1 pf2 pf3]
  IMakeQuery
  (make-query [this runtime]
    (let [py1 (make-yield pf1 runtime)
          pq2 (make-query pf2 runtime)
          pq3 (make-query pf3 runtime)
          fmap (get runtime :fmap)]
      (fn [target bindings]
        (fmap (fn [py-bindings]
                (let [object (get py-bindings :object)]
                  (fmap (fn [bindings]
                          (pq3 target bindings))
                        (pq2 object bindings))))
              (py1 bindings)))))

  IMakeYield
  (make-yield [this runtime]
    (let [py1 (make-yield pf1 runtime)
          pq2 (make-query pf2 runtime)
          py3 (make-yield pf3 runtime)
          fmap (get runtime :fmap)]
      (fn [bindings]
        (fmap (fn [py-bindings]
                (let [object (get py-bindings :object)]
                  (fmap (fn [bindings]
                          (py3 bindings))
                        (pq2 object bindings))))
              (py1 bindings))))))

(defn project
  ([pf1 pf2]
   (Project. pf1 pf2 anything))
  ([pf1 pf2 pf3]
   (Project. pf1 pf2 pf3)))

(defrecord LogicVariable [id]
  IVariableKey
  (variable-key [this]
    id)

  IMakeQuery
  (make-query [this runtime]
    (let [fail (get runtime :fail)
          pass (get runtime :pass)]
      (fn logic-variable-query [target bindings]
        (if-some [e (find bindings id)]
          (if (= (val e) target)
            (pass bindings)
            fail)
          (pass (assoc bindings id target))))))

  IMakeYield
  (make-yield [this runtime]
    (let [fail (get runtime :fail)
          pass (get runtime :pass)]
      (fn logic-variable-yield [bindings]
        (if-some [e (find bindings id)]
          (pass (assoc bindings :object (val e)))
          fail)))))

(defn logic-variable [id]
  (LogicVariable. id))

(defrecord MemoryVariable [id]
  IVariableKey
  (variable-key [this]
    id)

  IMakeQuery
  (make-query [this runtime]
    (let [pass (get runtime :pass)]
      (fn memory-variable-query [target bindings]
        (pass (update bindings id (fnil clojure/conj []) target)))))

  IMakeYield
  (make-yield [this runtime]
    (let [fail (get runtime :fail)
          pass (get runtime :pass)]
      (fn memory-variable-yield [bindings]
        (if-some [e (find bindings id)]
          (let [xs (val e)]
            (if (clojure/seq xs)
              (let [object (nth xs 0)]
                (pass (merge bindings {:object object, id (rest xs)})))
              fail))
          fail)))))

(defn memory-variable [id]
  (MemoryVariable. id))

(defrecord MutableVariable [id]
  IVariableKey
  (variable-key [this]
    id)

  IMakeQuery
  (make-query [this runtime]
    (let [pass (get runtime :pass)]
      (fn mutable-variable-query [target bindings]
        (pass (assoc bindings id target)))))

  IMakeYield
  (make-yield [this runtime]
    (let [fail (get runtime :fail)
          pass (get runtime :pass)]
      (fn mutable-variable-yield [bindings]
        (if-some [e (find bindings id)]
          (pass (val e))
          fail)))))

(defn mutable-variable [id]
  (MutableVariable. id))

(defrecord Fresh [var-pf pattern-pf]
  IMakeQuery
  (make-query [this runtime]
    (let [key (variable-key var-pf)
          var-pq (make-query pattern-pf runtime)
          pattern-pq (make-query pattern-pf runtime)
          fmap (get runtime :fmap)
          pass (get runtime :pass)]
      (fn fresh-query [target bindings]
        (if-some [e (find bindings key)]
          (let [old-val (val e)]
            (fmap (fn [bindings]
                    (pass (assoc bindings key old-val)))
                  (pattern-pq target (dissoc bindings key))))
          (fmap (fn [bindings]
                  (pass (dissoc bindings key)))
                (pattern-pq target bindings))))))

  IMakeYield
  (make-yield [this runtime]
    (let [key (variable-key var-pf)
          var-py (make-yield pattern-pf runtime)
          pattern-py (make-yield pattern-pf runtime)
          fmap (get runtime :fmap)
          pass (get runtime :pass)]
      (fn fresh-yield [target bindings]
        (if-some [e (find bindings key)]
          (let [old-val (val e)]
            (fmap (fn [bindings]
                    (pass (assoc bindings key old-val)))
                  (pattern-py (dissoc bindings key))))
          (fmap (fn [bindings]
                  (pass (dissoc bindings key)))
                (pattern-py bindings)))))))


(defn make-fresh [var-pf pattern-pf]
  (Fresh. var-pf pattern-pf))

(defrecord Reference [id]
  IMakeQuery
  (make-query [this runtime]
    (let [meta {::reference-id id}]
      (with-meta
        (fn reference-query [target bindings]
          (if-some [pq (get bindings meta)]
            (pq target bindings)
            (throw (ex-info "Unbound reference" {:id id}))))
        meta)))

  IMakeYield
  (make-yield [this runtime]
    (let [meta {::reference-id id}]
      (with-meta
        (fn reference-yield [bindings]
          (if-some [py (get bindings meta)]
            (py bindings)
            (throw (ex-info "Unbound reference" {:id id}))))
        meta))))

(defn reference [id]
  (Reference. id))

(defn with [bindings body-pf]
  (factory
   (fn with-make-query [runtime]
     (let [body-pq (make-query body-pf runtime)
           bindings_with (into {}
                               (map (fn [[reference-pf pf]]
                                      (let [reference-pq (make-query reference-pf runtime)
                                            pq (make-query pf runtime)]
                                        [(meta reference-pq) pq])))
                               (partition 2 bindings))
           bindings-keys (keys bindings_with)
           fmap (get runtime :fmap)
           pass (get runtime :pass)]
       (fn with-query [target bindings]
         (fmap (fn [bindings_body]
                 (pass (merge bindings (reduce dissoc bindings_body bindings-keys))))
               (body-pq target (merge bindings bindings_with))))))
   (fn with-make-yield [runtime]
     (fn with-yield [bindings]
       (let [body-py (make-yield body-pf runtime)
             bindings_with (into {}
                                 (map (fn [[reference-pf pf]]
                                        (let [reference-py (make-yield reference-pf runtime)
                                              py (make-yield pf runtime)]
                                          [(meta reference-py) py])))
                                 (partition 2 bindings))
             bindings-keys (keys bindings_with)
             fmap (get runtime :fmap)
             pass (get runtime :pass)]
         (fn with-yield [target bindings]
           (fmap (fn [bindings_body]
                   (pass (merge bindings (reduce dissoc bindings_body bindings-keys))))
                 (body-py target (merge bindings bindings_with)))))))))

(defn contain
  ([node-pf]
   (factory
     (fn contain-a1-make-query [runtime]
       (let [node-pq (make-query node-pf runtime)
             scan (get runtime :scan)]
         (fn contain-a1-query [target bindings]
           (scan (fn [x]
                   (node-pq x bindings))
                 (m.util/coll-seq target)))))
     (fn contain-a1-make-yield [runtime]
       (let [node-py (make-yield node-pf runtime)]
         (fn contain-a1-yield [bindings]
           (node-py bindings))))))
  ([context-pf node-pf]
   (factory
     (fn contain-a2-make-query [runtime]
       (let [context-pq (make-query context-pf runtime)
             node-pq (make-query node-pf runtime)
             fmap (get runtime :fmap)
             scan (get runtime :scan)]
         (fn contain-a2-query [target bindings]
           (scan (fn [loc]
                   (let [node (zip/node loc)
                         edit (fn [x]
                                (zip/root (zip/replace loc x)))]
                     (fmap (fn [bindings]
                             (context-pq edit bindings))
                           (node-pq node bindings))))
                 (m.util/zip-next-seq (m.util/coll-zip target))))))

     (fn contain-a2-make-yield [runtime]
       (let [context-py (make-yield context-pf runtime)
             node-py (make-yield node-pf runtime)
             fmap (get runtime :fmap)
             pass (get runtime :pass)]
         (fn contain-a2-yield [bindings]
           (fmap (fn [bindings]
                   (let [edit (get bindings :object)]
                     (fmap (fn [bindings]
                             (pass (update bindings :object edit)))
                           (node-py bindings))))
                 (context-py bindings))))))))

(defrecord Cons [head-pf tail-pf]
  IMakeQuery
  (make-query [this runtime]
    (let [head-pq (make-query head-pf runtime)
          tail-pq (make-query tail-pf runtime)
          fail (get runtime :fail)
          fmap (get runtime :fmap)]
      (fn cons-query [target bindings]
        (if (and (sequential? target) (clojure/seq target))
          (let [head (first target)
                tail (next target)]
            (fmap (fn [bindings]
                    (tail-pq tail bindings))
                  (head-pq head bindings)))
          fail))))

  IMakeYield
  (make-yield [this runtime]
    (let [head-py (make-yield head-pf runtime)
          tail-py (make-yield tail-pf runtime)
          fail (get runtime :fail)
          fmap (get runtime :fmap)
          pass (get runtime :pass)]
      (fn cons-yield [bindings]
        (fmap (fn [bindings]
                (let [head (get bindings :object)]
                  (fmap (fn [bindings]
                          (let [tail (get bindings :object)]
                            (if (sequential? tail)
                              (pass (assoc bindings :object (clojure/cons head tail)))
                              fail)))
                        (tail-py bindings))))
              (head-py bindings))))))

(defn cons [head-pf tail-pf]
  (Cons. head-pf tail-pf))

(def empty
  (some (is ()) (is nil)))

(defn list-from [pfs]
  (let [k (count pfs)
        j (inc k)]
    (pred (fn [x]
             (and (seq? x)
                  (= k (bounded-count j x))))
          (reduce (fn [cons-pf pf]
                    (cons pf cons-pf))
                  empty
                  (reverse pfs)))))

(defn list [& pfs]
  (list-from (clojure/vec pfs)))

(defn cat-from [pfs]
  (let [k (count pfs)
        j (inc k)]
    (pred (fn [x]
             (and (sequential? x)
                  (= k (bounded-count j x))))
          (reduce (fn [cons-pf pf]
                    (cons pf cons-pf))
                  empty
                  (reverse pfs)))))

(defn cat [& pfs]
  (cat-from (clojure/vec pfs)))

(defrecord Conj [coll-pf item-pf]
  IMakeQuery
  (make-query [this runtime]
    (let [coll-pq (make-query coll-pf runtime)
          item-pq (make-query item-pf runtime)
          fail (get runtime :fail)
          fmap (get runtime :fmap)
          scan (get runtime :scan)]
      (fn conj-query [target bindings]
        (if (clojure/seq target)
          (cond
            (vector? target)
            (let [coll (pop target)
                  item (peek target)]
              (fmap (fn [bindings]
                      (item-pq item bindings))
                    (coll-pq coll bindings)))

            (map? target)
            (scan (fn [[[entry] rest-map]]
                    (fmap (fn [bindings]
                            (item-pq entry bindings))
                          (coll-pq rest-map bindings)))
                  (m.util/map-k-permutations-with-unselected target 1))

            (set? target)
            (scan (fn [[[element] rest-set]]
                    (fmap (fn [bindings]
                            (item-pq element bindings))
                          (coll-pq rest-set bindings)))
                  (m.util/set-k-permutations-with-unselected target 1))

            (seq? target)
            (let [item (first target)
                  coll (rest target)]
              (fmap (fn [bindings]
                      (item-pq item bindings))
                    (coll-pq coll bindings)))

            :else
            (let [coll (butlast target)
                  item (last target)]
              (fmap (fn [bindings]
                      (item-pq item bindings))
                    (coll-pq coll bindings))))
          fail))))

  IMakeYield
  (make-yield [this runtime]
    (let [coll-py (make-yield coll-pf runtime)
          item-py (make-yield item-pf runtime)
          fail (get runtime :fail)
          fmap (get runtime :fmap)
          scan (get runtime :scan)]
      (fn conj-yield [bindings]
        (fmap (fn [bindings]
                (let [coll (get bindings :object)]
                  (fmap (fn [bindings]
                          (let [item (get bindings :object)]
                            (assoc bindings :object (clojure/conj coll item))))
                        (item-py bindings))))
              (coll-py bindings))))))

(defn conj
  ([coll-pf item-pf]
   (Conj. coll-pf item-pf))
  ([coll-pf item-pf & more-items-pf]
   (call conj (conj coll-pf item-pf) more-items-pf)))

(defn vector-from
  ([]
   (pred vector? (is [])))
  ([& pfs]
   (pred (fn [x] (vector? x))
         (call clojure/vec (list-from pfs)))))

(defrecord Concat [left-pf right-pf]
  IMakeQuery
  (make-query [this runtime]
    (let [fmap (get runtime :fmap)
          scan (get runtime :scan)
          left-pq (make-query left-pf runtime)
          right-pq (make-query right-pf runtime)]
      (fn concat-query [target bindings]
        (scan (fn [[left right]]
                (fmap (fn [bindings]
                        (right-pq right bindings))
                      (left-pq left bindings)))
              (m.util/partitions 2 target)))))

  IMakeYield
  (make-yield [this runtime]
    (let [fail (get runtime :fail)
          fmap (get runtime :fmap)
          pass (get runtime :pass)
          left-py (make-yield left-pf runtime)
          right-py (make-yield right-pf runtime)]
      (fn concat-yield [bindings]
        (fmap (fn [bindings]
                (let [left (get bindings :object)]
                  (if (coll? left)
                    (fmap (fn [bindings]
                            (let [right (get bindings :object)]
                              (if (coll? right)
                                (pass (assoc bindings :object (clojure/concat left right)))
                                fail)))
                          (right-py bindings))
                    fail)))
              (left-py bindings))))))

(defn concat [left-pf right-pf]
  (Concat. left-pf right-pf))

(defrecord GreedyStar [pf]
  IMakeQuery
  (make-query [this runtime]
    (let [fail (get runtime :fail)
          fmap (get runtime :fmap)
          join (get runtime :join)
          pass (get runtime :pass)
          pq (make-query pf runtime)]
      (fn greedy-star-query [target bindings]
        ;; Pass if there is nothing to consume or attempt to consume
        ;; the whole target.
        (if (clojure/seq target)
          (reduce
           (fn [default [left right]]
             (let [x (pq left bindings)]
               (if (= x fail)
                 default
                 (reduced
                  (fmap (fn [bindings]
                          (greedy-star-query right bindings))
                        x)))))
           fail
           (rest (m.util/partitions 2 target)))
          (pass bindings)))))

  IMakeYield
  (make-yield [this runtime]
    (let [fail (get runtime :fail)
          fmap (get runtime :fmap)
          pass (get runtime :pass)
          py (make-yield pf runtime)]
      (fn greedy-star-yield [bindings]
        (let [x (py bindings)]
          (if (= fail x)
            (pass (assoc bindings :object ()))
            (fmap (fn [bindings]
                    (let [y (get bindings :object)]
                      (if (sequential? y)
                        (fmap (fn [bindings]
                                (let [z (get bindings :object)]
                                  (pass (assoc bindings :object (clojure/concat y z)))))
                              (greedy-star-yield bindings))
                        fail)))
                  x)))))))

(defn greedy-star [pf]
  (let [pf (GreedyStar. pf)]
    (pred sequential? pf)))

(defrecord FrugalStar [pf]
  IMakeQuery
  (make-query [this runtime]
    (let [fmap (get runtime :fmap)
          pass (get runtime :pass)
          scan (get runtime :scan)
          pq (make-query pf runtime)]
      (fn frugal-star-query [target bindings]
        ;; Pass if there is nothing to consume or attempt to consume a
        ;; part of the target.
        (if (clojure/seq target)
          (scan (fn [[left right]]
                  (fmap (fn [bindings]
                          (frugal-star-query right bindings))
                        (pq left bindings)))
                (rest (m.util/partitions 2 target)))
          (pass bindings)))))

  IMakeYield
  (make-yield [this runtime]
    (let [fail (get runtime :fail)
          fmap (get runtime :fmap)
          join (get runtime :join)
          pass (get runtime :pass)
          py (make-yield pf runtime)]
      (fn frugal-star-yield [bindings]
        (join (pass (assoc bindings :object ()))
              (fmap (fn [bindings]
                      (let [y (get bindings :object)]
                        (if (sequential? y)
                          (fmap (fn [bindings]
                                  (let [z (get bindings :object)]
                                    (pass (assoc bindings :object (clojure/concat y z)))))
                                (frugal-star-yield bindings))
                          fail)))
                    (py bindings)))))))


(defn frugal-star [pf]
  (FrugalStar. pf))

 (defn frugal-plus [n pf]
   {:pre [(nat-int? n)]}
   (reduce
    (fn [tail-pf pf]
      (concat pf tail-pf))
    (frugal-star pf)
    (repeat n pf)))

(defn greedy-plus [n pf]
   {:pre [(nat-int? n)]}
  (reduce
   (fn [tail-pf pf]
     (concat pf tail-pf))
   (greedy-star pf)
   (repeat n pf)))

(defrecord Entry [key-pf val-pf]
  IMakeQuery
  (make-query [this runtime]
    (let [key-pq (make-query key-pf runtime)
          val-pq (make-query val-pf runtime)
          fmap (get runtime :fmap)]
      (fn entry-query [target bindings]
        (let [k (key target)
              v (val target)]
          (fmap (fn [bindings]
                  (val-pq v bindings))
                (key-pq k bindings))))))

  IMakeYield
  (make-yield [this runtime]
    (let [key-py (make-yield key-pf runtime)
          val-py (make-yield val-pf runtime)
          fmap (get runtime :fmap)
          pass (get runtime :pass)]
      (fn entry-yield [bindings]
        (fmap (fn [bindings]
                (let [k (get bindings :object)]
                  (fmap (fn [bindings]
                          (let [v (get bindings :object)
                                e #?(:clj (clojure.lang.MapEntry/create k v)
                                     :cljs (MapEntry. k v (hash [k v])))]
                            (pass (assoc bindings :object e))))
                        (val-py bindings))))
              (key-py bindings))))))

(defn entry [key-pf val-pf]
  (Entry. key-pf val-pf))

(defrecord HashMapA1 [entries-pf]
  IMakeQuery
  (make-query [this runtime]
    (let [k (count entries-pf)
          entries-pf-cat (list-from entries-pf)
          entries-pq-cat (make-query entries-pf-cat runtime)
          fail (get runtime :fail)
          pass (get runtime :pass)
          scan (get runtime :scan)]
      (fn hash-map-a1-query [target bindings]
        (if (map? target)
          (scan (fn [[entries _]]
                  (entries-pq-cat entries bindings))
                (m.util/map-k-permutations-with-unselected target k))
          fail))))

  IMakeYield
  (make-yield [this runtime]
    (let [entries-pf-cat (list-from entries-pf)
          entries-py-cat (make-yield entries-pf-cat runtime)
          fmap (get runtime :fmap)
          pass (get runtime :pass)]
      (fn hash-map-a1-yield [bindings]
        (fmap (fn [bindings]
                (let [entries (get bindings :object)]
                  (pass (assoc bindings :object (into {} entries)))))
              (entries-py-cat bindings))))))

(defrecord HashMapA2 [entries-pf rest-pf-map]
  IMakeQuery
  (make-query [this runtime]
    (let [k (count entries-pf)
          entries-pf-cat (list-from entries-pf)
          entries-pq-cat (make-query entries-pf-cat runtime)
          rest-pq-map (make-query rest-pf-map runtime)
          fail (get runtime :fail)
          fmap (get runtime :fmap)
          pass (get runtime :pass)
          scan (get runtime :scan)]
      (fn hash-map-a2-query [target bindings]
        (if (map? target)
          (scan (fn [[entries rest-map]]
                  (fmap (fn [bindings]
                          (rest-pq-map rest-map bindings))
                        (entries-pq-cat entries bindings)))
                (m.util/map-k-permutations-with-unselected target k))
          fail))))

  IMakeYield
  (make-yield [this runtime]
    (let [entries-pf-cat (list-from entries-pf)
          entries-py-cat (make-yield entries-pf-cat runtime)
          rest-py-map (make-yield rest-pf-map runtime)
          fmap (get runtime :fmap)
          pass (get runtime :pass)]
      (fn hash-map-a2-yield [bindings]
        (fmap (fn [bindings]
                (let [entries (get bindings :object)]
                  (fmap (fn [bindings]
                          (let [rest-map (get bindings :object)]
                            (pass (assoc bindings :object (into rest-map entries)))))
                        (rest-py-map bindings))))
              (entries-py-cat bindings))))))

(defrecord SetA1 [elements-pf]
  IMakeQuery
  (make-query [this runtime]
    (let [k (count elements-pf)
          elements-pf-cat (list-from elements-pf)
          elements-pq-cat (make-query elements-pf-cat runtime)
          scan (get runtime :scan)
          fail (get runtime :fail)]
      (fn set-a1-query [target bindings]
        (if (set? target)
          (scan (fn [[elements _]]
                  (elements-pq-cat elements bindings))
                (m.util/set-k-permutations-with-unselected target k))
          fail))))

  IMakeYield
  (make-yield [this runtime]
    (let [elements-pf-cat (list-from elements-pf)
          elements-py-cat (make-yield elements-pf-cat runtime)
          fmap (get runtime :fmap)]
      (fn set-a1-yield [bindings]
        (fmap (fn [bindings]
                (let [elements (get bindings :object)]
                  (clojure/set elements)))
              (elements-py-cat bindings))))))

(defrecord SetA2 [elements-pf rest-pf-set]
  IMakeQuery
  (make-query [this runtime]
    (let [k (count elements-pf)
          elements-pf-cat (list-from elements-pf)
          elements-pq-cat (make-query elements-pf-cat runtime)
          rest-pq-set (make-query rest-pf-set runtime)
          scan (get runtime :scan)
          fail (get runtime :fail)
          fmap (get runtime :fmap)]
      (fn set-a2-query [target bindings]
        (if (set? target)
          (scan (fn [[elements rest-set]]
                  (fmap (fn [bindings]
                          (rest-pq-set rest-set bindings))
                        (elements-pq-cat elements bindings)))
                (m.util/set-k-permutations-with-unselected target k))
          fail))))

  IMakeYield
  (make-yield [this runtime]
    (let [elements-pf-cat (list-from elements-pf)
          elements-py-cat (make-yield elements-pf-cat runtime)
          rest-py-set (make-yield rest-pf-set runtime)
          fmap (get runtime :fmap)
          fail (get runtime :fail)]
      (fn set-a2-yield [bindings]
        (fmap (fn [bindings]
                (let [elements (get bindings :object)]
                  (fmap (fn [bindings]
                          (let [rest-set (get bindings :object)]
                            (if (set? rest)
                              (into rest-set elements)
                              fail))))
                  (rest-py-set bindings)))
              (elements-py-cat bindings))))))

(defn set-from [element-pfs]
  {:pre [(sequential? element-pfs)]}
  (SetA1. (clojure/vec element-pfs)))

(defn into-set-of [element-pfs rest-pf-set]
  {:pre [(sequential? element-pfs)]}
  (SetA2. (clojure/vec element-pfs) rest-pf-set))

(defrecord SimpleSymbol [name-pf]
  IMakeQuery
  (make-query [this runtime]
    (let [name-pf (make-query name-pf runtime)
          fail (get runtime :fail)
          fmap (get runtime :fmap)
          pass (get runtime :pass)]
      (fn [target bindings]
        (if (symbol? target)
          (name-pf (name target) bindings)
          fail))))
  
  IMakeYield
  (make-yield [this runtime]
    (let [pass (get runtime :pass)]
      (fn [bindings]
        (let [name-py (make-query name-pf runtime)
              fail (get runtime :fail)
              fmap (get runtime :fmap)
              pass (get runtime :pass)]
          (fn [bindings]
            (fmap (fn [bindings]
                    (let [object (get bindings :object)]
                      (if (string? object)
                        (pass (assoc bindings :object (clojure/symbol object)))
                        fail)))
                  (name-py name bindings))))))))

(defrecord QualifiedSymbol [namespace-pf name-pf]
  IMakeQuery
  (make-query [this runtime]
    (let [namespace-pq (make-query namespace-pf runtime)
          name-pq (make-query name-pf runtime)
          fail (get runtime :fail)
          fmap (get runtime :fmap)
          pass (get runtime :pass)]
      (fn [target bindings]
        (if (symbol? target)
          (let [namespace (namespace target)
                name (name target)]
            (fmap (fn [bindings]
                    (name-pq name bindings))
                  (namespace-pq namespace bindings)))
          fail))))
  
  IMakeYield
  (make-yield [this runtime]
    (let [pass (get runtime :pass)]
      (fn [bindings]
        (let [namespace-py (make-query namespace-pf runtime)
              name-py (make-query name-pf runtime)
              fail (get runtime :fail)
              fmap (get runtime :fmap)
              pass (get runtime :pass)]
          (fn [bindings]
            (fmap (fn [bindings]
                    (let [namespace (get bindings :object)]
                      (if (string? namespace)
                        (fmap (fn [bindings]
                                (let [name (get bindings :object)]
                                  (if (string? name)
                                    (pass (assoc bindings :object (clojure/symbol namespace name)))
                                    fail)))
                              (name-py name bindings))
                        fail)))
                  (namespace-py namespace bindings))
            fail))))))

(defn symbol
  ([name_pf]
   (SimpleSymbol. name_pf))
  ([namespace_pf name_pf]
   (QualifiedSymbol. namespace_pf name_pf)))

;; Macros
;; ---------------------------------------------------------------------

(defmacro fresh
  {:style/indent 1}
  [vars body]
  (reduce
    (fn [body var]
      `(let [~var ~(case (get (m.syntax/parse var) :tag)
                     :lvr `(logic-variable '~var)
                     :mvr `(memory-variable '~var)
                     :mut `(mutable-variable '~var))]
         (Fresh. ~var ~body)))
    body
    vars))

(defmacro stale
  {:style/indent 1}
  [vars body]
  (reduce
    (fn [body var]
      `(let [~var ~(case (get (m.syntax/parse var) :tag)
                     :lvr `(logic-variable '~var)
                     :mvr `(memory-variable '~var)
                     :mut `(mutable-variable '~var))]
         ~body))
    body
    vars))

(def ^{:arglists '([form])
       :private true}
  parse-*-or-+-args
  (stale [!xs ?as]
    (rewrite-rule
     (concat (greedy-star (list !xs))
             (one empty (list (is :as) ?as)))
     ;; =>
     (list (greedy-star (list !xs))
           (one ?as (is nil))))))

(defmacro *
  [& args]
  (if-some [[form-pfs as] (parse-*-or-+-args args)]
    (if as
      `(greedy-star (all (list ~@form-pfs) ~as))
      `(greedy-star (list ~@form-pfs)))
    (throw (ex-info "Invalid syntax" {:form &form}))))

(defmacro *?
  [& args]
  (if-some [[form-pfs as] (parse-*-or-+-args args)]
    (if as
      `(frugal-star (all (list ~@form-pfs) ~as))
      `(frugal-star (list ~@form-pfs)))
    (throw (ex-info "Invalid syntax" {:form &form}))))

(defmacro +
  [n & args]
  (if-some [[form-pfs as] (parse-*-or-+-args args)]
    (if as
      `(greedy-plus ~n (all (list ~@form-pfs) ~as))
      `(greedy-plus ~n (list ~@form-pfs)))
    (throw (ex-info "Invalid syntax" {:form &form}))))

(defmacro +? [n & args]
  (if-some [[form-pfs as] (parse-*-or-+-args args)]
    (if as
      `(frugal-plus ~n (all (list ~@form-pfs) ~as))
      `(frugal-plus ~n (list ~@form-pfs)))
    (throw (ex-info "Invalid syntax" {:form &form}))))

(defn seq [pf]
  (factory
   (fn list-make-query [runtime]
     (let [pq (make-query pf runtime)
           fail (get runtime :fail)]
       (fn [target bindings]
         (if (seqable? target)
           (pq target bindings)
           fail))))
   (fn list-make-yield [runtime]
     (let [py (make-yield pf runtime)
           fail (get runtime :fail)
           fmap (get runtime :fmap)
           pass (get runtime :pass)]
       (fn [bindings]
         (fmap (fn [bindings]
                 (let [object (get bindings :object)]
                   (if (seqable? object)
                     (pass (update bindings :object clojure/seq))
                     fail)))
               (py bindings)))))))

(defn vec [pf]
  (factory
   (fn [runtime]
     (let [pq (make-query pf runtime)
           fail (get runtime :fail)]
       (fn [target bindings]
         (if (vector? target)
           (pq target bindings)
           fail))))
   (fn [runtime]
     (let [py (make-yield pf runtime)
           fail (get runtime :fail)
           fmap (get runtime :fmap)
           pass (get runtime :pass)]
       (fn [bindings]
         (fmap (fn [bindings]
                 (let [object (get bindings :object)]
                   (if (coll? object)
                     (pass (update bindings :object clojure/vec))
                     fail)))
               (py bindings)))))))

(def ^{:arglists '([form])
       :private true}
  parse-vector-args
  (stale [!x ?rest]
    (rewrite-rule
     (concat (* !x)
             (one (list (is '&) ?rest) empty))
     (list (* !x) (one ?rest (is nil))))))

(defmacro vector [& args]
  (let [[items rest-vector] (parse-vector-args args)]
    (if (some? rest-vector)
      `(vec (concat (list ~@items) ~rest-vector))
      `(vec (list ~@items)))))

(defn hash-map-from
  ([entry-pfs]
   (HashMapA1. entry-pfs))
  ([entry-pfs rest-pf]
   (HashMapA2. entry-pfs rest-pf)))

(def ^{:arglists '([form])
       :private true}
  parse-hash-map-args
  (stale [!k ?rest-map ?as-map]
    (rewrite-rule
     (concat (* !k !k)
             (concat (one empty (list (is '&) ?rest-map))
                     (one empty (list (is ':as) ?as-map))))
     (list (greedy-star (list (vector !k !k)))
           (one ?rest-map (is nil))
           (one ?as-map (is nil))))))

(defmacro hash-map
  [& args]
  (if-some [[keyvals rest-map as-map] (parse-hash-map-args args)]
    (let [entries (map (fn [[k v]] `(entry ~k ~v)) keyvals)
          form-pf (if (some? rest-map)
                    `(HashMapA2. ~(clojure/vec entries) ~rest-map)
                    `(HashMapA1. ~(clojure/vec entries)))
          form-pf (if (some? as-map)
                    `(all ~as-map ~form-pf)
                    form-pf)]
      form-pf)
    (throw (ex-info "hash-map expects and even number of arguments" {:form &form}))))

(def ^{:arglists '([form])
       :private true}
  parse-hash-set-args
  (stale [!e ?rest-set ?as-set]
    (rewrite-rule
     (concat (greedy-star (list !e))
             (concat (one empty (list (is '&) ?rest-set))
                     (one empty (list (is ':as) ?as-set))))
     (list (greedy-star (list !e))
           (one ?rest-set (is nil))
           (one ?as-set (is nil))))))

(defmacro hash-set
  [& args]
  (if-some [[elements rest-set as-set] (parse-hash-set-args args)]
    (let [pf-form (if (some? rest-set)
                    `(HashMapA2. ~(clojure/vec elements) ~rest-set)
                    `(HashMapA1. ~(clojure/vec elements)))
          pf-form (if (some? as-set)
                    `(all ~pf-form ~as-set)
                    pf-form)]
      pf-form)
    (throw (ex-info "Invalid syntax" {:form &form}))))
