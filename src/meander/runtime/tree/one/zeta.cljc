(ns meander.runtime.tree.one.zeta
  (:refer-clojure :exclude [resolve test])
  (:require [meander.peval.zeta :as m.peval]
            [meander.util.zeta :as m.util]
            [meander.tree.zeta :as m.tree])
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

(extend-protocol IClojure
  Arguments
  (clojure [this]
    (map clojure (.-arguments this)))

  Bind
  (clojure [this]
    (let [identifier (clojure (.-identifier this))
          expression (clojure (.-expression this))
          body (clojure (.-body this))]
      `(let* [~identifier ~expression]
         (if ~identifier
           ~body))))

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
    (:data this))

  Fail
  (clojure [this]
    nil)

  GetBindings
  (clojure [this]
    (let [state (clojure (.-state this))]
      `(get ~state :bindings)))

  GetBinding
  (clojure [this]
    (let [identifier (.-identifier this)
          state (clojure (.-state this))
          none (clojure (.-none this))]
      `(get ~state (quote ~(clojure identifier)) ~none)))

  GetObject
  (clojure [this]
    `(get ~(clojure (.-state this)) :object))

  Identifier
  (clojure [this]
    (.-symbol this))

  Join
  (clojure [this]
    (let [x__0 (gensym "x__")
          x--0 (clojure (.-ma this))
          x--1 (clojure (.-mb this))]
      `(let* [~x__0 ~x--0]
         (if ~x__0
           ~x__0
           ~x--1))))

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
    (let [x__0 (gensym "x__")
          x--0 (clojure (.-ma this))
          x--1 (clojure (.-mb this))]
      `(let* [~x__0 ~x--0]
         (if ~x__0
           ~x__0
           ~x--1))))

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
    (let [meta (meta this)
          object (clojure (.-object this))
          bindings (clojure (.-bindings this))]
      `{:bindings ~bindings
        :object ~object}))

  Test
  (clojure [this]
    (let [test (clojure (.-test this))
          then (clojure (.-then this))
          else (clojure (.-else this))]
      `(if ~test ~then ~else))))

;; Interpretation
;; ---------------------------------------------------------------------

(defprotocol IGetBinding
  (get-binding [this identifier none]))

(defprotocol IGetBindings
  (get-bindings [this]))

(defprotocol IGetObject
  (get-object [this]))

(defprotocol ISetObject
  (set-object [this new-object]))

(defprotocol ISetBinding
  (set-binding [this identifier value]))

;; Helpers
;; -------

(defn scope-from-path [path]
  (reduce
   (fn [scope node]
     (if (m.tree/let? node)
       (update scope (.-identifier ^Let node) (fnil identity (.-expression ^Let node)))
       scope))
   {}
   path))

(defn test-scope-from-path [path]
  (reduce
   (fn [scope node]
     (if (m.tree/test? node)
       (update scope (.-test ^Test node) (fnil identity node))
       scope))
   {}
   path))

(defn resolve
  ([node scope]
   (resolve scope node nil))
  ([node scope not-found]
   (if (m.tree/identifier? node)
     (loop [x (get scope node not-found)]
       (cond
         (= x not-found)
         not-found

         (m.tree/identifier? x)
         (recur (get scope x not-found))

         :else
         x))
     not-found)))

(defn reverse-resolve [node scope]
  (some (fn [[identifier expression]]
          (if (= node expression)
            identifier))
          scope))

(defn test-resolve [node test-scope]
  (get test-scope node))

;; Passes
;; ------

;; pass-interpret
;; ---------------------------------------------------------------------

(defn rule-interpret-pass [node scope]
  (if (instance? Pass node)
    (if-some [state (resolve (.-state ^Pass node) scope nil)]
      (m.tree/pass state))))

(extend-protocol IGetObject
  ;; (get-object (state object bindings))
  ;; ------------------------------------ GetObjectSetObject
  ;;             object
  SetObject
  (get-object [this]
    (.-value this))

  ;; (get-object (set-object state object))
  ;; -------------------------------------- GetObjectState
  ;;               object
  State
  (get-object [this]
    (.-object this)))

(defn rule-interpret-get-object [node scope]
  (if (instance? GetObject node)
    (let [state (.-state ^GetObject node)
          resolved-state (resolve state scope state)]
      (if (satisfies? IGetObject resolved-state)
        (get-object resolved-state)))))

(extend-protocol ISetObject
  ;; (set-object (set-object state e1) e2)
  ;; ------------------------------------- SetObjectSetObject
  ;;        (set-object state e2)
  SetObject
  (set-object [this new-object]
    (m.tree/set-object (.-state this) new-object))

  ;; (set-object (set-binding state e1) e2)
  ;; ------------------------------------- SetObjectSetBinding
  ;; (set-binding (set-object state e1) e2)
  SetBinding
  (set-object [this new-object]
    (m.tree/set-binding (set-object (.-state this) new-object) (.-identifier this) (.-value this)))

  ;; (set-object (state e1 bindings) e2)
  ;; ----------------------------------- SetObjectState
  ;;        (state e2 bindings)
  State
  (set-object [this new-object]
    (m.tree/state new-object (.-bindings this))))

(defn rule-interpret-set-object
  [node scope]
  (if (instance? SetObject node)
    (let [state (.-state ^SetObject node)
          resolved-state (resolve state scope state)]
      (if (satisfies? ISetObject resolved-state)
        (set-object resolved-state (.-value ^SetObject node))))))

(extend-protocol IGetBinding
  Bindings
  (get-binding [this identifier none]
    (get (.-bindings this) identifier none))

  SetBinding
  (get-binding [this identifier none]
    (let [this-identifier (.-identifier this)
          this-value (.-value this)]
      (if (= this-identifier identifier)
        this-value
        (m.tree/get-binding (.-state this) identifier none))))

  SetObject
  (get-binding [this identifier none]
    (get-binding (.-state this) identifier none))

  State
  (get-binding [this identifier none]
    (get-binding (.bindings this) identifier none)))

(defn rule-interpret-get-binding [node scope]
  (if (instance? GetBinding node)
    (let [state (.-state ^GetBinding node)
          resolved-state (resolve state scope state)]
      (if (satisfies? IGetBinding resolved-state)
        (get-binding resolved-state (.-identifier ^GetBinding node) (.-none ^GetBinding node))))))

(defn rule-interpret-get-bindings [node scope]
  (if (instance? GetBindings node)
    (let [state (.-state ^GetBindings node)
          resolved-state (resolve state scope state)]
      (if (instance? State resolved-state)
        (.-bindings ^State resolved-state)))))

(extend-protocol ISetBinding
  ;; (set-binding {i1 e1 ,,, in en} im em)
  ;; -------------------------------------
  ;;      {i1 e1 ,,, in en , im em}
  Bindings
  (set-binding [this identifier value]
    (m.tree/bindings (assoc (.bindings this) identifier value)))

  ;; (set-binding (set-binding state i e1) i e2)
  ;; -------------------------------------------
  ;;          (set-binding state i e2)
  SetBinding
  (set-binding [this identifier value]
    (if (= (.-identifier this) identifier)
      (m.tree/set-binding (.-state this) identifier value)
      (m.tree/set-binding this identifier value)))

  ;; (set-binding (state object bindings) i e)
  ;; -----------------------------------------
  ;; (state object (set-binding bindings i e))
  State
  (set-binding [this identifier value]
    (m.tree/state (.object this) (m.tree/set-binding (.bindings this) identifier value))))

(defn rule-interpret-set-binding
  [node scope]
  (if (instance? SetBinding node)
    (let [state (.-state ^SetBinding node)
          resolved-state (resolve state scope state)]
      (if (satisfies? ISetBinding resolved-state)
        (set-binding resolved-state (.-identifier ^SetBinding node) (.-value ^SetBinding node))))))

(defn rule-interpret-identifier [node scope]
  (if (instance? Identifier node)
    (if-some [node (resolve node scope nil)]
      (if (or (m.tree/data? node)
              (m.tree/code? node)
              (m.tree/state? node))
        node))))

(defn rule-interpret-bind [node scope]
  (if (instance? Bind node)
    (let [expression (.-expression ^Bind node)
          body (.-body ^Bind node)]
      (if (m.tree/fail? expression)
        expression
        (if (m.tree/pass? expression)
          (m.tree/let (.-identifier ^Bind node) (.-state expression)
            (.-body ^Bind node)))))))

;; (pick (pass e1) e2)
;; ------------------- 
;;         e1
;;
;; (pick (fail e1) e2)
;; ------------------- 
;;         e2
(defn rule-interpret-pick [node scope]
  (if (instance? Pick node)
    (let [ma (.-ma ^Pick node)]
      (if (m.tree/pass? ma)
        ma
        (if (m.tree/fail? ma)
          (.-mb ^Pick node))))))

;; (join (pass e1) e2)
;; ------------------- 
;;         e1
;;
;; (join (fail e1) e2)
;; ------------------- 
;;         e2
(defn rule-interpret-join [node scope]
  (if (instance? Join node)
    (let [ma (.-ma ^Join node)]
      (if (m.tree/pass? ma)
        ma
        (if (m.tree/fail? ma)
          (.-mb ^Join node))))))

(defn system-interpret [node scope]
  (or (rule-interpret-pass node scope)
      (rule-interpret-bind node scope)
      (rule-interpret-pick node scope)
      (rule-interpret-join node scope)
      (rule-interpret-identifier node scope)
      (rule-interpret-get-object node scope)
      (rule-interpret-get-binding node scope)
      (rule-interpret-get-bindings node scope)
      (rule-interpret-set-object node scope)
      (rule-interpret-set-binding node scope)
      node))

(defn pass-interpret [node]
  (m.tree/top-down-pass
   (fn [node path]
     (let [scope (scope-from-path path)]
       (loop [node node]
         (let [node* (system-interpret node scope)]
           (if (= node node*)
             node
             (recur node*))))))
   node))


;; pass-commute
;; ---------------------------------------------------------------------

;; (bind x (let y e1 e2) e3)
;; ------------------------- BindLet
;; (let y e1 (bind x e2 e3))
(defn rule-bind-let [node path]
  (if (instance? Bind node)
    (let [expression (.-expression ^Bind node)]
      (if (instance? Let expression)
        (m.tree/let (.-identifier ^Let expression) (.-expression ^Let expression)
          (m.tree/bind (.-identifier ^Bind node) (.-body ^Let expression)
            (.-body ^Bind node)))))))

;;      (bind x (test e1 e2 e3) e4)
;; --------------------------------------- BindTest
;; (test e1 (bind x e2 e4) (bind x e3 e4))
(defn rule-bind-test [node path]
  (if (instance? Bind node)
    (let [expression (.-expression node)]
      (if (instance? Test expression)
        (m.tree/test (.-test ^Test expression)
          (m.tree/bind (.-identifier ^Bind node) (.-then ^Test expression)
            (.-body ^Bind node))
          (m.tree/bind (.-identifier ^Bind node) (.-else ^Test expression)
            (.-body ^Bind node)))
        nil))
    nil))

;; (let x (let y e1 e2) e3)
;; ------------------------ LetLet
;; (let y e1 (let x e2 e3))
(defn rule-let-let [node path]
  (if (instance? Let node)
    (let [expression (.-expression node)]
      (if (instance? Let expression)
        (m.tree/let (.-identifier ^Let expression) (.-expression ^Let expression)
          (m.tree/let (.-identifier ^Let node) (.-body ^Let expression)
            (.-body ^Let node))))
      nil)
    nil))

;;     (let x (test e1 e2 e3) e4)
;; ------------------------------------ LetTest
;; (test e1 (let x e2 e4) (let x e3 e4)
(defn rule-let-test [node path]
  (if (instance? Let node)
    (let [expression (.-expression ^Let node)]
      (if (instance? Test expression)
        (m.tree/test (.-test ^Test expression)
          (m.tree/let (.-identifier ^Let node) (.-then ^Test expression)
            (.-body ^Let node))
          (m.tree/let (.-identifier ^Let node) (.-else ^Test expression)
            (.-body ^Let node)))
        nil))
    nil))

(defn system-commute [node path]
  (or (rule-bind-let node path)
      (rule-bind-test node path)
      (rule-let-let node path)
      (rule-let-test node path)
      node))

(defn pass-commute [node]
  (m.tree/top-down-pass
   (fn [node path]
     (loop [node node]
       (let [node* (system-commute node path)]
         (if (= node node*)
           node
           (recur node*)))))
   node))


;; Prune
;; ---------------------------------------------------------------------

(defprotocol IPrune
  (-prune [this loc]))

(defn rule-prune-let [node scope]
  (if (instance? Let node)
    (let [this-identifier (.-identifier ^Let node)
          this-expression (.-expression ^Let node)
          this-body (.-body ^Let node)]
      (if (instance? Identifier this-expression)
        (m.tree/postwalk-replace {this-identifier this-expression} this-body)
        (if-some [identifier (reverse-resolve this-expression scope)]
          (m.tree/postwalk-replace {this-identifier identifier} this-body)
          (let [uses (count (filter #{this-identifier} (m.tree/subnodes this-body)))]
            (if (zero? uses)
              this-body)))))))


(defn rule-prune-test [node test-scope]
  (if (instance? Test node)
    (let [test (.-test ^Test node)
          then (.-then ^Test node)
          else (.-else ^Test node)]
      (if (= then else)
        then
        (if-some [parent-test (get test-scope test)]
          (if (some #{node} (m.tree/subnodes (.-then ^Test parent-test)))
            then
            else)
          (let [form (m.peval/peval (clojure test))]
            (cond
              (m.peval/truthy-constant-expression? form)
              then

              (m.peval/falsey-constant-value? form)
              else)))))))

(defn prune [node]
  (m.tree/top-down-pass
   (fn [node path]
     (let [scope (scope-from-path path)
           test-scope (test-scope-from-path path)]
       (loop [node node]
         (let [node* (or #_(rule-prune-let node scope)
                         (rule-prune-test node test-scope)
                         node)]
           (if (= node node*)
             node
             (recur node*))))))
   node))
