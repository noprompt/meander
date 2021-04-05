(ns meander.ir.zeta
  {:no-doc true}
  (:refer-clojure :exclude [let test])
  (:require [#?(:clj clojure.core, :cljs cljs.core) :as clojure]
            [clojure.zip :as zip]
            [meander.protocols.zeta :as m.protocols]))

(defprotocol IBranch)
(defprotocol IForm
  (form [this]))

;; Node types
;; ---------------------------------------------------------------------

(defrecord Identifier [symbol]
  IForm
  (form [this]
    `(identifier '~symbol)))

(defrecord Bind [identifier expression body]
  IBranch

  IForm
  (form [this]
    `(bind ~(form identifier) ~(form expression)
       ~(form body)))

  m.protocols/IChildren
  (children [this]
    [expression body])

  m.protocols/IMakeNode
  (make-node [this [new-expression new-body]]
    (Bind. identifier new-expression new-body)))

(defrecord Code [code]
  IForm
  (form [this]
    `(code ~code)))

(defrecord Data [data]
  IForm
  (form [this]
    `(data ~data)))

(defrecord Dual [is is-not]
  IBranch

  IForm
  (form [this]
    `(dual ~(form is) ~(form is-not)))

  m.protocols/IChildren
  (children [this]
    [is is-not])

  m.protocols/IMakeNode
  (make-node [this [new-is new-is-not]]
    (Dual. new-is new-is-not)))

(defrecord Bindings [bindings]
  IBranch

  IForm
  (form [this]
    `(bindings ~(into {} (map (fn [[k v]] [(form k) (form v)])) bindings)))

  m.protocols/IChildren
  (children [this]
    (vec (vals bindings)))

  m.protocols/IMakeNode
  (make-node [this new-vals]
    (Bindings. (zipmap (keys bindings) new-vals))))

(defrecord State [object bindings]
  IBranch

  IForm
  (form [this]
    `(state ~(form object) ~(form bindings)))

  m.protocols/IChildren
  (children [this]
    [object bindings])

  m.protocols/IMakeNode
  (make-node [this [new-object new-variables]]
    (State. new-object new-variables)))

(defrecord Fail [state]
  IForm
  (form [this]
    `(fail ~(form state)))

  ;; IBranch

  ;; m.protocols/IChildren
  ;; (children [this]
  ;;   [state])

  ;; m.protocols/IMakeNode
  ;; (make-node [this [new-state]]
  ;;   (Fail. new-state))
  )

(defrecord Pass [state]
  IBranch

  IForm
  (form [this]
    `(pass ~(form state)))

  m.protocols/IChildren
  (children [this]
    [state])

  m.protocols/IMakeNode
  (make-node [this [new-state]]
    (Pass. new-state)))

(defrecord Let [identifier expression body]
  IBranch

  IForm
  (form [this]
    `(let ~(form identifier) ~(form expression)
       ~(form body)))

  m.protocols/IChildren
  (children [this]
    [expression body])

  m.protocols/IMakeNode
  (make-node [this [new-expression new-body]]
    (Let. identifier new-expression new-body)))

(defrecord Arguments [arguments]
  IBranch

  IForm
  (form [this]
    `[~@(map form arguments)])

  m.protocols/IChildren
  (children [this]
    arguments)

  m.protocols/IMakeNode
  (make-node [this new-arguments]
    (Arguments. new-arguments)))

(defrecord Call [f arguments]
  IBranch

  IForm
  (form [this]
    `(call ~(form f) ~(form arguments)))

  m.protocols/IChildren
  (children [this]
    [f arguments])

  m.protocols/IMakeNode
  (make-node [this [new-f new-arguments]]
    (Call. new-f new-arguments)))

(defrecord Fabricate [state]
  IBranch

  IForm
  (form [this]
    `(fabricate ~(form state)))

  m.protocols/IChildren
  (children [this]
    [state])

  m.protocols/IMakeNode
  (make-node [this [new-state]]
    (Fabricate. new-state)))

(defrecord GetObject [state]
  IBranch

  IForm
  (form [this]
    `(get-object ~(form state)))

  m.protocols/IChildren
  (children [this]
    [state])

  m.protocols/IMakeNode
  (make-node [this [new-state]]
    (GetObject. new-state)))

(defrecord GetReference [state reference]
  IBranch

  m.protocols/IChildren
  (children [this]
    [state])

  m.protocols/IMakeNode
  (make-node [this [new-state]]
    (GetReference. new-state reference)))

(defrecord GetBinding [state identifier none]
  IBranch

  IForm
  (form [this]
    `(get-binding ~(form state) ~(form identifier) ~(form none)))

  m.protocols/IChildren
  (children [this]
    [state none])

  m.protocols/IMakeNode
  (make-node [this [new-state new-none]]
    (GetBinding. new-state identifier new-none)))

(defrecord GetBindings [state]
  IBranch

  IForm
  (form [this]
    `(get-bindings ~(form state)))

  m.protocols/IChildren
  (children [this]
    [state])

  m.protocols/IMakeNode
  (make-node [this [new-state]]
    (GetBindings. new-state)))

(defrecord Join [ma mb]
  IBranch

  IForm
  (form [this]
    `(join ~(form ma) ~(form mb)))

  m.protocols/IChildren
  (children [this]
    [ma mb])

  m.protocols/IMakeNode
  (make-node [this [new-ma new-mb]]
    (Join. new-ma new-mb)))

(defrecord Mint [state]
  IBranch

  IForm
  (form [this]
    `(mint ~(form state)))

  m.protocols/IChildren
  (children [this]
    [state])

  m.protocols/IMakeNode
  (make-node [this [new-state]]
    (Mint. new-state)))

(defrecord Pick [ma mb]
  IBranch

  IForm
  (form [this]
    `(pick ~(form ma) ~(form mb)))

  m.protocols/IChildren
  (children [this]
    [ma mb])

  m.protocols/IMakeNode
  (make-node [this [new-ma new-mb]]
    (Pick. new-ma new-mb)))

(defrecord Scan [identifier expression body]
  IBranch

  IForm
  (form [this]
    `(scan ~(form identifier) ~(form expression) ~(form body)))

  m.protocols/IChildren
  (children [this]
    [expression body])

  m.protocols/IMakeNode
  (make-node [this [new-expression new-body]]
    (Scan. identifier new-expression new-body)))

(defrecord SetObject [state value]
  IBranch

  IForm
  (form [this]
    `(set-object ~(form state) ~(form value)))

  m.protocols/IChildren
  (children [this]
    [state value])

  m.protocols/IMakeNode
  (make-node [this [new-state new-value]]
    (SetObject. new-state new-value)))

(defrecord SetBinding [state identifier value]
  IBranch

  IForm
  (form [this]
    `(set-binding ~(form state) ~(form identifier) ~(form value)))

  m.protocols/IChildren
  (children [this]
    [state identifier value])

  m.protocols/IMakeNode
  (make-node [this [new-state identifier new-value]]
    (SetBinding. new-state identifier new-value)))

(defrecord Star [state identifier body]
  IBranch

  IForm
  (form [this]
    `(star ~(form state) ~(form identifier) ~(form body)))

  m.protocols/IChildren
  (children [this]
    [state body])

  m.protocols/IMakeNode
  (make-node [this [new-state new-value]]
    (Star. new-state identifier new-value)))

(defrecord Test [test then else]
  IBranch

  IForm
  (form [this]
    `(test ~(form test)
       ~(form then)
       ~(form else)))

  m.protocols/IChildren
  (children [this]
    [test then else])

  m.protocols/IMakeNode
  (make-node [this [new-test new-then new-else]]
    (Test. new-test new-then new-else)))

(defrecord With [state mapping identifier body]
  IBranch
  IForm
  (form [this]
    `(with ~(form state) ~(form mapping) ~(form identifier) ~(form body)))

  m.protocols/IChildren
  (children [this]
    [state mapping body])

  m.protocols/IMakeNode
  (make-node [this [new-state new-mapping new-body]]
    (With. new-state new-mapping identifier new-body)))

;; API
;; ---------------------------------------------------------------------

(defn identifier
  ([] (->Identifier (gensym "i__")))
  ([symbol]
   {:pre [(symbol? symbol)]}
   (->Identifier symbol)))

(defn identifier? [x]
  (instance? Identifier x))

(defn code [x]
  (->Code x))

(defn data [x]
  (->Data x))

(defn data? [x]
  (instance? Data x))

(defn dual [is-node is-not-node]
  (->Dual is-node is-not-node))

(defn pass [state]
  (->Pass state))

(defn pass? [x]
  (instance? Pass x))

(defn fail [state]
  (->Fail state))

(defn fail? [x]
  (instance? Fail x))

(defn bindings
  ([] (->Bindings {}))
  ([map] (->Bindings map)))

(defn bindings?
  [x]
  (instance? Bindings x))

(defn bind
  {:style/indent 2}
  [identifier expression body]
  {:pre [(identifier? identifier)]}
  (->Bind identifier expression body))

(defn bind? [x]
  (instance? Bind x))

(defn fabricate [state]
  (->Fabricate state))

(defn join [ma mb]
  (->Join ma mb))

(defn join?
  [x]
  (instance? Join x))

(defn let
  {:style/indent 2}
  [identifier expression body]
  {:pre [(identifier? identifier)]}
  (->Let identifier expression body))

(defn let?
  [x]
  (instance? Let x))

(defn call [f arguments]
  {:pre [(sequential? arguments)]}
  (->Call f (->Arguments (vec arguments))))

(defn get-object [state-node]
  (->GetObject state-node))

(defn get-binding
  [state identifier none]
  {:pre [(identifier? identifier)]}
  (->GetBinding state identifier none))

(defn get-bindings [state]
  (->GetBindings state))

(defn get-bindings? [x]
  (instance? GetBindings x))

(defn scan
  {:style/indent 2}
  [identifier expression body]
  {:pre [(identifier? identifier)]}
  (->Scan identifier expression body))

(defn seed [object]
  (->State object (->Bindings {})))

(defn set-object [state object]
  (->SetObject state object))

(defn set-binding
  {:style/indent 2}
  [state identifier value]
  {:pre [(identifier? identifier)]}
  (->SetBinding state identifier value))

(defn state
  [object bindings]
  {:pre [(bindings? bindings)]}
  (->State object bindings))

(defn state?
  [x]
  (instance? State x))

(defn star
  {:style/indent 2}
  [state identifier body]
  {:pre [(identifier? identifier)]}
  (->SetBinding state identifier body))

(defn test
  {:style/indent 1}
  [test then else]
  (->Test test then else))

(defn test?
  [x]
  (instance? Test x))

(defn with
  {:style/indent 3}
  [state mapping identifier body]
  (->With state mapping identifier body))

(defn mint [state]
  (->Mint state))

(defn pick [ma mb]
  (->Pick ma mb))

(defn pick?
  [x]
  (instance? Pick x))

(defn branch? [x]
  (and (satisfies? IBranch x)
       (boolean (seq (m.protocols/children x)))))

(defn postwalk [f node]
  (if (branch? node)
    (f (m.protocols/make-node node (mapv (partial postwalk f) (m.protocols/children node))))
    (f node)))

(defn postwalk-replace [smap node]
  (postwalk (fn [node] (get smap node node)) node))

(defn subnodes [root]
  (tree-seq branch? m.protocols/children root))

(defn proper-subnodes [root]
  (next (subnodes root)))

(defn child? [root node]
  (boolean (some (fn [ir] (= ir node)) (proper-subnodes root))))

(defn zipper [root]
  (zip/zipper branch? m.protocols/children m.protocols/make-node root))

(defn top-down-pass*
  {:private true}
  [f node path]
  (clojure/let [node* (f node path)]
    (if (branch? node*)
      (clojure/let [path* (cons node* path)
                    children* (mapv (fn [child-node]
                                      (top-down-pass* f child-node path*))
                                    (m.protocols/children node*))]
        (m.protocols/make-node node* children*))
      node*)))

(defn top-down-pass [f node]
  (top-down-pass* f node ()))
