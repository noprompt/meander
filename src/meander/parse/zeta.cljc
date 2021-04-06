(ns ^:no-doc meander.parse.zeta
  (:require [meander.core.zeta :as m]
            [meander.runtime.eval.zeta :as m.rt.eval]
            [meander.util.zeta :as m.util]))

(defn default-variable-id
  {:private true}
  [sigil name]
  (symbol (str sigil name)))

(defn special-symbol
  {:private true}
  [fully-qualified-symbol environment]
  (let [namespace-name (namespace fully-qualified-symbol)
        namespace-symbol (symbol namespace-name)
        name (name fully-qualified-symbol)
        ?alias (m/logic-variable)]
    (m/one (m/data fully-qualified-symbol)
           (m/project (m/data environment) (m/assoc m/_ (m/data :requires) (m/assoc m/_ (m/symbol ?alias) (m/data (symbol namespace-name))))
             (m/symbol ?alias (m/data name))))))

(defn make-special-form-rules
  {:private true}
  [environment]
  (let [?f (m/logic-variable)
        >arguments (m/fifo-variable)
        arguments* (m/* [>arguments])]
    (m/one-system
     [(m/rule
       (m/cons (m/data 'clojure.core/unquote) (m/cons >arguments m/_))
       (m/apply (m/data m/code) [>arguments]))

      (m/rule
       (m/cons (m/one (m/project (m/data m/all) ?f
                        (special-symbol `m/all environment))
                      (m/project (m/data m/apply) ?f
                        (special-symbol `m/apply environment))
                      (m/project (m/data m/again) ?f
                        (special-symbol `m/again environment))
                      (m/project (m/data m/dual) ?f
                        (special-symbol `m/dual environment))
                      (m/project (m/data m/some) ?f
                        (special-symbol `m/some environment))
                      (m/project (m/data m/predicate) ?f
                        (special-symbol `m/predicate environment))
                      (m/project (m/data m/project) ?f
                        (special-symbol `m/project environment))
                      (m/project (m/data m/one) ?f
                        (special-symbol `m/one environment)))
               (m/* [>arguments]))
       (m/apply ?f (m/* [(m/again >arguments)])))])))

(defn make-special-symbol-rules
  {:private true}
  [environment]
  (let [?id (m/logic-variable)
        ?name (m/logic-variable) 
        variable-id (m/data (get environment :variable-id))]
    (m/one-system
     [;; Anything
      (m/rule
       (m/data '_)
       (m/apply (m/data m/anything) []))

      ;; Logic Variable
      (m/rule
       (m/all ?id (m/symbol nil (m/str "?" ?name)))
       (m/apply (m/data m/logic-variable) [(m/apply variable-id ["?" ?name])]))

      ;; Fifo Variable
      (m/rule
       (m/all ?id (m/symbol nil (m/str "<" ?name)))
       (m/apply (m/data m/fifo-variable) [(m/apply variable-id ["<" ?name])]))

      ;; Filo Variable
      (m/rule
       (m/all ?id (m/symbol nil (m/str ">" ?name)))
       (m/apply (m/data m/filo-variable) [(m/apply variable-id [">" ?name])]))

      ;; Mutable Variable
      (m/rule
       (m/all ?id (m/symbol nil (m/str "!" ?name)))
       (m/apply (m/data m/mutable-variable) [(m/apply variable-id ["!" ?name])]))

      ;; Reference
      (m/rule
       (m/all ?id (m/symbol nil (m/str "%" ?name)))
       (m/apply (m/data m/reference) [(m/apply variable-id ["%" ?name])]))])))


(def ^{:private true}
  %ampersand
  (m/data '&))

(def ^{:private true}
  %greedy-plus
  (m/data '+))

(def ^{:private true}
  %greedy-star
  (m/data '*))

(def ^{:private true}
  %frugal-star
  (m/data '*?))

(def ^{:private true}
  %regex-operator
  (m/one %frugal-star
         %greedy-plus
         %greedy-star))

(def ^{:private true}
  %sequential-operator
  (m/one %ampersand
         %regex-operator))

(defn base-sequential-rules
  {:private true}
  []
  (m/one-system
   [(m/rule
     (m/one (m/rx-cat [%sequential-operator])
            (m/rx-cat []))
     (m/apply (m/data m/rx-cat) [[]]))

    (let [?1 (m/logic-variable)
          ?2 (m/logic-variable)]
      (m/rule
       (m/rx-cons %ampersand (m/rx-cons ?1 ?2))
       (m/apply (m/data m/rx-join) [(m/again ?1) (m/again ?2)])))

    (let [<1 (m/fifo-variable)
          ?2 (m/logic-variable)
          ?regex-operator (m/logic-variable)]
      (m/rule
       (m/* [(m/dual <1 %sequential-operator)]
            (m/rx-cons (m/all %regex-operator ?regex-operator) ?2))
       (m/apply (m/one (m/project ?regex-operator %greedy-star (m/data m/*))
                       (m/project ?regex-operator %greedy-plus (m/data m/+))
                       (m/project ?regex-operator %frugal-star (m/data m/*?)))
                [(m/* [(m/again <1)])
                 (m/again ?2)])))

    (let [?1 (m/logic-variable)
          ?2 (m/logic-variable)]
      (m/rule
       (m/rx-cons ?1 ?2)
       (m/apply (m/data m/rx-cons) [(m/again ?1) (m/again (m/list `regex ?2))])))]))

(defn make-sequential-rules []
  (let [?x (m/logic-variable)
        ?f (m/logic-variable)
        %base-sequential-rules (base-sequential-rules)]
    (m/one-system
     [(m/rule
       (m/list `regex %base-sequential-rules)
       %base-sequential-rules)

      (m/rule
       (m/one (m/seq (m/project m/seq ?f %base-sequential-rules))
              (m/vec (m/project m/vec ?f %base-sequential-rules)))
       (m/apply ?f [%base-sequential-rules]))])))

(defn make-map-rules []
  (m/one-system
   [;; {} => (m/merge)
    (m/rule
     (m/data {})
     (m/apply m/merge []))

    ;; {(m/dual ?k '&) ?v & ?m} => (m/assoc ?m ?k ?v)
    (let [?m (m/logic-variable)
          ?k (m/logic-variable)
          ?v (m/logic-variable)]
      (m/rule
       (m/assoc ?m (m/dual ?k %ampersand) ?v)
       (m/apply (m/data m/assoc) [(m/again ?m) (m/again ?k) (m/again ?v)])))

    ;; {'& ?m} => (m/merge ?m)
    (let [?m (m/logic-variable)
          ?x (m/logic-variable)]
      (m/rule
       (m/assoc (m/data {}) %ampersand ?x)
       (m/apply (m/data m/merge) [(m/again ?x)])))]))

(defn rx [p]
  (m/rx-cat [(m/data `rx) p]))

(defn make-rules
  {:private true}
  [environment]
  (let [_ (m/anything)
        ?head (m/logic-variable)
        ?tail (m/logic-variable)
        ?xs (m/logic-variable)
        rx-empty (m/apply (m/data m/rx-empty) [])]
    (m/one-system
     [(make-special-symbol-rules environment)
      
      ;; (rx ()) | (rx (?x & ?y))
      (m/rule
       (rx (m/one (m/data ()) (m/data [])))
       (m/apply (m/data m/rx-empty) []))

      (m/rule
       (m/rx-cat [?xs (m/data '*)] ?tail)
       (m/again (rx ?tail)))

      (m/rule
       (rx (m/rx-cons ?head ?tail))
       (m/apply (m/data m/rx-cons) [(m/again ?head) (m/again (rx ?tail))]))

      (m/rule
       (m/seq ?xs)
       (m/apply (m/data m/seq) [(m/again (rx ?xs))]))

      (m/rule
       (m/vec ?xs)
       (m/apply (m/data m/vec) [(m/again (rx ?xs))]))
      

      ;; Default
      (let [?x (m/logic-variable)]
        (m/rule ?x (m/apply m/data [?x])))])))

(defn parser [environment]
  (let [environment (update environment :variable-id (fnil identity default-variable-id))
        rules (make-rules environment)
        rt (m.rt.eval/df-one)
        bind (get rt :bind)
        pass (get rt :pass)
        take (get rt :take)]
    (fn [x]
      (bind (fn [state] (take state pass))
            (m/run-system rules rt x)))))

(defn parse [environment x]
  ((parser environment) x))
