(ns meander.match.ir.delta
  "Functions for working with the Meander's match compiler
  intermediate representation (IR)."
  (:refer-clojure :exclude [compile])
  #?(:cljs (:require-macros [meander.match.ir.delta :refer [defop]]))
  (:require
   [clojure.zip :as zip]
   [meander.util.delta :as r.util]
   [meander.syntax.delta :as r.syntax]))

;; TODO: create :resolve node for symbols
;; TODO: replace :eval with :resolve where possible
;; TODO: Inline bindings used once
;; TODO: Remove bindings never used

(defn child-keys [ir]
  (keep
   (fn [[k v]]
     (when (some? (:op v))
       k))
   ir))

(defn children [ir]
  (if (map? ir)
    (case (:op ir)
      :branch
      (:arms ir)

      ;; else
      (map ir (child-keys ir)))))

(defn branch? [ir]
  (some? (seq (children ir))))

(defn make-node [ir new-children]
  (case (:op ir)
    :branch
    (assoc ir :arms new-children)

    ;; else
    (into ir (map vector (child-keys ir) new-children))))

(defn ir-zip [ir]
  (zip/zipper branch? children make-node ir))

(defn height
  "Return the height of ir."
  [ir]
  (if-some [dts (children ir)]
    (transduce (comp (map height)
                     (map inc))
               max
               1
               dts)
    1))

(defn nodes
  "Return all nodes in ir."
  [ir]
  (tree-seq branch? children ir))

;; ---------------------------------------------------------------------
;; Tree nodes

(defmacro defop [symbol op params]
  (let [meta {:arglists `'(~params)
              :style/indent :defn} ]
    `(defn ~(vary-meta symbol merge meta) ~params
       (hash-map ~@(mapcat
                    (fn [param]
                      [(keyword (name param)) param])
                    params)
                 :op ~op))))

(defop op-bind :bind [symbol value then])

(defop op-branch :branch [arms])

(defop op-drop :drop [target n kind])

(defop op-nth :nth [target index])

(defop op-pass :pass [then])

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

(defop op-lvr-check :lvr-check [symbol target then])

(defop op-lvr-bind :lvr-bind [symbol target then])

(defop op-mvr-append :mvr-append [symbol target then])

(defop op-mvr-bind :mvr-bind [symbol target then])

(defop op-mvr-init :mvr-init [symbol then])

(defop op-save :save [id body-1 body-2])

;; TODO: No need for :symbol.
(defop op-search :search [symbol value body])

(defn op-star
  {:style/indent :defn}
  [input n kind return-symbols body-fn then]
  (let [input-symbol (gensym "input__")]
    {:op :star
     :input-symbol input-symbol
     :input input
     :body (body-fn input-symbol (op-eval (vec return-symbols)))
     :then then
     :kind kind
     :n n
     :return-symbols (vec return-symbols)}))

(defn op-plus
  {:style/indent :defn}
  [input n m kind return-symbols body-fn then]
  (let [input-symbol (gensym "input__")]
    {:op :plus
     :input-symbol input-symbol
     :input input
     :body (body-fn input-symbol (op-eval (vec return-symbols)))
     :then then
     :kind kind
     :n n
     :m m
     :return-symbols (vec return-symbols)}))

(defop op-take :take [target n kind])

(defop op-fail :fail [])

;; ---------------------------------------------------------------------
;; Code generation
;;
;; TODO: Move all vars intended to be used by compiled code to a
;; meander.runtime.<greek-letter> namespace.

(def FAIL
  "Special value signaling a match failure. Generated code will often
  utilize this value as for control flow purposes."
  (reify))

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

(defn ir-fail?
  {:private true}
  [ir]
  (= (:op ir) :fail))

(defn ir-check?
  [ir]
  (case (:op ir)
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
    false))

(defmulti compile*
  (fn [ir fail kind]
    (:op ir)))

(defmethod compile* :bind
  [ir fail kind]
  (loop [bindings []
         ir ir]
    (if (= (:op ir) :bind)
      (recur (conj bindings (:symbol ir) (compile* (:value ir) fail kind))
             (:then ir))
      `(let ~bindings
         ~(compile* ir fail kind)))))

(defmethod compile* :branch
  [ir fail kind]
  (case kind
    :search
    (let [arms (remove ir-fail? (:arms ir))]
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
                 (remove ir-fail? arms)
                 arms)]
      (case (count arms)
        0
        fail

        1
        (compile* (first arms) fail kind)

        2
        (compile* (first arms)
               (compile* (second arms) fail kind)
               kind)

        ;; else
        (reduce
         (fn [fail arm]
           (compile* arm fail kind))
         fail
         (reverse arms))))))

(defmethod compile* :call
  [ir fail kind]
  `(let [x# (~(:symbol ir) ~(compile* (:target ir) fail kind) ~@(:req-syms ir))]
     (if (identical? x# FAIL)
       ~fail
       (let [[~@(:ret-syms ir)] x#]
         ~(compile* (:then ir) fail kind)))))

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
                     ~(compile* (:body ir) `FAIL kind)))
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
                          (let [~result-sym ~(compile* (:body ir) `FAIL kind)]
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

;; ---------------------------------------------------------------------
;; Tree rewriting

;; :def rewriting

(defn def-remove-unused
  [ir]
  (let [call-symbols (into #{}
                           (comp (filter (comp #{:call} :op))
                                 (map :symbol))
                           (nodes ir))]
    (loop [loc (ir-zip ir)]
      (if (zip/end? loc)
        (zip/root loc)
        (let [node (zip/node loc)]
          (recur
           (case (:op node)
             :def
             (if (contains? call-symbols (:symbol node))
               (zip/next loc)
               (zip/replace loc (:then node)))

             ;; else
             (zip/next loc))))))))

(defn rewrite-def [ir]
  (-> ir
      def-remove-unused))

;; :mvr rewriting

(defn rewrite-move-mvr-init-to-top-level [ir]
  (reduce
   (fn [_ loc]
     (let [node (zip/node loc)]
       (case (:op node)
         :mvr-init
         (let [ir* (zip/root (zip/edit loc :then))]
           (reduced (assoc node :then (rewrite-move-mvr-init-to-top-level ir*))))
         ;; else
         ir)))
   ir
   (r.util/zip-next-seq (ir-zip ir))))

;; :branch rewriting

(defn branch-flatten
  "Equivalent to the rewrite rule

    (rewrite
     (with [%arms [(or {:op :branch
                        :arms %arms}
                       {:op :fail}
                       !arms)
                   ...]]
       {:op :branch
        :arms %arms})
     ;; =>
     {:op :branch
      :arms [!arms ... {:op :fail}]})"
  {:private true}
  [ir]
  (case (:op ir)
    :branch
    (let [arms* (into [] (mapcat
                          (fn f [node]
                            (if (= (:op node) :branch)
                              (mapcat f (:arms node))
                              (list node))))
                      (:arms ir))
          arms* (if (some ir-fail? arms*)
                  (conj (into [] (remove ir-fail?) arms*)
                        (op-fail))
                  arms*)]
      (assoc ir :arms arms*))
    ir))

(defmulti branch-merge-checks*
  (fn [[a b]]
    (let [op-a (:op a)
          op-b (:op b)]
      (if (= op-a op-b)
        op-a
        ::default)))
  :default ::default)

(defmethod branch-merge-checks* ::default
  [[a b]]
  [a b])

(defmethod branch-merge-checks* :bind
  [[a b]]
  (if (= (:target a)
         (:target b))
    [(assoc a
            :then (op-bind (:symbol b) (op-eval (:symbol a))
                    (op-branch [(:then a)
                                (:then b)])))]
    [a b]))

(defmethod branch-merge-checks* :check-bounds
  [[a b]]
  (if (and (= (:target a)
              (:target b))
           (= (:length a)
              (:length b))
           (= (:kind a)
              (:kind b)))
    [(assoc a
            :then (op-branch [(:then a)
                              (:then b)]))]
    [a b]))

(defmethod branch-merge-checks* :check-array
  [[a b]]
  (if (= (:target a)
         (:target b))
    [(assoc a
            :then (op-branch [(:then a)
                              (:then b)]))]
    [a b]))

(defmethod branch-merge-checks* :check-map
  [[a b]]
  (if (= (:target a)
         (:target b))
    [(assoc a
            :then (op-branch [(:then a)
                              (:then b)]))]
    [a b]))

(defmethod branch-merge-checks* :check-lit
  [[a b]]
  (if (= (:target a)
         (:target b))
    [(assoc a
            :then (op-branch [(:then a)
                              (:then b)]))]
    [a b]))

(defmethod branch-merge-checks* :check-seq
  [[a b]]
  (if (= (:target a)
         (:target b))
    [(assoc a
            :then (op-branch [(:then a)
                              (:then b)]))]
    [a b]))

(defmethod branch-merge-checks* :check-vector
  [[a b]]
  (if (= (:target a)
         (:target b))
    [(assoc a
            :then (op-branch [(:then a)
                              (:then b)]))]
    [a b]))

(defn branch-merge-checks
  [ir]
  (case (:op ir)
    :branch
    (loop [arms (:arms ir)
           arms* []]
      (case (count arms)
        (0 1)
        (assoc ir :arms (into arms* arms))
        ;; else
        (let [[a b] (take 2 arms)
              [a* b*] (branch-merge-checks* [a b])]
          (if (some? b*)
            ;; Couldn't merge, consume a.
            (recur (drop 1 arms) (conj arms* a))
            ;; Could merge, drop b, push a*.
            (recur (cons a* (drop 2 arms)) arms*)))))
    ;; else
    ir))


(defn branch-one-arm
  [ir]
  (if (= (:op ir) :branch)
    (if (= (count (:arms ir)) 1)
      (recur (first (:arms ir)))
      ir)
    ir))

(defn rewrite-branch* [ir]
  (-> ir
      branch-flatten
      branch-merge-checks
      branch-one-arm))

(defn rewrite-branch [ir]
  (let [ir* (loop [loc (ir-zip ir)]
              (if (zip/end? loc)
                (zip/root loc)
                (let [node (zip/node loc)]
                  (case (:op node)
                    :branch
                    (let [arms (:arms node)]
                      (case (count arms)
                        0
                        (recur (zip/next loc))

                        1
                        (recur (zip/replace loc (first arms)))

                        ;; else
                        (recur (zip/next (zip/edit loc rewrite-branch*)))))

                    ;; else
                    (recur (zip/next loc))))))]
    (if (= ir ir*)
      ir
      (recur ir*))))

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
                      (if (some ir-check? (nodes body-1))
                        (recur (zip/next loc))
                        (recur (zip/replace loc body-1))))

                    ;; else
                    (recur (zip/next loc))))))]
    (if (= ir ir*)
      ir
      (recur ir*))))

(defn rewrite [ir]
  (-> ir
      rewrite-def
      rewrite-branch
      rewrite-save))

(defn compile [ir fail kind]
  (compile* (rewrite ir) fail kind))
