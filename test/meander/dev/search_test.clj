(ns meander.dev.search-test
  (:require [meander.dev.search :as r.search]
            [clojure.test :as t]))


(t/deftest search-test
  (t/is (= #{{:!ws [:A :B], :!xs [], :!ys [1 1 1 2 2], :!zs [:C :D]}
             {:!ws [:A :B], :!xs [1], :!ys [1 1 2 2], :!zs [:C :D]}
             {:!ws [:A :B], :!xs [1 1], :!ys [1 2 2], :!zs [:C :D]}
             {:!ws [:A :B], :!xs [1 1 1], :!ys [2 2], :!zs [:C :D]}
             {:!ws [:A :B], :!xs [1 1 1 2], :!ys [2], :!zs [:C :D]}
             {:!ws [:A :B], :!xs [1 1 1 2 2], :!ys [], :!zs [:C :D]}
             {:?a 1, :?b 2}
             {:!xs [[1 1 1 2 2]]}}
           (set
            (r.search/search [:A :B [1 1 1 2 2] :C :D] 
              [!ws ... . [!xs ... . !ys ...] . !zs ...]
              {:!ws !ws
               :!xs !xs
               :!ys !ys
               :!zs !zs}

              [!xs ... . [?a ... . ?b ...] . !ys ...]
              {:?a ?a
               :?b ?b}

              [:A :B . !xs ... . :C :D]
              {:!xs !xs}))))

  (t/is (= (list 10)
           (r.search/search 1
             (not [?x ... . ?y ...])
             10))))
