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


  (t/is (= #{[[:med/ids :meds] [4 "four"]]
             [[:med/ids :meds] [3 "three"]]}
           (set 
            (r.search/search {:med/ids [{:med/id 3}
                                        {:med/id 4}]
                              :meds [{:med/id 3
                                      :med/name "three"}
                                     {:med/id 10
                                      :med/name "ten"}
                                     {:med/id 4,
                                      :med/name "four"}]} 
              {?k1 (vscan {:med/id ?id})
               ?k2 (vscan {:med/id ?id, :med/name ?name})}
              [[?k1 ?k2] [?id ?name]])))))
