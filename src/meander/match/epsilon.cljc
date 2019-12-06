(ns ^:no-doc meander.match.epsilon
  (:refer-clojure :exclude [compile find])
  #?(:cljs (:require-macros [meander.match.epsilon]))
  (:require [#?(:clj clojure.core :cljs cljs.core) :as clojure]
            [#?(:clj clojure.pprint :cljs cljs.pprint) :as pprint]
            [clojure.set :as set]
            [clojure.spec.alpha :as s]
            [clojure.walk :as walk]
            [clojure.zip :as zip]
            [meander.match.check.epsilon :as r.match.check]
            [meander.match.ir.epsilon :as r.ir]
            [meander.matrix.epsilon :as r.matrix]
            [meander.syntax.epsilon :as r.syntax]
            [meander.match.syntax.epsilon :as r.match.syntax]
            [meander.match.runtime.epsilon :as r.match.runtime]
            [meander.util.epsilon :as r.util]
            #?(:cljs [goog.object :as gobj]))
  #?(:clj
     (:import (cljs.tagged_literals JSValue))))

(def
  ^{:dynamic true
    :doc "true if currently compiling a matrix derived from a not pattern,
  false otherwise."}
  *negating* false)


(defn negating?
  "true if currently compiling a matrix derived from a not pattern,
  false otherwise."
  []
  (true? *negating*))


(def
  ^{:dynamic true
    :doc "The current collection context e.g. :vector, :seq, etc."}
  *collection-context*)


(defn vector-context?
  "true if the current value of *collect-context* is :vector."
  []
  (= *collection-context* :vector))


(defn js-array-context?
  "true if the current value of *collect-context* is :js-array."
  []
  (= *collection-context* :js-array))


(def
  ^{:doc "If a pattern match has a catamorphism this will be bound to
  a symbol representing its function name."
    :dynamic true}
  *cata-symbol*
  nil)


(defn gensym*
  "Like gensym but adds additional meta data which can be used by the
  match compiler."
  {:private true}
  ([]
   (with-meta (gensym) {:meander/gensym? true}))
  ([prefix-string]
   (with-meta (gensym prefix-string) {:meander/gensym? true})))


(defn gensym?
  "true if x is an internally generate gensym, false otherwise."
  {:private true}
  ([x]
   (and (symbol? x) (::gensym? (meta x)))))


(defn get-spec-map
  {:private true}
  [ref-spec-map ref-node env]
  (if-some [[_ ref-specs] (clojure/find ref-spec-map ref-node)]
    (apply max-key
           (fn [ref-spec]
             (count (:vars ref-spec)))
           (filter
            (fn [ref-spec]
              (every? env (:vars ref-spec)))
            ref-specs))
    nil))

;; TODO: Document
(defn make-ref-spec-map
  {:private true}
  [ref-map]
  (into {} (map
            (fn [[ref node]]
              (let [vars (r.syntax/variables (r.syntax/substitute-refs node ref-map))
                    rets (vec vars)
                    ret-syms (mapv :symbol rets)]
                [ref (into [{:symbol (gensym* "def__")
                             :vars #{}
                             :reqs []
                             :rets rets
                             :node node}]
                           (comp
                            (mapcat
                             (fn [i]
                               (r.util/k-combinations vars i)))
                            (map set)
                            (distinct)
                            (map vec)
                            (map (fn [reqs]
                                   {:symbol (gensym* "def__")
                                    :vars (set reqs)
                                    :reqs reqs
                                    :rets rets
                                    :node node})))
                           (range 1 (inc (count vars))))])))
        ref-map))

(declare compile)

(defn literal?
  "true if node is ground and does not contain :map or :set subnodes,
  false otherwise.

  The constraint that node may not contain :map or :set subnodes is
  due to the semantics of map and set patterns: they express submap
  and subsets respectively. Compiling these patterns to literals as
  part of an equality check would result in false negative matches.

  See also: compile-ground"
  [node]
  (and (r.syntax/ground? node)
       (not-any? (comp #{:map :unq :set} r.syntax/tag)
                 (r.syntax/subnodes node))))


;; Use solved?* instead.
(defn solved?
  "true if all logic variables occuring in node are bound in env and
  node is free of memory variables, false otherwise."
  [node env]
  (if (r.syntax/ground? node)
    true
    (let [vars (r.syntax/variables node)]
      (if (some r.syntax/mvr-node? vars)
        false
        (every? env vars)))))

(defn solved?*
  "true if all logic variables occuring in `node` are bound in `env`
  and `node` is free of memory variables and non-literls, false
  otherwise."
  {:private true}
  [node env]
  (cond
    (r.syntax/literal? node)
    true

    (r.syntax/lvr-node? node)
    (contains? env node)

    (or (r.syntax/map-node? node)
        (r.syntax/mut-node? node)
        (r.syntax/mvr-node? node)
        (r.syntax/set-node? node))
    false

    (or (r.syntax/prt-node? node)
        (r.syntax/seq-node? node)
        (r.syntax/vec-node? node))
    (every? (fn [child-node]
              (solved?* child-node env))
            (r.syntax/children node))

    :else
    false))


(defn compile-ground
  "Compile node as a literal if possible."
  [node]
  (case (r.syntax/tag node)
    :cat
    (map compile-ground (:elements node))

    :jsa
    #?(:clj
       (JSValue. (vec (compile-ground (:prt node))))
       :cljs
       (into-array (compile-ground (:prt node))))

    :lit
    (r.syntax/unparse node)

    :lvr
    (r.syntax/unparse node)

    :map
    (into {}
          (map (fn [[k v]]
                 [(compile-ground k)
                  (compile-ground v)]))
          (:map node))

    :prt
    (concat (compile-ground (:left node))
            (compile-ground (:right node)))

    :unq
    (:expr node)

    :quo
    (list 'quote (:form node))

    :vec
    (into [] (compile-ground (:prt node)))

    :seq
    (if-some [l (seq (compile-ground (:prt node)))]
      (cons `list l)
      ())

    :set
    (into #{} (map compile-ground (:elements node)))))

(defn compile-pass
  {:private true}
  [targets matrix]
  (r.ir/op-pass (compile targets matrix)))


(def ^{:doc "Node tags ordered from highest precedence to lowest."
       :private true}
  tag-ranking
  [:mut
   :lit
   :quo])

(defn tag-rank
  "Returns the rank of a tag. Used to compute the score
  of a pattern matrix column."
  {:private true}
  [tag]
  (let [i (.indexOf tag-ranking tag)]
    (if (= i -1)
      (count tag-ranking)
      i)))

(s/fdef tag-rank
  :args (s/cat :tag keyword?)
  :ret nat-int?)


(defn score-column
  "Returns the total score of a pattern matrix column"
  {:private true}
  [column]
  (transduce
   (comp (map :tag)
         (map tag-rank))
   +
   0
   column))

(s/fdef score-column
  :args (s/cat :column (s/coll-of ::r.syntax/node :kind sequential?))
  :ret nat-int?)


(defn prioritize-matrix
  "Reorganizes a the pattern targets and matrix columns according to
  the score of each column. Columns are resorted from lowest to
  highest score."
  [targets matrix]
  (let [columns (r.matrix/columns matrix)
        scores (map score-column columns)
        targets* (map second (sort-by first (map vector scores targets)))
        columns* (map second (sort-by first (map vector scores columns)))
        matrix* (map-indexed
                 (fn [i cols]
                   (assoc (nth matrix i) :cols cols))
                 (apply map vector columns*))]
    [(vec targets*) (vec matrix*)]))


(defn specialize-matrix
  "Retains rows of the matrix whose tag is tag or `:any`."
  [tag matrix]
  (into []
        (keep-indexed
         (fn [i node]
           (let [other-tag (r.syntax/tag node)]
             (when (or (= other-tag tag)
                       (= other-tag :any))
               (nth matrix i)))))
        (r.matrix/first-column matrix)))


(defmulti compile-specialized-matrix
  "Compile the matrix specialized for tag with respect to targets to a
  sequence of decision trees."
  {:arglists '([tag targets matrix])}
  (fn [tag targets matrix]
    tag))


(s/fdef compile-specialized-matrix
  :args (s/cat :tag keyword?
               :targets (s/coll-of simple-symbol? :kind vector? :into [])
               :matrix :meander.matrix.epsilon/matrix))


(defmethod compile-specialized-matrix :any
  [_ targets matrix]
  (mapv (fn [node row]
          (compile-pass targets [row]))
        (r.matrix/first-column matrix)
        (r.matrix/drop-column matrix)))


(defmethod compile-specialized-matrix ::r.match.syntax/apply
  [_ [target & targets*] matrix]
  (mapv
   (fn [node row]
     (case (r.syntax/tag node)
       :any
       (compile-pass targets* [row])

       ::r.match.syntax/apply
       (r.ir/op-apply* target (:function node)
         (fn [result-target]
           (compile `[~result-target ~@targets*]
                    (r.matrix/prepend-column [row] [(:argument node)]))))))
   (r.matrix/first-column matrix)
   (r.matrix/drop-column matrix)))


(defmethod compile-specialized-matrix ::r.match.syntax/and
  [_ [target & targets*] matrix]
  (let [max-args (reduce
                  (fn [n node]
                    (case (r.syntax/tag node)
                      :any n
                      ::r.match.syntax/and (max n (count (:arguments node)))))
                  0
                  (r.matrix/first-column matrix))
        targets* `[~@(repeat max-args target) ~@targets*]
        matrix* (mapv
                 (fn [node row]
                   (case (r.syntax/tag node)
                     :any
                     (assoc row :cols `[~@(repeat max-args node) ~@(:cols row)])

                     ::r.match.syntax/and
                     (let [arguments (:arguments node)]
                       (assoc row :cols `[~@arguments
                                          ~@(repeat (- max-args (count arguments))
                                                    {:tag :any, :symbol '_})
                                          ~@(:cols row)]))))
                 (r.matrix/first-column matrix)
                 (r.matrix/drop-column matrix))]
    [(compile targets* matrix*)]))

(defmethod compile-specialized-matrix ::r.match.syntax/cata
  [_ [target & targets*] matrix]
  (mapv
   (fn [node row]
     (case (r.syntax/tag node)
       :any
       (compile-pass targets* [row])

       ::r.match.syntax/cata
       (let [cata-return (gensym "CATA_RETURN__")]
         {:op :call
          :symbol *cata-symbol*
          :target (r.ir/op-eval target)
          :req-syms []
          :ret-syms [cata-return]
          :then (compile `[~cata-return ~@targets*]
                         (r.matrix/prepend-column [row] [(get node :argument)]))})))
   (r.matrix/first-column matrix)
   (r.matrix/drop-column matrix)))


(defmethod compile-specialized-matrix :cat
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)
        max-size (reduce
                  (fn [n node]
                    (case (r.syntax/tag node)
                      :any n
                      :cat (max n (count (:elements node)))))
                  0
                  (r.matrix/first-column matrix))
        nth-syms (mapv
                  (fn [i]
                    (symbol (str target "_nth_" i "__")))
                  (range max-size))]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets* [row])

         :cat
         (if (literal? node)
           (if (js-array-context?)
             (r.ir/op-check-array-equals
               (r.ir/op-eval target)
               (r.ir/op-eval (compile-ground
                              {:tag :jsa
                               :prt {:tag :prt
                                     :left node
                                     :right {:tag :cat
                                             :elements []}}}))
               (compile targets* [row]))
             (r.ir/op-check-equal
               (r.ir/op-eval target)
               (r.ir/op-eval (vec (compile-ground node)))
               (compile targets* [row])))
           (let [elements (:elements node)
                 nth-syms (take (count elements) nth-syms)
                 targets* `[~@nth-syms ~@targets*]]
             (reduce
              (fn [tree [i nth-sym elem]]
                (case (r.syntax/tag elem)
                  :any tree
                  (r.ir/op-bind nth-sym (r.ir/op-nth (r.ir/op-eval target) i)
                    tree)))
              (compile targets* [(assoc row :cols `[~@elements ~@(:cols row)])])
              (reverse (map vector (range max-size) nth-syms elements)))))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))

(defmethod compile-specialized-matrix :ctn
  [_ [target & targets*] matrix]
  (mapv
   (fn [node row]
     (case (r.syntax/tag node)
       :any
       (compile-pass targets* [row])

       :ctn
       (let [context (:context node)
             pattern (:pattern node)
             ;; Bound zipper location if :context supplied.
             loc-sym (symbol (str target "_loc_"))
             ;; Bound to zipper node and tested against :pattern.
             node-sym (symbol (str target "_node__"))
             matrix* (as-> [row] %matrix
                       (if (some? context)
                         (r.matrix/prepend-column %matrix
                                                  [{:tag ::r.match.syntax/let
                                                    :pattern context
                                                    :expression `(fn [x#]
                                                                   (zip/root (zip/replace ~loc-sym x#)))}])
                         %matrix)
                       (r.matrix/prepend-column %matrix
                                                [{:tag ::r.match.syntax/let
                                                  :pattern pattern
                                                  :expression node-sym}]))
             targets* (as-> [node-sym] %targets
                        (if (some? context)
                          (conj %targets loc-sym))
                        (into %targets targets*))]
         (if (some? context)
           (r.ir/op-search loc-sym (r.ir/op-eval `(r.match.runtime/zip-next-seq (r.match.runtime/coll-zip ~target)))
             (r.ir/op-bind node-sym (r.ir/op-eval `(zip/node ~loc-sym))
               (compile targets* matrix*)))
           (r.ir/op-search node-sym (r.ir/op-eval `(r.match.runtime/coll-seq ~target))
             (compile targets* matrix*))))))
   (r.matrix/first-column matrix)
   (r.matrix/drop-column matrix)))


(defmethod compile-specialized-matrix :drp
  [_ [target & targets*] matrix]
  [(compile (vec targets*) (r.matrix/drop-column matrix))])


(defmethod compile-specialized-matrix ::r.match.syntax/or
  [_ targets matrix]
  (let [targets* (vec (rest targets))]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets [row])

         ::r.match.syntax/or
         (let [unbound-mvrs (r.matrix/unbound-mvrs row node)
               row* (r.matrix/add-vars row unbound-mvrs)]
           (reduce
            (fn [dt mvr]
              (r.ir/op-mvr-init (:symbol mvr)
                dt))
            (compile targets (mapv
                              (fn [node]
                                (first (r.matrix/prepend-column [row*] [node])))
                              (:arguments node)))
            unbound-mvrs))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix ::r.match.syntax/guard
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets* [row])

         ::r.match.syntax/guard
         (r.ir/op-check (r.ir/op-eval (:expr node))
           (compile targets* [row]))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :jsa
  [_ [target & targets* :as targets] matrix]
  (mapv
   (fn [node row]
     (case (r.syntax/tag node)
       :any
       (compile-pass targets [row])

       :jsa
       (if (literal? node)
         (r.ir/op-check-array-equals
           (r.ir/op-eval target)
           (r.ir/op-eval (compile-ground node))
           (compile targets* [row]))
         (r.ir/op-check-array (r.ir/op-eval target)
           (let [;; prt needs to be compiled within a :js-array
                 ;; collection-context separately from the targets*
                 ;; to the right. The targets* on the right need to
                 ;; be compiled in an environment including variables
                 ;; bound by compiling prt.
                 prt (:prt node)
                 rhs*-env (into (get row :env) (r.syntax/variables prt))
                 rhs*-row (assoc row :env rhs*-env)
                 rhs* (compile targets* [rhs*-row])
                 row* (assoc row :cols [prt] :rhs rhs*)]
             (binding [*collection-context* :js-array]
               (compile [target] [row*])))))))
   (r.matrix/first-column matrix)
   (r.matrix/drop-column matrix)))


(defn jso-matrix-all-keys
  "Return a sequence of all :jso keys in matrix."
  {:private true}
  [matrix]
  (mapcat
   (fn [node]
     (case (r.syntax/tag node)
       :jso
       (keys (:object node))
       ()))
   (r.matrix/first-column matrix)))


(defmethod compile-specialized-matrix :jso
  [_ [target & targets*] matrix]
  (let [ranked-keys (r.util/rank (jso-matrix-all-keys matrix))]
    ;; Recompile with object keys aligned. For example if the pattern
    ;; conditions were
    ;;
    ;;   #js {:x ?1, :y ?2, :z ?3}
    ;;   #js {:x ?1}
    ;;   #js {:w ?2, :z ?3}
    ;;
    ;; then we would organize the matrix as
    ;;
    ;;   [:x ?1] [:z ?3] [:y ?2] [:w __]
    ;;   [:x ?1] [:z __] [:y __] [:w __]
    ;;   [:x __] [:z ?3] [:y __] [:w ?2]
    [(r.ir/op-check-boolean
       (r.ir/op-eval `(some? ~target)) ;; This may be a bit liberal.
       (compile `[~@(repeat (count ranked-keys) target) ~@targets*]
                (mapv
                 (fn [row]
                   (let [[node & rest-nodes] (get row :cols)]
                     (case (r.syntax/tag node)
                       :jso
                       (let [object (:object node)
                             prefix (mapv
                                     (fn [key-node]
                                       (if-some [entry (clojure/find object key-node)]
                                         {:tag :okv
                                          :entry entry}
                                         {:tag :okv
                                          :entry [key-node {:tag :any
                                                            :symbol '_}]}))
                                     ranked-keys)]
                         (assoc row :cols `[~@prefix ~@rest-nodes]))
                       row)))
                 matrix)))]))


(defmethod compile-specialized-matrix :okv
  [_ [target & targets* :as targets] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets* [row])

         :okv
         (let [entry (:entry node)
               [key-node val-node] entry]
           (if (r.syntax/ground? key-node)
             (let [node* {:tag ::r.match.syntax/let
                          :pattern val-node
                          :expression (list 'js* "(~{}[~{}])" target (compile-ground key-node))}
                   matrix* (r.matrix/prepend-column [row] [node*])]
               (compile targets matrix*))
             ;; The #js {} reader only allows keys that are strings or
             ;; unqualified keywords. Without and alternative notation
             ;; this branch should never be entered.
             (let [node* {:tag :cat
                          :elements [key-node val-node]}
                   matrix* (r.matrix/prepend-column [row] [node*])
                   search-target (gensym* "okv__")]
               (r.ir/op-search search-target (r.ir/op-eval
                                               `(map (fn [k#]
                                                       [k# (gobj/get ~target k#)])
                                                     (gobj/getKeys ~target)))
                 (compile `[~search-target ~@targets*] matrix*)))))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix ::r.match.syntax/let
  [_ [target & targets* :as targets] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets [row])

         ::r.match.syntax/let
         (let [xsym (gensym* "x__")
               targets* `[~xsym ~@targets*]
               matrix* (r.matrix/prepend-column [row] [(:pattern node)])]
           (r.ir/op-bind xsym (r.ir/op-eval (:expression node))
             (compile targets* matrix*)))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :lit
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (r.ir/op-pass (compile targets* [row]))

         :lit
         (let [then (compile targets* [row])]
           (r.ir/op-check-lit
             (r.ir/op-eval target)
             (r.ir/op-eval (r.syntax/unparse node))
             then))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :lvr
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (r.ir/op-pass (compile targets* [row]))

         :lvr
         (if (r.matrix/get-var row node)
           (r.ir/op-lvr-check (:symbol node) (r.ir/op-eval target)
             (compile targets* [(r.matrix/add-var row node)]))
           (r.ir/op-lvr-bind (:symbol node) (r.ir/op-eval target)
             (compile targets* [(r.matrix/add-var row node)])))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defn map-matrix-all-keys
  "Return a sequence of all :map keys in matrix."
  {:private true}
  [matrix]
  (mapcat
   (fn [node]
     (when (= (r.syntax/tag node) :map)
       (keys (:map node))))
   (r.matrix/first-column matrix)))

(defn non-search-lvars
  {:private true}
  [node env]
  (set/union
   env
   (reduce (fn [acc node]
             (case (r.syntax/tag node)
               :lvr (conj acc node)
               :map (set/union acc (non-search-lvars node env))
               nil))
           #{}
           (map second
                (filter (fn [[k v]] (r.syntax/ground? k)) (:map node))))))

(defn find-search-keys
  {:private true}
  [node env]
  (set/difference
   (set (remove r.syntax/ground? (keys (:map node))))
   env))

(defn search-map?
  {:private true}
  [node env]
  (or (some (fn [[k v]]
              (r.syntax/mvr-node? k))
            (get node :map))
      (not (empty? (set/difference
                    (find-search-keys node env)
                    (non-search-lvars node env))))))


(defn find-search-keys-recursive
  {:private true}
  [env [k v]]
  (concat
   (find-search-keys {:tag :map :map {k v}} env)
   (if (map? k) (mapcat (partial find-search-keys-recursive env) (:map k)) '())
   (if (map? v) (mapcat (partial find-search-keys-recursive env) (:map v)) '())))

(defn sort-by-search-keys
  {:private true}
  [the-map env]
  (sort-by
   (comp count (partial find-search-keys-recursive env))
   the-map))

(defmethod compile-specialized-matrix :map
  [_ [target & targets*] matrix]
  (let [all-keys (map-matrix-all-keys matrix)
        num-keys (count all-keys)
        matrix* (if (zero? num-keys)
                  (mapv (fn [node row]
                          (r.matrix/prepend-cells row [{:tag :any :symbol '_}]))
                        (r.matrix/first-column matrix)
                        (r.matrix/drop-column matrix))
                  (mapv
                   (fn [node row]
                     (case (r.syntax/tag node)
                       :any
                       (r.matrix/prepend-cells row (repeat num-keys node))

                       :map
                       (let [the-map (:map node)
                             env (get row :env)
                             the-map* (sort-by-search-keys the-map env)]
                         (if (search-map? node env)
                           (r.matrix/prepend-cells row
                             (into [{:tag ::r.match.syntax/apply
                                     :function `set
                                     :argument {:tag :set
                                                :elements (map (fn [[k-node v-node]]
                                                                 {:tag :cat
                                                                  :elements [k-node v-node]})
                                                               the-map*)}}]
                                   (repeat (dec num-keys) '{:tag :any, :symbol _})))
                           (r.matrix/prepend-cells row
                             (into (mapv (fn [key]
                                           {:tag :mkv
                                            :entry (clojure/find the-map key)})
                                         (keys the-map*))
                                   (repeat (- num-keys (count the-map*))
                                           '{:tag :any :symbol _})))))))
                   (r.matrix/first-column matrix)
                   (r.matrix/drop-column matrix)))]
    [(r.ir/op-check-map (r.ir/op-eval target)
       (compile `[~@(repeat (max 1 num-keys) target) ~@targets*] matrix*))]))


(defmethod compile-specialized-matrix :mkv
  [_ [target & targets* :as targets] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets* [row])

         :mkv
         (let [[key-node val-node] (:entry node)
               val-target (gensym* "VAL__")]
           (r.ir/op-bind val-target (r.ir/op-lookup (r.ir/op-eval target) (r.ir/op-eval (compile-ground key-node)))
             (compile `[~val-target ~@targets*] (r.matrix/prepend-column [row] [val-node]))))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :mut
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets* [row])

         :mut
         (r.ir/op-mut-bind (:symbol node) (r.ir/op-eval target)
           (compile targets* [(r.matrix/add-var row node)]))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :mvr
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets* [row])

         :mvr
         (let [sym (:symbol node)]
           (if (r.matrix/get-var row node)
             (let [save-id (gensym* "save__")]
               ;; save/load is necessary here since it is possible
               ;; for the state of a memory variable to persist
               ;; even after a match failure.
               (r.ir/op-save save-id
                 (r.ir/op-mvr-append sym (r.ir/op-eval target)
                   (compile targets* [row]))
                 (r.ir/op-load save-id)))
             (r.ir/op-mvr-init sym
               (r.ir/op-mvr-append sym (r.ir/op-eval target)
                 (compile targets* [(r.matrix/add-var row node)])))))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix ::r.match.syntax/not
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets* [row])

         ::r.match.syntax/not
         (let [save-id (gensym "save__")
               not-matrix [(assoc row
                                  :cols [(:argument node)]
                                  :rhs (r.ir/op-load save-id))]]
           (r.ir/op-save save-id
             (binding [*negating* true]
               (compile [target] not-matrix))
             (compile targets* [row])))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :prt
  [_ [target & targets* :as targets] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets* [row])

         :prt
         (let [{:keys [left right]} node
               ;; Left tree symbol
               lsym (symbol (str target "_l__"))
               ;; Left min length
               llen (r.syntax/min-length left)
               ;; Right tree symbol
               rsym (symbol (str target "_r__"))
               ;; Right min length
               rlen (r.syntax/min-length right)
               ;; Target length symbol
               nsym (gensym "n__")
               ;; Target length symbol minus either the left or right min length
               msym (gensym "m__")
               ;; The optional as node
               as-node (:as node)]
           (case [(r.syntax/variable-length? left) (r.syntax/variable-length? right)]
             ;; Invariable length.
             [false false]
             (cond
               (and (zero? llen)
                    (zero? rlen))
               (r.ir/op-check-empty
                 (r.ir/op-eval target)
                 (compile targets [row]))

               (zero? llen)
               (r.ir/op-check-bounds (r.ir/op-eval target) rlen *collection-context*
                 (compile targets [(assoc row :cols `[~right ~@(:cols row)])]))

               (zero? rlen)
               (r.ir/op-check-bounds (r.ir/op-eval target) llen *collection-context*
                 (compile targets [(assoc row :cols `[~left ~@(:cols row)])]))

               :else
               (let [inner-ir (compile `[~lsym ~rsym ~@targets*]
                                [(r.matrix/prepend-cells row [left right])])]
                 (r.ir/op-bind lsym (r.ir/op-take (r.ir/op-eval target) llen *collection-context*)
                   (r.ir/op-check-bounds (r.ir/op-eval lsym) llen *collection-context*
                     (r.ir/op-bind rsym (r.ir/op-drop (r.ir/op-eval target) llen *collection-context*)
                       (if (r.syntax/prt-node? right)
                         ;; `inner-ir` will do the bounds checking on
                         ;; the right if `right` is a `:prt` node.
                         inner-ir
                         (r.ir/op-check-bounds (r.ir/op-eval rsym) rlen *collection-context*
                           inner-ir)))))))

             ;; Variable length on the right.
             [false true]
             (let [op-body (r.ir/op-bind rsym (r.ir/op-drop (r.ir/op-eval target) llen *collection-context*)
                             (compile `[~lsym ~rsym ~@targets*]
                                      [(assoc row :cols `[~left ~right ~@(:cols row)])]))]
               (r.ir/op-bind lsym (r.ir/op-take (r.ir/op-eval target) llen *collection-context*)
                 (if (zero? llen)
                   (r.ir/op-check-empty (r.ir/op-eval lsym)
                     op-body)
                   (r.ir/op-check-bounds (r.ir/op-eval lsym) llen *collection-context*
                     op-body))))

             ;; Variable length on the left.
             [true false]
             (if (zero? rlen)
               (compile targets [(assoc row :cols `[~left ~@(:cols row)])])
               (let [op-target (r.ir/op-eval target)
                     op-body (compile `[~lsym ~rsym ~@targets*]
                                      [(assoc row :cols `[~left ~right ~@(:cols row)])])]
                 (r.ir/op-bind nsym (r.ir/op-eval `(count ~target))
                   (r.ir/op-bind msym (r.ir/op-eval `(max 0 (- ~nsym ~rlen)))
                     (r.ir/op-bind lsym (r.ir/op-take op-target msym *collection-context*)
                       (r.ir/op-bind rsym (r.ir/op-drop op-target msym *collection-context*)
                         (if (zero? rlen)
                           (r.ir/op-check-empty (r.ir/op-eval rsym)
                             op-body)
                           (r.ir/op-check-bounds (r.ir/op-eval rsym) rlen *collection-context*
                             op-body))))))))

             ;; Variable length on both sides.
             [true true]
             (let [parts-sym (symbol (str target "_parts__"))]
               (if-some [[cat-node right] (r.syntax/window node)]
                 (let [elements (:elements cat-node)
                       cat-length (count elements)]
                   (case cat-length
                     0
                     (compile-pass `[~target ~@targets*]
                                   (r.matrix/prepend-column [row] [right]))

                     1
                     (if right
                       (r.ir/op-search parts-sym `(r.match.runtime/partitions 2 ~target)
                         (r.ir/op-bind lsym (r.ir/op-nth (r.ir/op-eval parts-sym) 0)
                           (r.ir/op-bind rsym (r.ir/op-nth (r.ir/op-eval parts-sym) 1)
                             (let [partition-sym (symbol (str target "_partition__"))]
                               (r.ir/op-search partition-sym (r.ir/op-eval lsym)
                                 (compile `[~partition-sym ~rsym ~@targets*]
                                          [(r.matrix/prepend-cells row [(first elements) right])]))))))
                       (r.ir/op-search parts-sym (r.ir/op-eval target)
                         (compile `[~parts-sym ~@targets*]
                                  [(r.matrix/prepend-cells row [(first elements)])])))

                     ;; else
                     (if right
                       (r.ir/op-search parts-sym `(r.match.runtime/partitions 2 ~cat-length ~target)
                         (r.ir/op-bind lsym (r.ir/op-nth (r.ir/op-eval parts-sym) 0)
                           (r.ir/op-bind rsym (r.ir/op-nth (r.ir/op-eval parts-sym) 1)
                             (let [partition-sym (symbol (str target "_partition__"))]
                               (r.ir/op-search partition-sym (r.ir/op-eval `(partition ~cat-length 1 ~lsym))
                                 (compile `[~partition-sym ~rsym ~@targets*]
                                          [(r.matrix/prepend-cells row [cat-node right])]))))))
                       (r.ir/op-search parts-sym (r.ir/op-eval `(partition ~cat-length 1 ~target))
                         (compile `[~parts-sym ~@targets*]
                                  [(r.matrix/prepend-cells row [cat-node])])))))
                 (r.ir/op-search parts-sym (r.ir/op-eval `(r.match.runtime/partitions 2 ~target))
                   (r.ir/op-bind lsym (r.ir/op-nth (r.ir/op-eval parts-sym) 0)
                     (r.ir/op-bind rsym (r.ir/op-nth (r.ir/op-eval parts-sym) 1)
                       (compile `[~lsym ~rsym ~@targets*]
                                [(assoc row :cols `[~left ~right ~@(:cols row)])]))))))))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix ::r.match.syntax/pred
  [_ [target & targets* :as targets] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets* [row])

         ::r.match.syntax/pred
         (let [arguments (:arguments node)
               form (:form node)
               eval-form (if (:meander.epsilon/beta-reduce (meta form))
                           (let [[_fn [arg] & body] form]
                             (r.ir/op-eval `(let [~arg ~target] ~@body)))
                           (r.ir/op-eval `(~form ~target)))]
           (r.ir/op-check-boolean eval-form
             (if (seq arguments)
               (compile targets
                        [(assoc row :cols `[~{:tag ::r.match.syntax/and
                                              :arguments arguments}
                                            ~@(:cols row)])])
               (compile targets* [row]))))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :quo
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets* [row])

         :quo
         (r.ir/op-check-equal
           (r.ir/op-eval target)
           (r.ir/op-eval (r.syntax/unparse node))
           (compile targets* [row]))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix ::r.match.syntax/rxt
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets* [row])

         ::r.match.syntax/rxt
         (r.ir/op-check-boolean (r.ir/op-eval `(string? ~target))
           (r.ir/op-check-boolean (r.ir/op-eval `(re-matches ~(:regex node) ~target))
             (compile targets* [row])))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix ::r.match.syntax/rxc
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets* [row])

         ::r.match.syntax/rxc
         (let [ret-sym (gensym* "ret__")
               cols* `[~(:capture node) ~@(:cols row)]
               row* (assoc row :cols cols*)]
           (r.ir/op-check-boolean (r.ir/op-eval `(string? ~target))
             (r.ir/op-bind ret-sym (r.ir/op-eval `(re-matches ~(:regex node) ~target))
               (r.ir/op-check-boolean (r.ir/op-eval `(some? ~ret-sym))
                 (compile `[~ret-sym ~@targets*] [row*])))))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :ref
  [_ targets matrix]
  (let [target (nth targets 0)
        targets* (vec (rest targets))]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets [row])

         :ref
         (if-some [ref-spec (get-spec-map (:ref-specs row) node (:env row))]
           {:op :call
            :symbol (:symbol ref-spec)
            :target (r.ir/op-eval target)
            :req-syms (mapv :symbol (:reqs ref-spec))
            :ret-syms (mapv :symbol (:rets ref-spec))
            :then (compile targets* [(r.matrix/add-vars row (:rets ref-spec))])}
           (throw (ex-info "Unbound reference" {:rest-row row, :col node})))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :rp*
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets* [row])

         :rp*
         (let [cat-node (:cat node)
               n (count (:elements cat-node))
               ;; Unbound memory variables must be bound before loop
               ;; execution and added to the compilation environment
               ;; for the internal loop body.
               unbound-mvrs (set/difference
                             (r.syntax/memory-variables
                              (r.syntax/substitute-refs cat-node (:refs row)))
                             (r.matrix/bound-mvrs row))
               row* (r.matrix/add-vars row unbound-mvrs)
               mvr-syms (map :symbol (r.matrix/bound-mvrs row*))]
           (reduce
            (fn [op-tree mvr-node]
              (r.ir/op-mvr-init (:symbol mvr-node)
                op-tree))
            (r.ir/op-star (r.ir/op-eval target) n *collection-context*
              ;; Symbols to bind
              mvr-syms
              ;; Result of this is accumulated.
              (fn [input-symbol dt-return]
                (compile [input-symbol]
                         [(assoc row* :cols [cat-node] :rhs dt-return)]))
              (compile targets* [row*]))
            unbound-mvrs))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :rp+
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets* [row])

         :rp+
         (let [cat-node (:cat node)
               n (count (:elements cat-node))
               ;; Minimum number of times subsequence must match.
               m (:n node)
               unbound-mvrs (set/difference
                             (r.syntax/memory-variables
                              (r.syntax/substitute-refs cat-node (:refs row)))
                             (r.matrix/bound-mvrs row))
               row* (r.matrix/add-vars row unbound-mvrs)
               mvr-syms (map :symbol (r.matrix/bound-mvrs row*))]
           (reduce
            (fn [op-tree mvr-node]
              (r.ir/op-mvr-init (:symbol mvr-node)
                op-tree))
            (r.ir/op-plus (r.ir/op-eval target) n m *collection-context*
              ;; Symbols to bind
              mvr-syms
              ;; Result of this is accumulated.
              (fn [input-symbol dt-return]
                (compile [input-symbol]
                         [(assoc row* :cols [cat-node] :rhs dt-return)]))
              (compile targets* [row*]))
            unbound-mvrs))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :rpl
  [_ [target :as targets] matrix]
  (let [matrix* (mapv
                 (fn [node row]
                   (case (r.syntax/tag node)
                     :any
                     (r.matrix/prepend-cells row [node node])

                     :rpl
                     (let [cat-node (:cat node)
                           cat-length (count (:elements cat-node))
                           rp*-node {:tag :rp*
                                     :cat cat-node}
                           app-node {:tag ::r.match.syntax/apply
                                     :function `clojure.core/count
                                     :argument (if (= cat-length 1)
                                                 (:lvr node)
                                                 {:tag ::r.match.syntax/apply
                                                  :function (vary-meta
                                                             `(fn [n#] (/ n# ~cat-length))
                                                             assoc
                                                             :meander.epsilon/beta-reduce true)
                                                  :argument (:lvr node)})}]
                       ;; If the logic variable is bound we place the
                       ;; `::apply` node ahead of the `:rp*` node to
                       ;; verify the `count` is equal before attemping
                       ;; to pattern match on the input.
                       (if (r.matrix/get-var row (:lvr node))
                         (r.matrix/prepend-cells row [app-node rp*-node])
                         (r.matrix/prepend-cells row [rp*-node app-node])))))
                 (r.matrix/first-column matrix)
                 (r.matrix/drop-column matrix))
        targets*  `[~target ~@targets]]
    [(compile targets* matrix*)]))


(defmethod compile-specialized-matrix :rpm
  [_ [target :as targets] matrix]
  (let [matrix* (mapv
                 (fn [node row]
                   (case (r.syntax/tag node)
                     :any
                     (r.matrix/prepend-cells row [node node])

                     :rpm
                     (let [cat-node (:cat node)
                           cat-length (count (:elements cat-node))
                           rp*-node {:tag :rp*
                                     :cat cat-node}
                           app-node {:tag ::r.match.syntax/apply
                                     :function `clojure.core/count
                                     :argument (if (= cat-length 1)
                                                 (:mvr node)
                                                 {:tag ::r.match.syntax/apply
                                                  :function (vary-meta
                                                             `(fn [n#] (/ n# ~cat-length))
                                                             assoc
                                                             :meander.epsilon/beta-reduce true)
                                                  :argument (:mvr node)})}]
                       (r.matrix/prepend-cells row [rp*-node app-node]))))
                 (r.matrix/first-column matrix)
                 (r.matrix/drop-column matrix))
        targets* `[~target ~@targets]]
    [(compile targets* matrix*)]))


(defmethod compile-specialized-matrix :rst
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets* [row])

         :rst
         (let [mvr (:mvr node)
               sym (:symbol mvr)]
           (if (r.matrix/get-var row mvr)
             (r.ir/op-mvr-bind sym (r.ir/op-eval `(into ~sym ~target))
               (compile targets* [(r.matrix/add-var row mvr)]))
             (r.ir/op-mvr-bind sym (r.ir/op-eval `(vec ~target))
               (compile targets* [(r.matrix/add-var row mvr)]))))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :set
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets* [row])

         :set
         (cond
           (r.syntax/search? node)
           (let [elements (:elements node)
                 n (count elements)
                 ;; Symbol for each permutation of target.
                 perm-sym (gensym* "perm__")
                 targets** `[~perm-sym ~@targets*]
                 matrix* (if (= n 1)
                           [(assoc row :cols `[~(first elements) ~@(:cols row)])]
                           [(assoc row :cols `[~{:tag :cat
                                                 :elements (vec elements)}
                                               ~@(:cols row)])])
                 search-space (if (= n 1)
                                `(seq ~target)
                                `(r.match.runtime/k-combinations ~target ~n))]
             (r.ir/op-check-set (r.ir/op-eval target)
               (r.ir/op-check-bounds (r.ir/op-eval target) n :set
                 (if (negating?)
                   (r.ir/op-find perm-sym (r.ir/op-eval search-space)
                     (compile targets** matrix*))
                   (r.ir/op-search perm-sym (r.ir/op-eval search-space)
                     (compile targets** matrix*))))))

           (some (comp #{:map :set} r.syntax/tag)
                 (r.syntax/proper-subnodes node))
           (let [elements (:elements node)
                 n (count elements)
                 ;; Symbol for each permutation of target.
                 perm-sym (gensym* "perm__")
                 targets** `[~perm-sym ~@targets*]
                 matrix* (if (= n 1)
                           [(assoc row :cols `[~(first elements) ~@(:cols row)])]
                           [(assoc row :cols `[~{:tag :cat
                                                 :elements (vec elements)}
                                               ~@(:cols row)])])
                 search-space (if (= n 1)
                                `(seq ~target)
                                `(r.match.runtime/k-combinations ~target ~n))]
             (r.ir/op-check-set (r.ir/op-eval target)
               (r.ir/op-check-bounds (r.ir/op-eval target) n :set
                 (r.ir/op-find perm-sym (r.ir/op-eval search-space)
                   (compile targets** matrix*)))))

           :else
           (r.ir/op-check-set (r.ir/op-eval target)
             (r.ir/op-check-boolean (r.ir/op-eval `(set/subset? ~(compile-ground node) ~target))
               (compile targets* [row]))))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :seq
  [_ [target & targets* :as targets] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets [row])

         :seq
         (r.ir/op-check-seq (r.ir/op-eval target)
           (if (literal? node)
             (r.ir/op-check-lit (r.ir/op-eval target) (r.ir/op-eval (r.syntax/lit-form node))
               (compile targets* [row]))
             (binding [*collection-context* :seq]
               (compile targets [(assoc row :cols `[~(:prt node) ~@(:cols row)])]))))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :tail
  [_ targets matrix]
  [(compile targets
            (mapv
             (fn [node row]
               (case (r.syntax/tag node)
                 :any
                 (r.matrix/prepend-cells row [node])

                 :tail
                 (r.matrix/prepend-cells row [(:pattern node)])))
             (r.matrix/first-column matrix)
             (r.matrix/drop-column matrix)))])


(defmethod compile-specialized-matrix :unq
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets* [row])

         :unq
         (r.ir/op-check-equal (r.ir/op-eval target) (r.ir/op-eval (:expr node))
           (compile targets* [row]))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :vec
  [_ [target & targets* :as targets] matrix]
  (mapv
   (fn [node row]
     (case (r.syntax/tag node)
       :any
       (compile-pass targets [row])

       :vec
       (if (literal? node)
         (r.ir/op-check-vector (r.ir/op-eval target)
           (r.ir/op-check-lit (r.ir/op-eval target) (r.ir/op-eval (r.syntax/lit-form node))
             (compile targets* [row])))
         (r.ir/op-check-vector (r.ir/op-eval target)
           (let [;; prt needs to be compiled within a :vector
                 ;; collection-context separately from the targets*
                 ;; to the right. The targets* on the right need to
                 ;; be compiled in an environment including variables
                 ;; bound by compiling prt.
                 prt (:prt node)
                 rhs*-env (into (get row :env) (r.syntax/variables prt))
                 rhs*-row (assoc row :env rhs*-env)
                 rhs* (compile targets* [rhs*-row])
                 row* (assoc row :cols [prt] :rhs rhs*)]
             (binding [*collection-context* :vector]
               (compile [target] [row*])))))))
   (r.matrix/first-column matrix)
   (r.matrix/drop-column matrix)))


(defn compile-wth-matrix [targets matrix]
  (let [target (nth targets 0)
        targets* (vec (rest targets))]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets* [row])

         :wth
         (if-some [body (:body node)]
           (let [ref-map (r.syntax/make-ref-map node)
                 refs* (merge (:refs row) ref-map)
                 ref-spec-map (make-ref-spec-map refs*)
                 ref-spec-map* (merge (:ref-specs row) ref-spec-map)
                 bound-mvrs (r.matrix/bound-mvrs row)
                 unbound-mvrs (into #{}
                                    (mapcat
                                     (fn [[_ node]]
                                       (set/difference
                                        (r.syntax/memory-variables
                                         (r.syntax/substitute-refs node refs*))
                                        bound-mvrs)))
                                    ref-map)
                 row* (assoc row :refs refs* :ref-specs ref-spec-map*)
                 row* (r.matrix/add-vars row* unbound-mvrs)
                 matrix* (r.matrix/prepend-column [row*] [body])]
             ;; Initialize memory variables to prevent an explosion of
             ;; data brought on by potentially differing memory
             ;; variable sets in each pattern.
             (reduce
              (fn [dt node]
                (r.ir/op-mvr-init (:symbol node)
                  dt))
              ;; Compile nodes for all possible defs.
              (reduce
               (fn [dt spec-map]
                 (let [target-arg (gensym* "arg__")
                       ret-syms (mapv :symbol (:rets spec-map))]
                   {:op :def
                    :symbol (:symbol spec-map)
                    :target-arg target-arg
                    :req-syms (mapv :symbol (:reqs spec-map))
                    :ret-syms ret-syms
                    :body (binding [*negating* false]
                            (compile [target-arg]
                                     [(assoc (r.matrix/add-vars row* (:reqs spec-map))
                                             :cols [(:node spec-map)]
                                             :rhs (r.ir/op-return ret-syms))]))
                    :then dt}))
               (compile targets matrix*)
               (mapcat identity (vals ref-spec-map)))
              unbound-mvrs))
           (compile-pass targets* [row]))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :wth
  [_ targets matrix]
  (compile-wth-matrix targets matrix))


(defn debug-compile
  {:private true}
  [targets matrix]
  (pprint/print-table
   (mapcat
    (fn [row]
      (map
       (fn [target node]
         {:target target
          :pattern (r.syntax/unparse node)
          :node node})
       targets
       (:cols row)))
    matrix)))


(defn compile
  "Compile the pattern matrix with respect to targets to a decision
  tree."
  [targets matrix]
  (cond
    ;; Nothing in the matrix; fail.
    (= (count matrix) 0)
    (r.ir/op-fail)

    ;; _ is in every cell of the first row; return the action.
    (r.matrix/any-row? (first matrix))
    (r.matrix/action (first matrix))

    ;; _ is in every cell of the first column; drop the first column,
    ;; recurse.
    (r.matrix/any-column? matrix 0)
    (compile (rest targets) (r.matrix/drop-column matrix))

    :else
    (let [[targets* matrix*] (prioritize-matrix targets matrix)
          tags (into []
                     (comp (map r.syntax/tag)
                           (distinct))
                     (r.matrix/first-column matrix*))
          arms (into [] (mapcat
                         (fn [tag]
                           (let [s-matrix (specialize-matrix tag matrix*)]
                             (compile-specialized-matrix tag targets* s-matrix))))
                     tags)
          arms (if (not (some (fn [op]
                                (= (:op op) :pass))
                              arms))
                 (conj arms (r.ir/op-fail))
                 arms)]
      (r.ir/op-branch arms))))


;; ---------------------------------------------------------------------
;; match macro


(s/def :meander.match.epsilon/expr
  any?)


(s/def :meander.match.epsilon/pattern
  any?)


(s/def :meander.match.epsilon/clause
  (s/cat :pat :meander.match.epsilon/pattern
         :rhs :meander.match.epsilon/expr))


(s/def :meander.match.epsilon.match/clauses
  (s/* (s/cat :pat :meander.match.epsilon/pattern
              :rhs :meander.match.epsilon/expr)))


(s/def :meander.match.epsilon.match/args
  (s/cat :expr :meander.match.epsilon/expr
         :clauses (s/* :meander.match.epsilon/clause)))


(s/def :meander.match.epsilon.match/data
  (s/keys :req-un [:meander.match.epsilon/expr
                   :meander.matrix.epsilon/matrix
                   :meander.matrix.epsilon.data/final-clause]))


(s/def :meander.match.epsilon.match.data/final-clause
  (s/nilable :meander.matrix.alpha/row))


(defn parse-expand
  {:private true}
  ([x]
   (parse-expand x {}))
  ([x env]
   (r.match.syntax/expand-ast (r.match.syntax/parse x env))))

(defn parse-match-args
  [match-args env]
  (let [data (s/conform :meander.match.epsilon.match/args match-args)]
    (if (identical? data ::s/invalid)
      {:errors [(ex-info "Invalid match-args" (s/explain-data :meander.match.epsilon.match/args match-args))]}
      (let [clauses (map
                     (fn [{:keys [pat rhs]}]
                       (let [node (r.match.syntax/parse pat env)]
                         {:pat node
                          :contains-cata? (r.match.syntax/contains-cata-node? node)
                          :rhs rhs}))
                     (:clauses data))
            contains-cata? (some :contains-cata? clauses)]
        {:clauses clauses
         :contains-cata? contains-cata?
         :expr (get data :expr)}))))

(defn analyze-match-args
  "Analyzes arguments as would be supplied to the match macro e.g.

    (expr clause action ,,,)

  Returns a map containing the following keys:

  :errors A sequence of errors. These are instances of
    clojure.lang.Exception and are derived by applying
    `meander.match.check.epsilon/check` to the pattern of each clause.
  :expr The expression which is the target of pattern matching, the
    first argument to the match macro.
  :exhaustive? Boolean value indicating whether or not the match
    clauses are exhaustive. true if :final-clause is present, false
    otherwise.
  :final-clause The pattern matrix row which is the first catch-all
    pattern matching clause.
  :matrix The pattern matrix derived from the (clause action ,,,)
    forms. If :final-clause is present contains all of the rows above
    :final-clause and none of the rows below it."
  ([match-args]
   (analyze-match-args match-args {}))
  ([match-args env]
   (let [result (parse-match-args match-args env)]
     (if-some [error (first (get result :errors))]
       (throw error)
       (let [clauses (get result :clauses)
             contains-cata? (boolean (some :contains-cata? clauses))
             errors (into []
                          (keep
                           (fn [{pat :pat}]
                             (r.match.check/check pat false)))
                          clauses)
             matrix (mapv
                     (fn [clause]
                       (r.matrix/make-row
                        [(r.match.syntax/expand-ast (:pat clause))]
                        (if contains-cata?
                          (r.ir/op-return [(:rhs clause)])
                          (r.ir/op-return (:rhs clause)))))
                     clauses)
             final-clause (some
                           (fn [row]
                             (let [node (first (:cols row))]
                               (case (r.syntax/tag node)
                                 (:any :lvr :mvr)
                                 row
                                 ;; else
                                 nil)))
                           matrix)
             ;; Drop clauses below the final clause; they're redundant.
             matrix (if final-clause
                      (vec (take-while (partial not= final-clause) matrix))
                      matrix)]
         {:cata-symbol (gensym "CATA__FN__")
          :contains-cata? contains-cata?
          :errors errors
          :expr (get result :expr)
          :exhaustive? (some? final-clause)
          :final-clause final-clause
          :matrix matrix})))))

(s/fdef analyze-match-args
  :args (s/alt :a1 (s/cat :match-args :meander.match.epsilon.match/args)
               :a2 (s/cat :match-args :meander.match.epsilon.match/args
                          :env any?))
  :ret :meander.match.epsilon.match/data)

(defmacro match
  "Traditional pattern matching operator.

  Syntax

      (match x
        pattern_1 expr_1
        ,,,
        pattern_n expr_n)

  Attempts to pattern match `x` against one of patterns `pattern_1`
  through `pattern_n`. If some pattern `pattern_i` matches
  successfully, `expr_i` will be executed. If none of the patterns
  match successfully an error will be thrown indicating the pattern
  match failed.

  This operator restricts patterns which may have several possible
  solutions. For example, the pattern

      #{?x ?y}

  matches any set with at least two elements. However, with
  consideration to the property that Clojure sets are unordered, there
  are many possible ways we could bind values for `?x` and
  `?y`. Because there is no obvious way to know which solution to
  pick, patterns which have this property are illegal in the context
  of this operator.

  For operators which relax this restriction, see `find` and `search`."
  {:arglists '([x & clauses])
   :style/indent [1]}
  [& match-args]
  (let [match-data (analyze-match-args match-args &env)]
    (if-some [error (first (get match-data :errors))]
      (throw error)
      (let [expr (get match-data :expr)
            matrix (get match-data :matrix)
            final-clause (get match-data :final-clause)
            final-clause? (some? final-clause)]
        (if (r.matrix/empty? matrix)
          (if final-clause?
            (r.ir/compile (compile [expr] [final-clause]) nil :match &env)
            `(throw (ex-info "non exhaustive pattern match" '~(merge {} (meta &form)))))
          (let [contains-cata? (get match-data :contains-cata?)
                symbol-target? (symbol? expr)
                target (if symbol-target?
                         expr
                         (gensym "TARGET__"))
                ir (if contains-cata?
                     (let [matrix (if final-clause?
                                    (conj matrix final-clause)
                                    matrix)]
                       (binding [*cata-symbol* (get match-data :cata-symbol)]
                         (let [cata-return (gensym "CATA_RETURN__")]
                           {:op :def
                            :symbol *cata-symbol*
                            :target-arg target
                            :req-syms []
                            :ret-syms []
                            :body (compile [target] matrix)
                            :then {:op :call
                                   :symbol *cata-symbol*
                                   :target (r.ir/op-eval target)
                                   :req-syms []
                                   :ret-syms [cata-return]
                                   :then (r.ir/op-return cata-return)}})))
                     (compile [target target] matrix))]
            (case [final-clause? contains-cata?]
              [false false]
              (let [ir (if symbol-target?
                         ir
                         (r.ir/op-bind target (r.ir/op-eval expr)
                           ir))
                    fail `(throw (ex-info "non exhaustive pattern match" '~(merge {} (meta &form))))]
                (r.ir/compile ir fail :match &env))

              [false true]
              (let [fail `(throw (ex-info "non exhaustive pattern match" '~(merge {} (meta &form))))
                    code (r.ir/compile ir fail :match &env)]
                (if symbol-target?
                  code
                  `(let [~target ~expr]
                     ~code)))

              [true false]
              (let [fail (gensym "FAIL__")
                    fail-fn `(fn []
                               ~(r.ir/compile (compile [target] [final-clause]) nil :match &env))
                    ir (r.ir/op-bind fail (r.ir/op-eval fail-fn)
                           ir)
                    ir (if symbol-target?
                         ir
                         (r.ir/op-bind target (r.ir/op-eval expr)
                           ir))]
                (r.ir/compile ir `(~fail) :match &env))

              [true true]
              (let [fail (gensym "FAIL__")
                    fail-fn `(fn []
                               ~(r.ir/compile (compile [target] [final-clause]) nil :match &env))
                    ir (r.ir/op-bind fail (r.ir/op-eval fail-fn)
                         ir)
                    code (r.ir/compile ir `(~fail) :match &env)]
                (if symbol-target?
                  code
                  `(let [~target ~expr]
                     ~code))))))))))


(s/fdef match
  :args (s/cat :expr any?
               :clauses :meander.match.epsilon.match/clauses)
  :ret any?)


(defn analyze-search-args
  "Analyzes arguments as would be supplied to the search macro e.g.

    (expr clause action ,,,)

  Returns a map containing the following keys:

  :errors A sequence of errors. These are instances of
    clojure.lang.Exception and are derived by applying
    `meander.match.check.epsilon/check` to the pattern of each clause.
  :expr The expression which is the target of pattern matching, the
    first argument to the match macro.
  :matrix The pattern matrix derived from the (clause action ,,,)
    forms. Each action expression is wrapped in a list."
  ([match-args]
   (analyze-search-args match-args {}))
  ([match-args env]
   (let [result (parse-match-args match-args env)]
     (if-some [error (first (get result :errors))]
       (throw error)
       (let [clauses (get result :clauses)
             contains-cata? (boolean (some :contains-cata? clauses))
             errors (into [] (keep
                              (fn [{pat :pat}]
                                (r.match.check/check pat true)))
                          clauses)
             matrix (mapv
                     (fn [clause]
                       (let [rhs (get clause :rhs)]
                         (r.matrix/make-row
                          [(r.match.syntax/expand-ast (:pat clause))]
                          (if contains-cata?
                            (r.ir/op-return [rhs])
                            (r.ir/op-return rhs)))))
                     clauses)]
         {:cata-symbol (gensym "CATA__FN__")
          :cata-return (gensym "CATA__RETURN__")
          :contains-cata? contains-cata?
          :errors errors
          :expr (get result :expr)
          :matrix matrix})))))


(defn compile-search-analysis
  [search-analysis env]
  (let [matrix (get search-analysis :matrix)]
    (if (r.matrix/empty? matrix)
      nil
      (let [expr (get search-analysis :expr)
            symbol-target? (symbol? expr)
            contains-cata? (get search-analysis :contains-cata?)
            target (if symbol-target?
                     expr
                     (gensym "TARGET__"))]
        (if contains-cata?
          ;; We cannot use the `op-bind` approach as below because
          ;; the IR compiler will use the type information of the
          ;; expr to eliminate clauses that won't match which can
          ;; be too aggressive when a catamorphism is present.
          (binding [*cata-symbol* (get search-analysis :cata-symbol)]
            (let [cata-return (gensym "CATA_RETURN__")
                  ir {:op :def
                      :symbol *cata-symbol*
                      :target-arg target
                      :req-syms []
                      :ret-syms [cata-return]
                      :body (compile [target] matrix)
                      :then {:op :call
                             :symbol *cata-symbol*
                             :target (r.ir/op-eval target)
                             :req-syms []
                             :ret-syms [cata-return]
                             :then (r.ir/op-return cata-return)}}
                  code (r.ir/compile ir nil :search env)]
              (if symbol-target?
                code
                `(let [~target ~expr]
                   ~code))))
          (let [ir (compile [target] matrix)
                ir (if symbol-target?
                     ir
                     (r.ir/op-bind target (r.ir/op-eval expr)
                       ir))]
            (r.ir/compile ir nil :search env)))))))

(defmacro search
  "Like `match` but allows for patterns which may match `x` in more
  than one way. Returns a lazy sequence of expression values in
  depth-first order.

  Example

      (search [1 2 3]
        [!xs ... !ys ...]
        {'!xs !xs, '!ys !ys})
      ;; =>
      ({!xs [], !ys [1 2 3]}
       {!xs [1], !ys [2 3]}
       {!xs [1 2], !ys [3]}
       {!xs [1 2 3], !ys []})

  Note, if only the first value is needed, use `find` instead. The
  expression

      (first (search x ,,,))

  can be significantly slower than

      (find x ,,,)"
  {:arglists '([x & clauses])
   :style/indent [1]}
  [& match-args]
  (let [env (merge (meta &form) &env)
        search-analysis (analyze-search-args match-args env)]
    (if-some [error (first (get search-analysis :errors))]
      (throw error)
      (compile-search-analysis search-analysis env))))


(s/fdef search
  :args (s/cat :expr any?
               :clauses :meander.match.epsilon.match/clauses)
  :ret (s/coll-of any? :kind sequential?))


(defn analyze-find-args
  "Analyzes arguments as would be supplied to the find macro e.g.

    (expr clause action ,,,)

  Returns a map containing the following keys:

  :errors A sequence of errors. These are instances of
    clojure.lang.Exception and are derived by applying
    `meander.match.check.epsilon/check` to the pattern of each clause.
  :expr The expression which is the target of pattern matching, the
    first argument to the match macro.
  :matrix The pattern matrix derived from the (clause action ,,,)
    forms. Each action expression is wrapped in a list."
  ([match-args]
   (analyze-find-args match-args {}))
  ([match-args env]
   (let [result (parse-match-args match-args env)]
     (if-some [error (first (get result :errors))]
       result
       (let [clauses (get result :clauses)
             contains-cata? (boolean (some :contains-cata? clauses))
             errors (into [] (keep
                              (fn [{pat :pat}]
                                (r.match.check/check pat true)))
                          clauses)
             matrix (mapv
                     (fn [clause]
                       (r.matrix/make-row
                        [(r.match.syntax/expand-ast (:pat clause))]
                        (if contains-cata?
                          (r.ir/op-return [(:rhs clause)])
                          (r.ir/op-return (:rhs clause)))))
                     clauses)
             final-clause (some
                           (fn [row]
                             (let [node (first (:cols row))]
                               (case (r.syntax/tag node)
                                 (:any :lvr :mvr)
                                 row
                                 ;; else
                                 nil)))
                           matrix)
             matrix (if final-clause
                      (into [] (take-while (partial not= final-clause)) matrix)
                      matrix)]
         {:cata-symbol (gensym "CATA__FN__")
          :contains-cata? contains-cata?
          :errors errors
          :expr (get result :expr)
          :final-clause final-clause
          :matrix matrix})))))


(defn compile-find-analysis
  [find-analysis env]
  (let [matrix (get find-analysis :matrix)
        final-clause (get find-analysis :final-clause)
        matrix (if (some? final-clause)
                 (conj matrix final-clause)
                 matrix)]
    (if (r.matrix/empty? matrix)
      nil
      (let [expr (get find-analysis :expr)
            symbol-target? (symbol? expr)
            contains-cata? (get find-analysis :contains-cata?)
            target (if symbol-target?
                     expr
                     (gensym "TARGET__"))]
        (if contains-cata?
          (binding [*cata-symbol* (get find-analysis :cata-symbol)]
            (let [cata-return (gensym "CATA_RETURN__")
                  ir {:op :def
                      :symbol *cata-symbol*
                      :target-arg target
                      :req-syms []
                      :ret-syms [cata-return]
                      :body (compile [target] matrix)
                      :then {:op :call
                             :symbol *cata-symbol*
                             :target (r.ir/op-eval target)
                             :req-syms []
                             :ret-syms [cata-return]
                             :then (r.ir/op-return cata-return)}}
                  code (r.ir/compile ir nil :find env)]
              (if symbol-target?
                code
                `(let [~target ~expr]
                   ~code))))
          (let [ir (compile [target] matrix)
                ir (if symbol-target?
                     ir
                     (r.ir/op-bind target (r.ir/op-eval expr)
                       ir))]
            (r.ir/compile ir nil :find env)))))))


(defmacro find
  "Like `search` but returns only the first successful match."
  {:arglists '([x & clauses])
   :style/indent [1]}
  [& match-args]
  (let [env (merge (meta &form) &env)
        find-analysis (analyze-find-args match-args env)]
    (if-some [error (first (get find-analysis :errors))]
      (throw error)
      (compile-find-analysis find-analysis env))))

(s/fdef find
  :args (s/cat :expr any?
               :clauses :meander.match.epsilon.match/clauses)
  :ret any?)
