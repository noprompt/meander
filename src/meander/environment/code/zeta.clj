(ns meander.environment.code.zeta)

;; Depth First One
;; ---------------------------------------------------------------------

(def ^{:private true}
  none ::none)

(defn star-code [f & args]
  (let [loop__ (gensym "F__")
        args__ (repeatedly (count args) #(gensym "X__"))]
    `((fn* ~loop__ [~@args__] ~(apply f loop__ args__)) ~@args)))

(def depth-first-one
  (letfn [(bind [f x]
            (let [a (gensym "A__")]
              `(let* [~a ~x]
                 ~(f ~a))))

          (call [f & args]
            `(~f ~@args))

          (dual [a b]
            `(if ~a
               (if ~b
                 nil
                 ~a)
               nil))

          (eval [x]
            x)

          (fail [state]
            nil)

          (find [state id]
            id)

          (give [state object]
            `(assoc ~state :object ~object))

          (join [a b]
            (let [x (gensym "X__")]
              `(let* [~x a]
                 (if ~x
                   ~x
                   ~b))))

          (load [state id unfold pass fail]
            (let [pass* (fn [x new]
                          (let [new-state (gensym "X__")]
                            `(let* [~new-state (assoc ~state (quote ~id) new) x]
                               ~(pass (give new-state)))))
                  fail* (fn [x]
                          (fail state))]
              (let [entry (gensym "E__")
                    old (gensym "X__")]
                `(let* [~entry (clojure.core/find ~state '~id)
                        ~old (if ~entry (val ~entry) ~none)]
                   ~(unfold old pass* fail*)))))

          (make [state]
            `(reify))

          (mint [state]
            {:object `(get state :object)})

          (pass [state]
            state)

          (pick [a b]
            (let [x (gensym "X__")]
              `(let* [~x ~a]
                 (if ~x
                   ~x
                   ~b))))

          (save [state id fold new pass fail]
            (let [pass* (fn [new]
                          (let [new-state (gensym "X__")]
                            `(let* [~new-state (assoc ~state `(quote ~id) new)]
                               ~(pass new-state))))
                  fail* (fn [x]
                          (fail state))]
              (let [entry (gensym "E__")
                    old (gensym "X__")]
                `(let* [~entry (clojure.core/find ~state '~id)
                        ~old (if ~entry (val ~entry) ~none)]
                   ~(fold old new pass* fail*)))))

          (scan [f xs]
            (let [state (gensym "S__")
                  _ (gensym "X__")
                  y (gensym "X__")]
              `(reduce (fn* [~_ ~y]
                            (let* [~state ~(f y)]
                              (if ~state
                                (reduced ~state)
                                nil)))
                       nil
                       ~xs)))

          (seed [x]
            {:object x})

          (take [state]
            `(get ~state :object))

          (test [test then else]
            `(if ~test ~(then) ~(else)))

          (with [state mapping f]
            `(letfn [~@(map (fn [[id g]]
                              (let [state (gensym "X__")]
                                (list id [state] (g state))))
                         mapping)]
               ~(f state)))]
    {:bind bind
     :call call
     :dual dual
     :eval eval
     :fail fail
     :find find
     :give give
     :join join
     :load load
     :make make
     :mint mint
     :none none
     :pass pass
     :pick pick
     :save save
     :scan scan
     :seed seed
     :star star-code
     :take take
     :test test
     :with with}))

;; Depth First All
;; ---------------------------------------------------------------------

(def depth-first-all
  (letfn [(bind [f x]
            (let [a__ (gensym "A__")]
              `(mapcat (fn* [~a__] ~(f a__)) ~x)))

          (call [f & args]
            `(~f ~@args))

          (dual [a b]
            `(if (seq ~a)
               (if (seq ~b)
                 ()
                 ~a)
               ()))

          (eval [x]
            x)

          (fail [state]
            ())

          (give [state object]
            `(assoc ~state :object ~object))

          (join [a b]
            `(concat ~a ~b))

          (load [state id unfold pass fail]
            (let [entry__ (gensym "X__")
                  old__ (gensym "X__")]
              (let [pass* (fn [x new]
                            (pass (give `(assoc ~state '~id ~new) x)))
                    fail* (fn [x]
                            (fail state))]
                `(let [~entry__ (find ~state '~id)
                       ~old__ (if ~entry__ (val ~entry__) ~none)]
                   ~(unfold old__ pass* fail*)))))

          (make [state]
            `(reify))

          (pass [state]
            `(list ~state))

          (pick [a b]
            (let [m-a (gensym "M__")]
              `(let [~m-a ~a]
                 (if (seq ~m-a)
                   ~m-a
                   ~b))))

          (save [state id fold new pass fail]
            (let [entry__ (gensym "E__")
                  old__ (gensym "X__")
                  pass* (fn [new]
                          (pass `(assoc ~state '~id ~new)))
                  fail* (fn [x]
                          (fail state))]
              `(let [~entry__ (find ~state '~id)
                     ~old__ (if ~entry__ (val ~entry__) ~none)]
                 ~(fold old__ new pass* fail*))))

          (scan [f x]
            (let [y (gensym "X__")]
              `(mapcat (fn* [~y] ~(f y)) ~x)))

          (seed [x]
            {:object x})

          (take [state]
            (partial-evaluate
             `(get ~state :object)))

          (test [test then else]
            `(if ~test ~(then) ~(else)))

          (with [state mapping f]
            `(letfn [~@(map
                         (fn [[id g]]
                           (g (gensym "S__")))
                         mapping)]
               ~(f state)))]
    {:bind bind
     :call call
     :dual dual
     :eval eval
     :fail fail
     :give give
     :join join
     :load load
     :make make
     :none none
     :pass pass
     :pick pick
     :save save
     :scan scan
     :seed seed
     :star star-code
     :take take
     :test test
     :with with}))
