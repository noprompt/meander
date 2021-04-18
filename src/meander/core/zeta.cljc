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
            [meander.algorithms.zeta :as m.algorithms]
            [meander.util.zeta :as m.util]))

(defprotocol IQueryFunction
  :extend-via-metadata true
  (query-function [this environment]))

(defprotocol IYieldFunction
  :extend-via-metadata true
  (yield-function [this environment]))

(defprotocol IChildren
  :extend-via-metadata true
  (children [this]))

(defprotocol IClassifier
  :extend-via-metadata true
  (classifier [this]))

(defprotocol IGround
  :extend-via-metadata true
  (ground? [this environment]))

(defprotocol IMaxLength
  :extend-via-metadata true
  (max-length [this unknown]))

(defprotocol IMinLength
  :extend-via-metadata true
  (min-length [this unknown]))

(defprotocol IRegexCons
  (regex-cons [this pattern]))

(extend-type #?(:clj Object, :cljs default)
  IChildren
  (children [this] [])

  IClassifier
  (classifier [this] this)

  IGround
  (ground? [this] false)

  IMaxLength
  (max-length [this unknown]
    unknown)

  IMinLength
  (min-length [this unknown]
    unknown))

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

(defn classify [patterns]
  (let [order (reduce
               (fn [m [i pattern]]
                 (if (get m pattern)
                   m
                   (clojure/assoc m pattern i)))
               {}
               (map-indexed clojure/vector patterns))]
    (sort-by
     (fn [[k v]]
       (reduce min 0 (map order v)))
     (group-by classifier patterns))))

(defn sort-by-classification [patterns]
  (mapcat (fn [[classifier patterns]] patterns) (classify patterns)))

;; Primitive Patterns
;; ---------------------------------------------------------------------

(defrecord AnythingPattern []
  IClassifier
  (classifier [this]
    (class this))

  IGround
  (ground? [this environment]
    false)

  IQueryFunction
  (query-function [this environment]
    (get environment :pass))

  IYieldFunction
  (yield-function [this environment]
    (let [pass (get environment :pass)
          give (get environment :give)
          make (get environment :make)]
      (fn [state]
        (give state (make state) pass)))))

(defrecord CodePattern [x]
  IClassifier
  (classifier [this]
    this)

  IGround
  (ground? [this environment]
    true)

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
        (take state
          (fn [object]
            (call = object y
              (fn [truth]
                (test truth
                  (fn [] (pass state))
                  (fn [] (fail state))))))))))

  IYieldFunction
  (yield-function [this environment]
    (let [eval (get environment :eval)
          pass (get environment :pass)
          give (get environment :give)
          y (eval x)]
      (fn [state]
        (give state y pass)))))


(defrecord DataPattern [x]
  IClassifier
  (classifier [this]
    this)

  IGround
  (ground? [this environment]
    true)

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
        (take state
          (fn [target]
            (call = target y
              (fn [truth]
                (test truth
                  (fn [] (pass state))
                  (fn [] (fail state))))))))))

  IYieldFunction
  (yield-function [this environment]
    (let [data (get environment :data)
          pass (get environment :pass)
          give (get environment :give)
          y (data x)]
      (fn [state]
        (give state y pass)))))


(defrecord DualPattern [is-pattern is-not-pattern]
  IChildren
  (children [this]
    [is-pattern is-not-pattern])

  IClassifier
  (classifier [this]
    this)

  IGround
  (ground? [this environment]
    false)

  IQueryFunction
  (query-function [this environment]
    (let [dual (get environment :dual)
          is-query (query-function is-pattern environment)
          is-not-query (query-function is-not-pattern environment)]
      (fn [state]
        (dual (is-query state)
              (is-not-query state)))))

  IYieldFunction
  (yield-function [this environment]
    (let [bind (get environment :bind)
          dual (get environment :dual)
          give (get environment :give)
          pass (get environment :pass)
          take (get environment :take)
          is-yield (yield-function is-pattern environment)
          is-not-query (query-function is-not-pattern environment)]
      (fn [state]
        (bind (fn [yield-state]
                (take yield-state
                      (fn [object]
                        (dual (pass yield-state)
                              (give state object is-not-query)))))
              (is-yield state))))))

(defrecord OnePattern [pattern-a pattern-b]
  IChildren
  (children [this]
    [pattern-a pattern-b])

  IClassifier
  (classifier [this]
    (let [c (class this)]
      (tree-seq (fn [x] (instance? c x)) children this)))

  IGround
  (ground? [this environment]
    false)

  IQueryFunction
  (query-function [this environment]
    (let [pick (get environment :pick)
          query-a (query-function pattern-a environment)
          query-b (query-function pattern-b environment)]
      (fn [state]
        (pick (fn [] (query-a state))
              (fn [] (query-b state))))))

  IYieldFunction
  (yield-function [this environment]
    (let [pick (get environment :pick)
          yield-a (yield-function pattern-a environment)
          yield-b (yield-function pattern-b environment)]
      (fn [state]
        (pick (fn [] (yield-a state))
              (fn [] (yield-b state)))))))

(defrecord SomePattern [pattern-a pattern-b]
  IChildren
  (children [this]
    [pattern-a pattern-b])

  IClassifier
  (classifier [this]
    (let [c (class this)]
      (tree-seq (fn [x] (instance? c x)) children this)))

  IGround
  (ground? [this environment]
    false)

  IQueryFunction
  (query-function [this environment]
    (let [join (get environment :join)
          query-a (query-function pattern-a environment)
          query-b (query-function pattern-b environment)]
      (fn [state]
        (join (fn [] (query-a state))
              (fn [] (query-b state))))))

  IYieldFunction
  (yield-function [this environment]
    (let [join (get environment :join)
          yield-a (yield-function pattern-a environment)
          yield-b (yield-function pattern-b environment)]
      (fn [state]
        (join (fn [] (yield-a state))
              (fn [] (yield-b state)))))))


(defrecord AllPattern [a-pattern b-pattern]
  IQueryFunction
  (query-function [this environment]
    (let [bind (get environment :bind)
          give (get environment :give)
          take (get environment :take)
          a-query (query-function a-pattern environment)
          b-query (query-function b-pattern environment)]
      (fn [state]
        (take state
          (fn [object]
            (bind (fn [a-state]
                    (give a-state object b-query))
                  (a-query state)))))))

  IYieldFunction
  (yield-function [this environment]
    (let [bind (get environment :bind)
          join (get environment :join)
          a-query (query-function a-pattern environment)
          b-query (query-function b-pattern environment)
          a-yield (yield-function a-pattern environment)
          b-yield (yield-function b-pattern environment)]
      (fn [state]
        (join (fn [] (bind b-query (a-yield state)))
              (fn [] (bind a-query (b-yield state))))))))

(defrecord ApplyPattern [function-pattern arguments-pattern return-pattern]
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
        (take state
              (fn [object]
                (bind (fn [function-state]
                        (bind (fn [arguments-state]
                                (take function-state
                                      (fn [function]
                                        (take arguments-state
                                              (fn [arguments]
                                                (call apply function object arguments
                                                  (fn [object]
                                                    (bind (fn [return-state]
                                                            (give return-state object pass))
                                                          (give state object return-query)))))))))
                              (arguments-yield state)))
                      (function-yield state)))))))

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
                (take function-state
                      (fn [function]
                        (call fn? function
                          (fn [truth]
                            (test truth
                              (fn []
                                (bind (fn [arguments-state]
                                        (take arguments-state
                                              (fn [arguments]
                                                (call seqable? arguments
                                                  (fn [truth]
                                                    (test truth
                                                      (fn []
                                                        (call apply function arguments
                                                          (fn [object]
                                                            (give arguments-state object
                                                                  (fn [object-state]
                                                                    (bind (fn [return-state]
                                                                            (pass object-state))
                                                                          (return-query object-state)))))))
                                                      (fn []
                                                        (fail arguments-state))))))))
                                      (arguments-yield function-state)))
                              (fn []
                                (fail function-state))))))))
              (function-yield state))))))

(defrecord PredicatePattern [predicate-pattern x-pattern]
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
        (take state
          (fn [object]
            (bind (fn [predicate-state]
                    (take predicate-state
                      (fn [predicate]
                        (call predicate object
                          (fn [truth]
                            (test truth 
                              (fn [] (x-query state))
                              (fn [] (fail state))))))))
              (predicate-yield state)))))))

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
                (take predicate-state
                  (fn [predicate]
                    (bind (fn [x-state]
                            (take x-state
                              (fn [x]
                                (call predicate x
                                  (fn [truth]
                                    (test truth 
                                      (fn [] (pass x-state))
                                      (fn [] (fail x-state))))))))
                      (x-yield predicate-state)))))
          (predicate-yield state))))))

(defrecord ProjectPattern [a-pattern b-pattern c-pattern]
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
        (take state
              (fn [object]
                (bind (fn [a-state]
                        (bind (fn [b-state]
                                (give b-state object c-query))
                              (take a-state
                                    (fn [a] (give state a b-query)))))
                      (a-yield state)))))))

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
                (take a-state
                      (fn [a]
                        (bind c-yield (give state a b-query)))))
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
        (call (find state id) state identity))))

  IYieldFunction
  (yield-function [this environment]
    (let [call (get environment :call)
          find (get environment :find)]
      (fn [state]
        (call (find state id) state identity)))))

(defrecord With [pattern-mapping pattern]
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
                (take cata-state
                      (fn [cata-object]
                        (give state cata-object pattern-query))))
              (cata-query state)))))

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
                        (take cata-state
                              (fn [x]
                                (give pattern-state x pass))))
                      (cata-yield pattern-state)))
              (pattern-yield state))))))

;; Regular Expression Patterns
;; ---------------------------------------------------------------------

(defrecord RegexEmpty []
  IMaxLength
  (max-length [this unknown]
    0)

  IMinLength
  (min-length [this unknown]
    0)
  
  IQueryFunction
  (query-function [this environment]
    (let [call (get environment :call)
          eval (get environment :eval)
          fail (get environment :fail)
          pass (get environment :pass)
          take (get environment :take)
          test (get environment :test)
          seq (eval `clojure/seq)
          sequential? (if (or (annotated-with? this `clojure/sequential?)
                              (annotated-with? this `clojure/vector?))
                        (eval `clojure/any?)
                        (eval `clojure/sequential?))]
      (fn [state]
        (take state
          (fn [object]
            (call sequential? object
              (fn [truth]
                (test truth
                  (fn []
                    (call seq object
                      (fn [truth]
                        (test truth 
                          (fn [] (fail state))
                          (fn [] (pass state))))))
                  (fn []
                    (fail state))))))))))

  IYieldFunction
  (yield-function [this environment]
    (let [data (get environment :data)
          give (get environment :give)
          pass (get environment :pass)
          empty-vector (data [])]
      (fn [state]
        (give state empty-vector pass)))))

(defrecord RegexConcatenation [initial-patterns tail-pattern]
  IClassifier
  (classifier [this]
    [(class this) (count initial-patterns)])

  IMaxLength
  (max-length [this unknown]
    (let [initial-length (count initial-patterns)
          tail-length (max-length tail-pattern unknown)]
      (if (number? tail-length)
        (clojure/+ initial-length tail-length)
        unknown)))

  IMinLength
  (min-length [this unknown]
    (let [initial-length (count initial-patterns)
          tail-length (min-length tail-pattern unknown)]
      (if (number? tail-length)
        (clojure/+ initial-length tail-length)
        unknown)))

  IQueryFunction
  (query-function [this environment]
    (let [bind (get environment :bind)
          call (get environment :call)
          data (get environment :data)
          eval (get environment :eval)
          give (get environment :give)
          fail (get environment :fail)
          pass (get environment :pass)
          take (get environment :take)
          test (get environment :test)
          initial-queries (map (fn [pattern]
                                 (query-function pattern environment))
                               initial-patterns)
          indexed-queries (map-indexed (fn [index query]
                                         [(eval index) query])
                                       initial-queries)
          tail-query (query-function (annotate tail-pattern `clojure/sequential?) environment)
          n* (count initial-patterns)
          m* (inc n*)
          n (data n*)
          m (data m*)
          <= (eval `clojure/<=)
          nth (eval `clojure/nth)
          bounded-count (eval `clojure/bounded-count)
          drop (eval `m.algorithms/drop)
          sequential? (if (or (annotated-with? this `clojure/sequential?)
                              (annotated-with? this `clojure/vector?)) 
                        (eval `clojure/any?) 
                        (eval `clojure/sequential?))]
      (fn [state]
        (take state
          (fn [object]
            (call sequential? object
              (fn [truth]
                (test truth 
                  (fn []
                    (call bounded-count m object
                      (fn [m]
                        (call <= n m
                          (fn [truth]
                            (test truth 
                              (fn []
                                (call drop n object
                                  (fn [tail]
                                    (bind (fn [state]
                                            (give state tail tail-query))
                                          (reduce
                                           (fn [ma [index query]]
                                             (call nth object index
                                               (fn [x]
                                                 (bind (fn [state]
                                                         (give state x query))
                                                       ma))))
                                           (pass state)
                                           indexed-queries)))))
                              (fn []
                                (fail state))))))))
                  (fn []
                    (fail state))))))))))

  IRegexCons
  (regex-cons [this pattern]
    (RegexConcatenation. (into [pattern] initial-patterns) tail-pattern))

  IYieldFunction
  (yield-function [this environment]
    (let [bind (get environment :bind)
          call (get environment :call)
          eval (get environment :eval)
          give (get environment :give)
          pass (get environment :pass)
          take (get environment :take)
          n (count initial-patterns)
          initial-yields (map (fn [pattern]
                                (yield-function pattern environment))
                              initial-patterns)
          tail-yield (yield-function tail-pattern environment)
          conj (eval `clojure/conj)
          into (eval `clojure/into)
          vec (eval `clojure/vec)
          vector (eval `clojure/vector)]
      (case n
        0
        tail-yield

        1
        (let [yield (nth initial-yields 0)]
          (fn [state]
            (bind (fn [state]
                    (take state
                      (fn [object]
                        (call vector object
                          (fn [xs]
                            (bind (fn [state]
                                    (take state
                                      (fn [tail]
                                        (call into xs tail
                                          (fn [ys]
                                            (give state ys pass))))))
                                  (tail-yield state)))))))
                  (yield state))))

        ;; else
        (let [first-yield (nth initial-yields 0)
              rest-yields (rest initial-yields)]
          (fn [state]
            (bind (fn [state]
                    (take state
                      (fn [object]
                        (call vec object
                          (fn [xs]
                            (bind (fn [state]
                                    (take state
                                      (fn [tail]
                                        (call into xs tail
                                          (fn [ys]
                                            (give state ys pass))))))
                                  (tail-yield state)))))))
                  (reduce (fn [m yield]
                            (bind (fn [state]
                                    (take state
                                      (fn [xs]
                                        (bind (fn [x-state]
                                                (take x-state
                                                  (fn [x]
                                                    (call conj xs x
                                                      (fn [ys]
                                                        (give x-state ys pass))))))
                                              (yield state)))))
                                  m))
                          (bind (fn [state]
                                  (take state
                                    (fn [x]
                                      (call vector x
                                        (fn [xs]
                                          (give state xs pass))))))
                                (first-yield state))
                          rest-yields))))))))


(defrecord RegexCons [head-pattern tail-pattern]
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
          tail-query (query-function (annotate-using tail-pattern this) environment)
          nth (eval `clojure/nth)
          tail (eval `m.algorithms/tail)
          seq (eval `clojure/seq)
          sequential? (if (annotated-with? this `clojure/sequential?)
                        (eval `clojure/any?)
                        (eval `clojure/sequential?))
          zero (eval 0)]
      (fn [state]
        (take state
          (fn [object]
            (call sequential? object
              (fn [truth]
                (test truth
                  (fn []
                    (call seq object
                      (fn [truth]
                        (test truth 
                          (fn []
                            (call nth object zero
                              (fn [head]
                                (call tail object
                                  (fn [rest]
                                    (bind (fn [x-state]
                                            (give x-state rest tail-query))
                                      (give state head head-query)))))))
                          (fn []
                            (fail state))))))
                  (fn []
                    (fail state))))))))))

  IRegexCons
  (regex-cons [this pattern]
    (->RegexConcatenation [pattern head-pattern] tail-pattern))

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
                (take head-state
                  (fn [head]
                    (bind (fn [tail-state]
                            (take tail-state
                              (fn [tail]
                                (call sequential? tail
                                  (fn [truth]
                                    (test truth
                                      (fn []
                                        (give tail-state (call cons head tail) pass))
                                      (fn []
                                        (fail tail-state))))))))
                      (tail-yield head-state)))))
              (head-yield state))))))

(defrecord RegexJoin [x-pattern y-pattern]
  IClassifier
  (classifier [this]
    [(class this) (classifier x-pattern)])

  IMaxLength
  (max-length [this unknown]
    #_
    (let [initial-length (count initial-patterns)
          tail-length (max-length tail-pattern unknown)]
      (if (number? tail-length)
        (+ initial-length tail-length)
        unknown)))

  IMinLength
  (min-length [this unknown]
    #_
    (let [initial-length (count initial-patterns)
          tail-length (min-length tail-pattern unknown)]
      (if (number? tail-length)
        (+ initial-length tail-length)
        unknown)))

  IQueryFunction
  (query-function [this environment]
    (let [bind (get environment :bind)
          call (get environment :call)
          data (get environment :data)
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
          zero (data 0)
          one (data 1)
          two (data 2)]

      (fn [state]
        (take state
          (fn [object]
            (call sequential? object
              (fn [truth]
                (test truth
                  (fn []
                    (call partitions two object
                      (fn [partitions]
                        (scan (fn [partition]
                                (call nth partition zero
                                  (fn [x]
                                    (call nth partition one
                                      (fn [y]
                                        (bind (fn  [x-state]
                                                (give x-state y y-query))
                                          (give state x x-query)))))))
                          partitions))))
                  (fn []
                    (fail state))))))))))

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
                (take x-state
                  (fn [x]
                    (call coll? x
                      (fn [truth]
                        (test truth
                          (fn []
                            (bind (fn [y-state]
                                    (take y-state
                                      (fn [y]
                                        (call coll? y
                                          (fn [truth]
                                            (test truth
                                              (fn []
                                                (call concat x y
                                                  (fn [xy]
                                                    (give y-state xy pass))))
                                              (fn []
                                                (fail y-state))))))))
                              (y-yield x-state)))
                          (fn []
                            (fail x-state))))))))
              (x-yield state))))))

(defrecord GreedyStar [subsequence-pattern rest-pattern]
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
          two (eval 2)]
      (fn [state]
        (take state
          (fn [object]
            (call sequential? object
              (fn [truth]
                (test truth
                  (fn []
                    (star state
                      (fn [rec state]
                        (take state
                          (fn [object]
                            (pick (fn []
                                    (call partitions two object
                                      (fn [partitions]
                                        (call rest partitions
                                          (fn [rest-partitions]
                                            (scan (fn [partition]
                                                    (call nth partition zero
                                                      (fn [a]
                                                        (call nth partition one
                                                          (fn [b]

                                                            (bind (fn [subsequence-state]
                                                                    (give subsequence-state b
                                                                      (fn [state]
                                                                        (call rec state identity))))
                                                                  (give state a subsequence-query)))))))
                                                  rest-partitions))))))
                                  (fn []
                                    (rest-query state))))))))
                  (fn []
                    (fail state))))))))))

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
        (give state ()
          (fn [initial-state]
            (star initial-state
              (fn [rec state]
                    (take state
                      (fn [a]
                        (pick (fn []
                                (bind (fn [subsequence-state]
                                        (take subsequence-state
                                          (fn [b]
                                            (call sequential? b
                                              (fn [truth]
                                                (test truth
                                                  (fn []
                                                    (call concat a b
                                                      (fn [ab]
                                                        (give subsequence-state ab
                                                          (fn [state]
                                                            (call rec state identity))))))
                                                  (fn []
                                                    (fail state))))))))
                                      (give state () subsequence-yield)))
                              (fn []
                                (bind (fn [rest-state]
                                        (take rest-state
                                          (fn [b]
                                            (call sequential? b
                                              (fn [truth]
                                                (test truth
                                                  (fn []
                                                    (call concat a b
                                                      (fn [ab]
                                                        (give rest-state ab pass))))
                                                  (fn []
                                                    (fail rest-state))))))))
                                      (rest-yield state))))))))))))))

(defrecord FrugalStar [subsequence-pattern rest-pattern]
  IQueryFunction
  (query-function [this environment]
    (let [bind (get environment :bind)
          call (get environment :call)
          data (get environment :data)
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
          zero (data 0)
          one (data 1)
          two (data 2)]
      (fn [state]
        (take state
          (fn [object]
            (call sequential? object
              (fn [truth]
                (test truth
                  (fn []
                    (star state
                      (fn [rec state]
                        (take state
                          (fn [object]
                            (call seq object
                              (fn [truth]
                                (test truth 
                                  (fn []
                                    (join
                                      (fn []
                                        (rest-query state))
                                      (fn []
                                        (call partitions two object
                                          (fn [partitions]
                                            (call rest partitions
                                              (fn [rest-partitions]
                                                (scan (fn [x]
                                                        (call nth x zero
                                                          (fn [a]
                                                            (call nth x one
                                                              (fn [b]
                                                                (bind
                                                                  (fn [a-state]
                                                                    (give a-state b
                                                                      (fn [a-state*]
                                                                        (call rec a-state*))))
                                                                  (give state a subsequence-query)))))))
                                                      rest-partitions))))))))
                                  (fn []
                                    (pass state))))))))))
                  (fn []
                    (fail state))))))))))

  IYieldFunction
  (yield-function [this environment]
    (let [bind (get environment :bind)
          call (get environment :call)
          data (get environment :data)
          eval (get environment :eval)
          fail (get environment :fail)
          give (get environment :give)
          join (get environment :join)
          pass (get environment :pass)
          star (get environment :star)
          take (get environment :take)
          test (get environment :test)
          subsequence-yield (yield-function subsequence-pattern environment)
          rest-yield (yield-function rest-pattern environment)
          concat (eval `clojure/concat)
          sequential? (eval `clojure/sequential?)
          empty-list (data ()) ]
      (fn [state]
        (join (fn []
                (bind (fn [rest-state]
                        (take rest-state
                          (fn [as]
                            (call sequential? as
                              (fn [truth]
                                (test truth
                                  (fn []
                                    (pass rest-state))
                                  (fn []
                                    (fail rest-state))))))))
                      (rest-yield state)))
              (fn [] 
                (give state empty-list
                  (fn [state]
                    (star state
                     (fn [rec state]
                       (take state
                         (fn [as]
                           (bind (fn [subsequence-state]
                                   (take subsequence-state
                                     (fn [bs]
                                       (call sequential? bs
                                         (fn [truth]
                                           (test truth
                                             (fn []
                                               (bind (fn [rest-state]
                                                       (take rest-state
                                                         (fn [rest]
                                                           (call sequential? rest
                                                             (fn [truth]
                                                               (test truth
                                                                 (fn []
                                                                   (call concat as bs
                                                                     (fn [cs]
                                                                       (call concat cs rest
                                                                         (fn [ds]
                                                                           (join
                                                                            (fn [] 
                                                                              (give rest-state ds pass))
                                                                            (fn []
                                                                              (give subsequence-state cs
                                                                                (fn [next-state]
                                                                                  (call rec next-state identity))))))))))
                                                                 (fn []
                                                                   (fail state))))))))
                                                     (rest-yield subsequence-state)))
                                             (fn []
                                               (fail state))))))))
                                 (subsequence-yield state))))))))))))))


;; Data Structure Patterns
;; -----------------------

(defrecord AssocPattern [map-pattern key-pattern val-pattern]
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
      (fn [state]
        (take state
          (fn [object]
            (call map? object
              (fn [truth]
                (test truth 
                  (fn []
                    (call seq object
                      (fn [entries]
                        (scan (fn [entry]
                                (call key entry
                                  (fn [k]
                                    (call val entry
                                      (fn [v]
                                        (call dissoc object k
                                          (fn [m]
                                            (bind (fn [key-state]
                                                    (bind (fn [val-state]
                                                            (give val-state m map-query))
                                                          (give key-state v val-query)))
                                                  (give state k key-query)))))))))
                              entries))))
                  (fn []
                    (fail state))))))))))

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
                (take map-state
                  (fn [m]
                    (call map? m
                      (fn [truth]
                        (test truth
                          (fn []
                            (bind
                             (fn [key-state]
                               (take key-state
                                 (fn [k]
                                   (bind (fn [val-state]
                                           (take val-state
                                             (fn [v]
                                               (call assoc m k v
                                                 (fn [m*]
                                                   (give val-state m* pass))))))
                                         (val-yield key-state)))))
                             (key-yield map-state)))
                          (fn []
                            (fail map-state))))))))
              (map-yield state))))))

(defrecord MergePattern [map-patterns]
  IQueryFunction
  (query-function [this environment]
    (let [bind (get environment :bind)
          call (get environment :call)
          data (get environment :data)
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
          n (data (count queries))]
      (fn [state]
        (take state
          (fn [object]
            (call map? object
              (fn [truth]
                (test truth 
                  (fn []
                    (call map-partitions object n
                      (fn [partitions]
                        (scan (fn [partition]
                                (reduce
                                 (fn [ma [index query]]
                                   (call nth partition index
                                     (fn [m]
                                       (bind (fn [state]
                                               (give state m query))
                                             ma))))
                                 (pass state)
                                 indexed-queries))
                              partitions))))
                  (fn []
                    (fail state))))))))))

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
                   (take state
                     (fn [m1]
                       (bind (fn [yield-state]
                               (take yield-state
                                 (fn [m2]
                                   (call map? m2
                                     (fn [truth]
                                       (test truth
                                         (fn []
                                           (call merge m1 m2
                                             (fn [m3]
                                               (give state m3 pass))))
                                         (fn []
                                           (fail state))))))))
                             (yield state)))))
                 ma))
         (give state {} pass)
         yields)))))


;; Other objects
;; ---------------------------------------------------------------------

(defrecord Rule [query-pattern yield-pattern]
  IQueryFunction
  (query-function [this environment]
    (query-function query-pattern environment))

  IYieldFunction
  (yield-function [this environment]
    (yield-function yield-pattern environment)))

(extend-type nil
  IQueryFunction
  (query-function [this environment]
    (query-function (->DataPattern this) environment))

  IYieldFunction
  (yield-function [this environment]
    (yield-function (->DataPattern this) environment)))

(extend-type #?(:clj Object :cljs default)
  IQueryFunction
  (query-function [this environment]
    (query-function (->DataPattern this) environment))

  IYieldFunction
  (yield-function [this environment]
    (yield-function (->DataPattern this) environment)))

;; API
;; ---------------------------------------------------------------------

(extend-type #?(:clj Object, :cljs :default)
  IRegexCons
  (regex-cons [this pattern]
    (annotate (->RegexConcatenation [pattern] this)
              `clojure/seqable?
              `clojure/sequential?)))

(defn rx-empty []
  (->RegexEmpty))

(defn rx-empty? [x]
  (instance? RegexEmpty x))

(defn rx-cons? [x]
  (instance? RegexCons x))

(defn rx-cat? [x]
  (instance? RegexConcatenation x))

(defn rx-cons [x-pattern rx-pattern]
  (regex-cons rx-pattern x-pattern))

(defn rx-cat
  ([patterns]
   {:pre (sequential? patterns)}
   (if (clojure/seq patterns)
     (rx-cat patterns (rx-empty))
     (rx-empty)))
  ([patterns tail-pattern]
   {:pre (sequential? patterns)}
   (->RegexConcatenation (clojure/vec patterns) tail-pattern)))

(defn rx-join
  ([]
   (rx-empty))
  ([x-pattern]
   (rx-join x-pattern (rx-empty)))
  ([x-pattern y-pattern]
   (->RegexJoin x-pattern y-pattern))
  ([x-pattern y-pattern & more-patterns]
   (rx-join x-pattern (clojure/apply rx-join y-pattern more-patterns))))

(defn *
  ([subsequence-pattern]
   (* subsequence-pattern (rx-empty)))
  ([subsequence-pattern rest-pattern]
   (if (sequential? subsequence-pattern)
     (if (clojure/seq subsequence-pattern)
       (->GreedyStar (rx-cat subsequence-pattern) rest-pattern)
       (rx-empty))
     (->GreedyStar subsequence-pattern rest-pattern))))

(defn +
  ([subsequence-pattern]
   (+ subsequence-pattern ()))
  ([subsequence-pattern rest-pattern]
   (let [subsequence-pattern (if (sequential? subsequence-pattern)
                               (rx-cat subsequence-pattern)
                               subsequence-pattern)]
     (rx-join subsequence-pattern (* subsequence-pattern rest-pattern)))))


(defn *?
  ([subsequence-pattern]
   (*? subsequence-pattern ()))
  ([subsequence-pattern rest-pattern]
   (let [subsequence-pattern (if (sequential? subsequence-pattern)
                               (rx-cat subsequence-pattern)
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
   (one a-pattern(clojure/apply one b-pattern more-patterns))))

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
                             (rx-cat arguments-pattern)
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
      (call = old none
        (fn [truth]
          (test truth
            (fn []
              (pass new))
            (fn []
              (call = new old
                (fn [truth]
                  (test truth
                    (fn []
                      (pass old))
                    (fn []
                      (fail old))))))))))))

(defn logic-unfold-function [environment]
  (let [call (get environment :call)
        eval (get environment :eval)
        none (get environment :none)
        test (get environment :test)
        = (eval `clojure/=)]
    (fn [old pass fail]
      (call = old none
        (fn [truth]
          (test truth
            (fn [] (fail old))
            (fn [] (pass old old))))))))

(defn logic-variable
  ([] (logic-variable (gensym "?__")))
  ([id] (variable id logic-fold-function logic-unfold-function)))

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
      (call = old none
        (fn [truth]
          (test truth
            (fn [] (fail old))
            (fn [] (pass old old))))))))

(defn mutable-variable
  ([] (mutable-variable (gensym "!__")))
  ([id] (variable id mutable-fold-function mutable-unfold-function)))

(defn fifo-fold-function [environment]
  (let [call (get environment :call)
        eval (get environment :eval)
        none (get environment :none)
        test (get environment :test)
        = (eval `clojure/=)
        vector (eval `clojure/vector)
        conj (eval `clojure/conj)]
    (fn [old new pass fail]
      (call = old none
        (fn [truth]
          (test truth
            (fn [] (call vector new pass))
            (fn [] (call conj old new pass))))))))

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
      (call = old none
        (fn [truth]
          (test truth
            (fn [] (fail old))
            (fn []
              (call seq old
                (fn [truth]
                  (test truth
                    (fn []
                      (call nth old 0
                        (fn [x]
                          (call subvec old 1
                            (fn [new] (pass x new))))))
                    (fn []
                      (fail none))))))))))))

(defn fifo-variable
  ([] (fifo-variable (gensym "<__")))
  ([id] (variable id fifo-fold-function fifo-unfold-function)))


#_
(meander.environment.data.zeta/compile-one-no-optimization
 (run-query
  (* [(fifo-variable '<x) (fifo-variable '<y)])
  meander.environment.data.zeta/environment
  'TARGET))


(defn filo-fold-function [environment]
  (let [call (get environment :call)
        eval (get environment :eval)
        none (get environment :none)
        test (get environment :test)
        = (eval `clojure/=)
        cons (eval `clojure/cons)
        list (eval `clojure/list)]
    (fn [old new pass fail]
      (call = old none
        (fn [truth]
          (test truth
            (fn [] (call list new pass))
            (fn [] (call cons new old pass))))))))

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
      (call = old none
        (fn [truth]
          (test truth
            (fn [] (fail none))
            (fn []
              (call seq old
                (fn [truth]
                  (test truth
                    (fn []
                      (call nth old 0
                        (fn [x]
                          (call rest old
                            (fn [new] (pass x new))))))
                    (fn []
                      (fail none))))))))))))

(defn filo-variable
  ([] (filo-variable (gensym ">__")))
  ([id] (variable id filo-fold-function filo-unfold-function)))

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

(def %fn--
  (ifn-code `clojure/-))

(def %fn-count
  (ifn-code `clojure/count))

(def %fn-subs
  (ifn-code `clojure/subs))

(defn seq [pattern]
  (with-meta {::identifier `seq
              :pattern pattern}
    {`query-function
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
  (seq (rx-cat patterns)))

(defn concat [& patterns]
  (seq (clojure/apply rx-join patterns)))

(defn vec [pattern]
  (with-meta {::identifier `vec
              :pattern pattern}
    {`query-function
     (fn [this environment]
       (if (annotated-with? pattern `clojure/vector?)
         (query-function pattern environment)
         (let [%vector? (annotate (code `clojure/vector?) `clojure/fn?)
               pattern (annotate pattern `clojure/vector? `clojure/sequential?)]
           (query-function (predicate %vector? pattern) environment))))

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
       {`query-function
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
                       (rx-cat [(one (predicate %fn-string? namespace-pattern)
                                     (data nil))
                                (predicate %fn-string? name-pattern)]))]
     (with-meta {::identifier `keyword
                 :namespace-pattern namespace-pattern
                 :name-pattern name-pattern}
       {`query-function
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
       {`query-function
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
       {`query-function
        (fn [this environment]
          (query-function %query environment))

        `yield-function
        (fn [this environment]
          (yield-function %yield environment))}))))

(defn str
  ([] (data ""))
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
   (let [%yield (apply %fn-str (rx-cat [x-pattern y-pattern]))]
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
             (predicate %fn-string?
               (all (apply %fn-subs (rx-cat [(data 0) (data (count x-pattern))]) x-pattern)
                    (apply %fn-subs (rx-cat [(data (count x-pattern))]) y-pattern)))

             [false true]
             (let [?count (logic-variable)
                   ?total (logic-variable)]
               (predicate %fn-string?
                 (all (apply %fn-count [] ?count)
                      (all (project (apply - [?count (count x-pattern)]) ?total _)
                           (apply %fn-subs [?total] y-pattern)
                           (apply %fn-subs [0 ?total] x-pattern)))))

             [false false]
             (let [%fn-string-partitions (code `m.algorithms/string-partitions)]
               (predicate %fn-string?
                 (apply %fn-string-partitions [2] (rx-join _ (rx-cat [(rx-cat [x-pattern y-pattern])]) _)))))))

        `yield-function
        (fn [this environment]
          (yield-function %yield environment))})))
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
     (bind query state))))

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
     (run-yield* pattern environment bindings))))

(defn run-rule [rule environment object]
  (let [bind (get environment :bind)
        pass (get environment :pass)
        seed (get environment :seed)
        take (get environment :take)
        query (query-function rule environment)
        yield (yield-function rule environment)]
    (bind yield (bind query (pass (seed object))))))

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
        mint (get environment :mint)
        with (get environment :with)
        pass (get environment :pass)
        seed (get environment :seed)
        take (get environment :take)
        query (query-function system environment)
        yield (yield-function system environment)]
    ((fn again [state]
       (bind (fn [state]
               (yield state))
             (with (mint state) {(:id %again) again} query)))
     (seed x))))

;; Local Variables:
;; eval: (put-clojure-indent 'call :defn)
;; eval: (put-clojure-indent 'test :defn)
;; eval: (put-clojure-indent 'take :defn)
;; eval: (put-clojure-indent 'give :defn)
;; eval: (put-clojure-indent 'star :defn)
;; End:
