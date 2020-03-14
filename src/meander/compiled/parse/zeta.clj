(ns meander.compiled.parse.zeta (:require [meander.runtime.zeta]))
(clojure.core/defn
 parse
 [input__55632]
 (let*
  [ret__8106__auto__
   (clojure.core/letfn
    [(CATA__FN__55682
      [input__55632]
      (clojure.core/letfn
       [(state__56670
         []
         (if
          (clojure.core/vector? input__55632)
          (if
           (clojure.core/= (clojure.core/count input__55632) 3)
           (clojure.core/let
            [input__55632_nth_0__
             (clojure.core/nth input__55632 0)
             input__55632_nth_1__
             (clojure.core/nth input__55632 1)
             input__55632_nth_2__
             (clojure.core/nth input__55632 2)]
            (clojure.core/case
             input__55632_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__55632_nth_1__)
              (clojure.core/letfn
               [(state__56695
                 []
                 (clojure.core/case
                  input__55632_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__55632_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__10169__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10169__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10169__auto__)))))
                  (state__56696)))
                (state__56696
                 []
                 (clojure.core/let
                  [n__55689
                   (clojure.core/count input__55632_nth_1__)
                   m__55690
                   (clojure.core/max 0 (clojure.core/- n__55689 2))
                   input__55632_nth_1___l__
                   (clojure.core/subvec
                    input__55632_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__55632_nth_1__)
                     m__55690))
                   input__55632_nth_1___r__
                   (clojure.core/subvec input__55632_nth_1__ m__55690)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__55632_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__55632_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__55632_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__55632_nth_1___r___nth_0__
                       (clojure.core/nth input__55632_nth_1___r__ 0)
                       input__55632_nth_1___r___nth_1__
                       (clojure.core/nth input__55632_nth_1___r__ 1)]
                      (clojure.core/case
                       input__55632_nth_1___r___nth_0__
                       (:meander.zeta/as)
                       (clojure.core/let
                        [?pattern input__55632_nth_1___r___nth_1__]
                        (clojure.core/let
                         [?env input__55632_nth_2__]
                         (try
                          [(clojure.core/let
                            [!xs__counter
                             (meander.runtime.zeta/iterator !xs)]
                            {:tag :as,
                             :pattern
                             (clojure.core/let
                              [CATA_RESULT__9229__auto__
                               (CATA__FN__55682 [?pattern ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__9229__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__9229__auto__
                                0))),
                             :next
                             (clojure.core/let
                              [CATA_RESULT__9229__auto__
                               (CATA__FN__55682
                                ['meander.dev.parse.zeta/parse-sequential
                                 (clojure.core/into
                                  []
                                  (clojure.core/vec
                                   (clojure.core/iterator-seq
                                    !xs__counter)))
                                 ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__9229__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__9229__auto__
                                0)))})]
                          (catch
                           java.lang.Exception
                           e__10169__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__10169__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__10169__auto__))))))
                       (state__56671)))
                     (state__56671)))
                   (state__56671))))]
               (state__56695))
              (state__56671))
             (state__56671)))
           (state__56671))
          (state__56671)))
        (state__56671
         []
         (clojure.core/letfn
          [(def__55695
            [arg__55730 ?ns]
            (clojure.core/letfn
             [(state__56697
               []
               (clojure.core/let
                [x__55731 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__55731)
                 (clojure.core/let [?env arg__55730] [?env ?ns])
                 (state__56698))))
              (state__56698
               []
               (if
                (clojure.core/map? arg__55730)
                (clojure.core/let
                 [VAL__55732 (.valAt arg__55730 :aliases)]
                 (if
                  (clojure.core/map? VAL__55732)
                  (clojure.core/let
                   [X__55734 (clojure.core/set VAL__55732)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__55734))
                    (clojure.core/loop
                     [search_space__56699 (clojure.core/seq X__55734)]
                     (if
                      (clojure.core/seq search_space__56699)
                      (clojure.core/let
                       [elem__55735
                        (clojure.core/first search_space__56699)
                        result__56700
                        (clojure.core/let
                         [elem__55735_nth_0__
                          (clojure.core/nth elem__55735 0)
                          elem__55735_nth_1__
                          (clojure.core/nth elem__55735 1)]
                         (if
                          (clojure.core/symbol? elem__55735_nth_0__)
                          (clojure.core/let
                           [X__55737
                            (clojure.core/name elem__55735_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__55737)
                            (if
                             (clojure.core/symbol? elem__55735_nth_1__)
                             (clojure.core/let
                              [X__55739
                               (clojure.core/name elem__55735_nth_1__)]
                              (clojure.core/case
                               X__55739
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__55730]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__56700)
                        (recur (clojure.core/next search_space__56699))
                        result__56700))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__56697)))]
          (if
           (clojure.core/vector? input__55632)
           (if
            (clojure.core/= (clojure.core/count input__55632) 3)
            (clojure.core/let
             [input__55632_nth_0__
              (clojure.core/nth input__55632 0)
              input__55632_nth_1__
              (clojure.core/nth input__55632 1)
              input__55632_nth_2__
              (clojure.core/nth input__55632 2)]
             (clojure.core/case
              input__55632_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__55632_nth_1__)
               (clojure.core/loop
                [search_space__56702
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__55632_nth_1__)]
                (if
                 (clojure.core/seq search_space__56702)
                 (clojure.core/let
                  [input__55632_nth_1___parts__
                   (clojure.core/first search_space__56702)
                   result__56703
                   (clojure.core/let
                    [input__55632_nth_1___l__
                     (clojure.core/nth input__55632_nth_1___parts__ 0)
                     input__55632_nth_1___r__
                     (clojure.core/nth input__55632_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__55632_nth_1___l__)]
                     (clojure.core/let
                      [input__55632_nth_1___r___l__
                       (clojure.core/subvec
                        input__55632_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__55632_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__55632_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__55632_nth_1___r___r__
                         (clojure.core/subvec
                          input__55632_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__55632_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__55632_nth_1___r___l__
                           0)
                          input__55632_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__55632_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__55632_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__55704
                            (clojure.core/namespace
                             input__55632_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__55704]
                            (clojure.core/let
                             [X__55706
                              (clojure.core/name
                               input__55632_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__55706)
                              (clojure.core/let
                               [ret__55707
                                (clojure.core/re-matches
                                 #"&(\d+)"
                                 X__55706)]
                               (if
                                (clojure.core/some? ret__55707)
                                (if
                                 (clojure.core/vector? ret__55707)
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count ret__55707)
                                   2)
                                  (clojure.core/let
                                   [ret__55707_nth_1__
                                    (clojure.core/nth ret__55707 1)]
                                   (clojure.core/let
                                    [?n ret__55707_nth_1__]
                                    (clojure.core/let
                                     [?pattern
                                      input__55632_nth_1___r___l___nth_1__]
                                     (clojure.core/let
                                      [?rest
                                       input__55632_nth_1___r___r__]
                                      (clojure.core/let
                                       [x__7926__auto__
                                        (def__55695
                                         input__55632_nth_2__
                                         ?ns)]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         x__7926__auto__)
                                        (meander.runtime.zeta/fail)
                                        (clojure.core/let
                                         [[?env ?ns] x__7926__auto__]
                                         (try
                                          [(clojure.core/let
                                            [!init__counter
                                             (meander.runtime.zeta/iterator
                                              !init)]
                                            (clojure.core/let
                                             [CATA_RESULT__9229__auto__
                                              (CATA__FN__55682
                                               ['meander.dev.parse.zeta/make-join
                                                (clojure.core/let
                                                 [CATA_RESULT__9229__auto__
                                                  (CATA__FN__55682
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !init__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9229__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9229__auto__
                                                   0)))
                                                (clojure.core/let
                                                 [CATA_RESULT__9229__auto__
                                                  (CATA__FN__55682
                                                   ['meander.dev.parse.zeta/make-join
                                                    {:tag :slice,
                                                     :size
                                                     (Integer. ?n),
                                                     :pattern
                                                     (clojure.core/let
                                                      [CATA_RESULT__9229__auto__
                                                       (CATA__FN__55682
                                                        [?pattern
                                                         ?env])]
                                                      (if
                                                       (meander.runtime.zeta/fail?
                                                        CATA_RESULT__9229__auto__)
                                                       (throw
                                                        (meander.runtime.zeta/fail))
                                                       (clojure.core/nth
                                                        CATA_RESULT__9229__auto__
                                                        0)))}
                                                    (clojure.core/let
                                                     [CATA_RESULT__9229__auto__
                                                      (CATA__FN__55682
                                                       ['meander.dev.parse.zeta/parse-sequential
                                                        (clojure.core/into
                                                         []
                                                         ?rest)
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__9229__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__9229__auto__
                                                       0)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9229__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9229__auto__
                                                   0)))
                                                ?env])]
                                             (if
                                              (meander.runtime.zeta/fail?
                                               CATA_RESULT__9229__auto__)
                                              (throw
                                               (meander.runtime.zeta/fail))
                                              (clojure.core/nth
                                               CATA_RESULT__9229__auto__
                                               0))))]
                                          (catch
                                           java.lang.Exception
                                           e__10169__auto__
                                           (if
                                            (meander.runtime.zeta/fail?
                                             e__10169__auto__)
                                            (meander.runtime.zeta/fail)
                                            (throw
                                             e__10169__auto__)))))))))))
                                  (meander.runtime.zeta/fail))
                                 (meander.runtime.zeta/fail))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__56703)
                   (recur (clojure.core/next search_space__56702))
                   result__56703))
                 (state__56672)))
               (state__56672))
              (state__56672)))
            (state__56672))
           (state__56672))))
        (state__56672
         []
         (clojure.core/letfn
          [(def__55752
            [arg__55784 ?ns]
            (clojure.core/letfn
             [(state__56705
               []
               (clojure.core/let
                [x__55785 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__55785)
                 (clojure.core/let [?env arg__55784] [?env ?ns])
                 (state__56706))))
              (state__56706
               []
               (if
                (clojure.core/map? arg__55784)
                (clojure.core/let
                 [VAL__55786 (.valAt arg__55784 :aliases)]
                 (if
                  (clojure.core/map? VAL__55786)
                  (clojure.core/let
                   [X__55788 (clojure.core/set VAL__55786)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__55788))
                    (clojure.core/loop
                     [search_space__56707 (clojure.core/seq X__55788)]
                     (if
                      (clojure.core/seq search_space__56707)
                      (clojure.core/let
                       [elem__55789
                        (clojure.core/first search_space__56707)
                        result__56708
                        (clojure.core/let
                         [elem__55789_nth_0__
                          (clojure.core/nth elem__55789 0)
                          elem__55789_nth_1__
                          (clojure.core/nth elem__55789 1)]
                         (if
                          (clojure.core/symbol? elem__55789_nth_0__)
                          (clojure.core/let
                           [X__55791
                            (clojure.core/name elem__55789_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__55791)
                            (if
                             (clojure.core/symbol? elem__55789_nth_1__)
                             (clojure.core/let
                              [X__55793
                               (clojure.core/name elem__55789_nth_1__)]
                              (clojure.core/case
                               X__55793
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__55784]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__56708)
                        (recur (clojure.core/next search_space__56707))
                        result__56708))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__56705)))]
          (if
           (clojure.core/vector? input__55632)
           (if
            (clojure.core/= (clojure.core/count input__55632) 3)
            (clojure.core/let
             [input__55632_nth_0__
              (clojure.core/nth input__55632 0)
              input__55632_nth_1__
              (clojure.core/nth input__55632 1)
              input__55632_nth_2__
              (clojure.core/nth input__55632 2)]
             (clojure.core/case
              input__55632_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__55632_nth_1__)
               (clojure.core/loop
                [search_space__56710
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__55632_nth_1__)]
                (if
                 (clojure.core/seq search_space__56710)
                 (clojure.core/let
                  [input__55632_nth_1___parts__
                   (clojure.core/first search_space__56710)
                   result__56711
                   (clojure.core/let
                    [input__55632_nth_1___l__
                     (clojure.core/nth input__55632_nth_1___parts__ 0)
                     input__55632_nth_1___r__
                     (clojure.core/nth input__55632_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__55632_nth_1___l__)]
                     (clojure.core/let
                      [input__55632_nth_1___r___l__
                       (clojure.core/subvec
                        input__55632_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__55632_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__55632_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__55632_nth_1___r___r__
                         (clojure.core/subvec
                          input__55632_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__55632_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__55632_nth_1___r___l__
                           0)
                          input__55632_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__55632_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__55632_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__55761
                            (clojure.core/namespace
                             input__55632_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__55761]
                            (clojure.core/let
                             [X__55763
                              (clojure.core/name
                               input__55632_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__55763)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__55763)
                               (clojure.core/let
                                [?pattern
                                 input__55632_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__55632_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__7926__auto__
                                   (def__55752
                                    input__55632_nth_2__
                                    ?ns)]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    x__7926__auto__)
                                   (meander.runtime.zeta/fail)
                                   (clojure.core/let
                                    [[?env ?ns] x__7926__auto__]
                                    (try
                                     [(clojure.core/let
                                       [!init__counter
                                        (meander.runtime.zeta/iterator
                                         !init)]
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__55682
                                          ['meander.dev.parse.zeta/make-join
                                           (clojure.core/let
                                            [CATA_RESULT__9229__auto__
                                             (CATA__FN__55682
                                              ['meander.dev.parse.zeta/parse-sequential
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !init__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9229__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9229__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__9229__auto__
                                             (CATA__FN__55682
                                              ['meander.dev.parse.zeta/make-join
                                               (clojure.core/let
                                                [CATA_RESULT__9229__auto__
                                                 (CATA__FN__55682
                                                  [?pattern ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__9229__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__9229__auto__
                                                  0)))
                                               (clojure.core/let
                                                [CATA_RESULT__9229__auto__
                                                 (CATA__FN__55682
                                                  ['meander.dev.parse.zeta/parse-sequential
                                                   (clojure.core/into
                                                    []
                                                    ?rest)
                                                   ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__9229__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__9229__auto__
                                                  0)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9229__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9229__auto__
                                              0)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9229__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9229__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__10169__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10169__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10169__auto__)))))))))
                               (meander.runtime.zeta/fail))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__56711)
                   (recur (clojure.core/next search_space__56710))
                   result__56711))
                 (state__56673)))
               (state__56673))
              (state__56673)))
            (state__56673))
           (state__56673))))
        (state__56673
         []
         (if
          (clojure.core/vector? input__55632)
          (clojure.core/letfn
           [(state__56713
             []
             (if
              (clojure.core/= (clojure.core/count input__55632) 3)
              (clojure.core/let
               [input__55632_nth_0__
                (clojure.core/nth input__55632 0)
                input__55632_nth_1__
                (clojure.core/nth input__55632 1)
                input__55632_nth_2__
                (clojure.core/nth input__55632 2)]
               (clojure.core/case
                input__55632_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__55632_nth_1__)
                 (clojure.core/letfn
                  [(state__56717
                    []
                    (clojure.core/let
                     [n__55814
                      (clojure.core/count input__55632_nth_1__)
                      m__55815
                      (clojure.core/max 0 (clojure.core/- n__55814 2))
                      input__55632_nth_1___l__
                      (clojure.core/subvec
                       input__55632_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__55632_nth_1__)
                        m__55815))
                      input__55632_nth_1___r__
                      (clojure.core/subvec
                       input__55632_nth_1__
                       m__55815)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__55632_nth_1___r__)
                       2)
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__55632_nth_1___l__)]
                       (if
                        (clojure.core/=
                         (clojure.core/count input__55632_nth_1___r__)
                         2)
                        (clojure.core/let
                         [input__55632_nth_1___r___nth_0__
                          (clojure.core/nth input__55632_nth_1___r__ 0)
                          input__55632_nth_1___r___nth_1__
                          (clojure.core/nth
                           input__55632_nth_1___r__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__55632_nth_1___r___nth_0__)
                          (clojure.core/let
                           [X__55819
                            (clojure.core/namespace
                             input__55632_nth_1___r___nth_0__)]
                           (clojure.core/let
                            [?ns X__55819]
                            (clojure.core/let
                             [X__55821
                              (clojure.core/name
                               input__55632_nth_1___r___nth_0__)]
                             (if
                              (clojure.core/string? X__55821)
                              (clojure.core/let
                               [ret__55822
                                (clojure.core/re-matches
                                 #"&.*"
                                 X__55821)]
                               (if
                                (clojure.core/some? ret__55822)
                                (clojure.core/let
                                 [?name ret__55822]
                                 (clojure.core/let
                                  [?pattern
                                   input__55632_nth_1___r___nth_1__]
                                  (if
                                   (clojure.core/map?
                                    input__55632_nth_2__)
                                   (clojure.core/let
                                    [VAL__55806
                                     (.valAt
                                      input__55632_nth_2__
                                      :aliases)]
                                    (if
                                     (clojure.core/map? VAL__55806)
                                     (clojure.core/let
                                      [X__55808
                                       (clojure.core/set VAL__55806)]
                                      (if
                                       (clojure.core/<=
                                        1
                                        (clojure.core/count X__55808))
                                       (clojure.core/loop
                                        [search_space__56721
                                         (clojure.core/seq X__55808)]
                                        (if
                                         (clojure.core/seq
                                          search_space__56721)
                                         (clojure.core/let
                                          [elem__55809
                                           (clojure.core/first
                                            search_space__56721)
                                           result__56722
                                           (clojure.core/let
                                            [elem__55809_nth_0__
                                             (clojure.core/nth
                                              elem__55809
                                              0)
                                             elem__55809_nth_1__
                                             (clojure.core/nth
                                              elem__55809
                                              1)]
                                            (if
                                             (clojure.core/symbol?
                                              elem__55809_nth_0__)
                                             (clojure.core/let
                                              [X__55811
                                               (clojure.core/name
                                                elem__55809_nth_0__)]
                                              (if
                                               (clojure.core/=
                                                ?ns
                                                X__55811)
                                               (if
                                                (clojure.core/symbol?
                                                 elem__55809_nth_1__)
                                                (clojure.core/let
                                                 [X__55813
                                                  (clojure.core/name
                                                   elem__55809_nth_1__)]
                                                 (clojure.core/case
                                                  X__55813
                                                  ("meander.zeta")
                                                  (clojure.core/let
                                                   [?env
                                                    input__55632_nth_2__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__9229__auto__
                                                        (CATA__FN__55682
                                                         ['meander.dev.parse.zeta/parse-sequential
                                                          (clojure.core/into
                                                           []
                                                           (clojure.core/concat
                                                            (clojure.core/vec
                                                             (clojure.core/iterator-seq
                                                              !xs__counter))
                                                            (clojure.core/list
                                                             ((clojure.core/partial
                                                               clojure.core/apply
                                                               clojure.core/symbol)
                                                              ["meander.zeta"
                                                               ?name])
                                                             ?pattern)))
                                                          ?env])]
                                                       (if
                                                        (meander.runtime.zeta/fail?
                                                         CATA_RESULT__9229__auto__)
                                                        (throw
                                                         (meander.runtime.zeta/fail))
                                                        (clojure.core/nth
                                                         CATA_RESULT__9229__auto__
                                                         0))))]
                                                    (catch
                                                     java.lang.Exception
                                                     e__10169__auto__
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       e__10169__auto__)
                                                      (meander.runtime.zeta/fail)
                                                      (throw
                                                       e__10169__auto__)))))
                                                  (meander.runtime.zeta/fail)))
                                                (meander.runtime.zeta/fail))
                                               (meander.runtime.zeta/fail)))
                                             (meander.runtime.zeta/fail)))]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            result__56722)
                                           (recur
                                            (clojure.core/next
                                             search_space__56721))
                                           result__56722))
                                         (state__56718)))
                                       (state__56718)))
                                     (state__56718)))
                                   (state__56718))))
                                (state__56718)))
                              (state__56718)))))
                          (state__56718)))
                        (state__56718)))
                      (state__56718))))
                   (state__56718
                    []
                    (clojure.core/loop
                     [search_space__56724
                      (meander.match.runtime.epsilon/partitions
                       2
                       input__55632_nth_1__)]
                     (if
                      (clojure.core/seq search_space__56724)
                      (clojure.core/let
                       [input__55632_nth_1___parts__
                        (clojure.core/first search_space__56724)
                        result__56725
                        (clojure.core/let
                         [input__55632_nth_1___l__
                          (clojure.core/nth
                           input__55632_nth_1___parts__
                           0)
                          input__55632_nth_1___r__
                          (clojure.core/nth
                           input__55632_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs
                           (clojure.core/vec input__55632_nth_1___l__)]
                          (clojure.core/let
                           [input__55632_nth_1___r___l__
                            (clojure.core/subvec
                             input__55632_nth_1___r__
                             0
                             (clojure.core/min
                              (clojure.core/count
                               input__55632_nth_1___r__)
                              1))]
                           (if
                            (clojure.core/=
                             (clojure.core/count
                              input__55632_nth_1___r___l__)
                             1)
                            (clojure.core/let
                             [input__55632_nth_1___r___r__
                              (clojure.core/subvec
                               input__55632_nth_1___r__
                               1)]
                             (if
                              (clojure.core/=
                               input__55632_nth_1___r___l__
                               ['.])
                              (clojure.core/let
                               [?rest input__55632_nth_1___r___r__]
                               (clojure.core/let
                                [?env input__55632_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [!xs__counter
                                    (meander.runtime.zeta/iterator
                                     !xs)]
                                   (clojure.core/let
                                    [CATA_RESULT__9229__auto__
                                     (CATA__FN__55682
                                      ['meander.dev.parse.zeta/make-join
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__55682
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            (clojure.core/vec
                                             (clojure.core/iterator-seq
                                              !xs__counter)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9229__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9229__auto__
                                          0)))
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__55682
                                          ['meander.dev.parse.zeta/parse-sequential
                                           ?rest
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9229__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9229__auto__
                                          0)))
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__9229__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__9229__auto__
                                      0))))]
                                 (catch
                                  java.lang.Exception
                                  e__10169__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__10169__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__10169__auto__))))))
                              (meander.runtime.zeta/fail)))
                            (meander.runtime.zeta/fail)))))]
                       (if
                        (meander.runtime.zeta/fail? result__56725)
                        (recur (clojure.core/next search_space__56724))
                        result__56725))
                      (state__56719))))
                   (state__56719
                    []
                    (clojure.core/let
                     [input__55632_nth_1___l__
                      (clojure.core/subvec
                       input__55632_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__55632_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__55632_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__55632_nth_1___r__
                        (clojure.core/subvec input__55632_nth_1__ 1)]
                       (if
                        (clojure.core/=
                         input__55632_nth_1___l__
                         ['...])
                        (clojure.core/let
                         [?rest input__55632_nth_1___r__]
                         (clojure.core/let
                          [?env input__55632_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__9229__auto__
                              (CATA__FN__55682
                               ['meander.dev.parse.zeta/parse-sequential
                                ?rest
                                ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__9229__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__9229__auto__
                               0)))]
                           (catch
                            java.lang.Exception
                            e__10169__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__10169__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10169__auto__))))))
                        (state__56720)))
                      (state__56720))))
                   (state__56720
                    []
                    (clojure.core/loop
                     [search_space__56727
                      (meander.match.runtime.epsilon/partitions
                       2
                       input__55632_nth_1__)]
                     (if
                      (clojure.core/seq search_space__56727)
                      (clojure.core/let
                       [input__55632_nth_1___parts__
                        (clojure.core/first search_space__56727)
                        result__56728
                        (clojure.core/let
                         [input__55632_nth_1___l__
                          (clojure.core/nth
                           input__55632_nth_1___parts__
                           0)
                          input__55632_nth_1___r__
                          (clojure.core/nth
                           input__55632_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__8090__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__55632_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__55839]
                              (clojure.core/let
                               [input__55839_nth_0__
                                (clojure.core/nth input__55839 0)]
                               (clojure.core/letfn
                                [(save__55840
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__56731
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__55839_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__55839_nth_0__)
                                 (clojure.core/let
                                  [X__55842
                                   (clojure.core/namespace
                                    input__55839_nth_0__)]
                                  (clojure.core/case
                                   X__55842
                                   (nil)
                                   (clojure.core/let
                                    [X__55844
                                     (clojure.core/name
                                      input__55839_nth_0__)]
                                    (if
                                     (clojure.core/string? X__55844)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__55844)
                                      (save__55840)
                                      (f__56731))
                                     (f__56731)))
                                   (f__56731)))
                                 (f__56731)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__55632_nth_1___r___l__
                                (clojure.core/subvec
                                 input__55632_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__55632_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__55632_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__55632_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__55632_nth_1___r__
                                   1)]
                                 (if
                                  (clojure.core/=
                                   input__55632_nth_1___r___l__
                                   ['...])
                                  (clojure.core/let
                                   [?rest input__55632_nth_1___r___r__]
                                   (clojure.core/let
                                    [?env input__55632_nth_2__]
                                    (try
                                     [(clojure.core/let
                                       [!xs__counter
                                        (meander.runtime.zeta/iterator
                                         !xs)]
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__55682
                                          ['meander.dev.parse.zeta/make-star
                                           (clojure.core/let
                                            [CATA_RESULT__9229__auto__
                                             (CATA__FN__55682
                                              ['meander.dev.parse.zeta/parse-sequential
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !xs__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9229__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9229__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__9229__auto__
                                             (CATA__FN__55682
                                              ['meander.dev.parse.zeta/parse-sequential
                                               ?rest
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9229__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9229__auto__
                                              0)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9229__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9229__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__10169__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10169__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10169__auto__))))))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))))]
                           (if
                            (meander.runtime.zeta/fail?
                             ret__8090__auto__)
                            (meander.runtime.zeta/fail)
                            ret__8090__auto__))))]
                       (if
                        (meander.runtime.zeta/fail? result__56728)
                        (recur (clojure.core/next search_space__56727))
                        result__56728))
                      (state__56714))))]
                  (state__56717))
                 (state__56714))
                (state__56714)))
              (state__56714)))
            (state__56714
             []
             (if
              (clojure.core/= (clojure.core/count input__55632) 4)
              (clojure.core/let
               [input__55632_nth_0__
                (clojure.core/nth input__55632 0)
                input__55632_nth_1__
                (clojure.core/nth input__55632 1)
                input__55632_nth_2__
                (clojure.core/nth input__55632 2)]
               (clojure.core/letfn
                [(state__56732
                  []
                  (clojure.core/let
                   [input__55632_nth_3__
                    (clojure.core/nth input__55632 3)]
                   (clojure.core/case
                    input__55632_nth_0__
                    (meander.dev.parse.zeta/make-star)
                    (clojure.core/letfn
                     [(state__56734
                       []
                       (if
                        (clojure.core/map? input__55632_nth_1__)
                        (clojure.core/let
                         [VAL__55848
                          (.valAt input__55632_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__55848
                          (:cat)
                          (clojure.core/let
                           [VAL__55849
                            (.valAt input__55632_nth_1__ :sequence)]
                           (if
                            (clojure.core/vector? VAL__55849)
                            (if
                             (clojure.core/=
                              (clojure.core/count VAL__55849)
                              1)
                             (clojure.core/let
                              [VAL__55849_nth_0__
                               (clojure.core/nth VAL__55849 0)]
                              (if
                               (clojure.core/map? VAL__55849_nth_0__)
                               (clojure.core/let
                                [VAL__55854
                                 (.valAt VAL__55849_nth_0__ :tag)]
                                (clojure.core/case
                                 VAL__55854
                                 (:memory-variable)
                                 (clojure.core/let
                                  [?memory-variable VAL__55849_nth_0__]
                                  (clojure.core/let
                                   [VAL__55850
                                    (.valAt
                                     input__55632_nth_1__
                                     :next)]
                                   (if
                                    (clojure.core/map? VAL__55850)
                                    (clojure.core/let
                                     [VAL__55851
                                      (.valAt VAL__55850 :tag)]
                                     (clojure.core/case
                                      VAL__55851
                                      (:empty)
                                      (clojure.core/let
                                       [?next input__55632_nth_2__]
                                       (clojure.core/let
                                        [?env input__55632_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__9229__auto__
                                            (CATA__FN__55682
                                             ['meander.dev.parse.zeta/make-join
                                              {:tag :into,
                                               :memory-variable
                                               ?memory-variable}
                                              ?next
                                              ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             CATA_RESULT__9229__auto__)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             CATA_RESULT__9229__auto__
                                             0)))]
                                         (catch
                                          java.lang.Exception
                                          e__10169__auto__
                                          (if
                                           (meander.runtime.zeta/fail?
                                            e__10169__auto__)
                                           (meander.runtime.zeta/fail)
                                           (throw
                                            e__10169__auto__))))))
                                      (state__56735)))
                                    (state__56735))))
                                 (state__56735)))
                               (state__56735)))
                             (state__56735))
                            (state__56735)))
                          (state__56735)))
                        (state__56735)))
                      (state__56735
                       []
                       (clojure.core/let
                        [?pattern input__55632_nth_1__]
                        (clojure.core/let
                         [?next input__55632_nth_2__]
                         (if
                          (clojure.core/map? input__55632_nth_3__)
                          (clojure.core/let
                           [VAL__55857
                            (.valAt input__55632_nth_3__ :context)]
                           (clojure.core/case
                            VAL__55857
                            (:string)
                            (try
                             [{:tag :string-star,
                               :pattern ?pattern,
                               :next ?next}]
                             (catch
                              java.lang.Exception
                              e__10169__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__10169__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10169__auto__))))
                            (state__56733)))
                          (state__56733)))))]
                     (state__56734))
                    (state__56733))))
                 (state__56733
                  []
                  (clojure.core/case
                   input__55632_nth_0__
                   (meander.dev.parse.zeta/make-star)
                   (clojure.core/let
                    [?pattern input__55632_nth_1__]
                    (clojure.core/let
                     [?next input__55632_nth_2__]
                     (try
                      [{:tag :star, :pattern ?pattern, :next ?next}]
                      (catch
                       java.lang.Exception
                       e__10169__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10169__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10169__auto__))))))
                   (state__56715)))]
                (state__56732)))
              (state__56715)))
            (state__56715
             []
             (if
              (clojure.core/= (clojure.core/count input__55632) 3)
              (clojure.core/let
               [input__55632_nth_0__
                (clojure.core/nth input__55632 0)
                input__55632_nth_1__
                (clojure.core/nth input__55632 1)
                input__55632_nth_2__
                (clojure.core/nth input__55632 2)]
               (clojure.core/case
                input__55632_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__55632_nth_1__)
                 (clojure.core/letfn
                  [(state__56736
                    []
                    (clojure.core/let
                     [input__55632_nth_1___l__
                      (clojure.core/subvec
                       input__55632_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__55632_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__55632_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__55632_nth_1___r__
                        (clojure.core/subvec input__55632_nth_1__ 1)]
                       (clojure.core/let
                        [input__55632_nth_1___l___nth_0__
                         (clojure.core/nth input__55632_nth_1___l__ 0)]
                        (if
                         (clojure.core/symbol?
                          input__55632_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__55865
                           (clojure.core/namespace
                            input__55632_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__55865
                           (nil)
                           (clojure.core/let
                            [X__55867
                             (clojure.core/name
                              input__55632_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__55867)
                             (clojure.core/let
                              [ret__55868
                               (clojure.core/re-matches
                                #"\.\.(\d+)"
                                X__55867)]
                              (if
                               (clojure.core/some? ret__55868)
                               (if
                                (clojure.core/vector? ret__55868)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__55868)
                                  2)
                                 (clojure.core/let
                                  [ret__55868_nth_1__
                                   (clojure.core/nth ret__55868 1)]
                                  (clojure.core/let
                                   [?n ret__55868_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__55632_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__55632_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__55632_nth_2__]
                                      (try
                                       [{:tag :syntax-error,
                                         :message
                                         "The n or more operator ..N must be preceeded by at least one pattern"}]
                                       (catch
                                        java.lang.Exception
                                        e__10169__auto__
                                        (if
                                         (meander.runtime.zeta/fail?
                                          e__10169__auto__)
                                         (meander.runtime.zeta/fail)
                                         (throw
                                          e__10169__auto__)))))))))
                                 (state__56737))
                                (state__56737))
                               (state__56737)))
                             (state__56737)))
                           (state__56737)))
                         (state__56737))))
                      (state__56737))))
                   (state__56737
                    []
                    (clojure.core/loop
                     [search_space__56743
                      (meander.match.runtime.epsilon/partitions
                       2
                       input__55632_nth_1__)]
                     (if
                      (clojure.core/seq search_space__56743)
                      (clojure.core/let
                       [input__55632_nth_1___parts__
                        (clojure.core/first search_space__56743)
                        result__56744
                        (clojure.core/let
                         [input__55632_nth_1___l__
                          (clojure.core/nth
                           input__55632_nth_1___parts__
                           0)
                          input__55632_nth_1___r__
                          (clojure.core/nth
                           input__55632_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__8090__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__55632_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__55884]
                              (clojure.core/let
                               [input__55884_nth_0__
                                (clojure.core/nth input__55884 0)]
                               (clojure.core/letfn
                                [(save__55885
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__56747
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__55884_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__55884_nth_0__)
                                 (clojure.core/let
                                  [X__55887
                                   (clojure.core/namespace
                                    input__55884_nth_0__)]
                                  (clojure.core/case
                                   X__55887
                                   (nil)
                                   (clojure.core/let
                                    [X__55889
                                     (clojure.core/name
                                      input__55884_nth_0__)]
                                    (if
                                     (clojure.core/string? X__55889)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__55889)
                                      (save__55885)
                                      (f__56747))
                                     (f__56747)))
                                   (f__56747)))
                                 (f__56747)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__55632_nth_1___r___l__
                                (clojure.core/subvec
                                 input__55632_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__55632_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__55632_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__55632_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__55632_nth_1___r__
                                   1)]
                                 (clojure.core/let
                                  [input__55632_nth_1___r___l___nth_0__
                                   (clojure.core/nth
                                    input__55632_nth_1___r___l__
                                    0)]
                                  (if
                                   (clojure.core/symbol?
                                    input__55632_nth_1___r___l___nth_0__)
                                   (clojure.core/let
                                    [X__55878
                                     (clojure.core/namespace
                                      input__55632_nth_1___r___l___nth_0__)]
                                    (clojure.core/case
                                     X__55878
                                     (nil)
                                     (clojure.core/let
                                      [X__55880
                                       (clojure.core/name
                                        input__55632_nth_1___r___l___nth_0__)]
                                      (if
                                       (clojure.core/string? X__55880)
                                       (clojure.core/let
                                        [ret__55881
                                         (clojure.core/re-matches
                                          #"\.\.(\d+)"
                                          X__55880)]
                                        (if
                                         (clojure.core/some?
                                          ret__55881)
                                         (if
                                          (clojure.core/vector?
                                           ret__55881)
                                          (if
                                           (clojure.core/=
                                            (clojure.core/count
                                             ret__55881)
                                            2)
                                           (clojure.core/let
                                            [ret__55881_nth_1__
                                             (clojure.core/nth
                                              ret__55881
                                              1)]
                                            (clojure.core/let
                                             [?n ret__55881_nth_1__]
                                             (clojure.core/let
                                              [?rest
                                               input__55632_nth_1___r___r__]
                                              (clojure.core/let
                                               [?env
                                                input__55632_nth_2__]
                                               (try
                                                [(clojure.core/let
                                                  [!xs__counter
                                                   (meander.runtime.zeta/iterator
                                                    !xs)]
                                                  {:tag :plus,
                                                   :n (Integer. ?n),
                                                   :pattern
                                                   (clojure.core/let
                                                    [CATA_RESULT__9229__auto__
                                                     (CATA__FN__55682
                                                      ['meander.dev.parse.zeta/parse-sequential
                                                       (clojure.core/into
                                                        []
                                                        (clojure.core/vec
                                                         (clojure.core/iterator-seq
                                                          !xs__counter)))
                                                       ?env])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__9229__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__9229__auto__
                                                      0))),
                                                   :next
                                                   (clojure.core/let
                                                    [CATA_RESULT__9229__auto__
                                                     (CATA__FN__55682
                                                      ['meander.dev.parse.zeta/parse-sequential
                                                       ?rest
                                                       ?env])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__9229__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__9229__auto__
                                                      0)))})]
                                                (catch
                                                 java.lang.Exception
                                                 e__10169__auto__
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   e__10169__auto__)
                                                  (meander.runtime.zeta/fail)
                                                  (throw
                                                   e__10169__auto__))))))))
                                           (meander.runtime.zeta/fail))
                                          (meander.runtime.zeta/fail))
                                         (meander.runtime.zeta/fail)))
                                       (meander.runtime.zeta/fail)))
                                     (meander.runtime.zeta/fail)))
                                   (meander.runtime.zeta/fail))))
                                (meander.runtime.zeta/fail)))))]
                           (if
                            (meander.runtime.zeta/fail?
                             ret__8090__auto__)
                            (meander.runtime.zeta/fail)
                            ret__8090__auto__))))]
                       (if
                        (meander.runtime.zeta/fail? result__56744)
                        (recur (clojure.core/next search_space__56743))
                        result__56744))
                      (state__56738))))
                   (state__56738
                    []
                    (clojure.core/let
                     [input__55632_nth_1___l__
                      (clojure.core/subvec
                       input__55632_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__55632_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__55632_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__55632_nth_1___r__
                        (clojure.core/subvec input__55632_nth_1__ 1)]
                       (clojure.core/let
                        [input__55632_nth_1___l___nth_0__
                         (clojure.core/nth input__55632_nth_1___l__ 0)]
                        (if
                         (clojure.core/symbol?
                          input__55632_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__55896
                           (clojure.core/namespace
                            input__55632_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__55896
                           (nil)
                           (clojure.core/let
                            [X__55898
                             (clojure.core/name
                              input__55632_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__55898)
                             (clojure.core/let
                              [ret__55899
                               (clojure.core/re-matches
                                #"\.\.(\?.+)"
                                X__55898)]
                              (if
                               (clojure.core/some? ret__55899)
                               (if
                                (clojure.core/vector? ret__55899)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__55899)
                                  2)
                                 (clojure.core/let
                                  [ret__55899_nth_1__
                                   (clojure.core/nth ret__55899 1)]
                                  (clojure.core/let
                                   [?n ret__55899_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__55632_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__55632_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__55632_nth_2__]
                                      (try
                                       [{:tag :syntax-error,
                                         :message
                                         "The ?n or more operator ..?n must be preceeded by at least one pattern"}]
                                       (catch
                                        java.lang.Exception
                                        e__10169__auto__
                                        (if
                                         (meander.runtime.zeta/fail?
                                          e__10169__auto__)
                                         (meander.runtime.zeta/fail)
                                         (throw
                                          e__10169__auto__)))))))))
                                 (state__56739))
                                (state__56739))
                               (state__56739)))
                             (state__56739)))
                           (state__56739)))
                         (state__56739))))
                      (state__56739))))
                   (state__56739
                    []
                    (clojure.core/loop
                     [search_space__56748
                      (meander.match.runtime.epsilon/partitions
                       2
                       input__55632_nth_1__)]
                     (if
                      (clojure.core/seq search_space__56748)
                      (clojure.core/let
                       [input__55632_nth_1___parts__
                        (clojure.core/first search_space__56748)
                        result__56749
                        (clojure.core/let
                         [input__55632_nth_1___l__
                          (clojure.core/nth
                           input__55632_nth_1___parts__
                           0)
                          input__55632_nth_1___r__
                          (clojure.core/nth
                           input__55632_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__8090__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__55632_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__55915]
                              (clojure.core/let
                               [input__55915_nth_0__
                                (clojure.core/nth input__55915 0)]
                               (clojure.core/letfn
                                [(save__55916
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__56752
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__55915_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__55915_nth_0__)
                                 (clojure.core/let
                                  [X__55918
                                   (clojure.core/namespace
                                    input__55915_nth_0__)]
                                  (clojure.core/case
                                   X__55918
                                   (nil)
                                   (clojure.core/let
                                    [X__55920
                                     (clojure.core/name
                                      input__55915_nth_0__)]
                                    (if
                                     (clojure.core/string? X__55920)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__55920)
                                      (save__55916)
                                      (f__56752))
                                     (f__56752)))
                                   (f__56752)))
                                 (f__56752)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__55632_nth_1___r___l__
                                (clojure.core/subvec
                                 input__55632_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__55632_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__55632_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__55632_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__55632_nth_1___r__
                                   1)]
                                 (clojure.core/let
                                  [input__55632_nth_1___r___l___nth_0__
                                   (clojure.core/nth
                                    input__55632_nth_1___r___l__
                                    0)]
                                  (if
                                   (clojure.core/symbol?
                                    input__55632_nth_1___r___l___nth_0__)
                                   (clojure.core/let
                                    [X__55909
                                     (clojure.core/namespace
                                      input__55632_nth_1___r___l___nth_0__)]
                                    (clojure.core/case
                                     X__55909
                                     (nil)
                                     (clojure.core/let
                                      [X__55911
                                       (clojure.core/name
                                        input__55632_nth_1___r___l___nth_0__)]
                                      (if
                                       (clojure.core/string? X__55911)
                                       (clojure.core/let
                                        [ret__55912
                                         (clojure.core/re-matches
                                          #"\.\.(\?.+)"
                                          X__55911)]
                                        (if
                                         (clojure.core/some?
                                          ret__55912)
                                         (if
                                          (clojure.core/vector?
                                           ret__55912)
                                          (if
                                           (clojure.core/=
                                            (clojure.core/count
                                             ret__55912)
                                            2)
                                           (clojure.core/let
                                            [ret__55912_nth_1__
                                             (clojure.core/nth
                                              ret__55912
                                              1)]
                                            (clojure.core/let
                                             [?n ret__55912_nth_1__]
                                             (clojure.core/let
                                              [?rest
                                               input__55632_nth_1___r___r__]
                                              (clojure.core/let
                                               [?env
                                                input__55632_nth_2__]
                                               (try
                                                [(clojure.core/let
                                                  [!xs__counter
                                                   (meander.runtime.zeta/iterator
                                                    !xs)]
                                                  {:tag :logical-plus,
                                                   :n
                                                   {:tag
                                                    :logic-variable,
                                                    :name ?n,
                                                    :symbol
                                                    (clojure.core/symbol
                                                     ?n)},
                                                   :pattern
                                                   (clojure.core/let
                                                    [CATA_RESULT__9229__auto__
                                                     (CATA__FN__55682
                                                      ['meander.dev.parse.zeta/parse-sequential
                                                       (clojure.core/into
                                                        []
                                                        (clojure.core/vec
                                                         (clojure.core/iterator-seq
                                                          !xs__counter)))
                                                       ?env])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__9229__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__9229__auto__
                                                      0))),
                                                   :next
                                                   (clojure.core/let
                                                    [CATA_RESULT__9229__auto__
                                                     (CATA__FN__55682
                                                      ['meander.dev.parse.zeta/parse-sequential
                                                       ?rest
                                                       ?env])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__9229__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__9229__auto__
                                                      0)))})]
                                                (catch
                                                 java.lang.Exception
                                                 e__10169__auto__
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   e__10169__auto__)
                                                  (meander.runtime.zeta/fail)
                                                  (throw
                                                   e__10169__auto__))))))))
                                           (meander.runtime.zeta/fail))
                                          (meander.runtime.zeta/fail))
                                         (meander.runtime.zeta/fail)))
                                       (meander.runtime.zeta/fail)))
                                     (meander.runtime.zeta/fail)))
                                   (meander.runtime.zeta/fail))))
                                (meander.runtime.zeta/fail)))))]
                           (if
                            (meander.runtime.zeta/fail?
                             ret__8090__auto__)
                            (meander.runtime.zeta/fail)
                            ret__8090__auto__))))]
                       (if
                        (meander.runtime.zeta/fail? result__56749)
                        (recur (clojure.core/next search_space__56748))
                        result__56749))
                      (state__56740))))
                   (state__56740
                    []
                    (clojure.core/let
                     [input__55632_nth_1___l__
                      (clojure.core/subvec
                       input__55632_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__55632_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__55632_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__55632_nth_1___r__
                        (clojure.core/subvec input__55632_nth_1__ 1)]
                       (clojure.core/let
                        [input__55632_nth_1___l___nth_0__
                         (clojure.core/nth input__55632_nth_1___l__ 0)]
                        (if
                         (clojure.core/symbol?
                          input__55632_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__55927
                           (clojure.core/namespace
                            input__55632_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__55927
                           (nil)
                           (clojure.core/let
                            [X__55929
                             (clojure.core/name
                              input__55632_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__55929)
                             (clojure.core/let
                              [ret__55930
                               (clojure.core/re-matches
                                #"\.\.(!.+)"
                                X__55929)]
                              (if
                               (clojure.core/some? ret__55930)
                               (if
                                (clojure.core/vector? ret__55930)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__55930)
                                  2)
                                 (clojure.core/let
                                  [ret__55930_nth_1__
                                   (clojure.core/nth ret__55930 1)]
                                  (clojure.core/let
                                   [?n ret__55930_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__55632_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__55632_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__55632_nth_2__]
                                      (try
                                       [{:tag :syntax-error,
                                         :message
                                         "The operator ..!n must be preceeded by at least one pattern"}]
                                       (catch
                                        java.lang.Exception
                                        e__10169__auto__
                                        (if
                                         (meander.runtime.zeta/fail?
                                          e__10169__auto__)
                                         (meander.runtime.zeta/fail)
                                         (throw
                                          e__10169__auto__)))))))))
                                 (state__56741))
                                (state__56741))
                               (state__56741)))
                             (state__56741)))
                           (state__56741)))
                         (state__56741))))
                      (state__56741))))
                   (state__56741
                    []
                    (clojure.core/loop
                     [search_space__56753
                      (meander.match.runtime.epsilon/partitions
                       2
                       input__55632_nth_1__)]
                     (if
                      (clojure.core/seq search_space__56753)
                      (clojure.core/let
                       [input__55632_nth_1___parts__
                        (clojure.core/first search_space__56753)
                        result__56754
                        (clojure.core/let
                         [input__55632_nth_1___l__
                          (clojure.core/nth
                           input__55632_nth_1___parts__
                           0)
                          input__55632_nth_1___r__
                          (clojure.core/nth
                           input__55632_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__8090__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__55632_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__55946]
                              (clojure.core/let
                               [input__55946_nth_0__
                                (clojure.core/nth input__55946 0)]
                               (clojure.core/letfn
                                [(save__55947
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__56757
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__55946_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__55946_nth_0__)
                                 (clojure.core/let
                                  [X__55949
                                   (clojure.core/namespace
                                    input__55946_nth_0__)]
                                  (clojure.core/case
                                   X__55949
                                   (nil)
                                   (clojure.core/let
                                    [X__55951
                                     (clojure.core/name
                                      input__55946_nth_0__)]
                                    (if
                                     (clojure.core/string? X__55951)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__55951)
                                      (save__55947)
                                      (f__56757))
                                     (f__56757)))
                                   (f__56757)))
                                 (f__56757)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__55632_nth_1___r___l__
                                (clojure.core/subvec
                                 input__55632_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__55632_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__55632_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__55632_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__55632_nth_1___r__
                                   1)]
                                 (clojure.core/let
                                  [input__55632_nth_1___r___l___nth_0__
                                   (clojure.core/nth
                                    input__55632_nth_1___r___l__
                                    0)]
                                  (if
                                   (clojure.core/symbol?
                                    input__55632_nth_1___r___l___nth_0__)
                                   (clojure.core/let
                                    [X__55940
                                     (clojure.core/namespace
                                      input__55632_nth_1___r___l___nth_0__)]
                                    (clojure.core/case
                                     X__55940
                                     (nil)
                                     (clojure.core/let
                                      [X__55942
                                       (clojure.core/name
                                        input__55632_nth_1___r___l___nth_0__)]
                                      (if
                                       (clojure.core/string? X__55942)
                                       (clojure.core/let
                                        [ret__55943
                                         (clojure.core/re-matches
                                          #"\.\.(\!.+)"
                                          X__55942)]
                                        (if
                                         (clojure.core/some?
                                          ret__55943)
                                         (if
                                          (clojure.core/vector?
                                           ret__55943)
                                          (if
                                           (clojure.core/=
                                            (clojure.core/count
                                             ret__55943)
                                            2)
                                           (clojure.core/let
                                            [ret__55943_nth_1__
                                             (clojure.core/nth
                                              ret__55943
                                              1)]
                                            (clojure.core/let
                                             [?n ret__55943_nth_1__]
                                             (clojure.core/let
                                              [?rest
                                               input__55632_nth_1___r___r__]
                                              (clojure.core/let
                                               [?env
                                                input__55632_nth_2__]
                                               (try
                                                [(clojure.core/let
                                                  [!xs__counter
                                                   (meander.runtime.zeta/iterator
                                                    !xs)]
                                                  {:tag :memory-plus,
                                                   :n
                                                   {:tag
                                                    :memory-variable,
                                                    :name ?n,
                                                    :symbol
                                                    (clojure.core/symbol
                                                     ?n)},
                                                   :pattern
                                                   (clojure.core/let
                                                    [CATA_RESULT__9229__auto__
                                                     (CATA__FN__55682
                                                      ['meander.dev.parse.zeta/parse-sequential
                                                       (clojure.core/into
                                                        []
                                                        (clojure.core/vec
                                                         (clojure.core/iterator-seq
                                                          !xs__counter)))
                                                       ?env])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__9229__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__9229__auto__
                                                      0))),
                                                   :next
                                                   (clojure.core/let
                                                    [CATA_RESULT__9229__auto__
                                                     (CATA__FN__55682
                                                      ['meander.dev.parse.zeta/parse-sequential
                                                       ?rest
                                                       ?env])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__9229__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__9229__auto__
                                                      0)))})]
                                                (catch
                                                 java.lang.Exception
                                                 e__10169__auto__
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   e__10169__auto__)
                                                  (meander.runtime.zeta/fail)
                                                  (throw
                                                   e__10169__auto__))))))))
                                           (meander.runtime.zeta/fail))
                                          (meander.runtime.zeta/fail))
                                         (meander.runtime.zeta/fail)))
                                       (meander.runtime.zeta/fail)))
                                     (meander.runtime.zeta/fail)))
                                   (meander.runtime.zeta/fail))))
                                (meander.runtime.zeta/fail)))))]
                           (if
                            (meander.runtime.zeta/fail?
                             ret__8090__auto__)
                            (meander.runtime.zeta/fail)
                            ret__8090__auto__))))]
                       (if
                        (meander.runtime.zeta/fail? result__56754)
                        (recur (clojure.core/next search_space__56753))
                        result__56754))
                      (state__56742))))
                   (state__56742
                    []
                    (clojure.core/let
                     [!xs (clojure.core/vec input__55632_nth_1__)]
                     (clojure.core/let
                      [?env input__55632_nth_2__]
                      (try
                       [(clojure.core/let
                         [!xs__counter
                          (meander.runtime.zeta/iterator !xs)]
                         (clojure.core/let
                          [CATA_RESULT__9229__auto__
                           (CATA__FN__55682
                            ['meander.dev.parse.zeta/make-cat
                             (clojure.core/into
                              []
                              (clojure.core/loop
                               [return__55683
                                (clojure.core/transient [])]
                               (if
                                (clojure.core/and
                                 (.hasNext !xs__counter))
                                (recur
                                 (clojure.core/conj!
                                  return__55683
                                  (clojure.core/let
                                   [CATA_RESULT__9229__auto__
                                    (CATA__FN__55682
                                     [(if
                                       (.hasNext !xs__counter)
                                       (.next !xs__counter))
                                      ?env])]
                                   (if
                                    (meander.runtime.zeta/fail?
                                     CATA_RESULT__9229__auto__)
                                    (throw (meander.runtime.zeta/fail))
                                    (clojure.core/nth
                                     CATA_RESULT__9229__auto__
                                     0)))))
                                (clojure.core/persistent!
                                 return__55683))))
                             {:tag :empty}
                             ?env])]
                          (if
                           (meander.runtime.zeta/fail?
                            CATA_RESULT__9229__auto__)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth
                            CATA_RESULT__9229__auto__
                            0))))]
                       (catch
                        java.lang.Exception
                        e__10169__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10169__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10169__auto__)))))))]
                  (state__56736))
                 (state__56716))
                (state__56716)))
              (state__56716)))
            (state__56716
             []
             (if
              (clojure.core/= (clojure.core/count input__55632) 4)
              (clojure.core/let
               [input__55632_nth_0__
                (clojure.core/nth input__55632 0)
                input__55632_nth_1__
                (clojure.core/nth input__55632 1)
                input__55632_nth_2__
                (clojure.core/nth input__55632 2)]
               (clojure.core/letfn
                [(state__56758
                  []
                  (clojure.core/case
                   input__55632_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (if
                    (clojure.core/vector? input__55632_nth_1__)
                    (clojure.core/let
                     [!forms []]
                     (clojure.core/let
                      [ret__8090__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__55632_nth_1__
                        [!forms]
                        (clojure.core/fn
                         [[!forms] input__55962]
                         (clojure.core/let
                          [input__55962_nth_0__
                           (clojure.core/nth input__55962 0)]
                          (if
                           (clojure.core/map? input__55962_nth_0__)
                           (clojure.core/let
                            [VAL__55963
                             (.valAt input__55962_nth_0__ :tag)]
                            (clojure.core/case
                             VAL__55963
                             (:literal)
                             (clojure.core/let
                              [VAL__55964
                               (.valAt input__55962_nth_0__ :type)]
                              (if
                               (clojure.core/let
                                [x__6986__auto__ VAL__55964]
                                (clojure.core/case
                                 x__6986__auto__
                                 (:string :char)
                                 true
                                 false))
                               (clojure.core/let
                                [VAL__55965
                                 (.valAt input__55962_nth_0__ :form)]
                                (clojure.core/let
                                 [!forms
                                  (clojure.core/conj
                                   !forms
                                   VAL__55965)]
                                 [!forms]))
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))))
                        (clojure.core/fn
                         [[!forms]]
                         (if
                          (clojure.core/map? input__55632_nth_2__)
                          (clojure.core/let
                           [VAL__55959
                            (.valAt input__55632_nth_2__ :tag)]
                           (clojure.core/case
                            VAL__55959
                            (:empty)
                            (try
                             [{:tag :literal,
                               :type :string,
                               :form
                               (clojure.string/join
                                (clojure.core/into [] !forms))}]
                             (catch
                              java.lang.Exception
                              e__10169__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__10169__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10169__auto__))))
                            (state__56759)))
                          (state__56759))))]
                      (if
                       (meander.runtime.zeta/fail? ret__8090__auto__)
                       (state__56759)
                       ret__8090__auto__)))
                    (state__56759))
                   (state__56759)))
                 (state__56759
                  []
                  (clojure.core/let
                   [input__55632_nth_3__
                    (clojure.core/nth input__55632 3)]
                   (clojure.core/case
                    input__55632_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (if
                     (clojure.core/vector? input__55632_nth_1__)
                     (clojure.core/letfn
                      [(state__56763
                        []
                        (clojure.core/let
                         [input__55632_nth_1___l__
                          (clojure.core/subvec
                           input__55632_nth_1__
                           0
                           (clojure.core/min
                            (clojure.core/count input__55632_nth_1__)
                            1))]
                         (if
                          (clojure.core/=
                           (clojure.core/count
                            input__55632_nth_1___l__)
                           1)
                          (clojure.core/let
                           [input__55632_nth_1___r__
                            (clojure.core/subvec
                             input__55632_nth_1__
                             1)]
                           (clojure.core/let
                            [input__55632_nth_1___l___nth_0__
                             (clojure.core/nth
                              input__55632_nth_1___l__
                              0)]
                            (if
                             (clojure.core/map?
                              input__55632_nth_1___l___nth_0__)
                             (clojure.core/let
                              [VAL__55971
                               (.valAt
                                input__55632_nth_1___l___nth_0__
                                :tag)]
                              (clojure.core/case
                               VAL__55971
                               (:literal)
                               (clojure.core/let
                                [VAL__55972
                                 (.valAt
                                  input__55632_nth_1___l___nth_0__
                                  :type)]
                                (clojure.core/case
                                 VAL__55972
                                 (:string)
                                 (clojure.core/let
                                  [?ast
                                   input__55632_nth_1___l___nth_0__]
                                  (clojure.core/let
                                   [?rest input__55632_nth_1___r__]
                                   (clojure.core/let
                                    [?next input__55632_nth_2__]
                                    (clojure.core/let
                                     [?env input__55632_nth_3__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__55682
                                          ['meander.dev.parse.zeta/make-join
                                           ?ast
                                           (clojure.core/let
                                            [CATA_RESULT__9229__auto__
                                             (CATA__FN__55682
                                              ['meander.dev.parse.zeta/make-cat
                                               ?rest
                                               ?next
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9229__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9229__auto__
                                              0)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9229__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9229__auto__
                                          0)))]
                                      (catch
                                       java.lang.Exception
                                       e__10169__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__10169__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw e__10169__auto__))))))))
                                 (state__56764)))
                               (state__56764)))
                             (state__56764))))
                          (state__56764))))
                       (state__56764
                        []
                        (clojure.core/let
                         [!forms []]
                         (clojure.core/let
                          [ret__8090__auto__
                           (meander.runtime.zeta/epsilon-run-star-1
                            input__55632_nth_1__
                            [!forms]
                            (clojure.core/fn
                             [[!forms] input__55978]
                             (clojure.core/let
                              [input__55978_nth_0__
                               (clojure.core/nth input__55978 0)]
                              (if
                               (clojure.core/map? input__55978_nth_0__)
                               (clojure.core/let
                                [VAL__55979
                                 (.valAt input__55978_nth_0__ :tag)]
                                (clojure.core/case
                                 VAL__55979
                                 (:literal)
                                 (clojure.core/let
                                  [VAL__55980
                                   (.valAt input__55978_nth_0__ :form)]
                                  (clojure.core/let
                                   [!forms
                                    (clojure.core/conj
                                     !forms
                                     VAL__55980)]
                                   [!forms]))
                                 (meander.runtime.zeta/fail)))
                               (meander.runtime.zeta/fail))))
                            (clojure.core/fn
                             [[!forms]]
                             (if
                              (clojure.core/map? input__55632_nth_2__)
                              (clojure.core/let
                               [VAL__55975
                                (.valAt input__55632_nth_2__ :tag)]
                               (clojure.core/case
                                VAL__55975
                                (:empty)
                                (clojure.core/let
                                 [?env input__55632_nth_3__]
                                 (try
                                  [{:tag :literal,
                                    :form
                                    (clojure.core/into [] !forms)}]
                                  (catch
                                   java.lang.Exception
                                   e__10169__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__10169__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__10169__auto__)))))
                                (state__56760)))
                              (state__56760))))]
                          (if
                           (meander.runtime.zeta/fail?
                            ret__8090__auto__)
                           (state__56760)
                           ret__8090__auto__))))]
                      (state__56763))
                     (state__56760))
                    (state__56760))))
                 (state__56760
                  []
                  (clojure.core/case
                   input__55632_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (clojure.core/let
                    [?sequence input__55632_nth_1__]
                    (clojure.core/let
                     [?next input__55632_nth_2__]
                     (try
                      [{:tag :cat, :sequence ?sequence, :next ?next}]
                      (catch
                       java.lang.Exception
                       e__10169__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10169__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10169__auto__))))))
                   (state__56761)))
                 (state__56761
                  []
                  (clojure.core/let
                   [input__55632_nth_3__
                    (clojure.core/nth input__55632 3)]
                   (clojure.core/case
                    input__55632_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (clojure.core/letfn
                     [(state__56766
                       []
                       (if
                        (clojure.core/map? input__55632_nth_1__)
                        (clojure.core/let
                         [VAL__55986
                          (.valAt input__55632_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__55986
                          (:cat)
                          (clojure.core/let
                           [VAL__55987
                            (.valAt input__55632_nth_1__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__55987]
                            (clojure.core/let
                             [VAL__55988
                              (.valAt input__55632_nth_1__ :next)]
                             (if
                              (clojure.core/map? VAL__55988)
                              (clojure.core/let
                               [VAL__55989 (.valAt VAL__55988 :tag)]
                               (clojure.core/case
                                VAL__55989
                                (:empty)
                                (clojure.core/let
                                 [?right input__55632_nth_2__]
                                 (clojure.core/let
                                  [?env input__55632_nth_3__]
                                  (try
                                   [(clojure.core/let
                                     [CATA_RESULT__9229__auto__
                                      (CATA__FN__55682
                                       ['meander.dev.parse.zeta/make-cat
                                        ?sequence
                                        ?right
                                        ?env])]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       CATA_RESULT__9229__auto__)
                                      (throw
                                       (meander.runtime.zeta/fail))
                                      (clojure.core/nth
                                       CATA_RESULT__9229__auto__
                                       0)))]
                                   (catch
                                    java.lang.Exception
                                    e__10169__auto__
                                    (if
                                     (meander.runtime.zeta/fail?
                                      e__10169__auto__)
                                     (meander.runtime.zeta/fail)
                                     (throw e__10169__auto__))))))
                                (state__56767)))
                              (state__56767)))))
                          (state__56767)))
                        (state__56767)))
                      (state__56767
                       []
                       (clojure.core/let
                        [?left input__55632_nth_1__]
                        (if
                         (clojure.core/map? input__55632_nth_2__)
                         (clojure.core/let
                          [VAL__55992
                           (.valAt input__55632_nth_2__ :tag)]
                          (clojure.core/case
                           VAL__55992
                           (:empty)
                           (clojure.core/let
                            [?env input__55632_nth_3__]
                            (try
                             [?left]
                             (catch
                              java.lang.Exception
                              e__10169__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__10169__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10169__auto__)))))
                           (state__56768)))
                         (state__56768))))
                      (state__56768
                       []
                       (if
                        (clojure.core/map? input__55632_nth_1__)
                        (clojure.core/let
                         [VAL__55995
                          (.valAt input__55632_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__55995
                          (:empty)
                          (clojure.core/let
                           [?right input__55632_nth_2__]
                           (clojure.core/let
                            [?env input__55632_nth_3__]
                            (try
                             [?right]
                             (catch
                              java.lang.Exception
                              e__10169__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__10169__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10169__auto__))))))
                          (state__56769)))
                        (state__56769)))
                      (state__56769
                       []
                       (clojure.core/let
                        [?left input__55632_nth_1__]
                        (clojure.core/let
                         [?right input__55632_nth_2__]
                         (clojure.core/letfn
                          [(state__56771
                            []
                            (if
                             (clojure.core/map? input__55632_nth_3__)
                             (clojure.core/let
                              [VAL__55998
                               (.valAt input__55632_nth_3__ :context)]
                              (clojure.core/case
                               VAL__55998
                               (:string)
                               (try
                                [{:tag :string-join,
                                  :left ?left,
                                  :right ?right}]
                                (catch
                                 java.lang.Exception
                                 e__10169__auto__
                                 (if
                                  (meander.runtime.zeta/fail?
                                   e__10169__auto__)
                                  (meander.runtime.zeta/fail)
                                  (throw e__10169__auto__))))
                               (state__56772)))
                             (state__56772)))
                           (state__56772
                            []
                            (clojure.core/let
                             [?env input__55632_nth_3__]
                             (try
                              [{:tag :join,
                                :left ?left,
                                :right ?right}]
                              (catch
                               java.lang.Exception
                               e__10169__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__10169__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__10169__auto__))))))]
                          (state__56771)))))
                      (state__56770
                       []
                       (if
                        (clojure.core/map? input__55632_nth_1__)
                        (clojure.core/let
                         [VAL__56669
                          (.valAt input__55632_nth_1__ :type)
                          VAL__56668
                          (.valAt input__55632_nth_1__ :tag)]
                         (clojure.core/letfn
                          [(state__56773
                            []
                            (clojure.core/case
                             VAL__56668
                             (:literal)
                             (clojure.core/case
                              VAL__56669
                              (:string)
                              (clojure.core/let
                               [VAL__56005
                                (.valAt input__55632_nth_1__ :form)]
                               (clojure.core/let
                                [?form-1 VAL__56005]
                                (if
                                 (clojure.core/map?
                                  input__55632_nth_2__)
                                 (clojure.core/let
                                  [VAL__56006
                                   (.valAt input__55632_nth_2__ :tag)]
                                  (clojure.core/case
                                   VAL__56006
                                   (:string-join)
                                   (clojure.core/let
                                    [VAL__56007
                                     (.valAt
                                      input__55632_nth_2__
                                      :left)]
                                    (if
                                     (clojure.core/map? VAL__56007)
                                     (clojure.core/let
                                      [VAL__56008
                                       (.valAt VAL__56007 :tag)]
                                      (clojure.core/case
                                       VAL__56008
                                       (:literal)
                                       (clojure.core/let
                                        [VAL__56009
                                         (.valAt VAL__56007 :type)]
                                        (clojure.core/case
                                         VAL__56009
                                         (:string)
                                         (clojure.core/let
                                          [VAL__56010
                                           (.valAt VAL__56007 :form)]
                                          (clojure.core/let
                                           [?form-2 VAL__56010]
                                           (clojure.core/let
                                            [VAL__56011
                                             (.valAt
                                              input__55632_nth_2__
                                              :right)]
                                            (clojure.core/let
                                             [?right VAL__56011]
                                             (if
                                              (clojure.core/map?
                                               input__55632_nth_3__)
                                              (clojure.core/let
                                               [VAL__56012
                                                (.valAt
                                                 input__55632_nth_3__
                                                 :context)]
                                               (clojure.core/case
                                                VAL__56012
                                                (:string)
                                                (clojure.core/let
                                                 [?env
                                                  input__55632_nth_3__]
                                                 (try
                                                  [(clojure.core/let
                                                    [CATA_RESULT__9229__auto__
                                                     (CATA__FN__55682
                                                      ['meander.dev.parse.zeta/make-join
                                                       {:tag :literal,
                                                        :type :string,
                                                        :form
                                                        ((clojure.core/partial
                                                          clojure.core/apply
                                                          str)
                                                         [?form-1
                                                          ?form-2])}
                                                       ?right
                                                       ?env])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__9229__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__9229__auto__
                                                      0)))]
                                                  (catch
                                                   java.lang.Exception
                                                   e__10169__auto__
                                                   (if
                                                    (meander.runtime.zeta/fail?
                                                     e__10169__auto__)
                                                    (meander.runtime.zeta/fail)
                                                    (throw
                                                     e__10169__auto__)))))
                                                (state__56774)))
                                              (state__56774))))))
                                         (state__56774)))
                                       (state__56774)))
                                     (state__56774)))
                                   (state__56774)))
                                 (state__56774))))
                              (state__56774))
                             (state__56774)))
                           (state__56774
                            []
                            (clojure.core/let
                             [VAL__56667
                              (.valAt input__55632_nth_1__ :next)]
                             (clojure.core/case
                              VAL__56668
                              (:literal)
                              (clojure.core/case
                               VAL__56669
                               (:string)
                               (clojure.core/let
                                [?ast input__55632_nth_1__]
                                (if
                                 (clojure.core/map?
                                  input__55632_nth_2__)
                                 (clojure.core/let
                                  [VAL__56017
                                   (.valAt input__55632_nth_2__ :tag)]
                                  (clojure.core/case
                                   VAL__56017
                                   (:string-cat)
                                   (clojure.core/let
                                    [VAL__56018
                                     (.valAt
                                      input__55632_nth_2__
                                      :sequence)]
                                    (clojure.core/let
                                     [?sequence VAL__56018]
                                     (clojure.core/let
                                      [VAL__56019
                                       (.valAt
                                        input__55632_nth_2__
                                        :next)]
                                      (clojure.core/let
                                       [?next VAL__56019]
                                       (if
                                        (clojure.core/map?
                                         input__55632_nth_3__)
                                        (clojure.core/let
                                         [VAL__56020
                                          (.valAt
                                           input__55632_nth_3__
                                           :context)]
                                         (clojure.core/case
                                          VAL__56020
                                          (:string)
                                          (try
                                           [{:tag :string-cat,
                                             :sequence
                                             (clojure.core/into
                                              []
                                              (clojure.core/concat
                                               (clojure.core/list ?ast)
                                               ?sequence)),
                                             :next ?next}]
                                           (catch
                                            java.lang.Exception
                                            e__10169__auto__
                                            (if
                                             (meander.runtime.zeta/fail?
                                              e__10169__auto__)
                                             (meander.runtime.zeta/fail)
                                             (throw
                                              e__10169__auto__))))
                                          (state__56674)))
                                        (state__56674))))))
                                   (state__56674)))
                                 (state__56674)))
                               (state__56674))
                              (:string-cat)
                              (clojure.core/let
                               [VAL__56024
                                (.valAt
                                 input__55632_nth_1__
                                 :sequence)]
                               (clojure.core/let
                                [?sequence VAL__56024]
                                (if
                                 (clojure.core/map? VAL__56667)
                                 (clojure.core/let
                                  [VAL__56026 (.valAt VAL__56667 :tag)]
                                  (clojure.core/case
                                   VAL__56026
                                   (:empty)
                                   (clojure.core/let
                                    [?right input__55632_nth_2__]
                                    (clojure.core/let
                                     [?env input__55632_nth_3__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__55682
                                          ['meander.dev.parse.zeta/make-join
                                           ?sequence
                                           ?right
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9229__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9229__auto__
                                          0)))]
                                      (catch
                                       java.lang.Exception
                                       e__10169__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__10169__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw e__10169__auto__))))))
                                   (state__56674)))
                                 (state__56674))))
                              (:string-star)
                              (clojure.core/let
                               [VAL__56030
                                (.valAt input__55632_nth_1__ :pattern)]
                               (clojure.core/let
                                [?pattern VAL__56030]
                                (if
                                 (clojure.core/map? VAL__56667)
                                 (clojure.core/let
                                  [VAL__56032 (.valAt VAL__56667 :tag)]
                                  (clojure.core/case
                                   VAL__56032
                                   (:empty)
                                   (clojure.core/let
                                    [?right input__55632_nth_2__]
                                    (if
                                     (clojure.core/map?
                                      input__55632_nth_3__)
                                     (clojure.core/let
                                      [VAL__56033
                                       (.valAt
                                        input__55632_nth_3__
                                        :context)]
                                      (clojure.core/case
                                       VAL__56033
                                       (:string)
                                       (try
                                        [{:tag :string-star,
                                          :pattern ?pattern,
                                          :next ?right}]
                                        (catch
                                         java.lang.Exception
                                         e__10169__auto__
                                         (if
                                          (meander.runtime.zeta/fail?
                                           e__10169__auto__)
                                          (meander.runtime.zeta/fail)
                                          (throw e__10169__auto__))))
                                       (state__56674)))
                                     (state__56674)))
                                   (state__56674)))
                                 (state__56674))))
                              (:string-join)
                              (clojure.core/let
                               [VAL__56037
                                (.valAt input__55632_nth_1__ :left)]
                               (clojure.core/let
                                [?left VAL__56037]
                                (clojure.core/let
                                 [VAL__56038
                                  (.valAt input__55632_nth_1__ :right)]
                                 (clojure.core/let
                                  [?right-1 VAL__56038]
                                  (clojure.core/let
                                   [?right-2 input__55632_nth_2__]
                                   (if
                                    (clojure.core/map?
                                     input__55632_nth_3__)
                                    (clojure.core/let
                                     [VAL__56039
                                      (.valAt
                                       input__55632_nth_3__
                                       :context)]
                                     (clojure.core/case
                                      VAL__56039
                                      (:string)
                                      (clojure.core/let
                                       [?env input__55632_nth_3__]
                                       (try
                                        [{:tag :string-join,
                                          :left ?left,
                                          :right
                                          (clojure.core/let
                                           [CATA_RESULT__9229__auto__
                                            (CATA__FN__55682
                                             ['meander.dev.parse.zeta/make-join
                                              ?right-1
                                              ?right-2
                                              ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             CATA_RESULT__9229__auto__)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             CATA_RESULT__9229__auto__
                                             0)))}]
                                        (catch
                                         java.lang.Exception
                                         e__10169__auto__
                                         (if
                                          (meander.runtime.zeta/fail?
                                           e__10169__auto__)
                                          (meander.runtime.zeta/fail)
                                          (throw e__10169__auto__)))))
                                      (state__56674)))
                                    (state__56674)))))))
                              (state__56674))))]
                          (state__56773)))
                        (state__56674)))]
                     (state__56766))
                    (state__56674))))]
                (state__56758)))
              (state__56674)))]
           (state__56713))
          (state__56674)))
        (state__56674
         []
         (if
          (clojure.core/seq? input__55632)
          (if
           (clojure.core/=
            (clojure.core/bounded-count
             (clojure.core/inc 3)
             input__55632)
            3)
           (clojure.core/let
            [input__55632_nth_0__
             (clojure.core/nth input__55632 0)
             input__55632_nth_1__
             (clojure.core/nth input__55632 1)
             input__55632_nth_2__
             (clojure.core/nth input__55632 2)]
            (clojure.core/case
             input__55632_nth_0__
             (parse-entries)
             (if
              (clojure.core/map? input__55632_nth_1__)
              (clojure.core/let
               [VAL__56042
                (.valAt input__55632_nth_1__ :meander.zeta/as)]
               (clojure.core/let
                [?pattern VAL__56042]
                (clojure.core/let
                 [X__56044
                  ((clojure.core/fn
                    [m__6993__auto__]
                    (clojure.core/dissoc
                     m__6993__auto__
                     :meander.zeta/as))
                   input__55632_nth_1__)]
                 (clojure.core/let
                  [?rest X__56044]
                  (clojure.core/letfn
                   [(save__56045 [] (state__56675))
                    (f__56775
                     []
                     (clojure.core/let
                      [?env input__55632_nth_2__]
                      (try
                       [{:tag :as,
                         :pattern
                         (clojure.core/let
                          [CATA_RESULT__9229__auto__
                           (CATA__FN__55682 [?pattern ?env])]
                          (if
                           (meander.runtime.zeta/fail?
                            CATA_RESULT__9229__auto__)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth
                            CATA_RESULT__9229__auto__
                            0))),
                         :next
                         (clojure.core/let
                          [CATA_RESULT__9229__auto__
                           (CATA__FN__55682 [?rest ?env])]
                          (if
                           (meander.runtime.zeta/fail?
                            CATA_RESULT__9229__auto__)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth
                            CATA_RESULT__9229__auto__
                            0)))}]
                       (catch
                        java.lang.Exception
                        e__10169__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10169__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10169__auto__))))))]
                   (if
                    (clojure.core/= ?rest input__55632_nth_1__)
                    (save__56045)
                    (f__56775)))))))
              (state__56675))
             (state__56675)))
           (state__56675))
          (state__56675)))
        (state__56675
         []
         (clojure.core/letfn
          [(def__56048
            [arg__56081 ?ns]
            (clojure.core/letfn
             [(state__56776
               []
               (clojure.core/let
                [x__56082 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__56082)
                 (clojure.core/let [?env arg__56081] [?env ?ns])
                 (state__56777))))
              (state__56777
               []
               (if
                (clojure.core/map? arg__56081)
                (clojure.core/let
                 [VAL__56083 (.valAt arg__56081 :aliases)]
                 (if
                  (clojure.core/map? VAL__56083)
                  (clojure.core/let
                   [X__56085 (clojure.core/set VAL__56083)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__56085))
                    (clojure.core/loop
                     [search_space__56778 (clojure.core/seq X__56085)]
                     (if
                      (clojure.core/seq search_space__56778)
                      (clojure.core/let
                       [elem__56086
                        (clojure.core/first search_space__56778)
                        result__56779
                        (clojure.core/let
                         [elem__56086_nth_0__
                          (clojure.core/nth elem__56086 0)
                          elem__56086_nth_1__
                          (clojure.core/nth elem__56086 1)]
                         (if
                          (clojure.core/symbol? elem__56086_nth_0__)
                          (clojure.core/let
                           [X__56088
                            (clojure.core/name elem__56086_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__56088)
                            (if
                             (clojure.core/symbol? elem__56086_nth_1__)
                             (clojure.core/let
                              [X__56090
                               (clojure.core/name elem__56086_nth_1__)]
                              (clojure.core/case
                               X__56090
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__56081]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__56779)
                        (recur (clojure.core/next search_space__56778))
                        result__56779))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__56776)))]
          (if
           (clojure.core/seq? input__55632)
           (if
            (clojure.core/=
             (clojure.core/bounded-count
              (clojure.core/inc 3)
              input__55632)
             3)
            (clojure.core/let
             [input__55632_nth_0__
              (clojure.core/nth input__55632 0)
              input__55632_nth_1__
              (clojure.core/nth input__55632 1)
              input__55632_nth_2__
              (clojure.core/nth input__55632 2)]
             (clojure.core/case
              input__55632_nth_0__
              (parse-entries)
              (if
               (clojure.core/map? input__55632_nth_1__)
               (clojure.core/let
                [X__56053 (clojure.core/set input__55632_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__56053))
                 (clojure.core/loop
                  [search_space__56781 (clojure.core/seq X__56053)]
                  (if
                   (clojure.core/seq search_space__56781)
                   (clojure.core/let
                    [elem__56054
                     (clojure.core/first search_space__56781)
                     result__56782
                     (clojure.core/let
                      [elem__56054_nth_0__
                       (clojure.core/nth elem__56054 0)
                       elem__56054_nth_1__
                       (clojure.core/nth elem__56054 1)]
                      (clojure.core/let
                       [*m__55651 elem__56054_nth_0__]
                       (if
                        (clojure.core/symbol? elem__56054_nth_0__)
                        (clojure.core/let
                         [X__56056
                          (clojure.core/namespace elem__56054_nth_0__)]
                         (clojure.core/let
                          [?ns X__56056]
                          (clojure.core/let
                           [X__56058
                            (clojure.core/name elem__56054_nth_0__)]
                           (if
                            (clojure.core/string? X__56058)
                            (if
                             (clojure.core/re-matches #"&.*" X__56058)
                             (clojure.core/let
                              [?pattern elem__56054_nth_1__]
                              (clojure.core/let
                               [X__56060
                                ((clojure.core/fn
                                  [m__6993__auto__]
                                  (clojure.core/dissoc
                                   m__6993__auto__
                                   *m__55651))
                                 input__55632_nth_1__)]
                               (clojure.core/let
                                [?rest X__56060]
                                (clojure.core/let
                                 [x__7926__auto__
                                  (def__56048
                                   input__55632_nth_2__
                                   ?ns)]
                                 (if
                                  (meander.runtime.zeta/fail?
                                   x__7926__auto__)
                                  (meander.runtime.zeta/fail)
                                  (clojure.core/let
                                   [[?env ?ns] x__7926__auto__]
                                   (try
                                    [{:tag :rest-map,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9229__auto__
                                        (CATA__FN__55682
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9229__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9229__auto__
                                         0))),
                                      :next
                                      (clojure.core/list
                                       'parse-entries
                                       ?rest
                                       ?env)}]
                                    (catch
                                     java.lang.Exception
                                     e__10169__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10169__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10169__auto__))))))))))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))))
                        (meander.runtime.zeta/fail))))]
                    (if
                     (meander.runtime.zeta/fail? result__56782)
                     (recur (clojure.core/next search_space__56781))
                     result__56782))
                   (state__56676)))
                 (state__56676)))
               (state__56676))
              (state__56676)))
            (state__56676))
           (state__56676))))
        (state__56676
         []
         (if
          (clojure.core/seq? input__55632)
          (if
           (clojure.core/=
            (clojure.core/bounded-count
             (clojure.core/inc 3)
             input__55632)
            3)
           (clojure.core/let
            [input__55632_nth_0__
             (clojure.core/nth input__55632 0)
             input__55632_nth_1__
             (clojure.core/nth input__55632 1)
             input__55632_nth_2__
             (clojure.core/nth input__55632 2)]
            (clojure.core/case
             input__55632_nth_0__
             (parse-entries)
             (if
              (clojure.core/map? input__55632_nth_1__)
              (clojure.core/letfn
               [(state__56784
                 []
                 (clojure.core/let
                  [X__56104 (clojure.core/set input__55632_nth_1__)]
                  (if
                   (clojure.core/<= 1 (clojure.core/count X__56104))
                   (clojure.core/loop
                    [search_space__56786 (clojure.core/seq X__56104)]
                    (if
                     (clojure.core/seq search_space__56786)
                     (clojure.core/let
                      [elem__56105
                       (clojure.core/first search_space__56786)
                       result__56787
                       (clojure.core/let
                        [elem__56105_nth_0__
                         (clojure.core/nth elem__56105 0)
                         elem__56105_nth_1__
                         (clojure.core/nth elem__56105 1)]
                        (clojure.core/let
                         [?key-pattern elem__56105_nth_0__]
                         (clojure.core/let
                          [?val-pattern elem__56105_nth_1__]
                          (clojure.core/let
                           [X__56107
                            ((clojure.core/fn
                              [m__6993__auto__]
                              (clojure.core/dissoc
                               m__6993__auto__
                               ?key-pattern))
                             input__55632_nth_1__)]
                           (clojure.core/let
                            [?rest X__56107]
                            (clojure.core/let
                             [?env input__55632_nth_2__]
                             (try
                              [{:tag :entry,
                                :key-pattern
                                (clojure.core/let
                                 [CATA_RESULT__9229__auto__
                                  (CATA__FN__55682
                                   [?key-pattern ?env])]
                                 (if
                                  (meander.runtime.zeta/fail?
                                   CATA_RESULT__9229__auto__)
                                  (throw (meander.runtime.zeta/fail))
                                  (clojure.core/nth
                                   CATA_RESULT__9229__auto__
                                   0))),
                                :val-pattern
                                (clojure.core/let
                                 [CATA_RESULT__9229__auto__
                                  (CATA__FN__55682
                                   [?val-pattern ?env])]
                                 (if
                                  (meander.runtime.zeta/fail?
                                   CATA_RESULT__9229__auto__)
                                  (throw (meander.runtime.zeta/fail))
                                  (clojure.core/nth
                                   CATA_RESULT__9229__auto__
                                   0))),
                                :next
                                (clojure.core/list
                                 'parse-entries
                                 ?rest
                                 ?env)}]
                              (catch
                               java.lang.Exception
                               e__10169__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__10169__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__10169__auto__))))))))))]
                      (if
                       (meander.runtime.zeta/fail? result__56787)
                       (recur (clojure.core/next search_space__56786))
                       result__56787))
                     (state__56785)))
                   (state__56785))))
                (state__56785
                 []
                 (clojure.core/let
                  [?env input__55632_nth_2__]
                  (try
                   [{:tag :some-map}]
                   (catch
                    java.lang.Exception
                    e__10169__auto__
                    (if
                     (meander.runtime.zeta/fail? e__10169__auto__)
                     (meander.runtime.zeta/fail)
                     (throw e__10169__auto__))))))]
               (state__56784))
              (state__56677))
             (state__56677)))
           (state__56677))
          (state__56677)))
        (state__56677
         []
         (if
          (clojure.core/vector? input__55632)
          (clojure.core/letfn
           [(state__56789
             []
             (if
              (clojure.core/= (clojure.core/count input__55632) 3)
              (clojure.core/let
               [input__55632_nth_0__
                (clojure.core/nth input__55632 0)
                input__55632_nth_1__
                (clojure.core/nth input__55632 1)
                input__55632_nth_2__
                (clojure.core/nth input__55632 2)]
               (clojure.core/case
                input__55632_nth_0__
                (meander.dev.parse.zeta/parse-with-bindings)
                (if
                 (clojure.core/vector? input__55632_nth_1__)
                 (clojure.core/letfn
                  [(state__56791
                    []
                    (clojure.core/case
                     input__55632_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__55632_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__10169__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10169__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10169__auto__)))))
                     (state__56792)))
                   (state__56792
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__55632_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__55632_nth_2__]
                      (try
                       [[{:tag :error,
                          :message
                          "meander.zeta/with expects an even number of bindings"}]]
                       (catch
                        java.lang.Exception
                        e__10169__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10169__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10169__auto__)))))
                     (state__56793)))
                   (state__56793
                    []
                    (clojure.core/let
                     [input__55632_nth_1___l__
                      (clojure.core/subvec
                       input__55632_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__55632_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__55632_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__55632_nth_1___r__
                        (clojure.core/subvec input__55632_nth_1__ 2)]
                       (clojure.core/let
                        [input__55632_nth_1___l___nth_0__
                         (clojure.core/nth input__55632_nth_1___l__ 0)
                         input__55632_nth_1___l___nth_1__
                         (clojure.core/nth input__55632_nth_1___l__ 1)]
                        (clojure.core/letfn
                         [(state__56794
                           []
                           (if
                            (clojure.core/symbol?
                             input__55632_nth_1___l___nth_0__)
                            (clojure.core/let
                             [X__56121
                              (clojure.core/namespace
                               input__55632_nth_1___l___nth_0__)]
                             (clojure.core/let
                              [X__56123
                               (clojure.core/name
                                input__55632_nth_1___l___nth_0__)]
                              (if
                               (clojure.core/string? X__56123)
                               (if
                                (clojure.core/re-matches
                                 #"%.+"
                                 X__56123)
                                (clojure.core/let
                                 [?symbol
                                  input__55632_nth_1___l___nth_0__]
                                 (clojure.core/let
                                  [?pattern
                                   input__55632_nth_1___l___nth_1__]
                                  (clojure.core/let
                                   [?rest input__55632_nth_1___r__]
                                   (clojure.core/let
                                    [?env input__55632_nth_2__]
                                    (try
                                     [(clojure.core/into
                                       []
                                       (clojure.core/concat
                                        (clojure.core/list
                                         {:tag :with-binding,
                                          :reference
                                          {:tag :reference,
                                           :symbol ?symbol,
                                           :form ?symbol},
                                          :pattern
                                          (clojure.core/let
                                           [CATA_RESULT__9229__auto__
                                            (CATA__FN__55682
                                             [?pattern ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             CATA_RESULT__9229__auto__)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             CATA_RESULT__9229__auto__
                                             0)))})
                                        (clojure.core/let
                                         [CATA_RESULT__9229__auto__
                                          (CATA__FN__55682
                                           ['meander.dev.parse.zeta/parse-with-bindings
                                            ?rest
                                            ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__9229__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__9229__auto__
                                           0)))))]
                                     (catch
                                      java.lang.Exception
                                      e__10169__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10169__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10169__auto__))))))))
                                (state__56795))
                               (state__56795))))
                            (state__56795)))
                          (state__56795
                           []
                           (clojure.core/let
                            [?x input__55632_nth_1___l___nth_0__]
                            (clojure.core/let
                             [?pattern
                              input__55632_nth_1___l___nth_1__]
                             (clojure.core/let
                              [?rest input__55632_nth_1___r__]
                              (clojure.core/let
                               [?env input__55632_nth_2__]
                               (try
                                [[{:tag :error,
                                   :message
                                   "meander.zeta/with bindings must be an repeating sequence of %name pattern"}]]
                                (catch
                                 java.lang.Exception
                                 e__10169__auto__
                                 (if
                                  (meander.runtime.zeta/fail?
                                   e__10169__auto__)
                                  (meander.runtime.zeta/fail)
                                  (throw e__10169__auto__)))))))))]
                         (state__56794))))
                      (state__56790))))]
                  (state__56791))
                 (state__56790))
                (state__56790)))
              (state__56790)))
            (state__56790
             []
             (if
              (clojure.core/= (clojure.core/count input__55632) 2)
              (clojure.core/let
               [input__55632_nth_0__
                (clojure.core/nth input__55632 0)
                input__55632_nth_1__
                (clojure.core/nth input__55632 1)]
               (if
                (clojure.core/vector? input__55632_nth_0__)
                (clojure.core/let
                 [?sequence input__55632_nth_0__]
                 (clojure.core/let
                  [?form input__55632_nth_0__]
                  (clojure.core/let
                   [?env input__55632_nth_1__]
                   (try
                    [{:tag :vector,
                      :next
                      (clojure.core/let
                       [CATA_RESULT__9229__auto__
                        (CATA__FN__55682
                         ['meander.dev.parse.zeta/parse-sequential
                          ?sequence
                          ?env])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__9229__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__9229__auto__
                         0))),
                      :form ?form}]
                    (catch
                     java.lang.Exception
                     e__10169__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10169__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10169__auto__)))))))
                (state__56678)))
              (state__56678)))]
           (state__56789))
          (state__56678)))
        (state__56678
         []
         (clojure.core/letfn
          [(def__56133
            [arg__56156 ?__55633]
            (clojure.core/letfn
             [(state__56796
               []
               (clojure.core/let
                [x__56157 "meander.zeta"]
                (if
                 (clojure.core/= ?__55633 x__56157)
                 [?__55633]
                 (state__56797))))
              (state__56797
               []
               (if
                (clojure.core/map? arg__56156)
                (clojure.core/let
                 [VAL__56158 (.valAt arg__56156 :aliases)]
                 (if
                  (clojure.core/map? VAL__56158)
                  (clojure.core/let
                   [X__56160 (clojure.core/set VAL__56158)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__56160))
                    (clojure.core/loop
                     [search_space__56798 (clojure.core/seq X__56160)]
                     (if
                      (clojure.core/seq search_space__56798)
                      (clojure.core/let
                       [elem__56161
                        (clojure.core/first search_space__56798)
                        result__56799
                        (clojure.core/let
                         [elem__56161_nth_0__
                          (clojure.core/nth elem__56161 0)
                          elem__56161_nth_1__
                          (clojure.core/nth elem__56161 1)]
                         (if
                          (clojure.core/symbol? elem__56161_nth_0__)
                          (clojure.core/let
                           [X__56163
                            (clojure.core/name elem__56161_nth_0__)]
                           (if
                            (clojure.core/= ?__55633 X__56163)
                            (if
                             (clojure.core/symbol? elem__56161_nth_1__)
                             (clojure.core/let
                              [X__56165
                               (clojure.core/name elem__56161_nth_1__)]
                              (clojure.core/case
                               X__56165
                               ("meander.zeta")
                               [?__55633]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__56799)
                        (recur (clojure.core/next search_space__56798))
                        result__56799))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__56796)))]
          (if
           (clojure.core/vector? input__55632)
           (if
            (clojure.core/= (clojure.core/count input__55632) 2)
            (clojure.core/let
             [input__55632_nth_0__
              (clojure.core/nth input__55632 0)
              input__55632_nth_1__
              (clojure.core/nth input__55632 1)]
             (if
              (clojure.core/seq? input__55632_nth_0__)
              (clojure.core/let
               [input__55632_nth_0___l__
                (clojure.core/take 1 input__55632_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__55632_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__55632_nth_0___r__
                  (clojure.core/drop 1 input__55632_nth_0__)]
                 (clojure.core/let
                  [input__55632_nth_0___l___nth_0__
                   (clojure.core/nth input__55632_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__55632_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__56143
                     (clojure.core/namespace
                      input__55632_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__55633 X__56143]
                     (clojure.core/let
                      [X__56145
                       (clojure.core/name
                        input__55632_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__56145
                       ("with")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__56133 input__55632_nth_1__ ?__55633)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__56679)
                         (clojure.core/let
                          [[?__55633] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__55632)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__55632)
                             2)
                            (clojure.core/let
                             [input__55632_nth_0__
                              (clojure.core/nth input__55632 0)
                              input__55632_nth_1__
                              (clojure.core/nth input__55632 1)]
                             (if
                              (clojure.core/seq? input__55632_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__55632_nth_0__)
                                3)
                               (clojure.core/let
                                [input__55632_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__55632_nth_0__
                                  1)
                                 input__55632_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__55632_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__55632_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__55632_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__55632_nth_0__]
                                   (clojure.core/let
                                    [?env input__55632_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__9229__auto__
                                          (CATA__FN__55682
                                           ['meander.dev.parse.zeta/parse-with-bindings
                                            ?bindings
                                            ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__9229__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__9229__auto__
                                           0)))},
                                       :body
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__55682
                                          [?body ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9229__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9229__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10169__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10169__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10169__auto__)))))))))
                               (state__56679))
                              (state__56679)))
                            (state__56679))
                           (state__56679)))))
                       (state__56679)))))
                   (state__56679))))
                (state__56679)))
              (state__56679)))
            (state__56679))
           (state__56679))))
        (state__56679
         []
         (clojure.core/letfn
          [(def__56167
            [arg__56190 ?__55634]
            (clojure.core/letfn
             [(state__56801
               []
               (clojure.core/let
                [x__56191 "meander.zeta"]
                (if
                 (clojure.core/= ?__55634 x__56191)
                 [?__55634]
                 (state__56802))))
              (state__56802
               []
               (if
                (clojure.core/map? arg__56190)
                (clojure.core/let
                 [VAL__56192 (.valAt arg__56190 :aliases)]
                 (if
                  (clojure.core/map? VAL__56192)
                  (clojure.core/let
                   [X__56194 (clojure.core/set VAL__56192)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__56194))
                    (clojure.core/loop
                     [search_space__56803 (clojure.core/seq X__56194)]
                     (if
                      (clojure.core/seq search_space__56803)
                      (clojure.core/let
                       [elem__56195
                        (clojure.core/first search_space__56803)
                        result__56804
                        (clojure.core/let
                         [elem__56195_nth_0__
                          (clojure.core/nth elem__56195 0)
                          elem__56195_nth_1__
                          (clojure.core/nth elem__56195 1)]
                         (if
                          (clojure.core/symbol? elem__56195_nth_0__)
                          (clojure.core/let
                           [X__56197
                            (clojure.core/name elem__56195_nth_0__)]
                           (if
                            (clojure.core/= ?__55634 X__56197)
                            (if
                             (clojure.core/symbol? elem__56195_nth_1__)
                             (clojure.core/let
                              [X__56199
                               (clojure.core/name elem__56195_nth_1__)]
                              (clojure.core/case
                               X__56199
                               ("meander.zeta")
                               [?__55634]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__56804)
                        (recur (clojure.core/next search_space__56803))
                        result__56804))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__56801)))]
          (if
           (clojure.core/vector? input__55632)
           (if
            (clojure.core/= (clojure.core/count input__55632) 2)
            (clojure.core/let
             [input__55632_nth_0__
              (clojure.core/nth input__55632 0)
              input__55632_nth_1__
              (clojure.core/nth input__55632 1)]
             (if
              (clojure.core/seq? input__55632_nth_0__)
              (clojure.core/let
               [input__55632_nth_0___l__
                (clojure.core/take 1 input__55632_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__55632_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__55632_nth_0___r__
                  (clojure.core/drop 1 input__55632_nth_0__)]
                 (clojure.core/let
                  [input__55632_nth_0___l___nth_0__
                   (clojure.core/nth input__55632_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__55632_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__56177
                     (clojure.core/namespace
                      input__55632_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__55634 X__56177]
                     (clojure.core/let
                      [X__56179
                       (clojure.core/name
                        input__55632_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__56179
                       ("apply")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__56167 input__55632_nth_1__ ?__55634)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__56680)
                         (clojure.core/let
                          [[?__55634] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__55632)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__55632)
                             2)
                            (clojure.core/let
                             [input__55632_nth_0__
                              (clojure.core/nth input__55632 0)
                              input__55632_nth_1__
                              (clojure.core/nth input__55632 1)]
                             (if
                              (clojure.core/seq? input__55632_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__55632_nth_0__)
                                3)
                               (clojure.core/let
                                [input__55632_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__55632_nth_0__
                                  1)
                                 input__55632_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__55632_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__55632_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__55632_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__55632_nth_0__]
                                   (clojure.core/let
                                    [?env input__55632_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__55682
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9229__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9229__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10169__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10169__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10169__auto__)))))))))
                               (state__56680))
                              (state__56680)))
                            (state__56680))
                           (state__56680)))))
                       (state__56680)))))
                   (state__56680))))
                (state__56680)))
              (state__56680)))
            (state__56680))
           (state__56680))))
        (state__56680
         []
         (clojure.core/letfn
          [(def__56201
            [arg__56224 ?__55635]
            (clojure.core/letfn
             [(state__56806
               []
               (clojure.core/let
                [x__56225 "meander.zeta"]
                (if
                 (clojure.core/= ?__55635 x__56225)
                 [?__55635]
                 (state__56807))))
              (state__56807
               []
               (if
                (clojure.core/map? arg__56224)
                (clojure.core/let
                 [VAL__56226 (.valAt arg__56224 :aliases)]
                 (if
                  (clojure.core/map? VAL__56226)
                  (clojure.core/let
                   [X__56228 (clojure.core/set VAL__56226)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__56228))
                    (clojure.core/loop
                     [search_space__56808 (clojure.core/seq X__56228)]
                     (if
                      (clojure.core/seq search_space__56808)
                      (clojure.core/let
                       [elem__56229
                        (clojure.core/first search_space__56808)
                        result__56809
                        (clojure.core/let
                         [elem__56229_nth_0__
                          (clojure.core/nth elem__56229 0)
                          elem__56229_nth_1__
                          (clojure.core/nth elem__56229 1)]
                         (if
                          (clojure.core/symbol? elem__56229_nth_0__)
                          (clojure.core/let
                           [X__56231
                            (clojure.core/name elem__56229_nth_0__)]
                           (if
                            (clojure.core/= ?__55635 X__56231)
                            (if
                             (clojure.core/symbol? elem__56229_nth_1__)
                             (clojure.core/let
                              [X__56233
                               (clojure.core/name elem__56229_nth_1__)]
                              (clojure.core/case
                               X__56233
                               ("meander.zeta")
                               [?__55635]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__56809)
                        (recur (clojure.core/next search_space__56808))
                        result__56809))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__56806)))]
          (if
           (clojure.core/vector? input__55632)
           (if
            (clojure.core/= (clojure.core/count input__55632) 2)
            (clojure.core/let
             [input__55632_nth_0__
              (clojure.core/nth input__55632 0)
              input__55632_nth_1__
              (clojure.core/nth input__55632 1)]
             (if
              (clojure.core/seq? input__55632_nth_0__)
              (clojure.core/let
               [input__55632_nth_0___l__
                (clojure.core/take 1 input__55632_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__55632_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__55632_nth_0___r__
                  (clojure.core/drop 1 input__55632_nth_0__)]
                 (clojure.core/let
                  [input__55632_nth_0___l___nth_0__
                   (clojure.core/nth input__55632_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__55632_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__56211
                     (clojure.core/namespace
                      input__55632_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__55635 X__56211]
                     (clojure.core/let
                      [X__56213
                       (clojure.core/name
                        input__55632_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__56213
                       ("and")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__56201 input__55632_nth_1__ ?__55635)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__56681)
                         (clojure.core/let
                          [[?__55635] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__55632)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__55632)
                             2)
                            (clojure.core/let
                             [input__55632_nth_0__
                              (clojure.core/nth input__55632 0)
                              input__55632_nth_1__
                              (clojure.core/nth input__55632 1)]
                             (if
                              (clojure.core/seq? input__55632_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__55632_nth_0__)
                                3)
                               (clojure.core/let
                                [input__55632_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__55632_nth_0__
                                  1)
                                 input__55632_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__55632_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__55632_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right input__55632_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__55632_nth_0__]
                                   (clojure.core/let
                                    [?env input__55632_nth_1__]
                                    (try
                                     [{:tag :and,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__55682
                                          [?left ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9229__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9229__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__55682
                                          [?right ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9229__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9229__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10169__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10169__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10169__auto__)))))))))
                               (state__56681))
                              (state__56681)))
                            (state__56681))
                           (state__56681)))))
                       (state__56681)))))
                   (state__56681))))
                (state__56681)))
              (state__56681)))
            (state__56681))
           (state__56681))))
        (state__56681
         []
         (clojure.core/letfn
          [(def__56235
            [arg__56258 ?__55636]
            (clojure.core/letfn
             [(state__56811
               []
               (clojure.core/let
                [x__56259 "meander.zeta"]
                (if
                 (clojure.core/= ?__55636 x__56259)
                 [?__55636]
                 (state__56812))))
              (state__56812
               []
               (if
                (clojure.core/map? arg__56258)
                (clojure.core/let
                 [VAL__56260 (.valAt arg__56258 :aliases)]
                 (if
                  (clojure.core/map? VAL__56260)
                  (clojure.core/let
                   [X__56262 (clojure.core/set VAL__56260)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__56262))
                    (clojure.core/loop
                     [search_space__56813 (clojure.core/seq X__56262)]
                     (if
                      (clojure.core/seq search_space__56813)
                      (clojure.core/let
                       [elem__56263
                        (clojure.core/first search_space__56813)
                        result__56814
                        (clojure.core/let
                         [elem__56263_nth_0__
                          (clojure.core/nth elem__56263 0)
                          elem__56263_nth_1__
                          (clojure.core/nth elem__56263 1)]
                         (if
                          (clojure.core/symbol? elem__56263_nth_0__)
                          (clojure.core/let
                           [X__56265
                            (clojure.core/name elem__56263_nth_0__)]
                           (if
                            (clojure.core/= ?__55636 X__56265)
                            (if
                             (clojure.core/symbol? elem__56263_nth_1__)
                             (clojure.core/let
                              [X__56267
                               (clojure.core/name elem__56263_nth_1__)]
                              (clojure.core/case
                               X__56267
                               ("meander.zeta")
                               [?__55636]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__56814)
                        (recur (clojure.core/next search_space__56813))
                        result__56814))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__56811)))]
          (if
           (clojure.core/vector? input__55632)
           (if
            (clojure.core/= (clojure.core/count input__55632) 2)
            (clojure.core/let
             [input__55632_nth_0__
              (clojure.core/nth input__55632 0)
              input__55632_nth_1__
              (clojure.core/nth input__55632 1)]
             (if
              (clojure.core/seq? input__55632_nth_0__)
              (clojure.core/let
               [input__55632_nth_0___l__
                (clojure.core/take 1 input__55632_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__55632_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__55632_nth_0___r__
                  (clojure.core/drop 1 input__55632_nth_0__)]
                 (clojure.core/let
                  [input__55632_nth_0___l___nth_0__
                   (clojure.core/nth input__55632_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__55632_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__56245
                     (clojure.core/namespace
                      input__55632_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__55636 X__56245]
                     (clojure.core/let
                      [X__56247
                       (clojure.core/name
                        input__55632_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__56247
                       ("cata")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__56235 input__55632_nth_1__ ?__55636)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__56682)
                         (clojure.core/let
                          [[?__55636] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__55632)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__55632)
                             2)
                            (clojure.core/let
                             [input__55632_nth_0__
                              (clojure.core/nth input__55632 0)
                              input__55632_nth_1__
                              (clojure.core/nth input__55632 1)]
                             (if
                              (clojure.core/seq? input__55632_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__55632_nth_0__)
                                2)
                               (clojure.core/let
                                [input__55632_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__55632_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__55632_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__55632_nth_0__]
                                  (clojure.core/let
                                   [?env input__55632_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9229__auto__
                                        (CATA__FN__55682
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9229__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9229__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__10169__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10169__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10169__auto__))))))))
                               (state__56682))
                              (state__56682)))
                            (state__56682))
                           (state__56682)))))
                       (state__56682)))))
                   (state__56682))))
                (state__56682)))
              (state__56682)))
            (state__56682))
           (state__56682))))
        (state__56682
         []
         (clojure.core/letfn
          [(def__56269
            [arg__56292 ?__55637]
            (clojure.core/letfn
             [(state__56816
               []
               (clojure.core/let
                [x__56293 "meander.zeta"]
                (if
                 (clojure.core/= ?__55637 x__56293)
                 [?__55637]
                 (state__56817))))
              (state__56817
               []
               (if
                (clojure.core/map? arg__56292)
                (clojure.core/let
                 [VAL__56294 (.valAt arg__56292 :aliases)]
                 (if
                  (clojure.core/map? VAL__56294)
                  (clojure.core/let
                   [X__56296 (clojure.core/set VAL__56294)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__56296))
                    (clojure.core/loop
                     [search_space__56818 (clojure.core/seq X__56296)]
                     (if
                      (clojure.core/seq search_space__56818)
                      (clojure.core/let
                       [elem__56297
                        (clojure.core/first search_space__56818)
                        result__56819
                        (clojure.core/let
                         [elem__56297_nth_0__
                          (clojure.core/nth elem__56297 0)
                          elem__56297_nth_1__
                          (clojure.core/nth elem__56297 1)]
                         (if
                          (clojure.core/symbol? elem__56297_nth_0__)
                          (clojure.core/let
                           [X__56299
                            (clojure.core/name elem__56297_nth_0__)]
                           (if
                            (clojure.core/= ?__55637 X__56299)
                            (if
                             (clojure.core/symbol? elem__56297_nth_1__)
                             (clojure.core/let
                              [X__56301
                               (clojure.core/name elem__56297_nth_1__)]
                              (clojure.core/case
                               X__56301
                               ("meander.zeta")
                               [?__55637]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__56819)
                        (recur (clojure.core/next search_space__56818))
                        result__56819))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__56816)))]
          (if
           (clojure.core/vector? input__55632)
           (if
            (clojure.core/= (clojure.core/count input__55632) 2)
            (clojure.core/let
             [input__55632_nth_0__
              (clojure.core/nth input__55632 0)
              input__55632_nth_1__
              (clojure.core/nth input__55632 1)]
             (if
              (clojure.core/seq? input__55632_nth_0__)
              (clojure.core/let
               [input__55632_nth_0___l__
                (clojure.core/take 1 input__55632_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__55632_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__55632_nth_0___r__
                  (clojure.core/drop 1 input__55632_nth_0__)]
                 (clojure.core/let
                  [input__55632_nth_0___l___nth_0__
                   (clojure.core/nth input__55632_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__55632_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__56279
                     (clojure.core/namespace
                      input__55632_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__55637 X__56279]
                     (clojure.core/let
                      [X__56281
                       (clojure.core/name
                        input__55632_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__56281
                       ("fold")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__56269 input__55632_nth_1__ ?__55637)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__56683)
                         (clojure.core/let
                          [[?__55637] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__55632)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__55632)
                             2)
                            (clojure.core/let
                             [input__55632_nth_0__
                              (clojure.core/nth input__55632 0)
                              input__55632_nth_1__
                              (clojure.core/nth input__55632 1)]
                             (if
                              (clojure.core/seq? input__55632_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__55632_nth_0__)
                                4)
                               (clojure.core/let
                                [input__55632_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__55632_nth_0__
                                  1)
                                 input__55632_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__55632_nth_0__
                                  2)
                                 input__55632_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__55632_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__55632_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__55632_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__55632_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__55632_nth_0__]
                                    (clojure.core/let
                                     [?env input__55632_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__55682
                                          ['meander.dev.parse.zeta/make-fold
                                           (clojure.core/let
                                            [CATA_RESULT__9229__auto__
                                             (CATA__FN__55682
                                              [?mutable-variable
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9229__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9229__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__9229__auto__
                                             (CATA__FN__55682
                                              [?initial-value ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9229__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9229__auto__
                                              0)))
                                           ?fold-function
                                           ?form])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9229__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9229__auto__
                                          0)))]
                                      (catch
                                       java.lang.Exception
                                       e__10169__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__10169__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__10169__auto__))))))))))
                               (state__56683))
                              (state__56683)))
                            (state__56683))
                           (state__56683)))))
                       (state__56683)))))
                   (state__56683))))
                (state__56683)))
              (state__56683)))
            (state__56683))
           (state__56683))))
        (state__56683
         []
         (if
          (clojure.core/vector? input__55632)
          (if
           (clojure.core/= (clojure.core/count input__55632) 5)
           (clojure.core/let
            [input__55632_nth_0__
             (clojure.core/nth input__55632 0)
             input__55632_nth_1__
             (clojure.core/nth input__55632 1)
             input__55632_nth_2__
             (clojure.core/nth input__55632 2)
             input__55632_nth_3__
             (clojure.core/nth input__55632 3)
             input__55632_nth_4__
             (clojure.core/nth input__55632 4)]
            (clojure.core/case
             input__55632_nth_0__
             (meander.dev.parse.zeta/make-fold)
             (if
              (clojure.core/map? input__55632_nth_1__)
              (clojure.core/let
               [VAL__56304 (.valAt input__55632_nth_1__ :tag)]
               (clojure.core/case
                VAL__56304
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__55632_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__55632_nth_2__]
                  (clojure.core/let
                   [?fold-function input__55632_nth_3__]
                   (clojure.core/let
                    [?form input__55632_nth_4__]
                    (try
                     [{:tag :fold,
                       :variable ?variable-ast,
                       :initial-value ?initial-value-ast,
                       :fold-function
                       {:tag :host-expression, :form ?fold-function},
                       :form ?form}]
                     (catch
                      java.lang.Exception
                      e__10169__auto__
                      (if
                       (meander.runtime.zeta/fail? e__10169__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__10169__auto__))))))))
                (state__56684)))
              (state__56684))
             (state__56684)))
           (state__56684))
          (state__56684)))
        (state__56684
         []
         (clojure.core/letfn
          [(def__56306
            [arg__56329 ?__55638]
            (clojure.core/letfn
             [(state__56821
               []
               (clojure.core/let
                [x__56330 "meander.zeta"]
                (if
                 (clojure.core/= ?__55638 x__56330)
                 [?__55638]
                 (state__56822))))
              (state__56822
               []
               (if
                (clojure.core/map? arg__56329)
                (clojure.core/let
                 [VAL__56331 (.valAt arg__56329 :aliases)]
                 (if
                  (clojure.core/map? VAL__56331)
                  (clojure.core/let
                   [X__56333 (clojure.core/set VAL__56331)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__56333))
                    (clojure.core/loop
                     [search_space__56823 (clojure.core/seq X__56333)]
                     (if
                      (clojure.core/seq search_space__56823)
                      (clojure.core/let
                       [elem__56334
                        (clojure.core/first search_space__56823)
                        result__56824
                        (clojure.core/let
                         [elem__56334_nth_0__
                          (clojure.core/nth elem__56334 0)
                          elem__56334_nth_1__
                          (clojure.core/nth elem__56334 1)]
                         (if
                          (clojure.core/symbol? elem__56334_nth_0__)
                          (clojure.core/let
                           [X__56336
                            (clojure.core/name elem__56334_nth_0__)]
                           (if
                            (clojure.core/= ?__55638 X__56336)
                            (if
                             (clojure.core/symbol? elem__56334_nth_1__)
                             (clojure.core/let
                              [X__56338
                               (clojure.core/name elem__56334_nth_1__)]
                              (clojure.core/case
                               X__56338
                               ("meander.zeta")
                               [?__55638]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__56824)
                        (recur (clojure.core/next search_space__56823))
                        result__56824))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__56821)))]
          (if
           (clojure.core/vector? input__55632)
           (if
            (clojure.core/= (clojure.core/count input__55632) 2)
            (clojure.core/let
             [input__55632_nth_0__
              (clojure.core/nth input__55632 0)
              input__55632_nth_1__
              (clojure.core/nth input__55632 1)]
             (if
              (clojure.core/seq? input__55632_nth_0__)
              (clojure.core/let
               [input__55632_nth_0___l__
                (clojure.core/take 1 input__55632_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__55632_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__55632_nth_0___r__
                  (clojure.core/drop 1 input__55632_nth_0__)]
                 (clojure.core/let
                  [input__55632_nth_0___l___nth_0__
                   (clojure.core/nth input__55632_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__55632_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__56316
                     (clojure.core/namespace
                      input__55632_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__55638 X__56316]
                     (clojure.core/let
                      [X__56318
                       (clojure.core/name
                        input__55632_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__56318
                       ("let")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__56306 input__55632_nth_1__ ?__55638)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__56685)
                         (clojure.core/let
                          [[?__55638] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__55632)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__55632)
                             2)
                            (clojure.core/let
                             [input__55632_nth_0__
                              (clojure.core/nth input__55632 0)
                              input__55632_nth_1__
                              (clojure.core/nth input__55632 1)]
                             (if
                              (clojure.core/seq? input__55632_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__55632_nth_0__)
                                3)
                               (clojure.core/let
                                [input__55632_nth_0___nth_0__
                                 (clojure.core/nth
                                  input__55632_nth_0__
                                  0)
                                 input__55632_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__55632_nth_0__
                                  1)
                                 input__55632_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__55632_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__55632_nth_0___nth_0__]
                                 (clojure.core/let
                                  [?expression
                                   input__55632_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?next input__55632_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?form input__55632_nth_0__]
                                    (clojure.core/let
                                     [?env input__55632_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__9229__auto__
                                          (CATA__FN__55682
                                           [?pattern ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__9229__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__9229__auto__
                                           0))),
                                        :expression
                                        {:tag :host-expression,
                                         :form ?expression},
                                        :next
                                        (clojure.core/let
                                         [CATA_RESULT__9229__auto__
                                          (CATA__FN__55682
                                           [?next ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__9229__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__9229__auto__
                                           0)))}]
                                      (catch
                                       java.lang.Exception
                                       e__10169__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__10169__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__10169__auto__))))))))))
                               (state__56685))
                              (state__56685)))
                            (state__56685))
                           (state__56685)))))
                       (state__56685)))))
                   (state__56685))))
                (state__56685)))
              (state__56685)))
            (state__56685))
           (state__56685))))
        (state__56685
         []
         (clojure.core/letfn
          [(def__56340
            [arg__56363 ?__55639]
            (clojure.core/letfn
             [(state__56826
               []
               (clojure.core/let
                [x__56364 "meander.zeta"]
                (if
                 (clojure.core/= ?__55639 x__56364)
                 [?__55639]
                 (state__56827))))
              (state__56827
               []
               (if
                (clojure.core/map? arg__56363)
                (clojure.core/let
                 [VAL__56365 (.valAt arg__56363 :aliases)]
                 (if
                  (clojure.core/map? VAL__56365)
                  (clojure.core/let
                   [X__56367 (clojure.core/set VAL__56365)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__56367))
                    (clojure.core/loop
                     [search_space__56828 (clojure.core/seq X__56367)]
                     (if
                      (clojure.core/seq search_space__56828)
                      (clojure.core/let
                       [elem__56368
                        (clojure.core/first search_space__56828)
                        result__56829
                        (clojure.core/let
                         [elem__56368_nth_0__
                          (clojure.core/nth elem__56368 0)
                          elem__56368_nth_1__
                          (clojure.core/nth elem__56368 1)]
                         (if
                          (clojure.core/symbol? elem__56368_nth_0__)
                          (clojure.core/let
                           [X__56370
                            (clojure.core/name elem__56368_nth_0__)]
                           (if
                            (clojure.core/= ?__55639 X__56370)
                            (if
                             (clojure.core/symbol? elem__56368_nth_1__)
                             (clojure.core/let
                              [X__56372
                               (clojure.core/name elem__56368_nth_1__)]
                              (clojure.core/case
                               X__56372
                               ("meander.zeta")
                               [?__55639]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__56829)
                        (recur (clojure.core/next search_space__56828))
                        result__56829))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__56826)))]
          (if
           (clojure.core/vector? input__55632)
           (if
            (clojure.core/= (clojure.core/count input__55632) 2)
            (clojure.core/let
             [input__55632_nth_0__
              (clojure.core/nth input__55632 0)
              input__55632_nth_1__
              (clojure.core/nth input__55632 1)]
             (if
              (clojure.core/seq? input__55632_nth_0__)
              (clojure.core/let
               [input__55632_nth_0___l__
                (clojure.core/take 1 input__55632_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__55632_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__55632_nth_0___r__
                  (clojure.core/drop 1 input__55632_nth_0__)]
                 (clojure.core/let
                  [input__55632_nth_0___l___nth_0__
                   (clojure.core/nth input__55632_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__55632_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__56350
                     (clojure.core/namespace
                      input__55632_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__55639 X__56350]
                     (clojure.core/let
                      [X__56352
                       (clojure.core/name
                        input__55632_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__56352
                       ("not")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__56340 input__55632_nth_1__ ?__55639)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__56686)
                         (clojure.core/let
                          [[?__55639] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__55632)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__55632)
                             2)
                            (clojure.core/let
                             [input__55632_nth_0__
                              (clojure.core/nth input__55632 0)
                              input__55632_nth_1__
                              (clojure.core/nth input__55632 1)]
                             (if
                              (clojure.core/seq? input__55632_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__55632_nth_0__)
                                2)
                               (clojure.core/let
                                [input__55632_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__55632_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__55632_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__55632_nth_0__]
                                  (clojure.core/let
                                   [?env input__55632_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9229__auto__
                                        (CATA__FN__55682
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9229__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9229__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__10169__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10169__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10169__auto__))))))))
                               (state__56686))
                              (state__56686)))
                            (state__56686))
                           (state__56686)))))
                       (state__56686)))))
                   (state__56686))))
                (state__56686)))
              (state__56686)))
            (state__56686))
           (state__56686))))
        (state__56686
         []
         (clojure.core/letfn
          [(def__56374
            [arg__56397 ?__55640]
            (clojure.core/letfn
             [(state__56831
               []
               (clojure.core/let
                [x__56398 "meander.zeta"]
                (if
                 (clojure.core/= ?__55640 x__56398)
                 [?__55640]
                 (state__56832))))
              (state__56832
               []
               (if
                (clojure.core/map? arg__56397)
                (clojure.core/let
                 [VAL__56399 (.valAt arg__56397 :aliases)]
                 (if
                  (clojure.core/map? VAL__56399)
                  (clojure.core/let
                   [X__56401 (clojure.core/set VAL__56399)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__56401))
                    (clojure.core/loop
                     [search_space__56833 (clojure.core/seq X__56401)]
                     (if
                      (clojure.core/seq search_space__56833)
                      (clojure.core/let
                       [elem__56402
                        (clojure.core/first search_space__56833)
                        result__56834
                        (clojure.core/let
                         [elem__56402_nth_0__
                          (clojure.core/nth elem__56402 0)
                          elem__56402_nth_1__
                          (clojure.core/nth elem__56402 1)]
                         (if
                          (clojure.core/symbol? elem__56402_nth_0__)
                          (clojure.core/let
                           [X__56404
                            (clojure.core/name elem__56402_nth_0__)]
                           (if
                            (clojure.core/= ?__55640 X__56404)
                            (if
                             (clojure.core/symbol? elem__56402_nth_1__)
                             (clojure.core/let
                              [X__56406
                               (clojure.core/name elem__56402_nth_1__)]
                              (clojure.core/case
                               X__56406
                               ("meander.zeta")
                               [?__55640]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__56834)
                        (recur (clojure.core/next search_space__56833))
                        result__56834))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__56831)))]
          (if
           (clojure.core/vector? input__55632)
           (if
            (clojure.core/= (clojure.core/count input__55632) 2)
            (clojure.core/let
             [input__55632_nth_0__
              (clojure.core/nth input__55632 0)
              input__55632_nth_1__
              (clojure.core/nth input__55632 1)]
             (if
              (clojure.core/seq? input__55632_nth_0__)
              (clojure.core/let
               [input__55632_nth_0___l__
                (clojure.core/take 1 input__55632_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__55632_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__55632_nth_0___r__
                  (clojure.core/drop 1 input__55632_nth_0__)]
                 (clojure.core/let
                  [input__55632_nth_0___l___nth_0__
                   (clojure.core/nth input__55632_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__55632_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__56384
                     (clojure.core/namespace
                      input__55632_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__55640 X__56384]
                     (clojure.core/let
                      [X__56386
                       (clojure.core/name
                        input__55632_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__56386
                       ("or")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__56374 input__55632_nth_1__ ?__55640)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__56687)
                         (clojure.core/let
                          [[?__55640] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__55632)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__55632)
                             2)
                            (clojure.core/let
                             [input__55632_nth_0__
                              (clojure.core/nth input__55632 0)
                              input__55632_nth_1__
                              (clojure.core/nth input__55632 1)]
                             (if
                              (clojure.core/seq? input__55632_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__55632_nth_0__)
                                3)
                               (clojure.core/let
                                [input__55632_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__55632_nth_0__
                                  1)
                                 input__55632_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__55632_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__55632_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right input__55632_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__55632_nth_0__]
                                   (clojure.core/let
                                    [?env input__55632_nth_1__]
                                    (try
                                     [{:tag :or,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__55682
                                          [?left ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9229__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9229__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__55682
                                          [?right ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9229__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9229__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10169__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10169__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10169__auto__)))))))))
                               (state__56687))
                              (state__56687)))
                            (state__56687))
                           (state__56687)))))
                       (state__56687)))))
                   (state__56687))))
                (state__56687)))
              (state__56687)))
            (state__56687))
           (state__56687))))
        (state__56687
         []
         (clojure.core/letfn
          [(def__56408
            [arg__56431 ?__55641]
            (clojure.core/letfn
             [(state__56836
               []
               (clojure.core/let
                [x__56432 "meander.zeta"]
                (if
                 (clojure.core/= ?__55641 x__56432)
                 [?__55641]
                 (state__56837))))
              (state__56837
               []
               (if
                (clojure.core/map? arg__56431)
                (clojure.core/let
                 [VAL__56433 (.valAt arg__56431 :aliases)]
                 (if
                  (clojure.core/map? VAL__56433)
                  (clojure.core/let
                   [X__56435 (clojure.core/set VAL__56433)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__56435))
                    (clojure.core/loop
                     [search_space__56838 (clojure.core/seq X__56435)]
                     (if
                      (clojure.core/seq search_space__56838)
                      (clojure.core/let
                       [elem__56436
                        (clojure.core/first search_space__56838)
                        result__56839
                        (clojure.core/let
                         [elem__56436_nth_0__
                          (clojure.core/nth elem__56436 0)
                          elem__56436_nth_1__
                          (clojure.core/nth elem__56436 1)]
                         (if
                          (clojure.core/symbol? elem__56436_nth_0__)
                          (clojure.core/let
                           [X__56438
                            (clojure.core/name elem__56436_nth_0__)]
                           (if
                            (clojure.core/= ?__55641 X__56438)
                            (if
                             (clojure.core/symbol? elem__56436_nth_1__)
                             (clojure.core/let
                              [X__56440
                               (clojure.core/name elem__56436_nth_1__)]
                              (clojure.core/case
                               X__56440
                               ("meander.zeta")
                               [?__55641]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__56839)
                        (recur (clojure.core/next search_space__56838))
                        result__56839))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__56836)))]
          (if
           (clojure.core/vector? input__55632)
           (if
            (clojure.core/= (clojure.core/count input__55632) 2)
            (clojure.core/let
             [input__55632_nth_0__
              (clojure.core/nth input__55632 0)
              input__55632_nth_1__
              (clojure.core/nth input__55632 1)]
             (if
              (clojure.core/seq? input__55632_nth_0__)
              (clojure.core/let
               [input__55632_nth_0___l__
                (clojure.core/take 1 input__55632_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__55632_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__55632_nth_0___r__
                  (clojure.core/drop 1 input__55632_nth_0__)]
                 (clojure.core/let
                  [input__55632_nth_0___l___nth_0__
                   (clojure.core/nth input__55632_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__55632_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__56418
                     (clojure.core/namespace
                      input__55632_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__55641 X__56418]
                     (clojure.core/let
                      [X__56420
                       (clojure.core/name
                        input__55632_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__56420
                       ("re")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__56408 input__55632_nth_1__ ?__55641)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__56688)
                         (clojure.core/let
                          [[?__55641] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__55632)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__55632)
                             2)
                            (clojure.core/let
                             [input__55632_nth_0__
                              (clojure.core/nth input__55632 0)
                              input__55632_nth_1__
                              (clojure.core/nth input__55632 1)]
                             (if
                              (clojure.core/seq? input__55632_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__55632_nth_0__)
                                2)
                               (clojure.core/let
                                [input__55632_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__55632_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?regex input__55632_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__55632_nth_0__]
                                  (clojure.core/let
                                   [?env input__55632_nth_1__]
                                   (try
                                    [{:tag :regex,
                                      :regex ?regex,
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__10169__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10169__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10169__auto__))))))))
                               (state__56688))
                              (state__56688)))
                            (state__56688))
                           (state__56688)))))
                       (state__56688)))))
                   (state__56688))))
                (state__56688)))
              (state__56688)))
            (state__56688))
           (state__56688))))
        (state__56688
         []
         (clojure.core/letfn
          [(def__56442
            [arg__56465 ?__55642]
            (clojure.core/letfn
             [(state__56841
               []
               (clojure.core/let
                [x__56466 "meander.zeta"]
                (if
                 (clojure.core/= ?__55642 x__56466)
                 [?__55642]
                 (state__56842))))
              (state__56842
               []
               (if
                (clojure.core/map? arg__56465)
                (clojure.core/let
                 [VAL__56467 (.valAt arg__56465 :aliases)]
                 (if
                  (clojure.core/map? VAL__56467)
                  (clojure.core/let
                   [X__56469 (clojure.core/set VAL__56467)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__56469))
                    (clojure.core/loop
                     [search_space__56843 (clojure.core/seq X__56469)]
                     (if
                      (clojure.core/seq search_space__56843)
                      (clojure.core/let
                       [elem__56470
                        (clojure.core/first search_space__56843)
                        result__56844
                        (clojure.core/let
                         [elem__56470_nth_0__
                          (clojure.core/nth elem__56470 0)
                          elem__56470_nth_1__
                          (clojure.core/nth elem__56470 1)]
                         (if
                          (clojure.core/symbol? elem__56470_nth_0__)
                          (clojure.core/let
                           [X__56472
                            (clojure.core/name elem__56470_nth_0__)]
                           (if
                            (clojure.core/= ?__55642 X__56472)
                            (if
                             (clojure.core/symbol? elem__56470_nth_1__)
                             (clojure.core/let
                              [X__56474
                               (clojure.core/name elem__56470_nth_1__)]
                              (clojure.core/case
                               X__56474
                               ("meander.zeta")
                               [?__55642]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__56844)
                        (recur (clojure.core/next search_space__56843))
                        result__56844))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__56841)))]
          (if
           (clojure.core/vector? input__55632)
           (if
            (clojure.core/= (clojure.core/count input__55632) 2)
            (clojure.core/let
             [input__55632_nth_0__
              (clojure.core/nth input__55632 0)
              input__55632_nth_1__
              (clojure.core/nth input__55632 1)]
             (if
              (clojure.core/seq? input__55632_nth_0__)
              (clojure.core/let
               [input__55632_nth_0___l__
                (clojure.core/take 1 input__55632_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__55632_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__55632_nth_0___r__
                  (clojure.core/drop 1 input__55632_nth_0__)]
                 (clojure.core/let
                  [input__55632_nth_0___l___nth_0__
                   (clojure.core/nth input__55632_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__55632_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__56452
                     (clojure.core/namespace
                      input__55632_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__55642 X__56452]
                     (clojure.core/let
                      [X__56454
                       (clojure.core/name
                        input__55632_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__56454
                       ("re")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__56442 input__55632_nth_1__ ?__55642)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__56689)
                         (clojure.core/let
                          [[?__55642] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__55632)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__55632)
                             2)
                            (clojure.core/let
                             [input__55632_nth_0__
                              (clojure.core/nth input__55632 0)
                              input__55632_nth_1__
                              (clojure.core/nth input__55632 1)]
                             (if
                              (clojure.core/seq? input__55632_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__55632_nth_0__)
                                3)
                               (clojure.core/let
                                [input__55632_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__55632_nth_0__
                                  1)
                                 input__55632_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__55632_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?regex input__55632_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?capture
                                   input__55632_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__55632_nth_0__]
                                   (clojure.core/let
                                    [?env input__55632_nth_1__]
                                    (try
                                     [{:tag :regex,
                                       :regex ?regex,
                                       :capture
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__55682
                                          [?capture ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9229__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9229__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10169__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10169__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10169__auto__)))))))))
                               (state__56689))
                              (state__56689)))
                            (state__56689))
                           (state__56689)))))
                       (state__56689)))))
                   (state__56689))))
                (state__56689)))
              (state__56689)))
            (state__56689))
           (state__56689))))
        (state__56689
         []
         (clojure.core/letfn
          [(def__56476
            [arg__56499 ?__55643]
            (clojure.core/letfn
             [(state__56846
               []
               (clojure.core/let
                [x__56500 "meander.zeta"]
                (if
                 (clojure.core/= ?__55643 x__56500)
                 [?__55643]
                 (state__56847))))
              (state__56847
               []
               (if
                (clojure.core/map? arg__56499)
                (clojure.core/let
                 [VAL__56501 (.valAt arg__56499 :aliases)]
                 (if
                  (clojure.core/map? VAL__56501)
                  (clojure.core/let
                   [X__56503 (clojure.core/set VAL__56501)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__56503))
                    (clojure.core/loop
                     [search_space__56848 (clojure.core/seq X__56503)]
                     (if
                      (clojure.core/seq search_space__56848)
                      (clojure.core/let
                       [elem__56504
                        (clojure.core/first search_space__56848)
                        result__56849
                        (clojure.core/let
                         [elem__56504_nth_0__
                          (clojure.core/nth elem__56504 0)
                          elem__56504_nth_1__
                          (clojure.core/nth elem__56504 1)]
                         (if
                          (clojure.core/symbol? elem__56504_nth_0__)
                          (clojure.core/let
                           [X__56506
                            (clojure.core/name elem__56504_nth_0__)]
                           (if
                            (clojure.core/= ?__55643 X__56506)
                            (if
                             (clojure.core/symbol? elem__56504_nth_1__)
                             (clojure.core/let
                              [X__56508
                               (clojure.core/name elem__56504_nth_1__)]
                              (clojure.core/case
                               X__56508
                               ("meander.zeta")
                               [?__55643]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__56849)
                        (recur (clojure.core/next search_space__56848))
                        result__56849))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__56846)))]
          (if
           (clojure.core/vector? input__55632)
           (if
            (clojure.core/= (clojure.core/count input__55632) 2)
            (clojure.core/let
             [input__55632_nth_0__
              (clojure.core/nth input__55632 0)
              input__55632_nth_1__
              (clojure.core/nth input__55632 1)]
             (if
              (clojure.core/seq? input__55632_nth_0__)
              (clojure.core/let
               [input__55632_nth_0___l__
                (clojure.core/take 1 input__55632_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__55632_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__55632_nth_0___r__
                  (clojure.core/drop 1 input__55632_nth_0__)]
                 (clojure.core/let
                  [input__55632_nth_0___l___nth_0__
                   (clojure.core/nth input__55632_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__55632_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__56486
                     (clojure.core/namespace
                      input__55632_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__55643 X__56486]
                     (clojure.core/let
                      [X__56488
                       (clojure.core/name
                        input__55632_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__56488
                       ("string")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__56476 input__55632_nth_1__ ?__55643)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__56690)
                         (clojure.core/let
                          [[?__55643] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__55632)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__55632)
                             2)
                            (clojure.core/let
                             [input__55632_nth_0__
                              (clojure.core/nth input__55632 0)
                              input__55632_nth_1__
                              (clojure.core/nth input__55632 1)]
                             (if
                              (clojure.core/seq? input__55632_nth_0__)
                              (clojure.core/let
                               [input__55632_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__55632_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__55632_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__55632_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__55632_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__55632_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__55632_nth_0__]
                                   (clojure.core/let
                                    [?env input__55632_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__55682
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?sequence)
                                           (clojure.core/let
                                            [form__9328__auto__
                                             {:context :string}]
                                            (clojure.core/merge
                                             (clojure.core/into
                                              {}
                                              ?env)
                                             form__9328__auto__))])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9229__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9229__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10169__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10169__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10169__auto__))))))))
                                (state__56690)))
                              (state__56690)))
                            (state__56690))
                           (state__56690)))))
                       (state__56690)))))
                   (state__56690))))
                (state__56690)))
              (state__56690)))
            (state__56690))
           (state__56690))))
        (state__56690
         []
         (clojure.core/letfn
          [(def__56510
            [arg__56533 ?__55644]
            (clojure.core/letfn
             [(state__56851
               []
               (clojure.core/let
                [x__56534 "meander.zeta"]
                (if
                 (clojure.core/= ?__55644 x__56534)
                 [?__55644]
                 (state__56852))))
              (state__56852
               []
               (if
                (clojure.core/map? arg__56533)
                (clojure.core/let
                 [VAL__56535 (.valAt arg__56533 :aliases)]
                 (if
                  (clojure.core/map? VAL__56535)
                  (clojure.core/let
                   [X__56537 (clojure.core/set VAL__56535)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__56537))
                    (clojure.core/loop
                     [search_space__56853 (clojure.core/seq X__56537)]
                     (if
                      (clojure.core/seq search_space__56853)
                      (clojure.core/let
                       [elem__56538
                        (clojure.core/first search_space__56853)
                        result__56854
                        (clojure.core/let
                         [elem__56538_nth_0__
                          (clojure.core/nth elem__56538 0)
                          elem__56538_nth_1__
                          (clojure.core/nth elem__56538 1)]
                         (if
                          (clojure.core/symbol? elem__56538_nth_0__)
                          (clojure.core/let
                           [X__56540
                            (clojure.core/name elem__56538_nth_0__)]
                           (if
                            (clojure.core/= ?__55644 X__56540)
                            (if
                             (clojure.core/symbol? elem__56538_nth_1__)
                             (clojure.core/let
                              [X__56542
                               (clojure.core/name elem__56538_nth_1__)]
                              (clojure.core/case
                               X__56542
                               ("meander.zeta")
                               [?__55644]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__56854)
                        (recur (clojure.core/next search_space__56853))
                        result__56854))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__56851)))]
          (if
           (clojure.core/vector? input__55632)
           (if
            (clojure.core/= (clojure.core/count input__55632) 2)
            (clojure.core/let
             [input__55632_nth_0__
              (clojure.core/nth input__55632 0)
              input__55632_nth_1__
              (clojure.core/nth input__55632 1)]
             (if
              (clojure.core/seq? input__55632_nth_0__)
              (clojure.core/let
               [input__55632_nth_0___l__
                (clojure.core/take 1 input__55632_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__55632_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__55632_nth_0___r__
                  (clojure.core/drop 1 input__55632_nth_0__)]
                 (clojure.core/let
                  [input__55632_nth_0___l___nth_0__
                   (clojure.core/nth input__55632_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__55632_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__56520
                     (clojure.core/namespace
                      input__55632_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__55644 X__56520]
                     (clojure.core/let
                      [X__56522
                       (clojure.core/name
                        input__55632_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__56522
                       ("symbol")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__56510 input__55632_nth_1__ ?__55644)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__56691)
                         (clojure.core/let
                          [[?__55644] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__55632)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__55632)
                             2)
                            (clojure.core/let
                             [input__55632_nth_0__
                              (clojure.core/nth input__55632 0)
                              input__55632_nth_1__
                              (clojure.core/nth input__55632 1)]
                             (if
                              (clojure.core/seq? input__55632_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__55632_nth_0__)
                                2)
                               (clojure.core/let
                                [input__55632_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__55632_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?name input__55632_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__55632_nth_0__]
                                  (clojure.core/let
                                   [?env input__55632_nth_1__]
                                   (try
                                    [{:tag :symbol,
                                      :name
                                      (clojure.core/let
                                       [CATA_RESULT__9229__auto__
                                        (CATA__FN__55682 [?name ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9229__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9229__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__10169__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10169__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10169__auto__))))))))
                               (state__56691))
                              (state__56691)))
                            (state__56691))
                           (state__56691)))))
                       (state__56691)))))
                   (state__56691))))
                (state__56691)))
              (state__56691)))
            (state__56691))
           (state__56691))))
        (state__56691
         []
         (clojure.core/letfn
          [(def__56544
            [arg__56567 ?__55645]
            (clojure.core/letfn
             [(state__56856
               []
               (clojure.core/let
                [x__56568 "meander.zeta"]
                (if
                 (clojure.core/= ?__55645 x__56568)
                 [?__55645]
                 (state__56857))))
              (state__56857
               []
               (if
                (clojure.core/map? arg__56567)
                (clojure.core/let
                 [VAL__56569 (.valAt arg__56567 :aliases)]
                 (if
                  (clojure.core/map? VAL__56569)
                  (clojure.core/let
                   [X__56571 (clojure.core/set VAL__56569)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__56571))
                    (clojure.core/loop
                     [search_space__56858 (clojure.core/seq X__56571)]
                     (if
                      (clojure.core/seq search_space__56858)
                      (clojure.core/let
                       [elem__56572
                        (clojure.core/first search_space__56858)
                        result__56859
                        (clojure.core/let
                         [elem__56572_nth_0__
                          (clojure.core/nth elem__56572 0)
                          elem__56572_nth_1__
                          (clojure.core/nth elem__56572 1)]
                         (if
                          (clojure.core/symbol? elem__56572_nth_0__)
                          (clojure.core/let
                           [X__56574
                            (clojure.core/name elem__56572_nth_0__)]
                           (if
                            (clojure.core/= ?__55645 X__56574)
                            (if
                             (clojure.core/symbol? elem__56572_nth_1__)
                             (clojure.core/let
                              [X__56576
                               (clojure.core/name elem__56572_nth_1__)]
                              (clojure.core/case
                               X__56576
                               ("meander.zeta")
                               [?__55645]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__56859)
                        (recur (clojure.core/next search_space__56858))
                        result__56859))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__56856)))]
          (if
           (clojure.core/vector? input__55632)
           (if
            (clojure.core/= (clojure.core/count input__55632) 2)
            (clojure.core/let
             [input__55632_nth_0__
              (clojure.core/nth input__55632 0)
              input__55632_nth_1__
              (clojure.core/nth input__55632 1)]
             (if
              (clojure.core/seq? input__55632_nth_0__)
              (clojure.core/let
               [input__55632_nth_0___l__
                (clojure.core/take 1 input__55632_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__55632_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__55632_nth_0___r__
                  (clojure.core/drop 1 input__55632_nth_0__)]
                 (clojure.core/let
                  [input__55632_nth_0___l___nth_0__
                   (clojure.core/nth input__55632_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__55632_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__56554
                     (clojure.core/namespace
                      input__55632_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__55645 X__56554]
                     (clojure.core/let
                      [X__56556
                       (clojure.core/name
                        input__55632_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__56556
                       ("symbol")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__56544 input__55632_nth_1__ ?__55645)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__56692)
                         (clojure.core/let
                          [[?__55645] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__55632)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__55632)
                             2)
                            (clojure.core/let
                             [input__55632_nth_0__
                              (clojure.core/nth input__55632 0)
                              input__55632_nth_1__
                              (clojure.core/nth input__55632 1)]
                             (if
                              (clojure.core/seq? input__55632_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__55632_nth_0__)
                                3)
                               (clojure.core/let
                                [input__55632_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__55632_nth_0__
                                  1)
                                 input__55632_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__55632_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?namespace
                                  input__55632_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?name input__55632_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__55632_nth_0__]
                                   (clojure.core/let
                                    [?env input__55632_nth_1__]
                                    (try
                                     [{:tag :symbol,
                                       :name
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__55682
                                          [?name ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9229__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9229__auto__
                                          0))),
                                       :namespace
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__55682
                                          [?namespace ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9229__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9229__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10169__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10169__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10169__auto__)))))))))
                               (state__56692))
                              (state__56692)))
                            (state__56692))
                           (state__56692)))))
                       (state__56692)))))
                   (state__56692))))
                (state__56692)))
              (state__56692)))
            (state__56692))
           (state__56692))))
        (state__56692
         []
         (clojure.core/letfn
          [(def__56578
            [arg__56601 ?__55646]
            (clojure.core/letfn
             [(state__56861
               []
               (clojure.core/let
                [x__56602 "meander.zeta"]
                (if
                 (clojure.core/= ?__55646 x__56602)
                 [?__55646]
                 (state__56862))))
              (state__56862
               []
               (if
                (clojure.core/map? arg__56601)
                (clojure.core/let
                 [VAL__56603 (.valAt arg__56601 :aliases)]
                 (if
                  (clojure.core/map? VAL__56603)
                  (clojure.core/let
                   [X__56605 (clojure.core/set VAL__56603)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__56605))
                    (clojure.core/loop
                     [search_space__56863 (clojure.core/seq X__56605)]
                     (if
                      (clojure.core/seq search_space__56863)
                      (clojure.core/let
                       [elem__56606
                        (clojure.core/first search_space__56863)
                        result__56864
                        (clojure.core/let
                         [elem__56606_nth_0__
                          (clojure.core/nth elem__56606 0)
                          elem__56606_nth_1__
                          (clojure.core/nth elem__56606 1)]
                         (if
                          (clojure.core/symbol? elem__56606_nth_0__)
                          (clojure.core/let
                           [X__56608
                            (clojure.core/name elem__56606_nth_0__)]
                           (if
                            (clojure.core/= ?__55646 X__56608)
                            (if
                             (clojure.core/symbol? elem__56606_nth_1__)
                             (clojure.core/let
                              [X__56610
                               (clojure.core/name elem__56606_nth_1__)]
                              (clojure.core/case
                               X__56610
                               ("meander.zeta")
                               [?__55646]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__56864)
                        (recur (clojure.core/next search_space__56863))
                        result__56864))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__56861)))]
          (if
           (clojure.core/vector? input__55632)
           (if
            (clojure.core/= (clojure.core/count input__55632) 2)
            (clojure.core/let
             [input__55632_nth_0__
              (clojure.core/nth input__55632 0)
              input__55632_nth_1__
              (clojure.core/nth input__55632 1)]
             (if
              (clojure.core/seq? input__55632_nth_0__)
              (clojure.core/let
               [input__55632_nth_0___l__
                (clojure.core/take 1 input__55632_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__55632_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__55632_nth_0___r__
                  (clojure.core/drop 1 input__55632_nth_0__)]
                 (clojure.core/let
                  [input__55632_nth_0___l___nth_0__
                   (clojure.core/nth input__55632_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__55632_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__56588
                     (clojure.core/namespace
                      input__55632_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__55646 X__56588]
                     (clojure.core/let
                      [X__56590
                       (clojure.core/name
                        input__55632_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__56590
                       ("symbol")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__56578 input__55632_nth_1__ ?__55646)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__56693)
                         (clojure.core/let
                          [[?__55646] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__55632)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__55632)
                             2)
                            (clojure.core/let
                             [input__55632_nth_0__
                              (clojure.core/nth input__55632 0)
                              input__55632_nth_1__
                              (clojure.core/nth input__55632 1)]
                             (if
                              (clojure.core/seq? input__55632_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 5)
                                 input__55632_nth_0__)
                                5)
                               (clojure.core/let
                                [input__55632_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__55632_nth_0__
                                  1)
                                 input__55632_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__55632_nth_0__
                                  2)
                                 input__55632_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__55632_nth_0__
                                  3)
                                 input__55632_nth_0___nth_4__
                                 (clojure.core/nth
                                  input__55632_nth_0__
                                  4)]
                                (clojure.core/case
                                 input__55632_nth_0___nth_3__
                                 (:meander.zeta/as)
                                 (clojure.core/let
                                  [?namespace
                                   input__55632_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?name input__55632_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?pattern
                                     input__55632_nth_0___nth_4__]
                                    (clojure.core/let
                                     [?form input__55632_nth_0__]
                                     (clojure.core/let
                                      [?env input__55632_nth_1__]
                                      (try
                                       [{:tag :symbol,
                                         :name
                                         (clojure.core/let
                                          [CATA_RESULT__9229__auto__
                                           (CATA__FN__55682
                                            [?name ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__9229__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__9229__auto__
                                            0))),
                                         :namespace
                                         (clojure.core/let
                                          [CATA_RESULT__9229__auto__
                                           (CATA__FN__55682
                                            [?namespace ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__9229__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__9229__auto__
                                            0))),
                                         :as-pattern
                                         (clojure.core/let
                                          [CATA_RESULT__9229__auto__
                                           (CATA__FN__55682
                                            [?pattern ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__9229__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__9229__auto__
                                            0))),
                                         :form ?form}]
                                       (catch
                                        java.lang.Exception
                                        e__10169__auto__
                                        (if
                                         (meander.runtime.zeta/fail?
                                          e__10169__auto__)
                                         (meander.runtime.zeta/fail)
                                         (throw
                                          e__10169__auto__)))))))))
                                 (state__56693)))
                               (state__56693))
                              (state__56693)))
                            (state__56693))
                           (state__56693)))))
                       (state__56693)))))
                   (state__56693))))
                (state__56693)))
              (state__56693)))
            (state__56693))
           (state__56693))))
        (state__56693
         []
         (if
          (clojure.core/vector? input__55632)
          (if
           (clojure.core/= (clojure.core/count input__55632) 2)
           (clojure.core/let
            [input__55632_nth_0__ (clojure.core/nth input__55632 0)]
            (clojure.core/letfn
             [(state__56866
               []
               (clojure.core/let
                [input__55632_nth_1__
                 (clojure.core/nth input__55632 1)]
                (clojure.core/letfn
                 [(state__56871
                   []
                   (if
                    (clojure.core/seq? input__55632_nth_0__)
                    (clojure.core/let
                     [?sequence input__55632_nth_0__]
                     (clojure.core/let
                      [?env input__55632_nth_1__]
                      (try
                       [{:tag :seq,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__9229__auto__
                           (CATA__FN__55682
                            ['meander.dev.parse.zeta/parse-sequential
                             (clojure.core/into [] ?sequence)
                             ?env])]
                          (if
                           (meander.runtime.zeta/fail?
                            CATA_RESULT__9229__auto__)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth
                            CATA_RESULT__9229__auto__
                            0))),
                         :form ?sequence}]
                       (catch
                        java.lang.Exception
                        e__10169__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10169__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10169__auto__))))))
                    (state__56872)))
                  (state__56872
                   []
                   (if
                    (clojure.core/map? input__55632_nth_0__)
                    (clojure.core/let
                     [?map input__55632_nth_0__]
                     (clojure.core/let
                      [?env input__55632_nth_1__]
                      (try
                       [{:tag :map,
                         :next
                         (clojure.core/list 'parse-entries ?map ?env),
                         :form ?map}]
                       (catch
                        java.lang.Exception
                        e__10169__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10169__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10169__auto__))))))
                    (state__56867)))]
                 (state__56871))))
              (state__56867
               []
               (if
                (clojure.core/symbol? input__55632_nth_0__)
                (clojure.core/let
                 [X__56620
                  (clojure.core/namespace input__55632_nth_0__)]
                 (clojure.core/let
                  [X__56622 (clojure.core/name input__55632_nth_0__)]
                  (if
                   (clojure.core/string? X__56622)
                   (clojure.core/letfn
                    [(state__56873
                      []
                      (clojure.core/let
                       [ret__56623
                        (clojure.core/re-matches #"_.*" X__56622)]
                       (if
                        (clojure.core/some? ret__56623)
                        (clojure.core/let
                         [?name ret__56623]
                         (clojure.core/let
                          [?symbol input__55632_nth_0__]
                          (try
                           [{:tag :wildcard,
                             :name ?name,
                             :form ?symbol,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__10169__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__10169__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10169__auto__))))))
                        (state__56874))))
                     (state__56874
                      []
                      (clojure.core/let
                       [ret__56630
                        (clojure.core/re-matches #".+#" X__56622)]
                       (if
                        (clojure.core/some? ret__56630)
                        (clojure.core/let
                         [?name ret__56630]
                         (clojure.core/let
                          [?symbol input__55632_nth_0__]
                          (try
                           [{:tag :random-symbol,
                             :name ?name,
                             :form ?symbol,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__10169__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__10169__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10169__auto__))))))
                        (state__56875))))
                     (state__56875
                      []
                      (clojure.core/let
                       [ret__56637
                        (clojure.core/re-matches #"%.+" X__56622)]
                       (if
                        (clojure.core/some? ret__56637)
                        (clojure.core/let
                         [?name ret__56637]
                         (clojure.core/let
                          [?symbol input__55632_nth_0__]
                          (try
                           [{:tag :reference,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__10169__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__10169__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10169__auto__))))))
                        (state__56876))))
                     (state__56876
                      []
                      (clojure.core/let
                       [ret__56644
                        (clojure.core/re-matches #"\*.+" X__56622)]
                       (if
                        (clojure.core/some? ret__56644)
                        (clojure.core/let
                         [?name ret__56644]
                         (clojure.core/let
                          [?symbol input__55632_nth_0__]
                          (try
                           [{:tag :mutable-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__10169__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__10169__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10169__auto__))))))
                        (state__56877))))
                     (state__56877
                      []
                      (clojure.core/let
                       [ret__56651
                        (clojure.core/re-matches #"\!.+" X__56622)]
                       (if
                        (clojure.core/some? ret__56651)
                        (clojure.core/let
                         [?name ret__56651]
                         (clojure.core/let
                          [?symbol input__55632_nth_0__]
                          (try
                           [{:tag :memory-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__10169__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__10169__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10169__auto__))))))
                        (state__56878))))
                     (state__56878
                      []
                      (clojure.core/let
                       [ret__56658
                        (clojure.core/re-matches #"\?.+" X__56622)]
                       (if
                        (clojure.core/some? ret__56658)
                        (clojure.core/let
                         [?name ret__56658]
                         (clojure.core/let
                          [?symbol input__55632_nth_0__]
                          (try
                           [{:tag :logic-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__10169__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__10169__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10169__auto__))))))
                        (state__56868))))]
                    (state__56873))
                   (state__56868))))
                (state__56868)))
              (state__56868
               []
               (if
                (string? input__55632_nth_0__)
                (clojure.core/let
                 [?x input__55632_nth_0__]
                 (try
                  [{:tag :literal, :type :string, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__10169__auto__
                   (if
                    (meander.runtime.zeta/fail? e__10169__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__10169__auto__)))))
                (state__56869)))
              (state__56869
               []
               (if
                (char? input__55632_nth_0__)
                (clojure.core/let
                 [?x input__55632_nth_0__]
                 (try
                  [{:tag :literal, :type :char, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__10169__auto__
                   (if
                    (meander.runtime.zeta/fail? e__10169__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__10169__auto__)))))
                (state__56870)))
              (state__56870
               []
               (clojure.core/let
                [?x input__55632_nth_0__]
                (try
                 [{:tag :literal, :form ?x}]
                 (catch
                  java.lang.Exception
                  e__10169__auto__
                  (if
                   (meander.runtime.zeta/fail? e__10169__auto__)
                   (meander.runtime.zeta/fail)
                   (throw e__10169__auto__))))))]
             (state__56866)))
           (state__56694))
          (state__56694)))
        (state__56694
         []
         (clojure.core/let
          [?x input__55632]
          (try
           [{:tag :mistake, :x ?x}]
           (catch
            java.lang.Exception
            e__10169__auto__
            (if
             (meander.runtime.zeta/fail? e__10169__auto__)
             (meander.runtime.zeta/fail)
             (throw e__10169__auto__))))))]
       (state__56670)))]
    (clojure.core/let
     [x__7926__auto__ (CATA__FN__55682 input__55632)]
     (if
      (meander.runtime.zeta/fail? x__7926__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__55684] x__7926__auto__]
       CATA_RETURN__55684))))]
  (if
   (meander.runtime.zeta/fail? ret__8106__auto__)
   nil
   ret__8106__auto__)))
