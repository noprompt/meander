(ns meander.pattern.zeta
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
        (give state (make) pass)))))

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
  (yield-function [this environment]
    (let [call (get environment :call)
          host (get environment :eval)
          fail (get environment :fail)
          give (get environment :give)
          pass (get environment :pass)
          take (get environment :take)
          test (get environment :test)
          host-apply (host `clojure/apply)
          host-seqable? (host `clojure/seqable?)
          host-fn? (host `clojure/fn?)
          function-yield (yield-function function-pattern environment)
          arguments-yield (yield-function arguments-pattern environment)
          return-query (query-function return-pattern environment)]
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
            (fn [] (pass old))))))))

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

(defn run-query [pattern kernel object]
  (let [query (query-function pattern kernel)
        data (get kernel :data)
        fail (get kernel :fail)
        pass (get kernel :pass)
        seed (get kernel :seed)]
    (query pass fail (seed (data object)))))

(defn run-yield [pattern kernel]
  (let [yield (yield-function pattern kernel)
        fail (get kernel :fail)
        pass (get kernel :pass)
        seed (get kernel :seed)]
    (yield pass fail (seed (data nil)))))
