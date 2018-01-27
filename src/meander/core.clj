(ns meander.core
  (:refer-clojure :exclude [bound? extend replace resolve])
  (:require
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
                (map str (repeat i) (positions* s-i (inc level))))
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
  ([x substitution-map]
   (if (satisfies? protocols/ISubstitute x)
     (protocols/-substitute x substitution-map)
     x)))


(spec/fdef unify
  :args (spec/cat :a any?
                  :b any?
                  :substitution-map ::substitution-map)
  :ret ::maybe-substitution-map)


(defn unify
  {:arglists '([a b substitution-map])}
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
   (map resolve ts (repeat smap))))


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


(deftype SplicingVariable [name]
  clojure.lang.Named
  (getName [_]
    (clojure.core/name name))

  protocols/IForm
  (-form [this]
    this)

  protocols/IFmap
  (-fmap [_ f]
    (SplicingVariable. (f name)))

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
   (SplicingVariable. (gensym)))
  ([name]
   (SplicingVariable. name)))


(defn splicing-variable? [x]
  (instance? SplicingVariable x))


;; ---------------------------------------------------------------------
;; Vector terms

;; Given the term
;;
;;   [~@as ~@bs ~@as]
;;
;; what are the possible solutions that would satisfy unification for
;; the following form?
;;
;;   [x x a b x x]
;;
;; {as [x x], bs [a b]}
;; {as [x], bs [x a b x]}
;; {as [], bs [x x a b x x]}
(defn unify-splicing-variables*
  ([a-vars b-vec smap]
   (let [;; a-vars (map (comp make-variable second) a-vars)
         n (count a-vars)]
     (mapcat
      (fn [k+vs]
        (loop [smap smap
               k+vs k+vs]
          (if-some [[[k v] & k+vs*] (seq k+vs)]
            (if-unifies [smap* k v smap]
              (recur smap* k+vs*)
              ())
            (list smap))))
      (map (partial partition 2)
           (map interleave (repeat a-vars) (util/partitions n b-vec)))))))


(defn unify-splicing-vector*
  ([a-vec b-vec smap]
   (if (vector? b-vec)
     (let [[a-left a-right] (map vec (split-with (complement splicing-variable?) a-vec))
           [b-left b-right] (util/vsplit-at (count a-left) b-vec)]
       (mapcat
        (fn [smap]
          (let [a-partitions (mapv vec (partition-by splicing-variable? a-right))
                b-partitions*  (util/partitions (count a-partitions) b-right)
                unify* (cycle [unify-splicing-variables*
                               unify*])]
            (mapcat
             (fn [b-partitions]
               ((lconj*
                 (map
                  (fn [f [a-partition b-partition]]
                    (fn [smap]
                      (f a-partition b-partition smap)))
                  unify*
                  (partition 2
                             (interleave a-partitions
                                         b-partitions))))
                smap))
             b-partitions*)))
        (unify* a-left b-left smap)))
     ())))


(defn unify-vector
  ([u-vec v-vec smap]
   {:pre [(vector? u-vec)]}
   (if (vector? v-vec)
     (if-some [[_ x] (find smap u-vec)]
       (if (= v-vec x)
         smap
         nil)
       (loop [u-vec u-vec
              v-vec v-vec
              smap smap]
         (case [(empty? u-vec) (empty? v-vec)]
           [true true]
           smap

           [true false]
           nil

           [false true]
           nil

           [false false]
           (let [a (peek u-vec)
                 b (peek v-vec)]
             (if-unifies [smap* a b smap]
               (let [u-vec* (pop u-vec)
                     v-vec* (pop v-vec)]
                 (recur u-vec*
                        v-vec*
                        smap*))
               nil)))))
     nil)))


(defn unify-vector*
  ([u-vec v-vec smap]
   {:pre [(vector? u-vec)]}
   (if (some splicing-variable? u-vec)
     (unify-splicing-vector* u-vec v-vec smap)
     (if-some [smap* (unify-vector u-vec v-vec smap)]
       (list smap*)
       ()))))


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
  ([a-seq b-seq smap]
   (if (seq? b-seq)
     (let [[a-left a-right] (split-with (complement splicing-variable?) a-seq)
           [b-left b-right] (split-at (count a-left) b-seq)]
       (mapcat
        (fn [smap]
          (let [a-partitions (partition-by splicing-variable? a-right)
                b-partitions* (util/partitions (count a-partitions) b-right)
                unify* (cycle [unify-splicing-variables*
                               unify*])]
            (mapcat
             (fn [b-partitions]
               ((lconj*
                 (map
                  (fn [f [a-partition b-partition]]
                    (fn [smap]
                      (f a-partition b-partition smap)))
                  unify*
                  (partition 2
                             (interleave a-partitions
                                         b-partitions))))
                smap))
             b-partitions*)))
        (unify* a-left b-left smap)))
     ())))



(defn unify-seq
  ([u-seq v-seq smap]
   {:pre [(seq? u-seq)]}
   (if (seq? v-seq)
     (if-some [[_ x] (find smap u-seq)]
       (if (= v-seq x)
         smap
         nil)
       (loop [u-seq-seq u-seq
              v-seq-seq v-seq
              smap smap]
         (case [(empty? u-seq-seq) (empty? v-seq-seq)]
           [true true]
           smap

           [true false]
           nil

           [false true]
           nil

           [false false]
           (let [a (first u-seq-seq)
                 b (first v-seq-seq)]
             (if-unifies [smap* a b smap]
               (let [u-seq-seq* (rest u-seq-seq)
                     v-seq-seq* (rest v-seq-seq)]
                 (recur u-seq-seq* v-seq-seq* smap*))
               nil)))))
     nil)))


(defn unify-seq*
  ([u-seq v-seq smap]
   {:pre [(seq? u-seq)]}
   (if (seq? v-seq)
     (if (some splicing-variable? u-seq)
       (unify-splicing-seq* u-seq v-seq smap)
       (if-some [smap* (unify-seq u-seq v-seq smap)]
         (list smap*)
         ()))
     ())))


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


;; ---------------------------------------------------------------------
;; Rule construction


(defn left-side
  ([rule]
   (protocols/-rule-left-side rule)))


(defn right-side
  ([rule]
   (protocols/-rule-right-side rule)))


(defn make-rule
  {:arglists '([left-side right-side])}
  ([lhs rhs]
   ;; lhs is the left side of the rule (the pattern to match).
   ;; rhs is the right side of the rule (the replacement pattern to
   ;; substitue).
   ;; f is a unary function
   ;; t, u are terms
   (reify
     clojure.lang.IFn
     (invoke [_ t]
       (let [t* (prewalk
                 (fn [u]
                   (if-unifies [smap u lhs {}]
                     (substitute rhs smap)
                     u))
                 t)]
         (if (= t* t)
           t
           (recur t*))))


     protocols/IFmap
     (-fmap [_ f]
       (make-rule (f lhs) (f rhs)))


     protocols/IRule


     protocols/IUnify
     (-unify [_ t smap]
       (unify lhs t smap))


     protocols/ISubstitute
     (-substitute [_ smap]
       (substitute rhs smap))


     protocols/IWalk
     (-walk [this inner-f outer-f]
       (outer-f
        (protocols/-fmap
         this
         (fn [x]
           (walk inner-f outer-f x))))))))


(comment
  (= ((make-rule [1 2 3] [4 5 6])
      [1 2 3])
     [4 5 6])

  (= ((make-rule [1 2 3] [4 5 6])
      [3 2 1])
     [3 2 1])

  (let [x (make-variable 'x)
        y (make-variable 'y)
        r (make-rule [x y x] [x y])]
    (= (r [1 2 1])
       [1 2])))


(defn run-rule
  {:arglists '([rule term] [rule term substitution-map])}
  ([r t]
   (run-rule r t {}))
  ([r t smap]
   (if (satisfies? protocols/IRule r)
     (loop [t t]
       (let [t* (prewalk
                 (fn [u]
                   (if-unifies [smap* r u smap]
                     (substitute r smap*)
                     u))
                 t)]
         (if (= t* t)
           t
           (recur t*))))
     t)))


(defn make-associative-rule
  ([fsym]
   {:pre [(symbol? fsym)]}
   (reify
     clojure.lang.IFn
     (invoke [this t]
       (run-rule this t))
     

     protocols/IRule


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
         ^{:explanation "An associative rule could not be found in the substitution map."}
         (util/undefined)))
     

     protocols/IUnify
     (-unify [this x smap]
       (if (and (seq? x)
                (= (first x) fsym))
         ;; Intentional use of `assoc` instead of `extend`, etc.
         (assoc smap this x)
         nil)))))

(comment
  (= ((make-associative-rule '+)
      '(+ 1 2 (+ 3)))
     '(+ 1 2 3)))


(defmacro associative-rule
  ([fsym]
   {:pre [(symbol? fsym)]}
   `(make-associative-rule '~fsym)))


(defn make-monoid-rule
  ([fsym id]
   {:pre [(symbol? fsym)]}
   (reify
     clojure.lang.IFn
     (invoke [this t]
       (run-rule this t))

     protocols/IRule

     protocols/ISubstitute
     (-substitute [this smap]
       (if-some [seq (get smap this)]
         (let [seq* (mapcat
                     (fn ([x]
                          (cond
                            (and (seq? x)
                                 (= (first x) fsym))
                            (remove #{id} (rest x))

                            (= x id)
                            ()
                            :else
                            (list x))))
                     seq)]
           (if (= seq* (list fsym))
             id
             seq*))
         ^{:explanation "A monoid rule could not be found in the substitution map."}
         (util/undefined)))


     protocols/IUnify
     (-unify [this x smap]
       (if (and (seq? x)
                (= (first x) fsym))
         ;; Intentional use of `assoc` instead of `extend`, etc.
         (assoc smap this x)
         nil)))))


(defmacro monoid-rule
  ([fsym id]
   {:pre [(symbol? fsym)]}
   `(monoid-call-r '~fsym ~id)))


(defn make-zero-property-rule
  "The zero property of the function that's symbol is
  `fsym`. Semantically equivalent to

  (rule []
    :replace
    (fsym ~@xs ~zero ~@ys)

    :with
    ~zero)
  "
  ([fsym zero]
   {:pre [(symbol? fsym)]}
   (reify
     clojure.lang.IFn
     (invoke [this t]
       (run-rule this t))

     protocols/IRule

     protocols/ISubstitute
     (-substitute [this smap]
       (if-some [seq (get smap this)]
         (if (some (partial = zero) seq)
           zero
           seq)
         ^{:explanation "An zero property rule could not be found in the substitution map."}
         (util/undefined)))


     protocols/IUnify
     (-unify [this x smap]
       (if (and (seq? x)
                (= (first x) fsym))
         ;; Intentional use of `assoc` instead of `extend`, etc.
         (assoc smap this x)
         nil)))))


(defmacro zero-property-rule
  "Examples:
  
  (run-rule
   (zero-property-rule * 0)
   '(* x y (* z 0)))
  ;; => 0

  (run-rule
   (zero-property-rule and false)
   '(and yes? no? (and maybe? false)))
  ;; => false
  "
  [fsym zero]
  {:pre [(symbol? fsym)]}
  `(make-zero-property-rule '~fsym ~zero))


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


(spec/def ::rule-name
  symbol?)


(spec/def ::rule-params
  (spec/coll-of symbol? :kind vector?))


(spec/def ::as-clause
  (spec/cat
   :keyword #{:as}
   :form ::core.specs/binding-form))


(spec/def ::replace-clause
  (spec/cat
   :keyword #{:replace}
   :form any?))


(spec/def ::when-clause
  (spec/cat
   :keyword #{:when}
   :form any?))


(spec/def ::where-clause
  (spec/cat
   :keyword #{:where}
   :form (spec/and vector?
                   (spec/* ::core.specs/binding))))


(spec/def ::with-clause
  (spec/cat
   :keyword #{:with}
   :form any?))


(spec/def ::rule-clause
  (spec/alt
   :as-clause ::as-clause
   :replace-clause ::replace-clause
   :when-clause ::when-clause
   :where-clause ::where-clause
   :with-clause ::with-clause))


(spec/def ::rule-args
  (spec/cat
   :rule-name (spec/? ::rule-name)
   :params ::rule-params
   :clauses (spec/* ::rule-clause)))


(spec/def ::defrule-args
  (spec/cat
   :rule-name ::rule-name
   :params ::rule-params
   :clauses (spec/* ::rule-clause)))


(defn parse-rule-args
  {:private true}
  [rule-args]
  (let [{:keys [clauses params rule-name]}
        (spec/conform ::rule-args rule-args)]
    (into 
     {:smap (gensym "smap__")
      :u (gensym "u__")
      :v (gensym "v__")
      :params params
      :rule-name rule-name}
     (map (comp (juxt :keyword :form) second) clauses))))


(defn compile-rule-name
  {:private true}
  [rule-data]
  (when-some [rule-name (get rule-data :rule-name)]
    (let [params (get rule-data :params [])
          arglists `'~(reverse
                       (map (comp vec (partial cons 'term))
                            (take (inc (count params))
                                  (iterate pop params))))]
      (with-meta rule-name {:arglists arglists}))))


(defn compile-when
  {:arglists '([rule-data then-form else-form])
   :private true}
  [{:keys [params replace smap when]} then-form else-form]
  (if (some? when)
    (let [[_ env] (parse-form* replace {})
          var-syms (set (map (comp symbol name) (vals env)))]
      `(let [~@(mapcat
                (juxt identity
                      (fn [sym]
                        `(resolve (make-variable '~sym) ~smap)))
                var-syms)
             ~@(mapcat
                (juxt identity
                      (fn [sym]
                        `(if-resolve [x# (make-variable '~sym) ~smap]
                           x#
                           nil)))
                (remove var-syms params))]
         (if ~when
           ~then-form
           ~else-form)))
    then-form))


(defn compile-where
  {:arglists '([rule-data inner-form])
   :private true}
  [{:keys [params replace smap where]} inner-form]
  (if (some? where)
    (let [[_ env] (parse-form* replace {})
          var-syms (set (map (comp symbol name) (vals env)))]
      `(let [~@(mapcat
                (juxt identity
                      (fn [sym]
                        `(resolve (make-variable '~sym) ~smap)))
                var-syms)
             ~@(mapcat
                (juxt identity
                      (fn [sym]
                        `(if-resolve [x# (make-variable '~sym) ~smap]
                           x#
                           nil)))
                (remove var-syms params))
             ~@(mapcat
                (fn [[binding val]]
                  `(~binding ~val
                    ~smap (extend-no-check ~smap (make-variable '~binding) ~binding)))
                (partition 2
                           (mapcat
                            (comp destructure
                                  (partial spec/unform ::core.specs/binding))
                            where)))]
         ~inner-form))
    inner-form)) 


(defn compile-as
  {:arglists '([rule-data inner-form])
   :private true}
  [{:keys [as v]} inner-form]
  (if (some? as)
    `(let ~(destructure [(spec/unform ::core.specs/binding-form as) v])
       ~inner-form)
    inner-form))


(defn compile-rule
  {:arglists '([rule-data])
   :private true}
  ([{:keys [params replace rule-name smap u v when where with] :as rule-data}]
   (let [this-sym (gensym "this__")
         with-sym (gensym "with__")
         arglists (map (partial concat [this-sym v])
                       (take (inc (count params))
                             (iterate pop params)))
         substitute-form `(substitute ~with-sym ~smap)]
     `(let [[~u env#] (parse-form* '~replace {})
            [~with-sym _#] (parse-form* '~with env#)]
        (reify
          clojure.lang.IFn
          ~@(for [arglist arglists]
              `(~'invoke [~@arglist]
                (let [~smap {}
                      ~@(mapcat
                         (juxt (constantly smap)
                               (fn [symbol]
                                 `(extend-no-check ~smap (make-variable '~symbol) ~symbol)))
                         (drop 2 arglist))
                      ~@(if (some? rule-name)
                          `(~rule-name ~this-sym))]
                  (loop [~v ~v]
                    (let [v# (prewalk
                              (fn [~v]
                                (if-some [~smap (unify ~u ~v ~smap)]
                                  ~(compile-when
                                    rule-data
                                    (compile-as rule-data
                                                (compile-where rule-data
                                                               substitute-form))
                                    v)
                                  ~v))
                              ~v)]
                      (if (= v# ~v)
                        ~v
                        (recur v#)))))))

          protocols/IRuleLeftSide
          (~'-rule-left-side [~this-sym]
           ~u)

          protocols/IRuleRightSide
          (~'-rule-right-side [~this-sym]
           ~with-sym)

          protocols/ISubstitute
          (~'-substitute [~this-sym ~smap]
           (substitute ~with-sym ~smap))

          protocols/IUnify
          (~'-unify [~this-sym ~v ~smap]
           (unify ~u ~v ~smap)))))))


(spec/fdef rule
  :args ::rule-args
  :ret any?)

;; TODO: Variable names (ids) need to be unique within the rule.
;; TODO: Add a clause for controlling substitution.
(defmacro rule
  {:arglists '([[params*] & {:keys [replace with where when]}])
   :style/indent :defn}
  ([& args]
   (let [rule-data (parse-rule-args args)]
     (compile-rule rule-data))))


(spec/fdef defrule
  :args ::defrule-args
  :ret any?)


(defmacro defrule
  {:arglists '([name [params*] & {:keys [replace when where with]}])
   :style/indent :defn}
  ([name params & rule-args]
   (let [name (compile-rule-name {:params params
                                  :rule-name name})]
     `(def ~name
        (rule ~name ~params ~@rule-args)))))


;; ---------------------------------------------------------------------
;; Utilities


(defn extract
  "Return a lazy sequence of all instances of `pattern` in `u`. Uses
  `tree-seq` to produce all subterms.

  (let [[pattern _] (parse-form* '{:a ~A
                                   :b ~B})]
    (extract pattern '[1
                       {:a a
                        :b {:a b
                            :b c}}
                       2
                       {:c {:a x
                            :b y}}
                       3])))
    ;; =>
    ({:a a,
      :b {:a b,
          :b c}}
    {:a b,
     :b c}
    {:a x,
     :b y})
  "
  ([pattern]
   (fn [u]
     (extract pattern u)))
  ([pattern u]
   (filter
    (fn [v]
      (unify pattern v {}))
    (tree-seq seqable? seq u))))


;; ---------------------------------------------------------------------
;; Scratch

;; TODO: Map unification can be made smarter by
;;   [ ] comparing map sizes, if the LHS is larger than the RHS fail;
;;
;; TODO: Rule compilation can be made smarter by checking if the LHS
;; is ground. If so, matching is simply an equality check.

#_
(do
  (require '[taoensso.tufte :as tufte])
  (tufte/add-basic-println-handler! {}))

;; Rules can be parameterized. `x` and `y` are optional (positional)
;; parameters which can be passed when the rule is applied. So
;;
;;   (rule-name term 1)
;;
;; means "apply `rule-name` with `x` bound to 1." In this example
;; `term` will only match when it is of the form `[1 y 1]`. The
;; application
;;
;;   (rule-name term 1 2)
;;
;; binds `x` and `y` thus `term` will stictly match `[1 2 1]`.
(defrule rule-name [x y]
  ;; The left side of the rule, the pattern to match.
  :replace
  [~x ~y ~x] ;; [:a :b :a], [:b :a :b], [1 2 1], etc.

  ;; Bind the value of a successfully matched pattern to `v` for
  ;; subsequent use in `:with`, `:when`, and `:where` clauses.
  :as
  v

  ;; The right side of the rule, the replacement pattern which
  ;; substitution will be appliek.
  :with
  [~z]

  ;; Optionally a constraint may be placed on the match.
  :when
  (keyword? x)
  
  ;; Essentially a `let` between matching and replacing. This allows
  ;; subrules, functions, etc. to be executed before performing the
  ;; substitution to the right side.
  :where
  [z (if (number? x) y x)])


;; This rule will diverge whenever `x` and `y` unify with
;; eachother. This is restricted in term rewriting systems which
;; require termination of rules.
(defrule replace [x y]
  :replace
  ~x

  :with
  ~y)
