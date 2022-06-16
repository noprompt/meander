(ns meander.noprompt.dev-test
  (:require [clojure.test :as t]
            [meander.noprompt.dev :as m.dev]
            [meander.primitive.zeta :as m]
            [meander.primitive.character.zeta :as m.char]
            [meander.primitive.keyword.zeta :as m.keyword]
            [meander.primitive.integer.zeta :as m.int]
            [meander.primitive.string.zeta :as m.str]
            [meander.primitive.symbol.zeta :as m.symbol]
            [meander.random.zeta :as m.random]))

;; Helpers
;; ---------------------------------------------------------------------

(defn query-for
  ([f logicm p]
   (m.dev/-each (m.dev/-query p logicm)
     (fn [state]
       (m.dev/-pass logicm (f state))))))

(defn query-for-objects
  ([logicm p]
   (query-for m.dev/-get-object logicm p)))

(defn yield-for
  [f logicm p]
  (m.dev/-each (m.dev/-yield p logicm)
    (fn [state]
      (m.dev/-pass logicm (f state)))))

(defn yield-for-objects
  [logicm p]
  (yield-for m.dev/-get-object logicm p))

(defn query-for-projection
  [projection logicm p]
  (yield-for-objects (query-for identity logicm p) projection))

;; Tests
;; ---------------------------------------------------------------------

(t/deftest anything-test
  (let [logicm (list (m.dev/make-state {:object (rand)}))]
    (t/testing "Anything (Query)"
      (t/is (= logicm (query-for identity logicm (m/anything)))
            "Anything should always succeed"))

    (t/testing "anything (Yield)"
      (t/is (seq (yield-for identity logicm (m/anything)))
            "Anything should always succeed"))))

(t/deftest nothing-test
  (let [logicm (list (m.dev/make-state {:object (rand)}))]
    (t/testing "Nothing (Query)"
      (t/is (not (seq (query-for identity logicm (m/nothing))))
            "Nothing should always fail"))

    (t/testing "Nothing (Yield)"
      (t/is (not (seq (yield-for identity logicm (m/nothing))))
            "Nothing should always fail"))))

(t/deftest is-test
  (let [state (m.dev/make-state {:object true})
        logicm (list state)]
    (t/testing "Is (Query)"
      (t/is (= logicm
               (query-for identity logicm (m/is true))))

      (t/is (= ()
               (query-for identity logicm (m/is false)))))
     
    (t/testing "Is (Yield)"
      (t/is (= (list false)
               (yield-for-objects logicm (m/is false)))))))

(t/deftest some-test
  (let [state (m.dev/make-state {:object true})
        logicm (list state)]
    (t/testing "Some (Query)"
      (t/is (= (list true true)
               (query-for-objects logicm (m/some (m/anything) (m/anything)))))

      (t/is (= (list true)
               (query-for-objects logicm (m/some (m/nothing) (m/anything)))))

      (t/is (= (list true)
               (query-for-objects logicm (m/some (m/anything) (m/nothing)))))

      (t/is (= (list)
               (query-for-objects logicm (m/some (m/nothing) (m/nothing))))))

    (t/testing "Some (Yield)"
      (t/is (= (list true false)
               (yield-for-objects logicm (m/some (m/is true) (m/is false)))))

      (t/is (= (list true)
               (yield-for-objects logicm (m/some (m/is true) (m/nothing)))))

      (t/is (= (list true)
               (yield-for-objects logicm (m/some (m/nothing) (m/is true)))))

      (t/is (= (list)
               (yield-for-objects logicm (m/some (m/nothing) (m/nothing))))))))

(t/deftest pick-test
  (let [state (m.dev/make-state {:object true})
        logicm (list state)]
    (t/testing "Pick (Query)"
      (t/is (= (list true)
               (query-for-objects logicm (m/pick (m/anything) (m/anything)))))

      (t/is (= (list true)
               (query-for-objects logicm (m/pick (m/nothing) (m/anything)))))

      (t/is (= (list true)
               (query-for-objects logicm (m/pick (m/anything) (m/nothing)))))

      (t/is (= (list)
               (query-for-objects logicm (m/pick (m/nothing) (m/nothing))))))

    (t/testing "Pick (Yield)"
      (t/is (= (list true)
               (yield-for-objects logicm (m/pick (m/is true) (m/is false)))))

      (t/is (= (list true)
               (yield-for-objects logicm (m/pick (m/is true) (m/nothing)))))

      (t/is (= (list true)
               (yield-for-objects logicm (m/pick (m/nothing) (m/is true)))))

      (t/is (= (list)
               (yield-for-objects logicm (m/pick (m/nothing) (m/nothing))))))))

(t/deftest each
  (t/testing "Each (Query)"
    (let [state (m.dev/make-state {:object true})
          logicm (list state)]
      (t/is (= (list true)
               (query-for-objects logicm (m/each (m/anything) (m/anything)))))

      (t/is (= (list)
               (query-for-objects logicm (m/each (m/nothing) (m/anything)))))

      (t/is (= (list)
               (query-for-objects logicm (m/each (m/anything) (m/nothing)))))

      (t/is (= (list)
               (query-for-objects logicm (m/each (m/nothing) (m/nothing)))))))

  (t/testing "Each (Yield)"
    (let [state (m.dev/make-state {:object true})
          logicm (list state)
          a true
          b false
          c (rand-nth [a b])]
      (t/is (= (list c c)
               (yield-for-objects logicm (m/each (m/is c) (m/some (m/is a) (m/is b))))))

      (t/is (= (list c c)
               (yield-for-objects logicm (m/each (m/some (m/is a) (m/is b)) (m/is c)))))

      (t/is (= (list)
               (yield-for-objects logicm (m/each (m/is c) (m/nothing))))))))

(t/deftest logic-variable-test
  (t/testing "Logic Variable (Query)"
    (let [state (m.dev/make-state {:object true})
          logicm (list state)
          ?1 (m/? 1)]
      (t/is (= (list true)
               (query-for-projection ?1 logicm ?1)))

      (t/is (= (list true)
               (query-for-projection ?1 logicm (m/each ?1 ?1))))))

  (t/testing "Logic Variable (Yield)"
    (let [?1 (m/? 1)
          state (m.dev/make-state {:object true})
          state (m.dev/-set-variable state ?1 false)
          logicm (list state)]
      (t/is (= (list)
               (query-for-projection ?1 logicm ?1)))

      (t/is (= (list false)
               (yield-for-objects logicm ?1))))))

;; Atomic
;; ---------------------------------------------------------------------

;; Numbers

(t/deftest integer-in-range-test
  (let [uppercase (m.int/in-range (m/is 65) (m/is (+ 65 26)))
        rnd-state (m.dev/make-state {})
        y-answers (m.dev/-yield uppercase (list rnd-state))
        q-answers (m.dev/-query uppercase y-answers)]
    (t/is (= 26 (count (take 27 (map :object y-answers)))))

    (t/testing "Yielding integers in a range produces each element in the range once."
      (t/is (= {65 1, 66 1, 67 1, 68 1, 69 1, 70 1, 71 1, 72 1, 73 1, 74 1, 75 1, 76 1, 77 1, 78 1, 79 1, 80 1, 81 1, 82 1, 83 1, 84 1, 85 1, 86 1, 87 1, 88 1, 89 1, 90 1}
               (frequencies (take 27 (map :object y-answers))))))

    (t/testing "Every yielded answer should successfully query"
      (t/is (= y-answers q-answers)))))

;; Character

(t/deftest character-in-range-test
  (let [uppercase (m.char/in-range (m/is 65) (m/is (+ 65 26)))
        rnd-state (m.dev/make-state {})
        y-answers (m.dev/-yield uppercase (list rnd-state))
        q-answers (m.dev/-query uppercase y-answers)]
    (t/testing "Yielding characters in a range produces each element in the range once."
      (t/is (= {\A 1, \B 1, \C 1, \D 1, \E 1, \F 1, \G 1, \H 1, \I 1, \J 1, \K 1, \L 1, \M 1, \N 1, \O 1, \P 1, \Q 1, \R 1, \S 1, \T 1, \U 1, \V 1, \W 1, \X 1, \Y 1, \Z 1}
               (frequencies (take 26 (map :object y-answers))))))

    (t/testing "Every yielded answer should successfully query"
      (t/is (= y-answers q-answers)))))

;; String

(t/deftest str-test
  (let [fan-fin-fun (m/str (m/is "f")
                           (m/some (m/is "a") (m/is "i") (m/is "u"))
                           (m/is "n"))]
    (t/is (= (list 1 3 5)
             (map :seed (m.dev/-query fan-fin-fun (list (m.dev/make-state {:object "fan" :seed 1})
                                                        (m.dev/make-state {:object "fen" :seed 2})
                                                        (m.dev/make-state {:object "fin" :seed 3})
                                                        (m.dev/make-state {:object "fon" :seed 4})
                                                        (m.dev/make-state {:object "fun" :seed 5}))))))

    (t/is (= (list "fan" "fun" "fin")
             (map :object (m.dev/-yield fan-fin-fun (list (m.dev/make-state {}))))))

    (t/is 3
          (count (m.dev/-query fan-fin-fun (m.dev/-yield fan-fin-fun (list (m.dev/make-state {}))))))))

;; Keyword

(t/deftest keyword-test
  (let [p (m.keyword/any)]
    (t/testing "KeywordAny (query)"
      (t/is (seq (query-for identity (list (m.dev/make-state {:object :foo})) p)))
      (t/is (not (seq (query-for identity (list (m.dev/make-state {:object 'foo})) p)))))

    (t/testing "KeywordAny (yield)"
      ;; Should fail
      (t/is (seq (m.dev/-yield p (list (m.dev/make-state {:object nil})))))))

  (let [p (m/keyword (m/is "foo"))]
    (t/testing "KeywordUnqualified (query)"
      (t/is (seq (query-for identity (list (m.dev/make-state {:object :foo})) p)))
      (t/is (not (seq (query-for identity (list (m.dev/make-state {:object 'foo})) p)))))

    (t/testing "KeywordUnqualified (yield)"
      (t/is (= '(:foo)
               (yield-for-objects (list (m.dev/make-state {:object nil})) p)))))

  (let [p (m/keyword (m/is "foo") (m/is "bar"))]
    (t/testing "KeywordQualified (query)"
      (t/is (seq (query-for identity (list (m.dev/make-state {:object :foo/bar})) p)))
      (t/is (not (seq (query-for identity (list (m.dev/make-state {:object 'foo})) p)))))

    (t/testing "KeywordQualified (yield)"
      (t/is (= '(:foo/bar)
               (yield-for-objects (list (m.dev/make-state {:object nil})) p))))))


;; Symbol

(t/deftest symbol-test
  (let [p (m.symbol/any)]
    (t/testing "SymbolAny (query)"
      (t/is (seq (query-for identity (list (m.dev/make-state {:object 'foo})) p)))
      (t/is (not (seq (query-for identity (list (m.dev/make-state {:object :foo})) p)))))

    (t/testing "SymbolAny (yield)"
      ;; Should fail
      (t/is (seq (m.dev/-yield p (list (m.dev/make-state {:object nil})))))))

  (let [p (m/symbol (m/is "foo"))]
    (t/testing "SymbolUnqualified (query)"
      (t/is (seq (query-for identity (list (m.dev/make-state {:object 'foo})) p)))
      (t/is (not (seq (query-for identity (list (m.dev/make-state {:object :foo})) p)))))

    (t/testing "SymbolUnqualified (yield)"
      (t/is (= '(foo)
               (yield-for-objects (list (m.dev/make-state {:object nil})) p)))))

  (let [p (m/symbol (m/is "foo") (m/is "bar"))]
    (t/testing "SymbolQualified (query)"
      (t/is (seq (query-for identity (list (m.dev/make-state {:object 'foo/bar})) p)))
      (t/is (not (seq (query-for identity (list (m.dev/make-state {:object :foo})) p)))))

    (t/testing "SymbolQualified (yield)"
      (t/is (= '(foo/bar)
               (yield-for-objects (list (m.dev/make-state {:object nil})) p))))))


;; Collection
;; ---------------------------------------------------------------------


;; Rule, System
;; ---------------------------------------------------------------------
