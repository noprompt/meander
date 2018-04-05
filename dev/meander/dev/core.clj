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
;; Any


(def any
  (reify
    ISubstitutions
    (-substitutions [_ t smap]
      (if (identical? any t)
        nil
        (list smap)))))


(defmethod print-method (class any) [_ ^java.io.Writer w]
  (if *debug*
    (.write w "#meander/any"))
  (.write w "_"))


;; ---------------------------------------------------------------------
;; Cap

(deftype Cap [pat var]
  ISubstitutions
  (-substitutions [_cap t smap]
    (sequence
     (mapcat
      (fn [smap]
        (substitutions var t smap)))
     (substitutions pat t smap)))

  IMinLength
  (-min-length [_cap]
    (-min-length pat))

  IMultipleSubstitutions
  (-multiple-substitutions? [_cap]
    (multiple-substitutions? pat))

  IVariables
  (-variables [_cap]
    (conj (variables pat) var))

  IVariableLength
  (-variable-length? [_cap]
    (variable-length? pat)))


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
;; Variable


(deftype Variable [^clojure.lang.Symbol sym]
  clojure.lang.Named
  (getName [_var]
    (.getName sym))

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
    #{var})

  Object
  (equals [_var that]
    (and (instance? Variable that)
         (= (.-sym ^Variable that)
            sym)))

  (hashCode [this]
    (.hashCode sym)))


(defmethod print-method Variable [v ^java.io.Writer w]
  (if *debug* (.write "#meander/var "))
  (.write w (name v)))


(defn variable? [x]
  (instance? Variable x))



;; ---------------------------------------------------------------------
;; Memo


(deftype Memo [sym]
  clojure.lang.Named
  (getName [_mem]
    (.getName ^clojure.lang.Symbol sym))

  ISubstitutions
  (-substitutions [_mem x smap]
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
    #{mem}))


(defmethod print-method Memo [v ^java.io.Writer w]
  (if *debug* (.write "#meander/mem "))
  (.write w (name v)))


(defn mem? [x]
  (instance? Memo x))


(def
  ^{:private true}
  not-found (reify))


;; ---------------------------------------------------------------------
;; Nth


(deftype Nth [term index]
  ISubstitutions
  (-substitutions [_nth t smap]
    (let [u (nth t index not-found)]
      (if (identical? u not-found)
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
  IMaxLength
  (-max-length [_cat]
    (count terms))

  IMinLength
  (-min-length [_cat]
    (count terms))

  IMultipleSubstitutions
  (-multiple-substitutions? [_cat]
    (some multiple-substitutions? terms))

  ISubstitutions
  (-substitutions [_cat t-coll smap]
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
  ^Cat [terms]
  {:pre [(or (sequential? terms)
             (cat? terms))]}
  (if (cat? terms)
    terms
    (Cat. terms)))


(defmethod print-method Cat [^Cat cat ^java.io.Writer w]
  (if *debug* (.write w "#meander/cat["))
  (write-elems! (.-terms cat) w)
  (if *debug* (.write w "]")))


(def seq-end
  (reify
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


(deftype Rep [term min-length]
  IMultipleSubstitutions
  (-multiple-substitutions? [_sseq]
    true)

  ISubstitutions
  (-substitutions [rep t smap]
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
                         (substitutions term slice smap)))))
              identity
              (partition n t))
             (list smap))))
        (if (zero? min-length)
          (list smap)))))

  IMinLength
  (-min-length [_sseq]
    min-length)
  
  IVariableLength
  (-variable-length? [_sseq]
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


(defmethod print-method Rep [^Rep rep ^java.io.Writer w]
  (if *debug*
    (.write w "#meander/rep["))
  (print-method (.-term rep) w)
  (if *debug*
    (.write w "]")
    (.write w " ...")))


(defn rep? [x]
  (instance? Rep x))


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
  
  IMultipleSubstitutions
  (-multiple-substitutions? [ivec]
    (some multiple-substitutions? ivec))
  
  ISubstitutions
  (-substitutions [u-vec v-vec smap]
    (when (vector? v-vec)
      (-substitutions (coll->partition u-vec) v-vec smap)))

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


(deftype VecTerm [term min-length multiple-substitutions? variables variable-length?]
  IMinLength
  (-min-length [_vec-term]
    min-length)

  ISubstitutions
  (-substitutions [_vec-term u smap]
    (when (vector? u)
      (-substitutions term u smap)))

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
        multiple-substitutions? (-multiple-substitutions? t)
        variables (-variables t)
        variable-length? (-variable-length? t)]
    (VecTerm. t min-length multiple-substitutions? variables variable-length?)))


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


(deftype SeqTerm [term min-length multiple-substitutions? variables variable-length?]
  IMinLength
  (-min-length [_seq-term]
    min-length)

  ISubstitutions
  (-substitutions [_seq-term u smap]
    (when (seq? u)
      (-substitutions term u smap)))

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
        multiple-substitutions? (-multiple-substitutions? t)
        variables (-variables t)
        variable-length? (-variable-length? t)]
    (SeqTerm. t min-length multiple-substitutions? variables variable-length?)))


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


(defmethod parse-form* :term [[_ x]]
  (parse-form* x))


(defmethod parse-form* :lit [[_ x]]
  x)


(defmethod parse-form* :quo [[_ [_ x]]]
  x)


(defmethod parse-form* :var [[_ sym]]
  (Variable. sym))


(defmethod parse-form* :mem [[_ sym]]
  (Memo. sym))


(defmethod parse-form* :vec [[_ x]]
  (vec-term-no-check (parse-form* x)))


(defmethod parse-form* :seq [[_ x]]
  (seq-term-no-check (parse-form* x)))


(defmethod parse-form* :rep [[_ {init :init}]]
  (Rep. (parse-form* init) 0))


(defmethod parse-form* :repk [[_ {init :init k :k}]]
  (Rep. (parse-form* init) k))


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


(defmacro matcher
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
             (when (and ~@(map second *when-clauses))
               ~expr)))
          (seq (substitutions m# t#)))))))




#_
(let [m (matcher (let [(_ _ :as !binding-pairs) ...] . !body ...) 
          [!binding-pairs
           !body])]
  (m '(let [a 1 b 2])))




