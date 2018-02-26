(ns meander.scratch
  (:require
   [clojure.core :as clj]
   [clojure.zip :as zip]
   [clojure.string :as string]
   [clojure.test :as t]
   [meander.core :as r]
   [meander.protocols :as protocols]))

;; ---------------------------------------------------------------------
;; Tools


(def strip-ns
  (r/guard qualified-symbol?
    (r/pipe name symbol)))


(defn strip-auto-gensym-tail
  [t]
  (if (simple-symbol? t)
    (symbol (string/replace (name t) #"(.*)__auto__\z" "$1"))
    t))


(comment
  (let [form `(when (vector? x#)
                (let [y# (first x#)]
                  (when (number? y#)
                    (let [result# (+ y# y#)]
                      result#))))]
    ((r/bottom-up (r/pipe strip-ns
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

(declare clj-ast)

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
  (= '(. "foo" substring 1)
     (dot-form-t dot-form-1)
     (dot-form-t dot-form-2)))

;; ---------------------------------------------------------------------
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




;; ---------------------------------------------------------------------
;; AST

(declare clj-ast)

;; TOOD: :type, :record, :class, :var
(defn clj-const [x]
  (let [type (cond
               (char? x)
               :char

               (boolean? x)
               :bool

               (keyword? x)
               :keyword

               (nil? x)
               :nil

               (number? x)
               :number

               (map? x)
               :map

               (instance? java.util.regex.Pattern x)
               :regex

               (set? x)
               :set

               (seq? x)
               :seq

               (string? x)
               :string

               (symbol? x)
               :symbol

               (vector? x)
               :vector

               :else
               :unkown)]
    {:op :const
     :form x
     :literal? true
     :type type
     :val x
     :children []}))

(defn at-key [k s]
  (fn [t]
    (if (map? t)
      (update t k s)
      t)))



;; Special

(def clj-def
  (r/repeat
   (r/choice
    (r/t (def ~name)
      (def ~name nil nil))
    (r/t (def ~name ~init)
      (def ~name nil ~init))
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
       :children ~children}))))


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
          params (mapv clj-fn-binding (remove '#{&} params))
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

(def clj-host-field
  (r/t (. ~target ~field)
    :as form
    :when (and (simple-symbol? field)
               (. (name field) startsWith "-"))
    :let [target* (clj-ast target)
          field* (symbol (subs (name field) 1))]
    {:op :host-field
     :env nil
     :form ~form
     :target ~target*
     :field ~field*
     :children [:target]}))

(def clj-host-call
  (r/t (. ~target ~method ~@args)
    :as form
    :when (and (simple-symbol? method)
               (not (. (name method) startsWith "-")))
    :let [target* (clj-ast target)
          args* (mapv clj-ast args)]
    {:op :host-call
     :env nil
     :form ~form
     :target ~target*
     :method ~method
     :args ~args*
     :children [:target :args]}))


(def clj-bad-host-expr
  (r/t (. ~target ~method ~@args)
    :as form
    {:op :bad-host-expr
     :form ~form}))


(def clj-if
  (r/t (if ~test ~then ~@rest)
    :as form
    :when (<= 0 (count rest) 1)
    :let [test* (clj-ast test)
          then* (clj-ast then)
          else* (clj-ast (first rest))]
    {:op :if
     :env nil
     :form ~form
     :test ~test*
     :then ~test*
     :else ~else*
     :children [:test :then :else]}))

(def clj-quote
  (fn [x]
    (if (seq? x)
      (let [[a expr & rest] x]
        (if (and (= a 'quote)
                 (not (seq rest)))
          {:op :quote
           :env nil
           :expr (clj-const expr)
           :form x
           :literal? true
           :chidren [:expr]}
          x))
      x))
  ;; TODO: This results in a bug because parse-form is handling this
  ;; wrong.
  #_
  (r/t (quote ~expr)
    :as form
    :let [expr* (clj-const expr)]
    {:op :quote
     :env nil
     :expr ~expr*
     :form ~form
     :literal? true
     :chidren [:expr]}))


(def clj-invoke
  (r/t (~f ~@args)
    :as form
    :let [fn (clj-ast f)
          args (mapv clj-ast args)
          meta (meta form)]
    {:op :invoke
     :env nil
     :fn ~fn
     :args ~args
     :meta ~meta
     :children [:fn :args]}))


(def clj-new
  (r/choice
   (r/t (new ~class ~@args)
     :as form
     :when (symbol? class)
     :let [args (mapv clj-ast args)]
     {:op :new
      :env nil
      :form ~form
      :class ~class
      :args ~args
      :children [:args]})
   (r/t (new ~@args)
     :as form
     {:op :bad-new
      :form ~form})))


(def clj-special
  (r/choice clj-def
            clj-do
            clj-fn
            clj-host-field
            clj-host-call
            clj-bad-host-expr
            clj-if
            clj-quote
            clj-new
            clj-invoke))


(def clj-compound
  (r/pipe (r/many clj-ast)
          (r/choice
           (r/t ~x
             :when ((some-fn vector? set?) x)
             :let [op (cond
                        (vector? x)
                        :vector

                        (set? x)
                        :set)
                   form (into (empty x)
                              (mapv (some-fn :form identity) x))]
             {:op ~op
              :env nil
              :form ~form
              :items ~x
              :children [:items]})
           (r/t ~x
             :when (map? x)
             :let [form (reduce-kv
                         (fn [form k v]
                           (assoc form (:form k) (:form v)))
                         {}
                         x)
                   keys (vec (keys x))
                   vals (vec (vals x))]
             {:op :map
              :env nil
              :form ~form
              :keys ~keys
              :vals ~vals
              :children [:keys :vals]}))))

(def clj-ast
  (r/many-td
   (r/choice (r/pipe macroexpand clj-ast)
             clj-special
             clj-compound
             clj-const)))


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



