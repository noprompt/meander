(ns meander.core
  (:refer-clojure :exclude [bound? extend resolve])
  (:require
   [clojure.set :as set]
   [clojure.walk :as walk]
   [meander.protocols :as protocols]
   [meander.util :as util]
   [meander.kahn :as kahn]
   [taoensso.tufte :as tufte]))


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


(defn splicing-form?
  ([x]
   (and (seq? x)
        (= (first x)
           'clojure.core/unquote-splicing))))


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
  {:arglists '([[binding u v substitution-map bottom?] then else?])
   :style/indent 1}
  ([[smap* u v smap bottom] then]
   `(if-unifies ~[smap* u v smap bottom] ~then nil))
  ([[smap* u v smap bottom] then else]
   `(let [bottom# ~(if bottom bottom `(Object.))
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

  protocols/IVariable

  protocols/ISubstitute
  (-substitute [this substitution-map]
    (resolve this substitution-map))

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
  (-unify [this that smap bottom]
    (if-some [_ (find smap this)]
      (let [x (resolve this smap)]
        (if (coll? x)
          (let [y (resolve that smap)]
            (if (= x y)
              smap
              bottom))
          bottom))
      (if (coll? that)
        (extend smap this that bottom)
        bottom)))

  Object
  (equals [_ that]
    (and (instance? SplicingVariable that)
         (= name (.name that))))

  (hashCode [_]
    (.hashCode name)))


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
  ([a-vars b-vec smap bottom]
   (let [;; a-vars (map (comp make-variable second) a-vars)
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


(defn unify-splicing-vector*
  ([a-vec b-vec smap bottom]
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
                      (f a-partition b-partition smap bottom)))
                  unify*
                  (partition 2
                             (interleave a-partitions
                                         b-partitions))))
                smap))
             b-partitions*)))
        (unify* a-left b-left smap bottom)))
     ())))


(defn unify-vector
  ([u-vec v-vec smap bottom]
   {:pre [(vector? u-vec)]}
   (if (vector? v-vec)
     (if-some [[_ x] (find smap u-vec)]
       (if (= v-vec x)
         smap
         bottom)
       (loop [u-vec u-vec
              v-vec v-vec
              smap smap]
         (case [(empty? u-vec) (empty? v-vec)]
           [true true]
           smap

           [true false]
           bottom

           [false true]
           bottom

           [false false]
           (let [a (peek u-vec)
                 b (peek v-vec)]
             (if-unifies [smap* a b smap bottom]
               (let [u-vec* (pop u-vec)
                     v-vec* (pop v-vec)]
                 (recur u-vec*
                        v-vec*
                        smap*))
               bottom)))))
     bottom)))


(defn unify-vector*
  ([u-vec v-vec smap bottom]
   {:pre [(vector? u-vec)]}
   (if (some splicing-variable? u-vec)
     (unify-splicing-vector* u-vec v-vec smap bottom)
     (let [smap* (unify-vector u-vec v-vec smap bottom)]
       (if (identical? smap* bottom)
         ()
         (list smap*))))))


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
             (throw
              (ex-info "Missing substitution for splice variable."
                       {:variable x}))

             (or (coll? ys) (nil? ys))
             (into v ys)

             :else
             (throw
              (ex-info "Splicing variable not bound to a collection."
                       {:value ys}))))
         (conj v (substitute x smap))))
     []
     this))


  protocols/IUnify*
  (-unify* [this that smap bottom]
    (unify-vector* this that smap bottom))

  
  protocols/IUnify
  (-unify [this that smap bottom]
    (or (first (protocols/-unify* this that smap bottom))
        bottom))


  protocols/IWalk
  (-walk [this inner-f outer-f]
    (outer-f (protocols/-fmap this inner-f))))


;; ---------------------------------------------------------------------
;; SeqTerm


(defn unify-splicing-seq*
  ([a-seq b-seq smap bottom]
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
                      (f a-partition b-partition smap bottom)))
                  unify*
                  (partition 2
                             (interleave a-partitions
                                         b-partitions))))
                smap))
             b-partitions*)))
        (unify* a-left b-left smap bottom)))
     ())))



(defn unify-seq
  ([u-seq v-seq smap bottom]
   {:pre [(seq? u-seq)]}
   (if (seq? v-seq)
     (if-some [[_ x] (find smap u-seq)]
       (if (= v-seq x)
         smap
         bottom)
       (loop [u-seq-seq u-seq
              v-seq-seq v-seq
              smap smap]
         (case [(empty? u-seq-seq) (empty? v-seq-seq)]
           [true true]
           smap

           [true false]
           bottom

           [false true]
           bottom

           [false false]
           (let [a (first u-seq-seq)
                 b (first v-seq-seq)]
             (if-unifies [smap* a b smap bottom]
               (let [u-seq-seq* (rest u-seq-seq)
                     v-seq-seq* (rest v-seq-seq)]
                 (recur u-seq-seq* v-seq-seq* smap*))
               bottom)))))
     bottom)))


(defn unify-seq*
  ([u-seq v-seq smap bottom]
   {:pre [(seq? u-seq)]}
   (if (seq? v-seq)
     (if (some splicing-variable? u-seq)
       (unify-splicing-seq* u-seq v-seq smap bottom)
       (let [smap* (unify-seq u-seq v-seq smap bottom)]
         (if (identical? smap* bottom)
           ()
           (list smap*))))
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
  (-unify* [this that smap bottom]
    (unify-seq* this that smap bottom))


  protocols/IUnify
  (-unify [this that smap bottom]
    (or (first (protocols/-unify* this that smap bottom))
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
  "Return a lazy seq of all possible substitutions for `map-a` and
  `map-b`."
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


(extend-type clojure.lang.IPersistentMap
  protocols/IFmap
  (-fmap [this f]
    (reduce-kv
     (fn [m k v]
       (assoc m (fmap f k) (fmap f v)))
     {}
     this))

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
  (-unify [this that smap bottom]
    (or (first (protocols/-unify* this that smap bottom))
        bottom))


  protocols/IUnify*
  (-unify* [this that smap bottom]
    (unify-map* this that smap bottom))


  protocols/IWalk
  (-walk [this inner-f outer-f]
    (outer-f
     (protocols/-fmap this (comp outer-f inner-f)))))


;; ---------------------------------------------------------------------
;; Rule construction


(defn left-hand-side
  ([rule]
   (protocols/-rule-left-hand-side rule)))


(defn right-hand-side
  ([rule]
   (protocols/-rule-right-hand-side rule)))


(defn make-rule
  {:arglists '([left-hand-side right-hand-side])}
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
     (-unify [_ t smap bottom]
       (unify lhs t smap bottom))


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
     (-unify [this x smap bottom]
       (if (and (seq? x)
                (= (first x) fsym))
         (assoc smap this x)
         bottom)))))

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
     (-unify [this x smap bottom]
       (if (and (seq? x)
                (= (first x) fsym))
         (assoc smap this x)
         bottom)))))


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
     (-unify [this x smap bottom]
       (if (and (seq? x)
                (= (first x) fsym))
         (assoc smap this x)
         bottom)))))


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

       :else
       [x env]))))


(defmacro rule
  {:arglists '([[params*] & {:keys [replace with where when]}])
   :style/indent :defn}
  ([params & {:keys [replace with where when]}]
   (let [vars `vars#
         lhs `lhs#
         rhs `rhs#
         smap `smap#
         ;; FIXME: This is a mess.
         [_ env] (parse-form* replace {})
         var-sym (comp symbol name)
         splicing-var-sym?
         (into {}
               (map (juxt var-sym splicing-variable?))
               (set/union (variables env)
                          (variables (parse-form* with env))))
         var-syms (distinct (concat params (map var-sym (variables env))))]
     `(let [splicing-var-sym?# '~splicing-var-sym?
            [~vars env#] (reduce
                          (fn [[vars# env#] param#]
                            (let [var# (if (splicing-var-sym?# param#)
                                         (make-splicing-variable param#)
                                         (make-variable param#))
                                  vars*# (conj vars# var#)
                                  var-form# 
                                  (if (splicing-var-sym?# param#)
                                    (list 'clojure.core/unquote param#)
                                    (list 'clojure.core/unquote-splicing param#))
                                  env*# (assoc env# var-form# var#)]
                              [vars*# env*#]))
                          [[] {}]
                          '~params)
            [~lhs env#] (parse-form* '~replace env#)
            [~rhs env#] (parse-form* '~with env#)]
        (reify
          clojure.lang.IFn
          ~@(for [arglist-tail (take (inc (count params)) (iterate butlast params))]
              `(~'invoke [this# t# ~@arglist-tail]
                (let [smap# (into {} (map vector ~vars [~@arglist-tail]))]
                  (run-rule this# t# smap#))))


          protocols/IRule


          protocols/ISubstitute
          (-substitute [this# ~smap]
            (let [~@(mapcat
                     (fn [var-sym]
                       [var-sym `(resolve
                                  ~(if (splicing-var-sym? var-sym)
                                     `(make-splicing-variable '~var-sym)
                                     `(make-variable '~var-sym))
                                  ~smap)])
                     var-syms)
                  ~@(mapcat
                     (fn [[var val-expr]]
                       (let [var-val (gensym "val__")]
                         [var (if (splicing-var-sym? var)
                                `(make-splicing-variable '~var)
                                `(make-variable '~var))
                          var-val val-expr
                          smap `(assoc ~smap ~var ~var-val)]))
                     (partition 2 where))]
              (substitute ~rhs ~smap)))


          protocols/IUnify
          (-unify [this# t# smap# bottom#]
            (if-unifies [~smap ~lhs t# smap# bottom#]
              (let [~@(mapcat
                       (fn [var-sym]
                         [var-sym `(resolve
                                    ~(if (splicing-var-sym? var-sym)
                                       `(make-splicing-variable '~var-sym)
                                       `(make-variable '~var-sym))
                                    ~smap)])
                       var-syms)]
                (if ~(or when true)
                  ~smap
                  bottom#))
              bottom#)))))))


(defmacro defrule
  {:arglists '([name [params*] & {:keys [replace with]}])
   :style/indent :defn}
  ([name params & rule-args]
   (let [arglists (map (fn [params*]
                         (vec (cons 'term params*)))
                       (take (count params)
                             (iterate butlast
                                      (map-indexed
                                       (fn [i _]
                                         (symbol (str "p-" (inc i))))
                                       params))))
         arglists (cons ['term] (reverse arglists))]
     `(def ~(with-meta name {:arglists `'~arglists})
        (rule ~params ~@rule-args)))))


;; ---------------------------------------------------------------------
;; Scratch

;; TODO: Map unification can be made smarter by
;;   * comparing map sizes, if the LHS is larger than the RHS fail;
;;   * unifying ground keys first.
;;
;; TODO: Rule compilation can be made smarter by checking if the LHS
;; is ground. If so, matching is simply an equality check.
;;
;; TODO: "Real" splicing variables. 

(tufte/add-basic-println-handler! {})

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

  ;; The right side of the rule, the replacement pattern which
  ;; substitution will be appliek.
  :with
  [~z]

  ;; Essentially a `let` between matching and replacing. This allows
  ;; subrules, functions, etc. to be executed before performing the
  ;; substitution to the right side.
  :where
  [z (if (number? x) y x)])


;; This rule will diverge whenever `x` and `y` unify with
;; eachother. This is restricted in term rewriting systems which
;; require termination of rules.
(defrule x=>y [x y]
  :replace
  ~x

  :with
  ~y)

(comment
  (= (x=>y 1 1 2)
     2)

  (= (x=>y (make-variable 't) 1 2)
     2))


(defrule x-y-x=>x-y [x y]
  :replace
  (~x ~y ~x)

  :with
  (~x ~y))


(defrule singleton-do []
  :replace
  (do ~x)

  :with
  x)



(def
  ^{:arglists '([term])}
  associative-do
  (associative-rule do))


(comment
  (= ((rule []
        :when
        (some
         (fn [x]
           (and (seq? x)
                (= 'do (first x))))
         xs)

        :replace
        (do ~@xs)

        :with
        (do ~@ys)

        :where
        [ys (-> (cons 'do xs)
                (associative-do)
                (singleton-do)
                (rest))])
      '(do 1 (do 2 3)))
     '(do 1 2 3)))


(comment
  (= ((rule []
        :when
        (every? (every-pred number? even?) xs)

        :replace
        [~@xs ~@xs]

        :with
        [~@ys]

        :where
        [ys (map str xs)])
      [2 4 2 4])
     ["2" "4"]))

#_
(tufte/profile)

(comment
  (defn label? [x]
    (and (symbol? x)
         (re-matches #"\AL__.+\z" (name x))))


  (defn run-rule-x [rule term]
    (let [!term-map (volatile!
                     {:by-label {}
                      :by-term {}})

          _ (walk/postwalk
             (fn [x]
               (if-some [label (get-in @!term-map [:by-term x])]
                 label
                 (let [label (gensym "L__")]
                   (vswap! !term-map assoc-in [:by-label label] x)
                   (vswap! !term-map assoc-in [:by-term x] label)
                   label)))
             term)

          queue (kahn/kahn-sort
                 (reduce
                  (fn [g [label x]]
                    (if (coll? x)
                      (update g label (fnil into #{}) x)
                      (update g label (fnil conj #{}) x)))
                  {}
                  (:by-label @!term-map)))

          root-node (first queue)

          term*
          (loop [by-label (:by-label @!term-map)
                 queue queue]
            (if (seq queue)
              (let [x (peek queue)]
                (if (label? x)
                  (let [v (loop [v (walk/postwalk-replace by-label (get by-label x))]
                            (let [v* (apply-rule rule v)]
                              (if (= v v*)
                                v
                                (recur v*))))]
                    (recur (assoc by-label x v)
                           (pop queue)))
                  (recur by-label (pop queue))))
              (get by-label root-node)))]
      term*)))
