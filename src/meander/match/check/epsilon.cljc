(ns ^:no-doc meander.match.check.epsilon
  (:require [clojure.set :as set]
            [clojure.spec.alpha :as s]
            [meander.match.syntax.epsilon :as r.match.syntax]
            [meander.syntax.epsilon :as r.syntax]))

(s/def :meander.match.epsilon.check-env/lvrs
  (s/coll-of :meander.syntax.epsilon.node/lvr :kind set?))

(s/def :meander.match.epsilon.check-env/mvrs
  (s/coll-of :meander.syntax.epsilon.node/mvr :kind set?))

(s/def :meander.match.epsilon.check-env/ref-map
  (s/keys :req-un [:meander.syntax.epsilon/node
                   :meander.syntax.epsilon.node/with]))

(s/def :meander.match.epsilon.check-env/refs
  (s/map-of :meander.syntax.epsilon.node/ref
            :meander.match.epsilon.check-env/ref-map))

(s/def :meander.match.epsilon/check-env
  (s/keys :req-un [:meander.match.epsilon.check-env/lvrs
                   :meander.match.epsilon.check-env/mvrs
                   :meander.match.epsilon.check-env/refs]))


(def empty-check-env
  {:lvrs #{}
   :mvrs #{}
   :refs {}
   :path []})


(defn unparse-check-env
  "Unparse all AST nodes in check-env."
  {:private true}
  [check-env]
  {:lvrs (into #{} (map r.syntax/unparse) (:lvrs check-env))
   :mvrs (into #{} (map r.syntax/unparse) (:mvrs check-env))
   :refs (into {} (map
                   (fn [[k v]]
                     [(r.syntax/unparse k)
                      {:node (r.syntax/unparse (:node v))
                       :with (r.syntax/unparse (:with v))}]))
               (:refs check-env))
   :path (into []
               (comp (keep
                      (fn [node]
                        (case (r.syntax/tag node)
                          (:prt :cat)
                          nil

                          node)))
                     (map r.syntax/unparse))
               (:path check-env))})

(defn add-refs
  {:private true}
  [check-env node]
  (if (r.syntax/with-node? node)
    (update check-env :refs merge (into {} (map
                                            (fn [binding]
                                              [(:ref binding)
                                               {:node (:pattern binding)
                                                :with node}]))
                                        (:bindings node)))
    check-env))


(defmulti check-ast
  "Validates node returning
  [:error [{:message string, :ex-data map?}]
    whenever validation fails.
  [:okay child-nodes new-env]
    whenever validation succeeds. child-nodes are the child nodes of
    node. new-env is env extended with variables that would be bound
    during the process of matching node but not it's children."
  {:arglists '([node env search?])}
  (fn [node env search?]
    (r.syntax/tag node))
  :default ::default)


(defmethod check-ast ::default
  [node env _]
  [:okay (r.syntax/children node) env])


(defn check-error?
  {:private true}
  [[tag _]]
  (= tag :error))


(defn check-okay?
  {:private true}
  [[tag _]]
  (= tag :okay))


(defn check*
  "Checks if node is valid with respect to env. Returns

  [:error [{:message string?, :ex-data map?} & syntax-trace]
    whenever an error is detected. syntax-trace is a sequence forms
    which represent the path to the invalid pattern from the leaf
    to the root.


  [:okay exit-env]
    whenever the node is valid. exit-env is a set of all
    logic and memory variables which would be bound by a successful
    pattern match; equivalent to (meander.syntax.epsilon/variables node)."
  [node env search?]
  (let [[tag :as result] (check-ast node env search?)]
    (case tag
      :error
      (let [[_ trace] result]
        [:error (conj trace node)])

      :okay
      (let [[_ children env] result]
        (reduce
         (fn [[_ env] child]
           (let [[tag :as result] (check* child env search?)]
             (case tag
               :error
               (let [[_ trace] result]
                 (reduced [:error (conj trace node)]))

               :okay
               result)))
         [:okay (update env :path conj node)]
         children)))))


(defn check
  "Checks if node is valid. Returns an instance of
  clojure.lang.Exception if an error can be found and nil otherwise."
  [node search?]
  (let [[tag :as result] (check* node empty-check-env search?)]
    (case tag
      :error
      (let [[_ [{:keys [message ex-data]} & trace]] result
            syntax-trace (into [] (comp (remove (comp #{:cat :prt} r.syntax/tag))
                                        (map r.syntax/unparse))
                               trace)]
        (ex-info message (assoc ex-data :syntax-trace syntax-trace)))

      :okay
      nil)))


(defmethod check-ast ::r.match.syntax/or
  [node env search?]
  (let [arguments (:arguments node)
        bound-lvrs (:lvrs env)
        ref-map (into {}
                      (map
                       (fn [[k v]]
                         [k (:node v)]))
                      (:refs env))
        argument-lvrs (map
                       (fn [node]
                         (let [node* (r.syntax/substitute-refs node ref-map)]
                           (set/difference (r.syntax/logic-variables node*)
                                           bound-lvrs)))
                       arguments)
        all-lvrs (reduce set/union #{} argument-lvrs)
        problems (sequence
                  (comp (map vector)
                        (keep
                         (fn [[term argument-lvrs]]
                           (let [absent-lvrs (set/difference all-lvrs argument-lvrs)]
                             (when (seq absent-lvrs)
                               [term absent-lvrs])))))
                  arguments
                  argument-lvrs)]
    (if (seq problems)
      [:error [{:message "Every pattern of an or pattern must have references to the same unbound logic variables."
                :ex-data {:env (unparse-check-env env)
                          :problems (mapv
                                     (fn [[pat absent-vars]]
                                       {:pattern (r.syntax/unparse pat)
                                        :absent (into #{} (map r.syntax/unparse) absent-vars)})
                                     problems)}}]]
      [:okay (r.syntax/children node) env])))


(defmethod check-ast :rp*
  [node env _]
  (let [unbound-lvrs (into #{} (remove (:lvrs env)) (r.syntax/logic-variables node))]
    (cond
      (seq unbound-lvrs)
      [:error [{:message "Zero or more patterns may not have references to unbound logic variables."
                :ex-data {:unbound (into #{} (map r.syntax/unparse) unbound-lvrs)}}]]

      (empty? (:elements (:cat node)))
      (let [dots '...]
        [:error
         [{:message (str "Zero or more (" dots ") is a postfix operator. It must have some value in front of it. (i.e. [1 " dots " ?x])")}]])

      :else
      [:okay (r.syntax/children node) env])))


(defmethod check-ast :rp+
  [node env _]
  (let [elements (:elements (:cat node))
        n (:n node)]
    (cond
      (nil? n)
      [:error [{:message "Ambiguous ellipsis. Perhaps you meant the n or more operator (..N) or the zero or more operator (...)?"}]]

      (empty? elements)
      [:error [{:message (str "N or more (..N) is a postfix operator. It must have some value in front of it. (i.e. [1 .." n " ?x])")}]]

      :else
      [:okay (r.syntax/children node) env])))


(defmethod check-ast :lvr
  [node env _]
  [:okay [] (update env :lvrs conj node)])

(defn non-search-lvars
  {:private true}
  [node env]
  (set/union
   env
   (reduce (fn [acc node]
             (case (r.syntax/tag node)
               :lvr (conj acc node)
               :map (set/union acc (non-search-lvars node env))
               nil))
           #{}
           (map second
                (filter (fn [[k v]] (r.syntax/ground? k)) (:map node))))))

(defn find-search-keys
  {:private true}
  [node env]
  (set/difference
   (set (remove r.syntax/ground? (keys (:map node))))
   env))

(defn all-search-keys
  {:private true}
  [node env]
  (set/difference
   (find-search-keys node env)
   (non-search-lvars node env)))

(defn find-search-keys-recursive [env [k v]]
  (concat
   (find-search-keys {:tag :map :map {k v}} env)
   (if (map? k) (mapcat (partial find-search-keys-recursive env) (:map k)) '())
   (if (map? v) (mapcat (partial find-search-keys-recursive env) (:map v)) '())))

(defn sort-by-search-keys [the-map env]
  (sort-by
   (comp count (partial find-search-keys-recursive env))
   the-map))

(defmethod check-ast :map
  [node env search?]
  (if search?
    [:okay (r.syntax/children node) env]
    (let [the-map (:map node)
          invalid-keys (seq (all-search-keys node (:lvrs env)))]
      (if invalid-keys
        [:error [{:message "When matching, map patterns may not contain variables in their keys that would make it so there is more than one match possible."
                  :ex-data {:keys (mapv r.syntax/unparse invalid-keys)}}]]
        [:okay (vals (sort-by-search-keys the-map (:lvrs env))) env]))))


(defmethod check-ast :mvr
  [node env _]
  [:okay [] (update env :mvrs conj node)])


(defmethod check-ast :prt
  [node env search?]
  (if search?
    [:okay (r.syntax/children node) env]
    (let [{left :left, right :right} node]
      (if (and (r.syntax/variable-length? left)
               (r.syntax/variable-length? right))
        [:error [{:message "When matching, a variable length subsequence pattern may not be followed by another variable length subsequence pattern."
                  :ex-data {}}]]
        [:okay (r.syntax/children node) env]))))


(defmethod check-ast :ref
  [node env search?]
  (if-some [entry (get (:refs env) node)]
    (let [other-node (:node entry)]
      (if (some
           (fn [prev-node]
             (identical? prev-node other-node))
           (:path env))
        [:okay [] env]
        [:okay [other-node] env]))
    [:error [{:message (str "Unbound reference " (r.syntax/unparse node))
              :ex-data {:env (unparse-check-env env)
                        :reference (r.syntax/unparse node)}}]]))


(defmethod check-ast :set
  [node env search?]
  (if search?
    [:okay (r.syntax/children node) env]
    (if (r.syntax/ground? node)
      [:okay [] env]
      [:error [{:message "Set patterns may not contain variables."
                :ex-data {}}]])))


(defmethod check-ast :wth
  [node env search?]
  (if-some [body (:body node)]
    (let [bindings (:bindings node)
          ref-freqs (into {} (filter
                              (fn [[_ n]]
                                (< 1 n)))
                          (frequencies (map :ref bindings)))]
      (if (seq ref-freqs)
        [:error [{:message "with patterns must have distinct references"
                  :ex-data {:env (unparse-check-env env)
                            :offending-bindings (into #{} (map r.syntax/unparse) (keys ref-freqs))}}]]
        [:okay [body] (add-refs env node)]))
    ;; Nothing to check.
    [:okay [] env]))