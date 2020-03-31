(ns meander.compiled.parse.zeta (:require [meander.runtime.zeta]))
(clojure.core/defn
 parse
 [input__122159]
 (let*
  [ret__9760__auto__
   (clojure.core/letfn
    [(CATA__FN__122227
      [input__122159]
      (clojure.core/letfn
       [(state__123469
         []
         (if
          (clojure.core/vector? input__122159)
          (if
           (clojure.core/= (clojure.core/count input__122159) 3)
           (clojure.core/let
            [input__122159_nth_0__
             (clojure.core/nth input__122159 0)
             input__122159_nth_1__
             (clojure.core/nth input__122159 1)
             input__122159_nth_2__
             (clojure.core/nth input__122159 2)]
            (clojure.core/case
             input__122159_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__122159_nth_1__)
              (clojure.core/letfn
               [(state__123502
                 []
                 (clojure.core/case
                  input__122159_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__122159_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__11823__auto__
                     (if
                      (meander.runtime.zeta/fail? e__11823__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__11823__auto__)))))
                  (state__123503)))
                (state__123503
                 []
                 (clojure.core/let
                  [n__122234
                   (clojure.core/count input__122159_nth_1__)
                   m__122235
                   (clojure.core/max 0 (clojure.core/- n__122234 2))
                   input__122159_nth_1___l__
                   (clojure.core/subvec
                    input__122159_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__122159_nth_1__)
                     m__122235))
                   input__122159_nth_1___r__
                   (clojure.core/subvec
                    input__122159_nth_1__
                    m__122235)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__122159_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__122159_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__122159_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__122159_nth_1___r___nth_0__
                       (clojure.core/nth input__122159_nth_1___r__ 0)
                       input__122159_nth_1___r___nth_1__
                       (clojure.core/nth input__122159_nth_1___r__ 1)]
                      (clojure.core/case
                       input__122159_nth_1___r___nth_0__
                       (:meander.zeta/as)
                       (clojure.core/let
                        [?pattern input__122159_nth_1___r___nth_1__]
                        (clojure.core/let
                         [?env input__122159_nth_2__]
                         (try
                          [(clojure.core/let
                            [!xs__counter
                             (meander.runtime.zeta/iterator !xs)]
                            {:tag :as,
                             :pattern
                             (clojure.core/let
                              [CATA_RESULT__10883__auto__
                               (CATA__FN__122227 [?pattern ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__10883__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__10883__auto__
                                0))),
                             :next
                             (clojure.core/let
                              [CATA_RESULT__10883__auto__
                               (CATA__FN__122227
                                ['meander.dev.parse.zeta/parse-sequential
                                 (clojure.core/into
                                  []
                                  (clojure.core/vec
                                   (clojure.core/iterator-seq
                                    !xs__counter)))
                                 ?env])]
                              (if
                               (meander.runtime.zeta/fail?
                                CATA_RESULT__10883__auto__)
                               (throw (meander.runtime.zeta/fail))
                               (clojure.core/nth
                                CATA_RESULT__10883__auto__
                                0)))})]
                          (catch
                           java.lang.Exception
                           e__11823__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__11823__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__11823__auto__))))))
                       (state__123470)))
                     (state__123470)))
                   (state__123470))))]
               (state__123502))
              (state__123470))
             (state__123470)))
           (state__123470))
          (state__123470)))
        (state__123470
         []
         (clojure.core/letfn
          [(def__122240
            [arg__122275 ?ns]
            (clojure.core/letfn
             [(state__123504
               []
               (clojure.core/let
                [x__122276 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__122276)
                 (clojure.core/let [?env arg__122275] [?env ?ns])
                 (state__123505))))
              (state__123505
               []
               (if
                (clojure.core/map? arg__122275)
                (clojure.core/let
                 [VAL__122277 (.valAt arg__122275 :aliases)]
                 (if
                  (clojure.core/map? VAL__122277)
                  (clojure.core/let
                   [X__122279 (clojure.core/set VAL__122277)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__122279))
                    (clojure.core/loop
                     [search_space__123506
                      (clojure.core/seq X__122279)]
                     (if
                      (clojure.core/seq search_space__123506)
                      (clojure.core/let
                       [elem__122280
                        (clojure.core/first search_space__123506)
                        result__123507
                        (clojure.core/let
                         [elem__122280_nth_0__
                          (clojure.core/nth elem__122280 0)
                          elem__122280_nth_1__
                          (clojure.core/nth elem__122280 1)]
                         (if
                          (clojure.core/symbol? elem__122280_nth_0__)
                          (clojure.core/let
                           [X__122282
                            (clojure.core/name elem__122280_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__122282)
                            (if
                             (clojure.core/symbol?
                              elem__122280_nth_1__)
                             (clojure.core/let
                              [X__122284
                               (clojure.core/name
                                elem__122280_nth_1__)]
                              (clojure.core/case
                               X__122284
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__122275]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__123507)
                        (recur
                         (clojure.core/next search_space__123506))
                        result__123507))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__123504)))]
          (if
           (clojure.core/vector? input__122159)
           (if
            (clojure.core/= (clojure.core/count input__122159) 3)
            (clojure.core/let
             [input__122159_nth_0__
              (clojure.core/nth input__122159 0)
              input__122159_nth_1__
              (clojure.core/nth input__122159 1)
              input__122159_nth_2__
              (clojure.core/nth input__122159 2)]
             (clojure.core/case
              input__122159_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__122159_nth_1__)
               (clojure.core/loop
                [search_space__123509
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__122159_nth_1__)]
                (if
                 (clojure.core/seq search_space__123509)
                 (clojure.core/let
                  [input__122159_nth_1___parts__
                   (clojure.core/first search_space__123509)
                   result__123510
                   (clojure.core/let
                    [input__122159_nth_1___l__
                     (clojure.core/nth input__122159_nth_1___parts__ 0)
                     input__122159_nth_1___r__
                     (clojure.core/nth
                      input__122159_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__122159_nth_1___l__)]
                     (clojure.core/let
                      [input__122159_nth_1___r___l__
                       (clojure.core/subvec
                        input__122159_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__122159_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__122159_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__122159_nth_1___r___r__
                         (clojure.core/subvec
                          input__122159_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__122159_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__122159_nth_1___r___l__
                           0)
                          input__122159_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__122159_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__122159_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__122249
                            (clojure.core/namespace
                             input__122159_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__122249]
                            (clojure.core/let
                             [X__122251
                              (clojure.core/name
                               input__122159_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__122251)
                              (clojure.core/let
                               [ret__122252
                                (clojure.core/re-matches
                                 #"&(\d+)"
                                 X__122251)]
                               (if
                                (clojure.core/some? ret__122252)
                                (if
                                 (clojure.core/vector? ret__122252)
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count ret__122252)
                                   2)
                                  (clojure.core/let
                                   [ret__122252_nth_1__
                                    (clojure.core/nth ret__122252 1)]
                                   (clojure.core/let
                                    [?n ret__122252_nth_1__]
                                    (clojure.core/let
                                     [?pattern
                                      input__122159_nth_1___r___l___nth_1__]
                                     (clojure.core/let
                                      [?rest
                                       input__122159_nth_1___r___r__]
                                      (clojure.core/let
                                       [x__9580__auto__
                                        (def__122240
                                         input__122159_nth_2__
                                         ?ns)]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         x__9580__auto__)
                                        (meander.runtime.zeta/fail)
                                        (clojure.core/let
                                         [[?env ?ns] x__9580__auto__]
                                         (try
                                          [(clojure.core/let
                                            [!init__counter
                                             (meander.runtime.zeta/iterator
                                              !init)]
                                            (clojure.core/let
                                             [CATA_RESULT__10883__auto__
                                              (CATA__FN__122227
                                               ['meander.dev.parse.zeta/make-join
                                                (clojure.core/let
                                                 [CATA_RESULT__10883__auto__
                                                  (CATA__FN__122227
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !init__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10883__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10883__auto__
                                                   0)))
                                                (clojure.core/let
                                                 [CATA_RESULT__10883__auto__
                                                  (CATA__FN__122227
                                                   ['meander.dev.parse.zeta/make-join
                                                    {:tag :slice,
                                                     :size
                                                     (Integer. ?n),
                                                     :pattern
                                                     (clojure.core/let
                                                      [CATA_RESULT__10883__auto__
                                                       (CATA__FN__122227
                                                        [?pattern
                                                         ?env])]
                                                      (if
                                                       (meander.runtime.zeta/fail?
                                                        CATA_RESULT__10883__auto__)
                                                       (throw
                                                        (meander.runtime.zeta/fail))
                                                       (clojure.core/nth
                                                        CATA_RESULT__10883__auto__
                                                        0)))}
                                                    (clojure.core/let
                                                     [CATA_RESULT__10883__auto__
                                                      (CATA__FN__122227
                                                       ['meander.dev.parse.zeta/parse-sequential
                                                        (clojure.core/into
                                                         []
                                                         ?rest)
                                                        ?env])]
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       CATA_RESULT__10883__auto__)
                                                      (throw
                                                       (meander.runtime.zeta/fail))
                                                      (clojure.core/nth
                                                       CATA_RESULT__10883__auto__
                                                       0)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10883__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10883__auto__
                                                   0)))
                                                ?env])]
                                             (if
                                              (meander.runtime.zeta/fail?
                                               CATA_RESULT__10883__auto__)
                                              (throw
                                               (meander.runtime.zeta/fail))
                                              (clojure.core/nth
                                               CATA_RESULT__10883__auto__
                                               0))))]
                                          (catch
                                           java.lang.Exception
                                           e__11823__auto__
                                           (if
                                            (meander.runtime.zeta/fail?
                                             e__11823__auto__)
                                            (meander.runtime.zeta/fail)
                                            (throw
                                             e__11823__auto__)))))))))))
                                  (meander.runtime.zeta/fail))
                                 (meander.runtime.zeta/fail))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__123510)
                   (recur (clojure.core/next search_space__123509))
                   result__123510))
                 (state__123471)))
               (state__123471))
              (state__123471)))
            (state__123471))
           (state__123471))))
        (state__123471
         []
         (clojure.core/letfn
          [(def__122297
            [arg__122329 ?ns]
            (clojure.core/letfn
             [(state__123512
               []
               (clojure.core/let
                [x__122330 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__122330)
                 (clojure.core/let [?env arg__122329] [?env ?ns])
                 (state__123513))))
              (state__123513
               []
               (if
                (clojure.core/map? arg__122329)
                (clojure.core/let
                 [VAL__122331 (.valAt arg__122329 :aliases)]
                 (if
                  (clojure.core/map? VAL__122331)
                  (clojure.core/let
                   [X__122333 (clojure.core/set VAL__122331)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__122333))
                    (clojure.core/loop
                     [search_space__123514
                      (clojure.core/seq X__122333)]
                     (if
                      (clojure.core/seq search_space__123514)
                      (clojure.core/let
                       [elem__122334
                        (clojure.core/first search_space__123514)
                        result__123515
                        (clojure.core/let
                         [elem__122334_nth_0__
                          (clojure.core/nth elem__122334 0)
                          elem__122334_nth_1__
                          (clojure.core/nth elem__122334 1)]
                         (if
                          (clojure.core/symbol? elem__122334_nth_0__)
                          (clojure.core/let
                           [X__122336
                            (clojure.core/name elem__122334_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__122336)
                            (if
                             (clojure.core/symbol?
                              elem__122334_nth_1__)
                             (clojure.core/let
                              [X__122338
                               (clojure.core/name
                                elem__122334_nth_1__)]
                              (clojure.core/case
                               X__122338
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__122329]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__123515)
                        (recur
                         (clojure.core/next search_space__123514))
                        result__123515))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__123512)))]
          (if
           (clojure.core/vector? input__122159)
           (if
            (clojure.core/= (clojure.core/count input__122159) 3)
            (clojure.core/let
             [input__122159_nth_0__
              (clojure.core/nth input__122159 0)
              input__122159_nth_1__
              (clojure.core/nth input__122159 1)
              input__122159_nth_2__
              (clojure.core/nth input__122159 2)]
             (clojure.core/case
              input__122159_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__122159_nth_1__)
               (clojure.core/loop
                [search_space__123517
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__122159_nth_1__)]
                (if
                 (clojure.core/seq search_space__123517)
                 (clojure.core/let
                  [input__122159_nth_1___parts__
                   (clojure.core/first search_space__123517)
                   result__123518
                   (clojure.core/let
                    [input__122159_nth_1___l__
                     (clojure.core/nth input__122159_nth_1___parts__ 0)
                     input__122159_nth_1___r__
                     (clojure.core/nth
                      input__122159_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__122159_nth_1___l__)]
                     (clojure.core/let
                      [input__122159_nth_1___r___l__
                       (clojure.core/subvec
                        input__122159_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__122159_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__122159_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__122159_nth_1___r___r__
                         (clojure.core/subvec
                          input__122159_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__122159_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__122159_nth_1___r___l__
                           0)
                          input__122159_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__122159_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__122159_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__122306
                            (clojure.core/namespace
                             input__122159_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__122306]
                            (clojure.core/let
                             [X__122308
                              (clojure.core/name
                               input__122159_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__122308)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__122308)
                               (clojure.core/let
                                [?pattern
                                 input__122159_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__122159_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__9580__auto__
                                   (def__122297
                                    input__122159_nth_2__
                                    ?ns)]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    x__9580__auto__)
                                   (meander.runtime.zeta/fail)
                                   (clojure.core/let
                                    [[?env ?ns] x__9580__auto__]
                                    (try
                                     [(clojure.core/let
                                       [!init__counter
                                        (meander.runtime.zeta/iterator
                                         !init)]
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__122227
                                          ['meander.dev.parse.zeta/make-join
                                           (clojure.core/let
                                            [CATA_RESULT__10883__auto__
                                             (CATA__FN__122227
                                              ['meander.dev.parse.zeta/parse-sequential
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !init__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__10883__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__10883__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__10883__auto__
                                             (CATA__FN__122227
                                              ['meander.dev.parse.zeta/make-join
                                               (clojure.core/let
                                                [CATA_RESULT__10883__auto__
                                                 (CATA__FN__122227
                                                  [?pattern ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__10883__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__10883__auto__
                                                  0)))
                                               (clojure.core/let
                                                [CATA_RESULT__10883__auto__
                                                 (CATA__FN__122227
                                                  ['meander.dev.parse.zeta/parse-sequential
                                                   (clojure.core/into
                                                    []
                                                    ?rest)
                                                   ?env])]
                                                (if
                                                 (meander.runtime.zeta/fail?
                                                  CATA_RESULT__10883__auto__)
                                                 (throw
                                                  (meander.runtime.zeta/fail))
                                                 (clojure.core/nth
                                                  CATA_RESULT__10883__auto__
                                                  0)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__10883__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__10883__auto__
                                              0)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__11823__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11823__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11823__auto__)))))))))
                               (meander.runtime.zeta/fail))
                              (meander.runtime.zeta/fail)))))
                          (meander.runtime.zeta/fail))))
                       (meander.runtime.zeta/fail)))))]
                  (if
                   (meander.runtime.zeta/fail? result__123518)
                   (recur (clojure.core/next search_space__123517))
                   result__123518))
                 (state__123472)))
               (state__123472))
              (state__123472)))
            (state__123472))
           (state__123472))))
        (state__123472
         []
         (if
          (clojure.core/vector? input__122159)
          (clojure.core/letfn
           [(state__123520
             []
             (if
              (clojure.core/= (clojure.core/count input__122159) 3)
              (clojure.core/let
               [input__122159_nth_0__
                (clojure.core/nth input__122159 0)
                input__122159_nth_1__
                (clojure.core/nth input__122159 1)
                input__122159_nth_2__
                (clojure.core/nth input__122159 2)]
               (clojure.core/case
                input__122159_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__122159_nth_1__)
                 (clojure.core/letfn
                  [(state__123523
                    []
                    (clojure.core/let
                     [n__122359
                      (clojure.core/count input__122159_nth_1__)
                      m__122360
                      (clojure.core/max 0 (clojure.core/- n__122359 2))
                      input__122159_nth_1___l__
                      (clojure.core/subvec
                       input__122159_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__122159_nth_1__)
                        m__122360))
                      input__122159_nth_1___r__
                      (clojure.core/subvec
                       input__122159_nth_1__
                       m__122360)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__122159_nth_1___r__)
                       2)
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__122159_nth_1___l__)]
                       (if
                        (clojure.core/=
                         (clojure.core/count input__122159_nth_1___r__)
                         2)
                        (clojure.core/let
                         [input__122159_nth_1___r___nth_0__
                          (clojure.core/nth
                           input__122159_nth_1___r__
                           0)
                          input__122159_nth_1___r___nth_1__
                          (clojure.core/nth
                           input__122159_nth_1___r__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__122159_nth_1___r___nth_0__)
                          (clojure.core/let
                           [X__122364
                            (clojure.core/namespace
                             input__122159_nth_1___r___nth_0__)]
                           (clojure.core/let
                            [?ns X__122364]
                            (clojure.core/let
                             [X__122366
                              (clojure.core/name
                               input__122159_nth_1___r___nth_0__)]
                             (if
                              (clojure.core/string? X__122366)
                              (clojure.core/let
                               [ret__122367
                                (clojure.core/re-matches
                                 #"&.*"
                                 X__122366)]
                               (if
                                (clojure.core/some? ret__122367)
                                (clojure.core/let
                                 [?name ret__122367]
                                 (clojure.core/let
                                  [?pattern
                                   input__122159_nth_1___r___nth_1__]
                                  (if
                                   (clojure.core/map?
                                    input__122159_nth_2__)
                                   (clojure.core/let
                                    [VAL__122351
                                     (.valAt
                                      input__122159_nth_2__
                                      :aliases)]
                                    (if
                                     (clojure.core/map? VAL__122351)
                                     (clojure.core/let
                                      [X__122353
                                       (clojure.core/set VAL__122351)]
                                      (if
                                       (clojure.core/<=
                                        1
                                        (clojure.core/count X__122353))
                                       (clojure.core/loop
                                        [search_space__123527
                                         (clojure.core/seq X__122353)]
                                        (if
                                         (clojure.core/seq
                                          search_space__123527)
                                         (clojure.core/let
                                          [elem__122354
                                           (clojure.core/first
                                            search_space__123527)
                                           result__123528
                                           (clojure.core/let
                                            [elem__122354_nth_0__
                                             (clojure.core/nth
                                              elem__122354
                                              0)
                                             elem__122354_nth_1__
                                             (clojure.core/nth
                                              elem__122354
                                              1)]
                                            (if
                                             (clojure.core/symbol?
                                              elem__122354_nth_0__)
                                             (clojure.core/let
                                              [X__122356
                                               (clojure.core/name
                                                elem__122354_nth_0__)]
                                              (if
                                               (clojure.core/=
                                                ?ns
                                                X__122356)
                                               (if
                                                (clojure.core/symbol?
                                                 elem__122354_nth_1__)
                                                (clojure.core/let
                                                 [X__122358
                                                  (clojure.core/name
                                                   elem__122354_nth_1__)]
                                                 (clojure.core/case
                                                  X__122358
                                                  ("meander.zeta")
                                                  (clojure.core/let
                                                   [?env
                                                    input__122159_nth_2__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__10883__auto__
                                                        (CATA__FN__122227
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
                                                         CATA_RESULT__10883__auto__)
                                                        (throw
                                                         (meander.runtime.zeta/fail))
                                                        (clojure.core/nth
                                                         CATA_RESULT__10883__auto__
                                                         0))))]
                                                    (catch
                                                     java.lang.Exception
                                                     e__11823__auto__
                                                     (if
                                                      (meander.runtime.zeta/fail?
                                                       e__11823__auto__)
                                                      (meander.runtime.zeta/fail)
                                                      (throw
                                                       e__11823__auto__)))))
                                                  (meander.runtime.zeta/fail)))
                                                (meander.runtime.zeta/fail))
                                               (meander.runtime.zeta/fail)))
                                             (meander.runtime.zeta/fail)))]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            result__123528)
                                           (recur
                                            (clojure.core/next
                                             search_space__123527))
                                           result__123528))
                                         (state__123524)))
                                       (state__123524)))
                                     (state__123524)))
                                   (state__123524))))
                                (state__123524)))
                              (state__123524)))))
                          (state__123524)))
                        (state__123524)))
                      (state__123524))))
                   (state__123524
                    []
                    (clojure.core/loop
                     [search_space__123530
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__122159_nth_1__)]
                     (if
                      (clojure.core/seq search_space__123530)
                      (clojure.core/let
                       [input__122159_nth_1___parts__
                        (clojure.core/first search_space__123530)
                        result__123531
                        (clojure.core/let
                         [input__122159_nth_1___l__
                          (clojure.core/nth
                           input__122159_nth_1___parts__
                           0)
                          input__122159_nth_1___r__
                          (clojure.core/nth
                           input__122159_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs
                           (clojure.core/vec
                            input__122159_nth_1___l__)]
                          (clojure.core/let
                           [input__122159_nth_1___r___l__
                            (clojure.core/subvec
                             input__122159_nth_1___r__
                             0
                             (clojure.core/min
                              (clojure.core/count
                               input__122159_nth_1___r__)
                              1))]
                           (if
                            (clojure.core/=
                             (clojure.core/count
                              input__122159_nth_1___r___l__)
                             1)
                            (clojure.core/let
                             [input__122159_nth_1___r___r__
                              (clojure.core/subvec
                               input__122159_nth_1___r__
                               1)]
                             (if
                              (clojure.core/=
                               input__122159_nth_1___r___l__
                               ['.])
                              (clojure.core/let
                               [?rest input__122159_nth_1___r___r__]
                               (clojure.core/let
                                [?env input__122159_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [!xs__counter
                                    (meander.runtime.zeta/iterator
                                     !xs)]
                                   (clojure.core/let
                                    [CATA_RESULT__10883__auto__
                                     (CATA__FN__122227
                                      ['meander.dev.parse.zeta/make-join
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__122227
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            (clojure.core/vec
                                             (clojure.core/iterator-seq
                                              !xs__counter)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0)))
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__122227
                                          ['meander.dev.parse.zeta/parse-sequential
                                           ?rest
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0)))
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__10883__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__10883__auto__
                                      0))))]
                                 (catch
                                  java.lang.Exception
                                  e__11823__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__11823__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__11823__auto__))))))
                              (meander.runtime.zeta/fail)))
                            (meander.runtime.zeta/fail)))))]
                       (if
                        (meander.runtime.zeta/fail? result__123531)
                        (recur
                         (clojure.core/next search_space__123530))
                        result__123531))
                      (state__123525))))
                   (state__123525
                    []
                    (clojure.core/let
                     [input__122159_nth_1___l__
                      (clojure.core/subvec
                       input__122159_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__122159_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__122159_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__122159_nth_1___r__
                        (clojure.core/subvec input__122159_nth_1__ 1)]
                       (if
                        (clojure.core/=
                         input__122159_nth_1___l__
                         ['...])
                        (clojure.core/let
                         [?rest input__122159_nth_1___r__]
                         (clojure.core/let
                          [?env input__122159_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__10883__auto__
                              (CATA__FN__122227
                               ['meander.dev.parse.zeta/parse-sequential
                                ?rest
                                ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__10883__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__10883__auto__
                               0)))]
                           (catch
                            java.lang.Exception
                            e__11823__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__11823__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__11823__auto__))))))
                        (state__123526)))
                      (state__123526))))
                   (state__123526
                    []
                    (clojure.core/loop
                     [search_space__123533
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__122159_nth_1__)]
                     (if
                      (clojure.core/seq search_space__123533)
                      (clojure.core/let
                       [input__122159_nth_1___parts__
                        (clojure.core/first search_space__123533)
                        result__123534
                        (clojure.core/let
                         [input__122159_nth_1___l__
                          (clojure.core/nth
                           input__122159_nth_1___parts__
                           0)
                          input__122159_nth_1___r__
                          (clojure.core/nth
                           input__122159_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__9744__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__122159_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__122384]
                              (clojure.core/let
                               [input__122384_nth_0__
                                (clojure.core/nth input__122384 0)]
                               (clojure.core/letfn
                                [(save__122385
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__123537
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__122384_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__122384_nth_0__)
                                 (clojure.core/let
                                  [X__122387
                                   (clojure.core/namespace
                                    input__122384_nth_0__)]
                                  (clojure.core/case
                                   X__122387
                                   (nil)
                                   (clojure.core/let
                                    [X__122389
                                     (clojure.core/name
                                      input__122384_nth_0__)]
                                    (if
                                     (clojure.core/string? X__122389)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__122389)
                                      (save__122385)
                                      (f__123537))
                                     (f__123537)))
                                   (f__123537)))
                                 (f__123537)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__122159_nth_1___r___l__
                                (clojure.core/subvec
                                 input__122159_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__122159_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__122159_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__122159_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__122159_nth_1___r__
                                   1)]
                                 (if
                                  (clojure.core/=
                                   input__122159_nth_1___r___l__
                                   ['...])
                                  (clojure.core/let
                                   [?rest
                                    input__122159_nth_1___r___r__]
                                   (clojure.core/let
                                    [?env input__122159_nth_2__]
                                    (try
                                     [(clojure.core/let
                                       [!xs__counter
                                        (meander.runtime.zeta/iterator
                                         !xs)]
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__122227
                                          ['meander.dev.parse.zeta/make-star
                                           (clojure.core/let
                                            [CATA_RESULT__10883__auto__
                                             (CATA__FN__122227
                                              ['meander.dev.parse.zeta/parse-sequential
                                               (clojure.core/into
                                                []
                                                (clojure.core/vec
                                                 (clojure.core/iterator-seq
                                                  !xs__counter)))
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__10883__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__10883__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__10883__auto__
                                             (CATA__FN__122227
                                              ['meander.dev.parse.zeta/parse-sequential
                                               ?rest
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__10883__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__10883__auto__
                                              0)))
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))))]
                                     (catch
                                      java.lang.Exception
                                      e__11823__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11823__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11823__auto__))))))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))))]
                           (if
                            (meander.runtime.zeta/fail?
                             ret__9744__auto__)
                            (meander.runtime.zeta/fail)
                            ret__9744__auto__))))]
                       (if
                        (meander.runtime.zeta/fail? result__123534)
                        (recur
                         (clojure.core/next search_space__123533))
                        result__123534))
                      (state__123521))))]
                  (state__123523))
                 (state__123521))
                (state__123521)))
              (state__123521)))
            (state__123521
             []
             (if
              (clojure.core/= (clojure.core/count input__122159) 4)
              (clojure.core/let
               [input__122159_nth_0__
                (clojure.core/nth input__122159 0)
                input__122159_nth_1__
                (clojure.core/nth input__122159 1)
                input__122159_nth_2__
                (clojure.core/nth input__122159 2)]
               (clojure.core/letfn
                [(state__123538
                  []
                  (clojure.core/let
                   [input__122159_nth_3__
                    (clojure.core/nth input__122159 3)]
                   (clojure.core/case
                    input__122159_nth_0__
                    (meander.dev.parse.zeta/make-star)
                    (clojure.core/letfn
                     [(state__123540
                       []
                       (if
                        (clojure.core/map? input__122159_nth_1__)
                        (clojure.core/let
                         [VAL__122393
                          (.valAt input__122159_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__122393
                          (:cat)
                          (clojure.core/let
                           [VAL__122394
                            (.valAt input__122159_nth_1__ :sequence)]
                           (if
                            (clojure.core/vector? VAL__122394)
                            (if
                             (clojure.core/=
                              (clojure.core/count VAL__122394)
                              1)
                             (clojure.core/let
                              [VAL__122394_nth_0__
                               (clojure.core/nth VAL__122394 0)]
                              (if
                               (clojure.core/map? VAL__122394_nth_0__)
                               (clojure.core/let
                                [VAL__122399
                                 (.valAt VAL__122394_nth_0__ :tag)]
                                (clojure.core/case
                                 VAL__122399
                                 (:memory-variable)
                                 (clojure.core/let
                                  [?memory-variable
                                   VAL__122394_nth_0__]
                                  (clojure.core/let
                                   [VAL__122395
                                    (.valAt
                                     input__122159_nth_1__
                                     :next)]
                                   (if
                                    (clojure.core/map? VAL__122395)
                                    (clojure.core/let
                                     [VAL__122396
                                      (.valAt VAL__122395 :tag)]
                                     (clojure.core/case
                                      VAL__122396
                                      (:empty)
                                      (clojure.core/let
                                       [?next input__122159_nth_2__]
                                       (clojure.core/let
                                        [?env input__122159_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__10883__auto__
                                            (CATA__FN__122227
                                             ['meander.dev.parse.zeta/make-join
                                              {:tag :into,
                                               :memory-variable
                                               ?memory-variable}
                                              ?next
                                              ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             CATA_RESULT__10883__auto__)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             CATA_RESULT__10883__auto__
                                             0)))]
                                         (catch
                                          java.lang.Exception
                                          e__11823__auto__
                                          (if
                                           (meander.runtime.zeta/fail?
                                            e__11823__auto__)
                                           (meander.runtime.zeta/fail)
                                           (throw
                                            e__11823__auto__))))))
                                      (state__123541)))
                                    (state__123541))))
                                 (state__123541)))
                               (state__123541)))
                             (state__123541))
                            (state__123541)))
                          (state__123541)))
                        (state__123541)))
                      (state__123541
                       []
                       (clojure.core/let
                        [?pattern input__122159_nth_1__]
                        (clojure.core/let
                         [?next input__122159_nth_2__]
                         (if
                          (clojure.core/map? input__122159_nth_3__)
                          (clojure.core/let
                           [VAL__122402
                            (.valAt input__122159_nth_3__ :context)]
                           (clojure.core/case
                            VAL__122402
                            (:string)
                            (try
                             [{:tag :string-star,
                               :greedy? false,
                               :pattern ?pattern,
                               :next ?next}]
                             (catch
                              java.lang.Exception
                              e__11823__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__11823__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__11823__auto__))))
                            (state__123539)))
                          (state__123539)))))]
                     (state__123540))
                    (state__123539))))
                 (state__123539
                  []
                  (clojure.core/case
                   input__122159_nth_0__
                   (meander.dev.parse.zeta/make-star)
                   (clojure.core/let
                    [?pattern input__122159_nth_1__]
                    (clojure.core/let
                     [?next input__122159_nth_2__]
                     (try
                      [{:tag :star,
                        :greedy? false,
                        :pattern ?pattern,
                        :next ?next}]
                      (catch
                       java.lang.Exception
                       e__11823__auto__
                       (if
                        (meander.runtime.zeta/fail? e__11823__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__11823__auto__))))))
                   (state__123522)))]
                (state__123538)))
              (state__123522)))
            (state__123522
             []
             (if
              (clojure.core/= (clojure.core/count input__122159) 3)
              (clojure.core/let
               [input__122159_nth_0__
                (clojure.core/nth input__122159 0)
                input__122159_nth_1__
                (clojure.core/nth input__122159 1)
                input__122159_nth_2__
                (clojure.core/nth input__122159 2)]
               (clojure.core/case
                input__122159_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__122159_nth_1__)
                 (clojure.core/let
                  [input__122159_nth_1___l__
                   (clojure.core/subvec
                    input__122159_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__122159_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__122159_nth_1___l__)
                    1)
                   (clojure.core/let
                    [input__122159_nth_1___r__
                     (clojure.core/subvec input__122159_nth_1__ 1)]
                    (clojure.core/let
                     [input__122159_nth_1___l___nth_0__
                      (clojure.core/nth input__122159_nth_1___l__ 0)]
                     (if
                      (clojure.core/symbol?
                       input__122159_nth_1___l___nth_0__)
                      (clojure.core/let
                       [X__122410
                        (clojure.core/namespace
                         input__122159_nth_1___l___nth_0__)]
                       (clojure.core/case
                        X__122410
                        (nil)
                        (clojure.core/let
                         [X__122412
                          (clojure.core/name
                           input__122159_nth_1___l___nth_0__)]
                         (if
                          (clojure.core/string? X__122412)
                          (clojure.core/let
                           [ret__122413
                            (clojure.core/re-matches
                             #"\.\.(\d+)"
                             X__122412)]
                           (if
                            (clojure.core/some? ret__122413)
                            (if
                             (clojure.core/vector? ret__122413)
                             (if
                              (clojure.core/=
                               (clojure.core/count ret__122413)
                               2)
                              (clojure.core/let
                               [ret__122413_nth_1__
                                (clojure.core/nth ret__122413 1)]
                               (clojure.core/let
                                [?n ret__122413_nth_1__]
                                (clojure.core/let
                                 [?operator
                                  input__122159_nth_1___l___nth_0__]
                                 (clojure.core/let
                                  [?rest input__122159_nth_1___r__]
                                  (clojure.core/let
                                   [?env input__122159_nth_2__]
                                   (try
                                    [{:tag :syntax-error,
                                      :message
                                      "The n or more operator ..N must be preceeded by at least one pattern"}]
                                    (catch
                                     java.lang.Exception
                                     e__11823__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11823__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11823__auto__)))))))))
                              (state__123473))
                             (state__123473))
                            (state__123473)))
                          (state__123473)))
                        (state__123473)))
                      (state__123473))))
                   (state__123473)))
                 (state__123473))
                (state__123473)))
              (state__123473)))]
           (state__123520))
          (state__123473)))
        (state__123473
         []
         (clojure.core/letfn
          [(def__122416
            [arg__122440]
            (clojure.core/letfn
             [(state__123542
               []
               (clojure.core/let
                [x__122441 :string-plus]
                (clojure.core/let
                 [?tag x__122441]
                 (if
                  (clojure.core/map? arg__122440)
                  (clojure.core/let
                   [VAL__122442 (.valAt arg__122440 :context)]
                   (clojure.core/case
                    VAL__122442
                    (:string)
                    (clojure.core/let [?env arg__122440] [?tag ?env])
                    (state__123543)))
                  (state__123543)))))
              (state__123543
               []
               (clojure.core/let
                [x__122443 :plus]
                (clojure.core/let
                 [?tag x__122443]
                 (clojure.core/let [?env arg__122440] [?tag ?env]))))]
             (state__123542)))]
          (if
           (clojure.core/vector? input__122159)
           (if
            (clojure.core/= (clojure.core/count input__122159) 3)
            (clojure.core/let
             [input__122159_nth_0__
              (clojure.core/nth input__122159 0)
              input__122159_nth_1__
              (clojure.core/nth input__122159 1)
              input__122159_nth_2__
              (clojure.core/nth input__122159 2)]
             (clojure.core/case
              input__122159_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__122159_nth_1__)
               (clojure.core/loop
                [search_space__123544
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__122159_nth_1__)]
                (if
                 (clojure.core/seq search_space__123544)
                 (clojure.core/let
                  [input__122159_nth_1___parts__
                   (clojure.core/first search_space__123544)
                   result__123545
                   (clojure.core/let
                    [input__122159_nth_1___l__
                     (clojure.core/nth input__122159_nth_1___parts__ 0)
                     input__122159_nth_1___r__
                     (clojure.core/nth
                      input__122159_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__9744__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__122159_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__122433]
                         (clojure.core/let
                          [input__122433_nth_0__
                           (clojure.core/nth input__122433 0)]
                          (clojure.core/letfn
                           [(save__122434
                             []
                             (meander.runtime.zeta/fail))
                            (f__123548
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__122433_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol?
                             input__122433_nth_0__)
                            (clojure.core/let
                             [X__122436
                              (clojure.core/namespace
                               input__122433_nth_0__)]
                             (clojure.core/case
                              X__122436
                              (nil)
                              (clojure.core/let
                               [X__122438
                                (clojure.core/name
                                 input__122433_nth_0__)]
                               (if
                                (clojure.core/string? X__122438)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__122438)
                                 (save__122434)
                                 (f__123548))
                                (f__123548)))
                              (f__123548)))
                            (f__123548)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__122159_nth_1___r___l__
                           (clojure.core/subvec
                            input__122159_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__122159_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__122159_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__122159_nth_1___r___r__
                             (clojure.core/subvec
                              input__122159_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__122159_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__122159_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__122159_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__122427
                                (clojure.core/namespace
                                 input__122159_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__122427
                                (nil)
                                (clojure.core/let
                                 [X__122429
                                  (clojure.core/name
                                   input__122159_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__122429)
                                  (clojure.core/let
                                   [ret__122430
                                    (clojure.core/re-matches
                                     #"\.\.(\d+)"
                                     X__122429)]
                                   (if
                                    (clojure.core/some? ret__122430)
                                    (if
                                     (clojure.core/vector? ret__122430)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__122430)
                                       2)
                                      (clojure.core/let
                                       [ret__122430_nth_1__
                                        (clojure.core/nth
                                         ret__122430
                                         1)]
                                       (clojure.core/let
                                        [?n ret__122430_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__122159_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__9580__auto__
                                           (def__122416
                                            input__122159_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__9580__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__9580__auto__]
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
                                                 [CATA_RESULT__10883__auto__
                                                  (CATA__FN__122227
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10883__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10883__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__10883__auto__
                                                  (CATA__FN__122227
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10883__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10883__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__11823__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__11823__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__11823__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__9744__auto__)
                       (meander.runtime.zeta/fail)
                       ret__9744__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__123545)
                   (recur (clojure.core/next search_space__123544))
                   result__123545))
                 (state__123474)))
               (state__123474))
              (state__123474)))
            (state__123474))
           (state__123474))))
        (state__123474
         []
         (if
          (clojure.core/vector? input__122159)
          (if
           (clojure.core/= (clojure.core/count input__122159) 3)
           (clojure.core/let
            [input__122159_nth_0__
             (clojure.core/nth input__122159 0)
             input__122159_nth_1__
             (clojure.core/nth input__122159 1)
             input__122159_nth_2__
             (clojure.core/nth input__122159 2)]
            (clojure.core/case
             input__122159_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__122159_nth_1__)
              (clojure.core/let
               [input__122159_nth_1___l__
                (clojure.core/subvec
                 input__122159_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__122159_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__122159_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__122159_nth_1___r__
                  (clojure.core/subvec input__122159_nth_1__ 1)]
                 (clojure.core/let
                  [input__122159_nth_1___l___nth_0__
                   (clojure.core/nth input__122159_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__122159_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__122461
                     (clojure.core/namespace
                      input__122159_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__122461
                     (nil)
                     (clojure.core/let
                      [X__122463
                       (clojure.core/name
                        input__122159_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__122463)
                       (clojure.core/let
                        [ret__122464
                         (clojure.core/re-matches
                          #"\.\.(\?.+)"
                          X__122463)]
                        (if
                         (clojure.core/some? ret__122464)
                         (if
                          (clojure.core/vector? ret__122464)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__122464)
                            2)
                           (clojure.core/let
                            [ret__122464_nth_1__
                             (clojure.core/nth ret__122464 1)]
                            (clojure.core/let
                             [?n ret__122464_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__122159_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__122159_nth_1___r__]
                               (clojure.core/let
                                [?env input__122159_nth_2__]
                                (try
                                 [{:tag :syntax-error,
                                   :message
                                   "The ?n or more operator ..?n must be preceeded by at least one pattern"}]
                                 (catch
                                  java.lang.Exception
                                  e__11823__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__11823__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__11823__auto__)))))))))
                           (state__123475))
                          (state__123475))
                         (state__123475)))
                       (state__123475)))
                     (state__123475)))
                   (state__123475))))
                (state__123475)))
              (state__123475))
             (state__123475)))
           (state__123475))
          (state__123475)))
        (state__123475
         []
         (clojure.core/letfn
          [(def__122467
            [arg__122491]
            (clojure.core/letfn
             [(state__123549
               []
               (clojure.core/let
                [x__122492 :string-logical-plus]
                (clojure.core/let
                 [?tag x__122492]
                 (if
                  (clojure.core/map? arg__122491)
                  (clojure.core/let
                   [VAL__122493 (.valAt arg__122491 :context)]
                   (clojure.core/case
                    VAL__122493
                    (:string)
                    (clojure.core/let [?env arg__122491] [?tag ?env])
                    (state__123550)))
                  (state__123550)))))
              (state__123550
               []
               (clojure.core/let
                [x__122494 :logical-plus]
                (clojure.core/let
                 [?tag x__122494]
                 (clojure.core/let [?env arg__122491] [?tag ?env]))))]
             (state__123549)))]
          (if
           (clojure.core/vector? input__122159)
           (if
            (clojure.core/= (clojure.core/count input__122159) 3)
            (clojure.core/let
             [input__122159_nth_0__
              (clojure.core/nth input__122159 0)
              input__122159_nth_1__
              (clojure.core/nth input__122159 1)
              input__122159_nth_2__
              (clojure.core/nth input__122159 2)]
             (clojure.core/case
              input__122159_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__122159_nth_1__)
               (clojure.core/loop
                [search_space__123551
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__122159_nth_1__)]
                (if
                 (clojure.core/seq search_space__123551)
                 (clojure.core/let
                  [input__122159_nth_1___parts__
                   (clojure.core/first search_space__123551)
                   result__123552
                   (clojure.core/let
                    [input__122159_nth_1___l__
                     (clojure.core/nth input__122159_nth_1___parts__ 0)
                     input__122159_nth_1___r__
                     (clojure.core/nth
                      input__122159_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__9744__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__122159_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__122484]
                         (clojure.core/let
                          [input__122484_nth_0__
                           (clojure.core/nth input__122484 0)]
                          (clojure.core/letfn
                           [(save__122485
                             []
                             (meander.runtime.zeta/fail))
                            (f__123555
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__122484_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol?
                             input__122484_nth_0__)
                            (clojure.core/let
                             [X__122487
                              (clojure.core/namespace
                               input__122484_nth_0__)]
                             (clojure.core/case
                              X__122487
                              (nil)
                              (clojure.core/let
                               [X__122489
                                (clojure.core/name
                                 input__122484_nth_0__)]
                               (if
                                (clojure.core/string? X__122489)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__122489)
                                 (save__122485)
                                 (f__123555))
                                (f__123555)))
                              (f__123555)))
                            (f__123555)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__122159_nth_1___r___l__
                           (clojure.core/subvec
                            input__122159_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__122159_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__122159_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__122159_nth_1___r___r__
                             (clojure.core/subvec
                              input__122159_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__122159_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__122159_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__122159_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__122478
                                (clojure.core/namespace
                                 input__122159_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__122478
                                (nil)
                                (clojure.core/let
                                 [X__122480
                                  (clojure.core/name
                                   input__122159_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__122480)
                                  (clojure.core/let
                                   [ret__122481
                                    (clojure.core/re-matches
                                     #"\.\.(\?.+)"
                                     X__122480)]
                                   (if
                                    (clojure.core/some? ret__122481)
                                    (if
                                     (clojure.core/vector? ret__122481)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__122481)
                                       2)
                                      (clojure.core/let
                                       [ret__122481_nth_1__
                                        (clojure.core/nth
                                         ret__122481
                                         1)]
                                       (clojure.core/let
                                        [?n ret__122481_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__122159_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__9580__auto__
                                           (def__122467
                                            input__122159_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__9580__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__9580__auto__]
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
                                                 [CATA_RESULT__10883__auto__
                                                  (CATA__FN__122227
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10883__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10883__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__10883__auto__
                                                  (CATA__FN__122227
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10883__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10883__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__11823__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__11823__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__11823__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__9744__auto__)
                       (meander.runtime.zeta/fail)
                       ret__9744__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__123552)
                   (recur (clojure.core/next search_space__123551))
                   result__123552))
                 (state__123476)))
               (state__123476))
              (state__123476)))
            (state__123476))
           (state__123476))))
        (state__123476
         []
         (if
          (clojure.core/vector? input__122159)
          (if
           (clojure.core/= (clojure.core/count input__122159) 3)
           (clojure.core/let
            [input__122159_nth_0__
             (clojure.core/nth input__122159 0)
             input__122159_nth_1__
             (clojure.core/nth input__122159 1)
             input__122159_nth_2__
             (clojure.core/nth input__122159 2)]
            (clojure.core/case
             input__122159_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__122159_nth_1__)
              (clojure.core/let
               [input__122159_nth_1___l__
                (clojure.core/subvec
                 input__122159_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__122159_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__122159_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__122159_nth_1___r__
                  (clojure.core/subvec input__122159_nth_1__ 1)]
                 (clojure.core/let
                  [input__122159_nth_1___l___nth_0__
                   (clojure.core/nth input__122159_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__122159_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__122512
                     (clojure.core/namespace
                      input__122159_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__122512
                     (nil)
                     (clojure.core/let
                      [X__122514
                       (clojure.core/name
                        input__122159_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__122514)
                       (clojure.core/let
                        [ret__122515
                         (clojure.core/re-matches
                          #"\.\.(!.+)"
                          X__122514)]
                        (if
                         (clojure.core/some? ret__122515)
                         (if
                          (clojure.core/vector? ret__122515)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__122515)
                            2)
                           (clojure.core/let
                            [ret__122515_nth_1__
                             (clojure.core/nth ret__122515 1)]
                            (clojure.core/let
                             [?n ret__122515_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__122159_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__122159_nth_1___r__]
                               (clojure.core/let
                                [?env input__122159_nth_2__]
                                (try
                                 [{:tag :syntax-error,
                                   :message
                                   "The operator ..!n must be preceeded by at least one pattern"}]
                                 (catch
                                  java.lang.Exception
                                  e__11823__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__11823__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__11823__auto__)))))))))
                           (state__123477))
                          (state__123477))
                         (state__123477)))
                       (state__123477)))
                     (state__123477)))
                   (state__123477))))
                (state__123477)))
              (state__123477))
             (state__123477)))
           (state__123477))
          (state__123477)))
        (state__123477
         []
         (clojure.core/letfn
          [(def__122518
            [arg__122542]
            (clojure.core/letfn
             [(state__123556
               []
               (clojure.core/let
                [x__122543 :string-memory-plus]
                (clojure.core/let
                 [?tag x__122543]
                 (if
                  (clojure.core/map? arg__122542)
                  (clojure.core/let
                   [VAL__122544 (.valAt arg__122542 :context)]
                   (clojure.core/case
                    VAL__122544
                    (:string)
                    (clojure.core/let [?env arg__122542] [?tag ?env])
                    (state__123557)))
                  (state__123557)))))
              (state__123557
               []
               (clojure.core/let
                [x__122545 :memory-plus]
                (clojure.core/let
                 [?tag x__122545]
                 (clojure.core/let [?env arg__122542] [?tag ?env]))))]
             (state__123556)))]
          (if
           (clojure.core/vector? input__122159)
           (if
            (clojure.core/= (clojure.core/count input__122159) 3)
            (clojure.core/let
             [input__122159_nth_0__
              (clojure.core/nth input__122159 0)
              input__122159_nth_1__
              (clojure.core/nth input__122159 1)
              input__122159_nth_2__
              (clojure.core/nth input__122159 2)]
             (clojure.core/case
              input__122159_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__122159_nth_1__)
               (clojure.core/loop
                [search_space__123558
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__122159_nth_1__)]
                (if
                 (clojure.core/seq search_space__123558)
                 (clojure.core/let
                  [input__122159_nth_1___parts__
                   (clojure.core/first search_space__123558)
                   result__123559
                   (clojure.core/let
                    [input__122159_nth_1___l__
                     (clojure.core/nth input__122159_nth_1___parts__ 0)
                     input__122159_nth_1___r__
                     (clojure.core/nth
                      input__122159_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__9744__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__122159_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__122535]
                         (clojure.core/let
                          [input__122535_nth_0__
                           (clojure.core/nth input__122535 0)]
                          (clojure.core/letfn
                           [(save__122536
                             []
                             (meander.runtime.zeta/fail))
                            (f__123562
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__122535_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol?
                             input__122535_nth_0__)
                            (clojure.core/let
                             [X__122538
                              (clojure.core/namespace
                               input__122535_nth_0__)]
                             (clojure.core/case
                              X__122538
                              (nil)
                              (clojure.core/let
                               [X__122540
                                (clojure.core/name
                                 input__122535_nth_0__)]
                               (if
                                (clojure.core/string? X__122540)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__122540)
                                 (save__122536)
                                 (f__123562))
                                (f__123562)))
                              (f__123562)))
                            (f__123562)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__122159_nth_1___r___l__
                           (clojure.core/subvec
                            input__122159_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__122159_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__122159_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__122159_nth_1___r___r__
                             (clojure.core/subvec
                              input__122159_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__122159_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__122159_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__122159_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__122529
                                (clojure.core/namespace
                                 input__122159_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__122529
                                (nil)
                                (clojure.core/let
                                 [X__122531
                                  (clojure.core/name
                                   input__122159_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__122531)
                                  (clojure.core/let
                                   [ret__122532
                                    (clojure.core/re-matches
                                     #"\.\.(\!.+)"
                                     X__122531)]
                                   (if
                                    (clojure.core/some? ret__122532)
                                    (if
                                     (clojure.core/vector? ret__122532)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__122532)
                                       2)
                                      (clojure.core/let
                                       [ret__122532_nth_1__
                                        (clojure.core/nth
                                         ret__122532
                                         1)]
                                       (clojure.core/let
                                        [?n ret__122532_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__122159_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__9580__auto__
                                           (def__122518
                                            input__122159_nth_2__)]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            x__9580__auto__)
                                           (meander.runtime.zeta/fail)
                                           (clojure.core/let
                                            [[?tag ?env]
                                             x__9580__auto__]
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
                                                 [CATA_RESULT__10883__auto__
                                                  (CATA__FN__122227
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    (clojure.core/into
                                                     []
                                                     (clojure.core/vec
                                                      (clojure.core/iterator-seq
                                                       !xs__counter)))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10883__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10883__auto__
                                                   0))),
                                                :next
                                                (clojure.core/let
                                                 [CATA_RESULT__10883__auto__
                                                  (CATA__FN__122227
                                                   ['meander.dev.parse.zeta/parse-sequential
                                                    ?rest
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10883__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10883__auto__
                                                   0)))})]
                                             (catch
                                              java.lang.Exception
                                              e__11823__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__11823__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__11823__auto__))))))))))
                                      (meander.runtime.zeta/fail))
                                     (meander.runtime.zeta/fail))
                                    (meander.runtime.zeta/fail)))
                                  (meander.runtime.zeta/fail)))
                                (meander.runtime.zeta/fail)))
                              (meander.runtime.zeta/fail))))
                           (meander.runtime.zeta/fail)))))]
                      (if
                       (meander.runtime.zeta/fail? ret__9744__auto__)
                       (meander.runtime.zeta/fail)
                       ret__9744__auto__))))]
                  (if
                   (meander.runtime.zeta/fail? result__123559)
                   (recur (clojure.core/next search_space__123558))
                   result__123559))
                 (state__123478)))
               (state__123478))
              (state__123478)))
            (state__123478))
           (state__123478))))
        (state__123478
         []
         (if
          (clojure.core/vector? input__122159)
          (clojure.core/letfn
           [(state__123563
             []
             (if
              (clojure.core/= (clojure.core/count input__122159) 3)
              (clojure.core/let
               [input__122159_nth_0__
                (clojure.core/nth input__122159 0)
                input__122159_nth_1__
                (clojure.core/nth input__122159 1)
                input__122159_nth_2__
                (clojure.core/nth input__122159 2)]
               (clojure.core/case
                input__122159_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__122159_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__122159_nth_1__)]
                  (clojure.core/let
                   [?env input__122159_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__10883__auto__
                        (CATA__FN__122227
                         ['meander.dev.parse.zeta/make-cat
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__122228
                             (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (recur
                              (clojure.core/conj!
                               return__122228
                               (clojure.core/let
                                [CATA_RESULT__10883__auto__
                                 (CATA__FN__122227
                                  [(if
                                    (.hasNext !xs__counter)
                                    (.next !xs__counter))
                                   ?env])]
                                (if
                                 (meander.runtime.zeta/fail?
                                  CATA_RESULT__10883__auto__)
                                 (throw (meander.runtime.zeta/fail))
                                 (clojure.core/nth
                                  CATA_RESULT__10883__auto__
                                  0)))))
                             (clojure.core/persistent!
                              return__122228))))
                          {:tag :empty}
                          ?env])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__10883__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__10883__auto__
                         0))))]
                    (catch
                     java.lang.Exception
                     e__11823__auto__
                     (if
                      (meander.runtime.zeta/fail? e__11823__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__11823__auto__))))))
                 (state__123564))
                (state__123564)))
              (state__123564)))
            (state__123564
             []
             (if
              (clojure.core/= (clojure.core/count input__122159) 4)
              (clojure.core/let
               [input__122159_nth_0__
                (clojure.core/nth input__122159 0)
                input__122159_nth_1__
                (clojure.core/nth input__122159 1)
                input__122159_nth_2__
                (clojure.core/nth input__122159 2)]
               (clojure.core/letfn
                [(state__123566
                  []
                  (clojure.core/let
                   [input__122159_nth_3__
                    (clojure.core/nth input__122159 3)]
                   (clojure.core/case
                    input__122159_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (if
                     (clojure.core/vector? input__122159_nth_1__)
                     (clojure.core/letfn
                      [(state__123573
                        []
                        (clojure.core/case
                         input__122159_nth_1__
                         ([])
                         (clojure.core/let
                          [?next input__122159_nth_2__]
                          (clojure.core/let
                           [?env input__122159_nth_3__]
                           (try
                            [?next]
                            (catch
                             java.lang.Exception
                             e__11823__auto__
                             (if
                              (meander.runtime.zeta/fail?
                               e__11823__auto__)
                              (meander.runtime.zeta/fail)
                              (throw e__11823__auto__))))))
                         (state__123574)))
                       (state__123574
                        []
                        (clojure.core/loop
                         [search_space__123575
                          (meander.runtime.zeta/epsilon-partitions
                           2
                           input__122159_nth_1__)]
                         (if
                          (clojure.core/seq search_space__123575)
                          (clojure.core/let
                           [input__122159_nth_1___parts__
                            (clojure.core/first search_space__123575)
                            result__123576
                            (clojure.core/let
                             [input__122159_nth_1___l__
                              (clojure.core/nth
                               input__122159_nth_1___parts__
                               0)
                              input__122159_nth_1___r__
                              (clojure.core/nth
                               input__122159_nth_1___parts__
                               1)]
                             (clojure.core/letfn
                              [(state__123578
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__9744__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__122159_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__122571]
                                     (clojure.core/let
                                      [input__122571_nth_0__
                                       (clojure.core/nth
                                        input__122571
                                        0)]
                                      (clojure.core/letfn
                                       [(save__122572
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__123582
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__122571_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__122571_nth_0__)
                                        (clojure.core/let
                                         [VAL__122573
                                          (.valAt
                                           input__122571_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__122573
                                          (:group)
                                          (save__122572)
                                          (f__123582)))
                                        (f__123582)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__122159_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__122159_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__122159_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__122159_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__122159_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__122159_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__122159_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__122159_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__122159_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__122570
                                            (.valAt
                                             input__122159_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__122570
                                            (:group)
                                            (clojure.core/let
                                             [?group
                                              input__122159_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__122159_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__122159_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__122159_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__10883__auto__
                                                     (CATA__FN__122227
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__10883__auto__
                                                         (CATA__FN__122227
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
                                                          CATA_RESULT__10883__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__10883__auto__
                                                          0)))
                                                       (clojure.core/let
                                                        [CATA_RESULT__10883__auto__
                                                         (CATA__FN__122227
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?group
                                                           (clojure.core/let
                                                            [CATA_RESULT__10883__auto__
                                                             (CATA__FN__122227
                                                              ['meander.dev.parse.zeta/make-cat
                                                               ?rest
                                                               ?next
                                                               ?env])]
                                                            (if
                                                             (meander.runtime.zeta/fail?
                                                              CATA_RESULT__10883__auto__)
                                                             (throw
                                                              (meander.runtime.zeta/fail))
                                                             (clojure.core/nth
                                                              CATA_RESULT__10883__auto__
                                                              0)))
                                                           ?env])]
                                                        (if
                                                         (meander.runtime.zeta/fail?
                                                          CATA_RESULT__10883__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__10883__auto__
                                                          0)))
                                                       ?env])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__10883__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__10883__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__11823__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__11823__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__11823__auto__))))))))
                                            (state__123579)))
                                          (state__123579))))
                                       (state__123579)))))]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    ret__9744__auto__)
                                   (state__123579)
                                   ret__9744__auto__))))
                               (state__123579
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__9744__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__122159_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__122582]
                                     (clojure.core/let
                                      [input__122582_nth_0__
                                       (clojure.core/nth
                                        input__122582
                                        0)]
                                      (clojure.core/letfn
                                       [(save__122583
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__123584
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__122582_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__122582_nth_0__)
                                        (clojure.core/let
                                         [VAL__122584
                                          (.valAt
                                           input__122582_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__122584
                                          (:star)
                                          (save__122583)
                                          (f__123584)))
                                        (f__123584)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__122159_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__122159_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__122159_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__122159_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__122159_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__122159_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__122159_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__122159_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__122159_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__122581
                                            (.valAt
                                             input__122159_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__122581
                                            (:star)
                                            (clojure.core/let
                                             [?star
                                              input__122159_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__122159_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__122159_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__122159_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__10883__auto__
                                                     (CATA__FN__122227
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__10883__auto__
                                                         (CATA__FN__122227
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
                                                          CATA_RESULT__10883__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__10883__auto__
                                                          0)))
                                                       (clojure.core/let
                                                        [CATA_RESULT__10883__auto__
                                                         (CATA__FN__122227
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?star
                                                           (clojure.core/let
                                                            [CATA_RESULT__10883__auto__
                                                             (CATA__FN__122227
                                                              ['meander.dev.parse.zeta/make-cat
                                                               ?rest
                                                               ?next
                                                               ?env])]
                                                            (if
                                                             (meander.runtime.zeta/fail?
                                                              CATA_RESULT__10883__auto__)
                                                             (throw
                                                              (meander.runtime.zeta/fail))
                                                             (clojure.core/nth
                                                              CATA_RESULT__10883__auto__
                                                              0)))
                                                           ?env])]
                                                        (if
                                                         (meander.runtime.zeta/fail?
                                                          CATA_RESULT__10883__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__10883__auto__
                                                          0)))
                                                       ?env])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__10883__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__10883__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__11823__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__11823__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__11823__auto__))))))))
                                            (state__123580)))
                                          (state__123580))))
                                       (state__123580)))))]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    ret__9744__auto__)
                                   (state__123580)
                                   ret__9744__auto__))))
                               (state__123580
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__9744__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__122159_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__122593]
                                     (clojure.core/let
                                      [input__122593_nth_0__
                                       (clojure.core/nth
                                        input__122593
                                        0)]
                                      (clojure.core/letfn
                                       [(save__122594
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__123586
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__122593_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__122593_nth_0__)
                                        (clojure.core/let
                                         [VAL__122595
                                          (.valAt
                                           input__122593_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__122595
                                          (:reference)
                                          (save__122594)
                                          (f__123586)))
                                        (f__123586)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__122159_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__122159_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__122159_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__122159_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__122159_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__122159_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__122159_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__122159_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__122159_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__122592
                                            (.valAt
                                             input__122159_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__122592
                                            (:reference)
                                            (clojure.core/let
                                             [?reference
                                              input__122159_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__122159_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__122159_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__122159_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__10883__auto__
                                                     (CATA__FN__122227
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__10883__auto__
                                                         (CATA__FN__122227
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
                                                          CATA_RESULT__10883__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__10883__auto__
                                                          0)))
                                                       (clojure.core/let
                                                        [CATA_RESULT__10883__auto__
                                                         (CATA__FN__122227
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?reference
                                                           (clojure.core/let
                                                            [CATA_RESULT__10883__auto__
                                                             (CATA__FN__122227
                                                              ['meander.dev.parse.zeta/make-cat
                                                               ?rest
                                                               ?next
                                                               ?env])]
                                                            (if
                                                             (meander.runtime.zeta/fail?
                                                              CATA_RESULT__10883__auto__)
                                                             (throw
                                                              (meander.runtime.zeta/fail))
                                                             (clojure.core/nth
                                                              CATA_RESULT__10883__auto__
                                                              0)))
                                                           ?env])]
                                                        (if
                                                         (meander.runtime.zeta/fail?
                                                          CATA_RESULT__10883__auto__)
                                                         (throw
                                                          (meander.runtime.zeta/fail))
                                                         (clojure.core/nth
                                                          CATA_RESULT__10883__auto__
                                                          0)))
                                                       ?env])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__10883__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__10883__auto__
                                                      0))))]
                                                 (catch
                                                  java.lang.Exception
                                                  e__11823__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__11823__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__11823__auto__))))))))
                                            (meander.runtime.zeta/fail)))
                                          (meander.runtime.zeta/fail))))
                                       (meander.runtime.zeta/fail)))))]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    ret__9744__auto__)
                                   (meander.runtime.zeta/fail)
                                   ret__9744__auto__))))]
                              (state__123578)))]
                           (if
                            (meander.runtime.zeta/fail? result__123576)
                            (recur
                             (clojure.core/next search_space__123575))
                            result__123576))
                          (state__123567))))]
                      (state__123573))
                     (state__123567))
                    (state__123567))))
                 (state__123567
                  []
                  (clojure.core/case
                   input__122159_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (if
                    (clojure.core/vector? input__122159_nth_1__)
                    (clojure.core/let
                     [input__122159_nth_1___l__
                      (clojure.core/subvec
                       input__122159_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__122159_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__122159_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__122159_nth_1___r__
                        (clojure.core/subvec input__122159_nth_1__ 1)]
                       (clojure.core/let
                        [input__122159_nth_1___l___nth_0__
                         (clojure.core/nth
                          input__122159_nth_1___l__
                          0)]
                        (if
                         (clojure.core/map?
                          input__122159_nth_1___l___nth_0__)
                         (clojure.core/let
                          [VAL__122604
                           (.valAt
                            input__122159_nth_1___l___nth_0__
                            :tag)]
                          (clojure.core/case
                           VAL__122604
                           (:literal)
                           (clojure.core/let
                            [VAL__122605
                             (.valAt
                              input__122159_nth_1___l___nth_0__
                              :type)]
                            (if
                             (clojure.core/let
                              [x__8640__auto__ VAL__122605]
                              (clojure.core/case
                               x__8640__auto__
                               (:string :char)
                               true
                               false))
                             (clojure.core/let
                              [VAL__122606
                               (.valAt
                                input__122159_nth_1___l___nth_0__
                                :form)]
                              (clojure.core/let
                               [!forms []]
                               (clojure.core/let
                                [!forms
                                 (clojure.core/conj
                                  !forms
                                  VAL__122606)]
                                (clojure.core/loop
                                 [i__9717__auto__
                                  0
                                  coll__123587
                                  input__122159_nth_1___r__
                                  [!forms]
                                  [!forms]]
                                 (clojure.core/let
                                  [input__122607
                                   (clojure.core/subvec
                                    coll__123587
                                    0
                                    (clojure.core/min
                                     (clojure.core/count coll__123587)
                                     1))]
                                  (if
                                   (clojure.core/=
                                    (clojure.core/count input__122607)
                                    1)
                                   (clojure.core/let
                                    [result__9718__auto__
                                     (clojure.core/let
                                      [input__122607_nth_0__
                                       (clojure.core/nth
                                        input__122607
                                        0)]
                                      (if
                                       (clojure.core/map?
                                        input__122607_nth_0__)
                                       (clojure.core/let
                                        [VAL__122608
                                         (.valAt
                                          input__122607_nth_0__
                                          :tag)]
                                        (clojure.core/case
                                         VAL__122608
                                         (:literal)
                                         (clojure.core/let
                                          [VAL__122609
                                           (.valAt
                                            input__122607_nth_0__
                                            :type)]
                                          (if
                                           (clojure.core/let
                                            [x__8640__auto__
                                             VAL__122609]
                                            (clojure.core/case
                                             x__8640__auto__
                                             (:string :char)
                                             true
                                             false))
                                           (clojure.core/let
                                            [VAL__122610
                                             (.valAt
                                              input__122607_nth_0__
                                              :form)]
                                            (clojure.core/let
                                             [!forms
                                              (clojure.core/conj
                                               !forms
                                               VAL__122610)]
                                             [!forms]))
                                           (meander.runtime.zeta/fail)))
                                         (meander.runtime.zeta/fail)))
                                       (meander.runtime.zeta/fail)))]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      result__9718__auto__)
                                     (state__123568)
                                     (recur
                                      (clojure.core/inc
                                       i__9717__auto__)
                                      (clojure.core/subvec
                                       coll__123587
                                       1)
                                      result__9718__auto__)))
                                   (if
                                    (clojure.core/or
                                     (clojure.core/seq coll__123587)
                                     (clojure.core/<
                                      i__9717__auto__
                                      0))
                                    (state__123568)
                                    (if
                                     (clojure.core/map?
                                      input__122159_nth_2__)
                                     (clojure.core/let
                                      [VAL__122599
                                       (.valAt
                                        input__122159_nth_2__
                                        :tag)]
                                      (clojure.core/case
                                       VAL__122599
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
                                         e__11823__auto__
                                         (if
                                          (meander.runtime.zeta/fail?
                                           e__11823__auto__)
                                          (meander.runtime.zeta/fail)
                                          (throw e__11823__auto__))))
                                       (state__123568)))
                                     (state__123568)))))))))
                             (state__123568)))
                           (state__123568)))
                         (state__123568))))
                      (state__123568)))
                    (state__123568))
                   (state__123568)))
                 (state__123568
                  []
                  (clojure.core/let
                   [input__122159_nth_3__
                    (clojure.core/nth input__122159 3)]
                   (clojure.core/case
                    input__122159_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (clojure.core/letfn
                     [(state__123588
                       []
                       (if
                        (clojure.core/vector? input__122159_nth_1__)
                        (clojure.core/let
                         [input__122159_nth_1___l__
                          (clojure.core/subvec
                           input__122159_nth_1__
                           0
                           (clojure.core/min
                            (clojure.core/count input__122159_nth_1__)
                            1))]
                         (if
                          (clojure.core/=
                           (clojure.core/count
                            input__122159_nth_1___l__)
                           1)
                          (clojure.core/let
                           [input__122159_nth_1___r__
                            (clojure.core/subvec
                             input__122159_nth_1__
                             1)]
                           (clojure.core/let
                            [input__122159_nth_1___l___nth_0__
                             (clojure.core/nth
                              input__122159_nth_1___l__
                              0)]
                            (if
                             (clojure.core/map?
                              input__122159_nth_1___l___nth_0__)
                             (clojure.core/let
                              [VAL__123465
                               (.valAt
                                input__122159_nth_1___l___nth_0__
                                :tag)]
                              (clojure.core/case
                               VAL__123465
                               (:literal)
                               (clojure.core/letfn
                                [(state__123590
                                  []
                                  (clojure.core/let
                                   [VAL__122617
                                    (.valAt
                                     input__122159_nth_1___l___nth_0__
                                     :type)]
                                   (clojure.core/case
                                    VAL__122617
                                    (:string)
                                    (clojure.core/let
                                     [?ast
                                      input__122159_nth_1___l___nth_0__]
                                     (clojure.core/let
                                      [?rest input__122159_nth_1___r__]
                                      (clojure.core/let
                                       [?next input__122159_nth_2__]
                                       (clojure.core/let
                                        [?env input__122159_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__10883__auto__
                                            (CATA__FN__122227
                                             ['meander.dev.parse.zeta/make-join
                                              ?ast
                                              (clojure.core/let
                                               [CATA_RESULT__10883__auto__
                                                (CATA__FN__122227
                                                 ['meander.dev.parse.zeta/make-cat
                                                  ?rest
                                                  ?next
                                                  ?env])]
                                               (if
                                                (meander.runtime.zeta/fail?
                                                 CATA_RESULT__10883__auto__)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 CATA_RESULT__10883__auto__
                                                 0)))
                                              ?env])]
                                           (if
                                            (meander.runtime.zeta/fail?
                                             CATA_RESULT__10883__auto__)
                                            (throw
                                             (meander.runtime.zeta/fail))
                                            (clojure.core/nth
                                             CATA_RESULT__10883__auto__
                                             0)))]
                                         (catch
                                          java.lang.Exception
                                          e__11823__auto__
                                          (if
                                           (meander.runtime.zeta/fail?
                                            e__11823__auto__)
                                           (meander.runtime.zeta/fail)
                                           (throw
                                            e__11823__auto__))))))))
                                    (state__123591))))
                                 (state__123591
                                  []
                                  (clojure.core/let
                                   [VAL__122627
                                    (.valAt
                                     input__122159_nth_1___l___nth_0__
                                     :form)]
                                   (clojure.core/let
                                    [!forms []]
                                    (clojure.core/let
                                     [!forms
                                      (clojure.core/conj
                                       !forms
                                       VAL__122627)]
                                     (clojure.core/loop
                                      [i__9717__auto__
                                       0
                                       coll__123592
                                       input__122159_nth_1___r__
                                       [!forms]
                                       [!forms]]
                                      (clojure.core/let
                                       [input__122628
                                        (clojure.core/subvec
                                         coll__123592
                                         0
                                         (clojure.core/min
                                          (clojure.core/count
                                           coll__123592)
                                          1))]
                                       (if
                                        (clojure.core/=
                                         (clojure.core/count
                                          input__122628)
                                         1)
                                        (clojure.core/let
                                         [result__9718__auto__
                                          (clojure.core/let
                                           [input__122628_nth_0__
                                            (clojure.core/nth
                                             input__122628
                                             0)]
                                           (if
                                            (clojure.core/map?
                                             input__122628_nth_0__)
                                            (clojure.core/let
                                             [VAL__122629
                                              (.valAt
                                               input__122628_nth_0__
                                               :tag)]
                                             (clojure.core/case
                                              VAL__122629
                                              (:literal)
                                              (clojure.core/let
                                               [VAL__122630
                                                (.valAt
                                                 input__122628_nth_0__
                                                 :form)]
                                               (clojure.core/let
                                                [!forms
                                                 (clojure.core/conj
                                                  !forms
                                                  VAL__122630)]
                                                [!forms]))
                                              (meander.runtime.zeta/fail)))
                                            (meander.runtime.zeta/fail)))]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           result__9718__auto__)
                                          (state__123589)
                                          (recur
                                           (clojure.core/inc
                                            i__9717__auto__)
                                           (clojure.core/subvec
                                            coll__123592
                                            1)
                                           result__9718__auto__)))
                                        (if
                                         (clojure.core/or
                                          (clojure.core/seq
                                           coll__123592)
                                          (clojure.core/<
                                           i__9717__auto__
                                           0))
                                         (state__123589)
                                         (if
                                          (clojure.core/map?
                                           input__122159_nth_2__)
                                          (clojure.core/let
                                           [VAL__122620
                                            (.valAt
                                             input__122159_nth_2__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__122620
                                            (:empty)
                                            (if
                                             (clojure.core/map?
                                              input__122159_nth_3__)
                                             (clojure.core/let
                                              [VAL__122621
                                               (.valAt
                                                input__122159_nth_3__
                                                :context)]
                                              (clojure.core/let
                                               [?context VAL__122621]
                                               (clojure.core/let
                                                [?env
                                                 input__122159_nth_3__]
                                                (try
                                                 [{:tag :literal,
                                                   :type ?context,
                                                   :form
                                                   (clojure.core/into
                                                    []
                                                    !forms)}]
                                                 (catch
                                                  java.lang.Exception
                                                  e__11823__auto__
                                                  (if
                                                   (meander.runtime.zeta/fail?
                                                    e__11823__auto__)
                                                   (meander.runtime.zeta/fail)
                                                   (throw
                                                    e__11823__auto__)))))))
                                             (state__123589))
                                            (state__123589)))
                                          (state__123589))))))))))]
                                (state__123590))
                               (state__123589)))
                             (state__123589))))
                          (state__123589)))
                        (state__123589)))
                      (state__123589
                       []
                       (clojure.core/let
                        [?sequence input__122159_nth_1__]
                        (clojure.core/let
                         [?next input__122159_nth_2__]
                         (if
                          (clojure.core/map? input__122159_nth_3__)
                          (clojure.core/let
                           [VAL__122634
                            (.valAt input__122159_nth_3__ :context)]
                           (clojure.core/case
                            VAL__122634
                            (:string)
                            (try
                             [{:tag :string-cat,
                               :sequence ?sequence,
                               :next ?next}]
                             (catch
                              java.lang.Exception
                              e__11823__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__11823__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__11823__auto__))))
                            (state__123569)))
                          (state__123569)))))]
                     (state__123588))
                    (state__123569))))
                 (state__123569
                  []
                  (clojure.core/case
                   input__122159_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (clojure.core/let
                    [?sequence input__122159_nth_1__]
                    (clojure.core/let
                     [?next input__122159_nth_2__]
                     (try
                      [{:tag :cat, :sequence ?sequence, :next ?next}]
                      (catch
                       java.lang.Exception
                       e__11823__auto__
                       (if
                        (meander.runtime.zeta/fail? e__11823__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__11823__auto__))))))
                   (state__123570)))
                 (state__123570
                  []
                  (clojure.core/let
                   [input__122159_nth_3__
                    (clojure.core/nth input__122159 3)]
                   (clojure.core/case
                    input__122159_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (if
                     (clojure.core/map? input__122159_nth_1__)
                     (clojure.core/let
                      [VAL__123463 (.valAt input__122159_nth_1__ :tag)]
                      (clojure.core/case
                       VAL__123463
                       (:cat)
                       (clojure.core/let
                        [VAL__122640
                         (.valAt input__122159_nth_1__ :sequence)]
                        (clojure.core/let
                         [?sequence VAL__122640]
                         (clojure.core/let
                          [VAL__122641
                           (.valAt input__122159_nth_1__ :next)]
                          (if
                           (clojure.core/map? VAL__122641)
                           (clojure.core/let
                            [VAL__122642 (.valAt VAL__122641 :tag)]
                            (clojure.core/case
                             VAL__122642
                             (:empty)
                             (clojure.core/let
                              [?right input__122159_nth_2__]
                              (clojure.core/let
                               [?env input__122159_nth_3__]
                               (try
                                [(clojure.core/let
                                  [CATA_RESULT__10883__auto__
                                   (CATA__FN__122227
                                    ['meander.dev.parse.zeta/make-cat
                                     ?sequence
                                     ?right
                                     ?env])]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    CATA_RESULT__10883__auto__)
                                   (throw (meander.runtime.zeta/fail))
                                   (clojure.core/nth
                                    CATA_RESULT__10883__auto__
                                    0)))]
                                (catch
                                 java.lang.Exception
                                 e__11823__auto__
                                 (if
                                  (meander.runtime.zeta/fail?
                                   e__11823__auto__)
                                  (meander.runtime.zeta/fail)
                                  (throw e__11823__auto__))))))
                             (state__123571)))
                           (state__123571)))))
                       (:literal)
                       (clojure.core/let
                        [VAL__122646
                         (.valAt input__122159_nth_1__ :type)]
                        (clojure.core/case
                         VAL__122646
                         (:string)
                         (clojure.core/let
                          [VAL__122647
                           (.valAt input__122159_nth_1__ :form)]
                          (clojure.core/let
                           [?form-1 VAL__122647]
                           (if
                            (clojure.core/map? input__122159_nth_2__)
                            (clojure.core/let
                             [VAL__122648
                              (.valAt input__122159_nth_2__ :tag)]
                             (clojure.core/case
                              VAL__122648
                              (:string-join)
                              (clojure.core/let
                               [VAL__122649
                                (.valAt input__122159_nth_2__ :left)]
                               (if
                                (clojure.core/map? VAL__122649)
                                (clojure.core/let
                                 [VAL__122650
                                  (.valAt VAL__122649 :tag)]
                                 (clojure.core/case
                                  VAL__122650
                                  (:literal)
                                  (clojure.core/let
                                   [VAL__122651
                                    (.valAt VAL__122649 :type)]
                                   (clojure.core/case
                                    VAL__122651
                                    (:string)
                                    (clojure.core/let
                                     [VAL__122652
                                      (.valAt VAL__122649 :form)]
                                     (clojure.core/let
                                      [?form-2 VAL__122652]
                                      (clojure.core/let
                                       [VAL__122653
                                        (.valAt
                                         input__122159_nth_2__
                                         :right)]
                                       (clojure.core/let
                                        [?right VAL__122653]
                                        (if
                                         (clojure.core/map?
                                          input__122159_nth_3__)
                                         (clojure.core/let
                                          [VAL__122654
                                           (.valAt
                                            input__122159_nth_3__
                                            :context)]
                                          (clojure.core/case
                                           VAL__122654
                                           (:string)
                                           (clojure.core/let
                                            [?env
                                             input__122159_nth_3__]
                                            (try
                                             [(clojure.core/let
                                               [CATA_RESULT__10883__auto__
                                                (CATA__FN__122227
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
                                                 CATA_RESULT__10883__auto__)
                                                (throw
                                                 (meander.runtime.zeta/fail))
                                                (clojure.core/nth
                                                 CATA_RESULT__10883__auto__
                                                 0)))]
                                             (catch
                                              java.lang.Exception
                                              e__11823__auto__
                                              (if
                                               (meander.runtime.zeta/fail?
                                                e__11823__auto__)
                                               (meander.runtime.zeta/fail)
                                               (throw
                                                e__11823__auto__)))))
                                           (state__123571)))
                                         (state__123571))))))
                                    (state__123571)))
                                  (state__123571)))
                                (state__123571)))
                              (state__123571)))
                            (state__123571))))
                         (state__123571)))
                       (state__123571)))
                     (state__123571))
                    (state__123571))))
                 (state__123571
                  []
                  (clojure.core/case
                   input__122159_nth_0__
                   (meander.dev.parse.zeta/make-join)
                   (if
                    (clojure.core/map? input__122159_nth_1__)
                    (clojure.core/let
                     [VAL__123464 (.valAt input__122159_nth_1__ :tag)]
                     (clojure.core/case
                      VAL__123464
                      (:cat)
                      (clojure.core/let
                       [?ast input__122159_nth_1__]
                       (if
                        (clojure.core/map? input__122159_nth_2__)
                        (clojure.core/let
                         [VAL__122658
                          (.valAt input__122159_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__122658
                          (:cat)
                          (clojure.core/let
                           [VAL__122659
                            (.valAt input__122159_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__122659]
                            (clojure.core/let
                             [VAL__122660
                              (.valAt input__122159_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__122660]
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
                                e__11823__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__11823__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__11823__auto__))))))))
                          (state__123572)))
                        (state__123572)))
                      (:string-cat)
                      (clojure.core/let
                       [?ast input__122159_nth_1__]
                       (if
                        (clojure.core/map? input__122159_nth_2__)
                        (clojure.core/let
                         [VAL__122664
                          (.valAt input__122159_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__122664
                          (:string-cat)
                          (clojure.core/let
                           [VAL__122665
                            (.valAt input__122159_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__122665]
                            (clojure.core/let
                             [VAL__122666
                              (.valAt input__122159_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__122666]
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
                                e__11823__auto__
                                (if
                                 (meander.runtime.zeta/fail?
                                  e__11823__auto__)
                                 (meander.runtime.zeta/fail)
                                 (throw e__11823__auto__))))))))
                          (state__123572)))
                        (state__123572)))
                      (state__123572)))
                    (state__123572))
                   (state__123572)))
                 (state__123572
                  []
                  (clojure.core/let
                   [input__122159_nth_3__
                    (clojure.core/nth input__122159 3)]
                   (clojure.core/case
                    input__122159_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (clojure.core/letfn
                     [(state__123593
                       []
                       (if
                        (clojure.core/map? input__122159_nth_1__)
                        (clojure.core/let
                         [VAL__123468
                          (.valAt input__122159_nth_1__ :next)
                          VAL__123467
                          (.valAt input__122159_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__123467
                          (:string-cat)
                          (clojure.core/let
                           [VAL__122670
                            (.valAt input__122159_nth_1__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__122670]
                            (if
                             (clojure.core/map? VAL__123468)
                             (clojure.core/let
                              [VAL__122672 (.valAt VAL__123468 :tag)]
                              (clojure.core/case
                               VAL__122672
                               (:empty)
                               (clojure.core/let
                                [?right input__122159_nth_2__]
                                (clojure.core/let
                                 [?env input__122159_nth_3__]
                                 (try
                                  [(clojure.core/let
                                    [CATA_RESULT__10883__auto__
                                     (CATA__FN__122227
                                      ['meander.dev.parse.zeta/make-join
                                       ?sequence
                                       ?right
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__10883__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__10883__auto__
                                      0)))]
                                  (catch
                                   java.lang.Exception
                                   e__11823__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__11823__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__11823__auto__))))))
                               (state__123594)))
                             (state__123594))))
                          (:string-star)
                          (clojure.core/let
                           [VAL__122676
                            (.valAt input__122159_nth_1__ :pattern)]
                           (clojure.core/let
                            [?pattern VAL__122676]
                            (if
                             (clojure.core/map? VAL__123468)
                             (clojure.core/let
                              [VAL__122678 (.valAt VAL__123468 :tag)]
                              (clojure.core/case
                               VAL__122678
                               (:empty)
                               (clojure.core/let
                                [?right input__122159_nth_2__]
                                (if
                                 (clojure.core/map?
                                  input__122159_nth_3__)
                                 (clojure.core/let
                                  [VAL__122679
                                   (.valAt
                                    input__122159_nth_3__
                                    :context)]
                                  (clojure.core/case
                                   VAL__122679
                                   (:string)
                                   (try
                                    [{:tag :string-star,
                                      :pattern ?pattern,
                                      :next ?right}]
                                    (catch
                                     java.lang.Exception
                                     e__11823__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11823__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11823__auto__))))
                                   (state__123594)))
                                 (state__123594)))
                               (state__123594)))
                             (state__123594))))
                          (:string-join)
                          (clojure.core/let
                           [VAL__122683
                            (.valAt input__122159_nth_1__ :left)]
                           (clojure.core/let
                            [?left VAL__122683]
                            (clojure.core/let
                             [VAL__122684
                              (.valAt input__122159_nth_1__ :right)]
                             (clojure.core/let
                              [?right-1 VAL__122684]
                              (clojure.core/let
                               [?right-2 input__122159_nth_2__]
                               (if
                                (clojure.core/map?
                                 input__122159_nth_3__)
                                (clojure.core/let
                                 [VAL__122685
                                  (.valAt
                                   input__122159_nth_3__
                                   :context)]
                                 (clojure.core/case
                                  VAL__122685
                                  (:string)
                                  (clojure.core/let
                                   [?env input__122159_nth_3__]
                                   (try
                                    [{:tag :string-join,
                                      :left ?left,
                                      :right
                                      (clojure.core/let
                                       [CATA_RESULT__10883__auto__
                                        (CATA__FN__122227
                                         ['meander.dev.parse.zeta/make-join
                                          ?right-1
                                          ?right-2
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__10883__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__10883__auto__
                                         0)))}]
                                    (catch
                                     java.lang.Exception
                                     e__11823__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11823__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11823__auto__)))))
                                  (state__123594)))
                                (state__123594)))))))
                          (state__123594)))
                        (state__123594)))
                      (state__123594
                       []
                       (clojure.core/let
                        [?left input__122159_nth_1__]
                        (if
                         (clojure.core/map? input__122159_nth_2__)
                         (clojure.core/let
                          [VAL__122688
                           (.valAt input__122159_nth_2__ :tag)]
                          (clojure.core/case
                           VAL__122688
                           (:empty)
                           (clojure.core/let
                            [?env input__122159_nth_3__]
                            (try
                             [?left]
                             (catch
                              java.lang.Exception
                              e__11823__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__11823__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__11823__auto__)))))
                           (state__123595)))
                         (state__123595))))
                      (state__123595
                       []
                       (if
                        (clojure.core/map? input__122159_nth_1__)
                        (clojure.core/let
                         [VAL__123466
                          (.valAt input__122159_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__123466
                          (:empty)
                          (clojure.core/let
                           [?right input__122159_nth_2__]
                           (clojure.core/let
                            [?env input__122159_nth_3__]
                            (try
                             [?right]
                             (catch
                              java.lang.Exception
                              e__11823__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__11823__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__11823__auto__))))))
                          (:star)
                          (clojure.core/let
                           [VAL__122695
                            (.valAt input__122159_nth_1__ :next)]
                           (if
                            (clojure.core/map? VAL__122695)
                            (clojure.core/let
                             [VAL__122696 (.valAt VAL__122695 :tag)]
                             (clojure.core/case
                              VAL__122696
                              (:empty)
                              (clojure.core/let
                               [?left input__122159_nth_1__]
                               (clojure.core/let
                                [?right input__122159_nth_2__]
                                (clojure.core/let
                                 [?env input__122159_nth_3__]
                                 (try
                                  [(clojure.core/let
                                    [form__10982__auto__
                                     {:tag :star, :next ?right}]
                                    (clojure.core/merge
                                     (clojure.core/into {} ?left)
                                     form__10982__auto__))]
                                  (catch
                                   java.lang.Exception
                                   e__11823__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__11823__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__11823__auto__)))))))
                              (state__123596)))
                            (state__123596)))
                          (state__123596)))
                        (state__123596)))
                      (state__123596
                       []
                       (clojure.core/let
                        [?left input__122159_nth_1__]
                        (clojure.core/let
                         [?right input__122159_nth_2__]
                         (clojure.core/letfn
                          [(state__123597
                            []
                            (if
                             (clojure.core/map? input__122159_nth_3__)
                             (clojure.core/let
                              [VAL__122699
                               (.valAt input__122159_nth_3__ :context)]
                              (clojure.core/case
                               VAL__122699
                               (:string)
                               (try
                                [{:tag :string-join,
                                  :left ?left,
                                  :right ?right}]
                                (catch
                                 java.lang.Exception
                                 e__11823__auto__
                                 (if
                                  (meander.runtime.zeta/fail?
                                   e__11823__auto__)
                                  (meander.runtime.zeta/fail)
                                  (throw e__11823__auto__))))
                               (state__123598)))
                             (state__123598)))
                           (state__123598
                            []
                            (clojure.core/let
                             [?env input__122159_nth_3__]
                             (try
                              [{:tag :join,
                                :left ?left,
                                :right ?right}]
                              (catch
                               java.lang.Exception
                               e__11823__auto__
                               (if
                                (meander.runtime.zeta/fail?
                                 e__11823__auto__)
                                (meander.runtime.zeta/fail)
                                (throw e__11823__auto__))))))]
                          (state__123597)))))]
                     (state__123593))
                    (state__123565))))]
                (state__123566)))
              (state__123565)))
            (state__123565
             []
             (if
              (clojure.core/= (clojure.core/count input__122159) 3)
              (clojure.core/let
               [input__122159_nth_0__
                (clojure.core/nth input__122159 0)
                input__122159_nth_1__
                (clojure.core/nth input__122159 1)
                input__122159_nth_2__
                (clojure.core/nth input__122159 2)]
               (clojure.core/case
                input__122159_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__122159_nth_1__)
                 (clojure.core/let
                  [VAL__122704
                   (.valAt input__122159_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__122704]
                   (clojure.core/let
                    [X__122706
                     ((clojure.core/fn
                       [m__8647__auto__]
                       (clojure.core/dissoc
                        m__8647__auto__
                        :meander.zeta/as))
                      input__122159_nth_1__)]
                    (clojure.core/let
                     [?rest X__122706]
                     (clojure.core/letfn
                      [(save__122707 [] (state__123479))
                       (f__123599
                        []
                        (clojure.core/let
                         [?env input__122159_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern
                            (clojure.core/let
                             [CATA_RESULT__10883__auto__
                              (CATA__FN__122227 [?pattern ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__10883__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__10883__auto__
                               0))),
                            :next
                            (clojure.core/let
                             [CATA_RESULT__10883__auto__
                              (CATA__FN__122227 [?rest ?env])]
                             (if
                              (meander.runtime.zeta/fail?
                               CATA_RESULT__10883__auto__)
                              (throw (meander.runtime.zeta/fail))
                              (clojure.core/nth
                               CATA_RESULT__10883__auto__
                               0)))}]
                          (catch
                           java.lang.Exception
                           e__11823__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__11823__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__11823__auto__))))))]
                      (if
                       (clojure.core/= ?rest input__122159_nth_1__)
                       (save__122707)
                       (f__123599)))))))
                 (state__123479))
                (state__123479)))
              (state__123479)))]
           (state__123563))
          (state__123479)))
        (state__123479
         []
         (clojure.core/letfn
          [(def__122710
            [arg__122743 ?ns]
            (clojure.core/letfn
             [(state__123600
               []
               (clojure.core/let
                [x__122744 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__122744)
                 (clojure.core/let [?env arg__122743] [?env ?ns])
                 (state__123601))))
              (state__123601
               []
               (if
                (clojure.core/map? arg__122743)
                (clojure.core/let
                 [VAL__122745 (.valAt arg__122743 :aliases)]
                 (if
                  (clojure.core/map? VAL__122745)
                  (clojure.core/let
                   [X__122747 (clojure.core/set VAL__122745)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__122747))
                    (clojure.core/loop
                     [search_space__123602
                      (clojure.core/seq X__122747)]
                     (if
                      (clojure.core/seq search_space__123602)
                      (clojure.core/let
                       [elem__122748
                        (clojure.core/first search_space__123602)
                        result__123603
                        (clojure.core/let
                         [elem__122748_nth_0__
                          (clojure.core/nth elem__122748 0)
                          elem__122748_nth_1__
                          (clojure.core/nth elem__122748 1)]
                         (if
                          (clojure.core/symbol? elem__122748_nth_0__)
                          (clojure.core/let
                           [X__122750
                            (clojure.core/name elem__122748_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__122750)
                            (if
                             (clojure.core/symbol?
                              elem__122748_nth_1__)
                             (clojure.core/let
                              [X__122752
                               (clojure.core/name
                                elem__122748_nth_1__)]
                              (clojure.core/case
                               X__122752
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__122743]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__123603)
                        (recur
                         (clojure.core/next search_space__123602))
                        result__123603))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__123600)))]
          (if
           (clojure.core/vector? input__122159)
           (if
            (clojure.core/= (clojure.core/count input__122159) 3)
            (clojure.core/let
             [input__122159_nth_0__
              (clojure.core/nth input__122159 0)
              input__122159_nth_1__
              (clojure.core/nth input__122159 1)
              input__122159_nth_2__
              (clojure.core/nth input__122159 2)]
             (clojure.core/case
              input__122159_nth_0__
              (meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__122159_nth_1__)
               (clojure.core/let
                [X__122715 (clojure.core/set input__122159_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__122715))
                 (clojure.core/loop
                  [search_space__123605 (clojure.core/seq X__122715)]
                  (if
                   (clojure.core/seq search_space__123605)
                   (clojure.core/let
                    [elem__122716
                     (clojure.core/first search_space__123605)
                     result__123606
                     (clojure.core/let
                      [elem__122716_nth_0__
                       (clojure.core/nth elem__122716 0)
                       elem__122716_nth_1__
                       (clojure.core/nth elem__122716 1)]
                      (clojure.core/let
                       [*m__122188 elem__122716_nth_0__]
                       (if
                        (clojure.core/symbol? elem__122716_nth_0__)
                        (clojure.core/let
                         [X__122718
                          (clojure.core/namespace
                           elem__122716_nth_0__)]
                         (clojure.core/let
                          [?ns X__122718]
                          (clojure.core/let
                           [X__122720
                            (clojure.core/name elem__122716_nth_0__)]
                           (if
                            (clojure.core/string? X__122720)
                            (if
                             (clojure.core/re-matches #"&.*" X__122720)
                             (clojure.core/let
                              [?pattern elem__122716_nth_1__]
                              (clojure.core/let
                               [X__122722
                                ((clojure.core/fn
                                  [m__8647__auto__]
                                  (clojure.core/dissoc
                                   m__8647__auto__
                                   *m__122188))
                                 input__122159_nth_1__)]
                               (clojure.core/let
                                [?rest X__122722]
                                (clojure.core/let
                                 [x__9580__auto__
                                  (def__122710
                                   input__122159_nth_2__
                                   ?ns)]
                                 (if
                                  (meander.runtime.zeta/fail?
                                   x__9580__auto__)
                                  (meander.runtime.zeta/fail)
                                  (clojure.core/let
                                   [[?env ?ns] x__9580__auto__]
                                   (try
                                    [{:tag :rest-map,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__10883__auto__
                                        (CATA__FN__122227
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__10883__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__10883__auto__
                                         0))),
                                      :next
                                      (clojure.core/let
                                       [CATA_RESULT__10883__auto__
                                        (CATA__FN__122227
                                         ['meander.dev.parse.zeta/parse-entries
                                          ?rest
                                          ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__10883__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__10883__auto__
                                         0)))}]
                                    (catch
                                     java.lang.Exception
                                     e__11823__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11823__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11823__auto__))))))))))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))))
                        (meander.runtime.zeta/fail))))]
                    (if
                     (meander.runtime.zeta/fail? result__123606)
                     (recur (clojure.core/next search_space__123605))
                     result__123606))
                   (state__123480)))
                 (state__123480)))
               (state__123480))
              (state__123480)))
            (state__123480))
           (state__123480))))
        (state__123480
         []
         (if
          (clojure.core/vector? input__122159)
          (clojure.core/letfn
           [(state__123608
             []
             (if
              (clojure.core/= (clojure.core/count input__122159) 3)
              (clojure.core/let
               [input__122159_nth_0__
                (clojure.core/nth input__122159 0)
                input__122159_nth_1__
                (clojure.core/nth input__122159 1)
                input__122159_nth_2__
                (clojure.core/nth input__122159 2)]
               (clojure.core/case
                input__122159_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (clojure.core/letfn
                 [(state__123610
                   []
                   (if
                    (clojure.core/map? input__122159_nth_1__)
                    (clojure.core/let
                     [X__122766
                      (clojure.core/set input__122159_nth_1__)]
                     (if
                      (clojure.core/<=
                       1
                       (clojure.core/count X__122766))
                      (clojure.core/loop
                       [search_space__123612
                        (clojure.core/seq X__122766)]
                       (if
                        (clojure.core/seq search_space__123612)
                        (clojure.core/let
                         [elem__122767
                          (clojure.core/first search_space__123612)
                          result__123613
                          (clojure.core/let
                           [elem__122767_nth_0__
                            (clojure.core/nth elem__122767 0)
                            elem__122767_nth_1__
                            (clojure.core/nth elem__122767 1)]
                           (clojure.core/let
                            [?key-pattern elem__122767_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__122767_nth_1__]
                             (clojure.core/let
                              [X__122769
                               ((clojure.core/fn
                                 [m__8647__auto__]
                                 (clojure.core/dissoc
                                  m__8647__auto__
                                  ?key-pattern))
                                input__122159_nth_1__)]
                              (clojure.core/let
                               [?rest X__122769]
                               (clojure.core/let
                                [?env input__122159_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__10883__auto__
                                     (CATA__FN__122227
                                      [?key-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__10883__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__10883__auto__
                                      0))),
                                   :val-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__10883__auto__
                                     (CATA__FN__122227
                                      [?val-pattern ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__10883__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__10883__auto__
                                      0))),
                                   :next
                                   (clojure.core/let
                                    [CATA_RESULT__10883__auto__
                                     (CATA__FN__122227
                                      ['meander.dev.parse.zeta/parse-entries
                                       ?rest
                                       ?env])]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      CATA_RESULT__10883__auto__)
                                     (throw
                                      (meander.runtime.zeta/fail))
                                     (clojure.core/nth
                                      CATA_RESULT__10883__auto__
                                      0)))}]
                                 (catch
                                  java.lang.Exception
                                  e__11823__auto__
                                  (if
                                   (meander.runtime.zeta/fail?
                                    e__11823__auto__)
                                   (meander.runtime.zeta/fail)
                                   (throw e__11823__auto__))))))))))]
                         (if
                          (meander.runtime.zeta/fail? result__123613)
                          (recur
                           (clojure.core/next search_space__123612))
                          result__123613))
                        (state__123611)))
                      (state__123611)))
                    (state__123611)))
                  (state__123611
                   []
                   (if
                    (clojure.core/map? input__122159_nth_1__)
                    (clojure.core/let
                     [?env input__122159_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__11823__auto__
                       (if
                        (meander.runtime.zeta/fail? e__11823__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__11823__auto__)))))
                    (state__123609)))]
                 (state__123610))
                (meander.dev.parse.zeta/parse-with-bindings)
                (clojure.core/letfn
                 [(state__123615
                   []
                   (if
                    (clojure.core/vector? input__122159_nth_1__)
                    (clojure.core/case
                     input__122159_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__122159_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__11823__auto__
                        (if
                         (meander.runtime.zeta/fail? e__11823__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__11823__auto__)))))
                     (state__123616))
                    (state__123616)))
                  (state__123616
                   []
                   (if
                    (clojure.core/vector? input__122159_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__122159_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__122159_nth_2__]
                      (try
                       [[{:tag :error,
                          :message
                          "meander.zeta/with expects an even number of bindings"}]]
                       (catch
                        java.lang.Exception
                        e__11823__auto__
                        (if
                         (meander.runtime.zeta/fail? e__11823__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__11823__auto__)))))
                     (state__123617))
                    (state__123617)))
                  (state__123617
                   []
                   (if
                    (clojure.core/vector? input__122159_nth_1__)
                    (clojure.core/let
                     [input__122159_nth_1___l__
                      (clojure.core/subvec
                       input__122159_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__122159_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__122159_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__122159_nth_1___r__
                        (clojure.core/subvec input__122159_nth_1__ 2)]
                       (clojure.core/let
                        [input__122159_nth_1___l___nth_0__
                         (clojure.core/nth input__122159_nth_1___l__ 0)
                         input__122159_nth_1___l___nth_1__
                         (clojure.core/nth
                          input__122159_nth_1___l__
                          1)]
                        (if
                         (clojure.core/symbol?
                          input__122159_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__122783
                           (clojure.core/namespace
                            input__122159_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__122785
                            (clojure.core/name
                             input__122159_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__122785)
                            (if
                             (clojure.core/re-matches #"%.+" X__122785)
                             (clojure.core/let
                              [?symbol
                               input__122159_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__122159_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__122159_nth_1___r__]
                                (clojure.core/let
                                 [?env input__122159_nth_2__]
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
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__122227
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0)))})
                                     (clojure.core/let
                                      [CATA_RESULT__10883__auto__
                                       (CATA__FN__122227
                                        ['meander.dev.parse.zeta/parse-with-bindings
                                         ?rest
                                         ?env])]
                                      (if
                                       (meander.runtime.zeta/fail?
                                        CATA_RESULT__10883__auto__)
                                       (throw
                                        (meander.runtime.zeta/fail))
                                       (clojure.core/nth
                                        CATA_RESULT__10883__auto__
                                        0)))))]
                                  (catch
                                   java.lang.Exception
                                   e__11823__auto__
                                   (if
                                    (meander.runtime.zeta/fail?
                                     e__11823__auto__)
                                    (meander.runtime.zeta/fail)
                                    (throw e__11823__auto__))))))))
                             (state__123618))
                            (state__123618))))
                         (state__123618))))
                      (state__123618)))
                    (state__123618)))
                  (state__123618
                   []
                   (if
                    (clojure.core/vector? input__122159_nth_1__)
                    (clojure.core/let
                     [input__122159_nth_1___l__
                      (clojure.core/subvec
                       input__122159_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__122159_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__122159_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__122159_nth_1___r__
                        (clojure.core/subvec input__122159_nth_1__ 2)]
                       (clojure.core/let
                        [input__122159_nth_1___l___nth_0__
                         (clojure.core/nth input__122159_nth_1___l__ 0)
                         input__122159_nth_1___l___nth_1__
                         (clojure.core/nth
                          input__122159_nth_1___l__
                          1)]
                        (clojure.core/let
                         [?x input__122159_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__122159_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__122159_nth_1___r__]
                           (clojure.core/let
                            [?env input__122159_nth_2__]
                            (try
                             [[{:tag :error,
                                :message
                                "meander.zeta/with bindings must be an repeating sequence of %name pattern"}]]
                             (catch
                              java.lang.Exception
                              e__11823__auto__
                              (if
                               (meander.runtime.zeta/fail?
                                e__11823__auto__)
                               (meander.runtime.zeta/fail)
                               (throw e__11823__auto__))))))))))
                      (state__123609)))
                    (state__123609)))]
                 (state__123615))
                (state__123609)))
              (state__123609)))
            (state__123609
             []
             (if
              (clojure.core/= (clojure.core/count input__122159) 2)
              (clojure.core/let
               [input__122159_nth_0__
                (clojure.core/nth input__122159 0)
                input__122159_nth_1__
                (clojure.core/nth input__122159 1)]
               (if
                (clojure.core/vector? input__122159_nth_0__)
                (clojure.core/let
                 [?sequence input__122159_nth_0__]
                 (clojure.core/let
                  [?form input__122159_nth_0__]
                  (clojure.core/let
                   [?env input__122159_nth_1__]
                   (try
                    [{:tag :vector,
                      :next
                      (clojure.core/let
                       [CATA_RESULT__10883__auto__
                        (CATA__FN__122227
                         ['meander.dev.parse.zeta/parse-sequential
                          ?sequence
                          (clojure.core/let
                           [form__10982__auto__ {:context :vector}]
                           (clojure.core/merge
                            (clojure.core/into {} ?env)
                            form__10982__auto__))])]
                       (if
                        (meander.runtime.zeta/fail?
                         CATA_RESULT__10883__auto__)
                        (throw (meander.runtime.zeta/fail))
                        (clojure.core/nth
                         CATA_RESULT__10883__auto__
                         0))),
                      :form ?form}]
                    (catch
                     java.lang.Exception
                     e__11823__auto__
                     (if
                      (meander.runtime.zeta/fail? e__11823__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__11823__auto__)))))))
                (state__123481)))
              (state__123481)))]
           (state__123608))
          (state__123481)))
        (state__123481
         []
         (clojure.core/letfn
          [(def__122795
            [arg__122818 ?__122160]
            (clojure.core/letfn
             [(state__123619
               []
               (clojure.core/let
                [x__122819 "meander.zeta"]
                (if
                 (clojure.core/= ?__122160 x__122819)
                 [?__122160]
                 (state__123620))))
              (state__123620
               []
               (if
                (clojure.core/map? arg__122818)
                (clojure.core/let
                 [VAL__122820 (.valAt arg__122818 :aliases)]
                 (if
                  (clojure.core/map? VAL__122820)
                  (clojure.core/let
                   [X__122822 (clojure.core/set VAL__122820)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__122822))
                    (clojure.core/loop
                     [search_space__123621
                      (clojure.core/seq X__122822)]
                     (if
                      (clojure.core/seq search_space__123621)
                      (clojure.core/let
                       [elem__122823
                        (clojure.core/first search_space__123621)
                        result__123622
                        (clojure.core/let
                         [elem__122823_nth_0__
                          (clojure.core/nth elem__122823 0)
                          elem__122823_nth_1__
                          (clojure.core/nth elem__122823 1)]
                         (if
                          (clojure.core/symbol? elem__122823_nth_0__)
                          (clojure.core/let
                           [X__122825
                            (clojure.core/name elem__122823_nth_0__)]
                           (if
                            (clojure.core/= ?__122160 X__122825)
                            (if
                             (clojure.core/symbol?
                              elem__122823_nth_1__)
                             (clojure.core/let
                              [X__122827
                               (clojure.core/name
                                elem__122823_nth_1__)]
                              (clojure.core/case
                               X__122827
                               ("meander.zeta")
                               [?__122160]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__123622)
                        (recur
                         (clojure.core/next search_space__123621))
                        result__123622))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__123619)))]
          (if
           (clojure.core/vector? input__122159)
           (if
            (clojure.core/= (clojure.core/count input__122159) 2)
            (clojure.core/let
             [input__122159_nth_0__
              (clojure.core/nth input__122159 0)
              input__122159_nth_1__
              (clojure.core/nth input__122159 1)]
             (if
              (clojure.core/seq? input__122159_nth_0__)
              (clojure.core/let
               [input__122159_nth_0___l__
                (clojure.core/take 1 input__122159_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__122159_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__122159_nth_0___r__
                  (clojure.core/drop 1 input__122159_nth_0__)]
                 (clojure.core/let
                  [input__122159_nth_0___l___nth_0__
                   (clojure.core/nth input__122159_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__122159_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__122805
                     (clojure.core/namespace
                      input__122159_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__122160 X__122805]
                     (clojure.core/let
                      [X__122807
                       (clojure.core/name
                        input__122159_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__122807
                       ("*")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__122795 input__122159_nth_1__ ?__122160)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__123482)
                         (clojure.core/let
                          [[?__122160] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__122159)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__122159)
                             2)
                            (clojure.core/let
                             [input__122159_nth_0__
                              (clojure.core/nth input__122159 0)
                              input__122159_nth_1__
                              (clojure.core/nth input__122159 1)]
                             (if
                              (clojure.core/seq? input__122159_nth_0__)
                              (clojure.core/let
                               [input__122159_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__122159_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__122159_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__122159_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__122159_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__122159_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__122159_nth_0__]
                                   (clojure.core/let
                                    [?env input__122159_nth_1__]
                                    (try
                                     [{:tag :star,
                                       :greedy? true,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__122227
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?patterns)
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))),
                                       :next {:tag :empty}}]
                                     (catch
                                      java.lang.Exception
                                      e__11823__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11823__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11823__auto__))))))))
                                (state__123482)))
                              (state__123482)))
                            (state__123482))
                           (state__123482)))))
                       (state__123482)))))
                   (state__123482))))
                (state__123482)))
              (state__123482)))
            (state__123482))
           (state__123482))))
        (state__123482
         []
         (clojure.core/letfn
          [(def__122829
            [arg__122852 ?__122161]
            (clojure.core/letfn
             [(state__123624
               []
               (clojure.core/let
                [x__122853 "meander.zeta"]
                (if
                 (clojure.core/= ?__122161 x__122853)
                 [?__122161]
                 (state__123625))))
              (state__123625
               []
               (if
                (clojure.core/map? arg__122852)
                (clojure.core/let
                 [VAL__122854 (.valAt arg__122852 :aliases)]
                 (if
                  (clojure.core/map? VAL__122854)
                  (clojure.core/let
                   [X__122856 (clojure.core/set VAL__122854)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__122856))
                    (clojure.core/loop
                     [search_space__123626
                      (clojure.core/seq X__122856)]
                     (if
                      (clojure.core/seq search_space__123626)
                      (clojure.core/let
                       [elem__122857
                        (clojure.core/first search_space__123626)
                        result__123627
                        (clojure.core/let
                         [elem__122857_nth_0__
                          (clojure.core/nth elem__122857 0)
                          elem__122857_nth_1__
                          (clojure.core/nth elem__122857 1)]
                         (if
                          (clojure.core/symbol? elem__122857_nth_0__)
                          (clojure.core/let
                           [X__122859
                            (clojure.core/name elem__122857_nth_0__)]
                           (if
                            (clojure.core/= ?__122161 X__122859)
                            (if
                             (clojure.core/symbol?
                              elem__122857_nth_1__)
                             (clojure.core/let
                              [X__122861
                               (clojure.core/name
                                elem__122857_nth_1__)]
                              (clojure.core/case
                               X__122861
                               ("meander.zeta")
                               [?__122161]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__123627)
                        (recur
                         (clojure.core/next search_space__123626))
                        result__123627))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__123624)))]
          (if
           (clojure.core/vector? input__122159)
           (if
            (clojure.core/= (clojure.core/count input__122159) 2)
            (clojure.core/let
             [input__122159_nth_0__
              (clojure.core/nth input__122159 0)
              input__122159_nth_1__
              (clojure.core/nth input__122159 1)]
             (if
              (clojure.core/seq? input__122159_nth_0__)
              (clojure.core/let
               [input__122159_nth_0___l__
                (clojure.core/take 1 input__122159_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__122159_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__122159_nth_0___r__
                  (clojure.core/drop 1 input__122159_nth_0__)]
                 (clojure.core/let
                  [input__122159_nth_0___l___nth_0__
                   (clojure.core/nth input__122159_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__122159_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__122839
                     (clojure.core/namespace
                      input__122159_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__122161 X__122839]
                     (clojure.core/let
                      [X__122841
                       (clojure.core/name
                        input__122159_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__122841
                       ("<>")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__122829 input__122159_nth_1__ ?__122161)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__123483)
                         (clojure.core/let
                          [[?__122161] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__122159)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__122159)
                             2)
                            (clojure.core/let
                             [input__122159_nth_0__
                              (clojure.core/nth input__122159 0)
                              input__122159_nth_1__
                              (clojure.core/nth input__122159 1)]
                             (if
                              (clojure.core/seq? input__122159_nth_0__)
                              (clojure.core/let
                               [input__122159_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__122159_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__122159_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__122159_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__122159_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__122159_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__122159_nth_0__]
                                   (clojure.core/let
                                    [?env input__122159_nth_1__]
                                    (try
                                     [{:tag :group,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__122227
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?patterns)
                                           ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0)))}]
                                     (catch
                                      java.lang.Exception
                                      e__11823__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11823__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11823__auto__))))))))
                                (state__123483)))
                              (state__123483)))
                            (state__123483))
                           (state__123483)))))
                       (state__123483)))))
                   (state__123483))))
                (state__123483)))
              (state__123483)))
            (state__123483))
           (state__123483))))
        (state__123483
         []
         (clojure.core/letfn
          [(def__122863
            [arg__122886 ?__122162]
            (clojure.core/letfn
             [(state__123629
               []
               (clojure.core/let
                [x__122887 "meander.math.zeta"]
                (if
                 (clojure.core/= ?__122162 x__122887)
                 [?__122162]
                 (state__123630))))
              (state__123630
               []
               (if
                (clojure.core/map? arg__122886)
                (clojure.core/let
                 [VAL__122888 (.valAt arg__122886 :aliases)]
                 (if
                  (clojure.core/map? VAL__122888)
                  (clojure.core/let
                   [X__122890 (clojure.core/set VAL__122888)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__122890))
                    (clojure.core/loop
                     [search_space__123631
                      (clojure.core/seq X__122890)]
                     (if
                      (clojure.core/seq search_space__123631)
                      (clojure.core/let
                       [elem__122891
                        (clojure.core/first search_space__123631)
                        result__123632
                        (clojure.core/let
                         [elem__122891_nth_0__
                          (clojure.core/nth elem__122891 0)
                          elem__122891_nth_1__
                          (clojure.core/nth elem__122891 1)]
                         (if
                          (clojure.core/symbol? elem__122891_nth_0__)
                          (clojure.core/let
                           [X__122893
                            (clojure.core/name elem__122891_nth_0__)]
                           (if
                            (clojure.core/= ?__122162 X__122893)
                            (if
                             (clojure.core/symbol?
                              elem__122891_nth_1__)
                             (clojure.core/let
                              [X__122895
                               (clojure.core/name
                                elem__122891_nth_1__)]
                              (clojure.core/case
                               X__122895
                               ("meander.math.zeta")
                               [?__122162]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__123632)
                        (recur
                         (clojure.core/next search_space__123631))
                        result__123632))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__123629)))]
          (if
           (clojure.core/vector? input__122159)
           (if
            (clojure.core/= (clojure.core/count input__122159) 2)
            (clojure.core/let
             [input__122159_nth_0__
              (clojure.core/nth input__122159 0)
              input__122159_nth_1__
              (clojure.core/nth input__122159 1)]
             (if
              (clojure.core/seq? input__122159_nth_0__)
              (clojure.core/let
               [input__122159_nth_0___l__
                (clojure.core/take 1 input__122159_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__122159_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__122159_nth_0___r__
                  (clojure.core/drop 1 input__122159_nth_0__)]
                 (clojure.core/let
                  [input__122159_nth_0___l___nth_0__
                   (clojure.core/nth input__122159_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__122159_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__122873
                     (clojure.core/namespace
                      input__122159_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__122162 X__122873]
                     (clojure.core/let
                      [X__122875
                       (clojure.core/name
                        input__122159_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__122875
                       ("+")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__122863 input__122159_nth_1__ ?__122162)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__123484)
                         (clojure.core/let
                          [[?__122162] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__122159)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__122159)
                             2)
                            (clojure.core/let
                             [input__122159_nth_0__
                              (clojure.core/nth input__122159 0)
                              input__122159_nth_1__
                              (clojure.core/nth input__122159 1)]
                             (if
                              (clojure.core/seq? input__122159_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__122159_nth_0__)
                                3)
                               (clojure.core/let
                                [input__122159_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  1)
                                 input__122159_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?a input__122159_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?b input__122159_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__122159_nth_0__]
                                   (clojure.core/let
                                    [?env input__122159_nth_1__]
                                    (try
                                     [{:tag :meander.math.zeta/+,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__122227 [?a ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__122227 [?b ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0)))}]
                                     (catch
                                      java.lang.Exception
                                      e__11823__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11823__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11823__auto__)))))))))
                               (state__123484))
                              (state__123484)))
                            (state__123484))
                           (state__123484)))))
                       (state__123484)))))
                   (state__123484))))
                (state__123484)))
              (state__123484)))
            (state__123484))
           (state__123484))))
        (state__123484
         []
         (clojure.core/letfn
          [(def__122897
            [arg__122920 ?__122163]
            (clojure.core/letfn
             [(state__123634
               []
               (clojure.core/let
                [x__122921 "meander.zeta"]
                (if
                 (clojure.core/= ?__122163 x__122921)
                 [?__122163]
                 (state__123635))))
              (state__123635
               []
               (if
                (clojure.core/map? arg__122920)
                (clojure.core/let
                 [VAL__122922 (.valAt arg__122920 :aliases)]
                 (if
                  (clojure.core/map? VAL__122922)
                  (clojure.core/let
                   [X__122924 (clojure.core/set VAL__122922)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__122924))
                    (clojure.core/loop
                     [search_space__123636
                      (clojure.core/seq X__122924)]
                     (if
                      (clojure.core/seq search_space__123636)
                      (clojure.core/let
                       [elem__122925
                        (clojure.core/first search_space__123636)
                        result__123637
                        (clojure.core/let
                         [elem__122925_nth_0__
                          (clojure.core/nth elem__122925 0)
                          elem__122925_nth_1__
                          (clojure.core/nth elem__122925 1)]
                         (if
                          (clojure.core/symbol? elem__122925_nth_0__)
                          (clojure.core/let
                           [X__122927
                            (clojure.core/name elem__122925_nth_0__)]
                           (if
                            (clojure.core/= ?__122163 X__122927)
                            (if
                             (clojure.core/symbol?
                              elem__122925_nth_1__)
                             (clojure.core/let
                              [X__122929
                               (clojure.core/name
                                elem__122925_nth_1__)]
                              (clojure.core/case
                               X__122929
                               ("meander.zeta")
                               [?__122163]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__123637)
                        (recur
                         (clojure.core/next search_space__123636))
                        result__123637))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__123634)))]
          (if
           (clojure.core/vector? input__122159)
           (if
            (clojure.core/= (clojure.core/count input__122159) 2)
            (clojure.core/let
             [input__122159_nth_0__
              (clojure.core/nth input__122159 0)
              input__122159_nth_1__
              (clojure.core/nth input__122159 1)]
             (if
              (clojure.core/seq? input__122159_nth_0__)
              (clojure.core/let
               [input__122159_nth_0___l__
                (clojure.core/take 1 input__122159_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__122159_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__122159_nth_0___r__
                  (clojure.core/drop 1 input__122159_nth_0__)]
                 (clojure.core/let
                  [input__122159_nth_0___l___nth_0__
                   (clojure.core/nth input__122159_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__122159_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__122907
                     (clojure.core/namespace
                      input__122159_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__122163 X__122907]
                     (clojure.core/let
                      [X__122909
                       (clojure.core/name
                        input__122159_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__122909
                       ("with")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__122897 input__122159_nth_1__ ?__122163)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__123485)
                         (clojure.core/let
                          [[?__122163] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__122159)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__122159)
                             2)
                            (clojure.core/let
                             [input__122159_nth_0__
                              (clojure.core/nth input__122159 0)
                              input__122159_nth_1__
                              (clojure.core/nth input__122159 1)]
                             (if
                              (clojure.core/seq? input__122159_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__122159_nth_0__)
                                3)
                               (clojure.core/let
                                [input__122159_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  1)
                                 input__122159_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__122159_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__122159_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__122159_nth_0__]
                                   (clojure.core/let
                                    [?env input__122159_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__10883__auto__
                                          (CATA__FN__122227
                                           ['meander.dev.parse.zeta/parse-with-bindings
                                            ?bindings
                                            ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__10883__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__10883__auto__
                                           0)))},
                                       :body
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__122227
                                          [?body ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__11823__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11823__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11823__auto__)))))))))
                               (state__123485))
                              (state__123485)))
                            (state__123485))
                           (state__123485)))))
                       (state__123485)))))
                   (state__123485))))
                (state__123485)))
              (state__123485)))
            (state__123485))
           (state__123485))))
        (state__123485
         []
         (clojure.core/letfn
          [(def__122931
            [arg__122954 ?__122164]
            (clojure.core/letfn
             [(state__123639
               []
               (clojure.core/let
                [x__122955 "meander.zeta"]
                (if
                 (clojure.core/= ?__122164 x__122955)
                 [?__122164]
                 (state__123640))))
              (state__123640
               []
               (if
                (clojure.core/map? arg__122954)
                (clojure.core/let
                 [VAL__122956 (.valAt arg__122954 :aliases)]
                 (if
                  (clojure.core/map? VAL__122956)
                  (clojure.core/let
                   [X__122958 (clojure.core/set VAL__122956)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__122958))
                    (clojure.core/loop
                     [search_space__123641
                      (clojure.core/seq X__122958)]
                     (if
                      (clojure.core/seq search_space__123641)
                      (clojure.core/let
                       [elem__122959
                        (clojure.core/first search_space__123641)
                        result__123642
                        (clojure.core/let
                         [elem__122959_nth_0__
                          (clojure.core/nth elem__122959 0)
                          elem__122959_nth_1__
                          (clojure.core/nth elem__122959 1)]
                         (if
                          (clojure.core/symbol? elem__122959_nth_0__)
                          (clojure.core/let
                           [X__122961
                            (clojure.core/name elem__122959_nth_0__)]
                           (if
                            (clojure.core/= ?__122164 X__122961)
                            (if
                             (clojure.core/symbol?
                              elem__122959_nth_1__)
                             (clojure.core/let
                              [X__122963
                               (clojure.core/name
                                elem__122959_nth_1__)]
                              (clojure.core/case
                               X__122963
                               ("meander.zeta")
                               [?__122164]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__123642)
                        (recur
                         (clojure.core/next search_space__123641))
                        result__123642))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__123639)))]
          (if
           (clojure.core/vector? input__122159)
           (if
            (clojure.core/= (clojure.core/count input__122159) 2)
            (clojure.core/let
             [input__122159_nth_0__
              (clojure.core/nth input__122159 0)
              input__122159_nth_1__
              (clojure.core/nth input__122159 1)]
             (if
              (clojure.core/seq? input__122159_nth_0__)
              (clojure.core/let
               [input__122159_nth_0___l__
                (clojure.core/take 1 input__122159_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__122159_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__122159_nth_0___r__
                  (clojure.core/drop 1 input__122159_nth_0__)]
                 (clojure.core/let
                  [input__122159_nth_0___l___nth_0__
                   (clojure.core/nth input__122159_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__122159_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__122941
                     (clojure.core/namespace
                      input__122159_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__122164 X__122941]
                     (clojure.core/let
                      [X__122943
                       (clojure.core/name
                        input__122159_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__122943
                       ("apply")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__122931 input__122159_nth_1__ ?__122164)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__123486)
                         (clojure.core/let
                          [[?__122164] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__122159)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__122159)
                             2)
                            (clojure.core/let
                             [input__122159_nth_0__
                              (clojure.core/nth input__122159 0)
                              input__122159_nth_1__
                              (clojure.core/nth input__122159 1)]
                             (if
                              (clojure.core/seq? input__122159_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__122159_nth_0__)
                                3)
                               (clojure.core/let
                                [input__122159_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  1)
                                 input__122159_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__122159_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__122159_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__122159_nth_0__]
                                   (clojure.core/let
                                    [?env input__122159_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__122227
                                          [?pattern ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__11823__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11823__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11823__auto__)))))))))
                               (state__123486))
                              (state__123486)))
                            (state__123486))
                           (state__123486)))))
                       (state__123486)))))
                   (state__123486))))
                (state__123486)))
              (state__123486)))
            (state__123486))
           (state__123486))))
        (state__123486
         []
         (clojure.core/letfn
          [(def__122965
            [arg__122988 ?__122165]
            (clojure.core/letfn
             [(state__123644
               []
               (clojure.core/let
                [x__122989 "meander.zeta"]
                (if
                 (clojure.core/= ?__122165 x__122989)
                 [?__122165]
                 (state__123645))))
              (state__123645
               []
               (if
                (clojure.core/map? arg__122988)
                (clojure.core/let
                 [VAL__122990 (.valAt arg__122988 :aliases)]
                 (if
                  (clojure.core/map? VAL__122990)
                  (clojure.core/let
                   [X__122992 (clojure.core/set VAL__122990)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__122992))
                    (clojure.core/loop
                     [search_space__123646
                      (clojure.core/seq X__122992)]
                     (if
                      (clojure.core/seq search_space__123646)
                      (clojure.core/let
                       [elem__122993
                        (clojure.core/first search_space__123646)
                        result__123647
                        (clojure.core/let
                         [elem__122993_nth_0__
                          (clojure.core/nth elem__122993 0)
                          elem__122993_nth_1__
                          (clojure.core/nth elem__122993 1)]
                         (if
                          (clojure.core/symbol? elem__122993_nth_0__)
                          (clojure.core/let
                           [X__122995
                            (clojure.core/name elem__122993_nth_0__)]
                           (if
                            (clojure.core/= ?__122165 X__122995)
                            (if
                             (clojure.core/symbol?
                              elem__122993_nth_1__)
                             (clojure.core/let
                              [X__122997
                               (clojure.core/name
                                elem__122993_nth_1__)]
                              (clojure.core/case
                               X__122997
                               ("meander.zeta")
                               [?__122165]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__123647)
                        (recur
                         (clojure.core/next search_space__123646))
                        result__123647))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__123644)))]
          (if
           (clojure.core/vector? input__122159)
           (if
            (clojure.core/= (clojure.core/count input__122159) 2)
            (clojure.core/let
             [input__122159_nth_0__
              (clojure.core/nth input__122159 0)
              input__122159_nth_1__
              (clojure.core/nth input__122159 1)]
             (if
              (clojure.core/seq? input__122159_nth_0__)
              (clojure.core/let
               [input__122159_nth_0___l__
                (clojure.core/take 1 input__122159_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__122159_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__122159_nth_0___r__
                  (clojure.core/drop 1 input__122159_nth_0__)]
                 (clojure.core/let
                  [input__122159_nth_0___l___nth_0__
                   (clojure.core/nth input__122159_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__122159_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__122975
                     (clojure.core/namespace
                      input__122159_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__122165 X__122975]
                     (clojure.core/let
                      [X__122977
                       (clojure.core/name
                        input__122159_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__122977
                       ("and")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__122965 input__122159_nth_1__ ?__122165)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__123487)
                         (clojure.core/let
                          [[?__122165] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__122159)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__122159)
                             2)
                            (clojure.core/let
                             [input__122159_nth_0__
                              (clojure.core/nth input__122159 0)
                              input__122159_nth_1__
                              (clojure.core/nth input__122159 1)]
                             (if
                              (clojure.core/seq? input__122159_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__122159_nth_0__)
                                3)
                               (clojure.core/let
                                [input__122159_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  1)
                                 input__122159_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__122159_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right
                                   input__122159_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__122159_nth_0__]
                                   (clojure.core/let
                                    [?env input__122159_nth_1__]
                                    (try
                                     [{:tag :and,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__122227
                                          [?left ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__122227
                                          [?right ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__11823__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11823__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11823__auto__)))))))))
                               (state__123487))
                              (state__123487)))
                            (state__123487))
                           (state__123487)))))
                       (state__123487)))))
                   (state__123487))))
                (state__123487)))
              (state__123487)))
            (state__123487))
           (state__123487))))
        (state__123487
         []
         (clojure.core/letfn
          [(def__122999
            [arg__123022 ?__122166]
            (clojure.core/letfn
             [(state__123649
               []
               (clojure.core/let
                [x__123023 "meander.zeta"]
                (if
                 (clojure.core/= ?__122166 x__123023)
                 [?__122166]
                 (state__123650))))
              (state__123650
               []
               (if
                (clojure.core/map? arg__123022)
                (clojure.core/let
                 [VAL__123024 (.valAt arg__123022 :aliases)]
                 (if
                  (clojure.core/map? VAL__123024)
                  (clojure.core/let
                   [X__123026 (clojure.core/set VAL__123024)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__123026))
                    (clojure.core/loop
                     [search_space__123651
                      (clojure.core/seq X__123026)]
                     (if
                      (clojure.core/seq search_space__123651)
                      (clojure.core/let
                       [elem__123027
                        (clojure.core/first search_space__123651)
                        result__123652
                        (clojure.core/let
                         [elem__123027_nth_0__
                          (clojure.core/nth elem__123027 0)
                          elem__123027_nth_1__
                          (clojure.core/nth elem__123027 1)]
                         (if
                          (clojure.core/symbol? elem__123027_nth_0__)
                          (clojure.core/let
                           [X__123029
                            (clojure.core/name elem__123027_nth_0__)]
                           (if
                            (clojure.core/= ?__122166 X__123029)
                            (if
                             (clojure.core/symbol?
                              elem__123027_nth_1__)
                             (clojure.core/let
                              [X__123031
                               (clojure.core/name
                                elem__123027_nth_1__)]
                              (clojure.core/case
                               X__123031
                               ("meander.zeta")
                               [?__122166]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__123652)
                        (recur
                         (clojure.core/next search_space__123651))
                        result__123652))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__123649)))]
          (if
           (clojure.core/vector? input__122159)
           (if
            (clojure.core/= (clojure.core/count input__122159) 2)
            (clojure.core/let
             [input__122159_nth_0__
              (clojure.core/nth input__122159 0)
              input__122159_nth_1__
              (clojure.core/nth input__122159 1)]
             (if
              (clojure.core/seq? input__122159_nth_0__)
              (clojure.core/let
               [input__122159_nth_0___l__
                (clojure.core/take 1 input__122159_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__122159_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__122159_nth_0___r__
                  (clojure.core/drop 1 input__122159_nth_0__)]
                 (clojure.core/let
                  [input__122159_nth_0___l___nth_0__
                   (clojure.core/nth input__122159_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__122159_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__123009
                     (clojure.core/namespace
                      input__122159_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__122166 X__123009]
                     (clojure.core/let
                      [X__123011
                       (clojure.core/name
                        input__122159_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__123011
                       ("cata")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__122999 input__122159_nth_1__ ?__122166)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__123488)
                         (clojure.core/let
                          [[?__122166] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__122159)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__122159)
                             2)
                            (clojure.core/let
                             [input__122159_nth_0__
                              (clojure.core/nth input__122159 0)
                              input__122159_nth_1__
                              (clojure.core/nth input__122159 1)]
                             (if
                              (clojure.core/seq? input__122159_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__122159_nth_0__)
                                2)
                               (clojure.core/let
                                [input__122159_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__122159_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__122159_nth_0__]
                                  (clojure.core/let
                                   [?env input__122159_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__10883__auto__
                                        (CATA__FN__122227
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__10883__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__10883__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__11823__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11823__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11823__auto__))))))))
                               (state__123488))
                              (state__123488)))
                            (state__123488))
                           (state__123488)))))
                       (state__123488)))))
                   (state__123488))))
                (state__123488)))
              (state__123488)))
            (state__123488))
           (state__123488))))
        (state__123488
         []
         (clojure.core/letfn
          [(def__123033
            [arg__123056 ?__122167]
            (clojure.core/letfn
             [(state__123654
               []
               (clojure.core/let
                [x__123057 "meander.zeta"]
                (if
                 (clojure.core/= ?__122167 x__123057)
                 [?__122167]
                 (state__123655))))
              (state__123655
               []
               (if
                (clojure.core/map? arg__123056)
                (clojure.core/let
                 [VAL__123058 (.valAt arg__123056 :aliases)]
                 (if
                  (clojure.core/map? VAL__123058)
                  (clojure.core/let
                   [X__123060 (clojure.core/set VAL__123058)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__123060))
                    (clojure.core/loop
                     [search_space__123656
                      (clojure.core/seq X__123060)]
                     (if
                      (clojure.core/seq search_space__123656)
                      (clojure.core/let
                       [elem__123061
                        (clojure.core/first search_space__123656)
                        result__123657
                        (clojure.core/let
                         [elem__123061_nth_0__
                          (clojure.core/nth elem__123061 0)
                          elem__123061_nth_1__
                          (clojure.core/nth elem__123061 1)]
                         (if
                          (clojure.core/symbol? elem__123061_nth_0__)
                          (clojure.core/let
                           [X__123063
                            (clojure.core/name elem__123061_nth_0__)]
                           (if
                            (clojure.core/= ?__122167 X__123063)
                            (if
                             (clojure.core/symbol?
                              elem__123061_nth_1__)
                             (clojure.core/let
                              [X__123065
                               (clojure.core/name
                                elem__123061_nth_1__)]
                              (clojure.core/case
                               X__123065
                               ("meander.zeta")
                               [?__122167]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__123657)
                        (recur
                         (clojure.core/next search_space__123656))
                        result__123657))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__123654)))]
          (if
           (clojure.core/vector? input__122159)
           (if
            (clojure.core/= (clojure.core/count input__122159) 2)
            (clojure.core/let
             [input__122159_nth_0__
              (clojure.core/nth input__122159 0)
              input__122159_nth_1__
              (clojure.core/nth input__122159 1)]
             (if
              (clojure.core/seq? input__122159_nth_0__)
              (clojure.core/let
               [input__122159_nth_0___l__
                (clojure.core/take 1 input__122159_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__122159_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__122159_nth_0___r__
                  (clojure.core/drop 1 input__122159_nth_0__)]
                 (clojure.core/let
                  [input__122159_nth_0___l___nth_0__
                   (clojure.core/nth input__122159_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__122159_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__123043
                     (clojure.core/namespace
                      input__122159_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__122167 X__123043]
                     (clojure.core/let
                      [X__123045
                       (clojure.core/name
                        input__122159_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__123045
                       ("fold")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__123033 input__122159_nth_1__ ?__122167)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__123489)
                         (clojure.core/let
                          [[?__122167] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__122159)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__122159)
                             2)
                            (clojure.core/let
                             [input__122159_nth_0__
                              (clojure.core/nth input__122159 0)
                              input__122159_nth_1__
                              (clojure.core/nth input__122159 1)]
                             (if
                              (clojure.core/seq? input__122159_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__122159_nth_0__)
                                4)
                               (clojure.core/let
                                [input__122159_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  1)
                                 input__122159_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  2)
                                 input__122159_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__122159_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__122159_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__122159_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__122159_nth_0__]
                                    (clojure.core/let
                                     [?env input__122159_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__122227
                                          ['meander.dev.parse.zeta/make-fold
                                           (clojure.core/let
                                            [CATA_RESULT__10883__auto__
                                             (CATA__FN__122227
                                              [?mutable-variable
                                               ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__10883__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__10883__auto__
                                              0)))
                                           (clojure.core/let
                                            [CATA_RESULT__10883__auto__
                                             (CATA__FN__122227
                                              [?initial-value ?env])]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              CATA_RESULT__10883__auto__)
                                             (throw
                                              (meander.runtime.zeta/fail))
                                             (clojure.core/nth
                                              CATA_RESULT__10883__auto__
                                              0)))
                                           ?fold-function
                                           ?form])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0)))]
                                      (catch
                                       java.lang.Exception
                                       e__11823__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__11823__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__11823__auto__))))))))))
                               (state__123489))
                              (state__123489)))
                            (state__123489))
                           (state__123489)))))
                       (state__123489)))))
                   (state__123489))))
                (state__123489)))
              (state__123489)))
            (state__123489))
           (state__123489))))
        (state__123489
         []
         (if
          (clojure.core/vector? input__122159)
          (if
           (clojure.core/= (clojure.core/count input__122159) 5)
           (clojure.core/let
            [input__122159_nth_0__
             (clojure.core/nth input__122159 0)
             input__122159_nth_1__
             (clojure.core/nth input__122159 1)
             input__122159_nth_2__
             (clojure.core/nth input__122159 2)
             input__122159_nth_3__
             (clojure.core/nth input__122159 3)
             input__122159_nth_4__
             (clojure.core/nth input__122159 4)]
            (clojure.core/case
             input__122159_nth_0__
             (meander.dev.parse.zeta/make-fold)
             (if
              (clojure.core/map? input__122159_nth_1__)
              (clojure.core/let
               [VAL__123068 (.valAt input__122159_nth_1__ :tag)]
               (clojure.core/case
                VAL__123068
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__122159_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__122159_nth_2__]
                  (clojure.core/let
                   [?fold-function input__122159_nth_3__]
                   (clojure.core/let
                    [?form input__122159_nth_4__]
                    (try
                     [{:tag :fold,
                       :variable ?variable-ast,
                       :initial-value ?initial-value-ast,
                       :fold-function
                       {:tag :host-expression, :form ?fold-function},
                       :form ?form}]
                     (catch
                      java.lang.Exception
                      e__11823__auto__
                      (if
                       (meander.runtime.zeta/fail? e__11823__auto__)
                       (meander.runtime.zeta/fail)
                       (throw e__11823__auto__))))))))
                (state__123490)))
              (state__123490))
             (state__123490)))
           (state__123490))
          (state__123490)))
        (state__123490
         []
         (clojure.core/letfn
          [(def__123070
            [arg__123093 ?__122168]
            (clojure.core/letfn
             [(state__123659
               []
               (clojure.core/let
                [x__123094 "meander.zeta"]
                (if
                 (clojure.core/= ?__122168 x__123094)
                 [?__122168]
                 (state__123660))))
              (state__123660
               []
               (if
                (clojure.core/map? arg__123093)
                (clojure.core/let
                 [VAL__123095 (.valAt arg__123093 :aliases)]
                 (if
                  (clojure.core/map? VAL__123095)
                  (clojure.core/let
                   [X__123097 (clojure.core/set VAL__123095)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__123097))
                    (clojure.core/loop
                     [search_space__123661
                      (clojure.core/seq X__123097)]
                     (if
                      (clojure.core/seq search_space__123661)
                      (clojure.core/let
                       [elem__123098
                        (clojure.core/first search_space__123661)
                        result__123662
                        (clojure.core/let
                         [elem__123098_nth_0__
                          (clojure.core/nth elem__123098 0)
                          elem__123098_nth_1__
                          (clojure.core/nth elem__123098 1)]
                         (if
                          (clojure.core/symbol? elem__123098_nth_0__)
                          (clojure.core/let
                           [X__123100
                            (clojure.core/name elem__123098_nth_0__)]
                           (if
                            (clojure.core/= ?__122168 X__123100)
                            (if
                             (clojure.core/symbol?
                              elem__123098_nth_1__)
                             (clojure.core/let
                              [X__123102
                               (clojure.core/name
                                elem__123098_nth_1__)]
                              (clojure.core/case
                               X__123102
                               ("meander.zeta")
                               [?__122168]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__123662)
                        (recur
                         (clojure.core/next search_space__123661))
                        result__123662))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__123659)))]
          (if
           (clojure.core/vector? input__122159)
           (if
            (clojure.core/= (clojure.core/count input__122159) 2)
            (clojure.core/let
             [input__122159_nth_0__
              (clojure.core/nth input__122159 0)
              input__122159_nth_1__
              (clojure.core/nth input__122159 1)]
             (if
              (clojure.core/seq? input__122159_nth_0__)
              (clojure.core/let
               [input__122159_nth_0___l__
                (clojure.core/take 1 input__122159_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__122159_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__122159_nth_0___r__
                  (clojure.core/drop 1 input__122159_nth_0__)]
                 (clojure.core/let
                  [input__122159_nth_0___l___nth_0__
                   (clojure.core/nth input__122159_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__122159_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__123080
                     (clojure.core/namespace
                      input__122159_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__122168 X__123080]
                     (clojure.core/let
                      [X__123082
                       (clojure.core/name
                        input__122159_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__123082
                       ("let")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__123070 input__122159_nth_1__ ?__122168)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__123491)
                         (clojure.core/let
                          [[?__122168] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__122159)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__122159)
                             2)
                            (clojure.core/let
                             [input__122159_nth_0__
                              (clojure.core/nth input__122159 0)
                              input__122159_nth_1__
                              (clojure.core/nth input__122159 1)]
                             (if
                              (clojure.core/seq? input__122159_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__122159_nth_0__)
                                3)
                               (clojure.core/let
                                [input__122159_nth_0___nth_0__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  0)
                                 input__122159_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  1)
                                 input__122159_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__122159_nth_0___nth_0__]
                                 (clojure.core/let
                                  [?expression
                                   input__122159_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?next
                                    input__122159_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?form input__122159_nth_0__]
                                    (clojure.core/let
                                     [?env input__122159_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__10883__auto__
                                          (CATA__FN__122227
                                           [?pattern ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__10883__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__10883__auto__
                                           0))),
                                        :expression
                                        {:tag :host-expression,
                                         :form ?expression},
                                        :next
                                        (clojure.core/let
                                         [CATA_RESULT__10883__auto__
                                          (CATA__FN__122227
                                           [?next ?env])]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           CATA_RESULT__10883__auto__)
                                          (throw
                                           (meander.runtime.zeta/fail))
                                          (clojure.core/nth
                                           CATA_RESULT__10883__auto__
                                           0)))}]
                                      (catch
                                       java.lang.Exception
                                       e__11823__auto__
                                       (if
                                        (meander.runtime.zeta/fail?
                                         e__11823__auto__)
                                        (meander.runtime.zeta/fail)
                                        (throw
                                         e__11823__auto__))))))))))
                               (state__123491))
                              (state__123491)))
                            (state__123491))
                           (state__123491)))))
                       (state__123491)))))
                   (state__123491))))
                (state__123491)))
              (state__123491)))
            (state__123491))
           (state__123491))))
        (state__123491
         []
         (clojure.core/letfn
          [(def__123104
            [arg__123127 ?__122169]
            (clojure.core/letfn
             [(state__123664
               []
               (clojure.core/let
                [x__123128 "meander.zeta"]
                (if
                 (clojure.core/= ?__122169 x__123128)
                 [?__122169]
                 (state__123665))))
              (state__123665
               []
               (if
                (clojure.core/map? arg__123127)
                (clojure.core/let
                 [VAL__123129 (.valAt arg__123127 :aliases)]
                 (if
                  (clojure.core/map? VAL__123129)
                  (clojure.core/let
                   [X__123131 (clojure.core/set VAL__123129)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__123131))
                    (clojure.core/loop
                     [search_space__123666
                      (clojure.core/seq X__123131)]
                     (if
                      (clojure.core/seq search_space__123666)
                      (clojure.core/let
                       [elem__123132
                        (clojure.core/first search_space__123666)
                        result__123667
                        (clojure.core/let
                         [elem__123132_nth_0__
                          (clojure.core/nth elem__123132 0)
                          elem__123132_nth_1__
                          (clojure.core/nth elem__123132 1)]
                         (if
                          (clojure.core/symbol? elem__123132_nth_0__)
                          (clojure.core/let
                           [X__123134
                            (clojure.core/name elem__123132_nth_0__)]
                           (if
                            (clojure.core/= ?__122169 X__123134)
                            (if
                             (clojure.core/symbol?
                              elem__123132_nth_1__)
                             (clojure.core/let
                              [X__123136
                               (clojure.core/name
                                elem__123132_nth_1__)]
                              (clojure.core/case
                               X__123136
                               ("meander.zeta")
                               [?__122169]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__123667)
                        (recur
                         (clojure.core/next search_space__123666))
                        result__123667))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__123664)))]
          (if
           (clojure.core/vector? input__122159)
           (if
            (clojure.core/= (clojure.core/count input__122159) 2)
            (clojure.core/let
             [input__122159_nth_0__
              (clojure.core/nth input__122159 0)
              input__122159_nth_1__
              (clojure.core/nth input__122159 1)]
             (if
              (clojure.core/seq? input__122159_nth_0__)
              (clojure.core/let
               [input__122159_nth_0___l__
                (clojure.core/take 1 input__122159_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__122159_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__122159_nth_0___r__
                  (clojure.core/drop 1 input__122159_nth_0__)]
                 (clojure.core/let
                  [input__122159_nth_0___l___nth_0__
                   (clojure.core/nth input__122159_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__122159_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__123114
                     (clojure.core/namespace
                      input__122159_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__122169 X__123114]
                     (clojure.core/let
                      [X__123116
                       (clojure.core/name
                        input__122159_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__123116
                       ("not")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__123104 input__122159_nth_1__ ?__122169)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__123492)
                         (clojure.core/let
                          [[?__122169] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__122159)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__122159)
                             2)
                            (clojure.core/let
                             [input__122159_nth_0__
                              (clojure.core/nth input__122159 0)
                              input__122159_nth_1__
                              (clojure.core/nth input__122159 1)]
                             (if
                              (clojure.core/seq? input__122159_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__122159_nth_0__)
                                2)
                               (clojure.core/let
                                [input__122159_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__122159_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__122159_nth_0__]
                                  (clojure.core/let
                                   [?env input__122159_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__10883__auto__
                                        (CATA__FN__122227
                                         [?pattern ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__10883__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__10883__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__11823__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11823__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11823__auto__))))))))
                               (state__123492))
                              (state__123492)))
                            (state__123492))
                           (state__123492)))))
                       (state__123492)))))
                   (state__123492))))
                (state__123492)))
              (state__123492)))
            (state__123492))
           (state__123492))))
        (state__123492
         []
         (clojure.core/letfn
          [(def__123138
            [arg__123161 ?__122170]
            (clojure.core/letfn
             [(state__123669
               []
               (clojure.core/let
                [x__123162 "meander.zeta"]
                (if
                 (clojure.core/= ?__122170 x__123162)
                 [?__122170]
                 (state__123670))))
              (state__123670
               []
               (if
                (clojure.core/map? arg__123161)
                (clojure.core/let
                 [VAL__123163 (.valAt arg__123161 :aliases)]
                 (if
                  (clojure.core/map? VAL__123163)
                  (clojure.core/let
                   [X__123165 (clojure.core/set VAL__123163)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__123165))
                    (clojure.core/loop
                     [search_space__123671
                      (clojure.core/seq X__123165)]
                     (if
                      (clojure.core/seq search_space__123671)
                      (clojure.core/let
                       [elem__123166
                        (clojure.core/first search_space__123671)
                        result__123672
                        (clojure.core/let
                         [elem__123166_nth_0__
                          (clojure.core/nth elem__123166 0)
                          elem__123166_nth_1__
                          (clojure.core/nth elem__123166 1)]
                         (if
                          (clojure.core/symbol? elem__123166_nth_0__)
                          (clojure.core/let
                           [X__123168
                            (clojure.core/name elem__123166_nth_0__)]
                           (if
                            (clojure.core/= ?__122170 X__123168)
                            (if
                             (clojure.core/symbol?
                              elem__123166_nth_1__)
                             (clojure.core/let
                              [X__123170
                               (clojure.core/name
                                elem__123166_nth_1__)]
                              (clojure.core/case
                               X__123170
                               ("meander.zeta")
                               [?__122170]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__123672)
                        (recur
                         (clojure.core/next search_space__123671))
                        result__123672))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__123669)))]
          (if
           (clojure.core/vector? input__122159)
           (if
            (clojure.core/= (clojure.core/count input__122159) 2)
            (clojure.core/let
             [input__122159_nth_0__
              (clojure.core/nth input__122159 0)
              input__122159_nth_1__
              (clojure.core/nth input__122159 1)]
             (if
              (clojure.core/seq? input__122159_nth_0__)
              (clojure.core/let
               [input__122159_nth_0___l__
                (clojure.core/take 1 input__122159_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__122159_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__122159_nth_0___r__
                  (clojure.core/drop 1 input__122159_nth_0__)]
                 (clojure.core/let
                  [input__122159_nth_0___l___nth_0__
                   (clojure.core/nth input__122159_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__122159_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__123148
                     (clojure.core/namespace
                      input__122159_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__122170 X__123148]
                     (clojure.core/let
                      [X__123150
                       (clojure.core/name
                        input__122159_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__123150
                       ("or")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__123138 input__122159_nth_1__ ?__122170)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__123493)
                         (clojure.core/let
                          [[?__122170] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__122159)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__122159)
                             2)
                            (clojure.core/let
                             [input__122159_nth_0__
                              (clojure.core/nth input__122159 0)
                              input__122159_nth_1__
                              (clojure.core/nth input__122159 1)]
                             (if
                              (clojure.core/seq? input__122159_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__122159_nth_0__)
                                3)
                               (clojure.core/let
                                [input__122159_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  1)
                                 input__122159_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?left input__122159_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?right
                                   input__122159_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__122159_nth_0__]
                                   (clojure.core/let
                                    [?env input__122159_nth_1__]
                                    (try
                                     [{:tag :or,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__122227
                                          [?left ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))),
                                       :right
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__122227
                                          [?right ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__11823__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11823__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11823__auto__)))))))))
                               (state__123493))
                              (state__123493)))
                            (state__123493))
                           (state__123493)))))
                       (state__123493)))))
                   (state__123493))))
                (state__123493)))
              (state__123493)))
            (state__123493))
           (state__123493))))
        (state__123493
         []
         (clojure.core/letfn
          [(def__123172
            [arg__123195 ?__122171]
            (clojure.core/letfn
             [(state__123674
               []
               (clojure.core/let
                [x__123196 "meander.zeta"]
                (if
                 (clojure.core/= ?__122171 x__123196)
                 [?__122171]
                 (state__123675))))
              (state__123675
               []
               (if
                (clojure.core/map? arg__123195)
                (clojure.core/let
                 [VAL__123197 (.valAt arg__123195 :aliases)]
                 (if
                  (clojure.core/map? VAL__123197)
                  (clojure.core/let
                   [X__123199 (clojure.core/set VAL__123197)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__123199))
                    (clojure.core/loop
                     [search_space__123676
                      (clojure.core/seq X__123199)]
                     (if
                      (clojure.core/seq search_space__123676)
                      (clojure.core/let
                       [elem__123200
                        (clojure.core/first search_space__123676)
                        result__123677
                        (clojure.core/let
                         [elem__123200_nth_0__
                          (clojure.core/nth elem__123200 0)
                          elem__123200_nth_1__
                          (clojure.core/nth elem__123200 1)]
                         (if
                          (clojure.core/symbol? elem__123200_nth_0__)
                          (clojure.core/let
                           [X__123202
                            (clojure.core/name elem__123200_nth_0__)]
                           (if
                            (clojure.core/= ?__122171 X__123202)
                            (if
                             (clojure.core/symbol?
                              elem__123200_nth_1__)
                             (clojure.core/let
                              [X__123204
                               (clojure.core/name
                                elem__123200_nth_1__)]
                              (clojure.core/case
                               X__123204
                               ("meander.zeta")
                               [?__122171]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__123677)
                        (recur
                         (clojure.core/next search_space__123676))
                        result__123677))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__123674)))]
          (if
           (clojure.core/vector? input__122159)
           (if
            (clojure.core/= (clojure.core/count input__122159) 2)
            (clojure.core/let
             [input__122159_nth_0__
              (clojure.core/nth input__122159 0)
              input__122159_nth_1__
              (clojure.core/nth input__122159 1)]
             (if
              (clojure.core/seq? input__122159_nth_0__)
              (clojure.core/let
               [input__122159_nth_0___l__
                (clojure.core/take 1 input__122159_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__122159_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__122159_nth_0___r__
                  (clojure.core/drop 1 input__122159_nth_0__)]
                 (clojure.core/let
                  [input__122159_nth_0___l___nth_0__
                   (clojure.core/nth input__122159_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__122159_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__123182
                     (clojure.core/namespace
                      input__122159_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__122171 X__123182]
                     (clojure.core/let
                      [X__123184
                       (clojure.core/name
                        input__122159_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__123184
                       ("pred")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__123172 input__122159_nth_1__ ?__122171)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__123494)
                         (clojure.core/let
                          [[?__122171] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__122159)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__122159)
                             2)
                            (clojure.core/let
                             [input__122159_nth_0__
                              (clojure.core/nth input__122159 0)
                              input__122159_nth_1__
                              (clojure.core/nth input__122159 1)]
                             (if
                              (clojure.core/seq? input__122159_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__122159_nth_0__)
                                2)
                               (clojure.core/let
                                [input__122159_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?expression
                                  input__122159_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__122159_nth_0__]
                                  (clojure.core/let
                                   [?env input__122159_nth_1__]
                                   (try
                                    [{:tag :pred,
                                      :expression
                                      {:tag :host-expression,
                                       :form ?expression},
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__11823__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11823__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11823__auto__))))))))
                               (state__123494))
                              (state__123494)))
                            (state__123494))
                           (state__123494)))))
                       (state__123494)))))
                   (state__123494))))
                (state__123494)))
              (state__123494)))
            (state__123494))
           (state__123494))))
        (state__123494
         []
         (clojure.core/letfn
          [(def__123206
            [arg__123229 ?__122172]
            (clojure.core/letfn
             [(state__123679
               []
               (clojure.core/let
                [x__123230 "meander.zeta"]
                (if
                 (clojure.core/= ?__122172 x__123230)
                 [?__122172]
                 (state__123680))))
              (state__123680
               []
               (if
                (clojure.core/map? arg__123229)
                (clojure.core/let
                 [VAL__123231 (.valAt arg__123229 :aliases)]
                 (if
                  (clojure.core/map? VAL__123231)
                  (clojure.core/let
                   [X__123233 (clojure.core/set VAL__123231)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__123233))
                    (clojure.core/loop
                     [search_space__123681
                      (clojure.core/seq X__123233)]
                     (if
                      (clojure.core/seq search_space__123681)
                      (clojure.core/let
                       [elem__123234
                        (clojure.core/first search_space__123681)
                        result__123682
                        (clojure.core/let
                         [elem__123234_nth_0__
                          (clojure.core/nth elem__123234 0)
                          elem__123234_nth_1__
                          (clojure.core/nth elem__123234 1)]
                         (if
                          (clojure.core/symbol? elem__123234_nth_0__)
                          (clojure.core/let
                           [X__123236
                            (clojure.core/name elem__123234_nth_0__)]
                           (if
                            (clojure.core/= ?__122172 X__123236)
                            (if
                             (clojure.core/symbol?
                              elem__123234_nth_1__)
                             (clojure.core/let
                              [X__123238
                               (clojure.core/name
                                elem__123234_nth_1__)]
                              (clojure.core/case
                               X__123238
                               ("meander.zeta")
                               [?__122172]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__123682)
                        (recur
                         (clojure.core/next search_space__123681))
                        result__123682))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__123679)))]
          (if
           (clojure.core/vector? input__122159)
           (if
            (clojure.core/= (clojure.core/count input__122159) 2)
            (clojure.core/let
             [input__122159_nth_0__
              (clojure.core/nth input__122159 0)
              input__122159_nth_1__
              (clojure.core/nth input__122159 1)]
             (if
              (clojure.core/seq? input__122159_nth_0__)
              (clojure.core/let
               [input__122159_nth_0___l__
                (clojure.core/take 1 input__122159_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__122159_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__122159_nth_0___r__
                  (clojure.core/drop 1 input__122159_nth_0__)]
                 (clojure.core/let
                  [input__122159_nth_0___l___nth_0__
                   (clojure.core/nth input__122159_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__122159_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__123216
                     (clojure.core/namespace
                      input__122159_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__122172 X__123216]
                     (clojure.core/let
                      [X__123218
                       (clojure.core/name
                        input__122159_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__123218
                       ("re")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__123206 input__122159_nth_1__ ?__122172)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__123495)
                         (clojure.core/let
                          [[?__122172] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__122159)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__122159)
                             2)
                            (clojure.core/let
                             [input__122159_nth_0__
                              (clojure.core/nth input__122159 0)
                              input__122159_nth_1__
                              (clojure.core/nth input__122159 1)]
                             (if
                              (clojure.core/seq? input__122159_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__122159_nth_0__)
                                2)
                               (clojure.core/let
                                [input__122159_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?regex input__122159_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__122159_nth_0__]
                                  (clojure.core/let
                                   [?env input__122159_nth_1__]
                                   (try
                                    [{:tag :regex,
                                      :regex ?regex,
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__11823__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11823__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11823__auto__))))))))
                               (state__123495))
                              (state__123495)))
                            (state__123495))
                           (state__123495)))))
                       (state__123495)))))
                   (state__123495))))
                (state__123495)))
              (state__123495)))
            (state__123495))
           (state__123495))))
        (state__123495
         []
         (clojure.core/letfn
          [(def__123240
            [arg__123263 ?__122173]
            (clojure.core/letfn
             [(state__123684
               []
               (clojure.core/let
                [x__123264 "meander.zeta"]
                (if
                 (clojure.core/= ?__122173 x__123264)
                 [?__122173]
                 (state__123685))))
              (state__123685
               []
               (if
                (clojure.core/map? arg__123263)
                (clojure.core/let
                 [VAL__123265 (.valAt arg__123263 :aliases)]
                 (if
                  (clojure.core/map? VAL__123265)
                  (clojure.core/let
                   [X__123267 (clojure.core/set VAL__123265)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__123267))
                    (clojure.core/loop
                     [search_space__123686
                      (clojure.core/seq X__123267)]
                     (if
                      (clojure.core/seq search_space__123686)
                      (clojure.core/let
                       [elem__123268
                        (clojure.core/first search_space__123686)
                        result__123687
                        (clojure.core/let
                         [elem__123268_nth_0__
                          (clojure.core/nth elem__123268 0)
                          elem__123268_nth_1__
                          (clojure.core/nth elem__123268 1)]
                         (if
                          (clojure.core/symbol? elem__123268_nth_0__)
                          (clojure.core/let
                           [X__123270
                            (clojure.core/name elem__123268_nth_0__)]
                           (if
                            (clojure.core/= ?__122173 X__123270)
                            (if
                             (clojure.core/symbol?
                              elem__123268_nth_1__)
                             (clojure.core/let
                              [X__123272
                               (clojure.core/name
                                elem__123268_nth_1__)]
                              (clojure.core/case
                               X__123272
                               ("meander.zeta")
                               [?__122173]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__123687)
                        (recur
                         (clojure.core/next search_space__123686))
                        result__123687))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__123684)))]
          (if
           (clojure.core/vector? input__122159)
           (if
            (clojure.core/= (clojure.core/count input__122159) 2)
            (clojure.core/let
             [input__122159_nth_0__
              (clojure.core/nth input__122159 0)
              input__122159_nth_1__
              (clojure.core/nth input__122159 1)]
             (if
              (clojure.core/seq? input__122159_nth_0__)
              (clojure.core/let
               [input__122159_nth_0___l__
                (clojure.core/take 1 input__122159_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__122159_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__122159_nth_0___r__
                  (clojure.core/drop 1 input__122159_nth_0__)]
                 (clojure.core/let
                  [input__122159_nth_0___l___nth_0__
                   (clojure.core/nth input__122159_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__122159_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__123250
                     (clojure.core/namespace
                      input__122159_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__122173 X__123250]
                     (clojure.core/let
                      [X__123252
                       (clojure.core/name
                        input__122159_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__123252
                       ("re")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__123240 input__122159_nth_1__ ?__122173)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__123496)
                         (clojure.core/let
                          [[?__122173] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__122159)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__122159)
                             2)
                            (clojure.core/let
                             [input__122159_nth_0__
                              (clojure.core/nth input__122159 0)
                              input__122159_nth_1__
                              (clojure.core/nth input__122159 1)]
                             (if
                              (clojure.core/seq? input__122159_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__122159_nth_0__)
                                3)
                               (clojure.core/let
                                [input__122159_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  1)
                                 input__122159_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?regex input__122159_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?capture
                                   input__122159_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__122159_nth_0__]
                                   (clojure.core/let
                                    [?env input__122159_nth_1__]
                                    (try
                                     [{:tag :regex,
                                       :regex ?regex,
                                       :capture
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__122227
                                          [?capture ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__11823__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11823__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11823__auto__)))))))))
                               (state__123496))
                              (state__123496)))
                            (state__123496))
                           (state__123496)))))
                       (state__123496)))))
                   (state__123496))))
                (state__123496)))
              (state__123496)))
            (state__123496))
           (state__123496))))
        (state__123496
         []
         (clojure.core/letfn
          [(def__123274
            [arg__123297 ?__122174]
            (clojure.core/letfn
             [(state__123689
               []
               (clojure.core/let
                [x__123298 "meander.zeta"]
                (if
                 (clojure.core/= ?__122174 x__123298)
                 [?__122174]
                 (state__123690))))
              (state__123690
               []
               (if
                (clojure.core/map? arg__123297)
                (clojure.core/let
                 [VAL__123299 (.valAt arg__123297 :aliases)]
                 (if
                  (clojure.core/map? VAL__123299)
                  (clojure.core/let
                   [X__123301 (clojure.core/set VAL__123299)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__123301))
                    (clojure.core/loop
                     [search_space__123691
                      (clojure.core/seq X__123301)]
                     (if
                      (clojure.core/seq search_space__123691)
                      (clojure.core/let
                       [elem__123302
                        (clojure.core/first search_space__123691)
                        result__123692
                        (clojure.core/let
                         [elem__123302_nth_0__
                          (clojure.core/nth elem__123302 0)
                          elem__123302_nth_1__
                          (clojure.core/nth elem__123302 1)]
                         (if
                          (clojure.core/symbol? elem__123302_nth_0__)
                          (clojure.core/let
                           [X__123304
                            (clojure.core/name elem__123302_nth_0__)]
                           (if
                            (clojure.core/= ?__122174 X__123304)
                            (if
                             (clojure.core/symbol?
                              elem__123302_nth_1__)
                             (clojure.core/let
                              [X__123306
                               (clojure.core/name
                                elem__123302_nth_1__)]
                              (clojure.core/case
                               X__123306
                               ("meander.zeta")
                               [?__122174]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__123692)
                        (recur
                         (clojure.core/next search_space__123691))
                        result__123692))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__123689)))]
          (if
           (clojure.core/vector? input__122159)
           (if
            (clojure.core/= (clojure.core/count input__122159) 2)
            (clojure.core/let
             [input__122159_nth_0__
              (clojure.core/nth input__122159 0)
              input__122159_nth_1__
              (clojure.core/nth input__122159 1)]
             (if
              (clojure.core/seq? input__122159_nth_0__)
              (clojure.core/let
               [input__122159_nth_0___l__
                (clojure.core/take 1 input__122159_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__122159_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__122159_nth_0___r__
                  (clojure.core/drop 1 input__122159_nth_0__)]
                 (clojure.core/let
                  [input__122159_nth_0___l___nth_0__
                   (clojure.core/nth input__122159_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__122159_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__123284
                     (clojure.core/namespace
                      input__122159_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__122174 X__123284]
                     (clojure.core/let
                      [X__123286
                       (clojure.core/name
                        input__122159_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__123286
                       ("string")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__123274 input__122159_nth_1__ ?__122174)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__123497)
                         (clojure.core/let
                          [[?__122174] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__122159)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__122159)
                             2)
                            (clojure.core/let
                             [input__122159_nth_0__
                              (clojure.core/nth input__122159 0)
                              input__122159_nth_1__
                              (clojure.core/nth input__122159 1)]
                             (if
                              (clojure.core/seq? input__122159_nth_0__)
                              (clojure.core/let
                               [input__122159_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__122159_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__122159_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__122159_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__122159_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__122159_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__122159_nth_0__]
                                   (clojure.core/let
                                    [?env input__122159_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__122227
                                          ['meander.dev.parse.zeta/parse-sequential
                                           (clojure.core/into
                                            []
                                            ?sequence)
                                           (clojure.core/let
                                            [form__10982__auto__
                                             {:context :string}]
                                            (clojure.core/merge
                                             (clojure.core/into
                                              {}
                                              ?env)
                                             form__10982__auto__))])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__11823__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11823__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11823__auto__))))))))
                                (state__123497)))
                              (state__123497)))
                            (state__123497))
                           (state__123497)))))
                       (state__123497)))))
                   (state__123497))))
                (state__123497)))
              (state__123497)))
            (state__123497))
           (state__123497))))
        (state__123497
         []
         (clojure.core/letfn
          [(def__123308
            [arg__123331 ?__122175]
            (clojure.core/letfn
             [(state__123694
               []
               (clojure.core/let
                [x__123332 "meander.zeta"]
                (if
                 (clojure.core/= ?__122175 x__123332)
                 [?__122175]
                 (state__123695))))
              (state__123695
               []
               (if
                (clojure.core/map? arg__123331)
                (clojure.core/let
                 [VAL__123333 (.valAt arg__123331 :aliases)]
                 (if
                  (clojure.core/map? VAL__123333)
                  (clojure.core/let
                   [X__123335 (clojure.core/set VAL__123333)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__123335))
                    (clojure.core/loop
                     [search_space__123696
                      (clojure.core/seq X__123335)]
                     (if
                      (clojure.core/seq search_space__123696)
                      (clojure.core/let
                       [elem__123336
                        (clojure.core/first search_space__123696)
                        result__123697
                        (clojure.core/let
                         [elem__123336_nth_0__
                          (clojure.core/nth elem__123336 0)
                          elem__123336_nth_1__
                          (clojure.core/nth elem__123336 1)]
                         (if
                          (clojure.core/symbol? elem__123336_nth_0__)
                          (clojure.core/let
                           [X__123338
                            (clojure.core/name elem__123336_nth_0__)]
                           (if
                            (clojure.core/= ?__122175 X__123338)
                            (if
                             (clojure.core/symbol?
                              elem__123336_nth_1__)
                             (clojure.core/let
                              [X__123340
                               (clojure.core/name
                                elem__123336_nth_1__)]
                              (clojure.core/case
                               X__123340
                               ("meander.zeta")
                               [?__122175]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__123697)
                        (recur
                         (clojure.core/next search_space__123696))
                        result__123697))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__123694)))]
          (if
           (clojure.core/vector? input__122159)
           (if
            (clojure.core/= (clojure.core/count input__122159) 2)
            (clojure.core/let
             [input__122159_nth_0__
              (clojure.core/nth input__122159 0)
              input__122159_nth_1__
              (clojure.core/nth input__122159 1)]
             (if
              (clojure.core/seq? input__122159_nth_0__)
              (clojure.core/let
               [input__122159_nth_0___l__
                (clojure.core/take 1 input__122159_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__122159_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__122159_nth_0___r__
                  (clojure.core/drop 1 input__122159_nth_0__)]
                 (clojure.core/let
                  [input__122159_nth_0___l___nth_0__
                   (clojure.core/nth input__122159_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__122159_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__123318
                     (clojure.core/namespace
                      input__122159_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__122175 X__123318]
                     (clojure.core/let
                      [X__123320
                       (clojure.core/name
                        input__122159_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__123320
                       ("symbol")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__123308 input__122159_nth_1__ ?__122175)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__123498)
                         (clojure.core/let
                          [[?__122175] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__122159)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__122159)
                             2)
                            (clojure.core/let
                             [input__122159_nth_0__
                              (clojure.core/nth input__122159 0)
                              input__122159_nth_1__
                              (clojure.core/nth input__122159 1)]
                             (if
                              (clojure.core/seq? input__122159_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__122159_nth_0__)
                                2)
                               (clojure.core/let
                                [input__122159_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?name input__122159_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__122159_nth_0__]
                                  (clojure.core/let
                                   [?env input__122159_nth_1__]
                                   (try
                                    [{:tag :symbol,
                                      :name
                                      (clojure.core/let
                                       [CATA_RESULT__10883__auto__
                                        (CATA__FN__122227
                                         [?name ?env])]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         CATA_RESULT__10883__auto__)
                                        (throw
                                         (meander.runtime.zeta/fail))
                                        (clojure.core/nth
                                         CATA_RESULT__10883__auto__
                                         0))),
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__11823__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11823__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11823__auto__))))))))
                               (state__123498))
                              (state__123498)))
                            (state__123498))
                           (state__123498)))))
                       (state__123498)))))
                   (state__123498))))
                (state__123498)))
              (state__123498)))
            (state__123498))
           (state__123498))))
        (state__123498
         []
         (clojure.core/letfn
          [(def__123342
            [arg__123365 ?__122176]
            (clojure.core/letfn
             [(state__123699
               []
               (clojure.core/let
                [x__123366 "meander.zeta"]
                (if
                 (clojure.core/= ?__122176 x__123366)
                 [?__122176]
                 (state__123700))))
              (state__123700
               []
               (if
                (clojure.core/map? arg__123365)
                (clojure.core/let
                 [VAL__123367 (.valAt arg__123365 :aliases)]
                 (if
                  (clojure.core/map? VAL__123367)
                  (clojure.core/let
                   [X__123369 (clojure.core/set VAL__123367)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__123369))
                    (clojure.core/loop
                     [search_space__123701
                      (clojure.core/seq X__123369)]
                     (if
                      (clojure.core/seq search_space__123701)
                      (clojure.core/let
                       [elem__123370
                        (clojure.core/first search_space__123701)
                        result__123702
                        (clojure.core/let
                         [elem__123370_nth_0__
                          (clojure.core/nth elem__123370 0)
                          elem__123370_nth_1__
                          (clojure.core/nth elem__123370 1)]
                         (if
                          (clojure.core/symbol? elem__123370_nth_0__)
                          (clojure.core/let
                           [X__123372
                            (clojure.core/name elem__123370_nth_0__)]
                           (if
                            (clojure.core/= ?__122176 X__123372)
                            (if
                             (clojure.core/symbol?
                              elem__123370_nth_1__)
                             (clojure.core/let
                              [X__123374
                               (clojure.core/name
                                elem__123370_nth_1__)]
                              (clojure.core/case
                               X__123374
                               ("meander.zeta")
                               [?__122176]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__123702)
                        (recur
                         (clojure.core/next search_space__123701))
                        result__123702))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__123699)))]
          (if
           (clojure.core/vector? input__122159)
           (if
            (clojure.core/= (clojure.core/count input__122159) 2)
            (clojure.core/let
             [input__122159_nth_0__
              (clojure.core/nth input__122159 0)
              input__122159_nth_1__
              (clojure.core/nth input__122159 1)]
             (if
              (clojure.core/seq? input__122159_nth_0__)
              (clojure.core/let
               [input__122159_nth_0___l__
                (clojure.core/take 1 input__122159_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__122159_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__122159_nth_0___r__
                  (clojure.core/drop 1 input__122159_nth_0__)]
                 (clojure.core/let
                  [input__122159_nth_0___l___nth_0__
                   (clojure.core/nth input__122159_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__122159_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__123352
                     (clojure.core/namespace
                      input__122159_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__122176 X__123352]
                     (clojure.core/let
                      [X__123354
                       (clojure.core/name
                        input__122159_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__123354
                       ("symbol")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__123342 input__122159_nth_1__ ?__122176)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__123499)
                         (clojure.core/let
                          [[?__122176] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__122159)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__122159)
                             2)
                            (clojure.core/let
                             [input__122159_nth_0__
                              (clojure.core/nth input__122159 0)
                              input__122159_nth_1__
                              (clojure.core/nth input__122159 1)]
                             (if
                              (clojure.core/seq? input__122159_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__122159_nth_0__)
                                3)
                               (clojure.core/let
                                [input__122159_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  1)
                                 input__122159_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?namespace
                                  input__122159_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?name input__122159_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__122159_nth_0__]
                                   (clojure.core/let
                                    [?env input__122159_nth_1__]
                                    (try
                                     [{:tag :symbol,
                                       :name
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__122227
                                          [?name ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))),
                                       :namespace
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__122227
                                          [?namespace ?env])]
                                        (if
                                         (meander.runtime.zeta/fail?
                                          CATA_RESULT__10883__auto__)
                                         (throw
                                          (meander.runtime.zeta/fail))
                                         (clojure.core/nth
                                          CATA_RESULT__10883__auto__
                                          0))),
                                       :form ?form}]
                                     (catch
                                      java.lang.Exception
                                      e__11823__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11823__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11823__auto__)))))))))
                               (state__123499))
                              (state__123499)))
                            (state__123499))
                           (state__123499)))))
                       (state__123499)))))
                   (state__123499))))
                (state__123499)))
              (state__123499)))
            (state__123499))
           (state__123499))))
        (state__123499
         []
         (clojure.core/letfn
          [(def__123376
            [arg__123399 ?__122177]
            (clojure.core/letfn
             [(state__123704
               []
               (clojure.core/let
                [x__123400 "meander.zeta"]
                (if
                 (clojure.core/= ?__122177 x__123400)
                 [?__122177]
                 (state__123705))))
              (state__123705
               []
               (if
                (clojure.core/map? arg__123399)
                (clojure.core/let
                 [VAL__123401 (.valAt arg__123399 :aliases)]
                 (if
                  (clojure.core/map? VAL__123401)
                  (clojure.core/let
                   [X__123403 (clojure.core/set VAL__123401)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__123403))
                    (clojure.core/loop
                     [search_space__123706
                      (clojure.core/seq X__123403)]
                     (if
                      (clojure.core/seq search_space__123706)
                      (clojure.core/let
                       [elem__123404
                        (clojure.core/first search_space__123706)
                        result__123707
                        (clojure.core/let
                         [elem__123404_nth_0__
                          (clojure.core/nth elem__123404 0)
                          elem__123404_nth_1__
                          (clojure.core/nth elem__123404 1)]
                         (if
                          (clojure.core/symbol? elem__123404_nth_0__)
                          (clojure.core/let
                           [X__123406
                            (clojure.core/name elem__123404_nth_0__)]
                           (if
                            (clojure.core/= ?__122177 X__123406)
                            (if
                             (clojure.core/symbol?
                              elem__123404_nth_1__)
                             (clojure.core/let
                              [X__123408
                               (clojure.core/name
                                elem__123404_nth_1__)]
                              (clojure.core/case
                               X__123408
                               ("meander.zeta")
                               [?__122177]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__123707)
                        (recur
                         (clojure.core/next search_space__123706))
                        result__123707))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__123704)))]
          (if
           (clojure.core/vector? input__122159)
           (if
            (clojure.core/= (clojure.core/count input__122159) 2)
            (clojure.core/let
             [input__122159_nth_0__
              (clojure.core/nth input__122159 0)
              input__122159_nth_1__
              (clojure.core/nth input__122159 1)]
             (if
              (clojure.core/seq? input__122159_nth_0__)
              (clojure.core/let
               [input__122159_nth_0___l__
                (clojure.core/take 1 input__122159_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__122159_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__122159_nth_0___r__
                  (clojure.core/drop 1 input__122159_nth_0__)]
                 (clojure.core/let
                  [input__122159_nth_0___l___nth_0__
                   (clojure.core/nth input__122159_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__122159_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__123386
                     (clojure.core/namespace
                      input__122159_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__122177 X__123386]
                     (clojure.core/let
                      [X__123388
                       (clojure.core/name
                        input__122159_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__123388
                       ("symbol")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__123376 input__122159_nth_1__ ?__122177)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__123500)
                         (clojure.core/let
                          [[?__122177] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__122159)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__122159)
                             2)
                            (clojure.core/let
                             [input__122159_nth_0__
                              (clojure.core/nth input__122159 0)
                              input__122159_nth_1__
                              (clojure.core/nth input__122159 1)]
                             (if
                              (clojure.core/seq? input__122159_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 5)
                                 input__122159_nth_0__)
                                5)
                               (clojure.core/let
                                [input__122159_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  1)
                                 input__122159_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  2)
                                 input__122159_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  3)
                                 input__122159_nth_0___nth_4__
                                 (clojure.core/nth
                                  input__122159_nth_0__
                                  4)]
                                (clojure.core/case
                                 input__122159_nth_0___nth_3__
                                 (:meander.zeta/as)
                                 (clojure.core/let
                                  [?namespace
                                   input__122159_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?name
                                    input__122159_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?pattern
                                     input__122159_nth_0___nth_4__]
                                    (clojure.core/let
                                     [?form input__122159_nth_0__]
                                     (clojure.core/let
                                      [?env input__122159_nth_1__]
                                      (try
                                       [{:tag :symbol,
                                         :name
                                         (clojure.core/let
                                          [CATA_RESULT__10883__auto__
                                           (CATA__FN__122227
                                            [?name ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__10883__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__10883__auto__
                                            0))),
                                         :namespace
                                         (clojure.core/let
                                          [CATA_RESULT__10883__auto__
                                           (CATA__FN__122227
                                            [?namespace ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__10883__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__10883__auto__
                                            0))),
                                         :as-pattern
                                         (clojure.core/let
                                          [CATA_RESULT__10883__auto__
                                           (CATA__FN__122227
                                            [?pattern ?env])]
                                          (if
                                           (meander.runtime.zeta/fail?
                                            CATA_RESULT__10883__auto__)
                                           (throw
                                            (meander.runtime.zeta/fail))
                                           (clojure.core/nth
                                            CATA_RESULT__10883__auto__
                                            0))),
                                         :form ?form}]
                                       (catch
                                        java.lang.Exception
                                        e__11823__auto__
                                        (if
                                         (meander.runtime.zeta/fail?
                                          e__11823__auto__)
                                         (meander.runtime.zeta/fail)
                                         (throw
                                          e__11823__auto__)))))))))
                                 (state__123500)))
                               (state__123500))
                              (state__123500)))
                            (state__123500))
                           (state__123500)))))
                       (state__123500)))))
                   (state__123500))))
                (state__123500)))
              (state__123500)))
            (state__123500))
           (state__123500))))
        (state__123500
         []
         (if
          (clojure.core/vector? input__122159)
          (if
           (clojure.core/= (clojure.core/count input__122159) 2)
           (clojure.core/let
            [input__122159_nth_0__ (clojure.core/nth input__122159 0)]
            (clojure.core/letfn
             [(state__123709
               []
               (clojure.core/let
                [input__122159_nth_1__
                 (clojure.core/nth input__122159 1)]
                (clojure.core/letfn
                 [(state__123714
                   []
                   (if
                    (clojure.core/seq? input__122159_nth_0__)
                    (clojure.core/let
                     [?sequence input__122159_nth_0__]
                     (clojure.core/let
                      [?env input__122159_nth_1__]
                      (try
                       [{:tag :seq,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__10883__auto__
                           (CATA__FN__122227
                            ['meander.dev.parse.zeta/parse-sequential
                             (clojure.core/into [] ?sequence)
                             (clojure.core/let
                              [form__10982__auto__ {:context :seq}]
                              (clojure.core/merge
                               (clojure.core/into {} ?env)
                               form__10982__auto__))])]
                          (if
                           (meander.runtime.zeta/fail?
                            CATA_RESULT__10883__auto__)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth
                            CATA_RESULT__10883__auto__
                            0))),
                         :form ?sequence}]
                       (catch
                        java.lang.Exception
                        e__11823__auto__
                        (if
                         (meander.runtime.zeta/fail? e__11823__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__11823__auto__))))))
                    (state__123715)))
                  (state__123715
                   []
                   (if
                    (clojure.core/map? input__122159_nth_0__)
                    (clojure.core/let
                     [?map input__122159_nth_0__]
                     (clojure.core/let
                      [?env input__122159_nth_1__]
                      (try
                       [{:tag :map,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__10883__auto__
                           (CATA__FN__122227
                            ['meander.dev.parse.zeta/parse-entries
                             ?map
                             ?env])]
                          (if
                           (meander.runtime.zeta/fail?
                            CATA_RESULT__10883__auto__)
                           (throw (meander.runtime.zeta/fail))
                           (clojure.core/nth
                            CATA_RESULT__10883__auto__
                            0))),
                         :form ?map}]
                       (catch
                        java.lang.Exception
                        e__11823__auto__
                        (if
                         (meander.runtime.zeta/fail? e__11823__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__11823__auto__))))))
                    (state__123710)))]
                 (state__123714))))
              (state__123710
               []
               (if
                (clojure.core/symbol? input__122159_nth_0__)
                (clojure.core/let
                 [X__123418
                  (clojure.core/namespace input__122159_nth_0__)]
                 (clojure.core/let
                  [X__123420 (clojure.core/name input__122159_nth_0__)]
                  (if
                   (clojure.core/string? X__123420)
                   (clojure.core/letfn
                    [(state__123716
                      []
                      (clojure.core/let
                       [ret__123421
                        (clojure.core/re-matches #"_.*" X__123420)]
                       (if
                        (clojure.core/some? ret__123421)
                        (clojure.core/let
                         [?name ret__123421]
                         (clojure.core/let
                          [?symbol input__122159_nth_0__]
                          (try
                           [{:tag :wildcard,
                             :name ?name,
                             :form ?symbol,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__11823__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__11823__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__11823__auto__))))))
                        (state__123717))))
                     (state__123717
                      []
                      (clojure.core/let
                       [ret__123428
                        (clojure.core/re-matches #".+#" X__123420)]
                       (if
                        (clojure.core/some? ret__123428)
                        (clojure.core/let
                         [?name ret__123428]
                         (clojure.core/let
                          [?symbol input__122159_nth_0__]
                          (try
                           [{:tag :random-symbol,
                             :name ?name,
                             :form ?symbol,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__11823__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__11823__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__11823__auto__))))))
                        (state__123718))))
                     (state__123718
                      []
                      (clojure.core/let
                       [ret__123435
                        (clojure.core/re-matches #"%.+" X__123420)]
                       (if
                        (clojure.core/some? ret__123435)
                        (clojure.core/let
                         [?name ret__123435]
                         (clojure.core/let
                          [?symbol input__122159_nth_0__]
                          (try
                           [{:tag :reference,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__11823__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__11823__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__11823__auto__))))))
                        (state__123719))))
                     (state__123719
                      []
                      (clojure.core/let
                       [ret__123442
                        (clojure.core/re-matches #"\*.+" X__123420)]
                       (if
                        (clojure.core/some? ret__123442)
                        (clojure.core/let
                         [?name ret__123442]
                         (clojure.core/let
                          [?symbol input__122159_nth_0__]
                          (try
                           [{:tag :mutable-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__11823__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__11823__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__11823__auto__))))))
                        (state__123720))))
                     (state__123720
                      []
                      (clojure.core/let
                       [ret__123449
                        (clojure.core/re-matches #"\!.+" X__123420)]
                       (if
                        (clojure.core/some? ret__123449)
                        (clojure.core/let
                         [?name ret__123449]
                         (clojure.core/let
                          [?symbol input__122159_nth_0__]
                          (try
                           [{:tag :memory-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__11823__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__11823__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__11823__auto__))))))
                        (state__123721))))
                     (state__123721
                      []
                      (clojure.core/let
                       [ret__123456
                        (clojure.core/re-matches #"\?.+" X__123420)]
                       (if
                        (clojure.core/some? ret__123456)
                        (clojure.core/let
                         [?name ret__123456]
                         (clojure.core/let
                          [?symbol input__122159_nth_0__]
                          (try
                           [{:tag :logic-variable,
                             :name ?name,
                             :symbol ?symbol}]
                           (catch
                            java.lang.Exception
                            e__11823__auto__
                            (if
                             (meander.runtime.zeta/fail?
                              e__11823__auto__)
                             (meander.runtime.zeta/fail)
                             (throw e__11823__auto__))))))
                        (state__123711))))]
                    (state__123716))
                   (state__123711))))
                (state__123711)))
              (state__123711
               []
               (if
                (string? input__122159_nth_0__)
                (clojure.core/let
                 [?x input__122159_nth_0__]
                 (try
                  [{:tag :literal, :type :string, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__11823__auto__
                   (if
                    (meander.runtime.zeta/fail? e__11823__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__11823__auto__)))))
                (state__123712)))
              (state__123712
               []
               (if
                (char? input__122159_nth_0__)
                (clojure.core/let
                 [?x input__122159_nth_0__]
                 (try
                  [{:tag :literal, :type :char, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__11823__auto__
                   (if
                    (meander.runtime.zeta/fail? e__11823__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__11823__auto__)))))
                (state__123713)))
              (state__123713
               []
               (clojure.core/let
                [?x input__122159_nth_0__]
                (try
                 [{:tag :literal, :form ?x}]
                 (catch
                  java.lang.Exception
                  e__11823__auto__
                  (if
                   (meander.runtime.zeta/fail? e__11823__auto__)
                   (meander.runtime.zeta/fail)
                   (throw e__11823__auto__))))))]
             (state__123709)))
           (state__123501))
          (state__123501)))
        (state__123501
         []
         (clojure.core/let
          [?x input__122159]
          (try
           [{:tag :mistake, :x ?x}]
           (catch
            java.lang.Exception
            e__11823__auto__
            (if
             (meander.runtime.zeta/fail? e__11823__auto__)
             (meander.runtime.zeta/fail)
             (throw e__11823__auto__))))))]
       (state__123469)))]
    (clojure.core/let
     [x__9580__auto__ (CATA__FN__122227 input__122159)]
     (if
      (meander.runtime.zeta/fail? x__9580__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__122229] x__9580__auto__]
       CATA_RETURN__122229))))]
  (if
   (meander.runtime.zeta/fail? ret__9760__auto__)
   nil
   ret__9760__auto__)))
