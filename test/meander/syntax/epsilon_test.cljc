(ns meander.syntax.epsilon-test
  (:require
   [clojure.test :as t]
   [meander.epsilon :as m :include-macros true]
   [meander.syntax.epsilon :as m.syntax :include-macros true]))

(m.syntax/defsyntax $cons [?head ?tail]
  `(m/pred seq? (m/app first ~?head) (m/app rest ~?tail)))

(t/deftest defsyntax-test
  ;; $cons will be fully qualified.
  (t/is (= [1 '(2 3)]
           (m/find '(1 2 3)
             ($cons ?first ?rest)
             [?first ?rest])))

  ;; Test fully qualified symbol resolution.
  (t/is (= [1 '(2 3)]
           (m/find '(1 2 3)
             (meander.syntax.epsilon-test/$cons ?first ?rest)
             [?first ?rest])))

  (t/is (= 1
           (m/find '(1 2 3)
             ($cons ?first (2 3))
             ?first)))

  (t/is (= '(2 3)
           (m/find '(1 2 3)
             ($cons 1 ?rest)
             ?rest)))

  (t/is (= [1 2]
           (m/find '(1 (1 2) 2)
             ($cons ?x ($cons ($cons ?x (?y)) (?y)))
             [?x ?y]))))


(t/deftest set-unparse-test
  (let [form #{1 2 3}
        node (m.syntax/parse form)]
    (t/is (= form
             (m.syntax/unparse node))))

  (let [form '#{^:as ?foo}
        node (m.syntax/parse form)]
    (t/is (= {:as true}
             (meta (first (m.syntax/unparse node))))))

  (let [form '#{^& ?foo}
        node (m.syntax/parse form)]
    (t/is (= {:tag '&}
             (meta (first (m.syntax/unparse node)))))))
