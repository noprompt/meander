(ns meander.match.alpha
  (:refer-clojure :exclude [compile])
  (:require [clojure.set :as set]
            [clojure.spec.alpha :as s]
            [clojure.walk :as walk]
            [meander.matrix.alpha :as r.matrix]
            [meander.syntax.alpha :as r.syntax]
            [meander.util.alpha :as r.util]))


(def
  ^{:dynamic true
    :doc "The current collection context e.g. :vector, :seq, etc."}
  *collection-context*)


(defn vector-context?
  "true if the current value of *collect-context* is :vector."
  []
  (= *collection-context* :vector))


(defn take-form
  "Form for taking n elements from target with respect to the current
  value of *collection-context*."
  [n target]
  (if (vector-context?)
    `(subvec ~target 0 (min (count ~target) ~n))
    `(take ~n ~target)))


(defn drop-form
  "Form for dropping n elements from target with respect to the
  current value of *collection-context*."
  [n target]
  (if (vector-context?)
    `(subvec ~target (min (count ~target) ~n))
    `(drop ~n ~target)))


(declare compile)


(s/def :meander.match.alpha.tree/action-node
  (s/tuple #{:action} any?))


(s/def :meander.match.alpha.tree/bind-node
  (s/tuple #{:bind} vector? :meander.match.alpha/tree))


(s/def :meander.match.alpha.tree/branch-node
  (s/tuple #{:branch}
           (s/coll-of :meander.match.alpha/tree
                      :kind vector?
                      :into [])))


(s/def :meander.match.alpha.tree/fail-node
  (s/tuple #{:fail}))


(s/def :meander.match.alpha.tree/identitifer
  simple-symbol?)


(s/def :meander.match.alpha.tree/loop-node
  (s/tuple #{:loop}
           :meander.match.alpha.tree/identitifer
           (s/coll-of simple-symbol? :kind vector? :into [])
           :meander.match.alpha/tree))


(s/def :meander.match.alpha.tree/save-node
  (s/tuple #{:save}
           :meander.match.alpha.tree/identitifer
           :meander.match.alpha/tree
           :meander.match.alpha/tree))


(s/def :meander.match.alpha.tree/load-node
  (s/tuple #{:load}
           :meander.match.alpha.tree/identitifer))


(s/def :meander.match.alpha.tree/pass-node
  (s/tuple #{:pass} any?))


(s/def :meander.match.alpha.tree/recur-node
  (s/tuple #{:recur}
           :meander.match.alpha.tree/identitifer
           (s/coll-of simple-symbol? :kind vector? :into [])))


(s/def :meander.match.alpha.tree/test-node
  (s/tuple #{:test} any? :meander.match.alpha/tree))


(s/def :meander.match.alpha.tree/search-node
  (s/tuple #{:search}
           (s/tuple simple-symbol? any?)
           :meander.match.alpha/tree))


(s/def :meander.match.alpha/tree
  (s/or :action :meander.match.alpha.tree/action-node
        :bind :meander.match.alpha.tree/bind-node
        :branch :meander.match.alpha.tree/branch-node
        :fail :meander.match.alpha.tree/fail-node
        :pass :meander.match.alpha.tree/pass-node
        :load :meander.match.alpha.tree/load-node
        :loop :meander.match.alpha.tree/loop-node
        :recur :meander.match.alpha.tree/recur-node
        :save :meander.match.alpha.tree/save-node
        :search :meander.match.alpha.tree/search-node
        :test :meander.match.alpha.tree/test-node))


(defn compile-ground
  {:private true}
  [[tag :as node]]
  (case tag
    :cat
    (let [[_ nodes] node]
      (map compile-ground nodes))

    :lit
    (r.syntax/unparse node)

    :map
    (let [[_ kvs] node]
      (into {}
            (map (fn [[k v]]
                   [(compile-ground k)
                    (compile-ground v)]))
            kvs))

    :prt
    (let [[_ {l :left, r :right}] node]
      (concat (when (some? l)
                (compile-ground l))
              (when (some? r)
                (compile-ground r))))

    :unq
    (let [[_ {expr :expr}] node]
      expr)

    :quo
    (let [[_ {form :form}] node]
      (list 'quote form))

    :vec
    (let [[_ prt] node]
      (into [] (compile-ground prt)))

    :seq
    (let [[_ prt] node]
      (if-some [l (seq (compile-ground prt))]
        (cons `list l)
        ()))

    :set
    (let [[_ set] node]
      (into #{} (map compile-ground set)))))


(defn specialize-matrix
  "Retains rows of the matrix whose tag is tag or :any."
  [tag matrix]
  (into []
        (keep-indexed
         (fn [i [other-tag _]]
           (when (or (= other-tag tag)
                     (= other-tag :any))
             (nth matrix i))))
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
               :matrix :meander.matrix.alpha/matrix)
  :ret (s/coll-of :meander.match.alpha/tree))

(defmethod compile-specialized-matrix :app
  [_ [target & targets*] matrix]
  (let [app-target (gensym "app_target__")]
    (mapv
     (fn [[tag :as node] row]
       (case tag
         :any
         [:pass (compile targets* [row])]

         :app
         (let [[_ {form :form, pat :pat}] node]
           [:bind [app-target `(~form ~target)]
            (compile `[~app-target ~@targets*]
                     [(assoc row :cols `[~pat ~@(:cols row)])])])))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :cat
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)
        max-size (reduce
                  (fn [n [tag :as node]]
                    (case tag
                      :any n
                      :cat (let [[_ nodes] node]
                             (max n (count nodes)))))
                  0
                  (r.matrix/first-column matrix))
        nth-syms (mapv
                  (fn [i]
                    (gensym (str "nth_" i "__")))
                  (range max-size))]
    (mapv
     (fn [[tag :as node] row]
       (case tag
         :any
         [:pass (compile targets* [row])]

         :cat
         (if (r.syntax/ground? node)
           [:test `(= ~target ~(vec (compile-ground node)))
            (compile targets* [row])]
           (let [[_ nodes] node
                 nth-syms (take (count nodes) nth-syms)
                 targets* `[~@nth-syms ~@targets*]]
             (reduce
              (fn [tree [i nth-sym]]
                [:bind [nth-sym `(nth ~target ~i)]
                 tree])
              (compile targets* [(assoc row :cols `[~@nodes ~@(:cols row)])])
              (reverse (map-indexed vector nth-syms)))))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :cnj
  [_ [target & targets*] matrix]
  (let [max-args (reduce
                  (fn [n [tag :as node]]
                    (case tag
                      :any n
                      :cnj (let [[_ {terms :terms}] node]
                             (max n (count terms)))))
                  0
                  (r.matrix/first-column matrix))
        targets* `[~@(repeat max-args target) ~@targets*]
        matrix* (mapv
                 (fn [[tag :as node] row]
                   (case tag
                     :any
                     (assoc row :cols `[~@(repeat max-args node) ~@(:cols row)])

                     :cnj
                     (let [[_ {terms :terms}] node]
                       (assoc row :cols `[~@terms ~@(repeat (- max-args (count terms)) '[:any _]) ~@(:cols row)]))))
                 (r.matrix/first-column matrix)
                 (r.matrix/drop-column matrix))]
    [(compile targets* matrix*)]))


(defmethod compile-specialized-matrix :drp
  [_ [target & targets*] matrix]
  [(compile (vec targets*) (r.matrix/drop-column matrix))])


(defmethod compile-specialized-matrix :dsj
  [_ targets matrix]
  (let [max-args (reduce
                  (fn [n [tag :as node]]
                    (case tag
                      :any n
                      :dsj (let [[_ {terms :terms}] node]
                             (max n (count terms)))))
                  0
                  (r.matrix/first-column matrix))
        matrix* (into []
                      (mapcat
                       (fn [[tag :as node] row]
                         (case tag
                           :any
                           [(assoc row :cols `[~node ~@(:cols row)])]

                           :dsj
                           (let [[_ {terms :terms}] node
                                 cols (:cols row)]
                             (map
                              (fn [term]
                                (assoc row :cols `[~term ~@cols]))
                              terms))))
                       (r.matrix/first-column matrix)
                       (r.matrix/drop-column matrix)))]
    [(compile targets matrix*)]))


(defmethod compile-specialized-matrix :grd
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [[tag :as node] row]
       (case tag
         :any
         [:pass
          (compile targets* [row])]

         :grd
         (let [[_ {form :form}] node]
           [:test form
            (compile targets* [row])])))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :let
  [_ [target & targets* :as targets] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [[tag :as node] row]
       (case tag
         :any
         [:pass (compile targets* [row])]

         :let
         (let [[_ {:keys [binding expr]}] node
               xsym (gensym "x__")
               targets* `[~xsym ~@targets*]
               matrix* [(assoc row :cols `[~binding ~@(:cols row)])]]
           [:bind [xsym expr]
            (compile targets* matrix*)])))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :lit
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [[tag :as node] row]
       (case tag
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
     (fn [[tag :as node] row]
       (case tag
         :any
         [:pass (compile targets* [row])]

         :lvr
         (let [[_ sym] node]
           (if (r.matrix/get-var row node)
             [:test `(= ~target ~sym)
              (compile targets* [row])]
             [:bind [sym target]
              (compile targets* [(r.matrix/add-var row node)])]))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :map
  [_ [target & targets*] matrix]
  (let [all-keys (mapcat
                  (fn [[tag :as node]]
                    (when (= tag :map)
                      (let [[_ data] node]
                        (keys data))))
                  (r.matrix/first-column matrix))
        key-sort (sort-by
                  (comp - (frequencies all-keys))
                  (distinct all-keys))
        num-keys (count key-sort)
        matrix* (mapv
                 (fn [[tag :as node] row]
                   (case tag
                     :any
                     (assoc row :cols `[~@(repeat num-keys node) ~@(:cols row)])

                     :map
                     (let [[_ the-map] node]
                       (if (and (r.syntax/search? node)
                                (some r.syntax/variable-node? (keys the-map)))
                         (let [[_ the-map] node
                               set-node [:set (map
                                               (fn [[k-node v-node]]
                                                 [:cat [k-node v-node]])
                                               the-map)]
                               let-node [:let {:binding set-node
                                               :expr `(set ~target)}]]
                           (assoc row :cols `[~let-node ~@(repeat (dec num-keys) '[:any _]) ~@(:cols row)]))
                         (let [[_ data] node
                               new-cols (sort-by
                                         (fn [[tag]]
                                           (if (= tag :mkv)
                                             0
                                             1))
                                         (map
                                          (fn [key]
                                            (if-some [entry (find data key)]
                                              [:mkv entry]
                                              '[:any _]))
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
     (fn [[tag :as node] row]
       (case tag
         :any
         [:pass (compile targets* [row])]

         :mkv
         (let [[_ [key-node val-node]] node
               row* (assoc row :cols `[~[:let {:binding val-node
                                               :expr `(get ~target ~(compile-ground key-node))}]
                                       ~@(:cols row)])]
           (compile targets [row*]))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :mvr
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [[tag :as node] row]
       (case tag
         :any
         [:pass (compile targets* [row])]

         :mvr
         (let [[_ sym] node]
           (if (r.matrix/get-var row node)
             [:bind [sym `(conj ~sym ~target)]
              (compile targets* [row])]
             [:bind [sym [target]]
              (compile targets* [(r.matrix/add-var row node)])]))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :not
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [[tag :as node] row]
       (case tag
         :any
         [:pass (compile targets* [row])]

         :not
         (let [[_ {term :term}] node
               save-id (gensym "save__")
               not-matrix [{:cols [term]
                            :env (:env row),
                            :rhs [:load save-id]}]]
           [:save save-id
            (compile [target] not-matrix)
            (compile targets* [row])])))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :prt
  [_ [target & targets* :as targets] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [[tag :as node] row]
       (case tag
         :any
         [:pass (compile targets* [row])]

         :prt
         (let [[_ {:keys [left right]}] node
               right (if (some? right)
                       right
                       [:cat []])
               ;; Left tree symbol
               lsym (gensym "l__")
               ;; Left min length
               llen (r.syntax/min-length left)
               ;; Right tree symbol
               rsym (gensym "r__")
               ;; Right min length
               rlen (r.syntax/min-length right)
               ;; Target length symbol
               nsym (gensym "n__")
               ;; Target length symbol minus either the left or right min length
               msym (gensym "m__")]
           (case [(r.syntax/variable-length? left) (r.syntax/variable-length? right)]
             ;; Invariable length.
             [false false]
             (cond
               (and (zero? llen)
                    (zero? rlen))
               [:test `(not (seq ~target))
                (compile targets [row])]

               (zero? llen)
               [:test `(= (count ~target) ~llen)
                (compile targets [(assoc row :cols `[~right ~@(:cols row)])])]

               (zero? rlen)
               [:test `(= (count ~target) ~llen)
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
             (let [parts-sym (gensym "parts__")]
               [:search [parts-sym `(r.util/partitions 2 ~target)]
                [:bind [lsym `(nth ~parts-sym 0)]
                 [:bind [rsym `(nth ~parts-sym 1)]
                  (compile `[~lsym ~rsym ~@targets*]
                           [(assoc row :cols `[~left ~right ~@(:cols row)])])]]])))))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :prd
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [[tag :as node] row]
       (case tag
         :any
         [:pass (compile targets* [row])]

         :prd
         (let [[_ {form :form}] node]
           [:test `(~form ~target)
            (compile targets* [row])])))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :quo
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [[tag :as node] row]
       (case tag
         :any
         [:pass
          (compile targets* [row])]

         :quo
         [:test `(= ~target ~(r.syntax/unparse node))
          (compile targets* [row])]))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :rp*
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [[tag :as node] row]
       (case tag
         :any
         [:pass (compile targets* [row])]

         :rp*
         (let [[_ {items :items}] node
               n (count items)
               ;; Symbol which is bound to the first n elements of
               ;; target at the top of each loop.
               init (gensym "init__")
               ;; Gaurd pattern to check the length of the initial
               ;; slice at the top of each loop.
               init-grd [:grd {:form `(= (count ~init) ~n)}]
               ;; The sequence to match.
               init-cat [:cat items]
               ;; Unbound memory variables must be bound before loop
               ;; execution and added to the compilation environment
               ;; for the internal loop body.
               init-mvrs (keep
                          (fn [[tag :as node]]
                            (when (and (= tag :mvr)
                                       (not (r.matrix/get-var row node)))
                              node))
                          (r.syntax/variables init-cat))
               env* (into (:env row) init-mvrs)
               row* (assoc row :env env*)
               loop-id (gensym "loop__")
               loop-targets (into [target] (map (fn [[_ sym]] sym)) init-mvrs)
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
     (fn [[tag :as node] row]
       (case tag
         :any
         [:pass (compile targets* [row])]

         :rp+
         (let [[_ {dots :dots items :items}] node
               n (count items)
               ;; The minimum number of times the loop must execute.
               m (Integer/parseInt (aget (.split (name dots) "\\.+" 2) 1))
               ;; Symbol which tracks the number of successful iterations.
               iter (gensym "iter__")
               ;; Symbol which is bound to the first n elements of
               ;; target at the top of each loop.
               init (gensym "init__")
               ;; Gaurd pattern to check the length of the initial
               ;; slice at the top of each loop.
               init-grd [:grd {:form `(= (count ~init) ~n)}]
               ;; The sequence to match.
               init-cat [:cat items]
               init-vars (r.syntax/variables init-cat)
               ;; Memory variables must be tracked during loop
               ;; execution and added to the compilation environment
               ;; for the internal loop body.
               init-mvrs (keep
                          (fn [[tag :as node]]
                            (when (= tag :mvr)
                              node))
                          init-vars)
               env* (into (:env row) init-vars)
               row* (assoc row :env env*)
               loop-id (gensym "loop__")
               loop-targets (into [target iter] (map (fn [[_ sym]] sym)) init-mvrs)
               loop-tree [:loop loop-id loop-targets
                          [:bind [init (take-form n target)]
                           [:branch
                            [(compile [init init]
                                      [(assoc row*
                                              :cols [init-grd init-cat]
                                              :rhs [:bind [target (drop-form n target)]
                                                    [:bind [iter `(inc ~iter)]
                                                     [:recur loop-id loop-targets]]])])
                             [:test `(<= ~m ~iter)
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
     (fn [[tag :as node] row]
       (case tag
         :any
         [:pass (compile targets* [row])]

         :rst
         (let [[_ {mvr :mvr}] node
               [_ sym] mvr]
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
     (fn [[tag :as node] row]
       (case tag
         :any
         [:pass (compile targets* [row])]

         :set
         (if (r.syntax/search? node)
           (let [[_ the-set] node
                 n (count the-set)
                 ;; Symbol for the size of target.
                 m-sym (gensym "m__")
                 ;; Symbol for each permutation of target.
                 perm-sym (gensym "perm__")]
             [:test `(set? ~target)
              [:bind [m-sym `(count ~target)]
               [:test `(<= ~n ~m-sym)
                [:search [perm-sym `(r.util/k-combinations ~target ~n)]
                 (compile `[~perm-sym ~@targets*]
                          [(assoc row :cols `[~[:cat (vec the-set)] ~@(:cols row)])])]]]])
           [:test `(set/subset? ~(compile-ground node) ~target)
            (compile targets* [row])])))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :seq
  [_ [target :as targets] matrix]
  (mapv
   (fn [[tag :as node] row]
     (case tag
       :any
       [:pass (compile targets [row])]

       :seq
       (let [[_ prt] node]
         [:test `(seq? ~target)
          (binding [*collection-context* :seq]
            (compile targets [(assoc row :cols `[~prt ~@(:cols row)])]))])))
   (r.matrix/first-column matrix)
   (r.matrix/drop-column matrix)))


(defmethod compile-specialized-matrix :unq
  [_ [target & targets*] matrix]
  (let [targets* (vec targets*)]
    (mapv
     (fn [[tag :as node] row]
       (case tag
         :any
         [:pass (compile targets* [row])]

         :unq
         (let [[_ {expr :expr}] node]
           [:test `(= ~target ~expr)
            (compile targets* [row])])))
     (r.matrix/first-column matrix)
     (r.matrix/drop-column matrix))))


(defmethod compile-specialized-matrix :vec
  [_ [target :as targets] matrix]
  (mapv
   (fn [[tag :as node] row]
     (case tag
       :any
       [:pass (compile targets [row])]

       :vec
       (let [[_ prt] node]
         (if (r.syntax/ground? node)
           [:test `(= ~target ~(compile-ground node))
            (compile targets [row])]
           [:test `(vector? ~target)
            (binding [*collection-context* :vector]
              (compile targets [(assoc row :cols `[~prt ~@(:cols row)])]))]))))
   (r.matrix/first-column matrix)
   (r.matrix/drop-column matrix)))

(defn debug-compile
  {:private true}
  [targets matrix]
  ;; This doesn't print the state accurately.
  (clojure.pprint/print-table
   (mapv
    (fn [row]
      (reduce merge
              (mapv
               (fn [target node]
                 {target (r.syntax/unparse node)})
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
  [tree fail search?]
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
                 ~(emit* body fail search?))))))

      :branch
      (if search?
        (let [[_ arms] node
              arms (remove #{[:fail]} arms)]
          (case (count arms)
            0
            fail

            1
            (emit* (first arms) fail search?)

            ;;else
            `(concat
              ~@(map
                 (fn [arm]
                   (emit* arm fail search?))
                 arms))))
        (let [[_ arms] node]
          (case (count arms)
            0
            fail

            1
            (emit* (first arms) fail search?)

            2
            (emit* (first arms)
                   (emit* (second arms) fail search?)
                   search?)

            ;; else
            (let [fsyms (mapv
                         (fn [_]
                           (gensym "f__"))
                         arms)]
              `(letfn [~@(map
                           (fn [fsym fail arm]
                             `(~fsym []
                               ~(emit* arm fail search?)))
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
                  ~(emit* body fail false))]
           (~ident ~@syms)))

      :load
      (let [[_ ident] node]
        `(~ident))

      :pass
      (let [[_ body] node]
        (emit* body fail search?))

      :save
      (let [[_ ident body1 body2] node
            f-sym (gensym "f__")]
        `(letfn [(~ident [] ~fail)
                 (~f-sym [] ~(emit* body2 fail search?))]
           ~(emit* body1 `(~f-sym) search?)))

      :search
      (let [[_ [sym seq-expr] body] node]
        ;; Assumes action node evaluates to a singleton list.
        `(mapcat
          (fn [~sym]
            ~(emit* body nil true))
          ~seq-expr))

      :recur
      (let [[_ ident syms] node]
        `(~ident ~@syms))

      :test
      (let [[_ test body] node]
        `(if ~test
           ~(emit* body fail search?)
           ~fail)))))


(defn rewrite-bind-unused
  "If a binding is never used, remove it."
  {:private true}
  [[_ [bsym bval] body]]
  (if (some #{bsym} (tree-seq coll? seq body))
    [:bind [bsym bval] body]
    body))


#_
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
        (let [[_ [bsym bval]] node
              [xs ys] (split-with
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
                                     ;; This is potentially dangerous and irresponsible.
                                     (walk/postwalk-replace {bsym bsym*} body))
                                   xs)]])))]
          (recur q-nodes s-nodes))
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
                         (when (s/valid? :meander.match.alpha.tree/fail-node node)
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
                 (if (s/valid? :meander.match.alpha.tree/branch-node x)
                   (rewrite-branch-one-case x)
                   x))
               tree)
        tree* (clojure.walk/prewalk
               (fn [x]
                 (if (s/valid? :meander.match.alpha.tree/branch-node x)
                   (-> x
                       rewrite-branch-splice-branches
                       rewrite-branch-one-fail
                       #_rewrite-branch-shared-bindings
                       rewrite-branch-equal-bindings
                       rewrite-branch-equal-tests)
                   x))
               tree*)]
    (if (= tree tree*)
      tree
      (recur tree*))))


(defn emit
  "Rewrite the decision tree as Clojure code with optimizations."
  [tree fail search?]
  (let [tree* (rewrite-tree tree)]
    (emit* tree* fail search?)))


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
  (fn [[tag] env search?]
    tag)
  :default ::default)


(defmethod check-node ::default
  [node env _]
  [:okay (r.syntax/children node) env])


(defmethod check-node :dsj
  [[_ {terms :terms} :as node] env _]
  (let [term-vars (sequence
                   (map
                    (fn [term]
                      ;; We don't need to account for bound variables.
                      (set/difference (r.syntax/variables term) env)))
                   terms)
        all-vars (reduce set/union #{} term-vars)
        problems (sequence
                  (comp (map vector)
                        (keep
                         (fn [[term term-vars]]
                           (let [absent-vars (set/difference all-vars term-vars)]
                             (when (seq absent-vars)
                               [term (into #{} (map r.syntax/unparse) absent-vars)])))))
                  terms
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
  [[_ {items :items, dots :dots} :as node] env _]
  (let [init-cat [:cat items]
        init-vars (r.syntax/variables init-cat)
        unbound-lvrs (into #{} (comp (filter r.syntax/lvr-node?)
                                     (remove env))
                           init-vars)]
    (cond
      (seq unbound-lvrs)
      [:error [{:message "Zero or more patterns may not have references to unbound logic variables."
                :ex-data {:unbound (into #{} (map r.syntax/unparse) unbound-lvrs)}}]]

      (empty? items)
      (let [dots (if (r.syntax/zero-or-more-symbol? dots)
                   dots
                   '...)]
        [:error
         [{:message (format "Zero or more (%s) is a postfix operator. It must have some value in front of it. (i.e. [1 %s ?x])"
                            dots
                            dots)}]])

      :else
      [:okay (r.syntax/children node) env])))


(defmethod check-node :rp+
  [[_ {items :items n :n} :as node] env _]
  (cond
    (nil? n)
    [:error [{:message "Ambiguous ellipsis. Perhaps you meant the n or more operator (..N) or the zero or more operator (...)?"}]]

    (empty? items)
    [:error [{:message (format "N or more (..N) is a postfix operator. It must have some value in front of it. (i.e. [1 ..%s ?x])"
                               n)}]]

    :else
    [:okay (r.syntax/children node) env]))


(defmethod check-node :lvr
  [node env _]
  [:okay [] (conj env node)])


(defmethod check-node :map
  [node env search?]
  (if search?
    [:okay (r.syntax/children node) env]
    (let [[_ the-map] node
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
    (let [[_ {left :left, right :right}] node]
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
    pattern match; equivalent to (meander.syntax.alpha/variables node)."
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


(s/def :meander.match.alpha/expr
  any?)


(s/def :meander.match.alpha/clause
  (s/cat :pat ::r.syntax/term
         :rhs :meander.match.alpha/expr))


(s/def :meander.match.alpha.match/clauses
  (s/* (s/cat :pat ::r.syntax/term
              :rhs :meander.match.alpha/expr)))


(s/def :meander.match.alpha.match/args
  (s/cat :expr :meander.match.alpha/expr
         :clauses (s/* :meander.match.alpha/clause)))


(s/def :meander.match.alpha.match/data
  (s/keys :req-un [:meander.match.alpha/expr
                   :meander.matrix.alpha/matrix
                   :meander.matrix.alpha.data/final-clause]))


(s/def :meander.match.alpha.match.data/final-clause
  (s/nilable :meander.matrix.alpha/row))


(s/fdef analyze-match-args
  :args (s/cat :match-args :meander.match.alpha.match/args)
  :ret :meander.match.alpha.match/data)

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
  [match-args]
  (let [data (s/conform :meander.match.alpha.match/args match-args)]
    (if (identical? data ::s/invalid)
      (throw (ex-info "Invalid match args"
                      (s/explain-data :meander.match.alpha.match/args match-args)))
      (let [clauses (mapv
                     (fn [clause]
                       (update clause :pat r.syntax/expand-usr-ops))
                     (:clauses data))
            matrix (mapv
                    (fn [{:keys [pat rhs]}]
                      {:cols [pat]
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
                     matrix)
            errors (into [] (keep
                             (fn [{pat :pat}]
                               (check pat false)))
                         clauses)]
        {:errors errors
         :expr (:expr data)
         :exhaustive? (some? final-clause)
         :final-clause final-clause
         :matrix matrix}))))


(s/fdef match
  :args (s/cat :expr any?
               :clauses :meander.match.alpha.match/clauses)
  :ret any?)


(defmacro match
  {:arglists '([x & clauses])
   :style/indent [1]}
  [& match-args]
  (let [match-data (analyze-match-args match-args)
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
            (emit (compile [expr] [final-clause]) nil false)
            `(throw (Exception. "non exhaustive pattern match")))
          `(let [~target ~expr
                 ~fail (fn []
                         ~(if (some? final-clause)
                            (emit (compile [target] [final-clause]) nil false)
                            `(throw (Exception. "non exhaustive pattern match"))))]
             ~(emit (compile [target] matrix) `(~fail) false)))))))


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
  [match-args]
  (let [data (s/conform :meander.match.alpha.match/args match-args)]
    (if (identical? data ::s/invalid)
      (throw (ex-info "Invalid match args"
                      (s/explain-data :meander.match.alpha.match/args match-args)))
      (let [clauses (mapv
                     (fn [clause]
                       (update clause :pat r.syntax/expand-usr-ops))
                     (:clauses data))
            matrix (mapv
                    (fn [{:keys [pat rhs]}]
                      {:cols [pat]
                       :env #{}
                       :rhs [:action `(list ~rhs)]})
                    clauses)
            errors (into [] (keep
                             (fn [{pat :pat}]
                               (check pat true)))
                         clauses)]
        {:errors errors
         :expr (:expr data)
         :matrix matrix}))))


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
   {!xs [1 2 3], !ys []})"
  {:arglists '([x & clauses])
   :style/indent [1]}
  [& match-args]
  (let [match-data (analyze-search-args match-args)
        expr (:expr match-data)
        matrix (:matrix match-data)
        errors (:errors match-data)]
    (if-some [error (first errors)]
      (throw error)
      (let [target (gensym "target__")]
        (if (r.matrix/empty? matrix)
          nil
          `(let [~target ~expr]
             ~(emit (compile [target] matrix) nil true)))))))

(s/fdef search
  :args (s/cat :expr any?
               :clauses :meander.match.alpha.match/clauses)
  :ret any?)
