(ns meander.noprompt.parse
  (:require [clojure.string :as string]
            [meander.environment.zeta :as m.env]
            [meander.primitive.zeta :as m.prim]
            [meander.primitive.hash-set.zeta :as m.prim.hash-set]))

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

      (= symbol-name "_")
      (m.prim/anything)

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

(defn parse-map [env form]
  {:pre [(map? form)]}
  (reduce
   (fn [m [k v]]
     (m.prim/assoc m (parse env k) (parse env v)))
   (m.prim/hash-map)
   form))

(defn parse-set [env form]
  {:pre [(set? form)]}
  (apply m.prim/hash-set (parse-all env form)))

;; NOTE: Experimental
(defn apply-extensions [env form]
  (reduce
   (fn [form [k f]]
     (let [form (if (instance? clojure.lang.IObj form)
                  (vary-meta form assoc ::m.env/env env)
                  form)]
       (if-some [{:keys [object]} (f form)]
         (reduced object)
         form)))
   form
   (::m.env/extensions env)))

(defn parse [env form]
  (let [form* (apply-extensions env form)]
    (cond
      (reduced? form*)
      (deref form*)

      (not= form* form)
      (parse env form*)

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

      (map? form)
      (parse-map env form)

      (set? form)
      (parse-set env form)

      :else
      (todo env form))))


(comment
  (comment
    (define-syntax hash-map-ampersand
      (m/rule
       (assoc ?m (m/symbol "meander.zeta" (m/str "&" _)) ?v) 
       (m/list m/merge ?m ?v))))

  (defn hash-map-ampersand [form]
    (if (map? form)
      (if-some [k (some (fn [k]
                          (if (and (symbol? k)
                                   (string/starts-with? (name k) "&"))
                            k))
                        (keys form))]
        (let [env (::m.env/env (meta form))]
          {:object (reduced (m.prim/merge (parse env (dissoc form k)) (parse env (get form k))))}))))

  (defn hash-map-as [form]
    (if (map? form)
      (if-some [k (some (fn [k]
                          (if (and (keyword? k)
                                   (string/starts-with? (name k) "as"))
                            k))
                        (keys form))]
        (let [env (::m.env/env (meta form))]
          {:object (reduced (m.prim/each (parse env (get form k)) (parse env (dissoc form k))))}))))

  (parse
   (m.env/create {::m.env/operators
                  {`m.prim/rule
                   (fn [[_ q y :as form]]
                     (let [env (::m.env/env (meta form))]
                       {:object (reduced (m.prim/rule (parse env q) (parse env y)))}))

                   `m.prim/boolean
                   (fn [_]
                     {:object `(m.prim/some true false)})

                   `m.prim/some
                   (fn [[_ & xs :as form]]
                     {:object (reduced (apply m.prim/some (parse-all (::m.env/env (meta form)) xs)))})}

                  ::m.env/extensions
                  [['hash-map-as hash-map-as]
                   ['hash-map-ampersand hash-map-ampersand]]})
   '{:foo 1 :bar 2 & ?x :as ?foo}))



