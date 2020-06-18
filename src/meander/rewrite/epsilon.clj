(ns ^:no-doc meander.rewrite.epsilon
  (:require
   [meander.match.epsilon :as m.match]
   [meander.match.syntax.epsilon :as m.match.syntax]
   [meander.match.runtime.epsilon :as m.match.runtime]
   [meander.substitute.epsilon :as m.subst]
   [meander.substitute.syntax.epsilon :as m.subst.syntax]
   [meander.syntax.epsilon :as m.syntax]))

;; rewrite compilation
;; -------------------

(defn analyze-rewrite-args
  {:arglists '([[target & rewrite-clauses] env])
   :private true}
  [rewrite-args env]
  (let [find-analysis (m.match/analyze-find-args rewrite-args env)
        parse-subst (fn [form]
                      (m.subst.syntax/parse form env))]
    (m.match/match find-analysis
      {:errors [?error & _]}
      ?error

      {:cata-symbol ?cata-symbol
       :contains-cata? ?match-cata?
       :matrix [{:rhs {:value (m.match.syntax/apply parse-subst !subst-asts)}} ...
                :as ?matrix]
       :final-clause ?final-clause}
      (let [subst-cata? (boolean (some m.subst.syntax/contains-cata-node? !subst-asts))
            matrix (mapv
                    (fn [column subst-ast]
                      (assoc column :rhs subst-ast))
                    ?matrix
                    !subst-asts)
            final-clause (if (some? ?final-clause)
                           (let [ir (get ?final-clause :rhs)
                                 subst-form (get ir :value)
                                 subst-node (parse-subst subst-form)]
                             (assoc ?final-clause :rhs subst-node)))
            matrix (if (some? final-clause)
                     (conj matrix final-clause)
                     matrix)
            analysis (merge find-analysis
                            {:contains-cata? (or ?match-cata? subst-cata?)
                             :final-clause nil
                             :match-cata? ?match-cata?
                             :matrix matrix
                             :subst-cata? subst-cata?})]
        analysis))))

(defn compile-rewrite-args
  {:arglists '([[target & rewrite-clauses] env])}
  [rewrite-args env]
  (let [rewrite-analysis (analyze-rewrite-args rewrite-args env)]
    (m.match/match rewrite-analysis
      {:cata-symbol ?cata-symbol
       :match-cata? ?match-cata?
       :matrix ?matrix
       :subst-cata? ?subst-cata?}
      (let [subst-env (merge env {:cata-symbol ?cata-symbol
                                  :match-cata? ?match-cata?
                                  :subst-cata? ?subst-cata?})
            find-matrix (mapv
                         (fn [column]
                           (let [subst-node (get column :rhs)
                                 value (m.subst/compile subst-node subst-env)
                                 ir {:op :return, :value value}]
                             (assoc column :rhs ir)))
                         ?matrix)
            find-analysis (assoc rewrite-analysis :matrix find-matrix)]
        (m.match/compile-find-analysis find-analysis env))

      ?error
      ?error)))

;; rewrites compilation
;; --------------------

(defn analyze-rewrites-args
  {:arglists '([[target & rewrite-clauses] env])
   :private true}
  [rewrite-args env]
  (let [search-analysis (m.match/analyze-search-args rewrite-args env)
        parse-subst (fn [form]
                      (m.subst.syntax/parse form env))]
    (m.match/match search-analysis
      {:errors [?error & _]}
      ?error

      {:cata-symbol ?cata-symbol
       :contains-cata? ?match-cata?
       :matrix [{:rhs {:value (m.match.syntax/apply parse-subst !subst-asts)}} ...
                :as ?matrix]}
      (let [subst-cata? (boolean (some m.subst.syntax/contains-cata-node? !subst-asts))
            matrix (mapv
                    (fn [column subst-ast]
                      (assoc column :rhs subst-ast))
                    ?matrix
                    !subst-asts)
            analysis (merge search-analysis
                            {:contains-cata? (or ?match-cata? subst-cata?)
                             :match-cata? ?match-cata?
                             :matrix matrix
                             :subst-cata? subst-cata?})]
        analysis))))

(defn compile-rewrites-args
  {:arglists '([[target & rewrite-clauses] env])}
  [rewrite-args env]
  (let [rewrites-analysis (analyze-rewrites-args rewrite-args env)]
    (m.match/match rewrites-analysis
      {:cata-symbol ?cata-symbol
       :match-cata? ?match-cata?
       :matrix ?matrix
       :subst-cata? ?subst-cata?}
      (if ?subst-cata? 
        ::CATA_NOT_IMPLEMENTED
        (let [subst-env (merge env {:cata-symbol ?cata-symbol
                                    :match-cata? ?match-cata?
                                    :subst-cata? ?subst-cata?})
              search-matrix (mapv
                             (fn [column]
                               (let [subst-ast (get column :rhs)
                                     value (m.subst/compile subst-ast subst-env)
                                     ir {:op :return, :value value}]
                                 (assoc column :rhs ir)))
                             ?matrix)
              search-analysis (assoc rewrites-analysis :matrix search-matrix)]
          (m.match/compile-search-analysis search-analysis env)))

      ?error
      ?error)))
