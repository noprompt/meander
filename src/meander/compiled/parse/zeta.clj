(ns meander.compiled.parse.zeta (:require [meander.runtime.zeta]))
(clojure.core/defn
 parse
 [input__36431]
 (let*
  [ret__8106__auto__
   (clojure.core/letfn
    [(CATA__FN__36481
      [input__36431]
      (clojure.core/letfn
       [(state__37469
         []
         (if
          (clojure.core/vector? input__36431)
          (if
           (clojure.core/= (clojure.core/count input__36431) 3)
           (clojure.core/let
            [input__36431_nth_0__
             (clojure.core/nth input__36431 0)
             input__36431_nth_1__
             (clojure.core/nth input__36431 1)
             input__36431_nth_2__
             (clojure.core/nth input__36431 2)]
            (clojure.core/case
             input__36431_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__36431_nth_1__)
              (clojure.core/letfn
               [(state__37492
                 []
                 (clojure.core/case
                  input__36431_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__36431_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__10169__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10169__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10169__auto__)))))
                  (state__37493)))
                (state__37493
                 []
                 (clojure.core/let
                  [n__36488
                   (clojure.core/count input__36431_nth_1__)
                   m__36489
                   (clojure.core/max 0 (clojure.core/- n__36488 2))
                   input__36431_nth_1___l__
                   (clojure.core/subvec
                    input__36431_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__36431_nth_1__)
                     m__36489))
                   input__36431_nth_1___r__
                   (clojure.core/subvec input__36431_nth_1__ m__36489)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__36431_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__36431_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__36431_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__36431_nth_1___r___nth_0__
                       (clojure.core/nth input__36431_nth_1___r__ 0)
                       input__36431_nth_1___r___nth_1__
                       (clojure.core/nth input__36431_nth_1___r__ 1)]
                      (clojure.core/case
                       input__36431_nth_1___r___nth_0__
                       (:meander.zeta/as)
                       (clojure.core/let
                        [?pattern input__36431_nth_1___r___nth_1__]
                        (clojure.core/let
                         [?env input__36431_nth_2__]
                         (try
                          [(clojure.core/let
                            [!xs__counter
                             (meander.runtime.zeta/iterator !xs)]
                            {:tag :as,
                             :pattern
                             (clojure.core/let
                              [CATA_RESULT__9229__auto__
                               (CATA__FN__36481 [?pattern ?env])]
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
                               (CATA__FN__36481
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
                       (state__37470)))
                     (state__37470)))
                   (state__37470))))]
               (state__37492))
              (state__37470))
             (state__37470)))
           (state__37470))
          (state__37470)))
        (state__37470
         []
         (clojure.core/letfn
          [(def__36494
            [arg__36529 ?ns]
            (clojure.core/letfn
             [(state__37494
               []
               (clojure.core/let
                [x__36530 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__36530)
                 (clojure.core/let [?env arg__36529] [?env ?ns])
                 (state__37495))))
              (state__37495
               []
               (if
                (clojure.core/map? arg__36529)
                (clojure.core/let
                 [VAL__36531 (.valAt arg__36529 :aliases)]
                 (if
                  (clojure.core/map? VAL__36531)
                  (clojure.core/let
                   [X__36533 (clojure.core/set VAL__36531)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__36533))
                    (clojure.core/loop
                     [search_space__37496 (clojure.core/seq X__36533)]
                     (if
                      (clojure.core/seq search_space__37496)
                      (clojure.core/let
                       [elem__36534
                        (clojure.core/first search_space__37496)
                        result__37497
                        (clojure.core/let
                         [elem__36534_nth_0__
                          (clojure.core/nth elem__36534 0)
                          elem__36534_nth_1__
                          (clojure.core/nth elem__36534 1)]
                         (if
                          (clojure.core/symbol? elem__36534_nth_0__)
                          (clojure.core/let
                           [X__36536
                            (clojure.core/name elem__36534_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__36536)
                            (if
                             (clojure.core/symbol? elem__36534_nth_1__)
                             (clojure.core/let
                              [X__36538
                               (clojure.core/name elem__36534_nth_1__)]
                              (clojure.core/case
                               X__36538
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__36529]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__37497)
                        (recur (clojure.core/next search_space__37496))
                        result__37497))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__37494)))]
          (if
           (clojure.core/vector? input__36431)
           (if
            (clojure.core/= (clojure.core/count input__36431) 3)
            (clojure.core/let
             [input__36431_nth_0__
              (clojure.core/nth input__36431 0)
              input__36431_nth_1__
              (clojure.core/nth input__36431 1)
              input__36431_nth_2__
              (clojure.core/nth input__36431 2)]
             (clojure.core/case
              input__36431_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__36431_nth_1__)
               (clojure.core/loop
                [search_space__37499
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__36431_nth_1__)]
                (if
                 (clojure.core/seq search_space__37499)
                 (clojure.core/let
                  [input__36431_nth_1___parts__
                   (clojure.core/first search_space__37499)
                   result__37500
                   (clojure.core/let
                    [input__36431_nth_1___l__
                     (clojure.core/nth input__36431_nth_1___parts__ 0)
                     input__36431_nth_1___r__
                     (clojure.core/nth input__36431_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__36431_nth_1___l__)]
                     (clojure.core/let
                      [input__36431_nth_1___r___l__
                       (clojure.core/subvec
                        input__36431_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__36431_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__36431_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__36431_nth_1___r___r__
                         (clojure.core/subvec
                          input__36431_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__36431_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__36431_nth_1___r___l__
                           0)
                          input__36431_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__36431_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__36431_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__36503
                            (clojure.core/namespace
                             input__36431_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__36503]
                            (clojure.core/let
                             [X__36505
                              (clojure.core/name
                               input__36431_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__36505)
                              (clojure.core/let
                               [ret__36506
                                (clojure.core/re-matches
                                 #"&(\d+)"
                                 X__36505)]
                               (if
                                (clojure.core/some? ret__36506)
                                (if
                                 (clojure.core/vector? ret__36506)
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count ret__36506)
                                   2)
                                  (clojure.core/let
                                   [ret__36506_nth_1__
                                    (clojure.core/nth ret__36506 1)]
                                   (clojure.core/let
                                    [?n ret__36506_nth_1__]
                                    (clojure.core/let
                                     [?pattern
                                      input__36431_nth_1___r___l___nth_1__]
                                     (clojure.core/let
                                      [?rest
                                       input__36431_nth_1___r___r__]
                                      (clojure.core/let
                                       [x__7926__auto__
                                        (def__36494
                                         input__36431_nth_2__
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
                                              (CATA__FN__36481
                                               ['meander.dev.parse.zeta/make-join
                                                (clojure.core/let
                                                 [CATA_RESULT__9229__auto__
                                                  (CATA__FN__36481
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
                                                  (CATA__FN__36481
                                                   ['meander.dev.parse.zeta/make-join
                                                    {:tag :slice,
                                                     :size
                                                     (Integer. ?n),
                                                     :pattern
                                                     (clojure.core/let
                                                      [CATA_RESULT__9229__auto__
                                                       (CATA__FN__36481
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
                                                      (CATA__FN__36481
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
                   (meander.runtime.zeta/fail? result__37500)
                   (recur (clojure.core/next search_space__37499))
                   result__37500))
                 (state__37471)))
               (state__37471))
              (state__37471)))
            (state__37471))
           (state__37471))))
        (state__37471
         []
         (clojure.core/letfn
          [(def__36551
            [arg__36583 ?ns]
            (clojure.core/letfn
             [(state__37502
               []
               (clojure.core/let
                [x__36584 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__36584)
                 (clojure.core/let [?env arg__36583] [?env ?ns])
                 (state__37503))))
              (state__37503
               []
               (if
                (clojure.core/map? arg__36583)
                (clojure.core/let
                 [VAL__36585 (.valAt arg__36583 :aliases)]
                 (if
                  (clojure.core/map? VAL__36585)
                  (clojure.core/let
                   [X__36587 (clojure.core/set VAL__36585)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__36587))
                    (clojure.core/loop
                     [search_space__37504 (clojure.core/seq X__36587)]
                     (if
                      (clojure.core/seq search_space__37504)
                      (clojure.core/let
                       [elem__36588
                        (clojure.core/first search_space__37504)
                        result__37505
                        (clojure.core/let
                         [elem__36588_nth_0__
                          (clojure.core/nth elem__36588 0)
                          elem__36588_nth_1__
                          (clojure.core/nth elem__36588 1)]
                         (if
                          (clojure.core/symbol? elem__36588_nth_0__)
                          (clojure.core/let
                           [X__36590
                            (clojure.core/name elem__36588_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__36590)
                            (if
                             (clojure.core/symbol? elem__36588_nth_1__)
                             (clojure.core/let
                              [X__36592
                               (clojure.core/name elem__36588_nth_1__)]
                              (clojure.core/case
                               X__36592
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__36583]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__37505)
                        (recur (clojure.core/next search_space__37504))
                        result__37505))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__37502)))]
          (if
           (clojure.core/vector? input__36431)
           (if
            (clojure.core/= (clojure.core/count input__36431) 3)
            (clojure.core/let
             [input__36431_nth_0__
              (clojure.core/nth input__36431 0)
              input__36431_nth_1__
              (clojure.core/nth input__36431 1)
              input__36431_nth_2__
              (clojure.core/nth input__36431 2)]
             (clojure.core/case
              input__36431_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__36431_nth_1__)
               (clojure.core/loop
                [search_space__37507
                 (meander.match.runtime.epsilon/partitions
                  2
                  input__36431_nth_1__)]
                (if
                 (clojure.core/seq search_space__37507)
                 (clojure.core/let
                  [input__36431_nth_1___parts__
                   (clojure.core/first search_space__37507)
                   result__37508
                   (clojure.core/let
                    [input__36431_nth_1___l__
                     (clojure.core/nth input__36431_nth_1___parts__ 0)
                     input__36431_nth_1___r__
                     (clojure.core/nth input__36431_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__36431_nth_1___l__)]
                     (clojure.core/let
                      [input__36431_nth_1___r___l__
                       (clojure.core/subvec
                        input__36431_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__36431_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__36431_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__36431_nth_1___r___r__
                         (clojure.core/subvec
                          input__36431_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__36431_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__36431_nth_1___r___l__
                           0)
                          input__36431_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__36431_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__36431_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__36560
                            (clojure.core/namespace
                             input__36431_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__36560]
                            (clojure.core/let
                             [X__36562
                              (clojure.core/name
                               input__36431_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__36562)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__36562)
                               (clojure.core/let
                                [?pattern
                                 input__36431_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__36431_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__7926__auto__
                                   (def__36551
                                    input__36431_nth_2__
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
                                         (CATA__FN__36481
                                          ['meander.dev.parse.zeta/make-join
                                           (clojure.core/let
                                            [CATA_RESULT__9229__auto__
                                             (CATA__FN__36481
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
                                             (CATA__FN__36481
                                              ['meander.dev.parse.zeta/make-join
                                               (clojure.core/let
                                                [CATA_RESULT__9229__auto__
                                                 (CATA__FN__36481
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
                                                 (CATA__FN__36481
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
                   (meander.runtime.zeta/fail? result__37508)
                   (recur (clojure.core/next search_space__37507))
                   result__37508))
                 (state__37472)))
               (state__37472))
              (state__37472)))
            (state__37472))
           (state__37472))))
        (state__37472
         []
         (if
          (clojure.core/vector? input__36431)
          (clojure.core/letfn
           [(state__37510
             []
             (if
              (clojure.core/= (clojure.core/count input__36431) 3)
              (clojure.core/let
               [input__36431_nth_0__
                (clojure.core/nth input__36431 0)
                input__36431_nth_1__
                (clojure.core/nth input__36431 1)
                input__36431_nth_2__
                (clojure.core/nth input__36431 2)]
               (clojure.core/case
                input__36431_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__36431_nth_1__)
                 (clojure.core/letfn
                  [(state__37515
                    []
                    (clojure.core/let
                     [n__36613
                      (clojure.core/count input__36431_nth_1__)
                      m__36614
                      (clojure.core/max 0 (clojure.core/- n__36613 2))
                      input__36431_nth_1___l__
                      (clojure.core/subvec
                       input__36431_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__36431_nth_1__)
                        m__36614))
                      input__36431_nth_1___r__
                      (clojure.core/subvec
                       input__36431_nth_1__
                       m__36614)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__36431_nth_1___r__)
                       2)
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__36431_nth_1___l__)]
                       (if
                        (clojure.core/=
                         (clojure.core/count input__36431_nth_1___r__)
                         2)
                        (clojure.core/let
                         [input__36431_nth_1___r___nth_0__
                          (clojure.core/nth input__36431_nth_1___r__ 0)
                          input__36431_nth_1___r___nth_1__
                          (clojure.core/nth
                           input__36431_nth_1___r__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__36431_nth_1___r___nth_0__)
                          (clojure.core/let
                           [X__36618
                            (clojure.core/namespace
                             input__36431_nth_1___r___nth_0__)]
                           (clojure.core/let
                            [?ns X__36618]
                            (clojure.core/let
                             [X__36620
                              (clojure.core/name
                               input__36431_nth_1___r___nth_0__)]
                             (if
                              (clojure.core/string? X__36620)
                              (clojure.core/let
                               [ret__36621
                                (clojure.core/re-matches
                                 #"&.*"
                                 X__36620)]
                               (if
                                (clojure.core/some? ret__36621)
                                (clojure.core/let
                                 [?name ret__36621]
                                 (clojure.core/let
                                  [?pattern
                                   input__36431_nth_1___r___nth_1__]
                                  (if
                                   (clojure.core/map?
                                    input__36431_nth_2__)
                                   (clojure.core/let
                                    [VAL__36605
                                     (.valAt
                                      input__36431_nth_2__
                                      :aliases)]
                                    (if
                                     (clojure.core/map? VAL__36605)
                                     (clojure.core/let
                                      [X__36607
                                       (clojure.core/set VAL__36605)]
                                      (if
                                       (clojure.core/<=
                                        1
                                        (clojure.core/count X__36607))
                                       (clojure.core/loop
                                        [search_space__37519
                                         (clojure.core/seq X__36607)]
                                        (if
                                         (clojure.core/seq
                                          search_space__37519)
                                         (clojure.core/let
                                          [elem__36608
                                           (clojure.core/first
                                            search_space__37519)
                                           result__37520
                                           (clojure.core/let
                                            [elem__36608_nth_0__
                                             (clojure.core/nth
                                              elem__36608
                                              0)
                                             elem__36608_nth_1__
                                             (clojure.core/nth
                                              elem__36608
                                              1)]
                                            (if
                                             (clojure.core/symbol?
                                              elem__36608_nth_0__)
                                             (clojure.core/let
                                              [X__36610
                                               (clojure.core/name
                                                elem__36608_nth_0__)]
                                              (if
                                               (clojure.core/=
                                                ?ns
                                                X__36610)
                                               (if
                                                (clojure.core/symbol?
                                                 elem__36608_nth_1__)
                                                (clojure.core/let
                                                 [X__36612
                                                  (clojure.core/name
                                                   elem__36608_nth_1__)]
                                                 (clojure.core/case
                                                  X__36612
                                                  ("meander.zeta")
                                                  (clojure.core/let
                                                   [?env
                                                    input__36431_nth_2__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__9229__auto__
                                                        (CATA__FN__36481
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
                                            result__37520)
                                           (recur
                                            (clojure.core/next
                                             search_space__37519))
                                           result__37520))
                                         (state__37516)))
                                       (state__37516)))
                                     (state__37516)))
                                   (state__37516))))
                                (state__37516)))
                              (state__37516)))))
                          (state__37516)))
                        (state__37516)))
                      (state__37516))))
                   (state__37516
                    []
                    (clojure.core/loop
                     [search_space__37522
                      (meander.match.runtime.epsilon/partitions
                       2
                       input__36431_nth_1__)]
                     (if
                      (clojure.core/seq search_space__37522)
                      (clojure.core/let
                       [input__36431_nth_1___parts__
                        (clojure.core/first search_space__37522)
                        result__37523
                        (clojure.core/let
                         [input__36431_nth_1___l__
                          (clojure.core/nth
                           input__36431_nth_1___parts__
                           0)
                          input__36431_nth_1___r__
                          (clojure.core/nth
                           input__36431_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs
                           (clojure.core/vec input__36431_nth_1___l__)]
                          (clojure.core/let
                           [input__36431_nth_1___r___l__
                            (clojure.core/subvec
                             input__36431_nth_1___r__
                             0
                             (clojure.core/min
                              (clojure.core/count
                               input__36431_nth_1___r__)
                              1))]
                           (if
                            (clojure.core/=
                             (clojure.core/count
                              input__36431_nth_1___r___l__)
                             1)
                            (clojure.core/let
                             [input__36431_nth_1___r___r__
                              (clojure.core/subvec
                               input__36431_nth_1___r__
                               1)]
                             (if
                              (clojure.core/=
                               input__36431_nth_1___r___l__
                               ['.])
                              (clojure.core/let
                               [?rest input__36431_nth_1___r___r__]
                               (clojure.core/let
                                [?env input__36431_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [!xs__counter
                                    (meander.runtime.zeta/iterator
                                     !xs)]
                                   (clojure.core/let
                                    [CATA_RESULT__9229__auto__
                                     (CATA__FN__36481
                                      ['meander.dev.parse.zeta/make-join
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__36481
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
                                         (CATA__FN__36481
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
                        (meander.runtime.zeta/fail? result__37523)
                        (recur (clojure.core/next search_space__37522))
                        result__37523))
                      (state__37517))))
                   (state__37517
                    []
                    (clojure.core/let
                     [input__36431_nth_1___l__
                      (clojure.core/subvec
                       input__36431_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__36431_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__36431_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__36431_nth_1___r__
                        (clojure.core/subvec input__36431_nth_1__ 1)]
                       (if
                        (clojure.core/=
                         input__36431_nth_1___l__
                         ['...])
                        (clojure.core/let
                         [?rest input__36431_nth_1___r__]
                         (clojure.core/let
                          [?env input__36431_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__9229__auto__
                              (CATA__FN__36481
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
                        (state__37518)))
                      (state__37518))))
                   (state__37518
                    []
                    (clojure.core/loop
                     [search_space__37525
                      (meander.match.runtime.epsilon/partitions
                       2
                       input__36431_nth_1__)]
                     (if
                      (clojure.core/seq search_space__37525)
                      (clojure.core/let
                       [input__36431_nth_1___parts__
                        (clojure.core/first search_space__37525)
                        result__37526
                        (clojure.core/let
                         [input__36431_nth_1___l__
                          (clojure.core/nth
                           input__36431_nth_1___parts__
                           0)
                          input__36431_nth_1___r__
                          (clojure.core/nth
                           input__36431_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__8090__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__36431_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__36638]
                              (clojure.core/let
                               [input__36638_nth_0__
                                (clojure.core/nth input__36638 0)]
                               (clojure.core/letfn
                                [(save__36639
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__37529
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__36638_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__36638_nth_0__)
                                 (clojure.core/let
                                  [X__36641
                                   (clojure.core/namespace
                                    input__36638_nth_0__)]
                                  (clojure.core/case
                                   X__36641
                                   (nil)
                                   (clojure.core/let
                                    [X__36643
                                     (clojure.core/name
                                      input__36638_nth_0__)]
                                    (if
                                     (clojure.core/string? X__36643)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__36643)
                                      (save__36639)
                                      (f__37529))
                                     (f__37529)))
                                   (f__37529)))
                                 (f__37529)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__36431_nth_1___r___l__
                                (clojure.core/subvec
                                 input__36431_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__36431_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__36431_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__36431_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__36431_nth_1___r__
                                   1)]
                                 (if
                                  (clojure.core/=
                                   input__36431_nth_1___r___l__
                                   ['...])
                                  (clojure.core/let
                                   [?rest input__36431_nth_1___r___r__]
                                   (clojure.core/let
                                    [?env input__36431_nth_2__]
                                    (try
                                     [(clojure.core/let
                                       [!xs__counter
                                        (meander.runtime.zeta/iterator
                                         !xs)]
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__36481
                                          ['meander.dev.parse.zeta/make-star
                                           (clojure.core/let
                                            [CATA_RESULT__9229__auto__
                                             (CATA__FN__36481
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
                                             (CATA__FN__36481
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
                        (meander.runtime.zeta/fail? result__37526)
                        (recur (clojure.core/next search_space__37525))
                        result__37526))
                      (state__37511))))]
                  (state__37515))
                 (state__37511))
                (state__37511)))
              (state__37511)))
            (state__37511
             []
             (if
              (clojure.core/= (clojure.core/count input__36431) 4)
              (clojure.core/let
               [input__36431_nth_0__
                (clojure.core/nth input__36431 0)
                input__36431_nth_1__
                (clojure.core/nth input__36431 1)
                input__36431_nth_2__
                (clojure.core/nth input__36431 2)]
               (clojure.core/letfn
                [(state__37530
                  []
                  (clojure.core/let
                   [input__36431_nth_3__
                    (clojure.core/nth input__36431 3)]
                   (clojure.core/case
                    input__36431_nth_0__
                    (meander.dev.parse.zeta/make-star)
                    (clojure.core/letfn
                     [(state__37532
                       []
                       (if
                        (clojure.core/map? input__36431_nth_1__)
                        (clojure.core/let
                         [VAL__36647
                          (.valAt input__36431_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__36647
                          (:cat)
                          (clojure.core/let
                           [VAL__36648
                            (.valAt input__36431_nth_1__ :sequence)]
                           (if
                            (clojure.core/vector? VAL__36648)
                            (if
                             (clojure.core/=
                              (clojure.core/count VAL__36648)
                              1)
                             (clojure.core/let
                              [VAL__36648_nth_0__
                               (clojure.core/nth VAL__36648 0)]
                              (if
                               (clojure.core/map? VAL__36648_nth_0__)
                               (clojure.core/let
                                [VAL__36653
                                 (.valAt VAL__36648_nth_0__ :tag)]
                                (clojure.core/case
                                 VAL__36653
                                 (:memory-variable)
                                 (clojure.core/let
                                  [?memory-variable VAL__36648_nth_0__]
                                  (clojure.core/let
                                   [VAL__36649
                                    (.valAt
                                     input__36431_nth_1__
                                     :next)]
                                   (if
                                    (clojure.core/map? VAL__36649)
                                    (clojure.core/let
                                     [VAL__36650
                                      (.valAt VAL__36649 :tag)]
                                     (clojure.core/case
                                      VAL__36650
                                      (:empty)
                                      (clojure.core/let
                                       [?next input__36431_nth_2__]
                                       (clojure.core/let
                                        [?env input__36431_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__9229__auto__
                                            (CATA__FN__36481
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
                                      (state__37533)))
                                    (state__37533))))
                                 (state__37533)))
                               (state__37533)))
                             (state__37533))
                            (state__37533)))
                          (state__37533)))
                        (state__37533)))
                      (state__37533
                       []
                       (clojure.core/let
                        [?pattern input__36431_nth_1__]
                        (clojure.core/let
                         [?next input__36431_nth_2__]
                         (if
                          (clojure.core/map? input__36431_nth_3__)
                          (clojure.core/let
                           [VAL__36656
                            (.valAt input__36431_nth_3__ :context)]
                           (clojure.core/case
                            VAL__36656
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
                            (state__37531)))
                          (state__37531)))))]
                     (state__37532))
                    (state__37531))))
                 (state__37531
                  []
                  (clojure.core/case
                   input__36431_nth_0__
                   (meander.dev.parse.zeta/make-star)
                   (clojure.core/let
                    [?pattern input__36431_nth_1__]
                    (clojure.core/let
                     [?next input__36431_nth_2__]
                     (try
                      [{:tag :star, :pattern ?pattern, :next ?next}]
                      (catch
                       java.lang.Exception
                       e__10169__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10169__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10169__auto__))))))
                   (state__37512)))]
                (state__37530)))
              (state__37512)))
            (state__37512
             []
             (if
              (clojure.core/= (clojure.core/count input__36431) 3)
              (clojure.core/let
               [input__36431_nth_0__
                (clojure.core/nth input__36431 0)
                input__36431_nth_1__
                (clojure.core/nth input__36431 1)
                input__36431_nth_2__
                (clojure.core/nth input__36431 2)]
               (clojure.core/case
                input__36431_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__36431_nth_1__)
                 (clojure.core/letfn
                  [(state__37534
                    []
                    (clojure.core/let
                     [input__36431_nth_1___l__
                      (clojure.core/subvec
                       input__36431_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__36431_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__36431_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__36431_nth_1___r__
                        (clojure.core/subvec input__36431_nth_1__ 1)]
                       (clojure.core/let
                        [input__36431_nth_1___l___nth_0__
                         (clojure.core/nth input__36431_nth_1___l__ 0)]
                        (if
                         (clojure.core/symbol?
                          input__36431_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__36664
                           (clojure.core/namespace
                            input__36431_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__36664
                           (nil)
                           (clojure.core/let
                            [X__36666
                             (clojure.core/name
                              input__36431_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__36666)
                             (clojure.core/let
                              [ret__36667
                               (clojure.core/re-matches
                                #"\.\.(\d+)"
                                X__36666)]
                              (if
                               (clojure.core/some? ret__36667)
                               (if
                                (clojure.core/vector? ret__36667)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__36667)
                                  2)
                                 (clojure.core/let
                                  [ret__36667_nth_1__
                                   (clojure.core/nth ret__36667 1)]
                                  (clojure.core/let
                                   [?n ret__36667_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__36431_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__36431_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__36431_nth_2__]
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
                                 (state__37535))
                                (state__37535))
                               (state__37535)))
                             (state__37535)))
                           (state__37535)))
                         (state__37535))))
                      (state__37535))))
                   (state__37535
                    []
                    (clojure.core/loop
                     [search_space__37541
                      (meander.match.runtime.epsilon/partitions
                       2
                       input__36431_nth_1__)]
                     (if
                      (clojure.core/seq search_space__37541)
                      (clojure.core/let
                       [input__36431_nth_1___parts__
                        (clojure.core/first search_space__37541)
                        result__37542
                        (clojure.core/let
                         [input__36431_nth_1___l__
                          (clojure.core/nth
                           input__36431_nth_1___parts__
                           0)
                          input__36431_nth_1___r__
                          (clojure.core/nth
                           input__36431_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__8090__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__36431_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__36683]
                              (clojure.core/let
                               [input__36683_nth_0__
                                (clojure.core/nth input__36683 0)]
                               (clojure.core/letfn
                                [(save__36684
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__37545
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__36683_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__36683_nth_0__)
                                 (clojure.core/let
                                  [X__36686
                                   (clojure.core/namespace
                                    input__36683_nth_0__)]
                                  (clojure.core/case
                                   X__36686
                                   (nil)
                                   (clojure.core/let
                                    [X__36688
                                     (clojure.core/name
                                      input__36683_nth_0__)]
                                    (if
                                     (clojure.core/string? X__36688)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__36688)
                                      (save__36684)
                                      (f__37545))
                                     (f__37545)))
                                   (f__37545)))
                                 (f__37545)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__36431_nth_1___r___l__
                                (clojure.core/subvec
                                 input__36431_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__36431_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__36431_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__36431_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__36431_nth_1___r__
                                   1)]
                                 (clojure.core/let
                                  [input__36431_nth_1___r___l___nth_0__
                                   (clojure.core/nth
                                    input__36431_nth_1___r___l__
                                    0)]
                                  (if
                                   (clojure.core/symbol?
                                    input__36431_nth_1___r___l___nth_0__)
                                   (clojure.core/let
                                    [X__36677
                                     (clojure.core/namespace
                                      input__36431_nth_1___r___l___nth_0__)]
                                    (clojure.core/case
                                     X__36677
                                     (nil)
                                     (clojure.core/let
                                      [X__36679
                                       (clojure.core/name
                                        input__36431_nth_1___r___l___nth_0__)]
                                      (if
                                       (clojure.core/string? X__36679)
                                       (clojure.core/let
                                        [ret__36680
                                         (clojure.core/re-matches
                                          #"\.\.(\d+)"
                                          X__36679)]
                                        (if
                                         (clojure.core/some?
                                          ret__36680)
                                         (if
                                          (clojure.core/vector?
                                           ret__36680)
                                          (if
                                           (clojure.core/=
                                            (clojure.core/count
                                             ret__36680)
                                            2)
                                           (clojure.core/let
                                            [ret__36680_nth_1__
                                             (clojure.core/nth
                                              ret__36680
                                              1)]
                                            (clojure.core/let
                                             [?n ret__36680_nth_1__]
                                             (clojure.core/let
                                              [?rest
                                               input__36431_nth_1___r___r__]
                                              (clojure.core/let
                                               [?env
                                                input__36431_nth_2__]
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
                                                     (CATA__FN__36481
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
                                                     (CATA__FN__36481
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
                        (meander.runtime.zeta/fail? result__37542)
                        (recur (clojure.core/next search_space__37541))
                        result__37542))
                      (state__37536))))
                   (state__37536
                    []
                    (clojure.core/let
                     [input__36431_nth_1___l__
                      (clojure.core/subvec
                       input__36431_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__36431_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__36431_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__36431_nth_1___r__
                        (clojure.core/subvec input__36431_nth_1__ 1)]
                       (clojure.core/let
                        [input__36431_nth_1___l___nth_0__
                         (clojure.core/nth input__36431_nth_1___l__ 0)]
                        (if
                         (clojure.core/symbol?
                          input__36431_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__36695
                           (clojure.core/namespace
                            input__36431_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__36695
                           (nil)
                           (clojure.core/let
                            [X__36697
                             (clojure.core/name
                              input__36431_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__36697)
                             (clojure.core/let
                              [ret__36698
                               (clojure.core/re-matches
                                #"\.\.(\?.+)"
                                X__36697)]
                              (if
                               (clojure.core/some? ret__36698)
                               (if
                                (clojure.core/vector? ret__36698)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__36698)
                                  2)
                                 (clojure.core/let
                                  [ret__36698_nth_1__
                                   (clojure.core/nth ret__36698 1)]
                                  (clojure.core/let
                                   [?n ret__36698_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__36431_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__36431_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__36431_nth_2__]
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
                                 (state__37537))
                                (state__37537))
                               (state__37537)))
                             (state__37537)))
                           (state__37537)))
                         (state__37537))))
                      (state__37537))))
                   (state__37537
                    []
                    (clojure.core/loop
                     [search_space__37546
                      (meander.match.runtime.epsilon/partitions
                       2
                       input__36431_nth_1__)]
                     (if
                      (clojure.core/seq search_space__37546)
                      (clojure.core/let
                       [input__36431_nth_1___parts__
                        (clojure.core/first search_space__37546)
                        result__37547
                        (clojure.core/let
                         [input__36431_nth_1___l__
                          (clojure.core/nth
                           input__36431_nth_1___parts__
                           0)
                          input__36431_nth_1___r__
                          (clojure.core/nth
                           input__36431_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__8090__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__36431_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__36714]
                              (clojure.core/let
                               [input__36714_nth_0__
                                (clojure.core/nth input__36714 0)]
                               (clojure.core/letfn
                                [(save__36715
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__37550
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__36714_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__36714_nth_0__)
                                 (clojure.core/let
                                  [X__36717
                                   (clojure.core/namespace
                                    input__36714_nth_0__)]
                                  (clojure.core/case
                                   X__36717
                                   (nil)
                                   (clojure.core/let
                                    [X__36719
                                     (clojure.core/name
                                      input__36714_nth_0__)]
                                    (if
                                     (clojure.core/string? X__36719)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__36719)
                                      (save__36715)
                                      (f__37550))
                                     (f__37550)))
                                   (f__37550)))
                                 (f__37550)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__36431_nth_1___r___l__
                                (clojure.core/subvec
                                 input__36431_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__36431_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__36431_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__36431_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__36431_nth_1___r__
                                   1)]
                                 (clojure.core/let
                                  [input__36431_nth_1___r___l___nth_0__
                                   (clojure.core/nth
                                    input__36431_nth_1___r___l__
                                    0)]
                                  (if
                                   (clojure.core/symbol?
                                    input__36431_nth_1___r___l___nth_0__)
                                   (clojure.core/let
                                    [X__36708
                                     (clojure.core/namespace
                                      input__36431_nth_1___r___l___nth_0__)]
                                    (clojure.core/case
                                     X__36708
                                     (nil)
                                     (clojure.core/let
                                      [X__36710
                                       (clojure.core/name
                                        input__36431_nth_1___r___l___nth_0__)]
                                      (if
                                       (clojure.core/string? X__36710)
                                       (clojure.core/let
                                        [ret__36711
                                         (clojure.core/re-matches
                                          #"\.\.(\?.+)"
                                          X__36710)]
                                        (if
                                         (clojure.core/some?
                                          ret__36711)
                                         (if
                                          (clojure.core/vector?
                                           ret__36711)
                                          (if
                                           (clojure.core/=
                                            (clojure.core/count
                                             ret__36711)
                                            2)
                                           (clojure.core/let
                                            [ret__36711_nth_1__
                                             (clojure.core/nth
                                              ret__36711
                                              1)]
                                            (clojure.core/let
                                             [?n ret__36711_nth_1__]
                                             (clojure.core/let
                                              [?rest
                                               input__36431_nth_1___r___r__]
                                              (clojure.core/let
                                               [?env
                                                input__36431_nth_2__]
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
                                                     (CATA__FN__36481
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
                                                     (CATA__FN__36481
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
                        (meander.runtime.zeta/fail? result__37547)
                        (recur (clojure.core/next search_space__37546))
                        result__37547))
                      (state__37538))))
                   (state__37538
                    []
                    (clojure.core/let
                     [input__36431_nth_1___l__
                      (clojure.core/subvec
                       input__36431_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__36431_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__36431_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__36431_nth_1___r__
                        (clojure.core/subvec input__36431_nth_1__ 1)]
                       (clojure.core/let
                        [input__36431_nth_1___l___nth_0__
                         (clojure.core/nth input__36431_nth_1___l__ 0)]
                        (if
                         (clojure.core/symbol?
                          input__36431_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__36726
                           (clojure.core/namespace
                            input__36431_nth_1___l___nth_0__)]
                          (clojure.core/case
                           X__36726
                           (nil)
                           (clojure.core/let
                            [X__36728
                             (clojure.core/name
                              input__36431_nth_1___l___nth_0__)]
                            (if
                             (clojure.core/string? X__36728)
                             (clojure.core/let
                              [ret__36729
                               (clojure.core/re-matches
                                #"\.\.(!.+)"
                                X__36728)]
                              (if
                               (clojure.core/some? ret__36729)
                               (if
                                (clojure.core/vector? ret__36729)
                                (if
                                 (clojure.core/=
                                  (clojure.core/count ret__36729)
                                  2)
                                 (clojure.core/let
                                  [ret__36729_nth_1__
                                   (clojure.core/nth ret__36729 1)]
                                  (clojure.core/let
                                   [?n ret__36729_nth_1__]
                                   (clojure.core/let
                                    [?operator
                                     input__36431_nth_1___l___nth_0__]
                                    (clojure.core/let
                                     [?rest input__36431_nth_1___r__]
                                     (clojure.core/let
                                      [?env input__36431_nth_2__]
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
                                 (state__37539))
                                (state__37539))
                               (state__37539)))
                             (state__37539)))
                           (state__37539)))
                         (state__37539))))
                      (state__37539))))
                   (state__37539
                    []
                    (clojure.core/loop
                     [search_space__37551
                      (meander.match.runtime.epsilon/partitions
                       2
                       input__36431_nth_1__)]
                     (if
                      (clojure.core/seq search_space__37551)
                      (clojure.core/let
                       [input__36431_nth_1___parts__
                        (clojure.core/first search_space__37551)
                        result__37552
                        (clojure.core/let
                         [input__36431_nth_1___l__
                          (clojure.core/nth
                           input__36431_nth_1___parts__
                           0)
                          input__36431_nth_1___r__
                          (clojure.core/nth
                           input__36431_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__8090__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__36431_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__36745]
                              (clojure.core/let
                               [input__36745_nth_0__
                                (clojure.core/nth input__36745 0)]
                               (clojure.core/letfn
                                [(save__36746
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__37555
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__36745_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__36745_nth_0__)
                                 (clojure.core/let
                                  [X__36748
                                   (clojure.core/namespace
                                    input__36745_nth_0__)]
                                  (clojure.core/case
                                   X__36748
                                   (nil)
                                   (clojure.core/let
                                    [X__36750
                                     (clojure.core/name
                                      input__36745_nth_0__)]
                                    (if
                                     (clojure.core/string? X__36750)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__36750)
                                      (save__36746)
                                      (f__37555))
                                     (f__37555)))
                                   (f__37555)))
                                 (f__37555)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__36431_nth_1___r___l__
                                (clojure.core/subvec
                                 input__36431_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__36431_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__36431_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__36431_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__36431_nth_1___r__
                                   1)]
                                 (clojure.core/let
                                  [input__36431_nth_1___r___l___nth_0__
                                   (clojure.core/nth
                                    input__36431_nth_1___r___l__
                                    0)]
                                  (if
                                   (clojure.core/symbol?
                                    input__36431_nth_1___r___l___nth_0__)
                                   (clojure.core/let
                                    [X__36739
                                     (clojure.core/namespace
                                      input__36431_nth_1___r___l___nth_0__)]
                                    (clojure.core/case
                                     X__36739
                                     (nil)
                                     (clojure.core/let
                                      [X__36741
                                       (clojure.core/name
                                        input__36431_nth_1___r___l___nth_0__)]
                                      (if
                                       (clojure.core/string? X__36741)
                                       (clojure.core/let
                                        [ret__36742
                                         (clojure.core/re-matches
                                          #"\.\.(\!.+)"
                                          X__36741)]
                                        (if
                                         (clojure.core/some?
                                          ret__36742)
                                         (if
                                          (clojure.core/vector?
                                           ret__36742)
                                          (if
                                           (clojure.core/=
                                            (clojure.core/count
                                             ret__36742)
                                            2)
                                           (clojure.core/let
                                            [ret__36742_nth_1__
                                             (clojure.core/nth
                                              ret__36742
                                              1)]
                                            (clojure.core/let
                                             [?n ret__36742_nth_1__]
                                             (clojure.core/let
                                              [?rest
                                               input__36431_nth_1___r___r__]
                                              (clojure.core/let
                                               [?env
                                                input__36431_nth_2__]
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
                                                     (CATA__FN__36481
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
                                                     (CATA__FN__36481
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
                        (meander.runtime.zeta/fail? result__37552)
                        (recur (clojure.core/next search_space__37551))
                        result__37552))
                      (state__37540))))
                   (state__37540
                    []
                    (clojure.core/let
                     [!xs (clojure.core/vec input__36431_nth_1__)]
                     (clojure.core/let
                      [?env input__36431_nth_2__]
                      (try
                       [(clojure.core/let
                         [!xs__counter
                          (meander.runtime.zeta/iterator !xs)]
                         (clojure.core/let
                          [CATA_RESULT__9229__auto__
                           (CATA__FN__36481
                            ['meander.dev.parse.zeta/make-cate
                             (clojure.core/into
                              []
                              (clojure.core/loop
                               [return__36482
                                (clojure.core/transient [])]
                               (if
                                (clojure.core/and
                                 (.hasNext !xs__counter))
                                (recur
                                 (clojure.core/conj!
                                  return__36482
                                  (clojure.core/let
                                   [CATA_RESULT__9229__auto__
                                    (CATA__FN__36481
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
                                 return__36482))))
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
                  (state__37534))
                 (state__37513))
                (state__37513)))
              (state__37513)))
            (state__37513
             []
             (if
              (clojure.core/= (clojure.core/count input__36431) 4)
              (clojure.core/let
               [input__36431_nth_0__
                (clojure.core/nth input__36431 0)
                input__36431_nth_1__
                (clojure.core/nth input__36431 1)
                input__36431_nth_2__
                (clojure.core/nth input__36431 2)]
               (clojure.core/letfn
                [(state__37556
                  []
                  (clojure.core/case
                   input__36431_nth_0__
                   (meander.dev.parse.zeta/make-cate)
                   (if
                    (clojure.core/vector? input__36431_nth_1__)
                    (clojure.core/let
                     [!forms []]
                     (clojure.core/let
                      [ret__8090__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__36431_nth_1__
                        [!forms]
                        (clojure.core/fn
                         [[!forms] input__36761]
                         (clojure.core/let
                          [input__36761_nth_0__
                           (clojure.core/nth input__36761 0)]
                          (if
                           (clojure.core/map? input__36761_nth_0__)
                           (clojure.core/let
                            [VAL__36762
                             (.valAt input__36761_nth_0__ :tag)]
                            (clojure.core/case
                             VAL__36762
                             (:literal)
                             (clojure.core/let
                              [VAL__36763
                               (.valAt input__36761_nth_0__ :type)]
                              (if
                               (clojure.core/let
                                [x__6986__auto__ VAL__36763]
                                (clojure.core/case
                                 x__6986__auto__
                                 (:string :char)
                                 true
                                 false))
                               (clojure.core/let
                                [VAL__36764
                                 (.valAt input__36761_nth_0__ :form)]
                                (clojure.core/let
                                 [!forms
                                  (clojure.core/conj
                                   !forms
                                   VAL__36764)]
                                 [!forms]))
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail)))
                           (meander.runtime.zeta/fail))))
                        (clojure.core/fn
                         [[!forms]]
                         (if
                          (clojure.core/map? input__36431_nth_2__)
                          (clojure.core/let
                           [VAL__36758
                            (.valAt input__36431_nth_2__ :tag)]
                           (clojure.core/case
                            VAL__36758
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
                            (state__37557)))
                          (state__37557))))]
                      (if
                       (meander.runtime.zeta/fail? ret__8090__auto__)
                       (state__37557)
                       ret__8090__auto__)))
                    (state__37557))
                   (state__37557)))
                 (state__37557
                  []
                  (clojure.core/let
                   [input__36431_nth_3__
                    (clojure.core/nth input__36431 3)]
                   (clojure.core/case
                    input__36431_nth_0__
                    (meander.dev.parse.zeta/make-cate)
                    (if
                     (clojure.core/vector? input__36431_nth_1__)
                     (clojure.core/letfn
                      [(state__37561
                        []
                        (clojure.core/let
                         [input__36431_nth_1___l__
                          (clojure.core/subvec
                           input__36431_nth_1__
                           0
                           (clojure.core/min
                            (clojure.core/count input__36431_nth_1__)
                            1))]
                         (if
                          (clojure.core/=
                           (clojure.core/count
                            input__36431_nth_1___l__)
                           1)
                          (clojure.core/let
                           [input__36431_nth_1___r__
                            (clojure.core/subvec
                             input__36431_nth_1__
                             1)]
                           (clojure.core/let
                            [input__36431_nth_1___l___nth_0__
                             (clojure.core/nth
                              input__36431_nth_1___l__
                              0)]
                            (if
                             (clojure.core/map?
                              input__36431_nth_1___l___nth_0__)
                             (clojure.core/let
                              [VAL__36770
                               (.valAt
                                input__36431_nth_1___l___nth_0__
                                :tag)]
                              (clojure.core/case
                               VAL__36770
                               (:literal)
                               (clojure.core/let
                                [VAL__36771
                                 (.valAt
                                  input__36431_nth_1___l___nth_0__
                                  :type)]
                                (clojure.core/case
                                 VAL__36771
                                 (:string)
                                 (clojure.core/let
                                  [?ast
                                   input__36431_nth_1___l___nth_0__]
                                  (clojure.core/let
                                   [?rest input__36431_nth_1___r__]
                                   (clojure.core/let
                                    [?next input__36431_nth_2__]
                                    (clojure.core/let
                                     [?env input__36431_nth_3__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__36481
                                          ['meander.dev.parse.zeta/make-join
                                           ?ast
                                           (clojure.core/let
                                            [CATA_RESULT__9229__auto__
                                             (CATA__FN__36481
                                              ['meander.dev.parse.zeta/make-cate
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
                                 (state__37562)))
                               (state__37562)))
                             (state__37562))))
                          (state__37562))))
                       (state__37562
                        []
                        (clojure.core/let
                         [!forms []]
                         (clojure.core/let
                          [ret__8090__auto__
                           (meander.runtime.zeta/epsilon-run-star-1
                            input__36431_nth_1__
                            [!forms]
                            (clojure.core/fn
                             [[!forms] input__36777]
                             (clojure.core/let
                              [input__36777_nth_0__
                               (clojure.core/nth input__36777 0)]
                              (if
                               (clojure.core/map? input__36777_nth_0__)
                               (clojure.core/let
                                [VAL__36778
                                 (.valAt input__36777_nth_0__ :tag)]
                                (clojure.core/case
                                 VAL__36778
                                 (:literal)
                                 (clojure.core/let
                                  [VAL__36779
                                   (.valAt input__36777_nth_0__ :form)]
                                  (clojure.core/let
                                   [!forms
                                    (clojure.core/conj
                                     !forms
                                     VAL__36779)]
                                   [!forms]))
                                 (meander.runtime.zeta/fail)))
                               (meander.runtime.zeta/fail))))
                            (clojure.core/fn
                             [[!forms]]
                             (if
                              (clojure.core/map? input__36431_nth_2__)
                              (clojure.core/let
                               [VAL__36774
                                (.valAt input__36431_nth_2__ :tag)]
                               (clojure.core/case
                                VAL__36774
                                (:empty)
                                (clojure.core/let
                                 [?env input__36431_nth_3__]
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
                                (state__37558)))
                              (state__37558))))]
                          (if
                           (meander.runtime.zeta/fail?
                            ret__8090__auto__)
                           (state__37558)
                           ret__8090__auto__))))]
                      (state__37561))
                     (state__37558))
                    (state__37558))))
                 (state__37558
                  []
                  (clojure.core/case
                   input__36431_nth_0__
                   (meander.dev.parse.zeta/make-cate)
                   (clojure.core/let
                    [?sequence input__36431_nth_1__]
                    (clojure.core/let
                     [?next input__36431_nth_2__]
                     (try
                      [{:tag :cat, :sequence ?sequence, :next ?next}]
                      (catch
                       java.lang.Exception
                       e__10169__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10169__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10169__auto__))))))
                   (state__37559)))
                 (state__37559
                  []
                  (clojure.core/let
                   [input__36431_nth_3__
                    (clojure.core/nth input__36431 3)]
                   (clojure.core/case
                    input__36431_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (clojure.core/letfn
                     [(state__37564
                       []
                       (if
                        (clojure.core/map? input__36431_nth_1__)
                        (clojure.core/let
                         [VAL__36785
                          (.valAt input__36431_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__36785
                          (:cat)
                          (clojure.core/let
                           [VAL__36786
                            (.valAt input__36431_nth_1__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__36786]
                            (clojure.core/let
                             [VAL__36787
                              (.valAt input__36431_nth_1__ :next)]
                             (if
                              (clojure.core/map? VAL__36787)
                              (clojure.core/let
                               [VAL__36788 (.valAt VAL__36787 :tag)]
                               (clojure.core/case
                                VAL__36788
                                (:empty)
                                (clojure.core/let
                                 [?right input__36431_nth_2__]
                                 (clojure.core/let
                                  [?env input__36431_nth_3__]
                                  (try
                                   [(clojure.core/let
                                     [CATA_RESULT__9229__auto__
                                      (CATA__FN__36481
                                       ['meander.dev.parse.zeta/make-cate
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
                                (state__37565)))
                              (state__37565)))))
                          (state__37565)))
                        (state__37565)))
                      (state__37565
                       []
                       (clojure.core/let
                        [?left input__36431_nth_1__]
                        (if
                         (clojure.core/map? input__36431_nth_2__)
                         (clojure.core/let
                          [VAL__36791
                           (.valAt input__36431_nth_2__ :tag)]
                          (clojure.core/case
                           VAL__36791
                           (:empty)
                           (clojure.core/let
                            [?env input__36431_nth_3__]
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
                           (state__37566)))
                         (state__37566))))
                      (state__37566
                       []
                       (if
                        (clojure.core/map? input__36431_nth_1__)
                        (clojure.core/let
                         [VAL__36794
                          (.valAt input__36431_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__36794
                          (:empty)
                          (clojure.core/let
                           [?right input__36431_nth_2__]
                           (clojure.core/let
                            [?env input__36431_nth_3__]
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
                          (state__37567)))
                        (state__37567)))
                      (state__37567
                       []
                       (clojure.core/let
                        [?left input__36431_nth_1__]
                        (clojure.core/let
                         [?right input__36431_nth_2__]
                         (clojure.core/letfn
                          [(state__37569
                            []
                            (if
                             (clojure.core/map? input__36431_nth_3__)
                             (clojure.core/let
                              [VAL__36797
                               (.valAt input__36431_nth_3__ :context)]
                              (clojure.core/case
                               VAL__36797
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
                               (state__37570)))
                             (state__37570)))
                           (state__37570
                            []
                            (clojure.core/let
                             [?env input__36431_nth_3__]
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
                          (state__37569)))))
                      (state__37568
                       []
                       (if
                        (clojure.core/map? input__36431_nth_1__)
                        (clojure.core/let
                         [VAL__37468
                          (.valAt input__36431_nth_1__ :type)
                          VAL__37467
                          (.valAt input__36431_nth_1__ :tag)]
                         (clojure.core/letfn
                          [(state__37571
                            []
                            (clojure.core/case
                             VAL__37467
                             (:literal)
                             (clojure.core/case
                              VAL__37468
                              (:string)
                              (clojure.core/let
                               [VAL__36804
                                (.valAt input__36431_nth_1__ :form)]
                               (clojure.core/let
                                [?form-1 VAL__36804]
                                (if
                                 (clojure.core/map?
                                  input__36431_nth_2__)
                                 (clojure.core/let
                                  [VAL__36805
                                   (.valAt input__36431_nth_2__ :tag)]
                                  (clojure.core/case
                                   VAL__36805
                                   (:string-join)
                                   (clojure.core/let
                                    [VAL__36806
                                     (.valAt
                                      input__36431_nth_2__
                                      :left)]
                                    (if
                                     (clojure.core/map? VAL__36806)
                                     (clojure.core/let
                                      [VAL__36807
                                       (.valAt VAL__36806 :tag)]
                                      (clojure.core/case
                                       VAL__36807
                                       (:literal)
                                       (clojure.core/let
                                        [VAL__36808
                                         (.valAt VAL__36806 :type)]
                                        (clojure.core/case
                                         VAL__36808
                                         (:string)
                                         (clojure.core/let
                                          [VAL__36809
                                           (.valAt VAL__36806 :form)]
                                          (clojure.core/let
                                           [?form-2 VAL__36809]
                                           (clojure.core/let
                                            [VAL__36810
                                             (.valAt
                                              input__36431_nth_2__
                                              :right)]
                                            (clojure.core/let
                                             [?right VAL__36810]
                                             (if
                                              (clojure.core/map?
                                               input__36431_nth_3__)
                                              (clojure.core/let
                                               [VAL__36811
                                                (.valAt
                                                 input__36431_nth_3__
                                                 :context)]
                                               (clojure.core/case
                                                VAL__36811
                                                (:string)
                                                (clojure.core/let
                                                 [?env
                                                  input__36431_nth_3__]
                                                 (try
                                                  [(clojure.core/let
                                                    [CATA_RESULT__9229__auto__
                                                     (CATA__FN__36481
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
                                                (state__37572)))
                                              (state__37572))))))
                                         (state__37572)))
                                       (state__37572)))
                                     (state__37572)))
                                   (state__37572)))
                                 (state__37572))))
                              (state__37572))
                             (state__37572)))
                           (state__37572
                            []
                            (clojure.core/let
                             [VAL__37466
                              (.valAt input__36431_nth_1__ :next)]
                             (clojure.core/case
                              VAL__37467
                              (:literal)
                              (clojure.core/case
                               VAL__37468
                               (:string)
                               (clojure.core/let
                                [?ast input__36431_nth_1__]
                                (if
                                 (clojure.core/map?
                                  input__36431_nth_2__)
                                 (clojure.core/let
                                  [VAL__36816
                                   (.valAt input__36431_nth_2__ :tag)]
                                  (clojure.core/case
                                   VAL__36816
                                   (:string-cat)
                                   (clojure.core/let
                                    [VAL__36817
                                     (.valAt
                                      input__36431_nth_2__
                                      :sequence)]
                                    (clojure.core/let
                                     [?sequence VAL__36817]
                                     (clojure.core/let
                                      [VAL__36818
                                       (.valAt
                                        input__36431_nth_2__
                                        :next)]
                                      (clojure.core/let
                                       [?next VAL__36818]
                                       (if
                                        (clojure.core/map?
                                         input__36431_nth_3__)
                                        (clojure.core/let
                                         [VAL__36819
                                          (.valAt
                                           input__36431_nth_3__
                                           :context)]
                                         (clojure.core/case
                                          VAL__36819
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
                                          (state__37514)))
                                        (state__37514))))))
                                   (state__37514)))
                                 (state__37514)))
                               (state__37514))
                              (:string-cat)
                              (clojure.core/let
                               [VAL__36823
                                (.valAt
                                 input__36431_nth_1__
                                 :sequence)]
                               (clojure.core/let
                                [?sequence VAL__36823]
                                (if
                                 (clojure.core/map? VAL__37466)
                                 (clojure.core/let
                                  [VAL__36825 (.valAt VAL__37466 :tag)]
                                  (clojure.core/case
                                   VAL__36825
                                   (:empty)
                                   (clojure.core/let
                                    [?right input__36431_nth_2__]
                                    (clojure.core/let
                                     [?env input__36431_nth_3__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__36481
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
                                   (state__37514)))
                                 (state__37514))))
                              (:string-star)
                              (clojure.core/let
                               [VAL__36829
                                (.valAt input__36431_nth_1__ :pattern)]
                               (clojure.core/let
                                [?pattern VAL__36829]
                                (if
                                 (clojure.core/map? VAL__37466)
                                 (clojure.core/let
                                  [VAL__36831 (.valAt VAL__37466 :tag)]
                                  (clojure.core/case
                                   VAL__36831
                                   (:empty)
                                   (clojure.core/let
                                    [?right input__36431_nth_2__]
                                    (if
                                     (clojure.core/map?
                                      input__36431_nth_3__)
                                     (clojure.core/let
                                      [VAL__36832
                                       (.valAt
                                        input__36431_nth_3__
                                        :context)]
                                      (clojure.core/case
                                       VAL__36832
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
                                       (state__37514)))
                                     (state__37514)))
                                   (state__37514)))
                                 (state__37514))))
                              (:string-join)
                              (clojure.core/let
                               [VAL__36836
                                (.valAt input__36431_nth_1__ :left)]
                               (clojure.core/let
                                [?left VAL__36836]
                                (clojure.core/let
                                 [VAL__36837
                                  (.valAt input__36431_nth_1__ :right)]
                                 (clojure.core/let
                                  [?right-1 VAL__36837]
                                  (clojure.core/let
                                   [?right-2 input__36431_nth_2__]
                                   (if
                                    (clojure.core/map?
                                     input__36431_nth_3__)
                                    (clojure.core/let
                                     [VAL__36838
                                      (.valAt
                                       input__36431_nth_3__
                                       :context)]
                                     (clojure.core/case
                                      VAL__36838
                                      (:string)
                                      (clojure.core/let
                                       [?env input__36431_nth_3__]
                                       (try
                                        [{:tag :string-join,
                                          :left ?left,
                                          :right
                                          (clojure.core/let
                                           [CATA_RESULT__9229__auto__
                                            (CATA__FN__36481
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
                                      (state__37514)))
                                    (state__37514)))))))
                              (state__37514))))]
                          (state__37571)))
                        (state__37514)))]
                     (state__37564))
                    (state__37514))))]
                (state__37556)))
              (state__37514)))
            (state__37514
             []
             (if
              (clojure.core/= (clojure.core/count input__36431) 3)
              (clojure.core/let
               [input__36431_nth_0__
                (clojure.core/nth input__36431 0)
                input__36431_nth_1__
                (clojure.core/nth input__36431 1)
                input__36431_nth_2__
                (clojure.core/nth input__36431 2)]
               (if
                (clojure.core/=
                 input__36431_nth_0__
                 'meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__36431_nth_1__)
                 (clojure.core/let
                  [VAL__36841
                   (.valAt input__36431_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__36841]
                   (clojure.core/let
                    [X__36843
                     ((clojure.core/fn
                       [m__6993__auto__]
                       (clojure.core/dissoc
                        m__6993__auto__
                        :meander.zeta/as))
                      input__36431_nth_1__)]
                    (clojure.core/let
                     [?rest X__36843]
                     (clojure.core/letfn
                      [(save__36844 [] (state__37473))
                       (f__37573
                        []
                        (clojure.core/let
                         [?env input__36431_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern
                            (clojure.core/let
                             [CATA_RESULT__9229__auto__
                              (CATA__FN__36481 [?pattern ?env])]
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
                              (CATA__FN__36481 [?rest ?env])]
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
                            (meander.runtime.zeta/fail?
                             e__10169__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__10169__auto__))))))]
                      (if
                       (clojure.core/= ?rest input__36431_nth_1__)
                       (save__36844)
                       (f__37573)))))))
                 (state__37473))
                (state__37473)))
              (state__37473)))]
           (state__37510))
          (state__37473)))
        (state__37473
         []
         (clojure.core/letfn
          [(def__36847
            [arg__36880 ?ns]
            (clojure.core/letfn
             [(state__37574
               []
               (clojure.core/let
                [x__36881 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__36881)
                 (clojure.core/let [?env arg__36880] [?env ?ns])
                 (state__37575))))
              (state__37575
               []
               (if
                (clojure.core/map? arg__36880)
                (clojure.core/let
                 [VAL__36882 (.valAt arg__36880 :aliases)]
                 (if
                  (clojure.core/map? VAL__36882)
                  (clojure.core/let
                   [X__36884 (clojure.core/set VAL__36882)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__36884))
                    (clojure.core/loop
                     [search_space__37576 (clojure.core/seq X__36884)]
                     (if
                      (clojure.core/seq search_space__37576)
                      (clojure.core/let
                       [elem__36885
                        (clojure.core/first search_space__37576)
                        result__37577
                        (clojure.core/let
                         [elem__36885_nth_0__
                          (clojure.core/nth elem__36885 0)
                          elem__36885_nth_1__
                          (clojure.core/nth elem__36885 1)]
                         (if
                          (clojure.core/symbol? elem__36885_nth_0__)
                          (clojure.core/let
                           [X__36887
                            (clojure.core/name elem__36885_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__36887)
                            (if
                             (clojure.core/symbol? elem__36885_nth_1__)
                             (clojure.core/let
                              [X__36889
                               (clojure.core/name elem__36885_nth_1__)]
                              (clojure.core/case
                               X__36889
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__36880]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__37577)
                        (recur (clojure.core/next search_space__37576))
                        result__37577))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__37574)))]
          (if
           (clojure.core/vector? input__36431)
           (if
            (clojure.core/= (clojure.core/count input__36431) 3)
            (clojure.core/let
             [input__36431_nth_0__
              (clojure.core/nth input__36431 0)
              input__36431_nth_1__
              (clojure.core/nth input__36431 1)
              input__36431_nth_2__
              (clojure.core/nth input__36431 2)]
             (if
              (clojure.core/=
               input__36431_nth_0__
               'meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__36431_nth_1__)
               (clojure.core/let
                [X__36852 (clojure.core/set input__36431_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__36852))
                 (clojure.core/loop
                  [search_space__37579 (clojure.core/seq X__36852)]
                  (if
                   (clojure.core/seq search_space__37579)
                   (clojure.core/let
                    [elem__36853
                     (clojure.core/first search_space__37579)
                     result__37580
                     (clojure.core/let
                      [elem__36853_nth_0__
                       (clojure.core/nth elem__36853 0)
                       elem__36853_nth_1__
                       (clojure.core/nth elem__36853 1)]
                      (clojure.core/let
                       [*m__36450 elem__36853_nth_0__]
                       (if
                        (clojure.core/symbol? elem__36853_nth_0__)
                        (clojure.core/let
                         [X__36855
                          (clojure.core/namespace elem__36853_nth_0__)]
                         (clojure.core/let
                          [?ns X__36855]
                          (clojure.core/let
                           [X__36857
                            (clojure.core/name elem__36853_nth_0__)]
                           (if
                            (clojure.core/string? X__36857)
                            (if
                             (clojure.core/re-matches #"&.*" X__36857)
                             (clojure.core/let
                              [?pattern elem__36853_nth_1__]
                              (clojure.core/let
                               [X__36859
                                ((clojure.core/fn
                                  [m__6993__auto__]
                                  (clojure.core/dissoc
                                   m__6993__auto__
                                   *m__36450))
                                 input__36431_nth_1__)]
                               (clojure.core/let
                                [?rest X__36859]
                                (clojure.core/let
                                 [x__7926__auto__
                                  (def__36847
                                   input__36431_nth_2__
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
                                        (CATA__FN__36481
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
                                      (clojure.core/let
                                       [CATA_RESULT__9229__auto__
                                        (CATA__FN__36481
                                         ['meander.dev.parse.zeta/parse-entries
                                          ?rest
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
                                      (throw e__10169__auto__))))))))))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))))
                        (meander.runtime.zeta/fail))))]
                    (if
                     (meander.runtime.zeta/fail? result__37580)
                     (recur (clojure.core/next search_space__37579))
                     result__37580))
                   (state__37474)))
                 (state__37474)))
               (state__37474))
              (state__37474)))
            (state__37474))
           (state__37474))))
        (state__37474
         []
         (if
          (clojure.core/vector? input__36431)
          (clojure.core/letfn
           [(state__37582
             []
             (if
              (clojure.core/= (clojure.core/count input__36431) 3)
              (clojure.core/let
               [input__36431_nth_0__
                (clojure.core/nth input__36431 0)
                input__36431_nth_1__
                (clojure.core/nth input__36431 1)
                input__36431_nth_2__
                (clojure.core/nth input__36431 2)]
               (clojure.core/letfn
                [(state__37584
                  []
                  (if
                   (clojure.core/=
                    input__36431_nth_0__
                    'meander.dev.parse.zeta/parse-entries)
                   (if
                    (clojure.core/map? input__36431_nth_1__)
                    (clojure.core/let
                     [X__36903 (clojure.core/set input__36431_nth_1__)]
                     (if
                      (clojure.core/<= 1 (clojure.core/count X__36903))
                      (clojure.core/loop
                       [search_space__37590
                        (clojure.core/seq X__36903)]
                       (if
                        (clojure.core/seq search_space__37590)
                        (clojure.core/let
                         [elem__36904
                          (clojure.core/first search_space__37590)
                          result__37591
                          (clojure.core/let
                           [elem__36904_nth_0__
                            (clojure.core/nth elem__36904 0)
                            elem__36904_nth_1__
                            (clojure.core/nth elem__36904 1)]
                           (clojure.core/let
                            [?key-pattern elem__36904_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__36904_nth_1__]
                             (clojure.core/let
                              [X__36906
                               ((clojure.core/fn
                                 [m__6993__auto__]
                                 (clojure.core/dissoc
                                  m__6993__auto__
                                  ?key-pattern))
                                input__36431_nth_1__)]
                              (clojure.core/let
                               [?rest X__36906]
                               (clojure.core/let
                                [?env input__36431_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__9229__auto__
                                     (CATA__FN__36481
                                      [?key-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__9229__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__9229__auto__
                                      0))),
                                   :val-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__9229__auto__
                                     (CATA__FN__36481
                                      [?val-pattern ?env])]
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
                                     (CATA__FN__36481
                                      ['meander.dev.parse.zeta/parse-entries
                                       ?rest
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
                                   (throw e__10169__auto__))))))))))]
                         (if
                          (meander.runtime.zeta/fail? result__37591)
                          (recur
                           (clojure.core/next search_space__37590))
                          result__37591))
                        (state__37585)))
                      (state__37585)))
                    (state__37585))
                   (state__37585)))
                 (state__37585
                  []
                  (if
                   (clojure.core/=
                    input__36431_nth_0__
                    'meander.dev.parse.zeta/parse-entries)
                   (if
                    (clojure.core/map? input__36431_nth_1__)
                    (clojure.core/let
                     [?env input__36431_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__10169__auto__
                       (if
                        (meander.runtime.zeta/fail? e__10169__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__10169__auto__)))))
                    (state__37586))
                   (state__37586)))
                 (state__37586
                  []
                  (if
                   (clojure.core/=
                    input__36431_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__36431_nth_1__)
                    (clojure.core/case
                     input__36431_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__36431_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__10169__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10169__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10169__auto__)))))
                     (state__37587))
                    (state__37587))
                   (state__37587)))
                 (state__37587
                  []
                  (if
                   (clojure.core/=
                    input__36431_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__36431_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__36431_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__36431_nth_2__]
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
                     (state__37588))
                    (state__37588))
                   (state__37588)))
                 (state__37588
                  []
                  (if
                   (clojure.core/=
                    input__36431_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__36431_nth_1__)
                    (clojure.core/let
                     [input__36431_nth_1___l__
                      (clojure.core/subvec
                       input__36431_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__36431_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__36431_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__36431_nth_1___r__
                        (clojure.core/subvec input__36431_nth_1__ 2)]
                       (clojure.core/let
                        [input__36431_nth_1___l___nth_0__
                         (clojure.core/nth input__36431_nth_1___l__ 0)
                         input__36431_nth_1___l___nth_1__
                         (clojure.core/nth input__36431_nth_1___l__ 1)]
                        (if
                         (clojure.core/symbol?
                          input__36431_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__36920
                           (clojure.core/namespace
                            input__36431_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__36922
                            (clojure.core/name
                             input__36431_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__36922)
                            (if
                             (clojure.core/re-matches #"%.+" X__36922)
                             (clojure.core/let
                              [?symbol
                               input__36431_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__36431_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__36431_nth_1___r__]
                                (clojure.core/let
                                 [?env input__36431_nth_2__]
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
                                         (CATA__FN__36481
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
                                       (CATA__FN__36481
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
                             (state__37589))
                            (state__37589))))
                         (state__37589))))
                      (state__37589)))
                    (state__37589))
                   (state__37589)))
                 (state__37589
                  []
                  (if
                   (clojure.core/=
                    input__36431_nth_0__
                    'meander.dev.parse.zeta/parse-with-bindings)
                   (if
                    (clojure.core/vector? input__36431_nth_1__)
                    (clojure.core/let
                     [input__36431_nth_1___l__
                      (clojure.core/subvec
                       input__36431_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__36431_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__36431_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__36431_nth_1___r__
                        (clojure.core/subvec input__36431_nth_1__ 2)]
                       (clojure.core/let
                        [input__36431_nth_1___l___nth_0__
                         (clojure.core/nth input__36431_nth_1___l__ 0)
                         input__36431_nth_1___l___nth_1__
                         (clojure.core/nth input__36431_nth_1___l__ 1)]
                        (clojure.core/let
                         [?x input__36431_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__36431_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__36431_nth_1___r__]
                           (clojure.core/let
                            [?env input__36431_nth_2__]
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
                               (throw e__10169__auto__))))))))))
                      (state__37583)))
                    (state__37583))
                   (state__37583)))]
                (state__37584)))
              (state__37583)))
            (state__37583
             []
             (if
              (clojure.core/= (clojure.core/count input__36431) 2)
              (clojure.core/let
               [input__36431_nth_0__
                (clojure.core/nth input__36431 0)
                input__36431_nth_1__
                (clojure.core/nth input__36431 1)]
               (if
                (clojure.core/vector? input__36431_nth_0__)
                (clojure.core/let
                 [?sequence input__36431_nth_0__]
                 (clojure.core/let
                  [?form input__36431_nth_0__]
                  (clojure.core/let
                   [?env input__36431_nth_1__]
                   (try
                    [{:tag :vector,
                      :next
                      (clojure.core/let
                       [CATA_RESULT__9229__auto__
                        (CATA__FN__36481
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
                (state__37475)))
              (state__37475)))]
           (state__37582))
          (state__37475)))
        (state__37475
         []
         (clojure.core/letfn
          [(def__36932
            [arg__36955 ?__36432]
            (clojure.core/letfn
             [(state__37593
               []
               (clojure.core/let
                [x__36956 "meander.zeta"]
                (if
                 (clojure.core/= ?__36432 x__36956)
                 [?__36432]
                 (state__37594))))
              (state__37594
               []
               (if
                (clojure.core/map? arg__36955)
                (clojure.core/let
                 [VAL__36957 (.valAt arg__36955 :aliases)]
                 (if
                  (clojure.core/map? VAL__36957)
                  (clojure.core/let
                   [X__36959 (clojure.core/set VAL__36957)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__36959))
                    (clojure.core/loop
                     [search_space__37595 (clojure.core/seq X__36959)]
                     (if
                      (clojure.core/seq search_space__37595)
                      (clojure.core/let
                       [elem__36960
                        (clojure.core/first search_space__37595)
                        result__37596
                        (clojure.core/let
                         [elem__36960_nth_0__
                          (clojure.core/nth elem__36960 0)
                          elem__36960_nth_1__
                          (clojure.core/nth elem__36960 1)]
                         (if
                          (clojure.core/symbol? elem__36960_nth_0__)
                          (clojure.core/let
                           [X__36962
                            (clojure.core/name elem__36960_nth_0__)]
                           (if
                            (clojure.core/= ?__36432 X__36962)
                            (if
                             (clojure.core/symbol? elem__36960_nth_1__)
                             (clojure.core/let
                              [X__36964
                               (clojure.core/name elem__36960_nth_1__)]
                              (clojure.core/case
                               X__36964
                               ("meander.zeta")
                               [?__36432]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__37596)
                        (recur (clojure.core/next search_space__37595))
                        result__37596))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__37593)))]
          (if
           (clojure.core/vector? input__36431)
           (if
            (clojure.core/= (clojure.core/count input__36431) 2)
            (clojure.core/let
             [input__36431_nth_0__
              (clojure.core/nth input__36431 0)
              input__36431_nth_1__
              (clojure.core/nth input__36431 1)]
             (if
              (clojure.core/seq? input__36431_nth_0__)
              (clojure.core/let
               [input__36431_nth_0___l__
                (clojure.core/take 1 input__36431_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__36431_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__36431_nth_0___r__
                  (clojure.core/drop 1 input__36431_nth_0__)]
                 (clojure.core/let
                  [input__36431_nth_0___l___nth_0__
                   (clojure.core/nth input__36431_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__36431_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__36942
                     (clojure.core/namespace
                      input__36431_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__36432 X__36942]
                     (clojure.core/let
                      [X__36944
                       (clojure.core/name
                        input__36431_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__36944
                       ("with")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__36932 input__36431_nth_1__ ?__36432)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__37476)
                         (clojure.core/let
                          [[?__36432] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__36431)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__36431)
                             2)
                            (clojure.core/let
                             [input__36431_nth_0__
                              (clojure.core/nth input__36431 0)
                              input__36431_nth_1__
                              (clojure.core/nth input__36431 1)]
                             (if
                              (clojure.core/seq? input__36431_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__36431_nth_0__)
                                3)
                               (clojure.core/let
                                [input__36431_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__36431_nth_0__
                                  1)
                                 input__36431_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__36431_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__36431_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__36431_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__36431_nth_0__]
                                   (clojure.core/let
                                    [?env input__36431_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__9229__auto__
                                          (CATA__FN__36481
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
                                         (CATA__FN__36481
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
                               (state__37476))
                              (state__37476)))
                            (state__37476))
                           (state__37476)))))
                       (state__37476)))))
                   (state__37476))))
                (state__37476)))
              (state__37476)))
            (state__37476))
           (state__37476))))
        (state__37476
         []
         (clojure.core/letfn
          [(def__36966
            [arg__36989 ?__36433]
            (clojure.core/letfn
             [(state__37598
               []
               (clojure.core/let
                [x__36990 "meander.zeta"]
                (if
                 (clojure.core/= ?__36433 x__36990)
                 [?__36433]
                 (state__37599))))
              (state__37599
               []
               (if
                (clojure.core/map? arg__36989)
                (clojure.core/let
                 [VAL__36991 (.valAt arg__36989 :aliases)]
                 (if
                  (clojure.core/map? VAL__36991)
                  (clojure.core/let
                   [X__36993 (clojure.core/set VAL__36991)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__36993))
                    (clojure.core/loop
                     [search_space__37600 (clojure.core/seq X__36993)]
                     (if
                      (clojure.core/seq search_space__37600)
                      (clojure.core/let
                       [elem__36994
                        (clojure.core/first search_space__37600)
                        result__37601
                        (clojure.core/let
                         [elem__36994_nth_0__
                          (clojure.core/nth elem__36994 0)
                          elem__36994_nth_1__
                          (clojure.core/nth elem__36994 1)]
                         (if
                          (clojure.core/symbol? elem__36994_nth_0__)
                          (clojure.core/let
                           [X__36996
                            (clojure.core/name elem__36994_nth_0__)]
                           (if
                            (clojure.core/= ?__36433 X__36996)
                            (if
                             (clojure.core/symbol? elem__36994_nth_1__)
                             (clojure.core/let
                              [X__36998
                               (clojure.core/name elem__36994_nth_1__)]
                              (clojure.core/case
                               X__36998
                               ("meander.zeta")
                               [?__36433]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__37601)
                        (recur (clojure.core/next search_space__37600))
                        result__37601))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__37598)))]
          (if
           (clojure.core/vector? input__36431)
           (if
            (clojure.core/= (clojure.core/count input__36431) 2)
            (clojure.core/let
             [input__36431_nth_0__
              (clojure.core/nth input__36431 0)
              input__36431_nth_1__
              (clojure.core/nth input__36431 1)]
             (if
              (clojure.core/seq? input__36431_nth_0__)
              (clojure.core/let
               [input__36431_nth_0___l__
                (clojure.core/take 1 input__36431_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__36431_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__36431_nth_0___r__
                  (clojure.core/drop 1 input__36431_nth_0__)]
                 (clojure.core/let
                  [input__36431_nth_0___l___nth_0__
                   (clojure.core/nth input__36431_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__36431_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__36976
                     (clojure.core/namespace
                      input__36431_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__36433 X__36976]
                     (clojure.core/let
                      [X__36978
                       (clojure.core/name
                        input__36431_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__36978
                       ("apply")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__36966 input__36431_nth_1__ ?__36433)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__37477)
                         (clojure.core/let
                          [[?__36433] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__36431)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__36431)
                             2)
                            (clojure.core/let
                             [input__36431_nth_0__
                              (clojure.core/nth input__36431 0)
                              input__36431_nth_1__
                              (clojure.core/nth input__36431 1)]
                             (if
                              (clojure.core/seq? input__36431_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__36431_nth_0__)
                                3)
                               (clojure.core/let
                                [input__36431_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__36431_nth_0__
                                  1)
                                 input__36431_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__36431_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__36431_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__36431_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__36431_nth_0__]
                                   (clojure.core/let
                                    [?env input__36431_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__36481
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
                               (state__37477))
                              (state__37477)))
                            (state__37477))
                           (state__37477)))))
                       (state__37477)))))
                   (state__37477))))
                (state__37477)))
              (state__37477)))
            (state__37477))
           (state__37477))))
        (state__37477
         []
         (clojure.core/letfn
          [(def__37000
            [arg__37023 ?__36434]
            (clojure.core/letfn
             [(state__37603
               []
               (clojure.core/let
                [x__37024 "meander.zeta"]
                (if
                 (clojure.core/= ?__36434 x__37024)
                 [?__36434]
                 (state__37604))))
              (state__37604
               []
               (if
                (clojure.core/map? arg__37023)
                (clojure.core/let
                 [VAL__37025 (.valAt arg__37023 :aliases)]
                 (if
                  (clojure.core/map? VAL__37025)
                  (clojure.core/let
                   [X__37027 (clojure.core/set VAL__37025)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__37027))
                    (clojure.core/loop
                     [search_space__37605 (clojure.core/seq X__37027)]
                     (if
                      (clojure.core/seq search_space__37605)
                      (clojure.core/let
                       [elem__37028
                        (clojure.core/first search_space__37605)
                        result__37606
                        (clojure.core/let
                         [elem__37028_nth_0__
                          (clojure.core/nth elem__37028 0)
                          elem__37028_nth_1__
                          (clojure.core/nth elem__37028 1)]
                         (if
                          (clojure.core/symbol? elem__37028_nth_0__)
                          (clojure.core/let
                           [X__37030
                            (clojure.core/name elem__37028_nth_0__)]
                           (if
                            (clojure.core/= ?__36434 X__37030)
                            (if
                             (clojure.core/symbol? elem__37028_nth_1__)
                             (clojure.core/let
                              [X__37032
                               (clojure.core/name elem__37028_nth_1__)]
                              (clojure.core/case
                               X__37032
                               ("meander.zeta")
                               [?__36434]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__37606)
                        (recur (clojure.core/next search_space__37605))
                        result__37606))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__37603)))]
          (if
           (clojure.core/vector? input__36431)
           (if
            (clojure.core/= (clojure.core/count input__36431) 2)
            (clojure.core/let
             [input__36431_nth_0__
              (clojure.core/nth input__36431 0)
              input__36431_nth_1__
              (clojure.core/nth input__36431 1)]
             (if
              (clojure.core/seq? input__36431_nth_0__)
              (clojure.core/let
               [input__36431_nth_0___l__
                (clojure.core/take 1 input__36431_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__36431_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__36431_nth_0___r__
                  (clojure.core/drop 1 input__36431_nth_0__)]
                 (clojure.core/let
                  [input__36431_nth_0___l___nth_0__
                   (clojure.core/nth input__36431_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__36431_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__37010
                     (clojure.core/namespace
                      input__36431_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__36434 X__37010]
                     (clojure.core/let
                      [X__37012
                       (clojure.core/name
                        input__36431_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__37012
                       ("and")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__37000 input__36431_nth_1__ ?__36434)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__37478)
                         (clojure.core/let
                          [[?__36434] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__36431)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__36431)
                             2)
                            (clojure.core/let
                             [input__36431_nth_0__
                              (clojure.core/nth input__36431 0)
                              input__36431_nth_1__
                              (clojure.core/nth input__36431 1)]
                             (if
                              (clojure.core/seq? input__36431_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__36431_nth_0__)
                                3)
                               (clojure.core/let
                                [input__36431_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__36431_nth_0__
                                  1)
                                 input__36431_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__36431_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__36431_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right input__36431_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__36431_nth_0__]
                                   (clojure.core/let
                                    [?env input__36431_nth_1__]
                                    (try
                                     [{:tag :and,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__36481
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
                                         (CATA__FN__36481
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
                               (state__37478))
                              (state__37478)))
                            (state__37478))
                           (state__37478)))))
                       (state__37478)))))
                   (state__37478))))
                (state__37478)))
              (state__37478)))
            (state__37478))
           (state__37478))))
        (state__37478
         []
         (clojure.core/letfn
          [(def__37034
            [arg__37057 ?__36435]
            (clojure.core/letfn
             [(state__37608
               []
               (clojure.core/let
                [x__37058 "meander.zeta"]
                (if
                 (clojure.core/= ?__36435 x__37058)
                 [?__36435]
                 (state__37609))))
              (state__37609
               []
               (if
                (clojure.core/map? arg__37057)
                (clojure.core/let
                 [VAL__37059 (.valAt arg__37057 :aliases)]
                 (if
                  (clojure.core/map? VAL__37059)
                  (clojure.core/let
                   [X__37061 (clojure.core/set VAL__37059)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__37061))
                    (clojure.core/loop
                     [search_space__37610 (clojure.core/seq X__37061)]
                     (if
                      (clojure.core/seq search_space__37610)
                      (clojure.core/let
                       [elem__37062
                        (clojure.core/first search_space__37610)
                        result__37611
                        (clojure.core/let
                         [elem__37062_nth_0__
                          (clojure.core/nth elem__37062 0)
                          elem__37062_nth_1__
                          (clojure.core/nth elem__37062 1)]
                         (if
                          (clojure.core/symbol? elem__37062_nth_0__)
                          (clojure.core/let
                           [X__37064
                            (clojure.core/name elem__37062_nth_0__)]
                           (if
                            (clojure.core/= ?__36435 X__37064)
                            (if
                             (clojure.core/symbol? elem__37062_nth_1__)
                             (clojure.core/let
                              [X__37066
                               (clojure.core/name elem__37062_nth_1__)]
                              (clojure.core/case
                               X__37066
                               ("meander.zeta")
                               [?__36435]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__37611)
                        (recur (clojure.core/next search_space__37610))
                        result__37611))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__37608)))]
          (if
           (clojure.core/vector? input__36431)
           (if
            (clojure.core/= (clojure.core/count input__36431) 2)
            (clojure.core/let
             [input__36431_nth_0__
              (clojure.core/nth input__36431 0)
              input__36431_nth_1__
              (clojure.core/nth input__36431 1)]
             (if
              (clojure.core/seq? input__36431_nth_0__)
              (clojure.core/let
               [input__36431_nth_0___l__
                (clojure.core/take 1 input__36431_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__36431_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__36431_nth_0___r__
                  (clojure.core/drop 1 input__36431_nth_0__)]
                 (clojure.core/let
                  [input__36431_nth_0___l___nth_0__
                   (clojure.core/nth input__36431_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__36431_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__37044
                     (clojure.core/namespace
                      input__36431_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__36435 X__37044]
                     (clojure.core/let
                      [X__37046
                       (clojure.core/name
                        input__36431_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__37046
                       ("cata")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__37034 input__36431_nth_1__ ?__36435)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__37479)
                         (clojure.core/let
                          [[?__36435] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__36431)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__36431)
                             2)
                            (clojure.core/let
                             [input__36431_nth_0__
                              (clojure.core/nth input__36431 0)
                              input__36431_nth_1__
                              (clojure.core/nth input__36431 1)]
                             (if
                              (clojure.core/seq? input__36431_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__36431_nth_0__)
                                2)
                               (clojure.core/let
                                [input__36431_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__36431_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__36431_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__36431_nth_0__]
                                  (clojure.core/let
                                   [?env input__36431_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9229__auto__
                                        (CATA__FN__36481
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
                               (state__37479))
                              (state__37479)))
                            (state__37479))
                           (state__37479)))))
                       (state__37479)))))
                   (state__37479))))
                (state__37479)))
              (state__37479)))
            (state__37479))
           (state__37479))))
        (state__37479
         []
         (clojure.core/letfn
          [(def__37068
            [arg__37091 ?__36436]
            (clojure.core/letfn
             [(state__37613
               []
               (clojure.core/let
                [x__37092 "meander.zeta"]
                (if
                 (clojure.core/= ?__36436 x__37092)
                 [?__36436]
                 (state__37614))))
              (state__37614
               []
               (if
                (clojure.core/map? arg__37091)
                (clojure.core/let
                 [VAL__37093 (.valAt arg__37091 :aliases)]
                 (if
                  (clojure.core/map? VAL__37093)
                  (clojure.core/let
                   [X__37095 (clojure.core/set VAL__37093)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__37095))
                    (clojure.core/loop
                     [search_space__37615 (clojure.core/seq X__37095)]
                     (if
                      (clojure.core/seq search_space__37615)
                      (clojure.core/let
                       [elem__37096
                        (clojure.core/first search_space__37615)
                        result__37616
                        (clojure.core/let
                         [elem__37096_nth_0__
                          (clojure.core/nth elem__37096 0)
                          elem__37096_nth_1__
                          (clojure.core/nth elem__37096 1)]
                         (if
                          (clojure.core/symbol? elem__37096_nth_0__)
                          (clojure.core/let
                           [X__37098
                            (clojure.core/name elem__37096_nth_0__)]
                           (if
                            (clojure.core/= ?__36436 X__37098)
                            (if
                             (clojure.core/symbol? elem__37096_nth_1__)
                             (clojure.core/let
                              [X__37100
                               (clojure.core/name elem__37096_nth_1__)]
                              (clojure.core/case
                               X__37100
                               ("meander.zeta")
                               [?__36436]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__37616)
                        (recur (clojure.core/next search_space__37615))
                        result__37616))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__37613)))]
          (if
           (clojure.core/vector? input__36431)
           (if
            (clojure.core/= (clojure.core/count input__36431) 2)
            (clojure.core/let
             [input__36431_nth_0__
              (clojure.core/nth input__36431 0)
              input__36431_nth_1__
              (clojure.core/nth input__36431 1)]
             (if
              (clojure.core/seq? input__36431_nth_0__)
              (clojure.core/let
               [input__36431_nth_0___l__
                (clojure.core/take 1 input__36431_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__36431_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__36431_nth_0___r__
                  (clojure.core/drop 1 input__36431_nth_0__)]
                 (clojure.core/let
                  [input__36431_nth_0___l___nth_0__
                   (clojure.core/nth input__36431_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__36431_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__37078
                     (clojure.core/namespace
                      input__36431_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__36436 X__37078]
                     (clojure.core/let
                      [X__37080
                       (clojure.core/name
                        input__36431_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__37080
                       ("fold")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__37068 input__36431_nth_1__ ?__36436)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__37480)
                         (clojure.core/let
                          [[?__36436] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__36431)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__36431)
                             2)
                            (clojure.core/let
                             [input__36431_nth_0__
                              (clojure.core/nth input__36431 0)
                              input__36431_nth_1__
                              (clojure.core/nth input__36431 1)]
                             (if
                              (clojure.core/seq? input__36431_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__36431_nth_0__)
                                4)
                               (clojure.core/let
                                [input__36431_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__36431_nth_0__
                                  1)
                                 input__36431_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__36431_nth_0__
                                  2)
                                 input__36431_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__36431_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__36431_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__36431_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__36431_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__36431_nth_0__]
                                    (clojure.core/let
                                     [?env input__36431_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__36481
                                          ['meander.dev.parse.zeta/fold-args
                                           (clojure.core/let
                                            [CATA_RESULT__9229__auto__
                                             (CATA__FN__36481
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
                                             (CATA__FN__36481
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
                               (state__37480))
                              (state__37480)))
                            (state__37480))
                           (state__37480)))))
                       (state__37480)))))
                   (state__37480))))
                (state__37480)))
              (state__37480)))
            (state__37480))
           (state__37480))))
        (state__37480
         []
         (if
          (clojure.core/vector? input__36431)
          (if
           (clojure.core/= (clojure.core/count input__36431) 5)
           (clojure.core/let
            [input__36431_nth_0__
             (clojure.core/nth input__36431 0)
             input__36431_nth_1__
             (clojure.core/nth input__36431 1)
             input__36431_nth_2__
             (clojure.core/nth input__36431 2)
             input__36431_nth_3__
             (clojure.core/nth input__36431 3)
             input__36431_nth_4__
             (clojure.core/nth input__36431 4)]
            (if
             (clojure.core/=
              input__36431_nth_0__
              'meander.dev.parse.zeta/fold-args)
             (if
              (clojure.core/map? input__36431_nth_1__)
              (clojure.core/let
               [VAL__37103 (.valAt input__36431_nth_1__ :tag)]
               (clojure.core/case
                VAL__37103
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__36431_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__36431_nth_2__]
                  (clojure.core/let
                   [?fold-function input__36431_nth_3__]
                   (clojure.core/let
                    [?form input__36431_nth_4__]
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
                (state__37481)))
              (state__37481))
             (state__37481)))
           (state__37481))
          (state__37481)))
        (state__37481
         []
         (clojure.core/letfn
          [(def__37105
            [arg__37128 ?__36437]
            (clojure.core/letfn
             [(state__37618
               []
               (clojure.core/let
                [x__37129 "meander.zeta"]
                (if
                 (clojure.core/= ?__36437 x__37129)
                 [?__36437]
                 (state__37619))))
              (state__37619
               []
               (if
                (clojure.core/map? arg__37128)
                (clojure.core/let
                 [VAL__37130 (.valAt arg__37128 :aliases)]
                 (if
                  (clojure.core/map? VAL__37130)
                  (clojure.core/let
                   [X__37132 (clojure.core/set VAL__37130)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__37132))
                    (clojure.core/loop
                     [search_space__37620 (clojure.core/seq X__37132)]
                     (if
                      (clojure.core/seq search_space__37620)
                      (clojure.core/let
                       [elem__37133
                        (clojure.core/first search_space__37620)
                        result__37621
                        (clojure.core/let
                         [elem__37133_nth_0__
                          (clojure.core/nth elem__37133 0)
                          elem__37133_nth_1__
                          (clojure.core/nth elem__37133 1)]
                         (if
                          (clojure.core/symbol? elem__37133_nth_0__)
                          (clojure.core/let
                           [X__37135
                            (clojure.core/name elem__37133_nth_0__)]
                           (if
                            (clojure.core/= ?__36437 X__37135)
                            (if
                             (clojure.core/symbol? elem__37133_nth_1__)
                             (clojure.core/let
                              [X__37137
                               (clojure.core/name elem__37133_nth_1__)]
                              (clojure.core/case
                               X__37137
                               ("meander.zeta")
                               [?__36437]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__37621)
                        (recur (clojure.core/next search_space__37620))
                        result__37621))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__37618)))]
          (if
           (clojure.core/vector? input__36431)
           (if
            (clojure.core/= (clojure.core/count input__36431) 2)
            (clojure.core/let
             [input__36431_nth_0__
              (clojure.core/nth input__36431 0)
              input__36431_nth_1__
              (clojure.core/nth input__36431 1)]
             (if
              (clojure.core/seq? input__36431_nth_0__)
              (clojure.core/let
               [input__36431_nth_0___l__
                (clojure.core/take 1 input__36431_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__36431_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__36431_nth_0___r__
                  (clojure.core/drop 1 input__36431_nth_0__)]
                 (clojure.core/let
                  [input__36431_nth_0___l___nth_0__
                   (clojure.core/nth input__36431_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__36431_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__37115
                     (clojure.core/namespace
                      input__36431_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__36437 X__37115]
                     (clojure.core/let
                      [X__37117
                       (clojure.core/name
                        input__36431_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__37117
                       ("let")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__37105 input__36431_nth_1__ ?__36437)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__37482)
                         (clojure.core/let
                          [[?__36437] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__36431)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__36431)
                             2)
                            (clojure.core/let
                             [input__36431_nth_0__
                              (clojure.core/nth input__36431 0)
                              input__36431_nth_1__
                              (clojure.core/nth input__36431 1)]
                             (if
                              (clojure.core/seq? input__36431_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__36431_nth_0__)
                                3)
                               (clojure.core/let
                                [input__36431_nth_0___nth_0__
                                 (clojure.core/nth
                                  input__36431_nth_0__
                                  0)
                                 input__36431_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__36431_nth_0__
                                  1)
                                 input__36431_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__36431_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__36431_nth_0___nth_0__]
                                 (clojure.core/let
                                  [?expression
                                   input__36431_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?next input__36431_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?form input__36431_nth_0__]
                                    (clojure.core/let
                                     [?env input__36431_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__9229__auto__
                                          (CATA__FN__36481
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
                                          (CATA__FN__36481
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
                               (state__37482))
                              (state__37482)))
                            (state__37482))
                           (state__37482)))))
                       (state__37482)))))
                   (state__37482))))
                (state__37482)))
              (state__37482)))
            (state__37482))
           (state__37482))))
        (state__37482
         []
         (clojure.core/letfn
          [(def__37139
            [arg__37162 ?__36438]
            (clojure.core/letfn
             [(state__37623
               []
               (clojure.core/let
                [x__37163 "meander.zeta"]
                (if
                 (clojure.core/= ?__36438 x__37163)
                 [?__36438]
                 (state__37624))))
              (state__37624
               []
               (if
                (clojure.core/map? arg__37162)
                (clojure.core/let
                 [VAL__37164 (.valAt arg__37162 :aliases)]
                 (if
                  (clojure.core/map? VAL__37164)
                  (clojure.core/let
                   [X__37166 (clojure.core/set VAL__37164)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__37166))
                    (clojure.core/loop
                     [search_space__37625 (clojure.core/seq X__37166)]
                     (if
                      (clojure.core/seq search_space__37625)
                      (clojure.core/let
                       [elem__37167
                        (clojure.core/first search_space__37625)
                        result__37626
                        (clojure.core/let
                         [elem__37167_nth_0__
                          (clojure.core/nth elem__37167 0)
                          elem__37167_nth_1__
                          (clojure.core/nth elem__37167 1)]
                         (if
                          (clojure.core/symbol? elem__37167_nth_0__)
                          (clojure.core/let
                           [X__37169
                            (clojure.core/name elem__37167_nth_0__)]
                           (if
                            (clojure.core/= ?__36438 X__37169)
                            (if
                             (clojure.core/symbol? elem__37167_nth_1__)
                             (clojure.core/let
                              [X__37171
                               (clojure.core/name elem__37167_nth_1__)]
                              (clojure.core/case
                               X__37171
                               ("meander.zeta")
                               [?__36438]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__37626)
                        (recur (clojure.core/next search_space__37625))
                        result__37626))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__37623)))]
          (if
           (clojure.core/vector? input__36431)
           (if
            (clojure.core/= (clojure.core/count input__36431) 2)
            (clojure.core/let
             [input__36431_nth_0__
              (clojure.core/nth input__36431 0)
              input__36431_nth_1__
              (clojure.core/nth input__36431 1)]
             (if
              (clojure.core/seq? input__36431_nth_0__)
              (clojure.core/let
               [input__36431_nth_0___l__
                (clojure.core/take 1 input__36431_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__36431_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__36431_nth_0___r__
                  (clojure.core/drop 1 input__36431_nth_0__)]
                 (clojure.core/let
                  [input__36431_nth_0___l___nth_0__
                   (clojure.core/nth input__36431_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__36431_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__37149
                     (clojure.core/namespace
                      input__36431_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__36438 X__37149]
                     (clojure.core/let
                      [X__37151
                       (clojure.core/name
                        input__36431_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__37151
                       ("not")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__37139 input__36431_nth_1__ ?__36438)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__37483)
                         (clojure.core/let
                          [[?__36438] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__36431)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__36431)
                             2)
                            (clojure.core/let
                             [input__36431_nth_0__
                              (clojure.core/nth input__36431 0)
                              input__36431_nth_1__
                              (clojure.core/nth input__36431 1)]
                             (if
                              (clojure.core/seq? input__36431_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__36431_nth_0__)
                                2)
                               (clojure.core/let
                                [input__36431_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__36431_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__36431_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__36431_nth_0__]
                                  (clojure.core/let
                                   [?env input__36431_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9229__auto__
                                        (CATA__FN__36481
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
                               (state__37483))
                              (state__37483)))
                            (state__37483))
                           (state__37483)))))
                       (state__37483)))))
                   (state__37483))))
                (state__37483)))
              (state__37483)))
            (state__37483))
           (state__37483))))
        (state__37483
         []
         (clojure.core/letfn
          [(def__37173
            [arg__37196 ?__36439]
            (clojure.core/letfn
             [(state__37628
               []
               (clojure.core/let
                [x__37197 "meander.zeta"]
                (if
                 (clojure.core/= ?__36439 x__37197)
                 [?__36439]
                 (state__37629))))
              (state__37629
               []
               (if
                (clojure.core/map? arg__37196)
                (clojure.core/let
                 [VAL__37198 (.valAt arg__37196 :aliases)]
                 (if
                  (clojure.core/map? VAL__37198)
                  (clojure.core/let
                   [X__37200 (clojure.core/set VAL__37198)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__37200))
                    (clojure.core/loop
                     [search_space__37630 (clojure.core/seq X__37200)]
                     (if
                      (clojure.core/seq search_space__37630)
                      (clojure.core/let
                       [elem__37201
                        (clojure.core/first search_space__37630)
                        result__37631
                        (clojure.core/let
                         [elem__37201_nth_0__
                          (clojure.core/nth elem__37201 0)
                          elem__37201_nth_1__
                          (clojure.core/nth elem__37201 1)]
                         (if
                          (clojure.core/symbol? elem__37201_nth_0__)
                          (clojure.core/let
                           [X__37203
                            (clojure.core/name elem__37201_nth_0__)]
                           (if
                            (clojure.core/= ?__36439 X__37203)
                            (if
                             (clojure.core/symbol? elem__37201_nth_1__)
                             (clojure.core/let
                              [X__37205
                               (clojure.core/name elem__37201_nth_1__)]
                              (clojure.core/case
                               X__37205
                               ("meander.zeta")
                               [?__36439]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__37631)
                        (recur (clojure.core/next search_space__37630))
                        result__37631))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__37628)))]
          (if
           (clojure.core/vector? input__36431)
           (if
            (clojure.core/= (clojure.core/count input__36431) 2)
            (clojure.core/let
             [input__36431_nth_0__
              (clojure.core/nth input__36431 0)
              input__36431_nth_1__
              (clojure.core/nth input__36431 1)]
             (if
              (clojure.core/seq? input__36431_nth_0__)
              (clojure.core/let
               [input__36431_nth_0___l__
                (clojure.core/take 1 input__36431_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__36431_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__36431_nth_0___r__
                  (clojure.core/drop 1 input__36431_nth_0__)]
                 (clojure.core/let
                  [input__36431_nth_0___l___nth_0__
                   (clojure.core/nth input__36431_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__36431_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__37183
                     (clojure.core/namespace
                      input__36431_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__36439 X__37183]
                     (clojure.core/let
                      [X__37185
                       (clojure.core/name
                        input__36431_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__37185
                       ("or")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__37173 input__36431_nth_1__ ?__36439)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__37484)
                         (clojure.core/let
                          [[?__36439] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__36431)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__36431)
                             2)
                            (clojure.core/let
                             [input__36431_nth_0__
                              (clojure.core/nth input__36431 0)
                              input__36431_nth_1__
                              (clojure.core/nth input__36431 1)]
                             (if
                              (clojure.core/seq? input__36431_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__36431_nth_0__)
                                3)
                               (clojure.core/let
                                [input__36431_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__36431_nth_0__
                                  1)
                                 input__36431_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__36431_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__36431_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right input__36431_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__36431_nth_0__]
                                   (clojure.core/let
                                    [?env input__36431_nth_1__]
                                    (try
                                     [{:tag :or,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__36481
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
                                         (CATA__FN__36481
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
                               (state__37484))
                              (state__37484)))
                            (state__37484))
                           (state__37484)))))
                       (state__37484)))))
                   (state__37484))))
                (state__37484)))
              (state__37484)))
            (state__37484))
           (state__37484))))
        (state__37484
         []
         (clojure.core/letfn
          [(def__37207
            [arg__37230 ?__36440]
            (clojure.core/letfn
             [(state__37633
               []
               (clojure.core/let
                [x__37231 "meander.zeta"]
                (if
                 (clojure.core/= ?__36440 x__37231)
                 [?__36440]
                 (state__37634))))
              (state__37634
               []
               (if
                (clojure.core/map? arg__37230)
                (clojure.core/let
                 [VAL__37232 (.valAt arg__37230 :aliases)]
                 (if
                  (clojure.core/map? VAL__37232)
                  (clojure.core/let
                   [X__37234 (clojure.core/set VAL__37232)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__37234))
                    (clojure.core/loop
                     [search_space__37635 (clojure.core/seq X__37234)]
                     (if
                      (clojure.core/seq search_space__37635)
                      (clojure.core/let
                       [elem__37235
                        (clojure.core/first search_space__37635)
                        result__37636
                        (clojure.core/let
                         [elem__37235_nth_0__
                          (clojure.core/nth elem__37235 0)
                          elem__37235_nth_1__
                          (clojure.core/nth elem__37235 1)]
                         (if
                          (clojure.core/symbol? elem__37235_nth_0__)
                          (clojure.core/let
                           [X__37237
                            (clojure.core/name elem__37235_nth_0__)]
                           (if
                            (clojure.core/= ?__36440 X__37237)
                            (if
                             (clojure.core/symbol? elem__37235_nth_1__)
                             (clojure.core/let
                              [X__37239
                               (clojure.core/name elem__37235_nth_1__)]
                              (clojure.core/case
                               X__37239
                               ("meander.zeta")
                               [?__36440]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__37636)
                        (recur (clojure.core/next search_space__37635))
                        result__37636))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__37633)))]
          (if
           (clojure.core/vector? input__36431)
           (if
            (clojure.core/= (clojure.core/count input__36431) 2)
            (clojure.core/let
             [input__36431_nth_0__
              (clojure.core/nth input__36431 0)
              input__36431_nth_1__
              (clojure.core/nth input__36431 1)]
             (if
              (clojure.core/seq? input__36431_nth_0__)
              (clojure.core/let
               [input__36431_nth_0___l__
                (clojure.core/take 1 input__36431_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__36431_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__36431_nth_0___r__
                  (clojure.core/drop 1 input__36431_nth_0__)]
                 (clojure.core/let
                  [input__36431_nth_0___l___nth_0__
                   (clojure.core/nth input__36431_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__36431_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__37217
                     (clojure.core/namespace
                      input__36431_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__36440 X__37217]
                     (clojure.core/let
                      [X__37219
                       (clojure.core/name
                        input__36431_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__37219
                       ("re")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__37207 input__36431_nth_1__ ?__36440)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__37485)
                         (clojure.core/let
                          [[?__36440] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__36431)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__36431)
                             2)
                            (clojure.core/let
                             [input__36431_nth_0__
                              (clojure.core/nth input__36431 0)
                              input__36431_nth_1__
                              (clojure.core/nth input__36431 1)]
                             (if
                              (clojure.core/seq? input__36431_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__36431_nth_0__)
                                2)
                               (clojure.core/let
                                [input__36431_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__36431_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?regex input__36431_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__36431_nth_0__]
                                  (clojure.core/let
                                   [?env input__36431_nth_1__]
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
                               (state__37485))
                              (state__37485)))
                            (state__37485))
                           (state__37485)))))
                       (state__37485)))))
                   (state__37485))))
                (state__37485)))
              (state__37485)))
            (state__37485))
           (state__37485))))
        (state__37485
         []
         (clojure.core/letfn
          [(def__37241
            [arg__37264 ?__36441]
            (clojure.core/letfn
             [(state__37638
               []
               (clojure.core/let
                [x__37265 "meander.zeta"]
                (if
                 (clojure.core/= ?__36441 x__37265)
                 [?__36441]
                 (state__37639))))
              (state__37639
               []
               (if
                (clojure.core/map? arg__37264)
                (clojure.core/let
                 [VAL__37266 (.valAt arg__37264 :aliases)]
                 (if
                  (clojure.core/map? VAL__37266)
                  (clojure.core/let
                   [X__37268 (clojure.core/set VAL__37266)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__37268))
                    (clojure.core/loop
                     [search_space__37640 (clojure.core/seq X__37268)]
                     (if
                      (clojure.core/seq search_space__37640)
                      (clojure.core/let
                       [elem__37269
                        (clojure.core/first search_space__37640)
                        result__37641
                        (clojure.core/let
                         [elem__37269_nth_0__
                          (clojure.core/nth elem__37269 0)
                          elem__37269_nth_1__
                          (clojure.core/nth elem__37269 1)]
                         (if
                          (clojure.core/symbol? elem__37269_nth_0__)
                          (clojure.core/let
                           [X__37271
                            (clojure.core/name elem__37269_nth_0__)]
                           (if
                            (clojure.core/= ?__36441 X__37271)
                            (if
                             (clojure.core/symbol? elem__37269_nth_1__)
                             (clojure.core/let
                              [X__37273
                               (clojure.core/name elem__37269_nth_1__)]
                              (clojure.core/case
                               X__37273
                               ("meander.zeta")
                               [?__36441]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__37641)
                        (recur (clojure.core/next search_space__37640))
                        result__37641))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__37638)))]
          (if
           (clojure.core/vector? input__36431)
           (if
            (clojure.core/= (clojure.core/count input__36431) 2)
            (clojure.core/let
             [input__36431_nth_0__
              (clojure.core/nth input__36431 0)
              input__36431_nth_1__
              (clojure.core/nth input__36431 1)]
             (if
              (clojure.core/seq? input__36431_nth_0__)
              (clojure.core/let
               [input__36431_nth_0___l__
                (clojure.core/take 1 input__36431_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__36431_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__36431_nth_0___r__
                  (clojure.core/drop 1 input__36431_nth_0__)]
                 (clojure.core/let
                  [input__36431_nth_0___l___nth_0__
                   (clojure.core/nth input__36431_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__36431_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__37251
                     (clojure.core/namespace
                      input__36431_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__36441 X__37251]
                     (clojure.core/let
                      [X__37253
                       (clojure.core/name
                        input__36431_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__37253
                       ("re")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__37241 input__36431_nth_1__ ?__36441)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__37486)
                         (clojure.core/let
                          [[?__36441] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__36431)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__36431)
                             2)
                            (clojure.core/let
                             [input__36431_nth_0__
                              (clojure.core/nth input__36431 0)
                              input__36431_nth_1__
                              (clojure.core/nth input__36431 1)]
                             (if
                              (clojure.core/seq? input__36431_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__36431_nth_0__)
                                3)
                               (clojure.core/let
                                [input__36431_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__36431_nth_0__
                                  1)
                                 input__36431_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__36431_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?regex input__36431_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?capture
                                   input__36431_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__36431_nth_0__]
                                   (clojure.core/let
                                    [?env input__36431_nth_1__]
                                    (try
                                     [{:tag :regex,
                                       :regex ?regex,
                                       :capture
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__36481
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
                               (state__37486))
                              (state__37486)))
                            (state__37486))
                           (state__37486)))))
                       (state__37486)))))
                   (state__37486))))
                (state__37486)))
              (state__37486)))
            (state__37486))
           (state__37486))))
        (state__37486
         []
         (clojure.core/letfn
          [(def__37275
            [arg__37298 ?__36442]
            (clojure.core/letfn
             [(state__37643
               []
               (clojure.core/let
                [x__37299 "meander.zeta"]
                (if
                 (clojure.core/= ?__36442 x__37299)
                 [?__36442]
                 (state__37644))))
              (state__37644
               []
               (if
                (clojure.core/map? arg__37298)
                (clojure.core/let
                 [VAL__37300 (.valAt arg__37298 :aliases)]
                 (if
                  (clojure.core/map? VAL__37300)
                  (clojure.core/let
                   [X__37302 (clojure.core/set VAL__37300)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__37302))
                    (clojure.core/loop
                     [search_space__37645 (clojure.core/seq X__37302)]
                     (if
                      (clojure.core/seq search_space__37645)
                      (clojure.core/let
                       [elem__37303
                        (clojure.core/first search_space__37645)
                        result__37646
                        (clojure.core/let
                         [elem__37303_nth_0__
                          (clojure.core/nth elem__37303 0)
                          elem__37303_nth_1__
                          (clojure.core/nth elem__37303 1)]
                         (if
                          (clojure.core/symbol? elem__37303_nth_0__)
                          (clojure.core/let
                           [X__37305
                            (clojure.core/name elem__37303_nth_0__)]
                           (if
                            (clojure.core/= ?__36442 X__37305)
                            (if
                             (clojure.core/symbol? elem__37303_nth_1__)
                             (clojure.core/let
                              [X__37307
                               (clojure.core/name elem__37303_nth_1__)]
                              (clojure.core/case
                               X__37307
                               ("meander.zeta")
                               [?__36442]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__37646)
                        (recur (clojure.core/next search_space__37645))
                        result__37646))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__37643)))]
          (if
           (clojure.core/vector? input__36431)
           (if
            (clojure.core/= (clojure.core/count input__36431) 2)
            (clojure.core/let
             [input__36431_nth_0__
              (clojure.core/nth input__36431 0)
              input__36431_nth_1__
              (clojure.core/nth input__36431 1)]
             (if
              (clojure.core/seq? input__36431_nth_0__)
              (clojure.core/let
               [input__36431_nth_0___l__
                (clojure.core/take 1 input__36431_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__36431_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__36431_nth_0___r__
                  (clojure.core/drop 1 input__36431_nth_0__)]
                 (clojure.core/let
                  [input__36431_nth_0___l___nth_0__
                   (clojure.core/nth input__36431_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__36431_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__37285
                     (clojure.core/namespace
                      input__36431_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__36442 X__37285]
                     (clojure.core/let
                      [X__37287
                       (clojure.core/name
                        input__36431_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__37287
                       ("string")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__37275 input__36431_nth_1__ ?__36442)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__37487)
                         (clojure.core/let
                          [[?__36442] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__36431)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__36431)
                             2)
                            (clojure.core/let
                             [input__36431_nth_0__
                              (clojure.core/nth input__36431 0)
                              input__36431_nth_1__
                              (clojure.core/nth input__36431 1)]
                             (if
                              (clojure.core/seq? input__36431_nth_0__)
                              (clojure.core/let
                               [input__36431_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__36431_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__36431_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__36431_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__36431_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__36431_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__36431_nth_0__]
                                   (clojure.core/let
                                    [?env input__36431_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__36481
                                          ['meander.dev.parse.zeta/parse-string
                                           (clojure.core/into
                                            []
                                            ?sequence)
                                           ?env])]
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
                                (state__37487)))
                              (state__37487)))
                            (state__37487))
                           (state__37487)))))
                       (state__37487)))))
                   (state__37487))))
                (state__37487)))
              (state__37487)))
            (state__37487))
           (state__37487))))
        (state__37487
         []
         (clojure.core/letfn
          [(def__37309
            [arg__37332 ?__36443]
            (clojure.core/letfn
             [(state__37648
               []
               (clojure.core/let
                [x__37333 "meander.zeta"]
                (if
                 (clojure.core/= ?__36443 x__37333)
                 [?__36443]
                 (state__37649))))
              (state__37649
               []
               (if
                (clojure.core/map? arg__37332)
                (clojure.core/let
                 [VAL__37334 (.valAt arg__37332 :aliases)]
                 (if
                  (clojure.core/map? VAL__37334)
                  (clojure.core/let
                   [X__37336 (clojure.core/set VAL__37334)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__37336))
                    (clojure.core/loop
                     [search_space__37650 (clojure.core/seq X__37336)]
                     (if
                      (clojure.core/seq search_space__37650)
                      (clojure.core/let
                       [elem__37337
                        (clojure.core/first search_space__37650)
                        result__37651
                        (clojure.core/let
                         [elem__37337_nth_0__
                          (clojure.core/nth elem__37337 0)
                          elem__37337_nth_1__
                          (clojure.core/nth elem__37337 1)]
                         (if
                          (clojure.core/symbol? elem__37337_nth_0__)
                          (clojure.core/let
                           [X__37339
                            (clojure.core/name elem__37337_nth_0__)]
                           (if
                            (clojure.core/= ?__36443 X__37339)
                            (if
                             (clojure.core/symbol? elem__37337_nth_1__)
                             (clojure.core/let
                              [X__37341
                               (clojure.core/name elem__37337_nth_1__)]
                              (clojure.core/case
                               X__37341
                               ("meander.zeta")
                               [?__36443]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__37651)
                        (recur (clojure.core/next search_space__37650))
                        result__37651))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__37648)))]
          (if
           (clojure.core/vector? input__36431)
           (if
            (clojure.core/= (clojure.core/count input__36431) 2)
            (clojure.core/let
             [input__36431_nth_0__
              (clojure.core/nth input__36431 0)
              input__36431_nth_1__
              (clojure.core/nth input__36431 1)]
             (if
              (clojure.core/seq? input__36431_nth_0__)
              (clojure.core/let
               [input__36431_nth_0___l__
                (clojure.core/take 1 input__36431_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__36431_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__36431_nth_0___r__
                  (clojure.core/drop 1 input__36431_nth_0__)]
                 (clojure.core/let
                  [input__36431_nth_0___l___nth_0__
                   (clojure.core/nth input__36431_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__36431_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__37319
                     (clojure.core/namespace
                      input__36431_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__36443 X__37319]
                     (clojure.core/let
                      [X__37321
                       (clojure.core/name
                        input__36431_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__37321
                       ("symbol")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__37309 input__36431_nth_1__ ?__36443)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__37488)
                         (clojure.core/let
                          [[?__36443] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__36431)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__36431)
                             2)
                            (clojure.core/let
                             [input__36431_nth_0__
                              (clojure.core/nth input__36431 0)
                              input__36431_nth_1__
                              (clojure.core/nth input__36431 1)]
                             (if
                              (clojure.core/seq? input__36431_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__36431_nth_0__)
                                2)
                               (clojure.core/let
                                [input__36431_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__36431_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?name input__36431_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__36431_nth_0__]
                                  (clojure.core/let
                                   [?env input__36431_nth_1__]
                                   (try
                                    [{:tag :symbol,
                                      :name
                                      (clojure.core/let
                                       [CATA_RESULT__9229__auto__
                                        (CATA__FN__36481 [?name ?env])]
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
                               (state__37488))
                              (state__37488)))
                            (state__37488))
                           (state__37488)))))
                       (state__37488)))))
                   (state__37488))))
                (state__37488)))
              (state__37488)))
            (state__37488))
           (state__37488))))
        (state__37488
         []
         (clojure.core/letfn
          [(def__37343
            [arg__37366 ?__36444]
            (clojure.core/letfn
             [(state__37653
               []
               (clojure.core/let
                [x__37367 "meander.zeta"]
                (if
                 (clojure.core/= ?__36444 x__37367)
                 [?__36444]
                 (state__37654))))
              (state__37654
               []
               (if
                (clojure.core/map? arg__37366)
                (clojure.core/let
                 [VAL__37368 (.valAt arg__37366 :aliases)]
                 (if
                  (clojure.core/map? VAL__37368)
                  (clojure.core/let
                   [X__37370 (clojure.core/set VAL__37368)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__37370))
                    (clojure.core/loop
                     [search_space__37655 (clojure.core/seq X__37370)]
                     (if
                      (clojure.core/seq search_space__37655)
                      (clojure.core/let
                       [elem__37371
                        (clojure.core/first search_space__37655)
                        result__37656
                        (clojure.core/let
                         [elem__37371_nth_0__
                          (clojure.core/nth elem__37371 0)
                          elem__37371_nth_1__
                          (clojure.core/nth elem__37371 1)]
                         (if
                          (clojure.core/symbol? elem__37371_nth_0__)
                          (clojure.core/let
                           [X__37373
                            (clojure.core/name elem__37371_nth_0__)]
                           (if
                            (clojure.core/= ?__36444 X__37373)
                            (if
                             (clojure.core/symbol? elem__37371_nth_1__)
                             (clojure.core/let
                              [X__37375
                               (clojure.core/name elem__37371_nth_1__)]
                              (clojure.core/case
                               X__37375
                               ("meander.zeta")
                               [?__36444]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__37656)
                        (recur (clojure.core/next search_space__37655))
                        result__37656))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__37653)))]
          (if
           (clojure.core/vector? input__36431)
           (if
            (clojure.core/= (clojure.core/count input__36431) 2)
            (clojure.core/let
             [input__36431_nth_0__
              (clojure.core/nth input__36431 0)
              input__36431_nth_1__
              (clojure.core/nth input__36431 1)]
             (if
              (clojure.core/seq? input__36431_nth_0__)
              (clojure.core/let
               [input__36431_nth_0___l__
                (clojure.core/take 1 input__36431_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__36431_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__36431_nth_0___r__
                  (clojure.core/drop 1 input__36431_nth_0__)]
                 (clojure.core/let
                  [input__36431_nth_0___l___nth_0__
                   (clojure.core/nth input__36431_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__36431_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__37353
                     (clojure.core/namespace
                      input__36431_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__36444 X__37353]
                     (clojure.core/let
                      [X__37355
                       (clojure.core/name
                        input__36431_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__37355
                       ("symbol")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__37343 input__36431_nth_1__ ?__36444)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__37489)
                         (clojure.core/let
                          [[?__36444] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__36431)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__36431)
                             2)
                            (clojure.core/let
                             [input__36431_nth_0__
                              (clojure.core/nth input__36431 0)
                              input__36431_nth_1__
                              (clojure.core/nth input__36431 1)]
                             (if
                              (clojure.core/seq? input__36431_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__36431_nth_0__)
                                3)
                               (clojure.core/let
                                [input__36431_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__36431_nth_0__
                                  1)
                                 input__36431_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__36431_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?namespace
                                  input__36431_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?name input__36431_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__36431_nth_0__]
                                   (clojure.core/let
                                    [?env input__36431_nth_1__]
                                    (try
                                     [{:tag :symbol,
                                       :name
                                       (clojure.core/let
                                        [CATA_RESULT__9229__auto__
                                         (CATA__FN__36481
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
                                         (CATA__FN__36481
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
                               (state__37489))
                              (state__37489)))
                            (state__37489))
                           (state__37489)))))
                       (state__37489)))))
                   (state__37489))))
                (state__37489)))
              (state__37489)))
            (state__37489))
           (state__37489))))
        (state__37489
         []
         (clojure.core/letfn
          [(def__37377
            [arg__37400 ?__36445]
            (clojure.core/letfn
             [(state__37658
               []
               (clojure.core/let
                [x__37401 "meander.zeta"]
                (if
                 (clojure.core/= ?__36445 x__37401)
                 [?__36445]
                 (state__37659))))
              (state__37659
               []
               (if
                (clojure.core/map? arg__37400)
                (clojure.core/let
                 [VAL__37402 (.valAt arg__37400 :aliases)]
                 (if
                  (clojure.core/map? VAL__37402)
                  (clojure.core/let
                   [X__37404 (clojure.core/set VAL__37402)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__37404))
                    (clojure.core/loop
                     [search_space__37660 (clojure.core/seq X__37404)]
                     (if
                      (clojure.core/seq search_space__37660)
                      (clojure.core/let
                       [elem__37405
                        (clojure.core/first search_space__37660)
                        result__37661
                        (clojure.core/let
                         [elem__37405_nth_0__
                          (clojure.core/nth elem__37405 0)
                          elem__37405_nth_1__
                          (clojure.core/nth elem__37405 1)]
                         (if
                          (clojure.core/symbol? elem__37405_nth_0__)
                          (clojure.core/let
                           [X__37407
                            (clojure.core/name elem__37405_nth_0__)]
                           (if
                            (clojure.core/= ?__36445 X__37407)
                            (if
                             (clojure.core/symbol? elem__37405_nth_1__)
                             (clojure.core/let
                              [X__37409
                               (clojure.core/name elem__37405_nth_1__)]
                              (clojure.core/case
                               X__37409
                               ("meander.zeta")
                               [?__36445]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__37661)
                        (recur (clojure.core/next search_space__37660))
                        result__37661))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__37658)))]
          (if
           (clojure.core/vector? input__36431)
           (if
            (clojure.core/= (clojure.core/count input__36431) 2)
            (clojure.core/let
             [input__36431_nth_0__
              (clojure.core/nth input__36431 0)
              input__36431_nth_1__
              (clojure.core/nth input__36431 1)]
             (if
              (clojure.core/seq? input__36431_nth_0__)
              (clojure.core/let
               [input__36431_nth_0___l__
                (clojure.core/take 1 input__36431_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__36431_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__36431_nth_0___r__
                  (clojure.core/drop 1 input__36431_nth_0__)]
                 (clojure.core/let
                  [input__36431_nth_0___l___nth_0__
                   (clojure.core/nth input__36431_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__36431_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__37387
                     (clojure.core/namespace
                      input__36431_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__36445 X__37387]
                     (clojure.core/let
                      [X__37389
                       (clojure.core/name
                        input__36431_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__37389
                       ("symbol")
                       (clojure.core/let
                        [x__7926__auto__
                         (def__37377 input__36431_nth_1__ ?__36445)]
                        (if
                         (meander.runtime.zeta/fail? x__7926__auto__)
                         (state__37490)
                         (clojure.core/let
                          [[?__36445] x__7926__auto__]
                          (if
                           (clojure.core/vector? input__36431)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__36431)
                             2)
                            (clojure.core/let
                             [input__36431_nth_0__
                              (clojure.core/nth input__36431 0)
                              input__36431_nth_1__
                              (clojure.core/nth input__36431 1)]
                             (if
                              (clojure.core/seq? input__36431_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 5)
                                 input__36431_nth_0__)
                                5)
                               (clojure.core/let
                                [input__36431_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__36431_nth_0__
                                  1)
                                 input__36431_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__36431_nth_0__
                                  2)
                                 input__36431_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__36431_nth_0__
                                  3)
                                 input__36431_nth_0___nth_4__
                                 (clojure.core/nth
                                  input__36431_nth_0__
                                  4)]
                                (clojure.core/case
                                 input__36431_nth_0___nth_3__
                                 (:meander.zeta/as)
                                 (clojure.core/let
                                  [?namespace
                                   input__36431_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?name input__36431_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?pattern
                                     input__36431_nth_0___nth_4__]
                                    (clojure.core/let
                                     [?form input__36431_nth_0__]
                                     (clojure.core/let
                                      [?env input__36431_nth_1__]
                                      (try
                                       [{:tag :symbol,
                                         :name
                                         (clojure.core/let
                                          [CATA_RESULT__9229__auto__
                                           (CATA__FN__36481
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
                                           (CATA__FN__36481
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
                                           (CATA__FN__36481
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
                                 (state__37490)))
                               (state__37490))
                              (state__37490)))
                            (state__37490))
                           (state__37490)))))
                       (state__37490)))))
                   (state__37490))))
                (state__37490)))
              (state__37490)))
            (state__37490))
           (state__37490))))
        (state__37490
         []
         (if
          (clojure.core/vector? input__36431)
          (if
           (clojure.core/= (clojure.core/count input__36431) 2)
           (clojure.core/let
            [input__36431_nth_0__ (clojure.core/nth input__36431 0)]
            (clojure.core/letfn
             [(state__37663
               []
               (clojure.core/let
                [input__36431_nth_1__
                 (clojure.core/nth input__36431 1)]
                (clojure.core/letfn
                 [(state__37668
                   []
                   (if
                    (clojure.core/seq? input__36431_nth_0__)
                    (clojure.core/let
                     [?sequence input__36431_nth_0__]
                     (clojure.core/let
                      [?env input__36431_nth_1__]
                      (try
                       [{:tag :seq,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__9229__auto__
                           (CATA__FN__36481
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
                    (state__37669)))
                  (state__37669
                   []
                   (if
                    (clojure.core/map? input__36431_nth_0__)
                    (clojure.core/let
                     [?map input__36431_nth_0__]
                     (clojure.core/let
                      [?env input__36431_nth_1__]
                      (try
                       [{:tag :map,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__9229__auto__
                           (CATA__FN__36481
                            ['meander.dev.parse.zeta/parse-entries
                             ?map
                             ?env])]
                          (if
                           (meander.runtime.zeta/fail?
                            CATA_RESULT__9229__auto__)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth
                            CATA_RESULT__9229__auto__
                            0))),
                         :form ?map}]
                       (catch
                        java.lang.Exception
                        e__10169__auto__
                        (if
                         (meander.runtime.zeta/fail? e__10169__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__10169__auto__))))))
                    (state__37664)))]
                 (state__37668))))
              (state__37664
               []
               (if
                (clojure.core/symbol? input__36431_nth_0__)
                (clojure.core/let
                 [X__37419
                  (clojure.core/namespace input__36431_nth_0__)]
                 (clojure.core/let
                  [X__37421 (clojure.core/name input__36431_nth_0__)]
                  (if
                   (clojure.core/string? X__37421)
                   (clojure.core/letfn
                    [(state__37670
                      []
                      (clojure.core/let
                       [ret__37422
                        (clojure.core/re-matches #"_.*" X__37421)]
                       (if
                        (clojure.core/some? ret__37422)
                        (clojure.core/let
                         [?name ret__37422]
                         (clojure.core/let
                          [?symbol input__36431_nth_0__]
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
                        (state__37671))))
                     (state__37671
                      []
                      (clojure.core/let
                       [ret__37429
                        (clojure.core/re-matches #".+#" X__37421)]
                       (if
                        (clojure.core/some? ret__37429)
                        (clojure.core/let
                         [?name ret__37429]
                         (clojure.core/let
                          [?symbol input__36431_nth_0__]
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
                        (state__37672))))
                     (state__37672
                      []
                      (clojure.core/let
                       [ret__37436
                        (clojure.core/re-matches #"%.+" X__37421)]
                       (if
                        (clojure.core/some? ret__37436)
                        (clojure.core/let
                         [?name ret__37436]
                         (clojure.core/let
                          [?symbol input__36431_nth_0__]
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
                        (state__37673))))
                     (state__37673
                      []
                      (clojure.core/let
                       [ret__37443
                        (clojure.core/re-matches #"\*.+" X__37421)]
                       (if
                        (clojure.core/some? ret__37443)
                        (clojure.core/let
                         [?name ret__37443]
                         (clojure.core/let
                          [?symbol input__36431_nth_0__]
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
                        (state__37674))))
                     (state__37674
                      []
                      (clojure.core/let
                       [ret__37450
                        (clojure.core/re-matches #"\!.+" X__37421)]
                       (if
                        (clojure.core/some? ret__37450)
                        (clojure.core/let
                         [?name ret__37450]
                         (clojure.core/let
                          [?symbol input__36431_nth_0__]
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
                        (state__37675))))
                     (state__37675
                      []
                      (clojure.core/let
                       [ret__37457
                        (clojure.core/re-matches #"\?.+" X__37421)]
                       (if
                        (clojure.core/some? ret__37457)
                        (clojure.core/let
                         [?name ret__37457]
                         (clojure.core/let
                          [?symbol input__36431_nth_0__]
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
                        (state__37665))))]
                    (state__37670))
                   (state__37665))))
                (state__37665)))
              (state__37665
               []
               (if
                (string? input__36431_nth_0__)
                (clojure.core/let
                 [?x input__36431_nth_0__]
                 (try
                  [{:tag :literal, :type :string, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__10169__auto__
                   (if
                    (meander.runtime.zeta/fail? e__10169__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__10169__auto__)))))
                (state__37666)))
              (state__37666
               []
               (if
                (char? input__36431_nth_0__)
                (clojure.core/let
                 [?x input__36431_nth_0__]
                 (try
                  [{:tag :literal, :type :char, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__10169__auto__
                   (if
                    (meander.runtime.zeta/fail? e__10169__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__10169__auto__)))))
                (state__37667)))
              (state__37667
               []
               (clojure.core/let
                [?x input__36431_nth_0__]
                (try
                 [{:tag :literal, :form ?x}]
                 (catch
                  java.lang.Exception
                  e__10169__auto__
                  (if
                   (meander.runtime.zeta/fail? e__10169__auto__)
                   (meander.runtime.zeta/fail)
                   (throw e__10169__auto__))))))]
             (state__37663)))
           (state__37491))
          (state__37491)))
        (state__37491
         []
         (clojure.core/let
          [?x input__36431]
          (try
           [{:tag :unknown, :x ?x}]
           (catch
            java.lang.Exception
            e__10169__auto__
            (if
             (meander.runtime.zeta/fail? e__10169__auto__)
             (meander.runtime.zeta/fail)
             (throw e__10169__auto__))))))]
       (state__37469)))]
    (clojure.core/let
     [x__7926__auto__ (CATA__FN__36481 input__36431)]
     (if
      (meander.runtime.zeta/fail? x__7926__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__36483] x__7926__auto__]
       CATA_RETURN__36483))))]
  (if
   (meander.runtime.zeta/fail? ret__8106__auto__)
   nil
   ret__8106__auto__)))
