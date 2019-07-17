(ns meander.syntax.epsilon-test
  (:require
   [clojure.test :as t]
   [meander.epsilon :as r :include-macros true]
   [meander.syntax.epsilon :as r.syntax :include-macros true]))

(r.syntax/defsyntax $cons [?head ?tail]
  `(r/pred seq? (r/app first ~?head) (r/app rest ~?tail)))

(t/deftest defsyntax-test
  ;; $cons will be fully qualified.
  (t/is (= [1 '(2 3)]
           (r/find '(1 2 3)
             ($cons ?first ?rest)
             [?first ?rest])))

  ;; Test fully qualified symbol resolution.
  (t/is (= [1 '(2 3)]
           (r/find '(1 2 3)
             (meander.syntax.epsilon-test/$cons ?first ?rest)
             [?first ?rest])))

  (t/is (= 1
           (r/find '(1 2 3)
             ($cons ?first (2 3))
             ?first)))

  (t/is (= '(2 3)
           (r/find '(1 2 3)
             ($cons 1 ?rest)
             ?rest)))

  (t/is (= [1 2]
           (r/find '(1 (1 2) 2)
             ($cons ?x ($cons ($cons ?x (?y)) (?y)))
             [?x ?y]))))
