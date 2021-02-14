(ns meander.environment.data.zeta
  (:require
   [#?(:clj clojure.core, :cljs cljs.core) :as clojure]
   [clojure.walk :as walk]
   [clojure.zip :as zip]
   [meander.peval.zeta :as m.peval]
   [meander.util.zeta :as m.util]
   [meander.zip.zeta :as m.zip]))

;; Tree Constructors
;; ---------------------------------------------------------------------

(defn x__ []
  (gensym "x__"))

(defn i__ []
  (gensym "i__"))

(defn leaf
  {:style/indent 1}
  [tag & keyvals]
  (with-meta (apply hash-map :tag tag keyvals) 
    {:id (i__)}))

(defn branch
  {:style/indent 1}
  [tag child-map]
  (let [child-map (dissoc child-map :tag :id :childre)
        child-keys (vec (keys child-map))]
    (with-meta (merge child-map {:tag tag, :children child-keys})
      {:id (i__)})))

(defn make-identifier []
  {:tag :identifier, :id (i__)})

(defn identifier? [x]
  (= (:tag x) :identifier))

(defn make-bind [expression-tree f]
  (let [identifier (make-identifier)]
    {:tag :bind
     :identifier identifier
     :expression expression-tree
     :body (f identifier)
     :children [:expression :body]}))

(defn bind? [x]
  (= (:tag x) :bind))

(defn arg-key
  {:private true}
  [n]
  (keyword (str "a_" n)))

(defn make-call [nodes]
  (branch :call
    (into {} (map-indexed (fn [n arg] [(arg-key n) arg])) nodes)))

(defn make-dual [a b]
  (branch :dual
    {:a a
     :b b}))

(defn make-eval [x]
  (with-meta {:tag :eval, :form x}
    {::evaluated? true}))

(defn make-fail [state-node]
  (leaf :fail :state state-node))

(defn make-find [state-tree reference-id-leaf]
  (branch :find
    {:state state-tree
     :reference-id reference-id-leaf}))

(defn make-getv [state-tree variable-id-leaf none-leaf]
  (branch :getv
    {:state state-tree
     :variable-id variable-id-leaf
     :none none-leaf}))

(defn make-give [state-node object-node]
  (branch :give
    {:state state-node
     :object object-node}))

(defn make-join [ma-node mb-node]
  (branch :join
    {:ma ma-node
     :mb mb-node}))

(defn make-let [identifier-leaf expression-tree body-tree]
  (if (identifier? expression-tree)
    expression-tree
    (with-meta {:tag :let
                :identifier identifier-leaf
                :expression expression-tree
                :body body-tree
                :children [:expression :body]}
      {:id (i__)})))

(defn let? [x]
  (= :let (:tag x)))

(defn let-n*
  {:style/indent 1}
  [expression-tree f]
  (if (or (identifier? expression-tree)
          (= (:tag expression-tree) :eval))
    (f expression-tree)
    (let [identifier (make-identifier)]
      (make-let identifier expression-tree (f identifier)))))

(defmacro let-n [bindings body]
  (reduce
   (fn [body [b v]]
     `(let-n* ~v (fn [~b] ~body)))
   body
   (reverse (partition 2 bindings))))

(defn make-list [state-tree]
  (branch :list
    {:state state-tree}))

(defn make-pass [tree]
  (branch :pass
    {:state tree}))

(defn make-seed [object]
  (with-meta {:tag :state
              :variables {:tag :map
                          :children []}
              :references {}
              :object (leaf :eval :form object)
              :children [:object
                         :variables]}
    {:id (i__)}))

(defn make-setv [state-tree id-leaf expression-tree]
  (branch :setv
    {:state state-tree
     :variable-id id-leaf
     :expression expression-tree}))

(defn make-take [node]
  (branch :take {:state node}))

(defn make-test
  {:style/indent 1}
  [test-node then-node else-node]
  (branch :test
    {:test test-node
     :then then-node
     :else else-node}))

(def %none
  (leaf :eval :form ::none))

(def %assoc
  (leaf :eval :form `clojure/assoc))

(def %find
  (leaf :eval :form `clojure/find))

(def %val
  (leaf :eval :form `clojure/val))

(def %variable-key
  (leaf :eval :form :variables))

(def %object-key
  (leaf :eval :form :variables))

(defn call-tree? [x]
  (= (:tag x) :call))

(defn state-tree? [x]
  (= (:tag x) :state))

(defn give-tree? [x]
  (= (:tag x) :give))

(defn pass-tree? [x]
  (= (:tag x) :pass))

(defn true-leaf? [x]
  (and (= (:tag x) :eval)
       (= (:form x) true)))

(def
  ^{:arglists '([tree])}
  truthy-tree?
  (some-fn state-tree?
           give-tree?
           pass-tree?
           true-leaf?))

(defn false-leaf? [tree]
  (and (= (:tag tree) :eval)
       (= (:form tree) false)))

(defn nil-leaf? [tree]
  (and (= (:tag tree) :eval)
       (= (:form tree) nil)))

(defn binding-tree? [tree]
  (or (bind? tree) (let? tree)))

;; Traversal
;; ---------------------------------------------------------------------

(defn child-keys [node]
  (if-some [[_ child-keys] (find node :children)]
    child-keys
    nil))

(defn branch? [node]
  (seq (child-keys node)))

(defn children [node]
  (mapv node (child-keys node)))

(defn make-node [node children]
  (merge node (zipmap (child-keys node) children)))

(defn subnodes [tree]
  (tree-seq branch? children tree))

;; Zipper
;; ------

(defn zipper [node]
  (zip/zipper branch? children make-node node))

;; Walk
;; ----

(defn postwalk [f node]
  (f (make-node node (mapv (partial postwalk f) (children node)))))

(defn postwalk-replace [smap node]
  (postwalk (fn [node] (get smap node node)) node))

;; Environment
;; ---------------------------------------------------------------------

(def environment
  (letfn [(bind [f m]
            (make-bind m f))

          (code [x]
            (eval x))

          (call [f & args]
            (make-call (cons f args)))

          (dual [a b]
            (make-dual a b))

          (eval [form]
            (if (::evaluated? (meta form))
              form
              (make-eval form)))

          (fail [state]
            (make-fail state))

          (find [state id]
            (make-find state id))

          (give [state object]
            (make-give state object))

          (join [ma mb]
            (make-join ma mb))

          (list [state]
            (make-list state))

          (load [state id unfold pass fail]
            (let-n [id (eval (clojure/list 'quote id))
                    value (make-getv state id %none)]
              (unfold value
                      (fn [dispersed new]
                        (let [state (make-setv state id new)]
                          (make-give state dispersed)))
                      (fn [_]
                        (fail state)))))

          (make [state]
            (leaf :make))

          (mint [state]
            (branch :mint
              {:state state}))

          (pass [state]
            (make-pass state))

          (pick [ma mb]
            (branch :pick
              {:ma ma
               :mb mb}))

          (save [state id fold pass fail]
            (let [id (eval (clojure/list 'quote id))]
              (let-n [object (take state)
                      old (make-getv state id %none)]
                (fold old object
                      (fn [new]
                        (pass (make-setv state id new)))
                      (fn [_]
                        (fail state))))))

          (scan [f xs]
            {:tag :scan
             :f f
             :xs xs})

          (star [f & args]
            {:tag :star
             :f f
             :args (vec args)})

          (seed [object]
            (make-seed object))

          (take [state]
            (make-take state))

          (test [test then else]
            (make-test test (then) (else)))

          (with [state mapping f]
            {:tag :with
             :state state
             :mapping mapping
             :f f})]
    {:bind bind
     :call call
     :code code
     :dual dual
     :eval eval
     :fail fail
     :find find
     :give give
     :join join
     :list list
     :load load
     :make make
     :mint mint
     :none %none
     :pass pass
     :pick pick
     :save save
     :scan scan
     :seed seed
     :star star
     :take take
     :test test
     :with with}))

(defn one-code [node]
  (postwalk
   (fn f [node]
     (case (:tag node)
       :bind
       `(let* [~(f (:identifier node)) ~(:expression node)]
          (if ~(f (:identifier node))
            ~(:body node)))
       
       :call
       (m.peval/peval `(~@(map node (:children node))))

       :eval
       (:form node)

       :give
       `(assoc ~(:state node) :object ~(:object node))

       :getv
       `(get (get ~(:state node) :variables) ~(:variable-id node) ~(:none node))

       :fail
       nil
       
       :identifier
       (:id node)

       (:join :pick)
       (let [x__ (x__)]
         `(let* [~x__ ~(:ma node)]
            (if ~x__
              ~x__
              ~(:mb node))))

       :let
       `(let* [~(f (:identifier node)) ~(:expression node)]
          ~(:body node))

       :list
       `(get ~(:state node) :variables)

       :map
       (into {} (map (fn [k] [(f k) (f (get node k))])) (:children node))

       :pass
       (:state node)

       :setv
       (let [x__0 (x__)
             x__1 (x__)
             x__2 (x__)]
         `(let* [~x__0 ~(:state node)
                 ~x__1 (get ~x__0 :variables)
                 ~x__2 (assoc ~x__1 ~(:variable-id node) ~(:expression node))]
            (assoc ~x__0 :variables ~x__2)))

       :take
       `(get ~(:state node) :object)

       :test
       ;; else
       (m.peval/peval
        `(if ~(:test node)
           ~(:then node)
           ~(:else node)))

       :state
       {:object (f (:object node))
        :variables (f (:variables node))}

       node))
   node))

(defn let-scope [loc]
  (into {}
        (comp (filter (fn [tree] (= (:tag tree) :let)))
              (map (juxt :identifier :expression)))
        (zip/path loc)))

(defn bind-scope [loc]
  (into {}
        (keep (fn [tree]
                (if (= (:tag tree) :bind)
                  (let [expression (:expression tree)]
                    (if (= (:tag expression) :pass)
                      [(:identifier tree) (:state expression)])))))
        (zip/path loc)))

(defn scope-map [loc]
  (merge (let-scope loc) (bind-scope loc)))


;; Passes
;; ---------------------------------------------------------------------

(defn bottom-up-pass [f tree]
  (zip/root (m.zip/bottom-up f (zipper tree))))

(defn top-down-pass [f tree]
  (zip/root (m.zip/top-down f (zipper tree))))

(defn pass-interpret-test [tree]
  (bottom-up-pass
   (fn [loc]
     (let [node (zip/node loc)]
       (if (= (:tag node) :test)
         (let [test (:test node)
               test (get (scope-map loc) test test)]
           (cond
             (truthy-tree? test)
             (zip/replace loc (:then node))

             (or (false-leaf? test) (nil-leaf? test))
             (zip/replace loc (:else node))

             :else
             loc))
         ;;else
         loc)))
   tree))

(defn interpret-getv [getv-tree scope]
  (let [state (:state getv-tree)
        state (get scope state state)]
    (if (= (:tag state) :state)
      (let [variable-id (:variable-id getv-tree)
            variable-map (:variables state)]
        (get variable-map variable-id (:none getv-tree)))
      getv-tree)))

(defn pass-interpret-getv [tree]
  (bottom-up-pass
   (fn [loc]
     (let [node (zip/node loc)]
       (if (= (:tag node) :getv)
         (zip/edit loc interpret-getv (scope-map loc))
         loc)))
   tree))

(defn interpret-setv [setv-tree scope]
  (let [state (:state setv-tree)
        state (get scope state state)]
    (case (:tag state)
      :state
      (let [expression (:expression setv-tree)
            variable-id (:variable-id setv-tree)
            variables (:variables state)
            variables (assoc variables variable-id expression)
            variables (assoc variables :children (vec (keys (dissoc variables :id :tag :children))))]
        (assoc state :variables variables))

      ;; else
      setv-tree)))

(defn pass-interpret-setv [tree]
  (bottom-up-pass
   (fn [loc]
     (let [node (zip/node loc)]
       (if (= (:tag node) :setv)
         (zip/edit loc interpret-setv (scope-map loc))
         loc)))
   tree))

(defn interpret-give [tree scope]
  (let [state (:state tree)
        state (get scope state state)]
    (if (= (:tag state) :state)
      (let [object (:object tree)
            state (assoc state :object object)]
        state)
      tree)))

(defn pass-interpret-give [tree]
  (bottom-up-pass
   (fn [loc]
     (let [node (zip/node loc)]
       (if (= (:tag node) :give)
         (zip/edit loc interpret-give (scope-map loc))
         loc)))
   tree))

(defn interpret-take [tree scope]
  (let [state (:state tree)
        state (get scope state state)]
    (if (= (:tag state) :state)
      (get state :object)
      tree)))

(defn pass-interpret-take [tree]
  (bottom-up-pass
   (fn [loc]
     (let [node (zip/node loc)]
       (if (= (:tag node) :take)
         (zip/edit loc interpret-take (scope-map loc))
         loc)))
   tree))
          
(defn interpret-bind-to-test [bind-tree]
  (let [identifier (:identifier bind-tree)
        test-tree (:expression bind-tree)]
    (make-test (:test test-tree)
      (assoc bind-tree :expression (:then test-tree))
      (assoc bind-tree :expression (:else test-tree)))))

(defn interpret-bind-to-let [bind-tree]
  (let [identifier (:identifier bind-tree)
        let-tree (:expression bind-tree)
        body (:body bind-tree)]
    (assoc let-tree :body (assoc bind-tree :expression (:body let-tree)))))

(defn interpret-bind-one
  [bind-tree]
  (let [identifier (:identifier bind-tree)
        expression (:expression bind-tree)
        body (:body bind-tree)]
    (case (:tag expression)
      :let
      (interpret-bind-to-let bind-tree)

      :fail
      expression

      :pass
      (let [state (:state expression)]
        (if (= (:tag state) :state)
          (make-let identifier state body)
          bind-tree))

      :state
      (make-let identifier expression body)

      :test
      (interpret-bind-to-test bind-tree)
     
      ;; else
      bind-tree)))

(defn pass-interpret-bind-one [tree]
  (top-down-pass
   (fn [loc]
     (let [node (zip/node loc)]
       (if (= (:tag node) :bind)
         (zip/edit loc interpret-bind-one)
         loc)))
   tree))

(defn interpret-let-test [let-tree]
  (let [identifier (:identifier let-tree)
        body (:body let-tree)
        expression (:expression let-tree)]
    (if (and (= :test (:tag expression) (:tag body))
             (= identifier (:test body)))
      (make-test (:test expression)
        (make-let identifier (:then expression)
                  (:then body))
        (make-let identifier (:else expression)
                  (:else body)))
      let-tree)))

(defn remove-let-unused [let-tree]
  (let [identifier (:identifier let-tree)
        body (:body let-tree)]
    (if (seq (filter (partial = identifier) (rest (subnodes body))))
      let-tree
      body)))

(defn fold-let-eval [let-tree]
  (let [identifier (:identifier let-tree)
        expression (:expression let-tree)
        body (:body let-tree)]
    (if (or (= (:tag expression) :eval)
            (= (:tag expression) :identifier)
            (= (:tag expression) :state))
      (top-down-pass
       (fn [loc]
         (if (= identifier (zip/node loc))
           (zip/replace loc expression)
           loc))
       body)
      let-tree)))


(defn pass-interpret-let [tree]
  (top-down-pass
   (fn [loc]
     (let [node (zip/node loc)]
       (if (= (:tag node) :let)
         (zip/edit loc m.util/one [remove-let-unused fold-let-eval interpret-let-test])
         loc)))
   tree))

(defn pass-interpret-identifier [tree]
  (bottom-up-pass
   (fn [loc]
     (let [node (zip/node loc)]
       (if (= (:tag node) :identifier)
         (let [scope (scope-map loc)]
           (loop [identifier node]
             (if-some [tree (get scope identifier)]
               (cond
                 (= (:tag tree) :eval)
                 (zip/replace loc tree)

                 (= (:tag tree) :identifier)
                 (recur tree)

                 :else
                 loc)
               loc)))
         loc)))
   tree))

(defn interpret-list [list-tree scope]
  (let [state (:state list-tree)
        state (get scope state state)]
    (case (:tag state)
      :state
      (:variables state)

      list-tree)))

(defn pass-interpret-list [tree]
  (bottom-up-pass
   (fn [loc]
     (let [node (zip/node loc)]
       (if (= (:tag node) :list)
         (zip/edit loc interpret-list (scope-map loc))
         loc)))
   tree))

(defn binding-pass [tree]
  ((m.util/fix (comp pass-interpret-let
                     pass-interpret-bind-one))
   tree))

(defn interpretation-pass [tree]
  ((m.util/fix (comp pass-interpret-identifier
                     pass-interpret-give
                     pass-interpret-getv
                     pass-interpret-setv
                     pass-interpret-take
                     pass-interpret-test))
   tree))

(defn run-passes [tree]
  ((m.util/fix (comp binding-pass interpretation-pass))
   tree))


(let [tree* (run-passes test-tree)
      code* (one-code tree*)]
  [code*
   tree*])

;; Examples
;; ---------------------------------------------------------------------
(def test-tree
' {:tag :bind,
 :identifier {:tag :identifier, :id i__19523},
 :expression
 {:tag :bind,
  :identifier {:tag :identifier, :id i__19478},
  :expression
  {:tag :bind,
   :identifier {:tag :identifier, :id i__19384},
   :expression
   {:state
    {:tag :state,
     :variables {:tag :map, :children []},
     :references {},
     :object {:form input, :tag :eval},
     :children [:object :variables]},
    :tag :pass,
    :children [:state]},
   :body
   {:tag :bind,
    :identifier {:tag :identifier, :id i__19388},
    :expression
    {:state
     {:state {:tag :identifier, :id i__19384},
      :object {:tag :eval, :form clojure.core/seq?},
      :tag :give,
      :children [:state :object]},
     :tag :pass,
     :children [:state]},
    :body
    {:test
     {:a_0
      {:state {:tag :identifier, :id i__19388},
       :tag :take,
       :children [:state]},
      :a_1
      {:state {:tag :identifier, :id i__19384},
       :tag :take,
       :children [:state]},
      :tag :call,
      :children [:a_0 :a_1]},
     :then
     {:test
      {:a_0 {:tag :eval, :form clojure.core/=},
       :a_1 {:tag :eval, :form 3},
       :a_2
       {:a_0 {:tag :eval, :form clojure.core/bounded-count},
        :a_1 {:tag :eval, :form 4},
        :a_2
        {:state {:tag :identifier, :id i__19384},
         :tag :take,
         :children [:state]},
        :tag :call,
        :children [:a_0 :a_1 :a_2]},
       :tag :call,
       :children [:a_0 :a_1 :a_2]},
      :then
      {:tag :bind,
       :identifier {:tag :identifier, :id i__19457},
       :expression
       {:tag :bind,
        :identifier {:tag :identifier, :id i__19404},
        :expression
        {:tag :bind,
         :identifier {:tag :identifier, :id i__19396},
         :expression
         {:state {:tag :identifier, :id i__19384},
          :tag :pass,
          :children [:state]},
         :body
         {:test
          {:a_0 {:tag :eval, :form clojure.core/=},
           :a_1
           {:state
            {:state {:tag :identifier, :id i__19396},
             :object
             {:a_0 {:tag :eval, :form clojure.core/nth},
              :a_1
              {:state {:tag :identifier, :id i__19384},
               :tag :take,
               :children [:state]},
              :a_2 {:tag :eval, :form 0},
              :tag :call,
              :children [:a_0 :a_1 :a_2]},
             :tag :give,
             :children [:state :object]},
            :tag :take,
            :children [:state]},
           :a_2 {:tag :eval, :form 'let*},
           :tag :call,
           :children [:a_0 :a_1 :a_2]},
          :then
          {:state
           {:state {:tag :identifier, :id i__19396},
            :object
            {:a_0 {:tag :eval, :form clojure.core/nth},
             :a_1
             {:state {:tag :identifier, :id i__19384},
              :tag :take,
              :children [:state]},
             :a_2 {:tag :eval, :form 0},
             :tag :call,
             :children [:a_0 :a_1 :a_2]},
            :tag :give,
            :children [:state :object]},
           :tag :pass,
           :children [:state]},
          :else
          {:state
           {:state {:tag :identifier, :id i__19396},
            :object
            {:a_0 {:tag :eval, :form clojure.core/nth},
             :a_1
             {:state {:tag :identifier, :id i__19384},
              :tag :take,
              :children [:state]},
             :a_2 {:tag :eval, :form 0},
             :tag :call,
             :children [:a_0 :a_1 :a_2]},
            :tag :give,
            :children [:state :object]},
           :tag :fail},
          :tag :test,
          :children [:test :then :else]},
         :children [:expression :body]},
        :body
        {:tag :bind,
         :identifier {:tag :identifier, :id i__19409},
         :expression
         {:state
          {:state
           {:state {:tag :identifier, :id i__19404},
            :object
            {:a_0 {:tag :eval, :form clojure.core/nth},
             :a_1
             {:state {:tag :identifier, :id i__19384},
              :tag :take,
              :children [:state]},
             :a_2 {:tag :eval, :form 1},
             :tag :call,
             :children [:a_0 :a_1 :a_2]},
            :tag :give,
            :children [:state :object]},
           :object {:tag :eval, :form clojure.core/vector?},
           :tag :give,
           :children [:state :object]},
          :tag :pass,
          :children [:state]},
         :body
         {:test
          {:a_0
           {:state {:tag :identifier, :id i__19409},
            :tag :take,
            :children [:state]},
           :a_1
           {:state
            {:state {:tag :identifier, :id i__19404},
             :object
             {:a_0 {:tag :eval, :form clojure.core/nth},
              :a_1
              {:state {:tag :identifier, :id i__19384},
               :tag :take,
               :children [:state]},
              :a_2 {:tag :eval, :form 1},
              :tag :call,
              :children [:a_0 :a_1 :a_2]},
             :tag :give,
             :children [:state :object]},
            :tag :take,
            :children [:state]},
           :tag :call,
           :children [:a_0 :a_1]},
          :then
          {:test
           {:a_0 {:tag :eval, :form clojure.core/=},
            :a_1 {:tag :eval, :form 2},
            :a_2
            {:a_0 {:tag :eval, :form clojure.core/bounded-count},
             :a_1 {:tag :eval, :form 3},
             :a_2
             {:state
              {:state {:tag :identifier, :id i__19404},
               :object
               {:a_0 {:tag :eval, :form clojure.core/nth},
                :a_1
                {:state {:tag :identifier, :id i__19384},
                 :tag :take,
                 :children [:state]},
                :a_2 {:tag :eval, :form 1},
                :tag :call,
                :children [:a_0 :a_1 :a_2]},
               :tag :give,
               :children [:state :object]},
              :tag :take,
              :children [:state]},
             :tag :call,
             :children [:a_0 :a_1 :a_2]},
            :tag :call,
            :children [:a_0 :a_1 :a_2]},
           :then
           {:tag :bind,
            :identifier {:tag :identifier, :id i__19435},
            :expression
            {:tag :bind,
             :identifier {:tag :identifier, :id i__19417},
             :expression
             {:state
              {:state {:tag :identifier, :id i__19404},
               :object
               {:a_0 {:tag :eval, :form clojure.core/nth},
                :a_1
                {:state {:tag :identifier, :id i__19384},
                 :tag :take,
                 :children [:state]},
                :a_2 {:tag :eval, :form 1},
                :tag :call,
                :children [:a_0 :a_1 :a_2]},
               :tag :give,
               :children [:state :object]},
              :tag :pass,
              :children [:state]},
             :body
             {:tag :let,
              :identifier {:tag :identifier, :id i__19420},
              :expression
              {:state
               {:state {:tag :identifier, :id i__19417},
                :object
                {:a_0 {:tag :eval, :form clojure.core/nth},
                 :a_1
                 {:state
                  {:state {:tag :identifier, :id i__19404},
                   :object
                   {:a_0 {:tag :eval, :form clojure.core/nth},
                    :a_1
                    {:state {:tag :identifier, :id i__19384},
                     :tag :take,
                     :children [:state]},
                    :a_2 {:tag :eval, :form 1},
                    :tag :call,
                    :children [:a_0 :a_1 :a_2]},
                   :tag :give,
                   :children [:state :object]},
                  :tag :take,
                  :children [:state]},
                 :a_2 {:tag :eval, :form 0},
                 :tag :call,
                 :children [:a_0 :a_1 :a_2]},
                :tag :give,
                :children [:state :object]},
               :tag :take,
               :children [:state]},
              :body
              {:tag :let,
               :identifier {:tag :identifier, :id i__19422},
               :expression
               {:state
                {:state {:tag :identifier, :id i__19417},
                 :object
                 {:a_0 {:tag :eval, :form clojure.core/nth},
                  :a_1
                  {:state
                   {:state {:tag :identifier, :id i__19404},
                    :object
                    {:a_0 {:tag :eval, :form clojure.core/nth},
                     :a_1
                     {:state {:tag :identifier, :id i__19384},
                      :tag :take,
                      :children [:state]},
                     :a_2 {:tag :eval, :form 1},
                     :tag :call,
                     :children [:a_0 :a_1 :a_2]},
                    :tag :give,
                    :children [:state :object]},
                   :tag :take,
                   :children [:state]},
                  :a_2 {:tag :eval, :form 0},
                  :tag :call,
                  :children [:a_0 :a_1 :a_2]},
                 :tag :give,
                 :children [:state :object]},
                :variable-id {:tag :eval, :form '?__19265},
                :none
                {:form :meander.environment.data.zeta/none,
                 :tag :eval},
                :tag :getv,
                :children [:state :variable-id :none]},
               :body
               {:test
                {:a_0 {:tag :eval, :form clojure.core/=},
                 :a_1 {:tag :identifier, :id i__19422},
                 :a_2
                 {:form :meander.environment.data.zeta/none,
                  :tag :eval},
                 :tag :call,
                 :children [:a_0 :a_1 :a_2]},
                :then
                {:state
                 {:state
                  {:state {:tag :identifier, :id i__19417},
                   :object
                   {:a_0 {:tag :eval, :form clojure.core/nth},
                    :a_1
                    {:state
                     {:state {:tag :identifier, :id i__19404},
                      :object
                      {:a_0 {:tag :eval, :form clojure.core/nth},
                       :a_1
                       {:state {:tag :identifier, :id i__19384},
                        :tag :take,
                        :children [:state]},
                       :a_2 {:tag :eval, :form 1},
                       :tag :call,
                       :children [:a_0 :a_1 :a_2]},
                      :tag :give,
                      :children [:state :object]},
                     :tag :take,
                     :children [:state]},
                    :a_2 {:tag :eval, :form 0},
                    :tag :call,
                    :children [:a_0 :a_1 :a_2]},
                   :tag :give,
                   :children [:state :object]},
                  :variable-id {:tag :eval, :form '?__19265},
                  :expression {:tag :identifier, :id i__19420},
                  :tag :setv,
                  :children [:state :variable-id :expression]},
                 :tag :pass,
                 :children [:state]},
                :else
                {:test
                 {:a_0 {:tag :eval, :form clojure.core/=},
                  :a_1 {:tag :identifier, :id i__19420},
                  :a_2 {:tag :identifier, :id i__19422},
                  :tag :call,
                  :children [:a_0 :a_1 :a_2]},
                 :then
                 {:state
                  {:state
                   {:state {:tag :identifier, :id i__19417},
                    :object
                    {:a_0 {:tag :eval, :form clojure.core/nth},
                     :a_1
                     {:state
                      {:state {:tag :identifier, :id i__19404},
                       :object
                       {:a_0 {:tag :eval, :form clojure.core/nth},
                        :a_1
                        {:state {:tag :identifier, :id i__19384},
                         :tag :take,
                         :children [:state]},
                        :a_2 {:tag :eval, :form 1},
                        :tag :call,
                        :children [:a_0 :a_1 :a_2]},
                       :tag :give,
                       :children [:state :object]},
                      :tag :take,
                      :children [:state]},
                     :a_2 {:tag :eval, :form 0},
                     :tag :call,
                     :children [:a_0 :a_1 :a_2]},
                    :tag :give,
                    :children [:state :object]},
                   :variable-id {:tag :eval, :form '?__19265},
                   :expression {:tag :identifier, :id i__19422},
                   :tag :setv,
                   :children [:state :variable-id :expression]},
                  :tag :pass,
                  :children [:state]},
                 :else
                 {:state
                  {:state {:tag :identifier, :id i__19417},
                   :object
                   {:a_0 {:tag :eval, :form clojure.core/nth},
                    :a_1
                    {:state
                     {:state {:tag :identifier, :id i__19404},
                      :object
                      {:a_0 {:tag :eval, :form clojure.core/nth},
                       :a_1
                       {:state {:tag :identifier, :id i__19384},
                        :tag :take,
                        :children [:state]},
                       :a_2 {:tag :eval, :form 1},
                       :tag :call,
                       :children [:a_0 :a_1 :a_2]},
                      :tag :give,
                      :children [:state :object]},
                     :tag :take,
                     :children [:state]},
                    :a_2 {:tag :eval, :form 0},
                    :tag :call,
                    :children [:a_0 :a_1 :a_2]},
                   :tag :give,
                   :children [:state :object]},
                  :tag :fail},
                 :tag :test,
                 :children [:test :then :else]},
                :tag :test,
                :children [:test :then :else]},
               :children [:expression :body]},
              :children [:expression :body]},
             :children [:expression :body]},
            :body
            {:tag :let,
             :identifier {:tag :identifier, :id i__19438},
             :expression
             {:state
              {:state {:tag :identifier, :id i__19435},
               :object
               {:a_0 {:tag :eval, :form clojure.core/nth},
                :a_1
                {:state
                 {:state {:tag :identifier, :id i__19404},
                  :object
                  {:a_0 {:tag :eval, :form clojure.core/nth},
                   :a_1
                   {:state {:tag :identifier, :id i__19384},
                    :tag :take,
                    :children [:state]},
                   :a_2 {:tag :eval, :form 1},
                   :tag :call,
                   :children [:a_0 :a_1 :a_2]},
                  :tag :give,
                  :children [:state :object]},
                 :tag :take,
                 :children [:state]},
                :a_2 {:tag :eval, :form 1},
                :tag :call,
                :children [:a_0 :a_1 :a_2]},
               :tag :give,
               :children [:state :object]},
              :tag :take,
              :children [:state]},
             :body
             {:tag :let,
              :identifier {:tag :identifier, :id i__19440},
              :expression
              {:state
               {:state {:tag :identifier, :id i__19435},
                :object
                {:a_0 {:tag :eval, :form clojure.core/nth},
                 :a_1
                 {:state
                  {:state {:tag :identifier, :id i__19404},
                   :object
                   {:a_0 {:tag :eval, :form clojure.core/nth},
                    :a_1
                    {:state {:tag :identifier, :id i__19384},
                     :tag :take,
                     :children [:state]},
                    :a_2 {:tag :eval, :form 1},
                    :tag :call,
                    :children [:a_0 :a_1 :a_2]},
                   :tag :give,
                   :children [:state :object]},
                  :tag :take,
                  :children [:state]},
                 :a_2 {:tag :eval, :form 1},
                 :tag :call,
                 :children [:a_0 :a_1 :a_2]},
                :tag :give,
                :children [:state :object]},
               :variable-id {:tag :eval, :form '?__19266},
               :none
               {:form :meander.environment.data.zeta/none,
                :tag :eval},
               :tag :getv,
               :children [:state :variable-id :none]},
              :body
              {:test
               {:a_0 {:tag :eval, :form clojure.core/=},
                :a_1 {:tag :identifier, :id i__19440},
                :a_2
                {:form :meander.environment.data.zeta/none,
                 :tag :eval},
                :tag :call,
                :children [:a_0 :a_1 :a_2]},
               :then
               {:state
                {:state
                 {:state {:tag :identifier, :id i__19435},
                  :object
                  {:a_0 {:tag :eval, :form clojure.core/nth},
                   :a_1
                   {:state
                    {:state {:tag :identifier, :id i__19404},
                     :object
                     {:a_0 {:tag :eval, :form clojure.core/nth},
                      :a_1
                      {:state {:tag :identifier, :id i__19384},
                       :tag :take,
                       :children [:state]},
                      :a_2 {:tag :eval, :form 1},
                      :tag :call,
                      :children [:a_0 :a_1 :a_2]},
                     :tag :give,
                     :children [:state :object]},
                    :tag :take,
                    :children [:state]},
                   :a_2 {:tag :eval, :form 1},
                   :tag :call,
                   :children [:a_0 :a_1 :a_2]},
                  :tag :give,
                  :children [:state :object]},
                 :variable-id {:tag :eval, :form '?__19266},
                 :expression {:tag :identifier, :id i__19438},
                 :tag :setv,
                 :children [:state :variable-id :expression]},
                :tag :pass,
                :children [:state]},
               :else
               {:test
                {:a_0 {:tag :eval, :form clojure.core/=},
                 :a_1 {:tag :identifier, :id i__19438},
                 :a_2 {:tag :identifier, :id i__19440},
                 :tag :call,
                 :children [:a_0 :a_1 :a_2]},
                :then
                {:state
                 {:state
                  {:state {:tag :identifier, :id i__19435},
                   :object
                   {:a_0 {:tag :eval, :form clojure.core/nth},
                    :a_1
                    {:state
                     {:state {:tag :identifier, :id i__19404},
                      :object
                      {:a_0 {:tag :eval, :form clojure.core/nth},
                       :a_1
                       {:state {:tag :identifier, :id i__19384},
                        :tag :take,
                        :children [:state]},
                       :a_2 {:tag :eval, :form 1},
                       :tag :call,
                       :children [:a_0 :a_1 :a_2]},
                      :tag :give,
                      :children [:state :object]},
                     :tag :take,
                     :children [:state]},
                    :a_2 {:tag :eval, :form 1},
                    :tag :call,
                    :children [:a_0 :a_1 :a_2]},
                   :tag :give,
                   :children [:state :object]},
                  :variable-id {:tag :eval, :form '?__19266},
                  :expression {:tag :identifier, :id i__19440},
                  :tag :setv,
                  :children [:state :variable-id :expression]},
                 :tag :pass,
                 :children [:state]},
                :else
                {:state
                 {:state {:tag :identifier, :id i__19435},
                  :object
                  {:a_0 {:tag :eval, :form clojure.core/nth},
                   :a_1
                   {:state
                    {:state {:tag :identifier, :id i__19404},
                     :object
                     {:a_0 {:tag :eval, :form clojure.core/nth},
                      :a_1
                      {:state {:tag :identifier, :id i__19384},
                       :tag :take,
                       :children [:state]},
                      :a_2 {:tag :eval, :form 1},
                      :tag :call,
                      :children [:a_0 :a_1 :a_2]},
                     :tag :give,
                     :children [:state :object]},
                    :tag :take,
                    :children [:state]},
                   :a_2 {:tag :eval, :form 1},
                   :tag :call,
                   :children [:a_0 :a_1 :a_2]},
                  :tag :give,
                  :children [:state :object]},
                 :tag :fail},
                :tag :test,
                :children [:test :then :else]},
               :tag :test,
               :children [:test :then :else]},
              :children [:expression :body]},
             :children [:expression :body]},
            :children [:expression :body]},
           :else
           {:state
            {:state {:tag :identifier, :id i__19404},
             :object
             {:a_0 {:tag :eval, :form clojure.core/nth},
              :a_1
              {:state {:tag :identifier, :id i__19384},
               :tag :take,
               :children [:state]},
              :a_2 {:tag :eval, :form 1},
              :tag :call,
              :children [:a_0 :a_1 :a_2]},
             :tag :give,
             :children [:state :object]},
            :tag :fail},
           :tag :test,
           :children [:test :then :else]},
          :else
          {:state
           {:state {:tag :identifier, :id i__19404},
            :object
            {:a_0 {:tag :eval, :form clojure.core/nth},
             :a_1
             {:state {:tag :identifier, :id i__19384},
              :tag :take,
              :children [:state]},
             :a_2 {:tag :eval, :form 1},
             :tag :call,
             :children [:a_0 :a_1 :a_2]},
            :tag :give,
            :children [:state :object]},
           :tag :fail},
          :tag :test,
          :children [:test :then :else]},
         :children [:expression :body]},
        :children [:expression :body]},
       :body
       {:tag :let,
        :identifier {:tag :identifier, :id i__19460},
        :expression
        {:state
         {:state {:tag :identifier, :id i__19457},
          :object
          {:a_0 {:tag :eval, :form clojure.core/nth},
           :a_1
           {:state {:tag :identifier, :id i__19384},
            :tag :take,
            :children [:state]},
           :a_2 {:tag :eval, :form 2},
           :tag :call,
           :children [:a_0 :a_1 :a_2]},
          :tag :give,
          :children [:state :object]},
         :tag :take,
         :children [:state]},
        :body
        {:tag :let,
         :identifier {:tag :identifier, :id i__19462},
         :expression
         {:state
          {:state {:tag :identifier, :id i__19457},
           :object
           {:a_0 {:tag :eval, :form clojure.core/nth},
            :a_1
            {:state {:tag :identifier, :id i__19384},
             :tag :take,
             :children [:state]},
            :a_2 {:tag :eval, :form 2},
            :tag :call,
            :children [:a_0 :a_1 :a_2]},
           :tag :give,
           :children [:state :object]},
          :variable-id {:tag :eval, :form '?__19267},
          :none
          {:form :meander.environment.data.zeta/none, :tag :eval},
          :tag :getv,
          :children [:state :variable-id :none]},
         :body
         {:test
          {:a_0 {:tag :eval, :form clojure.core/=},
           :a_1 {:tag :identifier, :id i__19462},
           :a_2
           {:form :meander.environment.data.zeta/none, :tag :eval},
           :tag :call,
           :children [:a_0 :a_1 :a_2]},
          :then
          {:state
           {:state
            {:state {:tag :identifier, :id i__19457},
             :object
             {:a_0 {:tag :eval, :form clojure.core/nth},
              :a_1
              {:state {:tag :identifier, :id i__19384},
               :tag :take,
               :children [:state]},
              :a_2 {:tag :eval, :form 2},
              :tag :call,
              :children [:a_0 :a_1 :a_2]},
             :tag :give,
             :children [:state :object]},
            :variable-id {:tag :eval, :form '?__19267},
            :expression {:tag :identifier, :id i__19460},
            :tag :setv,
            :children [:state :variable-id :expression]},
           :tag :pass,
           :children [:state]},
          :else
          {:test
           {:a_0 {:tag :eval, :form clojure.core/=},
            :a_1 {:tag :identifier, :id i__19460},
            :a_2 {:tag :identifier, :id i__19462},
            :tag :call,
            :children [:a_0 :a_1 :a_2]},
           :then
           {:state
            {:state
             {:state {:tag :identifier, :id i__19457},
              :object
              {:a_0 {:tag :eval, :form clojure.core/nth},
               :a_1
               {:state {:tag :identifier, :id i__19384},
                :tag :take,
                :children [:state]},
               :a_2 {:tag :eval, :form 2},
               :tag :call,
               :children [:a_0 :a_1 :a_2]},
              :tag :give,
              :children [:state :object]},
             :variable-id {:tag :eval, :form '?__19267},
             :expression {:tag :identifier, :id i__19462},
             :tag :setv,
             :children [:state :variable-id :expression]},
            :tag :pass,
            :children [:state]},
           :else
           {:state
            {:state {:tag :identifier, :id i__19457},
             :object
             {:a_0 {:tag :eval, :form clojure.core/nth},
              :a_1
              {:state {:tag :identifier, :id i__19384},
               :tag :take,
               :children [:state]},
              :a_2 {:tag :eval, :form 2},
              :tag :call,
              :children [:a_0 :a_1 :a_2]},
             :tag :give,
             :children [:state :object]},
            :tag :fail},
           :tag :test,
           :children [:test :then :else]},
          :tag :test,
          :children [:test :then :else]},
         :children [:expression :body]},
        :children [:expression :body]},
       :children [:expression :body]},
      :else {:state {:tag :identifier, :id i__19384}, :tag :fail},
      :tag :test,
      :children [:test :then :else]},
     :else {:state {:tag :identifier, :id i__19384}, :tag :fail},
     :tag :test,
     :children [:test :then :else]},
    :children [:expression :body]},
   :children [:expression :body]},
  :body
  {:tag :bind,
   :identifier {:tag :identifier, :id i__19481},
   :expression
   {:state
    {:state {:tag :identifier, :id i__19478},
     :object {:tag :eval, :form clojure.core/vec},
     :tag :give,
     :children [:state :object]},
    :tag :pass,
    :children [:state]},
   :body
   {:tag :bind,
    :identifier {:tag :identifier, :id i__19516},
    :expression
    {:tag :bind,
     :identifier {:tag :identifier, :id i__19511},
     :expression
     {:tag :bind,
      :identifier {:tag :identifier, :id i__19496},
      :expression
      {:tag :bind,
       :identifier {:tag :identifier, :id i__19491},
       :expression
       {:tag :let,
        :identifier {:tag :identifier, :id i__19484},
        :expression
        {:state {:tag :identifier, :id i__19481},
         :variable-id {:tag :eval, :form '?__19265},
         :none
         {:form :meander.environment.data.zeta/none, :tag :eval},
         :tag :getv,
         :children [:state :variable-id :none]},
        :body
        {:test
         {:a_0 {:tag :eval, :form clojure.core/=},
          :a_1 {:tag :identifier, :id i__19484},
          :a_2
          {:form :meander.environment.data.zeta/none, :tag :eval},
          :tag :call,
          :children [:a_0 :a_1 :a_2]},
         :then {:state {:tag :identifier, :id i__19481}, :tag :fail},
         :else
         {:state
          {:state {:tag :identifier, :id i__19481},
           :variable-id {:tag :eval, :form '?__19265},
           :expression {:tag :identifier, :id i__19484},
           :tag :setv,
           :children [:state :variable-id :expression]},
          :object {:tag :identifier, :id i__19484},
          :tag :give,
          :children [:state :object]},
         :tag :test,
         :children [:test :then :else]},
        :children [:expression :body]},
       :body
       {:state
        {:state {:tag :identifier, :id i__19491},
         :object
         {:a_0 {:tag :eval, :form clojure.core/vector},
          :a_1
          {:state {:tag :identifier, :id i__19491},
           :tag :take,
           :children [:state]},
          :tag :call,
          :children [:a_0 :a_1]},
         :tag :give,
         :children [:state :object]},
        :tag :pass,
        :children [:state]},
       :children [:expression :body]},
      :body
      {:tag :bind,
       :identifier {:tag :identifier, :id i__19506},
       :expression
       {:tag :let,
        :identifier {:tag :identifier, :id i__19499},
        :expression
        {:state {:tag :identifier, :id i__19496},
         :variable-id {:tag :eval, :form '?__19266},
         :none
         {:form :meander.environment.data.zeta/none, :tag :eval},
         :tag :getv,
         :children [:state :variable-id :none]},
        :body
        {:test
         {:a_0 {:tag :eval, :form clojure.core/=},
          :a_1 {:tag :identifier, :id i__19499},
          :a_2
          {:form :meander.environment.data.zeta/none, :tag :eval},
          :tag :call,
          :children [:a_0 :a_1 :a_2]},
         :then {:state {:tag :identifier, :id i__19496}, :tag :fail},
         :else
         {:state
          {:state {:tag :identifier, :id i__19496},
           :variable-id {:tag :eval, :form '?__19266},
           :expression {:tag :identifier, :id i__19499},
           :tag :setv,
           :children [:state :variable-id :expression]},
          :object {:tag :identifier, :id i__19499},
          :tag :give,
          :children [:state :object]},
         :tag :test,
         :children [:test :then :else]},
        :children [:expression :body]},
       :body
       {:state
        {:state {:tag :identifier, :id i__19506},
         :object
         {:a_0 {:tag :eval, :form clojure.core/conj},
          :a_1
          {:state {:tag :identifier, :id i__19496},
           :tag :take,
           :children [:state]},
          :a_2
          {:state {:tag :identifier, :id i__19506},
           :tag :take,
           :children [:state]},
          :tag :call,
          :children [:a_0 :a_1 :a_2]},
         :tag :give,
         :children [:state :object]},
        :tag :pass,
        :children [:state]},
       :children [:expression :body]},
      :children [:expression :body]},
     :body
     {:state
      {:state {:tag :identifier, :id i__19511},
       :object
       {:a_0 {:tag :eval, :form clojure.core/vector},
        :a_1
        {:state {:tag :identifier, :id i__19511},
         :tag :take,
         :children [:state]},
        :tag :call,
        :children [:a_0 :a_1]},
       :tag :give,
       :children [:state :object]},
      :tag :pass,
      :children [:state]},
     :children [:expression :body]},
    :body
    {:tag :bind,
     :identifier {:tag :identifier, :id i__19521},
     :expression
     {:state
      {:state {:tag :identifier, :id i__19516},
       :object
       {:a_0 {:tag :eval, :form clojure.core/apply},
        :a_1
        {:state {:tag :identifier, :id i__19481},
         :tag :take,
         :children [:state]},
        :a_2
        {:state {:tag :identifier, :id i__19516},
         :tag :take,
         :children [:state]},
        :tag :call,
        :children [:a_0 :a_1 :a_2]},
       :tag :give,
       :children [:state :object]},
      :tag :pass,
      :children [:state]},
     :body
     {:state
      {:state {:tag :identifier, :id i__19516},
       :object
       {:a_0 {:tag :eval, :form clojure.core/apply},
        :a_1
        {:state {:tag :identifier, :id i__19481},
         :tag :take,
         :children [:state]},
        :a_2
        {:state {:tag :identifier, :id i__19516},
         :tag :take,
         :children [:state]},
        :tag :call,
        :children [:a_0 :a_1 :a_2]},
       :tag :give,
       :children [:state :object]},
      :tag :pass,
      :children [:state]},
     :children [:expression :body]},
    :children [:expression :body]},
   :children [:expression :body]},
  :children [:expression :body]},
 :body
 {:state
  {:state {:tag :identifier, :id i__19523},
   :tag :take,
   :children [:state]},
  :tag :pass,
  :children [:state]},
 :children [:expression :body]}

  )

