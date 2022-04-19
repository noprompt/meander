(ns meander.noprompt.dev-test
  (:require [clojure.test :as t]
            [meander.noprompt.dev :as m.dev]
            [meander.primitive.zeta :as m]
            [meander.primitive.string.zeta :as m.str]
            [meander.primitive.character.zeta :as m.char]
            [meander.random.zeta :as m.random]))

(t/deftest anything-test
  (let [m (list (m.dev/make-state {}))]
    (t/testing "anything"
      (t/is (= m
               (m.dev/-query (m/anything) m))))))

(t/deftest is-test
  (let [s (m.dev/make-state {:object 1})
        m (list s)]
    (t/testing "is"
      (t/is (= (list s)
               (m.dev/-query (m/is 1) m)))

      (t/is (= ()
               (m.dev/-query (m/is 2) m)))

      (t/is (= (list s s)
               (m.dev/-query (m/some (m/is 1) (m/is 1)) m)))

      (t/is (= (list s)
               (m.dev/-query (m/some (m/is 0) (m/is 1)) m)))

      (t/is (= ()
               (m.dev/-query (m/some (m/is 0) (m/is 2)) m)))

      (t/is (= (list s)
               (m.dev/-query (m/each (m/is 1) (m/anything)) m)))

      (t/is (= ()
               (m.dev/-query (m/each (m/is 1) (m/is 0)) m)))

      (t/is (= (list s)
               (m.dev/-query (m/not (m/is 2)) m)))

      (t/is (= (list (m.dev/-set-object s 1))
               (m.dev/-yield (m/is 1) m))))))

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
