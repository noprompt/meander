(ns meander.peval.zeta)

(defn constant-value? [x]
  (or (number? x)
      (keyword? x)
      (string? x)
      (boolean? x)
      (nil? x)
      (and (or (map? x)
               (vector? x)
               (set? x))
           (every? constant-value? x))
      (and (seq? x)
           (or (= (first x) 'quote)
               (= x ())))))

(defn truthy-constant-value?
  [x]
  (or (true? x)
      (number? x)
      (string? x)
      (keyword? x)
      (vector? x)
      (map? x)
      (set? x)
      (= x ())))

(defn falsey-constant-value?
  [x]
  (or (false? x)
      (nil? x)))

(defn constant-expression? [x]
  (or (symbol? x)
      (constant-value? x)
      (and (or (map? x)
               (vector? x)
               (set? x))
           (every? constant-expression? x))))

(defmulti peval-seq
  {:arglists '([seq])}
  (fn [xs]
    (if (seq xs)
      (first xs)
      ::default))
  :default ::default)

(defmethod peval-seq ::default [xs]
  xs)

(defn peval [form]
  (cond
    (constant-expression? form)
    form

    (seq? form)
    (peval-seq form)

    :else
    form))

(defn =-form
  {:private true}
  [args]
  (if (< 1 (count args))
    `(= ~@args)
    true))

(defmethod peval-seq 'if [[_ test then else :as form]]
  (cond
    (truthy-constant-value? test)
    then

    (falsey-constant-value? test)
    else

    :else
    form))

(defmethod peval-seq `= [[f & args]]
  (let [distinct-args (distinct args)]
    (if (= (count distinct-args) 1)
      true
      (let [constant-value-args (filter constant-value? args)]
        (if (< 1 (count constant-value-args))
          (if (apply = constant-value-args)
            (let [symbolic-args (filter constant-expression? args)]
              (if (and (= (count symbolic-args) 1)
                       (seq constant-value-args))
                (=-form constant-value-args)
                (=-form distinct-args)))
            false)
          (=-form distinct-args))))))

(defmethod peval-seq `apply [[f g & args :as form]]
  (let [last-arg (last args)]
    (if (and (not (seq? last-arg))
             (coll? last-arg))
      (peval-seq `(~g ~@(butlast args) ~@(last args)))
      form)))

(defmethod peval-seq `assoc [[f m k v :as form]]
  (if (map? m)
    (assoc m k v)
    form))

(defmethod peval-seq `get [[f m k v :as form]]
  (if (map? m)
    (if (empty? m)
      v
      (if (contains? m k)
        (get m k)
        (if (every? constant-value? (keys m))
          v
          form)))
    form))

(defmethod peval-seq `conj [[f xs & args :as form]]
  (if (vector? xs)
    (apply conj xs args)
    form))

(defmethod peval-seq `vec [[f x :as xs]]
  (if (and (not (seq? x))
           (coll? x))
    (vec x)
    xs))

(defmethod peval-seq `vector [[f & args]]
  (vec args))
