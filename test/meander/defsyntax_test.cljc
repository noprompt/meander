(ns meander.defsyntax-test
  (:require [clojure.test :as t :include-macros true]
            [meander.defsyntax-test.gh-145 :as gh-145 :refer [gen-map]]
            [meander.epsilon :as m :include-macros true]))

(t/deftest gh-145-test
  (t/is (= {1 2, 3 4}
           (m/rewrite [1 2 3 4]
             [!xs !ys ...]
             (gen-map !xs !ys))))

  (t/is (= {1 2, 3 4}
           (m/rewrite [1 2 3 4]
             [!xs !ys ...]
             (gh-145/gen-map !xs !ys)))))
