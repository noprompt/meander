(ns meander.dev.search-test
  (:require [meander.dev.search :as r.search]
            [clojure.test :as t]))


(t/deftest search-test
  ;; Note: This shows that queries are processed in approximately the
  ;; order they appear which may not be desirable. The stream of
  ;; results should be able to be interleaved as in miniKanren.
  (t/is (= [{:!ws [:A :B], :!xs [], :!ys [1 1 1 2 2], :!zs [:C :D]}
            {:!ws [:A :B], :!xs [1], :!ys [1 1 2 2], :!zs [:C :D]}
            {:!ws [:A :B], :!xs [1 1], :!ys [1 2 2], :!zs [:C :D]}
            {:!ws [:A :B], :!xs [1 1 1], :!ys [2 2], :!zs [:C :D]}
            {:!ws [:A :B], :!xs [1 1 1 2], :!ys [2], :!zs [:C :D]}
            {:!ws [:A :B], :!xs [1 1 1 2 2], :!ys [], :!zs [:C :D]}
            {:?a 1, :?b 2}
            {:!xs [[1 1 1 2 2]]}]
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

