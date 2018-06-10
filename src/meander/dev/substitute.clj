(ns meander.dev.substitute
  (:require [clojure.set :as set]
            [meander.dev.syntax :as syntax]))


(defn compile-ground [x]
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
  [env node]
  (syntax/tag node))


(defmulti compile-substitute {:arglists '([env node])}
  #'compile-substitute-dispatch)


(defmethod compile-substitute :any [_ _]
  (Object.))


(defmethod compile-substitute :seq [env [_ node]]
  (compile-substitute env node))


(defmethod compile-substitute :part [env [_ {left :left, right :right}]]
  `(concat ~(compile-substitute env left)
           ~(compile-substitute env right)))

(defmethod compile-substitute :vpart [env [_ {left :left, right :right}]]
  `(into (into [] ~(compile-substitute env left))
         ~(compile-substitute env right)))


(defmethod compile-substitute :cat [env [_ elems]]
  (cons `list (map (partial compile-substitute env) elems)))


(defmethod compile-substitute :vec [env [_ node]]
  (compile-substitute env node))


(defmethod compile-substitute :vcat [env [_ elems]]
  (mapv (partial compile-substitute env) elems))


(defmethod compile-substitute :seq-end [_ _]
  ())


(defmethod compile-substitute :lit [_ [_ lit]]
  (compile-ground lit))


(defmethod compile-substitute :var [_ [_ sym]]
  sym)


(defmethod compile-substitute :unq [_ [_ [_ form]]]
  form)


(defmacro mem-ref-sym [env mem-sym]
  `(if-some [[_# mem-ref-sym#] (find ~env ~mem-sym)]
     mem-ref-sym#
     (throw
      (ex-info
       (str "No ref symbol found for memvar " ~mem-sym)
       '{:env ~env
         :mem-sym ~mem-sym})))) 

(defmethod compile-substitute :mem [env [_ mem-sym]]
  (let [mem-ref-sym (mem-ref-sym env mem-sym)]
    (let [item (gensym "item__")]
      `(when-some [[_# ~item] (find (deref ~mem-ref-sym) 0)]
         (vswap! ~mem-ref-sym subvec 1)
         ~item))))


(defmethod compile-substitute :rest [env [_ {mem-sym :mem}]]
  (let [mem-ref-sym (mem-ref-sym env mem-sym)
        item (gensym "item__")
        loop-sym (gensym "loop__")]
    `((fn ~loop-sym []
        (when-some [[_# ~item] (find (deref ~mem-ref-sym) 0)]
          (vswap! ~mem-ref-sym subvec 1)
          (lazy-seq (cons ~item (~loop-sym))))))))


(defn compile-init [env init]
  (let [mem-vars (syntax/mem-vars init)
        loop-sym (gensym "loop__")]
    `((fn ~loop-sym []
        ~(if (seq mem-vars)
           ;; If there are mem-vars, loop until one of them is
           ;; exhausted.
           `(when (and ~@(map
                          (fn [[_ mem-sym]]
                            `(seq (deref ~(mem-ref-sym env mem-sym))))
                          mem-vars))
              (lazy-seq (concat ~(compile-substitute env init) (~loop-sym))))
           ;; If there are no mem-vars, loop forever.
           ;; Should this warn?
           `(lazy-seq (concat ~(compile-substitute env init) (~loop-sym))))))))

(defmethod compile-substitute :rep [env [_ {init :init}]]
  (compile-init env init))


(defmethod compile-substitute :init [env [_ {mem-sym :mem}]]
  (compile-init env [:cat [[:mem mem-sym]]]))


(defmethod compile-substitute :repk [env [_ {init :init, k :k}]]
  (let [loop-sym (gensym "loop__")]
    `((fn ~loop-sym [k#]
        (when (not (= ~k k#))
          (lazy-seq
           (concat ~(compile-substitute env init)
                   (~loop-sym (inc k#))))))
      0)))


(defmethod compile-substitute :map [env [_ m]]
  `(hash-map ~@(sequence
                (comp (mapcat identity)
                      (map (partial compile-substitute env)))
                m)))


(defmethod compile-substitute :set [env [_ s]]
  `(hash-set ~@(map (partial compile-substitute env) s)))


(defmacro substitute [pat] 
  (let [node (syntax/parse pat)
        env (into {}
                  (map (fn [[_ sym]]
                         [sym (symbol (str sym "-ref"))]))
                  (syntax/mem-vars node))]
    `(let [~@(mapcat
              (fn [[var-tag var-sym]]
                (case var-tag
                  :mem
                  (when-not (contains? &env var-sym)
                    [var-sym []])

                  :var
                  (when-not (contains? &env var-sym)
                    [var-sym `[:meander/unbound '~var-sym]])))
              (syntax/variables node))
           ~@(mapcat
              (fn [[mem-sym mem-ref-sym]]
                [mem-ref-sym `(volatile! ~mem-sym)])
              env)]
       ~(compile-substitute env node))))

