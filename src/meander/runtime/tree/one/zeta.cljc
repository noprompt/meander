(ns meander.runtime.tree.one.zeta
  (:refer-clojure :exclude [resolve test])
  (:require [clojure.zip :as zip]
            [meander.peval.zeta :as m.peval]
            [meander.util.zeta :as m.util]
            [meander.tree.zeta :as m.tree]
            [meander.zip.zeta :as m.zip])
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

(extend-protocol IClojure
  Arguments
  (clojure [this]
    (let [meta (meta this)]
      (doall
       (map (fn [argument]
              (clojure (with-meta argument meta)))
            (:arguments this)))))

  Bind
  (clojure [this]
    (let [meta (meta this)
          identifier (clojure (:identifier this))
          expression (clojure (with-meta (:expression this) meta))
          body (clojure (with-meta (:body this) (update meta :bindings assoc identifier expression)))]
      (if (m.peval/constant-expression? expression)
        body
        (if (and (seq? body) (= (first body) 'let*))
          `(let* [~identifier ~expression
                  ~@(nth body 1)]
             ~@(drop 2 body))
          `(let* [~identifier ~expression]
             ~body)))))

  Bindings
  (clojure [this]
    (let [meta (meta this)]
      (into {} (map (fn [[k-node v-node]]
                      (let [k (clojure (with-meta k-node meta))
                            v (clojure (with-meta v-node meta))]
                        [`(quote ~k) v]))
                    (:bindings this)))))

  Call
  (clojure [this]
    (let [meta (meta this)
          f (clojure (with-meta (:f this) meta))
          arguments (clojure (with-meta (:arguments this) meta))]
      (m.peval/peval `(~f ~@arguments))))

  Code
  (clojure [this]
    (:code this))

  Data
  (clojure [this]
    (:data this))

  Fail
  (clojure [this]
    nil)

  GetBindings
  (clojure [this]
    (let [state (clojure (with-meta (:state this) (meta this)))]
      `(get ~state :bindings)))

  GetBinding
  (clojure [this]
    (let [meta (meta this)
          state (clojure (with-meta (.-state this) meta))
          none (clojure (with-meta (.-none this) meta))]
      `(get ~state (quote ~(clojure (.-identifier this))) ~none)))

  GetObject
  (clojure [this]
    `(get ~(clojure (with-meta (:state this) (meta this))) :object))

  Identifier
  (clojure [this]
    (if-some [[_ expression] (find (get (meta this) :bindings) this)]
      (if (m.peval/constant-expression? expression)
        expression
        (:symbol this))
      (:symbol this)))

  Join
  (clojure [this]
    (let [meta (meta this)
          x__0 (gensym "x__")
          x--0 (clojure (with-meta (.-ma this) meta))
          x--1 (clojure (with-meta (.-mb this) meta))]
      `(let* [~x__0 ~x--0]
         (if ~x__0
           ~x__0
           ~x--1))))

  Let
  (clojure [this]
    (let [meta (meta this)
          identifier (clojure (:identifier this))
          expression (clojure (with-meta (:expression this) meta))
          body (clojure (with-meta (:body this) (update meta :bindings assoc (:identifier this) expression)))]
      (if (m.peval/constant-expression? expression)
        body
        (if (and (seq? body) (= (first body) 'let*))
          `(let* [~identifier ~expression
                  ~@(nth body 1)]
             ~@(drop 2 body))
          `(let* [~identifier ~expression]
             ~body)))))

  Pass
  (clojure [this]
    (clojure (with-meta (:state this) (meta this))))

  Pick
  (clojure [this]
    (let [meta (meta this)
          x--0 (clojure (with-meta (:ma this) meta))]
      (if (m.peval/truthy-constant-value? x--0)
        x--0
        (let [x__0 (gensym "x__")]
          `(let* [~x__0 ~x--0]
             (if ~x__0
               ~x__0
               ~(clojure (with-meta (:mb this) meta))))))))

  SetBinding
  (clojure [this]
    (let [x__0 (gensym "x__")
          x__1 (gensym "x__")
          meta (meta this)
          state (clojure (with-meta (.-state this) meta))
          value (clojure (with-meta (.-value this) meta))]
      `(let* [~x__0 ~state
              ~x__1 (get ~x__0 :bindings)]
         (assoc ~x__0 :bindings (assoc ~x__1  (quote ~(clojure (.-identifier this))) ~value)))))

  SetObject
  (clojure [this]
    (let [meta (meta this)
          state (clojure (with-meta (.-state this) meta))
          value (clojure (with-meta (.-value this) meta))]
      `(assoc ~state :object ~value)))

  State
  (clojure [this]
    (let [meta (meta this)
          object (clojure (with-meta (:object this) meta))
          bindings (clojure (with-meta (:bindings this) meta))]
      `{:bindings ~bindings
        :object ~object}))

  Test
  (clojure [this]
    (let [meta (meta this)
          test (clojure (with-meta (:test this) meta))
          then (clojure (with-meta (:then this) meta))
          else (clojure (with-meta (:else this) meta))]
      (m.peval/peval `(if ~test ~then ~else)))))

;; Interpretation
;; ---------------------------------------------------------------------

(defprotocol IInterpret
  (-interpret [this loc]))

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

(defn pass [f]
  (fn [node]
    (zip/root (m.zip/top-down (fn [loc] (zip/edit loc f loc)) (m.tree/zipper node)))))

(defn interpret [node]
  ((pass -interpret) node))

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

(defn scope [loc]
  (let [parent-loc (zip/up loc)
        loc (if (and (some? parent-loc)
                     (let [parent-node (zip/node parent-loc)]
                       (and (m.tree/let? parent-node)
                            (= (.-expression ^Let parent-node)
                               (zip/node loc)))))
              parent-loc
              loc)]
    (scope-from-path (zip/path loc))))

(defn test-scope [loc]
  (let [parent-loc (zip/up loc)
        loc (if (and (some? parent-loc)
                     (let [parent-node (zip/node parent-loc)]
                       (and (m.tree/test? parent-node)
                            (= (.-test ^Test parent-node)
                               (zip/node loc)))))
              parent-loc
              loc)]
    (test-scope-from-path (zip/path loc))))

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

(defn test-resolve [node loc]
  (get (test-scope loc) node))

;; Implementation
;; --------------

;; pass-interpret
;; ---------------------------------------------------------------------

(defn rule-interpret-pass [node path]
  (if (instance? Pass node)
    (if-some [state (resolve (.-state ^Pass node) (scope-from-path path) nil)]
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

(defn rule-interpret-get-object
  [node path]
  (if (instance? GetObject node)
    (let [state (.-state ^GetObject node)
          resolved-state (resolve state (scope-from-path path) state)]
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
  [node path]
  (if (instance? SetObject node)
    (let [state (.-state ^SetObject node)
          resolved-state (resolve state (scope-from-path path) state)]
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

(defn rule-interpret-get-binding [node path]
  (if (instance? GetBinding node)
    (let [state (.-state ^GetBinding node)
          resolved-state (resolve state (scope-from-path path) state)]
      (if (satisfies? IGetBinding resolved-state)
        (get-binding resolved-state (.-identifier ^GetBinding node) (.-none ^GetBinding node))))))

(defn rule-interpret-get-bindings [node path]
  (if (instance? GetBindings node)
    (let [state (.-state ^GetBindings node)
          resolved-state (resolve state (scope-from-path path) state)]
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
  [node path]
  (if (instance? SetBinding node)
    (let [state (.-state ^SetBinding node)
          resolved-state (resolve state (scope-from-path path) state)]
      (if (satisfies? ISetBinding resolved-state)
        (set-binding resolved-state (.-identifier ^SetBinding node) (.-value ^SetBinding node))))))

(defn rule-interpret-identifier [node path]
  (if (instance? Identifier node)
    (if-some [node (resolve node (scope-from-path path) nil)]
      (if (or (m.tree/data? node)
              (m.tree/code? node)
              (m.tree/state? node))
        node))))

(defn rule-interpret-bind [node path]
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
(defn rule-interpret-pick [node path]
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
(defn rule-interpret-join [node path]
  (if (instance? Join node)
    (let [ma (.-ma ^Join node)]
      (if (m.tree/pass? ma)
        ma
        (if (m.tree/fail? ma)
          (.-mb ^Join node))))))

(defn pass-interpret [node]
  (m.tree/top-down-pass
   (fn [node path]
     (or (rule-interpret-pass node path)
         (rule-interpret-bind node path)
         (rule-interpret-set-object node path)
         (rule-interpret-get-object node path)
         (rule-interpret-set-binding node path)
         (rule-interpret-get-binding node path)
         (rule-interpret-get-bindings node path)
         (rule-interpret-identifier node path)
         (rule-interpret-pick node path)
         (rule-interpret-join node path)
         node))
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
;; -------------------------------------- BindTest
;; (test e1 (bind x e2 e4) (bind x e3 e4)
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

(defn pass-commute [node]
  (m.tree/top-down-pass
   (fn [node path]
     (or (rule-bind-let node path)
         (rule-bind-test node path)
         (rule-let-let node path)
         (rule-let-test node path)
         node))
   node))


;; Prune
;; ---------------------------------------------------------------------

(defprotocol IPrune
  (-prune [this loc]))

(defn prune [node]
  ((m.util/fix (pass -prune)) node))

(extend-protocol IPrune
  Let
  (-prune [this loc]
    (let [this-identifier (.-identifier this)
          this-expression (.-expression this)
          this-body (.-body this)]
      (if (instance? Identifier this-expression)
        (m.tree/postwalk-replace {this-identifier this-expression} this-body)
        (if-some [identifier (reverse-resolve this-expression (scope loc))]
          (m.tree/postwalk-replace {this-identifier identifier} this-body)
          (let [uses (count (filter #{this-identifier} (m.tree/subnodes this-body)))]
            (case uses
              0 this-body

              ;; else
              this))))))

  Test
  (-prune [this loc]
    (let [test (.-test this)
          then (.-then this)
          else (.-else this)]
      (if (= then else)
        then
        (if-some [parent-test (test-resolve test loc)]
          (if (some #{this} (m.tree/subnodes (.-then ^Test parent-test)))
            then
            else)
          (let [form (m.peval/peval (clojure test))]
            (cond
              (m.peval/truthy-constant-expression? form)
              then

              (m.peval/falsey-constant-value? form)
              else

              :else
              this))))))


  #?(:clj Object, :cljs default)
  (-prune [this loc]
    this))
