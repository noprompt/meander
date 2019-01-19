(ns meander.substitute.alpha
  #?(:cljs (:require-macros [meander.substitute.alpha]))
  (:require [clojure.spec.alpha :as s]
            [meander.syntax.alpha :as r.syntax]
            [meander.util.alpha :as r.util]))


(defn make-env
  "Derives a map of {mvr ref-sym} from the pattern node where
  mvr is a memory variable node [:mvr !xs], and ref-sym is a memory
  varable symbol with the suffix _ref__<digits> e.g. !xs_ref__234."
  {:private true}
  [pat]
  (into {} (map (fn [[_ sym :as mvr]]
                  [mvr (gensym (str sym "_ref__"))]))
        (r.syntax/memory-variables pat)))


(defn get-mvr-ref-sym
  "INTERNAL Gets the reference symbol for mvr-sym from env. Throws if
  the symbol is not found (sanity check)."
  {:private true}
  [env [_ mvr-sym :as mvr]]
  (if-some [[_ mvr-ref-sym] (find env mvr)]
    mvr-ref-sym
    (throw
     (ex-info (str "No ref symbol found for memory variable " mvr-sym)
              {:env env
               :mvr mvr}))))


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
  [[_ {form :form, terms :terms}] env]
  `(list '~'app ~form ~@(map compile-substitute terms (repeat env))))


(defmethod compile-substitute :cat
  [[_ nodes] env]
  (if (some #{:uns} (map r.syntax/tag nodes))
    `(concat ~@(sequence (map
                          (fn [[tag :as node] env]
                            (let [form (compile-substitute node env)]
                              (if (= :uns tag)
                                form
                                `(list ~form)))))
                         nodes
                         (repeat env)))   
    `(list ~@(sequence (map compile-substitute) nodes (repeat env)))))


(defmethod compile-substitute :cnj
  [[_ {terms :terms}] env]
  `(list '~'and ~@(map compile-substitute terms (repeat env))))


(defmethod compile-substitute :drp
  [_ _]
  `(list))


(defmethod compile-substitute :dsj
  [[_ {terms :terms}] env]
  `(list '~'or  ~@(map compile-substitute terms (repeat env))))


(defmethod compile-substitute :lit
  [[_ x] env]
  (compile-ground x))


(defmethod compile-substitute :lvr
  [[_ sym] env]
  sym)


(defmethod compile-substitute :map [[_ m] env]
  `(hash-map ~@(sequence
                (comp (mapcat identity)
                      (map (fn [x]
                             (compile-substitute x env))))
                m))) 


(defmethod compile-substitute :mvr [mvr env]
  (let [mvr-ref-sym (get-mvr-ref-sym env mvr)]
    (let [item (gensym "item__")]
      `(when-some [[_# ~item] (find (deref ~mvr-ref-sym) 0)]
         (vswap! ~mvr-ref-sym subvec 1)
         ~item)))) 


(defmethod compile-substitute :not
  [[_ {term :term}] env]
  `(list '~'not ~(compile-substitute term env)))


(defmethod compile-substitute :prd
  [[_ {form :form, terms :terms}] env]
  `(list '~'pred ~form ~@(map compile-substitute terms (repeat env))))


(defmethod compile-substitute :prt
  [[_ {l :left, r :right}] env]
  (if (and (= (r.syntax/tag l) :cat)
           (zero? (count (second l))))
    (when r
      (compile-substitute r env))
    `(concat ~(compile-substitute l env)
             ~(when r
                (compile-substitute r env)))))


(defmethod compile-substitute :quo
  [[_ {form :form}] env]
  `(quote ~form))

(defmethod compile-substitute :rp* [node env]
  (let [[_ {items :items}] node
        cat-node [:cat items]
        mvrs (r.syntax/memory-variables node)]
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
      ;; If there are no mem-vars, loop forever. This case should
      ;; either warn or throw!
      `(sequence (mapcat identity)
                 (repeatedly (fn [] ~(compile-substitute cat-node env)))))))

(defmethod compile-substitute :rp+ [node env]
  (let [[_ {items :items, dots :dots}] node
        n (r.util/parse-int (aget (.split (name dots) "\\.+" 2) 1))
        cat-node [:cat items]
        mvrs (r.syntax/memory-variables node)]
    (if (seq mvrs)
      ;; If there are mem-vars, loop until one or all of them is
      ;; exhausted and we've looped at least twice.
      `(let [ret# (transient [])]
         (loop [n# 0]
           (if (and ~@(map
                       (fn [mvr]
                         `(seq (deref ~(get-mvr-ref-sym env mvr))))
                       mvrs))
             (do
               (run! (fn [x#] (conj! ret# x#)) ~(compile-substitute cat-node env))
               (recur (inc n#)))
             (if (< n# ~n)
               (do
                 (run! (fn [x#] (conj! ret# x#)) ~(compile-substitute cat-node env))
                 (recur (inc n#)))
               (persistent! ret#)))))
      ;; If there are no mem-vars, loop forever. This case should
      ;; either warn or throw!
      `(sequence (mapcat identity)
                 (repeatedly (fn [] ~(compile-substitute cat-node env)))))))


(defmethod compile-substitute :rst [[_ {mvr-sym :mvr}] env]
  (let [mvr-ref-sym (get-mvr-ref-sym env mvr-sym)]
    ;; Memory variable substitution is stateful so we must consume
    ;; all remaining elements in the memory variable reference to
    ;; avoid nondeterministic behavior.
    `(let [res# (deref ~mvr-ref-sym)]
       (vreset! ~mvr-ref-sym [])
       res#)))


(defmethod compile-substitute :set [[_ s] env]
  `(hash-set ~@(map (fn [x] (compile-substitute x env)) s)))


(defmethod compile-substitute :seq
  [node env]
  (let [[_ prt] node]
    `(seq ~(compile-substitute prt env))))


(defmethod compile-substitute :unq
  [[_ {expr :expr}] env]
  expr)


(defmethod compile-substitute :uns
  [[_ {form :form}] env]
  form)


(defmethod compile-substitute :vec
  [node env]
  (let [[_ prt] node]
    `(vec ~(compile-substitute prt env))))


(defmacro substitute [term]
  (let [node (r.syntax/parse term)
        env (make-env node)]
    `(let [~@(mapcat
              (fn [[[_ mvr-sym] ref-sym]]
                [ref-sym `(volatile! ~mvr-sym)])
              env)]
       ~(compile-substitute node env))))

(s/fdef substitute
  :args (s/cat :term :meander.syntax.alpha/term)
  :ret any?)
