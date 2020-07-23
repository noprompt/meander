(ns meander.interpreter.epsilon
  (:require [clojure.zip :as zip]
            [meander.environment.epsilon :as m.environment]
            [meander.util.epsilon :as m.util]
            [meander.match.epsilon :as m.match]
            [meander.match.syntax.epsilon :as m.match.syntax]
            [meander.syntax.epsilon :as m.syntax]))


;; Private API
;; -----------

(declare search-fn)

(defn pass
  {:private true}
  [target bindings]
  (return bindings))

(defn fail
  {:private true}
  [target bindings]
  ())

;; Tree patterns

(defn search-fn-and
  {:private true}
  ([search-fn-a]
   search-fn-a)
  ([search-fn-a search-fn-b]
   (fn [x bindings]
     (mapcat
      (fn [bindings]
        (search-fn-b x bindings))
      (search-fn-a x bindings)))))

(defn search-fn-or
  {:private true}
  ([search-fn-a]
   search-fn-a)
  ([search-fn-a search-fn-b]
   (fn [x bindings]
     (m.util/mix (search-fn-a x bindings)
                 (search-fn-b x bindings)))))

;; Leaf patterns

(defn search-fn-literal
  {:private true}
  [x return reject]
  (fn [target bindings]
    (if (= target x)
      (return bindings)
      (reject bindings))))

(defn search-fn-logic-variable
  {:private true}
  [v return reject]
  (fn [x bindings]
    (if-some [[_ y] (find bindings v)]
      (if (= y x)
        (return bindings)
        (reject bindings))
      (return (assoc bindings v x)))))

(defn search-fn-memory-variable
  {:private true}
  [v return]
  (fn [x bindings]
    (let [xs (if-some [e (find bindings v)]
               (val e)
               [])
          xs (conj xs x)]
      (return (assoc bindings v xs)))))

(defn search-fn-mutable-variable
  {:private true}
  [v return]
  (fn [x bindings]
    (return (assoc bindings v x))))

(defn search-fn-pred
  {:style/indent 1
   :private true}
  [p search-fn reject]
  (fn [x bindings]
    (if (p x)
      (search-fn x bindings)
      (reject bindings))))

(defn search-fn-partition
  {:private true}
  [search-fn-left search-fn-right]
  (fn [target bindings]
    (mapcat
     (fn [partition]
       (let [a (nth partition 0)
             b (nth partition 1)]
         (mapcat
          (fn [bindings]
            (search-fn-right b bindings))
          (search-fn-left a bindings))))
     (m.util/partitions 2 target))))

(defn run-cat
  {:arglists '([search-fns xs bindings return reject])
   :private true}
  [fs xs bindings return reject]
  (if-some [[f & rest-fs] (seq fs)]
    (let [[x & rest-xs] xs]
      (mapcat
       (fn [bindings]
         (run-cat rest-fs rest-xs bindings return reject))
       (f x bindings)))
    (if (seq xs)
      (reject bindings)
      (return bindings))))

(defn search-fn-cat
  {:private true}
  [length search-fns return reject]
  (let [length+1 (inc length)]
    (fn [target bindings]
      (if (= (bounded-count length+1 target) length)
        (run-cat search-fns target bindings return reject)
        (reject bindings)))))

(defn entry-search-fns
  {:private true}
  [entry-asts env return reject]
  (map (fn [e]
         (let [k-ast (key e)
               v-ast (val e)
               k-search-fn (search-fn k-ast env return reject)
               v-search-fn (search-fn v-ast env return reject)]
           (fn [target bindings]
             (let [k (key target)
                   v (val target)]
               (mapcat (fn [bindings]
                         (v-search-fn v bindings))
                       (k-search-fn k bindings))))))
       entry-asts))

(defn search-fn
  {:private true}
  [ast env return reject]
  (m.match/match ast
    {:tag :any}
    return

    {:tag :cat, :elements ?elements}
    (search-fn-cat (count ?elements)
                   (map (fn [element]
                          (search-fn element env return reject))
                        ?elements)
                   return
                   reject)

    {:tag :ctn, :context {:as ?context} :pattern ?pattern}
    (let [context-search-fn (search-fn ?context env reject)
          pattern-search-fn (search-fn ?pattern env reject)]
      (fn [target bindings]
        (mapcat
         (fn [loc]
           (let [node (zip/node loc)
                 edit (fn [x]
                        (zip/root (zip/replace loc x)))]
             (mapcat
              (fn [bindings]
                (context-search-fn edit bindings))
              (pattern-search-fn node bindings))))
         (m.util/zip-next-seq (m.util/coll-zip target)))))

    {:tag :ctn, :context nil :pattern ?pattern}
    (let [pattern-search-fn (search-fn ?pattern env reject)]
      (fn [target bindings]
        (mapcat
         (fn [x]
           (pattern-search-fn x bindings))
         (m.util/coll-seq target))))

    {:tag :drp}
    return
    
    {:tag :lit, :value ?value}
    (search-fn-literal ?value return reject)

    {:tag :lvr, :symbol ?symbol}
    (search-fn-logic-variable ?symbol return reject)

    ;; Map
    ;; ---

    {:tag :map ':as nil :rest-map nil :map {:as ?map}}
    (let [k (count ?map)
          entry-search-fns (entry-search-fns ?map env reject)]
      (search-fn-pred map?
        (fn [target bindings]
          (if (<= k (count target))
            (mapcat
             (fn [selection]
               (run-cat entry-search-fns selection bindings reject))
             (m.util/k-permutations target k))
            (reject bindings)))
        reject))

    {:tag :map ':as nil :rest-map {:as ?rest-map} :map {:as ?map}}
    (let [k (count ?map)
          entry-search-fns (entry-search-fns ?map env reject) 
          rest-search-fn (search-fn ?rest-map env reject)]
      (search-fn-pred map?
        (fn [target bindings]
          (if (<= k (count target))
            (mapcat
             (fn [pair]
               (let [selection (nth pair 0)
                     rest (nth pair 1)]
                 (mapcat
                  (fn [bindings]
                    (rest-search-fn rest bindings))
                  (run-cat entry-search-fns selection bindings))))
             (m.util/map-k-permutations-with-unselected target k))
            (reject bindings)))
        reject))

    {:tag :map ':as ?as :rest-map ?rest-map}
    (search-fn-and (search-fn ?as env return reject)
                   (search-fn (assoc ast :as nil) env return reject))

    {:tag :mvr :symbol ?symbol}
    (search-fn-memory-variable ?symbol)

    {:tag :prt, :left ?left, :right ?right}
    (search-fn-partition (search-fn ?left env return reject) (search-fn ?right env return reject))

    {:tag :ref, :symbol ?symbol}
    (if-some [e (find env ?symbol)]
      (val e)
      (throw (ex-info (str "Unbound reference: " ?symbol) {:meta (meta ?symbol)})))

    {:tag :seq, :prt ?prt, ':as ?as}
    (if (some? ?as)
      (search-fn-and (search-fn ?as env return reject)
                     (search-fn-pred vector? (search-fn ?prt env return reject) reject))
      (search-fn-pred vector? (search-fn ?prt env return reject) reject))

    {:tag :mut :symbol ?symbol}
    (search-fn-mutable-variable ?symbol)

    {:tag :rp* :cat {:elements ?elements :as ?cat}}
    (let [k (count ?elements)
          search-fn-cat (search-fn ?cat env return reject)]
      (fn search-fn-star [target bindings]
        (if (seq target)
          (let [front (take k target)]
            (if (= k (count front))
              (let [back (drop k target)]
                (mapcat
                 (fn [bindings]
                   (search-fn-star back bindings))
                 (search-fn-cat front bindings)))
              (reject bindings)))
          (return bindings))))

    {:tag :rp+, :n ?n, :cat {:elements ?elements :as ?cat}}
    (search-fn {:tag :prt
                :left {:tag :cat
                       :elements (reduce concat () (repeat ?n ?elements))}
                :right {:tag :rp*, :cat ?cat}})
    
    {:tag :rpl, :lvr {:symbol ?symbol :as ?lvr}, :cat {:elements ?elements :as ?cat}}
    (let [search-fn-star (search-fn {:tag :rp* :cat ?cat} env return reject)]
      (fn [target bindings]
        (let [m (count target)]
          (if-some [e (find bindings ?symbol)]
            (let [n (val e)]
              (if (= n m)
                (search-fn-star target bindings)
                (reject bindings)))
            (search-fn-star target (assoc bindings ?symbol m))))))

    {:tag :rpm, :mvr {:symbol ?symbol :as ?lvr}, :cat {:elements ?elements :as ?cat}}
    (let [search-fn-star (search-fn {:tag :rp* :cat ?cat} env return reject)]
      (fn [target bindings]
        (let [m (count target)
              xs (if-some [e (find bindings ?symbol)]
                   (val e)
                   [])
              xs (conj xs m)]
          (search-fn-star target (assoc bindings ?symbol xs)))))

    {:tag :rst, :mvr {:symbol ?symbol}}
    (fn [target bindings]
      (let [xs (if-some [e (find bindings ?symbol)]
                 (val e)
                 [])
            xs (into xs target)]
        (return (assoc bindings ?symbol xs))))

    {:tag :set ':as {:as ?as}}
    (search-fn-and (search-fn ?as env return reject)
                   (search-fn (assoc ast :as nil) env return reject))

    {:tag :set ':as nil :rest nil :elements ?elements :as ?set}
    (let [k (count ?elements)
          element-search-fns (map (fn [element]
                                    (search-fn element env return reject))
                                  ?elements)
          search-fn-set (fn [target bindings]
                          (if (<= k (count target))
                            (mapcat
                             (fn [selection]
                               (run-cat element-search-fns selection bindings return reject))
                             (m.util/k-permutations target k))
                            (reject bindings)))]
      (if (= set? (get env ?set))
        search-fn-set
        (search-fn-pred set? search-fn-set reject)))

    {:tag :set ':as nil :rest ?rest :elements ?elements :as ?set}
    (let [k (count ?elements)
          element-search-fns (map (fn [element]
                                    (search-fn element env return reject))
                                  ?elements)
          rest-search-fn (search-fn ?rest (assoc env ?rest set?) return reject)
          search-fn-set (fn [target bindings]
                          (if (<= k (count target))
                            (mapcat
                             (fn [pair]
                               (let [selection (nth pair 0)
                                     rest (nth pair 1)]
                                 (mapcat
                                  (fn [bindings]
                                    (rest-search-fn rest bindings))
                                  (run-cat element-search-fns selection bindings return reject))))
                             (m.util/set-k-permutations-with-unselected target k))
                            (reject bindings)))]
      (if (= set? (get env ?set))
        search-fn-set
        (search-fn-pred set? search-fn-set reject)))

    {:tag :tail, :pattern ?pattern}
    (search-fn ?pattern env)

    {:tag :unq, :form ?form}
    (if-some [e (find env ::eval)]
      (let [eval (val e)]
        (fn [target bindings]
          (if (= target (eval ?form))
            (return bindings)
            (reject bindings))))
      (throw (ex-info "Unable to resolve eval" {})))

    {:tag :vec, :prt ?prt, ':as nil}
    (search-fn-pred vector? (search-fn ?prt env return reject) reject)

    {:tag :vec, :prt ?prt, ':as ?as}
    (search-fn-and (search-fn ?as env return reject)
                   (search-fn-pred vector? (search-fn ?prt env return reject) reject))

    {:tag :wth, :bindings ?bindings, :body {:as ?body}}
    (let [ref-bindings (reduce
                        (fn [ref-bindings binding]
                          (m.match/match binding
                            {:ref {:symbol ?symbol}, :pattern ?pattern}
                            (assoc ref-bindings ?symbol (search-fn ?pattern env return reject))))
                        {}
                        ?bindings)
          search-fn-body (search-fn ?body (merge env ref-bindings) return reject)]
      (fn [target bindings]
        (search-fn-body target bindings)))

    {:tag :wth, :bindings _, :body _}
    pass

    {:tag ::m.match.syntax/and, :arguments ?arguments}
    (if (seq ?arguments)
      (reduce search-fn-and
              (map (fn [argument]
                     (search-fn argument env return reject))
                   ?arguments))
      pass)

    {:tag ::m.match.syntax/apply, :function ?function, :argument ?argument}
    (let [eval (get env ::eval)
          search-fn-argument (search-fn ?argument env return reject)]
      (if-some [e (find env ?function)]
        (let [f (val e)]
          (fn [target bindings]
            (search-fn-argument (f target) bindings)))
        (throw (ex-info "Unable to resolve function" {:meta (meta ast)}))))

    {:tag ::m.match.syntax/cata, :argument ?pattern}
    (let [search-fn-pattern (search-fn ?pattern env return reject)]
      (if-some [e (find env ::cata)]
        (let [search-fn-cata (val e)]
          (fn [target bindings]
            (let [search-fn-cata (val e)]
              (mapcat
               (fn [target]
                 (search-fn-pattern target bindings))
               (search-fn-cata target)))))
        (fn [target bindings]
          (if-some [e (find bindings ::cata)]
            (let [search-fn-cata (val e)]
              (mapcat
               (fn [target]
                 (search-fn-pattern target bindings))
               (search-fn-cata target)))
            (throw (ex-info "Unable to resolve cata" {:meta (meta ast)}))))))

    {:tag ::m.match.syntax/guard, :expr ?expr}
    (if-some [e (find env ?expr)]
      (let [p (val e)]
        (fn [target bindings]
          (if (p target)
            (return bindings)
            (reject bindings))))
      (throw (ex-info "Unable to resolve guard predicate" {:meta (meta ast)})))

    {:tag ::m.match.syntax/let, :pattern ?pattern, :expression ?expression}
    (let [eval (get env ::eval)
          search-fn-pattern (search-fn ?pattern env reject)]
      (fn [target bindings]
        (search-fn-pattern (eval ?expression) bindings)))

    {:tag :meander.match.syntax.epsilon/not :argument ?argument}
    (let [search-fn-argument (search-fn ?argument env return reject)]
      (fn [target bindings]
        (if (seq (search-fn-argument target bindings))
          (reject bindings)
          (return bindings))))
    
    {:tag :meander.match.syntax.epsilon/or :arguments ?arguments}
    (if (seq ?arguments)
      (reduce search-fn-or
              (map (fn [argument]
                     (search-fn argument env return reject))
                   ?arguments))
      reject)
    
    {:tag :meander.match.syntax.epsilon/pred, :form ?form, :arguments ?arguments}
    (let [eval (get env ::eval)
          synthetic-and {:tag :meander.match.syntax.epsilon/and, :arguments ?arguments}
          search-fn-arguments (search-fn synthetic-and env return reject)]
      (fn [target bindings]
        (let [predicate (eval ?form)]
          (if (predicate target)
            (search-fn-arguments target bindings)
            (reject bindings)))))

    {:tag :meander.match.syntax.epsilon/rxc, :regex ?regex, :capture ?capture}
    (let [search-fn-capture (search-fn ?capture env return reject)]
      (search-fn-pred string?
        (fn [target bindings]
          (if-some [matches (re-matches ?regex target)]
            (let [matches (if (coll? matches)
                            (vec matches)
                            [matches])]
              (search-fn-capture matches bindings))
            (reject bindings)))
        reject))

    {:tag :meander.match.syntax.epsilon/rxt, :regex ?regex}
    (search-fn-pred string?
      (fn [target bindings]
        (if (re-matches ?regex target)
          (return bindings)
          (reject bindings)))
      reject)

    _
    (throw (ex-info "" {:ast ast}))))

;; Public API
;; ----------

(def
  ^{:arglists '([bindings])}
  default-return list)

(defn default-reject [bindings]
  ())

(defn make-search-fn
  ([form]
   (let [options {::eval #?(:clj eval
                            :cljs (fn [_] (throw (ex-info "eval not defined" {}))))}]
     (make-search-fn form options)))
  ([form options]
   (let [search-fn (search-fn (m.syntax/parse form) options default-return default-reject)]
     (fn
       ([target] (search-fn target {}))
       ([target bindings] (search-fn target bindings))))))
