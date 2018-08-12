(ns meander.strategy.alpha
  "Rewrite strategy combinators.

  A strategy is a unary function of a term (any object) and returns
  the term rewritten.

  Notation:

  t ∈ Term
  p, q, r, s ∈ Strategy"
  (:refer-clojure :exclude [while repeat some])
  (:require [clojure.core :as clj]
            [clojure.spec.alpha :as s]
            [meander.protocols.alpha :as r.protocols]
            [meander.match.alpha :as r.match]))


(def
  ^{:arglists '([t])
    :dynamic true}
  *pass*
  "Strategy which returns t."
  (reify 
    clojure.lang.IFn
    (invoke [_ t]
      t)

    (applyTo [_ args]
      (first args))))


(defmethod print-method (class *pass*) [v ^java.io.Writer w]
  (.write w "#meander/pass[]"))


(defn pass?
  "true if `x` is `*pass*`, false otherwise."
  [x]
  (identical? x *pass*))


(def
  ^{:arglists '([t])
    :dynamic true}
  *fail*
  "Strategy which always fails."
  (reify
    clojure.lang.IFn
    (invoke [this _]
      this)

    (applyTo [this _]
      this)))


(defmethod print-method (class *fail*) [v ^java.io.Writer w]
  (.write w "#meander/fail[]"))


(defn fail?
  "true if `x` is `*fail*`, false otherwise."
  [x]
  (identical? x *fail*))


(defn build
  "Build a strategy which always returns `t`."
  [t]
  (fn [_] t))


(defmacro pipe-body
  {:private true}
  ([params]
   (let [t (gensym "t__")]
     `(if (or ~@(map (partial list `fail?) params))
        *fail*
        (fn [~t]
          ~(reduce
            (fn [inner-form p]
              `(let [~t (~p ~t)]
                 (if (fail? ~t)
                   *fail*
                   ~inner-form)))
            t
            (reverse params)))))))


(defn pipe
  "Build a strategy which applies `p` to `t` and then `q` iff `p` rewrites
  `t`. If `p` and `q` are successful, return the result, otherwise
  return `*fail*`."
  {:arglists '([] [p] [p q] [p q & more])}
  ([] *pass*)
  ([p] p)
  ([p q]
   (pipe-body [p q]))
  ([p q r]
   (pipe-body [p q r]))
  ([p q r s]
   (pipe-body [p q r s]))
  ([p q r s & more]
   (apply pipe (pipe-body [p q r s]) more)))


(defn choice
  "Build a strategy which applies `p` or `q` to `t`. If `p` rewrites,
  return the result, otherwise apply `q`."
  ([] *fail*)
  ([p] p)
  ([p q]
   (fn [t]
     (let [t* (p t)]
       (if (fail? t*)
         (q t)
         t*))))
  ([p q & more]
   (apply choice (choice p q) more)))


(defn branch
  {:style/indent :defn}
  [p q r]
  (fn [t]
    (let [t* (p t)]
      (if (fail? t*)
        (r t)
        (q t*)))))


(defn attempt
  "Build a strategy which attempts apply `s` to a term. If `s`
  succeeds, it returns the result. If `s` fails return the original
  term."
  [s]
  (choice s *pass*))


(defn pred
  "Build a strategy which returns `t` iff `p` is true for `t` and
  fails otherwise."
  [p]
  (fn [t]
    (if (p t)
      t
      *fail*)))


(defn guard
  "Build a strategy which applies `s` to `t` iff `p` is true for `t`."
  {:style/indent :defn}
  [p s]
  (attempt (pipe (pred p) s)))


(defn repeat
  "Build a strategy which applies `s` to `t` repeatedly until failure.
  Note that, if used in conjunction with a strategy which never fails
  i.e. `attempt`, this will cause a stack overflow. To avoid this, use
  `while` or `until`.

  Example:

  ((repeat
    (pipe (pred vector?)
          (fn [v]
            (if (seq v)
              (if (= (peek v) 2)
                *fail*
                (pop v))
              *fail*))))
   [1 2 3 4])
  ;; =>
  [1 2]
  "
  [s]
  (fn rec [t]
    ((attempt (pipe s rec)) t)))


(defn while
  "Build a strategy which repeatedly applies `s` to `t` so long as `pred`
  is false for `t` and `t*`.

  ((while not=
     (t (let [~@bvs ~b ~v] ~@body)
       (let [~@bvs] ((fn [~b] ~@body) ~v))))
   '(let [a 1
          b 2
          c 3]
      (+ a b c)))
  =>
  (let [] ((fn [a] ((fn [b] ((fn [c] (+ a b c)) 3)) 2)) 1))"
  {:style/indent :defn}
  [pred s]
  (fn rec [t]
    ((pipe (attempt s)
           (fn [t*]
             (if (pred t t*)
               (rec t*)
               t*)))
     t)))


(defn until
  "Build a strategy which repeatedly applies `s` to `t` so long as `pred`
  is false for `t` and `t*`.

  ((until =
     (t (let [~@bvs ~b ~v] ~@body)
       (let [~@bvs] ((fn [~b] ~@body) ~v))))
   '(let [a 1
          b 2
          c 3]
      (+ a b c)))
  =>
  (let [] ((fn [a] ((fn [b] ((fn [c] (+ a b c)) 3)) 2)) 1))"
  {:style/indent :defn}
  [pred s]
  (fn [t]
    (let [t* ((attempt s) t)]
      (if (pred t t*)
        t*
        (recur t*)))))


(defn iall? [x]
  (satisfies? r.protocols/IAll x))


(defn all [s]
  (fn [t]
    (if (iall? t)
      (r.protocols/-all t s)
      t)))


(defn all-td
  "Apply the all strategy with `s` to every subterm in `t` from the
  top down."
  [s]
  (fn rec [t]
    ((choice s (all rec)) t)))


(defn all-bu
  "Apply the all strategy with `s` to every subterm in `t` from the
  bottom up."
  [s]
  (fn rec [t]
    ((choice (all rec) s) t)))


(defn ione? [x]
  (satisfies? r.protocols/IOne x))


(defn one [s]
  (fn [t]
    (if (ione? t)
      (r.protocols/-one t s)
      t)))


(defn once-td
  "Apply the `one` strategy with `s` to every subterm in `t` from the
  top down."
  [s]
  (fn rec [t]
    ((choice s (one rec)) t)))


(defn once-bu
  "Apply the `one` strategy with `s` to every subterm in `t` from the
  bottom up."
  [s]
  (fn rec [t]
    ((choice (one rec) s) t)))


(defn isome? [x]
  (satisfies? r.protocols/ISome x))


(defn some
  "Build a strategy which applies `s` to as many direct subterms of
  `t` as possible. Succeeds if at least one application applies, fails
  otherwise."
  [s]
  (fn [t]
    (if (isome? t)
      (r.protocols/-some t s)
      t)))


(defn some-td
  [s]
  (fn rec [t]
    ((choice s (some rec)) t)))


(defn some-bu
  [s]
  (fn rec [t]
    ((choice (some rec) s) t)))


(defn spine-td
  [s]
  (fn rec [t]
    ((pipe s (attempt (one rec))))))


(defn spine-bu
  [s]
  (fn rec [t]
    ((pipe (attempt (one rec)) s))))


(defn breadth-first [s]
  (fn rec [t]
    ((pipe (all s) (all rec)) t)))


(defn bottom-up
  "Build a strategy which applies `s` to each subterm of `t` from
  bottom to top."
  [s]
  (fn rec [t]
    ((pipe (all rec) s) t)))


(defn top-down
  "Build a strategy which applies `s` to each subterm of `t` from
  top to bottom."
  [s]
  (fn rec [t]
    ((pipe s (all rec)) t)))


(defn top-down-while
  "Build a strategy which applies `s` to each subterm of `t` from
  top to bottom so long as `pred` is true for some subterm of `t`."
  [pred s]
  (fn rec [t]
    (if (pred t)
      ((pipe s (all rec)) t)
      t)))


(defn top-down-until
  "Build a strategy which applies `s` to each subterm of `t` from
  top to bottom until `pred` is false for some subterm of `t`."
  [pred s]
  (fn rec [t]
    (if (pred t)
      t
      ((pipe s (all rec)) t))))


(defn outermost
  "Build a strategy which repeatedly applies `s` to `t` starting from
  the outermost subterm in `t` until it fails."
  [s]
  (repeat (once-td s)))


(defn innermost
  "Build a strategy which repeatedly applies `s` to `t` starting from
  the innermost subterm in `t`."
  [s]
  (fn rec [t]
    ((bottom-up (repeat s)) t)))


(defn trace
  "Build a strategy which monitors the entry and exit values of `s`."
  ([s]
   (trace s prn))
  ([s f]
   (let [id (gensym "t_")]
     (fn [t]
       (f {:id id, :in t})
       (let [t* (s t)]
         (f {:id id, :out t*})
         t*)))))


(defn spread
  "Build a strategey which applies the first `n` values of `t` to `f`
  iff `t` is a coll. Behaves like apply if `n` is not supplied. Useful
  in conjunction with `juxt`.

  ((pipe
    (tuple (comp (some keyword) keys)
           vals)
    (spread zipmap 2))
   {\"foo\" \"bar\"
    \"baz\" \"quux\"})
  ;; => 
  {:foo \"bar\"
   :baz \"quux\"}"
  ([f]
   (fn spread-all [t]
     (if (coll? t)
       (apply f t)
       t)))
  ([f n]
   (fn spread-n [t]
     (if (coll? t)
       (let [args (take n t)]
         (if (= (count args)
                n)
           (apply f args)
           t))
       t))))


(defmacro tuple-body
  {:private true}
  [params]
  (let [t (gensym "t__")
        t*s (mapv gensym (clj/repeat (count params) "t__"))]
    `(fn [~t]
       ~(reduce
         (fn [inner-form [t* param]]
           `(let [~t* (~param ~t)]
              (if (fail? ~t*)
                *fail*
                ~inner-form)))
         t*s
         (reverse (map vector t*s params))))))


(defn tuple
  "Build a strategy which behaves similarly to `juxt` but fails if any
  of the strategies which compose it fail."
  {:arglists '([] [p] [p q] [p q & more])}
  ([]
   (build []))
  ([p]
   (pipe p vector))
  ([p q]
   (tuple-body [p q]))
  ([p q r]
   (tuple-body [p q r]))
  ([p q r s]
   (tuple-body [p q r s]))
  ([p q r s & more]
   (apply tuple (tuple-body [p q r s]) more)))


(extend-type clojure.lang.IPersistentVector
  r.protocols/IAll
  (-all [this s]
    (reduce
     (fn [this* x]
       (let [x* (s x)]
         (if (fail? x*)
           (reduced *fail*)
           (conj this* x*))))
     []
     this))


  r.protocols/ISome
  (-some [this s]
    (let [[this* pass?]
          (reduce-kv
           (fn [[this* pass?] i x]
             (let [x* (s x)]
               (if (fail? x*)
                 [this* pass?]
                 [(assoc this* i x*) true])))
           [this false]
           this)]
      (if pass?
        this*
        *fail*)))

  
  r.protocols/IOne
  (-one [this s]
    (reduce-kv
     (fn [acc i x]
       (let [x* (s x)]
         (if (fail? x*)
           acc
           (reduced (assoc this i x*)))))
     *fail*
     this)))


(extend-type clojure.lang.ISeq
  r.protocols/IAll
  (-all [this s]
    (reduce
     (fn [this* x]
       (let [x* (s x)]
         (if (fail? x*)
           (reduced *fail*)
           (concat this* (list x*)))))
     ()
     this))


  r.protocols/ISome
  (-some [this s]
    (let [[this* pass?]
          (reduce
           (fn [[this* pass?] x]
             (let [x* (s x)]
               (if (fail? x*)
                 [(cons x this*) pass?]
                 [(cons x* this*) true])))
           [() false]
           (reverse this))]
      (if pass?
        this*
        *fail*)))

  
  r.protocols/IOne
  (-one [this s]
    (reduce
     (fn [_acc [i x]]
       (let [x* (s x)]
         (if (fail? x*)
           *fail*
           (reduced (concat (take i this)
                            (list x*)
                            (drop (inc i) this))))))
     *fail*
     (map-indexed vector this))))


(extend-type clojure.lang.IPersistentMap
  r.protocols/IAll
  (-all [this s]
    (reduce-kv
     (fn [this* k v]
       (let [k* (s k)]
         (if (fail? k*)
           *fail*
           (let [v* (s v)]
             (if (fail? v*)
               *fail*
               (assoc this* k* v*))))))
     {}
     this))


  r.protocols/ISome
  (-some [this s]
    (let [[this* pass?]
          (reduce-kv
           (fn [[this* pass?] k v]
             (let [k* (s k)
                   v* (s v)]
               (case [(fail? k*) (fail? v*)]
                 [true true]
                 [(assoc this* k v) pass?]

                 [true false]
                 [(assoc this* k v*) true]

                 [false true]
                 [(assoc this* k* v) true]

                 [false false]
                 [(assoc this* k* v*) true])))
           [{} false]
           this)]
      (if pass?
        this*
        *fail*)))

  
  r.protocols/IOne
  (-one [this s]
    (reduce-kv
     (fn [acc k v]
       (let [k* (s k)]
         (if (fail? k*)
           (let [v* (s v)]
             (if (fail? v*)
               acc
               (reduced (assoc this k v*))))
           (reduced (assoc this k* v)))))
     *fail*
     this)))


(extend-type clojure.lang.IPersistentSet
  r.protocols/IAll
  (-all [this s]
    (reduce
     (fn [this* x]
       (let [x* (s x)]
         (if (fail? x*)
           (reduced *fail*)
           (conj this* x*))))
     #{}
     this))


  r.protocols/ISome
  (-some [this s]
    (let [[this* pass?] 
          (reduce
           (fn [[this* pass?] x]
             (let [x* (s x)]
               (if (fail? x*)
                 [(conj this* x) pass?]
                 [(conj this* x*) true])))
           [#{} false]
           this)]
      (if pass?
        this*
        *fail*)))

  
  r.protocols/IOne
  (-one [this s]
    (reduce
     (fn [acc x]
       (let [x* (s x)]
         (if (fail? x*)
           *fail*
           (reduced (conj (disj this x) x*)))))
     *fail*
     this)))


(s/fdef match
  :args :meander.match.alpha.match/clauses
  :ret any?)


(defmacro match
  "Strategy version of match which defaults to *fail*."
  {:style/indent 0}
  [& clauses]
  `(fn [x#]
     (r.match/match x#
       ~@clauses

       ~'_
       *fail*)))


(defn extract
  "Return a sequence of all successful rewrites of s applied to all
  subterms of t."
  [s]
  (fn [t]
    (sequence
     (comp
      (map s)
      (remove fail?))
     (tree-seq coll? seq t))))
