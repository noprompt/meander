(ns ^:no-doc meander.parse.zeta
  (:require [meander.core.zeta :as m]
            [meander.environment.eval.zeta :as m.environment.eval]
            [meander.util.zeta :as m.util]))


(defn default-variable-id [sigil name]
  (symbol (str sigil name)))

(defn special-symbol
  {:private true}
  [fully-qualified-symbol environment]
  (let [namespace-name (namespace fully-qualified-symbol)
        namespace-symbol (symbol namespace-name)
        name (name fully-qualified-symbol)
        ?alias (m/logic-variable)]
    (m/one fully-qualified-symbol
           (m/project environment
                      (m/assoc m/_ :requires (m/assoc m/_ (m/symbol ?alias) namespace-symbol))
                      (m/symbol ?alias name)))))

(defn special-form-rules
  {:private true}
  [environment]
  (let [?f (m/logic-variable)
        >arguments (m/fifo-variable)
        arguments* (m/* [>arguments])]
    (m/one-system
     [(m/rule
       (m/cons (m/symbol "clojure.core" "unquote") (m/cons >arguments m/_))
       (m/apply m/constant [>arguments]))

      (m/rule
       (m/cons (m/one (m/project m/all ?f (special-symbol `m/all environment))
                      (m/project m/again ?f (special-symbol `m/again environment))
                      (m/project m/some ?f (special-symbol `m/some environment))
                      (m/project m/predicate ?f (special-symbol `m/predicate environment))
                      (m/project m/project ?f (special-symbol `m/project environment))
                      (m/project m/one ?f (special-symbol `m/one environment)))
               (m/* [>arguments]))
       (m/apply ?f (m/* [(m/again >arguments)])))])))


(defn make-variable-rules
  {:private true}
  [environment]
  (let [?id (m/logic-variable)
        ?name  (m/logic-variable) 
        variable-id (get environment :variable-id)]
    (m/one-system
     [;; Logic Variable
      (m/rule
       (m/all ?id (m/symbol nil (m/str "?" ?name)))
       (m/apply m/logic-variable [(m/apply variable-id ["?" ?name])]))

      ;; Fifo Variable
      (m/rule
       (m/all ?id (m/symbol nil (m/str "<" ?name)))
       (m/apply m/fifo-variable [(m/apply variable-id ["<" ?name])]))

      ;; Filo Variable
      (m/rule
       (m/all ?id (m/symbol nil (m/str ">" ?name)))
       (m/apply m/filo-variable [(m/apply variable-id [">" ?name])]))

      ;; Mutable Variable
      (m/rule
       (m/all ?id (m/symbol nil (m/str "*" ?name)))
       (m/apply m/mutable-variable [(m/apply variable-id ["*" ?name])]))

      ;; Reference
      (m/rule
       (m/all ?id (m/symbol nil (m/str "%" ?name)))
       (m/apply m/reference [(m/apply variable-id ["%" ?name])]))])))

(defn make-symbol-rules
  {:private true}
  [environment]
  (m/one-system 
   [(make-variable-rules environment)

    (m/rule
     (m/one (m/symbol nil "_")
            (special-symbol `m/_ environment))
     (m/apply m/anything []))

    (let [?namespace (m/logic-variable)
          ?name (m/logic-variable)]
      (m/rule
       (m/symbol ?namespace ?name)
       (m/apply m/symbol [?namespace ?name])))]))

(def base-sequential-rules
  (m/one-system
   [(m/rule
     (m/one [] '[&] '[*] '[+])
     [])

    (let [?1 (m/logic-variable)
          ?2 (m/logic-variable)
          ?3 (m/logic-variable)]
      (m/rule
       (m/rx-join ?1 (m/rx-cons '& (m/rx-cons ?2 ?3)))
       (m/apply m/rx-join [(m/again ?1) (m/again ?2) (m/again ?3)])))

    (let [?1 (m/logic-variable)
          ?2 (m/logic-variable)
          ?f (m/logic-variable)]
      (m/rule
       (m/rx-join ?1 (m/rx-cons (m/one (m/project m/* ?f '*)
                                       (m/project m/+ ?f '+))
                                ?2))
       (m/apply ?f [(m/again ?1) (m/again ?2)])))

    (let [<1 (m/fifo-variable)]
      (m/rule
       (m/* [(m/dual <1 (m/one '& '* '+))])
       (m/apply m/rx-cat (m/* [(m/again <1)]))))]))

(def sequential-rules
  (let [?x (m/logic-variable)
        ?f (m/logic-variable)]
    (m/rule
     (m/one (m/seq (m/project m/seq ?f base-sequential-rules))
            (m/vec (m/project m/vec ?f base-sequential-rules)))
     (m/apply ?f [base-sequential-rules]))))

(def map-rules 
  (m/one-system
   [;; {} => (m/merge)
    (m/rule
     {}
     (m/apply m/merge []))

    ;; {'& ?m} => (m/merge ?m)
    (let [?m (m/logic-variable)
          ?x (m/logic-variable)]
      (m/rule
       (m/assoc {} '& ?x)
       (m/apply m/merge [(m/again ?x)])))

    ;; {(m/dual ?k '&) ?v & ?m} => (m/assoc ?m ?k ?v)
    (let [?m (m/logic-variable)
          ?k (m/logic-variable)
          ?v (m/logic-variable)]
      (m/rule
       (m/assoc ?m (m/dual ?k '&) ?v)
       (m/apply m/assoc [(m/again ?m)
                         (m/again ?k)
                         (m/again ?v)])))]))

(defn make-rules
  {:private true}
  [environment]
  (let [?x (m/logic-variable)]
    (m/one-system
     [(special-form-rules environment)
      (make-symbol-rules environment)
      sequential-rules
      map-rules
      ?x])))

(defn parser [environment]
  (let [environment (update environment :variable-id (fnil identity default-variable-id))
        rules (make-rules environment)]
    (fn [x]
      (m/run-system rules m.environment.eval/depth-first-one x))))

(defn parse [environment x]
  ((parser environment) x))

