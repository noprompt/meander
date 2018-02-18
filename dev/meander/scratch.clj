(ns meander.scratch
  (:require
   [clojure.core :as clj]
   [clojure.zip :as zip]
   [clojure.string :as string]
   [clojure.test :as t]
   [meander.core :as r]
   [meander.protocols :as protocols]))

;; ---------------------------------------------------------------------
;; Clojure AST


(def strip-ns
  (r/t ~x
    :when (qualified-symbol? x)
    :let [x* (symbol (name x))]
    ~x*))


(def strip-auto-gensym-tail
  (r/t ~x
    :when (simple-symbol? x)
    :let [x* (symbol (string/replace (name x) #"(.*)__auto__\z" "$1"))]
    ~x*))


(comment
  (let [form `(when (vector? x#)
                (let [y# (first x#)]
                  (when (number? y#)
                    (let [result# (+ y# y#)]
                      result#))))]
    ((r/bottom-up
      (r/choice strip-ns
                strip-auto-gensym-tail))
     form))
  ;; =>

  '(when (vector? x__24958)
     (let [y__24959 (first x__24958)]
       (when (number? y__24959)
         (let [result__24960 (+ y__24959 y__24959)]
           result__24960)))))


;; ---------------------------------------------------------------------
;; Clojure AST

(def dot-form-t1
  ;; (.foo bar baz)
  ;; =>
  ;; (. bar foo baz)
  (r/t (~method ~target ~@args)
    :when (symbol? method)
    :let [method-name (name method)]
    :when (re-find #"\A\.." method-name)
    :let [method* (symbol (subs method-name 1))]
    (. ~target ~method* ~@args)))


(def dot-form-t2
  ;; (. bar (foo baz))
  ;; =>
  ;; (. bar foo baz)
  (r/t (. ~target (~method ~@args))
    :when (symbol? method)
    (. ~target ~method ~@args)))


(def dot-form-t
  (r/choice dot-form-t1 dot-form-t2))

;; BUG
;; The replacement in the following pattern can cause a confusing
;; error: Wrong number of arguments passed to Symbol. The culprit
;; is @args missing the leading ~.
#_
(r/t (. ~target (~method ~@args))
     :when (symbol? method)
     (. ~target ~method @args))

(let [dot-form-1 '(. "foo" (substring 1))
      dot-form-2 '(.substring "foo" 1)]
  (dot-form-t1 dot-form-2))

;; ---------------------------------------------------------------------


(def prop-n-t
  (r/t (not ~x)
    (impl ~x false)))

(def prop-i-t
  (r/t (impl ~x ~y)
    (or (not ~x) ~y)))

(def prop-e-t
  (r/t (eq ~x ~y)
    (and (impl ~x ~y)
         (impl ~y ~x))))

(def prop-o1-t
  (r/t (or ~x ~y)
    (impl (not ~x) ~y)))

(def prop-o2-t
  (r/t (or ~x ~y)
    (not (and (not ~x) (not ~y)))))

(def prop-a1-t
  (r/t (and ~x ~y)
    (not (or (not ~x) (not ~y)))))

(def prop-a2-t
  (r/t (and ~x ~y)
    (not (impl ~x (not ~y)))))


(def prop-desugar
  (r/top-down (r/choice prop-i-t prop-e-t)))


;; module prop-desugar
;; imports prop libstrategolib
;; 
;; rules
;; 
;;   DefN  : Not(x)     -> Impl(x, False)
;;   DefI  : Impl(x, y) -> Or(Not(x), y)
;;   DefE  : Eq(x, y)   -> And(Impl(x, y), Impl(y, x))
;;   DefO1 : Or(x, y)   -> Impl(Not(x), y)
;;   DefO2 : Or(x, y)   -> Not(And(Not(x), Not(y)))
;;   DefA1 : And(x, y)  -> Not(Or(Not(x), Not(y)))
;;   DefA2 : And(x, y)  -> Not(Impl(x, Not(y)))
;; 
;;   IDefI : Or(Not(x), y) -> Impl(x, y)
;; 
;;   IDefE : And(Impl(x, y), Impl(y, x)) -> Eq(x, y)
;; 
;; strategies
;; 
;;   desugar = 
;;     topdown(try(DefI <+ DefE))
;; 
;;   impl-nf = 
;;     topdown(repeat(DefN <+ DefA2 <+ DefO1 <+ DefE))
;; 
;;   main-desugar = 
;;     io-wrap(desugar)
;;   
;;   main-inf =
;;     io-wrap(impl-nf)


;; ---------------------------------------------------------------------
;;

(declare clj-ast)

(def clj-number
  (r/t ~x
    :when (number? x)
    {:op :const
     :form ~x
     :literal? true
     :type :number
     :val ~x
     :children []}))

(def clj-keyword
  (r/t ~x
    :when (keyword? x)
    {:op :const
     :form ~x
     :literal? true
     :type :keyword
     :val ~x
     :children []}))


(def clj-scalar
  (r/choice clj-number
            clj-keyword))


(def clj-map
  (r/t ~x
    :when (map? x)
    :let [keys (mapv clj-ast (keys x))
          vals (mapv clj-ast (vals x))]
    {:op :map
     :form ~x
     :keys ~keys
     :vals ~vals}))


(def clj-vector
  (r/t ~x
    :when (vector? x)
    :let [items (mapv clj-ast x)]
    {:op :vector
     :form ~x
     :items ~items
     :children [:items]}))

(def clj-set
  (r/t ~x
    :when (set? x)
    :let [items (mapv clj-ast x)]
    {:op :set
     :form ~x
     :items ~items
     :children [:items]}))


(def clj-compound
  (r/choice clj-vector
            clj-map
            clj-set))


(def clj-def-1
  (r/t (def ~name ~init)
    :as form
    :let [init (clj-ast init)
          var (ns-resolve *ns* name)
          meta (when var (meta var))
          children (cond-> []
                     (some? meta)
                     (conj :meta)

                     (some? init)
                     (conj :init))]
    {:op :def
     :form ~form
     :name ~name
     :var ~var
     :meta ~meta
     :init ~init
     :children ~children}))


(def clj-def-2
  (r/t (def ~name ~docstring ~init)
    :as form
    :let [init (clj-ast init)
          var (ns-resolve *ns* name)
          meta (when var (meta var))
          children (cond-> []
                     (some? meta)
                     (conj :meta)

                     (some? init)
                     (conj :init))]
    {:op :def
     :form ~form
     :name ~name
     :var ~var
     :meta ~meta
     :init ~init
     :doc ~docstring
     :children ~children}))

(def clj-do
  (r/t (do ~@statements ~ret)
    :as form
    :let [statements (mapv clj-ast statements)
          ret (clj-ast ret)]
    {:op :do
     :env nil
     :form ~form
     :statments ~statements
     :ret ~ret
     :children [:statements :ret]}))

(def clj-def
  (r/choice clj-def-1
            clj-def-2))


(def clj-fn-1
  (r/t (fn* [~@args] ~@body)
    (fn* ([~@args] ~@body))))

(def clj-fn-binding
  (r/t ~x
    :when (simple-symbol? x)
    :let [arg-id (gensym "arg_id__")]
    {:op :binding
     :env nil
     :form ~x
     :name ~x
     :local :fn
     :arg-id ~arg-id
     :variadic? false}))

(def clj-fn-method
  (r/t ([~@params] ~@body)
    :as form
    :let [loop-id (gensym "loop__")
          variadic? (boolean (some '#{&} params))
          params (vec (map clj-fn-binding (remove '#{&} params)))
          params (if (seq params)
                   (conj (pop params)
                         (assoc (peek params) :variadic? variadic?))
                   params)
          fixed-arity (if (and (seq params) variadic?)
                        (count (pop params))
                        (count params))
          body (mapv clj-ast body)]
    {:op :fn-method
     :env nil
     :form ~form
     :loop-id ~loop-id
     :variadic? ~variadic?
     :params ~params
     :fixed-arity ~fixed-arity
     :body ~body
     :local nil
     :children [:params :body]}))


(def clj-fn-2
  (r/t (fn* ([~@args] ~@body) ~@rest-methods)
    :as form
    :let [methods (mapv clj-fn-method (rest form))
          variadic? (some :variadic? methods)
          max-fixed-arity (reduce max (map :fixed-arity methods))]
    {:op :fn
     :env nil
     :form ~form
     :variadic? ~variadic?
     :max-fixed-arity ~max-fixed-arity
     :methods ~methods
     :once nil
     :children [:methods]}))

(def clj-fn
  (r/repeat
   (r/choice clj-fn-1
             clj-fn-2)))

(def clj-special
  (r/choice clj-def
            clj-do
            clj-fn))


(def clj-ast
  (r/choice clj-special
            clj-scalar
            clj-compound))

(clj-ast
 '(def clj-ast
    (fn* [x y] (do 3 2 1))))


;; ---------------------------------------------------------------------
;; match? test helper

(defmethod t/assert-expr 'match? [msg form]
  (let [[_ p x] form
        u `u#
        v `v#]
    `(let [~u (r/parse-form '~p)
           ~v ~x]
       (if (r/unify ~u ~v)
         (do
           (t/do-report
            {:type :pass
             :message '~msg
             :expected '~p,
             :actual ~v})
           ~v)
         (t/do-report
          {:type :fail
           :diffs ~(let [p* (r/parse-form p)]
                     (if (and (find-ns 'clojure.data)
                              (not (some r/splicing-variable? (r/variables p*))))
                       `[[~v (clojure.data/diff (r/parse-form '~p) ~v)]]
                       []))
           :message '~msg
           :expected (r/parse-form '~p)
           :actual ~v})))))



(t/is (match? [{:a
                {:reason :requested
                 :date "2018-02-02"}}]
              [{:a
                {:date "2018-02-02"
                 :reason :requested}}]))

(t/is (match? [{:a
                {:reason :ordered
                 :date ~date}}]
              [{:a
                {:date "2018-02-02"
                 :reason :requested}}]))

(t/is (match? [~x 2] [1 2 3]))



