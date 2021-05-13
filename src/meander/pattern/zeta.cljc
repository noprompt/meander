(ns meander.pattern.zeta
  (:require [meander.algorithms.zeta :as m.algorithms])
  (:refer-clojure :exclude [apply some])
  #?(:clj
     (:require [clojure.core :as clojure])
     :cljs
     (:require [cljs.core :as clojure])))

(defprotocol QueryFunction
  (query-function [this kernel]))

(defprotocol YieldFunction
  (yield-function [this kernel]))

(defrecord Anything []
  QueryFunction
  (query-function [this kernel]
    (fn [pass fail state]
      (pass state)))

  YieldFunction
  (yield-function [this kernel]
    (let [give (get kernel :give)
          make (get kernel :make)]
      (fn [pass fail state]
        (give state (make state) pass)))))

(defrecord Nothing []
  QueryFunction
  (query-function [this kernel]
    (fn [pass fail state]
      (fail state)))

  YieldFunction
  (yield-function [this kernel]
    (fn [pass fail state]
      (fail state))))

(defrecord Data [value]
  QueryFunction
  (query-function [this kernel]
    (let [call (get kernel :call)
          data (get kernel :data)
          host (get kernel :eval)
          take (get kernel :take)
          test (get kernel :test)
          data-value (data value)
          host-equal (host `=)]
      (fn [pass fail state]
        (take state
          (fn [object]
            (call host-equal object data-value
              (fn [truth]
                (test truth
                  (fn [] (pass state))
                  (fn [] (fail state))))))))))

  YieldFunction
  (yield-function [this kernel]
    (let [data (get kernel :data)
          give (get kernel :give)
          data-value (data value)]
      (fn [pass fail state]
        (give state data-value pass)))))

(defrecord Host [form]
  QueryFunction
  (query-function [this kernel]
    (let [call (get kernel :call)
          data (get kernel :data)
          host (get kernel :eval)
          take (get kernel :take)
          test (get kernel :test)
          host-equal (host `clojure/=)]
      (fn [pass fail state]
        (take state
          (fn [object]
            (call host-equal object (host form)
              (fn [truth]
                (test truth
                  (fn [] (pass state))
                  (fn [] (fail state))))))))))

  YieldFunction
  (yield-function [this kernel]
    (let [give (get kernel :give)
          host (get kernel :eval)]
      (fn [pass fail state]
        (give state (host form) pass)))))

(defrecord Variable [symbol fold-function unfold-function]
  QueryFunction
  (query-function [this kernel]
    (let [save (get kernel :save)
          fold (fold-function kernel)]
      (fn [pass fail state]
        (save state symbol fold pass fail))))

  YieldFunction
  (yield-function [this kernel]
    (let [load (get kernel :load)
          unfold (unfold-function kernel)]
      (fn [pass fail state]
        (load state symbol unfold pass fail)))))

(defrecord Pick [pattern-a pattern-b]
  QueryFunction
  (query-function [this kernel]
    (let [query-a (query-function pattern-a kernel)
          query-b (query-function pattern-b kernel)]
      (fn [pass fail state]
        (query-a pass (fn [_] (query-b pass fail state)) state))))

  YieldFunction
  (yield-function [this kernel]
    (let [yield-a (yield-function pattern-a kernel)
          yield-b (yield-function pattern-b kernel)]
      (fn [pass fail state]
        (yield-a pass (fn [_] (yield-b pass fail state)) state)))))

(defrecord Some [pattern-a pattern-b]
  QueryFunction
  (query-function [this kernel]
    (let [query-a (query-function pattern-a kernel)
          query-b (query-function pattern-b kernel)
          join (get kernel :join)]
      (fn [pass fail state]
        (join (fn [] (query-a pass fail state))
              (fn [] (query-b pass fail state))))))

  YieldFunction
  (yield-function [this kernel]
    (let [yield-a (yield-function pattern-a kernel)
          yield-b (yield-function pattern-b kernel)
          join (get kernel :join)]
      (fn [pass fail state]
        (join (fn [] (yield-a pass fail state))
              (fn [] (yield-b pass fail state)))))))

(defrecord Each [pattern-a pattern-b]
  QueryFunction
  (query-function [this kernel]
    (let [query-a (query-function pattern-a kernel)
          query-b (query-function pattern-b kernel)]
      (fn [pass fail state]
        (query-a (fn [state] (query-b pass fail state)) fail state))))

  YieldFunction
  (yield-function [this kernel]
    (let [join (get kernel :join)
          yield-a (yield-function pattern-a kernel)
          yield-b (yield-function pattern-b kernel)
          query-a (query-function pattern-a kernel)
          query-b (query-function pattern-b kernel)]
      (fn [pass fail state]
        (join (fn [] (yield-a (fn [state] (query-b pass fail state)) fail state))
              (fn [] (yield-b (fn [state] (query-a pass fail state)) fail state)))))))

(defrecord Apply [function-pattern arguments-pattern return-pattern]
  QueryFunction
  (query-function [this kernel]
    (let [call (get kernel :call)
          host (get kernel :eval)
          pass (get kernel :pass)
          give (get kernel :give)
          take (get kernel :take)
          host-apply (host `clojure/apply)
          function-yield (yield-function function-pattern kernel)
          arguments-yield (yield-function arguments-pattern kernel)
          return-query (query-function return-pattern kernel)]
      (fn [pass fail state]
        (take state
          (fn [object]
            (function-yield
             (fn [function-state]
               (take function-state
                 (fn [function]
                   (arguments-yield
                    (fn [arguments-state]
                      (take arguments-state
                        (fn [arguments]
                          (call host-apply function object arguments
                            (fn [return-object]
                              (give state return-object
                                (fn [return-state]
                                  (return-query (fn [return-query-state]
                                                  (give return-query-state return-object pass))
                                                fail
                                                return-state))))))))
                    fail
                    state))))
             fail
             state))))))

  YieldFunction
  (yield-function [this kernel]
    (let [call (get kernel :call)
          host (get kernel :eval)
          fail (get kernel :fail)
          give (get kernel :give)
          pass (get kernel :pass)
          take (get kernel :take)
          test (get kernel :test)
          host-apply (host `clojure/apply)
          host-seqable? (host `clojure/seqable?)
          host-fn? (host `clojure/fn?)
          function-yield (yield-function function-pattern kernel)
          arguments-yield (yield-function arguments-pattern kernel)
          return-query (query-function return-pattern kernel)]
      (fn [pass fail state]
        (function-yield
         (fn [function-state]
           (take function-state
             (fn [function-object]
               (call host-fn? function-object
                 (fn [truth]
                   (test truth
                     (fn []
                       (arguments-yield
                        (fn [arguments-state]
                          (take arguments-state
                            (fn [arguments-object]
                              (call host-seqable? arguments-object
                                (fn [truth]
                                  (test truth
                                    (fn []
                                      (call host-apply function-object arguments-object
                                        (fn [return-object]
                                          (give arguments-state return-object
                                            (fn [return-state]
                                              (return-query pass fail return-state))))))
                                    (fn []
                                      (fail state))))))))
                        fail
                        state))
                     (fn []
                       (fail state))))))))
         fail
         state)))))

(defrecord Predicate [predicate-pattern x-pattern]
  QueryFunction
  (query-function [this kernel]
    (let [call (get kernel :call)
          fail (get kernel :fail)
          pass (get kernel :pass)
          take (get kernel :take)
          test (get kernel :test)
          predicate-yield (yield-function predicate-pattern kernel)
          x-query (query-function x-pattern kernel)]
      (fn [pass fail state]
        (take state
          (fn [object]
            (predicate-yield
             (fn [predicate-state]
               (take predicate-state
                 (fn [predicate-object]
                   (call predicate-object object
                     (fn [truth]
                       (test truth 
                         (fn [] (x-query pass fail state))
                         (fn [] (fail state))))))))
             fail
             state))))))

  YieldFunction
  (yield-function [this kernel]
    (let [call (get kernel :call)
          fail (get kernel :fail)
          pass (get kernel :pass)
          take (get kernel :take)
          test (get kernel :test)
          predicate-yield (yield-function predicate-pattern kernel)
          x-yield (yield-function x-pattern kernel)]
      (fn [pass fail state]
        (predicate-yield
         (fn [predicate-state]
           (take predicate-state
             (fn [predicate-object]
               (x-yield
                (fn [x-state]
                  (take x-state
                    (fn [x]
                      (call predicate-object x
                        (fn [truth]
                          (test truth 
                            (fn [] (pass x-state))
                            (fn [] (fail x-state))))))))
                fail
                predicate-state))))
         fail
         state)))))

(defrecord Project [a-pattern b-pattern c-pattern]
  QueryFunction
  (query-function [this kernel]
    (let [give (get kernel :give)
          pass (get kernel :pass)
          take (get kernel :take)
          a-yield (yield-function a-pattern kernel)
          b-query (query-function b-pattern kernel)
          c-query (query-function c-pattern kernel)]
     (fn [pass fail state]
        (take state
          (fn [object]
            (a-yield
             (fn [a-state]
               (take a-state
                 (fn [a-object]
                   (give state a-object
                     (fn [state]
                       (b-query (fn [b-state]
                                  (give b-state object
                                    (fn [state]
                                      (c-query pass fail state))))
                                fail
                                state))))))
             fail
             state))))))

  YieldFunction
  (yield-function [this kernel]
    (let [give (get kernel :give)
          take (get kernel :take)
          a-yield (yield-function a-pattern kernel)
          b-query (query-function b-pattern kernel)
          c-yield (yield-function c-pattern kernel)]
      (fn [pass fail state]
        (a-yield
         (fn [a-state]
           (take a-state
             (fn [a-object]
               (give state a-object
                 (fn [state]
                   (b-query
                    (fn [b-state]
                      (c-yield pass fail b-state))
                    fail
                    state))))))
         fail
         state)))))

;; Regular Expression Patterns
;; ---------------------------------------------------------------------

(defrecord RegexEmpty []
  QueryFunction
  (query-function [this kernel]
    (let [call (get kernel :call)
          host (get kernel :eval)
          take (get kernel :take)
          test (get kernel :test)
          host-seq (host `clojure/seq)
          host-sequential? (if (get kernel [this sequential?])
                             (host `clojure/any?)
                             (host `clojure/sequential?))]
      (fn [pass fail state]
        (take state
          (fn [object]
            (call host-sequential? object
              (fn [truth]
                (test truth
                  (fn []
                    (call host-seq object
                      (fn [truth]
                        (test truth 
                          (fn [] (fail state))
                          (fn [] (pass state))))))
                  (fn []
                    (fail state))))))))))

  YieldFunction
  (yield-function [this kernel]
    (let [data (get kernel :data)
          give (get kernel :give)
          empty-vector (data [])]
      (fn [pass fail state]
        (give state empty-vector pass)))))

(defrecord RegexCons [head-pattern tail-pattern]
  QueryFunction
  (query-function [this kernel]
    (let [call (get kernel :call)
          data (get kernel :data)
          host (get kernel :eval)
          give (get kernel :give)
          take (get kernel :take)
          test (get kernel :test)
          head-query (query-function head-pattern kernel)
          tail-query (query-function tail-pattern (assoc kernel [tail-pattern sequential?] true))
          host-nth (host `clojure/nth)
          host-tail (host `m.algorithms/tail)
          host-seq (host `clojure/seq)
          host-sequential? (if (get kernel [this sequential?])
                             (host `clojure/any?)
                             (host `clojure/sequential?))
          data-0 (data 0)]
      (fn [pass fail state]
        (take state
          (fn [object]
            (call host-sequential? object
              (fn [truth]
                (test truth
                  (fn []
                    (call host-seq object
                      (fn [truth]
                        (test truth 
                          (fn []
                            (call host-nth object data-0
                              (fn [head-object]
                                (call host-tail object
                                  (fn [tail-object]
                                    (give state head-object
                                      (fn [state]
                                        (head-query (fn [head-state]
                                                      (give head-state tail-object
                                                        (fn [state]
                                                          (tail-query pass fail state))))
                                                    fail
                                                    state))))))))
                          (fn []
                            (fail state))))))
                  (fn []
                    (fail state))))))))))


  YieldFunction
  (yield-function [this kernel]
    (let [call (get kernel :call)
          host (get kernel :eval)
          give (get kernel :give)
          take (get kernel :take)
          test (get kernel :test)
          head-yield (yield-function head-pattern kernel)
          tail-yield (yield-function tail-pattern kernel)
          host-cons (host `clojure/cons)
          host-sequential? (host `clojure/sequential?)]
      (fn [pass fail state]
        (head-yield
         (fn [head-state]
           (take head-state
             (fn [head-object]
               (tail-yield
                (fn [tail-state]
                  (take tail-state
                    (fn [tail-object]
                      (call host-sequential? tail-object
                        (fn [truth]
                          (test truth
                            (fn []
                              (call host-cons head-object tail-object
                                (fn [sequential-object]
                                  (give tail-state sequential-object pass))))
                            (fn []
                              (fail tail-state))))))))
                fail
                head-state))))
         fail
         state)))))

(defrecord RegexConcatenation [initial-patterns tail-pattern]
  QueryFunction
  (query-function [this kernel]
    (let [bind (get kernel :bind)
          call (get kernel :call)
          data (get kernel :data)
          host (get kernel :eval)
          give (get kernel :give)
          take (get kernel :take)
          test (get kernel :test)
          initial-queries (map (fn [pattern]
                                 (query-function pattern kernel))
                               initial-patterns)
          indexed-queries (map-indexed (fn [index query]
                                         [(data index) query])
                                       initial-queries)
          tail-query (query-function tail-pattern (assoc kernel [tail-pattern clojure/sequential?] true))
          n* (count initial-patterns)
          m* (inc n*)
          n (data n*)
          m (data m*)
          <= (host `clojure/<=)
          nth (host `clojure/nth)
          bounded-count (host `clojure/bounded-count)
          drop (host `m.algorithms/drop)
          sequential? (if (get kernel [this clojure/sequential?])
                        (host `clojure/any?) 
                        (host `clojure/sequential?))]
      (fn [pass fail state]
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
                                    (reduce
                                     (fn [ma [index query]]
                                       (call nth object index
                                         (fn [x]
                                           (bind (fn [state]
                                                   (give state x
                                                     (fn [state]
                                                       (query pass fail state))))
                                                 ma))))
                                     (pass state)
                                     indexed-queries))))
                              (fn []
                                (fail state))))))))
                  (fn []
                    (fail state))))))))))

  YieldFunction
  (yield-function [this kernel]
    (let [bind (get kernel :bind)
          call (get kernel :call)
          host (get kernel :eval)
          give (get kernel :give)
          take (get kernel :take)
          n (count initial-patterns)
          initial-yields (map (fn [pattern]
                                (yield-function pattern kernel))
                              initial-patterns)
          tail-yield (yield-function tail-pattern kernel)
          conj (host `clojure/conj)
          into (host `clojure/into)
          vec (host `clojure/vec)
          vector (host `clojure/vector)]
      (case n
        0
        tail-yield

        1
        (let [yield (nth initial-yields 0)]
          (fn [pass fail state]
            (yield (fn [state]
                     (take state
                       (fn [object]
                         (call vector object
                           (fn [xs]
                             (tail-yield
                              (fn [state]
                                (take state
                                  (fn [tail]
                                    (call into xs tail
                                      (fn [ys]
                                        (give state ys pass))))))
                              fail
                              state))))))
                   fail
                   state)))

        ;; else
        (let [first-yield (nth initial-yields 0)
              rest-yields (rest initial-yields)]
          (fn [pass fail state]
            (bind (fn [state]
                    (take state
                      (fn [object]
                        (call vec object
                          (fn [xs]
                            (tail-yield
                             (fn [state]
                               (take state
                                 (fn [tail]
                                   (call into xs tail
                                     (fn [ys]
                                       (give state ys pass))))))
                             fail
                             state))))))
                  (reduce (fn [m yield]
                            (bind (fn [state]
                                    (take state
                                      (fn [xs]
                                        (yield (fn [x-state]
                                                 (take x-state
                                                   (fn [x]
                                                     (call conj xs x
                                                       (fn [ys]
                                                         (give x-state ys pass))))))
                                               fail
                                               state)
                                        #_
                                        (bind (fn [x-state]
                                                (take x-state
                                                  (fn [x]
                                                    (call conj xs x
                                                      (fn [ys]
                                                        (give x-state ys pass))))))
                                              (yield state)))))
                                  m))
                          (first-yield
                           (fn [state]
                             (take state
                               (fn [x]
                                 (call vector x
                                   (fn [xs]
                                     (give state xs pass))))))
                           fail
                           state)
                          rest-yields))))))))

;; Atomic patterns
;; ---------------------------------------------------------------------

(defn anything
  "Pattern which represents any object in the universe."
  []
  (->Anything))

(defn nothing
  "Pattern which represents no object in the universe."
  []
  (->Nothing))

(defn data
  "Pattern which represents value."
  [value]
  (->Data value))

(defn host
  "Pattern which represents the value of form as evaluated by the host
  language."
  [form]
  (->Host form))

;; Variable
;; --------

(defn logic-fold-function [kernel]
  (let [call (get kernel :call)
        host (get kernel :eval)
        none (get kernel :none)
        test (get kernel :test)
        host-equals (host `clojure/=)]
    (fn [old new pass fail]
      (call host-equals old none
        (fn [truth]
          (test truth
            (fn [] (pass new))
            (fn []
              (call host-equals new old
                (fn [truth]
                  (test truth
                    (fn [] (pass new))
                    (fn [] (fail old))))))))))))

(defn logic-unfold-function [kernel]
  (let [call (get kernel :call)
        host (get kernel :eval)
        none (get kernel :none)
        test (get kernel :test)
        host-equals (host `clojure/=)]
    (fn [old pass fail]
      (call host-equals old none
        (fn [truth]
          (test truth
            (fn [] (fail old))
            (fn [] (pass old old))))))))

(defn logic-variable
  ([]
   (->Variable (gensym "?__") logic-fold-function logic-unfold-function))
  ([symbol]
   {:pre [(symbol? symbol)]}
   (->Variable symbol logic-fold-function logic-unfold-function)))

;; Compound patterns
;; ---------------------------------------------------------------------

(defn pick
  "Pattern which represents either pattern-a or pattern-b but not both."
  [pattern-a pattern-b]
  (->Pick pattern-a pattern-b))

(defn some
  "Pattern which represents either pattern-a, pattern-b, or both."
  [pattern-a pattern-b]
  (->Some pattern-a pattern-b))

(defn each
  "Pattern which represents each of pattern-a and pattern-b."
  [pattern-a pattern-b]
  (->Each pattern-a pattern-b))

(defn project
  [yield-pattern query-pattern object-pattern]
  (->Project yield-pattern query-pattern object-pattern))

(defn apply
  [function-pattern arguments-pattern return-pattern]
  (->Apply function-pattern arguments-pattern return-pattern))

(defn predicate
  [function-pattern object-pattern]
  (->Predicate function-pattern object-pattern))

;; Regular Expression Pattern Construction API
;; ---------------------------------------------------------------------

(defn regex-empty []
  (->RegexEmpty))

(defn regex-cons [head-pattern tail-pattern]
  (->RegexCons head-pattern tail-pattern))

(defn regex-concatenation [[:as initial-patterns] tail-pattern]
  (->RegexConcatenation initial-patterns tail-pattern))


;; API
;; ---------------------------------------------------------------------

(defn run-query [pattern kernel object]
  (let [query (query-function pattern kernel)
        data (get kernel :data)
        fail (get kernel :fail)
        pass (get kernel :pass)
        seed (get kernel :seed)]
    (query pass fail (seed (data object)))))

(defn run-yield [pattern kernel]
  (let [yield (yield-function pattern kernel)
        data (get kernel :data)
        fail (get kernel :fail)
        pass (get kernel :pass)
        seed (get kernel :seed)]
    (yield pass fail (seed (data nil)))))
