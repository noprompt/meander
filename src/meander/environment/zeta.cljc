(ns meander.environment.zeta)

;; Cross platform namespace information
;; ---------------------------------------------------------------------

(def empty-ns-info
  {::namespace 'user
   ::requires {}
   ::refers {}
   ::interns #{}})

(defn cljs-env?
  "true if compiling ClojureScript or in a ClojureScript setting,
  false otherwise."
  [env]
  #?(:clj (some? (:ns env))
     :cljs true))

#?(:clj
   (defn cljs-ns-from-clj-ns
     "Given a `clojure.lang.Namespace` clj-ns, return a map which
  partially emulates the :ns key of a ClojureScript &env
  environment. The map has the structure

    {::name symbol?
     ::requires {symbol? qualified-symbol?}
     ::refers {symbol? qualified-symbol?}
     ::interns #{symbol?}

  This is used to provide a canonical format for symbol resolution in
  cases where using `*ns*` is inappropriate or does not work."
     [clj-ns]
     (let [requires (into {}
                          (map (fn [[k v]] [k (ns-name v)]))
                          (ns-aliases *ns*))
           required-ns-names (set (vals requires))
           refers (into {}
                        (keep (fn [[sym x]]
                                (if (var? x)
                                  (let [var-ns-name (ns-name (:ns (meta x)))]
                                    (if (contains? required-ns-names var-ns-name)
                                      [sym var-ns-name])))))
                        (ns-refers *ns*))
           interns (set (keys (ns-interns *ns*)))]
       {::namespace (ns-name *ns*)
        ::requires requires
        ::refers refers
        ;; TODO: Determine what CLJS calls this.
        ::interns interns})))

#?(:clj
   (defmacro ns-info
     "Return the current compile time canonical namespace data."
     []
     (if (cljs-env? &env)
       (let [{:keys [name requires uses]} (get &env :ns)]
         `(quote {::namespace ~name
                  ::requires ~requires
                  ::refers ~uses
                  ;; TODO: Determine how to fill this in.
                  ::interns #{}}))
       `(quote ~(cljs-ns-from-clj-ns *ns*)))))

(def operator-registry
  (atom {}))

;; Environment construction
;; ---------------------------------------------------------------------

(defn make-environment []
  (merge empty-ns-info {::operators (deref operator-registry)}))

(defmacro create
  ([]
   `(merge (make-environment) (ns-info)))
  ([overrides]
   `(merge (create) ~overrides)))

(defn qualify-symbol
  "Attempts to fully qualify the symbol in-symbol. If the
  qualification is successful the result is returned. If not, nil is
  returned."
  [environment in-symbol]
  (assert (symbol? in-symbol))
  (let [env-ns (::namespace environment)]
    ;; If the symbol is qualified, determine if the namespace
    ;; portition of the symbol is an alias or shares the same
    ;; name with the environment namespace.
    (if (qualified-symbol? in-symbol)
      (let [symbol-ns-symbol (symbol (namespace in-symbol))]
        (if (= env-ns symbol-ns-symbol)
          in-symbol
          (some (fn [[ns-alias ns-actual-name]]
                  (if (or (= symbol-ns-symbol ns-alias)
                          (= symbol-ns-symbol ns-actual-name))
                    (symbol (name ns-actual-name) (name in-symbol))))
                (::requires environment))))
      ;; If symbol is not qualified, qualify it as in-symbol* with
      ;; respect to the environment namespace and return it if it is a
      ;; registered operator.
      (let [in-symbol* (symbol (name env-ns) (name in-symbol))]
        (if-some [_ (find (::operators environment) in-symbol*)]
          in-symbol*)))))

;; Operator registry manipulation, look up, and expansion.
;; ---------------------------------------------------------------------

(defn operator-add! [symbol system]
  {:pre [(symbol? symbol)
         (fn? system)]}
  (swap! operator-registry assoc symbol system))

(defn operator-remove! [symbol]
  {:pre [(symbol? symbol)]}
  (swap! operator-registry dissoc symbol))

(defn operator-find [environment x]
  (if (symbol? x)
    (if-some [qualified-symbol (qualify-symbol environment x)]
      (find (::operators environment) qualified-symbol))))

(defn operator-expand [environment form]
  (if (seq? form)
    (if-some [[_ f] (operator-find environment (first form))]
      (if-some [{:keys [object]} (f form)]
        object
        form)
      form)
    form))


(defn operator-expand-all [environment form]
  (let [form* (operator-expand environment form)]
    (if (= form* form)
      form
      (recur environment form*))))
