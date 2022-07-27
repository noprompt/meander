(ns meander.private.zeta
  {:private true}
  (:require [meander.environment.zeta :as m.env]
            [meander.logic.zeta :as m.logic]
            [meander.parse.zeta :as m.parse]
            [meander.primitive.zeta :as m.primitive]
            [meander.protocols.zeta :as m.protocols]
            [meander.state.zeta :as m.state]))

(defn derive-operator-from-function
  [f]
  (fn [env [_ & args]]
    (apply f (m.parse/parse-all env args))))

(defmacro def-fn-operator
  [sym f]
  (let [ns-info (m.env/derive-ns-info &env)
        fq-sym (symbol (name (::m.env/namespace ns-info)) (name sym))]
    `(let [f# (derive-operator-from-function ~f)
           v# (defn ~sym [& args#] (f# nil (cons '~fq-sym args#)))]
       (m.env/operator-add! '~fq-sym (comp reduced f#))
       v#)))

(defn preprocess-form [env form]
  (m.parse/autogensym (m.parse/qualify-operator-symbols env form)))

(defn make-notation*
  [env system-form on-zero {:keys [terminal?]}]
  (let [system-term (m.parse/parse env (preprocess-form env system-form))]
    (if (satisfies? m.protocols/IRedex system-term)
      (fn [form]
        (let [istate (m.state/make {:object form})
                  ilogic (m.logic/make-dff istate)
                  result (m.protocols/-redex system-term ilogic)]
          (if (m.logic/zero? result)
            (on-zero form)
            (let [object (m.protocols/-get-object (deref result))]
              (if terminal?
                (reduced object)
                object)))))
      (ex-info "system-form must parse to an object which satisfies meander.protocols.zeta/IRedex"
               {:system-form system-form
                :system-term system-term}))))

(defn exception? [x]
  #?(:clj
     (instance? Exception x)
     :cljs
     (instance? js/Error x)))


(defmacro make-notation
  [env system-form on-zero options]
  `(let [x# (make-notation* ~env '~system-form ~on-zero ~options)]
     (if (exception? x#)
       (throw x#)
       x#)))

(defn variable-factory
  [env q-system y-system]
  (let [q-system-term (m.parse/parse env q-system)
            y-system-term (m.parse/parse env y-system)]
    (fn [id]
      (m.primitive/variable id q-system-term y-system-term))))

(defn create-system-fn*
  [env system-form make-logic]
  (let [system-term (m.parse/parse env (preprocess-form env system-form))]
    (if (satisfies? m.protocols/IRedex system-term)
      (fn [form]
        (let [istate (m.state/make {:object form})
                  ilogic (make-logic istate)
                  result (m.protocols/-redex system-term ilogic)]
          (deref result)))
      (ex-info "system-form must parse to an object which satisfies meander.protocols.zeta/IRedex"
               {:system-form system-form
                :system-term system-term}))))

(defmacro create-system-fn
  [system-form options make-logic]
  `(let [env# (m.env/create ~{::m.env/eval (:eval ~options)
                              ::m.env/extensions (:notations ~options)})
         x# (create-system-fn* env# '~system-form ~make-logic)]
     (if (exception? x#)
       (throw x#)
       x#)))
