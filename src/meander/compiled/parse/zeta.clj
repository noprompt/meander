(ns meander.compiled.parse.zeta (:require [meander.runtime.zeta]))
(clojure.core/defn
 parse
 [input__13581]
 (let*
  [ret__8100__auto__
   (clojure.core/letfn
    [(CATA__FN__13640
      [input__13581]
      (clojure.core/letfn
       [(state__14752
         []
         (if
          (clojure.core/vector? input__13581)
          (if
           (clojure.core/= (clojure.core/count input__13581) 3)
           (clojure.core/let
            [input__13581_nth_0__
             (clojure.core/nth input__13581 0)
             input__13581_nth_1__
             (clojure.core/nth input__13581 1)
             input__13581_nth_2__
             (clojure.core/nth input__13581 2)]
            (clojure.core/case
             input__13581_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__13581_nth_1__)
              (clojure.core/letfn
               [(state__14782
                 []
                 (clojure.core/case
                  input__13581_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__13581_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__10163__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10163__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10163__auto__)))))
                  (state__14783)))
                (state__14783
                 []
                 (clojure.core/let
                  [n__13647
                   (clojure.core/count input__13581_nth_1__)
                   m__13648
                   (clojure.core/max 0 (clojure.core/- n__13647 2))
                   input__13581_nth_1___l__
                   (clojure.core/subvec
                    input__13581_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__13581_nth_1__)
                     m__13648))
                   input__13581_nth_1___r__
                   (clojure.core/subvec input__13581_nth_1__ m__13648)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__13581_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__13581_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__13581_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__13581_nth_1___r___nth_0__
                       (clojure.core/nth input__13581_nth_1___r__ 0)
                       input__13581_nth_1___r___nth_1__
                       (clojure.core/nth input__13581_nth_1___r__ 1)]
                      (clojure.core/case
                       input__13581_nth_1___r___nth_0__
                       (:meander.zeta/as)
                       (clojure.core/let
                        [?pattern input__13581_nth_1___r___nth_1__]
                        (clojure.core/let
                         [?env input__13581_nth_2__]
                         (try
                          [(clojure.core/let
                            [!xs__counter
                             (meander.runtime.zeta/iterator !xs)]
                            {:tag :as,
                             :pattern
                             (clojure.core/let
                              [CATA_RESULT__9223__auto__
                               (CATA__FN__13640 [?pattern ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__9223__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__9223__auto__
                                0))),
                             :next
                             (clojure.core/let
                              [CATA_RESULT__9223__auto__
                               (CATA__FN__13640
                                ['meander.dev.parse.zeta/parse-sequential
                                 (clojure.core/into
                                  []
                                  (clojure.core/vec
                                   (clojure.core/iterator-seq
                                    !xs__counter)))
                                 ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__9223__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__9223__auto__
                                0)))})]
                          (catch
                           java.lang.Exception
                           e__10163__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__10163__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__10163__auto__))))))
                       (state__14753)))
                     (state__14753)))
                   (state__14753))))]
               (state__14782))
              (state__14753))
             (state__14753)))
           (state__14753))
          (state__14753)))
        (state__14753
         []
         (clojure.core/letfn
          [(def__13653
            [arg__13688 ?ns]
            (clojure.core/letfn
             [(state__14784
               []
               (clojure.core/let
                [x__13689 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__13689)
                 (clojure.core/let [?env arg__13688] [?env ?ns])
                 (state__14785))))
              (state__14785
               []
               (if
                (clojure.core/map? arg__13688)
                (clojure.core/let
                 [VAL__13690 (.valAt arg__13688 :aliases)]
                 (if
                  (clojure.core/map? VAL__13690)
                  (clojure.core/let
                   [X__13692 (clojure.core/set VAL__13690)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__13692))
                    (clojure.core/loop
                     [search_space__14786 (clojure.core/seq X__13692)]
                     (if
                      (clojure.core/seq search_space__14786)
                      (clojure.core/let
                       [elem__13693
                        (clojure.core/first search_space__14786)
                        result__14787
                        (clojure.core/let
                         [elem__13693_nth_0__
                          (clojure.core/nth elem__13693 0)
                          elem__13693_nth_1__
                          (clojure.core/nth elem__13693 1)]
                         (if
                          (clojure.core/symbol? elem__13693_nth_0__)
                          (clojure.core/let
                           [X__13695
                            (clojure.core/name elem__13693_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__13695)
                            (if
                             (clojure.core/symbol? elem__13693_nth_1__)
                             (clojure.core/let
                              [X__13697
                               (clojure.core/name elem__13693_nth_1__)]
                              (clojure.core/case
                               X__13697
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__13688]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__14787)
                        (recur (clojure.core/next search_space__14786))
                        result__14787))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__14784)))]
          (if
           (clojure.core/vector? input__13581)
           (if
            (clojure.core/= (clojure.core/count input__13581) 3)
            (clojure.core/let
             [input__13581_nth_0__
              (clojure.core/nth input__13581 0)
              input__13581_nth_1__
              (clojure.core/nth input__13581 1)
              input__13581_nth_2__
              (clojure.core/nth input__13581 2)]
             (clojure.core/case
              input__13581_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__13581_nth_1__)
               (clojure.core/loop
                [search_space__14789
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__13581_nth_1__)]
                (if
                 (clojure.core/seq search_space__14789)
                 (clojure.core/let
                  [input__13581_nth_1___parts__
                   (clojure.core/first search_space__14789)
                   result__14790
                   (clojure.core/let
                    [input__13581_nth_1___l__
                     (clojure.core/nth input__13581_nth_1___parts__ 0)
                     input__13581_nth_1___r__
                     (clojure.core/nth input__13581_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__13581_nth_1___l__)]
                     (clojure.core/let
                      [input__13581_nth_1___r___l__
                       (clojure.core/subvec
                        input__13581_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__13581_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__13581_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__13581_nth_1___r___r__
                         (clojure.core/subvec
                          input__13581_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__13581_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__13581_nth_1___r___l__
                           0)
                          input__13581_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__13581_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__13581_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__13662
                            (clojure.core/namespace
                             input__13581_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__13662]
                            (clojure.core/let
                             [X__13664
                              (clojure.core/name
                               input__13581_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__13664)
                              (clojure.core/let
                               [ret__13665
                                (clojure.core/re-matches
                                 #"&(\d+)"
                                 X__13664)]
                               (if
                                (clojure.core/some? ret__13665)
                                (if
                                 (clojure.core/vector? ret__13665)
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count ret__13665)
                                   2)
                                  (clojure.core/let
                                   [ret__13665_nth_1__
                                    (clojure.core/nth ret__13665 1)]
                                   (clojure.core/let
                                    [?n ret__13665_nth_1__]
                                    (clojure.core/let
                                     [?pattern
                                      input__13581_nth_1___r___l___nth_1__]
                                     (clojure.core/let
                                      [?rest
                                       input__13581_nth_1___r___r__]
                                      (clojure.core/let
                                       [x__7920__auto__
                                        (def__13653
                                         input__13581_nth_2__
                                         ?ns)]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         x__7920__auto__)
                                        (meander.runtime.zeta/fail)
                                        (clojure.core/let
                                         [[?env ?ns] x__7920__auto__]
                                         (try
                                          [(clojure.core/let
                                            [!init__counter
                                             (meander.runtime.zeta/iterator
                                              !init)]
                                            (clojure.core/let
                                             [CATA_RESULT__9223__auto__
                                              (CATA__FN__13640
                                               ['meander.dev.parse.zeta/make-join
                                                (clojure.core/let
                                                 [CATA_RESULT__9223__auto__
                                                  (CATA__FN__13640
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !init__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9223__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9223__auto__
                                                   0)))
                                                (clojure.core/let
                                                 [CATA_RESULT__9223__auto__
                                                  (CATA__FN__13640
                                                   ['meander.dev.parse.zeta/make-join
                                                    {:tag :slice,
                                                     :size
                                                     (Integer. ?n),
                                                     :pattern
                                                     (clojure.core/let
                                                      [CATA_RESULT__9223__auto__
                                                       (CATA__FN__13640
                                                        [?pattern
                                                         ?env])]
                                                      (if
                                                       (meander.runtime.zeta/fail?
                                                        CATA_RESULT__9223__auto__)
                                                       (throw
                                                        (meander.runtime.zeta/fail))
                                                       (clojure.core/nth
                                                        CATA_RESULT__9223__auto__
                                                        0)))}
                                                    (clojure.core/let
                                                     [CATA_RESULT__9223__auto__
                                                      (CATA__FN__13640
                                                       ['meander.dev.parse.zeta/parse-sequential
                                                        (clojure.core/into
                                                         []
                                                         ?rest)
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__9223__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__9223__auto__
                                                       0)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9223__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9223__auto__
                                                   0)))
                                                ?env])]
                                             (if
                                              (meander.runtime.zeta/fail?
                                               CATA_RESULT__9223__auto__)
                                              (throw
                                               (meander.runtime.zeta/fail))
                                              (clojure.core/nth
                                               CATA_RESULT__9223__auto__
                                               0))))]
                                          (catch
                                           java.lang.Exception
                                           e__10163__auto__
                                           (if
                                            (meander.runtime.zeta/fail?
                                             e__10163__auto__)
                                            (meander.runtime.zeta/fail)
                                            (throw
                                             e__10163__auto__)))))))))))
                                  (meander.runtime.zeta/fail))
                                 (meander.runtime.zeta/fail))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__14790)
                   (recur (clojure.core/next search_space__14789))
                   result__14790))
                 (state__14754)))
               (state__14754))
              (state__14754)))
            (state__14754))
           (state__14754))))
        (state__14754
         []
         (clojure.core/letfn
          [(def__13710
            [arg__13742 ?ns]
            (clojure.core/letfn
             [(state__14792
               []
               (clojure.core/let
                [x__13743 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__13743)
                 (clojure.core/let [?env arg__13742] [?env ?ns])
                 (state__14793))))
              (state__14793
               []
               (if
                (clojure.core/map? arg__13742)
                (clojure.core/let
                 [VAL__13744 (.valAt arg__13742 :aliases)]
                 (if
                  (clojure.core/map? VAL__13744)
                  (clojure.core/let
                   [X__13746 (clojure.core/set VAL__13744)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__13746))
                    (clojure.core/loop
                     [search_space__14794 (clojure.core/seq X__13746)]
                     (if
                      (clojure.core/seq search_space__14794)
                      (clojure.core/let
                       [elem__13747
                        (clojure.core/first search_space__14794)
                        result__14795
                        (clojure.core/let
                         [elem__13747_nth_0__
                          (clojure.core/nth elem__13747 0)
                          elem__13747_nth_1__
                          (clojure.core/nth elem__13747 1)]
                         (if
                          (clojure.core/symbol? elem__13747_nth_0__)
                          (clojure.core/let
                           [X__13749
                            (clojure.core/name elem__13747_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__13749)
                            (if
                             (clojure.core/symbol? elem__13747_nth_1__)
                             (clojure.core/let
                              [X__13751
                               (clojure.core/name elem__13747_nth_1__)]
                              (clojure.core/case
                               X__13751
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__13742]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__14795)
                        (recur (clojure.core/next search_space__14794))
                        result__14795))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__14792)))]
          (if
           (clojure.core/vector? input__13581)
           (if
            (clojure.core/= (clojure.core/count input__13581) 3)
            (clojure.core/let
             [input__13581_nth_0__
              (clojure.core/nth input__13581 0)
              input__13581_nth_1__
              (clojure.core/nth input__13581 1)
              input__13581_nth_2__
              (clojure.core/nth input__13581 2)]
             (clojure.core/case
              input__13581_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__13581_nth_1__)
               (clojure.core/loop
                [search_space__14797
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__13581_nth_1__)]
                (if
                 (clojure.core/seq search_space__14797)
                 (clojure.core/let
                  [input__13581_nth_1___parts__
                   (clojure.core/first search_space__14797)
                   result__14798
                   (clojure.core/let
                    [input__13581_nth_1___l__
                     (clojure.core/nth input__13581_nth_1___parts__ 0)
                     input__13581_nth_1___r__
                     (clojure.core/nth input__13581_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__13581_nth_1___l__)]
                     (clojure.core/let
                      [input__13581_nth_1___r___l__
                       (clojure.core/subvec
                        input__13581_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__13581_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__13581_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__13581_nth_1___r___r__
                         (clojure.core/subvec
                          input__13581_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__13581_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__13581_nth_1___r___l__
                           0)
                          input__13581_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__13581_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__13581_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__13719
                            (clojure.core/namespace
                             input__13581_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__13719]
                            (clojure.core/let
                             [X__13721
                              (clojure.core/name
                               input__13581_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__13721)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__13721)
                               (clojure.core/let
                                [?pattern
                                 input__13581_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__13581_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__7920__auto__
                                   (def__13710
                                    input__13581_nth_2__
                                    ?ns)]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    x__7920__auto__)
                                   (meander.runtime.zeta/fail)
                                   (clojure.core/let
                                    [[?env ?ns] x__7920__auto__]
                                    (try
                                     [(clojure.core/let
                                       [!init__counter
                                        (meander.runtime.zeta/iterator
                                         !init)]
                                       (clojure.core/let
                                        [CATA_RESULT__9223__auto__
                                         (CATA__FN__13640
                                          ['meander.dev.parse.zeta/make-join
                                           (clojure.core/let
                                            [CATA_RESULT__9223__auto__
                                             (CATA__FN__13640
                                              ['meander.dev.parse.zeta/parse-sequential
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !init__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9223__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9223__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__9223__auto__
                                             (CATA__FN__13640
                                              ['meander.dev.parse.zeta/make-join
                                               (clojure.core/let
                                                [CATA_RESULT__9223__auto__
                                                 (CATA__FN__13640
                                                  [?pattern ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__9223__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__9223__auto__
                                                  0)))
                                               (clojure.core/let
                                                [CATA_RESULT__9223__auto__
                                                 (CATA__FN__13640
                                                  ['meander.dev.parse.zeta/parse-sequential
                                                   (clojure.core/into
                                                    []
                                                    ?rest)
                                                   ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__9223__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__9223__auto__
                                                  0)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9223__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9223__auto__
                                              0)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9223__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9223__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__10163__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10163__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10163__auto__)))))))))
                               (meander.runtime.zeta/fail))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__14798)
                   (recur (clojure.core/next search_space__14797))
                   result__14798))
                 (state__14755)))
               (state__14755))
              (state__14755)))
            (state__14755))
           (state__14755))))
        (state__14755
         []
         (if
          (clojure.core/vector? input__13581)
          (clojure.core/letfn
           [(state__14800
             []
             (if
              (clojure.core/= (clojure.core/count input__13581) 3)
              (clojure.core/let
               [input__13581_nth_0__
                (clojure.core/nth input__13581 0)
                input__13581_nth_1__
                (clojure.core/nth input__13581 1)
                input__13581_nth_2__
                (clojure.core/nth input__13581 2)]
               (clojure.core/case
                input__13581_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__13581_nth_1__)
                 (clojure.core/letfn
                  [(state__14803
                    []
                    (clojure.core/let
                     [n__13772
                      (clojure.core/count input__13581_nth_1__)
                      m__13773
                      (clojure.core/max 0 (clojure.core/- n__13772 2))
                      input__13581_nth_1___l__
                      (clojure.core/subvec
                       input__13581_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__13581_nth_1__)
                        m__13773))
                      input__13581_nth_1___r__
                      (clojure.core/subvec
                       input__13581_nth_1__
                       m__13773)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__13581_nth_1___r__)
                       2)
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__13581_nth_1___l__)]
                       (if
                        (clojure.core/=
                         (clojure.core/count input__13581_nth_1___r__)
                         2)
                        (clojure.core/let
                         [input__13581_nth_1___r___nth_0__
                          (clojure.core/nth input__13581_nth_1___r__ 0)
                          input__13581_nth_1___r___nth_1__
                          (clojure.core/nth
                           input__13581_nth_1___r__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__13581_nth_1___r___nth_0__)
                          (clojure.core/let
                           [X__13777
                            (clojure.core/namespace
                             input__13581_nth_1___r___nth_0__)]
                           (clojure.core/let
                            [?ns X__13777]
                            (clojure.core/let
                             [X__13779
                              (clojure.core/name
                               input__13581_nth_1___r___nth_0__)]
                             (if
                              (clojure.core/string? X__13779)
                              (clojure.core/let
                               [ret__13780
                                (clojure.core/re-matches
                                 #"&.*"
                                 X__13779)]
                               (if
                                (clojure.core/some? ret__13780)
                                (clojure.core/let
                                 [?name ret__13780]
                                 (clojure.core/let
                                  [?pattern
                                   input__13581_nth_1___r___nth_1__]
                                  (if
                                   (clojure.core/map?
                                    input__13581_nth_2__)
                                   (clojure.core/let
                                    [VAL__13764
                                     (.valAt
                                      input__13581_nth_2__
                                      :aliases)]
                                    (if
                                     (clojure.core/map? VAL__13764)
                                     (clojure.core/let
                                      [X__13766
                                       (clojure.core/set VAL__13764)]
                                      (if
                                       (clojure.core/<=
                                        1
                                        (clojure.core/count X__13766))
                                       (clojure.core/loop
                                        [search_space__14807
                                         (clojure.core/seq X__13766)]
                                        (if
                                         (clojure.core/seq
                                          search_space__14807)
                                         (clojure.core/let
                                          [elem__13767
                                           (clojure.core/first
                                            search_space__14807)
                                           result__14808
                                           (clojure.core/let
                                            [elem__13767_nth_0__
                                             (clojure.core/nth
                                              elem__13767
                                              0)
                                             elem__13767_nth_1__
                                             (clojure.core/nth
                                              elem__13767
                                              1)]
                                            (if
                                             (clojure.core/symbol?
                                              elem__13767_nth_0__)
                                             (clojure.core/let
                                              [X__13769
                                               (clojure.core/name
                                                elem__13767_nth_0__)]
                                              (if
                                               (clojure.core/=
                                                ?ns
                                                X__13769)
                                               (if
                                                (clojure.core/symbol?
                                                 elem__13767_nth_1__)
                                                (clojure.core/let
                                                 [X__13771
                                                  (clojure.core/name
                                                   elem__13767_nth_1__)]
                                                 (clojure.core/case
                                                  X__13771
                                                  ("meander.zeta")
                                                  (clojure.core/let
                                                   [?env
                                                    input__13581_nth_2__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__9223__auto__
                                                        (CATA__FN__13640
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
                                                         CATA_RESULT__9223__auto__)
                                                        (throw
                                                         (meander.runtime.zeta/fail))
                                                        (clojure.core/nth
                                                         CATA_RESULT__9223__auto__
                                                         0))))]
                                                    (catch
                                                     java.lang.Exception
                                                     e__10163__auto__
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       e__10163__auto__)
                                                      (meander.runtime.zeta/fail)
                                                      (throw
                                                       e__10163__auto__)))))
                                                  (meander.runtime.zeta/fail)))
                                                (meander.runtime.zeta/fail))
                                               (meander.runtime.zeta/fail)))
                                             (meander.runtime.zeta/fail)))]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            result__14808)
                                           (recur
                                            (clojure.core/next
                                             search_space__14807))
                                           result__14808))
                                         (state__14804)))
                                       (state__14804)))
                                     (state__14804)))
                                   (state__14804))))
                                (state__14804)))
                              (state__14804)))))
                          (state__14804)))
                        (state__14804)))
                      (state__14804))))
                   (state__14804
                    []
                    (clojure.core/loop
                     [search_space__14810
                      (meander.match.runtime.epsilon/partitions
                       2
                       input__13581_nth_1__)]
                     (if
                      (clojure.core/seq search_space__14810)
                      (clojure.core/let
                       [input__13581_nth_1___parts__
                        (clojure.core/first search_space__14810)
                        result__14811
                        (clojure.core/let
                         [input__13581_nth_1___l__
                          (clojure.core/nth
                           input__13581_nth_1___parts__
                           0)
                          input__13581_nth_1___r__
                          (clojure.core/nth
                           input__13581_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs
                           (clojure.core/vec input__13581_nth_1___l__)]
                          (clojure.core/let
                           [input__13581_nth_1___r___l__
                            (clojure.core/subvec
                             input__13581_nth_1___r__
                             0
                             (clojure.core/min
                              (clojure.core/count
                               input__13581_nth_1___r__)
                              1))]
                           (if
                            (clojure.core/=
                             (clojure.core/count
                              input__13581_nth_1___r___l__)
                             1)
                            (clojure.core/let
                             [input__13581_nth_1___r___r__
                              (clojure.core/subvec
                               input__13581_nth_1___r__
                               1)]
                             (if
                              (clojure.core/=
                               input__13581_nth_1___r___l__
                               ['.])
                              (clojure.core/let
                               [?rest input__13581_nth_1___r___r__]
                               (clojure.core/let
                                [?env input__13581_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [!xs__counter
                                    (meander.runtime.zeta/iterator
                                     !xs)]
                                   (clojure.core/let
                                    [CATA_RESULT__9223__auto__
                                     (CATA__FN__13640
                                      ['meander.dev.parse.zeta/make-join
                                       (clojure.core/let
                                        [CATA_RESULT__9223__auto__
                                         (CATA__FN__13640
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            (clojure.core/vec
                                             (clojure.core/iterator-seq
                                              !xs__counter)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9223__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9223__auto__
                                          0)))
                                       (clojure.core/let
                                        [CATA_RESULT__9223__auto__
                                         (CATA__FN__13640
                                          ['meander.dev.parse.zeta/parse-sequential
                                           ?rest
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9223__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9223__auto__
                                          0)))
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__9223__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__9223__auto__
                                      0))))]
                                 (catch
                                  java.lang.Exception
                                  e__10163__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__10163__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__10163__auto__))))))
                              (meander.runtime.zeta/fail)))
                            (meander.runtime.zeta/fail)))))]
                       (if
                        (meander.runtime.zeta/fail? result__14811)
                        (recur (clojure.core/next search_space__14810))
                        result__14811))
                      (state__14805))))
                   (state__14805
                    []
                    (clojure.core/let
                     [input__13581_nth_1___l__
                      (clojure.core/subvec
                       input__13581_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__13581_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__13581_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__13581_nth_1___r__
                        (clojure.core/subvec input__13581_nth_1__ 1)]
                       (if
                        (clojure.core/=
                         input__13581_nth_1___l__
                         ['...])
                        (clojure.core/let
                         [?rest input__13581_nth_1___r__]
                         (clojure.core/let
                          [?env input__13581_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__9223__auto__
                              (CATA__FN__13640
                               ['meander.dev.parse.zeta/parse-sequential
                                ?rest
                                ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__9223__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__9223__auto__
                               0)))]
                           (catch
                            java.lang.Exception
                            e__10163__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__10163__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10163__auto__))))))
                        (state__14806)))
                      (state__14806))))
                   (state__14806
                    []
                    (clojure.core/loop
                     [search_space__14813
                      (meander.match.runtime.epsilon/partitions
                       2
                       input__13581_nth_1__)]
                     (if
                      (clojure.core/seq search_space__14813)
                      (clojure.core/let
                       [input__13581_nth_1___parts__
                        (clojure.core/first search_space__14813)
                        result__14814
                        (clojure.core/let
                         [input__13581_nth_1___l__
                          (clojure.core/nth
                           input__13581_nth_1___parts__
                           0)
                          input__13581_nth_1___r__
                          (clojure.core/nth
                           input__13581_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__8084__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__13581_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__13797]
                              (clojure.core/let
                               [input__13797_nth_0__
                                (clojure.core/nth input__13797 0)]
                               (clojure.core/letfn
                                [(save__13798
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__14817
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__13797_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__13797_nth_0__)
                                 (clojure.core/let
                                  [X__13800
                                   (clojure.core/namespace
                                    input__13797_nth_0__)]
                                  (clojure.core/case
                                   X__13800
                                   (nil)
                                   (clojure.core/let
                                    [X__13802
                                     (clojure.core/name
                                      input__13797_nth_0__)]
                                    (if
                                     (clojure.core/string? X__13802)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__13802)
                                      (save__13798)
                                      (f__14817))
                                     (f__14817)))
                                   (f__14817)))
                                 (f__14817)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__13581_nth_1___r___l__
                                (clojure.core/subvec
                                 input__13581_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__13581_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__13581_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__13581_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__13581_nth_1___r__
                                   1)]
                                 (if
                                  (clojure.core/=
                                   input__13581_nth_1___r___l__
                                   ['...])
                                  (clojure.core/let
                                   [?rest input__13581_nth_1___r___r__]
                                   (clojure.core/let
                                    [?env input__13581_nth_2__]
                                    (try
                                     [(clojure.core/let
                                       [!xs__counter
                                        (meander.runtime.zeta/iterator
                                         !xs)]
                                       (clojure.core/let
                                        [CATA_RESULT__9223__auto__
                                         (CATA__FN__13640
                                          ['meander.dev.parse.zeta/make-star
                                           (clojure.core/let
                                            [CATA_RESULT__9223__auto__
                                             (CATA__FN__13640
                                              ['meander.dev.parse.zeta/parse-sequential
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !xs__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9223__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9223__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__9223__auto__
                                             (CATA__FN__13640
                                              ['meander.dev.parse.zeta/parse-sequential
                                               ?rest
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9223__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9223__auto__
                                              0)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9223__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9223__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__10163__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10163__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10163__auto__))))))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))))]
                           (if
                            (meander.runtime.zeta/fail?
                             ret__8084__auto__)
                            (meander.runtime.zeta/fail)
                            ret__8084__auto__))))]
                       (if
                        (meander.runtime.zeta/fail? result__14814)
                        (recur (clojure.core/next search_space__14813))
                        result__14814))
                      (state__14801))))]
                  (state__14803))
                 (state__14801))
                (state__14801)))
              (state__14801)))
            (state__14801
             []
             (if
              (clojure.core/= (clojure.core/count input__13581) 4)
              (clojure.core/let
               [input__13581_nth_0__
                (clojure.core/nth input__13581 0)
                input__13581_nth_1__
                (clojure.core/nth input__13581 1)
                input__13581_nth_2__
                (clojure.core/nth input__13581 2)]
               (clojure.core/letfn
                [(state__14818
                  []
                  (clojure.core/let
                   [input__13581_nth_3__
                    (clojure.core/nth input__13581 3)]
                   (clojure.core/case
                    input__13581_nth_0__
                    (meander.dev.parse.zeta/make-star)
                    (clojure.core/letfn
                     [(state__14820
                       []
                       (if
                        (clojure.core/map? input__13581_nth_1__)
                        (clojure.core/let
                         [VAL__13806
                          (.valAt input__13581_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__13806
                          (:cat)
                          (clojure.core/let
                           [VAL__13807
                            (.valAt input__13581_nth_1__ :sequence)]
                           (if
                            (clojure.core/vector? VAL__13807)
                            (if
                             (clojure.core/=
                              (clojure.core/count VAL__13807)
                              1)
                             (clojure.core/let
                              [VAL__13807_nth_0__
                               (clojure.core/nth VAL__13807 0)]
                              (if
                               (clojure.core/map? VAL__13807_nth_0__)
                               (clojure.core/let
                                [VAL__13812
                                 (.valAt VAL__13807_nth_0__ :tag)]
                                (clojure.core/case
                                 VAL__13812
                                 (:memory-variable)
                                 (clojure.core/let
                                  [?memory-variable VAL__13807_nth_0__]
                                  (clojure.core/let
                                   [VAL__13808
                                    (.valAt
                                     input__13581_nth_1__
                                     :next)]
                                   (if
                                    (clojure.core/map? VAL__13808)
                                    (clojure.core/let
                                     [VAL__13809
                                      (.valAt VAL__13808 :tag)]
                                     (clojure.core/case
                                      VAL__13809
                                      (:empty)
                                      (clojure.core/let
                                       [?next input__13581_nth_2__]
                                       (clojure.core/let
                                        [?env input__13581_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__9223__auto__
                                            (CATA__FN__13640
                                             ['meander.dev.parse.zeta/make-join
                                              {:tag :into,
                                               :memory-variable
                                               ?memory-variable}
                                              ?next
                                              ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             CATA_RESULT__9223__auto__)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             CATA_RESULT__9223__auto__
                                             0)))]
                                         (catch
                                          java.lang.Exception
                                          e__10163__auto__
                                          (if
                                           (meander.runtime.zeta/fail?
                                            e__10163__auto__)
                                           (meander.runtime.zeta/fail)
                                           (throw
                                            e__10163__auto__))))))
                                      (state__14821)))
                                    (state__14821))))
                                 (state__14821)))
                               (state__14821)))
                             (state__14821))
                            (state__14821)))
                          (state__14821)))
                        (state__14821)))
                      (state__14821
                       []
                       (clojure.core/let
                        [?pattern input__13581_nth_1__]
                        (clojure.core/let
                         [?next input__13581_nth_2__]
                         (if
                          (clojure.core/map? input__13581_nth_3__)
                          (clojure.core/let
                           [VAL__13815
                            (.valAt input__13581_nth_3__ :context)]
                           (clojure.core/case
                            VAL__13815
                            (:string)
                            (try
                             [{:tag :string-star,
                               :greedy? false,
                               :pattern ?pattern,
                               :next ?next}]
                             (catch
                              java.lang.Exception
                              e__10163__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__10163__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10163__auto__))))
                            (state__14819)))
                          (state__14819)))))]
                     (state__14820))
                    (state__14819))))
                 (state__14819
                  []
                  (clojure.core/case
                   input__13581_nth_0__
                   (meander.dev.parse.zeta/make-star)
                   (clojure.core/let
                    [?pattern input__13581_nth_1__]
                    (clojure.core/let
                     [?next input__13581_nth_2__]
                     (try
                      [{:tag :star,
                        :greedy? false,
                        :pattern ?pattern,
                        :next ?next}]
                      (catch
                       java.lang.Exception
                       e__10163__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10163__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10163__auto__))))))
                   (state__14802)))]
                (state__14818)))
              (state__14802)))
            (state__14802
             []
             (if
              (clojure.core/= (clojure.core/count input__13581) 3)
              (clojure.core/let
               [input__13581_nth_0__
                (clojure.core/nth input__13581 0)
                input__13581_nth_1__
                (clojure.core/nth input__13581 1)
                input__13581_nth_2__
                (clojure.core/nth input__13581 2)]
               (clojure.core/case
                input__13581_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__13581_nth_1__)
                 (clojure.core/let
                  [input__13581_nth_1___l__
                   (clojure.core/subvec
                    input__13581_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__13581_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__13581_nth_1___l__)
                    1)
                   (clojure.core/let
                    [input__13581_nth_1___r__
                     (clojure.core/subvec input__13581_nth_1__ 1)]
                    (clojure.core/let
                     [input__13581_nth_1___l___nth_0__
                      (clojure.core/nth input__13581_nth_1___l__ 0)]
                     (if
                      (clojure.core/symbol?
                       input__13581_nth_1___l___nth_0__)
                      (clojure.core/let
                       [X__13823
                        (clojure.core/namespace
                         input__13581_nth_1___l___nth_0__)]
                       (clojure.core/case
                        X__13823
                        (nil)
                        (clojure.core/let
                         [X__13825
                          (clojure.core/name
                           input__13581_nth_1___l___nth_0__)]
                         (if
                          (clojure.core/string? X__13825)
                          (clojure.core/let
                           [ret__13826
                            (clojure.core/re-matches
                             #"\.\.(\d+)"
                             X__13825)]
                           (if
                            (clojure.core/some? ret__13826)
                            (if
                             (clojure.core/vector? ret__13826)
                             (if
                              (clojure.core/=
                               (clojure.core/count ret__13826)
                               2)
                              (clojure.core/let
                               [ret__13826_nth_1__
                                (clojure.core/nth ret__13826 1)]
                               (clojure.core/let
                                [?n ret__13826_nth_1__]
                                (clojure.core/let
                                 [?operator
                                  input__13581_nth_1___l___nth_0__]
                                 (clojure.core/let
                                  [?rest input__13581_nth_1___r__]
                                  (clojure.core/let
                                   [?env input__13581_nth_2__]
                                   (try
                                    [{:tag :syntax-error,
                                      :message
                                      "The n or more operator ..N must be preceeded by at least one pattern"}]
                                    (catch
                                     java.lang.Exception
                                     e__10163__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10163__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10163__auto__)))))))))
                              (state__14756))
                             (state__14756))
                            (state__14756)))
                          (state__14756)))
                        (state__14756)))
                      (state__14756))))
                   (state__14756)))
                 (state__14756))
                (state__14756)))
              (state__14756)))]
           (state__14800))
          (state__14756)))
        (state__14756
         []
         (clojure.core/letfn
          [(def__13829
            [arg__13853]
            (clojure.core/letfn
             [(state__14822
               []
               (clojure.core/let
                [x__13854 :string-plus]
                (clojure.core/let
                 [?tag x__13854]
                 (if
                  (clojure.core/map? arg__13853)
                  (clojure.core/let
                   [VAL__13855 (.valAt arg__13853 :context)]
                   (clojure.core/case
                    VAL__13855
                    (:string)
                    (clojure.core/let [?env arg__13853] [?tag ?env])
                    (state__14823)))
                  (state__14823)))))
              (state__14823
               []
               (clojure.core/let
                [x__13856 :plus]
                (clojure.core/let
                 [?tag x__13856]
                 (clojure.core/let [?env arg__13853] [?tag ?env]))))]
             (state__14822)))]
          (if
           (clojure.core/vector? input__13581)
           (if
            (clojure.core/= (clojure.core/count input__13581) 3)
            (clojure.core/let
             [input__13581_nth_0__
              (clojure.core/nth input__13581 0)
              input__13581_nth_1__
              (clojure.core/nth input__13581 1)
              input__13581_nth_2__
              (clojure.core/nth input__13581 2)]
             (clojure.core/case
              input__13581_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__13581_nth_1__)
               (clojure.core/loop
                [search_space__14824
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__13581_nth_1__)]
                (if
                 (clojure.core/seq search_space__14824)
                 (clojure.core/let
                  [input__13581_nth_1___parts__
                   (clojure.core/first search_space__14824)
                   result__14825
                   (clojure.core/let
                    [input__13581_nth_1___l__
                     (clojure.core/nth input__13581_nth_1___parts__ 0)
                     input__13581_nth_1___r__
                     (clojure.core/nth input__13581_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__8084__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__13581_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__13846]
                         (clojure.core/let
                          [input__13846_nth_0__
                           (clojure.core/nth input__13846 0)]
                          (clojure.core/letfn
                           [(save__13847
                             []
                             (meander.runtime.zeta/fail))
                            (f__14828
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__13846_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__13846_nth_0__)
                            (clojure.core/let
                             [X__13849
                              (clojure.core/namespace
                               input__13846_nth_0__)]
                             (clojure.core/case
                              X__13849
                              (nil)
                              (clojure.core/let
                               [X__13851
                                (clojure.core/name
                                 input__13846_nth_0__)]
                               (if
                                (clojure.core/string? X__13851)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__13851)
                                 (save__13847)
                                 (f__14828))
                                (f__14828)))
                              (f__14828)))
                            (f__14828)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__13581_nth_1___r___l__
                           (clojure.core/subvec
                            input__13581_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__13581_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__13581_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__13581_nth_1___r___r__
                             (clojure.core/subvec
                              input__13581_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__13581_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__13581_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__13581_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__13840
                                (clojure.core/namespace
                                 input__13581_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__13840
                                (nil)
                                (clojure.core/let
                                 [X__13842
                                  (clojure.core/name
                                   input__13581_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__13842)
                                  (clojure.core/let
                                   [ret__13843
                                    (clojure.core/re-matches
                                     #"\.\.(\d+)"
                                     X__13842)]
                                   (if
                                    (clojure.core/some? ret__13843)
                                    (if
                                     (clojure.core/vector? ret__13843)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__13843)
                                       2)
                                      (clojure.core/let
                                       [ret__13843_nth_1__
                                        (clojure.core/nth
                                         ret__13843
                                         1)]
                                       (clojure.core/let
                                        [?n ret__13843_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__13581_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__7920__auto__
                                           (def__13829
                                            input__13581_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__7920__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__7920__auto__]
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
                                                 [CATA_RESULT__9223__auto__
                                                  (CATA__FN__13640
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9223__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9223__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__9223__auto__
                                                  (CATA__FN__13640
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9223__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9223__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__10163__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__10163__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__10163__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__8084__auto__)
                       (meander.runtime.zeta/fail)
                       ret__8084__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__14825)
                   (recur (clojure.core/next search_space__14824))
                   result__14825))
                 (state__14757)))
               (state__14757))
              (state__14757)))
            (state__14757))
           (state__14757))))
        (state__14757
         []
         (if
          (clojure.core/vector? input__13581)
          (if
           (clojure.core/= (clojure.core/count input__13581) 3)
           (clojure.core/let
            [input__13581_nth_0__
             (clojure.core/nth input__13581 0)
             input__13581_nth_1__
             (clojure.core/nth input__13581 1)
             input__13581_nth_2__
             (clojure.core/nth input__13581 2)]
            (clojure.core/case
             input__13581_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__13581_nth_1__)
              (clojure.core/let
               [input__13581_nth_1___l__
                (clojure.core/subvec
                 input__13581_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__13581_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__13581_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__13581_nth_1___r__
                  (clojure.core/subvec input__13581_nth_1__ 1)]
                 (clojure.core/let
                  [input__13581_nth_1___l___nth_0__
                   (clojure.core/nth input__13581_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__13581_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__13874
                     (clojure.core/namespace
                      input__13581_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__13874
                     (nil)
                     (clojure.core/let
                      [X__13876
                       (clojure.core/name
                        input__13581_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__13876)
                       (clojure.core/let
                        [ret__13877
                         (clojure.core/re-matches
                          #"\.\.(\?.+)"
                          X__13876)]
                        (if
                         (clojure.core/some? ret__13877)
                         (if
                          (clojure.core/vector? ret__13877)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__13877)
                            2)
                           (clojure.core/let
                            [ret__13877_nth_1__
                             (clojure.core/nth ret__13877 1)]
                            (clojure.core/let
                             [?n ret__13877_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__13581_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__13581_nth_1___r__]
                               (clojure.core/let
                                [?env input__13581_nth_2__]
                                (try
                                 [{:tag :syntax-error,
                                   :message
                                   "The ?n or more operator ..?n must be preceeded by at least one pattern"}]
                                 (catch
                                  java.lang.Exception
                                  e__10163__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__10163__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__10163__auto__)))))))))
                           (state__14758))
                          (state__14758))
                         (state__14758)))
                       (state__14758)))
                     (state__14758)))
                   (state__14758))))
                (state__14758)))
              (state__14758))
             (state__14758)))
           (state__14758))
          (state__14758)))
        (state__14758
         []
         (clojure.core/letfn
          [(def__13880
            [arg__13904]
            (clojure.core/letfn
             [(state__14829
               []
               (clojure.core/let
                [x__13905 :string-logical-plus]
                (clojure.core/let
                 [?tag x__13905]
                 (if
                  (clojure.core/map? arg__13904)
                  (clojure.core/let
                   [VAL__13906 (.valAt arg__13904 :context)]
                   (clojure.core/case
                    VAL__13906
                    (:string)
                    (clojure.core/let [?env arg__13904] [?tag ?env])
                    (state__14830)))
                  (state__14830)))))
              (state__14830
               []
               (clojure.core/let
                [x__13907 :logical-plus]
                (clojure.core/let
                 [?tag x__13907]
                 (clojure.core/let [?env arg__13904] [?tag ?env]))))]
             (state__14829)))]
          (if
           (clojure.core/vector? input__13581)
           (if
            (clojure.core/= (clojure.core/count input__13581) 3)
            (clojure.core/let
             [input__13581_nth_0__
              (clojure.core/nth input__13581 0)
              input__13581_nth_1__
              (clojure.core/nth input__13581 1)
              input__13581_nth_2__
              (clojure.core/nth input__13581 2)]
             (clojure.core/case
              input__13581_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__13581_nth_1__)
               (clojure.core/loop
                [search_space__14831
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__13581_nth_1__)]
                (if
                 (clojure.core/seq search_space__14831)
                 (clojure.core/let
                  [input__13581_nth_1___parts__
                   (clojure.core/first search_space__14831)
                   result__14832
                   (clojure.core/let
                    [input__13581_nth_1___l__
                     (clojure.core/nth input__13581_nth_1___parts__ 0)
                     input__13581_nth_1___r__
                     (clojure.core/nth input__13581_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__8084__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__13581_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__13897]
                         (clojure.core/let
                          [input__13897_nth_0__
                           (clojure.core/nth input__13897 0)]
                          (clojure.core/letfn
                           [(save__13898
                             []
                             (meander.runtime.zeta/fail))
                            (f__14835
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__13897_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__13897_nth_0__)
                            (clojure.core/let
                             [X__13900
                              (clojure.core/namespace
                               input__13897_nth_0__)]
                             (clojure.core/case
                              X__13900
                              (nil)
                              (clojure.core/let
                               [X__13902
                                (clojure.core/name
                                 input__13897_nth_0__)]
                               (if
                                (clojure.core/string? X__13902)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__13902)
                                 (save__13898)
                                 (f__14835))
                                (f__14835)))
                              (f__14835)))
                            (f__14835)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__13581_nth_1___r___l__
                           (clojure.core/subvec
                            input__13581_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__13581_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__13581_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__13581_nth_1___r___r__
                             (clojure.core/subvec
                              input__13581_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__13581_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__13581_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__13581_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__13891
                                (clojure.core/namespace
                                 input__13581_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__13891
                                (nil)
                                (clojure.core/let
                                 [X__13893
                                  (clojure.core/name
                                   input__13581_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__13893)
                                  (clojure.core/let
                                   [ret__13894
                                    (clojure.core/re-matches
                                     #"\.\.(\?.+)"
                                     X__13893)]
                                   (if
                                    (clojure.core/some? ret__13894)
                                    (if
                                     (clojure.core/vector? ret__13894)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__13894)
                                       2)
                                      (clojure.core/let
                                       [ret__13894_nth_1__
                                        (clojure.core/nth
                                         ret__13894
                                         1)]
                                       (clojure.core/let
                                        [?n ret__13894_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__13581_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__7920__auto__
                                           (def__13880
                                            input__13581_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__7920__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__7920__auto__]
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
                                                 [CATA_RESULT__9223__auto__
                                                  (CATA__FN__13640
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9223__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9223__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__9223__auto__
                                                  (CATA__FN__13640
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9223__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9223__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__10163__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__10163__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__10163__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__8084__auto__)
                       (meander.runtime.zeta/fail)
                       ret__8084__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__14832)
                   (recur (clojure.core/next search_space__14831))
                   result__14832))
                 (state__14759)))
               (state__14759))
              (state__14759)))
            (state__14759))
           (state__14759))))
        (state__14759
         []
         (if
          (clojure.core/vector? input__13581)
          (if
           (clojure.core/= (clojure.core/count input__13581) 3)
           (clojure.core/let
            [input__13581_nth_0__
             (clojure.core/nth input__13581 0)
             input__13581_nth_1__
             (clojure.core/nth input__13581 1)
             input__13581_nth_2__
             (clojure.core/nth input__13581 2)]
            (clojure.core/case
             input__13581_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__13581_nth_1__)
              (clojure.core/let
               [input__13581_nth_1___l__
                (clojure.core/subvec
                 input__13581_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__13581_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__13581_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__13581_nth_1___r__
                  (clojure.core/subvec input__13581_nth_1__ 1)]
                 (clojure.core/let
                  [input__13581_nth_1___l___nth_0__
                   (clojure.core/nth input__13581_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__13581_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__13925
                     (clojure.core/namespace
                      input__13581_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__13925
                     (nil)
                     (clojure.core/let
                      [X__13927
                       (clojure.core/name
                        input__13581_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__13927)
                       (clojure.core/let
                        [ret__13928
                         (clojure.core/re-matches
                          #"\.\.(!.+)"
                          X__13927)]
                        (if
                         (clojure.core/some? ret__13928)
                         (if
                          (clojure.core/vector? ret__13928)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__13928)
                            2)
                           (clojure.core/let
                            [ret__13928_nth_1__
                             (clojure.core/nth ret__13928 1)]
                            (clojure.core/let
                             [?n ret__13928_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__13581_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__13581_nth_1___r__]
                               (clojure.core/let
                                [?env input__13581_nth_2__]
                                (try
                                 [{:tag :syntax-error,
                                   :message
                                   "The operator ..!n must be preceeded by at least one pattern"}]
                                 (catch
                                  java.lang.Exception
                                  e__10163__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__10163__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__10163__auto__)))))))))
                           (state__14760))
                          (state__14760))
                         (state__14760)))
                       (state__14760)))
                     (state__14760)))
                   (state__14760))))
                (state__14760)))
              (state__14760))
             (state__14760)))
           (state__14760))
          (state__14760)))
        (state__14760
         []
         (clojure.core/letfn
          [(def__13931
            [arg__13955]
            (clojure.core/letfn
             [(state__14836
               []
               (clojure.core/let
                [x__13956 :string-memory-plus]
                (clojure.core/let
                 [?tag x__13956]
                 (if
                  (clojure.core/map? arg__13955)
                  (clojure.core/let
                   [VAL__13957 (.valAt arg__13955 :context)]
                   (clojure.core/case
                    VAL__13957
                    (:string)
                    (clojure.core/let [?env arg__13955] [?tag ?env])
                    (state__14837)))
                  (state__14837)))))
              (state__14837
               []
               (clojure.core/let
                [x__13958 :memory-plus]
                (clojure.core/let
                 [?tag x__13958]
                 (clojure.core/let [?env arg__13955] [?tag ?env]))))]
             (state__14836)))]
          (if
           (clojure.core/vector? input__13581)
           (if
            (clojure.core/= (clojure.core/count input__13581) 3)
            (clojure.core/let
             [input__13581_nth_0__
              (clojure.core/nth input__13581 0)
              input__13581_nth_1__
              (clojure.core/nth input__13581 1)
              input__13581_nth_2__
              (clojure.core/nth input__13581 2)]
             (clojure.core/case
              input__13581_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__13581_nth_1__)
               (clojure.core/loop
                [search_space__14838
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__13581_nth_1__)]
                (if
                 (clojure.core/seq search_space__14838)
                 (clojure.core/let
                  [input__13581_nth_1___parts__
                   (clojure.core/first search_space__14838)
                   result__14839
                   (clojure.core/let
                    [input__13581_nth_1___l__
                     (clojure.core/nth input__13581_nth_1___parts__ 0)
                     input__13581_nth_1___r__
                     (clojure.core/nth input__13581_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__8084__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__13581_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__13948]
                         (clojure.core/let
                          [input__13948_nth_0__
                           (clojure.core/nth input__13948 0)]
                          (clojure.core/letfn
                           [(save__13949
                             []
                             (meander.runtime.zeta/fail))
                            (f__14842
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__13948_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__13948_nth_0__)
                            (clojure.core/let
                             [X__13951
                              (clojure.core/namespace
                               input__13948_nth_0__)]
                             (clojure.core/case
                              X__13951
                              (nil)
                              (clojure.core/let
                               [X__13953
                                (clojure.core/name
                                 input__13948_nth_0__)]
                               (if
                                (clojure.core/string? X__13953)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__13953)
                                 (save__13949)
                                 (f__14842))
                                (f__14842)))
                              (f__14842)))
                            (f__14842)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__13581_nth_1___r___l__
                           (clojure.core/subvec
                            input__13581_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__13581_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__13581_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__13581_nth_1___r___r__
                             (clojure.core/subvec
                              input__13581_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__13581_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__13581_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__13581_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__13942
                                (clojure.core/namespace
                                 input__13581_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__13942
                                (nil)
                                (clojure.core/let
                                 [X__13944
                                  (clojure.core/name
                                   input__13581_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__13944)
                                  (clojure.core/let
                                   [ret__13945
                                    (clojure.core/re-matches
                                     #"\.\.(\!.+)"
                                     X__13944)]
                                   (if
                                    (clojure.core/some? ret__13945)
                                    (if
                                     (clojure.core/vector? ret__13945)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__13945)
                                       2)
                                      (clojure.core/let
                                       [ret__13945_nth_1__
                                        (clojure.core/nth
                                         ret__13945
                                         1)]
                                       (clojure.core/let
                                        [?n ret__13945_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__13581_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__7920__auto__
                                           (def__13931
                                            input__13581_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__7920__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__7920__auto__]
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
                                                 [CATA_RESULT__9223__auto__
                                                  (CATA__FN__13640
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9223__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9223__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__9223__auto__
                                                  (CATA__FN__13640
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9223__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9223__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__10163__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__10163__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__10163__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__8084__auto__)
                       (meander.runtime.zeta/fail)
                       ret__8084__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__14839)
                   (recur (clojure.core/next search_space__14838))
                   result__14839))
                 (state__14761)))
               (state__14761))
              (state__14761)))
            (state__14761))
           (state__14761))))
        (state__14761
         []
         (if
          (clojure.core/vector? input__13581)
          (clojure.core/letfn
           [(state__14843
             []
             (if
              (clojure.core/= (clojure.core/count input__13581) 3)
              (clojure.core/let
               [input__13581_nth_0__
                (clojure.core/nth input__13581 0)
                input__13581_nth_1__
                (clojure.core/nth input__13581 1)
                input__13581_nth_2__
                (clojure.core/nth input__13581 2)]
               (clojure.core/case
                input__13581_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__13581_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__13581_nth_1__)]
                  (clojure.core/let
                   [?env input__13581_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__9223__auto__
                        (CATA__FN__13640
                         ['meander.dev.parse.zeta/make-cat
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__13641 (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (recur
                              (clojure.core/conj!
                               return__13641
                               (clojure.core/let
                                [CATA_RESULT__9223__auto__
                                 (CATA__FN__13640
                                  [(if
                                    (.hasNext !xs__counter)
                                    (.next !xs__counter))
                                   ?env])]
                                (if
                                 (meander.runtime.zeta/fail?
                                  CATA_RESULT__9223__auto__)
                                 (throw (meander.runtime.zeta/fail))
                                 (clojure.core/nth
                                  CATA_RESULT__9223__auto__
                                  0)))))
                             (clojure.core/persistent!
                              return__13641))))
                          {:tag :empty}
                          ?env])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__9223__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__9223__auto__
                         0))))]
                    (catch
                     java.lang.Exception
                     e__10163__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10163__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10163__auto__))))))
                 (state__14844))
                (state__14844)))
              (state__14844)))
            (state__14844
             []
             (if
              (clojure.core/= (clojure.core/count input__13581) 4)
              (clojure.core/let
               [input__13581_nth_0__
                (clojure.core/nth input__13581 0)
                input__13581_nth_1__
                (clojure.core/nth input__13581 1)
                input__13581_nth_2__
                (clojure.core/nth input__13581 2)]
               (clojure.core/letfn
                [(state__14846
                  []
                  (clojure.core/let
                   [input__13581_nth_3__
                    (clojure.core/nth input__13581 3)]
                   (clojure.core/case
                    input__13581_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (if
                     (clojure.core/vector? input__13581_nth_1__)
                     (clojure.core/letfn
                      [(state__14853
                        []
                        (clojure.core/case
                         input__13581_nth_1__
                         ([])
                         (clojure.core/let
                          [?next input__13581_nth_2__]
                          (clojure.core/let
                           [?env input__13581_nth_3__]
                           (try
                            [?next]
                            (catch
                             java.lang.Exception
                             e__10163__auto__
                             (if
                              (meander.runtime.zeta/fail?
                               e__10163__auto__)
                              (meander.runtime.zeta/fail)
                              (throw e__10163__auto__))))))
                         (state__14854)))
                       (state__14854
                        []
                        (clojure.core/loop
                         [search_space__14855
                          (meander.match.runtime.epsilon/partitions
                           2
                           input__13581_nth_1__)]
                         (if
                          (clojure.core/seq search_space__14855)
                          (clojure.core/let
                           [input__13581_nth_1___parts__
                            (clojure.core/first search_space__14855)
                            result__14856
                            (clojure.core/let
                             [input__13581_nth_1___l__
                              (clojure.core/nth
                               input__13581_nth_1___parts__
                               0)
                              input__13581_nth_1___r__
                              (clojure.core/nth
                               input__13581_nth_1___parts__
                               1)]
                             (clojure.core/let
                              [!xs []]
                              (clojure.core/let
                               [ret__8084__auto__
                                (meander.runtime.zeta/epsilon-run-star-1
                                 input__13581_nth_1___l__
                                 [!xs]
                                 (clojure.core/fn
                                  [[!xs] input__13984]
                                  (clojure.core/let
                                   [input__13984_nth_0__
                                    (clojure.core/nth input__13984 0)]
                                   (clojure.core/letfn
                                    [(save__13985
                                      []
                                      (meander.runtime.zeta/fail))
                                     (f__14859
                                      []
                                      (clojure.core/let
                                       [!xs
                                        (clojure.core/conj
                                         !xs
                                         input__13984_nth_0__)]
                                       [!xs]))]
                                    (if
                                     (clojure.core/map?
                                      input__13984_nth_0__)
                                     (clojure.core/let
                                      [VAL__13986
                                       (.valAt
                                        input__13984_nth_0__
                                        :tag)]
                                      (clojure.core/case
                                       VAL__13986
                                       (:group)
                                       (save__13985)
                                       (f__14859)))
                                     (f__14859)))))
                                 (clojure.core/fn
                                  [[!xs]]
                                  (clojure.core/let
                                   [input__13581_nth_1___r___l__
                                    (clojure.core/subvec
                                     input__13581_nth_1___r__
                                     0
                                     (clojure.core/min
                                      (clojure.core/count
                                       input__13581_nth_1___r__)
                                      1))]
                                   (if
                                    (clojure.core/=
                                     (clojure.core/count
                                      input__13581_nth_1___r___l__)
                                     1)
                                    (clojure.core/let
                                     [input__13581_nth_1___r___r__
                                      (clojure.core/subvec
                                       input__13581_nth_1___r__
                                       1)]
                                     (clojure.core/let
                                      [input__13581_nth_1___r___l___nth_0__
                                       (clojure.core/nth
                                        input__13581_nth_1___r___l__
                                        0)]
                                      (if
                                       (clojure.core/map?
                                        input__13581_nth_1___r___l___nth_0__)
                                       (clojure.core/let
                                        [VAL__13983
                                         (.valAt
                                          input__13581_nth_1___r___l___nth_0__
                                          :tag)]
                                        (clojure.core/case
                                         VAL__13983
                                         (:group)
                                         (clojure.core/let
                                          [?group
                                           input__13581_nth_1___r___l___nth_0__]
                                          (clojure.core/let
                                           [?rest
                                            input__13581_nth_1___r___r__]
                                           (clojure.core/let
                                            [?next
                                             input__13581_nth_2__]
                                            (clojure.core/let
                                             [?env
                                              input__13581_nth_3__]
                                             (try
                                              [(clojure.core/let
                                                [!xs__counter
                                                 (meander.runtime.zeta/iterator
                                                  !xs)]
                                                (clojure.core/let
                                                 [CATA_RESULT__9223__auto__
                                                  (CATA__FN__13640
                                                   ['meander.dev.parse.zeta/make-join
                                                    (clojure.core/let
                                                     [CATA_RESULT__9223__auto__
                                                      (CATA__FN__13640
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
                                                       CATA_RESULT__9223__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__9223__auto__
                                                       0)))
                                                    (clojure.core/let
                                                     [CATA_RESULT__9223__auto__
                                                      (CATA__FN__13640
                                                       ['meander.dev.parse.zeta/make-join
                                                        ?group
                                                        ?rest
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__9223__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__9223__auto__
                                                       0)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9223__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9223__auto__
                                                   0))))]
                                              (catch
                                               java.lang.Exception
                                               e__10163__auto__
                                               (if
                                                (meander.runtime.zeta/fail?
                                                 e__10163__auto__)
                                                (meander.runtime.zeta/fail)
                                                (throw
                                                 e__10163__auto__))))))))
                                         (meander.runtime.zeta/fail)))
                                       (meander.runtime.zeta/fail))))
                                    (meander.runtime.zeta/fail)))))]
                               (if
                                (meander.runtime.zeta/fail?
                                 ret__8084__auto__)
                                (meander.runtime.zeta/fail)
                                ret__8084__auto__))))]
                           (if
                            (meander.runtime.zeta/fail? result__14856)
                            (recur
                             (clojure.core/next search_space__14855))
                            result__14856))
                          (state__14847))))]
                      (state__14853))
                     (state__14847))
                    (state__14847))))
                 (state__14847
                  []
                  (clojure.core/case
                   input__13581_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (if
                    (clojure.core/vector? input__13581_nth_1__)
                    (clojure.core/let
                     [input__13581_nth_1___l__
                      (clojure.core/subvec
                       input__13581_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__13581_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__13581_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__13581_nth_1___r__
                        (clojure.core/subvec input__13581_nth_1__ 1)]
                       (clojure.core/let
                        [input__13581_nth_1___l___nth_0__
                         (clojure.core/nth input__13581_nth_1___l__ 0)]
                        (if
                         (clojure.core/map?
                          input__13581_nth_1___l___nth_0__)
                         (clojure.core/let
                          [VAL__13995
                           (.valAt
                            input__13581_nth_1___l___nth_0__
                            :tag)]
                          (clojure.core/case
                           VAL__13995
                           (:literal)
                           (clojure.core/let
                            [VAL__13996
                             (.valAt
                              input__13581_nth_1___l___nth_0__
                              :type)]
                            (if
                             (clojure.core/let
                              [x__6980__auto__ VAL__13996]
                              (clojure.core/case
                               x__6980__auto__
                               (:string :char)
                               true
                               false))
                             (clojure.core/let
                              [VAL__13997
                               (.valAt
                                input__13581_nth_1___l___nth_0__
                                :form)]
                              (clojure.core/let
                               [!forms []]
                               (clojure.core/let
                                [!forms
                                 (clojure.core/conj !forms VAL__13997)]
                                (clojure.core/loop
                                 [i__8057__auto__
                                  0
                                  coll__14860
                                  input__13581_nth_1___r__
                                  [!forms]
                                  [!forms]]
                                 (clojure.core/let
                                  [input__13998
                                   (clojure.core/subvec
                                    coll__14860
                                    0
                                    (clojure.core/min
                                     (clojure.core/count coll__14860)
                                     1))]
                                  (if
                                   (clojure.core/=
                                    (clojure.core/count input__13998)
                                    1)
                                   (clojure.core/let
                                    [result__8058__auto__
                                     (clojure.core/let
                                      [input__13998_nth_0__
                                       (clojure.core/nth
                                        input__13998
                                        0)]
                                      (if
                                       (clojure.core/map?
                                        input__13998_nth_0__)
                                       (clojure.core/let
                                        [VAL__13999
                                         (.valAt
                                          input__13998_nth_0__
                                          :tag)]
                                        (clojure.core/case
                                         VAL__13999
                                         (:literal)
                                         (clojure.core/let
                                          [VAL__14000
                                           (.valAt
                                            input__13998_nth_0__
                                            :type)]
                                          (if
                                           (clojure.core/let
                                            [x__6980__auto__
                                             VAL__14000]
                                            (clojure.core/case
                                             x__6980__auto__
                                             (:string :char)
                                             true
                                             false))
                                           (clojure.core/let
                                            [VAL__14001
                                             (.valAt
                                              input__13998_nth_0__
                                              :form)]
                                            (clojure.core/let
                                             [!forms
                                              (clojure.core/conj
                                               !forms
                                               VAL__14001)]
                                             [!forms]))
                                           (meander.runtime.zeta/fail)))
                                         (meander.runtime.zeta/fail)))
                                       (meander.runtime.zeta/fail)))]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      result__8058__auto__)
                                     (state__14848)
                                     (recur
                                      (clojure.core/inc
                                       i__8057__auto__)
                                      (clojure.core/subvec
                                       coll__14860
                                       1)
                                      result__8058__auto__)))
                                   (if
                                    (clojure.core/or
                                     (clojure.core/seq coll__14860)
                                     (clojure.core/<
                                      i__8057__auto__
                                      0))
                                    (state__14848)
                                    (if
                                     (clojure.core/map?
                                      input__13581_nth_2__)
                                     (clojure.core/let
                                      [VAL__13990
                                       (.valAt
                                        input__13581_nth_2__
                                        :tag)]
                                      (clojure.core/case
                                       VAL__13990
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
                                         e__10163__auto__
                                         (if
                                          (meander.runtime.zeta/fail?
                                           e__10163__auto__)
                                          (meander.runtime.zeta/fail)
                                          (throw e__10163__auto__))))
                                       (state__14848)))
                                     (state__14848)))))))))
                             (state__14848)))
                           (state__14848)))
                         (state__14848))))
                      (state__14848)))
                    (state__14848))
                   (state__14848)))
                 (state__14848
                  []
                  (clojure.core/let
                   [input__13581_nth_3__
                    (clojure.core/nth input__13581 3)]
                   (clojure.core/case
                    input__13581_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (clojure.core/letfn
                     [(state__14861
                       []
                       (if
                        (clojure.core/vector? input__13581_nth_1__)
                        (clojure.core/let
                         [input__13581_nth_1___l__
                          (clojure.core/subvec
                           input__13581_nth_1__
                           0
                           (clojure.core/min
                            (clojure.core/count input__13581_nth_1__)
                            1))]
                         (if
                          (clojure.core/=
                           (clojure.core/count
                            input__13581_nth_1___l__)
                           1)
                          (clojure.core/let
                           [input__13581_nth_1___r__
                            (clojure.core/subvec
                             input__13581_nth_1__
                             1)]
                           (clojure.core/let
                            [input__13581_nth_1___l___nth_0__
                             (clojure.core/nth
                              input__13581_nth_1___l__
                              0)]
                            (if
                             (clojure.core/map?
                              input__13581_nth_1___l___nth_0__)
                             (clojure.core/let
                              [VAL__14751
                               (.valAt
                                input__13581_nth_1___l___nth_0__
                                :tag)]
                              (clojure.core/case
                               VAL__14751
                               (:literal)
                               (clojure.core/letfn
                                [(state__14863
                                  []
                                  (clojure.core/let
                                   [VAL__14008
                                    (.valAt
                                     input__13581_nth_1___l___nth_0__
                                     :type)]
                                   (clojure.core/case
                                    VAL__14008
                                    (:string)
                                    (clojure.core/let
                                     [?ast
                                      input__13581_nth_1___l___nth_0__]
                                     (clojure.core/let
                                      [?rest input__13581_nth_1___r__]
                                      (clojure.core/let
                                       [?next input__13581_nth_2__]
                                       (clojure.core/let
                                        [?env input__13581_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__9223__auto__
                                            (CATA__FN__13640
                                             ['meander.dev.parse.zeta/make-join
                                              ?ast
                                              (clojure.core/let
                                               [CATA_RESULT__9223__auto__
                                                (CATA__FN__13640
                                                 ['meander.dev.parse.zeta/make-cat
                                                  ?rest
                                                  ?next
                                                  ?env])]
                                               (if
                                                (meander.runtime.zeta/fail?
                                                 CATA_RESULT__9223__auto__)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 CATA_RESULT__9223__auto__
                                                 0)))
                                              ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             CATA_RESULT__9223__auto__)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             CATA_RESULT__9223__auto__
                                             0)))]
                                         (catch
                                          java.lang.Exception
                                          e__10163__auto__
                                          (if
                                           (meander.runtime.zeta/fail?
                                            e__10163__auto__)
                                           (meander.runtime.zeta/fail)
                                           (throw
                                            e__10163__auto__))))))))
                                    (state__14864))))
                                 (state__14864
                                  []
                                  (clojure.core/let
                                   [VAL__14018
                                    (.valAt
                                     input__13581_nth_1___l___nth_0__
                                     :form)]
                                   (clojure.core/let
                                    [!forms []]
                                    (clojure.core/let
                                     [!forms
                                      (clojure.core/conj
                                       !forms
                                       VAL__14018)]
                                     (clojure.core/loop
                                      [i__8057__auto__
                                       0
                                       coll__14865
                                       input__13581_nth_1___r__
                                       [!forms]
                                       [!forms]]
                                      (clojure.core/let
                                       [input__14019
                                        (clojure.core/subvec
                                         coll__14865
                                         0
                                         (clojure.core/min
                                          (clojure.core/count
                                           coll__14865)
                                          1))]
                                       (if
                                        (clojure.core/=
                                         (clojure.core/count
                                          input__14019)
                                         1)
                                        (clojure.core/let
                                         [result__8058__auto__
                                          (clojure.core/let
                                           [input__14019_nth_0__
                                            (clojure.core/nth
                                             input__14019
                                             0)]
                                           (if
                                            (clojure.core/map?
                                             input__14019_nth_0__)
                                            (clojure.core/let
                                             [VAL__14020
                                              (.valAt
                                               input__14019_nth_0__
                                               :tag)]
                                             (clojure.core/case
                                              VAL__14020
                                              (:literal)
                                              (clojure.core/let
                                               [VAL__14021
                                                (.valAt
                                                 input__14019_nth_0__
                                                 :form)]
                                               (clojure.core/let
                                                [!forms
                                                 (clojure.core/conj
                                                  !forms
                                                  VAL__14021)]
                                                [!forms]))
                                              (meander.runtime.zeta/fail)))
                                            (meander.runtime.zeta/fail)))]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           result__8058__auto__)
                                          (state__14862)
                                          (recur
                                           (clojure.core/inc
                                            i__8057__auto__)
                                           (clojure.core/subvec
                                            coll__14865
                                            1)
                                           result__8058__auto__)))
                                        (if
                                         (clojure.core/or
                                          (clojure.core/seq
                                           coll__14865)
                                          (clojure.core/<
                                           i__8057__auto__
                                           0))
                                         (state__14862)
                                         (if
                                          (clojure.core/map?
                                           input__13581_nth_2__)
                                          (clojure.core/let
                                           [VAL__14011
                                            (.valAt
                                             input__13581_nth_2__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__14011
                                            (:empty)
                                            (if
                                             (clojure.core/map?
                                              input__13581_nth_3__)
                                             (clojure.core/let
                                              [VAL__14012
                                               (.valAt
                                                input__13581_nth_3__
                                                :context)]
                                              (clojure.core/let
                                               [?context VAL__14012]
                                               (clojure.core/let
                                                [?env
                                                 input__13581_nth_3__]
                                                (try
                                                 [{:tag :literal,
                                                   :type ?context,
                                                   :form
                                                   (clojure.core/into
                                                    []
                                                    !forms)}]
                                                 (catch
                                                  java.lang.Exception
                                                  e__10163__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__10163__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__10163__auto__)))))))
                                             (state__14862))
                                            (state__14862)))
                                          (state__14862))))))))))]
                                (state__14863))
                               (state__14862)))
                             (state__14862))))
                          (state__14862)))
                        (state__14862)))
                      (state__14862
                       []
                       (clojure.core/let
                        [?sequence input__13581_nth_1__]
                        (clojure.core/let
                         [?next input__13581_nth_2__]
                         (if
                          (clojure.core/map? input__13581_nth_3__)
                          (clojure.core/let
                           [VAL__14025
                            (.valAt input__13581_nth_3__ :context)]
                           (clojure.core/case
                            VAL__14025
                            (:string)
                            (try
                             [{:tag :string-cat,
                               :sequence ?sequence,
                               :next ?next}]
                             (catch
                              java.lang.Exception
                              e__10163__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__10163__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10163__auto__))))
                            (state__14849)))
                          (state__14849)))))]
                     (state__14861))
                    (state__14849))))
                 (state__14849
                  []
                  (clojure.core/case
                   input__13581_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (clojure.core/let
                    [?sequence input__13581_nth_1__]
                    (clojure.core/let
                     [?next input__13581_nth_2__]
                     (try
                      [{:tag :cat, :sequence ?sequence, :next ?next}]
                      (catch
                       java.lang.Exception
                       e__10163__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10163__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10163__auto__))))))
                   (state__14850)))
                 (state__14850
                  []
                  (clojure.core/let
                   [input__13581_nth_3__
                    (clojure.core/nth input__13581 3)]
                   (clojure.core/case
                    input__13581_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (if
                     (clojure.core/map? input__13581_nth_1__)
                     (clojure.core/let
                      [VAL__14747 (.valAt input__13581_nth_1__ :tag)]
                      (clojure.core/case
                       VAL__14747
                       (:cat)
                       (clojure.core/let
                        [VAL__14031
                         (.valAt input__13581_nth_1__ :sequence)]
                        (clojure.core/let
                         [?sequence VAL__14031]
                         (clojure.core/let
                          [VAL__14032
                           (.valAt input__13581_nth_1__ :next)]
                          (if
                           (clojure.core/map? VAL__14032)
                           (clojure.core/let
                            [VAL__14033 (.valAt VAL__14032 :tag)]
                            (clojure.core/case
                             VAL__14033
                             (:empty)
                             (clojure.core/let
                              [?right input__13581_nth_2__]
                              (clojure.core/let
                               [?env input__13581_nth_3__]
                               (try
                                [(clojure.core/let
                                  [CATA_RESULT__9223__auto__
                                   (CATA__FN__13640
                                    ['meander.dev.parse.zeta/make-cat
                                     ?sequence
                                     ?right
                                     ?env])]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    CATA_RESULT__9223__auto__)
                                   (throw (meander.runtime.zeta/fail))
                                   (clojure.core/nth
                                    CATA_RESULT__9223__auto__
                                    0)))]
                                (catch
                                 java.lang.Exception
                                 e__10163__auto__
                                 (if
                                  (meander.runtime.zeta/fail?
                                   e__10163__auto__)
                                  (meander.runtime.zeta/fail)
                                  (throw e__10163__auto__))))))
                             (state__14851)))
                           (state__14851)))))
                       (:literal)
                       (clojure.core/let
                        [VAL__14037
                         (.valAt input__13581_nth_1__ :type)]
                        (clojure.core/case
                         VAL__14037
                         (:string)
                         (clojure.core/let
                          [VAL__14038
                           (.valAt input__13581_nth_1__ :form)]
                          (clojure.core/let
                           [?form-1 VAL__14038]
                           (if
                            (clojure.core/map? input__13581_nth_2__)
                            (clojure.core/let
                             [VAL__14039
                              (.valAt input__13581_nth_2__ :tag)]
                             (clojure.core/case
                              VAL__14039
                              (:string-join)
                              (clojure.core/let
                               [VAL__14040
                                (.valAt input__13581_nth_2__ :left)]
                               (if
                                (clojure.core/map? VAL__14040)
                                (clojure.core/let
                                 [VAL__14041 (.valAt VAL__14040 :tag)]
                                 (clojure.core/case
                                  VAL__14041
                                  (:literal)
                                  (clojure.core/let
                                   [VAL__14042
                                    (.valAt VAL__14040 :type)]
                                   (clojure.core/case
                                    VAL__14042
                                    (:string)
                                    (clojure.core/let
                                     [VAL__14043
                                      (.valAt VAL__14040 :form)]
                                     (clojure.core/let
                                      [?form-2 VAL__14043]
                                      (clojure.core/let
                                       [VAL__14044
                                        (.valAt
                                         input__13581_nth_2__
                                         :right)]
                                       (clojure.core/let
                                        [?right VAL__14044]
                                        (if
                                         (clojure.core/map?
                                          input__13581_nth_3__)
                                         (clojure.core/let
                                          [VAL__14045
                                           (.valAt
                                            input__13581_nth_3__
                                            :context)]
                                          (clojure.core/case
                                           VAL__14045
                                           (:string)
                                           (clojure.core/let
                                            [?env input__13581_nth_3__]
                                            (try
                                             [(clojure.core/let
                                               [CATA_RESULT__9223__auto__
                                                (CATA__FN__13640
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
                                                 CATA_RESULT__9223__auto__)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 CATA_RESULT__9223__auto__
                                                 0)))]
                                             (catch
                                              java.lang.Exception
                                              e__10163__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__10163__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__10163__auto__)))))
                                           (state__14851)))
                                         (state__14851))))))
                                    (state__14851)))
                                  (state__14851)))
                                (state__14851)))
                              (state__14851)))
                            (state__14851))))
                         (state__14851)))
                       (state__14851)))
                     (state__14851))
                    (state__14851))))
                 (state__14851
                  []
                  (clojure.core/case
                   input__13581_nth_0__
                   (meander.dev.parse.zeta/make-join)
                   (if
                    (clojure.core/map? input__13581_nth_1__)
                    (clojure.core/let
                     [VAL__14749 (.valAt input__13581_nth_1__ :tag)]
                     (clojure.core/case
                      VAL__14749
                      (:cat)
                      (clojure.core/let
                       [?ast input__13581_nth_1__]
                       (if
                        (clojure.core/map? input__13581_nth_2__)
                        (clojure.core/let
                         [VAL__14049
                          (.valAt input__13581_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__14049
                          (:cat)
                          (clojure.core/let
                           [VAL__14050
                            (.valAt input__13581_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__14050]
                            (clojure.core/let
                             [VAL__14051
                              (.valAt input__13581_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__14051]
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
                                e__10163__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__10163__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__10163__auto__))))))))
                          (state__14852)))
                        (state__14852)))
                      (:string-cat)
                      (clojure.core/let
                       [?ast input__13581_nth_1__]
                       (if
                        (clojure.core/map? input__13581_nth_2__)
                        (clojure.core/let
                         [VAL__14055
                          (.valAt input__13581_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__14055
                          (:string-cat)
                          (clojure.core/let
                           [VAL__14056
                            (.valAt input__13581_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__14056]
                            (clojure.core/let
                             [VAL__14057
                              (.valAt input__13581_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__14057]
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
                                e__10163__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__10163__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__10163__auto__))))))))
                          (state__14852)))
                        (state__14852)))
                      (state__14852)))
                    (state__14852))
                   (state__14852)))
                 (state__14852
                  []
                  (clojure.core/let
                   [input__13581_nth_3__
                    (clojure.core/nth input__13581 3)]
                   (clojure.core/case
                    input__13581_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (clojure.core/letfn
                     [(state__14866
                       []
                       (if
                        (clojure.core/map? input__13581_nth_1__)
                        (clojure.core/let
                         [VAL__14750
                          (.valAt input__13581_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__14750
                          (:string-cat)
                          (clojure.core/let
                           [VAL__14061
                            (.valAt input__13581_nth_1__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__14061]
                            (clojure.core/let
                             [VAL__14062
                              (.valAt input__13581_nth_1__ :next)]
                             (if
                              (clojure.core/map? VAL__14062)
                              (clojure.core/let
                               [VAL__14063 (.valAt VAL__14062 :tag)]
                               (clojure.core/case
                                VAL__14063
                                (:empty)
                                (clojure.core/let
                                 [?right input__13581_nth_2__]
                                 (clojure.core/let
                                  [?env input__13581_nth_3__]
                                  (try
                                   [(clojure.core/let
                                     [CATA_RESULT__9223__auto__
                                      (CATA__FN__13640
                                       ['meander.dev.parse.zeta/make-join
                                        ?sequence
                                        ?right
                                        ?env])]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       CATA_RESULT__9223__auto__)
                                      (throw
                                       (meander.runtime.zeta/fail))
                                      (clojure.core/nth
                                       CATA_RESULT__9223__auto__
                                       0)))]
                                   (catch
                                    java.lang.Exception
                                    e__10163__auto__
                                    (if
                                     (meander.runtime.zeta/fail?
                                      e__10163__auto__)
                                     (meander.runtime.zeta/fail)
                                     (throw e__10163__auto__))))))
                                (state__14867)))
                              (state__14867)))))
                          (:string-star)
                          (clojure.core/let
                           [VAL__14067
                            (.valAt input__13581_nth_1__ :pattern)]
                           (clojure.core/let
                            [?pattern VAL__14067]
                            (clojure.core/let
                             [VAL__14068
                              (.valAt input__13581_nth_1__ :next)]
                             (if
                              (clojure.core/map? VAL__14068)
                              (clojure.core/let
                               [VAL__14069 (.valAt VAL__14068 :tag)]
                               (clojure.core/case
                                VAL__14069
                                (:empty)
                                (clojure.core/let
                                 [?right input__13581_nth_2__]
                                 (if
                                  (clojure.core/map?
                                   input__13581_nth_3__)
                                  (clojure.core/let
                                   [VAL__14070
                                    (.valAt
                                     input__13581_nth_3__
                                     :context)]
                                   (clojure.core/case
                                    VAL__14070
                                    (:string)
                                    (try
                                     [{:tag :string-star,
                                       :pattern ?pattern,
                                       :next ?right}]
                                     (catch
                                      java.lang.Exception
                                      e__10163__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10163__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10163__auto__))))
                                    (state__14867)))
                                  (state__14867)))
                                (state__14867)))
                              (state__14867)))))
                          (:string-join)
                          (clojure.core/let
                           [VAL__14074
                            (.valAt input__13581_nth_1__ :left)]
                           (clojure.core/let
                            [?left VAL__14074]
                            (clojure.core/let
                             [VAL__14075
                              (.valAt input__13581_nth_1__ :right)]
                             (clojure.core/let
                              [?right-1 VAL__14075]
                              (clojure.core/let
                               [?right-2 input__13581_nth_2__]
                               (if
                                (clojure.core/map?
                                 input__13581_nth_3__)
                                (clojure.core/let
                                 [VAL__14076
                                  (.valAt
                                   input__13581_nth_3__
                                   :context)]
                                 (clojure.core/case
                                  VAL__14076
                                  (:string)
                                  (clojure.core/let
                                   [?env input__13581_nth_3__]
                                   (try
                                    [{:tag :string-join,
                                      :left ?left,
                                      :right
                                      (clojure.core/let
                                       [CATA_RESULT__9223__auto__
                                        (CATA__FN__13640
                                         ['meander.dev.parse.zeta/make-join
                                          ?right-1
                                          ?right-2
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9223__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9223__auto__
                                         0)))}]
                                    (catch
                                     java.lang.Exception
                                     e__10163__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10163__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10163__auto__)))))
                                  (state__14867)))
                                (state__14867)))))))
                          (state__14867)))
                        (state__14867)))
                      (state__14867
                       []
                       (clojure.core/let
                        [?left input__13581_nth_1__]
                        (if
                         (clojure.core/map? input__13581_nth_2__)
                         (clojure.core/let
                          [VAL__14079
                           (.valAt input__13581_nth_2__ :tag)]
                          (clojure.core/case
                           VAL__14079
                           (:empty)
                           (clojure.core/let
                            [?env input__13581_nth_3__]
                            (try
                             [?left]
                             (catch
                              java.lang.Exception
                              e__10163__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__10163__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10163__auto__)))))
                           (state__14868)))
                         (state__14868))))
                      (state__14868
                       []
                       (if
                        (clojure.core/map? input__13581_nth_1__)
                        (clojure.core/let
                         [VAL__14082
                          (.valAt input__13581_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__14082
                          (:empty)
                          (clojure.core/let
                           [?right input__13581_nth_2__]
                           (clojure.core/let
                            [?env input__13581_nth_3__]
                            (try
                             [?right]
                             (catch
                              java.lang.Exception
                              e__10163__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__10163__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10163__auto__))))))
                          (state__14869)))
                        (state__14869)))
                      (state__14869
                       []
                       (clojure.core/let
                        [?left input__13581_nth_1__]
                        (clojure.core/let
                         [?right input__13581_nth_2__]
                         (clojure.core/letfn
                          [(state__14870
                            []
                            (if
                             (clojure.core/map? input__13581_nth_3__)
                             (clojure.core/let
                              [VAL__14085
                               (.valAt input__13581_nth_3__ :context)]
                              (clojure.core/case
                               VAL__14085
                               (:string)
                               (try
                                [{:tag :string-join,
                                  :left ?left,
                                  :right ?right}]
                                (catch
                                 java.lang.Exception
                                 e__10163__auto__
                                 (if
                                  (meander.runtime.zeta/fail?
                                   e__10163__auto__)
                                  (meander.runtime.zeta/fail)
                                  (throw e__10163__auto__))))
                               (state__14871)))
                             (state__14871)))
                           (state__14871
                            []
                            (clojure.core/let
                             [?env input__13581_nth_3__]
                             (try
                              [{:tag :join,
                                :left ?left,
                                :right ?right}]
                              (catch
                               java.lang.Exception
                               e__10163__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__10163__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__10163__auto__))))))]
                          (state__14870)))))]
                     (state__14866))
                    (state__14845))))]
                (state__14846)))
              (state__14845)))
            (state__14845
             []
             (if
              (clojure.core/= (clojure.core/count input__13581) 3)
              (clojure.core/let
               [input__13581_nth_0__
                (clojure.core/nth input__13581 0)
                input__13581_nth_1__
                (clojure.core/nth input__13581 1)
                input__13581_nth_2__
                (clojure.core/nth input__13581 2)]
               (clojure.core/case
                input__13581_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__13581_nth_1__)
                 (clojure.core/let
                  [VAL__14090
                   (.valAt input__13581_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__14090]
                   (clojure.core/let
                    [X__14092
                     ((clojure.core/fn
                       [m__6987__auto__]
                       (clojure.core/dissoc
                        m__6987__auto__
                        :meander.zeta/as))
                      input__13581_nth_1__)]
                    (clojure.core/let
                     [?rest X__14092]
                     (clojure.core/letfn
                      [(save__14093 [] (state__14762))
                       (f__14872
                        []
                        (clojure.core/let
                         [?env input__13581_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern
                            (clojure.core/let
                             [CATA_RESULT__9223__auto__
                              (CATA__FN__13640 [?pattern ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__9223__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__9223__auto__
                               0))),
                            :next
                            (clojure.core/let
                             [CATA_RESULT__9223__auto__
                              (CATA__FN__13640 [?rest ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__9223__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__9223__auto__
                               0)))}]
                          (catch
                           java.lang.Exception
                           e__10163__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__10163__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__10163__auto__))))))]
                      (if
                       (clojure.core/= ?rest input__13581_nth_1__)
                       (save__14093)
                       (f__14872)))))))
                 (state__14762))
                (state__14762)))
              (state__14762)))]
           (state__14843))
          (state__14762)))
        (state__14762
         []
         (clojure.core/letfn
          [(def__14096
            [arg__14129 ?ns]
            (clojure.core/letfn
             [(state__14873
               []
               (clojure.core/let
                [x__14130 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__14130)
                 (clojure.core/let [?env arg__14129] [?env ?ns])
                 (state__14874))))
              (state__14874
               []
               (if
                (clojure.core/map? arg__14129)
                (clojure.core/let
                 [VAL__14131 (.valAt arg__14129 :aliases)]
                 (if
                  (clojure.core/map? VAL__14131)
                  (clojure.core/let
                   [X__14133 (clojure.core/set VAL__14131)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14133))
                    (clojure.core/loop
                     [search_space__14875 (clojure.core/seq X__14133)]
                     (if
                      (clojure.core/seq search_space__14875)
                      (clojure.core/let
                       [elem__14134
                        (clojure.core/first search_space__14875)
                        result__14876
                        (clojure.core/let
                         [elem__14134_nth_0__
                          (clojure.core/nth elem__14134 0)
                          elem__14134_nth_1__
                          (clojure.core/nth elem__14134 1)]
                         (if
                          (clojure.core/symbol? elem__14134_nth_0__)
                          (clojure.core/let
                           [X__14136
                            (clojure.core/name elem__14134_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__14136)
                            (if
                             (clojure.core/symbol? elem__14134_nth_1__)
                             (clojure.core/let
                              [X__14138
                               (clojure.core/name elem__14134_nth_1__)]
                              (clojure.core/case
                               X__14138
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__14129]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__14876)
                        (recur (clojure.core/next search_space__14875))
                        result__14876))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__14873)))]
          (if
           (clojure.core/vector? input__13581)
           (if
            (clojure.core/= (clojure.core/count input__13581) 3)
            (clojure.core/let
             [input__13581_nth_0__
              (clojure.core/nth input__13581 0)
              input__13581_nth_1__
              (clojure.core/nth input__13581 1)
              input__13581_nth_2__
              (clojure.core/nth input__13581 2)]
             (clojure.core/case
              input__13581_nth_0__
              (meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__13581_nth_1__)
               (clojure.core/let
                [X__14101 (clojure.core/set input__13581_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__14101))
                 (clojure.core/loop
                  [search_space__14878 (clojure.core/seq X__14101)]
                  (if
                   (clojure.core/seq search_space__14878)
                   (clojure.core/let
                    [elem__14102
                     (clojure.core/first search_space__14878)
                     result__14879
                     (clojure.core/let
                      [elem__14102_nth_0__
                       (clojure.core/nth elem__14102 0)
                       elem__14102_nth_1__
                       (clojure.core/nth elem__14102 1)]
                      (clojure.core/let
                       [*m__13607 elem__14102_nth_0__]
                       (if
                        (clojure.core/symbol? elem__14102_nth_0__)
                        (clojure.core/let
                         [X__14104
                          (clojure.core/namespace elem__14102_nth_0__)]
                         (clojure.core/let
                          [?ns X__14104]
                          (clojure.core/let
                           [X__14106
                            (clojure.core/name elem__14102_nth_0__)]
                           (if
                            (clojure.core/string? X__14106)
                            (if
                             (clojure.core/re-matches #"&.*" X__14106)
                             (clojure.core/let
                              [?pattern elem__14102_nth_1__]
                              (clojure.core/let
                               [X__14108
                                ((clojure.core/fn
                                  [m__6987__auto__]
                                  (clojure.core/dissoc
                                   m__6987__auto__
                                   *m__13607))
                                 input__13581_nth_1__)]
                               (clojure.core/let
                                [?rest X__14108]
                                (clojure.core/let
                                 [x__7920__auto__
                                  (def__14096
                                   input__13581_nth_2__
                                   ?ns)]
                                 (if
                                  (meander.runtime.zeta/fail?
                                   x__7920__auto__)
                                  (meander.runtime.zeta/fail)
                                  (clojure.core/let
                                   [[?env ?ns] x__7920__auto__]
                                   (try
                                    [{:tag :rest-map,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9223__auto__
                                        (CATA__FN__13640
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9223__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9223__auto__
                                         0))),
                                      :next
                                      (clojure.core/let
                                       [CATA_RESULT__9223__auto__
                                        (CATA__FN__13640
                                         ['meander.dev.parse.zeta/parse-entries
                                          ?rest
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9223__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9223__auto__
                                         0)))}]
                                    (catch
                                     java.lang.Exception
                                     e__10163__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10163__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10163__auto__))))))))))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))))
                        (meander.runtime.zeta/fail))))]
                    (if
                     (meander.runtime.zeta/fail? result__14879)
                     (recur (clojure.core/next search_space__14878))
                     result__14879))
                   (state__14763)))
                 (state__14763)))
               (state__14763))
              (state__14763)))
            (state__14763))
           (state__14763))))
        (state__14763
         []
         (if
          (clojure.core/vector? input__13581)
          (clojure.core/letfn
           [(state__14881
             []
             (if
              (clojure.core/= (clojure.core/count input__13581) 3)
              (clojure.core/let
               [input__13581_nth_0__
                (clojure.core/nth input__13581 0)
                input__13581_nth_1__
                (clojure.core/nth input__13581 1)
                input__13581_nth_2__
                (clojure.core/nth input__13581 2)]
               (clojure.core/case
                input__13581_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (clojure.core/letfn
                 [(state__14883
                   []
                   (if
                    (clojure.core/map? input__13581_nth_1__)
                    (clojure.core/let
                     [X__14152 (clojure.core/set input__13581_nth_1__)]
                     (if
                      (clojure.core/<= 1 (clojure.core/count X__14152))
                      (clojure.core/loop
                       [search_space__14885
                        (clojure.core/seq X__14152)]
                       (if
                        (clojure.core/seq search_space__14885)
                        (clojure.core/let
                         [elem__14153
                          (clojure.core/first search_space__14885)
                          result__14886
                          (clojure.core/let
                           [elem__14153_nth_0__
                            (clojure.core/nth elem__14153 0)
                            elem__14153_nth_1__
                            (clojure.core/nth elem__14153 1)]
                           (clojure.core/let
                            [?key-pattern elem__14153_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__14153_nth_1__]
                             (clojure.core/let
                              [X__14155
                               ((clojure.core/fn
                                 [m__6987__auto__]
                                 (clojure.core/dissoc
                                  m__6987__auto__
                                  ?key-pattern))
                                input__13581_nth_1__)]
                              (clojure.core/let
                               [?rest X__14155]
                               (clojure.core/let
                                [?env input__13581_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__9223__auto__
                                     (CATA__FN__13640
                                      [?key-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__9223__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__9223__auto__
                                      0))),
                                   :val-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__9223__auto__
                                     (CATA__FN__13640
                                      [?val-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__9223__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__9223__auto__
                                      0))),
                                   :next
                                   (clojure.core/let
                                    [CATA_RESULT__9223__auto__
                                     (CATA__FN__13640
                                      ['meander.dev.parse.zeta/parse-entries
                                       ?rest
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__9223__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__9223__auto__
                                      0)))}]
                                 (catch
                                  java.lang.Exception
                                  e__10163__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__10163__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__10163__auto__))))))))))]
                         (if
                          (meander.runtime.zeta/fail? result__14886)
                          (recur
                           (clojure.core/next search_space__14885))
                          result__14886))
                        (state__14884)))
                      (state__14884)))
                    (state__14884)))
                  (state__14884
                   []
                   (if
                    (clojure.core/map? input__13581_nth_1__)
                    (clojure.core/let
                     [?env input__13581_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__10163__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10163__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10163__auto__)))))
                    (state__14882)))]
                 (state__14883))
                (meander.dev.parse.zeta/parse-with-bindings)
                (clojure.core/letfn
                 [(state__14888
                   []
                   (if
                    (clojure.core/vector? input__13581_nth_1__)
                    (clojure.core/case
                     input__13581_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__13581_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__10163__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10163__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10163__auto__)))))
                     (state__14889))
                    (state__14889)))
                  (state__14889
                   []
                   (if
                    (clojure.core/vector? input__13581_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__13581_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__13581_nth_2__]
                      (try
                       [[{:tag :error,
                          :message
                          "meander.zeta/with expects an even number of bindings"}]]
                       (catch
                        java.lang.Exception
                        e__10163__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10163__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10163__auto__)))))
                     (state__14890))
                    (state__14890)))
                  (state__14890
                   []
                   (if
                    (clojure.core/vector? input__13581_nth_1__)
                    (clojure.core/let
                     [input__13581_nth_1___l__
                      (clojure.core/subvec
                       input__13581_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__13581_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__13581_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__13581_nth_1___r__
                        (clojure.core/subvec input__13581_nth_1__ 2)]
                       (clojure.core/let
                        [input__13581_nth_1___l___nth_0__
                         (clojure.core/nth input__13581_nth_1___l__ 0)
                         input__13581_nth_1___l___nth_1__
                         (clojure.core/nth input__13581_nth_1___l__ 1)]
                        (if
                         (clojure.core/symbol?
                          input__13581_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__14169
                           (clojure.core/namespace
                            input__13581_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__14171
                            (clojure.core/name
                             input__13581_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__14171)
                            (if
                             (clojure.core/re-matches #"%.+" X__14171)
                             (clojure.core/let
                              [?symbol
                               input__13581_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__13581_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__13581_nth_1___r__]
                                (clojure.core/let
                                 [?env input__13581_nth_2__]
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
                                        [CATA_RESULT__9223__auto__
                                         (CATA__FN__13640
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9223__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9223__auto__
                                          0)))})
                                     (clojure.core/let
                                      [CATA_RESULT__9223__auto__
                                       (CATA__FN__13640
                                        ['meander.dev.parse.zeta/parse-with-bindings
                                         ?rest
                                         ?env])]
                                      (if
                                       (meander.runtime.zeta/fail?
                                        CATA_RESULT__9223__auto__)
                                       (throw
                                        (meander.runtime.zeta/fail))
                                       (clojure.core/nth
                                        CATA_RESULT__9223__auto__
                                        0)))))]
                                  (catch
                                   java.lang.Exception
                                   e__10163__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__10163__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__10163__auto__))))))))
                             (state__14891))
                            (state__14891))))
                         (state__14891))))
                      (state__14891)))
                    (state__14891)))
                  (state__14891
                   []
                   (if
                    (clojure.core/vector? input__13581_nth_1__)
                    (clojure.core/let
                     [input__13581_nth_1___l__
                      (clojure.core/subvec
                       input__13581_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__13581_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__13581_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__13581_nth_1___r__
                        (clojure.core/subvec input__13581_nth_1__ 2)]
                       (clojure.core/let
                        [input__13581_nth_1___l___nth_0__
                         (clojure.core/nth input__13581_nth_1___l__ 0)
                         input__13581_nth_1___l___nth_1__
                         (clojure.core/nth input__13581_nth_1___l__ 1)]
                        (clojure.core/let
                         [?x input__13581_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__13581_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__13581_nth_1___r__]
                           (clojure.core/let
                            [?env input__13581_nth_2__]
                            (try
                             [[{:tag :error,
                                :message
                                "meander.zeta/with bindings must be an repeating sequence of %name pattern"}]]
                             (catch
                              java.lang.Exception
                              e__10163__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__10163__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__10163__auto__))))))))))
                      (state__14882)))
                    (state__14882)))]
                 (state__14888))
                (state__14882)))
              (state__14882)))
            (state__14882
             []
             (if
              (clojure.core/= (clojure.core/count input__13581) 2)
              (clojure.core/let
               [input__13581_nth_0__
                (clojure.core/nth input__13581 0)
                input__13581_nth_1__
                (clojure.core/nth input__13581 1)]
               (if
                (clojure.core/vector? input__13581_nth_0__)
                (clojure.core/let
                 [?sequence input__13581_nth_0__]
                 (clojure.core/let
                  [?form input__13581_nth_0__]
                  (clojure.core/let
                   [?env input__13581_nth_1__]
                   (try
                    [{:tag :vector,
                      :next
                      (clojure.core/let
                       [CATA_RESULT__9223__auto__
                        (CATA__FN__13640
                         ['meander.dev.parse.zeta/parse-sequential
                          ?sequence
                          (clojure.core/let
                           [form__9322__auto__ {:context :vector}]
                           (clojure.core/merge
                            (clojure.core/into {} ?env)
                            form__9322__auto__))])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__9223__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__9223__auto__
                         0))),
                      :form ?form}]
                    (catch
                     java.lang.Exception
                     e__10163__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10163__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10163__auto__)))))))
                (state__14764)))
              (state__14764)))]
           (state__14881))
          (state__14764)))
        (state__14764
         []
         (clojure.core/letfn
          [(def__14181
            [arg__14204 ?__13582]
            (clojure.core/letfn
             [(state__14892
               []
               (clojure.core/let
                [x__14205 "meander.zeta"]
                (if
                 (clojure.core/= ?__13582 x__14205)
                 [?__13582]
                 (state__14893))))
              (state__14893
               []
               (if
                (clojure.core/map? arg__14204)
                (clojure.core/let
                 [VAL__14206 (.valAt arg__14204 :aliases)]
                 (if
                  (clojure.core/map? VAL__14206)
                  (clojure.core/let
                   [X__14208 (clojure.core/set VAL__14206)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14208))
                    (clojure.core/loop
                     [search_space__14894 (clojure.core/seq X__14208)]
                     (if
                      (clojure.core/seq search_space__14894)
                      (clojure.core/let
                       [elem__14209
                        (clojure.core/first search_space__14894)
                        result__14895
                        (clojure.core/let
                         [elem__14209_nth_0__
                          (clojure.core/nth elem__14209 0)
                          elem__14209_nth_1__
                          (clojure.core/nth elem__14209 1)]
                         (if
                          (clojure.core/symbol? elem__14209_nth_0__)
                          (clojure.core/let
                           [X__14211
                            (clojure.core/name elem__14209_nth_0__)]
                           (if
                            (clojure.core/= ?__13582 X__14211)
                            (if
                             (clojure.core/symbol? elem__14209_nth_1__)
                             (clojure.core/let
                              [X__14213
                               (clojure.core/name elem__14209_nth_1__)]
                              (clojure.core/case
                               X__14213
                               ("meander.zeta")
                               [?__13582]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__14895)
                        (recur (clojure.core/next search_space__14894))
                        result__14895))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__14892)))]
          (if
           (clojure.core/vector? input__13581)
           (if
            (clojure.core/= (clojure.core/count input__13581) 2)
            (clojure.core/let
             [input__13581_nth_0__
              (clojure.core/nth input__13581 0)
              input__13581_nth_1__
              (clojure.core/nth input__13581 1)]
             (if
              (clojure.core/seq? input__13581_nth_0__)
              (clojure.core/let
               [input__13581_nth_0___l__
                (clojure.core/take 1 input__13581_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__13581_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__13581_nth_0___r__
                  (clojure.core/drop 1 input__13581_nth_0__)]
                 (clojure.core/let
                  [input__13581_nth_0___l___nth_0__
                   (clojure.core/nth input__13581_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__13581_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__14191
                     (clojure.core/namespace
                      input__13581_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__13582 X__14191]
                     (clojure.core/let
                      [X__14193
                       (clojure.core/name
                        input__13581_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__14193
                       ("<>")
                       (clojure.core/let
                        [x__7920__auto__
                         (def__14181 input__13581_nth_1__ ?__13582)]
                        (if
                         (meander.runtime.zeta/fail? x__7920__auto__)
                         (state__14765)
                         (clojure.core/let
                          [[?__13582] x__7920__auto__]
                          (if
                           (clojure.core/vector? input__13581)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__13581)
                             2)
                            (clojure.core/let
                             [input__13581_nth_0__
                              (clojure.core/nth input__13581 0)
                              input__13581_nth_1__
                              (clojure.core/nth input__13581 1)]
                             (if
                              (clojure.core/seq? input__13581_nth_0__)
                              (clojure.core/let
                               [input__13581_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__13581_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__13581_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__13581_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__13581_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__13581_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__13581_nth_0__]
                                   (clojure.core/let
                                    [?env input__13581_nth_1__]
                                    (try
                                     [{:tag :group,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__9223__auto__
                                         (CATA__FN__13640
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?patterns)
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9223__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9223__auto__
                                          0)))}]
                                     (catch
                                      java.lang.Exception
                                      e__10163__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10163__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10163__auto__))))))))
                                (state__14765)))
                              (state__14765)))
                            (state__14765))
                           (state__14765)))))
                       (state__14765)))))
                   (state__14765))))
                (state__14765)))
              (state__14765)))
            (state__14765))
           (state__14765))))
        (state__14765
         []
         (clojure.core/letfn
          [(def__14215
            [arg__14238 ?__13583]
            (clojure.core/letfn
             [(state__14897
               []
               (clojure.core/let
                [x__14239 "meander.zeta"]
                (if
                 (clojure.core/= ?__13583 x__14239)
                 [?__13583]
                 (state__14898))))
              (state__14898
               []
               (if
                (clojure.core/map? arg__14238)
                (clojure.core/let
                 [VAL__14240 (.valAt arg__14238 :aliases)]
                 (if
                  (clojure.core/map? VAL__14240)
                  (clojure.core/let
                   [X__14242 (clojure.core/set VAL__14240)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14242))
                    (clojure.core/loop
                     [search_space__14899 (clojure.core/seq X__14242)]
                     (if
                      (clojure.core/seq search_space__14899)
                      (clojure.core/let
                       [elem__14243
                        (clojure.core/first search_space__14899)
                        result__14900
                        (clojure.core/let
                         [elem__14243_nth_0__
                          (clojure.core/nth elem__14243 0)
                          elem__14243_nth_1__
                          (clojure.core/nth elem__14243 1)]
                         (if
                          (clojure.core/symbol? elem__14243_nth_0__)
                          (clojure.core/let
                           [X__14245
                            (clojure.core/name elem__14243_nth_0__)]
                           (if
                            (clojure.core/= ?__13583 X__14245)
                            (if
                             (clojure.core/symbol? elem__14243_nth_1__)
                             (clojure.core/let
                              [X__14247
                               (clojure.core/name elem__14243_nth_1__)]
                              (clojure.core/case
                               X__14247
                               ("meander.zeta")
                               [?__13583]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__14900)
                        (recur (clojure.core/next search_space__14899))
                        result__14900))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__14897)))]
          (if
           (clojure.core/vector? input__13581)
           (if
            (clojure.core/= (clojure.core/count input__13581) 2)
            (clojure.core/let
             [input__13581_nth_0__
              (clojure.core/nth input__13581 0)
              input__13581_nth_1__
              (clojure.core/nth input__13581 1)]
             (if
              (clojure.core/seq? input__13581_nth_0__)
              (clojure.core/let
               [input__13581_nth_0___l__
                (clojure.core/take 1 input__13581_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__13581_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__13581_nth_0___r__
                  (clojure.core/drop 1 input__13581_nth_0__)]
                 (clojure.core/let
                  [input__13581_nth_0___l___nth_0__
                   (clojure.core/nth input__13581_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__13581_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__14225
                     (clojure.core/namespace
                      input__13581_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__13583 X__14225]
                     (clojure.core/let
                      [X__14227
                       (clojure.core/name
                        input__13581_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__14227
                       ("with")
                       (clojure.core/let
                        [x__7920__auto__
                         (def__14215 input__13581_nth_1__ ?__13583)]
                        (if
                         (meander.runtime.zeta/fail? x__7920__auto__)
                         (state__14766)
                         (clojure.core/let
                          [[?__13583] x__7920__auto__]
                          (if
                           (clojure.core/vector? input__13581)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__13581)
                             2)
                            (clojure.core/let
                             [input__13581_nth_0__
                              (clojure.core/nth input__13581 0)
                              input__13581_nth_1__
                              (clojure.core/nth input__13581 1)]
                             (if
                              (clojure.core/seq? input__13581_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__13581_nth_0__)
                                3)
                               (clojure.core/let
                                [input__13581_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__13581_nth_0__
                                  1)
                                 input__13581_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__13581_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__13581_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__13581_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__13581_nth_0__]
                                   (clojure.core/let
                                    [?env input__13581_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__9223__auto__
                                          (CATA__FN__13640
                                           ['meander.dev.parse.zeta/parse-with-bindings
                                            ?bindings
                                            ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__9223__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__9223__auto__
                                           0)))},
                                       :body
                                       (clojure.core/let
                                        [CATA_RESULT__9223__auto__
                                         (CATA__FN__13640
                                          [?body ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9223__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9223__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10163__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10163__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10163__auto__)))))))))
                               (state__14766))
                              (state__14766)))
                            (state__14766))
                           (state__14766)))))
                       (state__14766)))))
                   (state__14766))))
                (state__14766)))
              (state__14766)))
            (state__14766))
           (state__14766))))
        (state__14766
         []
         (clojure.core/letfn
          [(def__14249
            [arg__14272 ?__13584]
            (clojure.core/letfn
             [(state__14902
               []
               (clojure.core/let
                [x__14273 "meander.zeta"]
                (if
                 (clojure.core/= ?__13584 x__14273)
                 [?__13584]
                 (state__14903))))
              (state__14903
               []
               (if
                (clojure.core/map? arg__14272)
                (clojure.core/let
                 [VAL__14274 (.valAt arg__14272 :aliases)]
                 (if
                  (clojure.core/map? VAL__14274)
                  (clojure.core/let
                   [X__14276 (clojure.core/set VAL__14274)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14276))
                    (clojure.core/loop
                     [search_space__14904 (clojure.core/seq X__14276)]
                     (if
                      (clojure.core/seq search_space__14904)
                      (clojure.core/let
                       [elem__14277
                        (clojure.core/first search_space__14904)
                        result__14905
                        (clojure.core/let
                         [elem__14277_nth_0__
                          (clojure.core/nth elem__14277 0)
                          elem__14277_nth_1__
                          (clojure.core/nth elem__14277 1)]
                         (if
                          (clojure.core/symbol? elem__14277_nth_0__)
                          (clojure.core/let
                           [X__14279
                            (clojure.core/name elem__14277_nth_0__)]
                           (if
                            (clojure.core/= ?__13584 X__14279)
                            (if
                             (clojure.core/symbol? elem__14277_nth_1__)
                             (clojure.core/let
                              [X__14281
                               (clojure.core/name elem__14277_nth_1__)]
                              (clojure.core/case
                               X__14281
                               ("meander.zeta")
                               [?__13584]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__14905)
                        (recur (clojure.core/next search_space__14904))
                        result__14905))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__14902)))]
          (if
           (clojure.core/vector? input__13581)
           (if
            (clojure.core/= (clojure.core/count input__13581) 2)
            (clojure.core/let
             [input__13581_nth_0__
              (clojure.core/nth input__13581 0)
              input__13581_nth_1__
              (clojure.core/nth input__13581 1)]
             (if
              (clojure.core/seq? input__13581_nth_0__)
              (clojure.core/let
               [input__13581_nth_0___l__
                (clojure.core/take 1 input__13581_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__13581_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__13581_nth_0___r__
                  (clojure.core/drop 1 input__13581_nth_0__)]
                 (clojure.core/let
                  [input__13581_nth_0___l___nth_0__
                   (clojure.core/nth input__13581_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__13581_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__14259
                     (clojure.core/namespace
                      input__13581_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__13584 X__14259]
                     (clojure.core/let
                      [X__14261
                       (clojure.core/name
                        input__13581_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__14261
                       ("apply")
                       (clojure.core/let
                        [x__7920__auto__
                         (def__14249 input__13581_nth_1__ ?__13584)]
                        (if
                         (meander.runtime.zeta/fail? x__7920__auto__)
                         (state__14767)
                         (clojure.core/let
                          [[?__13584] x__7920__auto__]
                          (if
                           (clojure.core/vector? input__13581)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__13581)
                             2)
                            (clojure.core/let
                             [input__13581_nth_0__
                              (clojure.core/nth input__13581 0)
                              input__13581_nth_1__
                              (clojure.core/nth input__13581 1)]
                             (if
                              (clojure.core/seq? input__13581_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__13581_nth_0__)
                                3)
                               (clojure.core/let
                                [input__13581_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__13581_nth_0__
                                  1)
                                 input__13581_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__13581_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__13581_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__13581_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__13581_nth_0__]
                                   (clojure.core/let
                                    [?env input__13581_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__9223__auto__
                                         (CATA__FN__13640
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9223__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9223__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10163__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10163__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10163__auto__)))))))))
                               (state__14767))
                              (state__14767)))
                            (state__14767))
                           (state__14767)))))
                       (state__14767)))))
                   (state__14767))))
                (state__14767)))
              (state__14767)))
            (state__14767))
           (state__14767))))
        (state__14767
         []
         (clojure.core/letfn
          [(def__14283
            [arg__14306 ?__13585]
            (clojure.core/letfn
             [(state__14907
               []
               (clojure.core/let
                [x__14307 "meander.zeta"]
                (if
                 (clojure.core/= ?__13585 x__14307)
                 [?__13585]
                 (state__14908))))
              (state__14908
               []
               (if
                (clojure.core/map? arg__14306)
                (clojure.core/let
                 [VAL__14308 (.valAt arg__14306 :aliases)]
                 (if
                  (clojure.core/map? VAL__14308)
                  (clojure.core/let
                   [X__14310 (clojure.core/set VAL__14308)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14310))
                    (clojure.core/loop
                     [search_space__14909 (clojure.core/seq X__14310)]
                     (if
                      (clojure.core/seq search_space__14909)
                      (clojure.core/let
                       [elem__14311
                        (clojure.core/first search_space__14909)
                        result__14910
                        (clojure.core/let
                         [elem__14311_nth_0__
                          (clojure.core/nth elem__14311 0)
                          elem__14311_nth_1__
                          (clojure.core/nth elem__14311 1)]
                         (if
                          (clojure.core/symbol? elem__14311_nth_0__)
                          (clojure.core/let
                           [X__14313
                            (clojure.core/name elem__14311_nth_0__)]
                           (if
                            (clojure.core/= ?__13585 X__14313)
                            (if
                             (clojure.core/symbol? elem__14311_nth_1__)
                             (clojure.core/let
                              [X__14315
                               (clojure.core/name elem__14311_nth_1__)]
                              (clojure.core/case
                               X__14315
                               ("meander.zeta")
                               [?__13585]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__14910)
                        (recur (clojure.core/next search_space__14909))
                        result__14910))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__14907)))]
          (if
           (clojure.core/vector? input__13581)
           (if
            (clojure.core/= (clojure.core/count input__13581) 2)
            (clojure.core/let
             [input__13581_nth_0__
              (clojure.core/nth input__13581 0)
              input__13581_nth_1__
              (clojure.core/nth input__13581 1)]
             (if
              (clojure.core/seq? input__13581_nth_0__)
              (clojure.core/let
               [input__13581_nth_0___l__
                (clojure.core/take 1 input__13581_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__13581_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__13581_nth_0___r__
                  (clojure.core/drop 1 input__13581_nth_0__)]
                 (clojure.core/let
                  [input__13581_nth_0___l___nth_0__
                   (clojure.core/nth input__13581_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__13581_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__14293
                     (clojure.core/namespace
                      input__13581_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__13585 X__14293]
                     (clojure.core/let
                      [X__14295
                       (clojure.core/name
                        input__13581_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__14295
                       ("and")
                       (clojure.core/let
                        [x__7920__auto__
                         (def__14283 input__13581_nth_1__ ?__13585)]
                        (if
                         (meander.runtime.zeta/fail? x__7920__auto__)
                         (state__14768)
                         (clojure.core/let
                          [[?__13585] x__7920__auto__]
                          (if
                           (clojure.core/vector? input__13581)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__13581)
                             2)
                            (clojure.core/let
                             [input__13581_nth_0__
                              (clojure.core/nth input__13581 0)
                              input__13581_nth_1__
                              (clojure.core/nth input__13581 1)]
                             (if
                              (clojure.core/seq? input__13581_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__13581_nth_0__)
                                3)
                               (clojure.core/let
                                [input__13581_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__13581_nth_0__
                                  1)
                                 input__13581_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__13581_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__13581_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right input__13581_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__13581_nth_0__]
                                   (clojure.core/let
                                    [?env input__13581_nth_1__]
                                    (try
                                     [{:tag :and,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__9223__auto__
                                         (CATA__FN__13640
                                          [?left ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9223__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9223__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__9223__auto__
                                         (CATA__FN__13640
                                          [?right ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9223__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9223__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10163__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10163__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10163__auto__)))))))))
                               (state__14768))
                              (state__14768)))
                            (state__14768))
                           (state__14768)))))
                       (state__14768)))))
                   (state__14768))))
                (state__14768)))
              (state__14768)))
            (state__14768))
           (state__14768))))
        (state__14768
         []
         (clojure.core/letfn
          [(def__14317
            [arg__14340 ?__13586]
            (clojure.core/letfn
             [(state__14912
               []
               (clojure.core/let
                [x__14341 "meander.zeta"]
                (if
                 (clojure.core/= ?__13586 x__14341)
                 [?__13586]
                 (state__14913))))
              (state__14913
               []
               (if
                (clojure.core/map? arg__14340)
                (clojure.core/let
                 [VAL__14342 (.valAt arg__14340 :aliases)]
                 (if
                  (clojure.core/map? VAL__14342)
                  (clojure.core/let
                   [X__14344 (clojure.core/set VAL__14342)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14344))
                    (clojure.core/loop
                     [search_space__14914 (clojure.core/seq X__14344)]
                     (if
                      (clojure.core/seq search_space__14914)
                      (clojure.core/let
                       [elem__14345
                        (clojure.core/first search_space__14914)
                        result__14915
                        (clojure.core/let
                         [elem__14345_nth_0__
                          (clojure.core/nth elem__14345 0)
                          elem__14345_nth_1__
                          (clojure.core/nth elem__14345 1)]
                         (if
                          (clojure.core/symbol? elem__14345_nth_0__)
                          (clojure.core/let
                           [X__14347
                            (clojure.core/name elem__14345_nth_0__)]
                           (if
                            (clojure.core/= ?__13586 X__14347)
                            (if
                             (clojure.core/symbol? elem__14345_nth_1__)
                             (clojure.core/let
                              [X__14349
                               (clojure.core/name elem__14345_nth_1__)]
                              (clojure.core/case
                               X__14349
                               ("meander.zeta")
                               [?__13586]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__14915)
                        (recur (clojure.core/next search_space__14914))
                        result__14915))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__14912)))]
          (if
           (clojure.core/vector? input__13581)
           (if
            (clojure.core/= (clojure.core/count input__13581) 2)
            (clojure.core/let
             [input__13581_nth_0__
              (clojure.core/nth input__13581 0)
              input__13581_nth_1__
              (clojure.core/nth input__13581 1)]
             (if
              (clojure.core/seq? input__13581_nth_0__)
              (clojure.core/let
               [input__13581_nth_0___l__
                (clojure.core/take 1 input__13581_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__13581_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__13581_nth_0___r__
                  (clojure.core/drop 1 input__13581_nth_0__)]
                 (clojure.core/let
                  [input__13581_nth_0___l___nth_0__
                   (clojure.core/nth input__13581_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__13581_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__14327
                     (clojure.core/namespace
                      input__13581_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__13586 X__14327]
                     (clojure.core/let
                      [X__14329
                       (clojure.core/name
                        input__13581_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__14329
                       ("cata")
                       (clojure.core/let
                        [x__7920__auto__
                         (def__14317 input__13581_nth_1__ ?__13586)]
                        (if
                         (meander.runtime.zeta/fail? x__7920__auto__)
                         (state__14769)
                         (clojure.core/let
                          [[?__13586] x__7920__auto__]
                          (if
                           (clojure.core/vector? input__13581)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__13581)
                             2)
                            (clojure.core/let
                             [input__13581_nth_0__
                              (clojure.core/nth input__13581 0)
                              input__13581_nth_1__
                              (clojure.core/nth input__13581 1)]
                             (if
                              (clojure.core/seq? input__13581_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__13581_nth_0__)
                                2)
                               (clojure.core/let
                                [input__13581_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__13581_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__13581_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__13581_nth_0__]
                                  (clojure.core/let
                                   [?env input__13581_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9223__auto__
                                        (CATA__FN__13640
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9223__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9223__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__10163__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10163__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10163__auto__))))))))
                               (state__14769))
                              (state__14769)))
                            (state__14769))
                           (state__14769)))))
                       (state__14769)))))
                   (state__14769))))
                (state__14769)))
              (state__14769)))
            (state__14769))
           (state__14769))))
        (state__14769
         []
         (clojure.core/letfn
          [(def__14351
            [arg__14374 ?__13587]
            (clojure.core/letfn
             [(state__14917
               []
               (clojure.core/let
                [x__14375 "meander.zeta"]
                (if
                 (clojure.core/= ?__13587 x__14375)
                 [?__13587]
                 (state__14918))))
              (state__14918
               []
               (if
                (clojure.core/map? arg__14374)
                (clojure.core/let
                 [VAL__14376 (.valAt arg__14374 :aliases)]
                 (if
                  (clojure.core/map? VAL__14376)
                  (clojure.core/let
                   [X__14378 (clojure.core/set VAL__14376)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14378))
                    (clojure.core/loop
                     [search_space__14919 (clojure.core/seq X__14378)]
                     (if
                      (clojure.core/seq search_space__14919)
                      (clojure.core/let
                       [elem__14379
                        (clojure.core/first search_space__14919)
                        result__14920
                        (clojure.core/let
                         [elem__14379_nth_0__
                          (clojure.core/nth elem__14379 0)
                          elem__14379_nth_1__
                          (clojure.core/nth elem__14379 1)]
                         (if
                          (clojure.core/symbol? elem__14379_nth_0__)
                          (clojure.core/let
                           [X__14381
                            (clojure.core/name elem__14379_nth_0__)]
                           (if
                            (clojure.core/= ?__13587 X__14381)
                            (if
                             (clojure.core/symbol? elem__14379_nth_1__)
                             (clojure.core/let
                              [X__14383
                               (clojure.core/name elem__14379_nth_1__)]
                              (clojure.core/case
                               X__14383
                               ("meander.zeta")
                               [?__13587]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__14920)
                        (recur (clojure.core/next search_space__14919))
                        result__14920))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__14917)))]
          (if
           (clojure.core/vector? input__13581)
           (if
            (clojure.core/= (clojure.core/count input__13581) 2)
            (clojure.core/let
             [input__13581_nth_0__
              (clojure.core/nth input__13581 0)
              input__13581_nth_1__
              (clojure.core/nth input__13581 1)]
             (if
              (clojure.core/seq? input__13581_nth_0__)
              (clojure.core/let
               [input__13581_nth_0___l__
                (clojure.core/take 1 input__13581_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__13581_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__13581_nth_0___r__
                  (clojure.core/drop 1 input__13581_nth_0__)]
                 (clojure.core/let
                  [input__13581_nth_0___l___nth_0__
                   (clojure.core/nth input__13581_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__13581_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__14361
                     (clojure.core/namespace
                      input__13581_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__13587 X__14361]
                     (clojure.core/let
                      [X__14363
                       (clojure.core/name
                        input__13581_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__14363
                       ("fold")
                       (clojure.core/let
                        [x__7920__auto__
                         (def__14351 input__13581_nth_1__ ?__13587)]
                        (if
                         (meander.runtime.zeta/fail? x__7920__auto__)
                         (state__14770)
                         (clojure.core/let
                          [[?__13587] x__7920__auto__]
                          (if
                           (clojure.core/vector? input__13581)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__13581)
                             2)
                            (clojure.core/let
                             [input__13581_nth_0__
                              (clojure.core/nth input__13581 0)
                              input__13581_nth_1__
                              (clojure.core/nth input__13581 1)]
                             (if
                              (clojure.core/seq? input__13581_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__13581_nth_0__)
                                4)
                               (clojure.core/let
                                [input__13581_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__13581_nth_0__
                                  1)
                                 input__13581_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__13581_nth_0__
                                  2)
                                 input__13581_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__13581_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__13581_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__13581_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__13581_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__13581_nth_0__]
                                    (clojure.core/let
                                     [?env input__13581_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__9223__auto__
                                         (CATA__FN__13640
                                          ['meander.dev.parse.zeta/make-fold
                                           (clojure.core/let
                                            [CATA_RESULT__9223__auto__
                                             (CATA__FN__13640
                                              [?mutable-variable
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9223__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9223__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__9223__auto__
                                             (CATA__FN__13640
                                              [?initial-value ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9223__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9223__auto__
                                              0)))
                                           ?fold-function
                                           ?form])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9223__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9223__auto__
                                          0)))]
                                      (catch
                                       java.lang.Exception
                                       e__10163__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__10163__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__10163__auto__))))))))))
                               (state__14770))
                              (state__14770)))
                            (state__14770))
                           (state__14770)))))
                       (state__14770)))))
                   (state__14770))))
                (state__14770)))
              (state__14770)))
            (state__14770))
           (state__14770))))
        (state__14770
         []
         (if
          (clojure.core/vector? input__13581)
          (if
           (clojure.core/= (clojure.core/count input__13581) 5)
           (clojure.core/let
            [input__13581_nth_0__
             (clojure.core/nth input__13581 0)
             input__13581_nth_1__
             (clojure.core/nth input__13581 1)
             input__13581_nth_2__
             (clojure.core/nth input__13581 2)
             input__13581_nth_3__
             (clojure.core/nth input__13581 3)
             input__13581_nth_4__
             (clojure.core/nth input__13581 4)]
            (clojure.core/case
             input__13581_nth_0__
             (meander.dev.parse.zeta/make-fold)
             (if
              (clojure.core/map? input__13581_nth_1__)
              (clojure.core/let
               [VAL__14386 (.valAt input__13581_nth_1__ :tag)]
               (clojure.core/case
                VAL__14386
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__13581_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__13581_nth_2__]
                  (clojure.core/let
                   [?fold-function input__13581_nth_3__]
                   (clojure.core/let
                    [?form input__13581_nth_4__]
                    (try
                     [{:tag :fold,
                       :variable ?variable-ast,
                       :initial-value ?initial-value-ast,
                       :fold-function
                       {:tag :host-expression, :form ?fold-function},
                       :form ?form}]
                     (catch
                      java.lang.Exception
                      e__10163__auto__
                      (if
                       (meander.runtime.zeta/fail? e__10163__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__10163__auto__))))))))
                (state__14771)))
              (state__14771))
             (state__14771)))
           (state__14771))
          (state__14771)))
        (state__14771
         []
         (clojure.core/letfn
          [(def__14388
            [arg__14411 ?__13588]
            (clojure.core/letfn
             [(state__14922
               []
               (clojure.core/let
                [x__14412 "meander.zeta"]
                (if
                 (clojure.core/= ?__13588 x__14412)
                 [?__13588]
                 (state__14923))))
              (state__14923
               []
               (if
                (clojure.core/map? arg__14411)
                (clojure.core/let
                 [VAL__14413 (.valAt arg__14411 :aliases)]
                 (if
                  (clojure.core/map? VAL__14413)
                  (clojure.core/let
                   [X__14415 (clojure.core/set VAL__14413)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14415))
                    (clojure.core/loop
                     [search_space__14924 (clojure.core/seq X__14415)]
                     (if
                      (clojure.core/seq search_space__14924)
                      (clojure.core/let
                       [elem__14416
                        (clojure.core/first search_space__14924)
                        result__14925
                        (clojure.core/let
                         [elem__14416_nth_0__
                          (clojure.core/nth elem__14416 0)
                          elem__14416_nth_1__
                          (clojure.core/nth elem__14416 1)]
                         (if
                          (clojure.core/symbol? elem__14416_nth_0__)
                          (clojure.core/let
                           [X__14418
                            (clojure.core/name elem__14416_nth_0__)]
                           (if
                            (clojure.core/= ?__13588 X__14418)
                            (if
                             (clojure.core/symbol? elem__14416_nth_1__)
                             (clojure.core/let
                              [X__14420
                               (clojure.core/name elem__14416_nth_1__)]
                              (clojure.core/case
                               X__14420
                               ("meander.zeta")
                               [?__13588]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__14925)
                        (recur (clojure.core/next search_space__14924))
                        result__14925))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__14922)))]
          (if
           (clojure.core/vector? input__13581)
           (if
            (clojure.core/= (clojure.core/count input__13581) 2)
            (clojure.core/let
             [input__13581_nth_0__
              (clojure.core/nth input__13581 0)
              input__13581_nth_1__
              (clojure.core/nth input__13581 1)]
             (if
              (clojure.core/seq? input__13581_nth_0__)
              (clojure.core/let
               [input__13581_nth_0___l__
                (clojure.core/take 1 input__13581_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__13581_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__13581_nth_0___r__
                  (clojure.core/drop 1 input__13581_nth_0__)]
                 (clojure.core/let
                  [input__13581_nth_0___l___nth_0__
                   (clojure.core/nth input__13581_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__13581_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__14398
                     (clojure.core/namespace
                      input__13581_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__13588 X__14398]
                     (clojure.core/let
                      [X__14400
                       (clojure.core/name
                        input__13581_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__14400
                       ("let")
                       (clojure.core/let
                        [x__7920__auto__
                         (def__14388 input__13581_nth_1__ ?__13588)]
                        (if
                         (meander.runtime.zeta/fail? x__7920__auto__)
                         (state__14772)
                         (clojure.core/let
                          [[?__13588] x__7920__auto__]
                          (if
                           (clojure.core/vector? input__13581)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__13581)
                             2)
                            (clojure.core/let
                             [input__13581_nth_0__
                              (clojure.core/nth input__13581 0)
                              input__13581_nth_1__
                              (clojure.core/nth input__13581 1)]
                             (if
                              (clojure.core/seq? input__13581_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__13581_nth_0__)
                                3)
                               (clojure.core/let
                                [input__13581_nth_0___nth_0__
                                 (clojure.core/nth
                                  input__13581_nth_0__
                                  0)
                                 input__13581_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__13581_nth_0__
                                  1)
                                 input__13581_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__13581_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__13581_nth_0___nth_0__]
                                 (clojure.core/let
                                  [?expression
                                   input__13581_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?next input__13581_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?form input__13581_nth_0__]
                                    (clojure.core/let
                                     [?env input__13581_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__9223__auto__
                                          (CATA__FN__13640
                                           [?pattern ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__9223__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__9223__auto__
                                           0))),
                                        :expression
                                        {:tag :host-expression,
                                         :form ?expression},
                                        :next
                                        (clojure.core/let
                                         [CATA_RESULT__9223__auto__
                                          (CATA__FN__13640
                                           [?next ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__9223__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__9223__auto__
                                           0)))}]
                                      (catch
                                       java.lang.Exception
                                       e__10163__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__10163__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__10163__auto__))))))))))
                               (state__14772))
                              (state__14772)))
                            (state__14772))
                           (state__14772)))))
                       (state__14772)))))
                   (state__14772))))
                (state__14772)))
              (state__14772)))
            (state__14772))
           (state__14772))))
        (state__14772
         []
         (clojure.core/letfn
          [(def__14422
            [arg__14445 ?__13589]
            (clojure.core/letfn
             [(state__14927
               []
               (clojure.core/let
                [x__14446 "meander.zeta"]
                (if
                 (clojure.core/= ?__13589 x__14446)
                 [?__13589]
                 (state__14928))))
              (state__14928
               []
               (if
                (clojure.core/map? arg__14445)
                (clojure.core/let
                 [VAL__14447 (.valAt arg__14445 :aliases)]
                 (if
                  (clojure.core/map? VAL__14447)
                  (clojure.core/let
                   [X__14449 (clojure.core/set VAL__14447)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14449))
                    (clojure.core/loop
                     [search_space__14929 (clojure.core/seq X__14449)]
                     (if
                      (clojure.core/seq search_space__14929)
                      (clojure.core/let
                       [elem__14450
                        (clojure.core/first search_space__14929)
                        result__14930
                        (clojure.core/let
                         [elem__14450_nth_0__
                          (clojure.core/nth elem__14450 0)
                          elem__14450_nth_1__
                          (clojure.core/nth elem__14450 1)]
                         (if
                          (clojure.core/symbol? elem__14450_nth_0__)
                          (clojure.core/let
                           [X__14452
                            (clojure.core/name elem__14450_nth_0__)]
                           (if
                            (clojure.core/= ?__13589 X__14452)
                            (if
                             (clojure.core/symbol? elem__14450_nth_1__)
                             (clojure.core/let
                              [X__14454
                               (clojure.core/name elem__14450_nth_1__)]
                              (clojure.core/case
                               X__14454
                               ("meander.zeta")
                               [?__13589]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__14930)
                        (recur (clojure.core/next search_space__14929))
                        result__14930))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__14927)))]
          (if
           (clojure.core/vector? input__13581)
           (if
            (clojure.core/= (clojure.core/count input__13581) 2)
            (clojure.core/let
             [input__13581_nth_0__
              (clojure.core/nth input__13581 0)
              input__13581_nth_1__
              (clojure.core/nth input__13581 1)]
             (if
              (clojure.core/seq? input__13581_nth_0__)
              (clojure.core/let
               [input__13581_nth_0___l__
                (clojure.core/take 1 input__13581_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__13581_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__13581_nth_0___r__
                  (clojure.core/drop 1 input__13581_nth_0__)]
                 (clojure.core/let
                  [input__13581_nth_0___l___nth_0__
                   (clojure.core/nth input__13581_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__13581_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__14432
                     (clojure.core/namespace
                      input__13581_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__13589 X__14432]
                     (clojure.core/let
                      [X__14434
                       (clojure.core/name
                        input__13581_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__14434
                       ("not")
                       (clojure.core/let
                        [x__7920__auto__
                         (def__14422 input__13581_nth_1__ ?__13589)]
                        (if
                         (meander.runtime.zeta/fail? x__7920__auto__)
                         (state__14773)
                         (clojure.core/let
                          [[?__13589] x__7920__auto__]
                          (if
                           (clojure.core/vector? input__13581)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__13581)
                             2)
                            (clojure.core/let
                             [input__13581_nth_0__
                              (clojure.core/nth input__13581 0)
                              input__13581_nth_1__
                              (clojure.core/nth input__13581 1)]
                             (if
                              (clojure.core/seq? input__13581_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__13581_nth_0__)
                                2)
                               (clojure.core/let
                                [input__13581_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__13581_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__13581_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__13581_nth_0__]
                                  (clojure.core/let
                                   [?env input__13581_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9223__auto__
                                        (CATA__FN__13640
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9223__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9223__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__10163__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10163__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10163__auto__))))))))
                               (state__14773))
                              (state__14773)))
                            (state__14773))
                           (state__14773)))))
                       (state__14773)))))
                   (state__14773))))
                (state__14773)))
              (state__14773)))
            (state__14773))
           (state__14773))))
        (state__14773
         []
         (clojure.core/letfn
          [(def__14456
            [arg__14479 ?__13590]
            (clojure.core/letfn
             [(state__14932
               []
               (clojure.core/let
                [x__14480 "meander.zeta"]
                (if
                 (clojure.core/= ?__13590 x__14480)
                 [?__13590]
                 (state__14933))))
              (state__14933
               []
               (if
                (clojure.core/map? arg__14479)
                (clojure.core/let
                 [VAL__14481 (.valAt arg__14479 :aliases)]
                 (if
                  (clojure.core/map? VAL__14481)
                  (clojure.core/let
                   [X__14483 (clojure.core/set VAL__14481)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14483))
                    (clojure.core/loop
                     [search_space__14934 (clojure.core/seq X__14483)]
                     (if
                      (clojure.core/seq search_space__14934)
                      (clojure.core/let
                       [elem__14484
                        (clojure.core/first search_space__14934)
                        result__14935
                        (clojure.core/let
                         [elem__14484_nth_0__
                          (clojure.core/nth elem__14484 0)
                          elem__14484_nth_1__
                          (clojure.core/nth elem__14484 1)]
                         (if
                          (clojure.core/symbol? elem__14484_nth_0__)
                          (clojure.core/let
                           [X__14486
                            (clojure.core/name elem__14484_nth_0__)]
                           (if
                            (clojure.core/= ?__13590 X__14486)
                            (if
                             (clojure.core/symbol? elem__14484_nth_1__)
                             (clojure.core/let
                              [X__14488
                               (clojure.core/name elem__14484_nth_1__)]
                              (clojure.core/case
                               X__14488
                               ("meander.zeta")
                               [?__13590]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__14935)
                        (recur (clojure.core/next search_space__14934))
                        result__14935))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__14932)))]
          (if
           (clojure.core/vector? input__13581)
           (if
            (clojure.core/= (clojure.core/count input__13581) 2)
            (clojure.core/let
             [input__13581_nth_0__
              (clojure.core/nth input__13581 0)
              input__13581_nth_1__
              (clojure.core/nth input__13581 1)]
             (if
              (clojure.core/seq? input__13581_nth_0__)
              (clojure.core/let
               [input__13581_nth_0___l__
                (clojure.core/take 1 input__13581_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__13581_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__13581_nth_0___r__
                  (clojure.core/drop 1 input__13581_nth_0__)]
                 (clojure.core/let
                  [input__13581_nth_0___l___nth_0__
                   (clojure.core/nth input__13581_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__13581_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__14466
                     (clojure.core/namespace
                      input__13581_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__13590 X__14466]
                     (clojure.core/let
                      [X__14468
                       (clojure.core/name
                        input__13581_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__14468
                       ("or")
                       (clojure.core/let
                        [x__7920__auto__
                         (def__14456 input__13581_nth_1__ ?__13590)]
                        (if
                         (meander.runtime.zeta/fail? x__7920__auto__)
                         (state__14774)
                         (clojure.core/let
                          [[?__13590] x__7920__auto__]
                          (if
                           (clojure.core/vector? input__13581)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__13581)
                             2)
                            (clojure.core/let
                             [input__13581_nth_0__
                              (clojure.core/nth input__13581 0)
                              input__13581_nth_1__
                              (clojure.core/nth input__13581 1)]
                             (if
                              (clojure.core/seq? input__13581_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__13581_nth_0__)
                                3)
                               (clojure.core/let
                                [input__13581_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__13581_nth_0__
                                  1)
                                 input__13581_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__13581_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__13581_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right input__13581_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__13581_nth_0__]
                                   (clojure.core/let
                                    [?env input__13581_nth_1__]
                                    (try
                                     [{:tag :or,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__9223__auto__
                                         (CATA__FN__13640
                                          [?left ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9223__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9223__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__9223__auto__
                                         (CATA__FN__13640
                                          [?right ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9223__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9223__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10163__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10163__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10163__auto__)))))))))
                               (state__14774))
                              (state__14774)))
                            (state__14774))
                           (state__14774)))))
                       (state__14774)))))
                   (state__14774))))
                (state__14774)))
              (state__14774)))
            (state__14774))
           (state__14774))))
        (state__14774
         []
         (clojure.core/letfn
          [(def__14490
            [arg__14513 ?__13591]
            (clojure.core/letfn
             [(state__14937
               []
               (clojure.core/let
                [x__14514 "meander.zeta"]
                (if
                 (clojure.core/= ?__13591 x__14514)
                 [?__13591]
                 (state__14938))))
              (state__14938
               []
               (if
                (clojure.core/map? arg__14513)
                (clojure.core/let
                 [VAL__14515 (.valAt arg__14513 :aliases)]
                 (if
                  (clojure.core/map? VAL__14515)
                  (clojure.core/let
                   [X__14517 (clojure.core/set VAL__14515)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14517))
                    (clojure.core/loop
                     [search_space__14939 (clojure.core/seq X__14517)]
                     (if
                      (clojure.core/seq search_space__14939)
                      (clojure.core/let
                       [elem__14518
                        (clojure.core/first search_space__14939)
                        result__14940
                        (clojure.core/let
                         [elem__14518_nth_0__
                          (clojure.core/nth elem__14518 0)
                          elem__14518_nth_1__
                          (clojure.core/nth elem__14518 1)]
                         (if
                          (clojure.core/symbol? elem__14518_nth_0__)
                          (clojure.core/let
                           [X__14520
                            (clojure.core/name elem__14518_nth_0__)]
                           (if
                            (clojure.core/= ?__13591 X__14520)
                            (if
                             (clojure.core/symbol? elem__14518_nth_1__)
                             (clojure.core/let
                              [X__14522
                               (clojure.core/name elem__14518_nth_1__)]
                              (clojure.core/case
                               X__14522
                               ("meander.zeta")
                               [?__13591]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__14940)
                        (recur (clojure.core/next search_space__14939))
                        result__14940))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__14937)))]
          (if
           (clojure.core/vector? input__13581)
           (if
            (clojure.core/= (clojure.core/count input__13581) 2)
            (clojure.core/let
             [input__13581_nth_0__
              (clojure.core/nth input__13581 0)
              input__13581_nth_1__
              (clojure.core/nth input__13581 1)]
             (if
              (clojure.core/seq? input__13581_nth_0__)
              (clojure.core/let
               [input__13581_nth_0___l__
                (clojure.core/take 1 input__13581_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__13581_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__13581_nth_0___r__
                  (clojure.core/drop 1 input__13581_nth_0__)]
                 (clojure.core/let
                  [input__13581_nth_0___l___nth_0__
                   (clojure.core/nth input__13581_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__13581_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__14500
                     (clojure.core/namespace
                      input__13581_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__13591 X__14500]
                     (clojure.core/let
                      [X__14502
                       (clojure.core/name
                        input__13581_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__14502
                       ("re")
                       (clojure.core/let
                        [x__7920__auto__
                         (def__14490 input__13581_nth_1__ ?__13591)]
                        (if
                         (meander.runtime.zeta/fail? x__7920__auto__)
                         (state__14775)
                         (clojure.core/let
                          [[?__13591] x__7920__auto__]
                          (if
                           (clojure.core/vector? input__13581)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__13581)
                             2)
                            (clojure.core/let
                             [input__13581_nth_0__
                              (clojure.core/nth input__13581 0)
                              input__13581_nth_1__
                              (clojure.core/nth input__13581 1)]
                             (if
                              (clojure.core/seq? input__13581_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__13581_nth_0__)
                                2)
                               (clojure.core/let
                                [input__13581_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__13581_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?regex input__13581_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__13581_nth_0__]
                                  (clojure.core/let
                                   [?env input__13581_nth_1__]
                                   (try
                                    [{:tag :regex,
                                      :regex ?regex,
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__10163__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10163__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10163__auto__))))))))
                               (state__14775))
                              (state__14775)))
                            (state__14775))
                           (state__14775)))))
                       (state__14775)))))
                   (state__14775))))
                (state__14775)))
              (state__14775)))
            (state__14775))
           (state__14775))))
        (state__14775
         []
         (clojure.core/letfn
          [(def__14524
            [arg__14547 ?__13592]
            (clojure.core/letfn
             [(state__14942
               []
               (clojure.core/let
                [x__14548 "meander.zeta"]
                (if
                 (clojure.core/= ?__13592 x__14548)
                 [?__13592]
                 (state__14943))))
              (state__14943
               []
               (if
                (clojure.core/map? arg__14547)
                (clojure.core/let
                 [VAL__14549 (.valAt arg__14547 :aliases)]
                 (if
                  (clojure.core/map? VAL__14549)
                  (clojure.core/let
                   [X__14551 (clojure.core/set VAL__14549)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14551))
                    (clojure.core/loop
                     [search_space__14944 (clojure.core/seq X__14551)]
                     (if
                      (clojure.core/seq search_space__14944)
                      (clojure.core/let
                       [elem__14552
                        (clojure.core/first search_space__14944)
                        result__14945
                        (clojure.core/let
                         [elem__14552_nth_0__
                          (clojure.core/nth elem__14552 0)
                          elem__14552_nth_1__
                          (clojure.core/nth elem__14552 1)]
                         (if
                          (clojure.core/symbol? elem__14552_nth_0__)
                          (clojure.core/let
                           [X__14554
                            (clojure.core/name elem__14552_nth_0__)]
                           (if
                            (clojure.core/= ?__13592 X__14554)
                            (if
                             (clojure.core/symbol? elem__14552_nth_1__)
                             (clojure.core/let
                              [X__14556
                               (clojure.core/name elem__14552_nth_1__)]
                              (clojure.core/case
                               X__14556
                               ("meander.zeta")
                               [?__13592]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__14945)
                        (recur (clojure.core/next search_space__14944))
                        result__14945))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__14942)))]
          (if
           (clojure.core/vector? input__13581)
           (if
            (clojure.core/= (clojure.core/count input__13581) 2)
            (clojure.core/let
             [input__13581_nth_0__
              (clojure.core/nth input__13581 0)
              input__13581_nth_1__
              (clojure.core/nth input__13581 1)]
             (if
              (clojure.core/seq? input__13581_nth_0__)
              (clojure.core/let
               [input__13581_nth_0___l__
                (clojure.core/take 1 input__13581_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__13581_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__13581_nth_0___r__
                  (clojure.core/drop 1 input__13581_nth_0__)]
                 (clojure.core/let
                  [input__13581_nth_0___l___nth_0__
                   (clojure.core/nth input__13581_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__13581_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__14534
                     (clojure.core/namespace
                      input__13581_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__13592 X__14534]
                     (clojure.core/let
                      [X__14536
                       (clojure.core/name
                        input__13581_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__14536
                       ("re")
                       (clojure.core/let
                        [x__7920__auto__
                         (def__14524 input__13581_nth_1__ ?__13592)]
                        (if
                         (meander.runtime.zeta/fail? x__7920__auto__)
                         (state__14776)
                         (clojure.core/let
                          [[?__13592] x__7920__auto__]
                          (if
                           (clojure.core/vector? input__13581)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__13581)
                             2)
                            (clojure.core/let
                             [input__13581_nth_0__
                              (clojure.core/nth input__13581 0)
                              input__13581_nth_1__
                              (clojure.core/nth input__13581 1)]
                             (if
                              (clojure.core/seq? input__13581_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__13581_nth_0__)
                                3)
                               (clojure.core/let
                                [input__13581_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__13581_nth_0__
                                  1)
                                 input__13581_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__13581_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?regex input__13581_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?capture
                                   input__13581_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__13581_nth_0__]
                                   (clojure.core/let
                                    [?env input__13581_nth_1__]
                                    (try
                                     [{:tag :regex,
                                       :regex ?regex,
                                       :capture
                                       (clojure.core/let
                                        [CATA_RESULT__9223__auto__
                                         (CATA__FN__13640
                                          [?capture ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9223__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9223__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10163__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10163__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10163__auto__)))))))))
                               (state__14776))
                              (state__14776)))
                            (state__14776))
                           (state__14776)))))
                       (state__14776)))))
                   (state__14776))))
                (state__14776)))
              (state__14776)))
            (state__14776))
           (state__14776))))
        (state__14776
         []
         (clojure.core/letfn
          [(def__14558
            [arg__14581 ?__13593]
            (clojure.core/letfn
             [(state__14947
               []
               (clojure.core/let
                [x__14582 "meander.zeta"]
                (if
                 (clojure.core/= ?__13593 x__14582)
                 [?__13593]
                 (state__14948))))
              (state__14948
               []
               (if
                (clojure.core/map? arg__14581)
                (clojure.core/let
                 [VAL__14583 (.valAt arg__14581 :aliases)]
                 (if
                  (clojure.core/map? VAL__14583)
                  (clojure.core/let
                   [X__14585 (clojure.core/set VAL__14583)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14585))
                    (clojure.core/loop
                     [search_space__14949 (clojure.core/seq X__14585)]
                     (if
                      (clojure.core/seq search_space__14949)
                      (clojure.core/let
                       [elem__14586
                        (clojure.core/first search_space__14949)
                        result__14950
                        (clojure.core/let
                         [elem__14586_nth_0__
                          (clojure.core/nth elem__14586 0)
                          elem__14586_nth_1__
                          (clojure.core/nth elem__14586 1)]
                         (if
                          (clojure.core/symbol? elem__14586_nth_0__)
                          (clojure.core/let
                           [X__14588
                            (clojure.core/name elem__14586_nth_0__)]
                           (if
                            (clojure.core/= ?__13593 X__14588)
                            (if
                             (clojure.core/symbol? elem__14586_nth_1__)
                             (clojure.core/let
                              [X__14590
                               (clojure.core/name elem__14586_nth_1__)]
                              (clojure.core/case
                               X__14590
                               ("meander.zeta")
                               [?__13593]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__14950)
                        (recur (clojure.core/next search_space__14949))
                        result__14950))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__14947)))]
          (if
           (clojure.core/vector? input__13581)
           (if
            (clojure.core/= (clojure.core/count input__13581) 2)
            (clojure.core/let
             [input__13581_nth_0__
              (clojure.core/nth input__13581 0)
              input__13581_nth_1__
              (clojure.core/nth input__13581 1)]
             (if
              (clojure.core/seq? input__13581_nth_0__)
              (clojure.core/let
               [input__13581_nth_0___l__
                (clojure.core/take 1 input__13581_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__13581_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__13581_nth_0___r__
                  (clojure.core/drop 1 input__13581_nth_0__)]
                 (clojure.core/let
                  [input__13581_nth_0___l___nth_0__
                   (clojure.core/nth input__13581_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__13581_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__14568
                     (clojure.core/namespace
                      input__13581_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__13593 X__14568]
                     (clojure.core/let
                      [X__14570
                       (clojure.core/name
                        input__13581_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__14570
                       ("string")
                       (clojure.core/let
                        [x__7920__auto__
                         (def__14558 input__13581_nth_1__ ?__13593)]
                        (if
                         (meander.runtime.zeta/fail? x__7920__auto__)
                         (state__14777)
                         (clojure.core/let
                          [[?__13593] x__7920__auto__]
                          (if
                           (clojure.core/vector? input__13581)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__13581)
                             2)
                            (clojure.core/let
                             [input__13581_nth_0__
                              (clojure.core/nth input__13581 0)
                              input__13581_nth_1__
                              (clojure.core/nth input__13581 1)]
                             (if
                              (clojure.core/seq? input__13581_nth_0__)
                              (clojure.core/let
                               [input__13581_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__13581_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__13581_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__13581_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__13581_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__13581_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__13581_nth_0__]
                                   (clojure.core/let
                                    [?env input__13581_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__9223__auto__
                                         (CATA__FN__13640
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?sequence)
                                           (clojure.core/let
                                            [form__9322__auto__
                                             {:context :string}]
                                            (clojure.core/merge
                                             (clojure.core/into
                                              {}
                                              ?env)
                                             form__9322__auto__))])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9223__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9223__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10163__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10163__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10163__auto__))))))))
                                (state__14777)))
                              (state__14777)))
                            (state__14777))
                           (state__14777)))))
                       (state__14777)))))
                   (state__14777))))
                (state__14777)))
              (state__14777)))
            (state__14777))
           (state__14777))))
        (state__14777
         []
         (clojure.core/letfn
          [(def__14592
            [arg__14615 ?__13594]
            (clojure.core/letfn
             [(state__14952
               []
               (clojure.core/let
                [x__14616 "meander.zeta"]
                (if
                 (clojure.core/= ?__13594 x__14616)
                 [?__13594]
                 (state__14953))))
              (state__14953
               []
               (if
                (clojure.core/map? arg__14615)
                (clojure.core/let
                 [VAL__14617 (.valAt arg__14615 :aliases)]
                 (if
                  (clojure.core/map? VAL__14617)
                  (clojure.core/let
                   [X__14619 (clojure.core/set VAL__14617)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14619))
                    (clojure.core/loop
                     [search_space__14954 (clojure.core/seq X__14619)]
                     (if
                      (clojure.core/seq search_space__14954)
                      (clojure.core/let
                       [elem__14620
                        (clojure.core/first search_space__14954)
                        result__14955
                        (clojure.core/let
                         [elem__14620_nth_0__
                          (clojure.core/nth elem__14620 0)
                          elem__14620_nth_1__
                          (clojure.core/nth elem__14620 1)]
                         (if
                          (clojure.core/symbol? elem__14620_nth_0__)
                          (clojure.core/let
                           [X__14622
                            (clojure.core/name elem__14620_nth_0__)]
                           (if
                            (clojure.core/= ?__13594 X__14622)
                            (if
                             (clojure.core/symbol? elem__14620_nth_1__)
                             (clojure.core/let
                              [X__14624
                               (clojure.core/name elem__14620_nth_1__)]
                              (clojure.core/case
                               X__14624
                               ("meander.zeta")
                               [?__13594]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__14955)
                        (recur (clojure.core/next search_space__14954))
                        result__14955))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__14952)))]
          (if
           (clojure.core/vector? input__13581)
           (if
            (clojure.core/= (clojure.core/count input__13581) 2)
            (clojure.core/let
             [input__13581_nth_0__
              (clojure.core/nth input__13581 0)
              input__13581_nth_1__
              (clojure.core/nth input__13581 1)]
             (if
              (clojure.core/seq? input__13581_nth_0__)
              (clojure.core/let
               [input__13581_nth_0___l__
                (clojure.core/take 1 input__13581_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__13581_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__13581_nth_0___r__
                  (clojure.core/drop 1 input__13581_nth_0__)]
                 (clojure.core/let
                  [input__13581_nth_0___l___nth_0__
                   (clojure.core/nth input__13581_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__13581_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__14602
                     (clojure.core/namespace
                      input__13581_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__13594 X__14602]
                     (clojure.core/let
                      [X__14604
                       (clojure.core/name
                        input__13581_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__14604
                       ("symbol")
                       (clojure.core/let
                        [x__7920__auto__
                         (def__14592 input__13581_nth_1__ ?__13594)]
                        (if
                         (meander.runtime.zeta/fail? x__7920__auto__)
                         (state__14778)
                         (clojure.core/let
                          [[?__13594] x__7920__auto__]
                          (if
                           (clojure.core/vector? input__13581)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__13581)
                             2)
                            (clojure.core/let
                             [input__13581_nth_0__
                              (clojure.core/nth input__13581 0)
                              input__13581_nth_1__
                              (clojure.core/nth input__13581 1)]
                             (if
                              (clojure.core/seq? input__13581_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__13581_nth_0__)
                                2)
                               (clojure.core/let
                                [input__13581_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__13581_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?name input__13581_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__13581_nth_0__]
                                  (clojure.core/let
                                   [?env input__13581_nth_1__]
                                   (try
                                    [{:tag :symbol,
                                      :name
                                      (clojure.core/let
                                       [CATA_RESULT__9223__auto__
                                        (CATA__FN__13640 [?name ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9223__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9223__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__10163__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10163__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10163__auto__))))))))
                               (state__14778))
                              (state__14778)))
                            (state__14778))
                           (state__14778)))))
                       (state__14778)))))
                   (state__14778))))
                (state__14778)))
              (state__14778)))
            (state__14778))
           (state__14778))))
        (state__14778
         []
         (clojure.core/letfn
          [(def__14626
            [arg__14649 ?__13595]
            (clojure.core/letfn
             [(state__14957
               []
               (clojure.core/let
                [x__14650 "meander.zeta"]
                (if
                 (clojure.core/= ?__13595 x__14650)
                 [?__13595]
                 (state__14958))))
              (state__14958
               []
               (if
                (clojure.core/map? arg__14649)
                (clojure.core/let
                 [VAL__14651 (.valAt arg__14649 :aliases)]
                 (if
                  (clojure.core/map? VAL__14651)
                  (clojure.core/let
                   [X__14653 (clojure.core/set VAL__14651)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14653))
                    (clojure.core/loop
                     [search_space__14959 (clojure.core/seq X__14653)]
                     (if
                      (clojure.core/seq search_space__14959)
                      (clojure.core/let
                       [elem__14654
                        (clojure.core/first search_space__14959)
                        result__14960
                        (clojure.core/let
                         [elem__14654_nth_0__
                          (clojure.core/nth elem__14654 0)
                          elem__14654_nth_1__
                          (clojure.core/nth elem__14654 1)]
                         (if
                          (clojure.core/symbol? elem__14654_nth_0__)
                          (clojure.core/let
                           [X__14656
                            (clojure.core/name elem__14654_nth_0__)]
                           (if
                            (clojure.core/= ?__13595 X__14656)
                            (if
                             (clojure.core/symbol? elem__14654_nth_1__)
                             (clojure.core/let
                              [X__14658
                               (clojure.core/name elem__14654_nth_1__)]
                              (clojure.core/case
                               X__14658
                               ("meander.zeta")
                               [?__13595]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__14960)
                        (recur (clojure.core/next search_space__14959))
                        result__14960))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__14957)))]
          (if
           (clojure.core/vector? input__13581)
           (if
            (clojure.core/= (clojure.core/count input__13581) 2)
            (clojure.core/let
             [input__13581_nth_0__
              (clojure.core/nth input__13581 0)
              input__13581_nth_1__
              (clojure.core/nth input__13581 1)]
             (if
              (clojure.core/seq? input__13581_nth_0__)
              (clojure.core/let
               [input__13581_nth_0___l__
                (clojure.core/take 1 input__13581_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__13581_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__13581_nth_0___r__
                  (clojure.core/drop 1 input__13581_nth_0__)]
                 (clojure.core/let
                  [input__13581_nth_0___l___nth_0__
                   (clojure.core/nth input__13581_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__13581_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__14636
                     (clojure.core/namespace
                      input__13581_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__13595 X__14636]
                     (clojure.core/let
                      [X__14638
                       (clojure.core/name
                        input__13581_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__14638
                       ("symbol")
                       (clojure.core/let
                        [x__7920__auto__
                         (def__14626 input__13581_nth_1__ ?__13595)]
                        (if
                         (meander.runtime.zeta/fail? x__7920__auto__)
                         (state__14779)
                         (clojure.core/let
                          [[?__13595] x__7920__auto__]
                          (if
                           (clojure.core/vector? input__13581)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__13581)
                             2)
                            (clojure.core/let
                             [input__13581_nth_0__
                              (clojure.core/nth input__13581 0)
                              input__13581_nth_1__
                              (clojure.core/nth input__13581 1)]
                             (if
                              (clojure.core/seq? input__13581_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__13581_nth_0__)
                                3)
                               (clojure.core/let
                                [input__13581_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__13581_nth_0__
                                  1)
                                 input__13581_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__13581_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?namespace
                                  input__13581_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?name input__13581_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__13581_nth_0__]
                                   (clojure.core/let
                                    [?env input__13581_nth_1__]
                                    (try
                                     [{:tag :symbol,
                                       :name
                                       (clojure.core/let
                                        [CATA_RESULT__9223__auto__
                                         (CATA__FN__13640
                                          [?name ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9223__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9223__auto__
                                          0))),
                                       :namespace
                                       (clojure.core/let
                                        [CATA_RESULT__9223__auto__
                                         (CATA__FN__13640
                                          [?namespace ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9223__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9223__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10163__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10163__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10163__auto__)))))))))
                               (state__14779))
                              (state__14779)))
                            (state__14779))
                           (state__14779)))))
                       (state__14779)))))
                   (state__14779))))
                (state__14779)))
              (state__14779)))
            (state__14779))
           (state__14779))))
        (state__14779
         []
         (clojure.core/letfn
          [(def__14660
            [arg__14683 ?__13596]
            (clojure.core/letfn
             [(state__14962
               []
               (clojure.core/let
                [x__14684 "meander.zeta"]
                (if
                 (clojure.core/= ?__13596 x__14684)
                 [?__13596]
                 (state__14963))))
              (state__14963
               []
               (if
                (clojure.core/map? arg__14683)
                (clojure.core/let
                 [VAL__14685 (.valAt arg__14683 :aliases)]
                 (if
                  (clojure.core/map? VAL__14685)
                  (clojure.core/let
                   [X__14687 (clojure.core/set VAL__14685)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14687))
                    (clojure.core/loop
                     [search_space__14964 (clojure.core/seq X__14687)]
                     (if
                      (clojure.core/seq search_space__14964)
                      (clojure.core/let
                       [elem__14688
                        (clojure.core/first search_space__14964)
                        result__14965
                        (clojure.core/let
                         [elem__14688_nth_0__
                          (clojure.core/nth elem__14688 0)
                          elem__14688_nth_1__
                          (clojure.core/nth elem__14688 1)]
                         (if
                          (clojure.core/symbol? elem__14688_nth_0__)
                          (clojure.core/let
                           [X__14690
                            (clojure.core/name elem__14688_nth_0__)]
                           (if
                            (clojure.core/= ?__13596 X__14690)
                            (if
                             (clojure.core/symbol? elem__14688_nth_1__)
                             (clojure.core/let
                              [X__14692
                               (clojure.core/name elem__14688_nth_1__)]
                              (clojure.core/case
                               X__14692
                               ("meander.zeta")
                               [?__13596]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__14965)
                        (recur (clojure.core/next search_space__14964))
                        result__14965))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__14962)))]
          (if
           (clojure.core/vector? input__13581)
           (if
            (clojure.core/= (clojure.core/count input__13581) 2)
            (clojure.core/let
             [input__13581_nth_0__
              (clojure.core/nth input__13581 0)
              input__13581_nth_1__
              (clojure.core/nth input__13581 1)]
             (if
              (clojure.core/seq? input__13581_nth_0__)
              (clojure.core/let
               [input__13581_nth_0___l__
                (clojure.core/take 1 input__13581_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__13581_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__13581_nth_0___r__
                  (clojure.core/drop 1 input__13581_nth_0__)]
                 (clojure.core/let
                  [input__13581_nth_0___l___nth_0__
                   (clojure.core/nth input__13581_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__13581_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__14670
                     (clojure.core/namespace
                      input__13581_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__13596 X__14670]
                     (clojure.core/let
                      [X__14672
                       (clojure.core/name
                        input__13581_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__14672
                       ("symbol")
                       (clojure.core/let
                        [x__7920__auto__
                         (def__14660 input__13581_nth_1__ ?__13596)]
                        (if
                         (meander.runtime.zeta/fail? x__7920__auto__)
                         (state__14780)
                         (clojure.core/let
                          [[?__13596] x__7920__auto__]
                          (if
                           (clojure.core/vector? input__13581)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__13581)
                             2)
                            (clojure.core/let
                             [input__13581_nth_0__
                              (clojure.core/nth input__13581 0)
                              input__13581_nth_1__
                              (clojure.core/nth input__13581 1)]
                             (if
                              (clojure.core/seq? input__13581_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 5)
                                 input__13581_nth_0__)
                                5)
                               (clojure.core/let
                                [input__13581_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__13581_nth_0__
                                  1)
                                 input__13581_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__13581_nth_0__
                                  2)
                                 input__13581_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__13581_nth_0__
                                  3)
                                 input__13581_nth_0___nth_4__
                                 (clojure.core/nth
                                  input__13581_nth_0__
                                  4)]
                                (clojure.core/case
                                 input__13581_nth_0___nth_3__
                                 (:meander.zeta/as)
                                 (clojure.core/let
                                  [?namespace
                                   input__13581_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?name input__13581_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?pattern
                                     input__13581_nth_0___nth_4__]
                                    (clojure.core/let
                                     [?form input__13581_nth_0__]
                                     (clojure.core/let
                                      [?env input__13581_nth_1__]
                                      (try
                                       [{:tag :symbol,
                                         :name
                                         (clojure.core/let
                                          [CATA_RESULT__9223__auto__
                                           (CATA__FN__13640
                                            [?name ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__9223__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__9223__auto__
                                            0))),
                                         :namespace
                                         (clojure.core/let
                                          [CATA_RESULT__9223__auto__
                                           (CATA__FN__13640
                                            [?namespace ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__9223__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__9223__auto__
                                            0))),
                                         :as-pattern
                                         (clojure.core/let
                                          [CATA_RESULT__9223__auto__
                                           (CATA__FN__13640
                                            [?pattern ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__9223__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__9223__auto__
                                            0))),
                                         :form ?form}]
                                       (catch
                                        java.lang.Exception
                                        e__10163__auto__
                                        (if
                                         (meander.runtime.zeta/fail?
                                          e__10163__auto__)
                                         (meander.runtime.zeta/fail)
                                         (throw
                                          e__10163__auto__)))))))))
                                 (state__14780)))
                               (state__14780))
                              (state__14780)))
                            (state__14780))
                           (state__14780)))))
                       (state__14780)))))
                   (state__14780))))
                (state__14780)))
              (state__14780)))
            (state__14780))
           (state__14780))))
        (state__14780
         []
         (if
          (clojure.core/vector? input__13581)
          (if
           (clojure.core/= (clojure.core/count input__13581) 2)
           (clojure.core/let
            [input__13581_nth_0__ (clojure.core/nth input__13581 0)]
            (clojure.core/letfn
             [(state__14967
               []
               (clojure.core/let
                [input__13581_nth_1__
                 (clojure.core/nth input__13581 1)]
                (clojure.core/letfn
                 [(state__14972
                   []
                   (if
                    (clojure.core/seq? input__13581_nth_0__)
                    (clojure.core/let
                     [?sequence input__13581_nth_0__]
                     (clojure.core/let
                      [?env input__13581_nth_1__]
                      (try
                       [{:tag :seq,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__9223__auto__
                           (CATA__FN__13640
                            ['meander.dev.parse.zeta/parse-sequential
                             (clojure.core/into [] ?sequence)
                             (clojure.core/let
                              [form__9322__auto__ {:context :seq}]
                              (clojure.core/merge
                               (clojure.core/into {} ?env)
                               form__9322__auto__))])]
                          (if
                           (meander.runtime.zeta/fail?
                            CATA_RESULT__9223__auto__)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth
                            CATA_RESULT__9223__auto__
                            0))),
                         :form ?sequence}]
                       (catch
                        java.lang.Exception
                        e__10163__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10163__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10163__auto__))))))
                    (state__14973)))
                  (state__14973
                   []
                   (if
                    (clojure.core/map? input__13581_nth_0__)
                    (clojure.core/let
                     [?map input__13581_nth_0__]
                     (clojure.core/let
                      [?env input__13581_nth_1__]
                      (try
                       [{:tag :map,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__9223__auto__
                           (CATA__FN__13640
                            ['meander.dev.parse.zeta/parse-entries
                             ?map
                             ?env])]
                          (if
                           (meander.runtime.zeta/fail?
                            CATA_RESULT__9223__auto__)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth
                            CATA_RESULT__9223__auto__
                            0))),
                         :form ?map}]
                       (catch
                        java.lang.Exception
                        e__10163__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10163__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10163__auto__))))))
                    (state__14968)))]
                 (state__14972))))
              (state__14968
               []
               (if
                (clojure.core/symbol? input__13581_nth_0__)
                (clojure.core/let
                 [X__14702
                  (clojure.core/namespace input__13581_nth_0__)]
                 (clojure.core/let
                  [X__14704 (clojure.core/name input__13581_nth_0__)]
                  (if
                   (clojure.core/string? X__14704)
                   (clojure.core/letfn
                    [(state__14974
                      []
                      (clojure.core/let
                       [ret__14705
                        (clojure.core/re-matches #"_.*" X__14704)]
                       (if
                        (clojure.core/some? ret__14705)
                        (clojure.core/let
                         [?name ret__14705]
                         (clojure.core/let
                          [?symbol input__13581_nth_0__]
                          (try
                           [{:tag :wildcard,
                             :name ?name,
                             :form ?symbol,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__10163__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__10163__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10163__auto__))))))
                        (state__14975))))
                     (state__14975
                      []
                      (clojure.core/let
                       [ret__14712
                        (clojure.core/re-matches #".+#" X__14704)]
                       (if
                        (clojure.core/some? ret__14712)
                        (clojure.core/let
                         [?name ret__14712]
                         (clojure.core/let
                          [?symbol input__13581_nth_0__]
                          (try
                           [{:tag :random-symbol,
                             :name ?name,
                             :form ?symbol,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__10163__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__10163__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10163__auto__))))))
                        (state__14976))))
                     (state__14976
                      []
                      (clojure.core/let
                       [ret__14719
                        (clojure.core/re-matches #"%.+" X__14704)]
                       (if
                        (clojure.core/some? ret__14719)
                        (clojure.core/let
                         [?name ret__14719]
                         (clojure.core/let
                          [?symbol input__13581_nth_0__]
                          (try
                           [{:tag :reference,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__10163__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__10163__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10163__auto__))))))
                        (state__14977))))
                     (state__14977
                      []
                      (clojure.core/let
                       [ret__14726
                        (clojure.core/re-matches #"\*.+" X__14704)]
                       (if
                        (clojure.core/some? ret__14726)
                        (clojure.core/let
                         [?name ret__14726]
                         (clojure.core/let
                          [?symbol input__13581_nth_0__]
                          (try
                           [{:tag :mutable-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__10163__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__10163__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10163__auto__))))))
                        (state__14978))))
                     (state__14978
                      []
                      (clojure.core/let
                       [ret__14733
                        (clojure.core/re-matches #"\!.+" X__14704)]
                       (if
                        (clojure.core/some? ret__14733)
                        (clojure.core/let
                         [?name ret__14733]
                         (clojure.core/let
                          [?symbol input__13581_nth_0__]
                          (try
                           [{:tag :memory-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__10163__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__10163__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10163__auto__))))))
                        (state__14979))))
                     (state__14979
                      []
                      (clojure.core/let
                       [ret__14740
                        (clojure.core/re-matches #"\?.+" X__14704)]
                       (if
                        (clojure.core/some? ret__14740)
                        (clojure.core/let
                         [?name ret__14740]
                         (clojure.core/let
                          [?symbol input__13581_nth_0__]
                          (try
                           [{:tag :logic-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__10163__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__10163__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10163__auto__))))))
                        (state__14969))))]
                    (state__14974))
                   (state__14969))))
                (state__14969)))
              (state__14969
               []
               (if
                (string? input__13581_nth_0__)
                (clojure.core/let
                 [?x input__13581_nth_0__]
                 (try
                  [{:tag :literal, :type :string, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__10163__auto__
                   (if
                    (meander.runtime.zeta/fail? e__10163__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__10163__auto__)))))
                (state__14970)))
              (state__14970
               []
               (if
                (char? input__13581_nth_0__)
                (clojure.core/let
                 [?x input__13581_nth_0__]
                 (try
                  [{:tag :literal, :type :char, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__10163__auto__
                   (if
                    (meander.runtime.zeta/fail? e__10163__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__10163__auto__)))))
                (state__14971)))
              (state__14971
               []
               (clojure.core/let
                [?x input__13581_nth_0__]
                (try
                 [{:tag :literal, :form ?x}]
                 (catch
                  java.lang.Exception
                  e__10163__auto__
                  (if
                   (meander.runtime.zeta/fail? e__10163__auto__)
                   (meander.runtime.zeta/fail)
                   (throw e__10163__auto__))))))]
             (state__14967)))
           (state__14781))
          (state__14781)))
        (state__14781
         []
         (clojure.core/let
          [?x input__13581]
          (try
           [{:tag :mistake, :x ?x}]
           (catch
            java.lang.Exception
            e__10163__auto__
            (if
             (meander.runtime.zeta/fail? e__10163__auto__)
             (meander.runtime.zeta/fail)
             (throw e__10163__auto__))))))]
       (state__14752)))]
    (clojure.core/let
     [x__7920__auto__ (CATA__FN__13640 input__13581)]
     (if
      (meander.runtime.zeta/fail? x__7920__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__13642] x__7920__auto__]
       CATA_RETURN__13642))))]
  (if
   (meander.runtime.zeta/fail? ret__8100__auto__)
   nil
   ret__8100__auto__)))
