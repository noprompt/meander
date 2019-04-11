(ns meander.match.ir.delta
  "Functions for working with the Meander's match compiler
  intermediate representation (IR)."
  #?(:cljs (:require-macros [meander.match.ir.delta :refer [defop]]))
  (:require
   [clojure.zip :as zip]
   [meander.util.delta :as r.util]
   [meander.syntax.delta :as r.syntax]))

;; TODO: (TR) Inline bindings used once
;; TODO: (TR) Remove bindings never used
;; TODO: (TC) take and drop have :then
;; TODO: (TC) create :resolve node for symbols 
;; TODO: (TC) replace :eval with :resolve where possible

(defn child-keys [dt]
  (keep
   (fn [[k v]]
     (when (some? (:op v))
       k))
   dt))

(defn children [dt]
  (if (map? dt)
    (case (:op dt)
      :branch
      (:arms dt)

      ;; else
      (map dt (child-keys dt)))))

(defn branch? [dt]
  (some? (seq (children dt))))

(defn make-node [dt new-children]
  (case (:op dt)
    :branch
    (assoc dt :arms new-children)

    ;; else
    (into dt (map vector (child-keys dt) new-children))))

(defn dt-zip [dt]
  (zip/zipper branch? children make-node dt))


(defn height
  "Return the height of dt."
  [dt]
  (if-some [dts (children dt)]
    (transduce (comp (map height)
                     (map inc))
               max
               1
               dts)
    1))

(defn nodes
  "Return all nodes in dt."
  [dt]
  (tree-seq branch? children dt))

;; ---------------------------------------------------------------------
;; Tree nodes

(defmacro defop [symbol op params]
  (let [meta {:arglists `'(~params)
              :style/indent :defn} ]
    `(defn ~(vary-meta symbol merge meta) ~params
       (hash-map ~@(mapcat
                    (fn [param]
                      [(keyword (name param)) param])
                    params)
                 :op ~op))))

(defop op-bind :bind [symbol value then])

(defop op-branch :branch [arms])

(defop op-drop :drop [target n kind])

(defop op-nth :nth [target index])

(defop op-pass :pass [then])

(defop op-eval :eval [form])

(defop op-check :check [test target then])

(defop op-check-array :check-array [target then])

(defop op-check-array-equals :check-array-equals [target-1 target-2 then])

(defop op-check-boolean :check-boolean [test then])

(defop op-check-bounds :check-bounds [target length kind then])

(defop op-check-empty :check-empty [target then])

(defop op-check-equal :check-equal [target-1 target-2 then])

(defop op-check-lit :check-lit [target value then])

(defop op-check-map :check-map [target then])

(defop op-check-seq :check-seq [target then])

(defop op-check-set :check-set [target then])

(defop op-check-vector :check-vector [target then])

;; TODO: No need for :symbol.
(defop op-find :find [symbol value body])

(defop op-load :load [id])

(defop op-lvr-check :lvr-check [symbol target then])

(defop op-lvr-bind :lvr-bind [symbol target then])

(defop op-mvr-append :mvr-append [symbol target then])

(defop op-mvr-bind :mvr-bind [symbol target then])

(defop op-mvr-init :mvr-init [symbol then])

(defop op-save :save [id body-1 body-2])

;; TODO: No need for :symbol.
(defop op-search :search [symbol value body])

(defn op-star
  {:style/indent :defn}
  [input n kind return-symbols body-fn then]
  (let [input-symbol (gensym "input__")]
    {:op :star
     :input-symbol input-symbol
     :input input
     :body (body-fn input-symbol (op-eval (vec return-symbols)))
     :then then
     :kind kind
     :n n
     :return-symbols (vec return-symbols)}))

(defn op-plus
  {:style/indent :defn}
  [input n m kind return-symbols body-fn then]
  (let [input-symbol (gensym "input__")]
    {:op :plus
     :input-symbol input-symbol
     :input input
     :body (body-fn input-symbol (op-eval (vec return-symbols)))
     :then then
     :kind kind
     :n n
     :m m
     :return-symbols (vec return-symbols)}))

(defop op-take :take [target n kind])

(defop op-fail :fail [])

;; ---------------------------------------------------------------------
;; Code generation

(def FAIL
  "Special value returned by compiled :def nodes signaling a match
  failure. Compiled :call nodes check for this value."
  (reify))

(defn seq-bites [n coll]
  (if (seq coll)
    (lazy-seq
     (cons [(take n coll) (drop n coll)]
           (seq-bites n (drop n coll))))
    (list [() ()])))

(defn seq-bites-indexed
  ([n coll]
   (seq-bites-indexed n coll 0))
  ([n coll i]
   (if (seq coll)
     (lazy-seq
      (cons [(take n coll) (drop n coll) i]
            (seq-bites-indexed n (drop n coll) (inc i))))
     (list [() () 0]))))

(defn vec-bites
  [n coll]
  (if (seq coll)
    (map
     (fn [[a b]]
       (if b
         [(subvec coll a b) (subvec coll b)]
         [(subvec coll a) []]))
     (partition-all 2 1 (range 0 (count coll) n)))
    (list [[] []])))

(defn vec-bites-indexed
  [n coll]
  (if (seq coll)
    (map-indexed
     (fn [i [a b]]
       (if b
         [(subvec coll a b) (subvec coll b) i]
         [(subvec coll a) [] i]))
     (partition-all 2 1 (range 0 (count coll) n)))
    (list [[] [] 0])))

(defn js-array-equals-form
  "Form used to test if two arrays a and b are equal in
  ClojureScript."
  [a b]
  `(goog.array/equals ~a ~b
                      (fn f# [a# b#]
                        (if (cljs.core/array? a#)
                          (goog.array/equals a# b# f#)
                          (= a# b#)))))

(defn dt-fail?
  {:private true}
  [dt]
  (= (:op dt) :fail))

(defmulti emit*
  (fn [dt fail kind]
    (:op dt)))

(defmethod emit* :bind
  [dt fail kind]
  (loop [bindings []
         dt dt]
    (if (= (:op dt) :bind)
      (recur (conj bindings (:symbol dt) (emit* (:value dt) fail kind))
             (:then dt))
      `(let ~bindings
         ~(emit* dt fail kind)))))

(defmethod emit* :branch
  [dt fail kind]
  (case kind
    :search
    (let [arms (remove dt-fail? (:arms dt))]
      (case (count arms)
        0
        fail

        1
        (emit* (first arms) fail kind)

        ;; else
        `(concat
          ~@(map
             (fn [dt]
               (emit* dt fail kind))
             arms))))

    (:find :match)
    (let [arms (:arms dt)
          arms (if (= kind :find)
                 (remove dt-fail? arms)
                 arms)]
      (case (count arms)
        0
        fail

        1
        (emit* (first arms) fail kind)

        2
        (emit* (first arms)
               (emit* (second arms) fail kind)
               kind)

        ;; else
        (reduce
         (fn [fail arm]
           (emit* arm fail kind))
         fail
         (reverse arms))))))

(defmethod emit* :call
  [dt fail kind]
  `(let [x# (~(:symbol dt) ~(emit* (:target dt) fail kind) ~@(:req-syms dt))]
     (if (identical? x# FAIL)
       ~fail
       (let [[~@(:ret-syms dt)] x#]
         ~(emit* (:then dt) fail kind)))))

(defmethod emit* :check-array
  [dt fail kind]
  `(if (cljs.core/array? ~(emit* (:target dt) fail kind))
     ~(emit* (:then dt) fail kind)
     ~fail))

(defmethod emit* :check-array-equals
  [dt fail kind]
  `(if ~(js-array-equals-form
         (emit* (:target-1 dt) fail kind)
         (emit* (:target-2 dt) fail kind))
     ~(emit* (:then dt) fail kind)
     ~fail))

(defmethod emit* :check-boolean
  [dt fail kind]
  `(if ~(emit* (:test dt) fail kind)
     ~(emit* (:then dt) fail kind)
     ~fail))

(defmethod emit* :check-bounds
  [dt fail kind]
  (let [length (:length dt)
        target (emit* (:target dt) fail kind)
        test (case (:kind dt)
               (:map :set)
               `(<= ~length (count ~target))

               :seq
               `(= (bounded-count (inc ~length) ~target)
                   ~length)

               :vector
               `(= (count ~target) ~length))]
    `(if ~test
       ~(emit* (:then dt) fail kind)
       ~fail)))

(defmethod emit* :check-equal
  [dt fail kind]
  `(if (= ~(emit* (:target-1 dt) fail kind)
          ~(emit* (:target-2 dt) fail kind))
     ~(emit* (:then dt) fail kind)
     ~fail))

(defmethod emit* :check-empty
  [dt fail kind]
  `(if (not (seq ~(emit* (:target dt) fail kind)))
     ~(emit* (:then dt) fail kind)
     ~fail))

(defmethod emit* :check-lit
  [dt fail kind]
  `(if (= ~(emit* (:target dt) fail kind)
          ~(emit* (:value dt) fail kind))
     ~(emit* (:then dt) fail kind)
     ~fail))

(defmethod emit* :check-map
  [dt fail kind]
  `(if (map? ~(emit* (:target dt) fail kind))
     ~(emit* (:then dt) fail kind)
     ~fail))

(defmethod emit* :check-seq
  [dt fail kind]
  `(if (seq? ~(emit* (:target dt) fail kind))
     ~(emit* (:then dt) fail kind)
     ~fail))

(defmethod emit* :check-set
  [dt fail kind]
  `(if (set? ~(emit* (:target dt) fail kind))
     ~(emit* (:then dt) fail kind)
     ~fail))

(defmethod emit* :check-vector
  [dt fail kind]
  `(if (vector? ~(emit* (:target dt) fail kind))
     ~(emit* (:then dt) fail kind)
     ~fail))

(defmethod emit* :drop
  [dt fail kind]
  (if (= (:kind dt) :vector)
    `(subvec ~(emit* (:target dt) fail kind)
             ~(:n dt))
    `(drop ~(:n dt)
           ~(emit* (:target dt) fail kind))))

(defmethod emit* :def
  [dt fail kind]
  (loop [bindings []
         dt dt]
    (if (= (:op dt) :def)
      (recur (conj bindings
                   `(~(:symbol dt) [~(:target-arg dt) ~@(:req-syms dt)]
                     ~(emit* (:body dt) `FAIL kind)))
             (:then dt))
      `(letfn ~bindings
         ~(emit* dt fail kind)))))

(defmethod emit* :eval
  [dt fail kind]
  (:form dt))

(defmethod emit* :fail
  [dt fail kind]
  fail)

(defmethod emit* :find
  [dt fail kind]
  (let [result-sym (gensym "result__")
        test-fail-sym (gensym "fail__")]
    `(let [~test-fail-sym (reify)
           ~result-sym (reduce
                        (fn [~test-fail-sym ~(:symbol dt)]
                          (let [~result-sym ~(emit* (:body dt) test-fail-sym kind)]
                            (if (identical? ~result-sym ~test-fail-sym)
                              ~test-fail-sym
                              (reduced ~result-sym))))
                        ~test-fail-sym
                        ~(emit* (:value dt) fail kind))]
       (if (identical? ~result-sym ~test-fail-sym)
         ~fail
         ~result-sym))))

(defmethod emit* :load
  [dt fail kind]
  `(~(:id dt)))

(defmethod emit* :lvr-bind
  [dt fail kind]
  `(let [~(:symbol dt) ~(emit* (:target dt) fail kind)]
     ~(emit* (:then dt) fail kind)))

(defmethod emit* :lvr-check
  [dt fail kind]
  `(if (= ~(:symbol dt) ~(emit* (:target dt) fail kind))
     ~(emit* (:then dt) fail kind)
     ~fail))

(defmethod emit* :nth
  [dt fail kind]
  `(nth ~(emit* (:target dt) fail kind) ~(:index dt)))

(defmethod emit* :mvr-append
  [dt fail kind]
  (let [!symbol (:symbol dt)]
    `(let [~!symbol (conj ~!symbol ~(emit* (:target dt) fail kind))]
       ~(emit* (:then dt) fail kind))))

(defmethod emit* :mvr-init
  [dt fail kind]
  `(let [~(:symbol dt) []]
     ~(emit* (:then dt) fail kind)))

(defmethod emit* :pass
  [dt fail kind]
  (emit* (:then dt) fail kind))

(defmethod emit* :plus
  [dt fail kind]
  (let [input-sym (:input-symbol dt)
        return-syms (:return-symbols dt)
        minimum (:m dt)
        fail-sym (gensym "fail__")
        then-sym (gensym "then__")
        then-form (emit* (:then dt) fail kind)]
    `(let [~input-sym ~(emit* (:input dt) fail kind)
           ~fail-sym (reify)
           ~then-sym (fn [~return-syms] ~then-form)]
       (reduce
        (fn [~return-syms [~input-sym tail# i#]]
          (if (= (count ~input-sym) ~(:n dt))
            (let [result# ~(emit* (:body dt) fail-sym (case kind
                                                        :search :find
                                                        kind))]
              (if (identical? result# ~fail-sym)
                (reduced ~fail)
                (if (seq tail#)
                  result#
                  ;; Because we've successfully consumed and i will
                  ;; increment at the top of the next iteration, we
                  ;; need to use inc to check if we met the minimum.
                  (if (<= ~minimum (inc i#))
                    (reduced (~then-sym result#))
                    ~fail))))
            ;; Failed to consume
            (reduced
             (if (or (seq ~input-sym)
                     (seq tail#)
                     (< i# ~minimum))
               ~fail
               (~then-sym ~return-syms)))))
        ~(:return-symbols dt)
        ~(case (:kind dt)
           :seq
           `(seq-bites-indexed ~(:n dt) ~input-sym)

           :vector
           `(vec-bites-indexed ~(:n dt) ~input-sym))))))

(defmethod emit* :save
  [dt fail kind]
  (let [id (:id dt)
        body-1 (:body-1 dt)
        body-2 (:body-2 dt)]
    (if (and (= (:op body-2) :load)
             (= (:id body-2 id)))
      `(letfn [(~id [] ~fail)]
         ~(emit* body-1 `(~id) kind))
      (let [f-sym (gensym "f__")]
        `(letfn [(~id [] ~fail)
                 (~f-sym [] ~(emit* body-2 fail kind))]
           ~(emit* body-1 `(~f-sym) kind))))))

(defmethod emit* :search
  [dt fail kind]
  (case kind
    (:find :match)
    (emit* (assoc dt :op :find) fail kind)
    
    :search
    `(mapcat
      (fn [~(:symbol dt)]
        ~(emit* (:body dt) fail kind))
      ~(emit* (:value dt) fail kind))))

(defmethod emit* :star
  [dt fail kind]
  (let [input-sym (:input-symbol dt)
        return-syms (:return-symbols dt)
        fail-sym (gensym "fail__")
        then-sym (gensym "then__")
        then-form (emit* (:then dt) fail kind)]
    `(let [~input-sym ~(emit* (:input dt) fail kind)
           ~fail-sym (reify)
           ~then-sym (fn [~return-syms] ~then-form)]
       (reduce
        (fn [~return-syms [~input-sym tail#]]
          (if (= (count ~input-sym) ~(:n dt))
            (let [result# ~(emit* (:body dt) fail-sym (case kind
                                                        :search :find
                                                        kind))]
              (if (identical? result# ~fail-sym)
                (reduced ~fail)
                (if (seq tail#)
                  result#
                  (reduced (~then-sym result#)))))
            ;; Failed to consume
            (reduced
             (if (or (seq ~input-sym) (seq tail#))
               ~fail
               (~then-sym ~return-syms)))))
        ~(:return-symbols dt)
        ~(case (:kind dt)
           :seq
           `(seq-bites ~(:n dt) ~input-sym)

           :vector
           `(vec-bites ~(:n dt) ~input-sym))))))

(defmethod emit* :take
  [dt fail kind]
  (let [target-code (emit* (:target dt) fail kind)
        n (:n dt)]
    (case (:kind dt)
      :vector
      `(subvec ~target-code 0 (min (count ~target-code) ~n))

      :js-array
      `(.slice ~target-code 0 (min (.-length ~target-code) ~n))

      ;; else
      `(take ~n ~target-code))))


(defmethod emit* :default
  [dt fail kind]
  dt)

;; ---------------------------------------------------------------------
;; Tree rewriting

;; :def rewriting

(defn def-remove-unused [dt]
  (let [call-symbols (into #{}
                           (comp (filter (comp #{:call} :op))
                                 (map :symbol))
                           (nodes dt))]
    (loop [loc (dt-zip dt)]
      (if (zip/end? loc)
        (zip/root loc)
        (let [node (zip/node loc)]
          (recur
           (case (:op node)
             :def
             (if (contains? call-symbols (:symbol node))
               (zip/next loc)
               (zip/replace loc (:then node)))
             
             ;; else
             (zip/next loc))))))))

(defn rewrite-def [dt]
  (-> dt
      def-remove-unused))

;; :mvr rewriting

(defn rewrite-move-mvr-init-to-top-level [dt]
  (reduce
   (fn [_ loc]
     (let [node (zip/node loc)]
       (case (:op node)
         :mvr-init
         (let [dt* (zip/root (zip/edit loc :then))]
           (reduced (assoc node :then (rewrite-move-mvr-init-to-top-level dt*))))
         ;; else
         dt)))
   dt
   (r.util/zip-next-seq (dt-zip dt))))

;; :branch rewriting

(defn branch-flatten
  "Equivalent to the rewrite rule

    (rewrite
     (with [%arms [(or {:op :branch
                        :arms %arms}
                       {:op :fail}
                       !arms)
                   ...]]
       {:op :branch
        :arms %arms})
     ;; =>
     {:op :branch
      :arms [!arms ... {:op :fail}]})"
  {:private true}
  [dt]
  (case (:op dt)
    :branch
    (let [arms* (into [] (mapcat
                          (fn f [node]
                            (if (= (:op node) :branch)
                              (mapcat f (:arms node))
                              (list node))))                                
                      (:arms dt))
          arms* (if (some dt-fail? arms*)
                  (conj (into [] (remove dt-fail?) arms*)
                        (op-fail))
                  arms*)]
      (assoc dt :arms arms*))
    dt))

(defmulti branch-merge-checks*
  (fn [[a b]]
    (let [op-a (:op a)
          op-b (:op b)]
      (if (= op-a op-b)
        op-a
        ::default)))
  :default ::default)

(defmethod branch-merge-checks* ::default
  [[a b]]
  [a b])

(defmethod branch-merge-checks* :bind
  [[a b]]
  (if (= (:target a)
         (:target b))
    [(assoc a
            :then (op-bind (:symbol b) (op-eval (:symbol a))
                    (op-branch [(:then a)
                                (:then b)])))]
    [a b]))

(defmethod branch-merge-checks* :check-bounds
  [[a b]]
  (if (and (= (:target a)
              (:target b))
           (= (:length a)
              (:length b))
           (= (:kind a)
              (:kind b)))
    [(assoc a
            :then (op-branch [(:then a)
                              (:then b)]))]
    [a b]))

(defmethod branch-merge-checks* :check-array
  [[a b]]
  (if (= (:target a)
         (:target b))
    [(assoc a
            :then (op-branch [(:then a)
                              (:then b)]))]
    [a b]))

(defmethod branch-merge-checks* :check-map
  [[a b]]
  (if (= (:target a)
         (:target b))
    [(assoc a
            :then (op-branch [(:then a)
                              (:then b)]))]
    [a b]))

(defmethod branch-merge-checks* :check-lit
  [[a b]]
  (if (= (:target a)
         (:target b))
    [(assoc a
            :then (op-branch [(:then a)
                              (:then b)]))]
    [a b]))

(defmethod branch-merge-checks* :check-seq
  [[a b]]
  (if (= (:target a)
         (:target b))
    [(assoc a
            :then (op-branch [(:then a)
                              (:then b)]))]
    [a b]))

(defmethod branch-merge-checks* :check-vector
  [[a b]]
  (if (= (:target a)
         (:target b))
    [(assoc a
            :then (op-branch [(:then a)
                              (:then b)]))]
    [a b]))

(defn branch-merge-checks
  [dt]
  (case (:op dt)
    :branch
    (loop [arms (:arms dt)
           arms* []]
      (case (count arms)
        (0 1)
        (assoc dt :arms (into arms* arms))
        ;; else
        (let [[a b] (take 2 arms)
              [a* b*] (branch-merge-checks* [a b])]
          (if (some? b*)
            ;; Couldn't merge, consume a.
            (recur (drop 1 arms) (conj arms* a))
            ;; Could merge, drop b, push a*.
            (recur (cons a* (drop 2 arms)) arms*)))))
    ;; else
    dt))


(defn branch-one-arm
  [dt]
  (if (= (:op dt) :branch)
    (if (= (count (:arms dt)) 1)
      (recur (first (:arms dt)))
      dt)
    dt))

(defn rewrite-branch* [dt]
  (-> dt
      branch-flatten
      branch-merge-checks
      branch-one-arm))

(defn rewrite-branch [dt]
  (let [dt* (loop [loc (dt-zip dt)]
              (if (zip/end? loc)
                (zip/root loc)
                (let [node (zip/node loc)]
                  (case (:op node)
                    :branch
                    (let [arms (:arms node)]
                      (case (count arms)
                        0
                        (recur (zip/next loc))

                        1
                        (recur (zip/replace loc (first arms)))

                        ;; else
                        (recur (zip/next (zip/edit loc rewrite-branch*)))))

                    ;; else
                    (recur (zip/next loc))))))]
    (if (= dt dt*)
      dt
      (recur dt*))))

(defn rewrite [dt]
  (-> dt
      rewrite-def
      rewrite-branch))

(defn emit [dt fail kind]
  (emit* (rewrite dt) fail kind))
