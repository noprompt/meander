(ns meander.strategy.epsilon
  "Rewrite strategy combinators.

  A strategy is a unary function of a term (any object) and returns
  the term rewritten.

  Notation:

  t ∈ Term
  p, q, r, s ∈ Strategy"
  (:refer-clojure :exclude [find while repeat some spread])
  #?(:cljs (:require-macros [meander.strategy.epsilon]))
  (:require
   [clojure.core :as clj]
   [clojure.set :as set]
   [clojure.spec.alpha :as s]
   [meander.match.epsilon :as r.match :include-macros true]
   [meander.match.syntax.epsilon :as r.match.syntax :include-macros true]
   [meander.protocols.epsilon :as r.protocols]
   [meander.substitute.epsilon :as r.substitute :include-macros true]
   [meander.syntax.epsilon :as r.syntax :include-macros true]))


(def
  ^{:arglists '([t])
    :dynamic true}
  *pass*
  "Strategy which returns t."
  (reify
    #?@(:clj [clojure.lang.IFn
              (invoke [_ t] t)
              (applyTo [_ args] (first args))]
        :cljs [cljs.core/IFn
               (-invoke [_ t] t)
               ;; TODO: why no applyTo?
               #_(-applyTo [_ args] (first args))])))

#?(:clj
   (defmethod print-method (class *pass*) [v ^java.io.Writer w]
     (.write w "#meander.epsilon/pass[]"))
   :cljs
   (specify! *pass* IPrintWithWriter
     (-pr-writer [new-obj writer _]
       (write-all writer "#meander.epsilon/pass[]"))))

(defn pass?
  "true if `x` is `*pass*`, false otherwise."
  [x]
  (identical? x *pass*))


(defn pass
  "Strategy which returns t."
  [t]
  (*pass* t))


(def
  ^{:arglists '([t])
    :dynamic true}
  *fail*
  "Strategy which always fails."
  (reify
    #?@(:clj [clojure.lang.IFn
              (invoke [this _] this)
              (applyTo [this _] this)]
        :cljs [cljs.core/IFn
               (-invoke [this _] this)
               ;; TODO: why no applyTo?
               #_(-applyTo [this _] this)])))

#?(:clj
   (defmethod print-method (class *fail*) [v ^java.io.Writer w]
     (.write w "#meander.epsilon/fail[]"))
   :cljs
   (specify! *fail* IPrintWithWriter
     (-pr-writer [new-obj writer _]
       (write-all writer "#meander.epsilon/fail[]"))))


(defn fail?
  "true if `x` is `*fail*`, false otherwise."
  [x]
  (identical? x *fail*))


(defn fail
  "Strategy which always fails."
  [t]
  (*fail* t))


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
   (meander.strategy.epsilon/pipe-body [p q]))
  ([p q r]
   (meander.strategy.epsilon/pipe-body [p q r]))
  ([p q r s]
   (meander.strategy.epsilon/pipe-body [p q r s]))
  ([p q r s & more]
   (apply pipe (meander.strategy.epsilon/pipe-body [p q r s]) more)))


(defmacro choice-body
  {:private true}
  [params]
  (let [t (gensym "t__")
        t* (gensym "t__")]
    `(fn [~t]
       ~(reduce
         (fn [inner-form p]
           `(let [~t* (~p ~t)]
              (if (fail? ~t*)
                ~inner-form
                ~t*)))
         `*fail*
         (reverse params)))))


(defn choice
  "Build a strategy which applies `p` or `q` to `t`. If `p` rewrites,
  return the result, otherwise apply `q`."
  {:arglists '([] [p] [p q] [p q & more])}
  ([] *fail*)
  ([p] p)
  ([p q]
   (meander.strategy.epsilon/choice-body [p q]))
  ([p q r]
   (meander.strategy.epsilon/choice-body [p q r]))
  ([p q r s]
   (meander.strategy.epsilon/choice-body [p q r s]))
  ([p q r s & more]
   (apply choice (meander.strategy.epsilon/choice-body [p q r s]) more)))


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
       (rewrite
        ('let [!bs !vs ... ?b ?v]
         . !body ...)
        ('let [!bs !vs ...]
         (('fn [?b]
           . !body ...)
          ?v))

        ('let [] ?x)
        ?x))
     '(let [a 1
            b 2
            c 3]
        (+ a b c)))
    ;; =>
    ((fn [a] ((fn [b] ((fn [c] (+ a b c)) 3)) 2)) 1)
  "
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
       (rewrite
        ('let [!bs !vs ... ?b ?v]
         . !body ...)
        ('let [!bs !vs ...]
         (('fn [?b]
           . !body ...)
          ?v))

        ('let [] ?x)
        ?x))
     '(let [a 1
            b 2
            c 3]
        (+ a b c)))
    ;; =>
    ((fn [a] ((fn [b] ((fn [c] (+ a b c)) 3)) 2)) 1)"
  {:style/indent :defn}
  [pred s]
  (fn [t]
    (let [t* ((attempt s) t)]
      (if (pred t t*)
        t*
        (recur t*)))))

(defn n-times
  "Builds a strategy that repeats the passed in strategy `n` times.
  This is particularly useful for non-terminating rewrites or
  when you are trying to write a strategy and want to make sure you
  don't accidentally infinite loop.

  ((n-times 2
   (rewrite
    ?x (?x ?x)))
   :x)
  ;; => ((:x :x) (:x :x))
  "
  {:style/indent :defn}
  [n s]
  (apply pipe (clojure.core/repeat n s)))


(defn fix
  "Return a strategy which applies the strategy `s` to a term `t`
  repeatedly until it the result of applying `s` its argument returns
  its argument.

      (def to-pair
        (fix (rewrite
               [?x ?y]
               [?x ?y]

               ?x
               [?x ?x])))

      (to-pair [1 2])
      ;; => [1 2]
      (to-pair 1)
      ;; => [1 1]"
  [s]
  (fn [t]
    (let [t* (s t)]
      (if (= t* t)
        t
        (recur t*)))))

;; ---------------------------------------------------------------------
;; IAll implementation



(defn iall? [x]
  (satisfies? r.protocols/IAll x))


(defmacro iseq-all-body
  [t s]
  `(let [res# (reduce
                (fn [t*# x#]
                  (let [x*# (~s x#)]
                    (if (fail? x*#)
                      (reduced *fail*)
                      (concat t*# (list x*#)))))
                ()
                ~t)]
     (if (fail? res#)
       res#
       (vary-meta res# (fn [new-meta#] (merge (meta ~t) new-meta#))))))


(defmacro ivector-all-body
  {:private true}
  [t s]
  `(reduce
    (fn [t*# x#]
      (let [x*# (~s x#)]
        (if (fail? x*#)
          (reduced *fail*)
          (conj t*# x*#))))
    (empty ~t)
    ~t))


(defmacro imap-all-body
  {:private true}
  [t s]
  `(reduce-kv
    (fn [t*# k# v#]
      (let [k*# (~s k#)]
        (if (fail? k*#)
          *fail*
          (let [v*# (~s v#)]
            (if (fail? v*#)
              *fail*
              (assoc t*# k*# v*#))))))
    (empty ~t)
    ~t))


(defmacro irecord-all-body
  {:private true}
  [t s]
  `(reduce-kv
    (fn [t*# k# v#]
      (let [k*# (~s k#)]
        (if (fail? k*#)
          *fail*
          (let [v*# (~s v#)]
            (if (fail? v*#)
              *fail*
              (assoc t*# k*# v*#))))))
    ~t
    ~t))

(defmacro iset-all-body
  {:private true}
  [t s]
  `(reduce
    (fn [t*# x#]
      (let [x*# (~s x#)]
        (if (fail? x*#)
          (reduced *fail*)
          (conj t*# x*#))))
    (empty ~t)
    ~t))


(extend-protocol r.protocols/IAll
  #?@(:clj [clojure.lang.IPersistentMap (-all [this s] (imap-all-body this s))])
  #?@(:clj [clojure.lang.IPersistentSet (-all [this s] (iset-all-body this s))])
  #?@(:clj [clojure.lang.IPersistentVector (-all [this s] (ivector-all-body this s))])
  #?@(:clj [clojure.lang.ISeq (-all [this s] (iseq-all-body this s))])
  #?@(:cljs [cljs.core/LazySeq (-all [this s] (meander.strategy.epsilon/iseq-all-body this s))])
  #?@(:cljs [cljs.core/List (-all [this s] (meander.strategy.epsilon/iseq-all-body this s))])
  #?@(:cljs [cljs.core/PersistentArrayMap (-all [this s] (meander.strategy.epsilon/imap-all-body this s))])
  #?@(:cljs [cljs.core/PersistentHashMap (-all [this s] (meander.strategy.epsilon/imap-all-body this s))])
  #?@(:cljs [cljs.core/PersistentHashSet (-all [this s] (meander.strategy.epsilon/iset-all-body this s))])
  #?@(:cljs [cljs.core/PersistentVector (-all [this s] (meander.strategy.epsilon/ivector-all-body this s))])
  #?@(:cljs [cljs.core/Range (-all [this s] (meander.strategy.epsilon/iseq-all-body this s))]))


(defn all [s]
  #?(:clj
     (fn [t]
       (cond
         (record? t) (irecord-all-body t s)
         (iall? t) (r.protocols/-all t s)
         :else t))

     :cljs
     (fn [t]
       (cond
         (iall? t)
         (r.protocols/-all t s)

         (satisfies? cljs.core/IRecord t)
         (meander.strategy.epsilon/irecord-all-body t s)

         (satisfies? cljs.core/ISeq t)
         (meander.strategy.epsilon/iseq-all-body t s)

         (satisfies? cljs.core/IVector t)
         (meander.strategy.epsilon/ivector-all-body t s)

         (satisfies? cljs.core/IMap t)
         (meander.strategy.epsilon/iseq-all-body t s)

         (satisfies? cljs.core/ISet t)
         (meander.strategy.epsilon/iseq-all-body t s)

         :else
         t))))


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


;; ---------------------------------------------------------------------
;; IOne implementation


(defn ione? [x]
  (satisfies? r.protocols/IOne x))


(defmacro iseq-one-body
  {:private true}
  [t s]
  `(reduce
    (fn [_acc# [i# x#]]
      (let [x*# (~s x#)]
        (if (fail? x*#)
          *fail*
          (reduced (concat (take i# ~t)
                           (list x*#)
                           (drop (inc i#) ~t))))))
    *fail*
    (map-indexed vector ~t)))


(defmacro ivector-one-body
  {:private true}
  [t s]
  `(reduce-kv
    (fn [acc# i# x#]
      (let [x*# (~s x#)]
        (if (fail? x*#)
          acc#
          (reduced (assoc ~t i# x*#)))))
    *fail*
    ~t))


(defmacro imap-one-body
  {:private true}
  [t s]
  `(reduce-kv
    (fn [acc# k# v#]
      (let [k*# (~s k#)]
        (if (fail? k*#)
          (let [v*# (~s v#)]
            (if (fail? v*#)
              acc#
              (reduced (assoc ~t k# v*#))))
          (reduced (assoc ~t k*# v#)))))
    *fail*
    ~t))

(defmacro iset-one-body
  {:private true}
  [t s]
  `(reduce
    (fn [acc# x#]
      (let [x*# (~s x#)]
        (if (fail? x*#)
          *fail*
          (reduced (conj (disj ~t x#) x*#)))))
    *fail*
    ~t))


(extend-protocol r.protocols/IOne
  #?@(:clj [clojure.lang.IPersistentMap (-one [this s] (imap-one-body this s))])
  #?@(:clj [clojure.lang.IPersistentSet (-one [this s] (iset-one-body this s))])
  #?@(:clj [clojure.lang.IPersistentVector (-one [this s] (ivector-one-body this s))])
  #?@(:clj [clojure.lang.ISeq (-one [this s] (iseq-one-body this s))])
  #?@(:cljs [cljs.core/LazySeq (-one [this s] (meander.strategy.epsilon/iseq-one-body this s))])
  #?@(:cljs [cljs.core/List (-one [this s] (meander.strategy.epsilon/iseq-one-body this s))])
  #?@(:cljs [cljs.core/PersistentArrayMap (-one [this s] (meander.strategy.epsilon/imap-one-body this s))])
  #?@(:cljs [cljs.core/PersistentHashMap (-one [this s] (meander.strategy.epsilon/imap-one-body this s))])
  #?@(:cljs [cljs.core/PersistentHashSet (-one [this s] (meander.strategy.epsilon/iset-one-body this s))])
  #?@(:cljs [cljs.core/PersistentVector (-one [this s] (meander.strategy.epsilon/ivector-one-body this s))])
  #?@(:cljs [cljs.core/Range (-one [this s] (meander.strategy.epsilon/iseq-one-body this s))]))


(defn one [s]
  #?(:clj
     (fn [t]
       (if (ione? t)
         (r.protocols/-one t s)
         t))
     :cljs
     (fn [t]
       (cond
         (ione? t)
         (r.protocols/-one t s)

         (satisfies? cljs.core/ISeq t)
         (meander.strategy.epsilon/iseq-one-body t s)

         (satisfies? cljs.core/IVector t)
         (meander.strategy.epsilon/ivector-one-body t s)

         (satisfies? cljs.core/IMap t)
         (meander.strategy.epsilon/imap-one-body t s)

         (satisfies? cljs.core/ISet t)
         (meander.strategy.epsilon/iset-one-body t s)

         :else
         t))))


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


;; ---------------------------------------------------------------------
;; ISome implementation


(defn isome? [x]
  (satisfies? r.protocols/ISome x))


(defmacro iseq-some-body
  {:private true}
  [t s]
  `(let [[t*# pass?#]
         (reduce
          (fn [[t*# pass?#] x#]
            (let [x*# (~s x#)]
              (if (fail? x*#)
                [(cons x# t*#) pass?#]
                [(cons x*# t*#) true])))
          [() false]
          (reverse ~t))]
     (if pass?#
       t*#
       *fail*)))


(defmacro ivector-some-body
  {:private true}
  [t s]
  `(let [[t*# pass?#]
         (reduce-kv
          (fn [[t*# pass?#] i# x#]
            (let [x*# (~s x#)]
              (if (fail? x*#)
                [t*# pass?#]
                [(assoc t*# i# x*#) true])))
          [~t false]
          ~t)]
     (if pass?#
       t*#
       *fail*)))


(defmacro imap-some-body
  {:private true}
  [t s]
  `(let [[t*# pass?#]
         (reduce-kv
          (fn [[t*# pass?#] k# v#]
            (let [k*# (~s k#)
                  v*# (~s v#)]
              (case [(fail? k*#) (fail? v*#)]
                [true true]
                [(assoc t*# k# v#) pass?#]

                [true false]
                [(assoc t*# k# v*#) true]

                [false true]
                [(assoc t*# k*# v#) true]

                [false false]
                [(assoc t*# k*# v*#) true])))
          [{} false]
          ~t)]
     (if pass?#
       t*#
       *fail*)))


(defmacro irecord-some-body
  {:private true}
  [t s]
  `(let [[t*# pass?#]
         (reduce-kv
          (fn [[t*# pass?#] k# v#]
            (let [k*# (~s k#)
                  v*# (~s v#)]
              (case [(fail? k*#) (fail? v*#)]
                [true true]
                [(assoc t*# k# v#) pass?#]

                [true false]
                [(assoc t*# k# v*#) true]

                [false true]
                [(assoc t*# k*# v#) true]

                [false false]
                [(assoc t*# k*# v*#) true])))
          [~t false]
          ~t)]
     (if pass?#
       t*#
       *fail*)))


(defmacro iset-some-body
  {:private true}
  [t s]
  `(let [[t*# pass?#]
         (reduce
          (fn [[t*# pass?#] x#]
            (let [x*# (~s x#)]
              (if (fail? x*#)
                [(conj t*# x#) pass?#]
                [(conj t*# x*#) true])))
          [#{} false]
          ~t)]
     (if pass?#
       t*#
       *fail*)))


(extend-protocol r.protocols/ISome
  #?@(:clj [clojure.lang.IPersistentMap (-some [this s] (imap-some-body this s))])
  #?@(:clj [clojure.lang.IPersistentSet (-some [this s] (iset-some-body this s))])
  #?@(:clj [clojure.lang.IPersistentVector (-some [this s] (ivector-some-body this s))])
  #?@(:clj [clojure.lang.ISeq (-some [this s] (iseq-some-body this s))])
  #?@(:cljs [cljs.core/LazySeq (-some [this s] (meander.strategy.epsilon/iseq-some-body this s))])
  #?@(:cljs [cljs.core/List (-some [this s] (meander.strategy.epsilon/iseq-some-body this s))])
  #?@(:cljs [cljs.core/PersistentArrayMap (-some [this s] (meander.strategy.epsilon/imap-some-body this s))])
  #?@(:cljs [cljs.core/PersistentHashMap (-some [this s] (meander.strategy.epsilon/imap-some-body this s))])
  #?@(:cljs [cljs.core/PersistentHashSet (-some [this s] (meander.strategy.epsilon/iset-some-body this s))])
  #?@(:cljs [cljs.core/PersistentVector (-some [this s] (meander.strategy.epsilon/ivector-some-body this s))])
  #?@(:cljs [cljs.core/Range (-some [this s] (meander.strategy.epsilon/iseq-some-body this s))]))


(defn some
  "Build a strategy which applies `s` to as many direct subterms of
  `t` as possible. Succeeds if at least one application applies, fails
  otherwise."
  [s]
  #?(:clj
     (fn [t]
       (cond
         (record? t)
         (irecord-some-body t s)

         (isome? t)
         (r.protocols/-some t s)

         :else t))

     :cljs
     (fn [t]
       (cond
         (isome? t)
         (r.protocols/-some t s)

         (satisfies? cljs.core/IRecord t)
         (meander.strategy.epsilon/irecord-some-body t s)

         (satisfies? cljs.core/ISeq t)
         (meander.strategy.epsilon/iseq-some-body t s)

         (satisfies? cljs.core/IVector t)
         (meander.strategy.epsilon/ivector-some-body t s)

         (satisfies? cljs.core/IMap t)
         (meander.strategy.epsilon/imap-some-body t s)

         (satisfies? cljs.core/ISet t)
         (meander.strategy.epsilon/iset-some-body t s)

         :else
         t))))


(defn some-td
  "Return a strategy which attempts to apply the strategy `s` to all
  subterms of a term `t` from the top-down (pre-order). Succeeds if at
  least one subterm of `t` passed to `s` succeeds, fails otherwise.

      (def inc-numbers
        (some-td (pipe (pred number?) inc)))

      (inc-numbers [1 [\"2\" 3] \"4\"])
      ;;=> [2 [\"2\" 4] \"4\"]

      (inc-numbers [\"1\" \"2\"])
      ;; => #meander.epsilon/fail[]"
  [s]
  (fn rec [t]
    (if (isome? t)
      ((choice s (some rec)) t)
      (s t))))


(defn some-bu
  "Return a strategy which attempts to apply the strategy `s` to all
  subterms of a term `t` from the bottom-up (post-order). Succeeds if
  at least one subterm of `t` passed to `s` succeeds, fails otherwise.

      (def inc-numbers
        (some-td (pipe (pred number?) inc)))

      (inc-numbers [1 [\"2\" 3] \"4\"])
      ;;=> [2 [\"2\" 4] \"4\"]

      (inc-numbers [\"1\" \"2\"])
      ;; => #meander.epsilon/fail[]"
  [s]
  (fn rec [t]
    (if (isome? t)
      ((choice (some rec) s) t)
      (s t))))

;; ---------------------------------------------------------------------
;; IRetain implementation


(defn iretain? [x]
  (satisfies? r.protocols/IRetain x))


(defmacro iseq-retain-body
  {:private true}
  [t s]
  `(sequence (comp (map ~s) (remove fail?)) ~t))


(defmacro ivector-retain-body
  {:private true}
  [t s]
  `(into [] (comp (map ~s) (remove fail?)) ~t))


(defmacro iset-retain-body
  {:private true}
  [t s]
  `(into #{} (comp (map ~s) (remove fail?)) ~t))


(defmacro imap-retain-body
  {:private true}
  [t s]
  `(into {} (comp (map ~s) (remove fail?)) ~t))


(extend-protocol r.protocols/IRetain
  #?@(:clj [clojure.lang.IPersistentMap (-retain [this s] (imap-retain-body this s))])
  #?@(:clj [clojure.lang.IPersistentSet (-retain [this s] (iset-retain-body this s))])
  #?@(:clj [clojure.lang.IPersistentVector (-retain [this s] (ivector-retain-body this s))])
  #?@(:clj [clojure.lang.ISeq (-retain [this s] (iseq-retain-body this s))])
  #?@(:cljs [cljs.core/LazySeq (-retain [this s] (meander.strategy.epsilon/iseq-retain-body this s))])
  #?@(:cljs [cljs.core/List (-retain [this s] (meander.strategy.epsilon/iseq-retain-body this s))])
  #?@(:cljs [cljs.core/PersistentArrayMap (-retain [this s] (meander.strategy.epsilon/imap-retain-body this s))])
  #?@(:cljs [cljs.core/PersistentHashMap (-retain [this s] (meander.strategy.epsilon/imap-retain-body this s))])
  #?@(:cljs [cljs.core/PersistentHashSet (-retain [this s] (meander.strategy.epsilon/iset-retain-body this s))])
  #?@(:cljs [cljs.core/PersistentVector (-retain [this s] (meander.strategy.epsilon/ivector-retain-body this s))])
  #?@(:cljs [cljs.core/Range (-retain [this s] (meander.strategy.epsilon/iseq-retain-body this s))]))


(defn retain
  "Return a strategy which retains subterms of t for which the
  strategy s succeeds."
  ([s]
   (fn [t]
     #?(:clj
        (if (iretain? t)
          (r.protocols/-retain t s)
          t)

        :cljs
        (cond
          (iretain? t)
          (r.protocols/-retain t s)

          (satisfies? cljs.core/ISeq t)
          (meander.strategy.epsilon/iseq-retain-body t s)

          (satisfies? cljs.core/IVector t)
          (meander.strategy.epsilon/ivector-retain-body t s)

          (satisfies? cljs.core/IMap t)
          (meander.strategy.epsilon/imap-retain-body t s)

          (satisfies? cljs.core/ISet t)
          (meander.strategy.epsilon/iset-retain-body t s)

          :else
          t))))
  ([s t]
   #?(:clj
      (if (iretain? t)
        (r.protocols/-retain t s)
        t)

      :cljs
      (cond
        (iretain? t)
        (r.protocols/-retain t s)

        (satisfies? cljs.core/ISeq t)
        (meander.strategy.epsilon/iseq-retain-body t s)

        (satisfies? cljs.core/IVector t)
        (meander.strategy.epsilon/ivector-retain-body t s)

        (satisfies? cljs.core/IMap t)
        (meander.strategy.epsilon/imap-retain-body t s)

        (satisfies? cljs.core/ISet t)
        (meander.strategy.epsilon/iset-retain-body t s)

        :else
        t))))


(defn spine-td
  [s]
  (fn rec [t]
    ((pipe s (attempt (one rec))) t)))


(defn spine-bu
  [s]
  (fn rec [t]
    ((pipe (attempt (one rec)) s) t)))


(defn breadth-first
  "Build a strategy which applies `s` to each subterm of `t` in a
  breadth-first order."
  [s]
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
  "Build a strategy which applies the first `n` values of `t` to `f`
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
   (meander.strategy.epsilon/tuple-body [p q]))
  ([p q r]
   (meander.strategy.epsilon/tuple-body [p q r]))
  ([p q r s]
   (meander.strategy.epsilon/tuple-body [p q r s]))
  ([p q r s & more]
   (pipe (tuple (meander.strategy.epsilon/tuple-body [p q r s])
                (apply tuple more))
         (fn [v]
           (into (nth v 0) (nth v 1))))))


(defn at
  "Build a strategy which modifies t at key with p, then q, etc. Works
  with map?, vector?, and set?. For vector? and set? key must be
  present."
  {:arglists '([key]
               [key p]
               [key p q]
               [key p q & more])}
  ([k & ps]
   (let [p (apply pipe ps)]
     (fn [t]
       (cond
         (or (map? t)
             (and (vector? t) (contains? t k)))
         (let [v* (p (get t k))]
           (if (fail? v*)
             v*
             (assoc t k v*)))

         (and (set? t) (contains? t k))
         (let [v* (p (get t k))]
           (if (fail? v*)
             v*
             (conj (disj t k) v*)))

         :else
         *fail*)))))


(s/fdef match
  :args :meander.match.epsilon.match/clauses
  :ret any?)


(defmacro match
  "Strategy version of match which defaults to returning *fail*."
  {:style/indent 0}
  [& clauses]
  `(fn [x#]
     (r.match/match x#
       ~@clauses

       ~'_
       *fail*)))


(s/fdef search
  :args :meander.match.epsilon.match/clauses
  :ret any?)


(defmacro search
  "Strategy version of search."
  {:style/indent 0}
  [& clauses]
  `(fn [x#]
     (r.match/search x#
       ~@clauses)))


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


(defmacro find
  "Strategy version of meander.match.epsilon/find that defaults to
  returning *fail*."
  {:style/indent 0}
  [& clauses]
  `(fn [x#]
     (r.match/find x#
       ~@clauses)))


(s/fdef find
  :args :meander.match.epsilon.match/clauses
  :ret any?)

;; ---------------------------------------------------------------------
;; Rewrite

(defn linear?
  "`true` if no variable occurs more than once in `node`."
  {:private true}
  [node]
  (r.match/find (r.match.syntax/analyze node)
    {:occurrences {_ (not 1)}}
    false

    _
    true))


;; This property is also known as "nonerasing".
(defn variable-preserving?
  "`true` if the set of variables occuring on the left side of `rule`
  is equal to the set variables on the right, `false` otherwise."
  {:private true}
  [rule]
  (r.match/find rule
    (r.syntax/with [%variables (r.match.syntax/apply keys (r.match.syntax/apply set ?variables))]
      {:lhs/analysis {:occurrences %variables}
       :rhs/analysis {:occurrences %variables}})
    true

    _
    false))


(defn collapsing?
  "`true` if the right side of `rule` is a variable, false otherwise."
  {:private true}
  [rule]
  (r.match/find rule
    {:rhs/node {:tag (or :mvr :lvr)}}
    true

    _
    false))


(defn duplicating?
  "true if there is a variable occuring more often on the right side
  of `rule` than on the left."
  {:private true}
  [rule]
  (r.match/find rule
    (r.match.syntax/and {:rhs/analysis {:occurrences {?variable ?m}}}
                        {:lhs/analysis {:occurrences {?variable ?n}}}
                        (r.match.syntax/guard (< ?n ?m)))
    true

    _
    false))


(defn check-rule
  {:private true}
  [rule]
  (r.match/search rule
    ;; Find all occurences of variables on the right that are not on
    ;; the left.
    (r.match.syntax/and
     {:rhs/analysis {:occurrences {{:symbol ?symbol :as ?node} _}}}
     {:lhs/analysis {:occurrences (r.match.syntax/not {?node _})}})
    [:error {:message (str "The variable " ?symbol " must also appear in the match if it appears in the substitution")
             :ex-data {:variable ?symbol}}]))


(defn analyze-rewrite-args
  {:private true}
  [args]
  (sequence
   (map
    (fn [[lhs rhs]]
      (let [lhs-node (r.syntax/parse lhs)
            rhs-node (r.syntax/parse rhs)
            rule {:lhs/analysis (r.match.syntax/analyze lhs-node)
                  :lhs/form lhs
                  :lhs/node lhs-node
                  ;; Probably should use r.substitute/syntax analyze.
                  :rhs/analysis (r.match.syntax/analyze rhs-node)
                  :rhs/form rhs
                  :rhs/node rhs-node}]
        (merge rule {:errors (check-rule rule)}))))
   (partition 2 args)))


(defmacro rewrite
  "Returns strategy which symbolically transforms `t` in to `t*` via
  pattern matching and substitution. Pattern matching is conducted
  using `find`.

  Example:

  (let [s (rewrite
           (let* [!bs !vs ..1]
             . !body ...)
           (let* [!bs !vs]
             (let* [!bs !vs ...]
               . !body ...)))]
    (s '(let* [b1 :v1, b2 :v2, b3 :v3]
          (vector b1 b2 b3))))
    ;; =>
    (let* [b1 :v1]
      (let* [b2 :v2
             b3 :v3]
        (vector b1 b2 b3)))"
  [& rules]
  `(find
     ~@(mapcat
        (fn [[pat rhs]]
          [pat `(r.substitute/substitute ~rhs)])
        (partition 2 rules))
     ~'_
     *fail*))

(s/fdef rewrite
  :args :meander.match.epsilon.match/clauses
  :ret any?)

(defmacro rewrites
  [& rules]
  `(search
    ~@(mapcat
       (fn [[pat rhs]]
         [pat `(r.substitute/substitute ~rhs)])
       (partition 2 rules))))

(s/fdef rewrites
  :args :meander.match.epsilon.match/clauses
  :ret any?)
