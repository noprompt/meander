(ns meander.substitute.syntax.epsilon
  #?(:clj
     (:require [clojure.walk :as walk]
               [clojure.spec.alpha :as s]
               [clojure.core.specs.alpha :as core.specs]
               [meander.match.epsilon :as r.match]
               [meander.syntax.epsilon :as r.syntax]
               [meander.util.epsilon :as r.util])
     :cljs
     (:require [clojure.walk :as walk]
               [cljs.spec.alpha :as s :include-macros true]
               [cljs.core.specs.alpha :as core.specs]
               [meander.match.epsilon :as r.match]
               [meander.syntax.epsilon :as r.syntax]
               [meander.util.epsilon :as r.util]))
  #?(:cljs
     (:require-macros [meander.substitute.syntax.epsilon])))

;; ---------------------------------------------------------------------
;; Rewrite helpers

(defn rewrite-partition
  [node]
  (r.match/find node
    (with [%right (not (or () []))]
      {:tag :prt
       :left {:tag :cat
              :elements ?ls}
       :right {:tag :cat
               :elements (and %right ?rs)}
       :as ?prt})
    {:tag :prt
     :left {:tag :cat
            :elements (vec (concat ?ls ?rs))}
     :right {:tag :cat
             :elements []}}

    {:tag :prt
     :left {:tag :cat
            :elements ?elements1}
     :right {:tag :prt
             :left {:tag :cat
                    :elements ?elements2}
             :right ?right}}
    (rewrite-partition
     {:tag :prt
      :left {:tag :cat
             :elements (vec (concat ?elements1 ?elements2))}
      :right ?right})

    {:tag :prt
     :left {:tag :prt,
            :left ?left,
            :right {:tag :cat
                    :elements ?elements-1}},
     :right {:tag :cat
             :elements ?elements-2}}
    (rewrite-partition
     {:tag :prt
      :left ?left
      :right {:tag :cat
              :elements (vec (concat ?elements-1 ?elements-2))}})

    {:tag :prt
     :left ?left
     :right {:tag :prt
             :left {:tag :cat
                    :elements ?elements}
             :right {:tag :cat
                     :elements (or [] ())}}}
    {:tag :prt
     :left ?left
     :right {:tag :cat
             :elements ?elements}}

    _
    node))

(defn rewrite-partitions
  [node]
  (r.syntax/prewalk rewrite-partition node))


(defn rewrite-coerce-literals-to-lit
  [node]
  (r.syntax/prewalk
   (fn [node]
     (if (and (r.syntax/literal? node)
              (not= (r.syntax/tag node) :cat)
              (not= (r.syntax/tag node) :prt))
       {:tag :lit
        :value (r.syntax/unparse node)}
       node))
   node))


(defn expand-ast
  [node]
  (-> node
      r.syntax/rename-refs
      rewrite-partitions
      rewrite-coerce-literals-to-lit))

(def parse-env
  {::r.syntax/parse-special (fn [form env] form)
   ::r.syntax/special-form? (constantly false)
   ::r.syntax/syntax-expand (fn [form env] form)})

(defn parse
  ([form]
   (r.syntax/parse form parse-env))
  ([form env]
   (r.syntax/parse form (merge env parse-env))))
