(ns meander.dev.search
  (:refer-clojure :exclude [compile])
  (:require [clojure.spec.alpha :as s]
            [meander.dev.match :as r.match]
            [meander.dev.matrix :as r.matrix]
            [meander.dev.syntax :as r.syntax]
            [meander.util :as r.util]))


(declare compile)


;; ---------------------------------------------------------------------
;; Matrix utilities


(defn search-and-match-matrices
  "Splits matrix into search and match matrices and stores them in a
  map under the keys :search and :match respectively.

  A search matrix is a pattern matrix such that every pattern in the
  first column is a search pattern (node). A match matrix is pattern
  matrix such that no pattern in the first column is a search
  pattern."
  [matrix]
  (group-by
   (fn [row]
     (if (r.syntax/search? (r.matrix/first-column row))
       :search
       :match))
   matrix))


(defn specialize-by
  "Split matrix into submatrices by the return result of applying f to
  the first column of each row in matrix."
  {:private true}
  [f matrix]
  (group-by (comp f r.matrix/first-column) matrix))


;; ---------------------------------------------------------------------
;; Matrix compilation


(defn compile-specialized-matrix-strategy
  "Dispatch function used by compile-specialized-matrix."
  {:private true}
  [tag _vars _s-matrix _default]
  tag)


(defmulti compile-specialized-matrix
  "Compiles the first column in the specialized matrix s-matrix.

  A specialized matrix is a pattern matrix such that every pattern in
  the first column is a pattern with the same tag."
  {:arglists '([tag vars s-matrix default])
   :private true}
  #'compile-specialized-matrix-strategy)


(defn concat-form
  {:private true}
  [forms]
  (if (< 1 (count forms))
    `(concat ~@forms)
    (first forms)))


(defn compile-match-matrix
  "Compiles each first column of each row in match-matrix as an
  idependent singleton match matrix with the match compiler. The
  right hand side of each row is rewritten as the compilation of
  it's remaining columns wit the search compiler."
  {:arglists '([vars match-matrix default])
   :private true}
  [vars matrix default]
  (concat-form
   (mapv r.match/compile
         (repeat (take 1 vars))
         (mapv
          (fn [row]
            (let [node (r.matrix/first-column row)]
              (vector
               (assoc row
                      :cols [node]
                      :rhs
                      (let [cols* (r.matrix/rest-columns row)]
                        (if (seq cols*)
                          (let [lvars (r.syntax/variables node)
                                row* (if (seq vars)
                                       (reduce r.matrix/add-sym row (map r.syntax/data lvars))
                                       row)
                                row* (assoc row* :cols cols*)]
                            (compile (drop 1 vars) [row*] default))
                          (:rhs row)))))))
          matrix)
         (repeat default))))


(defmethod compile-specialized-matrix :default [_ vars s-matrix default]
  (compile-match-matrix vars s-matrix default))


;; ---------------------------------------------------------------------
;; Seq, Vector


(defn compile-sequential-matrix
  {:private true}
  [vars matrix default]
  (compile vars
           (map
            (fn [row]
              (assoc row
                     :cols (cons (r.syntax/data (r.matrix/first-column row))
                                 (r.matrix/rest-columns row))))
            matrix)
           default))


(defmethod compile-specialized-matrix :seq [_ vars search-matrix default]
  `(if (seq? ~(first vars))
     ~(compile-sequential-matrix vars search-matrix default)))


(defmethod compile-specialized-matrix :vec [_ vars search-matrix default]
  `(if (vector? ~(first vars))
     ~(compile-sequential-matrix vars search-matrix default)))


;; ---------------------------------------------------------------------
;; Part, VPart


(defn compile-part-matrix
  {:private true}
  [vars search-matrix default]
  (let [left-sym (gensym "left__")
        right-sym (gensym "right__")
        vars* (concat [left-sym right-sym] (rest vars))
        {:keys [variable-length invariable-length]}
        (group-by
         (comp {true :variable-length
                false :invariable-length}
               r.syntax/variable-length?
               r.syntax/left-node
               r.matrix/first-column)
         search-matrix)
        forms (mapv
               (fn [[n matrix]]
                 `(let [~left-sym (take ~n ~(first vars))
                        ~right-sym (drop ~n ~(first vars))]
                    ~(compile vars*
                              (map
                               (fn [row]
                                 (let [{:keys [left right]} (r.syntax/data (r.matrix/first-column row))]
                                   (assoc (r.matrix/drop-column row)
                                          :cols (concat [left right] (r.matrix/rest-columns row)))))
                               matrix)
                              default)))
               (group-by
                (comp r.syntax/length
                      r.syntax/left-node
                      r.matrix/first-column)
                invariable-length))
        forms (if (seq variable-length)
                (conj forms
                      `(sequence
                        (mapcat
                         (fn [[~left-sym ~right-sym]]
                           ~(compile vars*
                                     (map
                                      (fn [row]
                                        (let [{:keys [left right]} (r.syntax/data (r.matrix/first-column row))]
                                          (assoc (r.matrix/drop-column row)
                                                 :cols (concat [left right] (r.matrix/rest-columns row)))))
                                      variable-length)
                                     default)))
                        (r.util/partitions 2 ~(first vars))))
                forms)]
    (concat-form forms)))


(defmethod compile-specialized-matrix :part [_ vars s-matrix default]
  (compile-part-matrix vars s-matrix default))


(defmethod compile-specialized-matrix :vpart [_ vars s-matrix default]
  (compile-part-matrix vars s-matrix default))


;; ---------------------------------------------------------------------
;; Cat, VCat


(defn compile-cat-clauses [tag vars s-matrix default]
  (let [forms (mapv
               (fn [[n s-matrix*]]
                 (let [target (first vars)
                       nth-forms (map
                                  (fn [index]
                                    [(gensym (str "nth_" index "__"))
                                     `(nth ~target ~index)])
                                  (range n))
                       nth-vars (map first nth-forms)
                       vars* (concat nth-vars (rest vars))
                       rows* (map
                              (fn [row]
                                (assoc row
                                       :cols (concat
                                              (r.syntax/data (r.matrix/first-column row))
                                              (r.matrix/rest-columns row))))
                              s-matrix*)]
                   (case tag
                     :cat
                     `(if (== ~n (count (take ~n ~target)))
                        (let [~@(mapcat identity nth-forms)]
                          ~(compile vars* rows* default)))

                     :vcat
                     `(if (== ~n (count ~target))
                        (let [~@(mapcat identity nth-forms)]
                          ~(compile vars* rows* default))))))
               (group-by
                (comp r.syntax/cat-length r.matrix/first-column)
                s-matrix))]
    (concat-form forms)))


(defmethod compile-specialized-matrix :cat [tag vars s-matrix default]
  (compile-cat-clauses tag vars s-matrix default))


(defmethod compile-specialized-matrix :vcat [tag vars s-matrix default]
  (compile-cat-clauses tag vars s-matrix default))

;; ---------------------------------------------------------------------
;; Map


(defmethod r.syntax/search? :entry [[_ {:keys [key-pat val-pat]}]]
  (or (boolean (seq (r.syntax/variables key-pat)))
      (r.syntax/search? val-pat)))


(defmethod compile-specialized-matrix :entry
  [_tag vars s-matrix default]
  (compile vars
           (map
            (fn [row]
              (let [[_ {:keys [key-pat val-pat]}] (r.matrix/first-column row)
                    vec-node [:vec
                              [:vpart
                               {:left [:vcat [key-pat val-pat]]
                                :right [:seq-end]}]]]
                (assoc row :cols (cons vec-node (r.matrix/rest-columns row)))))
            s-matrix)
           default))


(defmethod compile-specialized-matrix :map [_tag vars s-matrix default]
  `(if (map? ~(first vars))
     ~(compile vars
               (map
                (fn [row]
                  (let [[_ map-data] (r.matrix/first-column row)
                        entries (into #{}
                                      (map
                                       (fn [[k v]]
                                         [:entry {:key-pat k
                                                  :val-pat v}]))
                                      map-data)]
                    ;; Let :set do the lifting for now.
                    (assoc row :cols (cons [:set-no-check entries] (r.matrix/rest-columns row)))))
                s-matrix)
               default)
     nil))

;; ---------------------------------------------------------------------
;; Set


(defn compile-set-matrix
  {:private true}
  [vars s-matrix default]
  (let [target (first vars)
        perm-sym (gensym "perm__")]
    (concat-form
     (map
      (fn [[n s-matrix*]]
        (let [elem-syms (mapv gensym (repeat n "elem__"))
              vars* (concat elem-syms (rest vars))]
          `(if (<= ~n (count ~target))
             (sequence
              (mapcat
               (fn [~perm-sym]
                 (let [~elem-syms ~perm-sym]
                   ~(compile vars*
                             (map
                              (fn [row]
                                (assoc row :cols (concat (r.syntax/data (r.matrix/first-column row))
                                                         (r.matrix/rest-columns row))))
                              s-matrix*)
                             default))))
              (r.util/permutations ~target)))))
      (specialize-by r.syntax/length s-matrix)))))


(defmethod compile-specialized-matrix :set-no-check
  [_tag vars s-matrix default]
  (compile-set-matrix vars s-matrix default))


(defmethod r.syntax/search? :set-no-check [[_ elems]]
  (r.syntax/search? [:set elems]))


(defmethod r.syntax/length :set-no-check [[_ elems]]
  (r.syntax/length [:set elems]))


(defmethod compile-specialized-matrix :set [_tag vars s-matrix default]
  `(if (set? ~(first vars))
     ~(compile-set-matrix vars s-matrix default)
     nil))


;; ---------------------------------------------------------------------
;; Search macro

;; TODO: Prioritize match columns over search columns.
(defn compile
  {:private true}
  [vars matrix default]
  (if (some? (r.matrix/first-column (first matrix)))
    (let [matrices (search-and-match-matrices matrix)
          search-matrix (:search matrices)
          match-matrix (:match matrices)]
      (concat-form
       (cond-> []
         search-matrix
         (into (mapv
                (fn [[tag s-matrix]]
                  (compile-specialized-matrix tag vars s-matrix default))
                (group-by
                 (comp r.syntax/tag r.matrix/first-column)
                 search-matrix)))

         match-matrix
         (conj (compile-match-matrix vars match-matrix default)))))
    default))


(s/fdef search
  :args ::r.match/match-args
  :ret any?)


(defn parse-search-args
  {:private true}
  [match-args]
  (s/conform ::r.match/match-args match-args))


(defn clauses->matrix
  {:private true}
  [clauses]
  (into []
        (map
         (fn [{:keys [pat rhs] :as clause}]
           (let [check-if-bound-vars
                 (into #{}
                       (comp
                        (filter r.syntax/rep-node?)
                        (mapcat r.syntax/variables)
                        (filter r.syntax/var-node?)
                        (map r.syntax/data))
                       (tree-seq coll? seq (:pat clause)))
                 rhs (if (seq check-if-bound-vars)
                       `(if (contains? (hash-set ~@check-if-bound-vars) ::r.match/unbound)
                          nil
                          (list ~rhs))
                       `(list ~rhs))]
             {:cols [pat]
              :env #{}
              :rhs rhs})))
        clauses))


(defmacro search
  "Like meander.dev.match/match macro extended to permit \"search
  patterns\" and returns a lazy sequence of all resulting actions.

  Search patterns are like match patterns but differ in that they
  express patterns which may have multiple matches when applied. A map
  pattern with variable keys, a set pattern with non-ground elements,
  and a sequence containing neighboring variable subsequence patterns,
  are all examples of such patterns."
  {:arglists '([target & pattern action ...])
   :style/indent :defn}
  [& search-args]
  (let [{:keys [target clauses]} (parse-search-args search-args)
        target-sym (gensym "target__")
        vars [target-sym]
        matrix (clauses->matrix clauses)]
    `(let [~target-sym ~target]
       ~(compile vars matrix nil))))
