(ns meander.runtime.tree.one.zeta
  {:no-doc true}
  (:require [meander.peval.zeta :as m.peval]
            [meander.util.zeta :as m.util]
            [meander.tree.zeta :as m.tree
             #?@(:cljs [:refer
                        [Arguments
                         Bind
                         Bindings
                         Code
                         Call
                         Data
                         Fail
                         GetBinding
                         GetBindings
                         GetObject
                         Identifier
                         Join
                         Let
                         Pass
                         Pick
                         Scan
                         SetBinding
                         SetObject
                         State
                         Star
                         Test]])])
  #?(:clj
     (:import (meander.tree.zeta Arguments
                                 Bind
                                 Bindings
                                 Code
                                 Call
                                 Data
                                 Fail
                                 GetBinding
                                 GetBindings
                                 GetObject
                                 Identifier
                                 Join
                                 Let
                                 Pass
                                 Pick
                                 Scan
                                 SetBinding
                                 SetObject
                                 State
                                 Star
                                 Test))))
;; Code generation
;; ---------------------------------------------------------------------

(defprotocol IClojure
  (clojure [tree]))

(defn flat-let*
  {:style/indent 2
   :private true}
  [binding expression body]
  (if (and (seq? body) (= (first body) 'let*))
    `(let* [~binding ~expression ~@(nth body 1)]
       ~@(drop 2 body))
    `(let* [~binding ~expression] ~body)))

(defn do-let*
  {:style/indent 1
   :private true}
  [expression f]
  (if (symbol? expression)
    (f expression)
    (let [x__ (gensym "x__")]
      `(let* [~x__ ~expression]
         ~(f x__)))))

(extend-protocol IClojure
  Arguments
  (clojure [this]
    (mapv clojure (.-arguments this)))

  Bind
  (clojure [this]
    (let [identifier (clojure (.-identifier this))
          expression (clojure (.-expression this))
          body (clojure (.-body this))]
      `(let* [~identifier ~expression]
         (if ~identifier ~body))))

  Bindings
  (clojure [this]
    (into {} (map (fn [[k-node v-node]]
                    (let [k (clojure k-node)
                          v (clojure v-node)]
                      [`(quote ~k) v])))
          (.-bindings this)))

  Call
  (clojure [this]
    (let [f (clojure (.-f this))
          arguments (clojure (.-arguments this))]
      `(~f ~@arguments)))

  Code
  (clojure [this]
    (.-code this))

  Data
  (clojure [this]
    (.-data this))

  Fail
  (clojure [this]
    nil)

  GetBinding
  (clojure [this]
    `(get (get ~(clojure (.-state this)) :bindings)
          (quote ~(clojure (.-identifier this)))
          ~(clojure (.-none this))))

  GetBindings
  (clojure [this]
    (let [state (clojure (.-state this))]
      `(get ~state :bindings)))

  GetObject
  (clojure [this]
    `(get ~(clojure (.-state this)) :object))

  Identifier
  (clojure [this]
    (.-symbol this))

  Join
  (clojure [this]
    (do-let* (clojure (.-ma this))
      (fn [a]
        `(if ~a ~a ~(clojure (.-mb this))))))

  Let
  (clojure [this]
    (let [identifier (clojure (.-identifier this))
          expression (clojure (.-expression this))
          body (clojure (.-body this))]
      (flat-let* identifier expression body)))

  Pass
  (clojure [this]
    (clojure (.-state this)))

  Pick
  (clojure [this]
    (let [x__0 (gensym "m__")
          x--0 (clojure (.-ma this))
          x--1 (clojure (.-mb this))]
      `(let* [~x__0 ~x--0]
         (if ~x__0
           ~x__0
           ~x--1))))

  Scan
  (clojure [this]
    (let [x__0 (clojure (.-identifier this))
          x__1 (gensym "x__")
          x__2 (gensym "x__")]
      `(some (fn [~x__0] ~(clojure (.-body this))) ~(clojure (.-expression this)))
      #_
      `(reduce
        (fn [~x__2 ~x__0]
          (let* [~x__1 ~(clojure (.-body this))]
            (if ~x__1
              (reduced ~x__1)
              ~x__2)))
        nil
        ~(clojure (.-expression this)))))

  SetBinding
  (clojure [this]
    (let [x__0 (gensym "x__")
          x__1 (gensym "x__")
          state (clojure (.-state this))
          identifier (clojure (.-identifier this))
          value (clojure (.-value this))]
      `(let* [~x__0 ~state
              ~x__1 (get ~x__0 :bindings)]
         (assoc ~x__0 :bindings (assoc ~x__1  (quote ~identifier) ~value)))))

  SetObject
  (clojure [this]
    (let [state (clojure (.-state this))
          value (clojure (.-value this))]
      `(assoc ~state :object ~value)))

  State
  (clojure [this]
    (let [object (clojure (.-object this))
          bindings (clojure (.-bindings this))]
      `{:bindings ~bindings
        :object ~object}))

  Star
  (clojure [this]
    `((fn ~(clojure (.-recur-identifier this)) [~(clojure (.-state-identifier this))]
        ~(clojure (.-body this)))
      ~(clojure (.-state this))))

  Test
  (clojure [this]
    (let [test (clojure (.-test this))
          then (clojure (.-then this))
          else (clojure (.-else this))]
      `(if ~test ~then ~else))))

