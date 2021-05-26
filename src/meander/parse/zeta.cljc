(ns ^:no-doc meander.parse.zeta
  (:require [meander.pattern.zeta :as m.pattern]
            [meander.runtime.eval.zeta :as m.kernel.eval]
            [meander.util.zeta :as m.util]))

(def rule-default
  (let [?x (m.pattern/logic-variable)]
    (m.pattern/rule
     ?x
     (m.pattern/apply (m.pattern/data m.pattern/data)
                      (m.pattern/regex-cons ?x (m.pattern/regex-empty))
                      (m.pattern/anything)))))

(defn parser [options]
  (let [kernel (get options :kernel)
        bind (get kernel :bind)
        take (get kernel :take)
        pass (get kernel :pass)]
    (fn [form]
      (bind (fn [state] (take state pass))
            (m.pattern/run-rule rule-default kernel form)))))

(defn parse [form]
  (let [parse (parser {:kernel (m.kernel.eval/df-one)})]
    (parse form)))

;; (defn default-variable-id
;;   {:private true}
;;   [sigil name]
;;   (symbol (str sigil name)))

;; (defn special-symbol
;;   "Builds the pattern

;;       (one ~fully-qualified-symbol
;;         (project ~environment {_ {:requires {(symbol ?alias) (symbol ~namespace-name)}}}
;;         (symbol ?alias ~name)))"
;;   [fully-qualified-symbol environment]
;;   (let [namespace-name (namespace fully-qualified-symbol)
;;         namespace-symbol (symbol namespace-name)
;;         name (name fully-qualified-symbol)
;;         ?alias (m/logic-variable)]
;;     (m/one (m/data fully-qualified-symbol)
;;            (m/project (m/data environment) (m/assoc m/_ (m/data :requires) (m/assoc m/_ (m/symbol ?alias) (m/data (symbol namespace-name))))
;;              (m/symbol ?alias (m/data name))))))

;; (defn make-special-form-rules
;;   {:private true}
;;   [environment]
;;   (let [?f (m/logic-variable)
;;         >arguments (m/fifo-variable)
;;         arguments* (m/* [>arguments])]
;;     (m/one-system
;;      [;; ('clojure.core/unquote & _)
;;       (m/rule
;;        (m/cons (m/data 'clojure.core/unquote) (m/cons >arguments m/_))
;;        (m/apply (m/data m/code) [>arguments]))

;;       (m/rule
;;        (m/cons (m/one (m/project (m/data m/all) ?f
;;                         (special-symbol `m/all environment))
;;                       (m/project (m/data m/apply) ?f
;;                         (special-symbol `m/apply environment))
;;                       (m/project (m/data m/again) ?f
;;                         (special-symbol `m/again environment))
;;                       (m/project (m/data m/dual) ?f
;;                         (special-symbol `m/dual environment))
;;                       (m/project (m/data m/some) ?f
;;                         (special-symbol `m/some environment))
;;                       (m/project (m/data m/predicate) ?f
;;                         (special-symbol `m/predicate environment))
;;                       (m/project (m/data m/project) ?f
;;                         (special-symbol `m/project environment))
;;                       (m/project (m/data m/one) ?f
;;                         (special-symbol `m/one environment)))
;;                (m/* [>arguments]))
;;        (m/apply ?f (m/* [(m/again >arguments)])))])))

;; (defn make-special-symbol-rules
;;   {:private true}
;;   [environment]
;;   (let [?id (m/logic-variable)
;;         ?name (m/logic-variable) 
;;         variable-id (m/data (get environment :variable-id))]
;;     (m/one-system
;;      [;; Anything
;;       (m/rule
;;        (m/data '_)
;;        (m/apply (m/data m/anything) []))

;;       ;; Logic Variable
;;       (m/rule
;;        (m/all ?id (m/symbol nil (m/str "?" ?name)))
;;        (m/apply (m/data m/logic-variable) [(m/apply variable-id ["?" ?name])]))

;;       ;; Fifo Variable
;;       (m/rule
;;        (m/all ?id (m/symbol nil (m/str "<" ?name)))
;;        (m/apply (m/data m/fifo-variable) [(m/apply variable-id ["<" ?name])]))

;;       ;; Filo Variable
;;       (m/rule
;;        (m/all ?id (m/symbol nil (m/str ">" ?name)))
;;        (m/apply (m/data m/filo-variable) [(m/apply variable-id [">" ?name])]))

;;       ;; Mutable Variable
;;       (m/rule
;;        (m/all ?id (m/symbol nil (m/str "!" ?name)))
;;        (m/apply (m/data m/mutable-variable) [(m/apply variable-id ["!" ?name])]))

;;       ;; Reference
;;       (m/rule
;;        (m/all ?id (m/symbol nil (m/str "%" ?name)))
;;        (m/apply (m/data m/reference) [(m/apply variable-id ["%" ?name])]))])))


;; (defn make-map-rules []
;;   (m/one-system
;;    [;; {} => (m/merge)
;;     (m/rule
;;      (m/data {})
;;      (m/apply m/merge []))

;;     ;; {(m/dual ?k '&) ?v & ?m} => (m/assoc ?m ?k ?v)
;;     (let [?m (m/logic-variable)
;;           ?k (m/logic-variable)
;;           ?v (m/logic-variable)]
;;       (m/rule
;;        (m/assoc ?m (m/dual ?k (m/data '&)) ?v)
;;        (m/apply (m/data m/assoc) [(m/again ?m) (m/again ?k) (m/again ?v)])))

;;     ;; {'& ?m} => (m/merge ?m)
;;     (let [?m (m/logic-variable)
;;           ?x (m/logic-variable)]
;;       (m/rule
;;        (m/assoc (m/data {}) (m/data '&) ?x)
;;        (m/apply (m/data m/merge) [(m/again ?x)])))]))

;; (defn rx
;;   {:private true}
;;   [%pattern]
;;   (m/rx-cat [(m/data `rx) %pattern]))

;; (defn rx-quantifier-rule
;;   "Matches and yields objects of the form

;;       (`rx (,,,))
;;       (`rx [,,,])

;;   This rule helps to avoid redundant calls to `m/seq` or `m/vec`."
;;   {:private true}
;;   [%head %host]
;;   (let [?tail (m/logic-variable)]
;;     (m/rule (m/seq (m/rx-cons %head ?tail))
;;             (m/apply %host [(m/again (rx ?tail))]))))

;; (defn make-rules
;;   {:private true}
;;   [environment]
;;   (let [_ (m/anything)
;;         ?head (m/logic-variable)
;;         ?tail (m/logic-variable)
;;         ?x (m/logic-variable)
;;         ?xs (m/logic-variable)
;;         rx-empty (m/apply (m/data m/rx-empty) [])]
;;     (m/one-system
;;      [(get environment :extra-rules (m/nothing))

;;       (make-special-symbol-rules environment)
      
;;       (make-special-form-rules environment)

;;       ;; (rx ())
;;       (m/rule
;;        (rx (m/one (m/data ()) (m/data [])))
;;        (m/apply (m/data m/rx-empty) []))

;;       ;; (rx (& ?a '& ?b)
;;       (m/rule
;;        (rx (m/rx-join ?head (m/rx-cat [(m/data '&) ?x] ?tail)))
;;        (m/apply (m/data m/rx-join) [(m/again (rx ?head)) (m/apply (m/data m/rx-join) [(m/again ?x) (m/again (rx ?tail))])]))

;;       ;; (rx (?head & ?tail))
;;       (m/rule
;;        (rx (m/rx-cons ?head ?tail))
;;        (m/apply (m/data m/rx-cons) [(m/again ?head) (m/again (rx ?tail))]))

;;       ;; ('meander.core.zeta/+ & ?rest)
;;       ;; ('meander.core.zeta/* & ?rest)
;;       ;; ('meander.core.zeta/*? & ?rest)
;;       ;; ('meander.core.zeta/+? & ?rest)
;;       (let [?host (m/logic-variable)]
;;         (m/rule
;;          (m/seq (m/rx-cons (m/one (m/project (m/data m/*) ?host (special-symbol `m/* environment))
;;                                   (m/project (m/data m/+) ?host (special-symbol `m/+ environment))
;;                                   (m/project (m/data m/*?) ?host (special-symbol `m/*? environment))
;;                                   (m/project (m/data m/+?) ?host (special-symbol `m/+? environment)))
;;                            ?tail))
;;          (m/apply ?host [(m/again (rx ?tail))])))

;;       (m/rule
;;        (m/seq ?xs)
;;        (m/apply (m/data m/seq) [(m/again (rx ?xs))]))

;;       (m/rule
;;        (m/vec ?xs)
;;        (m/apply (m/data m/vec) [(m/again (rx ?xs))]))
      
;;       ;; Default
;;       (let [?x (m/logic-variable)]
;;         (m/rule ?x (m/apply m/data [?x])))])))

;; (defn parser [environment]
;;   (let [environment (update environment :variable-id (fnil identity default-variable-id))
;;         rules (make-rules environment)
;;         rt (m.rt.eval/df-one)
;;         bind (get rt :bind)
;;         pass (get rt :pass)
;;         take (get rt :take)]
;;     (fn [x]
;;       (bind (fn [state] (take state pass))
;;             (m/run-system rules rt x)))))

;; (defn parse [environment x]
;;   ((parser environment) x))
