(ns meander.dev.core
  (:refer-clojure :exclude [bound?
                            compile
                            extend
                            resolve])
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


(defn var-sym [var]
  (symbol (name var)))


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


(defprotocol IMultipleUnifiers
  (-multiple-unifiers? [t]))


(defn multiple-unifiers?
  "true if x may have more than one unifier (substitution) under
  unification."
  [x]
  (and (satisfies? IMultipleUnifiers x)
       (boolean (-multiple-unifiers? x))))


(defn match?
  "true if x can have no more than one unifier for a given term t
  under unification."
  [x]
  (not (multiple-unifiers? x)))


(defprotocol IMinLength
  (-min-length [t]))


(defn min-length [t]
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


(defprotocol IUnifiers
  (-unifiers [u v smap]))


(defn iunifiers?
  [x]
  (satisfies? IUnifiers x))


(defn unifiers
  ([u v]
   (unifiers u v {}))
  ([u v smap]
   (cond
     (iunifiers? u)
     (-unifiers u v smap)

     (iunifiers? v)
     (recur v u smap)

     :else
     (if (= u v)
       (list smap)))))


(defn bound?
  [smap ivar]
  (contains? smap (name ivar)))


(defn resolve
  [smap ivar not-found]
  (if-some [[_ x] (find smap (name ivar))]
    (if (ivariable? x)
      (recur smap x not-found)
      x)
    not-found))


(defn extend-no-check
  [smap ivar val]
  (assoc smap (name ivar) val))


(defn lconj-xform
  {:private true}
  [u-coll v-coll]
  (apply comp
    (sequence
     (map
      (fn [u v]
        (mapcat
         (fn [smap]
           (unifiers u v smap)))))
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
;; Compilation


(defprotocol ICompile
  (-compile [x target inner-form env]))


(def empty-env
  {})


(defn add-var [env var]
  (assoc env var (symbol (name var))))


(defn derive-env
  ([t]
   (derive-env t empty-env))
  ([t env]
   (into env
         (map (juxt identity (comp symbol name)))
         (variables t))))


(defn derive-sig
  ([t]
   (derive-sig t empty-env))
  ([t env]
   (let [ret-env (derive-env t env)
         diff-env (reduce dissoc ret-env (keys env))]
     [env ret-env diff-env])))


(defn compile
  [x target inner-form env]
  (if (satisfies? ICompile x)
    (-compile x target inner-form env)
    (cond
      (= x '())
      `(if (= ~target ())
         ~inner-form)

      (seq? x)
      `(if (= ~target (quote ~x))
         ~inner-form)

      (symbol? x)
      `(if (= ~target (quote ~x))
         ~inner-form)

      :else
      `(if (= ~target ~x)
         ~inner-form))))


(defn compile-many [pats targets inner-form env]
  (let [[pat1 pat2 & rest-pats] pats
        [target1 target2 & rest-targets] targets]
    (cond
      (and pat1 pat2)
      (let [[env1 ret-env1 diff-env1] (derive-sig pat1 env)
            [env2 ret-env2] (derive-sig pat2 ret-env1)]
        (if (match? pat2)
          (compile pat1
                   target1
                   (compile pat2
                            target2
                            (compile-many rest-pats
                                          rest-targets
                                          inner-form
                                          ret-env2)
                            env2)
                   env1)
          (let [ret-vals (into [] (map (comp symbol name)) diff-env1)]
            `(sequence
              (mapcat
               (fn [~ret-vals]
                 ~(compile pat2
                           target2
                           (compile-many rest-pats
                                         rest-targets
                                         inner-form
                                         ret-env2)
                           env2)))
              ~(compile pat1 target1 ret-vals env1))))) 

      pat1
      (compile pat1 target1 inner-form env)

      :else
      inner-form)))


;; ---------------------------------------------------------------------
;; Nothing


(def nothing
  (reify
    ICompile
    (-compile [_any _target _inner-form _smap]
      nil)

    IUnifiers
    (-unifiers [_any _t _smap]
      nil)

    IMultipleUnifiers
    (-multiple-unifiers? [_any]
      false) 

    IVariables
    (-variables [_any]
      #{})

    IVariableLength
    (-variable-length? [_any]
      false)

    ISubstituteStep
    (-substitute-step [nothing smap]
      [nothing smap])))


;; ---------------------------------------------------------------------
;; Any


(def any
  (reify
    ICompile
    (-compile [_any target inner-form smap]
      inner-form)

    IUnifiers
    (-unifiers [_any t smap]
      (if (identical? any t)
        nil
        (list smap)))

    IMultipleUnifiers
    (-multiple-unifiers? [_any]
      false) 

    IVariables
    (-variables [_any]
      #{})

    IVariableLength
    (-variable-length? [_any]
      false)

    ISubstituteStep
    (-substitute-step [any smap]
      [any smap])))


(defmethod print-method (class any) [_ ^java.io.Writer w]
  (if *debug*
    (.write w "#meander/any "))
  (.write w "_"))


;; ---------------------------------------------------------------------
;; Variable


(deftype LVar [^clojure.lang.Symbol sym]
  clojure.lang.Named
  (getName [_var]
    (.getName sym))


  ICompile
  (-compile [var target inner-form env]
    (if (contains? env var)
      `(if (= ~target ~sym)
         ~inner-form)
      `(let [~sym ~target]
         ~inner-form)))

  IUnifiers
  (-unifiers [_var x smap]
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
    #{var})

  Object
  (equals [_var that]
    (and (instance? LVar that)
         (= (.-sym ^LVar that)
            sym)))

  (hashCode [this]
    (.hashCode sym)))


(defn make-lvar
  [sym]
  {:pre [(symbol? sym)]}
  (LVar. sym))


(defn lvar? [x]
  (instance? LVar x))


(defn lvars [t]
  (into #{} (filter lvar?) (variables t)))


(defmethod print-method LVar [v ^java.io.Writer w]
  (if *debug* (.write w "#meander/lvar "))
  (.write w (name v)))


;; ---------------------------------------------------------------------
;; MemVar


(deftype MemVar [sym]
  clojure.lang.Named
  (getName [_mem]
    (.getName ^clojure.lang.Symbol sym))

  ICompile
  (-compile [mem target inner-form env]
    (if (contains? env mem)
      `(let [~sym (conj ~sym ~target)]
         ~inner-form)
      `(let [~sym [~target]]
         ~inner-form)))

  IUnifiers
  (-unifiers [_mem x smap]
    (if-some [[_ y] (find smap (name sym))]
      (if (vector? y)
        (list (update smap (name sym) conj x)))
      (list (assoc smap (name sym) [x]))))

  ISubstituteStep
  (-substitute-step [mem smap]
    (if-some [[_ x] (find smap (name sym))]
      (if (and (coll? x) (seq x))
        [(first x) (assoc smap (name sym) (next x))]
        [mem smap])
      [mem smap]))

  IVariable

  IVariables
  (-variables [mem]
    #{mem})

  Object
  (equals [_var that]
    (and (instance? MemVar that)
         (= (.-sym ^MemVar that)
            sym)))

  (hashCode [this]
    (.hashCode sym)))


(defmethod print-method MemVar [v ^java.io.Writer w]
  (if *debug* (.write w "#meander/mem "))
  (.write w (name v)))


(defn make-mem-var [sym]
  (MemVar. sym))


(defn mem-var? [x]
  (instance? MemVar x))


(defn mem-vars [t]
  (into #{} (filter mem-var?) (variables t)))


;; ---------------------------------------------------------------------
;; Cap


(deftype Cap [pat var]
  ICompile
  (-compile [cap target inner-form env]
    (compile-many [var pat]
                  [target target]
                  inner-form
                  env))

  IUnifiers
  (-unifiers [_cap t smap]
    (sequence
     (mapcat
      (fn [smap]
        (unifiers var t smap)))
     (unifiers pat t smap)))

  IMinLength
  (-min-length [_cap]
    (-min-length pat))

  IMultipleUnifiers
  (-multiple-unifiers? [_cap]
    (multiple-unifiers? pat))

  IVariables
  (-variables [_cap]
    (conj (variables pat) var))

  IVariableLength
  (-variable-length? [_cap]
    (variable-length? pat)))


(defn make-cap
  [pat var]
  {:pre [(satisfies? IVariable var)]}
  (Cap. pat var))


(defn cap? [x]
  (instance? Cap x))


(defmethod print-method Cap [^Cap cap ^java.io.Writer w]
  (if *debug*
    (.write w "#meander/cap"))
  (.write w "(")
  (print-method (.-pat cap) w)
  (.write w " :as ")
  (print-method (.-var cap) w)
  (.write w ")"))


;; ---------------------------------------------------------------------
;; Nth


(deftype Nth [term index]
  ICompile
  (-compile [nth target inner-form env]
    (let [nth-sym (gensym (str "nth__" index "__"))]
      `(let [~nth-sym (nth ~target ~index nothing)]
         (if (identical? ~nth-sym nothing)
           nil
           ~(compile term nth-sym inner-form env)))))

  IUnifiers
  (-unifiers [_nth t smap]
    (let [u (nth t index nothing)]
      (if (identical? u nothing)
        nil
        (unifiers term u smap))))

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
  (if *debug* (.write w "#meander/nth["))
  (write-elems! [(.-term nth) (.-index nth)] w)
  (if *debug* (.write w "]")))


;; ---------------------------------------------------------------------
;; Cat


(deftype Cat [terms]
  ICompile
  (-compile [cat target inner-form env]
    (let [nths (sequence (map make-nth) terms (range))]
      (compile-many nths (repeat target) inner-form env)))
  
  IMaxLength
  (-max-length [_cat]
    (count terms))

  IMinLength
  (-min-length [_cat]
    (count terms))

  IMultipleUnifiers
  (-multiple-unifiers? [_cat]
    (some multiple-unifiers? terms))

  IUnifiers
  (-unifiers [_cat t-coll smap]
    (if (and (ground? terms)
             (= t-coll terms))
      (list smap)
      (sequence
       (lconj-xform
        (sequence (map make-nth) terms (range))
        (repeat t-coll))
       (list smap))))

  IVariables
  (-variables [_cat]
    (transduce (map variables) set/union #{} terms))

  IVariableLength
  (-variable-length? [_cat]
    false)

  clojure.lang.Seqable
  (seq [_cat]
    (seq terms)))


(defn cat? [x]
  (instance? Cat x))


(defn make-cat
  ^Cat [pats]
  {:pre [(or (sequential? pats)
             (cat? pats))]}
  (if (cat? pats)
    pats
    (Cat. pats)))


(defmethod print-method Cat [^Cat cat ^java.io.Writer w]
  (if *debug* (.write w "#meander/cat["))
  (write-elems! (.-terms cat) w)
  (if *debug* (.write w "]")))


(def seq-end
  (reify
    ICompile
    (-compile [_seq-end target inner-form env]
      `(if (seq ~target)
         nil
         ~inner-form))
    
    IMinLength
    (-min-length [seq-end]
      0)

    IUnifiers
    (-unifiers [_seq-end t smap]
      (if (sequential? t)
        (if (seq t)
          nil
          (list smap))))))


(defmethod print-method (class seq-end) [_ ^java.io.Writer w]
  (if *debug*
    (.write w (str `seq-end))))


;; ---------------------------------------------------------------------
;; Partition
;;
;; A partition separates a two subsequences, variable length or
;; otherwise.


(deftype Partition [left right]
  ICompile
  (-compile [part target inner-form env]
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
           ~(compile-many [left right]
                          [left-target right-target]
                          inner-form
                          env))

        [true false]
        `(let [~n (max 0 (- (count ~target) ~min-right))
               ~left-target (take ~n ~target)
               ~right-target (drop ~n ~target)]
           ~(compile-many [left right]
                          [left-target right-target]
                          inner-form
                          env))

        [true true]
        `(sequence
          (mapcat
           (fn [[~left-target ~right-target]]
             ~(compile-many [left right]
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

  IMultipleUnifiers
  (-multiple-unifiers? [part]
    (or (multiple-unifiers? left)
        (multiple-unifiers? right)
        (and (variable-length? right)
             (variable-length? left))))

  IUnifiers
  (-unifiers [_part t smap]
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


(defn make-partition
  ([left]
   (Partition. left seq-end))
  ([left right]
   (Partition. left right)))


(defmethod print-method Partition [^Partition partition ^java.io.Writer w]
  (if *debug*
    (.write w "#meander/partition["))
  (print-method (.-left partition) w)
  (when (not (identical? (.-right partition) seq-end))
    (.write w " . ")
    (print-method (.-right partition) w))
  (if *debug*
    (.write w "]")))

#_
(t/testing "partition interpretation"
  (t/is (= (unifiers
            (Partition. (Cat. [1 2 (make-lvar '?x)])
                        (Cat. [(make-lvar '?y) 5 6]))
            [1 2 3 4 5 6])
           (list {"?x" 3, "?y" 4})))
  (t/is (= (unifiers
            (Partition. (Cat. [1 2 (make-lvar '?x)])
                        (Cat. [(make-lvar '?x) 5 6]))
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

(deftype Rep [term min-length]
  ICompile
  (-compile [rep target inner-form env]
    (if (match? term)
      (let [slice (gensym "slice__")
            k (gensym "k__")
            n (-min-length term)
            loop-env (derive-env term env)
            mem-vars (into [] (filter mem-var?) (keys loop-env))
            mem-syms (into [] (map var-sym) mem-vars)]
        `(let [~target ~target
               ~slice (take ~n ~target)
               ~k ~min-length
               ~@(sequence
                  (mapcat
                   (fn [mem-var mem-sym]
                     (if (contains? env mem-var)
                       [mem-sym mem-sym]
                       [mem-sym []])))
                  mem-vars
                  mem-syms)]
           (if (== ~n (count ~slice))
             ~(compile term
                       slice
                       `(loop [~target (drop ~n ~target)
                               ~k ~(dec min-length)
                               ~@(sequence
                                  (mapcat (fn [x] [x x]))
                                  mem-syms)]
                          (let [~slice (take ~n ~target)]
                            (if (== ~n (count ~slice))
                              ~(compile term
                                        slice
                                        `(recur (drop ~n ~target)
                                                (dec ~k)
                                                ~@mem-syms)
                                        loop-env)
                              (if (<= ~k 0)
                                ~inner-form))))
                       env)
             (if (<= ~k 0)
               ~inner-form))))
      (undefined)))

  IMultipleUnifiers
  (-multiple-unifiers? [_rep]
    (-multiple-unifiers? term))

  IUnifiers
  (-unifiers [rep t smap]
    (if (sequential? t)
      (if (seq t)
        ;; This is gonna be slow.
        (let [n (-min-length term)
              m (count t)]
          (if (and (== 0 (mod m n))
                   (<= min-length m))
            (sequence
             (reduce
              (fn [xform slice]
                (comp xform
                      (mapcat
                       (fn [smap]
                         (unifiers term slice smap)))))
              identity
              (partition n t))
             (list smap))))
        (if (zero? min-length)
          (list smap)))))

  IMinLength
  (-min-length [_rep]
    min-length)
  
  IVariableLength
  (-variable-length? [_rep]
    true)

  IVariables
  (-variables [_rep]
    (variables term)))

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


(defn rep? [x]
  (instance? Rep x))


(defmethod print-method Rep [^Rep rep ^java.io.Writer w]
  (if *debug*
    (.write w "#meander/rep["))
  (print-method (.-term rep) w)
  (if *debug*
    (.write w "]")
    (.write w " ...")))


;; ---------------------------------------------------------------------
;; Rest

(deftype Rest [mem-var]
  ICompile
  (-compile [_rest target inner-form env]
    (prn :compile-rest)
    (if (contains? env mem-var)
      `(let [~(var-sym mem-var) (into ~(var-sym mem-var) ~target)]
         ~inner-form)
      `(let [~(var-sym mem-var) (vec ~target)]
         ~inner-form)))

  IMinLength
  (-min-length [_rest]
    0)

  IMultipleUnifiers
  (-multiple-unifiers? [_rep]
    false)

  ISubstituteStep
  (-substitute-step [_rest smap]
    [(get smap (name mem-var) [])
     (assoc smap (name mem-var) [])])

  IUnifiers
  (-unifiers [_rest t smap]
    (if (sequential? t)
      (if (bound? smap mem-var)
        (list (update smap (name mem-var) into t))
        (list (assoc smap (name mem-var) (vec t))))))

  IVariables
  (-variables [_rest]
    #{mem-var})

  IVariableLength
  (-variable-length? [_rest]
    false))


(defn rest? [x]
  (instance? Rest x))


(defmethod print-method Rest [^Rest rest ^java.io.Writer w]
  (if *debug*
    (.write w "#meander/rest["))
  (print-method (.-mem-var rest) w)
  (if *debug*
    (.write w "]")
    (.write w " ...")))

;; ---------------------------------------------------------------------
;; Vector


(defn coll->partition* [coll]
  (if (seq coll)
    (Partition. (first coll) (coll->partition* (next coll)))
    seq-end))


;; This needs a better name.
(def
  ^{:private true}
  x-concat
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



(extend-type clojure.lang.IPersistentVector
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
  
  IMultipleUnifiers
  (-multiple-unifiers? [ivec]
    (some multiple-unifiers? ivec))

  IUnifiers
  (-unifiers [u-vec v-vec smap]
    (when (vector? v-vec)
      (-unifiers (coll->partition u-vec) v-vec smap)))

  IVariables
  (-variables [ivec]
    (transduce (map variables) into #{} ivec))

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


(deftype VecTerm [term min-length multiple-unifiers? variables variable-length?]
  ICompile
  (-compile [_seq-term target inner-form env]
    `(if (vector? ~target)
       ~(compile term target inner-form env)))

  IMinLength
  (-min-length [_vec-term]
    min-length)

  IMultipleUnifiers
  (-multiple-unifiers? [_vec-term]
    multiple-unifiers?)

  IUnifiers
  (-unifiers [_vec-term u smap]
    (when (vector? u)
      (-unifiers term u smap)))

  IVariables
  (-variables [_vec_term]
    variables)

  IVariableLength
  (-variable-length? [_vec-term]
    variable-length?))


(defmethod print-method VecTerm [^VecTerm vec-term ^java.io.Writer w]
  (if *debug*
    (.write w "#meander/vec-term"))
  (.write w "[")
  (print-method (.-term vec-term) w)
  (.write w "]"))


(defn vec-term-no-check
  {:private true}
  [t]
  (let [min-length (-min-length t)
        multiple-unifiers? (-multiple-unifiers? t)
        variables (-variables t)
        variable-length? (-variable-length? t)]
    (VecTerm. t
              min-length
              multiple-unifiers?
              variables
              variable-length?)))


(defn vec-term [coll]
  {:pre [(coll? coll)]}
  (vec-term-no-check (coll->partition coll)))


;; ---------------------------------------------------------------------
;; Seq


(extend-type clojure.lang.ISeq
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

  IMultipleUnifiers
  (-multiple-unifiers? [iseq]
    (some multiple-unifiers? iseq))

  IUnifiers
  (-unifiers [u-seq v-seq smap]
    (when (seq? v-seq)
      (-unifiers (coll->partition u-seq) v-seq smap)))

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


(deftype SeqTerm [term min-length multiple-unifiers? variables variable-length?]
  ICompile
  (-compile [_seq-term target inner-form env]
    `(if (seq? ~target)
       ~(compile term target inner-form env)))

  IMinLength
  (-min-length [_seq-term]
    min-length)

  IMultipleUnifiers
  (-multiple-unifiers? [_seq-term]
    multiple-unifiers?)

  IUnifiers
  (-unifiers [_seq-term u smap]
    (when (seq? u)
      (-unifiers term u smap)))

  IVariables
  (-variables [_seq_term]
    variables)

  IVariableLength
  (-variable-length? [_seq-term]
    variable-length?))


(defmethod print-method SeqTerm [^SeqTerm seq-term ^java.io.Writer w]
  (if *debug*
    (.write w "#meander/seq-term"))
  (.write w "(")
  (print-method (.-term seq-term) w)
  (.write w ")"))


(defn seq-term-no-check
  {:private true}
  [t]
  (let [min-length (-min-length t)
        multiple-unifiers? (-multiple-unifiers? t)
        variables (-variables t)
        variable-length? (-variable-length? t)]
    (SeqTerm. t
              min-length
              multiple-unifiers?
              variables
              variable-length?)))


(defn seq-term [coll]
  {:pre [(coll? coll)]}
  (seq-term-no-check (coll->partition coll)))


;; ---------------------------------------------------------------------
;; Parse


(defmulti parse-form*
  {:arglists '([x])}
  (fn tag [x] (first x)))


(defmethod parse-form* :any [_]
  any)


(defmethod parse-form* :cap [[_ {:keys [pat var]}]]
  (let [pat (parse-form* pat)
        var (parse-form* var)]
    (Cap. pat var)))


(defmethod parse-form* :cat [[_ items]]
  (make-cat (map parse-form* items)))


(defmethod parse-form* :lit [[_ x]]
  x)


(defmethod parse-form* :mem [[_ sym]]
  (make-mem-var sym))


(defmethod parse-form* :part [[_ {:keys [left right]}]]
  (Partition. (parse-form* left)
              (parse-form* right)))


(defmethod parse-form* :quo [[_ [_ x]]]
  x)

(defmethod parse-form* :rest [[_ {var :var}]]
  (Rest. (parse-form* var)))


(defmethod parse-form* :rep [[_ {init :init}]]
  (Rep. (parse-form* init) 0))


(defmethod parse-form* :repk [[_ {init :init k :k}]]
  (Rep. (parse-form* init) k))


(defmethod parse-form* :seq [[_ x]]
  (seq-term-no-check (parse-form* x)))


(defmethod parse-form* :term [[_ x]]
  (parse-form* x))


(defmethod parse-form* :var [[_ sym]]
  (make-lvar sym))


(defmethod parse-form* :vec [[_ x]]
  (vec-term-no-check (parse-form* x)))


(defmethod parse-form* :err/bad-top-level-cap [[_ pat-data]]
  (throw (ex-info "Top level capture pattern may not be a subsequence pattern"
                  ;; TODO: Would be nice to have the actual form here.
                  {:pat (parse-form* (:pat pat-data))
                   :var (parse-form* (:var pat-data))})))


(defmethod parse-form* :seq-end [_]
  seq-end)


(defn parse-form [form]
  (let [result (syntax/parse-term form)]
    (if (= ::s/invalid result)
      form
      (parse-form* result))))


;; ---------------------------------------------------------------------


(defmacro unifier
  {:arglists '([form *when-clauses expr])
   :style/indent :defn}
  [& args]
  (let [form (first args)
        *when-clauses (partition 2 (butlast (rest args)))
        expr (last args)
        vars (variables (parse-form form))]
    `(let [m# (parse-form '~form)]
       (fn [t#]
         (sequence
          (keep
           (fn [{:strs [~@(map (comp symbol name) vars)]}]
             (when (and ~@(map second *when-clauses)) ~expr))) (seq (unifiers m# t#)))))))


(defmacro matcher
  {:arglists '([pattern expr])
   :style/indent :defn}
  [form expr]
  (let [target (gensym "target__")
        pat (parse-form form)]
    `(fn [~target]
       ~(compile pat target expr empty-env))))


#_
(let [m (matcher (fn [!bindings ...] . !body ...)
          [!bindings !body])
      u (unifier (fn [!bindings ...] . !body ...)
          [!bindings !body])
      n 1000]
  (time
    (dotimes [_ n]
      (m '(fn [a] b c))))
  (time
    (dotimes [_ n]
      (u '(fn [a] b c)))))


