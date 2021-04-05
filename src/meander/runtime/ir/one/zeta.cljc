(ns meander.runtime.ir.one.zeta
  (:refer-clojure :exclude [resolve test])
  (:require [clojure.zip :as zip]
            [meander.peval.zeta :as m.peval]
            [meander.util.zeta :as m.util]
            [meander.ir.zeta :as m.ir]
            [meander.zip.zeta :as m.zip])
  (:import (meander.ir.zeta Arguments
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

;; pass-resolve-identifiers
;; pass-apply-set-object
;; pass-apply-set-binding
;; pass-apply-get-binding
;; pass-apply-get-object

;; Code generation
;; ---------------------------------------------------------------------

(defprotocol IClojure
  (clojure [ir]))

(extend-protocol IClojure
  Arguments
  (clojure [this]
    (map clojure (:arguments this)))

  Bind
  (clojure [this]
    (let [identifier (clojure (:identifier this))]
      `(let* [~identifier ~(clojure (:expression this))]
         (if ~identifier
           ~(clojure (:body this))))))

  Bindings
  (clojure [this]
    (into {} (map (fn [[k v]] [`(quote ~(clojure k)) (clojure v)]) (:bindings this))))

  Call
  (clojure [this]
    `(~(clojure (:f this)) ~@(clojure (:arguments this))))

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
    `(get ~(clojure (:state this)) :bindings))


  GetBinding
  (clojure [this]
    `(get ~(clojure (.-state this)) (quote ~(clojure (.-identifier this))) ~(clojure (.-none this))))

  GetObject
  (clojure [this]
    `(get ~(clojure (:state this)) :object))

  Identifier
  (clojure [this]
    (:symbol this))

  Join
  (clojure [this]
    (let [x__0 (gensym "x__")]
      `(let* [~x__0 ~(clojure (.-ma this))]
         (if ~x__0
           ~x__0
           ~(clojure (.-mb this))))))

  Let
  (clojure [this]
    (let [identifier (clojure (:identifier this))]
      `(let* [~identifier ~(clojure (:expression this))]
         ~(clojure (:body this)))))

  Pass
  (clojure [this]
    (clojure (:state this)))

  Pick
  (clojure [this]
    (let [x__0 (gensym "x__")]
      `(let* [~x__0 ~(clojure (:ma this))]
         (if ~x__0
           ~x__0
           ~(clojure (:mb this))))))

  SetBinding
  (clojure [this]
    (let [x__0 (gensym "x__")
          x__1 (gensym "x__")]
      `(let* [~x__0 ~(clojure (.-state this))]
         (let* [~x__1 (get ~x__0 :bindings)]
           (assoc ~x__0 :bindings (assoc ~x__1  (quote ~(clojure (.-identifier this))) ~(clojure (.-value this))))))))

  SetObject
  (clojure [this]
    `(assoc ~(clojure (.-state this)) :object ~(clojure (.-value this))))

  State
  (clojure [this]
    `{:object ~(clojure (:object this))
      :bindings ~(clojure (:bindings this))})

  Test
  (clojure [this]
    `(if ~(clojure (:test this))
       ~(clojure (:then this))
       ~(clojure (:else this)))))

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
    (zip/root (m.zip/top-down  (fn [loc] (zip/edit loc f loc)) (m.ir/zipper node)))))

(defn interpret [node]
  ((pass -interpret) node))

(defn scope [loc]
  (let [parent-loc (zip/up loc)
        loc (if (and (some? parent-loc)
                     (let [parent-node (zip/node parent-loc)]
                       (and (m.ir/let? parent-node)
                            (= (.-expression ^Let parent-node)
                               (zip/node loc)))))
              parent-loc
              loc)]
    (reduce
     (fn [scope node]
       (if (m.ir/let? node)
         (update scope (.-identifier ^Let node) (fnil identity (.-expression ^Let node)))
         scope))
     {}
     (zip/path loc))))

(defn test-scope [loc]
  (let [parent-loc (zip/up loc)
        loc (if (and (some? parent-loc)
                     (let [parent-node (zip/node parent-loc)]
                       (and (m.ir/test? parent-node)
                            (= (.-test ^Test parent-node)
                               (zip/node loc)))))
              parent-loc
              loc)]
    (reduce
     (fn [scope node]
       (if (m.ir/test? node)
         (update scope (.-test ^Test node) (fnil identity node))
         scope))
     {}
     (zip/path loc))))

(defn resolve
  ([node loc]
   (resolve (scope loc) node nil))
  ([node loc not-found]
   (if (m.ir/identifier? node)
     (let [scope (scope loc)]
       (loop [x (get scope node not-found)]
         (cond
           (= x not-found)
           not-found

           (m.ir/identifier? x)
           (recur (get scope x not-found))

           :else
           x)))
     not-found)))

(defn reverse-resolve [node loc]
  (some (fn [[identifier expression]]
          (if (= node expression)
            identifier))
          (scope loc)))

(defn test-resolve [node loc]
  (get (test-scope loc) node))

;; Implementation
;; --------------

(extend-protocol IGetBinding
  Bindings
  (get-binding [this identifier none]
    (get (.-bindings this) identifier none))

  Identifier
  (get-binding [this identifier none]
    (m.ir/get-binding this identifier none))

  SetBinding
  (get-binding [this identifier none]
    (let [this-identifier (.-identifier this)
          this-value (.-value this)]
      (if (= this-identifier identifier)
        this-value
        (m.ir/get-binding (.-state this) identifier none))))

  SetObject
  (get-binding [this identifier none]
    (get-binding (.-state this) identifier none))

  State
  (get-binding [this identifier none]
    (get-binding (.bindings this) identifier none)))

(extend-protocol IGetBindings
  SetBinding
  (get-bindings [this]
    this)

  SetObject
  (get-bindings [this]
    (get-bindings (.-state this)))

  State
  (get-bindings [this]
    (.-bindings this)))

(extend-protocol IGetObject
  Identifier
  (get-object [this]
    (m.ir/get-object this))

  SetObject
  (get-object [this]
    (.-value this))

  State
  (get-object [this]
    (.-object this)))

(extend-protocol ISetObject
  Identifier
  (set-object [this new-object]
    (m.ir/set-object this new-object))

  SetObject
  (set-object [this new-object]
    (m.ir/set-object (.-state this) new-object))

  SetBinding
  (set-object [this new-object]
    (m.ir/set-binding (set-object (.-state this) new-object) (.-identifier this) (.-value this)))

  State
  (set-object [this new-object]
    (m.ir/state new-object (.-bindings this))))

(extend-protocol ISetBinding
  Bindings
  (set-binding [this identifier value]
    (m.ir/bindings (assoc (.bindings this) identifier value)))

  Data
  (set-binding [this]
    (m.ir/set-binding this))

  Identifier
  (set-binding [this identifier value]
    (m.ir/set-binding this identifier value))

  SetBinding
  (set-binding [this identifier value]
    (m.ir/set-binding (set-binding (.-state this) identifier value) (.-identifier this) (.-value this)))

  SetObject
  (set-binding [this identifier value]
    (m.ir/set-object (set-binding (.-state this) identifier value) (.-value this)))

  State
  (set-binding [this identifier value]
    (m.ir/state (.object this) (set-binding (.bindings this) identifier value))))

(extend-protocol IInterpret
  Arguments
  (-interpret [this loc]
    this)

  Bind
  (-interpret [this loc]
    (let [expression (.-expression this)
          body (.-body this)]
      (cond
        (m.ir/fail? expression)
        expression

        (m.ir/pass? expression)
        (m.ir/let (.-identifier this) (.-state expression)
          (.-body this))

        :else
        this)))

  Bindings
  (-interpret [this loc]
    this)

  Call
  (-interpret [this loc]
    this)

  Code
  (-interpret [this loc]
    this)

  Data
  (-interpret [this loc]
    this)

  Fail
  (-interpret [this loc]
    this)

  GetBinding
  (-interpret [this loc]
    (get-binding (resolve (.-state this) loc (.-state this)) (.-identifier this) (.-none this))
   )

  GetBindings
  (-interpret [this loc]
    (if-some [state (resolve (.-state this) loc nil)]
      (get-bindings state)
      this))

  GetObject
  (-interpret [this loc]
    (get-object (resolve (.-state this) loc (.-state this))))

  Identifier
  (-interpret [this loc]
    (if-some [node (resolve this loc nil)]
      (if (m.ir/data? node)
        node
        this)
      this))

  Join
  (-interpret [this loc]
    (cond
      (or (m.ir/fail? (.-ma this))
          (m.ir/pass? (.-mb this)))
      (.-mb this)

      (or (m.ir/fail? (.-mb this))
          (m.ir/pass? (.-mb this)))
      (.-ma this)

      :else
      this))

  Let
  (-interpret [this loc]
    this)

  Pass
  (-interpret [this loc]
    (if-some [state (resolve (.-state this) loc nil)]
      (m.ir/pass state)
      this))

  Pick
  (-interpret [this loc]
    (cond
      (or (m.ir/fail? (.-ma this))
          (m.ir/pass? (.-mb this)))
      (.-mb this)

      (or (m.ir/fail? (.-mb this))
          (m.ir/pass? (.-mb this)))
      (.-ma this)

      :else
      this))

  SetBinding
  (-interpret [this loc]
    (set-binding (resolve (.-state this) loc (.-state this)) (.-identifier this) (.-value this)))

  SetObject
  (-interpret [this loc]
    (set-object (resolve (.-state this) loc (.-state this)) (.-value this))))

;; pass-set-object
;; ---------------------------------------------------------------------

;; (set-object (state e1 bindings) e2)
;; ----------------------------------- SetObjectState
;;        (state e2 bindings)

;; (set-object (set-object state e1) e2)
;; ------------------------------------- SetObjectSetObject
;;        (set-object state e2)
(defn rule-set-object
  [node path]
  (if (instance? SetObject node)
    (resolve (.-state this) path (.-state this)) (.-value this)
    node))

;; pass-commute
;; ---------------------------------------------------------------------

;; (bind x (let y e1 e2) e3)
;; ------------------------- BindLet
;; (let y e1 (bind x e2 e3))
(defn rule-bind-let [node]
  (if (instance? Bind node)
    (let [expression (.-expression ^Bind node)]
      (if (instance? Let expression)
        (m.ir/let (.-identifier ^Let expression) (.-expression ^Let expression)
          (m.ir/bind (.-identifier ^Bind node) (.-body ^Let expression)
            (.-body ^Bind node))))
      node)
    node))

;;      (bind x (test e1 e2 e3) e4)
;; -------------------------------------- BindTest
;; (test e1 (bind x e2 e4) (bind x e3 e4)
(defn rule-bind-test [node]
  (if (instance? Bind node)
    (let [expression (.-expression node)]
      (if (instance? Test expression)
        (m.ir/test (.-test ^Test expression)
          (m.ir/bind (.-identifier ^Bind node) (.-then ^Test expression)
            (.-body ^Bind node))
          (m.ir/bind (.-identifier ^Bind node) (.-else ^Test expression)
            (.-body ^Bind node)))
        node))
    node))

;; (let x (let y e1 e2) e3)
;; ------------------------ LetLet
;; (let y e1 (let x e2 e3))
(defn rule-let-let [node]
  (if (instance? Let node)
    (let [expression (.-expression node)]
      (if (instance? Let expression)
        (m.ir/let (.-identifier ^Let expression) (.-expression ^Let expression)
          (m.ir/let (.-identifier ^Let node) (.-body ^Let expression)
            (.-body ^Let node))))
      node)
    node))

;;     (let x (test e1 e2 e3) e4)
;; ------------------------------------ LetTest
;; (test e1 (let x e2 e4) (let x e3 e4)
(defn rule-let-test [node path]
  (if (instance? Let node)
    (let [expression (.-expression ^Let node)]
      (if (instance? Test expression)
        (m.ir/test (.-test ^Test expression)
          (m.ir/let (.-identifier ^Let node) (.-then ^Test expression)
            (.-body ^Let node))
          (m.ir/let (.-identifier ^Let node) (.-else ^Test expression)
            (.-body ^Let node)))
        node))
    node))

(defn pass-commute [node]
  (m.ir/top-down-pass
   (some-fn rule-bind-let
            rule-bind-test
            rule-let-let
            rule-let-test)  
   node))

(extend-protocol ISimplify
  Call
  (-simplify [this loc]
    this
    #_
    (let [args (.-arguments (.-arguments this))]
      ;; TODO: Fail nodes should not arrive here.
      (if-some [fail (some (fn [x] (if (m.ir/fail? x) x)) args)]
        fail
        (let [[initial-args [test & rest-args]] (split-with (complement m.ir/test?) args)]
          ;;       (call f [a* (test x y z) b *])
          ;; ----------------------------------------------
          ;; (test x (call f [a* y b*]) (call f [a* z b*]))
          (if test
            (m.ir/test (.-test ^Test test)
              (m.ir/call (.-f this) `[~@initial-args ~(.-then ^Test test) ~@rest-args])
              (m.ir/call (.-f this) `[~@initial-args ~(.-else ^Test test) ~@rest-args]))
            (let [[initial-args [let & rest-args]] (split-with (complement m.ir/let?) args)]
              (if let
                (m.ir/let (.-identifier ^Let let) (.-expression ^Let let)
                  (m.ir/call (.-f this) `[~@initial-args ~(.-body ^Let let) ~@rest-args]))
                this)))))))

  Join
  (-simplify [this loc]
    (m.ir/pick (.-ma this) (.-mb this)))

  Let
  (-simplify [this loc]
    (let [expression (.-expression this)]
      (cond
        (m.ir/let? expression)
        (rule-let-let this)
        (m.ir/test? expression)
        (rule-let-test this)

        :else
        this)))

  Pick
  (-simplify [this loc]
    (let [ma (.-ma this)
          mb (.-mb this)]
      (cond
        ;; (pick (let x e1 e2) e3)
        ;; ----------------------- PickLeftLet
        ;; (let x e1 (pick e2 e3))
        (m.ir/let? ma)
        (m.ir/let (.-identifier ^Let ma) (.-expression ^Let ma)
          (m.ir/join (.-body ^Let ma) mb))

        ;; (pick e1 (let x e2 e3))
        ;; ----------------------- PickRightLet
        ;; (let x e2 (pick e1 e3))
        (m.ir/let? mb)
        (m.ir/let (.-identifier ^Let mb) (.-expression ^Let mb)
          (m.ir/join ma (.-body ^Let mb)))

        ;; (pick (test e1 e2 e3) e4)
        ;; ------------------------- PickLeftTest
        ;; (test e1 e2 (pick e3 e4))
        (m.ir/test? ma)
        (m.ir/test (.-test ^Test ma)
          (.-then ^Test ma)
          (m.ir/pick (.-else ^Test ma) mb))

        ;; (pick (pick e1 e2) e3)
        ;; ---------------------- PickLeftPick
        ;; (pick e1 (pick e2 e3))
        (m.ir/pick? ma)
        (m.ir/pick (.-ma ^Pick ma)
                        (m.ir/pick (.-mb ^Pick ma) mb))

        :else
        this)))

  Test
  (-simplify [this loc]
    ;;      (test (test a b c) d e)
    ;; ----------------------------------
    ;; (test a (test b d e) (test c d e))
    (let [test (.-test this)]
      (if (m.ir/test? test)
        (m.ir/test (.-test test)
          (m.ir/test (.-then test)
            (.-then this)
            (.-else this))
          (m.ir/test (.-else test)
            (.-then this)
            (.-else this)))
        this))))

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
      (if-some [identifier (reverse-resolve this-expression loc)]
        (m.ir/postwalk-replace {this-identifier identifier} this-body)
        (let [uses (count (filter #{this-identifier} (m.ir/subnodes this-body)))]
          (case uses 
            0 this-body

            1 (m.ir/postwalk-replace {this-identifier this-expression} this-body)

            ;; else
            this)))))

  Test
  (-prune [this loc]
    (let [test (.-test this)
          then (.-then this)
          else (.-else this)]
      (if (= then else)
        then
        (if-some [parent-test (test-resolve test loc)]
          (if (some #{this} (m.ir/subnodes (.-then ^Test parent-test)))
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
