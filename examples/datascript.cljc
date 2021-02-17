(ns datascript
  (:require
   [meander.epsilon :as m]))

(def eid? (some-fn keyword? pos-int? string?))

(defn- apply-tx
  [db cache_ tx]
  (m/match tx
    ;; add ?id ?k ?v
    [:db/add (m/pred eid? ?id) (m/pred keyword? ?k) (m/pred some? ?v)]
    (assoc-in db [?id ?k] ?v)
    ;; add -?id ?k ?v
    [:db/add (m/pred neg-int? ?id) (m/pred keyword? ?k) (m/pred some? ?v)]
    (let [id (get @cache_ ?id (rand-int 1e6))]
      (assoc-in db [id ?k] ?v))
    ;; add m
    [:db/add {:db/id (m/or (m/pred eid? ?id) (m/let [?id (rand-int 1e6)])) :as ?m}]
    (assoc db ?id ?m)
    ;; retract ?id
    [:db/retract (m/pred eid? ?id)]
    (dissoc db ?id)
    ;; retract ?id ?k
    [:db/retract (m/pred eid? ?id) ?k]
    (update db ?id dissoc ?k)
    ;; retract {}
    [:db/retract {:db/id (m/pred eid? ?id)}]
    (dissoc db ?id)))

(defn transact
  ([db txs]
   (let [cache_ (atom {})]
     (reduce
      (fn [acc tx]
        (apply-tx acc cache_ tx))
      db
      txs))))

(def data
  [[:db/add
    {:db/id     :ivan
     :name      "Ivan"
     :last-name "Ivanov"
     :friend    :petr
     :age       30}]
   [:db/add
    {:db/id     :petr
     :name      "Petr"
     :last-name "Petrov"
     :friend    [:smith :ivan]
     :age       15}]
   [:db/add
    {:db/id     :smith
     :name      "Smith"
     :last-name "Smith"
     :friend    [:ivan]
     :age       55}]])

(def db (transact {} data))

db
;; => {:ivan  {:db/id     :ivan,
;;             :name      "Ivan",
;;             :last-name "Ivanov",
;;             :friend    :petr,
;;             :age       30},
;;     :petr  {:db/id     :petr,
;;             :name      "Petr",
;;             :last-name "Petrov",
;;             :friend    [:smith :ivan],
;;             :age       15},
;;     :smith {:db/id     :smith,
;;             :name      "Smith",
;;             :last-name "Smith",
;;             :friend    [:ivan],
;;             :age       55}}



(defn pull
  ([db selector id]
   (m/match id
     (m/pred eid? ?id)
     (some-> (pull db [{?id selector}]) (get ?id))
     [(m/pred eid? !ids) ...]
     (mapv #(pull db selector %) !ids)))
  ([db selector]
   (m/rewrite {:data     db
               :selector selector}
     ;; single join
     {:data     (m/pred eid? ?root)
      :selector ?k}
     (m/cata {:data     (m/cata {:data ~db
                                 :k    ?root})
              :selector ?k})
     ;; multiple join
     {:data     (m/or (m/pred eid? !roots) [(m/pred eid? !roots) ...])
      :selector ?k}
     [(m/cata {:data     (m/cata {:data ~db
                                  :k    !roots})
               :selector ?k}) ...]
     ;;
     {:data     {:as ?m}
      :selector [(m/pred keyword? !ks) ...]}
     ~(select-keys ?m !ks)
     ;;
     {:data {?k ?v :as ?db}
      :k    (m/pred keyword? ?k)}
     ?v
     {:data     {?k ?v :as ?db}
      :selector (m/pred keyword? ?k)}
     {?k ?v}
     ;;
     {:data     [{:as !maps} ...]
      :selector [(m/pred keyword? !ks) ...]}
     ~(mapv (fn [m] (select-keys m !ks)) !maps)
     ;;
     {:data     {:as ?db}
      :selector {?root ?more}}
     {& [[?root (m/cata {:data     ~(get ?db ?root)
                         :selector ?more})]]}
     ;;
     {:data     {:as ?db}
      :selector [!ks ...]}
     {& [(m/cata {:data     ?db
                  :selector !ks}) ...]})))

(pull db [:name :friend] :ivan)
;; => {:name "Ivan", :friend :petr}

;; join
(pull db [:name {:friend [:name]}] :ivan)
;; => {:name "Ivan", :friend {:name "Petr"}}

;; recursive joins
(pull db [:name {:friend [:name {:friend [:name {:friend [:name]}]}]}] :ivan)
;; => {:name "Ivan", :friend {:name "Petr", :friend [{:name "Smith", :friend [{:name "Ivan"}]} {:name "Ivan", :friend {:name "Petr"}}]}}

(defn datalog->meander [where]
  (loop [[elem & where*] where m {} fns [] vars []]
    (if elem
      (let [[m fns vars]
            (m/match elem
              [(m/pred (complement list?) ?e) ?k]
              [(update m ?e merge {:db/id ?e ?k `(m/some)}) fns vars]
              [(m/pred (complement list?) ?e) ?k ?v]
              [(update m ?e merge {:db/id ?e ?k ?v}) fns vars]
              [(?fn . !args ...)]
              [m (conj fns `(m/guard (apply ~?fn ~!args))) vars]
              [(?fn . !args ...) ?x]
              [m fns (conj vars `(m/let [~?x (~?fn ~@!args)]))])]
        (recur where* m fns vars))
      `(m/and ~m ~@fns ~@vars))))

(defn parse-query [q]
  (m/rewrite q
    [(m/pred keyword? ?k) . !args ... & ?more]
    {& [[?k [!args ...]] & (m/cata ?more)]}
    [] []))

(m/defsyntax query [args]
     (let [q (datalog->meander args)]
       `~q))

(defmacro q [q' db]
     (let [{:keys [where find]} (parse-query q')]
       `(doall
         (m/rewrites ~db
           ~(query where) ~@find))))

(q [:find ?e
    :where
    [?e :name "Ivan"]]
  db)
;; => (:ivan)

(q [:find [?e ?age]
    :where
    [?e :name "Ivan"]
    [?e :age ?age]]
  db)
;; => ([:ivan 30])

(q [:find [?name ?age]
    :where
    [?e :name ?name]
    [?e :age ?age]
    [(> ?age 30)]]
  db)
;; => (["Smith" 55])

(macroexpand-1
 '(q [:find [?name ?age]
     :where
     [?e :name ?name]
     [?e :age ?age]
     [(> ?age 30)]]
    db))
;; (doall
;;   (m/rewrites
;;     db
;;     (m/and
;;       {?e {:db/id ?e,
;;            :name  ?name,
;;            :age   ?age}}
;;       (m/guard (> ?age 30)))
;;     [?name ?age]))
