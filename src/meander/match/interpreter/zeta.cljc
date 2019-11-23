(ns meander.match.interpreter.zeta
  (:require [meander.syntax.zeta :as m.syntax]
            [meander.util.zeta :as m.util]))

(defmulti interpret
  (fn [ast form smap]
    (get ast :tag)))

(defmethod interpret :as
  [ast form smap]
  (let [pattern-ast (get ast :pattern)
        next-ast (get ast :next)]
    (mapcat
     (fn [smap]
       (interpret pattern-ast form smap))
     (interpret next-ast form smap))))

(defmethod interpret :cat
  [ast form smap]
  (if (seq form)
    (let [pattern-ast (get ast :pattern)
          next-ast (get ast :next)
          head (nth form 0)
          tail (next form)]
      (mapcat
       (fn [smap]
         (interpret next-ast tail smap))
       (interpret pattern-ast head smap)))))

(defmethod interpret :empty
  [ast form smap]
  (if (seq form)
    nil
    (list smap)))

(defmethod interpret :entry
  [ast entry smap]
  (let [key-ast (get ast :key)
        val-ast (get ast :val)
        key (key entry)
        val (val entry)]
    (mapcat
     (fn [smap]
       (interpret val-ast val smap))
     (interpret key-ast key smap))))

(defmethod interpret :literal
  [ast form smap]
  (let [x (get ast :form)]
    (if (= x form)
      (list smap))))

(defmethod interpret :logic-variable
  [ast form smap]
  (if-some [[_ other-form] (find smap ast)]
    (if (= other-form form)
      (list smap))
    (list (assoc smap ast form))))

(defmethod interpret :memory-variable
  [ast form smap]
  (list (update smap ast (fnil conj []) form)))

(defmethod interpret :mutable-variable
  [ast form smap]
  (let [pattern-ast (get ast :pattern)]
    (list (assoc smap ast form))))

(defmethod interpret :map
  [ast form smap]
  (if (map? form)
    (let [entries (get ast :entries)]
      (if (seq entries)
        (let [[entry-ast & rest-entries] entries
              ast* (assoc ast :entries rest-entries)]
          (mapcat
           (fn [[k :as entry]]
             (let [form* (dissoc form k)]
               (mapcat
                (fn [smap]
                  (interpret ast* form* smap))
                (interpret entry-ast entry smap))))
           form))
        (if-some [rest-ast (get ast :rest)]
          (interpret rest-ast form smap)
          (list smap))))))

(defmethod interpret :logical-plus
  [ast form smap]
  (let [pattern-ast (get ast :pattern)
        operator-ast (get ast :operator)
        logic-variable-ast (get operator-ast :logic-variable)
        next-ast (get ast :next)
        star-ast (merge ast {:tag :star
                             :next {:tag :empty}})]
    (mapcat
     (fn [[left-part right-part]]
       (mapcat
        (fn [smap]
          (mapcat
           (fn [smap]
             (interpret next-ast right-part smap))
           (interpret star-ast left-part smap)))
        (interpret logic-variable-ast (count left-part) smap)))
     (m.util/partitions 2 form))))

(defmethod interpret :plus
  [ast form smap]
  (let [pattern-ast (get ast :pattern)
        operator-ast (get ast :operator)
        n (get operator-ast :n)
        next-ast (get ast :next)]
    (case n
      0
      (mapcat
       (fn [[left-part right-part]]
         (mapcat
          (fn [smap]
            (m.util/knit
             [(interpret ast right-part smap)
              (interpret next-ast right-part smap)]))
          (interpret pattern-ast left-part smap)))
       (m.util/partitions 2 form))

      ;; else
      (let [operator-ast* (assoc operator-ast :n (dec n))
            ast* (assoc ast :operator operator-ast*)]
        (mapcat
         (fn [[left-part right-part]]
           (mapcat
            (fn [smap]
              (m.util/knit
               [(interpret ast* right-part smap)
                (interpret next-ast right-part smap)]))
            (interpret pattern-ast left-part smap)))
         (m.util/partitions 2 form))))))

(defmethod interpret :seq
  [ast form smap]
  (if (seq? form)
    (let [pattern (get ast :pattern)]
      (interpret pattern form smap))))

(defmethod interpret :set
  [ast form smap]
  (if (set? form)
    (let [elements (get ast :elements)]
      (if (seq elements)
        (let [[element-ast & rest-elements] elements
              ast* (assoc ast :elements rest-elements)]
          (mapcat
           (fn [element]
             (let [form* (disj form element)]
               (mapcat
                (fn [smap]
                  (interpret ast* form* smap))
                (interpret element-ast element smap))))
           form))
        (if-some [rest-ast (get ast :rest)]
          (interpret rest-ast form smap)
          (list smap))))))

(defmethod interpret :star
  [ast form smap]
  (let [pattern-ast (get ast :pattern)
        next-ast (get ast :next)]
    (mapcat
     (fn [[left-part right-part]]
       (mapcat
        (fn [smap]
          (m.util/knit
           [(interpret ast right-part smap)
            (interpret next-ast right-part smap)]))
        (interpret pattern-ast left-part smap)))
     (m.util/partitions 2 form))))

(defmethod interpret :vector
  [ast form smap]
  (if (vector? form)
    (let [pattern (get ast :pattern)]
      (interpret pattern form smap))))

(defn match-interpret
  {:style/indent 1}
  [form & patterns]
  (m.util/knit
   (sequence
    (comp (map m.syntax/parse)
          (map (fn [ast] (interpret ast form {}))))
    patterns)))
