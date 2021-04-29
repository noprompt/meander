(ns meander.tree.rewrite.zeta
  {:no-doc true}
  (:refer-clojure :exclude [resolve
                            test])
  (:require [meander.peval.zeta :as m.peval]
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

(defmacro try->
  {:style/indent 1
   :private true}
  ([expr] expr)
  ([expr [f & rest] & more]
   `(let [x# ~expr
          y# (~f x# ~@rest)]
      (try-> (if (some? y#) y# x#)
        ~@more))))

;; Rewrite Rules
;; ---------------------------------------------------------------------

;; (bind x (pass e1) e2)
;; ---------------------
;;     (let x e1 e2)
(defn rule-bind-pass [node]
  (if (instance? Bind node)
    (let [expression (.-expression ^Bind node)
          body (.-body ^Bind node)
          identifier (m.tree/identifier)]
      (if (m.tree/pass? expression)
        (m.tree/let (.-identifier ^Bind node) (.-state expression)
          (.-body ^Bind node))))))

;; (bind x (fail e1) e2)
;; ---------------------
;;      (fail e1)
(defn rule-bind-fail [node]
  (if (instance? Bind node)
    (let [expression (.-expression ^Bind node)
          body (.-body ^Bind node)]
      (if (m.tree/fail? expression)
        expression))))

;; (bind x (let y e1 e2) e3)
;; ------------------------- BindLet
;; (let y e1 (bind x e2 e3))
(defn rule-bind-let [node]
  (if (instance? Bind node)
    (let [expression (.-expression ^Bind node)]
      (if (instance? Let expression)
        (m.tree/let (.-identifier ^Let expression) (.-expression ^Let expression)
          (m.tree/bind (.-identifier ^Bind node) (.-body ^Let expression)
            (.-body ^Bind node)))))))

;;      (bind x (test e1 e2 e3) e4)
;; --------------------------------------- BindTest
;; (test e1 (bind x e2 e4) (bind x e3 e4))
(defn rule-bind-test [node]
  (if (instance? Bind node)
    (let [expression (.-expression node)]
      (if (instance? Test expression)
        (m.tree/test (.-test ^Test expression)
          (m.tree/bind (.-identifier ^Bind node) (.-then ^Test expression)
            (.-body ^Bind node))
          (m.tree/bind (.-identifier ^Bind node) (.-else ^Test expression)
            (.-body ^Bind node)))))))

;;       (bind i (pick e1 e2) e3)
;; ------------------------------------ BindPick
;; (pick (bind i e1 e3) (bind i e2 e3))
(defn rule-bind-pick [node]
  (if (instance? Bind node)
    (let [expression (.-expression node)]
      (if (instance? Pick expression)
        (let [identifier (.-identifier ^Bind node)
              body (.-body ^Bind node)
              ma (.-ma ^Pick expression)
              mb (.-mb ^Pick expression)]
          (m.tree/pick (m.tree/bind identifier ma body)
                       (m.tree/bind identifier mb body)))))))

(defn system-bind [node]
  (try-> node
    (rule-bind-fail)
    (rule-bind-pass)
    (rule-bind-let)
    (rule-bind-test)
    (rule-bind-pick)))

(defn pass-bind [node]
  (m.tree/top-down-pass
   (fn [node path]
     (system-bind node))
   node))

;; (join (fail e1) e2)
;; ------------------- 
;;         e2
(defn rule-join [node]
  (if (instance? Join node)
    (let [ma (.-ma ^Join node)]
      (if (m.tree/fail? ma)
        (.-mb ^Join node)))))

;; Pick rules
;; ----------

;; (pick (pass e1) e2)
;; ------------------- 
;;         e1
;;
;; (pick (fail e1) e2)
;; ------------------- 
;;         e2
(defn rule-pick [node]
  (if (instance? Pick node)
    (let [ma (.-ma ^Pick node)]
      (if (m.tree/pass? ma)
        ma
        (if (m.tree/fail? ma)
          (.-mb ^Pick node))))))


;; (pick (let x e1 e2) e3)
;; -----------------------
;; (let x e1 (pick e2 e3))
(defn rule-pick-let [node]
  (if (instance? Pick node)
    (let [ma (.-ma ^Pick node)]
      (if (instance? Let ma)
        (let [mb (.-mb ^Pick node)]
          (m.tree/let (.-identifier ^Let ma) (.-expression ^Let ma)
            (m.tree/pick (.-body ^Let ma) mb)))))))

;;     (pick (test e1 e2 e3) e4)
;; -----------------------------------
;; (test e1 (pick e2 e4) (pick e3 e4))
(defn rule-pick-test [node]
  (if (instance? Pick node)
    (let [ma (.-ma ^Pick node)]
      (if (instance? Test ma)
        (let [mb (.-mb ^Pick node)]
          (m.tree/test (.-test ^Test ma)
            (m.tree/pick (.-then ma) mb)
            (m.tree/pick (.-else ma) mb)))))))

(defn system-pick [node]
  (try-> node
    (rule-pick)
    (rule-pick-let)
    (rule-pick-test)))

(defn pass-pick [node]
  (m.tree/top-down-pass
   (fn [node path]
     (system-pick node))
   node))

;; Let rules
;; ---------

;; (let x (let y e1 e2) e3)
;; ------------------------ LetLet
;; (let y e1 (let x e2 e3))
(defn rule-let-let [node]
  (if (instance? Let node)
    (let [expression (.-expression node)]
      (if (instance? Let expression)
        (m.tree/let (.-identifier ^Let expression) (.-expression ^Let expression)
          (m.tree/let (.-identifier ^Let node) (.-body ^Let expression)
            (.-body ^Let node)))))))

;;     (let x (test e1 e2 e3) e4)
;; ------------------------------------ LetTest
;; (test e1 (let x e2 e4) (let x e3 e4)
(defn rule-let-test [node]
  (if (instance? Let node)
    (let [expression (.-expression ^Let node)]
      (if (instance? Test expression)
        (m.tree/test (.-test ^Test expression)
          (m.tree/let (.-identifier ^Let node) (.-then ^Test expression)
            (.-body ^Let node))
          (m.tree/let (.-identifier ^Let node) (.-else ^Test expression)
            (.-body ^Let node)))))))

(defn system-let [node]
  (try-> node
    (rule-let-let)
    (rule-let-test)))

(defn pass-let [node]
  (m.tree/top-down-pass
   (fn [node path]
     (system-let node))
   node))

;; Test rules
;; ----------

;; (test (data true) e1 e2)
;; ------------------------
;;            e1
(defn rule-test-true [node]
  (if (instance? Test node)
    (let [test (.-test ^Test node)]
      (if (= test m.tree/$true)
        (.-then ^Test node)))))

;; (test (data false) e1 e2)
;; ------------------------
;;            e1
(defn rule-test-false [node]
  (if (instance? Test node)
    (let [test (.-test ^Test node)]
      (if (= test m.tree/$false)
        (.-else ^Test node)))))

(defn system-test [node]
  (try-> node
    (rule-test-true)
    (rule-test-false)))

(defn pass-test [node]
  (m.tree/top-down-pass
   (fn [node path]
     (system-test node))
   node))

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
    (let [state (.-state this)]
      (m.tree/set-binding (if (satisfies? ISetObject state)
                            (set-object state new-object)
                            state)
          (.-identifier this) (.-value this))))

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
    (let [state (.-state this)]
      (if (satisfies? IGetBinding state)
        (get-binding state identifier none)
        (m.tree/get-binding this identifier none))))

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

(defn rule-interpret-call [node scope]
  (if (instance? Call node)
    (let [function (.-f ^Call node)
          arguments (.-arguments ^Arguments (.-arguments ^Call node))]
      (if (= function m.tree/$any?)
        m.tree/$true
        (if (and (= function m.tree/$=)
                 (apply = arguments))
          m.tree/$true)))))
;; => #'meander.tree.rewrite.zeta/rule-interpret-call

(defn system-interpret [node scope]
  (try-> node
    (rule-interpret-pass scope)
    (rule-interpret-identifier scope)
    (rule-interpret-get-object scope)
    (rule-interpret-get-binding scope)
    (rule-interpret-get-bindings scope)
    (rule-interpret-set-object scope)
    (rule-interpret-set-binding scope)
    (rule-interpret-call scope)))

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

;; Prune
;; ---------------------------------------------------------------------

(defn rule-prune-let-already-bound [node scope]
  (if (instance? Let node)
    (let [this-identifier (.-identifier ^Let node)
          this-expression (.-expression ^Let node)
          this-body (.-body ^Let node)]
      (if-some [identifier (reverse-resolve this-expression scope)]
        (m.tree/postwalk-replace {this-identifier identifier} this-body)))))

(defn rule-prune-let-unused [node scope]
  (if (instance? Let node)
    (let [identifier (.-identifier ^Let node)
          expression (.-expression ^Let node)
          body (.-body ^Let node)]
      (let [uses (count (filter #{identifier} (m.tree/subnodes body)))]
        (if (zero? uses)
          body)))))

(defn rule-prune-let-redundant [node scope]
  (if (instance? Let node)
    (let [identifier (.-identifier ^Let node)
          expression (.-expression ^Let node)
          body (.-body ^Let node)]
      (if (instance? Identifier expression)
        (m.tree/postwalk-replace {identifier expression} body)))))

(defn system-prune-let [node scope]
  (try-> node
    (rule-prune-let-redundant scope)
    (rule-prune-let-already-bound scope)
    (rule-prune-let-unused scope)))

(defn pass-prune-let [node]
  (m.tree/top-down-pass
   (fn [node path]
     (let [scope (scope-from-path path)]
       (system-prune-let node scope)))
   node))

(defn rule-prune-nested-test [node]
  (if (instance? Test node)
    (let [test (.-test ^Test node)
          then (.-then ^Test node)
          else (.-else ^Test node)]
      (m.tree/test test
        (m.tree/top-down-pass
         (fn [node path]
           (if (instance? Test node)
             (let [nested-test (.-test ^Test node)]
               (if (= nested-test test)
                 (.-then ^Test node)
                 node))
             node))
         then)
        (m.tree/top-down-pass
         (fn [node path]
           (if (instance? Test node)
             (let [nested-test (.-test ^Test node)]
               (if (= nested-test test)
                 (.-else ^Test node)
                 node))
             node))
         else)))))

(defn rule-prune-test-redundant [node]
  (if (instance? Test node)
    (let [test (.-test ^Test node)
          then (.-then ^Test node)
          else (.-else ^Test node)]
      (if (or (= then else)
              (and (m.tree/fail? then)
                   (m.tree/fail? else)))
        then))))

(defn pass-prune-test [node]
  (m.tree/top-down-pass
   (fn [node path]
     (try-> node
       (rule-prune-test-redundant)
       (rule-prune-nested-test)))
   node))
