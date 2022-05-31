(ns meander.primitive.zeta
  (:require
   [meander.primitive.sequence.zeta :as m.primitive.sequence]
   [meander.primitive.string.zeta :as m.primitive.string]
   [meander.primitive.hash-map.zeta :as m.primitive.hash-map]
   [meander.primitive.hash-set.zeta :as m.primitive.hash-set])
  (:refer-clojure :exclude [assoc
                            concat
                            cons
                            hash-map
                            hash-set
                            let
                            list
                            not
                            seq
                            some
                            str
                            vec]))

(defrecord Anything [])
(defrecord Is [x])
(defrecord Each [a b])
(defrecord Not [a])
(defrecord Pick [a b])
(defrecord Some [a b])
(defrecord Reference [id])
(defrecord With [index a])
(defrecord Predicate [p])
(defrecord LogicVariable [id])
(defrecord Project [y q a])
(defrecord Rule [q y])
(defrecord RuleSystem [id rules])

(def ^{:arglists '([])
       :doc "Constructor for the pattern which represents an element
  of set of all objects."}
  anything #'->Anything)

(def ^{:arglists '([x])
       :doc "Constructor for the pattern which represents an element
  of the set containing only x."}
  is #'->Is)

(def ^{:arglists '([p])
       :doc "Constructor for the pattern which represents an element
  of the complement of the set described by the pattern p."}
  not #'->Not)

(def ^{:arglists '([])
       :doc "Constructor for the pattern which represents an element
  of the empty set e.g. nothing."}
  nothing (comp not anything))

;; Note: Not ready for documentation.
(def predicate
  #'->Predicate)

(defn some
  "Constructor for the pattern which represents an element of the
  union of the sets described by patterns provided."
  ([] (nothing))
  ([a] a)
  ([a b] (->Some a b))
  ([a b & more]
   (apply some (->Some a b) more)))

(defn pick
  "Constructor for the pattern which represents an element of the
  of one of the sets described by patterns provided."
  ([] (nothing))
  ([a] a)
  ([a b] (->Pick a b))
  ([a b & more]
   (apply pick (->Pick a b) more)))

(defn each
  "Constructor for the pattern which represents an element of the
  of the intersection of sets described by patterns provided."
  ([] (anything))
  ([a] a)
  ([a b] (->Each a b))
  ([a b & more]
   (apply each (->Each a b) more)))

(def
  ^{:arglists '([id])}
  % #'->Reference)

(defn with
  [index a]
  (assert (and (map? index)
               (every? (fn [x] (instance? Reference x)) 
                       (keys index))))
  (->With index a))

(def
  ^{:arglists '([id])}
  ? #'->LogicVariable)

(def
  ^{:arglists '([y q a])}
  project #'->Project)

(defmacro let
  {:style/indent 1}
  [patterns a]
  {:pre [(and (vector? patterns) (even? (count patterns)))]}
  (reduce (fn [a [q y]] `(project ~y ~q ~a))
          a
          (partition 2 patterns)))

(def
  ^{:arglists '([q y])}
  rule #'->Rule)

(defn system
  {:style/indent 1}
  ([rules]
   {:pre [(sequential? rules)]}
   (->RuleSystem (gensym) rules))
  ([id rules]
   {:pre [(sequential? rules)]}
   (->RuleSystem id rules)))

(defn str
  "Constructor for the pattern which represents an element of the
  of set of strings described by patterns provided."
  ([] (is ""))
  ([a] (m.primitive.string/concat (is "") a))
  ([a b] (m.primitive.string/concat a b))
  ([a b & more] (apply str (str a b) more)))

(defn cons
  ([a b] (m.primitive.sequence/cons a b)))

(defn concat
  ([] (is ()))
  ([a] (concat (concat) a))
  ([a b] (m.primitive.sequence/concat a b))
  ([a b & more] (apply concat (concat a b) more)))

;; NOTE: Temporary implementation
(defn list
  ([] (is ()))
  ([a] (cons a (list)))
  ([a b] (cons a (list b)))
  ([a b & more] (cons a (cons b (apply list more)))))

(defn seq
  [a]
  (m.primitive.sequence/seq-cast a))

(defn vec
  [a]
  (m.primitive.sequence/vector-cast a))

(defn hash-map
  [& kvs]
  (assert (even? (count kvs)) "hash-map expects an even number of arguments")
  (reduce (fn [m [k v]] (m.primitive.hash-map/assoc m k v))
          (m.primitive.hash-map/empty)
          (partition 2 kvs)))

(defn assoc
  [m k v & kvs]
  (assert (even? (count kvs)) "assoc expects an even number of arguments")
  (reduce (fn [m [k v]] (m.primitive.hash-map/assoc m k v))
          (m.primitive.hash-map/assoc m k v)
          (partition 2 kvs)))

(defn hash-set
  [& keys]
  (reduce m.primitive.hash-set/conj (m.primitive.hash-set/empty) keys))
