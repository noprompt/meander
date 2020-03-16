(ns meander.compiled.parse.zeta (:require [meander.runtime.zeta]))
(clojure.core/defn
 parse
 [input__19495]
 (let*
  [ret__8123__auto__
   (clojure.core/letfn
    [(CATA__FN__19554
      [input__19495]
      (clojure.core/letfn
       [(state__20666
         []
         (if
          (clojure.core/vector? input__19495)
          (if
           (clojure.core/= (clojure.core/count input__19495) 3)
           (clojure.core/let
            [input__19495_nth_0__
             (clojure.core/nth input__19495 0)
             input__19495_nth_1__
             (clojure.core/nth input__19495 1)
             input__19495_nth_2__
             (clojure.core/nth input__19495 2)]
            (clojure.core/case
             input__19495_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__19495_nth_1__)
              (clojure.core/letfn
               [(state__20696
                 []
                 (clojure.core/case
                  input__19495_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__19495_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__10186__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10186__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10186__auto__)))))
                  (state__20697)))
                (state__20697
                 []
                 (clojure.core/let
                  [n__19561
                   (clojure.core/count input__19495_nth_1__)
                   m__19562
                   (clojure.core/max 0 (clojure.core/- n__19561 2))
                   input__19495_nth_1___l__
                   (clojure.core/subvec
                    input__19495_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__19495_nth_1__)
                     m__19562))
                   input__19495_nth_1___r__
                   (clojure.core/subvec input__19495_nth_1__ m__19562)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__19495_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__19495_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__19495_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__19495_nth_1___r___nth_0__
                       (clojure.core/nth input__19495_nth_1___r__ 0)
                       input__19495_nth_1___r___nth_1__
                       (clojure.core/nth input__19495_nth_1___r__ 1)]
                      (clojure.core/case
                       input__19495_nth_1___r___nth_0__
                       (:meander.zeta/as)
                       (clojure.core/let
                        [?pattern input__19495_nth_1___r___nth_1__]
                        (clojure.core/let
                         [?env input__19495_nth_2__]
                         (try
                          [(clojure.core/let
                            [!xs__counter
                             (meander.runtime.zeta/iterator !xs)]
                            {:tag :as,
                             :pattern
                             (clojure.core/let
                              [CATA_RESULT__9246__auto__
                               (CATA__FN__19554 [?pattern ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__9246__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__9246__auto__
                                0))),
                             :next
                             (clojure.core/let
                              [CATA_RESULT__9246__auto__
                               (CATA__FN__19554
                                ['meander.dev.parse.zeta/parse-sequential
                                 (clojure.core/into
                                  []
                                  (clojure.core/vec
                                   (clojure.core/iterator-seq
                                    !xs__counter)))
                                 ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__9246__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__9246__auto__
                                0)))})]
                          (catch
                           java.lang.Exception
                           e__10186__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__10186__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__10186__auto__))))))
                       (state__20667)))
                     (state__20667)))
                   (state__20667))))]
               (state__20696))
              (state__20667))
             (state__20667)))
           (state__20667))
          (state__20667)))
        (state__20667
         []
         (clojure.core/letfn
          [(def__19567
            [arg__19602 ?ns]
            (clojure.core/letfn
             [(state__20698
               []
               (clojure.core/let
                [x__19603 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__19603)
                 (clojure.core/let [?env arg__19602] [?env ?ns])
                 (state__20699))))
              (state__20699
               []
               (if
                (clojure.core/map? arg__19602)
                (clojure.core/let
                 [VAL__19604 (.valAt arg__19602 :aliases)]
                 (if
                  (clojure.core/map? VAL__19604)
                  (clojure.core/let
                   [X__19606 (clojure.core/set VAL__19604)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__19606))
                    (clojure.core/loop
                     [search_space__20700 (clojure.core/seq X__19606)]
                     (if
                      (clojure.core/seq search_space__20700)
                      (clojure.core/let
                       [elem__19607
                        (clojure.core/first search_space__20700)
                        result__20701
                        (clojure.core/let
                         [elem__19607_nth_0__
                          (clojure.core/nth elem__19607 0)
                          elem__19607_nth_1__
                          (clojure.core/nth elem__19607 1)]
                         (if
                          (clojure.core/symbol? elem__19607_nth_0__)
                          (clojure.core/let
                           [X__19609
                            (clojure.core/name elem__19607_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__19609)
                            (if
                             (clojure.core/symbol? elem__19607_nth_1__)
                             (clojure.core/let
                              [X__19611
                               (clojure.core/name elem__19607_nth_1__)]
                              (clojure.core/case
                               X__19611
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__19602]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__20701)
                        (recur (clojure.core/next search_space__20700))
                        result__20701))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__20698)))]
          (if
           (clojure.core/vector? input__19495)
           (if
            (clojure.core/= (clojure.core/count input__19495) 3)
            (clojure.core/let
             [input__19495_nth_0__
              (clojure.core/nth input__19495 0)
              input__19495_nth_1__
              (clojure.core/nth input__19495 1)
              input__19495_nth_2__
              (clojure.core/nth input__19495 2)]
             (clojure.core/case
              input__19495_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__19495_nth_1__)
               (clojure.core/loop
                [search_space__20703
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__19495_nth_1__)]
                (if
                 (clojure.core/seq search_space__20703)
                 (clojure.core/let
                  [input__19495_nth_1___parts__
                   (clojure.core/first search_space__20703)
                   result__20704
                   (clojure.core/let
                    [input__19495_nth_1___l__
                     (clojure.core/nth input__19495_nth_1___parts__ 0)
                     input__19495_nth_1___r__
                     (clojure.core/nth input__19495_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__19495_nth_1___l__)]
                     (clojure.core/let
                      [input__19495_nth_1___r___l__
                       (clojure.core/subvec
                        input__19495_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__19495_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__19495_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__19495_nth_1___r___r__
                         (clojure.core/subvec
                          input__19495_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__19495_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__19495_nth_1___r___l__
                           0)
                          input__19495_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__19495_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__19495_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__19576
                            (clojure.core/namespace
                             input__19495_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__19576]
                            (clojure.core/let
                             [X__19578
                              (clojure.core/name
                               input__19495_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__19578)
                              (clojure.core/let
                               [ret__19579
                                (clojure.core/re-matches
                                 #"&(\d+)"
                                 X__19578)]
                               (if
                                (clojure.core/some? ret__19579)
                                (if
                                 (clojure.core/vector? ret__19579)
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count ret__19579)
                                   2)
                                  (clojure.core/let
                                   [ret__19579_nth_1__
                                    (clojure.core/nth ret__19579 1)]
                                   (clojure.core/let
                                    [?n ret__19579_nth_1__]
                                    (clojure.core/let
                                     [?pattern
                                      input__19495_nth_1___r___l___nth_1__]
                                     (clojure.core/let
                                      [?rest
                                       input__19495_nth_1___r___r__]
                                      (clojure.core/let
                                       [x__7943__auto__
                                        (def__19567
                                         input__19495_nth_2__
                                         ?ns)]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         x__7943__auto__)
                                        (meander.runtime.zeta/fail)
                                        (clojure.core/let
                                         [[?env ?ns] x__7943__auto__]
                                         (try
                                          [(clojure.core/let
                                            [!init__counter
                                             (meander.runtime.zeta/iterator
                                              !init)]
                                            (clojure.core/let
                                             [CATA_RESULT__9246__auto__
                                              (CATA__FN__19554
                                               ['meander.dev.parse.zeta/make-join
                                                (clojure.core/let
                                                 [CATA_RESULT__9246__auto__
                                                  (CATA__FN__19554
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !init__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9246__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9246__auto__
                                                   0)))
                                                (clojure.core/let
                                                 [CATA_RESULT__9246__auto__
                                                  (CATA__FN__19554
                                                   ['meander.dev.parse.zeta/make-join
                                                    {:tag :slice,
                                                     :size
                                                     (Integer. ?n),
                                                     :pattern
                                                     (clojure.core/let
                                                      [CATA_RESULT__9246__auto__
                                                       (CATA__FN__19554
                                                        [?pattern
                                                         ?env])]
                                                      (if
                                                       (meander.runtime.zeta/fail?
                                                        CATA_RESULT__9246__auto__)
                                                       (throw
                                                        (meander.runtime.zeta/fail))
                                                       (clojure.core/nth
                                                        CATA_RESULT__9246__auto__
                                                        0)))}
                                                    (clojure.core/let
                                                     [CATA_RESULT__9246__auto__
                                                      (CATA__FN__19554
                                                       ['meander.dev.parse.zeta/parse-sequential
                                                        (clojure.core/into
                                                         []
                                                         ?rest)
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__9246__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__9246__auto__
                                                       0)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9246__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9246__auto__
                                                   0)))
                                                ?env])]
                                             (if
                                              (meander.runtime.zeta/fail?
                                               CATA_RESULT__9246__auto__)
                                              (throw
                                               (meander.runtime.zeta/fail))
                                              (clojure.core/nth
                                               CATA_RESULT__9246__auto__
                                               0))))]
                                          (catch
                                           java.lang.Exception
                                           e__10186__auto__
                                           (if
                                            (meander.runtime.zeta/fail?
                                             e__10186__auto__)
                                            (meander.runtime.zeta/fail)
                                            (throw
                                             e__10186__auto__)))))))))))
                                  (meander.runtime.zeta/fail))
                                 (meander.runtime.zeta/fail))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__20704)
                   (recur (clojure.core/next search_space__20703))
                   result__20704))
                 (state__20668)))
               (state__20668))
              (state__20668)))
            (state__20668))
           (state__20668))))
        (state__20668
         []
         (clojure.core/letfn
          [(def__19624
            [arg__19656 ?ns]
            (clojure.core/letfn
             [(state__20706
               []
               (clojure.core/let
                [x__19657 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__19657)
                 (clojure.core/let [?env arg__19656] [?env ?ns])
                 (state__20707))))
              (state__20707
               []
               (if
                (clojure.core/map? arg__19656)
                (clojure.core/let
                 [VAL__19658 (.valAt arg__19656 :aliases)]
                 (if
                  (clojure.core/map? VAL__19658)
                  (clojure.core/let
                   [X__19660 (clojure.core/set VAL__19658)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__19660))
                    (clojure.core/loop
                     [search_space__20708 (clojure.core/seq X__19660)]
                     (if
                      (clojure.core/seq search_space__20708)
                      (clojure.core/let
                       [elem__19661
                        (clojure.core/first search_space__20708)
                        result__20709
                        (clojure.core/let
                         [elem__19661_nth_0__
                          (clojure.core/nth elem__19661 0)
                          elem__19661_nth_1__
                          (clojure.core/nth elem__19661 1)]
                         (if
                          (clojure.core/symbol? elem__19661_nth_0__)
                          (clojure.core/let
                           [X__19663
                            (clojure.core/name elem__19661_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__19663)
                            (if
                             (clojure.core/symbol? elem__19661_nth_1__)
                             (clojure.core/let
                              [X__19665
                               (clojure.core/name elem__19661_nth_1__)]
                              (clojure.core/case
                               X__19665
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__19656]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__20709)
                        (recur (clojure.core/next search_space__20708))
                        result__20709))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__20706)))]
          (if
           (clojure.core/vector? input__19495)
           (if
            (clojure.core/= (clojure.core/count input__19495) 3)
            (clojure.core/let
             [input__19495_nth_0__
              (clojure.core/nth input__19495 0)
              input__19495_nth_1__
              (clojure.core/nth input__19495 1)
              input__19495_nth_2__
              (clojure.core/nth input__19495 2)]
             (clojure.core/case
              input__19495_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__19495_nth_1__)
               (clojure.core/loop
                [search_space__20711
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__19495_nth_1__)]
                (if
                 (clojure.core/seq search_space__20711)
                 (clojure.core/let
                  [input__19495_nth_1___parts__
                   (clojure.core/first search_space__20711)
                   result__20712
                   (clojure.core/let
                    [input__19495_nth_1___l__
                     (clojure.core/nth input__19495_nth_1___parts__ 0)
                     input__19495_nth_1___r__
                     (clojure.core/nth input__19495_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__19495_nth_1___l__)]
                     (clojure.core/let
                      [input__19495_nth_1___r___l__
                       (clojure.core/subvec
                        input__19495_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__19495_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__19495_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__19495_nth_1___r___r__
                         (clojure.core/subvec
                          input__19495_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__19495_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__19495_nth_1___r___l__
                           0)
                          input__19495_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__19495_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__19495_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__19633
                            (clojure.core/namespace
                             input__19495_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__19633]
                            (clojure.core/let
                             [X__19635
                              (clojure.core/name
                               input__19495_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__19635)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__19635)
                               (clojure.core/let
                                [?pattern
                                 input__19495_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__19495_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__7943__auto__
                                   (def__19624
                                    input__19495_nth_2__
                                    ?ns)]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    x__7943__auto__)
                                   (meander.runtime.zeta/fail)
                                   (clojure.core/let
                                    [[?env ?ns] x__7943__auto__]
                                    (try
                                     [(clojure.core/let
                                       [!init__counter
                                        (meander.runtime.zeta/iterator
                                         !init)]
                                       (clojure.core/let
                                        [CATA_RESULT__9246__auto__
                                         (CATA__FN__19554
                                          ['meander.dev.parse.zeta/make-join
                                           (clojure.core/let
                                            [CATA_RESULT__9246__auto__
                                             (CATA__FN__19554
                                              ['meander.dev.parse.zeta/parse-sequential
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !init__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9246__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9246__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__9246__auto__
                                             (CATA__FN__19554
                                              ['meander.dev.parse.zeta/make-join
                                               (clojure.core/let
                                                [CATA_RESULT__9246__auto__
                                                 (CATA__FN__19554
                                                  [?pattern ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__9246__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__9246__auto__
                                                  0)))
                                               (clojure.core/let
                                                [CATA_RESULT__9246__auto__
                                                 (CATA__FN__19554
                                                  ['meander.dev.parse.zeta/parse-sequential
                                                   (clojure.core/into
                                                    []
                                                    ?rest)
                                                   ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__9246__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__9246__auto__
                                                  0)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9246__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9246__auto__
                                              0)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9246__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9246__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__10186__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10186__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10186__auto__)))))))))
                               (meander.runtime.zeta/fail))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__20712)
                   (recur (clojure.core/next search_space__20711))
                   result__20712))
                 (state__20669)))
               (state__20669))
              (state__20669)))
            (state__20669))
           (state__20669))))
        (state__20669
         []
         (if
          (clojure.core/vector? input__19495)
          (clojure.core/letfn
           [(state__20714
             []
             (if
              (clojure.core/= (clojure.core/count input__19495) 3)
              (clojure.core/let
               [input__19495_nth_0__
                (clojure.core/nth input__19495 0)
                input__19495_nth_1__
                (clojure.core/nth input__19495 1)
                input__19495_nth_2__
                (clojure.core/nth input__19495 2)]
               (clojure.core/case
                input__19495_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__19495_nth_1__)
                 (clojure.core/letfn
                  [(state__20717
                    []
                    (clojure.core/let
                     [n__19686
                      (clojure.core/count input__19495_nth_1__)
                      m__19687
                      (clojure.core/max 0 (clojure.core/- n__19686 2))
                      input__19495_nth_1___l__
                      (clojure.core/subvec
                       input__19495_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__19495_nth_1__)
                        m__19687))
                      input__19495_nth_1___r__
                      (clojure.core/subvec
                       input__19495_nth_1__
                       m__19687)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__19495_nth_1___r__)
                       2)
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__19495_nth_1___l__)]
                       (if
                        (clojure.core/=
                         (clojure.core/count input__19495_nth_1___r__)
                         2)
                        (clojure.core/let
                         [input__19495_nth_1___r___nth_0__
                          (clojure.core/nth input__19495_nth_1___r__ 0)
                          input__19495_nth_1___r___nth_1__
                          (clojure.core/nth
                           input__19495_nth_1___r__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__19495_nth_1___r___nth_0__)
                          (clojure.core/let
                           [X__19691
                            (clojure.core/namespace
                             input__19495_nth_1___r___nth_0__)]
                           (clojure.core/let
                            [?ns X__19691]
                            (clojure.core/let
                             [X__19693
                              (clojure.core/name
                               input__19495_nth_1___r___nth_0__)]
                             (if
                              (clojure.core/string? X__19693)
                              (clojure.core/let
                               [ret__19694
                                (clojure.core/re-matches
                                 #"&.*"
                                 X__19693)]
                               (if
                                (clojure.core/some? ret__19694)
                                (clojure.core/let
                                 [?name ret__19694]
                                 (clojure.core/let
                                  [?pattern
                                   input__19495_nth_1___r___nth_1__]
                                  (if
                                   (clojure.core/map?
                                    input__19495_nth_2__)
                                   (clojure.core/let
                                    [VAL__19678
                                     (.valAt
                                      input__19495_nth_2__
                                      :aliases)]
                                    (if
                                     (clojure.core/map? VAL__19678)
                                     (clojure.core/let
                                      [X__19680
                                       (clojure.core/set VAL__19678)]
                                      (if
                                       (clojure.core/<=
                                        1
                                        (clojure.core/count X__19680))
                                       (clojure.core/loop
                                        [search_space__20721
                                         (clojure.core/seq X__19680)]
                                        (if
                                         (clojure.core/seq
                                          search_space__20721)
                                         (clojure.core/let
                                          [elem__19681
                                           (clojure.core/first
                                            search_space__20721)
                                           result__20722
                                           (clojure.core/let
                                            [elem__19681_nth_0__
                                             (clojure.core/nth
                                              elem__19681
                                              0)
                                             elem__19681_nth_1__
                                             (clojure.core/nth
                                              elem__19681
                                              1)]
                                            (if
                                             (clojure.core/symbol?
                                              elem__19681_nth_0__)
                                             (clojure.core/let
                                              [X__19683
                                               (clojure.core/name
                                                elem__19681_nth_0__)]
                                              (if
                                               (clojure.core/=
                                                ?ns
                                                X__19683)
                                               (if
                                                (clojure.core/symbol?
                                                 elem__19681_nth_1__)
                                                (clojure.core/let
                                                 [X__19685
                                                  (clojure.core/name
                                                   elem__19681_nth_1__)]
                                                 (clojure.core/case
                                                  X__19685
                                                  ("meander.zeta")
                                                  (clojure.core/let
                                                   [?env
                                                    input__19495_nth_2__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__9246__auto__
                                                        (CATA__FN__19554
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
                                                         CATA_RESULT__9246__auto__)
                                                        (throw
                                                         (meander.runtime.zeta/fail))
                                                        (clojure.core/nth
                                                         CATA_RESULT__9246__auto__
                                                         0))))]
                                                    (catch
                                                     java.lang.Exception
                                                     e__10186__auto__
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       e__10186__auto__)
                                                      (meander.runtime.zeta/fail)
                                                      (throw
                                                       e__10186__auto__)))))
                                                  (meander.runtime.zeta/fail)))
                                                (meander.runtime.zeta/fail))
                                               (meander.runtime.zeta/fail)))
                                             (meander.runtime.zeta/fail)))]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            result__20722)
                                           (recur
                                            (clojure.core/next
                                             search_space__20721))
                                           result__20722))
                                         (state__20718)))
                                       (state__20718)))
                                     (state__20718)))
                                   (state__20718))))
                                (state__20718)))
                              (state__20718)))))
                          (state__20718)))
                        (state__20718)))
                      (state__20718))))
                   (state__20718
                    []
                    (clojure.core/loop
                     [search_space__20724
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__19495_nth_1__)]
                     (if
                      (clojure.core/seq search_space__20724)
                      (clojure.core/let
                       [input__19495_nth_1___parts__
                        (clojure.core/first search_space__20724)
                        result__20725
                        (clojure.core/let
                         [input__19495_nth_1___l__
                          (clojure.core/nth
                           input__19495_nth_1___parts__
                           0)
                          input__19495_nth_1___r__
                          (clojure.core/nth
                           input__19495_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs
                           (clojure.core/vec input__19495_nth_1___l__)]
                          (clojure.core/let
                           [input__19495_nth_1___r___l__
                            (clojure.core/subvec
                             input__19495_nth_1___r__
                             0
                             (clojure.core/min
                              (clojure.core/count
                               input__19495_nth_1___r__)
                              1))]
                           (if
                            (clojure.core/=
                             (clojure.core/count
                              input__19495_nth_1___r___l__)
                             1)
                            (clojure.core/let
                             [input__19495_nth_1___r___r__
                              (clojure.core/subvec
                               input__19495_nth_1___r__
                               1)]
                             (if
                              (clojure.core/=
                               input__19495_nth_1___r___l__
                               ['.])
                              (clojure.core/let
                               [?rest input__19495_nth_1___r___r__]
                               (clojure.core/let
                                [?env input__19495_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [!xs__counter
                                    (meander.runtime.zeta/iterator
                                     !xs)]
                                   (clojure.core/let
                                    [CATA_RESULT__9246__auto__
                                     (CATA__FN__19554
                                      ['meander.dev.parse.zeta/make-join
                                       (clojure.core/let
                                        [CATA_RESULT__9246__auto__
                                         (CATA__FN__19554
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            (clojure.core/vec
                                             (clojure.core/iterator-seq
                                              !xs__counter)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9246__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9246__auto__
                                          0)))
                                       (clojure.core/let
                                        [CATA_RESULT__9246__auto__
                                         (CATA__FN__19554
                                          ['meander.dev.parse.zeta/parse-sequential
                                           ?rest
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9246__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9246__auto__
                                          0)))
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__9246__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__9246__auto__
                                      0))))]
                                 (catch
                                  java.lang.Exception
                                  e__10186__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__10186__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__10186__auto__))))))
                              (meander.runtime.zeta/fail)))
                            (meander.runtime.zeta/fail)))))]
                       (if
                        (meander.runtime.zeta/fail? result__20725)
                        (recur (clojure.core/next search_space__20724))
                        result__20725))
                      (state__20719))))
                   (state__20719
                    []
                    (clojure.core/let
                     [input__19495_nth_1___l__
                      (clojure.core/subvec
                       input__19495_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__19495_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__19495_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__19495_nth_1___r__
                        (clojure.core/subvec input__19495_nth_1__ 1)]
                       (if
                        (clojure.core/=
                         input__19495_nth_1___l__
                         ['...])
                        (clojure.core/let
                         [?rest input__19495_nth_1___r__]
                         (clojure.core/let
                          [?env input__19495_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__9246__auto__
                              (CATA__FN__19554
                               ['meander.dev.parse.zeta/parse-sequential
                                ?rest
                                ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__9246__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__9246__auto__
                               0)))]
                           (catch
                            java.lang.Exception
                            e__10186__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__10186__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10186__auto__))))))
                        (state__20720)))
                      (state__20720))))
                   (state__20720
                    []
                    (clojure.core/loop
                     [search_space__20727
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__19495_nth_1__)]
                     (if
                      (clojure.core/seq search_space__20727)
                      (clojure.core/let
                       [input__19495_nth_1___parts__
                        (clojure.core/first search_space__20727)
                        result__20728
                        (clojure.core/let
                         [input__19495_nth_1___l__
                          (clojure.core/nth
                           input__19495_nth_1___parts__
                           0)
                          input__19495_nth_1___r__
                          (clojure.core/nth
                           input__19495_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__8107__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__19495_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__19711]
                              (clojure.core/let
                               [input__19711_nth_0__
                                (clojure.core/nth input__19711 0)]
                               (clojure.core/letfn
                                [(save__19712
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__20731
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__19711_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__19711_nth_0__)
                                 (clojure.core/let
                                  [X__19714
                                   (clojure.core/namespace
                                    input__19711_nth_0__)]
                                  (clojure.core/case
                                   X__19714
                                   (nil)
                                   (clojure.core/let
                                    [X__19716
                                     (clojure.core/name
                                      input__19711_nth_0__)]
                                    (if
                                     (clojure.core/string? X__19716)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__19716)
                                      (save__19712)
                                      (f__20731))
                                     (f__20731)))
                                   (f__20731)))
                                 (f__20731)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__19495_nth_1___r___l__
                                (clojure.core/subvec
                                 input__19495_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__19495_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__19495_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__19495_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__19495_nth_1___r__
                                   1)]
                                 (if
                                  (clojure.core/=
                                   input__19495_nth_1___r___l__
                                   ['...])
                                  (clojure.core/let
                                   [?rest input__19495_nth_1___r___r__]
                                   (clojure.core/let
                                    [?env input__19495_nth_2__]
                                    (try
                                     [(clojure.core/let
                                       [!xs__counter
                                        (meander.runtime.zeta/iterator
                                         !xs)]
                                       (clojure.core/let
                                        [CATA_RESULT__9246__auto__
                                         (CATA__FN__19554
                                          ['meander.dev.parse.zeta/make-star
                                           (clojure.core/let
                                            [CATA_RESULT__9246__auto__
                                             (CATA__FN__19554
                                              ['meander.dev.parse.zeta/parse-sequential
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !xs__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9246__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9246__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__9246__auto__
                                             (CATA__FN__19554
                                              ['meander.dev.parse.zeta/parse-sequential
                                               ?rest
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9246__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9246__auto__
                                              0)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9246__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9246__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__10186__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10186__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10186__auto__))))))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))))]
                           (if
                            (meander.runtime.zeta/fail?
                             ret__8107__auto__)
                            (meander.runtime.zeta/fail)
                            ret__8107__auto__))))]
                       (if
                        (meander.runtime.zeta/fail? result__20728)
                        (recur (clojure.core/next search_space__20727))
                        result__20728))
                      (state__20715))))]
                  (state__20717))
                 (state__20715))
                (state__20715)))
              (state__20715)))
            (state__20715
             []
             (if
              (clojure.core/= (clojure.core/count input__19495) 4)
              (clojure.core/let
               [input__19495_nth_0__
                (clojure.core/nth input__19495 0)
                input__19495_nth_1__
                (clojure.core/nth input__19495 1)
                input__19495_nth_2__
                (clojure.core/nth input__19495 2)]
               (clojure.core/letfn
                [(state__20732
                  []
                  (clojure.core/let
                   [input__19495_nth_3__
                    (clojure.core/nth input__19495 3)]
                   (clojure.core/case
                    input__19495_nth_0__
                    (meander.dev.parse.zeta/make-star)
                    (clojure.core/letfn
                     [(state__20734
                       []
                       (if
                        (clojure.core/map? input__19495_nth_1__)
                        (clojure.core/let
                         [VAL__19720
                          (.valAt input__19495_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__19720
                          (:cat)
                          (clojure.core/let
                           [VAL__19721
                            (.valAt input__19495_nth_1__ :sequence)]
                           (if
                            (clojure.core/vector? VAL__19721)
                            (if
                             (clojure.core/=
                              (clojure.core/count VAL__19721)
                              1)
                             (clojure.core/let
                              [VAL__19721_nth_0__
                               (clojure.core/nth VAL__19721 0)]
                              (if
                               (clojure.core/map? VAL__19721_nth_0__)
                               (clojure.core/let
                                [VAL__19726
                                 (.valAt VAL__19721_nth_0__ :tag)]
                                (clojure.core/case
                                 VAL__19726
                                 (:memory-variable)
                                 (clojure.core/let
                                  [?memory-variable VAL__19721_nth_0__]
                                  (clojure.core/let
                                   [VAL__19722
                                    (.valAt
                                     input__19495_nth_1__
                                     :next)]
                                   (if
                                    (clojure.core/map? VAL__19722)
                                    (clojure.core/let
                                     [VAL__19723
                                      (.valAt VAL__19722 :tag)]
                                     (clojure.core/case
                                      VAL__19723
                                      (:empty)
                                      (clojure.core/let
                                       [?next input__19495_nth_2__]
                                       (clojure.core/let
                                        [?env input__19495_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__9246__auto__
                                            (CATA__FN__19554
                                             ['meander.dev.parse.zeta/make-join
                                              {:tag :into,
                                               :memory-variable
                                               ?memory-variable}
                                              ?next
                                              ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             CATA_RESULT__9246__auto__)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             CATA_RESULT__9246__auto__
                                             0)))]
                                         (catch
                                          java.lang.Exception
                                          e__10186__auto__
                                          (if
                                           (meander.runtime.zeta/fail?
                                            e__10186__auto__)
                                           (meander.runtime.zeta/fail)
                                           (throw
                                            e__10186__auto__))))))
                                      (state__20735)))
                                    (state__20735))))
                                 (state__20735)))
                               (state__20735)))
                             (state__20735))
                            (state__20735)))
                          (state__20735)))
                        (state__20735)))
                      (state__20735
                       []
                       (clojure.core/let
                        [?pattern input__19495_nth_1__]
                        (clojure.core/let
                         [?next input__19495_nth_2__]
                         (if
                          (clojure.core/map? input__19495_nth_3__)
                          (clojure.core/let
                           [VAL__19729
                            (.valAt input__19495_nth_3__ :context)]
                           (clojure.core/case
                            VAL__19729
                            (:string)
                            (try
                             [{:tag :string-star,
                               :greedy? false,
                               :pattern ?pattern,
                               :next ?next}]
                             (catch
                              java.lang.Exception
                              e__10186__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__10186__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10186__auto__))))
                            (state__20733)))
                          (state__20733)))))]
                     (state__20734))
                    (state__20733))))
                 (state__20733
                  []
                  (clojure.core/case
                   input__19495_nth_0__
                   (meander.dev.parse.zeta/make-star)
                   (clojure.core/let
                    [?pattern input__19495_nth_1__]
                    (clojure.core/let
                     [?next input__19495_nth_2__]
                     (try
                      [{:tag :star,
                        :greedy? false,
                        :pattern ?pattern,
                        :next ?next}]
                      (catch
                       java.lang.Exception
                       e__10186__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10186__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10186__auto__))))))
                   (state__20716)))]
                (state__20732)))
              (state__20716)))
            (state__20716
             []
             (if
              (clojure.core/= (clojure.core/count input__19495) 3)
              (clojure.core/let
               [input__19495_nth_0__
                (clojure.core/nth input__19495 0)
                input__19495_nth_1__
                (clojure.core/nth input__19495 1)
                input__19495_nth_2__
                (clojure.core/nth input__19495 2)]
               (clojure.core/case
                input__19495_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__19495_nth_1__)
                 (clojure.core/let
                  [input__19495_nth_1___l__
                   (clojure.core/subvec
                    input__19495_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__19495_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__19495_nth_1___l__)
                    1)
                   (clojure.core/let
                    [input__19495_nth_1___r__
                     (clojure.core/subvec input__19495_nth_1__ 1)]
                    (clojure.core/let
                     [input__19495_nth_1___l___nth_0__
                      (clojure.core/nth input__19495_nth_1___l__ 0)]
                     (if
                      (clojure.core/symbol?
                       input__19495_nth_1___l___nth_0__)
                      (clojure.core/let
                       [X__19737
                        (clojure.core/namespace
                         input__19495_nth_1___l___nth_0__)]
                       (clojure.core/case
                        X__19737
                        (nil)
                        (clojure.core/let
                         [X__19739
                          (clojure.core/name
                           input__19495_nth_1___l___nth_0__)]
                         (if
                          (clojure.core/string? X__19739)
                          (clojure.core/let
                           [ret__19740
                            (clojure.core/re-matches
                             #"\.\.(\d+)"
                             X__19739)]
                           (if
                            (clojure.core/some? ret__19740)
                            (if
                             (clojure.core/vector? ret__19740)
                             (if
                              (clojure.core/=
                               (clojure.core/count ret__19740)
                               2)
                              (clojure.core/let
                               [ret__19740_nth_1__
                                (clojure.core/nth ret__19740 1)]
                               (clojure.core/let
                                [?n ret__19740_nth_1__]
                                (clojure.core/let
                                 [?operator
                                  input__19495_nth_1___l___nth_0__]
                                 (clojure.core/let
                                  [?rest input__19495_nth_1___r__]
                                  (clojure.core/let
                                   [?env input__19495_nth_2__]
                                   (try
                                    [{:tag :syntax-error,
                                      :message
                                      "The n or more operator ..N must be preceeded by at least one pattern"}]
                                    (catch
                                     java.lang.Exception
                                     e__10186__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10186__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10186__auto__)))))))))
                              (state__20670))
                             (state__20670))
                            (state__20670)))
                          (state__20670)))
                        (state__20670)))
                      (state__20670))))
                   (state__20670)))
                 (state__20670))
                (state__20670)))
              (state__20670)))]
           (state__20714))
          (state__20670)))
        (state__20670
         []
         (clojure.core/letfn
          [(def__19743
            [arg__19767]
            (clojure.core/letfn
             [(state__20736
               []
               (clojure.core/let
                [x__19768 :string-plus]
                (clojure.core/let
                 [?tag x__19768]
                 (if
                  (clojure.core/map? arg__19767)
                  (clojure.core/let
                   [VAL__19769 (.valAt arg__19767 :context)]
                   (clojure.core/case
                    VAL__19769
                    (:string)
                    (clojure.core/let [?env arg__19767] [?tag ?env])
                    (state__20737)))
                  (state__20737)))))
              (state__20737
               []
               (clojure.core/let
                [x__19770 :plus]
                (clojure.core/let
                 [?tag x__19770]
                 (clojure.core/let [?env arg__19767] [?tag ?env]))))]
             (state__20736)))]
          (if
           (clojure.core/vector? input__19495)
           (if
            (clojure.core/= (clojure.core/count input__19495) 3)
            (clojure.core/let
             [input__19495_nth_0__
              (clojure.core/nth input__19495 0)
              input__19495_nth_1__
              (clojure.core/nth input__19495 1)
              input__19495_nth_2__
              (clojure.core/nth input__19495 2)]
             (clojure.core/case
              input__19495_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__19495_nth_1__)
               (clojure.core/loop
                [search_space__20738
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__19495_nth_1__)]
                (if
                 (clojure.core/seq search_space__20738)
                 (clojure.core/let
                  [input__19495_nth_1___parts__
                   (clojure.core/first search_space__20738)
                   result__20739
                   (clojure.core/let
                    [input__19495_nth_1___l__
                     (clojure.core/nth input__19495_nth_1___parts__ 0)
                     input__19495_nth_1___r__
                     (clojure.core/nth input__19495_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__8107__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__19495_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__19760]
                         (clojure.core/let
                          [input__19760_nth_0__
                           (clojure.core/nth input__19760 0)]
                          (clojure.core/letfn
                           [(save__19761
                             []
                             (meander.runtime.zeta/fail))
                            (f__20742
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__19760_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__19760_nth_0__)
                            (clojure.core/let
                             [X__19763
                              (clojure.core/namespace
                               input__19760_nth_0__)]
                             (clojure.core/case
                              X__19763
                              (nil)
                              (clojure.core/let
                               [X__19765
                                (clojure.core/name
                                 input__19760_nth_0__)]
                               (if
                                (clojure.core/string? X__19765)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__19765)
                                 (save__19761)
                                 (f__20742))
                                (f__20742)))
                              (f__20742)))
                            (f__20742)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__19495_nth_1___r___l__
                           (clojure.core/subvec
                            input__19495_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__19495_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__19495_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__19495_nth_1___r___r__
                             (clojure.core/subvec
                              input__19495_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__19495_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__19495_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__19495_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__19754
                                (clojure.core/namespace
                                 input__19495_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__19754
                                (nil)
                                (clojure.core/let
                                 [X__19756
                                  (clojure.core/name
                                   input__19495_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__19756)
                                  (clojure.core/let
                                   [ret__19757
                                    (clojure.core/re-matches
                                     #"\.\.(\d+)"
                                     X__19756)]
                                   (if
                                    (clojure.core/some? ret__19757)
                                    (if
                                     (clojure.core/vector? ret__19757)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__19757)
                                       2)
                                      (clojure.core/let
                                       [ret__19757_nth_1__
                                        (clojure.core/nth
                                         ret__19757
                                         1)]
                                       (clojure.core/let
                                        [?n ret__19757_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__19495_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__7943__auto__
                                           (def__19743
                                            input__19495_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__7943__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__7943__auto__]
                                            (try
                                             [(clojure.core/let
                                               [!xs__counter
                                                (meander.runtime.zeta/iterator
                                                 !xs)]
                                               {:tag ?tag,
                                                :n (Integer. ?n),
                                                :greedy? false,
                                                :pattern
                                                (clojure.core/let
                                                 [CATA_RESULT__9246__auto__
                                                  (CATA__FN__19554
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9246__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9246__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__9246__auto__
                                                  (CATA__FN__19554
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9246__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9246__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__10186__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__10186__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__10186__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__8107__auto__)
                       (meander.runtime.zeta/fail)
                       ret__8107__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__20739)
                   (recur (clojure.core/next search_space__20738))
                   result__20739))
                 (state__20671)))
               (state__20671))
              (state__20671)))
            (state__20671))
           (state__20671))))
        (state__20671
         []
         (if
          (clojure.core/vector? input__19495)
          (if
           (clojure.core/= (clojure.core/count input__19495) 3)
           (clojure.core/let
            [input__19495_nth_0__
             (clojure.core/nth input__19495 0)
             input__19495_nth_1__
             (clojure.core/nth input__19495 1)
             input__19495_nth_2__
             (clojure.core/nth input__19495 2)]
            (clojure.core/case
             input__19495_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__19495_nth_1__)
              (clojure.core/let
               [input__19495_nth_1___l__
                (clojure.core/subvec
                 input__19495_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__19495_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__19495_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__19495_nth_1___r__
                  (clojure.core/subvec input__19495_nth_1__ 1)]
                 (clojure.core/let
                  [input__19495_nth_1___l___nth_0__
                   (clojure.core/nth input__19495_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__19495_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__19788
                     (clojure.core/namespace
                      input__19495_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__19788
                     (nil)
                     (clojure.core/let
                      [X__19790
                       (clojure.core/name
                        input__19495_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__19790)
                       (clojure.core/let
                        [ret__19791
                         (clojure.core/re-matches
                          #"\.\.(\?.+)"
                          X__19790)]
                        (if
                         (clojure.core/some? ret__19791)
                         (if
                          (clojure.core/vector? ret__19791)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__19791)
                            2)
                           (clojure.core/let
                            [ret__19791_nth_1__
                             (clojure.core/nth ret__19791 1)]
                            (clojure.core/let
                             [?n ret__19791_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__19495_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__19495_nth_1___r__]
                               (clojure.core/let
                                [?env input__19495_nth_2__]
                                (try
                                 [{:tag :syntax-error,
                                   :message
                                   "The ?n or more operator ..?n must be preceeded by at least one pattern"}]
                                 (catch
                                  java.lang.Exception
                                  e__10186__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__10186__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__10186__auto__)))))))))
                           (state__20672))
                          (state__20672))
                         (state__20672)))
                       (state__20672)))
                     (state__20672)))
                   (state__20672))))
                (state__20672)))
              (state__20672))
             (state__20672)))
           (state__20672))
          (state__20672)))
        (state__20672
         []
         (clojure.core/letfn
          [(def__19794
            [arg__19818]
            (clojure.core/letfn
             [(state__20743
               []
               (clojure.core/let
                [x__19819 :string-logical-plus]
                (clojure.core/let
                 [?tag x__19819]
                 (if
                  (clojure.core/map? arg__19818)
                  (clojure.core/let
                   [VAL__19820 (.valAt arg__19818 :context)]
                   (clojure.core/case
                    VAL__19820
                    (:string)
                    (clojure.core/let [?env arg__19818] [?tag ?env])
                    (state__20744)))
                  (state__20744)))))
              (state__20744
               []
               (clojure.core/let
                [x__19821 :logical-plus]
                (clojure.core/let
                 [?tag x__19821]
                 (clojure.core/let [?env arg__19818] [?tag ?env]))))]
             (state__20743)))]
          (if
           (clojure.core/vector? input__19495)
           (if
            (clojure.core/= (clojure.core/count input__19495) 3)
            (clojure.core/let
             [input__19495_nth_0__
              (clojure.core/nth input__19495 0)
              input__19495_nth_1__
              (clojure.core/nth input__19495 1)
              input__19495_nth_2__
              (clojure.core/nth input__19495 2)]
             (clojure.core/case
              input__19495_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__19495_nth_1__)
               (clojure.core/loop
                [search_space__20745
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__19495_nth_1__)]
                (if
                 (clojure.core/seq search_space__20745)
                 (clojure.core/let
                  [input__19495_nth_1___parts__
                   (clojure.core/first search_space__20745)
                   result__20746
                   (clojure.core/let
                    [input__19495_nth_1___l__
                     (clojure.core/nth input__19495_nth_1___parts__ 0)
                     input__19495_nth_1___r__
                     (clojure.core/nth input__19495_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__8107__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__19495_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__19811]
                         (clojure.core/let
                          [input__19811_nth_0__
                           (clojure.core/nth input__19811 0)]
                          (clojure.core/letfn
                           [(save__19812
                             []
                             (meander.runtime.zeta/fail))
                            (f__20749
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__19811_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__19811_nth_0__)
                            (clojure.core/let
                             [X__19814
                              (clojure.core/namespace
                               input__19811_nth_0__)]
                             (clojure.core/case
                              X__19814
                              (nil)
                              (clojure.core/let
                               [X__19816
                                (clojure.core/name
                                 input__19811_nth_0__)]
                               (if
                                (clojure.core/string? X__19816)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__19816)
                                 (save__19812)
                                 (f__20749))
                                (f__20749)))
                              (f__20749)))
                            (f__20749)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__19495_nth_1___r___l__
                           (clojure.core/subvec
                            input__19495_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__19495_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__19495_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__19495_nth_1___r___r__
                             (clojure.core/subvec
                              input__19495_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__19495_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__19495_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__19495_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__19805
                                (clojure.core/namespace
                                 input__19495_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__19805
                                (nil)
                                (clojure.core/let
                                 [X__19807
                                  (clojure.core/name
                                   input__19495_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__19807)
                                  (clojure.core/let
                                   [ret__19808
                                    (clojure.core/re-matches
                                     #"\.\.(\?.+)"
                                     X__19807)]
                                   (if
                                    (clojure.core/some? ret__19808)
                                    (if
                                     (clojure.core/vector? ret__19808)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__19808)
                                       2)
                                      (clojure.core/let
                                       [ret__19808_nth_1__
                                        (clojure.core/nth
                                         ret__19808
                                         1)]
                                       (clojure.core/let
                                        [?n ret__19808_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__19495_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__7943__auto__
                                           (def__19794
                                            input__19495_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__7943__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__7943__auto__]
                                            (try
                                             [(clojure.core/let
                                               [!xs__counter
                                                (meander.runtime.zeta/iterator
                                                 !xs)]
                                               {:tag ?tag,
                                                :n
                                                {:tag :logic-variable,
                                                 :name ?n,
                                                 :symbol
                                                 (clojure.core/symbol
                                                  ?n)},
                                                :greedy? false,
                                                :pattern
                                                (clojure.core/let
                                                 [CATA_RESULT__9246__auto__
                                                  (CATA__FN__19554
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9246__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9246__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__9246__auto__
                                                  (CATA__FN__19554
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9246__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9246__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__10186__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__10186__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__10186__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__8107__auto__)
                       (meander.runtime.zeta/fail)
                       ret__8107__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__20746)
                   (recur (clojure.core/next search_space__20745))
                   result__20746))
                 (state__20673)))
               (state__20673))
              (state__20673)))
            (state__20673))
           (state__20673))))
        (state__20673
         []
         (if
          (clojure.core/vector? input__19495)
          (if
           (clojure.core/= (clojure.core/count input__19495) 3)
           (clojure.core/let
            [input__19495_nth_0__
             (clojure.core/nth input__19495 0)
             input__19495_nth_1__
             (clojure.core/nth input__19495 1)
             input__19495_nth_2__
             (clojure.core/nth input__19495 2)]
            (clojure.core/case
             input__19495_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__19495_nth_1__)
              (clojure.core/let
               [input__19495_nth_1___l__
                (clojure.core/subvec
                 input__19495_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__19495_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__19495_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__19495_nth_1___r__
                  (clojure.core/subvec input__19495_nth_1__ 1)]
                 (clojure.core/let
                  [input__19495_nth_1___l___nth_0__
                   (clojure.core/nth input__19495_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__19495_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__19839
                     (clojure.core/namespace
                      input__19495_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__19839
                     (nil)
                     (clojure.core/let
                      [X__19841
                       (clojure.core/name
                        input__19495_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__19841)
                       (clojure.core/let
                        [ret__19842
                         (clojure.core/re-matches
                          #"\.\.(!.+)"
                          X__19841)]
                        (if
                         (clojure.core/some? ret__19842)
                         (if
                          (clojure.core/vector? ret__19842)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__19842)
                            2)
                           (clojure.core/let
                            [ret__19842_nth_1__
                             (clojure.core/nth ret__19842 1)]
                            (clojure.core/let
                             [?n ret__19842_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__19495_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__19495_nth_1___r__]
                               (clojure.core/let
                                [?env input__19495_nth_2__]
                                (try
                                 [{:tag :syntax-error,
                                   :message
                                   "The operator ..!n must be preceeded by at least one pattern"}]
                                 (catch
                                  java.lang.Exception
                                  e__10186__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__10186__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__10186__auto__)))))))))
                           (state__20674))
                          (state__20674))
                         (state__20674)))
                       (state__20674)))
                     (state__20674)))
                   (state__20674))))
                (state__20674)))
              (state__20674))
             (state__20674)))
           (state__20674))
          (state__20674)))
        (state__20674
         []
         (clojure.core/letfn
          [(def__19845
            [arg__19869]
            (clojure.core/letfn
             [(state__20750
               []
               (clojure.core/let
                [x__19870 :string-memory-plus]
                (clojure.core/let
                 [?tag x__19870]
                 (if
                  (clojure.core/map? arg__19869)
                  (clojure.core/let
                   [VAL__19871 (.valAt arg__19869 :context)]
                   (clojure.core/case
                    VAL__19871
                    (:string)
                    (clojure.core/let [?env arg__19869] [?tag ?env])
                    (state__20751)))
                  (state__20751)))))
              (state__20751
               []
               (clojure.core/let
                [x__19872 :memory-plus]
                (clojure.core/let
                 [?tag x__19872]
                 (clojure.core/let [?env arg__19869] [?tag ?env]))))]
             (state__20750)))]
          (if
           (clojure.core/vector? input__19495)
           (if
            (clojure.core/= (clojure.core/count input__19495) 3)
            (clojure.core/let
             [input__19495_nth_0__
              (clojure.core/nth input__19495 0)
              input__19495_nth_1__
              (clojure.core/nth input__19495 1)
              input__19495_nth_2__
              (clojure.core/nth input__19495 2)]
             (clojure.core/case
              input__19495_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__19495_nth_1__)
               (clojure.core/loop
                [search_space__20752
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__19495_nth_1__)]
                (if
                 (clojure.core/seq search_space__20752)
                 (clojure.core/let
                  [input__19495_nth_1___parts__
                   (clojure.core/first search_space__20752)
                   result__20753
                   (clojure.core/let
                    [input__19495_nth_1___l__
                     (clojure.core/nth input__19495_nth_1___parts__ 0)
                     input__19495_nth_1___r__
                     (clojure.core/nth input__19495_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__8107__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__19495_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__19862]
                         (clojure.core/let
                          [input__19862_nth_0__
                           (clojure.core/nth input__19862 0)]
                          (clojure.core/letfn
                           [(save__19863
                             []
                             (meander.runtime.zeta/fail))
                            (f__20756
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__19862_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__19862_nth_0__)
                            (clojure.core/let
                             [X__19865
                              (clojure.core/namespace
                               input__19862_nth_0__)]
                             (clojure.core/case
                              X__19865
                              (nil)
                              (clojure.core/let
                               [X__19867
                                (clojure.core/name
                                 input__19862_nth_0__)]
                               (if
                                (clojure.core/string? X__19867)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__19867)
                                 (save__19863)
                                 (f__20756))
                                (f__20756)))
                              (f__20756)))
                            (f__20756)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__19495_nth_1___r___l__
                           (clojure.core/subvec
                            input__19495_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__19495_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__19495_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__19495_nth_1___r___r__
                             (clojure.core/subvec
                              input__19495_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__19495_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__19495_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__19495_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__19856
                                (clojure.core/namespace
                                 input__19495_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__19856
                                (nil)
                                (clojure.core/let
                                 [X__19858
                                  (clojure.core/name
                                   input__19495_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__19858)
                                  (clojure.core/let
                                   [ret__19859
                                    (clojure.core/re-matches
                                     #"\.\.(\!.+)"
                                     X__19858)]
                                   (if
                                    (clojure.core/some? ret__19859)
                                    (if
                                     (clojure.core/vector? ret__19859)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__19859)
                                       2)
                                      (clojure.core/let
                                       [ret__19859_nth_1__
                                        (clojure.core/nth
                                         ret__19859
                                         1)]
                                       (clojure.core/let
                                        [?n ret__19859_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__19495_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__7943__auto__
                                           (def__19845
                                            input__19495_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__7943__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__7943__auto__]
                                            (try
                                             [(clojure.core/let
                                               [!xs__counter
                                                (meander.runtime.zeta/iterator
                                                 !xs)]
                                               {:tag ?tag,
                                                :n
                                                {:tag :memory-variable,
                                                 :name ?n,
                                                 :symbol
                                                 (clojure.core/symbol
                                                  ?n)},
                                                :greedy? false,
                                                :pattern
                                                (clojure.core/let
                                                 [CATA_RESULT__9246__auto__
                                                  (CATA__FN__19554
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9246__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9246__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__9246__auto__
                                                  (CATA__FN__19554
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9246__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9246__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__10186__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__10186__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__10186__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__8107__auto__)
                       (meander.runtime.zeta/fail)
                       ret__8107__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__20753)
                   (recur (clojure.core/next search_space__20752))
                   result__20753))
                 (state__20675)))
               (state__20675))
              (state__20675)))
            (state__20675))
           (state__20675))))
        (state__20675
         []
         (if
          (clojure.core/vector? input__19495)
          (clojure.core/letfn
           [(state__20757
             []
             (if
              (clojure.core/= (clojure.core/count input__19495) 3)
              (clojure.core/let
               [input__19495_nth_0__
                (clojure.core/nth input__19495 0)
                input__19495_nth_1__
                (clojure.core/nth input__19495 1)
                input__19495_nth_2__
                (clojure.core/nth input__19495 2)]
               (clojure.core/case
                input__19495_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__19495_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__19495_nth_1__)]
                  (clojure.core/let
                   [?env input__19495_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__9246__auto__
                        (CATA__FN__19554
                         ['meander.dev.parse.zeta/make-cat
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__19555 (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (recur
                              (clojure.core/conj!
                               return__19555
                               (clojure.core/let
                                [CATA_RESULT__9246__auto__
                                 (CATA__FN__19554
                                  [(if
                                    (.hasNext !xs__counter)
                                    (.next !xs__counter))
                                   ?env])]
                                (if
                                 (meander.runtime.zeta/fail?
                                  CATA_RESULT__9246__auto__)
                                 (throw (meander.runtime.zeta/fail))
                                 (clojure.core/nth
                                  CATA_RESULT__9246__auto__
                                  0)))))
                             (clojure.core/persistent!
                              return__19555))))
                          {:tag :empty}
                          ?env])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__9246__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__9246__auto__
                         0))))]
                    (catch
                     java.lang.Exception
                     e__10186__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10186__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10186__auto__))))))
                 (state__20758))
                (state__20758)))
              (state__20758)))
            (state__20758
             []
             (if
              (clojure.core/= (clojure.core/count input__19495) 4)
              (clojure.core/let
               [input__19495_nth_0__
                (clojure.core/nth input__19495 0)
                input__19495_nth_1__
                (clojure.core/nth input__19495 1)
                input__19495_nth_2__
                (clojure.core/nth input__19495 2)]
               (clojure.core/letfn
                [(state__20760
                  []
                  (clojure.core/let
                   [input__19495_nth_3__
                    (clojure.core/nth input__19495 3)]
                   (clojure.core/case
                    input__19495_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (if
                     (clojure.core/vector? input__19495_nth_1__)
                     (clojure.core/letfn
                      [(state__20767
                        []
                        (clojure.core/case
                         input__19495_nth_1__
                         ([])
                         (clojure.core/let
                          [?next input__19495_nth_2__]
                          (clojure.core/let
                           [?env input__19495_nth_3__]
                           (try
                            [?next]
                            (catch
                             java.lang.Exception
                             e__10186__auto__
                             (if
                              (meander.runtime.zeta/fail?
                               e__10186__auto__)
                              (meander.runtime.zeta/fail)
                              (throw e__10186__auto__))))))
                         (state__20768)))
                       (state__20768
                        []
                        (clojure.core/loop
                         [search_space__20769
                          (meander.runtime.zeta/epsilon-partitions
                           2
                           input__19495_nth_1__)]
                         (if
                          (clojure.core/seq search_space__20769)
                          (clojure.core/let
                           [input__19495_nth_1___parts__
                            (clojure.core/first search_space__20769)
                            result__20770
                            (clojure.core/let
                             [input__19495_nth_1___l__
                              (clojure.core/nth
                               input__19495_nth_1___parts__
                               0)
                              input__19495_nth_1___r__
                              (clojure.core/nth
                               input__19495_nth_1___parts__
                               1)]
                             (clojure.core/let
                              [!xs []]
                              (clojure.core/let
                               [ret__8107__auto__
                                (meander.runtime.zeta/epsilon-run-star-1
                                 input__19495_nth_1___l__
                                 [!xs]
                                 (clojure.core/fn
                                  [[!xs] input__19898]
                                  (clojure.core/let
                                   [input__19898_nth_0__
                                    (clojure.core/nth input__19898 0)]
                                   (clojure.core/letfn
                                    [(save__19899
                                      []
                                      (meander.runtime.zeta/fail))
                                     (f__20773
                                      []
                                      (clojure.core/let
                                       [!xs
                                        (clojure.core/conj
                                         !xs
                                         input__19898_nth_0__)]
                                       [!xs]))]
                                    (if
                                     (clojure.core/map?
                                      input__19898_nth_0__)
                                     (clojure.core/let
                                      [VAL__19900
                                       (.valAt
                                        input__19898_nth_0__
                                        :tag)]
                                      (clojure.core/case
                                       VAL__19900
                                       (:group)
                                       (save__19899)
                                       (f__20773)))
                                     (f__20773)))))
                                 (clojure.core/fn
                                  [[!xs]]
                                  (clojure.core/let
                                   [input__19495_nth_1___r___l__
                                    (clojure.core/subvec
                                     input__19495_nth_1___r__
                                     0
                                     (clojure.core/min
                                      (clojure.core/count
                                       input__19495_nth_1___r__)
                                      1))]
                                   (if
                                    (clojure.core/=
                                     (clojure.core/count
                                      input__19495_nth_1___r___l__)
                                     1)
                                    (clojure.core/let
                                     [input__19495_nth_1___r___r__
                                      (clojure.core/subvec
                                       input__19495_nth_1___r__
                                       1)]
                                     (clojure.core/let
                                      [input__19495_nth_1___r___l___nth_0__
                                       (clojure.core/nth
                                        input__19495_nth_1___r___l__
                                        0)]
                                      (if
                                       (clojure.core/map?
                                        input__19495_nth_1___r___l___nth_0__)
                                       (clojure.core/let
                                        [VAL__19897
                                         (.valAt
                                          input__19495_nth_1___r___l___nth_0__
                                          :tag)]
                                        (clojure.core/case
                                         VAL__19897
                                         (:group)
                                         (clojure.core/let
                                          [?group
                                           input__19495_nth_1___r___l___nth_0__]
                                          (clojure.core/let
                                           [?rest
                                            input__19495_nth_1___r___r__]
                                           (clojure.core/let
                                            [?next
                                             input__19495_nth_2__]
                                            (clojure.core/let
                                             [?env
                                              input__19495_nth_3__]
                                             (try
                                              [(clojure.core/let
                                                [!xs__counter
                                                 (meander.runtime.zeta/iterator
                                                  !xs)]
                                                (clojure.core/let
                                                 [CATA_RESULT__9246__auto__
                                                  (CATA__FN__19554
                                                   ['meander.dev.parse.zeta/make-join
                                                    (clojure.core/let
                                                     [CATA_RESULT__9246__auto__
                                                      (CATA__FN__19554
                                                       ['meander.dev.parse.zeta/make-cat
                                                        (clojure.core/into
                                                         []
                                                         (clojure.core/vec
                                                          (clojure.core/iterator-seq
                                                           !xs__counter)))
                                                        {:tag :empty}
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__9246__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__9246__auto__
                                                       0)))
                                                    (clojure.core/let
                                                     [CATA_RESULT__9246__auto__
                                                      (CATA__FN__19554
                                                       ['meander.dev.parse.zeta/make-join
                                                        ?group
                                                        ?rest
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__9246__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__9246__auto__
                                                       0)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9246__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9246__auto__
                                                   0))))]
                                              (catch
                                               java.lang.Exception
                                               e__10186__auto__
                                               (if
                                                (meander.runtime.zeta/fail?
                                                 e__10186__auto__)
                                                (meander.runtime.zeta/fail)
                                                (throw
                                                 e__10186__auto__))))))))
                                         (meander.runtime.zeta/fail)))
                                       (meander.runtime.zeta/fail))))
                                    (meander.runtime.zeta/fail)))))]
                               (if
                                (meander.runtime.zeta/fail?
                                 ret__8107__auto__)
                                (meander.runtime.zeta/fail)
                                ret__8107__auto__))))]
                           (if
                            (meander.runtime.zeta/fail? result__20770)
                            (recur
                             (clojure.core/next search_space__20769))
                            result__20770))
                          (state__20761))))]
                      (state__20767))
                     (state__20761))
                    (state__20761))))
                 (state__20761
                  []
                  (clojure.core/case
                   input__19495_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (if
                    (clojure.core/vector? input__19495_nth_1__)
                    (clojure.core/let
                     [input__19495_nth_1___l__
                      (clojure.core/subvec
                       input__19495_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__19495_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__19495_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__19495_nth_1___r__
                        (clojure.core/subvec input__19495_nth_1__ 1)]
                       (clojure.core/let
                        [input__19495_nth_1___l___nth_0__
                         (clojure.core/nth input__19495_nth_1___l__ 0)]
                        (if
                         (clojure.core/map?
                          input__19495_nth_1___l___nth_0__)
                         (clojure.core/let
                          [VAL__19909
                           (.valAt
                            input__19495_nth_1___l___nth_0__
                            :tag)]
                          (clojure.core/case
                           VAL__19909
                           (:literal)
                           (clojure.core/let
                            [VAL__19910
                             (.valAt
                              input__19495_nth_1___l___nth_0__
                              :type)]
                            (if
                             (clojure.core/let
                              [x__7003__auto__ VAL__19910]
                              (clojure.core/case
                               x__7003__auto__
                               (:string :char)
                               true
                               false))
                             (clojure.core/let
                              [VAL__19911
                               (.valAt
                                input__19495_nth_1___l___nth_0__
                                :form)]
                              (clojure.core/let
                               [!forms []]
                               (clojure.core/let
                                [!forms
                                 (clojure.core/conj !forms VAL__19911)]
                                (clojure.core/loop
                                 [i__8080__auto__
                                  0
                                  coll__20774
                                  input__19495_nth_1___r__
                                  [!forms]
                                  [!forms]]
                                 (clojure.core/let
                                  [input__19912
                                   (clojure.core/subvec
                                    coll__20774
                                    0
                                    (clojure.core/min
                                     (clojure.core/count coll__20774)
                                     1))]
                                  (if
                                   (clojure.core/=
                                    (clojure.core/count input__19912)
                                    1)
                                   (clojure.core/let
                                    [result__8081__auto__
                                     (clojure.core/let
                                      [input__19912_nth_0__
                                       (clojure.core/nth
                                        input__19912
                                        0)]
                                      (if
                                       (clojure.core/map?
                                        input__19912_nth_0__)
                                       (clojure.core/let
                                        [VAL__19913
                                         (.valAt
                                          input__19912_nth_0__
                                          :tag)]
                                        (clojure.core/case
                                         VAL__19913
                                         (:literal)
                                         (clojure.core/let
                                          [VAL__19914
                                           (.valAt
                                            input__19912_nth_0__
                                            :type)]
                                          (if
                                           (clojure.core/let
                                            [x__7003__auto__
                                             VAL__19914]
                                            (clojure.core/case
                                             x__7003__auto__
                                             (:string :char)
                                             true
                                             false))
                                           (clojure.core/let
                                            [VAL__19915
                                             (.valAt
                                              input__19912_nth_0__
                                              :form)]
                                            (clojure.core/let
                                             [!forms
                                              (clojure.core/conj
                                               !forms
                                               VAL__19915)]
                                             [!forms]))
                                           (meander.runtime.zeta/fail)))
                                         (meander.runtime.zeta/fail)))
                                       (meander.runtime.zeta/fail)))]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      result__8081__auto__)
                                     (state__20762)
                                     (recur
                                      (clojure.core/inc
                                       i__8080__auto__)
                                      (clojure.core/subvec
                                       coll__20774
                                       1)
                                      result__8081__auto__)))
                                   (if
                                    (clojure.core/or
                                     (clojure.core/seq coll__20774)
                                     (clojure.core/<
                                      i__8080__auto__
                                      0))
                                    (state__20762)
                                    (if
                                     (clojure.core/map?
                                      input__19495_nth_2__)
                                     (clojure.core/let
                                      [VAL__19904
                                       (.valAt
                                        input__19495_nth_2__
                                        :tag)]
                                      (clojure.core/case
                                       VAL__19904
                                       (:empty)
                                       (try
                                        [{:tag :literal,
                                          :type :string,
                                          :form
                                          (clojure.string/join
                                           (clojure.core/into
                                            []
                                            !forms))}]
                                        (catch
                                         java.lang.Exception
                                         e__10186__auto__
                                         (if
                                          (meander.runtime.zeta/fail?
                                           e__10186__auto__)
                                          (meander.runtime.zeta/fail)
                                          (throw e__10186__auto__))))
                                       (state__20762)))
                                     (state__20762)))))))))
                             (state__20762)))
                           (state__20762)))
                         (state__20762))))
                      (state__20762)))
                    (state__20762))
                   (state__20762)))
                 (state__20762
                  []
                  (clojure.core/let
                   [input__19495_nth_3__
                    (clojure.core/nth input__19495 3)]
                   (clojure.core/case
                    input__19495_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (clojure.core/letfn
                     [(state__20775
                       []
                       (if
                        (clojure.core/vector? input__19495_nth_1__)
                        (clojure.core/let
                         [input__19495_nth_1___l__
                          (clojure.core/subvec
                           input__19495_nth_1__
                           0
                           (clojure.core/min
                            (clojure.core/count input__19495_nth_1__)
                            1))]
                         (if
                          (clojure.core/=
                           (clojure.core/count
                            input__19495_nth_1___l__)
                           1)
                          (clojure.core/let
                           [input__19495_nth_1___r__
                            (clojure.core/subvec
                             input__19495_nth_1__
                             1)]
                           (clojure.core/let
                            [input__19495_nth_1___l___nth_0__
                             (clojure.core/nth
                              input__19495_nth_1___l__
                              0)]
                            (if
                             (clojure.core/map?
                              input__19495_nth_1___l___nth_0__)
                             (clojure.core/let
                              [VAL__20665
                               (.valAt
                                input__19495_nth_1___l___nth_0__
                                :tag)]
                              (clojure.core/case
                               VAL__20665
                               (:literal)
                               (clojure.core/letfn
                                [(state__20777
                                  []
                                  (clojure.core/let
                                   [VAL__19922
                                    (.valAt
                                     input__19495_nth_1___l___nth_0__
                                     :type)]
                                   (clojure.core/case
                                    VAL__19922
                                    (:string)
                                    (clojure.core/let
                                     [?ast
                                      input__19495_nth_1___l___nth_0__]
                                     (clojure.core/let
                                      [?rest input__19495_nth_1___r__]
                                      (clojure.core/let
                                       [?next input__19495_nth_2__]
                                       (clojure.core/let
                                        [?env input__19495_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__9246__auto__
                                            (CATA__FN__19554
                                             ['meander.dev.parse.zeta/make-join
                                              ?ast
                                              (clojure.core/let
                                               [CATA_RESULT__9246__auto__
                                                (CATA__FN__19554
                                                 ['meander.dev.parse.zeta/make-cat
                                                  ?rest
                                                  ?next
                                                  ?env])]
                                               (if
                                                (meander.runtime.zeta/fail?
                                                 CATA_RESULT__9246__auto__)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 CATA_RESULT__9246__auto__
                                                 0)))
                                              ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             CATA_RESULT__9246__auto__)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             CATA_RESULT__9246__auto__
                                             0)))]
                                         (catch
                                          java.lang.Exception
                                          e__10186__auto__
                                          (if
                                           (meander.runtime.zeta/fail?
                                            e__10186__auto__)
                                           (meander.runtime.zeta/fail)
                                           (throw
                                            e__10186__auto__))))))))
                                    (state__20778))))
                                 (state__20778
                                  []
                                  (clojure.core/let
                                   [VAL__19932
                                    (.valAt
                                     input__19495_nth_1___l___nth_0__
                                     :form)]
                                   (clojure.core/let
                                    [!forms []]
                                    (clojure.core/let
                                     [!forms
                                      (clojure.core/conj
                                       !forms
                                       VAL__19932)]
                                     (clojure.core/loop
                                      [i__8080__auto__
                                       0
                                       coll__20779
                                       input__19495_nth_1___r__
                                       [!forms]
                                       [!forms]]
                                      (clojure.core/let
                                       [input__19933
                                        (clojure.core/subvec
                                         coll__20779
                                         0
                                         (clojure.core/min
                                          (clojure.core/count
                                           coll__20779)
                                          1))]
                                       (if
                                        (clojure.core/=
                                         (clojure.core/count
                                          input__19933)
                                         1)
                                        (clojure.core/let
                                         [result__8081__auto__
                                          (clojure.core/let
                                           [input__19933_nth_0__
                                            (clojure.core/nth
                                             input__19933
                                             0)]
                                           (if
                                            (clojure.core/map?
                                             input__19933_nth_0__)
                                            (clojure.core/let
                                             [VAL__19934
                                              (.valAt
                                               input__19933_nth_0__
                                               :tag)]
                                             (clojure.core/case
                                              VAL__19934
                                              (:literal)
                                              (clojure.core/let
                                               [VAL__19935
                                                (.valAt
                                                 input__19933_nth_0__
                                                 :form)]
                                               (clojure.core/let
                                                [!forms
                                                 (clojure.core/conj
                                                  !forms
                                                  VAL__19935)]
                                                [!forms]))
                                              (meander.runtime.zeta/fail)))
                                            (meander.runtime.zeta/fail)))]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           result__8081__auto__)
                                          (state__20776)
                                          (recur
                                           (clojure.core/inc
                                            i__8080__auto__)
                                           (clojure.core/subvec
                                            coll__20779
                                            1)
                                           result__8081__auto__)))
                                        (if
                                         (clojure.core/or
                                          (clojure.core/seq
                                           coll__20779)
                                          (clojure.core/<
                                           i__8080__auto__
                                           0))
                                         (state__20776)
                                         (if
                                          (clojure.core/map?
                                           input__19495_nth_2__)
                                          (clojure.core/let
                                           [VAL__19925
                                            (.valAt
                                             input__19495_nth_2__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__19925
                                            (:empty)
                                            (if
                                             (clojure.core/map?
                                              input__19495_nth_3__)
                                             (clojure.core/let
                                              [VAL__19926
                                               (.valAt
                                                input__19495_nth_3__
                                                :context)]
                                              (clojure.core/let
                                               [?context VAL__19926]
                                               (clojure.core/let
                                                [?env
                                                 input__19495_nth_3__]
                                                (try
                                                 [{:tag :literal,
                                                   :type ?context,
                                                   :form
                                                   (clojure.core/into
                                                    []
                                                    !forms)}]
                                                 (catch
                                                  java.lang.Exception
                                                  e__10186__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__10186__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__10186__auto__)))))))
                                             (state__20776))
                                            (state__20776)))
                                          (state__20776))))))))))]
                                (state__20777))
                               (state__20776)))
                             (state__20776))))
                          (state__20776)))
                        (state__20776)))
                      (state__20776
                       []
                       (clojure.core/let
                        [?sequence input__19495_nth_1__]
                        (clojure.core/let
                         [?next input__19495_nth_2__]
                         (if
                          (clojure.core/map? input__19495_nth_3__)
                          (clojure.core/let
                           [VAL__19939
                            (.valAt input__19495_nth_3__ :context)]
                           (clojure.core/case
                            VAL__19939
                            (:string)
                            (try
                             [{:tag :string-cat,
                               :sequence ?sequence,
                               :next ?next}]
                             (catch
                              java.lang.Exception
                              e__10186__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__10186__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10186__auto__))))
                            (state__20763)))
                          (state__20763)))))]
                     (state__20775))
                    (state__20763))))
                 (state__20763
                  []
                  (clojure.core/case
                   input__19495_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (clojure.core/let
                    [?sequence input__19495_nth_1__]
                    (clojure.core/let
                     [?next input__19495_nth_2__]
                     (try
                      [{:tag :cat, :sequence ?sequence, :next ?next}]
                      (catch
                       java.lang.Exception
                       e__10186__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10186__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10186__auto__))))))
                   (state__20764)))
                 (state__20764
                  []
                  (clojure.core/let
                   [input__19495_nth_3__
                    (clojure.core/nth input__19495 3)]
                   (clojure.core/case
                    input__19495_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (if
                     (clojure.core/map? input__19495_nth_1__)
                     (clojure.core/let
                      [VAL__20661 (.valAt input__19495_nth_1__ :tag)]
                      (clojure.core/case
                       VAL__20661
                       (:cat)
                       (clojure.core/let
                        [VAL__19945
                         (.valAt input__19495_nth_1__ :sequence)]
                        (clojure.core/let
                         [?sequence VAL__19945]
                         (clojure.core/let
                          [VAL__19946
                           (.valAt input__19495_nth_1__ :next)]
                          (if
                           (clojure.core/map? VAL__19946)
                           (clojure.core/let
                            [VAL__19947 (.valAt VAL__19946 :tag)]
                            (clojure.core/case
                             VAL__19947
                             (:empty)
                             (clojure.core/let
                              [?right input__19495_nth_2__]
                              (clojure.core/let
                               [?env input__19495_nth_3__]
                               (try
                                [(clojure.core/let
                                  [CATA_RESULT__9246__auto__
                                   (CATA__FN__19554
                                    ['meander.dev.parse.zeta/make-cat
                                     ?sequence
                                     ?right
                                     ?env])]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    CATA_RESULT__9246__auto__)
                                   (throw (meander.runtime.zeta/fail))
                                   (clojure.core/nth
                                    CATA_RESULT__9246__auto__
                                    0)))]
                                (catch
                                 java.lang.Exception
                                 e__10186__auto__
                                 (if
                                  (meander.runtime.zeta/fail?
                                   e__10186__auto__)
                                  (meander.runtime.zeta/fail)
                                  (throw e__10186__auto__))))))
                             (state__20765)))
                           (state__20765)))))
                       (:literal)
                       (clojure.core/let
                        [VAL__19951
                         (.valAt input__19495_nth_1__ :type)]
                        (clojure.core/case
                         VAL__19951
                         (:string)
                         (clojure.core/let
                          [VAL__19952
                           (.valAt input__19495_nth_1__ :form)]
                          (clojure.core/let
                           [?form-1 VAL__19952]
                           (if
                            (clojure.core/map? input__19495_nth_2__)
                            (clojure.core/let
                             [VAL__19953
                              (.valAt input__19495_nth_2__ :tag)]
                             (clojure.core/case
                              VAL__19953
                              (:string-join)
                              (clojure.core/let
                               [VAL__19954
                                (.valAt input__19495_nth_2__ :left)]
                               (if
                                (clojure.core/map? VAL__19954)
                                (clojure.core/let
                                 [VAL__19955 (.valAt VAL__19954 :tag)]
                                 (clojure.core/case
                                  VAL__19955
                                  (:literal)
                                  (clojure.core/let
                                   [VAL__19956
                                    (.valAt VAL__19954 :type)]
                                   (clojure.core/case
                                    VAL__19956
                                    (:string)
                                    (clojure.core/let
                                     [VAL__19957
                                      (.valAt VAL__19954 :form)]
                                     (clojure.core/let
                                      [?form-2 VAL__19957]
                                      (clojure.core/let
                                       [VAL__19958
                                        (.valAt
                                         input__19495_nth_2__
                                         :right)]
                                       (clojure.core/let
                                        [?right VAL__19958]
                                        (if
                                         (clojure.core/map?
                                          input__19495_nth_3__)
                                         (clojure.core/let
                                          [VAL__19959
                                           (.valAt
                                            input__19495_nth_3__
                                            :context)]
                                          (clojure.core/case
                                           VAL__19959
                                           (:string)
                                           (clojure.core/let
                                            [?env input__19495_nth_3__]
                                            (try
                                             [(clojure.core/let
                                               [CATA_RESULT__9246__auto__
                                                (CATA__FN__19554
                                                 ['meander.dev.parse.zeta/make-join
                                                  {:tag :literal,
                                                   :type :string,
                                                   :form
                                                   ((clojure.core/partial
                                                     clojure.core/apply
                                                     str)
                                                    [?form-1 ?form-2])}
                                                  ?right
                                                  ?env])]
                                               (if
                                                (meander.runtime.zeta/fail?
                                                 CATA_RESULT__9246__auto__)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 CATA_RESULT__9246__auto__
                                                 0)))]
                                             (catch
                                              java.lang.Exception
                                              e__10186__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__10186__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__10186__auto__)))))
                                           (state__20765)))
                                         (state__20765))))))
                                    (state__20765)))
                                  (state__20765)))
                                (state__20765)))
                              (state__20765)))
                            (state__20765))))
                         (state__20765)))
                       (state__20765)))
                     (state__20765))
                    (state__20765))))
                 (state__20765
                  []
                  (clojure.core/case
                   input__19495_nth_0__
                   (meander.dev.parse.zeta/make-join)
                   (if
                    (clojure.core/map? input__19495_nth_1__)
                    (clojure.core/let
                     [VAL__20663 (.valAt input__19495_nth_1__ :tag)]
                     (clojure.core/case
                      VAL__20663
                      (:cat)
                      (clojure.core/let
                       [?ast input__19495_nth_1__]
                       (if
                        (clojure.core/map? input__19495_nth_2__)
                        (clojure.core/let
                         [VAL__19963
                          (.valAt input__19495_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__19963
                          (:cat)
                          (clojure.core/let
                           [VAL__19964
                            (.valAt input__19495_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__19964]
                            (clojure.core/let
                             [VAL__19965
                              (.valAt input__19495_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__19965]
                              (try
                               [{:tag :cat,
                                 :sequence
                                 (clojure.core/into
                                  []
                                  (clojure.core/concat
                                   (clojure.core/list ?ast)
                                   ?sequence)),
                                 :next ?next}]
                               (catch
                                java.lang.Exception
                                e__10186__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__10186__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__10186__auto__))))))))
                          (state__20766)))
                        (state__20766)))
                      (:string-cat)
                      (clojure.core/let
                       [?ast input__19495_nth_1__]
                       (if
                        (clojure.core/map? input__19495_nth_2__)
                        (clojure.core/let
                         [VAL__19969
                          (.valAt input__19495_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__19969
                          (:string-cat)
                          (clojure.core/let
                           [VAL__19970
                            (.valAt input__19495_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__19970]
                            (clojure.core/let
                             [VAL__19971
                              (.valAt input__19495_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__19971]
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
                                e__10186__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__10186__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__10186__auto__))))))))
                          (state__20766)))
                        (state__20766)))
                      (state__20766)))
                    (state__20766))
                   (state__20766)))
                 (state__20766
                  []
                  (clojure.core/let
                   [input__19495_nth_3__
                    (clojure.core/nth input__19495 3)]
                   (clojure.core/case
                    input__19495_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (clojure.core/letfn
                     [(state__20780
                       []
                       (if
                        (clojure.core/map? input__19495_nth_1__)
                        (clojure.core/let
                         [VAL__20664
                          (.valAt input__19495_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__20664
                          (:string-cat)
                          (clojure.core/let
                           [VAL__19975
                            (.valAt input__19495_nth_1__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__19975]
                            (clojure.core/let
                             [VAL__19976
                              (.valAt input__19495_nth_1__ :next)]
                             (if
                              (clojure.core/map? VAL__19976)
                              (clojure.core/let
                               [VAL__19977 (.valAt VAL__19976 :tag)]
                               (clojure.core/case
                                VAL__19977
                                (:empty)
                                (clojure.core/let
                                 [?right input__19495_nth_2__]
                                 (clojure.core/let
                                  [?env input__19495_nth_3__]
                                  (try
                                   [(clojure.core/let
                                     [CATA_RESULT__9246__auto__
                                      (CATA__FN__19554
                                       ['meander.dev.parse.zeta/make-join
                                        ?sequence
                                        ?right
                                        ?env])]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       CATA_RESULT__9246__auto__)
                                      (throw
                                       (meander.runtime.zeta/fail))
                                      (clojure.core/nth
                                       CATA_RESULT__9246__auto__
                                       0)))]
                                   (catch
                                    java.lang.Exception
                                    e__10186__auto__
                                    (if
                                     (meander.runtime.zeta/fail?
                                      e__10186__auto__)
                                     (meander.runtime.zeta/fail)
                                     (throw e__10186__auto__))))))
                                (state__20781)))
                              (state__20781)))))
                          (:string-star)
                          (clojure.core/let
                           [VAL__19981
                            (.valAt input__19495_nth_1__ :pattern)]
                           (clojure.core/let
                            [?pattern VAL__19981]
                            (clojure.core/let
                             [VAL__19982
                              (.valAt input__19495_nth_1__ :next)]
                             (if
                              (clojure.core/map? VAL__19982)
                              (clojure.core/let
                               [VAL__19983 (.valAt VAL__19982 :tag)]
                               (clojure.core/case
                                VAL__19983
                                (:empty)
                                (clojure.core/let
                                 [?right input__19495_nth_2__]
                                 (if
                                  (clojure.core/map?
                                   input__19495_nth_3__)
                                  (clojure.core/let
                                   [VAL__19984
                                    (.valAt
                                     input__19495_nth_3__
                                     :context)]
                                   (clojure.core/case
                                    VAL__19984
                                    (:string)
                                    (try
                                     [{:tag :string-star,
                                       :pattern ?pattern,
                                       :next ?right}]
                                     (catch
                                      java.lang.Exception
                                      e__10186__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10186__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10186__auto__))))
                                    (state__20781)))
                                  (state__20781)))
                                (state__20781)))
                              (state__20781)))))
                          (:string-join)
                          (clojure.core/let
                           [VAL__19988
                            (.valAt input__19495_nth_1__ :left)]
                           (clojure.core/let
                            [?left VAL__19988]
                            (clojure.core/let
                             [VAL__19989
                              (.valAt input__19495_nth_1__ :right)]
                             (clojure.core/let
                              [?right-1 VAL__19989]
                              (clojure.core/let
                               [?right-2 input__19495_nth_2__]
                               (if
                                (clojure.core/map?
                                 input__19495_nth_3__)
                                (clojure.core/let
                                 [VAL__19990
                                  (.valAt
                                   input__19495_nth_3__
                                   :context)]
                                 (clojure.core/case
                                  VAL__19990
                                  (:string)
                                  (clojure.core/let
                                   [?env input__19495_nth_3__]
                                   (try
                                    [{:tag :string-join,
                                      :left ?left,
                                      :right
                                      (clojure.core/let
                                       [CATA_RESULT__9246__auto__
                                        (CATA__FN__19554
                                         ['meander.dev.parse.zeta/make-join
                                          ?right-1
                                          ?right-2
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9246__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9246__auto__
                                         0)))}]
                                    (catch
                                     java.lang.Exception
                                     e__10186__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10186__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10186__auto__)))))
                                  (state__20781)))
                                (state__20781)))))))
                          (state__20781)))
                        (state__20781)))
                      (state__20781
                       []
                       (clojure.core/let
                        [?left input__19495_nth_1__]
                        (if
                         (clojure.core/map? input__19495_nth_2__)
                         (clojure.core/let
                          [VAL__19993
                           (.valAt input__19495_nth_2__ :tag)]
                          (clojure.core/case
                           VAL__19993
                           (:empty)
                           (clojure.core/let
                            [?env input__19495_nth_3__]
                            (try
                             [?left]
                             (catch
                              java.lang.Exception
                              e__10186__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__10186__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10186__auto__)))))
                           (state__20782)))
                         (state__20782))))
                      (state__20782
                       []
                       (if
                        (clojure.core/map? input__19495_nth_1__)
                        (clojure.core/let
                         [VAL__19996
                          (.valAt input__19495_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__19996
                          (:empty)
                          (clojure.core/let
                           [?right input__19495_nth_2__]
                           (clojure.core/let
                            [?env input__19495_nth_3__]
                            (try
                             [?right]
                             (catch
                              java.lang.Exception
                              e__10186__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__10186__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10186__auto__))))))
                          (state__20783)))
                        (state__20783)))
                      (state__20783
                       []
                       (clojure.core/let
                        [?left input__19495_nth_1__]
                        (clojure.core/let
                         [?right input__19495_nth_2__]
                         (clojure.core/letfn
                          [(state__20784
                            []
                            (if
                             (clojure.core/map? input__19495_nth_3__)
                             (clojure.core/let
                              [VAL__19999
                               (.valAt input__19495_nth_3__ :context)]
                              (clojure.core/case
                               VAL__19999
                               (:string)
                               (try
                                [{:tag :string-join,
                                  :left ?left,
                                  :right ?right}]
                                (catch
                                 java.lang.Exception
                                 e__10186__auto__
                                 (if
                                  (meander.runtime.zeta/fail?
                                   e__10186__auto__)
                                  (meander.runtime.zeta/fail)
                                  (throw e__10186__auto__))))
                               (state__20785)))
                             (state__20785)))
                           (state__20785
                            []
                            (clojure.core/let
                             [?env input__19495_nth_3__]
                             (try
                              [{:tag :join,
                                :left ?left,
                                :right ?right}]
                              (catch
                               java.lang.Exception
                               e__10186__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__10186__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__10186__auto__))))))]
                          (state__20784)))))]
                     (state__20780))
                    (state__20759))))]
                (state__20760)))
              (state__20759)))
            (state__20759
             []
             (if
              (clojure.core/= (clojure.core/count input__19495) 3)
              (clojure.core/let
               [input__19495_nth_0__
                (clojure.core/nth input__19495 0)
                input__19495_nth_1__
                (clojure.core/nth input__19495 1)
                input__19495_nth_2__
                (clojure.core/nth input__19495 2)]
               (clojure.core/case
                input__19495_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__19495_nth_1__)
                 (clojure.core/let
                  [VAL__20004
                   (.valAt input__19495_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__20004]
                   (clojure.core/let
                    [X__20006
                     ((clojure.core/fn
                       [m__7010__auto__]
                       (clojure.core/dissoc
                        m__7010__auto__
                        :meander.zeta/as))
                      input__19495_nth_1__)]
                    (clojure.core/let
                     [?rest X__20006]
                     (clojure.core/letfn
                      [(save__20007 [] (state__20676))
                       (f__20786
                        []
                        (clojure.core/let
                         [?env input__19495_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern
                            (clojure.core/let
                             [CATA_RESULT__9246__auto__
                              (CATA__FN__19554 [?pattern ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__9246__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__9246__auto__
                               0))),
                            :next
                            (clojure.core/let
                             [CATA_RESULT__9246__auto__
                              (CATA__FN__19554 [?rest ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__9246__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__9246__auto__
                               0)))}]
                          (catch
                           java.lang.Exception
                           e__10186__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__10186__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__10186__auto__))))))]
                      (if
                       (clojure.core/= ?rest input__19495_nth_1__)
                       (save__20007)
                       (f__20786)))))))
                 (state__20676))
                (state__20676)))
              (state__20676)))]
           (state__20757))
          (state__20676)))
        (state__20676
         []
         (clojure.core/letfn
          [(def__20010
            [arg__20043 ?ns]
            (clojure.core/letfn
             [(state__20787
               []
               (clojure.core/let
                [x__20044 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__20044)
                 (clojure.core/let [?env arg__20043] [?env ?ns])
                 (state__20788))))
              (state__20788
               []
               (if
                (clojure.core/map? arg__20043)
                (clojure.core/let
                 [VAL__20045 (.valAt arg__20043 :aliases)]
                 (if
                  (clojure.core/map? VAL__20045)
                  (clojure.core/let
                   [X__20047 (clojure.core/set VAL__20045)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__20047))
                    (clojure.core/loop
                     [search_space__20789 (clojure.core/seq X__20047)]
                     (if
                      (clojure.core/seq search_space__20789)
                      (clojure.core/let
                       [elem__20048
                        (clojure.core/first search_space__20789)
                        result__20790
                        (clojure.core/let
                         [elem__20048_nth_0__
                          (clojure.core/nth elem__20048 0)
                          elem__20048_nth_1__
                          (clojure.core/nth elem__20048 1)]
                         (if
                          (clojure.core/symbol? elem__20048_nth_0__)
                          (clojure.core/let
                           [X__20050
                            (clojure.core/name elem__20048_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__20050)
                            (if
                             (clojure.core/symbol? elem__20048_nth_1__)
                             (clojure.core/let
                              [X__20052
                               (clojure.core/name elem__20048_nth_1__)]
                              (clojure.core/case
                               X__20052
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__20043]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__20790)
                        (recur (clojure.core/next search_space__20789))
                        result__20790))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__20787)))]
          (if
           (clojure.core/vector? input__19495)
           (if
            (clojure.core/= (clojure.core/count input__19495) 3)
            (clojure.core/let
             [input__19495_nth_0__
              (clojure.core/nth input__19495 0)
              input__19495_nth_1__
              (clojure.core/nth input__19495 1)
              input__19495_nth_2__
              (clojure.core/nth input__19495 2)]
             (clojure.core/case
              input__19495_nth_0__
              (meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__19495_nth_1__)
               (clojure.core/let
                [X__20015 (clojure.core/set input__19495_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__20015))
                 (clojure.core/loop
                  [search_space__20792 (clojure.core/seq X__20015)]
                  (if
                   (clojure.core/seq search_space__20792)
                   (clojure.core/let
                    [elem__20016
                     (clojure.core/first search_space__20792)
                     result__20793
                     (clojure.core/let
                      [elem__20016_nth_0__
                       (clojure.core/nth elem__20016 0)
                       elem__20016_nth_1__
                       (clojure.core/nth elem__20016 1)]
                      (clojure.core/let
                       [*m__19521 elem__20016_nth_0__]
                       (if
                        (clojure.core/symbol? elem__20016_nth_0__)
                        (clojure.core/let
                         [X__20018
                          (clojure.core/namespace elem__20016_nth_0__)]
                         (clojure.core/let
                          [?ns X__20018]
                          (clojure.core/let
                           [X__20020
                            (clojure.core/name elem__20016_nth_0__)]
                           (if
                            (clojure.core/string? X__20020)
                            (if
                             (clojure.core/re-matches #"&.*" X__20020)
                             (clojure.core/let
                              [?pattern elem__20016_nth_1__]
                              (clojure.core/let
                               [X__20022
                                ((clojure.core/fn
                                  [m__7010__auto__]
                                  (clojure.core/dissoc
                                   m__7010__auto__
                                   *m__19521))
                                 input__19495_nth_1__)]
                               (clojure.core/let
                                [?rest X__20022]
                                (clojure.core/let
                                 [x__7943__auto__
                                  (def__20010
                                   input__19495_nth_2__
                                   ?ns)]
                                 (if
                                  (meander.runtime.zeta/fail?
                                   x__7943__auto__)
                                  (meander.runtime.zeta/fail)
                                  (clojure.core/let
                                   [[?env ?ns] x__7943__auto__]
                                   (try
                                    [{:tag :rest-map,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9246__auto__
                                        (CATA__FN__19554
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9246__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9246__auto__
                                         0))),
                                      :next
                                      (clojure.core/let
                                       [CATA_RESULT__9246__auto__
                                        (CATA__FN__19554
                                         ['meander.dev.parse.zeta/parse-entries
                                          ?rest
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9246__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9246__auto__
                                         0)))}]
                                    (catch
                                     java.lang.Exception
                                     e__10186__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10186__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10186__auto__))))))))))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))))
                        (meander.runtime.zeta/fail))))]
                    (if
                     (meander.runtime.zeta/fail? result__20793)
                     (recur (clojure.core/next search_space__20792))
                     result__20793))
                   (state__20677)))
                 (state__20677)))
               (state__20677))
              (state__20677)))
            (state__20677))
           (state__20677))))
        (state__20677
         []
         (if
          (clojure.core/vector? input__19495)
          (clojure.core/letfn
           [(state__20795
             []
             (if
              (clojure.core/= (clojure.core/count input__19495) 3)
              (clojure.core/let
               [input__19495_nth_0__
                (clojure.core/nth input__19495 0)
                input__19495_nth_1__
                (clojure.core/nth input__19495 1)
                input__19495_nth_2__
                (clojure.core/nth input__19495 2)]
               (clojure.core/case
                input__19495_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (clojure.core/letfn
                 [(state__20797
                   []
                   (if
                    (clojure.core/map? input__19495_nth_1__)
                    (clojure.core/let
                     [X__20066 (clojure.core/set input__19495_nth_1__)]
                     (if
                      (clojure.core/<= 1 (clojure.core/count X__20066))
                      (clojure.core/loop
                       [search_space__20799
                        (clojure.core/seq X__20066)]
                       (if
                        (clojure.core/seq search_space__20799)
                        (clojure.core/let
                         [elem__20067
                          (clojure.core/first search_space__20799)
                          result__20800
                          (clojure.core/let
                           [elem__20067_nth_0__
                            (clojure.core/nth elem__20067 0)
                            elem__20067_nth_1__
                            (clojure.core/nth elem__20067 1)]
                           (clojure.core/let
                            [?key-pattern elem__20067_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__20067_nth_1__]
                             (clojure.core/let
                              [X__20069
                               ((clojure.core/fn
                                 [m__7010__auto__]
                                 (clojure.core/dissoc
                                  m__7010__auto__
                                  ?key-pattern))
                                input__19495_nth_1__)]
                              (clojure.core/let
                               [?rest X__20069]
                               (clojure.core/let
                                [?env input__19495_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__9246__auto__
                                     (CATA__FN__19554
                                      [?key-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__9246__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__9246__auto__
                                      0))),
                                   :val-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__9246__auto__
                                     (CATA__FN__19554
                                      [?val-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__9246__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__9246__auto__
                                      0))),
                                   :next
                                   (clojure.core/let
                                    [CATA_RESULT__9246__auto__
                                     (CATA__FN__19554
                                      ['meander.dev.parse.zeta/parse-entries
                                       ?rest
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__9246__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__9246__auto__
                                      0)))}]
                                 (catch
                                  java.lang.Exception
                                  e__10186__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__10186__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__10186__auto__))))))))))]
                         (if
                          (meander.runtime.zeta/fail? result__20800)
                          (recur
                           (clojure.core/next search_space__20799))
                          result__20800))
                        (state__20798)))
                      (state__20798)))
                    (state__20798)))
                  (state__20798
                   []
                   (if
                    (clojure.core/map? input__19495_nth_1__)
                    (clojure.core/let
                     [?env input__19495_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__10186__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10186__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10186__auto__)))))
                    (state__20796)))]
                 (state__20797))
                (meander.dev.parse.zeta/parse-with-bindings)
                (clojure.core/letfn
                 [(state__20802
                   []
                   (if
                    (clojure.core/vector? input__19495_nth_1__)
                    (clojure.core/case
                     input__19495_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__19495_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__10186__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10186__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10186__auto__)))))
                     (state__20803))
                    (state__20803)))
                  (state__20803
                   []
                   (if
                    (clojure.core/vector? input__19495_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__19495_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__19495_nth_2__]
                      (try
                       [[{:tag :error,
                          :message
                          "meander.zeta/with expects an even number of bindings"}]]
                       (catch
                        java.lang.Exception
                        e__10186__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10186__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10186__auto__)))))
                     (state__20804))
                    (state__20804)))
                  (state__20804
                   []
                   (if
                    (clojure.core/vector? input__19495_nth_1__)
                    (clojure.core/let
                     [input__19495_nth_1___l__
                      (clojure.core/subvec
                       input__19495_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__19495_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__19495_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__19495_nth_1___r__
                        (clojure.core/subvec input__19495_nth_1__ 2)]
                       (clojure.core/let
                        [input__19495_nth_1___l___nth_0__
                         (clojure.core/nth input__19495_nth_1___l__ 0)
                         input__19495_nth_1___l___nth_1__
                         (clojure.core/nth input__19495_nth_1___l__ 1)]
                        (if
                         (clojure.core/symbol?
                          input__19495_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__20083
                           (clojure.core/namespace
                            input__19495_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__20085
                            (clojure.core/name
                             input__19495_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__20085)
                            (if
                             (clojure.core/re-matches #"%.+" X__20085)
                             (clojure.core/let
                              [?symbol
                               input__19495_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__19495_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__19495_nth_1___r__]
                                (clojure.core/let
                                 [?env input__19495_nth_2__]
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
                                        [CATA_RESULT__9246__auto__
                                         (CATA__FN__19554
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9246__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9246__auto__
                                          0)))})
                                     (clojure.core/let
                                      [CATA_RESULT__9246__auto__
                                       (CATA__FN__19554
                                        ['meander.dev.parse.zeta/parse-with-bindings
                                         ?rest
                                         ?env])]
                                      (if
                                       (meander.runtime.zeta/fail?
                                        CATA_RESULT__9246__auto__)
                                       (throw
                                        (meander.runtime.zeta/fail))
                                       (clojure.core/nth
                                        CATA_RESULT__9246__auto__
                                        0)))))]
                                  (catch
                                   java.lang.Exception
                                   e__10186__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__10186__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__10186__auto__))))))))
                             (state__20805))
                            (state__20805))))
                         (state__20805))))
                      (state__20805)))
                    (state__20805)))
                  (state__20805
                   []
                   (if
                    (clojure.core/vector? input__19495_nth_1__)
                    (clojure.core/let
                     [input__19495_nth_1___l__
                      (clojure.core/subvec
                       input__19495_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__19495_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__19495_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__19495_nth_1___r__
                        (clojure.core/subvec input__19495_nth_1__ 2)]
                       (clojure.core/let
                        [input__19495_nth_1___l___nth_0__
                         (clojure.core/nth input__19495_nth_1___l__ 0)
                         input__19495_nth_1___l___nth_1__
                         (clojure.core/nth input__19495_nth_1___l__ 1)]
                        (clojure.core/let
                         [?x input__19495_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__19495_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__19495_nth_1___r__]
                           (clojure.core/let
                            [?env input__19495_nth_2__]
                            (try
                             [[{:tag :error,
                                :message
                                "meander.zeta/with bindings must be an repeating sequence of %name pattern"}]]
                             (catch
                              java.lang.Exception
                              e__10186__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__10186__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10186__auto__))))))))))
                      (state__20796)))
                    (state__20796)))]
                 (state__20802))
                (state__20796)))
              (state__20796)))
            (state__20796
             []
             (if
              (clojure.core/= (clojure.core/count input__19495) 2)
              (clojure.core/let
               [input__19495_nth_0__
                (clojure.core/nth input__19495 0)
                input__19495_nth_1__
                (clojure.core/nth input__19495 1)]
               (if
                (clojure.core/vector? input__19495_nth_0__)
                (clojure.core/let
                 [?sequence input__19495_nth_0__]
                 (clojure.core/let
                  [?form input__19495_nth_0__]
                  (clojure.core/let
                   [?env input__19495_nth_1__]
                   (try
                    [{:tag :vector,
                      :next
                      (clojure.core/let
                       [CATA_RESULT__9246__auto__
                        (CATA__FN__19554
                         ['meander.dev.parse.zeta/parse-sequential
                          ?sequence
                          (clojure.core/let
                           [form__9345__auto__ {:context :vector}]
                           (clojure.core/merge
                            (clojure.core/into {} ?env)
                            form__9345__auto__))])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__9246__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__9246__auto__
                         0))),
                      :form ?form}]
                    (catch
                     java.lang.Exception
                     e__10186__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10186__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10186__auto__)))))))
                (state__20678)))
              (state__20678)))]
           (state__20795))
          (state__20678)))
        (state__20678
         []
         (clojure.core/letfn
          [(def__20095
            [arg__20118 ?__19496]
            (clojure.core/letfn
             [(state__20806
               []
               (clojure.core/let
                [x__20119 "meander.zeta"]
                (if
                 (clojure.core/= ?__19496 x__20119)
                 [?__19496]
                 (state__20807))))
              (state__20807
               []
               (if
                (clojure.core/map? arg__20118)
                (clojure.core/let
                 [VAL__20120 (.valAt arg__20118 :aliases)]
                 (if
                  (clojure.core/map? VAL__20120)
                  (clojure.core/let
                   [X__20122 (clojure.core/set VAL__20120)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__20122))
                    (clojure.core/loop
                     [search_space__20808 (clojure.core/seq X__20122)]
                     (if
                      (clojure.core/seq search_space__20808)
                      (clojure.core/let
                       [elem__20123
                        (clojure.core/first search_space__20808)
                        result__20809
                        (clojure.core/let
                         [elem__20123_nth_0__
                          (clojure.core/nth elem__20123 0)
                          elem__20123_nth_1__
                          (clojure.core/nth elem__20123 1)]
                         (if
                          (clojure.core/symbol? elem__20123_nth_0__)
                          (clojure.core/let
                           [X__20125
                            (clojure.core/name elem__20123_nth_0__)]
                           (if
                            (clojure.core/= ?__19496 X__20125)
                            (if
                             (clojure.core/symbol? elem__20123_nth_1__)
                             (clojure.core/let
                              [X__20127
                               (clojure.core/name elem__20123_nth_1__)]
                              (clojure.core/case
                               X__20127
                               ("meander.zeta")
                               [?__19496]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__20809)
                        (recur (clojure.core/next search_space__20808))
                        result__20809))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__20806)))]
          (if
           (clojure.core/vector? input__19495)
           (if
            (clojure.core/= (clojure.core/count input__19495) 2)
            (clojure.core/let
             [input__19495_nth_0__
              (clojure.core/nth input__19495 0)
              input__19495_nth_1__
              (clojure.core/nth input__19495 1)]
             (if
              (clojure.core/seq? input__19495_nth_0__)
              (clojure.core/let
               [input__19495_nth_0___l__
                (clojure.core/take 1 input__19495_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__19495_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__19495_nth_0___r__
                  (clojure.core/drop 1 input__19495_nth_0__)]
                 (clojure.core/let
                  [input__19495_nth_0___l___nth_0__
                   (clojure.core/nth input__19495_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__19495_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__20105
                     (clojure.core/namespace
                      input__19495_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__19496 X__20105]
                     (clojure.core/let
                      [X__20107
                       (clojure.core/name
                        input__19495_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__20107
                       ("<>")
                       (clojure.core/let
                        [x__7943__auto__
                         (def__20095 input__19495_nth_1__ ?__19496)]
                        (if
                         (meander.runtime.zeta/fail? x__7943__auto__)
                         (state__20679)
                         (clojure.core/let
                          [[?__19496] x__7943__auto__]
                          (if
                           (clojure.core/vector? input__19495)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__19495)
                             2)
                            (clojure.core/let
                             [input__19495_nth_0__
                              (clojure.core/nth input__19495 0)
                              input__19495_nth_1__
                              (clojure.core/nth input__19495 1)]
                             (if
                              (clojure.core/seq? input__19495_nth_0__)
                              (clojure.core/let
                               [input__19495_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__19495_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__19495_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__19495_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__19495_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__19495_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__19495_nth_0__]
                                   (clojure.core/let
                                    [?env input__19495_nth_1__]
                                    (try
                                     [{:tag :group,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__9246__auto__
                                         (CATA__FN__19554
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?patterns)
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9246__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9246__auto__
                                          0)))}]
                                     (catch
                                      java.lang.Exception
                                      e__10186__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10186__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10186__auto__))))))))
                                (state__20679)))
                              (state__20679)))
                            (state__20679))
                           (state__20679)))))
                       (state__20679)))))
                   (state__20679))))
                (state__20679)))
              (state__20679)))
            (state__20679))
           (state__20679))))
        (state__20679
         []
         (clojure.core/letfn
          [(def__20129
            [arg__20152 ?__19497]
            (clojure.core/letfn
             [(state__20811
               []
               (clojure.core/let
                [x__20153 "meander.zeta"]
                (if
                 (clojure.core/= ?__19497 x__20153)
                 [?__19497]
                 (state__20812))))
              (state__20812
               []
               (if
                (clojure.core/map? arg__20152)
                (clojure.core/let
                 [VAL__20154 (.valAt arg__20152 :aliases)]
                 (if
                  (clojure.core/map? VAL__20154)
                  (clojure.core/let
                   [X__20156 (clojure.core/set VAL__20154)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__20156))
                    (clojure.core/loop
                     [search_space__20813 (clojure.core/seq X__20156)]
                     (if
                      (clojure.core/seq search_space__20813)
                      (clojure.core/let
                       [elem__20157
                        (clojure.core/first search_space__20813)
                        result__20814
                        (clojure.core/let
                         [elem__20157_nth_0__
                          (clojure.core/nth elem__20157 0)
                          elem__20157_nth_1__
                          (clojure.core/nth elem__20157 1)]
                         (if
                          (clojure.core/symbol? elem__20157_nth_0__)
                          (clojure.core/let
                           [X__20159
                            (clojure.core/name elem__20157_nth_0__)]
                           (if
                            (clojure.core/= ?__19497 X__20159)
                            (if
                             (clojure.core/symbol? elem__20157_nth_1__)
                             (clojure.core/let
                              [X__20161
                               (clojure.core/name elem__20157_nth_1__)]
                              (clojure.core/case
                               X__20161
                               ("meander.zeta")
                               [?__19497]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__20814)
                        (recur (clojure.core/next search_space__20813))
                        result__20814))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__20811)))]
          (if
           (clojure.core/vector? input__19495)
           (if
            (clojure.core/= (clojure.core/count input__19495) 2)
            (clojure.core/let
             [input__19495_nth_0__
              (clojure.core/nth input__19495 0)
              input__19495_nth_1__
              (clojure.core/nth input__19495 1)]
             (if
              (clojure.core/seq? input__19495_nth_0__)
              (clojure.core/let
               [input__19495_nth_0___l__
                (clojure.core/take 1 input__19495_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__19495_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__19495_nth_0___r__
                  (clojure.core/drop 1 input__19495_nth_0__)]
                 (clojure.core/let
                  [input__19495_nth_0___l___nth_0__
                   (clojure.core/nth input__19495_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__19495_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__20139
                     (clojure.core/namespace
                      input__19495_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__19497 X__20139]
                     (clojure.core/let
                      [X__20141
                       (clojure.core/name
                        input__19495_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__20141
                       ("with")
                       (clojure.core/let
                        [x__7943__auto__
                         (def__20129 input__19495_nth_1__ ?__19497)]
                        (if
                         (meander.runtime.zeta/fail? x__7943__auto__)
                         (state__20680)
                         (clojure.core/let
                          [[?__19497] x__7943__auto__]
                          (if
                           (clojure.core/vector? input__19495)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__19495)
                             2)
                            (clojure.core/let
                             [input__19495_nth_0__
                              (clojure.core/nth input__19495 0)
                              input__19495_nth_1__
                              (clojure.core/nth input__19495 1)]
                             (if
                              (clojure.core/seq? input__19495_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__19495_nth_0__)
                                3)
                               (clojure.core/let
                                [input__19495_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__19495_nth_0__
                                  1)
                                 input__19495_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__19495_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__19495_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__19495_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__19495_nth_0__]
                                   (clojure.core/let
                                    [?env input__19495_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__9246__auto__
                                          (CATA__FN__19554
                                           ['meander.dev.parse.zeta/parse-with-bindings
                                            ?bindings
                                            ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__9246__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__9246__auto__
                                           0)))},
                                       :body
                                       (clojure.core/let
                                        [CATA_RESULT__9246__auto__
                                         (CATA__FN__19554
                                          [?body ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9246__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9246__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10186__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10186__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10186__auto__)))))))))
                               (state__20680))
                              (state__20680)))
                            (state__20680))
                           (state__20680)))))
                       (state__20680)))))
                   (state__20680))))
                (state__20680)))
              (state__20680)))
            (state__20680))
           (state__20680))))
        (state__20680
         []
         (clojure.core/letfn
          [(def__20163
            [arg__20186 ?__19498]
            (clojure.core/letfn
             [(state__20816
               []
               (clojure.core/let
                [x__20187 "meander.zeta"]
                (if
                 (clojure.core/= ?__19498 x__20187)
                 [?__19498]
                 (state__20817))))
              (state__20817
               []
               (if
                (clojure.core/map? arg__20186)
                (clojure.core/let
                 [VAL__20188 (.valAt arg__20186 :aliases)]
                 (if
                  (clojure.core/map? VAL__20188)
                  (clojure.core/let
                   [X__20190 (clojure.core/set VAL__20188)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__20190))
                    (clojure.core/loop
                     [search_space__20818 (clojure.core/seq X__20190)]
                     (if
                      (clojure.core/seq search_space__20818)
                      (clojure.core/let
                       [elem__20191
                        (clojure.core/first search_space__20818)
                        result__20819
                        (clojure.core/let
                         [elem__20191_nth_0__
                          (clojure.core/nth elem__20191 0)
                          elem__20191_nth_1__
                          (clojure.core/nth elem__20191 1)]
                         (if
                          (clojure.core/symbol? elem__20191_nth_0__)
                          (clojure.core/let
                           [X__20193
                            (clojure.core/name elem__20191_nth_0__)]
                           (if
                            (clojure.core/= ?__19498 X__20193)
                            (if
                             (clojure.core/symbol? elem__20191_nth_1__)
                             (clojure.core/let
                              [X__20195
                               (clojure.core/name elem__20191_nth_1__)]
                              (clojure.core/case
                               X__20195
                               ("meander.zeta")
                               [?__19498]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__20819)
                        (recur (clojure.core/next search_space__20818))
                        result__20819))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__20816)))]
          (if
           (clojure.core/vector? input__19495)
           (if
            (clojure.core/= (clojure.core/count input__19495) 2)
            (clojure.core/let
             [input__19495_nth_0__
              (clojure.core/nth input__19495 0)
              input__19495_nth_1__
              (clojure.core/nth input__19495 1)]
             (if
              (clojure.core/seq? input__19495_nth_0__)
              (clojure.core/let
               [input__19495_nth_0___l__
                (clojure.core/take 1 input__19495_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__19495_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__19495_nth_0___r__
                  (clojure.core/drop 1 input__19495_nth_0__)]
                 (clojure.core/let
                  [input__19495_nth_0___l___nth_0__
                   (clojure.core/nth input__19495_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__19495_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__20173
                     (clojure.core/namespace
                      input__19495_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__19498 X__20173]
                     (clojure.core/let
                      [X__20175
                       (clojure.core/name
                        input__19495_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__20175
                       ("apply")
                       (clojure.core/let
                        [x__7943__auto__
                         (def__20163 input__19495_nth_1__ ?__19498)]
                        (if
                         (meander.runtime.zeta/fail? x__7943__auto__)
                         (state__20681)
                         (clojure.core/let
                          [[?__19498] x__7943__auto__]
                          (if
                           (clojure.core/vector? input__19495)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__19495)
                             2)
                            (clojure.core/let
                             [input__19495_nth_0__
                              (clojure.core/nth input__19495 0)
                              input__19495_nth_1__
                              (clojure.core/nth input__19495 1)]
                             (if
                              (clojure.core/seq? input__19495_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__19495_nth_0__)
                                3)
                               (clojure.core/let
                                [input__19495_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__19495_nth_0__
                                  1)
                                 input__19495_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__19495_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__19495_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__19495_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__19495_nth_0__]
                                   (clojure.core/let
                                    [?env input__19495_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__9246__auto__
                                         (CATA__FN__19554
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9246__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9246__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10186__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10186__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10186__auto__)))))))))
                               (state__20681))
                              (state__20681)))
                            (state__20681))
                           (state__20681)))))
                       (state__20681)))))
                   (state__20681))))
                (state__20681)))
              (state__20681)))
            (state__20681))
           (state__20681))))
        (state__20681
         []
         (clojure.core/letfn
          [(def__20197
            [arg__20220 ?__19499]
            (clojure.core/letfn
             [(state__20821
               []
               (clojure.core/let
                [x__20221 "meander.zeta"]
                (if
                 (clojure.core/= ?__19499 x__20221)
                 [?__19499]
                 (state__20822))))
              (state__20822
               []
               (if
                (clojure.core/map? arg__20220)
                (clojure.core/let
                 [VAL__20222 (.valAt arg__20220 :aliases)]
                 (if
                  (clojure.core/map? VAL__20222)
                  (clojure.core/let
                   [X__20224 (clojure.core/set VAL__20222)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__20224))
                    (clojure.core/loop
                     [search_space__20823 (clojure.core/seq X__20224)]
                     (if
                      (clojure.core/seq search_space__20823)
                      (clojure.core/let
                       [elem__20225
                        (clojure.core/first search_space__20823)
                        result__20824
                        (clojure.core/let
                         [elem__20225_nth_0__
                          (clojure.core/nth elem__20225 0)
                          elem__20225_nth_1__
                          (clojure.core/nth elem__20225 1)]
                         (if
                          (clojure.core/symbol? elem__20225_nth_0__)
                          (clojure.core/let
                           [X__20227
                            (clojure.core/name elem__20225_nth_0__)]
                           (if
                            (clojure.core/= ?__19499 X__20227)
                            (if
                             (clojure.core/symbol? elem__20225_nth_1__)
                             (clojure.core/let
                              [X__20229
                               (clojure.core/name elem__20225_nth_1__)]
                              (clojure.core/case
                               X__20229
                               ("meander.zeta")
                               [?__19499]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__20824)
                        (recur (clojure.core/next search_space__20823))
                        result__20824))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__20821)))]
          (if
           (clojure.core/vector? input__19495)
           (if
            (clojure.core/= (clojure.core/count input__19495) 2)
            (clojure.core/let
             [input__19495_nth_0__
              (clojure.core/nth input__19495 0)
              input__19495_nth_1__
              (clojure.core/nth input__19495 1)]
             (if
              (clojure.core/seq? input__19495_nth_0__)
              (clojure.core/let
               [input__19495_nth_0___l__
                (clojure.core/take 1 input__19495_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__19495_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__19495_nth_0___r__
                  (clojure.core/drop 1 input__19495_nth_0__)]
                 (clojure.core/let
                  [input__19495_nth_0___l___nth_0__
                   (clojure.core/nth input__19495_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__19495_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__20207
                     (clojure.core/namespace
                      input__19495_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__19499 X__20207]
                     (clojure.core/let
                      [X__20209
                       (clojure.core/name
                        input__19495_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__20209
                       ("and")
                       (clojure.core/let
                        [x__7943__auto__
                         (def__20197 input__19495_nth_1__ ?__19499)]
                        (if
                         (meander.runtime.zeta/fail? x__7943__auto__)
                         (state__20682)
                         (clojure.core/let
                          [[?__19499] x__7943__auto__]
                          (if
                           (clojure.core/vector? input__19495)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__19495)
                             2)
                            (clojure.core/let
                             [input__19495_nth_0__
                              (clojure.core/nth input__19495 0)
                              input__19495_nth_1__
                              (clojure.core/nth input__19495 1)]
                             (if
                              (clojure.core/seq? input__19495_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__19495_nth_0__)
                                3)
                               (clojure.core/let
                                [input__19495_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__19495_nth_0__
                                  1)
                                 input__19495_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__19495_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__19495_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right input__19495_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__19495_nth_0__]
                                   (clojure.core/let
                                    [?env input__19495_nth_1__]
                                    (try
                                     [{:tag :and,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__9246__auto__
                                         (CATA__FN__19554
                                          [?left ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9246__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9246__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__9246__auto__
                                         (CATA__FN__19554
                                          [?right ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9246__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9246__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10186__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10186__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10186__auto__)))))))))
                               (state__20682))
                              (state__20682)))
                            (state__20682))
                           (state__20682)))))
                       (state__20682)))))
                   (state__20682))))
                (state__20682)))
              (state__20682)))
            (state__20682))
           (state__20682))))
        (state__20682
         []
         (clojure.core/letfn
          [(def__20231
            [arg__20254 ?__19500]
            (clojure.core/letfn
             [(state__20826
               []
               (clojure.core/let
                [x__20255 "meander.zeta"]
                (if
                 (clojure.core/= ?__19500 x__20255)
                 [?__19500]
                 (state__20827))))
              (state__20827
               []
               (if
                (clojure.core/map? arg__20254)
                (clojure.core/let
                 [VAL__20256 (.valAt arg__20254 :aliases)]
                 (if
                  (clojure.core/map? VAL__20256)
                  (clojure.core/let
                   [X__20258 (clojure.core/set VAL__20256)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__20258))
                    (clojure.core/loop
                     [search_space__20828 (clojure.core/seq X__20258)]
                     (if
                      (clojure.core/seq search_space__20828)
                      (clojure.core/let
                       [elem__20259
                        (clojure.core/first search_space__20828)
                        result__20829
                        (clojure.core/let
                         [elem__20259_nth_0__
                          (clojure.core/nth elem__20259 0)
                          elem__20259_nth_1__
                          (clojure.core/nth elem__20259 1)]
                         (if
                          (clojure.core/symbol? elem__20259_nth_0__)
                          (clojure.core/let
                           [X__20261
                            (clojure.core/name elem__20259_nth_0__)]
                           (if
                            (clojure.core/= ?__19500 X__20261)
                            (if
                             (clojure.core/symbol? elem__20259_nth_1__)
                             (clojure.core/let
                              [X__20263
                               (clojure.core/name elem__20259_nth_1__)]
                              (clojure.core/case
                               X__20263
                               ("meander.zeta")
                               [?__19500]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__20829)
                        (recur (clojure.core/next search_space__20828))
                        result__20829))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__20826)))]
          (if
           (clojure.core/vector? input__19495)
           (if
            (clojure.core/= (clojure.core/count input__19495) 2)
            (clojure.core/let
             [input__19495_nth_0__
              (clojure.core/nth input__19495 0)
              input__19495_nth_1__
              (clojure.core/nth input__19495 1)]
             (if
              (clojure.core/seq? input__19495_nth_0__)
              (clojure.core/let
               [input__19495_nth_0___l__
                (clojure.core/take 1 input__19495_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__19495_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__19495_nth_0___r__
                  (clojure.core/drop 1 input__19495_nth_0__)]
                 (clojure.core/let
                  [input__19495_nth_0___l___nth_0__
                   (clojure.core/nth input__19495_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__19495_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__20241
                     (clojure.core/namespace
                      input__19495_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__19500 X__20241]
                     (clojure.core/let
                      [X__20243
                       (clojure.core/name
                        input__19495_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__20243
                       ("cata")
                       (clojure.core/let
                        [x__7943__auto__
                         (def__20231 input__19495_nth_1__ ?__19500)]
                        (if
                         (meander.runtime.zeta/fail? x__7943__auto__)
                         (state__20683)
                         (clojure.core/let
                          [[?__19500] x__7943__auto__]
                          (if
                           (clojure.core/vector? input__19495)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__19495)
                             2)
                            (clojure.core/let
                             [input__19495_nth_0__
                              (clojure.core/nth input__19495 0)
                              input__19495_nth_1__
                              (clojure.core/nth input__19495 1)]
                             (if
                              (clojure.core/seq? input__19495_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__19495_nth_0__)
                                2)
                               (clojure.core/let
                                [input__19495_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__19495_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__19495_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__19495_nth_0__]
                                  (clojure.core/let
                                   [?env input__19495_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9246__auto__
                                        (CATA__FN__19554
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9246__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9246__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__10186__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10186__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10186__auto__))))))))
                               (state__20683))
                              (state__20683)))
                            (state__20683))
                           (state__20683)))))
                       (state__20683)))))
                   (state__20683))))
                (state__20683)))
              (state__20683)))
            (state__20683))
           (state__20683))))
        (state__20683
         []
         (clojure.core/letfn
          [(def__20265
            [arg__20288 ?__19501]
            (clojure.core/letfn
             [(state__20831
               []
               (clojure.core/let
                [x__20289 "meander.zeta"]
                (if
                 (clojure.core/= ?__19501 x__20289)
                 [?__19501]
                 (state__20832))))
              (state__20832
               []
               (if
                (clojure.core/map? arg__20288)
                (clojure.core/let
                 [VAL__20290 (.valAt arg__20288 :aliases)]
                 (if
                  (clojure.core/map? VAL__20290)
                  (clojure.core/let
                   [X__20292 (clojure.core/set VAL__20290)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__20292))
                    (clojure.core/loop
                     [search_space__20833 (clojure.core/seq X__20292)]
                     (if
                      (clojure.core/seq search_space__20833)
                      (clojure.core/let
                       [elem__20293
                        (clojure.core/first search_space__20833)
                        result__20834
                        (clojure.core/let
                         [elem__20293_nth_0__
                          (clojure.core/nth elem__20293 0)
                          elem__20293_nth_1__
                          (clojure.core/nth elem__20293 1)]
                         (if
                          (clojure.core/symbol? elem__20293_nth_0__)
                          (clojure.core/let
                           [X__20295
                            (clojure.core/name elem__20293_nth_0__)]
                           (if
                            (clojure.core/= ?__19501 X__20295)
                            (if
                             (clojure.core/symbol? elem__20293_nth_1__)
                             (clojure.core/let
                              [X__20297
                               (clojure.core/name elem__20293_nth_1__)]
                              (clojure.core/case
                               X__20297
                               ("meander.zeta")
                               [?__19501]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__20834)
                        (recur (clojure.core/next search_space__20833))
                        result__20834))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__20831)))]
          (if
           (clojure.core/vector? input__19495)
           (if
            (clojure.core/= (clojure.core/count input__19495) 2)
            (clojure.core/let
             [input__19495_nth_0__
              (clojure.core/nth input__19495 0)
              input__19495_nth_1__
              (clojure.core/nth input__19495 1)]
             (if
              (clojure.core/seq? input__19495_nth_0__)
              (clojure.core/let
               [input__19495_nth_0___l__
                (clojure.core/take 1 input__19495_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__19495_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__19495_nth_0___r__
                  (clojure.core/drop 1 input__19495_nth_0__)]
                 (clojure.core/let
                  [input__19495_nth_0___l___nth_0__
                   (clojure.core/nth input__19495_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__19495_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__20275
                     (clojure.core/namespace
                      input__19495_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__19501 X__20275]
                     (clojure.core/let
                      [X__20277
                       (clojure.core/name
                        input__19495_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__20277
                       ("fold")
                       (clojure.core/let
                        [x__7943__auto__
                         (def__20265 input__19495_nth_1__ ?__19501)]
                        (if
                         (meander.runtime.zeta/fail? x__7943__auto__)
                         (state__20684)
                         (clojure.core/let
                          [[?__19501] x__7943__auto__]
                          (if
                           (clojure.core/vector? input__19495)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__19495)
                             2)
                            (clojure.core/let
                             [input__19495_nth_0__
                              (clojure.core/nth input__19495 0)
                              input__19495_nth_1__
                              (clojure.core/nth input__19495 1)]
                             (if
                              (clojure.core/seq? input__19495_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__19495_nth_0__)
                                4)
                               (clojure.core/let
                                [input__19495_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__19495_nth_0__
                                  1)
                                 input__19495_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__19495_nth_0__
                                  2)
                                 input__19495_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__19495_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__19495_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__19495_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__19495_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__19495_nth_0__]
                                    (clojure.core/let
                                     [?env input__19495_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__9246__auto__
                                         (CATA__FN__19554
                                          ['meander.dev.parse.zeta/make-fold
                                           (clojure.core/let
                                            [CATA_RESULT__9246__auto__
                                             (CATA__FN__19554
                                              [?mutable-variable
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9246__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9246__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__9246__auto__
                                             (CATA__FN__19554
                                              [?initial-value ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9246__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9246__auto__
                                              0)))
                                           ?fold-function
                                           ?form])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9246__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9246__auto__
                                          0)))]
                                      (catch
                                       java.lang.Exception
                                       e__10186__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__10186__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__10186__auto__))))))))))
                               (state__20684))
                              (state__20684)))
                            (state__20684))
                           (state__20684)))))
                       (state__20684)))))
                   (state__20684))))
                (state__20684)))
              (state__20684)))
            (state__20684))
           (state__20684))))
        (state__20684
         []
         (if
          (clojure.core/vector? input__19495)
          (if
           (clojure.core/= (clojure.core/count input__19495) 5)
           (clojure.core/let
            [input__19495_nth_0__
             (clojure.core/nth input__19495 0)
             input__19495_nth_1__
             (clojure.core/nth input__19495 1)
             input__19495_nth_2__
             (clojure.core/nth input__19495 2)
             input__19495_nth_3__
             (clojure.core/nth input__19495 3)
             input__19495_nth_4__
             (clojure.core/nth input__19495 4)]
            (clojure.core/case
             input__19495_nth_0__
             (meander.dev.parse.zeta/make-fold)
             (if
              (clojure.core/map? input__19495_nth_1__)
              (clojure.core/let
               [VAL__20300 (.valAt input__19495_nth_1__ :tag)]
               (clojure.core/case
                VAL__20300
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__19495_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__19495_nth_2__]
                  (clojure.core/let
                   [?fold-function input__19495_nth_3__]
                   (clojure.core/let
                    [?form input__19495_nth_4__]
                    (try
                     [{:tag :fold,
                       :variable ?variable-ast,
                       :initial-value ?initial-value-ast,
                       :fold-function
                       {:tag :host-expression, :form ?fold-function},
                       :form ?form}]
                     (catch
                      java.lang.Exception
                      e__10186__auto__
                      (if
                       (meander.runtime.zeta/fail? e__10186__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__10186__auto__))))))))
                (state__20685)))
              (state__20685))
             (state__20685)))
           (state__20685))
          (state__20685)))
        (state__20685
         []
         (clojure.core/letfn
          [(def__20302
            [arg__20325 ?__19502]
            (clojure.core/letfn
             [(state__20836
               []
               (clojure.core/let
                [x__20326 "meander.zeta"]
                (if
                 (clojure.core/= ?__19502 x__20326)
                 [?__19502]
                 (state__20837))))
              (state__20837
               []
               (if
                (clojure.core/map? arg__20325)
                (clojure.core/let
                 [VAL__20327 (.valAt arg__20325 :aliases)]
                 (if
                  (clojure.core/map? VAL__20327)
                  (clojure.core/let
                   [X__20329 (clojure.core/set VAL__20327)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__20329))
                    (clojure.core/loop
                     [search_space__20838 (clojure.core/seq X__20329)]
                     (if
                      (clojure.core/seq search_space__20838)
                      (clojure.core/let
                       [elem__20330
                        (clojure.core/first search_space__20838)
                        result__20839
                        (clojure.core/let
                         [elem__20330_nth_0__
                          (clojure.core/nth elem__20330 0)
                          elem__20330_nth_1__
                          (clojure.core/nth elem__20330 1)]
                         (if
                          (clojure.core/symbol? elem__20330_nth_0__)
                          (clojure.core/let
                           [X__20332
                            (clojure.core/name elem__20330_nth_0__)]
                           (if
                            (clojure.core/= ?__19502 X__20332)
                            (if
                             (clojure.core/symbol? elem__20330_nth_1__)
                             (clojure.core/let
                              [X__20334
                               (clojure.core/name elem__20330_nth_1__)]
                              (clojure.core/case
                               X__20334
                               ("meander.zeta")
                               [?__19502]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__20839)
                        (recur (clojure.core/next search_space__20838))
                        result__20839))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__20836)))]
          (if
           (clojure.core/vector? input__19495)
           (if
            (clojure.core/= (clojure.core/count input__19495) 2)
            (clojure.core/let
             [input__19495_nth_0__
              (clojure.core/nth input__19495 0)
              input__19495_nth_1__
              (clojure.core/nth input__19495 1)]
             (if
              (clojure.core/seq? input__19495_nth_0__)
              (clojure.core/let
               [input__19495_nth_0___l__
                (clojure.core/take 1 input__19495_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__19495_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__19495_nth_0___r__
                  (clojure.core/drop 1 input__19495_nth_0__)]
                 (clojure.core/let
                  [input__19495_nth_0___l___nth_0__
                   (clojure.core/nth input__19495_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__19495_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__20312
                     (clojure.core/namespace
                      input__19495_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__19502 X__20312]
                     (clojure.core/let
                      [X__20314
                       (clojure.core/name
                        input__19495_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__20314
                       ("let")
                       (clojure.core/let
                        [x__7943__auto__
                         (def__20302 input__19495_nth_1__ ?__19502)]
                        (if
                         (meander.runtime.zeta/fail? x__7943__auto__)
                         (state__20686)
                         (clojure.core/let
                          [[?__19502] x__7943__auto__]
                          (if
                           (clojure.core/vector? input__19495)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__19495)
                             2)
                            (clojure.core/let
                             [input__19495_nth_0__
                              (clojure.core/nth input__19495 0)
                              input__19495_nth_1__
                              (clojure.core/nth input__19495 1)]
                             (if
                              (clojure.core/seq? input__19495_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__19495_nth_0__)
                                3)
                               (clojure.core/let
                                [input__19495_nth_0___nth_0__
                                 (clojure.core/nth
                                  input__19495_nth_0__
                                  0)
                                 input__19495_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__19495_nth_0__
                                  1)
                                 input__19495_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__19495_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__19495_nth_0___nth_0__]
                                 (clojure.core/let
                                  [?expression
                                   input__19495_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?next input__19495_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?form input__19495_nth_0__]
                                    (clojure.core/let
                                     [?env input__19495_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__9246__auto__
                                          (CATA__FN__19554
                                           [?pattern ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__9246__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__9246__auto__
                                           0))),
                                        :expression
                                        {:tag :host-expression,
                                         :form ?expression},
                                        :next
                                        (clojure.core/let
                                         [CATA_RESULT__9246__auto__
                                          (CATA__FN__19554
                                           [?next ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__9246__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__9246__auto__
                                           0)))}]
                                      (catch
                                       java.lang.Exception
                                       e__10186__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__10186__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__10186__auto__))))))))))
                               (state__20686))
                              (state__20686)))
                            (state__20686))
                           (state__20686)))))
                       (state__20686)))))
                   (state__20686))))
                (state__20686)))
              (state__20686)))
            (state__20686))
           (state__20686))))
        (state__20686
         []
         (clojure.core/letfn
          [(def__20336
            [arg__20359 ?__19503]
            (clojure.core/letfn
             [(state__20841
               []
               (clojure.core/let
                [x__20360 "meander.zeta"]
                (if
                 (clojure.core/= ?__19503 x__20360)
                 [?__19503]
                 (state__20842))))
              (state__20842
               []
               (if
                (clojure.core/map? arg__20359)
                (clojure.core/let
                 [VAL__20361 (.valAt arg__20359 :aliases)]
                 (if
                  (clojure.core/map? VAL__20361)
                  (clojure.core/let
                   [X__20363 (clojure.core/set VAL__20361)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__20363))
                    (clojure.core/loop
                     [search_space__20843 (clojure.core/seq X__20363)]
                     (if
                      (clojure.core/seq search_space__20843)
                      (clojure.core/let
                       [elem__20364
                        (clojure.core/first search_space__20843)
                        result__20844
                        (clojure.core/let
                         [elem__20364_nth_0__
                          (clojure.core/nth elem__20364 0)
                          elem__20364_nth_1__
                          (clojure.core/nth elem__20364 1)]
                         (if
                          (clojure.core/symbol? elem__20364_nth_0__)
                          (clojure.core/let
                           [X__20366
                            (clojure.core/name elem__20364_nth_0__)]
                           (if
                            (clojure.core/= ?__19503 X__20366)
                            (if
                             (clojure.core/symbol? elem__20364_nth_1__)
                             (clojure.core/let
                              [X__20368
                               (clojure.core/name elem__20364_nth_1__)]
                              (clojure.core/case
                               X__20368
                               ("meander.zeta")
                               [?__19503]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__20844)
                        (recur (clojure.core/next search_space__20843))
                        result__20844))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__20841)))]
          (if
           (clojure.core/vector? input__19495)
           (if
            (clojure.core/= (clojure.core/count input__19495) 2)
            (clojure.core/let
             [input__19495_nth_0__
              (clojure.core/nth input__19495 0)
              input__19495_nth_1__
              (clojure.core/nth input__19495 1)]
             (if
              (clojure.core/seq? input__19495_nth_0__)
              (clojure.core/let
               [input__19495_nth_0___l__
                (clojure.core/take 1 input__19495_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__19495_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__19495_nth_0___r__
                  (clojure.core/drop 1 input__19495_nth_0__)]
                 (clojure.core/let
                  [input__19495_nth_0___l___nth_0__
                   (clojure.core/nth input__19495_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__19495_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__20346
                     (clojure.core/namespace
                      input__19495_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__19503 X__20346]
                     (clojure.core/let
                      [X__20348
                       (clojure.core/name
                        input__19495_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__20348
                       ("not")
                       (clojure.core/let
                        [x__7943__auto__
                         (def__20336 input__19495_nth_1__ ?__19503)]
                        (if
                         (meander.runtime.zeta/fail? x__7943__auto__)
                         (state__20687)
                         (clojure.core/let
                          [[?__19503] x__7943__auto__]
                          (if
                           (clojure.core/vector? input__19495)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__19495)
                             2)
                            (clojure.core/let
                             [input__19495_nth_0__
                              (clojure.core/nth input__19495 0)
                              input__19495_nth_1__
                              (clojure.core/nth input__19495 1)]
                             (if
                              (clojure.core/seq? input__19495_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__19495_nth_0__)
                                2)
                               (clojure.core/let
                                [input__19495_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__19495_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__19495_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__19495_nth_0__]
                                  (clojure.core/let
                                   [?env input__19495_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9246__auto__
                                        (CATA__FN__19554
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9246__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9246__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__10186__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10186__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10186__auto__))))))))
                               (state__20687))
                              (state__20687)))
                            (state__20687))
                           (state__20687)))))
                       (state__20687)))))
                   (state__20687))))
                (state__20687)))
              (state__20687)))
            (state__20687))
           (state__20687))))
        (state__20687
         []
         (clojure.core/letfn
          [(def__20370
            [arg__20393 ?__19504]
            (clojure.core/letfn
             [(state__20846
               []
               (clojure.core/let
                [x__20394 "meander.zeta"]
                (if
                 (clojure.core/= ?__19504 x__20394)
                 [?__19504]
                 (state__20847))))
              (state__20847
               []
               (if
                (clojure.core/map? arg__20393)
                (clojure.core/let
                 [VAL__20395 (.valAt arg__20393 :aliases)]
                 (if
                  (clojure.core/map? VAL__20395)
                  (clojure.core/let
                   [X__20397 (clojure.core/set VAL__20395)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__20397))
                    (clojure.core/loop
                     [search_space__20848 (clojure.core/seq X__20397)]
                     (if
                      (clojure.core/seq search_space__20848)
                      (clojure.core/let
                       [elem__20398
                        (clojure.core/first search_space__20848)
                        result__20849
                        (clojure.core/let
                         [elem__20398_nth_0__
                          (clojure.core/nth elem__20398 0)
                          elem__20398_nth_1__
                          (clojure.core/nth elem__20398 1)]
                         (if
                          (clojure.core/symbol? elem__20398_nth_0__)
                          (clojure.core/let
                           [X__20400
                            (clojure.core/name elem__20398_nth_0__)]
                           (if
                            (clojure.core/= ?__19504 X__20400)
                            (if
                             (clojure.core/symbol? elem__20398_nth_1__)
                             (clojure.core/let
                              [X__20402
                               (clojure.core/name elem__20398_nth_1__)]
                              (clojure.core/case
                               X__20402
                               ("meander.zeta")
                               [?__19504]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__20849)
                        (recur (clojure.core/next search_space__20848))
                        result__20849))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__20846)))]
          (if
           (clojure.core/vector? input__19495)
           (if
            (clojure.core/= (clojure.core/count input__19495) 2)
            (clojure.core/let
             [input__19495_nth_0__
              (clojure.core/nth input__19495 0)
              input__19495_nth_1__
              (clojure.core/nth input__19495 1)]
             (if
              (clojure.core/seq? input__19495_nth_0__)
              (clojure.core/let
               [input__19495_nth_0___l__
                (clojure.core/take 1 input__19495_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__19495_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__19495_nth_0___r__
                  (clojure.core/drop 1 input__19495_nth_0__)]
                 (clojure.core/let
                  [input__19495_nth_0___l___nth_0__
                   (clojure.core/nth input__19495_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__19495_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__20380
                     (clojure.core/namespace
                      input__19495_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__19504 X__20380]
                     (clojure.core/let
                      [X__20382
                       (clojure.core/name
                        input__19495_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__20382
                       ("or")
                       (clojure.core/let
                        [x__7943__auto__
                         (def__20370 input__19495_nth_1__ ?__19504)]
                        (if
                         (meander.runtime.zeta/fail? x__7943__auto__)
                         (state__20688)
                         (clojure.core/let
                          [[?__19504] x__7943__auto__]
                          (if
                           (clojure.core/vector? input__19495)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__19495)
                             2)
                            (clojure.core/let
                             [input__19495_nth_0__
                              (clojure.core/nth input__19495 0)
                              input__19495_nth_1__
                              (clojure.core/nth input__19495 1)]
                             (if
                              (clojure.core/seq? input__19495_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__19495_nth_0__)
                                3)
                               (clojure.core/let
                                [input__19495_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__19495_nth_0__
                                  1)
                                 input__19495_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__19495_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__19495_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right input__19495_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__19495_nth_0__]
                                   (clojure.core/let
                                    [?env input__19495_nth_1__]
                                    (try
                                     [{:tag :or,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__9246__auto__
                                         (CATA__FN__19554
                                          [?left ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9246__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9246__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__9246__auto__
                                         (CATA__FN__19554
                                          [?right ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9246__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9246__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10186__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10186__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10186__auto__)))))))))
                               (state__20688))
                              (state__20688)))
                            (state__20688))
                           (state__20688)))))
                       (state__20688)))))
                   (state__20688))))
                (state__20688)))
              (state__20688)))
            (state__20688))
           (state__20688))))
        (state__20688
         []
         (clojure.core/letfn
          [(def__20404
            [arg__20427 ?__19505]
            (clojure.core/letfn
             [(state__20851
               []
               (clojure.core/let
                [x__20428 "meander.zeta"]
                (if
                 (clojure.core/= ?__19505 x__20428)
                 [?__19505]
                 (state__20852))))
              (state__20852
               []
               (if
                (clojure.core/map? arg__20427)
                (clojure.core/let
                 [VAL__20429 (.valAt arg__20427 :aliases)]
                 (if
                  (clojure.core/map? VAL__20429)
                  (clojure.core/let
                   [X__20431 (clojure.core/set VAL__20429)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__20431))
                    (clojure.core/loop
                     [search_space__20853 (clojure.core/seq X__20431)]
                     (if
                      (clojure.core/seq search_space__20853)
                      (clojure.core/let
                       [elem__20432
                        (clojure.core/first search_space__20853)
                        result__20854
                        (clojure.core/let
                         [elem__20432_nth_0__
                          (clojure.core/nth elem__20432 0)
                          elem__20432_nth_1__
                          (clojure.core/nth elem__20432 1)]
                         (if
                          (clojure.core/symbol? elem__20432_nth_0__)
                          (clojure.core/let
                           [X__20434
                            (clojure.core/name elem__20432_nth_0__)]
                           (if
                            (clojure.core/= ?__19505 X__20434)
                            (if
                             (clojure.core/symbol? elem__20432_nth_1__)
                             (clojure.core/let
                              [X__20436
                               (clojure.core/name elem__20432_nth_1__)]
                              (clojure.core/case
                               X__20436
                               ("meander.zeta")
                               [?__19505]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__20854)
                        (recur (clojure.core/next search_space__20853))
                        result__20854))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__20851)))]
          (if
           (clojure.core/vector? input__19495)
           (if
            (clojure.core/= (clojure.core/count input__19495) 2)
            (clojure.core/let
             [input__19495_nth_0__
              (clojure.core/nth input__19495 0)
              input__19495_nth_1__
              (clojure.core/nth input__19495 1)]
             (if
              (clojure.core/seq? input__19495_nth_0__)
              (clojure.core/let
               [input__19495_nth_0___l__
                (clojure.core/take 1 input__19495_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__19495_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__19495_nth_0___r__
                  (clojure.core/drop 1 input__19495_nth_0__)]
                 (clojure.core/let
                  [input__19495_nth_0___l___nth_0__
                   (clojure.core/nth input__19495_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__19495_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__20414
                     (clojure.core/namespace
                      input__19495_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__19505 X__20414]
                     (clojure.core/let
                      [X__20416
                       (clojure.core/name
                        input__19495_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__20416
                       ("re")
                       (clojure.core/let
                        [x__7943__auto__
                         (def__20404 input__19495_nth_1__ ?__19505)]
                        (if
                         (meander.runtime.zeta/fail? x__7943__auto__)
                         (state__20689)
                         (clojure.core/let
                          [[?__19505] x__7943__auto__]
                          (if
                           (clojure.core/vector? input__19495)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__19495)
                             2)
                            (clojure.core/let
                             [input__19495_nth_0__
                              (clojure.core/nth input__19495 0)
                              input__19495_nth_1__
                              (clojure.core/nth input__19495 1)]
                             (if
                              (clojure.core/seq? input__19495_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__19495_nth_0__)
                                2)
                               (clojure.core/let
                                [input__19495_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__19495_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?regex input__19495_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__19495_nth_0__]
                                  (clojure.core/let
                                   [?env input__19495_nth_1__]
                                   (try
                                    [{:tag :regex,
                                      :regex ?regex,
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__10186__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10186__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10186__auto__))))))))
                               (state__20689))
                              (state__20689)))
                            (state__20689))
                           (state__20689)))))
                       (state__20689)))))
                   (state__20689))))
                (state__20689)))
              (state__20689)))
            (state__20689))
           (state__20689))))
        (state__20689
         []
         (clojure.core/letfn
          [(def__20438
            [arg__20461 ?__19506]
            (clojure.core/letfn
             [(state__20856
               []
               (clojure.core/let
                [x__20462 "meander.zeta"]
                (if
                 (clojure.core/= ?__19506 x__20462)
                 [?__19506]
                 (state__20857))))
              (state__20857
               []
               (if
                (clojure.core/map? arg__20461)
                (clojure.core/let
                 [VAL__20463 (.valAt arg__20461 :aliases)]
                 (if
                  (clojure.core/map? VAL__20463)
                  (clojure.core/let
                   [X__20465 (clojure.core/set VAL__20463)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__20465))
                    (clojure.core/loop
                     [search_space__20858 (clojure.core/seq X__20465)]
                     (if
                      (clojure.core/seq search_space__20858)
                      (clojure.core/let
                       [elem__20466
                        (clojure.core/first search_space__20858)
                        result__20859
                        (clojure.core/let
                         [elem__20466_nth_0__
                          (clojure.core/nth elem__20466 0)
                          elem__20466_nth_1__
                          (clojure.core/nth elem__20466 1)]
                         (if
                          (clojure.core/symbol? elem__20466_nth_0__)
                          (clojure.core/let
                           [X__20468
                            (clojure.core/name elem__20466_nth_0__)]
                           (if
                            (clojure.core/= ?__19506 X__20468)
                            (if
                             (clojure.core/symbol? elem__20466_nth_1__)
                             (clojure.core/let
                              [X__20470
                               (clojure.core/name elem__20466_nth_1__)]
                              (clojure.core/case
                               X__20470
                               ("meander.zeta")
                               [?__19506]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__20859)
                        (recur (clojure.core/next search_space__20858))
                        result__20859))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__20856)))]
          (if
           (clojure.core/vector? input__19495)
           (if
            (clojure.core/= (clojure.core/count input__19495) 2)
            (clojure.core/let
             [input__19495_nth_0__
              (clojure.core/nth input__19495 0)
              input__19495_nth_1__
              (clojure.core/nth input__19495 1)]
             (if
              (clojure.core/seq? input__19495_nth_0__)
              (clojure.core/let
               [input__19495_nth_0___l__
                (clojure.core/take 1 input__19495_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__19495_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__19495_nth_0___r__
                  (clojure.core/drop 1 input__19495_nth_0__)]
                 (clojure.core/let
                  [input__19495_nth_0___l___nth_0__
                   (clojure.core/nth input__19495_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__19495_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__20448
                     (clojure.core/namespace
                      input__19495_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__19506 X__20448]
                     (clojure.core/let
                      [X__20450
                       (clojure.core/name
                        input__19495_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__20450
                       ("re")
                       (clojure.core/let
                        [x__7943__auto__
                         (def__20438 input__19495_nth_1__ ?__19506)]
                        (if
                         (meander.runtime.zeta/fail? x__7943__auto__)
                         (state__20690)
                         (clojure.core/let
                          [[?__19506] x__7943__auto__]
                          (if
                           (clojure.core/vector? input__19495)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__19495)
                             2)
                            (clojure.core/let
                             [input__19495_nth_0__
                              (clojure.core/nth input__19495 0)
                              input__19495_nth_1__
                              (clojure.core/nth input__19495 1)]
                             (if
                              (clojure.core/seq? input__19495_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__19495_nth_0__)
                                3)
                               (clojure.core/let
                                [input__19495_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__19495_nth_0__
                                  1)
                                 input__19495_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__19495_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?regex input__19495_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?capture
                                   input__19495_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__19495_nth_0__]
                                   (clojure.core/let
                                    [?env input__19495_nth_1__]
                                    (try
                                     [{:tag :regex,
                                       :regex ?regex,
                                       :capture
                                       (clojure.core/let
                                        [CATA_RESULT__9246__auto__
                                         (CATA__FN__19554
                                          [?capture ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9246__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9246__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10186__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10186__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10186__auto__)))))))))
                               (state__20690))
                              (state__20690)))
                            (state__20690))
                           (state__20690)))))
                       (state__20690)))))
                   (state__20690))))
                (state__20690)))
              (state__20690)))
            (state__20690))
           (state__20690))))
        (state__20690
         []
         (clojure.core/letfn
          [(def__20472
            [arg__20495 ?__19507]
            (clojure.core/letfn
             [(state__20861
               []
               (clojure.core/let
                [x__20496 "meander.zeta"]
                (if
                 (clojure.core/= ?__19507 x__20496)
                 [?__19507]
                 (state__20862))))
              (state__20862
               []
               (if
                (clojure.core/map? arg__20495)
                (clojure.core/let
                 [VAL__20497 (.valAt arg__20495 :aliases)]
                 (if
                  (clojure.core/map? VAL__20497)
                  (clojure.core/let
                   [X__20499 (clojure.core/set VAL__20497)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__20499))
                    (clojure.core/loop
                     [search_space__20863 (clojure.core/seq X__20499)]
                     (if
                      (clojure.core/seq search_space__20863)
                      (clojure.core/let
                       [elem__20500
                        (clojure.core/first search_space__20863)
                        result__20864
                        (clojure.core/let
                         [elem__20500_nth_0__
                          (clojure.core/nth elem__20500 0)
                          elem__20500_nth_1__
                          (clojure.core/nth elem__20500 1)]
                         (if
                          (clojure.core/symbol? elem__20500_nth_0__)
                          (clojure.core/let
                           [X__20502
                            (clojure.core/name elem__20500_nth_0__)]
                           (if
                            (clojure.core/= ?__19507 X__20502)
                            (if
                             (clojure.core/symbol? elem__20500_nth_1__)
                             (clojure.core/let
                              [X__20504
                               (clojure.core/name elem__20500_nth_1__)]
                              (clojure.core/case
                               X__20504
                               ("meander.zeta")
                               [?__19507]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__20864)
                        (recur (clojure.core/next search_space__20863))
                        result__20864))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__20861)))]
          (if
           (clojure.core/vector? input__19495)
           (if
            (clojure.core/= (clojure.core/count input__19495) 2)
            (clojure.core/let
             [input__19495_nth_0__
              (clojure.core/nth input__19495 0)
              input__19495_nth_1__
              (clojure.core/nth input__19495 1)]
             (if
              (clojure.core/seq? input__19495_nth_0__)
              (clojure.core/let
               [input__19495_nth_0___l__
                (clojure.core/take 1 input__19495_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__19495_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__19495_nth_0___r__
                  (clojure.core/drop 1 input__19495_nth_0__)]
                 (clojure.core/let
                  [input__19495_nth_0___l___nth_0__
                   (clojure.core/nth input__19495_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__19495_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__20482
                     (clojure.core/namespace
                      input__19495_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__19507 X__20482]
                     (clojure.core/let
                      [X__20484
                       (clojure.core/name
                        input__19495_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__20484
                       ("string")
                       (clojure.core/let
                        [x__7943__auto__
                         (def__20472 input__19495_nth_1__ ?__19507)]
                        (if
                         (meander.runtime.zeta/fail? x__7943__auto__)
                         (state__20691)
                         (clojure.core/let
                          [[?__19507] x__7943__auto__]
                          (if
                           (clojure.core/vector? input__19495)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__19495)
                             2)
                            (clojure.core/let
                             [input__19495_nth_0__
                              (clojure.core/nth input__19495 0)
                              input__19495_nth_1__
                              (clojure.core/nth input__19495 1)]
                             (if
                              (clojure.core/seq? input__19495_nth_0__)
                              (clojure.core/let
                               [input__19495_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__19495_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__19495_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__19495_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__19495_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__19495_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__19495_nth_0__]
                                   (clojure.core/let
                                    [?env input__19495_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__9246__auto__
                                         (CATA__FN__19554
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?sequence)
                                           (clojure.core/let
                                            [form__9345__auto__
                                             {:context :string}]
                                            (clojure.core/merge
                                             (clojure.core/into
                                              {}
                                              ?env)
                                             form__9345__auto__))])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9246__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9246__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10186__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10186__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10186__auto__))))))))
                                (state__20691)))
                              (state__20691)))
                            (state__20691))
                           (state__20691)))))
                       (state__20691)))))
                   (state__20691))))
                (state__20691)))
              (state__20691)))
            (state__20691))
           (state__20691))))
        (state__20691
         []
         (clojure.core/letfn
          [(def__20506
            [arg__20529 ?__19508]
            (clojure.core/letfn
             [(state__20866
               []
               (clojure.core/let
                [x__20530 "meander.zeta"]
                (if
                 (clojure.core/= ?__19508 x__20530)
                 [?__19508]
                 (state__20867))))
              (state__20867
               []
               (if
                (clojure.core/map? arg__20529)
                (clojure.core/let
                 [VAL__20531 (.valAt arg__20529 :aliases)]
                 (if
                  (clojure.core/map? VAL__20531)
                  (clojure.core/let
                   [X__20533 (clojure.core/set VAL__20531)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__20533))
                    (clojure.core/loop
                     [search_space__20868 (clojure.core/seq X__20533)]
                     (if
                      (clojure.core/seq search_space__20868)
                      (clojure.core/let
                       [elem__20534
                        (clojure.core/first search_space__20868)
                        result__20869
                        (clojure.core/let
                         [elem__20534_nth_0__
                          (clojure.core/nth elem__20534 0)
                          elem__20534_nth_1__
                          (clojure.core/nth elem__20534 1)]
                         (if
                          (clojure.core/symbol? elem__20534_nth_0__)
                          (clojure.core/let
                           [X__20536
                            (clojure.core/name elem__20534_nth_0__)]
                           (if
                            (clojure.core/= ?__19508 X__20536)
                            (if
                             (clojure.core/symbol? elem__20534_nth_1__)
                             (clojure.core/let
                              [X__20538
                               (clojure.core/name elem__20534_nth_1__)]
                              (clojure.core/case
                               X__20538
                               ("meander.zeta")
                               [?__19508]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__20869)
                        (recur (clojure.core/next search_space__20868))
                        result__20869))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__20866)))]
          (if
           (clojure.core/vector? input__19495)
           (if
            (clojure.core/= (clojure.core/count input__19495) 2)
            (clojure.core/let
             [input__19495_nth_0__
              (clojure.core/nth input__19495 0)
              input__19495_nth_1__
              (clojure.core/nth input__19495 1)]
             (if
              (clojure.core/seq? input__19495_nth_0__)
              (clojure.core/let
               [input__19495_nth_0___l__
                (clojure.core/take 1 input__19495_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__19495_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__19495_nth_0___r__
                  (clojure.core/drop 1 input__19495_nth_0__)]
                 (clojure.core/let
                  [input__19495_nth_0___l___nth_0__
                   (clojure.core/nth input__19495_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__19495_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__20516
                     (clojure.core/namespace
                      input__19495_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__19508 X__20516]
                     (clojure.core/let
                      [X__20518
                       (clojure.core/name
                        input__19495_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__20518
                       ("symbol")
                       (clojure.core/let
                        [x__7943__auto__
                         (def__20506 input__19495_nth_1__ ?__19508)]
                        (if
                         (meander.runtime.zeta/fail? x__7943__auto__)
                         (state__20692)
                         (clojure.core/let
                          [[?__19508] x__7943__auto__]
                          (if
                           (clojure.core/vector? input__19495)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__19495)
                             2)
                            (clojure.core/let
                             [input__19495_nth_0__
                              (clojure.core/nth input__19495 0)
                              input__19495_nth_1__
                              (clojure.core/nth input__19495 1)]
                             (if
                              (clojure.core/seq? input__19495_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__19495_nth_0__)
                                2)
                               (clojure.core/let
                                [input__19495_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__19495_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?name input__19495_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__19495_nth_0__]
                                  (clojure.core/let
                                   [?env input__19495_nth_1__]
                                   (try
                                    [{:tag :symbol,
                                      :name
                                      (clojure.core/let
                                       [CATA_RESULT__9246__auto__
                                        (CATA__FN__19554 [?name ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9246__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9246__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__10186__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10186__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10186__auto__))))))))
                               (state__20692))
                              (state__20692)))
                            (state__20692))
                           (state__20692)))))
                       (state__20692)))))
                   (state__20692))))
                (state__20692)))
              (state__20692)))
            (state__20692))
           (state__20692))))
        (state__20692
         []
         (clojure.core/letfn
          [(def__20540
            [arg__20563 ?__19509]
            (clojure.core/letfn
             [(state__20871
               []
               (clojure.core/let
                [x__20564 "meander.zeta"]
                (if
                 (clojure.core/= ?__19509 x__20564)
                 [?__19509]
                 (state__20872))))
              (state__20872
               []
               (if
                (clojure.core/map? arg__20563)
                (clojure.core/let
                 [VAL__20565 (.valAt arg__20563 :aliases)]
                 (if
                  (clojure.core/map? VAL__20565)
                  (clojure.core/let
                   [X__20567 (clojure.core/set VAL__20565)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__20567))
                    (clojure.core/loop
                     [search_space__20873 (clojure.core/seq X__20567)]
                     (if
                      (clojure.core/seq search_space__20873)
                      (clojure.core/let
                       [elem__20568
                        (clojure.core/first search_space__20873)
                        result__20874
                        (clojure.core/let
                         [elem__20568_nth_0__
                          (clojure.core/nth elem__20568 0)
                          elem__20568_nth_1__
                          (clojure.core/nth elem__20568 1)]
                         (if
                          (clojure.core/symbol? elem__20568_nth_0__)
                          (clojure.core/let
                           [X__20570
                            (clojure.core/name elem__20568_nth_0__)]
                           (if
                            (clojure.core/= ?__19509 X__20570)
                            (if
                             (clojure.core/symbol? elem__20568_nth_1__)
                             (clojure.core/let
                              [X__20572
                               (clojure.core/name elem__20568_nth_1__)]
                              (clojure.core/case
                               X__20572
                               ("meander.zeta")
                               [?__19509]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__20874)
                        (recur (clojure.core/next search_space__20873))
                        result__20874))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__20871)))]
          (if
           (clojure.core/vector? input__19495)
           (if
            (clojure.core/= (clojure.core/count input__19495) 2)
            (clojure.core/let
             [input__19495_nth_0__
              (clojure.core/nth input__19495 0)
              input__19495_nth_1__
              (clojure.core/nth input__19495 1)]
             (if
              (clojure.core/seq? input__19495_nth_0__)
              (clojure.core/let
               [input__19495_nth_0___l__
                (clojure.core/take 1 input__19495_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__19495_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__19495_nth_0___r__
                  (clojure.core/drop 1 input__19495_nth_0__)]
                 (clojure.core/let
                  [input__19495_nth_0___l___nth_0__
                   (clojure.core/nth input__19495_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__19495_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__20550
                     (clojure.core/namespace
                      input__19495_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__19509 X__20550]
                     (clojure.core/let
                      [X__20552
                       (clojure.core/name
                        input__19495_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__20552
                       ("symbol")
                       (clojure.core/let
                        [x__7943__auto__
                         (def__20540 input__19495_nth_1__ ?__19509)]
                        (if
                         (meander.runtime.zeta/fail? x__7943__auto__)
                         (state__20693)
                         (clojure.core/let
                          [[?__19509] x__7943__auto__]
                          (if
                           (clojure.core/vector? input__19495)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__19495)
                             2)
                            (clojure.core/let
                             [input__19495_nth_0__
                              (clojure.core/nth input__19495 0)
                              input__19495_nth_1__
                              (clojure.core/nth input__19495 1)]
                             (if
                              (clojure.core/seq? input__19495_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__19495_nth_0__)
                                3)
                               (clojure.core/let
                                [input__19495_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__19495_nth_0__
                                  1)
                                 input__19495_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__19495_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?namespace
                                  input__19495_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?name input__19495_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__19495_nth_0__]
                                   (clojure.core/let
                                    [?env input__19495_nth_1__]
                                    (try
                                     [{:tag :symbol,
                                       :name
                                       (clojure.core/let
                                        [CATA_RESULT__9246__auto__
                                         (CATA__FN__19554
                                          [?name ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9246__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9246__auto__
                                          0))),
                                       :namespace
                                       (clojure.core/let
                                        [CATA_RESULT__9246__auto__
                                         (CATA__FN__19554
                                          [?namespace ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9246__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9246__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10186__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10186__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10186__auto__)))))))))
                               (state__20693))
                              (state__20693)))
                            (state__20693))
                           (state__20693)))))
                       (state__20693)))))
                   (state__20693))))
                (state__20693)))
              (state__20693)))
            (state__20693))
           (state__20693))))
        (state__20693
         []
         (clojure.core/letfn
          [(def__20574
            [arg__20597 ?__19510]
            (clojure.core/letfn
             [(state__20876
               []
               (clojure.core/let
                [x__20598 "meander.zeta"]
                (if
                 (clojure.core/= ?__19510 x__20598)
                 [?__19510]
                 (state__20877))))
              (state__20877
               []
               (if
                (clojure.core/map? arg__20597)
                (clojure.core/let
                 [VAL__20599 (.valAt arg__20597 :aliases)]
                 (if
                  (clojure.core/map? VAL__20599)
                  (clojure.core/let
                   [X__20601 (clojure.core/set VAL__20599)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__20601))
                    (clojure.core/loop
                     [search_space__20878 (clojure.core/seq X__20601)]
                     (if
                      (clojure.core/seq search_space__20878)
                      (clojure.core/let
                       [elem__20602
                        (clojure.core/first search_space__20878)
                        result__20879
                        (clojure.core/let
                         [elem__20602_nth_0__
                          (clojure.core/nth elem__20602 0)
                          elem__20602_nth_1__
                          (clojure.core/nth elem__20602 1)]
                         (if
                          (clojure.core/symbol? elem__20602_nth_0__)
                          (clojure.core/let
                           [X__20604
                            (clojure.core/name elem__20602_nth_0__)]
                           (if
                            (clojure.core/= ?__19510 X__20604)
                            (if
                             (clojure.core/symbol? elem__20602_nth_1__)
                             (clojure.core/let
                              [X__20606
                               (clojure.core/name elem__20602_nth_1__)]
                              (clojure.core/case
                               X__20606
                               ("meander.zeta")
                               [?__19510]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__20879)
                        (recur (clojure.core/next search_space__20878))
                        result__20879))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__20876)))]
          (if
           (clojure.core/vector? input__19495)
           (if
            (clojure.core/= (clojure.core/count input__19495) 2)
            (clojure.core/let
             [input__19495_nth_0__
              (clojure.core/nth input__19495 0)
              input__19495_nth_1__
              (clojure.core/nth input__19495 1)]
             (if
              (clojure.core/seq? input__19495_nth_0__)
              (clojure.core/let
               [input__19495_nth_0___l__
                (clojure.core/take 1 input__19495_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__19495_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__19495_nth_0___r__
                  (clojure.core/drop 1 input__19495_nth_0__)]
                 (clojure.core/let
                  [input__19495_nth_0___l___nth_0__
                   (clojure.core/nth input__19495_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__19495_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__20584
                     (clojure.core/namespace
                      input__19495_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__19510 X__20584]
                     (clojure.core/let
                      [X__20586
                       (clojure.core/name
                        input__19495_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__20586
                       ("symbol")
                       (clojure.core/let
                        [x__7943__auto__
                         (def__20574 input__19495_nth_1__ ?__19510)]
                        (if
                         (meander.runtime.zeta/fail? x__7943__auto__)
                         (state__20694)
                         (clojure.core/let
                          [[?__19510] x__7943__auto__]
                          (if
                           (clojure.core/vector? input__19495)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__19495)
                             2)
                            (clojure.core/let
                             [input__19495_nth_0__
                              (clojure.core/nth input__19495 0)
                              input__19495_nth_1__
                              (clojure.core/nth input__19495 1)]
                             (if
                              (clojure.core/seq? input__19495_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 5)
                                 input__19495_nth_0__)
                                5)
                               (clojure.core/let
                                [input__19495_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__19495_nth_0__
                                  1)
                                 input__19495_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__19495_nth_0__
                                  2)
                                 input__19495_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__19495_nth_0__
                                  3)
                                 input__19495_nth_0___nth_4__
                                 (clojure.core/nth
                                  input__19495_nth_0__
                                  4)]
                                (clojure.core/case
                                 input__19495_nth_0___nth_3__
                                 (:meander.zeta/as)
                                 (clojure.core/let
                                  [?namespace
                                   input__19495_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?name input__19495_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?pattern
                                     input__19495_nth_0___nth_4__]
                                    (clojure.core/let
                                     [?form input__19495_nth_0__]
                                     (clojure.core/let
                                      [?env input__19495_nth_1__]
                                      (try
                                       [{:tag :symbol,
                                         :name
                                         (clojure.core/let
                                          [CATA_RESULT__9246__auto__
                                           (CATA__FN__19554
                                            [?name ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__9246__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__9246__auto__
                                            0))),
                                         :namespace
                                         (clojure.core/let
                                          [CATA_RESULT__9246__auto__
                                           (CATA__FN__19554
                                            [?namespace ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__9246__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__9246__auto__
                                            0))),
                                         :as-pattern
                                         (clojure.core/let
                                          [CATA_RESULT__9246__auto__
                                           (CATA__FN__19554
                                            [?pattern ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__9246__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__9246__auto__
                                            0))),
                                         :form ?form}]
                                       (catch
                                        java.lang.Exception
                                        e__10186__auto__
                                        (if
                                         (meander.runtime.zeta/fail?
                                          e__10186__auto__)
                                         (meander.runtime.zeta/fail)
                                         (throw
                                          e__10186__auto__)))))))))
                                 (state__20694)))
                               (state__20694))
                              (state__20694)))
                            (state__20694))
                           (state__20694)))))
                       (state__20694)))))
                   (state__20694))))
                (state__20694)))
              (state__20694)))
            (state__20694))
           (state__20694))))
        (state__20694
         []
         (if
          (clojure.core/vector? input__19495)
          (if
           (clojure.core/= (clojure.core/count input__19495) 2)
           (clojure.core/let
            [input__19495_nth_0__ (clojure.core/nth input__19495 0)]
            (clojure.core/letfn
             [(state__20881
               []
               (clojure.core/let
                [input__19495_nth_1__
                 (clojure.core/nth input__19495 1)]
                (clojure.core/letfn
                 [(state__20886
                   []
                   (if
                    (clojure.core/seq? input__19495_nth_0__)
                    (clojure.core/let
                     [?sequence input__19495_nth_0__]
                     (clojure.core/let
                      [?env input__19495_nth_1__]
                      (try
                       [{:tag :seq,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__9246__auto__
                           (CATA__FN__19554
                            ['meander.dev.parse.zeta/parse-sequential
                             (clojure.core/into [] ?sequence)
                             (clojure.core/let
                              [form__9345__auto__ {:context :seq}]
                              (clojure.core/merge
                               (clojure.core/into {} ?env)
                               form__9345__auto__))])]
                          (if
                           (meander.runtime.zeta/fail?
                            CATA_RESULT__9246__auto__)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth
                            CATA_RESULT__9246__auto__
                            0))),
                         :form ?sequence}]
                       (catch
                        java.lang.Exception
                        e__10186__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10186__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10186__auto__))))))
                    (state__20887)))
                  (state__20887
                   []
                   (if
                    (clojure.core/map? input__19495_nth_0__)
                    (clojure.core/let
                     [?map input__19495_nth_0__]
                     (clojure.core/let
                      [?env input__19495_nth_1__]
                      (try
                       [{:tag :map,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__9246__auto__
                           (CATA__FN__19554
                            ['meander.dev.parse.zeta/parse-entries
                             ?map
                             ?env])]
                          (if
                           (meander.runtime.zeta/fail?
                            CATA_RESULT__9246__auto__)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth
                            CATA_RESULT__9246__auto__
                            0))),
                         :form ?map}]
                       (catch
                        java.lang.Exception
                        e__10186__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10186__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10186__auto__))))))
                    (state__20882)))]
                 (state__20886))))
              (state__20882
               []
               (if
                (clojure.core/symbol? input__19495_nth_0__)
                (clojure.core/let
                 [X__20616
                  (clojure.core/namespace input__19495_nth_0__)]
                 (clojure.core/let
                  [X__20618 (clojure.core/name input__19495_nth_0__)]
                  (if
                   (clojure.core/string? X__20618)
                   (clojure.core/letfn
                    [(state__20888
                      []
                      (clojure.core/let
                       [ret__20619
                        (clojure.core/re-matches #"_.*" X__20618)]
                       (if
                        (clojure.core/some? ret__20619)
                        (clojure.core/let
                         [?name ret__20619]
                         (clojure.core/let
                          [?symbol input__19495_nth_0__]
                          (try
                           [{:tag :wildcard,
                             :name ?name,
                             :form ?symbol,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__10186__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__10186__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10186__auto__))))))
                        (state__20889))))
                     (state__20889
                      []
                      (clojure.core/let
                       [ret__20626
                        (clojure.core/re-matches #".+#" X__20618)]
                       (if
                        (clojure.core/some? ret__20626)
                        (clojure.core/let
                         [?name ret__20626]
                         (clojure.core/let
                          [?symbol input__19495_nth_0__]
                          (try
                           [{:tag :random-symbol,
                             :name ?name,
                             :form ?symbol,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__10186__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__10186__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10186__auto__))))))
                        (state__20890))))
                     (state__20890
                      []
                      (clojure.core/let
                       [ret__20633
                        (clojure.core/re-matches #"%.+" X__20618)]
                       (if
                        (clojure.core/some? ret__20633)
                        (clojure.core/let
                         [?name ret__20633]
                         (clojure.core/let
                          [?symbol input__19495_nth_0__]
                          (try
                           [{:tag :reference,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__10186__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__10186__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10186__auto__))))))
                        (state__20891))))
                     (state__20891
                      []
                      (clojure.core/let
                       [ret__20640
                        (clojure.core/re-matches #"\*.+" X__20618)]
                       (if
                        (clojure.core/some? ret__20640)
                        (clojure.core/let
                         [?name ret__20640]
                         (clojure.core/let
                          [?symbol input__19495_nth_0__]
                          (try
                           [{:tag :mutable-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__10186__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__10186__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10186__auto__))))))
                        (state__20892))))
                     (state__20892
                      []
                      (clojure.core/let
                       [ret__20647
                        (clojure.core/re-matches #"\!.+" X__20618)]
                       (if
                        (clojure.core/some? ret__20647)
                        (clojure.core/let
                         [?name ret__20647]
                         (clojure.core/let
                          [?symbol input__19495_nth_0__]
                          (try
                           [{:tag :memory-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__10186__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__10186__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10186__auto__))))))
                        (state__20893))))
                     (state__20893
                      []
                      (clojure.core/let
                       [ret__20654
                        (clojure.core/re-matches #"\?.+" X__20618)]
                       (if
                        (clojure.core/some? ret__20654)
                        (clojure.core/let
                         [?name ret__20654]
                         (clojure.core/let
                          [?symbol input__19495_nth_0__]
                          (try
                           [{:tag :logic-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__10186__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__10186__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10186__auto__))))))
                        (state__20883))))]
                    (state__20888))
                   (state__20883))))
                (state__20883)))
              (state__20883
               []
               (if
                (string? input__19495_nth_0__)
                (clojure.core/let
                 [?x input__19495_nth_0__]
                 (try
                  [{:tag :literal, :type :string, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__10186__auto__
                   (if
                    (meander.runtime.zeta/fail? e__10186__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__10186__auto__)))))
                (state__20884)))
              (state__20884
               []
               (if
                (char? input__19495_nth_0__)
                (clojure.core/let
                 [?x input__19495_nth_0__]
                 (try
                  [{:tag :literal, :type :char, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__10186__auto__
                   (if
                    (meander.runtime.zeta/fail? e__10186__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__10186__auto__)))))
                (state__20885)))
              (state__20885
               []
               (clojure.core/let
                [?x input__19495_nth_0__]
                (try
                 [{:tag :literal, :form ?x}]
                 (catch
                  java.lang.Exception
                  e__10186__auto__
                  (if
                   (meander.runtime.zeta/fail? e__10186__auto__)
                   (meander.runtime.zeta/fail)
                   (throw e__10186__auto__))))))]
             (state__20881)))
           (state__20695))
          (state__20695)))
        (state__20695
         []
         (clojure.core/let
          [?x input__19495]
          (try
           [{:tag :mistake, :x ?x}]
           (catch
            java.lang.Exception
            e__10186__auto__
            (if
             (meander.runtime.zeta/fail? e__10186__auto__)
             (meander.runtime.zeta/fail)
             (throw e__10186__auto__))))))]
       (state__20666)))]
    (clojure.core/let
     [x__7943__auto__ (CATA__FN__19554 input__19495)]
     (if
      (meander.runtime.zeta/fail? x__7943__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__19556] x__7943__auto__]
       CATA_RETURN__19556))))]
  (if
   (meander.runtime.zeta/fail? ret__8123__auto__)
   nil
   ret__8123__auto__)))
