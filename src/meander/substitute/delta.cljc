(ns meander.substitute.delta
  #?(:cljs (:require-macros [meander.substitute.delta]))
  (:require [clojure.set :as set]
            [clojure.spec.alpha :as s]
            [clojure.walk :as walk]
            [meander.match.delta :as r.match]
            [meander.syntax.delta :as r.syntax]
            [meander.util.delta :as r.util]))


(defn make-env
  "Derives a map of

  {:collection-context nil
   :mvr-refs {mvr mvr-ref-sym}
   :wth-refs {ref pattern}}

  from the pattern node where mvr is a memory variable node, and
  mvr-ref-sym is a memory varable symbol with the suffix
  _ref__<digits> e.g. !xs_ref__234."
  {:private true}
  [pat]
  {:collection-context nil
   :wth-refs {}
   :mvr-refs (into {} (map
                       (fn [mvr-node]
                         [mvr-node (gensym (str (:symbol mvr-node) "_ref__"))]))
                   (r.syntax/memory-variables pat))})


(defn get-mvr-ref-sym
  "Gets the reference symbol for mvr-sym from env. Throws if
  the symbol is not found (sanity check)."
  {:private true}
  [env mvr-node]
  (if-some [[_ mvr-ref-sym] (find (:mvr-refs env) mvr-node)]
    mvr-ref-sym
    (throw
     (ex-info (str "No state symbol found for memory variable " (:symbol mvr-node))
              {:env env
               :mvr mvr-node}))))

(defn get-wth-refs
  {:private true}
  [env]
  (get env :wth-refs))

(defn get-wth-ref-pattern
  {:private true}
  [env ref-node]
  (get-in env [:wth-refs ref-node]))

(defn add-wth-refs
  {:private true}
  [env ref-map]
  (update env :wth-refs merge ref-map))

(defn compile-ground
  {:private true}
  [x]
  (cond
    (symbol? x)
    `(quote ~x)

    (seq? x)
    (if (= (first x) 'quote)
      x
      (if (= (first x) `list)
        (cons (first x) (map compile-ground (rest x)))
        (if (seq x)
          (cons `list (map compile-ground x))
          ())))

    (map? x)
    (into {}
          (map
           (fn [[k v]]
             [(compile-ground k) (compile-ground v)]))
          x)

    (coll? x)
    (into (empty x) (map compile-ground) x)

    :else
    x))


(defn compile-substitute-dispatch
  [node env]
  (r.syntax/tag node))


(defmulti compile-substitute
  {:arglists '([node env])}
  #'compile-substitute-dispatch)


(defmethod compile-substitute :app
  [node env]
  `(list '~'app
         '~(:fn-expr node)
         ~@(map compile-substitute (:arguments node) (repeat env))))


(defmethod compile-substitute :cat
  [node env]
  (let [elements (:elements node)]
    (if (some #{:uns} (map r.syntax/tag elements))
      `(concat ~@(sequence (map
                            (fn [node env]
                              (let [form (compile-substitute node env)]
                                (if (= :uns (r.syntax/tag node))
                                  form
                                  `(list ~form)))))
                           elements
                           (repeat env)))
      `[~@(sequence (map compile-substitute) elements (repeat env))])))


(defmethod compile-substitute :cnj
  [node env]
  `(list '~'and ~@(map compile-substitute (:arguments node) (repeat env))))


(defmethod compile-substitute :ctn
  [node env]
  (let [pattern (:pattern node)]
    (if-some [context (:context node)]
      `(~(compile-substitute context env) ~(compile-substitute pattern env))
      (compile-substitute pattern env))))


(defmethod compile-substitute :drp
  [_ _]
  `(list))


(defmethod compile-substitute :dsj
  [node env]
  `(list '~'or  ~@(map compile-substitute (:arguments node) (repeat env))))


(defmethod compile-substitute :lit
  [node env]
  (compile-ground (:value node)))


(defmethod compile-substitute :lvr
  [node env]
  (:symbol node))

(defmethod compile-substitute :map [node env]
  (let [as (:as node)
        rest-map (:rest-map node)
        compiled-kvs (sequence
                      (comp (mapcat identity)
                            (map (fn [x]
                                   (compile-substitute x env))))
                      (:map node))]
    (if-some [rest-map (:rest-map node)]
      (let [compiled-rest-map `(let [x# ~(compile-substitute rest-map env)]
                                 (if (map? x#)
                                   x#
                                   (into {} x#)))]
        (if (seq compiled-kvs)
          `(assoc ~compiled-rest-map
                  ;; Specified keys have a higher precedence than the rest
                  ;; map. When matching, we dissoc values to derive the
                  ;; rest map. When substituting we assoc values into the
                  ;; rest map.
                  ~@(if (some? as)
                      [:as (compile-substitute as env)])
                  ~@compiled-kvs)
          compiled-rest-map))
      `(hash-map ~@compiled-kvs
                 ~@(if (some? as)
                     [:as (compile-substitute as env)])))))


(defmethod compile-substitute :mvr [mvr env]
  (let [mvr-ref-sym (get-mvr-ref-sym env mvr)]
    (let [item (gensym "item__")]
      `(when-some [[_# ~item] (find (deref ~mvr-ref-sym) 0)]
         (vswap! ~mvr-ref-sym subvec 1)
         ~item))))


(defmethod compile-substitute :not
  [node env]
  `(list '~'not ~(compile-substitute (:argument node) env)))


(defmethod compile-substitute :prd
  [node env]
  `(list '~'pred '~(:form node)
         ~@(map compile-substitute (:arguments node) (repeat env))))


(defmethod compile-substitute :prt
  [node env]
  (r.match/match node
    {:left ?left
     :right {:tag :cat
             :elements (or [] ())}}
    (compile-substitute ?left env)

    {:left ?left
     :right ?right}
    `(concat ~(compile-substitute ?left env)
             ~(compile-substitute ?right env))))


(defmethod compile-substitute :quo
  [node env]
  `(quote ~(:form node)))


(defmethod compile-substitute :rp* [node env]
  (let [cat-node (:cat node)
        mvrs (r.syntax/memory-variables
              (r.syntax/substitute-refs node (get-wth-refs env)))]
    (if (seq mvrs)
      ;; If there are mem-vars, loop until one of them is
      ;; exhausted.
      `(let [ret# (transient [])]
         (loop []
           (if (and ~@(map
                       (fn [mvr]
                         `(seq (deref ~(get-mvr-ref-sym env mvr))))
                       mvrs))
             (do
               (run! (fn [x#] (conj! ret# x#)) ~(compile-substitute cat-node env))
               (recur))
             (persistent! ret#))))
      (throw (ex-info "No memory variables found for operator (...)"
                      {:node (r.syntax/unparse node)
                       :env env})))))

(defmethod compile-substitute :rp+ [node env]
  (r.match/match node
    {:cat {:tag :cat
           :elements (or ({:tag :lit} ..1 :as ?elements)
                         [{:tag :lit} ..1 :as ?elements])}
     :n ?n}
    (into [] cat (repeat ?n (map compile-substitute ?elements (repeat env))))

    {:cat ?cat
     :n ?n}
    ;; Yield n substitutions.
    `(into [] cat (repeatedly ~?n (fn [] ~(compile-substitute ?cat env))))))


(defmethod compile-substitute :rst [node env]
  (let [ref-sym (get-mvr-ref-sym env (:mvr node))]
    `(let [xs# (deref ~ref-sym)]
       (vreset! ~ref-sym [])
       xs#)))

(defmethod compile-substitute :set [node env]
  `(set/union
    (hash-set
     ~@(map
        (fn [x]
          (compile-substitute x env))
        (:elements node)))
    ~@(if-some [as (:as node)]
        [(compile-substitute as env)])
    ~@(if-some [rest (:rest node)]
        [(compile-substitute rest env)])))


(defmethod compile-substitute :seq
  [node env]
  (let [env* (assoc env :collection-context :seq)]
    (r.match/find node
      {:prt {:left {:tag :cat
                    :elements (and ?left-elements (not (scan {:tag :uns})))}
             :right {:tag :cat
                     :elements (and ?right-elements (not (scan {:tag :uns})))}}}
      `(list ~@(map compile-substitute ?left-elements (repeat env*))
             ~@(map compile-substitute ?right-elements (repeat env*)))

      {:prt {:left {:tag :cat
                    :elements (and ?left-elements (not (scan {:tag :uns})))}
             :right {:tag :lit
                     :value (_ ... :as ?right-elements)}}}
      `(list ~@(map compile-substitute ?left-elements (repeat env*))
             ~@?right-elements)

      {:prt {:left ?left
             :right ?right
             :as ?prt}}
      (if (and (r.syntax/variable-length? ?left)
               (r.syntax/variable-length? ?right))
        `(or (seq ~(compile-substitute ?prt env*)) (list))
        (compile-substitute ?prt env*)))))


(defmethod compile-substitute :unq
  [node env]
  (:expr node))


(defmethod compile-substitute :uns
  [node env]
  (:expr node))


(defmethod compile-substitute :vec
  [node env]
  (let [env* (assoc env :collection-context :vector)]
    (r.match/find node
      {:prt {:left {:tag :cat
                    :elements (and ?left-elements (not (scan {:tag :uns})))}
             :right {:tag :cat
                     :elements (and ?right-elements (not (scan {:tag :uns})))}}}
      `[~@(map compile-substitute ?left-elements (repeat env*))
        ~@(map compile-substitute ?right-elements (repeat env*))]

      {:prt {:left {:tag :cat
                    :elements (and ?left-elements (not (scan {:tag :uns})))}
             :right {:tag :lit
                     :value (_ ... :as ?right-elements)}}}
      `[~@(map compile-substitute ?left-elements (repeat env*))
        ~@?right-elements]

      _
      `(vec ~(compile-substitute (:prt node) env*)))))


(defmethod compile-substitute :ref
  [node env]
  `(~(:symbol node)))


(defmethod compile-substitute :wth
  [node env]
  (let [ref-map (r.syntax/make-ref-map node)
        env* (add-wth-refs env ref-map)
        refs (into (r.syntax/references node)
                   (comp (map :pattern)
                         (mapcat r.syntax/references))
                   (:bindings node))
        bindings (filter
                  (fn [binding]
                    (contains? refs (:ref binding)))
                  (:bindings node))]
    `(letfn [~@(map
                 (fn [binding]
                   `(~(:symbol (:ref binding)) []
                     ~(compile-substitute (:pattern binding) env*)))
                 bindings)]
       ~(compile-substitute (:body node) env*))))


(defn rewrite-partitions
  {:private true}
  [node]
  (r.syntax/prewalk
   (fn [node]
     (r.match/find node
       (with [%right (not (or () []))]
         {:tag :prt
          :left {:tag :cat
                 :elements ?ls}
          :right {:tag :cat
                  :elements (and %right ?rs)}
          :as ?prt})
       {:tag :prt
        :left {:tag :cat
               :elements (concat ?ls ?rs)}
        :right {:tag :cat
                :elements []}}

       {:tag :prt
        :left {:tag :cat
               :elements ?elements1}
        :right {:tag :prt
                :left {:tag :cat
                       :elements ?elements2}
                :right ?right}}
       {:tag :prt
        :left {:tag :cat
               :elements (concat ?elements1 ?elements2)}
        :right ?right}
       _
       node))
   node))


(defn rewrite-coerce-literals-to-lit
  {:private true}
  [node]
  (r.syntax/prewalk
   (fn [node]
     (if (and (r.syntax/literal? node)
              (not= (r.syntax/tag node) :cat))
       {:tag :lit
        :value (r.syntax/unparse node)}
       node))
   node))


(defn rewrite-node
  {:private true}
  [node]
  (-> node
      r.syntax/rename-refs
      rewrite-partitions
      rewrite-coerce-literals-to-lit))


(defn parse-rewrite
  {:private true}
  ([x]
   (parse-rewrite x))
  ([x env]
   (rewrite-node (r.syntax/parse x env))))


(defmacro substitute
  [x]
  (let [node (parse-rewrite x &env) 
        env (make-env node)
        mvr-ref-bindings (mapcat
                          (fn [[mvr-node ref-sym]]
                            [ref-sym `(volatile! ~(:symbol mvr-node))])
                          (:mvr-refs env))
        compiled (compile-substitute node env)]
    (if (seq mvr-ref-bindings)
      `(let [~@mvr-ref-bindings]
         ~compiled)
      compiled)))

(s/fdef substitute
  :args (s/cat :term any?)
  :ret any?)

#_
(comment
  (let [!tags [1 2 3]]
    (substitute
     (with [%h1 [!tags . %h1 ...]]
       %h1)))

  (let [!xs [11 12 14]
        ?y 12
        ?z 13]
    (substitute
     (with [%foo [%bar ..3]
            %bar [!xs ?z]]
       %foo)))
  ;; =>
  [[11 13] [12 13] [14 13]])
