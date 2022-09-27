(ns meander.integer.zeta
  (:refer-clojure :exclude [min max +])
  (:require [meander.logic.zeta :as m.logic]
            [meander.private.zeta :as m.private]
            [meander.primitive.zeta :as m.primitive]
            [meander.integer.primitive.zeta :as m.integer*]
            [meander.protocols.zeta :as m.protocols]
            [meander.state.zeta :as m.state]
            [meander.environment.zeta :as m.env]))

(m.private/def-fn-operator + m.integer*/+)
(m.private/def-fn-operator min* m.integer*/min)
(m.private/def-fn-operator max* m.integer*/max)

(def ^:private min-system
  (m.primitive/fresh [?a ?b]
    (m.primitive/system `min
      [;; (min integer integer) => integer
       (m.primitive/rule
        (m.primitive/list (m.primitive/anything)
                          (m.primitive/each ?a (m.integer*/any))
                          (m.primitive/each ?b (m.integer*/any)))
        (m.integer*/min ?a ?b))

       ;; TODO: (min integer (min integer x)) => (min (min integer integer) x)

       ;; Default
       (m.primitive/rule
        (m.primitive/list (m.primitive/anything) ?a ?b)
        (m.primitive/list (m.primitive/is `min*) ?a ?b))])))

(defn min [a b]
  (let [istate (m.state/make {:object (list `min a b)})
        ilogic (m.logic/make-dff istate)
        result (m.protocols/-redex min-system ilogic)]
    (deref (m.protocols/-fmap result m.state/get-object))))

(m.env/operator-add! `min (fn [env form] (apply min (rest form))))

(def ^:private max-system
  (m.primitive/fresh [?a ?b]
    (m.primitive/system `max
      [;; (max integer integer) => integer
       (m.primitive/rule
        (m.primitive/list (m.primitive/anything)
                          (m.primitive/each ?a (m.integer*/any))
                          (m.primitive/each ?b (m.integer*/any)))
        (m.integer*/max ?a ?b))

       ;; TODO: (max integer (max integer x)) => (max (max integer integer) x)

       ;; Default
       (m.primitive/rule
        (m.primitive/list (m.primitive/anything) ?a ?b)
        (m.primitive/list (m.primitive/is `max*) ?a ?b))])))

(defn max [a b]
  (let [istate (m.state/make {:object (list `max a b)})
        ilogic (m.logic/make-dff istate)
        result (m.protocols/-redex max-system ilogic)]
    (deref (m.protocols/-fmap result m.state/get-object))))

(m.env/operator-add! `max (fn [env form] (apply max (rest form))))
