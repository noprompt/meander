(ns meander.dev.core
  (:require
   [clojure.core :as clj]
   [clojure.set :as set]
   [clojure.spec.alpha :as s]
   [clojure.test :as t]
   [meander.dev.syntax :as syntax]
   [meander.util :as util]))

#_
(set! *warn-on-reflection* false)

;; ---------------------------------------------------------------------
;; Internal utilities


(def
  ^{:dynamic true
    :private true}
  *debug* false)


(defmacro debug
  {:private true}
  ([& forms]
   `(binding [*debug* true]
      ~@forms)))


(defmacro no-debug
  {:private true}
  ([& forms]
   `(binding [*debug* false]
      ~@forms)))


(defn clean-form
  {:private true}
  [form]
  (clojure.walk/postwalk
   (fn [x]
     (if (and (qualified-symbol? x)
              (= (namespace x) "clojure.core"))
       (symbol (name x))
       x))
   form))


(defmacro undefined
  {:private true}
  ([& _]
   `(throw (ex-info "undefined" ~(meta &form)))))


(defn write-elems!
  {:private true}
  [elems ^java.io.Writer w]
  (run!
   (fn [x]
     (if (= " " x)
       (.write w " ")
       (print-method x w)))
   (interpose " " elems)))


;; ---------------------------------------------------------------------
;; Protocols

(defprotocol IVariable
  "Marker protocol indicating a type represents a variable.")


(defn ivariable? [t]
  (satisfies? IVariable t))


(defprotocol IVariables
  "Return a set of all variables in t."
  (-variables [t]))


(defn variables
  "Return the set of term variables in x."
  [t]
  (if (satisfies? IVariables t)
    (set (-variables t))
    #{}))


(defn ground?
  "true if the term x contains no variables."
  [t]
  (not (seq (variables t))))


(defprotocol IMultipleSubstitutions
  (-multiple-substitutions? [t]))


(defn multiple-substitutions?
  "true if x may have more than one substitution under unification."
  [t]
  (and (satisfies? IMultipleSubstitutions t)
       (boolean (-multiple-substitutions? t))))


(defprotocol IMinLength
  (-min-length [t]))


(defprotocol IMaxLength
  (-max-length [t]))


(defprotocol IVariableLength
  (-variable-length? [t]))


(defn variable-length?
  "true if t may have a variable length."
  [t]
  (and (satisfies? IVariableLength t)
       (boolean (-variable-length? t))))


(defprotocol ISubstitutions
  (-substitutions [u v smap]))


(defn isubstitutions?
  [x]
  (satisfies? ISubstitutions x))


(defn substitutions
  ([u v]
   (substitutions u v {}))
  ([u v smap]
   (cond
     (isubstitutions? u)
     (-substitutions u v smap)

     (isubstitutions? v)
     (recur v u smap)

     :else
     (if (= u v)
       (list smap)))))


(defn lconj-xform
  {:private true}
  [u-coll v-coll]
  (apply comp
    (sequence
     (map
      (fn [u v]
        (mapcat
         (fn [smap]
           (substitutions u v smap)))))
     u-coll
     v-coll)))


(defprotocol ISubstituteStep
  (-substitute-step [u smap]))


(defn substitute-step
  [t smap]
  (if (satisfies? ISubstituteStep t)
    (-substitute-step t smap)
    [t smap]))


(defn substitute
  [x smap]
  (first (substitute-step x smap)))


;; ---------------------------------------------------------------------
;; Compiler


(defprotocol ICompilePattern
  (-compile-pattern [p target inner-form env]))


(defn compile-pattern
  [p target inner-form env]
  (if (satisfies? ICompilePattern p)
    (-compile-pattern p target inner-form env)
    `(if (= ~target ~p)
       (list ~inner-form))))


(defn compile-ground
  {:private true}
  [t]
  (clojure.walk/postwalk
   (fn [x]
     (cond
       (symbol? x)
       (list 'quote x)

       (seq? x)
       (cons 'clojure.core/list x)

       :else
       x))
   t))


(defn derive-env
  ([t]
   (derive-env t #{}))
  ([t env]
   (into env (map (comp symbol name)) (variables t))))


(defn derive-envs
  ([ts]
   (derive-envs ts #{}))
  ([ts env]
   (reductions set/union env (map derive-env ts))))


(defn derive-sigs
  ([ts]
   (derive-sigs ts #{}))
  ([ts env]
   (let [envs (derive-envs ts env)
         ret-envs (rest (derive-envs ts env))]
     (sequence
      (map
       (fn [env ret-env]
         (let [diff (into (set/difference ret-env env)
                          (filter syntax/mut-symbol? ret-env))]
           [env ret-env diff])))
      envs
      ret-envs))))


(defn compile-patterns
  [ts target inner-form env]
  (let [sigs (derive-sigs ts env)]
    (reduce
     (fn [inner-form [t target [env ret-env diff]]]
       (let [ret-vals (vec diff)]
         `(sequence
           (mapcat
            (fn [~ret-vals]
              ~inner-form))
           ~(compile-pattern t target `(list ~ret-vals) env))))
     inner-form
     (reverse (sequence (map vector)
                        ts
                        (if (sequential? target)
                          target
                          (repeat target))
                        sigs)))))


;; ---------------------------------------------------------------------
;; Variable


(deftype Variable [sym]
  clojure.lang.Named
  (getName [_var]
    (.getName ^clojure.lang.Symbol sym))

  ICompilePattern
  (-compile-pattern [_var target inner-form env]
    (if (contains? env sym)
      `(if (= ~target ~sym)
         ~inner-form)
      `(let [~sym ~target]
         ~inner-form)))

  ISubstitutions
  (-substitutions [_var x smap]
    (if-some [[_ y] (find smap (name sym))]
      (if (= x y)
        (list smap))
      (list (assoc smap (name sym) x))))

  ISubstituteStep
  (-substitute-step [var smap]
    (if-some [[_ x] (find smap (name sym))]
      [x smap]
      [var smap]))

  IVariable

  IVariables
  (-variables [var]
    #{var}))


(defmethod print-method Variable [v ^java.io.Writer w]
  (if *debug* (.write "#meander/var "))
  (.write w (name v)))


(defn variable? [x]
  (instance? Variable x))



;; ---------------------------------------------------------------------
;; Mutable


(deftype Mutable [sym]
  clojure.lang.Named
  (getName [_mut]
    (.getName ^clojure.lang.Symbol sym))

  ICompilePattern
  (-compile-pattern [_mut target inner-form env]
    (if (contains? env sym)
      `(let [~sym (conj ~sym ~target)]
         ~inner-form)
      `(let [~sym [~target]]
         ~inner-form)))

  ISubstitutions
  (-substitutions [_mut x smap]
    (if-some [[_ y] (find smap (name sym))]
      (if (vector? y)
        (list (update smap (name sym) conj x)))
      (list (assoc smap (name sym) [x]))))

  ISubstituteStep
  (-substitute-step [mut smap]
    (if-some [[_ x] (find smap (name sym))]
      (if (and (coll? x) (seq x))
        [(first x) (assoc smap (name sym) (next x))]
        [mut smap])
      [mut smap]))

  IVariable

  IVariables
  (-variables [mut]
    #{mut}))


(defmethod print-method Mutable [v ^java.io.Writer w]
  (if *debug* (.write "#meander/mut "))
  (.write w (name v)))


(defn mutable? [x]
  (instance? Mutable x))


;; ---------------------------------------------------------------------
;; Nth


(def not-found
  (reify))


(deftype Nth [term index]
  ICompilePattern
  (-compile-pattern [_nth target inner-form env]
    (let [nth-target (gensym "nth__")]
      `(let [~nth-target (nth ~target ~index not-found)]
         (if (identical? ~nth-target not-found)
           nil
           ~(compile-patterns [term] nth-target inner-form env)))))

  ISubstitutions
  (-substitutions [_nth t smap]
    (let [u (nth t index not-found)]
      (if (identical? t not-found)
        nil
        (substitutions term u smap))))

  IVariables
  (-variables [_nth]
    (variables term)))


(defn nth?
  [x]
  (instance? Nth x))


(defn make-nth
  [term index]
  (if (nth? term)
    (Nth. (.-term ^Nth term) index)
    (Nth. term index)))


(defmethod print-method Nth [^Nth nth ^java.io.Writer w]
  (if *debug* (.write "#meander/nth["))
  (write-elems! [(.-term nth) (.-index nth)] w)
  (if *debug* (.write "]")))


;; ---------------------------------------------------------------------
;; Cat


(deftype Cat [terms]
  ICompilePattern
  (-compile-pattern [_cat target inner-form env]
    (compile-patterns (sequence (map make-nth) terms (range))
                      target
                      inner-form
                      env))

  IMinLength
  (-min-length [_cat]
    (count terms))

  IMaxLength
  (-max-length [_cat]
    (count terms))

  ISubstitutions
  (-substitutions [_cat t-coll smap]
    (sequence
     (lconj-xform
      (sequence (map make-nth) terms (range))
      (repeat t-coll))
     (list smap)))

  IVariables
  (-variables [_cat]
    (transduce (map variables) set/union #{} terms))

  clojure.lang.Seqable
  (seq [_cat]
    (seq terms)))


(defn cat? [x]
  (instance? Cat x))


(defn make-cat
  ^Cat [terms]
  {:pre [(or (sequential? terms)
             (cat? terms))
         (not (some variable-length? terms))]}
  (if (cat? terms)
    terms
    (Cat. terms)))


(defmethod print-method Cat [^Cat cat ^java.io.Writer w]
  (if *debug* (.write w "#meander/cat["))
  (write-elems! (.-terms cat) w)
  (if *debug* (.write w "]")))


(def seq-end
  (reify
    ICompilePattern
    (-compile-pattern [_seq-end target inner-form env]
      `(if (sequential? ~target)
         (if (seq ~target)
           nil
           ~inner-form)))

    IMinLength
    (-min-length [seq-end]
      0)

    ISubstitutions
    (-substitutions [_seq-end t smap]
      (if (sequential? t)
        (if (seq t)
          nil
          (list smap))))))


(defmethod print-method (class seq-end) [end ^java.io.Writer w]
  (if *debug*
    (.write w `seq-end)))


;; ---------------------------------------------------------------------
;; Partition
;;
;; A partition separates a two subsequences, variable length or
;; otherwise.


(deftype Partition [left right]
  ICompilePattern
  (-compile-pattern [part target inner-form env]
    (let [left-target (gensym "left__")
          right-target (gensym "right__")
          n (gensym "n__")
          min-left (-min-length left)
          min-right (-min-length right)]
      (case [(variable-length? left) (variable-length? right)]
        ([false false] [false true])
        `(let [~n ~min-left
               ~left-target (take ~n ~target)
               ~right-target (drop ~n ~target)]
           ~(compile-patterns [left right]
                              [left-target right-target]
                              inner-form
                              env))

        [true false]
        `(let [~n (max 0 (- (count ~target) ~min-right))
               ~left-target (take ~n ~target)
               ~right-target (drop ~n ~target)]
           ~(compile-patterns [left right]
                              [left-target right-target]
                              inner-form
                              env))

        [true true]
        `(sequence
          (mapcat
           (fn [[~left-target ~right-target]]
             ~(compile-patterns [left right]
                                [left-target right-target]
                                inner-form
                                env)))
          ;; TODO: This should create partitions such that the left
          ;; and right sides have *at least* min-length of left and
          ;; min-length of right terms respectively.
          (util/partitions 2 ~target)))))

  IMinLength
  (-min-length [_part]
    (+ (-min-length left)
       (-min-length right)))

  IMultipleSubstitutions
  (-multiple-substitutions? [part]
    (or (multiple-substitutions? left)
        (multiple-substitutions? right)
        (-variable-length? part)))

  ISubstitutions
  (-substitutions [_part t smap]
    (if (sequential? t)
      (case [(variable-length? left) (variable-length? right)]
        ([false false] [false true])
        (let [n (-min-length left)
              left-part (take n t)
              right-part (drop n t)]
          (if (= n (count left-part))
            (sequence
             (lconj-xform [left right]
                          [left-part right-part])
             (list smap))))

        [true false]
        (let [n (max 0 (- (count t)
                          (-min-length right)))
              left-part (take n t)
              right-part (drop n t)]
          ;; NOTE: We solve the right side first since it has a
          ;; non-variable length.
          (sequence
           (lconj-xform [right left]
                        [right-part left-part])
           (list smap)))

        [true true]
        (let [min-left (-min-length left)
              min-right (-min-length right)]
          (sequence
           (mapcat
            (fn [[left-part right-part]]
              ;; Avoid expending effort on parts that will fail
              ;; anyway.
              (if (and (<= min-left (count left-part))
                       (<= min-right (count right-part)))
                (sequence
                 (lconj-xform [right left]
                              [right-part left-part])
                 (list smap)))))
           ;; TODO: Create a specialized version of this function
           ;; which avoids creating left and right parts which don't
           ;; have at least min-left and min-right elements
           ;; respectively.
           (util/partitions 2 (vec t)))))))

  IVariableLength
  (-variable-length? [_part]
    (or (variable-length? left)
        (variable-length? right)))

  IVariables
  (-variables [_part]
    (set/union (variables left)
               (variables right)))

  clojure.lang.Seqable
  (seq [_part]
    (case [(seqable? left) (seqable? right)]
      [true true]
      (concat (seq left) (seq right))

      [true false]
      (concat (seq left) (list right))

      [false true]
      (concat (list left) (seq right))

      [false false]
      (list left right))))


(defn partition? [x]
  (instance? Partition x))


(defmethod print-method Partition [^Partition partition ^java.io.Writer w]
  (if *debug*
    (.write w "#meander/partition["))
  (print-method (.-left partition) w)
  (.write w " . ")
  (print-method (.-right partition) w)
  (if *debug*
    (.write w "]")))


#_
(t/testing "partition interpretation"
  (t/is (= (substitutions
            (Partition. (Cat. [1 2 (Variable. '?x)])
                        (Cat. [(Variable. '?y) 5 6]))
            [1 2 3 4 5 6])
           (list {"?x" 3, "?y" 4})))
  (t/is (= (substitutions
            (Partition. (Cat. [1 2 (Variable. '?x)])
                        (Cat. [(Variable. '?x) 5 6]))
            [1 2 3 4 5 6])
           (list))))


#_
(deftype Partition [left right]
  ISubstituteStep
  (-substitute-step [part smap]
    (let [[left* smap*] (substitute-step left smap)
          [right* smap**] (substitute-step right smap*)]
      [(concat left* right*) smap**])))


;; ---------------------------------------------------------------------
;; Rep
;;
;; A Rep represents variable length repeating subsequence.


(deftype Rep [items]
  ICompilePattern
  (-compile-pattern [sseq target inner-form env]
    (let [cat (make-cat items)
          ret-env (derive-env items env)
          unbound-env (set/difference ret-env env)
          ret-vals (conj (vec ret-env) target)
          slice (gensym "slice__")
          f (gensym "loop__")
          n (-min-length cat)]
      `(or (seq
            (sequence
             (mapcat
              (fn ~f [~ret-vals]
                (let [~target (drop ~n ~target)
                      ~slice (take ~n ~target)]
                  (or (seq
                       (sequence
                        (mapcat ~f)
                        ~(compile-pattern cat slice `(list ~ret-vals) ret-env)))
                      ~inner-form))))
             ~(compile-pattern cat target `(list ~ret-vals) env)))
           (let [~@(sequence
                    (mapcat
                     (fn [var-sym]
                       (let [default (cond
                                       (syntax/var-symbol? var-sym)
                                       `(Variable. '~var-sym)

                                       (syntax/mut-symbol? var-sym)
                                       [])]
                         [var-sym default])))
                    unbound-env)]
             ~inner-form))))

  IMultipleSubstitutions
  (-multiple-substitutions? [_sseq]
    true)

  ISubstitutions
  (-substitutions [rep t smap]
    (if (sequential? t)
      (if (seq t)
        (-substitutions (Partition. (make-cat items) rep) t smap)
        (list smap))))

  IMinLength
  (-min-length [_sseq]
    0)
  
  IVariableLength
  (-variable-length? [_sseq]
    true)

  IVariables
  (-variables [_rep]
    (transduce (map variables) into #{} items)))


#_
(deftype Rep [init]
  ISubstituteStep
  (-substitute-step [sseq smap]
    (let [[xs smap*] (reduce
                      (fn [[ret smap*] x]
                        (let [[x* smap*] (substitute-step x smap*)]
                          [(conj ret x*) smap*]))
                      [[] smap]
                      init)]
      (if (= init xs)
        [[] smap]
        (let [[ys smap**] (-substitute-step sseq smap*)]
          [(concat xs ys) smap**])))))


(defmethod print-method Rep [^Rep rep ^java.io.Writer w]
  (if *debug*
    (.write w "#meander/rep["))
  (write-elems! (.-items rep) w)
  (if *debug*
    (.write w "]")
    (.write w " ...")))


(defn rep? [x]
  (instance? Rep x))


(defn coll->partition* [coll]
  (if (seq coll)
    (Partition. (first coll) (coll->partition* (next coll)))
    seq-end))

(def x-concat
  (comp
   (mapcat
    (fn [x]
      (if (partition? x)
        (seq x)
        (list x))))
   (mapcat
    (fn [x]
      (if (cat? x)
        (seq x)
        (list x))))
   (mapcat
    (fn [x]
      (if (nth? x)
        (list (.-term ^Nth x))
        (list x))))))

(defn coll->partition
  [coll]
  (coll->partition*
   (sequence
    (comp
     x-concat
     (partition-by variable-length?)
     (mapcat
      (fn [coll]
        (if (variable-length? (first coll))
          coll
          (list (make-cat coll))))))
    coll)))


;; ---------------------------------------------------------------------
;; Vector

#_
(deftype VecTerm [term]
  )


(extend-type clojure.lang.IPersistentVector
  ICompilePattern
  (-compile-pattern [ivec target inner-form env]
    (if (ground? ivec)
      `(if (= ~target ~(compile-ground ivec))
         ~inner-form)
      `(if (vector? ~target)
         ~(compile-patterns ivec target inner-form env))))

  IMinLength
  (-min-length [ivec]
    (transduce
     (comp
      x-concat
      (map
       (fn [x]
         (if (variable-length? x)
           0
           1))))
     +
     0
     ivec))
  
  IMultipleSubstitutions
  (-multiple-substitutions? [ivec]
    (some multiple-substitutions? ivec))
  
  ISubstitutions
  (-substitutions [u-vec v-vec smap]
    (when (vector? v-vec)
      (-substitutions (coll->partition u-vec) v-vec smap)))

  IVariables
  (-variables [ivec]
    (transduce (map variables) into #{}) ivec)

  IVariableLength
  (-variable-length? [ivec]
    (some variable-length? ivec)))

#_
(extend-type clojure.lang.IPersistentVector
  ISubstituteStep
  (-substitute-step [v smap]
    (reduce
     (fn [[v* smap*] x]
       (let [[x* smap*] (substitute-step x smap*)
             v* (if (or (partition? x)
                        (rep? x))
                  (into v* x*)
                  (conj v* x*))]
         [v* smap*]))
     [[] smap]
     v)))


;; ---------------------------------------------------------------------
;; Seq


(extend-type clojure.lang.ISeq
  ICompilePattern
  (-compile-pattern [iseq target inner-form env]
    (if (ground? iseq)
      ;; This check prevents the dreaded "Unknown Collection type"
      ;; error.
      (if (= iseq ())
        `(if (= ~target ())
           ~inner-form)
        `(if (= ~target ~(compile-ground iseq))
           ~inner-form)))
    `(if (seq? ~target)
       ~(compile-patterns iseq target inner-form env)))

  IMinLength
  (-min-length [iseq]
    (transduce
     (comp
      x-concat
      (map
       (fn [x]
         (if (variable-length? x)
           0
           1))))
     +
     0
     iseq))

  IMultipleSubstitutions
  (-multiple-substitutions? [iseq]
    (some multiple-substitutions? iseq))

  ISubstitutions
  (-substitutions [u-seq v-seq smap]
    (when (seq? v-seq)
      (-substitutions (coll->partition u-seq) v-seq smap)))

  IVariableLength
  (-variable-length? [iseq]
    (some variable-length? iseq))

  IVariables
  (-variables [iseq]
    (transduce (map variables) into #{} iseq)))


#_
(extend-type clojure.lang.ISeq
  ISubstituteStep
  (-substitute-step [s smap]
    (let [s* (transient [])
          smap*
          (reduce
           (fn [smap* x]
             (let [[x* smap*] (substitute-step x smap*)]
               (if (or (partition? x)
                       (rep? x))
                 (run! (fn [x] (conj! s* x)) x*)
                 (conj! s* x*))
               smap*))
           smap
           s)]
      [(seq (persistent! s*)) smap*])))


;; ---------------------------------------------------------------------
;; Parse


(defmulti parse-form*
  {:arglists '([x])}
  (fn tag [x] (first x)))


(defmethod parse-form* :term [[_ x]]
  (parse-form* x))


(defmethod parse-form* :lit [[_ x]]
  x)


(defmethod parse-form* :quo [[_ [_ x]]]
  x)


(defmethod parse-form* :var [[_ sym]]
  (Variable. sym))


(defmethod parse-form* :mut [[_ sym]]
  (Mutable. sym))


(defmethod parse-form* :vec [[_ x]]
  ;; Use VecTerm here.
  (vector (parse-form* x)))


(defmethod parse-form* :seq [[_ x]]
  ;; Use SeqTerm here.
  (list (parse-form* x)))


(defmethod parse-form* :rep [[_ {init :init}]]
  (Rep. (map parse-form* init)))


(defmethod parse-form* :cat [[_ items]]
  (make-cat (map parse-form* items)))


(defmethod parse-form* :part [[_ {:keys [left right]}]]
  (Partition. (parse-form* left)
              (if (some? right)
                (parse-form* right)
                seq-end)))


(defn parse-form [form]
  (let [result (syntax/parse-term form)]
    (if (= ::s/invalid result)
      form
      (parse-form* result))))


;; ---------------------------------------------------------------------
;; 

(defn t-collapse-clauses
  [t-clauses]
  (reduce
   (fn [clauses* [clause-key clause-form :as clause]]
     (let [[clause*-key clause*-form] (peek clauses*)]
       (if (= clause-key clause*-key)
         (conj (pop clauses*)
               (case clause-key
                 :let
                 [:let (into clause*-form clause-form)]

                 :when
                 [:when `(and ~clause*-form
                              ~clause-form)]))
         (conj clauses* clause))))
   [(first t-clauses)]
   (rest t-clauses)))


(defmacro matcher
  ([form ret-form]
   (let [this (gensym "matcher__")
         target (gensym "target__")
         inner-form `(list ~ret-form)]
     `(reify
        clojure.lang.IFn
        (invoke [~this ~target]
          ~(compile-pattern (parse-form form) target inner-form #{}))))))

#_
(substitutions [(Variable. '?x) (Variable. '?x)] [1 1])

#_
(let [m (matcher (?x !x ... . !y ... )
                 {"?x" ?x, "!x" !x, "!y" !y})
      t (list 1 2 3)]
  (m t)
  #_
  (t/is (= (m t)
           (list {"?x" 1, "!x" [2 3]}))))

#_
(let [target 'target
      inner-form '(list [?x !x])
      env #{}
      ]
  (eval
   (clean-form
    `(let [~target (list 1 2 2 3)]
       ~(compile-pattern (parse-form '(?x . !x ...))
                         target
                         inner-form
                         env)))))


#_
(substitutions
 (Partition.
  (Partition.
   (Cat. [1 2 3])  
   (Rep. [(Mutable. '!x) (Mutable. '!y)]))
  (Rep. [(Mutable. '!a) (Mutable. '!b)]))
 [1 2 3 1 2 1 2])

