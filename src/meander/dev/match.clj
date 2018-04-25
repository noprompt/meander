(ns meander.dev.match
  (:refer-clojure :exclude [compile])
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as s.gen]
            [meander.dev.syntax :as syntax]))

(defn fresh-n
  ([n]
   (fresh-n n "v__"))
  ([n prefix]
   (map
    (fn [_]
      (gensym prefix))
    (range n))))

(defn add-sym [row sym]
  (update row :env (fnil conj #{}) sym))

(defn get-sym [row sym]
  (get (:env row) sym))

(defn swap [v i j]
  (let [v (vec v)]
    (assoc v
           i (nth v j)
           j (nth v i))))

(defn swap-column [rows i j]
  (sequence
   (map
    (fn [row]
      (update row :cols swap i j)))
   rows))

(defmulti tag-score
  identity
  :default ::default-score)

(defmethod tag-score ::default-score [_]
  1)

(defmethod tag-score :any [_]
  0)

(defmethod tag-score :mem [_]
  0)

(defmethod tag-score :var [_]
  0)


(defn nth-column [row index]
  (nth (:cols row) index nil))

(defn first-column [row]
  (nth-column row 0))

(defn rest-columns [row]
  (rest (:cols row)))

(defn group-rows [rows]
  (sort
   (fn [[tag1 _] [tag2 _]]
     (compare (tag-score tag2)
              (tag-score tag1)))
   (group-by
    (fn [row]
      (when-some [col (first-column row)]
        (syntax/tag col)))
    rows)))

(defn drop-column [row]
  (update row :cols rest))


(declare compile)

(defn variables [x]
  (sequence
   (filter
    (fn [x]
      (or (syntax/has-tag? x :var)
          (syntax/has-tag? x :mem))))
   (tree-seq seqable? seq x)))


(defmulti min-length
  #'syntax/tag)


(defmethod min-length :cap [node]
  (min-length (:pat (syntax/data node))))


(defmethod min-length :cat [node]
  (count (syntax/data node)))


(defmethod min-length :init [node]
  0)

(defmethod min-length :part [node]
  (let [data (syntax/data node)]
    (+ (min-length (:left data))
       (min-length (:right data)))))

(defmethod min-length :rest [node]
  0)

(defmethod min-length :seq [node]
  (min-length (syntax/data node)))


(defmethod min-length :seq-end [node]
  0)

(defmethod min-length :vec [node]
  (min-length (syntax/data node)))



(defn columns-dispatch [row]
  (syntax/tag (first-column row)))

(defmulti columns
  #'columns-dispatch)

(defmethod columns :default [row]
  row)

(defmethod columns :cap
  [row]
  (let [node (first-column row)
        {:keys [pat var]} (syntax/data node)
        ;; The var is placed in the first column before the pattern
        ;; since the checks around them, i.e. verifying equality in
        ;; the case of a logic variable, is potentially much cheaper
        ;; than testing the pattern first.
        cols* (list* var pat (rest-columns row))]
    (assoc row :cols cols*)))


(defmethod columns :cat
  [row]
  (let [node (first-column row)
        cols* (concat (syntax/data node) (rest (:cols row)))]
    (assoc row :cols cols*)))


(defmethod columns :part
  [row]
  (let [node (first-column row)
        {:keys [left right]} (syntax/data node)
        left-cols (list left) #_(if (syntax/has-tag? left :cap)
                                  (let [{:keys [pat var]} (syntax/data left)]
                                    (list var pat))
                                  (list left))
        cols* (if (syntax/has-tag? right :seq-end)
                (concat left-cols (rest (:cols row)))
                (concat left-cols (list right) (rest (:cols row))))]
    (assoc row :cols cols*)))


(defmethod columns :seq
  [row]
  (let [node (first-column row)
        ;; TODO: Move to syntax.
        part (update (syntax/data node) 1 assoc :kind :seq)
        cols* (list* part (rest (:cols row)))]
    (assoc row :cols cols*)))

(defmethod columns :vec
  [row]
  (let [node (first-column row)
        ;; TODO: Move to syntax.
        part (update (syntax/data node) 1 assoc :kind :vec)
        cols* (list* part (rest (:cols row)))]
    (assoc row :cols cols*)))


(declare compile)

(defn compile-ctor-clauses-dispatch [tag vars rows default]
  tag)

(defmulti compile-ctor-clauses
  #'compile-ctor-clauses-dispatch)


(defmethod compile-ctor-clauses :any [_tag vars rows default]
  (map
   (fn [row]
     [true
      (compile (rest vars) [(drop-column row)] default)])
   rows))


(defmethod compile-ctor-clauses :cap [_tag vars rows default]
  (sequence
   (map
    (fn [row]
      [true
       (compile (cons (first vars) vars) [(columns row)] default)]))
   rows))


(defmethod compile-ctor-clauses :cat [_tag vars rows default]
  (map
   (fn [[min rows]]
     (let [[var & rest-vars] vars
           nth-forms (map
                      (fn [index]
                        [(gensym (str "nth_" index "__"))
                         `(nth ~var ~index)])
                      (range min))
           nth-vars (map first nth-forms)
           vars* (concat nth-vars rest-vars)
           rows*  (map columns rows)]
       [true
        `(let [~@(mapcat identity nth-forms)]
           ~(compile vars* rows* default))]))
   (group-by
    (comp count syntax/data first-column)
    rows)))


(defmethod compile-ctor-clauses :lit [_tag vars rows default]
  (map
   (fn [[[_ val] rows]]
     `[(= ~(first vars) '~val)
       ~(compile (rest vars)
                 (map drop-column rows)
                 default)])
   (group-by first-column rows)))


(defmethod compile-ctor-clauses :mem [_tag vars rows default]
  (let [[var & vars*] vars]
    (sequence
     (map
      (fn [row]
        (let [sym (syntax/data (first-column row))
              row* (drop-column (add-sym row sym))]
          [true
           `(let ~(if (some? (get-sym row sym))
                    [sym `(conj ~sym ~var)]
                    [sym `[~var]])
              ~(compile vars* [row*] default))])))
     rows)))


(defmethod compile-ctor-clauses :part [_tag vars rows default]
  (map
   (fn [[[kind min tags] rows]]
     (case tags
       [:cap :seq-end]
       [true
        (compile (cons vars) (map columns rows) default)]
       
       [:cat :seq-end]
       (let [var (first vars)]
         `[;; Bounds check.
           ~(case kind
              :vec
              `(== (count ~var) ~min)
              
              ;; Is there a faster way to do this?
              :seq
              `(and (== (count (take ~min ~var))
                        ~min)
                    (not (seq (drop ~min ~var)))))
           ~(compile
             vars
             (map columns rows)
             default)])

       [:cat :rest]
       [true
        (let [[var & rest-vars] vars
              rest-var (gensym "rest__")
              vars* (list* var rest-var rest-vars)]
          `(let [~rest-var ~(case kind
                              :vec
                              `(subvec ~var ~min)

                              :seq
                              `(drop ~min ~var))]
             ~(compile vars* (map columns rows) default)))]

       [:init :cat]
       [true
        (let [[var & rest-vars] vars
              n (gensym "n__")
              init-var (gensym "init__")
              tail-var (gensym "tail__")
              vars* (list* init-var tail-var rest-vars)]
          `(let [~n (max 0 (- (count ~var) ~min))
                 ~init-var ~(case kind
                              :vec
                              `(subvec ~var 0 ~n)

                              :seq
                              `(take ~n ~var))
                 ~tail-var ~(case kind
                              :vec
                              `(subvec ~var ~n)
                              :seq
                              `(drop ~n ~var))]
             ~(compile vars* (map columns rows) default)))]

       [:rest :seq-end]
       [true
        (compile vars (map columns rows) default)]))
   (group-by
    (fn [row]
      (let [node (first-column row)
            {:keys [kind left right]} (syntax/data node)]
        [kind (min-length node) [(syntax/tag left) (syntax/tag right)]]))
    rows)))


(defmethod compile-ctor-clauses :init [_tag vars rows default]
  (let [[var & vars*] vars]
    (sequence
     (map
      (fn [row]
        (let [node (first-column row)
              sym (syntax/data (:var (syntax/data node)))]
          [true
           `(let [~sym ~(if (get-sym row sym)
                          `(into ~sym ~var)
                          `(vec ~var))]
              ~(compile vars* [(drop-column row)] default))])))
     rows)))


(defmethod compile-ctor-clauses :rest [_tag vars rows default]
  (let [[var & vars*] vars]
    (sequence
     (map
      (fn [row]
        (let [node (first-column row)
              sym (syntax/data (:var (syntax/data node)))]
          [true
           `(let [~sym ~(if (get-sym row sym)
                          `(into ~sym ~var)
                          `(vec ~var))]
              ~(compile vars* [(drop-column row)] default))])))
     rows)))


(defmethod compile-ctor-clauses :seq [_tag vars rows default]
  (let [[var & vars*] vars]
    [[`(seq? ~var)
      (compile vars
               (map columns rows)
               default)]]))


(defmethod compile-ctor-clauses :seq-end [_tag vars rows default]
  (let [[var & vars*] vars]
    `[[;; This check is not needed because :part emits an equivalent
       ;; check.
       #_(not (seq ~var))
       true
       ~(compile vars*
                 (map drop-column rows)
                 default)]]))


(defmethod compile-ctor-clauses :var [_tag vars rows default]
  (map
   (fn [row]
     (let [[var & vars*] vars
           [_ sym] (first-column row)
           row* (drop-column (add-sym row sym))
           body (compile vars* [row*] default)]
       (if (some? (get-sym row sym))
         [`(= ~var ~sym) body]
         [true
          `(let [~sym ~var]
             ~body)])))
   rows))

(defmethod compile-ctor-clauses :vec [_tag vars rows default]
  (let [[var & vars*] vars]
    `[[(vector? ~var)
       ~(compile vars (sequence (map columns) rows) default)]]))


(defmethod compile-ctor-clauses :default [_tag vars rows default]
  [[true
    (if (seq vars)
      [:error vars rows]
      (:rhs (first rows)))]])


(def backtrack
  (Exception. "non exhaustive pattern match"))


(defn compile [vars rows default]
  (reduce
   (fn [inner [test then]]
     (let [body-form (if (= true test)
                       then
                       `(if ~test
                          ~then
                          (throw backtrack)))]
       (if (= inner default)
         body-form
         `(try
            ~body-form
            (catch Exception exception#
              (if (identical? exception# backtrack)
                ~inner
                (throw exception#)))))))
   default
   (reverse
    (mapcat
     (fn [[tag rows]]
       (compile-ctor-clauses tag vars rows default))
     (group-rows rows)))))



(defmacro match [x & clauses]
  (let [var (gensym "v__")]
    `(let [~var ~x]
       ~(compile [var]
                 (sequence
                  (map
                   (fn [[pat act]]
                     {:cols [(syntax/parse pat)]
                      :rhs act}))
                  (partition 2 clauses))
                 `(throw backtrack)))))
