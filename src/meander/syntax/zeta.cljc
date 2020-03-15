(ns meander.syntax.zeta
  (:require [meander.util.zeta :as m.util]
            [meander.compiled.parse.zeta :as m.parse])
  #?(:cljs (:require-macros [meander.syntax.zeta])))

;; The map returned by this function is used to qualify symbols during
;; parse.
(defn ns-aliases*
  "Like `ns-aliases` but returns a map from symbol to symbol."
  [ns]
  #?(:clj (into {} (map (fn [[alias ns]]
                          [alias (ns-name ns)]))
                (ns-aliases ns))
     :cljs {}))

(defn make-parse-env [ns]
  {:aliases (ns-aliases* ns)
   :cata-symbol (gensym "C__")
   :state-symbol (gensym "S__")
   :expander-registry {}
   :parser-registry {}})

(defn parse
  ([form]
   (parse form (make-parse-env *ns*)))
  ([form env]
   (let [ast (m.parse/parse [form env])]
     {:tag :root, :next ast})))

(defn ast?
  [x]
  (and (map? x) (contains? x :tag)))

(defn cat?
  [x]
  (= (get x :tag) :cat))

(defn child-entries
  [ast]
  (cond
    (cat? ast)
    [(find ast :sequence)
     (find ast :next)]

    (ast? ast)
    (into [] (keep (fn [e]
                     (if (ast? (val e))
                       e)))
         ast)

    :else
    []))

(defn children
  [ast]
  (mapcat (fn [e]
            (let [x (val e)]
              (if (sequential? x)
                x
                (list x))))
   (child-entries ast)))

(defn subnodes [ast]
  (tree-seq ast? children ast))

(defn variables [ast]
  (into #{} (filter
             (fn [ast]
               (case (get ast :tag)
                 (:logic-variable :memory-variable :mutable-variable)
                 true
                 ;;
                 nil)))
        (subnodes ast)))

(defn prewalk [f ast]
  (let [ast* (f ast)]
    (reduce
     (fn [ast* [key val]]
       (let [val* (if (sequential? val)
                    (mapv (fn [ast]
                            (prewalk f ast)) val)
                    (prewalk f val))]
         (assoc ast* key val*)))
     ast*
     (child-entries ast*))))

(defn postwalk [f ast]
  (f (reduce
      (fn [ast [key val]]
        (let [val* (if (sequential? val)
                     (mapv (fn [ast]
                             (postwalk f ast)) val)
                     (postwalk f val))]
          (assoc ast key val*)))
      ast
      (child-entries ast))))
