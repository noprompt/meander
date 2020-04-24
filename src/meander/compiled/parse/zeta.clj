(ns
 meander.compiled.parse.zeta
 (:require [meander.runtime.zeta] [meander.util.zeta]))
(clojure.core/defn
 parse
 [input__14393]
 (let*
  [ret__8115__auto__
   (clojure.core/letfn
    [(CATA__FN__14472
      [input__14393]
      (clojure.core/letfn
       [(state__15909
         []
         (if
          (clojure.core/vector? input__14393)
          (if
           (clojure.core/= (clojure.core/count input__14393) 3)
           (clojure.core/let
            [input__14393_nth_0__
             (clojure.core/nth input__14393 0)
             input__14393_nth_1__
             (clojure.core/nth input__14393 1)
             input__14393_nth_2__
             (clojure.core/nth input__14393 2)]
            (clojure.core/case
             input__14393_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__14393_nth_1__)
              (clojure.core/letfn
               [(state__15948
                 []
                 (clojure.core/case
                  input__14393_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__14393_nth_2__]
                   [{:tag :empty}])
                  (state__15949)))
                (state__15949
                 []
                 (clojure.core/let
                  [n__14481
                   (clojure.core/count input__14393_nth_1__)
                   m__14482
                   (clojure.core/max 0 (clojure.core/- n__14481 2))
                   input__14393_nth_1___l__
                   (clojure.core/subvec
                    input__14393_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__14393_nth_1__)
                     m__14482))
                   input__14393_nth_1___r__
                   (clojure.core/subvec input__14393_nth_1__ m__14482)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__14393_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__14393_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__14393_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__14393_nth_1___r___nth_0__
                       (clojure.core/nth input__14393_nth_1___r__ 0)
                       input__14393_nth_1___r___nth_1__
                       (clojure.core/nth input__14393_nth_1___r__ 1)]
                      (clojure.core/case
                       input__14393_nth_1___r___nth_0__
                       (:meander.zeta/as)
                       (clojure.core/let
                        [?pattern input__14393_nth_1___r___nth_1__]
                        (clojure.core/let
                         [?env input__14393_nth_2__]
                         (try
                          [(clojure.core/let
                            [!xs__counter
                             (meander.runtime.zeta/iterator !xs)]
                            {:tag :as,
                             :pattern
                             (clojure.core/let
                              [CATA_RESULT__9238__auto__
                               (CATA__FN__14472 [?pattern ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__9238__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__9238__auto__
                                0))),
                             :next
                             (clojure.core/let
                              [CATA_RESULT__9238__auto__
                               (CATA__FN__14472
                                ['meander.dev.parse.zeta/parse-sequential
                                 (clojure.core/into
                                  []
                                  (clojure.core/vec
                                   (clojure.core/iterator-seq
                                    !xs__counter)))
                                 ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__9238__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__9238__auto__
                                0)))})]
                          (catch
                           java.lang.Exception
                           e__10178__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__10178__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__10178__auto__))))))
                       (state__15910)))
                     (state__15910)))
                   (state__15910))))]
               (state__15948))
              (state__15910))
             (state__15910)))
           (state__15910))
          (state__15910)))
        (state__15910
         []
         (clojure.core/letfn
          [(def__14487
            [arg__14522 ?ns]
            (clojure.core/letfn
             [(state__15950
               []
               (clojure.core/let
                [x__14523 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__14523)
                 (clojure.core/let [?env arg__14522] [?env ?ns])
                 (state__15951))))
              (state__15951
               []
               (if
                (clojure.core/map? arg__14522)
                (clojure.core/let
                 [VAL__14524 (.valAt arg__14522 :aliases)]
                 (if
                  (clojure.core/map? VAL__14524)
                  (clojure.core/let
                   [X__14526 (clojure.core/set VAL__14524)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14526))
                    (clojure.core/loop
                     [search_space__15952 (clojure.core/seq X__14526)]
                     (if
                      (clojure.core/seq search_space__15952)
                      (clojure.core/let
                       [elem__14527
                        (clojure.core/first search_space__15952)
                        result__15953
                        (clojure.core/let
                         [elem__14527_nth_0__
                          (clojure.core/nth elem__14527 0)
                          elem__14527_nth_1__
                          (clojure.core/nth elem__14527 1)]
                         (if
                          (clojure.core/symbol? elem__14527_nth_0__)
                          (clojure.core/let
                           [X__14529
                            (clojure.core/name elem__14527_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__14529)
                            (if
                             (clojure.core/symbol? elem__14527_nth_1__)
                             (clojure.core/let
                              [X__14531
                               (clojure.core/name elem__14527_nth_1__)]
                              (clojure.core/case
                               X__14531
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__14522]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__15953)
                        (recur (clojure.core/next search_space__15952))
                        result__15953))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__15950)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 3)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)
              input__14393_nth_2__
              (clojure.core/nth input__14393 2)]
             (clojure.core/case
              input__14393_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__14393_nth_1__)
               (clojure.core/loop
                [search_space__15955
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__14393_nth_1__)]
                (if
                 (clojure.core/seq search_space__15955)
                 (clojure.core/let
                  [input__14393_nth_1___parts__
                   (clojure.core/first search_space__15955)
                   result__15956
                   (clojure.core/let
                    [input__14393_nth_1___l__
                     (clojure.core/nth input__14393_nth_1___parts__ 0)
                     input__14393_nth_1___r__
                     (clojure.core/nth input__14393_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__14393_nth_1___l__)]
                     (clojure.core/let
                      [input__14393_nth_1___r___l__
                       (clojure.core/subvec
                        input__14393_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__14393_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__14393_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__14393_nth_1___r___r__
                         (clojure.core/subvec
                          input__14393_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__14393_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__14393_nth_1___r___l__
                           0)
                          input__14393_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__14393_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__14393_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__14496
                            (clojure.core/namespace
                             input__14393_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__14496]
                            (clojure.core/let
                             [X__14498
                              (clojure.core/name
                               input__14393_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__14498)
                              (clojure.core/let
                               [ret__14499
                                (clojure.core/re-matches
                                 #"&(\d+)"
                                 X__14498)]
                               (if
                                (clojure.core/some? ret__14499)
                                (if
                                 (clojure.core/vector? ret__14499)
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count ret__14499)
                                   2)
                                  (clojure.core/let
                                   [ret__14499_nth_1__
                                    (clojure.core/nth ret__14499 1)]
                                   (clojure.core/let
                                    [?n ret__14499_nth_1__]
                                    (clojure.core/let
                                     [?pattern
                                      input__14393_nth_1___r___l___nth_1__]
                                     (clojure.core/let
                                      [?rest
                                       input__14393_nth_1___r___r__]
                                      (clojure.core/let
                                       [x__7935__auto__
                                        (def__14487
                                         input__14393_nth_2__
                                         ?ns)]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         x__7935__auto__)
                                        (meander.runtime.zeta/fail)
                                        (clojure.core/let
                                         [[?env ?ns] x__7935__auto__]
                                         (try
                                          [(clojure.core/let
                                            [!init__counter
                                             (meander.runtime.zeta/iterator
                                              !init)]
                                            (clojure.core/let
                                             [CATA_RESULT__9238__auto__
                                              (CATA__FN__14472
                                               ['meander.dev.parse.zeta/make-join
                                                (clojure.core/let
                                                 [CATA_RESULT__9238__auto__
                                                  (CATA__FN__14472
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !init__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9238__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9238__auto__
                                                   0)))
                                                (clojure.core/let
                                                 [CATA_RESULT__9238__auto__
                                                  (CATA__FN__14472
                                                   ['meander.dev.parse.zeta/make-join
                                                    {:tag :slice,
                                                     :size
                                                     (Integer. ?n),
                                                     :pattern
                                                     (clojure.core/let
                                                      [CATA_RESULT__9238__auto__
                                                       (CATA__FN__14472
                                                        [?pattern
                                                         ?env])]
                                                      (if
                                                       (meander.runtime.zeta/fail?
                                                        CATA_RESULT__9238__auto__)
                                                       (throw
                                                        (meander.runtime.zeta/fail))
                                                       (clojure.core/nth
                                                        CATA_RESULT__9238__auto__
                                                        0)))}
                                                    (clojure.core/let
                                                     [CATA_RESULT__9238__auto__
                                                      (CATA__FN__14472
                                                       ['meander.dev.parse.zeta/parse-sequential
                                                        (clojure.core/into
                                                         []
                                                         ?rest)
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__9238__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__9238__auto__
                                                       0)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9238__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9238__auto__
                                                   0)))
                                                ?env])]
                                             (if
                                              (meander.runtime.zeta/fail?
                                               CATA_RESULT__9238__auto__)
                                              (throw
                                               (meander.runtime.zeta/fail))
                                              (clojure.core/nth
                                               CATA_RESULT__9238__auto__
                                               0))))]
                                          (catch
                                           java.lang.Exception
                                           e__10178__auto__
                                           (if
                                            (meander.runtime.zeta/fail?
                                             e__10178__auto__)
                                            (meander.runtime.zeta/fail)
                                            (throw
                                             e__10178__auto__)))))))))))
                                  (meander.runtime.zeta/fail))
                                 (meander.runtime.zeta/fail))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__15956)
                   (recur (clojure.core/next search_space__15955))
                   result__15956))
                 (state__15911)))
               (state__15911))
              (state__15911)))
            (state__15911))
           (state__15911))))
        (state__15911
         []
         (clojure.core/letfn
          [(def__14544
            [arg__14576 ?ns]
            (clojure.core/letfn
             [(state__15958
               []
               (clojure.core/let
                [x__14577 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__14577)
                 (clojure.core/let [?env arg__14576] [?env ?ns])
                 (state__15959))))
              (state__15959
               []
               (if
                (clojure.core/map? arg__14576)
                (clojure.core/let
                 [VAL__14578 (.valAt arg__14576 :aliases)]
                 (if
                  (clojure.core/map? VAL__14578)
                  (clojure.core/let
                   [X__14580 (clojure.core/set VAL__14578)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14580))
                    (clojure.core/loop
                     [search_space__15960 (clojure.core/seq X__14580)]
                     (if
                      (clojure.core/seq search_space__15960)
                      (clojure.core/let
                       [elem__14581
                        (clojure.core/first search_space__15960)
                        result__15961
                        (clojure.core/let
                         [elem__14581_nth_0__
                          (clojure.core/nth elem__14581 0)
                          elem__14581_nth_1__
                          (clojure.core/nth elem__14581 1)]
                         (if
                          (clojure.core/symbol? elem__14581_nth_0__)
                          (clojure.core/let
                           [X__14583
                            (clojure.core/name elem__14581_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__14583)
                            (if
                             (clojure.core/symbol? elem__14581_nth_1__)
                             (clojure.core/let
                              [X__14585
                               (clojure.core/name elem__14581_nth_1__)]
                              (clojure.core/case
                               X__14585
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__14576]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__15961)
                        (recur (clojure.core/next search_space__15960))
                        result__15961))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__15958)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 3)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)
              input__14393_nth_2__
              (clojure.core/nth input__14393 2)]
             (clojure.core/case
              input__14393_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__14393_nth_1__)
               (clojure.core/loop
                [search_space__15963
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__14393_nth_1__)]
                (if
                 (clojure.core/seq search_space__15963)
                 (clojure.core/let
                  [input__14393_nth_1___parts__
                   (clojure.core/first search_space__15963)
                   result__15964
                   (clojure.core/let
                    [input__14393_nth_1___l__
                     (clojure.core/nth input__14393_nth_1___parts__ 0)
                     input__14393_nth_1___r__
                     (clojure.core/nth input__14393_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__14393_nth_1___l__)]
                     (clojure.core/let
                      [input__14393_nth_1___r___l__
                       (clojure.core/subvec
                        input__14393_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__14393_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__14393_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__14393_nth_1___r___r__
                         (clojure.core/subvec
                          input__14393_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__14393_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__14393_nth_1___r___l__
                           0)
                          input__14393_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__14393_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__14393_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__14553
                            (clojure.core/namespace
                             input__14393_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__14553]
                            (clojure.core/let
                             [X__14555
                              (clojure.core/name
                               input__14393_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__14555)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__14555)
                               (clojure.core/let
                                [?pattern
                                 input__14393_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__14393_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__7935__auto__
                                   (def__14544
                                    input__14393_nth_2__
                                    ?ns)]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    x__7935__auto__)
                                   (meander.runtime.zeta/fail)
                                   (clojure.core/let
                                    [[?env ?ns] x__7935__auto__]
                                    (try
                                     [(clojure.core/let
                                       [!init__counter
                                        (meander.runtime.zeta/iterator
                                         !init)]
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14472
                                          ['meander.dev.parse.zeta/make-join
                                           (clojure.core/let
                                            [CATA_RESULT__9238__auto__
                                             (CATA__FN__14472
                                              ['meander.dev.parse.zeta/parse-sequential
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !init__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9238__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9238__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__9238__auto__
                                             (CATA__FN__14472
                                              ['meander.dev.parse.zeta/make-join
                                               (clojure.core/let
                                                [CATA_RESULT__9238__auto__
                                                 (CATA__FN__14472
                                                  [?pattern ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__9238__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__9238__auto__
                                                  0)))
                                               (clojure.core/let
                                                [CATA_RESULT__9238__auto__
                                                 (CATA__FN__14472
                                                  ['meander.dev.parse.zeta/parse-sequential
                                                   (clojure.core/into
                                                    []
                                                    ?rest)
                                                   ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__9238__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__9238__auto__
                                                  0)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9238__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9238__auto__
                                              0)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9238__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9238__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__10178__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10178__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10178__auto__)))))))))
                               (meander.runtime.zeta/fail))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__15964)
                   (recur (clojure.core/next search_space__15963))
                   result__15964))
                 (state__15912)))
               (state__15912))
              (state__15912)))
            (state__15912))
           (state__15912))))
        (state__15912
         []
         (if
          (clojure.core/vector? input__14393)
          (clojure.core/letfn
           [(state__15966
             []
             (if
              (clojure.core/= (clojure.core/count input__14393) 3)
              (clojure.core/let
               [input__14393_nth_0__
                (clojure.core/nth input__14393 0)
                input__14393_nth_1__
                (clojure.core/nth input__14393 1)
                input__14393_nth_2__
                (clojure.core/nth input__14393 2)]
               (clojure.core/case
                input__14393_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__14393_nth_1__)
                 (clojure.core/letfn
                  [(state__15969
                    []
                    (clojure.core/let
                     [n__14606
                      (clojure.core/count input__14393_nth_1__)
                      m__14607
                      (clojure.core/max 0 (clojure.core/- n__14606 2))
                      input__14393_nth_1___l__
                      (clojure.core/subvec
                       input__14393_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__14393_nth_1__)
                        m__14607))
                      input__14393_nth_1___r__
                      (clojure.core/subvec
                       input__14393_nth_1__
                       m__14607)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__14393_nth_1___r__)
                       2)
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__14393_nth_1___l__)]
                       (if
                        (clojure.core/=
                         (clojure.core/count input__14393_nth_1___r__)
                         2)
                        (clojure.core/let
                         [input__14393_nth_1___r___nth_0__
                          (clojure.core/nth input__14393_nth_1___r__ 0)
                          input__14393_nth_1___r___nth_1__
                          (clojure.core/nth
                           input__14393_nth_1___r__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__14393_nth_1___r___nth_0__)
                          (clojure.core/let
                           [X__14611
                            (clojure.core/namespace
                             input__14393_nth_1___r___nth_0__)]
                           (clojure.core/let
                            [?ns X__14611]
                            (clojure.core/let
                             [X__14613
                              (clojure.core/name
                               input__14393_nth_1___r___nth_0__)]
                             (if
                              (clojure.core/string? X__14613)
                              (clojure.core/let
                               [ret__14614
                                (clojure.core/re-matches
                                 #"&.*"
                                 X__14613)]
                               (if
                                (clojure.core/some? ret__14614)
                                (clojure.core/let
                                 [?name ret__14614]
                                 (clojure.core/let
                                  [?pattern
                                   input__14393_nth_1___r___nth_1__]
                                  (if
                                   (clojure.core/map?
                                    input__14393_nth_2__)
                                   (clojure.core/let
                                    [VAL__14598
                                     (.valAt
                                      input__14393_nth_2__
                                      :aliases)]
                                    (if
                                     (clojure.core/map? VAL__14598)
                                     (clojure.core/let
                                      [X__14600
                                       (clojure.core/set VAL__14598)]
                                      (if
                                       (clojure.core/<=
                                        1
                                        (clojure.core/count X__14600))
                                       (clojure.core/loop
                                        [search_space__15973
                                         (clojure.core/seq X__14600)]
                                        (if
                                         (clojure.core/seq
                                          search_space__15973)
                                         (clojure.core/let
                                          [elem__14601
                                           (clojure.core/first
                                            search_space__15973)
                                           result__15974
                                           (clojure.core/let
                                            [elem__14601_nth_0__
                                             (clojure.core/nth
                                              elem__14601
                                              0)
                                             elem__14601_nth_1__
                                             (clojure.core/nth
                                              elem__14601
                                              1)]
                                            (if
                                             (clojure.core/symbol?
                                              elem__14601_nth_0__)
                                             (clojure.core/let
                                              [X__14603
                                               (clojure.core/name
                                                elem__14601_nth_0__)]
                                              (if
                                               (clojure.core/=
                                                ?ns
                                                X__14603)
                                               (if
                                                (clojure.core/symbol?
                                                 elem__14601_nth_1__)
                                                (clojure.core/let
                                                 [X__14605
                                                  (clojure.core/name
                                                   elem__14601_nth_1__)]
                                                 (clojure.core/case
                                                  X__14605
                                                  ("meander.zeta")
                                                  (clojure.core/let
                                                   [?env
                                                    input__14393_nth_2__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__9238__auto__
                                                        (CATA__FN__14472
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
                                                         CATA_RESULT__9238__auto__)
                                                        (throw
                                                         (meander.runtime.zeta/fail))
                                                        (clojure.core/nth
                                                         CATA_RESULT__9238__auto__
                                                         0))))]
                                                    (catch
                                                     java.lang.Exception
                                                     e__10178__auto__
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       e__10178__auto__)
                                                      (meander.runtime.zeta/fail)
                                                      (throw
                                                       e__10178__auto__)))))
                                                  (meander.runtime.zeta/fail)))
                                                (meander.runtime.zeta/fail))
                                               (meander.runtime.zeta/fail)))
                                             (meander.runtime.zeta/fail)))]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            result__15974)
                                           (recur
                                            (clojure.core/next
                                             search_space__15973))
                                           result__15974))
                                         (state__15970)))
                                       (state__15970)))
                                     (state__15970)))
                                   (state__15970))))
                                (state__15970)))
                              (state__15970)))))
                          (state__15970)))
                        (state__15970)))
                      (state__15970))))
                   (state__15970
                    []
                    (clojure.core/loop
                     [search_space__15976
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__14393_nth_1__)]
                     (if
                      (clojure.core/seq search_space__15976)
                      (clojure.core/let
                       [input__14393_nth_1___parts__
                        (clojure.core/first search_space__15976)
                        result__15977
                        (clojure.core/let
                         [input__14393_nth_1___l__
                          (clojure.core/nth
                           input__14393_nth_1___parts__
                           0)
                          input__14393_nth_1___r__
                          (clojure.core/nth
                           input__14393_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs
                           (clojure.core/vec input__14393_nth_1___l__)]
                          (clojure.core/let
                           [input__14393_nth_1___r___l__
                            (clojure.core/subvec
                             input__14393_nth_1___r__
                             0
                             (clojure.core/min
                              (clojure.core/count
                               input__14393_nth_1___r__)
                              1))]
                           (if
                            (clojure.core/=
                             (clojure.core/count
                              input__14393_nth_1___r___l__)
                             1)
                            (clojure.core/let
                             [input__14393_nth_1___r___r__
                              (clojure.core/subvec
                               input__14393_nth_1___r__
                               1)]
                             (if
                              (clojure.core/=
                               input__14393_nth_1___r___l__
                               ['.])
                              (clojure.core/let
                               [?rest input__14393_nth_1___r___r__]
                               (clojure.core/let
                                [?env input__14393_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [!xs__counter
                                    (meander.runtime.zeta/iterator
                                     !xs)]
                                   (clojure.core/let
                                    [CATA_RESULT__9238__auto__
                                     (CATA__FN__14472
                                      ['meander.dev.parse.zeta/make-join
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14472
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            (clojure.core/vec
                                             (clojure.core/iterator-seq
                                              !xs__counter)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9238__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9238__auto__
                                          0)))
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14472
                                          ['meander.dev.parse.zeta/parse-sequential
                                           ?rest
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9238__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9238__auto__
                                          0)))
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__9238__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__9238__auto__
                                      0))))]
                                 (catch
                                  java.lang.Exception
                                  e__10178__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__10178__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__10178__auto__))))))
                              (meander.runtime.zeta/fail)))
                            (meander.runtime.zeta/fail)))))]
                       (if
                        (meander.runtime.zeta/fail? result__15977)
                        (recur (clojure.core/next search_space__15976))
                        result__15977))
                      (state__15971))))
                   (state__15971
                    []
                    (clojure.core/let
                     [input__14393_nth_1___l__
                      (clojure.core/subvec
                       input__14393_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__14393_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__14393_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__14393_nth_1___r__
                        (clojure.core/subvec input__14393_nth_1__ 1)]
                       (if
                        (clojure.core/=
                         input__14393_nth_1___l__
                         ['...])
                        (clojure.core/let
                         [?rest input__14393_nth_1___r__]
                         (clojure.core/let
                          [?env input__14393_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__9238__auto__
                              (CATA__FN__14472
                               ['meander.dev.parse.zeta/parse-sequential
                                ?rest
                                ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__9238__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__9238__auto__
                               0)))]
                           (catch
                            java.lang.Exception
                            e__10178__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__10178__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10178__auto__))))))
                        (state__15972)))
                      (state__15972))))
                   (state__15972
                    []
                    (clojure.core/loop
                     [search_space__15979
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__14393_nth_1__)]
                     (if
                      (clojure.core/seq search_space__15979)
                      (clojure.core/let
                       [input__14393_nth_1___parts__
                        (clojure.core/first search_space__15979)
                        result__15980
                        (clojure.core/let
                         [input__14393_nth_1___l__
                          (clojure.core/nth
                           input__14393_nth_1___parts__
                           0)
                          input__14393_nth_1___r__
                          (clojure.core/nth
                           input__14393_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__8099__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__14393_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__14631]
                              (clojure.core/let
                               [input__14631_nth_0__
                                (clojure.core/nth input__14631 0)]
                               (clojure.core/letfn
                                [(save__14632
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__15983
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__14631_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__14631_nth_0__)
                                 (clojure.core/let
                                  [X__14634
                                   (clojure.core/namespace
                                    input__14631_nth_0__)]
                                  (clojure.core/case
                                   X__14634
                                   (nil)
                                   (clojure.core/let
                                    [X__14636
                                     (clojure.core/name
                                      input__14631_nth_0__)]
                                    (if
                                     (clojure.core/string? X__14636)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__14636)
                                      (save__14632)
                                      (f__15983))
                                     (f__15983)))
                                   (f__15983)))
                                 (f__15983)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__14393_nth_1___r___l__
                                (clojure.core/subvec
                                 input__14393_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__14393_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__14393_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__14393_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__14393_nth_1___r__
                                   1)]
                                 (if
                                  (clojure.core/=
                                   input__14393_nth_1___r___l__
                                   ['...])
                                  (clojure.core/let
                                   [?rest input__14393_nth_1___r___r__]
                                   (clojure.core/let
                                    [?env input__14393_nth_2__]
                                    (try
                                     [(clojure.core/let
                                       [!xs__counter
                                        (meander.runtime.zeta/iterator
                                         !xs)]
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14472
                                          ['meander.dev.parse.zeta/make-star
                                           (clojure.core/let
                                            [CATA_RESULT__9238__auto__
                                             (CATA__FN__14472
                                              ['meander.dev.parse.zeta/parse-sequential
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !xs__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9238__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9238__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__9238__auto__
                                             (CATA__FN__14472
                                              ['meander.dev.parse.zeta/parse-sequential
                                               ?rest
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9238__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9238__auto__
                                              0)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9238__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9238__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__10178__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10178__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10178__auto__))))))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))))]
                           (if
                            (meander.runtime.zeta/fail?
                             ret__8099__auto__)
                            (meander.runtime.zeta/fail)
                            ret__8099__auto__))))]
                       (if
                        (meander.runtime.zeta/fail? result__15980)
                        (recur (clojure.core/next search_space__15979))
                        result__15980))
                      (state__15967))))]
                  (state__15969))
                 (state__15967))
                (state__15967)))
              (state__15967)))
            (state__15967
             []
             (if
              (clojure.core/= (clojure.core/count input__14393) 4)
              (clojure.core/let
               [input__14393_nth_0__
                (clojure.core/nth input__14393 0)
                input__14393_nth_1__
                (clojure.core/nth input__14393 1)
                input__14393_nth_2__
                (clojure.core/nth input__14393 2)]
               (clojure.core/letfn
                [(state__15984
                  []
                  (clojure.core/let
                   [input__14393_nth_3__
                    (clojure.core/nth input__14393 3)]
                   (clojure.core/case
                    input__14393_nth_0__
                    (meander.dev.parse.zeta/make-star)
                    (clojure.core/letfn
                     [(state__15986
                       []
                       (if
                        (clojure.core/map? input__14393_nth_1__)
                        (clojure.core/let
                         [VAL__14640
                          (.valAt input__14393_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__14640
                          (:cat)
                          (clojure.core/let
                           [VAL__14641
                            (.valAt input__14393_nth_1__ :sequence)]
                           (if
                            (clojure.core/vector? VAL__14641)
                            (if
                             (clojure.core/=
                              (clojure.core/count VAL__14641)
                              1)
                             (clojure.core/let
                              [VAL__14641_nth_0__
                               (clojure.core/nth VAL__14641 0)]
                              (if
                               (clojure.core/map? VAL__14641_nth_0__)
                               (clojure.core/let
                                [VAL__14646
                                 (.valAt VAL__14641_nth_0__ :tag)]
                                (clojure.core/case
                                 VAL__14646
                                 (:memory-variable)
                                 (clojure.core/let
                                  [?memory-variable VAL__14641_nth_0__]
                                  (clojure.core/let
                                   [VAL__14642
                                    (.valAt
                                     input__14393_nth_1__
                                     :next)]
                                   (if
                                    (clojure.core/map? VAL__14642)
                                    (clojure.core/let
                                     [VAL__14643
                                      (.valAt VAL__14642 :tag)]
                                     (clojure.core/case
                                      VAL__14643
                                      (:empty)
                                      (clojure.core/let
                                       [?next input__14393_nth_2__]
                                       (clojure.core/let
                                        [?env input__14393_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__9238__auto__
                                            (CATA__FN__14472
                                             ['meander.dev.parse.zeta/make-join
                                              {:tag :into,
                                               :memory-variable
                                               ?memory-variable}
                                              ?next
                                              ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             CATA_RESULT__9238__auto__)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             CATA_RESULT__9238__auto__
                                             0)))]
                                         (catch
                                          java.lang.Exception
                                          e__10178__auto__
                                          (if
                                           (meander.runtime.zeta/fail?
                                            e__10178__auto__)
                                           (meander.runtime.zeta/fail)
                                           (throw
                                            e__10178__auto__))))))
                                      (state__15987)))
                                    (state__15987))))
                                 (state__15987)))
                               (state__15987)))
                             (state__15987))
                            (state__15987)))
                          (state__15987)))
                        (state__15987)))
                      (state__15987
                       []
                       (clojure.core/let
                        [?pattern input__14393_nth_1__]
                        (clojure.core/let
                         [?next input__14393_nth_2__]
                         (if
                          (clojure.core/map? input__14393_nth_3__)
                          (clojure.core/let
                           [VAL__14649
                            (.valAt input__14393_nth_3__ :context)]
                           (clojure.core/case
                            VAL__14649
                            (:string)
                            [{:tag :string-star,
                              :greedy? false,
                              :pattern ?pattern,
                              :next ?next}]
                            (state__15985)))
                          (state__15985)))))]
                     (state__15986))
                    (state__15985))))
                 (state__15985
                  []
                  (clojure.core/case
                   input__14393_nth_0__
                   (meander.dev.parse.zeta/make-star)
                   (clojure.core/let
                    [?pattern input__14393_nth_1__]
                    (clojure.core/let
                     [?next input__14393_nth_2__]
                     [{:tag :star,
                       :greedy? false,
                       :pattern ?pattern,
                       :next ?next}]))
                   (state__15968)))]
                (state__15984)))
              (state__15968)))
            (state__15968
             []
             (if
              (clojure.core/= (clojure.core/count input__14393) 3)
              (clojure.core/let
               [input__14393_nth_0__
                (clojure.core/nth input__14393 0)
                input__14393_nth_1__
                (clojure.core/nth input__14393 1)
                input__14393_nth_2__
                (clojure.core/nth input__14393 2)]
               (clojure.core/case
                input__14393_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__14393_nth_1__)
                 (clojure.core/let
                  [input__14393_nth_1___l__
                   (clojure.core/subvec
                    input__14393_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__14393_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__14393_nth_1___l__)
                    1)
                   (clojure.core/let
                    [input__14393_nth_1___r__
                     (clojure.core/subvec input__14393_nth_1__ 1)]
                    (clojure.core/let
                     [input__14393_nth_1___l___nth_0__
                      (clojure.core/nth input__14393_nth_1___l__ 0)]
                     (if
                      (clojure.core/symbol?
                       input__14393_nth_1___l___nth_0__)
                      (clojure.core/let
                       [X__14657
                        (clojure.core/namespace
                         input__14393_nth_1___l___nth_0__)]
                       (clojure.core/case
                        X__14657
                        (nil)
                        (clojure.core/let
                         [X__14659
                          (clojure.core/name
                           input__14393_nth_1___l___nth_0__)]
                         (if
                          (clojure.core/string? X__14659)
                          (clojure.core/let
                           [ret__14660
                            (clojure.core/re-matches
                             #"\.\.(\d+)"
                             X__14659)]
                           (if
                            (clojure.core/some? ret__14660)
                            (if
                             (clojure.core/vector? ret__14660)
                             (if
                              (clojure.core/=
                               (clojure.core/count ret__14660)
                               2)
                              (clojure.core/let
                               [ret__14660_nth_1__
                                (clojure.core/nth ret__14660 1)]
                               (clojure.core/let
                                [?n ret__14660_nth_1__]
                                (clojure.core/let
                                 [?operator
                                  input__14393_nth_1___l___nth_0__]
                                 (clojure.core/let
                                  [?rest input__14393_nth_1___r__]
                                  (clojure.core/let
                                   [?env input__14393_nth_2__]
                                   [{:tag :syntax-error,
                                     :message
                                     "The n or more operator ..N must be preceeded by at least one pattern"}])))))
                              (state__15913))
                             (state__15913))
                            (state__15913)))
                          (state__15913)))
                        (state__15913)))
                      (state__15913))))
                   (state__15913)))
                 (state__15913))
                (state__15913)))
              (state__15913)))]
           (state__15966))
          (state__15913)))
        (state__15913
         []
         (clojure.core/letfn
          [(def__14663
            [arg__14687]
            (clojure.core/letfn
             [(state__15988
               []
               (clojure.core/let
                [x__14688 :string-plus]
                (clojure.core/let
                 [?tag x__14688]
                 (if
                  (clojure.core/map? arg__14687)
                  (clojure.core/let
                   [VAL__14689 (.valAt arg__14687 :context)]
                   (clojure.core/case
                    VAL__14689
                    (:string)
                    (clojure.core/let [?env arg__14687] [?tag ?env])
                    (state__15989)))
                  (state__15989)))))
              (state__15989
               []
               (clojure.core/let
                [x__14690 :plus]
                (clojure.core/let
                 [?tag x__14690]
                 (clojure.core/let [?env arg__14687] [?tag ?env]))))]
             (state__15988)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 3)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)
              input__14393_nth_2__
              (clojure.core/nth input__14393 2)]
             (clojure.core/case
              input__14393_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__14393_nth_1__)
               (clojure.core/loop
                [search_space__15990
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__14393_nth_1__)]
                (if
                 (clojure.core/seq search_space__15990)
                 (clojure.core/let
                  [input__14393_nth_1___parts__
                   (clojure.core/first search_space__15990)
                   result__15991
                   (clojure.core/let
                    [input__14393_nth_1___l__
                     (clojure.core/nth input__14393_nth_1___parts__ 0)
                     input__14393_nth_1___r__
                     (clojure.core/nth input__14393_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__8099__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__14393_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__14680]
                         (clojure.core/let
                          [input__14680_nth_0__
                           (clojure.core/nth input__14680 0)]
                          (clojure.core/letfn
                           [(save__14681
                             []
                             (meander.runtime.zeta/fail))
                            (f__15994
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__14680_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__14680_nth_0__)
                            (clojure.core/let
                             [X__14683
                              (clojure.core/namespace
                               input__14680_nth_0__)]
                             (clojure.core/case
                              X__14683
                              (nil)
                              (clojure.core/let
                               [X__14685
                                (clojure.core/name
                                 input__14680_nth_0__)]
                               (if
                                (clojure.core/string? X__14685)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__14685)
                                 (save__14681)
                                 (f__15994))
                                (f__15994)))
                              (f__15994)))
                            (f__15994)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__14393_nth_1___r___l__
                           (clojure.core/subvec
                            input__14393_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__14393_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__14393_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__14393_nth_1___r___r__
                             (clojure.core/subvec
                              input__14393_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__14393_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__14393_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__14393_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__14674
                                (clojure.core/namespace
                                 input__14393_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__14674
                                (nil)
                                (clojure.core/let
                                 [X__14676
                                  (clojure.core/name
                                   input__14393_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__14676)
                                  (clojure.core/let
                                   [ret__14677
                                    (clojure.core/re-matches
                                     #"\.\.(\d+)"
                                     X__14676)]
                                   (if
                                    (clojure.core/some? ret__14677)
                                    (if
                                     (clojure.core/vector? ret__14677)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__14677)
                                       2)
                                      (clojure.core/let
                                       [ret__14677_nth_1__
                                        (clojure.core/nth
                                         ret__14677
                                         1)]
                                       (clojure.core/let
                                        [?n ret__14677_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__14393_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__7935__auto__
                                           (def__14663
                                            input__14393_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__7935__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__7935__auto__]
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
                                                 [CATA_RESULT__9238__auto__
                                                  (CATA__FN__14472
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9238__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9238__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__9238__auto__
                                                  (CATA__FN__14472
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9238__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9238__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__10178__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__10178__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__10178__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__8099__auto__)
                       (meander.runtime.zeta/fail)
                       ret__8099__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__15991)
                   (recur (clojure.core/next search_space__15990))
                   result__15991))
                 (state__15914)))
               (state__15914))
              (state__15914)))
            (state__15914))
           (state__15914))))
        (state__15914
         []
         (if
          (clojure.core/vector? input__14393)
          (if
           (clojure.core/= (clojure.core/count input__14393) 3)
           (clojure.core/let
            [input__14393_nth_0__
             (clojure.core/nth input__14393 0)
             input__14393_nth_1__
             (clojure.core/nth input__14393 1)
             input__14393_nth_2__
             (clojure.core/nth input__14393 2)]
            (clojure.core/case
             input__14393_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__14393_nth_1__)
              (clojure.core/let
               [input__14393_nth_1___l__
                (clojure.core/subvec
                 input__14393_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__14393_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__14393_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__14393_nth_1___r__
                  (clojure.core/subvec input__14393_nth_1__ 1)]
                 (clojure.core/let
                  [input__14393_nth_1___l___nth_0__
                   (clojure.core/nth input__14393_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14393_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__14708
                     (clojure.core/namespace
                      input__14393_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__14708
                     (nil)
                     (clojure.core/let
                      [X__14710
                       (clojure.core/name
                        input__14393_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__14710)
                       (clojure.core/let
                        [ret__14711
                         (clojure.core/re-matches
                          #"\.\.(\?.+)"
                          X__14710)]
                        (if
                         (clojure.core/some? ret__14711)
                         (if
                          (clojure.core/vector? ret__14711)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__14711)
                            2)
                           (clojure.core/let
                            [ret__14711_nth_1__
                             (clojure.core/nth ret__14711 1)]
                            (clojure.core/let
                             [?n ret__14711_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__14393_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__14393_nth_1___r__]
                               (clojure.core/let
                                [?env input__14393_nth_2__]
                                [{:tag :syntax-error,
                                  :message
                                  "The ?n or more operator ..?n must be preceeded by at least one pattern"}])))))
                           (state__15915))
                          (state__15915))
                         (state__15915)))
                       (state__15915)))
                     (state__15915)))
                   (state__15915))))
                (state__15915)))
              (state__15915))
             (state__15915)))
           (state__15915))
          (state__15915)))
        (state__15915
         []
         (clojure.core/letfn
          [(def__14714
            [arg__14738]
            (clojure.core/letfn
             [(state__15995
               []
               (clojure.core/let
                [x__14739 :string-logical-plus]
                (clojure.core/let
                 [?tag x__14739]
                 (if
                  (clojure.core/map? arg__14738)
                  (clojure.core/let
                   [VAL__14740 (.valAt arg__14738 :context)]
                   (clojure.core/case
                    VAL__14740
                    (:string)
                    (clojure.core/let [?env arg__14738] [?tag ?env])
                    (state__15996)))
                  (state__15996)))))
              (state__15996
               []
               (clojure.core/let
                [x__14741 :logical-plus]
                (clojure.core/let
                 [?tag x__14741]
                 (clojure.core/let [?env arg__14738] [?tag ?env]))))]
             (state__15995)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 3)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)
              input__14393_nth_2__
              (clojure.core/nth input__14393 2)]
             (clojure.core/case
              input__14393_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__14393_nth_1__)
               (clojure.core/loop
                [search_space__15997
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__14393_nth_1__)]
                (if
                 (clojure.core/seq search_space__15997)
                 (clojure.core/let
                  [input__14393_nth_1___parts__
                   (clojure.core/first search_space__15997)
                   result__15998
                   (clojure.core/let
                    [input__14393_nth_1___l__
                     (clojure.core/nth input__14393_nth_1___parts__ 0)
                     input__14393_nth_1___r__
                     (clojure.core/nth input__14393_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__8099__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__14393_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__14731]
                         (clojure.core/let
                          [input__14731_nth_0__
                           (clojure.core/nth input__14731 0)]
                          (clojure.core/letfn
                           [(save__14732
                             []
                             (meander.runtime.zeta/fail))
                            (f__16001
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__14731_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__14731_nth_0__)
                            (clojure.core/let
                             [X__14734
                              (clojure.core/namespace
                               input__14731_nth_0__)]
                             (clojure.core/case
                              X__14734
                              (nil)
                              (clojure.core/let
                               [X__14736
                                (clojure.core/name
                                 input__14731_nth_0__)]
                               (if
                                (clojure.core/string? X__14736)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__14736)
                                 (save__14732)
                                 (f__16001))
                                (f__16001)))
                              (f__16001)))
                            (f__16001)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__14393_nth_1___r___l__
                           (clojure.core/subvec
                            input__14393_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__14393_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__14393_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__14393_nth_1___r___r__
                             (clojure.core/subvec
                              input__14393_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__14393_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__14393_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__14393_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__14725
                                (clojure.core/namespace
                                 input__14393_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__14725
                                (nil)
                                (clojure.core/let
                                 [X__14727
                                  (clojure.core/name
                                   input__14393_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__14727)
                                  (clojure.core/let
                                   [ret__14728
                                    (clojure.core/re-matches
                                     #"\.\.(\?.+)"
                                     X__14727)]
                                   (if
                                    (clojure.core/some? ret__14728)
                                    (if
                                     (clojure.core/vector? ret__14728)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__14728)
                                       2)
                                      (clojure.core/let
                                       [ret__14728_nth_1__
                                        (clojure.core/nth
                                         ret__14728
                                         1)]
                                       (clojure.core/let
                                        [?n ret__14728_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__14393_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__7935__auto__
                                           (def__14714
                                            input__14393_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__7935__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__7935__auto__]
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
                                                 [CATA_RESULT__9238__auto__
                                                  (CATA__FN__14472
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9238__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9238__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__9238__auto__
                                                  (CATA__FN__14472
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9238__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9238__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__10178__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__10178__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__10178__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__8099__auto__)
                       (meander.runtime.zeta/fail)
                       ret__8099__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__15998)
                   (recur (clojure.core/next search_space__15997))
                   result__15998))
                 (state__15916)))
               (state__15916))
              (state__15916)))
            (state__15916))
           (state__15916))))
        (state__15916
         []
         (if
          (clojure.core/vector? input__14393)
          (if
           (clojure.core/= (clojure.core/count input__14393) 3)
           (clojure.core/let
            [input__14393_nth_0__
             (clojure.core/nth input__14393 0)
             input__14393_nth_1__
             (clojure.core/nth input__14393 1)
             input__14393_nth_2__
             (clojure.core/nth input__14393 2)]
            (clojure.core/case
             input__14393_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__14393_nth_1__)
              (clojure.core/let
               [input__14393_nth_1___l__
                (clojure.core/subvec
                 input__14393_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__14393_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__14393_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__14393_nth_1___r__
                  (clojure.core/subvec input__14393_nth_1__ 1)]
                 (clojure.core/let
                  [input__14393_nth_1___l___nth_0__
                   (clojure.core/nth input__14393_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14393_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__14759
                     (clojure.core/namespace
                      input__14393_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__14759
                     (nil)
                     (clojure.core/let
                      [X__14761
                       (clojure.core/name
                        input__14393_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__14761)
                       (clojure.core/let
                        [ret__14762
                         (clojure.core/re-matches
                          #"\.\.(!.+)"
                          X__14761)]
                        (if
                         (clojure.core/some? ret__14762)
                         (if
                          (clojure.core/vector? ret__14762)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__14762)
                            2)
                           (clojure.core/let
                            [ret__14762_nth_1__
                             (clojure.core/nth ret__14762 1)]
                            (clojure.core/let
                             [?n ret__14762_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__14393_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__14393_nth_1___r__]
                               (clojure.core/let
                                [?env input__14393_nth_2__]
                                [{:tag :syntax-error,
                                  :message
                                  "The operator ..!n must be preceeded by at least one pattern"}])))))
                           (state__15917))
                          (state__15917))
                         (state__15917)))
                       (state__15917)))
                     (state__15917)))
                   (state__15917))))
                (state__15917)))
              (state__15917))
             (state__15917)))
           (state__15917))
          (state__15917)))
        (state__15917
         []
         (clojure.core/letfn
          [(def__14765
            [arg__14789]
            (clojure.core/letfn
             [(state__16002
               []
               (clojure.core/let
                [x__14790 :string-memory-plus]
                (clojure.core/let
                 [?tag x__14790]
                 (if
                  (clojure.core/map? arg__14789)
                  (clojure.core/let
                   [VAL__14791 (.valAt arg__14789 :context)]
                   (clojure.core/case
                    VAL__14791
                    (:string)
                    (clojure.core/let [?env arg__14789] [?tag ?env])
                    (state__16003)))
                  (state__16003)))))
              (state__16003
               []
               (clojure.core/let
                [x__14792 :memory-plus]
                (clojure.core/let
                 [?tag x__14792]
                 (clojure.core/let [?env arg__14789] [?tag ?env]))))]
             (state__16002)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 3)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)
              input__14393_nth_2__
              (clojure.core/nth input__14393 2)]
             (clojure.core/case
              input__14393_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__14393_nth_1__)
               (clojure.core/loop
                [search_space__16004
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__14393_nth_1__)]
                (if
                 (clojure.core/seq search_space__16004)
                 (clojure.core/let
                  [input__14393_nth_1___parts__
                   (clojure.core/first search_space__16004)
                   result__16005
                   (clojure.core/let
                    [input__14393_nth_1___l__
                     (clojure.core/nth input__14393_nth_1___parts__ 0)
                     input__14393_nth_1___r__
                     (clojure.core/nth input__14393_nth_1___parts__ 1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__8099__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__14393_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__14782]
                         (clojure.core/let
                          [input__14782_nth_0__
                           (clojure.core/nth input__14782 0)]
                          (clojure.core/letfn
                           [(save__14783
                             []
                             (meander.runtime.zeta/fail))
                            (f__16008
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__14782_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol? input__14782_nth_0__)
                            (clojure.core/let
                             [X__14785
                              (clojure.core/namespace
                               input__14782_nth_0__)]
                             (clojure.core/case
                              X__14785
                              (nil)
                              (clojure.core/let
                               [X__14787
                                (clojure.core/name
                                 input__14782_nth_0__)]
                               (if
                                (clojure.core/string? X__14787)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__14787)
                                 (save__14783)
                                 (f__16008))
                                (f__16008)))
                              (f__16008)))
                            (f__16008)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__14393_nth_1___r___l__
                           (clojure.core/subvec
                            input__14393_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__14393_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__14393_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__14393_nth_1___r___r__
                             (clojure.core/subvec
                              input__14393_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__14393_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__14393_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__14393_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__14776
                                (clojure.core/namespace
                                 input__14393_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__14776
                                (nil)
                                (clojure.core/let
                                 [X__14778
                                  (clojure.core/name
                                   input__14393_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__14778)
                                  (clojure.core/let
                                   [ret__14779
                                    (clojure.core/re-matches
                                     #"\.\.(\!.+)"
                                     X__14778)]
                                   (if
                                    (clojure.core/some? ret__14779)
                                    (if
                                     (clojure.core/vector? ret__14779)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__14779)
                                       2)
                                      (clojure.core/let
                                       [ret__14779_nth_1__
                                        (clojure.core/nth
                                         ret__14779
                                         1)]
                                       (clojure.core/let
                                        [?n ret__14779_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__14393_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__7935__auto__
                                           (def__14765
                                            input__14393_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__7935__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__7935__auto__]
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
                                                 [CATA_RESULT__9238__auto__
                                                  (CATA__FN__14472
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9238__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9238__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__9238__auto__
                                                  (CATA__FN__14472
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9238__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9238__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__10178__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__10178__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__10178__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__8099__auto__)
                       (meander.runtime.zeta/fail)
                       ret__8099__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__16005)
                   (recur (clojure.core/next search_space__16004))
                   result__16005))
                 (state__15918)))
               (state__15918))
              (state__15918)))
            (state__15918))
           (state__15918))))
        (state__15918
         []
         (if
          (clojure.core/vector? input__14393)
          (clojure.core/letfn
           [(state__16009
             []
             (if
              (clojure.core/= (clojure.core/count input__14393) 3)
              (clojure.core/let
               [input__14393_nth_0__
                (clojure.core/nth input__14393 0)
                input__14393_nth_1__
                (clojure.core/nth input__14393 1)
                input__14393_nth_2__
                (clojure.core/nth input__14393 2)]
               (clojure.core/case
                input__14393_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__14393_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__14393_nth_1__)]
                  (clojure.core/let
                   [?env input__14393_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__9238__auto__
                        (CATA__FN__14472
                         ['meander.dev.parse.zeta/make-cat
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__14473 (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (recur
                              (clojure.core/conj!
                               return__14473
                               (clojure.core/let
                                [CATA_RESULT__9238__auto__
                                 (CATA__FN__14472
                                  [(if
                                    (.hasNext !xs__counter)
                                    (.next !xs__counter))
                                   ?env])]
                                (if
                                 (meander.runtime.zeta/fail?
                                  CATA_RESULT__9238__auto__)
                                 (throw (meander.runtime.zeta/fail))
                                 (clojure.core/nth
                                  CATA_RESULT__9238__auto__
                                  0)))))
                             (clojure.core/persistent!
                              return__14473))))
                          {:tag :empty}
                          ?env])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__9238__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__9238__auto__
                         0))))]
                    (catch
                     java.lang.Exception
                     e__10178__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10178__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10178__auto__))))))
                 (state__16010))
                (state__16010)))
              (state__16010)))
            (state__16010
             []
             (if
              (clojure.core/= (clojure.core/count input__14393) 4)
              (clojure.core/let
               [input__14393_nth_0__
                (clojure.core/nth input__14393 0)
                input__14393_nth_1__
                (clojure.core/nth input__14393 1)
                input__14393_nth_2__
                (clojure.core/nth input__14393 2)]
               (clojure.core/letfn
                [(state__16012
                  []
                  (clojure.core/let
                   [input__14393_nth_3__
                    (clojure.core/nth input__14393 3)]
                   (clojure.core/case
                    input__14393_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (if
                     (clojure.core/vector? input__14393_nth_1__)
                     (clojure.core/letfn
                      [(state__16017
                        []
                        (clojure.core/case
                         input__14393_nth_1__
                         ([])
                         (clojure.core/let
                          [?next input__14393_nth_2__]
                          (clojure.core/let
                           [?env input__14393_nth_3__]
                           [?next]))
                         (state__16018)))
                       (state__16018
                        []
                        (clojure.core/loop
                         [search_space__16020
                          (meander.runtime.zeta/epsilon-partitions
                           2
                           input__14393_nth_1__)]
                         (if
                          (clojure.core/seq search_space__16020)
                          (clojure.core/let
                           [input__14393_nth_1___parts__
                            (clojure.core/first search_space__16020)
                            result__16021
                            (clojure.core/let
                             [input__14393_nth_1___l__
                              (clojure.core/nth
                               input__14393_nth_1___parts__
                               0)
                              input__14393_nth_1___r__
                              (clojure.core/nth
                               input__14393_nth_1___parts__
                               1)]
                             (clojure.core/letfn
                              [(state__16023
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__8099__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__14393_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__14818]
                                     (clojure.core/let
                                      [input__14818_nth_0__
                                       (clojure.core/nth
                                        input__14818
                                        0)]
                                      (clojure.core/letfn
                                       [(save__14819
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__16027
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__14818_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__14818_nth_0__)
                                        (clojure.core/let
                                         [VAL__14820
                                          (.valAt
                                           input__14818_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__14820
                                          (:group)
                                          (save__14819)
                                          (f__16027)))
                                        (f__16027)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__14393_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__14393_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__14393_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__14393_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__14393_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__14393_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__14393_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__14393_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__14393_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__14817
                                            (.valAt
                                             input__14393_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__14817
                                            (:group)
                                            (clojure.core/let
                                             [?group
                                              input__14393_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__14393_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__14393_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__14393_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__9238__auto__
                                                     (CATA__FN__14472
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__9238__auto__
                                                         (CATA__FN__14472
                                                          ['meander.dev.parse.zeta/make-cat
                                                           (clojure.core/into
                                                            []
                                                            (clojure.core/vec
                                                             (clojure.core/iterator-seq
                                                              !xs__counter)))
                                                           {:tag
                                                            :empty}
                                                           ?env])]
                                                        (if
                                                         (meander.runtime.zeta/fail?
                                                          CATA_RESULT__9238__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__9238__auto__
                                                          0)))
                                                       (clojure.core/let
                                                        [CATA_RESULT__9238__auto__
                                                         (CATA__FN__14472
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?group
                                                           (clojure.core/let
                                                            [CATA_RESULT__9238__auto__
                                                             (CATA__FN__14472
                                                              ['meander.dev.parse.zeta/make-cat
                                                               ?rest
                                                               ?next
                                                               ?env])]
                                                            (if
                                                             (meander.runtime.zeta/fail?
                                                              CATA_RESULT__9238__auto__)
                                                             (throw
                                                              (meander.runtime.zeta/fail))
                                                             (clojure.core/nth
                                                              CATA_RESULT__9238__auto__
                                                              0)))
                                                           ?env])]
                                                        (if
                                                         (meander.runtime.zeta/fail?
                                                          CATA_RESULT__9238__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__9238__auto__
                                                          0)))
                                                       ?env])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__9238__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__9238__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__10178__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__10178__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__10178__auto__))))))))
                                            (state__16024)))
                                          (state__16024))))
                                       (state__16024)))))]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    ret__8099__auto__)
                                   (state__16024)
                                   ret__8099__auto__))))
                               (state__16024
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__8099__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__14393_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__14829]
                                     (clojure.core/let
                                      [input__14829_nth_0__
                                       (clojure.core/nth
                                        input__14829
                                        0)]
                                      (clojure.core/letfn
                                       [(save__14830
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__16029
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__14829_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__14829_nth_0__)
                                        (clojure.core/let
                                         [VAL__14831
                                          (.valAt
                                           input__14829_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__14831
                                          (:star)
                                          (save__14830)
                                          (f__16029)))
                                        (f__16029)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__14393_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__14393_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__14393_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__14393_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__14393_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__14393_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__14393_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__14393_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__14393_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__14828
                                            (.valAt
                                             input__14393_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__14828
                                            (:star)
                                            (clojure.core/let
                                             [?star
                                              input__14393_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__14393_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__14393_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__14393_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__9238__auto__
                                                     (CATA__FN__14472
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__9238__auto__
                                                         (CATA__FN__14472
                                                          ['meander.dev.parse.zeta/make-cat
                                                           (clojure.core/into
                                                            []
                                                            (clojure.core/vec
                                                             (clojure.core/iterator-seq
                                                              !xs__counter)))
                                                           {:tag
                                                            :empty}
                                                           ?env])]
                                                        (if
                                                         (meander.runtime.zeta/fail?
                                                          CATA_RESULT__9238__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__9238__auto__
                                                          0)))
                                                       (clojure.core/let
                                                        [CATA_RESULT__9238__auto__
                                                         (CATA__FN__14472
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?star
                                                           (clojure.core/let
                                                            [CATA_RESULT__9238__auto__
                                                             (CATA__FN__14472
                                                              ['meander.dev.parse.zeta/make-cat
                                                               ?rest
                                                               ?next
                                                               ?env])]
                                                            (if
                                                             (meander.runtime.zeta/fail?
                                                              CATA_RESULT__9238__auto__)
                                                             (throw
                                                              (meander.runtime.zeta/fail))
                                                             (clojure.core/nth
                                                              CATA_RESULT__9238__auto__
                                                              0)))
                                                           ?env])]
                                                        (if
                                                         (meander.runtime.zeta/fail?
                                                          CATA_RESULT__9238__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__9238__auto__
                                                          0)))
                                                       ?env])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__9238__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__9238__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__10178__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__10178__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__10178__auto__))))))))
                                            (state__16025)))
                                          (state__16025))))
                                       (state__16025)))))]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    ret__8099__auto__)
                                   (state__16025)
                                   ret__8099__auto__))))
                               (state__16025
                                []
                                (clojure.core/let
                                 [input__14393_nth_1___l___l__
                                  (clojure.core/subvec
                                   input__14393_nth_1___l__
                                   0
                                   (clojure.core/min
                                    (clojure.core/count
                                     input__14393_nth_1___l__)
                                    1))]
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count
                                    input__14393_nth_1___l___l__)
                                   1)
                                  (clojure.core/let
                                   [input__14393_nth_1___l___r__
                                    (clojure.core/subvec
                                     input__14393_nth_1___l__
                                     1)]
                                   (clojure.core/let
                                    [input__14393_nth_1___l___l___nth_0__
                                     (clojure.core/nth
                                      input__14393_nth_1___l___l__
                                      0)]
                                    (clojure.core/letfn
                                     [(state__16030
                                       []
                                       (clojure.core/letfn
                                        [(save__14839
                                          []
                                          (state__16031))
                                         (f__16032
                                          []
                                          (clojure.core/let
                                           [!xs []]
                                           (clojure.core/let
                                            [!xs
                                             (clojure.core/conj
                                              !xs
                                              input__14393_nth_1___l___l___nth_0__)]
                                            (clojure.core/loop
                                             [i__8072__auto__
                                              0
                                              coll__16033
                                              input__14393_nth_1___l___r__
                                              [!xs]
                                              [!xs]]
                                             (clojure.core/let
                                              [input__14844
                                               (clojure.core/subvec
                                                coll__16033
                                                0
                                                (clojure.core/min
                                                 (clojure.core/count
                                                  coll__16033)
                                                 1))]
                                              (if
                                               (clojure.core/=
                                                (clojure.core/count
                                                 input__14844)
                                                1)
                                               (clojure.core/let
                                                [result__8073__auto__
                                                 (clojure.core/let
                                                  [input__14844_nth_0__
                                                   (clojure.core/nth
                                                    input__14844
                                                    0)]
                                                  (clojure.core/letfn
                                                   [(save__14845
                                                     []
                                                     (meander.runtime.zeta/fail))
                                                    (f__16034
                                                     []
                                                     (clojure.core/let
                                                      [!xs
                                                       (clojure.core/conj
                                                        !xs
                                                        input__14844_nth_0__)]
                                                      [!xs]))]
                                                   (if
                                                    (clojure.core/map?
                                                     input__14844_nth_0__)
                                                    (clojure.core/let
                                                     [VAL__14846
                                                      (.valAt
                                                       input__14844_nth_0__
                                                       :tag)]
                                                     (clojure.core/case
                                                      VAL__14846
                                                      (:reference)
                                                      (save__14845)
                                                      (f__16034)))
                                                    (f__16034))))]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  result__8073__auto__)
                                                 (state__16031)
                                                 (recur
                                                  (clojure.core/inc
                                                   i__8072__auto__)
                                                  (clojure.core/subvec
                                                   coll__16033
                                                   1)
                                                  result__8073__auto__)))
                                               (if
                                                (clojure.core/or
                                                 (clojure.core/seq
                                                  coll__16033)
                                                 (clojure.core/<
                                                  i__8072__auto__
                                                  0))
                                                (state__16031)
                                                (clojure.core/let
                                                 [input__14393_nth_1___r___l__
                                                  (clojure.core/subvec
                                                   input__14393_nth_1___r__
                                                   0
                                                   (clojure.core/min
                                                    (clojure.core/count
                                                     input__14393_nth_1___r__)
                                                    1))]
                                                 (if
                                                  (clojure.core/=
                                                   (clojure.core/count
                                                    input__14393_nth_1___r___l__)
                                                   1)
                                                  (clojure.core/let
                                                   [input__14393_nth_1___r___r__
                                                    (clojure.core/subvec
                                                     input__14393_nth_1___r__
                                                     1)]
                                                   (clojure.core/let
                                                    [input__14393_nth_1___r___l___nth_0__
                                                     (clojure.core/nth
                                                      input__14393_nth_1___r___l__
                                                      0)]
                                                    (if
                                                     (clojure.core/map?
                                                      input__14393_nth_1___r___l___nth_0__)
                                                     (clojure.core/let
                                                      [VAL__14843
                                                       (.valAt
                                                        input__14393_nth_1___r___l___nth_0__
                                                        :tag)]
                                                      (clojure.core/case
                                                       VAL__14843
                                                       (:reference)
                                                       (clojure.core/let
                                                        [?reference
                                                         input__14393_nth_1___r___l___nth_0__]
                                                        (clojure.core/let
                                                         [?rest
                                                          input__14393_nth_1___r___r__]
                                                         (clojure.core/let
                                                          [?next
                                                           input__14393_nth_2__]
                                                          (clojure.core/let
                                                           [?env
                                                            input__14393_nth_3__]
                                                           (try
                                                            [(clojure.core/let
                                                              [!xs__counter
                                                               (meander.runtime.zeta/iterator
                                                                !xs)]
                                                              (clojure.core/let
                                                               [CATA_RESULT__9238__auto__
                                                                (CATA__FN__14472
                                                                 ['meander.dev.parse.zeta/make-join
                                                                  (clojure.core/let
                                                                   [CATA_RESULT__9238__auto__
                                                                    (CATA__FN__14472
                                                                     ['meander.dev.parse.zeta/make-cat
                                                                      (clojure.core/into
                                                                       []
                                                                       (clojure.core/vec
                                                                        (clojure.core/iterator-seq
                                                                         !xs__counter)))
                                                                      {:tag
                                                                       :empty}
                                                                      ?env])]
                                                                   (if
                                                                    (meander.runtime.zeta/fail?
                                                                     CATA_RESULT__9238__auto__)
                                                                    (throw
                                                                     (meander.runtime.zeta/fail))
                                                                    (clojure.core/nth
                                                                     CATA_RESULT__9238__auto__
                                                                     0)))
                                                                  (clojure.core/let
                                                                   [CATA_RESULT__9238__auto__
                                                                    (CATA__FN__14472
                                                                     ['meander.dev.parse.zeta/make-join
                                                                      (clojure.core/let
                                                                       [CATA_RESULT__9238__auto__
                                                                        (CATA__FN__14472
                                                                         ['meander.dev.parse.zeta/make-cat
                                                                          [?reference]
                                                                          {:tag
                                                                           :empty}
                                                                          ?env])]
                                                                       (if
                                                                        (meander.runtime.zeta/fail?
                                                                         CATA_RESULT__9238__auto__)
                                                                        (throw
                                                                         (meander.runtime.zeta/fail))
                                                                        (clojure.core/nth
                                                                         CATA_RESULT__9238__auto__
                                                                         0)))
                                                                      (clojure.core/let
                                                                       [CATA_RESULT__9238__auto__
                                                                        (CATA__FN__14472
                                                                         ['meander.dev.parse.zeta/make-cat
                                                                          ?rest
                                                                          ?next
                                                                          ?env])]
                                                                       (if
                                                                        (meander.runtime.zeta/fail?
                                                                         CATA_RESULT__9238__auto__)
                                                                        (throw
                                                                         (meander.runtime.zeta/fail))
                                                                        (clojure.core/nth
                                                                         CATA_RESULT__9238__auto__
                                                                         0)))
                                                                      ?env])]
                                                                   (if
                                                                    (meander.runtime.zeta/fail?
                                                                     CATA_RESULT__9238__auto__)
                                                                    (throw
                                                                     (meander.runtime.zeta/fail))
                                                                    (clojure.core/nth
                                                                     CATA_RESULT__9238__auto__
                                                                     0)))
                                                                  ?env])]
                                                               (if
                                                                (meander.runtime.zeta/fail?
                                                                 CATA_RESULT__9238__auto__)
                                                                (throw
                                                                 (meander.runtime.zeta/fail))
                                                                (clojure.core/nth
                                                                 CATA_RESULT__9238__auto__
                                                                 0))))]
                                                            (catch
                                                             java.lang.Exception
                                                             e__10178__auto__
                                                             (if
                                                              (meander.runtime.zeta/fail?
                                                               e__10178__auto__)
                                                              (meander.runtime.zeta/fail)
                                                              (throw
                                                               e__10178__auto__))))))))
                                                       (state__16031)))
                                                     (state__16031))))
                                                  (state__16031))))))))))]
                                        (if
                                         (clojure.core/map?
                                          input__14393_nth_1___l___l___nth_0__)
                                         (clojure.core/let
                                          [VAL__14840
                                           (.valAt
                                            input__14393_nth_1___l___l___nth_0__
                                            :tag)]
                                          (clojure.core/case
                                           VAL__14840
                                           (:reference)
                                           (save__14839)
                                           (f__16032)))
                                         (f__16032))))
                                      (state__16031
                                       []
                                       (if
                                        (clojure.core/map?
                                         input__14393_nth_1___l___l___nth_0__)
                                        (clojure.core/let
                                         [VAL__14854
                                          (.valAt
                                           input__14393_nth_1___l___l___nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__14854
                                          (:literal)
                                          (clojure.core/let
                                           [VAL__14855
                                            (.valAt
                                             input__14393_nth_1___l___l___nth_0__
                                             :type)]
                                           (clojure.core/case
                                            VAL__14855
                                            (:string)
                                            (clojure.core/let
                                             [VAL__14856
                                              (.valAt
                                               input__14393_nth_1___l___l___nth_0__
                                               :form)]
                                             (clojure.core/let
                                              [!forms []]
                                              (clojure.core/let
                                               [!forms
                                                (clojure.core/conj
                                                 !forms
                                                 VAL__14856)]
                                               (clojure.core/loop
                                                [i__8072__auto__
                                                 0
                                                 coll__16035
                                                 input__14393_nth_1___l___r__
                                                 [!forms]
                                                 [!forms]]
                                                (clojure.core/let
                                                 [input__14857
                                                  (clojure.core/subvec
                                                   coll__16035
                                                   0
                                                   (clojure.core/min
                                                    (clojure.core/count
                                                     coll__16035)
                                                    1))]
                                                 (if
                                                  (clojure.core/=
                                                   (clojure.core/count
                                                    input__14857)
                                                   1)
                                                  (clojure.core/let
                                                   [result__8073__auto__
                                                    (clojure.core/let
                                                     [input__14857_nth_0__
                                                      (clojure.core/nth
                                                       input__14857
                                                       0)]
                                                     (if
                                                      (clojure.core/map?
                                                       input__14857_nth_0__)
                                                      (clojure.core/let
                                                       [VAL__14858
                                                        (.valAt
                                                         input__14857_nth_0__
                                                         :tag)]
                                                       (clojure.core/case
                                                        VAL__14858
                                                        (:literal)
                                                        (clojure.core/let
                                                         [VAL__14859
                                                          (.valAt
                                                           input__14857_nth_0__
                                                           :type)]
                                                         (clojure.core/case
                                                          VAL__14859
                                                          (:string)
                                                          (clojure.core/let
                                                           [VAL__14860
                                                            (.valAt
                                                             input__14857_nth_0__
                                                             :form)]
                                                           (clojure.core/let
                                                            [!forms
                                                             (clojure.core/conj
                                                              !forms
                                                              VAL__14860)]
                                                            [!forms]))
                                                          (meander.runtime.zeta/fail)))
                                                        (meander.runtime.zeta/fail)))
                                                      (meander.runtime.zeta/fail)))]
                                                   (if
                                                    (meander.runtime.zeta/fail?
                                                     result__8073__auto__)
                                                    (meander.runtime.zeta/fail)
                                                    (recur
                                                     (clojure.core/inc
                                                      i__8072__auto__)
                                                     (clojure.core/subvec
                                                      coll__16035
                                                      1)
                                                     result__8073__auto__)))
                                                  (if
                                                   (clojure.core/or
                                                    (clojure.core/seq
                                                     coll__16035)
                                                    (clojure.core/<
                                                     i__8072__auto__
                                                     1))
                                                   (meander.runtime.zeta/fail)
                                                   (clojure.core/let
                                                    [?rest
                                                     input__14393_nth_1___r__]
                                                    (clojure.core/let
                                                     [?next
                                                      input__14393_nth_2__]
                                                     (clojure.core/let
                                                      [?env
                                                       input__14393_nth_3__]
                                                      (try
                                                       [(clojure.core/let
                                                         [!forms__counter
                                                          (meander.runtime.zeta/iterator
                                                           !forms)]
                                                         (clojure.core/let
                                                          [CATA_RESULT__9238__auto__
                                                           (CATA__FN__14472
                                                            ['meander.dev.parse.zeta/make-cat
                                                             (clojure.core/into
                                                              []
                                                              (clojure.core/concat
                                                               (clojure.core/list
                                                                {:tag
                                                                 :literal,
                                                                 :type
                                                                 :string,
                                                                 :form
                                                                 (clojure.string/join
                                                                  (clojure.core/into
                                                                   []
                                                                   (clojure.core/vec
                                                                    (clojure.core/iterator-seq
                                                                     !forms__counter))))})
                                                               ?rest))
                                                             ?next
                                                             ?env])]
                                                          (if
                                                           (meander.runtime.zeta/fail?
                                                            CATA_RESULT__9238__auto__)
                                                           (throw
                                                            (meander.runtime.zeta/fail))
                                                           (clojure.core/nth
                                                            CATA_RESULT__9238__auto__
                                                            0))))]
                                                       (catch
                                                        java.lang.Exception
                                                        e__10178__auto__
                                                        (if
                                                         (meander.runtime.zeta/fail?
                                                          e__10178__auto__)
                                                         (meander.runtime.zeta/fail)
                                                         (throw
                                                          e__10178__auto__))))))))))))))
                                            (meander.runtime.zeta/fail)))
                                          (meander.runtime.zeta/fail)))
                                        (meander.runtime.zeta/fail)))]
                                     (state__16030))))
                                  (meander.runtime.zeta/fail))))]
                              (state__16023)))]
                           (if
                            (meander.runtime.zeta/fail? result__16021)
                            (recur
                             (clojure.core/next search_space__16020))
                            result__16021))
                          (state__16019))))
                       (state__16019
                        []
                        (clojure.core/let
                         [input__14393_nth_1___l__
                          (clojure.core/subvec
                           input__14393_nth_1__
                           0
                           (clojure.core/min
                            (clojure.core/count input__14393_nth_1__)
                            1))]
                         (if
                          (clojure.core/=
                           (clojure.core/count
                            input__14393_nth_1___l__)
                           1)
                          (clojure.core/let
                           [input__14393_nth_1___r__
                            (clojure.core/subvec
                             input__14393_nth_1__
                             1)]
                           (clojure.core/let
                            [input__14393_nth_1___l___nth_0__
                             (clojure.core/nth
                              input__14393_nth_1___l__
                              0)]
                            (clojure.core/letfn
                             [(state__16036
                               []
                               (if
                                (clojure.core/map?
                                 input__14393_nth_1___l___nth_0__)
                                (clojure.core/let
                                 [VAL__14866
                                  (.valAt
                                   input__14393_nth_1___l___nth_0__
                                   :tag)]
                                 (clojure.core/case
                                  VAL__14866
                                  (:literal)
                                  (clojure.core/let
                                   [VAL__14867
                                    (.valAt
                                     input__14393_nth_1___l___nth_0__
                                     :type)]
                                   (clojure.core/case
                                    VAL__14867
                                    (:string)
                                    (clojure.core/let
                                     [?ast
                                      input__14393_nth_1___l___nth_0__]
                                     (clojure.core/let
                                      [?rest input__14393_nth_1___r__]
                                      (clojure.core/let
                                       [?next input__14393_nth_2__]
                                       (clojure.core/let
                                        [?env input__14393_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__9238__auto__
                                            (CATA__FN__14472
                                             ['meander.dev.parse.zeta/make-join
                                              ?ast
                                              (clojure.core/let
                                               [CATA_RESULT__9238__auto__
                                                (CATA__FN__14472
                                                 ['meander.dev.parse.zeta/make-cat
                                                  ?rest
                                                  ?next
                                                  ?env])]
                                               (if
                                                (meander.runtime.zeta/fail?
                                                 CATA_RESULT__9238__auto__)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 CATA_RESULT__9238__auto__
                                                 0)))
                                              ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             CATA_RESULT__9238__auto__)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             CATA_RESULT__9238__auto__
                                             0)))]
                                         (catch
                                          java.lang.Exception
                                          e__10178__auto__
                                          (if
                                           (meander.runtime.zeta/fail?
                                            e__10178__auto__)
                                           (meander.runtime.zeta/fail)
                                           (throw
                                            e__10178__auto__))))))))
                                    (state__16037)))
                                  (state__16037)))
                                (state__16037)))
                              (state__16037
                               []
                               (clojure.core/let
                                [?x input__14393_nth_1___l___nth_0__]
                                (clojure.core/let
                                 [?sequence input__14393_nth_1___r__]
                                 (clojure.core/let
                                  [?next input__14393_nth_2__]
                                  (if
                                   (clojure.core/map?
                                    input__14393_nth_3__)
                                   (clojure.core/let
                                    [VAL__14870
                                     (.valAt
                                      input__14393_nth_3__
                                      :context)]
                                    (clojure.core/case
                                     VAL__14870
                                     (:string)
                                     (clojure.core/let
                                      [?env input__14393_nth_3__]
                                      (try
                                       [(clojure.core/let
                                         [CATA_RESULT__9238__auto__
                                          (CATA__FN__14472
                                           ['meander.dev.parse.zeta/make-join
                                            ?x
                                            (clojure.core/let
                                             [CATA_RESULT__9238__auto__
                                              (CATA__FN__14472
                                               ['meander.dev.parse.zeta/make-cat
                                                ?sequence
                                                ?next
                                                ?env])]
                                             (if
                                              (meander.runtime.zeta/fail?
                                               CATA_RESULT__9238__auto__)
                                              (throw
                                               (meander.runtime.zeta/fail))
                                              (clojure.core/nth
                                               CATA_RESULT__9238__auto__
                                               0)))
                                            ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__9238__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__9238__auto__
                                           0)))]
                                       (catch
                                        java.lang.Exception
                                        e__10178__auto__
                                        (if
                                         (meander.runtime.zeta/fail?
                                          e__10178__auto__)
                                         (meander.runtime.zeta/fail)
                                         (throw e__10178__auto__)))))
                                     (state__16013)))
                                   (state__16013))))))]
                             (state__16036))))
                          (state__16013))))]
                      (state__16017))
                     (state__16013))
                    (state__16013))))
                 (state__16013
                  []
                  (clojure.core/case
                   input__14393_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (clojure.core/let
                    [?sequence input__14393_nth_1__]
                    (clojure.core/let
                     [?next input__14393_nth_2__]
                     [{:tag :cat, :sequence ?sequence, :next ?next}]))
                   (state__16014)))
                 (state__16014
                  []
                  (clojure.core/let
                   [input__14393_nth_3__
                    (clojure.core/nth input__14393 3)]
                   (clojure.core/case
                    input__14393_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (clojure.core/letfn
                     [(state__16038
                       []
                       (if
                        (clojure.core/map? input__14393_nth_1__)
                        (clojure.core/let
                         [VAL__14877
                          (.valAt input__14393_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__14877
                          (:star)
                          (clojure.core/let
                           [VAL__14878
                            (.valAt input__14393_nth_1__ :next)]
                           (if
                            (clojure.core/map? VAL__14878)
                            (clojure.core/let
                             [VAL__14879 (.valAt VAL__14878 :tag)]
                             (clojure.core/case
                              VAL__14879
                              (:empty)
                              (clojure.core/let
                               [?left input__14393_nth_1__]
                               (clojure.core/let
                                [?right input__14393_nth_2__]
                                (clojure.core/let
                                 [?env input__14393_nth_3__]
                                 [(clojure.core/let
                                   [form__9337__auto__
                                    {:tag :star, :next ?right}]
                                   (clojure.core/merge
                                    (clojure.core/into {} ?left)
                                    form__9337__auto__))])))
                              (state__16039)))
                            (state__16039)))
                          (state__16039)))
                        (state__16039)))
                      (state__16039
                       []
                       (clojure.core/let
                        [?left input__14393_nth_1__]
                        (if
                         (clojure.core/map? input__14393_nth_2__)
                         (clojure.core/let
                          [VAL__14882
                           (.valAt input__14393_nth_2__ :tag)]
                          (clojure.core/case
                           VAL__14882
                           (:empty)
                           (clojure.core/let
                            [?env input__14393_nth_3__]
                            [?left])
                           (state__16040)))
                         (state__16040))))
                      (state__16040
                       []
                       (if
                        (clojure.core/map? input__14393_nth_1__)
                        (clojure.core/let
                         [VAL__15907
                          (.valAt input__14393_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__15907
                          (:empty)
                          (clojure.core/let
                           [?right input__14393_nth_2__]
                           (clojure.core/let
                            [?env input__14393_nth_3__]
                            [?right]))
                          (:cat)
                          (clojure.core/let
                           [VAL__14889
                            (.valAt input__14393_nth_1__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__14889]
                            (clojure.core/let
                             [VAL__14890
                              (.valAt input__14393_nth_1__ :next)]
                             (if
                              (clojure.core/map? VAL__14890)
                              (clojure.core/let
                               [VAL__14891 (.valAt VAL__14890 :tag)]
                               (clojure.core/case
                                VAL__14891
                                (:empty)
                                (clojure.core/let
                                 [?right input__14393_nth_2__]
                                 (clojure.core/let
                                  [?env input__14393_nth_3__]
                                  (try
                                   [(clojure.core/let
                                     [CATA_RESULT__9238__auto__
                                      (CATA__FN__14472
                                       ['meander.dev.parse.zeta/make-cat
                                        ?sequence
                                        ?right
                                        ?env])]
                                     (if
                                      (meander.runtime.zeta/fail?
                                       CATA_RESULT__9238__auto__)
                                      (throw
                                       (meander.runtime.zeta/fail))
                                      (clojure.core/nth
                                       CATA_RESULT__9238__auto__
                                       0)))]
                                   (catch
                                    java.lang.Exception
                                    e__10178__auto__
                                    (if
                                     (meander.runtime.zeta/fail?
                                      e__10178__auto__)
                                     (meander.runtime.zeta/fail)
                                     (throw e__10178__auto__))))))
                                (state__16015)))
                              (state__16015)))))
                          (state__16015)))
                        (state__16015)))]
                     (state__16038))
                    (state__16015))))
                 (state__16015
                  []
                  (clojure.core/case
                   input__14393_nth_0__
                   (meander.dev.parse.zeta/make-join)
                   (clojure.core/letfn
                    [(state__16041
                      []
                      (if
                       (clojure.core/map? input__14393_nth_1__)
                       (clojure.core/let
                        [VAL__15908 (.valAt input__14393_nth_1__ :tag)]
                        (clojure.core/case
                         VAL__15908
                         (:cat)
                         (clojure.core/let
                          [?ast input__14393_nth_1__]
                          (if
                           (clojure.core/map? input__14393_nth_2__)
                           (clojure.core/let
                            [VAL__14895
                             (.valAt input__14393_nth_2__ :tag)]
                            (clojure.core/case
                             VAL__14895
                             (:cat)
                             (clojure.core/let
                              [VAL__14896
                               (.valAt input__14393_nth_2__ :sequence)]
                              (clojure.core/let
                               [?sequence VAL__14896]
                               (clojure.core/let
                                [VAL__14897
                                 (.valAt input__14393_nth_2__ :next)]
                                (clojure.core/let
                                 [?next VAL__14897]
                                 [{:tag :cat,
                                   :sequence
                                   (clojure.core/into
                                    []
                                    (clojure.core/concat
                                     (clojure.core/list ?ast)
                                     ?sequence)),
                                   :next ?next}]))))
                             (state__16042)))
                           (state__16042)))
                         (:literal)
                         (clojure.core/let
                          [VAL__14901
                           (.valAt input__14393_nth_1__ :type)]
                          (clojure.core/case
                           VAL__14901
                           (:string)
                           (clojure.core/let
                            [VAL__14902
                             (.valAt input__14393_nth_1__ :form)]
                            (clojure.core/let
                             [?form VAL__14902]
                             (clojure.core/let
                              [?right input__14393_nth_2__]
                              [{:tag :string-prefix,
                                :form ?form,
                                :next ?right}])))
                           (state__16042)))
                         (state__16042)))
                       (state__16042)))
                     (state__16042
                      []
                      (clojure.core/let
                       [?left input__14393_nth_1__]
                       (if
                        (clojure.core/map? input__14393_nth_2__)
                        (clojure.core/let
                         [VAL__14905
                          (.valAt input__14393_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__14905
                          (:literal)
                          (clojure.core/let
                           [VAL__14906
                            (.valAt input__14393_nth_2__ :type)]
                           (clojure.core/case
                            VAL__14906
                            (:string)
                            (clojure.core/let
                             [VAL__14907
                              (.valAt input__14393_nth_2__ :form)]
                             (clojure.core/let
                              [?form VAL__14907]
                              [{:tag :string-suffix,
                                :form ?form,
                                :next ?left}]))
                            (state__16016)))
                          (state__16016)))
                        (state__16016))))]
                    (state__16041))
                   (state__16016)))
                 (state__16016
                  []
                  (clojure.core/let
                   [input__14393_nth_3__
                    (clojure.core/nth input__14393 3)]
                   (clojure.core/case
                    input__14393_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (clojure.core/letfn
                     [(state__16043
                       []
                       (if
                        (clojure.core/map? input__14393_nth_1__)
                        (clojure.core/let
                         [VAL__14910
                          (.valAt input__14393_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__14910
                          (:string-star)
                          (clojure.core/let
                           [VAL__14911
                            (.valAt input__14393_nth_1__ :pattern)]
                           (clojure.core/let
                            [?pattern VAL__14911]
                            (clojure.core/let
                             [VAL__14912
                              (.valAt input__14393_nth_1__ :next)]
                             (if
                              (clojure.core/map? VAL__14912)
                              (clojure.core/let
                               [VAL__14913 (.valAt VAL__14912 :tag)]
                               (clojure.core/case
                                VAL__14913
                                (:empty)
                                (clojure.core/let
                                 [?right input__14393_nth_2__]
                                 (if
                                  (clojure.core/map?
                                   input__14393_nth_3__)
                                  (clojure.core/let
                                   [VAL__14914
                                    (.valAt
                                     input__14393_nth_3__
                                     :context)]
                                   (clojure.core/case
                                    VAL__14914
                                    (:string)
                                    [{:tag :string-star,
                                      :pattern ?pattern,
                                      :next ?right}]
                                    (state__16044)))
                                  (state__16044)))
                                (state__16044)))
                              (state__16044)))))
                          (state__16044)))
                        (state__16044)))
                      (state__16044
                       []
                       (clojure.core/let
                        [?left input__14393_nth_1__]
                        (if
                         (clojure.core/map? input__14393_nth_2__)
                         (clojure.core/let
                          [VAL__14917
                           (.valAt input__14393_nth_2__ :tag)]
                          (clojure.core/case
                           VAL__14917
                           (:string-prefix)
                           (clojure.core/let
                            [VAL__14918
                             (.valAt input__14393_nth_2__ :form)]
                            (clojure.core/let
                             [?form VAL__14918]
                             (clojure.core/let
                              [VAL__14919
                               (.valAt input__14393_nth_2__ :next)]
                              (clojure.core/let
                               [?right VAL__14919]
                               (if
                                (clojure.core/map?
                                 input__14393_nth_3__)
                                (clojure.core/let
                                 [VAL__14920
                                  (.valAt
                                   input__14393_nth_3__
                                   :context)]
                                 (clojure.core/case
                                  VAL__14920
                                  (:string)
                                  (clojure.core/let
                                   [?env input__14393_nth_3__]
                                   [{:tag :string-infix,
                                     :form ?form,
                                     :left ?left,
                                     :right ?right}])
                                  (state__16045)))
                                (state__16045))))))
                           (state__16045)))
                         (state__16045))))
                      (state__16045
                       []
                       (if
                        (clojure.core/map? input__14393_nth_1__)
                        (clojure.core/let
                         [VAL__14923
                          (.valAt input__14393_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__14923
                          (:string-join)
                          (clojure.core/let
                           [VAL__14924
                            (.valAt input__14393_nth_1__ :left)]
                           (clojure.core/let
                            [?left VAL__14924]
                            (clojure.core/let
                             [VAL__14925
                              (.valAt input__14393_nth_1__ :right)]
                             (clojure.core/let
                              [?right-1 VAL__14925]
                              (clojure.core/let
                               [?right-2 input__14393_nth_2__]
                               (if
                                (clojure.core/map?
                                 input__14393_nth_3__)
                                (clojure.core/let
                                 [VAL__14926
                                  (.valAt
                                   input__14393_nth_3__
                                   :context)]
                                 (clojure.core/case
                                  VAL__14926
                                  (:string)
                                  (clojure.core/let
                                   [?env input__14393_nth_3__]
                                   (try
                                    [{:tag :string-join,
                                      :left ?left,
                                      :right
                                      (clojure.core/let
                                       [CATA_RESULT__9238__auto__
                                        (CATA__FN__14472
                                         ['meander.dev.parse.zeta/make-join
                                          ?right-1
                                          ?right-2
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9238__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9238__auto__
                                         0)))}]
                                    (catch
                                     java.lang.Exception
                                     e__10178__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10178__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10178__auto__)))))
                                  (state__16046)))
                                (state__16046)))))))
                          (state__16046)))
                        (state__16046)))
                      (state__16046
                       []
                       (clojure.core/let
                        [?left input__14393_nth_1__]
                        (clojure.core/let
                         [?right input__14393_nth_2__]
                         (clojure.core/letfn
                          [(state__16047
                            []
                            (if
                             (clojure.core/map? input__14393_nth_3__)
                             (clojure.core/let
                              [VAL__14929
                               (.valAt input__14393_nth_3__ :context)]
                              (clojure.core/case
                               VAL__14929
                               (:string)
                               [{:tag :string-join,
                                 :left ?left,
                                 :right ?right}]
                               (state__16048)))
                             (state__16048)))
                           (state__16048
                            []
                            (clojure.core/let
                             [?env input__14393_nth_3__]
                             [{:tag :join,
                               :left ?left,
                               :right ?right}]))]
                          (state__16047)))))]
                     (state__16043))
                    (state__16011))))]
                (state__16012)))
              (state__16011)))
            (state__16011
             []
             (if
              (clojure.core/= (clojure.core/count input__14393) 3)
              (clojure.core/let
               [input__14393_nth_0__
                (clojure.core/nth input__14393 0)
                input__14393_nth_1__
                (clojure.core/nth input__14393 1)
                input__14393_nth_2__
                (clojure.core/nth input__14393 2)]
               (clojure.core/case
                input__14393_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__14393_nth_1__)
                 (clojure.core/let
                  [VAL__14934
                   (.valAt input__14393_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__14934]
                   (clojure.core/let
                    [X__14936
                     ((clojure.core/fn
                       [m__7002__auto__]
                       (clojure.core/dissoc
                        m__7002__auto__
                        :meander.zeta/as))
                      input__14393_nth_1__)]
                    (clojure.core/let
                     [?rest X__14936]
                     (clojure.core/letfn
                      [(save__14937 [] (state__15919))
                       (f__16049
                        []
                        (clojure.core/let
                         [?env input__14393_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern
                            (clojure.core/let
                             [CATA_RESULT__9238__auto__
                              (CATA__FN__14472 [?pattern ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__9238__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__9238__auto__
                               0))),
                            :next
                            (clojure.core/let
                             [CATA_RESULT__9238__auto__
                              (CATA__FN__14472 [?rest ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__9238__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__9238__auto__
                               0)))}]
                          (catch
                           java.lang.Exception
                           e__10178__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__10178__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__10178__auto__))))))]
                      (if
                       (clojure.core/= ?rest input__14393_nth_1__)
                       (save__14937)
                       (f__16049)))))))
                 (state__15919))
                (state__15919)))
              (state__15919)))]
           (state__16009))
          (state__15919)))
        (state__15919
         []
         (clojure.core/letfn
          [(def__14940
            [arg__14973 ?ns]
            (clojure.core/letfn
             [(state__16050
               []
               (clojure.core/let
                [x__14974 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__14974)
                 (clojure.core/let [?env arg__14973] [?env ?ns])
                 (state__16051))))
              (state__16051
               []
               (if
                (clojure.core/map? arg__14973)
                (clojure.core/let
                 [VAL__14975 (.valAt arg__14973 :aliases)]
                 (if
                  (clojure.core/map? VAL__14975)
                  (clojure.core/let
                   [X__14977 (clojure.core/set VAL__14975)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__14977))
                    (clojure.core/loop
                     [search_space__16052 (clojure.core/seq X__14977)]
                     (if
                      (clojure.core/seq search_space__16052)
                      (clojure.core/let
                       [elem__14978
                        (clojure.core/first search_space__16052)
                        result__16053
                        (clojure.core/let
                         [elem__14978_nth_0__
                          (clojure.core/nth elem__14978 0)
                          elem__14978_nth_1__
                          (clojure.core/nth elem__14978 1)]
                         (if
                          (clojure.core/symbol? elem__14978_nth_0__)
                          (clojure.core/let
                           [X__14980
                            (clojure.core/name elem__14978_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__14980)
                            (if
                             (clojure.core/symbol? elem__14978_nth_1__)
                             (clojure.core/let
                              [X__14982
                               (clojure.core/name elem__14978_nth_1__)]
                              (clojure.core/case
                               X__14982
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__14973]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16053)
                        (recur (clojure.core/next search_space__16052))
                        result__16053))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16050)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 3)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)
              input__14393_nth_2__
              (clojure.core/nth input__14393 2)]
             (clojure.core/case
              input__14393_nth_0__
              (meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__14393_nth_1__)
               (clojure.core/let
                [X__14945 (clojure.core/set input__14393_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__14945))
                 (clojure.core/loop
                  [search_space__16055 (clojure.core/seq X__14945)]
                  (if
                   (clojure.core/seq search_space__16055)
                   (clojure.core/let
                    [elem__14946
                     (clojure.core/first search_space__16055)
                     result__16056
                     (clojure.core/let
                      [elem__14946_nth_0__
                       (clojure.core/nth elem__14946 0)
                       elem__14946_nth_1__
                       (clojure.core/nth elem__14946 1)]
                      (clojure.core/let
                       [*m__14427 elem__14946_nth_0__]
                       (if
                        (clojure.core/symbol? elem__14946_nth_0__)
                        (clojure.core/let
                         [X__14948
                          (clojure.core/namespace elem__14946_nth_0__)]
                         (clojure.core/let
                          [?ns X__14948]
                          (clojure.core/let
                           [X__14950
                            (clojure.core/name elem__14946_nth_0__)]
                           (if
                            (clojure.core/string? X__14950)
                            (if
                             (clojure.core/re-matches #"&.*" X__14950)
                             (clojure.core/let
                              [?pattern elem__14946_nth_1__]
                              (clojure.core/let
                               [X__14952
                                ((clojure.core/fn
                                  [m__7002__auto__]
                                  (clojure.core/dissoc
                                   m__7002__auto__
                                   *m__14427))
                                 input__14393_nth_1__)]
                               (clojure.core/let
                                [?rest X__14952]
                                (clojure.core/let
                                 [x__7935__auto__
                                  (def__14940
                                   input__14393_nth_2__
                                   ?ns)]
                                 (if
                                  (meander.runtime.zeta/fail?
                                   x__7935__auto__)
                                  (meander.runtime.zeta/fail)
                                  (clojure.core/let
                                   [[?env ?ns] x__7935__auto__]
                                   (try
                                    [{:tag :rest-map,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9238__auto__
                                        (CATA__FN__14472
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9238__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9238__auto__
                                         0))),
                                      :next
                                      (clojure.core/let
                                       [CATA_RESULT__9238__auto__
                                        (CATA__FN__14472
                                         ['meander.dev.parse.zeta/parse-entries
                                          ?rest
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9238__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9238__auto__
                                         0)))}]
                                    (catch
                                     java.lang.Exception
                                     e__10178__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10178__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10178__auto__))))))))))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))))
                        (meander.runtime.zeta/fail))))]
                    (if
                     (meander.runtime.zeta/fail? result__16056)
                     (recur (clojure.core/next search_space__16055))
                     result__16056))
                   (state__15920)))
                 (state__15920)))
               (state__15920))
              (state__15920)))
            (state__15920))
           (state__15920))))
        (state__15920
         []
         (if
          (clojure.core/vector? input__14393)
          (clojure.core/letfn
           [(state__16058
             []
             (if
              (clojure.core/= (clojure.core/count input__14393) 3)
              (clojure.core/let
               [input__14393_nth_0__
                (clojure.core/nth input__14393 0)
                input__14393_nth_1__
                (clojure.core/nth input__14393 1)
                input__14393_nth_2__
                (clojure.core/nth input__14393 2)]
               (clojure.core/case
                input__14393_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (clojure.core/letfn
                 [(state__16060
                   []
                   (if
                    (clojure.core/map? input__14393_nth_1__)
                    (clojure.core/let
                     [X__14996 (clojure.core/set input__14393_nth_1__)]
                     (if
                      (clojure.core/<= 1 (clojure.core/count X__14996))
                      (clojure.core/loop
                       [search_space__16062
                        (clojure.core/seq X__14996)]
                       (if
                        (clojure.core/seq search_space__16062)
                        (clojure.core/let
                         [elem__14997
                          (clojure.core/first search_space__16062)
                          result__16063
                          (clojure.core/let
                           [elem__14997_nth_0__
                            (clojure.core/nth elem__14997 0)
                            elem__14997_nth_1__
                            (clojure.core/nth elem__14997 1)]
                           (clojure.core/let
                            [?key-pattern elem__14997_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__14997_nth_1__]
                             (clojure.core/let
                              [X__14999
                               ((clojure.core/fn
                                 [m__7002__auto__]
                                 (clojure.core/dissoc
                                  m__7002__auto__
                                  ?key-pattern))
                                input__14393_nth_1__)]
                              (clojure.core/let
                               [?rest X__14999]
                               (clojure.core/let
                                [?env input__14393_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__9238__auto__
                                     (CATA__FN__14472
                                      [?key-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__9238__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__9238__auto__
                                      0))),
                                   :val-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__9238__auto__
                                     (CATA__FN__14472
                                      [?val-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__9238__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__9238__auto__
                                      0))),
                                   :next
                                   (clojure.core/let
                                    [CATA_RESULT__9238__auto__
                                     (CATA__FN__14472
                                      ['meander.dev.parse.zeta/parse-entries
                                       ?rest
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__9238__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__9238__auto__
                                      0)))}]
                                 (catch
                                  java.lang.Exception
                                  e__10178__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__10178__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__10178__auto__))))))))))]
                         (if
                          (meander.runtime.zeta/fail? result__16063)
                          (recur
                           (clojure.core/next search_space__16062))
                          result__16063))
                        (state__16061)))
                      (state__16061)))
                    (state__16061)))
                  (state__16061
                   []
                   (if
                    (clojure.core/map? input__14393_nth_1__)
                    (clojure.core/let
                     [?env input__14393_nth_2__]
                     [{:tag :some-map}])
                    (state__16059)))]
                 (state__16060))
                (meander.dev.parse.zeta/parse-with-bindings)
                (clojure.core/letfn
                 [(state__16065
                   []
                   (if
                    (clojure.core/vector? input__14393_nth_1__)
                    (clojure.core/case
                     input__14393_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__14393_nth_2__]
                      [[]])
                     (state__16066))
                    (state__16066)))
                  (state__16066
                   []
                   (if
                    (clojure.core/vector? input__14393_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__14393_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__14393_nth_2__]
                      [[{:tag :error,
                         :message
                         "meander.zeta/with expects an even number of bindings"}]])
                     (state__16067))
                    (state__16067)))
                  (state__16067
                   []
                   (if
                    (clojure.core/vector? input__14393_nth_1__)
                    (clojure.core/let
                     [input__14393_nth_1___l__
                      (clojure.core/subvec
                       input__14393_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__14393_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__14393_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__14393_nth_1___r__
                        (clojure.core/subvec input__14393_nth_1__ 2)]
                       (clojure.core/let
                        [input__14393_nth_1___l___nth_0__
                         (clojure.core/nth input__14393_nth_1___l__ 0)
                         input__14393_nth_1___l___nth_1__
                         (clojure.core/nth input__14393_nth_1___l__ 1)]
                        (if
                         (clojure.core/symbol?
                          input__14393_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__15013
                           (clojure.core/namespace
                            input__14393_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__15015
                            (clojure.core/name
                             input__14393_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__15015)
                            (if
                             (clojure.core/re-matches #"%.+" X__15015)
                             (clojure.core/let
                              [?symbol
                               input__14393_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__14393_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__14393_nth_1___r__]
                                (clojure.core/let
                                 [?env input__14393_nth_2__]
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
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14472
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9238__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9238__auto__
                                          0)))})
                                     (clojure.core/let
                                      [CATA_RESULT__9238__auto__
                                       (CATA__FN__14472
                                        ['meander.dev.parse.zeta/parse-with-bindings
                                         ?rest
                                         ?env])]
                                      (if
                                       (meander.runtime.zeta/fail?
                                        CATA_RESULT__9238__auto__)
                                       (throw
                                        (meander.runtime.zeta/fail))
                                       (clojure.core/nth
                                        CATA_RESULT__9238__auto__
                                        0)))))]
                                  (catch
                                   java.lang.Exception
                                   e__10178__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__10178__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__10178__auto__))))))))
                             (state__16068))
                            (state__16068))))
                         (state__16068))))
                      (state__16068)))
                    (state__16068)))
                  (state__16068
                   []
                   (if
                    (clojure.core/vector? input__14393_nth_1__)
                    (clojure.core/let
                     [input__14393_nth_1___l__
                      (clojure.core/subvec
                       input__14393_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__14393_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__14393_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__14393_nth_1___r__
                        (clojure.core/subvec input__14393_nth_1__ 2)]
                       (clojure.core/let
                        [input__14393_nth_1___l___nth_0__
                         (clojure.core/nth input__14393_nth_1___l__ 0)
                         input__14393_nth_1___l___nth_1__
                         (clojure.core/nth input__14393_nth_1___l__ 1)]
                        (clojure.core/let
                         [?x input__14393_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__14393_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__14393_nth_1___r__]
                           (clojure.core/let
                            [?env input__14393_nth_2__]
                            [[{:tag :error,
                               :message
                               "meander.zeta/with bindings must be an repeating sequence of %name pattern"}]]))))))
                      (state__16059)))
                    (state__16059)))]
                 (state__16065))
                (state__16059)))
              (state__16059)))
            (state__16059
             []
             (if
              (clojure.core/= (clojure.core/count input__14393) 2)
              (clojure.core/let
               [input__14393_nth_0__
                (clojure.core/nth input__14393 0)
                input__14393_nth_1__
                (clojure.core/nth input__14393 1)]
               (if
                (clojure.core/vector? input__14393_nth_0__)
                (clojure.core/let
                 [?sequence input__14393_nth_0__]
                 (clojure.core/let
                  [?form input__14393_nth_0__]
                  (clojure.core/let
                   [?env input__14393_nth_1__]
                   (try
                    [{:tag :vector,
                      :next
                      (clojure.core/let
                       [CATA_RESULT__9238__auto__
                        (CATA__FN__14472
                         ['meander.dev.parse.zeta/parse-sequential
                          ?sequence
                          (clojure.core/let
                           [form__9337__auto__ {:context :vector}]
                           (clojure.core/merge
                            (clojure.core/into {} ?env)
                            form__9337__auto__))])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__9238__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__9238__auto__
                         0))),
                      :form ?form}]
                    (catch
                     java.lang.Exception
                     e__10178__auto__
                     (if
                      (meander.runtime.zeta/fail? e__10178__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__10178__auto__)))))))
                (state__15921)))
              (state__15921)))]
           (state__16058))
          (state__15921)))
        (state__15921
         []
         (clojure.core/letfn
          [(def__15025
            [arg__15048 ?__14395]
            (clojure.core/letfn
             [(state__16069
               []
               (clojure.core/let
                [x__15049 "clojure.core"]
                (if
                 (clojure.core/= ?__14395 x__15049)
                 [?__14395]
                 (state__16070))))
              (state__16070
               []
               (if
                (clojure.core/map? arg__15048)
                (clojure.core/let
                 [VAL__15050 (.valAt arg__15048 :aliases)]
                 (if
                  (clojure.core/map? VAL__15050)
                  (clojure.core/let
                   [X__15052 (clojure.core/set VAL__15050)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15052))
                    (clojure.core/loop
                     [search_space__16071 (clojure.core/seq X__15052)]
                     (if
                      (clojure.core/seq search_space__16071)
                      (clojure.core/let
                       [elem__15053
                        (clojure.core/first search_space__16071)
                        result__16072
                        (clojure.core/let
                         [elem__15053_nth_0__
                          (clojure.core/nth elem__15053 0)
                          elem__15053_nth_1__
                          (clojure.core/nth elem__15053 1)]
                         (if
                          (clojure.core/symbol? elem__15053_nth_0__)
                          (clojure.core/let
                           [X__15055
                            (clojure.core/name elem__15053_nth_0__)]
                           (if
                            (clojure.core/= ?__14395 X__15055)
                            (if
                             (clojure.core/symbol? elem__15053_nth_1__)
                             (clojure.core/let
                              [X__15057
                               (clojure.core/name elem__15053_nth_1__)]
                              (clojure.core/case
                               X__15057
                               ("clojure.core")
                               [?__14395]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16072)
                        (recur (clojure.core/next search_space__16071))
                        result__16072))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16069)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 2)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)]
             (if
              (clojure.core/seq? input__14393_nth_0__)
              (clojure.core/let
               [input__14393_nth_0___l__
                (clojure.core/take 1 input__14393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14393_nth_0___r__
                  (clojure.core/drop 1 input__14393_nth_0__)]
                 (clojure.core/let
                  [input__14393_nth_0___l___nth_0__
                   (clojure.core/nth input__14393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15035
                     (clojure.core/namespace
                      input__14393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14395 X__15035]
                     (clojure.core/let
                      [X__15037
                       (clojure.core/name
                        input__14393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15037
                       ("unquote")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15025 input__14393_nth_1__ ?__14395)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15922)
                         (clojure.core/let
                          [[?__14395] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14393)
                             2)
                            (clojure.core/let
                             [input__14393_nth_0__
                              (clojure.core/nth input__14393 0)
                              input__14393_nth_1__
                              (clojure.core/nth input__14393 1)]
                             (if
                              (clojure.core/seq? input__14393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__14393_nth_0__)
                                2)
                               (clojure.core/let
                                [input__14393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14393_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?x input__14393_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__14393_nth_0__]
                                  (clojure.core/let
                                   [?env input__14393_nth_1__]
                                   [{:tag :host-expression,
                                     :expression ?x,
                                     :form ?form}]))))
                               (state__15922))
                              (state__15922)))
                            (state__15922))
                           (state__15922)))))
                       (state__15922)))))
                   (state__15922))))
                (state__15922)))
              (state__15922)))
            (state__15922))
           (state__15922))))
        (state__15922
         []
         (clojure.core/letfn
          [(def__15059
            [arg__15082 ?__14396]
            (clojure.core/letfn
             [(state__16074
               []
               (clojure.core/let
                [x__15083 "meander.zeta"]
                (if
                 (clojure.core/= ?__14396 x__15083)
                 [?__14396]
                 (state__16075))))
              (state__16075
               []
               (if
                (clojure.core/map? arg__15082)
                (clojure.core/let
                 [VAL__15084 (.valAt arg__15082 :aliases)]
                 (if
                  (clojure.core/map? VAL__15084)
                  (clojure.core/let
                   [X__15086 (clojure.core/set VAL__15084)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15086))
                    (clojure.core/loop
                     [search_space__16076 (clojure.core/seq X__15086)]
                     (if
                      (clojure.core/seq search_space__16076)
                      (clojure.core/let
                       [elem__15087
                        (clojure.core/first search_space__16076)
                        result__16077
                        (clojure.core/let
                         [elem__15087_nth_0__
                          (clojure.core/nth elem__15087 0)
                          elem__15087_nth_1__
                          (clojure.core/nth elem__15087 1)]
                         (if
                          (clojure.core/symbol? elem__15087_nth_0__)
                          (clojure.core/let
                           [X__15089
                            (clojure.core/name elem__15087_nth_0__)]
                           (if
                            (clojure.core/= ?__14396 X__15089)
                            (if
                             (clojure.core/symbol? elem__15087_nth_1__)
                             (clojure.core/let
                              [X__15091
                               (clojure.core/name elem__15087_nth_1__)]
                              (clojure.core/case
                               X__15091
                               ("meander.zeta")
                               [?__14396]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16077)
                        (recur (clojure.core/next search_space__16076))
                        result__16077))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16074)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 2)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)]
             (if
              (clojure.core/seq? input__14393_nth_0__)
              (clojure.core/let
               [input__14393_nth_0___l__
                (clojure.core/take 1 input__14393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14393_nth_0___r__
                  (clojure.core/drop 1 input__14393_nth_0__)]
                 (clojure.core/let
                  [input__14393_nth_0___l___nth_0__
                   (clojure.core/nth input__14393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15069
                     (clojure.core/namespace
                      input__14393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14396 X__15069]
                     (clojure.core/let
                      [X__15071
                       (clojure.core/name
                        input__14393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15071
                       ("*")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15059 input__14393_nth_1__ ?__14396)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15923)
                         (clojure.core/let
                          [[?__14396] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14393)
                             2)
                            (clojure.core/let
                             [input__14393_nth_0__
                              (clojure.core/nth input__14393 0)
                              input__14393_nth_1__
                              (clojure.core/nth input__14393 1)]
                             (if
                              (clojure.core/seq? input__14393_nth_0__)
                              (clojure.core/let
                               [input__14393_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__14393_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__14393_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__14393_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__14393_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__14393_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__14393_nth_0__]
                                   (clojure.core/let
                                    [?env input__14393_nth_1__]
                                    (try
                                     [{:tag :star,
                                       :greedy? true,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14472
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?patterns)
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9238__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9238__auto__
                                          0))),
                                       :next {:tag :empty}}]
                                     (catch
                                      java.lang.Exception
                                      e__10178__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10178__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10178__auto__))))))))
                                (state__15923)))
                              (state__15923)))
                            (state__15923))
                           (state__15923)))))
                       (state__15923)))))
                   (state__15923))))
                (state__15923)))
              (state__15923)))
            (state__15923))
           (state__15923))))
        (state__15923
         []
         (clojure.core/letfn
          [(def__15093
            [arg__15116 ?__14397]
            (clojure.core/letfn
             [(state__16079
               []
               (clojure.core/let
                [x__15117 "meander.zeta"]
                (if
                 (clojure.core/= ?__14397 x__15117)
                 [?__14397]
                 (state__16080))))
              (state__16080
               []
               (if
                (clojure.core/map? arg__15116)
                (clojure.core/let
                 [VAL__15118 (.valAt arg__15116 :aliases)]
                 (if
                  (clojure.core/map? VAL__15118)
                  (clojure.core/let
                   [X__15120 (clojure.core/set VAL__15118)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15120))
                    (clojure.core/loop
                     [search_space__16081 (clojure.core/seq X__15120)]
                     (if
                      (clojure.core/seq search_space__16081)
                      (clojure.core/let
                       [elem__15121
                        (clojure.core/first search_space__16081)
                        result__16082
                        (clojure.core/let
                         [elem__15121_nth_0__
                          (clojure.core/nth elem__15121 0)
                          elem__15121_nth_1__
                          (clojure.core/nth elem__15121 1)]
                         (if
                          (clojure.core/symbol? elem__15121_nth_0__)
                          (clojure.core/let
                           [X__15123
                            (clojure.core/name elem__15121_nth_0__)]
                           (if
                            (clojure.core/= ?__14397 X__15123)
                            (if
                             (clojure.core/symbol? elem__15121_nth_1__)
                             (clojure.core/let
                              [X__15125
                               (clojure.core/name elem__15121_nth_1__)]
                              (clojure.core/case
                               X__15125
                               ("meander.zeta")
                               [?__14397]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16082)
                        (recur (clojure.core/next search_space__16081))
                        result__16082))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16079)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 2)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)]
             (if
              (clojure.core/seq? input__14393_nth_0__)
              (clojure.core/let
               [input__14393_nth_0___l__
                (clojure.core/take 1 input__14393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14393_nth_0___r__
                  (clojure.core/drop 1 input__14393_nth_0__)]
                 (clojure.core/let
                  [input__14393_nth_0___l___nth_0__
                   (clojure.core/nth input__14393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15103
                     (clojure.core/namespace
                      input__14393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14397 X__15103]
                     (clojure.core/let
                      [X__15105
                       (clojure.core/name
                        input__14393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15105
                       ("<>")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15093 input__14393_nth_1__ ?__14397)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15924)
                         (clojure.core/let
                          [[?__14397] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14393)
                             2)
                            (clojure.core/let
                             [input__14393_nth_0__
                              (clojure.core/nth input__14393 0)
                              input__14393_nth_1__
                              (clojure.core/nth input__14393 1)]
                             (if
                              (clojure.core/seq? input__14393_nth_0__)
                              (clojure.core/let
                               [input__14393_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__14393_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__14393_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__14393_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__14393_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__14393_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__14393_nth_0__]
                                   (clojure.core/let
                                    [?env input__14393_nth_1__]
                                    (try
                                     [{:tag :group,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14472
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?patterns)
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9238__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9238__auto__
                                          0)))}]
                                     (catch
                                      java.lang.Exception
                                      e__10178__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10178__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10178__auto__))))))))
                                (state__15924)))
                              (state__15924)))
                            (state__15924))
                           (state__15924)))))
                       (state__15924)))))
                   (state__15924))))
                (state__15924)))
              (state__15924)))
            (state__15924))
           (state__15924))))
        (state__15924
         []
         (clojure.core/letfn
          [(def__15127
            [arg__15150 ?__14398]
            (clojure.core/letfn
             [(state__16084
               []
               (clojure.core/let
                [x__15151 "meander.math.zeta"]
                (if
                 (clojure.core/= ?__14398 x__15151)
                 [?__14398]
                 (state__16085))))
              (state__16085
               []
               (if
                (clojure.core/map? arg__15150)
                (clojure.core/let
                 [VAL__15152 (.valAt arg__15150 :aliases)]
                 (if
                  (clojure.core/map? VAL__15152)
                  (clojure.core/let
                   [X__15154 (clojure.core/set VAL__15152)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15154))
                    (clojure.core/loop
                     [search_space__16086 (clojure.core/seq X__15154)]
                     (if
                      (clojure.core/seq search_space__16086)
                      (clojure.core/let
                       [elem__15155
                        (clojure.core/first search_space__16086)
                        result__16087
                        (clojure.core/let
                         [elem__15155_nth_0__
                          (clojure.core/nth elem__15155 0)
                          elem__15155_nth_1__
                          (clojure.core/nth elem__15155 1)]
                         (if
                          (clojure.core/symbol? elem__15155_nth_0__)
                          (clojure.core/let
                           [X__15157
                            (clojure.core/name elem__15155_nth_0__)]
                           (if
                            (clojure.core/= ?__14398 X__15157)
                            (if
                             (clojure.core/symbol? elem__15155_nth_1__)
                             (clojure.core/let
                              [X__15159
                               (clojure.core/name elem__15155_nth_1__)]
                              (clojure.core/case
                               X__15159
                               ("meander.math.zeta")
                               [?__14398]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16087)
                        (recur (clojure.core/next search_space__16086))
                        result__16087))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16084)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 2)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)]
             (if
              (clojure.core/seq? input__14393_nth_0__)
              (clojure.core/let
               [input__14393_nth_0___l__
                (clojure.core/take 1 input__14393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14393_nth_0___r__
                  (clojure.core/drop 1 input__14393_nth_0__)]
                 (clojure.core/let
                  [input__14393_nth_0___l___nth_0__
                   (clojure.core/nth input__14393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15137
                     (clojure.core/namespace
                      input__14393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14398 X__15137]
                     (clojure.core/let
                      [X__15139
                       (clojure.core/name
                        input__14393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15139
                       ("+")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15127 input__14393_nth_1__ ?__14398)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15925)
                         (clojure.core/let
                          [[?__14398] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14393)
                             2)
                            (clojure.core/let
                             [input__14393_nth_0__
                              (clojure.core/nth input__14393 0)
                              input__14393_nth_1__
                              (clojure.core/nth input__14393 1)]
                             (if
                              (clojure.core/seq? input__14393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__14393_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14393_nth_0__
                                  1)
                                 input__14393_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14393_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?a input__14393_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?b input__14393_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14393_nth_0__]
                                   (clojure.core/let
                                    [?env input__14393_nth_1__]
                                    (try
                                     [{:tag :meander.math.zeta/+,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14472 [?a ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9238__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9238__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14472 [?b ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9238__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9238__auto__
                                          0)))}]
                                     (catch
                                      java.lang.Exception
                                      e__10178__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10178__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10178__auto__)))))))))
                               (state__15925))
                              (state__15925)))
                            (state__15925))
                           (state__15925)))))
                       (state__15925)))))
                   (state__15925))))
                (state__15925)))
              (state__15925)))
            (state__15925))
           (state__15925))))
        (state__15925
         []
         (clojure.core/letfn
          [(def__15161
            [arg__15184 ?__14399]
            (clojure.core/letfn
             [(state__16089
               []
               (clojure.core/let
                [x__15185 "meander.math.zeta"]
                (if
                 (clojure.core/= ?__14399 x__15185)
                 [?__14399]
                 (state__16090))))
              (state__16090
               []
               (if
                (clojure.core/map? arg__15184)
                (clojure.core/let
                 [VAL__15186 (.valAt arg__15184 :aliases)]
                 (if
                  (clojure.core/map? VAL__15186)
                  (clojure.core/let
                   [X__15188 (clojure.core/set VAL__15186)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15188))
                    (clojure.core/loop
                     [search_space__16091 (clojure.core/seq X__15188)]
                     (if
                      (clojure.core/seq search_space__16091)
                      (clojure.core/let
                       [elem__15189
                        (clojure.core/first search_space__16091)
                        result__16092
                        (clojure.core/let
                         [elem__15189_nth_0__
                          (clojure.core/nth elem__15189 0)
                          elem__15189_nth_1__
                          (clojure.core/nth elem__15189 1)]
                         (if
                          (clojure.core/symbol? elem__15189_nth_0__)
                          (clojure.core/let
                           [X__15191
                            (clojure.core/name elem__15189_nth_0__)]
                           (if
                            (clojure.core/= ?__14399 X__15191)
                            (if
                             (clojure.core/symbol? elem__15189_nth_1__)
                             (clojure.core/let
                              [X__15193
                               (clojure.core/name elem__15189_nth_1__)]
                              (clojure.core/case
                               X__15193
                               ("meander.math.zeta")
                               [?__14399]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16092)
                        (recur (clojure.core/next search_space__16091))
                        result__16092))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16089)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 2)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)]
             (if
              (clojure.core/seq? input__14393_nth_0__)
              (clojure.core/let
               [input__14393_nth_0___l__
                (clojure.core/take 1 input__14393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14393_nth_0___r__
                  (clojure.core/drop 1 input__14393_nth_0__)]
                 (clojure.core/let
                  [input__14393_nth_0___l___nth_0__
                   (clojure.core/nth input__14393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15171
                     (clojure.core/namespace
                      input__14393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14399 X__15171]
                     (clojure.core/let
                      [X__15173
                       (clojure.core/name
                        input__14393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15173
                       ("-")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15161 input__14393_nth_1__ ?__14399)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15926)
                         (clojure.core/let
                          [[?__14399] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14393)
                             2)
                            (clojure.core/let
                             [input__14393_nth_0__
                              (clojure.core/nth input__14393 0)
                              input__14393_nth_1__
                              (clojure.core/nth input__14393 1)]
                             (if
                              (clojure.core/seq? input__14393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__14393_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14393_nth_0__
                                  1)
                                 input__14393_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14393_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?a input__14393_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?b input__14393_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14393_nth_0__]
                                   (clojure.core/let
                                    [?env input__14393_nth_1__]
                                    (try
                                     [{:tag :meander.math.zeta/-,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14472 [?a ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9238__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9238__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14472 [?b ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9238__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9238__auto__
                                          0)))}]
                                     (catch
                                      java.lang.Exception
                                      e__10178__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10178__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10178__auto__)))))))))
                               (state__15926))
                              (state__15926)))
                            (state__15926))
                           (state__15926)))))
                       (state__15926)))))
                   (state__15926))))
                (state__15926)))
              (state__15926)))
            (state__15926))
           (state__15926))))
        (state__15926
         []
         (clojure.core/letfn
          [(def__15195
            [arg__15218 ?__14400]
            (clojure.core/letfn
             [(state__16094
               []
               (clojure.core/let
                [x__15219 "meander.zeta"]
                (if
                 (clojure.core/= ?__14400 x__15219)
                 [?__14400]
                 (state__16095))))
              (state__16095
               []
               (if
                (clojure.core/map? arg__15218)
                (clojure.core/let
                 [VAL__15220 (.valAt arg__15218 :aliases)]
                 (if
                  (clojure.core/map? VAL__15220)
                  (clojure.core/let
                   [X__15222 (clojure.core/set VAL__15220)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15222))
                    (clojure.core/loop
                     [search_space__16096 (clojure.core/seq X__15222)]
                     (if
                      (clojure.core/seq search_space__16096)
                      (clojure.core/let
                       [elem__15223
                        (clojure.core/first search_space__16096)
                        result__16097
                        (clojure.core/let
                         [elem__15223_nth_0__
                          (clojure.core/nth elem__15223 0)
                          elem__15223_nth_1__
                          (clojure.core/nth elem__15223 1)]
                         (if
                          (clojure.core/symbol? elem__15223_nth_0__)
                          (clojure.core/let
                           [X__15225
                            (clojure.core/name elem__15223_nth_0__)]
                           (if
                            (clojure.core/= ?__14400 X__15225)
                            (if
                             (clojure.core/symbol? elem__15223_nth_1__)
                             (clojure.core/let
                              [X__15227
                               (clojure.core/name elem__15223_nth_1__)]
                              (clojure.core/case
                               X__15227
                               ("meander.zeta")
                               [?__14400]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16097)
                        (recur (clojure.core/next search_space__16096))
                        result__16097))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16094)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 2)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)]
             (if
              (clojure.core/seq? input__14393_nth_0__)
              (clojure.core/let
               [input__14393_nth_0___l__
                (clojure.core/take 1 input__14393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14393_nth_0___r__
                  (clojure.core/drop 1 input__14393_nth_0__)]
                 (clojure.core/let
                  [input__14393_nth_0___l___nth_0__
                   (clojure.core/nth input__14393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15205
                     (clojure.core/namespace
                      input__14393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14400 X__15205]
                     (clojure.core/let
                      [X__15207
                       (clojure.core/name
                        input__14393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15207
                       ("with")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15195 input__14393_nth_1__ ?__14400)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15927)
                         (clojure.core/let
                          [[?__14400] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14393)
                             2)
                            (clojure.core/let
                             [input__14393_nth_0__
                              (clojure.core/nth input__14393 0)
                              input__14393_nth_1__
                              (clojure.core/nth input__14393 1)]
                             (if
                              (clojure.core/seq? input__14393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__14393_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14393_nth_0__
                                  1)
                                 input__14393_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14393_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__14393_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__14393_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14393_nth_0__]
                                   (clojure.core/let
                                    [?env input__14393_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__9238__auto__
                                          (CATA__FN__14472
                                           ['meander.dev.parse.zeta/parse-with-bindings
                                            ?bindings
                                            ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__9238__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__9238__auto__
                                           0)))},
                                       :body
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14472
                                          [?body ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9238__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9238__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10178__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10178__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10178__auto__)))))))))
                               (state__15927))
                              (state__15927)))
                            (state__15927))
                           (state__15927)))))
                       (state__15927)))))
                   (state__15927))))
                (state__15927)))
              (state__15927)))
            (state__15927))
           (state__15927))))
        (state__15927
         []
         (clojure.core/letfn
          [(def__15229
            [arg__15252 ?__14401]
            (clojure.core/letfn
             [(state__16099
               []
               (clojure.core/let
                [x__15253 "meander.zeta"]
                (if
                 (clojure.core/= ?__14401 x__15253)
                 [?__14401]
                 (state__16100))))
              (state__16100
               []
               (if
                (clojure.core/map? arg__15252)
                (clojure.core/let
                 [VAL__15254 (.valAt arg__15252 :aliases)]
                 (if
                  (clojure.core/map? VAL__15254)
                  (clojure.core/let
                   [X__15256 (clojure.core/set VAL__15254)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15256))
                    (clojure.core/loop
                     [search_space__16101 (clojure.core/seq X__15256)]
                     (if
                      (clojure.core/seq search_space__16101)
                      (clojure.core/let
                       [elem__15257
                        (clojure.core/first search_space__16101)
                        result__16102
                        (clojure.core/let
                         [elem__15257_nth_0__
                          (clojure.core/nth elem__15257 0)
                          elem__15257_nth_1__
                          (clojure.core/nth elem__15257 1)]
                         (if
                          (clojure.core/symbol? elem__15257_nth_0__)
                          (clojure.core/let
                           [X__15259
                            (clojure.core/name elem__15257_nth_0__)]
                           (if
                            (clojure.core/= ?__14401 X__15259)
                            (if
                             (clojure.core/symbol? elem__15257_nth_1__)
                             (clojure.core/let
                              [X__15261
                               (clojure.core/name elem__15257_nth_1__)]
                              (clojure.core/case
                               X__15261
                               ("meander.zeta")
                               [?__14401]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16102)
                        (recur (clojure.core/next search_space__16101))
                        result__16102))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16099)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 2)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)]
             (if
              (clojure.core/seq? input__14393_nth_0__)
              (clojure.core/let
               [input__14393_nth_0___l__
                (clojure.core/take 1 input__14393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14393_nth_0___r__
                  (clojure.core/drop 1 input__14393_nth_0__)]
                 (clojure.core/let
                  [input__14393_nth_0___l___nth_0__
                   (clojure.core/nth input__14393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15239
                     (clojure.core/namespace
                      input__14393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14401 X__15239]
                     (clojure.core/let
                      [X__15241
                       (clojure.core/name
                        input__14393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15241
                       ("apply")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15229 input__14393_nth_1__ ?__14401)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15928)
                         (clojure.core/let
                          [[?__14401] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14393)
                             2)
                            (clojure.core/let
                             [input__14393_nth_0__
                              (clojure.core/nth input__14393 0)
                              input__14393_nth_1__
                              (clojure.core/nth input__14393 1)]
                             (if
                              (clojure.core/seq? input__14393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__14393_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14393_nth_0__
                                  1)
                                 input__14393_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14393_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__14393_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__14393_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14393_nth_0__]
                                   (clojure.core/let
                                    [?env input__14393_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14472
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9238__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9238__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10178__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10178__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10178__auto__)))))))))
                               (state__15928))
                              (state__15928)))
                            (state__15928))
                           (state__15928)))))
                       (state__15928)))))
                   (state__15928))))
                (state__15928)))
              (state__15928)))
            (state__15928))
           (state__15928))))
        (state__15928
         []
         (clojure.core/letfn
          [(def__15263
            [arg__15288 ?__14402]
            (clojure.core/letfn
             [(state__16104
               []
               (clojure.core/let
                [x__15289 "meander.zeta"]
                (if
                 (clojure.core/= ?__14402 x__15289)
                 [?__14402]
                 (state__16105))))
              (state__16105
               []
               (if
                (clojure.core/map? arg__15288)
                (clojure.core/let
                 [VAL__15290 (.valAt arg__15288 :aliases)]
                 (if
                  (clojure.core/map? VAL__15290)
                  (clojure.core/let
                   [X__15292 (clojure.core/set VAL__15290)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15292))
                    (clojure.core/loop
                     [search_space__16106 (clojure.core/seq X__15292)]
                     (if
                      (clojure.core/seq search_space__16106)
                      (clojure.core/let
                       [elem__15293
                        (clojure.core/first search_space__16106)
                        result__16107
                        (clojure.core/let
                         [elem__15293_nth_0__
                          (clojure.core/nth elem__15293 0)
                          elem__15293_nth_1__
                          (clojure.core/nth elem__15293 1)]
                         (if
                          (clojure.core/symbol? elem__15293_nth_0__)
                          (clojure.core/let
                           [X__15295
                            (clojure.core/name elem__15293_nth_0__)]
                           (if
                            (clojure.core/= ?__14402 X__15295)
                            (if
                             (clojure.core/symbol? elem__15293_nth_1__)
                             (clojure.core/let
                              [X__15297
                               (clojure.core/name elem__15293_nth_1__)]
                              (clojure.core/case
                               X__15297
                               ("meander.zeta")
                               [?__14402]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16107)
                        (recur (clojure.core/next search_space__16106))
                        result__16107))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16104)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 2)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)]
             (if
              (clojure.core/seq? input__14393_nth_0__)
              (clojure.core/let
               [input__14393_nth_0___l__
                (clojure.core/take 1 input__14393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14393_nth_0___r__
                  (clojure.core/drop 1 input__14393_nth_0__)]
                 (clojure.core/let
                  [input__14393_nth_0___l___nth_0__
                   (clojure.core/nth input__14393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15275
                     (clojure.core/namespace
                      input__14393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14402 X__15275]
                     (clojure.core/let
                      [X__15277
                       (clojure.core/name
                        input__14393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15277
                       ("and")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15263 input__14393_nth_1__ ?__14402)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15929)
                         (clojure.core/let
                          [[?__14402] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14393)
                             2)
                            (clojure.core/let
                             [input__14393_nth_0__
                              (clojure.core/nth input__14393 0)
                              input__14393_nth_1__
                              (clojure.core/nth input__14393 1)]
                             (if
                              (clojure.core/seq? input__14393_nth_0__)
                              (clojure.core/let
                               [input__14393_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__14393_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__14393_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__14393_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__14393_nth_0__)]
                                 (clojure.core/let
                                  [!forms
                                   (clojure.core/vec
                                    input__14393_nth_0___r__)]
                                  (clojure.core/let
                                   [?form input__14393_nth_0__]
                                   (clojure.core/let
                                    [?env input__14393_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [!forms__counter
                                        (meander.runtime.zeta/iterator
                                         !forms)]
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14472
                                          ['meander.dev.parse.zeta/make-and
                                           (clojure.core/into
                                            []
                                            (clojure.core/loop
                                             [return__14474
                                              (clojure.core/transient
                                               [])]
                                             (if
                                              (clojure.core/and
                                               (.hasNext
                                                !forms__counter))
                                              (recur
                                               (clojure.core/conj!
                                                return__14474
                                                (clojure.core/let
                                                 [CATA_RESULT__9238__auto__
                                                  (CATA__FN__14472
                                                   [(if
                                                     (.hasNext
                                                      !forms__counter)
                                                     (.next
                                                      !forms__counter))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9238__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9238__auto__
                                                   0)))))
                                              (clojure.core/persistent!
                                               return__14474))))
                                           ?form])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9238__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9238__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__10178__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10178__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10178__auto__))))))))
                                (state__15929)))
                              (state__15929)))
                            (state__15929))
                           (state__15929)))))
                       (state__15929)))))
                   (state__15929))))
                (state__15929)))
              (state__15929)))
            (state__15929))
           (state__15929))))
        (state__15929
         []
         (if
          (clojure.core/vector? input__14393)
          (if
           (clojure.core/= (clojure.core/count input__14393) 3)
           (clojure.core/let
            [input__14393_nth_0__
             (clojure.core/nth input__14393 0)
             input__14393_nth_1__
             (clojure.core/nth input__14393 1)
             input__14393_nth_2__
             (clojure.core/nth input__14393 2)]
            (clojure.core/case
             input__14393_nth_0__
             (meander.dev.parse.zeta/make-and)
             (clojure.core/letfn
              [(state__16109
                []
                (if
                 (clojure.core/vector? input__14393_nth_1__)
                 (clojure.core/case
                  input__14393_nth_1__
                  ([])
                  (clojure.core/let
                   [?form input__14393_nth_2__]
                   [{:tag :error,
                     :message
                     "meander.zeta/and requires 1 or more arguments",
                     :form ?form}])
                  (state__16110))
                 (state__16110)))
               (state__16110
                []
                (clojure.core/case
                 input__14393_nth_2__
                 (nil)
                 (if
                  (clojure.core/vector? input__14393_nth_1__)
                  (if
                   (clojure.core/=
                    (clojure.core/count input__14393_nth_1__)
                    1)
                   (clojure.core/let
                    [input__14393_nth_1___nth_0__
                     (clojure.core/nth input__14393_nth_1__ 0)]
                    (clojure.core/let
                     [?ast-a input__14393_nth_1___nth_0__]
                     [?ast-a]))
                   (state__16111))
                  (state__16111))
                 (state__16111)))
               (state__16111
                []
                (if
                 (clojure.core/vector? input__14393_nth_1__)
                 (clojure.core/letfn
                  [(state__16112
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__14393_nth_1__)
                      1)
                     (clojure.core/let
                      [input__14393_nth_1___nth_0__
                       (clojure.core/nth input__14393_nth_1__ 0)]
                      (clojure.core/let
                       [?ast-a input__14393_nth_1___nth_0__]
                       (clojure.core/let
                        [?form input__14393_nth_2__]
                        (try
                         [(clojure.core/let
                           [CATA_RESULT__9238__auto__
                            (CATA__FN__14472
                             ['meander.dev.parse.zeta/make-and
                              [?ast-a {:tag :pass}]
                              ?form])]
                           (if
                            (meander.runtime.zeta/fail?
                             CATA_RESULT__9238__auto__)
                            (throw (meander.runtime.zeta/fail))
                            (clojure.core/nth
                             CATA_RESULT__9238__auto__
                             0)))]
                         (catch
                          java.lang.Exception
                          e__10178__auto__
                          (if
                           (meander.runtime.zeta/fail?
                            e__10178__auto__)
                           (meander.runtime.zeta/fail)
                           (throw e__10178__auto__)))))))
                     (state__16113)))
                   (state__16113
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__14393_nth_1__)
                      2)
                     (clojure.core/let
                      [input__14393_nth_1___nth_0__
                       (clojure.core/nth input__14393_nth_1__ 0)
                       input__14393_nth_1___nth_1__
                       (clojure.core/nth input__14393_nth_1__ 1)]
                      (clojure.core/let
                       [?ast-a input__14393_nth_1___nth_0__]
                       (clojure.core/let
                        [?ast-b input__14393_nth_1___nth_1__]
                        (clojure.core/let
                         [?form input__14393_nth_2__]
                         [{:tag :and,
                           :left ?ast-a,
                           :right ?ast-b,
                           :form ?form}]))))
                     (state__16114)))
                   (state__16114
                    []
                    (clojure.core/loop
                     [search_space__16115
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__14393_nth_1__)]
                     (if
                      (clojure.core/seq search_space__16115)
                      (clojure.core/let
                       [input__14393_nth_1___parts__
                        (clojure.core/first search_space__16115)
                        result__16116
                        (clojure.core/let
                         [input__14393_nth_1___l__
                          (clojure.core/nth
                           input__14393_nth_1___parts__
                           0)
                          input__14393_nth_1___r__
                          (clojure.core/nth
                           input__14393_nth_1___parts__
                           1)]
                         (clojure.core/letfn
                          [(state__16118
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__8099__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__14393_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__15324]
                                 (clojure.core/let
                                  [input__15324_nth_0__
                                   (clojure.core/nth input__15324 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__15324_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__15317
                                   (clojure.core/count
                                    input__14393_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__15317]
                                   (clojure.core/let
                                    [X__15321
                                     (clojure.core/count
                                      input__14393_nth_1___r__)]
                                    (if
                                     (clojure.core/= ?n X__15321)
                                     (clojure.core/let
                                      [!asts-2 []]
                                      (clojure.core/let
                                       [ret__8099__auto__
                                        (meander.runtime.zeta/epsilon-run-star-1
                                         input__14393_nth_1___r__
                                         [!asts-2 !asts-1]
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]
                                           input__15322]
                                          (clojure.core/let
                                           [input__15322_nth_0__
                                            (clojure.core/nth
                                             input__15322
                                             0)]
                                           (clojure.core/let
                                            [!asts-2
                                             (clojure.core/conj
                                              !asts-2
                                              input__15322_nth_0__)]
                                            [!asts-2 !asts-1])))
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]]
                                          (clojure.core/let
                                           [?form input__14393_nth_2__]
                                           (try
                                            [(clojure.core/let
                                              [!asts-1__counter
                                               (meander.runtime.zeta/iterator
                                                !asts-1)
                                               !asts-2__counter
                                               (meander.runtime.zeta/iterator
                                                !asts-2)]
                                              (clojure.core/let
                                               [CATA_RESULT__9238__auto__
                                                (CATA__FN__14472
                                                 ['meander.dev.parse.zeta/make-and
                                                  [(clojure.core/let
                                                    [CATA_RESULT__9238__auto__
                                                     (CATA__FN__14472
                                                      ['meander.dev.parse.zeta/make-and
                                                       (clojure.core/into
                                                        []
                                                        (clojure.core/vec
                                                         (clojure.core/iterator-seq
                                                          !asts-1__counter)))
                                                       nil])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__9238__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__9238__auto__
                                                      0)))
                                                   (clojure.core/let
                                                    [CATA_RESULT__9238__auto__
                                                     (CATA__FN__14472
                                                      ['meander.dev.parse.zeta/make-and
                                                       (clojure.core/into
                                                        []
                                                        (clojure.core/vec
                                                         (clojure.core/iterator-seq
                                                          !asts-2__counter)))
                                                       nil])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__9238__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__9238__auto__
                                                      0)))]
                                                  ?form])]
                                               (if
                                                (meander.runtime.zeta/fail?
                                                 CATA_RESULT__9238__auto__)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 CATA_RESULT__9238__auto__
                                                 0))))]
                                            (catch
                                             java.lang.Exception
                                             e__10178__auto__
                                             (if
                                              (meander.runtime.zeta/fail?
                                               e__10178__auto__)
                                              (meander.runtime.zeta/fail)
                                              (throw
                                               e__10178__auto__)))))))]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         ret__8099__auto__)
                                        (state__16119)
                                        ret__8099__auto__)))
                                     (state__16119)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__8099__auto__)
                               (state__16119)
                               ret__8099__auto__))))
                           (state__16119
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__8099__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__14393_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__15340]
                                 (clojure.core/let
                                  [input__15340_nth_0__
                                   (clojure.core/nth input__15340 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__15340_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__15331
                                   (clojure.core/count
                                    input__14393_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__15331]
                                   (clojure.core/let
                                    [input__14393_nth_1___r___l__
                                     (clojure.core/subvec
                                      input__14393_nth_1___r__
                                      0
                                      (clojure.core/min
                                       (clojure.core/count
                                        input__14393_nth_1___r__)
                                       1))]
                                    (if
                                     (clojure.core/=
                                      (clojure.core/count
                                       input__14393_nth_1___r___l__)
                                      1)
                                     (clojure.core/let
                                      [input__14393_nth_1___r___r__
                                       (clojure.core/subvec
                                        input__14393_nth_1___r__
                                        1)]
                                      (clojure.core/let
                                       [input__14393_nth_1___r___l___nth_0__
                                        (clojure.core/nth
                                         input__14393_nth_1___r___l__
                                         0)]
                                       (clojure.core/let
                                        [?ast
                                         input__14393_nth_1___r___l___nth_0__]
                                        (clojure.core/let
                                         [X__15337
                                          (clojure.core/count
                                           input__14393_nth_1___r___r__)]
                                         (if
                                          (clojure.core/= ?n X__15337)
                                          (clojure.core/let
                                           [!asts-2 []]
                                           (clojure.core/let
                                            [ret__8099__auto__
                                             (meander.runtime.zeta/epsilon-run-star-1
                                              input__14393_nth_1___r___r__
                                              [!asts-2 !asts-1]
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]
                                                input__15338]
                                               (clojure.core/let
                                                [input__15338_nth_0__
                                                 (clojure.core/nth
                                                  input__15338
                                                  0)]
                                                (clojure.core/let
                                                 [!asts-2
                                                  (clojure.core/conj
                                                   !asts-2
                                                   input__15338_nth_0__)]
                                                 [!asts-2 !asts-1])))
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]]
                                               (clojure.core/let
                                                [?form
                                                 input__14393_nth_2__]
                                                (try
                                                 [(clojure.core/let
                                                   [!asts-1__counter
                                                    (meander.runtime.zeta/iterator
                                                     !asts-1)
                                                    !asts-2__counter
                                                    (meander.runtime.zeta/iterator
                                                     !asts-2)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__9238__auto__
                                                     (CATA__FN__14472
                                                      ['meander.dev.parse.zeta/make-and
                                                       [(clojure.core/let
                                                         [CATA_RESULT__9238__auto__
                                                          (CATA__FN__14472
                                                           ['meander.dev.parse.zeta/make-and
                                                            (clojure.core/into
                                                             []
                                                             (clojure.core/concat
                                                              (clojure.core/vec
                                                               (clojure.core/iterator-seq
                                                                !asts-1__counter))
                                                              (clojure.core/list
                                                               ?ast)))
                                                            nil])]
                                                         (if
                                                          (meander.runtime.zeta/fail?
                                                           CATA_RESULT__9238__auto__)
                                                          (throw
                                                           (meander.runtime.zeta/fail))
                                                          (clojure.core/nth
                                                           CATA_RESULT__9238__auto__
                                                           0)))
                                                        (clojure.core/let
                                                         [CATA_RESULT__9238__auto__
                                                          (CATA__FN__14472
                                                           ['meander.dev.parse.zeta/make-and
                                                            (clojure.core/into
                                                             []
                                                             (clojure.core/vec
                                                              (clojure.core/iterator-seq
                                                               !asts-2__counter)))
                                                            nil])]
                                                         (if
                                                          (meander.runtime.zeta/fail?
                                                           CATA_RESULT__9238__auto__)
                                                          (throw
                                                           (meander.runtime.zeta/fail))
                                                          (clojure.core/nth
                                                           CATA_RESULT__9238__auto__
                                                           0)))]
                                                       ?form])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__9238__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__9238__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__10178__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__10178__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__10178__auto__)))))))]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              ret__8099__auto__)
                                             (meander.runtime.zeta/fail)
                                             ret__8099__auto__)))
                                          (meander.runtime.zeta/fail))))))
                                     (meander.runtime.zeta/fail)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__8099__auto__)
                               (meander.runtime.zeta/fail)
                               ret__8099__auto__))))]
                          (state__16118)))]
                       (if
                        (meander.runtime.zeta/fail? result__16116)
                        (recur (clojure.core/next search_space__16115))
                        result__16116))
                      (state__15930))))]
                  (state__16112))
                 (state__15930)))]
              (state__16109))
             (state__15930)))
           (state__15930))
          (state__15930)))
        (state__15930
         []
         (clojure.core/letfn
          [(def__15343
            [arg__15366 ?__14403]
            (clojure.core/letfn
             [(state__16124
               []
               (clojure.core/let
                [x__15367 "meander.zeta"]
                (if
                 (clojure.core/= ?__14403 x__15367)
                 [?__14403]
                 (state__16125))))
              (state__16125
               []
               (if
                (clojure.core/map? arg__15366)
                (clojure.core/let
                 [VAL__15368 (.valAt arg__15366 :aliases)]
                 (if
                  (clojure.core/map? VAL__15368)
                  (clojure.core/let
                   [X__15370 (clojure.core/set VAL__15368)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15370))
                    (clojure.core/loop
                     [search_space__16126 (clojure.core/seq X__15370)]
                     (if
                      (clojure.core/seq search_space__16126)
                      (clojure.core/let
                       [elem__15371
                        (clojure.core/first search_space__16126)
                        result__16127
                        (clojure.core/let
                         [elem__15371_nth_0__
                          (clojure.core/nth elem__15371 0)
                          elem__15371_nth_1__
                          (clojure.core/nth elem__15371 1)]
                         (if
                          (clojure.core/symbol? elem__15371_nth_0__)
                          (clojure.core/let
                           [X__15373
                            (clojure.core/name elem__15371_nth_0__)]
                           (if
                            (clojure.core/= ?__14403 X__15373)
                            (if
                             (clojure.core/symbol? elem__15371_nth_1__)
                             (clojure.core/let
                              [X__15375
                               (clojure.core/name elem__15371_nth_1__)]
                              (clojure.core/case
                               X__15375
                               ("meander.zeta")
                               [?__14403]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16127)
                        (recur (clojure.core/next search_space__16126))
                        result__16127))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16124)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 2)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)]
             (if
              (clojure.core/seq? input__14393_nth_0__)
              (clojure.core/let
               [input__14393_nth_0___l__
                (clojure.core/take 1 input__14393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14393_nth_0___r__
                  (clojure.core/drop 1 input__14393_nth_0__)]
                 (clojure.core/let
                  [input__14393_nth_0___l___nth_0__
                   (clojure.core/nth input__14393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15353
                     (clojure.core/namespace
                      input__14393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14403 X__15353]
                     (clojure.core/let
                      [X__15355
                       (clojure.core/name
                        input__14393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15355
                       ("cata")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15343 input__14393_nth_1__ ?__14403)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15931)
                         (clojure.core/let
                          [[?__14403] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14393)
                             2)
                            (clojure.core/let
                             [input__14393_nth_0__
                              (clojure.core/nth input__14393 0)
                              input__14393_nth_1__
                              (clojure.core/nth input__14393 1)]
                             (if
                              (clojure.core/seq? input__14393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__14393_nth_0__)
                                2)
                               (clojure.core/let
                                [input__14393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14393_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__14393_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__14393_nth_0__]
                                  (clojure.core/let
                                   [?env input__14393_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9238__auto__
                                        (CATA__FN__14472
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9238__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9238__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__10178__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10178__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10178__auto__))))))))
                               (state__15931))
                              (state__15931)))
                            (state__15931))
                           (state__15931)))))
                       (state__15931)))))
                   (state__15931))))
                (state__15931)))
              (state__15931)))
            (state__15931))
           (state__15931))))
        (state__15931
         []
         (clojure.core/letfn
          [(def__15377
            [arg__15400 ?__14404]
            (clojure.core/letfn
             [(state__16129
               []
               (clojure.core/let
                [x__15401 "meander.zeta"]
                (if
                 (clojure.core/= ?__14404 x__15401)
                 [?__14404]
                 (state__16130))))
              (state__16130
               []
               (if
                (clojure.core/map? arg__15400)
                (clojure.core/let
                 [VAL__15402 (.valAt arg__15400 :aliases)]
                 (if
                  (clojure.core/map? VAL__15402)
                  (clojure.core/let
                   [X__15404 (clojure.core/set VAL__15402)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15404))
                    (clojure.core/loop
                     [search_space__16131 (clojure.core/seq X__15404)]
                     (if
                      (clojure.core/seq search_space__16131)
                      (clojure.core/let
                       [elem__15405
                        (clojure.core/first search_space__16131)
                        result__16132
                        (clojure.core/let
                         [elem__15405_nth_0__
                          (clojure.core/nth elem__15405 0)
                          elem__15405_nth_1__
                          (clojure.core/nth elem__15405 1)]
                         (if
                          (clojure.core/symbol? elem__15405_nth_0__)
                          (clojure.core/let
                           [X__15407
                            (clojure.core/name elem__15405_nth_0__)]
                           (if
                            (clojure.core/= ?__14404 X__15407)
                            (if
                             (clojure.core/symbol? elem__15405_nth_1__)
                             (clojure.core/let
                              [X__15409
                               (clojure.core/name elem__15405_nth_1__)]
                              (clojure.core/case
                               X__15409
                               ("meander.zeta")
                               [?__14404]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16132)
                        (recur (clojure.core/next search_space__16131))
                        result__16132))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16129)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 2)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)]
             (if
              (clojure.core/seq? input__14393_nth_0__)
              (clojure.core/let
               [input__14393_nth_0___l__
                (clojure.core/take 1 input__14393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14393_nth_0___r__
                  (clojure.core/drop 1 input__14393_nth_0__)]
                 (clojure.core/let
                  [input__14393_nth_0___l___nth_0__
                   (clojure.core/nth input__14393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15387
                     (clojure.core/namespace
                      input__14393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14404 X__15387]
                     (clojure.core/let
                      [X__15389
                       (clojure.core/name
                        input__14393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15389
                       ("fold")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15377 input__14393_nth_1__ ?__14404)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15932)
                         (clojure.core/let
                          [[?__14404] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14393)
                             2)
                            (clojure.core/let
                             [input__14393_nth_0__
                              (clojure.core/nth input__14393 0)
                              input__14393_nth_1__
                              (clojure.core/nth input__14393 1)]
                             (if
                              (clojure.core/seq? input__14393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__14393_nth_0__)
                                4)
                               (clojure.core/let
                                [input__14393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14393_nth_0__
                                  1)
                                 input__14393_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14393_nth_0__
                                  2)
                                 input__14393_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__14393_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__14393_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__14393_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__14393_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__14393_nth_0__]
                                    (clojure.core/let
                                     [?env input__14393_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14472
                                          ['meander.dev.parse.zeta/make-fold
                                           (clojure.core/let
                                            [CATA_RESULT__9238__auto__
                                             (CATA__FN__14472
                                              [?mutable-variable
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9238__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9238__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__9238__auto__
                                             (CATA__FN__14472
                                              [?initial-value ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__9238__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__9238__auto__
                                              0)))
                                           ?fold-function
                                           ?form])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9238__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9238__auto__
                                          0)))]
                                      (catch
                                       java.lang.Exception
                                       e__10178__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__10178__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__10178__auto__))))))))))
                               (state__15932))
                              (state__15932)))
                            (state__15932))
                           (state__15932)))))
                       (state__15932)))))
                   (state__15932))))
                (state__15932)))
              (state__15932)))
            (state__15932))
           (state__15932))))
        (state__15932
         []
         (if
          (clojure.core/vector? input__14393)
          (if
           (clojure.core/= (clojure.core/count input__14393) 5)
           (clojure.core/let
            [input__14393_nth_0__
             (clojure.core/nth input__14393 0)
             input__14393_nth_1__
             (clojure.core/nth input__14393 1)
             input__14393_nth_2__
             (clojure.core/nth input__14393 2)
             input__14393_nth_3__
             (clojure.core/nth input__14393 3)
             input__14393_nth_4__
             (clojure.core/nth input__14393 4)]
            (clojure.core/case
             input__14393_nth_0__
             (meander.dev.parse.zeta/make-fold)
             (if
              (clojure.core/map? input__14393_nth_1__)
              (clojure.core/let
               [VAL__15412 (.valAt input__14393_nth_1__ :tag)]
               (clojure.core/case
                VAL__15412
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__14393_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__14393_nth_2__]
                  (clojure.core/let
                   [?fold-function input__14393_nth_3__]
                   (clojure.core/let
                    [?form input__14393_nth_4__]
                    [{:tag :fold,
                      :variable ?variable-ast,
                      :initial-value ?initial-value-ast,
                      :fold-function
                      {:tag :host-expression, :form ?fold-function},
                      :form ?form}]))))
                (state__15933)))
              (state__15933))
             (state__15933)))
           (state__15933))
          (state__15933)))
        (state__15933
         []
         (clojure.core/letfn
          [(def__15414
            [arg__15437 ?__14405]
            (clojure.core/letfn
             [(state__16134
               []
               (clojure.core/let
                [x__15438 "meander.zeta"]
                (if
                 (clojure.core/= ?__14405 x__15438)
                 [?__14405]
                 (state__16135))))
              (state__16135
               []
               (if
                (clojure.core/map? arg__15437)
                (clojure.core/let
                 [VAL__15439 (.valAt arg__15437 :aliases)]
                 (if
                  (clojure.core/map? VAL__15439)
                  (clojure.core/let
                   [X__15441 (clojure.core/set VAL__15439)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15441))
                    (clojure.core/loop
                     [search_space__16136 (clojure.core/seq X__15441)]
                     (if
                      (clojure.core/seq search_space__16136)
                      (clojure.core/let
                       [elem__15442
                        (clojure.core/first search_space__16136)
                        result__16137
                        (clojure.core/let
                         [elem__15442_nth_0__
                          (clojure.core/nth elem__15442 0)
                          elem__15442_nth_1__
                          (clojure.core/nth elem__15442 1)]
                         (if
                          (clojure.core/symbol? elem__15442_nth_0__)
                          (clojure.core/let
                           [X__15444
                            (clojure.core/name elem__15442_nth_0__)]
                           (if
                            (clojure.core/= ?__14405 X__15444)
                            (if
                             (clojure.core/symbol? elem__15442_nth_1__)
                             (clojure.core/let
                              [X__15446
                               (clojure.core/name elem__15442_nth_1__)]
                              (clojure.core/case
                               X__15446
                               ("meander.zeta")
                               [?__14405]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16137)
                        (recur (clojure.core/next search_space__16136))
                        result__16137))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16134)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 2)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)]
             (if
              (clojure.core/seq? input__14393_nth_0__)
              (clojure.core/let
               [input__14393_nth_0___l__
                (clojure.core/take 1 input__14393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14393_nth_0___r__
                  (clojure.core/drop 1 input__14393_nth_0__)]
                 (clojure.core/let
                  [input__14393_nth_0___l___nth_0__
                   (clojure.core/nth input__14393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15424
                     (clojure.core/namespace
                      input__14393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14405 X__15424]
                     (clojure.core/let
                      [X__15426
                       (clojure.core/name
                        input__14393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15426
                       ("keyword")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15414 input__14393_nth_1__ ?__14405)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15934)
                         (clojure.core/let
                          [[?__14405] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14393)
                             2)
                            (clojure.core/let
                             [input__14393_nth_0__
                              (clojure.core/nth input__14393 0)
                              input__14393_nth_1__
                              (clojure.core/nth input__14393 1)]
                             (if
                              (clojure.core/seq? input__14393_nth_0__)
                              (clojure.core/let
                               [input__14393_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__14393_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__14393_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__14393_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__14393_nth_0__)]
                                 (clojure.core/let
                                  [?keyword-args
                                   input__14393_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__14393_nth_0__]
                                   (clojure.core/let
                                    [?env input__14393_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [CATA_RESULT__9238__auto__
                                        (CATA__FN__14472
                                         ['meander.dev.parse.zeta/make-keyword
                                          (clojure.core/into
                                           []
                                           ?keyword-args)
                                          ?form
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9238__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9238__auto__
                                         0)))]
                                     (catch
                                      java.lang.Exception
                                      e__10178__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10178__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10178__auto__))))))))
                                (state__15934)))
                              (state__15934)))
                            (state__15934))
                           (state__15934)))))
                       (state__15934)))))
                   (state__15934))))
                (state__15934)))
              (state__15934)))
            (state__15934))
           (state__15934))))
        (state__15934
         []
         (if
          (clojure.core/vector? input__14393)
          (if
           (clojure.core/= (clojure.core/count input__14393) 4)
           (clojure.core/let
            [input__14393_nth_0__
             (clojure.core/nth input__14393 0)
             input__14393_nth_1__
             (clojure.core/nth input__14393 1)
             input__14393_nth_2__
             (clojure.core/nth input__14393 2)]
            (clojure.core/letfn
             [(state__16139
               []
               (clojure.core/case
                input__14393_nth_0__
                (meander.dev.parse.zeta/make-keyword)
                (if
                 (clojure.core/vector? input__14393_nth_1__)
                 (clojure.core/case
                  input__14393_nth_1__
                  ([])
                  (clojure.core/let
                   [?form input__14393_nth_2__]
                   [{:tag :keyword,
                     :namespace {:tag :wildcard},
                     :name {:tag :wildcard},
                     :form ?form}])
                  (state__16140))
                 (state__16140))
                (state__16140)))
              (state__16140
               []
               (clojure.core/let
                [input__14393_nth_3__
                 (clojure.core/nth input__14393 3)]
                (clojure.core/case
                 input__14393_nth_0__
                 (meander.dev.parse.zeta/make-keyword)
                 (if
                  (clojure.core/vector? input__14393_nth_1__)
                  (clojure.core/letfn
                   [(state__16141
                     []
                     (if
                      (clojure.core/=
                       (clojure.core/count input__14393_nth_1__)
                       1)
                      (clojure.core/let
                       [input__14393_nth_1___nth_0__
                        (clojure.core/nth input__14393_nth_1__ 0)]
                       (clojure.core/let
                        [?name input__14393_nth_1___nth_0__]
                        (clojure.core/let
                         [?form input__14393_nth_2__]
                         (clojure.core/let
                          [?env input__14393_nth_3__]
                          (try
                           [{:tag :keyword,
                             :namespace {:tag :wildcard},
                             :name
                             (clojure.core/let
                              [CATA_RESULT__9238__auto__
                               (CATA__FN__14472 [?name ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__9238__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__9238__auto__
                                0))),
                             :form ?form}]
                           (catch
                            java.lang.Exception
                            e__10178__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__10178__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__10178__auto__))))))))
                      (state__16142)))
                    (state__16142
                     []
                     (if
                      (clojure.core/=
                       (clojure.core/count input__14393_nth_1__)
                       2)
                      (clojure.core/let
                       [input__14393_nth_1___nth_0__
                        (clojure.core/nth input__14393_nth_1__ 0)
                        input__14393_nth_1___nth_1__
                        (clojure.core/nth input__14393_nth_1__ 1)]
                       (clojure.core/let
                        [?namespace input__14393_nth_1___nth_0__]
                        (clojure.core/let
                         [?name input__14393_nth_1___nth_1__]
                         (clojure.core/let
                          [?form input__14393_nth_2__]
                          (clojure.core/let
                           [?env input__14393_nth_3__]
                           (try
                            [{:tag :keyword,
                              :namespace
                              (clojure.core/let
                               [CATA_RESULT__9238__auto__
                                (CATA__FN__14472 [?namespace ?env])]
                               (if
                                (meander.runtime.zeta/fail?
                                 CATA_RESULT__9238__auto__)
                                (throw (meander.runtime.zeta/fail))
                                (clojure.core/nth
                                 CATA_RESULT__9238__auto__
                                 0))),
                              :name
                              (clojure.core/let
                               [CATA_RESULT__9238__auto__
                                (CATA__FN__14472 [?name ?env])]
                               (if
                                (meander.runtime.zeta/fail?
                                 CATA_RESULT__9238__auto__)
                                (throw (meander.runtime.zeta/fail))
                                (clojure.core/nth
                                 CATA_RESULT__9238__auto__
                                 0))),
                              :form ?form}]
                            (catch
                             java.lang.Exception
                             e__10178__auto__
                             (if
                              (meander.runtime.zeta/fail?
                               e__10178__auto__)
                              (meander.runtime.zeta/fail)
                              (throw e__10178__auto__)))))))))
                      (state__15935)))]
                   (state__16141))
                  (state__15935))
                 (state__15935))))]
             (state__16139)))
           (state__15935))
          (state__15935)))
        (state__15935
         []
         (clojure.core/letfn
          [(def__15458
            [arg__15481 ?__14406]
            (clojure.core/letfn
             [(state__16143
               []
               (clojure.core/let
                [x__15482 "meander.zeta"]
                (if
                 (clojure.core/= ?__14406 x__15482)
                 [?__14406]
                 (state__16144))))
              (state__16144
               []
               (if
                (clojure.core/map? arg__15481)
                (clojure.core/let
                 [VAL__15483 (.valAt arg__15481 :aliases)]
                 (if
                  (clojure.core/map? VAL__15483)
                  (clojure.core/let
                   [X__15485 (clojure.core/set VAL__15483)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15485))
                    (clojure.core/loop
                     [search_space__16145 (clojure.core/seq X__15485)]
                     (if
                      (clojure.core/seq search_space__16145)
                      (clojure.core/let
                       [elem__15486
                        (clojure.core/first search_space__16145)
                        result__16146
                        (clojure.core/let
                         [elem__15486_nth_0__
                          (clojure.core/nth elem__15486 0)
                          elem__15486_nth_1__
                          (clojure.core/nth elem__15486 1)]
                         (if
                          (clojure.core/symbol? elem__15486_nth_0__)
                          (clojure.core/let
                           [X__15488
                            (clojure.core/name elem__15486_nth_0__)]
                           (if
                            (clojure.core/= ?__14406 X__15488)
                            (if
                             (clojure.core/symbol? elem__15486_nth_1__)
                             (clojure.core/let
                              [X__15490
                               (clojure.core/name elem__15486_nth_1__)]
                              (clojure.core/case
                               X__15490
                               ("meander.zeta")
                               [?__14406]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16146)
                        (recur (clojure.core/next search_space__16145))
                        result__16146))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16143)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 2)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)]
             (if
              (clojure.core/seq? input__14393_nth_0__)
              (clojure.core/let
               [input__14393_nth_0___l__
                (clojure.core/take 1 input__14393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14393_nth_0___r__
                  (clojure.core/drop 1 input__14393_nth_0__)]
                 (clojure.core/let
                  [input__14393_nth_0___l___nth_0__
                   (clojure.core/nth input__14393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15468
                     (clojure.core/namespace
                      input__14393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14406 X__15468]
                     (clojure.core/let
                      [X__15470
                       (clojure.core/name
                        input__14393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15470
                       ("let")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15458 input__14393_nth_1__ ?__14406)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15936)
                         (clojure.core/let
                          [[?__14406] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14393)
                             2)
                            (clojure.core/let
                             [input__14393_nth_0__
                              (clojure.core/nth input__14393 0)
                              input__14393_nth_1__
                              (clojure.core/nth input__14393 1)]
                             (if
                              (clojure.core/seq? input__14393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__14393_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14393_nth_0__
                                  1)
                                 input__14393_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14393_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__14393_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?expression
                                   input__14393_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14393_nth_0__]
                                   (clojure.core/let
                                    [?env input__14393_nth_1__]
                                    (try
                                     [{:tag :let,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14472
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9238__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9238__auto__
                                          0))),
                                       :expression
                                       {:tag :host-expression,
                                        :form ?expression},
                                       :next {:tag :pass}}]
                                     (catch
                                      java.lang.Exception
                                      e__10178__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10178__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10178__auto__)))))))))
                               (state__15936))
                              (state__15936)))
                            (state__15936))
                           (state__15936)))))
                       (state__15936)))))
                   (state__15936))))
                (state__15936)))
              (state__15936)))
            (state__15936))
           (state__15936))))
        (state__15936
         []
         (clojure.core/letfn
          [(def__15492
            [arg__15515 ?__14407]
            (clojure.core/letfn
             [(state__16148
               []
               (clojure.core/let
                [x__15516 "meander.zeta"]
                (if
                 (clojure.core/= ?__14407 x__15516)
                 [?__14407]
                 (state__16149))))
              (state__16149
               []
               (if
                (clojure.core/map? arg__15515)
                (clojure.core/let
                 [VAL__15517 (.valAt arg__15515 :aliases)]
                 (if
                  (clojure.core/map? VAL__15517)
                  (clojure.core/let
                   [X__15519 (clojure.core/set VAL__15517)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15519))
                    (clojure.core/loop
                     [search_space__16150 (clojure.core/seq X__15519)]
                     (if
                      (clojure.core/seq search_space__16150)
                      (clojure.core/let
                       [elem__15520
                        (clojure.core/first search_space__16150)
                        result__16151
                        (clojure.core/let
                         [elem__15520_nth_0__
                          (clojure.core/nth elem__15520 0)
                          elem__15520_nth_1__
                          (clojure.core/nth elem__15520 1)]
                         (if
                          (clojure.core/symbol? elem__15520_nth_0__)
                          (clojure.core/let
                           [X__15522
                            (clojure.core/name elem__15520_nth_0__)]
                           (if
                            (clojure.core/= ?__14407 X__15522)
                            (if
                             (clojure.core/symbol? elem__15520_nth_1__)
                             (clojure.core/let
                              [X__15524
                               (clojure.core/name elem__15520_nth_1__)]
                              (clojure.core/case
                               X__15524
                               ("meander.zeta")
                               [?__14407]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16151)
                        (recur (clojure.core/next search_space__16150))
                        result__16151))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16148)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 2)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)]
             (if
              (clojure.core/seq? input__14393_nth_0__)
              (clojure.core/let
               [input__14393_nth_0___l__
                (clojure.core/take 1 input__14393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14393_nth_0___r__
                  (clojure.core/drop 1 input__14393_nth_0__)]
                 (clojure.core/let
                  [input__14393_nth_0___l___nth_0__
                   (clojure.core/nth input__14393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15502
                     (clojure.core/namespace
                      input__14393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14407 X__15502]
                     (clojure.core/let
                      [X__15504
                       (clojure.core/name
                        input__14393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15504
                       ("let")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15492 input__14393_nth_1__ ?__14407)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15937)
                         (clojure.core/let
                          [[?__14407] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14393)
                             2)
                            (clojure.core/let
                             [input__14393_nth_0__
                              (clojure.core/nth input__14393 0)
                              input__14393_nth_1__
                              (clojure.core/nth input__14393 1)]
                             (if
                              (clojure.core/seq? input__14393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__14393_nth_0__)
                                4)
                               (clojure.core/let
                                [input__14393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14393_nth_0__
                                  1)
                                 input__14393_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14393_nth_0__
                                  2)
                                 input__14393_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__14393_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?pattern
                                  input__14393_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?expression
                                   input__14393_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?next input__14393_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__14393_nth_0__]
                                    (clojure.core/let
                                     [?env input__14393_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__9238__auto__
                                          (CATA__FN__14472
                                           [?pattern ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__9238__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__9238__auto__
                                           0))),
                                        :expression
                                        {:tag :host-expression,
                                         :form ?expression},
                                        :next
                                        (clojure.core/let
                                         [CATA_RESULT__9238__auto__
                                          (CATA__FN__14472
                                           [?next ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__9238__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__9238__auto__
                                           0)))}]
                                      (catch
                                       java.lang.Exception
                                       e__10178__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__10178__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__10178__auto__))))))))))
                               (state__15937))
                              (state__15937)))
                            (state__15937))
                           (state__15937)))))
                       (state__15937)))))
                   (state__15937))))
                (state__15937)))
              (state__15937)))
            (state__15937))
           (state__15937))))
        (state__15937
         []
         (clojure.core/letfn
          [(def__15526
            [arg__15549 ?__14408]
            (clojure.core/letfn
             [(state__16153
               []
               (clojure.core/let
                [x__15550 "meander.zeta"]
                (if
                 (clojure.core/= ?__14408 x__15550)
                 [?__14408]
                 (state__16154))))
              (state__16154
               []
               (if
                (clojure.core/map? arg__15549)
                (clojure.core/let
                 [VAL__15551 (.valAt arg__15549 :aliases)]
                 (if
                  (clojure.core/map? VAL__15551)
                  (clojure.core/let
                   [X__15553 (clojure.core/set VAL__15551)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15553))
                    (clojure.core/loop
                     [search_space__16155 (clojure.core/seq X__15553)]
                     (if
                      (clojure.core/seq search_space__16155)
                      (clojure.core/let
                       [elem__15554
                        (clojure.core/first search_space__16155)
                        result__16156
                        (clojure.core/let
                         [elem__15554_nth_0__
                          (clojure.core/nth elem__15554 0)
                          elem__15554_nth_1__
                          (clojure.core/nth elem__15554 1)]
                         (if
                          (clojure.core/symbol? elem__15554_nth_0__)
                          (clojure.core/let
                           [X__15556
                            (clojure.core/name elem__15554_nth_0__)]
                           (if
                            (clojure.core/= ?__14408 X__15556)
                            (if
                             (clojure.core/symbol? elem__15554_nth_1__)
                             (clojure.core/let
                              [X__15558
                               (clojure.core/name elem__15554_nth_1__)]
                              (clojure.core/case
                               X__15558
                               ("meander.zeta")
                               [?__14408]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16156)
                        (recur (clojure.core/next search_space__16155))
                        result__16156))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16153)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 2)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)]
             (if
              (clojure.core/seq? input__14393_nth_0__)
              (clojure.core/let
               [input__14393_nth_0___l__
                (clojure.core/take 1 input__14393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14393_nth_0___r__
                  (clojure.core/drop 1 input__14393_nth_0__)]
                 (clojure.core/let
                  [input__14393_nth_0___l___nth_0__
                   (clojure.core/nth input__14393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15536
                     (clojure.core/namespace
                      input__14393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14408 X__15536]
                     (clojure.core/let
                      [X__15538
                       (clojure.core/name
                        input__14393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15538
                       ("not")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15526 input__14393_nth_1__ ?__14408)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15938)
                         (clojure.core/let
                          [[?__14408] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14393)
                             2)
                            (clojure.core/let
                             [input__14393_nth_0__
                              (clojure.core/nth input__14393 0)
                              input__14393_nth_1__
                              (clojure.core/nth input__14393 1)]
                             (if
                              (clojure.core/seq? input__14393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__14393_nth_0__)
                                2)
                               (clojure.core/let
                                [input__14393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14393_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__14393_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__14393_nth_0__]
                                  (clojure.core/let
                                   [?env input__14393_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__9238__auto__
                                        (CATA__FN__14472
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9238__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9238__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__10178__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__10178__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__10178__auto__))))))))
                               (state__15938))
                              (state__15938)))
                            (state__15938))
                           (state__15938)))))
                       (state__15938)))))
                   (state__15938))))
                (state__15938)))
              (state__15938)))
            (state__15938))
           (state__15938))))
        (state__15938
         []
         (clojure.core/letfn
          [(def__15560
            [arg__15585 ?__14409]
            (clojure.core/letfn
             [(state__16158
               []
               (clojure.core/let
                [x__15586 "meander.zeta"]
                (if
                 (clojure.core/= ?__14409 x__15586)
                 [?__14409]
                 (state__16159))))
              (state__16159
               []
               (if
                (clojure.core/map? arg__15585)
                (clojure.core/let
                 [VAL__15587 (.valAt arg__15585 :aliases)]
                 (if
                  (clojure.core/map? VAL__15587)
                  (clojure.core/let
                   [X__15589 (clojure.core/set VAL__15587)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15589))
                    (clojure.core/loop
                     [search_space__16160 (clojure.core/seq X__15589)]
                     (if
                      (clojure.core/seq search_space__16160)
                      (clojure.core/let
                       [elem__15590
                        (clojure.core/first search_space__16160)
                        result__16161
                        (clojure.core/let
                         [elem__15590_nth_0__
                          (clojure.core/nth elem__15590 0)
                          elem__15590_nth_1__
                          (clojure.core/nth elem__15590 1)]
                         (if
                          (clojure.core/symbol? elem__15590_nth_0__)
                          (clojure.core/let
                           [X__15592
                            (clojure.core/name elem__15590_nth_0__)]
                           (if
                            (clojure.core/= ?__14409 X__15592)
                            (if
                             (clojure.core/symbol? elem__15590_nth_1__)
                             (clojure.core/let
                              [X__15594
                               (clojure.core/name elem__15590_nth_1__)]
                              (clojure.core/case
                               X__15594
                               ("meander.zeta")
                               [?__14409]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16161)
                        (recur (clojure.core/next search_space__16160))
                        result__16161))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16158)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 2)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)]
             (if
              (clojure.core/seq? input__14393_nth_0__)
              (clojure.core/let
               [input__14393_nth_0___l__
                (clojure.core/take 1 input__14393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14393_nth_0___r__
                  (clojure.core/drop 1 input__14393_nth_0__)]
                 (clojure.core/let
                  [input__14393_nth_0___l___nth_0__
                   (clojure.core/nth input__14393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15572
                     (clojure.core/namespace
                      input__14393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14409 X__15572]
                     (clojure.core/let
                      [X__15574
                       (clojure.core/name
                        input__14393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15574
                       ("or")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15560 input__14393_nth_1__ ?__14409)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15939)
                         (clojure.core/let
                          [[?__14409] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14393)
                             2)
                            (clojure.core/let
                             [input__14393_nth_0__
                              (clojure.core/nth input__14393 0)
                              input__14393_nth_1__
                              (clojure.core/nth input__14393 1)]
                             (if
                              (clojure.core/seq? input__14393_nth_0__)
                              (clojure.core/let
                               [input__14393_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__14393_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__14393_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__14393_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__14393_nth_0__)]
                                 (clojure.core/let
                                  [!forms
                                   (clojure.core/vec
                                    input__14393_nth_0___r__)]
                                  (clojure.core/let
                                   [?form input__14393_nth_0__]
                                   (clojure.core/let
                                    [?env input__14393_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [!forms__counter
                                        (meander.runtime.zeta/iterator
                                         !forms)]
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14472
                                          ['meander.dev.parse.zeta/make-or
                                           (clojure.core/into
                                            []
                                            (clojure.core/loop
                                             [return__14475
                                              (clojure.core/transient
                                               [])]
                                             (if
                                              (clojure.core/and
                                               (.hasNext
                                                !forms__counter))
                                              (recur
                                               (clojure.core/conj!
                                                return__14475
                                                (clojure.core/let
                                                 [CATA_RESULT__9238__auto__
                                                  (CATA__FN__14472
                                                   [(if
                                                     (.hasNext
                                                      !forms__counter)
                                                     (.next
                                                      !forms__counter))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__9238__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__9238__auto__
                                                   0)))))
                                              (clojure.core/persistent!
                                               return__14475))))
                                           ?form])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9238__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9238__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__10178__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10178__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10178__auto__))))))))
                                (state__15939)))
                              (state__15939)))
                            (state__15939))
                           (state__15939)))))
                       (state__15939)))))
                   (state__15939))))
                (state__15939)))
              (state__15939)))
            (state__15939))
           (state__15939))))
        (state__15939
         []
         (if
          (clojure.core/vector? input__14393)
          (if
           (clojure.core/= (clojure.core/count input__14393) 3)
           (clojure.core/let
            [input__14393_nth_0__
             (clojure.core/nth input__14393 0)
             input__14393_nth_1__
             (clojure.core/nth input__14393 1)
             input__14393_nth_2__
             (clojure.core/nth input__14393 2)]
            (clojure.core/case
             input__14393_nth_0__
             (meander.dev.parse.zeta/make-or)
             (clojure.core/letfn
              [(state__16163
                []
                (if
                 (clojure.core/vector? input__14393_nth_1__)
                 (clojure.core/case
                  input__14393_nth_1__
                  ([])
                  (clojure.core/let
                   [?form input__14393_nth_2__]
                   [{:tag :error,
                     :message
                     "meander.zeta/or requires 1 or more arguments",
                     :form ?form}])
                  (state__16164))
                 (state__16164)))
               (state__16164
                []
                (clojure.core/case
                 input__14393_nth_2__
                 (nil)
                 (if
                  (clojure.core/vector? input__14393_nth_1__)
                  (if
                   (clojure.core/=
                    (clojure.core/count input__14393_nth_1__)
                    1)
                   (clojure.core/let
                    [input__14393_nth_1___nth_0__
                     (clojure.core/nth input__14393_nth_1__ 0)]
                    (clojure.core/let
                     [?ast-a input__14393_nth_1___nth_0__]
                     [?ast-a]))
                   (state__16165))
                  (state__16165))
                 (state__16165)))
               (state__16165
                []
                (if
                 (clojure.core/vector? input__14393_nth_1__)
                 (clojure.core/letfn
                  [(state__16166
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__14393_nth_1__)
                      1)
                     (clojure.core/let
                      [input__14393_nth_1___nth_0__
                       (clojure.core/nth input__14393_nth_1__ 0)]
                      (clojure.core/let
                       [?ast-a input__14393_nth_1___nth_0__]
                       (clojure.core/let
                        [?form input__14393_nth_2__]
                        (try
                         [(clojure.core/let
                           [CATA_RESULT__9238__auto__
                            (CATA__FN__14472
                             ['meander.dev.parse.zeta/make-or
                              [?ast-a {:tag :pass}]
                              ?form])]
                           (if
                            (meander.runtime.zeta/fail?
                             CATA_RESULT__9238__auto__)
                            (throw (meander.runtime.zeta/fail))
                            (clojure.core/nth
                             CATA_RESULT__9238__auto__
                             0)))]
                         (catch
                          java.lang.Exception
                          e__10178__auto__
                          (if
                           (meander.runtime.zeta/fail?
                            e__10178__auto__)
                           (meander.runtime.zeta/fail)
                           (throw e__10178__auto__)))))))
                     (state__16167)))
                   (state__16167
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__14393_nth_1__)
                      2)
                     (clojure.core/let
                      [input__14393_nth_1___nth_0__
                       (clojure.core/nth input__14393_nth_1__ 0)
                       input__14393_nth_1___nth_1__
                       (clojure.core/nth input__14393_nth_1__ 1)]
                      (clojure.core/let
                       [?ast-a input__14393_nth_1___nth_0__]
                       (clojure.core/let
                        [?ast-b input__14393_nth_1___nth_1__]
                        (clojure.core/let
                         [?form input__14393_nth_2__]
                         [{:tag :or,
                           :left ?ast-a,
                           :right ?ast-b,
                           :form ?form}]))))
                     (state__16168)))
                   (state__16168
                    []
                    (clojure.core/loop
                     [search_space__16169
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__14393_nth_1__)]
                     (if
                      (clojure.core/seq search_space__16169)
                      (clojure.core/let
                       [input__14393_nth_1___parts__
                        (clojure.core/first search_space__16169)
                        result__16170
                        (clojure.core/let
                         [input__14393_nth_1___l__
                          (clojure.core/nth
                           input__14393_nth_1___parts__
                           0)
                          input__14393_nth_1___r__
                          (clojure.core/nth
                           input__14393_nth_1___parts__
                           1)]
                         (clojure.core/letfn
                          [(state__16172
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__8099__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__14393_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__15621]
                                 (clojure.core/let
                                  [input__15621_nth_0__
                                   (clojure.core/nth input__15621 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__15621_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__15614
                                   (clojure.core/count
                                    input__14393_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__15614]
                                   (clojure.core/let
                                    [X__15618
                                     (clojure.core/count
                                      input__14393_nth_1___r__)]
                                    (if
                                     (clojure.core/= ?n X__15618)
                                     (clojure.core/let
                                      [!asts-2 []]
                                      (clojure.core/let
                                       [ret__8099__auto__
                                        (meander.runtime.zeta/epsilon-run-star-1
                                         input__14393_nth_1___r__
                                         [!asts-2 !asts-1]
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]
                                           input__15619]
                                          (clojure.core/let
                                           [input__15619_nth_0__
                                            (clojure.core/nth
                                             input__15619
                                             0)]
                                           (clojure.core/let
                                            [!asts-2
                                             (clojure.core/conj
                                              !asts-2
                                              input__15619_nth_0__)]
                                            [!asts-2 !asts-1])))
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]]
                                          (clojure.core/let
                                           [?form input__14393_nth_2__]
                                           (try
                                            [(clojure.core/let
                                              [!asts-1__counter
                                               (meander.runtime.zeta/iterator
                                                !asts-1)
                                               !asts-2__counter
                                               (meander.runtime.zeta/iterator
                                                !asts-2)]
                                              (clojure.core/let
                                               [CATA_RESULT__9238__auto__
                                                (CATA__FN__14472
                                                 ['meander.dev.parse.zeta/make-or
                                                  [(clojure.core/let
                                                    [CATA_RESULT__9238__auto__
                                                     (CATA__FN__14472
                                                      ['meander.dev.parse.zeta/make-or
                                                       (clojure.core/into
                                                        []
                                                        (clojure.core/vec
                                                         (clojure.core/iterator-seq
                                                          !asts-1__counter)))
                                                       nil])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__9238__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__9238__auto__
                                                      0)))
                                                   (clojure.core/let
                                                    [CATA_RESULT__9238__auto__
                                                     (CATA__FN__14472
                                                      ['meander.dev.parse.zeta/make-or
                                                       (clojure.core/into
                                                        []
                                                        (clojure.core/vec
                                                         (clojure.core/iterator-seq
                                                          !asts-2__counter)))
                                                       nil])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__9238__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__9238__auto__
                                                      0)))]
                                                  ?form])]
                                               (if
                                                (meander.runtime.zeta/fail?
                                                 CATA_RESULT__9238__auto__)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 CATA_RESULT__9238__auto__
                                                 0))))]
                                            (catch
                                             java.lang.Exception
                                             e__10178__auto__
                                             (if
                                              (meander.runtime.zeta/fail?
                                               e__10178__auto__)
                                              (meander.runtime.zeta/fail)
                                              (throw
                                               e__10178__auto__)))))))]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         ret__8099__auto__)
                                        (state__16173)
                                        ret__8099__auto__)))
                                     (state__16173)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__8099__auto__)
                               (state__16173)
                               ret__8099__auto__))))
                           (state__16173
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__8099__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__14393_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__15637]
                                 (clojure.core/let
                                  [input__15637_nth_0__
                                   (clojure.core/nth input__15637 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__15637_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__15628
                                   (clojure.core/count
                                    input__14393_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__15628]
                                   (clojure.core/let
                                    [input__14393_nth_1___r___l__
                                     (clojure.core/subvec
                                      input__14393_nth_1___r__
                                      0
                                      (clojure.core/min
                                       (clojure.core/count
                                        input__14393_nth_1___r__)
                                       1))]
                                    (if
                                     (clojure.core/=
                                      (clojure.core/count
                                       input__14393_nth_1___r___l__)
                                      1)
                                     (clojure.core/let
                                      [input__14393_nth_1___r___r__
                                       (clojure.core/subvec
                                        input__14393_nth_1___r__
                                        1)]
                                      (clojure.core/let
                                       [input__14393_nth_1___r___l___nth_0__
                                        (clojure.core/nth
                                         input__14393_nth_1___r___l__
                                         0)]
                                       (clojure.core/let
                                        [?ast
                                         input__14393_nth_1___r___l___nth_0__]
                                        (clojure.core/let
                                         [X__15634
                                          (clojure.core/count
                                           input__14393_nth_1___r___r__)]
                                         (if
                                          (clojure.core/= ?n X__15634)
                                          (clojure.core/let
                                           [!asts-2 []]
                                           (clojure.core/let
                                            [ret__8099__auto__
                                             (meander.runtime.zeta/epsilon-run-star-1
                                              input__14393_nth_1___r___r__
                                              [!asts-2 !asts-1]
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]
                                                input__15635]
                                               (clojure.core/let
                                                [input__15635_nth_0__
                                                 (clojure.core/nth
                                                  input__15635
                                                  0)]
                                                (clojure.core/let
                                                 [!asts-2
                                                  (clojure.core/conj
                                                   !asts-2
                                                   input__15635_nth_0__)]
                                                 [!asts-2 !asts-1])))
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]]
                                               (clojure.core/let
                                                [?form
                                                 input__14393_nth_2__]
                                                (try
                                                 [(clojure.core/let
                                                   [!asts-1__counter
                                                    (meander.runtime.zeta/iterator
                                                     !asts-1)
                                                    !asts-2__counter
                                                    (meander.runtime.zeta/iterator
                                                     !asts-2)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__9238__auto__
                                                     (CATA__FN__14472
                                                      ['meander.dev.parse.zeta/make-or
                                                       [(clojure.core/let
                                                         [CATA_RESULT__9238__auto__
                                                          (CATA__FN__14472
                                                           ['meander.dev.parse.zeta/make-or
                                                            (clojure.core/into
                                                             []
                                                             (clojure.core/concat
                                                              (clojure.core/vec
                                                               (clojure.core/iterator-seq
                                                                !asts-1__counter))
                                                              (clojure.core/list
                                                               ?ast)))
                                                            nil])]
                                                         (if
                                                          (meander.runtime.zeta/fail?
                                                           CATA_RESULT__9238__auto__)
                                                          (throw
                                                           (meander.runtime.zeta/fail))
                                                          (clojure.core/nth
                                                           CATA_RESULT__9238__auto__
                                                           0)))
                                                        (clojure.core/let
                                                         [CATA_RESULT__9238__auto__
                                                          (CATA__FN__14472
                                                           ['meander.dev.parse.zeta/make-or
                                                            (clojure.core/into
                                                             []
                                                             (clojure.core/vec
                                                              (clojure.core/iterator-seq
                                                               !asts-2__counter)))
                                                            nil])]
                                                         (if
                                                          (meander.runtime.zeta/fail?
                                                           CATA_RESULT__9238__auto__)
                                                          (throw
                                                           (meander.runtime.zeta/fail))
                                                          (clojure.core/nth
                                                           CATA_RESULT__9238__auto__
                                                           0)))]
                                                       ?form])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__9238__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__9238__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__10178__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__10178__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__10178__auto__)))))))]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              ret__8099__auto__)
                                             (meander.runtime.zeta/fail)
                                             ret__8099__auto__)))
                                          (meander.runtime.zeta/fail))))))
                                     (meander.runtime.zeta/fail)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__8099__auto__)
                               (meander.runtime.zeta/fail)
                               ret__8099__auto__))))]
                          (state__16172)))]
                       (if
                        (meander.runtime.zeta/fail? result__16170)
                        (recur (clojure.core/next search_space__16169))
                        result__16170))
                      (state__15940))))]
                  (state__16166))
                 (state__15940)))]
              (state__16163))
             (state__15940)))
           (state__15940))
          (state__15940)))
        (state__15940
         []
         (clojure.core/letfn
          [(def__15640
            [arg__15663 ?__14410]
            (clojure.core/letfn
             [(state__16178
               []
               (clojure.core/let
                [x__15664 "meander.zeta"]
                (if
                 (clojure.core/= ?__14410 x__15664)
                 [?__14410]
                 (state__16179))))
              (state__16179
               []
               (if
                (clojure.core/map? arg__15663)
                (clojure.core/let
                 [VAL__15665 (.valAt arg__15663 :aliases)]
                 (if
                  (clojure.core/map? VAL__15665)
                  (clojure.core/let
                   [X__15667 (clojure.core/set VAL__15665)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15667))
                    (clojure.core/loop
                     [search_space__16180 (clojure.core/seq X__15667)]
                     (if
                      (clojure.core/seq search_space__16180)
                      (clojure.core/let
                       [elem__15668
                        (clojure.core/first search_space__16180)
                        result__16181
                        (clojure.core/let
                         [elem__15668_nth_0__
                          (clojure.core/nth elem__15668 0)
                          elem__15668_nth_1__
                          (clojure.core/nth elem__15668 1)]
                         (if
                          (clojure.core/symbol? elem__15668_nth_0__)
                          (clojure.core/let
                           [X__15670
                            (clojure.core/name elem__15668_nth_0__)]
                           (if
                            (clojure.core/= ?__14410 X__15670)
                            (if
                             (clojure.core/symbol? elem__15668_nth_1__)
                             (clojure.core/let
                              [X__15672
                               (clojure.core/name elem__15668_nth_1__)]
                              (clojure.core/case
                               X__15672
                               ("meander.zeta")
                               [?__14410]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16181)
                        (recur (clojure.core/next search_space__16180))
                        result__16181))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16178)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 2)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)]
             (if
              (clojure.core/seq? input__14393_nth_0__)
              (clojure.core/let
               [input__14393_nth_0___l__
                (clojure.core/take 1 input__14393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14393_nth_0___r__
                  (clojure.core/drop 1 input__14393_nth_0__)]
                 (clojure.core/let
                  [input__14393_nth_0___l___nth_0__
                   (clojure.core/nth input__14393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15650
                     (clojure.core/namespace
                      input__14393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14410 X__15650]
                     (clojure.core/let
                      [X__15652
                       (clojure.core/name
                        input__14393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15652
                       ("pred")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15640 input__14393_nth_1__ ?__14410)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15941)
                         (clojure.core/let
                          [[?__14410] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14393)
                             2)
                            (clojure.core/let
                             [input__14393_nth_0__
                              (clojure.core/nth input__14393 0)
                              input__14393_nth_1__
                              (clojure.core/nth input__14393 1)]
                             (if
                              (clojure.core/seq? input__14393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__14393_nth_0__)
                                2)
                               (clojure.core/let
                                [input__14393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14393_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?expression
                                  input__14393_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__14393_nth_0__]
                                  (clojure.core/let
                                   [?env input__14393_nth_1__]
                                   [{:tag :pred,
                                     :expression
                                     {:tag :host-expression,
                                      :form ?expression},
                                     :pattern {:tag :wildcard},
                                     :form ?form}]))))
                               (state__15941))
                              (state__15941)))
                            (state__15941))
                           (state__15941)))))
                       (state__15941)))))
                   (state__15941))))
                (state__15941)))
              (state__15941)))
            (state__15941))
           (state__15941))))
        (state__15941
         []
         (clojure.core/letfn
          [(def__15674
            [arg__15697 ?__14411]
            (clojure.core/letfn
             [(state__16183
               []
               (clojure.core/let
                [x__15698 "meander.zeta"]
                (if
                 (clojure.core/= ?__14411 x__15698)
                 [?__14411]
                 (state__16184))))
              (state__16184
               []
               (if
                (clojure.core/map? arg__15697)
                (clojure.core/let
                 [VAL__15699 (.valAt arg__15697 :aliases)]
                 (if
                  (clojure.core/map? VAL__15699)
                  (clojure.core/let
                   [X__15701 (clojure.core/set VAL__15699)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15701))
                    (clojure.core/loop
                     [search_space__16185 (clojure.core/seq X__15701)]
                     (if
                      (clojure.core/seq search_space__16185)
                      (clojure.core/let
                       [elem__15702
                        (clojure.core/first search_space__16185)
                        result__16186
                        (clojure.core/let
                         [elem__15702_nth_0__
                          (clojure.core/nth elem__15702 0)
                          elem__15702_nth_1__
                          (clojure.core/nth elem__15702 1)]
                         (if
                          (clojure.core/symbol? elem__15702_nth_0__)
                          (clojure.core/let
                           [X__15704
                            (clojure.core/name elem__15702_nth_0__)]
                           (if
                            (clojure.core/= ?__14411 X__15704)
                            (if
                             (clojure.core/symbol? elem__15702_nth_1__)
                             (clojure.core/let
                              [X__15706
                               (clojure.core/name elem__15702_nth_1__)]
                              (clojure.core/case
                               X__15706
                               ("meander.zeta")
                               [?__14411]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16186)
                        (recur (clojure.core/next search_space__16185))
                        result__16186))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16183)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 2)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)]
             (if
              (clojure.core/seq? input__14393_nth_0__)
              (clojure.core/let
               [input__14393_nth_0___l__
                (clojure.core/take 1 input__14393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14393_nth_0___r__
                  (clojure.core/drop 1 input__14393_nth_0__)]
                 (clojure.core/let
                  [input__14393_nth_0___l___nth_0__
                   (clojure.core/nth input__14393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15684
                     (clojure.core/namespace
                      input__14393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14411 X__15684]
                     (clojure.core/let
                      [X__15686
                       (clojure.core/name
                        input__14393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15686
                       ("pred")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15674 input__14393_nth_1__ ?__14411)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15942)
                         (clojure.core/let
                          [[?__14411] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14393)
                             2)
                            (clojure.core/let
                             [input__14393_nth_0__
                              (clojure.core/nth input__14393 0)
                              input__14393_nth_1__
                              (clojure.core/nth input__14393 1)]
                             (if
                              (clojure.core/seq? input__14393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__14393_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14393_nth_0__
                                  1)
                                 input__14393_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14393_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?expression
                                  input__14393_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__14393_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14393_nth_0__]
                                   (clojure.core/let
                                    [?env input__14393_nth_1__]
                                    (try
                                     [{:tag :pred,
                                       :expression
                                       {:tag :host-expression,
                                        :form ?expression},
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14472
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9238__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9238__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10178__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10178__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10178__auto__)))))))))
                               (state__15942))
                              (state__15942)))
                            (state__15942))
                           (state__15942)))))
                       (state__15942)))))
                   (state__15942))))
                (state__15942)))
              (state__15942)))
            (state__15942))
           (state__15942))))
        (state__15942
         []
         (clojure.core/letfn
          [(def__15708
            [arg__15731 ?__14412]
            (clojure.core/letfn
             [(state__16188
               []
               (clojure.core/let
                [x__15732 "meander.zeta"]
                (if
                 (clojure.core/= ?__14412 x__15732)
                 [?__14412]
                 (state__16189))))
              (state__16189
               []
               (if
                (clojure.core/map? arg__15731)
                (clojure.core/let
                 [VAL__15733 (.valAt arg__15731 :aliases)]
                 (if
                  (clojure.core/map? VAL__15733)
                  (clojure.core/let
                   [X__15735 (clojure.core/set VAL__15733)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15735))
                    (clojure.core/loop
                     [search_space__16190 (clojure.core/seq X__15735)]
                     (if
                      (clojure.core/seq search_space__16190)
                      (clojure.core/let
                       [elem__15736
                        (clojure.core/first search_space__16190)
                        result__16191
                        (clojure.core/let
                         [elem__15736_nth_0__
                          (clojure.core/nth elem__15736 0)
                          elem__15736_nth_1__
                          (clojure.core/nth elem__15736 1)]
                         (if
                          (clojure.core/symbol? elem__15736_nth_0__)
                          (clojure.core/let
                           [X__15738
                            (clojure.core/name elem__15736_nth_0__)]
                           (if
                            (clojure.core/= ?__14412 X__15738)
                            (if
                             (clojure.core/symbol? elem__15736_nth_1__)
                             (clojure.core/let
                              [X__15740
                               (clojure.core/name elem__15736_nth_1__)]
                              (clojure.core/case
                               X__15740
                               ("meander.zeta")
                               [?__14412]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16191)
                        (recur (clojure.core/next search_space__16190))
                        result__16191))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16188)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 2)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)]
             (if
              (clojure.core/seq? input__14393_nth_0__)
              (clojure.core/let
               [input__14393_nth_0___l__
                (clojure.core/take 1 input__14393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14393_nth_0___r__
                  (clojure.core/drop 1 input__14393_nth_0__)]
                 (clojure.core/let
                  [input__14393_nth_0___l___nth_0__
                   (clojure.core/nth input__14393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15718
                     (clojure.core/namespace
                      input__14393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14412 X__15718]
                     (clojure.core/let
                      [X__15720
                       (clojure.core/name
                        input__14393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15720
                       ("re")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15708 input__14393_nth_1__ ?__14412)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15943)
                         (clojure.core/let
                          [[?__14412] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14393)
                             2)
                            (clojure.core/let
                             [input__14393_nth_0__
                              (clojure.core/nth input__14393 0)
                              input__14393_nth_1__
                              (clojure.core/nth input__14393 1)]
                             (if
                              (clojure.core/seq? input__14393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__14393_nth_0__)
                                2)
                               (clojure.core/let
                                [input__14393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14393_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?regex input__14393_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__14393_nth_0__]
                                  (clojure.core/let
                                   [?env input__14393_nth_1__]
                                   [{:tag :regex,
                                     :regex ?regex,
                                     :form ?form}]))))
                               (state__15943))
                              (state__15943)))
                            (state__15943))
                           (state__15943)))))
                       (state__15943)))))
                   (state__15943))))
                (state__15943)))
              (state__15943)))
            (state__15943))
           (state__15943))))
        (state__15943
         []
         (clojure.core/letfn
          [(def__15742
            [arg__15765 ?__14413]
            (clojure.core/letfn
             [(state__16193
               []
               (clojure.core/let
                [x__15766 "meander.zeta"]
                (if
                 (clojure.core/= ?__14413 x__15766)
                 [?__14413]
                 (state__16194))))
              (state__16194
               []
               (if
                (clojure.core/map? arg__15765)
                (clojure.core/let
                 [VAL__15767 (.valAt arg__15765 :aliases)]
                 (if
                  (clojure.core/map? VAL__15767)
                  (clojure.core/let
                   [X__15769 (clojure.core/set VAL__15767)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15769))
                    (clojure.core/loop
                     [search_space__16195 (clojure.core/seq X__15769)]
                     (if
                      (clojure.core/seq search_space__16195)
                      (clojure.core/let
                       [elem__15770
                        (clojure.core/first search_space__16195)
                        result__16196
                        (clojure.core/let
                         [elem__15770_nth_0__
                          (clojure.core/nth elem__15770 0)
                          elem__15770_nth_1__
                          (clojure.core/nth elem__15770 1)]
                         (if
                          (clojure.core/symbol? elem__15770_nth_0__)
                          (clojure.core/let
                           [X__15772
                            (clojure.core/name elem__15770_nth_0__)]
                           (if
                            (clojure.core/= ?__14413 X__15772)
                            (if
                             (clojure.core/symbol? elem__15770_nth_1__)
                             (clojure.core/let
                              [X__15774
                               (clojure.core/name elem__15770_nth_1__)]
                              (clojure.core/case
                               X__15774
                               ("meander.zeta")
                               [?__14413]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16196)
                        (recur (clojure.core/next search_space__16195))
                        result__16196))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16193)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 2)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)]
             (if
              (clojure.core/seq? input__14393_nth_0__)
              (clojure.core/let
               [input__14393_nth_0___l__
                (clojure.core/take 1 input__14393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14393_nth_0___r__
                  (clojure.core/drop 1 input__14393_nth_0__)]
                 (clojure.core/let
                  [input__14393_nth_0___l___nth_0__
                   (clojure.core/nth input__14393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15752
                     (clojure.core/namespace
                      input__14393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14413 X__15752]
                     (clojure.core/let
                      [X__15754
                       (clojure.core/name
                        input__14393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15754
                       ("re")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15742 input__14393_nth_1__ ?__14413)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15944)
                         (clojure.core/let
                          [[?__14413] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14393)
                             2)
                            (clojure.core/let
                             [input__14393_nth_0__
                              (clojure.core/nth input__14393 0)
                              input__14393_nth_1__
                              (clojure.core/nth input__14393 1)]
                             (if
                              (clojure.core/seq? input__14393_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__14393_nth_0__)
                                3)
                               (clojure.core/let
                                [input__14393_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__14393_nth_0__
                                  1)
                                 input__14393_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__14393_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?regex input__14393_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?capture
                                   input__14393_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__14393_nth_0__]
                                   (clojure.core/let
                                    [?env input__14393_nth_1__]
                                    (try
                                     [{:tag :regex-with-capture,
                                       :regex ?regex,
                                       :capture
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14472
                                          [?capture ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9238__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9238__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10178__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10178__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10178__auto__)))))))))
                               (state__15944))
                              (state__15944)))
                            (state__15944))
                           (state__15944)))))
                       (state__15944)))))
                   (state__15944))))
                (state__15944)))
              (state__15944)))
            (state__15944))
           (state__15944))))
        (state__15944
         []
         (clojure.core/letfn
          [(def__15776
            [arg__15799 ?__14414]
            (clojure.core/letfn
             [(state__16198
               []
               (clojure.core/let
                [x__15800 "meander.zeta"]
                (if
                 (clojure.core/= ?__14414 x__15800)
                 [?__14414]
                 (state__16199))))
              (state__16199
               []
               (if
                (clojure.core/map? arg__15799)
                (clojure.core/let
                 [VAL__15801 (.valAt arg__15799 :aliases)]
                 (if
                  (clojure.core/map? VAL__15801)
                  (clojure.core/let
                   [X__15803 (clojure.core/set VAL__15801)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15803))
                    (clojure.core/loop
                     [search_space__16200 (clojure.core/seq X__15803)]
                     (if
                      (clojure.core/seq search_space__16200)
                      (clojure.core/let
                       [elem__15804
                        (clojure.core/first search_space__16200)
                        result__16201
                        (clojure.core/let
                         [elem__15804_nth_0__
                          (clojure.core/nth elem__15804 0)
                          elem__15804_nth_1__
                          (clojure.core/nth elem__15804 1)]
                         (if
                          (clojure.core/symbol? elem__15804_nth_0__)
                          (clojure.core/let
                           [X__15806
                            (clojure.core/name elem__15804_nth_0__)]
                           (if
                            (clojure.core/= ?__14414 X__15806)
                            (if
                             (clojure.core/symbol? elem__15804_nth_1__)
                             (clojure.core/let
                              [X__15808
                               (clojure.core/name elem__15804_nth_1__)]
                              (clojure.core/case
                               X__15808
                               ("meander.zeta")
                               [?__14414]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16201)
                        (recur (clojure.core/next search_space__16200))
                        result__16201))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16198)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 2)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)]
             (if
              (clojure.core/seq? input__14393_nth_0__)
              (clojure.core/let
               [input__14393_nth_0___l__
                (clojure.core/take 1 input__14393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14393_nth_0___r__
                  (clojure.core/drop 1 input__14393_nth_0__)]
                 (clojure.core/let
                  [input__14393_nth_0___l___nth_0__
                   (clojure.core/nth input__14393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15786
                     (clojure.core/namespace
                      input__14393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14414 X__15786]
                     (clojure.core/let
                      [X__15788
                       (clojure.core/name
                        input__14393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15788
                       ("str")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15776 input__14393_nth_1__ ?__14414)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15945)
                         (clojure.core/let
                          [[?__14414] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14393)
                             2)
                            (clojure.core/let
                             [input__14393_nth_0__
                              (clojure.core/nth input__14393 0)
                              input__14393_nth_1__
                              (clojure.core/nth input__14393 1)]
                             (if
                              (clojure.core/seq? input__14393_nth_0__)
                              (clojure.core/let
                               [input__14393_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__14393_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__14393_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__14393_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__14393_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__14393_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__14393_nth_0__]
                                   (clojure.core/let
                                    [?env input__14393_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__9238__auto__
                                         (CATA__FN__14472
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?sequence)
                                           (clojure.core/let
                                            [form__9337__auto__
                                             {:context :string}]
                                            (clojure.core/merge
                                             (clojure.core/into
                                              {}
                                              ?env)
                                             form__9337__auto__))])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__9238__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__9238__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__10178__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10178__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10178__auto__))))))))
                                (state__15945)))
                              (state__15945)))
                            (state__15945))
                           (state__15945)))))
                       (state__15945)))))
                   (state__15945))))
                (state__15945)))
              (state__15945)))
            (state__15945))
           (state__15945))))
        (state__15945
         []
         (clojure.core/letfn
          [(def__15810
            [arg__15833 ?__14415]
            (clojure.core/letfn
             [(state__16203
               []
               (clojure.core/let
                [x__15834 "meander.zeta"]
                (if
                 (clojure.core/= ?__14415 x__15834)
                 [?__14415]
                 (state__16204))))
              (state__16204
               []
               (if
                (clojure.core/map? arg__15833)
                (clojure.core/let
                 [VAL__15835 (.valAt arg__15833 :aliases)]
                 (if
                  (clojure.core/map? VAL__15835)
                  (clojure.core/let
                   [X__15837 (clojure.core/set VAL__15835)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__15837))
                    (clojure.core/loop
                     [search_space__16205 (clojure.core/seq X__15837)]
                     (if
                      (clojure.core/seq search_space__16205)
                      (clojure.core/let
                       [elem__15838
                        (clojure.core/first search_space__16205)
                        result__16206
                        (clojure.core/let
                         [elem__15838_nth_0__
                          (clojure.core/nth elem__15838 0)
                          elem__15838_nth_1__
                          (clojure.core/nth elem__15838 1)]
                         (if
                          (clojure.core/symbol? elem__15838_nth_0__)
                          (clojure.core/let
                           [X__15840
                            (clojure.core/name elem__15838_nth_0__)]
                           (if
                            (clojure.core/= ?__14415 X__15840)
                            (if
                             (clojure.core/symbol? elem__15838_nth_1__)
                             (clojure.core/let
                              [X__15842
                               (clojure.core/name elem__15838_nth_1__)]
                              (clojure.core/case
                               X__15842
                               ("meander.zeta")
                               [?__14415]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__16206)
                        (recur (clojure.core/next search_space__16205))
                        result__16206))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__16203)))]
          (if
           (clojure.core/vector? input__14393)
           (if
            (clojure.core/= (clojure.core/count input__14393) 2)
            (clojure.core/let
             [input__14393_nth_0__
              (clojure.core/nth input__14393 0)
              input__14393_nth_1__
              (clojure.core/nth input__14393 1)]
             (if
              (clojure.core/seq? input__14393_nth_0__)
              (clojure.core/let
               [input__14393_nth_0___l__
                (clojure.core/take 1 input__14393_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__14393_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__14393_nth_0___r__
                  (clojure.core/drop 1 input__14393_nth_0__)]
                 (clojure.core/let
                  [input__14393_nth_0___l___nth_0__
                   (clojure.core/nth input__14393_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__14393_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__15820
                     (clojure.core/namespace
                      input__14393_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__14415 X__15820]
                     (clojure.core/let
                      [X__15822
                       (clojure.core/name
                        input__14393_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__15822
                       ("symbol")
                       (clojure.core/let
                        [x__7935__auto__
                         (def__15810 input__14393_nth_1__ ?__14415)]
                        (if
                         (meander.runtime.zeta/fail? x__7935__auto__)
                         (state__15946)
                         (clojure.core/let
                          [[?__14415] x__7935__auto__]
                          (if
                           (clojure.core/vector? input__14393)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__14393)
                             2)
                            (clojure.core/let
                             [input__14393_nth_0__
                              (clojure.core/nth input__14393 0)
                              input__14393_nth_1__
                              (clojure.core/nth input__14393 1)]
                             (if
                              (clojure.core/seq? input__14393_nth_0__)
                              (clojure.core/let
                               [input__14393_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__14393_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__14393_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__14393_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__14393_nth_0__)]
                                 (clojure.core/let
                                  [?symbol-args
                                   input__14393_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__14393_nth_0__]
                                   (clojure.core/let
                                    [?env input__14393_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [CATA_RESULT__9238__auto__
                                        (CATA__FN__14472
                                         ['meander.dev.parse.zeta/make-symbol
                                          (clojure.core/into
                                           []
                                           ?symbol-args)
                                          ?form
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__9238__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__9238__auto__
                                         0)))]
                                     (catch
                                      java.lang.Exception
                                      e__10178__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__10178__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__10178__auto__))))))))
                                (state__15946)))
                              (state__15946)))
                            (state__15946))
                           (state__15946)))))
                       (state__15946)))))
                   (state__15946))))
                (state__15946)))
              (state__15946)))
            (state__15946))
           (state__15946))))
        (state__15946
         []
         (if
          (clojure.core/vector? input__14393)
          (clojure.core/letfn
           [(state__16208
             []
             (if
              (clojure.core/= (clojure.core/count input__14393) 4)
              (clojure.core/let
               [input__14393_nth_0__
                (clojure.core/nth input__14393 0)
                input__14393_nth_1__
                (clojure.core/nth input__14393 1)
                input__14393_nth_2__
                (clojure.core/nth input__14393 2)]
               (clojure.core/letfn
                [(state__16210
                  []
                  (clojure.core/case
                   input__14393_nth_0__
                   (meander.dev.parse.zeta/make-symbol)
                   (if
                    (clojure.core/vector? input__14393_nth_1__)
                    (clojure.core/case
                     input__14393_nth_1__
                     ([])
                     (clojure.core/let
                      [?form input__14393_nth_2__]
                      [{:tag :symbol,
                        :namespace {:tag :wildcard},
                        :name {:tag :wildcard},
                        :form ?form}])
                     (state__16211))
                    (state__16211))
                   (state__16211)))
                 (state__16211
                  []
                  (clojure.core/let
                   [input__14393_nth_3__
                    (clojure.core/nth input__14393 3)]
                   (clojure.core/case
                    input__14393_nth_0__
                    (meander.dev.parse.zeta/make-symbol)
                    (if
                     (clojure.core/vector? input__14393_nth_1__)
                     (clojure.core/letfn
                      [(state__16212
                        []
                        (if
                         (clojure.core/=
                          (clojure.core/count input__14393_nth_1__)
                          1)
                         (clojure.core/let
                          [input__14393_nth_1___nth_0__
                           (clojure.core/nth input__14393_nth_1__ 0)]
                          (clojure.core/let
                           [?name input__14393_nth_1___nth_0__]
                           (clojure.core/let
                            [?form input__14393_nth_2__]
                            (clojure.core/let
                             [?env input__14393_nth_3__]
                             (try
                              [{:tag :symbol,
                                :namespace {:tag :wildcard},
                                :name
                                (clojure.core/let
                                 [CATA_RESULT__9238__auto__
                                  (CATA__FN__14472 [?name ?env])]
                                 (if
                                  (meander.runtime.zeta/fail?
                                   CATA_RESULT__9238__auto__)
                                  (throw (meander.runtime.zeta/fail))
                                  (clojure.core/nth
                                   CATA_RESULT__9238__auto__
                                   0))),
                                :form ?form}]
                              (catch
                               java.lang.Exception
                               e__10178__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__10178__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__10178__auto__))))))))
                         (state__16213)))
                       (state__16213
                        []
                        (if
                         (clojure.core/=
                          (clojure.core/count input__14393_nth_1__)
                          2)
                         (clojure.core/let
                          [input__14393_nth_1___nth_0__
                           (clojure.core/nth input__14393_nth_1__ 0)
                           input__14393_nth_1___nth_1__
                           (clojure.core/nth input__14393_nth_1__ 1)]
                          (clojure.core/let
                           [?namespace input__14393_nth_1___nth_0__]
                           (clojure.core/let
                            [?name input__14393_nth_1___nth_1__]
                            (clojure.core/let
                             [?form input__14393_nth_2__]
                             (clojure.core/let
                              [?env input__14393_nth_3__]
                              (try
                               [{:tag :symbol,
                                 :namespace
                                 (clojure.core/let
                                  [CATA_RESULT__9238__auto__
                                   (CATA__FN__14472 [?namespace ?env])]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    CATA_RESULT__9238__auto__)
                                   (throw (meander.runtime.zeta/fail))
                                   (clojure.core/nth
                                    CATA_RESULT__9238__auto__
                                    0))),
                                 :name
                                 (clojure.core/let
                                  [CATA_RESULT__9238__auto__
                                   (CATA__FN__14472 [?name ?env])]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    CATA_RESULT__9238__auto__)
                                   (throw (meander.runtime.zeta/fail))
                                   (clojure.core/nth
                                    CATA_RESULT__9238__auto__
                                    0))),
                                 :form ?form}]
                               (catch
                                java.lang.Exception
                                e__10178__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__10178__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__10178__auto__)))))))))
                         (state__16209)))]
                      (state__16212))
                     (state__16209))
                    (state__16209))))]
                (state__16210)))
              (state__16209)))
            (state__16209
             []
             (if
              (clojure.core/= (clojure.core/count input__14393) 2)
              (clojure.core/let
               [input__14393_nth_0__ (clojure.core/nth input__14393 0)]
               (clojure.core/letfn
                [(state__16214
                  []
                  (clojure.core/let
                   [input__14393_nth_1__
                    (clojure.core/nth input__14393 1)]
                   (clojure.core/letfn
                    [(state__16219
                      []
                      (if
                       (clojure.core/seq? input__14393_nth_0__)
                       (clojure.core/let
                        [?sequence input__14393_nth_0__]
                        (clojure.core/let
                         [?env input__14393_nth_1__]
                         (try
                          [{:tag :seq,
                            :next
                            (clojure.core/let
                             [CATA_RESULT__9238__auto__
                              (CATA__FN__14472
                               ['meander.dev.parse.zeta/parse-sequential
                                (clojure.core/into [] ?sequence)
                                (clojure.core/let
                                 [form__9337__auto__ {:context :seq}]
                                 (clojure.core/merge
                                  (clojure.core/into {} ?env)
                                  form__9337__auto__))])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__9238__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__9238__auto__
                               0))),
                            :form ?sequence}]
                          (catch
                           java.lang.Exception
                           e__10178__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__10178__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__10178__auto__))))))
                       (state__16220)))
                     (state__16220
                      []
                      (if
                       (clojure.core/map? input__14393_nth_0__)
                       (clojure.core/let
                        [?map input__14393_nth_0__]
                        (clojure.core/let
                         [?env input__14393_nth_1__]
                         (try
                          [{:tag :map,
                            :next
                            (clojure.core/let
                             [CATA_RESULT__9238__auto__
                              (CATA__FN__14472
                               ['meander.dev.parse.zeta/parse-entries
                                ?map
                                ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__9238__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__9238__auto__
                               0))),
                            :form ?map}]
                          (catch
                           java.lang.Exception
                           e__10178__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__10178__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__10178__auto__))))))
                       (state__16215)))]
                    (state__16219))))
                 (state__16215
                  []
                  (if
                   (clojure.core/symbol? input__14393_nth_0__)
                   (clojure.core/let
                    [X__15862
                     (clojure.core/namespace input__14393_nth_0__)]
                    (clojure.core/let
                     [X__15864
                      (clojure.core/name input__14393_nth_0__)]
                     (if
                      (clojure.core/string? X__15864)
                      (clojure.core/letfn
                       [(state__16221
                         []
                         (clojure.core/let
                          [ret__15865
                           (clojure.core/re-matches #"_.*" X__15864)]
                          (if
                           (clojure.core/some? ret__15865)
                           (clojure.core/let
                            [?name ret__15865]
                            (clojure.core/let
                             [?symbol input__14393_nth_0__]
                             [{:tag :wildcard,
                               :name ?name,
                               :form ?symbol,
                               :symbol ?symbol}]))
                           (state__16222))))
                        (state__16222
                         []
                         (clojure.core/let
                          [ret__15872
                           (clojure.core/re-matches #".+#" X__15864)]
                          (if
                           (clojure.core/some? ret__15872)
                           (clojure.core/let
                            [?name ret__15872]
                            (clojure.core/let
                             [?symbol input__14393_nth_0__]
                             [{:tag :random-symbol,
                               :name ?name,
                               :form ?symbol,
                               :symbol ?symbol}]))
                           (state__16223))))
                        (state__16223
                         []
                         (clojure.core/let
                          [ret__15879
                           (clojure.core/re-matches #"%.+" X__15864)]
                          (if
                           (clojure.core/some? ret__15879)
                           (clojure.core/let
                            [?name ret__15879]
                            (clojure.core/let
                             [?symbol input__14393_nth_0__]
                             [{:tag :reference,
                               :name ?name,
                               :symbol ?symbol}]))
                           (state__16224))))
                        (state__16224
                         []
                         (clojure.core/let
                          [ret__15886
                           (clojure.core/re-matches #"\*.+" X__15864)]
                          (if
                           (clojure.core/some? ret__15886)
                           (clojure.core/let
                            [?name ret__15886]
                            (clojure.core/let
                             [?symbol input__14393_nth_0__]
                             [{:tag :mutable-variable,
                               :name ?name,
                               :symbol ?symbol}]))
                           (state__16225))))
                        (state__16225
                         []
                         (clojure.core/let
                          [ret__15893
                           (clojure.core/re-matches #"\!.+" X__15864)]
                          (if
                           (clojure.core/some? ret__15893)
                           (clojure.core/let
                            [?name ret__15893]
                            (clojure.core/let
                             [?symbol input__14393_nth_0__]
                             [{:tag :memory-variable,
                               :name ?name,
                               :symbol ?symbol}]))
                           (state__16226))))
                        (state__16226
                         []
                         (clojure.core/let
                          [ret__15900
                           (clojure.core/re-matches #"\?.+" X__15864)]
                          (if
                           (clojure.core/some? ret__15900)
                           (clojure.core/let
                            [?name ret__15900]
                            (clojure.core/let
                             [?symbol input__14393_nth_0__]
                             [{:tag :logic-variable,
                               :name ?name,
                               :symbol ?symbol}]))
                           (state__16216))))]
                       (state__16221))
                      (state__16216))))
                   (state__16216)))
                 (state__16216
                  []
                  (if
                   (string? input__14393_nth_0__)
                   (clojure.core/let
                    [?x input__14393_nth_0__]
                    [{:tag :literal, :type :string, :form ?x}])
                   (state__16217)))
                 (state__16217
                  []
                  (if
                   (char? input__14393_nth_0__)
                   (clojure.core/let
                    [?x input__14393_nth_0__]
                    [{:tag :literal, :type :char, :form ?x}])
                   (state__16218)))
                 (state__16218
                  []
                  (clojure.core/let
                   [?x input__14393_nth_0__]
                   [{:tag :literal, :form ?x}]))]
                (state__16214)))
              (state__15947)))]
           (state__16208))
          (state__15947)))
        (state__15947
         []
         (clojure.core/let
          [?x input__14393]
          [{:tag :mistake, :x ?x}]))]
       (state__15909)))]
    (clojure.core/let
     [x__7935__auto__ (CATA__FN__14472 input__14393)]
     (if
      (meander.runtime.zeta/fail? x__7935__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__14476] x__7935__auto__]
       CATA_RETURN__14476))))]
  (if
   (meander.runtime.zeta/fail? ret__8115__auto__)
   nil
   ret__8115__auto__)))
