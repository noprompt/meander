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


(deftype GroundTerm [x]
  protocols/IForm
  (-form [_]
    x)

  protocols/IFmap
  (-fmap [_ f]
    (GroundTerm. (f x)))

  protocols/ITermVariables
  (-term-variables [_]
    #{})

  protocols/IUnify
  (-unify [_ that substitution-map bottom]
    (unify x that substitution-map bottom))

  Object
  (equals [_ that]
    (and (instance? GroundTerm that)
         (= x x))) 

  (hashCode [_]
    (.hashCode x)))


(defn make-ground-term
  "Return an object which contains no term variables and unifies with
  any object that unifies with `x`."
  ([x]
   x
   #_
   (GroundTerm. x)))

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
  (-unify [this that substitution-map bottom]
    (cond
      (vector? that)
      (if-some [[_ x] (find substitution-map this)]
        (if (= that x)
          substitution-map
          bottom)
        (loop [this-vector vector
               that-vector that
               substitution-map substitution-map]
          (if (empty? this-vector)
            (if (empty? that-vector)
              (assoc substitution-map this that)
              bottom)
            (let [a (peek this-vector)
                  b (peek that-vector)
                  substitution-map* (unify a b substitution-map bottom)]
              (if (= substitution-map*
                     bottom)
                bottom
                (let [this-vector* (pop this-vector)
                      that-vector* (pop that-vector)]
                  (recur this-vector*
                         that-vector*
                         substitution-map*)))))))

      (instance? VectorTerm that)
      (protocols/-unify this (.vector that) substitution-map bottom)

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
   (VectorTerm. vector)
   #_
   (if (every? ground? vector)
     (make-ground-term vector) 
     )))


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
  (-unify [this that substitution-map bottom]
    (cond
      (seq? that)
      (if-some [[_ x] (find substitution-map this)]
        (if (= that x)
          substitution-map
          bottom)
        (loop [this-seq (.-seq this)
               that-seq that
               substitution-map substitution-map]
          (if (empty? this-seq)
            (if (empty? that-seq)
              (assoc substitution-map this that)
              bottom)
            (let [a (first this-seq)
                  b (first that-seq)
                  substitution-map* (unify a b substitution-map bottom)]
              (if (= substitution-map*
                     bottom)
                bottom
                (let [this-seq* (rest this-seq)
                      that-seq* (rest that-seq)]
                  (recur this-seq* that-seq* substitution-map*)))))))

      (instance? SeqTerm that)
      (protocols/-unify this (.-seq that) substitution-map bottom)

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
   (SeqTerm. seq)
   #_
   (if (every? ground? seq)
     (make-ground-term seq)
     (SeqTerm. seq))))


;; ---------------------------------------------------------------------
;; Map term

#_
(defn unify-entry
  {:private true}
  ([e-a e-b smap bottom]
   (let [[k-a v-a] e-a
         [k-b v-b] e-b
         smap* (unify k-a k-b smap bottom)]
     (if (identical? smap* bottom)
       bottom
       (unify v-a v-b smap* bottom)))))

#_
(defn unify-entries
  {:private true}
  ([pairs smap bottom]
   (if-some [[[e-a e-b] & pairs*] (seq pairs)]
     (let [smap* (unify-entry e-a e-b smap bottom)]
       (if (identical? smap* bottom)
         bottom
         (recur pairs* smap* bottom)))
     smap)))


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
            (let [entries (partition 2 (interleave map-b !map-a))
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


#_
(defn view-vars
  [form]
  (walk/postwalk
   (fn [x]
     (if (variable? x)
       (name x)
       x))
   form))


(extend-protocol protocols/IUnify
  clojure.lang.IPersistentMap
  (-unify [this that smap bottom]
    (unify-map this that smap bottom)))


(extend-protocol protocols/IUnify*
  clojure.lang.IPersistentMap
  (-unify* [this that smap bottom]
    (unify-map* this that smap bottom)))


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
  (-substitute [_ substitution-map]
    (MapTerm.
     (reduce-kv
      (fn [m k v]
        (assoc m (substitute k substitution-map) (substitute v substitution-map)))
      {}
      map)))

  protocols/ITermVariables
  (-term-variables [_]
    (reduce set/union #{} (clojure.core/map variables (mapcat identity map))))

  protocols/IUnify
  (-unify [this that substitution-map bottom]
    (cond
      (map? that)
      (if-some [[_ x] (find substitution-map this)]
        (if (= that x)
          substitution-map
          bottom)
        (let [substitution-map* (unify-map map that substitution-map bottom)]
          (if (identical? substitution-map* bottom)
            bottom
            (assoc substitution-map* this that))))

      (instance? MapTerm that)
      (protocols/-unify this (.map that) substitution-map bottom)

      :else
      bottom))

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
     (make-ground-term map)
     (MapTerm. map))))


;; ---------------------------------------------------------------------
;; Pure substitutions


(deftype SeqSplicingSubstitution [seq splice-variables]
  protocols/ITermVariables
  (-term-variables [this]
    (reduce set/union #{} (map variables (.-seq this))))

  protocols/ISubstitute
  (-substitute [this substitution-map]
    (make-seq-term
     (doall
      (mapcat
       (fn [x]
         (if (contains? splice-variables x)
           (let [ys (get substitution-map x ::not-found)]
             (cond
               (identical? ys ::not-found)
               (ex-info "Missing substitution for splice variable"
                        {:variable x})

               (or (coll? ys)
                   (nil? ys))
               ys

               :else
               (ex-info "Splicing variable not bound to a collection"
                        {:value x})))
           (list (substitute x substitution-map))))
       (.-seq this))))))


(defn make-seq-splicing-substitution
  ([seq splice-variables]
   {:pre [(seq? seq)
          (set? splice-variables)
          (every? variable? splice-variables)]}
   (SeqSplicingSubstitution. seq splice-variables)))


(deftype VectorSplicingSubstitution [vector splice-variables]
  protocols/ITermVariables
  (-term-variables [this]
    (reduce set/union #{} (mapv variables (.-vector this))))

  protocols/ISubstitute
  (-substitute [this substitution-map]
    (make-vector-term
     (reduce
      (fn [v x]
        (if (contains? splice-variables x)
          (let [ys (get substitution-map x ::not-found)]
            (cond
              (identical? ys ::not-found)
              (ex-info "Missing substitution for splice variable"
                       {:variable x})

              (or (coll? ys)
                  (nil? ys))
              (into v ys)

              :else
              (ex-info "Splicing variable not bound to a collection"
                       {:value x})))
          (conj v (substitute x substitution-map))))
      []
      (.-vector this)))))


(defn make-vector-splicing-substitution
  ([vector splice-variables]
   {:pre [(vector? vector)
          (set? splice-variables)
          (every? variable? splice-variables)]}
   (VectorSplicingSubstitution. vector splice-variables)))


;; ---------------------------------------------------------------------
;; Vector splicing term
;;
;; Given the term
;;
;;   [~@as ~@bs ~@as]
;;
;; what are the possible solutions that would satisfy unification for
;; the form
;;
;;   [x x a b x x]
;; ?
;;
;;   1. { as ↦ [], bs ↦ [x x a b x x] }
;;   2. { as ↦ [x], bs ↦ [x a b x] }
;;   3. { as ↦ [x x], bs ↦ [a b] } 
;;   4. { as ↦ [x x a b x x], bs ↦ [] }
;;
;; While each of these solutions are fine, 3 is the result we want
;; because it is the least greedy. Each variable gets a fair slice of
;; the vector.


(defn non-greedy-consume-until
  {:private true}
  ([pred coll]
   (cond
     (vector? coll)
     (loop [xs []
            v coll]
       (if (empty? v)
         xs
         (let [a (first v)]
           (if (pred a)
             (let [nothing (Object.)
                   b (get v 1 nothing)]
               (if (identical? b nothing)
                 xs
                 (if (pred b)
                   (recur (conj xs a) (subvec v 1))
                   xs)))
             (recur (conj xs a) (subvec v 1))))))

     :else
     ^::non-greedy-consume-until-unsupported
     (undefined))))

(non-greedy-consume-until even? [1 2 2])


(defn index-of
  "Return the index of first element satisfying `pred` in `coll` and
  `nil` no such element exists."
  {:private true}
  ([pred coll]
   (some
    (fn [[i x]]
      (when (pred x)
        i))
    (map-indexed vector coll))))


(defn distribute-elements-fairly
  "Internal strategy for partitioning collection values \"fairly\"
  over unbound, distinct splicing variables. Returns a sequence of
  `[var elements]` pairs where `var` is a variable and `elements` are
  the partitioned elements from `coll`."
  {:arglists '([coll variables])
   :private true}
  ([coll vars]
   {:pre [(= vars (distinct vars))]}
   (let [|coll| (count coll)
         |vars| (count vars)]
     (when (<= |vars| |coll|)
       (let [;; Every variable gets `q` elements.
             q (quot |coll| |vars|)
             vars+counts (map vector vars (repeat q))
             ;; The remaining `r` elements are distributed fairly
             ;; until exhausted.
             r (rem |coll| |vars|)
             vars+counts (concat
                          (map
                           (fn [[variable amount]]
                             [variable (inc amount)])
                           (take r vars+counts))
                          (drop r vars+counts))
             vars+elements (loop [vars+counts vars+counts
                                  coll (vec coll)
                                  vars+elements {}]
                             (if-some [[[var count] & vars+counts*] (seq vars+counts)]
                               (let [elements (subvec coll 0 count)
                                     var+elements [var elements]
                                     vars+elements* (conj vars+elements var+elements)
                                     coll* (subvec coll count)]
                                 (recur vars+counts*
                                        coll*
                                        vars+elements*))
                               vars+elements))]
         vars+elements)))))


(defn indexes-of-subsequence
  {:private true}
  ([subseq coll]
   (keep
    (fn [pairs]
      (when (= (map second pairs)
               subseq)
        (ffirst pairs)))
    (partition (count subseq) 1 (map-indexed vector coll)))))


(defn slice-out-vec
  {:private true}
  ([slice v]
   (slice-out-vec slice v Float/POSITIVE_INFINITY))
  ([slice v limit]
   (let [|slice| (count slice)]
     (loop [v v
            i 0
            result []]
       (if (or (< (count v) |slice|)
               (= i limit))
         (into result v)
         (let [v-slice (subvec v 0 |slice|)]
           (if (= v-slice slice)
             (recur (subvec v |slice|)
                    (inc i)
                    result)
             (recur (subvec v 1)
                    i
                    (conj result (nth v 0))))))))))


(defn partitioned-indexes-of-subsequence
  {:private true}
  ([subseq coll]
   (let [|subseq| (count subseq)]
     (loop [indexes (indexes-of-subsequence subseq coll)
            result []]
       (case (count indexes)
         0
         result

         1
         (conj result (first indexes))
         
         ;; else
         (let [[a b] (take 2 indexes)]
           (if (<= |subseq| (- b a))
             (recur (drop 2 indexes)
                    (conj result a b))
             (recur (cons a (drop 2 indexes))
                    result))))))))


;; TODO: This needs a better name.
(defn find-slices
  {:private true}
  ([n coll]
   (if (zero? n)
     []
     (let [slices (map
                   (fn [i]
                     (let [slice (vec (take (inc i) coll))
                           indexes (partitioned-indexes-of-subsequence slice coll)]
                       (when (<= n (count indexes))
                         slice)))
                   (range (/ (count coll) n)))]
       (cons [] (take-while some? slices))))))


(defn cartesian-product
  "All the ways to take one item from each sequence"
  {:private true}
  ([seqs]
   (let [v-original-seqs (vec seqs)
         step
         (fn step [v-seqs]
           (let [increment
                 (fn [v-seqs]
                   (loop [i (dec (count v-seqs))
                          v-seqs v-seqs]
                     (if (= i -1) nil
                         (if-let [rst (next (v-seqs i))]
                           (assoc v-seqs i rst)
                           (recur (dec i)
                                  (assoc v-seqs i (v-original-seqs i)))))))]
             (when v-seqs
               (cons (map first v-seqs)
                     (lazy-seq (step (increment v-seqs)))))))]
     (when (every? seq seqs)
       (lazy-seq (step v-original-seqs))))))


(defn partitioned-slice-out
  {:private true}
  ([slice coll]
   (let [indexes (rseq (partitioned-indexes-of-subsequence slice coll))
         |slice| (count slice)]
     (reduce
      (fn [coll index]
        (into (subvec coll 0 index)
              (subvec coll (+ index |slice|))))
      coll
      indexes)))
  ([slice limit coll]
   (let [indexes (reverse (take limit (partitioned-indexes-of-subsequence slice coll)))
         |slice| (count slice)]
     (reduce
      (fn [coll index]
        (into (subvec coll 0 index)
              (subvec coll (+ index |slice|))))
      coll
      indexes))))


(defn ???
  {:private true}
  ([coll vars]
   (let [var-counts (frequencies vars)

         distinct-vars (distinct vars)

         initial-state
         (let [var (first distinct-vars)
               n (get var-counts var)
               slices (find-slices n coll)
               colls (mapv
                      (fn [slice]
                        (partitioned-slice-out slice n coll))
                      slices)]
           [{:colls colls
             :count n
             :slices slices
             :var var}])

         info
         (reduce
          (fn [state var]
            (let [n (get var-counts var)
                  last-colls (get (peek state) :colls) 
                  slices (vec (mapcat
                               (fn [coll]
                                 (find-slices n coll))
                               last-colls))
                  colls (mapv
                         (fn [slice coll]
                           (partitioned-slice-out slice n coll))
                         slices
                         last-colls)]
              (conj state {:colls colls
                           :count n
                           :slices slices
                           :var var})))
          initial-state
          (next distinct-vars))]
     info)))


(defn distribute-elements-map-solutions
  {:arglists '([coll variables])
   :private true}
  ([coll vars]
   (let [info (??? coll vars)]
     (keep
      (fn [slices]
        (let [index (reduce conj {} (map vector (map :var info) slices))]
          (when (= (mapcat index vars)
                   coll)
            index)))
      (distinct
       (cartesian-product
        (map :slices info)))))))


(defn distribute-elements-map
  {:arglists '([coll variables])
   :private true}
  ([coll vars]
   (let [distinct-vars (distinct vars)]
     (if (= vars distinct-vars)
       ;; If all of the variables are distinct we can use a less
       ;; expensive algorithm to distribute the values fairly.
       (distribute-elements-fairly coll vars)
       ;; Choose the solution where the total distances between the
       ;; magnitudes of slices is the lowest i.e. the solution with
       ;; the values most fairly distributed.
       ;;
       ;; TODO: This function's execution time increases exponentially
       ;; with the number of `variables`.
       (first
        (sort-by
         (fn [solution]
           (let [distances (mapv
                            (fn [[a b]]
                              (Math/abs (- a b)))
                            (partition 2 1 (map (comp count solution) distinct-vars)))]
             ;; Sum the distances.
             (reduce + distances)))
         (distribute-elements-map-solutions coll vars)))))))


(defn vsplit-at
  "Like `clojure.core/split-at` but for vectors."
  {:private true}
  ([n v]
   {:pre [(vector? v)]}
   (let [i (min n (count v))]
     [(subvec v 0 i) (subvec v i)])))


(defn splicing-form?
  ([x]
   (and (seq? x)
        (= (first x)
           'clojure.core/unquote-splicing))))


(defn resolve-splicing-form
  ([splicing-form smap not-found]
   {:pre [(splicing-form? splicing-form)]}
   (if-some [[_ x] (find smap (make-variable (second splicing-form)))]
     x
     not-found)))


(defn unify-splicing-vector-head
  ([a-vec b-vec smap bottom]
   (if (identical? smap bottom)
     bottom
     (case [(empty? a-vec) (empty? b-vec)]
       [true true]
       [a-vec b-vec smap bottom]

       [false false]
       (let [a (first a-vec)
             b (first b-vec)]
         (if (splicing-form? a)
           (let [not-found (nothing)
                 value (resolve-splicing-form a smap not-found)]
             (if (identical? value not-found)
               [a-vec b-vec smap bottom]
               (if (sequential? value)
                 (if (< (count b-vec)
                        (count value))
                   bottom
                   (let [[b-slice b-vec*] (vsplit-at (count value) b-vec)]
                     (if (= value b-slice)
                       (let [a-vec* (subvec a-vec 1)]
                         (recur a-vec* b-vec* smap bottom))
                       bottom)))
                 ;; If `a` is not bound to a sequential value we
                 ;; cannot unify it.
                 bottom)))
           (let [smap* (unify a b smap bottom)]
             (if (identical? smap* bottom)
               bottom
               (let [a-vec* (subvec a-vec 1)
                     b-vec* (subvec b-vec 1)]
                 (recur a-vec* b-vec* smap* bottom))))))

       [true false]
       bottom

       [false true]
       (let [a (first a-vec)]
         (if (splicing-form? a)
           (let [v (make-variable (second a))
                 v-val (resolve v smap)]
             (cond 
               (or (identical? v-val v)
                   (= v-val []))
               (let [smap* (extend-no-check smap v [])
                     a-vec* (subvec a-vec 1)]
                 (recur a-vec* b-vec smap* bottom))

               :else
               bottom))
           bottom))))))

(defn unify-splicing-vector-tail
  ([a-vec b-vec smap bottom]
   (if (identical? smap bottom)
     bottom
     (case [(empty? a-vec) (empty? b-vec)]
       [true true]
       [a-vec b-vec smap bottom]

       [false false]
       (let [a (peek a-vec)
             b (peek b-vec)]
         (if (splicing-form? a)
           (let [not-found (nothing)
                 value (resolve-splicing-form a smap not-found)]
             (if (identical? value not-found)
               [a-vec b-vec smap bottom]
               (if (sequential? value)
                 (if (< (count b-vec)
                        (count value))
                   bottom
                   (let [[b-vec* b-slice] (vsplit-at (- (count b-vec)
                                                        (count value))
                                                     b-vec)]
                     (if (= value b-slice)
                       (let [a-vec* (pop a-vec)]
                         (recur a-vec* b-vec* smap bottom))
                       bottom)))
                 ;; If `a` is not bound to a sequential value we
                 ;; cannot unify it.
                 bottom)))
           (let [smap* (unify a b smap bottom)]
             (if (identical? smap* bottom)
               bottom
               (let [a-vec* (pop a-vec)
                     b-vec* (pop b-vec)]
                 (recur a-vec* b-vec* smap* bottom))))))

       [true false]
       bottom

       ;; There may be more work to do here.
       [false true]
       (let [a (peek a-vec)]
         (if (splicing-form? a)
           (let [v (make-variable (second a))
                 v-val (resolve v smap)]
             (cond 
               (or (identical? v-val v)
                   (= v-val []))
               (let [smap* (extend-no-check smap v [])
                     a-vec* (pop a-vec)]
                 (recur a-vec* b-vec smap* bottom))

               :else
               bottom))
           bottom))))))

(defn unify-splicing-vector-head-and-tail
  ([a-vec b-vec smap bottom]
   (let [x (unify-splicing-vector-head a-vec b-vec smap bottom)]
     (if (identical? x bottom)
       bottom
       (let [[a-vec* b-vec* smap* _] x]
         (unify-splicing-vector-tail a-vec* b-vec* smap* bottom))))))


;; TODO: This fails to unify the term
;;
;;    [x xs ys x ys xs]
;;
;; with
;;
;;   [:x 4 5 :x 4 5]
;;
;; where `x` is a logic variables, and `xs` and `ys` are subsequene
;; logic variables.
(defn unify-splicing-vector
  {:arglists '([a-vec b-vec substitution-map bottom])
   :private true}
  ([a-vec b-vec smap bottom]
   (let [x (unify-splicing-vector-head-and-tail a-vec b-vec smap bottom)]
     (if (identical? x bottom)
       bottom
       (let [[a-vec b-vec smap _] x]
         (case [(empty? a-vec) (empty? b-vec)]
           [true true]
           smap

           [false false]
           (let [a (first a-vec)
                 b (first b-vec)]
             (if (splicing-form? a)
               (if-some [[_ value] (find smap (make-variable (second a)))]
                 (if (sequential? value)
                   (if (< (count b-vec)
                          (count value))
                     bottom
                     (let [[b-slice b-vec*] (vsplit-at (count value) b-vec)]
                       (if (= value b-slice)
                         (let [a-vec* (subvec a-vec 1)]
                           (recur a-vec* b-vec* smap bottom))
                         bottom)))
                   ;; If `a` is not bound to a sequential value we cannot
                   ;; unify it.
                   bottom)
                 ;; If `a` is not bound we attempt to find the index of
                 ;; the next bound subsequence logic variable or
                 ;; non-unbound subsequence logic variable.
                 (if-some [i (index-of
                              (fn [x]
                                (if (splicing-form? x)
                                  (let [variable (make-variable (second a))]
                                    (bound? variable smap)) 
                                  true))
                              a-vec)]
                   (let [x (get a-vec i)]
                     (cond
                       (ground? x)
                       (let [[a-slice a-vec*] (vsplit-at i a-vec)
                             b-slice (non-greedy-consume-until #{x} b-vec)
                             slice-smap (unify-splicing-vector a-slice b-slice smap bottom)]
                         (if (identical? slice-smap bottom)
                           bottom
                           (let [b-vec* (subvec b-vec (count b-slice))
                                 smap* (merge smap slice-smap)]
                             (recur a-vec* b-vec* smap* bottom))))

                       (variable? x)
                       (let [value (resolve x smap)]
                         (if (not (identical? x value))
                           (if-some [j (index-of #{value} b-vec)]
                             (let [[a-slice a-vec*] (vsplit-at i a-vec)
                                   [b-slice b-vec*] (vsplit-at j b-vec)
                                   slice-smap (unify-splicing-vector a-slice b-slice smap bottom)]
                               (if (identical? slice-smap bottom)
                                 bottom
                                 (let [b-vec* (subvec b-vec (count b-slice))
                                       smap* (merge smap slice-smap)]
                                   (recur a-vec* b-vec* smap* bottom))))
                             bottom)
                           (let [v (make-variable (second a))
                                 x-val (first b-vec)
                                 smap* (extend-no-check smap v [])
                                 smap* (extend-no-check smap* x b-vec)
                                 a-vec* (subvec a-vec (inc i))
                                 b-vec* (subvec b-vec 1)]
                             (recur a-vec* b-vec* smap* bottom)
                             #_
                             (undefined)))) 

                       (splicing-form? x)
                       (undefined)
                       #_
                       (let [variable (make-variable (second a))]
                         (if-some [[_ value] (find smap variable)]
                           (if-some [_ (index-of-subsequence value b-vec)]
                             (let [[a-slice a-vec*] (vsplit-at i a-vec)
                                   b-vec* (slice-out-vec value b-vec)]
                               [a-vec* b-vec* smap bottom])
                             bottom)
                           ;; This should not happen.
                           (undefined))) 

                       :else
                       (undefined)))
                   (let [vars (mapv (comp make-variable second) a-vec)]
                     (merge smap (distribute-elements-map b-vec vars)))))
               (let [a-vec* (subvec a-vec 1)
                     b-vec* (subvec b-vec 1)
                     smap* (unify a b smap bottom)]
                 (recur a-vec* b-vec* smap* bottom))))

           [true false]
           bottom

           [false true]
           (loop [a-vec a-vec
                  smap smap]
             (if (or (identical? smap bottom)
                     (empty? a-vec))
               smap
               (let [a (first a-vec)]
                 (if (splicing-form? a)
                   (let [variable (make-variable (second a))]
                     (if-some [_ (find smap variable)]
                       bottom
                       (let [a-vec* (subvec a-vec 1)
                             smap* (assoc smap variable [])]
                         (recur a-vec* smap*))))
                   bottom))))))))))


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
  (-unify [this that substitution-map bottom]
    (cond
      (vector? that)
      (unify-splicing-vector (.vector this) that substitution-map bottom)

      (instance? VectorSplicingTerm that)
      (protocols/-unify this (.vector that) substitution-map bottom)

      :else
      bottom))

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
  ([vector splice-variables]
   {:pre [(vector? vector)]}
   (VectorSplicingTerm. vector splice-variables)))



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
   (let [f (fn [x]
             (let [x* (apply-rule rule x)]
               (if (identical? x x*)
                 x
                 (recur x*))))]
     (if (satisfies? protocols/IWalk term)
       (postwalk f term)
       (clojure.walk/postwalk f term)))))


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
                         #_
                         (if-some [[_ variable] (find env (second x))]
                           (do
                             (vswap! splice-variables! conj variable)
                             variable)
                           (undefined))
                         (parse-form x env)))
                     form)
             splice-variables @splice-variables!]
         (if (empty? splice-variables)
           (make-vector-term vector)
           (make-vector-splicing-term vector splice-variables))) 
       
       (seq? form)
       (make-seq-term
        (map parse-form form (repeat env)))

       :else
       form))))


(defmacro fresh
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
  ([variables lhs rhs]
   `(fresh ~variables
      (let [env# (hash-map ~@(mapcat
                              (fn [variable]
                                `('~variable ~variable))
                              variables))]
        (make-rule (parse-form '~lhs env#)
                   (parse-form '~rhs env#))))))

#_
(let [x (make-variable 'x)
      y (make-variable 'y)
      a-vec [x '=> y '~@ys]
      b-vec '[1 => 2
              (conj [1 2] 3) => [1 2 3]
              [foo => bar]]
      smap {}
      bottom :bottom]
  (run-rule
   (rule [x y]
     [x => y ~@ys]
     [~@ys (is (= x y))])
   b-vec)
  )



#_
(let [bottom ::bottom
      ?xs (make-variable 'xs)
      ?ys (make-variable 'ys)
      ?x (make-variable 'x)
      ;; FAILS!!!
      a-vec [?x '~@xs '~@ys ?x '~@xs '~@ys]
      b-vec [1 2 3 4 1 2 :a :b]
      smap {}
      term (parse-form '[~@xs ~@ys ~@xs ~@zs]
                       {'?x ?x})]
  (unify term b-vec smap bottom)
  #_
  (unify-splicing-vector a-vec b-vec smap bottom))
