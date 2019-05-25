(ns meander.substitute.delta
  #?(:cljs (:require-macros [meander.substitute.delta]))
  (:require [clojure.set :as set]
            [clojure.spec.alpha :as s]
            [clojure.walk :as walk]
            [meander.match.delta :as r.match]
            [meander.syntax.delta :as r.syntax]
            [meander.util.delta :as r.util]))

;; ---------------------------------------------------------------------
;; Rewrite helpers

(defn rewrite-partition
  {:private true}
  [node]
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
            :elements (vec (concat ?ls ?rs))}
     :right {:tag :cat
             :elements []}}

    {:tag :prt
     :left {:tag :cat
            :elements ?elements1}
     :right {:tag :prt
             :left {:tag :cat
                    :elements ?elements2}
             :right ?right}}
    (rewrite-partition
     {:tag :prt
      :left {:tag :cat
             :elements (vec (concat ?elements1 ?elements2))}
      :right ?right})

    {:tag :prt
     :left {:tag :prt,
            :left ?left,
            :right {:tag :cat
                    :elements ?elements-1}},
     :right {:tag :cat
             :elements ?elements-2}}
    (rewrite-partition
     {:tag :prt
      :left ?left
      :right {:tag :cat
              :elements (vec (concat ?elements-1 ?elements-2))}})

    _
    node))

(defn rewrite-partitions
  {:private true}
  [node]
  (r.syntax/prewalk rewrite-partition node))


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


(defn parse-and-rewrite
  {:private true}
  ([x]
   (parse-and-rewrite x {}))
  ([x env]
   (rewrite-node (r.syntax/parse x env))))

;; ---------------------------------------------------------------------
;; Environment

(defn make-env
  "Derives a map of

  {:collection-context nil
   :mvr-refs {mvr mvr-ref-sym}
   :wth-refs {ref pattern}}

  from the pattern node where mvr is a memory variable node, and
  mvr-ref-sym is a memory varable symbol with the suffix
  _idx__<digits> e.g. !xs_idx__234. The mvr-ref-sym will be bound to
  a volatile interger."
  {:private true}
  [pat]
  {:collection-context nil
   :wth-refs {}
   :mvr-refs (into {} (map
                       (fn [mvr-node]
                         [mvr-node (gensym (str (:symbol mvr-node) "_idx__"))]))
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


;; ---------------------------------------------------------------------
;; Compilation


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


(defn compile-all [nodes env]
  (map compile-substitute nodes (repeat env)))


(defmethod compile-substitute :app
  [node env]
  `(list '~'app
         '~(:fn-expr node)
         ~@(map compile-substitute (:arguments node) (repeat env))))


(defmethod compile-substitute :cat
  [node env]
  (r.match/find node
    {:elements (_ ... {:tag :uns} . _ ... :as ?elements)}
    `(concat ~@(sequence
                (map
                 (fn [node env]
                   (let [form (compile-substitute node env)]
                     (r.match/find node
                       {:tag :uns}
                       form

                       _
                       `(list ~form)))))
                ?elements
                (repeat env)))

    {:elements ?elements}
    `[~@(sequence (map compile-substitute) ?elements (repeat env))]))


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
      `(hash-map ~@(concat (if (some? as)
                             [:as (compile-substitute as env)])
                           compiled-kvs)))))


(defmethod compile-substitute :mvr [mvr env]
  (let [mvr-ref-sym (get-mvr-ref-sym env mvr)]
    (let [item (gensym "item__")]
      `(let [~item (nth ~(:symbol mvr) (deref ~mvr-ref-sym) nil)]
         (vswap! ~mvr-ref-sym inc)
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
                         `(find ~(:symbol mvr) (deref ~(get-mvr-ref-sym env mvr))))
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
  (r.match/find [node env]
    [{:mvr {:symbol ?symbol :as ?mvr}}
     {:mvr-refs {?mvr ?ref-sym}}]
    `(let [xs# (subvec ~?symbol (min (deref ~?ref-sym) (dec (count ~?symbol))))]
       (vreset! ~?ref-sym (count ~?symbol))
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
      `(list ~@(compile-all ?left-elements env*)
             ~@(compile-all ?right-elements env*))

      {:prt {:left {:tag :cat
                    :elements (and ?left-elements (not (scan {:tag :uns})))}
             :right {:tag :lit
                     :value (_ ... :as ?right-elements)}}}
      `(list ~@(compile-all ?left-elements env*)
             ~@?right-elements)

      ;; Default
      {:prt {:left ?left
             :right ?right
             :as ?prt}}
      (if (or (r.syntax/variable-length? ?left)
              (r.syntax/variable-length? ?right))
        `(or (seq ~(compile-substitute ?prt env*)) (list))
        (compile-substitute ?prt env*)))))


(defmethod compile-substitute :unq
  [node env]
  (:expr node))


(defmethod compile-substitute :uns
  [node env]
  (:expr node))


(defn compile-vec-prt
  {:private true}
  ([prt env]
   (compile-vec-prt [] prt env))
  ([vec-form prt env]
   (r.match/find (rewrite-partition prt)
     ;; Compile [?a ~@xs ?b] as (conj (into [?x] xs) ?b)
     (with [%not-uns (not {:tag :uns})]
       {:left {:tag :cat
               :elements [(and %not-uns !1s) ... {:tag :uns :as !uns} ..1 (and %not-uns !2s) ...]}
        :right ?right})
     (compile-vec-prt `(conj ~(reduce
                               (fn [form compiled-uns]
                                 `(into ~form ~compiled-uns))
                               `(conj ~vec-form ~@(compile-all !1s env))
                               (compile-all !uns env))
                             ~@(compile-all !2s env))
                      {:tag :prt
                       :left ?right
                       :right {:tag :cat
                               :elements []}}
                      env)

     ;; Compile [?x ?y . ?z] as (conj vec-form ?x ?y ?z)
     {:left {:tag :cat
             :elements ?left-elements}
      :right {:tag :cat
              :elements ?right-elements}}
     `(conj ~vec-form
            ~@(compile-all ?left-elements env)
            ~@(compile-all ?right-elements env))

     ;; Compile [1 2 . 3 4] as (conj vec-form 1 2 3 4)
     {:left {:tag :cat
             :elements ?left-elements}
      :right {:tag :lit
              :value (_ ... :as ?right-elements)}}
     `(conj ~vec-form
            ~@(compile-all ?left-elements env)
            ~@?right-elements)

     {:left {:tag :cat
             :elements ?elements}
      :right ?right}
     (compile-vec-prt `(conj ~vec-form ~@(compile-all ?elements env))
                      {:tag :prt
                       :left ?right
                       :right {:tag :cat
                               :elements []}}
                      env)

     ;; Compile [!xs ...] as (into vec-form !xs)
     {:left {:tag (or :rst :rp+ :rp*) :as ?rep}
      :right ?right}
     (compile-vec-prt `(into ~vec-form ~(compile-substitute ?rep env))
                      {:tag :prt
                       :left ?right
                       :right {:tag :cat
                               :elements []}}
                      env)
     _
     `(into ~vec-form ~(compile-substitute prt env)))))


(defmethod compile-substitute :vec
  [node env]
  (compile-vec-prt (:prt node) (assoc env :collection-context :vector)))


(defmethod compile-substitute :ref
  [node env]
  `(~(:symbol node)))


(defmethod compile-substitute :wth
  [node env]
  (let [;; Get all of the references used in the body and in the
        ;; bindings.
        ref-set (into (r.syntax/references node)
                      (comp (map :pattern)
                            (mapcat r.syntax/references))
                      (:bindings node))
        ;; Update the compilation environment for subnodes.
        env* (add-wth-refs env (r.syntax/make-ref-map node))]
    ;; Compile functions only for the references used.
    `(letfn [~@(r.match/search [node ref-set]
                 [{:bindings (scan {:ref {:symbol ?symbol :as ?ref}
                                    :pattern ?pattern})}
                  #{?ref}]
                 `(~?symbol []
                   ~(compile-substitute ?pattern env*)))]
       ~(compile-substitute (:body node) env*))))

(defmacro substitute
  [x]
  (let [node (parse-and-rewrite x &env)
        env (make-env node)
        mvr-ref-bindings (mapcat
                          (fn [[mvr-node ref-sym]]
                            [ref-sym `(volatile! 0)])
                          (:mvr-refs env))
        compiled (compile-substitute node env)]
    (if (seq mvr-ref-bindings)
      `(let [~@mvr-ref-bindings]
         ~compiled)
      compiled)))

(s/fdef substitute
  :args (s/cat :term any?)
  :ret any?)

(comment
  (let [!xs [:x1 :x2 :x3]
        ?x 4
        ?y 5
        ?z 6
        !ys [7 8 9]]
    (substitute [!xs ..2 !ys ... ?x ?y ?z]))
  (parse-and-rewrite '[!xs ..2 ?x ?y]))
