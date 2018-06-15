(ns meander.dev.search
  (:refer-clojure :exclude [compile])
  (:require [clojure.spec.alpha :as s]
            [meander.dev.match :as r.match]
            [meander.dev.syntax :as r.syntax]
            [meander.util :as r.util]))

(declare compile)

;; ---------------------------------------------------------------------
;; Matrix

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
     (if (r.syntax/search? (r.match/first-column row))
       :search
       :match))
   matrix))


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
            (vector
             (assoc row
                    :cols [(r.match/first-column row)]
                    :rhs
                    (let [cols* (r.match/rest-columns row)]
                      (if (seq cols*)
                        (compile (drop 1 vars)
                                 [(assoc row :cols cols*)]
                                 default)
                        (:rhs row))))))
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
                     :cols (cons (r.syntax/data (r.match/first-column row))
                                 (r.match/rest-columns row))))
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
               r.match/first-column)
         search-matrix)
        forms (mapv
               (fn [[n matrix]]
                 `(let [~left-sym (take ~n ~(first vars))
                        ~right-sym (drop ~n ~(first vars))]
                    ~(compile vars*
                              (map
                               (fn [row]
                                 (let [{:keys [left right]} (r.syntax/data (r.match/first-column row))]
                                   (assoc (r.match/drop-column row)
                                          :cols (concat [left right] (r.match/rest-columns row)))))
                               matrix)
                              default)))
               (group-by
                (comp r.syntax/length
                      r.syntax/left-node
                      r.match/first-column)
                invariable-length))
        forms (if (seq variable-length)
                (conj forms
                      `(sequence
                        (mapcat
                         (fn [[~left-sym ~right-sym]]
                           ~(compile vars*
                                     (map
                                      (fn [row]
                                        (let [{:keys [left right]} (r.syntax/data (r.match/first-column row))]
                                          (assoc (r.match/drop-column row)
                                                 :cols (concat [left right] (r.match/rest-columns row)))))
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
                                              (r.syntax/data (r.match/first-column row))
                                              (r.match/rest-columns row))))
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
                (comp r.syntax/cat-length r.match/first-column)
                s-matrix))]
    (concat-form forms)))


(defmethod compile-specialized-matrix :cat [tag vars s-matrix default]
  (compile-cat-clauses tag vars s-matrix default))


(defmethod compile-specialized-matrix :vcat [tag vars s-matrix default]
  (compile-cat-clauses tag vars s-matrix default))


(defn compile
  {:private true}
  [vars matrix default]
  (if (some? (r.match/first-column (first matrix)))
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
                 (comp r.syntax/tag r.match/first-column)
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
