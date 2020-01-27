(ns meander.dev.kernel.zeta
  (:require [clojure.walk :as walk]
            [clojure.java.io :as io]
            [meander.epsilon :as me]
            [meander.runtime.zeta :as m.runtime]))

(defn epsilon->zeta [form]
  (walk/prewalk
   (fn [x]
     (me/rewrite x
       meander.substitute.runtime.epsilon/fail?
       meander.runtime.zeta/fail?

       meander.match.runtime.epsilon/fail?
       meander.runtime.zeta/fail?

       meander.match.runtime.epsilon/FAIL
       (meander.runtime.zeta/fail)

       meander.substitute.runtime.epsilon/FAIL
       (meander.runtime.zeta/fail)

       meander.substitute.runtime.epsilon/iterator
       meander.runtime.zeta/iterator

       ?x ?x))
   form))

(defn replace-$-variables [form]
  (let [replace (memoize (fn [s] (gensym (str s "__"))))]
    (walk/postwalk
     (fn [x]
       (me/match x
         (me/symbol nil (me/re #"\$.+" ?name))
         (replace ?name)

         ?x ?x))
     form)))

(defmacro defmodule [module-name & rules]
  (let [input-symbol (gensym "input__")
        rewrite-form (macroexpand `(me/rewrite ~input-symbol ~@rules))
        rewrite-form (epsilon->zeta rewrite-form)
        rewrite-form (replace-$-variables rewrite-form)
        defn-form `(defn ~module-name [~input-symbol] ~rewrite-form)
        ns (symbol (str "meander.compiled." module-name ".zeta"))
        ns-form (list 'ns ns '(:require [meander.runtime.zeta]))
        file (io/file "src/meander/compiled" (munge (name module-name))  "zeta.clj")
        parent (io/file (.getParent file))]
    (.mkdirs parent)
    (.createNewFile file)
    (with-open [file (io/writer file)]
      (binding [*out* file]
        (prn ns-form)
        (prn defn-form)))
    (require ns :reload)
    `(def ~(with-meta module-name '{:arglists '([input])})
       ~(symbol (str ns) (str module-name)))))
