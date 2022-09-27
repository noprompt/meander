(ns meander.set.zeta
  (:require
   [meander.environment.zeta :as m.env]
   [meander.logic.zeta :as m.logic]
   [meander.primitive.hash-set.zeta :as m.primitive.hash-set]
   [meander.primitive.zeta :as m.primitive]
   [meander.private.zeta :as m.private]
   [meander.protocols.zeta :as m.protocols]
   [meander.state.zeta :as m.state]
   [meander.zeta :as m])
  (:refer-clojure :exclude [conj
                            empty]))

;; Set operators and notation
;; --------------------------

(m.private/def-fn-operator any m.primitive.hash-set/any)
(m.private/def-fn-operator conj m.primitive.hash-set/conj)
(m.private/def-fn-operator empty m.primitive.hash-set/empty)
(m.private/def-fn-operator intersection* m.primitive.hash-set/intersection)
(m.private/def-fn-operator member m.primitive.hash-set/member)
(m.private/def-fn-operator union* m.primitive.hash-set/union)

(m/defoperator union
  (m/system
   ;; (union) => (empty)
   (m/rule (_) (`empty))

   ;; (union a) => (member a)
   (m/rule (_ ?a) (`member ?a))

   ;; (union a a) => (member (each a a))
   (m/rule
    (?op ?a ?a)
    (`member (`m/each ?a ?a)))

   ;; (union a b & more) => (union* a (union b & more)
   (m/rule
    (m/cons ?op (m/cons ?a ?b))
    (`union* ?a (m/cons ?op ?b))))

  {:notations [m/anything-symbol
               m/logic-variable-symbol]})

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

(m/defnotation hash-set-as
  (m/rule
   (union #{(m/with-meta ?x (m/assoc ?rest-meta ::m/as true))} ?s)
   (`m/each (m/with-meta ?x ?rest-meta) ?s))
  {:notations [m/logic-variable-symbol]})

(m/defnotation hash-set-rest
  (m/system
   (m/rule
    #{(m/with-meta ?x (m/assoc ?rest-meta (m/symbol (m/str "&" _)) true))}
    (`m/each (m/with-meta ?x ?rest-meta)))

   (m/rule
    (union #{(m/with-meta ?x (m/assoc ?rest-meta (m/symbol (m/str "&" _)) true))} ?s)
    (`union (m/with-meta ?x ?rest-meta) ?s)))
  {:notations [m/anything-symbol
               m/logic-variable-symbol]})
