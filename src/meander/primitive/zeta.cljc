(ns meander.primitive.zeta
  (:require
   [clojure.core :as clj]
   [meander.algorithms.zeta :as m.algorithms]
   [meander.primitive.hash-map.zeta :as m.primitive.hash-map]
   [meander.primitive.hash-set.zeta :as m.primitive.hash-set]
   [meander.primitive.keyword.zeta :as m.primitive.keyword]
   [meander.primitive.sequence.zeta :as m.primitive.sequence]
   [meander.primitive.string.zeta :as m.primitive.string]
   [meander.primitive.symbol.zeta :as m.primitive.symbol]
   [meander.protocols.zeta :as m.protocols])
  (:refer-clojure :exclude [assoc
                            concat
                            cons
                            hash-map
                            hash-set
                            keyword
                            let
                            list
                            merge
                            not
                            seq
                            some
                            str
                            symbol
                            vec]))

;; Protocol Implementation
;; ---------------------------------------------------------------------

(defrecord Anything []
  m.protocols/IQuery
  (-query [this ilogic]
    ilogic)

  m.protocols/IYield
  (-yield [this ilogic]
    ;; FIXME: This should be infinite.
    (m.protocols/-each ilogic
      (fn [s]
        (m.protocols/-pass ilogic (m.protocols/-set-random s))))))

(defrecord Nothing []
  m.protocols/IQuery
  (-query [this ilogic]
    (m.protocols/-each ilogic
      (fn [s] (m.protocols/-fail ilogic s))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.protocols/-each ilogic
      (fn [s] (m.protocols/-fail ilogic s)))))

(defrecord Is [x]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.protocols/-each ilogic
      (fn [s]
        (clj/let [y (m.protocols/-get-object s)]
          (if (= x y)
            (m.protocols/-pass ilogic s)
            (m.protocols/-fail ilogic s))))))

  m.protocols/IYield
  (m.protocols/-yield [this ilogic]
    (m.protocols/-each ilogic
      (fn [s]
        (m.protocols/-pass ilogic (m.protocols/-set-object s x))))))

(defrecord Not [a]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.protocols/-comp ilogic
      (fn [s]
        (m.protocols/-query a (m.protocols/-pass ilogic s)))))

  m.protocols/IYield
  (-yield [this ilogic]
    (throw (ex-info "Not implemented" {}))))

(defrecord Some [a b]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.protocols/-some (m.protocols/-query a ilogic)
                       (m.protocols/-query b ilogic)))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.protocols/-some (m.protocols/-yield a ilogic)
                       (m.protocols/-yield b ilogic))))

(defrecord Pick [a b]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.protocols/-pick (m.protocols/-query a ilogic)
                       (m.protocols/-query b ilogic)))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.protocols/-pick (m.protocols/-yield a ilogic)
                       (m.protocols/-yield b ilogic))))

(defrecord Each [a b]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.protocols/-each (m.protocols/-query a ilogic)
      (fn [s]
        (m.protocols/-query b (m.protocols/-pass ilogic s)))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.protocols/-some
     ;; If the result of yielding a successfully queries against b,
     ;; pass, fail otherwise.
     (m.protocols/-each (m.protocols/-yield a ilogic)
       (fn [s]
         (clj/let [ilogic-out (m.protocols/-pass ilogic s)]
           (m.protocols/-each (m.protocols/-query b (m.protocols/-pass ilogic s))
             (fn [_] ilogic-out)))))
     ;; If the result of yielding b successfully queries against a,
     ;; pass, fail otherwise.
     (m.protocols/-each (m.protocols/-yield b ilogic)
       (fn [s]
         (clj/let [ilogic-out (m.protocols/-pass ilogic s)]
           (m.protocols/-each (m.protocols/-query a (m.protocols/-pass ilogic s))
             (fn [_] ilogic-out))))))))

(defrecord LogicVariable [id]
  m.protocols/IQuery
  (-query [this ilogic]
    (clj/let [unbound (m.protocols/-unbound ilogic)]
      (m.protocols/-each ilogic
        (fn [s]
          (clj/let [x (m.protocols/-get-object s)
                    y (m.protocols/-get-variable s this unbound)]
            (if (identical? y unbound)
              (do
                (print "Unbound")
                (m.protocols/-pass ilogic (m.protocols/-set-variable s this x)))
              (do (print (= x y))
                  (if (= x y)
                    (m.protocols/-pass ilogic s)
                    (m.protocols/-fail ilogic s)))))))))

  m.protocols/IYield
  (-yield [this m]
    (clj/let [unbound (m.protocols/-unbound m)]
      (m.protocols/-each m
        (fn [s]
          (clj/let [x (m.protocols/-get-variable s this unbound)]
            (if (identical? x unbound)
              (m.protocols/-fail m s)
              (m.protocols/-pass m (m.protocols/-set-object s x)))))))))

(defrecord Reference [id])
(defrecord With [index a])
(defrecord Predicate [p])
(defrecord Project [y q a])

(defrecord Rule [q y]
  m.protocols/IQuery
  (-query [this m]
    (m.protocols/-query q m))

  m.protocols/IYield
  (-yield [this m]
    (m.protocols/-yield y m))

  m.protocols/IRedex
  (-redex [this m]
    (m.protocols/-yield this (m.protocols/-query this m))))

(defrecord RuleSystem [id rules])
(defrecord Again [id a])

(defrecord StringCast [a]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.protocols/-each ilogic
      (fn [s]
        (clj/let [x (m.protocols/-get-object s)]
          (if (string? x)
            (m.protocols/-query a ilogic)
            (m.protocols/-fail ilogic s))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.protocols/-each ilogic
      (fn [s]
        (clj/let [x (m.protocols/-get-object s)]
          (m.protocols/-set-object s (clj/str x)))))))

(defrecord StringConcat [a b]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.protocols/-each ilogic
      (fn [s]
        (clj/let [x (m.protocols/-get-object s)]
          (if (string? x)
            (reduce m.protocols/-some
                    (m.protocols/-fail ilogic s)
                    (map (fn [[a-part b-part]]
                           (m.protocols/-each (m.protocols/-query a (m.protocols/-pass ilogic (m.protocols/-set-object s a-part)))
                             (fn [s]
                               (m.protocols/-query b (m.protocols/-pass ilogic (m.protocols/-set-object s b-part))))))
                         (m.algorithms/string-partitions x 2)))
            (m.protocols/-fail ilogic s))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.protocols/-each (m.protocols/-yield a ilogic)
      (fn [s]
        (clj/let [a (m.protocols/-get-object s)]
          (m.protocols/-each (m.protocols/-yield b (m.protocols/-pass ilogic s))
            (fn [s]
              (clj/let [b (m.protocols/-get-object s)]
                (m.protocols/-pass ilogic (m.protocols/-set-object s (clj/str a b)))))))))))

(defrecord SymbolUnqualified [name]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.protocols/-each ilogic
      (fn [s]
        (clj/let [x (m.protocols/-get-object s)]
          (if (symbol? x)
            (m.protocols/-query name (m.protocols/-pass ilogic (m.protocols/-set-object s (clj/name x))))
            (m.protocols/-fail ilogic s))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.protocols/-each (m.protocols/-yield name ilogic)
      (fn [s]
        (clj/let [x (m.protocols/-get-object s)]
          (if (string? x)
            (m.protocols/-pass ilogic (m.protocols/-set-object s (clj/symbol x)))
            (m.protocols/-fail ilogic s)))))))


(defrecord SymbolQualified [ns name]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.protocols/-each ilogic
      (fn [s]
        (clj/let [x (m.protocols/-get-object s)]
          (if (qualified-symbol? x)
            (clj/let [x-ns (namespace x)
                      x-name (clj/name x)]
              (m.protocols/-each (m.protocols/-query ns (m.protocols/-pass ilogic (m.protocols/-set-object s x-ns)))
                (fn [s]
                  (m.protocols/-query name (m.protocols/-pass ilogic (m.protocols/-set-object s x-name))))))
            (m.protocols/-fail ilogic s))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.protocols/-each (m.protocols/-yield (.-ns this) ilogic)
      (fn [s1]
        (clj/let [x (m.protocols/-get-object s1)]
          (if (string? x)
            (m.protocols/-each (m.protocols/-yield name ilogic)
              (fn [s2]
                (clj/let [y (m.protocols/-get-object s2)]
                  (if (string? y)
                    (m.protocols/-pass ilogic (m.protocols/-set-object s2 (clj/symbol x y)))
                    (m.protocols/-fail ilogic s2)))))
            (m.protocols/-fail ilogic s1)))))))

;; API
;; ---------------------------------------------------------------------

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
  nothing #'->Nothing)

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
  ([a] (->StringCast a))
  ([a b] (->StringConcat a b))
  ([a b & more] (apply str (str a b) more)))

(defn keyword
  ([name] (m.primitive.keyword/unqualified name))
  ([ns name] (m.primitive.keyword/qualified ns name)))

(defn symbol
  ([name] (->SymbolUnqualified name))
  ([ns name] (->SymbolQualified ns name)))

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

(defn merge
  ([]
   (hash-map))
  ([m1]
   (m.primitive.hash-map/merge (hash-map) m1))
  ([m1 m2 & ms]
   (reduce m.primitive.hash-map/merge (m.primitive.hash-map/merge m1 m2) ms)))

(defn hash-set
  [& keys]
  (reduce m.primitive.hash-set/conj (m.primitive.hash-set/empty) keys))
