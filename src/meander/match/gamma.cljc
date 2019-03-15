(ns meander.match.gamma
  (:refer-clojure :exclude [compile find])
  #?(:cljs (:require-macros [meander.match.gamma]))
  (:require [#?(:clj clojure.core :cljs cljs.core) :as clojure]
            [#?(:clj clojure.pprint :cljs cljs.pprint) :as pprint]
            [clojure.set :as set]
            [clojure.spec.alpha :as s]
            [clojure.walk :as walk]
            [clojure.zip :as zip]
            [meander.matrix.gamma :as r.matrix]
            [meander.syntax.gamma :as r.syntax]
            [meander.util.gamma :as r.util])
  #?(:clj
     (:import (cljs.tagged_literals JSValue))
     :cljs
     (:import (goog.object))))


(def
  ^{:dynamic true
    :doc ""}
  *negating* false)


(defn negating?
  "true if currently compiling a matrix dervied from a not pattern,
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


(defn take-form
  "Form for taking n elements from target with respect to the current
  value of *collection-context*."
  [n target]
  (cond
    (vector-context?)
    `(subvec ~target 0 (min (count ~target) ~n))

    (js-array-context?)
    `(.slice ~target 0 (min (.-length ~target) ~n))

    :else
    `(take ~n ~target)))


(defn drop-form
  "Form for dropping n elements from target with respect to the
  current value of *collection-context*."
  [n target]
  (cond
    (vector-context?)
    `(subvec ~target (min (count ~target) ~n))

    (js-array-context?)
    `(.slice ~target (min (.-length ~target) ~n))

    :else
    `(drop ~n ~target)))


(defn js-array-equals-form
  "Form used to test if two arrays a and b are equal in
  ClojureScript."
  [a b]
  `(goog.array/equals ~a ~b
                      (fn f# [a# b#]
                        (if (cljs.core/array? a#)
                          (goog.array/equals a# b# f#)
                          (= a# b#)))))


(defn gensym*
  "Like gensym but adds additional meta data which can be used by the
  match compiler."
  {:private true}
  ([]
   (with-meta (gensym) {::gensym? true}))
  ([prefix-string]
   (with-meta (gensym prefix-string) {::gensym? true})))


(defn gensym?
  "true if x is an internally generate gensym, false otherwise."
  {:private true}
  ([x]
   (and (symbol? x) (::gensym? (meta x)))))


(declare compile)


(s/def :meander.match.gamma.tree/action-node
  (s/tuple #{:action} any?))


(s/def :meander.match.gamma.tree/bind-node
  (s/tuple #{:bind} vector? :meander.match.gamma/tree))


(s/def :meander.match.gamma.tree/branch-node
  (s/tuple #{:branch}
           (s/coll-of :meander.match.gamma/tree
                      :kind vector?
                      :into [])))


(s/def :meander.match.gamma.tree/fail-node
  (s/tuple #{:fail}))


(s/def :meander.match.gamma.tree/identitifer
  simple-symbol?)


(s/def :meander.match.gamma.tree/loop-node
  (s/tuple #{:loop}
           :meander.match.gamma.tree/identitifer
           (s/coll-of simple-symbol? :kind vector? :into [])
           :meander.match.gamma/tree))


(s/def :meander.match.gamma.tree/save-node
  (s/tuple #{:save}
           :meander.match.gamma.tree/identitifer
           :meander.match.gamma/tree
           :meander.match.gamma/tree))


(s/def :meander.match.gamma.tree/load-node
  (s/tuple #{:load}
           :meander.match.gamma.tree/identitifer))


(s/def :meander.match.gamma.tree/pass-node
  (s/tuple #{:pass} :meander.match.gamma/tree))


(s/def :meander.match.gamma.tree/recur-node
  (s/tuple #{:recur}
           :meander.match.gamma.tree/identitifer
           (s/coll-of simple-symbol? :kind vector? :into [])))


(s/def :meander.match.gamma.tree/test-node
  (s/tuple #{:test} any? :meander.match.gamma/tree))


(s/def :meander.match.gamma.tree/search-node
  (s/tuple #{:search}
           (s/tuple simple-symbol? any?)
           :meander.match.gamma/tree))


(s/def :meander.match.gamma.tree/find-node
  (s/tuple #{:find}
           (s/tuple simple-symbol? any?)
           :meander.match.gamma/tree))


(s/def :meander.match.gamma/tree
  (s/or :action :meander.match.gamma.tree/action-node
        :bind :meander.match.gamma.tree/bind-node
        :branch :meander.match.gamma.tree/branch-node
        :fail :meander.match.gamma.tree/fail-node
        :find :meander.match.gamma.tree/find-node
        :pass :meander.match.gamma.tree/pass-node
        :load :meander.match.gamma.tree/load-node
        :loop :meander.match.gamma.tree/loop-node
        :recur :meander.match.gamma.tree/recur-node
        :save :meander.match.gamma.tree/save-node
        :search :meander.match.gamma.tree/search-node
        :test :meander.match.gamma.tree/test-node))


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
       (not-any? (comp #{:map :set} r.syntax/tag)
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


(defn compile-with-memory-variables-initialized
  {:private true}
  [targets matrix]
  (let [last-row (last matrix)]
    (reduce
     (fn [[matrix* all-mvrs] row]
       (let [mvrs (r.syntax/memory-variables (first (:cols row)))
             row* (assoc row :env mvrs)
             all-mvrs* (set/union all-mvrs mvrs)
             matrix** (conj matrix* row*)]
         (if (= row last-row)
           (reduce
            (fn [tree mvr-node]
              [:bind [(:symbol mvr-node) []]
               tree])
            (compile targets matrix**)
            all-mvrs*)
           [matrix** all-mvrs*])))
     [[] #{}]
     matrix)))


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
               :matrix :meander.matrix.gamma/matrix)
  :ret (s/coll-of :meander.match.gamma/tree))

(defmethod compile-specialized-matrix :app
  [_ [target & targets*] matrix]
  (let [app-target (gensym* "app_target__")]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         [:pass (compile targets* [row])]

         :app
         [:bind [app-target `(~(:fn-expr node) ~target)]
          (compile `[~app-target ~@targets*]
                   [(assoc row :cols `[~{:tag :cnj
                                         :arguments (:arguments node)}
                                       ~@(:cols row)])])]))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


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
         [:pass (compile targets* [row])]

         :cat
         (if (literal? node)
           [:test (if (js-array-context?)
                    (js-array-equals-form target (compile-ground
                                                  {:tag :jsa
                                                   :prt {:tag :prt
                                                         :left node
                                                         :right {:tag :cat
                                                                 :elements []}}}))
                    `(= ~target ~(vec (compile-ground node))))
            (compile targets* [row])]
           (let [elements (:elements node)
                 nth-syms (take (count elements) nth-syms)
                 targets* `[~@nth-syms ~@targets*]]
             (reduce
              (fn [tree [i nth-sym]]
                [:bind [nth-sym `(nth ~target ~i)]
                 tree])
              (compile targets* [(assoc row :cols `[~@elements ~@(:cols row)])])
              (reverse (map-indexed vector nth-syms)))))))
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
       [:pass (compile targets* [row])]

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
           [:search [loc-sym `(r.util/zip-next-seq (r.util/coll-zip ~target))]
            [:bind [node-sym `(zip/node ~loc-sym)]
             (compile targets* matrix*)]]
           [:search [node-sym `(tree-seq coll? seq ~target)]
            (compile targets* matrix*)]))))
   (r.matrix/first-column matrix)
   (r.matrix/drop-column matrix)))


(defmethod compile-specialized-matrix :drp
  [_ [target & targets*] matrix]
  [(compile (vec targets*) (r.matrix/drop-column matrix))])


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


(defmethod compile-specialized-matrix :grd
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         [:pass
          (compile targets* [row])]

         :grd
         [:test (:expr node)
          (compile targets* [row])]))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :jsa
  [_ [target & targets* :as targets] matrix]
  (mapv
   (fn [node row]
     (case (r.syntax/tag node)
       :any
       [:pass (compile targets [row])]

       :jsa
       (if (literal? node)
         [:test (js-array-equals-form target (compile-ground node))
          (compile targets* [row])]
         [:test `(cljs.core/array? ~target)
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
              (compile [target] [row*])))])))
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


(defn rank
  "Returns a sorted sequence of values in xs by frequency of
  occurence."
  {:private true}
  [xs]
  (map first (sort-by (comp - val) (frequencies xs))))


(defmethod compile-specialized-matrix :jso
  [_ [target & targets*] matrix]
  (let [ranked-keys (rank (jso-matrix-all-keys matrix))]
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
    [[:test `(some? ~target) ;; This may be a bit liberal.
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
                matrix))]]))


(defmethod compile-specialized-matrix :okv
  [_ [target & targets* :as targets] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         [:pass (compile targets* [row])]

         :okv
         (let [entry (:entry node)
               [key-node val-node] entry]
           (if (r.syntax/ground? key-node)
             (let [row* (assoc row :cols `[~{:tag :let*
                                             :binding val-node
                                             :expr (list 'js* "(~{}[~{}])" target (compile-ground key-node))}
                                           ~@(:cols row)])]
               (compile targets [row*]))
             ;; The #js {} reader only allows keys that are strings or
             ;; unqualified keywords. Without and alternative notation
             ;; this branch should never be entered.
             (let [row* (assoc row :cols `[~{:tag :cat
                                             :elements [key-node val-node]}
                                           ~@(:cols row)])
                   search-target (gensym* "okv__")]
               [:search [search-target `(map (fn [k#]
                                               [k# (goog.object/get ~target k#)])
                                             (goog.object/getKeys ~target))]
                (compile `[~search-target ~@targets*] [row*])])))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :let*
  [_ [target & targets* :as targets] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         [:pass (compile targets* [row])]

         :let*
         (let [xsym (gensym* "x__")
               targets* `[~xsym ~@targets*]
               matrix* [(assoc row :cols `[~(:binding node) ~@(:cols row)])]]
           [:bind [xsym (:expr node)]
            (compile targets* matrix*)])))
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
         [:pass (compile targets* [row])]

         :lit
         [:test `(= ~target ~(r.syntax/unparse node))
          (compile targets* [row])]))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :lvr
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         [:pass (compile targets* [row])]

         :lvr
         (if (r.matrix/get-var row node)
           [:test `(= ~target ~(:symbol node))
            (compile targets* [row])]
           [:bind [(:symbol node) target]
            (compile targets* [(r.matrix/add-var row node)])])))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :map
  [_ [target & targets*] matrix]
  (let [all-keys (mapcat
                  (fn [node]
                    (when (= (r.syntax/tag node) :map)
                      (keys (:map node))))
                  (r.matrix/first-column matrix))
        key-sort (sort-by
                  (comp - (frequencies all-keys))
                  (distinct all-keys))
        num-keys (count key-sort)
        matrix* (mapv
                 (fn [node row]
                   (case (r.syntax/tag node)
                     :any
                     (assoc row :cols `[~@(repeat num-keys node) ~@(:cols row)])

                     :map
                     (let [the-map (:map node)]
                       (if (and (r.syntax/search? node)
                                (some r.syntax/variable-node? (keys the-map)))
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
    [[:test `(map? ~target)
      (compile `[~@(repeat num-keys target) ~@targets*] matrix*)]]))


(defmethod compile-specialized-matrix :mkv
  [_ [target & targets* :as targets] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         [:pass (compile targets* [row])]

         :mkv
         (let [[key-node val-node] (:entry node)
               row* (assoc row :cols `[~{:tag :let*
                                         :binding val-node
                                         :expr `(get ~target ~(compile-ground key-node))}
                                       ~@(:cols row)])]
           (compile targets [row*]))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :mvr
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         [:pass (compile targets* [row])]

         :mvr
         (let [sym (:symbol node)]
           (if (r.matrix/get-var row node)
             (let [save-id (gensym "save__")]
               ;; :save/:load is necessary here since it is possible
               ;; for the state of a memory variable to persist
               ;; even after a match failure.
               [:save save-id
                [:bind [sym `(conj ~sym ~target)]
                 (compile targets* [row])]
                [:load save-id]])
             [:bind [sym [target]]
              (compile targets* [(r.matrix/add-var row node)])]))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :not
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         [:pass (compile targets* [row])]

         :not
         (let [save-id (gensym "save__")
               not-matrix [{:cols [(:argument node)]
                            :env (:env row),
                            :rhs [:load save-id]}]]
           [:save save-id
            (binding [*negating* true]
              (compile [target] not-matrix))
            (compile targets* [row])])))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :prt
  [_ [target & targets* :as targets] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         [:pass (compile targets* [row])]

         :prt
         (let [{:keys [left right]} node
               right (if (some? right)
                       right
                       {:tag :cat
                        :elements []})
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
               msym (gensym* "m__")]
           (case [(r.syntax/variable-length? left) (r.syntax/variable-length? right)]
             ;; Invariable length.
             [false false]
             (cond
               (and (zero? llen)
                    (zero? rlen))
               [:test `(not (seq ~target))
                (compile targets [row])]

               (zero? llen)
               [:test `(= (bounded-count ~(inc rlen) ~target) ~rlen)
                (compile targets [(assoc row :cols `[~right ~@(:cols row)])])]

               (zero? rlen)
               [:test `(= (bounded-count ~(inc llen) ~target) ~llen)
                (compile targets [(assoc row :cols `[~left ~@(:cols row)])])]

               :else
               [:bind [lsym (take-form llen target)]
                [:test `(= (count ~lsym) ~llen)
                 [:bind [rsym (drop-form llen target)]
                  [:test `(= (count ~rsym) ~rlen)
                   (compile `[~lsym ~rsym ~@targets*]
                            [(assoc row :cols `[~left ~right ~@(:cols row)])])]]]])

             ;; Variable length on the right.
             [false true]
             [:bind [lsym (take-form llen target)]
              [:test (if (zero? llen)
                       `(not (seq ~lsym))
                       `(= (count ~lsym) ~llen))
               [:bind [rsym (drop-form llen target)]
                (compile `[~lsym ~rsym ~@targets*]
                         [(assoc row :cols `[~left ~right ~@(:cols row)])])]]]

             ;; Variable length on the left.
             [true false]
             (if (zero? rlen)
               (compile targets [(assoc row :cols `[~left ~@(:cols row)])])
               [:bind [nsym `(count ~target)]
                [:bind [msym `(max 0 (- ~nsym ~rlen))]
                 [:bind [lsym (take-form msym target)]
                  [:bind [rsym (drop-form msym target)]
                   [:test (if (zero? rlen)
                            `(not (seq ~rsym))
                            `(= (count ~rsym) ~rlen))
                    (compile `[~lsym ~rsym ~@targets*]
                             [(assoc row :cols `[~left ~right ~@(:cols row)])])]]]]])

             ;; Variable length on both sides.
             [true true]
             (let [parts-sym (gensym* "parts__")]
               [:search [parts-sym `(r.util/partitions 2 ~target)]
                [:bind [lsym `(nth ~parts-sym 0)]
                 [:bind [rsym `(nth ~parts-sym 1)]
                  (compile `[~lsym ~rsym ~@targets*]
                           [(assoc row :cols `[~left ~right ~@(:cols row)])])]]])))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :prd
  [_ [target & targets* :as targets] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         [:pass (compile targets* [row])]

         :prd
         (let [arguments (:arguments node)]
           [:test `(~(:form node) ~target)
            (if (seq arguments)
              (compile targets [(assoc row :cols `[~{:tag :cnj
                                                     :arguments arguments}
                                                   ~@(:cols row)])])
              (compile targets* [row]))])))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :quo
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         [:pass (compile targets* [row])]

         :quo
         [:test `(= ~target ~(r.syntax/unparse node))
          (compile targets* [row])]))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :rxt
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         [:pass (compile targets* [row])]

         :rxt
         [:test `(string? ~target)
          [:test `(re-matches ~(:regex node) ~target)
           (compile targets* [row])]]))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :rxc
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         [:pass (compile targets* [row])]

         :rxc
         (let [ret-sym (gensym* "ret__")
               cols* `[~(:capture node) ~@(:cols row)]
               row* (assoc row :cols cols*)]
           [:test `(string? ~target)
            [:bind [ret-sym `(re-matches ~(:regex node) ~target)]
             [:test `(some? ~ret-sym) 
              (compile `[~ret-sym ~@targets*] [row*])]]])))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :rp*
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         [:pass (compile targets* [row])]

         :rp*
         (let [elements (:elements node)
               n (count elements)
               ;; Symbol which is bound to the first n elements of
               ;; target at the top of each loop.
               init (gensym* "init__")
               ;; Gaurd pattern to check the length of the initial
               ;; slice at the top of each loop.
               init-grd {:tag :grd
                         :expr `(= (count ~init) ~n)}
               ;; The sequence to match.
               init-cat {:tag :cat
                         :elements elements}
               ;; Unbound memory variables must be bound before loop
               ;; execution and added to the compilation environment
               ;; for the internal loop body.
               init-mvrs (keep
                          (fn [node]
                            (when (and (= (r.syntax/tag node) :mvr)
                                       (not (r.matrix/get-var row node)))
                              node))
                          (r.syntax/variables init-cat))
               env* (into (:env row) init-mvrs)
               row* (assoc row :env env*)
               loop-id (gensym "loop__")
               loop-targets (into [target] (keep
                                            (fn [node]
                                              (when (= (r.syntax/tag node) :mvr)
                                                (:symbol node))))
                                  env*)
               loop-tree [:loop loop-id loop-targets
                          [:bind [init (take-form n target)]
                           [:branch
                            [(compile [init init]
                                      [(assoc row*
                                              :cols [init-grd init-cat]
                                              :rhs [:bind [target (drop-form n target)]
                                                    [:recur loop-id loop-targets]])])
                             [:test `(not (seq ~target))
                              (compile targets* [row*])]]]]]]
           (reduce
            (fn [tree [_ sym]]
              [:bind [sym []] tree])
            loop-tree
            init-mvrs))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :rp+
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         [:pass (compile targets* [row])]

         :rp+
         (let [elements (:elements node)
               times (:n node)
               n (count elements)
               ;; The minimum number of times the loop must execute.
               ;; Symbol which tracks the number of successful iterations.
               iter (gensym* "iter__")
               ;; Symbol which is bound to the first n elements of
               ;; target at the top of each loop.
               init (gensym* "init__")
               ;; Gaurd pattern to check the length of the initial
               ;; slice at the top of each loop.
               init-grd {:tag :grd
                         :expr `(= (count ~init) ~n)}
               ;; The sequence to match.
               init-cat {:tag :cat
                         :elements elements}
               init-vars (r.syntax/variables init-cat)
               ;; Memory variables must be tracked during loop
               ;; execution and added to the compilation environment
               ;; for the internal loop body.
               init-mvrs (keep
                          (fn [node]
                            (when (= (r.syntax/tag node) :mvr)
                              node))
                          init-vars)
               env* (into (:env row) init-vars)
               row* (assoc row :env env*)
               loop-id (gensym "loop__")
               loop-targets (into [target iter]
                                  (map (fn [node]
                                         (:symbol node)))
                                  init-mvrs)
               loop-tree [:loop loop-id loop-targets
                          [:bind [init (take-form n target)]
                           [:branch
                            [(compile [init init]
                                      [(assoc row*
                                              :cols [init-grd init-cat]
                                              :rhs [:bind [target (drop-form n target)]
                                                    [:bind [iter `(inc ~iter)]
                                                     [:recur loop-id loop-targets]]])])
                             [:test `(<= ~times ~iter)
                              [:test `(not (seq ~target))
                               (compile targets* [row*])]]]]]]]
           [:bind [init (take-form n target)]
            (compile [init init]
                     [(assoc row
                             :cols [init-grd init-cat]
                             :rhs [:bind [target (drop-form n target)]
                                   [:bind [iter 1]
                                    loop-tree]])])])))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :rst
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         [:pass (compile targets* [row])]

         :rst
         (let [mvr (:mvr node)
               sym (:symbol mvr)]
           (if (r.matrix/get-var row mvr)
             [:bind [sym `(into ~sym ~target)]
              (compile targets* [(r.matrix/add-var row mvr)])]
             [:bind [sym `(vec ~target)]
              (compile targets* [(r.matrix/add-var row mvr)])]))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :set
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         [:pass (compile targets* [row])]

         :set
         (cond
           (r.syntax/search? node)
           (let [elements (:elements node)
                 n (count elements)
                 ;; Symbol for the size of target.
                 m-sym (gensym* "m__")
                 ;; Symbol for each permutation of target.
                 perm-sym (gensym* "perm__")
                 targets** `[~perm-sym ~@targets*]
                 matrix*  [(assoc row :cols `[~{:tag :cat
                                                :elements (vec elements)}
                                              ~@(:cols row)])]]
             [:test `(set? ~target)
              [:bind [m-sym `(count ~target)]
               [:test `(<= ~n ~m-sym)
                (if (negating?)
                  [:find [perm-sym `(r.util/k-combinations ~target ~n)]
                   (compile targets** matrix*)]
                  [:search [perm-sym `(r.util/k-combinations ~target ~n)]
                   (compile targets** matrix*)])]]])

           (some (comp #{:map :set} r.syntax/tag) (r.syntax/subnodes node))
           (let [elements (:elements node)
                 n (count elements)
                 ;; Symbol for the size of target.
                 m-sym (gensym* "m__")
                 ;; Symbol for each permutation of target.
                 perm-sym (gensym* "perm__")]
             [:test `(set? ~target)
              [:bind [m-sym `(count ~target)]
               [:test `(<= ~n ~m-sym)
                [:find [perm-sym `(r.util/k-combinations ~target ~n)]
                 (compile `[~perm-sym ~@targets*]
                          [(assoc row :cols `[~{:tag :cat
                                                :elements (vec elements)}
                                              ~@(:cols row)])])]]]])

           :else
           [:test `(set/subset? ~(compile-ground node) ~target)
            (compile targets* [row])])))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :seq
  [_ [target :as targets] matrix]
  (mapv
   (fn [node row]
     (case (r.syntax/tag node)
       :any
       [:pass (compile targets [row])]

       :seq
       [:test `(seq? ~target)
        (binding [*collection-context* :seq]
          (compile targets [(assoc row :cols `[~(:prt node) ~@(:cols row)])]))]))
   (r.matrix/first-column matrix)
   (r.matrix/drop-column matrix)))


(defmethod compile-specialized-matrix :unq
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [node row]
       (case (r.syntax/tag node)
         :any
         [:pass (compile targets* [row])]

         :unq
         [:test `(= ~target ~(:expr node))
          (compile targets* [row])]))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))

(defmethod compile-specialized-matrix :vec
  [_ [target & targets* :as targets] matrix]
  (mapv
   (fn [node row]
     (case (r.syntax/tag node)
       :any
       [:pass (compile targets [row])]

       :vec
       (if (literal? node)
         [:test `(= ~target ~(compile-ground node))
          (compile targets* [row])]
         [:test `(vector? ~target)
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
              (compile [target] [row*])))])))
   (r.matrix/first-column matrix)
   (r.matrix/drop-column matrix)))


(defn debug-compile
  {:private true}
  [targets matrix]
  ;; This doesn't print the state accurately.
  (pprint/print-table
   (mapv
    (fn [row]
      (reduce merge
              (mapv
               (fn [target node]
                 {target (r.syntax/unparse node)
                  :node node})
               targets
               (:cols row))))
    matrix)))


(defn compile
  "Compile the pattern matrix with respect to targets to a decision
  tree."
  [targets matrix]
  (cond
    (= (count matrix) 0)
    [:fail]

    (every? r.syntax/any-node? (mapcat identity (r.matrix/columns (take 1 matrix))))
    (r.matrix/action (first matrix))

    :else
    (let [i (some (fn [[i column]]
                    (when (not-every? r.syntax/any-node? column)
                      i))
                  (map-indexed vector (r.matrix/columns matrix)))]
      (if (= 0 i)
        (let [tags (into []
                         (comp (map r.syntax/tag)
                               (remove #{:any})
                               (distinct))
                         (r.matrix/first-column matrix))
              arms (into [] (mapcat
                             (fn [tag]
                               (let [s-matrix (specialize-matrix tag matrix)]
                                 (compile-specialized-matrix tag targets s-matrix))))
                         tags)
              arms (if (and (some (fn [[tag]]
                                    (= tag :test))
                                  arms)
                            (not (some (fn [[tag _]]
                                         (= tag :pass))
                                       arms)))
                     (conj arms [:fail])
                     arms)]
          [:branch arms])
        (let [targets* (r.matrix/swap targets 0 i)
              matrix* (r.matrix/swap-column matrix 0 i)]
          (compile targets* matrix*))))))


;; ---------------------------------------------------------------------
;; Code emission


(defn emit*
  "Rewrite the decision tree as Clojure code without optimizations."
  [tree fail kind]
  (let [[tag :as node] tree]
    (case tag
      :action
      (let [[_ expr] node]
        expr)

      :bind
      (let [[_ bindings body] node]
        ;; This isn't necessary but makes the resulting code easier to
        ;; read.
        (loop [bindings bindings
               body body]
          (let [[tag] body]
            (if (= tag :bind)
              (let [[_ bindings* body*] body]
                (recur (into bindings bindings*) body*))
              `(let ~bindings
                 ~(emit* body fail kind))))))

      :branch
      (case kind
        :search
        (let [[_ arms] node
              arms (remove #{[:fail]} arms)]
          (case (count arms)
            0
            fail

            1
            (emit* (first arms) fail kind)

            ;;else
            `(concat
              ~@(map
                 (fn [arm]
                   (emit* arm fail kind))
                 arms))))

        (:find :match)
        (let [[_ arms] node
              arms (if (= kind :find)
                     (remove #{[:fail]} arms)
                     arms)]
          (case (count arms)
            0
            fail

            1
            (emit* (first arms) fail kind)

            2
            (emit* (first arms)
                   (emit* (second arms) fail kind)
                   kind)

            ;; else
            (let [fsyms (mapv
                         (fn [_]
                           (gensym "backtrack__"))
                         arms)]
              `(letfn [~@(map
                           (fn [fsym fail arm]
                             `(~fsym []
                               ~(emit* arm fail kind)))
                           fsyms
                           (conj (mapv
                                  (fn [fsym]
                                    `(~fsym))
                                  (rest fsyms))
                                 fail)
                           arms)]
                 (~(first fsyms)))))))

      :fail
      fail

      :loop
      (let [[_ ident syms body] node]
        `(letfn [(~ident ~syms
                  ~(emit* body fail kind))]
           (~ident ~@syms)))

      :load
      (let [[_ ident] node]
        `(~ident))

      :pass
      (let [[_ body] node]
        (emit* body fail kind))

      :save
      (let [[_ ident body1 body2] node
            f-sym (gensym "f__")]
        `(letfn [(~ident [] ~fail)
                 (~f-sym [] ~(emit* body2 fail kind))]
           ~(emit* body1 `(~f-sym) kind)))

      :search
      (let [[_ [sym seq-expr] body] node]
        (case kind
          (:find :match)
          (recur [:find [sym seq-expr] body] fail kind)

          ;; Assumes action node evaluates to a singleton list.
          :search
          `(mapcat
            (fn [~sym]
              ~(emit* body fail kind))
            ~seq-expr)))

      :find
      (let [[_ [sym seq-expr] body] node
            result-sym (gensym "result__")
            test-fail-sym (gensym "fail__")]
        `(let [~test-fail-sym (reify)
               ~result-sym (reduce
                            (fn [~test-fail-sym ~sym]
                              (let [~result-sym ~(emit* body test-fail-sym kind)]
                                (if (identical? ~result-sym ~test-fail-sym)
                                  ~test-fail-sym
                                  (reduced ~result-sym))))
                            ~test-fail-sym
                            ~seq-expr)]
           (if (identical? ~result-sym ~test-fail-sym)
             ~fail
             ~result-sym)))

      :recur
      (let [[_ ident syms] node]
        `(~ident ~@syms))

      :test
      (let [[_ test body] node]
        `(if ~test
           ~(emit* body fail kind)
           ~fail)))))

(s/fdef emit*
  :args (s/cat :tree :meander.match.gamma/tree
               :fail any?
               :kind #{:find :match :search}))


(defn rewrite-bind-unused
  "If a binding is never used, remove it."
  {:private true}
  [[_ [bsym bval] body]]
  (if (some #{bsym} (tree-seq coll? seq body))
    [:bind [bsym bval] body]
    body))


(defn rewrite-branch-shared-bindings
  "If a there are consecutive bindings to the same expression in the
  arms of a branch, create a new branch arm with a shared binding and
  move those arms beneath it."
  {:private true}
  [[_ nodes]]
  (loop [;; Queue
         q-nodes nodes
         ;; Stack
         s-nodes []]
    (if-some [[tag :as node] (first q-nodes)]
      (if (or (= tag :bind)
              (= tag :search))
        (let [[_ [bsym bval]] node]
          (if (::gensym? (meta bsym))
            (let [[xs ys] (split-with
                           (fn [[other-tag :as other-node]]
                             (and (= other-tag tag)
                                  (let [[_ [_ other-bval]] other-node]
                                    (= other-bval bval))))
                           q-nodes)
                  q-nodes ys
                  s-nodes (conj s-nodes
                                (if (= xs [node])
                                  node
                                  (let [bsym* (gensym "x__")]
                                    [tag [bsym* bval]
                                     [:branch
                                      (mapv
                                       (fn [[_ [bsym _] body]]
                                         ;; This should be safe since
                                         ;; we're only renaming
                                         ;; symbols we've generated.
                                         (walk/postwalk-replace {bsym bsym*} body))
                                       xs)]])))]
              (recur q-nodes s-nodes))
            (let [q-nodes (rest q-nodes)
                  s-nodes (conj s-nodes node)]
              (recur q-nodes s-nodes))))
        (let [q-nodes (rest q-nodes)
              s-nodes (conj s-nodes node)]
          (recur q-nodes s-nodes)))
      [:branch s-nodes])))


(defn rewrite-branch-equal-bindings
  {:private true}
  [[_ nodes]]
  (loop [q-nodes nodes
         s-nodes []]
    (if-some [[tag :as node] (first q-nodes)]
      (if (= tag :bind)
        (let [[_ bindings] node
              [xs ys] (split-with
                       (fn [[other-tag :as other-node]]
                         (and (= other-tag :bind)
                              (let [[_ other-bindings] other-node]
                                (= other-bindings bindings))))
                       q-nodes)
              q-nodes ys
              s-nodes (conj s-nodes
                            (if (= xs [node])
                              node
                              [:bind bindings
                               [:branch (mapv (fn [[_ _ body]] body) xs)]]))]
          (recur q-nodes s-nodes))
        (let [q-nodes (rest q-nodes)
              s-nodes (conj s-nodes node)]
          (recur q-nodes s-nodes)))
      [:branch s-nodes])))


(defn rewrite-branch-equal-tests
  {:private true}
  [[_ nodes]]
  (loop [q-nodes nodes
         s-nodes []]
    (if-some [[tag :as node] (first q-nodes)]
      (if (= tag :test)
        (let [[_ test-expr] node
              [xs ys] (split-with
                       (fn [[other-tag :as other-node]]
                         (and (= other-tag :test)
                              (let [[_ other-test-expr] other-node]
                                (= other-test-expr test-expr))))
                       q-nodes)
              q-nodes ys
              s-nodes (conj s-nodes
                            (if (= xs [node])
                              node
                              [:test test-expr
                               [:branch (mapv (fn [[_ _ body]] body) xs)]]))]
          (recur q-nodes s-nodes))
        (let [q-nodes (rest q-nodes)
              s-nodes (conj s-nodes node)]
          (recur q-nodes s-nodes)))
      [:branch s-nodes])))


(defn rewrite-branch-splice-branches
  {:private true}
  [[_ nodes]]
  [:branch
   (into []
         (mapcat
          (fn [[tag :as node]]
            (if (= tag :branch)
              (let [[_ inner-nodes] node]
                inner-nodes)
              (list node))))
         nodes)])


(defn rewrite-branch-one-fail
  {:private true}
  [[_ nodes]]
  (if-some [fail-node (some
                       (fn [node]
                         (when (s/valid? :meander.match.gamma.tree/fail-node node)
                           node))
                       nodes)]
    [:branch
     (conj (into [] (remove #{fail-node}) nodes) fail-node)]
    [:branch
     nodes]))


(defn rewrite-branch-one-case
  {:private true}
  [[_ nodes]]
  (if (= (count nodes) 1)
    (first nodes)
    [:branch nodes]))


(defn rewrite-tree
  {:private true}
  [tree]
  (let [tree* (clojure.walk/prewalk
               (fn [x]
                 (if (s/valid? :meander.match.gamma.tree/branch-node x)
                   (rewrite-branch-one-case x)
                   x))
               tree)
        tree* (clojure.walk/prewalk
               (fn [x]
                 (if (s/valid? :meander.match.gamma.tree/branch-node x)
                   (-> x
                       rewrite-branch-splice-branches
                       rewrite-branch-one-fail
                       rewrite-branch-shared-bindings
                       rewrite-branch-equal-bindings
                       rewrite-branch-equal-tests)
                   x))
               tree*)]
    (if (= tree tree*)
      tree
      (recur tree*))))


(defn emit
  "Rewrite the decision tree as Clojure code with optimizations."
  [tree fail kind]
  (let [tree* (rewrite-tree tree)]
    (emit* tree* fail kind)))

(s/fdef emit
  :args (s/cat :tree :meander.match.gamma/tree
               :fail any?
               :kind #{:find :match :search}))

;; ---------------------------------------------------------------------
;; match pattern checking

(defmulti check-node
  "Validates the semantics of node returning

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


(defmethod check-node :dsj
  [node env _]
  (let [arguments (:arguments node)
        env* (into #{} (remove r.syntax/mvr-node?) env)
        term-vars (sequence
                   (map
                    (fn [term]
                      ;; We don't need to account for bound variables.
                      (set/difference (r.syntax/logic-variables term) env*)))
                   arguments)
        all-vars (reduce set/union #{} term-vars)
        problems (sequence
                  (comp (map vector)
                        (keep
                         (fn [[term term-vars]]
                           (let [absent-vars (set/difference all-vars term-vars)]
                             (when (seq absent-vars)
                               [term (into #{} (map r.syntax/unparse) absent-vars)])))))
                  arguments
                  term-vars)]
    (if (seq problems)
      [:error [{:message "Every pattern of an or pattern must have references to the same unbound logic variables."
                :ex-data {:env (into #{} (map r.syntax/unparse) env)
                          :problems (mapv
                                     (fn [[pat absent-vars]]
                                       {:pattern (r.syntax/unparse pat)
                                        :absent absent-vars})
                                     problems)}}]]
      [:okay (r.syntax/children node) env])))


(defmethod check-node :rp*
  [node env _]
  (let [elements (:elements node)
        init-cat {:tag :cat
                  :elements elements}
        init-vars (r.syntax/variables init-cat)
        unbound-lvrs (into #{} (comp (filter r.syntax/lvr-node?)
                                     (remove env))
                           init-vars)]
    (cond
      (seq unbound-lvrs)
      [:error [{:message "Zero or more patterns may not have references to unbound logic variables."
                :ex-data {:unbound (into #{} (map r.syntax/unparse) unbound-lvrs)}}]]

      (empty? elements)
      (let [dots '...]
        [:error
         [{:message (str "Zero or more (" dots ") is a postfix operator. It must have some value in front of it. (i.e. [1 " dots " ?x])")}]])

      :else
      [:okay (r.syntax/children node) env])))


(defmethod check-node :rp+
  [node env _]
  (let [elements (:elements node)
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
  [:okay [] (conj env node)])


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
  [:okay [] (conj env node)])


(defmethod check-node :prt
  [node env search?]
  (if search?
    [:okay (r.syntax/children node) env]
    (let [{left :left, right :right} node]
      (if (and (some? right)
               (r.syntax/variable-length? left)
               (r.syntax/variable-length? right))
        [:error [{:message "A variable length subsequence pattern may not be followed by another variable length subsequence pattern."
                  :ex-data {}}]]
        [:okay (r.syntax/children node) env]))))


(defmethod check-node :set
  [node env search?]
  (if search?
    [:okay (r.syntax/children node) env]
    (if (r.syntax/ground? node)
      [:okay [] env]
      [:error [{:message "Set patterns may not contain variables."
                :ex-data {}}]])))


(defn check*
  "Checks if node is semantically valid with respect to env. Returns

  [:error [{:message string?, :ex-data map?} & syntax-trace]
    whenever an error is detected. syntax-trace is a sequence forms
    which represent the path to the invalid pattern from the leaf
    to the root.


  [:okay exit-env]
    whenever the semantics of node are valid. exit-env is a set of all
    logic and memory variables which would be bound by a succesful
    pattern match; equivalent to (meander.syntax.gamma/variables node)."
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
         [:okay env]
         children)))))


(defn check
  "Checks if node is semantically valid. Returns an instance of
  clojure.lang.Exception if an error can be found and nil otherwise."
  [node search?]
  (let [[tag :as result] (check* node #{} search?)]
    (case tag
      :error
      (let [[_ [{:keys [message ex-data]} & trace]] result
            syntax-trace (into [] (comp (remove (comp #{:cat :prt} r.syntax/tag))
                                        (map r.syntax/unparse))
                               trace)]
        (ex-info message (assoc ex-data :syntax-trace syntax-trace)))

      :okay
      nil)))

;; ---------------------------------------------------------------------
;; match macro


(s/def :meander.match.gamma/expr
  any?)


(s/def :meander.match.gamma/pattern
  any?)


(s/def :meander.match.gamma/clause
  (s/cat :pat :meander.match.gamma/pattern
         :rhs :meander.match.gamma/expr))


(s/def :meander.match.gamma.match/clauses
  (s/* (s/cat :pat :meander.match.gamma/pattern
              :rhs :meander.match.gamma/expr)))


(s/def :meander.match.gamma.match/args
  (s/cat :expr :meander.match.gamma/expr
         :clauses (s/* :meander.match.gamma/clause)))


(s/def :meander.match.gamma.match/data
  (s/keys :req-un [:meander.match.gamma/expr
                   :meander.matrix.gamma/matrix
                   :meander.matrix.gamma.data/final-clause]))


(s/def :meander.match.gamma.match.data/final-clause
  (s/nilable :meander.matrix.alpha/row))


(s/fdef analyze-match-args
  :args (s/cat :match-args :meander.match.gamma.match/args)
  :ret :meander.match.gamma.match/data)


(defn expand-node
  {:private true}
  [node]
  (walk/prewalk
   (fn f [x]
     (if (r.syntax/node? x)
       (case (r.syntax/tag x)
         :cnj
         (let [arguments (:arguments x)
               arguments* (mapcat
                           (fn [x]
                             (if (= (r.syntax/tag x) :cnj)
                               (:arguments x)
                               (list x)))
                           arguments)]
           (if (= arguments arguments*)
             x
             (f {:tag :cnj
                 :arguments arguments*})))

         :map
         (if-some [rest-map (:rest-map x)]
           (let [lvr-map (into {}
                               (keep (fn [k-node]
                                       (if (or (r.syntax/ground? k-node)
                                               (r.syntax/variable-node? k-node))
                                         [k-node k-node]
                                         [k-node {:tag :lvr
                                                  :symbol (gensym "?v__")}])))
                               (keys (:map x)))
                 map* (into {}
                            (map
                             (fn [[k-node v-node]]
                               (let [node (get lvr-map k-node)]
                                 (if (= node k-node)
                                   [k-node v-node]
                                   [{:tag :cnj
                                     :arguments [k-node node]}
                                    v-node]))))
                            (:map x))
                 x* (dissoc x :rest-map)
                 x* (assoc x* :map map*)]
             (f {:tag :cnj
                 :arguments [x* {:tag :app
                                 :fn-expr `(fn [m#]
                                             (dissoc m# ~@(map r.syntax/unparse (vals lvr-map))))
                                 :arguments [rest-map]}]}))
           (if-some [as (:as x)]
             {:tag :cnj
              :arguments [as (dissoc x :as)]}
             x))
         x)
       x))
   node))

(defn parse-expand
  {:private true}
  [x env]
  (expand-node (r.syntax/parse x env)))


;; TODO: Include useless clause analysis.
(defn analyze-match-args
  "Analyzes arguments as would be supplied to the match macro e.g.

    (expr clause action ,,,)

  Returns a map containing the following keys:

  :errors A sequence of semantic errors. These are instances of
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
   (let [data (s/conform :meander.match.gamma.match/args match-args)]
     (if (identical? data ::s/invalid)
       (throw (ex-info "Invalid match-args"
                       (s/explain-data :meander.match.gamma.match/args match-args)))
       (let [clauses (map
                      (fn [{:keys [pat rhs]}]
                        {:pat (r.syntax/parse pat env)
                         :rhs rhs})
                      (:clauses data))
             errors (into []
                          (keep
                           (fn [{pat :pat}]
                             (check pat false)))
                          clauses)
             matrix (mapv
                     (fn [{:keys [pat rhs]}]
                       {:cols [(expand-node pat)]
                        :env #{}
                        :rhs [:action rhs]})
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

(s/fdef match
  :args (s/cat :expr any?
               :clauses :meander.match.gamma.match/clauses)
  :ret any?)


(defmacro match
  {:arglists '([x & clauses])
   :style/indent [1]}
  [& match-args]
  (let [match-data (analyze-match-args match-args &env)
        expr (:expr match-data)
        matrix (:matrix match-data)
        final-clause (:final-clause match-data)
        errors (:errors match-data)]
    (if-some [error (first errors)]
      (throw error)
      (let [target (gensym "target__")
            fail (gensym "fail__")]
        (if (r.matrix/empty? matrix)
          (if (some? final-clause)
            (emit (compile [expr] [final-clause]) nil :match)
            `(throw (ex-info "non exhaustive pattern match" '~(meta &form))))
          `(let [~target ~expr
                 ~fail (fn []
                         ~(if (some? final-clause)
                            (emit (compile [target] [final-clause]) nil :match)
                            `(throw (ex-info "non exhaustive pattern match" '~(meta &form)))))]
             ~(emit (compile-with-memory-variables-initialized [target] matrix) `(~fail) :match)))))))


(defn analyze-search-args
  "Analyzes arguments as would be supplied to the search macro e.g.

    (expr clause action ,,,)

  Returns a map containing the following keys:

  :errors A sequence of semantic errors. These are instances of
    clojure.lang.Exception and are derived by applying check to the
    pattern of each clause.
  :expr The expression which is the target of pattern matching, the
    first argument to the match macro.
  :matrix The pattern matrix derived from the (clause action ,,,)
    forms. Each action expression is wrapped in a list."
  ([match-args]
   (analyze-search-args match-args {}))
  ([match-args env]
   (let [data (s/conform :meander.match.gamma.match/args match-args)]
     (if (identical? data ::s/invalid)
       (throw (ex-info "Invalid search args"
                       (s/explain-data :meander.match.gamma.match/args match-args)))
       (let [clauses (mapv
                      (fn [{:keys [pat rhs]}]
                        {:pat (r.syntax/parse pat env)
                         :rhs rhs})
                      (:clauses data))
             errors (into [] (keep
                              (fn [{pat :pat}]
                                (check pat true)))
                          clauses)
             matrix (mapv
                     (fn [{:keys [pat rhs]}]
                       {:cols [(expand-node pat)]
                        :env #{}
                        :rhs [:action `(list ~rhs)]})
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
    (if-some [error (first errors)]
      (throw error)
      (let [target (gensym "target__")]
        (if (r.matrix/empty? matrix)
          nil
          `(let [~target ~expr]
             ~(emit (compile-with-memory-variables-initialized [target] matrix) nil :search)))))))


(s/fdef search
  :args (s/cat :expr any?
               :clauses :meander.match.gamma.match/clauses)
  :ret (s/coll-of any? :kind sequential?))


(defn analyze-find-args
  "Analyzes arguments as would be supplied to the find macro e.g.

    (expr clause action ,,,)

  Returns a map containing the following keys:

  :errors A sequence of semantic errors. These are instances of
    clojure.lang.Exception and are derived by applying check to the
    pattern of each clause.
  :expr The expression which is the target of pattern matching, the
    first argument to the match macro.
  :matrix The pattern matrix derived from the (clause action ,,,)
    forms. Each action expression is wrapped in a list."
  ([match-args]
   (analyze-find-args match-args {}))
  ([match-args env]
   (let [data (s/conform :meander.match.gamma.match/args match-args)]
     (if (identical? data ::s/invalid)
       (throw (ex-info "Invalid match args"
                       (s/explain-data :meander.match.gamma.match/args match-args)))
       (let [clauses (mapv
                      (fn [{:keys [pat rhs]}]
                        {:pat (r.syntax/parse pat env)
                         :rhs rhs})
                      (:clauses data))
             errors (into [] (keep
                              (fn [{pat :pat}]
                                (check pat true)))
                          clauses)
             matrix (mapv
                     (fn [{:keys [pat rhs]}]
                       {:cols [(expand-node pat)]
                        :env #{}
                        :rhs [:action rhs]})
                     clauses)]
         {:errors errors
          :expr (:expr data)
          :matrix matrix})))))


(defmacro find
  "Like search but returns only the first successful match."
  {:arglists '([x & clauses])
   :style/indent [1]}
  [& match-args]
  (let [match-data (analyze-find-args match-args &env)
        expr (:expr match-data)
        matrix (:matrix match-data)
        errors (:errors match-data)]
    (if-some [error (first errors)]
      (throw error)
      (let [target (gensym "target__")]
        (if (r.matrix/empty? matrix)
          nil
          `(let [~target ~expr]
             ~(emit (compile-with-memory-variables-initialized [target] matrix) nil :find)))))))


(s/fdef find
  :args (s/cat :expr any?
               :clauses :meander.match.gamma.match/clauses)
  :ret any?)
