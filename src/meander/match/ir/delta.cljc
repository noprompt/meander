(ns meander.match.ir.delta
  "Functions for working with the Meander's match compiler
  intermediate representation (IR)."
  (:refer-clojure :exclude [compile merge])
  #?(:cljs (:require-macros [meander.match.ir.delta :refer [defop]]))
  (:require
   [clojure.core :as clj]
   [clojure.spec.alpha :as s]
   [clojure.spec.gen.alpha :as s.gen]
   [clojure.walk :as walk]
   [clojure.zip :as zip]
   [meander.util.delta :as r.util]
   [meander.syntax.delta :as r.syntax]))

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
  :args (s/or :node ::node
              :any any?)
  :ret boolean?)

(def
  ^{:arglists '([node])
    :doc "Return the ::op of node."}
  op :op)

(s/fdef op
  :args ::node
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
          `(hash-map ~@(mapcat
                        (fn [param]
                          [(keyword (name param)) param])
                        params)
                     :op ~op)))))

(defop op-bind :bind [symbol value then])

(defop op-branch :branch [arms])

(defop op-drop :drop [target n kind])

(defop op-eval :eval [form])

(defop op-check :check [test target then])

(defop op-check-array :check-array [target then])

(defop op-check-array-equals :check-array-equals [target-1 target-2 then])

(defop op-check-boolean :check-boolean [test then])

(defop op-check-bounds :check-bounds [target length kind then])

(defop op-check-empty :check-empty [target then])

(defop op-check-equal :check-equal [target-1 target-2 then])

(defop op-check-lit :check-lit [target value then])

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

(defop op-mvr-append :mvr-append [symbol target then])

(defop op-mvr-bind :mvr-bind [symbol target then])

(defop op-mvr-init :mvr-init [symbol then])

(defop op-nth :nth [target index])

(defop op-pass :pass [then])

(defop op-return :return [value])

(defop op-save :save [id body-1 body-2])

;; TODO: No need for :symbol.
(defop op-search :search [symbol value body])

(defop op-star :star [input n kind return-symbols body-fn then]
  (let [input-symbol (gensym "input__")]
    {:input-symbol input-symbol
     :input input
     :body (body-fn input-symbol (op-eval (vec return-symbols)))
     :then then
     :kind kind
     :n n
     :return-symbols (vec return-symbols)}))

(defop op-plus :plus [input n m kind return-symbols body-fn then]
  (let [input-symbol (gensym "input__")]
    {:input-symbol input-symbol
     :input input
     :body (body-fn input-symbol (op-eval (vec return-symbols)))
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

(defn op-fail? [x]
  (op= x :fail))

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

(defmulti merge
  "Attempt to merge node-a and node-b. Returns the result of the merge
  if the merge succeeds. Returns a :branch node with :arms node-a and
  node-b if not."
  {:arglists '([node-a node-b])}
  (fn [a b]
    (if (= (op a) (op b))
      (op a)
      ::default))
  :default ::default)

(defmethod merge ::default
  [a b]
  (if (= a b)
    a
    (op-branch [a b])))

(defmethod merge :bind
  [a b]
  (if (= (:value a)
         (:value b))
    (let [a-symbol (:symbol a)
          b-symbol (:symbol b)]
      (assoc a
             :then
             (merge (:then a)
                    ;; Substitute b-symbol with a-symbol.
                    (walk/postwalk-replace {b-symbol a-symbol}
                                           (:then b)))))
    (op-branch [a b])))

(defn merge-check-coll
  {:private true}
  [a b]
  (if (= (:target a)
         (:target b))
    (assoc a :then (merge (:then a) (:then b)))
    (op-branch [a b])))

(defmethod merge :check-array
  [a b]
  (merge-check-coll a b))

(defmethod merge :check-lit
  [a b]
  (if (and (= (:target a)
              (:target b))
           (= (:value a)
              (:value b)))
    (assoc a :then (merge (:then a) (:then b)))
    (op-branch [a b])))

(defmethod merge :check-map
  [a b]
  (merge-check-coll a b))

(defmethod merge :check-seq
  [a b]
  (merge-check-coll a b))

(defmethod merge :check-set
  [a b]
  (merge-check-coll a b))

(defmethod merge :check-vector
  [a b]
  (merge-check-coll a b))

(defmethod merge :check-bounds
  [a b]
  (if (and (= (:target a)
              (:target b))
           (= (:kind a)
              (:kind b))
           (= (:length a)
              (:length b)))
    (assoc a :then (merge (:then a) (:then b)))
    (op-branch [a b])))

(defmethod merge :lvr-bind
  [a b]
  (if (and (= (:symbol a)
              (:symbol b))
           (= (:target a)
              (:target b)))
    (assoc a :then (merge (:then a) (:then b)))
    (op-branch [a b])))

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
    (let [arms (:arms node)]
      (case (count arms)
        0
        node ;; fail?

        1
        (first arms)

        2
        (let [[a b] arms]
          (merge a b))

        ;; else
        (let [[a b & rest-arms] arms]
          (merge (merge a b)
                 (op-branch rest-arms)))))
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
   (r.util/zip-next-seq (zipper node))))

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
        :branch
        (rewrite-branch-one-case node)
        ;; else
        node))
    (fn g [node]
      (case (op node)
        :branch
        (-> node
            rewrite-branch-one-fail
            rewrite-branch-merge
            rewrite-branch-splice-branches)
        ;; else
        node)))
   node))

(defn rewrite [node]
  (loop [node (def-remove-unused node)]
    (let [node* (rewrite* node)]
      (if (= node* node)
        node
        (recur node*)))))

;; ---------------------------------------------------------------------
;; Code generation
;;
;; TODO: Move all vars intended to be used by compiled code to a
;; meander.runtime.<greek-letter> namespace.

(def FAIL
  "Special value signaling a match failure. Generated code will often
  utilize this value as for control flow purposes."
  (reify))

(defn fail-form
  "Returns `(list FAIL) if kind is :search, `FAIL otherwise. This is
  used when compiling :def nodes to ensure the correct type of data is
  returned to code compiled for :call nodes."
  [kind]
  (case kind
    (:match :find)
    `FAIL

    :search
    `(list FAIL)))

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

(defmulti compile*
  (fn [ir fail kind]
    (:op ir)))

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
        `(concat
          ~@(map
             (fn [ir]
               (compile* ir fail kind))
             arms))))

    (:find :match)
    (let [arms (:arms ir)
          arms (if (= kind :find)
                 (remove op-fail? arms)
                 arms)]
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
             (~(first fsyms))))
        #_
        (reduce
         (fn [fail arm]
           (compile* arm fail kind))
         fail
         (reverse arms))))))

(defmethod compile* :call
  [ir fail kind]
  (case kind
    (:find :match)
    `(let [x# (~(:symbol ir) ~(compile* (:target ir) fail kind) ~@(:req-syms ir))]
       (if (identical? x# FAIL)
         ~fail
         (let [[~@(:ret-syms ir)] x#]
           ~(compile* (:then ir) fail kind))))

    :search
    `(mapcat
      (fn [x#]
        (if (identical? x# FAIL)
          ~fail
          (let [[~@(:ret-syms ir)] x#]
            ~(compile* (:then ir) fail kind))))
      (~(:symbol ir) ~(compile* (:target ir) fail kind) ~@(:req-syms ir)))))

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
  `(if (= ~(compile* (:target ir) fail kind)
          ~(compile* (:value ir) fail kind))
     ~(compile* (:then ir) fail kind)
     ~fail))

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
  (let [result-sym (gensym "result__")
        fail-sym (gensym "fail__")]
    `(let [~result-sym (reduce
                        (fn [~fail-sym ~(:symbol ir)]
                          (let [~result-sym ~(compile* (:body ir) `FAIL :find)]
                            (if (identical? ~result-sym ~fail-sym)
                              ~fail-sym
                              (reduced ~result-sym))))
                        FAIL
                        ~(compile* (:value ir) fail kind))]
       (if (identical? ~result-sym FAIL)
         ~fail
         ~result-sym))))

(defmethod compile* :load
  [ir fail kind]
  `(~(:id ir)))

(defmethod compile* :lookup
  [ir fail kind]
  `(get ~(compile* (:target ir) fail kind)
        ~(compile* (:key ir) fail kind)))


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

(defmethod compile* :mvr-append
  [ir fail kind]
  (let [!symbol (:symbol ir)]
    `(let [~!symbol (conj ~!symbol ~(compile* (:target ir) fail kind))]
       ~(compile* (:then ir) fail kind))))

(defmethod compile* :mvr-init
  [ir fail kind]
  `(let [~(:symbol ir) []]
     ~(compile* (:then ir) fail kind)))

(defmethod compile* :pass
  [ir fail kind]
  (compile* (:then ir) fail kind))

(defmethod compile* :plus
  [ir fail kind]
  (let [coll-sym (gensym "coll__")
        input-sym (:input-symbol ir)
        return-syms (:return-symbols ir)
        n (:n ir)
        m (:m ir)
        body-form (compile* (:body ir) `FAIL (case kind :search :find kind))
        then-form (compile* (:then ir) fail kind)]
    `(loop [i# 0
            ~coll-sym ~(compile* (:input ir) fail kind)
            ~return-syms ~(:return-symbols ir)]
       (let [~input-sym ~(take-form n coll-sym (:kind ir))]
         (if (= (count ~input-sym) ~n)
           (let [result# ~body-form]
             (if (identical? result# FAIL)
               ~fail
               (recur (inc i#) ~(drop-form n coll-sym (:kind ir)) result#)))
           ;; Failed to consume
           (if (or (seq ~coll-sym)
                   (< i# ~m))
             ~fail
             ~then-form))))))

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
      `(letfn [(~id [] ~fail)]
         ~(compile* body-1 `(~id) kind))
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
        return-syms (:return-symbols ir)
        n (:n ir)
        body-form (compile* (:body ir) `FAIL (case kind :search :find kind))
        then-form (compile* (:then ir) fail kind)]
    `(loop [~coll-sym ~(compile* (:input ir) fail kind)
            ~return-syms ~(:return-symbols ir)]
      (let [~input-sym ~(take-form n coll-sym (:kind ir))]
        (if (= (count ~input-sym) ~n)
          (let [result# ~body-form]
            (if (identical? result# FAIL)
              ~fail
              (recur ~(drop-form n coll-sym (:kind ir)) result#)))
          ;; Failed to consume
          (if (seq ~coll-sym)
            ~fail
            ~then-form))))))

(defmethod compile* :take
  [ir fail kind]
  (take-form (:n ir)
             (compile* (:target ir) fail kind)
             (:kind ir)))

(defmethod compile* :default
  [ir fail kind]
  ir)

(defn compile [ir fail kind]
  (compile* (rewrite ir) fail kind))
