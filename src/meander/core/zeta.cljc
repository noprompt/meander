(ns ^:no-doc meander.core.zeta
  (:refer-clojure :exclude [*
                            +
                            apply
                            assoc
                            cat
                            conj
                            cons
                            concat
                            empty
                            eval
                            keyword
                            list
                            merge
                            not
                            hash-map
                            hash-set
                            set
                            seq
                            some
                            symbol
                            str
                            take
                            test
                            vec
                            vector])
  (:require [#?(:clj clojure.core, :cljs cljs.core) :as clojure]
            [clojure.set :as set]
            [clojure.zip :as zip]
            [meander.algorithms.zeta :as m.algorithms]))

(defprotocol IQueryFunction
  :extend-via-metadata true
  (query-function [this environment]))

(defprotocol IYieldFunction
  :extend-via-metadata true
  (yield-function [this environment]))

(defprotocol IChildren
  :extend-via-metadata true
  (children [this]))

(defprotocol IGround
  :extend-via-metadata true
  (ground? [this environment]))

(defn annotate [imeta & facts]
  (vary-meta imeta clojure/assoc ::annotations (clojure/set facts)))

(defn annotate-add [imeta & facts]
  (vary-meta imeta update ::annotations (fnil clojure/into #{}) (clojure/set facts)))

(defn annotations [imeta]
  (clojure/set (get (meta imeta) ::annotations)))

(defn annotated-with? [imeta x]
  (contains? (annotations imeta) x))

(defn annotate-using [imeta-target imeta-source]
  (vary-meta imeta-target clojure/assoc ::annotations (annotations imeta-source)))

;; Primitive Patterns
;; ---------------------------------------------------------------------

(defrecord AnythingPattern []
  IQueryFunction
  (query-function [this environment]
    (let [pass (get environment :pass)]
      (fn [state]
        (pass state))))

  IYieldFunction
  (yield-function [this environment]
    (let [pass (get environment :pass)
          give (get environment :give)
          make (get environment :make)]
      (fn [state]
        (pass (give state (make state)))))))

(defrecord CodePattern [x]
  IQueryFunction
  (query-function [this environment]
    (let [call (get environment :call)
          eval (get environment :eval)
          fail (get environment :fail)
          pass (get environment :pass)
          take (get environment :take)
          test (get environment :test)
          = (eval `clojure/=)
          y (eval x)]
      (fn [state]
        (let [target (take state)]
          (test (call = target y)
                (fn [] (pass state))
                (fn [] (fail state)))))))

  IYieldFunction
  (yield-function [this environment]
    (let [eval (get environment :eval)
          pass (get environment :pass)
          give (get environment :give)
          y (eval x)]
      (fn [state]
        (pass (give state y))))))

(defrecord DataPattern [x]
  IQueryFunction
  (query-function [this environment]
    (let [call (get environment :call)
          data (get environment :data)
          eval (get environment :eval)
          fail (get environment :fail)
          pass (get environment :pass)
          take (get environment :take)
          test (get environment :test)
          = (eval `clojure/=)
          y (data x)]
      (fn [state]
        (let [target (take state)]
          (test (call = target y)
                (fn [] (pass state))
                (fn [] (fail state)))))))

  IYieldFunction
  (yield-function [this environment]
    (let [data (get environment :data)
          eval (get environment :eval)
          pass (get environment :pass)
          give (get environment :give)
          y (data x)]
      (fn [state]
        (pass (give state y))))))

(defrecord DualPattern [is-pattern is-not-pattern]
  IChildren
  (children [this]
    [is-pattern is-not-pattern])

  IQueryFunction
  (query-function [this environment]
    (let [dual (get environment :dual)
          is-query (query-function is-pattern environment)
          is-not-query (query-function is-not-pattern environment)]
      (fn [state]
        (dual (is-query state) (is-not-query state)))))

  IYieldFunction
  (yield-function [this environment]
    (let [bind (get environment :bind)
          dual (get environment :dual)
          give (get environment :give)
          take (get environment :take)
          is-yield (yield-function is-pattern environment)
          is-not-query (query-function is-not-pattern environment)]
      (fn [state]
        (bind (fn [yield-state]
                (let [object (take yield-state)]
                  (dual yield-state (is-not-query (give state object)))))
              (is-yield state))))))

(defrecord OnePattern [pattern-a pattern-b]
  IChildren
  (children [this]
    [pattern-a pattern-b])

  IQueryFunction
  (query-function [this environment]
    (let [pick (get environment :pick)
          query-a (query-function pattern-a environment)
          query-b (query-function pattern-b environment)]
      (fn [state]
        (pick (query-a state)
              (query-b state)))))

  IYieldFunction
  (yield-function [this environment]
    (let [pick (get environment :pick)
          yield-a (yield-function pattern-a environment)
          yield-b (yield-function pattern-b environment)]
      (fn [state]
        (pick (yield-a state)
              (yield-b state))))))

(defrecord SomePattern [pattern-a pattern-b]
  IChildren
  (children [this]
    [pattern-a pattern-b])

  IQueryFunction
  (query-function [this environment]
    (let [join (get environment :join)
          query-a (query-function pattern-a environment)
          query-b (query-function pattern-b environment)]
      (fn [state]
        (join (query-a state)
              (query-b state)))))

  IYieldFunction
  (yield-function [this environment]
    (let [join (get environment :join)
          yield-a (yield-function pattern-a environment)
          yield-b (yield-function pattern-b environment)]
      (fn [state]
        (join (yield-a state)
              (yield-b state))))))

(defrecord AllPattern [a-pattern b-pattern]
  IChildren
  (children [this]
    [a-pattern b-pattern])

  IQueryFunction
  (query-function [this environment]
    (let [bind (get environment :bind)
          give (get environment :give)
          take (get environment :take)
          a-query (query-function a-pattern environment)
          b-query (query-function b-pattern environment)]
      (fn [state]
        (let [object (take state)]
          (bind (fn [a-state]
                  (b-query (give a-state object)))
                (a-query state))))))

  IYieldFunction
  (yield-function [this environment]
    (let [bind (get environment :bind)
          join (get environment :join)
          a-query (query-function a-pattern environment)
          b-query (query-function b-pattern environment)
          a-yield (yield-function a-pattern environment)
          b-yield (yield-function b-pattern environment)]
      (fn [state]
        (join (bind b-query (a-yield state))
              (bind a-query (b-yield state)))))))

(defrecord ApplyPattern [function-pattern arguments-pattern return-pattern]
  IChildren
  (children [this]
    [function-pattern arguments-pattern return-pattern])

  IQueryFunction
  (query-function [this environment]
    (let [bind (get environment :bind)
          call (get environment :call)
          eval (get environment :eval)
          pass (get environment :pass)
          give (get environment :give)
          take (get environment :take)
          apply (eval `clojure/apply)
          function-yield (yield-function function-pattern environment)
          arguments-yield (yield-function arguments-pattern environment)
          return-query (query-function return-pattern environment)]
      (fn [state]
        (let [object (take state)]
          (bind
           (fn [function-state]
             (bind (fn [arguments-state]
                     (let [object (call apply (take function-state) object (take arguments-state))]
                       (bind (fn [return-state]
                               (pass (give return-state object)))
                             (return-query (give state object)))))
                   (arguments-yield state)))
           (function-yield state))))))

  IYieldFunction
  (yield-function [this environment]
    (let [bind (get environment :bind)
          call (get environment :call)
          eval (get environment :eval)
          fail (get environment :fail)
          give (get environment :give)
          pass (get environment :pass)
          take (get environment :take)
          test (get environment :test)
          apply (eval `clojure/apply)
          seqable? (eval `clojure/seqable?)
          fn? (eval `clojure/fn?)
          function-yield (yield-function function-pattern environment)
          arguments-yield (yield-function arguments-pattern environment)
          return-query (query-function return-pattern environment)
          skip-fn-check? (annotated-with? function-pattern `clojure/fn?)
          skip-seqable-check? (annotated-with? arguments-pattern `clojure/seqable?)]
      (fn [state]
        (bind (fn [function-state]
                (let [f (take function-state)]
                  (if skip-fn-check?
                    (bind (fn [arguments-state]
                            (let [arguments (take arguments-state)]
                              (if skip-seqable-check?
                                (let [object (call apply f arguments)
                                      object-state (give arguments-state object)]
                                  (bind (fn [return-state]
                                          (pass object-state))
                                        (return-query object-state)))
                                (test (call seqable? arguments)
                                      (fn []
                                        (let [object (call apply f arguments)
                                              object-state (give arguments-state object)]
                                          (bind (fn [return-state]
                                                  (pass object-state))
                                                (return-query object-state))))
                                      (fn []
                                        (fail arguments-state))))))
                          (arguments-yield function-state))
                    (test (call fn? f)
                          (fn []
                            (bind (fn [arguments-state]
                                    (let [arguments (take arguments-state)]
                                      (if skip-seqable-check?
                                        (let [object (call apply f arguments)
                                              object-state (give arguments-state object)]
                                          (bind (fn [return-state]
                                                  (pass object-state))
                                                (return-query object-state)))
                                        (test (call seqable? arguments)
                                              (fn []
                                                (let [object (call apply f arguments)
                                                      object-state (give arguments-state object)]
                                                  (bind (fn [return-state]
                                                          (pass object-state))
                                                        (return-query object-state))))
                                              (fn []
                                                (fail arguments-state))))))
                                  (arguments-yield function-state)))
                          (fn []
                            (fail function-state))))))
              (function-yield state))))))

(defrecord PredicatePattern [predicate-pattern x-pattern]
  IChildren
  (children [this]
    [predicate-pattern x-pattern])

  IQueryFunction
  (query-function [this environment]
    (let [bind (get environment :bind)
          call (get environment :call)
          fail (get environment :fail)
          pass (get environment :pass)
          take (get environment :take)
          test (get environment :test)
          predicate-yield (yield-function predicate-pattern environment)
          x-query (query-function x-pattern environment)]
      (fn query [state]
        (let [object (take state)]
          (bind (fn [predicate-state]
                  (let [predicate (take predicate-state)]
                    (test (call predicate object)
                          (fn []
                            (x-query state))
                          (fn []
                            (fail state)))))
                (predicate-yield state))))))

  IYieldFunction
  (yield-function [this environment]
    (let [bind (get environment :bind)
          call (get environment :call)
          fail (get environment :fail)
          pass (get environment :pass)
          take (get environment :take)
          test (get environment :test)
          predicate-yield (yield-function predicate-pattern environment)
          x-yield (yield-function x-pattern environment)]
      (fn yield [state]
        (bind (fn [predicate-state]
                (let [predicate (take predicate-state)]
                  (bind (fn [x-state]
                          (let [x (take x-state)]
                            (test (call predicate x)
                                  (fn []
                                    (pass x-state))
                                  (fn []
                                    (fail x-state)))))
                        (x-yield predicate-state))))
              (predicate-yield state))))))

(defrecord ProjectPattern [a-pattern b-pattern c-pattern]
  IChildren
  (children [this]
    [a-pattern b-pattern c-pattern])

  IQueryFunction
  (query-function [this environment]
    (let [bind (get environment :bind)
          give (get environment :give)
          pass (get environment :pass)
          take (get environment :take)
          a-yield (yield-function a-pattern environment)
          b-query (query-function b-pattern environment)
          c-query (query-function c-pattern environment)]
      (fn [state]
        (let [object (take state)]
          (bind (fn [a-state]
                  (bind (fn [b-state]
                          (c-query (give b-state object)))
                        (b-query (give state (take a-state)))))
                (a-yield state))))))

  IYieldFunction
  (yield-function [this environment]
    (let [bind (get environment :bind)
          give (get environment :give)
          take (get environment :take)
          a-yield (yield-function a-pattern environment)
          b-query (query-function b-pattern environment)
          c-yield (yield-function c-pattern environment)]
      (fn [state]
        (bind (fn [a-state]
                (bind c-yield (b-query (give state (take a-state)))))
              (a-yield state))))))

(defrecord Variable [id fold-function unfold-function]
  IQueryFunction
  (query-function [this environment]
    (let [fail (get environment :fail)
          save (get environment :save)
          pass (get environment :pass)
          take (get environment :take)
          fold (fold-function environment)]
      (fn [state]
        (save state id fold pass fail))))

  IYieldFunction
  (yield-function [this environment]
    (let [fail (get environment :fail)
          give (get environment :give)
          load (get environment :load)
          pass (get environment :pass)
          unfold (unfold-function environment)]
      (fn [state]
        (load state id unfold pass fail)))))

(defrecord Reference [id]
  IQueryFunction
  (query-function [this environment]
    (let [call (get environment :call)
          find (get environment :find)]
      (fn [state]
        (call (find state id) state))))

  IYieldFunction
  (yield-function [this environment]
    (let [call (get environment :call)
          find (get environment :find)]
      (fn [state]
        (call (find state id) state)))))

(defrecord With [pattern-mapping pattern]
  IChildren
  (children [this]
    [pattern])

  IQueryFunction
  (query-function [this environment]
    (let [with (get environment :with)
          mapping (reduce-kv (fn [m k v]
                               (clojure/assoc m (get k :id) (query-function v environment)))
                             {}
                             pattern-mapping)
          query (query-function pattern environment)]
      (fn [state]
        (with state mapping query))))

  IYieldFunction
  (yield-function [this environment]
    (let [with (get environment :with)
          mapping (reduce-kv (fn [m k v]
                               (clojure/assoc m (get k :id) (yield-function v environment)))
                             {}
                             pattern-mapping)
          yield (yield-function pattern environment)]
      (fn [state]
        (with state mapping yield)))))

(defrecord Again [pattern]
  IChildren
  (children [this]
    [pattern])

  IQueryFunction
  (query-function [this environment]
    (let [bind (get environment :bind)
          call (get environment :call)
          cata (get environment :cata)
          give (get environment :give)
          mint (get environment :mint)
          take (get environment :take)
          pattern-query (query-function pattern environment)
          cata-query (query-function cata environment)]
      (fn [state]
        (bind (fn [cata-state]
                (let [cata-object (take cata-state)]
                  (pattern-query (give state cata-object))))
              (cata-query (mint state))))))

  IYieldFunction
  (yield-function [this environment]
    (let [bind (get environment :bind)
          call (get environment :call)
          cata (get environment :cata)
          give (get environment :give)
          mint (get environment :mint)
          pass (get environment :pass)
          take (get environment :take)
          pattern-yield (yield-function pattern environment)
          cata-yield (yield-function cata environment)]
      (fn [state]
        (bind (fn [pattern-state]
                (bind (fn [cata-state]
                        (pass (give pattern-state (take cata-state))))
                      (cata-yield (mint pattern-state))))
              (pattern-yield state))))))

;; Regular Expression Patterns
;; ---------------------------------------------------------------------

(defrecord RegexCons [head-pattern tail-pattern]
  IChildren
  (children [this]
    [head-pattern tail-pattern])

  IQueryFunction
  (query-function [this environment]
    (let [bind (get environment :bind)
          call (get environment :call)
          eval (get environment :eval)
          fail (get environment :fail)
          give (get environment :give)
          pass (get environment :pass)
          take (get environment :take)
          test (get environment :test)
          head-query (query-function head-pattern environment)
          tail-query (query-function tail-pattern environment)
          nth (eval `clojure/nth)
          tail (eval `m.algorithms/tail)
          seq (eval `clojure/seq)
          sequential? (eval `clojure/sequential?)
          zero (eval 0)
          skip-check? (annotated-with? this `clojure/sequential?)]
      (if skip-check?
        (fn [state]
          (let [object (take state)]
            (test (call seq object)
                  (fn []
                    (let [head (call nth object zero)
                          rest (call tail object)]
                      (bind (fn [x-state]
                              (tail-query (give x-state rest)))
                            (head-query (give state head)))))
                  (fn []
                    (fail state)))))
        (fn [state]
          (let [object (take state)]
            (test (call sequential? object)
                  (fn []
                    (test (call seq object)
                          (fn []
                            (let [head (call nth object zero)
                                  rest (call tail object)]
                              (bind (fn [x-state]
                                      (tail-query (give x-state rest)))
                                    (head-query (give state head)))))
                          (fn []
                            (fail state))))
                  (fn []
                    (fail state))))))))

  IYieldFunction
  (yield-function [this environment]
    (let [bind (get environment :bind)
          call (get environment :call)
          eval (get environment :eval)
          fail (get environment :fail)
          give (get environment :give)
          pass (get environment :pass)
          take (get environment :take)
          test (get environment :test)
          head-yield (yield-function head-pattern environment)
          tail-yield (yield-function tail-pattern environment)
          cons (eval `clojure/cons)
          sequential? (eval `clojure/sequential?)]
      (fn [state]
        (bind (fn [head-state]
                (let [head (take head-state)]
                  (bind (fn [tail-state]
                          (let [tail (take tail-state)]
                            (test (call sequential? tail)
                                  (fn []
                                    (pass (give tail-state (call cons head tail))))
                                  (fn []
                                    (fail tail-state)))))
                        (tail-yield head-state))))
              (head-yield state))))))

(defrecord RegexConcatenation [patterns]
  IChildren
  (children [this]
    (clojure/vec patterns))

  IQueryFunction
  (query-function [this environment]
    (let [bind (get environment :bind)
          call (get environment :call)
          eval (get environment :eval)
          give (get environment :give)
          fail (get environment :fail)
          pass (get environment :pass)
          take (get environment :take)
          test (get environment :test)
          queries (map (fn [pattern]
                         (query-function pattern environment))
                       patterns)
          indexed-queries (map-indexed
                           (fn [index query] [(eval index) query])
                           queries)
          n* (count patterns)
          m* (inc n*)
          n (eval n*)
          m (eval m*)
          = (eval `clojure/=)
          nth (eval `clojure/nth)
          bounded-count (eval `clojure/bounded-count)
          sequential? (eval `clojure/sequential?)
          skip-check? (annotated-with? this `clojure/sequential?)]
      (if skip-check?
        (fn [state]
          (let [object (take state)]
            (test (call = n (call bounded-count m object))
                  (fn []
                    (reduce
                     (fn [m [index query]]
                       (let [x (call nth object index)]
                         (bind (fn [state]
                                 (query (give state x)))
                               m)))
                     (pass state)
                     indexed-queries))
                  (fn []
                    (fail state)))))
        (fn [state]
          (let [object (take state)]
            (test (call sequential? object)
                  (fn []
                    (test (call = n (call bounded-count m object))
                          (fn []
                            (reduce
                             (fn [m [index query]]
                               (let [x (call nth object index)]
                                 (bind (fn [state]
                                         (query (give state x)))
                                       m)))
                             (pass state)
                             indexed-queries))
                          (fn []
                            (fail state))))
                  (fn []
                    (fail state))))))))

  IYieldFunction
  (yield-function [this environment]
    (let [bind (get environment :bind)
          call (get environment :call)
          eval (get environment :eval)
          give (get environment :give)
          pass (get environment :pass)
          take (get environment :take)
          n (count patterns)
          yields (map (fn [pattern]
                        (yield-function pattern environment))
                      patterns)
          conj (eval `clojure/conj)
          vector-empty (eval [])
          vector (eval `clojure/vector)]
      (case n
        0
        (fn [state]
          (pass (give state vector-empty)))

        1
        (let [yield (nth yields 0)]
          (fn [state]
            (bind (fn [state]
                    (pass (give state (call vector (take state)))))
                  (yield state))))

        ;; else
        (let [first-yield (nth yields 0)
              rest-yields (rest yields)]
          (fn [state]
            (reduce (fn [m yield]
                      (bind (fn [state]
                              (let [xs (take state)]
                                (bind (fn [state]
                                        (pass (give state (call conj xs (take state)))))
                                      (yield state))))
                            m))
                    (bind (fn [state]
                            (pass (give state (call vector (take state)))))
                          (first-yield state))
                    rest-yields)))))))

(defrecord RegexJoin [x-pattern y-pattern]
  IChildren
  (children [this]
    [x-pattern y-pattern])

  IQueryFunction
  (query-function [this environment]
    (let [bind (get environment :bind)
          call (get environment :call)
          eval (get environment :eval)
          fail (get environment :fail)
          give (get environment :give)
          pass (get environment :pass)
          test (get environment :test)
          take (get environment :take)
          scan (get environment :scan)
          x-query (query-function (annotate-add (annotate-using x-pattern this) `clojure/sequential?) environment)
          y-query (query-function (annotate-add (annotate-using y-pattern this) `clojure/sequential?) environment)
          nth (eval `clojure/nth)
          sequential? (eval `clojure/sequential?)
          partitions (eval `meander.algorithms.zeta/partitions)
          zero (eval 0)
          one (eval 1)
          two (eval 2)
          skip-check? (annotated-with? this `clojure/sequential?)]
      (if skip-check?
        (fn [state]
          (let [object (take state)]
            (scan (fn [partition]
                    (let [x (call nth partition zero)
                          y (call nth partition one)]
                      (bind (fn [x-state]
                              (y-query (give x-state y)))
                            (x-query (give state x)))))
                  (call partitions two object))))
        (fn [state]
          (let [object (take state)]
            (test (call sequential? object)
                  (fn []
                    (scan (fn [partition]
                            (let [x (call nth partition zero)
                                  y (call nth partition one)]
                              (bind (fn [x-state]
                                      (y-query (give x-state y)))
                                    (x-query (give state x)))))
                          (call partitions two object)))
                  (fn []
                    (fail state))))))))

  IYieldFunction
  (yield-function [this environment]
    (let [bind (get environment :bind)
          call (get environment :call)
          eval (get environment :eval)
          give (get environment :give)
          fail (get environment :fail)
          give (get environment :give)
          pass (get environment :pass)
          take (get environment :take)
          test (get environment :test)
          x-yield (yield-function x-pattern environment)
          y-yield (yield-function y-pattern environment)
          concat (eval `clojure/concat)
          coll? (eval `clojure/coll?)]
      (fn [state]
        (bind (fn [x-state]
                (let [x (take x-state)]
                  (test (call coll? x)
                        (fn []
                          (bind (fn [y-state]
                                  (let [y (take y-state)]
                                    (test (call coll? y)
                                          (fn []
                                            (pass (give y-state (call concat x y))))
                                          (fn []
                                            (fail y-state)))))
                                (y-yield x-state)))
                        (fn []
                          (fail x-state)))))
              (x-yield state))))))

(defrecord GreedyStar [subsequence-pattern rest-pattern]
  IChildren
  (children [this]
    [subsequence-pattern rest-pattern])

  IQueryFunction
  (query-function [this environment]
    (let [bind (get environment :bind)
          call (get environment :call)
          eval (get environment :eval)
          fail (get environment :fail)
          give (get environment :give)
          pass (get environment :pass)
          pick (get environment :pick)
          scan (get environment :scan)
          star (get environment :star)
          take (get environment :take)
          test (get environment :test)
          subsequence-query (query-function (annotate-using subsequence-pattern this) environment)
          rest-query (query-function (annotate-using rest-pattern this) environment)
          partitions (eval `m.algorithms/partitions)
          nth (eval `clojure/nth)
          rest (eval `clojure/rest)
          seq (eval `clojure/seq)
          sequential? (eval `clojure/sequential?)
          zero (eval 0)
          one (eval 1)
          two (eval 2)
          skip-check? (annotated-with? this `clojure/sequential?)]
      (fn [state]
        (let [object (take state)]
          (test (or skip-check? (call sequential? object))
                (fn []
                  (star (fn [cata object state]
                          (test (call seq object)
                                (fn []
                                  (scan (fn [partition]
                                          (let [a (call nth partition zero)
                                                b (call nth partition one)]
                                            (bind (fn [subsequence-state]
                                                    (pick (cata b subsequence-state)
                                                          (rest-query (give subsequence-state b))))
                                                  (subsequence-query (give state a)))))
                                        (call rest (call partitions two object))))
                                (fn []
                                  (rest-query (give state object)))))
                        object
                        state))
                (fn []
                  (fail state)))))))

  IYieldFunction
  (yield-function [this environment]
    (let [bind (get environment :bind)
          call (get environment :call)
          eval (get environment :eval)
          fail (get environment :fail)
          give (get environment :give)
          pass (get environment :pass)
          pick (get environment :pick)
          star (get environment :star)
          take (get environment :take)
          test (get environment :test)
          subsequence-yield (yield-function subsequence-pattern environment)
          rest-yield (yield-function rest-pattern environment)
          concat (eval `clojure/concat)
          sequential? (eval `clojure/sequential?)]
      (fn [state]
        (star (fn [rec state]
                (let [a (take state)]
                  (pick (bind (fn [subsequence-state]
                                (let [b (take subsequence-state)]
                                  (test (call sequential? b)
                                        (fn []
                                          (call rec (give subsequence-state (call concat a b))))
                                        (fn []
                                          (fail state)))))
                              (subsequence-yield (give state ())))
                        (bind (fn [rest-state]
                                 (let [b (take rest-state)]
                                   (test (call sequential? b)
                                         (fn []
                                           (pass (give rest-state (call concat a b))))
                                         (fn []
                                           (fail rest-state)))))
                              (rest-yield state)))))
              (give state ()))))))

(defrecord FrugalStar [subsequence-pattern rest-pattern]
  IChildren
  (children [this]
    [subsequence-pattern rest-pattern])

  IQueryFunction
  (query-function [this environment]
    (let [bind (get environment :bind)
          call (get environment :call)
          eval (get environment :eval)
          fail (get environment :fail)
          give (get environment :give)
          join (get environment :join)
          pass (get environment :pass)
          scan (get environment :scan)
          star (get environment :star)
          take (get environment :take)
          test (get environment :test)
          subsequence-query (query-function subsequence-pattern environment)
          rest-query (query-function rest-pattern environment)
          partitions (eval `m.algorithms/partitions)
          nth (eval `clojure/nth)
          rest (eval `clojure/rest)
          seq (eval `clojure/seq)
          sequential? (eval `clojure/sequential?)
          zero (eval 0)
          one (eval 1)
          two (eval 2)]
      (fn [state]
        (let [object (take state)]
          (test (call sequential? object)
                (fn []
                  (star (fn [rec state]
                          (let [object (take state)]
                            (test (call seq object)
                                  (fn []
                                    (join (rest-query state)
                                          (scan (fn [x]
                                                  (let [a (call nth x zero)
                                                        b (call nth x one)]
                                                    (bind (fn [a-state]
                                                            (call rec (give a-state b)))
                                                          (subsequence-query (give state a)))))
                                                (call rest (call partitions two object)))))
                                  (fn []
                                    (pass state)))))
                        state))
                (fn []
                  (fail state)))))))

  IYieldFunction
  (yield-function [this environment]
    (let [bind (get environment :bind)
          call (get environment :call)
          eval (get environment :eval)
          fail (get environment :fail)
          give (get environment :give)
          join (get environment :join)
          pass (get environment :pass)
          pick (get environment :pick)
          star (get environment :star)
          take (get environment :take)
          test (get environment :test)
          subsequence-yield (yield-function subsequence-pattern environment)
          rest-yield (yield-function rest-pattern environment)
          concat (eval `clojure/concat)
          sequential? (eval `clojure/sequential?)]
      (fn [state]
        (join (rest-yield state)
              (star (fn [rec xs state]
                      (bind (fn [pattern-state]
                              (let [subsequence (take pattern-state)]
                                (test (call sequential? subsequence)
                                      (fn []
                                        (bind (fn [rest-state]
                                                (let [rest (take rest-state)]
                                                  (test (call sequential? rest)
                                                        (fn []
                                                          (let [xs* (call concat xs subsequence)
                                                                ys* (call concat xs* rest)]
                                                            (join (pass (give rest-state ys*))
                                                                  (call rec xs* pattern-state))))
                                                        (fn [] (fail state)))))
                                              (rest-yield pattern-state)))
                                      (fn []
                                        (fail state)))))
                            (subsequence-yield state)))
                    ()
                    state))))))

;; Data Structure Patterns
;; -----------------------

(defrecord AssocPattern [map-pattern key-pattern val-pattern]
  IChildren
  (children [this]
    [map-pattern key-pattern val-pattern])

  IQueryFunction
  (query-function [this environment]
    (let [bind (get environment :bind)
          call (get environment :call)
          eval (get environment :eval)
          fail (get environment :fail)
          give (get environment :give)
          pass (get environment :pass)
          scan (get environment :scan)
          take (get environment :take)
          test (get environment :test)
          map-query (query-function map-pattern environment)
          key-query (query-function key-pattern environment)
          val-query (query-function val-pattern environment)
          dissoc (eval `clojure/dissoc)
          get (eval `clojure/get)
          key (eval `clojure/key)
          map? (eval `clojure/map?)
          seq (eval `clojure/seq)
          val (eval `clojure/val)]
      #_
      (if (seq key-values)
        (let [key-values (vec key-values)]
          (fn [state]
            (let [object (take state)]
              (test (call map? object)
                    (fn []
                      (scan (fn [k]
                              (let [v (call get object k)
                                    m (call dissoc object k)]
                                (bind (fn [val-state]
                                        (map-query (give val-state m)))
                                      (val-query (give state v)))))
                            key-values))
                    (fn []
                      (fail state)))))))
      (fn [state]
        (let [object (take state)]
          (test (call map? object)
                (fn []
                  (scan (fn [entry]
                          (let [k (call key entry)
                                v (call val entry)
                                m (call dissoc object k)]
                            (bind (fn [key-state]
                                    (bind (fn [val-state]
                                            (map-query (give val-state m)))
                                          (val-query (give key-state v))))
                                  (key-query (give state k)))))
                        (call seq object)))
                (fn []
                  (fail state)))))))

  IYieldFunction
  (yield-function [this environment]
    (let [bind (get environment :bind)
          call (get environment :call)
          eval (get environment :eval)
          fail (get environment :fail)
          give (get environment :give)
          pass (get environment :pass)
          scan (get environment :scan)
          take (get environment :take)
          test (get environment :test)
          map-yield (yield-function map-pattern environment)
          key-yield (yield-function key-pattern environment)
          val-yield (yield-function val-pattern environment)
          assoc (eval `clojure/assoc)
          map? (eval `clojure/map?)
          skip-map-check? (annotated-with? map-pattern `clojure/map?)]
      (fn [state]
        (bind (fn [map-state]
                (let [m (take map-state)]
                  (test (or skip-map-check? (call map? m))
                        (fn []
                          (bind (fn [key-state]
                                  (let [k (take key-state)]
                                    (bind (fn [val-state]
                                            (let [v (take val-state)]
                                              (pass (give val-state (call assoc m k v)))))
                                          (val-yield key-state))))
                                (key-yield map-state)))
                        (fn []
                          (fail map-state)))))
              (map-yield state))))))

(defrecord MergePattern [map-patterns]
  IChildren
  (children [this]
    (clojure/vec map-patterns))

  IQueryFunction
  (query-function [this environment]
    (let [bind (get environment :bind)
          call (get environment :call)
          eval (get environment :eval)
          fail (get environment :fail)
          give (get environment :give)
          pass (get environment :pass)
          scan (get environment :scan)
          take (get environment :take)
          test (get environment :test)
          queries (map query-function map-patterns (repeat environment))
          indexed-queries (map-indexed (fn [index query] [(eval index) query]) queries)
          map? (eval `clojure/map?)
          map-partitions (eval `m.algorithms/map-partitions)
          nth (eval `clojure/nth)
          n (count queries)]
      (if (zero? n)
        (fn [state]
          (let [object (take state)]
            (test (call map? object)
                  (fn [] (pass state))
                  (fn [] (fail state)))))
        (let [n (eval n)]
          (fn [state]
            (let [object (take state)]
              (test (call map? object)
                    (fn []
                      (scan (fn [partition]
                              (reduce
                               (fn [ma [index query]]
                                 (let [m (call nth partition index)]
                                   (bind (fn [state]
                                           (query (give state m)))
                                         ma)))
                               (pass state)
                               indexed-queries))
                            (call map-partitions object n)))
                    (fn []
                      (fail state)))))))))

  IYieldFunction
  (yield-function [this environment]
    (let [bind (get environment :bind)
          call (get environment :call)
          eval (get environment :eval)
          fail (get environment :fail)
          give (get environment :give)
          pass (get environment :pass)
          scan (get environment :scan)
          take (get environment :take)
          test (get environment :test)
          yields (map (fn [map-pattern]
                        [(yield-function map-pattern environment) (annotated-with? map-pattern `clojure/map?)])
                      map-patterns)
          map? (eval `clojure/map?)
          merge (eval `clojure/merge)]
      (fn [state]
        (reduce
         (fn [ma [yield skip-map-check?]]
           (bind (fn [state]
                   (let [m1 (take state)]
                     (bind (fn [yield-state]
                             (let [m2 (take yield-state)]
                               (test (or skip-map-check? (call map? m2))
                                     (fn []
                                       (pass (give state (call merge m1 m2))))
                                     (fn []
                                       (fail state)))))
                           (yield state))))
                 ma))
         (pass (give state {}))
         yields)))))


;; Other objects
;; ---------------------------------------------------------------------

(defrecord Rule [query-pattern yield-pattern]
  IChildren
  (children [this]
    [query-pattern yield-pattern])

  IQueryFunction
  (query-function [this environment]
    (query-function query-pattern environment))

  IYieldFunction
  (yield-function [this environment]
    (yield-function yield-pattern environment)))

(extend-type nil
  IChildren
  (children [this]
    [])

  IQueryFunction
  (query-function [this environment]
    (query-function (->DataPattern this) environment))

  IYieldFunction
  (yield-function [this environment]
    (yield-function (->DataPattern this) environment)))

(extend-type #?(:clj Object :cljs default)
  IChildren
  (children [this]
    [])

  IQueryFunction
  (query-function [this environment]
    (query-function (->DataPattern this) environment))

  IYieldFunction
  (yield-function [this environment]
    (yield-function (->DataPattern this) environment)))

;; API
;; ---------------------------------------------------------------------

(defn rx-cons? [x]
  (instance? RegexCons x))

(defn rx-cat? [x]
  (instance? RegexConcatenation x))

(defn rx-cons [x-pattern rx-pattern]
  (annotate (->RegexCons x-pattern rx-pattern)
            `clojure/seqable?
            `clojure/sequential?))

(defn rx-cat [& patterns]
  (annotate (->RegexConcatenation (into [] patterns))
            `clojure/seqable?
            `clojure/sequential?))

(defn rx-join
  ([]
   (rx-join () ()))
  ([x-pattern]
   (rx-join x-pattern ()))
  ([x-pattern y-pattern]
   (annotate (->RegexJoin x-pattern y-pattern)
             `clojure/seqable?
             `clojure/sequential?))
  ([x-pattern y-pattern & more-patterns]
   (rx-join x-pattern (clojure/apply rx-join y-pattern more-patterns))))

(defn *
  ([subsequence-pattern]
   (* subsequence-pattern ()))
  ([subsequence-pattern rest-pattern]
   (let [subsequence-pattern (if (sequential? subsequence-pattern)
                               (clojure/apply rx-cat subsequence-pattern)
                               subsequence-pattern)]
     (->GreedyStar subsequence-pattern rest-pattern))))

(defn +
  ([subsequence-pattern]
   (+ subsequence-pattern ()))
  ([subsequence-pattern rest-pattern]
   (let [subsequence-pattern (if (sequential? subsequence-pattern)
                               (clojure/apply rx-cat subsequence-pattern)
                               subsequence-pattern)]
     (rx-join subsequence-pattern (* subsequence-pattern rest-pattern)))))


(defn *?
  ([subsequence-pattern]
   (*? subsequence-pattern ()))
  ([subsequence-pattern rest-pattern]
   (let [subsequence-pattern (if (sequential? subsequence-pattern)
                               (clojure/apply rx-cat subsequence-pattern)
                               subsequence-pattern)]
     (->FrugalStar subsequence-pattern rest-pattern))))


(defn rule [query-pattern yield-pattern]
  (->Rule query-pattern yield-pattern))

(defn dual [is-pattern is-not-pattern]
  (->DualPattern is-pattern is-not-pattern))

(defn anything []
  (->AnythingPattern))

(def _
  (anything))

(defn nothing []
  (dual (anything) (anything)))

(defn code [x]
  (->CodePattern x))

(defn data [x]
  (->DataPattern x))

(defn one
  ([] (nothing))
  ([a-pattern] a-pattern)
  ([a-pattern b-pattern]
   (->OnePattern a-pattern b-pattern))
  ([a-pattern b-pattern & more-patterns]
   (one a-pattern (clojure/apply one b-pattern more-patterns))))

(defn some
  ([] (nothing))
  ([a-pattern] a-pattern)
  ([a-pattern b-pattern]
   (->SomePattern a-pattern b-pattern))
  ([a-pattern b-pattern & more-patterns]
   (some a-pattern (clojure/apply some b-pattern more-patterns))))

(defn all
  ([] (anything))
  ([a-pattern] a-pattern)
  ([a-pattern b-pattern]
   (->AllPattern a-pattern b-pattern))
  ([a-pattern b-pattern & more-patterns]
   (->AllPattern a-pattern (clojure/apply all b-pattern more-patterns))))

(defn not [pattern]
  (dual (anything) pattern))

(defn apply
  ([function-pattern arguments-pattern]
   (apply function-pattern arguments-pattern (anything)))
  ([function-pattern arguments-pattern return-pattern]
   (let [arguments-pattern (if (sequential? arguments-pattern)
                             (clojure/apply rx-cat arguments-pattern)
                             arguments-pattern)]
     (->ApplyPattern function-pattern arguments-pattern return-pattern))))

(defn predicate
  {:style/indent 1}
  ([predicate-pattern]
   (->PredicatePattern predicate-pattern (anything)))
  ([predicate-pattern x-pattern]
   (->PredicatePattern predicate-pattern x-pattern)))

(defn project
  {:style/indent 2}
  [yield-pattern query-pattern a-pattern]
  (->ProjectPattern yield-pattern query-pattern a-pattern))

(defn variable [id fold-function unfold-function]
  (->Variable id fold-function unfold-function))

(defn logic-fold-function [environment]
  (let [call (get environment :call)
        eval (get environment :eval)
        none (get environment :none)
        test (get environment :test)
        = (eval `clojure/=)]
    (fn [old new pass fail]
      (test (call = old none)
            (fn []
              (pass new))
            (fn []
              (test (call = new old)
                    (fn []
                      (pass old))
                    (fn []
                      (fail old))))))))

(defn logic-unfold-function [environment]
  (let [call (get environment :call)
        eval (get environment :eval)
        none (get environment :none)
        test (get environment :test)
        = (eval `clojure/=)]
    (fn [old pass fail]
      (test (call = old none)
            (fn []
              (fail old))
            (fn []
              (pass old old))))))

(defn logic-variable
  ([]
   (logic-variable (gensym "?__")))
  ([id]
   (variable id logic-fold-function logic-unfold-function)))

(defn mutable-fold-function [environment]
  (fn [old new pass fail]
    (pass new)))

(defn mutable-unfold-function [environment]
  (let [call (get environment :call)
        eval (get environment :eval)
        none (get environment :none)
        test (get environment :test)
        = (eval `clojure/=)]
    (fn [old pass fail]
      (test (call = old none)
            (fn []
              (fail old))
            (fn []
              (pass old old))))))

(defn mutable-variable
  ([]
   (mutable-variable (gensym "!__")))
  ([id]
   (variable id mutable-fold-function mutable-unfold-function)))

(defn fifo-fold-function [environment]
  (let [call (get environment :call)
        eval (get environment :eval)
        none (get environment :none)
        test (get environment :test)
        = (eval `clojure/=)
        conj (eval `clojure/conj)]
    (fn [old new pass fail]
      (test (call = old none)
            (fn []
              (pass [new]))
            (fn []
              (pass (call conj old new)))))))

(defn fifo-unfold-function [environment]
  (let [call (get environment :call)
        eval (get environment :eval)
        none (get environment :none)
        test (get environment :test)
        = (eval `clojure/=)
        nth (eval `clojure/nth)
        seq (eval `clojure/seq)
        subvec (eval `clojure/subvec)]
    (fn [old pass fail]
      (test (call = old none)
            (fn []
              (fail old))
            (fn []
              (test (call seq old)
                    (fn []
                      (pass (call nth old 0) (call subvec old 1)))
                    (fn []
                      (fail none))))))))

(defn fifo-variable
  ([]
   (fifo-variable (gensym "<__")))
  ([id]
   (variable id fifo-fold-function fifo-unfold-function)))

(defn filo-fold-function [environment]
  (let [call (get environment :call)
        eval (get environment :eval)
        none (get environment :none)
        test (get environment :test)
        = (eval `clojure/=)
        cons (eval `clojure/cons)]
    (fn [old new pass fail]
      (test (call = old none)
            (fn []
              (pass (clojure/list new)))
            (fn []
              (pass (call cons new old)))))))

(defn filo-unfold-function [environment]
  (let [call (get environment :call)
        eval (get environment :eval)
        none (get environment :none)
        test (get environment :test)
        = (eval `clojure/=)
        nth (eval `clojure/nth)
        seq (eval `clojure/seq)
        rest (eval `clojure/rest)]
    (fn [old pass fail]
      (test (call = old none)
            (fn []
              (fail none))
            (fn []
              (test (call seq old)
                    (fn []
                      (pass (call nth old 0) (call rest old)))
                    (fn []
                      (fail none))))))))

(defn filo-variable
  ([]
   (filo-variable (gensym ">__")))
  ([id]
   (variable id filo-fold-function filo-unfold-function)))

(defn reference
  ([] (->Reference (gensym "%__")))
  ([id] (->Reference (gensym "%__"))))

(defn with [mapping pattern]
  (->With mapping pattern))

(defn query-proxy [f]
  (fn [this environment]
    (query-function (f environment) environment)))

(defn yield-proxy [f]
  (fn [this environment]
    (yield-function (f environment) environment)))

(defn ifn-code [x]
  (annotate (code x) `clojure/ifn?))

(def %fn-keyword
  (ifn-code `clojure/keyword))

(def %fn-keyword?
  (ifn-code `clojure/keyword?))

(def %fn-name
  (ifn-code `clojure/name))

(def %fn-namespace
  (ifn-code `clojure/namespace))

(def %fn-string?
  (ifn-code `clojure/string?))

(def %fn-symbol
  (ifn-code `clojure/symbol))

(def %fn-symbol?
  (ifn-code `clojure/symbol?))

(def %fn-str
  (ifn-code `clojure/str))


(defn seq [pattern]
  (with-meta {::identifier `seq
              :pattern pattern}
    {::annotations #{`clojure/seq?
                     `clojure/sequential?
                     `clojure/seqable?}
     `children
     (fn [this] [pattern])

     `query-function
     (fn [this environment]
       (if (annotated-with? pattern `clojure/seq?)
         (query-function pattern environment)
         (let [%seq? (annotate (code `clojure/seq?) `clojure/ifn?)
               annotated-pattern (annotate pattern `clojure/seq? `clojure/sequential?)]
           (query-function (predicate %seq? annotated-pattern) environment))))

     `yield-function
     (fn [this environment]
       (let [%list (annotate (code `clojure/list) `clojure/ifn?)
             %seqable? (annotate (code `clojure/seqable?) `clojure/ifn?)
             pattern (if (annotated-with? pattern `clojure/seqable?)
                       (apply %list pattern)
                       (apply %list (predicate %seqable? pattern)))]
         (yield-function pattern environment)))}))

(defn cons [x-pattern seq-pattern]
  (seq (rx-cons x-pattern seq-pattern)))

(defn list [& patterns]
  (seq (clojure/apply rx-cat patterns)))

(defn concat [& patterns]
  (seq (clojure/apply rx-join patterns)))

(defn vec [pattern]
  (with-meta {::identifier `vec
              :pattern pattern}
    {::annotations #{`clojure/vector?
                     `clojure/sequential?
                     `clojure/seqable?}
     `children
     (fn [this]
       [pattern])

     `query-function
     (fn [this environment]
       (if (annotated-with? pattern `clojure/vector)
         (query-function pattern environment)
         (let [%vector? (annotate (code `clojure/vector?) `clojure/fn?)]
           (query-function (predicate %vector? (annotate pattern `clojure/vector? `clojure/sequential?))
                           environment))))

     `yield-function
     (fn [this environment]
       (let [%vec (annotate (code `clojure/vec) `clojure/fn?)
             %seqable? (annotate (code `clojure/seqable?) `clojure/fn?)
             pattern (if (annotated-with? pattern `clojure/seqable?)
                       (if (annotated-with? pattern `clojure/vector?)
                         pattern
                         (apply %vec [pattern]))
                       (apply %vec [(predicate %seqable? pattern)]))]
         (yield-function pattern environment)))}))

(defn keyword
  ([name-pattern]
   (let [%query (predicate %fn-keyword? (apply %fn-name [] name-pattern))
         %yield (apply %fn-keyword? [(predicate %fn-string? name-pattern)])]
     (with-meta {::identifier `keyword
                 :name-pattern name-pattern}
       {`children
        (fn [this]
          [name-pattern])

        `query-function
        (fn [this environment]
          (query-function %query environment))

        `yield-function
        (fn [this environment]
          (yield-function %yield environment))})))
  ([namespace-pattern name-pattern]
   (let [%query (predicate %fn-keyword?
                  (all (apply %fn-namespace [] namespace-pattern)
                       (apply %fn-name [] name-pattern)))

         %yield (apply %fn-keyword
                       (rx-cat (one (predicate %fn-string? namespace-pattern)
                                    (data nil))
                               (predicate %fn-string? name-pattern)))]
     (with-meta {::identifier `keyword
                 :namespace-pattern namespace-pattern
                 :name-pattern name-pattern}
       {`children
        (fn [this]
          [namespace-pattern name-pattern])

        `query-function
        (fn [this environment]
          (query-function %query environment))

        `yield-function
        (fn [this environment]
          (yield-function %yield environment))}))))

(defn symbol
  ([name-pattern]
   (let [%query (predicate %fn-symbol? (apply %fn-name [] name-pattern))
         %yield (apply %fn-symbol [(predicate %fn-string? name-pattern)])]
     (with-meta {::identifier `symbol
                 :name-pattern name-pattern}
       {`children
        (fn [this]
          [name-pattern])

        `query-function
        (fn [this environment]
          (query-function %query environment))

        `yield-function
        (fn [this environment]
          (yield-function %yield environment))})))
  ([namespace-pattern name-pattern]
   (let [%query (predicate %fn-symbol?
                  (all (apply %fn-namespace [] namespace-pattern)
                       (apply %fn-name [] name-pattern)))
         %yield (apply %fn-symbol
                       [(one (predicate %fn-string? namespace-pattern)
                             (data nil))
                        (predicate %fn-string?
                          name-pattern)])]
     (with-meta {::identifier `symbol
                 :namespace-pattern namespace-pattern
                 :name-pattern name-pattern}
       {`children
        (fn [this]
          [namespace-pattern name-pattern])

        `query-function
        (fn [this environment]
          (query-function %query environment))

        `yield-function
        (fn [this environment]
          (yield-function %yield environment))}))))

(defn str
  ([] "")
  ([x-pattern]
   (if (string? x-pattern)
     x-pattern
     (with-meta {::identifier `str
                 :x-pattern x-pattern
                 :y-pattern ""}
       {`query-function
        (query-proxy
         (fn [environment]
           (predicate %fn-string? x-pattern)))

        `yield-function
        (yield-proxy
         (fn [environment]
           (apply %fn-str [x-pattern])))})))
  ([x-pattern y-pattern]
   (with-meta {::identifier `str
               :x-pattern x-pattern
               :y-pattern y-pattern}
     {`query-function
      (query-proxy
       (fn [environment]
         (case [(string? x-pattern) (string? y-pattern)]
           [true true]
           (clojure/str x-pattern y-pattern)

           [true false]
           (let [eval (get environment :eval)
                 subs (eval `clojure/subs)
                 string? (eval `clojure/string?)]
             (predicate string?
                        (all (apply subs [0 (count x-pattern)] x-pattern)
                             (apply subs [(count x-pattern)] y-pattern))))

           [false true]
           (let [eval (get environment :eval)
                 - (eval `clojure/-)
                 count (eval `clojure/count)
                 string? (eval `clojure/string?)
                 subs (eval `clojure/subs)
                 ?count (logic-variable)
                 ?total (logic-variable)]
             (predicate string?
                        (all (apply count [] ?count)
                             (all (project (apply - [?count (count x-pattern)]) ?total _)
                                  (apply subs [?total] y-pattern)
                                  (apply subs [0 ?total] x-pattern)))))

           [false false]
           (let [eval (get environment :eval)
                 string? (eval `clojure/string?)
                 string-partitions (eval `m.algorithms/string-partitions)]
             (predicate string?
                        (apply string-partitions [2] (rx-join _ (rx-cat (rx-cat x-pattern y-pattern)) _)))))))

      `yield-function
      (yield-proxy
       (fn [environment]
         (let [eval (get environment :eval)
               str (eval `clojure/str)]
           (apply str (rx-cat x-pattern y-pattern)))))}))
  ([x-pattern y-pattern z-pattern]
   (str (str x-pattern y-pattern) z-pattern)))

(defn assoc
  ([map-pattern key-pattern val-pattern]
   (annotate (->AssocPattern map-pattern key-pattern val-pattern)
             `clojure/associative?
             `clojure/map?))
  ([map-pattern key-pattern val-pattern & more-key-val-patterns]
   (assert (even? (count more-key-val-patterns)) "assoc expects an odd number of arguments greater than one")
   (clojure/apply assoc (assoc map-pattern key-pattern val-pattern) more-key-val-patterns)))

(defn merge
  ([& map-patterns]
   (annotate (->MergePattern (clojure/vec map-patterns))
             `clojure/associative?
             `clojure/map?)))

(defn again [pattern]
  (->Again pattern))

;; Running queries, yields, rules, and systems
;; ---------------------------------------------------------------------

(defn initial-state [environment object bindings]
  (let [bind (get environment :bind)
        eval (get environment :eval)
        fail (get environment :fail)
        give (get environment :give)
        pass (get environment :pass)
        seed (get environment :seed)
        save (get environment :save)
        take (get environment :take)]
    (reduce
     (fn [m [v x]]
       (bind (fn [state]
               (let [fold ((:fold-function v) environment)
                     object (take state)]
                 (-> state
                     (give (eval x))
                     (save (:id v) fold pass fail)
                     (give object))))
             m))
     (pass (seed object))
     bindings)))

(defn run-query
  ([pattern environment object]
   (run-query pattern environment object {}))
  ([pattern environment object bindings]
   (let [bind (get environment :bind)
         list (get environment :list)
         pass (get environment :pass)
         query (query-function pattern environment)
         state (initial-state environment object bindings)]
     (bind (comp pass list)
           (bind query state)))))

(defn run-yield*
  ([pattern environment]
   (run-yield* pattern environment {}))
  ([pattern environment bindings]
   (let [bind (get environment :bind)
         pass (get environment :pass)
         take (get environment :take)
         yield (yield-function pattern environment)
         state (initial-state environment nil bindings)]
     (bind yield state))))

(defn run-yield
  ([pattern environment]
   (run-yield pattern environment {}))
  ([pattern environment bindings]
   (let [bind (get environment :bind)
         pass (get environment :pass)
         take (get environment :take)]
     (bind (comp pass take)
           (run-yield* pattern environment bindings)))))

(defn run-rule [rule environment object]
  (let [bind (get environment :bind)
        pass (get environment :pass)
        seed (get environment :seed)
        take (get environment :take)
        query (query-function rule environment)
        yield (yield-function rule environment)]
    (bind (comp pass take)
          (bind yield (bind query (pass (seed object)))))))

(defn one-system [rules]
  (let [?rule-id (logic-variable)
        system-id (name (gensym "SYSTEM__"))
        ?system-id (logic-variable)]
    (if (seq rules)
      (project system-id ?system-id
               (reduce one (mapv
                            (fn [rule]
                              (let [rule-id (name (gensym "RULE__"))]
                                (project rule-id ?rule-id rule)))
                            rules)))
      nothing)))

(defn run-system [system environment x]
  (let [%again (reference)
        environment (clojure/assoc environment :cata %again)
        bind (get environment :bind)
        with (get environment :with)
        pass (get environment :pass)
        seed (get environment :seed)
        take (get environment :take)]
    (let [query (query-function system environment)
          yield (yield-function system environment)
          again (fn again [state] (bind yield (query state)))]
      (bind (comp pass take)
            (with (seed x) {(:id %again) again} again)))))
