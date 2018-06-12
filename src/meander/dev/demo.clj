(ns meander.dev.demo
  (:require
   [clojure.set :as set]
   [clojure.spec.alpha :as s]
   [clojure.string :as string]
   [clojure.java.io :as jio]
   [meander.dev.strategy :as r]
   [meander.dev.match :as r.match]
   [meander.dev.syntax :as r.syntax]))


;; ---------------------------------------------------------------------
;; Clojure AST

(declare clj=>ast)

(defn const=>ast [env]
  (fn [x]
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
       :children []})))


(defn def=>ast [env]
  (r/matcht
   ((def (? symbol? ?name) ?doc ?init) :as ?form)
   (let [init (clj=>ast env ?init)
         var (ns-resolve *ns* ?name)
         meta (when var (meta var))
         children (cond-> []
                    (some? meta)
                    (conj :meta)

                    (some? init)
                    (conj :init))]
     {:op :def
      :env env
      :form ?form
      :name ?name
      :var var
      :meta meta
      :init init
      :doc ?doc
      :children children})

   ((def (? symbol? ?name) ?init) :as ?form)
   (let [init (clj=>ast env ?init)
         var (ns-resolve *ns* ?name)
         meta (when var (meta var))
         doc (:doc meta)
         children (cond-> []
                    (some? meta)
                    (conj :meta)

                    (some? init)
                    (conj :init))]
     {:op :def
      :env env
      :form ?form
      :name ?name
      :var var
      :meta meta
      :init init
      :doc doc
      :children children})

   ((def (? symbol? ?name)) :as ?form)
   (let [var (ns-resolve *ns* ?name)
         meta (when var (meta var))
         doc (:doc meta)
         children (cond-> []
                    (some? meta)
                    (conj :meta))]
     {:op :def
      :env env
      :form ?form
      :name ?name
      :var var
      :meta meta
      :init nil
      :doc doc
      :children children})))


(defn do=>ast [env]
  (r/matcht
   (do)
   {:op :do
    :env env
    :form '(do)
    :statments []
    :ret nil
    :children [:statements :ret]}

   ((do . !statements ... . ?ret) :as ?form)
   (let [statements (mapv (clj=>ast env) !statements)
         ret (clj=>ast env ?ret)]
     {:op :do
      :env env
      :form ?form
      :statments statements
      :ret ret
      :children [:statements :ret]})))


(defn invoke=>ast [env]
  (r/matcht
   ((?f . !args ...) :as ?form)
   (let [fn (clj=>ast env ?f)
         args (mapv (clj=>ast env) !args)]
     {:op :invoke
      :env env
      :form ?form
      :fn fn
      :args args
      :meta (meta ?form)
      :children [:fn :args]})))


(defn quote=>ast [env]
  (r/matcht
   ('quote ?x)
   {:op :quote
    :env nil
    :expr ((const=>ast env) ?x)
    :form ?x
    :literal? true
    :chidren [:expr]}))


(defn new=>ast [env]
  (r/matcht
   ((new (? symbol? ?class) . !args ...) :as ?form)
   (let [args (mapv (clj=>ast env) !args)]
     {:op :new
      :env env
      :form ?form
      :class ?class
      :args args
      :children [:args]})))


(defn if=>ast [env]
  (r/matcht
   ((if ?test ?then ?else) :as ?form)
   (let [test (clj=>ast env ?test)
         then (clj=>ast env ?then)
         else (clj=>ast env ?else)]
     {:op :if
      :env env
      :form ?form
      :test test
      :then then
      :else else
      :children [:test :then :else]})

   ((if ?test ?then) :as ?form)
   (let [test (clj=>ast env ?test)
         then (clj=>ast env ?then)]
     {:op :if
      :env env
      :form ?form
      :test test
      :then then
      :else nil
      :children [:test :then]})))


(defn field-symbol? [x]
  (and (simple-symbol? x)
       (. (name x) startsWith "-")))


(defn host-field=>ast [env]
  (r/matcht
   (('. ?target ((? field-symbol?) :as ?field))
    :as ?form)
   (let [target (clj=>ast env ?target)
         field (symbol (subs (name ?field) 1))]
     {:op :host-field
      :env env
      :form ?form
      :target target
      :field field
      :children [:target]})))


(defn host-call=>ast [env]
  (r/matcht
   (('. ?target ((and (? simple-symbol?)
                      (? (complement field-symbol?)))
                 :as ?method) . !args ...)
    :as ?form)
   (let [target (clj=>ast env ?target)
         args (mapv (clj=>ast env) !args)]
     {:op :host-call
      :env env
      :form ?form
      :target target
      :method ?method
      :args args
      :children [:target :args]})))


(defn maybe-class=>ast [env]
  (r/pipe
   (r/pred
    (fn [t]
      (and (symbol? t)
           (Character/isUpperCase (. (name t) charAt 0)))))
   (fn [form]
     {:op :maybe-class
      :env env
      :form form
      :class form})))

(defn let-binding-node
  [env sym init]
  {:op :binding
   :env env
   :form sym
   :name sym
   :local :let
   :init init
   :children [:init]})

(defn clj=>ast*
  [env forms]
  (second
   (reduce
    (fn [[env* nodes] form]
      (let [node (clj=>ast env* form)]
        [(:env node env*) (conj nodes node)]))
    [env []]
    forms)))

(defn let=>ast [env]
  (r/matcht
   ((let* [(_ _ :as !bindings) ...] . !exprs ...) :as ?form)
   (loop [!bindings !bindings
          binding-nodes []
          env* env]
     (if-some [[[sym init] & !bindings*] (seq !bindings)]
       (let [binding-node (let-binding-node env* sym (clj=>ast env* init))]
         (recur !bindings*
                (conj binding-nodes )
                (update env* :locals (fnil conj #{}) sym)))
       {:op :let
        :env env
        :form ?form
        :bindings binding-nodes
        :body (clj=>ast* env* !exprs)
        :children [:bindings :body]}))))

#_
((let=>ast {}) '(let* [a 1 b 2] (+ a b)))

(defn clj=>ast
  ([env]
   (r/choice
    (do=>ast env)
    (def=>ast env)
    (new=>ast env)
    (if=>ast env)
    (quote=>ast env)
    (host-call=>ast env)
    (host-field=>ast env)
    (invoke=>ast env)
    (maybe-class=>ast env)
    (const=>ast env)))
  ([env clj-form]
   ((clj=>ast env) clj-form)))


;; ---------------------------------------------------------------------
;; Clojure AST => Ruby AS


(declare ast=>ruby)


(def ast=>ruby-ast
  (r/matcht
   {:op :def
    :name ?name
    :init ?init}
   (let [const-name (gensym "C__")]
     {:op :ruby/do
      :statements [{:op :ruby/casgn
                    :parent nil
                    :name const-name
                    :init (ast=>ruby-ast ?init)
                    :children [:init]}
                   {:op :ruby/def
                    :name (name ?name)
                    :params []
                    :body [{:op :ruby/const
                            :parent nil
                            :name const-name}]
                    :children [:params :body]}]
      :children [:statements]})

   {:op :host-call
    :method ?method
    :target ?target
    :args ?args}
   (let [target (ast=>ruby-ast ?target)
         args (map ast=>ruby-ast ?args)]
     {:op :ruby/send
      :method (str ?method)
      :target target
      :args args})

   ?x
   ?x))


(def ruby-ast=>string
  (r/matcht
   {:op :ruby/def
    :name ?name
    :params ?params
    :body ?body}
   (with-out-str
     (printf "def %s(%s)" ?name "_todo_")
     (println)
     (println
      (string/replace 
       (ruby-ast=>string
        {:op :ruby/do
         :statements ?body})
       #"(?m:(?=^))"
       "  "))
     (println "end"))

   {:op :ruby/do
    :statements ?statements}
   (string/join "\n" (map ruby-ast=>string ?statements))

   {:op :ruby/const
    :parent ?parent
    :name ?name}
   (if ?parent
     (str (ruby-ast=>string ?parent) "::" ?name)
     ?name)

   {:op :ruby/casgn
    :parent ?parent
    :name ?name
    :init ?init}
   (format "%s = %s"
           (if ?parent
             (str (ruby-ast=>string ?parent) "::" ?name)
             ?name)
           (ruby-ast=>string ?init))

   {:op :maybe-class
    :class ?class}
   (str ?class)

   {:op :ruby/send
    :method ?method
    :target ?target
    :args ?args}
   (format "(%s).%s(%s)"
           (ruby-ast=>string ?target)
           ?method
           "_todo_")))


;; ---------------------------------------------------------------------
;; CEK machine


(def
  ^{:arglists '([[c ρ κ]])
    :doc "CEK machine step function. Takes a CEK triple ς and returns
    the subsequent CEK triple ς′. If ς = ς′ the machine is in a halted
    state (or the input was invalid)."}
  cek-step
  (r/matcht
   ;; ⟨x, ρ, κ⟩ --> ⟨v, ρ′, κ⟩ where ρ(x) = (v, ρ′)
   [(? symbol? ?x) ?ρ ?k]
   (if-some [[_ [v ρ*]] (find ?ρ ?x)]
     [v ρ* ?k]
     [?x ?ρ [:error ?x ?k]])

   ;; ⟨(e₀ e₁), ρ, κ⟩ --> ⟨e₀, ρ, ar(e₁, ρ, κ)⟩
   [(?e₀ ?e₁) ?ρ ?k]
   [?e₀ ?ρ [:ar ?e₁ ?ρ ?k]]

   ;; ⟨v, ρ, ar(e, ρ′, κ)⟩ --> ⟨e, ρ′, fn(v, ρ, κ)⟩
   [?v ?ρ [:ar ?e ?ρ* ?k]]
   [?e ?ρ* [:fn ?v ?ρ ?k]]

   ;; ⟨v, ρ, fn((λx.e), ρ′, κ)⟩ -->  ⟨e, ρ′[x → (v, ρ)], κ⟩
   [?v ?ρ [:fn (fn [?x] ?e) ?ρ* ?k]]
   [?e (assoc ?ρ* ?x [?v ?ρ]) ?k]

   ;; Default, no action.
   ?ς
   ?ς))


;; ---------------------------------------------------------------------
;; Form search


(require '[clojure.tools.reader :as read])
(require '[clojure.tools.reader.reader-types :as read.types])


(defn alias-map
  "When passed an (ns ,,,) form attempt to derive a map from ns-alias
  symbol to ns symbol. Returns nil fails if such a map cannot be
  derived."
  [form]
  ;; TODO: (require ,,,)
  (r.match/when-match form
    ;; NOTE: For some reason ns needs to be quoted but I don't know
    ;; why. The macro expansion correctly emits (list 'ns) but if left
    ;; unquoted causes issues. This seems to be a problem when using
    ;; cider and does not seem to be a problem when used at the
    ;; command line REPL.
    ('ns . (_ ... :as ?form))
    (r.match/when-match (s/conform :clojure.core.specs.alpha/ns-form ?form)
      {:clauses ?clauses}
      (into {}
            (mapcat
             (fn [clause]
               (r.match/when-match clause
                 [:require {:body ?body}]
                 (map
                  (fn [x]
                    (r.match/when-match x
                      [:libspec
                       [:lib+opts {:lib ?ns-sym
                                   :options {:as ?ns-alias}}]]
                      [?ns-alias ?ns-sym]))
                  ?body))))
            ?clauses))
    ;; TODO
    #_ (require . !args ...)))


(defn read-forms-from-file
  [^java.io.File file]
  (let [source (.getCanonicalPath file)
        rdr (java.io.FileReader. file)
        pbr (java.io.PushbackReader. rdr)
        pbr (read.types/source-logging-push-back-reader pbr)
        eof (Object.)
        ;; This environment is for seq.
        env1 (volatile! {})
        ;; This environment is for reduce.
        env2 (volatile! {})]
    (reify
      clojure.lang.Seqable
      (seq [this]
        ((fn step []
           (let [form (binding [read/*alias-map* @env1]
                        (read/read {:eof eof} pbr))
                 form (if-some [m (meta form)]
                        (vary-meta form assoc :source source)
                        form)]
             (when-some [m (alias-map form)]
               (vswap! env1 merge m))
             (if (identical? form eof)
               ()
               (lazy-seq (cons form (step))))))))

      clojure.lang.IReduceInit
      (reduce [this f init]
        (loop [acc init]
          (if (reduced? acc)
            acc
            (let [form (binding [read/*alias-map* @env2]
                         (read/read {:eof eof} pbr))
                  form (if-some [m (meta form)]
                         (vary-meta form assoc :source source)
                         form)]
              (when-some [m (alias-map form)]
                (vswap! env2 merge m))
              (if (identical? form eof)
                acc
                (recur (f acc form))))))))))


(defn cljq
  ([query]
   (fn [forms]
     (cljq query forms)))
  ([query forms]
   (let [result-sym (gensym "?res__")
         qvars (map r.syntax/data
                    (r.syntax/variables
                     (r.syntax/parse query)))
         query* (list query :as result-sym)
         s (eval
            `(r/extract
              (fn [t#]
                (try
                  ((r/matcht
                    ~query*
                    [(assoc (meta ~result-sym)
                            :tag :match/pattern
                            :match ~result-sym)
                     ~@(map
                        (fn [qvar]
                          `(assoc (meta ~qvar)
                                  :tag :match/var
                                  :var '~qvar
                                  :match ~qvar)) 
                        qvars)])
                   t#)
                  (catch Exception e#
                    r/*fail*)))))]
     (into #{}
           (comp (mapcat s)
                 (mapcat identity))
           forms))))


(defn cljq-index-key
  "Returns the index key which corresponds to form."
  [form]
  ;; There is a bug(?) in the pattern match compiler which causes the
  ;; patterns in this match to be skipped if any of
  ;;
  ;;   (?pat ':as _)
  ;;   ('and . _ ...)
  ;;   ('? . _ ...)
  ;;
  ;; succeed in the seq? check they compile to.
  (r.match/match form
    (? seq?)
    (r.match/match form
      (?pat ':as _)
      (cljq-index-key ?pat)

      ('and . _ ...)
      :all

      ('? . _ ...)
      :all

      _
      :seq)

    (? vector?)
    :vec

    (? map?)
    :map

    (? set?)
    :set

    (? symbol?)
    :sym

    (? keyword?)
    :key

    _
    :all))


(defn cljq-index-forms
  [forms]
  (let [all-forms (vec (tree-seq
                        (every-pred (complement map-entry?) coll?)
                        seq
                        (vec forms)))]
    (assoc (group-by cljq-index-key all-forms)
           :all all-forms)))


(def cljq-app-state
  (atom {}))


(defn cljq-file-index
  "Returns the current form index for file."
  [^java.io.File file]
  (let [file-name (.getCanonicalPath file)]
    (if-some [[_ [last-known-mod-time index]] (find @cljq-app-state file-name)]
      (let [current-mod-time (.lastModified file)]
        (if (not= last-known-mod-time current-mod-time)
          (let [index* (cljq-index-forms (read-forms-from-file file))]
            (prn {:cljq/event :cljq.index/update
                  :cljq.index/name file-name})
            (swap! cljq-app-state assoc file-name [current-mod-time index*])
            index*)
          index))
      (let [current-mod-time (.lastModified file)
            index* (cljq-index-forms (read-forms-from-file file))]
        (prn {:cljq/event :cljq.index/create
              :cljq.index/name file-name})
        (swap! cljq-app-state assoc file-name [current-mod-time index*])
        index*))))


(defn cljq-file
  "Execute a cljq query against file."
  [q ^java.io.File file]
  (cljq q (get (cljq-file-index file) (cljq-index-key q))))


(defn cljq-dir
  "Execute a cljq query against a directory."
  [q ^java.io.File dir]
  (sequence
   (comp
    (filter
     (fn [^java.io.File f]
       (and (.isFile f)
            (.endsWith (.getName f) ".clj"))))
    (mapcat
     (fn [f]
       (cljq-file q f))))
   (file-seq dir)))


(defn cljq-pattern-match?
  [x]
  (and (map? x)
       (if-some [[_ tag] (find x :tag)]
         (= tag :match/pattern)
         false)))


(defn cljq-var-match?
  [x]
  (and (map? x)
       (if-some [[_ tag] (find x :tag)]
         (= tag :match/var)
         false)))


(defn symbol-like-fn
  ([re]
   (fn [x]
     (and (symbol? x)
          (re-find re (str x))))))
