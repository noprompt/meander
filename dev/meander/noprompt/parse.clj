(ns meander.noprompt.parse
  (:require [clojure.string :as string]
            [meander.environment.zeta :as m.env]
            [meander.primitive.zeta :as m.prim]))

(defmacro todo [env form]
  `(throw (ex-info "TODO" {:env ~env, :form ~form, :meta '~(meta &form)})))

(defn unquote-splicing-form? [x]
  (and (seq? x)
       (= 'clojure.core/unquote-splicing (first x))))

(declare parse)

(defn parse-all [env forms]
  (map parse (repeat env) forms))

(defn parse-literal-keyword [env form]
  (if (qualified-keyword? form)
    (m.prim/keyword (namespace form) (name form))
    (m.prim/keyword (name form))))

(defn parse-literal-symbol [env form]
  (if (qualified-symbol? form)
    (m.prim/symbol (namespace form) (name form))
    (m.prim/symbol (name form))))

(defn parse-symbol [env form]
  {:pre [(symbol? form)]}
  (let [symbol-name (name form)]
    (cond
      (string/starts-with? symbol-name "?")
      (if (namespace form)
        (m.prim/? form)
        (m.prim/? (symbol (name (::m.env/namespace env)) symbol-name)))

      :else
      (parse-literal-symbol env form))))

(defn parse-list [env form]
  {:pre [(sequential? form)]}
  (apply m.prim/list (parse-all env form)))

(defn parse-concat
  [env form]
  {:pre [(sequential? form)]}
  (let [[a b] (split-with (complement unquote-splicing-form?) form)
        term-a (if (seq a)
                 (parse-list env a))
        term-b (if (seq b)
                 (let [[[_ form] & rest-b] b
                       unquote-splicing-term (m.prim/is form)]
                   (if (seq rest-b)
                     (m.prim/concat unquote-splicing-term (parse-concat env rest-b))
                     (if term-a
                       unquote-splicing-term
                       (m.prim/seq unquote-splicing-term)))))]
    (case [(boolean term-a) (boolean term-b)]
      [true true]
      (m.prim/concat term-a term-b)

      [true false]
      term-a

      [false true]
      term-b

      [false false]
      (m.prim/list))))

(defn parse-seq [env form]
  {:pre [(seq? form)]}
  (case (first form)
    clojure.core/unquote
    (m.prim/is (second form))
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
  (m.prim/vec (parse-concat env form)))

(defn parse [env form]
  (cond
    (boolean? form)
    (m.prim/is form)

    (number? form)
    (m.prim/is form)

    (keyword? form)
    (parse-literal-keyword env form)

    (string? form)
    (m.prim/is form)

    (symbol? form)
    (parse-symbol env form)

    (seq? form)
    (parse-seq env form)

    (vector? form)
    (parse-vector env form)

    :else
    (todo env form)))


(comment
  (parse
   (m.env/create {::m.env/operators
                  {`m.prim/boolean
                   (fn [_]
                     {:object `(m.prim/some true false)})
                   `m.prim/some
                   (fn [[_ & xs :as form]]
                     {:object (reduced (apply m.prim/some (parse-all (::m.env (meta form)) xs)))})}})
   '[1 ~@xs 3 (m.prim/boolean)]))
