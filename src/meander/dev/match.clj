(ns meander.dev.match
  (:refer-clojure :exclude [compile])
  (:require [clojure.walk :as walk]
            [clojure.set :as set]
            [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as s.gen]
            [meander.dev.syntax :as syntax])
  (:import [java.util.concurrent.atomic AtomicInteger]))


(defonce
  ^{:tag AtomicInteger
    :private true}
  gensym-id
  (AtomicInteger.))


(defn next-gensym-id
  {:private true}
  []
  (.incrementAndGet gensym-id))


(defmacro gensym*
  "Custom version of gensym which prefixes the symbol with the line
  number. This is useful for debugging macro expansions."
  {:private true}
  ([]
   `(symbol (format "L%d__G%d"
                    ~(:line (meta &form))
                    (next-gensym-id))))
  ([prefix]
   `(symbol (format "L%d__%s%d"
                    ~(:line (meta &form))
                    ~prefix
                    (next-gensym-id)))))


;; ---------------------------------------------------------------------
;; Pattern matrix


(defn add-sym
  "Add the symbol sym to the environment in row."
  [row sym]
  (update row :env (fnil conj #{}) sym))


(defn get-sym
  "Get the symbol sym from the environment in row."
  [row sym]
  (get (:env row) sym))


(defn row-width
  [row]
  (count (:cols row)))


(defn swap
  "Swap elements at positions i and j in the vector v."
  [v i j]
  (let [v (vec v)]
    (assoc v i (nth v j) j (nth v i))))


(defn swap-column
  "Swaps column i with column j in the matrix."
  [matrix i j]
  (sequence
   (map
    (fn [row]
      (update row :cols swap i j)))
   matrix))


(defn nth-column
  "Get the nth column in row."
  ([row index]
   (nth (:cols row) index))
  ([row index not-found]
   (nth (:cols row) index not-found)))


(defn first-column
  [row]
  (nth-column row 0 nil))


(defn rest-columns
  [row]
  (rest (:cols row)))


(defn drop-column
  [row]
  (update row :cols rest))


(defmulti tag-score
  identity
  :default ::default-score)


(defmethod tag-score ::default-score [_]
  1)


(defn node-score [node]
  (tag-score (syntax/tag node)))


(defn score-column
  [matrix i]
  (transduce 
   (map
    (fn [row]
      (node-score (nth (:cols row) i))))
   +
   0
   matrix))


(defn variables
  "Return all variable nodes in x."
  [x]
  (into #{}
        (keep
         (fn [x]
           (cond
             (and (or (syntax/has-tag? x :var)
                      (syntax/has-tag? x :mem))
                  (simple-symbol? (syntax/data x)))
             x

             ;; Technically this is not needed. The particular use of
             ;; tree-seq will cause [:mem !name] to be located
             ;; (as a map entry), however, relying on this feels
             ;; dishonest and, so, it's explicitly stated here.
             (or (syntax/has-tag? x :rest)
                 (syntax/has-tag? x :init))
             (if-some [mem-var (find (syntax/data x) :mem)]
               mem-var))))
        (tree-seq seqable? seq x)))


(def not-ground-tags
  #{:and
    :any
    :drop
    :init
    :mem
    :prd
    :rest
    :rep
    :repk
    :var})

;; multimethod?
(defn ground? [x]
  (not (some
        (fn [y]
          (and (syntax/node? y)
               (contains? not-ground-tags (syntax/tag y))))
        (tree-seq seqable? seq x))))


(defn group-rows [rows]
  (sort
   (fn [[tag1 _] [tag2 _]]
     (compare (tag-score tag2)
              (tag-score tag1)))
   (group-by
    (fn [row]
      (when-some [column (first-column row)]
        (let [tag (syntax/tag column)]
          (if (ground? column)
            ::ground
            tag))))
    rows)))


(declare compile)


(defn min-min-length [rows]
  (transduce
   (map
    (fn [row]
      (syntax/min-length (first-column row))))
   min
   Float/POSITIVE_INFINITY
   rows))


(defn next-columns-dispatch
  {:private true}
  [row]
  (syntax/tag (first-column row)))


(defmulti next-columns
  {:arglists '([row])}
  #'next-columns-dispatch)


(defmethod next-columns :default [row]
  row)


(declare compile)


(defn compile-ctor-clauses-dispatch [tag vars rows default]
  tag)


(defmulti compile-ctor-clauses
  {:arglists '([targ vars rows default])}
  #'compile-ctor-clauses-dispatch)


;; ---------------------------------------------------------------------
;; And

(defmethod next-columns :and [row]
  (let [pats (:pats (syntax/data (first-column row)))]
    (assoc row :cols (concat pats (rest-columns row)))))


(defmethod compile-ctor-clauses :and [_tag vars rows default]
  (sequence
   (map
    (fn [row]
      (let [pats (:pats (syntax/data (first-column row)))
            n (count pats)]
        [true
         (if (zero? n)
           (compile (rest vars) [(drop-column row)] default)
           (compile (concat (repeat n (first vars))
                            (rest vars))
                    [(next-columns row)]
                    default))])))
   rows))


;; ---------------------------------------------------------------------
;; Any


(defmethod tag-score :any [_]
  -1)


(defmethod compile-ctor-clauses :any [_tag vars rows default]
  (sequence
   (map
    (fn [row]
      [true
       (compile (rest vars) [(drop-column row)] default)]))
   rows))


;; --------------------------------------------------------------------
;; Cap


(defmethod next-columns :cap
  [row]
  (let [node (first-column row)
        {:keys [pat var]} (syntax/data node)
        ;; The var is placed in the first column before the pattern
        ;; since the checks around them, i.e. verifying equality in
        ;; the case of a logic variable, is potentially much cheaper
        ;; than testing the pattern first.
        cols* (list* var pat (rest-columns row))]
    (assoc row :cols cols*)))


(defmethod compile-ctor-clauses :cap [_tag vars rows default]
  [[true
    (compile (cons (first vars) vars)
             (map next-columns rows)
             default)]])


;; --------------------------------------------------------------------
;; Cat, VCat


(defn compile-cat-clauses [tag vars rows default]
  (map
   (fn [[n rows]]
     (let [target (first vars)
           nth-forms (map
                      (fn [index]
                        [(gensym* (str "nth_" index "__"))
                         `(nth ~target ~index)])
                      (range n))
           nth-vars (map first nth-forms)
           vars* (concat nth-vars (rest vars))
           rows* (map
                  (fn [row]
                    (assoc row
                           :cols (concat
                                  (syntax/data (first-column row))
                                  (rest-columns row))))
                  rows)]
       (case tag
         :cat
         [`(== ~n (count (take ~n ~target)))
          `(let [~@(mapcat identity nth-forms)]
             ~(compile vars* rows* default))]

         :vcat
         [`(== ~n (count (subvec ~target 0 ~n)))
          `(let [~@(mapcat identity nth-forms)]
             ~(compile vars* rows* default))])))
   (group-by
    (comp count syntax/data first-column)
    rows)))


(defmethod compile-ctor-clauses :cat [tag vars rows default]
  (compile-cat-clauses tag vars rows default))


(defmethod compile-ctor-clauses :vcat [tag vars rows default]
  (compile-cat-clauses tag vars rows default))




;; --------------------------------------------------------------------
;; Drop


(defmethod compile-ctor-clauses :drop [_tag vars rows default]
  [[true
    (compile (rest vars) (map drop-column rows) default)]])


;; ---------------------------------------------------------------------
;; Ground


(defmethod tag-score ::ground [_]
  Integer/MAX_VALUE)


(defn compile-ground [x]
  [x]
  (cond
    (symbol? x)
    `(quote ~x)

    (seq? x)
    (if (= (first x) 'quote)
      x
      (if (= (first x) `list)
        (cons (first x) (map compile-ground (rest x)))
        (if (seq x) 
          (cons `list (map compile-ground x))
          ())))

    (map? x)
    (into {}
          (map
           (fn [[k v]]
             [(compile-ground k) (compile-ground v)]))
          x)

    (coll? x)
    (into (empty x) (map compile-ground) x)

    :else
    x))


(defmethod compile-ctor-clauses ::ground [_tag vars rows default]
  (let [[target & vars*] vars]
    (map
     (fn [[node rows]]
       [(case (syntax/tag node)
          :entry
          (let [{:keys [key-pat val-pat]} (syntax/data node)]
            `(if-some [[key# val#] (find ~target ~(compile-ground (syntax/unparse key-pat)))]
               (= val# ~(compile-ground (syntax/unparse val-pat)))
               false))

          ::map-no-check
          (let [entries (syntax/data node)]
            (case (count entries)
              0
              true

              1
              (let [[key-pat val-pat] (first entries)]
                `(if-some [[key# val#] (find ~target ~(compile-ground (syntax/unparse key-pat)))]
                   (= val# ~(compile-ground (syntax/unparse val-pat)))
                   false))

              ;; else
              `(and ~@(map
                       (fn [[key-pat val-pat]]
                         `(if-some [[key# val#] (find ~target ~(compile-ground (syntax/unparse key-pat)))]
                            (= val# ~(compile-ground (syntax/unparse val-pat)))
                            false))
                       (syntax/data node)))))

          ;; else
          `(= ~target ~(compile-ground (syntax/unparse node))))
        (compile vars* (map drop-column rows) default)])
     (group-by
      first-column
      rows))))



;; --------------------------------------------------------------------
;; Init


(defmethod compile-ctor-clauses :init [_tag vars rows default]
  (let [[target & vars*] vars]
    (map
     (fn [row]
       (let [node (first-column row)
             sym (:mem (syntax/data node))]
         [true
          `(let [~sym ~(if (get-sym row sym)
                         `(into ~sym ~target)
                         `(vec ~target))]
             ~(compile vars* [(add-sym (drop-column row) sym)] default))]))
     rows)))


;; --------------------------------------------------------------------
;; Lit


(defmethod compile-ctor-clauses :lit [_tag vars rows default]
  (map
   (fn [[[_ val] rows]]
     `[(= ~(first vars) ~(compile-ground val))
       ~(compile (rest vars)
                 (map drop-column rows)
                 default)])
   (group-by first-column rows)))


;; --------------------------------------------------------------------
;; Map


(defmethod syntax/min-length ::map-no-check [_]
  1)


(defn key-frequencies
  {:private true}
  [map-nodes]
  (frequencies
   (sequence
    (comp (map syntax/data)
          (mapcat keys))
    map-nodes)))


(defn rank-keys
  {:private true}
  [map-nodes]
  (sort-by second (key-frequencies map-nodes)))


(defmethod compile-ctor-clauses :entry
  [_tag vars rows default]
  (let [[target & rest-vars] vars]
    (map
     (fn [[key-pat rows]]
       (let [rows* (map
                    (fn [row]
                      (assoc row
                             :cols (cons
                                    (:val-pat (syntax/data (first-column row)))
                                    (rest-columns row))))
                    rows)
             val-sym (gensym* "val__")
             vars* (cons val-sym rest-vars)
             key-form (compile-ground (syntax/unparse key-pat))]
         [`(contains? ~target ~key-form)
          `(let [~val-sym (get ~target ~key-form)]
             ~(compile vars* rows* default))]))
     (group-by
      (comp :key-pat syntax/data first-column)
      rows))))




(defn next-map-rows
  {:private true}
  [map-rows]
  (let [map-nodes (map first-column map-rows)
        [key-pat] (first (rank-keys map-nodes))]
    (reduce
     (fn [rows* map-row]
       (let [data (syntax/data (first-column map-row))]
         (conj rows*
               (assoc map-row
                      :cols (if-some [[_ val-pat] (find data key-pat)]
                              (let [data* (dissoc data key-pat)]
                                (list*
                                 [:entry {:key-pat key-pat
                                          :val-pat val-pat}]
                                 (if (= data* {})
                                   [:any '_]
                                   [::map-no-check data*])
                                 (rest-columns map-row)))
                              (list*
                               [:any '_]
                               (if (= data {})
                                 [:any '_]
                                 [::map-no-check data]) 
                               (rest-columns map-row)))))))
     []
     (sort-by
      (comp count syntax/data first-column)
      map-rows))))


(defmethod compile-ctor-clauses ::map-no-check [_tag vars rows default]
  (let [target (first vars)]
    [[true
      (compile (cons target vars) (next-map-rows rows) default)]]))


(defmethod compile-ctor-clauses :map [_tag vars rows default]
  (let [target (first vars)]
    [[`(map? ~target)
      (compile (cons target vars) (next-map-rows rows) default)]]))


;; --------------------------------------------------------------------
;; Memvar


(defmethod tag-score :mem [_]
  0)


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


;; --------------------------------------------------------------------
;; Partition


(defmethod compile-ctor-clauses :part [_tag vars rows default]
  (let [target (first vars)]
    (map
     (fn [[left-tag rows]] 
       (let [n (min-min-length rows)]
         [true
          (case left-tag
            :cat
            (let [take-target (gensym* "take__")
                  drop-target (gensym* "drop__")]
              `(let [~take-target (take ~n ~target)
                     ~drop-target (drop ~n ~target)]
                 ~(compile (list* take-target drop-target (rest vars))
                           (map
                            (fn [row]
                              (let [part-data (syntax/data (first-column row))
                                    left (:left part-data) 
                                    items (syntax/data left)]
                                (assoc row :cols (if (seq items)
                                                   (let [[left-a left-b] (split-at n items)]
                                                     (list* [:cat left-a]
                                                            (if (seq left-b) 
                                                              [:part (assoc part-data :left [:cat left-b])]
                                                              (:right part-data))
                                                            (rest-columns row)))
                                                   (list* [:any '_]
                                                          (:right part-data)
                                                          (rest-columns row))))))
                            rows)
                           default)))

            :drop
            (let [drop-target (gensym* "drop__")]
              `(let [~drop-target (drop (max 0 (- (count ~target) ~n)) ~target)]
                 ~(compile (list* drop-target (rest vars))
                           (map
                            (fn [row]
                              (assoc row
                                     :cols
                                     (cons (:right (syntax/data (first-column row)))
                                           (rest-columns row))))
                            rows)
                           default)))

            :init
            (let [m (gensym* "m__")
                  take-target (gensym* "take__")
                  drop-target (gensym* "drop__")]
              `(let [~m (max 0 (- (count ~target) ~n))
                     ~take-target (take ~m ~target)
                     ~drop-target (drop ~m ~target)]
                 ~(compile (list* take-target drop-target (rest vars))
                           (map
                            (fn [row]
                              (assoc row
                                     :cols
                                     (concat
                                      ((juxt :left :right) (syntax/data (first-column row)))
                                      (rest-columns row))))
                            rows)
                           default)))

            :rep
            (if (== n 0)
              (compile (cons target vars)
                       (map
                        (fn [row]
                          (assoc row
                                 :cols
                                 (concat
                                  ((juxt :left :right) (syntax/data (first-column row)))
                                  (rest-columns row))))
                        rows)
                       default)
              (let [m (gensym* "m__")
                    take-target (gensym* "take__")
                    drop-target (gensym* "drop__")]
                `(let [~m (max 0 (- (count ~target) ~n))
                       ~take-target (take ~m ~target)
                       ~drop-target (drop ~m ~target)]
                   ~(compile (list* take-target drop-target (rest vars))
                             (map
                              (fn [row]
                                (assoc row
                                       :cols
                                       (concat
                                        ((juxt :left :right) (syntax/data (first-column row)))
                                        (rest-columns row))))
                              rows)
                             default))))

            :rest
            (compile vars
                     (map
                      (fn [row]
                        (assoc row
                               :cols (list* (:left (syntax/data (first-column row)))
                                            (rest-columns row))))
                      rows)
                     default))]))
     (group-by
      (comp syntax/left-tag first-column)
      rows))))

;; --------------------------------------------------------------------
;; Pred


(defmethod next-columns :prd [row]
  (let [node (first-column row)
        node* [:and {:pats (:pats (syntax/data node))}]]
    (assoc row :cols (cons node* (rest-columns row)))))


(defmethod compile-ctor-clauses :prd [_tag vars rows default]
  (sequence
   (map
    (fn do-pred-and-rows [[pred rows]]
      [`(~pred ~(first vars))
       (compile vars (sequence (map next-columns) rows) default)]))
   (group-by
    (comp :pred syntax/data first-column)
    rows)))


;; --------------------------------------------------------------------
;; Quote


(defmethod compile-ctor-clauses :quo [_tag vars rows default]
  (sequence
   (map
    (fn [row]
      (let [val (syntax/data (first-column row))]
        ;; No need to quote the value.
        [`(= ~val ~(first vars))
         (compile (rest vars) [(drop-column row)] default)])))
   rows))


;; --------------------------------------------------------------------
;; Rep

(comment
  ":rep nodes have the following structures."
  [:rep {:init [:cat [,,,]]
         :sym ...}]
 [:rep {:init [:cap {:pat [:cat [,,,]] ,,,}]
         :sym ...}]
  "The :init val will either be :cap or :cat.")


(defmethod tag-score :rep [_]
  1)


(defmethod compile-ctor-clauses :rep [_tag vars rows default]
  (let [target (first vars)]
    (map
     (fn [row]
       (let [pat (:init (syntax/data (first-column row)))
             n (if (= (syntax/tag pat) :cat)
                 (count (syntax/data pat))
                 (count (syntax/data (:pat (syntax/data pat)))))
             pat-vars (variables pat)
             let-bindings (into []
                                (mapcat
                                 (fn [[kind sym]]
                                   (case kind
                                     :mem
                                     (if (get-sym row sym)
                                       [sym sym]
                                       [sym []])

                                     :var
                                     (if (get-sym row sym)
                                       []
                                       [sym ::unbound]))))
                                pat-vars)
             let-env (:env (reduce add-sym row (filter syntax/mem-symbol? let-bindings)))
             let-else (compile (rest vars) [(drop-column row)] default)
             let-syms (take-nth 2 let-bindings)
             loop-name (gensym* "loop__")
             loop-bindings (into []
                                 (comp
                                  (filter syntax/mem-symbol?)
                                  (mapcat (juxt identity identity)))
                                 let-syms)
             loop-syms (vec (cons target (take-nth 2 loop-bindings)))
             loop-env (:env (reduce add-sym row let-syms))
             slice (gensym* "slice__")
             loop-else `(if (not (seq ~target))
                          ~(compile (rest vars)
                                    [(assoc (drop-column row) :env loop-env)]
                                    default)
                          ~default)]
         [true
          `(let [~@let-bindings
                 ~slice (take ~n ~target)]
             ~(compile [slice]
                       [{:cols [pat]
                         :env let-env
                         :rhs
                         `((fn ~loop-name ~loop-syms
                             ~(compile (take 1 vars)
                                       [{:cols [pat]
                                         :env loop-env
                                         :rhs
                                         `(let [~target (drop ~n ~target)]
                                            (~loop-name ~@loop-syms))}]
                                       loop-else))
                           (drop ~n ~target)
                           ~@(rest loop-syms))}]
                       let-else))]))
     rows)))


;; --------------------------------------------------------------------
;; Rest


(defmethod compile-ctor-clauses :rest [_tag vars rows default]
  (let [[target & vars*] vars]
    (map
     (fn [row]
       (let [node (first-column row)
             sym (:mem (syntax/data node))]
         [true
          `(let [~sym ~(if (get-sym row sym)
                         `(into ~sym ~target)
                         `(vec ~target))]
             ~(compile vars* [(add-sym (drop-column row) sym)] default))]))
     rows)))


;; --------------------------------------------------------------------
;; Seq


(defmethod next-columns :seq
  [row]
  (let [node (first-column row)
        ;; TODO: Move to syntax.
        part (update (syntax/data node) 1 assoc :kind :seq)
        cols* (list* part (rest (:cols row)))]
    (assoc row :cols cols*)))

(defmethod compile-ctor-clauses :seq [_tag vars rows default]
  (let [[var & vars*] vars]
    [[`(seq? ~var)
      (compile vars
               (map next-columns rows)
               default)]]))


;; --------------------------------------------------------------------
;; SeqEnd


(defmethod compile-ctor-clauses :seq-end [_tag vars rows default]
  (let [[var & vars*] vars]
    `[[(not (seq ~var))
       ~(compile vars*
                 (map drop-column rows)
                 default)]]))


;; --------------------------------------------------------------------
;; Var

(defmethod tag-score :var [_]
  1)



(defmethod compile-ctor-clauses :var [_tag vars rows default]
  (let [target (first vars)]
    (let [{:keys [bound unbound]}
          (group-by
           (fn [row]
             (let [sym (syntax/data (first-column row))]
               (if (get-sym row sym)
                 :bound
                 :unbound)))
           rows)]
      (cond-> []
        bound
        (into (map
               (fn [row]
                 (let [[_ sym] (first-column row)]
                   [`(= ~target ~sym)
                    (compile (rest vars) [(drop-column row)] default)]))
               bound))
        
        unbound
        (conj [true
               (let [rows* (map
                            (fn [row]
                              (let [[_ sym] (first-column row)]
                                (drop-column (add-sym row sym))))
                            unbound)
                     body (compile (rest vars) rows* default)]
                 `(let [~@(mapcat
                           (juxt (comp syntax/data first-column)
                                 (constantly target))
                           rows)]
                    ~body))])))))


;; --------------------------------------------------------------------
;; Vector


(defmethod next-columns :vec
  [row]
  (assoc row
         :cols (cons (syntax/data (first-column row))
                     (rest (:cols row)))))



(defmethod compile-ctor-clauses :vec [_tag vars rows default]
  (let [[var & vars*] vars]
    `[[(vector? ~var)
       ~(compile vars (sequence (map next-columns) rows) default)]]))


;; --------------------------------------------------------------------
;; VPartition


(defmethod compile-ctor-clauses :vpart [_tag vars rows default]
  (let [target (first vars)]
    (map
     (fn [[left-tag rows]]
       (let [n (min-min-length rows)]
         [true
          (case left-tag
            :cap
            (compile (cons (first vars) vars)
                     (map
                      (fn [row]
                        (let [part-data (syntax/data (first-column row))
                              {:keys [pat var]} (syntax/data (:left part-data))
                              right (:right part-data)]
                          (assoc row :cols (list* var pat right (rest-columns row)))))
                      rows)
                     default)

            :drop
            (let [drop-vec (gensym* "drop_vec__")]
              `(let [~drop-vec (subvec ~target (max 0 (- (count ~target) ~n)))]
                 ~(compile (cons drop-vec (rest vars))
                           (map
                            (fn [row]
                              (let [part-data (syntax/data (first-column row))
                                    right (:right part-data)]
                                (assoc row :cols (cons right (rest-columns row)))))
                            rows)
                           default)))

            :init
            (let [m (gensym* "m__")
                  left-vec (gensym* "left_vec__")
                  right-vec (gensym* "right_vec__")]
              `(let [~m (max 0 (- (count ~target) ~n))
                     ~left-vec (subvec ~target 0 ~m)
                     ~right-vec (subvec ~target ~m)]
                 ~(compile (list* left-vec right-vec (rest vars))
                           (map
                            (fn [row]
                              (let [part-data (syntax/data (first-column row))
                                    left (:left part-data)
                                    right (:right part-data)]
                                (assoc row :cols (list* left right (rest-columns row)))))
                            rows)
                           default)))

            :rep
            (if (== n 0)
              (compile (cons target vars)
                       (map
                        (fn [row]
                          (assoc row
                                 :cols
                                 (concat
                                  ((juxt :left :right) (syntax/data (first-column row)))
                                  (rest-columns row))))
                        rows)
                       default)
              (let [m (gensym* "m__")
                    take-target (gensym* "left_vec__")
                    drop-target (gensym* "right_vec__")]
                `(let [~m (max 0 (- (count ~target) ~n))
                       ~take-target (subvec ~target 0 ~m)
                       ~drop-target (subvec ~target ~m)]
                   ~(compile (list* take-target drop-target (rest vars))
                             (map
                              (fn [row]
                                (assoc row
                                       :cols
                                       (concat
                                        ((juxt :left :right) (syntax/data (first-column row)))
                                        (rest-columns row))))
                              rows)
                             default))))

            :rest
            (compile vars
                     (map
                      (fn [row]
                        (let [part-data (syntax/data (first-column row))
                              left (:left part-data)]
                          (assoc row :cols (cons left (rest-columns row)))))
                      rows)
                     default)

            :vcat
            (let [take-target (gensym* "left_vec__")
                  drop-target (gensym* "right_vec__")
                  m (gensym* "m__")]
              `(let [~m (min (count ~target) ~n)
                     ~take-target (subvec ~target 0 ~m)
                     ~drop-target (subvec ~target ~m)]
                 ~(compile (list* take-target drop-target (rest vars))
                           (map
                            (fn [row]
                              (let [part-data (syntax/data (first-column row))
                                    left (:left part-data) 
                                    items (syntax/data left)]
                                (assoc row :cols (if (seq items)
                                                   (let [[left-a left-b] (split-at n items)]
                                                     (list* [:vcat left-a]
                                                            (if (seq left-b) 
                                                              [:vpart (assoc part-data :left [:vcat left-b])]
                                                              (:right part-data))
                                                            (rest-columns row)))
                                                   (list* [:any '_]
                                                          (:right part-data)
                                                          (rest-columns row))))))
                            rows)
                           default))))]))
     (group-by
      (comp syntax/tag :left syntax/data first-column)
      rows))))


;; --------------------------------------------------------------------
;; Fail

(defmethod compile-ctor-clauses :default [_tag vars rows default]
  [[true
    (cond
      (seq vars)
      [:error vars rows]

      (some (comp seq :cols) rows)
      [:error vars rows]

      :else
      (:rhs (first rows)))]])


;; TODO: It'd be nice to move away from the try/catch style.
(def backtrack
  (Exception. "non exhaustive pattern match"))


(def throw-form 
  `(throw backtrack))


(defn try-form [expr catch]
  `(try
     ~expr
     (catch ~'Exception exception#
       (if (identical? exception# backtrack)
         ~catch
         (throw exception#)))))


(defn prioritize-matrix [[vars rows]]
  (let [idxs (into []
                   (map first)
                   (sort
                    (fn [[_ score-1] [_ score-2]]
                      (< score-2 score-1))
                    (sequence
                     (map
                      (fn [i]
                        [i (score-column rows i)]))
                     (range (count vars)))))
        vars* (into []
                    (map
                     (fn [idx]
                       (nth vars idx)))
                    idxs)
        rows* (into []
                    (map
                     (fn [row]
                       (let [cols (:cols row)]
                         (assoc row :cols (mapv
                                           (fn [idx]
                                             (nth cols idx))
                                           idxs)))))
                    rows)]
    [vars* rows*]))


(defn compile
  [vars rows default]
  (let [[vars rows] (prioritize-matrix [vars rows])
        {preds false, no-preds true}
        (group-by (comp true? first)
                  (mapcat
                   (fn [[tag rows]]
                     (compile-ctor-clauses tag vars rows default))
                   (group-rows rows)))

        no-pred-body (reduce
                      (fn [next-choice [_ body-form]]
                        (if (= next-choice default)
                          body-form
                          (try-form body-form next-choice)))
                      default
                      no-preds)

        pred-body (reduce
                   (fn [else [test then]]
                     (if (and (seq? then)
                              (= (first then)
                                 'if)
                              (= no-pred-body (nth then 3)))
                       (let [then-pred (second then)
                             then-preds (if (and (seq? then-pred)
                                                 (= (first then-pred)
                                                    `and))
                                          (rest then-pred)
                                          (list then-pred))]
                         `(if (and ~test ~@then-preds)
                            ~(nth then 2)
                            ~else))
                       `(if ~test
                          ~then
                          ~else)))
                   no-pred-body
                   (reverse preds))]
    (if (seq preds)
      (if (seq no-preds)
        (try-form pred-body no-pred-body)
        pred-body)
      no-pred-body)))


;; ---------------------------------------------------------------------
;; Match

(s/def ::match-clauses
  (s/* (s/cat
        :pat (s/conformer
              (fn [pat]
                (syntax/parse pat)))
        :rhs any?)))

(s/def ::match-args
  (s/cat
   :target any?
   :clauses ::match-clauses))


(defn parse-match-args
  {:private [true]}
  [match-args]
  (s/conform ::match-args match-args))

(s/fdef match
  :args ::match-args
  :ret any?)

(defmacro match
  {:arglists '([target & pattern action ...])}
  [& match-args]
  (let [{:keys [target clauses]} (parse-match-args match-args)
        final-clause (some
                      (fn [{:keys [pat rhs]}]
                        (when (= pat [:any '_])
                          rhs))
                      clauses)
        clauses* (if final-clause
                   (remove (comp #{[:any '_]} :pat) clauses)
                   clauses)
        target-sym (gensym* "target__")
        vars [target-sym]
        rows (sequence
              (map
               (fn [{:keys [pat rhs]}]
                 {:cols [pat]
                  :env #{}
                  :rhs rhs}))
              clauses*)
        form `(let [~target-sym ~target]
                ~(compile vars rows `(throw backtrack)))]
    (if final-clause
      (try-form form final-clause)
      `(try
         ~form
         (catch ~'Exception e#
           (if (identical? e# backtrack)
             (throw (Exception. "non exhaustive pattern match"))
             (throw e#)))))))
