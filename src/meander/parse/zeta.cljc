(ns meander.parse.zeta
  (:require [clojure.string :as string]
            [meander.environment.zeta :as m.env]
            [meander.primitive.zeta :as m.primitive]))

(defmacro todo
  {:private true}
  [env form]
  `(throw (ex-info "TODO" {:env ~env, :form ~form, :meta '~(meta &form)})))

(defn quote-form?
  {:private true}
  [x]
  (and (seq? x)
       (= 'quote (first x))))

(defn unquote-form?
  {:private true}
  [x]
  (and (seq? x)
       (= 'clojure.core/unquote (first x))))

(defn unquote-splicing-form?
  {:private true}
  [x]
  (and (seq? x)
       (= 'clojure.core/unquote-splicing (first x))))

(declare parse)

(defn parse-all [env forms]
  (map parse (repeat env) forms))

(defn parse-keyword [env form]
  (if (qualified-keyword? form)
    (m.primitive/keyword (m.primitive/is (namespace form)) (m.primitive/is (name form)))
    (m.primitive/keyword (m.primitive/is (name form)))))

(defn parse-symbol [env form]
  (if (qualified-symbol? form)
    (m.primitive/symbol (m.primitive/is (namespace form)) (m.primitive/is (name form)))
    (m.primitive/symbol (m.primitive/is (name form)))))

(defn parse-list [env form]
  {:pre [(sequential? form)]}
  (apply m.primitive/list (parse-all env form)))

(defn parse-concat
  [env form]
  {:pre [(sequential? form)]}
  (let [[a b] (split-with (complement unquote-splicing-form?) form)
        term-a (if (seq a)
                 (parse-list env a))
        term-b (if (seq b)
                 (let [[[_ form] & rest-b] b
                       eval (::m.env/eval env)
                       unquote-splicing-term (m.primitive/is (eval form))]
                   (if (seq rest-b)
                     (m.primitive/concat unquote-splicing-term (parse-concat env rest-b))
                     (if term-a
                       unquote-splicing-term
                       (m.primitive/seq unquote-splicing-term)))))]
    (case [(boolean term-a) (boolean term-b)]
      [true true]
      (m.primitive/concat term-a term-b)

      [true false]
      term-a

      [false true]
      term-b

      [false false]
      (m.primitive/list))))

(defn parse-seq [env form]
  {:pre [(seq? form)]}
  (case (first form)
    quote
    (let [x (second form)]
      (if (symbol? x)
        (parse-symbol env x)
        (m.primitive/is x)))
    
    clojure.core/unquote
    (let [eval (::m.env/eval env)]
      (m.primitive/is (eval (second form))))
    ;; else
    (let [form* (m.env/operator-expand env form)]
      ;; NOTE: Using `=` because `identical?` seems risky.
      (if (= form* form)
        (parse-concat env form)
        (if (reduced? form*)
          (deref form*)
          (parse env form*))))))

(defn parse-vector [env form]
  {:pre [(vector? form)]}
  (m.primitive/vec (parse-concat env form)))

(defn parse-map [env form]
  {:pre [(map? form)]}
  (reduce
   (fn [m [k v]]
     (m.primitive/assoc m (parse env k) (parse env v)))
   (m.primitive/hash-map)
   form))

(defn parse-set [env form]
  {:pre [(set? form)]}
  (apply m.primitive/hash-set (parse-all env form)))

(defn apply-extensions
  {:private true}
  [env form]
  (reduce
   (fn [_ f]
     (let [x (f form)]
       (if (= x form)
         x
         (reduced x))))
   form
   (::m.env/extensions env)))

(defn parse [env form]
  (let [form* (apply-extensions env form)]
    (cond
      (reduced? form*)
      ;; TODO: Assert form* is a term e.g. it implements IRedex,
      ;; IQuery, or IYield.
      (deref form*)

      (not= form* form)
      (parse env form*)

      (boolean? form)
      (m.primitive/is form)

      (number? form)
      (m.primitive/is form)

      (keyword? form)
      (m.primitive/is form)

      (string? form)
      (m.primitive/is form)

      (symbol? form)
      (parse-symbol env form)

      (seq? form)
      (parse-seq env form)

      (vector? form)
      (parse-vector env form)

      (map? form)
      (parse-map env form)

      (set? form)
      (parse-set env form)

      (fn? form)
      (m.primitive/is form)

      :else
      (todo env form))))


(defn prewalk [f x]
  (let [y (f x)]
    (cond
      (reduced? y)
      (deref y)

      (seq? y)
      (doall (map (partial prewalk f) y))

      (vector? y)
      (mapv (partial prewalk f) y)

      (map? y)
      (reduce-kv (fn [m k v]
                   (assoc m (prewalk f k) (prewalk f v)))
                 {}
                 y)

      (set? y)
      (set (map (partial prewalk f) y))


      :else y)))

(defn qualify-operator-symbols [env form]
  (prewalk (fn [x]
             (cond
               (quote-form? x)
               (reduced x)

               (and (seq? x) (seq x))
               (let [head (first x)]
                 (if (symbol? head)
                   (let [fq-sym (m.env/qualify-symbol env head)]
                     (if (m.env/operator-symbol? env fq-sym)
                       (cons fq-sym (rest x))
                       x))
                   x))

               :else x))
           form))

(defn autogensym [form]
  (prewalk (memoize
            (fn [x]
              (cond
                (quote-form? x)
                (reduced x)

                (and (symbol? x)
                     (string/ends-with? (name x) "#"))
                (let [old-sym-name (name x)
                      new-sym-name (subs old-sym-name 0 (dec (count old-sym-name)))]
                  (symbol (namespace x)
                          (name (gensym new-sym-name))))

                :else x)))
           form))
