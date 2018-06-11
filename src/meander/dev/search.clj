(ns meander.dev.search
  (:refer-clojure :exclude [compile])
  (:require [clojure.spec.alpha :as s]
            [meander.dev.match :as r.match]
            [meander.dev.syntax :as r.syntax]
            [meander.util :as r.util]))


(declare compile)

(defn compile-ctor-clauses-strategy [tag _vars _rows _default]
  tag)

(defmulti compile-ctor-clauses
  #'compile-ctor-clauses-strategy)


(defmethod compile-ctor-clauses :default [_ vars rows default]
  (r.match/compile
   (take 1 vars)
   (mapv
    (fn [row]
      (assoc row
             :cols [(r.match/first-column row)]
             :rhs
             (compile
              (drop 1 vars)
              [(assoc row :cols (r.match/rest-columns row))]
              default)))
    rows)
   default))

(defn compile-parts [vars rows default]
  (let [{:keys [matches searches]}
        (group-by
         (comp {false :matches, true :searches}
               r.syntax/search?
               r.match/first-column)
         rows)
        left-sym (gensym "left_vec__")
        right-sym (gensym "right_vec__")
        vars* (concat [left-sym right-sym] (rest vars))]
    `(sequence
      (mapcat
       (fn [[~left-sym ~right-sym]]
         ~(compile vars*
                   (map
                    (fn [row]
                      (let [{:keys [left right]} (r.syntax/data (r.match/first-column row))]
                        (assoc (r.match/drop-column row)
                               :cols (concat [left right] (r.match/rest-columns row)))))
                    searches)
                   (r.match/compile
                    vars*
                    (map
                     (fn [row]
                       (let [{:keys [left right]} (r.syntax/data (r.match/first-column row))]
                         (assoc (r.match/drop-column row)
                                :cols (concat [left right] (r.match/rest-columns row))
                                :rhs `(list ~(:rhs row)))))
                     matches)
                    default))))
      (r.util/partitions 2 ~(first vars)))))


(defmethod compile-ctor-clauses :vpart [_ vars rows default]
  (compile-parts vars rows default))


(defmethod compile-ctor-clauses :part [_ vars rows default]
  (compile-parts vars rows default))


(defmethod compile-ctor-clauses :vec [_ vars rows default]
  `(if (vector? ~(first vars))
     ~(compile vars
               (map
                (fn [row]
                  (assoc row
                         :cols (cons (r.syntax/data (r.match/first-column row))
                                     (r.match/rest-columns row))))
                rows)
               default)))


(defmethod compile-ctor-clauses :seq [_ vars rows default]
  `(if (seq? ~(first vars))
     ~(compile vars
               (map
                (fn [row]
                  (assoc row
                         :cols (cons (r.syntax/data (r.match/first-column row))
                                     (r.match/rest-columns row))))
                rows)
               default)))


(defn compile [vars rows default]
  (if (some? (r.match/first-column (first rows)))
    `(concat
      ~@(map
         (fn [[tag rows]]
           (compile-ctor-clauses tag vars rows default))
         (group-by
          (comp r.syntax/tag r.match/first-column)
          rows)))
    default))


(s/fdef search
  :args ::r.match/match-args
  :ret any?)


(defn parse-search-args
  {:private true}
  [match-args]
  (s/conform ::r.match/match-args match-args))


(defmacro search
  {:arglists '([target & pattern action ...])
   :style/indent :defn}
  [& search-args]
  (let [{:keys [target clauses]} (parse-search-args search-args)
        final-clause (some
                      (fn [{:keys [pat] :as clause}]
                        (when (= pat '[:any _])
                          clause))
                      clauses)
        clauses* (if final-clause
                   (remove (comp #{[:any '_]} :pat) clauses)
                   clauses)
        target-sym (gensym "target__")
        vars [target-sym]
        rows (sequence
              (map
               (fn [{:keys [pat rhs]}]
                 {:cols [pat]
                  :env #{}
                  :rhs rhs}))
              clauses*)]
    `(let [~target-sym ~target]
       ~(compile vars rows (if final-clause
                             `(list ~(:rhs final-clause))
                             nil)))))


#_
(time
  (search [1 2 3 4 5]
    [!xs ... . !ys ... . !zs ...]
    {:!xs !xs
     :!ys !ys 
     :!zs !zs}

    [1 2 . !xs-2 ... . !ys-2 ...]
    {:!xs-2 !xs-2
     :!ys-2 !ys-2}))



#_ ;; Not working
(search [1 2 [1 2 3 4 5] 3 4 5]
  [!xs ... . [!ws ... . !zs ...] . !ys ...]
  [!xs !ws !zs !ys])

