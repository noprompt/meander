(ns meander.dev.match
  (:refer-clojure :exclude [compile])
  (:require [clojure.set :as set]
            [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as s.gen]
            [meander.dev.syntax :as syntax]))


(defn add-sym [row sym]
  (update row :env (fnil conj #{}) sym))


(defn get-sym [row sym]
  (get (:env row) sym))


(defn swap [v i j]
  (let [v (vec v)]
    (assoc v i (nth v j) j (nth v i))))


;; ---------------------------------------------------------------------
;; Pattern matrix


(defn row-width
  [row]
  (count (:cols row)))


(defn swap-column
  "Swaps column i with column j in the matrix."
  [matrix i j]
  (sequence
   (map
    (fn [row]
      (update row :cols swap i j)))
   matrix))


(defn score-column
  [matrix i]
  (transduce 
   (map
    (fn [row]
      (node-score (nth (:cols row) i))))
   +
   0
   matrix))


(defn nth-column
  ([row index]
   (nth (:cols row) index))
  ([row index not-found]
   (nth (:cols row) index not-found)))


(defn first-column [row]
  (nth-column row 0 nil))


(defn rest-columns [row]
  (rest (:cols row)))


(defn drop-column [row]
  (update row :cols rest))


(defmulti tag-score
  identity
  :default ::default-score)


(defmethod tag-score ::default-score [_]
  1)

(defn node-score [node]
  (tag-score (syntax/tag node)))


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

(declare compile)


(defn variables
  "Return all variable nodes in x."
  [x]
  (into #{}
        (filter
         (fn [x]
           (and (or (syntax/has-tag? x :var)
                    (syntax/has-tag? x :mem)
                    (syntax/has-tag? x :any)) 
                (simple-symbol? (syntax/data x)))))
        (tree-seq seqable? seq x)))


(defn ground? [x]
  (empty? (variables x)))


(defmulti min-length
  #'syntax/tag)


(defn has-min-length?
  [node]
  (some? (get-method min-length (syntax/tag node))))


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
  0)


(defmethod compile-ctor-clauses :any [_tag vars rows default]
  (sequence
   (map
    (fn [row]
      [true
       (compile (rest vars) [(drop-column row)] default)]))
   rows))


;; --------------------------------------------------------------------
;; Cap


(defmethod min-length :cap [node]
  (min-length (:pat (syntax/data node))))


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
  (sequence
   (map
    (fn [row]
      [true
       (compile (cons (first vars) vars) [(next-columns row)] default)]))
   rows))


;; --------------------------------------------------------------------
;; Cat


(defmethod min-length :cat [node]
  (count (syntax/data node)))


(defmethod next-columns :cat
  [row]
  (let [node (first-column row)
        cols* (concat (syntax/data node) (rest (:cols row)))]
    (assoc row :cols cols*)))


(defmethod compile-ctor-clauses :cat [_tag vars rows default]
  (mapcat
   (fn [[min rows]]
     (let [{ground true, not-ground false} (group-by ground? rows)]
       (concat
        (when (seq ground)
          (let [[var & vars*] vars]
            (sequence
             (map
              (fn [row]
                [`(= ~var '(~@(syntax/unparse (first-column row))))
                 (compile vars* [(drop-column row)] default)]))
             ground)))
        (when (seq not-ground)
          (let [[var & rest-vars] vars
                nth-forms (map
                           (fn [index]
                             [(gensym (str "nth_" index "__"))
                              `(nth ~var ~index)])
                           (range min))
                nth-vars (map first nth-forms)
                vars* (concat nth-vars rest-vars)
                [vars* rows*] (rotate-cat-columns vars* rows)]
            (list
             [true
              `(let [~@(mapcat identity nth-forms)]
                 ~(compile vars* rows* default))]))))))
   (group-by
    (comp count syntax/data first-column)
    rows)))


;; --------------------------------------------------------------------
;; Drop


(defmethod min-length :drop [node]
  0)


(defmethod next-columns :drop [row]
  (drop-column row))


(defmethod compile-ctor-clauses :drop [_tag vars rows default]
  (sequence
   (map
    (fn [row]
      [true
       (compile (rest vars) [(next-columns row)] default)]))
   rows))



;; --------------------------------------------------------------------
;; Init


(defmethod min-length :init [node]
  0)


(defmethod compile-ctor-clauses :init [_tag vars rows default]
  (let [[var & vars*] vars]
    (sequence
     (map
      (fn [row]
        (let [node (first-column row)
              sym (:mem (syntax/data node))]
          [true
           `(let [~sym ~(if (get-sym row sym)
                          `(into ~sym ~var)
                          `(vec ~var))]
              ~(compile vars* [(drop-column row)] default))])))
     rows)))


;; --------------------------------------------------------------------
;; Lit


(defmethod compile-ctor-clauses :lit [_tag vars rows default]
  (map
   (fn [[[_ val] rows]]
     `[(= ~(first vars) '~val)
       ~(compile (rest vars)
                 (map drop-column rows)
                 default)])
   (group-by first-column rows)))


;; --------------------------------------------------------------------
;; Map


(defmethod min-length :map [node]
  1)


(defmethod min-length :map-no-check [node]
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
             val-sym (gensym "val__")
             vars* (cons val-sym rest-vars)
             key-form (syntax/unparse key-pat)]
         [`(contains? ~target '~key-form)
          `(let [~val-sym (get ~target '~key-form)]
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
                                (concat
                                 (list [:entry {:key-pat key-pat
                                                :val-pat val-pat}]
                                       (if (= data {})
                                         [:any '_]
                                         [:map-no-check data*]))
                                 (rest-columns map-row)))
                              (concat
                               (list [:any '_]
                                     (if (= data {})
                                       [:any '_]
                                       [:map-no-check data])) 
                               (rest-columns map-row)))))))
     []
     map-rows)))


(defmethod compile-ctor-clauses :map-no-check [_tag vars rows default]
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


(defmethod min-length :part [node]
  (let [data (syntax/data node)]
    (+ (min-length (:left data))
       (min-length (:right data)))))


(defmethod next-columns :part
  [row]
  (let [node (first-column row)
        {:keys [left right]} (syntax/data node)
        left-cols (list left)
        cols* (if (syntax/has-tag? right :seq-end)
                (case (syntax/tag left)
                  :rep
                  (concat left-cols (list right) (rest (:cols row))) 

                  :drop
                  (rest (:cols row))

                  ;; else
                  (concat left-cols (rest (:cols row))))
                (concat left-cols (list right) (rest (:cols row))))]
    (assoc row :cols cols*)))


(defmethod compile-ctor-clauses :part [_tag vars rows default]
  (map
   (fn [[[kind min tags] rows]]
     (case tags
       [:any :seq-end]
       [true
        (compile (rest vars) (map next-columns rows) default)]
       
       [:cap :seq-end]
       [true
        (compile vars (map next-columns rows) default)]

       [:cat :drop]
       (let [[var & rest-vars] vars
             init-var (gensym "init__")
             vars* (list* init-var init-var rest-vars)
             inner-form (compile vars* (map next-columns rows) default)]
         (case kind
           :vec
           [`(<= ~min (count ~var))
            `(let [~init-var (subvec ~var 0 ~min)]
               ~inner-form)]
           :seq
           [true
            `(let [~init-var (take ~min ~var)]
               (if (== (count ~init-var) ~min)
                 ~inner-form
                 ~default))]))
       
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
           ~(compile vars (map next-columns rows) default)])

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
             ~(compile vars* (map next-columns rows) default)))]

       [:drop :seq-end]
       [true
        (compile (rest vars) (map next-columns rows) default)]

       [:drop :cat]
       [true
        (let [[var & rest-vars] vars
              n (gensym "n__")
              tail-var (gensym "tail__")
              vars* (list* var tail-var rest-vars)]
          `(let [~n (max 0 (- (count ~var) ~min))
                 ~tail-var ~(case kind
                              :vec
                              `(subvec ~var ~n)

                              :seq
                              `(drop ~n ~var))]
             ~(compile vars* (map next-columns rows) default)))]

       ([:rep :cap]
        [:rep :cat]
        [:init :cat])
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
             ~(compile vars* (map next-columns rows) default)))]
       
       [:rep :seq-end]
       [true
        (compile (cons (first vars) vars) (map next-columns rows) default)]

       [:rest :seq-end]
       [true
        (compile vars (map next-columns rows) default)]))
   (group-by
    (fn [row]
      (let [node (first-column row)
            {:keys [kind left right]} (syntax/data node)]
        [kind (min-length node) [(syntax/tag left) (syntax/tag right)]]))
    rows)))

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



(defmethod min-length :rep [node]
  0)


(defmethod next-columns :rep
  [row]
  (assoc row
         :cols (cons (:init (syntax/data (first-column row)))
                     (rest-columns row))))

(defmethod compile-ctor-clauses :rep [_tag vars rows default]
  (sequence
   (map
    (fn [row]
      (let [pat (:init (syntax/data (first-column row)))
            pat-vars (variables pat)
            n (min-length pat)
            let-bindings (sequence
                          (mapcat
                           (fn [[kind sym]]
                             (case kind
                               :any
                               []
                               
                               :mem
                               (if (get-sym row sym)
                                 [sym sym]
                                 [sym []])

                               :var
                               (if (get-sym row sym)
                                 []
                                 [sym ::unbound]))))
                          pat-vars)
            target (first vars)
            slice (gensym "slice__")
            loop-bindings (list* target
                                 `(drop ~n ~target)
                                 (sequence (comp
                                            (filter syntax/mem-symbol?)
                                            (mapcat (juxt identity identity)))
                                           (take-nth 2 let-bindings)))
            loop-env (:env (reduce add-sym row (take-nth 2 let-bindings)))
            let-else (compile (rest vars)
                              [(drop-column row)]
                              default)
            loop-else (compile (rest vars)
                               [(assoc (drop-column row) :env loop-env)]
                               default)]
        [true
         `(let [~slice (take ~n ~target)
                ~@let-bindings]
            (if (== (count ~slice) ~n)
              ~(compile [slice]
                        [{:cols [pat]
                          :env (:env row)
                          :rhs
                          (let [loop-sym (gensym "loop__")]
                            `((fn ~loop-sym [~@(take-nth 2 loop-bindings)]
                                (let [~slice (take ~n ~target)]
                                  (if (== (count ~slice)  ~n)
                                    ~(compile [slice]
                                              [{:cols [pat]
                                                :env loop-env
                                                :rhs
                                                `(let [~target (drop ~n ~target)]
                                                   (~loop-sym ~@(take-nth 2 loop-bindings)))}]
                                              loop-else)
                                    ~loop-else)))
                              ~@(take-nth 2 (rest loop-bindings))))}]
                        let-else)
              ~let-else))])))
   rows))



;; --------------------------------------------------------------------
;; Rest


(defmethod min-length :rest [node]
  0)


(defmethod compile-ctor-clauses :rest [_tag vars rows default]
  (let [[var & vars*] vars]
    (sequence
     (map
      (fn [row]
        (let [node (first-column row)
              sym (:mem (syntax/data node))]
          [true
           `(let [~sym ~(if (get-sym row sym)
                          `(into ~sym ~var)
                          `(vec ~var))]
              ~(compile vars* [(drop-column row)] default))])))
     rows)))


;; --------------------------------------------------------------------
;; Seq


(defmethod min-length :seq [node]
  (min-length (syntax/data node)))


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


(defmethod min-length :seq-end [node]
  0)


(defmethod compile-ctor-clauses :seq-end [_tag vars rows default]
  (let [[var & vars*] vars]
    `[[(not (seq ~var))
       ~(compile vars*
                 (map drop-column rows)
                 default)]]))


;; --------------------------------------------------------------------
;; Var

(defmethod tag-score :var [_]
  0)

(defmethod compile-ctor-clauses :var [_tag vars rows default]
  (map
   (fn [row]
     (let [[var & vars*] vars
           [_ sym] (first-column row)
           row* (drop-column (add-sym row sym))
           body (compile vars* [row*] default)]
       (if (some? (get-sym row sym))
         [`(= ~var ~sym)
          body]
         [true
          `(let [~sym ~var]
             ~body)])))
   rows))


;; --------------------------------------------------------------------
;; Vector


(defmethod min-length :vec [node]
  (min-length (syntax/data node)))


(defmethod next-columns :vec
  [row]
  (let [node (first-column row)
        ;; TODO: Move to syntax.
        part (update (syntax/data node) 1 assoc :kind :vec)
        cols* (list* part (rest (:cols row)))]
    (assoc row :cols cols*)))


(defmethod compile-ctor-clauses :vec [_tag vars rows default]
  (let [[var & vars*] vars]
    `[[(vector? ~var)
       ~(compile vars (sequence (map next-columns) rows) default)]]))


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
                         (assoc row :cols (sequence
                                           (map
                                            (fn [idx]
                                              (nth cols idx)))
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


(s/def ::match-args
  (s/cat
   :target any?
   :clauses (s/* (s/cat
                  :pat (s/conformer
                        (fn [pat]
                          (syntax/parse pat)))
                  :rhs any?))))


(defn parse-match-args
  {:private [true]}
  [match-args]
  (s/conform ::match-args match-args))


(defmacro match
  {:arglists '([target & pattern action ...])}
  [& match-args]
  (let [{:keys [target clauses]} (parse-match-args match-args)
        target-sym (gensym "target__")
        vars [target-sym]]
    `(let [~target-sym ~target]
       ~(compile vars
                 (sequence
                  (map
                   (fn [{:keys [pat rhs]}]
                     {:cols [pat]
                      :env #{}
                      :rhs rhs}))
                  clauses)
                 `(throw backtrack)))))
