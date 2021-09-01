(ns schema
  (:require
   [meander.epsilon :as m]
   [meander.strategy.epsilon :as m*]))

(def registry-ref (atom {}))

(defn defspec [k v]
  (m/match k
    (m/keyword _ _ :as ?k)
    (swap! registry-ref assoc ?k v)))

(defn rewrite-spec [spec]
  (m/rewrite spec
    [:and . (m/cata !xs) ...]
    (m/and . !xs ...)

    [:or . (m/cata !xs) ...]
    (m/or . !xs ...)

    [:map . [!ks (m/cata !vs)] ...]
    {& [[!ks !vs] ...]}

    [:vector (m/cata ?x)]
    [?x ~'...]

    [:tuple . (m/cata !xs) ...]
    [!xs ...]

    [:re ?x]
    (m/re ?x)

    [(m/and (m/keyword _ _ :as ?k)
            (m/some (m/guard (@registry-ref ?k)))) . !opts ...]
    (m/pred (partial (@registry-ref ?k) . !opts ...))

    (m/and (m/keyword _ _ :as ?k)
           (m/some (m/guard (@registry-ref ?k))))
    (m/cata ~(@registry-ref ?k))

    (m/or (m/symbol _ _ :as ?x)
          (m/pred fn? ?x))
    (m/pred ?x)

    ?x ?x))

(defmacro valid? [spec v]
  `(m/match ~v
     ~(rewrite-spec spec) true
     ~'_ false))

(valid? number? 1)
;; => true
(valid? [:and number? pos?] 1)
;; => true
(valid? [:or number? string?] 1)
;; => true
(valid? [:map [:a number?] [:b string?]] {:a 1 :b "2"})
;; => true
(valid? [:vector number?] [1 2 3])
;; => true
(valid? [:vector [:and number? even?]] [2 4 6])
;; => true
(valid? [:tuple number? string?] [1 "2"])
;; => true

(defspec :string
  (fn str?
    ([x] (str? {} x))
    ([{:keys [min max] :or {min 0 max 999}} x]
     (m/match x
       (m/and (m/pred string? ?x)
              (m/guard (and (>= (count ?x) min)
                            (<  (count ?x) max))))
       true
       _ false))))

(valid? :string "foo")
;; => true
(valid? [:string {:min 1}] "foo")
;; => true
(valid? [:string {:min 10}] "foo")
;; => false

;; classic meander still works
(valid? {:x (m/pred number?)} {:x 1})
;; => true
