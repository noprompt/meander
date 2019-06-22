(ns meander.match.epsilon
  (:refer-clojure :exclude [compile find])
  #?(:cljs (:require-macros [meander.match.epsilon]))
  (:require [#?(:clj clojure.core :cljs cljs.core) :as clojure]
            [#?(:clj clojure.pprint :cljs cljs.pprint) :as pprint]
            [clojure.set :as set]
            [clojure.spec.alpha :as s]
            [clojure.walk :as walk]
            [clojure.zip :as zip]
            [meander.match.ir.epsilon :as r.ir]
            [meander.matrix.epsilon :as r.matrix]
            [meander.syntax.epsilon :as r.syntax]
            [meander.match.syntax.epsilon :as r.match.syntax]
            [meander.util.epsilon :as r.util]
            #?(:cljs [goog.object :as gobj]))
  #?(:clj
     (:import (cljs.tagged_literals JSValue))))

(def
  ^{:dynamic true
    :private true
    :doc ""}
  *env* {})


(def
  ^{:dynamic true
    :doc ""}
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
   :quo
   ;; The tags below are toggled off because some of them can break
   ;; the linear semantics of pattern matching. More investigation is
   ;; needed.
   #_:unq
   #_:any
   #_:drp
   #_:mvr
   #_:rst
   #_:rxt
   #_:lvr
   #_:grd
   #_:prd
   #_:vec
   #_:seq
   #_:jsa
   #_:jso
   #_:cat
   #_:mkv
   #_:okv
   #_:rxc
   #_:set
   #_:map
   #_:prt
   #_:ctn
   #_:rp*
   #_:rp+
   #_:ref
   #_:wth
   #_:app
   #_:let*
   #_:let
   #_:not
   #_:cnj
   #_:dsj])

(defn tag-rank
  "Returns the rank of a tag. Used to compute the score
  of a pattern matrix column."
  {:private true}
  [tag]
  (let [i (.indexOf tag-ranking tag)]
    (case i
      -1 (count tag-ranking)
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
  :args (s/cat :column (s/coll-of :r.syntax/node :kind sequential?))
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
  "Retains rows of the matrix whose tag is tag or :any."
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
               :matrix :meander.matrix.epsilon/matrix)
  :ret (s/coll-of :meander.match.epsilon/tree))


(defmethod compile-specialized-matrix :app
  [_ [target & targets*] matrix]
  (mapv
   (fn [node row]
     (case (r.syntax/tag node)
       :any
       (compile-pass targets* [row])

       :app
       (r.ir/op-apply target (:fn-expr node)
         (fn [result-target]
           (compile `[~result-target ~@targets*]
                    (r.matrix/prepend-column [row] [{:tag :cnj
                                                     :arguments (:arguments node)}]))))))
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
                    (gensym* (str "nth_" i "__")))
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


(defmethod compile-specialized-matrix :cnj
  [_ [target & targets*] matrix]
  (let [max-args (reduce
                  (fn [n node]
                    (case (r.syntax/tag node)
                      :any n
                      :cnj (max n (count (:arguments node)))))
                  0
                  (r.matrix/first-column matrix))
        targets* `[~@(repeat max-args target) ~@targets*]
        matrix* (mapv
                 (fn [node row]
                   (case (r.syntax/tag node)
                     :any
                     (assoc row :cols `[~@(repeat max-args node) ~@(:cols row)])

                     :cnj
                     (let [arguments (:arguments node)]
                       (assoc row :cols `[~@arguments
                                          ~@(repeat (- max-args (count arguments))
                                                    {:tag :any, :symbol '_})
                                          ~@(:cols row)]))))
                 (r.matrix/first-column matrix)
                 (r.matrix/drop-column matrix))]
    [(compile targets* matrix*)]))


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
             loc-sym (gensym* "loc__")
             ;; Bound to zipper node and tested against :pattern.
             node-sym (gensym* "node__")
             matrix* (as-> [row] %matrix
                       (if (some? context)
                         (r.matrix/prepend-column %matrix
                                                  [{:tag :let
                                                    :bindings
                                                    [{:binding context
                                                      :expr `(fn [x#]
                                                               (zip/root (zip/replace ~loc-sym x#)))}]}])
                         %matrix)
                       (r.matrix/prepend-column %matrix
                                                [{:tag :let
                                                  :bindings [{:binding pattern
                                                              :expr node-sym}]}]))
             targets* (as-> [node-sym] %targets
                        (if (some? context)
                          (conj %targets loc-sym))
                        (into %targets targets*))]
         (if (some? context)
           (r.ir/op-search loc-sym (r.ir/op-eval `(r.util/zip-next-seq (r.util/coll-zip ~target)))
             (r.ir/op-bind node-sym (r.ir/op-eval `(zip/node ~loc-sym))
               (compile targets* matrix*)))
           (r.ir/op-search node-sym (r.ir/op-eval `(tree-seq coll? seq ~target))
             (compile targets* matrix*))))))
   (r.matrix/first-column matrix)
   (r.matrix/drop-column matrix)))


(defmethod compile-specialized-matrix :drp
  [_ [target & targets*] matrix]
  [(compile (vec targets*) (r.matrix/drop-column matrix))])

#_
(defmethod compile-specialized-matrix :dsj
  [_ targets matrix]
  (let [matrix* (into []
                      (mapcat
                       (fn [node row]
                         (case (r.syntax/tag node)
                           :any
                           [(assoc row :cols `[~node ~@(:cols row)])]

                           :dsj
                           (map
                            (fn [argument]
                              (assoc row :cols `[~argument ~@(:cols row)]))
                            (:arguments node))))
                       (r.matrix/first-column matrix)
                       (r.matrix/drop-column matrix)))]
    [(compile targets matrix*)]))

(defmethod compile-specialized-matrix :dsj
  [_ targets matrix]
  (let [targets* (vec (rest targets))]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets [row])

         :dsj
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


(defmethod compile-specialized-matrix :grd
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets* [row])

         :grd
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
             (let [node* {:tag :let*
                          :binding val-node
                          :expr (list 'js* "(~{}[~{}])" target (compile-ground key-node))}
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


(defmethod compile-specialized-matrix :let*
  [_ [target & targets* :as targets] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets [row])

         :let*
         (let [xsym (gensym* "x__")
               targets* `[~xsym ~@targets*]
               matrix* (r.matrix/prepend-column [row] [(:binding node)])]
           (r.ir/op-bind xsym (r.ir/op-eval (:expr node))
             (compile targets* matrix*)))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :let
  [_ targets matrix]
  [(compile targets
            (r.matrix/prepend-column
             (r.matrix/drop-column matrix)
             (mapv
              (fn [node]
                (case (r.syntax/tag node)
                  :let
                  {:tag :cnj
                   :arguments (map
                               (fn [binding]
                                 {:tag :let*
                                  :binding (:binding binding)
                                  :expr (:expr binding)})
                               (:bindings node))}
                  node))
              (r.matrix/first-column matrix))))])


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
  "Return a sequence of all :jso keys in matrix."
  {:private true}
  [matrix]
  (mapcat
   (fn [node]
     (when (= (r.syntax/tag node) :map)
       (keys (:map node))))
   (r.matrix/first-column matrix)))


(defmethod compile-specialized-matrix :map
  [_ [target & targets*] matrix]
  (let [all-keys (map-matrix-all-keys matrix)
        key-sort (sort-by (comp - (frequencies all-keys))
                          (distinct all-keys))
        num-keys (count key-sort)
        matrix* (mapv
                 (fn [node row]
                   (case (r.syntax/tag node)
                     :any
                     (assoc row :cols `[~@(repeat num-keys node) ~@(:cols row)])

                     :map
                     (let [the-map (:map node)]
                       (if (r.syntax/search? node)
                         (let [set-node {:tag :set
                                         :elements (map
                                                    (fn [[k-node v-node]]
                                                      {:tag :cat
                                                       :elements [k-node v-node]})
                                                    the-map)}
                               let-node {:tag :let*
                                         :binding set-node
                                         :expr `(set ~target)}]
                           (assoc row :cols `[~let-node
                                              ~@(repeat (dec num-keys)
                                                        '{:tag :any
                                                          :symbol _})
                                              ~@(:cols row)]))
                         (let [new-cols (sort-by
                                         (fn [node]
                                           (if (= (r.syntax/tag node) :mkv)
                                             0
                                             1))
                                         (map
                                          (fn [key]
                                            (if-some [entry (clojure/find the-map key)]
                                              {:tag :mkv
                                               :entry entry}
                                              {:tag :any
                                               :symbol '_}))
                                          key-sort))]
                           (assoc row :cols `[~@new-cols ~@(:cols row)]))))))
                 (r.matrix/first-column matrix)
                 (r.matrix/drop-column matrix))]
    [(r.ir/op-check-map (r.ir/op-eval target)
       (compile `[~@(repeat num-keys target) ~@targets*] matrix*))]))

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
               val-target (gensym* "val__")]
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
           (compile targets* [row]))))
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

(defmethod compile-specialized-matrix :not
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets* [row])

         :not
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
               lsym (gensym* "l__")
               ;; Left min length
               llen (r.syntax/min-length left)
               ;; Right tree symbol
               rsym (gensym* "r__")
               ;; Right min length
               rlen (r.syntax/min-length right)
               ;; Target length symbol
               nsym (gensym "n__")
               ;; Target length symbol minus either the left or right min length
               msym (gensym* "m__")
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
               (r.ir/op-bind lsym (r.ir/op-take (r.ir/op-eval target) llen *collection-context*)
                 (r.ir/op-check-bounds (r.ir/op-eval lsym) llen *collection-context*
                   (r.ir/op-bind rsym (r.ir/op-take (r.ir/op-eval target) llen *collection-context*)
                     (r.ir/op-check-bounds (r.ir/op-eval rsym) llen *collection-context*
                       (compile `[~lsym ~rsym ~@targets*]
                                [(assoc row :cols `[~left ~right ~@(:cols row)])]))))))

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
             (let [parts-sym (gensym* "parts__")]
               (if-some [[cat-node right] (r.syntax/window node)]
                 (let [elements (:elements cat-node)
                       cat-length (count elements)]
                   (case cat-length
                     0
                     (compile-pass `[~target ~@targets*]
                                   (r.matrix/prepend-column [row] [right]))

                     1
                     (if right
                       (r.ir/op-search parts-sym `(r.util/partitions 2 ~target)
                         (r.ir/op-bind lsym (r.ir/op-nth (r.ir/op-eval parts-sym) 0)
                           (r.ir/op-bind rsym (r.ir/op-nth (r.ir/op-eval parts-sym) 1)
                             (let [partition-sym (gensym "partition__")]
                               (r.ir/op-search partition-sym (r.ir/op-eval lsym)
                                 (compile `[~partition-sym ~rsym ~@targets*]
                                          [(r.matrix/prepend-cells row [(first elements) right])]))))))
                       (r.ir/op-search parts-sym (r.ir/op-eval target)
                         (compile `[~parts-sym ~@targets*]
                                  [(r.matrix/prepend-cells row [(first elements)])])))

                     ;; else
                     (if right
                       (r.ir/op-search parts-sym `(r.util/partitions 2 ~cat-length ~target)
                         (r.ir/op-bind lsym (r.ir/op-nth (r.ir/op-eval parts-sym) 0)
                           (r.ir/op-bind rsym (r.ir/op-nth (r.ir/op-eval parts-sym) 1)
                             (let [partition-sym (gensym "partition__")]
                               (r.ir/op-search partition-sym (r.ir/op-eval `(partition ~cat-length 1 ~lsym))
                                 (compile `[~partition-sym ~rsym ~@targets*]
                                          [(r.matrix/prepend-cells row [cat-node right])]))))))
                       (r.ir/op-search parts-sym (r.ir/op-eval `(partition ~cat-length 1 ~target))
                         (compile `[~parts-sym ~@targets*]
                                  [(r.matrix/prepend-cells row [cat-node])])))))
                 (r.ir/op-search parts-sym (r.ir/op-eval `(r.util/partitions 2 ~target))
                   (r.ir/op-bind lsym (r.ir/op-nth (r.ir/op-eval parts-sym) 0)
                     (r.ir/op-bind rsym (r.ir/op-nth (r.ir/op-eval parts-sym) 1)
                       (compile `[~lsym ~rsym ~@targets*]
                                [(assoc row :cols `[~left ~right ~@(:cols row)])]))))))))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :prd
  [_ [target & targets* :as targets] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets* [row])

         :prd
         (let [arguments (:arguments node)]
           (r.ir/op-check-boolean (r.ir/op-eval `(~(:form node) ~target))
             (if (seq arguments)
               (compile targets
                        [(assoc row :cols `[~{:tag :cnj
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


(defmethod compile-specialized-matrix :rxt
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets* [row])

         :rxt
         (r.ir/op-check-boolean (r.ir/op-eval `(string? ~target))
           (r.ir/op-check-boolean (r.ir/op-eval `(re-matches ~(:regex node) ~target))
             (compile targets* [row])))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :rxc
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         (compile-pass targets* [row])

         :rxc
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
                     (let [rp*-node {:tag :rp*
                                     :cat (:cat node)}
                           app-node {:tag :app
                                     :fn-expr `clojure.core/count
                                     :arguments [(:lvr node)]}]
                       ;; If the logic variable is bound we place the `:app` node
                       ;; ahead of the `:rp*` node to verify the `count` is equal
                       ;; before attemping to pattern match on the input.
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

                     ;; Here we turn `:rpm` into two nodes: an `:rp*`
                     ;; and an `:app`. The `:rp*` will proceed against
                     ;; `target` as usual and then afterwards will
                     ;; `count` the target `conj`ing the result on to
                     ;; the memory variable given by the `:rpm`.
                     :rpm
                     (let [rp*-node {:tag :rp*
                                     :cat (:cat node)}
                           app-node {:tag :app
                                     :fn-expr `clojure.core/count
                                     :arguments [(:mvr node)]}]
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
             (r.ir/op-bind sym (r.ir/op-eval `(into ~sym ~target))
               (compile targets* [(r.matrix/add-var row mvr)]))
             (r.ir/op-bind sym (r.ir/op-eval `(vec ~target))
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
                 matrix*  [(assoc row :cols `[~{:tag :cat
                                                :elements (vec elements)}
                                              ~@(:cols row)])]]
             (r.ir/op-check-set (r.ir/op-eval target)
               (r.ir/op-check-bounds (r.ir/op-eval target) n :set
                 (if (negating?)
                   (r.ir/op-find perm-sym (r.ir/op-eval `(r.util/k-combinations ~target ~n))
                     (compile targets** matrix*))
                   (r.ir/op-search perm-sym (r.ir/op-eval `(r.util/k-combinations ~target ~n))
                     (compile targets** matrix*))))))

           (some (comp #{:map :set} r.syntax/tag)
                 (r.syntax/proper-subnodes node))
           (let [elements (:elements node)
                 n (count elements)
                 ;; Symbol for each permutation of target.
                 perm-sym (gensym* "perm__")]
             (r.ir/op-check-set (r.ir/op-eval target)
               (r.ir/op-check-bounds (r.ir/op-eval target) n :set
                 (r.ir/op-find perm-sym (r.ir/op-eval `(r.util/k-combinations ~target ~n))
                   (compile `[~perm-sym ~@targets*]
                            [(assoc row :cols `[~{:tag :cat
                                                  :elements (vec elements)}
                                                ~@(:cols row)])])))))

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
         (r.ir/op-check-lit (r.ir/op-eval target) (r.ir/op-eval (r.syntax/lit-form node))
           (compile targets* [row]))
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
    (= (count matrix) 0)
    (r.ir/op-fail)

    (every? r.syntax/any-node? (mapcat identity (r.matrix/columns (take 1 matrix))))
    (r.matrix/action (first matrix))

    :else
    (let [i (some (fn [[i column]]
                    (when (not-every? r.syntax/any-node? column)
                      i))
                  (map-indexed vector (r.matrix/columns matrix)))]
      (if (= 0 i)
        (let [[targets* matrix*] (prioritize-matrix targets matrix)
              tags (into []
                         (comp (map r.syntax/tag)
                               (remove #{:any})
                               (distinct))
                         (r.matrix/first-column matrix*))
              arms (into [] (mapcat
                             (fn [tag]
                               (let [s-matrix (specialize-matrix tag matrix*)]
                                 (compile-specialized-matrix tag targets* s-matrix))))
                         tags)
              arms (if (and (some (fn [op]
                                    (= (:type op) :test))
                                  arms)
                            (not (some (fn [op]
                                         (= (:op op) :pass))
                                       arms)))
                     (conj arms (r.ir/op-fail))
                     arms)]
          (r.ir/op-branch arms))
        (let [targets* (r.matrix/swap targets 0 i)
              matrix* (r.matrix/swap-column matrix 0 i)]
          (compile targets* matrix*))))))


;; ---------------------------------------------------------------------
;; match pattern checking

(s/def :meander.match.epsilon.check-env/lvrs
  (s/coll-of :meander.syntax.epsilon.node/lvr :kind set?))

(s/def :meander.match.epsilon.check-env/mvrs
  (s/coll-of :meander.syntax.epsilon.node/mvr :kind set?))

(s/def :meander.match.epsilon.check-env/ref-map
  (s/keys :req-un [:meander.syntax.epsilon/node
                   :meander.syntax.epsilon.node/with]))

(s/def :meander.match.epsilon.check-env/refs
  (s/map-of :meander.syntax.epsilon.node/ref
            :meander.match.epsilon.check-env/ref-map))

(s/def :meander.match.epsilon/check-env
  (s/keys :req-un [:meander.match.epsilon.check-env/lvrs
                   :meander.match.epsilon.check-env/mvrs
                   :meander.match.epsilon.check-env/refs]))


(def empty-check-env
  {:lvrs #{}
   :mvrs #{}
   :refs {}
   :path []})


(defn unparse-check-env
  "Unparse all AST nodes in check-env."
  {:private true}
  [check-env]
  {:lvrs (into #{} (map r.syntax/unparse) (:lvrs check-env))
   :mvrs (into #{} (map r.syntax/unparse) (:mvrs check-env))
   :refs (into {} (map
                   (fn [[k v]]
                     [(r.syntax/unparse k)
                      {:node (r.syntax/unparse (:node v))
                       :with (r.syntax/unparse (:with v))}]))
               (:refs check-env))
   :path (into []
               (comp (keep
                      (fn [node]
                        (case (r.syntax/tag node)
                          (:prt :cat)
                          nil

                          node)))
                     (map r.syntax/unparse))
               (:path check-env))})

(defn add-refs
  {:private true}
  [check-env node]
  (if (r.syntax/with-node? node)
    (update check-env :refs merge (into {} (map
                                            (fn [binding]
                                              [(:ref binding)
                                               {:node (:pattern binding)
                                                :with node}]))
                                        (:bindings node)))
    check-env))


(defmulti check-node
  "Validates node returning
  [:error [{:message string, :ex-data map?}]
    whenever validation fails.
  [:okay child-nodes new-env]
    whenever validation succeeds. child-nodes are the child nodes of
    node. new-env is env extended with variables that would be bound
    during the process of matching node but not it's children."
  {:arglists '([node env search?])}
  (fn [node env search?]
    (r.syntax/tag node))
  :default ::default)


(defmethod check-node ::default
  [node env _]
  [:okay (r.syntax/children node) env])


(defn check-error?
  {:private true}
  [[tag _]]
  (= tag :error))


(defn check-okay?
  {:private true}
  [[tag _]]
  (= tag :okay))


(defn check*
  "Checks if node is valid with respect to env. Returns

  [:error [{:message string?, :ex-data map?} & syntax-trace]
    whenever an error is detected. syntax-trace is a sequence forms
    which represent the path to the invalid pattern from the leaf
    to the root.


  [:okay exit-env]
    whenever the node is valid. exit-env is a set of all
    logic and memory variables which would be bound by a successful
    pattern match; equivalent to (meander.syntax.epsilon/variables node)."
  [node env search?]
  (let [[tag :as result] (check-node node env search?)]
    (case tag
      :error
      (let [[_ trace] result]
        [:error (conj trace node)])

      :okay
      (let [[_ children env] result]
        (reduce
         (fn [[_ env] child]
           (let [[tag :as result] (check* child env search?)]
             (case tag
               :error
               (let [[_ trace] result]
                 (reduced [:error (conj trace node)]))

               :okay
               result)))
         [:okay (update env :path conj node)]
         children)))))


(defn check
  "Checks if node is valid. Returns an instance of
  clojure.lang.Exception if an error can be found and nil otherwise."
  [node search?]
  (let [[tag :as result] (check* node empty-check-env search?)]
    (case tag
      :error
      (let [[_ [{:keys [message ex-data]} & trace]] result
            syntax-trace (into [] (comp (remove (comp #{:cat :prt} r.syntax/tag))
                                        (map r.syntax/unparse))
                               trace)]
        (ex-info message (assoc ex-data :syntax-trace syntax-trace)))

      :okay
      nil)))


(defmethod check-node :dsj
  [node env search?]
  (let [arguments (:arguments node)
        bound-lvrs (:lvrs env)
        ref-map (into {}
                      (map
                       (fn [[k v]]
                         [k (:node v)]))
                      (:refs env))
        argument-lvrs (map
                       (fn [node]
                         (let [node* (r.syntax/substitute-refs node ref-map)]
                           (set/difference (r.syntax/logic-variables node*)
                                           bound-lvrs)))
                       arguments)
        all-lvrs (reduce set/union #{} argument-lvrs)
        problems (sequence
                  (comp (map vector)
                        (keep
                         (fn [[term argument-lvrs]]
                           (let [absent-lvrs (set/difference all-lvrs argument-lvrs)]
                             (when (seq absent-lvrs)
                               [term absent-lvrs])))))
                  arguments
                  argument-lvrs)]
    (if (seq problems)
      [:error [{:message "Every pattern of an or pattern must have references to the same unbound logic variables."
                :ex-data {:env (unparse-check-env env)
                          :problems (mapv
                                     (fn [[pat absent-vars]]
                                       {:pattern (r.syntax/unparse pat)
                                        :absent (into #{} (map r.syntax/unparse) absent-vars)})
                                     problems)}}]]
      [:okay (r.syntax/children node) env])))


(defmethod check-node :rp*
  [node env _]
  (let [unbound-lvrs (into #{} (remove (:lvrs env)) (r.syntax/logic-variables node))]
    (cond
      (seq unbound-lvrs)
      [:error [{:message "Zero or more patterns may not have references to unbound logic variables."
                :ex-data {:unbound (into #{} (map r.syntax/unparse) unbound-lvrs)}}]]

      (empty? (:elements (:cat node)))
      (let [dots '...]
        [:error
         [{:message (str "Zero or more (" dots ") is a postfix operator. It must have some value in front of it. (i.e. [1 " dots " ?x])")}]])

      :else
      [:okay (r.syntax/children node) env])))


(defmethod check-node :rp+
  [node env _]
  (let [elements (:elements (:cat node))
        n (:n node)]
    (cond
      (nil? n)
      [:error [{:message "Ambiguous ellipsis. Perhaps you meant the n or more operator (..N) or the zero or more operator (...)?"}]]

      (empty? elements)
      [:error [{:message (str "N or more (..N) is a postfix operator. It must have some value in front of it. (i.e. [1 .." n " ?x])")}]]

      :else
      [:okay (r.syntax/children node) env])))


(defmethod check-node :lvr
  [node env _]
  [:okay [] (update env :lvrs conj node)])


(defmethod check-node :map
  [node env search?]
  (if search?
    [:okay (r.syntax/children node) env]
    (let [the-map (:map node)
          invalid-keys (remove r.syntax/ground? (keys the-map))]
      (if (seq invalid-keys)
        [:error [{:message "Map patterns may not contain variables in their keys."
                  :ex-data {:keys (mapv r.syntax/unparse invalid-keys)}}]]
        [:okay (vals the-map) env]))))


(defmethod check-node :mvr
  [node env _]
  [:okay [] (update env :mvrs conj node)])


(defmethod check-node :prt
  [node env search?]
  (if search?
    [:okay (r.syntax/children node) env]
    (let [{left :left, right :right} node]
      (if (and (r.syntax/variable-length? left)
               (r.syntax/variable-length? right))
        [:error [{:message "A variable length subsequence pattern may not be followed by another variable length subsequence pattern."
                  :ex-data {}}]]
        [:okay (r.syntax/children node) env]))))


(defmethod check-node :ref
  [node env search?]
  (if-some [entry (get (:refs env) node)]
    (let [other-node (:node entry)]
      (if (some
           (fn [prev-node]
             (identical? prev-node other-node))
           (:path env))
        [:okay [] env]
        [:okay [other-node] env]))
    [:error [{:message (str "Unbound reference " (r.syntax/unparse node))
              :ex-data {:env (unparse-check-env env)
                        :reference (r.syntax/unparse node)}}]]))


(defmethod check-node :set
  [node env search?]
  (if search?
    [:okay (r.syntax/children node) env]
    (if (r.syntax/ground? node)
      [:okay [] env]
      [:error [{:message "Set patterns may not contain variables."
                :ex-data {}}]])))


(defmethod check-node :wth
  [node env search?]
  (if-some [body (:body node)]
    (let [bindings (:bindings node)
          ref-freqs (into {} (filter
                              (fn [[_ n]]
                                (< 1 n)))
                          (frequencies (map :ref bindings)))]
      (if (seq ref-freqs)
        [:error [{:message "with patterns must have distict references"
                  :ex-data {:env (unparse-check-env env)
                            :offending-bindings (into #{} (map r.syntax/unparse) (keys ref-freqs))}}]]
        (let [check-env (add-refs env node)]
          (check* body check-env search?))))
    ;; Nothing to check.
    [:okay [] env]))


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


;; TODO: Include useless clause analysis.
(defn analyze-match-args
  "Analyzes arguments as would be supplied to the match macro e.g.

    (expr clause action ,,,)

  Returns a map containing the following keys:

  :errors A sequence of errors. These are instances of
    clojure.lang.Exception and are derived by applying check to the
    pattern of each clause.
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
   (let [data (s/conform :meander.match.epsilon.match/args match-args)]
     (if (identical? data ::s/invalid)
       (throw (ex-info "Invalid match-args"
                       (s/explain-data :meander.match.epsilon.match/args match-args)))
       (let [clauses (map
                      (fn [{:keys [pat rhs]}]
                        {:pat (r.match.syntax/parse pat env)
                         :rhs rhs})
                      (:clauses data))
             errors (into []
                          (keep
                           (fn [{pat :pat}]
                             (check pat false)))
                          clauses)
             matrix (mapv
                     (fn [clause]
                       (r.matrix/make-row
                        [(r.match.syntax/expand-ast (:pat clause))]
                        (r.ir/op-return (:rhs clause))))
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
         {:errors errors
          :expr (:expr data)
          :exhaustive? (some? final-clause)
          :final-clause final-clause
          :matrix matrix})))))

(s/fdef analyze-match-args
  :args (s/cat :match-args :meander.match.epsilon.match/args)
  :ret :meander.match.epsilon.match/data)


(defmacro match
  {:arglists '([x & clauses])
   :style/indent [1]}
  [& match-args]
  (let [match-data (analyze-match-args match-args &env)
        expr (:expr match-data)
        matrix (:matrix match-data)
        final-clause (:final-clause match-data)
        errors (:errors match-data)]
    (binding [*env* &env]
      (if-some [error (first errors)]
        (throw error)
        (let [target (gensym "target__")
              fail (gensym "fail__")]
          (if (r.matrix/empty? matrix)
            (if (some? final-clause)
              (r.ir/compile (compile [expr] [final-clause]) nil :match &env)
              `(throw (ex-info "non exhaustive pattern match" '~(meta &form))))
            `(let [~target ~expr
                   ~fail (fn []
                           ~(if (some? final-clause)
                              (r.ir/compile (compile [target] [final-clause]) nil :match &env)
                              `(throw (ex-info "non exhaustive pattern match" '~(meta &form)))))]
               ~(r.ir/compile (compile [target] matrix) `(~fail) :match &env))))))))


(s/fdef match
  :args (s/cat :expr any?
               :clauses :meander.match.epsilon.match/clauses)
  :ret any?)


(defn analyze-search-args
  "Analyzes arguments as would be supplied to the search macro e.g.

    (expr clause action ,,,)

  Returns a map containing the following keys:

  :errors A sequence of errors. These are instances of
    clojure.lang.Exception and are derived by applying check to the
    pattern of each clause.
  :expr The expression which is the target of pattern matching, the
    first argument to the match macro.
  :matrix The pattern matrix derived from the (clause action ,,,)
    forms. Each action expression is wrapped in a list."
  ([match-args]
   (analyze-search-args match-args {}))
  ([match-args env]
   (let [data (s/conform :meander.match.epsilon.match/args match-args)]
     (if (identical? data ::s/invalid)
       (throw (ex-info "Invalid search args"
                       (s/explain-data :meander.match.epsilon.match/args match-args)))
       (let [clauses (mapv
                      (fn [{:keys [pat rhs]}]
                        {:pat (r.match.syntax/parse pat env)
                         :rhs rhs})
                      (:clauses data))
             errors (into [] (keep
                              (fn [{pat :pat}]
                                (check pat true)))
                          clauses)
             matrix (mapv
                     (fn [clause]
                       (r.matrix/make-row
                        [(r.match.syntax/expand-ast (:pat clause))]
                        (r.ir/op-return (:rhs clause))))
                     clauses)]
         {:errors errors
          :expr (:expr data)
          :matrix matrix})))))


(defmacro search
  "Like match but allows for patterns which may match x in more than
  one way. Returns a lazy sequence of clause action values.

  Example:

  (search [1 2 3]
    [!xs ... !ys ...]
    {'!xs !xs, '!ys !ys})
  ;; =>
  ({!xs [], !ys [1 2 3]}
   {!xs [1], !ys [2 3]}
   {!xs [1 2], !ys [3]}
   {!xs [1 2 3], !ys []})

  Note: If only the first value is needed, use find instead."
  {:arglists '([x & clauses])
   :style/indent [1]}
  [& match-args]
  (let [match-data (analyze-search-args match-args &env)
        expr (:expr match-data)
        matrix (:matrix match-data)
        errors (:errors match-data)]
    (binding [*env* &env]
      (if-some [error (first errors)]
        (throw error)
        (let [target (gensym "target__")]
          (if (r.matrix/empty? matrix)
            nil
            (r.ir/compile (r.ir/op-bind target (r.ir/op-eval expr)
                            (compile [target] matrix))
                          nil
                          :search
                          &env)))))))


(s/fdef search
  :args (s/cat :expr any?
               :clauses :meander.match.epsilon.match/clauses)
  :ret (s/coll-of any? :kind sequential?))


(defn analyze-find-args
  "Analyzes arguments as would be supplied to the find macro e.g.

    (expr clause action ,,,)

  Returns a map containing the following keys:

  :errors A sequence of errors. These are instances of
    clojure.lang.Exception and are derived by applying check to the
    pattern of each clause.
  :expr The expression which is the target of pattern matching, the
    first argument to the match macro.
  :matrix The pattern matrix derived from the (clause action ,,,)
    forms. Each action expression is wrapped in a list."
  ([match-args]
   (analyze-find-args match-args {}))
  ([match-args env]
   (let [data (s/conform :meander.match.epsilon.match/args match-args)]
     (if (identical? data ::s/invalid)
       (throw (ex-info "Invalid match args"
                       (s/explain-data :meander.match.epsilon.match/args match-args)))
       (let [clauses (mapv
                      (fn [{:keys [pat rhs]}]
                        {:pat (r.match.syntax/parse pat env)
                         :rhs rhs})
                      (:clauses data))
             errors (into [] (keep
                              (fn [{pat :pat}]
                                (check pat true)))
                          clauses)
             matrix (mapv
                     (fn [clause]
                       (r.matrix/make-row
                        [(r.match.syntax/expand-ast (:pat clause))]
                        (r.ir/op-return (:rhs clause))))
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
                      (vec (take-while (partial not= final-clause) matrix))
                      matrix)]
         {:errors errors
          :expr (:expr data)
          :final-clause final-clause
          :matrix matrix})))))


(defmacro find
  "Like search but returns only the first successful match."
  {:arglists '([x & clauses])
   :style/indent [1]}
  [& match-args]
  (let [match-data (analyze-find-args match-args &env)
        expr (:expr match-data)
        matrix (:matrix match-data)
        errors (:errors match-data)
        final-clause (:final-clause match-data)
        target (gensym "target__")
        fail (gensym "fail__")]
    (binding [*env* &env]
      (if-some [error (first errors)]
        (throw error)
        (if (r.matrix/empty? matrix)
          (if (some? final-clause)
            (r.ir/compile (r.ir/op-bind target (r.ir/op-eval expr)
                            (compile [target] [final-clause]))
                          nil
                          :find
                          &env)
            nil)
          (r.ir/compile
           (r.ir/op-bind target (r.ir/op-eval expr)
             (r.ir/op-eval
               (if (some? final-clause)
                 `(let [~fail (fn []
                                ~(r.ir/compile (compile [target] [final-clause]) nil :find &env))]
                    ~(r.ir/compile (compile [target] matrix) `(~fail) :find &env))
                 (r.ir/compile (compile [target] matrix) nil :find &env))))
           nil
           :find))))))


(s/fdef find
  :args (s/cat :expr any?
               :clauses :meander.match.epsilon.match/clauses)
  :ret any?)

;; ---------------------------------------------------------------------
;; Match operators

(r.match.syntax/defsyntax scan [& args]
  (let [argc (count args)
        [as pattern] (drop (- argc 2) args)
        inner (if (= :as as)
                `(~@'(_ ...) ~@args ~@'(. _ ...) :as ~pattern)
                `(~@'(_ ...) ~@args ~@'(. _ ...)))]
    `(~'or [~@inner]
      ;; Needed to prevent producing the same search results twice
      ;; when the target is a vector.
      (~'and (~'not (~'pred vector?))
       (~'pred seqable? (~'app seq ~inner))))))

(r.match.syntax/defsyntax separated [& args]
  (let [inner `(~@'(_ ...) ~@(mapcat cons args (repeat '(. _ ...))))]
    `(~'or [~@inner]
      ;; Needed to prevent producing the same search results twice
      ;; when the target is a vector.
      (~'and (~'not (~'pred vector?))
       (~'pred seqable? (~'app seq (~@inner)))))))
