(ns food-example-readme
  (:require [meander.epsilon :as m]))


;; Single Favorite Food

(defn favorite-food-info [foods-by-name user]
  (m/match {:user user
            :foods-by-name foods-by-name}
    {:user
     {:name ?name
      :favorite-food {:name ?food}}
     :foods-by-name {?food {:popularity ?popularity
                            :calories ?calories}}}
    {:name ?name
     :favorite {:food ?food
                :popularity ?popularity
                :calories ?calories}}))

(def foods-by-name
  {:nachos {:popularity :high
            :calories :lots}
   :smoothie {:popularity :high
              :calories :less}})


(favorite-food-info
 foods-by-name
 {:name :alice
  :favorite-food {:name :nachos}})

;; =>

;; {:name :alice,
;;  :favorite {:food :nachos, :popularity :high, :calories :lots}}




;; Multiple favorite foods

(defn favorite-foods-info [foods-by-name user]
  (m/search {:user user
             :foods-by-name foods-by-name}
    {:user
     {:name ?name
      :favorite-foods (m/scan {:name ?food})}
     :foods-by-name {?food {:popularity ?popularity
                            :calories ?calories}}}
    {:name ?name
     :favorite {:food ?food
                :popularity ?popularity
                :calories ?calories}}))

(favorite-foods-info
 foods-by-name
 {:name :alice
  :favorite-foods [{:name :nachos} {:name :smoothie}]})

;; =>
;; ({:name :alice,
;;   :favorite {:food :nachos, :popularity :high, :calories :lots}}
;;  {:name :alice,
;;   :favorite {:food :smoothie, :popularity :high, :calories :less}})



;; Grabbing the food

(defn grab-all-foods [user]
  (m/find user
    {:favorite-foods [{:name !foods} ...]
     :special-food !food
     :recipes [{:title !foods} ...]
     :meal-plan {:breakfast [{:food !foods} ...]
                 :lunch [{:food !foods} ...]
                 :dinner [{:food !foods} ...]}}
    !foods))

(grab-all-foods
 {:favorite-foods [{:name :food1} {:name :food2}]
  :special-food :food3
  :recipes [{:title :food4} {:title :food5} {:title :food6}]
  :meal-plan {:breakfast [{:food :food7}]
              :lunch []
              :dinner [{:food :food8}]}})

;; =>
;; [:food1 :food2 :food4 :food5 :food6 :food7 :food8]
