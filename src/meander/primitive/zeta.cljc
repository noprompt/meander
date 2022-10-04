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
        (m.logic/pass ilogic (m.state/set-object istate0 x)))))

  m.protocols/IGroundValues
  (-ground-values [this ilogic]
    (m.protocols/-yield this ilogic)))

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
                  (m.protocols/-yield b ilogic)))

  m.protocols/IGroundValues
  (-ground-values [this ilogic]
    (m.logic/some (m.logic/ground-values ilogic a)
                  (m.logic/ground-values ilogic b))))

(defrecord Pick [a b]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/pick (m.protocols/-query a ilogic)
                  (m.protocols/-query b ilogic)))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/pick (m.protocols/-yield a ilogic)
                  (m.protocols/-yield b ilogic)))

  m.protocols/IGroundValues
  (-ground-values [this ilogic]
    (m.logic/pick (m.logic/ground-values ilogic a)
                  (m.logic/ground-values ilogic b))))

(defrecord Each [a b]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/foreach [istate0 ilogic
                      :let [x (m.state/get-object istate0)]
                      istate1 (m.logic/query (m.logic/pass ilogic istate0) a)]
      (-> (m.logic/pass ilogic (m.state/set-object istate1 x))
          (m.logic/query b ))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/some
     ;; If the result of yielding a successfully queries against b,
     ;; pass, fail otherwise.
     (m.logic/foreach [s (m.logic/yield ilogic a)
                       :let [ilogic-out (m.logic/pass ilogic s)]
                       _ (m.logic/query ilogic-out b)]
       ilogic-out)
     ;; If the result of yielding b successfully queries against a,
     ;; pass, fail otherwise.
     (m.logic/foreach [s (m.logic/yield ilogic b)
                       :let [ilogic-out (m.logic/pass ilogic s)]
                       _ (m.logic/query ilogic-out a)]
       ilogic-out))))


(defrecord LogicVariable [id]
  m.protocols/IQuery
  (-query [this ilogic]
    (clj/let [unbound (m.logic/unbound ilogic)]
      (m.logic/foreach [s ilogic
                        :let [x (m.state/get-object s)
                              y (m.state/get-variable s this unbound)]]
        (if (identical? y unbound)
          (m.logic/pass ilogic (m.state/set-variable s this x))
          (if (= x y)
            (m.logic/pass ilogic s)
            (m.logic/fail ilogic s))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (clj/let [unbound (m.logic/unbound ilogic)]
      (m.logic/foreach [s ilogic
                        :let [x (m.state/get-variable s this unbound)]]
        (if (identical? x unbound)
          (m.logic/fail ilogic s)
          (m.logic/pass ilogic (m.state/set-object s x))))))

  m.protocols/IGroundValues
  (-ground-values [this ilogic]
    (m.protocols/-yield this ilogic)))


(defrecord Unbound []
  m.protocols/IQuery
  (-query [this ilogic]
    (clj/let [unbound (m.logic/unbound ilogic)]
      (m.logic/foreach [istate0 ilogic
                        :let [x (m.state/get-object istate0)]]
        (if (identical? x unbound)
          (m.logic/pass ilogic istate0)
          (m.logic/fail ilogic istate0)))))

  m.protocols/IYield
  (-yield [this ilogic]
    (clj/let [unbound (m.logic/unbound ilogic)]
      (m.logic/foreach [istate0 ilogic]
        (m.logic/pass ilogic (m.state/set-object istate0 unbound))))))


(defrecord Variable [id qrule yrule]
  ;; Variable query rule is applied to [current-value incoming-value] and
  ;; expected to return new-value. If new-value is unbound we fail.
  m.protocols/IQuery
  (-query [this ilogic]
    (clj/let [unbound (m.logic/unbound ilogic)]
      (m.logic/foreach [istate0 ilogic
                        :let [x (m.state/get-variable istate0 this unbound)
                              y (m.state/get-object istate0)]
                        istate1 (m.logic/redex (m.logic/pass ilogic (m.state/set-object istate0 [x y])) qrule)
                        :let [z (m.state/get-object istate1)]]
        (if (identical? z unbound)
          (m.logic/fail ilogic istate1)
          (m.logic/pass ilogic (m.state/set-variable istate0 this z))))))

  ;; Variable yield rule is applied to current value and expected to return
  ;; [new-value outgoing-value]. If outgoing-value is unbound we fail.
  m.protocols/IYield
  (-yield [this ilogic]
    (clj/let [unbound (m.logic/unbound ilogic)]
      (m.logic/foreach [istate0 ilogic
                        :let [x (m.state/get-variable istate0 this unbound)]]
        (if (identical? x unbound)
          (m.logic/fail ilogic istate0)
          (m.logic/foreach [istate1 (m.logic/redex (m.logic/pass ilogic (m.state/set-object istate0 x)) yrule)
                            :let [[y z] (m.state/get-object istate1)]]
            (if (identical? z unbound)
              (m.logic/fail ilogic istate1)
              (m.logic/pass ilogic (m.state/set-object (m.state/set-variable istate0 this y) z)))))))))


(defrecord Reference [id]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/foreach [istate ilogic]
      (if-some [p (m.state/get-reference istate this nil)]
        (m.logic/query (m.logic/pass ilogic istate) p)
        (m.logic/fail ilogic istate))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/foreach [istate ilogic]
      (if-some [p (m.state/get-reference istate this nil)]
        (m.logic/yield (m.logic/pass ilogic istate) p)
        (m.logic/fail ilogic istate)))))


;; With
;; ----

(defn set-references
  {:private true}
  [istate index]
  (reduce-kv (fn [istate-out k v]
               (m.state/set-reference istate-out k v))
             istate
             index))

(defn replace-references
  {:private true}
  [istate-old istate-new index]
  (reduce (fn [istate-out k]
            (m.state/set-reference istate-out k (m.state/get-reference istate-new k nil)))
          istate-old
          (keys index)))

(defrecord With [index a]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/foreach [istate1 ilogic
                      :let [istate2 (set-references istate1 index)]
                      istate3 (m.logic/query (m.logic/pass ilogic istate2) a)]
      (m.logic/pass ilogic (replace-references istate3 istate1 index))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/foreach [istate1 ilogic
                      :let [istate2 (set-references istate1 index)]
                      istate3 (m.logic/yield (m.logic/pass ilogic istate2) a)]
      (m.logic/pass ilogic (replace-references istate3 istate1 index))))

  m.protocols/IRedex
  (-redex [this ilogic]
    (m.logic/yield (m.logic/query ilogic this) this)))


(defrecord Forget [a]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/forget ilogic
      (fn [istate0]
        (m.logic/query (m.logic/pass ilogic istate0) a))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/forget ilogic
      (fn [istate0]
        (m.logic/yield (m.logic/pass ilogic istate0) a))))

  m.protocols/IRedex
  (-redex [this ilogic]
    (m.logic/forget ilogic
      (fn [istate0]
        (m.logic/redex (m.logic/pass ilogic istate0) a)))))


(defrecord Project [y q a]
  ;; Yield y with non destructive affect on bindings, query the
  ;; yielded object with q with destructive affect on bindings, query
  ;; the original target object with a.
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/foreach [istate0 ilogic
                      :let [x (m.state/get-object istate0)]
                      istate1 (m.logic/yield (m.logic/pass ilogic istate0) y)
                      :let [y (m.state/get-object istate1)]
                      istate2 (m.logic/query (m.logic/pass ilogic (m.state/set-object istate0 y)) q)]
      (m.logic/query (m.logic/pass ilogic (m.state/set-object istate2 x)) a)))

  ;; Yield y with non destructive affect on bindings, query the
  ;; yielded object with q with destructive affect on bindings, yield
  ;; object with a.
  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/foreach [istate0 ilogic
                      istate1 (m.logic/yield (m.logic/pass ilogic istate0) y)
                      :let [y (m.state/get-object istate1)]]
      (-> (m.logic/pass ilogic (m.state/set-object istate0 y))
          (m.logic/query q)
          (m.logic/yield a)))))

(defrecord Apply [yf yargs q]
  ;; Yield function and args non destructively, query return destructively.
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/foreach [istate0 ilogic
                      :let [object0 (m.state/get-object istate0)
                            ilogic1 (m.logic/pass ilogic istate0)]
                      istate1 (m.logic/yield ilogic1 yf)
                      :let [f (m.state/get-object istate1)]]
      (if (ifn? f)
        (m.logic/foreach [istate2 (m.logic/yield ilogic1 yargs)
                          :let [args (m.state/get-object istate2)]]
          (if (sequential? args)
            (clj/let [x (clj/apply f object0 args)]
              (m.logic/query (m.logic/pass ilogic (m.state/set-object istate0 x)) q))
            (m.logic/fail ilogic istate2)))
        (m.logic/fail ilogic istate0))))

  ;; Yield function and args destructively, query return non destructively.
  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/foreach [istate0 ilogic
                      istate1 (m.logic/yield (m.logic/pass ilogic istate0) yf)
                      :let [f (m.state/get-object istate1)]]
      (if (ifn? f)
        (m.logic/foreach [istate2 (m.logic/yield (m.logic/pass ilogic istate1) yargs)
                          :let [args (m.state/get-object istate2)]]
          (if (sequential? args)
            (clj/let [x (clj/apply f args)
                      ilogic2 (m.logic/pass ilogic (m.state/set-object istate2 x))]
              (m.logic/each (m.logic/query ilogic2 q)
                (fn [_] ilogic2)))
            (m.logic/fail ilogic istate2)))
        (m.logic/fail ilogic istate0)))))


(defrecord Rule [q y]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.protocols/-query q ilogic))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.protocols/-yield y ilogic))

  m.protocols/IRedex
  (-redex [this ilogic]
    (m.logic/each (m.protocols/-query q ilogic)
      (fn [istate]
        (m.protocols/-yield y (m.logic/pass ilogic istate))))))


(defrecord RuleSystem [id rules]
  m.protocols/IQuery
  (-query [this ilogic]
    (if (zero? (count rules))
      (m.logic/each ilogic
        (fn [istate] (m.logic/fail ilogic istate)))
      (m.logic/scan rules
        (fn [rule] (m.protocols/-query rule ilogic)))))

  m.protocols/IYield
  (-yield [this ilogic]
    (if (zero? (count rules))
      (m.logic/each ilogic
        (fn [istate] (m.logic/fail ilogic istate)))
      (m.logic/scan rules
        (fn [rule] (m.protocols/-yield rule ilogic)))))

  m.protocols/IRedex
  (-redex [this ilogic]
    (if (zero? (count rules))
      (m.logic/each ilogic
        (fn [istate] (m.logic/fail ilogic istate)))
      (m.logic/scan rules
        (fn [rule] (m.protocols/-redex rule ilogic))))))

(defrecord Again [id a])

(defrecord StringCast [a]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.protocols/-query a (m.logic/check-object ilogic string?)))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/update-object (m.protocols/-yield a ilogic) clj/str)))

(defrecord StringConcat [a b]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/each ilogic
      (fn [s]
        (clj/let [x (m.state/get-object s)]
          (if (string? x)
            (m.logic/scan (m.algorithms/string-partitions x 2)
              (fn [[a-part b-part]]
                (m.logic/each (m.protocols/-query a (m.logic/pass ilogic (m.state/set-object s a-part)))
                  (fn [s]
                    (m.protocols/-query b (m.logic/pass ilogic (m.state/set-object s b-part)))))))
            (m.logic/fail ilogic s))))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/foreach [s (m.logic/yield ilogic a)
                      :let [a (m.state/get-object s)]]
      (m.logic/update-object (m.logic/yield (m.logic/pass ilogic s) b)
        (fn [b] (clj/str a b))))))


;; Symbol
;; ------

(defrecord SymbolUnqualified [name]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/foreach [s ilogic
                      :let [x (m.state/get-object s)]]
      (if (symbol? x)
        (m.logic/query (m.logic/pass ilogic (m.state/set-object s (clj/name x))) name)
        (m.logic/fail ilogic s))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/foreach [s (m.logic/yield ilogic name)
                      :let [x (m.state/get-object s)]]
      (if (string? x)
        (m.logic/pass ilogic (m.state/set-object s (clj/symbol x)))
        (m.logic/fail ilogic s)))))

(defrecord SymbolQualified [ns name]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/foreach [istate0 ilogic
                      :let [x (m.state/get-object istate0)]]
      (if (symbol? x)
        (clj/let [x-ns (namespace x)
                  x-name (clj/name x)]
          (m.logic/foreach [istate1 (-> (m.logic/pass ilogic (m.state/set-object istate0 x-ns))
                                        (m.logic/query ns))
                            :let [istate2 (m.state/set-object istate1 x-name)]]
            (m.logic/query (m.logic/pass ilogic istate2) name)))
        (m.logic/fail ilogic istate0))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/foreach [s1 (m.logic/yield ilogic ns)
                      :let [x (m.state/get-object s1)]]
      (if (string? x)
        (m.logic/foreach [s2 (m.logic/yield (m.logic/pass ilogic s1) name)
                          :let [y (m.state/get-object s2)]]
          (if (string? y)
            (m.logic/pass ilogic (m.state/set-object s2 (clj/symbol x y)))
            (m.logic/fail ilogic s2)))
        (m.logic/fail ilogic s1)))))


;; Keyword
;; -------

(defrecord KeywordUnqualified [name]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/foreach [istate ilogic
                      :let [x (m.state/get-object istate)]]
      (if (keyword? x)
        (m.logic/query (m.logic/pass ilogic (m.state/set-object istate (clj/name x))) name)
        (m.logic/fail ilogic istate))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/foreach [istate (m.protocols/-yield name ilogic)
                      :let [x (m.state/get-object istate)]]
      (if (string? x)
        (m.logic/pass ilogic (m.state/set-object istate (clj/keyword x)))
        (m.logic/fail ilogic istate)))))


(defrecord KeywordQualified [ns name]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/foreach [istate0 ilogic
                      :let [x (m.state/get-object istate0)]]
      (if (qualified-keyword? x)
        (clj/let [x-ns (namespace x)
                  x-name (clj/name x)]
          (m.logic/foreach [istate1 (m.logic/query (m.logic/pass ilogic (m.state/set-object istate0 x-ns)) ns)
                            :let [istate2 (m.state/set-object istate1 x-name)]]
            (m.logic/query (m.logic/pass ilogic istate2) name)))
        (m.logic/fail ilogic istate0))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/foreach [istate1 (m.logic/yield ilogic ns)
                      :let [x (m.state/get-object istate1)]]
      (if (string? x)
        (m.logic/foreach [istate2 (m.logic/yield ilogic name)
                          :let [y (m.state/get-object istate2)]]
          (if (string? y)
            (m.logic/pass ilogic (m.state/set-object istate2 (clj/keyword x y)))
            (m.logic/fail ilogic istate2)))
        (m.logic/fail ilogic istate1)))))

;; Sequences
;; ---------------------------------------------------------------------

(defrecord SequenceMember [a]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.protocols/-query a (m.logic/check-object ilogic sequential?)))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/check-object (m.protocols/-yield a ilogic) sequential?)))

(defrecord SequenceEmpty []
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/check-object ilogic (every-pred sequential? (complement clj/seq))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/some (m.logic/set-object ilogic ()) (m.logic/set-object ilogic []))))

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
            (m.logic/scan (m.algorithms/partitions 2 x)
              (fn [[x-a x-b]]
                (m.logic/each (m.protocols/-query a (m.logic/pass m (m.state/set-object s x-a)))
                  (fn [s]
                    (m.protocols/-query b (m.logic/pass m (m.state/set-object s x-b)))))))
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
    (m.logic/foreach [istate0 ilogic
                      :let [x (m.state/get-object istate0)]]
      (if (sequential? x)
        (if (clj/seq x)
          (clj/let [head (first x)
                    tail (rest x)]
            (m.logic/pick (m.protocols/-query this (m.logic/each (m.protocols/-query a (m.logic/pass ilogic (m.state/set-object istate0 head)))
                                                     (fn [istate1]
                                                       (m.logic/pass ilogic (m.state/set-object istate1 tail)))))
                          (m.protocols/-query b ilogic)))
          (m.protocols/-query b (m.logic/pass ilogic istate0)))
        (m.logic/fail ilogic istate0))))

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

(defrecord SeqCast [a]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.protocols/-query a (m.logic/check-object ilogic (some-fn seq? nil?))))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/update-object
        (m.logic/check-object (m.protocols/-yield a ilogic)
          seqable?)
      clj/seq)))

(defrecord VectorCast [a]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.protocols/-query a (m.logic/check-object ilogic vector?)))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/update-object
        (m.logic/check-object (m.protocols/-yield a ilogic)
          seqable?)
      clj/vec)))


;; HashMap
;; ---------------------------------------------------------------------

(defrecord HashMapEmpty []
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/check-object ilogic (every-pred map? empty?)))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/set-object ilogic {})))

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
              (if (zero? (count x))
                (m.logic/fail ilogic s)
                (m.logic/scan x
                  (fn [e]
                    (clj/let [x-e (dissoc x (key e))]
                      (m.logic/each (m.protocols/-query entry (m.logic/pass ilogic (m.state/set-object s e)))
                        (fn [s]
                          (m.protocols/-query (.-m this) (m.logic/pass ilogic (m.state/set-object s x-e)))))))))
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
              (m.logic/scan (m.algorithms/map-partitions x 2)
                (fn [[a b]]
                  (m.logic/each (m.protocols/-query (.-m1 this) (m.logic/pass ilogic (m.state/set-object s a)))
                    (fn [s]
                      (m.protocols/-query (.-m2 this) (m.logic/pass ilogic (m.state/set-object s b))))))))
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

;; Meta
;; ---------------------------------------------------------------------

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

(defrecord Explain [a]
  m.protocols/IQuery
  (-query [this ilogic]
    (m.logic/explain (m.protocols/-query a ilogic) {:pattern a, :method :query}))

  m.protocols/IYield
  (-yield [this ilogic]
    (m.logic/explain (m.protocols/-yield a ilogic) {:pattern a, :method :yield}))

  m.protocols/IRedex
  (-redex [this ilogic]
    (m.logic/explain (m.protocols/-redex a ilogic) {:pattern a, :method :redex})))

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

(def ^{:arglists '([a])}
  explain
  #'->Explain)
