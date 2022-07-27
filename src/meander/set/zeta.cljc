(ns meander.set.zeta
  (:require [meander.logic.zeta :as m.logic]
            [meander.private.zeta :as m.private]
            [meander.primitive.zeta :as m.primitive]
            [meander.primitive.hash-set.zeta :as m.primitive.hash-set]
            [meander.protocols.zeta :as m.protocols]
            [meander.state.zeta :as m.state]
            [meander.environment.zeta :as m.env])
  (:refer-clojure :exclude [conj
                            empty]))

(m.private/def-fn-operator any m.primitive.hash-set/any)
(m.private/def-fn-operator conj m.primitive.hash-set/conj)
(m.private/def-fn-operator empty m.primitive.hash-set/empty)
(m.private/def-fn-operator intersection* m.primitive.hash-set/intersection)
(m.private/def-fn-operator member m.primitive.hash-set/member)
(m.private/def-fn-operator union* m.primitive.hash-set/union)

(def ^:private union-system
  (m.primitive/fresh [?op ?a ?b]
    (m.primitive/system `union
      [;; (union) => (empty)
       (m.primitive/rule
        (m.primitive/list ?op)
        (m.primitive/list (m.primitive/is `empty)))

       ;; (union a) => (member a)
       (m.primitive/rule
        (m.primitive/list ?op ?a)
        (m.primitive/list (m.primitive/is `member) ?a))

       ;; (union a a) => (member (each a a))
       (m.primitive/rule
        (m.primitive/list ?op ?a ?a)
        (m.primitive/list (m.primitive/is `member) (m.primitive/list `m.private/each* ?a ?a)))

       ;; (union a b & more) => (union* a (union b & more)
       (m.primitive/rule
        (m.primitive/cons ?op (m.primitive/cons ?a ?b))
        (m.primitive/list (m.primitive/is `union*) ?a (m.primitive/cons ?op ?b)))])))

(defn union [& args]
  (let [istate (m.state/make {:object (cons `union args)})
        ilogic (m.logic/make-dff istate)
        result (m.protocols/-redex union-system ilogic)]
    (deref (m.protocols/-fmap result m.state/get-object))))

(m.env/operator-add! `union (fn [env form] (apply union (rest form))))

(def ^:private intersection-system
  (m.primitive/fresh [?op ?a ?b]
    (m.primitive/system `intersection
      [;; (intersection) => (empty)
       (m.primitive/rule
        (m.primitive/list ?op)
        (m.primitive/list (m.primitive/is `empty)))

       ;; (intersection a) => (member a)
       (m.primitive/rule
        (m.primitive/list ?op ?a)
        (m.primitive/list (m.primitive/is `member) ?a))

       ;; (intersection a a) => (member (each a a))
       (m.primitive/rule
        (m.primitive/list ?op ?a ?a)
        (m.primitive/list (m.primitive/is `member) (m.primitive/list `m.private/each* ?a ?a)))

       ;; (intersection a b & more) => (intersection* a (intersection b & more)
       (m.primitive/rule
        (m.primitive/cons ?op (m.primitive/cons ?a ?b))
        (m.primitive/list (m.primitive/is `intersection*) ?a (m.primitive/cons ?op ?b)))])))

(defn intersection [& args]
  (let [istate (m.state/make {:object (cons `intersection args)})
        ilogic (m.logic/make-dff istate)
        result (m.protocols/-redex intersection-system ilogic)]
    (deref (m.protocols/-fmap result m.state/get-object))))

(m.env/operator-add! `intersection (fn [env form] (apply intersection (rest form))))
