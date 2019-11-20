(ns meander.rewrite.epsilon
  (:require
   [meander.match.epsilon :as m.match]
   [meander.match.syntax.epsilon :as m.match.syntax]
   [meander.match.runtime.epsilon :as m.match.runtime]
   [meander.substitute.epsilon :as m.subst]
   [meander.substitute.syntax.epsilon :as m.subst.syntax]
   [meander.syntax.epsilon :as m.syntax]))

(defn find-cata-entry
  {:private true}
  [cata-index cata-node]
  (some (fn [[other-cata-node :as cata-entry]]
          (if (= other-cata-node cata-node)
            cata-entry))
        cata-index))

(defn compile-rewrite-subst
  {:private true}
  [node env]
  (let [env (merge env (m.subst/make-env node))
        ;; Find all the cata nodes
        cata-nodes (m.subst.syntax/cata-nodes node)
        ;; Expand them.
        cata-nodes (map m.subst.syntax/expand-ast cata-nodes)
        ;; Compile them.
        [cata-forms env] (m.subst/compile-all* cata-nodes env)
        ;; Build an index mapping each unique cata node to a binding
        ;; (a `simple-symbol?`), its compiled form, and an `:unq`
        ;; node. The binding will be used to bind the result of the
        ;; cata function call (which we get by compiling the cata
        ;; node).  Each cata node will be replaced by its
        ;; corresponding `:unq` node which references the binding. 
        cata-index (m.match/search (map vector cata-nodes cata-forms)
                     (_ ... [?node ?form] . _ ...)
                     (let [binding (gensym "X__")]
                       [?node {:binding binding
                               :form ?form
                               :unquote {:tag :unq
                                         :expr binding}}]))
        ;; Replace cata nodes with unquote nodes.
        node* (if (seq cata-nodes)
                (m.syntax/postwalk
                 (fn [x]
                   (m.match/match x
                     {:tag ::m.subst.syntax/cata
                      :as ?node}
                     (m.match/match (find-cata-entry cata-index ?node)
                       [_ {:unquote ?unquote}]
                       ?unquote

                       _
                       ?node)

                     ?x
                     ?x))
                 node)
                node)
        inner-form (m.subst/compile node* env)]
    (case [(get env :match-cata?) (get env :subst-cata?)]
      [false false]
      inner-form

      [false true]
      (reduce
       (fn [inner-form [_ data]]
         (m.match/match data 
           {:binding ?binding
            :form ?form}
           `(let [x# ~?form]
              (if (m.match.runtime/fail? x#)
                x#
                (let [~?binding (nth x# 0)]
                  ~inner-form)))))
       [inner-form]
       cata-index)

      [true true]
      (reduce
       (fn [inner-form [_ data]]
         (m.match/match data 
           {:binding ?binding
            :form ?form}
           `(let [x# ~?form]
              (if (m.match.runtime/fail? x#)
                x#
                (let [~?binding (nth x# 0)]
                  ~inner-form)))))
       inner-form
       cata-index)

      [true false]
      inner-form)))

(defn compile-rewrite-args
  [rewrite-args env]
  (let [find-analysis (m.match/analyze-find-args rewrite-args env)]
    (m.match/match find-analysis
      {:errors [?error & _]}
      ?error

      {:cata-symbol ?cata-symbol
       :contains-cata? ?match-cata?
       :matrix [{:rhs {:value !subst}} ... :as ?matrix]}
      (let [!subst-asts (mapv m.subst.syntax/parse !subst (repeat env))
            subst-cata? (or (some m.subst.syntax/contains-cata-node? !subst-asts)
                            ?match-cata?)
            rewrite-analysis (assoc find-analysis :contains-cata? (or ?match-cata? subst-cata?))
            subst-env (merge env {:subst-cata? subst-cata?
                                  :match-cata? ?match-cata?
                                  :cata-symbol ?cata-symbol})
            rewrite-matrix (mapv
                            (fn [column subst-ast]
                              (assoc-in column [:rhs :value] (compile-rewrite-subst subst-ast subst-env)))
                            ?matrix
                            !subst-asts)
            rewrite-analysis (merge rewrite-analysis {:matrix rewrite-matrix})]
        (m.match/compile-find-analysis rewrite-analysis env)))))

(defn compile-rewrites-subst
  {:private true}
  [node env]
  (let [env (merge env (m.subst/make-env node))
        cata-nodes (m.subst.syntax/cata-nodes node)
        cata-nodes (map m.subst.syntax/expand-ast cata-nodes)
        [cata-forms env] (m.subst/compile-all* cata-nodes env)
        cata-index (m.match/search (map vector cata-nodes cata-forms)
                     (_ ... [?node ?form] . _ ...)
                     (let [binding (gensym "X__")]
                       [?node {:binding binding
                               :form ?form
                               :unquote {:tag :unq
                                         :expr binding}}]))
        node* (if (seq cata-nodes)
                (m.syntax/postwalk
                 (fn [x]
                   (m.match/match x
                     {:tag ::m.subst.syntax/cata
                      :as ?node}
                     (m.match/match (find-cata-entry cata-index ?node)
                       [_ {:unquote ?unquote}]
                       ?unquote

                       _
                       ?node)

                     ?x
                     ?x))
                 node)
                node)
        inner-form (m.subst/compile node* env)]
    (case [(get env :match-cata?) (get env :subst-cata?)]
      [false false]
      inner-form
      
      [false true]
      (reduce
       (fn [inner-form [_ data]]
         (m.match/match data 
           {:binding ?binding
            :form ?form}
           `(mapcat
             (fn [x#]
               (if (m.match.runtime/fail? x#)
                 nil
                 (let [~?binding (nth x# 0)]
                   ~inner-form)))
             ~?form)))
       [inner-form]
       cata-index)
      
      [true true]
      (reduce
       (fn [inner-form [_ data]]
         (m.match/match data 
           {:binding ?binding
            :form ?form}
           `(mapcat
             (fn [x#]
               (if (m.match.runtime/fail? x#)
                 nil
                 (let [~?binding (nth x# 0)]
                   ~inner-form)))
             ~?form)))
       inner-form
       cata-index)

      [true false]
      inner-form)))


(defn compile-rewrites-args
  [rewrite-args env]
  (let [search-analysis (m.match/analyze-search-args rewrite-args env)]
    (m.match/match search-analysis
      {:errors [?error & _]}
      ?error

      {:cata-symbol ?cata-symbol
       :contains-cata? ?match-cata?
       :matrix [{:rhs {:value !subst}} ... :as ?matrix]}
      (let [!subst-asts (mapv m.subst.syntax/parse !subst (repeat env))
            subst-cata? (or (some m.subst.syntax/contains-cata-node? !subst-asts)
                            ?match-cata?)
            rewrite-analysis (assoc search-analysis :contains-cata? (or ?match-cata? subst-cata?))
            subst-env (merge env {:subst-cata? subst-cata?
                                  :match-cata? ?match-cata?
                                  :cata-symbol ?cata-symbol})
            rewrite-matrix (mapv
                            (fn [column subst-ast]
                              (assoc-in column [:rhs :value] (compile-rewrites-subst subst-ast subst-env)))
                            ?matrix
                            !subst-asts)
            rewrite-analysis (merge rewrite-analysis {:matrix rewrite-matrix})]
        (m.match/compile-search-analysis rewrite-analysis env)))))
