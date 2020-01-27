(ns meander.dev.zeta
  (:require [meander.epsilon :as me]
            [meander.runtime.zeta :as m.runtime]
            [meander.dev.parse.zeta :as dev.parse]
            [meander.dev.match.zeta :as dev.match]))

(defmacro solve [expression pattern]
  (let [ast (dev.parse/parse pattern)
        ast {:tag :root :next ast}
        target (gensym "target__")
        match-form (dev.match/match-compile [(list [ast target]) {}])]
    `(let [~target ~expression]
       ~match-form)))

(defn rewrite-operators [expr]
  (walk/prewalk
   (fn [x]
     (me/rewrite x
       (meander.zeta/and ?x ?y)
       (meander.epsilon/and ?x ?y)
       ?x ?x))
   expr))


(defmacro report [expr pattern action]
  (let [x (gensym)
        solve-form `(solve ~x ~pattern)
        search-form `(me/search ~(rewrite-operators expr) ~pattern ~action)]
    `(let [~x ~expr]
       {:pattern '~pattern
        :solve (with-out-str (time (dotimes [_# 1000] (doall ~solve-form))))
        :solve-expand (with-out-str (time (dotimes [_# 100] (macroexpand '~solve-form))))
        :search (with-out-str (time (dotimes [_# 1000] (doall ~search-form))))
        :search-expand (with-out-str (time (dotimes [_# 100] (macroexpand '~search-form))))
        :solve-answer ~solve-form
        :search-answer ~search-form})))

;; (report (into [] (repeat 10 1)) [!xs ...] {'!xs !xs})
;; (report (into [] (repeat 10 1)) [?x . ?x ...] {'?x ?x})
;; (let [root {:tag :root :next (dev.parse/parse '(1 2 3))}]
;;   (dev.match/match-compile [(list [root 'target]) {}]))
;; (solve '(1 2 3) (?x ?y ?z))

