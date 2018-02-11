(ns meander.core
  (:refer-clojure :exclude [bound? extend repeat replace resolve])
  (:require
   [clojure.core :as clj]
   [clojure.core.specs.alpha :as core.specs]
   [clojure.set :as set]
   [clojure.spec.alpha :as spec]
   [clojure.walk :as walk]
   [meander.protocols :as protocols]
   [meander.util :as util]))


;; ---------------------------------------------------------------------
;; Internal utilities


(defmacro undefined
  {:private true}
  ([]
   `(throw (ex-info "undefined" ~(meta &form)))))


(defn fmap
  ([f x]
   {:post [(instance? (class x) %)]}
   (if (satisfies? protocols/IFmap x)
     (protocols/-fmap x f)
     (f x))))


(defn walk
  ([inner-f outer-f x]
   (if (satisfies? protocols/IWalk x)
     (protocols/-walk x inner-f outer-f)
     (outer-f x))))


(defn postwalk
  ([f x]
   (walk (partial postwalk f) f x)))


(defn prewalk
  ([f x]
   (walk (partial prewalk f) identity (f x))))


(defn splicing-form?
  "True if `x` is an unquote splicing form i.e. `~@xs`."
  {:private true}
  ([x]
   (and (seq? x)
        (= (first x)
           'clojure.core/unquote-splicing))))


(defn lconj
  "Return a goal which is the logicical conjunction 0, 1, or 2 goals."
  {:arglists '([] [goal] [goal-1 goal-2])
   :private true}
  ([]
   (fn [smap]
     (list smap)))
  ([g]
   (fn [smap]
     (g smap)))
  ([g1 g2]
   (fn [smap]
     (mapcat g2 (g1 smap)))))


(defn lconj*
  "Return a goal which is the logical conjunction of `gaols`."
  {:private true}
  ([goals]
   {:pre [(sequential? goals)]}
   (reduce lconj goals)))


;; ---------------------------------------------------------------------
;; Term API


(defn form
  "Return the term form of `x`."
  ([x]
   (if (satisfies? protocols/IForm x)
     (protocols/-form x)
     x)))


(defn variable?
  "true if `x` is a variable, false otherwise."
  ([x]
   (satisfies? protocols/IVariable x)))


(defn variables
  "Return the set of term variables in `x`."
  ([x]
   {:post [(set? %)]}
   (if (satisfies? protocols/ITermVariables x)
     (protocols/-term-variables x)
     #{})))


(defn ground?
  "true if the term `x` contains no variables."
  ([x]
   (empty? (variables x))))


(defn positions*
  {:private true}
  ([term level]
   (cond
     (variable? term)
     #{""}

     (coll? term)
     (reduce into
             #{""} 
             (map-indexed
              (fn [i s-i]
                (map str (clj/repeat i) (positions* s-i (inc level))))
              term))

     :else
     #{""})))


(defn positions
  "Return a set of positions in `term`."
  ([term]
   {:pre [(or (variable? term)
              (coll? term))]}
   (positions* term 0)))


(defn size
  "The cardinality of the set of positions with respect to the term
  `term`."
  ([term]
   (count (positions term))))


(defn compare-term
  ([term-a term-b]
   (compare (size term-a) (size term-b))))


(defn rename-variables [term prefix]
  {:pre [(string? prefix)]}
  (let [state (volatile! {})]
    (postwalk
     (fn [x]
       (if (variable? x)
         (if-some [[_ renamed-variable] (find (deref state) (name x))]
           renamed-variable           
           (let [new-name (format "%s_%d" prefix (count (deref state)))
                 renamed-variable (fmap (constantly new-name) x)]
             (vswap! state assoc (name x) renamed-variable)
             renamed-variable))
         x))
     term)))


;; variant?
(defn isomorphic?
  "true if two terms, `term-a` and `term-b`, are structurally
  equivalent e.g. they have the same \"shape\".

    (def a (make-variable 'a))
    (def b (make-variable 'b))
    (def c (make-variable 'c))
    (def d (make-variable 'd))

    (isomorphic? [a a] [b b]) ;; => true
    (isomorphic? [a a] [b c]) ;; => false
    (isomorphic? [a b] [c d]) ;; => true
    (isomorphic? [a b] [b a]) ;; => true
    (isomorphic? [a b] [c a]) ;; => true

  Semantically equivalent to Prolog's `=@=`.
  "
  [term-a term-b]
  (= (rename-variables term-a "v")
     (rename-variables term-b "v")))


;; ---------------------------------------------------------------------
;; Unification API


(spec/def ::substitution-map
  (spec/map-of string? any?))


(defn substitution-map? [x]
  (spec/valid? ::substitution-map x))


(spec/def ::maybe-substitution-map
  (spec/nilable ::substitution-map))


(defn substitute
  {:arglists '([u substitution-map])}
  ([x smap]
   (if (satisfies? protocols/ISubstitute x)
     (protocols/-substitute x smap)
     x)))


(spec/fdef unify
  :args (spec/cat :a any?
                  :b any?
                  :substitution-map ::substitution-map)
  :ret ::maybe-substitution-map)


(defn unify
  {:arglists '([u v substitution-map])}
  ([a b smap]
   {:pre [(substitution-map? smap)]}
   ;; Orient: move variables to the left-hand side.
   (cond
     (and (not (variable? a))
          (variable? b))
     (recur b a smap)

     ;; Eliminate: solve for a particular variable (or unify in some
     ;; custom way).
     (satisfies? protocols/IUnify a)
     (protocols/-unify a b smap)

     (satisfies? protocols/IUnify* a)
     (first (protocols/-unify* a b smap))

     :else
     (if (= a b)
       smap
       nil))))


(defmacro if-unifies
  {:arglists '([[binding u v substitution-map] then else?])
   :style/indent 1}
  ([[smap* u v smap] then]
   `(if-unifies ~[smap* u v smap] ~then nil))
  ([[smap* u v smap bottom] then else]
   `(if-some [smap*# (unify ~u ~v ~smap)]
      (let [~smap* smap*#]
        ~then)
      ~else)))


(defn unify*
  "Return all possible substitutions for u and v."
  {:arglists '([u v substitution-map bottom])}
  ([u v smap]
   {:pre [(map? smap)]}
   (if (satisfies? protocols/IUnify* u)
     (protocols/-unify* u v smap)
     (if-unifies [smap* u v smap]
       (list smap*)
       ()))))


(defn bound?
  {:arglists '([variable substitution-map])}
  ([var smap]
   {:pre [(variable? var)
          (substitution-map? smap)]}
   (contains? smap var)))


(defn resolve
  "Semantically equivalent to μKaren's walk."
  {:arglists '([term substitution-map])}
  ([t smap]
   {:pre [(substitution-map? smap)]}
   (if (variable? t)
     (if-some [[_ x] (find smap (name t))]
       (resolve x smap)
       t)
     t)))


(defmacro if-resolve
  {:style/indent 1}
  ([[binding u smap] then]
   `(if-resolve [~binding ~u ~smap]
      ~then
      nil))
  ([[binding u smap] then else]
   `(let [u# ~u
          v# (resolve u# ~smap)]
      (if (identical? v# u#)
        ~else
        (let [~binding v#]
          ~then)))))


(defn resolve-all
  {:arglists '([terms substitution-map])}
  ([ts smap]
   {:pre [(substitution-map? smap)]}
   (map resolve ts (clj/repeat smap))))


(defn extend
  "Bind (`assoc`) `variable` to a `term` in the `substitution-map`
  provided the variable does not occur in `term`. Returns `nil` if
  the occurence check succeeds and the `substitution-map` if it does
  not."
  {:arglists '([substitution-map variable term])}
  ([smap v t]
   {:pre [(substitution-map? smap)
          (instance? clojure.lang.Named v)]}
   (if (contains? (variables t) v)
     nil
     (assoc smap (name v) t))))


(defmacro if-extend
  {:arglists '([[substitution-map* u v substitution-map] if-true if-false])
   :private true
   :style/indent 1}
  ([[smap* u v smap] if-t if-f]
   `(if (contains? (variables ~v) ~u)
      ~if-f
      (let [~smap* (assoc ~smap ~u ~v)]
        ~if-t))))


(defn extend-no-check
  "Bind (`assoc`) `variable` to a `term` in the `substitution-map`
  without checking if `variable` does not occur in `term`."
  {:arglists '([substitution-map variable term])}
  ([smap v t]
   {:pre [(substitution-map? smap)
          (instance? clojure.lang.Named v)]}
   (assoc smap (name v) t)))


;; ---------------------------------------------------------------------
;; Core Types


(deftype Variable [name]
  clojure.lang.Named
  (getName [_]
    (clojure.core/name name))

  protocols/IForm
  (-form [this]
    this)

  protocols/IFmap
  (-fmap [_ f]
    (Variable. (f name)))

  protocols/IVariable

  protocols/ISubstitute
  (-substitute [this smap]
    (resolve this smap))

  protocols/ITermVariables
  (-term-variables [this]
    #{this})

  protocols/IUnify
  (-unify [this that smap]
    (if-resolve [x this smap]
      (let [y (resolve that smap)]
        (if (= x y)
          smap
          nil))
      (extend smap this that)))

  Object
  (equals [_ that]
    (and (instance? Variable that)
         (= name (.name that))))

  (hashCode [this]
    (.hashCode (.name this))))


(defn make-variable
  ([name]
   {:pre [(or (instance? clojure.lang.Named name)
              (string? name))]}
   (Variable. (clojure.core/name name))))


(defmacro variable
  ([]
   `(Variable. (name (gensym))))
  ([name]
   `(Variable. (name ~name))))


(deftype SplicingVariable [name meta]
  clojure.lang.IMeta
  (meta [this]
    (.-meta this))

  clojure.lang.IObj
  (withMeta [this m]
    (SplicingVariable. name m))

  clojure.lang.Named
  (getName [_]
    (clojure.core/name name))

  protocols/IForm
  (-form [this]
    this)

  protocols/IFmap
  (-fmap [this f]
    (SplicingVariable. (f name) (.-meta this)))

  protocols/IVariable

  protocols/ISubstitute
  (-substitute [this smap]
    (resolve this smap))

  protocols/ITermVariables
  (-term-variables [this]
    #{this})

  protocols/IUnify
  (-unify [this that smap]
    (if-resolve [x this smap]
      (if (coll? x)
        (let [y (resolve that smap)]
          (if (= x y)
            smap
            nil))
        nil)
      (if (coll? that)
        (extend smap this that)
        nil)))

  Object
  (equals [_ that]
    (and (instance? SplicingVariable that)
         (= name (.name that))))

  (hashCode [this]
    (.hashCode (.name this))))


(defn make-splicing-variable
  ([]
   (SplicingVariable. (gensym) {}))
  ([name]
   (SplicingVariable. name {}))
  ([name meta]
   (SplicingVariable. name meta)))


(defn splicing-variable?
  ([x]
   (instance? SplicingVariable x)))


(defn not-splicing-variable?
  ([x]
   (not (splicing-variable? x))))


;; ---------------------------------------------------------------------
;; Sequential terms with splicing variables


(defn unify-splicing-variables*
  "Return a sequence of substitutions satisfying the unification of
  the splicing-variables `s-vars` and the collection `u-coll`.

    (let [s-vars (parse-form '[~@xs ~@ys])
        u-coll [1 2 3]]
      (unify-splicing-variables* s-vars u-coll {}))
    ;; =>
    ({\"xs\" [], \"ys\" [1 2 3]}
     {\"xs\" [1], \"ys\" [2 3]}
     {\"xs\" [1 2], \"ys\" [3]}
     {\"xs\" [1 2 3], \"ys\" []})

    (let [s-vars (parse-form '[~@xs ~@ys])
        u-coll [1 2 3]]
      (unify-splicing-variables* s-vars u-coll {\"xs\" [1 2 ]}))
    ;; =>
    ({\"xs\" [1 2], \"ys\" [3]})
  "
  {:private true}
  ([s-vars u-coll smap]
   (let [n (count s-vars)]
     (mapcat
      (fn [pairs]
        (loop [smap smap
               pairs pairs]
          (if-some [[[s-var coll] & pairs*] (seq pairs)]
            (if-unifies [smap* s-var coll smap]
              (recur smap*
                     pairs*)
              ())
            (list smap))))
      (map (partial partition 2)
           (map interleave
                (clj/repeat s-vars)
                (util/partitions n u-coll)))))))


;; ---------------------------------------------------------------------
;; Vector terms


(defn unify-splicing-vector*
  "Return a sequence of all possible substutitions satisfying `u-vec`
  and `v-vec` where `u-vec` contains splicing variables."
  {:private true}
  ([u-vec v-vec smap]
   (let [;; Split the pattern at the boundary of the first splicing
         ;; var.  For example, if the pattern was `[1 ~x 3 ~@xs 6 7 ~@ys]`
         ;; the result will be `[[1 ~x 3] [~@xs 6 7 ~@ys]]`.
         [u-left u-right] (map vec (split-with not-splicing-variable? u-vec))
         ;; Split the vector at the boundary index derived by
         ;; counting the number of values in `u-left`. Continuing
         ;; with the example above with `v-vec` as `[1 2 3 4 5 6 7 8]`
         ;; the result will be `[[1 2 3] [4 5 6 7 8]]`.
         [v-left v-right] (util/vsplit-at (count u-left) v-vec)]
     (->>
      ;; Unify `u-left` and `v-left` to get a sequence of solutions
      ;; for the non-variable porition of the vector. With our
      ;; example `u-left` and `v-left` we'd get back the sequence
      ;; `({"x" 2})`.
      (unify* u-left v-left smap)
      ;; Now the interesting part: finding all of the solutions for
      ;; `u-right` and `v-right`
      (mapcat
       (fn [smap]
         (let [;; Once again we need to break the pattern down into
               ;; pieces we can easily reason about. This time we
               ;; want to partition our pattern in a way that
               ;; isolates runs of splicing variables. Applying this
               ;; to `u-right` we get `[[~@xs] [6 7] [~@ys]]`.
               u-parts (mapv vec (partition-by splicing-variable? u-right))
               ;; Next we'll need to build a search space from
               ;; `v-right` that has parity with `u-parts`.
               ;;
               ;;   (,,,
               ;;    [[4] [5 6 7] [8 9]]
               ;;    [[4 5] [6 7] [8 9]]
               ;;    [[4 5 6] [7] [8 9]]
               ;;    ,,,)
               ;;
               v-space (util/partitions (count u-parts) v-right)]
           ;; With our search space in hand we can begin to look
           ;; for possible solutions.
           ;;
           ;; First we'll need to pair up each n-tuple in `v-space`
           ;; with each element `u-parts`.
           ;;
           ;; (,,,
           ;;  (([~@xs] [4]), ([6 7] [5 6 7]), ([~@ys] [8 9]))
           ;;  (([~@xs] [4 5]), ([6 7] [6 7]), ([~@ys] [8 9]))
           ;;  (([~@xs] [4 5 6]), ([6 7] [7]), ([~@ys] [8 9]))
           ;;  ,,,)
           ;;
           ;; Each pair in each row is then unified from left to
           ;; right using a logical conjunction goal. Notice second
           ;; row above will successfully unify `~@xs` with `[4 5]`,
           ;; `[6 7]` with `[6 7]`, and `~@ys` with `[8 9]`. This
           ;; happens to be the only solution in this case, however,
           ;; if we insert the sequence `6 7` anywhere after `3` and
           ;; not in between `6` and `7`, we would find an
           ;; additional solution.
           (->> (map (comp (partial partition 2)
                           (partial interleave u-parts))
                     v-space)
                (mapcat
                 (fn [row]
                   ((lconj*
                     (map
                      (fn [f [u-part v-part]]
                        (fn [smap]
                          (f u-part v-part smap)))
                      ;; We use a special function to find solutions
                      ;; for splicing variables.
                      (cycle [unify-splicing-variables*
                              unify*])
                      row))
                    smap)))))))))))



(defn unify-vector*
  "Return a sequence of all possible substutitions satisfying `u-vec`
  and `v-vec`."
  ([u-vec v-vec smap]
   {:pre [(vector? u-vec)]}
   (when (vector? v-vec)
     (if (some splicing-variable? u-vec)
       (unify-splicing-vector* u-vec v-vec smap)
       (when (= (count u-vec)
                (count v-vec))
         ((lconj*
           (map
            (fn [[u v]]
              (fn [smap]
                (unify* u v smap)))
            (partition 2 (interleave u-vec v-vec))))
          smap))))))


(defn unify-vector
  "Find the first substitution, if any, satisifying the unification of
  `u-vec` and `v-vec`."
  ([u-vec v-vec smap]
   {:pre [(vector? u-vec)]}
   (first (unify-vector* u-vec v-vec smap))))


(extend-type clojure.lang.IPersistentVector
  protocols/IFmap
  (-fmap [this f]
    (mapv f this))


  protocols/ITermVariables
  (-term-variables [this]
    (reduce set/union #{} (map variables this)))

  
  protocols/ISubstitute
  (-substitute [this smap]
    (reduce
     (fn [v x]
       (if (splicing-variable? x)
         (let [ys (resolve x smap)]
           (cond
             (identical? ys x)
             (let [ys* (resolve (make-variable (name x)) smap)]
               (cond
                 (identical? ys* x)
                 (throw
                  (ex-info "Missing substitution for splice variable."
                           {:variable x
                            :smap smap}))

                 (or (coll? ys*) (nil? ys*))
                 (into v ys*)

                 :else
                 (throw
                  (ex-info "Splicing variable not bound to a collection."
                           {:value ys*
                            :smap smap}))))

             (or (coll? ys) (nil? ys))
             (into v ys)

             :else
             (throw
              (ex-info "Splicing variable not bound to a collection."
                       {:value ys
                        :smap smap}))))
         (conj v (substitute x smap))))
     []
     this))


  protocols/IUnify*
  (-unify* [this that smap]
    (unify-vector* this that smap))

  
  protocols/IUnify
  (-unify [this that smap]
    (first (protocols/-unify* this that smap)))


  protocols/IWalk
  (-walk [this inner-f outer-f]
    (outer-f (protocols/-fmap this inner-f))))


;; ---------------------------------------------------------------------
;; SeqTerm


(defn unify-splicing-seq*
  ([u-seq v-seq smap]
   (when (seq? v-seq)
     (let [[u-left u-right] (split-with not-splicing-variable? u-seq)
           [v-left v-right] (split-at (count u-left) v-seq)]
       (mapcat
        (fn [smap]
          (let [u-parts (partition-by splicing-variable? u-right)
                v-space (util/partitions (count u-parts) v-right)]
            (mapcat
             (fn [row]
               ((lconj*
                 (map
                  (fn [f [u-part v-part]]
                    (fn [smap]
                      (f u-part v-part smap)))
                  (cycle [unify-splicing-variables*
                          unify*])
                  row))
                smap))
             (map (comp (partial partition 2)
                        (partial interleave u-parts))
                  v-space))))
        (unify* u-left v-left smap))))))


(defn unify-seq*
  ([u-seq v-seq smap]
   {:pre [(seq? u-seq)]}
   (when (seq? v-seq)
     (if (some splicing-variable? u-seq)
       (unify-splicing-seq* u-seq v-seq smap)
       (when (= (count u-seq)
                (count v-seq))
         ((lconj*
           (map
            (fn [[u v]]
              (fn [smap]
                (unify* u v smap)))
            (partition 2 (interleave u-seq v-seq))))
          smap))))))


(defn unify-seq
  ([u-seq v-seq smap]
   {:pre [(seq? u-seq)]}
   (first (unify-seq* u-seq v-seq))))


(extend-type clojure.lang.ISeq
  protocols/IFmap
  (-fmap [this f]
    (map f this))
  

  protocols/IForm
  (-form [_]
    (map form seq))

  
  protocols/ISubstitute
  (-substitute [this smap]
    (mapcat
     (fn [x]
       (if (splicing-variable? x)
         (let [ys (resolve x smap)]
           (cond
             (identical? ys x)
             (throw
              (ex-info "Missing substitution for splice variable"
                       {:variable x}))

             (or (coll? ys) (nil? ys))
             ys

             :else
             (throw
              (ex-info "Splice variable not bound to a collection"
                       {:value ys}))))
         (list (substitute x smap))))
     this))


  protocols/ITermVariables
  (-term-variables [this]
    (reduce set/union #{} (map variables this)))


  protocols/IWalk
  (-walk [this inner-f outer-f]
    (outer-f (protocols/-fmap this inner-f)))
  

  protocols/IUnify*
  (-unify* [this that smap]
    (unify-seq* this that smap))


  protocols/IUnify
  (-unify [this that smap]
    (first (protocols/-unify* this that smap))))


;; ---------------------------------------------------------------------
;; Map term


(defn unify-entry*
  {:private true}
  ([e-a e-b smap]
   (let [[k-a v-a] e-a
         [k-b v-b] e-b
         smap* (unify* k-a k-b smap)]
     (mapcat
      (fn [smap]
        (unify* v-a v-b smap))
      smap*))))


(defn unify-entries*
  {:private true}
  ([pairs smap]
   (if-some [[[e-a e-b] & pairs*] (seq pairs)]
     (mapcat
      (fn [smap]
        (unify-entries* (rest pairs) smap))
      (unify-entry* e-a e-b smap))
     (list smap))))


(defn unify-map*
  "Return a lazy seq of all possible substitutions for `map-a` and
  `map-b`."
  ([map-a map-b smap]
   {:pre [(map? map-a)]}
   (if (map? map-b)
     (if (= (count map-a) (count map-b))
       (if (not= map-a map-b)
         (mapcat 
          (fn [!map-a]
            (let [entries (partition 2 (interleave !map-a map-b))]
              (when-some [smap* (unify-entries* entries smap)]
                smap*)))
          (util/permutations map-a))
         (list smap))
       ())
     ())))


(defn unify-map
  ([map-a map-b smap]
   (first (unify-map* map-a map-b smap))))


(extend-type clojure.lang.IPersistentMap
  protocols/IFmap
  (-fmap [this f]
    (into {} (map f this)))

  protocols/IForm
  (-form [this]
    (reduce-kv
     (fn [m k v]
       (assoc m (form k) (form v)))
     {}
     this))

  protocols/ISubstitute
  (-substitute [this smap]
    (reduce-kv
     (fn [m k v]
       (assoc m (substitute k smap) (substitute v smap)))
     {}
     this))

  
  protocols/ITermVariables
  (-term-variables [this]
    (reduce set/union #{} (clojure.core/map variables (mapcat identity this))))


  protocols/IUnify
  (-unify [this that smap]
    (first (protocols/-unify* this that smap)))


  protocols/IUnify*
  (-unify* [this that smap]
    (unify-map* this that smap))


  protocols/IWalk
  (-walk [this inner-f outer-f]
    (outer-f (into {} (map inner-f this)))))


;; ---------------------------------------------------------------------
;; Set term


(defn unify-set*
  "Return a lazy sequence of all possible substitutions satisfying the
  unification of two sets `set-u` and `set-v`."
  {:arglists '([set-u set-v substitution-map])}
  ([set-u set-v smap]
   {:pre [(set? set-u)]}
   (if (set? set-v)
     (if (= (count set-u) (count set-v))
       (if (not= set-u set-v)
         (mapcat 
          (fn [!set-u]
            (let [pairs (partition 2 (interleave !set-u set-v))
                  goals (map (fn [[a b]]
                               (fn [smap]
                                 (unify* a b smap)))
                             pairs)]
              ((lconj* goals) smap)))
          (util/permutations set-u))      
         (list smap))
       ())
     ())))


(extend-type clojure.lang.IPersistentSet
  protocols/IFmap
  (-fmap [this f]
    (set (map f this)))


  protocols/IForm
  (-form [this]
    (set (map form this)))


  protocols/ISubstitute
  (-substitute [this smap]
    (protocols/-fmap this (fn [x] (substitute x smap))))

  
  protocols/ITermVariables
  (-term-variables [this]
    (reduce set/union #{} (map variables this)))


  protocols/IUnify
  (-unify [this that smap]
    (first (protocols/-unify* this that smap)))


  protocols/IUnify*
  (-unify* [this that smap]
    (unify-set* this that smap))


  protocols/IWalk
  (-walk [this inner-f outer-f]
    (outer-f (set (map inner-f this)))))


(defn parse-form*
  ([x]
   (parse-form* x {}))
  ([x env]
   (if-some [[_ val] (find env x)]
     [val env]
     (cond
       (seq? x)
       (cond
         (= (first x) 'quote)
         [x env]

         (= (first x) `unquote)
         (let [var (make-variable (second x))]
           [var (assoc env x var)])

         (= (first x) `unquote-splicing)
         (let [var (make-splicing-variable (second x))]
           [var (assoc env x var)])

         :else
         (reduce
          (fn [[s env*] y]
            (let [[y* env**] (parse-form* y env*)]
              [(concat s (list y*)) env**]))
          [() env]
          x))

       (vector? x)
       (reduce
        (fn [[v env*] y]
          (let [[y* env**] (parse-form* y env*)]
            [(conj v y*) env**]))
        [[] env]
        x)

       (map? x)
       (reduce
        (fn [[m env*] e]
          (let [[e* env**] (parse-form* e env*)]
            [(conj m e*) env**]))
        [{} env]
        x)

       (set? x)
       (reduce
        (fn [[s env*] y]
          (let [[y* env**] (parse-form* y env*)]
            [(conj s y*) env**]))
        [#{} env]
        x)

       :else
       [x env]))))


(defn parse-form
  [x]
  (first (parse-form* x {})))


(defn unparse-form [form]
  (postwalk
   (fn [x]
     (cond
       (splicing-variable? x)
       (list 'clojure.core/unquote-splicing (symbol (name x)))

       (variable? x)
       (list 'clojure.core/unquote (symbol (name x)))

       :else
       x))
   form))


;; ---------------------------------------------------------------------
;; Pattern compilation

(defn subvec?
  {:private true}
  [x]
  (instance? clojure.lang.APersistentVector$SubVector x))


(defn compile-smap
  {:private true}
  [seen-vars]
  `(hash-map
    ~@(mapcat
       (fn [v]
         `(~(name v)
           ~(symbol (name v))))
       seen-vars)))


(declare compile-pattern)


(defn compile-vector-pattern
  {:private true}
  [p obj inner]
  {:pre [(vector? p)]}
  (fn do-vec [seen-vars]
    (let [body
          (let [[p-init p-tail] (map vec (split-with not-splicing-variable? p))]
            (case [(empty? p-init) (empty? p-tail)]
              ;; Nothing there.
              [true true]
              `(when (seq ~obj)
                 ~(inner seen-vars))

              ;; Handle splicing variables in tail.
              [true false]
              (let [splicing-var-count (count (filter splicing-variable? p-tail))]
                (if (< 1 splicing-var-count)
                  `(unify* (parse-form '~(unparse-form p))
                           ~obj
                           ~(compile-smap seen-vars))
                  (let [svec `svec#]
                    `(let [~svec (subvec ~obj (max 0 (- (count ~obj)
                                                        ~(dec (count p-tail)))))]
                       ~((compile-pattern (subvec p-tail 1)
                                          svec
                                          (fn [seen-vars]
                                            (let [svec `svec#]
                                              `(let [~svec (subvec ~obj 0 (max 0 (- (count ~obj)
                                                                                    ~(dec (count p-tail)))))]
                                                 ~((compile-pattern (first p-tail) svec inner)
                                                   seen-vars)))))
                         seen-vars)))))

              ;; No splicing variables in the pattern.
              [false true]
              (let [[p-init* p-tail*] (map vec (split-with (complement variable?) p-init))]
                (case [(empty? p-init*) (empty? p-tail*)]
                  [true true]
                  (inner seen-vars)

                  ;; Variables at the head.
                  [true false]
                  (let [x `x#]
                    `(let [~x (first ~obj)]
                       ~((compile-pattern (first p-tail*)
                                          x
                                          (fn [seen-vars]
                                            (let [svec `svec#]
                                              `(let [~svec (subvec ~obj 1)]
                                                 ~((compile-pattern (subvec p-tail* 1) svec inner)
                                                   seen-vars)))))
                         seen-vars)))

                  [false true]
                  `(when (= (count ~obj) ~(count p))
                     ~((reduce
                        (fn [f [v obj]]
                          (fn [seen-vars]
                            (let [x `x#]
                              `(let [~x ~obj]
                                 ~((compile-pattern v x f) seen-vars)))))
                        inner
                        (reverse
                         (map-indexed
                          (fn [i x]
                            [x `(nth ~obj ~i)])
                          p)))
                       seen-vars))    
                  

                  [false false]
                  (let [svec1 `svec1#]
                    `(let [~svec1 (subvec ~obj 0 ~(count p-init*))]
                       ~((compile-pattern p-init*
                                          svec1
                                          (fn [seen-vars]
                                            (let [svec `svec#]
                                              `(let [~svec (subvec ~obj ~(count p-init*))]
                                                 ~((compile-pattern p-tail* svec inner)
                                                   seen-vars)))))
                         seen-vars)))))
              
              ;; Recurse with existing logic. 
              [false false]
              (let [svec `svec#]
                `(when (<= ~(count p-init) (count ~obj))
                   (let [~svec (subvec ~obj 0 ~(count p-init))]
                     ~((compile-pattern p-init
                                        svec
                                        (fn [seen-vars]
                                          (let [svec `svec#]
                                            `(let [~svec (subvec ~obj ~(count p-init))]
                                               ~((compile-pattern p-tail svec inner) seen-vars)))))
                       seen-vars))))))]
      ;; If `p` is a subvector then we assume it was produced by
      ;; this function and avoid type checking `obj` in the the
      ;; generated code.
      (if (subvec? p)
        body
        `(when (vector? ~obj)
           ~body)))))


(defn compile-seq-pattern
  {:private true}
  [p obj inner]
  {:pre [(seq? p)]}
  (fn do-seq [seen-vars]
    (let [body 
          (let [[p-init p-tail] (map (partial apply list)
                                     (split-with not-splicing-variable? p))]
            (case [(empty? p-init) (empty? p-tail)]
              ;; Nothing there.
              [true true]
              (inner seen-vars)

              ;; Handle splicing variables in tail.
              [true false]
              (let [splicing-var-count (count (filter splicing-variable? p-tail))]
                (if (< 1 splicing-var-count)
                  (let [inner* (fn [seen-vars]
                                 `(list ~(compile-smap seen-vars)))
                        smap `smap#
                        [p*] (reduce
                              (fn [[p* seen-vars*] x]
                                (cond
                                  (splicing-variable? x)
                                  [(concat p* `((make-splicing-variable ~(name x))))
                                   (conj seen-vars* x)]

                                  (variable? x)
                                  [(concat p* `((make-variable ~(name x))))
                                   (conj seen-vars* x)]

                                  (ground? x)
                                  [(concat p* (list x))
                                   seen-vars*]

                                  :else
                                  (let [obj `obj#]
                                    [(concat
                                      p*
                                      `((reify
                                          protocols/IUnify*
                                          (~'-unify* [this# ~obj ~smap]
                                           ;; Bind everything we
                                           ;; know about.
                                           (let [{:strs [~@(map (comp symbol name) seen-vars*)]} ~smap] 
                                             ~((compile-pattern x obj inner*) seen-vars*))))))
                                     (into seen-vars* (map name (variables x)))])))
                              [seen-vars `(list)]
                              p)] 
                    `(unify* ~p*
                             ~obj
                             ~(compile-smap seen-vars)))
                  (let [sseq `init#]
                    `(let [~sseq (take (max 0 (- (count ~obj)
                                                 ~(dec (count p-tail))))
                                       ~obj)]
                       ~((compile-seq-pattern
                          (with-meta (rest p-tail) {::subseq? true})
                          sseq
                          (fn [seen-vars]
                            (let [sseq `tail#]
                              `(let [~sseq (take (max 0 (- (count ~obj)
                                                           ~(dec (count p-tail))))
                                                 ~obj)]
                                 ~((compile-pattern
                                    (with-meta (first p-tail) {::subseq? true})
                                    sseq
                                    inner)
                                   seen-vars)))))
                         seen-vars)))))

              ;; No splicing variables in the pattern.
              [false true]
              (let [[p-init* p-tail*] (map (partial apply list)
                                           (split-with (complement variable?) p-init))]
                (case [(empty? p-init*) (empty? p-tail*)]
                  [true true]
                  `(when-not (seq ~obj)
                     ~(inner seen-vars))

                  ;; Variables at the head.
                  [true false]
                  (let [x `x#]
                    `(let [~x (first ~obj)]
                       ~((compile-pattern
                          (first p-tail*)
                          x
                          (fn [seen-vars]
                            (let [sseq `tail#]
                              `(let [~sseq (rest ~obj)]
                                 ~((compile-seq-pattern
                                    (with-meta (rest p-tail*) {::subseq? true})
                                    sseq
                                    inner)
                                   seen-vars)))))
                         seen-vars)))

                  [false true]
                  `(when (= (count ~obj) ~(count p))
                     ~((reduce
                        (fn [f [v obj]]
                          (fn [seen-vars]
                            (let [x `x#]
                              `(let [~x ~obj]
                                 ~((compile-pattern v x f)
                                   seen-vars)))))
                        inner
                        (reverse
                         (map-indexed
                          (fn [i x]
                            [x `(nth ~obj ~i)])
                          p)))
                       seen-vars))    
                  

                  ;; No variables in head, variables in tail. 
                  [false false]
                  (let [sseq `init#]
                    `(let [~sseq (take ~(count p-init*) ~obj)]
                       ~((compile-seq-pattern
                          (with-meta p-init* {::subseq? true})
                          sseq
                          (fn [seen-vars]
                            (let [sseq `tail#]
                              `(let [~sseq (drop ~(count p-init*) ~obj)]
                                 ~((compile-seq-pattern
                                    (with-meta p-tail* {::subseq? true})
                                    sseq
                                    inner)
                                   seen-vars)))))
                         seen-vars)))))
              
              ;; Recurse with existing logic. 
              [false false]
              (let [sseq `sseq#]
                `(when (<= ~(count p-init) (count ~obj))
                   (let [~sseq (take ~(count p-init) ~obj)]
                     ~((compile-seq-pattern
                        (with-meta p-init {::subseq? true})
                        sseq
                        (fn [seen-vars]
                          (let [sseq `sseq#]
                            `(let [~sseq (drop ~(count p-init) ~obj)]
                               ~((compile-seq-pattern
                                  (with-meta p-tail {::subseq? true})
                                  sseq
                                  inner)
                                 seen-vars)))))
                       seen-vars))))))]
      (if (::subseq? (meta p))
        body
        `(when (seq? ~obj)
           ~body)))))


(defn compile-set-pattern
  {:private true}
  [p obj inner]
  (fn do-set [seen-vars]
    `(when (set? ~obj)
       (unify* ~(set (map
                      (fn [x]
                        (cond
                          (splicing-variable? x)
                          `(make-splicing-variable ~(name x))

                          (variable? x)
                          `(make-variable ~(name x))

                          (ground? x)
                          x

                          :else
                          (let [obj `obj#
                                smap `smap#
                                inner* (fn [seen-vars]
                                         `(list (merge ~smap ~(compile-smap seen-vars))))]
                            `(reify
                               protocols/IUnify*
                               (~'-unify* [this# ~obj ~smap]
                                (let [~@(mapcat
                                         (fn [v]
                                           [(symbol v) `(get ~smap ~v)])
                                         (map name seen-vars))] 
                                  ~((compile-pattern x obj inner*) seen-vars)))))))
                      p))
               ~obj
               ~(compile-smap seen-vars)))))


(defn compile-pattern
  {:private true}
  [p obj inner]
  (cond
    ;; Handles splicing variables too.
    (variable? p)
    (fn do-var [seen-vars]
      (if (contains? seen-vars (name p))
        `(let [v# ~obj]
           (if (= v# ~(symbol (name p)))
             ~(inner seen-vars)))
        `(let [~(symbol (name p)) ~obj]
           ~(inner (conj seen-vars (name p))))))

    (ground? p)
    (fn do-gound [seen-vars]
      (cond
        (map-entry? p)
        (let [[k vr] p
              vl `v#]
          `(if-some [[_# ~vl] (find ~obj ~k)]
             ~((compile-pattern vr vl inner) seen-vars)))

        :else
        `(if (= ~obj '~p)
           ~(inner seen-vars))))
    
    (map-entry? p)
    (fn do-map-entry [seen-vars]
      (let [[k vr] p]
        (if (ground? k)
          (let [vl `v#]
            `(if-some [[_# ~vl] (find ~obj ~k)]
               ~((compile-pattern vr vl inner) seen-vars)))
          (undefined))))

    (vector? p)
    (compile-vector-pattern p obj inner)

    (seq? p)
    (compile-seq-pattern p obj inner)

    (map? p)
    (fn do-map [seen-vars]
      `(if (map? ~obj)
         ~(if (ground? (keys p))
            ((reduce
              (fn [f e]
                (compile-pattern e obj f))
              inner
              p)
             seen-vars)
            `(unify-map* (parse-form '~(unparse-form p))
                         ~obj
                         ~(compile-smap seen-vars)))))
    
    (set? p)
    (compile-set-pattern p obj inner)))


;; ---------------------------------------------------------------------
;; Substitution compilation


(defn compile-substitute
  {:private true}
  [pattern smap]
  (let [var-syms (map (comp symbol name) (variables pattern))]
    `(let [{:strs [~@var-syms]} ~smap]
       ~(postwalk
         (fn [x]
           (cond
             (and (variable? x)
                  (not (splicing-variable? x)))
             (symbol (name x))

             (vector? x)
             (if (some splicing-variable? x)
               (reduce
                (fn [v y]
                  (if (splicing-variable? y)
                    `(into ~v ~(symbol (name y)))
                    `(conj ~v ~y)))
                []
                x)
               x)

             (seq? x)
             (if (some splicing-variable? x)
               `(concat
                 ~@(map
                    (fn [y]
                      (if (splicing-variable? y)
                        (symbol (name y))
                        `(list ~y)))
                    x))
               x)

             (symbol? x)
             `'~x

             :else
             x))
         pattern))))


;; ---------------------------------------------------------------------
;; pattern macro


(defmacro pattern
  [form]
  (let [obj `obj#
        smap `smap#
        form* (parse-form form)]
    `(reify
       protocols/IUnify*
       (protocols/-unify* [this# ~obj smap-outer#]
         (for [smap-inner# ~((compile-pattern
                              form*
                              obj
                              (fn [seen-vars]
                                `(list ~(compile-smap seen-vars))))
                             #{})
               :when (every?
                      (fn [[ik# iv#]]
                        (if-some [[_# ov#] (find smap-outer# ik#)]
                          (= iv# ov#)
                          true))
                      smap-inner#)]
           (merge smap-outer# smap-inner#)))

       protocols/ISubstitute
       (protocols/-substitute [this# ~smap]
         ~(compile-substitute form* smap))

       clojure.lang.IFn
       (~'invoke [this# ~obj]
        (protocols/-unify* this# ~obj {})))))


;; ---------------------------------------------------------------------
;; transform macro

(spec/def ::transform-args
  (spec/cat
   :pattern any?
   :as-clause (spec/?
               (spec/cat :as #{:as}
                         :sym symbol?))
   :clauses (spec/*
             (spec/alt
              :when-clause (spec/cat
                            :when #{:when}
                            :expr any?)
              :let-clause (spec/cat
                           :let #{:let}
                           :bindings ::core.specs/bindings)))
   :ret any?))


(spec/fdef meander.core/transform
  :args ::transform-args
  :ret any?)

(defmacro transform
  {:arglists '([u-pattern clauses* s-pattern])}
  [& args]
  (let [[u-pattern & rest-args] args
        as (if (= (first rest-args) :as)
             (second rest-args))
        rest-args (if as
                    (nnext rest-args)
                    rest-args)
        clauses* (butlast rest-args)
        s-pattern (last rest-args)
        u-var-syms (map (comp symbol name)
                        (vals (second (parse-form* u-pattern))))
        s-var-syms (map (comp symbol name)
                        (vals (second (parse-form* s-pattern))))
        meta-smap (into {}
                        (map (juxt name identity)
                             (set/intersection
                              (set s-var-syms)
                              (set (mapcat
                                    (fn [clause]
                                      (when (= (first clause) :let)
                                        (take-nth 2 (destructure (second clause)))))
                                    (partition 2 clauses*))))))
        meta-smap (if as
                    (assoc meta-smap (name as) as)
                    meta-smap)
        v `v#]
    `(let [u-pattern# (pattern ~u-pattern)
           s-pattern# (pattern ~s-pattern)]
       (reify
         clojure.lang.IFn
         (clojure.lang.IFn/invoke [this# x#]
           (if-some [smap# (protocols/-unify this# x# {})]
             (protocols/-substitute this# (merge smap# (meta smap#)))
             x#))

         protocols/IUnify
         (protocols/-unify [this# v# smap#]
           (first (protocols/-unify* this# v# smap#)))

         protocols/IUnify*
         (protocols/-unify* [this# ~v smap#]
           (for [~'&smap (unify* u-pattern# ~v smap#)
                 :let [{:strs [~@u-var-syms]} ~'&smap
                       ~@(when as (list as v))]
                 ~@clauses*]
             (with-meta ~'&smap ~meta-smap)))

         protocols/ISubstitute
         (protocols/-substitute [this# smap#]
           (protocols/-substitute s-pattern# smap#))))))


;; ---------------------------------------------------------------------
;; if-transform macro


(spec/def ::if-transform-args
  (spec/cat
   :binding+f+arg
   (spec/and vector?
             (spec/cat
              :t* simple-symbol?
              :f any?
              :t any?))
   :then any?
   :else (spec/? any?)))

(spec/fdef if-transform
  :args ::if-transform-args
  :ret any?)

(defmacro if-transform
  {:style/indent :defn}
  ([& args]
   (let [[[t* f t] then else] args]
     `(let [~t* (~f ~t)]
        (if (= ~t ~t*)
          ~else
          ~then)))))


;; ---------------------------------------------------------------------
;; Strategy combinators
;;
;; A strategy is a unary function of a term and returns the term
;; rewriten.
;;
;; Notation
;;
;; t ∈ Term
;; p, q, r, s ∈ Strategy


(defn build
  "Returns a strategy which always returns `t`."
  [t]
  (fn [_] t))


(defn pipe
  "Build a strategy which applies `p` to `t` and then `q` iff `p` rewrites
  `t`. If `p` and `q` successful rewrite, return the result, otherwise
  return `t`. This is the strategy equivalent of `and`.

  Example:
  
    ((pipe (constantly :not-i) ;; Fail
           (constantly :pass!))
     :not-i)
    ;; =>
    :not-i

    ((pipe (constantly :not-i) ;; Pass
           (constantly :pass!)) ;; Pass
     :not-u)
    ;; =>
    :pass!
  "
  ([p q]
   (fn [t]
     (if-transform [t* p t]
       (if-transform [t** q t*]
         t**
         t)
       t)))
  ([p q & more]
   (apply pipe (pipe p q) more)))

(defn choice
  "Build a strategy which applies `p` or `q` to `t`. If `p` rewrites,
  return the result, otherwise apply `q`. This is the strategy
  equivalent of `or`.

  Example:

    ((choice (constantly :not-i) ;; Fail
             (constantly :pass!)) ;; Pass
     :not-i)
    ;; =>
    :pass!

    ((choice (constantly :not-i) ;; Pass
             (constantly :pass!))
     :not-u)
    ;; =>
    :not-i
  "
  ([p q]
   (fn [t]
     (if-transform [t* p t]
       t*
       (q t))))
  ([p q & more]
   (apply choice (choice p q) more)))


(defn branch
  {:style/indent :defn}
  [p q r]
  (fn [t]
    (let [t* (p t)]
      (if (not= t t*)
        (q t*)
        (r t)))))


(defn repeat
  "Build a strategy which applies `p` to `t` repeatedly until fails.

  Example:

    ((repeat
       (fn [v]
         (if (= 2 (peek v))
         v ;; Fail
         (pop v))))
       [1 2 3 4 5])
    ;; =>
    [1 2]
  "
  [p]
  (fn repeat* [t]
    ((branch p repeat* (constantly t)) t)))


(defn bottom-up [p]
  (fn [t]
    (postwalk p t)))


(defn top-down [p]
  (fn [t]
    (prewalk p t)))


;; ---------------------------------------------------------------------
;; Tools


(defn extract
  "Return a lazy sequence of all instances of `v` in `t` that unify
  with `u`. If `u` supports implements substitution it will return
  a sequence of `v*` instead where `v*` is the result of applying the
  substitution to `v`. Uses `tree-seq` to produce all subterms. 

  Example:
   
    (extract (pattern [:foo ~@xs :baz])
             [[:foo :bar :baz]
              [:foo :baz :bar]
              [:foo [:foo :baz] :baz]
              [:foo]])
    ;; =>
    ([:foo :bar :baz]
     [:foo [:foo :baz] :baz]
     [:foo :baz])
  
    (extract
     (t {:student/id ~id
         :test/score ~score}
       :when (< 90 score)
       [~id ~score])
     [{:student/id 1, :test/score 85}
      {:student/id 2, :test/score 93}
      {:student/id 3, :test/score 61}
      {:student/id 4, :test/score 99}])
    ;; =>
    ([2 93] [4 99])
  "
  ([u]
   (fn [t]
     (extract u t)))
  ([u t]
   (for [v (tree-seq seqable? seq t)
         :let [smap (unify u v {})]
         :when smap
         :let [v* (substitute u smap)]]
     (if (= v* u)
       v
       v*))))


(defn zero-property-t
  "The zero property of the function that's symbol is
  `fsym`. Semantically equivalent to

  (t (fsym ~@xs ~zero ~@ys)
    ~zero)
  "
  ([fsym zero]
   {:pre [(symbol? fsym)]}
   (reify
     clojure.lang.IFn
     (invoke [this t]
       (if-unifies [smap* this t {}]
         (protocols/-substitute this smap*)
         t))


     protocols/ISubstitute
     (-substitute [this smap]
       (if-some [seq (get smap this)]
         (if (some (partial = zero) seq)
           zero
           seq)
         (throw
          (ex-info "Zero property transform not be found in the substitution map."
                   {:fsym fsym
                    :zero zero}))))


     protocols/IUnify
     (-unify [this x smap]
       (if (and (seq? x)
                (= (first x) fsym))
         ;; Intentional use of `assoc` instead of `extend`, etc.
         (assoc smap this x)
         nil)))))


(defmacro zero-property
  "Examples:
  
  ((zero-property * 0)
   '(* x y 0))
  ;; => 0

  ((zero-property and false)
   '(and true x y false z))
  ;; => false
  "
  [fsym zero]
  {:pre [(symbol? fsym)]}
  `(zero-property-t '~fsym ~zero))


(defn associative-t
  ([fsym]
   {:pre [(symbol? fsym)]}
   (reify
     clojure.lang.IFn
     (invoke [this t]
       (if-unifies [smap* this t {}]
         (protocols/-substitute this smap*)
         t))
     
     protocols/ISubstitute
     (-substitute [this smap]
       (if-some [seq (get smap this)]
         (mapcat
          (fn [x]
            (if (and (seq? x)
                     (= (first x) fsym))
              (rest x)
              (list x)))
          seq)
         (throw
          (ex-info "Associative transform not found in the substitution."
                   {:fsym fsym}))))
     

     protocols/IUnify
     (-unify [this x smap]
       (if (and (seq? x)
                (= (first x) fsym))
         ;; Intentional use of `assoc` instead of `extend`, etc.
         (assoc smap this x)
         nil)))))


(defmacro associative
  ([fsym]
   {:pre [(symbol? fsym)]}
   `(associative-t '~fsym)))


(defn monoid-t
  ([fsym id]
   {:pre [(symbol? fsym)]}
   (reify
     clojure.lang.IFn
     (invoke [this t]
       (if-unifies [smap* this t {}]
         (protocols/-substitute this smap*)
         t))

     protocols/IRule

     protocols/ISubstitute
     (-substitute [this smap]
       (if-some [seq (get smap this)]
         (let [seq* (mapcat
                     (fn [x]
                       (cond
                         (and (seq? x)
                              (= (first x) fsym))
                         (remove #{id} (rest x))

                         (= x id)
                         ()

                         :else
                         (list x)))
                     seq)]
           (if (= seq* (list fsym))
             id
             seq*))
         ;; Is the "right" thing to do?
         (throw
          (ex-info "Monoid transform not found in the substitution map." 
                   {:fsym fsym
                    :id id}))))


     protocols/IUnify
     (-unify [this x smap]
       (if (and (seq? x)
                (= (first x) fsym))
         ;; Intentional use of `assoc` instead of `extend`.
         (assoc smap this x)
         nil)))))


(defmacro monoid
  ([fsym id]
   {:pre [(symbol? fsym)]}
   `(monoid-t '~fsym ~id)))


;; ---------------------------------------------------------------------
;; Scratch

#_
(do
  (require '[taoensso.tufte :as tufte])
  (tufte/add-basic-println-handler! {}))

(comment
  ;; Comparing performance of "interpreted" pattern unification and
  ;; "compiled" matcher unification.
  (let [p (parse-form '[1 (2 ~@xs) {:first ~first :last ~last}])
        m (pattern [1 (2 ~@xs) {:first ~first :last ~last}])
        n 10000]
    ;; ({"xs" (3), "last" "last", "first" "first"})
    [(= (unify* p [1 '(2 3) {:first "first" :last "last"}] {})
        (unify* m [1 '(2 3) {:first "first" :last "last"}] {}))
     (time ;; "Elapsed time: 6791.037121 msecs"
       (dotimes [_ n]
         (unify* p [1 '(2 3) {:first "first" :last "last"}] {})))
     (time ;; "Elapsed time: 11.289517 msecs"
       (dotimes [_ n]
         (unify* m [1 '(2 3) {:first "first" :last "last"}] {})))]))
