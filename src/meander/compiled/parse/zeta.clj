(ns meander.compiled.parse.zeta (:require [meander.runtime.zeta]))
(clojure.core/defn
 parse
 [input__270931]
 (let*
  [ret__9760__auto__
   (clojure.core/letfn
    [(CATA__FN__271005
      [input__270931]
      (clojure.core/letfn
       [(state__272409
         []
         (if
          (clojure.core/vector? input__270931)
          (if
           (clojure.core/= (clojure.core/count input__270931) 3)
           (clojure.core/let
            [input__270931_nth_0__
             (clojure.core/nth input__270931 0)
             input__270931_nth_1__
             (clojure.core/nth input__270931 1)
             input__270931_nth_2__
             (clojure.core/nth input__270931 2)]
            (clojure.core/case
             input__270931_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__270931_nth_1__)
              (clojure.core/letfn
               [(state__272446
                 []
                 (clojure.core/case
                  input__270931_nth_1__
                  ([])
                  (clojure.core/let
                   [?env input__270931_nth_2__]
                   (try
                    [{:tag :empty}]
                    (catch
                     java.lang.Exception
                     e__11823__auto__
                     (if
                      (meander.runtime.zeta/fail? e__11823__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__11823__auto__)))))
                  (state__272447)))
                (state__272447
                 []
                 (clojure.core/let
                  [n__271014
                   (clojure.core/count input__270931_nth_1__)
                   m__271015
                   (clojure.core/max 0 (clojure.core/- n__271014 2))
                   input__270931_nth_1___l__
                   (clojure.core/subvec
                    input__270931_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__270931_nth_1__)
                     m__271015))
                   input__270931_nth_1___r__
                   (clojure.core/subvec
                    input__270931_nth_1__
                    m__271015)]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__270931_nth_1___r__)
                    2)
                   (clojure.core/let
                    [!xs (clojure.core/vec input__270931_nth_1___l__)]
                    (if
                     (clojure.core/=
                      (clojure.core/count input__270931_nth_1___r__)
                      2)
                     (clojure.core/let
                      [input__270931_nth_1___r___nth_0__
                       (clojure.core/nth input__270931_nth_1___r__ 0)
                       input__270931_nth_1___r___nth_1__
                       (clojure.core/nth input__270931_nth_1___r__ 1)]
                      (clojure.core/case
                       input__270931_nth_1___r___nth_0__
                       (:meander.zeta/as)
                       (clojure.core/let
                        [?pattern input__270931_nth_1___r___nth_1__]
                        (clojure.core/let
                         [?env input__270931_nth_2__]
                         (try
                          [(clojure.core/let
                            [!xs__counter
                             (meander.runtime.zeta/iterator !xs)]
                            {:tag :as,
                             :pattern
                             (clojure.core/let
                              [CATA_RESULT__10883__auto__
                               (CATA__FN__271005 [?pattern ?env])]
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
                               (CATA__FN__271005
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
                       (state__272410)))
                     (state__272410)))
                   (state__272410))))]
               (state__272446))
              (state__272410))
             (state__272410)))
           (state__272410))
          (state__272410)))
        (state__272410
         []
         (clojure.core/letfn
          [(def__271020
            [arg__271055 ?ns]
            (clojure.core/letfn
             [(state__272448
               []
               (clojure.core/let
                [x__271056 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__271056)
                 (clojure.core/let [?env arg__271055] [?env ?ns])
                 (state__272449))))
              (state__272449
               []
               (if
                (clojure.core/map? arg__271055)
                (clojure.core/let
                 [VAL__271057 (.valAt arg__271055 :aliases)]
                 (if
                  (clojure.core/map? VAL__271057)
                  (clojure.core/let
                   [X__271059 (clojure.core/set VAL__271057)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__271059))
                    (clojure.core/loop
                     [search_space__272450
                      (clojure.core/seq X__271059)]
                     (if
                      (clojure.core/seq search_space__272450)
                      (clojure.core/let
                       [elem__271060
                        (clojure.core/first search_space__272450)
                        result__272451
                        (clojure.core/let
                         [elem__271060_nth_0__
                          (clojure.core/nth elem__271060 0)
                          elem__271060_nth_1__
                          (clojure.core/nth elem__271060 1)]
                         (if
                          (clojure.core/symbol? elem__271060_nth_0__)
                          (clojure.core/let
                           [X__271062
                            (clojure.core/name elem__271060_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__271062)
                            (if
                             (clojure.core/symbol?
                              elem__271060_nth_1__)
                             (clojure.core/let
                              [X__271064
                               (clojure.core/name
                                elem__271060_nth_1__)]
                              (clojure.core/case
                               X__271064
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__271055]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__272451)
                        (recur
                         (clojure.core/next search_space__272450))
                        result__272451))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__272448)))]
          (if
           (clojure.core/vector? input__270931)
           (if
            (clojure.core/= (clojure.core/count input__270931) 3)
            (clojure.core/let
             [input__270931_nth_0__
              (clojure.core/nth input__270931 0)
              input__270931_nth_1__
              (clojure.core/nth input__270931 1)
              input__270931_nth_2__
              (clojure.core/nth input__270931 2)]
             (clojure.core/case
              input__270931_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__270931_nth_1__)
               (clojure.core/loop
                [search_space__272453
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__270931_nth_1__)]
                (if
                 (clojure.core/seq search_space__272453)
                 (clojure.core/let
                  [input__270931_nth_1___parts__
                   (clojure.core/first search_space__272453)
                   result__272454
                   (clojure.core/let
                    [input__270931_nth_1___l__
                     (clojure.core/nth input__270931_nth_1___parts__ 0)
                     input__270931_nth_1___r__
                     (clojure.core/nth
                      input__270931_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__270931_nth_1___l__)]
                     (clojure.core/let
                      [input__270931_nth_1___r___l__
                       (clojure.core/subvec
                        input__270931_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__270931_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__270931_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__270931_nth_1___r___r__
                         (clojure.core/subvec
                          input__270931_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__270931_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__270931_nth_1___r___l__
                           0)
                          input__270931_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__270931_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__270931_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__271029
                            (clojure.core/namespace
                             input__270931_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__271029]
                            (clojure.core/let
                             [X__271031
                              (clojure.core/name
                               input__270931_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__271031)
                              (clojure.core/let
                               [ret__271032
                                (clojure.core/re-matches
                                 #"&(\d+)"
                                 X__271031)]
                               (if
                                (clojure.core/some? ret__271032)
                                (if
                                 (clojure.core/vector? ret__271032)
                                 (if
                                  (clojure.core/=
                                   (clojure.core/count ret__271032)
                                   2)
                                  (clojure.core/let
                                   [ret__271032_nth_1__
                                    (clojure.core/nth ret__271032 1)]
                                   (clojure.core/let
                                    [?n ret__271032_nth_1__]
                                    (clojure.core/let
                                     [?pattern
                                      input__270931_nth_1___r___l___nth_1__]
                                     (clojure.core/let
                                      [?rest
                                       input__270931_nth_1___r___r__]
                                      (clojure.core/let
                                       [x__9580__auto__
                                        (def__271020
                                         input__270931_nth_2__
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
                                              (CATA__FN__271005
                                               ['meander.dev.parse.zeta/make-join
                                                (clojure.core/let
                                                 [CATA_RESULT__10883__auto__
                                                  (CATA__FN__271005
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
                                                  (CATA__FN__271005
                                                   ['meander.dev.parse.zeta/make-join
                                                    {:tag :slice,
                                                     :size
                                                     (Integer. ?n),
                                                     :pattern
                                                     (clojure.core/let
                                                      [CATA_RESULT__10883__auto__
                                                       (CATA__FN__271005
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
                                                      (CATA__FN__271005
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
                   (meander.runtime.zeta/fail? result__272454)
                   (recur (clojure.core/next search_space__272453))
                   result__272454))
                 (state__272411)))
               (state__272411))
              (state__272411)))
            (state__272411))
           (state__272411))))
        (state__272411
         []
         (clojure.core/letfn
          [(def__271077
            [arg__271109 ?ns]
            (clojure.core/letfn
             [(state__272456
               []
               (clojure.core/let
                [x__271110 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__271110)
                 (clojure.core/let [?env arg__271109] [?env ?ns])
                 (state__272457))))
              (state__272457
               []
               (if
                (clojure.core/map? arg__271109)
                (clojure.core/let
                 [VAL__271111 (.valAt arg__271109 :aliases)]
                 (if
                  (clojure.core/map? VAL__271111)
                  (clojure.core/let
                   [X__271113 (clojure.core/set VAL__271111)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__271113))
                    (clojure.core/loop
                     [search_space__272458
                      (clojure.core/seq X__271113)]
                     (if
                      (clojure.core/seq search_space__272458)
                      (clojure.core/let
                       [elem__271114
                        (clojure.core/first search_space__272458)
                        result__272459
                        (clojure.core/let
                         [elem__271114_nth_0__
                          (clojure.core/nth elem__271114 0)
                          elem__271114_nth_1__
                          (clojure.core/nth elem__271114 1)]
                         (if
                          (clojure.core/symbol? elem__271114_nth_0__)
                          (clojure.core/let
                           [X__271116
                            (clojure.core/name elem__271114_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__271116)
                            (if
                             (clojure.core/symbol?
                              elem__271114_nth_1__)
                             (clojure.core/let
                              [X__271118
                               (clojure.core/name
                                elem__271114_nth_1__)]
                              (clojure.core/case
                               X__271118
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__271109]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__272459)
                        (recur
                         (clojure.core/next search_space__272458))
                        result__272459))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__272456)))]
          (if
           (clojure.core/vector? input__270931)
           (if
            (clojure.core/= (clojure.core/count input__270931) 3)
            (clojure.core/let
             [input__270931_nth_0__
              (clojure.core/nth input__270931 0)
              input__270931_nth_1__
              (clojure.core/nth input__270931 1)
              input__270931_nth_2__
              (clojure.core/nth input__270931 2)]
             (clojure.core/case
              input__270931_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__270931_nth_1__)
               (clojure.core/loop
                [search_space__272461
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__270931_nth_1__)]
                (if
                 (clojure.core/seq search_space__272461)
                 (clojure.core/let
                  [input__270931_nth_1___parts__
                   (clojure.core/first search_space__272461)
                   result__272462
                   (clojure.core/let
                    [input__270931_nth_1___l__
                     (clojure.core/nth input__270931_nth_1___parts__ 0)
                     input__270931_nth_1___r__
                     (clojure.core/nth
                      input__270931_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!init
                      (clojure.core/vec input__270931_nth_1___l__)]
                     (clojure.core/let
                      [input__270931_nth_1___r___l__
                       (clojure.core/subvec
                        input__270931_nth_1___r__
                        0
                        (clojure.core/min
                         (clojure.core/count input__270931_nth_1___r__)
                         2))]
                      (if
                       (clojure.core/=
                        (clojure.core/count
                         input__270931_nth_1___r___l__)
                        2)
                       (clojure.core/let
                        [input__270931_nth_1___r___r__
                         (clojure.core/subvec
                          input__270931_nth_1___r__
                          2)]
                        (clojure.core/let
                         [input__270931_nth_1___r___l___nth_0__
                          (clojure.core/nth
                           input__270931_nth_1___r___l__
                           0)
                          input__270931_nth_1___r___l___nth_1__
                          (clojure.core/nth
                           input__270931_nth_1___r___l__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__270931_nth_1___r___l___nth_0__)
                          (clojure.core/let
                           [X__271086
                            (clojure.core/namespace
                             input__270931_nth_1___r___l___nth_0__)]
                           (clojure.core/let
                            [?ns X__271086]
                            (clojure.core/let
                             [X__271088
                              (clojure.core/name
                               input__270931_nth_1___r___l___nth_0__)]
                             (if
                              (clojure.core/string? X__271088)
                              (if
                               (clojure.core/re-matches
                                #"&.*"
                                X__271088)
                               (clojure.core/let
                                [?pattern
                                 input__270931_nth_1___r___l___nth_1__]
                                (clojure.core/let
                                 [?rest input__270931_nth_1___r___r__]
                                 (clojure.core/let
                                  [x__9580__auto__
                                   (def__271077
                                    input__270931_nth_2__
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
                                         (CATA__FN__271005
                                          ['meander.dev.parse.zeta/make-join
                                           (clojure.core/let
                                            [CATA_RESULT__10883__auto__
                                             (CATA__FN__271005
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
                                             (CATA__FN__271005
                                              ['meander.dev.parse.zeta/make-join
                                               (clojure.core/let
                                                [CATA_RESULT__10883__auto__
                                                 (CATA__FN__271005
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
                                                 (CATA__FN__271005
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
                   (meander.runtime.zeta/fail? result__272462)
                   (recur (clojure.core/next search_space__272461))
                   result__272462))
                 (state__272412)))
               (state__272412))
              (state__272412)))
            (state__272412))
           (state__272412))))
        (state__272412
         []
         (if
          (clojure.core/vector? input__270931)
          (clojure.core/letfn
           [(state__272464
             []
             (if
              (clojure.core/= (clojure.core/count input__270931) 3)
              (clojure.core/let
               [input__270931_nth_0__
                (clojure.core/nth input__270931 0)
                input__270931_nth_1__
                (clojure.core/nth input__270931 1)
                input__270931_nth_2__
                (clojure.core/nth input__270931 2)]
               (clojure.core/case
                input__270931_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__270931_nth_1__)
                 (clojure.core/letfn
                  [(state__272467
                    []
                    (clojure.core/let
                     [n__271139
                      (clojure.core/count input__270931_nth_1__)
                      m__271140
                      (clojure.core/max 0 (clojure.core/- n__271139 2))
                      input__270931_nth_1___l__
                      (clojure.core/subvec
                       input__270931_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__270931_nth_1__)
                        m__271140))
                      input__270931_nth_1___r__
                      (clojure.core/subvec
                       input__270931_nth_1__
                       m__271140)]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__270931_nth_1___r__)
                       2)
                      (clojure.core/let
                       [!xs
                        (clojure.core/vec input__270931_nth_1___l__)]
                       (if
                        (clojure.core/=
                         (clojure.core/count input__270931_nth_1___r__)
                         2)
                        (clojure.core/let
                         [input__270931_nth_1___r___nth_0__
                          (clojure.core/nth
                           input__270931_nth_1___r__
                           0)
                          input__270931_nth_1___r___nth_1__
                          (clojure.core/nth
                           input__270931_nth_1___r__
                           1)]
                         (if
                          (clojure.core/symbol?
                           input__270931_nth_1___r___nth_0__)
                          (clojure.core/let
                           [X__271144
                            (clojure.core/namespace
                             input__270931_nth_1___r___nth_0__)]
                           (clojure.core/let
                            [?ns X__271144]
                            (clojure.core/let
                             [X__271146
                              (clojure.core/name
                               input__270931_nth_1___r___nth_0__)]
                             (if
                              (clojure.core/string? X__271146)
                              (clojure.core/let
                               [ret__271147
                                (clojure.core/re-matches
                                 #"&.*"
                                 X__271146)]
                               (if
                                (clojure.core/some? ret__271147)
                                (clojure.core/let
                                 [?name ret__271147]
                                 (clojure.core/let
                                  [?pattern
                                   input__270931_nth_1___r___nth_1__]
                                  (if
                                   (clojure.core/map?
                                    input__270931_nth_2__)
                                   (clojure.core/let
                                    [VAL__271131
                                     (.valAt
                                      input__270931_nth_2__
                                      :aliases)]
                                    (if
                                     (clojure.core/map? VAL__271131)
                                     (clojure.core/let
                                      [X__271133
                                       (clojure.core/set VAL__271131)]
                                      (if
                                       (clojure.core/<=
                                        1
                                        (clojure.core/count X__271133))
                                       (clojure.core/loop
                                        [search_space__272471
                                         (clojure.core/seq X__271133)]
                                        (if
                                         (clojure.core/seq
                                          search_space__272471)
                                         (clojure.core/let
                                          [elem__271134
                                           (clojure.core/first
                                            search_space__272471)
                                           result__272472
                                           (clojure.core/let
                                            [elem__271134_nth_0__
                                             (clojure.core/nth
                                              elem__271134
                                              0)
                                             elem__271134_nth_1__
                                             (clojure.core/nth
                                              elem__271134
                                              1)]
                                            (if
                                             (clojure.core/symbol?
                                              elem__271134_nth_0__)
                                             (clojure.core/let
                                              [X__271136
                                               (clojure.core/name
                                                elem__271134_nth_0__)]
                                              (if
                                               (clojure.core/=
                                                ?ns
                                                X__271136)
                                               (if
                                                (clojure.core/symbol?
                                                 elem__271134_nth_1__)
                                                (clojure.core/let
                                                 [X__271138
                                                  (clojure.core/name
                                                   elem__271134_nth_1__)]
                                                 (clojure.core/case
                                                  X__271138
                                                  ("meander.zeta")
                                                  (clojure.core/let
                                                   [?env
                                                    input__270931_nth_2__]
                                                   (try
                                                    [(clojure.core/let
                                                      [!xs__counter
                                                       (meander.runtime.zeta/iterator
                                                        !xs)]
                                                      (clojure.core/let
                                                       [CATA_RESULT__10883__auto__
                                                        (CATA__FN__271005
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
                                            result__272472)
                                           (recur
                                            (clojure.core/next
                                             search_space__272471))
                                           result__272472))
                                         (state__272468)))
                                       (state__272468)))
                                     (state__272468)))
                                   (state__272468))))
                                (state__272468)))
                              (state__272468)))))
                          (state__272468)))
                        (state__272468)))
                      (state__272468))))
                   (state__272468
                    []
                    (clojure.core/loop
                     [search_space__272474
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__270931_nth_1__)]
                     (if
                      (clojure.core/seq search_space__272474)
                      (clojure.core/let
                       [input__270931_nth_1___parts__
                        (clojure.core/first search_space__272474)
                        result__272475
                        (clojure.core/let
                         [input__270931_nth_1___l__
                          (clojure.core/nth
                           input__270931_nth_1___parts__
                           0)
                          input__270931_nth_1___r__
                          (clojure.core/nth
                           input__270931_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs
                           (clojure.core/vec
                            input__270931_nth_1___l__)]
                          (clojure.core/let
                           [input__270931_nth_1___r___l__
                            (clojure.core/subvec
                             input__270931_nth_1___r__
                             0
                             (clojure.core/min
                              (clojure.core/count
                               input__270931_nth_1___r__)
                              1))]
                           (if
                            (clojure.core/=
                             (clojure.core/count
                              input__270931_nth_1___r___l__)
                             1)
                            (clojure.core/let
                             [input__270931_nth_1___r___r__
                              (clojure.core/subvec
                               input__270931_nth_1___r__
                               1)]
                             (if
                              (clojure.core/=
                               input__270931_nth_1___r___l__
                               ['.])
                              (clojure.core/let
                               [?rest input__270931_nth_1___r___r__]
                               (clojure.core/let
                                [?env input__270931_nth_2__]
                                (try
                                 [(clojure.core/let
                                   [!xs__counter
                                    (meander.runtime.zeta/iterator
                                     !xs)]
                                   (clojure.core/let
                                    [CATA_RESULT__10883__auto__
                                     (CATA__FN__271005
                                      ['meander.dev.parse.zeta/make-join
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__271005
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
                                         (CATA__FN__271005
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
                        (meander.runtime.zeta/fail? result__272475)
                        (recur
                         (clojure.core/next search_space__272474))
                        result__272475))
                      (state__272469))))
                   (state__272469
                    []
                    (clojure.core/let
                     [input__270931_nth_1___l__
                      (clojure.core/subvec
                       input__270931_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__270931_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__270931_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__270931_nth_1___r__
                        (clojure.core/subvec input__270931_nth_1__ 1)]
                       (if
                        (clojure.core/=
                         input__270931_nth_1___l__
                         ['...])
                        (clojure.core/let
                         [?rest input__270931_nth_1___r__]
                         (clojure.core/let
                          [?env input__270931_nth_2__]
                          (try
                           [(clojure.core/let
                             [CATA_RESULT__10883__auto__
                              (CATA__FN__271005
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
                        (state__272470)))
                      (state__272470))))
                   (state__272470
                    []
                    (clojure.core/loop
                     [search_space__272477
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__270931_nth_1__)]
                     (if
                      (clojure.core/seq search_space__272477)
                      (clojure.core/let
                       [input__270931_nth_1___parts__
                        (clojure.core/first search_space__272477)
                        result__272478
                        (clojure.core/let
                         [input__270931_nth_1___l__
                          (clojure.core/nth
                           input__270931_nth_1___parts__
                           0)
                          input__270931_nth_1___r__
                          (clojure.core/nth
                           input__270931_nth_1___parts__
                           1)]
                         (clojure.core/let
                          [!xs []]
                          (clojure.core/let
                           [ret__9744__auto__
                            (meander.runtime.zeta/epsilon-run-star-1
                             input__270931_nth_1___l__
                             [!xs]
                             (clojure.core/fn
                              [[!xs] input__271164]
                              (clojure.core/let
                               [input__271164_nth_0__
                                (clojure.core/nth input__271164 0)]
                               (clojure.core/letfn
                                [(save__271165
                                  []
                                  (meander.runtime.zeta/fail))
                                 (f__272481
                                  []
                                  (clojure.core/let
                                   [!xs
                                    (clojure.core/conj
                                     !xs
                                     input__271164_nth_0__)]
                                   [!xs]))]
                                (if
                                 (clojure.core/symbol?
                                  input__271164_nth_0__)
                                 (clojure.core/let
                                  [X__271167
                                   (clojure.core/namespace
                                    input__271164_nth_0__)]
                                  (clojure.core/case
                                   X__271167
                                   (nil)
                                   (clojure.core/let
                                    [X__271169
                                     (clojure.core/name
                                      input__271164_nth_0__)]
                                    (if
                                     (clojure.core/string? X__271169)
                                     (if
                                      (clojure.core/re-matches
                                       #"\.\.(?:\.|\d+)"
                                       X__271169)
                                      (save__271165)
                                      (f__272481))
                                     (f__272481)))
                                   (f__272481)))
                                 (f__272481)))))
                             (clojure.core/fn
                              [[!xs]]
                              (clojure.core/let
                               [input__270931_nth_1___r___l__
                                (clojure.core/subvec
                                 input__270931_nth_1___r__
                                 0
                                 (clojure.core/min
                                  (clojure.core/count
                                   input__270931_nth_1___r__)
                                  1))]
                               (if
                                (clojure.core/=
                                 (clojure.core/count
                                  input__270931_nth_1___r___l__)
                                 1)
                                (clojure.core/let
                                 [input__270931_nth_1___r___r__
                                  (clojure.core/subvec
                                   input__270931_nth_1___r__
                                   1)]
                                 (if
                                  (clojure.core/=
                                   input__270931_nth_1___r___l__
                                   ['...])
                                  (clojure.core/let
                                   [?rest
                                    input__270931_nth_1___r___r__]
                                   (clojure.core/let
                                    [?env input__270931_nth_2__]
                                    (try
                                     [(clojure.core/let
                                       [!xs__counter
                                        (meander.runtime.zeta/iterator
                                         !xs)]
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__271005
                                          ['meander.dev.parse.zeta/make-star
                                           (clojure.core/let
                                            [CATA_RESULT__10883__auto__
                                             (CATA__FN__271005
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
                                             (CATA__FN__271005
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
                        (meander.runtime.zeta/fail? result__272478)
                        (recur
                         (clojure.core/next search_space__272477))
                        result__272478))
                      (state__272465))))]
                  (state__272467))
                 (state__272465))
                (state__272465)))
              (state__272465)))
            (state__272465
             []
             (if
              (clojure.core/= (clojure.core/count input__270931) 4)
              (clojure.core/let
               [input__270931_nth_0__
                (clojure.core/nth input__270931 0)
                input__270931_nth_1__
                (clojure.core/nth input__270931 1)
                input__270931_nth_2__
                (clojure.core/nth input__270931 2)]
               (clojure.core/letfn
                [(state__272482
                  []
                  (clojure.core/let
                   [input__270931_nth_3__
                    (clojure.core/nth input__270931 3)]
                   (clojure.core/case
                    input__270931_nth_0__
                    (meander.dev.parse.zeta/make-star)
                    (clojure.core/letfn
                     [(state__272484
                       []
                       (if
                        (clojure.core/map? input__270931_nth_1__)
                        (clojure.core/let
                         [VAL__271173
                          (.valAt input__270931_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__271173
                          (:cat)
                          (clojure.core/let
                           [VAL__271174
                            (.valAt input__270931_nth_1__ :sequence)]
                           (if
                            (clojure.core/vector? VAL__271174)
                            (if
                             (clojure.core/=
                              (clojure.core/count VAL__271174)
                              1)
                             (clojure.core/let
                              [VAL__271174_nth_0__
                               (clojure.core/nth VAL__271174 0)]
                              (if
                               (clojure.core/map? VAL__271174_nth_0__)
                               (clojure.core/let
                                [VAL__271179
                                 (.valAt VAL__271174_nth_0__ :tag)]
                                (clojure.core/case
                                 VAL__271179
                                 (:memory-variable)
                                 (clojure.core/let
                                  [?memory-variable
                                   VAL__271174_nth_0__]
                                  (clojure.core/let
                                   [VAL__271175
                                    (.valAt
                                     input__270931_nth_1__
                                     :next)]
                                   (if
                                    (clojure.core/map? VAL__271175)
                                    (clojure.core/let
                                     [VAL__271176
                                      (.valAt VAL__271175 :tag)]
                                     (clojure.core/case
                                      VAL__271176
                                      (:empty)
                                      (clojure.core/let
                                       [?next input__270931_nth_2__]
                                       (clojure.core/let
                                        [?env input__270931_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__10883__auto__
                                            (CATA__FN__271005
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
                                      (state__272485)))
                                    (state__272485))))
                                 (state__272485)))
                               (state__272485)))
                             (state__272485))
                            (state__272485)))
                          (state__272485)))
                        (state__272485)))
                      (state__272485
                       []
                       (clojure.core/let
                        [?pattern input__270931_nth_1__]
                        (clojure.core/let
                         [?next input__270931_nth_2__]
                         (if
                          (clojure.core/map? input__270931_nth_3__)
                          (clojure.core/let
                           [VAL__271182
                            (.valAt input__270931_nth_3__ :context)]
                           (clojure.core/case
                            VAL__271182
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
                            (state__272483)))
                          (state__272483)))))]
                     (state__272484))
                    (state__272483))))
                 (state__272483
                  []
                  (clojure.core/case
                   input__270931_nth_0__
                   (meander.dev.parse.zeta/make-star)
                   (clojure.core/let
                    [?pattern input__270931_nth_1__]
                    (clojure.core/let
                     [?next input__270931_nth_2__]
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
                   (state__272466)))]
                (state__272482)))
              (state__272466)))
            (state__272466
             []
             (if
              (clojure.core/= (clojure.core/count input__270931) 3)
              (clojure.core/let
               [input__270931_nth_0__
                (clojure.core/nth input__270931 0)
                input__270931_nth_1__
                (clojure.core/nth input__270931 1)
                input__270931_nth_2__
                (clojure.core/nth input__270931 2)]
               (clojure.core/case
                input__270931_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__270931_nth_1__)
                 (clojure.core/let
                  [input__270931_nth_1___l__
                   (clojure.core/subvec
                    input__270931_nth_1__
                    0
                    (clojure.core/min
                     (clojure.core/count input__270931_nth_1__)
                     1))]
                  (if
                   (clojure.core/=
                    (clojure.core/count input__270931_nth_1___l__)
                    1)
                   (clojure.core/let
                    [input__270931_nth_1___r__
                     (clojure.core/subvec input__270931_nth_1__ 1)]
                    (clojure.core/let
                     [input__270931_nth_1___l___nth_0__
                      (clojure.core/nth input__270931_nth_1___l__ 0)]
                     (if
                      (clojure.core/symbol?
                       input__270931_nth_1___l___nth_0__)
                      (clojure.core/let
                       [X__271190
                        (clojure.core/namespace
                         input__270931_nth_1___l___nth_0__)]
                       (clojure.core/case
                        X__271190
                        (nil)
                        (clojure.core/let
                         [X__271192
                          (clojure.core/name
                           input__270931_nth_1___l___nth_0__)]
                         (if
                          (clojure.core/string? X__271192)
                          (clojure.core/let
                           [ret__271193
                            (clojure.core/re-matches
                             #"\.\.(\d+)"
                             X__271192)]
                           (if
                            (clojure.core/some? ret__271193)
                            (if
                             (clojure.core/vector? ret__271193)
                             (if
                              (clojure.core/=
                               (clojure.core/count ret__271193)
                               2)
                              (clojure.core/let
                               [ret__271193_nth_1__
                                (clojure.core/nth ret__271193 1)]
                               (clojure.core/let
                                [?n ret__271193_nth_1__]
                                (clojure.core/let
                                 [?operator
                                  input__270931_nth_1___l___nth_0__]
                                 (clojure.core/let
                                  [?rest input__270931_nth_1___r__]
                                  (clojure.core/let
                                   [?env input__270931_nth_2__]
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
                              (state__272413))
                             (state__272413))
                            (state__272413)))
                          (state__272413)))
                        (state__272413)))
                      (state__272413))))
                   (state__272413)))
                 (state__272413))
                (state__272413)))
              (state__272413)))]
           (state__272464))
          (state__272413)))
        (state__272413
         []
         (clojure.core/letfn
          [(def__271196
            [arg__271220]
            (clojure.core/letfn
             [(state__272486
               []
               (clojure.core/let
                [x__271221 :string-plus]
                (clojure.core/let
                 [?tag x__271221]
                 (if
                  (clojure.core/map? arg__271220)
                  (clojure.core/let
                   [VAL__271222 (.valAt arg__271220 :context)]
                   (clojure.core/case
                    VAL__271222
                    (:string)
                    (clojure.core/let [?env arg__271220] [?tag ?env])
                    (state__272487)))
                  (state__272487)))))
              (state__272487
               []
               (clojure.core/let
                [x__271223 :plus]
                (clojure.core/let
                 [?tag x__271223]
                 (clojure.core/let [?env arg__271220] [?tag ?env]))))]
             (state__272486)))]
          (if
           (clojure.core/vector? input__270931)
           (if
            (clojure.core/= (clojure.core/count input__270931) 3)
            (clojure.core/let
             [input__270931_nth_0__
              (clojure.core/nth input__270931 0)
              input__270931_nth_1__
              (clojure.core/nth input__270931 1)
              input__270931_nth_2__
              (clojure.core/nth input__270931 2)]
             (clojure.core/case
              input__270931_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__270931_nth_1__)
               (clojure.core/loop
                [search_space__272488
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__270931_nth_1__)]
                (if
                 (clojure.core/seq search_space__272488)
                 (clojure.core/let
                  [input__270931_nth_1___parts__
                   (clojure.core/first search_space__272488)
                   result__272489
                   (clojure.core/let
                    [input__270931_nth_1___l__
                     (clojure.core/nth input__270931_nth_1___parts__ 0)
                     input__270931_nth_1___r__
                     (clojure.core/nth
                      input__270931_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__9744__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__270931_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__271213]
                         (clojure.core/let
                          [input__271213_nth_0__
                           (clojure.core/nth input__271213 0)]
                          (clojure.core/letfn
                           [(save__271214
                             []
                             (meander.runtime.zeta/fail))
                            (f__272492
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__271213_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol?
                             input__271213_nth_0__)
                            (clojure.core/let
                             [X__271216
                              (clojure.core/namespace
                               input__271213_nth_0__)]
                             (clojure.core/case
                              X__271216
                              (nil)
                              (clojure.core/let
                               [X__271218
                                (clojure.core/name
                                 input__271213_nth_0__)]
                               (if
                                (clojure.core/string? X__271218)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__271218)
                                 (save__271214)
                                 (f__272492))
                                (f__272492)))
                              (f__272492)))
                            (f__272492)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__270931_nth_1___r___l__
                           (clojure.core/subvec
                            input__270931_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__270931_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__270931_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__270931_nth_1___r___r__
                             (clojure.core/subvec
                              input__270931_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__270931_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__270931_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__270931_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__271207
                                (clojure.core/namespace
                                 input__270931_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__271207
                                (nil)
                                (clojure.core/let
                                 [X__271209
                                  (clojure.core/name
                                   input__270931_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__271209)
                                  (clojure.core/let
                                   [ret__271210
                                    (clojure.core/re-matches
                                     #"\.\.(\d+)"
                                     X__271209)]
                                   (if
                                    (clojure.core/some? ret__271210)
                                    (if
                                     (clojure.core/vector? ret__271210)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__271210)
                                       2)
                                      (clojure.core/let
                                       [ret__271210_nth_1__
                                        (clojure.core/nth
                                         ret__271210
                                         1)]
                                       (clojure.core/let
                                        [?n ret__271210_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__270931_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__9580__auto__
                                           (def__271196
                                            input__270931_nth_2__)]
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
                                                  (CATA__FN__271005
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
                                                  (CATA__FN__271005
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
                   (meander.runtime.zeta/fail? result__272489)
                   (recur (clojure.core/next search_space__272488))
                   result__272489))
                 (state__272414)))
               (state__272414))
              (state__272414)))
            (state__272414))
           (state__272414))))
        (state__272414
         []
         (if
          (clojure.core/vector? input__270931)
          (if
           (clojure.core/= (clojure.core/count input__270931) 3)
           (clojure.core/let
            [input__270931_nth_0__
             (clojure.core/nth input__270931 0)
             input__270931_nth_1__
             (clojure.core/nth input__270931 1)
             input__270931_nth_2__
             (clojure.core/nth input__270931 2)]
            (clojure.core/case
             input__270931_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__270931_nth_1__)
              (clojure.core/let
               [input__270931_nth_1___l__
                (clojure.core/subvec
                 input__270931_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__270931_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__270931_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__270931_nth_1___r__
                  (clojure.core/subvec input__270931_nth_1__ 1)]
                 (clojure.core/let
                  [input__270931_nth_1___l___nth_0__
                   (clojure.core/nth input__270931_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__270931_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__271241
                     (clojure.core/namespace
                      input__270931_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__271241
                     (nil)
                     (clojure.core/let
                      [X__271243
                       (clojure.core/name
                        input__270931_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__271243)
                       (clojure.core/let
                        [ret__271244
                         (clojure.core/re-matches
                          #"\.\.(\?.+)"
                          X__271243)]
                        (if
                         (clojure.core/some? ret__271244)
                         (if
                          (clojure.core/vector? ret__271244)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__271244)
                            2)
                           (clojure.core/let
                            [ret__271244_nth_1__
                             (clojure.core/nth ret__271244 1)]
                            (clojure.core/let
                             [?n ret__271244_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__270931_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__270931_nth_1___r__]
                               (clojure.core/let
                                [?env input__270931_nth_2__]
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
                           (state__272415))
                          (state__272415))
                         (state__272415)))
                       (state__272415)))
                     (state__272415)))
                   (state__272415))))
                (state__272415)))
              (state__272415))
             (state__272415)))
           (state__272415))
          (state__272415)))
        (state__272415
         []
         (clojure.core/letfn
          [(def__271247
            [arg__271271]
            (clojure.core/letfn
             [(state__272493
               []
               (clojure.core/let
                [x__271272 :string-logical-plus]
                (clojure.core/let
                 [?tag x__271272]
                 (if
                  (clojure.core/map? arg__271271)
                  (clojure.core/let
                   [VAL__271273 (.valAt arg__271271 :context)]
                   (clojure.core/case
                    VAL__271273
                    (:string)
                    (clojure.core/let [?env arg__271271] [?tag ?env])
                    (state__272494)))
                  (state__272494)))))
              (state__272494
               []
               (clojure.core/let
                [x__271274 :logical-plus]
                (clojure.core/let
                 [?tag x__271274]
                 (clojure.core/let [?env arg__271271] [?tag ?env]))))]
             (state__272493)))]
          (if
           (clojure.core/vector? input__270931)
           (if
            (clojure.core/= (clojure.core/count input__270931) 3)
            (clojure.core/let
             [input__270931_nth_0__
              (clojure.core/nth input__270931 0)
              input__270931_nth_1__
              (clojure.core/nth input__270931 1)
              input__270931_nth_2__
              (clojure.core/nth input__270931 2)]
             (clojure.core/case
              input__270931_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__270931_nth_1__)
               (clojure.core/loop
                [search_space__272495
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__270931_nth_1__)]
                (if
                 (clojure.core/seq search_space__272495)
                 (clojure.core/let
                  [input__270931_nth_1___parts__
                   (clojure.core/first search_space__272495)
                   result__272496
                   (clojure.core/let
                    [input__270931_nth_1___l__
                     (clojure.core/nth input__270931_nth_1___parts__ 0)
                     input__270931_nth_1___r__
                     (clojure.core/nth
                      input__270931_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__9744__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__270931_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__271264]
                         (clojure.core/let
                          [input__271264_nth_0__
                           (clojure.core/nth input__271264 0)]
                          (clojure.core/letfn
                           [(save__271265
                             []
                             (meander.runtime.zeta/fail))
                            (f__272499
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__271264_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol?
                             input__271264_nth_0__)
                            (clojure.core/let
                             [X__271267
                              (clojure.core/namespace
                               input__271264_nth_0__)]
                             (clojure.core/case
                              X__271267
                              (nil)
                              (clojure.core/let
                               [X__271269
                                (clojure.core/name
                                 input__271264_nth_0__)]
                               (if
                                (clojure.core/string? X__271269)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__271269)
                                 (save__271265)
                                 (f__272499))
                                (f__272499)))
                              (f__272499)))
                            (f__272499)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__270931_nth_1___r___l__
                           (clojure.core/subvec
                            input__270931_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__270931_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__270931_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__270931_nth_1___r___r__
                             (clojure.core/subvec
                              input__270931_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__270931_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__270931_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__270931_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__271258
                                (clojure.core/namespace
                                 input__270931_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__271258
                                (nil)
                                (clojure.core/let
                                 [X__271260
                                  (clojure.core/name
                                   input__270931_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__271260)
                                  (clojure.core/let
                                   [ret__271261
                                    (clojure.core/re-matches
                                     #"\.\.(\?.+)"
                                     X__271260)]
                                   (if
                                    (clojure.core/some? ret__271261)
                                    (if
                                     (clojure.core/vector? ret__271261)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__271261)
                                       2)
                                      (clojure.core/let
                                       [ret__271261_nth_1__
                                        (clojure.core/nth
                                         ret__271261
                                         1)]
                                       (clojure.core/let
                                        [?n ret__271261_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__270931_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__9580__auto__
                                           (def__271247
                                            input__270931_nth_2__)]
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
                                                  (CATA__FN__271005
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
                                                  (CATA__FN__271005
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
                   (meander.runtime.zeta/fail? result__272496)
                   (recur (clojure.core/next search_space__272495))
                   result__272496))
                 (state__272416)))
               (state__272416))
              (state__272416)))
            (state__272416))
           (state__272416))))
        (state__272416
         []
         (if
          (clojure.core/vector? input__270931)
          (if
           (clojure.core/= (clojure.core/count input__270931) 3)
           (clojure.core/let
            [input__270931_nth_0__
             (clojure.core/nth input__270931 0)
             input__270931_nth_1__
             (clojure.core/nth input__270931 1)
             input__270931_nth_2__
             (clojure.core/nth input__270931 2)]
            (clojure.core/case
             input__270931_nth_0__
             (meander.dev.parse.zeta/parse-sequential)
             (if
              (clojure.core/vector? input__270931_nth_1__)
              (clojure.core/let
               [input__270931_nth_1___l__
                (clojure.core/subvec
                 input__270931_nth_1__
                 0
                 (clojure.core/min
                  (clojure.core/count input__270931_nth_1__)
                  1))]
               (if
                (clojure.core/=
                 (clojure.core/count input__270931_nth_1___l__)
                 1)
                (clojure.core/let
                 [input__270931_nth_1___r__
                  (clojure.core/subvec input__270931_nth_1__ 1)]
                 (clojure.core/let
                  [input__270931_nth_1___l___nth_0__
                   (clojure.core/nth input__270931_nth_1___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__270931_nth_1___l___nth_0__)
                   (clojure.core/let
                    [X__271292
                     (clojure.core/namespace
                      input__270931_nth_1___l___nth_0__)]
                    (clojure.core/case
                     X__271292
                     (nil)
                     (clojure.core/let
                      [X__271294
                       (clojure.core/name
                        input__270931_nth_1___l___nth_0__)]
                      (if
                       (clojure.core/string? X__271294)
                       (clojure.core/let
                        [ret__271295
                         (clojure.core/re-matches
                          #"\.\.(!.+)"
                          X__271294)]
                        (if
                         (clojure.core/some? ret__271295)
                         (if
                          (clojure.core/vector? ret__271295)
                          (if
                           (clojure.core/=
                            (clojure.core/count ret__271295)
                            2)
                           (clojure.core/let
                            [ret__271295_nth_1__
                             (clojure.core/nth ret__271295 1)]
                            (clojure.core/let
                             [?n ret__271295_nth_1__]
                             (clojure.core/let
                              [?operator
                               input__270931_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?rest input__270931_nth_1___r__]
                               (clojure.core/let
                                [?env input__270931_nth_2__]
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
                           (state__272417))
                          (state__272417))
                         (state__272417)))
                       (state__272417)))
                     (state__272417)))
                   (state__272417))))
                (state__272417)))
              (state__272417))
             (state__272417)))
           (state__272417))
          (state__272417)))
        (state__272417
         []
         (clojure.core/letfn
          [(def__271298
            [arg__271322]
            (clojure.core/letfn
             [(state__272500
               []
               (clojure.core/let
                [x__271323 :string-memory-plus]
                (clojure.core/let
                 [?tag x__271323]
                 (if
                  (clojure.core/map? arg__271322)
                  (clojure.core/let
                   [VAL__271324 (.valAt arg__271322 :context)]
                   (clojure.core/case
                    VAL__271324
                    (:string)
                    (clojure.core/let [?env arg__271322] [?tag ?env])
                    (state__272501)))
                  (state__272501)))))
              (state__272501
               []
               (clojure.core/let
                [x__271325 :memory-plus]
                (clojure.core/let
                 [?tag x__271325]
                 (clojure.core/let [?env arg__271322] [?tag ?env]))))]
             (state__272500)))]
          (if
           (clojure.core/vector? input__270931)
           (if
            (clojure.core/= (clojure.core/count input__270931) 3)
            (clojure.core/let
             [input__270931_nth_0__
              (clojure.core/nth input__270931 0)
              input__270931_nth_1__
              (clojure.core/nth input__270931 1)
              input__270931_nth_2__
              (clojure.core/nth input__270931 2)]
             (clojure.core/case
              input__270931_nth_0__
              (meander.dev.parse.zeta/parse-sequential)
              (if
               (clojure.core/vector? input__270931_nth_1__)
               (clojure.core/loop
                [search_space__272502
                 (meander.runtime.zeta/epsilon-partitions
                  2
                  input__270931_nth_1__)]
                (if
                 (clojure.core/seq search_space__272502)
                 (clojure.core/let
                  [input__270931_nth_1___parts__
                   (clojure.core/first search_space__272502)
                   result__272503
                   (clojure.core/let
                    [input__270931_nth_1___l__
                     (clojure.core/nth input__270931_nth_1___parts__ 0)
                     input__270931_nth_1___r__
                     (clojure.core/nth
                      input__270931_nth_1___parts__
                      1)]
                    (clojure.core/let
                     [!xs []]
                     (clojure.core/let
                      [ret__9744__auto__
                       (meander.runtime.zeta/epsilon-run-star-1
                        input__270931_nth_1___l__
                        [!xs]
                        (clojure.core/fn
                         [[!xs] input__271315]
                         (clojure.core/let
                          [input__271315_nth_0__
                           (clojure.core/nth input__271315 0)]
                          (clojure.core/letfn
                           [(save__271316
                             []
                             (meander.runtime.zeta/fail))
                            (f__272506
                             []
                             (clojure.core/let
                              [!xs
                               (clojure.core/conj
                                !xs
                                input__271315_nth_0__)]
                              [!xs]))]
                           (if
                            (clojure.core/symbol?
                             input__271315_nth_0__)
                            (clojure.core/let
                             [X__271318
                              (clojure.core/namespace
                               input__271315_nth_0__)]
                             (clojure.core/case
                              X__271318
                              (nil)
                              (clojure.core/let
                               [X__271320
                                (clojure.core/name
                                 input__271315_nth_0__)]
                               (if
                                (clojure.core/string? X__271320)
                                (if
                                 (clojure.core/re-matches
                                  #"\.\.(?:\.|\d+)"
                                  X__271320)
                                 (save__271316)
                                 (f__272506))
                                (f__272506)))
                              (f__272506)))
                            (f__272506)))))
                        (clojure.core/fn
                         [[!xs]]
                         (clojure.core/let
                          [input__270931_nth_1___r___l__
                           (clojure.core/subvec
                            input__270931_nth_1___r__
                            0
                            (clojure.core/min
                             (clojure.core/count
                              input__270931_nth_1___r__)
                             1))]
                          (if
                           (clojure.core/=
                            (clojure.core/count
                             input__270931_nth_1___r___l__)
                            1)
                           (clojure.core/let
                            [input__270931_nth_1___r___r__
                             (clojure.core/subvec
                              input__270931_nth_1___r__
                              1)]
                            (clojure.core/let
                             [input__270931_nth_1___r___l___nth_0__
                              (clojure.core/nth
                               input__270931_nth_1___r___l__
                               0)]
                             (if
                              (clojure.core/symbol?
                               input__270931_nth_1___r___l___nth_0__)
                              (clojure.core/let
                               [X__271309
                                (clojure.core/namespace
                                 input__270931_nth_1___r___l___nth_0__)]
                               (clojure.core/case
                                X__271309
                                (nil)
                                (clojure.core/let
                                 [X__271311
                                  (clojure.core/name
                                   input__270931_nth_1___r___l___nth_0__)]
                                 (if
                                  (clojure.core/string? X__271311)
                                  (clojure.core/let
                                   [ret__271312
                                    (clojure.core/re-matches
                                     #"\.\.(\!.+)"
                                     X__271311)]
                                   (if
                                    (clojure.core/some? ret__271312)
                                    (if
                                     (clojure.core/vector? ret__271312)
                                     (if
                                      (clojure.core/=
                                       (clojure.core/count ret__271312)
                                       2)
                                      (clojure.core/let
                                       [ret__271312_nth_1__
                                        (clojure.core/nth
                                         ret__271312
                                         1)]
                                       (clojure.core/let
                                        [?n ret__271312_nth_1__]
                                        (clojure.core/let
                                         [?rest
                                          input__270931_nth_1___r___r__]
                                         (clojure.core/let
                                          [x__9580__auto__
                                           (def__271298
                                            input__270931_nth_2__)]
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
                                                  (CATA__FN__271005
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
                                                  (CATA__FN__271005
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
                   (meander.runtime.zeta/fail? result__272503)
                   (recur (clojure.core/next search_space__272502))
                   result__272503))
                 (state__272418)))
               (state__272418))
              (state__272418)))
            (state__272418))
           (state__272418))))
        (state__272418
         []
         (if
          (clojure.core/vector? input__270931)
          (clojure.core/letfn
           [(state__272507
             []
             (if
              (clojure.core/= (clojure.core/count input__270931) 3)
              (clojure.core/let
               [input__270931_nth_0__
                (clojure.core/nth input__270931 0)
                input__270931_nth_1__
                (clojure.core/nth input__270931 1)
                input__270931_nth_2__
                (clojure.core/nth input__270931 2)]
               (clojure.core/case
                input__270931_nth_0__
                (meander.dev.parse.zeta/parse-sequential)
                (if
                 (clojure.core/vector? input__270931_nth_1__)
                 (clojure.core/let
                  [!xs (clojure.core/vec input__270931_nth_1__)]
                  (clojure.core/let
                   [?env input__270931_nth_2__]
                   (try
                    [(clojure.core/let
                      [!xs__counter
                       (meander.runtime.zeta/iterator !xs)]
                      (clojure.core/let
                       [CATA_RESULT__10883__auto__
                        (CATA__FN__271005
                         ['meander.dev.parse.zeta/make-cat
                          (clojure.core/into
                           []
                           (clojure.core/loop
                            [return__271006
                             (clojure.core/transient [])]
                            (if
                             (clojure.core/and (.hasNext !xs__counter))
                             (recur
                              (clojure.core/conj!
                               return__271006
                               (clojure.core/let
                                [CATA_RESULT__10883__auto__
                                 (CATA__FN__271005
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
                              return__271006))))
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
                 (state__272508))
                (state__272508)))
              (state__272508)))
            (state__272508
             []
             (if
              (clojure.core/= (clojure.core/count input__270931) 4)
              (clojure.core/let
               [input__270931_nth_0__
                (clojure.core/nth input__270931 0)
                input__270931_nth_1__
                (clojure.core/nth input__270931 1)
                input__270931_nth_2__
                (clojure.core/nth input__270931 2)]
               (clojure.core/letfn
                [(state__272510
                  []
                  (clojure.core/let
                   [input__270931_nth_3__
                    (clojure.core/nth input__270931 3)]
                   (clojure.core/case
                    input__270931_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (if
                     (clojure.core/vector? input__270931_nth_1__)
                     (clojure.core/letfn
                      [(state__272517
                        []
                        (clojure.core/case
                         input__270931_nth_1__
                         ([])
                         (clojure.core/let
                          [?next input__270931_nth_2__]
                          (clojure.core/let
                           [?env input__270931_nth_3__]
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
                         (state__272518)))
                       (state__272518
                        []
                        (clojure.core/loop
                         [search_space__272519
                          (meander.runtime.zeta/epsilon-partitions
                           2
                           input__270931_nth_1__)]
                         (if
                          (clojure.core/seq search_space__272519)
                          (clojure.core/let
                           [input__270931_nth_1___parts__
                            (clojure.core/first search_space__272519)
                            result__272520
                            (clojure.core/let
                             [input__270931_nth_1___l__
                              (clojure.core/nth
                               input__270931_nth_1___parts__
                               0)
                              input__270931_nth_1___r__
                              (clojure.core/nth
                               input__270931_nth_1___parts__
                               1)]
                             (clojure.core/letfn
                              [(state__272522
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__9744__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__270931_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__271351]
                                     (clojure.core/let
                                      [input__271351_nth_0__
                                       (clojure.core/nth
                                        input__271351
                                        0)]
                                      (clojure.core/letfn
                                       [(save__271352
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__272526
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__271351_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__271351_nth_0__)
                                        (clojure.core/let
                                         [VAL__271353
                                          (.valAt
                                           input__271351_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__271353
                                          (:group)
                                          (save__271352)
                                          (f__272526)))
                                        (f__272526)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__270931_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__270931_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__270931_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__270931_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__270931_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__270931_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__270931_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__270931_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__270931_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__271350
                                            (.valAt
                                             input__270931_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__271350
                                            (:group)
                                            (clojure.core/let
                                             [?group
                                              input__270931_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__270931_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__270931_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__270931_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__10883__auto__
                                                     (CATA__FN__271005
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__10883__auto__
                                                         (CATA__FN__271005
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
                                                         (CATA__FN__271005
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?group
                                                           (clojure.core/let
                                                            [CATA_RESULT__10883__auto__
                                                             (CATA__FN__271005
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
                                            (state__272523)))
                                          (state__272523))))
                                       (state__272523)))))]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    ret__9744__auto__)
                                   (state__272523)
                                   ret__9744__auto__))))
                               (state__272523
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__9744__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__270931_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__271362]
                                     (clojure.core/let
                                      [input__271362_nth_0__
                                       (clojure.core/nth
                                        input__271362
                                        0)]
                                      (clojure.core/letfn
                                       [(save__271363
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__272528
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__271362_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__271362_nth_0__)
                                        (clojure.core/let
                                         [VAL__271364
                                          (.valAt
                                           input__271362_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__271364
                                          (:star)
                                          (save__271363)
                                          (f__272528)))
                                        (f__272528)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__270931_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__270931_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__270931_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__270931_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__270931_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__270931_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__270931_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__270931_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__270931_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__271361
                                            (.valAt
                                             input__270931_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__271361
                                            (:star)
                                            (clojure.core/let
                                             [?star
                                              input__270931_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__270931_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__270931_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__270931_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__10883__auto__
                                                     (CATA__FN__271005
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__10883__auto__
                                                         (CATA__FN__271005
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
                                                         (CATA__FN__271005
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?star
                                                           (clojure.core/let
                                                            [CATA_RESULT__10883__auto__
                                                             (CATA__FN__271005
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
                                            (state__272524)))
                                          (state__272524))))
                                       (state__272524)))))]
                                  (if
                                   (meander.runtime.zeta/fail?
                                    ret__9744__auto__)
                                   (state__272524)
                                   ret__9744__auto__))))
                               (state__272524
                                []
                                (clojure.core/let
                                 [!xs []]
                                 (clojure.core/let
                                  [ret__9744__auto__
                                   (meander.runtime.zeta/epsilon-run-star-1
                                    input__270931_nth_1___l__
                                    [!xs]
                                    (clojure.core/fn
                                     [[!xs] input__271373]
                                     (clojure.core/let
                                      [input__271373_nth_0__
                                       (clojure.core/nth
                                        input__271373
                                        0)]
                                      (clojure.core/letfn
                                       [(save__271374
                                         []
                                         (meander.runtime.zeta/fail))
                                        (f__272530
                                         []
                                         (clojure.core/let
                                          [!xs
                                           (clojure.core/conj
                                            !xs
                                            input__271373_nth_0__)]
                                          [!xs]))]
                                       (if
                                        (clojure.core/map?
                                         input__271373_nth_0__)
                                        (clojure.core/let
                                         [VAL__271375
                                          (.valAt
                                           input__271373_nth_0__
                                           :tag)]
                                         (clojure.core/case
                                          VAL__271375
                                          (:reference)
                                          (save__271374)
                                          (f__272530)))
                                        (f__272530)))))
                                    (clojure.core/fn
                                     [[!xs]]
                                     (clojure.core/let
                                      [input__270931_nth_1___r___l__
                                       (clojure.core/subvec
                                        input__270931_nth_1___r__
                                        0
                                        (clojure.core/min
                                         (clojure.core/count
                                          input__270931_nth_1___r__)
                                         1))]
                                      (if
                                       (clojure.core/=
                                        (clojure.core/count
                                         input__270931_nth_1___r___l__)
                                        1)
                                       (clojure.core/let
                                        [input__270931_nth_1___r___r__
                                         (clojure.core/subvec
                                          input__270931_nth_1___r__
                                          1)]
                                        (clojure.core/let
                                         [input__270931_nth_1___r___l___nth_0__
                                          (clojure.core/nth
                                           input__270931_nth_1___r___l__
                                           0)]
                                         (if
                                          (clojure.core/map?
                                           input__270931_nth_1___r___l___nth_0__)
                                          (clojure.core/let
                                           [VAL__271372
                                            (.valAt
                                             input__270931_nth_1___r___l___nth_0__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__271372
                                            (:reference)
                                            (clojure.core/let
                                             [?reference
                                              input__270931_nth_1___r___l___nth_0__]
                                             (clojure.core/let
                                              [?rest
                                               input__270931_nth_1___r___r__]
                                              (clojure.core/let
                                               [?next
                                                input__270931_nth_2__]
                                               (clojure.core/let
                                                [?env
                                                 input__270931_nth_3__]
                                                (try
                                                 [(clojure.core/let
                                                   [!xs__counter
                                                    (meander.runtime.zeta/iterator
                                                     !xs)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__10883__auto__
                                                     (CATA__FN__271005
                                                      ['meander.dev.parse.zeta/make-join
                                                       (clojure.core/let
                                                        [CATA_RESULT__10883__auto__
                                                         (CATA__FN__271005
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
                                                         (CATA__FN__271005
                                                          ['meander.dev.parse.zeta/make-join
                                                           ?reference
                                                           (clojure.core/let
                                                            [CATA_RESULT__10883__auto__
                                                             (CATA__FN__271005
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
                              (state__272522)))]
                           (if
                            (meander.runtime.zeta/fail? result__272520)
                            (recur
                             (clojure.core/next search_space__272519))
                            result__272520))
                          (state__272511))))]
                      (state__272517))
                     (state__272511))
                    (state__272511))))
                 (state__272511
                  []
                  (clojure.core/case
                   input__270931_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (if
                    (clojure.core/vector? input__270931_nth_1__)
                    (clojure.core/let
                     [input__270931_nth_1___l__
                      (clojure.core/subvec
                       input__270931_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__270931_nth_1__)
                        1))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__270931_nth_1___l__)
                       1)
                      (clojure.core/let
                       [input__270931_nth_1___r__
                        (clojure.core/subvec input__270931_nth_1__ 1)]
                       (clojure.core/let
                        [input__270931_nth_1___l___nth_0__
                         (clojure.core/nth
                          input__270931_nth_1___l__
                          0)]
                        (if
                         (clojure.core/map?
                          input__270931_nth_1___l___nth_0__)
                         (clojure.core/let
                          [VAL__271384
                           (.valAt
                            input__270931_nth_1___l___nth_0__
                            :tag)]
                          (clojure.core/case
                           VAL__271384
                           (:literal)
                           (clojure.core/let
                            [VAL__271385
                             (.valAt
                              input__270931_nth_1___l___nth_0__
                              :type)]
                            (if
                             (clojure.core/let
                              [x__8640__auto__ VAL__271385]
                              (clojure.core/case
                               x__8640__auto__
                               (:string :char)
                               true
                               false))
                             (clojure.core/let
                              [VAL__271386
                               (.valAt
                                input__270931_nth_1___l___nth_0__
                                :form)]
                              (clojure.core/let
                               [!forms []]
                               (clojure.core/let
                                [!forms
                                 (clojure.core/conj
                                  !forms
                                  VAL__271386)]
                                (clojure.core/loop
                                 [i__9717__auto__
                                  0
                                  coll__272531
                                  input__270931_nth_1___r__
                                  [!forms]
                                  [!forms]]
                                 (clojure.core/let
                                  [input__271387
                                   (clojure.core/subvec
                                    coll__272531
                                    0
                                    (clojure.core/min
                                     (clojure.core/count coll__272531)
                                     1))]
                                  (if
                                   (clojure.core/=
                                    (clojure.core/count input__271387)
                                    1)
                                   (clojure.core/let
                                    [result__9718__auto__
                                     (clojure.core/let
                                      [input__271387_nth_0__
                                       (clojure.core/nth
                                        input__271387
                                        0)]
                                      (if
                                       (clojure.core/map?
                                        input__271387_nth_0__)
                                       (clojure.core/let
                                        [VAL__271388
                                         (.valAt
                                          input__271387_nth_0__
                                          :tag)]
                                        (clojure.core/case
                                         VAL__271388
                                         (:literal)
                                         (clojure.core/let
                                          [VAL__271389
                                           (.valAt
                                            input__271387_nth_0__
                                            :type)]
                                          (if
                                           (clojure.core/let
                                            [x__8640__auto__
                                             VAL__271389]
                                            (clojure.core/case
                                             x__8640__auto__
                                             (:string :char)
                                             true
                                             false))
                                           (clojure.core/let
                                            [VAL__271390
                                             (.valAt
                                              input__271387_nth_0__
                                              :form)]
                                            (clojure.core/let
                                             [!forms
                                              (clojure.core/conj
                                               !forms
                                               VAL__271390)]
                                             [!forms]))
                                           (meander.runtime.zeta/fail)))
                                         (meander.runtime.zeta/fail)))
                                       (meander.runtime.zeta/fail)))]
                                    (if
                                     (meander.runtime.zeta/fail?
                                      result__9718__auto__)
                                     (state__272512)
                                     (recur
                                      (clojure.core/inc
                                       i__9717__auto__)
                                      (clojure.core/subvec
                                       coll__272531
                                       1)
                                      result__9718__auto__)))
                                   (if
                                    (clojure.core/or
                                     (clojure.core/seq coll__272531)
                                     (clojure.core/<
                                      i__9717__auto__
                                      0))
                                    (state__272512)
                                    (if
                                     (clojure.core/map?
                                      input__270931_nth_2__)
                                     (clojure.core/let
                                      [VAL__271379
                                       (.valAt
                                        input__270931_nth_2__
                                        :tag)]
                                      (clojure.core/case
                                       VAL__271379
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
                                       (state__272512)))
                                     (state__272512)))))))))
                             (state__272512)))
                           (state__272512)))
                         (state__272512))))
                      (state__272512)))
                    (state__272512))
                   (state__272512)))
                 (state__272512
                  []
                  (clojure.core/let
                   [input__270931_nth_3__
                    (clojure.core/nth input__270931 3)]
                   (clojure.core/case
                    input__270931_nth_0__
                    (meander.dev.parse.zeta/make-cat)
                    (clojure.core/letfn
                     [(state__272532
                       []
                       (if
                        (clojure.core/vector? input__270931_nth_1__)
                        (clojure.core/let
                         [input__270931_nth_1___l__
                          (clojure.core/subvec
                           input__270931_nth_1__
                           0
                           (clojure.core/min
                            (clojure.core/count input__270931_nth_1__)
                            1))]
                         (if
                          (clojure.core/=
                           (clojure.core/count
                            input__270931_nth_1___l__)
                           1)
                          (clojure.core/let
                           [input__270931_nth_1___r__
                            (clojure.core/subvec
                             input__270931_nth_1__
                             1)]
                           (clojure.core/let
                            [input__270931_nth_1___l___nth_0__
                             (clojure.core/nth
                              input__270931_nth_1___l__
                              0)]
                            (if
                             (clojure.core/map?
                              input__270931_nth_1___l___nth_0__)
                             (clojure.core/let
                              [VAL__272405
                               (.valAt
                                input__270931_nth_1___l___nth_0__
                                :tag)]
                              (clojure.core/case
                               VAL__272405
                               (:literal)
                               (clojure.core/letfn
                                [(state__272534
                                  []
                                  (clojure.core/let
                                   [VAL__271397
                                    (.valAt
                                     input__270931_nth_1___l___nth_0__
                                     :type)]
                                   (clojure.core/case
                                    VAL__271397
                                    (:string)
                                    (clojure.core/let
                                     [?ast
                                      input__270931_nth_1___l___nth_0__]
                                     (clojure.core/let
                                      [?rest input__270931_nth_1___r__]
                                      (clojure.core/let
                                       [?next input__270931_nth_2__]
                                       (clojure.core/let
                                        [?env input__270931_nth_3__]
                                        (try
                                         [(clojure.core/let
                                           [CATA_RESULT__10883__auto__
                                            (CATA__FN__271005
                                             ['meander.dev.parse.zeta/make-join
                                              ?ast
                                              (clojure.core/let
                                               [CATA_RESULT__10883__auto__
                                                (CATA__FN__271005
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
                                    (state__272535))))
                                 (state__272535
                                  []
                                  (clojure.core/let
                                   [VAL__271407
                                    (.valAt
                                     input__270931_nth_1___l___nth_0__
                                     :form)]
                                   (clojure.core/let
                                    [!forms []]
                                    (clojure.core/let
                                     [!forms
                                      (clojure.core/conj
                                       !forms
                                       VAL__271407)]
                                     (clojure.core/loop
                                      [i__9717__auto__
                                       0
                                       coll__272536
                                       input__270931_nth_1___r__
                                       [!forms]
                                       [!forms]]
                                      (clojure.core/let
                                       [input__271408
                                        (clojure.core/subvec
                                         coll__272536
                                         0
                                         (clojure.core/min
                                          (clojure.core/count
                                           coll__272536)
                                          1))]
                                       (if
                                        (clojure.core/=
                                         (clojure.core/count
                                          input__271408)
                                         1)
                                        (clojure.core/let
                                         [result__9718__auto__
                                          (clojure.core/let
                                           [input__271408_nth_0__
                                            (clojure.core/nth
                                             input__271408
                                             0)]
                                           (if
                                            (clojure.core/map?
                                             input__271408_nth_0__)
                                            (clojure.core/let
                                             [VAL__271409
                                              (.valAt
                                               input__271408_nth_0__
                                               :tag)]
                                             (clojure.core/case
                                              VAL__271409
                                              (:literal)
                                              (clojure.core/let
                                               [VAL__271410
                                                (.valAt
                                                 input__271408_nth_0__
                                                 :form)]
                                               (clojure.core/let
                                                [!forms
                                                 (clojure.core/conj
                                                  !forms
                                                  VAL__271410)]
                                                [!forms]))
                                              (meander.runtime.zeta/fail)))
                                            (meander.runtime.zeta/fail)))]
                                         (if
                                          (meander.runtime.zeta/fail?
                                           result__9718__auto__)
                                          (state__272533)
                                          (recur
                                           (clojure.core/inc
                                            i__9717__auto__)
                                           (clojure.core/subvec
                                            coll__272536
                                            1)
                                           result__9718__auto__)))
                                        (if
                                         (clojure.core/or
                                          (clojure.core/seq
                                           coll__272536)
                                          (clojure.core/<
                                           i__9717__auto__
                                           0))
                                         (state__272533)
                                         (if
                                          (clojure.core/map?
                                           input__270931_nth_2__)
                                          (clojure.core/let
                                           [VAL__271400
                                            (.valAt
                                             input__270931_nth_2__
                                             :tag)]
                                           (clojure.core/case
                                            VAL__271400
                                            (:empty)
                                            (if
                                             (clojure.core/map?
                                              input__270931_nth_3__)
                                             (clojure.core/let
                                              [VAL__271401
                                               (.valAt
                                                input__270931_nth_3__
                                                :context)]
                                              (clojure.core/let
                                               [?context VAL__271401]
                                               (clojure.core/let
                                                [?env
                                                 input__270931_nth_3__]
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
                                             (state__272533))
                                            (state__272533)))
                                          (state__272533))))))))))]
                                (state__272534))
                               (state__272533)))
                             (state__272533))))
                          (state__272533)))
                        (state__272533)))
                      (state__272533
                       []
                       (clojure.core/let
                        [?sequence input__270931_nth_1__]
                        (clojure.core/let
                         [?next input__270931_nth_2__]
                         (if
                          (clojure.core/map? input__270931_nth_3__)
                          (clojure.core/let
                           [VAL__271414
                            (.valAt input__270931_nth_3__ :context)]
                           (clojure.core/case
                            VAL__271414
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
                            (state__272513)))
                          (state__272513)))))]
                     (state__272532))
                    (state__272513))))
                 (state__272513
                  []
                  (clojure.core/case
                   input__270931_nth_0__
                   (meander.dev.parse.zeta/make-cat)
                   (clojure.core/let
                    [?sequence input__270931_nth_1__]
                    (clojure.core/let
                     [?next input__270931_nth_2__]
                     (try
                      [{:tag :cat, :sequence ?sequence, :next ?next}]
                      (catch
                       java.lang.Exception
                       e__11823__auto__
                       (if
                        (meander.runtime.zeta/fail? e__11823__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__11823__auto__))))))
                   (state__272514)))
                 (state__272514
                  []
                  (clojure.core/let
                   [input__270931_nth_3__
                    (clojure.core/nth input__270931 3)]
                   (clojure.core/case
                    input__270931_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (if
                     (clojure.core/map? input__270931_nth_1__)
                     (clojure.core/let
                      [VAL__272403 (.valAt input__270931_nth_1__ :tag)]
                      (clojure.core/case
                       VAL__272403
                       (:cat)
                       (clojure.core/let
                        [VAL__271420
                         (.valAt input__270931_nth_1__ :sequence)]
                        (clojure.core/let
                         [?sequence VAL__271420]
                         (clojure.core/let
                          [VAL__271421
                           (.valAt input__270931_nth_1__ :next)]
                          (if
                           (clojure.core/map? VAL__271421)
                           (clojure.core/let
                            [VAL__271422 (.valAt VAL__271421 :tag)]
                            (clojure.core/case
                             VAL__271422
                             (:empty)
                             (clojure.core/let
                              [?right input__270931_nth_2__]
                              (clojure.core/let
                               [?env input__270931_nth_3__]
                               (try
                                [(clojure.core/let
                                  [CATA_RESULT__10883__auto__
                                   (CATA__FN__271005
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
                             (state__272515)))
                           (state__272515)))))
                       (:literal)
                       (clojure.core/let
                        [VAL__271426
                         (.valAt input__270931_nth_1__ :type)]
                        (clojure.core/case
                         VAL__271426
                         (:string)
                         (clojure.core/let
                          [VAL__271427
                           (.valAt input__270931_nth_1__ :form)]
                          (clojure.core/let
                           [?form-1 VAL__271427]
                           (if
                            (clojure.core/map? input__270931_nth_2__)
                            (clojure.core/let
                             [VAL__271428
                              (.valAt input__270931_nth_2__ :tag)]
                             (clojure.core/case
                              VAL__271428
                              (:string-join)
                              (clojure.core/let
                               [VAL__271429
                                (.valAt input__270931_nth_2__ :left)]
                               (if
                                (clojure.core/map? VAL__271429)
                                (clojure.core/let
                                 [VAL__271430
                                  (.valAt VAL__271429 :tag)]
                                 (clojure.core/case
                                  VAL__271430
                                  (:literal)
                                  (clojure.core/let
                                   [VAL__271431
                                    (.valAt VAL__271429 :type)]
                                   (clojure.core/case
                                    VAL__271431
                                    (:string)
                                    (clojure.core/let
                                     [VAL__271432
                                      (.valAt VAL__271429 :form)]
                                     (clojure.core/let
                                      [?form-2 VAL__271432]
                                      (clojure.core/let
                                       [VAL__271433
                                        (.valAt
                                         input__270931_nth_2__
                                         :right)]
                                       (clojure.core/let
                                        [?right VAL__271433]
                                        (if
                                         (clojure.core/map?
                                          input__270931_nth_3__)
                                         (clojure.core/let
                                          [VAL__271434
                                           (.valAt
                                            input__270931_nth_3__
                                            :context)]
                                          (clojure.core/case
                                           VAL__271434
                                           (:string)
                                           (clojure.core/let
                                            [?env
                                             input__270931_nth_3__]
                                            (try
                                             [(clojure.core/let
                                               [CATA_RESULT__10883__auto__
                                                (CATA__FN__271005
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
                                           (state__272515)))
                                         (state__272515))))))
                                    (state__272515)))
                                  (state__272515)))
                                (state__272515)))
                              (state__272515)))
                            (state__272515))))
                         (state__272515)))
                       (state__272515)))
                     (state__272515))
                    (state__272515))))
                 (state__272515
                  []
                  (clojure.core/case
                   input__270931_nth_0__
                   (meander.dev.parse.zeta/make-join)
                   (if
                    (clojure.core/map? input__270931_nth_1__)
                    (clojure.core/let
                     [VAL__272404 (.valAt input__270931_nth_1__ :tag)]
                     (clojure.core/case
                      VAL__272404
                      (:cat)
                      (clojure.core/let
                       [?ast input__270931_nth_1__]
                       (if
                        (clojure.core/map? input__270931_nth_2__)
                        (clojure.core/let
                         [VAL__271438
                          (.valAt input__270931_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__271438
                          (:cat)
                          (clojure.core/let
                           [VAL__271439
                            (.valAt input__270931_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__271439]
                            (clojure.core/let
                             [VAL__271440
                              (.valAt input__270931_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__271440]
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
                          (state__272516)))
                        (state__272516)))
                      (:string-cat)
                      (clojure.core/let
                       [?ast input__270931_nth_1__]
                       (if
                        (clojure.core/map? input__270931_nth_2__)
                        (clojure.core/let
                         [VAL__271444
                          (.valAt input__270931_nth_2__ :tag)]
                         (clojure.core/case
                          VAL__271444
                          (:string-cat)
                          (clojure.core/let
                           [VAL__271445
                            (.valAt input__270931_nth_2__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__271445]
                            (clojure.core/let
                             [VAL__271446
                              (.valAt input__270931_nth_2__ :next)]
                             (clojure.core/let
                              [?next VAL__271446]
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
                          (state__272516)))
                        (state__272516)))
                      (state__272516)))
                    (state__272516))
                   (state__272516)))
                 (state__272516
                  []
                  (clojure.core/let
                   [input__270931_nth_3__
                    (clojure.core/nth input__270931 3)]
                   (clojure.core/case
                    input__270931_nth_0__
                    (meander.dev.parse.zeta/make-join)
                    (clojure.core/letfn
                     [(state__272537
                       []
                       (if
                        (clojure.core/map? input__270931_nth_1__)
                        (clojure.core/let
                         [VAL__272408
                          (.valAt input__270931_nth_1__ :next)
                          VAL__272407
                          (.valAt input__270931_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__272407
                          (:string-cat)
                          (clojure.core/let
                           [VAL__271450
                            (.valAt input__270931_nth_1__ :sequence)]
                           (clojure.core/let
                            [?sequence VAL__271450]
                            (if
                             (clojure.core/map? VAL__272408)
                             (clojure.core/let
                              [VAL__271452 (.valAt VAL__272408 :tag)]
                              (clojure.core/case
                               VAL__271452
                               (:empty)
                               (clojure.core/let
                                [?right input__270931_nth_2__]
                                (clojure.core/let
                                 [?env input__270931_nth_3__]
                                 (try
                                  [(clojure.core/let
                                    [CATA_RESULT__10883__auto__
                                     (CATA__FN__271005
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
                               (state__272538)))
                             (state__272538))))
                          (:string-star)
                          (clojure.core/let
                           [VAL__271456
                            (.valAt input__270931_nth_1__ :pattern)]
                           (clojure.core/let
                            [?pattern VAL__271456]
                            (if
                             (clojure.core/map? VAL__272408)
                             (clojure.core/let
                              [VAL__271458 (.valAt VAL__272408 :tag)]
                              (clojure.core/case
                               VAL__271458
                               (:empty)
                               (clojure.core/let
                                [?right input__270931_nth_2__]
                                (if
                                 (clojure.core/map?
                                  input__270931_nth_3__)
                                 (clojure.core/let
                                  [VAL__271459
                                   (.valAt
                                    input__270931_nth_3__
                                    :context)]
                                  (clojure.core/case
                                   VAL__271459
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
                                   (state__272538)))
                                 (state__272538)))
                               (state__272538)))
                             (state__272538))))
                          (:string-join)
                          (clojure.core/let
                           [VAL__271463
                            (.valAt input__270931_nth_1__ :left)]
                           (clojure.core/let
                            [?left VAL__271463]
                            (clojure.core/let
                             [VAL__271464
                              (.valAt input__270931_nth_1__ :right)]
                             (clojure.core/let
                              [?right-1 VAL__271464]
                              (clojure.core/let
                               [?right-2 input__270931_nth_2__]
                               (if
                                (clojure.core/map?
                                 input__270931_nth_3__)
                                (clojure.core/let
                                 [VAL__271465
                                  (.valAt
                                   input__270931_nth_3__
                                   :context)]
                                 (clojure.core/case
                                  VAL__271465
                                  (:string)
                                  (clojure.core/let
                                   [?env input__270931_nth_3__]
                                   (try
                                    [{:tag :string-join,
                                      :left ?left,
                                      :right
                                      (clojure.core/let
                                       [CATA_RESULT__10883__auto__
                                        (CATA__FN__271005
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
                                  (state__272538)))
                                (state__272538)))))))
                          (state__272538)))
                        (state__272538)))
                      (state__272538
                       []
                       (clojure.core/let
                        [?left input__270931_nth_1__]
                        (if
                         (clojure.core/map? input__270931_nth_2__)
                         (clojure.core/let
                          [VAL__271468
                           (.valAt input__270931_nth_2__ :tag)]
                          (clojure.core/case
                           VAL__271468
                           (:empty)
                           (clojure.core/let
                            [?env input__270931_nth_3__]
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
                           (state__272539)))
                         (state__272539))))
                      (state__272539
                       []
                       (if
                        (clojure.core/map? input__270931_nth_1__)
                        (clojure.core/let
                         [VAL__272406
                          (.valAt input__270931_nth_1__ :tag)]
                         (clojure.core/case
                          VAL__272406
                          (:empty)
                          (clojure.core/let
                           [?right input__270931_nth_2__]
                           (clojure.core/let
                            [?env input__270931_nth_3__]
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
                           [VAL__271475
                            (.valAt input__270931_nth_1__ :next)]
                           (if
                            (clojure.core/map? VAL__271475)
                            (clojure.core/let
                             [VAL__271476 (.valAt VAL__271475 :tag)]
                             (clojure.core/case
                              VAL__271476
                              (:empty)
                              (clojure.core/let
                               [?left input__270931_nth_1__]
                               (clojure.core/let
                                [?right input__270931_nth_2__]
                                (clojure.core/let
                                 [?env input__270931_nth_3__]
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
                              (state__272540)))
                            (state__272540)))
                          (state__272540)))
                        (state__272540)))
                      (state__272540
                       []
                       (clojure.core/let
                        [?left input__270931_nth_1__]
                        (clojure.core/let
                         [?right input__270931_nth_2__]
                         (clojure.core/letfn
                          [(state__272541
                            []
                            (if
                             (clojure.core/map? input__270931_nth_3__)
                             (clojure.core/let
                              [VAL__271479
                               (.valAt input__270931_nth_3__ :context)]
                              (clojure.core/case
                               VAL__271479
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
                               (state__272542)))
                             (state__272542)))
                           (state__272542
                            []
                            (clojure.core/let
                             [?env input__270931_nth_3__]
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
                          (state__272541)))))]
                     (state__272537))
                    (state__272509))))]
                (state__272510)))
              (state__272509)))
            (state__272509
             []
             (if
              (clojure.core/= (clojure.core/count input__270931) 3)
              (clojure.core/let
               [input__270931_nth_0__
                (clojure.core/nth input__270931 0)
                input__270931_nth_1__
                (clojure.core/nth input__270931 1)
                input__270931_nth_2__
                (clojure.core/nth input__270931 2)]
               (clojure.core/case
                input__270931_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (if
                 (clojure.core/map? input__270931_nth_1__)
                 (clojure.core/let
                  [VAL__271484
                   (.valAt input__270931_nth_1__ :meander.zeta/as)]
                  (clojure.core/let
                   [?pattern VAL__271484]
                   (clojure.core/let
                    [X__271486
                     ((clojure.core/fn
                       [m__8647__auto__]
                       (clojure.core/dissoc
                        m__8647__auto__
                        :meander.zeta/as))
                      input__270931_nth_1__)]
                    (clojure.core/let
                     [?rest X__271486]
                     (clojure.core/letfn
                      [(save__271487 [] (state__272419))
                       (f__272543
                        []
                        (clojure.core/let
                         [?env input__270931_nth_2__]
                         (try
                          [{:tag :as,
                            :pattern
                            (clojure.core/let
                             [CATA_RESULT__10883__auto__
                              (CATA__FN__271005 [?pattern ?env])]
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
                              (CATA__FN__271005 [?rest ?env])]
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
                       (clojure.core/= ?rest input__270931_nth_1__)
                       (save__271487)
                       (f__272543)))))))
                 (state__272419))
                (state__272419)))
              (state__272419)))]
           (state__272507))
          (state__272419)))
        (state__272419
         []
         (clojure.core/letfn
          [(def__271490
            [arg__271523 ?ns]
            (clojure.core/letfn
             [(state__272544
               []
               (clojure.core/let
                [x__271524 "meander.zeta"]
                (if
                 (clojure.core/= ?ns x__271524)
                 (clojure.core/let [?env arg__271523] [?env ?ns])
                 (state__272545))))
              (state__272545
               []
               (if
                (clojure.core/map? arg__271523)
                (clojure.core/let
                 [VAL__271525 (.valAt arg__271523 :aliases)]
                 (if
                  (clojure.core/map? VAL__271525)
                  (clojure.core/let
                   [X__271527 (clojure.core/set VAL__271525)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__271527))
                    (clojure.core/loop
                     [search_space__272546
                      (clojure.core/seq X__271527)]
                     (if
                      (clojure.core/seq search_space__272546)
                      (clojure.core/let
                       [elem__271528
                        (clojure.core/first search_space__272546)
                        result__272547
                        (clojure.core/let
                         [elem__271528_nth_0__
                          (clojure.core/nth elem__271528 0)
                          elem__271528_nth_1__
                          (clojure.core/nth elem__271528 1)]
                         (if
                          (clojure.core/symbol? elem__271528_nth_0__)
                          (clojure.core/let
                           [X__271530
                            (clojure.core/name elem__271528_nth_0__)]
                           (if
                            (clojure.core/= ?ns X__271530)
                            (if
                             (clojure.core/symbol?
                              elem__271528_nth_1__)
                             (clojure.core/let
                              [X__271532
                               (clojure.core/name
                                elem__271528_nth_1__)]
                              (clojure.core/case
                               X__271532
                               ("meander.zeta")
                               (clojure.core/let
                                [?env arg__271523]
                                [?env ?ns])
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__272547)
                        (recur
                         (clojure.core/next search_space__272546))
                        result__272547))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__272544)))]
          (if
           (clojure.core/vector? input__270931)
           (if
            (clojure.core/= (clojure.core/count input__270931) 3)
            (clojure.core/let
             [input__270931_nth_0__
              (clojure.core/nth input__270931 0)
              input__270931_nth_1__
              (clojure.core/nth input__270931 1)
              input__270931_nth_2__
              (clojure.core/nth input__270931 2)]
             (clojure.core/case
              input__270931_nth_0__
              (meander.dev.parse.zeta/parse-entries)
              (if
               (clojure.core/map? input__270931_nth_1__)
               (clojure.core/let
                [X__271495 (clojure.core/set input__270931_nth_1__)]
                (if
                 (clojure.core/<= 1 (clojure.core/count X__271495))
                 (clojure.core/loop
                  [search_space__272549 (clojure.core/seq X__271495)]
                  (if
                   (clojure.core/seq search_space__272549)
                   (clojure.core/let
                    [elem__271496
                     (clojure.core/first search_space__272549)
                     result__272550
                     (clojure.core/let
                      [elem__271496_nth_0__
                       (clojure.core/nth elem__271496 0)
                       elem__271496_nth_1__
                       (clojure.core/nth elem__271496 1)]
                      (clojure.core/let
                       [*m__270962 elem__271496_nth_0__]
                       (if
                        (clojure.core/symbol? elem__271496_nth_0__)
                        (clojure.core/let
                         [X__271498
                          (clojure.core/namespace
                           elem__271496_nth_0__)]
                         (clojure.core/let
                          [?ns X__271498]
                          (clojure.core/let
                           [X__271500
                            (clojure.core/name elem__271496_nth_0__)]
                           (if
                            (clojure.core/string? X__271500)
                            (if
                             (clojure.core/re-matches #"&.*" X__271500)
                             (clojure.core/let
                              [?pattern elem__271496_nth_1__]
                              (clojure.core/let
                               [X__271502
                                ((clojure.core/fn
                                  [m__8647__auto__]
                                  (clojure.core/dissoc
                                   m__8647__auto__
                                   *m__270962))
                                 input__270931_nth_1__)]
                               (clojure.core/let
                                [?rest X__271502]
                                (clojure.core/let
                                 [x__9580__auto__
                                  (def__271490
                                   input__270931_nth_2__
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
                                        (CATA__FN__271005
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
                                        (CATA__FN__271005
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
                     (meander.runtime.zeta/fail? result__272550)
                     (recur (clojure.core/next search_space__272549))
                     result__272550))
                   (state__272420)))
                 (state__272420)))
               (state__272420))
              (state__272420)))
            (state__272420))
           (state__272420))))
        (state__272420
         []
         (if
          (clojure.core/vector? input__270931)
          (clojure.core/letfn
           [(state__272552
             []
             (if
              (clojure.core/= (clojure.core/count input__270931) 3)
              (clojure.core/let
               [input__270931_nth_0__
                (clojure.core/nth input__270931 0)
                input__270931_nth_1__
                (clojure.core/nth input__270931 1)
                input__270931_nth_2__
                (clojure.core/nth input__270931 2)]
               (clojure.core/case
                input__270931_nth_0__
                (meander.dev.parse.zeta/parse-entries)
                (clojure.core/letfn
                 [(state__272554
                   []
                   (if
                    (clojure.core/map? input__270931_nth_1__)
                    (clojure.core/let
                     [X__271546
                      (clojure.core/set input__270931_nth_1__)]
                     (if
                      (clojure.core/<=
                       1
                       (clojure.core/count X__271546))
                      (clojure.core/loop
                       [search_space__272556
                        (clojure.core/seq X__271546)]
                       (if
                        (clojure.core/seq search_space__272556)
                        (clojure.core/let
                         [elem__271547
                          (clojure.core/first search_space__272556)
                          result__272557
                          (clojure.core/let
                           [elem__271547_nth_0__
                            (clojure.core/nth elem__271547 0)
                            elem__271547_nth_1__
                            (clojure.core/nth elem__271547 1)]
                           (clojure.core/let
                            [?key-pattern elem__271547_nth_0__]
                            (clojure.core/let
                             [?val-pattern elem__271547_nth_1__]
                             (clojure.core/let
                              [X__271549
                               ((clojure.core/fn
                                 [m__8647__auto__]
                                 (clojure.core/dissoc
                                  m__8647__auto__
                                  ?key-pattern))
                                input__270931_nth_1__)]
                              (clojure.core/let
                               [?rest X__271549]
                               (clojure.core/let
                                [?env input__270931_nth_2__]
                                (try
                                 [{:tag :entry,
                                   :key-pattern
                                   (clojure.core/let
                                    [CATA_RESULT__10883__auto__
                                     (CATA__FN__271005
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
                                     (CATA__FN__271005
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
                                     (CATA__FN__271005
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
                          (meander.runtime.zeta/fail? result__272557)
                          (recur
                           (clojure.core/next search_space__272556))
                          result__272557))
                        (state__272555)))
                      (state__272555)))
                    (state__272555)))
                  (state__272555
                   []
                   (if
                    (clojure.core/map? input__270931_nth_1__)
                    (clojure.core/let
                     [?env input__270931_nth_2__]
                     (try
                      [{:tag :some-map}]
                      (catch
                       java.lang.Exception
                       e__11823__auto__
                       (if
                        (meander.runtime.zeta/fail? e__11823__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__11823__auto__)))))
                    (state__272553)))]
                 (state__272554))
                (meander.dev.parse.zeta/parse-with-bindings)
                (clojure.core/letfn
                 [(state__272559
                   []
                   (if
                    (clojure.core/vector? input__270931_nth_1__)
                    (clojure.core/case
                     input__270931_nth_1__
                     ([])
                     (clojure.core/let
                      [?env input__270931_nth_2__]
                      (try
                       [[]]
                       (catch
                        java.lang.Exception
                        e__11823__auto__
                        (if
                         (meander.runtime.zeta/fail? e__11823__auto__)
                         (meander.runtime.zeta/fail)
                         (throw e__11823__auto__)))))
                     (state__272560))
                    (state__272560)))
                  (state__272560
                   []
                   (if
                    (clojure.core/vector? input__270931_nth_1__)
                    (if
                     (clojure.core/=
                      (clojure.core/count input__270931_nth_1__)
                      1)
                     (clojure.core/let
                      [?env input__270931_nth_2__]
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
                     (state__272561))
                    (state__272561)))
                  (state__272561
                   []
                   (if
                    (clojure.core/vector? input__270931_nth_1__)
                    (clojure.core/let
                     [input__270931_nth_1___l__
                      (clojure.core/subvec
                       input__270931_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__270931_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__270931_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__270931_nth_1___r__
                        (clojure.core/subvec input__270931_nth_1__ 2)]
                       (clojure.core/let
                        [input__270931_nth_1___l___nth_0__
                         (clojure.core/nth input__270931_nth_1___l__ 0)
                         input__270931_nth_1___l___nth_1__
                         (clojure.core/nth
                          input__270931_nth_1___l__
                          1)]
                        (if
                         (clojure.core/symbol?
                          input__270931_nth_1___l___nth_0__)
                         (clojure.core/let
                          [X__271563
                           (clojure.core/namespace
                            input__270931_nth_1___l___nth_0__)]
                          (clojure.core/let
                           [X__271565
                            (clojure.core/name
                             input__270931_nth_1___l___nth_0__)]
                           (if
                            (clojure.core/string? X__271565)
                            (if
                             (clojure.core/re-matches #"%.+" X__271565)
                             (clojure.core/let
                              [?symbol
                               input__270931_nth_1___l___nth_0__]
                              (clojure.core/let
                               [?pattern
                                input__270931_nth_1___l___nth_1__]
                               (clojure.core/let
                                [?rest input__270931_nth_1___r__]
                                (clojure.core/let
                                 [?env input__270931_nth_2__]
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
                                         (CATA__FN__271005
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
                                       (CATA__FN__271005
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
                             (state__272562))
                            (state__272562))))
                         (state__272562))))
                      (state__272562)))
                    (state__272562)))
                  (state__272562
                   []
                   (if
                    (clojure.core/vector? input__270931_nth_1__)
                    (clojure.core/let
                     [input__270931_nth_1___l__
                      (clojure.core/subvec
                       input__270931_nth_1__
                       0
                       (clojure.core/min
                        (clojure.core/count input__270931_nth_1__)
                        2))]
                     (if
                      (clojure.core/=
                       (clojure.core/count input__270931_nth_1___l__)
                       2)
                      (clojure.core/let
                       [input__270931_nth_1___r__
                        (clojure.core/subvec input__270931_nth_1__ 2)]
                       (clojure.core/let
                        [input__270931_nth_1___l___nth_0__
                         (clojure.core/nth input__270931_nth_1___l__ 0)
                         input__270931_nth_1___l___nth_1__
                         (clojure.core/nth
                          input__270931_nth_1___l__
                          1)]
                        (clojure.core/let
                         [?x input__270931_nth_1___l___nth_0__]
                         (clojure.core/let
                          [?pattern input__270931_nth_1___l___nth_1__]
                          (clojure.core/let
                           [?rest input__270931_nth_1___r__]
                           (clojure.core/let
                            [?env input__270931_nth_2__]
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
                      (state__272553)))
                    (state__272553)))]
                 (state__272559))
                (state__272553)))
              (state__272553)))
            (state__272553
             []
             (if
              (clojure.core/= (clojure.core/count input__270931) 2)
              (clojure.core/let
               [input__270931_nth_0__
                (clojure.core/nth input__270931 0)
                input__270931_nth_1__
                (clojure.core/nth input__270931 1)]
               (if
                (clojure.core/vector? input__270931_nth_0__)
                (clojure.core/let
                 [?sequence input__270931_nth_0__]
                 (clojure.core/let
                  [?form input__270931_nth_0__]
                  (clojure.core/let
                   [?env input__270931_nth_1__]
                   (try
                    [{:tag :vector,
                      :next
                      (clojure.core/let
                       [CATA_RESULT__10883__auto__
                        (CATA__FN__271005
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
                (state__272421)))
              (state__272421)))]
           (state__272552))
          (state__272421)))
        (state__272421
         []
         (clojure.core/letfn
          [(def__271575
            [arg__271598 ?__270932]
            (clojure.core/letfn
             [(state__272563
               []
               (clojure.core/let
                [x__271599 "clojure.core"]
                (if
                 (clojure.core/= ?__270932 x__271599)
                 [?__270932]
                 (state__272564))))
              (state__272564
               []
               (if
                (clojure.core/map? arg__271598)
                (clojure.core/let
                 [VAL__271600 (.valAt arg__271598 :aliases)]
                 (if
                  (clojure.core/map? VAL__271600)
                  (clojure.core/let
                   [X__271602 (clojure.core/set VAL__271600)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__271602))
                    (clojure.core/loop
                     [search_space__272565
                      (clojure.core/seq X__271602)]
                     (if
                      (clojure.core/seq search_space__272565)
                      (clojure.core/let
                       [elem__271603
                        (clojure.core/first search_space__272565)
                        result__272566
                        (clojure.core/let
                         [elem__271603_nth_0__
                          (clojure.core/nth elem__271603 0)
                          elem__271603_nth_1__
                          (clojure.core/nth elem__271603 1)]
                         (if
                          (clojure.core/symbol? elem__271603_nth_0__)
                          (clojure.core/let
                           [X__271605
                            (clojure.core/name elem__271603_nth_0__)]
                           (if
                            (clojure.core/= ?__270932 X__271605)
                            (if
                             (clojure.core/symbol?
                              elem__271603_nth_1__)
                             (clojure.core/let
                              [X__271607
                               (clojure.core/name
                                elem__271603_nth_1__)]
                              (clojure.core/case
                               X__271607
                               ("clojure.core")
                               [?__270932]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__272566)
                        (recur
                         (clojure.core/next search_space__272565))
                        result__272566))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__272563)))]
          (if
           (clojure.core/vector? input__270931)
           (if
            (clojure.core/= (clojure.core/count input__270931) 2)
            (clojure.core/let
             [input__270931_nth_0__
              (clojure.core/nth input__270931 0)
              input__270931_nth_1__
              (clojure.core/nth input__270931 1)]
             (if
              (clojure.core/seq? input__270931_nth_0__)
              (clojure.core/let
               [input__270931_nth_0___l__
                (clojure.core/take 1 input__270931_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__270931_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__270931_nth_0___r__
                  (clojure.core/drop 1 input__270931_nth_0__)]
                 (clojure.core/let
                  [input__270931_nth_0___l___nth_0__
                   (clojure.core/nth input__270931_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__270931_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__271585
                     (clojure.core/namespace
                      input__270931_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__270932 X__271585]
                     (clojure.core/let
                      [X__271587
                       (clojure.core/name
                        input__270931_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__271587
                       ("unquote")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__271575 input__270931_nth_1__ ?__270932)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__272422)
                         (clojure.core/let
                          [[?__270932] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__270931)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__270931)
                             2)
                            (clojure.core/let
                             [input__270931_nth_0__
                              (clojure.core/nth input__270931 0)
                              input__270931_nth_1__
                              (clojure.core/nth input__270931 1)]
                             (if
                              (clojure.core/seq? input__270931_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__270931_nth_0__)
                                2)
                               (clojure.core/let
                                [input__270931_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?x input__270931_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__270931_nth_0__]
                                  (clojure.core/let
                                   [?env input__270931_nth_1__]
                                   (try
                                    [{:tag :host-expression,
                                      :expression ?x,
                                      :form ?form}]
                                    (catch
                                     java.lang.Exception
                                     e__11823__auto__
                                     (if
                                      (meander.runtime.zeta/fail?
                                       e__11823__auto__)
                                      (meander.runtime.zeta/fail)
                                      (throw e__11823__auto__))))))))
                               (state__272422))
                              (state__272422)))
                            (state__272422))
                           (state__272422)))))
                       (state__272422)))))
                   (state__272422))))
                (state__272422)))
              (state__272422)))
            (state__272422))
           (state__272422))))
        (state__272422
         []
         (clojure.core/letfn
          [(def__271609
            [arg__271632 ?__270933]
            (clojure.core/letfn
             [(state__272568
               []
               (clojure.core/let
                [x__271633 "meander.zeta"]
                (if
                 (clojure.core/= ?__270933 x__271633)
                 [?__270933]
                 (state__272569))))
              (state__272569
               []
               (if
                (clojure.core/map? arg__271632)
                (clojure.core/let
                 [VAL__271634 (.valAt arg__271632 :aliases)]
                 (if
                  (clojure.core/map? VAL__271634)
                  (clojure.core/let
                   [X__271636 (clojure.core/set VAL__271634)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__271636))
                    (clojure.core/loop
                     [search_space__272570
                      (clojure.core/seq X__271636)]
                     (if
                      (clojure.core/seq search_space__272570)
                      (clojure.core/let
                       [elem__271637
                        (clojure.core/first search_space__272570)
                        result__272571
                        (clojure.core/let
                         [elem__271637_nth_0__
                          (clojure.core/nth elem__271637 0)
                          elem__271637_nth_1__
                          (clojure.core/nth elem__271637 1)]
                         (if
                          (clojure.core/symbol? elem__271637_nth_0__)
                          (clojure.core/let
                           [X__271639
                            (clojure.core/name elem__271637_nth_0__)]
                           (if
                            (clojure.core/= ?__270933 X__271639)
                            (if
                             (clojure.core/symbol?
                              elem__271637_nth_1__)
                             (clojure.core/let
                              [X__271641
                               (clojure.core/name
                                elem__271637_nth_1__)]
                              (clojure.core/case
                               X__271641
                               ("meander.zeta")
                               [?__270933]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__272571)
                        (recur
                         (clojure.core/next search_space__272570))
                        result__272571))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__272568)))]
          (if
           (clojure.core/vector? input__270931)
           (if
            (clojure.core/= (clojure.core/count input__270931) 2)
            (clojure.core/let
             [input__270931_nth_0__
              (clojure.core/nth input__270931 0)
              input__270931_nth_1__
              (clojure.core/nth input__270931 1)]
             (if
              (clojure.core/seq? input__270931_nth_0__)
              (clojure.core/let
               [input__270931_nth_0___l__
                (clojure.core/take 1 input__270931_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__270931_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__270931_nth_0___r__
                  (clojure.core/drop 1 input__270931_nth_0__)]
                 (clojure.core/let
                  [input__270931_nth_0___l___nth_0__
                   (clojure.core/nth input__270931_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__270931_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__271619
                     (clojure.core/namespace
                      input__270931_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__270933 X__271619]
                     (clojure.core/let
                      [X__271621
                       (clojure.core/name
                        input__270931_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__271621
                       ("*")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__271609 input__270931_nth_1__ ?__270933)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__272423)
                         (clojure.core/let
                          [[?__270933] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__270931)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__270931)
                             2)
                            (clojure.core/let
                             [input__270931_nth_0__
                              (clojure.core/nth input__270931 0)
                              input__270931_nth_1__
                              (clojure.core/nth input__270931 1)]
                             (if
                              (clojure.core/seq? input__270931_nth_0__)
                              (clojure.core/let
                               [input__270931_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__270931_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__270931_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__270931_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__270931_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__270931_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__270931_nth_0__]
                                   (clojure.core/let
                                    [?env input__270931_nth_1__]
                                    (try
                                     [{:tag :star,
                                       :greedy? true,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__271005
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
                                (state__272423)))
                              (state__272423)))
                            (state__272423))
                           (state__272423)))))
                       (state__272423)))))
                   (state__272423))))
                (state__272423)))
              (state__272423)))
            (state__272423))
           (state__272423))))
        (state__272423
         []
         (clojure.core/letfn
          [(def__271643
            [arg__271666 ?__270934]
            (clojure.core/letfn
             [(state__272573
               []
               (clojure.core/let
                [x__271667 "meander.zeta"]
                (if
                 (clojure.core/= ?__270934 x__271667)
                 [?__270934]
                 (state__272574))))
              (state__272574
               []
               (if
                (clojure.core/map? arg__271666)
                (clojure.core/let
                 [VAL__271668 (.valAt arg__271666 :aliases)]
                 (if
                  (clojure.core/map? VAL__271668)
                  (clojure.core/let
                   [X__271670 (clojure.core/set VAL__271668)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__271670))
                    (clojure.core/loop
                     [search_space__272575
                      (clojure.core/seq X__271670)]
                     (if
                      (clojure.core/seq search_space__272575)
                      (clojure.core/let
                       [elem__271671
                        (clojure.core/first search_space__272575)
                        result__272576
                        (clojure.core/let
                         [elem__271671_nth_0__
                          (clojure.core/nth elem__271671 0)
                          elem__271671_nth_1__
                          (clojure.core/nth elem__271671 1)]
                         (if
                          (clojure.core/symbol? elem__271671_nth_0__)
                          (clojure.core/let
                           [X__271673
                            (clojure.core/name elem__271671_nth_0__)]
                           (if
                            (clojure.core/= ?__270934 X__271673)
                            (if
                             (clojure.core/symbol?
                              elem__271671_nth_1__)
                             (clojure.core/let
                              [X__271675
                               (clojure.core/name
                                elem__271671_nth_1__)]
                              (clojure.core/case
                               X__271675
                               ("meander.zeta")
                               [?__270934]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__272576)
                        (recur
                         (clojure.core/next search_space__272575))
                        result__272576))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__272573)))]
          (if
           (clojure.core/vector? input__270931)
           (if
            (clojure.core/= (clojure.core/count input__270931) 2)
            (clojure.core/let
             [input__270931_nth_0__
              (clojure.core/nth input__270931 0)
              input__270931_nth_1__
              (clojure.core/nth input__270931 1)]
             (if
              (clojure.core/seq? input__270931_nth_0__)
              (clojure.core/let
               [input__270931_nth_0___l__
                (clojure.core/take 1 input__270931_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__270931_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__270931_nth_0___r__
                  (clojure.core/drop 1 input__270931_nth_0__)]
                 (clojure.core/let
                  [input__270931_nth_0___l___nth_0__
                   (clojure.core/nth input__270931_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__270931_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__271653
                     (clojure.core/namespace
                      input__270931_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__270934 X__271653]
                     (clojure.core/let
                      [X__271655
                       (clojure.core/name
                        input__270931_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__271655
                       ("<>")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__271643 input__270931_nth_1__ ?__270934)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__272424)
                         (clojure.core/let
                          [[?__270934] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__270931)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__270931)
                             2)
                            (clojure.core/let
                             [input__270931_nth_0__
                              (clojure.core/nth input__270931 0)
                              input__270931_nth_1__
                              (clojure.core/nth input__270931 1)]
                             (if
                              (clojure.core/seq? input__270931_nth_0__)
                              (clojure.core/let
                               [input__270931_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__270931_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__270931_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__270931_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__270931_nth_0__)]
                                 (clojure.core/let
                                  [?patterns input__270931_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__270931_nth_0__]
                                   (clojure.core/let
                                    [?env input__270931_nth_1__]
                                    (try
                                     [{:tag :group,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__271005
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
                                (state__272424)))
                              (state__272424)))
                            (state__272424))
                           (state__272424)))))
                       (state__272424)))))
                   (state__272424))))
                (state__272424)))
              (state__272424)))
            (state__272424))
           (state__272424))))
        (state__272424
         []
         (clojure.core/letfn
          [(def__271677
            [arg__271700 ?__270935]
            (clojure.core/letfn
             [(state__272578
               []
               (clojure.core/let
                [x__271701 "meander.math.zeta"]
                (if
                 (clojure.core/= ?__270935 x__271701)
                 [?__270935]
                 (state__272579))))
              (state__272579
               []
               (if
                (clojure.core/map? arg__271700)
                (clojure.core/let
                 [VAL__271702 (.valAt arg__271700 :aliases)]
                 (if
                  (clojure.core/map? VAL__271702)
                  (clojure.core/let
                   [X__271704 (clojure.core/set VAL__271702)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__271704))
                    (clojure.core/loop
                     [search_space__272580
                      (clojure.core/seq X__271704)]
                     (if
                      (clojure.core/seq search_space__272580)
                      (clojure.core/let
                       [elem__271705
                        (clojure.core/first search_space__272580)
                        result__272581
                        (clojure.core/let
                         [elem__271705_nth_0__
                          (clojure.core/nth elem__271705 0)
                          elem__271705_nth_1__
                          (clojure.core/nth elem__271705 1)]
                         (if
                          (clojure.core/symbol? elem__271705_nth_0__)
                          (clojure.core/let
                           [X__271707
                            (clojure.core/name elem__271705_nth_0__)]
                           (if
                            (clojure.core/= ?__270935 X__271707)
                            (if
                             (clojure.core/symbol?
                              elem__271705_nth_1__)
                             (clojure.core/let
                              [X__271709
                               (clojure.core/name
                                elem__271705_nth_1__)]
                              (clojure.core/case
                               X__271709
                               ("meander.math.zeta")
                               [?__270935]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__272581)
                        (recur
                         (clojure.core/next search_space__272580))
                        result__272581))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__272578)))]
          (if
           (clojure.core/vector? input__270931)
           (if
            (clojure.core/= (clojure.core/count input__270931) 2)
            (clojure.core/let
             [input__270931_nth_0__
              (clojure.core/nth input__270931 0)
              input__270931_nth_1__
              (clojure.core/nth input__270931 1)]
             (if
              (clojure.core/seq? input__270931_nth_0__)
              (clojure.core/let
               [input__270931_nth_0___l__
                (clojure.core/take 1 input__270931_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__270931_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__270931_nth_0___r__
                  (clojure.core/drop 1 input__270931_nth_0__)]
                 (clojure.core/let
                  [input__270931_nth_0___l___nth_0__
                   (clojure.core/nth input__270931_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__270931_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__271687
                     (clojure.core/namespace
                      input__270931_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__270935 X__271687]
                     (clojure.core/let
                      [X__271689
                       (clojure.core/name
                        input__270931_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__271689
                       ("+")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__271677 input__270931_nth_1__ ?__270935)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__272425)
                         (clojure.core/let
                          [[?__270935] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__270931)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__270931)
                             2)
                            (clojure.core/let
                             [input__270931_nth_0__
                              (clojure.core/nth input__270931 0)
                              input__270931_nth_1__
                              (clojure.core/nth input__270931 1)]
                             (if
                              (clojure.core/seq? input__270931_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__270931_nth_0__)
                                3)
                               (clojure.core/let
                                [input__270931_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  1)
                                 input__270931_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?a input__270931_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?b input__270931_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__270931_nth_0__]
                                   (clojure.core/let
                                    [?env input__270931_nth_1__]
                                    (try
                                     [{:tag :meander.math.zeta/+,
                                       :left
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__271005 [?a ?env])]
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
                                         (CATA__FN__271005 [?b ?env])]
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
                               (state__272425))
                              (state__272425)))
                            (state__272425))
                           (state__272425)))))
                       (state__272425)))))
                   (state__272425))))
                (state__272425)))
              (state__272425)))
            (state__272425))
           (state__272425))))
        (state__272425
         []
         (clojure.core/letfn
          [(def__271711
            [arg__271734 ?__270936]
            (clojure.core/letfn
             [(state__272583
               []
               (clojure.core/let
                [x__271735 "meander.zeta"]
                (if
                 (clojure.core/= ?__270936 x__271735)
                 [?__270936]
                 (state__272584))))
              (state__272584
               []
               (if
                (clojure.core/map? arg__271734)
                (clojure.core/let
                 [VAL__271736 (.valAt arg__271734 :aliases)]
                 (if
                  (clojure.core/map? VAL__271736)
                  (clojure.core/let
                   [X__271738 (clojure.core/set VAL__271736)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__271738))
                    (clojure.core/loop
                     [search_space__272585
                      (clojure.core/seq X__271738)]
                     (if
                      (clojure.core/seq search_space__272585)
                      (clojure.core/let
                       [elem__271739
                        (clojure.core/first search_space__272585)
                        result__272586
                        (clojure.core/let
                         [elem__271739_nth_0__
                          (clojure.core/nth elem__271739 0)
                          elem__271739_nth_1__
                          (clojure.core/nth elem__271739 1)]
                         (if
                          (clojure.core/symbol? elem__271739_nth_0__)
                          (clojure.core/let
                           [X__271741
                            (clojure.core/name elem__271739_nth_0__)]
                           (if
                            (clojure.core/= ?__270936 X__271741)
                            (if
                             (clojure.core/symbol?
                              elem__271739_nth_1__)
                             (clojure.core/let
                              [X__271743
                               (clojure.core/name
                                elem__271739_nth_1__)]
                              (clojure.core/case
                               X__271743
                               ("meander.zeta")
                               [?__270936]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__272586)
                        (recur
                         (clojure.core/next search_space__272585))
                        result__272586))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__272583)))]
          (if
           (clojure.core/vector? input__270931)
           (if
            (clojure.core/= (clojure.core/count input__270931) 2)
            (clojure.core/let
             [input__270931_nth_0__
              (clojure.core/nth input__270931 0)
              input__270931_nth_1__
              (clojure.core/nth input__270931 1)]
             (if
              (clojure.core/seq? input__270931_nth_0__)
              (clojure.core/let
               [input__270931_nth_0___l__
                (clojure.core/take 1 input__270931_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__270931_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__270931_nth_0___r__
                  (clojure.core/drop 1 input__270931_nth_0__)]
                 (clojure.core/let
                  [input__270931_nth_0___l___nth_0__
                   (clojure.core/nth input__270931_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__270931_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__271721
                     (clojure.core/namespace
                      input__270931_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__270936 X__271721]
                     (clojure.core/let
                      [X__271723
                       (clojure.core/name
                        input__270931_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__271723
                       ("with")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__271711 input__270931_nth_1__ ?__270936)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__272426)
                         (clojure.core/let
                          [[?__270936] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__270931)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__270931)
                             2)
                            (clojure.core/let
                             [input__270931_nth_0__
                              (clojure.core/nth input__270931 0)
                              input__270931_nth_1__
                              (clojure.core/nth input__270931 1)]
                             (if
                              (clojure.core/seq? input__270931_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__270931_nth_0__)
                                3)
                               (clojure.core/let
                                [input__270931_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  1)
                                 input__270931_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?bindings
                                  input__270931_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?body input__270931_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__270931_nth_0__]
                                   (clojure.core/let
                                    [?env input__270931_nth_1__]
                                    (try
                                     [{:tag :with,
                                       :bindings
                                       {:tag :with-bindings,
                                        :bindings
                                        (clojure.core/let
                                         [CATA_RESULT__10883__auto__
                                          (CATA__FN__271005
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
                                         (CATA__FN__271005
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
                               (state__272426))
                              (state__272426)))
                            (state__272426))
                           (state__272426)))))
                       (state__272426)))))
                   (state__272426))))
                (state__272426)))
              (state__272426)))
            (state__272426))
           (state__272426))))
        (state__272426
         []
         (clojure.core/letfn
          [(def__271745
            [arg__271768 ?__270937]
            (clojure.core/letfn
             [(state__272588
               []
               (clojure.core/let
                [x__271769 "meander.zeta"]
                (if
                 (clojure.core/= ?__270937 x__271769)
                 [?__270937]
                 (state__272589))))
              (state__272589
               []
               (if
                (clojure.core/map? arg__271768)
                (clojure.core/let
                 [VAL__271770 (.valAt arg__271768 :aliases)]
                 (if
                  (clojure.core/map? VAL__271770)
                  (clojure.core/let
                   [X__271772 (clojure.core/set VAL__271770)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__271772))
                    (clojure.core/loop
                     [search_space__272590
                      (clojure.core/seq X__271772)]
                     (if
                      (clojure.core/seq search_space__272590)
                      (clojure.core/let
                       [elem__271773
                        (clojure.core/first search_space__272590)
                        result__272591
                        (clojure.core/let
                         [elem__271773_nth_0__
                          (clojure.core/nth elem__271773 0)
                          elem__271773_nth_1__
                          (clojure.core/nth elem__271773 1)]
                         (if
                          (clojure.core/symbol? elem__271773_nth_0__)
                          (clojure.core/let
                           [X__271775
                            (clojure.core/name elem__271773_nth_0__)]
                           (if
                            (clojure.core/= ?__270937 X__271775)
                            (if
                             (clojure.core/symbol?
                              elem__271773_nth_1__)
                             (clojure.core/let
                              [X__271777
                               (clojure.core/name
                                elem__271773_nth_1__)]
                              (clojure.core/case
                               X__271777
                               ("meander.zeta")
                               [?__270937]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__272591)
                        (recur
                         (clojure.core/next search_space__272590))
                        result__272591))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__272588)))]
          (if
           (clojure.core/vector? input__270931)
           (if
            (clojure.core/= (clojure.core/count input__270931) 2)
            (clojure.core/let
             [input__270931_nth_0__
              (clojure.core/nth input__270931 0)
              input__270931_nth_1__
              (clojure.core/nth input__270931 1)]
             (if
              (clojure.core/seq? input__270931_nth_0__)
              (clojure.core/let
               [input__270931_nth_0___l__
                (clojure.core/take 1 input__270931_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__270931_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__270931_nth_0___r__
                  (clojure.core/drop 1 input__270931_nth_0__)]
                 (clojure.core/let
                  [input__270931_nth_0___l___nth_0__
                   (clojure.core/nth input__270931_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__270931_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__271755
                     (clojure.core/namespace
                      input__270931_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__270937 X__271755]
                     (clojure.core/let
                      [X__271757
                       (clojure.core/name
                        input__270931_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__271757
                       ("apply")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__271745 input__270931_nth_1__ ?__270937)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__272427)
                         (clojure.core/let
                          [[?__270937] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__270931)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__270931)
                             2)
                            (clojure.core/let
                             [input__270931_nth_0__
                              (clojure.core/nth input__270931 0)
                              input__270931_nth_1__
                              (clojure.core/nth input__270931 1)]
                             (if
                              (clojure.core/seq? input__270931_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__270931_nth_0__)
                                3)
                               (clojure.core/let
                                [input__270931_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  1)
                                 input__270931_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?fn input__270931_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?pattern
                                   input__270931_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__270931_nth_0__]
                                   (clojure.core/let
                                    [?env input__270931_nth_1__]
                                    (try
                                     [{:tag :apply,
                                       :fn ?fn,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__271005
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
                               (state__272427))
                              (state__272427)))
                            (state__272427))
                           (state__272427)))))
                       (state__272427)))))
                   (state__272427))))
                (state__272427)))
              (state__272427)))
            (state__272427))
           (state__272427))))
        (state__272427
         []
         (clojure.core/letfn
          [(def__271779
            [arg__271804 ?__270938]
            (clojure.core/letfn
             [(state__272593
               []
               (clojure.core/let
                [x__271805 "meander.zeta"]
                (if
                 (clojure.core/= ?__270938 x__271805)
                 [?__270938]
                 (state__272594))))
              (state__272594
               []
               (if
                (clojure.core/map? arg__271804)
                (clojure.core/let
                 [VAL__271806 (.valAt arg__271804 :aliases)]
                 (if
                  (clojure.core/map? VAL__271806)
                  (clojure.core/let
                   [X__271808 (clojure.core/set VAL__271806)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__271808))
                    (clojure.core/loop
                     [search_space__272595
                      (clojure.core/seq X__271808)]
                     (if
                      (clojure.core/seq search_space__272595)
                      (clojure.core/let
                       [elem__271809
                        (clojure.core/first search_space__272595)
                        result__272596
                        (clojure.core/let
                         [elem__271809_nth_0__
                          (clojure.core/nth elem__271809 0)
                          elem__271809_nth_1__
                          (clojure.core/nth elem__271809 1)]
                         (if
                          (clojure.core/symbol? elem__271809_nth_0__)
                          (clojure.core/let
                           [X__271811
                            (clojure.core/name elem__271809_nth_0__)]
                           (if
                            (clojure.core/= ?__270938 X__271811)
                            (if
                             (clojure.core/symbol?
                              elem__271809_nth_1__)
                             (clojure.core/let
                              [X__271813
                               (clojure.core/name
                                elem__271809_nth_1__)]
                              (clojure.core/case
                               X__271813
                               ("meander.zeta")
                               [?__270938]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__272596)
                        (recur
                         (clojure.core/next search_space__272595))
                        result__272596))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__272593)))]
          (if
           (clojure.core/vector? input__270931)
           (if
            (clojure.core/= (clojure.core/count input__270931) 2)
            (clojure.core/let
             [input__270931_nth_0__
              (clojure.core/nth input__270931 0)
              input__270931_nth_1__
              (clojure.core/nth input__270931 1)]
             (if
              (clojure.core/seq? input__270931_nth_0__)
              (clojure.core/let
               [input__270931_nth_0___l__
                (clojure.core/take 1 input__270931_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__270931_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__270931_nth_0___r__
                  (clojure.core/drop 1 input__270931_nth_0__)]
                 (clojure.core/let
                  [input__270931_nth_0___l___nth_0__
                   (clojure.core/nth input__270931_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__270931_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__271791
                     (clojure.core/namespace
                      input__270931_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__270938 X__271791]
                     (clojure.core/let
                      [X__271793
                       (clojure.core/name
                        input__270931_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__271793
                       ("and")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__271779 input__270931_nth_1__ ?__270938)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__272428)
                         (clojure.core/let
                          [[?__270938] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__270931)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__270931)
                             2)
                            (clojure.core/let
                             [input__270931_nth_0__
                              (clojure.core/nth input__270931 0)
                              input__270931_nth_1__
                              (clojure.core/nth input__270931 1)]
                             (if
                              (clojure.core/seq? input__270931_nth_0__)
                              (clojure.core/let
                               [input__270931_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__270931_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__270931_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__270931_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__270931_nth_0__)]
                                 (clojure.core/let
                                  [!forms
                                   (clojure.core/vec
                                    input__270931_nth_0___r__)]
                                  (clojure.core/let
                                   [?form input__270931_nth_0__]
                                   (clojure.core/let
                                    [?env input__270931_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [!forms__counter
                                        (meander.runtime.zeta/iterator
                                         !forms)]
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__271005
                                          ['meander.dev.parse.zeta/make-and
                                           (clojure.core/into
                                            []
                                            (clojure.core/loop
                                             [return__271007
                                              (clojure.core/transient
                                               [])]
                                             (if
                                              (clojure.core/and
                                               (.hasNext
                                                !forms__counter))
                                              (recur
                                               (clojure.core/conj!
                                                return__271007
                                                (clojure.core/let
                                                 [CATA_RESULT__10883__auto__
                                                  (CATA__FN__271005
                                                   [(if
                                                     (.hasNext
                                                      !forms__counter)
                                                     (.next
                                                      !forms__counter))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10883__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10883__auto__
                                                   0)))))
                                              (clojure.core/persistent!
                                               return__271007))))
                                           ?form])]
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
                                       (throw e__11823__auto__))))))))
                                (state__272428)))
                              (state__272428)))
                            (state__272428))
                           (state__272428)))))
                       (state__272428)))))
                   (state__272428))))
                (state__272428)))
              (state__272428)))
            (state__272428))
           (state__272428))))
        (state__272428
         []
         (if
          (clojure.core/vector? input__270931)
          (if
           (clojure.core/= (clojure.core/count input__270931) 3)
           (clojure.core/let
            [input__270931_nth_0__
             (clojure.core/nth input__270931 0)
             input__270931_nth_1__
             (clojure.core/nth input__270931 1)
             input__270931_nth_2__
             (clojure.core/nth input__270931 2)]
            (clojure.core/case
             input__270931_nth_0__
             (meander.dev.parse.zeta/make-and)
             (clojure.core/letfn
              [(state__272598
                []
                (if
                 (clojure.core/vector? input__270931_nth_1__)
                 (clojure.core/case
                  input__270931_nth_1__
                  ([])
                  (clojure.core/let
                   [?form input__270931_nth_2__]
                   (try
                    [{:tag :error,
                      :message
                      "meander.zeta/and requires 1 or more arguments",
                      :form ?form}]
                    (catch
                     java.lang.Exception
                     e__11823__auto__
                     (if
                      (meander.runtime.zeta/fail? e__11823__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__11823__auto__)))))
                  (state__272599))
                 (state__272599)))
               (state__272599
                []
                (clojure.core/case
                 input__270931_nth_2__
                 (nil)
                 (if
                  (clojure.core/vector? input__270931_nth_1__)
                  (if
                   (clojure.core/=
                    (clojure.core/count input__270931_nth_1__)
                    1)
                   (clojure.core/let
                    [input__270931_nth_1___nth_0__
                     (clojure.core/nth input__270931_nth_1__ 0)]
                    (clojure.core/let
                     [?ast-a input__270931_nth_1___nth_0__]
                     (try
                      [?ast-a]
                      (catch
                       java.lang.Exception
                       e__11823__auto__
                       (if
                        (meander.runtime.zeta/fail? e__11823__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__11823__auto__))))))
                   (state__272600))
                  (state__272600))
                 (state__272600)))
               (state__272600
                []
                (if
                 (clojure.core/vector? input__270931_nth_1__)
                 (clojure.core/letfn
                  [(state__272601
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__270931_nth_1__)
                      1)
                     (clojure.core/let
                      [input__270931_nth_1___nth_0__
                       (clojure.core/nth input__270931_nth_1__ 0)]
                      (clojure.core/let
                       [?ast-a input__270931_nth_1___nth_0__]
                       (clojure.core/let
                        [?form input__270931_nth_2__]
                        (try
                         [(clojure.core/let
                           [CATA_RESULT__10883__auto__
                            (CATA__FN__271005
                             ['meander.dev.parse.zeta/make-and
                              [?ast-a {:tag :pass}]
                              ?form])]
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
                           (throw e__11823__auto__)))))))
                     (state__272602)))
                   (state__272602
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__270931_nth_1__)
                      2)
                     (clojure.core/let
                      [input__270931_nth_1___nth_0__
                       (clojure.core/nth input__270931_nth_1__ 0)
                       input__270931_nth_1___nth_1__
                       (clojure.core/nth input__270931_nth_1__ 1)]
                      (clojure.core/let
                       [?ast-a input__270931_nth_1___nth_0__]
                       (clojure.core/let
                        [?ast-b input__270931_nth_1___nth_1__]
                        (clojure.core/let
                         [?form input__270931_nth_2__]
                         (try
                          [{:tag :and,
                            :left ?ast-a,
                            :right ?ast-b,
                            :form ?form}]
                          (catch
                           java.lang.Exception
                           e__11823__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__11823__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__11823__auto__))))))))
                     (state__272603)))
                   (state__272603
                    []
                    (clojure.core/loop
                     [search_space__272604
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__270931_nth_1__)]
                     (if
                      (clojure.core/seq search_space__272604)
                      (clojure.core/let
                       [input__270931_nth_1___parts__
                        (clojure.core/first search_space__272604)
                        result__272605
                        (clojure.core/let
                         [input__270931_nth_1___l__
                          (clojure.core/nth
                           input__270931_nth_1___parts__
                           0)
                          input__270931_nth_1___r__
                          (clojure.core/nth
                           input__270931_nth_1___parts__
                           1)]
                         (clojure.core/letfn
                          [(state__272607
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__9744__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__270931_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__271840]
                                 (clojure.core/let
                                  [input__271840_nth_0__
                                   (clojure.core/nth input__271840 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__271840_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__271833
                                   (clojure.core/count
                                    input__270931_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__271833]
                                   (clojure.core/let
                                    [X__271837
                                     (clojure.core/count
                                      input__270931_nth_1___r__)]
                                    (if
                                     (clojure.core/= ?n X__271837)
                                     (clojure.core/let
                                      [!asts-2 []]
                                      (clojure.core/let
                                       [ret__9744__auto__
                                        (meander.runtime.zeta/epsilon-run-star-1
                                         input__270931_nth_1___r__
                                         [!asts-2 !asts-1]
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]
                                           input__271838]
                                          (clojure.core/let
                                           [input__271838_nth_0__
                                            (clojure.core/nth
                                             input__271838
                                             0)]
                                           (clojure.core/let
                                            [!asts-2
                                             (clojure.core/conj
                                              !asts-2
                                              input__271838_nth_0__)]
                                            [!asts-2 !asts-1])))
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]]
                                          (clojure.core/let
                                           [?form
                                            input__270931_nth_2__]
                                           (try
                                            [(clojure.core/let
                                              [!asts-1__counter
                                               (meander.runtime.zeta/iterator
                                                !asts-1)
                                               !asts-2__counter
                                               (meander.runtime.zeta/iterator
                                                !asts-2)]
                                              (clojure.core/let
                                               [CATA_RESULT__10883__auto__
                                                (CATA__FN__271005
                                                 ['meander.dev.parse.zeta/make-and
                                                  [(clojure.core/let
                                                    [CATA_RESULT__10883__auto__
                                                     (CATA__FN__271005
                                                      ['meander.dev.parse.zeta/make-and
                                                       (clojure.core/into
                                                        []
                                                        (clojure.core/vec
                                                         (clojure.core/iterator-seq
                                                          !asts-1__counter)))
                                                       nil])]
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
                                                     (CATA__FN__271005
                                                      ['meander.dev.parse.zeta/make-and
                                                       (clojure.core/into
                                                        []
                                                        (clojure.core/vec
                                                         (clojure.core/iterator-seq
                                                          !asts-2__counter)))
                                                       nil])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__10883__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__10883__auto__
                                                      0)))]
                                                  ?form])]
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
                                               e__11823__auto__)))))))]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         ret__9744__auto__)
                                        (state__272608)
                                        ret__9744__auto__)))
                                     (state__272608)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__9744__auto__)
                               (state__272608)
                               ret__9744__auto__))))
                           (state__272608
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__9744__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__270931_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__271856]
                                 (clojure.core/let
                                  [input__271856_nth_0__
                                   (clojure.core/nth input__271856 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__271856_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__271847
                                   (clojure.core/count
                                    input__270931_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__271847]
                                   (clojure.core/let
                                    [input__270931_nth_1___r___l__
                                     (clojure.core/subvec
                                      input__270931_nth_1___r__
                                      0
                                      (clojure.core/min
                                       (clojure.core/count
                                        input__270931_nth_1___r__)
                                       1))]
                                    (if
                                     (clojure.core/=
                                      (clojure.core/count
                                       input__270931_nth_1___r___l__)
                                      1)
                                     (clojure.core/let
                                      [input__270931_nth_1___r___r__
                                       (clojure.core/subvec
                                        input__270931_nth_1___r__
                                        1)]
                                      (clojure.core/let
                                       [input__270931_nth_1___r___l___nth_0__
                                        (clojure.core/nth
                                         input__270931_nth_1___r___l__
                                         0)]
                                       (clojure.core/let
                                        [?ast
                                         input__270931_nth_1___r___l___nth_0__]
                                        (clojure.core/let
                                         [X__271853
                                          (clojure.core/count
                                           input__270931_nth_1___r___r__)]
                                         (if
                                          (clojure.core/= ?n X__271853)
                                          (clojure.core/let
                                           [!asts-2 []]
                                           (clojure.core/let
                                            [ret__9744__auto__
                                             (meander.runtime.zeta/epsilon-run-star-1
                                              input__270931_nth_1___r___r__
                                              [!asts-2 !asts-1]
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]
                                                input__271854]
                                               (clojure.core/let
                                                [input__271854_nth_0__
                                                 (clojure.core/nth
                                                  input__271854
                                                  0)]
                                                (clojure.core/let
                                                 [!asts-2
                                                  (clojure.core/conj
                                                   !asts-2
                                                   input__271854_nth_0__)]
                                                 [!asts-2 !asts-1])))
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]]
                                               (clojure.core/let
                                                [?form
                                                 input__270931_nth_2__]
                                                (try
                                                 [(clojure.core/let
                                                   [!asts-1__counter
                                                    (meander.runtime.zeta/iterator
                                                     !asts-1)
                                                    !asts-2__counter
                                                    (meander.runtime.zeta/iterator
                                                     !asts-2)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__10883__auto__
                                                     (CATA__FN__271005
                                                      ['meander.dev.parse.zeta/make-and
                                                       [(clojure.core/let
                                                         [CATA_RESULT__10883__auto__
                                                          (CATA__FN__271005
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
                                                           CATA_RESULT__10883__auto__)
                                                          (throw
                                                           (meander.runtime.zeta/fail))
                                                          (clojure.core/nth
                                                           CATA_RESULT__10883__auto__
                                                           0)))
                                                        (clojure.core/let
                                                         [CATA_RESULT__10883__auto__
                                                          (CATA__FN__271005
                                                           ['meander.dev.parse.zeta/make-and
                                                            (clojure.core/into
                                                             []
                                                             (clojure.core/vec
                                                              (clojure.core/iterator-seq
                                                               !asts-2__counter)))
                                                            nil])]
                                                         (if
                                                          (meander.runtime.zeta/fail?
                                                           CATA_RESULT__10883__auto__)
                                                          (throw
                                                           (meander.runtime.zeta/fail))
                                                          (clojure.core/nth
                                                           CATA_RESULT__10883__auto__
                                                           0)))]
                                                       ?form])]
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
                                                    e__11823__auto__)))))))]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              ret__9744__auto__)
                                             (meander.runtime.zeta/fail)
                                             ret__9744__auto__)))
                                          (meander.runtime.zeta/fail))))))
                                     (meander.runtime.zeta/fail)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__9744__auto__)
                               (meander.runtime.zeta/fail)
                               ret__9744__auto__))))]
                          (state__272607)))]
                       (if
                        (meander.runtime.zeta/fail? result__272605)
                        (recur
                         (clojure.core/next search_space__272604))
                        result__272605))
                      (state__272429))))]
                  (state__272601))
                 (state__272429)))]
              (state__272598))
             (state__272429)))
           (state__272429))
          (state__272429)))
        (state__272429
         []
         (clojure.core/letfn
          [(def__271859
            [arg__271882 ?__270939]
            (clojure.core/letfn
             [(state__272613
               []
               (clojure.core/let
                [x__271883 "meander.zeta"]
                (if
                 (clojure.core/= ?__270939 x__271883)
                 [?__270939]
                 (state__272614))))
              (state__272614
               []
               (if
                (clojure.core/map? arg__271882)
                (clojure.core/let
                 [VAL__271884 (.valAt arg__271882 :aliases)]
                 (if
                  (clojure.core/map? VAL__271884)
                  (clojure.core/let
                   [X__271886 (clojure.core/set VAL__271884)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__271886))
                    (clojure.core/loop
                     [search_space__272615
                      (clojure.core/seq X__271886)]
                     (if
                      (clojure.core/seq search_space__272615)
                      (clojure.core/let
                       [elem__271887
                        (clojure.core/first search_space__272615)
                        result__272616
                        (clojure.core/let
                         [elem__271887_nth_0__
                          (clojure.core/nth elem__271887 0)
                          elem__271887_nth_1__
                          (clojure.core/nth elem__271887 1)]
                         (if
                          (clojure.core/symbol? elem__271887_nth_0__)
                          (clojure.core/let
                           [X__271889
                            (clojure.core/name elem__271887_nth_0__)]
                           (if
                            (clojure.core/= ?__270939 X__271889)
                            (if
                             (clojure.core/symbol?
                              elem__271887_nth_1__)
                             (clojure.core/let
                              [X__271891
                               (clojure.core/name
                                elem__271887_nth_1__)]
                              (clojure.core/case
                               X__271891
                               ("meander.zeta")
                               [?__270939]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__272616)
                        (recur
                         (clojure.core/next search_space__272615))
                        result__272616))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__272613)))]
          (if
           (clojure.core/vector? input__270931)
           (if
            (clojure.core/= (clojure.core/count input__270931) 2)
            (clojure.core/let
             [input__270931_nth_0__
              (clojure.core/nth input__270931 0)
              input__270931_nth_1__
              (clojure.core/nth input__270931 1)]
             (if
              (clojure.core/seq? input__270931_nth_0__)
              (clojure.core/let
               [input__270931_nth_0___l__
                (clojure.core/take 1 input__270931_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__270931_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__270931_nth_0___r__
                  (clojure.core/drop 1 input__270931_nth_0__)]
                 (clojure.core/let
                  [input__270931_nth_0___l___nth_0__
                   (clojure.core/nth input__270931_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__270931_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__271869
                     (clojure.core/namespace
                      input__270931_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__270939 X__271869]
                     (clojure.core/let
                      [X__271871
                       (clojure.core/name
                        input__270931_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__271871
                       ("cata")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__271859 input__270931_nth_1__ ?__270939)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__272430)
                         (clojure.core/let
                          [[?__270939] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__270931)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__270931)
                             2)
                            (clojure.core/let
                             [input__270931_nth_0__
                              (clojure.core/nth input__270931 0)
                              input__270931_nth_1__
                              (clojure.core/nth input__270931 1)]
                             (if
                              (clojure.core/seq? input__270931_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__270931_nth_0__)
                                2)
                               (clojure.core/let
                                [input__270931_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__270931_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__270931_nth_0__]
                                  (clojure.core/let
                                   [?env input__270931_nth_1__]
                                   (try
                                    [{:tag :cata,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__10883__auto__
                                        (CATA__FN__271005
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
                               (state__272430))
                              (state__272430)))
                            (state__272430))
                           (state__272430)))))
                       (state__272430)))))
                   (state__272430))))
                (state__272430)))
              (state__272430)))
            (state__272430))
           (state__272430))))
        (state__272430
         []
         (clojure.core/letfn
          [(def__271893
            [arg__271916 ?__270940]
            (clojure.core/letfn
             [(state__272618
               []
               (clojure.core/let
                [x__271917 "meander.zeta"]
                (if
                 (clojure.core/= ?__270940 x__271917)
                 [?__270940]
                 (state__272619))))
              (state__272619
               []
               (if
                (clojure.core/map? arg__271916)
                (clojure.core/let
                 [VAL__271918 (.valAt arg__271916 :aliases)]
                 (if
                  (clojure.core/map? VAL__271918)
                  (clojure.core/let
                   [X__271920 (clojure.core/set VAL__271918)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__271920))
                    (clojure.core/loop
                     [search_space__272620
                      (clojure.core/seq X__271920)]
                     (if
                      (clojure.core/seq search_space__272620)
                      (clojure.core/let
                       [elem__271921
                        (clojure.core/first search_space__272620)
                        result__272621
                        (clojure.core/let
                         [elem__271921_nth_0__
                          (clojure.core/nth elem__271921 0)
                          elem__271921_nth_1__
                          (clojure.core/nth elem__271921 1)]
                         (if
                          (clojure.core/symbol? elem__271921_nth_0__)
                          (clojure.core/let
                           [X__271923
                            (clojure.core/name elem__271921_nth_0__)]
                           (if
                            (clojure.core/= ?__270940 X__271923)
                            (if
                             (clojure.core/symbol?
                              elem__271921_nth_1__)
                             (clojure.core/let
                              [X__271925
                               (clojure.core/name
                                elem__271921_nth_1__)]
                              (clojure.core/case
                               X__271925
                               ("meander.zeta")
                               [?__270940]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__272621)
                        (recur
                         (clojure.core/next search_space__272620))
                        result__272621))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__272618)))]
          (if
           (clojure.core/vector? input__270931)
           (if
            (clojure.core/= (clojure.core/count input__270931) 2)
            (clojure.core/let
             [input__270931_nth_0__
              (clojure.core/nth input__270931 0)
              input__270931_nth_1__
              (clojure.core/nth input__270931 1)]
             (if
              (clojure.core/seq? input__270931_nth_0__)
              (clojure.core/let
               [input__270931_nth_0___l__
                (clojure.core/take 1 input__270931_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__270931_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__270931_nth_0___r__
                  (clojure.core/drop 1 input__270931_nth_0__)]
                 (clojure.core/let
                  [input__270931_nth_0___l___nth_0__
                   (clojure.core/nth input__270931_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__270931_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__271903
                     (clojure.core/namespace
                      input__270931_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__270940 X__271903]
                     (clojure.core/let
                      [X__271905
                       (clojure.core/name
                        input__270931_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__271905
                       ("fold")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__271893 input__270931_nth_1__ ?__270940)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__272431)
                         (clojure.core/let
                          [[?__270940] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__270931)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__270931)
                             2)
                            (clojure.core/let
                             [input__270931_nth_0__
                              (clojure.core/nth input__270931 0)
                              input__270931_nth_1__
                              (clojure.core/nth input__270931 1)]
                             (if
                              (clojure.core/seq? input__270931_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__270931_nth_0__)
                                4)
                               (clojure.core/let
                                [input__270931_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  1)
                                 input__270931_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  2)
                                 input__270931_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?mutable-variable
                                  input__270931_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?initial-value
                                   input__270931_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?fold-function
                                    input__270931_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__270931_nth_0__]
                                    (clojure.core/let
                                     [?env input__270931_nth_1__]
                                     (try
                                      [(clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__271005
                                          ['meander.dev.parse.zeta/make-fold
                                           (clojure.core/let
                                            [CATA_RESULT__10883__auto__
                                             (CATA__FN__271005
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
                                             (CATA__FN__271005
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
                               (state__272431))
                              (state__272431)))
                            (state__272431))
                           (state__272431)))))
                       (state__272431)))))
                   (state__272431))))
                (state__272431)))
              (state__272431)))
            (state__272431))
           (state__272431))))
        (state__272431
         []
         (if
          (clojure.core/vector? input__270931)
          (if
           (clojure.core/= (clojure.core/count input__270931) 5)
           (clojure.core/let
            [input__270931_nth_0__
             (clojure.core/nth input__270931 0)
             input__270931_nth_1__
             (clojure.core/nth input__270931 1)
             input__270931_nth_2__
             (clojure.core/nth input__270931 2)
             input__270931_nth_3__
             (clojure.core/nth input__270931 3)
             input__270931_nth_4__
             (clojure.core/nth input__270931 4)]
            (clojure.core/case
             input__270931_nth_0__
             (meander.dev.parse.zeta/make-fold)
             (if
              (clojure.core/map? input__270931_nth_1__)
              (clojure.core/let
               [VAL__271928 (.valAt input__270931_nth_1__ :tag)]
               (clojure.core/case
                VAL__271928
                (:mutable-variable)
                (clojure.core/let
                 [?variable-ast input__270931_nth_1__]
                 (clojure.core/let
                  [?initial-value-ast input__270931_nth_2__]
                  (clojure.core/let
                   [?fold-function input__270931_nth_3__]
                   (clojure.core/let
                    [?form input__270931_nth_4__]
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
                (state__272432)))
              (state__272432))
             (state__272432)))
           (state__272432))
          (state__272432)))
        (state__272432
         []
         (clojure.core/letfn
          [(def__271930
            [arg__271953 ?__270941]
            (clojure.core/letfn
             [(state__272623
               []
               (clojure.core/let
                [x__271954 "meander.zeta"]
                (if
                 (clojure.core/= ?__270941 x__271954)
                 [?__270941]
                 (state__272624))))
              (state__272624
               []
               (if
                (clojure.core/map? arg__271953)
                (clojure.core/let
                 [VAL__271955 (.valAt arg__271953 :aliases)]
                 (if
                  (clojure.core/map? VAL__271955)
                  (clojure.core/let
                   [X__271957 (clojure.core/set VAL__271955)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__271957))
                    (clojure.core/loop
                     [search_space__272625
                      (clojure.core/seq X__271957)]
                     (if
                      (clojure.core/seq search_space__272625)
                      (clojure.core/let
                       [elem__271958
                        (clojure.core/first search_space__272625)
                        result__272626
                        (clojure.core/let
                         [elem__271958_nth_0__
                          (clojure.core/nth elem__271958 0)
                          elem__271958_nth_1__
                          (clojure.core/nth elem__271958 1)]
                         (if
                          (clojure.core/symbol? elem__271958_nth_0__)
                          (clojure.core/let
                           [X__271960
                            (clojure.core/name elem__271958_nth_0__)]
                           (if
                            (clojure.core/= ?__270941 X__271960)
                            (if
                             (clojure.core/symbol?
                              elem__271958_nth_1__)
                             (clojure.core/let
                              [X__271962
                               (clojure.core/name
                                elem__271958_nth_1__)]
                              (clojure.core/case
                               X__271962
                               ("meander.zeta")
                               [?__270941]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__272626)
                        (recur
                         (clojure.core/next search_space__272625))
                        result__272626))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__272623)))]
          (if
           (clojure.core/vector? input__270931)
           (if
            (clojure.core/= (clojure.core/count input__270931) 2)
            (clojure.core/let
             [input__270931_nth_0__
              (clojure.core/nth input__270931 0)
              input__270931_nth_1__
              (clojure.core/nth input__270931 1)]
             (if
              (clojure.core/seq? input__270931_nth_0__)
              (clojure.core/let
               [input__270931_nth_0___l__
                (clojure.core/take 1 input__270931_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__270931_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__270931_nth_0___r__
                  (clojure.core/drop 1 input__270931_nth_0__)]
                 (clojure.core/let
                  [input__270931_nth_0___l___nth_0__
                   (clojure.core/nth input__270931_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__270931_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__271940
                     (clojure.core/namespace
                      input__270931_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__270941 X__271940]
                     (clojure.core/let
                      [X__271942
                       (clojure.core/name
                        input__270931_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__271942
                       ("let")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__271930 input__270931_nth_1__ ?__270941)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__272433)
                         (clojure.core/let
                          [[?__270941] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__270931)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__270931)
                             2)
                            (clojure.core/let
                             [input__270931_nth_0__
                              (clojure.core/nth input__270931 0)
                              input__270931_nth_1__
                              (clojure.core/nth input__270931 1)]
                             (if
                              (clojure.core/seq? input__270931_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__270931_nth_0__)
                                3)
                               (clojure.core/let
                                [input__270931_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  1)
                                 input__270931_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?pattern
                                  input__270931_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?expression
                                   input__270931_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__270931_nth_0__]
                                   (clojure.core/let
                                    [?env input__270931_nth_1__]
                                    (try
                                     [{:tag :let,
                                       :pattern
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__271005
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
                                       :next {:tag :pass}}]
                                     (catch
                                      java.lang.Exception
                                      e__11823__auto__
                                      (if
                                       (meander.runtime.zeta/fail?
                                        e__11823__auto__)
                                       (meander.runtime.zeta/fail)
                                       (throw e__11823__auto__)))))))))
                               (state__272433))
                              (state__272433)))
                            (state__272433))
                           (state__272433)))))
                       (state__272433)))))
                   (state__272433))))
                (state__272433)))
              (state__272433)))
            (state__272433))
           (state__272433))))
        (state__272433
         []
         (clojure.core/letfn
          [(def__271964
            [arg__271987 ?__270942]
            (clojure.core/letfn
             [(state__272628
               []
               (clojure.core/let
                [x__271988 "meander.zeta"]
                (if
                 (clojure.core/= ?__270942 x__271988)
                 [?__270942]
                 (state__272629))))
              (state__272629
               []
               (if
                (clojure.core/map? arg__271987)
                (clojure.core/let
                 [VAL__271989 (.valAt arg__271987 :aliases)]
                 (if
                  (clojure.core/map? VAL__271989)
                  (clojure.core/let
                   [X__271991 (clojure.core/set VAL__271989)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__271991))
                    (clojure.core/loop
                     [search_space__272630
                      (clojure.core/seq X__271991)]
                     (if
                      (clojure.core/seq search_space__272630)
                      (clojure.core/let
                       [elem__271992
                        (clojure.core/first search_space__272630)
                        result__272631
                        (clojure.core/let
                         [elem__271992_nth_0__
                          (clojure.core/nth elem__271992 0)
                          elem__271992_nth_1__
                          (clojure.core/nth elem__271992 1)]
                         (if
                          (clojure.core/symbol? elem__271992_nth_0__)
                          (clojure.core/let
                           [X__271994
                            (clojure.core/name elem__271992_nth_0__)]
                           (if
                            (clojure.core/= ?__270942 X__271994)
                            (if
                             (clojure.core/symbol?
                              elem__271992_nth_1__)
                             (clojure.core/let
                              [X__271996
                               (clojure.core/name
                                elem__271992_nth_1__)]
                              (clojure.core/case
                               X__271996
                               ("meander.zeta")
                               [?__270942]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__272631)
                        (recur
                         (clojure.core/next search_space__272630))
                        result__272631))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__272628)))]
          (if
           (clojure.core/vector? input__270931)
           (if
            (clojure.core/= (clojure.core/count input__270931) 2)
            (clojure.core/let
             [input__270931_nth_0__
              (clojure.core/nth input__270931 0)
              input__270931_nth_1__
              (clojure.core/nth input__270931 1)]
             (if
              (clojure.core/seq? input__270931_nth_0__)
              (clojure.core/let
               [input__270931_nth_0___l__
                (clojure.core/take 1 input__270931_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__270931_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__270931_nth_0___r__
                  (clojure.core/drop 1 input__270931_nth_0__)]
                 (clojure.core/let
                  [input__270931_nth_0___l___nth_0__
                   (clojure.core/nth input__270931_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__270931_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__271974
                     (clojure.core/namespace
                      input__270931_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__270942 X__271974]
                     (clojure.core/let
                      [X__271976
                       (clojure.core/name
                        input__270931_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__271976
                       ("let")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__271964 input__270931_nth_1__ ?__270942)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__272434)
                         (clojure.core/let
                          [[?__270942] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__270931)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__270931)
                             2)
                            (clojure.core/let
                             [input__270931_nth_0__
                              (clojure.core/nth input__270931 0)
                              input__270931_nth_1__
                              (clojure.core/nth input__270931 1)]
                             (if
                              (clojure.core/seq? input__270931_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 4)
                                 input__270931_nth_0__)
                                4)
                               (clojure.core/let
                                [input__270931_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  1)
                                 input__270931_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  2)
                                 input__270931_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  3)]
                                (clojure.core/let
                                 [?pattern
                                  input__270931_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?expression
                                   input__270931_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?next
                                    input__270931_nth_0___nth_3__]
                                   (clojure.core/let
                                    [?form input__270931_nth_0__]
                                    (clojure.core/let
                                     [?env input__270931_nth_1__]
                                     (try
                                      [{:tag :let,
                                        :pattern
                                        (clojure.core/let
                                         [CATA_RESULT__10883__auto__
                                          (CATA__FN__271005
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
                                          (CATA__FN__271005
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
                               (state__272434))
                              (state__272434)))
                            (state__272434))
                           (state__272434)))))
                       (state__272434)))))
                   (state__272434))))
                (state__272434)))
              (state__272434)))
            (state__272434))
           (state__272434))))
        (state__272434
         []
         (clojure.core/letfn
          [(def__271998
            [arg__272021 ?__270943]
            (clojure.core/letfn
             [(state__272633
               []
               (clojure.core/let
                [x__272022 "meander.zeta"]
                (if
                 (clojure.core/= ?__270943 x__272022)
                 [?__270943]
                 (state__272634))))
              (state__272634
               []
               (if
                (clojure.core/map? arg__272021)
                (clojure.core/let
                 [VAL__272023 (.valAt arg__272021 :aliases)]
                 (if
                  (clojure.core/map? VAL__272023)
                  (clojure.core/let
                   [X__272025 (clojure.core/set VAL__272023)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__272025))
                    (clojure.core/loop
                     [search_space__272635
                      (clojure.core/seq X__272025)]
                     (if
                      (clojure.core/seq search_space__272635)
                      (clojure.core/let
                       [elem__272026
                        (clojure.core/first search_space__272635)
                        result__272636
                        (clojure.core/let
                         [elem__272026_nth_0__
                          (clojure.core/nth elem__272026 0)
                          elem__272026_nth_1__
                          (clojure.core/nth elem__272026 1)]
                         (if
                          (clojure.core/symbol? elem__272026_nth_0__)
                          (clojure.core/let
                           [X__272028
                            (clojure.core/name elem__272026_nth_0__)]
                           (if
                            (clojure.core/= ?__270943 X__272028)
                            (if
                             (clojure.core/symbol?
                              elem__272026_nth_1__)
                             (clojure.core/let
                              [X__272030
                               (clojure.core/name
                                elem__272026_nth_1__)]
                              (clojure.core/case
                               X__272030
                               ("meander.zeta")
                               [?__270943]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__272636)
                        (recur
                         (clojure.core/next search_space__272635))
                        result__272636))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__272633)))]
          (if
           (clojure.core/vector? input__270931)
           (if
            (clojure.core/= (clojure.core/count input__270931) 2)
            (clojure.core/let
             [input__270931_nth_0__
              (clojure.core/nth input__270931 0)
              input__270931_nth_1__
              (clojure.core/nth input__270931 1)]
             (if
              (clojure.core/seq? input__270931_nth_0__)
              (clojure.core/let
               [input__270931_nth_0___l__
                (clojure.core/take 1 input__270931_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__270931_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__270931_nth_0___r__
                  (clojure.core/drop 1 input__270931_nth_0__)]
                 (clojure.core/let
                  [input__270931_nth_0___l___nth_0__
                   (clojure.core/nth input__270931_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__270931_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__272008
                     (clojure.core/namespace
                      input__270931_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__270943 X__272008]
                     (clojure.core/let
                      [X__272010
                       (clojure.core/name
                        input__270931_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__272010
                       ("not")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__271998 input__270931_nth_1__ ?__270943)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__272435)
                         (clojure.core/let
                          [[?__270943] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__270931)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__270931)
                             2)
                            (clojure.core/let
                             [input__270931_nth_0__
                              (clojure.core/nth input__270931 0)
                              input__270931_nth_1__
                              (clojure.core/nth input__270931 1)]
                             (if
                              (clojure.core/seq? input__270931_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__270931_nth_0__)
                                2)
                               (clojure.core/let
                                [input__270931_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?pattern
                                  input__270931_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__270931_nth_0__]
                                  (clojure.core/let
                                   [?env input__270931_nth_1__]
                                   (try
                                    [{:tag :not,
                                      :pattern
                                      (clojure.core/let
                                       [CATA_RESULT__10883__auto__
                                        (CATA__FN__271005
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
                               (state__272435))
                              (state__272435)))
                            (state__272435))
                           (state__272435)))))
                       (state__272435)))))
                   (state__272435))))
                (state__272435)))
              (state__272435)))
            (state__272435))
           (state__272435))))
        (state__272435
         []
         (clojure.core/letfn
          [(def__272032
            [arg__272057 ?__270944]
            (clojure.core/letfn
             [(state__272638
               []
               (clojure.core/let
                [x__272058 "meander.zeta"]
                (if
                 (clojure.core/= ?__270944 x__272058)
                 [?__270944]
                 (state__272639))))
              (state__272639
               []
               (if
                (clojure.core/map? arg__272057)
                (clojure.core/let
                 [VAL__272059 (.valAt arg__272057 :aliases)]
                 (if
                  (clojure.core/map? VAL__272059)
                  (clojure.core/let
                   [X__272061 (clojure.core/set VAL__272059)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__272061))
                    (clojure.core/loop
                     [search_space__272640
                      (clojure.core/seq X__272061)]
                     (if
                      (clojure.core/seq search_space__272640)
                      (clojure.core/let
                       [elem__272062
                        (clojure.core/first search_space__272640)
                        result__272641
                        (clojure.core/let
                         [elem__272062_nth_0__
                          (clojure.core/nth elem__272062 0)
                          elem__272062_nth_1__
                          (clojure.core/nth elem__272062 1)]
                         (if
                          (clojure.core/symbol? elem__272062_nth_0__)
                          (clojure.core/let
                           [X__272064
                            (clojure.core/name elem__272062_nth_0__)]
                           (if
                            (clojure.core/= ?__270944 X__272064)
                            (if
                             (clojure.core/symbol?
                              elem__272062_nth_1__)
                             (clojure.core/let
                              [X__272066
                               (clojure.core/name
                                elem__272062_nth_1__)]
                              (clojure.core/case
                               X__272066
                               ("meander.zeta")
                               [?__270944]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__272641)
                        (recur
                         (clojure.core/next search_space__272640))
                        result__272641))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__272638)))]
          (if
           (clojure.core/vector? input__270931)
           (if
            (clojure.core/= (clojure.core/count input__270931) 2)
            (clojure.core/let
             [input__270931_nth_0__
              (clojure.core/nth input__270931 0)
              input__270931_nth_1__
              (clojure.core/nth input__270931 1)]
             (if
              (clojure.core/seq? input__270931_nth_0__)
              (clojure.core/let
               [input__270931_nth_0___l__
                (clojure.core/take 1 input__270931_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__270931_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__270931_nth_0___r__
                  (clojure.core/drop 1 input__270931_nth_0__)]
                 (clojure.core/let
                  [input__270931_nth_0___l___nth_0__
                   (clojure.core/nth input__270931_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__270931_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__272044
                     (clojure.core/namespace
                      input__270931_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__270944 X__272044]
                     (clojure.core/let
                      [X__272046
                       (clojure.core/name
                        input__270931_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__272046
                       ("or")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__272032 input__270931_nth_1__ ?__270944)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__272436)
                         (clojure.core/let
                          [[?__270944] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__270931)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__270931)
                             2)
                            (clojure.core/let
                             [input__270931_nth_0__
                              (clojure.core/nth input__270931 0)
                              input__270931_nth_1__
                              (clojure.core/nth input__270931 1)]
                             (if
                              (clojure.core/seq? input__270931_nth_0__)
                              (clojure.core/let
                               [input__270931_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__270931_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__270931_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__270931_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__270931_nth_0__)]
                                 (clojure.core/let
                                  [!forms
                                   (clojure.core/vec
                                    input__270931_nth_0___r__)]
                                  (clojure.core/let
                                   [?form input__270931_nth_0__]
                                   (clojure.core/let
                                    [?env input__270931_nth_1__]
                                    (try
                                     [(clojure.core/let
                                       [!forms__counter
                                        (meander.runtime.zeta/iterator
                                         !forms)]
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__271005
                                          ['meander.dev.parse.zeta/make-or
                                           (clojure.core/into
                                            []
                                            (clojure.core/loop
                                             [return__271008
                                              (clojure.core/transient
                                               [])]
                                             (if
                                              (clojure.core/and
                                               (.hasNext
                                                !forms__counter))
                                              (recur
                                               (clojure.core/conj!
                                                return__271008
                                                (clojure.core/let
                                                 [CATA_RESULT__10883__auto__
                                                  (CATA__FN__271005
                                                   [(if
                                                     (.hasNext
                                                      !forms__counter)
                                                     (.next
                                                      !forms__counter))
                                                    ?env])]
                                                 (if
                                                  (meander.runtime.zeta/fail?
                                                   CATA_RESULT__10883__auto__)
                                                  (throw
                                                   (meander.runtime.zeta/fail))
                                                  (clojure.core/nth
                                                   CATA_RESULT__10883__auto__
                                                   0)))))
                                              (clojure.core/persistent!
                                               return__271008))))
                                           ?form])]
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
                                       (throw e__11823__auto__))))))))
                                (state__272436)))
                              (state__272436)))
                            (state__272436))
                           (state__272436)))))
                       (state__272436)))))
                   (state__272436))))
                (state__272436)))
              (state__272436)))
            (state__272436))
           (state__272436))))
        (state__272436
         []
         (if
          (clojure.core/vector? input__270931)
          (if
           (clojure.core/= (clojure.core/count input__270931) 3)
           (clojure.core/let
            [input__270931_nth_0__
             (clojure.core/nth input__270931 0)
             input__270931_nth_1__
             (clojure.core/nth input__270931 1)
             input__270931_nth_2__
             (clojure.core/nth input__270931 2)]
            (clojure.core/case
             input__270931_nth_0__
             (meander.dev.parse.zeta/make-or)
             (clojure.core/letfn
              [(state__272643
                []
                (if
                 (clojure.core/vector? input__270931_nth_1__)
                 (clojure.core/case
                  input__270931_nth_1__
                  ([])
                  (clojure.core/let
                   [?form input__270931_nth_2__]
                   (try
                    [{:tag :error,
                      :message
                      "meander.zeta/or requires 1 or more arguments",
                      :form ?form}]
                    (catch
                     java.lang.Exception
                     e__11823__auto__
                     (if
                      (meander.runtime.zeta/fail? e__11823__auto__)
                      (meander.runtime.zeta/fail)
                      (throw e__11823__auto__)))))
                  (state__272644))
                 (state__272644)))
               (state__272644
                []
                (clojure.core/case
                 input__270931_nth_2__
                 (nil)
                 (if
                  (clojure.core/vector? input__270931_nth_1__)
                  (if
                   (clojure.core/=
                    (clojure.core/count input__270931_nth_1__)
                    1)
                   (clojure.core/let
                    [input__270931_nth_1___nth_0__
                     (clojure.core/nth input__270931_nth_1__ 0)]
                    (clojure.core/let
                     [?ast-a input__270931_nth_1___nth_0__]
                     (try
                      [?ast-a]
                      (catch
                       java.lang.Exception
                       e__11823__auto__
                       (if
                        (meander.runtime.zeta/fail? e__11823__auto__)
                        (meander.runtime.zeta/fail)
                        (throw e__11823__auto__))))))
                   (state__272645))
                  (state__272645))
                 (state__272645)))
               (state__272645
                []
                (if
                 (clojure.core/vector? input__270931_nth_1__)
                 (clojure.core/letfn
                  [(state__272646
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__270931_nth_1__)
                      1)
                     (clojure.core/let
                      [input__270931_nth_1___nth_0__
                       (clojure.core/nth input__270931_nth_1__ 0)]
                      (clojure.core/let
                       [?ast-a input__270931_nth_1___nth_0__]
                       (clojure.core/let
                        [?form input__270931_nth_2__]
                        (try
                         [(clojure.core/let
                           [CATA_RESULT__10883__auto__
                            (CATA__FN__271005
                             ['meander.dev.parse.zeta/make-or
                              [?ast-a {:tag :pass}]
                              ?form])]
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
                           (throw e__11823__auto__)))))))
                     (state__272647)))
                   (state__272647
                    []
                    (if
                     (clojure.core/=
                      (clojure.core/count input__270931_nth_1__)
                      2)
                     (clojure.core/let
                      [input__270931_nth_1___nth_0__
                       (clojure.core/nth input__270931_nth_1__ 0)
                       input__270931_nth_1___nth_1__
                       (clojure.core/nth input__270931_nth_1__ 1)]
                      (clojure.core/let
                       [?ast-a input__270931_nth_1___nth_0__]
                       (clojure.core/let
                        [?ast-b input__270931_nth_1___nth_1__]
                        (clojure.core/let
                         [?form input__270931_nth_2__]
                         (try
                          [{:tag :or,
                            :left ?ast-a,
                            :right ?ast-b,
                            :form ?form}]
                          (catch
                           java.lang.Exception
                           e__11823__auto__
                           (if
                            (meander.runtime.zeta/fail?
                             e__11823__auto__)
                            (meander.runtime.zeta/fail)
                            (throw e__11823__auto__))))))))
                     (state__272648)))
                   (state__272648
                    []
                    (clojure.core/loop
                     [search_space__272649
                      (meander.runtime.zeta/epsilon-partitions
                       2
                       input__270931_nth_1__)]
                     (if
                      (clojure.core/seq search_space__272649)
                      (clojure.core/let
                       [input__270931_nth_1___parts__
                        (clojure.core/first search_space__272649)
                        result__272650
                        (clojure.core/let
                         [input__270931_nth_1___l__
                          (clojure.core/nth
                           input__270931_nth_1___parts__
                           0)
                          input__270931_nth_1___r__
                          (clojure.core/nth
                           input__270931_nth_1___parts__
                           1)]
                         (clojure.core/letfn
                          [(state__272652
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__9744__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__270931_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__272093]
                                 (clojure.core/let
                                  [input__272093_nth_0__
                                   (clojure.core/nth input__272093 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__272093_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__272086
                                   (clojure.core/count
                                    input__270931_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__272086]
                                   (clojure.core/let
                                    [X__272090
                                     (clojure.core/count
                                      input__270931_nth_1___r__)]
                                    (if
                                     (clojure.core/= ?n X__272090)
                                     (clojure.core/let
                                      [!asts-2 []]
                                      (clojure.core/let
                                       [ret__9744__auto__
                                        (meander.runtime.zeta/epsilon-run-star-1
                                         input__270931_nth_1___r__
                                         [!asts-2 !asts-1]
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]
                                           input__272091]
                                          (clojure.core/let
                                           [input__272091_nth_0__
                                            (clojure.core/nth
                                             input__272091
                                             0)]
                                           (clojure.core/let
                                            [!asts-2
                                             (clojure.core/conj
                                              !asts-2
                                              input__272091_nth_0__)]
                                            [!asts-2 !asts-1])))
                                         (clojure.core/fn
                                          [[!asts-2 !asts-1]]
                                          (clojure.core/let
                                           [?form
                                            input__270931_nth_2__]
                                           (try
                                            [(clojure.core/let
                                              [!asts-1__counter
                                               (meander.runtime.zeta/iterator
                                                !asts-1)
                                               !asts-2__counter
                                               (meander.runtime.zeta/iterator
                                                !asts-2)]
                                              (clojure.core/let
                                               [CATA_RESULT__10883__auto__
                                                (CATA__FN__271005
                                                 ['meander.dev.parse.zeta/make-or
                                                  [(clojure.core/let
                                                    [CATA_RESULT__10883__auto__
                                                     (CATA__FN__271005
                                                      ['meander.dev.parse.zeta/make-or
                                                       (clojure.core/into
                                                        []
                                                        (clojure.core/vec
                                                         (clojure.core/iterator-seq
                                                          !asts-1__counter)))
                                                       nil])]
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
                                                     (CATA__FN__271005
                                                      ['meander.dev.parse.zeta/make-or
                                                       (clojure.core/into
                                                        []
                                                        (clojure.core/vec
                                                         (clojure.core/iterator-seq
                                                          !asts-2__counter)))
                                                       nil])]
                                                    (if
                                                     (meander.runtime.zeta/fail?
                                                      CATA_RESULT__10883__auto__)
                                                     (throw
                                                      (meander.runtime.zeta/fail))
                                                     (clojure.core/nth
                                                      CATA_RESULT__10883__auto__
                                                      0)))]
                                                  ?form])]
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
                                               e__11823__auto__)))))))]
                                       (if
                                        (meander.runtime.zeta/fail?
                                         ret__9744__auto__)
                                        (state__272653)
                                        ret__9744__auto__)))
                                     (state__272653)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__9744__auto__)
                               (state__272653)
                               ret__9744__auto__))))
                           (state__272653
                            []
                            (clojure.core/let
                             [!asts-1 []]
                             (clojure.core/let
                              [ret__9744__auto__
                               (meander.runtime.zeta/epsilon-run-star-1
                                input__270931_nth_1___l__
                                [!asts-1]
                                (clojure.core/fn
                                 [[!asts-1] input__272109]
                                 (clojure.core/let
                                  [input__272109_nth_0__
                                   (clojure.core/nth input__272109 0)]
                                  (clojure.core/let
                                   [!asts-1
                                    (clojure.core/conj
                                     !asts-1
                                     input__272109_nth_0__)]
                                   [!asts-1])))
                                (clojure.core/fn
                                 [[!asts-1]]
                                 (clojure.core/let
                                  [X__272100
                                   (clojure.core/count
                                    input__270931_nth_1___l__)]
                                  (clojure.core/let
                                   [?n X__272100]
                                   (clojure.core/let
                                    [input__270931_nth_1___r___l__
                                     (clojure.core/subvec
                                      input__270931_nth_1___r__
                                      0
                                      (clojure.core/min
                                       (clojure.core/count
                                        input__270931_nth_1___r__)
                                       1))]
                                    (if
                                     (clojure.core/=
                                      (clojure.core/count
                                       input__270931_nth_1___r___l__)
                                      1)
                                     (clojure.core/let
                                      [input__270931_nth_1___r___r__
                                       (clojure.core/subvec
                                        input__270931_nth_1___r__
                                        1)]
                                      (clojure.core/let
                                       [input__270931_nth_1___r___l___nth_0__
                                        (clojure.core/nth
                                         input__270931_nth_1___r___l__
                                         0)]
                                       (clojure.core/let
                                        [?ast
                                         input__270931_nth_1___r___l___nth_0__]
                                        (clojure.core/let
                                         [X__272106
                                          (clojure.core/count
                                           input__270931_nth_1___r___r__)]
                                         (if
                                          (clojure.core/= ?n X__272106)
                                          (clojure.core/let
                                           [!asts-2 []]
                                           (clojure.core/let
                                            [ret__9744__auto__
                                             (meander.runtime.zeta/epsilon-run-star-1
                                              input__270931_nth_1___r___r__
                                              [!asts-2 !asts-1]
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]
                                                input__272107]
                                               (clojure.core/let
                                                [input__272107_nth_0__
                                                 (clojure.core/nth
                                                  input__272107
                                                  0)]
                                                (clojure.core/let
                                                 [!asts-2
                                                  (clojure.core/conj
                                                   !asts-2
                                                   input__272107_nth_0__)]
                                                 [!asts-2 !asts-1])))
                                              (clojure.core/fn
                                               [[!asts-2 !asts-1]]
                                               (clojure.core/let
                                                [?form
                                                 input__270931_nth_2__]
                                                (try
                                                 [(clojure.core/let
                                                   [!asts-1__counter
                                                    (meander.runtime.zeta/iterator
                                                     !asts-1)
                                                    !asts-2__counter
                                                    (meander.runtime.zeta/iterator
                                                     !asts-2)]
                                                   (clojure.core/let
                                                    [CATA_RESULT__10883__auto__
                                                     (CATA__FN__271005
                                                      ['meander.dev.parse.zeta/make-or
                                                       [(clojure.core/let
                                                         [CATA_RESULT__10883__auto__
                                                          (CATA__FN__271005
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
                                                           CATA_RESULT__10883__auto__)
                                                          (throw
                                                           (meander.runtime.zeta/fail))
                                                          (clojure.core/nth
                                                           CATA_RESULT__10883__auto__
                                                           0)))
                                                        (clojure.core/let
                                                         [CATA_RESULT__10883__auto__
                                                          (CATA__FN__271005
                                                           ['meander.dev.parse.zeta/make-or
                                                            (clojure.core/into
                                                             []
                                                             (clojure.core/vec
                                                              (clojure.core/iterator-seq
                                                               !asts-2__counter)))
                                                            nil])]
                                                         (if
                                                          (meander.runtime.zeta/fail?
                                                           CATA_RESULT__10883__auto__)
                                                          (throw
                                                           (meander.runtime.zeta/fail))
                                                          (clojure.core/nth
                                                           CATA_RESULT__10883__auto__
                                                           0)))]
                                                       ?form])]
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
                                                    e__11823__auto__)))))))]
                                            (if
                                             (meander.runtime.zeta/fail?
                                              ret__9744__auto__)
                                             (meander.runtime.zeta/fail)
                                             ret__9744__auto__)))
                                          (meander.runtime.zeta/fail))))))
                                     (meander.runtime.zeta/fail)))))))]
                              (if
                               (meander.runtime.zeta/fail?
                                ret__9744__auto__)
                               (meander.runtime.zeta/fail)
                               ret__9744__auto__))))]
                          (state__272652)))]
                       (if
                        (meander.runtime.zeta/fail? result__272650)
                        (recur
                         (clojure.core/next search_space__272649))
                        result__272650))
                      (state__272437))))]
                  (state__272646))
                 (state__272437)))]
              (state__272643))
             (state__272437)))
           (state__272437))
          (state__272437)))
        (state__272437
         []
         (clojure.core/letfn
          [(def__272112
            [arg__272135 ?__270945]
            (clojure.core/letfn
             [(state__272658
               []
               (clojure.core/let
                [x__272136 "meander.zeta"]
                (if
                 (clojure.core/= ?__270945 x__272136)
                 [?__270945]
                 (state__272659))))
              (state__272659
               []
               (if
                (clojure.core/map? arg__272135)
                (clojure.core/let
                 [VAL__272137 (.valAt arg__272135 :aliases)]
                 (if
                  (clojure.core/map? VAL__272137)
                  (clojure.core/let
                   [X__272139 (clojure.core/set VAL__272137)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__272139))
                    (clojure.core/loop
                     [search_space__272660
                      (clojure.core/seq X__272139)]
                     (if
                      (clojure.core/seq search_space__272660)
                      (clojure.core/let
                       [elem__272140
                        (clojure.core/first search_space__272660)
                        result__272661
                        (clojure.core/let
                         [elem__272140_nth_0__
                          (clojure.core/nth elem__272140 0)
                          elem__272140_nth_1__
                          (clojure.core/nth elem__272140 1)]
                         (if
                          (clojure.core/symbol? elem__272140_nth_0__)
                          (clojure.core/let
                           [X__272142
                            (clojure.core/name elem__272140_nth_0__)]
                           (if
                            (clojure.core/= ?__270945 X__272142)
                            (if
                             (clojure.core/symbol?
                              elem__272140_nth_1__)
                             (clojure.core/let
                              [X__272144
                               (clojure.core/name
                                elem__272140_nth_1__)]
                              (clojure.core/case
                               X__272144
                               ("meander.zeta")
                               [?__270945]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__272661)
                        (recur
                         (clojure.core/next search_space__272660))
                        result__272661))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__272658)))]
          (if
           (clojure.core/vector? input__270931)
           (if
            (clojure.core/= (clojure.core/count input__270931) 2)
            (clojure.core/let
             [input__270931_nth_0__
              (clojure.core/nth input__270931 0)
              input__270931_nth_1__
              (clojure.core/nth input__270931 1)]
             (if
              (clojure.core/seq? input__270931_nth_0__)
              (clojure.core/let
               [input__270931_nth_0___l__
                (clojure.core/take 1 input__270931_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__270931_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__270931_nth_0___r__
                  (clojure.core/drop 1 input__270931_nth_0__)]
                 (clojure.core/let
                  [input__270931_nth_0___l___nth_0__
                   (clojure.core/nth input__270931_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__270931_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__272122
                     (clojure.core/namespace
                      input__270931_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__270945 X__272122]
                     (clojure.core/let
                      [X__272124
                       (clojure.core/name
                        input__270931_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__272124
                       ("pred")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__272112 input__270931_nth_1__ ?__270945)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__272438)
                         (clojure.core/let
                          [[?__270945] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__270931)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__270931)
                             2)
                            (clojure.core/let
                             [input__270931_nth_0__
                              (clojure.core/nth input__270931 0)
                              input__270931_nth_1__
                              (clojure.core/nth input__270931 1)]
                             (if
                              (clojure.core/seq? input__270931_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__270931_nth_0__)
                                2)
                               (clojure.core/let
                                [input__270931_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?expression
                                  input__270931_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__270931_nth_0__]
                                  (clojure.core/let
                                   [?env input__270931_nth_1__]
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
                               (state__272438))
                              (state__272438)))
                            (state__272438))
                           (state__272438)))))
                       (state__272438)))))
                   (state__272438))))
                (state__272438)))
              (state__272438)))
            (state__272438))
           (state__272438))))
        (state__272438
         []
         (clojure.core/letfn
          [(def__272146
            [arg__272169 ?__270946]
            (clojure.core/letfn
             [(state__272663
               []
               (clojure.core/let
                [x__272170 "meander.zeta"]
                (if
                 (clojure.core/= ?__270946 x__272170)
                 [?__270946]
                 (state__272664))))
              (state__272664
               []
               (if
                (clojure.core/map? arg__272169)
                (clojure.core/let
                 [VAL__272171 (.valAt arg__272169 :aliases)]
                 (if
                  (clojure.core/map? VAL__272171)
                  (clojure.core/let
                   [X__272173 (clojure.core/set VAL__272171)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__272173))
                    (clojure.core/loop
                     [search_space__272665
                      (clojure.core/seq X__272173)]
                     (if
                      (clojure.core/seq search_space__272665)
                      (clojure.core/let
                       [elem__272174
                        (clojure.core/first search_space__272665)
                        result__272666
                        (clojure.core/let
                         [elem__272174_nth_0__
                          (clojure.core/nth elem__272174 0)
                          elem__272174_nth_1__
                          (clojure.core/nth elem__272174 1)]
                         (if
                          (clojure.core/symbol? elem__272174_nth_0__)
                          (clojure.core/let
                           [X__272176
                            (clojure.core/name elem__272174_nth_0__)]
                           (if
                            (clojure.core/= ?__270946 X__272176)
                            (if
                             (clojure.core/symbol?
                              elem__272174_nth_1__)
                             (clojure.core/let
                              [X__272178
                               (clojure.core/name
                                elem__272174_nth_1__)]
                              (clojure.core/case
                               X__272178
                               ("meander.zeta")
                               [?__270946]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__272666)
                        (recur
                         (clojure.core/next search_space__272665))
                        result__272666))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__272663)))]
          (if
           (clojure.core/vector? input__270931)
           (if
            (clojure.core/= (clojure.core/count input__270931) 2)
            (clojure.core/let
             [input__270931_nth_0__
              (clojure.core/nth input__270931 0)
              input__270931_nth_1__
              (clojure.core/nth input__270931 1)]
             (if
              (clojure.core/seq? input__270931_nth_0__)
              (clojure.core/let
               [input__270931_nth_0___l__
                (clojure.core/take 1 input__270931_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__270931_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__270931_nth_0___r__
                  (clojure.core/drop 1 input__270931_nth_0__)]
                 (clojure.core/let
                  [input__270931_nth_0___l___nth_0__
                   (clojure.core/nth input__270931_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__270931_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__272156
                     (clojure.core/namespace
                      input__270931_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__270946 X__272156]
                     (clojure.core/let
                      [X__272158
                       (clojure.core/name
                        input__270931_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__272158
                       ("re")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__272146 input__270931_nth_1__ ?__270946)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__272439)
                         (clojure.core/let
                          [[?__270946] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__270931)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__270931)
                             2)
                            (clojure.core/let
                             [input__270931_nth_0__
                              (clojure.core/nth input__270931 0)
                              input__270931_nth_1__
                              (clojure.core/nth input__270931 1)]
                             (if
                              (clojure.core/seq? input__270931_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__270931_nth_0__)
                                2)
                               (clojure.core/let
                                [input__270931_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?regex input__270931_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__270931_nth_0__]
                                  (clojure.core/let
                                   [?env input__270931_nth_1__]
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
                               (state__272439))
                              (state__272439)))
                            (state__272439))
                           (state__272439)))))
                       (state__272439)))))
                   (state__272439))))
                (state__272439)))
              (state__272439)))
            (state__272439))
           (state__272439))))
        (state__272439
         []
         (clojure.core/letfn
          [(def__272180
            [arg__272203 ?__270947]
            (clojure.core/letfn
             [(state__272668
               []
               (clojure.core/let
                [x__272204 "meander.zeta"]
                (if
                 (clojure.core/= ?__270947 x__272204)
                 [?__270947]
                 (state__272669))))
              (state__272669
               []
               (if
                (clojure.core/map? arg__272203)
                (clojure.core/let
                 [VAL__272205 (.valAt arg__272203 :aliases)]
                 (if
                  (clojure.core/map? VAL__272205)
                  (clojure.core/let
                   [X__272207 (clojure.core/set VAL__272205)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__272207))
                    (clojure.core/loop
                     [search_space__272670
                      (clojure.core/seq X__272207)]
                     (if
                      (clojure.core/seq search_space__272670)
                      (clojure.core/let
                       [elem__272208
                        (clojure.core/first search_space__272670)
                        result__272671
                        (clojure.core/let
                         [elem__272208_nth_0__
                          (clojure.core/nth elem__272208 0)
                          elem__272208_nth_1__
                          (clojure.core/nth elem__272208 1)]
                         (if
                          (clojure.core/symbol? elem__272208_nth_0__)
                          (clojure.core/let
                           [X__272210
                            (clojure.core/name elem__272208_nth_0__)]
                           (if
                            (clojure.core/= ?__270947 X__272210)
                            (if
                             (clojure.core/symbol?
                              elem__272208_nth_1__)
                             (clojure.core/let
                              [X__272212
                               (clojure.core/name
                                elem__272208_nth_1__)]
                              (clojure.core/case
                               X__272212
                               ("meander.zeta")
                               [?__270947]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__272671)
                        (recur
                         (clojure.core/next search_space__272670))
                        result__272671))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__272668)))]
          (if
           (clojure.core/vector? input__270931)
           (if
            (clojure.core/= (clojure.core/count input__270931) 2)
            (clojure.core/let
             [input__270931_nth_0__
              (clojure.core/nth input__270931 0)
              input__270931_nth_1__
              (clojure.core/nth input__270931 1)]
             (if
              (clojure.core/seq? input__270931_nth_0__)
              (clojure.core/let
               [input__270931_nth_0___l__
                (clojure.core/take 1 input__270931_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__270931_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__270931_nth_0___r__
                  (clojure.core/drop 1 input__270931_nth_0__)]
                 (clojure.core/let
                  [input__270931_nth_0___l___nth_0__
                   (clojure.core/nth input__270931_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__270931_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__272190
                     (clojure.core/namespace
                      input__270931_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__270947 X__272190]
                     (clojure.core/let
                      [X__272192
                       (clojure.core/name
                        input__270931_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__272192
                       ("re")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__272180 input__270931_nth_1__ ?__270947)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__272440)
                         (clojure.core/let
                          [[?__270947] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__270931)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__270931)
                             2)
                            (clojure.core/let
                             [input__270931_nth_0__
                              (clojure.core/nth input__270931 0)
                              input__270931_nth_1__
                              (clojure.core/nth input__270931 1)]
                             (if
                              (clojure.core/seq? input__270931_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__270931_nth_0__)
                                3)
                               (clojure.core/let
                                [input__270931_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  1)
                                 input__270931_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?regex input__270931_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?capture
                                   input__270931_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__270931_nth_0__]
                                   (clojure.core/let
                                    [?env input__270931_nth_1__]
                                    (try
                                     [{:tag :regex,
                                       :regex ?regex,
                                       :capture
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__271005
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
                               (state__272440))
                              (state__272440)))
                            (state__272440))
                           (state__272440)))))
                       (state__272440)))))
                   (state__272440))))
                (state__272440)))
              (state__272440)))
            (state__272440))
           (state__272440))))
        (state__272440
         []
         (clojure.core/letfn
          [(def__272214
            [arg__272237 ?__270948]
            (clojure.core/letfn
             [(state__272673
               []
               (clojure.core/let
                [x__272238 "meander.zeta"]
                (if
                 (clojure.core/= ?__270948 x__272238)
                 [?__270948]
                 (state__272674))))
              (state__272674
               []
               (if
                (clojure.core/map? arg__272237)
                (clojure.core/let
                 [VAL__272239 (.valAt arg__272237 :aliases)]
                 (if
                  (clojure.core/map? VAL__272239)
                  (clojure.core/let
                   [X__272241 (clojure.core/set VAL__272239)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__272241))
                    (clojure.core/loop
                     [search_space__272675
                      (clojure.core/seq X__272241)]
                     (if
                      (clojure.core/seq search_space__272675)
                      (clojure.core/let
                       [elem__272242
                        (clojure.core/first search_space__272675)
                        result__272676
                        (clojure.core/let
                         [elem__272242_nth_0__
                          (clojure.core/nth elem__272242 0)
                          elem__272242_nth_1__
                          (clojure.core/nth elem__272242 1)]
                         (if
                          (clojure.core/symbol? elem__272242_nth_0__)
                          (clojure.core/let
                           [X__272244
                            (clojure.core/name elem__272242_nth_0__)]
                           (if
                            (clojure.core/= ?__270948 X__272244)
                            (if
                             (clojure.core/symbol?
                              elem__272242_nth_1__)
                             (clojure.core/let
                              [X__272246
                               (clojure.core/name
                                elem__272242_nth_1__)]
                              (clojure.core/case
                               X__272246
                               ("meander.zeta")
                               [?__270948]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__272676)
                        (recur
                         (clojure.core/next search_space__272675))
                        result__272676))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__272673)))]
          (if
           (clojure.core/vector? input__270931)
           (if
            (clojure.core/= (clojure.core/count input__270931) 2)
            (clojure.core/let
             [input__270931_nth_0__
              (clojure.core/nth input__270931 0)
              input__270931_nth_1__
              (clojure.core/nth input__270931 1)]
             (if
              (clojure.core/seq? input__270931_nth_0__)
              (clojure.core/let
               [input__270931_nth_0___l__
                (clojure.core/take 1 input__270931_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__270931_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__270931_nth_0___r__
                  (clojure.core/drop 1 input__270931_nth_0__)]
                 (clojure.core/let
                  [input__270931_nth_0___l___nth_0__
                   (clojure.core/nth input__270931_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__270931_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__272224
                     (clojure.core/namespace
                      input__270931_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__270948 X__272224]
                     (clojure.core/let
                      [X__272226
                       (clojure.core/name
                        input__270931_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__272226
                       ("string")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__272214 input__270931_nth_1__ ?__270948)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__272441)
                         (clojure.core/let
                          [[?__270948] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__270931)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__270931)
                             2)
                            (clojure.core/let
                             [input__270931_nth_0__
                              (clojure.core/nth input__270931 0)
                              input__270931_nth_1__
                              (clojure.core/nth input__270931 1)]
                             (if
                              (clojure.core/seq? input__270931_nth_0__)
                              (clojure.core/let
                               [input__270931_nth_0___l__
                                (clojure.core/take
                                 1
                                 input__270931_nth_0__)]
                               (if
                                (clojure.core/=
                                 (clojure.core/bounded-count
                                  (clojure.core/inc 1)
                                  input__270931_nth_0___l__)
                                 1)
                                (clojure.core/let
                                 [input__270931_nth_0___r__
                                  (clojure.core/drop
                                   1
                                   input__270931_nth_0__)]
                                 (clojure.core/let
                                  [?sequence input__270931_nth_0___r__]
                                  (clojure.core/let
                                   [?form input__270931_nth_0__]
                                   (clojure.core/let
                                    [?env input__270931_nth_1__]
                                    (try
                                     [{:tag :string,
                                       :next
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__271005
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
                                (state__272441)))
                              (state__272441)))
                            (state__272441))
                           (state__272441)))))
                       (state__272441)))))
                   (state__272441))))
                (state__272441)))
              (state__272441)))
            (state__272441))
           (state__272441))))
        (state__272441
         []
         (clojure.core/letfn
          [(def__272248
            [arg__272271 ?__270949]
            (clojure.core/letfn
             [(state__272678
               []
               (clojure.core/let
                [x__272272 "meander.zeta"]
                (if
                 (clojure.core/= ?__270949 x__272272)
                 [?__270949]
                 (state__272679))))
              (state__272679
               []
               (if
                (clojure.core/map? arg__272271)
                (clojure.core/let
                 [VAL__272273 (.valAt arg__272271 :aliases)]
                 (if
                  (clojure.core/map? VAL__272273)
                  (clojure.core/let
                   [X__272275 (clojure.core/set VAL__272273)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__272275))
                    (clojure.core/loop
                     [search_space__272680
                      (clojure.core/seq X__272275)]
                     (if
                      (clojure.core/seq search_space__272680)
                      (clojure.core/let
                       [elem__272276
                        (clojure.core/first search_space__272680)
                        result__272681
                        (clojure.core/let
                         [elem__272276_nth_0__
                          (clojure.core/nth elem__272276 0)
                          elem__272276_nth_1__
                          (clojure.core/nth elem__272276 1)]
                         (if
                          (clojure.core/symbol? elem__272276_nth_0__)
                          (clojure.core/let
                           [X__272278
                            (clojure.core/name elem__272276_nth_0__)]
                           (if
                            (clojure.core/= ?__270949 X__272278)
                            (if
                             (clojure.core/symbol?
                              elem__272276_nth_1__)
                             (clojure.core/let
                              [X__272280
                               (clojure.core/name
                                elem__272276_nth_1__)]
                              (clojure.core/case
                               X__272280
                               ("meander.zeta")
                               [?__270949]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__272681)
                        (recur
                         (clojure.core/next search_space__272680))
                        result__272681))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__272678)))]
          (if
           (clojure.core/vector? input__270931)
           (if
            (clojure.core/= (clojure.core/count input__270931) 2)
            (clojure.core/let
             [input__270931_nth_0__
              (clojure.core/nth input__270931 0)
              input__270931_nth_1__
              (clojure.core/nth input__270931 1)]
             (if
              (clojure.core/seq? input__270931_nth_0__)
              (clojure.core/let
               [input__270931_nth_0___l__
                (clojure.core/take 1 input__270931_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__270931_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__270931_nth_0___r__
                  (clojure.core/drop 1 input__270931_nth_0__)]
                 (clojure.core/let
                  [input__270931_nth_0___l___nth_0__
                   (clojure.core/nth input__270931_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__270931_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__272258
                     (clojure.core/namespace
                      input__270931_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__270949 X__272258]
                     (clojure.core/let
                      [X__272260
                       (clojure.core/name
                        input__270931_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__272260
                       ("symbol")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__272248 input__270931_nth_1__ ?__270949)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__272442)
                         (clojure.core/let
                          [[?__270949] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__270931)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__270931)
                             2)
                            (clojure.core/let
                             [input__270931_nth_0__
                              (clojure.core/nth input__270931 0)
                              input__270931_nth_1__
                              (clojure.core/nth input__270931 1)]
                             (if
                              (clojure.core/seq? input__270931_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 2)
                                 input__270931_nth_0__)
                                2)
                               (clojure.core/let
                                [input__270931_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  1)]
                                (clojure.core/let
                                 [?name input__270931_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?form input__270931_nth_0__]
                                  (clojure.core/let
                                   [?env input__270931_nth_1__]
                                   (try
                                    [{:tag :symbol,
                                      :name
                                      (clojure.core/let
                                       [CATA_RESULT__10883__auto__
                                        (CATA__FN__271005
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
                               (state__272442))
                              (state__272442)))
                            (state__272442))
                           (state__272442)))))
                       (state__272442)))))
                   (state__272442))))
                (state__272442)))
              (state__272442)))
            (state__272442))
           (state__272442))))
        (state__272442
         []
         (clojure.core/letfn
          [(def__272282
            [arg__272305 ?__270950]
            (clojure.core/letfn
             [(state__272683
               []
               (clojure.core/let
                [x__272306 "meander.zeta"]
                (if
                 (clojure.core/= ?__270950 x__272306)
                 [?__270950]
                 (state__272684))))
              (state__272684
               []
               (if
                (clojure.core/map? arg__272305)
                (clojure.core/let
                 [VAL__272307 (.valAt arg__272305 :aliases)]
                 (if
                  (clojure.core/map? VAL__272307)
                  (clojure.core/let
                   [X__272309 (clojure.core/set VAL__272307)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__272309))
                    (clojure.core/loop
                     [search_space__272685
                      (clojure.core/seq X__272309)]
                     (if
                      (clojure.core/seq search_space__272685)
                      (clojure.core/let
                       [elem__272310
                        (clojure.core/first search_space__272685)
                        result__272686
                        (clojure.core/let
                         [elem__272310_nth_0__
                          (clojure.core/nth elem__272310 0)
                          elem__272310_nth_1__
                          (clojure.core/nth elem__272310 1)]
                         (if
                          (clojure.core/symbol? elem__272310_nth_0__)
                          (clojure.core/let
                           [X__272312
                            (clojure.core/name elem__272310_nth_0__)]
                           (if
                            (clojure.core/= ?__270950 X__272312)
                            (if
                             (clojure.core/symbol?
                              elem__272310_nth_1__)
                             (clojure.core/let
                              [X__272314
                               (clojure.core/name
                                elem__272310_nth_1__)]
                              (clojure.core/case
                               X__272314
                               ("meander.zeta")
                               [?__270950]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__272686)
                        (recur
                         (clojure.core/next search_space__272685))
                        result__272686))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__272683)))]
          (if
           (clojure.core/vector? input__270931)
           (if
            (clojure.core/= (clojure.core/count input__270931) 2)
            (clojure.core/let
             [input__270931_nth_0__
              (clojure.core/nth input__270931 0)
              input__270931_nth_1__
              (clojure.core/nth input__270931 1)]
             (if
              (clojure.core/seq? input__270931_nth_0__)
              (clojure.core/let
               [input__270931_nth_0___l__
                (clojure.core/take 1 input__270931_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__270931_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__270931_nth_0___r__
                  (clojure.core/drop 1 input__270931_nth_0__)]
                 (clojure.core/let
                  [input__270931_nth_0___l___nth_0__
                   (clojure.core/nth input__270931_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__270931_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__272292
                     (clojure.core/namespace
                      input__270931_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__270950 X__272292]
                     (clojure.core/let
                      [X__272294
                       (clojure.core/name
                        input__270931_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__272294
                       ("symbol")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__272282 input__270931_nth_1__ ?__270950)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__272443)
                         (clojure.core/let
                          [[?__270950] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__270931)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__270931)
                             2)
                            (clojure.core/let
                             [input__270931_nth_0__
                              (clojure.core/nth input__270931 0)
                              input__270931_nth_1__
                              (clojure.core/nth input__270931 1)]
                             (if
                              (clojure.core/seq? input__270931_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 3)
                                 input__270931_nth_0__)
                                3)
                               (clojure.core/let
                                [input__270931_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  1)
                                 input__270931_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  2)]
                                (clojure.core/let
                                 [?namespace
                                  input__270931_nth_0___nth_1__]
                                 (clojure.core/let
                                  [?name input__270931_nth_0___nth_2__]
                                  (clojure.core/let
                                   [?form input__270931_nth_0__]
                                   (clojure.core/let
                                    [?env input__270931_nth_1__]
                                    (try
                                     [{:tag :symbol,
                                       :name
                                       (clojure.core/let
                                        [CATA_RESULT__10883__auto__
                                         (CATA__FN__271005
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
                                         (CATA__FN__271005
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
                               (state__272443))
                              (state__272443)))
                            (state__272443))
                           (state__272443)))))
                       (state__272443)))))
                   (state__272443))))
                (state__272443)))
              (state__272443)))
            (state__272443))
           (state__272443))))
        (state__272443
         []
         (clojure.core/letfn
          [(def__272316
            [arg__272339 ?__270951]
            (clojure.core/letfn
             [(state__272688
               []
               (clojure.core/let
                [x__272340 "meander.zeta"]
                (if
                 (clojure.core/= ?__270951 x__272340)
                 [?__270951]
                 (state__272689))))
              (state__272689
               []
               (if
                (clojure.core/map? arg__272339)
                (clojure.core/let
                 [VAL__272341 (.valAt arg__272339 :aliases)]
                 (if
                  (clojure.core/map? VAL__272341)
                  (clojure.core/let
                   [X__272343 (clojure.core/set VAL__272341)]
                   (if
                    (clojure.core/<= 1 (clojure.core/count X__272343))
                    (clojure.core/loop
                     [search_space__272690
                      (clojure.core/seq X__272343)]
                     (if
                      (clojure.core/seq search_space__272690)
                      (clojure.core/let
                       [elem__272344
                        (clojure.core/first search_space__272690)
                        result__272691
                        (clojure.core/let
                         [elem__272344_nth_0__
                          (clojure.core/nth elem__272344 0)
                          elem__272344_nth_1__
                          (clojure.core/nth elem__272344 1)]
                         (if
                          (clojure.core/symbol? elem__272344_nth_0__)
                          (clojure.core/let
                           [X__272346
                            (clojure.core/name elem__272344_nth_0__)]
                           (if
                            (clojure.core/= ?__270951 X__272346)
                            (if
                             (clojure.core/symbol?
                              elem__272344_nth_1__)
                             (clojure.core/let
                              [X__272348
                               (clojure.core/name
                                elem__272344_nth_1__)]
                              (clojure.core/case
                               X__272348
                               ("meander.zeta")
                               [?__270951]
                               (meander.runtime.zeta/fail)))
                             (meander.runtime.zeta/fail))
                            (meander.runtime.zeta/fail)))
                          (meander.runtime.zeta/fail)))]
                       (if
                        (meander.runtime.zeta/fail? result__272691)
                        (recur
                         (clojure.core/next search_space__272690))
                        result__272691))
                      (meander.runtime.zeta/fail)))
                    (meander.runtime.zeta/fail)))
                  (meander.runtime.zeta/fail)))
                (meander.runtime.zeta/fail)))]
             (state__272688)))]
          (if
           (clojure.core/vector? input__270931)
           (if
            (clojure.core/= (clojure.core/count input__270931) 2)
            (clojure.core/let
             [input__270931_nth_0__
              (clojure.core/nth input__270931 0)
              input__270931_nth_1__
              (clojure.core/nth input__270931 1)]
             (if
              (clojure.core/seq? input__270931_nth_0__)
              (clojure.core/let
               [input__270931_nth_0___l__
                (clojure.core/take 1 input__270931_nth_0__)]
               (if
                (clojure.core/=
                 (clojure.core/bounded-count
                  (clojure.core/inc 1)
                  input__270931_nth_0___l__)
                 1)
                (clojure.core/let
                 [input__270931_nth_0___r__
                  (clojure.core/drop 1 input__270931_nth_0__)]
                 (clojure.core/let
                  [input__270931_nth_0___l___nth_0__
                   (clojure.core/nth input__270931_nth_0___l__ 0)]
                  (if
                   (clojure.core/symbol?
                    input__270931_nth_0___l___nth_0__)
                   (clojure.core/let
                    [X__272326
                     (clojure.core/namespace
                      input__270931_nth_0___l___nth_0__)]
                    (clojure.core/let
                     [?__270951 X__272326]
                     (clojure.core/let
                      [X__272328
                       (clojure.core/name
                        input__270931_nth_0___l___nth_0__)]
                      (clojure.core/case
                       X__272328
                       ("symbol")
                       (clojure.core/let
                        [x__9580__auto__
                         (def__272316 input__270931_nth_1__ ?__270951)]
                        (if
                         (meander.runtime.zeta/fail? x__9580__auto__)
                         (state__272444)
                         (clojure.core/let
                          [[?__270951] x__9580__auto__]
                          (if
                           (clojure.core/vector? input__270931)
                           (if
                            (clojure.core/=
                             (clojure.core/count input__270931)
                             2)
                            (clojure.core/let
                             [input__270931_nth_0__
                              (clojure.core/nth input__270931 0)
                              input__270931_nth_1__
                              (clojure.core/nth input__270931 1)]
                             (if
                              (clojure.core/seq? input__270931_nth_0__)
                              (if
                               (clojure.core/=
                                (clojure.core/bounded-count
                                 (clojure.core/inc 5)
                                 input__270931_nth_0__)
                                5)
                               (clojure.core/let
                                [input__270931_nth_0___nth_1__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  1)
                                 input__270931_nth_0___nth_2__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  2)
                                 input__270931_nth_0___nth_3__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  3)
                                 input__270931_nth_0___nth_4__
                                 (clojure.core/nth
                                  input__270931_nth_0__
                                  4)]
                                (clojure.core/case
                                 input__270931_nth_0___nth_3__
                                 (:meander.zeta/as)
                                 (clojure.core/let
                                  [?namespace
                                   input__270931_nth_0___nth_1__]
                                  (clojure.core/let
                                   [?name
                                    input__270931_nth_0___nth_2__]
                                   (clojure.core/let
                                    [?pattern
                                     input__270931_nth_0___nth_4__]
                                    (clojure.core/let
                                     [?form input__270931_nth_0__]
                                     (clojure.core/let
                                      [?env input__270931_nth_1__]
                                      (try
                                       [{:tag :symbol,
                                         :name
                                         (clojure.core/let
                                          [CATA_RESULT__10883__auto__
                                           (CATA__FN__271005
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
                                           (CATA__FN__271005
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
                                           (CATA__FN__271005
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
                                 (state__272444)))
                               (state__272444))
                              (state__272444)))
                            (state__272444))
                           (state__272444)))))
                       (state__272444)))))
                   (state__272444))))
                (state__272444)))
              (state__272444)))
            (state__272444))
           (state__272444))))
        (state__272444
         []
         (if
          (clojure.core/vector? input__270931)
          (if
           (clojure.core/= (clojure.core/count input__270931) 2)
           (clojure.core/let
            [input__270931_nth_0__ (clojure.core/nth input__270931 0)]
            (clojure.core/letfn
             [(state__272693
               []
               (clojure.core/let
                [input__270931_nth_1__
                 (clojure.core/nth input__270931 1)]
                (clojure.core/letfn
                 [(state__272698
                   []
                   (if
                    (clojure.core/seq? input__270931_nth_0__)
                    (clojure.core/let
                     [?sequence input__270931_nth_0__]
                     (clojure.core/let
                      [?env input__270931_nth_1__]
                      (try
                       [{:tag :seq,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__10883__auto__
                           (CATA__FN__271005
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
                    (state__272699)))
                  (state__272699
                   []
                   (if
                    (clojure.core/map? input__270931_nth_0__)
                    (clojure.core/let
                     [?map input__270931_nth_0__]
                     (clojure.core/let
                      [?env input__270931_nth_1__]
                      (try
                       [{:tag :map,
                         :next
                         (clojure.core/let
                          [CATA_RESULT__10883__auto__
                           (CATA__FN__271005
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
                    (state__272694)))]
                 (state__272698))))
              (state__272694
               []
               (if
                (clojure.core/symbol? input__270931_nth_0__)
                (clojure.core/let
                 [X__272358
                  (clojure.core/namespace input__270931_nth_0__)]
                 (clojure.core/let
                  [X__272360 (clojure.core/name input__270931_nth_0__)]
                  (if
                   (clojure.core/string? X__272360)
                   (clojure.core/letfn
                    [(state__272700
                      []
                      (clojure.core/let
                       [ret__272361
                        (clojure.core/re-matches #"_.*" X__272360)]
                       (if
                        (clojure.core/some? ret__272361)
                        (clojure.core/let
                         [?name ret__272361]
                         (clojure.core/let
                          [?symbol input__270931_nth_0__]
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
                        (state__272701))))
                     (state__272701
                      []
                      (clojure.core/let
                       [ret__272368
                        (clojure.core/re-matches #".+#" X__272360)]
                       (if
                        (clojure.core/some? ret__272368)
                        (clojure.core/let
                         [?name ret__272368]
                         (clojure.core/let
                          [?symbol input__270931_nth_0__]
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
                        (state__272702))))
                     (state__272702
                      []
                      (clojure.core/let
                       [ret__272375
                        (clojure.core/re-matches #"%.+" X__272360)]
                       (if
                        (clojure.core/some? ret__272375)
                        (clojure.core/let
                         [?name ret__272375]
                         (clojure.core/let
                          [?symbol input__270931_nth_0__]
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
                        (state__272703))))
                     (state__272703
                      []
                      (clojure.core/let
                       [ret__272382
                        (clojure.core/re-matches #"\*.+" X__272360)]
                       (if
                        (clojure.core/some? ret__272382)
                        (clojure.core/let
                         [?name ret__272382]
                         (clojure.core/let
                          [?symbol input__270931_nth_0__]
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
                        (state__272704))))
                     (state__272704
                      []
                      (clojure.core/let
                       [ret__272389
                        (clojure.core/re-matches #"\!.+" X__272360)]
                       (if
                        (clojure.core/some? ret__272389)
                        (clojure.core/let
                         [?name ret__272389]
                         (clojure.core/let
                          [?symbol input__270931_nth_0__]
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
                        (state__272705))))
                     (state__272705
                      []
                      (clojure.core/let
                       [ret__272396
                        (clojure.core/re-matches #"\?.+" X__272360)]
                       (if
                        (clojure.core/some? ret__272396)
                        (clojure.core/let
                         [?name ret__272396]
                         (clojure.core/let
                          [?symbol input__270931_nth_0__]
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
                        (state__272695))))]
                    (state__272700))
                   (state__272695))))
                (state__272695)))
              (state__272695
               []
               (if
                (string? input__270931_nth_0__)
                (clojure.core/let
                 [?x input__270931_nth_0__]
                 (try
                  [{:tag :literal, :type :string, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__11823__auto__
                   (if
                    (meander.runtime.zeta/fail? e__11823__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__11823__auto__)))))
                (state__272696)))
              (state__272696
               []
               (if
                (char? input__270931_nth_0__)
                (clojure.core/let
                 [?x input__270931_nth_0__]
                 (try
                  [{:tag :literal, :type :char, :form ?x}]
                  (catch
                   java.lang.Exception
                   e__11823__auto__
                   (if
                    (meander.runtime.zeta/fail? e__11823__auto__)
                    (meander.runtime.zeta/fail)
                    (throw e__11823__auto__)))))
                (state__272697)))
              (state__272697
               []
               (clojure.core/let
                [?x input__270931_nth_0__]
                (try
                 [{:tag :literal, :form ?x}]
                 (catch
                  java.lang.Exception
                  e__11823__auto__
                  (if
                   (meander.runtime.zeta/fail? e__11823__auto__)
                   (meander.runtime.zeta/fail)
                   (throw e__11823__auto__))))))]
             (state__272693)))
           (state__272445))
          (state__272445)))
        (state__272445
         []
         (clojure.core/let
          [?x input__270931]
          (try
           [{:tag :mistake, :x ?x}]
           (catch
            java.lang.Exception
            e__11823__auto__
            (if
             (meander.runtime.zeta/fail? e__11823__auto__)
             (meander.runtime.zeta/fail)
             (throw e__11823__auto__))))))]
       (state__272409)))]
    (clojure.core/let
     [x__9580__auto__ (CATA__FN__271005 input__270931)]
     (if
      (meander.runtime.zeta/fail? x__9580__auto__)
      (meander.runtime.zeta/fail)
      (clojure.core/let
       [[CATA_RETURN__271009] x__9580__auto__]
       CATA_RETURN__271009))))]
  (if
   (meander.runtime.zeta/fail? ret__9760__auto__)
   nil
   ret__9760__auto__)))
