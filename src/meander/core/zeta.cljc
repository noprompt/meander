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

(defprotocol ISimplify
  :extend-via-metadata true
  (simplify [this context]))

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

(defrecord ConstantPattern [x]
  IQueryFunction
  (query-function [this environment]
    (let [call (get environment :call)
          code (get environment :code)
          eval (get environment :eval)
          fail (get environment :fail)
          pass (get environment :pass)
          take (get environment :take)
          test (get environment :test)
          = (eval `clojure/=)]
      (fn [state]
        (let [target (take state)]
          (test (call = target x)
                (fn [] (pass state))
                (fn [] (fail state)))))))

  IYieldFunction
  (yield-function [this environment]
    (let [pass (get environment :pass)
          give (get environment :give)]
      (fn [state]
        (pass (give state x))))))

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
          coll? (eval `clojure/coll?)
          nil? (eval `clojure/nil?)
          fn? (eval `clojure/fn?)
          function-yield (yield-function function-pattern environment)
          arguments-yield (yield-function arguments-pattern environment)
          return-query (query-function return-pattern environment)
          arguments-then (memoize
                          (fn [f arguments state]
                            (fn []
                              (let [object (call apply f arguments)
                                    object-state (give state object)]
                                (bind (fn [return-state]
                                        (pass object-state))
                                      (return-query object-state))))))]
      (fn [state]
        (bind (fn [function-state]
                (let [f (take function-state)]
                  (test (call fn? f)
                        (fn []
                          (bind (fn [arguments-state]
                                  (let [arguments (take arguments-state)]
                                    (test (call coll? arguments)
                                          (arguments-then f arguments arguments-state)
                                          (fn []
                                            (test (call nil? arguments)
                                                  (arguments-then f arguments arguments-state)
                                                  (fn []
                                                    (fail arguments-state)))))))
                                (arguments-yield function-state)))
                        (fn []
                          (fail function-state)))))
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
      (fn [state]
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
      (fn [state]
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
        (save state id fold (take state) pass fail))))

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
          zero (eval 0)]
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
                  (fail state)))))))

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
          sequential? (eval `clojure/sequential?)]
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
                  (fail state)))))))

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
          conj (eval `clojure/conj)]
      (case n
        0
        (fn [state]
          (pass (give state [])))

        1
        (let [yield (nth yields 0)]
          (fn [state]
            (bind (fn [state]
                    (pass (give state [(take state)])))
                  (yield state))))

        ;; else
        (let [first-yield (nth yields 0)
              rest-yields (rest yields)]
          (fn [state]
            (reduce (fn [m yield]
                      (bind (fn [state]
                              (let [xs (take state)]
                                (bind (fn [state ]
                                        (pass (give state (call conj xs (take state)))))
                                      (yield state))))
                            m))
                    (bind (fn [state]
                            (pass (give state [(take state)])))
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
          x-query (query-function x-pattern environment)
          y-query (query-function y-pattern environment)
          nth (eval `clojure/nth)
          sequential? (eval `clojure/sequential?)
          partitions (eval `meander.algorithms.zeta/partitions)
          zero (eval 0)
          one (eval 1)
          two (eval 2)]
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
                  (fail state)))))))

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

(defrecord FrugalStar [pattern rest-pattern]
  IChildren
  (children [this]
    [pattern rest-pattern])

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
          pattern-query (query-function pattern environment)
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
                  (star (fn [rec object state]
                          (test (call seq object)
                                (fn []
                                  (join (rest-query (give state object))
                                        (scan (fn [x]
                                                (let [a (call nth x zero)
                                                      b (call nth x one)]
                                                  (bind (fn [a-state]
                                                          (call rec b a-state))
                                                        (pattern-query (give state a)))))
                                              (call rest (call partitions two object)))))
                                (fn []
                                  (pass state))))
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
          join (get environment :join)
          pass (get environment :pass)
          pick (get environment :pick)
          star (get environment :star)
          take (get environment :take)
          test (get environment :test)
          pattern-yield (yield-function pattern environment)
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
                            (pattern-yield state)))
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
          key (eval `clojure/key)
          map? (eval `clojure/map?)
          seq (eval `clojure/seq)
          val (eval `clojure/val)]
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
          map? (eval `clojure/map?)]
      (fn [state]
        (bind (fn [map-state]
                (let [m (take map-state)]
                  (test (call map? m)
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
          n (eval (count queries))]
      (if (zero? n)
        (fn [state]
          (let [object (take state)]
            (test (call map? object)
                  (fn [] (pass state))
                  (fn [] (fail state)))))
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
                    (fail state))))))))

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
          yields (map yield-function map-patterns (repeat environment))
          map? (eval `clojure/map?)
          merge (eval `clojure/merge)]
      (fn [state]
        (reduce
         (fn [ma yield]
           (bind (fn [state]
                   (let [m1 (take state)]
                     (bind (fn [yield-state]
                             (let [m2 (take yield-state)]
                               (test (call map? m2)
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
    (query-function (->ConstantPattern this) environment))

  IYieldFunction
  (yield-function [this environment]
    (yield-function (->ConstantPattern this) environment)))

(extend-type #?(:clj Object :cljs default)
  IChildren
  (children [this]
    [])

  IQueryFunction
  (query-function [this environment]
    (query-function (->ConstantPattern this) environment))

  IYieldFunction
  (yield-function [this environment]
    (yield-function (->ConstantPattern this) environment)))

;; API
;; ---------------------------------------------------------------------

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

(defn constant [x]
  (->ConstantPattern x))

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
                             (->RegexConcatenation arguments-pattern)
                             arguments-pattern)]
     (->ApplyPattern function-pattern arguments-pattern return-pattern))))

(defn predicate ([predicate-pattern]
   (->PredicatePattern predicate-pattern (anything)))
  ([predicate-pattern x-pattern]
   (->PredicatePattern predicate-pattern x-pattern)))

(defn project
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

(defn mutable-variable [id]
  (variable id mutable-fold-function mutable-unfold-function))

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

(defn rx-cons? [x]
  (instance? RegexCons x))

(defn rx-cat? [x]
  (instance? RegexConcatenation x))

(defn rx-cons [x-pattern rx-pattern]
  (->RegexCons x-pattern rx-pattern))

(defn rx-cat [& patterns]
  (->RegexConcatenation (into [] patterns)))

(defn rx-join
  ([]
   (rx-join () ()))
  ([x-pattern]
   (rx-join x-pattern ()))
  ([x-pattern y-pattern]
   (->RegexJoin x-pattern y-pattern))
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


(defn *? [subsequence-pattern rest-pattern]
  (let [subsequence-pattern (if (sequential? subsequence-pattern)
                              (clojure/apply rx-cat subsequence-pattern)
                              subsequence-pattern)]
    (->FrugalStar subsequence-pattern rest-pattern)))

(defn query-proxy [f]
  (fn [this environment]
    (query-function (f environment) environment)))

(defn yield-proxy [f]
  (fn [this environment]
    (yield-function (f environment) environment)))

(defn seq [pattern]
  (with-meta {:seq-pattern pattern}
    {`query-function
     (query-proxy
      (fn [environment]
        (let [eval (get environment :eval)
              seq? (eval `clojure/seq?)]
          (predicate seq? pattern))))

     `yield-function
     (yield-proxy
      (fn [environment]
        (let [eval (get environment :eval)
              list (eval `clojure/list)
              coll? (eval `clojure/coll?)
              nil? (eval `clojure/nil?)
              ?x (logic-variable)]
          (project pattern
                   (some (predicate coll? ?x)
                         (predicate nil? ?x))
                   (apply list ?x)))))}))


(defn cons [x-pattern seq-pattern]
  (seq (rx-cons x-pattern seq-pattern)))

(defn list [& patterns]
  (seq (clojure/apply rx-cat patterns)))

(defn concat [& patterns]
  (seq (clojure/apply rx-join patterns)))

(defn vec [pattern]
  (with-meta {:vec-pattern pattern}
    {`query-function
     (query-proxy
      (fn [environment]
        (let [eval (get environment :eval)
              vector? (eval `clojure/vector?)]
          (predicate vector? pattern))))

     `yield-function
     (yield-proxy
      (fn [environment]
        (let [eval (get environment :eval)
              vec (eval `clojure/vec)
              coll? (eval `clojure/coll?)
              nil? (eval `clojure/nil?)]
          (apply vec
                 [(some (predicate coll? pattern)
                        (predicate nil? pattern))]))))}))

(defn keyword
  ([name-pattern]
   (with-meta {:name-pattern name-pattern}
     {`query-function
      (query-proxy
       (fn [environment]
         (let [eval (get environment :eval)
               keyword? (eval `clojure/keyword?)
               name (eval `clojure/name)]
           (predicate keyword? (apply name [] name-pattern)))))

      `yield-function
      (yield-proxy
       (fn [environment]
         (let [eval (get environment :eval)
               keyword (eval `clojure/keyword)
               string? (eval `clojure/string?)]
           (apply keyword? (rx-cat (predicate string? name-pattern))))))}))
  ([namespace-pattern name-pattern]
   (with-meta {:namespace-pattern namespace-pattern
               :name-pattern name-pattern}
     {`query-function
      (query-proxy
       (fn [environment]
         (let [eval (get environment :eval)
               keyword? (eval `clojure/keyword?)
               name (eval `clojure/name)
               namespace (eval `clojure/namespace)]
           (predicate keyword? (all (apply namespace [] namespace-pattern)
                                    (apply name [] name-pattern))))))
      `yield-function
      (yield-proxy
       (fn [environment]
         (let [eval (get environment :eval)
               keyword (eval `clojure/keyword)
               nil? (eval `clojure/nil?)
               string? (eval `clojure/string?)]
           (apply keyword
                  (rx-cat (one (predicate string? namespace-pattern) nil)
                          (predicate string? name-pattern))))))})))

(defn symbol
  ([name-pattern]
   (with-meta {:name-pattern name-pattern}
     {`query-function
      (query-proxy
       (fn [environment]
         (let [eval (get environment :eval)
               symbol? (eval `clojure/symbol?)
               name (eval `clojure/name)]
           (predicate symbol? (apply name [] name-pattern)))))

      `yield-function
      (yield-proxy
       (fn [environment]
         (let [eval (get environment :eval)
               symbol (eval `clojure/symbol)
               string? (eval `clojure/string?)]
           (apply symbol? (rx-cat (predicate string? name-pattern))))))}))
  ([namespace-pattern name-pattern]
   (with-meta {:namespace-pattern namespace-pattern
               :name-pattern name-pattern}
     {`query-function
      (query-proxy
       (fn [environment]
         (let [eval (get environment :eval)
               symbol? (eval `clojure/symbol?)
               name (eval `clojure/name)
               namespace (eval `clojure/namespace)]
           (predicate symbol? (all (apply namespace [] namespace-pattern)
                                   (apply name [] name-pattern))))))
      `yield-function
      (yield-proxy
       (fn [environment]
         (let [eval (get environment :eval)
               symbol (eval `clojure/symbol)
               nil? (eval `clojure/nil?)
               string? (eval `clojure/string?)]
           (apply symbol
                  (rx-cat (one (predicate string? namespace-pattern) nil)
                          (predicate string? name-pattern))))))})))
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
           (let [eval (get environment :eval)
                 string? (eval `clojure/string?)]
             (predicate string? x-pattern))))
        `yield-function
        (yield-proxy
         (fn [environment]
           (let [eval (get environment :eval)
                 str (eval `clojure/str)]
             (apply str (rx-cat x-pattern)))))})))
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
   (->AssocPattern map-pattern key-pattern val-pattern))
  ([map-pattern key-pattern val-pattern & more-key-val-patterns]
   (assert (even? (count more-key-val-patterns)) "assoc expects an odd number of arguments greater than one")
   (clojure/apply assoc (assoc map-pattern key-pattern val-pattern) more-key-val-patterns)))

(defn merge
  ([& map-patterns]
   (->MergePattern (clojure/vec map-patterns))))

(defn again [pattern]
  (->Again pattern))

;; Running queries, yields, rules, and systems
;; ---------------------------------------------------------------------

(defn initial-state [environment object bindings]
  (let [bind (get environment :bind)
        fail (get environment :fail)
        pass (get environment :pass)
        seed (get environment :seed)
        save (get environment :save)]
    (reduce-kv
     (fn [m v x]
       (bind (fn [state]
               (let [fold ((:fold-function v) environment)]
                 (save state (:id v) fold x pass fail)))
             m))
     (pass (seed object))
     bindings)))

(defn run-query
  ([pattern environment object]
   (run-query pattern environment object {}))
  ([pattern environment object bindings]
   (let [bind (get environment :bind)
         query (query-function pattern environment)]
     (bind query (initial-state environment object bindings)))))

(defn run-yield
  ([pattern environment]
   (run-yield pattern environment {}))
  ([pattern environment bindings]
   (let [bind (get environment :bind)
         pass (get environment :pass)
         take (get environment :take)
         yield (yield-function pattern environment)]
     (bind (comp pass take)
           (bind yield (initial-state environment nil bindings))))))

(defn run-rule [rule environment object]
  (let [bind (get environment :bind)
        pass (get environment :pass)
        seed (get environment :seed)
        take (get environment :take)
        query (query-function rule environment)
        yield (yield-function rule environment)]
    (bind (comp pass take)
          (bind yield (query (seed object))))))

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
