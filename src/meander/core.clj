(ns meander.core
  (:refer-clojure :exclude [bound?])
  (:require
   [meander.protocols :as protocols]
   [clojure.walk :as walk]
   [clojure.set :as set]))


;; ---------------------------------------------------------------------
;; Internal utilities

(defn fmap
  ([f x]
   {:post [(instance? (class x) %)]}
   (if (satisfies? protocols/IFmap x)
     (protocols/-fmap x f)
     (f x))))


(defn walk
  [inner-f outer-f x]
  (if (satisfies? protocols/IWalk x)
    (protocols/-walk x inner-f outer-f)
    (outer-f x)))


(defn postwalk
  [f x]
  (walk (partial postwalk f) f x))


(defn prewalk
  [f x]
  (walk (partial prewalk f) identity (f x)))


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


(defn bound?
  {:arglists '([variable substitution-map])}
  ([var smap]
   {:pre [(variable? var)
          (map? smap)]}
   (contains? smap var)))


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
    (if-some [[_ x] (find substitution-map this)]
      (if (= x that)
        substitution-map
        bottom)
      ;; Occurs check.
      (if (contains? (variables that) this)
        bottom
        (assoc substitution-map this that))))

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
        (if (contains? (variables that) this)
          bottom
          (assoc substitution-map this that))
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
   (if (every? ground? vector)
     (make-ground-term vector) 
     (VectorTerm. vector))))


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
   (if (every? ground? seq)
     (make-ground-term seq)
     (SeqTerm. seq))))


;; ---------------------------------------------------------------------
;; Map term

(defn map-entry-classifier [entry]
  (case (mapv ground? entry)
    [false true]
    :ground-value

    [true false]
    :ground-key

    [false false]
    :both-variable

    [true true]
    :both-ground))


(defn ground-entry? [entry]
  (= (map-entry-classifier entry)
     :both-ground))


(defn variable-entry? [entry]
  (= (map-entry-classifier entry)
     :both-variable))


(defn ground-key-entry? [entry]
  (contains? #{:both-ground :ground-key} (map-entry-classifier entry)))


(defn ground-value-entry? [entry]
  (contains? #{:both-ground :ground-value} (map-entry-classifier entry)))


(defn unify-entry
  {:private true}
  ([a-entry b-entry substitution-map bottom]
   (let [[a-key a-val] a-entry
         [b-key b-val] b-entry
         substitution-map* (unify a-key b-key substitution-map bottom)]
     (if (identical? substitution-map* bottom)
       bottom
       (unify a-val b-val substitution-map* bottom)))))


(defn unify-ground-entry
  {:private true}
  ([[a b substitution-map bottom]] 
   (let [a-both-ground (filter ground-entry? a)]
     (if (every?
          (fn [[a-key _ :as a-entry]]
            (= a-entry (find b a-key)))
          a-both-ground)
       (let [a* (reduce dissoc a (keys a-both-ground))
             b* (reduce dissoc b (keys a-both-ground))]
         [a* b* substitution-map bottom])
       [a b bottom bottom]))))


(defn unify-ground-value
  {:private true}
  ([[a b substitution-map bottom]]
   (loop [ground-value-entries (filter ground-value-entry? a)
          a a
          b b
          substitution-map substitution-map]
     (if (= substitution-map bottom)
       [a b bottom bottom]
       (if-some [[_ a-value :as a-entry] (first ground-value-entries)]
         (if-some [b-entry (some
                            (fn [[_ b-value :as b-entry]]
                              (when (= b-value
                                       a-value)
                                b-entry))
                            b)]
           (let [a* (dissoc a (key a-entry))
                 b* (dissoc b (key b-entry))
                 substitution-map* (unify-entry a-entry b-entry substitution-map bottom)]
             (recur (next ground-value-entries)
                    a*
                    b*
                    substitution-map*))
           ;; We have no more entries remaining in `b` and at least one
           ;; more variable-keyed entry remaining in `a`, so we fail
           ;; because we have nothing to from `b` with which to unify.
           [a b bottom bottom])
         ;; No more variable-keyed map entries from `a` so there's
         ;; nothing from `a` with which to unify.
         [a b substitution-map bottom])))))


(defn unify-ground-key
  {:private true}
  ([[a b substitution-map bottom]]
   (loop [ground-key-entries (filter ground-key-entry? a)
          a a
          b b
          substitution-map substitution-map]
     (if (= substitution-map bottom)
       [a b bottom bottom]
       (if-some [[a-key a-variable] (first ground-key-entries)]
         (if-some [[b-key b-val] (find b a-key)]
           (let [a* (dissoc a a-key)
                 b* (dissoc b b-key)
                 substitution-map* (unify a-variable b-val substitution-map bottom)]
             (recur (next ground-key-entries)
                    a*
                    b*
                    substitution-map*))
           [a b bottom bottom])
         [a b substitution-map bottom])))))



(defn unify-variable-entry
  {:private true}
  ([[a b substitution-map bottom]]
   (loop [both-variable-entries (filter variable-entry? a)
          a a
          b b
          substitution-map substitution-map]
     (if (= substitution-map bottom)
       [a b bottom bottom]
       (if-some [a-entry (first both-variable-entries)]
         (if-some [b-entry (first b)]
           (let [a* (dissoc a (key a-entry))
                 b* (dissoc b (key b-entry))
                 substitution-map* (unify-entry a-entry b-entry substitution-map bottom)]
             (recur (next both-variable-entries)
                    a*
                    b*
                    substitution-map*))
           [a b bottom bottom])
         [a b substitution-map bottom])))))


(defn unify-map
  {:private true}
  ([a b substitution-map bottom]
   {:pre [(map? a) (map? b)]}
   (case [(empty? a) (empty? b)]
     ;; Since the empty map is a part of any map this always
     ;; succeeds.
     ([true true] [true false])
     substitution-map

     ;; If the map on the left-hand-side is not empty but the map on
     ;; the right-hand-side is then we cannot attempt to unify the
     ;; two.
     [false true]
     bottom
     
     ;; else
     (-> [a b substitution-map bottom]
         (unify-ground-entry)
         (unify-ground-value)
         (unify-ground-key)
         (unify-variable-entry)
         (nth 2)))))


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
         (= (.this map)
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
   (postwalk
    (fn [x]
      (let [x* (apply-rule rule x)]
        (if (identical? x x*)
          x
          (recur x*))))
    term)))


;; ---------------------------------------------------------------------
;; Macros


(defn splicing-form?
  ([x]
   (and (seq? x)
        (= (first x)
           'clojure.core/unquote-splicing))))


(defn coll-with-splicing?
  ([x]
   (and (coll? x)
        (some splicing-form? x))))


(defmacro undefined
  ([]
   `(throw (ex-info "undefined" ~(meta &form)))))


(defn parse-form
  {:arglists '([form environment])}
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
           (make-vector-splicing-substitution vector splice-variables))) 
       
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
                                `(make-variable '~variable))))
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
               (if (pred b)
                 (recur (conj xs a) (subvec v 1))
                 xs))
             (recur (conj xs a) (subvec v 1))))))

     :else
     ^::non-greedy-consume-until-unsupported
     (undefined))))



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
         (distribute-elements-map* coll vars)))))))


#_
(time
  (let [vars '[a a b b c c]
        coll [1 1 1 1 2 2 1 1] 
        var->count (frequencies vars)
        distinct-vars (distinct vars)]
    (distribute-elements-map coll vars)))


#_ ;; ALMOST THERE!!!
(let [bottom ::bottom]
  (loop [a-vector [1 2 '~@as '~@bs '~@cs 9]
         b-vector [1 2 3 4 5 6 7 8 9 9]
         smap {}]
    (if (identical? smap bottom)
      bottom
      (case [(empty? a-vector) (empty? b-vector)]
        [true true]
        smap

        [false false]
        (let [a (first a-vector)
              b (first b-vector)]
          (if (splicing-form? a)
            ;; Is `a` bound?
            (let [variable (make-variable (second a))]
              (if-some [[_ value] (find smap variable)]
                (if (vector? value)
                  (if (< (count b-vector)
                         (count value))
                    bottom
                    (let [b-slice (subvec b-vector 0 (count value))]
                      (if (= value b-slice)
                        (let [a-vector* (subvec a-vector 1)
                              b-vector* (subvec b-vector (count value))]
                          (recur a-vector* b-vector* smap))
                        bottom)))
                  ;; If `a` is not bound to a sequential value we cannot
                  ;; unify it.
                  bottom)
                ;; `a` is not bound.
                (let [no-neighbor (Object.)
                      a-neighbor (get a-vector 1 no-neighbor)]
                  ;; Cases:
                  (cond
                    ;; There is no other value after `a` in `a-vector`.
                    (identical? a-neighbor no-neighbor)
                    (assoc smap variable b-vector)

                    ;; The value after `a` in `a-vector` is a splicing variable.
                    (splicing-form? a-neighbor)
                    (if-some [i (index-of (complement splicing-form?) a-vector)]
                      (let [x? #{(get a-vector i)}]
                        (if (index-of x? b-vector)
                          (let [;; Promote splicing forms to variables.
                                variables (map
                                           (fn [[_ symbol]]
                                             (make-variable symbol))
                                           (subvec a-vector 0 i))]
                            (if (every? #{1} (vals (frequencies variables)))
                              (if-some [v (some
                                           (fn [v]
                                             (bound? v smap))
                                           variables)]
                                (undefined)
                                ;; None of the splicing variables are
                                ;; bound so we distribute elements from
                                ;; a slice of `b-vector` fairly amongst
                                ;; them.
                                (let [a-vector* (subvec a-vector i)
                                      b-slice (non-greedy-consume-until x? b-vector)
                                      b-vector* (subvec b-vector (count b-slice))
                                      smap*
                                      (into smap
                                            (distribute-elements-fairly-over-distinct-unbound-splicing-variables
                                             b-slice
                                             variables))]
                                  (recur a-vector*
                                         b-vector*
                                         smap*)))
                              (undefined)))
                          bottom))
                      (if (every? #{1} (vals (frequencies a-vector)))
                        (let [variables (map
                                         (fn [[_ symbol]]
                                           (make-variable symbol))
                                         a-vector)]
                          (if-some [v (some
                                       (fn [v]
                                         (bound? v smap))
                                       variables)]
                            (undefined)
                            (let [smap*
                                  (into smap
                                        (distribute-elements-fairly-over-distinct-unbound-splicing-variables
                                         b-vector
                                         variables))]
                              smap*)))))
                    ;; There is value after `a` in `a-vector`.
                    ;;   The value after `a` in `a-vector` is ground.
                    ;;     Take values from `b-vector` while they do not equal
                    ;;     the value after `a` in `a-vector`.
                    (ground? a-neighbor)
                    (let [a-vector* (subvec a-vector 1)
                          values (non-greedy-consume-until #{a-neighbor} b-vector)
                          b-vector* (subvec b-vector (count values))
                          smap* (assoc smap variable values)]
                      (recur a-vector* b-vector* smap*))

                    ;; The value after `a` in `a-vector` is a variable.
                    ;;   If that variable is bound then take values from
                    ;;   `b-vector` while they do not equal the value
                    ;;   bound to that variable. These values will be
                    ;;   bound to the splicing variable `a`.
                    ;;
                    ;;   If that variable is not bound what do we do?
                    (variable? a-neighbor)
                    (if-some [[_ a-neighbor-value] (find smap a-neighbor)]
                      (let [a-vector* (subvec a-vector 1)
                            values (non-greedy-consume-until #{a-neighbor-value} b-vector)
                            b-vector* (subvec b-vector (count values))
                            smap* (assoc smap variable values)]
                        (recur a-vector* b-vector* smap*))
                      ^::unbound-variable-neighbor
                      (undefined))

                    :else
                    (undefined)))))
            (let [a-vector* (subvec a-vector 1)
                  b-vector* (subvec b-vector 1)
                  smap* (unify a b smap bottom)]
              (recur a-vector* b-vector* smap*))))
        bottom))))
