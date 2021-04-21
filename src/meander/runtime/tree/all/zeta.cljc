(ns meander.runtime.tree.all.zeta
  (:require [meander.tree.zeta :as m.tree])
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
                              SetBinding
                              SetObject
                              State
                              Test)))
(defn flat-let*
  {:style/indent 2
   :private true}
  [binding expression body]
  (if (and (seq? body) (= (first body) 'let*))
    `(let* [~binding ~expression ~@(nth body 1)]
       ~@(drop 2 body))
    `(let* [~binding ~expression] ~body)))

(defprotocol IClojure
  (clojure [this]))

(extend-protocol IClojure
  Arguments
  (clojure [this]
    (mapv clojure (.-arguments this)))

  Bind
  (clojure [this]
    `(mapcat
      (fn [~(clojure (.-identifier this))]
        ~(clojure (.-body this)))
      ~(clojure (.-expression this))))

  Bindings
  (clojure [this]
    (into {} (map (fn [[k v]] [`(quote ~(clojure k)) (clojure v)]))
          (.-bindings this)))

  Call
  (clojure [this]
    `(~(clojure (.-f this)) ~@(clojure (.-arguments this))))

  Code
  (clojure [this]
    (.-code this))

  Data
  (clojure [this]
    (.-data this))

  Fail
  (clojure [this]
    ())

  GetBinding
  (clojure [this]
    `(get (get ~(clojure (.-state this)) :bindings)
          '~(clojure (.-identifier this))
          ~(clojure (.-none this))))

  GetBindings
  (clojure [this]
    `(get ~(clojure (.-state this)) :bindings))

  GetObject
  (clojure [this]
    `(get ~(clojure (.-state this)) :object))

  Identifier
  (clojure [this]
    (.-symbol this))

  Let
  (clojure [this]
    (flat-let* (clojure (.-identifier this)) (clojure (.-expression this))
      (clojure (.-body this))))

  Pass
  (clojure [this]
    `(list ~(clojure (.-state this))))

  SetBinding
  (clojure [this]
    `(assoc-in ~(clojure (.-state this)) [:bindings '~(clojure (.-identifier this))] ~(clojure (.-value this))))

  SetObject
  (clojure [this]
    `(assoc ~(clojure (.-state this)) :object ~(clojure (.-value this))))

  State
  (clojure [this]
    {:object (clojure (.-object this))
     :bindings (clojure (.-bindings this))})

  Test
  (clojure [this]
    `(if ~(clojure (.-test this))
       ~(clojure (.-then this))
       ~(clojure (.-else this))))

  )
