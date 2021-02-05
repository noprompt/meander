(ns ^:no-doc meander.parse.zeta
  (:require [meander.core.zeta :as m]
            [meander.environment.eval.zeta :as m.environment.eval]
            [meander.util.zeta :as m.util]))

(defn system [rules]
  (let [?rule-id (m/logic-variable)]
    (reduce m/one (mapv
                   (fn [rule]
                     (let [rule-id (name (gensym "RULE__"))]
                       (m/project rule-id ?rule-id rule)))
                   rules))))

(defn sequential-rules [parse]
  (system
   [;; () => ()
    ;; (&) => ()
    (m/rule
     (m/seq (m/one () '(&)))
     ())

    ;; [] => []
    ;; ['&] => []
    ;; [*] => []
    (m/rule
     (m/one [] '[&] '[*])
     [])

    ;; ('& ?1 & ?2) => (m/seq (m/rx-join ?1 ?2))
    ;; ['& ?1 & ?2] => (m/vec (m/rx-join ?1 ?2))
    (let [?1 (m/logic-variable)
          ?2 (m/logic-variable)
          ?f (m/logic-variable)]
      (m/rule
       (m/all (m/rx-cons '& (m/rx-cons ?1 ?2))
              (m/one (m/all (m/seq m/_) (m/project m/seq ?f m/_))
                     (m/all (m/vec m/_) (m/project m/vec ?f m/_))))
       (m/apply ?f [(m/apply m/rx-join [(m/apply parse [?1])
                                        (m/apply parse [?2])])])))

    ;; (?1 & ?2) => (m/seq (m/rx-cons ?1 ?2))
    ;; [?1 & ?2) => (m/vec (m/rx-cons ?1 ?2))
    (let [?1 (m/logic-variable)
          ?2 (m/logic-variable)
          ?f (m/logic-variable)]
      (m/rule
       (m/all (m/rx-cons ?1 ?2)
              (m/one (m/all (m/seq m/_) (m/project m/seq ?f m/_))
                     (m/all (m/vec m/_) (m/project m/vec ?f m/_))))
       (m/apply ?f
                [(m/apply m/rx-cons [(m/apply parse [?1])
                                     (m/apply parse [?2])])])))


    ;; (<1 * & ?2) => (m/seq (m/rx-join (m/* [<1]) ?2))
    ;; [<1 * & ?2] => (m/vec (m/rx-join (m/* [<1]) ?2))
    (let [<1 (m/fifo-variable)
          ?2 (m/logic-variable)
          ?f (m/logic-variable)]
      (m/rule
       (m/all (m/* [(m/dual <1 '*)] (m/rx-cons '* ?2))
              (m/one (m/all (m/seq m/_) (m/project m/seq ?f m/_))
                     (m/all (m/vec m/_) (m/project m/vec ?f m/_))))
       (m/apply ?f [(m/apply m/* [(m/* [(m/apply parse [<1])])
                                  (m/apply parse [?2])])])))]))

(defn variable-rules [environment]
  (let [?id (m/logic-variable)
        ?name  (m/logic-variable) 
        variable-id (get environment :variable-id)]
    (system
     [ ;; Logic Variable
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

(defn map-rules [parse]
  (system
   [;; {} => (m/merge)
    (m/rule
     {}
     (m/apply m/merge []))

    ;; {'& ?m} => (m/merge ?m)
    (let [?m (m/logic-variable)
          ?x (m/logic-variable)]
      (m/rule
       (m/assoc {} '& ?x)
       (m/apply m/merge [(m/apply parse [?x])])))

    ;; {(m/dual ?k '&) ?v & ?m} => (m/assoc ?m ?k ?v)
    (let [?m (m/logic-variable)
          ?k (m/logic-variable)
          ?v (m/logic-variable)]
      (m/rule
       (m/assoc ?m (m/dual ?k '&) ?v)
       (m/apply m/assoc [(m/apply parse [?m])
                         (m/apply parse [?k])
                         (m/apply parse [?v])])))]))

(defn special-symbol [fully-qualified-symbol environment]
  (let [namespace-name (namespace fully-qualified-symbol)
        namespace-symbol (symbol namespace-name)
        name (name fully-qualified-symbol)
        ?alias (m/logic-variable)]
    (m/one fully-qualified-symbol
           (m/project environment
                      (m/assoc m/_ :requires (m/assoc m/_ (m/symbol ?alias) namespace-symbol))
                      (m/symbol ?alias name)))))

(defn special-rules [parse environment]
  (let [?f (m/logic-variable)
        >arguments (m/fifo-variable)
        arguments* (m/* [>arguments])]
    (system
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
       (m/apply ?f (m/* [(m/apply parse [>arguments])])))])))

(defn symbol-rules [environment]
  (system 
   [(m/rule
     (m/one (m/symbol nil "_")
            (special-symbol `m/_ environment))
     (m/apply m/anything []))

    (let [?namespace (m/logic-variable)
          ?name (m/logic-variable)]
      (m/rule
       (m/symbol ?namespace ?name)
       (m/apply m/symbol [?namespace ?name])))]))

(defn default-variable-id [sigil name]
  (symbol (str sigil name)))

(defn parse [environment x]
  (let [environment (update environment :variable-id (fnil identity default-variable-id))
        parse* (partial parse environment)]
    (m/run-system
     (m/one-system
      [(special-rules parse* environment)
       (sequential-rules parse*)
       (map-rules parse*)
       (variable-rules environment)
       (symbol-rules environment)
       ;; Default
       (m/logic-variable)])
     m.environment.eval/depth-first-one
     x)))
