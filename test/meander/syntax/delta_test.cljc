(ns meander.syntax.delta-test
  (:require
   [clojure.test :as t]
   [meander.match.delta :as r.match]
   [meander.syntax.delta :as r.syntax :include-macros true]))


(t/deftest match-bindings-test
  (t/is (= #{}
           (r.syntax/match-bindings
            (r.syntax/parse '(not [?x])))))
  (t/is (= #{{:tag :lvr, :symbol '?x}}
           (r.syntax/match-bindings
            (r.syntax/parse '(not (not [?x])))))))

(r.syntax/defsyntax $cons [?head ?tail]
  `(~'pred clojure.core/seq?
    (~'app clojure.core/first ~?head)
    (~'app clojure.core/rest ~?tail)))

(t/deftest defsyntax-test
  ;; $cons will be fully qualified.
  (t/is (= [1 '(2 3)]
           (r.match/find '(1 2 3)
             ($cons ?first ?rest)
             [?first ?rest])))

  ;; Test fully qualified symbol resolution.
  (t/is (= [1 '(2 3)]
           (r.match/find '(1 2 3)
             (meander.syntax.delta-test/$cons ?first ?rest)
             [?first ?rest])))

  (t/is (= 1
           (r.match/find '(1 2 3)
             ($cons ?first (2 3))
             ?first)))

  (t/is (= '(2 3)
           (r.match/find '(1 2 3)
             ($cons 1 ?rest)
             ?rest)))

  (t/is (= [1 2]
           (r.match/find '(1 (1 2) 2)
             ($cons ?x ($cons ($cons ?x (?y)) (?y)))
             [?x ?y]))))
