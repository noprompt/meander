(ns ^:no-doc meander.match.ir.epsilon
  "Functions for working with the Meander's match compiler
  intermediate representation (IR)."
  (:refer-clojure :exclude [compile merge])
  #?(:cljs (:require-macros [meander.match.ir.epsilon :refer [defop]]))
  (:require
   [clojure.core :as clj]
   [clojure.spec.alpha :as s]
   [clojure.spec.gen.alpha :as s.gen]
   [clojure.walk :as walk]
   [clojure.zip :as zip]
   [meander.util.epsilon :as r.util]
   [meander.match.runtime.epsilon :as r.match.runtime]
   [meander.syntax.epsilon :as r.syntax]
   #?(:cljs [goog.array])))


(def SeqInterface
  #?(:clj clojure.lang.ISeq
     :cljs ISeq))

(def SetInterface
  #?(:clj clojure.lang.IPersistentSet
     :cljs ISet))

(def VectorInterface
  #?(:clj clojure.lang.IPersistentVector
     :cljs IVector))

(def MapInterface
  #?(:clj clojure.lang.IPersistentMap
     :cljs IMap))

(def ^{:doc "A macro &env map. Used to make platform specific
compilation decisions."
       :dynamic true
       :private true}
  *env* {})

(defn breadth-first?
  "`true` if the current IR compilation environment `*env*` specifies
  a `:search-order` of `:breadth-first`."
  []
  (= (get *env* :search-order) :breadth-first))

;; ---------------------------------------------------------------------
;; AST API

;; A node is only required to have and :op key.
(s/def ::node
  (s/keys :req-un [::op]))

(s/def ::op
  keyword?)

(defn node?
  "true if x is a ::node, false otherwise."
  [x]
  (and (map? x) (contains? x :op)))

(s/fdef node?
  :args (s/cat :x any?)
  :ret boolean?)

(def
  ^{:arglists '([node])
    :doc "Return the ::op of node."}
  op :op)

(s/fdef op
  :args (s/cat :node ::node)
  :ret ::op)

(defn child-keys
  "Return the keys of node which have a value that is a ::node."
  {:private true}
  [node]
  (keep
   (fn [[k v]]
     (when (some? (:op v))
       k))
   node))

(s/fdef child-keys
  :args (s/cat :node ::node)
  :ret (s/coll-of any? :kind sequential? :into []))

(defn children
  "Return the child nodes of node, a sequence of ::node."
  [node]
  (case (op node)
    :branch
    (:arms node)

    ;; else
    (map node (child-keys node))))

(defn branch?
  "true if node has any children, false otherwise."
  [node]
  (some? (seq (children node))))

(defn nodes
  "Return all subnodes of node, a sequence of ::node."
  [node]
  (tree-seq branch? children node))

(defn height
  "Return the height of node."
  [node]
  (if-some [nodes (children node)]
    (transduce (comp (map height)
                     (map inc))
               max
               1
               nodes)
    1))

(defn make-node [node new-children]
  (case (op node)
    :branch
    (assoc node :arms new-children)

    ;; else
    (into node (map vector (child-keys node) new-children))))

(defn zipper [node]
  (zip/zipper branch? children make-node node))

(defn walk [inner outer node]
  (case (:op node)
    :branch
    (outer (assoc node :arms (doall (map inner (:arms node)))))

    ;; else
    (outer
     (reduce
      (fn [node* k]
        (assoc node* k (inner (get node k))))
      node
      (child-keys node)))))

(defn postwalk
  [f node]
  (walk (fn [x]
          (let [y (f x)]
            (if (reduced? y)
              (deref y)
              (postwalk f y))))
        f
        node))

(defn prewalk
  [f node]
  (let [x (f node)]
    (if (reduced? x)
      (deref x)
      (walk (partial prewalk f) identity x))))

;; ---------------------------------------------------------------------
;; AST constructors
;;
;; TODO: create :resolve node for symbols
;; TODO: replace :eval with :resolve where possible

(defmacro defop [symbol op params & body]
  (let [symbol (vary-meta symbol assoc
                          :arglists `'(~params)
                          :style/indent :defn)]
    `(defn ~symbol ~params
       ~(if (seq body)
          `(assoc (do ~@body) :op ~op)
          `(hash-map :op ~op
                     ~@(mapcat
                        (fn [param]
                          (when (not= 'op param)
                            [(keyword (name param)) param]))
                        params))))))

(defop op-bind :bind [symbol value then])

(defop op-apply :apply [target fn-expr body-fn]
  (let [result-symbol (gensym "result__")]
    {:op :apply
     :target target
     :fn-expr fn-expr
     :symbol result-symbol
     :then (body-fn result-symbol)}))

(defn op-branch [arms]
  {:op :branch
   :arms (vec arms)})

(defn op-case
  {:style/indent :defn}
  [target clauses else]
  {:op :case
   :target target
   :clauses clauses
   :then else})

(s/def ::op-case-clause
  (s/tuple (s/or :node ::node
                 :node-set (s/coll-of ::node :kind set?))
           ::node))

(s/fdef op-case
  :args (s/cat :target ::node
               :clauses (s/coll-of ::op-case-clause :kind sequential?)
               :then ::node)
  :ret ::node)

(defop op-drop :drop [target n kind])

(defop op-eval :eval [form])

(s/def :meander.match.ir.epsilon.op-eval/form
  any?)

(s/def :meander.match.ir.epsilon/op-eval
  (s/keys :req-un [:meander.match.ir.epsilon.op-eval/form]))

(defop op-check :check [test then])

(defop op-check-array :check-array [target then])

(defop op-check-array-equals :check-array-equals [target-1 target-2 then])

(defop op-check-boolean :check-boolean [test then])

(defop op-check-bounds :check-bounds [target length kind then])

(defop op-check-empty :check-empty [target then])

(defop op-check-equal :check-equal [target-1 target-2 then])

(defop op-check-lit :check-lit [target value then])

(s/fdef op-check-lit
  :args (s/cat :target ::node
               :value ::node
               :then ::node)
  :ret ::node)

(defop op-check-map :check-map [target then])

(defop op-check-seq :check-seq [target then])

(defop op-check-set :check-set [target then])

(defop op-check-vector :check-vector [target then])

;; TODO: No need for :symbol.
(defop op-find :find [symbol value body])

(defop op-load :load [id])

(defop op-lookup :lookup [target key])

(defop op-lvr-check :lvr-check [symbol target then])

(defop op-lvr-bind :lvr-bind [symbol target then])

(defop op-mut-bind :mut-bind [symbol target then])

(defop op-mvr-append :mvr-append [symbol target then])

(defop op-mvr-bind :mvr-bind [symbol target then])

(defop op-mvr-init :mvr-init [symbol then])

(defop op-nth :nth [target index])

(defop op-pass :pass [then])

(defop op-return :return [value])

(s/def :meander.match.ir.epsilon.op-return/value
  any?)

(s/def :meander.match.ir.epsilon/op-return
  (s/keys :req-un [:meander.match.ir.epsilon.op-return/value]))

(defop op-save :save [id body-1 body-2])

;; TODO: No need for :symbol.
(defop op-search :search [symbol value body])

(defop op-star :star [input n kind return-symbols body-fn then]
  (let [input-symbol (gensym "input__")]
    {:input-symbol input-symbol
     :input input
     :body (body-fn input-symbol (op-return (vec return-symbols)))
     :then then
     :kind kind
     :n n
     :return-symbols (vec return-symbols)}))

(defop op-plus :plus [input n m kind return-symbols body-fn then]
  (let [input-symbol (gensym "input__")]
    {:input-symbol input-symbol
     :input input
     :body (body-fn input-symbol (op-return (vec return-symbols)))
     :then then
     :kind kind
     :n n
     :m m
     :return-symbols (vec return-symbols)}))

(defop op-take :take [target n kind])

(defop op-fail :fail [])

(defn op=
  {:arglists '([node op])}
  [x k]
  (and (node? x) (= (op x) k)))

(defn op-fail?
  "`true` if `x` is an :fail node, `false` otherwise."
  [x]
  (op= x :fail))

(defn op-pass?
  "`true` if `x` is an :pass node, `false` otherwise."
  [x]
  (op= x :pass))

(defn check?
  [x]
  (and (node? x)
       (case (op x)
         (:check
          :check-array
          :check-array-equals
          :check-boolean
          :check-bounds
          :check-empty
          :check-equal
          :check-map
          :check-seq
          :check-set
          :check-vector
          :lvr-check)
         true

         ;; else
         false)))

;; ---------------------------------------------------------------------
;; AST Rewriting

(defmulti merge*
  "Attempts to merge two nodes into one node returning node* if
  successful and ::merge-fail otherwise."
  {:arglists '([node-a node-b])}
  (fn [a b]
    (if (= a b)
      ::merge-equal
      [(op a) (op b)]))
  :default ::merge-fail)

(s/fdef merge*
  :args (s/cat :node-a ::node
               :node-b ::node)
  :ret (s/or :node ::node
             :fail #{::merge-fail}))

(defmethod merge* ::merge-equal [a _]
  a)

(defmethod merge* ::merge-fail [_ _]
  ::merge-fail)

(defn do-merge
  "Attempts to merge two nodes `a` and `b` into a new node.  If the
  merge is successful the function `then` is invoked with the new
  node. If the merge fails and the optional function `else` was
  passed, then `else` is invoked with `a` and `b`. If the merge fails
  and `else` was not passed, then the value `::merge-fail` is
  returned."
  ([a b then]
   (let [x (merge* a b)]
     (if (= ::merge-fail x)
       x
       (then x))))
  ([a b then else]
   (let [x (merge* a b)]
     (if (= ::merge-fail x)
       (else a b)
       (then x)))))

(s/fdef do-merge
  :args (s/alt :a1 (s/cat :a ::node
                          :b ::node
                          :then fn?)
               :a2 (s/cat :a ::node
                          :b ::node
                          :then fn?
                          :else fn?))
  :ret (s/or :node ::node
             :fail #{::merge-fail}))

(defn merge-all
  "Attempts to successively merge all nodes in `nodes` from left to
  right."
  [nodes]
  (case (int (bounded-count 2 nodes))
    (0 1)
    nodes

    ;; else
    (reduce
     (fn [nodes* node]
       (do-merge (peek nodes*) node
                 (fn [node*]
                   (conj (pop nodes*) node*))
                 (fn [_ _]
                   (conj nodes* node))))
     [(first nodes)]
     (rest nodes))))

(s/fdef merge-all
  :args (s/cat :nodes (s/coll-of ::node :kind sequential? :into []))
  :ret (s/coll-of ::node :kind sequential? :into []))

(defmethod merge* [:apply :apply] [a b]
  (if (and (= (:target a) (:target b))
           (= (:fn-expr a) (:fn-expr b)))
    (let [then-b* (walk/postwalk-replace {(:symbol b) (:symbol a)} (:then b))]
      (do-merge (:then a) then-b*
                (fn [then-a*]
                  (assoc a :then then-a*))))

    ::merge-fail))

(defmethod merge* [:bind :bind] [a b]
  (if (= (:value a)
         (:value b))
    (let [then-b* (walk/postwalk-replace {(:symbol b) (:symbol a)} (:then b))]
      (do-merge (:then a) then-b*
                (fn [then-a*] (assoc a :then then-a*))))
    ::merge-fail))

(defmethod merge* [:branch :branch] [a b]
  (op-branch (merge-all
              (mapcat
               (fn [node]
                 (if (op= node :branch)
                   (:arms node)
                   (list node)))
               (concat (:arms a) (:arms b))))))

(defmethod merge* [:check-lit :check-lit] [a b]
  (if (= (:target a) (:target b))
    (if (= (:value a) (:value b))
      (do-merge (:then a) (:then b)
                (fn [then-a*] (assoc a :then then-a*)))
      (op-case (:target a)
        [[(:value a) (:then a)]
         [(:value b) (:then b)]]
        (op-fail)))
    ::merge-fail))

(defmethod merge* [:check-bounds :check-bounds] [a b]
  (if (and (= (:target a) (:target b))
           (= (:kind a) (:kind b))
           (= (:length a) (:length b)))
    (do-merge (:then a) (:then b)
              (fn [then-a*] (assoc a :then then-a*)))
    ::merge-fail))

(defmethod merge* [:case :check-lit] [a b]
  (if (= (:target a) (:target b))
    (op-case (:target a)
      (conj (vec (:clauses a)) [(:value b) (:then b)])
      (:then a))
    ::merge-fail))

(defmethod merge* [:check-lit :case] [a b]
  (if (= (:target a) (:target b))
    (op-case (:target b)
      (cons [(:value a) (:then a)] (:clauses b))
      (:then b))
    ::merge-fail))

(defmethod merge* [:lvr-bind :lvr-bind] [a b]
  (if (and (= (:symbol a) (:symbol b))
           (= (:target a) (:target b)))
    (do-merge (:then a) (:then b)
              (fn [then-a*]
                (assoc a :then then-a*)))
    ::merge-fail))

(defn merge-check-coll*
  {:private true}
  [a b]
  (if (= (:target a)
         (:target b))
    (do-merge (:then a) (:then b)
              (fn [then-a*]
                (assoc a :then then-a*))
              (fn [_ _]
                (assoc a :then (op-branch [(:then a) (:then b)]))))
    ::merge-fail))

(defmethod merge* [:check-array :check-array] [a b]
  (merge-check-coll* a b))

(defmethod merge* [:check-map :check-map] [a b]
  (merge-check-coll* a b))

(defmethod merge* [:check-seq :check-seq] [a b]
  (merge-check-coll* a b))

(defmethod merge* [:check-set :check-set] [a b]
  (merge-check-coll* a b))

(defmethod merge* [:check-vector :check-vector] [a b]
  (merge-check-coll* a b))

(defmethod merge* [:search :search] [a b]
  (if (and (= (get a :symbol)
              (get b :symbol))
           (= (get a :value)
              (get b :value)))
    (assoc a :body (op-branch [(get a :body) (get b :body)]))
    ::merge-fail))

(defmulti merge
  "Attempt to merge the nodes `a` and `b`. Returns the result of the merge
  if the merge succeeds. Returns a `:branch` node with `:arms` `[a b]`
  if the merge fails."
  {:arglists '([a b])}
  (fn [a b]
    (if (= (op a) (op b))
      (op a)
      ::default))
  :default ::default)

(defmethod merge ::default
  [a b]
  (do-merge a b identity (fn [a b] (op-branch [a b]))))


;; :apply rewriting
;; ----------------

(defn rewrite-beta-reduce-apply
  {:private true}
  [apply-node]
  (let [fn-expr (get apply-node :fn-expr)]
    (if (get (meta fn-expr) :meander.epsilon/beta-reduce)
      (let [[_fn [arg] & body] fn-expr
            target (get apply-node :target)]
        {:op :bind
         :symbol arg
         :value {:op :eval :form target}
         :then {:op :bind
                :symbol (get apply-node :symbol)
                :value {:op :eval
                        :form (case (int (count body))
                                0 nil
                                1 (first body)
                                ;; else
                                `(do ~@body))}
                :then (get apply-node :then)}})
      apply-node)))

;; :branch rewriting
;; -----------------

(defn rewrite-branch-one-case
  {:private true}
  [node]
  (if (op= node :branch)
    (if (= 1 (count (:arms node)))
      (first (:arms node))
      node)
    node))

(defn rewrite-branch-splice-branches
  {:private true}
  [node]
  (if (op= node :branch)
    (assoc node :arms (mapcat
                       (fn [node]
                         (if (op= node :branch)
                           (:arms node)
                           (list node)))
                       (:arms node)))
    node))

(defn rewrite-branch-one-fail
  {:private true}
  [node]
  (if (op= node :branch)
    (if (some op-fail? (:arms node))
      (assoc node :arms (conj (into [] (remove op-fail?) (:arms node))
                              (op-fail)))
      node)
    node))

(defn rewrite-branch-merge
  {:private true}
  [node]
  (if (op= node :branch)
    (op-branch (merge-all (:arms node)))
    node))

(defn rewrite-branch-check-lits-to-case
  [node]
  (if (op= node :branch)
    (case (count (:arms node))
      (0 1)
      node

      2
      (let [[node-a node-b] (:arms node)]
        (if (and (op= node-a :check-lit)
                 (op= node-b :check-lit)
                 (= (:target node-a)
                    (:target node-b)))
          (op-case (:target node-a)
            (mapv (juxt :value :then) (:arms node))
            (op-fail))
          node))

      ;; else
      (or (some
           (fn [[a b]]
             (let [[node & rest-nodes] b]
               (if (= (:op node) :check-lit)
                 (let [target (:target node)
                       [r1 r2] (split-with
                                (fn [other-node]
                                  (and (= (:op other-node) :check-lit)
                                       (= (:target other-node) target)))
                                rest-nodes)]
                   (if (<= 1 (count r1))
                     (op-branch (conj (vec a)
                                      (op-case target
                                        (map (juxt :value :then) (cons node r1))
                                        (op-branch r2)))))))))
           (r.match.runtime/partitions 2 (:arms node)))
          node))
    node))

;; :case rewriting
;; ---------------

(defn ungroup-case
  [node]
  (if (op= node :case)
    (assoc node :clauses
           (mapcat
            (fn [[test clause]]
              (if (set? test)
                (map vector test (repeat clause))
                (list [test clause])))
            (:clauses node)))
    node))

(defn rewrite-case-duplicate-actions
  [node]
  (if (op= node :case)
    (let [node* (ungroup-case node)
          clauses (:clauses node*)
          tests (mapv first clauses)]
      (if (= tests (distinct tests))
        node
        (let [clauses* (map
                        (fn [index test]
                          [test (op-branch (map second (get index test)))])
                        (repeat (group-by first clauses))
                        (distinct tests))]
          (assoc node :clauses clauses*))))
    node))

;; :def rewriting
;; --------------

(defn def-remove-unused
  [node]
  (let [call-symbols (into #{}
                           (comp (filter (comp #{:call} op))
                                 (map :symbol))
                           (nodes node))]
    (loop [loc (zipper node)]
      (if (zip/end? loc)
        (zip/root loc)
        (let [node (zip/node loc)]
          (recur
           (case (op node)
             :def
             (if (contains? call-symbols (:symbol node))
               (zip/next loc)
               (zip/replace loc (:then node)))

             ;; else
             (zip/next loc))))))))

;; :lookup rewriting
;; -----------------

(defn rewrite-map-lookups-once [node]
  {:pre [(op= node :check-map)]}
  (let [map-target (get node :target)
        bind-nodes (filter
                    (fn [subnode]
                      (and (= (get subnode :op) :bind)
                           (let [bind-value (get subnode :value)]
                             (and (= (get bind-value :op) :lookup)
                                  (= map-target (get bind-value :target))))))
                    (nodes node))
        lookup-nodes (map
                      (fn [bind-node]
                        (get bind-node :value))
                      bind-nodes)
        lookup-count (frequencies lookup-nodes)
        lookup-symbols (reduce
                        (fn [index bind-node]
                          (update index (get bind-node :value) (fnil conj #{}) (get bind-node :symbol)))
                        {}
                        bind-nodes)
        symbol-smap (reduce
                     (fn [smap [lookup-node lookup-count]]
                       (if (< 1 lookup-count)
                         (let [old-symbols (lookup-symbols lookup-node)
                               new-symbol (gensym "VAL__")]
                           (into smap (map vector old-symbols (repeat new-symbol))))
                         smap))
                     {}
                     lookup-count)
        ;; Remove old bindings
        inner-node (prewalk
                    (fn [subnode]
                      (if (and (= (get subnode :op) :bind)
                               (< 1 (get lookup-count (get subnode :value) 0)))
                        (get subnode :then)
                        subnode))
                    (get node :then))
        ;; Rename symbol
        inner-node (walk/postwalk-replace symbol-smap inner-node)
        ;; Create new bindings
        inner-node (reduce
                    (fn [inner-node [lookup-node lookup-symbols]]
                      (if-some [new-symbol (some symbol-smap lookup-symbols)]
                        (op-bind new-symbol lookup-node
                          inner-node)
                        inner-node))
                    inner-node
                    lookup-symbols)]
    (assoc node :then inner-node)))

;; :mvr rewriting
;; --------------

(defn rewrite-move-mvr-init-to-top-level
  [node]
  (reduce
   (fn [_ loc]
     (let [node (zip/node loc)]
       (case (:op node)
         :mvr-init
         (let [node* (zip/root (zip/edit loc :then))]
           (reduced (assoc node :then (rewrite-move-mvr-init-to-top-level node*))))
         ;; else
         node)))
   node
   (r.match.runtime/zip-next-seq (zipper node))))

#_
(defn rewrite-save
  "Remove useless save nodes from ir."
  [ir]
  (let [ir* (loop [loc (ir-zip ir)]
              (if (zip/end? loc)
                (zip/root loc)
                (let [node (zip/node loc)]
                  (case (:op node)
                    :save
                    (let [body-1 (:body-1 node)]
                      (if (some check? (nodes body-1))
                        (recur (zip/next loc))
                        (recur (zip/replace loc body-1))))

                    ;; else
                    (recur (zip/next loc))))))]
    (if (= ir ir*)
      ir
      (recur ir*))))

(defn rewrite*
  [node]
  (prewalk
   (comp
    (fn f [node]
      (case (op node)
        :apply
        (rewrite-beta-reduce-apply node)

        :branch
        (rewrite-branch-one-case node)

        :check-map
        (rewrite-map-lookups-once node)

        ;; else
        node))
    (fn g [node]
      (case (op node)
        :branch
        (-> node
            rewrite-branch-one-fail
            rewrite-branch-merge
            rewrite-branch-splice-branches
            rewrite-branch-check-lits-to-case)
        ;; else
        node))
    (fn h [node]
      (case (op node)
        :bind
        (let [then-node (:then node)]
          (if (and (op= then-node :bind)
                   (= (:value node)
                      (:value then-node)))
            (let [then* (walk/postwalk-replace
                         {(:symbol then-node) (:symbol node)}
                         (:then then-node))]
              (assoc node :then then*))
            node))
        :case
        (rewrite-case-duplicate-actions node)

        ;; else
        node)))
   node))

;; ---------------------------------------------------------------------
;; Type based rewrites

(def
  ^{:private true}
  type-not-found
  :meander.epsilon/type-not-found)

(defn type-not-found?
  {:private true}
  [type]
  (not= type type-not-found))

;; We don't currently track variable rebinding.
;; Might be worthwhile in the future
(defn lookup-type
  {:private true}
  [env sym]
  (get-in @env [sym :type] type-not-found))

;; We add :type because it may be worthwhile to store other sorts of
;; information (length, known keys, etc).
(defn add-type-to-env
  {:private true}
  [env sym type]
  (when type
    (swap! env assoc-in [sym :type] type)))

(defn eliminate-check-known-type
  {:private true}
  [env node known-type]
  (let [type (lookup-type env (get-in node [:target :form]))]
    (cond
      (isa? type known-type) (:then node)
      (type-not-found? type) (op-fail)
      :else node)))

(def
  ^{:private true}
  pred-types
  #?(:clj
     {#'clojure.core/coll? clojure.lang.IPersistentCollection
      #'clojure.core/map? MapInterface
      #'clojure.core/number? java.lang.Number
      #'clojure.core/seq? SeqInterface
      #'clojure.core/seqable? clojure.lang.Seqable
      #'clojure.core/set? clojure.lang.PersistentHashSet
      #'clojure.core/string? java.lang.String
      #'clojure.core/vector? VectorInterface}
     :cljs
     {#'cljs.core/number? js/Number
      #'cljs.core/set? cljs.core/PersistentHashSet
      #'cljs.core/string? js/String
      #'cljs.core/vector? cljs.core/PersistentVector}))



(def
  ^{:private true}
  kind-types
  {:vector VectorInterface
   :seq SeqInterface})

(defn eliminate-preds
  {:private true}
  [env node]
  #?(:clj
     (if (and (= (op (:test node)) :eval)
              (seq? (:form (:test node))))
       (let [type (lookup-type env (second (get-in node [:test :form])))
             pred-value (first (:form (:test node)))
             pred-type (and (symbol? pred-value)
                            (get pred-types (resolve *env* pred-value)))]
         (cond
           (isa? type pred-type)
           (:then node)

           (and (type-not-found? type) pred-type)
           (op-fail)

           :else node))
       node)
     :cljs
     node))


(defn eliminate-check-op
  {:private true}
  [env node]
  (case (op node)
    :check-seq
    (eliminate-check-known-type env node SeqInterface)

    :check-set
    (eliminate-check-known-type env node SetInterface)

    :check-vector
    (eliminate-check-known-type env node VectorInterface)

    :check-map
    (eliminate-check-known-type env node MapInterface)

    :check-boolean
    (eliminate-preds env node)

    ;; else
    node))

(defn add-type-if-coerced-bind
  {:private true}
  [env node coercing-function resulting-type]
  (let [value (:value node)]
    (when (and (= (op value) :eval)
               (seq? (:form value))
               (= (first (:form value)) coercing-function))
      (add-type-to-env env (:symbol node) resulting-type))))

(defn add-type-if-coerced-apply
  {:private true}
  [env node coercing-function resulting-type]
  (when (= (:fn-expr node) coercing-function)
        (add-type-to-env env (:symbol node) resulting-type)))

(defn infer-type-seq
  {:private true}
  [env node form]
  (cond
    ;; If it is quoted then a seq can't be a function call so it is a seq
    (and (r.util/quoted? form)
         (seq? (second form)))
    (add-type-to-env env (:symbol node) SeqInterface)
    ;; If it is quoted (and not a seq) then infer the type from the
    ;; thing in the quote
    (r.util/quoted? form)
    (recur env node (second form))

    ;; if it is a list, it is a seq
    (and (seq? form)
         (or (not (symbol? (first form)))
             (= (first form) 'list))
         (not (seq? (first form))))
    (add-type-to-env env (:symbol node) SeqInterface)

    ;; otherwise just check the type
    (and (not (seq? form))
         (not (symbol? form)))
    (add-type-to-env env (:symbol node) (type form))

    :else nil))

(defn add-type-if-star [env node]
  (add-type-to-env env (:form (:input node)) (kind-types (:kind node)))
  (add-type-to-env env (:input-symbol node) (kind-types (:kind node))))

(defn infer-collection-type
  {:private true}
  [env node]
  (case (op node)
    :star
    (do (add-type-if-star env node)
        node)

    :apply
    (do
      (add-type-if-coerced-apply env node `seq SeqInterface)
      (add-type-if-coerced-apply env node `set SetInterface)
      (add-type-if-coerced-apply env node `vec VectorInterface)
      node)

    :bind
    (do
      (let [value (:value node)
            form (:form value)]
        (when (= (op value) :eval)
          (infer-type-seq env node form))
        node)

      (add-type-if-coerced-bind env node `disj SetInterface)
      (add-type-if-coerced-bind env node `dissoc MapInterface)
      (add-type-if-coerced-bind env node `seq SeqInterface)
      (add-type-if-coerced-bind env node `set SetInterface)
      (add-type-if-coerced-bind env node `vec VectorInterface)
      node)

    node))

;; If something is a vector it is much fast to call it as a
;; function. This will directly invoke its .nth method. We could also
;; call that ourselves with a type hint, but in the face of clj and
;; cljs this is easier and the speed difference is negligible.
(defn fast-vector-nth-calls
  {:private true}
  [env node]
  (case (op node)
    :nth
    (let [target-type (lookup-type env (:form (:target node)))]
      (if (isa? target-type VectorInterface)
        (op-eval `(~(:form (:target node)) ~(:index node)))
        node))
    node))

(defn rewrite-with-types
  {:private true}
  [node]
  ;; If we did something better than an atom, we could know the type
  ;; of something a branch after a check-op.
  (let [env (atom {})]
    (prewalk
     (comp
      (partial fast-vector-nth-calls env)
      (partial eliminate-check-op env)
      (partial infer-collection-type env)) node)))

(defn rewrite [node]
  (loop [node node]
    (let [node* (rewrite-with-types
                 (rewrite*
                  (def-remove-unused node)))]
      (if (= node* node)
        node
        (recur node*)))))

;; ---------------------------------------------------------------------
;; Code generation


(defn fail-form
  "Returns `(list FAIL) if kind is :search, `FAIL otherwise. This is
  used when compiling :def nodes to ensure the correct type of data is
  returned to code compiled for :call nodes."
  [kind]
  (case kind
    (:match :find)
    `r.match.runtime/FAIL

    :search
    `(list r.match.runtime/FAIL)))

(defn js-array-equals-form
  "Form used to test if two arrays a and b are equal in
  ClojureScript."
  [a b]
  `(goog.array/equals ~a ~b
                      (fn f# [a# b#]
                        (if (cljs.core/array? a#)
                          (goog.array/equals a# b# f#)
                          (= a# b#)))))

(defn take-form [n target-form kind]
  (case kind
    :js-array
    `(.slice ~target-form 0 (min (.-length ~target-form) ~n))

    :vector
    `(subvec ~target-form 0 (min (count ~target-form) ~n))

    ;; else
    `(take ~n ~target-form)))

(defn drop-form [n target-form kind]
  (case kind
    :js-array
    `(.slice ~target-form ~n)

    :vector
    `(subvec ~target-form ~n)

    :seq
    `(drop ~n ~target-form)))

(defn case-clause-quote-symbols
  {:private true}
  [form]
  (if (r.util/cljs-env? *env*)
    (cond
      (sequential? form)
      (mapv (fn [x]
              (if (symbol? x)
                (list 'quote x)
                x))
            form)

      :else
      form)
    form))

(defn case-clause-test-form
  "Given an arbitrary Clojure value `form` return a form suitable for
  use as a case test clause.

    (case expr
      form ;; <-- Test clause
      expr
      ,,,)

  For Clojure this is always

    (form)

  For ClojureScript this is

    (form)

  if `form` is not a `seq?`. If `form` is a `seq?` then the form is

    (form*)

  where `form*` is `vec` of `form`.

  This is due to differences in how `case` is handled by ClojureScript
  where we must write

    (case expr
      ([1 2 3])
      expr
      ,,,)

  in placement of

    (case expr
      ((1 2 3))
      expr
      ,,,)
  "
  {:private true}
  [form]
  (let [form (walk/postwalk
              (fn [x]
                (cond
                  (seq? x)
                  (case-clause-quote-symbols
                   (if (= (first x) 'quote)
                     (second x)
                     x))

                  (vector? x)
                  (case-clause-quote-symbols x)

                  :else
                  x))
              form)]
    (if (r.util/cljs-env? *env*)
      (if (seq? form)
        `(~(vec form))
        `(~form))
      `(~form))))

(defmulti compile*
  {:arglists '([ir fail kind])}
  (fn [ir fail kind]
    (:op ir)))


(defmethod compile* :apply
  [ir fail kind]
  (let [fn-expr (:fn-expr ir)
        target (:target ir)]
    `(let [~(:symbol ir) ~(if (:meander.epsilon/beta-reduce (meta fn-expr))
                            (let [[_fn [arg] & body] fn-expr]
                              `(let [~arg ~target]
                                 ~@body))
                            `(~fn-expr ~target))]
       ~(compile* (:then ir) fail kind))))

(defmethod compile* :bind
  [ir fail kind]
  (loop [bindings []
         ir ir]
    (case (:op ir)
      :bind
      (recur (conj bindings (:symbol ir) (compile* (:value ir) fail kind))
             (:then ir))

      :lvr-bind
      (recur (conj bindings (:symbol ir) (compile* (:target ir) fail kind))
             (:then ir))
      ;; else
      `(let ~bindings
         ~(compile* ir fail kind)))))

(defmethod compile* :branch
  [ir fail kind]
  (case kind
    :search
    (let [arms (remove op-fail? (:arms ir))]
      (case (count arms)
        0
        fail

        1
        (compile* (first arms) fail kind)

        ;; else
        (let [compiled-arms (mapv
                             (fn [ir]
                               (compile* ir fail kind))
                             arms)]
          (if (breadth-first?)
            `(r.match.runtime/knit ~compiled-arms)
            `(concat ~@compiled-arms)))))

    (:find :match)
    (let [arms (remove op-fail? (:arms ir))]
      (case (count arms)
        0
        fail

        1
        (compile* (first arms) fail kind)

        ;; else
        (let [fsyms (mapv
                     (fn [_]
                       (gensym "state__"))
                     arms)]
          `(letfn [~@(map
                       (fn [fsym fail arm]
                         `(~fsym []
                           ~(compile* arm fail kind)))
                       fsyms
                       (conj (mapv
                              (fn [fsym]
                                `(~fsym))
                              (rest fsyms))
                             fail)
                       arms)]
             (~(first fsyms))))))))

(defmethod compile* :call
  [ir fail kind]
  (case kind
    (:find :match)
    `(let [x# (~(:symbol ir) ~(compile* (:target ir) fail kind) ~@(:req-syms ir))]
       (if (r.match.runtime/fail? x#)
         ~fail
         (let [[~@(:ret-syms ir)] x#]
           ~(compile* (:then ir) fail kind))))

    :search
    `(mapcat
      (fn [x#]
        (if (r.match.runtime/fail? x#)
          ~fail
          (let [[~@(:ret-syms ir)] x#]
            ~(compile* (:then ir) fail kind))))
      (~(:symbol ir) ~(compile* (:target ir) fail kind) ~@(:req-syms ir)))))

(defmethod compile* :case
  [ir fail kind]
  (let [clauses (mapcat
                 (fn [[value then]]
                   (if (set? value)
                     (let [compiled-values (map compile* value (repeat fail) (repeat kind))]
                       `(~(mapcat case-clause-test-form compiled-values)
                         ~(compile* then fail kind)))
                     (let [compiled-value (compile* value fail kind)]
                       `(~(case-clause-test-form compiled-value)
                         ~(compile* then fail kind)))))
                 (:clauses ir))
        expression (compile* (:target ir) fail kind)]
    (if (and (not (r.util/cljs-env? *env*))
             (every? number? (sequence (comp (take-nth 2) cat) clauses)))
      ;; Prevents performance warnings from Clojure when
      ;; `*warn-on-reflection*` is on. Is this really neccessary?
      (let [value (gensym "VALUE__")]
        `(let [~value ~expression]
           ;; Should we test `(number? ~value)`?
           (cond
             ~@(mapcat
                (fn [[tests expr]]
                  (case (count tests)
                    0 nil
                    1 `((= ~value ~(first tests))
                        ~expr)
                    ;; else
                    `((or ~@(map (fn [test]
                                   `(= ~value ~test))
                                 tests))
                      ~expr)))
                (partition 2 clauses))

             :else
             ~fail)))
      `(case ~expression
         ~@clauses
         ~(compile* (:then ir) fail kind)))))


(defmethod compile* :check
  [ir fail kind]
  `(if ~(compile* (:test ir) fail kind)
     ~(compile* (:then ir) fail kind)
     ~fail))

(defmethod compile* :check-array
  [ir fail kind]
  `(if (cljs.core/array? ~(compile* (:target ir) fail kind))
     ~(compile* (:then ir) fail kind)
     ~fail))

(defmethod compile* :check-array-equals
  [ir fail kind]
  `(if ~(js-array-equals-form
         (compile* (:target-1 ir) fail kind)
         (compile* (:target-2 ir) fail kind))
     ~(compile* (:then ir) fail kind)
     ~fail))

(defmethod compile* :check-boolean
  [ir fail kind]
  `(if ~(compile* (:test ir) fail kind)
     ~(compile* (:then ir) fail kind)
     ~fail))

(defmethod compile* :check-bounds
  [ir fail kind]
  (let [length (:length ir)
        target (compile* (:target ir) fail kind)
        test (case (:kind ir)
               :js-array
               `(= (.-length ~target) ~length)

               (:map :set)
               `(<= ~length (count ~target))

               :seq
               `(= (bounded-count (inc ~length) ~target)
                   ~length)

               :vector
               `(= (count ~target) ~length))]
    `(if ~test
       ~(compile* (:then ir) fail kind)
       ~fail)))

(defmethod compile* :check-equal
  [ir fail kind]
  `(if (= ~(compile* (:target-1 ir) fail kind)
          ~(compile* (:target-2 ir) fail kind))
     ~(compile* (:then ir) fail kind)
     ~fail))

(defmethod compile* :check-empty
  [ir fail kind]
  `(if (not (seq ~(compile* (:target ir) fail kind)))
     ~(compile* (:then ir) fail kind)
     ~fail))

(defmethod compile* :check-lit
  [ir fail kind]
  (let [compiled-value (compile* (:value ir) fail kind)
        expression (compile* (:target ir) fail kind)
        then (compile* (:then ir) fail kind)]
    (if (and (not (r.util/cljs-env? *env*))
             (number? compiled-value))
      ;; Prevents performance warnings from Clojure when
      ;; `*warn-on-reflection*` is on.
      `(if (= ~expression ~compiled-value)
         ~then
         ~fail)
      `(case ~expression
         ~(case-clause-test-form compiled-value)
         ~then
         ~fail))))

(defmethod compile* :check-map
  [ir fail kind]
  `(if (map? ~(compile* (:target ir) fail kind))
     ~(compile* (:then ir) fail kind)
     ~fail))

(defmethod compile* :check-seq
  [ir fail kind]
  `(if (seq? ~(compile* (:target ir) fail kind))
     ~(compile* (:then ir) fail kind)
     ~fail))

(defmethod compile* :check-set
  [ir fail kind]
  `(if (set? ~(compile* (:target ir) fail kind))
     ~(compile* (:then ir) fail kind)
     ~fail))

(defmethod compile* :check-vector
  [ir fail kind]
  `(if (vector? ~(compile* (:target ir) fail kind))
     ~(compile* (:then ir) fail kind)
     ~fail))

(defmethod compile* :drop
  [ir fail kind]
  (drop-form (:n ir)
             (compile* (:target ir) fail kind)
             (:kind ir)))

(defmethod compile* :def
  [ir fail kind]
  (loop [bindings []
         ir ir]
    (if (= (:op ir) :def)
      (recur (conj bindings
                   `(~(:symbol ir) [~(:target-arg ir) ~@(:req-syms ir)]
                     ~(compile* (:body ir) (fail-form kind) kind)))
             (:then ir))
      `(letfn ~bindings
         ~(compile* ir fail kind)))))

(defmethod compile* :eval
  [ir fail kind]
  (:form ir))

(defmethod compile* :fail
  [ir fail kind]
  fail)

(defmethod compile* :find
  [ir fail kind]
  (let [search-space (gensym "search_space__")
        result-sym (gensym "result__")
        fail-sym (gensym "fail__")]
    `(loop [~search-space ~(compile* (:value ir) fail kind)]
       (if (seq ~search-space)
         (let [~(:symbol ir) (first ~search-space)
               ~result-sym ~(compile* (:body ir) (fail-form :find) :find)]
           (if (r.match.runtime/fail? ~result-sym)
             (recur (next ~search-space))
             ~result-sym))
         ~fail))))

(defmethod compile* :load
  [ir fail kind]
  `(~(:id ir)))

(defmethod compile* :lookup
  [ir fail kind]
  (if (r.util/cljs-env? *env*)
    `(get ~(compile* (:target ir) fail kind)
          ~(compile* (:key ir) fail kind))
    `(.valAt ~(with-meta (compile* (:target ir) fail kind)
                {:tag 'clojure.lang.ILookup})
             ~(compile* (:key ir) fail kind))))

(defmethod compile* :lvr-bind
  [ir fail kind]
  `(let [~(:symbol ir) ~(compile* (:target ir) fail kind)]
     ~(compile* (:then ir) fail kind)))

(defmethod compile* :lvr-check
  [ir fail kind]
  `(if (= ~(:symbol ir) ~(compile* (:target ir) fail kind))
     ~(compile* (:then ir) fail kind)
     ~fail))

(defmethod compile* :nth
  [ir fail kind]
  `(nth ~(compile* (:target ir) fail kind) ~(:index ir)))

(defmethod compile* :mut-bind
  [ir fail kind]
  (let [*symbol (:symbol ir)]
    `(let [~*symbol ~(compile* (:target ir) fail kind)]
        ~(compile* (:then ir) fail kind))))

(defmethod compile* :mvr-append
  [ir fail kind]
  (let [!symbol (:symbol ir)]
    `(let [~!symbol (conj ~!symbol ~(compile* (:target ir) fail kind))]
       ~(compile* (:then ir) fail kind))))

(defmethod compile* :mvr-bind
  [ir fail kind]
  `(let [~(:symbol ir) ~(compile* (:target ir) fail kind)]
     ~(compile* (:then ir) fail kind)))

(defmethod compile* :mvr-init
  [ir fail kind]
  `(let [~(:symbol ir) []]
     ~(compile* (:then ir) fail kind)))

(defmethod compile* :pass
  [ir fail kind]
  (compile* (:then ir) fail kind))

(defmethod compile* :plus
  [ir fail kind]
  (let [n (:n ir)
        m (:m ir)]
    (case kind
      :search
      (let [input-sym (:input-symbol ir)
            input-form (compile* (:input ir) fail kind)
            rets (:return-symbols ir)
            body-form (compile* (:body ir) (fail-form kind) kind)
            body-f `(fn [~rets ~input-sym]
                      ~body-form)
            then-form (compile* (:then ir) fail kind)
            then-f `(fn [~rets] ~then-form)]
        (case (:kind ir)
          :js-array
          `(r.match.runtime/run-plus-js-array-search ~input-form ~rets ~n ~m ~body-f ~then-f)

          :seq
          `(r.match.runtime/run-plus-seq-search ~input-form ~rets ~n ~m ~body-f ~then-f)

          :vector
          `(r.match.runtime/run-plus-vec-search ~input-form ~rets ~n ~m ~body-f ~then-f)))

      ;; else
      (let [coll-sym (gensym "coll__")
            input-sym (:input-symbol ir)
            return-syms (:return-symbols ir)
            body-form (compile* (:body ir) (fail-form kind) kind)
            then-form (compile* (:then ir) fail kind)]
        `(loop [i# 0
                ~coll-sym ~(compile* (:input ir) fail kind)
                ~return-syms ~(:return-symbols ir)]
           (let [~input-sym ~(take-form n coll-sym (:kind ir))]
             (if (= (count ~input-sym) ~n)
               (let [result# ~body-form]
                 (if (r.match.runtime/fail? result#)
                   ~fail
                   (recur (inc i#) ~(drop-form n coll-sym (:kind ir)) result#)))
               ;; Failed to consume
               (if (or (seq ~coll-sym)
                       (< i# ~m))
                 ~fail
                 ~then-form))))))))

(defmethod compile* :return
  [ir fail kind]
  (case kind
    (:find :match)
    (:value ir)

    :search
    `(list ~(:value ir))))

(defmethod compile* :save
  [ir fail kind]
  (let [id (:id ir)
        body-1 (:body-1 ir)
        body-2 (:body-2 ir)]
    (if (and (= (:op body-2) :load)
             (= (:id body-2 id)))
      (compile* body-1 fail kind)
      (let [f-sym (gensym "f__")]
        `(letfn [(~id [] ~fail)
                 (~f-sym [] ~(compile* body-2 fail kind))]
           ~(compile* body-1 `(~f-sym) kind))))))

(defmethod compile* :search
  [ir fail kind]
  (case kind
    (:find :match)
    (compile* (assoc ir :op :find) fail kind)

    :search
    `(mapcat
      (fn [~(:symbol ir)]
        ~(compile* (:body ir) fail kind))
      ~(compile* (:value ir) fail kind))))

(defmethod compile* :star
  [ir fail kind]
  (let [coll-sym (gensym "coll__")
        input-sym (:input-symbol ir)
        input-form (compile* (:input ir) fail kind)
        n (:n ir)
        rets (:return-symbols ir)
        body-form (compile* (:body ir) (fail-form kind) kind)
        body-f `(fn [~rets ~input-sym]
                  ~body-form)
        then-form (compile* (:then ir) fail kind)
        then-f `(fn [~rets] ~then-form)]
    (case kind
      :search
      (case (:kind ir)
        :js-array
        `(r.match.runtime/run-star-js-array-search ~input-form ~rets ~n ~body-f ~then-f)

        :seq
        `(r.match.runtime/run-star-seq-search ~input-form ~rets ~n ~body-f ~then-f)

        :vector
        `(r.match.runtime/run-star-vec-search ~input-form ~rets ~n ~body-f ~then-f))

      ;;else
      `(let [ret# ~(case (:kind ir)
                     :js-array
                     `(r.match.runtime/run-star-js-array ~input-form ~rets ~n ~body-f ~then-f)

                     :seq
                     (if (= n 1)
                       `(r.match.runtime/run-star-1 ~input-form ~rets ~body-f ~then-f)
                       `(r.match.runtime/run-star-seq ~input-form ~rets ~n ~body-f ~then-f))

                     :vector
                     (if (= n 1)
                       `(r.match.runtime/run-star-1 ~input-form ~rets ~body-f ~then-f)
                       `(r.match.runtime/run-star-vec ~input-form ~rets ~n ~body-f ~then-f)))]
           (if (r.match.runtime/fail? ret#)
             ~fail
             ret#)))))

(defmethod compile* :take
  [ir fail kind]
  (take-form (:n ir)
             (compile* (:target ir) fail kind)
             (:kind ir)))

(defmethod compile* :default
  [ir fail kind]
  ir)

(defn compile
  ([ir fail kind]
   (compile* (rewrite ir) fail kind))
  ([ir fail kind env]
   (binding [*env* env]
     (compile* (rewrite ir) fail kind))))
