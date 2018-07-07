(ns meander.dev.match
  (:refer-clojure :exclude [compile])
  (:require [clojure.walk :as walk]
            [clojure.set :as set]
            [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as s.gen]
            [clojure.string :as string]
            [meander.dev.syntax :as syntax]
            [meander.dev.matrix :as r.matrix])
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
   ;; "M" means "match".
   `(symbol (format "ML%d__G%d"
                    ~(:line (meta &form))
                    (next-gensym-id))))
  ([prefix]
   `(symbol (format "ML%d__%s%d" ~(:line (meta &form))
                    ~prefix
                    (next-gensym-id)))))


;; ---------------------------------------------------------------------
;; Pattern matrix


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


(def not-ground-tags
  #{:and
    :any
    :app
    :drop
    :init
    :mem
    :not
    :or
    :prd
    :rest
    :rep
    :repk
    :unq
    :var})

;; multimethod?
(defn ground? [x]
  (not (some
        (fn [y]
          (and (syntax/node? y)
               (contains? not-ground-tags (syntax/tag y))))
        (tree-seq seqable? seq x))))


(defn group-rows [matrix]
  (sort
   (fn [[tag1 _] [tag2 _]]
     (compare (tag-score tag2)
              (tag-score tag1)))
   (r.matrix/specialize-by
    (fn [node]
      (if (some? node)
        (let [tag (syntax/tag node)]
          (if (ground? node)
            ::ground
            tag))))
    matrix)))


(declare compile)


(defn min-min-length [matrix]
  (transduce
   (map
    (fn [row]
      (syntax/min-length (r.matrix/first-column row))))
   min
   Float/POSITIVE_INFINITY
   matrix))


(defn next-columns-dispatch
  {:private true}
  [row]
  (syntax/tag (r.matrix/first-column row)))


(defmulti next-columns
  {:arglists '([row])}
  #'next-columns-dispatch)


(defmethod next-columns :default [row]
  row)


(declare compile)


(defn compile-ctor-clauses-dispatch [tag targets matrix default]
  tag)


(defmulti compile-ctor-clauses
  {:arglists '([tag targets matrix default])}
  #'compile-ctor-clauses-dispatch)


;; ---------------------------------------------------------------------
;; And

(defmethod next-columns :and [row]
  (let [pats (:pats (syntax/data (r.matrix/first-column row)))]
    (assoc row :cols (concat pats (r.matrix/rest-columns row)))))


(defmethod compile-ctor-clauses :and [_tag targets matrix default]
  (sequence
   (map
    (fn [row]
      (let [pats (:pats (syntax/data (r.matrix/first-column row)))
            n (count pats)]
        [true
         (if (zero? n)
           (compile (rest targets) [(r.matrix/drop-column row)] default)
           (compile (concat (repeat n (first targets))
                            (rest targets))
                    [(next-columns row)]
                    default))])))
   matrix))



;; ---------------------------------------------------------------------
;; App


(defmethod compile-ctor-clauses :app [_tag targets matrix default]
  (sequence
   (map
    (fn [row]
      (let [{:keys [expr pats]} (syntax/data (r.matrix/first-column row))
            and-pat [:and {:pats pats}]
            target (first targets)]
        [true
         `(let [~target (~expr ~target)]
            ~(compile targets
                      [(assoc row :cols (cons and-pat (r.matrix/rest-columns row)))]
                      default))])))
   matrix))


;; ---------------------------------------------------------------------
;; Any


(defmethod tag-score :any [_]
  -1)


(defmethod compile-ctor-clauses :any [_tag targets matrix default]
  (sequence
   (map
    (fn [row]
      [true
       (compile (rest targets) [(r.matrix/drop-column row)] default)]))
   matrix))


;; ---------------------------------------------------------------------
;; Not


(defmethod compile-ctor-clauses :not [_tag targets matrix default]
  (sequence
   (map
    (fn [row]
      (let [{:keys [pats]} (syntax/data (r.matrix/first-column row))]
        (if (= (count pats) 1)
          [(compile (take 1 targets)
                    [{:cols pats
                      :rhs false}]
                    true)
           (compile (rest targets) [(r.matrix/drop-column row)] default)]
          [(compile (take 1 targets)
                    [{:cols [(first pats)]
                      :rhs false}]
                    true)
           (compile targets
                    [(assoc row :cols
                            (cons [:not {:pats (rest pats)}]
                                  (r.matrix/rest-columns row)))]
                    default)]))))
   matrix))


;; ---------------------------------------------------------------------
;; Or


(defn analyze-or
  "Analyze or a sequence of [:fail pat absent-vars] tuples."
  {:arglists '([env or-pat])
   :private true}
  [env [_ {pats :pats}]]
  (let [pats-vars (map
                   (fn [pat]
                     ;; We don't need to account for bound variables.
                     (set/difference (syntax/var-syms pat) env))
                   pats)
        all-vars (reduce into #{} pats-vars)]
    (sequence
     (comp
      (map vector)
      (keep
       (fn [[pat pat-vars]]
         (let [absent-vars (set/difference all-vars pat-vars)]
           (when (seq absent-vars)
             [:fail pat absent-vars])))))
     pats
     pats-vars)))


(defn check-or
  "Checks if every var in or-pat occurs in every pattern of or-pat. If
  not returns an instance of ex-info describing the problems and
  returns nil otherwise. The returned ex-info contains the complete
  problematic or pattern, it's environment, and the sequence of
  offending or-pats."
  {:private true}
  [env or-pat]
  (when-some [fails (seq (analyze-or env or-pat))]
    (ex-info
     "Every pattern of an or pattern must have references to the same unbound variables."
     {:or-pat (syntax/unparse or-pat)
      :env env
      :problems (mapv
                 (fn [[_ pat absent-vars]]
                   {:pat (syntax/unparse pat)
                    :absent absent-vars})
                 fails)})))

(defmethod compile-ctor-clauses :or [_tag targets matrix default]
  (map
   (fn [row]
     (let [[_ {pats :pats} :as or-pat] (r.matrix/first-column row)]
       (when-some [ex (check-or (:env row) or-pat)]
         (throw ex))
       (case (count pats)
         ;; Just as (or) is falsey so is the (or) pattern; it is
         ;; semantically equivalent to (not _). The rewrite occurs
         ;; here instead of in syntax to preserve the pattern for
         ;; unparse.
         0
         [true
          (compile (take 1 targets)
                   [(assoc row :cols [[:not {:pats [[:any]]}]])]
                   default)]

         ;; Since (or pat) ≈ pat compile as if pat were given.
         1
         [true
          (compile targets
                   [(assoc row :cols (cons (first pats) (r.matrix/rest-columns row)))]
                   default)]

         ;; Otherwise
         (if (some syntax/any-node? pats)
           ;; No need to do extra work, (or ,,, _ ,,,) ≈ _.
           [true (compile
                  targets
                  [(assoc row :cols (cons [:any] (r.matrix/rest-columns row)))]
                  default)]
           (let [;; To reduce the amount of code generated a
                 ;; function containing the right hand side is
                 ;; compiled. The function accepts as
                 ;; arguments any variables that occur in the
                 ;; pattern (which are bound upon a successful
                 ;; pattern match). The original right hand side is
                 ;; then replaced with an invocation of this
                 ;; function with the required variables if any.
                 unbound-mem-vars (remove (:env row) (syntax/mem-syms (:cols row)))
                 unbound-vars (remove (:env row) (syntax/var-syms (:cols row)))
                 f-sym (gensym* "f__")
                 rhs* `(~f-sym ~@unbound-vars ~@unbound-mem-vars)
                 cols* (r.matrix/rest-columns row) 
                 matrix* (map
                          (fn [pat]
                            (assoc row
                                   :cols (cons pat cols*)
                                   :rhs rhs*))
                          pats)
                 inner-form (compile targets matrix* default)]
             [true
              `(let [~f-sym (fn ~f-sym [~@unbound-vars ~@unbound-mem-vars]
                              ~(:rhs row))
                     ~@(when (seq unbound-mem-vars)
                         (mapcat
                          (juxt identity (constantly []))
                          unbound-mem-vars))]
                 ~inner-form)])))))
   matrix))


;; --------------------------------------------------------------------
;; Cap


(defmethod next-columns :cap
  [row]
  (let [node (r.matrix/first-column row)
        {:keys [pat var]} (syntax/data node)
        ;; The var is placed in the first column before the pattern
        ;; since the checks around them, i.e. verifying equality in
        ;; the case of a logic variable, is potentially much cheaper
        ;; than testing the pattern first.
        cols* (list* var pat (r.matrix/rest-columns row))]
    (assoc row :cols cols*)))


(defmethod compile-ctor-clauses :cap [_tag targets matrix default]
  [[true
    (compile (cons (first targets) targets)
             (map next-columns matrix)
             default)]])


;; --------------------------------------------------------------------
;; Cat, VCat


(defn compile-cat-clauses [tag targets matrix default]
  (map
   (fn [[n matrix]]
     (let [target (first targets)
           nth-forms (map
                      (fn [index]
                        [(gensym* (str "nth_" index "__"))
                         `(nth ~target ~index)])
                      (range n))
           nth-targets (map first nth-forms)
           targets* (concat nth-targets (rest targets))
           matrix* (map
                    (fn [row]
                      (assoc row
                             :cols (concat
                                    (syntax/data (r.matrix/first-column row))
                                    (r.matrix/rest-columns row))))
                    matrix)]
       (case tag
         :cat
         [`(== ~n (count (take ~n ~target)))
          `(let [~@(mapcat identity nth-forms)]
             ~(compile targets* matrix* default))]

         :vcat
         [`(<= ~n (count ~target))
          `(let [~@(mapcat identity nth-forms)]
             ~(compile targets* matrix* default))])))
   (r.matrix/specialize-by
    (comp count syntax/data)
    matrix)))


(defmethod compile-ctor-clauses :cat [tag targets matrix default]
  (compile-cat-clauses tag targets matrix default))


(defmethod compile-ctor-clauses :vcat [tag targets matrix default]
  (compile-cat-clauses tag targets matrix default))



;; --------------------------------------------------------------------
;; Drop


(defmethod compile-ctor-clauses :drop [_tag targets matrix default]
  [[true
    (compile (rest targets) (map r.matrix/drop-column matrix) default)]])


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


(defmethod compile-ctor-clauses ::ground [_tag targets matrix default]
  (let [[target & targets*] targets]
    (map
     (fn [[node matrix]]
       [(case (syntax/tag node)
          :entry
          (let [{:keys [key-pat val-pat]} (syntax/data node)]
            `(if-some [[key# val#] (find ~target ~(compile-ground (syntax/unparse key-pat)))]
               (= val# ~(compile-ground (syntax/unparse val-pat)))
               false))

          (:map ::map-no-check)
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
        (compile targets* (map r.matrix/drop-column matrix) default)])
     (r.matrix/specialize-by identity matrix))))



;; --------------------------------------------------------------------
;; Init


(defmethod compile-ctor-clauses :init [_tag targets matrix default]
  (let [[target & targets*] targets]
    (map
     (fn [row]
       (let [node (r.matrix/first-column row)
             sym (:mem (syntax/data node))]
         [true
          `(let [~sym ~(if (r.matrix/get-sym row sym)
                         `(into ~sym ~target)
                         `(vec ~target))]
             ~(compile targets* [(r.matrix/add-sym (r.matrix/drop-column row) sym)] default))]))
     matrix)))


;; --------------------------------------------------------------------
;; Lit


(defmethod compile-ctor-clauses :lit [_tag targets matrix default]
  (map
   (fn [[[_ val] matrix]]
     `[(= ~(first targets) ~(compile-ground val))
       ~(compile (rest targets)
                 (map r.matrix/drop-column matrix)
                 default)])
   (r.matrix/specialize-by identity matrix)))


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
  [_tag targets matrix default]
  (let [[target & rest-targets] targets]
    (map
     (fn [[key-pat matrix]]
       (let [matrix* (map
                      (fn [row]
                        (assoc row
                               :cols (cons
                                      (:val-pat (syntax/data (r.matrix/first-column row)))
                                      (r.matrix/rest-columns row))))
                      matrix)
             val-sym (gensym* "val__")
             targets* (cons val-sym rest-targets)
             key-form (compile-ground (syntax/unparse key-pat))]
         [`(contains? ~target ~key-form)
          `(let [~val-sym (get ~target ~key-form)]
             ~(compile targets* matrix* default))]))
     (r.matrix/specialize-by
      (comp :key-pat syntax/data)
      matrix))))


(defn next-map-matrix
  {:private true}
  [map-matrix]
  (let [map-nodes (map r.matrix/first-column map-matrix)
        [key-pat] (first (rank-keys map-nodes))]
    (reduce
     (fn [matrix* map-row]
       (let [data (syntax/data (r.matrix/first-column map-row))]
         (conj matrix*
               (assoc map-row
                      :cols (if-some [[_ val-pat] (find data key-pat)]
                              (let [data* (dissoc data key-pat)]
                                (list*
                                 [:entry {:key-pat key-pat
                                          :val-pat val-pat}]
                                 (if (= data* {})
                                   [:any '_]
                                   [::map-no-check data*])
                                 (r.matrix/rest-columns map-row)))
                              (list*
                               [:any '_]
                               (if (= data {})
                                 [:any '_]
                                 [::map-no-check data]) 
                               (r.matrix/rest-columns map-row)))))))
     []
     (sort-by
      (comp count syntax/data r.matrix/first-column)
      map-matrix))))


(defmethod compile-ctor-clauses ::map-no-check [_tag targets matrix default]
  (let [target (first targets)]
    [[true
      (compile (cons target targets) (next-map-matrix matrix) default)]]))


(defmethod compile-ctor-clauses :map [_tag targets matrix default]
  (let [target (first targets)]
    [[`(map? ~target)
      (compile (cons target targets) (next-map-matrix matrix) default)]]))


;; --------------------------------------------------------------------
;; Memvar


(defmethod tag-score :mem [_]
  0)


(defmethod compile-ctor-clauses :mem [_tag targets matrix default]
  (let [[var & targets*] targets]
    (sequence
     (map
      (fn [row]
        (let [sym (syntax/data (r.matrix/first-column row))
              row* (r.matrix/drop-column (r.matrix/add-sym row sym))]
          [true
           `(let ~(if (some? (r.matrix/get-sym row sym))
                    [sym `(conj ~sym ~var)]
                    [sym `[~var]])
              ~(compile targets* [row*] default))])))
     matrix)))


;; --------------------------------------------------------------------
;; Partition


(defmethod compile-ctor-clauses :part [_tag targets matrix default]
  (let [target (first targets)]
    (map
     (fn [[left-tag matrix]] 
       (let [n (min-min-length matrix)]
         [true
          (case left-tag
            :cap
            (compile (cons (first targets) targets)
                     (map
                      (fn [row]
                        (let [part-data (syntax/data (r.matrix/first-column row))
                              {:keys [pat var]} (syntax/data (:left part-data))
                              right (:right part-data)]
                          (assoc row :cols (list* var pat right (r.matrix/rest-columns row)))))
                      matrix)
                     default)

            :cat
            (let [take-target (gensym* "take__")
                  drop-target (gensym* "drop__")
                  n (transduce (map
                                (comp syntax/cat-length syntax/left-node r.matrix/first-column))
                               min
                               n
                               matrix)]
              `(let [~take-target (take ~n ~target)
                     ~drop-target (drop ~n ~target)]
                 ~(compile (list* take-target drop-target (rest targets))
                           (map
                            (fn [row]
                              (let [part-data (syntax/data (r.matrix/first-column row))
                                    left (:left part-data) 
                                    items (syntax/data left)]
                                (assoc row :cols (if (seq items)
                                                   (let [[left-a left-b] (split-at n items)]
                                                     (list* [:cat left-a]
                                                            (if (seq left-b) 
                                                              [:part (assoc part-data :left [:cat left-b])]
                                                              (:right part-data))
                                                            (r.matrix/rest-columns row)))
                                                   (list* [:any '_]
                                                          (:right part-data)
                                                          (r.matrix/rest-columns row))))))
                            matrix)
                           default)))

            :drop
            (let [drop-target (gensym* "drop__")]
              `(let [~drop-target (drop (max 0 (- (count ~target) ~n)) ~target)]
                 ~(compile (list* drop-target (rest targets))
                           (map
                            (fn [row]
                              (assoc row
                                     :cols
                                     (cons (:right (syntax/data (r.matrix/first-column row)))
                                           (r.matrix/rest-columns row))))
                            matrix)
                           default)))

            :init
            (let [m (gensym* "m__")
                  take-target (gensym* "take__")
                  drop-target (gensym* "drop__")]
              `(let [~m (max 0 (- (count ~target) ~n))
                     ~take-target (take ~m ~target)
                     ~drop-target (drop ~m ~target)]
                 ~(compile (list* take-target drop-target (rest targets))
                           (map
                            (fn [row]
                              (assoc row
                                     :cols
                                     (concat
                                      ((juxt :left :right) (syntax/data (r.matrix/first-column row)))
                                      (r.matrix/rest-columns row))))
                            matrix)
                           default)))

            :rep
            (if (== n 0)
              (compile (cons target targets)
                       (map
                        (fn [row]
                          (assoc row
                                 :cols
                                 (concat
                                  ((juxt :left :right) (syntax/data (r.matrix/first-column row)))
                                  (r.matrix/rest-columns row))))
                        matrix)
                       default)
              (let [m (gensym* "m__")
                    take-target (gensym* "take__")
                    drop-target (gensym* "drop__")]
                `(let [~m (max 0 (- (count ~target) ~n))
                       ~take-target (take ~m ~target)
                       ~drop-target (drop ~m ~target)]
                   ~(compile (list* take-target drop-target (rest targets))
                             (map
                              (fn [row]
                                (assoc row
                                       :cols
                                       (concat
                                        ((juxt :left :right) (syntax/data (r.matrix/first-column row)))
                                        (r.matrix/rest-columns row))))
                              matrix)
                             default))))

            :rest
            (compile targets
                     (map
                      (fn [row]
                        (assoc row
                               :cols (list* (:left (syntax/data (r.matrix/first-column row)))
                                            (r.matrix/rest-columns row))))
                      matrix)
                     default))]))
     (r.matrix/specialize-by syntax/left-tag matrix))))


;; --------------------------------------------------------------------
;; Pred


(defmethod next-columns :prd [row]
  (let [node (r.matrix/first-column row)
        node* [:and {:pats (:pats (syntax/data node))}]]
    (assoc row :cols (cons node* (r.matrix/rest-columns row)))))


(defmethod compile-ctor-clauses :prd [_tag targets matrix default]
  (sequence
   (map
    (fn do-pred-and-matrix [[pred matrix]]
      [`(~pred ~(first targets))
       (compile targets (sequence (map next-columns) matrix) default)]))
   (r.matrix/specialize-by
    (comp :pred syntax/data)
    matrix)))


;; --------------------------------------------------------------------
;; Quote


(defmethod compile-ctor-clauses :quo [_tag targets matrix default]
  (sequence
   (map
    (fn [row]
      (let [val (syntax/data (r.matrix/first-column row))]
        ;; No need to quote the value.
        [`(= ~val ~(first targets))
         (compile (rest targets) [(r.matrix/drop-column row)] default)])))
   matrix))


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


(defmethod compile-ctor-clauses :rep [_tag targets matrix default]
  (let [target (first targets)]
    (map
     (fn [row]
       (let [pat (:init (syntax/data (r.matrix/first-column row)))
             n (if (= (syntax/tag pat) :cat)
                 (count (syntax/data pat))
                 (count (syntax/data (:pat (syntax/data pat)))))
             pat-vars (syntax/variables pat)
             let-bindings (into []
                                (mapcat
                                 (fn [[kind sym]]
                                   (case kind
                                     :mem
                                     (if (r.matrix/get-sym row sym)
                                       [sym sym]
                                       [sym []])

                                     :var
                                     (if (r.matrix/get-sym row sym)
                                       []
                                       [sym ::unbound]))))
                                pat-vars)
             let-env (:env (reduce r.matrix/add-sym row (filter syntax/mem-symbol? let-bindings)))
             let-else (compile (rest targets) [(r.matrix/drop-column row)] default)
             let-syms (take-nth 2 let-bindings)
             loop-name (gensym* "loop__")
             loop-bindings (into []
                                 (comp
                                  (filter syntax/mem-symbol?)
                                  (mapcat (juxt identity identity)))
                                 let-syms)
             loop-syms (vec (cons target (take-nth 2 loop-bindings)))
             loop-env (:env (reduce r.matrix/add-sym row let-syms))
             slice (gensym* "slice__")
             loop-else `(if (not (seq ~target))
                          ~(compile (rest targets)
                                    [(assoc (r.matrix/drop-column row) :env loop-env)]
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
                             ~(compile (take 1 targets)
                                       [{:cols [pat]
                                         :env loop-env
                                         :rhs
                                         `(let [~target (drop ~n ~target)]
                                            (~loop-name ~@loop-syms))}]
                                       loop-else))
                           (drop ~n ~target)
                           ~@(rest loop-syms))}]
                       let-else))]))
     matrix)))


;; --------------------------------------------------------------------
;; Rest


(defmethod compile-ctor-clauses :rest [_tag targets matrix default]
  (let [[target & targets*] targets]
    (map
     (fn [row]
       (let [node (r.matrix/first-column row)
             sym (:mem (syntax/data node))]
         [true
          `(let [~sym ~(if (r.matrix/get-sym row sym)
                         `(into ~sym ~target)
                         `(vec ~target))]
             ~(compile targets* [(r.matrix/add-sym (r.matrix/drop-column row) sym)] default))]))
     matrix)))


;; --------------------------------------------------------------------
;; Seq


(defmethod next-columns :seq
  [row]
  (let [node (r.matrix/first-column row)
        ;; TODO: Move to syntax.
        part (update (syntax/data node) 1 assoc :kind :seq)
        cols* (list* part (rest (:cols row)))]
    (assoc row :cols cols*)))

(defmethod compile-ctor-clauses :seq [_tag targets matrix default]
  (let [[target & targets*] targets]
    [[`(seq? ~target)
      (compile targets
               (map next-columns matrix)
               default)]]))


;; --------------------------------------------------------------------
;; SeqEnd


(defmethod compile-ctor-clauses :seq-end [_tag targets matrix default]
  (let [[target & targets*] targets]
    `[[(not (seq ~target))
       ~(compile targets*
                 (map r.matrix/drop-column matrix)
                 default)]]))


;; --------------------------------------------------------------------
;; Unquote


(defmethod compile-ctor-clauses :unq [_tag targets matrix default]
  (sequence
   (map
    (fn [row]
      (let [val (second (syntax/data (r.matrix/first-column row)))]
        [`(= ~val ~(first targets))
         (compile (rest targets) [(r.matrix/drop-column row)] default)])))
   matrix))


;; --------------------------------------------------------------------
;; Var

(defmethod tag-score :var [_]
  1)


(defmethod compile-ctor-clauses :var [_tag targets matrix default]
  (let [target (first targets)]
    (let [{:keys [bound unbound]}
          (group-by
           (fn [row]
             (let [sym (syntax/data (r.matrix/first-column row))]
               (if (r.matrix/get-sym row sym)
                 :bound
                 :unbound)))
           matrix)]
      (cond-> []
        bound
        (into (map
               (fn [row]
                 (let [[_ sym] (r.matrix/first-column row)]
                   [`(= ~target ~sym)
                    (compile (rest targets) [(r.matrix/drop-column row)] default)]))
               bound))
        
        unbound
        (conj [true
               (let [matrix* (map
                              (fn [row]
                                (let [[_ sym] (r.matrix/first-column row)]
                                  (r.matrix/drop-column (r.matrix/add-sym row sym))))
                              unbound)
                     body (compile (rest targets) matrix* default)]
                 `(let [~@(sequence (comp
                                     (map (comp syntax/data r.matrix/first-column))
                                     (distinct)
                                     (mapcat (juxt identity (constantly target))))
                                    unbound)]
                    ~body))])))))


;; --------------------------------------------------------------------
;; Vector


(defmethod next-columns :vec
  [row]
  (assoc row
         :cols (cons (syntax/data (r.matrix/first-column row))
                     (rest (:cols row)))))



(defmethod compile-ctor-clauses :vec [_tag targets matrix default]
  (let [[target & targets*] targets]
    `[[(vector? ~target)
       ~(compile targets (sequence (map next-columns) matrix) default)]]))


;; --------------------------------------------------------------------
;; VPartition


(defmethod compile-ctor-clauses :vpart [_tag targets matrix default]
  (let [target (first targets)]
    (map
     (fn [[left-tag matrix]]
       (let [n (min-min-length matrix)]
         [true
          (case left-tag
            :cap
            (compile (cons (first targets) targets)
                     (map
                      (fn [row]
                        (let [part-data (syntax/data (r.matrix/first-column row))
                              {:keys [pat var]} (syntax/data (:left part-data))
                              right (:right part-data)]
                          (assoc row :cols (list* var pat right (r.matrix/rest-columns row)))))
                      matrix)
                     default)

            :drop
            (let [drop-vec (gensym* "drop_vec__")]
              `(let [~drop-vec (subvec ~target (max 0 (- (count ~target) ~n)))]
                 ~(compile (cons drop-vec (rest targets))
                           (map
                            (fn [row]
                              (let [part-data (syntax/data (r.matrix/first-column row))
                                    right (:right part-data)]
                                (assoc row :cols (cons right (r.matrix/rest-columns row)))))
                            matrix)
                           default)))

            :init
            (let [m (gensym* "m__")
                  left-vec (gensym* "left_vec__")
                  right-vec (gensym* "right_vec__")]
              `(let [~m (max 0 (- (count ~target) ~n))
                     ~left-vec (subvec ~target 0 ~m)
                     ~right-vec (subvec ~target ~m)]
                 ~(compile (list* left-vec right-vec (rest targets))
                           (map
                            (fn [row]
                              (let [part-data (syntax/data (r.matrix/first-column row))
                                    left (:left part-data)
                                    right (:right part-data)]
                                (assoc row :cols (list* left right (r.matrix/rest-columns row)))))
                            matrix)
                           default)))

            :rep
            (if (== n 0)
              (compile (cons target targets)
                       (map
                        (fn [row]
                          (assoc row
                                 :cols
                                 (concat
                                  ((juxt :left :right) (syntax/data (r.matrix/first-column row)))
                                  (r.matrix/rest-columns row))))
                        matrix)
                       default)
              (let [m (gensym* "m__")
                    take-target (gensym* "left_vec__")
                    drop-target (gensym* "right_vec__")]
                `(let [~m (max 0 (- (count ~target) ~n))
                       ~take-target (subvec ~target 0 ~m)
                       ~drop-target (subvec ~target ~m)]
                   ~(compile (list* take-target drop-target (rest targets))
                             (map
                              (fn [row]
                                (assoc row
                                       :cols
                                       (concat
                                        ((juxt :left :right) (syntax/data (r.matrix/first-column row)))
                                        (r.matrix/rest-columns row))))
                              matrix)
                             default))))

            :rest
            (compile targets
                     (map
                      (fn [row]
                        (let [part-data (syntax/data (r.matrix/first-column row))
                              left (:left part-data)]
                          (assoc row :cols (cons left (r.matrix/rest-columns row)))))
                      matrix)
                     default)

            :vcat
            (let [;; n needs to be different here because the initial
                  ;; part of the pattern may have a length shorter
                  ;; than the total minimum length (which is what
                  ;; min-length for :vpart returns).
                  n (min-min-length
                     (mapv (fn [row]
                             {:cols [(syntax/left-node (r.matrix/first-column row))]})
                           matrix))
                  take-target (gensym* "left_vec__")
                  drop-target (gensym* "right_vec__")
                  m (gensym* "m__")]
              `(let [~m (min (count ~target) ~n)
                     ~take-target (subvec ~target 0 ~m)
                     ~drop-target (subvec ~target ~m)]
                 ~(compile (list* take-target drop-target (rest targets))
                           (map
                            (fn [row]
                              (let [part-data (syntax/data (r.matrix/first-column row))
                                    left (:left part-data) 
                                    items (syntax/data left)]
                                (assoc row :cols (if (seq items)
                                                   (let [[left-a left-b] (split-at n items)]
                                                     (list* [:vcat left-a]
                                                            (if (seq left-b) 
                                                              [:vpart (assoc part-data :left [:vcat left-b])]
                                                              (:right part-data))
                                                            (r.matrix/rest-columns row)))
                                                   (list* [:any '_]
                                                          (:right part-data)
                                                          (r.matrix/rest-columns row))))))
                            matrix)
                           default))))]))
     (r.matrix/specialize-by
      (comp syntax/tag :left syntax/data)
      matrix))))


;; --------------------------------------------------------------------
;; Fail

(defmethod compile-ctor-clauses :default [_tag targets matrix default]
  [[true
    (cond
      (seq targets)
      [:error targets matrix]

      (some (comp seq :cols) matrix)
      [:error targets matrix]

      :else
      (:rhs (first matrix)))]])


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


(defn prioritize-matrix [[targets matrix]]
  (let [idxs (into []
                   (map first)
                   (sort
                    (fn [[_ score-1] [_ score-2]]
                      (< score-2 score-1))
                    (sequence
                     (map
                      (fn [i]
                        [i (score-column matrix i)]))
                     (range (count targets)))))
        targets* (into []
                       (map
                        (fn [idx]
                          (nth targets idx)))
                       idxs)
        matrix* (into []
                      (map
                       (fn [row]
                         (let [cols (:cols row)]
                           (assoc row :cols (mapv
                                             (fn [idx]
                                               (nth cols idx))
                                             idxs)))))
                      matrix)]
    [targets* matrix*]))


(defn compile
  [targets matrix default]
  (let [[targets matrix] (prioritize-matrix [targets matrix])
        {preds false, no-preds true}
        (group-by (comp true? first)
                  (mapcat
                   (fn [[tag matrix]]
                     (compile-ctor-clauses tag targets matrix default))
                   (group-rows matrix)))

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
                              (= no-pred-body (nth then 3 nil)))
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
;; Match macros


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
  {:private true}
  [match-args]
  (s/conform ::match-args match-args))


(s/fdef match
  :args ::match-args
  :ret any?)


(defn clauses->matrix
  {:private true}
  [clauses]
  (into []
        (comp
         (map
          (fn [{:keys [pat rhs] :as row}]
            (let [check-if-bound-vars
                  (into #{}
                        (comp
                         (filter syntax/rep-node?)
                         (mapcat syntax/variables)
                         (filter syntax/var-node?)
                         (map syntax/data))
                        (tree-seq coll? seq (:pat row)))
                  rhs (if (seq check-if-bound-vars)
                        `(if (contains? (hash-set ~@check-if-bound-vars) ::unbound)
                           (throw backtrack)
                           ~rhs)
                        rhs)]
              (assoc row :rhs rhs))))
         (map
          (fn [{:keys [pat rhs]}]
            {:cols [pat]
             :env #{}
             :rhs rhs})))
        clauses))


(defmacro match
  {:arglists '([target & pattern action ...])
   :style/indent :defn}
  [& match-args]
  (let [{:keys [target clauses]} (parse-match-args match-args)
        final-clause (some
                      (fn [{:keys [pat] :as clause}]
                        (when (= pat '[:any _])
                          clause))
                      clauses)
        clauses* (if final-clause
                   (remove (comp #{[:any '_]} :pat) clauses)
                   clauses)
        target-sym (gensym* "target__")
        targets [target-sym]
        matrix (clauses->matrix clauses*)
        form `(let [~target-sym ~target]
                ~(compile targets matrix `(throw backtrack)))]
    (if final-clause
      (try-form form (:rhs final-clause))
      `(try
         ~form
         (catch ~'Exception e#
           (if (identical? e# backtrack)
             (throw (Exception. "non exhaustive pattern match"))
             (throw e#)))))))

(s/fdef match->
  :args ::match-args
  :ret any?)

(defmacro match->
  "Takes an expression and a set of pattern/form pairs. Threads expr
  (via ->) through each form for which the corresponding pattern
  matches."
  {:style/indent :defn}
  ([form] form)
  ([form pat expr & more-clauses]
   `(match->
      (r.match/match ~form
        ~pat
        (-> ~form ~expr)

        ~'_
        ~form)
      ~@more-clauses)))


(s/fdef match->>
  :args ::match-args
  :ret any?)


(defmacro match->>
  "Takes an expression and a set of pattern/form pairs. Threads expr
  (via ->>) through each form for which the corresponding pattern
  matches."
  {:style/indent :defn}
  ([form] form)
  ([form pat expr & more-clauses]
   `(match->>
      (r.match/match ~form
        ~pat
        (->> ~form ~expr)

        ~'_
        ~form)
      ~@more-clauses)))


(s/fdef when-match
  :args ::match-args
  :ret any?)


(defmacro when-match [expr & clauses]
  `(match ~expr ~@clauses ~'_ nil))
