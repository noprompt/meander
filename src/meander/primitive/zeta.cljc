(ns meander.primitive.zeta
  (:require
   [clojure.core :as clj]
   [meander.algorithms.zeta :as m.algorithms]
   [meander.logic.zeta :as m.logic]
   [meander.primitive.hash-map.zeta :as m.primitive.hash-map]
   [meander.primitive.hash-set.zeta :as m.primitive.hash-set]
   [meander.primitive.keyword.zeta :as m.primitive.keyword]
   [meander.primitive.sequence.zeta :as m.primitive.sequence]
   [meander.primitive.string.zeta :as m.primitive.string]
   [meander.primitive.symbol.zeta :as m.primitive.symbol]
   [meander.protocols.zeta :as m.protocols]
   [meander.state.zeta :as m.state]
   [clojure.set :as set])
  (:refer-clojure :exclude [apply
                            assoc
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
                            set
                            some
                            str
                            symbol
                            vec
                            vector
                            with-meta]))

;; Protocol Implementation
;; ---------------------------------------------------------------------

(defrecord Anything []
  m.protocols/IQuery
  (-query [this ilogic]
    ilogic)

  m.protocols/IYield
  (-yield [this ilogic]
    ;; FIXME: This should be infinite.
    (m.logic/each ilogic
      (fn [s]
        (m.logic/pass ilogic (m.state/set-random s))))))

(defrecord Nothing []
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/each ilogic
      (fn [s] (m.logic/fail ilogic s))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/each ilogic
      (fn [s] (m.logic/fail ilogic s)))))

(defrecord Is [x]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/each ilogic
      (fn [istate0]
        (clj/let [y (m.state/get-object istate0)]
          (if (= x y)
            (m.logic/pass ilogic istate0)
            (m.logic/fail ilogic istate0))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/each ilogic
      (fn [istate0]
        (m.logic/pass ilogic (m.state/set-object istate0 x))))))

(defrecord Not [a]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/comp ilogic
      (fn [s]
        (m.protocols/-query a (m.logic/pass ilogic s)))))

  m.protocols/IYield
  (-yield [this ilogic]
    (throw (ex-info "Not implemented" {}))))

(defrecord Some [a b]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/some (m.protocols/-query a ilogic)
                  (m.protocols/-query b ilogic)))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/some (m.protocols/-yield a ilogic)
                  (m.protocols/-yield b ilogic))))

(defrecord Pick [a b]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/pick (m.protocols/-query a ilogic)
                  (m.protocols/-query b ilogic)))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/pick (m.protocols/-yield a ilogic)
                  (m.protocols/-yield b ilogic))))

(defrecord Each [a b]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/each ilogic
      (fn [istate0]
        (clj/let [x (m.state/get-object istate0)]
          (m.logic/each (m.protocols/-query a (m.logic/pass ilogic istate0))
            (fn [istate1]
              (m.protocols/-query b (m.logic/pass ilogic (m.state/set-object istate1 x)))))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/some
     ;; If the result of yielding a successfully queries against b,
     ;; pass, fail otherwise.
     (m.logic/each (m.protocols/-yield a ilogic)
       (fn [s]
         (clj/let [ilogic-out (m.logic/pass ilogic s)]
           (m.logic/each (m.protocols/-query b (m.logic/pass ilogic s))
             (fn [_] ilogic-out)))))
     ;; If the result of yielding b successfully queries against a,
     ;; pass, fail otherwise.
     (m.logic/each (m.protocols/-yield b ilogic)
       (fn [s]
         (clj/let [ilogic-out (m.logic/pass ilogic s)]
           (m.logic/each (m.protocols/-query a (m.logic/pass ilogic s))
             (fn [_] ilogic-out))))))))

(defrecord LogicVariable [id]
  m.protocols/IQuery
  (-query [this ilogic]
    (clj/let [unbound (m.logic/unbound ilogic)]
      (m.logic/each ilogic
        (fn [s]
          (clj/let [x (m.state/get-object s)
                    y (m.state/get-variable s this unbound)]
            (if (identical? y unbound)
              (m.logic/pass ilogic (m.state/set-variable s this x))
              (if (= x y)
                (m.logic/pass ilogic s)
                (m.logic/fail ilogic s))))))))

  m.protocols/IYield
  (-yield [this m]
    (clj/let [unbound (m.logic/unbound m)]
      (m.logic/each m
        (fn [s]
          (clj/let [x (m.state/get-variable s this unbound)]
            (if (identical? x unbound)
              (m.logic/fail m s)
              (m.logic/pass m (m.state/set-object s x)))))))))

(defrecord Unbound []
  m.protocols/IQuery
  (-query [this ilogic]
    (clj/let [unbound (m.logic/unbound ilogic)]
      (m.logic/each ilogic
        (fn [istate0]
          (clj/let [x (m.state/get-object istate0)]
            (if (identical? x unbound)
              (m.logic/pass ilogic istate0)
              (m.logic/fail ilogic istate0)))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (clj/let [unbound (m.logic/unbound ilogic)]
      (m.logic/each ilogic
        (fn [istate0]
          (m.logic/pass ilogic (m.state/set-object istate0 unbound)))))))

(defrecord Variable [id qrule yrule]
  ;; Variable query rule is applied to [current-value incoming-value] and
  ;; expected to return new-value. If new-value is unbound we fail.
  m.protocols/IQuery
  (-query [this ilogic]
    (clj/let [unbound (m.logic/unbound ilogic)]
      (m.logic/each ilogic
        (fn [istate0]
          (clj/let [x (m.state/get-variable istate0 this unbound)
                    y (m.state/get-object istate0)]
            (m.logic/each (m.protocols/-redex qrule (m.logic/pass ilogic (m.state/set-object istate0 [x y])))
              (fn [istate1]
                (clj/let [z (m.state/get-object istate1)]
                  (if (identical? z unbound)
                    (m.logic/fail ilogic istate1)
                    (m.logic/pass ilogic (m.state/set-variable istate1 this z)))))))))))

  ;; Variable yield rule is applied to current value and expected to return
  ;; [new-value outgoing-value]. If new-value is unbound we fail.
  m.protocols/IYield
  (-yield [this ilogic]
    (clj/let [unbound (m.logic/unbound ilogic)]
      (m.logic/each ilogic
        (fn [istate0]
          (clj/let [x (m.state/get-variable istate0 this unbound)]
            (if (identical? x unbound)
              (m.logic/fail ilogic istate0)
              (m.logic/each (m.protocols/-redex yrule (m.logic/pass ilogic (m.state/set-object istate0 x)))
                (fn [istate1]
                  (clj/let [[y z] (m.state/get-object istate1)]
                    (if (identical? y unbound)
                      (m.logic/fail ilogic istate1)
                      (m.logic/pass ilogic (m.state/set-object (m.state/set-variable istate1 this z) y)))))))))))))

(defrecord Reference [id]
  m.protocols/IQuery
  (-query [this m]
    (m.logic/each m
      (fn [s]
        (if-some [p (m.state/get-reference s this nil)]
          (m.protocols/-query p (m.logic/pass m s))
          (m.logic/fail m s)))))

  m.protocols/IYield
  (-yield [this m]
    (m.logic/each m
      (fn [s]
        (if-some [p (m.state/get-reference s this nil)]
          (m.protocols/-yield p (m.logic/pass m s))
          (m.logic/fail m s))))))

(defrecord With [index a]
  m.protocols/IQuery
  (-query [this m]
    (m.logic/each m
      (fn [s1]
        (clj/let [s2 (reduce (fn [s2 [k v]]
                               (m.state/set-reference s2 k v))
                             s1
                             (.-index this))]
          (m.logic/each (m.protocols/-query (.-a this) (m.logic/pass m s2))
            (fn [s3]
              (m.logic/pass m (reduce
                               (fn [s4 k]
                                 (m.state/set-reference s4 k (m.state/get-reference s1 k nil)))
                               s3
                               (keys (.-index this))))))))))

  m.protocols/IYield
  (-yield [this m]
    (m.logic/each m
      (fn [s1]
        (clj/let [s2 (reduce (fn [s2 [k v]]
                               (m.state/set-reference s2 k v))
                             s1
                             (.-index this))]
          (m.logic/each (m.protocols/-yield (.-a this) (m.logic/pass m s2))
            (fn [s3]
              (m.logic/pass m (reduce
                               (fn [s4 k]
                                 (m.state/set-reference s4 k (m.state/get-reference s1 k nil)))
                               s3
                               (keys (.-index this)))))))))))

(defrecord Forget [a]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/each ilogic
      (fn [istate0]
        (m.logic/each (m.protocols/-query a (m.logic/pass ilogic istate0))
          (fn [istate1]
            (clj/let [x (m.state/get-object istate1)]
              (m.logic/pass ilogic (m.state/set-object istate0 x))))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/each ilogic
      (fn [istate0]
        (m.logic/each (m.protocols/-yield a (m.logic/pass ilogic istate0))
          (fn [istate1]
            (clj/let [x (m.state/get-object istate1)]
              (m.logic/pass ilogic (m.state/set-object istate0 x))))))))

  m.protocols/IRedex
  (-redex [this ilogic]
    (m.logic/each ilogic
      (fn [istate0]
        (m.logic/each (m.protocols/-redex a (m.logic/pass ilogic istate0))
          (fn [istate1]
            (clj/let [x (m.state/get-object istate1)]
              (m.logic/pass ilogic (m.state/set-object istate0 x)))))))))

(defrecord Project [y q a]
  ;; Yield y with non destructive affect on bindings, query the
  ;; yielded object with q with destructive affect on bindings, query
  ;; the original target object with a.
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/each ilogic
      (fn [istate0]
        (clj/let [x (m.state/get-object istate0)]
          (m.logic/each (m.protocols/-yield y (m.logic/pass ilogic istate0))
            (fn [istate1]
              (clj/let [y (m.state/get-object istate1)]
                (m.logic/each (m.protocols/-query q (m.logic/pass ilogic (m.state/set-object istate0 y)))
                  (fn [istate2]
                    (m.protocols/-query a (m.logic/pass ilogic (m.state/set-object istate2 x))))))))))))

  ;; Yield y with non destructive affect on bindings, query the
  ;; yielded object with q with destructive affect on bindings, yield
  ;; object with a.
  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/each ilogic
      (fn [istate0]
        (m.logic/each (m.protocols/-yield y (m.logic/pass ilogic istate0))
          (fn [istate1]
            (clj/let [y (m.state/get-object istate1)]
              (m.protocols/-yield a (m.protocols/-query q (m.logic/pass ilogic (m.state/set-object istate0 y)))))))))))

(defrecord Apply [yf yargs q]
  ;; Yield function and args non destructively, query return destructively.
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/each ilogic
      (fn [istate0]
        (clj/let [object0 (m.state/get-object istate0)
                  ilogic1 (m.logic/pass ilogic istate0)]
          (m.logic/each (m.protocols/-yield yf ilogic1)
            (fn [istate1]
              (clj/let [f (m.state/get-object istate1)]
                (if (ifn? f)
                  (m.logic/each (m.protocols/-yield yargs ilogic1)
                    (fn [istate2]
                      (clj/let [args (m.state/get-object istate2)]
                        (if (sequential? args)
                          (clj/let [x (clj/apply f object0 args)]
                            (m.protocols/-query q (m.logic/pass ilogic (m.state/set-object istate0 x))))
                          (m.logic/fail ilogic istate2)))))
                  (m.logic/fail ilogic istate0)))))))))

  ;; Yield function and args destructively, query return non destructively.
  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/each ilogic
      (fn [istate0]
        (m.logic/each (m.protocols/-yield yf (m.logic/pass ilogic istate0))
          (fn [istate1]
            (clj/let [f (m.state/get-object istate1)]
              (if (ifn? f)
                (m.logic/each (m.protocols/-yield yargs (m.logic/pass ilogic istate1))
                  (fn [istate2]
                    (clj/let [args (m.state/get-object istate2)]
                      (if (sequential? args)
                        (clj/let [x (clj/apply f args)
                                  ilogic2 (m.logic/pass ilogic (m.state/set-object istate2 x))]
                          (m.logic/each (m.protocols/-query q ilogic2)
                            (fn [_] ilogic2)))
                        (m.logic/fail ilogic istate2)))))
                (m.logic/fail ilogic istate0)))))))))

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

(defrecord RuleSystem [id rules]
  m.protocols/IQuery
  (-query [this m]
    (case (count (.-rules this))
      0
      (m.logic/each m (fn [s] (m.logic/fail m s)))

      1
      (m.protocols/-query (first (.-rules this)) m)

      ;; else
      (reduce m.logic/some
              (m.protocols/-query (first (.-rules this)) m)
              (map (fn [rule] (m.protocols/-query rule m))
                   (rest (.-rules this))))))

  m.protocols/IYield
  (-yield [this m]
    (case (count (.-rules this))
      0
      (m.logic/each m (fn [s] (m.logic/fail m s)))

      1
      (m.protocols/-query (first (.-rules this)) m)

      ;; else
      (reduce m.logic/some
              (m.protocols/-yield (first (.-rules this)) m)
              (map (fn [rule] (m.protocols/-yield rule m))
                   (rest (.-rules this))))))

  m.protocols/IRedex
  (-redex [this m]
    (case (count (.-rules this))
      0
      (m.logic/each m (fn [s] (m.logic/fail m s)))

      1
      (m.protocols/-redex (first (.-rules this)) m)

      ;; else
      (reduce m.logic/some
              (m.protocols/-redex (first (.-rules this)) m)
              (map (fn [rule] (m.protocols/-redex rule m))
                   (rest (.-rules this)))))))

(defrecord Again [id a])

(defrecord StringCast [a]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/each ilogic
      (fn [s]
        (clj/let [x (m.state/get-object s)]
          (if (string? x)
            (m.protocols/-query a ilogic)
            (m.logic/fail ilogic s))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/each ilogic
      (fn [s]
        (clj/let [x (m.state/get-object s)]
          (m.state/set-object s (clj/str x)))))))

(defrecord StringConcat [a b]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/each ilogic
      (fn [s]
        (clj/let [x (m.state/get-object s)]
          (if (string? x)
            (reduce m.logic/some
                    (m.logic/fail ilogic s)
                    (map (fn [[a-part b-part]]
                           (m.logic/each (m.protocols/-query a (m.logic/pass ilogic (m.state/set-object s a-part)))
                             (fn [s]
                               (m.protocols/-query b (m.logic/pass ilogic (m.state/set-object s b-part))))))
                         (m.algorithms/string-partitions x 2)))
            (m.logic/fail ilogic s))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/each (m.protocols/-yield a ilogic)
      (fn [s]
        (clj/let [a (m.state/get-object s)]
          (m.logic/each (m.protocols/-yield b (m.logic/pass ilogic s))
            (fn [s]
              (clj/let [b (m.state/get-object s)]
                (m.logic/pass ilogic (m.state/set-object s (clj/str a b)))))))))))

(defrecord SymbolUnqualified [name]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/each ilogic
      (fn [s]
        (clj/let [x (m.state/get-object s)]
          (if (symbol? x)
            (m.protocols/-query name (m.logic/pass ilogic (m.state/set-object s (clj/name x))))
            (m.logic/fail ilogic s))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/each (m.protocols/-yield name ilogic)
      (fn [s]
        (clj/let [x (m.state/get-object s)]
          (if (string? x)
            (m.logic/pass ilogic (m.state/set-object s (clj/symbol x)))
            (m.logic/fail ilogic s)))))))

(defrecord SymbolQualified [ns name]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/each ilogic
      (fn [s]
        (clj/let [x (m.state/get-object s)]
          (if (symbol? x)
            (clj/let [x-ns (namespace x)
                      x-name (clj/name x)]
              (m.logic/each (m.protocols/-query ns (m.logic/pass ilogic (m.state/set-object s x-ns)))
                (fn [s]
                  (m.protocols/-query name (m.logic/pass ilogic (m.state/set-object s x-name))))))
            (m.logic/fail ilogic s))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/each (m.protocols/-yield (.-ns this) ilogic)
      (fn [s1]
        (clj/let [x (m.state/get-object s1)]
          (if (string? x)
            (m.logic/each (m.protocols/-yield name ilogic)
              (fn [s2]
                (clj/let [y (m.state/get-object s2)]
                  (if (string? y)
                    (m.logic/pass ilogic (m.state/set-object s2 (clj/symbol x y)))
                    (m.logic/fail ilogic s2)))))
            (m.logic/fail ilogic s1)))))))

(defrecord KeywordUnqualified [name]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/each ilogic
      (fn [s]
        (clj/let [x (m.state/get-object s)]
          (if (keyword? x)
            (m.protocols/-query name (m.logic/pass ilogic (m.state/set-object s (clj/name x))))
            (m.logic/fail ilogic s))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/each (m.protocols/-yield name ilogic)
      (fn [s]
        (clj/let [x (m.state/get-object s)]
          (if (string? x)
            (m.logic/pass ilogic (m.state/set-object s (clj/keyword x)))
            (m.logic/fail ilogic s)))))))

(defrecord KeywordQualified [ns name]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/each ilogic
      (fn [s]
        (clj/let [x (m.state/get-object s)]
          (if (qualified-keyword? x)
            (clj/let [x-ns (namespace x)
                      x-name (clj/name x)]
              (m.logic/each (m.protocols/-query ns (m.logic/pass ilogic (m.state/set-object s x-ns)))
                (fn [s]
                  (m.protocols/-query name (m.logic/pass ilogic (m.state/set-object s x-name))))))
            (m.logic/fail ilogic s))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/each (m.protocols/-yield (.-ns this) ilogic)
      (fn [s1]
        (clj/let [x (m.state/get-object s1)]
          (if (string? x)
            (m.logic/each (m.protocols/-yield name ilogic)
              (fn [s2]
                (clj/let [y (m.state/get-object s2)]
                  (if (string? y)
                    (m.logic/pass ilogic (m.state/set-object s2 (clj/keyword x y)))
                    (m.logic/fail ilogic s2)))))
            (m.logic/fail ilogic s1)))))))

(defrecord SequenceEmpty []
  m.protocols/IQuery
  (-query [this m]
    (m.logic/each m
      (fn [s]
        (clj/let [x (m.state/get-object s)]
          (if (sequential? x)
            (m.logic/pass m s)
            (m.logic/fail m s))))))

  m.protocols/IYield
  (-yield [this m]
    (m.logic/each m
      (fn [s]
        (m.logic/some (m.logic/pass m (m.state/set-object s ()))
                      (m.logic/pass m (m.state/set-object s [])))))))

(defrecord SequenceCons [head tail]
  m.protocols/IQuery
  (-query [this m]
    (m.logic/each m
      (fn [s]
        (clj/let [x (m.state/get-object s)]
          (if (sequential? x)
            (if (clj/seq x)
              (clj/let [x-head (first x)
                        x-tail (rest x)]
                (m.logic/each (m.protocols/-query head (m.logic/pass m (m.state/set-object s x-head)))
                  (fn [s]
                    (m.protocols/-query tail (m.logic/pass m (m.state/set-object s x-tail))))))
              (m.logic/fail m s))
            (m.logic/fail m s))))))

  m.protocols/IYield
  (-yield [this m]
    (m.logic/each (m.protocols/-yield head m)
      (fn [s]
        (clj/let [x (m.state/get-object s)]
          (m.logic/each (m.protocols/-yield tail (m.logic/pass m s))
            (fn [s]
              (clj/let [y (m.state/get-object s)]
                (if (sequential? y)
                  (m.logic/pass m (m.state/set-object s (clj/cons x y)))
                  (m.logic/fail m s))))))))))

(defrecord SequenceConcat [a b]
  m.protocols/IQuery
  (-query [this m]
    (m.logic/each m
      (fn [s]
        (clj/let [x (m.state/get-object s)]
          (if (sequential? x)
            (reduce (fn [m [x-a x-b]]
                      (m.logic/some
                       m
                       (m.logic/each (m.protocols/-query a (m.logic/pass m (m.state/set-object s x-a)))
                         (fn [s]
                           (m.protocols/-query b (m.logic/pass m (m.state/set-object s x-b)))))))
                    (m.logic/fail m s)
                    (m.algorithms/partitions 2 x))
            (m.logic/fail m s))))))

  m.protocols/IYield
  (-yield [this m]
    (m.logic/each (m.protocols/-yield a m)
      (fn [s]
        (clj/let [x (m.state/get-object s)]
          (if (sequential? x)
            (m.logic/each (m.protocols/-yield b (m.logic/pass m s))
              (fn [s]
                (clj/let [y (m.state/get-object s)]
                  (if (sequential? y)
                    (m.logic/pass m (m.state/set-object s (clj/concat x y)))
                    (m.logic/fail m s)))))
            (m.logic/fail m s)))))))

(defrecord GreedyStar [a b]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/each ilogic
      (fn [istate0]
        (clj/let [x (m.state/get-object istate0)]
          (if (sequential? x)
            (if (clj/seq x)
              (clj/let [head (first x)
                        tail (rest x)]
                (m.logic/pick (m.protocols/-query this (m.logic/each (m.protocols/-query a (m.logic/pass ilogic (m.state/set-object istate0 head)))
                                                         (fn [istate1]
                                                           (m.logic/pass ilogic (m.state/set-object istate1 tail)))))
                              (m.protocols/-query b ilogic)))
              (m.protocols/-query b (m.logic/pass ilogic istate0)))
            (m.logic/fail ilogic istate0))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/pick
     (m.logic/each (m.protocols/-yield a ilogic)
       (fn [istate0]
         (clj/let [x (m.state/get-object istate0)]
           (m.logic/each (m.protocols/-yield this (m.logic/pass ilogic istate0))
             (fn [istate1]
               (clj/let [xs (m.state/get-object istate0)]
                 (m.logic/pass ilogic (m.state/set-object istate1 (clj/cons x xs)))))))))
     (m.logic/each (m.protocols/-yield a ilogic)
       (fn [istate0]
         (clj/let [x (m.state/get-object istate0)]
           (if (sequential? x)
             (m.logic/pass ilogic istate0)
             (m.logic/fail ilogic istate0))))))))

(defrecord FrugalStar [a b]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/each ilogic
      (fn [istate0]
        (clj/let [x (m.state/get-object istate0)]
          (if (sequential? x)
            (if (clj/seq x)
              (clj/let [head (first x)
                        tail (rest x)]
                (m.logic/some (m.protocols/-query b ilogic)
                              (m.logic/each (m.protocols/-query a (m.logic/pass ilogic (m.state/set-object istate0 head)))
                                (fn [istate1]
                                  (m.protocols/-query this (m.logic/pass ilogic (m.state/set-object istate1 tail)))))))
              (m.protocols/-query b (m.logic/pass ilogic istate0)))
            (m.logic/fail ilogic istate0))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/some
     (m.logic/each (m.protocols/-yield a ilogic)
       (fn [istate0]
         (clj/let [x (m.state/get-object istate0)]
           (m.logic/each (m.protocols/-yield this (m.logic/pass ilogic istate0))
             (fn [istate1]
               (clj/let [xs (m.state/get-object istate0)]
                 (m.logic/pass ilogic (m.state/set-object istate1 (clj/cons x xs)))))))))
     (m.logic/each (m.protocols/-yield a ilogic)
       (fn [istate0]
         (clj/let [x (m.state/get-object istate0)]
           (if (sequential? x)
             (m.logic/pass ilogic istate0)
             (m.logic/fail ilogic istate0))))))))

(defrecord SeqCast [x]
  m.protocols/IQuery
  (-query [this m]
    (m.logic/each m
      (fn [s]
        (clj/let [y (m.state/get-object s)]
          (if (or (seq? y) (nil? y))
            (m.protocols/-query x (m.logic/pass m s))
            (m.logic/fail m s))))))

  m.protocols/IYield
  (-yield [this m]
    (m.logic/each (m.protocols/-yield x m)
      (fn [s]
        (clj/let [y (m.state/get-object s)]
          (if (seqable? y)
            (m.logic/pass m (m.state/set-object s (clj/seq y)))
            (m.logic/fail m s)))))))

(defrecord VectorCast [x]
  m.protocols/IQuery
  (-query [this m]
    (m.logic/each m
      (fn [s]
        (clj/let [y (m.state/get-object s)]
          (if (vector? y)
            (m.protocols/-query x (m.logic/pass m s))
            (m.logic/fail m s))))))

  m.protocols/IYield
  (-yield [this m]
    (m.logic/each (m.protocols/-yield x m)
      (fn [s]
        (clj/let [y (m.state/get-object s)]
          (if (seqable? y)
            (m.logic/pass m (m.state/set-object s (clj/vec y)))
            (m.logic/fail m s)))))))

(defrecord HashMapEmpty []
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/each ilogic
      (fn [s]
        (clj/let [x (m.state/get-object s)]
          (if (= x {})
            (m.logic/pass ilogic s)
            (m.logic/fail ilogic s))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/each ilogic
      (fn [s]
        (m.logic/pass ilogic (m.state/set-object s {}))))))

(defrecord HashMapEntry [k v]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/each ilogic
      (fn [s]
        (clj/let [x (m.state/get-object s)]
          (if (map-entry? x)
            (m.logic/each (m.protocols/-query k (m.logic/pass ilogic (m.state/set-object s (key x))))
              (fn [s]
                (m.protocols/-query v (m.logic/pass ilogic (m.state/set-object s (val x))))))
            (m.logic/fail ilogic s))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/each (m.protocols/-yield (.-k this) ilogic)
      (fn [s]
        (clj/let [k (m.state/get-object s)]
          (m.logic/each (m.protocols/-yield (.-v this) (m.logic/pass ilogic s))
            (fn [s]
              (clj/let [v (m.state/get-object s)]
                (m.logic/pass ilogic (m.state/set-object s (clojure.lang.MapEntry. k v)))))))))))

(defrecord HashMapAssoc [m k v]
  m.protocols/IQuery
  (-query [this ilogic]
    (clj/let [entry (->HashMapEntry k v)]
      (m.logic/each ilogic
        (fn [s]
          (clj/let [x (m.state/get-object s)]
            (if (map? x)
              (case (count x)
                0 (m.logic/fail ilogic s)

                1 (clj/let [e (first x)
                            x-e (dissoc x (key e))]
                    (m.logic/each (m.protocols/-query entry (m.logic/pass ilogic (m.state/set-object s e)))
                      (fn [s]
                        (m.protocols/-query (.-m this) (m.logic/pass ilogic (m.state/set-object s x-e))))))

                ;; else
                (reduce m.logic/some
                        (map
                         (fn [e]
                           (clj/let [x-e (dissoc x (key e))]
                             (m.logic/each (m.protocols/-query entry (m.logic/pass ilogic (m.state/set-object s e)))
                               (fn [s]
                                 (m.protocols/-query (.-m this) (m.logic/pass ilogic (m.state/set-object s x-e)))))))
                         x)))
              (m.logic/fail ilogic s)))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/each (m.protocols/-yield (HashMapEntry. (.-k this) (.-v this)) ilogic)
      (fn [s]
        (clj/let [e (m.state/get-object s)]
          (m.logic/each (m.protocols/-yield (.-m this) (m.logic/pass ilogic s))
            (fn [s]
              (clj/let [x (m.state/get-object s)]
                (if (map? x)
                  (m.logic/pass ilogic (m.state/set-object s (conj x e)))
                  (m.logic/fail ilogic s))))))))))

(defrecord HashMapMerge [m1 m2]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/each ilogic
      (fn [s]
        (clj/let [x (m.state/get-object s)]
          (if (map? x)
            (if (zero? (count x))
              (m.logic/each (m.protocols/-query (.-m1 this) (m.logic/pass ilogic (m.state/set-object s {})))
                (fn [s]
                  (m.protocols/-query (.-m2 this) (m.logic/pass ilogic (m.state/set-object s {})))))
              ;; Not supplying val here is safe because
              ;; (map-partitions m 2) will give us a sequence of at
              ;; least 2 when the key count of m is greater than 0.
              (reduce m.logic/some
                      (map (fn [[a b]]
                             (m.logic/each (m.protocols/-query (.-m1 this) (m.logic/pass ilogic (m.state/set-object s a)))
                               (fn [s]
                                 (m.protocols/-query (.-m2 this) (m.logic/pass ilogic (m.state/set-object s b))))))
                           (m.algorithms/map-partitions x 2))))
            (m.logic/fail ilogic s))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/each (m.protocols/-yield (.-m1 this) ilogic)
      (fn [s]
        (clj/let [x (m.state/get-object s)]
          (if (map? x)
            (m.logic/each (m.protocols/-yield (.-m2 this) (m.logic/pass ilogic s))
              (fn [s]
                (clj/let [y (m.state/get-object s)]
                  (if (map? y)
                    (m.logic/pass ilogic (m.state/set-object s (clj/merge x y)))
                    (m.logic/fail ilogic s)))))
            (m.logic/fail ilogic s)))))))

(defrecord WithMeta [a b]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/each ilogic
      (fn [istate0]
        (clj/let [x (m.state/get-object istate0)]
          (if (instance? clojure.lang.IObj x)
            (clj/let [m (clj/meta x)]
              (m.logic/each (m.protocols/-query a (m.logic/pass ilogic istate0))
                (fn [istate1]
                  (m.protocols/-query b (m.logic/pass ilogic (m.state/set-object istate1 m))))))
            (m.logic/fail ilogic istate0))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/each (m.protocols/-yield a ilogic)
      (fn [istate1]
        (clj/let [x (m.state/get-object istate1)]
          (if (instance? clojure.lang.IObj x)
            (m.logic/each (m.protocols/-yield b (m.logic/pass ilogic istate1))
              (fn [istate2]
                (clj/let [m (m.state/get-object istate2)]
                  (if (map? m)
                    (m.logic/pass ilogic (m.state/set-object istate2 (clj/with-meta x m)))
                    (m.logic/fail ilogic istate2)))))
            (m.logic/fail ilogic istate1)))))))
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

(def
  ^{:arglists '([yf yargs yret])}
  apply #'->Apply)

(defn some
  "Constructor for the pattern which represents an element of the
  union of the sets described by patterns provided."
  ([] (nothing))
  ([a] a)
  ([a b] (->Some a b))
  ([a b & more]
   (clj/apply some (->Some a b) more)))

(defn pick
  "Constructor for the pattern which represents an element of the
  of one of the sets described by patterns provided."
  ([] (nothing))
  ([a] a)
  ([a b] (->Pick a b))
  ([a b & more]
   (clj/apply pick (->Pick a b) more)))

(defn each
  "Constructor for the pattern which represents an element of the
  of the intersection of sets described by patterns provided."
  ([] (anything))
  ([a] a)
  ([a b] (->Each a b))
  ([a b & more]
   (clj/apply each (->Each a b) more)))

(def
  ^{:arglists '([id])}
  reference #'->Reference)

(defn with
  [index a]
  (assert (and (map? index)
               (every? (fn [x] (instance? Reference x)) 
                       (keys index))))
  (->With index a))

(def
  ^{:arglists '([id])}
  logic-variable #'->LogicVariable)

(defn fresh* [f]
  (f (map logic-variable (repeatedly gensym))))

(defmacro fresh
  {:style/indent 1}
  [bindings & body]
  `(fresh* (fn [~(clj/vec bindings)] ~@body)))

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
  ([a b & more] (clj/apply str (str a b) more)))

(defn keyword
  "Constructor for the pattern which represents an element of the
  of set of keywords described by patterns provided."
  ([name] (->KeywordUnqualified name))
  ([ns name] (->KeywordQualified ns name)))

(defn symbol
  "Constructor for the pattern which represents an element of the
  of set of symbols described by patterns provided."
  ([name] (->SymbolUnqualified name))
  ([ns name] (->SymbolQualified ns name)))

(defn cons
  ([a b] (->SequenceCons a b)))

(defn concat
  ([] (is ()))
  ([a] (concat (concat) a))
  ([a b] (->SequenceConcat a b))
  ([a b & more] (clj/apply concat (concat a b) more)))

;; NOTE: Temporary implementation
(defn list
  ([] (is ()))
  ([a] (cons a (list)))
  ([a b] (cons a (list b)))
  ([a b & more] (cons a (cons b (clj/apply list more)))))

(def ^{:arglists '([a])}
  seq #'->SeqCast)

(def^{:arglists '([a])}
  vec #'->VectorCast)

;; NOTE: Temporary implementation
(def
  ^{:arglists '([& xs])}
  vector
  (comp vec list))

(defn assoc
  [m k v & kvs]
  (assert (even? (count kvs)) "assoc expects an even number of arguments")
  (reduce (fn [m [k v]] (->HashMapAssoc m k v))
          (->HashMapAssoc m k v)
          (partition 2 kvs)))

(defn hash-map
  [& kvs]
  (assert (even? (count kvs)) "hash-map expects an even number of arguments")
  (reduce (fn [m [k v]] (assoc m k v))
          (->HashMapEmpty)
          (partition 2 kvs)))

(defn merge
  ([] (hash-map))
  ([m1] (->HashMapMerge (hash-map) m1))
  ([m1 m2 & ms] (reduce ->HashMapMerge (->HashMapMerge m1 m2) ms)))

(defn hash-set
  [& keys]
  (reduce m.primitive.hash-set/conj (m.primitive.hash-set/empty) keys))

(defn set
  [x]
  (m.primitive.hash-set/->HashSetCast x))

(def ^{:arglists '([a rest])}
  greedy-star #'->GreedyStar)

(def ^{:arglists '([a rest])}
  frugal-star #'->FrugalStar)

(def ^{:arglists '([a b])}
  with-meta #'->WithMeta)

(def ^{:arglists '([a])}
  forget
  #'->Forget)

(def ^{:arglists '([id qrule yrule])}
  variable
  #'->Variable)

(def ^{:arglists '([])}
  unbound
  #'->Unbound)
