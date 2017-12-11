(ns meander.core
  (:refer-clojure :exclude [bound? extend resolve])
  (:require
   [clojure.set :as set]
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


(defmacro nothing
  {:private true}
  ([] `(Object.)))


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
   (and (satisfies? protocols/IIsVariable x)
        (protocols/-variable? x))))


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


(defn variable-frequencies
  {:private true}
  ([term]
   (let [state (volatile! {})]
     (postwalk
      (fn [x]
        (if (variable? x)
          (if-some [[_ counter] (find (deref state) x)]
            (do
              (vswap! state update x inc)
              x)
            (do
              (vswap! state assoc x 1)
              x))
          x))
      term)
     (deref state))))


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


(defn isomorphic?
  "true if two terms, `term-a` and `term-b`, have the same shape.

    (let [a (make-variable 'a)
          b (make-variable 'b)
          x (make-variable 'x)
          y (make-variable 'y)]
    (isomorphic? (make-vector-term [x y x])
                 (make-vector-term [a b a])))
    ;; => true
  "
  [term-a term-b]
  (= (rename-variables term-a "v")
     (rename-variables term-b "v")))


;; ---------------------------------------------------------------------
;; Unification API


(defn substitute
  ([x substitution-map]
   (if (satisfies? protocols/ISubstitute x)
     (protocols/-substitute x substitution-map)
     x)))


(defn unify
  ([a b substitution-map bottom]
   {:pre [(map? substitution-map)]}
   ;; Orient: move variables to the left-hand side.
   (cond
     (and (not (variable? a))
          (variable? b))
     (recur b a substitution-map bottom)

     ;; Eliminate: solve for a particular variable.
     (variable? a)
     (protocols/-unify a b substitution-map bottom)

     (satisfies? protocols/IUnify a)
     (protocols/-unify a b substitution-map bottom)

     :else
     (if (= a b)
       substitution-map
       bottom))))


(defmacro if-unifies
  {:arglists '([[binding u v substitution-map bottom] then else?])
   :style/indent 1}
  ([[smap* u v smap bottom] then]
   `(if-unifies ~[smap* u v smap bottom] then nil))
  ([[smap* u v smap bottom] then else]
   `(let [bottom# ~bottom
          smap*# (unify ~u ~v ~smap bottom#)]
      (if (identical? smap*# bottom#)
        ~else
        (let [~smap* smap*#]
          ~then)))))


(defn unify*
  "Return all possible substitutions for u and v."
  {:arglists '([u v substitution-map bottom])}
  ([u v smap bottom]
   {:pre [(map? smap)]}
   (if (satisfies? protocols/IUnify* u)
     (protocols/-unify* u v smap bottom)
     (if-unifies [smap* u v smap bottom]
       (list smap*)
       ()))))


(defn bound?
  {:arglists '([variable substitution-map])}
  ([var smap]
   {:pre [(variable? var)
          (map? smap)]}
   (contains? smap var)))


(defn resolve
  "Semantically equivalent to μKaren's walk."
  {:arglists '([term substitution-map])}
  ([t smap]
   {:pre [(map? smap)]}
   (if-some [[_ x] (find smap t)]
     (resolve x smap)
     t)))

(defmacro if-resolve
  {:style/indent 1}
  ([[binding u smap] then]
   `(if-resolve [~binding ~u ~smap] ~then nil))
  ([[binding u smap] then else]
   `(let [u# ~u
          v# (resolve u# ~smap)]
      (if (identical? v# u#)
        ~else
        (let [~binding v#]
          ~then)))))


(defn resolve-all
  {:arglists '([terms... substitution-map])}
  ([ts smap]
   (map resolve ts (repeat smap))))


(defn extend
  {:arglists '([substitution-map variable term bottom])}
  ([smap v t bottom]
   (if (contains? (variables t) v)
     bottom
     (assoc smap v t))))


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
  {:arglists '([substitution-map variable term])}
  ([smap v t]
   (assoc smap v t)))


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

  protocols/IIsVariable
  (-variable? [_] true)

  protocols/ISubstitute
  (-substitute [this substitution-map]
    (if-some [[_ x] (find substitution-map this)]
      x
      this))

  protocols/ITermVariables
  (-term-variables [this]
    #{this})

  protocols/IUnify
  (-unify [this that substitution-map bottom]
    (if-some [_ (find substitution-map this)]
      (let [x (resolve this substitution-map)
            y (resolve that substitution-map)]
        (if (= x y)
          substitution-map
          bottom))
      (extend substitution-map this that bottom)))

  Object
  (equals [_ that]
    (and (instance? Variable that)
         (= name (.name that))))

  (hashCode [_]
    (.hashCode name)))


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


(deftype ConditionalVariable [name predicate]
  clojure.lang.Named
  (getName [_]
    (clojure.core/name name))

  protocols/IForm
  (-form [this]
    this)

  protocols/IFmap
  (-fmap [_ f]
    (ConditionalVariable. (f name) predicate))

  protocols/IIsVariable
  (-variable? [_] true)

  protocols/ISubstitute
  (-substitute [this substitution-map]
    (if-some [[_ x] (find substitution-map this)]
      x
      this))

  protocols/ITermVariables
  (-term-variables [this]
    #{this})

  protocols/IUnify
  (-unify [this that substitution-map bottom]
    (if-some [[_ x] (find substitution-map this)]
      (if (= x that)
        substitution-map
        bottom)
      (if (predicate that)
        ;; Occurs check
        (extend substitution-map this that bottom)
        bottom)))

  Object
  (equals [_ that]
    (and (instance? ConditionalVariable that)
         (= name (.name that))
         (= predicate (.predicate that))))

  (hashCode [_]
    (.hashCode [name predicate])))


(defn make-conditional-variable
  ([name predicate]
   {:pre [(or (instance? clojure.lang.Named name)
              (string? name))
          (ifn? predicate)]}
   (ConditionalVariable. name predicate)))


(defn conditional-variable?
  "true if `x` is a conditional variable, false otherwise."
  ([x]
   (instance? ConditionalVariable x)))


;; ---------------------------------------------------------------------
;; VectorTerm


(deftype VectorTerm [vector]
  clojure.core.protocols/CollReduce
  (coll-reduce [_ f]
    (clojure.core.protocols/coll-reduce vector f))

  (coll-reduce [_ f val]
    (clojure.core.protocols/coll-reduce vector f val))

  clojure.lang.Seqable
  (seq [_]
    (.seq vector))

  clojure.lang.IPersistentCollection
  (count [_]
    (.count vector))

  (cons [_ x]
    (VectorTerm. (.cons vector x)))

  (empty [_]
    (VectorTerm. (empty vector)))

  (equiv [this that]
    (and (instance? VectorTerm that)
         (= (.vector this)
            (.vector that))))

  protocols/IFmap
  (-fmap [_ f]
    (VectorTerm. (mapv f vector)))

  protocols/IForm
  (-form [_]
    (mapv form vector))

  protocols/ISubstitute
  (-substitute [_ substitution-map]
    (VectorTerm. (mapv substitute vector (repeat substitution-map))))

  protocols/ITermVariables
  (-term-variables [_]
    (reduce set/union #{} (map variables vector)))

  protocols/IUnify
  (-unify [this that smap bottom]
    (cond
      (vector? that)
      (if-some [[_ x] (find smap this)]
        (if (= that x)
          smap
          bottom)
        (loop [this-vector vector
               that-vector that
               smap smap]
          (case [(empty? this-vector) (empty? that-vector)]
            [true true]
            smap

            [true false]
            bottom

            [false true]
            bottom

            [false false]
            (let [a (peek this-vector)
                  b (peek that-vector)]
              (if-unifies [smap* a b smap bottom]
                (let [this-vector* (pop this-vector)
                      that-vector* (pop that-vector)]
                  (recur this-vector*
                         that-vector*
                         smap*))
                bottom)))))

      (instance? VectorTerm that)
      (protocols/-unify this (.vector that) smap bottom)

      :else
      bottom))

  protocols/IWalk
  (-walk [this inner-f outer-f]
    (outer-f (protocols/-fmap this inner-f)))

  Object
  (equals [this that]
    (and (instance? VectorTerm that)
         (= (.vector this)
            (.vector that))))

  (hashCode [_]
    (.hashCode vector)))


(defn make-vector-term
  ([vector]
   {:pre [(vector? vector)]}
   (VectorTerm. vector)))


;; ---------------------------------------------------------------------
;; VectorSplicingTerm
;;
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


;; TODO: Move to util.
(defn indices-of
  "Return all indices of elements satisfying `pred` in `coll` and
  `nil` no such element exists."
  {:private true}
  ([pred coll]
   (keep
    (fn [[i x]]
      (when (pred x)
        i))
    (map-indexed vector coll))))


;; TODO: Move to util.
(defn index-of
  "Return the index of first element satisfying `pred` in `coll` and
  `nil` no such element exists."
  {:private true}
  ([pred coll]
   (first (indices-of pred coll))))


(defn splicing-form?
  ([x]
   (and (seq? x)
        (= (first x)
           'clojure.core/unquote-splicing))))


(defn unify-splicing-variables*
  ([a-vars b-vec smap bottom]
   (let [a-vars (map (comp make-variable second) a-vars)
         n (count a-vars)]
     (mapcat
      (fn [k+vs]
        (loop [smap smap
               k+vs k+vs]
          (if-some [[[k v] & k+vs*] (seq k+vs)]
            (if-unifies [smap* k v smap bottom]
              (recur smap* k+vs*)
              ())
            (list smap))))
      (map (partial partition 2)
           (map interleave (repeat a-vars) (util/partitions n b-vec)))))))


(defn lconj
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
  {:private true}
  ([goals]
   (reduce lconj goals)))


(defn unify-splicing-vector*
  ([a-vec b-vec smap bottom]
   (let [a-partitions (mapv vec (partition-by splicing-form? a-vec))
         b-partitions*  (util/partitions (count a-partitions) b-vec)
         unify* (cycle (if (splicing-form? (ffirst a-partitions))
                         [unify-splicing-variables*
                          unify*]
                         [unify*
                          unify-splicing-variables*]))]
     (mapcat
      (fn [b-partitions]
        ((lconj*
          (map
           (fn [f [a-partition b-partition]]
             (fn [smap]
               (f a-partition b-partition smap bottom)))
           unify*
           (partition 2
                      (interleave a-partitions
                                  b-partitions))))
         smap))
      b-partitions*))))


(deftype VectorSplicingTerm [vector splice-variables]
  clojure.core.protocols/CollReduce
  (coll-reduce [_ f]
    (clojure.core.protocols/coll-reduce vector f))

  (coll-reduce [_ f val]
    (clojure.core.protocols/coll-reduce vector f val))

  clojure.lang.Seqable
  (seq [_]
    (.seq vector))

  clojure.lang.IPersistentCollection
  (count [_]
    (.count vector))

  (cons [_ x]
    (VectorSplicingTerm. (.cons vector x) splice-variables))

  (empty [_]
    (VectorTerm. (empty vector)))

  (equiv [this that]
    (and (instance? VectorSplicingTerm that)
         (= (.vector this)
            (.vector that))))

  protocols/IFmap
  (-fmap [_ f]
    (VectorSplicingTerm. (mapv f vector) splice-variables))

  protocols/IForm
  (-form [_]
    (mapv form vector))

  protocols/ISubstitute
  (-substitute [this substitution-map]
    (VectorSplicingTerm.
     (reduce
      (fn [v x]
        (if (contains? splice-variables x)
          (let [x-var (make-variable (second x))
                ys (resolve x-var substitution-map)]
            (cond
              (identical? ys x-var)
              (throw
               (ex-info "Missing substitution for splice variable"
                        {:variable (second x)}))

              (or (coll? ys)
                  (nil? ys))
              (into v ys)

              :else
              (throw
               (ex-info "Splicing variable not bound to a collection"
                        {:value (second x)}))))
          (conj v (substitute x substitution-map))))
      []
      (.-vector this))
     splice-variables))

  protocols/ITermVariables
  (-term-variables [_]
    (reduce set/union #{} (map variables vector)))

  protocols/IUnify
  (-unify [this that smap bottom]
    (or (first (protocols/-unify* this that smap bottom))
        bottom))
  
  protocols/IUnify*
  (-unify* [this that smap bottom]
    (cond
      (vector? that)
      (unify-splicing-vector* (.vector this) that smap bottom)

      (instance? VectorSplicingTerm that)
      (undefined)

      :else
      ()))

  protocols/IWalk
  (-walk [this inner-f outer-f]
    (outer-f (protocols/-fmap this inner-f)))

  Object
  (equals [this that]
    (and (instance? VectorSplicingTerm that)
         (= (.vector this)
            (.vector that))))

  (hashCode [_]
    (.hashCode vector)))


(defn make-vector-splicing-term
  {:arglists '([vector]
               [vector splicing-variables])}
  ([v]
   {:pre [(vector? v)]}
   (make-vector-splicing-term v (set (filter splicing-form? v))))
  ([v splice-vars]
   {:pre [(vector? v)]}
   (if (seq splice-vars)
     (VectorSplicingTerm. v splice-vars)
     (make-vector-term v))))


(extend-protocol protocols/IUnify*
  clojure.lang.IPersistentVector
  (-unify* [this that smap bottom]
    (unify* (make-vector-splicing-term this) that smap bottom)))


(extend-protocol protocols/IUnify
  clojure.lang.IPersistentVector
  (-unify [this that smap bottom]
    (or (first (unify* this that smap bottom))
        bottom)))


;; ---------------------------------------------------------------------
;; SeqTerm

(deftype SeqTerm [seq]
  clojure.core.protocols/CollReduce
  (coll-reduce [this f]
    (clojure.core.protocols/coll-reduce (.-seq this) f))

  (coll-reduce [this f val]
    (clojure.core.protocols/coll-reduce (.-seq this) f val))

  clojure.lang.Seqable
  (seq [this]
    (.-seq this))

  clojure.lang.IPersistentCollection
  (count [this]
    (.count (.-seq this)))

  (cons [this x]
    (SeqTerm. (.cons (.-seq this) x)))

  (empty [this]
    (SeqTerm. (empty (.-seq this))))

  (equiv [this that]
    (and (instance? SeqTerm that)
         (= (.seq this)
            (.seq that))))

  protocols/IFmap
  (-fmap [this f]
    (SeqTerm. (doall (map f (.-seq this)))))

  protocols/IForm
  (-form [this]
    (map form (.-seq this)))

  protocols/ISubstitute
  (-substitute [this substitution-map]
    (SeqTerm. (map substitute (.-seq this) (repeat substitution-map))))

  protocols/ITermVariables
  (-term-variables [this]
    (reduce set/union #{} (map variables (.-seq this))))

  protocols/IUnify
  (-unify [this that smap bottom]
    (cond
      (seq? that)
      (if-some [[_ x] (find smap this)]
        (if (= that x)
          smap
          bottom)
        (loop [this-seq (.-seq this)
               that-seq that
               smap smap]
          (case [(empty? this-seq) (empty? that-seq)]
            [true true]
            smap

            [true false]
            bottom

            [false true]
            bottom

            [false false]
            (let [a (first this-seq)
                  b (first that-seq)
                  smap* (unify a b smap bottom)]
              (if (= smap*
                     bottom)
                bottom
                (let [this-seq* (rest this-seq)
                      that-seq* (rest that-seq)]
                  (recur this-seq* that-seq* smap*)))))))

      (instance? SeqTerm that)
      (protocols/-unify this (.-seq that) smap bottom)

      :else
      bottom))

  protocols/IWalk
  (-walk [this inner-f outer-f]
    (outer-f (protocols/-fmap this inner-f)))

  Object
  (equals [this that]
    (and (instance? SeqTerm that)
         (= (.-seq this)
            (.-seq that))))

  (hashCode [this]
    (.hashCode (.-seq this))))


(defn make-seq-term
  ([seq]
   {:pre [(seq? seq)]}
   (SeqTerm. seq)))


(defn unify-splicing-seq*
  ([a-seq b-seq smap bottom]
   (let [a-partitions (partition-by splicing-form? a-seq)
         b-partitions* (util/partitions (count a-partitions) b-seq)
         unify* (cycle (if (splicing-form? (ffirst a-partitions))
                         [unify-splicing-variables*
                          unify*]
                         [unify*
                          unify-splicing-variables*]))]
     (mapcat
      (fn [b-partitions]
        ((lconj*
          (map
           (fn [f [a-partition b-partition]]
             (fn [smap]
               (f a-partition b-partition smap bottom)))
           unify*
           (partition 2
                      (interleave a-partitions
                                  b-partitions))))
         smap))
      b-partitions*))))


(deftype SeqSplicingTerm [seq splice-variables]
  clojure.core.protocols/CollReduce
  (coll-reduce [this f]
    (clojure.core.protocols/coll-reduce (.seq this) f))

  (coll-reduce [this f val]
    (clojure.core.protocols/coll-reduce (.seq this) f val))

  clojure.lang.Seqable
  (seq [_]
    (.seq seq))

  clojure.lang.IPersistentCollection
  (count [_]
    (.count seq))

  (cons [_ x]
    (SeqSplicingTerm. (.cons seq x) splice-variables))

  (empty [_]
    (SeqTerm. (empty seq)))

  (equiv [this that]
    (and (instance? SeqSplicingTerm that)
         (= (.seq this)
            (.seq that))))

  protocols/IFmap
  (-fmap [_ f]
    (SeqSplicingTerm. (map f seq) splice-variables))

  protocols/IForm
  (-form [_]
    (map form seq))

  protocols/ISubstitute
  (-substitute [this substitution-map]
    (SeqSplicingTerm.
     (reduce
      (fn [v x]
        (if (contains? splice-variables x)
          (let [x-var (make-variable (second x))
                ys (resolve x-var substitution-map)]
            (cond
              (identical? ys x-var)
              (throw
               (ex-info "Missing substitution for splice variable"
                        {:variable (second x)}))

              (or (coll? ys)
                  (nil? ys))
              (concat v ys)

              :else
              (throw
               (ex-info "Splicing variable not bound to a collection"
                        {:value (second x)}))))
          (concat v (list (substitute x substitution-map)))))
      []
      (.-seq this))
     splice-variables))

  protocols/ITermVariables
  (-term-variables [_]
    (reduce set/union #{} (map variables seq)))

  protocols/IUnify
  (-unify [this that smap bottom]
    (or (first (protocols/-unify* this that smap bottom))
        bottom))
  
  protocols/IUnify*
  (-unify* [this that smap bottom]
    (cond
      (seq? that)
      (unify-splicing-seq* (.seq this) that smap bottom)

      (instance? SeqSplicingTerm that)
      (undefined)

      :else
      ()))

  protocols/IWalk
  (-walk [this inner-f outer-f]
    (outer-f (protocols/-fmap this inner-f)))

  Object
  (equals [this that]
    (and (instance? SeqSplicingTerm that)
         (= (.seq this)
            (.seq that))))

  (hashCode [_]
    (.hashCode seq)))


(defn make-seq-splicing-term
  {:arglists '([seq]
               [seq splicing-variables])}
  ([s]
   {:pre [(seq? s)]}
   (make-seq-splicing-term s (set (filter splicing-form? s))))
  ([s splice-vars]
   {:pre [(seq? s)]}
   (if (seq splice-vars)
     (SeqSplicingTerm. s splice-vars)
     (make-seq-term s))))


(extend-protocol protocols/IUnify*
  clojure.lang.ISeq
  (-unify* [this that smap bottom]
    (unify* (make-seq-splicing-term this) that smap bottom)))


(extend-protocol protocols/IUnify
  clojure.lang.ISeq
  (-unify [this that smap bottom]
    (or (first (unify* this that smap bottom))
        bottom)))


;; ---------------------------------------------------------------------
;; Map term


(defn unify-entry*
  {:private true}
  ([e-a e-b smap bottom]
   (let [[k-a v-a] e-a
         [k-b v-b] e-b
         smap* (unify* k-a k-b smap bottom)]
     (mapcat
      (fn [smap]
        (unify* v-a v-b smap bottom))
      smap*))))


(defn unify-entries*
  {:private true}
  ([pairs smap bottom]
   (if-some [[[e-a e-b] & pairs*] (seq pairs)]
     (mapcat
      (fn [smap]
        (unify-entries* (rest pairs) smap bottom))
      (unify-entry* e-a e-b smap bottom))
     (list smap))))


(defn unify-map*
  "Return a lazy seq of all possible substitutions."
  ([map-a map-b smap bottom]
   {:pre [(map? map-a)]}
   (if (map? map-b)
     (if (= (count map-a) (count map-b))
       (if (not= map-a map-b)
         (mapcat 
          (fn [!map-a]
            (let [entries (partition 2 (interleave !map-a map-b))
                  smap* (unify-entries* entries smap bottom)]
              (when-not (identical? smap* bottom)
                smap*)))
          (util/permutations map-a))
         (list smap))
       ())
     ())))


(defn unify-map
  ([map-a map-b smap bottom]
   (or (first (unify-map* map-a map-b smap bottom))
       bottom)))


(deftype MapTerm [^clojure.lang.IPersistentMap map]
  clojure.core.protocols/CollReduce
  (coll-reduce [_ f]
    (clojure.core.protocols/coll-reduce map f))

  (coll-reduce [_ f val]
    (clojure.core.protocols/coll-reduce map f val))

  clojure.lang.Seqable
  (seq [_]
    (.seq map))

  clojure.lang.IPersistentCollection
  (count [_]
    (.count map))

  (cons [_ x]
    (MapTerm. (.cons map x)))

  (empty [_]
    (MapTerm. (.empty map)))

  (equiv [this that]
    (= (.map this)
       (.map that)))

  protocols/IFmap
  (-fmap [_ f]
    (MapTerm.
     (reduce-kv
      (fn [m k v]
        (assoc m (fmap f k) (fmap f v)))
      {}
      map)))

  protocols/IForm
  (-form [_]
    (reduce-kv
     (fn [m k v]
       (assoc m (form k) (form v)))
     {}
     map))

  protocols/ISubstitute
  (-substitute [_ smap]
    (MapTerm.
     (reduce-kv
      (fn [m k v]
        (assoc m (substitute k smap) (substitute v smap)))
      {}
      map)))

  protocols/ITermVariables
  (-term-variables [_]
    (reduce set/union #{} (clojure.core/map variables (mapcat identity map))))

  protocols/IUnify
  (-unify [this that smap bottom]
    (or (first (protocols/-unify* this that smap bottom))
        bottom)) 

  protocols/IUnify*
  (-unify* [this that smap bottom]
    (cond
      (map? that)
      (if-some [[_ x] (find smap this)]
        (if (= that x)
          (list smap)
          ())
        (unify-map* map that smap bottom))

      (instance? MapTerm that)
      (protocols/-unify* this (.map that) smap bottom)

      :else
      ()))

  protocols/IWalk
  (-walk [this inner-f outer-f]
    (outer-f
     (protocols/-fmap this (comp outer-f inner-f))))

  Object
  (equals [this that]
    (and (instance? MapTerm that)
         (= (.map this)
            (.map that))))

  (hashCode [_]
    (.hashCode map)))


(defn make-map-term
  ([^clojure.lang.IPersistentMap map]
   {:pre [(map? map)]}
   (if (every?
        (fn [[k v]]
          (and (ground? k)
               (ground? v)))
        map)
     map
     (MapTerm. map))))


(extend-protocol protocols/IUnify
  clojure.lang.IPersistentMap
  (-unify [this that smap bottom]
    (unify-map this that smap bottom)))


(extend-protocol protocols/IUnify*
  clojure.lang.IPersistentMap
  (-unify* [this that smap bottom]
    (unify-map* this that smap bottom)))



;; ---------------------------------------------------------------------
;; Rule construction


(defn left-hand-side
  ([rule]
   (protocols/-rule-left-hand-side rule)))


(defn right-hand-side
  ([rule]
   (protocols/-rule-right-hand-side rule)))


(defn make-rule
  ([left-hand-side right-hand-side]
   (reify
     protocols/IFmap
     (-fmap [_ f]
       (make-rule (f left-hand-side)
                  (f right-hand-side)))
     
     protocols/IRuleLeftHandSide
     (protocols/-rule-left-hand-side [_]
       left-hand-side)

     protocols/IRuleRightHandSide
     (protocols/-rule-right-hand-side [_]
       right-hand-side)

     protocols/IWalk
     (-walk [this inner-f outer-f]
       (outer-f
        (protocols/-fmap
         this
         (fn [x]
           (walk inner-f outer-f x)))))

     Object
     (equals [_ that]
       (and (satisfies? protocols/IRuleLeftHandSide that)
            (= (#'left-hand-side that) left-hand-side)
            (satisfies? protocols/IRuleRightHandSide that)
            (= (#'right-hand-side that) right-hand-side)))

     (hashCode [_]
       (.hashCode [left-hand-side right-hand-side])))))


(defn rule?
  ([x]
   (and (satisfies? protocols/IRuleLeftHandSide x)
        (satisfies? protocols/IRuleRightHandSide x))))


(defn valid-rule?
  ([x]
   (and (rule? x)
        (not (variable? (left-hand-side x)))
        (set/superset?
         (variables (left-hand-side x))
         (variables (right-hand-side x))))))


(defn apply-rule
  ([rule term]
   (let [bottom (Object.)
         result (unify (left-hand-side rule) term {} bottom)]
     (if (identical? result bottom)
       term
       (form (substitute (right-hand-side rule) result))))))


(defn run-rule
  ([rule term]
   (let [f (partial apply-rule rule)
         ;; Apply the rule to every node in the term's tree.
         term* (if (satisfies? protocols/IWalk term)
                 (postwalk f term)
                 (clojure.walk/postwalk f term))]
     (if (= term term*)
       ;; If nothing has changed we're done.
       term
       ;; If the term has been rewritten we need to run the rule once
       ;; more.
       (recur rule term*)))))


(defn run-rules
  ([rules term]
   (let [term* (reduce
                (fn [term* rule]
                  (run-rule rule term*))
                term
                rules)]
     (if (= term term*)
       term
       (recur rules term*)))))


;; ---------------------------------------------------------------------
;; Macros


(defn coll-with-splicing?
  ([x]
   (and (coll? x)
        (some splicing-form? x))))


(defn parse-form
  {:arglists '([form]
               [form environment])}
  ([form]
   (parse-form form {}))
  ([form env]
   (if-some [[_ v] (find env form)]
     v
     (cond
       (map? form)
       (make-map-term
        (reduce-kv
         (fn [m k v]
           (assoc m (parse-form k env) (parse-form v env)))
         {}
         form))

       (vector? form)
       (let [splice-variables! (volatile! #{})
             vector (mapv
                     (fn [x]
                       (if (splicing-form? x)
                         (do 
                           (vswap! splice-variables! conj x)
                           x)
                         (parse-form x env)))
                     form)
             splice-variables @splice-variables!]
         (if (empty? splice-variables)
           (make-vector-term vector)
           (make-vector-splicing-term vector splice-variables))) 
       
       (seq? form)
       (let [splice-variables! (volatile! #{})
             ;; The collection must be forced to pick up the splice
             ;; variables.
             seq (doall (map
                         (fn [x]
                           (if (splicing-form? x)
                             (do 
                               (vswap! splice-variables! conj x)
                               x)
                             (parse-form x env)))
                         form))
             splice-variables @splice-variables!]
         (if (empty? splice-variables)
           (make-seq-term seq)
           (make-seq-splicing-term seq splice-variables)))

       :else
       form))))


(defmacro vars
  {:style/indent :defn}
  ([variables & body]
   `(let [~@(mapcat
             (fn [variable]
               `(~variable ~(or (when-some [[_ tag] (find (meta variable) :tag)]
                                  (when (symbol? tag)
                                    (when-some [v (resolve tag)]
                                      `(make-conditional-variable '~variable ~tag))))
                                `(make-variable '~(gensym (str (name variable) "__"))))))
             variables)]
      ~@body)))

(defmacro rule
  {:style/indent :defn}
  ([variables lhs rhs]
   `(vars ~variables
      (let [env# (hash-map ~@(mapcat
                              (fn [variable]
                                `('~variable ~variable))
                              variables))]
        (make-rule (parse-form '~lhs env#)
                   (parse-form '~rhs env#))))))



(comment
  (defn view-vars
    [form]
    (walk/postwalk
     (fn [x]
       (if (variable? x)
         (name x)
         x))
     form))

  (run-rules
   [(rule []
      (let [~@bindings-1]
        (let [~@bindings-2]
          ~@body-2)
        ~@body-1)
      (let [~@bindings-1
            ~@bindings-2]
        ~@body-2
        ~@body-1))
    (rule [p1 arg1]
      ((fn [p1 ~@ps] ~@body) arg1 ~@args)
      (let [p1 arg1]
        ((fn [~@ps] ~@body) ~@args)))]

   '((fn [foo bar baz]
       {:foo foo
        :bar bar
        :baz baz})
     "foo"
     "bar"
     "baz"))
  ;; =>
  (let [foo "foo"
        bar "bar"
        baz "baz"]
    ((fn []
       {:foo foo, :bar bar, :baz baz})))

  (run-rule
   (rule [x y]
     (~@xs x => y ~@ys)
     (~@xs (is (= x y)) ~@ys))
   '(do
      1 => 2
      (conj [1 2 3] 4) => [1 2 3 4]))
  ;; =>
  (do
    (is (= 1 2))
    (is (= (conj [1 2 3] 4)
           [1 2 3 4])))

  (let [?x (variable 'x)
        a-vec [?x '~@xs '~@ys ?x '~@ys '~@xs]
        b-vec [4 :x :x :y :y 4 :y :y :x :x]]
    (view-vars (unify a-vec b-vec {} ::bottom)))

  ;; =>
  {"x" 4, "xs" [:x :x], "ys" [:y :y]})

(let [a-vec '[~@xs ~@ys ~@xs]
      b-vec [:x :x :y :y :x :x]]
  (view-vars (unify* a-vec b-vec {} ::bottom)))
;; =>
({"xs" [:x :x], "ys" [:y :y]}
 {"xs" [:x], "ys" [:x :y :y :x]}
 {"xs" [], "ys" [:x :x :y :y :x :x]})
