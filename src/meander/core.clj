(ns meander.core
  (:refer-clojure :exclude [bound? extend resolve])
  (:require
   [clojure.set :as set]
   [clojure.walk :as walk]
   [meander.protocols :as protocols]
   [meander.util :as util]
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
   (satisfies? protocols/IIsVariable x)))


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
   `(if-unifies ~[smap* u v smap bottom] ~then nil))
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

  protocols/ISubstitute
  (-substitute [this substitution-map]
    (resolve this substitution-map))

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


(defn unify-splicing-vector*
  ([a-vec b-vec smap bottom]
   (if (vector? b-vec)
     (let [[a-left a-right] (map vec (split-with (complement splicing-form?) a-vec))
           [b-left b-right] (util/vsplit-at (count a-left) b-vec)]
       (mapcat
        (fn [smap]
          (let [a-partitions (mapv vec (partition-by splicing-form? a-right))
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
   (if (some splicing-form? u-vec)
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
       (if (splicing-form? x)
         (let [x-var (make-variable (second x))
               ys (resolve x-var smap)]
           (cond
             (identical? ys x-var)
             (throw
              (ex-info "Missing substitution for splice variable."
                       {:variable (second x)}))

             (or (coll? ys) (nil? ys))
             (into v ys)

             :else
             (throw
              (ex-info "Splicing variable not bound to a collection."
                       {:value (second x)}))))
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
     (let [[a-left a-right] (split-with (complement splicing-form?) a-seq)
           [b-left b-right] (split-at (count a-left) b-seq)]
       (mapcat
        (fn [smap]
          (let [a-partitions (partition-by splicing-form? a-right)
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
     (if (some splicing-form? u-seq)
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
       (if (splicing-form? x)
         (let [x-var (make-variable (second x))
               ys (resolve x-var smap)]
           (cond
             (identical? ys x-var)
             (throw
              (ex-info "Missing substitution for splice variable"
                       {:variable (second x)}))

             (or (coll? ys) (nil? ys))
             ys

             :else
             (throw
              (ex-info "Splice variable not bound to a collection"
                       {:value (second x)}))))
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
   (if-unifies [smap (left-hand-side rule) term {} (Object.)]
     (substitute (right-hand-side rule) smap)
     term)))


(defn apply-rules
  ([rules term]
   (reduce
    (fn [term* rule]
      (apply-rule rule term*))
    term
    rules)))


(defn run-rule
  ([rule term]
   (let [f (fn f [x]
             (let [x* (apply-rule rule x)]
               (if (= x x*)
                 x
                 (recur x*))))
         ;; Apply the rule to every node in the term's tree.
         term* (postwalk f term)]
     (if (= term term*)
       ;; If nothing has changed we're done.
       term
       ;; If the term has been rewritten we need to run the rule once
       ;; more.
       (recur rule term*)))))


(defn run-rules
  ([rules term]
   (let [f (fn [x]
             (apply-rules rules x))
         ;; Apply the rules to every node in the term's tree.
         term* (postwalk f term)]
     (if (= term term*)
       ;; If nothing has changed we're done.
       term
       ;; If the term has been rewritten we need to run the rule once
       ;; more.
       (recur rules term*)))))

;; ---------------------------------------------------------------------
;; Macros


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
       (mapv (fn [subform]
               (parse-form subform env))
             form)
       
       
       (seq? form)
       (map (fn [subform]
              (parse-form subform env))
            form)

       :else
       form))))


(defmacro vars
  {:style/indent :defn}
  ([variables & body]
   `(let [~@(mapcat
             (fn [variable]
               `(~variable ~(or (when-some [[_ tag] (find (meta variable) :tag)]
                                  (when (symbol? tag)
                                    (when-some [v (clojure.core/resolve tag)]
                                      `(make-conditional-variable '~variable ~tag))))
                                `(make-variable '~variable))))
             variables)]
      ~@body)))

(defn associative-call-r
  ([fsym]
   (reify
     protocols/IUnify
     (-unify [this x smap bottom]
       (if (and (seq? x)
                (= (first x) fsym))
         (assoc smap this x)
         bottom))

     protocols/IRuleLeftHandSide
     (-rule-left-hand-side [this]
       this)

     protocols/IRuleRightHandSide
     (-rule-right-hand-side [this]
       this)

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
         (util/undefined))))))


(defmacro associative-rule
  ([fsym]
   {:pre [(symbol? fsym)]}
   `(associative-call-r '~fsym)))


(defn monoid-call-r
  ([fsym id]
   (reify
     protocols/IUnify
     (-unify [this x smap bottom]
       (if (and (seq? x)
                (= (first x) fsym))
         (assoc smap this x)
         bottom))

     protocols/IRuleLeftHandSide
     (-rule-left-hand-side [this]
       this)

     protocols/IRuleRightHandSide
     (-rule-right-hand-side [this]
       this)

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
         (util/undefined))))))


(defmacro monoid-rule
  ([sym id]
   `(monoid-call-r '~sym ~id)))


(defn zero-property-r
  "The zero property of the function that's symbol is
  `fsym`. Semantically equivalent to

  (rule []
    (fsym ~@xs zero ~@ys)
    zero)
  "
  ([fsym zero]
   {:pre [(symbol? fsym)]}
   (reify
     protocols/IUnify
     (-unify [this x smap bottom]
       (if (and (seq? x)
                (= (first x) fsym))
         (assoc smap this x)
         bottom))

     protocols/IRuleLeftHandSide
     (-rule-left-hand-side [this]
       this)

     protocols/IRuleRightHandSide
     (-rule-right-hand-side [this]
       this)

     protocols/ISubstitute
     (-substitute [this smap]
       (if-some [seq (get smap this)]
         (if (some (partial = zero) seq)
           zero
           seq)
         (util/undefined))))))


(defmacro zero-property
  "Examples:
  
  (run-rule
   (zero-property * 0)
   '(* x y (* z 0)))
  ;; => 0

  (run-rule
   (zero-property and false)
   '(and yes? no? (and maybe? false)))
  ;; => false
  "
  [fsym zero]
  {:pre [(symbol? fsym)]}
  `(zero-property-r '~fsym ~zero))


(defmacro rule
  {:style/indent :defn}
  ([variables lhs rhs]
   `(vars ~variables
      (let [env# (hash-map ~@(mapcat
                              (fn [variable]
                                `('~variable ~variable))
                              variables))
            lhs# (parse-form '~lhs env#)
            rhs# (parse-form '~rhs env#)]
        (make-rule lhs# rhs#)))))


;; ---------------------------------------------------------------------
;; Scratch

(tufte/add-basic-println-handler! {})

(comment
  (= (run-rules
      [(monoid-rule + 0)
       (monoid-rule * 1)
       (zero-property or true)
       (zero-property and false)
       (monoid-rule str "")]
      '(+ 0 (* 1) 0 (str "foo" "bar") y))
     (+ 1 (str "foo" "bar") y)))



(tufte/profile
 {}
 (let [r1 (rule [a]
            (or ~@xs-1 [a ~@xs-2] ~@xs-3 [a ~@xs-4] ~@xs-5)
            (or [a (or [~@xs-2] [~@xs-4])] ~@xs-1 ~@xs-3 ~@xs-5))
       r2 (monoid-call-rule 'or)
       r3 (rule []
            (~@xs [] ~@ys)
            (~@xs ~@ys))
       r4 (rule []
            [~@xs [] ~@ys]
            [~@xs ~@ys])
       r5 (rule []
            [~@xs (or) ~@ys]
            [~@xs ~@ys])
       r6 (rule [a]
            (~@xs [a] ~@ys)
            (~@xs a ~@ys))
       r7 (rule [a]
            (or a) 
            a)
       r8 (rule [a]
            [~@xs [a ~@ys] ~@zs]
            [~@xs a ~@ys ~@zs])]
   (run-rules
    [r1 r2 r3 r4 r5 r6 r7 r8]
    '(or 1
         [2 3 4]
         7
         [2 3 5]))))
;; => (or [2 3 (or 4 5)] 1 7)
;; Clock Time 279.47ms
;; Accounted Time 0 0ns

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

        queue (meander.kahn/kahn-sort
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
    term*))


