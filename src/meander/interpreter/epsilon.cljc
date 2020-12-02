(ns meander.interpreter.epsilon
  (:refer-clojure :exclude [apply
                            cat
                            cons
                            concat
                            empty
                            hash-map
                            set
                            some])
  (:require [clojure.core :as clojure]
            [clojure.zip :as zip]
            [meander.environment.epsilon :as m.environment]
            [meander.util.epsilon :as m.util]
            [meander.match.syntax.epsilon :as m.match.syntax]
            [meander.syntax.epsilon :as m.syntax]))



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

(def
  ^{:private true}
  find-runtime
  (let [fail nil #_(reify)]
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

(def
  ^{:private true}
  depth-first-search-runtime
  {:eval default-eval
   :pass list
   :fail ()
   :fmap mapcat
   :join clojure/concat
   :scan mapcat})

(def
  ^{:private true}
  breadth-first-search-runtime
  (let [fmap (fn g [f x]
               (if (seq x)
                 (m.util/mix (f (first x))
                             (lazy-seq (g f (rest x))))
                 ()))]
    {:eval default-eval
     :pass list
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

;; Pattern factory constructors
;; ----------------------------

(def anything
  (reify
    IMakeQuery
    (make-query [this runtime]
      (let [pass (get runtime :pass)]
        (fn anything-query [target bindings]
          (pass bindings))))

    IMakeYield
    (make-yield [this runtime]
      (let [pass (get runtime :pass)]
        (fn anything-yield [bindings]
          (pass (assoc bindings :object (reify))))))))

(defn pred [f g]
  (reify
    IMakeQuery
    (make-query [this runtime]
      (let [pass (get runtime :pass)
            fail (get runtime :fail)]
        (fn pred-query [target bindings]
          (if (f target)
            (pass (assoc bindings :object target))
            fail))))

    IMakeYield
    (make-yield [this runtime]
      (let [pass (get runtime :pass)
            scan (get runtime :scan)]
        (fn pred-yield [bindings]
          (scan (fn [x]
                  (pass (assoc bindings :object x)))
                (g)))))))

(defn apply [f pf]
  (reify
    IMakeQuery
    (make-query [this runtime]
      (let [pq (make-query pf runtime)]
        (fn apply-query [target bindings]
          (pq (f target) bindings))))

    IMakeYield
    (make-yield [this runtime]
      (let [py (make-yield pf runtime)
            fmap (get runtime :fmap)]
        (fn apply-yield [bindings]
          (fmap (fn [bindings]
                  (update bindings :object f))
                (py bindings)))))))

(defn one [pf_1 pf_2]
  (reify
    IMakeQuery
    (make-query [this runtime]
      (let [fail (get runtime :fail)
            p_1 (make-query pf_1 runtime)
            p_2 (make-query pf_2 runtime)]
        (fn one-query [target bindings]
          (let [x (p_1 target bindings)]
            (if (= x fail)
              (p_2 target bindings)
              x)))))

    IMakeYield
    (make-yield [this runtime]
      (let [fail (get runtime :fail)
            p_1 (make-yield pf_1 runtime)
            p_2 (make-yield pf_2 runtime)]
        (fn one-yield [bindings]
          (let [x (p_1 bindings)]
            (if (= x fail)
              (p_2 bindings)
              x)))))))

(defn some [pf_1 pf_2]
  (reify
    IMakeQuery
    (make-query [this runtime]
      (let [fail (get runtime :fail)
            join (get runtime :join)
            pq_1 (make-query pf_1 runtime)
            pq_2 (make-query pf_2 runtime)]
        (fn some-query [target bindings]
          (let [x (pq_1 target bindings)
                y (pq_2 target bindings)]
            (case [(= x fail) (= y fail)]
              [false false]
              (join x y)

              [false true]
              x

              [true false]
              y

              ;; else
              fail)))))

    IMakeYield
    (make-yield [this runtime]
      (let [fail (get runtime :fail)
            join (get runtime :join)
            py_1 (make-yield pf_1 runtime)
            py_2 (make-yield pf_2 runtime)]
        (fn some-yield [bindings]
          (let [x (py_1 bindings)
                y (py_2 bindings)]
            (case [(= x fail) (= y fail)]
              [false false]
              (join x y)

              [false true]
              x

              [true false]
              y

              ;; else
              fail)))))))

(defn all [pf_1 pf_2]
  (reify
    IMakeQuery
    (make-query [this runtime]
      (let [fmap (get runtime :fmap)
            pq_1 (make-query pf_1 runtime)
            pq_2 (make-query pf_2 runtime)]
        (fn all-query [target bindings]
          (fmap (fn [bindings] (pq_2 target bindings))
                (pq_1 target bindings)))))

    IMakeYield
    (make-yield [this runtime]
      (let [fmap (get runtime :fmap)
            join (get runtime :join)
            pq_1 (make-query pf_1 runtime)
            pq_2 (make-query pf_2 runtime)
            py_1 (make-yield pf_1 runtime)
            py_2 (make-yield pf_2 runtime)]
        (fn all-yield [bindings]
          (join (fmap (fn [bindings]
                        (pq_2 (get bindings :object) bindings))
                      (py_1 bindings))
                (fmap (fn [bindings]
                        (pq_1 (get bindings :object) bindings))
                      (py_2 bindings))))))))

(defn literal [x]
  (pred (fn [y] (= y x)) (fn [] [x])))

(defn logic-variable [id]
  (reify
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
            fail))))))

(defn memory-variable [id]
  (reify
    IMakeQuery
    (make-query [this runtime]
      (let [pass (get runtime :pass)]
        (fn memory-variable-query [target bindings]
          (pass (update bindings id (fnil conj []) target)))))

    IMakeYield
    (make-yield [this runtime]
      (let [fail (get runtime :fail)
            pass (get runtime :pass)]
        (fn memory-variable-yield [bindings]
          (if-some [e (find bindings id)]
            (let [xs (val e)]
              (if (seq xs)
                (let [object (nth xs 0)]
                  (pass (merge bindings {:object object, id (rest xs)})))
                fail))
            fail))))))

(defn mutable-variable [id]
  (reify
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
            fail))))))

(defn reference [id]
  (reify
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
          meta)))))

(defn with [bindings pf_body]
  (reify
    IMakeQuery
    (make-query [this runtime]
      (let [pq_body (make-query pf_body runtime)
            bindings_with (into {}
                                (map (fn [[pf_reference pf]]
                                       (let [pq_reference (make-query pf_reference runtime)
                                             pq (make-query pf runtime)]
                                         [(meta pq_reference) pq])))
                                (partition 2 bindings))
            bindings-keys (keys bindings_with)
            fmap (get runtime :fmap)
            pass (get runtime :pass)]
        (fn with-query [target bindings]
          (fmap (fn [bindings_body]
                  (pass (merge bindings (reduce dissoc bindings_body bindings-keys))))
                (pq_body target (merge bindings bindings_with))))))

    IMakeYield
    (make-yield [this runtime]
      (fn with-yield [bindings]
        (let [py_body (make-yield pf_body runtime)
              bindings_with (into {}
                                  (map (fn [[pf_reference pf]]
                                         (let [py_reference (make-yield pf_reference runtime)
                                               py (make-yield pf runtime)]
                                           [(meta py_reference) py])))
                                  (partition 2 bindings))
              bindings-keys (keys bindings_with)
              fmap (get runtime :fmap)
              pass (get runtime :pass)]
          (fn with-yield [target bindings]
            (fmap (fn [bindings_body]
                    (pass (merge bindings (reduce dissoc bindings_body bindings-keys))))
                  (py_body target (merge bindings bindings_with)))))))))

(defn contain
  ([pf_node]
   (reify
     IMakeQuery
     (make-query [this runtime]
       (let [pq_node (make-query pf_node runtime)
             scan (get runtime :scan)]
         (fn contain-a1-query [target bindings]
           (scan (fn [x]
                   (pq_node x bindings))
                 (m.util/coll-seq target)))))

     IMakeYield
     (make-yield [this runtime]
       (let [py_node (make-yield pf_node runtime)]
         (fn contain-a1-yield [bindings]
           (py_node bindings))))))
  ([pf_context pf_node]
   (reify
     IMakeQuery
     (make-query [this runtime]
       (let [pq_context (make-query pf_context runtime)
             pq_node (make-query pf_node runtime)
             fmap (get runtime :fmap)
             scan (get runtime :scan)]
         (fn contain-a2-query [target bindings]
           (scan (fn [loc]
                   (let [node (zip/node loc)
                         edit (fn [x]
                                (zip/root (zip/replace loc x)))]
                     (fmap (fn [bindings]
                             (pq_context edit bindings))
                           (pq_node node bindings))))
                 (m.util/zip-next-seq (m.util/coll-zip target))))))

     IMakeYield
     (make-yield [this runtime]
       (let [py_context (make-yield pf_context runtime)
             py_node (make-yield pf_node runtime)
             fmap (get runtime :fmap)
             pass (get runtime :pass)]
         (fn contain-a2-yield [bindings]
           (fmap (fn [bindings]
                   (let [edit (get bindings :object)]
                     (fmap (fn [bindings]
                             (pass (update bindings :object edit)))
                           (py_node bindings))))
                 (py_context bindings))))))))

(def empty
  (pred empty? (fn [] [[]])))

(defn cons
  [pf_1 pf_2]
  (reify
    IMakeQuery
    (make-query [this runtime]
      (let [pq_1 (make-query pf_1 runtime)
            pq_2 (make-query pf_2 runtime)
            fail (get runtime :fail)
            fmap (get runtime :fmap)]
        (fn cons-query [target bindings]
          (if (sequential? target)
            (let [[head & tail] target]
              (fmap (fn [bindings]
                      (pq_2 tail bindings))
                    (pq_1 head bindings)))
            fail))))

    IMakeYield
    (make-yield [this runtime]
      (let [py_1 (make-yield pf_1 runtime)
            py_2 (make-yield pf_2 runtime)
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
                          (py_2 bindings))))
                (py_1 bindings)))))))

(defn cat [pfs]
  (reduce (fn [pf_cons pf]
            (cons pf pf_cons))
          empty
          (reverse pfs)))

(defn concat [pf_left pf_right]
  (reify
    IMakeQuery
    (make-query [this runtime]
      (let [fmap (get runtime :fmap)
            scan (get runtime :scan)
            pq_left (make-query pf_left runtime)
            pq_right (make-query pf_right runtime)]
        (fn concat-query [target bindings]
          (scan (fn [[left right]]
                  (fmap (fn [bindings]
                          (pq_right right bindings))
                        (pq_left left bindings)))
                (m.util/partitions 2 target)))))

    IMakeYield
    (make-yield [this runtime]
      (let [fail (get runtime :fail)
            fmap (get runtime :fmap)
            pass (get runtime :pass)
            py_left (make-yield pf_left runtime)
            py_right (make-yield pf_right runtime)]
        (fn concat-yield [bindings]
          (fmap (fn [bindings]
                  (let [left (get bindings :object)]
                    (if (coll? left)
                      (fmap (fn [bindings]
                              (let [right (get bindings :object)]
                                (if (coll? right)
                                  (pass (assoc bindings :object (clojure/concat left right)))
                                  fail)))
                            (py_right bindings))
                      fail)))
                (py_left bindings)))))))


(defn greedy-star [pf]
  (reify
    IMakeQuery
    (make-query [this runtime]
      (let [fail (get runtime :fail)
            fmap (get runtime :fmap)
            join (get runtime :join)
            pass (get runtime :pass)
            pq (make-query pf runtime)]
        (fn greedy-star-query [target bindings]
          (reduce
           (fn [default [left right]]
             (let [x (pq left bindings)]
               (if (= x fail)
                 default
                 (reduced
                  (fmap (fn [bindings]
                          (greedy-star-query right bindings))
                        x)))))
           (pass bindings)
           (m.util/partitions 2 target)))))

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
                    x))))))))


(defn frugal-star [pf]
  (reify
    IMakeQuery
    (make-query [this runtime]
      (let [fmap (get runtime :fmap)
            pass (get runtime :pass)
            scan (get runtime :scan)
            pq (make-query pf runtime)]
        (fn frugal-star-query [target bindings]
          (if (seq target)
            (scan (fn [[left right]]
                    (fmap (fn [bindings]
                            (frugal-star-query right bindings))
                          (pq left bindings)))
                  (m.util/partitions 2 target))
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
                      (py bindings))))))))


 (defn frugal-plus [pf n]
   (reduce
    (fn [pf_tail pf]
      (concat pf pf_tail))
    (frugal-star pf)
    (repeat n pf)))

(defn greedy-plus [pf n]
  (reduce
   (fn [pf_tail pf]
     (concat pf pf_tail))
   (greedy-star pf)
   (repeat n pf)))

(defn entry [pf_key pf_val]
  (reify
    IMakeQuery
    (make-query [this runtime]
      (let [pq_key (make-query pf_key runtime)
            pq_val (make-query pf_val runtime)
            fmap (get runtime :fmap)]
        (fn entry-query [target bindings]
          (let [k (key target)
                v (val target)]
            (fmap (fn [bindings]
                    (pq_val v bindings))
                  (pq_key k bindings))))))

    IMakeYield
    (make-yield [this runtime]
      (let [py_key (make-yield pf_key runtime)
            py_val (make-yield pf_val runtime)
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
                          (py_val bindings))))
                (py_key bindings)))))))

(defn hash-map
  ([pf_entries]
   {:pre [(sequential? pf_entries)]}
   (let [k (count pf_entries)
         pf_entries-cat (cat pf_entries)]
     (reify
       IMakeQuery
       (make-query [this runtime]
         (let [pq_entries-cat (make-query pf_entries-cat runtime)
               fail (get runtime :fail)
               pass (get runtime :pass)
               scan (get runtime :scan)]
           (fn hash-map-a1-query [target bindings]
             (if (map? target)
               (scan (fn [[entries _]]
                       (pq_entries-cat entries bindings))
                     (m.util/map-k-permutations-with-unselected target k))
               fail))))

       IMakeYield
       (make-yield [this runtime]
         (let [py_entries-cat (make-yield pf_entries-cat runtime)
               fmap (get runtime :fmap)
               pass (get runtime :pass)]
           (fn hash-map-a1-yield [bindings]
             (fmap (fn [bindings]
                     (let [entries (get bindings :object)]
                       (pass (assoc bindings :object (into {} entries)))))
                   (py_entries-cat bindings))))))))
  ([pf_entries pf_rest-map]
   {:pre [(sequential? pf_entries)]}
   (let [k (count pf_entries)
         pf_entries-cat (cat pf_entries)]
     (reify
       IMakeQuery
       (make-query [this runtime]
         (let [pq_entries-cat (make-query pf_entries-cat runtime)
               pq_rest-map (make-query pf_rest-map runtime)
               fail (get runtime :fail)
               fmap (get runtime :fmap)
               pass (get runtime :pass)
               scan (get runtime :scan)]
           (fn hash-map-a2-query [target bindings]
             (if (map? target)
               (scan (fn [[entries rest-map]]
                       (fmap (fn [bindings]
                               (pq_rest-map rest-map bindings))
                             (pq_entries-cat entries bindings)))
                     (m.util/map-k-permutations-with-unselected target k))
               fail))))

       IMakeYield
       (make-yield [this runtime]
         (let [py_entries-cat (make-yield pf_entries-cat runtime)
               py_rest-map (make-yield pf_rest-map runtime)
               fmap (get runtime :fmap)
               pass (get runtime :pass)]
           (fn hash-map-a2-yield [bindings]
             (fmap (fn [bindings]
                     (let [entries (get bindings :object)]
                       (fmap (fn [bindings]
                               (let [rest-map (get bindings :object)]
                                 (pass (assoc bindings :object (into rest-map entries)))))
                             (py_rest-map bindings))))
                   (py_entries-cat bindings)))))))))

(defn set
  ([pf_elements]
   {:pre [(sequential? pf_elements)]}
   (let [k (count pf_elements)
         pf_elements-cat (cat pf_elements)]
     (reify
       IMakeQuery
       (make-query [this runtime]
         (let [pq_elements-cat (make-query pf_elements-cat runtime)
               scan (get runtime :scan)
               fail (get runtime :fail)]
           (fn set-a1-query [target bindings]
             (if (set? target)
               (scan (fn [[elements _]]
                       (pq_elements-cat elements bindings))
                     (m.util/set-k-permutations-with-unselected target k))
               fail))))

       IMakeYield
       (make-yield [this runtime]
         (let [py_elements-cat (make-yield pf_elements-cat runtime)
               fmap (get runtime :fmap)]
           (fn set-a1-yield [bindings]
             (fmap (fn [bindings]
                     (let [elements (get bindings :object)]
                       (clojure/set elements)))
                   (py_elements-cat bindings))))))))
  ([pf_elements pf_rest-set]
   {:pre [(sequential? pf_elements)]}
   (let [k (count pf_elements)
         pf_elements-cat (cat pf_elements)]
     (reify
       IMakeQuery
       (make-query [this runtime]
         (let [pq_elements-cat (make-query pf_elements-cat runtime)
               pq_rest-set (make-query pf_rest-set runtime)
               scan (get runtime :scan)
               fail (get runtime :fail)
               fmap (get runtime :fmap)]
           (fn set-a2-query [target bindings]
             (if (set? target)
               (scan (fn [[elements rest-set]]
                       (fmap (fn [bindings]
                               (pq_rest-set rest-set bindings))
                             (pq_elements-cat elements bindings)))
                     (m.util/set-k-permutations-with-unselected target k))
               fail))))

       IMakeYield
       (make-yield [this runtime]
         (let [py_elements-cat (make-yield pf_elements-cat runtime)
               py_rest-set (make-yield pf_rest-set runtime)
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
                       (py_rest-set bindings)))
                   (py_elements-cat bindings)))))))))

(defn -pattern-dispatch
  {:private true}
  [ast]
  (get ast :tag))

(defmulti -pattern
  {:arglists '([ast])
   :private true}
  #'-pattern-dispatch)

(defn pattern
  {:private true}
  [form]
  (-pattern (m.match.syntax/parse form)))

(defmethod -pattern :any [_]
  anything)

(defmethod -pattern :cat
  [ast]
  (cat (map -pattern (get ast :elements))))

(defmethod -pattern :ctn
  [ast]
  (let [pf_pattern (-pattern (get ast :pattern))]
    (if-some [context (get ast :context)]
      ;; {:tag :ctn, :context {:as ?context} :pattern ?pattern}
      (contain (-pattern context) pf_pattern)
      ;; {:tag :ctn, :context nil :pattern ?pattern}
      (contain pf_pattern))))

(defmethod -pattern :drp [_]
  anything)

(defmethod -pattern :jsa [ast])

(defmethod -pattern :jso [ast])

(defmethod -pattern :lvr
  [ast]
  (logic-variable (get ast :symbol)))

(defmethod -pattern :lit
  [ast]
  (literal (get ast :value)))

(defmethod -pattern :map [ast]
  (if-some [as (get ast :as)]
    (all (-pattern as)
         (-pattern (assoc ast :as nil)))
    (let [pf_entries (map (fn [[k v]]
                            (entry (-pattern k) (-pattern v)))
                          (get ast :map))]
      (if-some [rest-map (get ast :rest-map)]
        (hash-map pf_entries (-pattern rest-map))
        (hash-map pf_entries)))))

(defmethod -pattern :mut
  [ast]
  (mutable-variable (get ast :symbol)))

(defmethod -pattern :mvr [ast]
  (memory-variable (get ast :symbol)))

(defmethod -pattern :quo [ast]
  (literal (get ast :form)))

(defmethod -pattern :prt
  [ast]
  (concat (-pattern (get ast :left))
          (-pattern (get ast :right))))

(defmethod -pattern :seq
  [ast]
  (let [pf_prt (-pattern (get ast :prt))
        pf_seq (reify
                 IMakeQuery
                 (make-query [this runtime]
                   (let [pq_prt (make-query pf_prt runtime)
                         fail (get runtime :fail)]
                     (fn seq-query [target bindings]
                       (if (seq? target)
                         (pq_prt target bindings)
                         fail))))

                 IMakeYield
                 (make-yield [this runtime]
                   (let [py_prt (make-yield pf_prt runtime)]
                     py_prt)))]
    (if-some [as (get ast :as)]
      (all (-pattern as) pf_seq)
      pf_seq)))

(defmethod -pattern :ref
  [ast]
  (reference (get ast :symbol)))

(defmethod -pattern :rp* [ast]
  (let [pf_cat (-pattern (get ast :cat))
        pf_frugal-star (frugal-star pf_cat)
        pf_greedy-star (greedy-star pf_cat)]
    (reify
      IMakeQuery
      (make-query [this runtime]
        (make-query pf_frugal-star runtime))

      IMakeYield
      (make-yield [this runtime]
        (make-query pf_greedy-star runtime)))))

(defmethod -pattern :rp+
  [ast]
  (let [n (get ast :n)
        pf_cat (-pattern (get ast :cat))
        pf_frugal-plus (frugal-plus pf_cat n)
        pf_greedy-plus (greedy-plus pf_cat n)]
    (reify
      IMakeQuery
      (make-query [this runtime]
        (make-query pf_frugal-plus runtime))

      IMakeYield
      (make-yield [this runtime]
        (make-query pf_greedy-plus runtime)))))

(defmethod -pattern :rpl [ast]
  (all (-pattern {:tag :rp*, :cat (get ast :cat)})
       (apply count (-pattern (get ast :lvr)))))

(defmethod -pattern :rpm
  [ast]
  (all (-pattern {:tag :rp*, :cat (get ast :cat)})
       (apply count (-pattern (get ast :mvr)))))

(defmethod -pattern :rst [ast]
  (-pattern (get ast :mvr)))

(defmethod -pattern :tail [ast]
  (-pattern (get ast :pattern)))

(defmethod -pattern :set
  [ast]
  (if-some [as (get ast :as)]
    (all (-pattern as)
         (-pattern (assoc ast :as nil)))
    (let [pf_elements (map -pattern (get ast :elements))]
      (if-some [rest (get ast :rest)]
        (set pf_elements (-pattern rest))
        (set pf_elements)))))

(defmethod -pattern :unq
  [ast]
  (let [expr (get ast :expr)]
    (reify
      IMakeQuery
      (make-query [this runtime]
        (if-some [eval (get runtime :eval)]
          (let [pass (get runtime :pass)
                fail (get runtime :fail)]
            (fn unquote-query [target bindings]
              (if (= target (eval expr))
                (pass bindings)
                fail)))
          (throw (ex-info "eval not provided" {:runtime runtime}))))

      IMakeYield
      (make-yield [this runtime]
        (if-some [eval (get runtime :eval)]
          (let [pass (get runtime :pass)
                fail (get runtime :fail)]
            (fn unquote-yield [target bindings]
              (pass (assoc bindings :object (eval expr)))))
          (throw (ex-info "eval not provided" {:runtime runtime})))))))

(defmethod -pattern :uns
  [ast]
  (let [expr (get ast :expr)]
    (reify
      IMakeQuery
      (make-query [this runtime]
        (if-some [eval (get runtime :eval)]
          (let [pass (get runtime :pass)
                fail (get runtime :fail)]
            (fn unquote-query [target bindings]
              (let [x (eval expr)]
                (if (and (coll? x) (= target x))
                  (pass bindings)
                  fail))))
          (throw (ex-info "eval not provided" {:runtime runtime}))))

      IMakeYield
      (make-yield [this runtime]
        (if-some [eval (get runtime :eval)]
          (let [pass (get runtime :pass)
                fail (get runtime :fail)]
            (fn unquote-yield [target bindings]
              (let [x (eval expr)]
                (if (coll? x)
                  (pass (assoc bindings :object x))
                  fail))))
          (throw (ex-info "eval not provided" {:runtime runtime})))))))


(defmethod -pattern :vec
  [ast]
  (let [pf_prt (-pattern (get ast :prt))
        pf_seq (reify
                 IMakeQuery
                 (make-query [this runtime]
                   (let [pq_prt (make-query pf_prt runtime)
                         fail (get runtime :fail)]
                     (fn vector-query [target bindings]
                       (if (vector? target)
                         (pq_prt target bindings)
                         fail))))

                 IMakeYield
                 (make-yield [this runtime]
                   (let [py_prt (make-yield pf_prt runtime)
                         pass (get runtime :pass)
                         fmap (get runtime :fmap)]
                     (fn vector-yield [bindings]
                       (fmap (fn [bindings]
                               (pass (update bindings :object vec)))
                             (py_prt bindings))))))]
    (if-some [as (get ast :as)]
      (all (-pattern as) pf_seq)
      pf_seq)))

(defmethod -pattern :wth
  [ast]
  (let [bindings (get ast :bindings)
        body (get ast :body)]
    (if (some? body)
      (with (sequence (comp (mapcat (juxt :ref :pattern))
                            (map -pattern))
                      bindings)
        (-pattern body))
      anything)))

(defmethod -pattern :meander.match.syntax.epsilon/and
  [ast]
  (reduce all (map -pattern (get ast :arguments))))

(defmethod -pattern :meander.match.syntax.epsilon/cata
  [ast]
  (let [pf_argument (-pattern (get ast :argument))]
    (reify
      IMakeQuery
      (make-query [this runtime]
        (let [fmap (get runtime :fmap)
              scan (get runtime :scan)
              pq_argument (make-query pf_argument runtime)]
          (fn cata-query [target bindings]
            (if-some [cata (get bindings ::cata)]
              (fmap (fn [x]
                      (pq_argument x bindings))
                    (cata target))
              (throw (ex-info "cata not provided" {}))))))

      IMakeYield
      (make-yield [this runtime]
        (let [fmap (get runtime :fmap)
              scan (get runtime :scan)
              py_argument (make-yield pf_argument runtime)]
          (fn cata-query [target bindings]
            (if-some [cata (get bindings ::cata)]
              (fmap (fn [x]
                      (py_argument x bindings))
                    (cata target))
              (throw (ex-info "cata not provided" {})))))))))

(defmethod -pattern :meander.match.syntax.epsilon/let
  [ast]
  (let [pf_pattern (-pattern (get ast :pattern))
        expression (get ast :expression)]
    (reify
      IMakeQuery
      (make-query [this runtime]
        (if-some [eval (get runtime :eval)]
          (let [pq_pattern (make-query pf_pattern runtime)]
            (fn let-query [target bindings]
              (let [x (eval expression)]
                (pq_pattern x bindings))))
          (throw (ex-info "eval not provided" {:runtime runtime}))))

      IMakeYield
      (make-yield [this runtime]
        (if-some [eval (get runtime :eval)]
          (let [pq_pattern (make-query pf_pattern runtime)]
            (fn let-yield [target bindings]
              (let [x (eval expression)]
                (pq_pattern x bindings))))
          (throw (ex-info "eval not provided" {:runtime runtime})))))))

(defmethod -pattern :meander.match.syntax.epsilon/or
  [ast]
  (reduce some (map -pattern (get ast :arguments))))

(defmethod -pattern :meander.match.syntax.epsilon/apply
  [ast]
  (let [function (get ast :function)
        pf_argument (-pattern (get ast :argument))]
    (reify
      IMakeQuery
      (make-query [this runtime]
        (if-some [eval (get runtime :eval)]
          (let [pq_argument (make-query pf_argument runtime)]
            (fn pattern-apply-query [target bindings]
              (let [f (eval function)]
                (pq_argument (f target) bindings))))
          (throw (ex-info "eval not provided" {:runtime runtime}))))

      IMakeYield
      (make-yield [this runtime]
        (if-some [eval (get runtime :eval)]
          (let [py_argument (make-yield pf_argument runtime)
                fmap (get runtime :fmap)]
            (fn pattern-apply-yield [bindings]
              (let [f (eval function)]
                (fmap (fn [bindings]
                        (update bindings :object f))
                      (py_argument bindings)))))
          (throw (ex-info "eval not provided" {:runtime runtime})))))))

(defmethod -pattern :meander.match.syntax.epsilon/guard
  [ast]
  (let [expr (get ast :expr)]
    (reify
      IMakeQuery
      (make-query [this runtime]
        (if-some [eval (get runtime :eval)]
          (let [fail (get runtime :fail)
                pass (get runtime :pass)]
            (fn pattern-guard-query [target bindings]
              (if (eval expr)
                (pass bindings)
                fail)))
          (throw (ex-info "eval not provided" {:runtime runtime}))))

      IMakeYield
      (make-yield [this runtime]
        (if-some [eval (get runtime :eval)]
          (let [fail (get runtime :fail)
                pass (get runtime :pass)]
            (fn pattern-guard-yield [bindings]
              (if (eval expr)
                (pass bindings)
                fail)))
          (throw (ex-info "eval not provided" {:runtime runtime})))))))

(defmethod -pattern :meander.match.syntax.epsilon/not
  [ast]
  (let [pf_argument (-pattern (get ast :argument))]
    (reify
      IMakeQuery
      (make-query [this runtime]
        (let [pq_argument (make-query pf_argument runtime)
              fail (get runtime :fail)
              pass (get runtime :pass)]
          (fn pattern-not-query [target bindings]
            (if (= fail (pq_argument target bindings))
              (pass bindings)
              fail))))

      IMakeYield
      (make-yield [this runtime]
        (let [py_argument (make-yield pf_argument runtime)
              fail (get runtime :fail)
              pass (get runtime :pass)]
          (fn pattern-not-yield [bindings]
            (let [x (py_argument bindings)]
              (if (= x fail)
                (pass bindings)
                fail))))))))

(defmethod -pattern :meander.match.syntax.epsilon/pred
  [ast]
  (let [form (get ast :form)
        arguments (get ast :arguments)
        pf_and (-pattern {:tag :meander.match.syntax.epsilon/and
                          :arguments arguments})]
    (reify
      IMakeQuery
      (make-query [this runtime]
        (if-some [eval (get runtime :eval)]
          (let [pq_and (make-query pf_and runtime)
                fail (get runtime :fail)
                pass (get runtime :pass)]
            (fn pattern-pred-query [target bindings]
              (let [predicate (eval form)]
                (if (predicate target)
                  (pass bindings)
                  fail))))
          (throw (ex-info "eval not provided" {:runtime runtime}))))

      IMakeYield
      (make-yield [this runtime]
        (throw (ex-info "pred pattern does not support yield" {}))))))

(defmethod -pattern :meander.match.syntax.epsilon/rxc
  [ast]
  (let [regex (get ast :regex)
        pf_capture (-pattern (get ast :capture))]
    (reify
      IMakeQuery
      (make-query [this runtime]
        (let [pq_capture (make-query pf_capture runtime)
              fail (get runtime :fail)
              pass (get runtime :pass)]
          (fn pattern-re-a2-query [target bindings]
            (if (string? target)
              (if-some [matches (re-matches regex target)]
                (pq_capture matches bindings)
                fail)
              fail))))

      IMakeYield
      (make-yield [this runtime]
        (throw (ex-info "re pattern does not support yield" {}))))))

(defmethod -pattern :meander.match.syntax.epsilon/rxt
  [ast]
  (let [regex (get ast :regex)]
    (reify
      IMakeQuery
      (make-query [this runtime]
        (let [fail (get runtime :fail)
              pass (get runtime :pass)]
          (fn pattern-re-a2-query [target bindings]
            (if (string? target)
              (pass bindings)
              fail))))

      IMakeYield
      (make-yield [this runtime]
        (throw (ex-info "re pattern does not support yield" {}))))))

(defmethod -pattern :meander.match.syntax.epsilon/subsequence
  [ast]
  (let [cat (get ast :cat)
        n (count cat)
        pf_cat (-pattern cat)]
    (reify
      IMakeQuery
      (make-query [this runtime]
        (let [pq_cat (make-query pf_cat runtime)
              scan (get runtime :scan)]
          (fn [target bindings]
            (scan (fn [partition]
                    (pf_cat partition bindings))
                  (partition n 1 target)))))

      IMakeYield
      (make-yield [this runtime]
        (make-yield pf_cat runtime)))))

(defn pattern [form]
  (let [ast (m.match.syntax/parse form)]
    (-pattern ast)))

(def default-parse-env
  {::m.syntax/expander-registry
   {`meander.interpreter.epsilon/and
    (fn [[_ & args] env]
      `(m.match.syntax/and ~@args))

    `meander.interpreter.epsilon/app
    (fn [[_ & args] env]
      (case (count args)
        (0 1)
        (throw (ex-info "app expects at least two arguments" {}))
        ;; else
        `(m.match.syntax/app ~(first args) (m.match.syntax/and ~@(rest args)))))

    `meander.interpreter.epsilon/cata
    (fn [[_ & args] env]
      `(m.match.syntax/cata ~@args))

    `meander.interpreter.epsilon/guard
    (fn [[_ & args] env]
      `(m.match.syntax/guard ~@args))

    `meander.interpreter.epsilon/let
    (fn [[_ & args] env]
      (let [bindings (nth args 0 nil)]
        (if (and (vector? bindings)
                 (even? (count bindings)))
          (reduce
           (fn [inner [pattern expression]]
             `(r.match.syntax/let ~pattern ~expression ~inner))
           (reverse (partition 2 bindings)))
          (throw (ex-info "The second argument to let must be a vector with an even number of elements" {}))))
      (cons `m.match.syntax/let args))

    `meander.interpreter.epsilon/not
    (fn [[_ & args] env]
      `(m.match.syntax/not ~@args))

    `meander.interpreter.epsilon/or
    (fn [[_ & args] env]
      `(m.match.syntax/or ~@args))

    `meander.interpreter.epsilon/pred
    (fn [[_ & args] env]
      (if (seq args)
        `(m.match.syntax/pred ~(first args) (m.match.syntax/and ~@(rest args)))
        `(m.match.syntax/pred)))

    `meander.interpreter.epsilon/re
    (fn [[_ & args] env]
      `(m.match.syntax/re ~@args))}})

(defn parse-rules
  {:private true}
  [xs]
  (map (fn [[left right]]
         (let [p_left* (if (fn? left)
                         left
                         (-pattern (m.match.syntax/parse left default-parse-env)))]
           [p_left* right]))
       (partition-all 2 xs)))

(defn system
  [args]
  (let [rules* (parse-rules args)]
    (fn [runtime]
      (let [fmap (get runtime :fmap)
            scan (get runtime :scan)
            pass (get runtime :pass)
            rules (map (fn [[pf_left right]]
                         [(make-query pf_left runtime) right])
                       rules*)]
        (fn f
          ([target]
           (f target {}))
          ([target bindings]
           (let [bindings (assoc bindings ::cata f)]
             (scan
              (fn [[left right]]
                (fmap (fn [x]
                        (pass (right (dissoc x ::cata))))
                      (left target bindings)))
              rules))))))))

(defn finder [& clauses]
  (let [s_clauses ((system clauses) find-runtime)
        fail (get find-runtime :fail)]
    (comp (fn [x] (if (= x fail) nil x)) s_clauses)))

(defn searcher [& clauses]
  (let [system* (system clauses)]
    (system* depth-first-search-runtime)))

(defn rule-factory [pf_query pf_yield]
  (fn [runtime]
    (let [pq_query (make-query pf_query runtime)
          py_yield (make-yield pf_yield runtime)
          fmap (get runtime :fmap)
          pass (get runtime :pass)]
      (fn [x bindings]
        (fmap (fn [bindings]
                (fmap (fn [bindings]
                        (pass (get bindings :object)))
                      (py_yield bindings)))
              (pq_query x bindings))))))

(defn rewriter [& clauses]
  (let [rfs (map (fn [[query yield]]
                   (rule-factory
                    (if (satisfies? IMakeQuery query)
                      query
                      (pattern query))
                    (if (satisfies? IMakeYield yield)
                      yield
                      (pattern yield))))
                 (partition 2 clauses))]
    (fn [runtime]
      (let [scan (get runtime :scan)
            rules (map (fn [rf] (rf runtime)) rfs)]
        (fn f [x]
          (scan (fn [rule] (rule x {::cata f})) rules))))))

(comment
  ((finder '{?k ?v} identity) {:a 1 :b 2})
  ;; =>
  {?k :a, ?v 1}

  ((searcher '{?k ?v} identity) {:a 1 :b 2})
  ;; =>
  ({?k :a, ?v 1} {?k :b, ?v 2})

  (let [pf_!xs (memory-variable '!xs)

        ;; cat setup
        ;; =========
        ;; (!xs !xs !xs)
        pf_cat (cat [pf_!xs pf_!xs pf_!xs])
        py_cat (make-yield pf_cat breadth-first-search-runtime)

        ;; greedy-star setup
        ;; =================
        ;; (* !xs !xs !xs)
        pf_greedy-star (greedy-star pf_cat)
        ;; Use the find-runtime to produce a single result
        pq_greedy-star (make-query pf_greedy-star find-runtime)
        py_greedy-star (make-yield pf_greedy-star breadth-first-search-runtime)

        ;; frugal-star setup
        ;; =================
        ;; (*? !xs !xs !xs)
        pf_frugal-star (frugal-star pf_cat)
        py_frugal-star (make-yield pf_frugal-star breadth-first-search-runtime)

        ;; frugal-star setup
        ;; =================
        ;; (+ 2 !xs !xs !xs)
        pf_frugal-plus (frugal-plus pf_cat 2)
        py_frugal-plus (make-yield pf_frugal-plus breadth-first-search-runtime)

        ;; Some bindings.
        bindings (pq_greedy-star (list 1 2 3 4 5 6) {})]
    [bindings
     ;; => {!xs [1 2 3 4 5 6], :object nil}
     (py_cat bindings)
     ;; => ({!xs (4 5 6), :object (1 2 3)})
     (py_greedy-star bindings)
     ;; => ({!xs (), :object (1 2 3 4 5 6)})
     (py_frugal-star bindings)
     ;; => ({!xs [1 2 3 4 5 6], :object ()}
     ;;     {!xs (4 5 6), :object (1 2 3)}
     ;;     {!xs (), :object (1 2 3 4 5 6)})
     (py_frugal-plus bindings)
     ]

    )

  (let [sf (rewriter
            '{?k1 ?v1, ?k2 ?v2}
            '(meander.epsilon/or {?v1 ?k1} {?v2 ?k2}))
        s_find (sf find-runtime)
        s_search (sf depth-first-search-runtime)
        target {:a 1 :b 2 :c 3}]
    [(s_find target)
     (s_search target)])
  ;; => [{1 :a}
  ;;     ({1 :a}
  ;;      {2 :b}
  ;;      {1 :a}
  ;;      {3 :c}
  ;;      {2 :b}
  ;;      {1 :a}
  ;;      {2 :b}
  ;;      {3 :c}
  ;;      {3 :c}
  ;;      {1 :a}
  ;;      {3 :c}
  ;;      {2 :b})]
  )
