(ns meander.interpreter.epsilon
  (:refer-clojure :exclude [apply
                            cat
                            cons
                            concat
                            empty
                            hash-map
                            set
                            some])
  (:require [clojure.core :as clojure]
            [clojure.zip :as zip]
            [meander.environment.epsilon :as m.environment]
            [meander.util.epsilon :as m.util]
            [meander.match.syntax.epsilon :as m.match.syntax]
            [meander.syntax.epsilon :as m.syntax]))



;; Runtime
;; -------

;; A runtime is a map of functions and values used to construct
;; matching functions.
;;
;; Matching functions take an object called "target" and a map called
;; "bindings" and return either a passing result with the runtime
;; function "pass", or a failing result with the runtime value "fail".

;; Results are process with the fuctions "fmap" which maps a function
;; f over a result, and "join" which combines two results.

;; Finally, there is the "scan" function which takes a sequence of
;; objects and a function which takes any object and returns a result.

(def default-eval
  #?(:clj eval
     :cljs (fn no-eval [_]
             (throw (ex-info "eval not defined" {})))))

(def
  ^{:private true}
  find-runtime
  (let [fail (reify)]
    {:eval default-eval
     :pass identity
     :fail fail
     :fmap (fn [f x]
             (if (identical? x fail)
               x
               (f x)))
     :join (fn
             ([a] a)
             ([a b] a))
     :scan (fn [p xs]
             (reduce
              (fn [_ x]
                (let [y (p x)]
                  (if (identical? y fail)
                    y
                    (reduced y))))
              fail
              xs))}))

(def
  ^{:private true}
  depth-first-search-runtime
  {:eval default-eval
   :pass list
   :fail ()
   :fmap mapcat 
   :join clojure/concat
   :scan mapcat})

(def
  ^{:private true}
  breadth-first-search-runtime
  (let [fmap (fn g [f x]
               (if (seq x)
                 (m.util/mix (f (first x))
                             (lazy-seq (g f (rest x))))
                 ()))]
    {:eval default-eval
     :pass list
     :fail ()
     :fmap fmap
     :join (fn
             ([a] a)
             ([a b] (m.util/mix a b)))
     :scan fmap}))

;; Matching function constructors
;; ------------------------------

;; Matching function constructors take a runtime along with other
;; arguments and return a matching function. The prefix "p" or "p_" is
;; used to indicate a matching function.

(defn make-anything
  {:private true}
  [runtime]
  (let [pass (get runtime :pass)]
    (fn [target bindings]
      (pass bindings))))

(defn make-pred
  {:private true}
  [runtime p]
  (let [pass (get runtime :pass)
        fail (get runtime :fail)]
    (fn [target bindings]
      (if (p target)
        (pass bindings)
        fail))))

(defn make-apply
  {:private true}
  [runtime f p]
  (fn [target bindings]
    (p (f target) bindings)))

(defn make-all
  {:private true}
  [runtime p1 p2]
  (let [fmap (get runtime :fmap)]
    (fn [target bindings]
      (fmap (fn [bindings] (p2 target bindings))
            (p1 target bindings)))))


(defn make-one
  {:private true}
  [runtime p1 p2]
  (let [fail (get runtime :fail)]
    (fn [target bindings]
      (let [x (p1 target bindings)]
        (if (= x fail)
          (p2 target bindings)
          x)))))

(defn make-some
  {:private true}
  [runtime p1 p2]
  (let [fail (get runtime :fail)
        join (get runtime :join)]
    (fn [target bindings]
      (let [x (p1 target bindings)
            y (p2 target bindings)]
        (case [(= x fail) (= y fail)]
          [false false]
          (join x y)

          [false true]
          x

          [true false]
          y
          
          ;; else
          fail)))))

(defn make-literal
  {:private true}
  [runtime x]
  (make-pred runtime (fn [y] (= y x))))

(defn make-logic-variable
  {:private true}
  [runtime id]
  (let [pass (get runtime :pass)
        fail (get runtime :fail)]
    (fn [target bindings]
      (if-some [e (find bindings id)]
        (if (= (val e) target)
          (pass bindings)
          fail)
        (pass (assoc bindings id target))))))

(defn make-memory-variable
  {:private true}
  [runtime id]
  (let [pass (get runtime :pass)]
    (fn [target bindings]
      (pass (update bindings id (fnil conj []) target)))))

(defn make-mutable-variable
  {:private true}
  [runtime id]
  (let [pass (get runtime :pass)]
    (fn [target bindings]
      (pass (assoc bindings id target)))))

(defn make-reference
  {:private true}
  [runtime id]
  (let [meta {::reference-id id}]
    (with-meta (fn [target bindings]
                 (if-some [p (get bindings meta)]
                   (p target bindings)
                   (throw (ex-info "Unbound reference" {:id id}))))
      meta)))

(defn make-with [runtime bindings p_body]
  (let [bindings_with (into {} (map (fn [[p_reference p]] [(meta p_reference) p])) 
                            (partition 2 bindings))
        fmap (get runtime :fmap)
        pass (get runtime :pass)]
    (fn [target bindings]
      (fmap (fn [bindings_body]
              ;; Pop the bindings
              (pass (merge bindings (reduce dissoc bindings_body (keys bindings_with)))))
            ;; Push the bindings
            (p_body target (merge bindings bindings_with))))))

(defn make-containment
  ([runtime p]
   (let [scan (get runtime :scan)]
     (fn [target bindings]
       (scan (fn [x] (p x bindings))
             (m.util/coll-seq target)))))
  ([runtime p_context p]
   (let [fmap (get runtime :fmap)
         scan (get runtime :scan)]
     (fn [target bindings]
       (scan (fn [loc]
               (let [node (zip/node loc)
                     edit (fn [x]
                            (zip/root (zip/replace loc x)))]
                 (fmap (fn [bindings]
                         (p_context edit bindings))
                       (p node bindings))))
             (m.util/zip-next-seq (m.util/coll-zip target)))))))

(defn make-empty
  {:private true}
  [runtime]
  (let [pass (get runtime :pass)
        fail (get runtime :fail)]
    (fn [target bindings]
      (if (seq target)
        fail
        (pass bindings)))))

(defn make-cons
  {:private true}
  [runtime p1 p2]
  (make-all runtime
            (make-pred runtime seq)
            (make-all runtime (make-apply runtime first p1) (make-apply runtime rest p2))))

(defn make-bounds-check
  [n]
  (let [exclusive-upper-bound (inc n)]
    (fn [x]
      (= (bounded-count exclusive-upper-bound x)
         n))))

(defn make-cat
  [runtime ps]
  (let [n (count ps)
        p_bounds-check (make-pred runtime (make-bounds-check n))
        p_nths (map-indexed
                (fn [i p]
                  (make-apply runtime (fn [x] (nth x i)) p))
                ps)]
    (reduce (fn [p p_nth]
              (make-all runtime p p_nth))
            p_bounds-check
            p_nths)))

(defn make-concat
  {:private true}
  [runtime p_left p_right]
  (let [fmap (get runtime :fmap)
        scan (get runtime :scan)]
    (fn [target bindings]
      (scan (fn [[left right]]
              (fmap (fn [bindings]
                      (p_right right bindings))
                    (p_left left bindings)))
            (m.util/partitions 2 target)))))

(defn make-star
  {:private true}
  [runtime p]
  (let [fmap (get runtime :fmap)
        pass (get runtime :pass)
        scan (get runtime :scan)]
    (fn f [target bindings]
      (if (seq target)
        (scan (fn [[left right]]
                (fmap (fn [bindings]
                        (f right bindings))
                      (p left bindings)))
              (m.util/partitions 2 target))
        (pass bindings)))))

(defn make-plus
  {:private true}
  [runtime p n]
  (let [fmap (get runtime :fmap)
        pass (get runtime :pass)
        fail (get runtime :fail)
        scan (get runtime :scan)]
    (fn [target bindings]
      ((fn f [n target bindings]
         (if (seq target)
           (let [n* (dec n)]
             (scan (fn [[left right]]
                     (fmap (fn [bindings]
                             (f n* right bindings))
                           (p left bindings)))
                   (m.util/partitions 2 target)))
           (if (zero? n)
             (pass bindings)
             fail)))
       n target bindings))))

(defn make-entry
  {:private true}
  [runtime p_key p_val]
  (make-all runtime (make-apply runtime key p_key) (make-apply runtime val p_val)))

(defn make-hash-map
  {:private true}
  ([runtime]
   (let [pass (get runtime :pass)]
     (fn [target bindings]
       (pass bindings))))
  ([runtime p_key p_val]
   (let [scan (get runtime :scan)
         p_entry (make-entry runtime p_key p_val)]
     (fn [target bindings]
       (scan (fn [entry]
               (p_entry entry bindings))
             target))))
  ([runtime p_key p_val & p_keyvals]
   (let [entry-count (count p_keyvals)]
     (if (even? entry-count)
       (let [scan (get runtime :scan)
             p_entries (reduce
                        (fn [p_entries p_entry]
                          (make-cons runtime p_entry p_entries))
                        (make-empty runtime)
                        (map (fn [[p_key p_val]]
                               (make-entry runtime p_key p_val))
                             (clojure.core/cons (list p_key p_val) (partition 2 p_keyvals))))]
         (fn [target bindings]
           (scan (fn [[entries _]] (p_entries entries bindings))
                 (m.util/map-k-permutations-with-unselected target entry-count))))
       (throw (ex-info "make-hash-map requires and odd number of arguments"))))))


(defn make-set
  {:private true}
  ([runtime p_elements]
   {:pre [(sequential? p_elements)]}
   (let [k (count p_elements)
         p_cat (make-cat runtime p_elements)
         scan (get runtime :scan)]
     (fn [target bindings]
       (scan (fn [[elements _]]
               (p_cat elements bindings)) 
             (m.util/set-k-permutations-with-unselected target k)))))
  ([runtime p_elements p_rest]
   {:pre [(sequential? p_elements)]}
   (let [k (count p_elements)
         p_cat (make-cat p_elements)
         scan (get runtime :scan)
         fmap (get runtime :fmap)]
     (fn [target bindings]
       (scan (fn [[elements unselected]]
               (fmap (fn [bindings]
                       (p_rest unselected bindings))
                     (p_cat elements bindings))) 
             (m.util/set-k-permutations-with-unselected target k))))))

;; Matching function factories
;; ---------------------------

;; Matching function factories return a function which takes a runtime
;; and return matching function. Arguments prefixed with "p" or "p_"
;; and suffixed with "*" indicate the argument is expected to be a
;; matching function factory.

(def ^{:arglists '([runtime])
       :private true}
  anything make-anything)

(defn pred [f]
  {:private true}
  (fn [runtime] (make-pred runtime f)))

(defn apply [f p*]
  {:private true}
  (fn [runtime] (make-apply runtime f (p* runtime))))

(defn all
  {:private true}
  ([p*] p*)
  ([p1* p2*]
   (fn [runtime] (make-all runtime (p1* runtime) (p2* runtime)))))

(defn one
  {:private true}
  ([p*] p*)
  ([p1* p2*]
   (fn [runtime] (make-one runtime (p1* runtime) (p2* runtime)))))

(defn some
  {:private true}
  ([p*] p*)
  ([p1* p2*]
   (fn [runtime] (make-some runtime (p1* runtime) (p2* runtime)))))

(defn $
  {:private true}
  ([p*]
   (fn [runtime]
     (make-containment runtime (p* runtime))))
  ([p_context* p*]
   (fn [runtime]
     (make-containment runtime (p_context* runtime) (p* runtime)))))

(defn literal
  {:private true}
  [x]
  (fn [runtime] (make-literal runtime x)))

(defn logic-variable
  {:private true}
  [id]
  (fn [runtime] (make-logic-variable runtime id)))

(defn memory-variable
  {:private true}
  [id]
  (fn [runtime] (make-memory-variable runtime id)))

(defn mutable-variable
  {:private true}
  [id]
  (fn [runtime] (make-mutable-variable runtime id)))

(defn reference
  {:private true}
  [id]
  (fn [runtime] (make-reference runtime id)))

(defn with
  {:private true}
  [bindings* p_body*]
  (fn [runtime] (make-with runtime (map (fn [p*] (p* runtime)) bindings*) (p_body* runtime))))

(def ^{:arglists '([runtime])
       :private true}
  empty make-empty)

(defn cons
  {:private true}
  [p_head* p_tail*]
  (fn [runtime] (make-cons runtime (p_head* runtime) (p_tail* runtime))))

(defn cat
  {:private true}
  [ps*]
  (fn [runtime] (make-cat runtime (map (fn [p*] (p* runtime)) ps*))))

(defn concat
  {:private true}
  [p_left* p_right*]
  (fn [runtime] (make-concat runtime (p_left* runtime) (p_right* runtime))))

(defn star
  {:private true}
  [p*]
  (fn [runtime] (make-star runtime (p* runtime))))

(defn plus
  {:private true}
  [p* n]
  (fn [runtime] (make-plus runtime (p* runtime) n)))

(defn hash-map
  {:private true}
  [p_keyvals*]
  (all (pred map?)
       (fn [runtime]
         (clojure/apply make-hash-map runtime (map (fn [p*] (p* runtime)) p_keyvals*)))))

(defn set
  {:private true}
  ([p_elements*]
   (all (pred set?)
        (fn [runtime]
          (make-set runtime
                    (map (fn [p*] (p* runtime))
                         p_elements*)))))
  ([p_elements* p_rest*]
   (all (pred set?)
        (fn [runtime]
          (make-set runtime
                    (map (fn [p*] (p* runtime)) p_elements*)
                    (p_rest* runtime))))))

(defn -pattern-dispatch
  {:private true}
  [ast]
  (get ast :tag))

(defmulti -pattern
  {:arglists '([ast])
   :private true}
  #'-pattern-dispatch)

(defn pattern
  {:private true}
  [form]
  (-pattern (m.match.syntax/parse form)))

(defmethod -pattern :any [_]
  anything)

(defmethod -pattern :cat
  [ast]
  (let [elements (get ast :elements)
        length (count elements)
        exclusive-upper-bound (inc length)]
    (all (pred (fn [x]
                 (= (bounded-count exclusive-upper-bound x) 
                    length)))
         (reduce
          (fn [p* p_element*]
            (cons p_element* p*))
          empty
          (map -pattern (reverse elements))))))

(defmethod -pattern :ctn
  [ast]
  (let [?context (get ast :context)
        ?pattern (get ast :pattern)
        p_-pattern (-pattern ?pattern)]
    (if (some? ?context)
      ;; {:tag :ctn, :context {:as ?context} :pattern ?pattern}
      (let [p_context* (-pattern ?context)]
        ($ p_context* p_-pattern))
      ;; {:tag :ctn, :context nil :pattern ?pattern}
      ($ p_-pattern))))

(defmethod -pattern :drp [_]
  anything)

(defmethod -pattern :jsa [ast])

(defmethod -pattern :jso [ast])

(defmethod -pattern :lvr
  [ast]
  (let [?symbol (get ast :symbol)]
    (logic-variable ?symbol)))

(defmethod -pattern :lit
  [ast]
  (let [?value (get ast :value)]
    (literal ?value)))

(defmethod -pattern :map [ast]
  (if-some [as (get ast :as)]
    (all (-pattern as)
         (-pattern (assoc ast :as nil)))
    (if-some [rest-map (get ast :rest-map)]
      (let [p_permutation* (cat (map (fn [[ast_key ast_val]]
                                       (all (apply first (-pattern ast_key))
                                            (apply second (-pattern ast_val))))
                                     (get ast :map)))
            p_rest-map* (-pattern rest-map)
            p_element* (cat [p_permutation* p_rest-map*])
            k (count (get ast :map))]
        (fn [runtime]
          (let [p_element (p_element* runtime)
                scan (get runtime :scan)]
            (fn [target bindings]
              (scan (fn [element]
                      (p_element element bindings))
                    (m.util/map-k-permutations-with-unselected target k))))))
      (let [p_keyvals* (map -pattern (mapcat identity (get ast :map)))]
        (hash-map p_keyvals*)))))

(defmethod -pattern :mut
  [ast]
  (let [?symbol (get ast :symbol)]
    (mutable-variable ?symbol)))

(defmethod -pattern :mvr [ast]
  (let [?symbol (get ast :symbol)]
    (memory-variable ?symbol)))

(defmethod -pattern :quo [ast]
  (let [form (get ast :form)]
    (literal form)))

(defmethod -pattern :prt
  [ast]
  (let [?left (get ast :left)
        ?right (get ast :right)]
    (concat (-pattern ?left) (-pattern ?right))))

(defmethod -pattern :seq
  [ast]
  (let [?as (get ast :as)
        ?prt (get ast :prt)]
    (all (pred seq?)
         (if (some? ?as)
           (-pattern ?as)
           (-pattern ?prt)))))

(defmethod -pattern :ref
  [ast]
  (reference (get ast :symbol)))

(defmethod -pattern :rp* [ast]
  (let [p_cat* (-pattern (get ast :cat))]
    (star p_cat*)))

(defmethod -pattern :rpl [ast]
  (let [p_cat* (-pattern (get ast :cat))
        p_lvr* (-pattern (get ast :lvr))]
    (all p_cat* (apply count p_lvr*))))

(defmethod -pattern :rst [ast]
  (-pattern (get ast :mvr)))

(defmethod -pattern :tail [ast]
  (-pattern (get ast :pattern)))


(defmethod -pattern :rp+
  [ast]
  (let [n (get ast :n)
        p_cat* (cat (map -pattern (get ast :cat)))]
    (plus -pattern n)))

(defmethod -pattern :rpm
  [ast]
  (all (-pattern {:tag :rp*, :cat (get ast :cat)})
       (-pattern (get ast :mvr))))

(defmethod -pattern :set
  [ast]
  (let [as (get ast :ast)
        rest (get ast :rest)
        elements (get ast :elements)
        k (count elements)
        p_elements* (map -pattern elements)]
    (case [(nil? as) (nil? rest)]
      [true true]
      (set p_elements*)
      
      [true false]
      (let [p_rest* (-pattern rest)]
        (set p_elements* p_rest*))

      (let [p_as* (-pattern as)]
        (all p_as* (-pattern (assoc ast :as nil)))))))

(defmethod -pattern :unq
  [ast]
  (let [expr (get ast :expr)]
    (fn [runtime]
      (if-some [eval (get runtime :eval)]
        (let [pass (get runtime :pass)
              fail (get runtime :fail)]
          (fn [target bindings]
            (if (= target (eval expr))
              (pass bindings)
              fail)))
        (throw (ex-info "eval not provided" {:runtime runtime}))))))

(defmethod -pattern :uns
  [ast]
  (let [expr (get ast :expr)]
    (fn [runtime]
      (if-some [eval (get runtime :eval)]
        (pred (fn [x] (= (eval expr) x)))
        (throw (ex-info "eval not provided" {:runtime runtime}))))))

(defmethod -pattern :vec
  [ast]  
  (let [?as (get ast :as)
        ?prt (get ast :prt)]
    (all (pred vector?)
         (if (some? ?as)
           (-pattern ?as)
           (-pattern ?prt)))))

(defmethod -pattern :wth
  [ast]
  (let [bindings (get ast :bindings)
        body (get ast :body)]
    (if (some? body)
      (with (sequence (comp (mapcat (juxt :ref :pattern))
                            (map -pattern))
                      bindings)
        (-pattern body))
      anything)))

(defmethod -pattern :meander.match.syntax.epsilon/and
  [ast]
  (let [p_arguments* (map -pattern (get ast :arguments))]
    (reduce all p_arguments*)))

(defmethod -pattern :meander.match.syntax.epsilon/cata
  [ast]
  (let [p_argument* (-pattern (get ast :argument))]
    (fn [runtime]
      (let [fmap (get runtime :fmap)
            scan (get runtime :scan)
            p_argument (p_argument* runtime)]
        (fn [target bindings]
          (if-some [cata (get bindings ::cata)]
            (fmap (fn [x] (p_argument x bindings))
                  (cata target))
            (throw (ex-info "cata not provided" {}))))))))

(defmethod -pattern :meander.match.syntax.epsilon/let
  [ast])

(defmethod -pattern :meander.match.syntax.epsilon/or
  [ast]
  (reduce some (map -pattern (get ast :arguments))))

(defmethod -pattern :meander.match.syntax.epsilon/apply
  [ast]
  (let [function (get ast :function)
        p_argument* (-pattern (get ast :argument))]
    (fn [runtime]
      (if-some [eval (get runtime :eval)]
        (let [p_argument (p_argument* runtime)]
          (fn [target bindings]
            (p_argument ((eval function) target) bindings)))
        (throw (ex-info "eval not provided" {:runtime runtime}))))))

(defmethod -pattern :meander.match.syntax.epsilon/guard
  [ast]
  (let [expr (get ast :expr)]
    (fn [runtime]
      (if-some [eval (get runtime :eval)]
        (pred (fn [_] (eval expr)))
        (throw (ex-info "eval not provided" {:runtime runtime}))))))

(defmethod -pattern :meander.match.syntax.epsilon/not
  [ast]
  (let [p_argument* (-pattern (get ast :argument))]
    (fn [runtime]
      (let [p_argument (p_argument* runtime)
            fail (get runtime :fail)
            pass (get runtime :pass)]
        (fn [target bindings]
          (if (= fail (p_argument target))
            (pass bindings)
            fail))))))

(defmethod -pattern :meander.match.syntax.epsilon/pred
  [ast]
  (let [form (get ast :form)
        arguments (get ast :arguments)
        synthetic-and {:tag :meander.match.syntax.epsilon/and
                       :arguments arguments}
        p_and* (-pattern synthetic-and)]
    (fn [runtime]
      (if-some [eval (get runtime :eval)]
        (let [predicate (eval form)
              p_and (p_and* runtime)
              fail (get runtime :fail)
              pass (get runtime :pass)]
          (fn [target bindings]
            (if (predicate target)
              (pass bindings)
              fail)))
        (throw (ex-info "eval not provided" {:runtime runtime}))))))

(defmethod -pattern :meander.match.syntax.epsilon/rxc
  [ast]
  (let [regex (get ast :regex)]
    (all (pred string?)
         (all (apply (fn [s] (re-matches regex s)))
              (-pattern (get ast :capture))))))

(defmethod -pattern :meander.match.syntax.epsilon/rxt
  [ast]
  (let [regex (get ast :regex)]
    (pred (fn [target]
            (and (string? target)
                 (re-matches regex target))))))

(defmethod -pattern :meander.match.syntax.epsilon/subsequence
  [ast]
  (let [cat (get ast :cat)
        p_cat* (-pattern cat)
        n (count cat)]
    (fn [runtime]
      (let [scan (get runtime :scan)
            p_cat (p_cat* runtime)]
        (fn [target bindings]
          (scan (fn [partition]
                  (p_cat partition bindings))
           (partition n 1 target)))))))

(defn pattern [form]
  (let [ast (m.match.syntax/parse form)]
    (-pattern ast)))


(def default-parse-env
  {::m.syntax/expander-registry
   {`meander.interpreter.epsilon/and
    (fn [[_ & args] env]
      `(m.match.syntax/and ~@args))

    `meander.interpreter.epsilon/app
    (fn [[_ & args] env]
      (case (count args)
        (0 1)
        (throw (ex-info "app expects at least two arguments" {}))
        ;; else
        `(m.match.syntax/app ~(first args) (m.match.syntax/and ~@(rest args)))))

    `meander.interpreter.epsilon/cata
    (fn [[_ & args] env]
      `(m.match.syntax/cata ~@args))

    `meander.interpreter.epsilon/guard
    (fn [[_ & args] env]
      `(m.match.syntax/guard ~@args))

    `meander.interpreter.epsilon/let
    (fn [[_ & args] env]
      (let [bindings (nth args 0 nil)]
        (if (and (vector? bindings)
                 (even? (count bindings)))
          (reduce
           (fn [inner [pattern expression]]
             `(r.match.syntax/let ~pattern ~expression ~inner))
           (reverse (partition 2 bindings)))
          (throw (ex-info "The second argument to let must be a vector with an even number of elements" {}))))
      (cons `m.match.syntax/let args))

    `meander.interpreter.epsilon/not
    (fn [[_ & args] env]
      `(m.match.syntax/not ~@args))

    `meander.interpreter.epsilon/or
    (fn [[_ & args] env]
      `(m.match.syntax/or ~@args))

    `meander.interpreter.epsilon/pred
    (fn [[_ & args] env]
      (if (seq args)
        `(m.match.syntax/pred ~(first args) (m.match.syntax/and ~@(rest args)))
        `(m.match.syntax/pred)))

    `meander.interpreter.epsilon/re
    (fn [[_ & args] env]
      `(m.match.syntax/re ~@args))}})

(defn parse-rules
  {:private true}
  [xs]
  (map (fn [[left right]]
         (let [p_left* (if (fn? left)
                         left
                         (-pattern (m.match.syntax/parse left default-parse-env)))]
           [p_left* right]))
       (partition-all 2 xs)))

(defn system
  [args]
  (let [rules* (parse-rules args)]
    (fn [runtime]
      (let [fmap (get runtime :fmap)
            scan (get runtime :scan)
            pass (get runtime :pass)
            rules (map (fn [[p_left* right]]
                         [(p_left* runtime) right])
                       rules*)]
        (fn f
          ([target]
           (f target {}))
          ([target bindings]
           (let [bindings (assoc bindings ::cata f)]
             (scan
              (fn [[left right]]
                (fmap (fn [x]
                        (pass (right (dissoc x ::cata))))
                      (left target bindings)))
              rules))))))))

(defn finder [& clauses]
  (let [s_clauses ((system clauses) find-runtime)
        fail (get find-runtime :fail)]
    (comp (fn [x] (if (= x fail) nil x)) s_clauses)))

(defn searcher [& clauses]
  (let [system* (system clauses)]
    (system* depth-first-search-runtime)))

(comment
  ((finder '{?k ?v} identity) {:a 1 :b 2})
  ;; =>
  {?k :a, ?v 1}

  ((searcher '{?k ?v} identity) {:a 1 :b 2})
  ;; =>
  ({?k :a, ?v 1} {?k :b, ?v 2}))
